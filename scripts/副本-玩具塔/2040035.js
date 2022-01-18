/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：玩具塔奖励领取
 */



var status;


function start() {
    status = -1;
    action(1, 0, 0);
}
//副本通关奖励区域
/*
cm.概率给物品(物品代码，固定数量，概率,备注)；
cm.概率给物品2(物品代码，随机数量，概率,备注)；
*/
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    if (mode == 1)
        status++;
    else
        status--;
    if (status == 0) {
        cm.sendNext("请确认你的道具栏有没有满,满了领不到东西喔。");
    } else if (status == 1) {
		//玩具盛产金属，恩
		cm.概率给物品2(4010000,2,30,"青铜母矿");
		cm.概率给物品2(4010001,2,30,"钢铁母矿");
		cm.概率给物品2(4010002,2,30,"锂矿石母矿");
		cm.概率给物品2(4010003,2,30,"朱矿石母矿");
		cm.概率给物品2(4010004,2,30,"银的母矿");
		cm.概率给物品2(4010005,2,30,"紫矿石母矿");
		cm.概率给物品2(4010006,2,30,"黄金母矿");
		cm.概率给物品2(4010007,2,30,"锂母矿");
		
		
		//记录通关信息
		cm.getPlayer().endPartyQuest(1202);
	    cm.setBossRankCount("玩具塔",1);
	    cm.setBossLog("玩具塔");
        cm.worldMessage(2, "[副本-玩具塔] : 恭喜 " + cm.getPlayer().getName() + " 完成玩具塔副本。");
        cm.warp(221024500);
        cm.dispose();
    }
}
