package gui;

import handling.channel.ChannelServer;
import java.awt.Point;
import java.util.concurrent.ScheduledFuture;
import server.Timer;
import server.maps.MapleMap;
import java.util.Arrays;
import java.util.Scanner;

/**
 * <�
 * ���ʼ��
 * ���ִ�
 * 100000000
 *
 * ���ִ���լ
 * 100000001
 *
 * ��Ģ��
 * 100000002
 *
 * ������԰��
 * 100000003
 *
 * ������԰��
 * 100000004
 *
 * ������԰��
 * 100000005
 *
 * ����԰��Ϣ�ط�
 * 100000006
 *
 * ���ִ弯��
 * 100000100
 *
 * ���ִ�������
 * 100000101
 *
 * ���ִ��ӻ���
 * 100000102
 *
 * ���ִ�����Ժ
 * 100000103
 *
 * ���ִ�������
 * 100000104
 *
 * ���ִ廤������
 * 100000105
 *
 * ���ִ幫԰
 * 100000200
 *
 * ��������ѵ����
 * 100000201
 *
 * ���ִ���Ϸ����
 * 100000203
 *
 * �����ֵĵ���
 * 100000204
 * >
 */
public class ��������� {

    public static ScheduledFuture<?> ���������߳� = null;
    public static ScheduledFuture<?> ��Ʒ�����߳� = null;
    public static ScheduledFuture<?> ����ˢ���߳� = null;
    public static int ��ͼ��ȫ1 = 0;
    public static int ��ͼ��ȫ2 = 0;
    public static int ��ͼ��ȫ3 = 0;
    public static int ��ͼ��ȫ4 = 0;
    public static int ��ͼ��ȫ5 = 0;
    public static int ��ͼ��ȫ6 = 0;
    public static int ��ͼ��ȫ7 = 0;
    public static int ��ͼ��ȫ8 = 0;
    public static int ��ͼ��ȫ9 = 0;
    public static int ��ͼ��ȫ10 = 0;
    public static int ��ͼ��ȫ11 = 0;
    public static int ��ͼ��ȫ12 = 0;
    public static int ��ͼ��ȫ13 = 0;
    public static int ��ͼ��ȫ14 = 0;
    public static int ��ͼ��ȫ15 = 0;
    public static int ��ͼ��ȫ16 = 0;
    public static int ��ͼ��ȫ17 = 0;
    public static int ��ͼ1 = 100000000;
    public static int ��ͼ2 = 100000001;
    public static int ��ͼ3 = 100000002;
    public static int ��ͼ4 = 100000003;
    public static int ��ͼ5 = 100000004;
    public static int ��ͼ6 = 100000005;
    public static int ��ͼ7 = 100000006;
    public static int ��ͼ8 = 100000100;
    public static int ��ͼ9 = 100000101;
    public static int ��ͼ10 = 100000102;
    public static int ��ͼ11 = 100000103;
    public static int ��ͼ12 = 100000104;
    public static int ��ͼ13 = 100000105;
    public static int ��ͼ14 = 100000200;
    public static int ��ͼ15 = 100000201;
    public static int ��ͼ16 = 100000203;
    public static int ��ͼ17 = 100000204;
    public static int Ԥ���ͼ1 = 0;
    public static int Ԥ���ͼ2 = 0;
    public static int Ԥ���ͼ3 = 0;
    public static int Ԥ���ͼ4 = 0;
    public static int Ԥ���ͼ5 = 0;
    public static int Ԥ���ͼ6 = 0;
    public static int Ԥ���ͼ7 = 0;
    public static int Ԥ���ͼ8 = 0;
    public static int Ԥ���ͼ9 = 0;
    public static int Ԥ���ͼ10 = 0;
    public static int Ԥ���ͼ11 = 0;
    public static int Ԥ���ͼ12 = 0;
    public static int Ԥ���ͼ13 = 0;
    public static int Ԥ���ͼ14 = 0;
    public static int Ԥ���ͼ15 = 0;
    public static int Ԥ���ͼ16 = 0;
    public static int Ԥ���ͼ17 = 0;

    public static void Ԥ����ͼ() {
        for (int i = 1; i < 17; i++) {
           
        }
    }

    /**
     * * <�����������ʼ��
     * ��ÿ30��ˢ��һ�ε�ͼ��Դ
     *
     * >continue;
     */
    public static void ����ˢ���߳�() {
        if (����ˢ���߳� == null) {
            ����ˢ���߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    double ���� = Math.ceil(Math.random() * 100);
                    if (���� == 1) {
                        ��ͼ1++;
                        ��ͼ2++;
                    }
                }
            }, 1000 * 60 * 2);
        }
    }

    /**
     * <�����������ʼ��ÿ30��ˢ��һ�ε�ͼ��Դ>
     */
    public static void ��Ʒ�����߳�() {
        if (��Ʒ�����߳� == null) {
            ��Ʒ�����߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    ��Ʒ����(100000000, 2000005, -586, 116, 20);

                    ��Ʒ����(100000000, 2000005, -160, 274, 20);
                }
            }, 1000 * 30);
        }
    }

    /**
     * <�����������ʼ��3����������Լ�ѡ���Լ���λ��>
     */
    public static void ��������Ԥ��() {

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 60 * 3);
                    ���������߳�();
                } catch (InterruptedException e) {
                }
            }
        }.start();

    }

    /**
     * <�����������ʼ�����������߳�>
     */
    public static void ���������߳�() {
        if (���������߳� == null) {
            ���������߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {

                }
            }, 1000 * 5);
        }
    }

    /**
     * <������Ʒ> @param a
     *
     * @param b
     * @param x
     * @param y
     * @param c
     */
    public static void ��Ʒ����(int a, int b, int x, int y, int c) {
        double ���� = Math.ceil(Math.random() * 100);
        if (���� <= c) {
            ChannelServer channelServer = ChannelServer.getInstance(2);
            MapleMap mapleMap = channelServer.getMapFactory().getMap(a);
            mapleMap.spawnAutoDrop2(b, new Point(x, y));
        }
    }
}
