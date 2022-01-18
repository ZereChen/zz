package gui;

import static abc.Game.����;
import client.MapleCharacter;
import static gui.QQͨ��.Ⱥ֪ͨ;
import handling.channel.ChannelServer;
import handling.world.World;
import java.util.Calendar;
import java.util.concurrent.ScheduledFuture;
import server.Timer;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import server.maps.MapleMap;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;

public class �ħ������ {

    public static ScheduledFuture<?> �ħ���ɧ�� = null;
    public static Boolean ���� = false;//true//false
    public static int x = 1;

    /**
     * <
     * >
     */
    public static int ����ħ = 8150000;
    public static int S = 0;
 public static void main(String args[]) {
        ħ�������߳�();
    }
    public static void ħ�������߳�() {
        if (�ħ���ɧ�� == null) {
            System.err.println("[�����]" + CurrentReadable_Time() + " : [ħ������] : ħ���Ѿ���Ȼ�ӽ�ð�մ�½�����λð�ռҴ������ǵȰ�ȫ����");
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(1, "[ð�վ���]\r\nħ���Ѿ���Ȼ�ӽ�ð�մ�½�����λð�ռҴ������ǵȰ�ȫ����"));
            Ⱥ֪ͨ("[ð�վ���] : ħ���Ѿ���Ȼ�ӽ�ð�մ�½�����λð�ռҴ������ǵȰ�ȫ����");
            �ħ���ɧ�� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    if (S > 0) {
                        x++;
                        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                                if (chr == null) {
                                    continue;
                                }
                                final double ��� = Math.ceil(Math.random() * 100);
                                int ��Ϯ�� = 100;
                                int �ȼ� = chr.getLevel();
                                if (�ȼ� >= 10 && �ȼ� <= 30) {
                                    ��Ϯ�� -= 100;
                                } else if (�ȼ� > 30 && �ȼ� <= 70) {
                                    ��Ϯ�� -= 30;
                                } else if (�ȼ� > 70 && �ȼ� <= 120) {
                                    ��Ϯ�� -= 40;
                                } else if (�ȼ� > 120 && �ȼ� <= 150) {
                                    ��Ϯ�� -= 50;
                                } else if (�ȼ� > 150 && �ȼ� <= 200) {
                                    ��Ϯ�� -= 60;
                                } else if (�ȼ� > 200) {
                                    ��Ϯ�� -= 70;
                                }
                                int ��ͼ���� = chr.getMap().getCharactersSize();
                                if (��ͼ���� > 3) {
                                    ��Ϯ�� -= 30;
                                } else if (��ͼ���� > 1) {
                                    ��Ϯ�� -= 10;
                                }
                                if (chr.getMapId() >= 100000000) {
                                    if (��� <= ��Ϯ��) {
                                        if (chr.getMapId() > 910000024 || chr.getMapId() < 910000000) {
                                            if (!����(chr.getMapId())) {
                                                MapleMonster mob1 = MapleLifeFactory.getMonster(����ħ);
                                                chr.getMap().spawnMonsterOnGroundBelow(mob1, chr.getPosition());
                                                MapleMonster mob2 = MapleLifeFactory.getMonster(����ħ);
                                                chr.getMap().spawnMonsterOnGroundBelow(mob2, chr.getPosition());
                                                MapleMonster mob3 = MapleLifeFactory.getMonster(����ħ);
                                                chr.getMap().spawnMonsterOnGroundBelow(mob3, chr.getPosition());
                                                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ð�վ���] : ��� " + chr.getName() + " �� " + chr.getMap().getMapName() + " ��ħ��Ϯ����"));
                                                chr.dropMessage(5, "�㱻ħ��Ϯ���ˡ�");
                                                Ⱥ֪ͨ("[ð�վ���] : ��� " + chr.getName() + " �� " + chr.getMap().getMapName() + " ��ħ��Ϯ��");
                                            }
                                        }
                                    } else {
                                        chr.dropMessage(5, "һ�����紵����");
                                    }
                                }
                            }
                        }
                        if (Calendar.getInstance().get(Calendar.MINUTE) >= 10) {
                            �رջħ���ɧ��();
                        }
                    } else {
                        S++;
                    }
                }
            }, 1000 * 60 * 2);
        }
    }

    public static void �رջħ���ɧ��() {
        if (�ħ���ɧ�� != null) {
            �ħ���ɧ��.cancel(true);
            �ħ���ɧ�� = null;
            Ⱥ֪ͨ("[ħ������] : ħ���Ѿ�������ȥ�����ǿ��ܻ��ٴξ�������");
            System.err.println("[�����]" + CurrentReadable_Time() + " : [ħ������] : ħ���Ѿ�������ȥ�����ǿ��ܻ��ٴξ�������");
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ð�վ���] : ħ���Ѿ�������ȥ�����ǿ��ܻ��ٴξ���������"));
        }
    }

}
