package pvp;

import client.MapleBuffStat;
import client.MapleCharacter;
import client.MapleStat;
import handling.channel.handler.AttackInfo;
import handling.world.World;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static gui.QQMsgServer.sendMsgToQQGroup;
import static pvp.Pvpskill.SK;
import static pvp.Pvpskill.Ⱥ�幥��;
import static pvp.Pvpskill.��ս����;
import static pvp.Pvpskill.Զ�̹���;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import server.maps.MapleMap;
import tools.MaplePacketCreator;
import tools.Pair;

public class MaplePvp {

    /**
     * ��������
     */
    private static int ����������;
    private static int maxDis;
    private static int maxHeight;
    private static boolean isAoe;
    public static boolean isLeft;
    public static boolean isRight;

    /**
     * DamageBalancer �����ж�����
     *
     * @param player �Լ�
     * @param attackedPlayers Ŀ��
     */
    private static void DamageBalancer(AttackInfo attack, MapleCharacter player) {
        /**
         * ��ͨ�߹��� *******************
         */
        if (attack.skill == 0) {
            ���������� = SK(50, 100);
            maxDis = 300;
            maxHeight = 35;
            /**
             * ��ս���� *********************
             */
        } else if (��ս����(attack)) {
            maxDis = 130;
            maxHeight = 40;
            isAoe = false;
            int skillleve = player.getSkillLevel(attack.skill);
            if (gui.Start.PVP�����˺�.get("" + attack.skill + "") != null) {
                ���������� = skillleve * gui.Start.PVP�����˺�.get("" + attack.skill + "");
            } else {
                ���������� = (int) (Math.floor(Math.random() * (400 - 200) + 100));
            }
        } else if (Զ�̹���(attack)) {
            maxDis = 300;
            maxHeight = 35;
            isAoe = false;
            int skillleve = player.getSkillLevel(attack.skill);
            if (gui.Start.PVP�����˺�.get("" + attack.skill + "") != null) {
                ���������� = skillleve * gui.Start.PVP�����˺�.get("" + attack.skill + "");
            } else {
                ���������� = (int) (Math.floor(Math.random() * (300 - 200) + 150));
            }
        } else if (Ⱥ�幥��(attack)) {
            maxDis = 350;
            maxHeight = 350;
            isAoe = true;
            int skillleve = player.getSkillLevel(attack.skill);
            if (gui.Start.PVP�����˺�.get("" + attack.skill + "") != null) {
                ���������� = skillleve * gui.Start.PVP�����˺�.get("" + attack.skill + "");
            } else {
                ���������� = (int) (Math.floor(Math.random() * (400 - 250) + 250));
            }
        } else {
            maxDis = 350;
            maxHeight = 350;
        }
    }

    /**
     * BUFF �������жϵ�BUFF
     *
     * @param player �Լ�
     * @param attackedPlayers Ŀ��
     */
    private static void BUFF(MapleCharacter player, MapleCharacter attackedPlayers) {
        /*
        *��ʼ��BUFF����
         */
        Integer mguard = attackedPlayers.getBuffedValue(MapleBuffStat.MAGIC_GUARD);
        Integer mesoguard = attackedPlayers.getBuffedValue(MapleBuffStat.MESOGUARD);
        if (mguard != null) {
            List<Pair<MapleStat, Integer>> stats = new ArrayList<Pair<MapleStat, Integer>>(1);
            int mploss = (int) (���������� / .5);
            ���������� *= .70;
            if (mploss > attackedPlayers.getMp()) {
                ���������� /= .70;
                attackedPlayers.cancelBuffStats(MapleBuffStat.MAGIC_GUARD);
            } else {
                attackedPlayers.setMp(attackedPlayers.getMp() - mploss);
                stats.add(new Pair<MapleStat, Integer>(MapleStat.MP, attackedPlayers.getMp()));
                attackedPlayers.getClient().sendPacket(MaplePacketCreator.updatePlayerStats(stats, player.getJob()));
            }
        } else if (mesoguard != null) {
            int mesoloss = (int) (���������� * .75);
            ���������� *= .75;
            if (mesoloss > attackedPlayers.getMeso()) {
                ���������� /= .75;
                attackedPlayers.cancelBuffStats(MapleBuffStat.MESOGUARD);
            } else {
                attackedPlayers.gainMeso(-mesoloss, false);
            }
        }

    }

    /**
     * killMonster jisha
     *
     * @param player �Լ�
     * @param attackedPlayers Ŀ��
     * @param map ��ͼ
     */
    private static void killMonster(MapleCharacter player, MapleCharacter attackedPlayers, MapleMap map) {
        //KDA����
        int KDA1 = player.GetPvpkills() - player.GetPvpdeaths() / 2;
        int KDA2 = attackedPlayers.GetPvpkills() - attackedPlayers.GetPvpdeaths() / 2;
        //�ٻ�͸������
        MapleMonster pvpMob = MapleLifeFactory.getMonster(9400711);
        //����˫����ֵ
        player.GainPvpkills(1);
        attackedPlayers.GainPvpdeaths(1);
        //���͹�����Ϣ
        player.getClient().sendPacket(MaplePacketCreator.serverNotice(6, "[������Ϣ]:��ɱ���� " + attackedPlayers.getName() + " ,ս��ֵ�����ˣ�"));
        attackedPlayers.getClient().sendPacket(MaplePacketCreator.serverNotice(6, "[������Ϣ]:�㱻 " + player.getName() + " ɱ���ˣ�ս��ֵ�����ˣ�"));
        sendMsgToQQGroup("[������Ϣ] : [" + KDA1 + "](" + player.getName() + ") ������ [" + KDA2 + "](" + attackedPlayers.getName() + ") ��");
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[������Ϣ] : [" + KDA1 + "](" + player.getName() + ") ������ [" + KDA2 + "](" + attackedPlayers.getName() + ") ��"));
        //���͸������
        map.killMonster(pvpMob, player, false, false, (byte) 1);
    }

    /**
     * monsterBomb
     *
     * @param player �Լ�
     * @param attackedPlayers Ŀ��
     * @param map ��ͼ
     * @param attack ����
     */
    private static void monsterBomb(MapleCharacter player, MapleCharacter attackedPlayers, MapleMap map, AttackInfo attack) {
        //�ж�BUFF����
        BUFF(player, attackedPlayers);
        //�ٻ���͸������
        MapleMonster pvpMob = MapleLifeFactory.getMonster(9400711);
        map.spawnMonsterOnGroundBelow(pvpMob, attackedPlayers.getPosition());
        for (int attacks = 0; attacks < attack.hits; attacks++) {
            //����Ŀ�����ʾ
            attackedPlayers.getClient().sendPacket(MaplePacketCreator.damagePlayer(0, pvpMob.getId(), attackedPlayers.getId(), ����������, 0, attack.animation, 0, false, pvpMob.getId(), attackedPlayers.getPosition().x, attackedPlayers.getPosition().y));
            player.getClient().sendPacket(MaplePacketCreator.damagePlayer(-1, pvpMob.getId(), attackedPlayers.getId(), ����������, 0, attack.animation, 0, false, pvpMob.getId(), attackedPlayers.getPosition().x, attackedPlayers.getPosition().y));
            attackedPlayers.addHP(-����������);
        }
        //��������Ŀ����ʾ
        attackedPlayers.getClient().sendPacket(MaplePacketCreator.sendHint("#b<�ܵ��˺�>\r\n\r\n#e#r " + ���������� + "\r\n", 200, 2));
        //Ŀ��������ִ�����
        if (attackedPlayers.getHp() <= 0 && !attackedPlayers.isAlive()) {
            killMonster(player, attackedPlayers, map);
        }
    }

    public static void doPvP(MapleCharacter player, MapleMap map, AttackInfo attack) {

        DamageBalancer(attack, player);
        if (isAoe) {
            isLeft = true;
            isRight = true;
            for (MapleCharacter attackedPlayers : player.getMap().getNearestPvpChar(player.getPosition(), maxDis, maxHeight, Collections.unmodifiableCollection(player.getMap().getCharacters()))) {
                if (attackedPlayers.isAlive() && (player.getParty() == null || player.getParty() != attackedPlayers.getParty())) {
                    monsterBomb(player, attackedPlayers, map, attack);
                }
            }
        } else if (attack.animation < 0) {// && attack.stance <= 0) {
            isLeft = true;
            isRight = false;
            for (MapleCharacter attackedPlayers : player.getMap().getNearestPvpChar(player.getPosition(), maxDis, maxHeight, Collections.unmodifiableCollection(player.getMap().getCharacters()))) {
                if (attackedPlayers.isAlive() && (player.getParty() == null || player.getParty() != attackedPlayers.getParty())) {
                    monsterBomb(player, attackedPlayers, map, attack);
                }
            }
        } else {
            isLeft = false;
            isRight = true;
            for (MapleCharacter attackedPlayers : player.getMap().getNearestPvpChar(player.getPosition(), maxDis, maxHeight, Collections.unmodifiableCollection(player.getMap().getCharacters()))) {
                if (attackedPlayers.isAlive() && (player.getParty() == null || player.getParty() != attackedPlayers.getParty())) {
                    monsterBomb(player, attackedPlayers, map, attack);
                }
            }
        }

    }

}
///*
//    private static void DamageBalancer(AttackInfo attack, MapleCharacter player) {
//        /**
//         * ��ͨ�߹��� *******************
//         */
//        if (attack.skill == 0) {
//            ���������� = SK(50, 100);
//            maxDis = 300;
//            maxHeight = 35;
//            /**
//             * ��ս���� *********************
//             */
//        } else if (��ս����(attack)) {
//            maxDis = 130;
//            maxHeight = 40;
//            isAoe = false;
//            int skillleve = player.getSkillLevel(attack.skill);
//            switch (attack.skill) {
//                //ǿ������
//                case 1001004:
//                    ���������� = skillleve * skilname("ǿ������", "ǿ������+");
//                    break;
//                //Ⱥ�幥��
//                case 1001005:
//                    isAoe = true;
//                    ���������� = skillleve * skilname("Ⱥ�幥��", "Ⱥ�幥��+");
//                    break;
//                //������
//                case 4001334:
//                    ���������� = skillleve * skilname("������", "������+");
//                    break;
//                //����ȭ
//                case 5001001:
//                    ���������� = skillleve * skilname("����ȭ", "����ȭ+");
//                    break;
//                //����ȭ
//                case 5001002:
//                    ���������� = skillleve * skilname("������", "������+");
//                    break;
//                //����ȭ
//                case 1121008:
//                    ���������� = skillleve * skilname("�������", "�������+");
//                    break;
//                //��������
//                case 1221009:
//                    ���������� = skillleve * skilname("��������", "��������+");
//                    break;
//                //ǹ����
//                case 1311001:
//                    ���������� = skillleve * skilname("ǹ����", "ǹ����+");
//                    break;
//                //ì����
//                case 1311002:
//                    ���������� = skillleve * skilname("ì����", "ì����+");
//                    break;
//                //��˫ǹ
//                case 1311003:
//                    ���������� = skillleve * skilname("��˫ǹ", "��˫ǹ+");
//                    break;
//                //��˫ì
//                case 1311004:
//                    ���������� = skillleve * skilname("��˫ì", "��˫ì+");
//                    break;
//                //��֮�׼�
//                case 1311005:
//                    ���������� = skillleve * skilname("��֮�׼�", "��֮�׼�+");
//                    break;
//                //ǿ��
//                case 3101003:
//                    ���������� = skillleve * skilname("ǿ��", "ǿ��+");
//                    break;
//                //ǿ��
//                case 3201003:
//                    ���������� = skillleve * skilname("ǿ��", "ǿ��+");
//                    break;
//                //ͻ��
//                case 1121006:
//                case 1221007:
//                case 1321003:
//                    ���������� = SK(100, 200);
//                default:
//                    ���������� = (int) (Math.floor(Math.random() * (500 - 250) + 250));
//                    break;
//            }
//            /**
//             * Զ�̹��� *********************
//             */
//        } else if (Զ�̹���(attack)) {
//            maxDis = 300;
//            maxHeight = 35;
//            isAoe = false;
//            int skillleve = player.getSkillLevel(attack.skill);
//            switch (attack.skill) {
//                //ħ����
//                case 2001004:
//                    ���������� = skillleve * skilname("ħ����", "ħ����+");
//                    break;
//                //ħ��˫��
//                case 2001005:
//                    ���������� = skillleve * skilname("ħ��˫��", "ħ��˫��+");
//                    break;
//                //˫��ն
//                case 4001344:
//                    ���������� = skillleve * skilname("˫��ն", "˫��ն+");
//                    break;
//                //�ϻ��
//                case 3001004:
//                    ���������� = skillleve * skilname("�ϻ��", "�ϻ��+");
//                    break;
//                //������
//                case 3001005:
//                    ���������� = skillleve * skilname("������", "������+");
//                    break;
//                //˫�����
//                case 5001003:
//                    ���������� = skillleve * skilname("˫�����", "˫�����+");
//                    break;
//                //�����
//                case 2101004:
//                    ���������� = skillleve * skilname("�����", "�����+");
//                    break;
//                //������
//                case 2101005:
//                    ���������� = skillleve * skilname("������", "������+");
//                    break;
//                //�𶾺ϻ�
//                case 2111006:
//                    ���������� = skillleve * skilname("�𶾺ϻ�", "�𶾺ϻ�+");
//                    break;
//                //�����
//                case 2121003:
//                    ���������� = skillleve * skilname("�����", "�����+");
//                    break;
//                //������
//                case 2201004:
//                    ���������� = skillleve * skilname("������", "������+");
//                    break;
//                //����ǹ
//                case 2211003:
//                    ���������� = skillleve * skilname("����ǹ", "����ǹ+");
//                    break;
//                //���׺ϻ�
//                case 2211006:
//                    ���������� = skillleve * skilname("���׺ϻ�", "���׺ϻ�+");
//                    break;
//                //������
//                case 2221003:
//                    ���������� = skillleve * skilname("������", "������+");
//                    break;
//                //ʥ����
//                case 2301005:
//                    ���������� = skillleve * skilname("ʥ����", "ʥ����+");
//                    break;
//                //��â�ɼ�
//                case 2321007:
//                    ���������� = skillleve * skilname("��â�ɼ�", "��â�ɼ�+");
//                    break;
//                //��ɨ��
//                case 3111006:
//                    ���������� = skillleve * skilname("��ɨ��", "��ɨ��+");
//                    break;
//                //���������
//                case 3121003:
//                    ���������� = skillleve * skilname("���������", "���������+");
//                    break;
//                //�������
//                case 3121004:
//                    ���������� = skillleve * skilname("�������", "�������+");
//                    break;
//                //��ɨ��
//                case 3211006:
//                    ���������� = skillleve * skilname("��ɨ��2", "��ɨ��2+");
//                    break;
//                //��͸��
//                case 3221001:
//                    ���������� = skillleve * skilname("��͸��2", "��͸��2+");
//                    break;
//                //һ��Ҫ����
//                case 3221007:
//                    ���������� = skillleve * skilname("һ��Ҫ����", "һ��Ҫ����+");
//                    break;
//                //��������
//                case 4101005:
//                    ���������� = skillleve * skilname("��������", "��������+");
//                    break;
//                //��Ǯ����
//                case 4111004:
//                    ���������� = skillleve * skilname("��Ǯ����", "��Ǯ����+");
//                    break;
//                //���ط���
//                case 4111005:
//                    isAoe = true;
//                    ���������� = skillleve * skilname("���ط���", "���ط���+");
//                    break;
//                //����
//                case 4121003:
//                    isAoe = true;
//                    ���������� = skillleve * skilname("����", "����+");
//                    break;
//                //�����������
//                case 4121007:
//                    isAoe = true;
//                    ���������� = skillleve * skilname("�����������", "�����������+");
//                    break;
//
//                default:
//                    ���������� = (int) (Math.floor(Math.random() * (400 - 250) + 250));
//                    break;
//            }
//            /**
//             * Ⱥ�幥�� *********************
//             */
//        } else if (Ⱥ�幥��(attack)) {
//            maxDis = 350;
//            maxHeight = 350;
//            isAoe = true;
//            int skillleve = player.getSkillLevel(attack.skill);
//            switch (attack.skill) {
//                //������
//                case 1111008:
//                    ���������� = skillleve * skilname("������", "������+");
//                    break;
//                //���Թ���
//                case 1211002:
//                    ���������� = skillleve * skilname("���Թ���", "���Թ���+");
//                    break;
//                //ʥ��
//                case 1221011:
//                    ���������� = skillleve * skilname("ʥ��", "ʥ��+");
//                    break;
//                //������
//                case 1311006:
//                    ���������� = skillleve * skilname("������", "������+");
//                    break;
//                //ĩ������
//                case 2111002:
//                    ���������� = skillleve * skilname("ĩ������", "ĩ������+");
//                    break;
//                //��������
//                case 2111003:
//                    ���������� = skillleve * skilname("��������", "��������+");
//                    break;
//                //����֮��1
//                case 2121001:
//                    ���������� = skillleve * skilname("����֮��1", "����֮��1+");
//                    break;
//                //��������
//                case 2121006:
//                    ���������� = skillleve * skilname("��������", "��������+");
//                    break;
//                //�콵����
//                case 2121007:
//                    ���������� = skillleve * skilname("�콵����", "�콵����+");
//                    break;
//                //�׵���
//                case 2201005:
//                    ���������� = skillleve * skilname("�׵���", "�׵���+");
//                    break;
//                //������
//                case 2211002:
//                    ���������� = skillleve * skilname("������", "������+");
//                    break;
//                //����֮��2
//                case 2221001:
//                    ���������� = skillleve * skilname("����֮��2", "����֮��2+");
//                    break;
//                //��������
//                case 2221006:
//                    ���������� = skillleve * skilname("��������", "��������+");
//                    break;
//                //��˪����
//                case 2221007:
//                    ���������� = skillleve * skilname("��˪����", "��˪����+");
//                    break;
//                //��˪����
//                case 2311004:
//                    ���������� = skillleve * skilname("ʥ��", "ʥ��+");
//                    break;
//                //����֮��3
//                case 2321001:
//                    ���������� = skillleve * skilname("����֮��3", "����֮��3+");
//                    break;
//                //ʥ������
//                case 2321008:
//                    ���������� = skillleve * skilname("ʥ������", "ʥ������+");
//                    break;
//                //��ը��
//                case 3101005:
//                    ���������� = skillleve * skilname("��ը��", "��ը��+");
//                    break;
//                //�����
//                case 3111003:
//                    ���������� = skillleve * skilname("�����", "�����+");
//                    break;
//                //����
//                case 3111004:
//                    ���������� = skillleve * skilname("����", "����+");
//                    break;
//                //��͸��
//                case 3201005:
//                    ���������� = skillleve * skilname("��͸��", "��͸��+");
//                    break;
//                //������
//                case 3211003:
//                    ���������� = skillleve * skilname("������", "������+");
//                    break;
//                //������
//                case 3211004:
//                    ���������� = skillleve * skilname("������", "������+");
//                    break;
//                //Ӱ����
//                case 4111003:
//                    ���������� = skillleve * skilname("Ӱ����", "Ӱ����+");
//                    break;
//                //���߷���
//                case 4121004:
//                    ���������� = skillleve * skilname("���߷���", "���߷���+");
//                    break;
//                //���߳��
//                case 4121008:
//                    ���������� = skillleve * skilname("���߳��", "���߳��+");
//                    break;
//                default:
//                    ���������� = (int) (Math.floor(Math.random() * (400 - 250) + 250));
//                    break;
//            }
//        } else {
//            maxDis = 350;
//            maxHeight = 350;
//        }
//    }
//*/
