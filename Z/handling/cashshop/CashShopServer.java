package handling.cashshop;

//import static constants.GameConstants.��IP;
import handling.MapleServerHandler;
import handling.channel.PlayerStorage;
import java.net.InetSocketAddress;

import server.ServerProperties;
import static tools.FileoutputUtil.CurrentReadable_Time;
import static abc.Game.�̳Ƕ˿�;
import gui.ZevmsLauncherServer;
import handling.netty.ServerConnection;
import handling.world.MapleParty;
import java.util.List;
import java.util.Map;

public class CashShopServer {

    private static String ip;
    private static InetSocketAddress InetSocketadd;
    private final static int PORT = �̳Ƕ˿�;//8596//
    private static ServerConnection acceptor;
    private static PlayerStorage players, playersMTS;
    private static boolean finishedShutdown = false;

    public static void run_startup_configurations() {
        ip = MapleParty.IP��ַ + ":" + PORT;//��IPZEV
        players = new PlayerStorage(-10);
        playersMTS = new PlayerStorage(-20);

        try {
            acceptor = new ServerConnection(PORT, 0, -10);
            acceptor.run();
            System.out.println("�� ���ص�����Ʒ:  ���");
            //System.out.println("�� ����ʱ��:  " + Integer.parseInt(ServerProperties.getProperty("ZEV.dysj")) + "  �� ");

            //sSystem.out.println("�� ��Ӷ��ȡ��Ʒ���ʱ��:  " + Integer.parseInt(ServerProperties.getProperty("ZEV.gylq")) + "  ���� ");
            System.out.println("�� ������Ϸ�̳�:");

            System.out.println("�� �̳�: �����˿� " + PORT);
            System.out.println("�� �̳�: " + CurrentReadable_Time() + " ��ȡ�̳��ϼ�ʱװ�С�����");
        } catch (final Exception e) {
            System.err.println("Binding to port " + PORT + " failed");
            e.printStackTrace();
            throw new RuntimeException("Binding failed.", e);
        }
    }

    public static final String getIP() {
        return ip;
    }

    public static final PlayerStorage getPlayerStorage() {
        return players;
    }

    public static final PlayerStorage getPlayerStorageMTS() {
        return playersMTS;
    }

//    public static final void closeConn(String ip) {
//        for (IoSession ss : acceptor.getManagedSessions().values()) {
//            if (ss.getRemoteAddress() == null) {
//                continue;
//            }
//            if (ss.getRemoteAddress().toString().split(":")[0].equals(ip)) {
//                ss.close(false);
//                return;
//            }
//        }
//    }
//    
//    public static final boolean checkPorts() {
//        for (IoSession ss : acceptor.getManagedSessions().values()) {
//            if (ss.getRemoteAddress() == null) {
//                continue;
//            }
//            String[] arr = ss.getRemoteAddress().toString().split(":");
//            String sip = arr[0];
//            String sport = arr[1];
//            
//            for (Map.Entry<String, List<String>> entry  : ZevmsLauncherServer.allowClientMap.entrySet()) {
//                String ip = entry.getKey();
//                if (ip.equals(sip)) {
//                    List<String> ports = entry.getValue();
//                    return ports.contains(sport);
//                }
//            }
//        }
//        
//        return true;
//    }

    public static final void shutdown() {
        if (finishedShutdown) {
            return;
        }
        System.out.println("���ڶϿ��̳������...");
        players.disconnectAll();
        //playersMTS.disconnectAll();
        // MTSStorage.getInstance().saveBuyNow(true);
        System.out.println("�ر���Ϸ�̳Ƿ�����...");
        //acceptor.unbindAll();
        finishedShutdown = true;
    }

    public static boolean isShutdown() {
        return finishedShutdown;
    }
    
//    public static Map<Long, IoSession> getSessions() {
//        return acceptor.getManagedSessions();
//    }
}
