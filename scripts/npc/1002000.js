/*
ZEVMSð�յ�(079)��Ϸ�����
�ű����������Ʊ��
*/

var status = 0;
var maps = [104000000, 102000000, 101000000, 100000000, 103000000, 120000000];
var cost = [120, 120, 80, 100, 100, 120];
var townText = [
    ["�Ǹ��ܲ���ĵط�"],
    ["�Ǹ��ܲ���ĵط�"],
    ["�Ǹ��ܲ���ĵط�"],
    ["�Ǹ��ܲ���ĵط�"],
    ["�Ǹ��ܲ���ĵط�"],
    ["�Ǹ��ܲ���ĵط�"],
    ["�Ǹ��ܲ���ĵط�"]
];
var selectedMap = -1;
var town = false;

function start() {
    cm.sendNext("����ҲҪȥ�����أ�");
}

function action(mode, type, selection) {
    status++;
    if (mode != 1) {
        if ((mode == 0 && !town) || mode == -1) {
            if (type == 1 && mode != -1)
                cm.sendNext("�кܶ࿴�����С������֪���������Ҫȥ��ĵط���");
            cm.dispose();
            return;
        } else
            status -= 2;
    }
    if (status == 1)
        cm.sendSimple("����������һ������ܻ�е���������ط������ǿ������ġ���������ط����κ�����??.\r\n#L0##b�ж��ٸ��������������?#l\r\n#L1#����ȥ��ĳ���..#k#l");
    else if (status == 2) {
        if (selection == 0) {
            town = true;
            var text = "������7����������˽���һ������??#b";
            for (var i = 0; i < maps.length; i++)
                text += "\r\n#L" + i + "##m" + maps[i] + "##l";
            cm.sendSimple(text);
        } else if (selection == 1) {
            var selStr = cm.getJob() == 0 ? "����������������90%���ۿ�\r\n��������ȥ��??#b" : "Ŷ���㲻��һ�����֣��ǰɣ������ҿ��ܻ�������ȡȫ�ۡ�����ȥ�ģ�#b";
            for (var i = 0; i < maps.length; i++)
                selStr += "\r\n#L" + i + "##m" + maps[i] + "# (" + (cost[i] * (cm.getJob() == 0 ? 1 : 10)) + "���)#l";
            cm.sendSimple(selStr);
        }
    } else if (town) {
        if (selectedMap == -1)
            selectedMap = selection;
        if (status == 3)
            cm.sendNext(townText[selectedMap][status - 3]);
        else
            townText[selectedMap][status - 3] == undefined ? cm.dispose() : cm.sendNextPrev(townText[selectedMap][status - 3]);
    } else if (status == 3) {
        selectedMap = selection;
        cm.sendYesNo("�Ҳ��㲻�������������Ҫ�ƶ��� #b#m" + maps[selection] + "##k? �ҽ�������ȡ #b" + (cost[selection] * (cm.getJob() == 0 ? 1 : 10)) + " ���#k. ����ô��??");
    } else if (status == 4) {
        if (cm.getMeso() < (cost[selectedMap] * (cm.getJob() == 0 ? 1 : 10)))
            cm.sendNext("��û���㹻�ķ���Ҳ��ܰ�����!!");
        else {
            cm.gainMeso(-(cost[selectedMap] * (cm.getJob() == 0 ? 1 : 10)));
            cm.warp(maps[selectedMap]);
        }
        cm.dispose();
    }
}