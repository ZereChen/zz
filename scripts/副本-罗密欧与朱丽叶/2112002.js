/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：罗密欧与朱丽叶
 */

var 通关经验 = 100000;


var status = -1;
function action(mode, type, selection) {
    if (cm.getMapId() == 926100600) {
		//罗密欧与朱丽叶盛产邮票，恩
		cm.概率给物品2(4030002, 2, 30, "俄罗斯方块");
		cm.概率给物品2(4030003, 2, 30, "俄罗斯方块");
		cm.概率给物品2(4030004, 2, 30, "俄罗斯方块");
		cm.概率给物品2(4030005, 2, 30, "俄罗斯方块");
		cm.概率给物品2(4030006, 2, 30, "俄罗斯方块");
		cm.概率给物品2(4030007, 2, 30, "俄罗斯方块");
		cm.概率给物品2(4030008, 2, 30, "俄罗斯方块");
        //记录通关信息
		cm.getPlayer().endPartyQuest(1205);
		cm.removeAll(4001130);
        cm.removeAll(4001131);
        cm.removeAll(4001132);
        cm.removeAll(4001133);
        cm.removeAll(4001134);
        cm.removeAll(4001135);
        cm.给经验(通关经验);
        cm.setBossRankCount("罗密欧与朱丽叶", 1);
        cm.setBossLog("罗密欧与朱丽叶");
        cm.worldMessage(2, "[副本-罗密欧与朱丽叶] : 恭喜 " + cm.getPlayer().getName() + " 完成罗密欧与朱丽叶副本。");
        cm.warp(926100700, 0);
        cm.dispose();
        return;
    }
    if (mode > 0) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
        cm.removeAll(4001130);
        cm.removeAll(4001131);
        cm.removeAll(4001132);
        cm.removeAll(4001133);
        cm.removeAll(4001134);
        cm.removeAll(4001135);
        cm.sendSimple("\r\n#b#L0#完成副本，让我离开这里#l");
    } else {
        if (selection == 0) {
            cm.warp(926100600, 0);
        }
        cm.dispose();
    }
}