package server;

import client.MapleCharacter;
import database.DatabaseConnection;
import handling.cashshop.CashShopServer;
import handling.channel.ChannelServer;
import handling.login.LoginServer;
import handling.world.World.Alliance;
import handling.world.World.Broadcast;
import handling.world.World.Family;
import handling.world.World.Guild;
import java.util.Set;
import static gui.QQMsgServer.sendMsgToQQGroup;
import tools.MaplePacketCreator;

public class ShutdownServer implements Runnable {

    private static final ShutdownServer instance = new ShutdownServer();
    public static boolean running = false;
    public int mode = 0;

    public static ShutdownServer getInstance() {
        return instance;
    }

    public void shutdown() {
        run();

    }

    @Override
    public void run() {
        //����Ƶ����Ӷ
        System.out.println("��ʼ������Ϸ��Ӷ����...");
        int p = 0;
        for (handling.channel.ChannelServer cserv : handling.channel.ChannelServer.getAllInstances()) {
            p++;
            cserv.closeAllMerchant();
        }
        System.out.println("��Ϸ��Ӷ���˱������...");
        //�����������
        System.out.println("��ʼ���������������A...");
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                p++;
                chr.saveToDB(true, true);
            }
        }
        System.out.println("�������������������A���...");
        //����ȫ������
        System.out.println("��ʼ�����������B...");
        int ppl = 0;
        try {
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                    if (chr == null) {
                        continue;
                    }
                    ppl++;
                    chr.saveToDB(false, false);

                }
            }
        } catch (Exception e) {
        }
        System.out.println("���������������B���...");
        System.out.println("��ʼ�ж������������...");
        //�������
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            cserv.getPlayerStorage().disconnectAll(true);
        }
        System.out.println("�ж�����������ҳɹ�...");
        //Timer
        Timer.WorldTimer.getInstance().stop();
        Timer.MapTimer.getInstance().stop();
        Timer.BuffTimer.getInstance().stop();
        Timer.CloneTimer.getInstance().stop();
        Timer.EventTimer.getInstance().stop();
        Timer.EtcTimer.getInstance().stop();

        //Merchant
        for (ChannelServer cs : ChannelServer.getAllInstances()) {
            cs.closeAllMerchant();
        }

        try {
            //Guild
            Guild.save();
            //Alliance
            Alliance.save();
            //Family
            Family.save();
        } catch (Exception ex) {
        }

        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, " ��Ϸ���������ر�ά��������Ұ�ȫ����..."));
        for (ChannelServer cs : ChannelServer.getAllInstances()) {
            try {
                cs.setServerMessage("��Ϸ���������ر�ά��������Ұ�ȫ����...");
            } catch (Exception ex) {
            }
        }
        Set<Integer> channels = ChannelServer.getAllInstance();

        for (Integer channel : channels) {
            try {
                ChannelServer cs = ChannelServer.getInstance(channel);
                cs.saveAll();
                cs.setFinishShutdown();
                cs.shutdown();
            } catch (Exception e) {
                System.out.println("Ƶ��" + String.valueOf(channel) + " �رմ���.");
            }
        }

        try {
            LoginServer.shutdown();
            System.out.println("��Ϸ��¼�̹߳ر����...");
        } catch (Exception e) {
        }

        try {
            CashShopServer.shutdown();
            System.out.println("��Ϸ�̳��̹߳ر����...");
        } catch (Exception e) {
        }
        try {
            DatabaseConnection.closeAll();
        } catch (Exception e) {
        }
        //Timer.PingTimer.getInstance().stop();
        System.out.println("������¼��ر����...");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("�رշ���˴��� - 2" + e);

        }
        System.out.println("������Ѿ��ɹ��ر�");

        if (gui.Start.ConfigValuesMap.get("QQ�����˿���") == 0) {
            sendMsgToQQGroup("[�������Ϣ]:����˹رճɹ���");
        }
        //���������();
        System.exit(0);
    }
}
