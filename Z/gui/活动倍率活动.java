package gui;

import static gui.QQͨ��.Ⱥ֪ͨ;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import handling.world.World;
import tools.MaplePacketCreator;
import server.ServerProperties;

/**
 *
 * @author Administrator
 */
public class ����ʻ {

    /**
     * <23��>
     */
    public static void ���ʻ�߳�() {
        int ��� = (int) Math.ceil(Math.random() * 9);
        switch (���) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                ���ʱ��ʻ();
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                ���鱶�ʻ();
                break;
            case 9:
                ���鱶�ʻ();
                ���ʱ��ʻ();
                break;
            default:
                break;
        }

    }

    public static void ���鱶�ʻ() {
        int ԭʼ���� = Integer.parseInt(ServerProperties.getProperty("ZEV.Exp"));
        int ���� = ԭʼ���� * 2;
        int seconds = 0;
        int mins = 0;
        int hours = 24;
        int time = seconds + (mins * 60) + (hours * 60 * 60);
        final String rate = "����";
        World.scheduleRateDelay(rate, time);
        for (ChannelServer cservs : ChannelServer.getAllInstances()) {
            cservs.setExpRate(����);
        }
        World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[���ʻ] : ��Ϸ��ʼ 2 ����ֱ��ʻ�������� 24 Сʱ����ĩ���λ��ҿ񻶰ɣ�"));
        Ⱥ֪ͨ("<" + MapleParty.�������� + ">����Ϸ��ʼ 2 ����ֱ��ʻ�������� 24 Сʱ����ĩ���λ��ҿ񻶰ɣ�");

    }

    public static void ���ʱ��ʻ() {
        int ԭʼ���� = Integer.parseInt(ServerProperties.getProperty("ZEV.Drop"));
        int ���ʻ = ԭʼ���� * 2;
        int seconds = 0;
        int mins = 0;
        int hours = 24;
        int time = seconds + (mins * 60) + (hours * 60 * 60);
        final String rate = "����";
        World.scheduleRateDelay(rate, time);
        for (ChannelServer cservs : ChannelServer.getAllInstances()) {
            cservs.setDropRate(���ʻ);
        }
        World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[���ʻ] : ��Ϸ��ʼ 2 ����ֱ��ʻ�������� 24 Сʱ����ĩ���λ��ҿ񻶰ɣ�"));
        Ⱥ֪ͨ("<" + MapleParty.�������� + ">����Ϸ��ʼ 2 ����ֱ��ʻ�������� 24 Сʱ����ĩ���λ��ҿ񻶰ɣ�");
    }
}
