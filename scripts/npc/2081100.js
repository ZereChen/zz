/*
 ZEVMSð�յ�(079)��Ϸ�����
 սʿ��ת�̹�
 */

var status = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1)
        status++;
    else
        status--;

    if (status == 0) {
        if (cm.getQuestStatus(6901) != 2 || cm.getQuestStatus(6902) != 2 || cm.getQuestStatus(6903) != 2|| cm.getQuestStatus(6904) != 2|| cm.getQuestStatus(6905) != 2) {
            cm.sendOk("�����Ҹ��㽲һ�¹��°ɣ�");
            cm.dispose();
            return;
        } else if (!(cm.getJob() == 111 || cm.getJob() == 121 || cm.getJob() == 131 || cm.getJob() == 2111)) {
            cm.sendOk("��ã���������ʲô������");
            cm.dispose();
            return;
        } else if (cm.getPlayer().getLevel() < 120) {
            cm.sendOk("��ȼ���δ����120��.");
            cm.dispose();
            return;
        } else {
            if (cm.getJob() == 111) {
                cm.sendSimple("���������ǿ��\r\n#b#L0#�����ΪӢ��#l\r\n#b#L1#������һ�¡�#l");
            } else if (cm.getJob() == 121) {
                cm.sendSimple("���������ǿ��\r\n#b#L0#�����Ϊʥ��ʿ#l\r\n#b#L1#������һ�¡�#l");
            } else if (cm.getJob() == 131) {
                cm.sendSimple("���������ǿ��\r\n#b#L0#�����Ϊ����ʿ#l\r\n#b#L1#������һ�¡�#l");
            } else if (cm.getJob() == 2111) {
                cm.sendSimple("���������ǿ��\r\n#b#L0#�����ǿ#l\r\n#b#L1#������һ��...#l");
            } else {
                cm.sendOk("�ðɼ�������Ҫ4ת�鷳��������");
                cm.dispose();
                return;
            }
        }
    } else if (status == 1) {
        if (selection == 1) {
            cm.sendOk("�ðɼ�������Ҫ4ת�鷳�������ҡ�");
            cm.dispose();
            return;
        }
        if (cm.getPlayerStat("RSP") > (cm.getPlayerStat("LVL") - 120) * 3) {
            cm.sendOk("��ļ��ܵ�����û���ꡣ");
            cm.dispose();
            return;
        } else {
            if (cm.canHold(2280003)) {
                cm.gainItem(2280003, 1);
                if (cm.getJob() == 111) {
                    cm.changeJob(112);
                    cm.teachSkill(1121001, 0, 10);
                    cm.teachSkill(1120004, 0, 10);
                    cm.teachSkill(1121008, 0, 10);
                    cm.sendNext("��ϲ��תְΪ #bӢ��#k ������һЩ����С����^^");
                } else if (cm.getJob() == 121) {
                    cm.changeJob(122);
                    cm.teachSkill(1221001, 0, 10);
                    cm.teachSkill(1220005, 0, 10);
                    cm.teachSkill(1221009, 0, 10);
                    cm.sendNext("��ϲ��תְΪ #bʥ��ʿ#k ������һЩ����С����^^");
                } else if (cm.getJob() == 131) {
                    cm.changeJob(132);
                    cm.teachSkill(1321001, 0, 10);
                    cm.teachSkill(1320005, 0, 10);
                    cm.teachSkill(1321007, 0, 10);
                    cm.sendNext("��ϲ��תְΪ #b����ʿ#k ������һЩ����С����^^");
                } else if (cm.getJob() == 2111) {
                    cm.changeJob(2112);
                    if (cm.canHold(1142132, 1)) {
                        cm.forceCompleteQuest(29927);
                        cm.gainItem(1142132, 1);
                    }
                    cm.sendNext("��ϲ�������ʿ�ָ���һ��¥�ˣ� ����������һЩ����С����^^");
                } else {
                    cm.sendOk("��û�ж����λ�������������һ��!");
                    cm.dispose();
                    return;
                }
            }
        }
    } else if (status == 2) {
        cm.sendNextPrev("��Ҫ��������һ�ж�ȡ���������˶���.");
        cm.dispose();
    }
}