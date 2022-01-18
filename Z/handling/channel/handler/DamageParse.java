/*
��ɫ�����ļ�
 */
package handling.channel.handler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import client.ISkill;
import constants.GameConstants;
import client.inventory.IItem;
import client.MapleBuffStat;
import client.MapleCharacter;
import client.inventory.MapleInventoryType;
import client.PlayerStats;
import client.SkillFactory;
import client.anticheat.CheatingOffense;
import client.status.MonsterStatus;
import client.status.MonsterStatusEffect;
import static fumo.FumoSkill.FM;
import static gui.QQMsgServer.sendMsgToQQGroup;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import handling.world.World;
import java.util.Map;
import server.MapleStatEffect;
import server.Randomizer;
import server.life.Element;
import server.life.MapleMonster;
import server.life.MapleMonsterStats;
import server.maps.MapleMap;
import server.maps.MapleMapItem;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import pvp.MaplePvp;
import static scripting.NPCConversationManager.��ɫIDȡ����;
import tools.MaplePacketCreator;
import tools.AttackPair;
import tools.Pair;
import tools.data.LittleEndianAccessor;

public class DamageParse {

    private final static int[] charges = {1211005, 1211006};

    //������
    public static void applyAttack(final AttackInfo attack, final ISkill theSkill, final MapleCharacter player, int attackCount, final double maxDamagePerMonster, final MapleStatEffect effect, final AttackType attack_type) {
        int totDamage = 0;
        final MapleMap map = player.getMap();
        int ȫ���������� = gui.Start.ConfigValuesMap.get("ȫ����������");
        if (ȫ���������� == 0) {
            MaplePvp.doPvP(player, map, attack);
        } else if (MapleParty.�����˺� == 1) {
            if (player.getMapId() == 104000400 && player.getClient().getChannel() == 2) {
                MaplePvp.doPvP(player, map, attack);
            }
        }
        attackCount += 1;
        /**
         * <��Ǯը��>
         */
        if (attack.skill == 4211006) {
            for (AttackPair oned : attack.allDamage) {
                if (oned.attack != null) {
                    continue;
                }
                final MapleMapObject mapobject = map.getMapObject(oned.objectid, MapleMapObjectType.ITEM);
                if (mapobject != null) {
                    final MapleMapItem mapitem = (MapleMapItem) mapobject;
                    mapitem.getLock().lock();
                    try {
                        if (mapitem.getMeso() > 0) {
                            if (mapitem.isPickedUp()) {
                                return;
                            }
                            map.removeMapObject(mapitem);
                            map.broadcastMessage(MaplePacketCreator.explodeDrop(mapitem.getObjectId()));
                            mapitem.setPickedUp(true);
                        } else {
                            player.getCheatTracker().registerOffense(CheatingOffense.�����쳣);
                            return;
                        }
                    } finally {
                        mapitem.getLock().unlock();
                    }
                } else {
                    player.getCheatTracker().registerOffense(CheatingOffense.��Ǯը��_�����ڵ���);
                    return;
                }
            }
        }
        int fixeddmg, totDamageToOneMonster = 0;
        long hpMob = 0;
        final PlayerStats stats = player.getStat();
        int CriticalDamage = stats.passive_sharpeye_percent();
        byte ShdowPartnerAttackPercentage = 0;
        if (attack_type == AttackType.RANGED_WITH_SHADOWPARTNER || attack_type == AttackType.NON_RANGED_WITH_MIRROR) {
            final MapleStatEffect shadowPartnerEffect;
            if (attack_type == AttackType.NON_RANGED_WITH_MIRROR) {
                shadowPartnerEffect = player.getStatForBuff(MapleBuffStat.MIRROR_IMAGE);
            } else {
                shadowPartnerEffect = player.getStatForBuff(MapleBuffStat.SHADOWPARTNER);
            }
            if (shadowPartnerEffect != null) {
                if (attack.skill != 0 && attack_type != AttackType.NON_RANGED_WITH_MIRROR) {
                    ShdowPartnerAttackPercentage = (byte) shadowPartnerEffect.getY();
                } else {
                    ShdowPartnerAttackPercentage = (byte) shadowPartnerEffect.getX();
                }
            }
            attackCount /= 2;
        }
        byte overallAttackCount;
        double maxDamagePerHit = 0;
        MapleMonster monster;
        MapleMonsterStats monsterstats;
        boolean Tempest;
        if (gui.Start.ConfigValuesMap.get("���ټ�⿪��") != null) {
            if (gui.Start.ConfigValuesMap.get("���ټ�⿪��") == 0) {
                if (attack.skill == 3121004 || attack.skill == 5221004) {
                    if (System.currentTimeMillis() - player.�������� < 50) {
                        player.���������ж�++;
                        if (player.���������ж� >= 10) {
                            int ch = World.Find.findChannel(��ɫIDȡ����(player.getId()));
                            MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(��ɫIDȡ����(player.getId()));
                            if (target.ban("�����ټ����", player.isAdmin(), false, false)) {
                                String ��Ϣ = "[ϵͳ����] : ��� " + target.getName() + " ��Ϊʹ�÷Ƿ���������ӳټ��ٹ������ƻ���Ϸƽ�⣬��ϵͳ���÷�š�";
                                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
                                sendMsgToQQGroup(��Ϣ);
                            }
                        }
                    }
                } else if (System.currentTimeMillis() - player.�������� < 400) {
                    player.���������ж�++;
                    if (player.���������ж� >= 10) {
                        int ch = World.Find.findChannel(��ɫIDȡ����(player.getId()));
                        MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(��ɫIDȡ����(player.getId()));
                        if (target.ban("�����ټ����", player.isAdmin(), false, false)) {
                            String ��Ϣ = "[ϵͳ����] : ��� " + target.getName() + " ��Ϊʹ�÷Ƿ���������ӳټ��ٹ������ƻ���Ϸƽ�⣬��ϵͳ���÷�š�";
                            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
                            sendMsgToQQGroup(��Ϣ);
                        }
                    }
                } else if (player.���������ж� > 0) {
                    player.���������ж� = 0;
                }
                player.�������� = System.currentTimeMillis();
            }
        }
        for (final AttackPair oned : attack.allDamage) {
            monster = map.getMonsterByOid(oned.objectid);
            if (monster != null) {
                totDamageToOneMonster = 0;
                hpMob = monster.getHp();
                monsterstats = monster.getStats();
                fixeddmg = monsterstats.getFixedDamage();
                Tempest = monster.getStatusSourceID(MonsterStatus.FREEZE) == 21120006;
                maxDamagePerHit = CalculateMaxWeaponDamagePerHit(player, monster, attack, theSkill, effect, maxDamagePerMonster, CriticalDamage);
                overallAttackCount = 0;
                if (gui.Start.ConfigValuesMap.get("ȫ����⿪��") != null) {
                    if (gui.Start.ConfigValuesMap.get("ȫ����⿪��") == 0) {
                        ȫ�����(player, monster, attack);
                    }
                }
                if (gui.Start.ConfigValuesMap.get("������⿪��") != null) {
                    if (gui.Start.ConfigValuesMap.get("������⿪��") == 0) {
                        ���ܹ����������(player, effect, attack, attackCount);
                    }
                }
                if (gui.Start.ConfigValuesMap.get("Ⱥ����⿪��") != null) {
                    if (gui.Start.ConfigValuesMap.get("Ⱥ����⿪��") == 0) {
                        Ⱥ���������(player, effect, attack);
                    }
                }
                long eachd;
                StringBuilder name = new StringBuilder();
                for (Pair<Integer, Boolean> eachde : oned.attack) {
                    eachd = eachde.left;
                    overallAttackCount++;
                    long �����˺� = 0;
                    //Ӱ�Ӵ�������������Ƿֿ�һ�Ρ�
                    if (overallAttackCount - 1 == attackCount) {
                        maxDamagePerHit = (maxDamagePerHit / 100) * ShdowPartnerAttackPercentage;
                    }
                    /**
                     * <սʿ>
                     */
                    if (player.job >= 100 && player.job < 200) {
                        if (player.job == 110 || player.job == 111 || player.job == 112) {
                            if (player.F().get(FM("��������")) != null) {
                                if (player.isActiveBuffedValue(1101006)) {
                                    eachd += eachd / 100 * player.F().get(FM("��������"));
                                }
                            }
                        } else if (player.job == 120 || player.job == 120 || player.job == 122) {
                            if (player.F().get(FM("��֮�׼�")) != null) {
                                if (player.getMaxHp() <= 70) {
                                    eachd += eachd * 1.3;
                                } else if (player.getMaxHp() <= 50) {
                                    eachd += eachd * 2.5;
                                } else if (player.getMaxHp() <= 30) {
                                    eachd += eachd * 3.7;
                                }
                                if (attack.skill == 1211002) {
                                    if (player.��֮�׼��������� < 5) {
                                        player.��֮�׼���������++;
                                    } else if (player.��֮�׼��������� >= 5) {
                                        if (attack.skill == 1311006) {
                                            eachd += eachd / 100 * player.F().get(FM("��֮�׼�"));
                                            player.��֮�׼��������� = 0;
                                        }
                                    }
                                }
                            }
                        } else if (player.job == 120 || player.job == 120 || player.job == 122) {
                            if (player.F().get(FM("���Թ���")) != null) {
                                if (attack.skill == 1311005) {
                                    //����״̬�����ӻ����˺�
                                    if (player.isActiveBuffedValue(1211003) || player.isActiveBuffedValue(1211004)) {
                                        eachd += eachd / 100 * player.F().get(FM("���Թ���"));
                                    }
                                    //����״̬��������Ѫ����
                                    if (player.isActiveBuffedValue(1211005) || player.isActiveBuffedValue(1211006)) {
                                        player.addHP(player.F().get(FM("���Թ���")) * 10);
                                    }
                                    //����״̬��������ɱ
                                    if (player.isActiveBuffedValue(1211007) || player.isActiveBuffedValue(1211008)) {
                                        double r1 = Math.ceil(Math.random() * 3000);
                                        if (player.F().get(FM("���Թ���")) > r1) {
                                            if (player.getEquippedFuMoMap().get(7) != null) {
                                                maxDamagePerHit += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                                                eachd += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                                            } else {
                                                maxDamagePerHit += 10000 * 1000;
                                                eachd += 10000 * 1000;
                                            }
                                            player.dropMessage(5, "����ͨ�������һ����ɱ");
                                        }
                                    }
                                    //��ʥ״̬���ۼ��˺�
                                    if (player.isActiveBuffedValue(1221003) || player.isActiveBuffedValue(1221004)) {
                                        if (player.���Թ����ۼ��˺����� < 3) {
                                            player.���Թ����ۼ��˺� += eachd;
                                            player.���Թ����ۼ��˺�����++;
                                        }
                                        eachd += player.���Թ����ۼ��˺�;
                                    }
                                }
                            }
                        }
                    } else if (player.job >= 300 && player.job < 400) {
                        if (player.job == 310 || player.job == 311 || player.job == 312) {
                            if (player.F().get(FM("��ɨ��1")) != null) {
                                if (attack.skill == 3111006) {
                                    eachd += eachd / 100 * player.F().get(FM("��ɨ��1"));
                                }
                            }
                        } else if (player.job == 320 || player.job == 321 || player.job == 322) {
                            if (player.F().get(FM("��ɨ��2")) != null) {
                                if (attack.skill == 3211006) {
                                    eachd += eachd / 100 * player.F().get(FM("��ɨ��2"));
                                }
                            }
                        }
                    } else if (player.job >= 400 && player.job < 500) {
                        if (player.job == 410 || player.job == 411 || player.job == 412) {
                            if (player.F().get(FM("��Ҷն")) != null) {
                                if (attack.skill == 4211002) {
                                    eachd += player.��Ҷ���� / 100 * player.F().get(FM("��Ҷն"));
                                }
                            }
                        } else if (player.job == 420 || player.job == 421 || player.job == 422) {
                            if (player.F().get(FM("���ط���")) != null) {
                                if (attack.skill == 4111005) {
                                    eachd += player.�������� / 100 * player.F().get(FM("��������"));
                                }
                            }
                        }
                    } else if (player.job >= 500 && player.job < 600) {
                        if (player.job == 510 || player.job == 511 || player.job == 512) {
                            if (player.F().get(FM("���˱���")) != null) {
                                if (player.isActiveBuffedValue(5111005)) {
                                    eachd += eachd / 100 * player.F().get(FM("���˱���"));
                                }
                            }
                        } else if (player.job == 510 || player.job == 511 || player.job == 512) {
                            if (player.F().get(FM("˫ǹ����")) != null) {
                                if (attack.skill == 5211004 || attack.skill == 5211005) {
                                    eachd += eachd / 100 * player.F().get(FM("˫ǹ����"));
                                }
                            }
                        }

                    }
                    //���Ӷ���ͨ������˺�
                    if (player.F().get(FM("ǿ��")) != null && !monster.getStats().isBoss()) {
                        �����˺� += eachd / 100 * player.F().get(FM("ǿ��"));
                    }
                    if (player.F().get(FM("��ǿ��")) != null && monster.getStats().isBoss()) {
                        �����˺� += eachd / 100 * player.F().get(FM("��ǿ��"));
                    }
                    if (player.F().get(FM("ս����־")) != null) {
                        �����˺� += eachd / 100 * player.F().get(FM("ս����־"));
                    }
                    if (player.getEquippedFuMoMap().get(10) != null) {
                        �����˺� += player.getEquippedFuMoMap().get(10);
                    }
                    if (player.F().get(FM("������Ϭ")) != null) {
                        �����˺� += eachd / 100 * player.F().get(FM("������Ϭ"));
                    }
                    /**
                     * <����ģʽ-����>
                     */
                    if (gui.Start.������Ϣ����.get("����ģʽ" + player.getId() + "") != null) {
                        if (gui.Start.������Ϣ����.get("����ģʽ" + player.getId() + "") == 0) {
                            if (monster.getStats().isBoss()) {
                                int ���� = (int) (gui.Start.������Ϣ����.get("BUFF����" + player.getId() + "") * 0.0001);
                                int �������� = gui.Start.������Ϣ����.get("��������" + player.getId() + "");
                                int ħ�������� = gui.Start.������Ϣ����.get("ħ��������" + player.getId() + "");
                                �����˺� += eachd * (0.3 + (�������� + ħ��������) * ����);
                            }
                            double ����ģʽ���� = Math.ceil(Math.random() * 100);
                            int ���� = gui.Start.������Ϣ����.get("BUFF����" + player.getId() + "");
                            int ����ģʽ������ = (int) (gui.Start.������Ϣ����.get("��������" + player.getId() + "") * ���� * 0.001);
                            if (����ģʽ���� <= 30) {
                                eachd += ����ģʽ������;
                            } else {
                                int ����ģʽ�����˺��ӳ� = gui.Start.������Ϣ����.get("�������" + player.getId() + "");
                                eachd += ����ģʽ������ * 2 + ����ģʽ�����˺��ӳ�;
                            }
                            player.addHP((int) (gui.Start.������Ϣ����.get("����������" + player.getId() + "") * ���� * 0.002));
                            player.addMP((int) (gui.Start.������Ϣ����.get("ħ��������" + player.getId() + "") * ���� * 0.002));
                        }
                    }
                    if (player.job == 510 || player.job == 511 || player.job == 512) {
                        if (player.F().get(FM("˫ǹ����")) != null) {
                            if (attack.skill == 5211004 || attack.skill == 5211005) {
                                �����˺� += eachd / 100 * player.F().get(FM("˫ǹ����"));
                            }
                        }
                    }
                    if (�����˺� > 0) {
                        eachd += �����˺�;
                    }
                    if (gui.Start.ConfigValuesMap.get("����˺�") != null) {
                        if (eachd > gui.Start.ConfigValuesMap.get("����˺�") * 10000) {
                            int ch = World.Find.findChannel(��ɫIDȡ����(player.getId()));
                            MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(��ɫIDȡ����(player.getId()));
                            if (target.ban("�����˼����" + eachd, player.isAdmin(), false, false)) {
                                String ��Ϣ = "[ϵͳ����] : ��� " + target.getName() + " ��Ϊʹ�÷Ƿ�������ƻ���Ϸƽ�⣬��ϵͳ���÷�š�";
                                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
                                sendMsgToQQGroup(��Ϣ);
                            }
                        }
                    }
                    //�����¼��Ҵ��������˺�
                    if (eachd > player.����˺�) {
                        player.����˺� = eachd;
                    }
                    //����ͨ����һ����ɱ
                    if (player.getEquippedFuMoMap().get(4) != null) {
                        if (!monster.getStats().isBoss()) {
                            double r1 = Math.ceil(Math.random() * 500);
                            if (player.getEquippedFuMoMap().get(4) > r1) {
                                if (player.getEquippedFuMoMap().get(7) != null) {
                                    maxDamagePerHit += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                                    eachd += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                                } else {
                                    maxDamagePerHit += 10000 * 1000;
                                    eachd += 10000 * 1000;
                                }
                                player.dropMessage(5, "����ͨ�������һ����ɱ");
                            }
                        }
                    }
                    //�Ը߼�����һ����ɱ
                    if (player.getEquippedFuMoMap().get(5) != null) {
                        if (monster.getStats().isBoss()) {
                            double r2 = Math.ceil(Math.random() * 2000);
                            if (player.getEquippedFuMoMap().get(5) > r2) {
                                if (player.getEquippedFuMoMap().get(7) != null) {
                                    maxDamagePerHit += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                                    eachd += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                                } else {
                                    maxDamagePerHit += 10000 * 1000;
                                    eachd += 10000 * 1000;
                                }
                                player.dropMessage(5, "�Ը߼��������һ����ɱ");
                            }
                        }
                    }
                    //�����й���һ����ɱ
                    if (player.getEquippedFuMoMap().get(6) != null) {
                        double r3 = Math.ceil(Math.random() * 4000);
                        if (player.getEquippedFuMoMap().get(6) > r3) {
                            if (player.getEquippedFuMoMap().get(7) != null) {
                                maxDamagePerHit += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                                eachd += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                            } else {
                                maxDamagePerHit += 10000 * 1000;
                                eachd += 10000 * 1000;
                            }
                            player.dropMessage(5, "�Թ������һ����ɱ");
                        }
                    }
                    if (fixeddmg != -1) {
                        if (monsterstats.getOnlyNoramlAttack()) {
                            eachd = attack.skill != 0 ? 0 : fixeddmg;
                        } else {
                            eachd = fixeddmg;
                        }
                    } else if (monsterstats.getOnlyNoramlAttack()) {
                        eachd = attack.skill != 0 ? 0 : Math.min(eachd, (int) maxDamagePerHit);  // Convert to server calculated damage
                    }
                    totDamageToOneMonster += eachd;
                    //�ڰ�����ʯ����
                    if (monster.getId() == 9300021 && player.getPyramidSubway() != null) { //miss
                        player.getPyramidSubway().onMiss(player);
                    }
                    if (attack.skill == 14101006) {
                        stats.setHp(stats.getHp() + (int) (eachd * 0.04));
                    }
                }
                totDamage += totDamageToOneMonster;
                //��������
                if (player.F().get(FM("�ɱ�")) != null) {
                    double a = Math.ceil(Math.random() * 200);
                    if (a >= player.F().get(FM("�ɱ�"))) {
                        player.checkMonsterAggro(monster);
                    }
                } else {
                    player.checkMonsterAggro(monster);
                }
                if (attack.skill == 2301002 && !monsterstats.getUndead()) {
                    player.ban("�޸�WZ", true, true, false);
                    //player.��Ǵ����();
                    return;
                }
                // ������
                if (player.getBuffedValue(MapleBuffStat.PICKPOCKET) != null) {
                    switch (attack.skill) {
                        case 0:
                        case 4001334:
                        case 4201005:
                        case 4211002:
                        case 4211004:
                        case 4221003:
                        case 4221007:
                            ������(player, monster, oned);
                            break;
                    }
                }
                if (player.F().get(FM("�������")) != null) {
                    double a = Math.ceil(Math.random() * 1000);
                    if (a <= player.F().get(FM("�������"))) {
                        monster.damage(player, (monster.getStats().isBoss() ? 199999 : (monster.getHp() - 1)), true, attack.skill);
                    }
                }

                if (totDamageToOneMonster > 0) {
                    //ʥ���ô����õ�,����15ֻ���µĵ���.�ܵ������ĵ�������ֻʣ1.
                    if (attack.skill != 1221011) {
                        monster.damage(player, totDamageToOneMonster, true, attack.skill);
                    } else {
                        monster.damage(player, (monster.getStats().isBoss() ? 199999 : (monster.getHp() - 1)), true, attack.skill);
                    }
                    if (monster.isBuffed(MonsterStatus.WEAPON_DAMAGE_REFLECT)) {
                        player.addHP(-(7000 + Randomizer.nextInt(8000)));
                    }
                    //������Ѫ
                    if (player.getBuffedValue(MapleBuffStat.COMBO_DRAIN) != null) {
                        stats.setHp((stats.getHp() + ((int) Math.min(monster.getMobMaxHp(), Math.min(((int) ((double) totDamage * (double) player.getStatForBuff(MapleBuffStat.COMBO_DRAIN).getX() / 100.0)), stats.getMaxHp() / 2)))), true);
                    }
                    switch (attack.skill) {
                        //��������
                        case 4101005:
                        //������ת
                        case 5111004: {
                            stats.setHp((stats.getHp() + ((int) Math.min(monster.getMobMaxHp(), Math.min(((int) ((double) totDamage * (double) theSkill.getEffect(player.getSkillLevel(theSkill)).getX() / 100.0)), stats.getMaxHp() / 2)))), true);
                            break;
                        }
                        //����
                        case 5211006:
                        //��������
                        case 5220011: {
                            player.setLinkMid(monster.getObjectId());
                            break;
                        }
                        //��֮�׼�
                        case 1311005: {
                            final int remainingHP = stats.getHp() - totDamage * effect.getX() / 100;
                            stats.setHp(remainingHP < 1 ? 1 : remainingHP);
                            break;
                        }
                        //һ��˫��
                        case 4221007:
                        //��ɱ
                        case 4221001:
                        //��Ҷն
                        case 4211002:
                        //����ն
                        case 4201005:
                        //������
                        case 4001002:
                        //������
                        case 4001334:
                        //�����������
                        case 4121007:
                        //���ط���
                        case 4111005:
                        //˫��ն
                        case 4001344: {
                            //�����ö�Һ
                            final ISkill skill = SkillFactory.getSkill(4120005);
                            //�����ö�Һ
                            final ISkill skill2 = SkillFactory.getSkill(4220005);
                            if (player.getSkillLevel(skill) > 0) {
                                final MapleStatEffect venomEffect = skill.getEffect(player.getSkillLevel(skill));
                                MonsterStatusEffect monsterStatusEffect;

                                for (int i = 0; i < attackCount; i++) {
                                    if (venomEffect.makeChanceResult()) {
                                        if (monster.getVenomMulti() < 3) {
                                            monster.setVenomMulti((byte) (monster.getVenomMulti() + 1));
                                            monsterStatusEffect = new MonsterStatusEffect(MonsterStatus.POISON, 1, 4120005, null, false);
                                            monster.applyStatus(player, monsterStatusEffect, false, venomEffect.getDuration(), true);
                                        }
                                    }
                                }
                            } else if (player.getSkillLevel(skill2) > 0) {
                                final MapleStatEffect venomEffect = skill2.getEffect(player.getSkillLevel(skill2));
                                MonsterStatusEffect monsterStatusEffect;

                                for (int i = 0; i < attackCount; i++) {
                                    if (venomEffect.makeChanceResult()) {
                                        if (monster.getVenomMulti() < 3) {
                                            monster.setVenomMulti((byte) (monster.getVenomMulti() + 1));
                                            monsterStatusEffect = new MonsterStatusEffect(MonsterStatus.POISON, 1, 4220005, null, false);
                                            monster.applyStatus(player, monsterStatusEffect, false, venomEffect.getDuration(), true);
                                        }
                                    }
                                }
                            }
                            break;
                        }
                        //��ͨ��
                        case 4201004: {
                            monster.��ͨ��(player);
                            break;
                        }
                        //��ѹ
                        case 21101003:
                        //˫���ػ�
                        case 21000002:
                        //�����ػ�
                        case 21100001:
                        //ս��ͻ��
                        case 21100002:
                        //��������
                        case 21100004:
                        //ȫ���ӻ�
                        case 21110002:
                        //�ռ�Ͷ��
                        case 21110003:
                        //��Ӱ����
                        case 21110004:
                        //����
                        case 21110006:
                        //ȫ���ӻ�- ˫���ػ�
                        case 21110007:
                        //ȫ���ӻ� - �����ػ�
                        case 21110008:
                        //ս��֮��
                        case 21120002:
                        //��������
                        case 21120005:
                        //��ʯ�ǳ�
                        case 21120006:
                        //ս��֮��- ˫���ػ�
                        case 21120009:
                        //ս��֮�� - �����ػ�
                        case 21120010: {
                            if (player.getBuffedValue(MapleBuffStat.WK_CHARGE) != null && !monster.getStats().isBoss()) {
                                final MapleStatEffect eff = player.getStatForBuff(MapleBuffStat.WK_CHARGE);
                                if (eff != null && eff.getSourceId() == 21111005) {
                                    monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.SPEED, eff.getX(), eff.getSourceId(), null, false), false, eff.getY() * 1000, false);
                                }
                            }
                            if (player.getBuffedValue(MapleBuffStat.BODY_PRESSURE) != null && !monster.getStats().isBoss()) {
                                final MapleStatEffect eff = player.getStatForBuff(MapleBuffStat.BODY_PRESSURE);

                                if (eff != null && eff.makeChanceResult() && !monster.isBuffed(MonsterStatus.NEUTRALISE)) {
                                    monster.applyStatus(player, new MonsterStatusEffect(MonsterStatus.NEUTRALISE, 1, eff.getSourceId(), null, false), false, eff.getX() * 1000, false);
                                }
                            }
                            break;
                        }
                        default:
                            break;
                    }
                    if (totDamageToOneMonster > 0) {
                        IItem weapon_ = player.getInventory(MapleInventoryType.EQUIPPED).getItem((byte) -11);
                        if (weapon_ != null) {
                            MonsterStatus stat = GameConstants.getStatFromWeapon(weapon_.getItemId()); //10001 = acc/darkness. 10005 = speed/slow.
                            if (stat != null && Randomizer.nextInt(100) < GameConstants.getStatChance()) {
                                final MonsterStatusEffect monsterStatusEffect = new MonsterStatusEffect(stat, GameConstants.getXForStat(stat), GameConstants.getSkillForStat(stat), null, false);
                                monster.applyStatus(player, monsterStatusEffect, false, 10000, false, false);
                            }
                        }
                        if (player.getBuffedValue(MapleBuffStat.BLIND) != null) {
                            final MapleStatEffect eff = player.getStatForBuff(MapleBuffStat.BLIND);

                            if (eff.makeChanceResult()) {
                                final MonsterStatusEffect monsterStatusEffect = new MonsterStatusEffect(MonsterStatus.ACC, eff.getX(), eff.getSourceId(), null, false);
                                monster.applyStatus(player, monsterStatusEffect, false, eff.getY() * 1000, false);
                            }

                        }
                        if (player.getBuffedValue(MapleBuffStat.HAMSTRING) != null) {
                            //���˼�
                            final ISkill skill = SkillFactory.getSkill(3121007);
                            final MapleStatEffect eff = skill.getEffect(player.getSkillLevel(skill));

                            if (eff.makeChanceResult()) {
                                final MonsterStatusEffect monsterStatusEffect = new MonsterStatusEffect(MonsterStatus.SPEED, eff.getX(), 3121007, null, false);
                                monster.applyStatus(player, monsterStatusEffect, false, eff.getY() * 1000, false);
                            }
                        }
                        if (player.getJob() == 121) {
                            for (int charge : charges) {
                                final ISkill skill = SkillFactory.getSkill(charge);
                                if (player.isBuffFrom(MapleBuffStat.WK_CHARGE, skill)) {
                                    final MonsterStatusEffect monsterStatusEffect = new MonsterStatusEffect(MonsterStatus.FREEZE, 1, charge, null, false);
                                    monster.applyStatus(player, monsterStatusEffect, false, skill.getEffect(player.getSkillLevel(skill)).getY() * 2000, false);
                                    break;
                                }
                            }
                        }
                    }
                    if (effect != null && effect.getMonsterStati().size() > 0) {
                        if (effect.makeChanceResult()) {
                            for (Map.Entry<MonsterStatus, Integer> z : effect.getMonsterStati().entrySet()) {
                                monster.applyStatus(player, new MonsterStatusEffect(z.getKey(), z.getValue(), theSkill.getId(), null, false), effect.isPoison(), effect.getDuration(), false);
                            }
                        }
                    }
                }
            }
        }
        if (attack.skill != 0 && attack.targets > 0 && attack.skill != 21101003 && attack.skill != 5110001 && attack.skill != 15100004 && attack.skill != 11101002 && attack.skill != 13101002) {
            effect.applyTo(player, attack.position);
        }
    }
    //ħ������

    public static final void applyAttackMagic(final AttackInfo attack, final ISkill theSkill, final MapleCharacter player, final MapleStatEffect effect) {
        if (effect == null) {
            player.getClient().sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final PlayerStats stats = player.getStat();
        double maxDamagePerHit;
        if (attack.skill == 1000 || attack.skill == 10001000 || attack.skill == 20001000 || attack.skill == 20011000 || attack.skill == 30001000) {
            maxDamagePerHit = 40;
        } else if (GameConstants.isPyramidSkill(attack.skill)) {
            maxDamagePerHit = 1;
        } else {
            final double v75 = (effect.getMatk() * 0.058);
            maxDamagePerHit = stats.getTotalMagic() * (stats.getInt() * 0.5 + (v75 * v75) + effect.getMatk() * 3.3) / 100;
        }
        // �����κδ���
        maxDamagePerHit *= 1.04;
        final Element element = player.getBuffedValue(MapleBuffStat.ELEMENT_RESET) != null ? Element.NEUTRAL : theSkill.getElement();

        double MaxDamagePerHit = 0;
        int totDamageToOneMonster, totDamage = 0, fixeddmg;
        byte overallAttackCount;
        boolean Tempest;
        MapleMonsterStats monsterstats;
        int CriticalDamage = stats.passive_sharpeye_percent();
        final ISkill eaterSkill = SkillFactory.getSkill(GameConstants.getMPEaterForJob(player.getJob()));
        final int eaterLevel = player.getSkillLevel(eaterSkill);
        final MapleMap map = player.getMap();
        int ȫ���������� = gui.Start.ConfigValuesMap.get("ȫ����������");
        if (ȫ���������� == 0) {
            MaplePvp.doPvP(player, map, attack);
        } else if (MapleParty.�����˺� == 1) {
            if (player.getMapId() == 104000400 && player.getClient().getChannel() == 2) {
                MaplePvp.doPvP(player, map, attack);
            }
        }
        int last = effect.getAttackCount() > effect.getBulletCount() ? effect.getAttackCount() : effect.getBulletCount();
        if (gui.Start.ConfigValuesMap.get("���ټ�⿪��") != null) {
            if (gui.Start.ConfigValuesMap.get("���ټ�⿪��") == 0) {
                if (System.currentTimeMillis() - player.�������� < 400) {
                    player.���������ж�++;
                    if (player.���������ж� >= 10) {
                        int ch = World.Find.findChannel(��ɫIDȡ����(player.getId()));
                        MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(��ɫIDȡ����(player.getId()));
                        if (target.ban("�����ټ����", player.isAdmin(), false, false)) {
                            String ��Ϣ = "[ϵͳ����] : ��� " + target.getName() + " ��Ϊʹ�÷Ƿ���������ӳټ��ٹ������ƻ���Ϸƽ�⣬��ϵͳ���÷�š�";
                            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
                            sendMsgToQQGroup(��Ϣ);
                        }
                    }
                } else if (player.���������ж� > 0) {
                    player.���������ж� = 0;
                }
                player.�������� = System.currentTimeMillis();
            }
        }
        for (final AttackPair oned : attack.allDamage) {
            final MapleMonster monster = map.getMonsterByOid(oned.objectid);
            if (monster != null) {
                Tempest = monster.getStatusSourceID(MonsterStatus.FREEZE) == 21120006 && !monster.getStats().isBoss();
                totDamageToOneMonster = 0;
                monsterstats = monster.getStats();
                fixeddmg = monsterstats.getFixedDamage();
                MaxDamagePerHit = CalculateMaxMagicDamagePerHit(player, theSkill, monster, monsterstats, stats, element, CriticalDamage, maxDamagePerHit);
                overallAttackCount = 0;
                if (gui.Start.ConfigValuesMap.get("ȫ����⿪��") != null) {
                    if (gui.Start.ConfigValuesMap.get("ȫ����⿪��") == 0) {
                        ȫ�����(player, monster, attack);
                    }
                }
                if (gui.Start.ConfigValuesMap.get("������⿪��") != null) {
                    if (gui.Start.ConfigValuesMap.get("������⿪��") == 0) {
                        ���ܹ����������(player, effect, attack, last);
                    }
                }
                if (gui.Start.ConfigValuesMap.get("Ⱥ����⿪��") != null) {
                    if (gui.Start.ConfigValuesMap.get("Ⱥ����⿪��") == 0) {
                        Ⱥ���������(player, effect, attack);
                    }
                }
                long eachd;
                StringBuilder name = new StringBuilder();
                long �����˺� = 0;
                for (Pair<Integer, Boolean> eachde : oned.attack) {
                    eachd = eachde.left;
                    overallAttackCount++;
                    /**
                     * <ħ��ʦ>
                     */
                    if (player.job >= 200 && player.job < 300) {
                        /**
                         * <��>
                         */
                        if (player.job == 210 || player.job == 211 || player.job == 212) {
                            if (player.F().get(FM("ĩ������")) != null) {
                                if (attack.skill == 2111002) {
                                    eachd += eachd / 100 * player.F().get(FM("ĩ������"));
                                    player.ĩ�������˺���¼ = eachd;
                                    eachd += player.ĩ�������˺���¼;
                                }
                            }
                        }
                        /**
                         * <����>
                         */
                        if (player.job == 220 || player.job == 221 || player.job == 222) {
                            if (player.F().get(FM("����ǹ")) != null) {
                                if (attack.skill == 2111002) {
                                    double r1 = Math.ceil(Math.random() * 3000);
                                    if (player.F().get(FM("����ǹ")) > r1) {
                                        if (player.getEquippedFuMoMap().get(7) != null) {
                                            maxDamagePerHit += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                                            eachd += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                                        } else {
                                            maxDamagePerHit += 10000 * 1000;
                                            eachd += 10000 * 1000;
                                        }
                                        player.dropMessage(5, "����ͨ�������һ����ɱ");
                                    } else {
                                        eachd += eachd / 100 * player.F().get(FM("����ǹ"));
                                    }
                                }
                            }
                        }
                        /**
                         * <����>
                         */
                        if (player.job == 230 || player.job == 231 || player.job == 232) {
                            if (player.F().get(FM("ʥ��")) != null) {
                                if (attack.skill == 2311004) {
                                    eachd += eachd / 100 * player.F().get(FM("ʥ��"));
                                    if (player.ʥ���޵� < 2) {
                                        player.ʥ���޵�++;
                                    }
                                }
                            }
                        }
                    }
                    //���Ӷ���ͨ������˺�
                    if (player.F().get(FM("ǿ��")) != null && !monster.getStats().isBoss()) {
                        �����˺� += eachd / 100 * player.F().get(FM("ǿ��"));
                    }
                    if (player.F().get(FM("��ǿ��")) != null && monster.getStats().isBoss()) {
                        �����˺� += eachd / 100 * player.F().get(FM("��ǿ��"));
                    }
                    if (player.F().get(FM("ս����־")) != null) {
                        �����˺� += eachd / 100 * player.F().get(FM("ս����־"));
                    }
                    if (player.getEquippedFuMoMap().get(10) != null) {
                        �����˺� += player.getEquippedFuMoMap().get(10);
                    }
                    if (player.F().get(FM("������Ϭ")) != null) {
                        �����˺� += eachd / 100 * player.F().get(FM("������Ϭ"));
                    }
                    /**
                     * <����ģʽ-ħ��>
                     */
                    if (gui.Start.������Ϣ����.get("����ģʽ" + player.getId() + "") != null) {
                        if (gui.Start.������Ϣ����.get("����ģʽ" + player.getId() + "") == 0) {
                            if (monster.getStats().isBoss()) {
                                int ���� = (int) (gui.Start.������Ϣ����.get("BUFF����" + player.getId() + "") * 0.00001);
                                int �������� = gui.Start.������Ϣ����.get("��������" + player.getId() + "");
                                int ħ�������� = gui.Start.������Ϣ����.get("ħ��������" + player.getId() + "");
                                �����˺� += eachd * (0.3 + (�������� + ħ��������) * ����);
                            }
                            double ����ģʽ���� = Math.ceil(Math.random() * 100);
                            int ���� = gui.Start.������Ϣ����.get("BUFF����" + player.getId() + "");
                            int ����ģʽ������ = (int) (gui.Start.������Ϣ����.get("ħ��������" + player.getId() + "") * ���� * 0.001);
                            if (����ģʽ���� <= 30) {
                                eachd += ����ģʽ������;
                            } else {
                                int ����ģʽ�����˺��ӳ� = gui.Start.������Ϣ����.get("ħ������" + player.getId() + "");
                                eachd += ����ģʽ������ * 2 + ����ģʽ�����˺��ӳ�;
                            }
                            player.addHP((int) (gui.Start.������Ϣ����.get("����������" + player.getId() + "") * ���� * 0.002));
                            player.addMP((int) (gui.Start.������Ϣ����.get("ħ��������" + player.getId() + "") * ���� * 0.002));
                        }
                    }
                    if (�����˺� > 0) {
                        eachd += �����˺�;
                    }
                    if (gui.Start.ConfigValuesMap.get("����˺�") != null) {
                        if (eachd > gui.Start.ConfigValuesMap.get("����˺�") * 10000) {
                            int ch = World.Find.findChannel(��ɫIDȡ����(player.getId()));
                            MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(��ɫIDȡ����(player.getId()));
                            if (target.ban("�����˼����" + eachd, player.isAdmin(), false, false)) {
                                String ��Ϣ = "[ϵͳ����] : ��� " + target.getName() + " ��Ϊʹ�÷Ƿ�������ƻ���Ϸƽ�⣬��ϵͳ���÷�š�";
                                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
                                sendMsgToQQGroup(��Ϣ);
                            }
                        }
                    }
                    //�����¼��Ҵ��������˺�
                    if (eachd > player.����˺�) {
                        player.����˺� = eachd;
                    }
                    //����ͨ����һ����ɱ
                    if (player.getEquippedFuMoMap().get(4) != null) {
                        if (!monster.getStats().isBoss()) {
                            double r1 = Math.ceil(Math.random() * 500);
                            if (player.getEquippedFuMoMap().get(4) > r1) {
                                if (player.getEquippedFuMoMap().get(7) != null) {
                                    maxDamagePerHit += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                                    eachd += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                                } else {
                                    maxDamagePerHit += 10000 * 1000;
                                    eachd += 10000 * 1000;
                                }
                                player.dropMessage(5, "����ͨ�������һ����ɱ");
                            }
                        }
                    }
                    //�Ը߼�����һ����ɱ
                    if (player.getEquippedFuMoMap().get(5) != null) {
                        if (monster.getStats().isBoss()) {
                            double r2 = Math.ceil(Math.random() * 2000);
                            if (player.getEquippedFuMoMap().get(5) > r2) {
                                if (player.getEquippedFuMoMap().get(7) != null) {
                                    maxDamagePerHit += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                                    eachd += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                                } else {
                                    maxDamagePerHit += 10000 * 1000;
                                    eachd += 10000 * 1000;
                                }
                                player.dropMessage(5, "�Ը߼��������һ����ɱ");
                            }
                        }
                    }
                    //�����й���һ����ɱ
                    if (player.getEquippedFuMoMap().get(6) != null) {
                        double r3 = Math.ceil(Math.random() * 4000);
                        if (player.getEquippedFuMoMap().get(6) > r3) {
                            if (player.getEquippedFuMoMap().get(7) != null) {
                                maxDamagePerHit += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                                eachd += 10000 * 1000 + (100000 * player.getEquippedFuMoMap().get(7));
                            } else {
                                maxDamagePerHit += 10000 * 1000;
                                eachd += 10000 * 1000;
                            }
                            player.dropMessage(5, "�Թ������һ����ɱ");
                        }
                    }

                    if (fixeddmg != -1) {
                        eachd = monsterstats.getOnlyNoramlAttack() ? 0 : fixeddmg;
                        /*Rectangle bounds = new Rectangle((int) player.getPosition().getX(), (int) player.getPosition().getY(), 1, 1);
                            MapleMist mist = new MapleMist(bounds, player);
                            player.getMap().spawnMist(mist, 30000, true);*/
                        //}
                    } else if (monsterstats.getOnlyNoramlAttack()) {
                        eachd = 0; // Magic is always not a normal attack
                    }
                    totDamageToOneMonster += eachd;
                }
                totDamage += totDamageToOneMonster;
                //��������
                if (player.F().get(FM("�ɱ�")) != null) {
                    double a = Math.ceil(Math.random() * 200);
                    if (a >= player.F().get(FM("�ɱ�"))) {
                        player.checkMonsterAggro(monster);
                    }
                } else {
                    player.checkMonsterAggro(monster);
                }
                //Ⱥ������
                if (attack.skill == 2301002 && !monsterstats.getUndead()) {
                    player.getCheatTracker().registerOffense(CheatingOffense.�����������ǲ���ϵ����);
                    return;
                }
                if (player.F().get(FM("�������")) != null) {
                    double a = Math.ceil(Math.random() * 1000);
                    if (a <= player.F().get(FM("�������"))) {
                        monster.damage(player, (monster.getStats().isBoss() ? 199999 : (monster.getHp() - 1)), true, attack.skill);
                    }
                }
                if (totDamageToOneMonster > 0) {
                    monster.damage(player, totDamageToOneMonster, true, attack.skill);
                    if (monster.isBuffed(MonsterStatus.MAGIC_DAMAGE_REFLECT)) { //test
                        player.addHP(-(7000 + Randomizer.nextInt(8000))); //this is what it seems to be?
                    }
                    switch (attack.skill) {
                        //������
                        case 2221003:
                            monster.setTempEffectiveness(Element.FIRE, theSkill.getEffect(player.getSkillLevel(theSkill)).getDuration());
                            break;
                        //�����
                        case 2121003:
                            monster.setTempEffectiveness(Element.ICE, theSkill.getEffect(player.getSkillLevel(theSkill)).getDuration());
                            break;
                    }
                    if (effect.getMonsterStati().size() > 0) {
                        if (effect.makeChanceResult()) {
                            for (Map.Entry<MonsterStatus, Integer> z : effect.getMonsterStati().entrySet()) {
                                monster.applyStatus(player, new MonsterStatusEffect(z.getKey(), z.getValue(), theSkill.getId(), null, false), effect.isPoison(), effect.getDuration(), false);
                            }
                        }
                    }
                    if (eaterLevel > 0) {
                        eaterSkill.getEffect(eaterLevel).applyPassive(player, monster);
                    }
                }
            }
        }
        //Ⱥ������
        if (attack.skill
                != 2301002) {
            effect.applyTo(player);
        }
        //һ�ּ��
        /*if (totDamage > 1) {
            final CheatTracker tracker = player.getCheatTracker();
            tracker.setAttacksWithoutHit(true);
            if (tracker.getAttacksWithoutHit() > 1000) {
                tracker.registerOffense(CheatingOffense.�����޵�, Integer.toString(tracker.getAttacksWithoutHit()));
            }
        }*/
    }

    private static final double CalculateMaxMagicDamagePerHit(final MapleCharacter chr, final ISkill skill, final MapleMonster monster, final MapleMonsterStats mobstats, final PlayerStats stats, final Element elem, final Integer sharpEye, final double maxDamagePerMonster) {
        final int dLevel = Math.max(mobstats.getLevel() - chr.getLevel(), 0);
        final int Accuracy = (int) (Math.floor((stats.getTotalInt() / 10.0)) + Math.floor((stats.getTotalLuk() / 10.0)));
        final int MinAccuracy = mobstats.getEva() * (dLevel * 2 + 51) / 120;

        if (MinAccuracy > Accuracy && skill.getId() != 1000 && skill.getId() != 10001000 && skill.getId() != 20001000 && skill.getId() != 20011000 && skill.getId() != 30001000 && !GameConstants.isPyramidSkill(skill.getId())) { // miss :P or HACK :O
            return 0;
        }
        double elemMaxDamagePerMob;

        switch (monster.getEffectiveness(elem)) {
            case IMMUNE://����
                elemMaxDamagePerMob = 1;
                break;
            case NORMAL://����
                elemMaxDamagePerMob = ElementalStaffAttackBonus(elem, maxDamagePerMonster, stats);
                break;
            case WEAK://����
                elemMaxDamagePerMob = ElementalStaffAttackBonus(elem, maxDamagePerMonster * 1.5, stats);
                break;
            case STRONG://ǿ��
                elemMaxDamagePerMob = ElementalStaffAttackBonus(elem, maxDamagePerMonster * 0.5, stats);
                break;
            default:
                throw new RuntimeException("δ֪��״̬");
        }

        elemMaxDamagePerMob -= mobstats.getMagicDefense() * 0.5;

        elemMaxDamagePerMob += ((double) elemMaxDamagePerMob / 100) * sharpEye;

        elemMaxDamagePerMob += (elemMaxDamagePerMob * (mobstats.isBoss() ? stats.bossdam_r : stats.dam_r)) / 100;
        switch (skill.getId()) {
            case 1000:
            //��ţͶ����
            case 10001000:
            //��ţͶ����
            case 20001000:
                elemMaxDamagePerMob = 40;
                break;
            case 1020:
            case 10001020:
            case 20001020:
            case 20011020:
            case 30001020:
                elemMaxDamagePerMob = 1;
                break;
        }
        //Ⱥ������
        if (skill.getId() == 2301002) {
            elemMaxDamagePerMob *= 2;
        }
        if (elemMaxDamagePerMob > 199999) {
            elemMaxDamagePerMob = 199999;
        } else if (elemMaxDamagePerMob < 0) {
            elemMaxDamagePerMob = 1;
        }
        return elemMaxDamagePerMob;
    }

    private static final double ElementalStaffAttackBonus(final Element elem, double elemMaxDamagePerMob, final PlayerStats stats) {
        switch (elem) {
            case FIRE:
                return (elemMaxDamagePerMob / 100) * stats.element_fire;
            case ICE:
                return (elemMaxDamagePerMob / 100) * stats.element_ice;
            case LIGHTING:
                return (elemMaxDamagePerMob / 100) * stats.element_light;
            case POISON:
                return (elemMaxDamagePerMob / 100) * stats.element_psn;
            default:
                return (elemMaxDamagePerMob / 100) * stats.def;
        }
    }

    //��ħ͵Ǯ
    private static void ��ħ͵Ǯ(final MapleCharacter player, final MapleMonster mob, AttackPair oned) {
        int maxmeso = 50000;
        final ISkill skill = SkillFactory.getSkill(4211003);
        final MapleStatEffect s = skill.getEffect(player.getSkillLevel(skill));
        for (Pair eachde : oned.attack) {
            Integer eachd = (Integer) eachde.left;
            if (s.makeChanceResult()) {
                player.getMap().spawnMesoDrop(Math.min((int) Math.max(eachd / 20000.0D * maxmeso, 1.0D), maxmeso), new Point((int) (mob.getTruePosition().getX() + Randomizer.nextInt(100) - 50.0D), (int) mob.getTruePosition().getY()), mob, player, false, (byte) 0);
            }
        }
    }

    //����������
    private static void ������(final MapleCharacter player, final MapleMonster mob, AttackPair oned) {
        int maxmeso = player.getBuffedValue(MapleBuffStat.PICKPOCKET);
        final ISkill skill = SkillFactory.getSkill(4211003);
        final MapleStatEffect s = skill.getEffect(player.getSkillLevel(skill));
        for (Pair eachde : oned.attack) {
            Integer eachd = (Integer) eachde.left;
            if (s.makeChanceResult()) {
                player.getMap().spawnMesoDrop(Math.min((int) Math.max(eachd / 20000.0D * maxmeso, 1.0D), maxmeso), new Point((int) (mob.getTruePosition().getX() + Randomizer.nextInt(100) - 50.0D), (int) mob.getTruePosition().getY()), mob, player, false, (byte) 0);
            }
        }
    }
    //�Ӳ���

    private static void �Ӳ���(final MapleCharacter player, final MapleMonster mob, AttackPair oned) {
        int maxmeso = player.getBuffedValue(MapleBuffStat.PICKPOCKET);
        final ISkill skill = SkillFactory.getSkill(4211003);
        final MapleStatEffect s = skill.getEffect(player.getSkillLevel(skill));
        for (Pair eachde : oned.attack) {
            Integer eachd = (Integer) eachde.left;
            if (s.makeChanceResult()) {
                player.getMap().spawnMesoDrop(Math.min((int) Math.max(eachd / 20000.0D * maxmeso, 1.0D), maxmeso), new Point((int) (mob.getTruePosition().getX() + Randomizer.nextInt(100) - 50.0D), (int) mob.getTruePosition().getY()), mob, player, false, (byte) 0);
            }
        }
    }

    private static double CalculateMaxWeaponDamagePerHit(final MapleCharacter player, final MapleMonster monster, final AttackInfo attack, final ISkill theSkill, final MapleStatEffect attackEffect, double maximumDamageToMonster, final Integer CriticalDamagePercent) {
        if (player.getMapId() / 1000000 == 914) { //aran
            return 199999;
        }
        List<Element> elements = new ArrayList<Element>();
        boolean defined = false;
        if (theSkill != null) {
            elements.add(theSkill.getElement());

            switch (theSkill.getId()) {
                case 3001004:
                case 33101001:
                    defined = true; //can go past 199999
                    break;
                case 1000:
                case 10001000:
                case 20001000:
                case 20011000:
                case 30001000:
                    maximumDamageToMonster = 40;
                    defined = true;
                    break;
                case 1020:
                case 10001020:
                case 20001020:
                case 20011020:
                case 30001020:
                    maximumDamageToMonster = 1;
                    defined = true;
                    break;
                case 4331003: //Owl Spirit
                    maximumDamageToMonster = (monster.getStats().isBoss() ? 199999 : monster.getHp());
                    defined = true;
                    break;
                case 3221007: // Sniping
                    maximumDamageToMonster = (monster.getStats().isBoss() ? 199999 : monster.getMobMaxHp());
                    defined = true;
                    break;
                case 1221011://Heavens Hammer
                    maximumDamageToMonster = (monster.getStats().isBoss() ? 199999 : monster.getHp() - 1);
                    defined = true;
                    break;
                case 4211006: // Meso Explosion
                    maximumDamageToMonster = 750000;
                    defined = true;
                    break;
                case 1009: // Bamboo Trust
                case 1001: // Bamboo Trust
                case 10001009:
                case 20001009:
                case 20011009:
                case 30001009:
                    defined = true;
                    maximumDamageToMonster = (monster.getStats().isBoss() ? monster.getMobMaxHp() / 30 * 100 : monster.getMobMaxHp());
                    break;
                case 3211006: //Sniper Strafe
                    if (monster.getStatusSourceID(MonsterStatus.FREEZE) == 3211003) { //blizzard in effect
                        defined = true;
                        maximumDamageToMonster = monster.getHp();
                    }
                    break;
            }
        }
        if (player.getBuffedValue(MapleBuffStat.WK_CHARGE) != null) {//�����˺�����
            int chargeSkillId = player.getBuffSource(MapleBuffStat.WK_CHARGE);

            switch (chargeSkillId) {
                case 1211003:
                case 1211004:
                    elements.add(Element.FIRE);//������
                    break;
                case 1211005:
                case 1211006:
                case 21111005:
                    elements.add(Element.ICE);//������
                    break;
                case 1211007:
                case 1211008:
                case 15101006:
                    elements.add(Element.LIGHTING);//������
                    break;
                case 1221003:
                case 1221004:
                case 11111007:
                    elements.add(Element.HOLY);//��ʥ����
                    break;
                case 12101005:
                    elements.clear(); //��Ȼ������
                    break;

            }
        }
        /*if (player.getEquippedFuMoMap().get(1) > 0) {
            elements.add(Element.FIRE);//������
            player.dropMessage(5,"[��ħ]������");
        }*/
        if (player.getBuffedValue(MapleBuffStat.LIGHTNING_CHARGE) != null) {
            elements.add(Element.LIGHTING);
        }
        double elementalMaxDamagePerMonster = maximumDamageToMonster;
        if (elements.size() > 0) {
            double elementalEffect;

            switch (attack.skill) {
                case 3211003:
                case 3111003: // inferno and blizzard
                    elementalEffect = attackEffect.getX() / 200.0;
                    break;
                default:
                    elementalEffect = 0.5;
                    break;
            }
            for (Element element : elements) {
                switch (monster.getEffectiveness(element)) {
                    case IMMUNE:
                        elementalMaxDamagePerMonster = 1;
                        break;
                    case WEAK:
                        elementalMaxDamagePerMonster *= (1.0 + elementalEffect);
                        break;
                    case STRONG:
                        elementalMaxDamagePerMonster *= (1.0 - elementalEffect);
                        break;
                    default:
                        break; //normal nothing
                }
            }
        }
        // Calculate mob def
        final short moblevel = monster.getStats().getLevel();
        final short d = moblevel > player.getLevel() ? (short) (moblevel - player.getLevel()) : 0;
        elementalMaxDamagePerMonster = elementalMaxDamagePerMonster * (1 - 0.01 * d) - monster.getStats().getPhysicalDefense() * 0.5;

        // Calculate passive bonuses + Sharp Eye
        elementalMaxDamagePerMonster += ((double) elementalMaxDamagePerMonster / 100.0) * CriticalDamagePercent;

//	if (theSkill.isChargeSkill()) {
//	    elementalMaxDamagePerMonster = (double) (90 * (System.currentTimeMillis() - player.getKeyDownSkill_Time()) / 2000 + 10) * elementalMaxDamagePerMonster * 0.01;
//	}
        if (theSkill != null && theSkill.isChargeSkill() && player.getKeyDownSkill_Time() == 0) {
            return 0;
        }
        final MapleStatEffect homing = player.getStatForBuff(MapleBuffStat.HOMING_BEACON);
        if (homing != null && player.getLinkMid() == monster.getObjectId() && homing.getSourceId() == 5220011) { //bullseye
            elementalMaxDamagePerMonster += (elementalMaxDamagePerMonster * homing.getX());
        }
        final PlayerStats stat = player.getStat();
        elementalMaxDamagePerMonster += (elementalMaxDamagePerMonster * (monster.getStats().isBoss() ? stat.bossdam_r : stat.dam_r)) / 100.0;
        if (player.getDebugMessage()) {
            player.dropMessage("[�˺�����] �����˺�:" + (int) elementalMaxDamagePerMonster);
        }
        if (elementalMaxDamagePerMonster > 199999) {
            if (!defined) {
                elementalMaxDamagePerMonster = 199999;
            }
        } else if (elementalMaxDamagePerMonster < 0) {
            elementalMaxDamagePerMonster = 1;
        }
        return elementalMaxDamagePerMonster;
    }

    public static final AttackInfo DivideAttack(final AttackInfo attack, final int rate) {
        attack.real = false;
        if (rate <= 1) {
            return attack; //lol
        }
        for (AttackPair p : attack.allDamage) {
            if (p.attack != null) {
                for (Pair<Integer, Boolean> eachd : p.attack) {
                    eachd.left /= rate; //too ex.
                }
            }
        }
        return attack;
    }

    public static final AttackInfo Modify_AttackCrit(final AttackInfo attack, final MapleCharacter chr, final int type) {
        final int CriticalRate = chr.getStat().passive_sharpeye_rate();
        final boolean shadow = (type == 2 && chr.getBuffedValue(MapleBuffStat.SHADOWPARTNER) != null) || (type == 1 && chr.getBuffedValue(MapleBuffStat.MIRROR_IMAGE) != null);
        if (attack.skill != 4211006 && attack.skill != 3211003 && attack.skill != 4111004 && (CriticalRate > 0 || attack.skill == 4221001 || attack.skill == 3221007)) { //blizz + shadow meso + m.e no crits
            for (AttackPair p : attack.allDamage) {
                if (p.attack != null) {
                    int hit = 0;
                    final int mid_att = p.attack.size() / 2;
                    final List<Pair<Integer, Boolean>> eachd_copy = new ArrayList<Pair<Integer, Boolean>>(p.attack);
                    for (Pair<Integer, Boolean> eachd : p.attack) {
                        hit++;
                        if (!eachd.right) {
                            if (attack.skill == 4221001) { //��ɱ������3�������������
                                eachd.right = (hit == 4 && Randomizer.nextInt(100) < 90);
                            } else if (attack.skill == 3221007 || eachd.left > 199999) { //һ��Ҫ��
                                eachd.right = true;
                            } else if (shadow && hit > mid_att) { //shadowpartner copies second half to first half
                                eachd.right = eachd_copy.get(hit - 1 - mid_att).right;
                            } else {
                                //rough calculation
                                eachd.right = (Randomizer.nextInt(100)/*
                                         * chr.CRand().CRand32__Random_ForMonster()
                                         * % 100
                                         */) < CriticalRate;
                            }
                            eachd_copy.get(hit - 1).right = eachd.right;
                            //System.out.println("CRITICAL RATE: " + CriticalRate + ", passive rate: " + chr.getStat().passive_sharpeye_rate() + ", critical: " + eachd.right);
                        }
                    }
                }
            }
        }
        return attack;
    }

    public static final AttackInfo parseDmgMa(final LittleEndianAccessor lea, final MapleCharacter chr) {
        //System.out.println(lea.toString());
        final AttackInfo ret = new AttackInfo();

        lea.skip(1);
        lea.skip(8);
        ret.tbyte = lea.readByte();
        //System.out.println("TBYTE: " + tbyte);
        ret.targets = (byte) ((ret.tbyte >>> 4) & 0xF);
        ret.hits = (byte) (ret.tbyte & 0xF);
        lea.skip(8); //?
        ret.skill = lea.readInt();
        lea.skip(12); // ORDER [4] bytes on v.79, [4] bytes on v.80, [1] byte on v.82
        switch (ret.skill) {
            case 2121001: // Big Bang
            case 2221001:
            case 2321001:
            case 22121000: //breath
            case 22151001:
                ret.charge = lea.readInt();
                break;
            default:
                ret.charge = -1;
                break;
        }
        lea.skip(1);
        ret.unk = 0;
        ret.display = lea.readByte(); // Always zero?
        ret.animation = lea.readByte();
        lea.skip(1); // Weapon subclass
        ret.speed = lea.readByte(); // Confirmed
        ret.lastAttackTickCount = lea.readInt(); // Ticks
//        lea.skip(4); //0

        int oid, damage;
        List<Pair<Integer, Boolean>> allDamageNumbers;
        ret.allDamage = new ArrayList<AttackPair>();

        for (int i = 0; i < ret.targets; i++) {
            oid = lea.readInt();
            lea.skip(14); // [1] Always 6?, [3] unk, [4] Pos1, [4] Pos2, [2] seems to change randomly for some attack

            allDamageNumbers = new ArrayList<Pair<Integer, Boolean>>();

            MapleMonster monster = chr.getMap().getMonsterByOid(oid);
            for (int j = 0; j < ret.hits; j++) {
                damage = lea.readInt();
//                if (ret.skill > 0) {
//                    damage = Damage_SkillPD(chr, damage, ret);
//                } else {
//                    damage = Damage_NoSkillPD(chr, damage);
//                }
                allDamageNumbers.add(new Pair<Integer, Boolean>(Integer.valueOf(damage), false));
            }
            lea.skip(4); // CRC of monster [Wz Editing]
            ret.allDamage.add(new AttackPair(Integer.valueOf(oid), allDamageNumbers));
        }
        ret.position = lea.readPos();

        return ret;
    }

    public static final AttackInfo parseDmgM(final LittleEndianAccessor lea, final MapleCharacter chr) {
        //System.out.println(lea.toString());
        final AttackInfo ret = new AttackInfo();

        lea.skip(1);
        lea.skip(8);
        ret.tbyte = lea.readByte();
        //System.out.println("TBYTE: " + tbyte);
        ret.targets = (byte) ((ret.tbyte >>> 4) & 0xF);
        ret.hits = (byte) (ret.tbyte & 0xF);
        lea.skip(8);
        ret.skill = lea.readInt();
        lea.skip(12); // ORDER [4] bytes on v.79, [4] bytes on v.80, [1] byte on v.82
        switch (ret.skill) {
            case 5101004: // Corkscrew
            case 15101003: // Cygnus corkscrew
            case 5201002: // Gernard
            case 14111006: // Poison bomb
            case 4341002:
            case 4341003:
                ret.charge = lea.readInt();
                break;
            default:
                ret.charge = 0;
                break;
        }
        lea.skip(1);
        ret.unk = 0;
        ret.display = lea.readByte(); // Always zero?
        ret.animation = lea.readByte();
        lea.skip(1); // Weapon class
        ret.speed = lea.readByte(); // Confirmed
        ret.lastAttackTickCount = lea.readInt(); // Ticks
//        lea.skip(4); //0

        ret.allDamage = new ArrayList<AttackPair>();

        if (ret.skill == 4211006) { // Meso Explosion
            return parseMesoExplosion(lea, ret, chr);
        }
        int oid, damage;
        List<Pair<Integer, Boolean>> allDamageNumbers;

        for (int i = 0; i < ret.targets; i++) {
            oid = lea.readInt();
//	    System.out.println(tools.HexTool.toString(lea.read(14)));
            lea.skip(14); // [1] Always 6?, [3] unk, [4] Pos1, [4] Pos2, [2] seems to change randomly for some attack

            allDamageNumbers = new ArrayList<Pair<Integer, Boolean>>();

            MapleMonster monster = chr.getMap().getMonsterByOid(oid);
            for (int j = 0; j < ret.hits; j++) {
                damage = lea.readInt();
//                if (ret.skill > 0) {
//                    damage = Damage_SkillPD(chr, damage, ret);
//                } else {
//                    damage = Damage_NoSkillPD(chr, damage);
//                }
                // System.out.println("Damage: " + damage);
                allDamageNumbers.add(new Pair<Integer, Boolean>(Integer.valueOf(damage), false));
            }
            lea.skip(4); // CRC of monster [Wz Editing]
            ret.allDamage.add(new AttackPair(Integer.valueOf(oid), allDamageNumbers));
        }
        ret.position = lea.readPos();
        return ret;
    }

    public static final AttackInfo parseDmgR(final LittleEndianAccessor lea, final MapleCharacter chr) {
        //System.out.println(lea.toString()); //<-- packet needs revision
        final AttackInfo ret = new AttackInfo();

        lea.skip(1);
        lea.skip(8);
        ret.tbyte = lea.readByte();
        //System.out.println("TBYTE: " + tbyte);
        ret.targets = (byte) ((ret.tbyte >>> 4) & 0xF);
        ret.hits = (byte) (ret.tbyte & 0xF);
        lea.skip(8);
        ret.skill = lea.readInt();

        lea.skip(12); // ORDER [4] bytes on v.79, [4] bytes on v.80, [1] byte on v.82

        switch (ret.skill) {
            case 3121004: // Hurricane
            case 3221001: // Pierce
            case 5221004: // Rapidfire
            case 13111002: // Cygnus Hurricane
                lea.skip(4); // extra 4 bytes
                break;
        }
        ret.charge = -1;
        lea.skip(1);
        ret.unk = 0;
        ret.display = lea.readByte(); // Always zero?
        ret.animation = lea.readByte();
        lea.skip(1); // Weapon class
        ret.speed = lea.readByte(); // Confirmed
        ret.lastAttackTickCount = lea.readInt(); // Ticks
//        lea.skip(4); //0
        ret.slot = (byte) lea.readShort();
        ret.csstar = (byte) lea.readShort();
        ret.AOE = lea.readByte(); // is AOE or not, TT/ Avenger = 41, Showdown = 0

        int damage, oid;
        List<Pair<Integer, Boolean>> allDamageNumbers;
        ret.allDamage = new ArrayList<AttackPair>();

        for (int i = 0; i < ret.targets; i++) {
            oid = lea.readInt();
//	    System.out.println(tools.HexTool.toString(lea.read(14)));
            lea.skip(14); // [1] Always 6?, [3] unk, [4] Pos1, [4] Pos2, [2] seems to change randomly for some attack

            MapleMonster monster = chr.getMap().getMonsterByOid(oid);
            allDamageNumbers = new ArrayList<Pair<Integer, Boolean>>();
            for (int j = 0; j < ret.hits; j++) {
                damage = lea.readInt();
//                if (ret.skill > 0) {
//                    damage = Damage_SkillPD(chr, damage, ret);
//                } else {
//                    damage = Damage_NoSkillPD(chr, damage);
//                }
                allDamageNumbers.add(new Pair<Integer, Boolean>(Integer.valueOf(damage), false));
                //System.out.println("Hit " + j + " from " + i + " to mobid " + oid + ", damage " + damage);
            }
            lea.skip(4); // CRC of monster [Wz Editing]
//	    System.out.println(tools.HexTool.toString(lea.read(4)));

            ret.allDamage.add(new AttackPair(Integer.valueOf(oid), allDamageNumbers));
        }
        lea.skip(4);
        ret.position = lea.readPos();

        return ret;
    }

    public static final AttackInfo parseMesoExplosion(final LittleEndianAccessor lea, final AttackInfo ret, final MapleCharacter chr) {
        //System.out.println(lea.toString(true));
        byte bullets;
        if (ret.hits == 0) {
            lea.skip(4);
            bullets = lea.readByte();
            for (int j = 0; j < bullets; j++) {
                ret.allDamage.add(new AttackPair(Integer.valueOf(lea.readInt()), null));
                lea.skip(1);
            }
            lea.skip(2); // 8F 02
            return ret;
        }

        int oid;
        List<Pair<Integer, Boolean>> allDamageNumbers;

        for (int i = 0; i < ret.targets; i++) {
            oid = lea.readInt();
            lea.skip(12);
            bullets = lea.readByte();
            allDamageNumbers = new ArrayList<Pair<Integer, Boolean>>();
            for (int j = 0; j < bullets; j++) {
                int damage = lea.readInt();
                //    damage = Damage_SkillPD(chr, damage, ret);
                allDamageNumbers.add(new Pair<Integer, Boolean>(Integer.valueOf(damage), false)); //m.e. never crits
            }
            ret.allDamage.add(new AttackPair(Integer.valueOf(oid), allDamageNumbers));
            lea.skip(4); // C3 8F 41 94, 51 04 5B 01
        }
        lea.skip(4);
        bullets = lea.readByte();

        for (int j = 0; j < bullets; j++) {
            ret.allDamage.add(new AttackPair(Integer.valueOf(lea.readInt()), null));
            lea.skip(1);
        }
        lea.skip(2); // 8F 02/ 63 02

        return ret;
    }

    public static void ȫ�����(MapleCharacter player, MapleMonster monster, AttackInfo ret) {
        double ��������X = monster.getPosition().getX();
        double ��������Y = monster.getPosition().getY();
        double X������� = player.getPosition().getX() - ��������X;
        double Y������� = player.getPosition().getY() - ��������Y;
        if (Y������� > 3000 && X������� > 3000) {
            int ch = World.Find.findChannel(��ɫIDȡ����(player.getId()));
            MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(��ɫIDȡ����(player.getId()));
            if (target.ban("��ȫ�������", player.isAdmin(), false, false)) {
                String ��Ϣ = "[ϵͳ����] : ��� " + target.getName() + " ��Ϊʹ�÷Ƿ����������/ȫ���������ƻ���Ϸƽ�⣬��ϵͳ���÷�š�";
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
                sendMsgToQQGroup(��Ϣ);
            }
        } else if (Y������� > 1000 || X������� > 1000) {
            player.������++;
            if (player.������ == 10) {
                int ch = World.Find.findChannel(��ɫIDȡ����(player.getId()));
                MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(��ɫIDȡ����(player.getId()));
                if (target.ban("��ȫ�������", player.isAdmin(), false, false)) {
                    String ��Ϣ = "[ϵͳ����] : ��� " + target.getName() + " ��Ϊʹ�÷Ƿ����������/ȫ���������ƻ���Ϸƽ�⣬��ϵͳ���÷�š�";
                    World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
                    sendMsgToQQGroup(��Ϣ);
                }
            }
        } else {
            player.������ = 0;
        }
    }

    public static final void ���ܹ����������(MapleCharacter player, MapleStatEffect effect, AttackInfo attack, int attackCount) {
        int last = attackCount;
        /**
         * <Ӱ����>
         */
        if (player.isActiveBuffedValue(4111002)) {
            last *= 2;
        }
        if (attack.hits > last) {
            int ch = World.Find.findChannel(��ɫIDȡ����(player.getId()));
            MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(��ɫIDȡ����(player.getId()));
            if (target.ban("�����������", player.isAdmin(), false, false)) {
                String ��Ϣ = "[ϵͳ����] : ��� " + target.getName() + " ��Ϊʹ�÷Ƿ�������޸Ŀͻ��˵��¹��������쳣���ƻ���Ϸƽ�⣬��ϵͳ���÷�š�";
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
                sendMsgToQQGroup(��Ϣ);
            }
        }
    }

    public static final void Ⱥ���������(MapleCharacter player, MapleStatEffect effect, AttackInfo attack) {
        if (attack.targets > 1) {
            if (player.getJob() >= 2110 && player.getJob() <= 2112) {
                if (attack.targets > 12) {
                    int ch = World.Find.findChannel(��ɫIDȡ����(player.getId()));
                    MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(��ɫIDȡ����(player.getId()));
                    if (target.ban("���������������", player.isAdmin(), false, false)) {
                        String ��Ϣ = "[ϵͳ����] : ��� " + target.getName() + " ��Ϊʹ�÷Ƿ�������޸Ŀͻ��˵���Ⱥ���쳣���ƻ���Ϸƽ�⣬��ϵͳ���÷�š�";
                        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
                        sendMsgToQQGroup(��Ϣ);
                    }
                }

            } else if (attack.targets > effect.getMobCount()) {
                int ch = World.Find.findChannel(��ɫIDȡ����(player.getId()));
                MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(��ɫIDȡ����(player.getId()));
                if (target.ban("���������������", player.isAdmin(), false, false)) {
                    String ��Ϣ = "[ϵͳ����] : ��� " + target.getName() + " ��Ϊʹ�÷Ƿ�������޸Ŀͻ��˵���Ⱥ���쳣���ƻ���Ϸƽ�⣬��ϵͳ���÷�š�";
                    World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
                    sendMsgToQQGroup(��Ϣ);
                }
            }
        }
    }

    public static final String Damage_AttackCount(MapleCharacter player, MapleStatEffect effect, AttackInfo attack, int attackCount) {

        String reason = "null";
        int last = attackCount;
        boolean mirror_fix = false;
        if (player.getJob() >= 411 && player.getJob() <= 412) {
            mirror_fix = true;
        }
        if (mirror_fix) {
            last *= 2;
        }
        if (attack.hits > last) {
            reason = "����˺����� : " + last + " ����˺�����: " + attack.skill;
        }
        return reason;
    }

    public static final String Damage_HighDamage(MapleCharacter player, MapleStatEffect effect, AttackInfo attack) {
        boolean BeginnerJob = player.getJob() == 0 || player.getJob() == 1000;
        String reason = "null";

        for (final AttackPair oned : attack.allDamage) {
            if (player.getMap().getMonsterByOid(oned.objectid) != null) {
                for (Pair<Integer, Boolean> eachde : oned.attack) {
                    if (eachde.left >= 2000000000) {//200000
                        reason = "���� " + attack.skill + " ����˺� " + eachde.left;
                    }
                    if (GameConstants.Novice_Skill(attack.skill) && eachde.left > 40) {
                        reason = "���� " + attack.skill + " ����˺� " + eachde.left;
                    }
                    if (BeginnerJob) {
                        if (eachde.left > 40) {
                            reason = "���� " + attack.skill + " ����˺� " + eachde.left;
                        }
                    } else if (eachde.left >= 20000000) {
                        reason = "���� " + attack.skill + " ����˺� " + eachde.left;
                    }
                }
            }
        }
        if (GameConstants.isElseSkill(attack.skill)) {
            reason = "null";
        }
        return reason;
    }

    public static final String Damage_MobCount(MapleCharacter player, MapleStatEffect effect, AttackInfo attack) {
        String reason = "null";
        if (attack.targets > effect.getMobCount()) {

            //reason = "����������࣬ �������: " + attack.targets + " ��ȷ����:" + effect.getMobCount();
        }
        return reason;
    }

}
