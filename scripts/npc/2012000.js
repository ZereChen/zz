/*
 ZEVMSð�յ�(079)��Ϸ�����
 */

var ticket = new Array(4031047, 4031074, 4031331, 4031576);
var cost = new Array(5000, 6000, 30000, 5000, 6000);
var mapNames = new Array("ǰ��ħ��ɭ��", "ǰ����߳�", "ǰ����ľ��", "ǰ������ɳĮ");
var mapName2 = new Array("ǰ��ħ��ɭ��", "ǰ����߳�", "ǰ����ľ��", "ǰ������ɳĮ");
var select;
var status = 0;

function start() {
    var where = "������Ҫȥ�����أ�";
    for (var i = 0; i < ticket.length; i++)
        where += "\r\n#L" + i + "##b" + mapNames[i] + "#k#l";
    cm.sendSimple(where);
}

function action(mode, type, selection) {
    if (mode < 1) {
        cm.dispose();
    } else {
        status++;
        if (status == 1) {
            select = selection;
            cm.sendYesNo("��ȷ��Ҫ���� " + mapName2[select] + " ��Ҫ " + (select == 0 ? 15 : 10) + " Сʱ����, ���Ứ���� #b" + cost[select] + " ���#k. �������Ƿ�ȷ��Ҫ���� #b#t" + ticket[select] + "##k?");
        } else if (status == 2) {
            if (cm.getMeso() < cost[select] || !cm.canHold(ticket[select]))
                cm.sendOk("��ȷ������ #b" + cost[select] + " ���#k? ����еĻ�,��Ȱ�����������������λ���Ƿ���û������.");
            else {
                cm.gainMeso(-cost[select]);
                cm.gainItem(ticket[select], 1);
            }
            cm.dispose();
        }
    }
}