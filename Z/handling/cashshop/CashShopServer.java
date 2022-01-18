package handling.cashshop;

//import static constants.GameConstants.绑定IP;
import handling.MapleServerHandler;
import handling.channel.PlayerStorage;
import java.net.InetSocketAddress;

import server.ServerProperties;
import static tools.FileoutputUtil.CurrentReadable_Time;
import static abc.Game.商城端口;
import gui.ZevmsLauncherServer;
import handling.netty.ServerConnection;
import handling.world.MapleParty;
import java.util.List;
import java.util.Map;

public class CashShopServer {

    private static String ip;
    private static InetSocketAddress InetSocketadd;
    private final static int PORT = 商城端口;//8596//
    private static ServerConnection acceptor;
    private static PlayerStorage players, playersMTS;
    private static boolean finishedShutdown = false;

    public static void run_startup_configurations() {
        ip = MapleParty.IP地址 + ":" + PORT;//绑定IPZEV
        players = new PlayerStorage(-10);
        playersMTS = new PlayerStorage(-20);

        try {
            acceptor = new ServerConnection(PORT, 0, -10);
            acceptor.run();
            System.out.println("○ 加载钓鱼物品:  完成");
            //System.out.println("○ 钓鱼时间:  " + Integer.parseInt(ServerProperties.getProperty("ZEV.dysj")) + "  秒 ");

            //sSystem.out.println("○ 雇佣领取物品间隔时间:  " + Integer.parseInt(ServerProperties.getProperty("ZEV.gylq")) + "  分钟 ");
            System.out.println("○ 启动游戏商城:");

            System.out.println("○ 商城: 启动端口 " + PORT);
            System.out.println("○ 商城: " + CurrentReadable_Time() + " 读取商城上架时装中···");
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
        System.out.println("正在断开商城内玩家...");
        players.disconnectAll();
        //playersMTS.disconnectAll();
        // MTSStorage.getInstance().saveBuyNow(true);
        System.out.println("关闭游戏商城服务器...");
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
