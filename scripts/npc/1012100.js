/*
 ZEVMSð�յ�(079)��Ϸ�����
 ������תְ�̹�
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
        cm.sendOk("������.");
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
                cm.�Ƿ�˵������("����Ҫ��Ϊһλ#b������#k��");
            } else {
                cm.˵������("�ȼ���������Ҫ�ﵽ #b10#k ����");
                cm.dispose();
            }
        } else {
            if (cm.getPlayer().getLevel() >= 30 && cm.getJob() == 300) {
                if (cm.haveItem(4031012, 1)) {
                    if (cm.haveItem(4031012, 1)) {
                        status = 20;
                        cm.�Ƿ�˵������("�ҿ���������˲��ԡ�");
                    } else {
                        if (!cm.haveItem(4031010)) {
                            cm.gainItem(4031010, 1);
                        }
                        cm.sendOk("��ȥ�� #r������תְ�̹�#k.")
                        cm.dispose();
                    }
                } else {
                    status = 10;
                    cm.�Ƿ�˵������("��׼���������������ǿ��ĵ�·��");
                }
            } else if (cm.getPlayer().getLevel() >= 70 && cm.getJob() == 310 || cm.getJob() == 320) {
                if (cm.getBossRank7("��ת����1", 2) > 0) {
                    if (cm.haveItem(4031059, 1)) {
                        cm.gainItem(4031057, 1);
                        cm.gainItem(4031059, -1);
                        //�����͹�ȥ
                        //cm.warp(211000001, 0);
						cm.setBossRank7("��ת����", 2, -cm.getBossRank7("��ת����", 2));
                        cm.sendOk("�������һ�����飬����ȥ�ҳ��Ϲ�����#b����#k��");
                    } else {
                        cm.setBossRank7("��ת����", 2, 1);
                        cm.sendOk("    hi, #b#h0##k����û�뵽�����ڱ�����ǿ������Ҫһ�� #b#z4031059##k. ��ȥ��#r�Թ� ɭ���Թ�V ���֮��#k�ø��Ұɡ�");
                    }
                } else {
                    cm.sendOk("���ƺ����Ը���ǿ��");
                }
                cm.dispose();
            } else {
                cm.sendOk("���,���ǹ�����תְ�١�");
                cm.dispose();
            }
        }
    } else if (status == 1) {
        if (cm.getJob() == 0) {
            cm.changeJob(300);
            cm.resetStats(4, 25, 4, 4);
        }
        cm.forceCompleteQuest(6700);
        cm.gainItem(1452002, 1);
        cm.gainItem(2060000, 1000);
        cm.sendOk("תְ�ɹ� ! ");
        cm.dispose();
    } else if (status == 11) {
        cm.sendNextPrev("ѡ��һ����ĵ�·��;\r\n\r\n#r����#k, \r\n#r����#k.");
    } else if (status == 12) {
        cm.askAcceptDecline("�����ұ����Ȳ�����,��׼�������� ?");
    } else if (status == 13) {
        cm.gainItem(4031010, 1);
        //�����͹�ȥ
        //cm.warp(106010000);
        cm.sendOk("��ȥ�� #b������תְ�̹�#k�����������ġ�");
        cm.dispose();
    } else if (status == 21) {
        cm.sendSimple("ѡ��һ����ĵ�·��;\r\n\r\n#b\r\n#L0#����#l\r\n#L1#����#l#k");
    } else if (status == 22) {
        var jobName;
        if (selection == 0) {
            jobName = "����";
            job = 310;
        } else if (selection == 1) {
            jobName = "����";
            job = 320;
        }
        cm.sendYesNo("�����Ҫ��Ϊһλ #r" + jobName + "#k ?");
    } else if (status == 23) {
        cm.changeJob(job);
        cm.gainItem(4031012, -1);
        cm.sendOk("תְ�ɹ� ! ");
        cm.dispose();
    }
}