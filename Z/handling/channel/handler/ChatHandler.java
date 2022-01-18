/*
���˵��������
 */
package handling.channel.handler;

import client.MapleClient;
import client.MapleCharacter;
import client.messages.CommandProcessor;
import constants.ServerConstants.CommandType;
import static gui.QQͨ��.Ⱥ֪ͨ;
import gui.���ѩ�����;
import handling.channel.ChannelServer;
import handling.world.MapleMessenger;
import handling.world.MapleMessengerCharacter;
import handling.world.MapleParty;
import handling.world.World;
import java.util.Calendar;
import java.util.concurrent.ScheduledFuture;
import server.Timer;
import server.custom.bossrank4.BossRankManager4;
import server.quest.MapleQuest;
import tools.FileoutputUtil;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;
import tools.data.LittleEndianAccessor;

public class ChatHandler {

    public static final void GeneralChat(final String text, final byte unk, final MapleClient c, final MapleCharacter chr) {
        if (chr != null) {

            try {
                boolean condition = CommandProcessor.processCommand(c, text, CommandType.���ָ��);
                if (condition) {
                    return;
                }
            } catch (Throwable e) {
                System.err.println(e);
            }
            if (gui.Start.ConfigValuesMap.get("������쿪��") > 0) {
                c.sendPacket(MaplePacketCreator.serverNotice(1, "����Ա�Ӻ�̨�ر������칦��"));
                return;
            }

            if (!chr.isGM() && text.length() >= 80) {
                return;
            }
            if (gui.Start.ConfigValuesMap.get("����ģʽ����") != null) {
                if (c.getPlayer().getLevel() >= 160) {
                    if ("��������ģʽ".equals(text)) {
                        chr.����ģʽ();
                    } else if ("�ر�����ģʽ".equals(text)) {
                        chr.�ر�����ģʽ();
                    }
                }
            } else {
                chr.dropMessage(5, "��ʱ�޷��鵽�������ģʽ���ݣ���ȴ��´θ���ά����");
            }
            int ʱ = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            int �� = Calendar.getInstance().get(Calendar.MINUTE);
            int ���� = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            if (MapleParty.����� == 0) {
                if (���� == 1 || ���� == 7) {
                    if (ʱ >= 10) {
                        if ("��Ҫ��ѩ����".equals(text) && chr.Getcharacterz("" + chr.getId() + "", 10) <= 0) {
                            MapleParty.ѩ����++;
                            chr.Gaincharacterz("" + chr.getId() + "", 10, 1);
                            chr.dropMessage(1, "[�ͶƱ] \r\nͶƱѩ�����ɹ�");
                            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[�����] : " + chr.getName() + " ��ʾ����ѩ������"));
                            Ⱥ֪ͨ("[�����] : " + chr.getName() + " ��ʾ����ѩ������");
                        }
                    }
                    if (MapleParty.ѩ���� >= gui.Start.ConfigValuesMap.get("��Ӧ��������")) {
                        ���ѩ�����.��ʼ��ѩ��();
                    }
                } else if (���� > 1 && ���� < 7) {
                    if (ʱ >= 17) {
                        if ("��Ҫ��ѩ����".equals(text) && chr.Getcharacterz("" + chr.getId() + "", 10) <= 0) {
                            MapleParty.ѩ����++;
                            chr.Gaincharacterz("" + chr.getId() + "", 10, 1);
                            chr.dropMessage(1, "[�ͶƱ] \r\nͶƱѩ�����ɹ�");
                            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[�����] : " + chr.getName() + " ��ʾ����ѩ������"));
                            Ⱥ֪ͨ("[�����] : " + chr.getName() + " ��ʾ����ѩ������");
                        }
                    }
                    if (MapleParty.ѩ���� >= gui.Start.ConfigValuesMap.get("��Ӧ��������")) {
                        ���ѩ�����.��ʼ��ѩ��();
                    }
                }
            }
            if (chr.getBossLog("���������ʾ") == 0) {
                if ("��֪����233".equals(text)) {
                    chr.setBossLog("���������ʾ");
                }
            }
            if (chr.getBossLog("����������ʾ") == 0) {
                if ("����֪����233".equals(text)) {
                    chr.setBossLog("����������ʾ");
                }
            }
            //�ʵ�
            if ("�Ұ�ð�յ�".equals(text)) {
                if (chr.getBossLog("�ʵ�2") == 0 && c.getPlayer().getMap().getCharactersSize() > 1) {
                    chr.modifyCSPoints(1, 50, true);
                    chr.modifyCSPoints(2, 50, true);
                    chr.setBossLog("�ʵ�2");
                    chr.dropMessage(1, "[С�ʵ�] : \r\n��ϲ�㴥���ʵ������ 50 ��ȯ/����ȯ��");
                }
            }
            //�ʵ�
            if ("���˷���".equals(text) && c.getPlayer().getMap().getCharactersSize() > 1) {
                if (chr.getBossLog("�ʵ�5") == 0) {
                    MapleParty.���˷���++;
                    chr.setBossLog("�ʵ�5");
                }
            }
            if ("why".equals(text) && c.getPlayer().getMap().getCharactersSize() > 1) {
                if (chr.getBossLog("�ʵ�5") == 1) {
                    MapleParty.���˷��� -= 1;
                    chr.setBossLog("�ʵ�5");
                    chr.modifyCSPoints(1, 66, true);
                    chr.modifyCSPoints(2, 66, true);
                    chr.dropMessage(1, "[С�ʵ�] : \r\n��ϲ�㴥���ʵ������ 66 ��ȯ/����ȯ��");
                }
            }
            if (chr.isHidden()) {
                chr.getMap().broadcastGMMessage(chr, MaplePacketCreator.getChatText(chr.getId(), text, c.getPlayer().isGM(), unk), true);
            } else {
                chr.getCheatTracker().checkMsg();
                chr.getMap().broadcastMessage(MaplePacketCreator.getChatText(chr.getId(), text, c.getPlayer().isGM(), unk), c.getPlayer().getPosition());
                if (gui.Start.ConfigValuesMap.get("�����¼����") <= 0) {
                    FileoutputUtil.logToFile("����˼�¼��Ϣ/��ҵ���" + c.getPlayer().getName() + "/�����¼.txt", "[ʱ��: " + CurrentReadable_Time() + " ]����: " + text + "\r\n");
                }
            }
            BossRankManager4.getInstance().setLog(chr.getId(), chr.getName(), "��߶����", (byte) 2, (byte) 1);
        }
    }

    public static final void Others(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        final int type = slea.readByte();
        final byte numRecipients = slea.readByte();
        int recipients[] = new int[numRecipients];

        for (byte i = 0; i < numRecipients; i++) {
            recipients[i] = slea.readInt();
        }
        final String chattext = slea.readMapleAsciiString();
        if (chr == null || !chr.getCanTalk()) {
            c.sendPacket(MaplePacketCreator.serverNotice(6, "���Ѿ������ԣ�����޷�˵��."));
            return;
        }
        if (CommandProcessor.processCommand(c, chattext, CommandType.���ָ��)) {
            return;
        }
        chr.getCheatTracker().checkMsg();
        switch (type) {
            case 0:
                World.Buddy.buddyChat(recipients, chr.getId(), chr.getName(), chattext);
                if (c.getPlayer().isAdmin() && gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    c.getPlayer().dropMessage(5, "handling.channel.handler��buddyChat��" + chattext);
                }
                break;
            //���˵��
            case 1:
                if (chr.getParty() == null) {
                    break;
                }
                World.Party.partyChat(chr.getParty().getId(), chattext, chr.getName());
                if (c.getPlayer().isAdmin() && gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    c.getPlayer().dropMessage(5, "handling.channel.handler��partyChat��1��" + chattext + "2��" + chr.getName());
                }
                break;
            case 2:
                if (chr.getGuildId() <= 0) {
                    break;
                }
                World.Guild.guildChat(chr.getGuildId(), chr.getName(), chr.getId(), chattext);
                if (c.getPlayer().isAdmin() && gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    c.getPlayer().dropMessage(5, "handling.channel.handler��guildChat��1��" + chr.getGuildId() + "2��" + chr.getName() + "3��" + chr.getId() + "4��" + chattext);
                }
                break;
            //����Ի�
            case 3:
                if (chr.getGuildId() <= 0) {
                    break;
                }
                World.Alliance.allianceChat(chr.getGuildId(), chr.getName(), chr.getId(), chattext);
                if (c.getPlayer().isAdmin() && gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    c.getPlayer().dropMessage(5, "handling.channel.handler��allianceChat��1��" + chr.getGuildId() + "2��" + chr.getName() + "3��" + chr.getId() + "4��" + chattext);
                }
                break;
        }
    }

    public static final void Messenger(final LittleEndianAccessor slea, final MapleClient c) {
        String input;
        MapleMessenger messenger = c.getPlayer().getMessenger();

        switch (slea.readByte()) {
            case 0x00: // open
                if (c.getPlayer().isAdmin() && gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    c.getPlayer().dropMessage(5, "handling.channel.handler��Messenger��-0x00");
                }
                if (messenger == null) {
                    int messengerid = slea.readInt();
                    if (messengerid == 0) { // create
                        c.getPlayer().setMessenger(World.Messenger.createMessenger(new MapleMessengerCharacter(c.getPlayer())));
                    } else { // join
                        messenger = World.Messenger.getMessenger(messengerid);
                        if (messenger != null) {
                            final int position = messenger.getLowestPosition();
                            if (position > -1 && position < 4) {
                                c.getPlayer().setMessenger(messenger);
                                World.Messenger.joinMessenger(messenger.getId(), new MapleMessengerCharacter(c.getPlayer()), c.getPlayer().getName(), c.getChannel());
                            }
                        }
                    }
                }
                break;
            case 0x02: // exit
                if (c.getPlayer().isAdmin() && gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    c.getPlayer().dropMessage(5, "handling.channel.handler��Messenger��-0x02");
                }
                if (messenger != null) {
                    final MapleMessengerCharacter messengerplayer = new MapleMessengerCharacter(c.getPlayer());
                    World.Messenger.leaveMessenger(messenger.getId(), messengerplayer);
                    c.getPlayer().setMessenger(null);
                }
                break;
            case 0x03: // invite
                if (c.getPlayer().isAdmin() && gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    c.getPlayer().dropMessage(5, "handling.channel.handler��Messenger��-0x03");
                }
                if (messenger != null) {
                    final int position = messenger.getLowestPosition();
                    if (position <= -1 || position >= 4) {
                        return;
                    }
                    input = slea.readMapleAsciiString();
                    final MapleCharacter target = c.getChannelServer().getPlayerStorage().getCharacterByName(input);

                    if (target != null) {
                        if (target.getMessenger() == null) {
                            if (!target.isGM() || c.getPlayer().isGM()) {
                                c.sendPacket(MaplePacketCreator.messengerNote(input, 4, 1));
                                target.getClient().sendPacket(MaplePacketCreator.messengerInvite(c.getPlayer().getName(), messenger.getId()));
                            } else {
                                c.sendPacket(MaplePacketCreator.messengerNote(input, 4, 0));
                            }
                        } else {
                            c.sendPacket(MaplePacketCreator.messengerChat(c.getPlayer().getName() + " : " + target.getName() + "�Ѿ���ʹ�÷�Ҷ��ʹ."));
                        }
                    } else if (World.isConnected(input)) {
                        World.Messenger.messengerInvite(c.getPlayer().getName(), messenger.getId(), input, c.getChannel(), c.getPlayer().isGM());
                    } else {
                        c.sendPacket(MaplePacketCreator.messengerNote(input, 4, 0));
                    }
                }
                break;
            case 0x05: // decline
                if (c.getPlayer().isAdmin() && gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    c.getPlayer().dropMessage(5, "handling.channel.handler��Messenger��-0x05");
                }
                final String targeted = slea.readMapleAsciiString();
                final MapleCharacter target = c.getChannelServer().getPlayerStorage().getCharacterByName(targeted);
                if (target != null) { // This channel
                    if (target.getMessenger() != null) {
                        target.getClient().sendPacket(MaplePacketCreator.messengerNote(c.getPlayer().getName(), 5, 0));
                    }
                } else // Other channel
                 if (!c.getPlayer().isGM()) {
                        World.Messenger.declineChat(targeted, c.getPlayer().getName());
                    }
                break;
            case 0x06: // message
                if (c.getPlayer().isAdmin() && gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    c.getPlayer().dropMessage(5, "handling.channel.handler��Messenger��-0x06");
                }
                if (messenger != null) {
                    World.Messenger.messengerChat(messenger.getId(), slea.readMapleAsciiString(), c.getPlayer().getName());

                }
                break;
        }
    }

    public static final void Whisper_Find(final LittleEndianAccessor slea, final MapleClient c) {
        final byte mode = slea.readByte();
//        slea.readInt(); //ticks
        switch (mode) {
            case 68: //buddy
            case 5: { // Find
                if (c.getPlayer().isAdmin() && gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    c.getPlayer().dropMessage(5, "handling.channel.handler��Whisper_Find��-5/68");
                }
                if (gui.Start.ConfigValuesMap.get("��Ϸ���˿���") > 0) {
                    c.getPlayer().dropMessage(5, "���˹��ܱ��ر�");
                    return;
                }
                final String recipient = slea.readMapleAsciiString();
                MapleCharacter player = c.getChannelServer().getPlayerStorage().getCharacterByName(recipient);
                if (player != null) {
                    if (!player.isGM() || c.getPlayer().isGM() && player.isGM()) {

                        c.sendPacket(MaplePacketCreator.getFindReplyWithMap(player.getName(), player.getMap().getId(), mode == 68));
                    } else {
                        c.sendPacket(MaplePacketCreator.getWhisperReply(recipient, (byte) 0));
                    }
                } else { // Not found
                    int ch = World.Find.findChannel(recipient);
                    if (ch > 0) {
                        player = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(recipient);
                        if (player == null) {
                            break;
                        }
                        if (player != null) {
                            if (!player.isGM() || (c.getPlayer().isGM() && player.isGM())) {
                                c.sendPacket(MaplePacketCreator.getFindReply(recipient, (byte) ch, mode == 68));
                            } else {
                                c.sendPacket(MaplePacketCreator.getWhisperReply(recipient, (byte) 0));
                            }
                            return;
                        }
                    }
                    if (ch == -10) {
                        c.sendPacket(MaplePacketCreator.getFindReplyWithCS(recipient, mode == 68));
                    } else if (ch == -20) {
                        c.sendPacket(MaplePacketCreator.getFindReplyWithMTS(recipient, mode == 68));
                    } else {
                        c.sendPacket(MaplePacketCreator.getWhisperReply(recipient, (byte) 0));
                    }
                }
                break;
            }
            case 6: { // Whisper
                if (c.getPlayer().isAdmin() && gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                    c.getPlayer().dropMessage(5, "handling.channel.handler��Whisper_Find��-6");
                }
                if (!c.getPlayer().getCanTalk()) {
                    c.sendPacket(MaplePacketCreator.serverNotice(6, "���Ѿ������ԣ�����޷�˵��."));
                    return;
                }
                c.getPlayer().getCheatTracker().checkMsg();
                final String recipient = slea.readMapleAsciiString();
                final String text = slea.readMapleAsciiString();
                final int ch = World.Find.findChannel(recipient);
                if (ch > 0) {
                    MapleCharacter player = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(recipient);
                    if (player == null) {
                        break;
                    }
                    player.getClient().sendPacket(MaplePacketCreator.getWhisper(c.getPlayer().getName(), c.getChannel(), text));
                    if (!c.getPlayer().isGM() && player.isGM()) {
                        c.sendPacket(MaplePacketCreator.getWhisperReply(recipient, (byte) 0));
                    } else {
                        c.sendPacket(MaplePacketCreator.getWhisperReply(recipient, (byte) 1));
                    }
                } else {
                    c.sendPacket(MaplePacketCreator.getWhisperReply(recipient, (byte) 0));
                }
            }
            break;
        }
    }

    /*
    2,0,0,0      ����
    -1��-1��-1��1 ȫְҵ
    -8,0,0,0    սʿȫѡ
    0,31,0,0    ħ��ʦȫѡ
    0��-32,1,0  ����ȫѡ
    0,0,30,0    ����ȫѡ
    0,0��-32,1  ������ȫѡ
    ******************************�ж�1
    2������
    4��ս��
    16����ʿ
    32����ʿ
    64������ʿ
    -128������ʿ
    ��ȫѡ����-8
    ******************************�ж�2
    16������ʿ
    8����ʦ
    4��������ʦ
    2������ʦ
    ��ȫѡ����31
    �ж�3
     */
    private static ScheduledFuture<?> ��Ա�����߳� = null;
    public static boolean ������� = false;
    public static int �������� = 0;
    public static int ���� = 0;

    public static void �ر������߳�() {
        if (��Ա�����߳� != null) {
            ��Ա�����߳�.cancel(true);
            ��Ա�����߳� = null;
            �������� = 0;
            ���� = 0;
        }
    }

    public static final void PARTY_SS(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        //c.getPlayer().dropMessage(1, "������Ӳ���");slea.toString(true)
        final int ��͵ȼ� = slea.readByte();
        slea.skip(3);
        final int ��ߵȼ� = slea.readByte();
        slea.skip(3);
        final int �������� = slea.readByte();
        slea.skip(3);
        final int A = slea.readByte();
        final int B = slea.readByte();
        final int C = slea.readByte();
        final int D = slea.readByte();
        int ȫְҵ�ж� = A + B + C + D;
        if (ȫְҵ�ж� != -2) {
            c.getPlayer().dropMessage(1, "�빴ѡ��ȫְҵ���ڿ�ʼ������");
            return;
        }
        if (��Ա�����߳� != null) {
            c.getPlayer().dropMessage(1, "���������С�");
            return;
        }
        ��Ա�����߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                ��������++;
                ������� = false;
                c.getPlayer().dropMessage(6, "[Ѱ�����]:���ڵ� " + �������� + " ��Ѱ�� " + chr.getClient().getChannel() + " Ƶ���ڵȼ��� " + ��͵ȼ� + " - " + ��ߵȼ� + " ��������û����ӵ���ҡ�");
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 8);
                            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                                for (MapleCharacter party : cserv1.getPlayerStorage().getAllCharacters()) {
                                    if (chr.getClient().getChannel() == party.getClient().getChannel()) {
                                        if (party.getParty() == null) {
                                            if (party.getLevel() >= ��͵ȼ� && party.getLevel() <= ��ߵȼ�) {
                                                MapleCharacter invited = c.getChannelServer().getPlayerStorage().getCharacterByName(party.getName());
                                                invited.getClient().sendPacket(MaplePacketCreator.partyInvite(c.getPlayer()));
                                                c.getPlayer().dropMessage(5, "[Ѱ�����]:�ҵ���� ( " + party.getName() + " ) ���ڵ�ͼ ( " + party.getMap().getMapName().toString() + " )");
                                                ������� = true;
                                                ����++;
                                            }
                                        }
                                    }
                                }
                            }
                            if (������� == true) {
                                c.getPlayer().dropMessage(1, "��������ҵ� " + ���� + " �����ϵȼ�������ң������Ѿ�����/���Ƿ�����������롣");
                                �ر������߳�();
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
            }
        }, 10 * 1000);
    }

    public static final void MAPLETV(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        final int ��ֳ��Ʒ = slea.readByte();
        final int B = slea.readByte();
        final int C = slea.readByte();
        final int D = slea.readByte();
        final int F = slea.readByte();
        switch (��ֳ��Ʒ) {

            case -9://04220153
                MapleQuest.getInstance(21763).forceStart(c.getPlayer(), 0, null);
                break;
            case -71://����4220089
                MapleQuest.getInstance(8252).forceStart(c.getPlayer(), 0, null);
                break;
            case -35://�𼦵�4220125
                MapleQuest.getInstance(8252).forceStart(c.getPlayer(), 0, null);
                break;
            case -92://��� 4220068
                MapleQuest.getInstance(8588).forceStart(c.getPlayer(), 0, null);
                break;
            case -115:// 04220045
                MapleQuest.getInstance(7691).forceStart(c.getPlayer(), 0, null);
                break;
            case 117://������ 04220020
                MapleQuest.getInstance(1).forceStart(c.getPlayer(), 0, null);
                break;

            default:
                break;
        }
        c.getPlayer().dropMessage(6, "-------------------------------");
        c.getPlayer().dropMessage(6, "B  " + ��ֳ��Ʒ);
//        c.getPlayer().dropMessage(6, "C  " + C);
//        c.getPlayer().dropMessage(6, "D  " + D);
//        c.getPlayer().dropMessage(6, "F  " + F);
        c.getPlayer().dropMessage(6, "" + slea.toString(true));

    }

}
