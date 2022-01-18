/*
��Ӷ
����
��ҽ���

 */
package handling.channel.handler;

import java.util.Arrays;

import client.inventory.IItem;
import client.inventory.ItemFlag;
import constants.GameConstants;
import client.MapleClient;
import client.MapleCharacter;
import client.inventory.MapleInventoryType;
import database.DatabaseConnection;
import handling.world.MapleParty;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.MapleTrade;
import server.maps.FieldLimitType;
import server.shops.HiredMerchant;
import server.shops.IMaplePlayerShop;
import server.shops.MaplePlayerShop;
import server.shops.MaplePlayerShopItem;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import server.shops.MapleMiniGame;
import tools.MaplePacketCreator;
import tools.packet.PlayerShopPacket;
import tools.data.LittleEndianAccessor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerInteractionHandler {

    private static final byte CREATE = 0x00,///////��Ӷ
            INVITE_TRADE = 0x02,
            DENY_TRADE = 0x03,
            VISIT = 0x04,
            CHAT = 0x06,
            EXIT = 0x0A,
            OPEN = 0x0B,
            CASH_ITEM_INTER = 0x0D,//�ֽ���
            SET_ITEMS = 0x0E,
            SET_MESO = 0x0F,
            CONFIRM_TRADE = 0x10,
            TRADE_SOMETHING = 0x12,
            PLAYER_SHOP_ADD_ITEM = 0x14,
            BUY_ITEM_PLAYER_SHOP = 0x15,
            MERCHANT_EXIT = 0x1B, //is this also updated
            ADD_ITEM = 0x1F,
            BUY_ITEM_HIREDMERCHANT = 0x20,
            BUY_ITEM_STORE = 0x21,
            REMOVE_ITEM = 0x23,
            TAKE_ITEM_BACK = 0x24,//23
            MAINTANCE_OFF = 0x25, //This is mispelled...
            MAINTANCE_ORGANISE = 0x26,
            CLOSE_MERCHANT = 0x27,//26
            ADMIN_STORE_NAMECHANGE = 0x2B,
            VIEW_MERCHANT_VISITOR = 0x2C,
            VIEW_MERCHANT_BLACKLIST = 0x2D,
            MERCHANT_BLACKLIST_ADD = 0x2E,
            MERCHANT_BLACKLIST_REMOVE = 0x2F,
            REQUEST_TIE = 0x30,
            ANSWER_TIE = 0x31,
            GIVE_UP = 0x32,
            REQUEST_REDO = 0x35,
            ANSWER_REDO = 0x36,
            EXIT_AFTER_GAME = 0x37,
            CANCEL_EXIT = 0x38,
            READY = 0x39,
            UN_READY = 0x3A,
            EXPEL = 0x3B,
            START = 0x3C,
            SKIP = 0x3E,
            MOVE_OMOK = 0x3F,
            SELECT_CARD = 0x43;

    public static final void PlayerInteraction(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        //c.getPlayer().dropMessage(5, "" + slea.readByte());
        if (chr == null) {
            return;
        }
        final byte action = slea.readByte();
        switch (action) { // Mode
            /*
            �����������ս
             */
            case CREATE: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "����һ����־");
                }
                final byte createType = slea.readByte();

                if (createType == 3) { // trade
                    MapleTrade.startTrade(chr);
                } else if (createType == 1 || createType == 2 || createType == 4 || createType == 5) { // shop
                    if (createType == 4 && !chr.isAdmin()) { //not hired merch... blocked playershop
                        c.sendPacket(MaplePacketCreator.enableActions());
                        return;
                    }

                    if (chr.getMap().getMapObjectsInRange(chr.getPosition(), 20000, Arrays.asList(MapleMapObjectType.SHOP, MapleMapObjectType.HIRED_MERCHANT)).size() != 0) {
                        chr.dropMessage(1, "�㲻���������ｨ��һ���̵�.");
                        c.sendPacket(MaplePacketCreator.enableActions());
                        return;
                    } else if (createType == 1 || createType == 2) {
                        if (c.getPlayer().getMapId() != 100000000) {
                            chr.dropMessage(1, "С��Ϸֻ�������ִ�ʹ�á�");
                            c.sendPacket(MaplePacketCreator.enableActions());
                            return;
                        }
                        if (FieldLimitType.Minigames.check(chr.getMap().getFieldLimit())) {
                            chr.dropMessage(1, "�㲻����������ʹ�õ�������Ϸ��");
                            c.sendPacket(MaplePacketCreator.enableActions());
                            return;
                        }
                    }
                    /*if (createType == 1) {
                        sendMsgToQQGroup("" + c.getPlayer().getName() + " �� ���ִ� ������С��Ϸ(������)��ս��");
                        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, " : " + c.getPlayer().getName() + " �� ���ִ� ������С��Ϸ(������)��ս��").getBytes());
                    } else if (createType == 2) {
                        sendMsgToQQGroup("" + c.getPlayer().getName() + " �� ���ִ� ������С��Ϸ(�������)��ս��");
                        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, " : " + c.getPlayer().getName() + " �� ���ִ� ������С��Ϸ(�������)��ս��").getBytes());
                    }*/
                    final String desc = slea.readMapleAsciiString();
                    String pass = "";
                    if (slea.readByte() > 0 && (createType == 1 || createType == 2)) {
                        pass = slea.readMapleAsciiString();
                    }
                    if (createType == 1 || createType == 2) {
                        final int piece = slea.readByte();
                        final int itemId = createType == 1 ? (4080000 + piece) : 4080100;
                        if (!chr.haveItem(itemId) || (c.getPlayer().getMapId() >= 910000001 && c.getPlayer().getMapId() <= 910000022)) {
                            return;
                        }
                        MapleMiniGame game = new MapleMiniGame(chr, itemId, desc, pass, createType); //itemid
                        game.setPieceType(piece);
                        chr.setPlayerShop(game);
                        game.setAvailable(true);
                        game.setOpen(true);
                        game.send(c);
                        chr.getMap().addMapObject(game);
                        game.update();
                    } else {
                        IItem shop = c.getPlayer().getInventory(MapleInventoryType.CASH).getItem((byte) slea.readShort());
                        if (shop == null || shop.getQuantity() <= 0 || shop.getItemId() != slea.readInt() || c.getPlayer().getMapId() < 910000000 || c.getPlayer().getMapId() > 910000022) {
                            return;
                        }
                        if (createType == 4) {
                            MaplePlayerShop mps = new MaplePlayerShop(chr, shop.getItemId(), desc);
                            chr.setPlayerShop(mps);
                            chr.getMap().addMapObject(mps);
                            c.sendPacket(PlayerShopPacket.getPlayerStore(chr, true));
                        } else {
                            final HiredMerchant merch = new HiredMerchant(chr, shop.getItemId(), desc);
                            chr.setPlayerShop(merch);
                            chr.getMap().addMapObject(merch);
                            c.sendPacket(PlayerShopPacket.getHiredMerch(chr, merch, true));
                        }
                    }
                }
                break;
            }
            case INVITE_TRADE: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "INVITE_TRADE");
                }
                MapleTrade.inviteTrade(chr, chr.getMap().getCharacterById(slea.readInt()));
                break;
            }
            case DENY_TRADE: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "DENY_TRADE");
                }
                MapleTrade.declineTrade(chr);
                break;
            }
            /**
             * <�򿪹�Ӷ>
             */
            case CASH_ITEM_INTER: {
                byte ���� = slea.readByte();
                byte �ֽ��� = slea.readByte();
                if (���� == 11 && �ֽ��� == 5) {
                    c.getPlayer().dropMessage(1, "���ȷ���һ�������ֽ���Ʒ�Ķ�������\r\n�����̵������̵�����ֽ���Ʒ��");
                    return;
                }
                int δ֪���� = slea.readInt();
                int obid = slea.readInt();
                MapleCharacter otherChar = c.getPlayer().getMap().getCharacterById(obid);
                MapleMapObject ob = chr.getMap().getMapObject(obid, MapleMapObjectType.HIRED_MERCHANT);
                if (�ֽ��� == 6 && ���� == 4 && (c.getPlayer().getTrade() != null) && (c.getPlayer().getTrade().getPartner() != null)) {
                    // byte δ֪ = slea.readByte();
                    MapleTrade.visit�ֽ���(chr, chr.getTrade().getPartner().getChr());
                    try {
                        c.getPlayer().dropMessage(6, "��� " + otherChar.getName() + "  �����ֽ�������!");
                    } catch (Exception e) {
                    }
                } else if (�ֽ��� == 6 && ���� != 4) {
                    MapleTrade.start�ֽ���(chr);
                    MapleTrade.invite�ֽ���(chr, otherChar);
                    c.getPlayer().dropMessage(6, "����� " + otherChar.getName() + "  �����ֽ�������!");

                } else if (chr.getMap() != null) {
                    if (ob == null) {
                        ob = chr.getMap().getMapObject(obid, MapleMapObjectType.SHOP);
                    }

                    if (ob instanceof IMaplePlayerShop && chr.getPlayerShop() == null) {
                        final IMaplePlayerShop ips = (IMaplePlayerShop) ob;

                        if (ob instanceof HiredMerchant) {
                            final HiredMerchant merchant = (HiredMerchant) ips;
                            if (merchant.isOwner(chr)) {
                                merchant.setOpen(false);
                                merchant.broadcastToVisitors(PlayerShopPacket.shopErrorMessage(13, 1), false);
                                merchant.removeAllVisitors((byte) 16, (byte) 0);
                                chr.setPlayerShop(ips);
                                c.sendPacket(PlayerShopPacket.getHiredMerch(chr, merchant, false));
                            } else if (!merchant.isOpen() || !merchant.isAvailable()) {
                                chr.dropMessage(1, "�������������̵���Ʒ\r\n���Ժ��ٶȹ��٣�");
                            } else if (ips.getFreeSlot() == -1) {
                                chr.dropMessage(1, "�̵������Ѿ�����,���Ժ��ٽ���");
                            } else if (merchant.isInBlackList(chr.getName())) {
                                chr.dropMessage(1, "�㱻����̵�����������,���Բ��ܽ���");
                            } else {
                                chr.setPlayerShop(ips);
                                merchant.addVisitor(chr);
                                c.sendPacket(PlayerShopPacket.getHiredMerch(chr, merchant, false));
                            }
                        } else if (ips instanceof MaplePlayerShop && ((MaplePlayerShop) ips).isBanned(chr.getName())) {
                            chr.dropMessage(1, "�㱻����̵�����������,���Բ��ܽ���.");
                            return;
                        } else if (ips.getFreeSlot() < 0 || ips.getVisitorSlot(chr) > -1 || !ips.isOpen() || !ips.isAvailable()) {
                            c.sendPacket(PlayerShopPacket.getMiniGameFull());
                        } else {
                            if (slea.available() > 0 && slea.readByte() > 0) { //a password has been entered
                                String pass = slea.readMapleAsciiString();
                                if (!pass.equals(ips.getPassword())) {
                                    c.getPlayer().dropMessage(1, "��������������.���������һ��.");
                                    return;
                                }
                            } else if (ips.getPassword().length() > 0) {
                                c.getPlayer().dropMessage(1, "��������������.���������һ��.");
                                return;
                            }
                            chr.setPlayerShop(ips);
                            ips.addVisitor(chr);
                            if (ips instanceof MapleMiniGame) {
                                ((MapleMiniGame) ips).send(c);
                            } else {
                                c.sendPacket(PlayerShopPacket.getPlayerStore(chr, false));
                            }
                        }
                    }
                }
                break;
            }
            /**
             * <�����Ӷ>
             */
            case VISIT: {
                /*
                �����������ս
                 */
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "����С��Ϸ");
                }
                if (chr.getTrade() != null && chr.getTrade().getPartner() != null) {
                    MapleTrade.visitTrade(chr, chr.getTrade().getPartner().getChr());
                } else if (chr.getMap() != null) {
                    final int obid = slea.readInt();
                    MapleMapObject ob = chr.getMap().getMapObject(obid, MapleMapObjectType.HIRED_MERCHANT);
                    if (ob == null) {
                        ob = chr.getMap().getMapObject(obid, MapleMapObjectType.SHOP);
                    }

                    if (ob instanceof IMaplePlayerShop && chr.getPlayerShop() == null) {
                        final IMaplePlayerShop ips = (IMaplePlayerShop) ob;

                        if (ob instanceof HiredMerchant) {
                            final HiredMerchant merchant = (HiredMerchant) ips;
                            if (merchant.isOwner(chr)) {
                                merchant.setOpen(false);
                                merchant.removeAllVisitors((byte) 16, (byte) 0);
                                chr.setPlayerShop(ips);
                                c.sendPacket(PlayerShopPacket.getHiredMerch(chr, merchant, false));
                            } else if (!merchant.isOpen() || !merchant.isAvailable()) {
                                chr.dropMessage(1, "����̵��������������û���ٷ�������");
                            } else if (ips.getFreeSlot() == -1) {
                                chr.dropMessage(1, "�̵������Ѿ����ˣ����Ժ��ڽ���");
                            } else if (merchant.isInBlackList(chr.getName())) {
                                chr.dropMessage(1, "���Ѿ�������̵��������������Բ��ܽ���");
                            } else {
                                chr.setPlayerShop(ips);
                                merchant.addVisitor(chr);
                                c.sendPacket(PlayerShopPacket.getHiredMerch(chr, merchant, false));
                            }
                        } else if (ips instanceof MaplePlayerShop && ((MaplePlayerShop) ips).isBanned(chr.getName())) {
                            chr.dropMessage(1, "�㱻����̵�����������,���Բ��ܽ���.");
                            return;
                        } else if (ips.getFreeSlot() < 0 || ips.getVisitorSlot(chr) > -1 || !ips.isOpen() || !ips.isAvailable()) {
                            c.sendPacket(PlayerShopPacket.getMiniGameFull());
                        } else {
                            if (slea.available() > 0 && slea.readByte() > 0) { //a password has been entered
                                String pass = slea.readMapleAsciiString();
                                if (!pass.equals(ips.getPassword())) {
                                    c.getPlayer().dropMessage(1, "��������������.���������һ��");
                                    return;
                                }
                            } else if (ips.getPassword().length() > 0) {
                                c.getPlayer().dropMessage(1, "��������������.���������һ��.");
                                return;
                            }
                            chr.setPlayerShop(ips);
                            ips.addVisitor(chr);
                            if (ips instanceof MapleMiniGame) {
                                ((MapleMiniGame) ips).send(c);
                            } else {
                                c.sendPacket(PlayerShopPacket.getPlayerStore(chr, false));
                            }
                        }
                    }
                }
                break;
            }
            case CHAT: {
                /*
                ������Ի�
                 */
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "С��Ϸ�Ի�");
                }
//                slea.readInt();
                if (chr.getTrade() != null) {
                    chr.getTrade().chat(slea.readMapleAsciiString());
                } else if (chr.getPlayerShop() != null) {
                    final IMaplePlayerShop ips = chr.getPlayerShop();
                    ips.broadcastToVisitors(PlayerShopPacket.shopChat(chr.getName() + " : " + slea.readMapleAsciiString(), ips.getVisitorSlot(chr)));
                }
                break;
            }
            /*
            �ر������壬�رչ�Ӷ
             */
            case EXIT: {
                if (chr.getTrade() != null) {
                    MapleTrade.cancelTrade(chr.getTrade(), chr.getClient());
                } else {
                    final IMaplePlayerShop ips = chr.getPlayerShop();
                    if (ips == null) {
                        return;
                    }
                    if (!ips.isAvailable() || (ips.isOwner(chr) && ips.getShopType() != 1)) {
                        ips.closeShop(false, ips.isAvailable());
                    } else {
                        ips.removeVisitor(chr);
                    }
                    chr.setPlayerShop(null);
                    c.sendPacket(MaplePacketCreator.enableActions());
                }
                break;
            }
            case OPEN: {
                /*
                ����������,������Ӷ
                 */
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "����С��Ϸ");
                }
                final IMaplePlayerShop shop = chr.getPlayerShop();
                if (shop != null && shop.isOwner(chr) && shop.getShopType() < 3) {
                    if (chr.getMap().allowPersonalShop()) {
                        if (c.getChannelServer().isShutdown()) {
                            chr.dropMessage(1, "���������̵�.");
                            c.sendPacket(MaplePacketCreator.enableActions());
                            shop.closeShop(shop.getShopType() == 1, false);
                            return;
                        }
                        if (shop.getShopType() == 1) {
                            final HiredMerchant merchant = (HiredMerchant) shop;
                            merchant.setStoreid(c.getChannelServer().addMerchant(merchant));
                            merchant.setOpen(true);
                            merchant.setAvailable(true);
                            chr.getMap().broadcastMessage(PlayerShopPacket.spawnHiredMerchant(merchant));
                            chr.setPlayerShop(null);
                            chr.setLastHM(System.currentTimeMillis());
                        } else if (shop.getShopType() == 2) {
                            shop.setOpen(true);
                            shop.setAvailable(true);
                            shop.update();
                        }
                    } else {
                        c.getSession().close();
                    }
                }

                break;
            }
            case SET_ITEMS: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "SET_ITEMS");
                }
                final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                final MapleInventoryType ivType = MapleInventoryType.getByType(slea.readByte());
                final IItem item = chr.getInventory(ivType).getItem((byte) slea.readShort());
                final short quantity = slea.readShort();
                final byte targetSlot = slea.readByte();
                if (chr.getTrade() != null && item != null) {
                    if ((quantity <= item.getQuantity() && quantity >= 0) || GameConstants.isThrowingStar(item.getItemId()) || GameConstants.isBullet(item.getItemId())) {
                        chr.getTrade().setItems(c, item, targetSlot, quantity);
                    }
                }
                break;
            }
            case SET_MESO: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "SET_MESO");
                }
                final MapleTrade trade = chr.getTrade();
                if (trade != null) {
                    trade.setMeso(slea.readInt());
                }
                break;
            }
            case CONFIRM_TRADE: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "CONFIRM_TRADE");
                }
                if (chr.getTrade() != null) {
                    MapleTrade.completeTrade(chr);
                }
                break;
            }
            case MERCHANT_EXIT: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "MERCHANT_EXIT");
                }
                break;
            }

            case BUY_ITEM_PLAYER_SHOP:
            case BUY_ITEM_STORE:
            case BUY_ITEM_HIREDMERCHANT: { // Buy and Merchant buy
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "BUY_ITEM_PLAYER_SHOP");
                }
                if (chr.getTrade() != null) {
                    MapleTrade.completeTrade(chr);
                    break;
                }
                final int item = slea.readByte();
                final short quantity = slea.readShort();
                //slea.skip(4);
                final IMaplePlayerShop shop = chr.getPlayerShop();

                if (shop == null || shop.isOwner(chr) || shop instanceof MapleMiniGame || item >= shop.getItems().size()) {
                    return;
                }
                final MaplePlayerShopItem tobuy = shop.getItems().get(item);
                if (tobuy == null) {
                    return;
                }
                long check = tobuy.bundles * quantity;
                long check2 = tobuy.price * quantity;
                long check3 = tobuy.item.getQuantity() * quantity;
                if (check <= 0 || check2 > 2147483647 || check2 <= 0 || check3 > 32767 || check3 < 0) { //This is the better way to check.
                    return;
                }
                if (tobuy.bundles < quantity || (tobuy.bundles % quantity != 0 && GameConstants.isEquip(tobuy.item.getItemId())) // Buying
                        || chr.getMeso() - (check2) < 0 || chr.getMeso() - (check2) > 2147483647 || shop.getMeso() + (check2) < 0 || shop.getMeso() + (check2) > 2147483647) {
                    return;
                }
                if (quantity >= 50 && tobuy.item.getItemId() == 2340000) {
                    //    c.setMonitored(true); //hack check
                }

                shop.buy(c, item, quantity);
                shop.broadcastToVisitors(PlayerShopPacket.shopItemUpdate(shop));
                break;
            }
            /**
             * <��Ӷ�ϼ���Ʒ>
             */
            case PLAYER_SHOP_ADD_ITEM:
            case ADD_ITEM: {
                if (c.getPlayer().getLastHM() + 1 * 1000 > System.currentTimeMillis()) {
                    c.getPlayer().dropMessage(1, "��������Ƶ�������� 1 ���ٲ�����");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    c.getPlayer().setConversation(0);
                    return;
                }
                final MapleInventoryType type = MapleInventoryType.getByType(slea.readByte());
                final byte slot = (byte) slea.readShort();
                final short bundles = slea.readShort(); // How many in a bundle
                final short perBundle = slea.readShort(); // Price per bundle
                //����
                final int price = slea.readInt();

                if (price <= 0 || bundles <= 0 || perBundle <= 0) {
                    return;
                }
                final IMaplePlayerShop shop = chr.getPlayerShop();

                if (shop == null || !shop.isOwner(chr) || shop instanceof MapleMiniGame) {
                    return;
                }
                final IItem ivItem = chr.getInventory(type).getItem(slot);
                final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                if (ivItem != null) {
                    long check = bundles * perBundle;
                    if (check > 32767 || check <= 0) { //This is the better way to check.
                        return;
                    }
                    final short bundles_perbundle = (short) (bundles * perBundle);
                    if (ivItem.getQuantity() >= bundles_perbundle) {
                        final byte flag = ivItem.getFlag();
                        if (ItemFlag.UNTRADEABLE.check(flag) || ItemFlag.LOCK.check(flag)) {
                            c.sendPacket(MaplePacketCreator.enableActions());
                            return;
                        }
                        if (ii.isDropRestricted(ivItem.getItemId()) || ii.isAccountShared(ivItem.getItemId())) {
                            if (!(ItemFlag.KARMA_EQ.check(flag) || ItemFlag.KARMA_USE.check(flag))) {
                                c.sendPacket(MaplePacketCreator.enableActions());
                                return;
                            }
                        }
                        if (bundles_perbundle >= 50 && GameConstants.isUpgradeScroll(ivItem.getItemId())) {
                            c.setMonitored(true);
                        }
                        if (GameConstants.isThrowingStar(ivItem.getItemId()) || GameConstants.isBullet(ivItem.getItemId())) {
                            MapleInventoryManipulator.removeFromSlot(c, type, slot, ivItem.getQuantity(), true);
                            final IItem sellItem = ivItem.copy();
                            sellItem.setPrice(price);
                            shop.addItem(new MaplePlayerShopItem(sellItem, (short) 1, price, sellItem.getFlag()));
                        } else {
                            MapleInventoryManipulator.removeFromSlot(c, type, slot, bundles_perbundle, true);
                            final IItem sellItem = ivItem.copy();
                            sellItem.setQuantity(perBundle);
                            sellItem.setPrice(price);
                            shop.addItem(new MaplePlayerShopItem(sellItem, bundles, price, sellItem.getFlag()));
                            chr.���ߴ浵();
                            /*if (ivItem.getItemId() < 2000000) {
                                IEquip zev = (IEquip) ivItem;
                                д�����(chr.getId(), ivItem.getItemId(), zev.getUpgradeSlots(), zev.getLevel(), zev.getStr(), zev.getDex(), zev.getInt(), zev.getLuk(), zev.getHp(), zev.getMp(), zev.getWatk(), zev.getMatk(), zev.getWdef(), zev.getMdef(), zev.getAcc(), zev.getAvoid(), zev.getHands(), zev.getSpeed(), zev.getJump(), zev.getViciousHammer(), zev.getItemEXP(), zev.getDurability(), zev.getEnhance(), zev.getPotential1(), zev.getPotential2(), zev.getPotential3(), zev.getHpR(), zev.getMpR(), zev.getEquipLevel(), ivItem.getDaKongFuMo());//
                            } else {
                                д�����(chr.getId(), ivItem.getItemId(), bundles, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "");//
                            }*/
                            chr.setLastHM(System.currentTimeMillis());
                        }
                        c.sendPacket(PlayerShopPacket.shopItemUpdate(shop));
                    }
                }
                break;
            }
            /**
             * <��Ӷ�¼���Ʒ>
             */
            case TAKE_ITEM_BACK:
            case REMOVE_ITEM: {
                if (c.getPlayer().getLastHM() + 1 * 1000 > System.currentTimeMillis()) {
                    c.getPlayer().dropMessage(1, "��������Ƶ�������� 1 ���ٲ�����");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    c.getPlayer().setConversation(0);
                    return;
                }
                int slot = slea.readShort(); //0
                final IMaplePlayerShop shop = chr.getPlayerShop();

                if (shop == null || !shop.isOwner(chr) || shop instanceof MapleMiniGame || shop.getItems().size() <= 0 || shop.getItems().size() <= slot || slot < 0) {
                    return;
                }
                final MaplePlayerShopItem item = shop.getItems().get(slot);
                if (item != null) {
                    if (item.bundles > 0) {
                        IItem item_get = item.item.copy();
                        long check = item.bundles * item.item.getQuantity();
                        if (check <= 0 || check > 32767) {
                            return;
                        }
                        item_get.setQuantity((short) check);
                        if (item_get.getQuantity() >= 50 && GameConstants.isUpgradeScroll(item.item.getItemId())) {
                            c.setMonitored(true); //hack check
                        }
                        if (MapleInventoryManipulator.checkSpace(c, item_get.getItemId(), item_get.getQuantity(), item_get.getOwner())) {
                            MapleInventoryManipulator.addFromDrop(c, item_get, false);
                            item.bundles = 0;
                            shop.removeFromSlot(slot);
                            chr.���ߴ浵();
                            /*if (item.item.getItemId() <= 2000000) {
                                IEquip zev = (IEquip) item_get;
                                ɾ������(chr.getId(), item.item.getItemId(), zev.getUpgradeSlots(), zev.getLevel(), zev.getStr(), zev.getDex(), zev.getInt(), zev.getLuk(), zev.getHp(), zev.getMp(), zev.getWatk(), zev.getMatk(), zev.getWdef(), zev.getMdef(), zev.getAcc(), zev.getAvoid(), zev.getHands(), zev.getSpeed(), zev.getJump(), zev.getViciousHammer(), zev.getItemEXP(), zev.getDurability(), zev.getEnhance(), zev.getPotential1(), zev.getPotential2(), zev.getPotential3(), zev.getHpR(), zev.getMpR(), zev.getEquipLevel(), zev.getDaKongFuMo());
                            } else {
                                ɾ������(chr.getId(), item.item.getItemId(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, (int) check, 0, 0, 0, "");//
                            }*/
                            chr.setLastHM(System.currentTimeMillis());
                        }
                    }
                }
                c.sendPacket(PlayerShopPacket.shopItemUpdate(shop));
                break;
            }
            /**
             * <�뿪��Ӷ>
             */
            case MAINTANCE_OFF: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "MAINTANCE_OFF");
                }
                final IMaplePlayerShop shop = chr.getPlayerShop();
                if (shop != null && shop instanceof HiredMerchant && shop.isOwner(chr)) {
                    //boolean save = false;
                    shop.setOpen(true);
                    chr.setPlayerShop(null);
                    //shop.closeShop(save, true);
                }
                break;
            }
            case MAINTANCE_ORGANISE: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "MAINTANCE_ORGANISE");
                }
                final IMaplePlayerShop imps = chr.getPlayerShop();
                if (imps != null && imps.isOwner(chr) && !(imps instanceof MapleMiniGame)) {
                    for (int i = 0; i < imps.getItems().size(); i++) {
                        if (imps.getItems().get(i).bundles == 0) {
                            imps.getItems().remove(i);
                        }
                    }
                    if (chr.getMeso() + imps.getMeso() < 0) {
                        c.sendPacket(PlayerShopPacket.shopItemUpdate(imps));
                    } else {
                        chr.gainMeso(imps.getMeso(), false);
                        imps.setMeso(0);

                        c.sendPacket(PlayerShopPacket.shopItemUpdate(imps));
                    }
                } else {
                    c.sendPacket(MaplePacketCreator.enableActions());
                }
                break;
            }
            /**
             * <�رչ�Ӷ����>
             */
            case CLOSE_MERCHANT: {
                c.getPlayer().dropMessage(1, "��Ӷ���޷��رյģ���Ҫ��ص��ߣ�����ֱ���ϻر���������۵��ߵĽ�ң������´η������������ӵ���Ӷ�ݵ㡣");
                /*final IMaplePlayerShop merchant = chr.getPlayerShop();
                if (merchant != null && merchant.getShopType() == 1 && merchant.isOwner(chr) && merchant.isAvailable()) {
                    merchant.removeAllVisitors(-1, -1);
                    merchant.closeShop(true, true);
                    chr.setPlayerShop(null);
                    c.getPlayer().dropMessage(1, "�����г���ع�Ӷ��Ʒ");
                }*/
                break;
            }
            case TRADE_SOMETHING:
            case ADMIN_STORE_NAMECHANGE: { // Changing store name, only Admin
                // 01 00 00 00
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "ADMIN_STORE_NAMECHANGE");
                }
                break;
            }
            case VIEW_MERCHANT_VISITOR: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "VIEW_MERCHANT_VISITOR");
                }
                final IMaplePlayerShop merchant = chr.getPlayerShop();
                if (merchant != null && merchant.getShopType() == 1 && merchant.isOwner(chr)) {
                    ((HiredMerchant) merchant).sendVisitor(c);
                }
                break;
            }
            case VIEW_MERCHANT_BLACKLIST: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "VIEW_MERCHANT_BLACKLIST");
                }
                final IMaplePlayerShop merchant = chr.getPlayerShop();
                if (merchant != null && merchant.getShopType() == 1 && merchant.isOwner(chr)) {
                    ((HiredMerchant) merchant).sendBlackList(c);
                }
                break;
            }
            case MERCHANT_BLACKLIST_ADD: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "MERCHANT_BLACKLIST_ADD");
                }
                final IMaplePlayerShop merchant = chr.getPlayerShop();
                if (merchant != null && merchant.getShopType() == 1 && merchant.isOwner(chr)) {
                    ((HiredMerchant) merchant).addBlackList(slea.readMapleAsciiString());
                }
                break;
            }
            case MERCHANT_BLACKLIST_REMOVE: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "MERCHANT_BLACKLIST_REMOVE");
                }
                final IMaplePlayerShop merchant = chr.getPlayerShop();
                if (merchant != null && merchant.getShopType() == 1 && merchant.isOwner(chr)) {
                    ((HiredMerchant) merchant).removeBlackList(slea.readMapleAsciiString());
                }
                break;
            }
            case GIVE_UP: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "GIVE_UP");
                }
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null && ips instanceof MapleMiniGame) {
                    MapleMiniGame game = (MapleMiniGame) ips;
                    if (game.isOpen()) {
                        break;
                    }
                    game.broadcastToVisitors(PlayerShopPacket.getMiniGameResult(game, 0, game.getVisitorSlot(chr)));
                    game.nextLoser();
                    game.setOpen(true);
                    game.update();
                    game.checkExitAfterGame();
                }
                break;
            }
            case EXPEL: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "EXPEL");
                }
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null && ips instanceof MapleMiniGame) {
                    if (!((MapleMiniGame) ips).isOpen()) {
                        break;
                    }
                    ips.removeAllVisitors(3, 1); //no msg
                }
                break;
            }
            case READY:
            case UN_READY: {
                /*
                ������׼��
                 */
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "׼����С��Ϸ");
                }
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null && ips instanceof MapleMiniGame) {
                    MapleMiniGame game = (MapleMiniGame) ips;
                    if (!game.isOwner(chr) && game.isOpen()) {
                        game.setReady(game.getVisitorSlot(chr));
                        game.broadcastToVisitors(PlayerShopPacket.getMiniGameReady(game.isReady(game.getVisitorSlot(chr))));
                    }
                }
                break;
            }
            case START: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "��ʼ��С��Ϸ");
                }
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null && ips instanceof MapleMiniGame) {
                    MapleMiniGame game = (MapleMiniGame) ips;
                    if (game.isOwner(chr) && game.isOpen()) {
                        for (int i = 1; i < ips.getSize(); i++) {
                            if (!game.isReady(i)) {
                                return;
                            }
                        }
                        game.setGameType();
                        game.shuffleList();
                        if (game.getGameType() == 1) {
                            game.broadcastToVisitors(PlayerShopPacket.getMiniGameStart(game.getLoser()));
                        } else {
                            game.broadcastToVisitors(PlayerShopPacket.getMatchCardStart(game, game.getLoser()));
                        }
                        game.setOpen(false);
                        game.update();
                    }
                }
                break;
            }
            case REQUEST_TIE: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "����ƽ������");
                }
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null && ips instanceof MapleMiniGame) {
                    MapleMiniGame game = (MapleMiniGame) ips;
                    if (game.isOpen()) {
                        break;
                    }
                    if (game.isOwner(chr)) {
                        game.broadcastToVisitors(PlayerShopPacket.getMiniGameRequestTie(), false);
                    } else {
                        game.getMCOwner().getClient().sendPacket(PlayerShopPacket.getMiniGameRequestTie());
                    }
                    game.setRequestedTie(game.getVisitorSlot(chr));
                }
                break;
            }
            case ANSWER_TIE: {
                /*
                ����ƽ������
                 */
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "ANSWER_TIE");
                }
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null && ips instanceof MapleMiniGame) {
                    MapleMiniGame game = (MapleMiniGame) ips;
                    if (game.isOpen()) {
                        break;
                    }
                    if (game.getRequestedTie() > -1 && game.getRequestedTie() != game.getVisitorSlot(chr)) {
                        if (slea.readByte() > 0) {
                            game.broadcastToVisitors(PlayerShopPacket.getMiniGameResult(game, 1, game.getRequestedTie()));
                            game.nextLoser();
                            game.setOpen(true);
                            game.update();
                            game.checkExitAfterGame();
                        } else {
                            game.broadcastToVisitors(PlayerShopPacket.getMiniGameDenyTie());
                        }
                        game.setRequestedTie(-1);
                    }
                }
                break;
            }

            case REQUEST_REDO: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "REQUEST_REDO");
                }
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null && ips instanceof MapleMiniGame) {
                    MapleMiniGame game = (MapleMiniGame) ips;
                    if (game.isOpen()) {
                        break;
                    }
                    if (game.isOwner(chr)) {
                        game.broadcastToVisitors(PlayerShopPacket.getMiniGameRequestREDO(), false);
                    } else {
                        game.getMCOwner().getClient().sendPacket(PlayerShopPacket.getMiniGameRequestREDO());
                    }
                    game.setRequestedTie(game.getVisitorSlot(chr));
                }
                break;
            }
            case ANSWER_REDO: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "ANSWER_REDO");
                }
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null && ips instanceof MapleMiniGame) {
                    MapleMiniGame game = (MapleMiniGame) ips;
                    if (game.isOpen()) {
                        break;
                    }
                    //     if (game.getRequestedTie() > -1 && game.getRequestedTie() != game.getVisitorSlot(chr)) {
                    if (slea.readByte() > 0) {
                        ips.broadcastToVisitors(PlayerShopPacket.getMiniGameSkip1(ips.getVisitorSlot(chr)));
                        game.nextLoser();
                    } else {
                        game.broadcastToVisitors(PlayerShopPacket.getMiniGameDenyTie());
                    }
                    game.setRequestedTie(-1);
                }
                //}
                break;
            }
            case SKIP: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "С��Ϸ����ʱ��");
                }
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null && ips instanceof MapleMiniGame) {
                    MapleMiniGame game = (MapleMiniGame) ips;
                    if (game.isOpen()) {
                        break;
                    }
                    /*
                     * if (game.getLoser() != ips.getVisitorSlot(chr)) {
                     * ips.broadcastToVisitors(PlayerShopPacket.shopChat("���^�����ɱ����^
                     * " + chr.getName() + ". ʧ����: " + game.getLoser() + " �[��: "
                     * + ips.getVisitorSlot(chr), ips.getVisitorSlot(chr)));
                     * return; }
                     */
                    ips.broadcastToVisitors(PlayerShopPacket.getMiniGameSkip(ips.getVisitorSlot(chr)));
                    game.nextLoser();
                }
                break;
            }
            case MOVE_OMOK: {
                /*
                ����
                 */
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "С��Ϸ������/����");
                }
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null && ips instanceof MapleMiniGame) {
                    MapleMiniGame game = (MapleMiniGame) ips;
                    if (game.isOpen()) {
                        break;
                    }
                    /*
                     * if (game.getLoser() != game.getVisitorSlot(chr)) {
                     * game.broadcastToVisitors(PlayerShopPacket.shopChat("���ܷ���ͨ�^
                     * " + chr.getName() + ". ʧ����: " + game.getLoser() + " �[��: "
                     * + game.getVisitorSlot(chr), game.getVisitorSlot(chr)));
                     * return; }
                     */
                    game.setPiece(slea.readInt(), slea.readInt(), slea.readByte(), chr);
                }
                break;
            }
            case SELECT_CARD: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "SELECT_CARD");
                }
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null && ips instanceof MapleMiniGame) {
                    MapleMiniGame game = (MapleMiniGame) ips;
                    if (game.isOpen()) {
                        break;
                    }

                    if (slea.readByte() != game.getTurn()) {
                        game.broadcastToVisitors(PlayerShopPacket.shopChat("���ܷ���ͨ�� " + chr.getName() + ". ʧ����: " + game.getLoser() + " �ο�: " + game.getVisitorSlot(chr) + " �Ƿ����: " + game.getTurn(), game.getVisitorSlot(chr)));
                        return;
                    }
                    final int slot = slea.readByte();
                    final int turn = game.getTurn();
                    final int fs = game.getFirstSlot();
                    if (turn == 1) {
                        game.setFirstSlot(slot);
                        if (game.isOwner(chr)) {
                            game.broadcastToVisitors(PlayerShopPacket.getMatchCardSelect(turn, slot, fs, turn), false);
                        } else {
                            game.getMCOwner().getClient().sendPacket(PlayerShopPacket.getMatchCardSelect(turn, slot, fs, turn));
                        }
                        game.setTurn(0); //2nd turn nao
                        return;
                    } else if (fs > 0 && game.getCardId(fs + 1) == game.getCardId(slot + 1)) {
                        game.broadcastToVisitors(PlayerShopPacket.getMatchCardSelect(turn, slot, fs, game.isOwner(chr) ? 2 : 3));
                        game.setPoints(game.getVisitorSlot(chr)); //correct.. so still same loser. diff turn tho
                    } else {
                        game.broadcastToVisitors(PlayerShopPacket.getMatchCardSelect(turn, slot, fs, game.isOwner(chr) ? 0 : 1));
                        game.nextLoser();//wrong haha

                    }
                    game.setTurn(1);
                    game.setFirstSlot(0);

                }
                break;
            }
            case EXIT_AFTER_GAME: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "�����У��˳�С��Ϸ");
                }
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null && ips instanceof MapleMiniGame) {
                    MapleMiniGame game = (MapleMiniGame) ips;
                    if (game.isOpen()) {
                        break;
                    }
                    game.broadcastToVisitors(PlayerShopPacket.getMiniGameResult(game, 0, game.getVisitorSlot(chr)));
                    game.nextLoser();
                    game.setOpen(true);
                    game.update();
                    game.checkExitAfterGame();
                }
                break;
            }
            case CANCEL_EXIT: {
                if (c.getPlayer().isAdmin() && MapleParty.����3 > 0) {
                    c.getPlayer().dropMessage(5, "CANCEL_EXIT");
                }
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null && ips instanceof MapleMiniGame) {
                    MapleMiniGame game = (MapleMiniGame) ips;
                    if (game.isOpen()) {
                        break;
                    }
                    game.setExitAfter(chr);
                    game.broadcastToVisitors(PlayerShopPacket.getMiniGameExitAfter(game.isExitAfter(chr)));
                }
                break;
            }
            default: {
                //��������
                //System.out.println("Pla:" + action);
                //some idiots try to send huge amounts of data to this (:
                // c.getPlayer().dropMessage(5, "Unhandled interaction action by " + chr.getName() + " : " + action + ", " + slea.toString());
                //19 (0x13) - 00 OR 01 -> itemid(maple leaf) ? who knows what this is
                break;
            }
        }
    }

    public static void д�����(int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int a10, int a11, int a12, int a13, int a14, int a15, int a16, int a17, int a18, int a19, int a20, int a21, int a22, int a23, int a24, int a25, int a26, int a27, int a28, int a29, String a30) {

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO hire (cid,itemid,upgradeslots,level,str,dex,2int,luk,hp,mp,watk,matk,wdef,mdef,acc,avoid,hands,speed,jump,ViciousHammer,itemEXP,durability,enhance,potential1,potential2,potential3,hpR,mpR,itemlevel,mxmxd_dakong_fumo) VALUES ( ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?,?)")) {
            ps.setInt(1, a1);
            ps.setInt(2, a2);
            ps.setInt(3, a3);
            ps.setInt(4, a4);
            ps.setInt(5, a5);
            ps.setInt(6, a6);
            ps.setInt(7, a7);
            ps.setInt(8, a8);
            ps.setInt(9, a9);
            ps.setInt(10, a10);
            ps.setInt(11, a11);
            ps.setInt(12, a12);
            ps.setInt(13, a13);
            ps.setInt(14, a14);
            ps.setInt(15, a15);
            ps.setInt(16, a16);
            ps.setInt(17, a17);
            ps.setInt(18, a18);
            ps.setInt(19, a19);
            ps.setInt(20, a20);
            ps.setInt(21, a21);
            ps.setInt(22, a22);
            ps.setInt(23, a23);
            ps.setInt(24, a24);
            ps.setInt(25, a25);
            ps.setInt(26, a26);
            ps.setInt(27, a27);
            ps.setInt(28, a28);
            ps.setInt(29, a29);
            ps.setString(30, a30);
            ps.execute();
            ps.close();
        } catch (Exception ex) {
            System.out.println("д�������Ϣ:" + ex);
        }
    }

    public static void ɾ������(int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int a10, int a11, int a12, int a13, int a14, int a15, int a16, int a17, int a18, int a19, int a20, int a21, int a22, int a23, int a24, int a25, int a26, int a27, int a28, int a29, String a30) {
        try {

            ResultSet rs = null;
            PreparedStatement ps = null;
            ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM hire");
            rs = ps.executeQuery();
            if (rs.next()) {
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
                ps.execute(sqlstr);
                ps.close();
            }
        } catch (SQLException ex) {
            System.out.println("ɾ��������Ϣ:" + ex);
        }
    }
}
