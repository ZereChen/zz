/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：星缘，个人在线奖励，自行修改
 */
var 箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
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
    var 在线奖励1时间 = 60;
    var 在线奖励1代码 = 4000313;
    var 在线奖励1数量 = 5;

    var 在线奖励2时间 = 120;
    var 在线奖励2代码 = 4007000;
    var 在线奖励2数量 = 5;

    var 在线奖励3时间 = 180;
    var 在线奖励3代码 = 2001000;
    var 在线奖励3数量 = 50;

    var 在线奖励4时间 = 240;
    var 在线奖励4代码 = 4006000;
    var 在线奖励4数量 = 3;

    var 在线奖励5时间 = 300;
    var 在线奖励5代码 = 4006001;
    var 在线奖励5数量 = 3;

    var 在线奖励6时间 = 360;
    var 在线奖励6代码 = 2049000;
    var 在线奖励6数量 = 1;

    var 在线奖励7时间 = 420;
    var 在线奖励7代码 = 2340000;
    var 在线奖励7数量 = 1;

    var 在线奖励8时间 = 480;
    var 在线奖励8代码 = 4001005;
    var 在线奖励8数量 = 1;
    if (status == 0) {
        var selStr = "	  Hi~#b#h ##k 你今日在线:#b #n#e" + cm.查询今日在线时间() + " #n#k分钟#k，你是不是想要找我领取奖品呢。\r\n";

        selStr += "在线 #r" + 在线奖励1时间 + " #k分钟可领取 #v" + 在线奖励1代码 + " ##z" + 在线奖励1代码 + "#  x  " + 在线奖励1数量 + "\r\n";
        if (cm.查询今日在线时间() >= 在线奖励1时间 && cm.getBossLog("在线奖励1时间") == 0) {
            selStr += "#L1#" + 箭头 + "#b领取#r" + 在线奖励1时间 + "#k#b分钟奖励#l#k\r\n";
        }

        selStr += "在线 #r" + 在线奖励2时间 + " #k分钟可领取 #v" + 在线奖励2代码 + "##z" + 在线奖励2代码 + "#  x  " + 在线奖励2数量 + "\r\n";
        if (cm.查询今日在线时间() >= 在线奖励2时间 && cm.getBossLog("在线奖励2时间") == 0) {
            selStr += "#L2#" + 箭头 + "#b领取#r" + 在线奖励2时间 + "#k#b分钟奖励#l#k\r\n";
        }

        selStr += "在线 #r" + 在线奖励3时间 + " #k分钟可领取 #v" + 在线奖励3代码 + "##z" + 在线奖励3代码 + "#  x  " + 在线奖励3数量 + "\r\n";
        if (cm.查询今日在线时间() >= 在线奖励3时间 && cm.getBossLog("在线奖励3时间") == 0) {
            selStr += "#L3#" + 箭头 + "#b领取#r" + 在线奖励3时间 + "#k#b分钟奖励#l#k\r\n";
        }

        selStr += "在线 #r" + 在线奖励4时间 + " #k分钟可领取 #v" + 在线奖励4代码 + "##z" + 在线奖励4代码 + "#  x  " + 在线奖励4数量 + "\r\n";
        if (cm.查询今日在线时间() >= 在线奖励4时间 && cm.getBossLog("在线奖励4时间") == 0) {
            selStr += "#L4#" + 箭头 + "#b领取#r" + 在线奖励4时间 + "#k#b分钟奖励#l#k\r\n";
        }

        selStr += "在线 #r" + 在线奖励5时间 + " #k分钟可领取 #v" + 在线奖励5代码 + "##z" + 在线奖励5代码 + "#  x  " + 在线奖励5数量 + "\r\n";
        if (cm.查询今日在线时间() >= 在线奖励5时间 && cm.getBossLog("在线奖励5时间") == 0) {
            selStr += "#L5#" + 箭头 + "#b领取#r" + 在线奖励5时间 + "#k#b分钟奖励#l#k\r\n";
        }

        selStr += "在线 #r" + 在线奖励6时间 + " #k分钟可领取 #v" + 在线奖励6代码 + "##z" + 在线奖励6代码 + "#  x  " + 在线奖励6数量 + "\r\n";
        if (cm.查询今日在线时间() >= 在线奖励6时间 && cm.getBossLog("在线奖励6时间") == 0) {
            selStr += "#L6#" + 箭头 + "#b领取#r" + 在线奖励6时间 + "#k#b分钟奖励#l#k\r\n";
        }

        selStr += "在线 #r" + 在线奖励7时间 + " #k分钟可领取 #v" + 在线奖励7代码 + "##z" + 在线奖励7代码 + "#  x  " + 在线奖励7数量 + "\r\n";
        if (cm.查询今日在线时间() >= 在线奖励7时间 && cm.getBossLog("在线奖励7时间") == 0) {
            selStr += "#L7#" + 箭头 + "#b领取#r" + 在线奖励7时间 + "#k#b分钟奖励#l#k\r\n";
        }

        selStr += "在线 #r" + 在线奖励8时间 + " #k分钟可领取 #v" + 在线奖励8代码 + "##z" + 在线奖励8代码 + "#  x  " + 在线奖励8数量 + "\r\n";
        if (cm.查询今日在线时间() >= 在线奖励8时间 && cm.getBossLog("在线奖励8时间") == 0) {
            selStr += "#L8#" + 箭头 + "#b领取#r" + 在线奖励8时间 + "#k#b分钟奖励#l#k\r\n";
        }


        cm.说明文字(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.gainItem(在线奖励1代码, 在线奖励1数量);
                cm.setBossLog("在线奖励1时间");
                cm.worldMessage(6, "[在线奖励快讯]:" + cm.getChar().getName() + " 领取了 " + 在线奖励1时间 + " 分钟在线奖励");

                cm.对话结束();
                break;
            case 2:
                cm.gainItem(在线奖励2代码, 在线奖励2数量);
                cm.setBossLog("在线奖励2时间");
                cm.worldMessage(6, "[在线奖励快讯]:" + cm.getChar().getName() + " 领取了 " + 在线奖励2时间 + " 分钟在线奖励");
                cm.对话结束();
                break;
            case 3:
                cm.gainItem(在线奖励3代码, 在线奖励3数量);
                cm.setBossLog("在线奖励3时间");
                cm.worldMessage(6, "[在线奖励快讯]:" + cm.getChar().getName() + " 领取了 " + 在线奖励3时间 + " 分钟在线奖励");
                cm.对话结束();
                break;
            case 4:
                cm.gainItem(在线奖励4代码, 在线奖励4数量);
                cm.setBossLog("在线奖励4时间");
                cm.worldMessage(6, "[在线奖励快讯]:" + cm.getChar().getName() + " 领取了 " + 在线奖励4时间 + " 分钟在线奖励");
                cm.对话结束();
                break;
            case 5:
                cm.gainItem(在线奖励5代码, 在线奖励5数量);
                cm.setBossLog("在线奖励5时间");
                cm.worldMessage(6, "[在线奖励快讯]:" + cm.getChar().getName() + " 领取了 " + 在线奖励5时间 + " 分钟在线奖励");
                cm.对话结束();
                break;
            case 6:
                cm.gainItem(在线奖励6代码, 在线奖励6数量);
                cm.setBossLog("在线奖励6时间");
                cm.worldMessage(6, "[在线奖励快讯]:" + cm.getChar().getName() + " 领取了 " + 在线奖励6时间 + " 分钟在线奖励");
                cm.对话结束();
                break;
            case 7:
                cm.gainItem(在线奖励7代码, 在线奖励7数量);
                cm.setBossLog("在线奖励7时间");
                cm.worldMessage(6, "[在线奖励快讯]:" + cm.getChar().getName() + " 领取了 " + 在线奖励7时间 + " 分钟在线奖励");
                cm.对话结束();
                break;
            case 8:
                cm.gainItem(在线奖励8代码, 在线奖励8数量);
                cm.setBossLog("在线奖励8时间");
                cm.worldMessage(6, "[在线奖励快讯]:" + cm.getChar().getName() + " 领取了 " + 在线奖励8时间 + " 分钟在线奖励");
                cm.对话结束();
                break;
        }
    }
}