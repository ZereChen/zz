/*
 ZEVMS冒险岛(079)游戏服务端
 */
var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.dispose();
        }
        status--;
    }var MC = cm.getServerName();

    if (status == 0) {
        var selStr = "\r\n\r\n#b";
        var iz = cm.getMap().getAllUniqueMonsters().iterator();
        while (iz.hasNext()) {
            var zz = iz.next();
            selStr += "#L" + zz + "##o" + zz + "##l\r\n";
	
        }
        cm.sendSimple(selStr);
    } else if (status == 1) {
        cm.sendNext(cm.checkDrop(selection));
		//cm.gainItem(2022524,-1);
        cm.dispose();
    }
}