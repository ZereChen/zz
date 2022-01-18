package gui;

import client.MapleCharacter;
import database.DatabaseConnection;
import static gui.QQͨ��.Ⱥ֪ͨ;
import static gui.�ħ�幥��1.A1����;
import static gui.�ħ�幥��1.A2����;
import static gui.�ħ�幥��1.A3����;
import static gui.�ħ�幥��1.����ħ;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import handling.world.World;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.concurrent.ScheduledFuture;
import server.Timer;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import server.maps.MapleMap;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;

public class �ħ�幥�� {

    public static int ����ħ = 8150000;
    public static int ÿ������ = 20;//10��
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
                    if (ʱ == 21 && �� == 10) {
                        if (���ִ�1 == null) {
                            ���ִ幥��1();
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
    public static ScheduledFuture<?> ���ִ�1 = null;
    public static ScheduledFuture<?> ���ִ�2 = null;
    public static ScheduledFuture<?> ���ִ�3 = null;
    public static int A���� = 0;

    public static void ���ִ幥��1() {
        if (���ִ�1 == null) {
            String ��Ϣ = "����ħ������ڽ������ִ��Թ���ڼ��� ";
            System.err.println("[�����]" + CurrentReadable_Time() + " : [���򾯱�] : " + ��Ϣ);
            Ⱥ֪ͨ("[���򾯱�] : " + ��Ϣ);
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���򾯱�] : " + ��Ϣ));
            ���ִ�1 = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    if (MapleParty.����ħA���� <= 500) {
                        ���ǲ���A(1);
                        MapleParty.����ħA����++;
                    } else if (���ִ�1 != null) {
                        ���ִ�1.cancel(true);
                        ���ִ�1 = null;
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000 * 60);
                                    if (MapleParty.����ħA���� > 0) {
                                        ���(106010100);
                                        ���ִ幥��2();
                                    } else {
                                        ���ִ�����ɹ�();
                                    }
                                } catch (InterruptedException e) {
                                }
                            }
                        }.start();
                    }
                }
            }, 1000 * 1);
        }
    }
    public static int ���ִ�2���� = 0;

    public static void ���ִ幥��2() {
        if (���ִ�2 == null) {
            String ��Ϣ = "����ħ����Ѿ��ִ��Թ�ͨ�����˴α������ " + MapleParty.����ħA���� + " ";
            System.err.println("[�����]" + CurrentReadable_Time() + " : [���򾯱�] : " + ��Ϣ);
            Ⱥ֪ͨ("[���򾯱�] : " + ��Ϣ);
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���򾯱�] : " + ��Ϣ));
            ���ִ�2 = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    if (���ִ�2���� <= MapleParty.����ħA����) {
                        ���ǲ���A(2);
                        ���ִ�2����++;
                    } else if (���ִ�2 != null) {
                        ���ִ�2.cancel(true);
                        ���ִ�2 = null;
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000 * 60);
                                    if (MapleParty.����ħA���� > 0) {
                                        ���(106010000);
                                        ���ִ幥��3();
                                    } else {
                                        ���ִ�����ɹ�();
                                    }
                                } catch (InterruptedException e) {
                                }
                            }
                        }.start();
                    }
                }
            }, 1000 * 1);
        }
    }
    public static int ���ִ�3���� = 0;

    public static void ���ִ幥��3() {
        String ��Ϣ = "����ħ����Ѿ��ִ����ִ壬�˴α������ " + MapleParty.����ħA���� + " ";
        System.err.println("[�����]" + CurrentReadable_Time() + " : [���򾯱�] : " + ��Ϣ);
        Ⱥ֪ͨ("[���򾯱�] : " + ��Ϣ);
        if (���ִ�3 == null) {
            ���ִ�3 = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    if (���ִ�3���� <= MapleParty.����ħA����) {
                        ���ǲ���A(3);
                        ���ִ�3����++;
                    } else if (���ִ�3 != null) {
                        ���ִ�3.cancel(true);
                        ���ִ�3 = null;
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000 * 60);
                                    if (MapleParty.����ħA���� > 0) {
                                        �ƻ����ִ�();
                                    } else {
                                        ���ִ�����ɹ�();
                                    }
                                } catch (InterruptedException e) {
                                }
                            }
                        }.start();
                    }
                }
            }, 1000 * 1);
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
                            int ���ٶ� = Getcharactera("���ִ己�ٶ�", 1);
                            int ���� = (int) (���ٶ� * 0.1);
                            if (���ٶ� > 10000) {
                                Gaincharactera("���ִ己�ٶ�", 1, -����);
                            }
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

    public static void Gaincharactera(String Name, int Channale, int Piot) {
        try {
            int ret = Getcharactera(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO charactera (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);

                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e);
                    }
                }
            }
            ret += Piot;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE charactera SET `Point` = ? WHERE Name = ? and channel = ?");
            ps.setInt(1, ret);
            ps.setString(2, Name);
            ps.setInt(3, Channale);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("Getcharactera!!55" + sql);
        }
    }

    public static int Getcharactera(String Name, int Channale) {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM charactera WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }
}
