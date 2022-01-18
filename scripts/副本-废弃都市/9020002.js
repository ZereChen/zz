/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：废弃都市奖励
 */
importPackage(java.awt);
importPackage(Packages.tools);
importPackage(Packages.server);
importPackage(Packages.handling.world);
var status;

function start() {
    status = -1;
    action(1, 0, 0);
}
var time = 0;
function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.dispose();
        return;
    } else {
        if (mode == 1)
            status++;
        else
            status--;
        var mapId = cm.getMapId();
        if (mapId == 103000890) {
            cm.warp(103000000, "mid00");
            cm.removeAll(4001007);
            cm.removeAll(4001008);
            cm.dispose();
        } else {
            var outText;
            if (mapId == 103000805) {
                outText = "你确定要离开地图？？";
                time = 1;
            } else {
                outText = "一旦你离开地图，你将不得不重新启动整个任务，如果你想再次尝试。你还是要离开这个地图？";
                time = 0;
            }
            if (status == 0) {
                cm.sendYesNo(outText);
            } else if (mode == 1) {
                if (time == 1) {
                    getPrize();
                }
				cm.warp(103000890, "st00");
                cm.dispose();
            }
        }
    }
}
//副本通关奖励区域
/*
cm.概率给物品(物品代码，固定数量，概率,备注)；
cm.概率给物品2(物品代码，随机数量，概率,备注)；
*/
function getPrize() {
	//废弃盛产母矿，恩。
	cm.概率给物品2(4004000,2,30,"力量母矿");
	cm.概率给物品2(4004001,2,30,"智慧母矿");
	cm.概率给物品2(4004002,2,30,"敏捷母矿");
	cm.概率给物品2(4004003,2,30,"幸运母矿");
	cm.概率给物品2(4004004,2,1,"黑暗水晶母矿");
	cm.概率给物品2(4020000,2,10,"石榴石母矿");
	cm.概率给物品2(4020001,2,10,"紫水晶母矿");
	cm.概率给物品2(4020002,2,10,"海蓝石母矿");
	cm.概率给物品2(4020003,2,10,"祖母绿母矿");
	cm.概率给物品2(4020004,2,10,"蛋白石母矿");
	cm.概率给物品2(4020005,2,10,"蓝宝石母矿");
	cm.概率给物品2(4020006,2,10,"黄晶母矿");
	cm.概率给物品2(4020007,2,10,"钻石母矿");
	cm.概率给物品2(4020008,2,10,"黑水晶母矿");
	//记录通关信息
	cm.getPlayer().endPartyQuest(1201);
	cm.setBossRankCount("废弃都市",1);
	cm.setBossLog("废弃都市");
    cm.worldMessage(2, "[副本-废弃都市] : 恭喜 " + cm.getPlayer().getName() + " 带领队伍，完成废弃都市副本。");
}
























