/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű���������
 */
var ��͵ȼ� = 40;
var ��ߵȼ� = 255;
var ɱ������ = 50000;
var ����ѫ�� = 1142142;
var status = 0;
var section = 0;
importPackage(java.lang);
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 99) {
            cm.dispose();
            return;
        }
        status--;
    }
    if (status == 1) {
        if (cm.getMapId() >= 926020001 && cm.getMapId() <= 926020004) {
            var itemid = 4001321 + (cm.getMapId() % 10);
            if (!cm.canHold(itemid)) {
                cm.˵������("��ճ�һЩ��������");
            } else {
                cm.gainItem(itemid, 1);
                cm.warp(cm.getMapId() - 10000, 0);
            }
            cm.dispose();
        } else if (cm.getMapId() >= 926010001 && cm.getMapId() <= 926010004) {
            cm.warp(926010000, 0);
            cm.dispose();
        } else if (cm.getMapId() >= 926010100 && cm.getMapId() <= 926013504) {
            cm.sendYesNo("����Ҫ�뿪�����");
            status = 99;
        } else {
            cm.sendSimple("   ��� #b#h ##k ����Ҫȥ�������𣿺�Σ�յģ�����������������\r\n#b#L1#�������������#l\r\n#L2#���뷨�ϸ���#l\r\n#L3#�һ�����#l\r\n#L4#�һ�ѫ��#l#k");
        }
    } else if (status == 2) {
        section = selection;
        if (selection == 1) {
            cm.sendSimple("\r\n#b#L3#������ˣ���ʼ��ս��#l");
        } else if (selection == 2) {
            cm.sendSimple("������Ҫ�ı�ʯ���Ҿ������ȥ��\r\n\r\n#b#L0##i4001322##t4001322##l\r\n#L1##i4001323##t4001323##l\r\n#L2##i4001324##t4001324##l\r\n#L3##i4001325##t4001325##l");
        } else if (selection == 3) {
            cm.sendSimple("ʵ��ǿ����ˣ���Ҫ�õ�װ���ӳ֡�#b\r\n#L0##i1132012##z1132012##l\r\n#L1##i1132013##z1132013##l");
        } else if (selection == 4) {
            var record = cm.getQuestRecord(7760);
            var data = record.getCustomData();
            if (data == null) {
                record.setCustomData("0");
                data = record.getCustomData();
            }
            var mons = parseInt(data);
            if (mons < ɱ������) {
                cm.˵������("���ɱ #b" + ɱ������ + "#k ֻ�����������ڵĹ��Ŀǰ : #b" + mons + "#k ");
            } else if (cm.canHold(����ѫ��) && !cm.haveItem(����ѫ��)) {
                cm.gainItem(����ѫ��, 1);
                cm.forceStartQuest(29932);
                cm.forceCompleteQuest(29932);
            } else {
                cm.˵������("��ճ�һЩװ�����ռ䡣");
            }
            cm.dispose();
        }
    } else if (status == 3) {
        if (section == 1) {
            var cont_ = false;
            if (cm.�жϵȼ�() < ��͵ȼ�) {
                cm.˵������("��ĵȼ����� #b" + ��͵ȼ� + "#k ����");
            } else if (cm.�жϵȼ�() > ��ߵȼ�) {
                cm.˵������("��ĵȼ����� #b" + ��ߵȼ� + "#k ����");
            } else {
                cont_ = true;
            }
            if (cont_ && cm.isLeader()) {
                if (!cm.start_PyramidSubway(selection)) {
                    cm.˵������("Ŀǰ�������������ˣ����Ժ��ٳ��ԡ�");
                }
            } else if (cont_ && !cm.isLeader()) {
                cm.˵������("�������Ķӳ�������˵����");
            }
        } else if (section == 2) {
            var itemid = 4001322 + selection;
            if (!cm.haveItem(itemid, 1)) {
                cm.˵������("��û��#b#t" + itemid + "##k��");
            } else {
                if (cm.bonus_PyramidSubway(selection)) {
                    cm.gainItem(itemid, -1);
                } else {
                    cm.˵������("Ŀǰ�������������ˣ����Ժ��ٳ��ԡ�");
                }
            }
        } else if (section == 3) {
            if (selection == 0) {
                if (cm.canHold(1132012)) {
                    if (cm.haveItem(2022613, 1000)) {
                        cm.gainItem(2022613, -1000);
                        cm.gainItem(1132012, 1);
                        cm.˵������("��������Ľ�����");
                    } else {
                        cm.˵������("����Ҫ #v2022613# #b#t2022613##k #b1000#k ����");
                    }
                    cm.˵������("��ճ�һЩ�ռ䡣");
                }
            } else if (selection == 1) {
                if (cm.canHold(1132013)) {
                    if (cm.haveItem(2022613, 400) && cm.haveItem(1132012)) {
                        cm.gainItem(2022613, -400);
                        cm.gainItem(1132012, -1);
                        cm.gainItem(1132013, 1);
                        cm.˵������("��������Ľ�����");
                    } else {
                        cm.˵������("����Ҫ #v2022613# #b#t2022613##k 400�� ��һ�� #b#v1132012##t1132012#��");
                    }
                    cm.˵������("��ճ�һЩ�ռ䡣");
                }
            }
            cm.dispose();
        } else if (status == 100) {
            cm.warp(926010000, 0);
            cm.dispose();
        }
    }
}