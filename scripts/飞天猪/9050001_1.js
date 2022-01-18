/*
 ZEVMS冒险岛(079)游戏服务端
 对应的蛋：4170000
 */
var 蛋 = 4170000;
var status = 0;
//物品ID，抽奖概率，物品数量，(0/不广播，1广播)
var itemList =
        Array(
                Array(2000000, 1000, 1, 1),
                Array(2000001, 1000, 1, 1),
                Array(2000002, 1000, 1, 1),
                Array(2000003, 1000, 1, 1),
				Array(2000004, 300, 1, 1),
                Array(2000005, 100, 1, 1)
				
                );

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
        var chance = Math.floor(Math.random() * +100);
        var finalitem = Array();
        for (var i = 0; i < itemList.length; i++) {
            if (itemList[i][1] >= chance) {
                finalitem.push(itemList[i]);
            }
        }
        if (finalitem.length != 0) {
            if (finalitem.length == 0) {
                return 1;
            }
            var item;
            var random = new java.util.Random();
            var finalchance = random.nextInt(finalitem.length);
            var itemId = finalitem[finalchance][0];
            var quantity = finalitem[finalchance][2];
            var notice = finalitem[finalchance][3];
            item = cm.gainGachaponItem(itemId, quantity, "飞天猪的蛋", notice);
            if (item != -1) {
                cm.gainItem(蛋, -1);
                cm.sendOk("你获得了 " + cm.显示物品(item) + " x #b"+quantity+"#k");
            } else {
                cm.sendOk("请你确认在背包的装备，消耗，其他窗口中是否有一格以上的空间。");
            }
            cm.safeDispose();
        } else {
            cm.sendOk("今天的运气可真差，什么都没有拿到。");
            cm.safeDispose();
        }
        cm.safeDispose();
    }
}