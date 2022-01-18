/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：英语村答题
 */
var status = -1;
var letter = Array("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.dispose();
        }
        status--;
    }
    if (status == 0) {
        cm.sendSimple("#b#L0#回答问题。#l\r\n#L1#离开地图。#l");
    } else if (status == 1) {
        if (selection == 0) {
            if (!cm.isLeader() || cm.getPlayer().getEventInstance() == null) {
                cm.sendOk("请找队长来找我谈话。");
            } else {
                var letters = cm.getPlayer().getEventInstance().getProperty("answer");
                var needed = Array(letters.length);
                var done = 0;
                for (var i = 0; i < letters.length(); i++) {
                    for (var x = 0; x < letter.length; x++) {
                        if (letters.substring(i, i + 1).equals(letter[x])) {
                            needed[i] = 3994059 + x;
                            break;
                        }
                    }
                }
                for (var i = 0; i < needed.length; i++) {
                    var num = 0;
                    for (var x = 0; x < needed.length; x++) {
                        if (needed[x] == needed[i]) {
                            num++;
                        }
                    }
                    if (cm.haveItem(needed[i], num, false, false)) {
                        done++;
                    }
                }
                var lettersNot = 0;
                for (var i = 3994059; i < 3994085; i++) {
                    var num = 0;
                    for (var x = 0; x < needed.length; x++) {
                        if (needed[x] == i) {
                            num++;
                            break;
                        }
                    }
                    if (num == 0 && cm.haveItem(i, 1)) {
                        lettersNot++;
                    }
                }
                if (lettersNot > 0) {
                    cm.sendNext("你有字母但是你回答了 #e错误的答案！#n");
                } else if (done != needed.length) {
                    cm.sendNext("你回答了 #e错误的答案！#n");
                } else {
                    for (var i = 3994059; i < 3994085; i++) {
                        cm.givePartyItems(i, 0, true);
                    }
                    cm.givePartyItems(4001137, 1);
                    cm.warpParty(702090400, 0);
                    cm.showEffect(true, "englishSchool/correct");
                    cm.setBossRankCount("英语村", 1);
                    cm.givePartyBossLog("英语村");
                    cm.worldMessage(2, "[副本-英语村] : 恭喜 " + cm.getPlayer().getName() + " 带领队伍，完成英语村副本。");
                }
            }
        } else if (selection == 1) {
            for (var i = 3994059; i < 3994085; i++) {
                cm.removeAll(i);
            }
            cm.warp(702090400, 0);
        }
        cm.dispose();
    }
}