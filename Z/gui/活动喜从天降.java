package gui;

import static gui.�ħ�幥��1.B������;
import handling.channel.ChannelServer;
import java.awt.Point;
import java.util.concurrent.ScheduledFuture;
import server.Timer;
import server.maps.MapleMap;

public class �ϲ���콵 {

    public static ScheduledFuture<?> ϲ���콵�߳� = null;
    public static int ��Ʒ����1 = 0;
    public static int ��Ʒ����2 = 0;
    public static int ��Ʒ����3 = 0;
    public static int ��Ʒ����4 = 0;
    public static int ��Ʒ����5 = 0;
    public static int ��Ʒ����6 = 0;
    public static int ��Ʒ����7 = 0;
    public static int ��Ʒ����8 = 0;
    public static int ��Ʒ����9 = 0;
    public static int ��Ʒ����10 = 0;
    public static int ��Ʒ����11 = 0;
    public static int ��Ʒ����12 = 0;
    public static int ��Ʒ����13 = 0;
    public static int ��Ʒ����14 = 0;
    public static int ��Ʒ����15 = 0;
    public static int ��Ʒ����16 = 0;
    public static int ��Ʒ����17 = 0;
    public static int ��Ʒ����18 = 0;
    public static int ��Ʒ����19 = 0;
    public static int ��Ʒ����20 = 0;
    public static int ��Ʒ����21 = 0;
    public static int ��Ʒ����22 = 0;
    public static int ���� = 0;

    public static void �����Ʒ() {
        int ���� = 22;
        int a1 = (int) Math.ceil(Math.random() * ����);
        int a2 = (int) Math.ceil(Math.random() * ����);
        int a3 = (int) Math.ceil(Math.random() * ����);
        int a4 = (int) Math.ceil(Math.random() * ����);
        int a5 = (int) Math.ceil(Math.random() * ����);
        int a6 = (int) Math.ceil(Math.random() * ����);
        int a7 = (int) Math.ceil(Math.random() * ����);
        int a8 = (int) Math.ceil(Math.random() * ����);
        int a9 = (int) Math.ceil(Math.random() * ����);
        int a10 = (int) Math.ceil(Math.random() * ����);
        int a11 = (int) Math.ceil(Math.random() * ����);
        int a12 = (int) Math.ceil(Math.random() * ����);
        int a13 = (int) Math.ceil(Math.random() * ����);
        int a14 = (int) Math.ceil(Math.random() * ����);
        int a15 = (int) Math.ceil(Math.random() * ����);
        int a16 = (int) Math.ceil(Math.random() * ����);
        int a17 = (int) Math.ceil(Math.random() * ����);
        int a18 = (int) Math.ceil(Math.random() * ����);
        int a19 = (int) Math.ceil(Math.random() * ����);
        int a20 = (int) Math.ceil(Math.random() * ����);
        int a21 = (int) Math.ceil(Math.random() * ����);
        int a22 = (int) Math.ceil(Math.random() * ����);
        int[][] ��Ʒ = {
            {2000000},
            {2000001},
            {2000002},
            {4031250},
            {4001126},
            {4000313},
            {4001126},
            {4000313},
            {4001126},
            {4000313},
            {4001126},
            {4000313},
            {4001126},
            {4000313},
            {4001126},
            {4000313},
            {4001126},
            {4000313},
            {4006000},
            {4006001},
            {4010000},
            {4010001},
            {2000005}
        };
        ��Ʒ����1 = ��Ʒ[a1][0];
        ��Ʒ����2 = ��Ʒ[a2][0];
        ��Ʒ����3 = ��Ʒ[a3][0];
        ��Ʒ����4 = ��Ʒ[a4][0];
        ��Ʒ����5 = ��Ʒ[a5][0];
        ��Ʒ����6 = ��Ʒ[a6][0];
        ��Ʒ����7 = ��Ʒ[a7][0];
        ��Ʒ����8 = ��Ʒ[a8][0];
        ��Ʒ����9 = ��Ʒ[a9][0];
        ��Ʒ����10 = ��Ʒ[a10][0];
        ��Ʒ����11 = ��Ʒ[a11][0];
        ��Ʒ����12 = ��Ʒ[a12][0];
        ��Ʒ����13 = ��Ʒ[a13][0];
        ��Ʒ����14 = ��Ʒ[a14][0];
        ��Ʒ����15 = ��Ʒ[a15][0];
        ��Ʒ����16 = ��Ʒ[a16][0];
        ��Ʒ����17 = ��Ʒ[a17][0];
        ��Ʒ����18 = ��Ʒ[a18][0];
        ��Ʒ����19 = ��Ʒ[a19][0];
        ��Ʒ����20 = ��Ʒ[a20][0];
        ��Ʒ����21 = ��Ʒ[a21][0];
        ��Ʒ����22 = ��Ʒ[a22][0];

    }

    public static void ϲ���콵() {
        if (ϲ���콵�߳� == null) {
            ϲ���콵�߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    ChannelServer channelServer = ChannelServer.getInstance(2);
                    MapleMap mapleMap = channelServer.getMapFactory().getMap(910000000);
                    �����Ʒ();
                    //��һ��
                    mapleMap.spawnAutoDrop2(��Ʒ����1, new Point(526, 4));
                    mapleMap.spawnAutoDrop2(��Ʒ����2, new Point(676, 4));
                    mapleMap.spawnAutoDrop2(��Ʒ����3, new Point(830, 4));
                    mapleMap.spawnAutoDrop2(��Ʒ����4, new Point(1023, 4));
                    mapleMap.spawnAutoDrop2(��Ʒ����5, new Point(1183, 4));
                    mapleMap.spawnAutoDrop2(��Ʒ����6, new Point(1356, 4));
                    //�ڶ���
                    mapleMap.spawnAutoDrop2(��Ʒ����7, new Point(560, -266));
                    mapleMap.spawnAutoDrop2(��Ʒ����8, new Point(714, -266));
                    mapleMap.spawnAutoDrop2(��Ʒ����9, new Point(851, -266));
                    mapleMap.spawnAutoDrop2(��Ʒ����10, new Point(1005, -266));
                    mapleMap.spawnAutoDrop2(��Ʒ����11, new Point(1157, -266));
                    mapleMap.spawnAutoDrop2(��Ʒ����12, new Point(1332, -266));
                    //������
                    mapleMap.spawnAutoDrop2(��Ʒ����13, new Point(616, -536));
                    mapleMap.spawnAutoDrop2(��Ʒ����14, new Point(777, -536));
                    mapleMap.spawnAutoDrop2(��Ʒ����15, new Point(951, -536));
                    mapleMap.spawnAutoDrop2(��Ʒ����16, new Point(1130, -536));
                    mapleMap.spawnAutoDrop2(��Ʒ����17, new Point(1284, -536));
                    //���Ĳ�
                    mapleMap.spawnAutoDrop2(��Ʒ����18, new Point(647, -806));
                    mapleMap.spawnAutoDrop2(��Ʒ����19, new Point(807, -806));
                    mapleMap.spawnAutoDrop2(��Ʒ����20, new Point(949, -806));
                    mapleMap.spawnAutoDrop2(��Ʒ����21, new Point(1103, -806));
                    mapleMap.spawnAutoDrop2(��Ʒ����22, new Point(1248, -806));
                    ����++;
                    if (���� >= 300) {
                        �ر�ϲ���콵�߳�();
                    }
                }
            }, 1000 * 2);
        }
    }

    public static void �ر�ϲ���콵�߳�() {
        if (ϲ���콵�߳� != null) {
            ϲ���콵�߳�.cancel(true);
            ϲ���콵�߳� = null;
        }
    }

}
