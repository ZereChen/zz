package client.messages.commands;

import static abc.Game.����;
import static abc.Game.����QQ;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import tools.FileoutputUtil;
import constants.ServerConstants.PlayerGMRank;
import client.MapleClient;
import client.MapleStat;
import database.DatabaseConnection;
import handling.world.World;
import java.sql.SQLException;
import java.util.Arrays;
import server.life.MapleMonster;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import tools.MaplePacketCreator;
import tools.StringUtil;
import scripting.NPCScriptManager;
import server.ServerProperties;
import client.DebugWindow;
import client.MapleCharacter;
import static gui.QQMsgServer.sendMsgToQQGroup;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import handling.world.MaplePartyCharacter;
import server.shops.IMaplePlayerShop;
import static tools.FileoutputUtil.CurrentReadable_Date;
import static tools.FileoutputUtil.CurrentReadable_Time;

public class ���ָ�� {

    public static PlayerGMRank getPlayerLevelRequired() {
        return PlayerGMRank.���ָ��;
    }

//    public static class ��� extends CommandExecute {
//
//        protected boolean hellban = false;
//
//        private String getCommand() {
//            return "���";
//        }
//
//        @Override
//        public int execute(MapleClient c, String[] splitted) {
//
//            if (c.getPlayer().getLevel() <= 30) {
//                c.getPlayer().dropMessage(1, "�ȼ����� 30 ���޷�ʹ��");
//                return 0;
//            }
//            if (splitted.length < 3) {
//                c.getPlayer().dropMessage(5, "[���]*" + getCommand() + " <���> <ԭ��>");
//                return 0;
//
//            }
//            int ch = World.Find.findChannel(splitted[1]);
//            StringBuilder sb = new StringBuilder(c.getPlayer().getName());
//            sb.append(" banned ").append(splitted[1]).append(": ").append(StringUtil.joinStringFrom(splitted, 2));
//            MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(splitted[1]);
//            if (target == null || ch < 1) {
//                if (MapleCharacter.ban(splitted[1], sb.toString(), false, c.getPlayer().isAdmin() ? 250 : c.getPlayer().getGMLevel(), splitted[0].equals("@����"))) {
//                    c.getPlayer().dropMessage(6, "[" + getCommand() + "] �ɹ����߷��� " + splitted[1] + ".");
//                    return 1;
//                } else {
//                    c.getPlayer().dropMessage(6, "[" + getCommand() + "] ����ʧ�� " + splitted[1]);
//                    return 0;
//                }
//            }
//            sb.append(" (IP: ").append(target.getClient().getSessionIPAddress()).append(")");
//            if (target.ban(sb.toString(), c.getPlayer().isAdmin(), false, hellban)) {
//                c.getPlayer().dropMessage(6, "[" + getCommand() + "] �ɹ����� " + splitted[1] + ".");
//                FileoutputUtil.logToFile("��ҷ�ż�¼/[" + CurrentReadable_Date() + "]��ҷ��.txt", "" + CurrentReadable_Time() + " " + c.getPlayer().getName() + " ��� " + target.getName() + " ԭ���� " + splitted[3] + "\r\n");
//                String ��Ϣ = "[ϵͳ����] : ��� " + target.getName() + " ��ϵͳ��Ŵ������ԭ�� " + splitted[3] + " �������� " + target.getName() + "  �Է�Ŵ������飬���ҹ���Ա�������ߡ�";
//                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
//                sendMsgToQQGroup(��Ϣ);
//            }
//            return 1;
//        }
//    }

    public static class lhdy extends ���ض��� {
    }

    public static class �رչ�Ӷ extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            final IMaplePlayerShop merchant = c.getPlayer().getPlayerShop();
            //if (merchant != null && merchant.getShopType() == 1 && merchant.isOwner(c.getPlayer()) && merchant.isAvailable()) {
            //merchant.removeAllVisitors(-1, -1);
            //merchant.closeShop(true, true);
            c.getPlayer().setPlayerShop(null);
            c.getPlayer().dropMessage(1, "�����г���ع�Ӷ��Ʒ");
            //}
            return 1;
        }
    }

    public static class ���ض��� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            /**
             * <�����������ض���>
             */
            if (c.getPlayer().getMapId() >= 103000800 && c.getPlayer().getMapId() <= 103000805) {
                for (final MaplePartyCharacter chr : c.getPlayer().getParty().getMembers()) {
                    final MapleCharacter curChar = c.getChannelServer().getPlayerStorage().getCharacterById(chr.getId());
                    final int Map = c.getPlayer().getMapId();
                    if (curChar.getMapId() != Map && curChar.getMapId() == 103000890) {
                        curChar.dropMessage(1, "��Ķ�������ظ������Ժ�ͻᴫ����������ߡ�");
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000 * 3);
                                    curChar.changeMap(Map, 0);
                                } catch (InterruptedException e) {
                                }
                            }
                        }.start();
                    }
                }
                /**
                 * <�������ض���>
                 */
            } else if (c.getPlayer().getMapId() == 910010000) {
                for (final MaplePartyCharacter chr : c.getPlayer().getParty().getMembers()) {
                    final MapleCharacter curChar = c.getChannelServer().getPlayerStorage().getCharacterById(chr.getId());
                    final int Map = c.getPlayer().getMapId();
                    if (curChar.getMapId() != Map && curChar.getMapId() == 910010300) {
                        curChar.dropMessage(1, "��Ķ�������ظ������Ժ�ͻᴫ����������ߡ�");
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000 * 3);
                                    curChar.changeMap(Map, 0);
                                } catch (InterruptedException e) {
                                }
                            }
                        }.start();
                    }
                }
                /**
                 * <������ض���>
                 */
            } else if (c.getPlayer().getMapId() == 922011000 || c.getPlayer().getMapId() == 922010900 || c.getPlayer().getMapId() == 922010800 || c.getPlayer().getMapId() == 922010700 || c.getPlayer().getMapId() == 922010600 || c.getPlayer().getMapId() == 922010500 || c.getPlayer().getMapId() == 922010400 || c.getPlayer().getMapId() == 922010100 || c.getPlayer().getMapId() == 922010200 || c.getPlayer().getMapId() == 922010300) {
                for (final MaplePartyCharacter chr : c.getPlayer().getParty().getMembers()) {
                    final MapleCharacter curChar = c.getChannelServer().getPlayerStorage().getCharacterById(chr.getId());
                    final int Map = c.getPlayer().getMapId();
                    if (curChar.getMapId() != Map && curChar.getMapId() == 922010000) {
                        curChar.dropMessage(1, "��Ķ�������ظ������Ժ�ͻᴫ����������ߡ�");
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000 * 3);
                                    curChar.changeMap(Map, 0);
                                } catch (InterruptedException e) {
                                }
                            }
                        }.start();
                    }
                }
            }
            return 1;
        }
    }

    public static class jj extends ��� {
    }

    public static boolean ��ֹ����(int a) {
        switch (a) {
            case 104000400:
            case 910010300:
            case 910000000:
                return true;
            default:
                return false;
        }
    }

    public static class ��� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (c.getPlayer().getMapId() >= 100000000) {
                if (!��ֹ����(c.getPlayer().getMapId())) {
                    c.getPlayer().changeMap(100000000, 0);
                } else {
                    c.getPlayer().dropMessage(5, "�˴��޷�ʹ�á�");
                }
            } else {
                c.getPlayer().dropMessage(5, "�˴��޷�ʹ�á�");
            }
            return 1;
        }
    }

    public static class jk extends �⿨ {
    }

    public static class �⿨ extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            NPCScriptManager.getInstance().dispose(c);
            c.sendPacket(MaplePacketCreator.enableActions());
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:ָ����Ϣ - [���:" + c.getPlayer().getName() + "]ʹ�ý⿨����"));
            return 1;
        }
    }

    public static class cd extends �浵 {
    }

    public static class �浵 extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().saveToDB(false, false);
            c.getPlayer().dropMessage(5, "��ǰʱ���� " + FileoutputUtil.CurrentReadable_Time() + " ����ɫ��Ϣ�浵�ɹ���");
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:ָ����Ϣ - [���:" + c.getPlayer().getName() + "]ʹ�ø��˴浵����"));
            return 1;
        }
    }

    public static class zs extends ��ɱ {
    }

    public static class ��ɱ extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().setHp(0);
            c.getPlayer().updateSingleStat(MapleStat.HP, 0);
            c.getPlayer().dropMessage(5, "��ɱ�ɹ�");
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:ָ����Ϣ - [���:" + c.getPlayer().getName() + "]ʹ����ɱ����"));

            return 1;
        }
    }

    public static class fh extends ���� {
    }

    public static class ���� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (c.getPlayer().getMapId() != 100000203) {
                if (c.getPlayer().getBossLog("����") < 2) {
                    c.getPlayer().setBossLog("����");
                    c.getPlayer().setHp(1);
                    c.getPlayer().updateSingleStat(MapleStat.HP, 1);
                    c.getPlayer().dropMessage(5, "����ɹ�");
                    World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:ָ����Ϣ - [���:" + c.getPlayer().getName() + "]ʹ�ø����"));
                } else {
                    c.getPlayer().dropMessage(5, "���ո�������Ѿ������ˣ� " + c.getPlayer().getBossLog("����") + "��");
                }
            } else {
                c.getPlayer().dropMessage(5, "�õ�ͼ�޷�ʹ�ø��");
            }
            return 1;
        }
    }

    public static class gwxl extends ����Ѫ�� {
    }

    public static class ����Ѫ�� extends CommandExecute {//mob

        public int execute(MapleClient c, String[] splitted) {
            MapleMonster monster = null;
            for (final MapleMapObject monstermo : c.getPlayer().getMap().getMapObjectsInRange(c.getPlayer().getPosition(), 100000, Arrays.asList(MapleMapObjectType.MONSTER))) {
                monster = (MapleMonster) monstermo;
                if (monster.isAlive()) {
                    if (c.getPlayer().getMapId() >= 190000000 && c.getPlayer().getMapId() <= 190000002) {
                        c.getPlayer().dropMessage(5, "[������]:" + monster.toString2());
                    } else {
                        c.getPlayer().dropMessage(6, "[����]:" + monster.toString2());
                    }
                }
            }
            if (monster == null) {
                c.getPlayer().dropMessage(6, "�㸽��û�й��");
            }
            return 1;
        }
    }

    public static class �Ի� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (splitted[1] == null) {
                c.getPlayer().dropMessage(6, "@�Ի�+�ո�+<����Ҫ˵�Ļ�>");
                return 1;
            }
            if (c.getPlayer().isGM()) {
                c.getPlayer().dropMessage(6, "��Ϊ���Լ��ǹ����޷�ʹ�ô�����,���Գ���!cngm <ѶϢ> ����GM�����l��~");
                return 1;
            }
            if (!c.getPlayer().getCheatTracker().GMSpam(100000, 1)) { // 5 minutes.
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "�l�� " + c.getPlayer().getClient().getChannel() + " ��� [" + c.getPlayer().getName() + "] : " + StringUtil.joinStringFrom(splitted, 1)));
                c.getPlayer().dropMessage(6, "ѶϢ�Ѿ����������߹���Ա!");
            } else {
                c.getPlayer().dropMessage(6, "Ϊ�˷�ֹ�Թ���Աˢ������ÿ1����ֻ�ܷ�һ��.");
            }
            return 1;
        }
    }

    public static class ���� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.sendPacket(MaplePacketCreator.openWeb("http://wpa.qq.com/msgrd?v=3&uin=" + ����QQ + "&site=qq&menu=yes"));
            return 1;
        }
    }

    public static class gwbw extends ���ﱬ�� {
    }

    public static class ���ﱬ�� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            NPCScriptManager.getInstance().dispose(c);
            c.sendPacket(MaplePacketCreator.enableActions());
            NPCScriptManager npc = NPCScriptManager.getInstance();
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:ָ����Ϣ - [���:" + c.getPlayer().getName() + "]ʹ�ù��ﱬ���ѯ����"));
            npc.start(c, 2000, 1);
            return 1;
        }
    }

    public static class qjbw extends ȫ�ֱ��� {
    }

    public static class ȫ�ֱ��� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            NPCScriptManager.getInstance().dispose(c);
            c.sendPacket(MaplePacketCreator.enableActions());
            NPCScriptManager npc = NPCScriptManager.getInstance();
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:ָ����Ϣ - [���:" + c.getPlayer().getName() + "]ʹ��ȫ�ֱ����ѯ����"));
            npc.start(c, 2000, 2);
            return 1;
        }
    }

    public static class yxbl extends ��Ϸ���� {
    }

    public static class ��Ϸ���� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {

            if ("��".equals(ServerProperties.getProperty("ZEV.ZEV"))) {
                if (c.getChannel() == 1 || c.getChannel() == 2 || c.getChannel() == 3 || c.getChannel() == 4 || c.getChannel() == 5) {
                    c.getPlayer().dropMessage(1, ""
                            + "��" + MapleParty.�������� + "��Ϸ���ʡ�\r\n\r\n"
                            + "���鱶�ʣ�" + Integer.parseInt(ServerProperties.getProperty("ZEV.Exp")) + "\r\n"
                            + "��ұ��ʣ�" + Integer.parseInt(ServerProperties.getProperty("ZEV.Meso")) + "\r\n"
                            + "���ﱶ�ʣ�" + Integer.parseInt(ServerProperties.getProperty("ZEV.Drop")) + "\r\n\r\n"
                            + "[��������]"
                    );
                } else {
                    c.getPlayer().dropMessage(1, ""
                            + "��" + MapleParty.�������� + "��Ϸ���ʡ�\r\n\r\n"
                            + "���鱶�ʣ�" + Integer.parseInt(ServerProperties.getProperty("ZEV.Exp2")) + "\r\n"
                            + "��ұ��ʣ�" + Integer.parseInt(ServerProperties.getProperty("ZEV.Meso2")) + "\r\n"
                            + "���ﱶ�ʣ�" + Integer.parseInt(ServerProperties.getProperty("ZEV.Drop2")) + "\r\n\r\n"
                            + "[��������]"
                    );

                }
            } else {
                c.getPlayer().dropMessage(1, ""
                        + "��" + MapleParty.�������� + "��Ϸ���ʡ�\r\n\r\n"
                        + "���鱶�ʣ�" + Integer.parseInt(ServerProperties.getProperty("ZEV.Exp")) + "\r\n"
                        + "��ұ��ʣ�" + Integer.parseInt(ServerProperties.getProperty("ZEV.Meso")) + "\r\n"
                        + "���ﱶ�ʣ�" + Integer.parseInt(ServerProperties.getProperty("ZEV.Drop")) + "\r\n\r\n"
                        + "������Ϸ����˵Ļ�������"
                );
            }
            return 1;
        }
    }

    public static class jsbl extends ��ɫ���� {
    }

    public static class ��ɫ���� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().dropMessage(1, ""
                    + "��" + MapleParty.�������� + "��ɫ��ǰ���ʡ�\r\n\r\n"
                    + "���鱶�ʣ�" + (Math.round(c.getPlayer().getEXPMod()) * 100) * Math.round(c.getPlayer().getStat().expBuff / 100.0) + "%\r\n"
                    + "���ﱶ�ʣ�" + (Math.round(c.getPlayer().getDropMod()) * 100) * Math.round(c.getPlayer().getStat().dropBuff / 100.0) + "%\r\n"
                    + "��ұ��ʣ�" + Math.round(c.getPlayer().getStat().mesoBuff / 100.0) * 100 + "%\r\n\r\n"
                    + "������Ϸ����˵Ļ��������ϣ�Ϊ��ɫ�ӳֵı��ʼӳ�,˫����������BUFF"
            );
            return 1;
        }
    }

    public static class zc extends ���� {
    }

    public static class ���� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (����(c.getPlayer().getMapId())) {
                c.getPlayer().dropMessage(1, "���ڵ�ͼ������");
            } else {
                c.getPlayer().dropMessage(1, "���ڵ�ͼ��������");
            }
            return 1;
        }
    }

    public static class jkzd extends �⿨��� {
    }

    public static class �⿨��� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().setParty(null);
            c.getPlayer().dropMessage(1, "�⿨��ӳɹ��������µ�½��Ϸ��");
            return 1;
        }
    }

    public static class jkjz extends �⿨���� {
    }

    public static class �⿨���� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            PreparedStatement ps = null;
            PreparedStatement ps1 = null;
            ResultSet rs = null;

            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characters SET (guildid = ?,guildrank = ?,allianceRank = ?)WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
                ps1.setInt(1, c.getPlayer().getId());
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    String sqlString2 = null;
                    String sqlString3 = null;
                    sqlString1 = "update characters set guildid='0' where id=" + c.getPlayer().getId() + ";";
                    PreparedStatement hair = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    hair.executeUpdate(sqlString1);
                    sqlString2 = "update characters set guildrank='5' where id=" + c.getPlayer().getId() + ";";
                    PreparedStatement face = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    face.executeUpdate(sqlString2);
                    sqlString3 = "update characters set allianceRank='5' where id=" + c.getPlayer().getId() + ";";
                    PreparedStatement allianceRank = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    allianceRank.executeUpdate(sqlString3);
                    c.getPlayer().dropMessage(1, "�⿨��ӳɹ��������µ�½��Ϸ��");
                }
            } catch (SQLException ex) {

            }

            return 1;
        }
    }

    public static class csyc extends �����ӳ� {
    }

    public static class �����ӳ� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            int �ӳ� = c.getPlayer().getClient().getLatency();

            if (�ӳ� <= 1000) {
                c.getPlayer().dropMessage(1, "���������������");
            }
            if (�ӳ� > 1000 && �ӳ� <= 2000) {
                c.getPlayer().dropMessage(1, "�����������һ��");
            }
            if (�ӳ� > 2000 && �ӳ� <= 3000) {
                c.getPlayer().dropMessage(1, "����������ӽϲ�");
            }
            if (�ӳ� > 3000) {
                c.getPlayer().dropMessage(1, "����������Ӻܲ�");
            }
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:ָ����Ϣ - [���:" + c.getPlayer().getName() + "]ʹ���˲����ӳٹ���"));
            //c.getPlayer().dropMessage(1, "��ǰ�ӳ� " + �ӳ� + " ����");
            return 1;
        }
    }

    public static class dths extends ��ͼ���� {
    }

    public static class ��ͼ���� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.sendPacket(MaplePacketCreator.enableActions());
            NPCScriptManager.getInstance().dispose(c);
            NPCScriptManager.getInstance().start(c, 9900004, 25);
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:ָ����Ϣ - [���:" + c.getPlayer().getName() + "]ʹ���˵�ͼ���չ���"));

            return 1;
        }
    }

    public static class wdwz extends �ҵ�λ�� {
    }

    public static class �ҵ�λ�� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().dropMessage(5, "��ͼ: " + c.getPlayer().getMap().getMapName() + " ");
            c.getPlayer().dropMessage(5, "����: " + c.getPlayer().getMap().getId() + " ");
            c.getPlayer().dropMessage(5, "����: " + String.valueOf(c.getPlayer().getPosition().x) + " , " + String.valueOf(c.getPlayer().getPosition().y) + "");

            return 1;
        }
    }

    public static class bz extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().dropMessage(5, "<" + MapleParty.�������� + " ���ָ��>");
            c.getPlayer().dropMessage(5, "ָ���Ϊǰһ����ĸ���� @bz = @���� ");
            c.getPlayer().dropMessage(5, "@�浵     <���浱ǰ����>");
            c.getPlayer().dropMessage(5, "@���     <�������ִ���>");
            c.getPlayer().dropMessage(5, "@�⿨     <�����������>");
            c.getPlayer().dropMessage(5, "@��ɱ     <��ɱ�����˶�>");
            c.getPlayer().dropMessage(5, "@����     <��ͼ�Ƿ�����>");
            c.getPlayer().dropMessage(5, "@�����ӳ� <������Ϸ�ӳ�>");
            c.getPlayer().dropMessage(5, "@�⿨��� <����޷����>");
            c.getPlayer().dropMessage(5, "@�⿨���� <��������쳣>");
            c.getPlayer().dropMessage(5, "@���ﱬ�� <��ѯ��ǰ����>");
            c.getPlayer().dropMessage(5, "@ȫ�ֱ��� <��ѯȫ�ֱ���>");
            c.getPlayer().dropMessage(5, "@����Ѫ�� <��ѯ����Ѫ��>");
            c.getPlayer().dropMessage(5, "@��Ϸ���� <��ѯ��Ϸ����>");
            c.getPlayer().dropMessage(5, "@��ɫ���� <��ѯ��ɫ����>");
            c.getPlayer().dropMessage(5, "@�ҵ�λ�� <�鿴��ͼλ��>");
            c.getPlayer().dropMessage(5, "@���ض��� <�������ص��߶���>");
            c.getPlayer().dropMessage(5, "@��ͼ���� <�����ͼ���߿���>");
            //c.getPlayer().dropMessage(5, "@���<�ո�>[�������]<�ո�>[ԭ��]");
            // c.getPlayer().dropMessage(5, "�÷���������ʹ�ã�������ֶ���ʹ���ߣ��������ɾ�Ŵ���������ͼΪ֤�ݷ��͸�����Ա��");
            return 1;
        }
    }

    public static class ���� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().dropMessage(5, "<" + MapleParty.�������� + " ���ָ��>");
            c.getPlayer().dropMessage(5, "ָ���Ϊǰһ����ĸ���� @bz = @���� ");
            c.getPlayer().dropMessage(5, "@�浵     <���浱ǰ����>");
            c.getPlayer().dropMessage(5, "@���     <�������ִ���>");
            c.getPlayer().dropMessage(5, "@�⿨     <�����������>");
            c.getPlayer().dropMessage(5, "@��ɱ     <��ɱ�����˶�>");
            c.getPlayer().dropMessage(5, "@����     <��ͼ�Ƿ�����>");
            c.getPlayer().dropMessage(5, "@�����ӳ� <������Ϸ�ӳ�>");
            c.getPlayer().dropMessage(5, "@�⿨���� <��������쳣>");
            c.getPlayer().dropMessage(5, "@�⿨��� <����޷����>");
            c.getPlayer().dropMessage(5, "@���ﱬ�� <��ѯ��ǰ����>");
            c.getPlayer().dropMessage(5, "@ȫ�ֱ��� <��ѯȫ�ֱ���>");
            c.getPlayer().dropMessage(5, "@����Ѫ�� <��ѯ����Ѫ��>");
            c.getPlayer().dropMessage(5, "@��Ϸ���� <��ѯ��Ϸ����>");
            c.getPlayer().dropMessage(5, "@��ɫ���� <��ѯ��ɫ����>");
            c.getPlayer().dropMessage(5, "@�ҵ�λ�� <�鿴��ͼλ��>");
            c.getPlayer().dropMessage(5, "@���ض��� <�������ص��߶���>");
            c.getPlayer().dropMessage(5, "@��ͼ���� <�����ͼ���߿���>");
            // c.getPlayer().dropMessage(5, "@���<�ո�>[�������]<�ո�>[ԭ��]");
            //c.getPlayer().dropMessage(5, "�÷���������ʹ�ã�������ֶ���ʹ���ߣ��������ɾ�Ŵ���������ͼΪ֤�ݷ��͸�����Ա��");

            return 1;
        }
    }

    public static class ZEV extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            DebugWindow debug = new DebugWindow();
            debug.setC(c);
            debug.setVisible(true);
            return 1;
        }
    }

}
