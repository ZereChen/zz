/*
 ZEVMSð�յ�(079)��Ϸ�����
 ħ��ʦתְ�̹�
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
        cm.˵������("������һ�¡�");
        cm.�Ի�����();
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
            if (cm.�жϵȼ�() >= 8) {
                cm.�Ƿ�˵������("����Ҫ��Ϊһλ#bħ��ʦ#k��");
            } else {
                cm.˵������("�ȼ���������Ҫ�ﵽ #b8#k ����");
                cm.�Ի�����();
            }
        } else {
            if (cm.�жϵȼ�() >= 30 && cm.getJob() == 200) {
                if (cm.haveItem(4031012, 1)) {
                    if (cm.haveItem(4031012, 1)) {
                        status = 20;
                        cm.�Ƿ�˵������("�ҿ���������˲��ԣ�");
                    } else {
                        if (!cm.haveItem(4031009)) {
                            cm.gainItem(4031009, 1);
                        }
                        cm.˵������("��ȥ�� #r��ʦתְ�̹�#k��")
                        cm.�Ի�����();
                    }
                } else {
                    status = 10;
                    cm.sendNext("��׼��������ѧ����µ�ħ����");
                }
                //��ת����
            } else if (cm.�жϵȼ�() >= 70 && cm.getJob() == 210 || cm.getJob() == 220 || cm.getJob() == 230) {
                if (cm.getBossRank7("��ת����1", 2) > 0) {
                    if (cm.haveItem(4031059, 1)) {
                        cm.gainItem(4031057, 1);
                        cm.gainItem(4031059, -1);
                        //�����͹�ȥ
                        //cm.warp(211000001, 0);
                        cm.setBossRank7("��ת����", 2, -cm.getBossRank7("��ת����", 2));
                        cm.˵������("�������һ�����飬����ȥ�ҳ��Ϲ�����#b³��#k��");
                    } else {
                        cm.setBossRank7("��ת����", 2, 1);
                        cm.sendOk("    hi, #b#h0##k����û�뵽�����ڱ�����ǿ������Ҫһ�� #b#z4031059##k. ��ȥ��#r������ ����ɭ��II ���֮��#k�ø��Ұɡ�");
                    }
                } else {
                    cm.sendOk("���ƺ����Ը���ǿ��");
                }
                cm.�Ի�����();
            } else {
                cm.˵������("���,���Ƿ�ʦתְ�١�");
                cm.�Ի�����();
            }
        }
    } else if (status == 1) {
        if (cm.getJob() == 0) {
            //תְ��Ϊħ��ʦ
            cm.changeJob(200);
            //����������
            cm.resetStats(4, 4, 20, 4);
        }
        //��һ��ħ��װ��
        cm.gainItem(1372005, 1);
        cm.˵������("תְ�ɹ�����������һλħ��ʦ�ˡ�");
        cm.�Ի�����();
    } else if (status == 11) {
        cm.sendNextPrev("ѡ��һ����ĵ�·��;\r\n\r\n#r��ʦ(��,��)#k\r\n#r��ʦ(��,��)#k\r\n#r��ʦ#k");
    } else if (status == 12) {
        cm.askAcceptDecline("�����ұ����Ȳ�����,��׼�������� ?");
    } else if (status == 13) {
        cm.gainItem(4031009, 1);
        //���Զ����͹�ȥ
        //cm.warp(101020000);
        cm.˵������("��ȥ�� #b��ʦתְ�̹�#k �����������ġ�");
        cm.�Ի�����();
    } else if (status == 21) {
        cm.sendSimple("����Ҫ��Ϊʲô ? #b\r\n#L0#��ʦ(��,��)#l\r\n#L1#��ʦ(��,��)#l\r\n#L2#��ʦ#l#k");
    } else if (status == 22) {
        if (selection == 0) {
            jobName = "��ʦ(��,��)";
            jobId = 210;
        } else if (selection == 1) {
            jobName = "��ʦ(��,��)";
            jobId = 220;
        } else {
            jobName = "��ʦ";
            jobId = 230;
        }
        cm.�Ƿ�˵������("�����Ҫ��Ϊһλ #r" + jobName + "#k ?");
    } else if (status == 23) {
        cm.changeJob(jobId);
        cm.gainItem(4031012, -1);
        cm.˵������("תְ�ɹ� ! ");
        cm.�Ի�����();
    }
}