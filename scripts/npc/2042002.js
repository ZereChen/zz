/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű������껪
 */
var status = -1;
var rank = "D";
var exp = 0;
var select = 0;

function start() {
    if (cm.getCarnivalParty() != null) {
        status = 99;
    }
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (mode == -1) {
        cm.dispose();
        return;
    }
    if (status == 0) {
        var msg = "    Hi~ #b#h ##k������Ҫ���������껪��̨��׼�����˺�������ҶԿ��𣿼��껪���ض���ʱ���ڣ�ʤ���ǻ�������ݷ�����Ŷ��\r\n\r\n#b#L0#��Ҫǰ��������̨#l";
        if (cm.getPlayer().getMapId() == 980000010) {
            msg += "\r\n#d#L1#��Ҫ���ط�������";
        }
        cm.sendSimple(msg);
    } else if (status == 1) {
        switch (selection) {
            case 0:
            {
                var level = cm.getPlayerStat("LVL");
                /*if (level >= 30 && level <= 198) {
                 cm.saveLocation("MONSTER_CARNIVAL");
                 cm.warp(980000000, "st00");
                 } else if (level >= 199 && level <= 200) {
                 cm.saveLocation("MONSTER_CARNIVAL");
                 cm.warp(980030000, "st00");
                 cm.dispose();
                 } else {
                 cm.sendOk("��ĵȼ���:"+ level +" Ŀǰû���κ���̨���Բμӡ�");
                 cm.dispose();
                 }*/
                if (level >= 30) {
                   cm.��NPC(2007,4);
                } else {
                    cm.sendOk("#b30#K ���ſ��Խ�����껪��");
                }
                cm.dispose();
                break;
            }
            case 1:
                cm.warp(103000000);
                cm.dispose();
                break;
            default:
                {
                    cm.dispose();
                    break;
                }
                break;
        }
    } else if (status == 100) {
        var carnivalparty = cm.getCarnivalParty();
        if (carnivalparty.getTotalCP() >= 501) {
            rank = "A";
            exp = 45000;
        } else if (carnivalparty.getTotalCP() >= 251) {
            rank = "B";
            exp = 35000;
        } else if (carnivalparty.getTotalCP() >= 101) {
            rank = "C";
            exp = 25000;
        } else if (carnivalparty.getTotalCP() >= 0) {
            rank = "D";
            exp = 15000;
        }
        cm.getPlayer().endPartyQuest(1301);
        if (carnivalparty.isWinner()) {
            cm.sendNext("��ϲ��Ӯ�� ̫����\r\n#b������̨������ : " + rank);
        } else {
            cm.sendNext("��Ȼ����Ҳ��Ҫ����\r\n#b������̨������ : " + rank);
        }
    } else if (status == 101) {
        var carnivalparty = cm.getCarnivalParty();
        var los = parseInt(cm.getPlayer().getOneInfo(1301, "lose"));
        var vic = parseInt(cm.getPlayer().getOneInfo(1301, "vic"));
        if (carnivalparty.isWinner()) {
            vic++;
            cm.getPlayer().updateOneInfo(1301, "vic", "" + vic);
            carnivalparty.removeMember(cm.getChar());
            cm.gainExpR(exp);
        } else {
            los++;
            cm.getPlayer().updateOneInfo(1301, "lose", "" + los);
            carnivalparty.removeMember(cm.getChar());
            cm.gainExpR(exp / 2);

        }
        cm.getPlayer().updateOneInfo(1301, "VR", "" + (java.lang.Math.ceil((vic * 100) / los)));
        cm.warp(980000000);
        cm.dispose();
    }
}