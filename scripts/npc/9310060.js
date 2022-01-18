/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：星缘
 */
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
        var selStr = "	  Hi~#b#h ##k我是星缘，你找我有什么事情吗？在这冒险世界里，还有多少不为人知的秘密，多有不为人晓的黑暗。\r\n";
		
		selStr += " #L2##b每日签到#k#l\r\n";
		selStr += " #L3##b在线奖励(个人)#k#l\r\n";
		selStr += " #L4##b在线奖励(全服)#k#l\r\n";
		selStr += " #L5##b在线奖励(家族)#k#l\r\n";
        selStr += " #L1##r[鬼屋]#b去闹鬼的宅邸#k#l\r\n";
		selStr += " #L6##r[每日]#b冒险收集#k#l\r\n";
	

        cm.说明文字(selStr);
    } else if (status == 1) {
        switch (selection) {
			case 6:
                cm.dispose();
				cm.打开NPC(9310060,5);
                break;
			case 5:
                cm.dispose();
				cm.打开NPC(9310060,4);
                break;
			case 4:
                cm.dispose();
				cm.打开NPC(9310060,3);
                break;
			case 3:
                cm.dispose();
				cm.打开NPC(9310060,2);
                break;
			case 2:
                cm.dispose();
				cm.打开NPC(9310060,1);
                break;
            case 1:
                cm.dispose();
				cm.打开NPC(2007,6);
                break;
        }
    }
}