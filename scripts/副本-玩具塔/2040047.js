/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű���������뿪
 */

function start() {
    if (cm.getMapId() != 922010000) {
	cm.sendYesNo("�����Ҫ�뿪#r���������#k��");
    } else {
	if (cm.haveItem(4001022)) {
	    cm.removeAll(4001022);
	}
	if (cm.haveItem(4001023)) {
	    cm.removeAll(4001023);
	}
	cm.warp(221024500, 0);
	cm.dispose();
    }
}

function action(mode, type, selection) {
    if (mode == 1) {
	cm.warp(922010000, 0);
    }
    cm.dispose();
}