package gui;

import client.MapleCharacter;
import handling.channel.ChannelServer;
import java.awt.Point;
import java.util.Calendar;
import java.util.concurrent.ScheduledFuture;
import server.Timer;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import server.maps.MapleMap;
import tools.MaplePacketCreator;

public class ��������� {

    /**
     * <��������> 2230107
     */
    public static ScheduledFuture<?> ���������߳� = null;
    public static int ���� = 2230107;
    public static int ��ͼ = 230000000;

    public static void ��������() {
        if (���������߳� == null) {
            ���������߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    int ʱ = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                    int �� = Calendar.getInstance().get(Calendar.MINUTE);
                    int ���� = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                    if (���� > 1 && ���� < 7) {
                        if (ʱ == 21 && �� >= 50) {
                            �ر����������߳�();
                        }
                    } else if (ʱ == 21 && �� >= 40) {
                        �ر����������߳�();
                    }
                    for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                        for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                            if (chr == null) {
                                continue;
                            }
                            if (chr.getMapId() == ��ͼ) {
                                if (chr.getClient().getChannel() == 2) {
                                    chr.��������++;
                                    if (chr.�������� > 0) {
                                        for (int i = 0; i <= gui.Start.ConfigValuesMap.get("��������"); i++) {
                                            �ٻ�����(chr.getPosition());
                                        }
                                        chr.getClient().sendPacket(MaplePacketCreator.sendHint("#b<���ֵ>\r\n\r\n#e#r " + chr.�������� + "\r\n", 200, 6));
                                        if (gui.Start.ConfigValuesMap.get("���㾭�鿪��") == 0) {
                                            chr.gainExp(chr.�������� * gui.Start.ConfigValuesMap.get("���㾭�鱶��"), true, true, false);
                                        }
                                        if (gui.Start.ConfigValuesMap.get("�����ȯ����") == 0) {
                                            chr.modifyCSPoints(1, chr.�������� * gui.Start.ConfigValuesMap.get("�����ȯ����"), true);
                                        }
                                        if (gui.Start.ConfigValuesMap.get("�����ҿ���") == 0) {
                                            chr.gainMeso(chr.�������� * gui.Start.ConfigValuesMap.get("�����ұ���"), true, true, true);
                                        }
                                    } else {
                                        chr.dropMessage(5, "���ܹ���Ĺ�����");
                                    }
                                } else {
                                    chr.dropMessage(5, "���� 2 Ƶ���μ� �������� ���");
                                }
                            }
                        }
                    }
                }
            }, 1000 * 30);
        }
    }

    public static void �ٻ�����() {
        ChannelServer channelServer = ChannelServer.getInstance(2);
        MapleMonster mapleMonster = MapleLifeFactory.getMonster(����);
        MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
        mapleMonster.setPosition(new Point(255, 340));
        mapleMonster.setHp(1000 * 10000 * 10000);
        mapleMap.spawnMonster(mapleMonster, -2);

    }

    public static void �ٻ�����(Point a) {
        ChannelServer channelServer = ChannelServer.getInstance(2);
        MapleMonster mapleMonster = MapleLifeFactory.getMonster(����);
        MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
        mapleMonster.setPosition(a);
        mapleMonster.setHp(1000);
        mapleMonster.setExp(-100000000);
        mapleMap.spawnMonster(mapleMonster, -2);
    }

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

    public static void �ر����������߳�() {
        if (���������߳� != null) {
            ���������߳�.cancel(false);
            ���������߳� = null;
            ���(��ͼ);
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                    if (chr == null) {
                        continue;
                    }
                    chr.startMapEffect("[��������]: ��������������Ѿ�������", 5120027);
                    chr.�ر����������߳�();
                }
            }
            QQͨ��.Ⱥ֪ͨ("[��������]: ��������������Ѿ�������");
        }
    }
}
