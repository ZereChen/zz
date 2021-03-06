/*
 ZEVMS冒险岛(079)游戏服务端
 异界之门3转任务
 */
function start() {
    var nextmap1 = cm.getMapFactory().getMap(108010201);
    var nextmap2 = cm.getMapFactory().getMap(108010301);
    var nextmap3 = cm.getMapFactory().getMap(108010101);
    var nextmap4 = cm.getMapFactory().getMap(108010401);
    var nextmap5 = cm.getMapFactory().getMap(108010501);
    var nextmap11 = cm.getMapFactory().getMap(108010200);
    var nextmap22 = cm.getMapFactory().getMap(108010300);
    var nextmap33 = cm.getMapFactory().getMap(108010100);
    var nextmap44 = cm.getMapFactory().getMap(108010400);
    var nextmap55 = cm.getMapFactory().getMap(108010500);
    if (cm.getPlayer().getLevel() >= 70 && cm.getBossRank7("三转任务", 2) > 0) {
        if (cm.canHold(4031059)) {
            if (!(cm.haveItem(4031059))) {
                if (nextmap1.mobCount() > 0 && nextmap1.playerCount() == 0 && nextmap11.playerCount() == 0) {
                    nextmap1.killAllMonsters(true);
                    cm.对话结束();
                }
                if (nextmap2.mobCount() > 0 && nextmap2.playerCount() == 0 && nextmap22.playerCount() == 0) {
                    nextmap2.killAllMonsters(true);
                    cm.对话结束();
                }
                if (nextmap3.mobCount() > 0 && nextmap3.playerCount() == 0 && nextmap33.playerCount() == 0) {
                    nextmap3.killAllMonsters(true);
                    cm.对话结束();
                }
                if (nextmap4.mobCount() > 0 && nextmap4.playerCount() == 0 && nextmap44.playerCount() == 0) {
                    nextmap4.killAllMonsters(true);
                    cm.对话结束();
                }
                if (nextmap5.mobCount() > 0 && nextmap5.playerCount() == 0 && nextmap55.playerCount() == 0) {
                    nextmap5.killAllMonsters(true);
                    cm.对话结束();
                }
				//魔法师异界之门
                if (cm.判断地图() == 100040106 && nextmap11.playerCount() == 0 && nextmap1.playerCount() == 0 && cm.getJob() == 210 || cm.getJob() == 220 || cm.getJob() == 230) {
                    cm.warp(108010200, 0);
                    cm.spawnMobOnMap(9001001, 1, -276, -3, 108010201);
                    cm.对话结束();
					//战士异界之门
                } else if (cm.判断地图() == 105070001 && nextmap22.playerCount() == 0 && nextmap2.playerCount() == 0 && cm.getJob() == 110 || cm.getJob() == 120 || cm.getJob() == 130 || cm.getJob() == 2110) {
                    cm.warp(108010300, 0);
                    cm.spawnMobOnMap(9001000, 1, -276, -3, 108010301);
                    cm.对话结束();
					//弓箭手异界之门
                } else if (cm.判断地图() == 105040305 && nextmap33.playerCount() == 0 && nextmap3.playerCount() == 0 && cm.getJob() == 310 || cm.getJob() == 320) {
                    cm.warp(108010100, 0);
                    cm.spawnMobOnMap(9001002, 1, -276, -3, 108010101);
                    cm.对话结束();
					//飞侠异界之门
                } else if (cm.判断地图() == 107000402 && nextmap44.playerCount() == 0 && nextmap4.playerCount() == 0 && cm.getJob() == 410 || cm.getJob() == 420) {
                    cm.warp(108010400, 0);
                    cm.spawnMobOnMap(9001003, 1, -276, -3, 108010401);
                    cm.对话结束();
					//海盗异界之门
                } else if (cm.判断地图() == 105070200 && nextmap55.playerCount() == 0 && nextmap5.playerCount() == 0 && cm.getJob() == 510 || cm.getJob() == 520) {
                    cm.warp(108010500, 0);
                    cm.spawnMobOnMap(9001004, 1, -276, -3, 108010501);
                    cm.对话结束();
                } else {
                    cm.说明文字("里面已经有人在挑战了，或许这不是你该来的地方。");
                }
            } else {
                cm.说明文字("你貌似已经有了#t4031059#。");
            }
        } else {
            cm.说明文字("请确认是否有足够的空间。");
        }
    } else {
        cm.说明文字("你无法进入。");
    }
    cm.对话结束();
}