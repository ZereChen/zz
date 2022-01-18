package gui;

import static gui.QQͨ��.Ⱥ֪ͨ;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import handling.world.World;
import java.awt.Point;
import server.ServerProperties;
import server.maps.MapleMap;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;

public class ��������� {

    /**
     * <�������˴���>
     */
    //�������˴���
    public static int �������� = 9900001;
    //���˳��ֺ���ڵ�ʱ��/��
    public static int ����ʱ�� = 5;

    /**
     * <��������˵�ʱ�������߳�>
     */
    public static void ������������() {
        ������˳�������();
    }

    /**
     * <������������˳��ֵ�ʱ�䣬��ͼ������>
     */
    public static void ������˳�������() {
        //���Ƶ��
        int pind = (int) Math.ceil(Math.random() * Integer.parseInt(ServerProperties.getProperty("ZEV.Count")));
        //��������Ƶ��Ϊ0�����1
        if (pind == 0) {
            pind += 1;
        }
        //���ʱ��
        int huor = (int) Math.ceil(Math.random() * 23);
        if (MapleParty.��������ʱ�� == huor) {
            if (huor == 23) {
                huor -= 1;
            } else {
                huor += 1;
            }
        }
        //���������
        int rand = (int) Math.ceil(Math.random() * 52);
        //�����ͼ����
        int[][] ���� = {
            {106010000, 488, 215},
            {240010000, 905, -298},
            {240010100, 2098, -508},
            {240010200, 2920, -688},
            {240010200, 409, -688},
            {240010300, -679, -1048},
            {240010300, 222, -868},
            {240010300, 719, 32},
            {110000000, -64, 151},
            {110000000, 379, -143},
            {110010000, -1594, -113},
            {110010000, 1204, -473},
            {110020000, -1077, -113},
            {110020000, -310, -118},
            {110020000, 1167, 182},
            {110030000, -1558, 149},
            {110030000, 164, 173},
            {104040000, 1059, -687},
            {104040000, 48, -685},
            {104030000, -858, -385},
            {104030000, 1481, -985},
            {104020000, 1418, -1345},
            {104010000, 2329, -115},
            {104010002, -1401, -25},
            {100000002, -16, -475},
            {100000002, 214, -475},
            {100010000, 198, 505},
            {100030000, -3652, -205},
            {100040000, 349, 1752},
            {105050000, 2282, 1619},
            {230010300, 44, 40},
            {230010300, -1744, -320},
            {541020100, 958, -346},
            {105090301, 928, -923},
            {105040305, 1245, 2295},
            {100030000, -2977, -1465},
            {100000006, -705, 215},
            {220030000, 638, 162},
            {600000000, 5682, -632},
            {200070000, -132, -715},
            {222010201, 120, -1047},
            {100040104, 66, 812},
            {260010400, 199, -85},
            {103010000, -1088, 232},
            {230010201, -50, -17},
            {240020100, -889, -508},
            {221020000, 11, 2162},
            {105090700, 523, -181},
            {220070201, 811, 1695},
            {211040300, -289, 454},
            {541010040, 1075, -1695},
            {600020300, 1, -204},
            {261000001, -47, 64}
        };
        //���������ֵ
        MapleParty.��������ʱ�� = huor;
        MapleParty.��������Ƶ�� = pind;
        MapleParty.�������˵�ͼ = ����[rand][0];
        MapleParty.������������X = ����[rand][1];
        MapleParty.������������Y = ����[rand][2];
        //�ٻ���Ƶ��
        ChannelServer channelServer = ChannelServer.getInstance(MapleParty.��������Ƶ��);
        //�ٻ��ĵ�ͼ
        MapleMap mapleMap = channelServer.getMapFactory().getMap(MapleParty.�������˵�ͼ);
        //֪ͨ��Ϣ
        String ��Ϣ = "[��������] : һ�����ص����� " + MapleParty.��������ʱ�� + " ʱ�������� " + MapleParty.��������Ƶ�� + " Ƶ����ĳ���ط���";
        Ⱥ֪ͨ(��Ϣ);
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
        System.err.println("[�����]" + CurrentReadable_Time() + " : " + ��Ϣ);
        System.err.println("[�����]" + CurrentReadable_Time() + " : �����ڵ�ͼ: " + ����[rand][0] + "( " + mapleMap.getMapName() + " ) ����: " + ����[rand][1] + "/" + ����[rand][2]);
    }

    /**
     * <�����������ٻ��������˳���>
     */
    public static void �ٻ���������() {
        //�ٻ���Ƶ��
        ChannelServer channelServer = ChannelServer.getInstance(MapleParty.��������Ƶ��);
        //�ٻ��ĵ�ͼ
        MapleMap mapleMap = channelServer.getMapFactory().getMap(MapleParty.�������˵�ͼ);
        //�ٻ�����
        mapleMap.spawnNpc(��������, new Point(MapleParty.������������X, MapleParty.������������Y));
        //��ʱִ��
        String ��Ϣ = "[��������] : һ�����ص����˳����� " + MapleParty.��������Ƶ�� + " Ƶ��" + mapleMap.getMapName() + "��";
        Ⱥ֪ͨ(��Ϣ);
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
        System.err.println("[�����]" + CurrentReadable_Time() + " : " + ��Ϣ);
        MapleParty.�������� += 1;
        new Thread() {
            @Override
            public void run() {
                try {
                    //���õ�ʱ����������
                    Thread.sleep(1000 * 60 * ����ʱ��);
                    ɾ����������();
                } catch (InterruptedException e) {
                }
            }
        }.start();
    }

    /**
     * <ɾ���������˲��������̣߳����������һ�γ��ֵ�ʱ���ͼ������>
     */
    public static void ɾ����������() {
        //�ٻ���Ƶ��
        ChannelServer channelServer = ChannelServer.getInstance(MapleParty.��������Ƶ��);
        //�ٻ��ĵ�ͼ
        MapleMap mapleMap = channelServer.getMapFactory().getMap(MapleParty.�������˵�ͼ);
        //ɾ������
        mapleMap.removeNpc(��������);
        //���������߳�
        MapleParty.�������� = 0;
        //���������������
        ������˳�������();
    }
}
