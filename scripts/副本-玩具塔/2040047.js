/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：玩具塔离开
 */

function start() {
    if (cm.getMapId() != 922010000) {
	cm.sendYesNo("你真的要离开#r组队任务了#k？");
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