/*
 ZEVMSð�յ�(079)��Ϸ�����
 ������ת�̹�
 */


var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        status--;
    }

    if (status == 0) {
        if (cm.getQuestStatus(6931) != 2 || cm.getQuestStatus(6932) != 2 || cm.getQuestStatus(6933) != 2 || cm.getQuestStatus(6934) != 2 || cm.getQuestStatus(6935) != 2) {
            cm.sendOk("�����Ҹ��㽲һ�¹��°ɣ�");
            cm.dispose();
            return;
        } else if (!(cm.getJob() == 411 || cm.getJob() == 421 || cm.getJob() == 433)) {
            cm.sendOk("Ϊʲô��Ҫ����??��������Ҫ���ҹ���ʲô����??");
            cm.dispose();
            return;
        } else if (cm.getPlayer().getLevel() < 120) {
            cm.sendOk("��ȼ���δ����120����");
            cm.dispose();
            return;
        } else {
            if (cm.getJob() == 411) {
                cm.sendSimple("���������ǿ��\r\n#b#L0#�����Ϊ��ʿ#l\r\n#b#L1#������һ��...#l");
            } else if (cm.getJob() == 421) {
                cm.sendSimple("���������ǿ��\r\n#b#L0#�����Ϊ����#l\r\n#b#L1#������һ��...#l");
            } else {
                cm.sendOk("�ðɼ�������Ҫ4ת�鷳�������ҡ�");
                cm.safeDispose();
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
                if (cm.getJob() == 411) {
                    cm.changeJob(412);
                    cm.teachSkill(4120002, 0, 10);
                    cm.teachSkill(4121006, 0, 10);
                    cm.teachSkill(4120005, 0, 10);
                    cm.gainItem(4031348, -1);
                    cm.sendNext("��ϲ��תְΪ #b��ʿ#k.������һЩ����С����^^");
                } else if (cm.getJob() == 421) {
                    cm.changeJob(422);
                    cm.teachSkill(4220002, 0, 10);
                    cm.teachSkill(4221007, 0, 10);
                    cm.teachSkill(4220005, 0, 10);
                    cm.gainItem(4031348, -1);
                    cm.sendNext("��ϲ��תְΪ #b����#k.������һЩ����С����^^");
                } else {
                    cm.sendOk("��û�ж����λ�������������һ��!");
                    cm.safeDispose();
                    return;
                }
            }
        }
    } else if (status == 2) {
        cm.sendNextPrev("��Ҫ��������һ�ж�ȡ���������˶���.");
    } else if (status == 3) {
        cm.dispose();
    }
}