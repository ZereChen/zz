/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：冒险百科
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
		var selStr = "\t\t\t" + 心 + "  " + 心 + " #r#e < 冒险百科 > #k#n " + 心 + "  " + 心 + "\r\n\r\n";
	    selStr += " #L0##b返回#k#l\r\n";
        selStr += " #L1##b我要升级#k#l\r\n";
		selStr += " #L2##b我要赚钱#k#l\r\n";
		selStr += " #L3##b我要变强#k#l\r\n";
		selStr += " #L4##b关于每日任务#k#l\r\n";
		selStr += " #L5##b关于钓鱼#k#l\r\n";
		selStr += " #L6##b关于道具制造者#k#l\r\n";
		selStr += " #L7##b关于魔鬼入口#k#l\r\n";
		selStr += " #L8##b关于签到，在线奖励#k#l\r\n";
		selStr += " #L9##b关于副本奖励#k#l\r\n";
		selStr += " #L10##b关于怪物结晶体#k#l\r\n";
        cm.说明文字(selStr);
    } else if (status == 1) {
        switch (selection) {
			//怪物结晶体
            case 10:
                cm.dispose();
                cm.openNpc(9900007, 10);
                break;
			//关于副本
            case 9:
                cm.dispose();
                cm.openNpc(9900007, 9);
                break;
			//关于签到，在线奖励
            case 8:
                cm.dispose();
                cm.openNpc(9900007, 8);
                break;
			//关于魔鬼入口
            case 7:
                cm.dispose();
                cm.openNpc(9900007, 7);
                break;
			//关于道具制造者
            case 6:
                cm.dispose();
                cm.openNpc(9900007, 6);
                break;
			//关于钓鱼
            case 5:
                cm.dispose();
                cm.openNpc(9900007, 5);
                break;
			//每日任务
            case 4:
                cm.dispose();
                cm.openNpc(9900007, 4);
                break;
			//返回主界面
            case 0:
                cm.dispose();
                cm.openNpc(9900004, 0);
                break;
			//我要变强
            case 3:
                cm.dispose();
                cm.openNpc(9900007, 3);
                break;
			//我要赚钱
            case 2:
                cm.dispose();
                cm.openNpc(9900007, 2);
                break;
            //我要升级
            case 1:
                cm.dispose();
                cm.openNpc(9900007, 1);
                break;
        }
    }
}