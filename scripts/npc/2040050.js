/*
 ZEVMSð�յ�(079)��Ϸ����˽ű�
 */
var status = 0;
var menu = "";
var set;
var makeitem;
var access = true;
var reqitem = new Array();
var cost = 10000;
var makeditem = new Array(
        4006000,
        4006001);
var reqset = new Array(
        [
            [[4000046, 20], [4000027, 20]],
			[[4000025, 20], [4000027, 20]],
            [[4000025, 20], [4000049, 20]],
            [[4000129, 15], [4000130, 15]],
            [[4000074, 15], [4000057, 15]],
            [[4000054, 10], [4000053, 10]],
            [[4000238, 15], [4000241, 15]]

        ],
        [
            [[4000028, 20], [4000027, 20]],
			[[4000025, 20], [4000027, 20]],
            [[4000014, 20], [4000056, 20]],
            [[4000132, 15], [4000128, 15]],
            [[4000074, 15], [4000069, 15]],
            [[4000080, 10], [4000079, 10]],
            [[4000226, 15], [4000237, 15]]

        ]
        );

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && (status == 1 || status == 2)) {
        cm.dispose();
        return;
    }
    if (mode == 0) {
        cm.sendNext("��û�õ���Ҫ�Ĳ��ϰ�? ���Ժ���ʱ�в��Ͼ͸��Ұɡ�����,����ȵȣ��õ����ߵİ취�ж��֡�");
        cm.dispose();
    }
    if (mode == 1) {
        status++;
    }
    if (status == 0) {
        cm.sendNext("û�з���֮��~ Ӵ? ��ʲôʱ����������?");
    } else if (status == 1) {
        cm.sendSimple("   Hi~#b#h ##k ��������������ʿ���һ�����һЩϡ�еĵ��ߣ�����û����Ҫ�˽�һ�µ��أ�\r\n\r\n#b#L0#" + cm.��ʾ��Ʒ(4006000) + "#l\r\n#b#L1#" + cm.��ʾ��Ʒ(4006001) + "#l");
    } else if (status == 2) {
        set = selection;
        makeitem = makeditem[set];
        for (i = 0; i < reqset[set].length; i++) {
            menu += "\r\n#L" + i + "##b#z" + reqset[set][i][0][0] + "##k x #b" + reqset[set][i][0][1] + "#k��#b#z" + reqset[set][i][1][0] + "# x #b" + reqset[set][i][1][1] + "#k#k#l";

        }
        cm.sendSimple("    ֻ��������#b#t" + makeitem + "##k�����ص�ʯͷ. ����ܶ�ð����˵��Ҫ���. ��#t" + makeitem + "#�İ취������Щ�����ˣ���Ҫ��ʲô����������?" + menu);
    } else if (status == 3) {
        if (selection < 0) {
            cm.dispose();
            return;
        }
        set = reqset[set][selection];
        reqitem[0] = new Array(set[0][0], set[0][1]);
        reqitem[1] = new Array(set[1][0], set[1][1]);
        menu = "";
        for (i = 0; i < reqitem.length; i++) {
            menu += "\r\n#v" + reqitem[i][0] + "# #t" + reqitem[i][0] + "# x #b" + reqitem[i][1] + "#k";
        }
        menu += "\r\n#i4031138# x #b" + cost + " ���#k";
        cm.sendYesNo("    ��#b5��#t" + makeitem + "##k��Ҫ���µĲ��ϣ�����Ǵ��Թ���ͻ�õ��ġ�������Ŭ����ȥ������ô�ѵõ�����ô��? ����������?\r\n" + menu);
    } else if (status == 4) {
        for (i = 0; i < reqitem.length; i++) {
            if (!cm.haveItem(reqitem[i][0], reqitem[i][1]))
                access = false;
        }
        if (access == false || !cm.canHold(makeitem) || cm.getMeso() < cost) {
            cm.sendNext("������ȷ�϶�����Ҫ�ĵ��߻򱳰�����������û�пռ䡣");
        } else {
            cm.sendOk("��ϲ���� 5 �� #b#t" + makeitem + "##k��");
            cm.gainItem(reqitem[0][0], -reqitem[0][1]);
            cm.gainItem(reqitem[1][0], -reqitem[1][1]);
            cm.gainMeso(-cost);
            cm.gainItem(makeitem, 5);
        }
        cm.�Ի�����();
    }
}
