/*
 ZEVMSð�յ�(079)��Ϸ�����
 */

var status = 0;

function start() {
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == -1) {
		cm.dispose();
	} else {
		if (mode == 1)
			status++;
		else
			status--;
		if (status == 0) {
			cm.forceStartQuest(22011);
			cm.playerMessage("���Ѿ������������");
			cm.warp(900090103);
			cm.dispose();
		}
	}
}