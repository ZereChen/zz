/*
��Ӧ��
 */
package handling.channel.handler;

import client.inventory.IItem;
import client.MapleCharacter;
import client.MapleClient;
import client.MapleLieDetector;
import client.inventory.MapleInventoryType;
import client.MapleStat;
import client.anticheat.CheatingOffense;
import constants.GameConstants;
import java.util.Iterator;
import scripting.NPCScriptManager;
import scripting.ReactorScriptManager;
import server.events.MapleCoconut;
import server.events.MapleCoconut.MapleCoconuts;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.custom.bossrank.BossRankManager;
import server.events.MapleEventType;
import server.maps.FieldLimitType;
import server.maps.MapleDoor;
import server.maps.MapleMap;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import server.maps.MapleReactor;
import server.quest.MapleQuest;
import tools.FileoutputUtil;
import tools.MaplePacketCreator;
import tools.data.LittleEndianAccessor;

public class PlayersHandler {

    public static void Note(final LittleEndianAccessor slea, final MapleCharacter chr) {
        final byte type = slea.readByte();

        switch (type) {
            case 0:
                String name = slea.readMapleAsciiString();
                String msg = slea.readMapleAsciiString();
                boolean fame = slea.readByte() > 0;
                slea.readInt(); //0?
                IItem itemz = chr.getCashInventory().findByCashId((int) slea.readLong());
                if (itemz == null || !itemz.getGiftFrom().equalsIgnoreCase(name) || !chr.getCashInventory().canSendNote(itemz.getUniqueId())) {
                    return;
                }
                try {
                    chr.sendNote(name, msg, fame ? 1 : 0);
                    chr.getCashInventory().sendedNote(itemz.getUniqueId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                final byte num = slea.readByte();
                slea.readByte();
                byte ���� = slea.readByte();
                for (int i = 0; i < num; i++) {
                    final int id = slea.readInt();
                    chr.deleteNote(id, ���� > 0 ? ���� : 0);
                }
                break;
            default:
                System.out.println("Unhandled note action, " + type + "");
        }
    }

    public static void GiveFame(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        final int who = slea.readInt();
        final int mode = slea.readByte();

        final int famechange = mode == 0 ? -1 : 1;
        final MapleCharacter target = (MapleCharacter) chr.getMap().getMapObject(who, MapleMapObjectType.PLAYER);

        if (target == chr) { // faming self
            chr.getCheatTracker().registerOffense(CheatingOffense.����Լ�����);
            return;
        } else if (chr.getLevel() < 15) {
            chr.getCheatTracker().registerOffense(CheatingOffense.����ʮ�弶�������);
            return;
        }
        switch (chr.canGiveFame(target)) {
            //���������������
            case OK:
                if (Math.abs(target.getFame() + famechange) <= 30000) {
                    target.addFame(famechange);
                    target.updateSingleStat(MapleStat.FAME, target.getFame());
                    target.setBossLog("�ճ�������2");//ΪĿ��Ӽ�¼
                    if (c.getPlayer().isAdmin() && gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                        target.dropMessage(5, "ÿ������-��������������");
                    }
                    chr.setBossLog("�ճ�������1");//Ϊ�Լ��Ӽ�¼
                    if (c.getPlayer().isAdmin() && gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0) {
                        chr.dropMessage(5, "ÿ������-���ӱ�������");
                    }

                }
                if (!chr.isGM()) {
                    chr.hasGivenFame(target);
                }
                c.sendPacket(MaplePacketCreator.giveFameResponse(mode, target.getName(), target.getFame()));
                target.getClient().sendPacket(MaplePacketCreator.receiveFame(mode, chr.getName()));

                break;
            case NOT_TODAY:
                c.sendPacket(MaplePacketCreator.giveFameErrorResponse(3));
                break;
            case NOT_THIS_MONTH:
                c.sendPacket(MaplePacketCreator.giveFameErrorResponse(4));

                break;
        }
    }

    public static void ChatRoomHandler(final LittleEndianAccessor slea, final MapleClient c) {

        NPCScriptManager.getInstance().dispose(c);
        c.sendPacket(MaplePacketCreator.enableActions());//����⿨
        NPCScriptManager.getInstance().start(c, 3, 330);

    }

    public static void �̳�(final LittleEndianAccessor slea, final MapleClient c) {

        InterServerHandler.EnterCS(c, c.getPlayer(), false);//����� false �ĳ� true ���ǽ�������̳�//Boolean.parseBoolean(ServerProperties.getProperty("ZEV.Shopping"))

        /*c.getPlayer().saveToDB(false, false);
        NPCScriptManager.getInstance().dispose(c);
        c.sendPacket(MaplePacketCreator.enableActions());//
        NPCScriptManager.getInstance().start(c, 4, 1001);*/
    }

    public static void ����(final LittleEndianAccessor slea, final MapleClient c) {
        //c.getPlayer().dropMessage(6, "����" + slea.toString(true));
        c.getPlayer().dropMessage(16, "������ɹ����������Ա�ϱ�����ԭ��");
        c.getPlayer().saveToDB(false, false);
        NPCScriptManager.getInstance().dispose(c);
        c.sendPacket(MaplePacketCreator.enableActions());//����⿨
    }

    public static void ����(final LittleEndianAccessor slea, final MapleClient c) {
        //c.getPlayer().saveToDB(false, false);
        NPCScriptManager.getInstance().dispose(c);
        c.sendPacket(MaplePacketCreator.enableActions());
        NPCScriptManager.getInstance().start(c, 9900004, 0);

    }

    public static void UseDoor(final LittleEndianAccessor slea, final MapleCharacter chr) {
        final int oid = slea.readInt();
        final boolean mode = slea.readByte() == 0; // ָ���Ƿ���ˣ�1��Ŀ�꣬0Ŀ����

        for (MapleMapObject obj : chr.getMap().getAllDoorsThreadsafe()) {
            final MapleDoor door = (MapleDoor) obj;
            if (door.getOwnerId() == oid) {
                door.warp(chr, mode);
                break;
            }
        }
    }

    public static void TransformPlayer(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        // D9 A4 FD 00
        // 11 00
        // A0 C0 21 00
        // 07 00 64 66 62 64 66 62 64
        chr.updateTick(slea.readInt());
        final byte slot = (byte) slea.readShort();
        final int itemId = slea.readInt();
        final String target = slea.readMapleAsciiString().toLowerCase();

        final IItem toUse = c.getPlayer().getInventory(MapleInventoryType.USE).getItem(slot);

        if (toUse == null || toUse.getQuantity() < 1 || toUse.getItemId() != itemId) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        switch (itemId) {
            //ʥ���ڱ���ҩˮ
            case 2212000:
                for (final MapleCharacter search_chr : c.getPlayer().getMap().getCharactersThreadsafe()) {
                    if (search_chr.getName().toLowerCase().equals(target)) {
                        MapleItemInformationProvider.getInstance().getItemEffect(2210023).applyTo(search_chr);
                        search_chr.dropMessage(6, chr.getName() + " ����ˣ�˶�����!");
                        MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, slot, (short) 1, false);
                    }
                }
                break;
        }
    }

    public static void HitReactor(final LittleEndianAccessor slea, final MapleClient c) {
        final int oid = slea.readInt();
        final int charPos = slea.readInt();
        final short stance = slea.readShort();
        final MapleReactor reactor = c.getPlayer().getMap().getReactorByOid(oid);
        if (reactor == null || !reactor.isAlive()) {
            return;
        }
        if (gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0 && c.getPlayer().isGM()) {
            c.getPlayer().dropMessage(5, "[ϵͳ��ʾ]���ѹ�����Ӧ��" + reactor.getReactorId());
        }
        reactor.hitReactor(charPos, stance, c);
    }

    public static void TouchReactor(final LittleEndianAccessor slea, final MapleClient c) {
        final int oid = slea.readInt();
        final boolean touched = slea.readByte() > 0;
        final MapleReactor reactor = c.getPlayer().getMap().getReactorByOid(oid);
        if (!touched || reactor == null || !reactor.isAlive() || reactor.getReactorId() < 6109013 || reactor.getReactorId() > 6109027 || (reactor.getTouch() == 0)) {
            return;
        }
        if (c.getPlayer().isAdmin()) {
            c.getPlayer().dropMessage(5, new StringBuilder().append("��Ӧ����Ϣ - oid: ").append(oid).append(" Touch: ").append(reactor.getTouch()).append(" isTimerActive: ").append(reactor.isTimerActive()).append(" ReactorType: ").append(reactor.getReactorType()).toString());
        }
        if (reactor.getTouch() == 2) {
            ReactorScriptManager.getInstance().act(c, reactor);
        } else if ((reactor.getTouch() == 1) && (!reactor.isTimerActive())) {
            if (reactor.getReactorType() == 100) {
                int itemid = GameConstants.getCustomReactItem(reactor.getReactorId(), ((Integer) reactor.getReactItem().getLeft()).intValue());
                if (c.getPlayer().haveItem(itemid, ((Integer) reactor.getReactItem().getRight()).intValue())) {
                    if (reactor.getArea().contains(c.getPlayer().getTruePosition())) {
                        MapleInventoryManipulator.removeById(c, GameConstants.getInventoryType(itemid), itemid, ((Integer) reactor.getReactItem().getRight()).intValue(), true, false);
                        reactor.hitReactor(c);
                    } else {
                        c.getPlayer().dropMessage(5, "����̫Զ���뿿�������³��ԡ�");
                    }
                } else {
                    c.getPlayer().dropMessage(5, "��û���������Ʒ.");
                }
            } else {
                reactor.hitReactor(c);
            }
        }

    }

    public static void hitCoconut(LittleEndianAccessor slea, MapleClient c) {
        int id = slea.readShort();
        String co = "Ҭ��";
        MapleCoconut map = (MapleCoconut) c.getChannelServer().getEvent(MapleEventType.��Ҭ�ӱ���);
        if (map == null || !map.isRunning()) {
            map = (MapleCoconut) c.getChannelServer().getEvent(MapleEventType.��ƿ�Ǳ���);
            co = "ƿ��";
            if (map == null || !map.isRunning()) {
                return;
            }
        }
        MapleCoconuts nut = map.getCoconut(id);
        if (nut == null || !nut.isHittable()) {
            return;
        }
        if (System.currentTimeMillis() < nut.getHitTime()) {
            return;
        }
        if (nut.getHits() > 2 && Math.random() < 0.4 && !nut.isStopped()) {
            nut.setHittable(false);
            if (Math.random() < 0.01 && map.getStopped() > 0) {
                nut.setStopped(true);
                map.stopCoconut();
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.hitCoconut(false, id, 1));
                return;
            }
            nut.resetHits();
            if (Math.random() < 0.05 && map.getBombings() > 0) {
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.hitCoconut(false, id, 2));
                map.bombCoconut();
            } else if (map.getFalling() > 0) {
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.hitCoconut(false, id, 3));
                map.fallCoconut();
                if (c.getPlayer().getTeam() == 0) {
                    map.addMapleScore();
                    c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.serverNotice(5, "[�ʺ��]: " + c.getPlayer().getName() + " �ɹ����һ�� " + co + "."));
                } else {
                    map.addStoryScore();
                    c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.serverNotice(5, "[���ض�]: " + c.getPlayer().getName() + " �ɹ����һ�� " + co + "."));
                }
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.coconutScore(map.getCoconutScore()));
            }
        } else {
            nut.hit();
            c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.hitCoconut(false, id, 1));
        }
    }

    public static void FollowRequest(final LittleEndianAccessor slea, final MapleClient c) {
        MapleCharacter tt = c.getPlayer().getMap().getCharacterById(slea.readInt());
        if (slea.readByte() > 0) {
            //1 when changing map
            tt = c.getPlayer().getMap().getCharacterById(c.getPlayer().getFollowId());
            if (tt != null && tt.getFollowId() == c.getPlayer().getId()) {
                tt.setFollowOn(true);
                c.getPlayer().setFollowOn(true);
            } else {
                c.getPlayer().checkFollow();
            }
            return;
        }
        if (slea.readByte() > 0) { //cancelling follow
            tt = c.getPlayer().getMap().getCharacterById(c.getPlayer().getFollowId());
            if (tt != null && tt.getFollowId() == c.getPlayer().getId() && c.getPlayer().isFollowOn()) {
                c.getPlayer().checkFollow();
            }
            return;
        }
        if (tt != null && tt.getPosition().distanceSq(c.getPlayer().getPosition()) < 10000 && tt.getFollowId() == 0 && c.getPlayer().getFollowId() == 0 && tt.getId() != c.getPlayer().getId()) { //estimate, should less
            tt.setFollowId(c.getPlayer().getId());
            tt.setFollowOn(false);
            tt.setFollowInitiator(false);
            c.getPlayer().setFollowOn(false);
            c.getPlayer().setFollowInitiator(false);
            //   tt.getClient().sendPacket(MaplePacketCreator.followRequest(c.getPlayer().getId()));
        } else {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "����̫Զ��."));
        }
    }

    public static void FollowReply(final LittleEndianAccessor slea, final MapleClient c) {
        if (c.getPlayer().getFollowId() > 0 && c.getPlayer().getFollowId() == slea.readInt()) {
            MapleCharacter tt = c.getPlayer().getMap().getCharacterById(c.getPlayer().getFollowId());
            if (tt != null && tt.getPosition().distanceSq(c.getPlayer().getPosition()) < 10000 && tt.getFollowId() == 0 && tt.getId() != c.getPlayer().getId()) { //estimate, should less
                boolean accepted = slea.readByte() > 0;
                if (accepted) {
                    tt.setFollowId(c.getPlayer().getId());
                    tt.setFollowOn(true);
                    tt.setFollowInitiator(true);
                    c.getPlayer().setFollowOn(true);
                    c.getPlayer().setFollowInitiator(false);
                    c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.followEffect(tt.getId(), c.getPlayer().getId(), null));
                } else {
                    c.getPlayer().setFollowId(0);
                    tt.setFollowId(0);
                    // tt.getClient().sendPacket(MaplePacketCreator.getFollowMsg(5));
                }
            } else {
                if (tt != null) {
                    tt.setFollowId(0);
                    c.getPlayer().setFollowId(0);
                }
                c.sendPacket(MaplePacketCreator.serverNotice(1, "����̫Զ��."));
            }
        } else {
            c.getPlayer().setFollowId(0);
        }
    }

    //���ڽ��
    public static void RingAction(final LittleEndianAccessor slea, final MapleClient c) {
        final byte mode = slea.readByte();
        if (mode == 0) {
            final String name = slea.readMapleAsciiString();
            final int itemid = slea.readInt();
            final int newItemId = 1112300 + (itemid - 2240004);
            final MapleCharacter chr = c.getChannelServer().getPlayerStorage().getCharacterByName(name);
            int errcode = 0;
            if (c.getPlayer().getMarriageId() > 0) {
                errcode = 0x17;
            } else if (chr == null) {
                errcode = 0x12;
            } else if (chr.getMapId() != c.getPlayer().getMapId()) {
                errcode = 0x13;
            } else if (!c.getPlayer().haveItem(itemid, 1) || itemid < 2240004 || itemid > 2240015) {//�³�ʯ��ָ//�����ָ3����
                errcode = 0x0D;
            } else if (chr.getMarriageId() > 0 || chr.getMarriageItemId() > 0) {
                errcode = 0x18;
            } else if (!MapleInventoryManipulator.checkSpace(c, newItemId, 1, "")) {
                errcode = 0x14;
            } else if (!MapleInventoryManipulator.checkSpace(chr.getClient(), newItemId, 1, "")) {
                errcode = 0x15;
            }
            if (errcode > 0) {
                c.sendPacket(MaplePacketCreator.sendEngagement((byte) errcode, 0, null, null));
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            c.getPlayer().setMarriageItemId(itemid);
            chr.getClient().sendPacket(MaplePacketCreator.sendEngagementRequest(c.getPlayer().getName(), c.getPlayer().getId()));
            //1112300 + (itemid - 2240004)
        } else if (mode == 1) {
            c.getPlayer().setMarriageItemId(0);
        } else if (mode == 2) { //accept/deny proposal
            final boolean accepted = slea.readByte() > 0;
            final String name = slea.readMapleAsciiString();
            final int id = slea.readInt();
            final MapleCharacter chr = c.getChannelServer().getPlayerStorage().getCharacterByName(name);
            if (c.getPlayer().getMarriageId() > 0 || chr == null || chr.getId() != id || chr.getMarriageItemId() <= 0 || !chr.haveItem(chr.getMarriageItemId(), 1) || chr.getMarriageId() > 0) {
                c.sendPacket(MaplePacketCreator.sendEngagement((byte) 0x1D, 0, null, null));
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            if (accepted) {
                final int newItemId = 1112300 + (chr.getMarriageItemId() - 2240004);
                if (!MapleInventoryManipulator.checkSpace(c, newItemId, 1, "") || !MapleInventoryManipulator.checkSpace(chr.getClient(), newItemId, 1, "")) {
                    c.sendPacket(MaplePacketCreator.sendEngagement((byte) 0x15, 0, null, null));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                MapleInventoryManipulator.addById(c, newItemId, (short) 1, (byte) 0);
                MapleInventoryManipulator.removeById(chr.getClient(), MapleInventoryType.USE, chr.getMarriageItemId(), 1, false, false);
                MapleInventoryManipulator.addById(chr.getClient(), newItemId, (short) 1, (byte) 0);
                chr.getClient().sendPacket(MaplePacketCreator.sendEngagement((byte) 0x10, newItemId, chr, c.getPlayer()));
                chr.setMarriageId(c.getPlayer().getId());
                c.getPlayer().setMarriageId(chr.getId());
            } else {
                chr.getClient().sendPacket(MaplePacketCreator.sendEngagement((byte) 0x1E, 0, null, null));
            }
            c.sendPacket(MaplePacketCreator.enableActions());
            chr.setMarriageItemId(0);
        } else if (mode == 3) { //drop, only works for ETC
            final int itemId = slea.readInt();
            final MapleInventoryType type = GameConstants.getInventoryType(itemId);
            final IItem item = c.getPlayer().getInventory(type).findById(itemId);
            if (item != null && type == MapleInventoryType.ETC && itemId / 10000 == 421) {
                MapleInventoryManipulator.drop(c, type, item.getPosition(), item.getQuantity());
            }
        }
    }

    public static void UpdateCharInfo(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        int type = slea.readByte();
        if (type == 0) { // ��ɫӍϢ
            String charmessage = slea.readMapleAsciiString();
            c.getPlayer().setcharmessage(charmessage);
            //System.err.println("SetCharMessage");
        } else if (type == 1) { // ����
            int expression = slea.readByte();
            c.getPlayer().setexpression(expression);
            //System.err.println("Expression"+ expression);
        } else if (type == 2) { // ���ռ�����
            int blood = slea.readByte();
            int month = slea.readByte();
            int day = slea.readByte();
            int constellation = slea.readByte();
            c.getPlayer().setblood(blood);
            c.getPlayer().setmonth(month);
            c.getPlayer().setday(day);
            c.getPlayer().setconstellation(constellation);
            //System.err.println("Constellation");
        }
    }

//     public static void LieDetector(LittleEndianAccessor slea, MapleClient c, MapleCharacter chr, boolean isItem) {
//        if ((chr == null) || (chr.getMap() == null)) {
//            return;
//        }
//        String target = slea.readMapleAsciiString();
//        byte slot = 0;
//        if (isItem) {
//        }
//    }
//
//    public static void LieDetectorResponse(LittleEndianAccessor slea, MapleClient c) {
//        if ((c.getPlayer() == null) || (c.getPlayer().getMap() == null)) {
//            return;
//        }
//        
//    }
//
//    public static void LieDetectorRefresh(LittleEndianAccessor slea, MapleClient c) {
//        if ((c.getPlayer() == null) || (c.getPlayer().getMap() == null)) {
//            return;
//        }
//        
//    }
    public static void LieDetector(LittleEndianAccessor slea, MapleClient c, MapleCharacter chr, boolean isItem) {
        if ((chr == null) || (chr.getMap() == null)) {
            return;
        }
        String target = slea.readMapleAsciiString();
        byte slot = 0;
//        if (isItem) {
        /*  if (!chr.getCheatTracker().canLieDetector()) {
                chr.dropMessage(1, "���Ѿ�ʹ�ù�һ�Σ���ʱ���޷�ʹ�ò���ǵ���.");
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }*/
        slot = (byte) slea.readShort();
        int itemId = slea.readInt();
        IItem toUse = chr.getInventory(MapleInventoryType.USE).getItem((short) slot);
        if ((toUse == null) || (toUse.getQuantity() <= 0) || (toUse.getItemId() != itemId) || (itemId != 2190000)) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        //} else if (!chr.isIntern()) {
        //     c.getSession().close(true);
        //      return;
        //  }
        if (((FieldLimitType.PotionUse.check(chr.getMap().getFieldLimit())) && (isItem)) || (chr.getMap().getReturnMapId() == chr.getMapId())) {
            chr.dropMessage(5, "��ǰ��ͼ�޷�ʹ�ò����.");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        MapleCharacter search_chr = chr.getMap().getCharacterByName(target);
        if ((search_chr == null) || (search_chr.getId() == chr.getId()) /*|| ((search_chr.isIntern()) && (!chr.isIntern()))*/) {
            chr.dropMessage(1, "δ�ҵ���ɫ.");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if ((search_chr.getEventInstance() != null) || (search_chr.getMapId() == 180000001)) {
            chr.dropMessage(5, "��ǰ��ͼ�޷�ʹ�ò����.");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (search_chr.getAntiMacro().inProgress()) {
            c.sendPacket(MaplePacketCreator.LieDetectorResponse((byte) 3));
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (((search_chr.getAntiMacro().isPassed()) && (isItem)) || (search_chr.getAntiMacro().getAttempt() == 2)) {
            c.sendPacket(MaplePacketCreator.LieDetectorResponse((byte) 2));
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (!search_chr.getAntiMacro().startLieDetector(chr.getName(), isItem, false)) {
            chr.dropMessage(5, "ʹ�ò����ʧ��.");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (isItem) {
            MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, (short) slot, (short) 1, false);
        }
        search_chr.dropMessage(5, new StringBuilder().append(chr.getName()).append(" ����ʹ���˲����.").toString());
//        } else {
//            chr.dropMessage(1, new StringBuilder().append(" ����ǻ�δ���ƣ���ʱ�ر�.").toString());
//        }
    }

    public static void LieDetectorResponse(LittleEndianAccessor slea, MapleClient c) {
        if ((c.getPlayer() == null) || (c.getPlayer().getMap() == null)) {
            return;
        }
        String answer = slea.readMapleAsciiString();
        MapleLieDetector ld = c.getPlayer().getAntiMacro();
        if ((!ld.inProgress()) || ((ld.isPassed()) && (ld.getLastType() == 0)) || (ld.getAnswer() == null) || (answer.length() <= 0)) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (answer.equalsIgnoreCase(ld.getAnswer())) {
            MapleCharacter search_chr = c.getPlayer().getMap().getCharacterByName(ld.getTester());
            if ((search_chr != null) && (search_chr.getId() != c.getPlayer().getId())) {
                search_chr.dropMessage(5, new StringBuilder().append(c.getPlayer().getName()).append(" ͨ���˲���ǵļ��.").toString());
            }
            c.sendPacket(MaplePacketCreator.LieDetectorResponse((byte) 0x0C, (byte) 1));
            c.getPlayer().gainMeso(5000, true);
            ld.end();
            //  World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, new StringBuilder().append("[GM Message] ���: ").append(c.getPlayer().getName()).append(" (�ȼ� ").append(c.getPlayer().getLevel()).append(") ͨ���˲���Ǽ�⡣").toString()));
        } else if (ld.getAttempt() < 3) {// ͼƬ����
            ld.startLieDetector(ld.getTester(), ld.getLastType() == 0, true);
        } else {
            MapleCharacter search_chr = c.getPlayer().getMap().getCharacterByName(ld.getTester());
            if ((search_chr != null) && (search_chr.getId() != c.getPlayer().getId())) {
                search_chr.dropMessage(5, new StringBuilder().append(c.getPlayer().getName()).append(" ͨ������ǵļ�⣬��ϲ����7000�Ľ��.").toString());
                search_chr.gainMeso(7000, true);
            }
            ld.end();
            c.getPlayer().getClient().sendPacket(MaplePacketCreator.LieDetectorResponse((byte) 0x0A, (byte) 4));
            MapleMap map = c.getChannelServer().getMapFactory().getMap(180000001);
            c.getPlayer().getQuestNAdd(MapleQuest.getInstance(123456)).setCustomData(String.valueOf(30 * 60));
            c.getPlayer().changeMap(map, map.getPortal(0));
            //World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, new StringBuilder().append("���: ").append(c.getPlayer().getName()).append(" (�ȼ� ").append(c.getPlayer().getLevel()).append(") δͨ������Ǽ�⣬ϵͳ������30���ӣ�").toString()));
        }
    }

    public static void LieDetectorRefresh(LittleEndianAccessor slea, MapleClient c) {
        if ((c.getPlayer() == null) || (c.getPlayer().getMap() == null)) {
            return;
        }
        MapleLieDetector ld = c.getPlayer().getAntiMacro();
        if (ld.getAttempt() < 3) {
            ld.startLieDetector(ld.getTester(), ld.getLastType() == 0, true);
        } else {
            ld.end();
            c.getPlayer().getClient().sendPacket(MaplePacketCreator.LieDetectorResponse((byte) 8, (byte) 4));
            MapleMap map = c.getChannelServer().getMapFactory().getMap(180000001);
            c.getPlayer().getQuestNAdd(MapleQuest.getInstance(123456)).setCustomData(String.valueOf(1800));
            c.getPlayer().changeMap(map, map.getPortal(0));
            //Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, new StringBuilder().append("[GM Message] ���: ").append(c.getPlayer().getName()).append(" (�ȼ� ").append(c.getPlayer().getLevel()).append(") δͨ������Ǽ�⣬ϵͳ������30���ӣ�").toString()));
        }
    }
}
