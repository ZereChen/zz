package client.messages.commands.a;

import client.ISkill;
import client.LoginCrypto;
import client.MapleCharacter;
import client.MapleCharacterUtil;
import client.MapleClient;
import client.MapleStat;
import client.SkillFactory;
import client.inventory.Equip;
import client.inventory.IItem;
import client.inventory.ItemFlag;
import client.inventory.MapleInventoryIdentifier;
import client.inventory.MapleInventoryType;
import client.inventory.MaplePet;
import client.inventory.MapleRing;
import client.messages.CommandProcessorUtil;
import client.messages.commands.CommandExecute;
import com.mysql.jdbc.PreparedStatement;
import constants.GameConstants;
import constants.ServerConstants;
import database.DatabaseConnection;
import handling.RecvPacketOpcode;
import handling.SendPacketOpcode;
import handling.channel.ChannelServer;
import handling.login.LoginServer;
import handling.login.handler.AutoRegister;
import handling.world.World;
import handling.world.CheaterData;
import handling.world.MapleParty;
import java.awt.Point;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map.Entry;
import scripting.EventManager;
import scripting.PortalScriptManager;
import scripting.ReactorScriptManager;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.MaplePortal;
import server.MapleShopFactory;
import server.ShutdownServer;
import server.Timer.EventTimer;
import server.events.MapleEvent;
import server.events.MapleEventType;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import server.life.MapleMonsterInformationProvider;
import server.life.MapleNPC;
import server.life.OverrideMonsterStats;
import server.life.PlayerNPC;
import server.maps.MapleMap;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import server.maps.MapleReactor;
import server.quest.MapleQuest;
import tools.ArrayMap;
import tools.MaplePacketCreator;
import tools.MockIOSession;
import tools.StringUtil;
import tools.packet.MobPacket;
import java.util.concurrent.ScheduledFuture;
import scripting.NPCScriptManager;
import handling.world.family.MapleFamily;
import handling.world.guild.MapleGuild;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import server.CashItemFactory;
import server.ServerProperties;
import server.events.MapleOxQuizFactory;
import tools.*;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.data.MaplePacketLittleEndianWriter;

/**
 *
 * @author ��Ϸ����Ա
 *
 *
 */
public class AdminCommand {

    /*public static PlayerGMRank getPlayerLevelRequired() {
        return PlayerGMRank.ADMIN;
    }*/
 /* public static class ���ش��� extends ReloadPortals {
    }
    public static class �����̵� extends ReloadShops {
    }
    public static class ���ر��� extends ReloadDrops {
    }*/
    // public static class ���ŵ�ͼ extends openmap {
    //  }
    // public static class �رյ�ͼ extends closemap {
    // }
    //  public static class �������˵���ͼ extends WarpPlayersTo {
    // }
    /* public static class �޸ĵ�ǰƵ����Ʒ���� extends DropRate {
    }
    
    public static class �޸ĵ�ǰƵ�����鱶�� extends ExpRate {
    }
    
    public static class �޸ĵ�ǰƵ����ұ��� extends MesoRate {
    }

    public static class ע�� extends register {
    }

    public static class ������ extends maxstats {
    }

    public static class ������ extends maxSkills {
    }

    public static class ��ȫ���˵������� extends WarpAllHere {
    }

    public static class �������˽�� extends mesoEveryone {
    }

    public static class �������˾��� extends ExpEveryone {
    }

    public static class �������˵�ȯ extends CashEveryone {
    }*/
    // public static class ����ȯ extends GainCash {
    // }
    // public static class ������ extends GainExp {
    // }
    //  public static class ����ǰ��ͼ������ extends GainMap {
    //  }
    // public static class ף�� extends buff {
    // }
    //  public static class �������� extends setRate {
    //   }
    // public static class ��ͼ���� extends WhereAmI {
    // }
    // public static class ˢ extends Item {
    // }
    // public static class �� extends Drop {
    // }
    // public static class ȫ������ extends HealMap {
    // }
    //  public static class ��� extends KillAll {
    //  }
    //  public static class ���� extends Fame {
    //  }
    // public static class ���� extends MobVac {
    // }
    //  public static class ����ذ� extends cleardrops {
    //  }
    //  public static class �ٻ����� extends Spawn {
    //  }
    //  public static class ��ʱ�� extends Clock {
    // }
    // public static class �Զ�ע�� extends autoreg {
    // }
    // public static class ������� extends mob {
    //  }
    // public static class ���״̬ extends BanStatus {
    //  }
    public static class Debug extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().setDebugMessage(!c.getPlayer().getDebugMessage());
            return 1;
        }
    }

    public static class ���״̬ extends CommandExecute {//BanStatus

        @Override
        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            String name = splitted[1];
            String mac = "";
            String ip = "";
            int acid = 0;
            boolean Systemban = false;
            boolean ACbanned = false;
            boolean IPbanned = false;
            boolean MACbanned = false;
            String reason = null;
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps;
                ps = (PreparedStatement) con.prepareStatement("select accountid from characters where name = ?");
                ps.setString(1, name);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        acid = rs.getInt("accountid");
                    }
                }
                ps = (PreparedStatement) con.prepareStatement("select banned, banreason, macs, Sessionip from accounts where id = ?");
                ps.setInt(1, acid);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Systemban = rs.getInt("banned") == 2;
                        ACbanned = rs.getInt("banned") == 1 || rs.getInt("banned") == 2;
                        reason = rs.getString("banreason");
                        mac = rs.getString("macs");
                        ip = rs.getString("Sessionip");
                    }
                }
                ps.close();
            } catch (Exception e) {
            }
            if (reason == null || reason == "") {
                reason = "?";
            }
            if (c.isBannedIP(ip)) {
                IPbanned = true;
            }
            if (c.isBannedMac(mac)) {
                MACbanned = true;
            }
            c.getPlayer().dropMessage("���[" + name + "] �ʺ�ID[" + acid + "]�Ƿ񱻷���: " + (ACbanned ? "��" : "��") + (Systemban ? "(ϵͳ�Զ�����)" : "") + ", ԭ��: " + reason);
            c.getPlayer().dropMessage("IP: " + ip + " �Ƿ��ڷ���IP����: " + (IPbanned ? "��" : "��"));
            c.getPlayer().dropMessage("MAC: " + mac + " �Ƿ��ڷ���MAC����: " + (MACbanned ? "��" : "��"));
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���״̬ <��ɫ��> - ��]").toString();
        }
    }

    public static class �������������� extends CommandExecute {//////setUserLimit

        public int execute(MapleClient c, String splitted[]) {
            int UserLimit = LoginServer.getUserLimit();
            try {
                UserLimit = Integer.parseInt(splitted[1]);
            } catch (Exception ex) {
            }
            LoginServer.setUserLimit(UserLimit);
            c.getPlayer().dropMessage("���������������Ѹ���Ϊ" + UserLimit);
            return 1;
        }
    }

    public static class �����Ӷ���� extends CommandExecute {///////SavePlayerShops

        public int execute(MapleClient c, String splitted[]) {
            for (handling.channel.ChannelServer cserv : handling.channel.ChannelServer.getAllInstances()) {
                cserv.closeAllMerchant();
            }
            c.getPlayer().dropMessage(6, "��Ӷ���˴������.");

            return 1;
        }
    }

    public static class �رշ���� extends CommandExecute {//////Shutdown

        private static Thread t = null;

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().dropMessage(6, "�رշ�����...");
            if (t == null || !t.isAlive()) {
                t = new Thread(server.ShutdownServer.getInstance());
                t.start();
            } else {
                c.getPlayer().dropMessage(6, "����ִ����...");
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�رշ���� - �رշ�����").toString();
        }
    }

    public static class ����ʱ�رշ���� extends CommandExecute {/////shutdowntime

        private static ScheduledFuture<?> ts = null;
        private int minutesLeft = 0;
        private static Thread t = null;

        public int execute(MapleClient c, String splitted[]) {

            if (splitted.length < 2) {
                return 0;
            }
            minutesLeft = Integer.parseInt(splitted[1]);
            c.getPlayer().dropMessage(6, "���������� " + minutesLeft + "���Ӻ�ر�. �뾡�ٹرվ������˲�����,����κ���ʧ�Ų�����");
            if (ts == null && (t == null || !t.isAlive())) {
                t = new Thread(ShutdownServer.getInstance());
                ts = EventTimer.getInstance().register(new Runnable() {

                    public void run() {
                        if (minutesLeft == 0) {
                            ShutdownServer.getInstance().run();
                            t.start();
                            ts.cancel(false);
                            return;
                        }
                        StringBuilder message = new StringBuilder();
                        message.append("[ð�յ�����] ���������� ");
                        message.append(minutesLeft);
                        message.append("���Ӻ�رգ����λ���������Ĺ�Ӷ���˲�����.");
                        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, message.toString()));
                        World.Broadcast.broadcastMessage(MaplePacketCreator.serverMessage(message.toString()));
                        for (ChannelServer cs : ChannelServer.getAllInstances()) {
                            cs.setServerMessage("���������� " + minutesLeft + " ���Ӻ���");
                        }
                        minutesLeft--;
                    }
                }, 60000);
            } else {
                c.getPlayer().dropMessage(6, new StringBuilder().append("�������ر�ʱ���޸�Ϊ ").append(minutesLeft).append("���Ӻ����Եȷ������ر�").toString());
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!����ʱ�رշ���� <����> - �رշ�����").toString();
        }
    }

    public static class �������н�ɫ��Ϣ extends CommandExecute {/////////////SaveAll

        private int p = 0;

        public int execute(MapleClient c, String splitted[]) {
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                List<MapleCharacter> chrs = cserv.getPlayerStorage().getAllCharactersThreadSafe();
                for (MapleCharacter chr : chrs) {
                    p++;
                    chr.saveToDB(false, false);
                }
            }
            c.getPlayer().dropMessage("[����] " + p + "��������ݱ��浽������.");
            p = 0;
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�������н�ɫ��Ϣ - �������н�ɫ�Y��").toString();
        }
    }

    public static class ��Ѫ���� extends CommandExecute {/////////LowHP

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().getStat().setHp((short) 1);
            c.getPlayer().getStat().setMp((short) 1);
            c.getPlayer().updateSingleStat(MapleStat.HP, 1);
            c.getPlayer().updateSingleStat(MapleStat.MP, 1);
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!��Ѫ���� - ��Ѫ������").toString();
        }
    }

    public static class �ָ�״̬ extends CommandExecute {/////////Heal

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().getStat().setHp(c.getPlayer().getStat().getCurrentMaxHp());
            c.getPlayer().getStat().setMp(c.getPlayer().getStat().getCurrentMaxMp());
            c.getPlayer().updateSingleStat(MapleStat.HP, c.getPlayer().getStat().getCurrentMaxHp());
            c.getPlayer().updateSingleStat(MapleStat.MP, c.getPlayer().getStat().getCurrentMaxMp());
            c.getPlayer().dispelDebuffs();
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�ָ� - ��Ѫ����").toString();
        }
    }

    public static class ����IP extends CommandExecute {//////UnbanIP

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            byte ret_ = MapleClient.unbanIPMacs(splitted[1]);
            if (ret_ == -2) {
                c.getPlayer().dropMessage(6, "[unbanip] SQL ����.");
            } else if (ret_ == -1) {
                c.getPlayer().dropMessage(6, "[unbanip] ��ɫ������.");
            } else if (ret_ == 0) {
                c.getPlayer().dropMessage(6, "[unbanip] No IP or Mac with that character exists!");
            } else if (ret_ == 1) {
                c.getPlayer().dropMessage(6, "[unbanip] IP��Mac�ѽ�������һ��.");
            } else if (ret_ == 2) {
                c.getPlayer().dropMessage(6, "[unbanip] IP�Լ�Mac�ѳɹ�����.");
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!����IP <�������> - �������").toString();
        }
    }

    public static class ������� extends CommandExecute {///////TempBan

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

    public static class ɱ��� extends CommandExecute {

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
                    c.getPlayer().dropMessage(6, "[kill] ��� " + splitted[i] + " ������.");
                } else if (player.allowedToTarget(victim)) {
                    victim.getStat().setHp((short) 0);
                    victim.getStat().setMp((short) 0);
                    victim.updateSingleStat(MapleStat.HP, 0);
                    victim.updateSingleStat(MapleStat.MP, 0);
                }
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!ɱ��� <�������1> <�������2> ...  - ɱ�����").toString();
        }
    }

    public static class ��ѧ���� extends CommandExecute {////////Skill

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            ISkill skill = SkillFactory.getSkill(Integer.parseInt(splitted[1]));
            byte level = (byte) CommandProcessorUtil.getOptionalIntArg(splitted, 2, 1);
            byte masterlevel = (byte) CommandProcessorUtil.getOptionalIntArg(splitted, 3, 1);
            if (level > skill.getMaxLevel()) {
                level = skill.getMaxLevel();
            }
            c.getPlayer().changeSkillLevel(skill, level, masterlevel);
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!��ѧ���� <����ID> [���ܵȼ�] [�������ȼ�] ...  - ѧϰ����").toString();
        }
    }

    public static class �������� extends CommandExecute {////////////Fame

        public int execute(MapleClient c, String splitted[]) {
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
            return new StringBuilder().append("!�������� <��ɫ����> <����> ...  - ����").toString();
        }
    }

    public static class �Զ�ע�� extends CommandExecute {////autoreg

        @Override
        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().dropMessage("Ŀǰ�Զ�ע���Ѿ� " + ServerConstants.ChangeAutoReg());
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!autoreg  - �Զ�ע�Ὺ��").toString();
        }
    }

    public static class Ⱥ������ extends CommandExecute {//HealMap

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

        public String getMessage() {
            return new StringBuilder().append("!Ⱥ������  - ������ͼ�����е���").toString();
        }
    }

    public static class �޵� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
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
            return new StringBuilder().append("!�޵�  - �޵п���").toString();
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
            return new StringBuilder().append("!���輼�� <�������> <����ID> [���ܵȼ�] [�������ȼ�] - ���輼��").toString();
        }
    }

    public static class ˢ���ܵ� extends CommandExecute {//SP

        public int execute(MapleClient c, String splitted[]) {
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
            c.getPlayer().setRemainingAp((short) CommandProcessorUtil.getOptionalIntArg(splitted, 1, 1));
            c.getPlayer().updateSingleStat(MapleStat.AVAILABLEAP, CommandProcessorUtil.getOptionalIntArg(splitted, 1, 1));
            String msg = "[����Ա��Ϣ][" + c.getPlayer().getName() + "] ʹ��ָ������AP " + splitted + "��";
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!ˢ������ [����] - ����AP").toString();
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
                c.getPlayer().dropMessage(5, "���̵�ID������");
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���̵� - �����̵�").toString();
        }
    }

    public static class �ؼ�ʱ�� extends CommandExecute {

        protected static ScheduledFuture<?> ts = null;

        public int execute(final MapleClient c, String splitted[]) {
            if (splitted.length < 1) {
                return 0;
            }
            if (ts != null) {
                ts.cancel(false);
                c.getPlayer().dropMessage(0, "ԭ���Ĺؼ�ʱ����ȡ��");
            }
            int minutesLeft;
            try {
                minutesLeft = Integer.parseInt(splitted[1]);
            } catch (NumberFormatException ex) {
                return 0;
            }
            if (minutesLeft > 0) {
                ts = EventTimer.getInstance().schedule(new Runnable() {

                    public void run() {
                        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                            for (MapleCharacter mch : cserv.getPlayerStorage().getAllCharacters()) {
                                if (!c.getPlayer().isGM()) {
                                    NPCScriptManager.getInstance().start(mch.getClient(), 9010010);
                                }
                            }
                        }
                        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "�ؼ�ʱ���Ѿ���ʼ��!!!"));
                        World.Broadcast.broadcastMessage(MaplePacketCreator.serverMessage("�ؼ�ʱ���Ѿ���ʼ��!!!"));
                        ts.cancel(false);
                        ts = null;
                    }
                }, minutesLeft * 60 * 1000); // ��ʮ��
                c.getPlayer().dropMessage(0, "�ؼ�ʱ��Ԥ�������");
            } else {
                c.getPlayer().dropMessage(0, "�趨��ʱ����� > 0��");
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�ؼ�ʱ�� <ʱ��:����> - �ؼ�ʱ��").toString();
        }
    }

    public static class ����ȯ extends CommandExecute {//GainCash

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
            String msg = "[����Ա��Ϣ][" + c.getPlayer().getName() + "] �o�� [" + player.getName() + "] [" + amount + "] ���";
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            FileoutputUtil.logToFile("����˼�¼��Ϣ/����Ա������Ϣ/������.txt", "\r\n " + FileoutputUtil.NowTime() + " GM " + c.getPlayer().getName() + " ���� " + player.getName() + " ��� " + amount + "��");
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!����ȯ <����> <���>").toString();
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
            //String msg = "[����Ա��Ϣ] GM " + c.getPlayer().getName() + " �o�� " + player.getName() + " ���� " + amount + "��";
            String msg = "[����Ա��Ϣ][" + c.getPlayer().getName() + "] �o�� [" + player.getName() + "] [" + amount + "] ����";
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            FileoutputUtil.logToFile("����˼�¼��Ϣ/����Ա������Ϣ/���辭��.txt", "\r\n " + FileoutputUtil.NowTime() + " GM " + c.getPlayer().getName() + " ���� " + player.getName() + " ���� " + amount + "��");
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!������ <����> <���>").toString();
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
            if (type.equalsIgnoreCase("��Ǯ")) {
                amount = Integer.parseInt(splitted[2]);

                for (i = cmc.iterator(); i.hasNext();) {
                    player = (MapleCharacter) i.next();
                    player.gainMeso(amount, true, true, true);
                }
            } else if (type.equalsIgnoreCase("����")) {
                amount = Integer.parseInt(splitted[2]);
                for (i = cmc.iterator(); i.hasNext();) {
                    player = (MapleCharacter) i.next();
                    player.gainExp(amount, true, true, false);
                }
            } else if (type.equalsIgnoreCase("��ȯ")) {
                amount = Integer.parseInt(splitted[2]);
                for (i = cmc.iterator(); i.hasNext();) {
                    player = (MapleCharacter) i.next();
                    player.modifyCSPoints(1, amount, true);
                    player.dropMessage("�Ѿ��յ����" + amount + "��");
                    String msg = "[����Ա��Ϣ] GM " + c.getPlayer().getName() + " �o�� " + player.getName() + " ��� " + amount + "��";
                    World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
                    FileoutputUtil.logToFile("����˼�¼��Ϣ/����Ա������Ϣ/������.txt", "\r\n " + FileoutputUtil.NowTime() + " GM " + c.getPlayer().getName() + " ���� " + player.getName() + " ��� " + amount + "��");
                }
            } else if (type.equalsIgnoreCase("����ȯ")) {
                amount = Integer.parseInt(splitted[2]);
                for (i = cmc.iterator(); i.hasNext();) {
                    player = (MapleCharacter) i.next();
                    player.modifyCSPoints(2, amount, true);
                    player.dropMessage("�Ѿ��յ�����ȯ" + amount + "��");
                    String msg = "[����Ա��Ϣ] GM " + c.getPlayer().getName() + " �o�� " + player.getName() + " ��� " + amount + "��";
                    World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
                    FileoutputUtil.logToFile("����˼�¼��Ϣ/����Ա������Ϣ/������.txt", "\r\n " + FileoutputUtil.NowTime() + " GM " + c.getPlayer().getName() + " ���� " + player.getName() + " ��� " + amount + "��");
                }
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("*����ǰ��ͼ������ <��Ǯ/����/��ȯ/����ȯ> <����> - ���磺*����ǰ��ͼ������ ��Ǯ 5000 ").toString();
        }
    }

    /* public static class GainMaplePoint extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 3) {
                return 0;
            }
            MapleCharacter player;
            int amount = Integer.parseInt(splitted[1]);
            String name = splitted[2];
            int ch = World.Find.findChannel(name);
            if (ch <= 0) {
                return 0;
            }
            player = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (player == null) {
                return 0;
            }
            player.modifyCSPoints(2, amount, true);
            String msg = "[����Ա��Ϣ] GM " + c.getPlayer().getName() + " ���� " + player.getName() + " ��Ҷ���� " + amount + "��";
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!gainmaplepoint <����> <���> - ȡ�÷�Ҷ����").toString();
        }
    }

    public static class GainPoint extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 3) {
                return 0;
            }
            MapleCharacter player;
            int amount = Integer.parseInt(splitted[1]);
            String name = splitted[2];
            int ch = World.Find.findChannel(name);
            if (ch <= 0) {
                return 0;
            }
            player = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (player == null) {
                return 0;
            }
            player.setPoints(player.getPoints() + amount);
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!gainpoint <����> <���> - ȡ��Point").toString();
        }
    }

    public static class GainVP extends GainPoint {
    }*/
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
//            if (c.getPlayer().getLevel() < 200) {
//                c.getPlayer().gainExp(GameConstants.getExpNeededForLevel(c.getPlayer().getLevel()) + 1, true, false, true);
//            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���� - �ȼ�����").toString();
        }
    }

    public static class �������� extends CommandExecute {//UnlockInv

        public int execute(MapleClient c, String splitted[]) {
            java.util.Map<IItem, MapleInventoryType> eqs = new ArrayMap<>();
            boolean add = false;
            if (splitted.length < 2 || splitted[1].equals("ȫ��")) {
                for (MapleInventoryType type : MapleInventoryType.values()) {
                    for (IItem item : c.getPlayer().getInventory(type)) {
                        if (ItemFlag.LOCK.check(item.getFlag())) {
                            item.setFlag((byte) (item.getFlag() - ItemFlag.LOCK.getValue()));
                            add = true;
                            c.getPlayer().reloadC();
                            c.getPlayer().dropMessage(5, "�Ѿ�����");
                            //c.sendPacket(MaplePacketCreator.updateSpecialItemUse(item, type.getType()));
                        }
                        if (ItemFlag.UNTRADEABLE.check(item.getFlag())) {
                            item.setFlag((byte) (item.getFlag() - ItemFlag.UNTRADEABLE.getValue()));
                            add = true;
                            c.getPlayer().reloadC();
                            c.getPlayer().dropMessage(5, "�Ѿ�����");
                            //c.sendPacket(MaplePacketCreator.updateSpecialItemUse(item, type.getType()));
                        }
                        if (add) {
                            eqs.put(item, type);
                        }
                        add = false;
                    }
                }
            } else if (splitted[1].equals("��װ������")) {
                for (IItem item : c.getPlayer().getInventory(MapleInventoryType.EQUIPPED)) {
                    if (ItemFlag.LOCK.check(item.getFlag())) {
                        item.setFlag((byte) (item.getFlag() - ItemFlag.LOCK.getValue()));
                        add = true;
                        c.getPlayer().reloadC();
                        c.getPlayer().dropMessage(5, "�Ѿ�����");
                        //c.sendPacket(MaplePacketCreator.updateSpecialItemUse(item, type.getType()));
                    }
                    if (ItemFlag.UNTRADEABLE.check(item.getFlag())) {
                        item.setFlag((byte) (item.getFlag() - ItemFlag.UNTRADEABLE.getValue()));
                        add = true;
                        c.getPlayer().reloadC();
                        c.getPlayer().dropMessage(5, "�ѽ����i");
                        //c.sendPacket(MaplePacketCreator.updateSpecialItemUse(item, type.getType()));
                    }
                    if (add) {
                        eqs.put(item, MapleInventoryType.EQUIP);
                    }
                    add = false;
                }
            } else if (splitted[1].equals("����")) {
                for (IItem item : c.getPlayer().getInventory(MapleInventoryType.EQUIP)) {
                    if (ItemFlag.LOCK.check(item.getFlag())) {
                        item.setFlag((byte) (item.getFlag() - ItemFlag.LOCK.getValue()));
                        add = true;
                        c.getPlayer().reloadC();
                        c.getPlayer().dropMessage(5, "�Ѿ�����");
                        //c.sendPacket(MaplePacketCreator.updateSpecialItemUse(item, type.getType()));
                    }
                    if (ItemFlag.UNTRADEABLE.check(item.getFlag())) {
                        item.setFlag((byte) (item.getFlag() - ItemFlag.UNTRADEABLE.getValue()));
                        add = true;
                        c.getPlayer().reloadC();
                        c.getPlayer().dropMessage(5, "�Ѿ�����");
                        //c.sendPacket(MaplePacketCreator.updateSpecialItemUse(item, type.getType()));
                    }
                    if (add) {
                        eqs.put(item, MapleInventoryType.EQUIP);
                    }
                    add = false;
                }
            } else if (splitted[1].equals("����")) {
                for (IItem item : c.getPlayer().getInventory(MapleInventoryType.USE)) {
                    if (ItemFlag.LOCK.check(item.getFlag())) {
                        item.setFlag((byte) (item.getFlag() - ItemFlag.LOCK.getValue()));
                        add = true;
                        c.getPlayer().reloadC();
                        c.getPlayer().dropMessage(5, "�Ѿ�����");
                        //c.sendPacket(MaplePacketCreator.updateSpecialItemUse(item, type.getType()));
                    }
                    if (ItemFlag.UNTRADEABLE.check(item.getFlag())) {
                        item.setFlag((byte) (item.getFlag() - ItemFlag.UNTRADEABLE.getValue()));
                        add = true;
                        c.getPlayer().reloadC();
                        c.getPlayer().dropMessage(5, "�Ѿ�����");
                        //c.sendPacket(MaplePacketCreator.updateSpecialItemUse(item, type.getType()));
                    }
                    if (add) {
                        eqs.put(item, MapleInventoryType.USE);
                    }
                    add = false;
                }
            } else if (splitted[1].equals("װ��")) {
                for (IItem item : c.getPlayer().getInventory(MapleInventoryType.SETUP)) {
                    if (ItemFlag.LOCK.check(item.getFlag())) {
                        item.setFlag((byte) (item.getFlag() - ItemFlag.LOCK.getValue()));
                        add = true;
                        c.getPlayer().reloadC();
                        c.getPlayer().dropMessage(5, "�Ѿ�����");
                        //c.sendPacket(MaplePacketCreator.updateSpecialItemUse(item, type.getType()));
                    }
                    if (ItemFlag.UNTRADEABLE.check(item.getFlag())) {
                        item.setFlag((byte) (item.getFlag() - ItemFlag.UNTRADEABLE.getValue()));
                        add = true;
                        c.getPlayer().reloadC();
                        c.getPlayer().dropMessage(5, "�Ѿ�����");
                        //c.sendPacket(MaplePacketCreator.updateSpecialItemUse(item, type.getType()));
                    }
                    if (add) {
                        eqs.put(item, MapleInventoryType.SETUP);
                    }
                    add = false;
                }
            } else if (splitted[1].equals("����")) {
                for (IItem item : c.getPlayer().getInventory(MapleInventoryType.ETC)) {
                    if (ItemFlag.LOCK.check(item.getFlag())) {
                        item.setFlag((byte) (item.getFlag() - ItemFlag.LOCK.getValue()));
                        add = true;
                        c.getPlayer().reloadC();
                        c.getPlayer().dropMessage(5, "�Ѿ�����");
                        //c.sendPacket(MaplePacketCreator.updateSpecialItemUse(item, type.getType()));
                    }
                    if (ItemFlag.UNTRADEABLE.check(item.getFlag())) {
                        item.setFlag((byte) (item.getFlag() - ItemFlag.UNTRADEABLE.getValue()));
                        add = true;
                        c.getPlayer().reloadC();
                        c.getPlayer().dropMessage(5, "�Ѿ�����");
                        //c.sendPacket(MaplePacketCreator.updateSpecialItemUse(item, type.getType()));
                    }
                    if (add) {
                        eqs.put(item, MapleInventoryType.ETC);
                    }
                    add = false;
                }
            } else if (splitted[1].equals("����")) {
                for (IItem item : c.getPlayer().getInventory(MapleInventoryType.CASH)) {
                    if (ItemFlag.LOCK.check(item.getFlag())) {
                        item.setFlag((byte) (item.getFlag() - ItemFlag.LOCK.getValue()));
                        add = true;
                        c.getPlayer().reloadC();
                        c.getPlayer().dropMessage(5, "�Ѿ�����");
                        //c.sendPacket(MaplePacketCreator.updateSpecialItemUse(item, type.getType()));
                    }
                    if (ItemFlag.UNTRADEABLE.check(item.getFlag())) {
                        item.setFlag((byte) (item.getFlag() - ItemFlag.UNTRADEABLE.getValue()));
                        add = true;
                        c.getPlayer().reloadC();
                        c.getPlayer().dropMessage(5, "�Ѿ�����");
                        //c.sendPacket(MaplePacketCreator.updateSpecialItemUse(item, type.getType()));
                    }
                    if (add) {
                        eqs.put(item, MapleInventoryType.CASH);
                    }
                    add = false;
                }
            } else {
                return 0;
            }

            for (Entry<IItem, MapleInventoryType> eq : eqs.entrySet()) {
                c.getPlayer().forceReAddItem_NoUpdate(eq.getKey().copy(), eq.getValue());
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�������� <ȫ��/��װ������/����/����/װ��/����/����> - ��������").toString();
        }
    }

    public static class ˢ extends CommandExecute {//Item

        public int execute(MapleClient c, String splitted[]) {
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

            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!ˢ <����ID> - ˢ������").toString();
        }
    }

    public static class ���Ĺ������� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length > 1) {
                StringBuilder sb = new StringBuilder();
                sb.append(StringUtil.joinStringFrom(splitted, 1));
                for (ChannelServer ch : ChannelServer.getAllInstances()) {
                    ch.setServerMessage(sb.toString());
                }
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverMessage(sb.toString()));
            } else {
                return 0;
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���Ĺ������� ѶϢ - �����Ϸ��Sɫ����").toString();
        }
    }

    public static class ���� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length > 1) {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                sb.append(c.getPlayer().getName());
                sb.append("] ");
                sb.append(StringUtil.joinStringFrom(splitted, 1));
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, sb.toString()));
            } else {
                return 0;
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���� ѶϢ - ����������").toString();
        }
    }

    public static class ��ȫ��������Ϣ extends CommandExecute {///////////Letter

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

    public static class ��� extends CommandExecute {/////Marry

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 3) {
                return 0;
            }
            int itemId = Integer.parseInt(splitted[2]);
            if (!GameConstants.isEffectRing(itemId)) {
                c.getPlayer().dropMessage(6, "����Ľ�ָID.");
            } else {
                MapleCharacter fff;
                String name = splitted[1];
                int ch = World.Find.findChannel(name);
                if (ch <= 0) {
                    c.getPlayer().dropMessage(6, "��ұ�������");
                    return 0;
                }
                fff = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
                if (fff == null) {
                    c.getPlayer().dropMessage(6, "��ұ�������");
                } else {
                    int[] ringID = {MapleInventoryIdentifier.getInstance(), MapleInventoryIdentifier.getInstance()};
                    try {
                        MapleCharacter[] chrz = {fff, c.getPlayer()};
                        for (int i = 0; i < chrz.length; i++) {
                            Equip eq = (Equip) MapleItemInformationProvider.getInstance().getEquipById(itemId);
                            if (eq == null) {
                                c.getPlayer().dropMessage(6, "����Ľ�ָID.");
                                return 1;
                            } else {
                                eq.setUniqueId(ringID[i]);
                                MapleInventoryManipulator.addbyItem(chrz[i].getClient(), eq.copy());
                                chrz[i].dropMessage(6, "�ɹ���  " + chrz[i == 0 ? 1 : 0].getName() + " ���");
                            }
                        }
                        MapleRing.addToDB(itemId, c.getPlayer(), fff.getName(), fff.getId(), ringID);
                    } catch (SQLException e) {
                    }
                }
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!��� <�������> <��ָ����> - ���").toString();
        }
    }

    public static class �����Ʒ extends CommandExecute {///ItemCheck

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 3 || splitted[1] == null || splitted[1].equals("") || splitted[2] == null || splitted[2].equals("")) {
                return 0;
            } else {
                int item = Integer.parseInt(splitted[2]);
                MapleCharacter chr;
                String name = splitted[1];
                int ch = World.Find.findChannel(name);
                if (ch <= 0) {
                    c.getPlayer().dropMessage(6, "��ұ�������");
                    return 0;
                }
                chr = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);

                int itemamount = chr.getItemQuantity(item, true);
                if (itemamount > 0) {
                    c.getPlayer().dropMessage(6, chr.getName() + " �� " + itemamount + " (" + item + ").");
                } else {
                    c.getPlayer().dropMessage(6, chr.getName() + " ���]�� (" + item + ")");
                }
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�����Ʒ <�������> <��ƷID> - �����Ʒ").toString();
        }
    }

    public static class ���� extends CommandExecute {////////////MobVac

        public int execute(MapleClient c, String splitted[]) {
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

    public static class ���� extends CommandExecute {/////Song

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.musicChange(splitted[1]));
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���� - ��������").toString();
        }
    }

    public static class �����Զ�� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            final EventManager em = c.getChannelServer().getEventSM().getEventManager("AutomatedEvent");
            if (em != null) {
                em.scheduleRandomEvent();
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�����Զ�� - �����Զ��").toString();
        }
    }

    public static class ���ʼ extends CommandExecute {

        private static ScheduledFuture<?> ts = null;
        private int min = 1;

        public int execute(final MapleClient c, String splitted[]) {
            if (c.getChannelServer().getEvent() == c.getPlayer().getMapId()) {
                MapleEvent.setEvent(c.getChannelServer(), false);
                c.getPlayer().dropMessage(5, "�Ѿ��رջ��ڣ�����ʹ�� !���ʼ ��������");
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "�l��:" + c.getChannel() + "�Ŀǰ�Ѿ��رմ��ſڡ�"));
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.getClock(60));
                ts = EventTimer.getInstance().register(new Runnable() {

                    public void run() {
                        if (min == 0) {
                            MapleEvent.onStartEvent(c.getPlayer());
                            ts.cancel(false);
                            return;
                        }
                        min--;
                    }
                }, 60 * 1000);
                return 1;
            } else {
                c.getPlayer().dropMessage(5, "��������ʹ�� !ѡ�� �趨��ǰ�l���Ļ�����ڵ�ǰ�l�����ͼ��ʹ�á�");
                return 1;
            }
        }

        public String getMessage() {
            return new StringBuilder().append("!���ʼ - ���ʼ").toString();
        }
    }

    public static class �رջ��� extends CommandExecute {

        private static boolean tt = false;

        public int execute(MapleClient c, String splitted[]) {
            if (c.getChannelServer().getEvent() == c.getPlayer().getMapId()) {
                MapleEvent.setEvent(c.getChannelServer(), false);
                c.getPlayer().dropMessage(5, "�Ѿ��رջ��ڣ�����ʹ�� !���ʼ ��������");
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "�l��:" + c.getChannel() + "�Ŀǰ�Ѿ��رմ��ſڡ�"));
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.getClock(60));
                EventTimer.getInstance().register(new Runnable() {

                    public void run() {
                        �رջ���.tt = true;
                    }
                }, 60 * 1000);
                if (tt) {
                    MapleEvent.onStartEvent(c.getPlayer());
                }
                return 1;
            } else {
                c.getPlayer().dropMessage(5, "��������ʹ�� !ѡ�� �趨��ǰ�l���Ļ�����ڵ�ǰ�l�����ͼ��ʹ�á�");
                return 1;
            }
        }

        public String getMessage() {
            return new StringBuilder().append("!�رջ��� -�رջ���").toString();
        }
    }

    public static class ѡ�� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            final MapleEventType type = MapleEventType.getByString(splitted[1]);
            if (type == null) {
                final StringBuilder sb = new StringBuilder("Ŀǰ���ŵĻ��: ");
                for (MapleEventType t : MapleEventType.values()) {
                    sb.append(t.name()).append(",");
                }
                c.getPlayer().dropMessage(5, sb.toString().substring(0, sb.toString().length() - 1));
            }
            final String msg = MapleEvent.scheduleEvent(type, c.getChannelServer());
            if (msg.length() > 0) {
                c.getPlayer().dropMessage(5, msg);
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!ѡ�� - ѡ��").toString();
        }
    }

    public static class ������ extends CommandExecute {//////checkgas

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            MapleCharacter chrs;
            String name = splitted[1];
            int ch = World.Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage(6, "��ұ�������");
                return 0;
            }
            chrs = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (chrs == null) {
                c.getPlayer().dropMessage(5, "�Ҳ����ý�ɫ");
            } else {
                c.getPlayer().dropMessage(6, chrs.getName() + " �� " + chrs.getCSPoints(1) + " ����.");
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!������ <�������>").toString();
        }
    }

    public static class �Ƴ����� extends CommandExecute {//RemoveItem

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 3) {
                return 0;
            }
            MapleCharacter chr;
            String name = splitted[1];
            int id = Integer.parseInt(splitted[2]);
            int ch = World.Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage(6, "��ұ�������");
                return 0;
            }
            chr = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);

            if (chr == null) {
                c.getPlayer().dropMessage(6, "����Ҳ�������");
            } else {
                chr.removeAll(id);
                c.getPlayer().dropMessage(6, "����IDΪ " + id + " �ĵ����Ѿ��� " + name + " ���ϱ��Ƴ���");
            }
            return 1;

        }

        public String getMessage() {
            return new StringBuilder().append("!�Ƴ� <��ɫ����> <��ƷID> - �Ƴ�������ϵĵ���").toString();
        }
    }

//    public static class LockItem extends CommandExecute {
//
//        public int execute(MapleClient c, String splitted[]) {
//            if (splitted.length < 3) {
//                return 0;
//            }
//            MapleCharacter chr = c.getChannelServer().getPlayerStorage().getCharacterByName(splitted[1]);
//            if (chr == null) {
//                c.getPlayer().dropMessage(6, "����Ҳ�������");
//            } else {
//                int itemid = Integer.parseInt(splitted[2]);
//                MapleInventoryType type = GameConstants.getInventoryType(itemid);
//                for (IItem item : chr.getInventory(type).listById(itemid)) {
//                    item.setFlag((byte) (item.getFlag() | ItemFlag.LOCK.getValue()));
//                    chr.getClient().sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(ModifyInventory.Types.UPDATE, item)));
//                }
//                if (type == MapleInventoryType.EQUIP) {
//                    type = MapleInventoryType.EQUIPPED;
//                    for (IItem item : chr.getInventory(type).listById(itemid)) {
//                        item.setFlag((byte) (item.getFlag() | ItemFlag.LOCK.getValue()));
//                        chr.getClient().sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(ModifyInventory.Types.UPDATE, item)));
//                    }
//                }
//                c.getPlayer().dropMessage(6, "��� " + splitted[1] + "��������IDΪ " + splitted[2] + " �ĵ����Ѿ���������");
//            }
//            return 1;
//        }
//
//        public String getMessage() {
//            return new StringBuilder().append("!lockitem <��ɫ����> <��ƷID> - ����������ϵĵ���").toString();
//        }
//    }
    public static class ɱ������ extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            for (MapleCharacter map : c.getPlayer().getMap().getCharactersThreadsafe()) {
                if (map != null && !map.isGM()) {
                    map.getStat().setHp((short) 0);
                    map.getStat().setMp((short) 0);
                    map.updateSingleStat(MapleStat.HP, 0);
                    map.updateSingleStat(MapleStat.MP, 0);
                }
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!ɱ������ - ɱ���������").toString();
        }
    }

    public static class ������ҷ����� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            MapleCharacter victim = null;
            if (splitted.length >= 2) {
                victim = c.getChannelServer().getPlayerStorage().getCharacterByName(splitted[1]);
            }
            try {
                World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(3, victim == null ? c.getChannel() : victim.getClient().getChannel(), victim == null ? splitted[1] : victim.getName() + " : " + StringUtil.joinStringFrom(splitted, 2), true));
            } catch (Exception e) {
                return 0;
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!������ҷ����� [�������] <ѶϢ> ").toString();
        }
    }

    public static class �������˵�� extends CommandExecute {///Speak

        public int execute(MapleClient c, String splitted[]) {
            MapleCharacter victim;
            String name = splitted[1];
            int ch = World.Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage(6, "��ұ�������");
                return 0;
            }
            victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);

            if (victim == null) {
                c.getPlayer().dropMessage(5, "�Ҳ��� '" + splitted[1]);
                return 0;
            } else {
                victim.getMap().broadcastMessage(MaplePacketCreator.getChatText(victim.getId(), StringUtil.joinStringFrom(splitted, 2), victim.isGM(), 0));
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�������˵�� <�������> <ѶϢ>").toString();
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

        public String getMessage() {
            return new StringBuilder().append("!����ͼ���˸���˵ <ѶϢ> - ��Ŀǰ��ͼ���з�����Ϣ").toString();
        }
    }

    public static class ��Ƶ���˸���˵ extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            for (MapleCharacter victim : c.getChannelServer().getPlayerStorage().getAllCharacters()) {
                if (victim.getId() != c.getPlayer().getId()) {
                    victim.getMap().broadcastMessage(MaplePacketCreator.getChatText(victim.getId(), StringUtil.joinStringFrom(splitted, 1), victim.isGM(), 0));
                }
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!��Ƶ���˸���˵ <ѶϢ> - ��ĿǰƵ�����з�����Ϣ").toString();
        }
    }

    public static class ȫ�����˸���˵ extends CommandExecute {//SpeakWorld

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

        public String getMessage() {
            return new StringBuilder().append("!ȫ�����˸���˵ <ѶϢ> - ��Ŀǰ���������д�����Ϣ").toString();
        }
    }


    /* public static class Disease extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 3) {
                //   c.getPlayer().dropMessage(6, "");
                return 0;
            }
            int type;
            MapleDisease dis;
            if (splitted[1].equalsIgnoreCase("SEAL")) {
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
            dis = MapleDisease.getBySkill(type);
            if (splitted.length == 4) {
                MapleCharacter victim;
                String name = splitted[2];
                int ch = World.Find.findChannel(name);
                if (ch <= 0) {
                    c.getPlayer().dropMessage(6, "��ұ�������");
                    return 0;
                }
                victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);

                if (victim == null) {
                    c.getPlayer().dropMessage(5, "�Ҳ��������");
                } else {
                    victim.setChair(0);
                    victim.getClient().sendPacket(MaplePacketCreator.cancelChair(-1));
                    victim.getMap().broadcastMessage(victim, MaplePacketCreator.showChair(c.getPlayer().getId(), 0), false);
                    victim.giveDebuff(dis, MobSkillFactory.getMobSkill(type, CommandProcessorUtil.getOptionalIntArg(splitted, 3, 1)));
                }
            } else {
                for (MapleCharacter victim : c.getPlayer().getMap().getCharactersThreadsafe()) {
                    victim.setChair(0);
                    victim.getClient().sendPacket(MaplePacketCreator.cancelChair(-1));
                    victim.getMap().broadcastMessage(victim, MaplePacketCreator.showChair(c.getPlayer().getId(), 0), false);
                    victim.giveDebuff(dis, MobSkillFactory.getMobSkill(type, CommandProcessorUtil.getOptionalIntArg(splitted, 2, 1)));
                }
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!disease <SEAL/DARKNESS/WEAKEN/STUN/CURSE/POISON/SLOW/SEDUCE/REVERSE/ZOMBIFY/POTION/SHADOW/BLIND/FREEZE> [��ɫ����] <״̬�ȼ�> - ���˵õ�����״̬").toString();
        }
    }*/
    public static class ������Ϣ extends CommandExecute {////////sendallnote

        public int execute(MapleClient c, String splitted[]) {

            if (splitted.length >= 1) {
                String text = StringUtil.joinStringFrom(splitted, 1);
                for (MapleCharacter mch : c.getChannelServer().getPlayerStorage().getAllCharacters()) {
                    c.getPlayer().sendNote(mch.getName(), text);
                    World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]��������Ϣ" + StringUtil.joinStringFrom(splitted, 1) + ""));

                }
            } else {
                return 0;
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!������Ϣ <����> ").toString();
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
                String msg = "[GM ����] GM " + c.getPlayer().getName() + " ���� " + victim.getName() + " ��� " + gain + "��";
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!����� <����> <����> - �o��ҽ��").toString();
        }
    }

    public static class ��¡ extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().cloneLook();
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

    /* public static class ��¼�����Ѷ extends CommandExecute {///////////monitor

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            MapleCharacter target = c.getChannelServer().getPlayerStorage().getCharacterByName(splitted[1]);
            if (target != null) {
                if (target.getClient().isMonitored()) {
                    target.getClient().setMonitored(false);
                    c.getPlayer().dropMessage(5, "Not monitoring " + target.getName() + " anymore.");
                } else {
                    target.getClient().setMonitored(true);
                    c.getPlayer().dropMessage(5, "Monitoring " + target.getName() + ".");
                }
            } else {
                c.getPlayer().dropMessage(5, "�Ҳ��������");
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("��¼�����Ѷ <���> - ��¼�����Ѷ").toString();
        }
    }*/
    public static class ���� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (c.getPlayer().getMap().getPermanentWeather() > 0) {
                c.getPlayer().getMap().setPermanentWeather(0);
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.removeMapEffect());
                c.getPlayer().dropMessage(5, "��ͼ�����ѱ�����.");
            } else {
                final int weather = CommandProcessorUtil.getOptionalIntArg(splitted, 1, 5120000);
                if (!MapleItemInformationProvider.getInstance().itemExists(weather) || weather / 10000 != 512) {
                    c.getPlayer().dropMessage(5, "��Ч��ID.");
                } else {
                    c.getPlayer().getMap().setPermanentWeather(weather);
                    c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.startMapEffect("", weather, false));
                    c.getPlayer().dropMessage(5, "��ͼ����������.");
                }
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���� - �趨����").toString();

        }
    }

    public static class �鿴��ɫ״̬ extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {

            if (splitted.length < 2) {
                return 0;
            }
            final StringBuilder builder = new StringBuilder();
            MapleCharacter other;
            String name = splitted[1];
            int ch = World.Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage(6, "��ұ�������");
                return 0;
            }
            other = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);

            if (other == null) {
                builder.append("��ɫ������");
                c.getPlayer().dropMessage(6, builder.toString());
            } else {
                if (other.getClient().getLastPing() <= 0) {
                    other.getClient().sendPing();
                }
                builder.append(MapleClient.getLogMessage(other, ""));
                builder.append(" �� ").append(other.getPosition().x);
                builder.append(" /").append(other.getPosition().y);

                builder.append(" || HP : ");
                builder.append(other.getStat().getHp());
                builder.append(" /");
                builder.append(other.getStat().getCurrentMaxHp());

                builder.append(" || MP : ");
                builder.append(other.getStat().getMp());
                builder.append(" /");
                builder.append(other.getStat().getCurrentMaxMp());

                builder.append(" || �������� : ");
                builder.append(other.getStat().getTotalWatk());
                builder.append(" || ħ�������� : ");
                builder.append(other.getStat().getTotalMagic());
                // builder.append(" || ��߹��� : ");
                //builder.append(other.getStat().getCurrentMaxBaseDamage());
                // builder.append(" || ����%�� : ");
                // builder.append(other.getStat().dam_r);
                // builder.append(" || BOSS����%�� : ");
                //  builder.append(other.getStat().bossdam_r);

                builder.append(" || ���� : ");
                builder.append(other.getStat().getStr());
                builder.append(" || ���� : ");
                builder.append(other.getStat().getDex());
                builder.append(" || ���� : ");
                builder.append(other.getStat().getInt());
                builder.append(" || ���\ : ");
                builder.append(other.getStat().getLuk());

                builder.append(" || ȫ������ : ");
                builder.append(other.getStat().getTotalStr());
                builder.append(" || ȫ������ : ");
                builder.append(other.getStat().getTotalDex());
                builder.append(" || ȫ������ : ");
                builder.append(other.getStat().getTotalInt());
                builder.append(" || ȫ�����\ : ");
                builder.append(other.getStat().getTotalLuk());

                builder.append(" || ����ֵ : ");
                builder.append(other.getExp());

                builder.append(" || ���״̬ : ");
                builder.append(other.getParty() != null);

                builder.append(" || ����״̬: ");
                builder.append(other.getTrade() != null);
                //builder.append(" || Latency: ");
                //builder.append(other.getClient().getLatency());
                builder.append(" || PING: ");
                builder.append(other.getClient().getLastPing());
                // builder.append(" || ����PONG: ");
                //  builder.append(other.getClient().getLastPong());
                builder.append(" || IP: ");
                builder.append(other.getClient().getSessionIPAddress());
                other.getClient().DebugMessage(builder);

                c.getPlayer().dropMessage(6, builder.toString());
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�鿴��ɫ״̬ <��ɫ����> - �鿴��ɫ״̬").toString();

        }
    }

    public static class �鿴�˵�ͼ������ extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            StringBuilder builder = new StringBuilder("�ڴ˵�ͼ�����: ");
            for (MapleCharacter chr : c.getPlayer().getMap().getCharactersThreadsafe()) {
                if (builder.length() > 150) { // wild guess :o
                    builder.setLength(builder.length() - 2);
                    c.getPlayer().dropMessage(6, builder.toString());
                    builder = new StringBuilder();
                }
                builder.append(MapleCharacterUtil.makeMapleReadable(chr.getName()));
                builder.append(", ");
            }
            builder.setLength(builder.length() - 2);
            c.getPlayer().dropMessage(6, builder.toString());
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�鿴�˵�ͼ������ - �鿴Ŀǰ��ͼ�ϵ����").toString();

        }
    }

    public static class ������ extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            List<CheaterData> cheaters = World.getCheaters();
            for (int x = cheaters.size() - 1; x >= 0; x--) {
                CheaterData cheater = cheaters.get(x);
                c.getPlayer().dropMessage(6, cheater.getInfo());
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!������ - �鿴���׽�ɫ").toString();

        }
    }

    public static class ���������� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            java.util.Map<Integer, Integer> connected = World.getConnected();
            StringBuilder conStr = new StringBuilder("�����ӵĿ͑���: ");
            boolean first = true;
            for (int i : connected.keySet()) {
                if (!first) {
                    conStr.append(", ");
                } else {
                    first = false;
                }
                if (i == 0) {
                    conStr.append("����: ");
                    conStr.append(connected.get(i));
                } else {
                    conStr.append("�l�� ");
                    conStr.append(i);
                    conStr.append(": ");
                    conStr.append(connected.get(i));
                }
            }
            c.getPlayer().dropMessage(6, conStr.toString());
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���������� - �鿴�����ߵĿ͑���").toString();

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

    public static class ǿ�ƿ�ʼ���� extends CommandExecute {//FStartQuest

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            MapleQuest.getInstance(Integer.parseInt(splitted[1])).forceStart(c.getPlayer(), Integer.parseInt(splitted[2]), splitted.length >= 4 ? splitted[3] : null);
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!ǿ�ƿ�ʼ���� <����ID> - ǿ�ƿ�ʼ����").toString();

        }
    }

    public static class ǿ��������� extends CommandExecute {//FCompleteQuest

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            MapleQuest.getInstance(Integer.parseInt(splitted[1])).forceComplete(c.getPlayer(), Integer.parseInt(splitted[2]));
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!ǿ��������� <����ID> - ǿ���������").toString();

        }
    }

    /* public static class FStartOther extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {

            MapleQuest.getInstance(Integer.parseInt(splitted[2])).forceStart(c.getChannelServer().getPlayerStorage().getCharacterByName(splitted[1]), Integer.parseInt(splitted[3]), splitted.length >= 4 ? splitted[4] : null);
            return 1;
        }

        public String δ֪() {
            return new StringBuilder().append("!δ֪1 - ��֪��ɶ").toString();

        }
    }

    public static class FCompleteOther extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            MapleQuest.getInstance(Integer.parseInt(splitted[2])).forceComplete(c.getChannelServer().getPlayerStorage().getCharacterByName(splitted[1]), Integer.parseInt(splitted[3]));
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!fcompleteother - ��֪��ɶ").toString();

        }
    }

    public static class NearestPortal extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            MaplePortal portal = c.getPlayer().getMap().findClosestSpawnpoint(c.getPlayer().getPosition());
            c.getPlayer().dropMessage(6, portal.getName() + " id: " + portal.getId() + " script: " + portal.getScriptName());

            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!nearestportal - ��֪��ɶ").toString();

        }
    }

    public static class SpawnDebug extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().dropMessage(6, c.getPlayer().getMap().spawnDebug());
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!spawndebug - debug�������").toString();

        }
    }

    public static class Threads extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            Thread[] threads = new Thread[Thread.activeCount()];
            Thread.enumerate(threads);
            String filter = "";
            if (splitted.length > 1) {
                filter = splitted[1];
            }
            for (int i = 0; i < threads.length; i++) {
                String tstring = threads[i].toString();
                if (tstring.toLowerCase().contains(filter.toLowerCase())) {
                    c.getPlayer().dropMessage(6, i + ": " + tstring);
                }
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!threads - �鿴Threads��Ѷ").toString();

        }
    }

    public static class ShowTrace extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            Thread[] threads = new Thread[Thread.activeCount()];
            Thread.enumerate(threads);
            Thread t = threads[Integer.parseInt(splitted[1])];
            c.getPlayer().dropMessage(6, t.toString() + ":");
            for (StackTraceElement elem : t.getStackTrace()) {
                c.getPlayer().dropMessage(6, elem.toString());
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!showtrace - show trace info").toString();

        }
    }*/
    public static class ˢ�� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            MapleCharacter player = c.getPlayer();
            c.sendPacket(MaplePacketCreator.getCharInfo(player));
            player.getMap().removePlayer(player);
            player.getMap().addPlayer(player);
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!ˢ�� - �ٵǳ��ٵ���").toString();

        }
    }

    /*public static class ToggleOffense extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;

            }
            try {
                CheatingOffense co = CheatingOffense.valueOf(splitted[1]);
                co.setEnabled(!co.isEnabled());
            } catch (IllegalArgumentException iae) {
                c.getPlayer().dropMessage(6, "Offense " + splitted[1] + " not found");
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!toggleoffense <Offense> - ������ر�CheatOffense").toString();

        }
    }*/
    public static class ���￪�� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().getMap().toggleDrops();
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���￪�� - ������رյ���").toString();

        }
    }

    public static class ���ȿ��� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            World.toggleMegaphoneMuteState();
            c.getPlayer().dropMessage(6, "�㲥�Ƿ���� : " + (c.getChannelServer().getMegaphoneMuteState() ? "��" : "��"));
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���ȿ��� - �������߹رչ㲥").toString();

        }
    }

    /* public static class SpawnReactor extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            int id = 0;
            try {
                id = Integer.parseInt(splitted[1]);
            } catch (Exception ex) {
            }
            MapleReactorStats reactorSt = MapleReactorFactory.getReactor(id);
            MapleReactor reactor = new MapleReactor(reactorSt, id);
            reactor.setDelay(-1);
            reactor.setPosition(c.getPlayer().getPosition());
            c.getPlayer().getMap().spawnReactor(reactor);
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!spawnreactor - ����Reactor").toString();

        }
    }

    public static class HReactor extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            c.getPlayer().getMap().getReactorByOid(Integer.parseInt(splitted[1])).hitReactor(c);
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!hitreactor - ����Reactor").toString();

        }
    }

    public static class DestroyReactor extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            MapleMap map = c.getPlayer().getMap();
            List<MapleMapObject> reactors = map.getMapObjectsInRange(c.getPlayer().getPosition(), Double.POSITIVE_INFINITY, Arrays.asList(MapleMapObjectType.REACTOR));
            if (splitted[1].equals("all")) {
                for (MapleMapObject reactorL : reactors) {
                    MapleReactor reactor2l = (MapleReactor) reactorL;
                    c.getPlayer().getMap().destroyReactor(reactor2l.getObjectId());
                }
            } else {
                c.getPlayer().getMap().destroyReactor(Integer.parseInt(splitted[1]));
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!drstroyreactor - �Ƴ�Reactor").toString();

        }
    }*/
    public static class ���÷�Ӧ�� extends CommandExecute {//ResetReactors

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().getMap().resetReactors();
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���÷�Ӧ�� - ���ô˵�ͼ���е�Reactor").toString();

        }
    }

    public static class ������Ӧ�� extends CommandExecute {//SetReactor

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            c.getPlayer().getMap().setReactorState(Byte.parseByte(splitted[1]));
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!������Ӧ�� - ����Reactor").toString();

        }
    }

    //public static class cleardrops extends RemoveDrops {
    // }
    public static class ���� extends CommandExecute {//RemoveDrops

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().dropMessage(5, "����˵�ͼ�� " + c.getPlayer().getMap().getNumItems() + " ��������");
            c.getPlayer().getMap().removeDrops();
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���� - �Ƴ����ϵ���Ʒ").toString();

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
                c.getPlayer().dropMessage(6, "��ǰƵ�����鱶�ʸ���Ϊ " + rate + "��");
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]����ǰƵ�����鱶�ʸ���Ϊ " + splitted[1] + " ��"));

            } else {
                return 0;
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�޸ĵ�ǰƵ�����鱶�� <����> - ���ľ��鱶��").toString();

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
                c.getPlayer().dropMessage(6, "��ǰƵ����Ʒ���ʸ���Ϊ " + rate + "��");
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]����ǰƵ�����ﱶ�ʸ���Ϊ " + splitted[1] + " ��"));

            } else {
                return 0;
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�޸ĵ�ǰƵ����Ʒ���� <����> - ���ĵ��䱶��").toString();

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
                c.getPlayer().dropMessage(6, "��ǰƵ����ұ��ʸ���Ϊ " + rate + " ��");
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]����ǰƵ����ұ��ʸ���Ϊ " + splitted[1] + " ��"));

            } else {
                return 0;
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�޸ĵ�ǰƵ����ұ��� <����> - ���Ľ�Ǯ����").toString();

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

    /* public static class GoTo extends CommandExecute {

        private static final HashMap<String, Integer> gotomaps = new HashMap<>();

        static {
            gotomaps.put("gmmap", 180000000);
            gotomaps.put("southperry", 2000000);
            gotomaps.put("amherst", 1010000);
            gotomaps.put("henesys", 100000000);
            gotomaps.put("ellinia", 101000000);
            gotomaps.put("perion", 102000000);
            gotomaps.put("kerning", 103000000);
            gotomaps.put("lithharbour", 104000000);
            gotomaps.put("sleepywood", 105040300);
            gotomaps.put("florina", 110000000);
            gotomaps.put("orbis", 200000000);
            gotomaps.put("happyville", 209000000);
            gotomaps.put("elnath", 211000000);
            gotomaps.put("ludibrium", 220000000);
            gotomaps.put("aquaroad", 230000000);
            gotomaps.put("leafre", 240000000);
            gotomaps.put("mulung", 250000000);
            gotomaps.put("herbtown", 251000000);
            gotomaps.put("omegasector", 221000000);
            gotomaps.put("koreanfolktown", 222000000);
            gotomaps.put("newleafcity", 600000000);
            gotomaps.put("sharenian", 990000000);
            gotomaps.put("pianus", 230040420);
            gotomaps.put("horntail", 240060200);
            gotomaps.put("chorntail", 240060201);
            gotomaps.put("mushmom", 100000005);
            gotomaps.put("griffey", 240020101);
            gotomaps.put("manon", 240020401);
            gotomaps.put("zakum", 280030000);
            gotomaps.put("czakum", 280030001);
            gotomaps.put("papulatus", 220080001);
            gotomaps.put("showatown", 801000000);
            gotomaps.put("zipangu", 800000000);
            gotomaps.put("ariant", 260000100);
            gotomaps.put("nautilus", 120000000);
            gotomaps.put("boatquay", 541000000);
            gotomaps.put("malaysia", 550000000);
            gotomaps.put("taiwan", 740000000);
            gotomaps.put("thailand", 500000000);
            gotomaps.put("erev", 130000000);
            gotomaps.put("ellinforest", 300000000);
            gotomaps.put("kampung", 551000000);
            gotomaps.put("singapore", 540000000);
            gotomaps.put("amoria", 680000000);
            gotomaps.put("timetemple", 270000000);
            gotomaps.put("pinkbean", 270050100);
            gotomaps.put("peachblossom", 700000000);
            gotomaps.put("fm", 910000000);
            gotomaps.put("freemarket", 910000000);
            gotomaps.put("oxquiz", 109020001);
            gotomaps.put("ola", 109030101);
            gotomaps.put("fitness", 109040000);
            gotomaps.put("snowball", 109060000);
            gotomaps.put("cashmap", 741010200);
            gotomaps.put("golden", 950100000);
            gotomaps.put("phantom", 610010000);
            gotomaps.put("cwk", 610030000);
            gotomaps.put("rien", 140000000);
        }

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                c.getPlayer().dropMessage(6, "Syntax: !goto <mapname>");
            } else if (gotomaps.containsKey(splitted[1])) {
                MapleMap target = c.getChannelServer().getMapFactory().getMap(gotomaps.get(splitted[1]));
                MaplePortal targetPortal = target.getPortal(0);
                c.getPlayer().changeMap(target, targetPortal);
            } else if (splitted[1].equals("locations")) {
                c.getPlayer().dropMessage(6, "Use !goto <location>. Locations are as follows:");
                StringBuilder sb = new StringBuilder();
                for (String s : gotomaps.keySet()) {
                    sb.append(s).append(", ");
                }
                c.getPlayer().dropMessage(6, sb.substring(0, sb.length() - 2));
            } else {
                c.getPlayer().dropMessage(6, "Invalid command ָ��Ҏ�t - Use !goto <location>. For a list of locations, use !goto locations.");
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!goto <����> - ��ĳ����ͼ").toString();

        }
    }*/
    public static class ��� extends CommandExecute {//KillAll

        public int execute(MapleClient c, String splitted[]) {
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
                map.killMonster(mob, c.getPlayer(), drop, false, (byte) 1);
            }
            c.getPlayer().dropMessage("���ܹ�ɱ�� " + monsters.size() + " ����");
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!��� [range] [mapid] - ɱ�����й���").toString();

        }
    }

    public static class ���ù��� extends CommandExecute {//ResetMobs

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().getMap().killAllMonsters(false);
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]�����˵�ͼ[" + splitted[1] + "]�Ĺ���"));

            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���ù��� - ���õ�ͼ�����й���").toString();

        }
    }

    public static class ���ָ������ extends CommandExecute {//KillMonster

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            MapleMap map = c.getPlayer().getMap();
            double range = Double.POSITIVE_INFINITY;
            MapleMonster mob;
            for (MapleMapObject monstermo : map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
                mob = (MapleMonster) monstermo;
                if (mob.getId() == Integer.parseInt(splitted[1])) {
                    mob.damage(c.getPlayer(), mob.getHp(), false);
                }
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���ָ������ <mobid> - ɱ����ͼ��ĳ������").toString();

        }
    }

    /* public static class KillMonsterByOID extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            MapleMap map = c.getPlayer().getMap();
            int targetId = Integer.parseInt(splitted[1]);
            MapleMonster monster = map.getMonsterByOid(targetId);
            if (monster != null) {
                map.killMonster(monster, c.getPlayer(), false, false, (byte) 1);
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!killmonsterbyoid <moboid> - ɱ����ͼ��ĳ������").toString();

        }
    }

    public static class HitMonsterByOID extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            MapleMap map = c.getPlayer().getMap();
            int targetId = Integer.parseInt(splitted[1]);
            int damage = Integer.parseInt(splitted[2]);
            MapleMonster monster = map.getMonsterByOid(targetId);
            if (monster != null) {
                map.broadcastMessage(MobPacket.damageMonster(targetId, damage));
                monster.damage(c.getPlayer(), damage, false);
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!hitmonsterbyoid <moboid> <damage> - ��ײ��ͼ��ĳ������").toString();

        }
    }*/
    public static class ��NPC extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            int npcId = 0;
            try {
                npcId = Integer.parseInt(splitted[1]);
            } catch (Exception ex) {
            }
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
                c.getPlayer().dropMessage(6, "�Ҳ����˴���Ϊ" + npcId + "��Npc");

            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!��NPC <npcid> - ���г�NPC").toString();
        }
    }

    public static class ɾ��NPC extends CommandExecute {////////RemoveNPCs

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().getMap().resetNPCs();
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!ɾ��NPC - �h������NPC").toString();
        }
    }

    public static class �鿴NPC extends CommandExecute {////LookNPCs

        public int execute(MapleClient c, String splitted[]) {
            for (MapleMapObject reactor1l : c.getPlayer().getMap().getAllNPCsThreadsafe()) {
                MapleNPC reactor2l = (MapleNPC) reactor1l;
                c.getPlayer().dropMessage(5, "NPC: oID: " + reactor2l.getObjectId() + " npcID: " + reactor2l.getId() + " Position: " + reactor2l.getPosition().toString() + " Name: " + reactor2l.getName());
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�鿴NPC - �鿴����NPC").toString();
        }
    }

    public static class �鿴��Ӧ�� extends CommandExecute {//////////LookReactors

        public int execute(MapleClient c, String splitted[]) {
            for (MapleMapObject reactor1l : c.getPlayer().getMap().getAllReactorsThreadsafe()) {
                MapleReactor reactor2l = (MapleReactor) reactor1l;
                c.getPlayer().dropMessage(5, "Reactor: oID: " + reactor2l.getObjectId() + " reactorID: " + reactor2l.getReactorId() + " Position: " + reactor2l.getPosition().toString() + " State: " + reactor2l.getState() + " Name: " + reactor2l.getName());
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�鿴��Ӧ��").toString();
        }
    }

    public static class �鿴���з�Ӧ�� extends CommandExecute {//////LookPortals

        public int execute(MapleClient c, String splitted[]) {
            for (MaplePortal portal : c.getPlayer().getMap().getPortals()) {
                c.getPlayer().dropMessage(5, "Portal: ID: " + portal.getId() + " script: " + portal.getScriptName() + " name: " + portal.getName() + " pos: " + portal.getPosition().x + "," + portal.getPosition().y + " target: " + portal.getTargetMapId() + " / " + portal.getTarget());
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�鿴���з�Ӧ��").toString();
        }
    }

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

    public static class ����npc extends CommandExecute {//////////MakeOfflineP

        public int execute(MapleClient c, String splitted[]) {
            try {
                c.getPlayer().dropMessage(6, "Making playerNPC...");
                MapleClient cs = new MapleClient(null, null, new MockIOSession());
                MapleCharacter chhr = MapleCharacter.loadCharFromDB(MapleCharacterUtil.getIdByName(splitted[1]), cs, false);
                if (chhr == null) {
                    c.getPlayer().dropMessage(6, splitted[1] + " does not exist");

                } else {
                    PlayerNPC npc = new PlayerNPC(chhr, Integer.parseInt(splitted[2]), c.getPlayer().getMap(), c.getPlayer());
                    npc.addToServer();
                    c.getPlayer().dropMessage(6, "Done");
                }
            } catch (Exception e) {
                c.getPlayer().dropMessage(6, "NPC failed... : " + e.getMessage());

            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!����npc <charname> <npcid> - ��������PNPC").toString();
        }
    }

    public static class �h��NPC extends CommandExecute {////////destroypnpc

        public int execute(MapleClient c, String splitted[]) {
            try {
                c.getPlayer().dropMessage(6, "Destroying playerNPC...");
                final MapleNPC npc = c.getPlayer().getMap().getNPCByOid(Integer.parseInt(splitted[1]));
                if (npc instanceof PlayerNPC) {
                    ((PlayerNPC) npc).destroy(true);
                    c.getPlayer().dropMessage(6, "Done");
                } else {
                    c.getPlayer().dropMessage(6, "!destroypnpc [objectid]");
                }
            } catch (Exception e) {
                c.getPlayer().dropMessage(6, "NPC failed... : " + e.getMessage());
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�h��NPC [objectid] - �h��PNPC").toString();
        }
    }

    public static class �ҵ�λ�� extends CommandExecute {//////MyPos

        public int execute(MapleClient c, String splitted[]) {
            Point pos = c.getPlayer().getPosition();
            c.getPlayer().dropMessage(6, "X: " + pos.x + " | Y: " + pos.y + " | RX0: " + (pos.x + 50) + " | RX1: " + (pos.x - 50) + " | FH: " + c.getPlayer().getFH() + "| CY:" + pos.y);
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�ҵ�λ��").toString();
        }
    }

    public static class �������� extends CommandExecute {//ReloadOps

        public int execute(MapleClient c, String splitted[]) {
            SendPacketOpcode.reloadValues();
            RecvPacketOpcode.reloadValues();
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ������������ָ��"));

            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�������� - ������������").toString();
        }
    }

    public static class ���±��� extends CommandExecute {//ReloadDrops

        public int execute(MapleClient c, String splitted[]) {
            MapleMonsterInformationProvider.getInstance().clearDrops();
            ReactorScriptManager.getInstance().clearDrops();
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ�������ر���ָ��"));

            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���±��� - ���±��ﱬ��").toString();
        }
    }

    public static class ���ش��� extends CommandExecute {//ReloadPortals

        public int execute(MapleClient c, String splitted[]) {
            PortalScriptManager.getInstance().clearScripts();
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ�������ش���ָ��"));

            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���ش��� - ������������").toString();
        }
    }

    public static class �����̵� extends CommandExecute {//ReloadShops

        public int execute(MapleClient c, String splitted[]) {
            MapleShopFactory.getInstance().clear();
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ���������̵�ָ��"));

            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�����̵� - �����̵���Ʒ").toString();
        }
    }

    /* public static class ReloadEvents extends CommandExecute {

        @Override
        public int execute(MapleClient c, String splitted[]) {
            for (ChannelServer instance : ChannelServer.getAllInstances()) {
                instance.reloadEvents();
            }
            return 1;
        }
    }*/
    public static class ������������ extends CommandExecute {//ReloadQuests

        public int execute(MapleClient c, String splitted[]) {
            MapleQuest.clearQuests();
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ������������ָ��"));

            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!������������ - ������������").toString();
        }
    }

    public static class �ٻ����� extends CommandExecute {//Spawn

        public int execute(MapleClient c, String splitted[]) {
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
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]�ٻ��˹���[" + splitted[1] + "]ָ��"));

            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�ٻ����� <����ID> <hp|exp|php||pexp = ?> - �ٻ�����").toString();

        }
    }

    public static class ����ʱ extends CommandExecute {///////clock

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.getClock(CommandProcessorUtil.getOptionalIntArg(splitted, 1, 60)));
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!����ʱ <time>").toString();
        }
    }

    public static class �������˵� extends CommandExecute {//////////WarpPlayersTo

        public int execute(MapleClient c, String splitted[]) {
            try {
                final MapleMap target = c.getChannelServer().getMapFactory().getMap(Integer.parseInt(splitted[1]));
                final MapleMap from = c.getPlayer().getMap();
                for (MapleCharacter chr : from.getCharactersThreadsafe()) {
                    chr.changeMap(target, target.getPortal(0));
                }
            } catch (Exception e) {
                return 0; //assume drunk GM
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�������˵� <maipid> ").toString();
        }
    }

    /* public static class LOLCastle extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length != 2) {
                return 0;
            }
            MapleMap target = c.getChannelServer().getEventSM().getEventManager("lolcastle").getInstance("lolcastle" + splitted[1]).getMapFactory().getMap(990000300, false, false);
            c.getPlayer().changeMap(target, target.getPortal(0));

            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!lolcastle level (level = 1-5) - ��֪����ɶ").toString();
        }
    }*/
    public static class ���� extends CommandExecute {//Map

        public int execute(MapleClient c, String splitted[]) {
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
                        // noop, assume the gm didn't know how many portals there are
                        c.getPlayer().dropMessage(5, "���͵����.");
                    } catch (NumberFormatException a) {
                        // noop, assume that the gm is drunk
                    }
                }
                if (targetPortal == null) {
                    targetPortal = target.getPortal(0);
                }
                c.getPlayer().changeMap(target, targetPortal);
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]���͵���ͼ[" + splitted[1] + "]"));

            } catch (Exception e) {
                c.getPlayer().dropMessage(5, "Error: " + e.getMessage());
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���� <mapid|charname> [portal] - ���͵�ĳ��ͼ/��").toString();
        }
    }

    /*  public static class StartProfiling extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            CPUSampler sampler = CPUSampler.getInstance();
            sampler.addIncluded("client");
            sampler.addIncluded("constants"); //or should we do Packages.constants etc.?
            sampler.addIncluded("database");
            sampler.addIncluded("handling");
            sampler.addIncluded("provider");
            sampler.addIncluded("scripting");
            sampler.addIncluded("server");
            sampler.addIncluded("tools");
            sampler.start();
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!startprofiling ��ʼ��¼JVM��Ѷ").toString();
        }
    }

    public static class StopProfiling extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            CPUSampler sampler = CPUSampler.getInstance();
            try {
                String filename = "odinprofile.txt";
                if (splitted.length > 1) {
                    filename = splitted[1];
                }
                File file = new File(filename);
                if (file.exists()) {
                    c.getPlayer().dropMessage(6, "The entered filename already exists, choose a different one");
                    return 1;
                }
                sampler.stop();
                try (FileWriter fw = new FileWriter(file)) {
                    sampler.save(fw, 1, 10);
                }
            } catch (IOException e) {
                System.err.println("Error saving profile" + e);
            }
            sampler.reset();
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!stopprofiling <filename> - ȡ����¼JVM��Ѷ�����浽����").toString();
        }
    }*/
    public static class ����ĳ����ͼ extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            MapleCharacter player = c.getPlayer();
            if (splitted.length < 2) {
                return 0;
            }
            boolean custMap = splitted.length >= 2;
            int mapid = custMap ? Integer.parseInt(splitted[1]) : player.getMapId();
            MapleMap map = custMap ? player.getClient().getChannelServer().getMapFactory().getMap(mapid) : player.getMap();
            if (player.getClient().getChannelServer().getMapFactory().destroyMap(mapid)) {
                MapleMap newMap = player.getClient().getChannelServer().getMapFactory().getMap(mapid);
                MaplePortal newPor = newMap.getPortal(0);
                LinkedHashSet<MapleCharacter> mcs = new LinkedHashSet<>(map.getCharacters()); // do NOT remove, fixing ConcurrentModificationEx.
                outerLoop:
                for (MapleCharacter m : mcs) {
                    for (int x = 0; x < 5; x++) {
                        try {
                            m.changeMap(newMap, newPor);
                            continue outerLoop;
                        } catch (Throwable t) {
                        }
                    }
                    player.dropMessage("Failed warping " + m.getName() + " to the new map. Skipping...");
                }
                player.dropMessage("��ͼˢ����ϣ��绹����NPC������ʹ�ô�����.");
                return 1;
            }
            player.dropMessage("Unsuccessful reset!");
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!����ĳ����ͼ <maipid> - ����ĳ����ͼ").toString();
        }
    }

    public static class ���ص�ͼ extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().getMap().respawn(true);
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ�������ص�ͼָ��"));

            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���ص�ͼ - ���������ͼ").toString();
        }
    }

    public static class ���õ�ͼ extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().getMap().resetFully();
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ�������õ�ͼָ��"));

            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���õ�ͼ - ���������ͼ").toString();
        }
    }

    public static class ���÷���� extends CommandExecute {

        public int execute(MapleClient c, String[] splitted) {
            for (ChannelServer instance : ChannelServer.getAllInstances()) {
                instance.reloadEvents();
            }
            MapleShopFactory.getInstance().clear();
            PortalScriptManager.getInstance().clearScripts();
            MapleItemInformationProvider.getInstance().load();

            CashItemFactory.getInstance().initialize();
            MapleMonsterInformationProvider.getInstance().clearDrops();

            MapleGuild.loadAll(); //(this); 
            MapleFamily.loadAll(); //(this); 
            MapleLifeFactory.loadQuestCounts();
            MapleQuest.initQuests();
            MapleOxQuizFactory.getInstance();
            ReactorScriptManager.getInstance().clearDrops();
            SendPacketOpcode.reloadValues();
            RecvPacketOpcode.reloadValues();
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ�������÷����ָ��"));

            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���÷���� - ����ȫ������").toString();
        }
    }

    public static class PNPC extends CommandExecute {

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
            } else {
                c.getPlayer().dropMessage(6, "���޴� Npc ");
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!����npc - ��������NPC").toString();
        }
    }

    public static class �Ҿ� extends CommandExecute {

        public int execute(MapleClient c, String[] splitted) {

            int npcId = Integer.parseInt(splitted[1]);
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
                try (com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("INSERT INTO �Ҿ߹��� (dataid, f, hide, fh, cy, rx0, rx1, type, x, y, mid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
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
                c.getPlayer().dropMessage(6, "δ�ܽ��Ҿ߱��浽���ݿ�");
            }
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                cserv.getMapFactory().getMap(c.getPlayer().getMapId()).addMapObject(npc);
                cserv.getMapFactory().getMap(c.getPlayer().getMapId()).broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
//                    c.getPlayer().getMap().addMapObject(npc);
//                    c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
            }
            c.getPlayer().dropMessage(6, "�����Ҿ߳ɹ��������Ҫɾ�����������ݿ⡶�Ҿ߹�����ɾ����������Ч");

            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�Ҿ� - �������üҾ�").toString();
        }
    }

    public static class ���������Ʒ extends CommandExecute {////////copyinv

        public int execute(MapleClient c, String[] splitted) {
            MapleCharacter player = c.getPlayer();
            MapleCharacter victim;
            int type = 1;
            if (splitted.length < 2) {
                return 0;
            }

            String name = splitted[1];
            int ch = World.Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage(6, "��ұ�������");
                return 0;
            }
            victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (victim == null) {
                player.dropMessage("�Ҳ��������");
                return 1;
            }
            try {
                type = Integer.parseInt(splitted[2]);
            } catch (Exception ex) {
            }
            if (type == 0) {
                for (client.inventory.IItem ii : victim.getInventory(MapleInventoryType.EQUIPPED).list()) {
                    client.inventory.IItem n = ii.copy();
                    player.getInventory(MapleInventoryType.EQUIP).addItem(n);
                }
                player.fakeRelog();
            } else {
                MapleInventoryType types;
                if (type == 1) {
                    types = MapleInventoryType.EQUIP;
                } else if (type == 2) {
                    types = MapleInventoryType.USE;
                } else if (type == 3) {
                    types = MapleInventoryType.ETC;
                } else if (type == 4) {
                    types = MapleInventoryType.SETUP;
                } else if (type == 5) {
                    types = MapleInventoryType.CASH;
                } else {
                    types = null;
                }
                if (types == null) {
                    c.getPlayer().dropMessage("��������");
                    return 1;
                }
                int[] equip = new int[97];
                for (int i = 1; i < 97; i++) {
                    if (victim.getInventory(types).getItem((short) i) != null) {
                        equip[i] = i;
                    }
                }
                for (int i = 0; i < equip.length; i++) {
                    if (equip[i] != 0) {
                        client.inventory.IItem n = victim.getInventory(types).getItem((short) equip[i]).copy();
                        player.getInventory(types).addItem(n);
                    }
                }
                player.fakeRelog();
            }
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!���������Ʒ ������� װ����λ(0 = װ���� 1=װ���� 2=������ 3=������ 4=װ���� 5=������)(Ԥ��װ����) - ������ҵ���").toString();
        }
    }

    public static class �Ƴ������Ʒ extends CommandExecute {//RemoveItemOff

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            com.mysql.jdbc.Connection dcon = (com.mysql.jdbc.Connection) DatabaseConnection.getConnection();
            try {
                int id = 0, quantity = 0;
                String name = splitted[2];
                com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) dcon.prepareStatement("select * from characters where name = ?");
                ps.setString(1, name);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        id = rs.getInt("id");
                    }
                }
                if (id == 0) {
                    c.getPlayer().dropMessage(5, "��ɫ���������Ͽ⡣");
                    return 0;
                }
                com.mysql.jdbc.PreparedStatement ps2 = (com.mysql.jdbc.PreparedStatement) dcon.prepareStatement("delete from inventoryitems WHERE itemid = ? and characterid = ?");
                ps2.setInt(1, Integer.parseInt(splitted[1]));
                ps2.setInt(2, id);
                ps2.executeUpdate();
                c.getPlayer().dropMessage(6, "����IDΪ " + splitted[1] + " �ĵ���" + quantity + "�Ѿ��� " + name + " ���ϱ��Ƴ���");
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]�Ƴ��� " + name + " ��������IDΪ " + splitted[1] + " �ĵ���" + quantity + ""));
                ps.close();
                ps2.close();
                return 1;
            } catch (SQLException e) {
                return 0;
            }
        }

        public String getMessage() {
            return new StringBuilder().append("!�Ƴ������Ʒ <��ƷID> <��ɫ���Q> - �Ƴ�������ϵĵ���").toString();
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
                    mch.startMapEffect("����Ա����" + gain + "��������ߵ�������ң�ף����Ŀ�����Ŀ���", 5120027);
                    FileoutputUtil.logToFile("����˼�¼��Ϣ/���߾��鷢�ͼ�¼.txt", "\r\n��ʱ��:" + CurrentReadable_Time() + "��������:" + c.getPlayer().getName() + "��������������� " + gain + " ���飬�˴��ܼƷ��� " + ret * gain + " ����");

                }
            }
            c.getPlayer().dropMessage(6, new StringBuilder().append("����ʹ�óɹ�����ǰ����: ").append(ret).append(" ����һ��: ").append(gain).append(" ���").append(" ���� ").append(" �ܼ�: ").append(ret * gain).toString());

            return 1;
        }
    }

    public static class �������˵�ȯ extends CommandExecute {//CashEveryone

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length > 2) {
                int type = Integer.parseInt(splitted[1]);
                int quantity = Integer.parseInt(splitted[2]);
                if (type == 1) {
                    type = 1;
                } else if (type == 2) {
                    type = 2;
                } else {
                    c.getPlayer().dropMessage(6, "�÷�: !�������˵�ȯ [�������1-2] [�������][1�ǵ��.2�ǵ��þ�]");
                    return 0;
                }
                if (quantity > 10000) {
                    quantity = 10000;
                }
                int ret = 0;
                for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv.getPlayerStorage().getAllCharacters()) {
                        mch.modifyCSPoints(type, quantity);
                        ret++;
                    }
                }
                String show = type == 1 ? "���" : "���þ�";
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.startMapEffect("����Ա����" + quantity + show + "�������ߵ�������ң�ף���Ŀ�����Ŀ���", 5120027);
                        FileoutputUtil.logToFile("����˼�¼��Ϣ/���ߵ�ȯ���ͼ�¼.txt", "\r\n��ʱ��:" + CurrentReadable_Time() + "��������:" + c.getPlayer().getName() + "��������������� " + quantity + show + " ��ȯ���˴��ܼƷ��� " + ret * quantity + " ��ȯ");

                    }
                }
                c.getPlayer().dropMessage(6, new StringBuilder().append("����ʹ�óɹ�����ǰ����: ").append(ret).append(" ����һ��: ").append(quantity).append(" ���").append(type == 1 ? "��ȯ " : " ����ȯ ").append(" �ܼ�: ").append(ret * quantity).toString());
            } else {
                c.getPlayer().dropMessage(6, "�÷�: !�������˵�� [�������1-2] [�������][1�ǵ��.2�ǵ��þ�]");
            }
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
                    mch.startMapEffect("����Ա����" + gain + "ð�ձҸ����ߵ�������ң�ף����Ŀ�����Ŀ���", 5120027);
                    FileoutputUtil.logToFile("����˼�¼��Ϣ/���߽�ҷ��ͼ�¼.txt", "\r\n��ʱ��:" + CurrentReadable_Time() + "��������:" + c.getPlayer().getName() + "��������������� " + gain + " ��ң��˴��ܼƷ��� " + ret * gain + " ���");

                }
            }
            c.getPlayer().dropMessage(6, new StringBuilder().append("����ʹ�óɹ�����ǰ����: ").append(ret).append(" ����һ��: ").append(gain).append(" ð�ձ� ").append(" �ܼ�: ").append(ret * gain).toString());

            return 1;
        }
    }

    public static class �������� extends CommandExecute {//setRate

        public int execute(MapleClient c, String splitted[]) {
            MapleCharacter player = c.getPlayer();
            MapleCharacter mc = player;
            if (splitted.length > 2) {
                int arg = Integer.parseInt(splitted[2]);
                int seconds = Integer.parseInt(splitted[3]);
                int mins = Integer.parseInt(splitted[4]);
                int hours = Integer.parseInt(splitted[5]);
                int time = seconds + (mins * 60) + (hours * 60 * 60);
                boolean bOk = true;
                if (splitted[1].equals("����")) {
                    if (arg <= Integer.parseInt(ServerProperties.getProperty("ZEV.Exp2"))) {
                        for (ChannelServer cservs : ChannelServer.getAllInstances()) {
                            cservs.setExpRate(arg);
                            cservs.broadcastPacket(MaplePacketCreator.serverNotice(6, "[" + MapleParty.�������� + "ϵͳ����]��" + "���������鱶���Ѿ��޸�Ϊ " + arg + " ��������ʱ��Ϊ " + hours + " Сʱ " + mins + " �� " + seconds + " �롣"));
                            //cservs.startMapEffect("["+MapleParty.��������+"ϵͳ����]��"+"���������鱶���Ѿ��޸�Ϊ " + arg + " ��������ʱ��Ϊ "+hours+" Сʱ "+mins+" �� "+seconds+" �롣", 5120027);
                            FileoutputUtil.logToFile("����˼�¼��Ϣ/��������.txt", "��ʱ�䣺" + CurrentReadable_Time() + "����" + c.getPlayer().getName() + "�� ������ ��" + arg + "�� ������������ʱ��Ϊ��" + hours + " Сʱ " + mins + " �� " + seconds + " �롿\r\n");
                        }
                    } else {
                        mc.dropMessage("���鱶��������� " + Integer.parseInt(ServerProperties.getProperty("ZEV.Exp2")) + " ��");
                    }
                } else if (splitted[1].equals("����")) {//drop
                    if (arg <= Integer.parseInt(ServerProperties.getProperty("ZEV.Drop2"))) {
                        for (ChannelServer cservs : ChannelServer.getAllInstances()) {
                            cservs.setDropRate(arg);
                            cservs.broadcastPacket(MaplePacketCreator.serverNotice(6, "[" + MapleParty.�������� + "ϵͳ����]��" + "���������ﱶ���Ѿ��޸�Ϊ " + arg + " ��������ʱ��Ϊ " + hours + " Сʱ " + mins + " �� " + seconds + " �롣"));
                            FileoutputUtil.logToFile("����˼�¼��Ϣ/��������.txt", "��ʱ�䣺" + CurrentReadable_Time() + "����" + c.getPlayer().getName() + "�� ������ ��" + arg + "�� ������������ʱ��Ϊ��" + hours + " Сʱ " + mins + " �� " + seconds + " �롿\r\n");
                        }
                    } else {
                        mc.dropMessage("���ﱶ��������� " + Integer.parseInt(ServerProperties.getProperty("ZEV.Drop2")) + " ��");
                    }
                } else if (splitted[1].equals("���")) {
                    if (arg <= Integer.parseInt(ServerProperties.getProperty("ZEV.Meso2"))) {
                        for (ChannelServer cservs : ChannelServer.getAllInstances()) {
                            cservs.setMesoRate(arg);
                            cservs.broadcastPacket(MaplePacketCreator.serverNotice(6, "[" + MapleParty.�������� + "ϵͳ����]��" + "��������ұ����Ѿ��޸�Ϊ " + arg + " ��������ʱ��Ϊ " + hours + " Сʱ " + mins + " �� " + seconds + " �롣"));
                            FileoutputUtil.logToFile("����˼�¼��Ϣ/������һ.txt", "��ʱ�䣺" + CurrentReadable_Time() + "����" + c.getPlayer().getName() + "�� ������ ��" + arg + "�� ����һ������ʱ��Ϊ��" + hours + " Сʱ " + mins + " �� " + seconds + " �롿\r\n");
                        }
                    } else {
                        mc.dropMessage("��ұ���������� " + Integer.parseInt(ServerProperties.getProperty("ZEV.Meso2")) + " ��");
                    }
                } else if (splitted[1].equalsIgnoreCase("boss����")) {
                    if (arg <= Integer.parseInt(ServerProperties.getProperty("ZEV.BDrop2"))) {
                        for (ChannelServer cservs : ChannelServer.getAllInstances()) {
                            cservs.setBossDropRate(arg);
                            cservs.broadcastPacket(MaplePacketCreator.serverNotice(6, "[" + MapleParty.�������� + "ϵͳ����]��" + "������BOSS���ﱶ���Ѿ��޸�Ϊ " + arg + " ��������ʱ��Ϊ " + hours + " Сʱ " + mins + " �� " + seconds + " �롣"));
                            FileoutputUtil.logToFile("����˼�¼��Ϣ/����BOSS����.txt", "��ʱ�䣺" + CurrentReadable_Time() + "����" + c.getPlayer().getName() + "�� ������ ��" + arg + "�� ��BOSS����������ʱ��Ϊ��" + hours + " Сʱ " + mins + " �� " + seconds + " �롿\r\n");
                        }
                    } else {
                        mc.dropMessage("BOSS���ﱶ��������� " + Integer.parseInt(ServerProperties.getProperty("ZEV.BDrop2")) + " ��");
                    }
                } else if (splitted[1].equals("���ﾭ��")) {//petexp
                    if (arg <= 5) {
//                        for (ChannelServer cservs : ChannelServer.getAllInstances()) {
//                            cservs.setPetExpRate(arg);
//                            cservs.broadcastPacket(MaplePacketCreator.serverNotice(6, "���ﾭ���Ѿ��ɸ��޸�Ϊ " + arg + "����ף�����Ϸ���ģ�"));
//                        }
                    } else {
                        mc.dropMessage("�����ѱ�ϵͳ���ơ�");
                    }
                } else {
                    bOk = false;
                }
                if (bOk) {
                    final String rate = splitted[1];
                    World.scheduleRateDelay(rate, time);
                } else {
                    mc.dropMessage("ʹ�÷���: !�������� <����|����|���|boss����> <����> <��> <��> <ʱ>");

                }
            } else {
                mc.dropMessage("ʹ�÷���: !�������� <����|����|���|boss����> <����> <��> <��> <ʱ>");
            }
            return 1;
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
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("!�������� ��������Ҵ��͵�����").toString();
        }
    }

    public static class ������ extends CommandExecute {//maxSkills

        public int execute(MapleClient c, String splitted[]) {
            MapleCharacter player = c.getPlayer();
            player.maxSkills();
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ����������ָ��"));

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

                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]��������Ʒ[" + splitted[1] + "]"));
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
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ����ף��ָ��"));

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
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ����������ָ��"));
            return 1;
        }
    }

    public static class ������ extends CommandExecute {//maxstats

        public int execute(MapleClient c, String splitted[]) {
            MapleCharacter player = c.getPlayer();
            player.getStat().setMaxHp((short) 2000);
            player.getStat().setMaxMp((short) 2000);
            /*player.getStat().setStr(200);
            player.getStat().setDex(200);
            player.getStat().setInt(200);
            player.getStat().setLuk(200);*/
            player.updateSingleStat(MapleStat.MAXHP, 2000);
            player.updateSingleStat(MapleStat.MAXMP, 2000);
            player.updateSingleStat(MapleStat.STR, 200);
            player.updateSingleStat(MapleStat.DEX, 200);
            player.updateSingleStat(MapleStat.INT, 200);
            player.updateSingleStat(MapleStat.LUK, 200);
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]ʹ���˵�����ָ��"));
            return 1;
        }
    }

    public static class ��ͼ���� extends CommandExecute {//WhereAmI

        @Override
        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().dropMessage(5, "Ŀǰ��ͼ " + c.getPlayer().getMap().getId() + "���� (" + String.valueOf(c.getPlayer().getPosition().x) + " , " + String.valueOf(c.getPlayer().getPosition().y) + ")");
            return 1;
        }
    }

    public static class ���ͷ�� extends CommandExecute {//Packet

        @Override
        public int execute(MapleClient c, String[] splitted) {
            final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
            int packetheader = Integer.parseInt(splitted[1]);
            String packet_in = " 00 00 00 00 00 00 00 00 00 ";
            if (splitted.length > 2) {
                packet_in = StringUtil.joinStringFrom(splitted, 2);
            }
            mplew.writeShort(packetheader);
            mplew.write(HexTool.getByteArrayFromHexString(packet_in));
            // mplew.write(1);
            // mplew.writeInt(2270002);
            // mplew.writeInt(100100);
            /*
             * mplew.write(1);// ariant mplew.writeMapleAsciiString("123456");
             * mplew.write(0x64);
             */
            mplew.writeZeroBytes(20);
            c.sendPacket(mplew.getPacket());

            c.getPlayer().dropMessage(packetheader + "�Ѵ��ͷ��[" + mplew.getPacket().length + "] : " + mplew.toString());
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
            return 1;
        }
    }

    public static class ע�� extends CommandExecute {//register

        @Override
        public int execute(MapleClient c, String[] splitted) {
            String acc = null;
            String password = null;
            try {
                acc = splitted[1];
                password = splitted[2];
            } catch (Exception ex) {
            }
            if (acc == null || password == null) {
                c.getPlayer().dropMessage("�˺Ż������쳣");
                return 0;
            }
            boolean ACCexist = AutoRegister.getAccountExists(acc);
            if (ACCexist) {
                c.getPlayer().dropMessage("�ʺ��ѱ�ʹ��");
                return 0;
            }
            if (acc.length() >= 12) {
                c.getPlayer().dropMessage("���볤�ȹ���");
                return 0;
            }

            Connection con;
            try {
                con = (Connection) DatabaseConnection.getConnection();
            } catch (Exception ex) {
                System.out.println(ex);
                return 0;
            }

            try {
                try (PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO accounts (name, password) VALUES (?, ?)")) {
                    ps.setString(1, acc);
                    ps.setString(2, LoginCrypto.hexSha1(password));
                    ps.executeUpdate();
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                return 0;
            }
            c.getPlayer().dropMessage("[ע�����]�˺�: " + acc + " ����: " + password);

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
//            if (c.getChannelServer().getMapFactory().getMap(mapid) == null) {
//                c.getPlayer().dropMessage("��ͼ������");
//                return 0;
//            }
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                cserv.getMapFactory().HealMap(mapid);

            }
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]�����˵�ͼ[" + splitted[1] + "]"));
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
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:[����Ա:" + c.getPlayer().getName() + "]�ر��˵�ͼ[" + splitted[1] + "]"));

            return 1;

        }
    }

    public static class reloadcpq extends CommandExecute {//reloadcpq

        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().getMap().reloadCPQ();
            c.getPlayer().dropMessage("���껪��ͼ���³ɹ�");
            return 1;
        }
    }
}
