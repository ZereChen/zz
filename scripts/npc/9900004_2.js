/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：传送
 */
 
 
var 魔法石 = 4006000;
var JT = "#fUI/Basic/BtHide3/mouseOver/0#";
var 心 = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
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
        var selStr = "  \t\t " + 心 + "  " + 心 + " #r#e < 空间传送 > #k#n " + 心 + "  " + 心 + "\r\n\r\n";
		selStr+="\t\t每次传送需要消耗:"+cm.显示物品(魔法石)+"\r\n\r\n";
        selStr += "\t\t\t\t  #L0##b返回界面#k#l\r\n";
		selStr += "\t\t\t\t  #L1##b城镇传送#k#l\r\n";
		selStr += "\t\t\t\t  #L2##b副本传送#k#l\r\n";
		selStr += "\t\t\t\t  #L3##b挑战传送#k#l\r\n";
        cm.说明文字(selStr);
    } else if (status == 1) {
        switch (selection) {
			case 0:
                cm.dispose();
                cm.openNpc(9900004, 0);
                break;
            case 1:
                cm.dispose();
                cm.openNpc(9900004, 20001);
                break;
            case 2:
                cm.dispose();
                cm.openNpc(9900004, 20002);
                break;
            case 3:
                cm.dispose();
                cm.openNpc(9900004, 20003);
                break;
        }
    }
}