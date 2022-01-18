package client.messages.commands;

import client.MapleCharacter;
import client.MapleCharacterUtil;
import client.MapleClient;
import client.SkillFactory;
import constants.ServerConstants;
import static gui.QQMsgServer.sendMsgToQQGroup;
import handling.channel.ChannelServer;
import handling.world.World;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import scripting.NPCScriptManager;
import server.MaplePortal;
import server.Timer;
import server.events.MapleEvent;
import server.events.MapleEventType;
import server.maps.MapleMap;
import tools.FileoutputUtil;
import tools.MaplePacketCreator;
import tools.StringUtil;

/*
1.����
2.���
3.����/��
4.�޵�
 */
public class Ѳ����� {//����Ȩ��1

    public static ServerConstants.PlayerGMRank getPlayerLevelRequired() {
        return ServerConstants.PlayerGMRank.Ѳ�����;
    }

    public static class ���� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.sendPacket(MaplePacketCreator.enableActions());
            NPCScriptManager.getInstance().dispose(c);
            NPCScriptManager.getInstance().start(c, 9900004, 0);
            return 1;
        }
    }

    public static class ���յ�ͼ extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            int ��ͼ = c.getPlayer().getMapId();
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                    if (chr == null) {
                        continue;
                    }
                    if (chr.getMapId() == ��ͼ) {
                        chr.getClient().getSession().close();
                    }
                }
            }
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                cserv.getMapFactory().destroyMap(��ͼ, true);
                cserv.getMapFactory().HealMap(��ͼ);
            }
            return 1;
        }
    }

    public static class ���� extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            MapleCharacter victim = c.getChannelServer().getPlayerStorage().getCharacterByName(splitted[1]);
            if (victim != null) {
                if (splitted.length == 2) {
                    c.getPlayer().changeMap(victim.getMap(), victim.getMap().findClosestSpawnpoint(victim.getPosition()));
                } else {
                    MapleMap target = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(Integer.parseInt(splitted[2]));
                    victim.changeMap(target, target.getPortal(0));
                }
            } else {
                try {
                    victim = c.getPlayer();
                    int ch = World.Find.findChannel(splitted[1]);
                    if (ch < 0) {
                        MapleMap target = c.getChannelServer().getMapFactory().getMap(Integer.parseInt(splitted[1]));
                        c.getPlayer().changeMap(target, target.getPortal(0));
                    } else {
                        victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(splitted[1]);
                        c.getPlayer().dropMessage(6, "���ڻ�Ƶ��,��ȴ�.");
                        if (victim.getMapId() != c.getPlayer().getMapId()) {
                            final MapleMap mapp = c.getChannelServer().getMapFactory().getMap(victim.getMapId());
                            c.getPlayer().changeMap(mapp, mapp.getPortal(0));
                        }
                        c.getPlayer().changeChannel(ch);
                    }
                } catch (Exception e) {
                    c.getPlayer().dropMessage(6, "����Ҳ����� " + e.getMessage());
                    return 0;
                }
            }
            return 1;
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

    public static class ���� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            c.getPlayer().getMap().disconnectAll();
            String msg = "[����ָ��] ����Ա " + c.getPlayer().getName() + "  �ж��˵�ͼ���";
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            return 1;
        }
    }
}
