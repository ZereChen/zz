function start() {
    cm.sendYesNo("����Ҫ�뿪�ˣ���");
}

function action(mode, type, selection) {
    if (mode == 1) {
	if (cm.getMapId() == 674030200 || cm.getMapId() == 674030000) {
		cm.warp(674030100,0);
		cm.dispose();
	} else {
		cm.dispose();
		cm.��NPC(2007,9);
		/*var map = cm.getSavedLocation("CHRISTMAS");
		if (map > -1 && map != cm.getMapId()) {
			cm.warp(map, 0);
		} else {
    			cm.warp(100000000, 0);
		}
		}*/
	}
    }
}