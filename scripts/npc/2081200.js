/*
 ZEVMSð�յ�(079)��Ϸ�����
 ħ��ʦ��ת�̹�
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
        if (cm.getQuestStatus(6911) != 2 || cm.getQuestStatus(6912) != 2 || cm.getQuestStatus(6913) != 2 || cm.getQuestStatus(6914) != 2 || cm.getQuestStatus(6915) != 2) {
            cm.sendOk("�����Ҹ��㽲һ�¹��°ɣ�");
            cm.dispose();
            return;
        } else if (!(cm.getJob() == 211 || cm.getJob() == 221 || cm.getJob() == 231)) {
            cm.sendOk("Ϊʲô��Ҫ����??��������Ҫ���ҹ���ʲô����??");
            cm.dispose();
            return;
        } else if (cm.getPlayer().getLevel() < 120) {
            cm.sendOk("��ȼ���δ����120��.");
            cm.dispose();
            return;
        } else {
            if (cm.getJob() == 211) {
                cm.sendSimple("���������ǿ��\r\n#b#L0#�����Ϊħ��ʦ#l\r\n#b#L1#������һ��...#l");
            } else if (cm.getJob() == 221) {
                cm.sendSimple("���������ǿ��\r\n#b#L0#�����Ϊħ��ʦ#l\r\n#b#L1#������һ��...#l");
            } else if (cm.getJob() == 231) {
                cm.sendSimple("���������ǿ��\r\n#b#L0#�����Ϊ����#l\r\n#b#L1#������һ��...#l");
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
                if (cm.getJob() == 211) {
                    cm.changeJob(212);
                    cm.teachSkill(2121001, 0, 10);
                    cm.teachSkill(2121006, 0, 10);
                    cm.teachSkill(2121002, 0, 10);
                    cm.sendNext("��ϲ��תְΪ #bħ��ʦ#k ������һЩ����С����^^");
                } else if (cm.getJob() == 221) {
                    cm.changeJob(222);
                    cm.teachSkill(2221001, 0, 10);
                    cm.teachSkill(2221006, 0, 10);
                    cm.teachSkill(2221002, 0, 10);
                    cm.sendNext("��ϲ��תְΪ #bħ��ʦ#k ������һЩ����С����^^");
                } else {
                    cm.changeJob(232);
                    cm.teachSkill(2321001, 0, 10);
                    cm.teachSkill(2321005, 0, 10);
                    cm.teachSkill(2321002, 0, 10);
                    cm.sendNext("��ϲ��תְΪ #b����#k ������һЩ����С����^^");
                }
            } else {
                cm.sendOk("��û�ж����λ�������������һ��!");
                cm.dispose();
                return;
            }
        }
    } else if (status == 2) {
        if (cm.getJob() == 212) {
            cm.sendNext("��Ҫ��������һ�ж�ȡ���������˶���.");
        } else if (cm.getJob() == 222) {
            cm.sendNextPrev("��Ҫ��������һ�ж�ȡ���������˶���.");
        } else {
            cm.sendNextPrev("��Ҫ��������һ�ж�ȡ���������˶���.");
        }
    } else if (status == 3) {
        cm.dispose();
    }
}