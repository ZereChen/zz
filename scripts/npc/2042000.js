/*
 ZEVMSð�յ�(079)��Ϸ�����
 */
var status = 0;
var ����1= " Hi~ #b#h ##k ����Ҫ�μӹ�����껪�������Ƿǳ���Ȥ��Ŷ��\r\n";

function start() {
    status = -1;
    action(1, 0, 0);
}


function action(mode, type, selection) {
    if (mode == 1)
        status++;
    else
        cm.dispose();
    if (status == 0 && mode == 1) {
        var selStr = "   ��ѡ��һ�ּ��껪ս������:\r\n#L100##b �һ�����#l";
        var found = false;
        for (var i = 0; i < 6; i++) {
            if (getCPQField(i + 1) != "") {
                selStr += "\r\n#b#L" + i + "# " + getCPQField(i + 1) + "#l#k";
                found = true;
            }
        }
        if (cm.getParty() == null) {
            cm.sendSimple(" "+����1+"   ������������ҡ�\r\n\r\n#L100##b�һ�����#l");
        } else {
            if (cm.isLeader()) {
                var pt = cm.getPlayer().getParty();
                if (pt.getMembers().size() < 0) {
                    cm.sendOk("��Ҫ 1 �˾Ϳ��Լ��껪����");
                    cm.dispose();
                    if ((cm.getParty() != null && 1 < cm.getParty().getMembers().size() && cm.getParty().getMembers().size() < (selection == 4 || selection == 5 || selection == 8 ? 4 : 3)) || cm.getPlayer().isGM()) {
                        if (checkLevelsAndMap(30, 198) == 1) {
                            cm.sendOk("���������˵ȼ������ϡ�");
                            cm.dispose();
                        } else if (checkLevelsAndMap(30, 198) == 1) {
                            cm.sendOk("�ڵ�ͼ���Ҳ������Ķ��ѡ�");
                            cm.dispose();
                        }
                    }
                }
                if (found) {
                    cm.sendSimple(selStr);
                } else {
                    cm.sendSimple(" "+����1+"   Ŀǰû�з���.\r\n#L100##b�һ�����#l");
                }
            } else {
                cm.sendSimple(" "+����1+"   �����Ķӳ�������\r\n#L100##b�һ�����#l");
            }
        }
    } else if (status == 1) {
        if (selection == 100) {
            cm.sendSimple("   ��Ҫ�һ�ʲô��\r\n\r\n#b#L0#"+cm.��ʾ��Ʒ(4001129)+" x 50 = #b#z1122007##k#l\r\n#L1#"+cm.��ʾ��Ʒ(4001129)+" x 30 = #b#z2041211##k#l\r\n#L2#"+cm.��ʾ��Ʒ(4001254)+" x 15 = #b#z1122058##k#l #L3#"+cm.��ʾ��Ʒ(4001129)+" x 20 = #b#z4001254##l#k");
        } else if (selection >= 0 && selection < 6) {
            var mapid = 980000000 + ((selection + 1) * 100);
            if (cm.getEventManager("cpq").getInstance("cpq" + mapid) == null) {
                if ((cm.getParty() != null && 1 < cm.getParty().getMembers().size() && cm.getParty().getMembers().size() < (selection == 4 || selection == 5 || selection == 8 ? 4 : 3)) || cm.getPlayer().isGM()) {
                    if (checkLevelsAndMap(30, 198) == 1) {
                        cm.sendOk("���������˵ȼ������ϡ�");
                        cm.dispose();
                    } else if (checkLevelsAndMap(30, 198) == 1) {
                        cm.sendOk("�ڵ�ͼ���Ҳ������Ķ��ѡ�");
                        cm.dispose();
                    } else {
                        cm.getEventManager("cpq").startInstance("" + mapid, cm.getPlayer());
                        cm.dispose();
                    }
                } else {
                    cm.sendOk("���Ķ����������㡣");
                }
            } else if (cm.getParty() != null && cm.getEventManager("cpq").getInstance("cpq" + mapid).getPlayerCount() == cm.getParty().getMembers().size()) {
                if (checkLevelsAndMap(30, 198) == 1) {
                    cm.sendOk("���������˵ȼ������ϡ�");
                    cm.dispose();
                } else if (checkLevelsAndMap(30, 198) == 1) {
                    cm.sendOk("�ڵ�ͼ���Ҳ������Ķ��ѡ�");
                    cm.dispose();
                } else {
                    var pt = cm.getPlayer().getParty();
                    if (pt.getMembers().size() < 1) {
                        cm.sendOk("��Ҫ 2 �����ϲſ��Լ��껪����");
                        cm.dispose();
                    } else {
                        var owner = cm.getChannelServer().getPlayerStorage().getCharacterByName(cm.getEventManager("cpq").getInstance("cpq" + mapid).getPlayers().get(0).getParty().getLeader().getName());
                        owner.addCarnivalRequest(cm.getCarnivalChallenge(cm.getChar()));
                        if (owner.getConversation() != 1) {
							cm.openNpc(owner.getClient(), 2042001);
                        }
                        cm.sendOk("������ս�Ѿ����͡�");
                        cm.dispose();
                    }
                }
            } else {
                cm.sendOk("���������������");
                cm.dispose();
            }
        } else {
            cm.dispose();
        }
    } else if (status == 2) {
        if (selection == 0) {
            if (!cm.�ж���Ʒ����(4001129, 50)) {
                cm.sendOk("�ܱ�Ǹ����û��# "+cm.��ʾ��Ʒ(4001129)+" #b50#k ����");
            } else if (!cm.canHold(1122007, 1)) {
                cm.sendOk("����������");
            } else {
                cm.gainItem(1122007, 1, true);
                cm.gainItem(4001129, -50);
            }
            cm.dispose();
        } else if (selection == 1) {
            if (!cm.�ж���Ʒ����(4001129, 30)) {
                cm.sendOk("�ܱ�Ǹ����û�� "+cm.��ʾ��Ʒ(4001129)+" #b30#k ����");
            } else if (!cm.canHold(2041211, 1)) {
                cm.sendOk("����������");
            } else {
                cm.gainItem(2041211, 1);
                cm.gainItem(4001129, -30);
            }
            cm.dispose();
        } else if (selection == 2) {
            if (!cm.�ж���Ʒ����(4001254, 15)) {
                cm.sendOk("�ܱ�Ǹ����û�� "+cm.��ʾ��Ʒ(4001254)+" #b15#k ����");
            } else if (!cm.canHold(1122058, 1)) {
                cm.sendOk("����������");
            } else {
                cm.gainItem(1122058, 1, true);
                cm.gainItem(4001254, -15);
            }
            cm.dispose();
        } else if (selection == 3) {
            if (!cm.�ж���Ʒ����(4001129, 20)) {
                cm.sendOk("�ܱ�Ǹ����û�� "+cm.��ʾ��Ʒ(4001129)+" #b20#k ����");
            } else if (!cm.canHold(4001254, 1)) {
                cm.sendOk("����������");
            } else {
                cm.gainItem(4001254, 1, true);
                cm.gainItem(4001129, -20);
            }
            cm.dispose();
        }
    }
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

function getCPQField(fieldnumber) {
    var status = "";
    var event1 = cm.getEventManager("cpq");
    if (event1 != null) {
        var event = event1.getInstance("cpq" + (980000000 + (fieldnumber * 100)));
        if (event == null && fieldnumber != 5 && fieldnumber != 6 && fieldnumber != 9) {
            status = "��̨������ " + fieldnumber + "(1v1)";
        } else if (event == null) {
            status = "��̨������ " + fieldnumber + "(3v3)";
        } else if (event != null && (event.getProperty("started").equals("false"))) {
            var averagelevel = 0;
            for (i = 0; i < event.getPlayerCount(); i++) {
                averagelevel += event.getPlayers().get(i).getLevel();
            }
            averagelevel /= event.getPlayerCount();
            status = event.getPlayers().get(0).getParty().getLeader().getName() + "/" + event.getPlayerCount() + "��/ƽ���ȼ� " + averagelevel;
        }
    }
    return status;
}