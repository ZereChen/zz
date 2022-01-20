/*
����
 */
package handling;

import static abc.Game.���ͻ��˰汾1;
import constants.ServerConstants;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import client.MapleClient;
import handling.cashshop.CashShopServer;
import handling.channel.ChannelServer;
import handling.cashshop.handler.*;
import handling.channel.handler.*;
import handling.login.LoginServer;
import handling.login.handler.*;
import handling.netty.MaplePacketDecoder;
import handling.world.MapleParty;
import handling.world.World;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import static io.netty.handler.timeout.IdleState.WRITER_IDLE;
import java.io.File;
import java.io.FileWriter;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import server.Randomizer;
import tools.MapleAESOFB;
import tools.packet.LoginPacket;
import tools.data.ByteArrayByteStream;
import tools.data.LittleEndianAccessor;
import tools.Pair;

import server.MTSStorage;
import server.ServerProperties;
import tools.FileoutputUtil;
import static tools.FileoutputUtil.CurrentReadable_Date;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;

public class MapleServerHandler extends ChannelDuplexHandler {

    public static final boolean Log_Packets = true;
    private final int channel;
    private final List<String> BlockedIP = new ArrayList<>();
    private final Map<String, Pair<Long, Byte>> tracker = new ConcurrentHashMap<>();
    //Screw locking. Doesn't matter.
    //private static final ReentrantReadWriteLock IPLoggingLock = new ReentrantReadWriteLock();
    private static final String nl = System.getProperty("line.separator");
    private static final HashMap<String, FileWriter> logIPMap = new HashMap<>();
    private static boolean debugMode = Boolean.parseBoolean(ServerProperties.getProperty("mxmxd.����ģʽ", "false"));
    //Note to Zero: Use an enumset. Don't iterate through an array.
    private static final EnumSet<RecvPacketOpcode> blocked = EnumSet.noneOf(RecvPacketOpcode.class);

    static {
        RecvPacketOpcode[] block = new RecvPacketOpcode[]{
            RecvPacketOpcode.NPC_ACTION,
            RecvPacketOpcode.MOVE_PLAYER,
            RecvPacketOpcode.MOVE_PET,
            RecvPacketOpcode.MOVE_SUMMON,
            RecvPacketOpcode.MOVE_DRAGON,
            RecvPacketOpcode.MOVE_LIFE,
            RecvPacketOpcode.HEAL_OVER_TIME,
            RecvPacketOpcode.STRANGE_DATA};
        blocked.addAll(Arrays.asList(block));
    }

    //���IP����¼���򷵻��ļ�д������������Ч��
    private static FileWriter isLoggedIP(Channel sess) {
        String a = sess.remoteAddress().toString();
        String realIP = a.substring(a.indexOf('/') + 1, a.indexOf(':'));
        return logIPMap.get(realIP);
    }
// <editor-fold defaultstate="collapsed" desc="Packet Log Implementation">
    private static final int Log_Size = 10000;
    private static final ArrayList<LoggedPacket> Packet_Log = new ArrayList<LoggedPacket>(Log_Size);
    private static final ReentrantReadWriteLock Packet_Log_Lock = new ReentrantReadWriteLock();
    private static final File Packet_Log_Output = new File("PacketLog.txt");

    public static void log(LittleEndianAccessor packet, RecvPacketOpcode op, MapleClient c, Channel io) {
        if (blocked.contains(op)) {
            return;
        }
        try {
            Packet_Log_Lock.writeLock().lock();
            LoggedPacket logged = null;
            if (Packet_Log.size() == Log_Size) {
                logged = Packet_Log.remove(0);
            }
            //This way, we don't create new LoggedPacket objects, we reuse them =]
            if (logged == null) {
                logged = new LoggedPacket(packet, op, io.remoteAddress().toString(),
                        c == null ? -1 : c.getAccID(),
                        c == null || c.getAccountName() == null ? "[Null]" : c.getAccountName(),
                        c == null || c.getPlayer() == null || c.getPlayer().getName() == null ? "[Null]" : c.getPlayer().getName());
            } else {
                logged.setInfo(packet, op, io.remoteAddress().toString(),
                        c == null ? -1 : c.getAccID(),
                        c == null || c.getAccountName() == null ? "[Null]" : c.getAccountName(),
                        c == null || c.getPlayer() == null || c.getPlayer().getName() == null ? "[Null]" : c.getPlayer().getName());
            }
            Packet_Log.add(logged);
        } finally {
            Packet_Log_Lock.writeLock().unlock();
        }
    }

    private static class LoggedPacket {

        private static final String nl = System.getProperty("line.separator");
        private String ip, accName, accId, chrName;
        private LittleEndianAccessor packet;
        private long timestamp;
        private RecvPacketOpcode op;

        public LoggedPacket(LittleEndianAccessor p, RecvPacketOpcode op, String ip, int id, String accName, String chrName) {
            setInfo(p, op, ip, id, accName, chrName);
        }

        public final void setInfo(LittleEndianAccessor p, RecvPacketOpcode op, String ip, int id, String accName, String chrName) {
            this.ip = ip;
            this.op = op;
            packet = p;
            this.accName = accName;
            this.chrName = chrName;
            timestamp = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[IP: ").append(ip).append("] [").append(accId).append('|').append(accName).append('|').append(chrName).append("] [Time: ").append(timestamp).append(']');
            sb.append(nl);
            sb.append("[Op: ").append(op.toString()).append(']');
            sb.append(" [Data: ").append(packet.toString()).append(']');
            return sb.toString();
        }
    }

    public void writeLog() {
        try {
            FileWriter fw = new FileWriter(Packet_Log_Output, true);
            try {
                Packet_Log_Lock.readLock().lock();
                String nl = System.getProperty("line.separator");
                for (LoggedPacket loggedPacket : Packet_Log) {
                    fw.write(loggedPacket.toString());
                    fw.write(nl);
                }
                fw.flush();
                fw.close();
            } finally {
                Packet_Log_Lock.readLock().unlock();
            }
        } catch (IOException ex) {
            System.out.println("Error writing log to file.");
        }
    }

    // </editor-fold>

    public MapleServerHandler(int channel) {
        this.channel = channel;
    }

//    @Override
//    public void messageSent(final IoSession session, final Object message) throws Exception {
////        if (MapleParty.����3 > 0) {
////            System.out.println("messageSent");
////        }
//        final Runnable r = ((MaplePacket) message).getOnSend();
//        if (r != null) {
//            r.run();
//        }
//        super.messageSent(session, message);
//    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        if (MapleParty.����3 > 0) {
            System.out.println("exceptionCaught");
        }
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {

        // Start of IP checking
        final String address = ctx.channel().remoteAddress().toString().split(":")[0];
        if (BlockedIP.contains(address)) {
//            System.out.print("�Զ��Ͽ�����A");
//            session.close();
            return;
        }
        final Pair<Long, Byte> track = tracker.get(address);
        byte count;
        if (track == null) {
            count = 1;
        } else {
            count = track.right;
            final long difference = System.currentTimeMillis() - track.left;
            if (difference < 2000) { // Less than 2 sec
                count++;
            } else if (difference > 20000) { // Over 20 sec
                count = 1;
            }
            if (count >= 10) {
                //System.out.print("�Զ��Ͽ�����A2");
                BlockedIP.add(address);
                tracker.remove(address); // Cleanup
                ctx.channel().close();
                return;
            }
        }
        tracker.put(address, new Pair<>(System.currentTimeMillis(), count));
        String IP = address.substring(address.indexOf('/') + 1, address.length());
        if (channel > -1) {
            if (ChannelServer.getInstance(channel).isShutdown()) {
                //System.out.print("�Զ��Ͽ�����B");
                ctx.channel().close();
                return;
            }
            if (!LoginServer.containsIPAuth(IP)) {
//                System.out.print("�Զ��Ͽ�����C");
//                session.close();
//                return;
            }
        } else if (channel == -10) {
            if (CashShopServer.isShutdown()) {
                //System.out.print("�Զ��Ͽ�����D");
                ctx.channel().close();
                return;
            }
        } else if (LoginServer.isShutdown()) {
            //System.out.print("�Զ��Ͽ�����E");
            ctx.channel().close();
            return;
        }
        if (MapleParty.����������� == 0) {
            ctx.channel().close();
            return;
        }
        final byte serverRecv[] = new byte[]{70, 114, 12, (byte) Randomizer.nextInt(255)};
        final byte serverSend[] = new byte[]{82, 48, 120, (byte) Randomizer.nextInt(255)};
        final byte ivRecv[] = ServerConstants.Use_Fixed_IV ? new byte[]{9, 0, 0x5, 0x5F} : serverRecv;
        final byte ivSend[] = ServerConstants.Use_Fixed_IV ? new byte[]{1, 0x5F, 4, 0x3F} : serverSend;

        final MapleClient client = new MapleClient(new MapleAESOFB(ivSend, (short) (0xFFFF - ���ͻ��˰汾1)), new MapleAESOFB(ivRecv, ���ͻ��˰汾1), ctx.channel());
        client.setChannel(channel);

        MaplePacketDecoder.DecoderState decoderState = new MaplePacketDecoder.DecoderState();
        ctx.channel().attr(MaplePacketDecoder.DECODER_STATE_KEY).set(decoderState);
        ctx.writeAndFlush(LoginPacket.getHello(���ͻ��˰汾1, ServerConstants.Use_Fixed_IV ? serverSend : ivSend, ServerConstants.Use_Fixed_IV ? serverRecv : ivRecv));
        ctx.channel().attr(MapleClient.CLIENT_KEY).set(client);
        //��������,������
        //ctx.channel().attr(IdleState.READER_IDLE).set(60);
        //д�����
        //ctx.channel().attr(WRITER_IDLE).set(60);
        //Ƶ��
        //System.out.println(channel);
        //��½�˺ŵĽ���
        if (channel == -1 && channel != -10) {
            //��ʾ��ӭ����
            if (gui.Start.ConfigValuesMap.get("��ӭ��������") <= 0) {
                ctx.channel().write(MaplePacketCreator.serverNotice(1, "��ӭ (*^__^*) ����\r\n\r\n< " + MapleParty.�������� + " >"));
            }
            //���IP��ַ
            System.out.println("[�����]" + CurrentReadable_Time() + " : IP��ַ��" + ctx.channel().remoteAddress().toString() + "�����ʷ����� ��");
        }
        World.Client.addClient(client);
    }

    @Override
    public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
        if (MapleParty.����3 > 0) {
            System.out.println("sessionClosed");
        }
       MapleClient client = (MapleClient) ctx.channel().attr(MapleClient.CLIENT_KEY).get();

        if (client != null) {
            try {
//                FileWriter fw = isLoggedIP(session);
//                if (fw != null) {
//                    fw.write("=== Session Closed ===");
//                    fw.write(nl);
//                    fw.flush();
//                }
                client.disconnect(true, channel == -10);
            } finally {
                World.Client.removeClient(client);
                ctx.close();
                ctx.channel().attr(MapleClient.CLIENT_KEY).remove();
            }
        }
        super.channelInactive(ctx);
    }

    /**
     * ���ָ��
     * @param ctx
     * @param message
     */
    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object message) {
        try {

            final LittleEndianAccessor slea = new LittleEndianAccessor(new ByteArrayByteStream((byte[]) message));
            if (slea.available() < 2) {
                return;
            }

            final short header_num = slea.readShort();
            // Console output part
            for (final RecvPacketOpcode recv : RecvPacketOpcode.values()) {
                if (recv.getValue() == header_num) {
                    if (debugMode && !RecvPacketOpcode.isSpamHeader(recv)) {
                        final StringBuilder sb = new StringBuilder("�������� - �Ѵ���: " + String.valueOf(recv) + "\n");
                        //sb.append(tools.HexTool.toString((byte[]) message)).append("\n").append(tools.HexTool.toStringFromAscii((byte[]) message));
                        System.out.println("b" + sb.toString());
                    }
                    final MapleClient c = (MapleClient) ctx.channel().attr(MapleClient.CLIENT_KEY).get();
                    if (!c.isReceiving()) {
                        return;
                    }
                    if (recv.NeedsChecking()) {
                        if (!c.isLoggedIn()) {
                            return;
                        }
                    }
                    if (c.getPlayer() != null && c.isMonitored()) {
                        if (!blocked.contains(recv)) {
                            FileoutputUtil.log("log\\Monitored\\" + c.getPlayer().getName() + ".log", String.valueOf(recv) + " (" + Integer.toHexString(header_num) + ") Handled: \r\n" + slea.toString() + "\r\n");
                        }
                    }
                    if (Log_Packets) {
                        log(slea, recv, c, ctx.channel());
                    }
//                    System.out.println("recv��" + recv);
//                    System.out.println("slea��" + slea);
//                    System.out.println("c��" + c);
//                    System.out.println("cs��" + cs);
                    handlePacket(recv, slea, c, channel == -10);

                    //Log after the packet is handle. You'll see why =]
//                    FileWriter fw = isLoggedIP(session);
//                    if (fw != null && !blocked.contains(recv)) {
//                        if (recv == RecvPacketOpcode.PLAYER_LOGGEDIN && c != null) { // << This is why. Win.
//                            fw.write(">> [AccountName: "
//                                    + (c.getAccountName() == null ? "null" : c.getAccountName())
//                                    + "] | [IGN: "
//                                    + (c.getPlayer() == null || c.getPlayer().getName() == null ? "null" : c.getPlayer().getName())
//                                    + "] | [Time: "
//                                    + FileoutputUtil.CurrentReadable_Time()
//                                    + "]");
//                            fw.write(nl);
//                        }
//                        fw.write("[" + recv.toString() + "]" + slea.toString(true));
//                        fw.write(nl);
//                        fw.flush();
//                    }
                    return;
                }
            }
            if (debugMode) {
                final StringBuilder sb = new StringBuilder("�ѽ������� - δ̎��: ");
                sb.append(tools.HexTool.toString((byte[]) message)).append("\n").append(tools.HexTool.toStringFromAscii((byte[]) message));
                System.out.println("r" + sb.toString());
            }
        } catch (RejectedExecutionException ex) {
        } catch (Exception e) {
            FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, e);
            e.printStackTrace();
        }

    }

    @Override
    public void userEventTriggered(final ChannelHandlerContext ctx, final Object status) throws Exception {
        if (MapleParty.����3 > 0) {
            System.out.println("sessionIdle");
        }
        MapleClient client = (MapleClient) ctx.channel().attr(MapleClient.CLIENT_KEY).get();
        /**
         * <��������>
         */
        if (client != null) {
            client.sendPing();
        } else {
            ctx.channel().close();
            return;
        }
        super.userEventTriggered(ctx, status);
    }

    /**
     * ��ʵ��ҵ���߼�����Ҳ�����
     * @param header
     * @param slea
     * @param c
     * @param cs
     * @throws Exception
     */
    public static void handlePacket(final RecvPacketOpcode header, final LittleEndianAccessor slea, final MapleClient c, final boolean cs) throws Exception {

        //System.out.println(header);
        /*System.out.println(slea);*/
        //System.out.println(header);
        switch (header) {
            case PONG:
                c.pongReceived();
                if (MapleParty.����3 > 0) {
                    System.out.println("PONG");
                }
                break;
            case STRANGE_DATA:
                if (MapleParty.����3 > 0) {
                    System.out.println("������STRANGE_DATA");
                }
                break;
            case EFFECT_ON_OFF:
            case NEW_SX:
                if (MapleParty.����3 > 0) {
                    System.out.println("������NEW_SX��EFFECT_ON_OFF");
                }
                break;
            case HELLO_LOGIN://����Ϸ��½ע��
                CharLoginHandler.Welcome(c);
                //����ûʲô������HackShield������
                if (MapleParty.����3 > 0) {
                    System.out.println("HELLO_LOGIN");
                }
                break;
            //����Ϸ��½ע��
            case HELLO_CHANNEL:
                CharLoginHandler.Welcome(c);
                if (MapleParty.����3 > 0) {
                    System.out.println("HELLO_CHANNEL");
                }
                break;
            //��½�˺�
            case LOGIN_PASSWORD:
                /*try {
                    Thread.sleep(1000L);
                } catch (InterruptedException ex) {
                    System.out.println("�߳�������ʧ��");
                }*/
                CharLoginHandler.login(slea, c);

                if (MapleParty.����3 > 0) {
                    System.out.println("��½�˺�");
                }
                break;
            //��ʾƵ��
            case SERVERLIST_REQUEST:
                CharLoginHandler.ServerListRequest(c);
                if (MapleParty.����3 > 0) {
                    System.out.println("SERVERLIST_REQUEST");
                }
                break;
            //��ʾƵ��
            case LICENSE_REQUEST:
                CharLoginHandler.ServerListRequest(c);
                if (MapleParty.����3 > 0) {
                    System.out.println("LICENSE_REQUEST");
                }
                break;
            //����Ƶ��
            case CHARLIST_REQUEST:
                CharLoginHandler.CharlistRequest(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("����Ƶ��");
                }
                break;
            //���Ƶ��
            case SERVERSTATUS_REQUEST:
                CharLoginHandler.ServerStatusRequest(c);
                if (MapleParty.����3 > 0) {
                    System.out.println("���Ƶ��");
                }
                break;
            //��ɫ���֣�
            case CHECK_CHAR_NAME:
                CharLoginHandler.CheckCharName(slea.readMapleAsciiString(), c);
                if (MapleParty.����3 > 0) {
                    System.out.println("������ɫ�ɹ�");
                }
                break;
            //������ɫ
            case CREATE_CHAR:
                CharLoginHandler.CreateChar(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("������ɫ");
                }
                break;
            //ɾ����ɫ
            case DELETE_CHAR:
                //CharLoginHandler.DeleteChar(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("ɾ����ɫ");
                }
                break;
            //ѡ��Ƶ��������Ϸ
            case CHAR_SELECT:
                CharLoginHandler.Character_WithoutSecondPassword(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("ѡ��Ƶ��������Ϸ");
                }
                break;
            //�ڶ�_ _����auth����½��
            case AUTH_SECOND_PASSWORD:
                CharLoginHandler.Character_WithSecondPassword(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("AUTH_SECOND_PASSWORD");
                }
                break;
            //��ĳ�ַ�ʽ���������⣿������
            case RSA_KEY:
                c.sendPacket(LoginPacket.StrangeDATA());
                if (MapleParty.����3 > 0) {
                    System.out.println("RSA_KEY");
                }
                break;
            // END OF LOGIN SERVER
            //��Ϸ���л�Ƶ��
            case CHANGE_CHANNEL://Ƶ��
                InterServerHandler.ChangeChannel(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("�л�Ƶ��");
                }
                break;
            case SET_GENDER://ѡ��Ƶ��
                CharLoginHandler.SetGenderRequest(slea, c);
                /* if ( server.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    System.out.println("ע�᣿");
                }*/
                break;
            //����Ƶ��������
            case PLAYER_LOGGEDIN://������Ϸ������
                final int playerid = slea.readInt();
                if (cs) {
                    //�̳Ƿ�����
                    CashShopOperation.EnterCS(playerid, c);
                } else {
                    //��Ϸ������
                    InterServerHandler.Loggedin(playerid, c);
                }
                if (MapleParty.����3 > 0) {
                    System.out.println("����Ƶ��������");
                }
                break;
            //������Ϸ��ִ��`��ȡ/�洢��ɫ��Ϣ
            case PLAYER_UPDATE:
                PlayerHandler.UpdateHandler(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("��ȡ/�洢��ɫ��Ϣ");
                }
                break;
            //��ɫ�ƶ�
            case MOVE_PLAYER:
                PlayerHandler.MovePlayer(slea, c, c.getPlayer());
                /* if ( server.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    System.out.println("��ɫ�ƶ�����");
                }*/
                break;
            //�����ɫ
            case CHAR_INFO_REQUEST:
                c.getPlayer().updateTick(slea.readInt());
                PlayerHandler.CharInfoRequest(slea.readInt(), c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("�����ɫ");
                }

                break;
            //��ɫ����
            case CLOSE_RANGE_ATTACK:
                PlayerHandler.closeRangeAttack(slea, c, c.getPlayer(), false);
                if (MapleParty.����3 > 0) {
                    System.out.println("��ɫ����");
                }
                break;
            //Զ�̹���
            case RANGED_ATTACK:
                PlayerHandler.rangedAttack(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("Զ�̹���");
                }
                break;
            //���๥��
            case PASSIVE_ENERGY:
                PlayerHandler.closeRangeAttack(slea, c, c.getPlayer(), true);
                if (MapleParty.����3 > 0) {
                    System.out.println("���๥��?");
                }
                break;
            //ʹ�ù�������
            case MAGIC_ATTACK:
                PlayerHandler.MagicDamage(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("ʹ�ù�������");
                }
                break;
            //���⶯��,ʹ��BUFF����
            case SPECIAL_MOVE:
                PlayerHandler.SpecialMove(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("ʹ��BUFF����");
                }
                break;
            //��ұ���
            case FACE_EXPRESSION://
                PlayerHandler.ChangeEmotion(slea.readInt(), c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("��ұ���");
                }
                break;
            //��ɫ�ܵ��˺�
            case TAKE_DAMAGE:
                PlayerHandler.TakeDamage(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("��ɫ�ܵ��˺�");
                }
                break;
            //��ɫ�ָ�
            case HEAL_OVER_TIME:

                PlayerHandler.Heal(slea, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("��ɫ�ָ�");
                }
                break;
            //ȡ��BUFF
            case CANCEL_BUFF:
                PlayerHandler.CancelBuffHandler(slea.readInt(), c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("������Ͻ�ȡ��BUFF");
                }
                break;
            //��Ʒ������
            case CANCEL_ITEM_EFFECT:
                PlayerHandler.CancelItemEffect(slea.readInt(), c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("CANCEL_ITEM_EFFECT");
                }
                break;
            //������
            case USE_CHAIR:
                PlayerHandler.UseChair(slea.readInt(), c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("������");
                }
                break;
            //������
            case CANCEL_CHAIR:
                PlayerHandler.CancelChair(slea.readShort(), c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("������");
                }
                break;
            //ԭ�ظ�����
            case USE_ITEMEFFECT:
            case WHEEL_OF_FORTUNE:
                PlayerHandler.UseItemEffect(slea.readInt(), c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("ԭ�ظ�����1��ԭ�ظ�����2");
                }
                break;
            //�򿪼���
            case SKILL_EFFECT:
                PlayerHandler.SkillEffect(slea, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("SKILL_EFFECT");//
                }
                break;
            //�����
            case MESO_DROP:
                c.getPlayer().updateTick(slea.readInt());
                PlayerHandler.DropMeso(slea.readInt(), c.getPlayer());
                /* if ( server.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    System.out.println("�����");
                }*/
                break;
            //������ͼ��
            case MONSTER_BOOK_COVER:
                PlayerHandler.ChangeMonsterBookCover(slea.readInt(), c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("������ͼ��");
                }
                break;
            //������̿�ݼ�
            case CHANGE_KEYMAP:
                PlayerHandler.ChangeKeymap(slea, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("������̿�ݼ�");
                }
                break;
            //�˳��̳�
            case CHANGE_MAP:
                if (cs) {
                    if (ServerConstants.����ģʽ) {
                        System.out.println("�˳��̳�");
                    }
                    CashShopOperation.LeaveCS(slea, c, c.getPlayer());
                } else {
                    PlayerHandler.ChangeMap(slea, c, c.getPlayer());
                }
                /* if(����=="��"){
                     System.out.println("�˳��̳�");
                }*/
                break;
            //��ͼ���͵����
            case CHANGE_MAP_SPECIAL:
                slea.skip(1);
                PlayerHandler.ChangeMapSpecial(slea, slea.readMapleAsciiString(), c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("��ͼ���͵����");
                }
                break;
            //���͵�
            case USE_INNER_PORTAL:
                slea.skip(1);
                PlayerHandler.InnerPortal(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("���͵�");
                }
                break;
            //˲��ʯͷ����ӵ�ͼ
            case TROCK_ADD_MAP:
                PlayerHandler.TrockAddMap(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("TROCK_ADD_MAP");
                }
                break;
            //ս������
            case ARAN_COMBO:
                PlayerHandler.AranCombo(c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("ARAN_COMBO");
                }
                break;
            //���ܺ�
            case SKILL_MACRO:
                PlayerHandler.ChangeSkillMacro(slea, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("���ܺ�");
                }
                break;
            //����ʹ����������
            case ITEM_BAOWU:
                InventoryHandler.UsePenguinBox(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("ʹ����������");
                }
                break;
            //���ӱ�����
            case ITEM_SUNZI:
                InventoryHandler.SunziBF(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("ITEM_SUNZI");
                }
                break;
            //��������
            case GIVE_FAME:
                PlayersHandler.GiveFame(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("����/��������");
                }
                break;
            //�ı�������
            case TRANSFORM_PLAYER:
                PlayersHandler.TransformPlayer(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("�ı�������");
                }
                break;
            //��Ʒ��ע�ͣ�
            case NOTE_ACTION:
                PlayersHandler.Note(slea, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("NOTE_ACTION");
                }
                break;
            //ʹ��_�Ŵ����ţ�
            case USE_DOOR:
                PlayersHandler.UseDoor(slea, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("USE_DOOR");
                }
                break;
            //������Ӧ��
            case DAMAGE_REACTOR:
                PlayersHandler.HitReactor(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("������Ӧ��");
                }
                break;
            //��Ӧ����Ϣ��
            case TOUCH_REACTOR:
                PlayersHandler.TouchReactor(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("TOUCH_REACTOR");
                }
                break;
            //..????????????
            case CLOSE_CHALKBOARD:
                c.getPlayer().setChalkboard(null);
                if (MapleParty.����3 > 0) {
                    System.out.println("CLOSE_CHALKBOARD");
                }
                break;
            //��Ŀ������
            case ITEM_MAKER:
                //ItemMakerHandler.ItemMaker(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("ITEM_MAKER");
                }
                break;
            //������������
            case ITEM_SORT:
                InventoryHandler.ItemSort(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("������������");
                }
                break;
            //��ɫ5110000�ɿ�������
            case ITEM_GATHER:
                InventoryHandler.ItemGather(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("�ɿ�������");
                }
                break;
            //��ɫ����/ʹ����Ʒ
            case ITEM_MOVE:
                InventoryHandler.ItemMove(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("��ɫ����/ʹ����Ʒ");
                }
                break;
            //��ɫ������Ʒ
            case ITEM_PICKUP://
                InventoryHandler.Pickup_Player(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("��ɫ������Ʒ");
                }
                break;
            //�㲥������Ʒ����������
            case USE_CASH_ITEM:
                InventoryHandler.UseCashItem(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("������");
                }
                break;
            //���ĵ���ȯ��
            case quest_KJ:
                InventoryHandler.QuestKJ(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("quest_KJ");
                }
                break;
            //ʹ������Ʒ
            case USE_ITEM:
                InventoryHandler.UseItem(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("ʹ������Ʒ");
                }
                break;
            //�Ŵ󾵣���������
            case USE_MAGNIFY_GLASS:
                InventoryHandler.UseMagnify(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("�Ŵ�");
                }
                break;
            //��һ��������Ʒ��ʹ��?
            case USE_SCRIPTED_NPC_ITEM:
                InventoryHandler.UseScriptedNPCItem(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("USE_SCRIPTED_NPC_ITEM");
                }
                break;
            //ʹ��ʲô���������ǲ����� ����ѩ����Ϣ��
            case USE_RETURN_SCROLL:
                InventoryHandler.UseReturnScroll(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("USE_RETURN_SCROLL");
                }
                break;
            //����֮�꼼�ܣ�
            case USE_UPGRADE_SCROLL:
                c.getPlayer().updateTick(slea.readInt());
                InventoryHandler.UseUpgradeScroll((byte) slea.readShort(), (byte) slea.readShort(), (byte) slea.readShort(), c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("����֮�꼼��1");
                }
                break;
            //����֮�꼼�ܣ�
            case USE_POTENTIAL_SCROLL:
                c.getPlayer().updateTick(slea.readInt());
                InventoryHandler.UseUpgradeScroll((byte) slea.readShort(), (byte) slea.readShort(), (byte) 0, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("����֮�꼼��2");
                }
                break;
            //����֮�꼼�ܣ�
            case USE_EQUIP_SCROLL:
                c.getPlayer().updateTick(slea.readInt());
                InventoryHandler.UseUpgradeScroll((byte) slea.readShort(), (byte) slea.readShort(), (byte) 0, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("����֮�꼼��3");
                }
                break;
            //ʹ���ٻ���
            case USE_SUMMON_BAG:
                InventoryHandler.UseSummonBag(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("USE_SUMMON_BAG");
                }
                break;
            //��������
            case USE_TREASUER_CHEST:
                InventoryHandler.UseTreasureChest(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("������");
                }
                break;
            case ITEM_MZD:
                InventoryHandler.UseTreasureChest(slea, c, c.getPlayer());
                break;
            //ʹ�ü����飿
            case USE_SKILL_BOOK:
//                c.getPlayer().updateTick(slea.readInt());
                InventoryHandler.UseSkillBook(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("ʹ�ü�����");
                }
                break;
            //�Թ���ʹ�õĵ�����Ʒ
            case USE_CATCH_ITEM:
                InventoryHandler.UseCatchItem(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("�Թ���ʹ�õĵ�����Ʒ");
                }
                break;
            //�ָ�����ƣ�Ͳ�ҩ���ԣ�
            case USE_MOUNT_FOOD:
                InventoryHandler.UseMountFood(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("������Ʒ��");
                }
                break;
            //��������
            case HYPNOTIZE_DMG:
                MobHandler.HypnotizeDmg(slea, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("HYPNOTIZE_DMG");
                }
                break;
            //���ͣ���
            case MOB_NODE:
                MobHandler.MobNode(slea, c.getPlayer());
                break;
            //��ʾ�ڵ�
            case DISPLAY_NODE:
                MobHandler.DisplayNode(slea, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("DISPLAY_NODE");
                }
                break;
            //�����ƶ�
            case MOVE_LIFE:
                MobHandler.MoveMonster(slea, c, c.getPlayer());
                /* if ( server.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    System.out.println("��ɫ��������");
                }*/
                break;
            //�����ƶ��߳�
            case AUTO_AGGRO:
                MobHandler.AutoAggro(slea.readInt(), c.getPlayer());
                /*if (server.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    System.out.println("�����ƶ��߳�");
                }*/
                break;
            //�Ѱ��˺���
            case FRIENDLY_DAMAGE:
                MobHandler.FriendlyDamage(slea, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("FRIENDLY_DAMAGE");
                }
                break;
            //��ɱ���
            case MONSTER_BOMB:
                MobHandler.MonsterBomb(slea.readInt(), c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("MONSTER_BOMB");
                }
                break;
            //NPC�̵�
            case NPC_SHOP:
                NPCHandler.NPCShop(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("NPC_SHOP");
                }
                break;
            //��ʼ�Ի�NPC
            case NPC_TALK:
                NPCHandler.NPCTalk(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("��ʼ�Ի�NPC");
                }
                break;
            //�����Ի�NPC
            case NPC_TALK_MORE:
                NPCHandler.NPCMoreTalk(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("�����Ի�NPC");
                }
                break;
            //������ͣ��жϵ�ͼ�ʹ���
            case MARRAGE_RECV:
                NPCHandler.MarrageNpc(c);
                if (MapleParty.����3 > 0) {
                    System.out.println("MARRAGE_RECV");
                }
                break;
            //�����Ի�
            case NPC_ACTION:
                NPCHandler.NPCAnimation(slea, c);
                /*if(����=="��"){
                     System.out.println("�����Ի�");
                }*/
                break;
            //����
            case QUEST_ACTION:
                NPCHandler.QuestAction(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("����1");
                }
                break;
            //��Ϸ�ֿ�
            case STORAGE:
                NPCHandler.Storage(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("��Ϸ�ֿ�");
                }
                break;
            //˵��?
            case GENERAL_CHAT:
//                c.getPlayer().updateTick(slea.readInt());
                ChatHandler.GeneralChat(slea.readMapleAsciiString(), slea.readByte(), c, c.getPlayer());
                break;
            //˵��?
            case PARTYCHAT:
                ChatHandler.Others(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("PARTYCHAT");
                }
                break;
            case PARTY_SS://����
                ChatHandler.PARTY_SS(slea, c, c.getPlayer());
                //System.out.println("PARTY_SS");
                break;
            //˵�����ͣ�
            case WHISPER:
                ChatHandler.Whisper_Find(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("WHISPER");
                }
                break;
            //��Ҷ��ʹ��
            case MESSENGER:
                ChatHandler.Messenger(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("MESSENGER");
                }
                break;
            //�Զ�����������
            case AUTO_ASSIGN_AP://
                StatsHandling.AutoAssignAP(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("�Զ�����������");
                }
                break;
            //��������
            case DISTRIBUTE_AP:
                StatsHandling.DistributeAP(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("ʹ��������");
                }
                break;
            //�Ӽ���
            case DISTRIBUTE_SP:
                c.getPlayer().updateTick(slea.readInt());
                StatsHandling.DistributeSP(slea.readInt(), c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("ʹ�ü��ܵ�");
                }
                break;
            //��ҽ���ʲô��Ʒ��С��Ϸ����Ӷ��
            case PLAYER_INTERACTION:
                PlayerInteractionHandler.PlayerInteraction(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("��С��Ϸ��");
                }
                break;
            //�򿪼���
            case GUILD_OPERATION:
                GuildHandler.Guild(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("GUILD_OPERATION");
                }
                break;
            //������
            case UPDATE_CHAR_INFO:
                //System.err.println("UPDATE_CHAR_INFO");
                //PlayersHandler.UpdateCharInfo(slea.readAsciiString(), c);
                PlayersHandler.UpdateCharInfo(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("UPDATE_CHAR_INFO");
                }
                break;
            //�������֣�
            case DENY_GUILD_REQUEST:
                slea.skip(1);
                GuildHandler.DenyGuildRequest(slea.readMapleAsciiString(), c);
                if (MapleParty.����3 > 0) {
                    System.out.println("DENY_GUILD_REQUEST");
                }
                break;
            //�������
            case ALLIANCE_OPERATION:
                AllianceHandler.HandleAlliance(slea, c, false);
                if (MapleParty.����3 > 0) {
                    System.out.println("ALLIANCE_OPERATION");
                }
                break;
            //�������
            case DENY_ALLIANCE_REQUEST:
                AllianceHandler.HandleAlliance(slea, c, true);
                if (MapleParty.����3 > 0) {
                    System.out.println("DENY_ALLIANCE_REQUEST");
                }
                break;
            //����ʲô��ְλ��
            case BBS_OPERATION:
                BBSHandler.BBSOperatopn(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("BBS_OPERATION");
                }
                break;
            //�������
            case PARTY_OPERATION:
                PartyHandler.PartyOperatopn(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("����/��ɢ���");
                }
                break;
            //�������
            case DENY_PARTY_REQUEST:
                PartyHandler.DenyPartyRequest(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("DENY_PARTY_REQUEST");
                }
                break;
            //Ԥ�����
            case BUDDYLIST_MODIFY:
                BuddyListHandler.BuddyOperation(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("BUDDYLIST_MODIFY");
                }
                break;
            //��ʿ�ŵ�����
            case CYGNUS_SUMMON:
                UserInterfaceHandler.CygnusSummon_NPCRequest(c);
                if (MapleParty.����3 > 0) {
                    System.out.println("CYGNUS_SUMMON");
                }
                break;
            //����ϵͳ
            case SHIP_OBJECT:
                UserInterfaceHandler.ShipObjectRequest(slea.readInt(), c);
                if (MapleParty.����3 > 0) {
                    System.out.println("SHIP_OBJECT");
                }
                break;
            //�̳ǹ���
            case BUY_CS_ITEM:
                CashShopOperation.BuyCashItem(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("�̳ǹ�����Ʒ");
                }
                break;
            //???????????
            case TOUCHING_CS:
                CashShopOperation.TouchingCashShop(c);
                if (MapleParty.����3 > 0) {
                    System.out.println("TOUCHING_CS");
                }
                break;
            //???????????
            case COUPON_CODE:
                FileoutputUtil.log(FileoutputUtil.PacketEx_Log, "Coupon : \n" + slea.toString(true));
                System.out.println("33" + slea.toString());
                slea.skip(2);
                CashShopOperation.CouponCode(slea.readMapleAsciiString(), c);
                if (MapleParty.����3 > 0) {
                    System.out.println("COUPON_CODE");
                }
                break;
            //???????????
            case CS_UPDATE:
                CashShopOperation.CSUpdate(c);
                if (MapleParty.����3 > 0) {
                    System.out.println("CS_UPDATE");
                }
                break;
            //???????????
            case TOUCHING_MTS:
                MTSOperation.MTSUpdate(MTSStorage.getInstance().getCart(c.getPlayer().getId()), c);
                if (MapleParty.����3 > 0) {
                    System.out.println("TOUCHING_MTS");
                }
                break;
            //???????????
            case MTS_TAB:
                MTSOperation.MTSOperation(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("MTS_TAB");
                }
                break;
            //????????
            case DAMAGE_SUMMON:
                //   slea.skip(4);
                SummonHandler.DamageSummon(slea, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("DAMAGE_SUMMON");
                }
                break;
            //���
            case MOVE_SUMMON:
                SummonHandler.MoveSummon(slea, c.getPlayer());
//                if (MapleParty.����3 > 0) {
//                    System.out.println("MOVE_SUMMON");
//                }
                break;
            //�ٻ���
            case SUMMON_ATTACK:
                SummonHandler.SummonAttack(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("SUMMON_ATTACK");
                }
                break;
            //�˶���
            case MOVE_DRAGON:
                SummonHandler.MoveDragon(slea, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("MOVE_DRAGON");
                }
                break;
            //�������ʰȡ�����б�
            case PET_EXCEPTIONLIST:
                PetHandler.PickExceptionList(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("PET_EXCEPTIONLIST");
                }
                break;
            //�ٻ�����
            case SPAWN_PET:
                PetHandler.SpawnPet(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("SPAWN_PET");
                }
                break;
            //���＼��
            case MOVE_PET:
                PetHandler.MovePet(slea, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("MOVE_PET");
                }
                break;
            //��������
            case PET_CHAT:
                if (slea.available() < 12) {
                    break;
                }
                PetHandler.PetChat((int) slea.readLong(), slea.readShort(), slea.readMapleAsciiString(), c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("PET_CHAT");
                }
                break;
            //��������
            case PET_COMMAND:
                PetHandler.PetCommand(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("PET_COMMAND");
                }
                break;
            //�������
            case PET_FOOD:
                PetHandler.PetFood(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("PET_FOOD");
                }
                break;
            //���������Ʒ
            case PET_LOOT:
                InventoryHandler.Pickup_Pet(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("PET_LOOT");
                }
                break;
            //���糺죿
            case PET_AUTO_POT:
                PetHandler.Pet_AutoPotion(slea, c, c.getPlayer());
                if (MapleParty.����3 > 0) {
                    System.out.println("PET_AUTO_POT");
                }
                break;
            //���껪
            case MONSTER_CARNIVAL:
                MonsterCarnivalHandler.MonsterCarnival(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("MONSTER_CARNIVAL");
                }
                break;
            case DUEY_ACTION:
                DueyHandler.DueyOperation(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("DUEY_ACTION");
                }
                break;
            //������Ӷ
            case USE_HIRED_MERCHANT:
                HiredMerchantHandler.UseHiredMerchant(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("USE_HIRED_MERCHANT");
                }
                break;
            //��Ӷ��ص���
            case MERCH_ITEM_STORE:
                HiredMerchantHandler.MerchantItemStore(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("MERCH_ITEM_STORE");
                }
                break;
            //��ʲô����
            case CANCEL_DEBUFF:
                // Ignore for now
                if (MapleParty.����3 > 0) {
                    System.out.println("CANCEL_DEBUFF");
                }
                break;
            //ѩ����
            case LEFT_KNOCK_BACK:
                PlayerHandler.leftKnockBack(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("LEFT_KNOCK_BACK");
                }
                break;
            //ʲô��û�У�
            case SNOWBALL:
                PlayerHandler.snowBall(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("SNOWBALL");
                }
                break;
            //���찴ť
            case ChatRoom_SYSTEM:
                //PlayersHandler.ChatRoomHandler(slea, c);
                PlayersHandler.����(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("������");
                }
                break;
            //������ť
            case ENTER_MTS:
                PlayersHandler.����(slea, c);
                //InterServerHandler.EnterMTS(c, c.getPlayer(), true);
                if (MapleParty.����3 > 0) {
                    System.out.println("�������");
                }
                break;
            case LIE_DETECTOR:
                PlayersHandler.LieDetector(slea, c, c.getPlayer(), true);
                System.out.println("LIE_DETECTOR");
                break;
            case LIE_DETECTOR_RESPONSE:
                PlayersHandler.LieDetectorResponse(slea, c);
                System.out.println("LIE_DETECTOR_RESPONSE");
                break;
            case LIE_DETECTOR_REFRESH:
                PlayersHandler.LieDetectorRefresh(slea, c);
                System.out.println("LIE_DETECTOR_REFRESH");
                break;
            //�̳ǰ�ť
            case ENTER_CASH_SHOP://
                slea.readInt();
                PlayersHandler.�̳�(slea, c);
                break;
            //��Ҭ�ӣ�ƿ��
            case COCONUT:
                PlayersHandler.hitCoconut(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("COCONUT");
                }
                break;
            //��������ľ��Ĺ��ܣ�������ľ��Ͳ�ִ�У�
            case REPAIR:
                NPCHandler.repair(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("REPAIR");
                }
                break;
            //��������ľ��Ĺ��ܣ�������ľ��Ͳ�ִ�У�
            case REPAIR_ALL:
                NPCHandler.repairAll(c);
                if (MapleParty.����3 > 0) {
                    System.out.println("REPAIR_ALL");
                }
                break;
            //˵�˾�лл�㣿�ʹ���ϵͳһ���
            case GAME_POLL:
                UserInterfaceHandler.InGame_Poll(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("GAME_POLL");
                }
                break;
            //�̵�������
            case OWL:
                InventoryHandler.Owl(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("OWL");
                }
                break;
            //��ȥ��Ӷ
            case OWL_WARP:
                InventoryHandler.OwlWarp(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("OWL_WARP");
                }
                break;
            case USE_OWL_MINERVA:
                InventoryHandler.OwlMinerva(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("USE_OWL_MINERVA");
                }
                break;
            //RPG��Ϸ��С��Ϸ����
            case RPS_GAME:
                NPCHandler.RPSGame(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("RPS_GAME");
                }
                break;
            //������Ʒ��
            case QUEST_ITEM:
                NPCHandler.QuestItemUI(slea, c);
                break;
            case UPDATE_QUEST:
                NPCHandler.UpdateQuest(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("UPDATE_QUEST");
                }
                break;
            //������Ʒ����������Ϳ��ĩ
            case USE_ITEM_QUEST:
                NPCHandler.UseItemQuest(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("USE_ITEM_QUEST");
                }
                break;
            //��ҶԿ����
            case FOLLOW_REQUEST:
                PlayersHandler.FollowRequest(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("FOLLOW_REQUEST");
                }
                break;
            //��ҶԿ����
            case FOLLOW_REPLY:
                PlayersHandler.FollowReply(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("FOLLOW_REPLY");
                }
                break;
            //�������
            case RING_ACTION:
                PlayersHandler.RingAction(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("RING_ACTION");
                }
                break;
            //��У��
            case REQUEST_FAMILY:
                FamilyHandler.RequestFamily(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("��У��");
                }
                break;
            //��ѧԺ
            case OPEN_FAMILY:
                FamilyHandler.OpenFamily(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("��ѧԺ");
                }
                break;
            //ѧԺ����
            case FAMILY_OPERATION:
                FamilyHandler.FamilyOperation(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("FAMILY_OPERATION");
                }
                break;
            //�߳�����ɢ����
            case DELETE_JUNIOR:
                FamilyHandler.DeleteJunior(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("DELETE_JUNIOR");
                }
                break;
            //�˳�����
            case DELETE_SENIOR:
                FamilyHandler.DeleteSenior(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("DELETE_SENIOR");
                }
                break;
            //ѧԺ����
            case USE_FAMILY:
                FamilyHandler.UseFamily(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("USE_FAMILY");
                }
                break;
            //��ѧԺ
            case FAMILY_PRECEPT:
                FamilyHandler.FamilyPrecept(slea, c);
                if (MapleParty.����3 > 0) {
                    System.out.println("��ѧԺ");
                }
                break;
            //ѧԺ���˹���
            case FAMILY_SUMMON:
                FamilyHandler.FamilySummon(slea, c);
                break;
            //��ѧԺ
            case ACCEPT_FAMILY:
                FamilyHandler.AcceptFamily(slea, c);
                break;
            // ������
            case BEANS_GAME1:
                //BeanGame.BeanGame1(slea, c);
                InventoryHandler.TestDouDou(slea, c);
                //c.sendPacket(MaplePacketCreator.serverNotice(1, "��������δ�޸����ǳ���Ǹ��"));
                break;
            // ������
            case BEANS_GAME2:
                //InventoryHandler.TestDouDou(slea, c);
                //BeanGame.BeanGame2(slea, c);
                break;
            case MOONRABBIT_HP://����
                PlayerHandler.Rabbit(slea, c);
                break;
            //���
            case MAPLETV://���
                ChatHandler.MAPLETV(slea, c, c.getPlayer());
                break;

            default:
                FileoutputUtil.logToZev("�������Ϣ�ļ�/����˱�����Ϣ/" + CurrentReadable_Date() + "/handling.txt", "[δ�������] �ͻ��˰� [" + header.toString() + "] ������\r\n");
                System.out.println("[δ�������] �ͻ��˰� [" + header.toString() + "] ������");
                break;
        }
    }
}
