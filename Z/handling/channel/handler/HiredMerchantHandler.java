/*
��Ӷ�̵�
 */
package handling.channel.handler;

import java.rmi.RemoteException;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import client.inventory.IItem;
import client.inventory.MapleInventoryType;
import client.MapleClient;
import client.MapleCharacter;
import client.inventory.IEquip;
import constants.GameConstants;
import client.inventory.ItemLoader;
import database.DatabaseConnection;
import handling.world.World;
import java.util.Map;
import scripting.NPCScriptManager;
import server.MapleInventoryManipulator;
import server.MerchItemPackage;
import server.MapleItemInformationProvider;
import server.ServerProperties;
import tools.FileoutputUtil;
import tools.MaplePacketCreator;
import tools.Pair;
import tools.StringUtil;
import tools.packet.PlayerShopPacket;
import tools.data.LittleEndianAccessor;

public class HiredMerchantHandler {

    /**
     * <�����Ӷ>
     */
    public static final void UseHiredMerchant(final LittleEndianAccessor slea, final MapleClient c) {
        if (gui.Start.ConfigValuesMap.get("��Ӷ���˿���") <= 0) {
            if (c.getPlayer().getMap().allowPersonalShop()) {
                final byte state = checkExistance(c.getPlayer().getAccountID(), c.getPlayer().getId());

                switch (state) {
                    case 1:
                        c.getPlayer().dropMessage(1, "����ȥ��ȡ��֮ǰ��̯�Ķ���");
                        break;
                    case 0:
                        boolean merch = World.hasMerchant(c.getPlayer().getAccountID());
                        if (!merch) {
                            c.sendPacket(PlayerShopPacket.sendTitleBox());
                            if (c.getPlayer().getBossLog("�ճ�������Ӷ") <= 0) {
                                c.getPlayer().setBossLog("�ճ�������Ӷ");//Ϊ�Լ��Ӽ�¼
                            }
                        } else {
                            c.getPlayer().dropMessage(1, "�뻻���ط������������Ѿ��п�����");
                        }
                        break;
                    default:
                        c.getPlayer().dropMessage(1, "����δ֪����.");
                        break;
                }
            } else {
                c.getSession().close();
            }
        } else {
            c.getPlayer().dropMessage(1, "����Ա�Ѿ��Ӻ�̨�ر��˹�Ӷ���˹���");
        }
    }

    /**
     * <��Ӷ������>
     */
    private static final byte checkExistance(final int accid, final int charid) {
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

    /**
     * <��Ӷȡ��>
     */
    public static void MerchantItemStore(final LittleEndianAccessor slea, final MapleClient c) {
        if (c.getPlayer() == null) {
            return;
        }
        final byte operation = slea.readByte();
        /*if (operation == 20 || operation == 26) {
            if (c.getPlayer().getLastHM() + 5 * 60 * 1000 > System.currentTimeMillis()) {
                c.getPlayer().dropMessage(1, "5 ���Ӻ������ع�Ӷ��Ʒ��");
                c.sendPacket(MaplePacketCreator.enableActions());
                c.getPlayer().setConversation(0);
                return;
            }
        }*/
        switch (operation) {
            /**
             * <�򿪹�Ӷ������ȡ>
             */
            case 20: {
                slea.readMapleAsciiString();
                final int conv = c.getPlayer().getConversation();
                /**
                 * <��Ӷ�̵�ID���˺�ID��>
                 */
                boolean merch = World.hasMerchant(c.getPlayer().getAccountID());
                if (merch) {
                    c.getPlayer().dropMessage(1, "��ر��̵������һ��.");
                    c.getPlayer().setConversation(0);
                } else if (conv == 3) { // Hired Merch �����Ķ���
                    //�������ж���û����Ҫ��ȡ�Ķ���
                    final MerchItemPackage pack = loadItemFrom_Database(c.getPlayer().getId(), c.getPlayer().getAccountID());
                    if (pack == null) {
                        c.getPlayer().dropMessage(1, "��û����Ʒ������ȡ!");
                        c.getPlayer().setConversation(0);
                    } else if (pack.getItems().size() <= 0) { //error fix for complainers.���ڱ�Թ�����޸���
                        if (!check(c.getPlayer(), pack)) {
                            c.sendPacket(PlayerShopPacket.merchItem_Message((byte) 0x21));
                            return;
                        }
                        if (deletePackage(c.getPlayer().getId(), c.getPlayer().getAccountID(), pack.getPackageid())) {
                            FileoutputUtil.logToFile_chr(c.getPlayer(), "����˼�¼��Ϣ/��Ҹ�����Ϣ��¼/" + c.getPlayer().getName() + "������Ϣ/��Ӷ�����ȡ��¼.txt", " ��ؽ�� " + pack.getMesos());
                            c.getPlayer().gainMeso(pack.getMesos(), false);
                            c.getPlayer().setConversation(0);
                            c.getPlayer().dropMessage(1, "��Ӷ��ؽ�� : " + pack.getMesos());
                            //     c.sendPacket(PlayerShopPacket.merchItem_Message((byte) 0x1d));
                            //      c.getPlayer().setLastHM(System.currentTimeMillis());
                        } else {
                            c.getPlayer().dropMessage(1, "����δ֪����");
                        }
                        c.getPlayer().setConversation(0);
                        c.sendPacket(MaplePacketCreator.enableActions());
                    } else {
                        c.sendPacket(PlayerShopPacket.merchItemStore_ItemData(pack));
                    }
                }
                break;
            }
            /**
             * <��ʾ�����ͳ���Ʒ>
             */
            case 25: {
                if (c.getPlayer().getConversation() != 3) {
                    return;
                }
                c.sendPacket(PlayerShopPacket.merchItemStore((byte) 0x24));
                //c.getPlayer().saveToDB(false, false);
                break;
            }
            /**
             * <�ͳ���Ʒ>
             */
            case 26: {
                if (c.getPlayer().getConversation() != 3) {
                    c.getPlayer().dropMessage(1, "����δ֪����1.");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                final MerchItemPackage pack = loadItemFrom_Database(c.getPlayer().getId(), c.getPlayer().getAccountID());

                if (pack == null) {
                    c.getPlayer().dropMessage(1, "����δ֪����\r\n��û����Ʒ������ȡ��");
                    c.getPlayer().���ߴ浵();
                    return;
                }
                if (!check(c.getPlayer(), pack)) {
                    c.sendPacket(PlayerShopPacket.merchItem_Message((byte) 0x21));
                    return;
                }
                /**
                 * <�����Ӷ�ĵ���>
                 */
                if (deletePackage(c.getPlayer().getId(), c.getPlayer().getAccountID(), pack.getPackageid())) {
                    c.getPlayer().gainMeso(pack.getMesos(), false);
                    for (IItem item : pack.getItems()) {
                        MapleInventoryManipulator.addFromDrop(c, item, false);
                    }
                    c.sendPacket(PlayerShopPacket.merchItem_Message((byte) 0x1d));
                    String item_id = "";
                    String item_name = "";
                    for (IItem item : pack.getItems()) {
                        item_id += item.getItemId() + "(" + item.getQuantity() + "), ";
                        item_name += MapleItemInformationProvider.getInstance().getName(item.getItemId()) + "(" + item.getQuantity() + "), ";
                    }
                    ɾ������(c.getPlayer().getId());
                    c.getPlayer().���ߴ浵();
                    FileoutputUtil.logToFile_chr(c.getPlayer(), "����˼�¼��Ϣ/��Ӷ��ȡ��¼.txt", " ��ؽ�� " + pack.getMesos() + " ��ص������� " + pack.getItems().size() + " ���ߴ��� " + item_id + " �������� " + item_name);
                    // FileoutputUtil.logToFile_chr(c.getPlayer(), "����˼�¼��Ϣ/��Ӷ��ȡ��¼2.txt", " ��ؽ�� " + pack.getMesos() + " ��ص������� " + pack.getItems().size() + " ���� " + item_name);
                    //    c.getPlayer().setLastHM(System.currentTimeMillis());
                } else {
                    c.getPlayer().dropMessage(1, "����δ֪����.");
                }
                break;
            }
            /**
             * <�뿪��Ӷ����Ա>
             */
            case 27: {
                c.getPlayer().setConversation(0);
                //c.getPlayer().���ߴ浵();
                break;
            }

        }
        //c.getPlayer().saveToDB(false, false);
    }

    private static void getShopItem(MapleClient c) {
        if (c.getPlayer().getConversation() != 3) {
            return;
        }
        final MerchItemPackage pack = loadItemFrom_Database(c.getPlayer().getId(), c.getPlayer().getAccountID());

        if (pack == null) {
            c.getPlayer().dropMessage(1, "����δ֪����");
            return;
        }
        if (!check(c.getPlayer(), pack)) {
            c.getPlayer().dropMessage(1, "�㱳�����Ӳ�����");
            //    c.sendPacket(PlayerShopPacket.merchItem_Message((byte) 0x21));
            return;
        }
        if (deletePackage(c.getPlayer().getId(), c.getPlayer().getAccountID(), pack.getPackageid())) {
            c.getPlayer().gainMeso(pack.getMesos(), false);
            for (IItem item : pack.getItems()) {
                MapleInventoryManipulator.addFromDrop(c, item, false);
            }
            c.getPlayer().dropMessage(5, "��ȡ�ɹ���");
            c.getPlayer().saveToDB(false, false);
            //  c.sendPacket(PlayerShopPacket.merchItem_Message((byte) 0x1d));
        } else {
            c.getPlayer().dropMessage(1, "����δ֪����");
        }
    }

    private static final boolean check(final MapleCharacter chr, final MerchItemPackage pack) {
        if (chr.getMeso() + pack.getMesos() < 0) {
            return false;
        }
        byte eq = 0, use = 0, setup = 0, etc = 0, cash = 0;
        for (IItem item : pack.getItems()) {
            final MapleInventoryType invtype = GameConstants.getInventoryType(item.getItemId());
            if (null != invtype) {
                switch (invtype) {
                    case EQUIP:
                        eq++;
                        break;
                    case USE:
                        use++;
                        break;
                    case SETUP:
                        setup++;
                        break;
                    case ETC:
                        etc++;
                        break;
                    case CASH:
                        cash++;
                        break;
                    default:
                        break;
                }
            }

        }
        if (chr.getInventory(MapleInventoryType.EQUIP).getNumFreeSlot() <= eq
                || chr.getInventory(MapleInventoryType.USE).getNumFreeSlot() <= use
                || chr.getInventory(MapleInventoryType.SETUP).getNumFreeSlot() <= setup
                || chr.getInventory(MapleInventoryType.ETC).getNumFreeSlot() <= etc
                || chr.getInventory(MapleInventoryType.CASH).getNumFreeSlot() <= cash) {
            return false;
        }
        return true;
    }

    private static final boolean deletePackage(final int charid, final int accid, final int packageid) {
        final Connection con = DatabaseConnection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement("DELETE from hiredmerch where characterid = ? OR accountid = ? OR packageid = ?");
            ps.setInt(1, charid);
            ps.setInt(2, accid);
            ps.setInt(3, packageid);
            ps.execute();
            ps.close();
            //System.out.println("4");
            ItemLoader.HIRED_MERCHANT.saveItems(null, packageid, accid, charid);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void ɾ������(int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int a10, int a11, int a12, int a13, int a14, int a15, int a16, int a17, int a18, int a19, int a20, int a21, int a22, int a23, int a24, int a25, int a26, int a27, int a28, int a29, String a30) {
        try {
            ResultSet rs = null;
            PreparedStatement ps = null;
            ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM hire");
            rs = ps.executeQuery();
            if (rs.next()) {
                int a = 0;

                String sqlstr = " delete from hire where "
                        + "cid = " + a1 + " && "
                        + "itemid = " + a2 + "&& "
                        + "upgradeslots = " + a3 + " && "
                        + "level = " + a4 + " &&"
                        + "str=" + a5 + " &&"
                        + "dex=" + a6 + " && "
                        + "2int=" + a7 + "&&"
                        + "luk=" + a8 + "&&"
                        + "hp=" + a9 + " &&"
                        + "mp=" + a10 + "&&"
                        + "watk=" + a11 + "&&"
                        + "matk=" + a12 + "&&"
                        + "wdef=" + a13 + "&&"
                        + "mdef=" + a14 + "&&"
                        + "acc=" + a15 + "&&"
                        + "avoid=" + a16 + "&&"
                        + "hands=" + a17 + "&&"
                        + "speed=" + a18 + "&&"
                        + "jump=" + a19 + "&&"
                        + "ViciousHammer=" + a20 + "&&"
                        + "itemEXP=" + a21 + "&&"
                        + "durability=" + a22 + "&&"
                        + "enhance=" + a23 + "&&"
                        + "potential1=" + a24 + "&&"
                        + "potential2=" + a25 + "&&"
                        + "potential3=" + a26 + "&&"
                        + "hpR=" + a27 + "&&"
                        + "mpR=" + a28 + "&&"
                        + "itemlevel=" + a29 + "&&"
                        + "mxmxd_dakong_fumo= '" + a30 + "' LIMIT 1";
                ps.executeUpdate(sqlstr);
                //ps.close();

            }
        } catch (SQLException ex) {

        }
    }

    public static void ɾ������(int a1) {
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
                //ps.close();

            }
        } catch (SQLException ex) {

        }
    }

    private static final MerchItemPackage loadItemFrom_Database(final int charid, final int accountid) {
        final Connection con = DatabaseConnection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * from hiredmerch where characterid = ? OR accountid = ?");
            ps.setInt(1, charid);
            ps.setInt(2, accountid);
            //System.out.println("5");
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
            Map<Integer, Pair<IItem, MapleInventoryType>> items = ItemLoader.HIRED_MERCHANT.loadItems_hm(packageid, accountid);
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

}
