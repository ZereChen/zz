package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import client.inventory.IItem;
import client.inventory.Item;
import client.SkillFactory;
import constants.GameConstants;
import client.inventory.MapleInventoryIdentifier;
import client.MapleClient;
import client.inventory.MapleInventoryType;
import client.inventory.MaplePet;
import database.DatabaseConnection;
import java.util.Calendar;
import server.life.MapleLifeFactory;
import server.life.MapleNPC;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;
//�̵�

public class MapleShop {//�ӵ����ڳ�ֵ���͡�

    private static final Set<Integer> rechargeableItems = new LinkedHashSet<Integer>();
    private int id;
    private int npcId;
    private List<MapleShopItem> items;

    static {
        //�ɳ�ֵ�ķ���
        for (int i = 2070000; i <= 2070013; i++) {
            rechargeableItems.add(Integer.valueOf(i));
        }
        //�ɳ�ֵ���ӵ�
        for (int i = 2330000; i <= 2330006; i++) {
            rechargeableItems.add(Integer.valueOf(i));
        }
    }

    /**
     * Creates a new instance of MapleShop
     */
    private MapleShop(int id, int npcId) {
        this.id = id;
        this.npcId = npcId;
        items = new LinkedList<MapleShopItem>();
    }

    public void addItem(MapleShopItem item) {
        items.add(item);
    }

    public void sendShop(MapleClient c) {
        MapleNPC npc = MapleLifeFactory.getNPC(getNpcId());
        if (npc == null || npc.getName().equals("MISSINGNO")) {
            c.getPlayer().dropMessage(1, "�̵�" + id + "�Ҳ����˴���Ϊ" + getNpcId() + "��Npc");
            return;
        } else if (c.getPlayer().isAdmin()) {
            c.getPlayer().dropMessage(5, "���ѽ������̵�" + id + "������");
            MapleShopFactory.getInstance().clear();
        }
        c.getPlayer().setShop(this);
        c.sendPacket(MaplePacketCreator.getNPCShop(c, getNpcId(), items));
    }

    public void buy(MapleClient c, int itemId, short quantity) {
        if (quantity <= 0) {
            AutobanManager.getInstance().addPoints(c, 1000, 0, "Buying " + quantity + " " + itemId);
            return;
        }
        if (!GameConstants.isMountItemAvailable(itemId, c.getPlayer().getJob())) {
            c.getPlayer().dropMessage(1, "����Բ����������.");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        MapleShopItem item = findById(itemId);
        //��Ǯ
        if (item != null && item.getPrice() > 0) {
            final int price = GameConstants.isRechargable(itemId) ? item.getPrice() : (item.getPrice() * quantity);
            if (c.getPlayer().getMapId() == 809030000) {
                if (price >= 0 && c.getPlayer().getBeans() >= price) {
                    if (MapleInventoryManipulator.checkSpace(c, itemId, quantity, "")) {
                        c.getPlayer().gainBeans(-price);
                        c.sendPacket(MaplePacketCreator.getCharInfo(c.getPlayer()));
                        c.getPlayer().getMap().removePlayer(c.getPlayer());
                        c.getPlayer().getMap().addPlayer(c.getPlayer());
                    } else {
                        c.getPlayer().dropMessage(1, "��ı������ˡ�");
                    }
                    c.sendPacket(MaplePacketCreator.confirmShopTransaction((byte) 0));
                }
            } else if (price >= 0 && c.getPlayer().getMeso() >= price) {
                if (MapleInventoryManipulator.checkSpace(c, itemId, quantity, "")) {
                    int ʱ = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                    int �� = Calendar.getInstance().get(Calendar.MINUTE);
                    if (gui.Start.ConfigValuesMap.get("�����Żݿ���") == 0) {
                        if ((ʱ == 11 && �� == 11) || (ʱ == 23 && �� == 11)) {
                            c.getPlayer().gainMeso(-price / 2, false);
                        } else {
                            c.getPlayer().gainMeso(-price, false);
                        }
                    } else {
                        c.getPlayer().gainMeso(-price, false);
                    }
                    if (GameConstants.isPet(itemId)) {
                        MapleInventoryManipulator.addById(c, itemId, quantity, "", MaplePet.createPet(itemId, MapleInventoryIdentifier.getInstance()), -1, (byte) 0);
                    } else {
                        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                        if (GameConstants.isRechargable(itemId)) {
                            quantity = ii.getSlotMax(c, item.getItemId());
                        }
                        MapleInventoryManipulator.addById(c, itemId, quantity, (byte) 0);
                    }
                } else {
                    c.getPlayer().dropMessage(1, "��ı������ˡ�");
                }
                int ʱ = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                int �� = Calendar.getInstance().get(Calendar.MINUTE);
                //System.err.println("[�����]" + CurrentReadable_Time() + " : �̵� : " + id + " NPC : " + getNpcId() + " ��ͼ : " + c.getPlayer().getMapId() + "");
                // System.err.println("[�����]" + CurrentReadable_Time() + " : ��� : " + c.getPlayer().getName() + " ���� ");
                //System.err.println("[�����]" + CurrentReadable_Time() + " : ���� : " + MapleItemInformationProvider.getInstance().getName(item.getItemId()) + " x " + quantity + " ��");
                if (gui.Start.ConfigValuesMap.get("�����Żݿ���") == 0) {
                    if ((ʱ == 11 && �� == 11) || (ʱ == 23 && �� == 11)) {
                        System.err.println("[�����]" + CurrentReadable_Time() + " : (5��)���� : " + price / 2 + " ���");
                    } else {
                        System.err.println("[�����]" + CurrentReadable_Time() + " : ���� : " + price + " ���");
                    }
                } else {
                    System.err.println("[�����]" + CurrentReadable_Time() + " : ���� : " + price + " ���");
                }

                c.sendPacket(MaplePacketCreator.confirmShopTransaction((byte) 0));
            }
        }
    }

    public void sell(MapleClient c, MapleInventoryType type, byte slot, short quantity) {
        if (quantity == 0xFFFF || quantity == 0) {
            quantity = 1;
        }

        IItem item = c.getPlayer().getInventory(type).getItem(slot);
        if (item == null) {
            return;
        }
        if (item.getExpiration() > 0) {
            c.getPlayer().dropMessage(1, "��ʱ���ߣ��޷����ۡ�");
            return;
        }
        if (GameConstants.isThrowingStar(item.getItemId()) || GameConstants.isBullet(item.getItemId())) {
            quantity = item.getQuantity();
        }
        if (quantity < 0) {
            AutobanManager.getInstance().addPoints(c, 1000, 0, "���� " + quantity + " " + item.getItemId() + " (" + type.name() + "/" + slot + ")");
            return;
        }
        short iQuant = item.getQuantity();
        if (iQuant == 0xFFFF) {
            iQuant = 1;
        }
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (ii.cantSell(item.getItemId())) {
            return;
        }
        MapleShopItem item2 = findById(item.getItemId());
        if (item2 != null) {
            final int �����Ǯ = GameConstants.isRechargable(item.getItemId()) ? item2.getPrice() : (item2.getPrice() * quantity);
            double ���� = ii.getPrice(item.getItemId());
            final int ���ۼ�Ǯ = (int) Math.max(Math.ceil(���� * quantity), 0);
            if (���ۼ�Ǯ > �����Ǯ) {
                c.getPlayer().dropMessage(1, "���ۼ۸��ڹ���ۣ��޷����ۡ�");
                return;
            }
        }
        if (quantity <= iQuant && iQuant > 0) {

            MapleInventoryManipulator.removeFromSlot(c, type, slot, quantity, false);
            double price;
            if (GameConstants.isThrowingStar(item.getItemId()) || GameConstants.isBullet(item.getItemId())) {
                price = ii.getWholePrice(item.getItemId()) / (double) ii.getSlotMax(c, item.getItemId());
            } else {
                price = ii.getPrice(item.getItemId());
            }

            final int recvMesos = (int) Math.max(Math.ceil(price * quantity), 0);

            if (price != -1.0 && recvMesos > 0) {
                if (recvMesos > 100 * 10000) {
                    c.getPlayer().gainMeso(100 * 10000, false);
                } else {
                    c.getPlayer().gainMeso(recvMesos, false);
                }
            }
            //System.err.println("[�����]" + CurrentReadable_Time() + " : �̵� : " + id + " NPC : " + getNpcId() + " ��ͼ : " + c.getPlayer().getMapId() + "");
            //System.err.println("[�����]" + CurrentReadable_Time() + " : ��� : " + c.getPlayer().getName() + "");
            //System.err.println("[�����]" + CurrentReadable_Time() + " : ���� : " + MapleItemInformationProvider.getInstance().getName(item.getItemId()) + " x " + quantity + " ��");
            //System.err.println("[�����]" + CurrentReadable_Time() + " : ��ȡ : " + recvMesos + " ���");
            c.sendPacket(MaplePacketCreator.confirmShopTransaction((byte) 0x8));
        }
    }

    public void recharge(final MapleClient c, final byte slot) {
        final IItem item = c.getPlayer().getInventory(MapleInventoryType.USE).getItem(slot);

        if (item == null || (!GameConstants.isThrowingStar(item.getItemId()) && !GameConstants.isBullet(item.getItemId()))) {
            return;
        }
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        short slotMax = ii.getSlotMax(c, item.getItemId());
        final int skill = GameConstants.getMasterySkill(c.getPlayer().getJob());

        if (skill != 0) {
            slotMax += c.getPlayer().getSkillLevel(SkillFactory.getSkill(skill)) * 10;
        }
        if (item.getQuantity() < slotMax) {
            final int price = (int) Math.round(ii.getPrice(item.getItemId()) * (slotMax - item.getQuantity()));
            if (c.getPlayer().getMeso() >= price) {
                item.setQuantity(slotMax);
                c.sendPacket(MaplePacketCreator.updateInventorySlot(MapleInventoryType.USE, (Item) item, false));
                c.getPlayer().gainMeso(-price, false, true, false);
                c.sendPacket(MaplePacketCreator.confirmShopTransaction((byte) 0x8));
            }
        }
    }

    protected MapleShopItem findById(int itemId) {
        for (MapleShopItem item : items) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        return null;
    }

    public static MapleShop createFromDB(int id, boolean isShopId) {
        MapleShop ret = null;
        int shopId;

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(isShopId ? "SELECT * FROM shops WHERE shopid = ?" : "SELECT * FROM shops WHERE npcid = ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                shopId = rs.getInt("shopid");
                ret = new MapleShop(shopId, rs.getInt("npcid"));
                rs.close();
                ps.close();
            } else {
                rs.close();
                ps.close();
                return null;
            }
            ps = con.prepareStatement("SELECT * FROM shopitems WHERE shopid = ? ORDER BY position ASC");
            ps.setInt(1, shopId);
            rs = ps.executeQuery();
            List<Integer> recharges = new ArrayList<Integer>(rechargeableItems);
            while (rs.next()) {
                if (GameConstants.isThrowingStar(rs.getInt("itemid")) || GameConstants.isBullet(rs.getInt("itemid"))) {
                    MapleShopItem starItem = new MapleShopItem((short) 1, rs.getInt("itemid"), rs.getInt("price"));
                    ret.addItem(starItem);
                    if (rechargeableItems.contains(starItem.getItemId())) {
                        recharges.remove(Integer.valueOf(starItem.getItemId()));
                    }
                } else {
                    ret.addItem(new MapleShopItem((short) 1000, rs.getInt("itemid"), rs.getInt("price")));
                }
            }
            for (Integer recharge : recharges) {
                ret.addItem(new MapleShopItem((short) 1000, recharge.intValue(), 0));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Could not load shop" + e);
        }
        return ret;
    }

    public int getNpcId() {
        return npcId;
    }

    public int getId() {
        return id;
    }
}
