/*
 ZEVMSð�յ�(079)��Ϸ�����
 ��������
 */
var JT = "#fUI/Basic/BtHide3/mouseOver/0#";
var �� = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
        var selStr = "Hi #b#h ##k������ A �����������ߣ����ó������ߵȼ�����������û��ʲô��Ҫ�������أ�\r\n\r\n";
		selStr += "#b#L1#30����Ҷ��#l#k\r\n";
		selStr += "#b#L3#35����Ҷ����#l#k\r\n";
		selStr += "#b#L2#43����Ҷ����#l#k\r\n";
		selStr += "#b#L4#64����Ҷ����#l#k\r\n";
		selStr += "#b#L5#20����Ҷ����#l#k\r\n";
		selStr += "#b#L8#20����Ҷ����#l#k\r\n";
		selStr += "#b#L6#30����ҶΧ��#l#k\r\n";
		selStr += "#b#L7#��ҶС��Ʒ#l#k\r\n";
        cm.˵������(selStr);
    } else if (status == 1) {
        switch (selection) {
			case 8:
                cm.dispose();
                cm.openNpc(1012002, 8);
                break;
			case 7:
                cm.dispose();
                cm.openNpc(1012002, 7);
                break;
			case 6:
                cm.dispose();
                cm.openNpc(1012002, 6);
                break;
			case 5:
                cm.dispose();
                cm.openNpc(1012002, 5);
                break;
			case 4:
                cm.dispose();
                cm.openNpc(1012002, 4);
                break;
			case 3:
                cm.dispose();
                cm.openNpc(1012002, 3);
                break;
            case 2:
                cm.dispose();
                cm.openNpc(1012002, 2);
                break;
			case 1:
                cm.dispose();
                cm.openNpc(1012002, 1);
                break;

        }
    }
}
