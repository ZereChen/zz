/*
三转职业？？
 */
package handling.channel.handler;

import java.util.List;

import client.inventory.IItem;
import client.MapleClient;
import client.MapleCharacter;
import client.MapleDisease;
import client.inventory.MapleInventoryType;
import client.inventory.MaplePet;
import constants.GameConstants;
import client.inventory.PetCommand;
import client.inventory.PetDataFactory;
import handling.world.MaplePartyCharacter;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import server.Randomizer;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.life.MapleMonster;
import server.movement.LifeMovementFragment;
import server.maps.FieldLimitType;
import server.maps.MapleMapItem;
import tools.MaplePacketCreator;
import tools.packet.PetPacket;
import tools.data.LittleEndianAccessor;

public class PetHandler {

    //宠物捡取列表
    public static final void PickExceptionList(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null || chr.getMap() == null) {
            return;
        }
        int itemid = slea.readInt();
        if (!chr.haveItem(itemid)) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
    }

    public static final void SpawnPet(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        chr.updateTick(slea.readInt());
        byte slot = (byte) slea.readShort();
        chr.spawnPet(slot, slea.readByte() > 0);
        
        //System.out.println("SPAWN_PET" + slot);
    }

    public static final void Pet_AutoPotion(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        slea.skip(13);
        final byte slot = slea.readByte();
        if (chr == null || !chr.isAlive() || chr.getMapId() == 749040100 || chr.getMap() == null || chr.hasDisease(MapleDisease.POTION)) {
            return;
        }
        final IItem toUse = chr.getInventory(MapleInventoryType.USE).getItem(slot);

        if (toUse == null || toUse.getQuantity() < 1) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final long time = System.currentTimeMillis();
        if (chr.getNextConsume() > time) {
            chr.dropMessage(5, "你可能不使用这个项目.");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (!FieldLimitType.PotionUse.check(chr.getMap().getFieldLimit()) || chr.getMapId() == 610030600) { //cwk quick hack
            if (MapleItemInformationProvider.getInstance().getItemEffect(toUse.getItemId()).applyTo(chr)) {
                MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.USE, slot, (short) 1, false);
                if (chr.getMap().getConsumeItemCoolTime() > 0) {
                    chr.setNextConsume(time + (chr.getMap().getConsumeItemCoolTime() * 1000));
                }
            }
        } else {
            c.sendPacket(MaplePacketCreator.enableActions());
        }
    }

    public static final void PetChat(final int petid, final short command, final String text, MapleCharacter chr) {
        if (chr == null || chr.getMap() == null || chr.getPetIndex(petid) < 0) {
            return;
        }
        chr.getMap().broadcastMessage(chr, PetPacket.petChat(chr.getId(), command, text, chr.getPetIndex(petid)), true);
    }

    public static final void PetCommand(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        final byte petIndex = chr.getPetIndex(slea.readInt());
        if (petIndex == -1) {
            return;
        }
        MaplePet pet = chr.getPet(petIndex);
        if (pet == null) {
            return;
        }
        slea.skip(5);
        final byte command = slea.readByte();
        final PetCommand petCommand = PetDataFactory.getPetCommand(pet.getPetItemId(), (int) command);

        boolean success = false;
        if (Randomizer.nextInt(99) <= petCommand.getProbability()) {
            success = true;
            if (pet.getCloseness() < 30000) {
                int newCloseness = pet.getCloseness() + petCommand.getIncrease();
                if (newCloseness > 30000) {
                    newCloseness = 30000;
                }
                pet.setCloseness(newCloseness);
                if (newCloseness >= GameConstants.getClosenessNeededForLevel(pet.getLevel() + 1)) {
                    pet.setLevel(pet.getLevel() + 1);
                    c.sendPacket(PetPacket.showOwnPetLevelUp(petIndex));
                    chr.getMap().broadcastMessage(PetPacket.showPetLevelUp(chr, petIndex));
                }
                c.sendPacket(PetPacket.updatePet(pet, chr.getInventory(MapleInventoryType.CASH).getItem((byte) pet.getInventoryPosition()), true));
            }
        }
        chr.getMap().broadcastMessage(chr, PetPacket.commandResponse(chr.getId(), command, petIndex, success, false), true);
    }

    public static final void PetFood(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        int previousFullness = 100;
        MaplePet pet = null;
        if (chr == null) {
            return;
        }
        for (final MaplePet pets : chr.getPets()) {
            if (pets.getSummoned()) {
                if (pets.getFullness() < previousFullness) {
                    previousFullness = pets.getFullness();
                    pet = pets;
                }
            }
        }
        if (pet == null) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }

        slea.skip(6);
        final int itemId = slea.readInt();

        boolean gainCloseness = false;

        if (Randomizer.nextInt(99) <= 50) {
            gainCloseness = true;
        }
        if (pet.getFullness() < 100) {
            int newFullness = pet.getFullness() + 30;
            if (newFullness > 100) {
                newFullness = 100;
            }
            pet.setFullness(newFullness);
            final byte index = chr.getPetIndex(pet);

            if (gainCloseness && pet.getCloseness() < 30000) {
                int newCloseness = pet.getCloseness() + 1;
                if (newCloseness > 30000) {
                    newCloseness = 30000;
                }
                pet.setCloseness(newCloseness);
                if (newCloseness >= GameConstants.getClosenessNeededForLevel(pet.getLevel() + 1)) {
                    pet.setLevel(pet.getLevel() + 1);

                    c.sendPacket(PetPacket.showOwnPetLevelUp(index));
                    chr.getMap().broadcastMessage(PetPacket.showPetLevelUp(chr, index));
                }
            }
            c.sendPacket(PetPacket.updatePet(pet, chr.getInventory(MapleInventoryType.CASH).getItem((byte) pet.getInventoryPosition()), true));
            chr.getMap().broadcastMessage(c.getPlayer(), PetPacket.commandResponse(chr.getId(), (byte) 1, index, true, true), true);
        } else {
            if (gainCloseness) {
                int newCloseness = pet.getCloseness() - 1;
                if (newCloseness < 0) {
                    newCloseness = 0;
                }
                pet.setCloseness(newCloseness);
                if (newCloseness < GameConstants.getClosenessNeededForLevel(pet.getLevel())) {
                    pet.setLevel(pet.getLevel() - 1);
                }
            }
            c.sendPacket(PetPacket.updatePet(pet, chr.getInventory(MapleInventoryType.CASH).getItem((byte) pet.getInventoryPosition()), true));
            chr.getMap().broadcastMessage(chr, PetPacket.commandResponse(chr.getId(), (byte) 1, chr.getPetIndex(pet), false, true), true);
        }
        MapleInventoryManipulator.removeById(c, MapleInventoryType.USE, itemId, 1, true, false);
        c.sendPacket(MaplePacketCreator.enableActions());
    }

    //宠物技能
    public static final void MovePet(final LittleEndianAccessor slea, final MapleCharacter chr) {
        final int petId = slea.readInt();
        slea.skip(8);
        final List<LifeMovementFragment> res = MovementParse.parseMovement(slea, 3);

        if (res != null && chr != null && res.size() != 0) { // map crash hack
            final byte slot = chr.getPetIndex(petId);
            if (slot == -1) {
                return;
            }
            chr.getPet(slot).updatePosition(res);
            chr.getMap().broadcastMessage(chr, PetPacket.movePet(chr.getId(), petId, slot, res), false);
            if (chr.getPlayerShop() != null || chr.getConversation() > 0 || chr.getTrade() != null) { //hack
                return;
            }
            if (chr.getStat().hasVac && (chr.getStat().宠物捡金币 || chr.getStat().宠物捡物品)) {
                List<MapleMapItem> objects = chr.getMap().getAllItems();
                for (MapleMapItem mapitem : objects) {
                    final Lock lock = mapitem.getLock();
                    lock.lock();
                    try {
                        if (mapitem.isPickedUp()) {
                            continue;
                        }
                        if (mapitem.getOwner() != chr.getId() && mapitem.isPlayerDrop()) {
                            continue;
                        }
                        if (mapitem.getOwner() != chr.getId() && ((!mapitem.isPlayerDrop() && mapitem.getDropType() == 0) || (mapitem.isPlayerDrop() && chr.getMap().getEverlast()))) {
                            continue;
                        }
                        if (!mapitem.isPlayerDrop() && mapitem.getDropType() == 1 && mapitem.getOwner() != chr.getId() && (chr.getParty() == null || chr.getParty().getMemberById(mapitem.getOwner()) == null)) {
                            continue;
                        }
                        if (mapitem.getMeso() > 0 && chr.getStat().宠物捡金币) {
                            if (chr.getParty() != null && mapitem.getOwner() != chr.getId()) {
                                final List<MapleCharacter> toGive = new LinkedList<MapleCharacter>();

                                for (MaplePartyCharacter mem : chr.getParty().getMembers()) {
                                    MapleCharacter m = chr.getMap().getCharacterById(mem.getId());
                                    if (m != null) {
                                        toGive.add(m);
                                    }
                                }
                                for (final MapleCharacter m : toGive) {
                                    m.gainMeso(mapitem.getMeso() / toGive.size() + (m.getStat().装备许可证 ? (int) (mapitem.getMeso() / 20.0) : 0), true, true);
                                }
                            } else {
                                chr.gainMeso(mapitem.getMeso(), true, true);
                            }
                            InventoryHandler.removeItem_Pet(chr, mapitem, slot);
                        } else if (chr.getStat().宠物捡物品 && MapleItemInformationProvider.getInstance().isPickupBlocked(mapitem.getItem().getItemId())) {
                            if (InventoryHandler.useItem(chr.getClient(), mapitem.getItemId())) {
                                InventoryHandler.removeItem_Pet(chr, mapitem, slot);
                            } else if (MapleInventoryManipulator.checkSpace(chr.getClient(), mapitem.getItem().getItemId(), mapitem.getItem().getQuantity(), mapitem.getItem().getOwner())) {
                                if (mapitem.getItem().getQuantity() >= 50 && GameConstants.isUpgradeScroll(mapitem.getItem().getItemId())) {

                                    chr.getClient().setMonitored(true); //hack check

                                }

                                if (MapleInventoryManipulator.addFromDrop(chr.getClient(), mapitem.getItem(), true, mapitem.getDropper() instanceof MapleMonster)) {

                                    InventoryHandler.removeItem_Pet(chr, mapitem, slot);
                                }
                            }
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    }
}
