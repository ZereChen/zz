/*
 ZEVMSð�յ�(079)��Ϸ�����
 ������תתְ�̹�
 */

var status = 0;
var jobId;
var jobName;
var mapId


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
    if (mode == 1)
        status++;
    else
        status--;
    if (status == 0) {
       /* if ((cm.getJob() == 510 || cm.getJob() == 520)&& cm.getMapId() == 912010200 && cm.haveItem(4031059, 1)) {
            if (cm.getQuestStatus(6370) == 1) {
                cm.removeAll(4031059);
                cm.teachSkill(5221006, 0, 10);
                cm.forceCompleteQuest(6370);
            } else if (cm.getQuestStatus(6330) == 1) {
                cm.removeAll(4031059);
                cm.teachSkill(5121003, 0, 10);
                cm.forceCompleteQuest(6330);
            }
            //cm.warp(120000101, 0);
			cm.gainItem(4031057, 1);
			cm.removeAll(4031059);
			cm.sendOk("�������һ�����飬����ȥ�ҳ��Ϲ�����#b�����#k��");
            cm.dispose();
			return;
        }*/
        if (cm.getJob() == 0) {
            if (cm.getPlayer().getLevel() >= 10) {
                cm.�Ƿ�˵������("����Ҫ��Ϊһλ#b����#k��");
            } else {
                cm.˵������("�ȼ���������Ҫ�ﵽ #b10#k ����");
                cm.dispose();
            }
        } else {
            if (cm.getPlayer().getLevel() >= 30 && cm.getJob() == 500) {
                if (cm.haveItem(4031012, 1)) {
                    if (cm.haveItem(4031012, 1)) {
                        status = 20;
                        cm.sendNext("�ҿ���������˲��ԡ�");
                    } else {
                        cm.sendOk("��ȥ�� #r����תְ�̹�#k.")
                        cm.dispose();
                    }
                } else {
                    status = 10;
                    cm.sendNext("��׼�������������������ĵ�·��");
                }
            } else if (cm.getPlayer().getLevel() >= 70 && cm.getJob() == 510 || cm.getJob() == 520) {
                if (cm.getBossRank7("��ת����1", 2) > 0) {
                    if (cm.haveItem(4031059, 1)) {
                        cm.gainItem(4031057, 1);
                        cm.gainItem(4031059, -1);
						//�����͹�ȥ
                        //cm.warp(211000001, 0);
						cm.setBossRank7("��ת����", 2, -cm.getBossRank7("��ת����", 2));
						cm.sendOk("�������һ�����飬����ȥ�ҳ��Ϲ�����#b�ѵ���#k��");
                    } else {
                        cm.setBossRank7("��ת����", 2, 1);
                        cm.sendOk("    hi, #b#h0##k����û�뵽�����ڱ�����ǿ������Ҫһ�� #b#z4031059##k. ��ȥ��#r�Թ� ������޶�ѨII ���֮��#k�ø��Ұɡ�");
                        cm.startQuest(100101);
                    }
                } else {
                    cm.sendOk("���ƺ����Ը���ǿ��");
                }
                cm.dispose();
            } else if (cm.isQuestActive(6370)) {
                var dd = cm.getEventManager("KyrinTrainingGroundC");
                if (dd != null) {
                    dd.startInstance(cm.getPlayer());
                } else {
                    cm.sendOk("δ֪�Ĵ������Ժ��ڳ��ԡ�");
                }
            } else if (cm.isQuestActive(6330)) {
                var dd = cm.getEventManager("KyrinTrainingGroundV");
                if (dd != null) {
                    dd.startInstance(cm.getPlayer());
                } else {
                    cm.sendOk("δ֪�Ĵ������Ժ��ڳ��ԡ�");
                }
            } else {
                cm.sendOk("���,���ǿ�����-����תְ��.");
                cm.dispose();
            }
        }
    } else if (status == 1) {
        if (cm.getJob() == 0) {
            cm.changeJob(500);
            cm.resetStats(4, 4, 4, 4);
        }
        cm.gainItem(1482014, 1);
        cm.gainItem(1492014, 1);
        cm.gainItem(2330000, 600);
        cm.gainItem(2330000, 600);
        cm.gainItem(2330000, 600);
        cm.sendOk("תְ�ɹ� !");
        cm.dispose();
    } else if (status == 11) {
        cm.sendNextPrev("ѡ��һ����ĵ�·��;\r\n\r\n#rȭ��#k\r\n#rǹ��#k")
    } else if (status == 12) {
        cm.askAcceptDecline("�����ұ����Ȳ�����,��׼�������� ?");
    } else if (status == 13) {
        cm.sendSimple("ѡ��һ����ĵ�·��;\r\n\r\n#b#L0#ȭ��#l\r\n#L1#ǹ��#l#k");
    } else if (status == 14) {
        var jobName;
        if (selection == 0) {
            jobName = "ȭ��";
            MapId = "108000502";
        } else if (selection == 1) {
            jobName = "ǹ��";
            MapId = "108000500";
        }
        cm.sendYesNo("�����Ҫ��Ϊһλ #r" + jobName + "#k?");
    } else if (status == 15) {
        cm.warp(MapId);
        //cm.sendOk("��ȥ�� #b����תְ�̹�#k . ����������.");
        cm.dispose();
    } else if (status == 21) {
        cm.sendSimple("����Ҫ��Ϊʲô ? #b\r\n#L0#ȭ��#l\r\n#L1#ǹ��#l#k");
    } else if (status == 22) {
        var jobName;
        if (selection == 0) {
            jobName = "ȭ��";
            job = 510;
        } else if (selection == 1) {
            jobName = "ǹ��";
            job = 520;
        }
        cm.sendYesNo("�����Ҫ��Ϊһλ #r" + jobName + "#k ?");
    } else if (status == 23) {
        cm.changeJob(job);
        if (cm.haveItem(4031857) && cm.haveItem(4031012, 1)) {
            cm.gainItem(4031857, -15);
            cm.gainItem(4031012, -1);
            cm.sendOk("תְ�ɹ� !");
            cm.dispose();
        } else if (cm.haveItem(4031856) && cm.haveItem(4031012, 1)) {
            cm.gainItem(4031856, -15);
            cm.gainItem(4031012, -1);
            cm.sendOk("תְ�ɹ� !");
            cm.dispose();
        } else {
            cm.removeAll(4031857);
            cm.removeAll(4031856);
            cm.removeAll(4031012);
            cm.sendOk("תְ�ɹ� !");
            cm.dispose();
        }
    }
}