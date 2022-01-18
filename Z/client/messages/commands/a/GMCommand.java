package client.messages.commands.a;

import client.ISkill;
import client.MapleCharacter;
import client.MapleCharacterUtil;
import constants.ServerConstants.PlayerGMRank;
import client.MapleClient;
import client.MapleDisease;
import client.MapleStat;
import client.SkillFactory;
import client.anticheat.CheatingOffense;
import client.inventory.Equip;
import client.inventory.IItem;
import client.inventory.ItemFlag;
import client.inventory.MapleInventory;
import client.inventory.MapleInventoryIdentifier;
import client.inventory.MapleInventoryType;
import client.inventory.MapleRing;
import client.messages.CommandProcessorUtil;
import client.messages.commands.CommandExecute;
import constants.GameConstants;
import database.DatabaseConnection;
import handling.MapleServerHandler;
import handling.RecvPacketOpcode;
import handling.SendPacketOpcode;
import handling.channel.ChannelServer;
import handling.world.World;
import handling.world.CheaterData;
import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import provider.MapleData;
import provider.MapleDataProvider;
import provider.MapleDataProviderFactory;
import provider.MapleDataTool;
import scripting.EventInstanceManager;
import scripting.EventManager;
import scripting.PortalScriptManager;
import scripting.ReactorScriptManager;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.MaplePortal;
import server.MapleShopFactory;
import server.MapleSquad;
import server.ShutdownServer;
import server.Timer;
import server.events.MapleEvent;
import server.events.MapleEventType;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import server.life.MapleMonsterInformationProvider;
import server.life.MapleNPC;
import server.life.MobSkillFactory;
import server.life.OverrideMonsterStats;
import server.life.PlayerNPC;
import server.maps.MapleMap;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import server.maps.MapleReactor;
import server.maps.MapleReactorFactory;
import server.maps.MapleReactorStats;
import server.quest.MapleQuest;
import tools.ArrayMap;
import tools.CPUSampler;
import tools.MaplePacketCreator;
import tools.MockIOSession;
import tools.Pair;
import tools.StringUtil;
import tools.packet.MobPacket;
import tools.packet.PlayerShopPacket;
import com.mysql.jdbc.Connection;
import java.util.Iterator;
import java.util.Map;
import server.ServerProperties;
import tools.FileoutputUtil;
import static tools.FileoutputUtil.CurrentReadable_Time;

/**
 *
 * @author ����Ȩ��2
 */
public class GMCommand {

    /*public static PlayerGMRank getPlayerLevelRequired() {
        return PlayerGMRank.GM;
    }*/
    public static class �� extends WarpHere {
    }

    // public static class �ȼ� extends Level {
    // }
    // public static class תְ extends Job {
    // }
    // public static class ��� extends ClearInv {
    //}
    // public static class ���� extends DC {
    // }
    // public static class ��ȡ��� extends spy {
    //  }
    //  public static class �������� extends online {
    // }
    // public static class ������� extends onlinecounts {
    // }
    // public static class ������ extends UnBan {
    // }
    // public static class ˢǮ extends GainMeso {
    // }
    public static class WarpHere extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            MapleCharacter victim = c.getChannelServer().getPlayerStorage().getCharacterByName(splitted[1]);
            if (victim != null) {
                victim.changeMap(c.getPlayer().getMap(), c.getPlayer().getMap().findClosestSpawnpoint(c.getPlayer().getPosition()));
            } else {
                int ch = World.Find.findChannel(splitted[1]);
                if (ch < 0) {
                    c.getPlayer().dropMessage(5, "��ɫ������");
                    return 1;
                } else {
                    victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(splitted[1]);
                    c.getPlayer().dropMessage(5, "���ڴ�����ҵ����");
                    victim.dropMessage(5, "����Ա���ڴ�����");
                    if (victim.getMapId() != c.getPlayer().getMapId()) {
                        final MapleMap mapp = victim.getClient().getChannelServer().getMapFactory().getMap(c.getPlayer().getMapId());
                        victim.changeMap(mapp, mapp.getPortal(0));
                    }
                    victim.changeChannel(c.getChannel());
                }
            }
            return 1;
        }
    }

    public static class ��� extends CommandExecute {

        protected boolean hellban = false;

        private String getCommand() {
            return "UnBan";
        }

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (splitted.length < 2) {
                c.getPlayer().dropMessage(6, "[Syntax] !" + getCommand() + " <ԭ��>");

                return 0;
            }
            byte ret;
            if (hellban) {
                ret = MapleClient.unHellban(splitted[1]);
            } else {
                ret = MapleClient.unban(splitted[1]);
            }
            if (ret == -2) {
                c.getPlayer().dropMessage(6, "[" + getCommand() + "] SQL error.");
                return 0;
            } else if (ret == -1) {
                c.getPlayer().dropMessage(6, "[" + getCommand() + "] The character does not exist.");
                return 0;
            } else {
                c.getPlayer().dropMessage(6, "[" + getCommand() + "] Successfully unbanned!");

            }
            byte ret_ = MapleClient.unbanIPMacs(splitted[1]);
            if (ret_ == -2) {
                c.getPlayer().dropMessage(6, "[UnbanIP] SQL error.");
            } else if (ret_ == -1) {
                c.getPlayer().dropMessage(6, "[UnbanIP] The character does not exist.");
            } else if (ret_ == 0) {
                c.getPlayer().dropMessage(6, "[UnbanIP] No IP or Mac with that character exists!");
            } else if (ret_ == 1) {
                c.getPlayer().dropMessage(6, "[UnbanIP] IP/Mac -- one of them was found and unbanned.");
            } else if (ret_ == 2) {
                c.getPlayer().dropMessage(6, "[UnbanIP] Both IP and Macs were unbanned.");
            }
            return ret_ > 0 ? 1 : 0;
        }
    }

    public static class �⿨��ɫ extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            ChannelServer.forceRemovePlayerByCharName(splitted[1]);
            c.getPlayer().dropMessage("������ſ��ǳɹ�");
            return 1;
        }
    }

    public static class תְ extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().changeJob(Integer.parseInt(splitted[1]));
            return 1;
        }
    }

    /* public static class GainMeso extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().gainMeso(Integer.MAX_VALUE - c.getPlayer().getMeso(), true);
            return 1;
        }
    }*/
    public static class �ȼ� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().setLevel(Short.parseShort(splitted[1]));
            c.getPlayer().levelUp();
            if (c.getPlayer().getExp() < 0) {
                c.getPlayer().gainExp(-c.getPlayer().getExp(), false, false, true);
            }
            return 1;
        }
    }

    public static class �鿴��ɫ extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (splitted.length < 2) {
                c.getPlayer().dropMessage(6, "ʹ�ù���: !spy <�������>");
            } else {
                MapleCharacter victim = c.getChannelServer().getPlayerStorage().getCharacterByName(splitted[1]);
                if (victim.getGMLevel() > c.getPlayer().getGMLevel() && c.getPlayer().getId() != victim.getId()) {
                    c.getPlayer().dropMessage(5, "�㲻�ܲ鿴�����Ȩ�޵���!");
                    return 0;
                }
                if (victim != null) {
                    c.getPlayer().dropMessage(5, "�����(" + victim.getId() + ")״̬:");
                    c.getPlayer().dropMessage(5, "�ȼ�: " + victim.getLevel() + "ְҵ: " + victim.getJob() + "����: " + victim.getFame());
                    c.getPlayer().dropMessage(5, "��ͼ: " + victim.getMapId() + " - " + victim.getMap().getMapName().toString());
                    c.getPlayer().dropMessage(5, "����: " + victim.getStat().getStr() + "  ||  ����: " + victim.getStat().getDex() + "  ||  ����: " + victim.getStat().getInt() + "  ||  ����: " + victim.getStat().getLuk());
                    c.getPlayer().dropMessage(5, "ӵ�� " + victim.getMeso() + " ���.");
                } else {
                    c.getPlayer().dropMessage(5, "�Ҳ��������.");
                }
            }
            return 1;
        }
    }

    public static class �������� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            int total = 0;
            int curConnected = c.getChannelServer().getConnectedClients();
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            c.getPlayer().dropMessage(6, new StringBuilder().append("�l��: ").append(c.getChannelServer().getChannel()).append(" ��������: ").append(curConnected).toString());
            total += curConnected;
            for (MapleCharacter chr : c.getChannelServer().getPlayerStorage().getAllCharacters()) {
                if (chr != null && c.getPlayer().getGMLevel() >= chr.getGMLevel()) {
                    StringBuilder ret = new StringBuilder();
                    ret.append(" ��ɫ���� ");
                    ret.append(StringUtil.getRightPaddedStr(chr.getName(), ' ', 15));
                    ret.append(" ID: ");
                    ret.append(StringUtil.getRightPaddedStr(chr.getId() + "", ' ', 4));
                    ret.append(" �ȼ�: ");
                    ret.append(StringUtil.getRightPaddedStr(String.valueOf(chr.getLevel()), ' ', 4));
                    ret.append(" ְҵ: ");
                    ret.append(chr.getJob());
                    if (chr.getMap() != null) {
                        ret.append(" ��ͼ: ");
                        ret.append(chr.getMapId());
                        ret.append("(").append(chr.getMap().getMapName()).append(")");
                        c.getPlayer().dropMessage(6, ret.toString());
                    }
                }
            }
            c.getPlayer().dropMessage(6, new StringBuilder().append("��ǰƵ���ܼ���������: ").append(total).toString());
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            int channelOnline = c.getChannelServer().getConnectedClients();
            int totalOnline = 0;
            /*
             * �ŷ������˔�
             */
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                totalOnline += cserv.getConnectedClients();
            }
            c.getPlayer().dropMessage(6, new StringBuilder().append("��ǰ�������ܼ���������: ").append(totalOnline).append("��").toString());
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            return 1;
        }
    }

    public static class ������� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().dropMessage("��������: ");
            for (ChannelServer cs : ChannelServer.getAllInstances()) {
                c.getPlayer().dropMessage("[Ƶ�� " + cs.getChannel() + "]");
                StringBuilder sb = new StringBuilder();
                Collection<MapleCharacter> cmc = cs.getPlayerStorage().getAllCharacters();
                for (MapleCharacter chr : cmc) {
                    if (sb.length() > 150) {
                        sb.setLength(sb.length() - 2);
                        c.getPlayer().dropMessage(sb.toString());
                        sb = new StringBuilder();
                    }
                    if (!chr.isGM()) {
                        sb.append(MapleCharacterUtil.makeMapleReadable(" ID:" + chr.getId() + " ��ɫ����:" + chr.getName() + " �ȼ�: " + chr.getLevel() + " ��ͼ: " + chr.getMapId() + " " + chr.getMap().getMapName() + "\r\n"));
                    }
                }
                if (sb.length() >= 2) {
                    sb.setLength(sb.length() - 2);
                    c.getPlayer().dropMessage(sb.toString());
                }
            }
            return 1;
        }
    }

    /*  public static class ClearInv extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            java.util.Map<Pair<Short, Short>, MapleInventoryType> eqs = new ArrayMap<Pair<Short, Short>, MapleInventoryType>();
            if (splitted[1].equals("ȫ��")) {
                for (MapleInventoryType type : MapleInventoryType.values()) {
                    for (IItem item : c.getPlayer().getInventory(type)) {
                        eqs.put(new Pair<Short, Short>(item.getPosition(), item.getQuantity()), type);
                    }
                }
            } else if (splitted[1].equals("��װ������")) {
                for (IItem item : c.getPlayer().getInventory(MapleInventoryType.EQUIPPED)) {
                    eqs.put(new Pair<Short, Short>(item.getPosition(), item.getQuantity()), MapleInventoryType.EQUIPPED);
                }
            } else if (splitted[1].equals("����")) {
                for (IItem item : c.getPlayer().getInventory(MapleInventoryType.EQUIP)) {
                    eqs.put(new Pair<Short, Short>(item.getPosition(), item.getQuantity()), MapleInventoryType.EQUIP);
                }
            } else if (splitted[1].equals("����")) {
                for (IItem item : c.getPlayer().getInventory(MapleInventoryType.USE)) {
                    eqs.put(new Pair<Short, Short>(item.getPosition(), item.getQuantity()), MapleInventoryType.USE);
                }
            } else if (splitted[1].equals("װ��")) {
                for (IItem item : c.getPlayer().getInventory(MapleInventoryType.SETUP)) {
                    eqs.put(new Pair<Short, Short>(item.getPosition(), item.getQuantity()), MapleInventoryType.SETUP);
                }
            } else if (splitted[1].equals("����")) {
                for (IItem item : c.getPlayer().getInventory(MapleInventoryType.ETC)) {
                    eqs.put(new Pair<Short, Short>(item.getPosition(), item.getQuantity()), MapleInventoryType.ETC);
                }
            } else if (splitted[1].equals("����")) {
                for (IItem item : c.getPlayer().getInventory(MapleInventoryType.CASH)) {
                    eqs.put(new Pair<Short, Short>(item.getPosition(), item.getQuantity()), MapleInventoryType.CASH);
                }
            } else {
                c.getPlayer().dropMessage(6, "[ȫ��/��װ������/����/����/װ��/����/����]");
            }
            for (Entry<Pair<Short, Short>, MapleInventoryType> eq : eqs.entrySet()) {
                MapleInventoryManipulator.removeFromSlot(c, eq.getValue(), eq.getKey().left, eq.getKey().right, false, false);
            }
            return 1;
        }
    }
    
        public static class ����ǰ��ͼ��� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (!c.getPlayer().isAdmin()) {
                return 0;
            }
            if (splitted.length < 2) {
                c.getPlayer().dropMessage(6, "�÷�: !����ǰ��ͼ��� [�������1����� - 2������] [�������]");
                return 0;
            }
            int type = Integer.parseInt(splitted[1]);
            int quantity = Integer.parseInt(splitted[2]);
            if ((type <= 0) || (type > 2)) {
                type = 2;
            }
            if (quantity > 9000) {
                quantity = 9000;
            }
            int ret = 0;
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv.getPlayerStorage().getAllCharacters()) {
                    if (c.getPlayer().getMapId() == mch.getMapId()) {
                        mch.modifyCSPoints(type, quantity, false);
                        mch.dropMessage(-11, new StringBuilder().append("[ϵͳ��ʾ] ��ϲ����ù���Ա���͸�����").append(type == 1 ? "��ȯ " : " ����ȯ ").append(quantity).append(" ��.").toString());
                        ret++;
                    }
                }
            }
            if (type == 1) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        if (c.getPlayer().getMapId() == mch.getMapId()) {
                            mch.startMapEffect(ServerProperties.getProperty("ZEV.CommandMessage") + "����Ա����" + quantity + "������ǰ��ͼ���ߵ�������ң����л����Ա�ɣ�", 5120027);
                        }
                    }
                }
            } else if (type == 2) {
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        if (c.getPlayer().getMapId() == mch.getMapId()) {
                            mch.startMapEffect(ServerProperties.getProperty("ZEV.CommandMessage") + "����Ա����" + quantity + "���þ����ǰ��ͼ���ߵ�������ң����л����Ա�ɣ�", 5120027);
                        }
                    }
                }
            }
            c.getPlayer().dropMessage(6, new StringBuilder().append("����ʹ�óɹ�����ǰ����: ").append(ret).append(" ����һ��: ").append(quantity).append(" ���").append(type == 1 ? "��ȯ " : " ����ȯ ").append(" �ܼ�: ").append(ret * quantity).toString());
            return 1;
        }
    }

    public static class ����ǰ��ͼ���� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (!c.getPlayer().isAdmin()) {
                return 0;
            }
            int quantity = Integer.parseInt(splitted[1]);
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv.getPlayerStorage().getAllCharacters()) {
                    //     mch.gainMeso(quantity, true);
                    if (c.getPlayer().getMapId() == mch.getMapId()) {
                        mch.gainExp(quantity, true, false, true);
                    }
                }
            }
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (c.getPlayer().getMapId() == mch.getMapId()) {
                        mch.startMapEffect(ServerProperties.getProperty("ZEV.CommandMessage") + "����Ա����" + quantity + "�������ǰ��ͼ���ߵ�������ң����л����Ա�ɣ�", 5120027);
                    }
                }
            }
            return 1;
        }
    }

    public static class ����ǰ��ͼð�ձ� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (!c.getPlayer().isAdmin()) {
                return 0;
            }
            int quantity = Integer.parseInt(splitted[1]);
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv.getPlayerStorage().getAllCharacters()) {
                    if (c.getPlayer().getMapId() == mch.getMapId()) {
                        mch.gainMeso(quantity, true);
                    }
                }
            }
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (c.getPlayer().getMapId() == mch.getMapId()) {
                        mch.startMapEffect(ServerProperties.getProperty("ZEV.CommandMessage") + "����Ա����" + quantity + "ð�ձҸ���ǰ��ͼ���ߵ�������ң����л����Ա�ɣ�", 5120027);
                    }

                }
            }
            return 1;
        }
    }

    public static class ����ǰ��ͼ��Ʒ extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (!c.getPlayer().isAdmin()) {
                return 0;
            }
            if (splitted.length < 2) {
                c.getPlayer().dropMessage(6, "�÷�: !����ǰ��ͼ��Ʒ [��ƷID] [����]");
                return 0;
            }
            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            int item = Integer.parseInt(splitted[1]);
            int quantity = Integer.parseInt(splitted[2]);
            String mz = ii.getName(item);
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv.getPlayerStorage().getAllCharacters()) {
                    if (c.getPlayer().getMapId() == mch.getMapId()) {
                        MapleInventoryManipulator.addById(mch.getClient(), item, (short) quantity, (byte) 0);
                    }
                }
            }
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (quantity <= 1) {
                        if (c.getPlayer().getMapId() == mch.getMapId()) {
                            mch.startMapEffect(ServerProperties.getProperty("ZEV.CommandMessage") + "����Ա���š�" + mz + "����Ʒ����ǰ��ͼ���ߵ�������ң����л����Ա�ɣ�", 5120027);
                        }

                    } else {
                        if (c.getPlayer().getMapId() == mch.getMapId()) {
                            mch.startMapEffect(ServerProperties.getProperty("ZEV.CommandMessage") + "����Ա���š�" + mz + "����Ʒ��" + quantity + "��������ǰ��ͼ���ߵ�������ң����л����Ա�ɣ�", 5120027);
                        }

                    }
                }
            }
            return 1;
        }
    }
    
        public static class ���������� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (!c.getPlayer().isAdmin()) {
                return 0;
            }
            Map connected = World.getConnected();
            StringBuilder conStr = new StringBuilder("��������: ");
            boolean first = true;
            for (Iterator i$ = connected.keySet().iterator(); i$.hasNext();) {
                int i = ((Integer) i$.next()).intValue();
                if (!first) {
                    conStr.append(", ");
                } else {
                    first = false;
                }
                if (i == 0) {
                    conStr.append("�ܼ�: ");
                    conStr.append(connected.get(Integer.valueOf(i)));
                } else {
                    conStr.append("Ƶ�� ");
                    conStr.append(i);
                    conStr.append(": ");
                    conStr.append(connected.get(Integer.valueOf(i)));
                }
            }
            c.getPlayer().dropMessage(conStr.toString());
            return 1;
        }
    }
        
     public static class ��ʱNPC extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            int npcId = Integer.parseInt(splitted[1]);
            MapleNPC npc = MapleLifeFactory.getNPC(npcId);
            if (npc != null && !npc.getName().equals("MISSINGNO")) {
                npc.setPosition(c.getPlayer().getPosition());
                npc.setCy(c.getPlayer().getPosition().y);
                npc.setRx0(c.getPlayer().getPosition().x + 50);
                npc.setRx1(c.getPlayer().getPosition().x - 50);
                npc.setFh(c.getPlayer().getMap().getFootholds().findBelow(c.getPlayer().getPosition()).getId());
                npc.setCustom(true);
                c.getPlayer().getMap().addMapObject(npc);
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
            } else {
                c.getPlayer().dropMessage(6, "You have entered an invalid NPC ID");
                return 0;
            }
            return 1;
        }
    }
     
    public static class ����NPC extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (splitted.length < 1) {
                c.getPlayer().dropMessage(6, "!pnpc [npcid]");
                return 0;
            }
            int npcId = Integer.parseInt(splitted[1]);
            MapleNPC npc = MapleLifeFactory.getNPC(npcId);
            if (npc != null && !npc.getName().equals("MISSINGNO")) {
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
                    Connection con = (Connection) DatabaseConnection.getConnection();
                    try (PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO spawns (idd, f, hide, fh, cy, rx0, rx1, type, x, y, mid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
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
                    c.getPlayer().dropMessage(6, "Failed to save NPC to the database");
                }
                c.getPlayer().getMap().addMapObject(npc);
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
                c.getPlayer().dropMessage(6, "Please do not reload this map or else the NPC will disappear till the next restart.");
            } else {
                c.getPlayer().dropMessage(6, "You have entered an invalid Npc-Id");
                return 0;
            }
            return 1;
        }
    }
    
    public static class ����������Ʒ extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (!c.getPlayer().isAdmin()) {
                return 0;
            }
            if (splitted.length < 2) {
                c.getPlayer().dropMessage(6, "�÷�: !����������Ʒ [��ƷID] [����]");
                return 0;
            }
            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            int item = Integer.parseInt(splitted[1]);
            int quantity = Integer.parseInt(splitted[2]);
            String mz = ii.getName(item);
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv.getPlayerStorage().getAllCharacters()) {
                    MapleInventoryManipulator.addById(mch.getClient(), item, (short) quantity, (byte) 0);
                }
            }
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (quantity <= 1) {
                          mch.startMapEffect(ServerProperties.getProperty("ZEV.CommandMessage")+"����Ա���š�" + mz + "����Ʒ�����ߵ�������ң����л����Ա�ɣ�", 5120027);
                       
                    } else {
                          mch.startMapEffect(ServerProperties.getProperty("ZEV.CommandMessage")+"����Ա���š�" + mz + "����Ʒ��" + quantity + "���������ߵ�������ң����л����Ա�ɣ�", 5120027);
                       
                    }
                }
            }
            return 1;
        }
    }
    
    public static class �������Ʒ extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (!c.getPlayer().isAdmin()) {
                return 0;
            }
            if (splitted.length < 3) {
                c.getPlayer().dropMessage(6, "�÷�: !�������Ʒ [��ɫ����][��ƷID] [����]");
                return 0;
            }
            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            String name = splitted[1];
            int item = Integer.parseInt(splitted[2]);
            int quantity = Integer.parseInt(splitted[3]);
            String mz = ii.getName(item);
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv.getPlayerStorage().getAllCharacters()) {
                    if (mch.getName().equals(name)) {
                        MapleInventoryManipulator.addById(mch.getClient(), item, (short) quantity, (byte) 0);
                        c.getPlayer().dropMessage(6, "����ɹ���");
                    }
                }
            }
            return 1;
        }
    }
    
    public static class �Ͽ�������� extends CommandExecute {

        public int execute(MapleClient c, String[] splitted) {
            if (!c.getPlayer().isAdmin()) {
                return 0;
            }
            MapleCharacter victim = c.getChannelServer().getPlayerStorage().getCharacterByName(splitted[(splitted.length - 1)]);
            if ((victim != null) && (c.getPlayer().getGMLevel() >= victim.getGMLevel())) {
                victim.getClient().getSession().close();
                victim.getClient().disconnect(true, false);
                c.getPlayer().dropMessage(6, "�Ѿ��ɹ��Ͽ� " + victim.getName() + " ������.");
                return 1;
            }
            c.getPlayer().dropMessage(6, "ʹ�õĶ��󲻴��ڻ��߽�ɫ���ִ�����߶Էŵ�GMȨ�ޱ����.");
            return 0;
        }
    }
    
  public static class ����ģʽ extends CommandExecute {

        public int execute(MapleClient c, String[] splitted) {
            if (!c.getPlayer().isAdmin()) {
                return 0;
            }
            SkillFactory.getSkill(9001004).getEffect(1).applyTo(c.getPlayer());
            c.getPlayer().dropMessage(6, "����ģʽ�ѿ���.");
            return 0;
        }
    }
  
    public static class ��������� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {

            if (splitted.length < 2 ) {
                c.getPlayer().dropMessage(6, "�÷�: !��������� <����Ƶ��> <����1-6>");
                return 0;
            }
            int channel = Integer.parseInt(splitted[1]);
            int lx = Integer.parseInt(splitted[2]);
            final EventManager em = c.getChannelServer().getEventSM().getEventManager("AutomatedEvent");
            if (em != null) {
                em.scheduleRandomEventInChannel(c.getPlayer(), channel, lx);
            }
            return 1;
        }
    }*/
}
