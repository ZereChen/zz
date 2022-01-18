package gui;

import database.DatabaseConnection;
import static gui.QQMsgServer.sendMsgToQQGroup;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import handling.world.World;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import server.ServerProperties;
import server.events.MapleEvent;
import server.events.MapleEventType;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;

public class ���ѩ����� {

    public static void ��ʼ��ѩ��() {
        final int pind = (int) Math.ceil(Math.random() * Integer.parseInt(ServerProperties.getProperty("ZEV.Count")));
        final MapleEventType type = MapleEventType.getByString("ѩ����");
        final String msg = MapleEvent.scheduleEvent(type, ChannelServer.getInstance(pind));
        MapleParty.�����++;
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 60 * 3);
                    MapleEvent.setEvent(ChannelServer.getInstance(pind), true);
                } catch (InterruptedException e) {
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 60 * 60 * 1);
                    MapleParty.����� = 0;
                    try {
                        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characterz SET Point = 0 WHERE channel = 10")) {
                            ps.executeUpdate();
                        }
                    } catch (SQLException ex) {
                    }
                    World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ϵͳ����] : �������ȴ�Ѿ����ã�����ǿ���ѡ������ˡ�"));
                    sendMsgToQQGroup("[ϵͳ����] : �������ȴ�Ѿ����ã�����ǿ���ѡ������ˡ�");
                } catch (InterruptedException e) {
                }
            }
        }.start();
        System.err.println("[�����]" + CurrentReadable_Time() + " : ѩ�����߳�����");
    }

}
