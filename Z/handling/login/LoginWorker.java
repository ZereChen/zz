package handling.login;

import java.util.Map;
import java.util.Map.Entry;

import client.MapleClient;
import handling.channel.ChannelServer;
import server.ServerProperties;
import server.Timer.PingTimer;
import tools.packet.LoginPacket;
import tools.MaplePacketCreator;

public class LoginWorker {

    private static long lastUpdate = 0;

    public static void registerClient(final MapleClient c) {
        int ��ֹ��½���� = gui.Start.ConfigValuesMap.get("��ֹ��½����");
        if (!c.isGm()) {
            if (��ֹ��½���� <= 0) {
                c.sendPacket(MaplePacketCreator.serverNotice(1, "��Ϸ����ά���С�"));
                c.sendPacket(LoginPacket.getLoginFailed(7));
                return;
            }
        }

        if (LoginServer.isAdminOnly() && !c.isGm()) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "��Ϸ����ά����"));
            c.sendPacket(LoginPacket.getLoginFailed(1));
            return;
        }

        if (System.currentTimeMillis() - lastUpdate > 10 * 60 * 1000) { // Update once every 10 minutes
            lastUpdate = System.currentTimeMillis();
            final Map<Integer, Integer> load = ChannelServer.getChannelLoad();
            int usersOn = 0;

            if (load == null || load.size() <= 0) { // In an unfortunate event that client logged in before load
                lastUpdate = 0;
                c.sendPacket(LoginPacket.getLoginFailed(7));
                return;
            }
            double loads = load.size();
            double userlimit = LoginServer.getUserLimit();
            final double loadFactor = 1200 / ((double) LoginServer.getUserLimit() / load.size());
            for (Entry<Integer, Integer> entry : load.entrySet()) {
                usersOn += entry.getValue();
                load.put(entry.getKey(), Math.min(1200, (int) (entry.getValue() * loadFactor)));

            }
            LoginServer.setLoad(load, usersOn);
            lastUpdate = System.currentTimeMillis();

        }

        if (c.finishLogin() == 0) {
            if (c.getGender() == 10) {
                c.sendPacket(LoginPacket.getGenderNeeded(c));//
            } else {
                c.sendPacket(LoginPacket.getAuthSuccessRequest(c));
                if (gui.Start.ConfigValuesMap.get("����ţ����") == 0) {
                    c.sendPacket(LoginPacket.getServerList(0, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.����ţ״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("Ģ���п���") == 0) {
                    c.sendPacket(LoginPacket.getServerList(1, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.Ģ����״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("��ˮ�鿪��") == 0) {
                    c.sendPacket(LoginPacket.getServerList(2, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��ˮ��״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("ƯƯ����") == 0) {
                    c.sendPacket(LoginPacket.getServerList(3, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.ƯƯ��״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("С���߿���") == 0) {
                    c.sendPacket(LoginPacket.getServerList(4, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.С����״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("���з����") == 0) {
                    c.sendPacket(LoginPacket.getServerList(5, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.���з״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("�󺣹꿪��") == 0) {
                    c.sendPacket(LoginPacket.getServerList(6, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�󺣹�״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("����ֿ���") == 0) {
                    c.sendPacket(LoginPacket.getServerList(7, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�����״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("��Ƥ�￪��") == 0) {
                    c.sendPacket(LoginPacket.getServerList(8, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��Ƥ��״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("�Ǿ��鿪��") == 0) {
                    c.sendPacket(LoginPacket.getServerList(9, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�Ǿ���״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("����쿪��") == 0) {
                    c.sendPacket(LoginPacket.getServerList(10, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�����״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("��ѩ�˿���") == 0) {
                    c.sendPacket(LoginPacket.getServerList(11, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��ѩ��״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("ʯͷ�˿���") == 0) {
                    c.sendPacket(LoginPacket.getServerList(12, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.ʯͷ��״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("��ɫè����") == 0) {
                    c.sendPacket(LoginPacket.getServerList(13, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��ɫè״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("����ǿ���") == 0) {
                    c.sendPacket(LoginPacket.getServerList(14, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�����״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("С���ÿ���") == 0) {
                    c.sendPacket(LoginPacket.getServerList(15, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.С����״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("���������") == 0) {
                    c.sendPacket(LoginPacket.getServerList(16, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�����״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("��Ұ����") == 0) {
                    c.sendPacket(LoginPacket.getServerList(17, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��Ұ��״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("�����㿪��") == 0) {
                    c.sendPacket(LoginPacket.getServerList(18, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.������״̬"))));
                }
                if (gui.Start.ConfigValuesMap.get("��Ģ������") == 0) {
                    c.sendPacket(LoginPacket.getServerList(19, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��Ģ��״̬"))));
                }
                c.sendPacket(LoginPacket.getEndOfServerList());
            }
            c.setIdleTask(PingTimer.getInstance().schedule(new Runnable() {

                public void run() {
//                    c.getSession().close();
                }
            }, 10 * 60 * 10000));
        } else if (c.getGender() == 10) {
            c.sendPacket(LoginPacket.getGenderNeeded(c));

        } else {
            c.sendPacket(LoginPacket.getAuthSuccessRequest(c));
            if (gui.Start.ConfigValuesMap.get("����ţ����") == 0) {
                c.sendPacket(LoginPacket.getServerList(0, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.����ţ״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("Ģ���п���") == 0) {
                c.sendPacket(LoginPacket.getServerList(1, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.Ģ����״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("��ˮ�鿪��") == 0) {
                c.sendPacket(LoginPacket.getServerList(2, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��ˮ��״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("ƯƯ����") == 0) {
                c.sendPacket(LoginPacket.getServerList(3, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.ƯƯ��״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("С���߿���") == 0) {
                c.sendPacket(LoginPacket.getServerList(4, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.С����״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("���з����") == 0) {
                c.sendPacket(LoginPacket.getServerList(5, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.���з״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("�󺣹꿪��") == 0) {
                c.sendPacket(LoginPacket.getServerList(6, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�󺣹�״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("����ֿ���") == 0) {
                c.sendPacket(LoginPacket.getServerList(7, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�����״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("��Ƥ�￪��") == 0) {
                c.sendPacket(LoginPacket.getServerList(8, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��Ƥ��״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("�Ǿ��鿪��") == 0) {
                c.sendPacket(LoginPacket.getServerList(9, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�Ǿ���״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("����쿪��") == 0) {
                c.sendPacket(LoginPacket.getServerList(10, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�����״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("��ѩ�˿���") == 0) {
                c.sendPacket(LoginPacket.getServerList(11, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��ѩ��״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("ʯͷ�˿���") == 0) {
                c.sendPacket(LoginPacket.getServerList(12, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.ʯͷ��״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("��ɫè����") == 0) {
                c.sendPacket(LoginPacket.getServerList(13, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��ɫè״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("����ǿ���") == 0) {
                c.sendPacket(LoginPacket.getServerList(14, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�����״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("С���ÿ���") == 0) {
                c.sendPacket(LoginPacket.getServerList(15, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.С����״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("���������") == 0) {
                c.sendPacket(LoginPacket.getServerList(16, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�����״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("��Ұ����") == 0) {
                c.sendPacket(LoginPacket.getServerList(17, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��Ұ��״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("�����㿪��") == 0) {
                c.sendPacket(LoginPacket.getServerList(18, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.������״̬"))));
            }
            if (gui.Start.ConfigValuesMap.get("��Ģ������") == 0) {
                c.sendPacket(LoginPacket.getServerList(19, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��Ģ��״̬"))));
            }
            c.sendPacket(LoginPacket.getEndOfServerList());
        }
        /* c.sendPacket(LoginPacket.getLoginFailed(7));

            System.out.println("��¼Z");
            return
        if (server.Start.ConfigValuesMap.get("����ţ����") == 0) {
                c.sendPacket(LoginPacket.getServerList(0, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("Ģ���п���") == 0) {
                c.sendPacket(LoginPacket.getServerList(1, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("��ˮ�鿪��") == 0) {
                c.sendPacket(LoginPacket.getServerList(2, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("ƯƯ����") == 0) {
                c.sendPacket(LoginPacket.getServerList(3, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("С���߿���") == 0) {
                c.sendPacket(LoginPacket.getServerList(4, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("���з����") == 0) {
                c.sendPacket(LoginPacket.getServerList(5, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("�󺣹꿪��") == 0) {
                c.sendPacket(LoginPacket.getServerList(6, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("����ֿ���") == 0) {
                c.sendPacket(LoginPacket.getServerList(7, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("��Ƥ�￪��") == 0) {
                c.sendPacket(LoginPacket.getServerList(8, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("�Ǿ��鿪��") == 0) {
                c.sendPacket(LoginPacket.getServerList(9, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("����쿪��") == 0) {
                c.sendPacket(LoginPacket.getServerList(10, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("��ѩ�˿���") == 0) {
                c.sendPacket(LoginPacket.getServerList(11, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("ʯͷ�˿���") == 0) {
                c.sendPacket(LoginPacket.getServerList(12, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("��ɫè����") == 0) {
                c.sendPacket(LoginPacket.getServerList(13, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("����ǿ���") == 0) {
                c.sendPacket(LoginPacket.getServerList(14, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("С���ÿ���") == 0) {
                c.sendPacket(LoginPacket.getServerList(15, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("���������") == 0) {
                c.sendPacket(LoginPacket.getServerList(16, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("��Ұ����") == 0) {
                c.sendPacket(LoginPacket.getServerList(17, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("�����㿪��") == 0) {
                c.sendPacket(LoginPacket.getServerList(18, LoginServer.getServerName(), LoginServer.getLoad()));
            }
            if (server.Start.ConfigValuesMap.get("��Ģ������") == 0) {
                c.sendPacket(LoginPacket.getServerList(19, LoginServer.getServerName(), LoginServer.getLoad()));
            }
        
        ;*/
    }
}
