package gui.����BOSS;

import client.MapleCharacter;
import client.MapleDisease;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import java.awt.Point;
import java.util.concurrent.ScheduledFuture;
import server.MapleItemInformationProvider;
import server.Timer;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import server.life.MobSkill;
import server.life.MobSkillFactory;
import server.maps.MapleMap;

public class ����BOSS�߳� {

    /**
     * <9223372036854775807>
     * <��ţ��Ѫ��>
     * <
     * ��������
     * ��������
     * �����䡿
     * ���ڰ���
     * ���յ���
     * ��ƶѪ��Ѫ��ʣ��1
     * ���Ƿϡ�����ʣ��1
     * ��������3�������ͼ�������
     * ����ɢ��3�����ɢ��ͼ������ң��سǣ�
     *
     * >
     */
    public static ScheduledFuture<?> ����BOSS�߳� = null;
    public static ScheduledFuture<?> ����BOSS�߳��˺� = null;
    public static ScheduledFuture<?> ȫͼ��HP = null;
    public static ScheduledFuture<?> ȫͼ��MP = null;
    public static ScheduledFuture<?> ȫͼ���� = null;
    public static int ���׺���ţ���� = 9500337;
    public static int ���� = 2230107;
    public static int ��ͼ = 104000400;
    public static int Ƶ�� = 2;
    public static Point ���� = new Point(232, 185);

    public static void ��������BOSS�߳�() {
        �ٻ�����();
        ��������BOSS�߳��˺�();
        if (����BOSS�߳� == null) {
            ����BOSS�߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    double ��� = Math.ceil(Math.random() * 20);
                    if (��� <= 0) {
                        ȫͼ��HP();
                    } else if (��� == 1) {
                        ȫͼ��MP();
                    } else if (��� == 2) {
                        ȫͼ����();
                    } else if (��� == 3) {
                        ȫͼ�ڰ�();
                    } else if (��� == 4) {
                        ȫͼ����();
                    } else if (��� == 5) {
                        ȫͼ����();
                    } else if (��� == 6) {
                        ȫͼ�յ�();
                        ����Ѫ��();
                    } else if (��� == 7) {
                        ȫͼ�յ�();
                    } else if (��� == 8) {
                        ȫͼ�յ�();
                    } else if (��� == 9) {
                        ȫͼ��HP();
                        ȫͼ�յ�();
                    } else if (��� == 10 || ��� == 11 || ��� == 12 || ��� == 13) {
                        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                                if (chr == null) {
                                    continue;
                                }
                                if (chr.getMapId() == ��ͼ && chr.getClient().getChannel() == Ƶ��) {
                                    chr.startMapEffect("������BOSS�� : ����ţ��ʹ�ó��ڰ�ħ����3��������ڳ������ˡ�", 5120027);
                                }
                            }
                        }
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000 * 3);
                                    ֱ������();
                                } catch (InterruptedException e) {
                                }
                            }
                        }.start();
                    } else if (��� == 14 || ��� == 15 || ��� == 16 || ��� == 17) {
                        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                                if (chr == null) {
                                    continue;
                                }
                                if (chr.getMapId() == ��ͼ && chr.getClient().getChannel() == Ƶ��) {
                                    chr.startMapEffect("������BOSS�� : ����ţ��ʹ�ó��ڰ�ħ����3�����ɢ�ڳ������ˡ�", 5120027);
                                }
                            }
                        }
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000 * 3);
                                    ֱ����ɢ();
                                } catch (InterruptedException e) {
                                }
                            }
                        }.start();
                    } else {
                        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                                if (chr == null) {
                                    continue;
                                }
                                if (chr.getMapId() == ��ͼ && chr.getClient().getChannel() == Ƶ��) {
                                    chr.startMapEffect("������BOSS�� : ����ţ��ʹ�ó��ڰ�ħ�����յ���ҿ��Ի��๥����", 5120027);
                                }
                            }
                        }
                        MapleParty.�����˺� += 1;
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000 * 10);
                                    MapleParty.�����˺� = 0;
                                } catch (InterruptedException e) {
                                }
                            }
                        }.start();

                    }
                }
            }, 1000 * 40);
        }
    }

    public static void �رս���BOSS�߳�() {
        if (����BOSS�߳� != null) {
            �رս���BOSS�߳��˺�();
            ����BOSS�߳�.cancel(false);
            ����BOSS�߳� = null;
        }
    }

    public static void ��������BOSS�߳��˺�() {
        if (����BOSS�߳��˺� == null) {
            ����BOSS�߳��˺� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    int ��� = (int) Math.ceil(Math.random() * 2);
                    switch (���) {
                        case 0:
                            ����Ѫ��();
                            break;
                        case 1:
                            ��������();
                            break;
                        default:
                            ����Ѫ��();
                            ��������();
                            break;
                    }
                }
            }, 1000 * 4);
        }
    }

    public static void �رս���BOSS�߳��˺�() {
        if (����BOSS�߳��˺� != null) {
            ����BOSS�߳��˺�.cancel(false);
            ����BOSS�߳��˺� = null;
        }
    }

    public static void ֱ����ɢ() {
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() == ��ͼ && chr.getClient().getChannel() == Ƶ��) {
                    double y = chr.getPosition().getY();
                    if (y == -355 || y == -85 || y == 185 || y == 455 || y == 395 || y == 335 || y == 515) {
                        MapleItemInformationProvider.getInstance().getItemEffect(2030000).applyReturnScroll(chr);
                        chr.dropMessage(5, "ֱ����ɢ");
                    } else {
                        chr.dropMessage(5, "�����ֱ����ɢ");
                    }
                }
            }
        }
    }

    public static void ֱ������() {
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() == ��ͼ && chr.getClient().getChannel() == Ƶ��) {
                    double y = chr.getPosition().getY();
                    if (y == -355 || y == -85 || y == 185 || y == 455 || y == 395 || y == 335 || y == 515) {
                        chr.addHP(-30000);
                        chr.dropMessage(5, "ֱ������ HP - 999999999");
                    } else {
                        chr.dropMessage(5, "�����ֱ������");
                    }
                }
            }
        }
    }

    public static void ����Ѫ��() {
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() == ��ͼ && chr.getClient().getChannel() == Ƶ��) {
                    double y = chr.getPosition().getY();
                    if (y == -355 || y == -85 || y == 185 || y == 455 || y == 395 || y == 335 || y == 515) {
                        int Ѫ�� = (int) Math.ceil(Math.random() * 30000);
                        if (chr.getEquippedFuMoMap().get(21) != null) {
                            long ��ħ���� = (long) (Ѫ�� / 100 * chr.getEquippedFuMoMap().get(21));
                            Ѫ�� -= ��ħ����;
                        }
                        chr.addHP(-Ѫ��);
                        chr.dropMessage(5, "HP - " + Ѫ��);
                    } else {
                        int Ѫ�� = (int) Math.ceil(Math.random() * 30000);
                        if (chr.getEquippedFuMoMap().get(21) != null) {
                            long ��ħ���� = (long) (Ѫ�� / 100 * chr.getEquippedFuMoMap().get(21));
                            Ѫ�� -= ��ħ����;
                        }
                        chr.addHP(-Ѫ�� / 2);
                        chr.dropMessage(5, "HP - " + Ѫ�� / 2);
                    }
                }
            }
        }
    }

    public static void ��������() {
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() == ��ͼ && chr.getClient().getChannel() == Ƶ��) {
                    double y = chr.getPosition().getY();
                    int ���� = (int) Math.ceil(Math.random() * 30000);
                    if (y == -355 || y == -85 || y == 185 || y == 455 || y == 395 || y == 335 || y == 515) {
                        chr.addMP(-����);
                        chr.dropMessage(5, "HP - " + ����);
                    } else {
                        chr.addMP(-���� / 2);
                        chr.dropMessage(5, "HP - " + ���� / 2);
                    }
                }
            }
        }
    }

    public static void ȫͼ�յ�() {
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() == ��ͼ && chr.getClient().getChannel() == Ƶ��) {
                    if (chr.getJob() != 230 || chr.getJob() != 231 || chr.getJob() != 232) {
                        MobSkill mobSkill = MobSkillFactory.getMobSkill(128, 1);
                        MapleDisease disease = null;
                        disease = MapleDisease.getBySkill(128);
                        chr.giveDebuff(disease, mobSkill);
                        chr.dropMessage(5, "���յ�");
                    } else {
                        chr.dropMessage(5, "����ְҵȺ�����߱��յ�");
                    }
                }
            }
        }
    }

    public static void ȫͼ����() {
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() == ��ͼ && chr.getClient().getChannel() == Ƶ��) {
                    MobSkill mobSkill = MobSkillFactory.getMobSkill(124, 1);
                    MapleDisease disease = null;
                    disease = MapleDisease.getBySkill(124);
                    chr.giveDebuff(disease, mobSkill);
                    chr.dropMessage(5, "������");
                }
            }
        }
    }

    public static void ȫͼ����() {
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() == ��ͼ && chr.getClient().getChannel() == Ƶ��) {
                    MobSkill mobSkill = MobSkillFactory.getMobSkill(122, 1);
                    MapleDisease disease = null;
                    disease = MapleDisease.getBySkill(122);
                    chr.giveDebuff(disease, mobSkill);
                    chr.dropMessage(5, "������");
                }
            }
        }
    }

    public static void ȫͼ�ڰ�() {
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() == ��ͼ && chr.getClient().getChannel() == Ƶ��) {
                    MobSkill mobSkill = MobSkillFactory.getMobSkill(121, 1);
                    MapleDisease disease = null;
                    disease = MapleDisease.getBySkill(121);
                    chr.giveDebuff(disease, mobSkill);
                    chr.dropMessage(5, "���ڰ�");
                }
            }
        }
    }

    public static void ȫͼ����() {
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() == ��ͼ && chr.getClient().getChannel() == Ƶ��) {
                    MobSkill mobSkill = MobSkillFactory.getMobSkill(120, 1);
                    MapleDisease disease = null;
                    disease = MapleDisease.getBySkill(120);
                    chr.giveDebuff(disease, mobSkill);
                    chr.dropMessage(5, "������");
                }
            }
        }

    }

    public static void ȫͼ��HP() {
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() == ��ͼ && chr.getClient().getChannel() == Ƶ��) {
                    chr.setHp(1);
                }
            }
        }
    }

    public static void ȫͼ��MP() {
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() == ��ͼ && chr.getClient().getChannel() == Ƶ��) {
                    chr.setMp(1);
                }
            }
        }
    }

    public static void �ٻ�����() {
        ChannelServer channelServer = ChannelServer.getInstance(Ƶ��);
        MapleMonster mapleMonster = MapleLifeFactory.getMonster(���׺���ţ����);
        MapleMap mapleMap = channelServer.getMapFactory().getMap(��ͼ);
        //���ù�������
        mapleMonster.setPosition(����);
        mapleMap.spawnMonster(mapleMonster, -2);
    }
}
