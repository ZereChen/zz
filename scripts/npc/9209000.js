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
		
        var selStr = "	Hi~#b#h ##k我是中介商人，你有人什么东西需要找我兑换吗？你有吗？我可不收破烂。\r\n\r\n";
		
		selStr += "#L1##b兑换点券#k#l\r\n";

        cm.说明文字(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.dispose();
                cm.openNpc(9209000, 1);
                break;
        }
    }
}






















