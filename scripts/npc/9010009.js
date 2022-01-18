/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：送货
 */
var a = "#fUI/Basic/BtHide3/mouseOver/0#";
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
        var selStr = "  Hi~#b#h ##k 你是要送货吗？抱歉，暂时不提供服务。\r\n\r\n";
        cm.是否说明文字(selStr);
    } else if (status == 1) {
        var selStr = "送货注意事项：#b\r\n";
        selStr += "1.送货期间不可传送。\r\n";
        selStr += "2.送货不可下线，下线后则为放弃。\r\n";
        selStr += "3.送货不可更换频道。\r\n";
        cm.是否说明文字(selStr);
    } else if (status == 2) {
        var selStr = "正在开发···\r\n";
		selStr += "\r\n";


        cm.说明文字(selStr);
    } else if (status == 3) {
        switch (selection) {
            case 1:
                cm.dispose();
                break;
        }
    }
}