/*
 ZEVMS冒险岛(079)游戏服务端
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
        var selStr = "  Hi~#b#h ##k你找我有什么事情吗？幸福村那边的人们过着幸福的生活，那里白雪皑皑的，你想要自己去看看嘛？\r\n\r\n";
        selStr += " #L1##b我要去幸福村#k#l\r\n";
		

        cm.说明文字(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.dispose();
				cm.打开NPC(2007,8);
                break;
        }
    }
}