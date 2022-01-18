package gui;

import static gui.QQͨ��.Ⱥ֪ͨ;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import handling.world.World;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import server.maps.MapleMap;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;

public class �Ұ��ͨ�� {

    public static void ���ͨ��() {
        if (gui.Start.ConfigValuesMap.get("Ұ��ͨ������") == 0) {
            int a = (int) Math.ceil(Math.random() * 25);
            int[][] ͨ�� = {
                {2220000, 104000400},
                {5220001, 110040000},
                {7220000, 250010304},
                {8220000, 200010300},
                {7220002, 250010503},
                {7220001, 222010310},
                {6220000, 107000300},
                {5220002, 100040105},
                {5220003, 220050100},
                {6220001, 221040301},
                {8220003, 240040401},
                {3220001, 260010201},
                {8220002, 261030000},
                {4220000, 230020100},
                {6130101, 100000005},
                {6300005, 105070002},
                {8130100, 105090900},
                {9400205, 800010100},
                {9400120, 801030000},
                {8220001, 211040101},
                {8180000, 240020401},
                {8180001, 240020101},
                {8220006, 270030500},
                {8220005, 270020500},
                {8220004, 270010500},
                {3220000, 101030404}
            };
            MapleParty.ͨ��BOSS = ͨ��[a][0];
            MapleParty.ͨ����ͼ = ͨ��[a][1];
            ChannelServer channelServer = ChannelServer.getInstance(1);
            MapleMap mapleMap = channelServer.getMapFactory().getMap(MapleParty.ͨ����ͼ);
            MapleMonster mobName = MapleLifeFactory.getMonster(MapleParty.ͨ��BOSS);
            String ��Ϣ = "[Ұ��ͨ��] : ϵͳ������һ��ͨ������� " + mapleMap.getMapName() + " ͨ�� " + mobName.getStats().getName() + "";
            Ⱥ֪ͨ(��Ϣ);
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
            System.err.println("[�����]" + CurrentReadable_Time() + " : " + ��Ϣ);
        }
    }

}
