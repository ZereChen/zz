/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű���������
 */


var ÿ�տ���ս = 5;
var status = -1;
var sel;
var mapid;

function start() {
    mapid = cm.getMapId();
    if (mapid == 925020001) {
        cm.sendSimple(" Hi~ #b#h ##k ��Ҫ��ս�������������ÿһ�������������зǳ������Ĺ��ﱣ�أ���ȷ������ʵ���������� ÿ��ֻ����ս #r"+ÿ�տ���ս+"#k �Σ����Ѿ���ս #r"+cm.�ж�ÿ��ֵ("������")+" #k��\r\n#b#L0#��Ҫ������ս#l \n\r #L1#��Ҫ��ӽ���#l \n\r #L2#��Ҫ�һ�����#l \n\r #L3#��Ҫ�����ҵĵ���#l");
    } else if (isRestingSpot(mapid)) {
        cm.sendSimple("����Ҫ�����ȥ��#b\r\n#L0#�ǣ��������#l\r\n#L1#�����뿪#l");
    } else {
        cm.sendYesNo("����Ҫ�뿪�ˣ���");
    }
}

function action(mode, type, selection) {
    if (mapid == 925020001) {
        if (mode == 1) {
            status++;
        } else {
            cm.�Ի�����();
            return;
        }
        if (status == 0) {
            sel = selection;
            if (sel == 3) {
                cm.sendYesNo("�������Ҫ���ã���");
            } else if (sel == 2) {
                cm.sendSimple("������ĵ��������� #b" + cm.getDojoPoints() + "#k. ���ǵ�����ϲ���вŻ����ˣ���������������㹻�ĵ�����������Ϳ��Ը�����ĵ�����������ȡ������\n\r #L0##i1132000:# #t1132000#(200)#l \n\r #L1##i1132001:# #t1132001#(1800)#l \n\r #L2##i1132002:# #t1132002#(4000)#l \n\r #L3##i1132003:# #t1132003#(9200)#l \n\r #L4##i1132004:# #t1132004#(17000)#l");
            } else if (sel == 1) {
                if (checkLevelsAndMap(30, 255) == 1) {
                    cm.sendOk("���������˵ȼ������ϡ�");
                    cm.dispose();
                } else if (checkLevelsAndMap(30, 255) == 2) {
                    cm.sendOk("�ڵ�ͼ���Ҳ������Ķ��ѡ�");
                    cm.dispose();
                } else if (cm.getParty() != null) {
                    if (cm.�ж��Ŷ�ÿ��("������") >= ÿ�տ���ս) {
                        cm.sendOk("����������ҽ����޷�������ս��");
                        cm.�Ի�����();
                        return;
                    }
                    if (cm.isLeader()) {
                        cm.���Ŷ�ÿ��("������");
                        cm.sendOk("��ʼ�����ս��");
                    } else {
                        cm.sendOk("������Ķӳ�������˵����");
                    }
                } else {
                    cm.sendOk("�����û����ӡ�");
                    cm.�Ի�����();
                    return;
                }
            } else if (sel == 0) {
                if (cm.getParty() != null) {
                    cm.sendOk("���뿪�����ӡ�");
                    cm.�Ի�����();
                    return;
                }
                var record = cm.getQuestRecord(150000);
                var data = record.getCustomData();
                if (data != null) {
                    cm.������ÿ��("������");
                    cm.warp(get_restinFieldID(parseInt(data)), 0);
                    record.setCustomData(null);
                } else {
                    cm.start_DojoAgent(true, false);
                }
                cm.�Ի�����();
            }
        } else if (status == 1) {
            if (sel == 3) {
                cm.setDojoRecord(true);
                cm.sendOk("���Ѿ��������㣬���ˡ�");
            } else if (sel == 2) {
                var record = cm.getDojoRecord();
                var required = 0;

                switch (record) {
                    case 0:
                        required = 200;
                        break;
                    case 1:
                        required = 1800;
                        break;
                    case 2:
                        required = 4000;
                        break;
                    case 3:
                        required = 9200;
                        break;
                    case 4:
                        required = 17000;
                        break;
                }

                if (record == selection && cm.getDojoPoints() >= required) {
                    var item = 1132000 + record;
                    if (cm.canHold(item)) {
                        cm.gainItem(item, 1);
                        cm.setDojoRecord(false);
                        cm.sendOk("��ϲ�һ��ɹ�����");
                    } else {
                        cm.sendOk("��ȷ��һ����ı����Ƿ�����.");
                    }
                } else if (record != selection) {
                    cm.sendOk("������˳��һ�������лл");
                } else {
                    cm.sendOk("�����û���㹻�ĵ����������Ի�....");
                }
                cm.�Ի�����();
            } else if (sel == 1) {
                cm.start_DojoAgent(true, true);
                cm.�Ի�����();
            }
        }
    } else if (isRestingSpot(mapid)) {
        if (mode == 1) {
            status++;
        } else {
            cm.�Ի�����();
            return;
        }

        if (status == 0) {
            sel = selection;

            if (sel == 0) {
                cm.dojoAgent_NextMap(true, true);
                //cm.getQuestRecord(150000).setCustomData(null);
                cm.�Ի�����();
            } else if (sel == 1) {
                cm.askAcceptDecline("�������Ҫ�뿪���");
            } else if (sel == 2) {
                if (cm.getParty() == null) {
                    var stage = get_stageId(cm.getMapId());

                    cm.getQuestRecord(150000).setCustomData(stage);
                    cm.sendOk("�Ҹոձ�������εļ�¼���´ε��㷵���Ҿ�ֱ�����㵽���");
                    cm.�Ի�����();
                } else {
                    cm.sendOk("�٣�С�һ��㲻�ܱ���..��Ϊ���������ս��");
                    cm.�Ի�����();
                }
            }
        } else if (status == 1) {
            if (sel == 1) {
                if (cm.isLeader()) {
                    cm.warpParty(925020002);
                } else {
                    cm.warp(925020002);
                }
            }
            cm.�Ի�����();
        }
    } else {
        if (mode == 1) {
            if (cm.isLeader()) {
                cm.warpParty(925020002);
            } else {
                cm.warp(925020002);
            }
        }
        cm.�Ի�����();
    }
}

function get_restinFieldID(id) {
    var idd = 925020002;
    switch (id) {
        case 1:
            idd = 925020600;
        case 2:
            idd = 925021200;
        case 3:
            idd = 925021800;
        case 4:
            idd = 925022400;
        case 5:
            idd = 925023000;
        case 6:
            idd = 925023600;
    }
    for (var i = 0; i < 15; i++) {
        var canenterr = true;
        for (var x = 1; x < 39; x++) {
            var map = cm.getMap(925020000 + 100 * x + i);
            if (map.getCharactersSize() > 0) {
                canenterr = false;
                break;
            }
        }
        if (canenterr) {
            idd += i;
            break;
        }
    }
    return idd;
}

function get_stageId(mapid) {
    if (mapid >= 925020600 && mapid <= 925020614) {
        return 1;
    } else if (mapid >= 925021200 && mapid <= 925021214) {
        return 2;
    } else if (mapid >= 925021800 && mapid <= 925021814) {
        return 3;
    } else if (mapid >= 925022400 && mapid <= 925022414) {
        return 4;
    } else if (mapid >= 925023000 && mapid <= 925023014) {
        return 5;
    } else if (mapid >= 925023600 && mapid <= 925023614) {
        return 6;
    }
    return 0;
}
function checkLevelsAndMap(lowestlevel, highestlevel) {
    var party = cm.getParty().getMembers();
    var mapId = cm.getMapId();
    var valid = 0;
    var inMap = 0;

    var it = party.iterator();
    while (it.hasNext()) {
        var cPlayer = it.next();
        if (!(cPlayer.getLevel() >= lowestlevel && cPlayer.getLevel() <= highestlevel) && cPlayer.getJobId() != 900) {
            valid = 1;
        }
        if (cPlayer.getMapid() != mapId) {
            valid = 2;
        }
    }
    return valid;
}
function isRestingSpot(id) {
    return (get_stageId(id) > 0);
}