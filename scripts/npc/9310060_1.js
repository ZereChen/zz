/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：星缘签到，自行修改
 */
var JT = "#fUI/Basic/BtHide3/mouseOver/0#";
var 心 = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.对话结束();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    var 签到 = cm.getBossRank("每日签到", 2);
    if (签到 < 0) {
        签到 = 0;
    }
    if (status == 0) {
        var selStr = "	  Hi~#b#h ##k我是星缘，你今天签到了吗？你已经签到了 #r" + 签到 + "#k 天，根据你的签到天数，可以找我领取奖励哦。\r\n";

        if (cm.getBossLog("每日签到") < 1) {
            selStr += " #L0##b我要签到#k#l\r\n";
        }
        if (cm.getBossRank("3天签到奖励", 2) <= 0) {
            selStr += " #L1##b3天签到奖励，"+cm.显示物品(2000004)+" x 50 #k#l\r\n";
        }
        if (cm.getBossRank("5天签到奖励", 2) <= 0) {
            selStr += " #L2##b5天签到奖励，"+cm.显示物品(2049000)+" x 5 #k#l\r\n";
        }
        if (cm.getBossRank("7天签到奖励", 2) <= 0) {
            selStr += " #L3##b7天签到奖励，#i4031138# x 500000 #k#l\r\n";
        }
        if (cm.getBossRank("10天签到奖励", 2) <= 0) {
            selStr += " #L4##b10天签到奖励，"+cm.显示物品(5531000)+" x 1#k#l\r\n";
        }
        if (cm.getBossRank("15天签到奖励", 2) <= 0) {
            selStr += " #L5##b15天签到奖励，"+cm.显示物品(5531000)+" x 2#k#l\r\n";
        }
        if (cm.getBossRank("30天签到奖励", 2) <= 0) {
            selStr += " #L6##b30天签到奖励，"+cm.显示物品(5531000)+" x 8#k#l\r\n";
        }
        cm.说明文字(selStr);
    } else if (status == 1) {
        switch (selection) {
            //30天签到奖励
            case 6:
                if (签到 >= 30) {
                    cm.给经验(60000000);
                    cm.setBossRankCount("30天签到奖励", 1);
                    cm.说明文字("恭喜你领取 30 天签到奖励成功。");
                } else {
                    cm.说明文字("签到天数不够。");
                }
                cm.对话结束();
                break;
                //15天签到奖励
            case 5:
                if (签到 >= 30) {
                    cm.给经验(6000000);
                    cm.setBossRankCount("15天签到奖励", 1);
                    cm.说明文字("恭喜你领取 15 天签到奖励成功。");
                } else {
                    cm.说明文字("签到天数不够。");
                }
                cm.对话结束();
                break;
                //10天签到奖励
            case 4:
                if (签到 >= 30) {
                    cm.给经验(1500000);
                    cm.setBossRankCount("10天签到奖励", 1);
                    cm.说明文字("恭喜你领取 10 天签到奖励成功。");
                } else {
                    cm.说明文字("签到天数不够。");
                }
                cm.对话结束();
                break;
                //7天签到奖励
            case 3:
                if (签到 >= 30) {
                    cm.给经验(500000);
					cm.给金币(50*10000);
                    cm.setBossRankCount("7天签到奖励", 1);
                    cm.说明文字("恭喜你领取 7 天签到奖励成功。");
                } else {
                    cm.说明文字("签到天数不够。");
                }
                cm.对话结束();
                break;
                //5天签到奖励
            case 2:
                if (签到 >= 30) {
                    cm.给经验(300000);
					cm.给物品(2049000,5);
                    cm.setBossRankCount("5天签到奖励", 1);
                    cm.说明文字("恭喜你领取 5 天签到奖励成功。");
                } else {
                    cm.说明文字("签到天数不够。");
                }
                cm.对话结束();
                break;
                //3天签到奖励
            case 1:
                if (签到 >= 30) {
                    cm.给经验(100000);
					cm.给物品(2000004,50);
                    cm.setBossRankCount("3天签到奖励", 1);
                    cm.说明文字("恭喜你领取 3 天签到奖励成功。");
                } else {
                    cm.说明文字("签到天数不够。");
                }
                cm.对话结束();
                break;
                //每日签到
            case 0:
                cm.setBossRankCount("每日签到", 1);
                cm.setBossLog("每日签到");
                cm.说明文字("恭喜你签到成功。");
                cm.对话结束();
                break;
        }
    }
}