/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：蜈蚣
 */
var 最低等级 = 20;
var 最高等级 = 255;
var 每日次数 = 10;

var status = 0;

function start() {
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.对话结束();
        return;
    }
    if (status == 1) {
        cm.sendYesNo("\r\n想要进去挑战蜈蚣？\r\n等级要求:#b" + 最低等级 + " - " + 最高等级 + "#k\r\n每日次数:#b" + 每日次数 + "#k\r\n总计完成: #r" + cm.getBossRank("蜈蚣", 2) + "\r\n#k今日完成: #r" + cm.getBossLog("蜈蚣") + "\r\n\r\n");
    } else if (status == 2) {
        if (cm.getParty() == null) {
            cm.sendOk("请组队再来找我。");
        } else if (!cm.isLeader()) {
            cm.sendOk("请叫你的队长来找我。");
        } else if (cm.判断等级() > 最高等级 || cm.判断等级() < 最低等级) {
        } else if (cm.getBossLog("蜈蚣") <= 每日次数) {
            var em = cm.getEventManager("WuGongPQ");
			cm.setBossLog("蜈蚣")
            if (em == null) {
                cm.说明文字("当前副本有问题，请联络管理员....");
            } else {
                var prop = em.getProperty("state");
                if (prop.equals("0") || prop == null) {
                    em.startInstance(cm.getParty(), cm.getMap());
                    cm.对话结束();
                    return;
                } else {
                    cm.说明文字("里面已经有人在挑战了。你需要等待等待");
                }
            }
        } else {
            cm.说明文字("你今日已经不能挑战了！");
        }
        cm.对话结束();
    }
}