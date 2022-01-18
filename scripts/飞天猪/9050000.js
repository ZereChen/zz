/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：飞天猪-魔法密林
 对应的蛋：4170001
 */
var 城镇 = "魔法密林";
var status = 0;
//物品ID，抽奖概率，物品数量，(0/不广播，1广播)
var itemList =
        Array(
                Array(2000000, 1000, 1, 1),
                Array(2000001, 1000, 1, 1),
                Array(2000002, 1000, 1, 1),
                Array(2000003, 1000, 1, 1),
				Array(2000004, 300, 1, 1),
                Array(2000005, 100, 1, 1),
				Array(4170001, 100, 1, 1)//蛋蛋
                );

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.sendOk("你没有 " + cm.显示物品(5220040) + " ？");
            cm.dispose();
        }
        status--;
    }
    if (status == 0) {
        if (cm.haveItem(5220040, 1)) {
            var str1 = "";
            for (var i = 0; i < itemList.length; i++) {
                str1 += "#v" + itemList[i][0] + "#";
            }
            cm.sendYesNo("我是#b"+城镇+"#k的飞天猪，你好 #b#h ##k 给我 " + cm.显示物品(5220040) + " 就可以抽奖了。");
        } else {
            var str1 = "\r\n";
            for (var i = 0; i < itemList.length; i++) {
                str1 += "#v" + itemList[i][0] + "#";
            }
            cm.sendOk("我是#b"+城镇+"#k的飞天猪，你没有 " + cm.显示物品(5220040) + " ! 等你有了在来找我把。");
            cm.safeDispose();
        }
    } else if (status == 1) {
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
            item = cm.gainGachaponItem(itemId, quantity, "飞天猪", notice);
            if (item != -1) {
                cm.gainItem(5220040, -1);
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