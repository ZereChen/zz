package gui;

import client.MapleCharacter;
import static gui.QQͨ��.Ⱥ֪ͨ;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import handling.world.World;
import java.awt.Point;
import java.util.Calendar;
import java.util.concurrent.ScheduledFuture;
import server.Timer;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import server.maps.MapleMap;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;

public class �ħ�幥��1 {

    public static int ����ħ = 8150000;
    public static int ÿ������ = 40;//10��
    public static ScheduledFuture<?> ħ�幥���߳� = null;
    public static int ħ�幥�� = 0;
    public static ScheduledFuture<?> ���ִ��߳� = null;
    public static int �ƻ����ִ� = 0;
    public static ScheduledFuture<?> ��ʿ�����߳� = null;
    public static int �ƻ���ʿ���� = 0;
    public static ScheduledFuture<?> ħ�������߳� = null;
    public static int �ƻ�ħ������ = 0;
    public static ScheduledFuture<?> ���������߳� = null;
    public static int �ƻ��������� = 0;
    public static int A = 0;
    public static int B = 0;
    public static int C = 0;
    public static int D = 0;
    public static int �ƻ���� = 30;
    public static Point A1���� = new Point(3114, 125);
    public static Point A2���� = new Point(2835, 275);
    public static Point A3���� = new Point(6084, -176);
    public static Point B1���� = new Point(1597, 101);
    public static Point B2���� = new Point(1855, 124);
    public static Point B3���� = new Point(1669, 124);
    public static Point B4���� = new Point(1666, 125);
    public static Point B5���� = new Point(1320, 121);
    public static Point B6���� = new Point(-849, 373);
    public static Point C1���� = new Point(213, -48);
    public static Point C2���� = new Point(-1344, 23);
    public static Point C3���� = new Point(-359, 24);
    public static Point C4���� = new Point(-357, 825);
    public static Point C5���� = new Point(-1642, 1560);
    public static Point C6���� = new Point(-1091, -2806);
    public static Point D1���� = new Point(-415, 2025);
    public static Point D2���� = new Point(1372, 2145);
    public static Point D3���� = new Point(-437, 2025);
    public static Point D4���� = new Point(758, 2025);
    public static Point D5���� = new Point(2824, 1935);
    public static int ���ִ�����ɹ� = 0;
    public static int �������з����ɹ� = 0;
    public static int ��ʿ��������ɹ� = 0;
    public static int ħ�����ַ����ɹ� = 0;

    /**
     * <
     * ����ħ������·����4������
     *
     * [A����] ���ִ��Թ�·��106010100���Թ�ͨ��106010000�����ִ�100000000
     * [B����]��(107000400)����̶����(107000300)����̶һ��(107000200)�����һ��(107000100)����ض���(107000000)���������(103000000)��������
     * [C����] ��ľ���ϲ�101010103����ľ�֢�101010102����ľ�֢�101010101����ľ��I101010100��ħ�����ֱ���101010000��ħ������101000000
     * [D����] ��ʿ�����Թ����106000300������Ͽ�Ȣ�106000200������Ͽ�ȶ�106000100������Ͽ��һ106000000����ʿ����102000000
     * 1.�������趨
     * 2.��̱�δ����
     *
     *
     * >
     */
    public static void ħ�幥���߳�() {
        if (ħ�幥���߳� == null) {
            System.err.println("[�����]" + CurrentReadable_Time() + "ħ�幥���߳�����");
            Ⱥ֪ͨ("[���򾯱�] : ����Ч�鱨��ħ��������2Ƶ������֮�Ǵ����ᣬ����Ҫ���ٳ����ˣ����ִ壬��ʿ���䣬�������У�ħ�����֣�����ת����Σ�ڵ�Ϧ����ð�ռ��Ƿ���Ԯ������");
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���򾯱�] : ����Ч�鱨��ħ��������2Ƶ������֮�Ǵ����ᣬ����Ҫ���ٳ����ˣ����ִ壬��ʿ���䣬�������У�ħ�����֣�����ת����Σ�ڵ�Ϧ����ð�ռ��Ƿ���Ԯ������"));

            ħ�幥���߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    int ʱ = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                    int �� = Calendar.getInstance().get(Calendar.MINUTE);
                    //���ִ�
                    if (A < 3) {
                        if (���ִ�����ɹ� == 0) {
                            ���ǲ���A();
                        }
                    }//��������
                    if (B < 6) {
                        if (�������з����ɹ� == 0) {
                            ���ǲ���B();
                        }
                    }
                    //ħ������
                    if (C < 6) {
                        if (ħ�����ַ����ɹ� == 0) {
                            ���ǲ���C();
                        }
                    }
                    //��ʿ����
                    if (D < 5) {
                        if (��ʿ��������ɹ� == 0) {
                            ���ǲ���D();
                        }
                    }
                    if (ʱ == 21 && �� == 40) {
                        �ر�ħ�幥��();
                    }
                }
            }, 1000 * 10);
        }
    }

    public static void �ر�ħ�幥��() {
        if (���������߳� != null) {
            ���������߳�.cancel(true);
            ���������߳� = null;
        }
    }

    /**
     * <����ħ�������ִ��ʼ�ƻ����ִ�>
     */
    public static void �ƻ����ִ�() {
        if (���ִ��߳� == null) {
            ���ִ��߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    ChannelServer channelServer = ChannelServer.getInstance(2);
                    MapleMap mapleMap = channelServer.getMapFactory().getMap(100000000);
                    if (�ƻ����ִ� < 10) {
                        if (mapleMap.getMonsterById(����ħ) != null) {
                            String ��Ϣ = "���ִ����ڱ��ƻ������ִ����ڱ��ƻ������ƻ���������";
                            System.err.println("[�����]" + CurrentReadable_Time() + " [���򾯱�] : " + ��Ϣ);
                            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���򾯱�] : " + ��Ϣ));
                            Ⱥ֪ͨ("[���򾯱�] : " + ��Ϣ);
                            �ƻ����ִ�++;
                        } else {
                            �ر��ƻ����ִ�(0);
                        }
                    } else {
                        �ر��ƻ����ִ�(1);
                    }
                }
            }, 1000 * �ƻ����);
        }
    }

    public static void �ر��ƻ����ִ�(int a) {
        if (���ִ��߳� != null) {
            ���ִ��߳�.cancel(true);
            ���ִ��߳� = null;
            if (a == 1) {
                ���(100000000);
                String ��Ϣ = "����ֹͣ�ˣ����ִ己�ٶȴ���½���";
                System.err.println("[�����]" + CurrentReadable_Time() + " : [���򾯱�] : " + ��Ϣ);
                Ⱥ֪ͨ("[���򾯱�] : " + ��Ϣ);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���򾯱�] : " + ��Ϣ));
            } else {
                String ��Ϣ = "��Ȼ���ִ屻�ƻ��ˣ���������Ȼ�����һЩ";
                System.err.println("[�����]" + CurrentReadable_Time() + " : [���򾯱�] : " + ��Ϣ);
                Ⱥ֪ͨ("[���򾯱�] : " + ��Ϣ);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���򾯱�] : " + ��Ϣ));
            }
        }
    }

    public static void ���ִ�����ɹ�() {
        ���ִ�����ɹ� = 0;
        String ��Ϣ = "��ʿ�ǳɹ��������˽������ִ��ħ�壬��ϲ�����ˡ�";
        System.err.println("[�����]" + CurrentReadable_Time() + " : [����֪ͨ] : " + ��Ϣ);
        Ⱥ֪ͨ("[����֪ͨ] : " + ��Ϣ);
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[����֪ͨ] : " + ��Ϣ));
    }

    /**
     * <����ħ����������к�ʼ�ƻ���������>
     */
    public static void �ƻ���������() {
        if (���������߳� == null) {
            ���������߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    ChannelServer channelServer = ChannelServer.getInstance(2);
                    MapleMap mapleMap = channelServer.getMapFactory().getMap(103000000);
                    if (�ƻ��������� <= 10) {
                        if (mapleMap.getMonsterById(����ħ) != null) {
                            String ��Ϣ = "���ִ����ڱ��ƻ������ִ����ڱ��ƻ������ƻ���������";
                            Ⱥ֪ͨ("[���򾯱�] : " + ��Ϣ);
                            System.err.println("[�����]" + CurrentReadable_Time() + " [���򾯱�] : " + ��Ϣ);
                            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���򾯱�] : " + ��Ϣ));
                            �ƻ���������++;
                        } else {
                            �ر��ƻ���������(0);
                        }
                    } else {
                        �ر��ƻ���������(1);
                    }
                }
            }, 1000 * �ƻ����);
        }
    }

    public static void �ر��ƻ���������(int a) {
        if (���������߳� != null) {
            ���������߳�.cancel(true);
            ���������߳� = null;
            if (a == 1) {
                ���(103000000);
                String ��Ϣ = "����ֹͣ�ˣ��������з��ٶȴ���½���";
                System.err.println("[�����]" + CurrentReadable_Time() + " : [���򾯱�] : " + ��Ϣ);
                Ⱥ֪ͨ("[���򾯱�] : " + ��Ϣ);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���򾯱�] : " + ��Ϣ));
            } else {
                String ��Ϣ = "��Ȼ�������б��ƻ��ˣ���������Ȼ�����һЩ";
                Ⱥ֪ͨ("[���򾯱�] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " : [���򾯱�] : " + ��Ϣ);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���򾯱�] : " + ��Ϣ));
            }
        }
    }

    public static void �������з����ɹ�() {
        �������з����ɹ� = 0;
        String ��Ϣ = "��ʿ�ǳɹ��������˽����������е�ħ�壬��ϲ�����ˡ�";
        System.err.println("[�����]" + CurrentReadable_Time() + " : [����֪ͨ] : " + ��Ϣ);
        Ⱥ֪ͨ("[����֪ͨ] : " + ��Ϣ);
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[����֪ͨ] : " + ��Ϣ));
    }

    /**
     * <����ħ������ʿ�����ʼ�ƻ���ʿ����>
     */
    public static void �ƻ���ʿ����() {
        if (��ʿ�����߳� == null) {
            ��ʿ�����߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    ChannelServer channelServer = ChannelServer.getInstance(2);
                    MapleMap mapleMap = channelServer.getMapFactory().getMap(103000000);
                    if (�ƻ���ʿ���� <= 10) {
                        if (mapleMap.getMonsterById(����ħ) != null) {
                            String ��Ϣ = "��ʿ�������ڱ��ƻ�����ʿ�������ڱ��ƻ������ƻ���������";
                            System.err.println("[�����]" + CurrentReadable_Time() + " [���򾯱�] : " + ��Ϣ);
                            Ⱥ֪ͨ("[���򾯱�] : " + ��Ϣ);
                            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���򾯱�] : " + ��Ϣ));
                            �ƻ���ʿ����++;
                        } else {
                            �ر��ƻ���ʿ����(0);
                        }
                    } else {
                        �ر��ƻ���ʿ����(1);
                    }
                }
            }, 1000 * �ƻ����);
        }
    }

    public static void �ر��ƻ���ʿ����(int a) {
        if (��ʿ�����߳� != null) {
            ��ʿ�����߳�.cancel(true);
            ��ʿ�����߳� = null;
            if (a == 1) {
                ���(102000000);
                String ��Ϣ = "����ֹͣ�ˣ���ʿ���䷱�ٶȴ���½���";
                System.err.println("[�����]" + CurrentReadable_Time() + " : [���򾯱�] : " + ��Ϣ);
                Ⱥ֪ͨ("[���򾯱�] : " + ��Ϣ);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���򾯱�] : " + ��Ϣ));
            } else {
                String ��Ϣ = "��Ȼ��ʿ���䱻�ƻ��ˣ���������Ȼ�����һЩ";
                System.err.println("[�����]" + CurrentReadable_Time() + " : [���򾯱�] : " + ��Ϣ);
                Ⱥ֪ͨ("[���򾯱�] : " + ��Ϣ);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���򾯱�] : " + ��Ϣ));
            }
        }
    }

    public static void ��ʿ��������ɹ�() {
        ��ʿ��������ɹ� = 0;
        String ��Ϣ = "��ʿ�ǳɹ��������˽�����ʿ�����ħ�壬��ϲ�����ˡ�";
        System.err.println("[�����]" + CurrentReadable_Time() + " : [����֪ͨ] : " + ��Ϣ);
        Ⱥ֪ͨ("[����֪ͨ] : " + ��Ϣ);
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[����֪ͨ] : " + ��Ϣ));
    }

    /**
     * <����ħ����ħ�����ֺ�ʼ�ƻ�ħ������>
     */
    public static void �ƻ�ħ������() {
        if (ħ�������߳� == null) {
            ħ�������߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    ChannelServer channelServer = ChannelServer.getInstance(2);
                    MapleMap mapleMap = channelServer.getMapFactory().getMap(103000000);
                    if (�ƻ�ħ������ < 10) {
                        if (mapleMap.getMonsterById(����ħ) != null) {
                            String ��Ϣ = "ħ���������ڱ��ƻ���ħ���������ڱ��ƻ������ƻ���������";
                            Ⱥ֪ͨ("[���򾯱�] : " + ��Ϣ);
                            System.err.println("[�����]" + CurrentReadable_Time() + " [���򾯱�] : " + ��Ϣ);
                            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���򾯱�] : " + ��Ϣ));
                            �ƻ�ħ������++;
                        } else {
                            �ر��ƻ�ħ������(0);
                        }
                    } else {
                        �ر��ƻ�ħ������(1);
                    }
                }
            }, 1000 * �ƻ����);
        }
    }

    public static void �ر��ƻ�ħ������(int a) {
        if (ħ�������߳� != null) {
            ħ�������߳�.cancel(true);
            ħ�������߳� = null;
            if (a == 1) {
                ���(101000000);
                String ��Ϣ = "����ֹͣ�ˣ�ħ�����ַ��ٶȴ���½���";
                Ⱥ֪ͨ("[���򾯱�] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " : [���򾯱�] : " + ��Ϣ);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���򾯱�] : " + ��Ϣ));
            } else {
                String ��Ϣ = "��Ȼħ�����ֱ��ƻ��ˣ���������Ȼ�����һЩ";
                Ⱥ֪ͨ("[���򾯱�] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " : [���򾯱�] : " + ��Ϣ);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���򾯱�] : " + ��Ϣ));
            }

        }
    }

    public static void ħ�����ַ����ɹ�() {
        ħ�����ַ����ɹ� = 0;
        String ��Ϣ = "��ʿ�ǳɹ��������˽���ħ�����ֵ�ħ�壬��ϲ�����ˡ�";
        System.err.println("[�����]" + CurrentReadable_Time() + " : [����֪ͨ] : " + ��Ϣ);
        Ⱥ֪ͨ("[����֪ͨ] : " + ��Ϣ);
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[����֪ͨ] : " + ��Ϣ));
    }
    /**
     * <
     * A���ӽ������ִ�
     *[A����]
     * ��(106010100)���ִ��Թ����
     * ��(106010000)�Թ�ͨ��
     * ��(100000000)���ִ�
     * >
     */
    public static int A������ = 0;

    public static void ���ǲ���A() {
        int ʱ = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int �� = Calendar.getInstance().get(Calendar.MINUTE);
        /**
         * <#################################��һͼ###################################>
         */
        if (ʱ == 21 && �� == 10 && A == 0) {
            //��ʼ������ħ����
            MapleParty.����ħA���� = ÿ������ * 10;
            final int · = 1;
            /**
             * <��һ���������>
             */
            for (int i = 0; i <= ÿ������; i++) {
                ���ǲ���A(1);
            }
            /**
             * <�ڶ������� 30 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 30);
                        for (int i = 0; i <= ÿ������; i++) {
                            ���ǲ���A(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            /**
             * <���������� 60 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 60);
                        for (int i = 0; i <= ÿ������; i++) {
                            ���ǲ���A(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            /**
             * <���Ĳ����� 90 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 90);
                        for (int i = 0; i <= ÿ������; i++) {
                            ���ǲ���A(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            /**
             * <���岨���� 120 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 120);
                        for (int i = 0; i <= ÿ������; i++) {
                            ���ǲ���A(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            /**
             * <���������� 150 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 150);
                        for (int i = 0; i <= ÿ������; i++) {
                            ���ǲ���A(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            /**
             * <���߲����� 180 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 180);
                        for (int i = 0; i <= ÿ������; i++) {
                            ���ǲ���A(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            /**
             * <�ڰ˲����� 210 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 210);
                        for (int i = 0; i <= ÿ������; i++) {
                            ���ǲ���A(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            /**
             * <�ھŲ����� 240 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 240);
                        for (int i = 0; i <= ÿ������; i++) {
                            ���ǲ���A(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            /**
             * <��ʮ������ 270 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 270);
                        for (int i = 0; i <= ÿ������; i++) {
                            ���ǲ���A(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
             /**
             * < 300 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 300);
                        
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
    }

    /**
     * <
     * B���ӽ�����������
     *[B����]
     * ��(107000400)����̶��
     * ��(107000300)����̶һ
     * ��(107000200)�����һ
     * ��(107000100)����ض�
     * ��(107000000)�������
     * ��(103000000)��������
     * >
     */
    public static int B������ = 0;

    public static void ���ǲ���B() {
        int ʱ = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int �� = Calendar.getInstance().get(Calendar.MINUTE);
        /**
         * <#################################��һͼ###################################>
         */
        if (ʱ == 21 && �� == 10 && B == 0) {
            //��ʼ������ħ����
            MapleParty.����ħB���� = ÿ������;
            final int · = 1;
            B������ = MapleParty.����ħB����;
            /**
             * <��һ���������>
             */
            for (int i = 0; i <= B������ / 4; i++) {
                ���ǲ���B(·);
            }
            /**
             * <�ڶ������� 60 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 60);
                        for (int i = 0; i <= B������ / 4; i++) {
                            ���ǲ���B(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            /**
             * <���������� 120 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 120);
                        for (int i = 0; i <= B������ / 4; i++) {
                            ���ǲ���B(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            /**
             * <���Ĳ����� 180 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 180);
                        for (int i = 0; i <= B������ / 4; i++) {
                            ���ǲ���B(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            String ��Ϣ = "(B����)�Ѿ��ִ�����̶����������������бƽ�";
            System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
            System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħB����);
            Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħB����);
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
            B = 1;
            /**
             * <#################################�ڶ�ͼ###################################>
             */
        } else if (ʱ == 21 && �� == 15 && B == 1) {
            if (MapleParty.����ħB���� == 0) {
                �������з����ɹ�();
            } else {
                final int · = 2;
                B������ = MapleParty.����ħB����;
                /**
                 * <��һ���������>
                 */
                for (int i = 0; i <= B������ / 4; i++) {
                    ���ǲ���B(·);
                }
                /**
                 * <�ڶ������� 60 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 60);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���B(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���������� 120 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 120);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���B(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���Ĳ����� 180 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 180);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���B(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                String ��Ϣ = "(B����)�Ѿ��ִ�����̶һ��������������бƽ�";
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħB����);
                Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħB����);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
                B = 2;
                ���(107000400);
            }
            /**
             * <#################################����ͼ###################################>
             */
        } else if (ʱ == 21 && �� == 20 && B == 2) {
            if (MapleParty.����ħB���� == 0) {
                �������з����ɹ�();
            } else {
                final int · = 3;
                B������ = MapleParty.����ħB����;
                /**
                 * <��һ���������>
                 */
                for (int i = 0; i <= B������ / 4; i++) {
                    ���ǲ���B(·);
                }
                /**
                 * <�ڶ������� 60 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 60);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���B(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���������� 120 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 120);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���B(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���Ĳ����� 180 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 180);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���B(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                String ��Ϣ = "(B����)�Ѿ��ִ������III��������������бƽ�";
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħB����);
                Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħB����);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
                B = 3;
                ���(107000300);
            }
            /**
             * <#################################����ͼ###################################>
             */
        } else if (ʱ == 21 && �� == 25 && B == 3) {
            if (MapleParty.����ħB���� == 0) {
                �������з����ɹ�();
            } else {
                final int · = 4;
                B������ = MapleParty.����ħB����;
                /**
                 * <��һ���������>
                 */
                for (int i = 0; i <= B������ / 4; i++) {
                    ���ǲ���B(·);
                }
                /**
                 * <�ڶ������� 60 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 60);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���B(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���������� 120 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 120);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���B(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���Ĳ����� 180 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 180);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���B(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                String ��Ϣ = "(B����)�Ѿ��ִ������II��������������бƽ�";
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħB����);
                Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħB����);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
                B = 4;
                ���(107000200);
            }
            /**
             * <#################################����ͼ###################################>
             */
        } else if (ʱ == 21 && �� == 30 && B == 4) {
            if (MapleParty.����ħB���� == 0) {
                �������з����ɹ�();
            } else {
                final int · = 5;
                B������ = MapleParty.����ħB����;
                /**
                 * <��һ���������>
                 */
                for (int i = 0; i <= B������ / 4; i++) {
                    ���ǲ���B(·);
                }
                /**
                 * <�ڶ������� 60 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 60);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���B(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���������� 120 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 120);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���B(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���Ĳ����� 180 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 180);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���B(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                String ��Ϣ = "(B����)�Ѿ��ִ������I��������������бƽ�";
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħB����);
                Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħB����);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
                B = 5;
                ���(107000100);
            }
            /**
             * <#################################����ͼ###################################>
             */
        } else if (ʱ == 21 && �� == 35 && B == 5) {
            if (MapleParty.����ħB���� == 0) {
                �������з����ɹ�();
            } else {
                final int · = 6;
                B������ = MapleParty.����ħB����;
                /**
                 * <��һ���������>
                 */
                for (int i = 0; i <= B������ / 4; i++) {
                    ���ǲ���B(·);
                }
                /**
                 * <�ڶ������� 60 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 60);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���B(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���������� 120 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 120);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���B(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���Ĳ����� 180 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 180);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���B(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                String ��Ϣ = "(B����)�Ѿ��ִ�������У������ƻ���������";
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħB����);
                Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħB����);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
                B = 6;
                ���(107000000);
                �ƻ���������();
            }
        }
    }

    /**
     * <
     * C���ӽ���ħ������
     *[C����]
     * ��(101010103)��ľ���ϲ�
     * ��(101010102)��ľ�֢�
     * ��(101010101)��ľ�֢�
     * ��(101010100)��ľ��I
     * ��(101010000)ħ�����ֱ���
     * ��(101000000)ħ������
     * >
     */
    public static int C������ = 0;

    public static void ���ǲ���C() {
        int ʱ = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int �� = Calendar.getInstance().get(Calendar.MINUTE);
        /**
         * <#################################��һͼ###################################>
         */
        if (ʱ == 21 && �� == 10 && C == 0) {
            MapleParty.����ħC���� = ÿ������;
            final int · = 1;
            C������ = MapleParty.����ħC����;
            /**
             * <��һ���������>
             */
            for (int i = 0; i <= C������ / 4; i++) {
                ���ǲ���C(·);
            }
            /**
             * <�ڶ������� 60 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 60);
                        for (int i = 0; i <= C������ / 4; i++) {
                            ���ǲ���C(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            /**
             * <���������� 120 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 120);
                        for (int i = 0; i <= C������ / 4; i++) {
                            ���ǲ���C(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            /**
             * <���Ĳ����� 180 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 180);
                        for (int i = 0; i <= C������ / 4; i++) {
                            ���ǲ���C(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            String ��Ϣ = "(C����)�Ѿ��ִ��ľ���ϲ㣬������ħ�����ֱƽ�";
            System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
            System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħC����);
            Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħC����);
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
            C = 1;
            /**
             * <#################################�ڶ�ͼ###################################>
             */
        } else if (ʱ == 21 && �� == 15 && C == 1) {
            if (MapleParty.����ħC���� == 0) {
                ħ�����ַ����ɹ�();
            } else {
                final int · = 2;
                C������ = MapleParty.����ħC����;
                /**
                 * <��һ���������>
                 */
                for (int i = 0; i <= C������ / 4; i++) {
                    ���ǲ���C(·);
                }
                /**
                 * <�ڶ������� 60 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 60);
                            for (int i = 0; i <= C������ / 4; i++) {
                                ���ǲ���C(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���������� 120 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 120);
                            for (int i = 0; i <= C������ / 4; i++) {
                                ���ǲ���C(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���Ĳ����� 180 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 180);
                            for (int i = 0; i <= C������ / 4; i++) {
                                ���ǲ���C(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                String ��Ϣ = "(C����)�Ѿ��ִ��ľ�֢�������ħ�����ֱƽ�";
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħC����);
                Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħC����);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
                C = 2;
                ���(101010103);
            }
            /**
             * <#################################����ͼ###################################>
             */
        } else if (ʱ == 21 && �� == 20 && C == 2) {
            if (MapleParty.����ħC���� == 0) {
                ħ�����ַ����ɹ�();
            } else {
                final int · = 3;
                C������ = MapleParty.����ħC����;
                /**
                 * <��һ���������>
                 */
                for (int i = 0; i <= C������ / 4; i++) {
                    ���ǲ���C(·);
                }
                /**
                 * <�ڶ������� 60 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 60);
                            for (int i = 0; i <= C������ / 4; i++) {
                                ���ǲ���C(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���������� 120 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 120);
                            for (int i = 0; i <= C������ / 4; i++) {
                                ���ǲ���C(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���Ĳ����� 180 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 180);
                            for (int i = 0; i <= C������ / 4; i++) {
                                ���ǲ���C(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                String ��Ϣ = "(C����)�Ѿ��ִ��ľ�֢�������ħ�����ֱƽ�";
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħC����);
                Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħC����);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
                C = 3;
                ���(101010102);
            }
            /**
             * <#################################����ͼ###################################>
             */
        } else if (ʱ == 21 && �� == 25 && C == 3) {
            if (MapleParty.����ħC���� == 0) {
                ħ�����ַ����ɹ�();
            } else {
                final int · = 4;
                C������ = MapleParty.����ħC����;
                /**
                 * <��һ���������>
                 */
                for (int i = 0; i <= C������ / 4; i++) {
                    ���ǲ���C(·);
                }
                /**
                 * <�ڶ������� 60 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 60);
                            for (int i = 0; i <= C������ / 4; i++) {
                                ���ǲ���C(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���������� 120 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 120);
                            for (int i = 0; i <= C������ / 4; i++) {
                                ���ǲ���C(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���Ĳ����� 180 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 180);
                            for (int i = 0; i <= C������ / 4; i++) {
                                ���ǲ���C(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                String ��Ϣ = "(C����)�Ѿ��ִ��ľ��I��������ħ�����ֱƽ�";
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħC����);
                Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħC����);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
                C = 4;
                ���(101010101);
            }
            /**
             * <#################################����ͼ###################################>
             */
        } else if (ʱ == 21 && �� == 30 && C == 4) {
            if (MapleParty.����ħC���� == 0) {
                ħ�����ַ����ɹ�();
            } else {
                final int · = 5;
                C������ = MapleParty.����ħC����;
                /**
                 * <��һ���������>
                 */
                for (int i = 0; i <= C������ / 4; i++) {
                    ���ǲ���C(·);
                }
                /**
                 * <�ڶ������� 60 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 60);
                            for (int i = 0; i <= C������ / 4; i++) {
                                ���ǲ���C(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���������� 120 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 120);
                            for (int i = 0; i <= C������ / 4; i++) {
                                ���ǲ���C(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���Ĳ����� 180 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 180);
                            for (int i = 0; i <= C������ / 4; i++) {
                                ���ǲ���C(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                String ��Ϣ = "(C����)�Ѿ��ִ�ħ�����ֱ�����������ħ�����ֱƽ�";
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħC����);
                Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħC����);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
                C = 5;
                ���(101010100);
            }
            /**
             * <#################################����ͼ###################################>
             */
        } else if (ʱ == 21 && �� == 35 && C == 5) {
            if (MapleParty.����ħC���� == 0) {
                ħ�����ַ����ɹ�();
            } else {
                final int · = 6;
                C������ = MapleParty.����ħC����;
                /**
                 * <��һ���������>
                 */
                for (int i = 0; i <= C������ / 4; i++) {
                    ���ǲ���C(·);
                }
                /**
                 * <�ڶ������� 60 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 60);
                            for (int i = 0; i <= C������ / 4; i++) {
                                ���ǲ���C(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���������� 120 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 120);
                            for (int i = 0; i <= C������ / 4; i++) {
                                ���ǲ���C(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���Ĳ����� 180 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 180);
                            for (int i = 0; i <= C������ / 4; i++) {
                                ���ǲ���C(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                String ��Ϣ = "(C����)�Ѿ��ִ�ħ�����֣������ƻ�ħ������";
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħC����);
                Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħC����);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
                C = 6;
                ���(101010000);
                �ƻ�ħ������();
            }
        }
    }

    /**
     * <
     * D���ӽ�����ʿ����
     * ��(106000300)��ʿ�����Թ����
     * ��(106000200)����Ͽ�Ȣ�
     * ��(106000100)����Ͽ�ȶ�
     * ��(106000000)����Ͽ��һ
     * ��(102000000)��ʿ����
     *
     * >
     */
    public static int D������ = 0;

    public static void ���ǲ���D() {
        int ʱ = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int �� = Calendar.getInstance().get(Calendar.MINUTE);
        /**
         * <#################################��һͼ###################################>
         */
        if (ʱ == 21 && �� == 10 && D == 0) {
            //��ʼ������ħ����
            MapleParty.����ħD���� = ÿ������;
            final int · = 1;
            D������ = MapleParty.����ħD����;
            /**
             * <��һ���������>
             */
            for (int i = 0; i <= D������ / 4; i++) {
                ���ǲ���B(·);
            }
            /**
             * <�ڶ������� 60 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 60);
                        for (int i = 0; i <= D������ / 4; i++) {
                            ���ǲ���D(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            /**
             * <���������� 120 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 120);
                        for (int i = 0; i <= D������ / 4; i++) {
                            ���ǲ���D(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            /**
             * <���Ĳ����� 180 ������>
             */
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 180);
                        for (int i = 0; i <= B������ / 4; i++) {
                            ���ǲ���D(·);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
            String ��Ϣ = "(D����)�Ѿ��ִ���ʿ�����Թ���ڣ���������ʿ����ƽ�";
            System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
            System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħD����);
            Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħD����);
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
            D = 1;
            /**
             * <#################################�ڶ�ͼ###################################>
             */
        } else if (ʱ == 21 && �� == 15 && D == 1) {
            if (MapleParty.����ħD���� == 0) {
                ��ʿ��������ɹ�();
            } else {
                final int · = 2;
                D������ = MapleParty.����ħD����;
                /**
                 * <��һ���������>
                 */
                for (int i = 0; i <= D������ / 4; i++) {
                    ���ǲ���B(·);
                }
                /**
                 * <�ڶ������� 60 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 60);
                            for (int i = 0; i <= D������ / 4; i++) {
                                ���ǲ���D(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���������� 120 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 120);
                            for (int i = 0; i <= D������ / 4; i++) {
                                ���ǲ���D(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���Ĳ����� 180 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 180);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���D(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                String ��Ϣ = "(D����)�Ѿ��ִ�����Ͽ�Ȣ���������ʿ����ƽ�";
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħD����);
                Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħD����);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
                D = 2;
                ���(106000300);
            }
            /**
             * <#################################����ͼ###################################>
             */
        } else if (ʱ == 21 && �� == 20 && D == 2) {
            if (MapleParty.����ħD���� == 0) {
                ��ʿ��������ɹ�();
            } else {
                final int · = 3;
                D������ = MapleParty.����ħD����;
                /**
                 * <��һ���������>
                 */
                for (int i = 0; i <= D������ / 4; i++) {
                    ���ǲ���B(·);
                }
                /**
                 * <�ڶ������� 60 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 60);
                            for (int i = 0; i <= D������ / 4; i++) {
                                ���ǲ���D(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���������� 120 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 120);
                            for (int i = 0; i <= D������ / 4; i++) {
                                ���ǲ���D(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���Ĳ����� 180 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 180);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���D(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                String ��Ϣ = "(D����)�Ѿ��ִ�����Ͽ�Ȣ���������ʿ����ƽ�";
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħD����);
                Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħD����);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
                D = 3;
                ���(106000200);
            }
            /**
             * <#################################����ͼ###################################>
             */
        } else if (ʱ == 21 && �� == 25 && D == 3) {
            if (MapleParty.����ħD���� == 0) {
                ��ʿ��������ɹ�();
            } else {
                final int · = 4;
                D������ = MapleParty.����ħD����;
                /**
                 * <��һ���������>
                 */
                for (int i = 0; i <= D������ / 4; i++) {
                    ���ǲ���B(·);
                }
                /**
                 * <�ڶ������� 60 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 60);
                            for (int i = 0; i <= D������ / 4; i++) {
                                ���ǲ���D(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���������� 120 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 120);
                            for (int i = 0; i <= D������ / 4; i++) {
                                ���ǲ���D(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���Ĳ����� 180 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 180);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���D(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                String ��Ϣ = "(D����)�Ѿ��ִ�����Ͽ�Ȣ���������ʿ����ƽ�";
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħD����);
                Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħD����);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
                D = 4;
                ���(106000100);
            }
            /**
             * <#################################����ͼ###################################>
             */
        } else if (ʱ == 21 && �� == 30 && D == 4) {
            if (MapleParty.����ħD���� == 0) {
                ��ʿ��������ɹ�();
            } else {
                final int · = 5;
                D������ = MapleParty.����ħD����;
                /**
                 * <��һ���������>
                 */
                for (int i = 0; i <= D������ / 4; i++) {
                    ���ǲ���B(·);
                }
                /**
                 * <�ڶ������� 60 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 60);
                            for (int i = 0; i <= D������ / 4; i++) {
                                ���ǲ���D(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���������� 120 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 120);
                            for (int i = 0; i <= D������ / 4; i++) {
                                ���ǲ���D(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                /**
                 * <���Ĳ����� 180 ������>
                 */
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 180);
                            for (int i = 0; i <= B������ / 4; i++) {
                                ���ǲ���D(·);
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
                String ��Ϣ = "(D����)�Ѿ��ִ���ʿ���䣬�����ƻ���ʿ����";
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : " + ��Ϣ);
                System.err.println("[�����]" + CurrentReadable_Time() + " [ħ�幥��] : ���ӱ��� " + MapleParty.����ħD����);
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ħ�幥��] : " + ��Ϣ));
                Ⱥ֪ͨ("[ħ�幥��] : " + ��Ϣ + " ���� " + MapleParty.����ħD����);
                D = 5;
                ���(106000000);
                �ƻ���ʿ����();
            }
        }
    }

    /**
     * <D���ӽ���> @param a
     *
     * @param a
     */
    public static void ���ǲ���D(int a) {
        ChannelServer channelServer = ChannelServer.getInstance(2);
        MapleMonster mapleMonster = MapleLifeFactory.getMonster(����ħ);
        switch (a) {
            //��ʿ�����Թ����
            case 1: {
                int ��ͼ = 106000300;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(D1����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //����Ͽ�Ȣ�
            case 2: {
                int ��ͼ = 106000200;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(D2����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //����Ͽ�ȶ�
            case 3: {
                int ��ͼ = 106000100;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(D3����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //����Ͽ��һ
            case 4: {
                int ��ͼ = 106000000;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(D4����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //��ʿ����
            case 5: {
                int ��ͼ = 102000000;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(D5����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            default:
                break;
        }
    }

    /**
     * <C���ӽ���> @param a
     */
    public static void ���ǲ���C(int a) {
        ChannelServer channelServer = ChannelServer.getInstance(2);
        MapleMonster mapleMonster = MapleLifeFactory.getMonster(����ħ);
        switch (a) {
            //��ľ���ϲ�
            case 1: {
                int ��ͼ = 101010103;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(C1����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //��ľ�֢�
            case 2: {
                int ��ͼ = 101010102;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(C2����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //��ľ�֢�
            case 3: {
                int ��ͼ = 101010101;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(C3����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //��ľ��I
            case 4: {
                int ��ͼ = 101010100;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(C4����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //ħ�����ֱ���
            case 5: {
                int ��ͼ = 101010000;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(C5����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //ħ������
            case 6: {
                int ��ͼ = 101000000;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(C6����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            default:
                break;
        }
    }

    /**
     * <B���ӽ���> @param a
     */
    public static void ���ǲ���B(int a) {
        ChannelServer channelServer = ChannelServer.getInstance(2);
        MapleMonster mapleMonster = MapleLifeFactory.getMonster(����ħ);
        switch (a) {
            //����̶��
            case 1: {
                int ��ͼ = 107000400;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(B1����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //����̶һ
            case 2: {
                int ��ͼ = 107000300;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(B2����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //�����һ
            case 3: {
                int ��ͼ = 107000200;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(B3����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //����ض�
            case 4: {
                int ��ͼ = 107000100;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(B4����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //�������
            case 5: {
                int ��ͼ = 107000000;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(B5����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //��������
            case 6: {
                int ��ͼ = 103000000;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(B6����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            default:
                break;
        }
    }

    /**
     * <A���ӽ���> @param a
     */
    public static void ���ǲ���A(int a) {
        ChannelServer channelServer = ChannelServer.getInstance(2);
        MapleMonster mapleMonster = MapleLifeFactory.getMonster(����ħ);
        switch (a) {
            //���ִ��Թ����
            case 1: {
                int ��ͼ = 106010100;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(A1����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //�Թ�ͨ��
            case 2: {
                int ��ͼ = 106010000;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(A2����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            //���ִ�
            case 3: {
                int ��ͼ = 100000000;
                MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
                mapleMonster.setPosition(A3����);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }

            default:
                break;
        }
    }

    /**
     * <��֣���ͼ�����ˢ�µ�ͼ���>
     */
    public static void ���(int a) {
        ChannelServer channelServer = ChannelServer.getInstance(2);
        MapleMap mapleMap = channelServer.getMapFactory().getMap(a);
        mapleMap.resetFully();
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() == a) {
                    chr.changeMap(a, 0);
                }
            }
        }
    }

}
