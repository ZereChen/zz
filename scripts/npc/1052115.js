/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�������
 */
 
var ��͵ȼ� = 20;
var ��ߵȼ� = 200;
var ɱ������ = 10000;
var ����ѫ�� = 1142141;
var ִ�� = 0;
var section = 0;
importPackage(java.lang);
function action(mode, type, selection) {
    if (ִ�� == 99 || mode == -1) {
        cm.˵������("��Ҫ��ʱ����������ҡ�");
        cm.�Ի�����();
    }
    if (mode == 1) {
        ִ��++;
    } else {
        ִ��--;
    }
    if (ִ�� == 1) {
        if (cm.getMapId() == 910320001) {
            cm.warp(910320000, 0);
            cm.�Ի�����();
        } else if (cm.getMapId() == 910330001) {
            var itemid = 4001321;
            if (!cm.canHold(itemid)) {
                cm.˵������("��ճ�һЩ��������");
            } else {
                cm.gainItem(itemid, 1);
                cm.warp(910320000, 0);
            }
            cm.�Ի�����();
        } else if (cm.getMapId() >= 910320100 && cm.getMapId() <= 910320304) {
            cm.sendYesNo("����Ҫ�뿪����");
            ִ�� = 99;
        } else {
            cm.sendSimple("   ��� #b#h ##k ������վ������һЩ�鷳����Ը��������������Ը������ң��ҿ��Ը����� #v1142141#  #b#t1142141##k\r\n\r\n#b#L1#������ս\r\n#L2#�����г�\r\n#L3#��ȡѫ��#l#k");
        }
    } else if (ִ�� == 2) {
        section = selection;
        if (selection == 1) {
            if (cm.getPlayer().getLevel() < ��͵ȼ� || cm.getPlayer().getLevel() > ��ߵȼ� || !cm.isLeader()) {
                cm.˵������("����Ҫ�ȼ� #b"+��͵ȼ�+" - "+��ߵȼ�+"#k ֮�ڣ�������Ӻ����ҡ�");
            } else {
                if (!cm.start_PyramidSubway(-1)) {
                    cm.˵������("Ŀǰ�����ġ�");
                }
            }
        } else if (selection == 2) {
            if (cm.haveItem(4001321)) {
                if (cm.bonus_PyramidSubway(-1)) {
                    cm.gainItem(4001321, -1);
                } else {
                    cm.˵������("�����Ѿ����ˡ�");
                }
            } else {
                cm.˵������("��û��#v4001321# #b#t4001321##k��");
            }
        } else if (selection == 3) {
            var record = cm.getQuestRecord(7662);
            var data = record.getCustomData();
            if (data == null) {
                record.setCustomData("0");
                data = record.getCustomData();
            }
            var mons = parseInt(data);
            if (mons < ɱ������) {
                cm.˵������("����Ҫɱ�� #r"+ɱ������+"#k ֻ���Ŀǰ:" + mons);
            } else if (cm.canHold(����ѫ��) && !cm.haveItem(����ѫ��)) {
                cm.gainItem(����ѫ��, 1);
                cm.forceStartQuest(29931);
                cm.forceCompleteQuest(29931);
            } else {
                cm.˵������("��ճ�һЩ�ռ䡣");
            }
        }
        cm.�Ի�����();
    } else if (ִ�� == 100) {
        cm.warp(910320000, 0);
        cm.�Ի�����();
    }
}