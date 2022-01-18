/*
������Ϸ
 */
package handling.channel.handler;

import static a.�÷���ȫ.��ɫIDȡ��������;
import static abc.Game.��ȫϵͳ;
import client.*;
import database.DatabaseConnection;
import java.util.List;
import handling.cashshop.CashShopServer;
import handling.channel.ChannelServer;
import static handling.login.handler.CharLoginHandler.Gaincharacterz;
import static handling.login.handler.CharLoginHandler.Getcharacterz;
import handling.world.CharacterTransfer;
import handling.world.MapleMessenger;
import handling.world.MapleMessengerCharacter;
import handling.world.CharacterIdChannelPair;
import handling.world.MapleParty;
import handling.world.MaplePartyCharacter;
import handling.world.PartyOperation;
import handling.world.PlayerBuffStorage;
import handling.world.World;
import handling.world.guild.MapleGuild;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import static scripting.NPCConversationManager.�˺�ȡ��QQ;
import scripting.NPCScriptManager;
import static gui.QQMsgServer.sendMsg;
import server.ServerProperties;
import server.maps.FieldLimitType;
import tools.FileoutputUtil;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;
import tools.packet.FamilyPacket;
import tools.data.LittleEndianAccessor;

public class InterServerHandler {

    public static final void EnterCS(final MapleClient c, final MapleCharacter chr, final boolean mts) {
        try {
            //�����־.����Ƶ����½��ʾ��("�����̳ǣ�");
            //�����־.show();
            if (c.getPlayer().getBuffedValue(MapleBuffStat.SUMMON) != null) {
                c.getPlayer().cancelEffectFromBuffStat(MapleBuffStat.SUMMON);
            }
            c.getPlayer().saveToDB(false, false);
            String[] socket = c.getChannelServer().getIP().split(":");
            final ChannelServer ch = ChannelServer.getInstance(c.getChannel());
            chr.changeRemoval();
            if (chr.getMessenger() != null) {
                MapleMessengerCharacter messengerplayer = new MapleMessengerCharacter(chr);
                World.Messenger.leaveMessenger(chr.getMessenger().getId(), messengerplayer);
            }
            PlayerBuffStorage.addBuffsToStorage(chr.getId(), chr.getAllBuffs());
            PlayerBuffStorage.addCooldownsToStorage(chr.getId(), chr.getCooldowns());
            PlayerBuffStorage.addDiseaseToStorage(chr.getId(), chr.getAllDiseases());
            World.ChannelChange_Data(new CharacterTransfer(chr), chr.getId(), mts ? -20 : -10);//
            ch.removePlayer(chr);
            c.updateLoginState(MapleClient.CHANGE_CHANNEL, c.getSessionIPAddress());
            c.sendPacket(MaplePacketCreator.getChannelChange(InetAddress.getByName(socket[0]), Integer.parseInt(CashShopServer.getIP().split(":")[1])));
            //chr.saveToDB(false, false);
            chr.getMap().removePlayer(chr);
            c.getPlayer().expirationTask(true, false);
            c.setPlayer(null);
            c.setReceiving(false);
        } catch (UnknownHostException ex) {
            Logger.getLogger(InterServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static final void EnterMTS(final MapleClient c, final MapleCharacter chr, final boolean mts) {
//        if (!chr.isAlive() || chr.getEventInstance() != null || c.getChannelServer() == null) {
        String[] socket = c.getChannelServer().getIP().split(":");
        if (c.getPlayer().getTrade() != null) {
            c.getPlayer().dropMessage(1, "�������޷���������������");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (chr.isGM() == false || chr.isGM() == true) {
            //NPCScriptManager.getInstance().start(c, 9900004, 9900005);//����npc
            c.sendPacket(MaplePacketCreator.enableActions());
            // c.sendPacket(MaplePacketCreator.serverBlocked(2));
            // c.sendPacket(MaplePacketCreator.enableActions());
            // return;
        } else {
            try {
                //if (c.getChannel() == 1 && !c.getPlayer().isGM()) {
                //    c.getPlayer().dropMessage(5, "�㲻�ܽ������Ƶ���������Ƶ������һ��.");
                //    c.sendPacket(MaplePacketCreator.enableActions());
                //    return;
                //}
                final ChannelServer ch = ChannelServer.getInstance(c.getChannel());
                chr.changeRemoval();
                if (chr.getMessenger() != null) {
                    MapleMessengerCharacter messengerplayer = new MapleMessengerCharacter(chr);
                    World.Messenger.leaveMessenger(chr.getMessenger().getId(), messengerplayer);
                }
                PlayerBuffStorage.addBuffsToStorage(chr.getId(), chr.getAllBuffs());
                PlayerBuffStorage.addCooldownsToStorage(chr.getId(), chr.getCooldowns());
                PlayerBuffStorage.addDiseaseToStorage(chr.getId(), chr.getAllDiseases());
                World.ChannelChange_Data(new CharacterTransfer(chr), chr.getId(), mts ? -20 : -10);
                ch.removePlayer(chr);
                c.updateLoginState(MapleClient.CHANGE_CHANNEL, c.getSessionIPAddress());
                c.sendPacket(MaplePacketCreator.getChannelChange(InetAddress.getByName(socket[0]), Integer.parseInt(CashShopServer.getIP().split(":")[1])));
                //chr.saveToDB(false, false);
                chr.getMap().removePlayer(chr);
                c.setPlayer(null);
                c.setReceiving(false);
            } catch (UnknownHostException ex) {
                Logger.getLogger(InterServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //private static ����Ƶ����½��ʾ�� �����־ = new ����Ƶ����½��ʾ��();
    public static void Loggedin(final int playerid, final MapleClient c) {//���ӷ�����
        if (c == null) {
            return;
        }
        final ChannelServer channelServer = c.getChannelServer();
        MapleCharacter player;
        final CharacterTransfer transfer = channelServer.getPlayerStorage().getPendingCharacter(playerid);
        if (transfer == null) { // Player isn't in storage, probably isn't CC
            player = MapleCharacter.loadCharFromDB(playerid, c, true);
        } else {
            player = MapleCharacter.ReconstructChr(transfer, c, true);
        }
        c.setPlayer(player);
        //��½��֤��
        if (gui.Start.ConfigValuesMap.get("��½��֤����") <= 0) {
            //�˺�IDȡ�˺�(player.getAccountID()
            c.getPlayer().Gaincharacterz("" + �˺�IDȡ�˺�(player.getAccountID()) + "", 100, 10);
            c.sendPacket(MaplePacketCreator.serverNotice(1, "" + ��ȫϵͳ + "���� 10 ���ڶԻ����˷���[*abc]��\r\n�Ե�һ�¾ͻ������Ϸ��\r\n����ᱻǿ�ƶϿ����ӡ�"));
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 10);
                        if (c != null) {
                            if (c.getPlayer().Getcharacterz("" + �˺�IDȡ�˺�(��ɫIDȡ�˺�ID(c.getPlayer().getId())) + "", 100) == 0) {
                                InterServerHandler.Loggedin2(playerid, c);
                            } else {
                                c.getPlayer().getClient().getSession().close();
                            }
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        } else {
            InterServerHandler.Loggedin2(playerid, c);
        }
    }

    public static void Loggedin2(final int playerid, final MapleClient c) {//���ӷ�����

        final ChannelServer channelServer = c.getChannelServer();
        MapleCharacter player;
        final CharacterTransfer transfer = channelServer.getPlayerStorage().getPendingCharacter(playerid);
        if (transfer == null) { // Player isn't in storage, probably isn't CC
            player = MapleCharacter.loadCharFromDB(playerid, c, true);
        } else {
            player = MapleCharacter.ReconstructChr(transfer, c, true);
        }
        c.setPlayer(player);
        c.setAccID(player.getAccountID());
        c.loadAccountData(player.getAccountID());
        ChannelServer.forceRemovePlayerByAccId(c, c.getAccID());
        c.updateLoginState(MapleClient.LOGIN_LOGGEDIN, c.getSessionIPAddress());
        //���Ϊɶ���ε���
        //c.updateLoginState(MapleClient.LOGIN_SERVER_TRANSITION, c.getSessionIPAddress());
        channelServer.addPlayer(player);//��ʾ���Ϲ�������
        c.sendPacket(MaplePacketCreator.getCharInfo(player));

        int �������� = gui.Start.ConfigValuesMap.get("����������");
        if (�������� <= 0) {//��������
            if (player.isGM()) {
                SkillFactory.getSkill(9001004).getEffect(1).applyTo(player);
            }
        }
        int ������� = gui.Start.ConfigValuesMap.get("������ٿ���");
        if (������� <= 0) {//�������
            if (player.isGM()) {
                SkillFactory.getSkill(9001001).getEffect(1).applyTo(player);
            }
        }
        c.sendPacket(MaplePacketCreator.temporaryStats_Reset());
        player.getMap().addPlayer(player);
        //�ٻ�����
        c.getPlayer().spawnSavedPets();
        try {
            player.silentGiveBuffs(PlayerBuffStorage.getBuffsFromStorage(player.getId()));
            player.giveCoolDowns(PlayerBuffStorage.getCooldownsFromStorage(player.getId()));
            player.giveSilentDebuff(PlayerBuffStorage.getDiseaseFromStorage(player.getId()));
            final Collection<Integer> buddyIds = player.getBuddylist().getBuddiesIds();
            World.Buddy.loggedOn(player.getName(), player.getId(), c.getChannel(), buddyIds, player.getGMLevel(), player.isHidden());
            if (player.getParty() != null) {
                //channelServer.getWorldInterface().updateParty(player.getParty().getId(), PartyOperation.LOG_ONOFF, new MaplePartyCharacter(player));
                World.Party.updateParty(player.getParty().getId(), PartyOperation.LOG_ONOFF, new MaplePartyCharacter(player));
            }
            final CharacterIdChannelPair[] onlineBuddies = World.Find.multiBuddyFind(player.getId(), buddyIds);
            for (CharacterIdChannelPair onlineBuddy : onlineBuddies) {
                final BuddyEntry ble = player.getBuddylist().get(onlineBuddy.getCharacterId());
                ble.setChannel(onlineBuddy.getChannel());
                player.getBuddylist().put(ble);
            }
            c.sendPacket(MaplePacketCreator.updateBuddylist(player.getBuddylist().getBuddies()));
            final MapleMessenger messenger = player.getMessenger();
            if (messenger != null) {
                World.Messenger.silentJoinMessenger(messenger.getId(), new MapleMessengerCharacter(c.getPlayer()));
                World.Messenger.updateMessenger(messenger.getId(), c.getPlayer().getName(), c.getChannel());
            }

            if (player.getGuildId() > 0) {
                World.Guild.setGuildMemberOnline(player.getMGC(), true, c.getChannel());
                c.sendPacket(MaplePacketCreator.showGuildInfo(player));
                final MapleGuild gs = World.Guild.getGuild(player.getGuildId());
                if (gs != null) {
                    final List<byte[]> packetList = World.Alliance.getAllianceInfo(gs.getAllianceId(), true);
                    if (packetList != null) {
                        for (byte[] pack : packetList) {
                            if (pack != null) {
                                c.sendPacket(pack);
                            }
                        }
                    }
                    //c.sendPacket(MaplePacketCreator.getGuildAlliance(gs.packetList()));//����
                }
            }
            if (player.getFamilyId() > 0) {
                World.Family.setFamilyMemberOnline(player.getMFC(), true, c.getChannel());
            }
            c.sendPacket(FamilyPacket.getFamilyInfo(player));
        } catch (Exception e) {
            FileoutputUtil.outputFileError(FileoutputUtil.Login_Error, e);
        }
        c.sendPacket(FamilyPacket.getFamilyData());
        //���ؼ��ܺ�
        player.sendMacros();
        //�����Ʒʱ��
        player.expirationTask();/////
        //��ʾС������Ϣ
        player.showNote();
        //�������HP
        player.updatePartyMemberHP();
        //��ʼ�����ɫ�����׹ʱ��
        //player.startFairySchedule(false);
        player.updatePetEquip();
        //�޸�3ת���Ͻ�ɫ����
        player.baseSkills(); //�޸�ʧȥ���ܵ���
        c.sendPacket(MaplePacketCreator.getKeymap(player.getKeyLayout()));

        for (MapleQuestStatus status : player.getStartedQuests()) {
            if (status.hasMobKills()) {
                c.sendPacket(MaplePacketCreator.updateQuestMobKills(status));
            }
        }

        final BuddyEntry pendingBuddyRequest = player.getBuddylist().pollPendingRequest();
        if (pendingBuddyRequest != null) {
            player.getBuddylist().put(new BuddyEntry(pendingBuddyRequest.getName(), pendingBuddyRequest.getCharacterId(), "ETC", -1, false, pendingBuddyRequest.getLevel(), pendingBuddyRequest.getJob()));
            c.sendPacket(MaplePacketCreator.requestBuddylistAdd(pendingBuddyRequest.getCharacterId(), pendingBuddyRequest.getName(), pendingBuddyRequest.getLevel(), pendingBuddyRequest.getJob()));
        }
        if (player.getJob() == 132) { // DARKKNIGHT
            player.checkBerserk();
        }
        //��ɫ��������
        if (gui.Start.ConfigValuesMap.get("�������ѿ���") <= 0) {
            if (!player.isGM()) {
                String ��� = c.getPlayer().getName();
                String ���� = ��ɫIDȡ��������(c.getPlayer().getId());
                if (!"".equals(����)) {
                    World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(11, c.getChannel(), "[ " + ��� + " ] : " + ����));
                }
            }
        }
        //�ٻ�����
        //player.spawnClones();
        // player.getHyPay(1);
        //player.spawnSavedPets();
        c.sendPacket(MaplePacketCreator.showCharCash(c.getPlayer()));
        if (player.getGMLevel() == 0) {
            String ��� = "[�����]" + CurrentReadable_Time() + " : ����(" + c.getChannel() + ")Ƶ�������: " + c.getPlayer().getName() + " ";
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:" + ���));
            //�����־.����Ƶ����½��ʾ��(���);
            System.err.println(���);
        } else {
            String ��� = "[�����]" + CurrentReadable_Time() + " : ����(" + c.getChannel() + ")Ƶ��������: " + c.getPlayer().getName() + " ";
            //�����־.����Ƶ����½��ʾ��(���);
            System.err.println(���);
        }

        /*if (gui.Start.ConfigValuesMap.get("������Ч����") <= 0) {
            c.sendPacket(MaplePacketCreator.showEffect("nightmare/wakeup"));//������Ч
            c.sendPacket(MaplePacketCreator.playSound("Party1/bb"));//������Ч
        }*/
        if (c.getPlayer().Getcharacterz("" + c.getPlayer().getId() + "", 2) <= 0) {
            sendMsg(" [��ɫ��½��Ϣ]:"
                    + "\r\n �˺�: " + c.getAccountName() + " "
                    + "\r\n ��ɫ: " + c.getPlayer().getName() + ""
                    + "\r\n ʱ��: " + CurrentReadable_Time() + " "
                    + "\r\n ��ַ: " + c.getSession().remoteAddress()+ ""
                    + "\r\n ����: " + MapleParty.�������� + "",
                    "" + �˺�ȡ��QQ(c.getAccountName()) + "");

        }

        int ˫��Ƶ�� = gui.Start.ConfigValuesMap.get("˫��Ƶ������");
        if (˫��Ƶ�� == 0) {
            if (c.getChannel() == Integer.parseInt(ServerProperties.getProperty("ZEV.Count")) && !c.getPlayer().haveItem(5220002, 1, false, false)) {
                c.getPlayer().dropMessage(5, "�Բ�����ȱ��������Ʒ���޷����� " + Integer.parseInt(ServerProperties.getProperty("ZEV.Count")) + " Ƶ����");
                c.getPlayer().changeChannel(1);
            }
        }
        c.sendPacket(MaplePacketCreator.weirdStatUpdate());

        if (gui.Start.ConfigValuesMap.get("��½��������") == 0) {
            if (player.getGMLevel() > 0 && player.getBossLog("����������ʾ") == 0) {
                player.dropMessage(5, "ָ��: [*ָ���ȫ] �鿴����Աָ��");
                player.dropMessage(5, "ָ��: [@����] �鿴���ָ�� / ˵[����֪����233]���������߲�������");
            } else if (player.getGMLevel() <= 0 && player.getBossLog("���������ʾ") == 0) {
                player.dropMessage(5, "ָ��: [@����] �鿴���ָ�� / ˵[��֪����233]���������߲�������");
            }
        }
        if (gui.Start.ConfigValuesMap.get("��½��������") == 0) {
            final String login = �˺�IDȡ�˺�(player.getAccountID());
            //���ӵ�½ֵ
            Gaincharacterz("" + login + "", 101, 1);
            //30��ĵ�½����
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 30);
                        Gaincharacterz("" + login + "", 101, -Getcharacterz("" + login + "", 101));
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
        if (gui.Start.ConfigValuesMap.get("��½���п���") == 0) {
            //������Ϸ��Ӷ������˳�
            final String login = �˺�IDȡ�˺�(player.getAccountID());
            Gaincharacterz("" + login + "", 102, -Getcharacterz("" + login + "", 102));
        }
        player.ˢ������װ����ħ��������();
        NPCScriptManager.getInstance().dispose(c);
        //�ط�
        if (c.getChannel() == 2) {
            if (c.getPlayer().Getcharacterz("" + c.getPlayer().getId() + "", 500) > 0) {
                NPCScriptManager.getInstance().dispose(c);
                NPCScriptManager.getInstance().start(c, 2030010, 1);
            } else if (c.getPlayer().Getcharacterz("" + c.getPlayer().getId() + "", 501) > 0) {
                NPCScriptManager.getInstance().dispose(c);
                NPCScriptManager.getInstance().start(c, 2083004, 10);
                //PB�ط�
            } else if (c.getPlayer().Getcharacterz("" + c.getPlayer().getId() + "", 502) > 0) {
                NPCScriptManager.getInstance().dispose(c);
                NPCScriptManager.getInstance().start(c, 2141001, 10);
            }
        }
        //player.�����߳�();
        if (gui.Start.ConfigValuesMap.get("����ְҵ����") == 0) {
            int ְҵ = player.getJob();
            int ְҵ2 = MapleParty.����ְҵ;
            if ((ְҵ == ְҵ2) || (ְҵ - ְҵ2 == 1) || (ְҵ2 - ְҵ == -1)) {
                player.dropMessage(5, "[����ְҵ] : ��ϲ�����˳�Ϊ����ְҵ������50%�������Ծ���");
            }
        }
        //player.dropMessage(5, "[�����׹] : " + player.getFairyExp());
        player.dropMessage(5, "[ƣ��ֵ] : " + 60 * gui.Start.ConfigValuesMap.get("ÿ��ƣ��ֵ") + " / " + (60 * gui.Start.ConfigValuesMap.get("ÿ��ƣ��ֵ") - player.�ж�ƣ��ֵ()) + "");
        //player.�����߳�();
        //������װЧ��(c);
        //�������������ڵ���Ʒ
//        player.removeAll(2070018);
//        player.removeAll(4001197);
    }

//    public static void ������װЧ��(MapleClient c) {
//        Equip ���� = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem((short) -11).copy();
//        Equip ñ�� = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem((short) -1).copy();
//        Equip ��װ = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem((short) -5).copy();
//        Equip ���� = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem((short) -8).copy();
//        Equip Ь�� = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem((short) -7).copy();
//        if (ñ��.getItemId() == 1002776 && ��װ.getItemId() == 1052155 && ����.getItemId() == 1082234 && Ь��.getItemId() == 1072355) {
//            if (սʿ����(����.getItemId())) {
//                c.getPlayer().dropMessage(5, "[սʿ]���㶷־ ��� + 100%");
//                 layer.�����������˺�=100;
//            }
//        }
//    }
    public static boolean սʿ����(int id) {
        switch (id) {
            //�����Ƽ׽�
            case 1302081:
            //������Ÿ�
            case 1312037:
            //���㾪����
            case 1322060:
            //������ڤ��
            case 1402046:
            //����������
            case 1412033:
            //����������
            case 1422037:
            //������ʥǹ
            case 1432047:
            //��������
            case 1442063:
                return true;
        }
        return false;
    }

    public static final void ChangeChannel(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (c.getPlayer().getTrade() != null || !chr.isAlive() || chr.getEventInstance() != null || chr.getMap() == null || FieldLimitType.ChannelSwitch.check(chr.getMap().getFieldLimit())) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        //������
        chr.expirationTask();
        chr.changeChannel(slea.readByte() + 1);
    }

    public static int ��ɫIDȡ�˺�ID(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters ");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    if (rs.getInt("id") == id) {
                        data = rs.getInt("accountid");
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("[��������]����ɫ����ȡ�˺�ID������");
        }
        return data;
    }

    public static String �˺�IDȡ�˺�(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("name");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("[��������]���˺�IDȡ�˺š�����");
        }
        return data;
    }

}
