/*
NPC�÷����ô�ȫ
 */
package scripting;

import static abc.Game.����;
import client.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import client.inventory.Equip;
import client.inventory.IItem;
import constants.GameConstants;
import client.inventory.ItemFlag;
import client.inventory.MapleInventory;
import client.inventory.MapleInventoryType;
import client.inventory.*;
//import static constants.GameConstants.��IP;
import server.MapleCarnivalParty;
import server.Randomizer;
import server.MapleInventoryManipulator;
import server.MapleShopFactory;
import server.MapleSquad;
import server.maps.MapleMap;
import server.maps.Event_DojoAgent;
import server.maps.AramiaFireWorks;
import server.quest.MapleQuest;
import tools.MaplePacketCreator;
import tools.Pair;
import tools.packet.PlayerShopPacket;
import server.MapleItemInformationProvider;
import handling.channel.ChannelServer;
import handling.channel.MapleGuildRanking;
import database.DatabaseConnection;
import static download.Toupdate.readInputStream;
import handling.cashshop.CashShopServer;
import handling.login.handler.AutoRegister;
import handling.world.CharacterTransfer;
import handling.world.MapleMessengerCharacter;
import handling.world.MapleParty;
import handling.world.MaplePartyCharacter;
import handling.world.PlayerBuffStorage;
import handling.world.World;
import handling.world.guild.MapleGuild;
import server.MapleCarnivalChallenge;
import java.util.HashMap;
import handling.world.guild.MapleGuildAlliance;
import java.awt.Point;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.Invocable;
import server.*;
import static server.MapleCarnivalChallenge.getJobNameById;
import static gui.QQMsgServer.sendMsg;
import static gui.QQMsgServer.sendMsgToQQGroup;
import gui.Start;
import static gui.Start.GetConfigValues;
import static gui.Start.GetFuMoInfo;
import static gui.Start.��ȡ��������Ϣ����;
import java.text.DecimalFormat;
import server.maps.SpeedRunType;
import server.Timer.CloneTimer;
import server.custom.bossrank8.BossRankInfo8;
import server.custom.bossrank8.BossRankManager8;
import server.custom.forum.Forum_Reply;
import server.custom.forum.Forum_Section;
import server.custom.forum.Forum_Thread;
import server.custom.rank.MiniGamePoints;
import server.custom.rank.RankManager;
import server.life.*;
import server.maps.*;
import tools.FileoutputUtil;
import static tools.FileoutputUtil.CurrentReadable_Date;
import static tools.FileoutputUtil.CurrentReadable_Time;
import static tools.MaplePacketCreator.showSpecialEffect;
import tools.StringUtil;
import tools.packet.UIPacket;

public class NPCConversationManager extends AbstractPlayerInteraction {

    private MapleClient c;
    private int npc, questid;
    private String getText;
    private byte type; // -1 = NPC, 0 = start quest, 1 = end quest
    private byte lastMsg = -1;
    public boolean pendingDisposal = false;
    private Invocable iv;
    private int wh = 0;

    /*
     * public NPCConversationManager(MapleClient c, int npc, int questid, byte
     * type, Invocable iv) { super(c); this.c = c; this.npc = npc; this.questid
     * = questid; this.type = type; this.iv = iv; }
     */
    public static int ����ȫ��������ʱ��() {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("todayOnlineTime") > 0) {
                    data += rs.getInt("todayOnlineTime");
                }
            }
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int ���ռ���������ʱ��(int a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("guildid") == a) {
                    if (rs.getInt("todayOnlineTime") > 0) {
                        data += rs.getInt("todayOnlineTime");
                    }
                }
            }
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int ��ɫIDȡGM(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT gm as DATA FROM characters WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ɫIDȡ�������ȡ�����");
        }
        return data;
    }

    public static String ��ɫIDȡ��������(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id2 as DATA FROM shangxianlaba WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ɫIDȡ�������ȡ�����");
        }
        return data;
    }

    public static void �޸�������������(int a, String b) {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM shangxianlaba");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String aa = null;
                aa = "update shangxianlaba set id2 = '" + b + "' where id = " + a + ";";
                PreparedStatement ab = DatabaseConnection.getConnection().prepareStatement(aa);
                ab.executeUpdate(aa);
                //}
            }
        } catch (SQLException ex) {
        }
    }

    public NPCConversationManager(MapleClient c, int npc, int questid, byte type, Invocable iv, int wh) {
        super(c);
        this.c = c;
        this.npc = npc;
        this.questid = questid;
        this.type = type;
        this.iv = iv;
        this.wh = wh;
    }

    public int getwh() {
        return this.wh;
    }

    public Invocable getIv() {
        return iv;
    }

    public String serverName() {//ȡ����������
        return c.getChannelServer().getServerName();
    }

    public int getNpc() {
        return npc;
    }

    public int getQuest() {
        return questid;
    }

    public byte getType() {
        return type;
    }

    public void safeDispose() {
        pendingDisposal = true;
    }

    public void dispose() {
        NPCScriptManager.getInstance().dispose(c);
    }

    public void �Ի�����() {
        NPCScriptManager.getInstance().dispose(c);
    }

    public void askMapSelection(final String sel) {
        if (lastMsg > -1) {
            return;
        }
        c.sendPacket(MaplePacketCreator.getMapSelection(npc, sel));
        lastMsg = 0xD;
    }

    public void sendNext(String text) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { //sendNext will dc otherwise!
            sendSimple(text);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 0, text, "00 01", (byte) 0));
        lastMsg = 0;
    }

    public void sendNextS(String text, byte type) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimpleS(text, type);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 0, text, "00 01", type));
        lastMsg = 0;
    }

    public void sendPrev(String text) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimple(text);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 0, text, "01 00", (byte) 0));
        lastMsg = 0;
    }

    public void sendPrevS(String text, byte type) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimpleS(text, type);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 0, text, "01 00", type));
        lastMsg = 0;
    }

    public void sendNextPrev(String text) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimple(text);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 0, text, "01 01", (byte) 0));
        lastMsg = 0;
    }

    public void PlayerToNpc(String text) {
        sendNextPrevS(text, (byte) 3);
    }

    public void sendNextPrevS(String text) {
        sendNextPrevS(text, (byte) 3);
    }

    public void sendNextPrevS(String text, byte type) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimpleS(text, type);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 0, text, "01 01", type));
        lastMsg = 0;
    }

    public void ˵������(String text) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimple(text);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 0, text, "00 00", (byte) 0));
        lastMsg = 0;
    }

    public void sendOk(String text) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimple(text);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 0, text, "00 00", (byte) 0));
        lastMsg = 0;
    }

    public void sendOkS(String text, byte type) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimpleS(text, type);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 0, text, "00 00", type));
        lastMsg = 0;
    }

    public void sendYesNo(String text) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimple(text);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 1, text, "", (byte) 0));
        lastMsg = 1;
    }

    public void �Ƿ�˵������(String text) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimple(text);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 1, text, "", (byte) 0));
        lastMsg = 1;
    }

    public void sendYesNoS(String text, byte type) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimpleS(text, type);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 1, text, "", type));
        lastMsg = 1;
    }

    public void sendAcceptDecline(String text) {
        askAcceptDecline(text);
    }

    public void sendAcceptDeclineNoESC(String text) {
        askAcceptDeclineNoESC(text);
    }

    public void askAcceptDecline(String text) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimple(text);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 0x0B, text, "", (byte) 0));
        lastMsg = 0xB;
    }

    public void askAcceptDeclineNoESC(String text) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimple(text);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 0x0C, text, "", (byte) 0));
        lastMsg = 0xC;
    }

    public void askAvatar(String text, int card, int... args) {
        if (lastMsg > -1) {
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalkStyle(npc, text, card, args));
        lastMsg = 7;
    }

    public void sendSimple(String text) {
        if (lastMsg > -1) {
            return;
        }
        if (!text.contains("#L")) { //sendSimple will dc otherwise!
            sendNext(text);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 4, text, "", (byte) 0));
        lastMsg = 4;
    }

    public void sendSimple(String text, int speaker) {
        if (lastMsg > -1) {
            return;
        }
        if (!text.contains("#L")) { //sendSimple will dc otherwise!
            sendNext(text);
            return;
        }
        getClient().sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 4, text, "", (byte) speaker));
        lastMsg = 4;
    }

    public void sendSimpleS(String text, byte type) {
        if (lastMsg > -1) {
            return;
        }
        if (!text.contains("#L")) { //sendSimple will dc otherwise!
            sendNextS(text, type);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(npc, (byte) 4, text, "", (byte) type));
        lastMsg = 4;
    }

    public void openWeb(String web) {//����ַ
        this.c.sendPacket(MaplePacketCreator.openWeb(web));
    }

    public void sendStyle(String text, int caid, int styles[]) {
        if (lastMsg > -1) {
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalkStyle(npc, text, caid, styles));
        lastMsg = 7;
    }

    public void sendGetNumber(String text, int def, int min, int max) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimple(text);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalkNum(npc, text, def, min, max));
        lastMsg = 3;
    }

    public void sendGetNumber2(String text, int def, int min, int max) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimple(text);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalkNum(npc, text, def, min, max));
        lastMsg = 3;
    }

    public void sendGetText(String text) {
        if (lastMsg > -1) {
            return;
        }
        if (text.contains("#L")) { // will dc otherwise!
            sendSimple(text);
            return;
        }
        c.sendPacket(MaplePacketCreator.getNPCTalkText(npc, text));
        lastMsg = 2;
    }

    public void setGetText(String text) {
        this.getText = text;
    }

    public String getText() {
        return getText;
    }

    public void setHair(int hair) {
        if (c.getPlayer().getMapId() == 100000104) {
            getPlayer().setHair(hair);
            getPlayer().updateSingleStat(MapleStat.HAIR, hair);//���÷���
            getPlayer().equipChanged();
        } else {
            c.getPlayer().dropMessage(1, "���ʹ���Ȩ�ѱ����ִ� ������ �����ˣ���������ȥ�������ŶӰɡ�");
        }
    }

    public void setFace(int face) {
        if (c.getPlayer().getMapId() == 100000103) {
            getPlayer().setFace(face);
            getPlayer().updateSingleStat(MapleStat.FACE, face);//��������
            getPlayer().equipChanged();
        } else {
            c.getPlayer().dropMessage(1, "���ݴ���Ȩ�ѱ����ִ� ���ҽ�� �����ˣ�������ȥ�������ŶӰɡ�");
        }
    }

    public void setSkin(int color) {
        getPlayer().setSkinColor((byte) color);
        getPlayer().updateSingleStat(MapleStat.SKIN, color);//���÷�ɫ
        getPlayer().equipChanged();
    }

    public int setRandomAvatar(int ticket, int[] args_all) {//���÷���ʱ
        if (!haveItem(ticket)) {
            return -1;
        }
        gainItem(ticket, (short) -1);
        int args = args_all[Randomizer.nextInt(args_all.length)];
        if (args < 100) {
            c.getPlayer().setSkinColor((byte) args);
            c.getPlayer().updateSingleStat(MapleStat.SKIN, args);
        } else if (args < 30000) {
            c.getPlayer().setFace(args);
            c.getPlayer().updateSingleStat(MapleStat.FACE, args);
        } else {
            c.getPlayer().setHair(args);
            c.getPlayer().updateSingleStat(MapleStat.HAIR, args);
        }
        c.getPlayer().equipChanged();

        return 1;
    }

    public int ���ɫ(int[] args_all) {//���÷���ʱ

        int args = args_all[Randomizer.nextInt(args_all.length)];
        if (args < 100) {
            c.getPlayer().setSkinColor((byte) args);
            c.getPlayer().updateSingleStat(MapleStat.SKIN, args);
        } else if (args < 30000) {
            c.getPlayer().setFace(args);
            c.getPlayer().updateSingleStat(MapleStat.FACE, args);
        } else {
            c.getPlayer().setHair(args);
            c.getPlayer().updateSingleStat(MapleStat.HAIR, args);
        }
        c.getPlayer().equipChanged();

        return 1;
    }

    public int setAvatar(int ticket, int args) {//���÷���
        if (!haveItem(ticket)) {
            return -1;
        }
        gainItem(ticket, (short) -1);

        if (args < 100) {
            c.getPlayer().setSkinColor((byte) args);
            c.getPlayer().updateSingleStat(MapleStat.SKIN, args);
        } else if (args < 30000) {
            c.getPlayer().setFace(args);
            c.getPlayer().updateSingleStat(MapleStat.FACE, args);
        } else {
            c.getPlayer().setHair(args);
            c.getPlayer().updateSingleStat(MapleStat.HAIR, args);
        }
        c.getPlayer().equipChanged();

        return 1;
    }

    public void sendStorage() {
        c.getPlayer().setConversation(4);
        c.getPlayer().getStorage().sendStorage(c, npc);
    }

    public void openShop(int id) {//���̵�
        MapleShopFactory.getInstance().getShop(id).sendShop(c);
    }

    public void ����������(int add) {
        if (Getsaiji("������������", 1) <= 0) {
            setBossRank8(getPlayer().getId(), getPlayer().getName(), "��������", (byte) 2, add);
            c.getPlayer().dropMessage(5, "�������� + " + add + "");
            //��λ�����ʾ
            /* if (getBossRank8("��������", (byte) 2) >= Getsaiji("���߷���", 1)) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.startMapEffect("��ϲ " + c.getPlayer().getName() + " ��Ծ�����ﵽ ���� ��λ��", 5120009);
                    }
                }
            } else if (getBossRank8("��������", (byte) 2) >= Getsaiji("��ʦ����", 1)) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.startMapEffect("��ϲ " + c.getPlayer().getName() + " ��Ծ�����ﵽ ��ʦ ��λ��", 5120009);
                    }
                }
            } else if (getBossRank8("��������", (byte) 2) >= Getsaiji("��ʯ����", 1)) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.startMapEffect("��ϲ " + c.getPlayer().getName() + " ��Ծ�����ﵽ ��ʯ ��λ��", 5120009);
                    }
                }
            } else if (getBossRank8("��������", (byte) 2) >= Getsaiji("�������", 1)) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.startMapEffect("��ϲ " + c.getPlayer().getName() + " ��Ծ�����ﵽ ���� ��λ��", 5120009);
                    }
                }
            } else if (getBossRank8("��������", (byte) 2) >= Getsaiji("�ƽ����", 1)) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.startMapEffect("��ϲ " + c.getPlayer().getName() + " ��Ծ�����ﵽ �ƽ� ��λ��", 5120009);
                    }
                }
            } else if (getBossRank8("��������", (byte) 2) >= Getsaiji("��������", 1)) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.startMapEffect("��ϲ " + c.getPlayer().getName() + " ��Ծ�����ﵽ ���� ��λ��", 5120009);
                    }
                }
            }*/
        }
    }

    @Override
    public int getBossRank8(int cid, String bossname, byte type) {
        int ret = -1;
        BossRankInfo8 info = BossRankManager8.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1://����
                ret = info.getPoints();
                break;
            case 2://����
                ret = info.getCount();
                break;
        }
        return ret;
    }

    public void ���̵�(int id) {//���̵�
        MapleShopFactory.getInstance().getShop(id).sendShop(c);
    }

    public final MapleInventory �жϱ���װ����() {//�жϱ���
        return c.getPlayer().getInventory(MapleInventoryType.getByType((byte) 1));
    }

    public final MapleInventory �жϱ���������() {//�жϱ���
        return c.getPlayer().getInventory(MapleInventoryType.getByType((byte) 2));
    }

    public final MapleInventory �жϱ���������() {//�жϱ���
        return c.getPlayer().getInventory(MapleInventoryType.getByType((byte) 3));
    }

    public final MapleInventory �жϱ���������() {//�жϱ���
        return c.getPlayer().getInventory(MapleInventoryType.getByType((byte) 4));
    }

    public final MapleInventory �жϱ���������() {//�жϱ���
        return c.getPlayer().getInventory(MapleInventoryType.getByType((byte) 5));
    }

//    public void �жϱ���װ����() {
//        c.getPlayer().getInventory(MapleInventoryType.EQUIP).getNextFreeSlot();
//    }
//
//    public void �жϱ���������() {
//        c.getPlayer().getInventory(MapleInventoryType.USE).getNextFreeSlot();
//    }
//
//    public void �жϱ���������() {
//        c.getPlayer().getInventory(MapleInventoryType.SETUP).getNextFreeSlot();
//    }
//
//    public void �жϱ���������() {
//        c.getPlayer().getInventory(MapleInventoryType.ETC).getNextFreeSlot();
//    }
//
//    public void �жϱ���������() {
//        c.getPlayer().getInventory(MapleInventoryType.CASH).getNextFreeSlot();
//    }
    public int gainGachaponItem(int id, int quantity) {//��Ʒ��
        return gainGachaponItem(id, quantity, c.getPlayer().getMap().getStreetName() + " - " + c.getPlayer().getMap().getMapName());
    }

    public int gainGachaponItem(int id, int quantity, final String msg) {//�ٱ���齱
        try {
            if (!MapleItemInformationProvider.getInstance().itemExists(id)) {
                return -1;
            }
            final IItem item = MapleInventoryManipulator.addbyId_Gachapon(c, id, (short) quantity);

            if (item == null) {
                return -1;
            }
            final byte rareness = GameConstants.gachaponRareItem(item.getItemId());
            if (rareness > 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.getGachaponMega("[" + msg + "] " + c.getPlayer().getName(), " : ��ϲ��õ���!", item, rareness, getPlayer().getClient().getChannel()));
            }
            return item.getItemId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int gainGachaponItem(int id, int quantity, final String msg, int ����) {//�ٱ���齱
        try {
            if (!MapleItemInformationProvider.getInstance().itemExists(id)) {
                return -1;
            }
            final IItem item = MapleInventoryManipulator.addbyId_Gachapon(c, id, (short) quantity);

            if (item == null) {
                return -1;
            }
            if (���� > 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.getGachaponMega("[ " + msg + " ] : �Ѿ������ [ " + c.getPlayer().getName(), " ] ���˳��У�", item, (byte) 0, getPlayer().getClient().getChannel()));
            }
            return item.getItemId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int gainGachaponItem2(int id, int quantity, final String msg, int ����) {//�ٱ���齱
        try {
            if (!MapleItemInformationProvider.getInstance().itemExists(id)) {
                return -1;
            }
            final IItem item = MapleInventoryManipulator.addbyId_Gachapon(c, id, (short) quantity);

            if (item == null) {
                return -1;
            }
            if (���� > 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.getGachaponMega("[ " + msg + " ] : �Ѿ������ [ " + c.getPlayer().getName(), " ] ���˳��У�", item, (byte) 0, getPlayer().getClient().getChannel()));
            }

            return item.getItemId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int gainGachaponItem3(int id, int quantity, final String msg, int ����) {//�ٱ���齱
        try {
            if (!MapleItemInformationProvider.getInstance().itemExists(id)) {
                return -1;
            }
            final IItem item = MapleInventoryManipulator.addbyId_Gachapon(c, id, (short) quantity);

            if (item == null) {
                return -1;
            }
            if (���� > 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.getGachaponMega("[ " + msg + " ] : �Ѿ������ [ " + c.getPlayer().getName(), " ] ���˳��У�", item, (byte) 0, getPlayer().getClient().getChannel()));
            }
            return item.getItemId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void changeJob(int job) {//ת��ְҵ
        c.getPlayer().changeJob(job);
    }

    public void startQuest(int id) {//����
        MapleQuest.getInstance(id).start(getPlayer(), npc);
    }

//    public void ��ʼ����(int id) {//����
//        MapleQuest.getInstance(id).start(getPlayer(), npc);
//    }
    public void �������(int id) {
        MapleQuest.getInstance(id).complete(getPlayer(), npc);
    }

    public void ��������(int id) {
        MapleQuest.getInstance(id).forfeit(getPlayer());
    }

    public void completeQuest(int id) {
        MapleQuest.getInstance(id).complete(getPlayer(), npc);
    }

    public void forfeitQuest(int id) {
        MapleQuest.getInstance(id).forfeit(getPlayer());
    }

    public void forceStartQuest() {
        MapleQuest.getInstance(questid).forceStart(getPlayer(), getNpc(), null);
    }

    public void forceStartQuest(int id) {
        MapleQuest.getInstance(id).forceStart(getPlayer(), getNpc(), null);
    }

    public void forceStartQuest(String customData) {
        MapleQuest.getInstance(questid).forceStart(getPlayer(), getNpc(), customData);
    }

    public void ����ʼ() {
        MapleQuest.getInstance(questid).forceStart(getPlayer(), getNpc(), null);
    }

    public void ����ʼ(int id) {
        MapleQuest.getInstance(id).forceStart(getPlayer(), getNpc(), null);
    }

    public void ��ʼ����(int id) {
        MapleQuest.getInstance(id).forceStart(getPlayer(), getNpc(), null);
    }

    public void ����ʼ(String customData) {
        MapleQuest.getInstance(questid).forceStart(getPlayer(), getNpc(), customData);
    }

    public void �������() {
        MapleQuest.getInstance(questid).forceComplete(getPlayer(), getNpc());
    }

    public void �������(final int id) {
        MapleQuest.getInstance(id).forceComplete(getPlayer(), getNpc());
    }

    public void �������(int id) {
        MapleQuest.getInstance(id).forfeit(getPlayer());
    }

    public void completeQuest() {
        forceCompleteQuest();
    }

    public void forceCompleteQuest() {
        MapleQuest.getInstance(questid).forceComplete(getPlayer(), getNpc());
    }

    public void forceCompleteQuest(final int id) {
        MapleQuest.getInstance(id).forceComplete(getPlayer(), getNpc());
    }

    public String getQuestCustomData() {
        return c.getPlayer().getQuestNAdd(MapleQuest.getInstance(questid)).getCustomData();
    }

    public void setQuestCustomData(String customData) {
        getPlayer().getQuestNAdd(MapleQuest.getInstance(questid)).setCustomData(customData);
    }

    public int getLevel() {//�жϵȼ�
        return getPlayer().getLevel();
    }

    public void ���õȼ�(int s) {//�жϵȼ�
        c.getPlayer().setLevel((short) s);
    }

    public int �жϵȼ�() {//�жϵȼ�
        return getPlayer().getLevel();
    }

    public void ˢ��() {//ˢ��
        MapleCharacter player = c.getPlayer();
        c.sendPacket(MaplePacketCreator.getCharInfo(player));
        player.getMap().removePlayer(player);
        player.getMap().addPlayer(player);
    }

    public int ��������(int s) {
        return setBossRankCount5("������", s);
    }

    public int �ڿ���() {//�жϵȼ�        
        return getBossRankCount5("������");
    }

    public int �жϽ��() {//�жϽ��
        return getPlayer().getMeso();
    }

    public int �жϽ�ɫID() {//�жϽ��
        return c.getPlayer().getId();
    }

    public int �жϵ�ȯ() {
        return c.getPlayer().getCSPoints(1);
    }

    public int �жϵ���ȯ() {
        return c.getPlayer().getCSPoints(2);
    }

    public int �ж�����() {
        return getPlayer().getCurrentRep();
    }

    public int �ж�ѧԺ() {
        return getPlayer().getFamilyId();
    }

    public int �ж�ʦ��() {
        return getPlayer().getSeniorId();
    }

    public void ������(int s) {
        c.getPlayer().setCurrentRep(s);
    }

    public void �������() {
        if (getParty() != null) {
            c.getPlayer().getParty().getMembers().size();
        }
    }

    public int �жϾ���() {
        return c.getPlayer().getExp();
    }

    public int �жϵ�ǰ��ͼ��������() {
        return c.getPlayer().getMap().getAllMonstersThreadsafe().size();
    }

    public int �ж�ָ����ͼ��������(int a) {
        return getMap(a).getAllMonstersThreadsafe().size();
    }

    public int �жϵ�ǰ��ͼ�������() {
        return c.getPlayer().getMap().getCharactersSize();
    }

    public int �����(int a) {
        return (int) Math.ceil(Math.random() * a);
    }

    public boolean �ٷ���(int q) {
        int a = (int) Math.ceil(Math.random() * 100);
        if (a <= q) {
            return true;
        } else {
            return false;
        }
    }

    public void ����Ŀ���ͼ(int a) {
        getMap(a).resetFully();
    }

    public void �����ͼ����(int a, int b) {
        MapleMonster mob;
        double range = Double.POSITIVE_INFINITY;
        MapleMap map = getMap(a);
        boolean drop = false;
        if (b == 0) {
            drop = true;
        } else {
            drop = false;
        }
        for (MapleMapObject monstermo : map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
            mob = (MapleMonster) monstermo;
            map.killMonster(mob, c.getPlayer(), drop, false, (byte) 1);
        }
    }

    public void �����ͼ��Ʒ(int a) {
        getMap(a).removeDrops();
    }

    public String ְҵ(int a) {
        return getJobNameById(a);
    }

    public int getMeso() {//�жϽ��
        return getPlayer().getMeso();
    }

    public void gainAp(final int amount) {//��AP
        c.getPlayer().gainAp((short) amount);
    }

    public void ��������(final int amount) {//��AP
        c.getPlayer().gainAp((short) amount);
    }

    public void ��������(final int amount) {//��AP
        c.getPlayer().gainAp((short) -amount);
    }

    public void �����ܵ�(final int amount) {//��AP
        c.getPlayer().gainSP((short) amount);
    }

    public void �ռ��ܵ�(final int amount) {//��AP
        c.getPlayer().gainSP2((short) amount);
    }

    public void expandInventory(byte type, int amt) {//��ʵ��
        c.getPlayer().expandInventory(type, amt);
    }

    public void �ѹ�װ��() {//��������װ��
        MapleInventory equipped = getPlayer().getInventory(MapleInventoryType.EQUIPPED);
        MapleInventory equip = getPlayer().getInventory(MapleInventoryType.EQUIP);
        List<Short> ids = new LinkedList<Short>();
        for (IItem item : equipped.list()) {
            ids.add(item.getPosition());
        }
        for (short id : ids) {
            MapleInventoryManipulator.unequip(getC(), id, equip.getNextFreeSlot());
        }
    }

    public void �ѵ���������װ��(int x) {//��������װ��
        MapleInventory equipped = getPlayer().getInventory(MapleInventoryType.EQUIPPED);
        MapleInventory equip = getPlayer().getInventory(MapleInventoryType.EQUIP);
        List<Short> ids = new LinkedList<Short>();
        for (IItem item : equipped.list()) {
            ids.add(item.getPosition());
        }
        for (short id : ids) {
            if (id == x) {
                MapleInventoryManipulator.unequip(getC(), id, equip.getNextFreeSlot());
            }
        }
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
    }

    public void unequipEverything() {//��������װ��
        MapleInventory equipped = getPlayer().getInventory(MapleInventoryType.EQUIPPED);
        MapleInventory equip = getPlayer().getInventory(MapleInventoryType.EQUIP);
        List<Short> ids = new LinkedList<Short>();
        for (IItem item : equipped.list()) {
            ids.add(item.getPosition());
        }
        for (short id : ids) {
            MapleInventoryManipulator.unequip(getC(), id, equip.getNextFreeSlot());
        }
    }

    public final void clearSkills() {//Ӧ���ǹ��ڼ��ܵ�
        Map<ISkill, SkillEntry> skills = getPlayer().getSkills();
        for (Entry<ISkill, SkillEntry> skill : skills.entrySet()) {
            getPlayer().changeSkillLevel(skill.getKey(), (byte) 0, (byte) 0);
        }
    }

    public boolean hasSkill(int skillid) {//������
        ISkill theSkill = SkillFactory.getSkill(skillid);
        if (theSkill != null) {
            return c.getPlayer().getSkillLevel(theSkill) > 0;
        }
        return false;
    }

    public void showEffect(boolean broadcast, String effect) {//cm.showEffect(true, "quest/party/clear");���Ŷ���
        if (broadcast) {
            c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.showEffect(effect));
        } else {
            c.sendPacket(MaplePacketCreator.showEffect(effect));
        }
    }

    public void showString(boolean broadcast, String effect) {
        if (broadcast) {
            c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.showString(effect));
        } else {
            c.sendPacket(MaplePacketCreator.showString(effect));
        }
    }

    public void playSound(boolean broadcast, String sound) {//��������
        if (broadcast) {
            c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.playSound(sound));
        } else {
            c.sendPacket(MaplePacketCreator.playSound(sound));
        }
    }

    public void ȫ����Ч(boolean broadcast, String sound) {//��������
        for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
            for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.playSound(sound));
            }
        }
    }

    public void environmentChange(boolean broadcast, String env) {//�����仯������ʹ�ã�������ͼ��Ӧ�ţ���
        if (broadcast) {
            c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.environmentChange(env, 2));
        } else {
            c.sendPacket(MaplePacketCreator.environmentChange(env, 2));
        }
    }

    public void updateBuddyCapacity(int capacity) {//���Ӻ�����
        c.getPlayer().setBuddyCapacity((byte) capacity);
    }

    public int getBuddyCapacity() {//�������
        return c.getPlayer().getBuddyCapacity();
    }

    public int partyMembersInMap() {//���������ж���Ա
        int inMap = 0;
        for (MapleCharacter char2 : getPlayer().getMap().getCharactersThreadsafe()) {
            if (char2.getParty() == getPlayer().getParty()) {
                inMap++;
            }
        }
        return inMap;
    }

    public List<MapleCharacter> getPartyMembers() {//��������
        if (getPlayer().getParty() == null) {
            return null;
        }
        List<MapleCharacter> chars = new LinkedList<MapleCharacter>(); // creates an empty array full of shit..
        for (MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            for (ChannelServer channel : ChannelServer.getAllInstances()) {
                MapleCharacter ch = channel.getPlayerStorage().getCharacterById(chr.getId());
                if (ch != null) { // double check <3
                    chars.add(ch);
                }
            }
        }
        return chars;
    }

    public void warpPartyWithExp(int mapId, int exp) {//ָ����ͼ������
        MapleMap target = getMap(mapId);
        for (MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            MapleCharacter curChar = c.getChannelServer().getPlayerStorage().getCharacterByName(chr.getName());
            if ((curChar.getEventInstance() == null && getPlayer().getEventInstance() == null) || curChar.getEventInstance() == getPlayer().getEventInstance()) {
                curChar.changeMap(target, target.getPortal(0));
                curChar.gainExp(exp, true, false, true);
            }
        }
    }

    public void warpPartyWithExpMeso(int mapId, int exp, int meso) {//ָ����ͼ��������
        MapleMap target = getMap(mapId);
        for (MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            MapleCharacter curChar = c.getChannelServer().getPlayerStorage().getCharacterByName(chr.getName());
            if ((curChar.getEventInstance() == null && getPlayer().getEventInstance() == null) || curChar.getEventInstance() == getPlayer().getEventInstance()) {
                curChar.changeMap(target, target.getPortal(0));
                curChar.gainExp(exp, true, false, true);
                curChar.gainMeso(meso, true);
            }
        }
    }

    public MapleSquad getSquad(String type) {//�ж��¼���
        return c.getChannelServer().getMapleSquad(type);
    }

    public int getSquadAvailability(String type) {//�ж��¼���
        final MapleSquad squad = c.getChannelServer().getMapleSquad(type);
        if (squad == null) {
            return -1;
        }
        return squad.getStatus();
    }

    public boolean registerSquad(String type, int minutes, String startText) {//��ʼһ��ʱ��
        if (c.getChannelServer().getMapleSquad(type) == null) {
            final MapleSquad squad = new MapleSquad(c.getChannel(), type, c.getPlayer(), minutes * 60 * 1000, startText);
            final boolean ret = c.getChannelServer().addMapleSquad(squad, type);
            if (ret) {
                final MapleMap map = c.getPlayer().getMap();

                map.broadcastMessage(MaplePacketCreator.getClock(minutes * 60));
                map.broadcastMessage(MaplePacketCreator.serverNotice(6, c.getPlayer().getName() + startText));
            } else {
                squad.clear();
            }
            return ret;
        }
        return false;
    }

    public boolean getSquadList(String type, byte type_) {//�¼����
//        try {
        final MapleSquad squad = c.getChannelServer().getMapleSquad(type);
        if (squad == null) {
            return false;
        }
        if (type_ == 0 || type_ == 3) { // Normal viewing
            sendNext(squad.getSquadMemberString(type_));
        } else if (type_ == 1) { // Squad Leader banning, Check out banned participant
            sendSimple(squad.getSquadMemberString(type_));
        } else if (type_ == 2) {
            if (squad.getBannedMemberSize() > 0) {
                sendSimple(squad.getSquadMemberString(type_));
            } else {
                sendNext(squad.getSquadMemberString(type_));
            }
        }
        return true;
        /*
         * } catch (NullPointerException ex) {
         * FileoutputUtil.outputFileError(FileoutputUtil.ScriptEx_Log, ex);
         * return false; }
         */
    }

    public byte isSquadLeader(String type) {//�¼����
        final MapleSquad squad = c.getChannelServer().getMapleSquad(type);
        if (squad == null) {
            return -1;
        } else if (squad.getLeader() != null && squad.getLeader().getId() == c.getPlayer().getId()) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean reAdd(String eim, String squad) {//�¼����
        EventInstanceManager eimz = getDisconnected(eim);
        MapleSquad squadz = getSquad(squad);
        if (eimz != null && squadz != null) {
            squadz.reAddMember(getPlayer());
            eimz.registerPlayer(getPlayer());
            return true;
        }
        return false;
    }

    public void banMember(String type, int pos) {//�¼����
        final MapleSquad squad = c.getChannelServer().getMapleSquad(type);
        if (squad != null) {
            squad.banMember(pos);
        }
    }

    public void acceptMember(String type, int pos) {//�¼����
        final MapleSquad squad = c.getChannelServer().getMapleSquad(type);
        if (squad != null) {
            squad.acceptMember(pos);
        }
    }

    public String getReadableMillis(long startMillis, long endMillis) {//��
        return StringUtil.getReadableMillis(startMillis, endMillis);
    }

    public int addMember(String type, boolean join) {//�¼�
        final MapleSquad squad = c.getChannelServer().getMapleSquad(type);
        if (squad != null) {
            return squad.addMember(c.getPlayer(), join);
        }
        return -1;
    }

    public byte isSquadMember(String type) {
        final MapleSquad squad = c.getChannelServer().getMapleSquad(type);
        if (squad == null) {
            return -1;
        } else if (squad.getMembers().contains(c.getPlayer())) {
            return 1;
        } else if (squad.isBanned(c.getPlayer())) {
            return 2;
        } else {
            return 0;
        }
    }

    public void resetReactors() {//�¼�
        getPlayer().getMap().resetReactors();
    }

    public void genericGuildMessage(int code) {//���ü���ID
        c.sendPacket(MaplePacketCreator.genericGuildMessage((byte) code));
    }

    public void disbandGuild() {//����
        final int gid = c.getPlayer().getGuildId();
        if (gid <= 0 || c.getPlayer().getGuildRank() != 1) {
            return;
        }
        World.Guild.disbandGuild(gid);
    }

    //�����������
    public void increaseGuildCapacity() {
        if (c.getPlayer().getMeso() < 2500000) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "��û���㹻�Ľ�����������������"));
            return;
        }
        final int gid = c.getPlayer().getGuildId();
        if (gid <= 0) {
            return;
        }
        World.Guild.increaseGuildCapacity(gid);
        c.getPlayer().gainMeso(-2500000, true, false, true);
    }

    public void displayGuildRanks() {//�������а�
        c.sendPacket(MaplePacketCreator.showGuildRanks(npc, MapleGuildRanking.getInstance().getGuildRank()));
    }

    public boolean removePlayerFromInstance() {//�¼���
        if (c.getPlayer().getEventInstance() != null) {
            c.getPlayer().getEventInstance().removePlayer(c.getPlayer());
            return true;
        }
        return false;
    }

    public boolean isPlayerInstance() {
        if (c.getPlayer().getEventInstance() != null) {
            return true;
        }
        return false;
    }

    public void changeStat(byte slot, int type, short amount) {//�޸�װ���Ľű�
        Equip sel = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem(slot);
        switch (type) {
            case 0:
                sel.setStr(amount);
                break;
            case 1:
                sel.setDex(amount);
                break;
            case 2:
                sel.setInt(amount);
                break;
            case 3:
                sel.setLuk(amount);
                break;
            case 4:
                sel.setHp(amount);
                break;
            case 5:
                sel.setMp(amount);
                break;
            case 6:
                sel.setWatk(amount);
                break;
            case 7:
                sel.setMatk(amount);
                break;
            case 8:
                sel.setWdef(amount);
                break;
            case 9:
                sel.setMdef(amount);
                break;
            case 10:
                sel.setAcc(amount);
                break;
            case 11:
                sel.setAvoid(amount);
                break;
            case 12:
                sel.setHands(amount);
                break;
            case 13:
                sel.setSpeed(amount);
                break;
            case 14:
                sel.setJump(amount);
                break;
            case 15:
                sel.setUpgradeSlots((byte) amount);
                break;
            case 16:
                sel.setViciousHammer((byte) amount);
                break;
            case 17:
                sel.setLevel((byte) amount);
                break;
            case 18:
                sel.setEnhance((byte) amount);
                break;
            case 19:
                sel.setPotential1(amount);
                break;
            case 20:
                sel.setPotential2(amount);
                break;
            case 21:
                sel.setPotential3(amount);
                break;
            case 22:
                sel.setOwner(getText());
                break;
            default:
                break;
        }
        c.getPlayer().equipChanged();
    }

    public void killAllMonsters() {//���ܣ���
        MapleMap map = c.getPlayer().getMap();
        double range = Double.POSITIVE_INFINITY;
        MapleMonster mob;
        for (MapleMapObject monstermo : map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
            mob = (MapleMonster) monstermo;
            if (mob.getStats().isBoss()) {
                map.killMonster(mob, c.getPlayer(), false, false, (byte) 1);
            }
        }
        /*
         * int mapid = c.getPlayer().getMapId(); MapleMap map =
         * c.getChannelServer().getMapFactory().getMap(mapid);
         * map.killAllMonsters(true); // No drop.
         */
    }

    public void giveMerchantMesos() {//����ࣿ
        long mesos = 0;
        try {
            Connection con = (Connection) DatabaseConnection.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM hiredmerchants WHERE merchantid = ?");
            ps.setInt(1, getPlayer().getId());
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                rs.close();
                ps.close();
            } else {
                mesos = rs.getLong("mesos");
            }
            rs.close();
            ps.close();

            ps = (PreparedStatement) con.prepareStatement("UPDATE hiredmerchants SET mesos = 0 WHERE merchantid = ?");
            ps.setInt(1, getPlayer().getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            System.err.println("Error gaining mesos in hired merchant" + ex);
        }
        c.getPlayer().gainMeso((int) mesos, true);
    }

    public void dc() {
        MapleCharacter victim = c.getChannelServer().getPlayerStorage().getCharacterByName(c.getPlayer().getName().toString());
        victim.getClient().getSession().close();
        victim.getClient().disconnect(true, false);

    }

    public void ��ҷ�����Ϣ(String msg1, String msg2) {
        FileoutputUtil.logToFile("��ҷ�����Ϣ/" + CurrentReadable_Date() + "/" + msg1 + " ������Ϣ.txt", "" + msg2 + "\r\n");
    }

    public void ��ֵ���һ���¼(String msg1, String msg2) {
        FileoutputUtil.logToFile("��ֵ���һ���¼/" + CurrentReadable_Date() + "/" + msg1 + " ��ֵ��.txt", "" + msg2 + "\r\n");
    }

    public void �޸�����(String �˺�, String ����) {
        if (����.length() > 11) {
            c.getPlayer().dropMessage(1, "�������");
            return;
        }
        if (!AutoRegister.getAccountExists(�˺�)) {
            c.getPlayer().dropMessage(1, "�˺Ų�����");
            return;
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("Update accounts set password = ? Where name = ?");
            ps.setString(1, LoginCrypto.hexSha1(����));
            ps.setString(2, �˺�);
            ps.execute();
            ps.close();
        } catch (Exception ex) {
        }
    }

    public long getMerchantMesos() {//���ڽ��
        long mesos = 0;
        try {
            Connection con = (Connection) DatabaseConnection.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM hiredmerchants WHERE merchantid = ?");
            ps.setInt(1, getPlayer().getId());
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                rs.close();
                ps.close();
            } else {
                mesos = rs.getLong("mesos");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println("Error gaining mesos in hired merchant" + ex);
        }
        return mesos;
    }

    public void openDuey() {//?
        c.getPlayer().setConversation(2);
        c.sendPacket(MaplePacketCreator.sendDuey((byte) 9, null));
    }

    public void openMerchantItemStore() {
        c.getPlayer().setConversation(3);
        c.sendPacket(PlayerShopPacket.merchItemStore((byte) 0x22));
//        boolean merch = World.hasMerchant(c.getPlayer().getAccountID());
//        if (!merch) {
//            c.getPlayer().setConversation(3);
//            c.sendPacket(PlayerShopPacket.merchItemStore((byte) 0x22));
//        } else {
//            c.getPlayer().dropMessage(1, "���ȹر�����̵ꡣ");
//        }

    }

    public void openMerchantItemStore1() {
        final MerchItemPackage pack = loadItemFrom_Database(c.getPlayer().getId(), c.getPlayer().getAccountID());
        //c.getPlayer().setConversation(3);
        c.sendPacket(PlayerShopPacket.merchItemStore_ItemData(pack));
    }

    private static final MerchItemPackage loadItemFrom_Database(final int charid, final int accountid) {
        final Connection con = DatabaseConnection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * from hiredmerch where characterid = ? OR accountid = ?");
            ps.setInt(1, charid);
            ps.setInt(2, accountid);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                ps.close();
                rs.close();
                return null;
            }
            final int packageid = rs.getInt("PackageId");

            final MerchItemPackage pack = new MerchItemPackage();
            pack.setPackageid(packageid);
            pack.setMesos(rs.getInt("Mesos"));
            pack.setSentTime(rs.getLong("time"));

            ps.close();
            rs.close();

            Map<Integer, Pair<IItem, MapleInventoryType>> items = ItemLoader.HIRED_MERCHANT.loadItems(false, charid);
            if (items != null) {
                List<IItem> iters = new ArrayList<IItem>();
                for (Pair<IItem, MapleInventoryType> z : items.values()) {
                    iters.add(z.left);
                }
                pack.setItems(iters);
            }

            return pack;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendRepairWindow() {
        c.sendPacket(MaplePacketCreator.sendRepairWindow(npc));
    }

    public final int getDojoPoints() {
        return c.getPlayer().getDojo();
    }

    public final int getDojoRecord() {
        return c.getPlayer().getDojoRecord();
    }

    public void setDojoRecord(final boolean reset) {
        c.getPlayer().setDojoRecord(reset);
    }

    public boolean start_DojoAgent(final boolean dojo, final boolean party) {
        if (dojo) {
            return Event_DojoAgent.warpStartDojo(c.getPlayer(), party);
        }
        return Event_DojoAgent.warpStartAgent(c.getPlayer(), party);
    }

    public boolean start_PyramidSubway(final int pyramid) {//����������������
        if (pyramid >= 0) {
            return Event_PyramidSubway.warpStartPyramid(c.getPlayer(), pyramid);
        }
        return Event_PyramidSubway.warpStartSubway(c.getPlayer());
    }

    public boolean bonus_PyramidSubway(final int pyramid) {//����������������
        if (pyramid >= 0) {
            return Event_PyramidSubway.warpBonusPyramid(c.getPlayer(), pyramid);
        }
        return Event_PyramidSubway.warpBonusSubway(c.getPlayer());
    }

    public final short getKegs() {//�Ǹ�ʲô�������ж�
        return AramiaFireWorks.getInstance().getKegsPercentage();
    }

    public void giveKegs(final int kegs) {//�Ǹ�ʲô��������ʾ
        AramiaFireWorks.getInstance().giveKegs(c.getPlayer(), kegs);
    }

    public final short getSunshines() {
        return AramiaFireWorks.getInstance().getSunsPercentage();
    }

    public void addSunshines(final int kegs) {
        AramiaFireWorks.getInstance().giveSuns(c.getPlayer(), kegs);
    }

    public final short getDecorations() {
        return AramiaFireWorks.getInstance().getDecsPercentage();
    }

    public void addDecorations(final int kegs) {
        try {
            AramiaFireWorks.getInstance().giveDecs(c.getPlayer(), kegs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final MapleInventory getInventory(int type) {//�жϱ���
        return c.getPlayer().getInventory(MapleInventoryType.getByType((byte) type));
    }

    public final MapleCarnivalParty getCarnivalParty() {//���껪�࣬�ж�ʲô��Ϊ��
        return c.getPlayer().getCarnivalParty();
    }

    public final MapleCarnivalChallenge getNextCarnivalRequest() {//���껪��
        return c.getPlayer().getNextCarnivalRequest();
    }

    public final MapleCarnivalChallenge getCarnivalChallenge(MapleCharacter chr) {//�öԷ���NPC�������껪��
        return new MapleCarnivalChallenge(chr);
    }

    public void setHP(short hp) {//���õ�ǰ����ֵ
        c.getPlayer().getStat().setHp(hp);
    }

    public void ���ӽ�ɫ�������ֵ(short hp) {//���õ�ǰ����ֵ
        c.getPlayer().getStat().setHp(hp);
    }

    public void ���ӽ�ɫ�����ֵ(short MP) {//���õ�ǰ����ֵ
        c.getPlayer().getStat().setMp(MP);
    }

    public void maxStats() {//�����������
        List<Pair<MapleStat, Integer>> statup = new ArrayList<Pair<MapleStat, Integer>>(2);
        c.getPlayer().getStat().setStr((short) 32767);
        c.getPlayer().getStat().setDex((short) 32767);
        c.getPlayer().getStat().setInt((short) 32767);
        c.getPlayer().getStat().setLuk((short) 32767);

        c.getPlayer().getStat().setMaxHp((short) 30000);
        c.getPlayer().getStat().setMaxMp((short) 30000);
        c.getPlayer().getStat().setHp((short) 30000);
        c.getPlayer().getStat().setMp((short) 30000);

        statup.add(new Pair<MapleStat, Integer>(MapleStat.STR, Integer.valueOf(32767)));
        statup.add(new Pair<MapleStat, Integer>(MapleStat.DEX, Integer.valueOf(32767)));
        statup.add(new Pair<MapleStat, Integer>(MapleStat.LUK, Integer.valueOf(32767)));
        statup.add(new Pair<MapleStat, Integer>(MapleStat.INT, Integer.valueOf(32767)));
        statup.add(new Pair<MapleStat, Integer>(MapleStat.HP, Integer.valueOf(30000)));
        statup.add(new Pair<MapleStat, Integer>(MapleStat.MAXHP, Integer.valueOf(30000)));
        statup.add(new Pair<MapleStat, Integer>(MapleStat.MP, Integer.valueOf(30000)));
        statup.add(new Pair<MapleStat, Integer>(MapleStat.MAXMP, Integer.valueOf(30000)));

        c.sendPacket(MaplePacketCreator.updatePlayerStats(statup, c.getPlayer().getJob()));
    }

    public Pair<String, Map<Integer, String>> getSpeedRun(String typ) {//��ͼ������
        final SpeedRunType type = SpeedRunType.valueOf(typ);
        if (SpeedRunner.getInstance().getSpeedRunData(type) != null) {
            return SpeedRunner.getInstance().getSpeedRunData(type);
        }
        return new Pair<String, Map<Integer, String>>("", new HashMap<Integer, String>());
    }

    public boolean getSR(Pair<String, Map<Integer, String>> ma, int sel) {
        if (ma.getRight().get(sel) == null || ma.getRight().get(sel).length() <= 0) {
            dispose();
            return false;
        }
        sendOk(ma.getRight().get(sel));
        return true;
    }

    public Equip getEquip(int itemid) {//��Ʒ���
        return (Equip) MapleItemInformationProvider.getInstance().getEquipById(itemid);
    }

    public void setExpiration(Object statsSel, long expire) {//������Ʒʱ��
        if (statsSel instanceof Equip) {
            ((Equip) statsSel).setExpiration(System.currentTimeMillis() + (expire * 24 * 60 * 60 * 1000));
        }
    }

    public void setLock(Object statsSel) {//������ô
        if (statsSel instanceof Equip) {
            Equip eq = (Equip) statsSel;
            if (eq.getExpiration() == -1) {
                eq.setFlag((byte) (eq.getFlag() | ItemFlag.LOCK.getValue()));
            } else {
                eq.setFlag((byte) (eq.getFlag() | ItemFlag.UNTRADEABLE.getValue()));
            }
        }
    }

    public boolean addFromDrop(Object statsSel) {
        if (statsSel instanceof IItem) {
            final IItem it = (IItem) statsSel;
            return MapleInventoryManipulator.checkSpace(getClient(), it.getItemId(), it.getQuantity(), it.getOwner()) && MapleInventoryManipulator.addFromDrop(getClient(), it, false);
        }
        return false;
    }

    public boolean replaceItem(int slot, int invType, Object statsSel, int offset, String type) {
        return replaceItem(slot, invType, statsSel, offset, type, false);
    }

    public boolean replaceItem(int slot, int invType, Object statsSel, int offset, String type, boolean takeSlot) {
        MapleInventoryType inv = MapleInventoryType.getByType((byte) invType);
        if (inv == null) {
            return false;
        }
        IItem item = getPlayer().getInventory(inv).getItem((byte) slot);
        if (item == null || statsSel instanceof IItem) {
            item = (IItem) statsSel;
        }
        if (offset > 0) {
            if (inv != MapleInventoryType.EQUIP) {
                return false;
            }
            Equip eq = (Equip) item;
            if (takeSlot) {
                if (eq.getUpgradeSlots() < 1) {
                    return false;
                } else {
                    eq.setUpgradeSlots((byte) (eq.getUpgradeSlots() - 1));
                }
            }
            if (type.equalsIgnoreCase("Slots")) {
                eq.setUpgradeSlots((byte) (eq.getUpgradeSlots() + offset));
            } else if (type.equalsIgnoreCase("Level")) {
                eq.setLevel((byte) (eq.getLevel() + offset));
            } else if (type.equalsIgnoreCase("Hammer")) {
                eq.setViciousHammer((byte) (eq.getViciousHammer() + offset));
            } else if (type.equalsIgnoreCase("STR")) {
                eq.setStr((short) (eq.getStr() + offset));
            } else if (type.equalsIgnoreCase("DEX")) {
                eq.setDex((short) (eq.getDex() + offset));
            } else if (type.equalsIgnoreCase("INT")) {
                eq.setInt((short) (eq.getInt() + offset));
            } else if (type.equalsIgnoreCase("LUK")) {
                eq.setLuk((short) (eq.getLuk() + offset));
            } else if (type.equalsIgnoreCase("HP")) {
                eq.setHp((short) (eq.getHp() + offset));
            } else if (type.equalsIgnoreCase("MP")) {
                eq.setMp((short) (eq.getMp() + offset));
            } else if (type.equalsIgnoreCase("WATK")) {
                eq.setWatk((short) (eq.getWatk() + offset));
            } else if (type.equalsIgnoreCase("MATK")) {
                eq.setMatk((short) (eq.getMatk() + offset));
            } else if (type.equalsIgnoreCase("WDEF")) {
                eq.setWdef((short) (eq.getWdef() + offset));
            } else if (type.equalsIgnoreCase("MDEF")) {
                eq.setMdef((short) (eq.getMdef() + offset));
            } else if (type.equalsIgnoreCase("ACC")) {
                eq.setAcc((short) (eq.getAcc() + offset));
            } else if (type.equalsIgnoreCase("Avoid")) {
                eq.setAvoid((short) (eq.getAvoid() + offset));
            } else if (type.equalsIgnoreCase("Hands")) {
                eq.setHands((short) (eq.getHands() + offset));
            } else if (type.equalsIgnoreCase("Speed")) {
                eq.setSpeed((short) (eq.getSpeed() + offset));
            } else if (type.equalsIgnoreCase("Jump")) {
                eq.setJump((short) (eq.getJump() + offset));
            } else if (type.equalsIgnoreCase("ItemEXP")) {
                eq.setItemEXP(eq.getItemEXP() + offset);
            } else if (type.equalsIgnoreCase("Expiration")) {
                eq.setExpiration((long) (eq.getExpiration() + offset));
            } else if (type.equalsIgnoreCase("Flag")) {
                eq.setFlag((byte) (eq.getFlag() + offset));
            }
            if (eq.getExpiration() == -1) {
                eq.setFlag((byte) (eq.getFlag() | ItemFlag.LOCK.getValue()));
            } else {
                eq.setFlag((byte) (eq.getFlag() | ItemFlag.UNTRADEABLE.getValue()));
            }
            item = eq.copy();
        }
        MapleInventoryManipulator.removeFromSlot(getClient(), inv, (short) slot, item.getQuantity(), false);
        return MapleInventoryManipulator.addFromDrop(getClient(), item, false);
    }

    public boolean replaceItem(int slot, int invType, Object statsSel, int upgradeSlots) {
        return replaceItem(slot, invType, statsSel, upgradeSlots, "Slots");
    }

    public boolean isCash(final int itemId) {
        return MapleItemInformationProvider.getInstance().isCash(itemId);
    }

    public boolean ʱװ(final int itemId) {
        return MapleItemInformationProvider.getInstance().isCash(itemId);
    }

    public void buffGuild(final int buff, final int duration, final String msg) {
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (ii.getItemEffect(buff) != null && getPlayer().getGuildId() > 0) {
            final MapleStatEffect mse = ii.getItemEffect(buff);
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                    if (chr.getGuildId() == getPlayer().getGuildId()) {
                        mse.applyTo(chr, chr, true, null, duration);
                        chr.dropMessage(5, "Your guild has gotten a " + msg + " buff.");
                    }
                }
            }
        }
    }

    public boolean createAlliance(String alliancename) {
        MapleParty pt = c.getPlayer().getParty();
        MapleCharacter otherChar = c.getChannelServer().getPlayerStorage().getCharacterById(pt.getMemberByIndex(1).getId());
        if (otherChar == null || otherChar.getId() == c.getPlayer().getId()) {
            return false;
        }
        try {
            return World.Alliance.createAlliance(alliancename, c.getPlayer().getId(), otherChar.getId(), c.getPlayer().getGuildId(), otherChar.getGuildId());
        } catch (Exception re) {
            re.printStackTrace();
            return false;
        }
    }

    public boolean addCapacityToAlliance() {
        try {
            final MapleGuild gs = World.Guild.getGuild(c.getPlayer().getGuildId());
            if (gs != null && c.getPlayer().getGuildRank() == 1 && c.getPlayer().getAllianceRank() == 1) {
                if (World.Alliance.getAllianceLeader(gs.getAllianceId()) == c.getPlayer().getId() && World.Alliance.changeAllianceCapacity(gs.getAllianceId())) {
                    gainMeso(-MapleGuildAlliance.CHANGE_CAPACITY_COST);
                    return true;
                }
            }
        } catch (Exception re) {
            re.printStackTrace();
        }
        return false;
    }

    public boolean disbandAlliance() {
        try {
            final MapleGuild gs = World.Guild.getGuild(c.getPlayer().getGuildId());
            if (gs != null && c.getPlayer().getGuildRank() == 1 && c.getPlayer().getAllianceRank() == 1) {
                if (World.Alliance.getAllianceLeader(gs.getAllianceId()) == c.getPlayer().getId() && World.Alliance.disbandAlliance(gs.getAllianceId())) {
                    return true;
                }
            }
        } catch (Exception re) {
            re.printStackTrace();
        }
        return false;
    }

    public byte getLastMsg() {
        return lastMsg;
    }

    public final void setLastMsg(final byte last) {
        this.lastMsg = last;
    }

    public final void maxAllSkills() {
        for (ISkill skil : SkillFactory.getAllSkills()) {
            if (GameConstants.isApplicableSkill(skil.getId())) { //no db/additionals/resistance skills
                teachSkill(skil.getId(), skil.getMaxLevel(), skil.getMaxLevel());
            }
        }
    }

    public final void resetStats(int str, int dex, int z, int luk) {
        c.getPlayer().resetStats(str, dex, z, luk);
    }

    public final boolean dropItem(int slot, int invType, int quantity) {
        MapleInventoryType inv = MapleInventoryType.getByType((byte) invType);
        if (inv == null) {
            return false;
        }
        return MapleInventoryManipulator.drop(c, inv, (short) slot, (short) quantity, true);
    }

    public final List<Integer> getAllPotentialInfo() {
        return new ArrayList<Integer>(MapleItemInformationProvider.getInstance().getAllPotentialInfo().keySet());
    }

    public final String getPotentialInfo(final int id) {//�޸�װ���ű�
        final List<StructPotentialItem> potInfo = MapleItemInformationProvider.getInstance().getPotentialInfo(id);
        final StringBuilder builder = new StringBuilder("#b#ePOTENTIAL INFO FOR ID: ");
        builder.append(id);
        builder.append("#n#k\r\n\r\n");
        int minLevel = 1, maxLevel = 10;
        for (StructPotentialItem item : potInfo) {
            builder.append("#eLevels ");
            builder.append(minLevel);
            builder.append("~");
            builder.append(maxLevel);
            builder.append(": #n");
            builder.append(item.toString());
            minLevel += 10;
            maxLevel += 10;
            builder.append("\r\n");
        }
        return builder.toString();
    }

    public final void sendRPS() {
        c.sendPacket(MaplePacketCreator.getRPSMode((byte) 8, -1, -1, -1));
    }

    public final void setQuestRecord(Object ch, final int questid, final String data) {
        ((MapleCharacter) ch).getQuestNAdd(MapleQuest.getInstance(questid)).setCustomData(data);
    }

    public final void doWeddingEffect(final Object ch) {//�����
        final MapleCharacter chr = (MapleCharacter) ch;
        // getMap().broadcastMessage(MaplePacketCreator.yellowChat(getPlayer().getName() + ", do you take " + chr.getName() + " as your wife and promise to stay beside her through all downtimes, crashes, and lags?"));
        CloneTimer.getInstance().schedule(new Runnable() {

            public void run() {
                if (chr == null || getPlayer() == null) {
                    warpMap(680000500, 0);
                } else {
                    // getMap().broadcastMessage(MaplePacketCreator.yellowChat(chr.getName() + ", do you take " + getPlayer().getName() + " as your husband and promise to stay beside him through all downtimes, crashes, and lags?"));
                }
            }
        }, 10000);
        CloneTimer.getInstance().schedule(new Runnable() {

            public void run() {
                if (chr == null || getPlayer() == null) {
                    if (getPlayer() != null) {
                        setQuestRecord(getPlayer(), 160001, "3");
                        setQuestRecord(getPlayer(), 160002, "0");
                    } else if (chr != null) {
                        setQuestRecord(chr, 160001, "3");
                        setQuestRecord(chr, 160002, "0");
                    }
                    warpMap(680000500, 0);
                } else {
                    setQuestRecord(getPlayer(), 160001, "2");
                    setQuestRecord(chr, 160001, "2");
                    sendNPCText(getPlayer().getName() + " and " + chr.getName() + ",��ϣ������������������ó�!", 9201002);
                    getMap().startExtendedMapEffect("�����ڿ��������� " + getPlayer().getName() + "!", 5120006);
                    if (chr.getGuildId() > 0) {
                        World.Guild.guildPacket(chr.getGuildId(), MaplePacketCreator.sendMarriage(false, chr.getName()));
                    }
                    if (chr.getFamilyId() > 0) {
                        World.Family.familyPacket(chr.getFamilyId(), MaplePacketCreator.sendMarriage(true, chr.getName()), chr.getId());
                    }
                    if (getPlayer().getGuildId() > 0) {
                        World.Guild.guildPacket(getPlayer().getGuildId(), MaplePacketCreator.sendMarriage(false, getPlayer().getName()));
                    }
                    if (getPlayer().getFamilyId() > 0) {
                        World.Family.familyPacket(getPlayer().getFamilyId(), MaplePacketCreator.sendMarriage(true, chr.getName()), getPlayer().getId());
                    }
                }
            }
        }, 20000); //10 sec 10 sec
    }

    public void openDD(int type) {//�򿪶�����
        c.sendPacket(MaplePacketCreator.openBeans(getPlayer().getBeans(), type));
        //c.sendPacket(MaplePacketCreator.testPacket(HexTool.getByteArrayFromHexString("5B 01 01 00 00 00 00 04 00 61 61 61 61")));
    }

    public void ���(int type) {//�򿪶�����
        c.sendPacket(MaplePacketCreator.openBeans(getPlayer().getBeans(), type));
    }

    public void Сֽ��(String type1, String type2) {
        c.getPlayer().sendNote(type1, type2);
    }

    public void Ⱥ�����Ϣ(String type) {//�򿪶�����
        if (gui.Start.ConfigValuesMap.get("QQ�����˿���") == 0) {
            if (MapleParty.�����˴��� == 0) {
                sendMsgToQQGroup(type);
            }
        }
    }

    public void Gainrebirth(String Name, int Channale, int Piot) {
        try {
            int ret = Getrebirth(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO rebirth (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);
                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e);
                    }
                }
            }
            ret += Piot;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE rebirth SET `Point` = ? WHERE Name = ? and channel = ?");
            ps.setInt(1, ret);
            ps.setString(2, Name);
            ps.setInt(3, Channale);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("Getrebirth!!55" + sql);
        }
    }

    public int Getrebirth(String Name, int Channale) {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM rebirth WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public void ˽�������Ϣ(String type1, String type2) {//c.getPlayer().sendNote("[��Ϣ����]", "����һ�β�����Ϣ");
        if (gui.Start.ConfigValuesMap.get("QQ�����˿���") == 0) {
            if (MapleParty.�����˴��� == 0) {
                sendMsg(type1, type2);
            }
        }
    }

    public void ��̬����(String type1) {
        gui.Start.ConfigValuesMap.get(type1);
    }

    public void worldMessage(String text) {//��������
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, text));
    }

    public void ȫ������(String text) {//��������
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, text));
    }

    public void ȫ������(String text) {//��������
        World.Broadcast.broadcastMessage(MaplePacketCreator.��ɫ����(text));
    }

    public int getBeans() {//�ж϶���
        return getClient().getPlayer().getBeans();
    }

    public int �ж϶�������() {//�ж϶���
        return getClient().getPlayer().getBeans();
    }

    public void gainBeans(int s) {//������
        getPlayer().gainBeans(s);
        c.sendPacket(MaplePacketCreator.updateBeans(c.getPlayer().getId(), s));
    }

    public void ������(int s) {//������
        getPlayer().gainBeans(s);
        c.sendPacket(MaplePacketCreator.updateBeans(c.getPlayer().getId(), s));
    }

    public void �ն���(int s) {//������
        getPlayer().gainBeans(-s);
        c.sendPacket(MaplePacketCreator.updateBeans(c.getPlayer().getId(), s));
    }

    public int getHyPay(int type) {//�ж�HYPAY
        return getPlayer().getHyPay(type);
    }

    public void szhs(String ss) {//8������
        c.sendPacket(MaplePacketCreator.��Ϸ��Ļ�м��ɫ����(ss));
    }

    public void ���Է���(int effect) {//8������
        c.sendPacket(MaplePacketCreator.showSpecialEffect(effect));
    }

    public void szhs(String ss, int id) {
        c.sendPacket(MaplePacketCreator.��Ϸ��Ļ�м��ɫ����(ss, id));
    }

    public int gainHyPay(int hypay) {//��hypay
        return getPlayer().gainHyPay(hypay);
    }

    public void ǿ���Ӿ����(int upgr) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setUpgradeSlots((byte) (item.getUpgradeSlots() + upgr));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void gainEquiPproperty(int upgr, int watk, int matk, int str, int dex, int Int, int luk, int hp, int mp, int acc, int avoid) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setUpgradeSlots((byte) (item.getUpgradeSlots() + upgr));
        item.setWatk((short) (item.getWatk() + watk));
        item.setMatk((short) (item.getMatk() + matk));
        item.setStr((short) (item.getStr() + str));
        item.setDex((short) (item.getDex() + dex));
        item.setInt((short) (item.getInt() + Int));
        item.setLuk((short) (item.getLuk() + luk));
        item.setHp((short) (item.getHp() + hp));
        item.setMp((short) (item.getMp() + mp));
        item.setAcc((byte) (item.getAcc() + acc));
        item.setAvoid((byte) (item.getAvoid() + avoid));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public String ��ʾװ������() {
        StringBuilder name = new StringBuilder();
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        if (item.getUpgradeSlots() > 0) {
            name.append("��������:#b" + item.getUpgradeSlots() + "#k\r\n");
        }
        if (item.getWatk() > 0) {
            name.append("��������:#b" + item.getWatk() + "#k\r\n");
        }
        if (item.getMatk() > 0) {
            name.append("ħ��������:#b" + item.getMatk() + "#k\r\n");
        }
        if (item.getWdef() > 0) {
            name.append("���������:#b" + item.getWdef() + "#k\r\n");
        }
        if (item.getMdef() > 0) {
            name.append("ħ��������:#b" + item.getMdef() + "#k\r\n");
        }
        if (item.getStr() > 0) {
            name.append("����:#b" + item.getStr() + "#k\r\n");
        }
        if (item.getDex() > 0) {
            name.append("����:#b" + item.getDex() + "#k\r\n");
        }
        if (item.getLuk() > 0) {
            name.append("����:#b" + item.getLuk() + "#k\r\n");
        }
        if (item.getInt() > 0) {
            name.append("����:#b" + item.getInt() + "#k\r\n");
        }
        if (item.getHp() > 0) {
            name.append("HP:#b" + item.getHp() + "#k\r\n");
        }
        if (item.getMp() > 0) {
            name.append("MP:#b" + item.getMp() + "#k\r\n");
        }
        if (item.getAcc() > 0) {
            name.append("������:#b" + item.getAcc() + "#k\r\n");
        }
        if (item.getAvoid() > 0) {
            name.append("������:#b" + item.getAvoid() + "#k\r\n");
        }
        if (item.getSpeed() > 0) {
            name.append("�ƶ��ٶ�:#b" + item.getSpeed() + "#k\r\n");
        }
        return name.toString();
    }

    public void ��ȡװ������Ʒ����() {
        c.getPlayer().dropMessage(5, "" + ((Equip) c.getPlayer().getInventory(MapleInventoryType.װ����).getItem((short) 1)) + "");
    }

    //��λ�����ԣ��ɹ��ʣ�ֵ
    public void ǿ������װ��(int aa, int bb, int cc, int dd) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) aa).copy();

    }

    public void װ��ϴ��() {

        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();

        if (item.getUpgradeSlots() > 0) {
            int getUpgradeSlots = (int) Math.ceil(Math.random() * (item.getUpgradeSlots() + item.getUpgradeSlots() * 0.5));
            item.setUpgradeSlots((byte) getUpgradeSlots);
        }
        if (item.getWatk() > 0) {
            int getWatk = (int) Math.ceil(Math.random() * (item.getWatk() + item.getWatk() * 0.5));
            item.setWatk((byte) getWatk);
        }
        if (item.getMatk() > 0) {
            int getMatk = (int) Math.ceil(Math.random() * (item.getMatk() + item.getMatk() * 0.5));
            item.setMatk((byte) getMatk);
        }
        if (item.getWdef() > 0) {
            int getWdef = (int) Math.ceil(Math.random() * (item.getWdef() + item.getWdef() * 0.5));
            item.setWdef((byte) getWdef);
        }
        if (item.getMdef() > 0) {
            int getMdef = (int) Math.ceil(Math.random() * (item.getMdef() + item.getMdef() * 0.5));
            item.setMdef((byte) getMdef);
        }
        if (item.getStr() > 0) {
            int getStr = (int) Math.ceil(Math.random() * (item.getStr() + item.getStr() * 0.5));
            item.setStr((byte) getStr);
        }
        if (item.getDex() > 0) {
            int getDex = (int) Math.ceil(Math.random() * (item.getDex() + item.getDex() * 0.5));
            item.setDex((byte) getDex);
        }
        if (item.getLuk() > 0) {
            int getLuk = (int) Math.ceil(Math.random() * (item.getLuk() + item.getLuk() * 0.5));
            item.setLuk((byte) getLuk);
        }
        if (item.getInt() > 0) {
            int getInt = (int) Math.ceil(Math.random() * (item.getInt() + item.getInt() * 0.5));
            item.setInt((byte) getInt);
        }
        if (item.getHp() > 0) {
            int getHp = (int) Math.ceil(Math.random() * (item.getHp() + item.getHp() * 0.5));
            item.setHp((byte) getHp);
        }
        if (item.getMp() > 0) {
            int getMp = (int) Math.ceil(Math.random() * (item.getMp() + item.getMp() * 0.5));
            item.setMp((byte) getMp);
        }
        if (item.getAcc() > 0) {
            int getAcc = (int) Math.ceil(Math.random() * (item.getAcc() + item.getAcc() * 0.5));
            item.setAcc((byte) getAcc);
        }
        if (item.getAvoid() > 0) {
            int getAvoid = (int) Math.ceil(Math.random() * (item.getAvoid() + item.getAvoid() * 0.5));
            item.setAvoid((byte) getAvoid);
        }
        if (item.getSpeed() > 0) {
            int getSpeed = (int) Math.ceil(Math.random() * (item.getSpeed() + item.getSpeed() * 0.5));
            item.setSpeed((byte) getSpeed);
        }
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }


    /*
    1.���ͣ�
    2.��ֵ��
    3.���ʣ�
    4.
     */
    public void ǿ��װ��1(int aa, int bb, int cc, int dd) {

    }

    /*
    //ǿ����һ��װ������,�ӷ���ֵ
     */
    public void �������ֵ(int mp) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setMp((short) (item.getMp() + mp));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void �������ֵ(int mp, int a) {
        final double ���� = Math.ceil(Math.random() * 100);
        if (���� <= a) {
            Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
            item.setMp((short) (item.getMp() + mp));
            MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
            MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
        } else {
            c.getPlayer().dropMessage(1, "ǿ��ʧ�ܡ�");
        }
    }

    public void �������ֵ2(int mp) {
        final double ��� = Math.ceil(Math.random() * mp);
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setMp((short) (item.getMp() + ���));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void �������ֵ2(int mp, int a) {
        final double ��� = Math.ceil(Math.random() * mp);
        final double ���� = Math.ceil(Math.random() * 100);
        if (���� <= a) {
            Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
            item.setMp((short) (item.getMp() + ���));
            MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
            MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
        } else {
            c.getPlayer().dropMessage(1, "ǿ��ʧ�ܡ�");
        }
    }

    /*
    //ǿ����һ��װ������,������ֵ
     */
    public void �ӱ�ʯ(int a) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setMpR((short) (item.getMpR() + a));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void װ���;�(int a, int b) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem((short) a).copy();
        item.setHpR((short) (item.getHpR() + b));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIPPED, (short) a, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void ���������ֵ(int hp) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setHp((short) (item.getHp() + hp));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void ������(int luk) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setLuk((short) (item.getLuk() + luk));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void ������(int Int) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setInt((short) (item.getInt() + Int));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void ������(int dex) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setDex((short) (item.getDex() + dex));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void ������(int str) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setStr((short) (item.getStr() + str));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void ��������(int Acc) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setAcc((short) (item.getAcc() + Acc));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void ����Ծ��(int Jump) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setJump((short) (item.getJump() + Jump));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void ���ƶ��ٶ�(int Speed) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setSpeed((short) (item.getSpeed() + Speed));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void ��������(int Avoid) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setAvoid((short) (item.getAvoid() + Avoid));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void ��ħ������(int matk) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setMatk((short) (item.getMatk() + matk));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void ��ħ������(int Mdef) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setMdef((short) (item.getMdef() + Mdef));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void ��������(int watk) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setWatk((short) (item.getWatk() + watk));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void ���������(int Wdef) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setWdef((short) (item.getWdef() + Wdef));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public void ����������(int upgr) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        item.setUpgradeSlots((byte) (item.getUpgradeSlots() + upgr));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    /*
    ǿ������
     */
    //��ȡǿ������
    public void ǿ������(int a) {
        //�ж�װ������һ���Ƿ��е���
        if (c.getPlayer().getInventory(MapleInventoryType.װ����).getItem((short) 1) == null) {
            c.getPlayer().dropMessage(1, "װ������һ��û�е��ߡ�");
            return;
        }
        //��ȡװ����װ��������Ϣ
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();
        //����ֵ����
        byte ���������� = 0;
        int HP, MP, ����, ����, ����, ����, ��������, ���������, ħ��������, ħ��������, �ر���, ������, ��Ծ��, �ƶ��ٶ�;
        int �ɹ���, ����̶�ֵ;
        //��ʼ������
        HP = 0;
        MP = 0;
        ���� = 0;
        ���� = 0;
        ���� = 0;
        ���� = 0;
        �������� = 0;
        ��������� = 0;
        ħ�������� = 0;
        ħ�������� = 0;
        �ر��� = 0;
        ������ = 0;
        ��Ծ�� = 0;
        �ƶ��ٶ� = 0;
        �ɹ��� = 0;
        ����̶�ֵ = 0;
        //�����ݿ���ȡ��Ϣ
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Strengthening WHERE id2 = " + a + "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ���������� = (byte) rs.getInt("����������");
                HP = rs.getInt("HP");
                MP = rs.getInt("MP");
                ���� = rs.getInt("����");
                ���� = rs.getInt("����");
                ���� = rs.getInt("����");
                ���� = rs.getInt("����");
                �������� = rs.getInt("��������");
                ��������� = rs.getInt("���������");
                ħ�������� = rs.getInt("ħ��������");
                ħ�������� = rs.getInt("ħ��������");
                �ر��� = rs.getInt("�ر���");
                ������ = rs.getInt("������");
                ��Ծ�� = rs.getInt("��Ծ��");
                �ƶ��ٶ� = rs.getInt("�ƶ��ٶ�");
                �ɹ��� = rs.getInt("�ɹ���");
                ����̶�ֵ = rs.getInt("���ֵ");//0�����1�̶�
            }
        } catch (SQLException ex) {
        }
        //��ȡ��Ϣ�󣬿�ʼ����
        if (�ɹ��� <= Math.ceil(Math.random() * 100)) {
            if (����̶�ֵ == 1) {
                item.setUpgradeSlots((byte) (item.getUpgradeSlots() + ����������));
                item.setHp((short) (item.getHp() + HP));
                item.setMp((short) (item.getMp() + MP));
                item.setWatk((short) (item.getWatk() + ��������));
                item.setWdef((short) (item.getWdef() + ���������));
                item.setMdef((short) (item.getMdef() + ħ��������));
                item.setMatk((short) (item.getMatk() + ħ��������));
                item.setStr((short) (item.getStr() + ����));
                item.setDex((short) (item.getDex() + ����));
                item.setInt((short) (item.getInt() + ����));
                item.setLuk((short) (item.getLuk() + ����));
                item.setAcc((byte) (item.getAcc() + ������));
                item.setAvoid((byte) (item.getAvoid() + �ر���));
                item.setJump((byte) (item.getJump() + ��Ծ��));
                item.setSpeed((byte) (item.getSpeed() + �ƶ��ٶ�));
                c.getPlayer().dropMessage(1, "[ǿ���ɹ�]:\r\n"
                        + "HP + " + HP + ""
                        + "MP + " + MP + ""
                        + "���� + " + ���� + ""
                        + "���� + " + ���� + ""
                        + "���� + " + ���� + ""
                        + "���� + " + ���� + ""
                        + "������ + " + ������ + ""
                        + "�ر��� + " + �ر��� + ""
                        + "��Ծ�� + " + ��Ծ�� + ""
                        + "�ƶ��ٶ� + " + �ƶ��ٶ� + ""
                        + "�������� + " + �������� + ""
                        + "��������� + " + ��������� + ""
                        + "ħ�������� + " + ħ�������� + ""
                        + "ħ�������� + " + ħ�������� + ""
                        + "");
                MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
                MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
            } else {
                item.setUpgradeSlots((byte) (item.getUpgradeSlots() + Math.ceil(Math.random() * ����������)));
                item.setHp((short) (item.getHp() + Math.ceil(Math.random() * HP)));
                item.setMp((short) (item.getMp() + Math.ceil(Math.random() * MP)));
                item.setWatk((short) (item.getWatk() + Math.ceil(Math.random() * ��������)));
                item.setWdef((short) (item.getWdef() + Math.ceil(Math.random() * ���������)));
                item.setMdef((short) (item.getMdef() + Math.ceil(Math.random() * ħ��������)));
                item.setMatk((short) (item.getMatk() + Math.ceil(Math.random() * ħ��������)));
                item.setStr((short) (item.getStr() + Math.ceil(Math.random() * ����)));
                item.setDex((short) (item.getDex() + Math.ceil(Math.random() * ����)));
                item.setInt((short) (item.getInt() + Math.ceil(Math.random() * ����)));
                item.setLuk((short) (item.getLuk() + Math.ceil(Math.random() * ����)));
                item.setAcc((byte) (item.getAcc() + Math.ceil(Math.random() * ������)));
                item.setAvoid((byte) (item.getAvoid() + Math.ceil(Math.random() * �ر���)));
                item.setJump((byte) (item.getJump() + Math.ceil(Math.random() * ��Ծ��)));
                item.setSpeed((byte) (item.getSpeed() + Math.ceil(Math.random() * �ƶ��ٶ�)));
                c.getPlayer().dropMessage(1, "[ǿ���ɹ�]:\r\n"
                        + "HP + " + HP + ""
                        + "MP + " + MP + ""
                        + "���� + " + ���� + ""
                        + "���� + " + ���� + ""
                        + "���� + " + ���� + ""
                        + "���� + " + ���� + ""
                        + "������ + " + ������ + ""
                        + "�ر��� + " + �ر��� + ""
                        + "��Ծ�� + " + ��Ծ�� + ""
                        + "�ƶ��ٶ� + " + �ƶ��ٶ� + ""
                        + "�������� + " + �������� + ""
                        + "��������� + " + ��������� + ""
                        + "ħ�������� + " + ħ�������� + ""
                        + "ħ�������� + " + ħ�������� + ""
                        + "");
            }
            MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
            MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
        } else {
            c.getPlayer().dropMessage(1, "ǿ��ʧ����");
        }

    }

    public void ħ��(int upgr, int watk, int matk, int str, int dex, int Int, int luk, int hp, int mp, int acc, int avoid) {
        Equip item = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short) 1).copy();

        item.setUpgradeSlots((byte) (item.getUpgradeSlots() + upgr));
        item.setWatk((short) (item.getWatk() + watk));
        item.setMatk((short) (item.getMatk() + matk));
        item.setStr((short) (item.getStr() + str));
        item.setDex((short) (item.getDex() + dex));
        item.setInt((short) (item.getInt() + Int));
        item.setLuk((short) (item.getLuk() + luk));
        item.setHp((short) (item.getHp() + hp));
        item.setMp((short) (item.getMp() + mp));
        item.setAcc((byte) (item.getAcc() + acc));
        item.setAvoid((byte) (item.getAvoid() + avoid));
        MapleInventoryManipulator.removeFromSlot(getC(), MapleInventoryType.EQUIP, (short) 1, (short) 1, true);
        MapleInventoryManipulator.addFromDrop(getChar().getClient(), item, false);
    }

    public int addHyPay(int hypay) {
        return getPlayer().addHyPay(hypay);
    }

    public int delPayReward(int pay) {
        return getPlayer().delPayReward(pay);
    }

    public int getItemLevel(int id) {
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        return ii.getReqLevel(id);
    }

    public void alatPQ() {
        //   c.sendPacket(MaplePacketCreator.updateAriantPQRanking(getText, npc, pendingDisposal))
    }

    public void xlkc(long days) {
        MapleQuestStatus marr = getPlayer().getQuestNoAdd(MapleQuest.getInstance(122700));
        if ((marr != null) && (marr.getCustomData() != null) && (Long.parseLong(marr.getCustomData()) >= System.currentTimeMillis())) {
            getPlayer().dropMessage(1, "��������ʧ�ܣ����Ѿ����й��������䡣");
        } else {
            String customData = String.valueOf(System.currentTimeMillis() + days * 24L * 60L * 60L * 1000L);
            getPlayer().getQuestNAdd(MapleQuest.getInstance(122700)).setCustomData(customData);
            getPlayer().dropMessage(1, "����" + days + "��������ɹ���");
        }
    }

    public String ���ߵ���(int itemid) {
        int rate = getClient().getChannelServer().getDropRate();
        MapleMonster mob = MapleLifeFactory.getMonster(itemid);
        if (MapleLifeFactory.getMonster(itemid) != null) {
            if (mob.getStats().isBoss()) {
                rate = getClient().getChannelServer().getBossDropRate();
            }
        }
        List ranks = MapleMonsterInformationProvider.getInstance().retrieveDrop(itemid);
        if ((ranks != null) && (ranks.size() > 0)) {
            int num = 0;
            int itemId = 0;
            int ch = 0;

            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < ranks.size(); i++) {
                MonsterDropEntry de = (MonsterDropEntry) ranks.get(i);
                if ((de.chance > 0) && ((de.questid <= 0) || ((de.questid > 0) && (MapleQuest.getInstance(de.questid).getName().length() > 0)))) {
                    itemId = de.itemId;
                    if (!ii.itemExists(itemId)) {
                        continue;
                    }
                    if (num == 0) {
                        name.append(" #r#v").append(itemid).append("##k �ĵ������:\r\n");
                        name.append("\r\n");
                    }
                    String namez = new StringBuilder().append("#z").append(itemId).append("#").toString();
                    if (itemId == 0) {
                        itemId = 4031041;
                        namez = new StringBuilder().append(de.Minimum * getClient().getChannelServer().getMesoRate()).append(" - ").append(de.Maximum * getClient().getChannelServer().getMesoRate()).append(" �Ľ��").toString();
                    }
                    ch = de.chance * rate;
                    //  if (getPlayer().isAdmin()) {
                    name.append(num + 1).append(".  #b#o").append(itemId).append("#").append(namez).append("#k ����: ").append(Integer.valueOf(ch >= 999999 ? 1000000 : ch).doubleValue() / 10000.0D).append(" %").append((de.questid > 0) && (MapleQuest.getInstance(de.questid).getName().length() > 0) ? new StringBuilder().append("��Ҫ��������: ").append(MapleQuest.getInstance(de.questid).getName()).toString() : "").append("\r\n");
                    // } else {
                    //     name.append(num + 1).append(") #v").append(itemId).append("#").append(namez).append((de.questid > 0) && (MapleQuest.getInstance(de.questid).getName().length() > 0) ? new StringBuilder().append("��Ҫ��������: ").append(MapleQuest.getInstance(de.questid).getName()).toString() : "").append("\r\n");
                    // }
                    num++;
                }
            }
            if (name.length() > 0) {
                return name.toString();
            }
        }
        return "û���ҵ�������ߵĵ�����Ϣ��";
    }

    /**
     * ��ѯ�������
     *
     * @param mobId
     * @return
     */
    public String checkDrop(int mobId) {
        int rate = getClient().getChannelServer().getDropRate();
        MapleMonster mob = MapleLifeFactory.getMonster(mobId);
        if (MapleLifeFactory.getMonster(mobId) != null) {
            if (mob.getStats().isBoss()) {
                rate = getClient().getChannelServer().getBossDropRate();
            }
        }
        List ranks = MapleMonsterInformationProvider.getInstance().retrieveDrop(mobId);
        if ((ranks != null) && (ranks.size() > 0)) {
            int num = 0;
            int itemId = 0;
            int ch = 0;

            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < ranks.size(); i++) {
                MonsterDropEntry de = (MonsterDropEntry) ranks.get(i);
                if ((de.chance > 0) && ((de.questid <= 0) || ((de.questid > 0) && (MapleQuest.getInstance(de.questid).getName().length() > 0)))) {
                    itemId = de.itemId;
                    if (!ii.itemExists(itemId)) {
                        continue;
                    }
                    if (num == 0) {
                        name.append("#r" + MapleParty.�������� + "#k#k #b#o").append(mobId).append("##k �ı�����ϸΪ:\r\n\r\n");
                        name.append("\r\n");
                    }
                    String namez = new StringBuilder().append("#z").append(itemId).append("#").toString();
                    if (itemId == 0) {
                        itemId = 4031041;
                        namez = new StringBuilder().append(de.Minimum * getClient().getChannelServer().getMesoRate()).append(" - ").append(de.Maximum * getClient().getChannelServer().getMesoRate()).append(" �Ľ��").toString();
                    }
                    ch = de.chance * rate;
                    if (getPlayer().isAdmin()) {
                        name.append(num + 1).append(".  #b#v").append(itemId).append("#").append(namez).append("#k ����: ").append(Integer.valueOf(ch >= 999999 ? 1000000 : ch).doubleValue() / 10000.0D).append(" %").append((de.questid > 0) && (MapleQuest.getInstance(de.questid).getName().length() > 0) ? new StringBuilder().append("��Ҫ��������: ").append(MapleQuest.getInstance(de.questid).getName()).toString() : "").append("\r\n");
                    } else {
                        name.append(num + 1).append(".  #b#v").append(itemId).append("#").append(namez).append((de.questid > 0) && (MapleQuest.getInstance(de.questid).getName().length() > 0) ? new StringBuilder().append("��Ҫ��������: ").append(MapleQuest.getInstance(de.questid).getName()).toString() : "").append("\r\n");
                    }
                    num++;
                }
            }
            if (name.length() > 0) {
                return name.toString();
            }
        }
        return "û���ҵ��������ı������ݡ�";
    }

    public String checkMapDrop() {
        List ranks = new ArrayList(MapleMonsterInformationProvider.getInstance().getGlobalDrop());
        int mapid = this.c.getPlayer().getMap().getId();
        int cashServerRate = getClient().getChannelServer().getCashRate();
        int globalServerRate = 1;
        if ((ranks != null) && (ranks.size() > 0)) {
            int num = 0;

            StringBuilder name = new StringBuilder();
            for (int i = 0; i < ranks.size(); i++) {
                MonsterGlobalDropEntry de = (MonsterGlobalDropEntry) ranks.get(i);
                //if ((de.continent < 0) || ((de.continent < 10) && (mapid / 100000000 == de.continent)) || ((de.continent < 100) && (mapid / 10000000 == de.continent)) || ((de.continent < 1000) && (mapid / 1000000 == de.continent))) {

                int itemId = de.itemId;
                if (num == 0) {
                    name.append("#r" + MapleParty.�������� + "#kȫ�ֱ���Ԥ����\r\n\r\n");
                    /*name.append("��ǰ��ͼ #r").append(mapid).append("#k - #m").append(mapid).append("# ��ȫ�ֱ���Ϊ:");
                        name.append("\r\n--------------------------------------\r\n");*/
                }

                String names = new StringBuilder().append("#z").append(itemId).append("#").toString();
                if ((itemId == 0) && (cashServerRate != 0)) {
                    itemId = 4031041;
                    names = new StringBuilder().append(de.Minimum * cashServerRate).append(" - ").append(de.Maximum * cashServerRate).append(" �ĵ��þ�").toString();
                }
                int chance = de.chance * globalServerRate;
                if (getPlayer().isAdmin()) {
                    name.append(num + 1).append(".  #b#v").append(itemId).append("#").append(names).append("#k ����: ").append(Integer.valueOf(chance >= 999999 ? 1000000 : chance).doubleValue() / 10000.0D).append(" %").append((de.questid > 0) && (MapleQuest.getInstance(de.questid).getName().length() > 0) ? new StringBuilder().append("��Ҫ��������: ").append(MapleQuest.getInstance(de.questid).getName()).toString() : "").append("\r\n");
                } else {
                    name.append(num + 1).append(".  #b#v").append(itemId).append("#").append(names).append((de.questid > 0) && (MapleQuest.getInstance(de.questid).getName().length() > 0) ? new StringBuilder().append("��Ҫ��������: ").append(MapleQuest.getInstance(de.questid).getName()).toString() : "").append("\r\n");
                }
                num++;
                //}
            }
            if (name.length() > 0) {
                return name.toString();
            }
        }
        return "ȫ�ֱ�������Ϊ�ա�";
    }

    public String ��ѯ��Ʒ����(int account) {
        List ranks = new ArrayList(MapleMonsterInformationProvider.getInstance().getGlobalDrop());
        int mapid = this.c.getPlayer().getMap().getId();
        int cashServerRate = getClient().getChannelServer().getCashRate();
        int globalServerRate = 1;
        if ((ranks != null) && (ranks.size() > 0)) {
            int num = 0;

            StringBuilder name = new StringBuilder();
            for (int i = 0; i < ranks.size(); i++) {
                MonsterGlobalDropEntry de = (MonsterGlobalDropEntry) ranks.get(i);
                //if ((de.continent < 0) || ((de.continent < 10) && (mapid / 100000000 == de.continent)) || ((de.continent < 100) && (mapid / 10000000 == de.continent)) || ((de.continent < 1000) && (mapid / 1000000 == de.continent))) {

                int itemId = de.itemId;
                if (num == 0) {
                    name.append("#r" + MapleParty.�������� + "#kȫ�ֱ���Ԥ����\r\n\r\n");
                }

                String names = new StringBuilder().append("#z").append(itemId).append("#").toString();
                int chance = de.chance * globalServerRate;
                if (getPlayer().isAdmin()) {
                    name.append(num + 1).append(".  #b#v").append(itemId).append("#").append(names).append("#k ����: ").append(Integer.valueOf(chance >= 999999 ? 1000000 : chance).doubleValue() / 10000.0D).append(" %").append((de.questid > 0) && (MapleQuest.getInstance(de.questid).getName().length() > 0) ? new StringBuilder().append("��Ҫ��������: ").append(MapleQuest.getInstance(de.questid).getName()).toString() : "").append("\r\n");
                } else {
                    name.append(num + 1).append(".  #b#v").append(itemId).append("#").append(names).append((de.questid > 0) && (MapleQuest.getInstance(de.questid).getName().length() > 0) ? new StringBuilder().append("��Ҫ��������: ").append(MapleQuest.getInstance(de.questid).getName()).toString() : "").append("\r\n");
                }
                num++;
            }
            if (name.length() > 0) {
                return name.toString();
            }
        }
        return "δ��ѯ�����䡣";
    }

    public int getzb() {//�ж��˺ŵ�ID��
        int money = 0;
        try {
            int cid = getPlayer().getAccountID();
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement limitCheck = con.prepareStatement("SELECT * FROM accounts WHERE id=" + cid + "");
            ResultSet rs = limitCheck.executeQuery();
            if (rs.next()) {
                money = rs.getInt("money");
            }
            limitCheck.close();
            rs.close();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return money;
    }

    public int ��ȡ��ǰ����() {

        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

    }

    public void setzb(int slot) {
        try {
            int cid = getPlayer().getAccountID();
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("UPDATE accounts SET money =money+ " + slot + " WHERE id = " + cid + "")) {
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }

    public int getmoneyb() {
        int moneyb = 0;
        try {
            int cid = getPlayer().getAccountID();
            Connection con = DatabaseConnection.getConnection();
            ResultSet rs;
            try (PreparedStatement limitCheck = con.prepareStatement("SELECT * FROM accounts WHERE id=" + cid + "")) {
                rs = limitCheck.executeQuery();
                if (rs.next()) {
                    moneyb = rs.getInt("moneyb");
                }
            }
            rs.close();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return moneyb;
    }

    public void setmoneyb(int slot) {
        try {
            int cid = getPlayer().getAccountID();
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE accounts SET moneyb =moneyb+ " + slot + " WHERE id = " + cid + "");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }

    public MapleMapFactory getMapFactory() {
        return getClient().getChannelServer().getMapFactory();
    }

    public void warpBack(int mid, final int retmap, final int time) {/////��ʱ����

        MapleMap warpMap = c.getChannelServer().getMapFactory().getMap(mid);
        c.getPlayer().changeMap(warpMap, warpMap.getPortal(0));
        c.sendPacket(MaplePacketCreator.getClock(time));
        Timer.EventTimer.getInstance().schedule(new Runnable() {

            @Override
            public void run() {
                MapleMap warpMap = c.getChannelServer().getMapFactory().getMap(retmap);
                if (c.getPlayer() != null) {
                    c.sendPacket(MaplePacketCreator.stopClock());
                    c.getPlayer().changeMap(warpMap, warpMap.getPortal(0));
                    //c.getPlayer().dropMessage(6, "����Ŀ�ĵ�");
                }
            }
        }, 1000 * time);
    }

    public void ��ʱ����(int mid, final int retmap, final int time) {/////��ʱ����

        MapleMap warpMap = c.getChannelServer().getMapFactory().getMap(mid);
        c.getPlayer().changeMap(warpMap, warpMap.getPortal(0));
        c.sendPacket(MaplePacketCreator.getClock(time));
        Timer.EventTimer.getInstance().schedule(new Runnable() {

            @Override
            public void run() {
                MapleMap warpMap = c.getChannelServer().getMapFactory().getMap(retmap);
                if (c.getPlayer() != null) {
                    c.sendPacket(MaplePacketCreator.stopClock());
                    c.getPlayer().changeMap(warpMap, warpMap.getPortal(0));
                    //c.getPlayer().dropMessage(6, "����Ŀ�ĵ�");
                }
            }
        }, 1000 * time);
    }

    public void ��Ʒ����() {

    }

//        public void ��Ʒ����() {
//        client.inventory.Item item1 = new client.inventory.Item(2000000, (byte) 0, (short) 1);
//        client.inventory.Item item2 = new client.inventory.Item(2000001, (byte) 0, (short) 1);
//        client.inventory.Item item3 = new client.inventory.Item(2000002, (byte) 0, (short) 1);
//        client.inventory.Item item4 = new client.inventory.Item(2000003, (byte) 0, (short) 1);
//        client.inventory.Item item5 = new client.inventory.Item(2000004, (byte) 0, (short) 1);
//        client.inventory.Item item6 = new client.inventory.Item(2000005, (byte) 0, (short) 1);
//        client.inventory.Item item7 = new client.inventory.Item(2000006, (byte) 0, (short) 1);
//        c.getPlayer().getMap().��Ʒ����(c.getPlayer(), c.getPlayer(), item1, new Point(c.getPlayer().getPosition().x, c.getPlayer().getPosition().y), false, false);
//        c.getPlayer().getMap().��Ʒ����(c.getPlayer(), c.getPlayer(), item2, new Point(c.getPlayer().getPosition().x + 20, c.getPlayer().getPosition().y), false, false);
//        c.getPlayer().getMap().��Ʒ����(c.getPlayer(), c.getPlayer(), item3, new Point(c.getPlayer().getPosition().x - 20, c.getPlayer().getPosition().y), false, false);
//        c.getPlayer().getMap().��Ʒ����(c.getPlayer(), c.getPlayer(), item4, new Point(c.getPlayer().getPosition().x + 40, c.getPlayer().getPosition().y), false, false);
//        c.getPlayer().getMap().��Ʒ����(c.getPlayer(), c.getPlayer(), item5, new Point(c.getPlayer().getPosition().x - 40, c.getPlayer().getPosition().y), false, false);
//        c.getPlayer().getMap().��Ʒ����(c.getPlayer(), c.getPlayer(), item6, new Point(c.getPlayer().getPosition().x + 60, c.getPlayer().getPosition().y), false, false);
//        c.getPlayer().getMap().��Ʒ����(c.getPlayer(), c.getPlayer(), item7, new Point(c.getPlayer().getPosition().x - 60, c.getPlayer().getPosition().y), false, false);
//    }
    public void �ж��Ƿ���() {
        c.getPlayer().getMarriageId();
    }

    public void �ٻ�����(int a) {
        c.getPlayer().�ٻ�����(a);
    }

    public void �������() {
        c.getPlayer().getName();
    }

    public void warpMapWithClock(final int mid, int seconds) {
        c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.getClock(seconds));
        Timer.MapTimer.getInstance().schedule(new Runnable() {

            public void run() {
                if (c.getPlayer() != null) {
                    for (MapleCharacter chr : c.getPlayer().getMap().getCharactersThreadsafe()) {
                        chr.changeMap(mid);
                    }
                }
            }
        }, seconds * 1000);
    }

    public void �������а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().����()));
    }

    public void ��ʿ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().��ʿ()));
    }

    public void Ӣ�����а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().Ӣ��()));
    }

    public void ׼��ʿ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().׼��ʿ()));
    }

    public void ��ʿ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().��ʿ()));
    }

    public void ʥ��ʿ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().ʥ��ʿ()));
    }

    public void ǹսʿ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().ǹսʿ()));
    }

    public void ����ʿ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().����ʿ()));
    }

    public void ����ʿ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().����ʿ()));
    }

    public void �𶾷�ʦ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().�𶾷�ʦ()));
    }

    public void ����ʦ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().����ʦ()));
    }

    public void ��ħ��ʦ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().��ħ��ʦ()));
    }

    public void ���׷�ʦ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().���׷�ʦ()));
    }

    public void ������ʦ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().������ʦ()));
    }

    public void ����ħ��ʦ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().����ħ��ʦ()));
    }

    public void ��ʦ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().��ʦ()));
    }

    public void ��ʦ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().��ʦ()));
    }

    public void �������а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().����()));
    }

    public void �������а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().����()));
    }

    public void �������а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().����()));
    }

    public void ���������а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().������()));
    }

    public void �������а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().����()));
    }

    public void �������а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().����()));
    }

    public void �������а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().����()));
    }

    public void �̿����а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().�̿�()));
    }

    public void ��Ӱ�����а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().��Ӱ��()));
    }

    public void ��ʿ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().��ʿ()));
    }

    public void �������а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().����()));
    }

    public void ���п����а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().���п�()));
    }

    public void �������а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().����()));
    }

    public void ȭ�����а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().ȭ��()));
    }

    public void ��ʿ���а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().��ʿ()));
    }

    public void ���ӳ����а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().���ӳ�()));
    }

    public void ��ǹ�����а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().��ǹ��()));
    }

    public void �����а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().��()));
    }

    public void �������а�() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().����()));
    }

    public void showlvl() {//�ȼ���
        c.sendPacket(MaplePacketCreator.showlevelRanks(npc, MapleGuildRanking.getInstance().getLevelRank()));
    }

    public void showmeso() {//��Ұ�
        c.sendPacket(MaplePacketCreator.showmesoRanks(npc, MapleGuildRanking.getInstance().getMesoRank()));
    }

    public void ShowMarrageEffect() {
        c.getPlayer().getMap().broadcastMessage((MaplePacketCreator.sendMarrageEffect()));
    }

//1���/2����/3���/4����
    public int ��ȫ�������(int ����, int ����) {
        int count = 0;

        try {
            if (���� <= 0 || ���� <= 0) {
                return 0;
            }

            if (���� == 1 || ���� == 2) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.modifyCSPoints(����, ����);
                        String cash = null;
                        if (���� == 1) {
                            cash = "���";
                        } else if (���� == 2) {
                            cash = "���þ�";
                        }
                        //mch.startMapEffect("����Ա����" + ���� + cash + "�����ߵ�������ң����л����Ա�ɣ�", 5121009);
                        count++;
                    }
                }
            } else if (���� == 3) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        // mch.modifyCSPoints(����, ����);
                        mch.gainMeso(����, true);
                        //mch.startMapEffect("����Ա����" + ���� + "ð�ձҸ����ߵ�������ң����л����Ա�ɣ�", 5121009);
                        count++;
                    }
                }
            } else if (���� == 4) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.gainExp(����, true, false, true);
                        //mch.startMapEffect("����Ա����" + ���� + "��������ߵ�������ң����л����Ա�ɣ�", 5121009);
                        count++;
                    }
                }
            }
        } catch (Exception e) {
            c.getPlayer().dropMessage("��ȫ����������" + e.getMessage());
        }

        return count;
    }

//1���/2����/3���/4����
    public int ����ǰ��ͼ�����(int ����, int ����) {
        int count = 0;
        int mapId = c.getPlayer().getMapId();
        try {
            if (���� <= 0 || ���� <= 0) {
                return 0;
            }

            if (���� == 1 || ���� == 2) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        if (mch.getMapId() != mapId) {
                            continue;
                        }
                        mch.modifyCSPoints(����, ����);
                        String cash = null;
                        if (���� == 1) {
                            cash = "���";
                        } else if (���� == 2) {
                            cash = "���þ�";
                        }
                        //mch.startMapEffect("����Ա����" + ���� + cash + "�����ߵ�������ң����л����Ա�ɣ�", 5121009);
                        count++;
                    }
                }
            } else if (���� == 3) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        if (mch.getMapId() != mapId) {
                            continue;
                        }
                        mch.gainMeso(����, true);
                        //mch.startMapEffect("����Ա����" + ���� + "ð�ձҸ����ߵ�������ң����л����Ա�ɣ�", 5121009);
                        count++;
                    }
                }
            } else if (���� == 4) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        if (mch.getMapId() != mapId) {
                            continue;
                        }
                        mch.gainExp(����, true, false, true);
                        //mch.startMapEffect("����Ա����" + ���� + "��������ߵ�������ң����л����Ա�ɣ�", 5121009);
                        count++;
                    }
                }
            }
        } catch (Exception e) {
            c.getPlayer().dropMessage("����ǰ��ͼ��������" + e.getMessage());
        }

        return count;
    }

//1���/2����/3���/4����
    public int ����ǰƵ�������(int ����, int ����) {
        int count = 0;
        int chlId = c.getPlayer().getMap().getChannel();

        try {
            if (���� <= 0 || ���� <= 0) {
                return 0;
            }
            if (���� == 1 || ���� == 2) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    if (cserv1.getChannel() != chlId) {
                        continue;
                    }
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.modifyCSPoints(����, ����);
                        String cash = null;
                        if (���� == 1) {
                            cash = "���";
                        } else if (���� == 2) {
                            cash = "���þ�";
                        }
                        //mch.startMapEffect("����Ա����" + ���� + cash + "�����ߵ�������ң����л����Ա�ɣ�", 5121009);
                        count++;
                    }
                }
            } else if (���� == 3) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    if (cserv1.getChannel() != chlId) {
                        continue;
                    }
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        // mch.modifyCSPoints(����, ����);
                        mch.gainMeso(����, true);
                        //mch.startMapEffect("����Ա����" + ���� + "ð�ձҸ����ߵ�������ң����л����Ա�ɣ�", 5121009);
                        count++;
                    }
                }
            } else if (���� == 4) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    if (cserv1.getChannel() != chlId) {
                        continue;
                    }
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.gainExp(����, true, false, true);
                        //mch.startMapEffect("����Ա����" + ���� + "��������ߵ�������ң����л����Ա�ɣ�", 5121009);
                        count++;
                    }
                }
            }
        } catch (Exception e) {
            c.getPlayer().dropMessage("����ǰƵ����������" + e.getMessage());
        }

        return count;
    }

    public int ��ȫ������Ʒ(int ��ƷID, int ����, int ����, int ����, int ����, int ����, int HP, int MP, int �ɼӾ����, String ����������, int ����ʱ��, String �Ƿ���Խ���, int ������, int ħ����, int �������, int ħ������) {
        int count = 0;

        try {
            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            MapleInventoryType type = GameConstants.getInventoryType(��ƷID);

            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (���� >= 0) {
                        if (!MapleInventoryManipulator.checkSpace(mch.getClient(), ��ƷID, ����, "")) {
                            return 0;
                        }
                        if (type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(��ƷID) && !GameConstants.isBullet(��ƷID)
                                || type.equals(MapleInventoryType.CASH) && ��ƷID >= 5000000 && ��ƷID <= 5000100) {
                            final Equip item = (Equip) (ii.getEquipById(��ƷID));
                            if (ii.isCash(��ƷID)) {
                                item.setUniqueId(1);
                            }
                            if (���� > 0 && ���� <= 32767) {
                                item.setStr((short) (����));
                            }
                            if (���� > 0 && ���� <= 32767) {
                                item.setDex((short) (����));
                            }
                            if (���� > 0 && ���� <= 32767) {
                                item.setInt((short) (����));
                            }
                            if (���� > 0 && ���� <= 32767) {
                                item.setLuk((short) (����));
                            }
                            if (������ > 0 && ������ <= 32767) {
                                item.setWatk((short) (������));
                            }
                            if (ħ���� > 0 && ħ���� <= 32767) {
                                item.setMatk((short) (ħ����));
                            }
                            if (������� > 0 && ������� <= 32767) {
                                item.setWdef((short) (�������));
                            }
                            if (ħ������ > 0 && ħ������ <= 32767) {
                                item.setMdef((short) (ħ������));
                            }
                            if (HP > 0 && HP <= 30000) {
                                item.setHp((short) (HP));
                            }
                            if (MP > 0 && MP <= 30000) {
                                item.setMp((short) (MP));
                            }
                            if ("���Խ���".equals(�Ƿ���Խ���)) {
                                byte flag = item.getFlag();
                                if (item.getType() == MapleInventoryType.EQUIP.getType()) {
                                    flag |= ItemFlag.KARMA_EQ.getValue();
                                } else {
                                    flag |= ItemFlag.KARMA_USE.getValue();
                                }
                                item.setFlag(flag);
                            }
                            if (����ʱ�� > 0) {
                                item.setExpiration(System.currentTimeMillis() + (����ʱ�� * 24 * 60 * 60 * 1000));
                            }
                            if (�ɼӾ���� > 0) {
                                item.setUpgradeSlots((byte) (�ɼӾ����));
                            }
                            if (���������� != null) {
                                item.setOwner(����������);
                            }
                            final String name = ii.getName(��ƷID);
                            if (��ƷID / 10000 == 114 && name != null && name.length() > 0) { //medal
                                final String msg = "���ѻ�óƺ� <" + name + ">";
                                mch.getClient().getPlayer().dropMessage(5, msg);
                            }
                            MapleInventoryManipulator.addbyItem(mch.getClient(), item.copy());
                        } else {
                            MapleInventoryManipulator.addById(mch.getClient(), ��ƷID, (short) ����, "", null, ����ʱ��, (byte) 0);
                        }
                    } else {
                        MapleInventoryManipulator.removeById(mch.getClient(), GameConstants.getInventoryType(��ƷID), ��ƷID, -����, true, false);
                    }
                    mch.getClient().sendPacket(MaplePacketCreator.getShowItemGain(��ƷID, (short) ����, true));

                    count++;
                }
            }
        } catch (Exception e) {
            c.getPlayer().dropMessage("��ȫ������Ʒ����" + e.getMessage());
        }

        return count;
    }

    public int ����ǰ��ͼ����Ʒ(int ��ƷID, int ����, int ����, int ����, int ����, int ����, int HP, int MP, int �ɼӾ����, String ����������, int ����ʱ��, String �Ƿ���Խ���, int ������, int ħ����, int �������, int ħ������) {
        int count = 0;
        int mapId = c.getPlayer().getMapId();

        try {
            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            MapleInventoryType type = GameConstants.getInventoryType(��ƷID);

            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (mch.getMapId() != mapId) {
                        continue;
                    }
                    if (���� >= 0) {
                        if (!MapleInventoryManipulator.checkSpace(mch.getClient(), ��ƷID, ����, "")) {
                            return 0;
                        }
                        if (type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(��ƷID) && !GameConstants.isBullet(��ƷID)
                                || type.equals(MapleInventoryType.CASH) && ��ƷID >= 5000000 && ��ƷID <= 5000100) {
                            final Equip item = (Equip) (ii.getEquipById(��ƷID));
                            if (ii.isCash(��ƷID)) {
                                item.setUniqueId(1);
                            }
                            if (���� > 0 && ���� <= 32767) {
                                item.setStr((short) (����));
                            }
                            if (���� > 0 && ���� <= 32767) {
                                item.setDex((short) (����));
                            }
                            if (���� > 0 && ���� <= 32767) {
                                item.setInt((short) (����));
                            }
                            if (���� > 0 && ���� <= 32767) {
                                item.setLuk((short) (����));
                            }
                            if (������ > 0 && ������ <= 32767) {
                                item.setWatk((short) (������));
                            }
                            if (ħ���� > 0 && ħ���� <= 32767) {
                                item.setMatk((short) (ħ����));
                            }
                            if (������� > 0 && ������� <= 32767) {
                                item.setWdef((short) (�������));
                            }
                            if (ħ������ > 0 && ħ������ <= 32767) {
                                item.setMdef((short) (ħ������));
                            }
                            if (HP > 0 && HP <= 30000) {
                                item.setHp((short) (HP));
                            }
                            if (MP > 0 && MP <= 30000) {
                                item.setMp((short) (MP));
                            }
                            if ("���Խ���".equals(�Ƿ���Խ���)) {
                                byte flag = item.getFlag();
                                if (item.getType() == MapleInventoryType.EQUIP.getType()) {
                                    flag |= ItemFlag.KARMA_EQ.getValue();
                                } else {
                                    flag |= ItemFlag.KARMA_USE.getValue();
                                }
                                item.setFlag(flag);
                            }
                            if (����ʱ�� > 0) {
                                item.setExpiration(System.currentTimeMillis() + (����ʱ�� * 24 * 60 * 60 * 1000));
                            }
                            if (�ɼӾ���� > 0) {
                                item.setUpgradeSlots((byte) (�ɼӾ����));
                            }
                            if (���������� != null) {
                                item.setOwner(����������);
                            }
                            final String name = ii.getName(��ƷID);
                            if (��ƷID / 10000 == 114 && name != null && name.length() > 0) { //medal
                                final String msg = "���ѻ�óƺ� <" + name + ">";
                                mch.getClient().getPlayer().dropMessage(5, msg);
                            }
                            MapleInventoryManipulator.addbyItem(mch.getClient(), item.copy());
                        } else {
                            MapleInventoryManipulator.addById(mch.getClient(), ��ƷID, (short) ����, "", null, ����ʱ��, (byte) 0);
                        }
                    } else {
                        MapleInventoryManipulator.removeById(mch.getClient(), GameConstants.getInventoryType(��ƷID), ��ƷID, -����, true, false);
                    }
                    mch.getClient().sendPacket(MaplePacketCreator.getShowItemGain(��ƷID, (short) ����, true));

                    count++;
                }
            }
        } catch (Exception e) {
            c.getPlayer().dropMessage("����ǰ��ͼ����Ʒ����" + e.getMessage());
        }

        return count;
    }

    public int ����ǰƵ������Ʒ(int ��ƷID, int ����, int ����, int ����, int ����, int ����, int HP, int MP, int �ɼӾ����, String ����������, int ����ʱ��, String �Ƿ���Խ���, int ������, int ħ����, int �������, int ħ������) {
        int count = 0;
        int chlId = c.getPlayer().getMap().getChannel();

        try {
            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            MapleInventoryType type = GameConstants.getInventoryType(��ƷID);

            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                if (cserv1.getChannel() != chlId) {
                    continue;
                }
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (���� >= 0) {
                        if (!MapleInventoryManipulator.checkSpace(mch.getClient(), ��ƷID, ����, "")) {
                            return 0;
                        }
                        if (type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(��ƷID) && !GameConstants.isBullet(��ƷID)
                                || type.equals(MapleInventoryType.CASH) && ��ƷID >= 5000000 && ��ƷID <= 5000100) {
                            final Equip item = (Equip) (ii.getEquipById(��ƷID));
                            if (ii.isCash(��ƷID)) {
                                item.setUniqueId(1);
                            }
                            if (���� > 0 && ���� <= 32767) {
                                item.setStr((short) (����));
                            }
                            if (���� > 0 && ���� <= 32767) {
                                item.setDex((short) (����));
                            }
                            if (���� > 0 && ���� <= 32767) {
                                item.setInt((short) (����));
                            }
                            if (���� > 0 && ���� <= 32767) {
                                item.setLuk((short) (����));
                            }
                            if (������ > 0 && ������ <= 32767) {
                                item.setWatk((short) (������));
                            }
                            if (ħ���� > 0 && ħ���� <= 32767) {
                                item.setMatk((short) (ħ����));
                            }
                            if (������� > 0 && ������� <= 32767) {
                                item.setWdef((short) (�������));
                            }
                            if (ħ������ > 0 && ħ������ <= 32767) {
                                item.setMdef((short) (ħ������));
                            }
                            if (HP > 0 && HP <= 30000) {
                                item.setHp((short) (HP));
                            }
                            if (MP > 0 && MP <= 30000) {
                                item.setMp((short) (MP));
                            }
                            if ("���Խ���".equals(�Ƿ���Խ���)) {
                                byte flag = item.getFlag();
                                if (item.getType() == MapleInventoryType.EQUIP.getType()) {
                                    flag |= ItemFlag.KARMA_EQ.getValue();
                                } else {
                                    flag |= ItemFlag.KARMA_USE.getValue();
                                }
                                item.setFlag(flag);
                            }
                            if (����ʱ�� > 0) {
                                item.setExpiration(System.currentTimeMillis() + (����ʱ�� * 24 * 60 * 60 * 1000));
                            }
                            if (�ɼӾ���� > 0) {
                                item.setUpgradeSlots((byte) (�ɼӾ����));
                            }
                            if (���������� != null) {
                                item.setOwner(����������);
                            }
                            final String name = ii.getName(��ƷID);
                            if (��ƷID / 10000 == 114 && name != null && name.length() > 0) { //medal
                                final String msg = "���ѻ�óƺ� <" + name + ">";
                                mch.getClient().getPlayer().dropMessage(5, msg);
                            }
                            MapleInventoryManipulator.addbyItem(mch.getClient(), item.copy());
                        } else {
                            MapleInventoryManipulator.addById(mch.getClient(), ��ƷID, (short) ����, "", null, ����ʱ��, (byte) 0);
                        }
                    } else {
                        MapleInventoryManipulator.removeById(mch.getClient(), GameConstants.getInventoryType(��ƷID), ��ƷID, -����, true, false);
                    }
                    mch.getClient().sendPacket(MaplePacketCreator.getShowItemGain(��ƷID, (short) ����, true));

                    count++;
                }
            }
        } catch (Exception e) {
            c.getPlayer().dropMessage("����ǰƵ������Ʒ����" + e.getMessage());
        }

        return count;
    }

    public int �Ƿ�����֤���() {
        try {
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as DATA FROM ��Ա WHERE name = ?")) {
                ps.setString(1, getName());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("DATA");
                    }
                }
            }
        } catch (SQLException Ex) {
            System.err.println("��ѯ��֤��ҳ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return 0;
    }

    public int ǿ��() {
        try {
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as DATA FROM ǿ����Ա WHERE name = ?")) {
                ps.setString(1, getName());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("DATA");
                    }
                }
            }
        } catch (SQLException Ex) {
            System.err.println("��ѯ��֤��ҳ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return 0;
    }

    public int ����() {
        try {
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as DATA FROM ���ֻ�Ա WHERE name = ?")) {
                ps.setString(1, getName());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("DATA");
                    }
                }
            }
        } catch (SQLException Ex) {
            System.err.println("��ѯ��֤��ҳ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return 0;
    }

    public int ����װ��() {
        try {
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as DATA FROM ����װ�� WHERE name = ?")) {
                ps.setString(1, getName());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("DATA");
                    }
                }
            }
        } catch (SQLException Ex) {
            System.err.println("��ѯ��֤��ҳ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return 0;
    }

    public int ������() {
        try {
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as DATA FROM ������ WHERE name = ?")) {
                ps.setString(1, getName());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("DATA");
                    }
                }
            }
        } catch (SQLException Ex) {
            System.err.println("��ѯ���������� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return 0;
    }

    public boolean �жϵ�ǰ��ͼ�Ƿ��ѽ��ô˽ű�(int scriptId) {
        try {
            int mapId = c.getPlayer().getMapId();
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as DATA FROM ���ýű���ͼ WHERE scriptId = ? AND mapId = ?")) {
                ps.setInt(1, scriptId);
                ps.setInt(2, mapId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("DATA") > 0;
                    }
                }
            }
        } catch (SQLException Ex) {
            System.err.println("�жϵ�ǰ��ͼ�Ƿ��ѽ��ô˽ű����� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return false;
    }

    public int ��֤����() {
        try {
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as DATA FROM ��֤���� WHERE name = ?")) {
                ps.setString(1, getName());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("DATA");
                    }
                }
            }
        } catch (SQLException Ex) {
            System.err.println("��ѯ��֤�������� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return 0;
    }

    public int ���ýű���ͼ() {
        try {
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as DATA FROM ���ýű���ͼ WHERE name = ?")) {
                ps.setString(1, getmapId());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("DATA");
                    }
                }
            }
        } catch (SQLException Ex) {
            System.err.println("��ѯ��֤��ҳ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return 0;
    }

    public int ���͵�ǰ��ͼ�����˵�ָ����ͼ(int destMapId, Boolean includeSelf) {
        int count = 0;
        int myMapId = c.getPlayer().getMapId();
        int myId = c.getPlayer().getId();

        try {
            final MapleMap tomap = getMapFactory().getMap(destMapId);
            final MapleMap frommap = getMapFactory().getMap(myMapId);
            List<MapleCharacter> list = frommap.getCharactersThreadsafe();
            if (tomap != null && frommap != null && list != null && frommap.getCharactersSize() > 0) {
                for (MapleMapObject mmo : list) {
                    MapleCharacter chr = (MapleCharacter) mmo;
                    if (chr.getId() == myId) { // �Լ�
                        if (includeSelf) {
                            chr.changeMap(tomap, tomap.getPortal(0));
                            count++;
                        }
                    } else {
                        chr.changeMap(tomap, tomap.getPortal(0));
                        count++;
                    }
                }
            }
        } catch (Exception e) {
            c.getPlayer().dropMessage("���͵�ǰ��ͼ�����˵�ָ����ͼ����" + e.getMessage());
        }

        return count;
    }

    public int ɱ����ǰ��ͼ������(Boolean includeSelf) {
        int count = 0;
        int myMapId = c.getPlayer().getMapId();
        int myId = c.getPlayer().getId();

        try {
            final MapleMap frommap = getMapFactory().getMap(myMapId);
            List<MapleCharacter> list = frommap.getCharactersThreadsafe();
            if (frommap != null && list != null && frommap.getCharactersSize() > 0) {
                for (MapleMapObject mmo : list) {
                    if (mmo != null) {
                        MapleCharacter chr = (MapleCharacter) mmo;
                        if (chr.getId() == myId) { // �Լ�
                            if (includeSelf) {
                                chr.setHp(0);
                                chr.updateSingleStat(MapleStat.HP, 0);
                                count++;
                            }
                        } else {
                            chr.setHp(0);
                            chr.updateSingleStat(MapleStat.HP, 0);
                            count++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            c.getPlayer().dropMessage("ɱ����ǰ��ͼ�����˳���" + e.getMessage());
        }

        return count;
    }

    public int ���ǰ��ͼ������(Boolean includeSelf) {
        int count = 0;
        int myMapId = c.getPlayer().getMapId();
        int myId = c.getPlayer().getId();

        try {
            final MapleMap frommap = getMapFactory().getMap(myMapId);
            List<MapleCharacter> list = frommap.getCharactersThreadsafe();
            if (frommap != null && list != null && frommap.getCharactersSize() > 0) {
                for (MapleMapObject mmo : list) {
                    if (mmo != null) {
                        MapleCharacter chr = (MapleCharacter) mmo;
                        if (chr.getId() == myId) { // �Լ�
                            if (includeSelf) {
                                chr.getStat().setHp(chr.getStat().getMaxHp());
                                chr.updateSingleStat(MapleStat.HP, chr.getStat().getMaxHp());
                                chr.getStat().setMp(chr.getStat().getMaxMp());
                                chr.updateSingleStat(MapleStat.MP, chr.getStat().getMaxMp());
                                chr.dispelDebuffs();
                                count++;
                            }
                        } else {
                            chr.getStat().setHp(chr.getStat().getMaxHp());
                            chr.updateSingleStat(MapleStat.HP, chr.getStat().getMaxHp());
                            chr.getStat().setMp(chr.getStat().getMaxMp());
                            chr.updateSingleStat(MapleStat.MP, chr.getStat().getMaxMp());
                            chr.dispelDebuffs();
                            count++;
                        }

                    }
                }
            }
        } catch (Exception e) {
            c.getPlayer().dropMessage("���ǰ��ͼ�����˳���" + e.getMessage());
        }

        return count;
    }

    public void �������(String charName) {
        for (ChannelServer chl : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : chl.getPlayerStorage().getAllCharacters()) {
                if (chr.getName() == charName) {
                    c.getPlayer().changeMap(chr.getMapId());
                }
            }
        }
    }

    public int ɱ��(int mapId, int max) {
        int count = 0;

        if (mapId < 1) {
            mapId = c.getPlayer().getMapId();
        }

        int myId = c.getPlayer().getId();

        try {
            final MapleMap frommap = getMapFactory().getMap(mapId);
            List<MapleCharacter> list = frommap.getCharactersThreadsafe();
            if (list != null && frommap.getCharactersSize() > 0) {
                for (MapleMapObject mmo : list) {
                    if (mmo != null && count < max) {
                        MapleCharacter chr = (MapleCharacter) mmo;
                        if (chr.getId() != myId) {
                            chr.setHp(0);
                            chr.updateSingleStat(MapleStat.HP, 0);
                            count++;
                            this.setBossRankCount("��ɱ");
                        }
                    }
                }
            }
        } catch (Exception e) {
            c.getPlayer().dropMessage("ɱ�˳���" + e.getMessage());
        }

        return count;
    }

    public int ��ָ����ͼ����Ʒ(int ��ͼID, int ��ƷID, int ����, int ����, int ����, int ����, int ����, int HP, int MP, int �ɼӾ����, String ����������, int ����ʱ��, String �Ƿ���Խ���, int ������, int ħ����, int �������, int ħ������) {
        int count = 0;

        if (��ͼID < 1) {
            ��ͼID = c.getPlayer().getMapId();
        }

        try {
            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            MapleInventoryType type = GameConstants.getInventoryType(��ƷID);

            final MapleMap frommap = getMapFactory().getMap(��ͼID);
            List<MapleCharacter> list = frommap.getCharactersThreadsafe();
            if (list != null && frommap.getCharactersSize() > 0) {
                for (MapleMapObject mmo : list) {
                    if (mmo != null) {
                        MapleCharacter chr = (MapleCharacter) mmo;
                        if (���� >= 0) {
                            if (!MapleInventoryManipulator.checkSpace(chr.getClient(), ��ƷID, ����, "")) {
                                return 0;
                            }
                            if (type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(��ƷID) && !GameConstants.isBullet(��ƷID)
                                    || type.equals(MapleInventoryType.CASH) && ��ƷID >= 5000000 && ��ƷID <= 5000100) {
                                final Equip item = (Equip) (ii.getEquipById(��ƷID));
                                if (ii.isCash(��ƷID)) {
                                    item.setUniqueId(1);
                                }
                                if (���� > 0 && ���� <= 32767) {
                                    item.setStr((short) (����));
                                }
                                if (���� > 0 && ���� <= 32767) {
                                    item.setDex((short) (����));
                                }
                                if (���� > 0 && ���� <= 32767) {
                                    item.setInt((short) (����));
                                }
                                if (���� > 0 && ���� <= 32767) {
                                    item.setLuk((short) (����));
                                }
                                if (������ > 0 && ������ <= 32767) {
                                    item.setWatk((short) (������));
                                }
                                if (ħ���� > 0 && ħ���� <= 32767) {
                                    item.setMatk((short) (ħ����));
                                }
                                if (������� > 0 && ������� <= 32767) {
                                    item.setWdef((short) (�������));
                                }
                                if (ħ������ > 0 && ħ������ <= 32767) {
                                    item.setMdef((short) (ħ������));
                                }
                                if (HP > 0 && HP <= 30000) {
                                    item.setHp((short) (HP));
                                }
                                if (MP > 0 && MP <= 30000) {
                                    item.setMp((short) (MP));
                                }
                                if ("���Խ���".equals(�Ƿ���Խ���)) {
                                    byte flag = item.getFlag();
                                    if (item.getType() == MapleInventoryType.EQUIP.getType()) {
                                        flag |= ItemFlag.KARMA_EQ.getValue();
                                    } else {
                                        flag |= ItemFlag.KARMA_USE.getValue();
                                    }
                                    item.setFlag(flag);
                                }
                                if (����ʱ�� > 0) {
                                    item.setExpiration(System.currentTimeMillis() + (����ʱ�� * 24 * 60 * 60 * 1000));
                                }
                                if (�ɼӾ���� > 0) {
                                    item.setUpgradeSlots((byte) (�ɼӾ����));
                                }
                                if (���������� != null) {
                                    item.setOwner(����������);
                                }
                                final String name = ii.getName(��ƷID);
                                if (��ƷID / 10000 == 114 && name != null && name.length() > 0) { //medal
                                    final String msg = "���ѻ�óƺ� <" + name + ">";
                                    chr.dropMessage(5, msg);
                                }
                                MapleInventoryManipulator.addbyItem(chr.getClient(), item.copy());
                            } else {
                                MapleInventoryManipulator.addById(chr.getClient(), ��ƷID, (short) ����, "", null, ����ʱ��, (byte) 0);
                            }
                        } else {
                            MapleInventoryManipulator.removeById(chr.getClient(), GameConstants.getInventoryType(��ƷID), ��ƷID, -����, true, false);
                        }
                        chr.getClient().sendPacket(MaplePacketCreator.getShowItemGain(��ƷID, (short) ����, true));

                        count++;
                    }
                }
            }
        } catch (Exception e) {
            c.getPlayer().dropMessage("��ָ����ͼ����Ʒ����" + e.getMessage());
        }

        return count;
    }

    public int ��ָ����ͼ����Ʒ(int ��ͼID, int ��ƷID, int ����) {
        return ��ָ����ͼ����Ʒ(��ͼID, ��ƷID, ����, 0, 0, 0, 0, 0, 0, 0, "", 0, "", 0, 0, 0, 0);
    }

    //1���/2����/3���/4����
    public int ��ָ����ͼ�����(int ��ͼID, int ����, int ����) {
        int count = 0;

        String name = c.getPlayer().getName();

        if (��ͼID < 1) {
            ��ͼID = c.getPlayer().getMapId();
        }

        try {
            if (���� <= 0 || ���� <= 0) {
                return 0;
            }

            final MapleMap frommap = getMapFactory().getMap(��ͼID);
            List<MapleCharacter> list = frommap.getCharactersThreadsafe();
            if (list != null && frommap.getCharactersSize() > 0) {
                if (���� == 1 || ���� == 2) {
                    for (MapleMapObject mmo : list) {
                        if (mmo != null) {
                            MapleCharacter chr = (MapleCharacter) mmo;
                            chr.modifyCSPoints(����, ����);
                            String cash = null;
                            if (���� == 1) {
                                cash = "���";
                            } else if (���� == 2) {
                                cash = "���þ�";
                            }

                            //  chr.startMapEffect(name + "Ϊ�㷢����" + ���� + cash + "��", 5121009);
                            count++;
                        }
                    }
                } else if (���� == 3) {
                    for (MapleMapObject mmo : list) {
                        if (mmo != null) {
                            MapleCharacter chr = (MapleCharacter) mmo;
                            chr.gainMeso(����, true);
                            //  chr.startMapEffect(name + "Ϊ�㷢����" + ���� + "��ң�", 5121009);
                            count++;
                        }
                    }
                } else if (���� == 4) {
                    for (MapleMapObject mmo : list) {
                        if (mmo != null) {
                            MapleCharacter chr = (MapleCharacter) mmo;
                            chr.gainExp(����, true, false, true);
                            //   chr.startMapEffect(name + "Ϊ�㷢����" + ���� + "����ֵ��", 5121009);
                            count++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            c.getPlayer().dropMessage("��ָ����ͼ��������" + e.getMessage());
        }

        return count;
    }

    public int ��ȡָ����ͼ�������(int mapId) {//var count = cm.��ȡָ����ͼ�������(910000000);
        return getMapFactory().getMap(mapId).characterSize();
    }

    public int �ж�ָ����ͼ�������(int mapId) {//var count = cm.��ȡָ����ͼ�������(910000000);
        return getMapFactory().getMap(mapId).characterSize();
    }

    public void ��ָ����ͼ������(int mapId, String msg, int itemId) {//cm.��ָ����ͼ������(910000000)
        getMapFactory().getMap(mapId).startMapEffect(msg, itemId);
    }

    public String getServerName() {
        return MapleParty.��������;//var MC = cm.getServerName();
    }

    public String getbanben() {
        return ServerProperties.getProperty("ZEV.banben");//var MC = cm.getbanben();
    }

    public void ��¡() {
        c.getPlayer().cloneLook();
    }

    public void ȡ����¡() {
        c.getPlayer().disposeClones();
    }

    public void ��������(int ����ID) {
        if (c.getPlayer().getMap().getPermanentWeather() > 0) {
            c.getPlayer().getMap().setPermanentWeather(0);
            c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.removeMapEffect());
        } else if (!MapleItemInformationProvider.getInstance().itemExists(����ID) || ����ID / 10000 != 512) {
            c.getPlayer().dropMessage(5, "��Ч������ID��");
        } else {
            c.getPlayer().getMap().setPermanentWeather(����ID);
            c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.startMapEffect("", ����ID, false));
            c.getPlayer().dropMessage(5, "��ͼ���������á�");
        }
    }

    public void MapleMSpvpkills() {
        MapleGuildRanking.MapleMSpvpkills(c, this.npc);
    }

    public void MapleMSpvpdeaths() {
        MapleGuildRanking.MapleMSpvpdeaths(c, this.npc);
    }

    public void ���￪��() {
        c.getPlayer().getMap().toggleDrops();
    }

    public long �鿴����һ��() {
        return (System.currentTimeMillis() - c.getPlayer().����һ��) / 10;
    }

    public void ������(int r) {
        c.getPlayer().addFame(r);
    }

    public void �ж�����() {
        c.getPlayer().getFame();
    }

    public void ����(String ����) {
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter victim : cserv.getPlayerStorage().getAllCharacters()) {
                if (victim.getId() != c.getPlayer().getId()) {
                    victim.getMap().broadcastMessage(MaplePacketCreator.getChatText(victim.getId(), StringUtil.joinStringFrom(����, 1), victim.isGM(), 0));
                }
            }
        }
    }

    public void ���յ�ͼ() {
        if (�жϵ�ͼ(c.getPlayer().getMapId()) <= 0) {
            final int ��ͼ = c.getPlayer().getMapId();
            ��¼��ͼ(��ͼ);
            c.getPlayer().dropMessage(1, "���ճɹ����˵�ͼ���� 5 ���Ӻ󱻻��ա�");
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 60 * 5);
                        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                                if (chr == null) {
                                    continue;
                                }
                                if (chr.getMapId() == ��ͼ) {
                                    chr.getClient().getSession().close();
                                }
                            }
                        }
                        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                            cserv.getMapFactory().destroyMap(��ͼ, true);
                            cserv.getMapFactory().HealMap(��ͼ);
                        }
                        ɾ����ͼ(��ͼ);
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        } else {
            c.getPlayer().dropMessage(1, "����ʧ�ܣ��˵�ͼ�ڻ��ն����С�");
        }
    }

    public void ���յ�ͼ(final int a) {
        if (�жϵ�ͼ(a) <= 0) {
            final int ��ͼ = a;
            ��¼��ͼ(��ͼ);
            c.getPlayer().dropMessage(1, "���ճɹ����˵�ͼ���� 1 Сʱ�����á�");
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 60 * 1);
                        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                            cserv.getMapFactory().destroyMap(��ͼ, true);
                            cserv.getMapFactory().HealMap(��ͼ);
                        }
                        ɾ����ͼ(��ͼ);
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        } else {
            c.getPlayer().dropMessage(1, "����ʧ�ܣ��˵�ͼ�ڻ��ն����С�");
        }
    }

    public void ��¼��ͼ(int a) {
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO map (id) VALUES ( ?)")) {
            ps.setInt(1, a);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {

        }
    }

    public void ɾ����ͼ(int a) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM map where id =" + a + "");
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlstr = " Delete from map where id = '" + a + "'";
                ps1.executeUpdate(sqlstr);
            }
        } catch (SQLException ex) {

        }
    }

    public int �жϵ�ͼ(int a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM map where id =" + a + "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data += 1;
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public void �������а�() {
        MapleGuild.��������(getClient(), this.npc);
    }

    public void �������а�() {
        MapleGuild.��������(getClient(), this.npc);
    }

    public void �������а�() {
        MapleGuild.��������(getClient(), this.npc);
    }

    public void ս�������а�() {
        MapleGuild.ս��������(getClient(), this.npc);
    }

    public void ������ʱ�����а�() {
        MapleGuild.������ʱ������(getClient(), this.npc);
    }

    public int ��ѯ��������ʱ��() {
        int data = 0;
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement psu = con.prepareStatement("SELECT todayOnlineTime FROM characters WHERE id = ?");
            psu.setInt(1, c.getPlayer().getId());
            ResultSet rs = psu.executeQuery();
            if (rs.next()) {
                data = rs.getInt("todayOnlineTime");
            }
            rs.close();
            psu.close();

        } catch (SQLException ex) {
            System.err.println("��ѯ��������ʱ�����" + ex.getMessage());
        }

        return data;
    }

    public int ��ѯ������ʱ��() {
        int data = 0;

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement psu = con.prepareStatement("SELECT totalOnlineTime FROM characters WHERE id = ?");
            psu.setInt(1, c.getPlayer().getId());
            ResultSet rs = psu.executeQuery();
            if (rs.next()) {
                data = rs.getInt("totalOnlineTime");
            }
            rs.close();
            psu.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ������ʱ�����" + ex.getMessage());
        }

        return data;
    }

    public int ��ѯ��������() {
        int count = 0;
        for (ChannelServer chl : ChannelServer.getAllInstances()) {
            count += chl.getPlayerStorage().getAllCharacters().size();
        }
        return count;
    }

    /**
     * �@ȡָ�����С�[��e��
     *
     * @param gameType 1 ������ GameConstants.OMOK_SCORE = 122200; //2 ӛ�����
     * GameConstants.MATCH_SCORE = 122210;
     * @param top ����@ʾǰ������
     * @return
     */
    public List<MiniGamePoints> getMiniGameTop(int gameType, int top) {
        return RankManager.getInstance().getTop(gameType, top);
    }

    private String getmapId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static int ��ɫIDȡ�˺�ID(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT accountid as DATA FROM characters WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ɫ����ȡ�˺�ID������");
        }
        return data;
    }

    //��ɫID���������͡�����λ��cm.�жϱ���λ���Ƿ�����Ʒ(��ɫID,����,λ��)
    public static int �жϱ���λ���Ƿ�����Ʒ(int a, int b, int c) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + a + " && inventorytype = " + b + " && position = " + c + "");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�жϱ���λ���Ƿ�����Ʒ - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }
    //�������ͣ�����λ��

    public static int �жϱ���λ�ô���(int a, int b, int c) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + a + " && inventorytype = " + b + " && position = " + c + "");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("itemid");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�жϱ���λ���Ƿ�����Ʒ - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    //��ɫID��װ������,��������
    public static int �ж�����Ƿ񴩴�ĳװ��(int a, int b, int c) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + a + " && itemid = " + b + " && inventorytype = " + c + "");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�ж�����Ƿ񴩴�ĳװ�� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static int ��ȡ�����ҵȼ�() {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT MAX(level) as DATA FROM characters WHERE gm = 0");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�����ҵȼ����� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return data;
    }

    public static String �˺�ȡ��QQ(String id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT qq as DATA FROM accounts WHERE name = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�˺�IDȡ�˺š�����");
        }
        return data;
    }

    public static String �˺�IDȡ��QQ(String id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT qq as DATA FROM accounts WHERE id = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�˺�IDȡ�˺š�����");
        }
        return data;
    }

    public static String �˺�IDȡ�˺�(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM accounts WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�˺�IDȡ�˺š�����");
        }
        return data;
    }

    /*     public static int ��ȡ���������ҵȼ�() {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT MAX(level) as DATA FROM characters WHERE gm = 0  WHERE job = 100");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ���������ҵȼ����� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return data;
    }*/
    public static String ��ȡ��ߵȼ��������() {
        String name = "";
        String level = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT `name`, `level` FROM characters WHERE gm = 0 ORDER BY `level` DESC LIMIT 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                    level = rs.getString("level");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return String.format("%s", name);
    }

    public static int ��ȡ��ߵȼ�() {
        int level = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT  `level` FROM characters WHERE gm = 0 ORDER BY `level` DESC LIMIT 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    level = rs.getInt("level");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return level;
    }

    public static int ��ȡ����������() {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT MAX(fame) as DATA FROM characters WHERE gm = 0");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�����ҵȼ����� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return data;
    }

    public static String ��ȡ��������������() {
        String name = "";
        String level = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT `name`, `fame` FROM characters WHERE gm = 0 ORDER BY `fame` DESC LIMIT 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                    level = rs.getString("fame");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return String.format("%s", name);
    }

    public static int ��ȡ�����ҽ��() {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT MAX(meso) as DATA FROM characters WHERE gm = 0");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�����ҵȼ����� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return data;
    }

    public static String ��ȡ��߽���������() {
        String name = "";
        String level = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT `name`, `meso` FROM characters WHERE gm = 0 ORDER BY `meso` DESC LIMIT 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                    level = rs.getString("meso");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return String.format("%s", name);
    }

    public static int ��ȡ����������() {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT MAX(totalOnlineTime) as DATA FROM characters WHERE gm = 0");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�����ҵȼ����� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return data;
    }

    public static String ��ȡ��������������() {
        String name = "";
        String level = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT `name`, `totalOnlineTime` FROM characters WHERE gm = 0 ORDER BY `totalOnlineTime` DESC LIMIT 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                    level = rs.getString("totalOnlineTime");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return String.format("%s", name);
    }

    public static String ��ȡ���������������() {
        String name = "";
        String level = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT `name`, `todayOnlineTime` FROM characters WHERE gm = 0 ORDER BY `todayOnlineTime` DESC LIMIT 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                    level = rs.getString("todayOnlineTime");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return String.format("%s", name);
    }

    public static String ��ȡ��ǿ��������() {
        String name = "";
        String level = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT `name`, `GP` FROM guilds  ORDER BY `GP` DESC LIMIT 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                    level = rs.getString("GP");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return String.format("%s", name);
    }

    public static String ��ȡ��������(int guildId) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static String ��ȡ�����峤��ע(int guildId) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT rank1title as DATA FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static String ��ȡ���帱�峤��ע(int guildId) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT rank2title as DATA FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static String ��ȡ����һ����Ա��ע(int guildId) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT rank3title as DATA FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static String ��ȡ���������Ա��ע(int guildId) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT rank4title as DATA FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static String ��ȡ����������Ա��ע(int guildId) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT rank5title as DATA FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static String ��ȡ�����峤ID(int guildId) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT leader as DATA FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static int �����Ա��(int a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters ");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (rs.getInt("guildid") == a) {
                        data += 1;
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��������Ա��ʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static String ��ɫIDȡ����(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM characters WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��ɫIDȡ���ֳ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        if (data == null) {
            data = "������ʿ";
        }
        return data;
    }

    public static String IPȡ�˺�(String id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM accounts WHERE SessionIP = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    data = rs.getString("DATA");
                    return data;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��ɫIDȡ���ֳ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        if (data == null) {
            data = "������ʿ";
        }
        return data;
    }

    public static String MACȡ�˺�(String id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM accounts WHERE macs = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��ɫIDȡ���ֳ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        if (data == null) {
            data = "������ʿ";
        }
        return data;
    }

    public static String IPȡ����ֵ(String id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT loggedin as DATA FROM accounts WHERE SessionIP = " + id + "");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    data = rs.getString("DATA");
                    return data;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("IPȡ����ֵ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        if (data == null) {
            data = "������ʿ";
        }
        return data;
    }

    public static int �˺������н�ɫID(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters ");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (rs.getInt("accountid") == id) {
                        data = rs.getInt("ACash");
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��ȯ��桢����");
        }
        return data;
    }

    public static int ��ȡ��ȯ���(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts ");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    if (rs.getInt("id") == id) {
                        data = rs.getInt("ACash");
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��ȯ��桢����");
        }
        return data;
    }

    public static String SNȡ����(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Point as DATA FROM character7 WHERE Name = ? && channel = 1");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��ɫIDȡ���ֳ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        if (data == null) {
            data = "������ʿ";
        }
        return data;
    }

    public static String SNȡ���(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Point as DATA FROM character7 WHERE Name = ? &&  channel = 2");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��ɫIDȡ���ֳ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        if (data == null) {
            data = "������ʿ";
        }
        return data;
    }

    public static String SNȡ�ۿ�(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Point as DATA FROM character7 WHERE Name = ? &&  channel = 3");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��ɫIDȡ���ֳ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        if (data == null) {
            data = "������ʿ";
        }
        return data;
    }

    public static String SNȡ�޹�(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Point as DATA FROM character7 WHERE Name = ? &&  channel = 4");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��ɫIDȡ���ֳ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        if (data == null) {
            data = "������ʿ";
        }
        return data;
    }

    public static String SNȡ����(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Point as DATA FROM character7 WHERE Name = ? &&  channel = 5");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��ɫIDȡ���ֳ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        if (data == null) {
            data = "������ʿ";
        }
        return data;
    }

    public static int ��ɫ����ȡID(String id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id as DATA FROM characters WHERE name = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��ɫ����ȡID���� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return data;
    }

    public static int ��ɫ����ȡ�˺�ID(String id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT accountid as DATA FROM characters WHERE name = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��ɫ����ȡID���� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static String �˺�IDȡ�˺�(String id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM accounts WHERE id = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��ɫ����ȡID���� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static String �˺�IDȡ����(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT loggedin as DATA FROM accounts WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��ɫ����ȡID���� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static String ��ɫ����ȡ�ȼ�(String id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT level as DATA FROM characters WHERE name = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��ɫ����ȡID���� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        if (data == null) {
            data = "������ʿ";
        }
        return data;
    }

    public static String ��Ʒ��ȡ�������(int itemid) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT dropperid as DATA FROM drop_data WHERE itemid = ?");
            ps.setInt(1, itemid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ��Ʒ��ȡ���������� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    /* public static String ��ȡ�����ҵȼ�������() {
        String name = "";
        String level = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT `name`, `level` FROM characters WHERE gm = 0 ORDER BY `level` DESC LIMIT 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                    level = rs.getString("level");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }

        return String.format("��һ��Ŀǰ�ȼ� %s ���� %s", name, level);
    }*/
    public int ��ȡ�Լ��ȼ�����() {
        int DATA = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT rank FROM (SELECT @rownum := @rownum + 1 AS rank, `name`, `level`, `id` FROM characters, (SELECT @rownum := 0) r WHERE gm = 0 ORDER BY `level` DESC) AS T1 WHERE `id` = ?");
            ps.setInt(1, c.getPlayer().getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DATA = rs.getInt("rank");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�Լ��ȼ��������� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return DATA;
    }

    public int ��ȡ�Լ��������() {
        int DATA = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT rank FROM (SELECT @rownum := @rownum + 1 AS rank, `name`, `meso`, `id` FROM characters, (SELECT @rownum := 0) r WHERE gm = 0 ORDER BY `meso` DESC) AS T1 WHERE `id` = ?");
            ps.setInt(1, c.getPlayer().getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DATA = rs.getInt("rank");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�Լ�������� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return DATA;
    }

    public int ��ȡ�Լ���������1() {
        int DATA = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT rank FROM (SELECT @rownum := @rownum + 1 AS rank, `name`, `totalOnlineTime`, `id` FROM characters, (SELECT @rownum := 0) r WHERE gm = 0 ORDER BY `totalOnlineTime` DESC) AS T1 WHERE `id` = ?");
            ps.setInt(1, c.getPlayer().getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DATA = rs.getInt("rank");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�Լ�������� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return DATA;
    }

    public int ��ȡ�Լ���������2() {
        int DATA = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT rank FROM (SELECT @rownum := @rownum + 1 AS rank, `name`, `todayOnlineTime`, `id` FROM characters, (SELECT @rownum := 0) r WHERE gm = 0 ORDER BY `todayOnlineTime` DESC) AS T1 WHERE `id` = ?");
            ps.setInt(1, c.getPlayer().getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DATA = rs.getInt("rank");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�Լ�������� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return DATA;
    }

    public int ��ȡ��ս��������() {
        int DATA = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT rank FROM (SELECT @rownum := @rownum + 1 AS rank, `name`, `level`, `id` FROM characters, (SELECT @rownum := 0) r WHERE gm = 0 ORDER BY `level` DESC) AS T1 WHERE `id` = ?");
            ps.setInt(1, c.getPlayer().getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DATA = rs.getInt("rank");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�Լ��ȼ��������� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return DATA;
    }

    public String GetPlayerList() {
        String ret = "";
        ret = "#e#r <" + MapleParty.�������� + "> �������׷��#k#n\r\n\r\n";
        for (ChannelServer cs : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cs.getPlayerStorage().getAllCharacters()) {
                //if (!chr.isGM()) {
                //ret = "#e#r <"+MapleParty.��������+"> �������׷��#k#n\r\n";
                ret += "#b#L" + chr.getId() + "#[����] #d���:  #r" + chr.getName() + " #k#l \r\n";
                // }

            }
        }
        return ret;
    }

    public String ��ɫ��Ϣ2(final int objectid) {
        String ret = "";
        ret = "#e#r ��ɫ��Ϣ#k#n\r\n";
        ret += "���:  #r" + c.getPlayer().getMap().getCharacterById(objectid).getId() + " #k\r\n";
        ret += "���:  #r" + c.getPlayer().getMap().getCharacterById(objectid).getName() + " #k\r\n";

        return ret;
    }

    public MapleCharacter GetPlayer(int cid) {
        MapleCharacter ret = null;
        for (ChannelServer cs : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cs.getPlayerStorage().getAllCharacters()) {
                if (!chr.isGM()) {
                    if (chr.getId() == cid) {
                        ret = chr;
                    }
                }
            }
        }
        return ret;
    }

    public String getItemName(int id) {//��Ʒ����
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        return ii.getName(id);
        // MapleItemInformationProvider.getInstance().getName(id);
    }

    public String getip() {
        return MapleParty.IP��ַ;
    }

    public ArrayList<Forum_Section> getForum() {
        return Forum_Section.getAllSection();
    }

    public boolean addSection(String name) {
        return Forum_Section.addSection(name);
    }

    public boolean deleteSection(int id) {
        return Forum_Section.deleteSection(id);
    }

    public void ����1(int id) {
        showSpecialEffect(id);
    }

    public void ��1��() {

        c.getPlayer().levelUp();

        c.getPlayer().setExp(0);
        c.getPlayer().updateSingleStat(MapleStat.EXP, 0);
    }

    public void �������() {
        double range = Double.POSITIVE_INFINITY;
        MapleMap map = c.getPlayer().getMap();
        MapleMonster mob;
        List<MapleMapObject> monsters = map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER));
        for (MapleMapObject monstermo : map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
            mob = (MapleMonster) monstermo;
            map.killMonster(mob, c.getPlayer(), true, false, (byte) 1);
        }
    }

    public void ���װ���ںϲ���(int cid) {

        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM bank_item2 WHERE cid = ?");
            ps1.setInt(1, cid);
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlstr = " delete from bank_item2 where cid = '" + cid + "'";
                ps1.executeUpdate(sqlstr);
            }
        } catch (SQLException ex) {
        }

    }

    public void �л�Ƶ��(int id) {
        c.getPlayer().changeChannel(id);
    }

    public void ���������ػؼ�¼(int a) {
        try {
            try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characterz SET Point = 0 WHERE channel = 200 && Point =" + a + "")) {
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("");
        }
    }

    public void ��ɢ����Զ����() {
        try {
            try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characterz SET Point = 0 WHERE channel = 201")) {
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("");
        }
    }

    public void �ӳ�() {
        c.getPlayer().getClient().getLatency();
        return;
    }
    private final String rankTitles[] = new String[5];

    public void MapleGuild(final int guildid) {

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildid);
            ResultSet rs = ps.executeQuery();
            rankTitles[0] = rs.getString("rank1title");
//            rankTitles[1] = rs.getString("rank2title");
//            rankTitles[2] = rs.getString("rank3title");
//            rankTitles[3] = rs.getString("rank4title");
//            rankTitles[4] = rs.getString("rank5title");
            rs.close();
            ps.close();
        } catch (SQLException se) {
            System.err.println("unable to read guild information from sql");
            se.printStackTrace();
        }
    }
//    public String �峤�ǳ�() {
//       c.getPlayer().getMapleGuild().rankTitles[0];
//    }

    public void ����(String String) {
        c.sendPacket(MaplePacketCreator.showEffect(String));//������Ч
        return;
    }

    public void ����3(String data) {
        c.sendPacket(UIPacket.ShowWZEffect(data, -1));
        return;
    }

    private static void showIntro(final MapleClient c, final String data) {
        c.sendPacket(UIPacket.IntroDisableUI(true));
        c.sendPacket(UIPacket.IntroLock(true));
        c.sendPacket(UIPacket.ShowWZEffect(data, -1));
    }

    public void ����2(String String) {
        c.sendPacket(UIPacket.AranTutInstructionalBalloon(String));

        return;
    }

    public Forum_Section getSectionById(int id) {
        return Forum_Section.getSectionById(id);
    }

    public ArrayList<Forum_Thread> getCurrentAllThread(int sid) {
        return Forum_Thread.getCurrentAllThread(sid);
    }

    public Forum_Thread getThreadById(int sid, int id) {
        return Forum_Thread.getThreadById(sid, id);
    }

    public Forum_Thread getThreadByName(int sid, String name) {
        return Forum_Thread.getThreadByName(sid, name);
    }

    public boolean addThread(int sid, String tname, int cid, String cname) {
        return Forum_Thread.addThread(sid, tname, cid, cname);
    }

    public boolean deleteThread(int sid, int tid) {
        return Forum_Thread.deleteThread(sid, tid, false);
    }

    public ArrayList<Forum_Reply> getCurrentAllReply(int tid) {
        return Forum_Reply.getCurrentAllReply(tid);
    }

    public boolean addReply(int tid, int cid, String cname, String news) {
        return Forum_Reply.addReply(tid, cid, cname, news);
    }

    public void EnterCS() {
        MapleCharacter chr = c.getPlayer();
        final ChannelServer ch = ChannelServer.getInstance(c.getChannel());

        chr.changeRemoval();

        if (chr.getMessenger() != null) {
            MapleMessengerCharacter messengerplayer = new MapleMessengerCharacter(chr);
            World.Messenger.leaveMessenger(chr.getMessenger().getId(), messengerplayer);
        }
        PlayerBuffStorage.addBuffsToStorage(chr.getId(), chr.getAllBuffs());
        PlayerBuffStorage.addCooldownsToStorage(chr.getId(), chr.getCooldowns());
        PlayerBuffStorage.addDiseaseToStorage(chr.getId(), chr.getAllDiseases());
        World.ChannelChange_Data(new CharacterTransfer(chr), chr.getId(), -20);
        ch.removePlayer(chr);
        c.updateLoginState(MapleClient.CHANGE_CHANNEL, c.getSessionIPAddress());

        c.sendPacket(MaplePacketCreator.getChannelChange(Integer.parseInt(CashShopServer.getIP().split(":")[1])));
        chr.saveToDB(false, false);
        chr.getMap().removePlayer(chr);
        c.setPlayer(null);
        c.setReceiving(false);
    }

    public void ���üҾ�(int npcId) {
        MapleNPC npc = MapleLifeFactory.getNPC(npcId);

        final int xpos = c.getPlayer().getPosition().x;
        final int ypos = c.getPlayer().getPosition().y;
        final int fh = c.getPlayer().getMap().getFootholds().findBelow(c.getPlayer().getPosition()).getId();
        npc.setPosition(c.getPlayer().getPosition());
        npc.setCy(ypos);
        npc.setRx0(xpos);
        npc.setRx1(xpos);
        npc.setFh(fh);
        npc.setCustom(true);
        try {
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DatabaseConnection.getConnection();
            try (com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("INSERT INTO �Ҿ� (dataid, f, hide, fh, cy, rx0, rx1, type, x, y, mid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                ps.setInt(1, npcId);
                ps.setInt(2, 0); // 1 = right , 0 = left
                ps.setInt(3, 0); // 1 = hide, 0 = show
                ps.setInt(4, fh);
                ps.setInt(5, ypos);
                ps.setInt(6, xpos);
                ps.setInt(7, xpos);
                ps.setString(8, "n");
                ps.setInt(9, xpos);
                ps.setInt(10, ypos);
                ps.setInt(11, c.getPlayer().getMapId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            c.getPlayer().dropMessage(6, "δ�ܽ����Ҿߡ����浽���ݿ�");
        }
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            cserv.getMapFactory().getMap(c.getPlayer().getMapId()).addMapObject(npc);
            cserv.getMapFactory().getMap(c.getPlayer().getMapId()).broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
//                    c.getPlayer().getMap().addMapObject(npc);
//                    c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
        }
        //c.getPlayer().dropMessage(6, "�����Ҿ߳ɹ��������Ҫɾ�����������ݿ⡶�Ҿߡ���ɾ����������Ч");
    }

    public void ��Ʒ�ڷ�(int npcId) {
        MapleNPC npc = MapleLifeFactory.getNPC(npcId);

        final int xpos = c.getPlayer().getPosition().x;
        final int ypos = c.getPlayer().getPosition().y;
        final int fh = c.getPlayer().getMap().getFootholds().findBelow(c.getPlayer().getPosition()).getId();
        npc.setPosition(c.getPlayer().getPosition());
        npc.setCy(ypos);
        npc.setRx0(xpos);
        npc.setRx1(xpos);
        npc.setFh(fh);
        npc.setCustom(true);
        try {
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DatabaseConnection.getConnection();
            try (com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("INSERT INTO ��Ʒ�ڷ� (dataid, f, hide, fh, cy, rx0, rx1, type, x, y, mid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                ps.setInt(1, npcId);
                ps.setInt(2, 0); // 1 = right , 0 = left
                ps.setInt(3, 0); // 1 = hide, 0 = show
                ps.setInt(4, fh);
                ps.setInt(5, ypos);
                ps.setInt(6, xpos);
                ps.setInt(7, xpos);
                ps.setString(8, "n");
                ps.setInt(9, xpos);
                ps.setInt(10, ypos);
                ps.setInt(11, c.getPlayer().getMapId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            c.getPlayer().dropMessage(6, "δ�ܽ�����Ʒ�ڷš����浽���ݿ�");
        }
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            cserv.getMapFactory().getMap(c.getPlayer().getMapId()).addMapObject(npc);
            cserv.getMapFactory().getMap(c.getPlayer().getMapId()).broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
//                    c.getPlayer().getMap().addMapObject(npc);
//                    c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
        }
        //c.getPlayer().dropMessage(6, "�����Ҿ߳ɹ��������Ҫɾ�����������ݿ⡶�Ҿߡ���ɾ����������Ч");
    }

    public int ���(String banPlayerName, String reason) {
        int ch = World.Find.findChannel(banPlayerName);

        StringBuilder sb = new StringBuilder(c.getPlayer().getName());
        sb.append(" banned ").append(banPlayerName).append(": ").append(reason);
        MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(banPlayerName);

        /*if (target == null || ch < 1) {
            if (MapleCharacter.ban(banPlayerName, sb.toString(), false, c.getPlayer().isAdmin() ? 250 : c.getPlayer().getGMLevel(), false)) {
                c.getPlayer().dropMessage(6, "�ɹ����߷��� " + banPlayerName + ".");
                return 1;
            } else {
                c.getPlayer().dropMessage(6, "����ʧ�� " + banPlayerName);
                return 0;
            }
        }*/
        //if (c.getPlayer().getGMLevel() > target.getGMLevel()) {
        if (target.getGMLevel() < 0) {
            sb.append(" (IP: ").append(target.getClient().getSessionIPAddress()).append(")");
            if (target.ban(sb.toString(), c.getPlayer().isAdmin(), false, false)) {
                c.getPlayer().dropMessage(6, "[�ɹ����� " + banPlayerName + ".");
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���ϵͳ]" + target.getName() + " ��Ϊʹ���˷Ƿ�����������÷�š�"));

                return 1;
            } else {
                c.getPlayer().dropMessage(6, "����ʧ��.");
                return 0;
            }
        } else {
            c.getPlayer().dropMessage(6, "���ܷ���GM...");
            return 1;
        }

    }

    public void Ů������������() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(" SELECT * FROM  Ů������������ ");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    double ���� = Math.ceil(Math.random() * rs.getInt("chance"));
                    double �Ա� = Math.ceil(Math.random() * 100);
                    double ���� = Math.ceil(Math.random() * rs.getInt("baseQty"));
                    double ��� = Math.ceil(Math.random() * rs.getInt("maxRandomQty"));
                    if (��� <= ����) {
                        ��� = ����;
                    }
                    if (���� >= 100) {
                        ���� = 100;
                    }
                    if (���� <= �Ա�) {
                        gainItem(rs.getInt("itemId"), (short) ���);
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("Ů������������������");
        }
    }

    public void Ů������������2() {
        // itemId baseQty maxRandomQty chance
        int count = 0;
        int arrLength = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(itemId) as Count, SUM(chance) as Data FROM Ů������������");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("Count");
                    arrLength = rs.getInt("Data");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯŮ������������1֮���ʷ�ĸ����" + ex.getMessage());
        }

        if (count == 0 || arrLength == 0) {
            return;
        }

        int[][] data = new int[arrLength][2];

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemId, baseQty, maxRandomQty, chance FROM Ů������������");
            try (ResultSet rs = ps.executeQuery()) {
                int j = 0;
                while (rs.next()) {
                    int randomQty = new Random().nextInt(rs.getInt("maxRandomQty"));
                    for (int i = 0; i <= rs.getInt("chance") - 1; i++) {
                        data[j] = new int[]{rs.getInt("itemId"), rs.getInt("baseQty") + randomQty};
                        j++;
                    }
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯŮ����������������" + ex.getMessage());
        }

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i <= count - 1; i++) {
            int r = new Random().nextInt(arrLength - 1);

            int itemId = data[r][0];
            int existsCount = 0;
            for (int[] is : result) {
                if (is[0] == itemId) {
                    existsCount++;
                }
            }
            if (existsCount > 0) {
                continue;
            }

            result.add(data[r]);
        }

        if (result.size() > 0) {
            for (int[] is : result) {
                int itemId = is[0];
                short qty = (short) is[1];
                gainItem(itemId, qty);
            }
        }
    }

    public void ���������() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(" SELECT * FROM  ��������� ");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    double ���� = Math.ceil(Math.random() * rs.getInt("chance"));
                    double �Ա� = Math.ceil(Math.random() * 100);
                    double ���� = Math.ceil(Math.random() * rs.getInt("baseQty"));
                    double ��� = Math.ceil(Math.random() * rs.getInt("maxRandomQty"));
                    if (��� <= ����) {
                        ��� = ����;
                    }
                    if (���� >= 100) {
                        ���� = 100;
                    }
                    if (���� <= �Ա�) {
                        gainItem(rs.getInt("itemId"), (short) ���);
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("���������������");
        }
    }

    public void ���������2() {
        // itemId baseQty maxRandomQty chance
        int count = 0;
        int arrLength = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(itemId) as Count, SUM(chance) as Data FROM ���������");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("Count");
                    arrLength = rs.getInt("Data");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ���߽���1֮���ʷ�ĸ����" + ex.getMessage());
        }

        if (count == 0 || arrLength == 0) {
            return;
        }

        int[][] data = new int[arrLength][2];

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemId, baseQty, maxRandomQty, chance FROM ���������");
            try (ResultSet rs = ps.executeQuery()) {
                int j = 0;
                while (rs.next()) {
                    int randomQty = new Random().nextInt(rs.getInt("maxRandomQty"));
                    for (int i = 0; i <= rs.getInt("chance") - 1; i++) {
                        data[j] = new int[]{rs.getInt("itemId"), rs.getInt("baseQty") + randomQty};
                        j++;
                    }
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ���߽���1����" + ex.getMessage());
        }

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i <= count - 1; i++) {
            int r = new Random().nextInt(arrLength - 1);

            int itemId = data[r][0];
            int existsCount = 0;
            for (int[] is : result) {
                if (is[0] == itemId) {
                    existsCount++;
                }
            }
            if (existsCount > 0) {
                continue;
            }

            result.add(data[r]);
        }

        if (result.size() > 0) {
            for (int[] is : result) {
                int itemId = is[0];
                short qty = (short) is[1];
                gainItem(itemId, qty);
            }
        }
    }

    public void �϶���������() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(" SELECT * FROM  �϶��������� ");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    double ���� = Math.ceil(Math.random() * rs.getInt("chance"));
                    double �Ա� = Math.ceil(Math.random() * 100);
                    double ���� = Math.ceil(Math.random() * rs.getInt("baseQty"));
                    double ��� = Math.ceil(Math.random() * rs.getInt("maxRandomQty"));
                    if (��� <= ����) {
                        ��� = ����;
                    }
                    if (���� >= 100) {
                        ���� = 100;
                    }
                    if (���� <= �Ա�) {
                        gainItem(rs.getInt("itemId"), (short) ���);
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�϶���������������");
        }
    }

    public void �϶���������2() {
        // itemId baseQty maxRandomQty chance
        int count = 0;
        int arrLength = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(itemId) as Count, SUM(chance) as Data FROM �϶���������");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("Count");
                    arrLength = rs.getInt("Data");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ���߽���1֮���ʷ�ĸ����" + ex.getMessage());
        }

        if (count == 0 || arrLength == 0) {
            return;
        }

        int[][] data = new int[arrLength][2];

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemId, baseQty, maxRandomQty, chance FROM �϶���������");
            try (ResultSet rs = ps.executeQuery()) {
                int j = 0;
                while (rs.next()) {
                    int randomQty = new Random().nextInt(rs.getInt("maxRandomQty"));
                    for (int i = 0; i <= rs.getInt("chance") - 1; i++) {
                        data[j] = new int[]{rs.getInt("itemId"), rs.getInt("baseQty") + randomQty};
                        j++;
                    }
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ���߽���1����" + ex.getMessage());
        }

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i <= count - 1; i++) {
            int r = new Random().nextInt(arrLength - 1);

            int itemId = data[r][0];
            int existsCount = 0;
            for (int[] is : result) {
                if (is[0] == itemId) {
                    existsCount++;
                }
            }
            if (existsCount > 0) {
                continue;
            }

            result.add(data[r]);
        }

        if (result.size() > 0) {
            for (int[] is : result) {
                int itemId = is[0];
                short qty = (short) is[1];
                gainItem(itemId, qty);
            }
        }
    }

    public void ���Խ���() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(" SELECT * FROM  ���Խ��� ");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    double ���� = Math.ceil(Math.random() * rs.getInt("chance"));
                    double �Ա� = Math.ceil(Math.random() * 100);
                    double ���� = Math.ceil(Math.random() * rs.getInt("baseQty"));
                    double ��� = Math.ceil(Math.random() * rs.getInt("maxRandomQty"));
                    if (��� <= ����) {
                        ��� = ����;
                    }
                    if (���� >= 100) {
                        ���� = 100;
                    }
                    if (���� <= �Ա�) {
                        gainItem(rs.getInt("itemId"), (short) ���);
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("���Խ���������");
        }
    }

    public void �������������() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(" SELECT * FROM  ������������� ");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    double ���� = Math.ceil(Math.random() * rs.getInt("chance"));
                    double �Ա� = Math.ceil(Math.random() * 100);
                    double ���� = Math.ceil(Math.random() * rs.getInt("baseQty"));
                    double ��� = Math.ceil(Math.random() * rs.getInt("maxRandomQty"));
                    if (��� <= ����) {
                        ��� = ����;
                    }
                    if (���� >= 100) {
                        ���� = 100;
                    }
                    if (���� <= �Ա�) {
                        gainItem(rs.getInt("itemId"), (short) ���);
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�������������������");
        }
    }

    public void ������������() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(" SELECT * FROM  ������������ ");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    double ���� = Math.ceil(Math.random() * rs.getInt("chance"));
                    double �Ա� = Math.ceil(Math.random() * 100);
                    double ���� = Math.ceil(Math.random() * rs.getInt("baseQty"));
                    double ��� = Math.ceil(Math.random() * rs.getInt("maxRandomQty"));
                    if (��� <= ����) {
                        ��� = ����;
                    }
                    if (���� >= 100) {
                        ���� = 100;
                    }
                    if (���� <= �Ա�) {
                        gainItem(rs.getInt("itemId"), (short) ���);
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("������������������");
        }
    }

    public void ���Խ���2() {
        // itemId baseQty maxRandomQty chancechance
        int count = 0;
        int arrLength = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(itemId) as Count, SUM(chance) as Data FROM ���Խ���");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("Count");
                    arrLength = rs.getInt("Data");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ���߽���1֮���ʷ�ĸ����" + ex.getMessage());
        }

        if (count == 0 || arrLength == 0) {
            return;
        }

        int[][] data = new int[arrLength][2];

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemId, baseQty, maxRandomQty, chance FROM ���Խ���");
            try (ResultSet rs = ps.executeQuery()) {
                int j = 0;
                while (rs.next()) {
                    int randomQty = new Random().nextInt(rs.getInt("maxRandomQty"));
                    for (int i = 0; i <= rs.getInt("chance") - 1; i++) {
                        data[j] = new int[]{rs.getInt("itemId"), rs.getInt("baseQty") + randomQty};
                        j++;
                    }
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ���߽���1����" + ex.getMessage());
        }

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i <= count - 1; i++) {
            int r = new Random().nextInt(arrLength - 1);

            int itemId = data[r][0];
            int existsCount = 0;
            for (int[] is : result) {
                if (is[0] == itemId) {
                    existsCount++;
                }
            }
            if (existsCount > 0) {
                continue;
            }

            result.add(data[r]);
        }

        if (result.size() > 0) {
            for (int[] is : result) {
                int itemId = is[0];
                short qty = (short) is[1];
                gainItem(itemId, qty);
            }
        }
    }

    public void �������������2() {
        // itemId baseQty maxRandomQty chance
        int count = 0;
        int arrLength = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(itemId) as Count, SUM(chance) as Data FROM �������������");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("Count");
                    arrLength = rs.getInt("Data");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ���߽���1֮���ʷ�ĸ����" + ex.getMessage());
        }

        if (count == 0 || arrLength == 0) {
            return;
        }

        int[][] data = new int[arrLength][2];

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemId, baseQty, maxRandomQty, chance FROM �������������");
            try (ResultSet rs = ps.executeQuery()) {
                int j = 0;
                while (rs.next()) {
                    int randomQty = new Random().nextInt(rs.getInt("maxRandomQty"));
                    for (int i = 0; i <= rs.getInt("chance") - 1; i++) {
                        data[j] = new int[]{rs.getInt("itemId"), rs.getInt("baseQty") + randomQty};
                        j++;
                    }
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ���߽���1����" + ex.getMessage());
        }

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i <= count - 1; i++) {
            int r = new Random().nextInt(arrLength - 1);

            int itemId = data[r][0];
            int existsCount = 0;
            for (int[] is : result) {
                if (is[0] == itemId) {
                    existsCount++;
                }
            }
            if (existsCount > 0) {
                continue;
            }

            result.add(data[r]);
        }

        if (result.size() > 0) {
            for (int[] is : result) {
                int itemId = is[0];
                short qty = (short) is[1];
                gainItem(itemId, qty);
            }
        }
    }

    public void ������������2() {
        // itemId baseQty maxRandomQty chance
        int count = 0;
        int arrLength = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(itemId) as Count, SUM(chance) as Data FROM ������������");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("Count");
                    arrLength = rs.getInt("Data");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ���߽���1֮���ʷ�ĸ����" + ex.getMessage());
        }

        if (count == 0 || arrLength == 0) {
            return;
        }

        int[][] data = new int[arrLength][2];

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemId, baseQty, maxRandomQty, chance FROM ������������");
            try (ResultSet rs = ps.executeQuery()) {
                int j = 0;
                while (rs.next()) {
                    int randomQty = new Random().nextInt(rs.getInt("maxRandomQty"));
                    for (int i = 0; i <= rs.getInt("chance") - 1; i++) {
                        data[j] = new int[]{rs.getInt("itemId"), rs.getInt("baseQty") + randomQty};
                        j++;
                    }
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ����������������" + ex.getMessage());
        }

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i <= count - 1; i++) {
            int r = new Random().nextInt(arrLength - 1);

            int itemId = data[r][0];
            int existsCount = 0;
            for (int[] is : result) {
                if (is[0] == itemId) {
                    existsCount++;
                }
            }
            if (existsCount > 0) {
                continue;
            }

            result.add(data[r]);
        }

        if (result.size() > 0) {
            for (int[] is : result) {
                int itemId = is[0];
                short qty = (short) is[1];
                gainItem(itemId, qty);
            }
        }
    }

    public void ħŮ��������() {
        // itemId baseQty maxRandomQty chance
        int count = 0;
        int arrLength = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(itemId) as Count, SUM(chance) as Data FROM ħŮ��������");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("Count");
                    arrLength = rs.getInt("Data");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ���߽���1֮���ʷ�ĸ����" + ex.getMessage());
        }

        if (count == 0 || arrLength == 0) {
            return;
        }

        int[][] data = new int[arrLength][2];

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemId, baseQty, maxRandomQty, chance FROM ħŮ��������");
            try (ResultSet rs = ps.executeQuery()) {
                int j = 0;
                while (rs.next()) {
                    int randomQty = new Random().nextInt(rs.getInt("maxRandomQty"));
                    for (int i = 0; i <= rs.getInt("chance") - 1; i++) {
                        data[j] = new int[]{rs.getInt("itemId"), rs.getInt("baseQty") + randomQty};
                        j++;
                    }
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯħŮ�������򵥳���" + ex.getMessage());
        }

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i <= count - 1; i++) {
            int r = new Random().nextInt(arrLength - 1);

            int itemId = data[r][0];
            int existsCount = 0;
            for (int[] is : result) {
                if (is[0] == itemId) {
                    existsCount++;
                }
            }
            if (existsCount > 0) {
                continue;
            }

            result.add(data[r]);
        }

        if (result.size() > 0) {
            for (int[] is : result) {
                int itemId = is[0];
                short qty = (short) is[1];
                gainItem(itemId, qty);
            }
        }
    }

    public void ÿ���ͻ�����() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(" SELECT * FROM  ÿ���ͻ����� ");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    double ���� = Math.ceil(Math.random() * rs.getInt("chance"));
                    double �Ա� = Math.ceil(Math.random() * 100);
                    double ���� = Math.ceil(Math.random() * rs.getInt("baseQty"));
                    double ��� = Math.ceil(Math.random() * rs.getInt("maxRandomQty"));
                    if (��� <= ����) {
                        ��� = ����;
                    }
                    if (���� >= 100) {
                        ���� = 100;
                    }
                    if (���� <= �Ա�) {
                        gainItem(rs.getInt("itemId"), (short) ���);
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ÿ���ͻ�����������");
        }
    }

    public void ÿ���ͻ�����2() {
        // itemId baseQty maxRandomQty chance
        int count = 0;
        int arrLength = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(itemId) as Count, SUM(chance) as Data FROM ÿ���ͻ�����");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("Count");
                    arrLength = rs.getInt("Data");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯÿ���ͻ�����֮���ʷ�ĸ����" + ex.getMessage());
        }

        if (count == 0 || arrLength == 0) {
            return;
        }

        int[][] data = new int[arrLength][2];

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemId, baseQty, maxRandomQty, chance FROM ÿ���ͻ�����");
            try (ResultSet rs = ps.executeQuery()) {
                int j = 0;
                while (rs.next()) {
                    int randomQty = new Random().nextInt(rs.getInt("maxRandomQty"));
                    for (int i = 0; i <= rs.getInt("chance") - 1; i++) {
                        data[j] = new int[]{rs.getInt("itemId"), rs.getInt("baseQty") + randomQty};
                        j++;
                    }
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯÿ���ͻ���������" + ex.getMessage());
        }

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i <= count - 1; i++) {
            int r = new Random().nextInt(arrLength - 1);

            int itemId = data[r][0];
            int existsCount = 0;
            for (int[] is : result) {
                if (is[0] == itemId) {
                    existsCount++;
                }
            }
            if (existsCount > 0) {
                continue;
            }

            result.add(data[r]);
        }

        if (result.size() > 0) {
            for (int[] is : result) {
                int itemId = is[0];
                short qty = (short) is[1];
                gainItem(itemId, qty);
            }
        }
    }

    public void ħŮ����������() {
        // itemId baseQty maxRandomQty chance
        int count = 0;
        int arrLength = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(itemId) as Count, SUM(chance) as Data FROM ħŮ����������");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("Count");
                    arrLength = rs.getInt("Data");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ��ħŮ����������֮���ʷ�ĸ����" + ex.getMessage());
        }

        if (count == 0 || arrLength == 0) {
            return;
        }

        int[][] data = new int[arrLength][2];

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemId, baseQty, maxRandomQty, chance FROM ħŮ����������");
            try (ResultSet rs = ps.executeQuery()) {
                int j = 0;
                while (rs.next()) {
                    int randomQty = new Random().nextInt(rs.getInt("maxRandomQty"));
                    for (int i = 0; i <= rs.getInt("chance") - 1; i++) {
                        data[j] = new int[]{rs.getInt("itemId"), rs.getInt("baseQty") + randomQty};
                        j++;
                    }
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯħŮ���������ѳ���" + ex.getMessage());
        }

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i <= count - 1; i++) {
            int r = new Random().nextInt(arrLength - 1);

            int itemId = data[r][0];
            int existsCount = 0;
            for (int[] is : result) {
                if (is[0] == itemId) {
                    existsCount++;
                }
            }
            if (existsCount > 0) {
                continue;
            }

            result.add(data[r]);
        }

        if (result.size() > 0) {
            for (int[] is : result) {
                int itemId = is[0];
                short qty = (short) is[1];
                gainItem(itemId, qty);
            }
        }
    }

    public void ���껪ʤ�߽���() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(" SELECT * FROM  ���껪ʤ�߽��� ");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    final double ���� = Math.ceil(Math.random() * rs.getInt("chance"));
                    final double �Ա� = Math.ceil(Math.random() * 100);
                    final double ���� = Math.ceil(Math.random() * rs.getInt("baseQty"));
                    double ��� = Math.ceil(Math.random() * rs.getInt("maxRandomQty"));
                    if (��� <= ����) {
                        ��� = ��� + ����;
                    }
                    if (���� >= �Ա�) {
                        gainItem(rs.getInt("itemId"), (short) ���);
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("���Խ���������");
        }
    }

    public void ���껪ʤ�߽���2() {
        // itemId baseQty maxRandomQty chance
        int count = 0;
        int arrLength = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(itemId) as Count, SUM(chance) as Data FROM ���껪ʤ�߽���");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("Count");
                    arrLength = rs.getInt("Data");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯÿ���ͻ�����֮���ʷ�ĸ����" + ex.getMessage());
        }

        if (count == 0 || arrLength == 0) {
            return;
        }

        int[][] data = new int[arrLength][2];

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemId, baseQty, maxRandomQty, chance FROM ���껪ʤ�߽���");
            try (ResultSet rs = ps.executeQuery()) {
                int j = 0;
                while (rs.next()) {
                    int randomQty = new Random().nextInt(rs.getInt("maxRandomQty"));
                    for (int i = 0; i <= rs.getInt("chance") - 1; i++) {
                        data[j] = new int[]{rs.getInt("itemId"), rs.getInt("baseQty") + randomQty};
                        j++;
                    }
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯÿ���ͻ���������" + ex.getMessage());
        }

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i <= count - 1; i++) {
            int r = new Random().nextInt(arrLength - 1);

            int itemId = data[r][0];
            int existsCount = 0;
            for (int[] is : result) {
                if (is[0] == itemId) {
                    existsCount++;
                }
            }
            if (existsCount > 0) {
                continue;
            }

            result.add(data[r]);
        }

        if (result.size() > 0) {
            for (int[] is : result) {
                int itemId = is[0];
                short qty = (short) is[1];
                gainItem(itemId, qty);
            }
        }
    }

    public void ���껪���߽���() {
        // itemId baseQty maxRandomQty chance
        int count = 0;
        int arrLength = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(itemId) as Count, SUM(chance) as Data FROM ���껪���߽���");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("Count");
                    arrLength = rs.getInt("Data");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯÿ���ͻ�����֮���ʷ�ĸ����" + ex.getMessage());
        }

        if (count == 0 || arrLength == 0) {
            return;
        }

        int[][] data = new int[arrLength][2];

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemId, baseQty, maxRandomQty, chance FROM ���껪���߽���");
            try (ResultSet rs = ps.executeQuery()) {
                int j = 0;
                while (rs.next()) {
                    int randomQty = new Random().nextInt(rs.getInt("maxRandomQty"));
                    for (int i = 0; i <= rs.getInt("chance") - 1; i++) {
                        data[j] = new int[]{rs.getInt("itemId"), rs.getInt("baseQty") + randomQty};
                        j++;
                    }
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯÿ���ͻ���������" + ex.getMessage());
        }

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i <= count - 1; i++) {
            int r = new Random().nextInt(arrLength - 1);

            int itemId = data[r][0];
            int existsCount = 0;
            for (int[] is : result) {
                if (is[0] == itemId) {
                    existsCount++;
                }
            }
            if (existsCount > 0) {
                continue;
            }

            result.add(data[r]);
        }

        if (result.size() > 0) {
            for (int[] is : result) {
                int itemId = is[0];
                short qty = (short) is[1];
                gainItem(itemId, qty);
            }
        }
    }

    public void ����������() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(" SELECT * FROM  ���������� ");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    double ���� = Math.ceil(Math.random() * rs.getInt("chance"));
                    double �Ա� = Math.ceil(Math.random() * 100);
                    double ���� = Math.ceil(Math.random() * rs.getInt("baseQty"));
                    double ��� = Math.ceil(Math.random() * rs.getInt("maxRandomQty"));
                    if (��� <= ����) {
                        ��� = ����;
                    }
                    if (���� >= 100) {
                        ���� = 100;
                    }
                    if (���� <= �Ա�) {
                        gainItem(rs.getInt("itemId"), (short) ���);
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("����������������");
        }
    }

    public void ����������2() {
        // itemId baseQty maxRandomQty chance
        int count = 0;
        int arrLength = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(itemId) as Count, SUM(chance) as Data FROM ����������");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("Count");
                    arrLength = rs.getInt("Data");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯÿ���ͻ�����֮���ʷ�ĸ����" + ex.getMessage());
        }

        if (count == 0 || arrLength == 0) {
            return;
        }

        int[][] data = new int[arrLength][2];

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemId, baseQty, maxRandomQty, chance FROM ����������");
            try (ResultSet rs = ps.executeQuery()) {
                int j = 0;
                while (rs.next()) {
                    int randomQty = new Random().nextInt(rs.getInt("maxRandomQty"));
                    for (int i = 0; i <= rs.getInt("chance") - 1; i++) {
                        data[j] = new int[]{rs.getInt("itemId"), rs.getInt("baseQty") + randomQty};
                        j++;
                    }
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯÿ���ͻ���������" + ex.getMessage());
        }

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i <= count - 1; i++) {
            int r = new Random().nextInt(arrLength - 1);

            int itemId = data[r][0];
            int existsCount = 0;
            for (int[] is : result) {
                if (is[0] == itemId) {
                    existsCount++;
                }
            }
            if (existsCount > 0) {
                continue;
            }

            result.add(data[r]);
        }

        if (result.size() > 0) {
            for (int[] is : result) {
                int itemId = is[0];
                short qty = (short) is[1];
                gainItem(itemId, qty);
            }
        }
    }

//    public void ���䱳��(int type) {
//        chr.getInventory(type).addSlot((byte) 4);
//
//    }
    public void ҡ(String text) {
        c.sendPacket(MaplePacketCreator.showEffect(text));

    }

    public void ����ʱ(int time) {

        c.sendPacket(MaplePacketCreator.getClock(time));
    }

    public void ִ��ҡ���ű�() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(3500);
                    NPCScriptManager.getInstance().dispose(c);
                    NPCScriptManager.getInstance().start(c, 9100201, 1);
                } catch (InterruptedException e) {
                }
            }
        }.start();
    }

    public void �ӳ�������() {
        new Thread() {
            public void run() {
                try {
                    c.getPlayer().dropMessage(1, "�����ļ��������IP��ַ������5��󽫶Ͽ����ӡ�");
                    Thread.sleep(5000);
                    //c.getPlayer().changeChannel(100);
                } catch (InterruptedException e) {
                }
            }
        }.start();
    }

    public void ҡ������() throws InterruptedException {
        final double r1 = Math.ceil(Math.random() * 5);
        final double r2 = Math.ceil(Math.random() * 5);
        final double r3 = Math.ceil(Math.random() * 6);
        c.sendPacket(MaplePacketCreator.showEffect("miro/frame"));
        if (r1 == 1) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR1/0"));
        } else if (r1 == 2) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR1/1"));
        } else if (r1 == 3) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR1/2"));
        } else if (r1 == 4) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR1/3"));
        } else if (r1 == 5) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR1/4"));
        }
        if (r2 == 1) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR2/0"));
        } else if (r2 == 2) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR2/1"));
        } else if (r2 == 3) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR2/2"));
        } else if (r2 == 4) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR2/3"));
        } else if (r2 == 5) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR2/4"));
        }
        if (r3 == 1) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR3/0"));
        } else if (r3 == 2) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR3/1"));
        } else if (r3 == 3) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR3/2"));
        } else if (r3 == 4) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR3/3"));
        } else if (r3 == 5) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR3/4"));
        } else if (r3 == 6) {
            c.sendPacket(MaplePacketCreator.showEffect("miro/RR3/4"));
        }
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3500);

                } catch (InterruptedException e) {
                }
            }
        }.start();

    }

    /*
    NPCScriptManager.getInstance().dispose(c);
                    NPCScriptManager.getInstance().start(c, 2000, 10);
                    if (r1 == 1 && r2 == 1 && r3 == 1) {
                        System.out.println("111");
                    } else if (r1 == 1 && r2 == 2 && r3 == 1) {
                        System.out.println("121");
                    } else if (r1 == 1 && r2 == 3 && r3 == 1) {
                        System.out.println("131");
                    } else if (r1 == 1 && r2 == 4 && r3 == 1) {
                        System.out.println("141");
                    } else if (r1 == 1 && r2 == 5 && r3 == 1) {
                        System.out.println("151");

                    } else if (r1 == 1 && r2 == 1 && r3 == 2) {
                        System.out.println("112");
                    } else if (r1 == 1 && r2 == 2 && r3 == 2) {
                        System.out.println("122");
                    } else if (r1 == 1 && r2 == 3 && r3 == 2) {
                        System.out.println("132");
                    } else if (r1 == 1 && r2 == 4 && r3 == 2) {
                        System.out.println("142");
                    } else if (r1 == 1 && r2 == 5 && r3 == 2) {
                        System.out.println("152");

                    } else if (r1 == 1 && r2 == 1 && r3 == 3) {
                        System.out.println("113");
                    } else if (r1 == 1 && r2 == 2 && r3 == 3) {
                        System.out.println("123");
                    } else if (r1 == 1 && r2 == 3 && r3 == 3) {
                        System.out.println("133");
                    } else if (r1 == 1 && r2 == 4 && r3 == 3) {
                        System.out.println("143");
                    } else if (r1 == 1 && r2 == 5 && r3 == 3) {
                        System.out.println("153");
                    }
     */
    public void ҡ������() throws InterruptedException {

        Thread.sleep(5000);

        NPCScriptManager.getInstance().dispose(c);
        NPCScriptManager.getInstance().start(c, 9900004, 1);//

        /* c.sendPacket(MaplePacketCreator.enableActions());//����⿨
        NPCScriptManager.getInstance().dispose(c);*/
    }
//////////////////////////////////////

    public void ����ҳ(String web) {//����ַ
        this.c.sendPacket(MaplePacketCreator.openWeb(web));
    }

    public void ���˻�ɫ����(String message) {
        c.sendPacket(UIPacket.getTopMsg(message));
    }

    public void ZEVMS(String txt, String txt2) {
        FileoutputUtil.logToZev(txt, txt2);
    }

    public void ȫ����ɫ����(String message) {
        for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
            for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                c.sendPacket(UIPacket.getTopMsg(message));
            }
        }
    }

    public String ��������() {//ȡ����������
        return c.getChannelServer().getServerName();
    }

    public int ��������() {
        int data = 0;
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement psu = con.prepareStatement("SELECT todayOnlineTime FROM characters WHERE id = ?");
            psu.setInt(1, c.getPlayer().getId());
            ResultSet rs = psu.executeQuery();
            if (rs.next()) {
                data = rs.getInt("todayOnlineTime");
            }
            rs.close();
            psu.close();

        } catch (SQLException ex) {
            System.err.println("��ѯ��������ʱ�����" + ex.getMessage());
        }

        return data;
    }

    public int ������() {
        int data = 0;
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement psu = con.prepareStatement("SELECT totalOnlineTime FROM characters WHERE id = ?");
            psu.setInt(1, c.getPlayer().getId());
            ResultSet rs = psu.executeQuery();
            if (rs.next()) {
                data = rs.getInt("totalOnlineTime");
            }
            rs.close();
            psu.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ������ʱ�����" + ex.getMessage());
        }

        return data;
    }

    public int ��������() {
        int count = 0;
        for (ChannelServer chl : ChannelServer.getAllInstances()) {
            count += chl.getPlayerStorage().getAllCharacters().size();
        }
        return count;
    }
    private Point position = new Point();

    public void ���() {
        MapleMap map = c.getPlayer().getMap();
        double range = Double.POSITIVE_INFINITY;
        MapleMonster mob;
        for (MapleMapObject monstermo : map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
            mob = (MapleMonster) monstermo;
            map.killMonster(mob, c.getPlayer(), true, false, (byte) 1);
        }
    }

    public void ɾ����ʹ�õĳ�ֵ��() {
        PreparedStatement ps1 = null;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM z��ȯ�һ���");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM z��ȯ�һ��� ");
            rs = ps.executeQuery();
            while (rs.next()) {
                String sqlstr = " delete from z��ȯ�һ��� where Point <= 0";
                ps1.executeUpdate(sqlstr);
            }

        } catch (SQLException ex) {
        }
    }

    public void ɾ������һ���1() {
        PreparedStatement ps1 = null;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM z����һ���1");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM z����һ���1 ");
            rs = ps.executeQuery();
            while (rs.next()) {
                String sqlstr = " delete from z����һ���1 where Point <= 0";
                ps1.executeUpdate(sqlstr);
            }

        } catch (SQLException ex) {
        }
    }

    public void ���˴浵() {
        c.getPlayer().saveToDB(false, false);
    }

    public void ��ɫID() {
        c.getPlayer().getId();
    }

    public void ����֤() {
        try {
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                    if (chr == null) {
                        continue;
                    }
                    int mapId = c.getPlayer().getMapId();
                    if (chr.getMapId() != mapId) {
                        continue;
                    }
                    NPCScriptManager.getInstance().start(c, 9900004, 17);
                }
            }
        } catch (Exception e) {
        }
    }

    public void ȫ���浵() {
        try {
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                    if (chr == null) {
                        continue;
                    }
                    chr.saveToDB(false, false);
                }
            }
        } catch (Exception e) {
        }
    }

    public void ������(final int action, final byte level, final byte masterlevel) {
        c.getPlayer().changeSkillLevel(SkillFactory.getSkill(action), level, masterlevel);
    }

    public static void �̳���Ʒ(final int id, final int key) throws SQLException {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ps = con.prepareStatement("INSERT INTO cashshop_modified_items (itemid, meso) VALUES (?, ?)");
            //дID
            ps.setInt(1, id);
            //д����
            ps.setInt(2, key);
            //д��ί
        } catch (SQLException ex) {
            Logger.getLogger(NPCConversationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void �����ϼ���(final int id, final int key, final int type, final int action, final byte level, final byte masterlevel) throws SQLException {
        //���輼����
        c.getPlayer().changeSkillLevel(SkillFactory.getSkill(action), level, masterlevel);
        c.getPlayer().dropMessage(1, "<��ʾ>\r\n5�������Զ����ߣ���1���Ӻ��ٴε�½��");
        //�浵
        c.getPlayer().saveToDB(false, false);
        new Thread() {
            @Override
            public void run() {
                try {
                    //5���Ͽ��������
                    Thread.sleep(1000 * 5);
                    c.getPlayer().getClient().getSession().close();
                    //10���ʼִ���ϼ���ָ��
                    Thread.sleep(1000 * 10);
                    Connection con = DatabaseConnection.getConnection();
                    PreparedStatement ps = null;
                    ps = con.prepareStatement("INSERT INTO keymap (characterid, `key`, `type`, `action`) VALUES (?, ?, ?, ?)");
                    //дID
                    ps.setInt(1, id);
                    //д����
                    ps.setInt(2, key);
                    //д��ί
                    ps.setInt(3, type);
                    //д����
                    ps.setInt(4, action);
                    ps.execute();
                } catch (InterruptedException e) {
                } catch (SQLException ex) {
                    Logger.getLogger(NPCConversationManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }

    public void ɾ����ɫ(int id) {
        PreparedStatement ps1 = null;
        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
        } catch (SQLException ex) {

        }
        String sqlstr = " delete from characters where id =" + id + "";
        try {
            ps1.executeUpdate(sqlstr);
            c.getPlayer().dropMessage(1, "��ɫɾ���ɹ���");
        } catch (SQLException ex) {
        }
    }

    public void ��ʼ��ʱ() {

        System.currentTimeMillis();
    }

    public void ��Ʊ() {
        GetConfigValues();
        gui.Start.ConfigValuesMap.get("ZEVMS��Ʊ1");
        gui.Start.ConfigValuesMap.get("ZEVMS��Ʊ2");
        gui.Start.ConfigValuesMap.get("ZEVMS��Ʊ3");
        gui.Start.ConfigValuesMap.get("ZEVMS��Ʊ4");
        gui.Start.ConfigValuesMap.get("ZEVMS��Ʊ5");
    }

    public void ���ظ�ħ��Ϣ() {
        GetFuMoInfo();
    }

    public int ���(final short equipmentPosition) {
        if (equipmentPosition >= 0) {
            return 0;
        }

        Connection con = DatabaseConnection.getConnection();

        // �Ȳ�ѯװ���Ĵ������
        String mxmxdDaKongFuMo = null;
        String sqlQuery1 = "SELECT b.mxmxd_dakong_fumo FROM inventoryitems a, inventoryequipment b WHERE a.inventoryitemid = b.inventoryitemid AND a.characterid = ? AND a.inventorytype = -1 AND a.position = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sqlQuery1);
            ps.setInt(1, c.getPlayer().getId());
            ps.setInt(2, equipmentPosition);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    mxmxdDaKongFuMo = rs.getString("mxmxd_dakong_fumo");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ף���ѯ��ѯװ���Ĵ�����ݳ���" + ex.getMessage());
            return 0;
        }

        if (mxmxdDaKongFuMo == null) {
            mxmxdDaKongFuMo = "";
        }

        // �ټ����װ���Ѵ������
        int dakongCount = 0;
        if (mxmxdDaKongFuMo.length() > 0) {
            dakongCount = mxmxdDaKongFuMo.split(",").length;
        }

        if (dakongCount >= 20) {
            return 0;
        }
        c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem(equipmentPosition).setDaKongFuMo(mxmxdDaKongFuMo + "0:0,");
        c.getPlayer().���ߴ浵();
        return 1;
    }

    public int ���2(final short equipmentPosition) {
        if (equipmentPosition >= 0) {
            return 0;
        }

        Connection con = DatabaseConnection.getConnection();

        // �Ȳ�ѯװ���Ĵ������
        String mxmxdDaKongFuMo = null;
        String sqlQuery1 = "SELECT b.mxmxd_dakong_fumo FROM inventoryitems a, inventoryequipment b WHERE a.inventoryitemid = b.inventoryitemid AND a.characterid = ? AND a.inventorytype = -1 AND a.position = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sqlQuery1);
            ps.setInt(1, c.getPlayer().getId());
            ps.setInt(2, equipmentPosition);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    mxmxdDaKongFuMo = rs.getString("mxmxd_dakong_fumo");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ף���ѯ��ѯװ���Ĵ�����ݳ���" + ex.getMessage());
            return 0;
        }

        if (mxmxdDaKongFuMo == null) {
            mxmxdDaKongFuMo = "";
        }

        // �ټ����װ���Ѵ������
        int dakongCount = 0;
        if (mxmxdDaKongFuMo.length() > 0) {
            dakongCount = mxmxdDaKongFuMo.split(",").length;
        }

        if (dakongCount >= 3) {
            return 0;
        }
        c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem(equipmentPosition).setDaKongFuMo(mxmxdDaKongFuMo + "0:0,");
        c.getPlayer().saveToDB(false, false);
        return 1;
    }

    /**
     * Replaces the first subsequence of the <tt>source</tt> string that matches
     * the literal target string with the specified literal replacement string.
     *
     * @param source source string on which the replacement is made
     * @param target the string to be replaced
     * @param replacement the replacement string
     * @return the resulting string
     */
    private static String replaceFirst2(String source, String target, String replacement) {
        int index = source.indexOf(target);
        if (index == -1) {
            return source;
        }

        return source.substring(0, index)
                .concat(replacement)
                .concat(source.substring(index + target.length()));
    }

    //��ţ���ħ�࣬��ħֵ
    public int ��ħ(final short equipmentPosition, final int fuMoType, final int fuMoValue) {
        if (equipmentPosition >= 0) {
            return 0;
        }

        Connection con = DatabaseConnection.getConnection();

        // �Ȳ�ѯװ���Ĵ�׸�ħ����
        String mxmxdDaKongFuMo = null;
        String sqlQuery1 = "SELECT b.mxmxd_dakong_fumo FROM inventoryitems a, inventoryequipment b WHERE a.inventoryitemid = b.inventoryitemid AND a.characterid = ? AND a.inventorytype = -1 AND a.position = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sqlQuery1);
            ps.setInt(1, c.getPlayer().getId());
            ps.setInt(2, equipmentPosition);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    mxmxdDaKongFuMo = rs.getString("mxmxd_dakong_fumo");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ħ����ѯװ���Ĵ�׸�ħ���ݳ���" + ex.getMessage());
            return 0;
        }
        if (mxmxdDaKongFuMo == null || mxmxdDaKongFuMo.length() == 0) {
            return 0;
        }

        mxmxdDaKongFuMo = replaceFirst2(mxmxdDaKongFuMo, "0:0,", String.format("%s:%s,", fuMoType, fuMoValue));
        c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem(equipmentPosition).setDaKongFuMo(mxmxdDaKongFuMo);
        String ��� = "";
        switch (fuMoType) {
            case 1:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ������Ӷ���ͨ���� " + fuMoValue + "% ���˺���";
                break;
            case 2:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ������ӶԸ߼����� " + fuMoValue + "% ���˺���";
                break;
            case 3:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ������Ӷ����й��� " + fuMoValue + "% ���˺���";
                break;
            case 4:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ������Ӷ���ͨ���� " + fuMoValue + "% ��һ����ɱ�ʡ�";
                break;
            case 5:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ������ӶԸ߼����� " + fuMoValue + "% ��һ����ɱ�ʡ�";
                break;
            case 6:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ������Ӷ����й��� " + fuMoValue + "% ��һ����ɱ�ʡ�";
                break;
            case 7:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ������� " + fuMoValue + "% ��һ����ɱֵ��";
                break;
            case 8:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ������� " + fuMoValue + "% �����ù���Ѫ��ֻʣ1��";
                break;
            case 9:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�����������ʱ��" + fuMoValue + "% ���ʹ��ﲻ���г�ޡ�";
                break;
            case 10:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������ʱ���� " + fuMoValue + " ����ʵ�˺���";
                break;
            case 21:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ������ٽ�ɫ " + fuMoValue + "% �ܵ����˺���";
                break;
            case 22:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ������ٽ�ɫ " + fuMoValue + " ���ܵ����˺���";
                break;
            case 23:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ����ܵ����˺������������ֵ 60% ʱ������� " + fuMoValue + "% ���˺���";
                break;
            case 24:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ����ó���ҩˮ�����ܵ����˺���";
                break;
            case 31:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ������� " + fuMoValue + "% �����Ծ����ȡ��";
                break;
            case 32:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ������� " + fuMoValue + "% ���ݵ㾭���ȡ��";
                break;
            case 33:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ������� " + fuMoValue + "% ���ݵ㾭���ȡ��";
                break;
            case 34:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ���������״̬������ 5 �����Ծ��顣";
                break;
            case 100:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ������� " + fuMoValue + " ���쳣���ԡ�";
                break;
            case 101:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ������� " + fuMoValue + " ���쳣���ߡ�";
                break;
            case 200:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ���ʹ��BUFF���� " + fuMoValue + "% �������������ȴ��";
                break;
            case 300:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������ʱ�������� " + fuMoValue + " �� MaxHP ��";
                break;
            case 301:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������ʱ�������� " + fuMoValue + " �� MaxMP��";
                break;
            case 302:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������ʱ�������� " + fuMoValue + " �� MaxHP��MaxMP��";
                break;
            case 303:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������ʱ " + fuMoValue + " % ���ʻ�ö���1����������������ԡ�";
                break;
            case 400:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ����ܵ��˺�ʱ " + fuMoValue + " % ���ʷ��� 10 ������̩ɽ��";
                break;
            case 401:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ����ܵ��˺�ʱ " + fuMoValue + " % ���ʷ��� 10 ����ŭ֮��";
                break;
            case 500:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ��������ٻ��� " + fuMoValue + " % ����ͨ������˺���";
                break;
            case 501:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ��������ٻ��� " + fuMoValue + " % �Ը߹�����˺���";
                break;
            case 502:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ����ٻ��޹���ʱ���� " + fuMoValue + "  ����ʵ�˺���";
                break;
            case 503:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ��������ٻ��޺���� " + fuMoValue + " % �����й�����˺���";
                break;
            case 4211002:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������[��Ҷն] ֵΪ (" + fuMoValue + ") ";
                break;
            case 4111005:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������[���ط���] ֵΪ (" + fuMoValue + ") ";
                break;
            case 1111002:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������[��������] ֵΪ (" + fuMoValue + ") ";
                break;
            case 1211002:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������[���Թ���] ֵΪ (" + fuMoValue + ") ";
                break;
            case 1311005:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������[��֮�׼�] ֵΪ (" + fuMoValue + ") ";
                break;
            case 2111002:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������[ĩ������] ֵΪ (" + fuMoValue + ") ";
                break;
            case 2211003:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������[����ǹ] ֵΪ (" + fuMoValue + ") ";
                break;
            case 2311004:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������[ʥ��] ֵΪ (" + fuMoValue + ") ";
                break;
            case 3111006:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������[��ɨ��-��] ֵΪ (" + fuMoValue + ") ";
                break;
            case 3211006:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������[��ɨ��-��] ֵΪ (" + fuMoValue + ") ";
                break;
            case 5111005:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������[���˱���] ֵΪ (" + fuMoValue + ") ";
                break;
            case 5211004:
                ��� = "[��ħϵͳ] : ��ϲ��� " + c.getPlayer().getName() + " ��ħ�ɹ�������[˫ǹ����] ֵΪ (" + fuMoValue + ") ";
                break;
            default:
                break;
        }
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ���));
        /*if (gui.Start.ConfigValuesMap.get("��ħ���ѿ���") == 0) {
            sendMsgToQQGroup(���);
        }*/
        c.getPlayer().���ߴ浵();
        return 1;
    }

    public String ��ʾ��ħЧ��() {
        StringBuilder name = new StringBuilder();

        if (c.getPlayer().getEquippedFuMoMap().get(7) != null) {
            int ���� = 10000000 + (10000000 / 100 * c.getPlayer().getEquippedFuMoMap().get(7));
            name.append("\t[#e#b��ɱֵ#k#n] : #r").append(����).append("#k\r\n");
        } else {
            name.append("\t[#e#b��ɱֵ#k#n] : #r10000000#k\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(100) != null) {
            name.append("\t[#e#b�쳣����#k#n] : ���Ӷ��쳣״̬���ԣ����ٳ��� #r").append(c.getPlayer().getEquippedFuMoMap().get(100)).append("#k % ���쳣״̬����ʱ��\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(1) != null) {
            name.append("\t[#e#bǿ��#k#n] : ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(1)).append("#k % ����ͨ������˺�\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(2) != null) {
            name.append("\t[#e#b��ǿ��#k#n] : ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(2)).append("#k % �Ը߼�������˺�\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(3) != null) {
            name.append("\t[#e#bս����־#k#n] : ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(3)).append("#k % �����й�����˺�\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(4) != null) {
            name.append("\t[#e#bӥ��#k#n] : ����ͨ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(4)).append("#k % ����һ����ɱ\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(5) != null) {
            name.append("\t[#e#b����#k#n] : �Ը߼����� #r").append(c.getPlayer().getEquippedFuMoMap().get(5)).append("#k % ����һ����ɱ\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(6) != null) {
            name.append("\t[#e#bлĻ#k#n] : �����й��� #r").append(c.getPlayer().getEquippedFuMoMap().get(6)).append("#k % ����һ����ɱ\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(8) != null) {
            name.append("\t[#e#b�������#k#n] : �������� #r").append(c.getPlayer().getEquippedFuMoMap().get(8)).append("#k % �����ù���Ѫ��ֻʣ1\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(9) != null) {
            name.append("\t[#e#b�ɱ�#k#n] : ��������ʱ #r").append(c.getPlayer().getEquippedFuMoMap().get(9)).append("#k % ���ʹ��ﲻ���г��\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(10) != null) {
            name.append("\t[#e#b׷��#k#n] : ����ʱ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(10)).append("#k ����ʵ�˺�\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(21) != null) {
            name.append("\t[#e#b����#k#n] : ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(21)).append("#k % �ܵ����˺�\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(22) != null) {
            name.append("\t[#e#b�᲻�ɴ�#k#n] : ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(22)).append("#k ���ܵ����˺�\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(23) != null) {
            name.append("\t[#e#b���#k#n] : �ܵ����˺��������������ֵ #b60%#k ʱ������� #r").append(c.getPlayer().getEquippedFuMoMap().get(23)).append("#k % �ܵ����˺�\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(24) != null) {
            name.append("\t[#e#bδ����֪#k#n] : �ó���ҩˮ�����ܵ����˺�\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(31) != null) {
            name.append("\t[#e#b��������#k#n] : ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(31)).append("#k % ���Ծ���\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(32) != null) {
            name.append("\t[#e#b��������#k#n] : ������״̬������ #r5#k �����Ծ���\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(33) != null) {
            name.append("\t[#e#b��������#k#n] : ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(33)).append("#k % �ݵ㾭��\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(34) != null) {
            name.append("\t[#e#b��Դ����#k#n] : ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(34)).append("#k % �ݵ���\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(200) != null) {
            name.append("\t[#e#b�Ż�����#k#n] : ʹ��BUFF����ʱ #r").append(c.getPlayer().getEquippedFuMoMap().get(200)).append("#k % �Ļ������������Ѿ�������ȴ�ļ���\r\n");
        }
        if (c.getPlayer().getEquippedFuMoMap().get(300) != null) {
            name.append("\t[#e#b��׳����#k#n] : ����ʱ��ö��� #r").append(c.getPlayer().getEquippedFuMoMap().get(300)).append("#k �� MaxHP\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(301) != null) {
            name.append("\t[#e#b��׳ħ��#k#n] : ����ʱ��ö��� #r").append(c.getPlayer().getEquippedFuMoMap().get(301)).append("#k �� MaxMP\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(302) != null) {
            name.append("\t[#e#b��׳����#k#n] : ����ʱ��ö��� #r").append(c.getPlayer().getEquippedFuMoMap().get(302)).append("#k ��MaxMP.MaxHP\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(303) != null) {
            name.append("\t[#e#b��������#k#n] : ����ʱ #r").append(c.getPlayer().getEquippedFuMoMap().get(303)).append("#k % ���ʻ�ö���1�����������������\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(400) != null) {
            name.append("\t[#e#b����̩ɽ#k#n] : ������ʱ #r").append(c.getPlayer().getEquippedFuMoMap().get(400)).append("#k % ���ʷ��� 10 ������̩ɽ\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(401) != null) {
            name.append("\t[#e#b��ŭ֮��#k#n] : ������ʱ #r").append(c.getPlayer().getEquippedFuMoMap().get(401)).append("#k % ���ʷ��� 10 ����ŭ֮��\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(500) != null) {
            name.append("\t[#e#bѵ���з�#k#n] : �����ٻ��� #r").append(c.getPlayer().getEquippedFuMoMap().get(500)).append("#k % ����ͨ������˺�\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(501) != null) {
            name.append("\t[#e#bѵ������#k#n] : �����ٻ��� #r").append(c.getPlayer().getEquippedFuMoMap().get(501)).append("#k % �Ը߼�������˺�\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(502) != null) {
            name.append("\t[#e#bѸ��ͻϮ#k#n] : �ٻ��޹���ʱ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(502)).append("#k ����ʵ�˺� \r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(503) != null) {
            name.append("\t[#e#b������Ϭ#k#n] : �����ٻ��޺���� #r").append(c.getPlayer().getEquippedFuMoMap().get(503)).append("#k % �����й�����˺� \r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(4211002) != null) {
            name.append("\t[#e#b��Ҷն#k#n] : ����ʱ����ӵ�еķ�Ҷ�������ӻ����˺���������Ϊ #r").append(c.getPlayer().getEquippedFuMoMap().get(4211002)).append("#k %\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(4111005) != null) {
            name.append("\t[#e#b���ط���#k#n] : ����ʱ����ӵ�еķ����������ӻ����˺���������Ϊ #r").append(c.getPlayer().getEquippedFuMoMap().get(4111005)).append("#k %\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(1111002) != null) {
            name.append("\t[#e#b��������#k#n] : ����״̬������ #r").append(c.getPlayer().getEquippedFuMoMap().get(1111002)).append("#k % �����˺�\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(1211002) != null) {
            name.append("\t[#e#b���Թ���#k#n] : ���Թ������ܣ�����״̬������ ����״̬���ӻ����˺�������״̬������Ѫ����������״̬�����ۻ��˺�����ʥ״̬������ɱ�ʡ��ο�ֵ #r").append(c.getPlayer().getEquippedFuMoMap().get(1211002)).append("#k\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(1311005) != null) {
            name.append("\t[#e#b��֮�׼�#k#n] : ��������ĿǰѪ����������֮�׼��˺���ʹ�� 5 ����֮�׼����������������� #r").append(c.getPlayer().getEquippedFuMoMap().get(1311005)).append("#k %�����˺�\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(2111002) != null) {
            name.append("\t[#e#bĩ������#k#n] : ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(2111002)).append("#k % �����˺���ÿ���˺�������һ���˺�ֵ\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(2211003) != null) {
            name.append("\t[#e#b����ǹ#k#n] : ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(2211003)).append("#k % �����˺���������һ��������ɱ��\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(2311004) != null) {
            name.append("\t[#e#bʥ��#k#n] : ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(2311004)).append("#k % �����˺���ʹ�ú��ȡһ�θ����˺����ߣ����ɴ�������\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(3111006) != null) {
            name.append("\t[#e#b��ɨ��-��#k#n] : ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(3111006)).append("#k % �����˺�\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(3211006) != null) {
            name.append("\t[#e#b��ɨ��-��#k#n] : ���� #r").append(c.getPlayer().getEquippedFuMoMap().get(3211006)).append("#k % �����˺���������һ��������ɱ��\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(5111005) != null) {
            name.append("\t[#e#b���˱���#k#n] : ���ӱ���״̬���� #r").append(c.getPlayer().getEquippedFuMoMap().get(5111005)).append("#k % �����˺��������˺�\r\n");
        }

        if (c.getPlayer().getEquippedFuMoMap().get(5211004) != null) {
            name.append("\t[#e#b˫ǹ����#k#n] : �������䣬������������ #r").append(c.getPlayer().getEquippedFuMoMap().get(5211004)).append("#k % �����˺��������˺�\r\n");
        }

        return name.toString();
    }

    /**
     * public int indexOf(int ch, int fromIndex)
     * �����ڴ��ַ����е�һ�γ���ָ���ַ�������������ָ����������ʼ����
     *
     * @param srcText
     * @param findText
     * @return
     */
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        int index = 0;
        while ((index = srcText.indexOf(findText, index)) != -1) {
            index = index + findText.length();
            count++;
        }
        return count;
    }

    // ����ֵ�ͱ�ʾ�Ѵ������
    public int ��ѯ����װ���Ѵ����(final short equipmentPosition) {
        if (equipmentPosition >= 0) {
            return 0;
        }

        Connection con = DatabaseConnection.getConnection();

        // �Ȳ�ѯװ���Ĵ������
        String mxmxdDaKongFuMo = null;
        String sqlQuery1 = "SELECT b.mxmxd_dakong_fumo FROM inventoryitems a, inventoryequipment b WHERE a.inventoryitemid = b.inventoryitemid AND a.characterid = ? AND a.inventorytype = -1 AND a.position = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sqlQuery1);
            ps.setInt(1, c.getPlayer().getId());
            ps.setInt(2, equipmentPosition);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    mxmxdDaKongFuMo = rs.getString("mxmxd_dakong_fumo");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ����װ���Ѵ��������ѯװ���Ĵ�����ݳ���" + ex.getMessage());
            return 0;
        }

        if (mxmxdDaKongFuMo == null) {
            mxmxdDaKongFuMo = "";
        }

        return appearNumber(mxmxdDaKongFuMo, ",");
    }

    // ����ֵ�ͱ�ʾ�ɸ�ħ����
    public int ��ѯ����װ���ɸ�ħ��(final short equipmentPosition) {
        if (equipmentPosition >= 0) {
            return 0;
        }

        Connection con = DatabaseConnection.getConnection();

        // �Ȳ�ѯװ���Ĵ�׸�ħ����
        String mxmxdDaKongFuMo = null;
        String sqlQuery1 = "SELECT b.mxmxd_dakong_fumo FROM inventoryitems a, inventoryequipment b WHERE a.inventoryitemid = b.inventoryitemid AND a.characterid = ? AND a.inventorytype = -1 AND a.position = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sqlQuery1);
            ps.setInt(1, c.getPlayer().getId());
            ps.setInt(2, equipmentPosition);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    mxmxdDaKongFuMo = rs.getString("mxmxd_dakong_fumo");
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ѯ����װ���ɸ�ħ������ѯװ���Ĵ�׸�ħ���ݳ���" + ex.getMessage());
            return 0;
        }

        if (mxmxdDaKongFuMo == null) {
            mxmxdDaKongFuMo = "";
        }

        return appearNumber(mxmxdDaKongFuMo, "0:0,");
    }

    // ����ֵ����0��ʾ��ϴ���
    public int ��ϴ����װ����ħ(final short equipmentPosition) {
        if (equipmentPosition >= 0) {
            return 0;
        }

        int dakongCount = ��ѯ����װ���Ѵ����(equipmentPosition);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= dakongCount; i++) {
            sb.append("0:0,");
        }

        c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem(equipmentPosition).setDaKongFuMo(sb.toString());
        c.getPlayer().saveToDB(false, false);
        return 1;
    }

    public int ��ϴ(final short equipmentPosition) {
        if (equipmentPosition >= 0) {
            return 0;
        }

        int dakongCount = ��ѯ����װ���Ѵ����(equipmentPosition);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= dakongCount; i++) {
            sb.append("0:0,");
        }

        c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem(equipmentPosition).setDaKongFuMo(sb.toString());
        c.getPlayer().saveToDB(false, false);
        return 1;
    }
//
//    public static final void Ŀ��(final int objectid) {
//        chr.getPlayer().getMap().getCharacterById(objectid);
//    }

    public void Gainrobot(String Name, int Channale, int Piot) {
        try {
            int ret = Getrobot(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO robot (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);

                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e);
                    }
                }
            }
            ret += Piot;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE robot SET `Point` = ? WHERE Name = ? and channel = ?");
            ps.setInt(1, ret);
            ps.setString(2, Name);
            ps.setInt(3, Channale);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("Getrobot!!55" + sql);
        }
    }

    public int Getrobot(String Name, int Channale) {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM robot WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public void ����2() {
        if (c.getPlayer().getCloneSize() <= Getrobot("" + c.getPlayer().getId() + "", 1)) {
            c.getPlayer().cloneLook1();
        } else {
            c.getPlayer().dropMessage(5, "�޷������ٻ�����");
        }
    }

    public void ����1() {
        if (c.getPlayer().getCloneSize() <= Getrobot("" + c.getPlayer().getId() + "", 1)) {
            c.getPlayer().cloneLook1();
        } else {
            c.getPlayer().dropMessage(5, "�޷������ٻ�����");
        }
    }

    public void ���ٷ���() {
        if (c.getPlayer().getCloneSize() > 0) {
            c.getPlayer().disposeClones();
        }
    }

    public void �����ļ�(String urlStr, String fileName, String savePath) {
        try {
            Properties �O���n = System.getProperties();
            downLoadFromUrl(urlStr, fileName, "" + �O���n.getProperty("user.dir") + "" + savePath);
        } catch (Exception e) {

        }
    }

    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //���ó�ʱ��Ϊ3��
        conn.setConnectTimeout(3 * 1000);
        //��ֹ���γ���ץȡ������403����
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //�õ�������
        InputStream inputStream = conn.getInputStream();
        //��ȡ�Լ�����
        byte[] getData = readInputStream(inputStream);

        //�ļ�����λ��
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        //System.out.println("info:" + url + " download success");

    }

    public void �������齱() {
        int sns = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT `id` FROM ��������Ʒ  ORDER BY `id` DESC LIMIT 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String SN = rs.getString("id");
                    sns = Integer.parseInt(SN);
                    sns++;
                    ps.close();
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("����" + ex.getMessage());
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(" SELECT * FROM  ��������Ʒ ");
            try (ResultSet rs = ps.executeQuery()) {
                final double ��� = Math.ceil(Math.random() * sns);
                while (rs.next()) {
                    if (rs.getInt("id") == ���) {
                        gainItem(rs.getInt("itemId"), (short) rs.getInt("cout"));
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�������������2������");
        }
    }

    public String ��ʾ�����������(int a) {
        StringBuilder name = new StringBuilder();
        name.append("#r#e��Ҫ����#k#n��������������������������������������������\r\n");
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ������ϱ� WHERE id2 = " + a + "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ��Ʒ���� = rs.getInt("��Ʒ����");
                int ��Ʒ���� = rs.getInt("��Ʒ����");
                //name.append(" #v" + ��Ʒ���� + "# #d#t" + ��Ʒ���� + "##k x [ #b" + ��Ʒ���� + "#k / #r#c" + ��Ʒ���� + "##k ]\r\n");
                name.append("\r\n#v").append(��Ʒ����).append("# #d#t").append(��Ʒ����).append("##k x [ #b").append(��Ʒ����).append("#k / #r#c").append(��Ʒ����).append("##k ]\r\n");
            }
        } catch (SQLException ex) {
        }
        return name.toString();
    }

    /*
    1.��ȯ
    2.���
    
     */
    public int �жϲ����Ƿ��㹻(int a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ������ϱ� WHERE id2 = " + a + "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data += 1;
                int ��Ʒ���� = rs.getInt("��Ʒ����");
                int ��Ʒ���� = rs.getInt("��Ʒ����");
                if (��Ʒ���� == 0) {
                    if (getPlayer().getMeso() <= ��Ʒ����) {
                        data -= 100;
                    }
                } else if (��Ʒ���� == 1) {
                    if (c.getPlayer().getCSPoints(1) <= ��Ʒ����) {
                        data -= 100;
                    }
                } else if (!haveItem(��Ʒ����, ��Ʒ����)) {
                    data -= 100;
                }
            }
        } catch (SQLException ex) {
        }
        //System.err.println(data);
        return data;
    }

    public int ��ȡ��������(int a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ������ϱ� WHERE id2 = " + a + "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data += 1;
                int ��Ʒ���� = rs.getInt("��Ʒ����");
                int ��Ʒ���� = rs.getInt("��Ʒ����");
                if (��Ʒ���� == 0) {
                    c.getPlayer().gainMeso(-��Ʒ����, true, false, true);
                } else if (��Ʒ���� == 1) {
                    c.getPlayer().modifyCSPoints(1, -��Ʒ����, true);
                } else {
                    gainItem(��Ʒ����, (short) -��Ʒ����);
                }
            }
        } catch (SQLException ex) {
        }
        //System.err.println(data);
        return data;
    }

    public String ��ʾ��Ʒ(int a) {
        String data = "";
        data = "#v" + a + "# #b#z" + a + "##k";
        return data;
    }

    /*
    0��״̬
    1��ӡ
    
     */
    public String ��ʾ���콱����Ϣ(int a) {
        StringBuilder name = new StringBuilder();
        name.append("#r#e�������#n#k��������������������������������������������\r\n");
        name.append("number: #g" + a + "#k\r\n");
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ������Ʒ�� WHERE id2 = " + a + "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ��Ʒ���� = rs.getInt("��Ʒ����");
                int ��Ʒ���� = rs.getInt("��Ʒ����");
                name.append("\r\n#v").append(��Ʒ����).append("# #d#t").append(��Ʒ����).append("##k x [ #r").append(��Ʒ����).append("#k ]\r\n");
                if (rs.getInt("����������") > 0) {
                    name.append("\r\n[��������:#r").append(rs.getInt("����������")).append("#k]");
                }
                if (��Ʒ���� < 2000000) {
                    switch (rs.getInt("��")) {
                        case 1:
                            name.append("\r\n[״̬:#r").append("��ӡ").append("#k]");
                            break;
                        case 2:
                            name.append("\r\n[״̬:#r").append("����").append("#k]");
                            break;
                        case 3:
                            name.append("\r\n[״̬:#r").append("��ӡ,����").append("#k]");
                            break;
                        case 4:
                            name.append("\r\n[״̬:#r").append("����").append("#k]");
                            break;
                        case 5:
                            name.append("\r\n[״̬:#r").append("��ӡ,����").append("#k]");
                            break;
                        case 6:
                            name.append("\r\n[״̬:#r").append("����,����").append("#k]");
                            break;
                        case 7:
                            name.append("\r\n[״̬:#r").append("��ӡ,����,����").append("#k]");
                            break;
                        case 8:
                            name.append("\r\n[״̬:#r").append("���ɽ���").append("#k]");
                            break;
                        case 9:
                            name.append("\r\n[״̬:#r").append("��ӡ,���ɽ���").append("#k]");
                            break;
                        case 10:
                            name.append("\r\n[״̬:#r").append("����,���ɽ���").append("#k]");
                            break;
                        case 11:
                            name.append("\r\n[״̬:#r").append("��ӡ,����,���ɽ���").append("#k]");
                            break;
                        case 12:
                            name.append("\r\n[״̬:#r").append("����,���ɽ���").append("#k]");
                            break;
                        case 13:
                            name.append("\r\n[״̬:#r").append("��ӡ,����,���ɽ���").append("#k]");
                            break;
                        case 14:
                            name.append("\r\n[״̬:#r").append("����,����,���ɽ���").append("#k]");
                            break;
                        case 15:
                            name.append("\r\n[״̬:#r").append("��ӡ,����,����,���ɽ���").append("#k]");
                            break;
                        default:
                            break;
                    }
                }
                if (rs.getInt("����") > 0) {
                    name.append("\r\n[����:#r").append(rs.getInt("����")).append("#k]");
                }
                if (rs.getInt("����") > 0) {
                    name.append("\r\n[����:#r").append(rs.getInt("����")).append("#k]");
                }
                if (rs.getInt("����") > 0) {
                    name.append("\r\n[����:#r").append(rs.getInt("����")).append("#k]");
                }
                if (rs.getInt("����") > 0) {
                    name.append("\r\n[����:#r").append(rs.getInt("����")).append("#k]");
                }
                if (rs.getInt("HP") > 0) {
                    name.append("\r\n[HP:#r").append(rs.getInt("HP")).append("#k]");
                }
                if (rs.getInt("MP") > 0) {
                    name.append("\r\n[MP:#r").append(rs.getInt("MP")).append("#k]");
                }
                if (rs.getInt("��������") > 0) {
                    name.append("\r\n[��������:#r").append(rs.getInt("��������")).append("#k]");
                }
                if (rs.getInt("���������") > 0) {
                    name.append("\r\n[���������:#r").append(rs.getInt("���������")).append("#k]");
                }
                if (rs.getInt("ħ��������") > 0) {
                    name.append("\r\n[ħ��������:#r").append(rs.getInt("ħ��������")).append("#k]");
                }
                if (rs.getInt("ħ��������") > 0) {
                    name.append("\r\n[ħ��������:#r").append(rs.getInt("ħ��������")).append("#k]");
                }
                if (rs.getInt("������") > 0) {
                    name.append("\r\n[������:#r").append(rs.getInt("������")).append("#k]");
                }
                if (rs.getInt("�ر���") > 0) {
                    name.append("\r\n[�ر���:#r").append(rs.getInt("�ر���")).append("#k]");
                }
                if (rs.getInt("��Ծ��") > 0) {
                    name.append("\r\n[��Ծ��:#r").append(rs.getInt("��Ծ��")).append("#k]");
                }
                if (rs.getInt("�ƶ��ٶ�") > 0) {
                    name.append("\r\n[�ƶ��ٶ�:#r").append(rs.getInt("�ƶ��ٶ�")).append("#k]");
                }
                if (rs.getInt("��ʱ") > 0) {
                    name.append("\r\n[��ʱ:#r").append(rs.getInt("��ʱ")).append("#k]");
                }
            }
        } catch (SQLException ex) {
        }
        return name.toString();
    }

    public void �����콱��(int a) {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ������Ʒ�� WHERE id2 = " + a + "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ��Ʒ���� = rs.getInt("��Ʒ����");
                int ��Ʒ���� = rs.getInt("��Ʒ����");
                if (��Ʒ���� > 2000000) {
                    gainItem(��Ʒ����, (short) ��Ʒ����);
                } else {
                    ��װ��(rs.getInt("��Ʒ����"),
                            rs.getInt("����������"),
                            rs.getInt("��"),
                            rs.getInt("����"),
                            rs.getInt("����"),
                            rs.getInt("����"),
                            rs.getInt("����"),
                            rs.getInt("HP"),
                            rs.getInt("MP"),
                            rs.getInt("��������"),
                            rs.getInt("���������"),
                            rs.getInt("ħ��������"),
                            rs.getInt("ħ��������"),
                            rs.getInt("�ر���"),
                            rs.getInt("������"),
                            rs.getInt("��Ծ��"),
                            rs.getInt("�ƶ��ٶ�"),
                            rs.getInt("��ʱ")
                    );
                }
            }
        } catch (SQLException ex) {
        }
    }

    public final void ��װ��(final int id, final int sj, final int Flag, final int str, final int dex, final int luk, final int Int, final int hp, int mp, int watk, int matk, int wdef, int mdef, int hb, int mz, int ty, int yd, int period) {
        ��װ��(id, sj, Flag, str, dex, luk, Int, hp, mp, watk, matk, wdef, mdef, hb, mz, ty, yd, c, period);
    }

    public final void ��װ��(final int id, final int sj, final int Flag, final int str, final int dex, final int luk, final int Int, final int hp, int mp, int watk, int matk, int wdef, int mdef, int hb, int mz, int ty, int yd, final MapleClient cg, int period) {
        if (1 >= 0) {
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final MapleInventoryType type = GameConstants.getInventoryType(id);

            if (!MapleInventoryManipulator.checkSpace(cg, id, 1, "")) {
                return;
            }
            if (type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(id) && !GameConstants.isBullet(id)) {
                final Equip item = (Equip) (ii.getEquipById(id));
                if (period > 0) {
                    item.setExpiration(System.currentTimeMillis() + (period * 60 * 60 * 1000));
                }
                final String name = ii.getName(id);
                if (id / 10000 == 114 && name != null && name.length() > 0) { //medal
                    final String msg = "���ѻ�óƺ� <" + name + ">";
                    cg.getPlayer().dropMessage(5, msg);
                }
                if (sj > 0) {
                    item.setUpgradeSlots((byte) (short) sj);
                }
                if (Flag > 0) {
                    item.setFlag((byte) (short) Flag);
                }
                if (str > 0) {
                    item.setStr((short) str);
                }
                if (dex > 0) {
                    item.setDex((short) dex);
                }
                if (luk > 0) {
                    item.setLuk((short) luk);
                }
                if (Int > 0) {
                    item.setInt((short) Int);
                }
                if (hp > 0) {
                    item.setHp((short) hp);
                }
                if (mp > 0) {
                    item.setMp((short) mp);
                }
                if (watk > 0) {
                    item.setWatk((short) watk);
                }
                if (matk > 0) {
                    item.setMatk((short) matk);
                }
                if (wdef > 0) {
                    item.setWdef((short) wdef);
                }
                if (mdef > 0) {
                    item.setMdef((short) mdef);
                }
                if (hb > 0) {
                    item.setAvoid((short) hb);
                }
                if (mz > 0) {
                    item.setAcc((short) mz);
                }
                if (ty > 0) {
                    item.setJump((short) ty);
                }
                if (yd > 0) {
                    item.setSpeed((short) yd);
                }
                MapleInventoryManipulator.addbyItem(cg, item.copy());
            } else {
                MapleInventoryManipulator.addById(cg, id, (short) 1, "", (byte) 0);
            }
        } else {
            MapleInventoryManipulator.removeById(cg, GameConstants.getInventoryType(id), id, -1, true, false);
        }
        cg.sendPacket(MaplePacketCreator.getShowItemGain(id, (short) 1, true));
    }

    public static int ȡװ������(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemid as DATA FROM inventoryitems WHERE inventoryitemid = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ȡװ�����롢����");
        }
        return data;
    }

    public static int ȡװ��ӵ����(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT characterid as DATA FROM inventoryitems WHERE inventoryitemid = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ȡװ�����롢����");
        }
        return data;
    }

    public String �ȼ��ɾ�(int a) {
        StringBuilder name = new StringBuilder();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Upgrade_career order by id desc");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("id2") == a) {
                    name.append("        " + rs.getString("shijian") + "  " + rs.getString("name") + "\r\n");
                }
            }
        } catch (SQLException ex) {
        }
        return name.toString();
    }

    public static String ��ʾ����() {
        return server.custom.capture.capture_yongfa.��ʾ������Ա();
    }

    public static void �μ�(int a) {
        server.custom.capture.capture_yongfa.�μ�(a);
    }

    public static int �Ƿ�μ�(int a) {
        return server.custom.capture.capture_yongfa.�ж��Ƿ��Ѿ��μ�(a);
    }

    public static int �ж϶���(int a) {
        return server.custom.capture.capture_yongfa.�ж϶���(a);
    }

    public static String ɱ������(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT count as DATA FROM mxmxd_mapkilledcount WHERE chrId = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ɱ������������");
        }
        return data;
    }

    public final void ��ʱ��ս��() {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000 * 60 * 1);
    }

    public String ����Զ����() {
        StringBuilder name = new StringBuilder();
        name.append("����Զ���ӣ�\r\n");
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characterz WHERE channel = 201 ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("Point") > 0) {
                    String ��� = "";
                    if (rs.getInt("Point") == 1) {
                        ��� = "[#b��Ա#k]";
                    } else {
                        ��� = "[#r�ӳ�#k]";
                    }
                    name.append("" + ��� + "  ���: #d" + ��ɫIDȡ����(rs.getInt("Name")) + "#k \r\n");
                }
            }
        } catch (SQLException ex) {
        }
        return name.toString();
    }

    public void ��ʼ����Զ��(int a, int b) {
        for (ChannelServer CS : ChannelServer.getAllInstances()) {
            for (final MapleCharacter mch : CS.getPlayerStorage().getAllCharactersThreadSafe()) {
                if (mch.Getcharacterz("" + mch.getId() + "", 201) > 0) {
                    if (mch.getClient().getChannel() != a) {
                        mch.changeChannel(a);
                    }
                    if (mch.getMapId() != b) {
                        mch.changeMap(b, 0);
                    }
                }
            }
        }
    }

    /**
     ******************************************************************************************************************************************************
     * ��ֵϵͳ
     * *****************************************************************************************************************************************************
     */
    public static int �ж϶һ����Ƿ����(String id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT code as DATA FROM nxcodez WHERE code = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data += 1;
                }
            }
        } catch (SQLException Ex) {
            System.err.println("�ж϶һ����Ƿ���ڡ�����");
        }
        return data;
    }

    public static int �ж϶һ�������(String code) throws SQLException {
        int item = -1;
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT `leixing` FROM nxcodez WHERE code = ?");
        ps.setString(1, code);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            item = rs.getInt("leixing");
        }
        /*rs.close();
        ps.close();*/
        return item;
    }

    public static int �ж϶һ�������(String code) throws SQLException {
        int item = -1;
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT `valid` FROM nxcodez WHERE code = ?");
        ps.setString(1, code);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            item = rs.getInt("valid");
        }
        /*rs.close();
        ps.close();*/
        return item;
    }

    public static int �ж϶һ������(String code) throws SQLException {
        int item = -1;
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT `itme` FROM nxcodez WHERE code = ?");
        ps.setString(1, code);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            item = rs.getInt("itme");
        }
        /*rs.close();
        ps.close();*/
        return item;
    }

    //ɾ���һ���
    public void Deleteexchangecard(String a) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM nxcodez ");
            rs = ps1.executeQuery();
            while (rs.next()) {
                String sqlstr = " delete from nxcodez where code = '" + a + "' ";
                ps1.executeUpdate(sqlstr);
            }
        } catch (SQLException ex) {
        }
    }

    /**
     ******************************************************************************************************************************************************
     * ��������
     * *****************************************************************************************************************************************************
     * @param id
     * @return
     */
    public String ��ʾ��Ʒ(int id) {
        StringBuilder name = new StringBuilder();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM mysterious WHERE f = " + id + "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ��� = rs.getInt("a");
                int ��Ʒ = rs.getInt("b");
                int ���� = rs.getInt("c");
                int ��ȯ = rs.getInt("d");
                int ��� = rs.getInt("e");
                name.append("   #L").append(���).append("# #v").append(��Ʒ).append("# #b#t").append(��Ʒ).append("##k x ").append(����).append("");
                name.append(" #d[ȯ/��]:#b").append(��ȯ).append("#k/#b").append(���).append("#k#l\r\n");
            }
        } catch (SQLException ex) {
        }
        return name.toString();
    }

    public void ������Ʒ(int id) {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM mysterious WHERE f = " + id + "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ��� = rs.getInt("a");
                int ��Ʒ = rs.getInt("b");
                int ���� = rs.getInt("c");
                int ��ȯ = rs.getInt("d");
                int ��� = rs.getInt("e");
                gainItem(��Ʒ, (short) ����);
            }

        } catch (SQLException ex) {
        }
    }

    /**
     ******************************************************************************************************************************************************
     * ����ϵͳ
     * *****************************************************************************************************************************************************
     */
    public String ��ʾ�ʼ�(int id) {
        StringBuilder name = new StringBuilder();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM mail");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("juese") == id) {
                    int �ʼ���� = rs.getInt("number");
                    String �ʼ����� = rs.getString("biaoti");
                    name.append("    #L").append(�ʼ����).append("##fUI/UIWindow.img/Delivery/icon4##b").append(�ʼ�����).append("#k#l\r\n");
                }
            }
        } catch (SQLException ex) {
        }
        return name.toString();
    }

    public void ��ȡ�ʼ�(int id) {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM mail");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("number") == id) {
                    int ���� = rs.getInt("shuliang1");
                    int ���� = rs.getInt("type1");
                    if (rs.getInt("type1") != 0) {
                        switch (����) {
                            case 4:
                                c.getPlayer().gainMeso(����, true, false, true);
                                break;
                            case 1:
                                c.getPlayer().modifyCSPoints(1, ����, true);
                                break;
                            case 2:
                                c.getPlayer().modifyCSPoints(2, ����, true);
                                break;
                            case 3:
                                c.getPlayer().gainExp(����, true, true, true);
                                break;
                            default:
                                gainItem(����, (short) ����);
                                break;
                        }
                    }
                    int ����2 = rs.getInt("shuliang2");
                    int ����2 = rs.getInt("type2");
                    if (rs.getInt("type2") != 0) {
                        switch (����2) {
                            case 4:
                                c.getPlayer().gainMeso(����2, true, false, true);
                                break;
                            case 1:
                                c.getPlayer().modifyCSPoints(1, ����2, true);
                                break;
                            case 2:
                                c.getPlayer().modifyCSPoints(2, ����2, true);
                                break;
                            case 3:
                                c.getPlayer().gainExp(����2, true, true, true);
                                break;
                            default:
                                gainItem(����2, (short) ����2);
                                break;
                        }
                    }

                    int ����3 = rs.getInt("shuliang3");
                    int ����3 = rs.getInt("type3");
                    if (rs.getInt("type3") != 0) {
                        switch (����3) {
                            case 4:
                                c.getPlayer().gainMeso(����3, true, false, true);
                                break;
                            case 1:
                                c.getPlayer().modifyCSPoints(1, ����3, true);
                                break;
                            case 2:
                                c.getPlayer().modifyCSPoints(2, ����3, true);
                                break;
                            case 3:
                                c.getPlayer().gainExp(����3, true, true, true);
                                break;
                            default:
                                MapleInventoryManipulator.addById(c, ����3, (short) ����3, "", null, (byte) 0);
                                gainItem(����3, (short) ����3);
                                break;
                        }
                    }
                    PreparedStatement ps1 = null;
                    ResultSet rs1 = null;
                    try {
                        ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM mail WHERE number = ?");
                        ps1.setInt(1, id);
                        rs1 = ps1.executeQuery();
                        if (rs1.next()) {
                            String sqlstr = " delete from mail where number = " + id + "";
                            ps1.executeUpdate(sqlstr);
                        }
                    } catch (SQLException ex) {

                    }
                }
            }
        } catch (SQLException ex) {
        }
    }

    public String ��ʾ�ʼ�����(int id) {
        StringBuilder name = new StringBuilder();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM mail");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("number") == id) {
                    String �ʼ����� = rs.getString("biaoti");
                    String �ʼ�ʱ�� = rs.getString("shijian");
                    String �ʼ����� = rs.getString("wenben");
                    name.append("����: #b").append(�ʼ�����).append("#k\r\n");
                    name.append("ʱ��: #b").append(�ʼ�ʱ��).append("#k\r\n");
                    name.append("����: #bϵͳ����Ա#k\r\n");
                    name.append("����: #b").append(�ʼ�����).append("#k\r\n\r\n");
                    //4=��ң�1=��ȯ��2=���ã�3=����
                    if (rs.getInt("type1") != 0) {
                        switch (rs.getInt("type1")) {
                            case 4:
                                name.append("[����]:#d��� x #b").append(rs.getInt("shuliang1")).append("#k\r\n");
                                break;
                            case 1:
                                name.append("[����]:#d��ȯ x #b").append(rs.getInt("shuliang1")).append("#k\r\n");
                                break;
                            case 2:
                                name.append("[����]:#d���� x #b").append(rs.getInt("shuliang1")).append("#k\r\n");
                                break;
                            case 3:
                                name.append("[����]:#d���� x #b").append(rs.getInt("shuliang1")).append("#k\r\n");
                                break;
                            default:
                                name.append("[����]:#v").append(rs.getInt("type1")).append("# #b#t").append(rs.getInt("type1")).append("##k x #b").append(rs.getInt("shuliang1")).append("#k\r\n");
                                break;
                        }
                    }
                    if (rs.getInt("type2") != 0) {
                        switch (rs.getInt("type2")) {
                            case 4:
                                name.append("[����]:#d��� x #b").append(rs.getInt("shuliang2")).append("#k\r\n");
                                break;
                            case 1:
                                name.append("[����]:#d��ȯ x #b").append(rs.getInt("shuliang2")).append("#k\r\n");
                                break;
                            case 2:
                                name.append("[����]:#d���� x #b").append(rs.getInt("shuliang2")).append("#k\r\n");
                                break;
                            case 3:
                                name.append("[����]:#d���� x #b").append(rs.getInt("shuliang2")).append("#k\r\n");
                                break;
                            default:
                                name.append("[����]:#v").append(rs.getInt("type2")).append("# #b#t").append(rs.getInt("type2")).append("##k x #b").append(rs.getInt("shuliang2")).append("#k\r\n");
                                break;
                        }
                    }
                    if (rs.getInt("type3") != 0) {
                        switch (rs.getInt("type3")) {
                            case 4:
                                name.append("[����]:#d��� x #b").append(rs.getInt("shuliang3")).append("#k\r\n");
                                break;
                            case 1:
                                name.append("[����]:#d��ȯ x #b").append(rs.getInt("shuliang3")).append("#k\r\n");
                                break;
                            case 2:
                                name.append("[����]:#d���� x #b").append(rs.getInt("shuliang3")).append("#k\r\n");
                                break;
                            case 3:
                                name.append("[����]:#d���� x #b").append(rs.getInt("shuliang3")).append("#k\r\n");
                                break;
                            default:
                                name.append("[����]:#v").append(rs.getInt("type3")).append("# #b#t").append(rs.getInt("type3")).append("##k x #b").append(rs.getInt("shuliang3")).append("#k\r\n");
                                break;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
        }
        return name.toString();
    }

    public void ɾ���ʼ�(int a) {
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;
        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM mail WHERE number = ?");
            ps1.setInt(1, a);
            rs1 = ps1.executeQuery();
            if (rs1.next()) {
                String sqlstr = " delete from mail where number = " + a + "";
                ps1.executeUpdate(sqlstr);
            }
        } catch (SQLException ex) {

        }
    }

    /**
     ******************************************************************************************************************************************************
     * ����ϵͳ 1.��ֳ������<id>
     * 2.��ֳ��������<mingzi>
     * 3.��ֳ��������<name>
     * 4.��ֳ��������<cid>
     * 5.��ֳ����ֲ�ﾭ��
     * *****************************************************************************************************************************************************
     */
    /**
     ******************************************************************************************************************************************************
     * ��������
     *
     * *****************************************************************************************************************************************************
     * @return
     */
    public String ��ʾ��������������Ҫ�ľ���() {
        StringBuilder name = new StringBuilder();
        String �ȼ� = "";
        for (int i = 1; i <= 30; i++) {
            �ȼ� = "װ���ȼ�" + i + "";
            name.append("�ȼ� [" + �ȼ� + "], ��Ҫ���� (" + gui.Start.ConfigValuesMap.get(�ȼ�) + ")\r\n");
        }
        return name.toString();
    }

    /**
     ******************************************************************************************************************************************************
     * ���͵�
     *
     * *****************************************************************************************************************************************************
     */
    public int �жϴ��͵�x(int id, int cid) {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM awarp WHERE id = ? and cid = ?");
            ps.setInt(1, id);
            ps.setInt(2, cid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("x");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public int �жϴ��͵�y(int id, int cid) {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM awarp WHERE id = ? and cid = ?");
            ps.setInt(1, id);
            ps.setInt(2, cid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("y");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public void ���ô��͵�x(int id, int cid, int x) {
        try {
            int ret = �жϴ��͵�x(id, cid);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO awarp (id, cid,x) VALUES (?, ?, ?)");
                    ps.setInt(1, id);
                    ps.setInt(2, cid);
                    ps.setInt(3, x);
                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("���ô��͵�x1:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("���ô��͵�x2:" + e);
                    }
                }
            } else {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("UPDATE awarp SET `x` = ? WHERE id = ? and cid = ?");
                ps.setInt(1, x);
                ps.setInt(2, id);
                ps.setInt(3, cid);
                ps.execute();
                ps.close();
            }
        } catch (SQLException sql) {
            System.err.println("���ô��͵�x3" + sql);
        }
    }

    public void ���ô��͵�y(int id, int cid, int y) {
        try {
            int ret = �жϴ��͵�x(id, cid);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO awarp (id, cid,x) VALUES (?, ?, ?)");
                    ps.setInt(1, id);
                    ps.setInt(2, cid);
                    ps.setInt(3, y);

                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("���ô��͵�y1:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("���ô��͵�y2:" + e);
                    }
                }
            } else {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("UPDATE awarp SET `y` = ? WHERE id = ? and cid = ?");
                ps.setInt(1, y);
                ps.setInt(2, id);
                ps.setInt(3, cid);
                ps.execute();
                ps.close();
            }
        } catch (SQLException sql) {
            System.err.println("���ô��͵�y3" + sql);
        }
    }

    /**
     ******************************************************************************************************************************************************
     * *���񷢲�Ŀ¼���<task>
     * ������<a>
     * ���񷢲���<b>
     * ���񷢲�ʱ��<c>
     * �����Ƿ����<d>
     * ������ɵ���<e>
     * �������<f>
     * ��������<g>
     * *���񷢲��ռ����ϱ�<task_1>
     * ������<a>
     * ����������ϱ��<b>
     * ���������������<c>
     * *���񷢲��ռ����ϱ�<task_2>
     * ������<a>
     * ����������<b> * 1��ȯ��2���ã�3��ң�4���飬5���� ����������<c>
     * *****************************************************************************************************************************************************
     */
    public String ��ʾ��������(int id) {
        StringBuilder name = new StringBuilder();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM mail");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("a") == id) {
                    String �ʼ����� = rs.getString("f");
                    String �ʼ�ʱ�� = rs.getString("c");
                    String �ʼ����� = rs.getString("g");
                    String ������ = ��ɫIDȡ����(rs.getInt("b"));
                    name.append("�������: #b").append(�ʼ�����).append("#k\r\n");
                    name.append("����ʱ��: #b").append(�ʼ�ʱ��).append("#k\r\n");
                    name.append("��������: #b").append(�ʼ�����).append("#k\r\n");
                    name.append("�� �� ��: #b").append(������).append("#k\r\n\r\n");
                }
            }
        } catch (SQLException ex) {
        }
        name.append("��������\r\n");
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM mail_1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("a") == id) {
                    int ��Ҫ���� = rs.getInt("b");
                    int ��Ҫ���� = rs.getInt("c");
                    switch (rs.getInt("��Ҫ����")) {
                        case 1:
                            name.append("[����]:#d��ȯ x #b" + ��Ҫ���� + "#k\r\n");
                            break;
                        case 2:
                            name.append("[����]:#d���� x #b" + ��Ҫ���� + "#k\r\n");
                            break;
                        case 3:
                            name.append("[����]:#d��� x #b" + ��Ҫ���� + "#k\r\n");
                            break;
                        case 4:
                            name.append("[����]:#d���� x #b" + ��Ҫ���� + "#k\r\n");
                            break;
                        case 5:
                            name.append("[����]:#d���� x #b" + ��Ҫ���� + "#k\r\n");
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (SQLException ex) {
        }
        name.append("������\r\n");

        return name.toString();

    }

    public String ��ʾ����Ŀ¼() {
        StringBuilder name = new StringBuilder();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM task");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ���� = "";
                if (rs.getInt("d") == 0) {
                    ���� = "#r[δ���]#k";
                } else {
                    ���� = "#g[�����]#k";
                }

                int ������ = rs.getInt("a");
                String ������� = rs.getString("f");
                name.append(" #L" + ������ + "#" + ���� + " #b" + ������� + "#k#l\r\n");
            }
        } catch (SQLException ex) {
        }
        return name.toString();
    }

    /**
     * ****************************************************************************************************************************************************
     * ���а�
     *
     * *****************************************************************************************************************************************************
     * @return
     */
    //�����а�
    public String �ȼ����а�() {
        int ���� = 1;
        StringBuilder name = new StringBuilder();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters  WHERE gm = 0 order by level desc");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("level") < gui.Start.ConfigValuesMap.get("ð�ռҵȼ�����") && rs.getInt("level") > 30) {
                    if (���� < 10) {
                        String ������� = rs.getString("name");
                        String ְҵ = ְҵ(rs.getInt("job"));
                        name.append("Top.#e#d").append(����).append("#n#k   ");
                        name.append("#b").append(�������).append("#k");
                        for (int j = 13 - �������.getBytes().length; j > 0; j--) {
                            name.append(" ");
                        }
                        name.append("  ").append(ְҵ).append("");
                        for (int j = 15 - ְҵ.getBytes().length; j > 0; j--) {
                            name.append(" ");
                        }
                        name.append("  Lv.#d").append(rs.getInt("level")).append("#k\r\n");
                        ����++;
                    } else if (���� >= 10 && ���� <= 99) {
                        String ������� = rs.getString("name");
                        String ְҵ = ְҵ(rs.getInt("job"));
                        name.append("Top.#e#d").append(����).append("#n#k  ");
                        name.append("#b").append(�������).append("#k");
                        for (int j = 13 - �������.getBytes().length; j > 0; j--) {
                            name.append(" ");
                        }
                        name.append("  ").append(ְҵ).append("");
                        for (int j = 15 - ְҵ.getBytes().length; j > 0; j--) {
                            name.append(" ");
                        }
                        name.append("  Lv.#d").append(rs.getInt("level")).append("#k\r\n");
                        ����++;
                    } else if (���� > 99) {
                        String ������� = rs.getString("name");
                        String ְҵ = ְҵ(rs.getInt("job"));
                        name.append("Top.#e#d").append(����).append("#n#k ");
                        name.append("#b").append(�������).append("#k");
                        for (int j = 13 - �������.getBytes().length; j > 0; j--) {
                            name.append(" ");
                        }
                        name.append("  ").append(ְҵ).append("");
                        for (int j = 15 - ְҵ.getBytes().length; j > 0; j--) {
                            name.append(" ");
                        }
                        name.append("  Lv.#d").append(rs.getInt("level")).append("#k\r\n");
                        ����++;

                    }
                }
            }
        } catch (SQLException ex) {
        }
        name.append("\r\n\r\n");
        return name.toString();
    }

    public String �������а�() {
        int ���� = 1;
        StringBuilder name = new StringBuilder();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters  WHERE gm = 0 order by level desc");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("level") == gui.Start.ConfigValuesMap.get("ð�ռҵȼ�����")) {
                    String ������� = rs.getString("name");
                    String ְҵ = ְҵ(rs.getInt("job"));
                    int ������ = rs.getInt("guildid");
                    name.append("    ");
                    name.append("#b").append(�������).append("#k");
                    for (int j = 13 - �������.getBytes().length; j > 0; j--) {
                        name.append(" ");
                    }
                    name.append("  ").append(ְҵ).append("");
                    for (int j = 15 - ְҵ.getBytes().length; j > 0; j--) {
                        name.append(" ");
                    }
                    name.append("����.#d").append(��ȡ��������(������)).append("#k\r\n");
                }
            }
        } catch (SQLException ex) {
        }
        name.append("\r\n\r\n");
        return name.toString();
    }

    public String ���￨Ƭ���а�() {
        int ���� = 1;
        StringBuilder name = new StringBuilder();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM monsterbook   order by level desc");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("level") == gui.Start.ConfigValuesMap.get("ð�ռҵȼ�����")) {
                    String ������� = rs.getString("name");
                    String ְҵ = ְҵ(rs.getInt("job"));
                    int ������ = rs.getInt("guildid");
                    name.append("    ");
                    name.append("#b").append(�������).append("#k");
                    for (int j = 13 - �������.getBytes().length; j > 0; j--) {
                        name.append(" ");
                    }
                    name.append("  ").append(ְҵ).append("");
                    for (int j = 15 - ְҵ.getBytes().length; j > 0; j--) {
                        name.append(" ");
                    }
                    name.append("����.#d").append(��ȡ��������(������)).append("#k\r\n");
                }
            }
        } catch (SQLException ex) {
        }
        name.append("\r\n\r\n");
        return name.toString();
    }

    //�����а�
    public String �Ƹ����а�() {
        int ���� = 1;
        StringBuilder name = new StringBuilder();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE gm = 0 order by meso desc LIMIT 20 ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("meso") > 0) {
                    if (���� < 10) {
                        String ������� = rs.getString("name");
                        String ��� = rs.getString("meso");
                        name.append("Top.#e#d").append(����).append("#n#k   ");
                        name.append("#b").append(�������).append("#k");
                        for (int j = 13 - �������.getBytes().length; j > 0; j--) {
                            name.append(" ");
                        }
                        name.append("     Meso.#d").append(���).append("#n\r\n");

                        ����++;
                    } else if (���� >= 10 && ���� <= 20) {
                        String ������� = rs.getString("name");
                        String ��� = rs.getString("meso");
                        name.append("Top.#e#d").append(����).append("#n#k  ");
                        name.append("#b").append(�������).append("#k");
                        for (int j = 13 - �������.getBytes().length; j > 0; j--) {
                            name.append(" ");
                        }
                        name.append("     Meso.#d").append(���).append("#n\r\n");

                        ����++;
                    }
                }
            }
        } catch (SQLException ex) {
        }
        name.append("\r\n\r\n");
        return name.toString();
    }

    //�������а�
    public String �������а�() {
        int ���� = 1;
        StringBuilder name = new StringBuilder();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE gm = 0 order by totalOnlineTime desc LIMIT 20 ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("totalOnlineTime") > 0) {
                    if (���� < 10) {
                        String ������� = rs.getString("name");
                        String ������ = rs.getString("totalOnlineTime");
                        String ������ = rs.getString("todayOnlineTime");
                        name.append("Top.#e#d").append(����).append("#n#k   ");
                        name.append("#b").append(�������).append("#k");
                        for (int j = 13 - �������.getBytes().length; j > 0; j--) {
                            name.append(" ");
                        }
                        name.append("     (tal/day).#d[").append(������).append(" / ").append(������).append("])\r\n");

                        ����++;
                    } else if (���� >= 10 && ���� <= 20) {
                        String ������� = rs.getString("name");
                        String ������ = rs.getString("totalOnlineTime");
                        String ������ = rs.getString("todayOnlineTime");
                        name.append("Top.#e#d").append(����).append("#n#k  ");
                        name.append("#b").append(�������).append("#k");
                        for (int j = 13 - �������.getBytes().length; j > 0; j--) {
                            name.append(" ");
                        }
                        name.append("     (tal/day).#d[").append(������).append(" / ").append(������).append("])\r\n");
                        ����++;
                    }
                }
            }
        } catch (SQLException ex) {
        }
        name.append("\r\n\r\n");
        return name.toString();
    }

    //�������а�
    public String �����������а�() {
        int ���� = 1;
        StringBuilder name = new StringBuilder();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM inventoryequipment order by itemlevel desc LIMIT 20");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("itemlevel") > 0) {
                    if (���� < 10) {
                        int ���ID = ����id��ȡ����(rs.getInt("inventoryitemid"));
                        String ������� = ��ɫIDȡ����(���ID);
                        if (��ɫIDȡGM(���ID) == 0) {
                            int ����IP = ����id��ȡ����ID(rs.getInt("inventoryitemid"));
                            name.append("Top.#e#d").append(����).append("#n#k   ");
                            name.append("ӵ����:#b").append(�������).append("#k");
                            for (int j = 15 - �������.getBytes().length; j > 0; j--) {
                                name.append(" ");
                            }
                            name.append(" lv.#r").append(rs.getInt("itemlevel")).append("#k #b#t").append(����IP).append("##k\r\n");
                            ����++;
                        }
                    } else if (���� >= 10 && ���� <= 20) {
                        int ���ID = ����id��ȡ����(rs.getInt("inventoryitemid"));
                        String ������� = ��ɫIDȡ����(���ID);
                        if (��ɫIDȡGM(���ID) == 0) {
                            int ����IP = ����id��ȡ����ID(rs.getInt("inventoryitemid"));
                            name.append("Top.#e#d").append(����).append("#n#k  ");
                            name.append("ӵ����:#b").append(�������).append("#k");
                            for (int j = 15 - �������.getBytes().length; j > 0; j--) {
                                name.append(" ");
                            }
                            name.append(" lv.#r").append(rs.getInt("itemlevel")).append("#k #b#t").append(����IP).append("##k\r\n");
                            ����++;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
        }
        name.append("\r\n\r\n");
        return name.toString();
    }

    public static int ����id��ȡ����ID(int a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = " + a + "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {//itemid
                data = rs.getInt("itemid");
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int ����id��ȡ����(int a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = " + a + "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {//itemid
                data = rs.getInt("characterid");
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    /**
     * ****************************************************************************************************************************************************
     * ��ʾ���壬���Ҽ������
     *
     * *****************************************************************************************************************************************************
     */
    public String ��ʾ���м���() {
        StringBuilder name = new StringBuilder();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM guilds order by GP desc");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ������ = rs.getInt("guildid");
                String �������� = rs.getString("name");
                int ����GP�� = �����Ա��(������);
                name.append("  #L").append(������).append("##d����:#b").append(��������).append("#k");
                for (int j = 13 - ��������.getBytes().length; j > 0; j--) {
                    name.append(" ");
                }
                String ������� = ��ɫIDȡ����(rs.getInt("leader"));
                name.append("#d�峤:#r").append(�������).append("#k");
                for (int j = 13 - �������.getBytes().length; j > 0; j--) {
                    name.append(" ");
                }
                name.append("#d����:#r").append(����GP��).append("#k#l\r\n");
            }
        } catch (SQLException ex) {
        }
        return name.toString();
    }

    //����ļ��壬��ɫ
    public void �������(int a, int b) {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE characters SET `guildid` = ? WHERE id = ?");
            ps.setInt(1, a);
            ps.setInt(2, b);
            ps.execute();
            ps.close();

            c.getPlayer().setGuildId(a);
            c.getPlayer().setGuildRank((byte) 5);
            int s = World.Guild.addGuildMember(c.getPlayer().getMGC());
            if (s == 0) {
                c.getPlayer().dropMessage(1, "����Ҫ����ļ����Ѿ���Ա�ˣ����Ժ����Ĳ��������Ѿ���Ч�ˡ�");
                c.getPlayer().setGuildId(0);
                return;
            }
            c.sendPacket(MaplePacketCreator.showGuildInfo(c.getPlayer()));
            c.getPlayer().saveGuildStatus();
        } catch (SQLException sql) {
            System.err.println("����������:" + sql);
        }
    }

    public int ��ʾ��������() {
        if (gui.Start.������Ϣ����.get("" + c.getPlayer().getName() + "2") == null) {
            int ID = 0;
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT `id` FROM jiezoudashi  ORDER BY `id` DESC LIMIT 1");
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String SN = rs.getString("id");
                        int sns = Integer.parseInt(SN);
                        sns++;
                        ID = sns;
                        ps.close();
                    }
                }
                ps.close();
            } catch (SQLException ex) {

            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO jiezoudashi ( id,name,Val ) VALUES ( ? ,?,?)")) {
                ps.setInt(1, ID);
                ps.setString(2, c.getPlayer().getName() + "2");
                ps.setInt(3, 0);
                ps.executeUpdate();
                ��ȡ��������Ϣ����();
            } catch (SQLException ex) {

            }
        }
        return gui.Start.������Ϣ����.get("" + c.getPlayer().getName() + "2");
    }

    public void �򿪸��˾���() {

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE jiezoudashi SET Val = ? WHERE name = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM jiezoudashi WHERE name = ?");
            ps1.setString(1, c.getPlayer().getName() + "2");
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                sqlString1 = "update jiezoudashi set Val =  '1' where name = '" + c.getPlayer().getName() + "2" + "';";
                PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                Val.executeUpdate(sqlString1);
                ��ȡ��������Ϣ����();
            }
        } catch (SQLException ex) {

        }

    }

    public void �رո��˾���() {
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE jiezoudashi SET Val = ? WHERE name = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM jiezoudashi WHERE name = ?");
            ps1.setString(1, c.getPlayer().getName() + "2");
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                sqlString1 = "update jiezoudashi set Val = '0' where name = '" + c.getPlayer().getName() + "2" + "';";
                PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                Val.executeUpdate(sqlString1);
                ��ȡ��������Ϣ����();
            }
        } catch (SQLException ex) {

        }
    }

    public int ��ʾ�˺���ϸ() {
        if (gui.Start.������Ϣ����.get("" + c.getPlayer().getName() + "1") == null) {
            int ID = 0;
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT `id` FROM jiezoudashi  ORDER BY `id` DESC LIMIT 1");
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String SN = rs.getString("id");
                        int sns = Integer.parseInt(SN);
                        sns++;
                        ID = sns;
                        ps.close();
                    }
                }
                ps.close();
            } catch (SQLException ex) {

            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO jiezoudashi ( id,name,Val ) VALUES ( ? ,?,?)")) {
                ps.setInt(1, ID);
                ps.setString(2, c.getPlayer().getName() + "1");
                ps.setInt(3, 0);
                ps.executeUpdate();
                ��ȡ��������Ϣ����();
            } catch (SQLException ex) {

            }
        }
        return gui.Start.������Ϣ����.get("" + c.getPlayer().getName() + "1");
    }

    public void ���˺���ϸ() {

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE jiezoudashi SET Val = ? WHERE name = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM jiezoudashi WHERE name = ?");
            ps1.setString(1, c.getPlayer().getName() + "1");
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                sqlString1 = "update jiezoudashi set Val = '1' where name = '" + c.getPlayer().getName() + "1" + "';";
                PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                Val.executeUpdate(sqlString1);
                ��ȡ��������Ϣ����();
            }
        } catch (SQLException ex) {

        }

    }

    public void �ر��˺���ϸ() {

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE jiezoudashi SET Val = ? WHERE name = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM jiezoudashi WHERE name = ?");
            ps1.setString(1, c.getPlayer().getName() + "1");
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                sqlString1 = "update jiezoudashi set Val = '0' where name = '" + c.getPlayer().getName() + "1" + "';";
                PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                Val.executeUpdate(sqlString1);
                ��ȡ��������Ϣ����();
            }
        } catch (SQLException ex) {

        }

    }

    public int ��ʾȺ���쿪��() {
        if (gui.Start.������Ϣ����.get("" + c.getPlayer().getName() + "10") == null) {
            int ID = 0;
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT `id` FROM jiezoudashi  ORDER BY `id` DESC LIMIT 1");
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String SN = rs.getString("id");
                        int sns = Integer.parseInt(SN);
                        sns++;
                        ID = sns;
                        ps.close();
                    }
                }
                ps.close();
            } catch (SQLException ex) {

            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO jiezoudashi ( id,name,Val ) VALUES ( ? ,?,?)")) {
                ps.setInt(1, ID);
                ps.setString(2, c.getPlayer().getName() + "10");
                ps.setInt(3, 0);
                ps.executeUpdate();
                ��ȡ��������Ϣ����();
            } catch (SQLException ex) {

            }
        }
        return gui.Start.������Ϣ����.get("" + c.getPlayer().getName() + "10");
    }

    public void ��Ⱥ����ʾ() {

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE jiezoudashi SET Val = ? WHERE name = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM jiezoudashi WHERE name = ?");
            ps1.setString(1, c.getPlayer().getName() + "10");
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                sqlString1 = "update jiezoudashi set Val = '1' where name = '" + c.getPlayer().getName() + "10" + "';";
                PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                Val.executeUpdate(sqlString1);
                ��ȡ��������Ϣ����();
            }
        } catch (SQLException ex) {

        }

    }

    public void �ر�Ⱥ����ʾ() {

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE jiezoudashi SET Val = ? WHERE name = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM jiezoudashi WHERE name = ?");
            ps1.setString(1, c.getPlayer().getName() + "10");
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                sqlString1 = "update jiezoudashi set Val = '0' where name = '" + c.getPlayer().getName() + "10" + "';";
                PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                Val.executeUpdate(sqlString1);
                ��ȡ��������Ϣ����();
            }
        } catch (SQLException ex) {

        }
    }

    public int ��ʾȺ���쿪��2() {
        if (gui.Start.������Ϣ����.get("" + c.getPlayer().getName() + "11") == null) {
            int ID = 0;
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT `id` FROM jiezoudashi  ORDER BY `id` DESC LIMIT 1");
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String SN = rs.getString("id");
                        int sns = Integer.parseInt(SN);
                        sns++;
                        ID = sns;
                        ps.close();
                    }
                }
                ps.close();
            } catch (SQLException ex) {

            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO jiezoudashi ( id,name,Val ) VALUES ( ? ,?,?)")) {
                ps.setInt(1, ID);
                ps.setString(2, c.getPlayer().getName() + "11");
                ps.setInt(3, 0);
                ps.executeUpdate();
                ��ȡ��������Ϣ����();
            } catch (SQLException ex) {

            }
        }
        return gui.Start.������Ϣ����.get("" + c.getPlayer().getName() + "11");
    }

    public void ��Ⱥ����ʾ2() {

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE jiezoudashi SET Val = ? WHERE name = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM jiezoudashi WHERE name = ?");
            ps1.setString(1, c.getPlayer().getName() + "11");
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                sqlString1 = "update jiezoudashi set Val = '1' where name = '" + c.getPlayer().getName() + "11" + "';";
                PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                Val.executeUpdate(sqlString1);
                ��ȡ��������Ϣ����();
            }
        } catch (SQLException ex) {

        }

    }

    public void �ر�Ⱥ����ʾ2() {

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE jiezoudashi SET Val = ? WHERE name = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM jiezoudashi WHERE name = ?");
            ps1.setString(1, c.getPlayer().getName() + "11");
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                sqlString1 = "update jiezoudashi set Val = '0' where name = '" + c.getPlayer().getName() + "11" + "';";
                PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                Val.executeUpdate(sqlString1);
                ��ȡ��������Ϣ����();
            }
        } catch (SQLException ex) {

        }
    }

    /**
     * <9>
     */
    public int �жϵ�һ�׶��Ƿ����() {
        int a = 0;
        if (c.getPlayer().getBossLog("�ͻ�104000100") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�104000200") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�104000300") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�104000400") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�104010000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�104020000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�104030000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�104040000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�100000000") > 0) {
            a += 1;
        }
        return a;
    }

    /**
     * <7>
     */
    public int �жϵڶ��׶��Ƿ����() {
        int a = 0;
        if (c.getPlayer().getBossLog("�ͻ�100010000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�100020000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�100030000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�100040000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�100040100") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�100050000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�101000000") > 0) {
            a += 1;
        }
        return a;
    }

    /**
     * <10>
     */
    public int �жϵ����׶��Ƿ����() {
        int a = 0;
        if (c.getPlayer().getBossLog("�ͻ�101010000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�101010100") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�101020000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�101030000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�101030100") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�101030200") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�101030300") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�101030400") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�101040000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�102000000") > 0) {
            a += 1;
        }
        return a;
    }

    /**
     * <6>
     */
    public int �жϵ��Ľ׶��Ƿ����() {
        int a = 0;
        if (c.getPlayer().getBossLog("�ͻ�102010000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�102020000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�102030000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�102040000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�102050000") > 0) {
            a += 1;
        }
        if (c.getPlayer().getBossLog("�ͻ�103000000") > 0) {
            a += 1;
        }

        return a;
    }

    /**
     * <�ֲ�>
     */
    public void ¼���ֲ�(int a, int b, int c) {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO shouce (b,c,d) VALUES ( ?, ?, ?)");
            ps.setInt(1, a);
            ps.setInt(2, b);
            ps.setInt(3, c);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("¼���ֲ�!!55" + sql);
        }
    }

    public String ��ʾ�ֲ�����(int a) {
        StringBuilder name = new StringBuilder();

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM shouce order by c desc");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("d") == a) {
                    int ��� = rs.getInt("a");
                    int ���� = rs.getInt("b");
                    int ���� = rs.getInt("c");
                    String ��Ʒ���� = MapleItemInformationProvider.getInstance().getName(rs.getInt("b"));
                    String ��Ʒ���� = "" + ���� + "";
                    name.append("#L").append(���).append("##b#z").append(����).append("##k ");
                    for (int j = 21 - ��Ʒ����.getBytes().length; j > 0; j--) {
                        name.append(" ");
                    }
                    name.append("���� #r").append(����).append("#k");
                    for (int j = 8 - ��Ʒ����.getBytes().length; j > 0; j--) {
                        name.append(" ");
                    }
                    name.append("���� #b#c").append(����).append("##k#l\r\n");

                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return name.toString();
    }

    public static int �ֲ���ߴ���(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT b as DATA FROM shouce WHERE a = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�ֲ���ߴ��롢����");
        }
        return data;
    }

    public static int �ֲ��������(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT c as DATA FROM shouce WHERE a = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�ֲ��������������");
        }
        return data;
    }

    public static void �޸��ֲ��������(int a, int b) {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM shouce");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("a").equals("" + a + "")) {
                    String aa = null;
                    aa = "update shouce set c =" + b + " where a = " + a + ";";
                    PreparedStatement S = DatabaseConnection.getConnection().prepareStatement(aa);
                    S.executeUpdate(aa);
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }

    }

    public static void ɾ��������() {
        PreparedStatement ps1 = null;
        ResultSet rs = null;

        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM shouce");
            rs = ps1.executeQuery();
            while (rs.next()) {
                String sqlstr = " delete from shouce where c = 0";
                ps1.executeUpdate(sqlstr);
            }
            ps1.close();
        } catch (SQLException ex) {

        }

    }

    public static void �����ֲ��ղ�(int a, int b, int c) {
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO shouce ( b,c,d ) VALUES ( ? ,?,? )")) {
            ps.setInt(1, a);
            ps.setInt(2, b);
            ps.setInt(3, c);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {

        }
    }

    public int OX����() {//var count = cm.��ȡָ����ͼ�������(910000000);
        return MapleParty.OX����;
    }

    public int ��������() {
        return MapleParty.��������ʱ��;
    }

    public int ��������2() {
        return MapleParty.��������Ƶ��;
    }

    public int ��������3() {
        return MapleParty.�������˵�ͼ;
    }

    public int ͨ����1() {
        return MapleParty.ͨ��BOSS;
    }

    public int ͨ����2() {
        return MapleParty.ͨ����ͼ;
    }

    public int ѩ����() {
        return MapleParty.ѩ����;
    }

    public String ����ְҵ() {
        String ְҵ1 = getJobNameById((MapleParty.����ְҵ - 1));
        String ְҵ2 = getJobNameById((MapleParty.����ְҵ));
        String ְҵ3 = getJobNameById((MapleParty.����ְҵ + 1));
        String ְҵ = ְҵ1 + "," + ְҵ2 + "," + ְҵ3;
        return ְҵ;
    }

    public String ��ʾ����(int a) {
        StringBuilder name = new StringBuilder();
        if (c.getPlayer().getLevel() >= 160) {
            ��ȡ��������Ϣ����();
            DecimalFormat ��ȷ��ʾ = new DecimalFormat("##0.0000");
            DecimalFormat ��ȷ��ʾ2 = new DecimalFormat("###");
            int ���� = (gui.Start.������Ϣ����.get("BUFF����" + a + ""));
            int �������� = gui.Start.������Ϣ����.get("��������" + a + "");
            int ħ�������� = gui.Start.������Ϣ����.get("ħ��������" + a + "");
            String �׼� = "";
            if (���� > 50 && ���� <= 100) {
                �׼� = "����";
            } else if (���� > 100 && ���� <= 150) {
                �׼� = "�м�";
            } else if (���� > 150 && ���� <= 200) {
                �׼� = "�߼�";
            } else {
                �׼� = "����";
            }

            name.append("״̬:#b����ģʽ : #r").append(�׼�).append("#k\r\n\r\n");
            name.append("    #d������״̬���������Եõ��޶����ǣ�������ֵ��ͨ������������#k\r\n");
            name.append("\r\n״̬ά�֣�\r\n");
            if (���� > 50 && ���� <= 100) {
                name.append("�� ÿ #r5#k ������ #r15%#k �������ֵ,�������ֵ\r\n");
            } else if (���� > 100 && ���� <= 150) {
                name.append("�� ÿ #r4#k ������ #r15%#k �������ֵ,�������ֵ\r\n");
            } else if (���� > 150) {
                name.append("�� ÿ #r4#k ������ #r20%#k �������ֵ,�������ֵ\r\n");
            } else {
                name.append("�� ÿ #r5#k ������ #r10%#k �������ֵ,�������ֵ\r\n");
            }
            name.append("�� ÿ #r20000#b(-").append(��ȷ��ʾ.format(gui.Start.������Ϣ����.get("�������" + a + "") * gui.Start.������Ϣ����.get("BUFF����" + a + "") * 0.0001)).append(")#k�����ȡ����BUFF\r\n");
            name.append("�� ���� #r30%#k �ܵ����˺�\r\n");
            name.append("�� ���� #r30#b(").append(��ȷ��ʾ.format((�������� + ħ��������) * 0.5 * ���� * 0.00001)).append(")#r%#k �Ը߼�������˺�\r\n");
            name.append("\r\n״̬���ԣ�\r\n");
            /**
             * <��������,10000>
             */
            String ����1 = "#d��������#k:#r" + ���� + "#k";
            name.append(����1);
            for (int j = 43 - ����1.getBytes().length; j > 0; j--) {
                name.append(" ");
            }
            name.append("����ģʽ������\r\n");
            String ����2 = "#dӲ��Ƥ��#k:#b" + ��ȷ��ʾ.format(gui.Start.������Ϣ����.get("Ӳ��Ƥ��" + a + "") * ���� * 0.01) + "#r(" + gui.Start.������Ϣ����.get("Ӳ��Ƥ��" + a + "") + ")#k";
            name.append(����2);
            for (int j = 45 - ����2.getBytes().length; j > 0; j--) {
                name.append(" ");
            }
            name.append("�����ܵ����˺�\r\n");

            String ����5 = "#d�������#k:#b" + ��ȷ��ʾ.format(gui.Start.������Ϣ����.get("�������" + a + "") * ���� * 0.01) + "#r(" + gui.Start.������Ϣ����.get("�������" + a + "") + ")#k";
            name.append(����5);
            for (int j = 45 - ����5.getBytes().length; j > 0; j--) {
                name.append(" ");
            }
            name.append("�ӿ�����BUFF��ȡ\r\n");

            /**
             * <��������>
             */
            String ����6 = "#d������(����)#k:#b" + ��ȷ��ʾ2.format(gui.Start.������Ϣ����.get("��������" + a + "") * ���� * 0.001) + "#r(" + gui.Start.������Ϣ����.get("��������" + a + "") + ")#k";
            name.append(����6);
            for (int j = 45 - ����6.getBytes().length; j > 0; j--) {
                name.append(" ");
            }
            name.append("����������ʵ�˺�\r\n");

            /**
             * <ħ��������>
             */
            String ����7 = "#d������(ħ��)#k:#b" + ��ȷ��ʾ2.format(gui.Start.������Ϣ����.get("ħ��������" + a + "") * ���� * 0.001) + "#r(" + gui.Start.������Ϣ����.get("ħ��������" + a + "") + ")#k";
            name.append(����7);
            for (int j = 45 - ����7.getBytes().length; j > 0; j--) {
                name.append(" ");
            }
            name.append("����ħ����ʵ�˺�\r\n");
            /**
             * <�������>
             */
            String ����8 = "#d����(����)#k:#b" + ��ȷ��ʾ.format(gui.Start.������Ϣ����.get("�������" + a + "") * ���� * 0.002) + "#r(" + gui.Start.������Ϣ����.get("�������" + a + "") + ")#k";
            name.append(����8);
            for (int j = 45 - ����8.getBytes().length; j > 0; j--) {
                name.append(" ");
            }
            name.append("������ʵ�˺�����\r\n");
            /**
             * <ħ������>
             */
            String ����9 = "#d����(ħ��)#k:#b" + ��ȷ��ʾ.format(gui.Start.������Ϣ����.get("ħ������" + a + "") * ���� * 0.002) + "#r(" + gui.Start.������Ϣ����.get("ħ������" + a + "") + ")#k";
            name.append(����9);
            for (int j = 45 - ����9.getBytes().length; j > 0; j--) {
                name.append(" ");
            }
            name.append("ħ����ʵ�˺�����\r\n");
            /**
             * <��������>
             */
            String ����10 = "#d������(����)#k:#b" + ��ȷ��ʾ.format(gui.Start.������Ϣ����.get("����������" + a + "") * ���� * 0.002) + "#r(" + gui.Start.������Ϣ����.get("����������" + a + "") + ")#k";
            name.append(����10);
            for (int j = 45 - ����10.getBytes().length; j > 0; j--) {
                name.append(" ");
            }
            name.append("����ʱ�ָ�HP\r\n");
            /**
             * <ħ������>
             */
            String ����11 = "#d������(ħ��)#k:#b" + ��ȷ��ʾ.format(gui.Start.������Ϣ����.get("ħ��������" + a + "") * ���� * 0.002) + "#r(" + gui.Start.������Ϣ����.get("ħ��������" + a + "") + ")#k";
            name.append(����11);
            for (int j = 45 - ����11.getBytes().length; j > 0; j--) {
                name.append(" ");
            }
            name.append("����ʱ�ָ�MP\r\n\r\n");
            name.append("#r#L3#����������#l\r\n");
            name.append("#r#L4#����Ӳ��Ƥ��#l\r\n");
            name.append("#b#L1#����������[����]#l\r\n");
            name.append("#b#L2#����������[ħ��]#l\r\n");

        } else {
            name.append(" ��ɫ #b160#k ���󼴿ɾ��� #b����֮��#k");
        }
        return name.toString();
    }

    public void ����Ӳ��Ƥ��() {
        c.getPlayer().����Ӳ��Ƥ��();
    }

    public void ������������() {
        c.getPlayer().������������();
    }

    public void ����BUFF����() {
        c.getPlayer().����BUFF����();
    }

    public void ����ħ��������() {
        c.getPlayer().����ħ��������();
    }

    public int �һ������֤��() {
        return c.getPlayer().�һ������֤��;
    }

    public void ������֤��() {
        c.getPlayer().�һ������֤�� = 0;
        c.getPlayer().b = 0;
        c.getPlayer().d = 0;
    }

    public void ѧϰ����ģʽ(String a, int b) {
        if (gui.Start.������Ϣ����.get(a) == null) {
            int ID = 0;
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT `id` FROM jiezoudashi  ORDER BY `id` DESC LIMIT 1");
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String SN = rs.getString("id");
                        int sns = Integer.parseInt(SN);
                        sns++;
                        ID = sns;
                        ps.close();
                    }
                }
                ps.close();
            } catch (SQLException ex) {

            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO jiezoudashi ( id,name,Val ) VALUES ( ? ,?,?)")) {
                ps.setInt(1, ID);
                ps.setString(2, a);
                ps.setInt(3, b);
                ps.executeUpdate();
                ��ȡ��������Ϣ����();
            } catch (SQLException ex) {

            }
        }

    }

    /**
     * <�������>
     */
    public String ��ʾ�����б�() {
        StringBuilder name = new StringBuilder();

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM yinhang_1 ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ��� = rs.getInt("a");
                String �������� = rs.getString("b");
                name.append("#L").append(���).append("##b#z").append(��������).append("##k ");
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return name.toString();
    }

    public String ��ʾ�������(int a) {
        StringBuilder name = new StringBuilder();

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM yinhang_1 ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ��� = rs.getInt("a");
                String �������� = rs.getString("b");
                name.append("#L").append(���).append("##b#z").append(��������).append("##k ");
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return name.toString();
    }

    public String ��ʾ��Ӷ������Ʒ() {
        StringBuilder name = new StringBuilder();
        final byte state = checkExistance(c.getPlayer().getAccountID(), c.getPlayer().getId());
        boolean merch = World.hasMerchant(c.getPlayer().getAccountID());
        /**
         * <1.�̵�û��>
         * <2.>
         * <3.��Ӷ���߼�¼���ݿ����ж���>
         */
        if (!merch && state != 1 && ��ɫIDȡ��Ӷ����(c.getPlayer().getId()) > 0) {
            name.append("   ��Ӷ�쳣��ʧ��Ʒ��أ��뱣֤�㱳��װ���£���Ȼ����ʧŶ��������ֵ����������ԣ��������Բ��ԣ�����ϵ����Ա���޷��ָ���ħװ����\r\n\r\n");
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM hire where cid =" + c.getPlayer().getId() + "");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getInt("itemid") < 2000000) {
                        name.append("#v").append(rs.getInt("itemid")).append("# #b#t").append(rs.getInt("itemid")).append("##k x 1\r\n");
                    } else {
                        name.append("#v").append(rs.getInt("itemid")).append("# #b#t").append(rs.getInt("itemid")).append("##k x ").append(rs.getInt("potential3")).append("\r\n");
                    }
                }
                ps.close();
            } catch (SQLException ex) {
            }
            name.append("\r\n#b#L1#[��ص���]#l");
        } else {
            name.append("��û�з������쳣��");
        }
        return name.toString();
    }

    public void ��ع�Ӷ����() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM hire where cid =" + c.getPlayer().getId() + "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("itemid") < 2000000) {
                    MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                    final Equip item = (Equip) ii.getEquipById(rs.getInt("itemid"));
                    if (rs.getInt("upgradeslots") > 0) {
                        item.setUpgradeSlots((byte) rs.getInt("upgradeslots"));
                    }
                    if (rs.getInt("str") > 0) {
                        item.setStr((short) rs.getInt("str"));
                    }
                    if (rs.getInt("dex") > 0) {
                        item.setDex((short) rs.getInt("dex"));
                    }
                    if (rs.getInt("2int") > 0) {
                        item.setInt((short) rs.getInt("2int"));
                    }
                    if (rs.getInt("luk") > 0) {
                        item.setLuk((short) rs.getInt("luk"));
                    }
                    if (rs.getInt("watk") > 0) {
                        item.setWatk((short) rs.getInt("watk"));
                    }
                    if (rs.getInt("matk") > 0) {
                        item.setMatk((short) rs.getInt("matk"));
                    }
                    if (rs.getInt("wdef") > 0) {
                        item.setWdef((short) rs.getInt("wdef"));
                    }
                    if (rs.getInt("mdef") > 0) {
                        item.setMdef((short) rs.getInt("mdef"));
                    }
                    if (rs.getInt("hp") > 0) {
                        item.setHp((short) rs.getInt("hp"));
                    }
                    if (rs.getInt("mp") > 0) {
                        item.setMp((short) rs.getInt("mp"));
                    }
                    if (rs.getInt("acc") > 0) {
                        item.setAcc((short) rs.getInt("acc"));
                    }
                    if (rs.getInt("avoid") > 0) {
                        item.setAvoid((short) rs.getInt("avoid"));
                    }
                    if (rs.getInt("speed") > 0) {
                        item.setSpeed((short) rs.getInt("speed"));
                    }
                    if (rs.getInt("jump") > 0) {
                        item.setJump((short) rs.getInt("jump"));
                    }
                    MapleInventoryManipulator.addbyItem(c.getPlayer().getClient(), item.copy());
                } else {
                    gainItem(rs.getInt("itemid"), (short) rs.getInt("upgradeslots"));
                }
                ɾ������(c.getPlayer().getId());
            }
        } catch (SQLException ex) {
        }
    }

    public void ɾ������(int a1) {
        try {
            ResultSet rs = null;
            PreparedStatement ps = null;
            ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM hire");
            rs = ps.executeQuery();
            if (rs.next()) {
                int a = 0;

                String sqlstr = " delete from hire where "
                        + "cid = " + a1 + " ";
                ps.executeUpdate(sqlstr);
                ps.close();
            }
        } catch (SQLException ex) {

        }
    }

    private final byte checkExistance(final int accid, final int charid) {
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * from hiredmerch where accountid = ? OR characterid = ?");
            ps.setInt(1, accid);
            ps.setInt(2, charid);
            //System.out.println("3");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ps.close();
                rs.close();
                return 1;
            }
            rs.close();
            ps.close();
            return 0;
        } catch (SQLException se) {
            return -1;
        }
    }

    public int ��ɫIDȡ��Ӷ����(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT cid as DATA FROM hire WHERE cid = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ɫ����ȡ�˺�ID������");
        }
        return data;
    }

    public int ȡ��ֳ��ֵ(int a) {
        c.getPlayer().����浵();
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM queststatus  WHERE characterid = " + c.getPlayer().getId() + " &&��quest = " + a + ";")) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    data = Integer.parseInt(rs.getString("customData"));
                }
            }
        } catch (SQLException ex) {
        }
        return data;
    }

    public void ����ֳ��ֵ(int a) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM queststatus WHERE id = ?");
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlstr = " delete from queststatus where characterid = " + c.getPlayer().getId() + " &&��quest=" + a + "";
                ps1.executeUpdate(sqlstr);
                c.getPlayer().����浵();
            }
        } catch (SQLException ex) {

        }

    }

    public void ɾ����Ӷ����() {
        try {
            ResultSet rs = null;
            PreparedStatement ps = null;
            ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM hiredmerch");
            rs = ps.executeQuery();
            if (rs.next()) {
                int a = 0;

                String sqlstr = " delete from hiredmerch where "
                        + "accountid = " + c.getPlayer().getAccountID() + " ";
                ps.executeUpdate(sqlstr);
                ps.close();

            }
        } catch (SQLException ex) {

        }
    }

    public String ��ѯ����(int a) {
        StringBuilder name = new StringBuilder();
        try {
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM drop_data where itemid =" + a + "")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    name.append("    #o").append(rs.getInt("dropperid")).append("#\r\n");
                }
                ps.close();
            }
        } catch (SQLException ex) {
        }
        return name.toString();
    }

    public static int �����˺�(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM bank WHERE a = " + id + "");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�����˺š�����");
        }
        return data;
    }

    public static int �����˺�����(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM bank WHERE a = " + id + "");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("b");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�����˺����롢����");
        }
        return data;
    }

    public static long ���н�����(int id) {
        long data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM bank WHERE a = " + id + "");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getLong("c");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("���н��������");
        }
        return data;
    }

    public static long ���е�ȯ���(int id) {
        long data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM bank WHERE a = " + id + "");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getLong("d");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("���е�ȯ������");
        }
        return data;
    }

    public static int ���п�����(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM bank WHERE a = " + id + "");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("e");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("���п����ˡ�����");
        }
        return data;
    }

    public static void ������н��(int a, long b) {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE bank SET c = " + b + " WHERE a = " + a + "");

            ps.executeUpdate();
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("������н�ҳ���: " + Ex);
        }
    }

    public static void ������е�ȯ(int a, long b) {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE bank SET d = " + b + " WHERE a = " + a + "");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("������е�ȯ����: " + Ex);
        }
    }

    //�кţ�ת��ת�룬��ȯ��ң�����
    public void ת�˼�¼(int a, int d, int e, String b) {
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO bank_jl (a, d,e,b) VALUES (?,?,?,?)");
            ps.setInt(1, a);
            ps.setInt(2, d);
            ps.setInt(3, e);
            ps.setString(4, b);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ת�˼�¼����: " + Ex);
        }
    }

    public void ��������˺�����(int a, int b) {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE bank SET b = " + b + " WHERE a = " + a + "");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("������е�ȯ����: " + Ex);
        }
    }

    public void ע�������˺�(int a, int b, int aa) {

        try {
            Connection con;
            con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO bank (a, b,e) VALUES (?,?,?)");
            ps.setInt(1, a);
            ps.setInt(2, b);
            ps.setInt(3, aa);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            return;
        }
    }

    public String ��ʾת�˼�¼(int a, int b, int e) {
        StringBuilder name = new StringBuilder();

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM bank_jl  WHERE a = " + a + " && d = " + b + " && e = " + e + " order by c desc  LIMIT 100 ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                name.append("\tʱ��: #d").append(rs.getString("c")).append("#k \r\n\t#b").append(rs.getString("b")).append("#k\r\n\r\n");
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return name.toString();
    }

    public String ʱ��() {
        return CurrentReadable_Time();
    }

    public String ��ʾ��ħЧ��(String a) {
        StringBuilder name = new StringBuilder();
        String arr1[] = a.split(",");
        for (int i = 0; i < arr1.length; i++) {
            String pair = arr1[i];
            if (pair.contains(":")) {
                String kongInfo = "��";
                String arr2[] = pair.split(":");
                int fumoType = Integer.parseInt(arr2[0]);
                int fumoVal = Integer.parseInt(arr2[1]);
                if (fumoType > 0 && Start.FuMoInfoMap.containsKey(fumoType)) {
                    String infoArr[] = Start.FuMoInfoMap.get(fumoType);
                    String fumoName = infoArr[0];
                    String fumoInfo = infoArr[1];
                    kongInfo += fumoName + " " + String.format(fumoInfo, fumoVal);
                } else {
                    kongInfo += "[δ��ħ]";
                }
                name.append("��ħ : ").append(kongInfo);
            }
        }
        return name.toString();
    }

    public static int ȡ���˸���ͨ��ʱ�����(int a, int b) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM fubenjilu WHERE name = " + a + " && cid = " + b + "order by time asc");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("time");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ȡ����ͨ��ʱ����� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static int ȡ����ͨ�����ʱ��(int a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM fubenjilu WHERE name = " + a + " order by time asc");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("time");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ȡ����ͨ��ʱ����� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static int ȡ����ͨ��������(int a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM fubenjilu WHERE name = " + a + " order by time asc");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("cid");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ȡ����ͨ��ʱ����� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static int ȡ����ͨ��ʱ��(int a, int b) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT time as DATA FROM fubenjilu WHERE name = " + a + " && cid = " + b + "");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ȡ����ͨ��ʱ�� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public void д��¼(int A, int B, int C) {
        try {
            PreparedStatement ps1 = null;
            ResultSet rs = null;
            Connection con;
            con = DatabaseConnection.getConnection();
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM fubenjilu ");
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlstr = " Delete from fubenjilu where name ='" + A + "' && cid = " + B + "";
                ps1.executeUpdate(sqlstr);
            }
            PreparedStatement ps = con.prepareStatement("INSERT INTO fubenjilu (name, cid,time) VALUES (?,?,?)");
            ps.setInt(1, A);
            ps.setInt(2, B);
            ps.setInt(3, C);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            return;
        }
    }

    public String ��ʾ�������() {
        StringBuilder name = new StringBuilder();
        for (ChannelServer cs : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cs.getPlayerStorage().getAllCharacters()) {
                if (c.getPlayer().getId() != chr.getId() && !chr.isGM()) {
                    if (!����(chr.getMapId())) {
                        name.append("\t\t\t#b#L").append(chr.getId()).append("# ���: #r").append(chr.getName()).append("#l\r\n");
                    } else {
                        name.append("\t\t\t#b#L").append(chr.getId()).append("# ���: #d").append(chr.getName()).append("#l\r\n");
                    }
                }
            }
        }
        return name.toString();
    }

    public String ��ʾ���������ϸ(int a) {
        StringBuilder name = new StringBuilder();
        for (ChannelServer cs : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cs.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getId() != a) {
                    continue;
                }
                if (!����(chr.getMapId())) {
                    name.append("\t\t#d�������:  #r").append(chr.getName()).append(" #k (").append(chr.getId()).append(")\r\n");
                    name.append("\t\t#d��ҵȼ�:  #r").append(chr.getLevel()).append(" #k (").append(getJobNameById(chr.job)).append(")\r\n");
                    name.append("\t\t#d����˺�:  #r").append(chr.����˺�).append(" #k\r\n");
                    name.append("\t\t#d��������:  #r").append(chr.����������).append(" #k\r\n");
                    name.append("\t\t#d�ۼƵ�Ѫ:  #r").append(chr.�ۼƵ�Ѫ).append(" #k\r\n");
                    name.append("\t\t#d���ڵ�ͼ:  [Ұ��] #r").append(chr.getMap().getMapName()).append(" #k\r\n");
                    name.append("\t\t#d�������:  #r").append(chr.�������).append(" #k\r\n");
                    name.append("\t\t#d�������:  #r").append(chr.X�������).append(" #k(X)\r\n");
                    name.append("\t\t#d�������:  #r").append(chr.Y�������).append(" #k(Y)\r\n");
                    name.append("\t\t#d��QQ��:  #r").append(�˺�IDȡ��QQ(��ɫIDȡ�˺�ID(chr.getId()))).append(" #k\r\n");
                    name.append("\t\t\t   #b#L").append(chr.getId()).append("#[����]#l  #L").append(chr.getId() + 1000000).append("#[���]#l\r\n\r\n");
                    if (chr.��ͼ����1 > 0 && !"".equals(chr.��ͼ����ʱ��1)) {
                        name.append("\t\t#d��ͼ��¼��\r\n");
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��1).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����1).append("##k\r\n");
                    }
                    if (chr.��ͼ����2 > 0 && !"".equals(chr.��ͼ����ʱ��2)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��2).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����2).append("##k\r\n");
                    }
                    if (chr.��ͼ����3 > 0 && !"".equals(chr.��ͼ����ʱ��3)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��3).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����3).append("##k\r\n");
                    }
                    if (chr.��ͼ����4 > 0 && !"".equals(chr.��ͼ����ʱ��4)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��4).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����4).append("##k\r\n");
                    }
                    if (chr.��ͼ����5 > 0 && !"".equals(chr.��ͼ����ʱ��5)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��5).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����5).append("##k\r\n");
                    }
                    if (chr.��ͼ����6 > 0 && !"".equals(chr.��ͼ����ʱ��6)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��6).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����6).append("##k\r\n");
                    }
                    if (chr.��ͼ����7 > 0 && !"".equals(chr.��ͼ����ʱ��7)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��7).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����7).append("##k\r\n");
                    }

                    if (chr.��ͼ����8 > 0 && !"".equals(chr.��ͼ����ʱ��8)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��8).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����8).append("##k\r\n");
                    }
                    if (chr.��ͼ����9 > 0 && !"".equals(chr.��ͼ����ʱ��9)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��9).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����9).append("##k\r\n");
                    }
                    if (chr.��ͼ����10 > 0 && !"".equals(chr.��ͼ����ʱ��10)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��10).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����10).append("##k\r\n");
                    }
                } else {
                    name.append("\t\t#d�������:  #r").append(chr.getName()).append(" #k (").append(chr.getId()).append(")\r\n");
                    name.append("\t\t#d��ҵȼ�:  #r").append(chr.getLevel()).append(" #k (").append(getJobNameById(chr.job)).append(")\r\n");
                    name.append("\t\t#d����˺�:  #r").append(chr.����˺�).append(" #k\r\n");
                    name.append("\t\t#d��������:  #r").append(chr.����������).append(" #k\r\n");
                    name.append("\t\t#d�ۼƵ�Ѫ:  #r").append(chr.�ۼƵ�Ѫ).append(" #k\r\n");
                    name.append("\t\t#d��ͼ���:  #rnull  #k\r\n");
                    name.append("\t\t#d���ڵ�ͼ:  [����] #r").append(chr.getMap().getMapName()).append(" #k\r\n");
                    name.append("\t\t#d�������:  #r").append(chr.�������).append(" #k\r\n");
                    name.append("\t\t#d�������:  #r").append(chr.X�������).append(" #k(X)\r\n");
                    name.append("\t\t#d�������:  #r").append(chr.Y�������).append(" #k(Y)\r\n");
                    name.append("\t\t#d��QQ��:  #r").append(�˺�IDȡ��QQ(��ɫIDȡ�˺�ID(chr.getId()))).append(" #k\r\n");
                    name.append("\t\t\t   #b#L").append(chr.getId()).append("#[����]#l  #L").append(chr.getId() + 1000000).append("#[���]#l\r\n\r\n");
                    if (chr.��ͼ����1 > 0 && !"".equals(chr.��ͼ����ʱ��1)) {
                        name.append("\t\t#d��ͼ��¼��\r\n");
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��1).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����1).append("##k\r\n");
                    }
                    if (chr.��ͼ����2 > 0 && !"".equals(chr.��ͼ����ʱ��2)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��2).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����2).append("##k\r\n");
                    }
                    if (chr.��ͼ����3 > 0 && !"".equals(chr.��ͼ����ʱ��3)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��3).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����3).append("##k\r\n");
                    }
                    if (chr.��ͼ����4 > 0 && !"".equals(chr.��ͼ����ʱ��4)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��4).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����4).append("##k\r\n");
                    }
                    if (chr.��ͼ����5 > 0 && !"".equals(chr.��ͼ����ʱ��5)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��5).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����5).append("##k\r\n");
                    }
                    if (chr.��ͼ����6 > 0 && !"".equals(chr.��ͼ����ʱ��6)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��6).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����6).append("##k\r\n");
                    }
                    if (chr.��ͼ����7 > 0 && !"".equals(chr.��ͼ����ʱ��7)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��7).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����7).append("##k\r\n");
                    }
                    if (chr.��ͼ����8 > 0 && !"".equals(chr.��ͼ����ʱ��8)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��8).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����8).append("##k\r\n");
                    }
                    if (chr.��ͼ����9 > 0 && !"".equals(chr.��ͼ����ʱ��9)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��9).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����9).append("##k\r\n");
                    }
                    if (chr.��ͼ����10 > 0 && !"".equals(chr.��ͼ����ʱ��10)) {
                        name.append("\t\t#d��ͼʱ��:  #r").append(chr.��ͼ����ʱ��10).append("\r\n");
                        name.append("\t\t#d��ͼ����:  #r#m").append(chr.��ͼ����10).append("##k\r\n");
                    }
                }
            }
        }
        return name.toString();
    }

    public static String �˺�IDȡ��QQ(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT qq as DATA FROM accounts WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�˺�IDȡ�˺š�����");
        }
        return data;
    }

    public void ����ID�������(int cid) {
        if (c.getPlayer().getId() == cid) {
            return;
        }
        for (ChannelServer cs : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cs.getPlayerStorage().getAllCharacters()) {
                if (chr.getId() == cid) {
                    if (c.getPlayer().getClient().getChannel() != chr.getClient().getChannel()) {
                        c.getPlayer().dropMessage(6, "���ڻ�Ƶ��,��ȴ���");
                        c.getPlayer().changeChannel(chr.getClient().getChannel());
                    } else if (c.getPlayer().getId() != chr.getMapId()) {
                        MapleMap mapp = c.getChannelServer().getMapFactory().getMap(chr.getMapId());
                        c.getPlayer().changeMap(mapp, mapp.getPortal(0));
                    }
                }
            }
        }
    }

    public void ����ID������(int cid) {
        if (c.getPlayer().getId() == cid) {
            return;
        }
        int ch = World.Find.findChannel(��ɫIDȡ����(cid));
        MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(��ɫIDȡ����(cid));
        if (target.ban("��Ѳ����/" + c.getPlayer().getName() + "", c.getPlayer().isAdmin(), false, false)) {
            String ��Ϣ = "[ϵͳ����] : ��� " + target.getName() + " ��Ϊʹ�÷Ƿ����/�ƻ���Ϸƽ�⣬��ϵͳ���÷�š�";
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
            sendMsgToQQGroup(��Ϣ);
        }
    }
}
