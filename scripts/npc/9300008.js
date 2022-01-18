/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：结婚殿堂
 */
var status = 0

function start(){
	action(1, 0, 0);
}

function action(mode, type ,selection){
	if(mode == 1) {
		status++;
	} else if(mode == 0) {
		status--;
	} else {
		cm.dispose();
		return;
	}
	if(status == 1){
		cm.sendYesNo("你是要回去吗？");
	} else if(status == 2){
		var map = cm.getSavedLocation("WEDDING");
		cm.warp(map, 0);
		cm.clearSavedLocation("WEDDING");
		cm.dispose();	
	} else {
		cm.dispose();
	}
}