/*
 ZEVMSð�յ�(079)��Ϸ�����
 ��������תתְ�̹�
 */

var status = 0;
var job;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0 && status == 1) {
            cm.sendOk("�����¶������ٴ����Ұ�.");
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            if (cm.getJob() == 311 || cm.getJob() == 321 || cm.getJob() == 312 || cm.getJob() == 322) {
                cm.sendOk("�㻹���ǿ��!");
                cm.dispose();
                return;
            }
            if (!(cm.getJob() == 310 || cm.getJob() == 320)) {
                cm.sendOk("���ǲ����Ҵ����ˣ�");
                cm.dispose();
                return;
            } else if (cm.getPlayer().getLevel() < 70) {
                cm.sendOk("��ĵȼ���δ��70����");
                cm.dispose();
                return;
            }
            if (cm.haveItem(4031057, 1)) {
                cm.sendNext("��ϲ�㵽������,����ҽ�����һ�����顣");
            } else if (!(cm.haveItem(4031057, 1))) {
				//�����͹�ȥ
                //cm.warp(100000201);	
                cm.sendOk("ȥ�� #r������#k ���������ġ�");
				cm.setBossRank7("��ת����1", 2, 1);
                cm.dispose();
            } else if (cm.getPlayer().getRemainingSp() <= (cm.getLevel() - 70) * 3) {
                cm.sendNext("��ļ��ܵ�����û���ꡣ");
            } else {
                cm.sendOk("�㻹����תְ��");
                cm.dispose();
            }
        } else if (status == 1) {
            if (cm.haveItem(4031058, 1)) {
                if (cm.getJob() == 310) {
                    cm.changeJob(311);
					cm.getPlayer().gainAp(5);
                    cm.gainItem(4031057, -1);
                    cm.gainItem(4031058, -1);
                    cm.dispose();
                } else if (cm.getJob() == 320) {
                    cm.changeJob(321);
					cm.getPlayer().gainAp(5);
                    cm.gainItem(4031057, -1);
                    cm.gainItem(4031058, -1);
                    cm.dispose();
                }
            } else if (cm.haveItem(4031057, 1))
                cm.sendAcceptDecline("��׼���е����ղ�����");
            else
                cm.sendAcceptDecline("���ǣ��ҿ����������ǿ����Ȼ�����֤�����������ʵ���������֪ʶ����׼������ս����");
        } else if (status == 2) {
            if (cm.haveItem(4031057, 1)) {
                cm.sendOk("ȥ��#b#m211040401##k��ʥ��ʯͷ��");
                cm.dispose();
            }
        }
    }
}