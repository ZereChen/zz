/*
 ZEVMSð�յ�(079)��Ϸ�����
 ����תְ�̹�
 */

var status = 0;
var jobId;
var jobName;


function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && status == 2) {
        cm.˵������("�����ԡ�");
        cm.dispose();
        return;
    }
    if (cm.�жϱ���������().isFull()) {
        cm.sendNext("������������һ����λ��");
        cm.�Ի�����();
        return;
    }
    if (mode == 1)
        status++;
    else
        status--;
    if (status == 0) {
        if (cm.getJob() == 0) {
            if (cm.getPlayer().getLevel() >= 10) {
                cm.�Ƿ�˵������("����Ҫ��Ϊһλ#b����#k��");
            } else {
                cm.˵������("�ȼ���������Ҫ�ﵽ #b10#k ����");
                cm.dispose();
            }
        } else {
            if (cm.getPlayer().getLevel() >= 30 && cm.getJob() == 400) {
                if (cm.haveItem(4031012, 1)) {
                    if (cm.haveItem(4031012, 1)) {
                        status = 20;
                        cm.�Ƿ�˵������("�ҿ���������˲��ԡ�");
                    } else {
                        if (!cm.haveItem(4031011)) {
                            cm.gainItem(4031011, 1);
                        }
                        cm.˵������("��ȥ�� #r����תְ�̹�#k.")
                        cm.dispose();
                    }
                } else {
                    status = 10;
                    cm.sendNext("��׼��������������Ӻڰ��ĵ�·��");
                }
            } else if (cm.getPlayer().getLevel() >= 70 && cm.getJob() == 410 || cm.getJob() == 420) {
                if (cm.getBossRank7("��ת����1", 2) > 0) {
                    if (cm.haveItem(4031059, 1)) {
                        cm.gainItem(4031057, 1);
                        cm.gainItem(4031059, -1);
                        //�����͹�ȥ
                        //cm.warp(211000001, 0);
                        cm.setBossRank7("��ת����", 2, -cm.getBossRank7("��ת����", 2));
                        cm.sendOk("�������һ�����飬����ȥ�ҳ��Ϲ�����#b�����#k��");
                    } else {
                        cm.setBossRank7("��ת����", 2, 1);
                        cm.sendOk("    hi, #b#h0##k����û�뵽�����ڱ�����ǿ������Ҫһ�� #b#z4031059##k. ��ȥ��#r������ ��������II ���֮��#k�ø��Ұɡ�");
                    }
                } else {
                    cm.sendOk("���ƺ����Ը���ǿ��");
                }
                cm.dispose();
            } else {
                cm.˵������("���,���ǵ���תְ��.");
                cm.dispose();
            }
        }
    } else if (status == 1) {
        if (cm.getJob() == 0) {
            cm.changeJob(400);
            cm.resetStats(4, 4, 4, 25);
        }
        cm.gainItem(1332063, 1);
        cm.gainItem(1472000, 1);
        cm.gainItem(2070000, 500);
        cm.gainItem(2070000, 500);
        cm.˵������("תְ�ɹ� !");
        cm.dispose();
    } else if (status == 11) {
        cm.sendNextPrev("ѡ��һ����ĵ�·��;\r\n\r\n#r�̿�#k\r\n#r����#k")
    } else if (status == 12) {
        cm.askAcceptDecline("�����ұ����Ȳ�����,��׼�������� ?");
    } else if (status == 13) {
        cm.gainItem(4031011, 1);
        //�����͹�ȥ
        //cm.warp(102040000);
        cm.˵������("��ȥ�� #b����תְ�̹�#k ���������ġ�");
        cm.dispose();
    } else if (status == 21) {
        cm.sendSimple("ѡ��һ����ĵ�·��;\r\n\r\n#b\r\n#L0#�̿�#l\r\n#L1#����#l#k");
    } else if (status == 22) {
        var jobName;
        if (selection == 0) {
            jobName = "�̿�";
            job = 410;
        } else if (selection == 1) {
            jobName = "����";
            job = 420;
        }
        cm.sendYesNo("�����Ҫ��Ϊһλ #r" + jobName + "#k ?");
    } else if (status == 23) {
        cm.changeJob(job);
        cm.gainItem(4031012, -1);
        cm.˵������("תְ�ɹ� ! ");
        cm.dispose();
    }
}
