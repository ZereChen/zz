package client.messages.commands.a;

import client.MapleCharacter;
import client.MapleClient;
import client.messages.commands.CommandExecute;
import static gui.QQMsgServer.sendMsgToQQGroup;
import handling.channel.ChannelServer;
import handling.world.World;
import tools.MaplePacketCreator;
import tools.StringUtil;

public class InternCommand {//����Ȩ��1

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
                    String ��Ϣ = "[ϵͳ����]" + target.getName() + " ��Ϊʹ����ʹ�÷Ƿ����/�ƻ���Ϸƽ�⣬�������÷�š�";
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

}
