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
        if (cm.getQuestStatus(6941) != 2 || cm.getQuestStatus(6942) != 2 || cm.getQuestStatus(6943) != 2 || cm.getQuestStatus(6944) != 2) {
            cm.sendOk("�����Ҹ��㽲һ�¹��°ɣ�");
            cm.dispose();
            return;
        } else if (!(cm.getJob() == 511 || cm.getJob() == 521)) {
            cm.sendOk("Ϊʲô��Ҫ����??��������Ҫ���ҹ���ʲô����??");
            cm.dispose();
            return;
        } else if (cm.getPlayer().getLevel() < 120) {
            cm.sendOk("��ȼ���δ����120����");
            cm.dispose();
            return;
        } else {
            if (cm.getJob() == 511) {
                cm.sendSimple("���������ǿ��\r\n#b#L1#�����Ϊ���ӳ�#l#l");
            } else if (cm.getJob() == 521) {
                cm.sendSimple("���������ǿ��\r\n#b#L1#�����Ϊ����#l#l");
            } else {
                cm.sendOk("�ðɼ�������Ҫ4ת�鷳�������ҡ�");
                cm.dispose();
                return;
            }
        }
    } else if (status == 1) {
        if (selection == 1) {
                if (cm.getJob() == 511) {
                    cm.changeJob(512);
                    cm.teachSkill(5121007, 0, 10);
                    cm.teachSkill(5121001, 0, 10);
                    cm.teachSkill(5121002, 0, 10);
                    cm.teachSkill(5121009, 0, 10);
                    cm.sendNext("��ϲ��תְΪ #b���ӳ�#k.������һЩ����С����^^");
                } else if (cm.getJob() == 521) {
                    cm.changeJob(522);
                    cm.teachSkill(5221004, 0, 10);
                    cm.teachSkill(5220001, 0, 10);
                    cm.teachSkill(5220002, 0, 10);
                    cm.teachSkill(5220011, 0, 10);
                    cm.sendNext("��ϲ��תְΪ #b����#k.������һЩ����С����^^");
                }
            cm.dispose();
        }
    } else if (status == 2) {
        if (cm.getJob() == 512) {
            cm.sendNext("��Ҫ��������һ�ж�ȡ���������˶���.");
        } else {
            cm.sendNext("��Ҫ��������һ�ж�ȡ���������˶���.");
        }
    } else if (status == 3) {
        cm.dispose();
    }
}