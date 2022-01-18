/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：拍卖行
 */
var 心 = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
var 箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
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
	 var MC = cm.getServerName();
	if (status == 0) {		
	  
	    var selStr = "\t\t\t" + 心 + "  " + 心 + " #r#e <  拍卖行  > #k#n " + 心 + "  " + 心 + "\r\n\r\n";
		selStr += "            #r#e提示#n:请选择你要进入的拍卖行类型。#k\r\n";
		selStr += "            #r  已经附魔的装备请勿放入拍卖。#k\r\n";
		selStr += "\t\t\t\t   #L0#"+箭头+"#b返回页面#l#n\r\n";
		selStr += "\t\t\t\t   #L1#"+箭头+"#b金币拍卖行#l#n\r\n";
		selStr += "\t\t\t\t   #L2#"+箭头+"#b点券拍卖行#l#n\r\n";
		
		cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
		case 0:
            cm.dispose(); 
			cm.openNpc(9900004,0);
            break;	
		case 1:
            cm.dispose(); 
			cm.openNpc(9900004,31);
            break;
		case 2:
            cm.dispose(); 
			cm.openNpc(9900004,32);
            break;
			 
		}
    }
}