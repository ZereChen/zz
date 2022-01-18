/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：擂台赛
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
        var selStr = "\t\t" + 心 + "  " + 心 + " #r#e < 擂台赛 > #k#n " + 心 + "  " + 心 + "\r\n";

        selStr += "\r\n";

        cm.说明文字(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.dispose();
                cm.openNpc(9900004, 1);
                break;
            case 2:
                cm.dispose();
                cm.openNpc(9900004, 2);
                break;
            case 3:
                cm.dispose();
                cm.openNpc(9900004, 3);
                break;
            case 4:
                cm.dispose();
                cm.openNpc(9900004, 4);
                break;
            case 5:
                cm.dispose();
                cm.openNpc(9900004, 5);
                break;
            case 6:
                cm.dispose();
                cm.openNpc(9900004, 6);
                break;
            case 7:
                cm.dispose();
                cm.openNpc(9900004, 7);
                break;
            case 8:
                cm.dispose();
                cm.openNpc(9900004, 8);
                break;
			case 9:
                cm.dispose();
                cm.openNpc(9900004, 9);
                break;
			case 10:
                cm.dispose();
                cm.openNpc(9900007, 0);
                break;
			case 11:
                cm.dispose();
                cm.openNpc(9900004, 11);
                break;
			case 12:
                cm.dispose();
                cm.openNpc(9900004, 12);
                break;
        }
    }
}