package client.messages.commands;

import static abc.Game.��������;
import static abc.Game.����;
import static abc.Game.ˢ;
import static abc.Game.ˢ���ܵ�;
import static abc.Game.ˢ��;
import static abc.Game.ˢ������;
import static abc.Game.�ٻ�����;
import static abc.Game.����;
import static abc.Game.�ҵ�λ��;
import static abc.Game.�޵�;
import static abc.Game.���;
import static abc.Game.���2;
import static abc.Game.����;
import client.ISkill;
import client.MapleCharacter;
import client.MapleCharacterUtil;
import constants.ServerConstants.PlayerGMRank;
import client.MapleClient;
import client.MapleStat;
import client.SkillFactory;
import client.inventory.Equip;
import client.inventory.IItem;
import client.inventory.ItemFlag;
import client.inventory.MapleInventoryIdentifier;
import client.inventory.MapleInventoryType;
import client.inventory.MaplePet;
import client.messages.CommandProcessorUtil;
import constants.GameConstants;
import database.DatabaseConnection;
import handling.channel.ChannelServer;
import handling.world.World;
import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.MaplePortal;
import server.MapleShopFactory;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import server.life.MapleNPC;
import server.life.OverrideMonsterStats;
import server.maps.MapleMap;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import tools.MaplePacketCreator;
import tools.StringUtil;
import tools.packet.MobPacket;
import java.sql.Connection;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import scripting.NPCScriptManager;
import server.Randomizer;
import server.ServerProperties;
import tools.*;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.packet.UIPacket;
import static abc.Game.�ȼ�;
import client.DebugWindow;
import handling.world.MapleParty;
import java.text.DateFormat;
import java.util.Calendar;
import static gui.QQMsgServer.sendMsgToQQGroup;
import static gui.Start.CashShopServer;
import server.life.PlayerNPC;
import server.quest.MapleQuest;
import static gui.����BOSS.����BOSS�߳�.��������BOSS�߳�;

/**
 *
 * @author ��Ϸ����Ա
 *
 *
 */
public class ��Ϸ���� {

    public static PlayerGMRank getPlayerLevelRequired() {
        return PlayerGMRank.��Ϸ����;
    }

    public static class ��� extends CommandExecute {

        protected boolean hellban = false;

        private String getCommand() {
            return "���";
        }

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (splitted.length < 3) {
                c.getPlayer().dropMessage(5, "[���] *" + getCommand() + " <�ո�>[�������]<�ո�>[ԭ��]");
                return 0;
            }
            int ch = World.Find.findChannel(splitted[1]);

            StringBuilder sb = new StringBuilder(c.getPlayer().getName());
            sb.append(" banned ").append(splitted[1]).append(": ").append(StringUtil.joinStringFrom(splitted, 2));
            MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(splitted[1]);
            if (target == null || ch < 1) {
                if (MapleCharacter.ban(splitted[1], sb.toString(), false, c.getPlayer().isAdmin() ? 250 : c.getPlayer().getGMLevel(), splitted[0].equals("*���"))) {
                    c.getPlayer().dropMessage(6, "[" + getCommand() + "] �ɹ����߷��� " + splitted[1] + ".");
                    return 1;
                } else {
                    c.getPlayer().dropMessage(6, "[" + getCommand() + "] ����ʧ�� " + splitted[1]);
                    return 0;
                }
            }
            if (c.getPlayer().getGMLevel() > target.getGMLevel()) {
                sb.append(" (IP: ").append(target.getClient().getSessionIPAddress()).append(")");
                if (target.ban(sb.toString(), c.getPlayer().isAdmin(), false, hellban)) {
                    c.getPlayer().dropMessage(6, "[" + getCommand() + "] �ɹ����� " + splitted[1] + ".");
                    String ��Ϣ = "[ϵͳ����]" + target.getName() + " ��Ϊʹ�÷Ƿ����/�ƻ���Ϸƽ�⣬�������÷�š�";
                    World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
                    sendMsgToQQGroup(��Ϣ);
                    return 1;
                } else {
                    c.getPlayer().dropMessage(6, "[" + getCommand() + "] ����ʧ��");
                    return 0;
                }
            } else {
                c.getPlayer().dropMessage(6, "[" + getCommand() + "] ���ܷ���GM");
                return 1;
            }
        }
    }

    public static class �������� extends CommandExecute {///////resetquest

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            MapleQuest.getInstance(Integer.parseInt(splitted[1])).forfeit(c.getPlayer());
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�������� <����ID> - ��������").toString();

        }
    }

    public static class ��ʼ���� extends CommandExecute {////////StartQuest

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            MapleQuest.getInstance(Integer.parseInt(splitted[1])).start(c.getPlayer(), Integer.parseInt(splitted[2]));
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!��ʼ���� <����ID> - ��ʼ����").toString();

        }
    }

    public static class ������� extends CommandExecute {//CompleteQuest

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            MapleQuest.getInstance(Integer.parseInt(splitted[1])).complete(c.getPlayer(), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]));
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!������� <����ID> - �������").toString();

        }
    }

    public static class ���� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().cloneLook();
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!��¡ - ������¡��").toString();
        }
    }

    public static class ��¡ extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (c.getPlayer().getCloneSize() == 0) {
                c.getPlayer().cloneLook2();
            } else {
                c.getPlayer().disposeClones();
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!��¡ - ������¡��").toString();
        }
    }

    public static class ȡ����¡ extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().dropMessage(6, c.getPlayer().getCloneSize() + "����¡����ʧ��.");
            c.getPlayer().disposeClones();
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!ȡ����¡ - �ݻٿ�¡��").toString();
        }
    }

    public static class ����ɱ����Կ��� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            MapleParty.����ɱ�++;
            return 1;
        }

    }

    public static class ����ɱ����Խ��� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            MapleParty.����ɱ� = 0;
            return 1;
        }

    }

    public static class ���� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            ��������BOSS�߳�();
            /*  MobSkill mobSkill = MobSkillFactory.getMobSkill(129, 3);
            MapleDisease disease = null;
            disease = MapleDisease.getBySkill(129);
            c.getPlayer().giveDebuff(disease, mobSkill);
             */
            // c.getPlayer().getClient().getSession().close();
            return 1;
        }

    }

    /*if (splitted[1].equalsIgnoreCase("SEAL")) {
                type = 120;
            } else if (splitted[1].equalsIgnoreCase("DARKNESS")) {
                type = 121;
            } else if (splitted[1].equalsIgnoreCase("WEAKEN")) {
                type = 122;
            } else if (splitted[1].equalsIgnoreCase("STUN")) {
                type = 123;
            } else if (splitted[1].equalsIgnoreCase("CURSE")) {
                type = 124;
            } else if (splitted[1].equalsIgnoreCase("POISON")) {
                type = 125;
            } else if (splitted[1].equalsIgnoreCase("SLOW")) {
                type = 126;
            } else if (splitted[1].equalsIgnoreCase("SEDUCE")) {
                type = 128;
            } else if (splitted[1].equalsIgnoreCase("REVERSE")) {
                type = 132;
            } else if (splitted[1].equalsIgnoreCase("ZOMBIFY")) {
                type = 133;
            } else if (splitted[1].equalsIgnoreCase("POTION")) {
                type = 134;
            } else if (splitted[1].equalsIgnoreCase("SHADOW")) {
                type = 135;
            } else if (splitted[1].equalsIgnoreCase("BLIND")) {
                type = 136;
            } else if (splitted[1].equalsIgnoreCase("FREEZE")) {
                type = 137;
            } else {
                return 0;
            }
    
     */
    public static class ���NPC extends CommandExecute {/////////MakePNPC

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 3) {
                return 0;
            }
            try {
                c.getPlayer().dropMessage(6, "Making playerNPC...");
                MapleCharacter chhr;
                String name = splitted[1];
                int ch = World.Find.findChannel(name);
                if (ch <= 0) {
                    c.getPlayer().dropMessage(6, "��ұ�������");
                    return 1;
                }
                chhr = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);

                if (chhr == null) {
                    c.getPlayer().dropMessage(6, splitted[1] + " is not online");
                } else {
                    int npcId = Integer.parseInt(splitted[2]);
                    MapleNPC npc_c = MapleLifeFactory.getNPC(npcId);
                    if (npc_c == null || npc_c.getName().equals("MISSINGNO")) {
                        c.getPlayer().dropMessage(6, "NPC������");
                        return 1;
                    }
                    PlayerNPC npc = new PlayerNPC(chhr, npcId, c.getPlayer().getMap(), c.getPlayer());
                    npc.addToServer();
                    c.getPlayer().dropMessage(6, "Done");
                }
            } catch (Exception e) {
                c.getPlayer().dropMessage(6, "NPC failed... : " + e.getMessage());

            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���npc <playername> <npcid> - �������NPC").toString();
        }
    }

    public static class ����2 extends CommandExecute {///////////Letter

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 3) {
                c.getPlayer().dropMessage(6, "ָ�����: ");
                return 0;
            }
            int start, nstart;
            if (splitted[1].equalsIgnoreCase("green")) {
                start = 3991026;
                nstart = 3990019;
            } else if (splitted[1].equalsIgnoreCase("red")) {
                start = 3991000;
                nstart = 3990009;
            } else {
                c.getPlayer().dropMessage(6, "δ֪���ɫ!");
                return 1;
            }
            String splitString = StringUtil.joinStringFrom(splitted, 2);
            List<Integer> chars = new ArrayList<>();
            splitString = splitString.toUpperCase();
            // System.out.println(splitString);
            for (int i = 0; i < splitString.length(); i++) {
                char chr = splitString.charAt(i);
                if (chr == ' ') {
                    chars.add(-1);
                } else if ((int) (chr) >= (int) 'A' && (int) (chr) <= (int) 'Z') {
                    chars.add((int) (chr));
                } else if ((int) (chr) >= (int) '0' && (int) (chr) <= (int) ('9')) {
                    chars.add((int) (chr) + 200);
                }
            }
            final int w = 32;
            int dStart = c.getPlayer().getPosition().x - (splitString.length() / 2 * w);
            for (Integer i : chars) {
                if (i == -1) {
                    dStart += w;
                } else if (i < 200) {
                    int val = start + i - (int) ('A');
                    client.inventory.Item item = new client.inventory.Item(val, (byte) 0, (short) 1);
                    c.getPlayer().getMap().spawnItemDrop(c.getPlayer(), c.getPlayer(), item, new Point(dStart, c.getPlayer().getPosition().y), false, false);
                    dStart += w;
                } else if (i >= 200 && i <= 300) {
                    int val = nstart + i - (int) ('0') - 200;
                    client.inventory.Item item = new client.inventory.Item(val, (byte) 0, (short) 1);
                    c.getPlayer().getMap().spawnItemDrop(c.getPlayer(), c.getPlayer(), item, new Point(dStart, c.getPlayer().getPosition().y), false, false);
                    dStart += w;
                }
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append(" !letter <color (green/red)> <word> - ����").toString();
        }
    }

    public static class �������� extends CommandExecute {///////////Letter

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 3) {
                c.getPlayer().dropMessage(6, "ָ�����: ");
                return 0;
            }
            int start, nstart;
            if (splitted[1].equalsIgnoreCase("green")) {
                start = 3991026;
                nstart = 3990019;
            } else if (splitted[1].equalsIgnoreCase("red")) {
                start = 3991000;
                nstart = 3990009;
            } else {
                c.getPlayer().dropMessage(6, "δ֪���ɫ!");
                return 1;
            }
            String splitString = StringUtil.joinStringFrom(splitted, 2);
            List<Integer> chars = new ArrayList<>();
            splitString = splitString.toUpperCase();
            // System.out.println(splitString);
            for (int i = 0; i < splitString.length(); i++) {
                char chr = splitString.charAt(i);
                if (chr == ' ') {
                    chars.add(-1);
                } else if ((int) (chr) >= (int) 'A' && (int) (chr) <= (int) 'Z') {
                    chars.add((int) (chr));
                } else if ((int) (chr) >= (int) '0' && (int) (chr) <= (int) ('9')) {
                    chars.add((int) (chr) + 200);
                }
            }
            final int w = 32;
            int dStart = c.getPlayer().getPosition().x - (splitString.length() / 2 * w);
            for (Integer i : chars) {
                if (i == -1) {
                    dStart += w;
                } else if (i < 200) {
                    int val = start + i - (int) ('A');
                    client.inventory.Item item = new client.inventory.Item(val, (byte) 0, (short) 1);
                    c.getPlayer().getMap().spawnItemDrop(c.getPlayer(), c.getPlayer(), item, new Point(dStart, c.getPlayer().getPosition().y), false, false);
                    dStart += w;
                } else if (i >= 200 && i <= 300) {
                    int val = nstart + i - (int) ('0') - 200;
                    client.inventory.Item item = new client.inventory.Item(val, (byte) 0, (short) 1);
                    c.getPlayer().getMap().spawnItemDrop(c.getPlayer(), c.getPlayer(), item, new Point(dStart, c.getPlayer().getPosition().y), false, false);
                    dStart += w;
                }
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append(" !letter <color (green/red)> <word> ").toString();
        }
    }

    public static class ����1 extends CommandExecute {///////////Letter

        public int execute(MapleClient c, String splitted[]) {
            client.inventory.Item item = new client.inventory.Item(2000005, (byte) 0, (short) 1);
            c.getPlayer().getMap().��Ʒ����(c.getPlayer(), c.getPlayer(), item, new Point(c.getPlayer().getPosition().x, c.getPlayer().getPosition().y + 1000), false, false);
            return 1;
        }
    }

    public static class ��� extends CommandExecute {

        protected boolean hellban = false;

        private String getCommand() {
            return "Ban";
        }

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (splitted.length < 3) {
                c.getPlayer().dropMessage(5, "[���] !" + getCommand() + " <���> <ԭ��>");
                return 0;
            }
            int ch = World.Find.findChannel(splitted[1]);
            StringBuilder sb = new StringBuilder(c.getPlayer().getName());
            sb.append(" banned ").append(splitted[1]).append(": ").append(StringUtil.joinStringFrom(splitted, 0));
            MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(splitted[1]);
            return 1;
        }
    }

    public static class �ȼ� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (�ȼ� == "��") {
                return 1;
            }
            c.getPlayer().setLevel(Short.parseShort(splitted[1]));
            c.getPlayer().levelUp();
            if (c.getPlayer().getExp() < 0) {
                c.getPlayer().gainExp(-c.getPlayer().getExp(), false, false, true);
            }
            return 1;
        }
    }

    public static class ���� extends CommandExecute {//LevelUp

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                c.getPlayer().levelUp();
            } else {
                int up = 0;
                try {
                    up = Integer.parseInt(splitted[1]);
                } catch (Exception ex) {
                }
                for (int i = 0; i < up; i++) {
                    c.getPlayer().levelUp();
                }
            }
            c.getPlayer().setExp(0);
            c.getPlayer().updateSingleStat(MapleStat.EXP, 0);
            if (c.getPlayer().getLevel() < 200) {
                c.getPlayer().gainExp(GameConstants.getExpNeededForLevel(c.getPlayer().getLevel()) + 1, true, false, true);
            }
            return 1;
        }

    }

    public static class �������� extends CommandExecute {////////////Fame

        public int execute(MapleClient c, String splitted[]) {
            if (�������� == "��") {
                return 1;
            }
            MapleCharacter player = c.getPlayer();
            if (splitted.length < 2) {
                c.getPlayer().dropMessage(("�������� <��ɫ����> <����> ...  - ����"));
                return 0;
            }
            MapleCharacter victim;
            String name = splitted[1];
            int ch = World.Find.findChannel(name);
            if (ch <= 0) {
                return 0;
            }
            victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);

            short fame;
            try {
                fame = Short.parseShort(splitted[2]);
            } catch (NumberFormatException nfe) {
                c.getPlayer().dropMessage(6, "���Ϸ�������");
                return 0;
            }
            if (victim != null && player.allowedToTarget(victim)) {
                victim.addFame(fame);
                victim.updateSingleStat(MapleStat.FAME, victim.getFame());
            } else {
                c.getPlayer().dropMessage(6, "[fame] ��ɫ������");
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("*��������+<��ɫ����> <����> ...  - ����").toString();
        }
    }

    public static class �޵� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (�޵� == "��") {
                return 1;
            }
            MapleCharacter player = c.getPlayer();
            if (player.isInvincible()) {
                player.setInvincible(false);
                player.dropMessage(6, "�޵��Ѿ��ر�");
            } else {
                player.setInvincible(true);
                player.dropMessage(6, "�޵��Ѿ�����.");
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("*�޵�  - �޵п���").toString();
        }
    }

    public static class ˢ���ܵ� extends CommandExecute {//SP

        public int execute(MapleClient c, String splitted[]) {
            if (ˢ���ܵ� == "��") {
                return 1;
            }
            c.getPlayer().setRemainingSp(CommandProcessorUtil.getOptionalIntArg(splitted, 1, 1));
            c.sendPacket(MaplePacketCreator.updateSp(c.getPlayer(), false));
            String msg = "[����Ա��Ϣ][" + c.getPlayer().getName() + "] ʹ��ָ������SP " + splitted + "��";
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!ˢ���ܵ� [����] - ����SP").toString();
        }
    }

    public static class ˢ������ extends CommandExecute {//AP

        public int execute(MapleClient c, String splitted[]) {
            if (ˢ������ == "��") {
                return 1;
            }
            c.getPlayer().setRemainingAp((short) CommandProcessorUtil.getOptionalIntArg(splitted, 1, 1));
            c.getPlayer().updateSingleStat(MapleStat.AVAILABLEAP, CommandProcessorUtil.getOptionalIntArg(splitted, 1, 1));
            String msg = "[����Ա��Ϣ][" + c.getPlayer().getName() + "] ʹ��ָ������AP " + splitted + "��";
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!ˢ������ [����] - ����AP").toString();
        }
    }

    public static class ˢ extends CommandExecute {//Item

        public int execute(MapleClient c, String splitted[]) {
            if (ˢ == "��") {
                return 1;
            }
            if (splitted.length < 2) {
                return 0;
            }
            int itemId = 0;
            try {
                itemId = Integer.parseInt(splitted[1]);
            } catch (Exception ex) {
            }
            short quantity = (short) CommandProcessorUtil.getOptionalIntArg(splitted, 2, 1);

            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            if (GameConstants.isPet(itemId)) {
                MaplePet pet = MaplePet.createPet(itemId, MapleInventoryIdentifier.getInstance());
                if (pet != null) {
                    MapleInventoryManipulator.addById(c, itemId, (short) 1, c.getPlayer().getName(), pet, 90, (byte) 0);

                }
            } else if (!ii.itemExists(itemId)) {
                c.getPlayer().dropMessage(5, itemId + " - ��Ʒ������");
            } else {
                IItem item;
                byte flag = 0;
                flag |= ItemFlag.LOCK.getValue();

                if (GameConstants.getInventoryType(itemId) == MapleInventoryType.EQUIP) {
                    item = ii.randomizeStats((Equip) ii.getEquipById(itemId));
                    //    item.setFlag(flag);
                } else {
                    item = new client.inventory.Item(itemId, (byte) 0, quantity, (byte) 0);
                    if (GameConstants.getInventoryType(itemId) != MapleInventoryType.USE) {
                        //     item.setFlag(flag);
                    }
                }
                item.setOwner(c.getPlayer().getName());
                item.setGMLog(c.getPlayer().getName());
                MapleInventoryManipulator.addbyItem(c, item);
                if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                    World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ˢ������Ʒ: " + MapleItemInformationProvider.getInstance().getName(Integer.parseInt(splitted[1])) + " [" + splitted[1] + "]"));
                }
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!ˢ <����ID> - ˢ������").toString();
        }
    }

    public static class ���� extends CommandExecute {////////////MobVac

        public int execute(MapleClient c, String splitted[]) {
            if (���� == "��") {
                return 1;
            }
            for (final MapleMapObject mmo : c.getPlayer().getMap().getAllMonstersThreadsafe()) {
                final MapleMonster monster = (MapleMonster) mmo;
                c.getPlayer().getMap().broadcastMessage(MobPacket.moveMonster(false, -1, 0, 0, 0, 0, monster.getObjectId(), monster.getPosition(), c.getPlayer().getPosition(), c.getPlayer().getLastRes()));
                monster.setPosition(c.getPlayer().getPosition());
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���� - ȫͼ����").toString();
        }
    }

    public static class ˢ�� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (ˢ�� == "��") {
                return 1;
            }
            MapleCharacter player = c.getPlayer();
            c.sendPacket(MaplePacketCreator.getCharInfo(player));
            player.getMap().removePlayer(player);
            player.getMap().addPlayer(player);
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ����ˢ��ָ��"));
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!ˢ�� - �ٵǳ��ٵ���").toString();

        }
    }

    public static class ���� extends CommandExecute {//RemoveDrops

        public int execute(MapleClient c, String splitted[]) {
            if (���� == "��") {
                return 1;
            }
            c.getPlayer().dropMessage(5, "����˵�ͼ�� " + c.getPlayer().getMap().getNumItems() + " ��������");
            c.getPlayer().getMap().removeDrops();
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ�������������Ʒָ��"));
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���� - �Ƴ����ϵ���Ʒ").toString();

        }
    }

    public static class ��� extends CommandExecute {//KillAll

        public int execute(MapleClient c, String splitted[]) {
            if (��� == "��") {
                return 1;
            }
            MapleMap map = c.getPlayer().getMap();
            double range = Double.POSITIVE_INFINITY;
            boolean drop = false;
            if (splitted.length > 1) {
                int irange = 9999;
                if (splitted.length < 2) {
                    range = irange * irange;
                } else {
                    try {
                        map = c.getChannelServer().getMapFactory().getMap(Integer.parseInt(splitted[1]));
                        range = Integer.parseInt(splitted[2]) * Integer.parseInt(splitted[2]);
                    } catch (Exception ex) {
                    }
                }
                if (splitted.length >= 3) {
                    drop = splitted[3].equalsIgnoreCase("true");
                }
            }
            MapleMonster mob;
            List<MapleMapObject> monsters = map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER));
            for (MapleMapObject monstermo : map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
                mob = (MapleMonster) monstermo;
                map.killMonster(mob, c.getPlayer(), true, false, (byte) 1);
            }
            c.getPlayer().dropMessage(5, "���ܹ�ɱ�� " + monsters.size() + " ����");
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ�����������ָ��"));
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!��� [range] [mapid] - ɱ�����й���").toString();

        }
    }

    /* if (server.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ�����������ָ��"));
            }*/
    public static class ���2 extends CommandExecute {//KillAll

        public int execute(MapleClient c, String splitted[]) {
            if (���2 == "��") {
                return 1;
            }
            MapleMap map = c.getPlayer().getMap();
            double range = Double.POSITIVE_INFINITY;
            boolean drop = true;
            if (splitted.length > 1) {
                int irange = 9999;
                if (splitted.length < 2) {
                    range = irange * irange;
                } else {
                    try {
                        map = c.getChannelServer().getMapFactory().getMap(Integer.parseInt(splitted[1]));
                        range = Integer.parseInt(splitted[2]) * Integer.parseInt(splitted[2]);
                    } catch (Exception ex) {
                    }
                }
                if (splitted.length >= 3) {
                    drop = splitted[3].equalsIgnoreCase("true");
                }
            }
            MapleMonster mob;
            List<MapleMapObject> monsters = map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER));
            for (MapleMapObject monstermo : map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
                mob = (MapleMonster) monstermo;
                map.killMonster(mob, c.getPlayer(), true, false, (byte) 1);
            }
            return 1;
        }
    }

    public static class �ҵ�λ�� extends CommandExecute {//////MyPos

        public int execute(MapleClient c, String splitted[]) {
            if (�ҵ�λ�� == "��") {
                return 1;
            }
            Point pos = c.getPlayer().getPosition();
            c.getPlayer().dropMessage(6, "X: " + pos.x + " | Y: " + pos.y + " | RX0: " + (pos.x + 50) + " | RX1: " + (pos.x - 50) + " | FH: " + c.getPlayer().getFH() + "| CY:" + pos.y);
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ�����ҵ�λ��ָ��"));
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("*�ҵ�λ��").toString();
        }
    }

    public static class �ٻ����� extends CommandExecute {//Spawn

        public int execute(MapleClient c, String splitted[]) {
            if (�ٻ����� == "��") {
                return 1;
            }
            if (splitted.length < 2) {
                return 0;
            }
            int mid = 0;
            try {
                mid = Integer.parseInt(splitted[1]);
            } catch (Exception ex) {
            }
            int num = Math.min(CommandProcessorUtil.getOptionalIntArg(splitted, 2, 1), 500);
            if (num > 1000) {
                num = 1000;
            }
            Long hp = CommandProcessorUtil.getNamedLongArg(splitted, 1, "hp");
            Integer exp = CommandProcessorUtil.getNamedIntArg(splitted, 1, "exp");
            Double php = CommandProcessorUtil.getNamedDoubleArg(splitted, 1, "php");
            Double pexp = CommandProcessorUtil.getNamedDoubleArg(splitted, 1, "pexp");

            MapleMonster onemob;
            try {
                onemob = MapleLifeFactory.getMonster(mid);
            } catch (RuntimeException e) {
                c.getPlayer().dropMessage(5, "����: " + e.getMessage());
                return 1;
            }

            long newhp;
            int newexp;
            if (hp != null) {
                newhp = hp;
            } else if (php != null) {
                newhp = (long) (onemob.getMobMaxHp() * (php / 100));
            } else {
                newhp = onemob.getMobMaxHp();
            }
            if (exp != null) {
                newexp = exp;
            } else if (pexp != null) {
                newexp = (int) (onemob.getMobExp() * (pexp / 100));
            } else {
                newexp = onemob.getMobExp();
            }
            if (newhp < 1) {
                newhp = 1;
            }

            final OverrideMonsterStats overrideStats = new OverrideMonsterStats(newhp, onemob.getMobMaxMp(), newexp, false);
            for (int i = 0; i < num; i++) {
                MapleMonster mob = MapleLifeFactory.getMonster(mid);
                mob.setHp(newhp);
                mob.setOverrideStats(overrideStats);
                c.getPlayer().getMap().spawnMonsterOnGroundBelow(mob, c.getPlayer().getPosition());
                if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                    World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]�ٻ��˹���[" + splitted[1] + "]ָ��"));
                }
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("*�ٻ����� <����ID> <hp|exp|php||pexp = ?> - �ٻ�����").toString();

        }
    }

    public static class ���� extends CommandExecute {//Map

        public int execute(MapleClient c, String splitted[]) {
            if (���� == "��") {
                return 1;
            }
            if (splitted.length < 2) {
                return 0;
            }
            try {
                MapleMap target = c.getChannelServer().getMapFactory().getMap(Integer.parseInt(splitted[1]));
                if (target == null) {
                    c.getPlayer().dropMessage(5, "��ͼ������.");
                    return 1;
                }
                MaplePortal targetPortal = null;
                if (splitted.length > 2) {
                    try {
                        targetPortal = target.getPortal(Integer.parseInt(splitted[2]));
                    } catch (IndexOutOfBoundsException e) {
                        c.getPlayer().dropMessage(5, "���͵����.");
                    } catch (NumberFormatException a) {
                    }
                }
                if (targetPortal == null) {
                    targetPortal = target.getPortal(0);
                }
                c.getPlayer().changeMap(target, targetPortal);
                if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                    World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]���͵���ͼ[" + splitted[1] + "]"));
                }
            } catch (Exception e) {
                c.getPlayer().dropMessage(5, "Error: " + e.getMessage());
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("*���� <mapid|charname> [portal] - ���͵�ĳ��ͼ/��").toString();
        }
    }

    public static class �½�NPC extends CommandExecute {

        public int execute(MapleClient c, String[] splitted) {

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
                    com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DatabaseConnection.getConnection();
                    try (com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("INSERT INTO ��ϷNPC���� (dataid, f, hide, fh, cy, rx0, rx1, type, x, y, mid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
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
                    c.getPlayer().dropMessage(6, "δ�ܽ�NPC���浽���ݿ�");
                }
                for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                    cserv.getMapFactory().getMap(c.getPlayer().getMapId()).addMapObject(npc);
                    cserv.getMapFactory().getMap(c.getPlayer().getMapId()).broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
//                    c.getPlayer().getMap().addMapObject(npc);
//                    c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
                }
                c.getPlayer().dropMessage(6, "NPC�����ɹ��������Ҫɾ�����������ݿ⡶��ϷNPC������ɾ����������Ч");
                if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                    World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ�����½�NPCָ��"));
                }
            } else {
                c.getPlayer().dropMessage(6, "���޴� Npc ");
            }

            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!����npc - ��������NPC").toString();
        }
    }

    public static class �������� extends CommandExecute {//WarpAllHere

        public int execute(MapleClient c, String splitted[]) {
            for (ChannelServer CS : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : CS.getPlayerStorage().getAllCharactersThreadSafe()) {
                    if (mch.getMapId() != c.getPlayer().getMapId()) {
                        mch.changeMap(c.getPlayer().getMap(), c.getPlayer().getPosition());
                    }
                    if (mch.getClient().getChannel() != c.getPlayer().getClient().getChannel()) {
                        mch.changeChannel(c.getPlayer().getClient().getChannel());
                    }
                }
            }
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ������������ָ��"));
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("*�������� ��������Ҵ��͵�����").toString();
        }
    }

    public static class ������ extends CommandExecute {//maxSkills

        public int execute(MapleClient c, String splitted[]) {
            MapleCharacter player = c.getPlayer();
            player.maxSkills();
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ����������ָ��"));
            }
            return 1;
        }
    }

    public static class �� extends CommandExecute {//Drop

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            int itemId = 0;
            try {
                itemId = Integer.parseInt(splitted[1]);
            } catch (Exception ex) {
            }

            final short quantity = (short) CommandProcessorUtil.getOptionalIntArg(splitted, 2, 1);
            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();

            if (GameConstants.isPet(itemId)) {
                c.getPlayer().dropMessage(5, "�����뵽�����̳ǹ���.");
            } else if (!ii.itemExists(itemId)) {
                c.getPlayer().dropMessage(5, itemId + " - ��Ʒ������");
            } else {
                IItem toDrop;
                if (GameConstants.getInventoryType(itemId) == MapleInventoryType.EQUIP) {

                    toDrop = ii.randomizeStats((Equip) ii.getEquipById(itemId));
                } else {
                    toDrop = new client.inventory.Item(itemId, (byte) 0, (short) quantity, (byte) 0);
                }
                //toDrop.setOwner(c.getPlayer().getName());
                toDrop.setGMLog(c.getPlayer().getName());
                if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                    World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]��������Ʒ: " + MapleItemInformationProvider.getInstance().getName(Integer.parseInt(splitted[1])) + " [" + splitted[1] + "]"));
                }
                c.getPlayer().getMap().spawnItemDrop(c.getPlayer(), c.getPlayer(), toDrop, c.getPlayer().getPosition(), true, true);
            }
            return 1;
        }
    }

    public static class ף�� extends CommandExecute {//buff

        public int execute(MapleClient c, String splitted[]) {
            MapleCharacter player = c.getPlayer();
            SkillFactory.getSkill(9001002).getEffect(1).applyTo(player);
            SkillFactory.getSkill(9001003).getEffect(1).applyTo(player);
            SkillFactory.getSkill(9001008).getEffect(1).applyTo(player);
            SkillFactory.getSkill(9001001).getEffect(1).applyTo(player);
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ����ף��ָ��"));
            }
            return 1;
        }
    }

    public static class ������ extends CommandExecute {//maxstats

        public int execute(MapleClient c, String splitted[]) {
            MapleCharacter player = c.getPlayer();
            player.getStat().setMaxHp((short) 30000);
            player.getStat().setMaxMp((short) 30000);
            player.getStat().setStr(Short.MAX_VALUE);
            player.getStat().setDex(Short.MAX_VALUE);
            player.getStat().setInt(Short.MAX_VALUE);
            player.getStat().setLuk(Short.MAX_VALUE);
            player.updateSingleStat(MapleStat.MAXHP, 30000);
            player.updateSingleStat(MapleStat.MAXMP, 30000);
            player.updateSingleStat(MapleStat.STR, Short.MAX_VALUE);
            player.updateSingleStat(MapleStat.DEX, Short.MAX_VALUE);
            player.updateSingleStat(MapleStat.INT, Short.MAX_VALUE);
            player.updateSingleStat(MapleStat.LUK, Short.MAX_VALUE);
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ����������ָ��"));
            }
            return 1;
        }
    }

    public static class �ƹ��˺���ʾ extends CommandExecute {//maxstats

        public int execute(MapleClient c, String splitted[]) {
            if (MapleParty.�˺���ʾ == 0) {
                MapleParty.�˺���ʾ = 1;
                c.getPlayer().dropMessage(5, "�����ƹ��˺���ʾ");
            } else {
                MapleParty.�˺���ʾ = 0;
                c.getPlayer().dropMessage(5, "�ر��ƹ��˺���ʾ");
            }
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ�����ƹ��˺���ʾָ��"));
            }
            return 1;
        }
    }

    public static class ������ extends CommandExecute {//maxstats

        public int execute(MapleClient c, String splitted[]) {
            MapleCharacter player = c.getPlayer();
            player.getStat().setMaxHp((short) 10000);
            player.getStat().setMaxMp((short) 10000);
            player.getStat().setStr((short) 1000);
            player.getStat().setDex((short) 1000);
            player.getStat().setInt((short) 1000);
            player.getStat().setLuk((short) 1000);
            player.updateSingleStat(MapleStat.MAXHP, 10000);
            player.updateSingleStat(MapleStat.MAXMP, 10000);
            player.updateSingleStat(MapleStat.STR, (short) 1000);
            player.updateSingleStat(MapleStat.DEX, (short) 1000);
            player.updateSingleStat(MapleStat.INT, (short) 1000);
            player.updateSingleStat(MapleStat.LUK, (short) 1000);
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ���˼�����ָ��"));
            }
            return 1;
        }
    }

    public static class �ڿ� extends CommandExecute {//WhereAmI

        @Override
        public int execute(MapleClient c, String splitted[]) {
            int r = Randomizer.nextInt(100000);
            c.getPlayer().dropMessage(5, "����¼�ɹ�,������:" + r + "");
            FileoutputUtil.logToFile("����������ɱ�/" + c.getPlayer().getMap().getId() + ".txt",
                    "<imgdir name=\"" + r + "\">\r\n"
                    + "<string name=\"id\" value=\"2112012\"/>\r\n"
                    + "<int name=\"x\" value=\"" + String.valueOf(c.getPlayer().getPosition().x) + "\"/>\r\n"
                    + "<int name=\"y\" value=\"" + String.valueOf(c.getPlayer().getPosition().y) + "\"/>\r\n"
                    + "<int name=\"reactorTime\" value=\"300\"/>\r\n"
                    + "<int name=\"f\" value=\"0\"/>\r\n"
                    + "<string name=\"name\" value=\"\"/>\r\n"
                    + "</imgdir>\r\n\r\n"
            );
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ���������ڿ�ص㹦��"));
            }
            return 1;
        }
    }

    public static class ��ҩ extends CommandExecute {//WhereAmI

        @Override
        public int execute(MapleClient c, String splitted[]) {
            int r = Randomizer.nextInt(100000);
            c.getPlayer().dropMessage(5, "��ҩ��¼�ɹ�,��ҩ�����:" + r + "");
            FileoutputUtil.logToFile("��ҩ���������ɱ�/" + c.getPlayer().getMap().getId() + ".txt",
                    "<imgdir name=\"" + r + "\">\r\n"
                    + "<string name=\"id\" value=\"1072000\"/>\r\n"
                    + "<int name=\"x\" value=\"" + String.valueOf(c.getPlayer().getPosition().x) + "\"/>\r\n"
                    + "<int name=\"y\" value=\"" + String.valueOf(c.getPlayer().getPosition().y) + "\"/>\r\n"
                    + "<int name=\"reactorTime\" value=\"300\"/>\r\n"
                    + "<int name=\"f\" value=\"0\"/>\r\n"
                    + "<string name=\"name\" value=\"\"/>\r\n"
                    + "</imgdir>\r\n\r\n"
            );
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ���������ڲ�ҩ�ص㹦��"));
            }
            return 1;
        }
    }

    public static class ������� extends CommandExecute {//mob

        public int execute(MapleClient c, String[] splitted) {
            MapleMonster monster = null;
            for (final MapleMapObject monstermo : c.getPlayer().getMap().getMapObjectsInRange(c.getPlayer().getPosition(), 100000, Arrays.asList(MapleMapObjectType.MONSTER))) {
                monster = (MapleMonster) monstermo;
                if (monster.isAlive()) {
                    c.getPlayer().dropMessage(6, "���� " + monster.toString());
                }
            }
            if (monster == null) {
                c.getPlayer().dropMessage(6, "�Ҳ�������");
            }
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ���˲�ѯ������빦��"));
            }
            return 1;
        }
    }

    public static class ���ŵ�ͼ extends CommandExecute {//openmap

        public int execute(MapleClient c, String[] splitted) {
            int mapid = 0;
            String input = null;
            MapleMap map = null;
            if (splitted.length < 2) {
                c.getPlayer().dropMessage(splitted[0] + " - ���ŵ�ͼ");
                return 0;
            }
            try {
                input = splitted[1];
                mapid = Integer.parseInt(input);
            } catch (Exception ex) {
            }
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                cserv.getMapFactory().HealMap(mapid);

            }
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]�����˵�ͼ[" + splitted[1] + "]"));
            }
            return 1;

        }
    }

    public static class �رյ�ͼ extends CommandExecute {//closemap

        public int execute(MapleClient c, String[] splitted) {
            int mapid = 0;
            String input = null;
            MapleMap map = null;
            if (splitted.length < 2) {
                c.getPlayer().dropMessage(splitted[0] + " - �رյ�ͼ");
                return 0;
            }
            try {
                input = splitted[1];
                mapid = Integer.parseInt(input);
            } catch (Exception ex) {
            }
            if (c.getChannelServer().getMapFactory().getMap(mapid) == null) {
                c.getPlayer().dropMessage("��ͼ������");
                return 0;
            }
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                cserv.getMapFactory().destroyMap(mapid, true);
            }
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]�ر��˵�ͼ[" + splitted[1] + "]"));
            }
            return 1;
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class ��ͼ���� extends CommandExecute {//WhereAmI

        @Override
        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            c.getPlayer().dropMessage(5, "��ͼ: " + c.getPlayer().getMap().getMapName() + " ");
            c.getPlayer().dropMessage(5, "����: " + c.getPlayer().getMap().getId() + " ");
            c.getPlayer().dropMessage(5, "����: " + String.valueOf(c.getPlayer().getPosition().x) + " , " + String.valueOf(c.getPlayer().getPosition().y) + "");

            c.getPlayer().dropMessage(5, "ʹ�� *����+<�ո�>+<��ͼID> ��ֱ�Ӵ��͵�Ŀ���ͼ");
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");

            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ���˲�ѯ��ǰ��ͼ���빦��"));
            }
            return 1;
        }
    }

    public static class �������� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            int totalOnline = 0;
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                totalOnline += cserv.getConnectedClients();
            }
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            c.getPlayer().dropMessage(5, new StringBuilder().append("��������ǰ: ").append(totalOnline).append(" ���������").toString());
            c.getPlayer().dropMessage(5, "                                            ");
            c.getPlayer().dropMessage(5, "ʹ�� *������� �ɲ鿴ÿ��Ƶ�������������Ϣ");
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            return 1;
        }
    }

    public static class ������� extends CommandExecute {

        @Override

        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            for (ChannelServer cs : ChannelServer.getAllInstances()) {
                c.getPlayer().dropMessage(6, "[Ƶ��:" + cs.getChannel() + "]��");
                StringBuilder sb = new StringBuilder();
                Collection<MapleCharacter> cmc = cs.getPlayerStorage().getAllCharacters();
                for (MapleCharacter chr : cmc) {
                    if (sb.length() > 150) {
                        sb.setLength(sb.length() - 2);
                        c.getPlayer().dropMessage(5, sb + "\r\n".toString());
                        sb = new StringBuilder();
                    }
                    //if (!chr.isGM()) {
                    c.getPlayer().dropMessage(5, sb + "\r\n".toString());
                    sb.append(MapleCharacterUtil.makeMapleReadable("-[��ɫID: " + chr.getId() + " ][����: " + chr.getName() + " ][�ȼ�: " + chr.getLevel() + " ] [��ͼ: " + chr.getMapId() + " / " + chr.getMap().getMapName() + " ]\r\n"));
                    // }
                }

                if (sb.length() >= 2) {
                    sb.setLength(sb.length() - 2);
                    c.getPlayer().dropMessage(5, sb.toString());
                }
            }
            c.getPlayer().dropMessage(5, "                                             ");
            c.getPlayer().dropMessage(5, "ʹ�� *�������� �ɲ鿴����������������");
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            return 1;
        }
    }

    public static class �������IP extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            Connection con = DatabaseConnection.getConnection();
            try {
                java.sql.PreparedStatement ps = con.prepareStatement("Delete from ipbans");
                ps.executeUpdate();
                ps.close();
                c.getPlayer().dropMessage(5, "�ɹ���������ѱ������IP��ַ");
            } catch (SQLException e) {
                System.out.println("Error " + e);
            }
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            return 1;
        }
    }

    public static class ������л����� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            Connection con = DatabaseConnection.getConnection();
            try {
                java.sql.PreparedStatement ps = con.prepareStatement("Delete from macbans");
                ps.executeUpdate();
                ps.close();
                c.getPlayer().dropMessage(5, "�ɹ���������ѱ�����Ļ�����");
            } catch (SQLException e) {
                System.out.println("Error " + e);
            }
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            return 1;
        }
    }

    public static class ���ÿ����Ϣ extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            Connection con = DatabaseConnection.getConnection();
            try {
                java.sql.PreparedStatement ps = con.prepareStatement("Delete from bosslog");
                ps.executeUpdate();
                ps.close();
                c.getPlayer().dropMessage(5, "�ɹ����ÿ����Ϣ");
            } catch (SQLException e) {
                System.out.println("Error " + e);
            }
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            return 1;
        }
    }

    public static class ְҵ���� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            c.getPlayer().dropMessage(5, "��ð�յ�ְҵ����һ����");
            c.getPlayer().dropMessage(5, "");
            c.getPlayer().dropMessage(5, "����:0  ������:1000  սͯ:2000");
            c.getPlayer().dropMessage(5, "����ʿ:1100 - 1112");
            c.getPlayer().dropMessage(5, "����ʿ:1200 - 1212");
            c.getPlayer().dropMessage(5, "����ʹ��:1300 - 1312");
            c.getPlayer().dropMessage(5, "ҹ����:1400 - 1412");
            c.getPlayer().dropMessage(5, "��Ϯ��:1500 - 1512");
            c.getPlayer().dropMessage(5, "ս��:2100 - 2112");
            c.getPlayer().dropMessage(5, "ս  ʿ:100   ��    ��:110  ��    ʿ:111  Ӣ      ��:112");
            c.getPlayer().dropMessage(5, "             ׼ �� ʿ:120  ��    ʿ:121  ʥ  ��  ʿ:122");
            c.getPlayer().dropMessage(5, "             ǹ ս ʿ:130  �� �� ʿ:131  ��  ��  ʿ:132");
            c.getPlayer().dropMessage(5, "ħ��ʦ:200   �𶾷�ʦ:210  ����ʦ:211  ��ħ��ʦ:212");
            c.getPlayer().dropMessage(5, "             ���׷�ʦ:220  ������ʦ:221  ����ħ��ʦ:222");
            c.getPlayer().dropMessage(5, "             ��    ʦ:230  ��    ʦ:231  ��      ��:232");
            c.getPlayer().dropMessage(5, "������:300   ��    ��:310  ��    ��:311  ��  ��  ��:312");
            c.getPlayer().dropMessage(5, "             �� �� ��:320  ��    ��:321  ��      ��:322");
            c.getPlayer().dropMessage(5, "��  ��:400   ��    ��:410  �� Ӱ ��:411  ��      ʿ:412");
            c.getPlayer().dropMessage(5, "             ��    ��:420  �� �� ��:421  ��      ��:422");
            c.getPlayer().dropMessage(5, "��  ��:500   ȭ    ��:510  ��    ʿ:511  ���  �ӳ�:512");
            c.getPlayer().dropMessage(5, "             �� ǹ ��:520  ��    ��:521  ��      ��:522");
            c.getPlayer().dropMessage(5, "                                      ");
            c.getPlayer().dropMessage(5, "תְָ��:*תְ+<�ո�>+<ְҵ����>");
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");

            return 1;
        }
    }

    public static class �޸ĵ�ǰƵ�����鱶�� extends CommandExecute {//ExpRate

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length > 1) {
                final int rate = Integer.parseInt(splitted[1]);
                if (splitted.length > 2 && splitted[2].equalsIgnoreCase("all")) {
                    for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                        cserv.setExpRate(rate);
                    }
                } else {
                    c.getChannelServer().setExpRate(rate);
                }
                c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
                c.getPlayer().dropMessage(5, "��Ϸ�������鱶��Ϊ: " + Integer.parseInt(ServerProperties.getProperty("ZEV.Exp")) + " ��");
                c.getPlayer().dropMessage(5, "�޸ĵ�ǰƵ�����鱶��Ϊ: " + rate + "��");
                c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
                if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                    World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]����ǰƵ�����鱶�ʸ���Ϊ " + splitted[1] + " ��"));
                }
            } else {
                return 0;
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("*�޸ĵ�ǰƵ�����鱶�� <����> - ���ľ��鱶��").toString();

        }
    }

    public static class �޸ĵ�ǰƵ����Ʒ���� extends CommandExecute {//DropRate

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length > 1) {
                final int rate = Integer.parseInt(splitted[1]);
                if (splitted.length > 2 && splitted[2].equalsIgnoreCase("all")) {
                    for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                        cserv.setDropRate(rate);
                    }
                } else {
                    c.getChannelServer().setDropRate(rate);
                }
                c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
                c.getPlayer().dropMessage(5, "��Ϸ������Ʒ����Ϊ: " + Integer.parseInt(ServerProperties.getProperty("ZEV.Drop")) + " ��");
                c.getPlayer().dropMessage(5, "�޸ĵ�ǰƵ����Ʒ����Ϊ: " + rate + "��");
                c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
                if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                    World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]����ǰƵ�����ﱶ�ʸ���Ϊ " + splitted[1] + " ��"));
                }
            } else {
                return 0;
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("*�޸ĵ�ǰƵ����Ʒ���� <����> - ���ĵ��䱶��").toString();

        }
    }

    public static class �޸ĵ�ǰƵ����ұ��� extends CommandExecute {//MesoRate

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length > 1) {
                final int rate = Integer.parseInt(splitted[1]);
                if (splitted.length > 2 && splitted[2].equalsIgnoreCase("all")) {
                    for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                        cserv.setMesoRate(rate);
                    }
                } else {
                    c.getChannelServer().setMesoRate(rate);
                }
                c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
                c.getPlayer().dropMessage(5, "��Ϸ������ұ���Ϊ: " + Integer.parseInt(ServerProperties.getProperty("ZEV.Meso")) + " ��");
                c.getPlayer().dropMessage(5, "�޸ĵ�ǰƵ����ұ���Ϊ: " + rate + "��");
                c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
                if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                    World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]����ǰƵ����ұ��ʸ���Ϊ " + splitted[1] + " ��"));
                }
            } else {
                return 0;
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("*�޸ĵ�ǰƵ����ұ��� <����> - ���Ľ�Ǯ����").toString();

        }
    }

    public static class ������� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            NPCScriptManager.getInstance().dispose(c);
            c.sendPacket(MaplePacketCreator.enableActions());
            NPCScriptManager npc = NPCScriptManager.getInstance();
            npc.start(c, 9900004, 81);
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            c.getPlayer().dropMessage(5, "�ɹ���������Ҹ�����壬ͨ������ֱ�Ӹ��ٵ�Ŀ���ͼ");
            c.getPlayer().dropMessage(5, "��ָͬ�*����+<�ո�>+<��ɫ����>");
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            return 1;
        }
    }

    public static class �޸���� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            NPCScriptManager.getInstance().dispose(c);
            c.sendPacket(MaplePacketCreator.enableActions());
            NPCScriptManager npc = NPCScriptManager.getInstance();
            npc.start(c, 9900004, 71447500);
            return 1;
        }
    }

    public static class תְ extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().changeJob(Integer.parseInt(splitted[1]));
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                String ְҵ = "";
                if (Integer.parseInt(splitted[1]) == 0) {
                    ְҵ = "����";
                } else if (Integer.parseInt(splitted[1]) == 100) {
                    ְҵ = "սʿ";
                } else if (Integer.parseInt(splitted[1]) == 200) {
                    ְҵ = "ħ��ʦ";
                } else if (Integer.parseInt(splitted[1]) == 300) {
                    ְҵ = "������";
                } else if (Integer.parseInt(splitted[1]) == 400) {
                    ְҵ = "����";
                } else if (Integer.parseInt(splitted[1]) == 500) {
                    ְҵ = "����";
                } else if (Integer.parseInt(splitted[1]) == 110) {
                    ְҵ = "����";
                } else if (Integer.parseInt(splitted[1]) == 111) {
                    ְҵ = "��ʿ";
                } else if (Integer.parseInt(splitted[1]) == 112) {
                    ְҵ = "Ӣ��";
                } else if (Integer.parseInt(splitted[1]) == 120) {
                    ְҵ = "׼��ʿ";
                } else if (Integer.parseInt(splitted[1]) == 121) {
                    ְҵ = "��ʿ";
                } else if (Integer.parseInt(splitted[1]) == 122) {
                    ְҵ = "ʥ��ʿ";
                } else if (Integer.parseInt(splitted[1]) == 130) {
                    ְҵ = "ǹսʿ";
                } else if (Integer.parseInt(splitted[1]) == 131) {
                    ְҵ = "����ʿ";
                } else if (Integer.parseInt(splitted[1]) == 132) {
                    ְҵ = "����ʿ";
                } else if (Integer.parseInt(splitted[1]) == 210) {
                    ְҵ = "�𶾷�ʦ";
                } else if (Integer.parseInt(splitted[1]) == 211) {
                    ְҵ = "����ʦ";
                } else if (Integer.parseInt(splitted[1]) == 212) {
                    ְҵ = "�𶾵�ʦ";
                } else if (Integer.parseInt(splitted[1]) == 220) {
                    ְҵ = "���׷�ʦ";
                } else if (Integer.parseInt(splitted[1]) == 221) {
                    ְҵ = "������ʦ";
                } else if (Integer.parseInt(splitted[1]) == 222) {
                    ְҵ = "���׵�ʦ";
                } else if (Integer.parseInt(splitted[1]) == 230) {
                    ְҵ = "��ʦ";
                } else if (Integer.parseInt(splitted[1]) == 231) {
                    ְҵ = "��ʦ";
                } else if (Integer.parseInt(splitted[1]) == 232) {
                    ְҵ = "����";
                } else if (Integer.parseInt(splitted[1]) == 310) {
                    ְҵ = "����";
                } else if (Integer.parseInt(splitted[1]) == 311) {
                    ְҵ = "����";
                } else if (Integer.parseInt(splitted[1]) == 312) {
                    ְҵ = "������";
                } else if (Integer.parseInt(splitted[1]) == 320) {
                    ְҵ = "������";
                } else if (Integer.parseInt(splitted[1]) == 321) {
                    ְҵ = "����";
                } else if (Integer.parseInt(splitted[1]) == 322) {
                    ְҵ = "����";
                } else if (Integer.parseInt(splitted[1]) == 410) {
                    ְҵ = "�̿�";
                } else if (Integer.parseInt(splitted[1]) == 411) {
                    ְҵ = "��Ӱ��";
                } else if (Integer.parseInt(splitted[1]) == 412) {
                    ְҵ = "��ʿ";
                } else if (Integer.parseInt(splitted[1]) == 420) {
                    ְҵ = "����";
                } else if (Integer.parseInt(splitted[1]) == 421) {
                    ְҵ = "���п�";
                } else if (Integer.parseInt(splitted[1]) == 422) {
                    ְҵ = "����";
                } else if (Integer.parseInt(splitted[1]) == 510) {
                    ְҵ = "ȭ��";
                } else if (Integer.parseInt(splitted[1]) == 511) {
                    ְҵ = "��ʿ";
                } else if (Integer.parseInt(splitted[1]) == 512) {
                    ְҵ = "���ӳ�";
                } else if (Integer.parseInt(splitted[1]) == 520) {
                    ְҵ = "��ǹ��";
                } else if (Integer.parseInt(splitted[1]) == 521) {
                    ְҵ = "��";
                } else if (Integer.parseInt(splitted[1]) == 522) {
                    ְҵ = "����";
                } else if (Integer.parseInt(splitted[1]) == 1000) {
                    ְҵ = "������";
                } else if (Integer.parseInt(splitted[1]) == 1100 || Integer.parseInt(splitted[1]) == 1110 || Integer.parseInt(splitted[1]) == 1111 || Integer.parseInt(splitted[1]) == 1112) {
                    ְҵ = "����ʿ";
                } else if (Integer.parseInt(splitted[1]) == 1200 || Integer.parseInt(splitted[1]) == 1210 || Integer.parseInt(splitted[1]) == 1211 || Integer.parseInt(splitted[1]) == 1212) {
                    ְҵ = "����ʿ";
                } else if (Integer.parseInt(splitted[1]) == 1300 || Integer.parseInt(splitted[1]) == 1310 || Integer.parseInt(splitted[1]) == 1311 || Integer.parseInt(splitted[1]) == 1312) {
                    ְҵ = "����ʹ��";
                } else if (Integer.parseInt(splitted[1]) == 1400 || Integer.parseInt(splitted[1]) == 1410 || Integer.parseInt(splitted[1]) == 1411 || Integer.parseInt(splitted[1]) == 1412) {
                    ְҵ = "ҹ����";
                } else if (Integer.parseInt(splitted[1]) == 1500 || Integer.parseInt(splitted[1]) == 1510 || Integer.parseInt(splitted[1]) == 1511 || Integer.parseInt(splitted[1]) == 1512) {
                    ְҵ = "��Ϯ��";
                } else if (Integer.parseInt(splitted[1]) == 2000) {
                    ְҵ = "սͯ";
                } else if (Integer.parseInt(splitted[1]) == 2100 || Integer.parseInt(splitted[1]) == 2110 || Integer.parseInt(splitted[1]) == 2111 || Integer.parseInt(splitted[1]) == 2112) {
                    ְҵ = "ս��";
                } else if (Integer.parseInt(splitted[1]) == 800 || Integer.parseInt(splitted[1]) == 900) {
                    ְҵ = "��ӪԱ";
                } else {
                    ְҵ = "δ֪";
                }
                c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
                c.getPlayer().dropMessage(5, "��ϲ��ɹ�תְΪ: " + ְҵ);
                c.getPlayer().dropMessage(5, "ʹ�� *ְҵ���� �ɲ鿴����ְҵ����ش���");
                c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]תְ��[" + ְҵ + "]"));
            }
            return 1;
        }
    }

    public static class ȫ������ extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter cm : cserv1.getPlayerStorage().getAllCharacters()) {
                    MapleItemInformationProvider.getInstance().getItemEffect(2210049).applyTo(c.getPlayer());
                    c.sendPacket(UIPacket.getStatusMsg(2210049));
                }
            }
            return 1;
        }
    }

    public static class ���� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            int range = -1;
            if (splitted.length < 2) {
                return 0;
            }
            String input = null;
            try {
                input = splitted[1];
            } catch (Exception ex) {
            }
            switch (splitted[1]) {
                case "m":
                    range = 0;
                    break;
                case "c":
                    range = 1;
                    break;
                case "w":
                default:
                    range = 2;
                    break;
            }
            if (range == -1) {
                range = 1;
            }
            if (range == 0) {
                c.getPlayer().getMap().disconnectAll();
            } else if (range == 1) {
                c.getChannelServer().getPlayerStorage().disconnectAll();
            } else if (range == 2) {
                for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                    cserv.getPlayerStorage().disconnectAll(true);
                }
            }
            String show = "";
            switch (range) {
                case 0:
                    show = "��ͼ";
                    break;
                case 1:
                    show = "�l��";
                    break;
                case 2:
                    show = "����";
                    break;
            }
            String msg = "[����ָ��] ����Ա " + c.getPlayer().getName() + "  �ж��� " + show + "���";
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���� [m|c|w] - ������Ҷ���").toString();

        }
    }

    public static class �������˾��� extends CommandExecute {//ExpEveryone

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                c.getPlayer().dropMessage(splitted[0] + " <������>");
                return 0;
            }
            int gain = Integer.parseInt(splitted[1]);
            int ret = 0;
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv.getPlayerStorage().getAllCharacters()) {
                    mch.gainExp(gain, true, true, true);
                    ret++;
                }
            }
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    mch.startMapEffect("" + MapleParty.�������� + "����Ա���� " + gain + " ��������ߵ�������ң�ף����Ŀ�����Ŀ���", 5120027);
                    FileoutputUtil.logToFile("����˼�¼��Ϣ/���߾��鷢�ͼ�¼.txt", "\r\n��ʱ��:" + CurrentReadable_Time() + "��������:" + c.getPlayer().getName() + "��������������� " + gain + " ���飬�˴��ܼƷ��� " + ret * gain + " ����");

                }
            }
            c.getPlayer().dropMessage(6, new StringBuilder().append("����ʹ�óɹ�����ǰ����: ").append(ret).append(" ����һ��: ").append(gain).append(" ���").append(" ���� ").append(" �ܼ�: ").append(ret * gain).toString());

            return 1;
        }
    }

    public static class �������˽�� extends CommandExecute {//mesoEveryone

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                c.getPlayer().dropMessage(splitted[0] + " <�����>");
                return 0;
            }
            int ret = 0;
            int gain = Integer.parseInt(splitted[1]);
            for (MapleCharacter mch : c.getChannelServer().getPlayerStorage().getAllCharactersThreadSafe()) {
                mch.gainMeso(gain, true);
                ret++;
            }
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    mch.startMapEffect("" + MapleParty.�������� + "����Ա���� " + gain + " ð�ձҸ����ߵ�������ң�ף����Ŀ�����Ŀ���", 5120027);
                    FileoutputUtil.logToFile("����˼�¼��Ϣ/���߽�ҷ��ͼ�¼.txt", "\r\n��ʱ��:" + CurrentReadable_Time() + "��������:" + c.getPlayer().getName() + "��������������� " + gain + " ��ң��˴��ܼƷ��� " + ret * gain + " ���");
                }
            }
            c.getPlayer().dropMessage(6, new StringBuilder().append("����ʹ�óɹ�����ǰ����: ").append(ret).append(" ����һ��: ").append(gain).append(" ð�ձ� ").append(" �ܼ�: ").append(ret * gain).toString());

            return 1;
        }
    }

    public static class �������˵�ȯ extends CommandExecute {//CashEveryone

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                c.getPlayer().dropMessage(splitted[0] + " <��ȯ>");
                return 0;
            }
            int ret = 0;
            int gain = Integer.parseInt(splitted[1]);
            for (MapleCharacter mch : c.getChannelServer().getPlayerStorage().getAllCharactersThreadSafe()) {
                mch.modifyCSPoints(1, gain);
                ret++;
            }
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    mch.startMapEffect("" + MapleParty.�������� + "����Ա���� " + gain + " ��ȯ�����ߵ�������ң�ף����Ŀ�����Ŀ���", 5120027);
                    FileoutputUtil.logToFile("����˼�¼��Ϣ/���ߵ�ȯ���ͼ�¼.txt", "\r\n��ʱ��:" + CurrentReadable_Time() + "��������:" + c.getPlayer().getName() + "��������������� " + gain + " ��ȯ���˴��ܼƷ��� " + ret * gain + " ��ȯ");

                }
            }
            c.getPlayer().dropMessage(6, new StringBuilder().append("����ʹ�óɹ�����ǰ����: ").append(ret).append(" ����һ��: ").append(gain).append(" ��ȯ ").append(" �ܼ�: ").append(ret * gain).toString());

            return 1;
        }
    }

    public static class �������˵���ȯ extends CommandExecute {//CashEveryone

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                c.getPlayer().dropMessage(splitted[0] + " <��ȯ>");
                return 0;
            }
            int ret = 0;
            int gain = Integer.parseInt(splitted[1]);
            for (MapleCharacter mch : c.getChannelServer().getPlayerStorage().getAllCharactersThreadSafe()) {
                mch.modifyCSPoints(2, gain);
                ret++;
            }
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    mch.startMapEffect("" + MapleParty.�������� + "����Ա���� " + gain + " ����ȯ�����ߵ�������ң�ף����Ŀ�����Ŀ���", 5120027);
                    FileoutputUtil.logToFile("����˼�¼��Ϣ/���ߵ���ȯ���ͼ�¼.txt", "\r\n��ʱ��:" + CurrentReadable_Time() + "��������:" + c.getPlayer().getName() + "��������������� " + gain + " ����ȯ���˴��ܼƷ��� " + ret * gain + " ��ȯ");

                }
            }
            c.getPlayer().dropMessage(6, new StringBuilder().append("����ʹ�óɹ�����ǰ����: ").append(ret).append(" ����һ��: ").append(gain).append(" ����ȯ ").append(" �ܼ�: ").append(ret * gain).toString());

            return 1;
        }
    }

    public static class ����ǰ��ͼ������ extends CommandExecute {//GainMap

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 1) {
                return 0;
            }
            Iterator i;
            MapleCharacter player = c.getPlayer();
            LinkedHashSet cmc = new LinkedHashSet(c.getPlayer().getMap().getCharacters());
            String type = splitted[1];
            int amount;
            if (type.equalsIgnoreCase("���")) {
                amount = Integer.parseInt(splitted[2]);

                for (i = cmc.iterator(); i.hasNext();) {
                    player = (MapleCharacter) i.next();
                    player.gainMeso(amount, true, true, true);
                    player.dropMessage("�Ѿ��յ� " + amount + " ���");
                }
            } else if (type.equalsIgnoreCase("����")) {
                amount = Integer.parseInt(splitted[2]);
                for (i = cmc.iterator(); i.hasNext();) {
                    player = (MapleCharacter) i.next();
                    player.gainExp(amount, true, true, false);
                    player.dropMessage("�Ѿ��յ� " + amount + " ����");
                }
            } else if (type.equalsIgnoreCase("��ȯ")) {
                amount = Integer.parseInt(splitted[2]);
                for (i = cmc.iterator(); i.hasNext();) {
                    player = (MapleCharacter) i.next();
                    player.modifyCSPoints(1, amount, true);
                    player.dropMessage("�Ѿ��յ���� " + amount + " ��");
                    String msg = "[����Ա��Ϣ] GM " + c.getPlayer().getName() + " �o�� " + player.getName() + " ��� " + amount + "��";
                    World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
                    FileoutputUtil.logToFile("����˼�¼��Ϣ/����Ա������Ϣ/������.txt", "\r\n " + FileoutputUtil.NowTime() + " GM " + c.getPlayer().getName() + " ���� " + player.getName() + " ��� " + amount + "��");
                }
            } else if (type.equalsIgnoreCase("����ȯ")) {
                amount = Integer.parseInt(splitted[2]);
                for (i = cmc.iterator(); i.hasNext();) {
                    player = (MapleCharacter) i.next();
                    player.modifyCSPoints(2, amount, true);
                    player.dropMessage("�Ѿ��յ�����ȯ " + amount + " ��");
                    String msg = "[����Ա��Ϣ] GM " + c.getPlayer().getName() + " �o�� " + player.getName() + " ��� " + amount + "��";
                    World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
                    FileoutputUtil.logToFile("����˼�¼��Ϣ/����Ա������Ϣ/������.txt", "\r\n " + FileoutputUtil.NowTime() + " GM " + c.getPlayer().getName() + " ���� " + player.getName() + " ��� " + amount + "��");
                }
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("*����ǰ��ͼ������ <���/����/��ȯ/����ȯ> <����> - ���磺*����ǰ��ͼ������ ��Ǯ 5000 ").toString();
        }
    }

    public static class ����� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            MapleCharacter victim;
            String name = splitted[1];
            int gain = Integer.parseInt(splitted[2]);
            int ch = World.Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage(6, "��ұ�������");
                return 0;
            }
            victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (victim == null) {
                c.getPlayer().dropMessage(5, "�Ҳ��� '" + name);
            } else {
                victim.gainMeso(gain, true);
                victim.dropMessage("[����]����Ա������ " + gain + " ���");
            }
            return 1;
        }
    }

    public static class ������ extends CommandExecute {//GainExp

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 3) {
                return 0;
            }
            MapleCharacter player = c.getPlayer();
            int amount = 0;
            String name = "";
            try {
                amount = Integer.parseInt(splitted[1]);
                name = splitted[2];
            } catch (Exception ex) {
                c.getPlayer().dropMessage("����Ҳ�����");
                return 1;
            }
            int ch = World.Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage("����Ҳ�����");
                return 1;
            }
            player = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (player == null) {
                c.getPlayer().dropMessage("����Ҳ�����");
                return 1;
            }
            player.gainExp(amount, true, true, false);
            player.dropMessage("[����]����Ա������ " + amount + " ����");
            return 1;
        }
    }

    public static class ����ȯ extends CommandExecute {//GainExp

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 3) {
                return 0;
            }
            MapleCharacter player = c.getPlayer();
            int amount = 0;
            String name = "";
            try {
                amount = Integer.parseInt(splitted[1]);
                name = splitted[2];
            } catch (Exception ex) {
                c.getPlayer().dropMessage("����Ҳ�����");
                return 1;
            }
            int ch = World.Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage("����Ҳ�����");
                return 1;
            }
            player = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (player == null) {
                c.getPlayer().dropMessage("����Ҳ�����");
                return 1;
            }
            player.modifyCSPoints(1, amount, true);
            player.dropMessage("[����]����Ա������ " + amount + " ��ȯ");
            return 1;
        }
    }

    public static class ������ȯ extends CommandExecute {//GainExp

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 3) {
                return 0;
            }
            MapleCharacter player = c.getPlayer();
            int amount = 0;
            String name = "";
            try {
                amount = Integer.parseInt(splitted[1]);
                name = splitted[2];
            } catch (Exception ex) {
                c.getPlayer().dropMessage("����Ҳ�����");
                return 1;
            }
            int ch = World.Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage("����Ҳ�����");
                return 1;
            }
            player = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (player == null) {
                c.getPlayer().dropMessage("����Ҳ�����");
                return 1;
            }
            player.modifyCSPoints(2, amount, true);
            player.dropMessage("[����]����Ա������ " + amount + " ����ȯ");
            return 1;
        }
    }

    public static class ����ͼ���˸���˵ extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            for (MapleCharacter victim : c.getPlayer().getMap().getCharactersThreadsafe()) {
                if (victim.getId() != c.getPlayer().getId()) {
                    victim.getMap().broadcastMessage(MaplePacketCreator.getChatText(victim.getId(), StringUtil.joinStringFrom(splitted, 1), victim.isGM(), 0));
                }
            }
            return 1;
        }
    }

    public static class ��Ƶ�����˸���˵ extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            for (MapleCharacter victim : c.getChannelServer().getPlayerStorage().getAllCharacters()) {
                if (victim.getId() != c.getPlayer().getId()) {
                    victim.getMap().broadcastMessage(MaplePacketCreator.getChatText(victim.getId(), StringUtil.joinStringFrom(splitted, 1), victim.isGM(), 0));
                }
            }
            return 1;
        }
    }

    public static class ���������˸���˵ extends CommandExecute {//SpeakWorld

        public int execute(MapleClient c, String splitted[]) {
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter victim : cserv.getPlayerStorage().getAllCharacters()) {
                    if (victim.getId() != c.getPlayer().getId()) {
                        victim.getMap().broadcastMessage(MaplePacketCreator.getChatText(victim.getId(), StringUtil.joinStringFrom(splitted, 1), victim.isGM(), 0));
                    }
                }
            }
            return 1;
        }
    }

    public static class ���� extends CommandExecute {//////UnbanIP

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            byte ret_ = MapleClient.unbanIPMacs(splitted[1]);
            if (ret_ == -2) {
                c.getPlayer().dropMessage(6, "[����IP] SQL ����.");
            } else if (ret_ == -1) {
                c.getPlayer().dropMessage(6, "[����IP] ��ɫ������.");
            } else if (ret_ == 0) {
                c.getPlayer().dropMessage(6, "[����IP] No IP or Mac with that character exists!");
            } else if (ret_ == 1) {
                c.getPlayer().dropMessage(6, "[����IP] IP��Mac�ѽ�������һ��.");
            } else if (ret_ == 2) {
                c.getPlayer().dropMessage(6, "[����IP] IP�Լ�Mac�ѳɹ�����.");
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("*����IP <�������> - �������").toString();
        }
    }

    public static class ���� extends CommandExecute {///////TempBan

        public int execute(MapleClient c, String splitted[]) {
            MapleCharacter victim;
            String name = splitted[1];
            int ch = World.Find.findChannel(name);
            if (ch <= 0) {
                return 0;
            }
            victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            final int reason = Integer.parseInt(splitted[2]);
            final int numDay = Integer.parseInt(splitted[3]);

            final Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, numDay);
            final DateFormat df = DateFormat.getInstance();

            if (victim == null) {
                c.getPlayer().dropMessage(6, "[tempban] �Ҳ���Ŀ���ɫ");

            } else {
                victim.tempban("��" + c.getPlayer().getName() + "��ʱ������", cal, reason, true);
                c.getPlayer().dropMessage(6, "[tempban] " + splitted[1] + " �ѳɹ�����ʱ������ " + df.format(cal.getTime()));
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!������� <�������> - ��ʱ�������").toString();
        }
    }

    public static class ɱ extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            MapleCharacter player = c.getPlayer();
            if (splitted.length < 2) {
                return 0;
            }
            MapleCharacter victim;
            for (int i = 1; i < splitted.length; i++) {
                String name = splitted[1];
                int ch = World.Find.findChannel(name);
                if (ch <= 0) {
                    return 0;
                }
                victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
                if (victim == null) {
                    c.getPlayer().dropMessage(6, "[��� " + splitted[i] + " ������.");
                } else if (player.allowedToTarget(victim)) {
                    victim.getStat().setHp((short) 0);
                    victim.getStat().setMp((short) 0);
                    victim.updateSingleStat(MapleStat.HP, 0);
                    victim.updateSingleStat(MapleStat.MP, 0);
                }
            }
            return 1;
        }
    }

    public static class ���輼�� extends CommandExecute {//GiveSkill

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 3) {
                return 0;
            }
            MapleCharacter victim;
            String name = splitted[1];
            int ch = World.Find.findChannel(name);
            if (ch <= 0) {
                return 0;
            }
            victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);

            ISkill skill = SkillFactory.getSkill(Integer.parseInt(splitted[2]));
            byte level = (byte) CommandProcessorUtil.getOptionalIntArg(splitted, 3, 1);
            byte masterlevel = (byte) CommandProcessorUtil.getOptionalIntArg(splitted, 4, 1);

            if (level > skill.getMaxLevel()) {
                level = skill.getMaxLevel();
            }
            victim.changeSkillLevel(skill, level, masterlevel);
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("*���輼�� <�������> <����ID> [���ܵȼ�] [�������ȼ�] - ���輼��").toString();
        }
    }

    public static class ���̵� extends CommandExecute {//Shop

        public int execute(MapleClient c, String splitted[]) {
            MapleShopFactory shop = MapleShopFactory.getInstance();
            int shopId;
            try {
                shopId = Integer.parseInt(splitted[1]);
            } catch (NumberFormatException ex) {
                return 0;
            }
            if (shop.getShop(shopId) != null) {
                shop.getShop(shopId).sendShop(c);
            } else {
                c.getPlayer().dropMessage(5, "���̵겻����");
            }
            return 1;
        }
    }

    public static class Ⱥ������ extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            MapleCharacter player = c.getPlayer();
            for (MapleCharacter mch : player.getMap().getCharacters()) {
                if (mch != null) {
                    mch.getStat().setHp(mch.getStat().getMaxHp());
                    mch.updateSingleStat(MapleStat.HP, mch.getStat().getMaxHp());
                    mch.getStat().setMp(mch.getStat().getMaxMp());
                    mch.updateSingleStat(MapleStat.MP, mch.getStat().getMaxMp());
                    mch.dispelDebuffs();
                }
            }
            return 1;
        }
    }

    public static class ɾ��NPC extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().getMap().resetNPCs();
            return 1;
        }
    }

    public static class ����C extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().spawnSavedPets();
            // c.sendPacket(LoginPacket.getLoginFailed(1));
            return 1;
        }
    }

    public static class ��Ϸ����ģʽ extends CommandExecute {//maxstats

        public int execute(MapleClient c, String splitted[]) {
            if (MapleParty.�˺���ʾ == 0) {
                MapleParty.����ģʽ = 1;
                c.getPlayer().dropMessage(5, "������Ϸ����ģʽ");
            } else {
                MapleParty.����ģʽ = 0;
                c.getPlayer().dropMessage(5, "�ر���Ϸ����ģʽ");
            }
            if (gui.Start.ConfigValuesMap.get("ָ��֪ͨ����") <= 0) {
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ������Ϸ����ģʽָ��"));
            }
            return 1;
        }
    }

    public static class ָ���ȫ extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().dropMessage(5, "��" + MapleParty.�������� + "����Աָ���ȫ��");
            c.getPlayer().dropMessage(5, "*�ڿ�   -   ��¼��ǰ�ڿ�ص�����");
            c.getPlayer().dropMessage(5, "*��ҩ   -   ��¼��ǰ��ҩ�ص�����");
            c.getPlayer().dropMessage(5, "*ף��   -   �������漼��");
            c.getPlayer().dropMessage(5, "*ˢ��   -   ˢ��һ��");
            c.getPlayer().dropMessage(5, "*�޵�   -   �޵�״̬");
            c.getPlayer().dropMessage(5, "*����   -   ����ͼ���й�����������ǰλ��");
            c.getPlayer().dropMessage(5, "*����   -   ��ֵ�ǰ��ͼ������Ʒ");
            c.getPlayer().dropMessage(5, "*���   -   ��ֵ�ǰ��ͼ���й�������");
            c.getPlayer().dropMessage(5, "*���2   -   �����ǰ��ͼ���й�����");
            c.getPlayer().dropMessage(5, "*��ͼ����   -   �鿴��ǰ��ͼ����");
            c.getPlayer().dropMessage(5, "*ְҵ����   -   �鿴����ְҵ���ְҵ����");
            c.getPlayer().dropMessage(5, "*�ҵ�λ��   -   �鿴��ǰλ������");
            c.getPlayer().dropMessage(5, "*�������   -   �鿴�����������");
            c.getPlayer().dropMessage(5, "*��������   -   ͳ��������������");
            c.getPlayer().dropMessage(5, "*������   -   �����м��ܼ���");
            c.getPlayer().dropMessage(5, "*������   -   ����������ֵ����");
            c.getPlayer().dropMessage(5, "*�������   -   �鿴�����������");
            c.getPlayer().dropMessage(5, "*��������   -   ������������������Լ�����λ��");
            c.getPlayer().dropMessage(5, "*�������IP   -   ��������ѱ������IP");
            c.getPlayer().dropMessage(5, "*������л�����   -   ��������ѱ�����Ļ�����");
            c.getPlayer().dropMessage(5, "*ˢ<�ո�>[��Ʒ����]   -   ˢ��ָ����Ʒ");
            c.getPlayer().dropMessage(5, "*��<�ո�>[��Ʒ����]   -   ����ָ����Ʒ");
            c.getPlayer().dropMessage(5, "*�ȼ�<�ո�>[�ȼ�]   -   ������ָ���ȼ�");
            c.getPlayer().dropMessage(5, "*����   -   ����һ��");
            c.getPlayer().dropMessage(5, "*תְ<�ո�>[ְҵ����]   -   תְָ��ְҵ");
            c.getPlayer().dropMessage(5, "*����<�ո�>[��ͼ����]   -   ת�͵�ָ����ͼ");
            c.getPlayer().dropMessage(5, "*����<�ո�>[�������]   -   ���ٵ�ָ��������ڵ�ͼ");
            c.getPlayer().dropMessage(5, "*���ŵ�ͼ<�ո�>[��ͼ����]   -   ���ŵ�ͼ");
            c.getPlayer().dropMessage(5, "*�رյ�ͼ<�ո�>[��ͼ����]   -   �رյ�ͼ");
            c.getPlayer().dropMessage(5, "*ˢ������<�ո�>[����]   -   ˢAP��");
            c.getPlayer().dropMessage(5, "*ˢ���ܵ�<�ո�>[����]   -   ˢSP��");
            c.getPlayer().dropMessage(5, "*�����<�ո�>[�������]   -   ��ָ����ҷ����");
            c.getPlayer().dropMessage(5, "*������<�ո�>[�������]   -   ��ָ����ҷ�����");
            c.getPlayer().dropMessage(5, "*����ȯ<�ո�>[�������]   -   ��ָ����ҷ���ȯ");
            c.getPlayer().dropMessage(5, "*������ȯ<�ո�>[�������]   -   ��ָ����ҷ�����ȯ");
            c.getPlayer().dropMessage(5, "*�������˾���<�ո�>[����]   -   �������˾���");
            c.getPlayer().dropMessage(5, "*�������˽��<�ո�>[����]   -   �������˽��");
            c.getPlayer().dropMessage(5, "*�������˵�ȯ<�ո�>[����]   -   �������˵�ȯ");
            c.getPlayer().dropMessage(5, "*����ǰ��ͼ������<�ո�>[����]<�ո�>[����]   -   ����:���,����,��ȯ,����ȯ");
            c.getPlayer().dropMessage(5, "*�������˵���ȯ<�ո�>[����]   -   �������˵���ȯ");
            c.getPlayer().dropMessage(5, "*���<�ո�>[�������]<�ո�>[���ԭ��]   -   �����ҵ�ָ��");
            c.getPlayer().dropMessage(5, "*���<�ո�>[�������]<�ո�>[���ԭ��]   -   �����ҵ�ָ��");
            c.getPlayer().dropMessage(5, "*����<�ո�>[�������]   -   ����IP��������");
            c.getPlayer().dropMessage(5, "*����<�ո�>[�������]   -   ����IP��������");
            c.getPlayer().dropMessage(5, "*����<�ո�>[��������]   -   m=��ͼ��c=Ƶ����w=����");
            c.getPlayer().dropMessage(5, "*���̵�<�ո�>[�̵�ID]   -   ��ָ�����̵�");
            c.getPlayer().dropMessage(5, "*�½�NPC<�ո�>[NPC����]   -   ���Լ���λ���½����ô��ڵ�NPC");
            c.getPlayer().dropMessage(5, "*�ٻ�����<�ո�>[�������]   -   ���Լ���λ���ٻ�ָ������");
            c.getPlayer().dropMessage(5, "*��������<�ո�>[���]<�ո�>[����]   -   ˢ����");
            c.getPlayer().dropMessage(5, "*���ÿ����Ϣ   -   ���ÿ����Ϣ���������޷����ʱ���");
            c.getPlayer().dropMessage(5, "*�޸ĵ�ǰƵ����ұ���<�ո�>[����]   -   �޸ĵ�ǰƵ���Ľ�ұ���");
            c.getPlayer().dropMessage(5, "*�޸ĵ�ǰƵ����Ʒ����<�ո�>[����]   -   �޸ĵ�ǰƵ������Ʒ����");
            c.getPlayer().dropMessage(5, "*�޸ĵ�ǰƵ�����鱶��<�ո�>[����]   -   �޸ĵ�ǰƵ���ľ��鱶��");
            c.getPlayer().dropMessage(5, "*����ͼ���˸���˵<�ո�>[����]   -   ���Ƶ�ͼ������Ҹ��Լ�˵��");
            c.getPlayer().dropMessage(5, "*��Ƶ�����˸���˵<�ո�>[����]   -   ����Ƶ��������Ҹ��Լ�˵��");
            c.getPlayer().dropMessage(5, "*���������˸���˵<�ո�>[����]   -   ����ȫ��������Ҹ��Լ�˵��");
            c.getPlayer().dropMessage(5, "*���輼��<�ո�>[�������]<�ո�>[����ID]<�ո�>[�������ڵȼ�]<�ո�>[�������ȼ�]   -   ������Ҽ���");
            c.getPlayer().dropMessage(5, "*ɱ<�ո�>[�������]   -   ɱ��ָ�����");
            c.getPlayer().dropMessage(5, "*Ⱥ������   -   ���Ƶ�ͼ�������");
            c.getPlayer().dropMessage(5, "*������̨(��С�Ĺرշ���˺�̨��ʹ�ú���)");
            c.getPlayer().dropMessage(5, "*��ͼ����(�����ͼ���٣���������)");
            return 1;
        }
    }

    public static class ������̨ extends CommandExecute {//Packet

        @Override
        public int execute(MapleClient c, String[] splitted) {
            CashShopServer();
            return 1;
        }
    }

    public static class ZEV extends CommandExecute {//Packet

        @Override
        public int execute(MapleClient c, String[] splitted) {
            DebugWindow debug = new DebugWindow();
            debug.setC(c);
            debug.setVisible(true);
            return 1;
        }
    }

    public static class ��ͼ���� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            int ��ͼ = c.getPlayer().getMapId();
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                cserv.getMapFactory().destroyMap(��ͼ, true);
                cserv.getMapFactory().HealMap(��ͼ);
            }
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:ָ����Ϣ - [����Ա:" + c.getPlayer().getName() + "]ʹ���˵�ͼ���չ���"));
            return 1;
        }
    }
}
