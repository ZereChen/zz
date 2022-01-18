package server.shops;

import constants.GameConstants;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.ref.WeakReference;
import handling.world.World;
import client.inventory.IItem;
import client.inventory.ItemLoader;
import client.MapleCharacter;
import client.MapleClient;
import client.inventory.MapleInventoryType;
import database.DatabaseConnection;
import handling.channel.ChannelServer;
import java.awt.Point;
import java.sql.*;
import java.util.ArrayList;
import server.maps.AbstractMapleMapObject;
import server.maps.MapleMap;
import server.maps.MapleMapObjectType;
import tools.Pair;
import tools.packet.PlayerShopPacket;

public abstract class AbstractPlayerStore extends AbstractMapleMapObject implements IMaplePlayerShop {

    protected boolean open = false, available = false;
    protected String ownerName, des, pass;
    protected int ownerId, owneraccount, itemId, channel, map;
    protected AtomicInteger meso = new AtomicInteger(0);
    protected WeakReference<MapleCharacter> chrs[];
    protected List<String> visitors = new LinkedList<String>();
    protected List<BoughtItem> bought = new LinkedList<BoughtItem>();
    protected List<MaplePlayerShopItem> items = new LinkedList<MaplePlayerShopItem>();
    private  int packageid = 0;
    // private static final Logger log = Logger.getLogger(AbstractPlayerStore.class);
    
    public AbstractPlayerStore(String ownerName, int ownerId, int x, int y, int type, String desc, int capacity,int itemId,int channel,int accid) {
        this.setPosition(new Point(x, y));
        this.ownerName = ownerName;
        this.ownerId = ownerId;
        this.des = desc;
        this.pass = "";
        this.itemId = itemId;
        this.owneraccount = accid;
        chrs = new WeakReference[3];
        this.channel =  channel;
        for (int i = 0; i < chrs.length; i++) {
            chrs[i] = new WeakReference<>(null);
        }
    }

    public AbstractPlayerStore(MapleCharacter owner, int itemId, String desc, String pass, int slots) {
        this.setPosition(owner.getPosition());
        this.ownerName = owner.getName();
        this.ownerId = owner.getId();
        this.owneraccount = owner.getAccountID();
        this.itemId = itemId;
        this.des = desc;
        this.pass = pass;
        this.map = owner.getMapId();
        this.channel = owner.getClient().getChannel();
        chrs = new WeakReference[slots];
        for (int i = 0; i < chrs.length; i++) {
            chrs[i] = new WeakReference<MapleCharacter>(null);
        }
    }

    @Override
    public int getMaxSize() {
        return chrs.length + 1;
    }

    @Override
    public int getSize() {
        return getFreeSlot() == -1 ? getMaxSize() : getFreeSlot();
    }

    @Override
    public void broadcastToVisitors(byte[] packet) {
        broadcastToVisitors(packet, true);
    }

    public void broadcastToVisitors(byte[] packet, boolean owner) {
        for (WeakReference<MapleCharacter> chr : chrs) {
            if (chr != null && chr.get() != null) {
                chr.get().getClient().sendPacket(packet);
            }
        }
        if (getShopType() != IMaplePlayerShop.HIRED_MERCHANT && owner && getMCOwner() != null) {
            getMCOwner().getClient().sendPacket(packet);
        }
    }

    public void broadcastToVisitors(byte[] packet, int exception) {
        for (WeakReference<MapleCharacter> chr : chrs) {
            if (chr != null && chr.get() != null && getVisitorSlot(chr.get()) != exception) {
                chr.get().getClient().sendPacket(packet);
            }
        }
        if (getShopType() != IMaplePlayerShop.HIRED_MERCHANT && getMCOwner() != null && exception != ownerId) {
            getMCOwner().getClient().sendPacket(packet);
        }
    }

    @Override
    public int getMeso() {
        return meso.get();
    }

    @Override
    public void setMeso(int meso) {
        this.meso.set(meso);
    }

    @Override
    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public boolean isOpen() {
        return open;
    }

    public boolean saveItems() {
        if (getShopType() != IMaplePlayerShop.HIRED_MERCHANT) { //hired merch only
            return false;
        }
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM hiredmerch WHERE accountid = ? OR characterid = ?");
            ps.setInt(1, owneraccount);
            ps.setInt(2, ownerId);
            ps.executeUpdate();
            ps.close();
            ps = con.prepareStatement("INSERT INTO hiredmerch (characterid, accountid, Mesos, map, channel, time) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ownerId);
            ps.setInt(2, owneraccount);
            ps.setInt(3, meso.get());
            ps.setInt(4, map);
            ps.setInt(5, getChannel());
            ps.setLong(6, System.currentTimeMillis());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (!rs.next()) {
                rs.close();
                ps.close();
                System.out.println("[SaveItems] �����Ӷ�̵���Ϣ���� - 1");
                //  log.error("[SaveItems] �����Ӷ�̵���Ϣ���� - 1");
                throw new RuntimeException("�����Ӷ�̵���Ϣ����.");
            }
            setPackageid(rs.getInt(1));
            rs.close();
            ps.close();
            List<Pair<IItem, MapleInventoryType>> iters = new ArrayList<Pair<IItem, MapleInventoryType>>();
            for (MaplePlayerShopItem pItems : items) {
                if ((pItems.item == null) || (pItems.bundles <= 0) || ((pItems.item.getQuantity() <= 0) && (!GameConstants.isRechargable(pItems.item.getItemId())))) {
                    continue;
                }
                IItem item = pItems.item.copy();
                item.setQuantity((short) (item.getQuantity() * pItems.bundles));
                //item.setFlag((byte) (pItems.flag));
                iters.add(new Pair<IItem, MapleInventoryType>(item, GameConstants.getInventoryType(item.getItemId())));
            }
            // ItemLoader.HIRED_MERCHANT.saveItems(iters, this.ownerId);
            ItemLoader.HIRED_MERCHANT.saveHiredItems(iters, getPackageid(), owneraccount, ownerId);
            return true;
        } catch (Exception se) {
            System.out.println("[SaveItems] �����Ӷ�̵���Ϣ���� - 2 " + se);
            //  log.error("[SaveItems] �����Ӷ�̵���Ϣ���� - 2 " + se);
        }
        return false;
    }

    public MapleCharacter getVisitor(int num) {
        return chrs[num].get();
    }

    @Override
    public void update() {
        if (isAvailable()) {
            if (getShopType() == IMaplePlayerShop.HIRED_MERCHANT) {
                getMap().broadcastMessage(PlayerShopPacket.updateHiredMerchant((HiredMerchant) this));
            } else if (getMCOwner() != null) {
                getMap().broadcastMessage(PlayerShopPacket.sendPlayerShopBox(getMCOwner()));
            }
        }
    }

    @Override
    public void addVisitor(MapleCharacter visitor) {
        int i = getFreeSlot();
        if (i > 0) {
            if (getShopType() >= 3) {
                broadcastToVisitors(PlayerShopPacket.getMiniGameNewVisitor(visitor, i, (MapleMiniGame) this));
            } else {
                broadcastToVisitors(PlayerShopPacket.shopVisitorAdd(visitor, i));
            }
            chrs[i - 1] = new WeakReference<MapleCharacter>(visitor);
            if (!isOwner(visitor)) {
                visitors.add(visitor.getName());
            }
            if (i == 3) {
                update();
            }
        }
    }

    @Override
    public void removeVisitor(MapleCharacter visitor) {
        final byte slot = getVisitorSlot(visitor);
        boolean shouldUpdate = getFreeSlot() == -1;
        if (slot > 0) {
            broadcastToVisitors(PlayerShopPacket.shopVisitorLeave(slot), slot);
            chrs[slot - 1] = new WeakReference<MapleCharacter>(null);
            if (shouldUpdate) {
                update();
            }
        }
    }

    @Override
    public byte getVisitorSlot(MapleCharacter visitor) {
        for (byte i = 0; i < chrs.length; i++) {
            if (chrs[i] != null && chrs[i].get() != null && chrs[i].get().getId() == visitor.getId()) {
                return (byte) (i + 1);
            }
        }
        if (visitor.getId() == ownerId) { //can visit own store in merch, otherwise not.
            return 0;
        }
        return -1;
    }

    @Override
    public void removeAllVisitors(int error, int type) {
        for (int i = 0; i < chrs.length; i++) {
            MapleCharacter visitor = getVisitor(i);
            if (visitor != null) {
                if (type != -1) {
                    visitor.getClient().sendPacket(PlayerShopPacket.shopErrorMessage(error, type));
                }
                broadcastToVisitors(PlayerShopPacket.shopVisitorLeave(getVisitorSlot(visitor)), getVisitorSlot(visitor));
                visitor.setPlayerShop(null);
                chrs[i] = new WeakReference<MapleCharacter>(null);
            }
        }
        update();
    }

    @Override
    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public int getOwnerId() {
        return ownerId;
    }

    @Override
    public int getOwnerAccId() {
        return owneraccount;
    }

    @Override
    public String getDescription() {
        if (des == null) {
            return "";
        }
        return des;
    }

    @Override
    public List<Pair<Byte, MapleCharacter>> getVisitors() {
        List<Pair<Byte, MapleCharacter>> chrz = new LinkedList<Pair<Byte, MapleCharacter>>();
        for (byte i = 0; i < chrs.length; i++) { //include owner or no
            if (chrs[i] != null && chrs[i].get() != null) {
                chrz.add(new Pair<Byte, MapleCharacter>((byte) (i + 1), chrs[i].get()));
            }
        }
        return chrz;
    }

    @Override
    public List<MaplePlayerShopItem> getItems() {
        return items;
    }

    @Override
    public void addItem(MaplePlayerShopItem item) {
        //System.out.println("Adding item ... 2");
        items.add(item);
    }

    @Override
    public boolean removeItem(int item) {
        return false;
    }

    @Override
    public void removeFromSlot(int slot) {
        items.remove(slot);
    }

    @Override
    public byte getFreeSlot() {
        for (byte i = 0; i < chrs.length; i++) {
            if (chrs[i] == null || chrs[i].get() == null) {
                return (byte) (i + 1);
            }
        }
        return -1;
    }

    @Override
    public int getItemId() {
        return itemId;
    }

    @Override
    public boolean isOwner(MapleCharacter chr) {
        return chr.getId() == ownerId && chr.getName().equals(ownerName);
    }

    @Override
    public String getPassword() {
        if (pass == null) {
            return "";
        }
        return pass;
    }

    @Override
    public void sendDestroyData(MapleClient client) {
    }

    @Override
    public void sendSpawnData(MapleClient client) {
    }

    @Override
    public MapleMapObjectType getType() {
        return MapleMapObjectType.SHOP;
    }

    public MapleCharacter getMCOwner() {
        return getMap().getCharacterById(ownerId);
    }

    public MapleCharacter getMCOwnerWorld() {
        int ourChannel = World.Find.findChannel(ownerId);
        if (ourChannel <= 0) {
            return null;
        }
        return ChannelServer.getInstance(ourChannel).getPlayerStorage().getCharacterById(ownerId);
    }

    public MapleMap getMap() {
        return ChannelServer.getInstance(getChannel()).getMapFactory().getMap(map);
    }

    @Override
    public int getGameType() {
        if (getShopType() == IMaplePlayerShop.HIRED_MERCHANT) { //hiredmerch
            return 5;
        } else if (getShopType() == IMaplePlayerShop.PLAYER_SHOP) { //shop lol
            return 4;
        } else if (getShopType() == IMaplePlayerShop.OMOK) { //omok
            return 1;
        } else if (getShopType() == IMaplePlayerShop.MATCH_CARD) { //matchcard
            return 2;
        }
        return 0;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean b) {
        this.available = b;
    }

    @Override
    public List<BoughtItem> getBoughtItems() {
        return bought;
    }

    /**
     * @return the packageid
     */
    public int getPackageid() {
        return packageid;
    }

    /**
     * @param packageid the packageid to set
     */
    public void setPackageid(int packageid) {
        this.packageid = packageid;
    }

    /**
     * @return the channel
     */
    public int getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(int channel) {
        this.channel = channel;
    }

    public static final class BoughtItem {

        public int id;
        public int quantity;
        public int totalPrice;
        public String buyer;

        public BoughtItem(final int id, final int quantity, final int totalPrice, final String buyer) {
            this.id = id;
            this.quantity = quantity;
            this.totalPrice = totalPrice;
            this.buyer = buyer;
        }
    }
}
