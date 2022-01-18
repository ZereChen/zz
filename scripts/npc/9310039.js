function start() {
        if (cm.haveItem(4032398, 1)) {
    cm.gainItem(4032398, -1);
    cm.warp(702060000, "sp");	
    cm.dispose();
	} else {
	    cm.sendOk("你没有出席图章，所以不能挑战妖僧。");
    cm.dispose();
}
}