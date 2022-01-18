/*
 ZEVMS冒险岛(079)游戏服务端
 */
 
var 兑换物品 = 4031250;


importPackage(Packages.client);
importPackage(Packages.client.inventory);
var status = -1;
var beauty = 0;
var tosend = 0;
var sl;
var mats;
function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.对话结束();
    } else {
        if (mode == 0 && status == 0) {
            cm.对话结束();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            if (status == 0) {
                cm.sendNext("如果需要点卷中介服务在来找我吧。");
                cm.对话结束();
            }
            status--;
        }
        if (status == 0) {
            var e = "";
            e += "  兑换物品：" + cm.显示物品(兑换物品) + "\r\n";
            e += "  兑换比例:#r1:1#k\r\n";
            e += "  当前点卷:#r" + cm.getPlayer().getCSPoints(1) + "#k\r\n\r\n";
            e += "#L0##b我要兑换#l\r\n\r\n";
            cm.sendSimple(e);
        } else if (status == 1) {
            if (selection == 0) {
                    beauty = 1;
                    cm.sendGetNumber("请输入你要兑换的点券数量；\r\n", 1, 1, 10000);
            } 
        } else if (status == 2) {
            if (beauty == 1) {
                if (selection <= 0) {
                    cm.说明文字("输入的兑换数字错误。");
                    cm.对话结束();
                } else if (cm.判断物品数量(兑换物品,selection)) {
                    cm.给点券(selection);
                    cm.收物品(兑换物品, selection);
                    cm.说明文字("您成功用 " + cm.显示物品(兑换物品) + " x #b"+selection+"#k 兑换 #r" + selection + "#k 点券。")
                } else {
                    cm.sendNext("您没有足够兑换物品。");
                    cm.对话结束();
                }
            } 
            status = -1;
        } else {
            cm.对话结束();
        }
    }
}