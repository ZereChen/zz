/*
 ZEVMS冒险岛(079)游戏服务端
 */
//素材引用
var JT = "#fUI/Basic/BtHide3/mouseOver/0#";
var 心 = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
var r = "#fUI/UIWindow.img/Quest/TimeQuest/AlarmClock/default/0/0#";

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
	//开始
    if (status == 0) {
		
        var selStr = "	Hi~#b#h ##k我是"+cm.开服名称()+"的运营员，你找我做什么呢？你是要举报吗？哈哈哈。\r\n\r\n";
		
		selStr += "#L1##b兑换精灵项链#k#l\r\n";
		selStr += "#L2##b收集出席图章#k#l\r\n";
		selStr += "#L4##b炽热中国心#k#l\r\n";
		selStr += "#L3##b爱老虎哟#k#l\r\n";
        cm.说明文字(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.dispose();
                cm.openNpc(9010000, 1);
                break;
			case 2:
                cm.dispose();
                cm.openNpc(9010000, 2);
                break;
			case 3:
                cm.dispose();
                cm.openNpc(9010000, 3);
                break;
			case 4:
                cm.dispose();
                cm.openNpc(9010000, 4);
                break;
        }
    }
}