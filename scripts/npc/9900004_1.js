/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：个人信息
 */

var jt = "#fUI/Basic/BtHide3/mouseOver/0#";
var 心 = "#fUI/GuildMark.img/Mark/Etc/00009001/13#";
var 心1 = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
var 心2 = "#fUI/GuildMark.img/Mark/Etc/00009001/15#";
var sl0items = null;
var character_auctionItems = null;
var select_type_sell_auctionItems = null;
var auctionPoint = null;
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    var MC = cm.getServerName();
    var 角色 = cm.getPlayer().id;
    var HP = cm.getPlayer().getMaxHp() / 100;
    var MP = cm.getPlayer().getMaxMp() / 100;
    var 力量 = cm.getPlayer().getStat().getTotalStr() * 2;
    var 敏捷 = cm.getPlayer().getStat().getTotalDex() * 2;
    var 智力 = cm.getPlayer().getStat().getTotalInt() * 2;
    var 幸运 = cm.getPlayer().getStat().getTotalLuk() * 2;
    var 物理攻击力 = cm.getPlayer().getStat().getTotalWatk() * 10;
    var 魔法攻击力 = cm.getPlayer().getStat().getTotalMagic() * 5;
    var 战斗力1 = (力量 + 敏捷 + 智力 + 幸运) / 10;
    var 战斗力 = HP + MP + 力量 + 敏捷 + 智力 + 幸运 + 物理攻击力 + 魔法攻击力 + 战斗力1;
    var 个人信息开关 = cm.GetPiot("个人信息开关", "1");
    var 签到 = cm.getBossRank("签到", 2);
    cm.getPlayer().刷新身上装备附魔汇总数据();
    if (cm.getBossRank8("赛季积分", 2) < 0 || cm.getBossRank("补充负数", 2) < 0 || cm.getBossRank7("养殖经验", 2) < 0 || cm.getBossRank5("炼金经验", 2) < 0 || cm.getBossRank1("钓鱼经验", 2) < 0 || cm.getBossRank2("挖矿经验", 2) < 0 || cm.getBossRank3("泡点经验", 2) < 0 || cm.getBossRank4("唠叨经验", 2) < 0) {
        cm.setBossRankCount("月妙副本通关次数", 0);
        cm.setBossRankCount("废弃副本通关次数", 0);
        cm.setBossRankCount("玩具塔副本通关次数", 0);
        cm.setBossRankCount("女神塔副本通关次数", 0);
        cm.setBossRankCount("毒雾副本通关次数", 0);
        cm.setBossRankCount("海盗副本通关次数", 0);
        cm.setBossRankCount("男女副本通关次数", 0);
        cm.setBossRankCount("挑战蜈蚣", 0);
        cm.setBossRankCount("骂豆豆", 0);
        cm.setBossRankCount("签到", 0);
        cm.setBossRankCount("挖矿", 0);
        cm.setBossRankCount("补充负数", 1);
        cm.setBossRankCount1("钓鱼经验", 1);
        cm.setBossRankCount2("挖矿经验", 1);
        cm.setBossRankCount3("泡点经验", 1);
        cm.setBossRankCount4("唠叨经验", 1);
        cm.setBossRankCount5("炼金经验", 1);
        cm.setBossRankCount7("养殖经验", 1);
        cm.setBossRankCount8("赛季积分", 1);
        cm.sendOk("加载数据成功，请重新开启。");
        cm.dispose();
        return;

    } else if (status == 0) {
        var selStr = "    \t\t" + 心 + "  " + 心 + " #r#e < 个人信息 > #k#n " + 心 + "  " + 心 + "\r\n\r\n";

        if (cm.GetPiot("个人信息开关", "1") <= 0) {
            selStr += "\t" + 心 + " #d玩家：#k#r" + cm.getChar().getName() + "#k\r\n";
            selStr += "\t" + 心 + " #d职业：#k#r" + cm.职业(cm.判断职业()) + "#k\r\n";
            selStr += "\t" + 心 + " #d在线：#k( #n#b" + cm.查询总在线时间() + "#k / #r" + cm.查询今日在线时间() + " #k) \r\n";
            selStr += "\t" + 心 + " #d战斗力：#k#r" + 战斗力.toFixed(0) + " & " + ((cm.getPlayer().GetPvpkills() - cm.getPlayer().GetPvpdeaths()) / 2) + "\r\n#k";
			selStr += "\t" + 心 + " #d杀怪数量：#k#r" + cm.杀怪数量(角色) + "\r\n#k";
			selStr += "\t" + 心 + " #d大逃杀：冠军#k#r[" + cm.getBossRank("大逃杀活动冠军",2) + "] #k击杀#k#r[" + cm.getBossRank("大逃杀活动击杀",2) + "] #k被击杀#k#r[" + cm.getBossRank("大逃杀活动被杀",2) + "]\r\n#k";
			selStr += "#L0#" + 心2 + "#b [返回界面]#l #L3#" + 心2 + "#b [等级成就]#l\r\n\r\n";
			
			
			
            selStr += "#L1000#" + 心2 + " #b[个人财富]————————————————" + 心2 + "#k#l\r\n\r\n";
            if (cm.getBossRank("个人财富", 2) > 0) {
                selStr += "\t" + 心 + " #d金币：#k#r" + cm.getPlayer().getMeso() + "#k\r\n";
                selStr += "\t" + 心 + " #d点券：#k#r" + cm.getPlayer().getCSPoints(1) + "#k\r\n";
                selStr += "\t" + 心 + " #d抵用：#k#r" + cm.getPlayer().getCSPoints(2) + "#k\r\n";
            }
			

            selStr += "#L1002#" + 心2 + " #b[附魔潜能]————————————————" + 心2 + "#k#l\r\n\r\n";
            if (cm.getBossRank("附魔潜能", 2) > 0) {
                var 一击必杀增加值 = cm.getPlayer().获取附魔汇总值(7);
                var 一击必杀 = 10000000 + (10000000 / 100 * 一击必杀增加值);
                selStr += "\t" + 心 + " [#e#b必杀值#k#n]:#r" + 一击必杀.toFixed(1) + "#k\r\n";
				if (cm.getPlayer().获取附魔汇总值(100) > 0) {
                    selStr += "\t" + 心 + " [#e#b异常抗性#k#n]: 减少 #r" + cm.getPlayer().获取附魔汇总值(100) + " #k% 异常状态持续时间#k\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(1) > 0) {
                    selStr += "\t" + 心 + " [#e#b强攻#k#n]: 增加 #r" + cm.getPlayer().获取附魔汇总值(1) + " #k% 对普通怪物的伤害#k\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(2) > 0) {
                    selStr += "\t" + 心 + " [#e#b超·强攻#k#n]: 增加 #r" + cm.getPlayer().获取附魔汇总值(2) + " #k% 对高级怪物的伤害#k\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(3) > 0) {
                    selStr += "\t" + 心 + " [#e#b战争意志#k#n]: 增加 #r" + cm.getPlayer().获取附魔汇总值(3) + " #k% 对所有怪物的伤害#k\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(4) > 0) {
                    selStr += "\t" + 心 + " [#e#b鹰眼#k#n]: 对普通怪物 #r" + cm.getPlayer().获取附魔汇总值(4) + " #k% 几率一击必杀#k\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(5) > 0) {
                    selStr += "\t" + 心 + " [#e#b锐眼#k#n]: 对高级怪物 #r" + cm.getPlayer().获取附魔汇总值(5) + " #k% 几率一击必杀#k\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(6) > 0) {
                    selStr += "\t" + 心 + " [#e#b谢幕#k#n]: 对所有怪物 #r" + cm.getPlayer().获取附魔汇总值(6) + " #k% 几率一击必杀#k\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(7) > 0) {
                    selStr += "\t" + 心 + " [#e#b兵不血刃#k#n]: 增加 #r" + cm.getPlayer().获取附魔汇总值(7) + " #k% 一击必杀值#k\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(21) > 0) {
                    selStr += "\t" + 心 + " [#e#b坚韧#k#n]: 减少 #r" + cm.getPlayer().获取附魔汇总值(21) + " #k% 受到的伤害#k\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(31) > 0) {
                    selStr += "\t" + 心 + " [#e#b幸运狩猎#k#n]: 增加 #r" + cm.getPlayer().获取附魔汇总值(31) + " #k% 狩猎经验#k\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(32) > 0) {
                    selStr += "\t" + 心 + " [#e#b苦中作乐#k#n]: 被诅咒状态下增加 #r5#k 倍狩猎经验#k\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(33) > 0) {
                    selStr += "\t" + 心 + " [#e#b闲来好运#k#n]: 增加 #r" + cm.getPlayer().获取附魔汇总值(33) + " #k% 泡点经验获取#k\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(34) > 0) {
                    selStr += "\t" + 心 + " [#e#b财源滚滚#k#n]: 增加 #r" + cm.getPlayer().获取附魔汇总值(34) + " #k% 泡点金币获取#k\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(40) > 0) {
                    selStr += "\t" + 心 + " [#e#b愤怒之火#k#n]: 愤怒之火状态下，回复输出 #r" + cm.getPlayer().获取附魔汇总值(40) + " #k% 的伤害值生命，行动时每 #r3#k 秒燃烧 #r20#k% 生命值。#k\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(41) > 0) {
                    selStr += "\t" + 心 + " [#e#b圣骑反弹#k#n]: 伤害反弹状态下，准骑士类职业 #r" + cm.getPlayer().获取附魔汇总值(40) + " #k%  的几率闪避所有伤害，闪避不成功减少 #r" + cm.getPlayer().获取附魔汇总值(41) + " #k% 的伤害#k\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(43) > 0) {
                    selStr += "\t" + 心 + " [#e#b蓄势待发#k#n]: 根据距离上一次攻击的间隔，为下一次攻击提高输出 （ #d"+cm.查看蓄力一击()+"#k ）\r\n\t\t#b[增加比例]: #r" + cm.getPlayer().获取附魔汇总值(43) + " #k%\r\n";
                }
                if (cm.getPlayer().获取附魔汇总值(51) > 0) {
                    selStr += "\t" + 心 + " [#e#b稳如泰山#k#n]: 角色受到伤害时 #r" + cm.getPlayer().获取附魔汇总值(42) + " #k% 的几率发动稳如泰山。#k\r\n";
                }

            }

            selStr += "#L1001#" + 心2 + " #b[游戏生涯]————————————————" + 心2 + "#k#l\r\n\r\n";
            if (cm.getBossRank("游戏生涯", 2) > 0) {
                var 钓鱼经验 = cm.getBossRank1("钓鱼经验", 2);
                var 挖矿经验 = cm.getBossRank2("挖矿经验", 2);
                var 泡点经验 = cm.getBossRank3("泡点经验", 2);
                var 唠叨经验 = cm.getBossRank4("唠叨经验", 2);
                var 炼金经验 = cm.getBossRank5("炼金经验", 2);
                var 养殖经验 = cm.getBossRank7("养殖经验", 2);
                /**********************
                 钓鱼钓鱼钓鱼钓鱼钓鱼钓鱼
                 ***********************/
                if (钓鱼经验 > 8601) {
                    var 钓鱼等级 = 6;
                } else if (钓鱼经验 > 3601) {
                    var 钓鱼等级 = 5;
                } else if (钓鱼经验 >= 1601) {
                    var 钓鱼等级 = 4;
                } else if (钓鱼经验 >= 601) {
                    var 钓鱼等级 = 3;
                } else if (钓鱼经验 >= 101) {
                    var 钓鱼等级 = 2;
                } else {
                    var 钓鱼等级 = 1;
                }

                if (钓鱼等级 == 1) {//1-100*需要100经验
                    var 钓鱼经验显示1 = 钓鱼经验;
                    var 钓鱼经验显示2 = 钓鱼经验;
                } else if (钓鱼等级 == 2) {//101-600*需要500经验
                    var 钓鱼经验显示1 = (钓鱼经验 - 100) / 5;
                    var 钓鱼经验显示2 = (钓鱼经验 - 100) / 5;
                } else if (钓鱼等级 == 3) {//601-1600*需要1000经验
                    var 钓鱼经验显示1 = (钓鱼经验 - 600) / 10;
                    var 钓鱼经验显示2 = (钓鱼经验 - 600) / 10;
                } else if (钓鱼等级 == 4) {//1601-3601*需要2000经验
                    var 钓鱼经验显示1 = (钓鱼经验 - 1600) / 20;
                    var 钓鱼经验显示2 = (钓鱼经验 - 1600) / 20;
                } else if (钓鱼等级 == 5) {//3601-8601*需要5000经验
                    var 钓鱼经验显示1 = (钓鱼经验 - 3600) / 50;
                    var 钓鱼经验显示2 = (钓鱼经验 - 3600) / 50;
                } else {
                    var 钓鱼经验显示1 = 100;
                    var 钓鱼经验显示2 = 钓鱼经验;
                }
                selStr += "\t" + 心 + " [#e#b钓鱼#k#n]:#r" + 钓鱼等级 + "级#k#B" + 钓鱼经验显示1 + "# [" + 钓鱼经验显示2.toFixed(2) + "%]\r\n";
                /**********************
                 挖矿挖矿挖矿挖矿挖矿挖矿
                 ***********************/
                if (挖矿经验 > 8601) {
                    var 挖矿等级 = 6;
                } else if (挖矿经验 > 3601) {
                    var 挖矿等级 = 5;
                } else if (挖矿经验 >= 1601) {
                    var 挖矿等级 = 4;
                } else if (挖矿经验 >= 601) {
                    var 挖矿等级 = 3;
                } else if (挖矿经验 >= 101) {
                    var 挖矿等级 = 2;
                } else {
                    var 挖矿等级 = 1;
                }

                if (挖矿等级 == 1) {//1-100*需要100经验
                    var 挖矿经验显示1 = 挖矿经验;
                    var 挖矿经验显示2 = 挖矿经验;
                } else if (挖矿等级 == 2) {//101-600*需要500经验
                    var 挖矿经验显示1 = (挖矿经验 - 100) / 5;
                    var 挖矿经验显示2 = (挖矿经验 - 100) / 5;
                } else if (挖矿等级 == 3) {//601-1600*需要1000经验
                    var 挖矿经验显示1 = (挖矿经验 - 600) / 10;
                    var 挖矿经验显示2 = (挖矿经验 - 600) / 10;
                } else if (挖矿等级 == 4) {//1601-3601*需要2000经验
                    var 挖矿经验显示1 = (挖矿经验 - 1600) / 20;
                    var 挖矿经验显示2 = (挖矿经验 - 1600) / 20;
                } else if (挖矿等级 == 5) {//3601-8601*需要5000经验
                    var 挖矿经验显示1 = (挖矿经验 - 3600) / 50;
                    var 挖矿经验显示2 = (挖矿经验 - 3600) / 50;
                } else {
                    var 挖矿经验显示1 = 100;
                    var 挖矿经验显示2 = 挖矿经验;
                }
                selStr += "\t" + 心 + " [#e#b挖矿#k#n]:#r" + 挖矿等级 + "级#k#B" + 挖矿经验显示1 + "# [" + 挖矿经验显示2.toFixed(2) + "%]\r\n";
                /**********************
                 泡点泡点泡点泡点泡点泡点
                 ***********************/
                if (泡点经验 > 8601) {
                    var 泡点等级 = 6;
                } else if (泡点经验 > 3601) {
                    var 泡点等级 = 5;
                } else if (泡点经验 >= 1601) {
                    var 泡点等级 = 4;
                } else if (泡点经验 >= 601) {
                    var 泡点等级 = 3;
                } else if (泡点经验 >= 101) {
                    var 泡点等级 = 2;
                } else {
                    var 泡点等级 = 1;
                }

                if (泡点等级 == 1) {//1-100*需要100经验
                    var 泡点经验显示1 = 泡点经验;
                    var 泡点经验显示2 = 泡点经验;
                } else if (泡点等级 == 2) {//101-600*需要500经验
                    var 泡点经验显示1 = (泡点经验 - 100) / 5;
                    var 泡点经验显示2 = (泡点经验 - 100) / 5;
                } else if (泡点等级 == 3) {//601-1600*需要1000经验
                    var 泡点经验显示1 = (泡点经验 - 600) / 10;
                    var 泡点经验显示2 = (泡点经验 - 600) / 10;
                } else if (泡点等级 == 4) {//1601-3601*需要2000经验
                    var 泡点经验显示1 = (泡点经验 - 1600) / 20;
                    var 泡点经验显示2 = (泡点经验 - 1600) / 20;
                } else if (泡点等级 == 5) {//3601-8601*需要5000经验
                    var 泡点经验显示1 = (泡点经验 - 3600) / 50;
                    var 泡点经验显示2 = (泡点经验 - 3600) / 50;
                } else {
                    var 泡点经验显示1 = 100;
                    var 泡点经验显示2 = 泡点经验;
                }
                selStr += "\t" + 心 + " [#e#b泡点#k#n]:#r" + 泡点等级 + "级#k#B" + 泡点经验显示1 + "# [" + 泡点经验显示2.toFixed(2) + "%]\r\n";
                /**********************
                 唠叨唠叨唠叨唠叨唠叨唠叨
                 ***********************/
                if (唠叨经验 > 8601) {
                    var 唠叨等级 = 6;
                } else if (唠叨经验 > 3601) {
                    var 唠叨等级 = 5;
                } else if (唠叨经验 >= 1601) {
                    var 唠叨等级 = 4;
                } else if (唠叨经验 >= 601) {
                    var 唠叨等级 = 3;
                } else if (唠叨经验 >= 101) {
                    var 唠叨等级 = 2;
                } else {
                    var 唠叨等级 = 1;
                }

                if (唠叨等级 == 1) {//1-100*需要100经验
                    var 唠叨经验显示1 = 唠叨经验;
                    var 唠叨经验显示2 = 唠叨经验;
                } else if (唠叨等级 == 2) {//101-600*需要500经验
                    var 唠叨经验显示1 = (唠叨经验 - 100) / 5;
                    var 唠叨经验显示2 = (唠叨经验 - 100) / 5;
                } else if (唠叨等级 == 3) {//601-1600*需要1000经验
                    var 唠叨经验显示1 = (唠叨经验 - 600) / 10;
                    var 唠叨经验显示2 = (唠叨经验 - 600) / 10;
                } else if (唠叨等级 == 4) {//1601-3601*需要2000经验
                    var 唠叨经验显示1 = (唠叨经验 - 1600) / 20;
                    var 唠叨经验显示2 = (唠叨经验 - 1600) / 20;
                } else if (唠叨等级 == 5) {//3601-8601*需要5000经验
                    var 唠叨经验显示1 = (唠叨经验 - 3600) / 50;
                    var 唠叨经验显示2 = (唠叨经验 - 3600) / 50;
                } else {
                    var 唠叨经验显示1 = 100;
                    var 唠叨经验显示2 = 唠叨经验;
                }
                selStr += "\t" + 心 + " [#e#b唠叨#k#n]:#r" + 唠叨等级 + "级#k#B" + 唠叨经验显示1 + "# [" + 唠叨经验显示2.toFixed(2) + "%]\r\n";
                /**********************
                 炼金炼金炼金炼金炼金炼金
                 ***********************/
                if (炼金经验 > 8601) {
                    var 炼金等级 = 6;
                } else if (炼金经验 > 3601) {
                    var 炼金等级 = 5;
                } else if (炼金经验 >= 1601) {
                    var 炼金等级 = 4;
                } else if (炼金经验 >= 601) {
                    var 炼金等级 = 3;
                } else if (炼金经验 >= 101) {
                    var 炼金等级 = 2;
                } else {
                    var 炼金等级 = 1;
                }

                if (炼金等级 == 1) {//1-100*需要100经验
                    var 炼金经验显示1 = 炼金经验;
                    var 炼金经验显示2 = 炼金经验;
                } else if (炼金等级 == 2) {//101-600*需要500经验
                    var 炼金经验显示1 = (炼金经验 - 100) / 5;
                    var 炼金经验显示2 = (炼金经验 - 100) / 5;
                } else if (炼金等级 == 3) {//601-1600*需要1000经验
                    var 炼金经验显示1 = (炼金经验 - 600) / 10;
                    var 炼金经验显示2 = (炼金经验 - 600) / 10;
                } else if (炼金等级 == 4) {//1601-3601*需要2000经验
                    var 炼金经验显示1 = (炼金经验 - 1600) / 20;
                    var 炼金经验显示2 = (炼金经验 - 1600) / 20;
                } else if (炼金等级 == 5) {//3601-8601*需要5000经验
                    var 炼金经验显示1 = (炼金经验 - 3600) / 50;
                    var 炼金经验显示2 = (炼金经验 - 3600) / 50;
                } else {
                    var 炼金经验显示1 = 100;
                    var 炼金经验显示2 = 炼金经验;
                }
                selStr += "\t" + 心 + " [#e#b炼金#k#n]:#r" + 炼金等级 + "级#k#B" + 炼金经验显示1 + "# [" + 炼金经验显示2.toFixed(2) + "%]\r\n";

                /**********************
                 养殖养殖养殖养殖养殖养殖
                 ***********************/
                if (养殖经验 > 8601) {
                    var 养殖等级 = 6;
                } else if (养殖经验 > 3601) {
                    var 养殖等级 = 5;
                } else if (养殖经验 >= 1601) {
                    var 养殖等级 = 4;
                } else if (养殖经验 >= 601) {
                    var 养殖等级 = 3;
                } else if (养殖经验 >= 101) {
                    var 养殖等级 = 2;
                } else {
                    var 养殖等级 = 1;
                }

                if (养殖等级 == 1) {//1-100*需要100经验
                    var 养殖经验显示1 = 养殖经验;
                    var 养殖经验显示2 = 养殖经验;
                } else if (养殖等级 == 2) {//101-600*需要500经验
                    var 养殖经验显示1 = (养殖经验 - 100) / 5;
                    var 养殖经验显示2 = (养殖经验 - 100) / 5;
                } else if (养殖等级 == 3) {//601-1600*需要1000经验
                    var 养殖经验显示1 = (养殖经验 - 600) / 10;
                    var 养殖经验显示2 = (养殖经验 - 600) / 10;
                } else if (养殖等级 == 4) {//1601-3601*需要2000经验
                    var 养殖经验显示1 = (养殖经验 - 1600) / 20;
                    var 养殖经验显示2 = (养殖经验 - 1600) / 20;
                } else if (养殖等级 == 5) {//3601-8601*需要5000经验
                    var 养殖经验显示1 = (养殖经验 - 3600) / 50;
                    var 养殖经验显示2 = (养殖经验 - 3600) / 50;
                } else {
                    var 养殖经验显示1 = 100;
                    var 养殖经验显示2 = 养殖经验;
                }
                selStr += "\t" + 心 + " [#e#b养殖#k#n]:#r" + 养殖等级 + "级#k#B" + 养殖经验显示1 + "# [" + 养殖经验显示2.toFixed(2) + "%]\r\n";
            }


        } else {
            selStr += "\r\n";
        }
		
         
         if (cm.getPlayer().getGMLevel() == 6) {
         if (cm.GetPiot("个人信息开关", "1") <= 0) {
         selStr += "\r\n\t\t\t#L100#" + jt + " #b个人信息#g[开启中]#r[GM]#k#l";
         }
         if (cm.GetPiot("个人信息开关", "1") >= 1) {
         selStr += "\r\n\t\t\t#L101#" + jt + " #b个人信息#r[关闭中]#r[GM]#k#l";
         }

         
         }
        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
			
			case 1003:
                if (cm.getBossRank("副本生涯", 2) > 0) {
                    cm.setBossRankCount("副本生涯", -cm.getBossRank("副本生涯", 2));
                } else {
                    cm.setBossRankCount("副本生涯", 1);
                }
                cm.对话结束();
                cm.打开NPC(9900004, 1);
                break;
			case 1002:
                if (cm.getBossRank("附魔潜能", 2) > 0) {
                    cm.setBossRankCount("附魔潜能", -cm.getBossRank("附魔潜能", 2));
                } else {
                    cm.setBossRankCount("附魔潜能", 1);
                }
                cm.对话结束();
                cm.打开NPC(9900004, 1);
                break;
            case 1000:
                if (cm.getBossRank("个人财富", 2) > 0) {
                    cm.setBossRankCount("个人财富", -cm.getBossRank("个人财富", 2));
                } else {
                    cm.setBossRankCount("个人财富", 1);
                }
                cm.对话结束();
                cm.打开NPC(9900004, 1);
                break;
            case 1001:
                if (cm.getBossRank("游戏生涯", 2) > 0) {
                    cm.setBossRankCount("游戏生涯", -cm.getBossRank("游戏生涯", 2));
                } else {
                    cm.setBossRankCount("游戏生涯", 1);
                }
                cm.对话结束();
                cm.打开NPC(9900004, 1);
                break;
            case 0:
                cm.dispose();
                cm.openNpc(9900004, 0);
                break;
            case 999:
                cm.dispose();
                cm.openNpc(9900004, 99);
                break;
            case 1:
                cm.dispose();
                cm.openNpc(9900004, 10001);
                break;
            case 3:
                cm.dispose();
                cm.openNpc(9900004, 1001);
                break;
            case 100:
                cm.GainPiot("个人信息开关", "1", -个人信息开关);
                cm.GainPiot("个人信息开关", "1", 1);
                cm.dispose();
                cm.openNpc(9900004, 1);
                break;
            case 101:
                cm.GainPiot("个人信息开关", "1", -个人信息开关);
                cm.dispose();
                cm.openNpc(9900004, 1);
                break
           

        }
    }
}
