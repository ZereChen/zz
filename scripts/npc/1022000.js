/*
 ZEVMSð�յ�(079)��Ϸ�����
 սʿתְ�̹�
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
        cm.sendOk("�����ԡ�");
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
                cm.�Ƿ�˵������("����Ҫ��Ϊһλ#bսʿ#k��");
            } else {
                cm.˵������("�ȼ���������Ҫ�ﵽ #b10#k ����");
                cm.dispose();
            }
        } else {
            if (cm.getPlayer().getLevel() >= 30 && cm.getJob() == 100) {
                if (cm.haveItem(4031012, 1)) {
                    if (cm.haveItem(4031012, 1)) {
                        status = 20;
                        cm.�Ƿ�˵������("�ҿ���������˲��ԣ�");
                    } else {
                        if (!cm.haveItem(4031008)) {
                            cm.gainItem(4031008, 1);
                        }
                        cm.sendOk("��ȥ�� #rսʿתְ�̹�#k��")
                        cm.dispose();
                    }
                } else {
                    status = 10;
                    cm.sendNext("��׼���������������ǿ��ĵ�·��");
                }
                //��ת����
            } else if (cm.getPlayer().getLevel() >= 70 && cm.getJob() == 110 || cm.getJob() == 120 || cm.getJob() == 130 || cm.getJob() == 2110) {
				//������ȥ�Ի���ת�̹�
                if (cm.getBossRank7("��ת����1", 2) > 0) {
                    if (cm.haveItem(4031059, 1)) {
                        cm.gainItem(4031057, 1);
                        cm.gainItem(4031059, -1);
                        //�����͹�ȥ
                        //cm.warp(211000001, 0);
                        cm.setBossRank7("��ת����", 2, -cm.getBossRank7("��ת����", 2));
                        cm.sendOk("�������һ�����飬����ȥ�ҳ��Ϲ���#b̩��˹#k��");
                    } else {
                        cm.setBossRank7("��ת����", 2, 1);
                        cm.sendOk("    hi, #b#h0##k����û�뵽�����ڱ�����ǿ������Ҫһ�� #b#z4031059##k. ��ȥ��##r�Թ� ���Ϲ㳡 ���֮��#k�ø��Ұɡ�");
                    }
                }else{
					cm.sendOk("���ƺ����Ը���ǿ��");
				}
                cm.dispose();
            } else {
                cm.sendOk("���,����սʿתְ�١�");
                cm.dispose();
            }
        }
    } else if (status == 1) {
        if (cm.getJob() == 0) {
            cm.changeJob(100);
            cm.resetStats(35, 4, 4, 4);
        }
        cm.gainItem(1402001, 1);
        cm.sendOk("תְ�ɹ� ! ��������һλսʿ�ˡ�");
        cm.dispose();
    } else if (status == 11) {
        cm.sendNextPrev("ѡ��һ����ĵ�·��;\r\n\r\n#r����#k\r\n#r׼��ʿ#k\r\n#rǹսʿ#k");
    } else if (status == 12) {
        cm.askAcceptDecline("�����ұ����Ȳ�����,��׼��������?");
    } else if (status == 13) {
        cm.gainItem(4031008, 1);
        //���Զ����͹�ȥ
        //cm.warp(102020300);
        cm.sendOk("��ȥ�� #bսʿתְ�̹�#k �����������ġ�");
        cm.dispose();
    } else if (status == 21) {
        cm.sendSimple("��ѡ��һ����ĵ�·��; #b\r\n#L0#����#l\r\n#L1#׼��ʿ#l\r\n#L2#ǹսʿ#l#k");
    } else if (status == 22) {
        var jobName;
        if (selection == 0) {
            jobName = "����";
            job = 110;
        } else if (selection == 1) {
            jobName = "׼��ʿ";
            job = 120;
        } else {
            jobName = "ǹսʿ";
            job = 130;
        }
        cm.�Ƿ�˵������("�����Ҫ��Ϊһλ #r" + jobName + "#k ?");
    } else if (status == 23) {
        cm.changeJob(job);
        cm.gainItem(4031012, -1);
        cm.sendOk("תְ�ɹ� ! ");
        cm.dispose();
    }
}