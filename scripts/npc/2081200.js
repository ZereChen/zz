/*
 ZEVMS冒险岛(079)游戏服务端
 魔法师四转教官
 */


var status = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1)
        status++;
    else
        status--;

    if (status == 0) {
        if (cm.getQuestStatus(6911) != 2 || cm.getQuestStatus(6912) != 2 || cm.getQuestStatus(6913) != 2 || cm.getQuestStatus(6914) != 2 || cm.getQuestStatus(6915) != 2) {
            cm.sendOk("先听我给你讲一下故事吧？");
            cm.dispose();
            return;
        } else if (!(cm.getJob() == 211 || cm.getJob() == 221 || cm.getJob() == 231)) {
            cm.sendOk("为什么你要见我??还有你想要问我关于什么事情??");
            cm.dispose();
            return;
        } else if (cm.getPlayer().getLevel() < 120) {
            cm.sendOk("你等级尚未到达120级.");
            cm.dispose();
            return;
        } else {
            if (cm.getJob() == 211) {
                cm.sendSimple("你想继续变强吗？\r\n#b#L0#我想成为魔导师#l\r\n#b#L1#像我想一下...#l");
            } else if (cm.getJob() == 221) {
                cm.sendSimple("你想继续变强吗？\r\n#b#L0#我想成为魔导师#l\r\n#b#L1#像我想一下...#l");
            } else if (cm.getJob() == 231) {
                cm.sendSimple("你想继续变强吗？\r\n#b#L0#我想成为主教#l\r\n#b#L1#像我想一下...#l");
            } else {
                cm.sendOk("好吧假如你想要4转麻烦再来找我");
                cm.dispose();
                return;
            }
        }
    } else if (status == 1) {
        if (selection == 1) {
            cm.sendOk("好吧假如你想要4转麻烦再来找我。");
            cm.dispose();
            return;
        }
        if (cm.getPlayerStat("RSP") > (cm.getPlayerStat("LVL") - 120) * 3) {
            cm.sendOk("你的技能点数还没点完。");
            cm.dispose();
            return;
        } else {
            if (cm.canHold(2280003)) {
                cm.gainItem(2280003, 1);
                if (cm.getJob() == 211) {
                    cm.changeJob(212);
                    cm.teachSkill(2121001, 0, 10);
                    cm.teachSkill(2121006, 0, 10);
                    cm.teachSkill(2121002, 0, 10);
                    cm.sendNext("恭喜你转职为 #b魔导师#k 我送你一些神秘小礼物^^");
                } else if (cm.getJob() == 221) {
                    cm.changeJob(222);
                    cm.teachSkill(2221001, 0, 10);
                    cm.teachSkill(2221006, 0, 10);
                    cm.teachSkill(2221002, 0, 10);
                    cm.sendNext("恭喜你转职为 #b魔导师#k 我送你一些神秘小礼物^^");
                } else {
                    cm.changeJob(232);
                    cm.teachSkill(2321001, 0, 10);
                    cm.teachSkill(2321005, 0, 10);
                    cm.teachSkill(2321002, 0, 10);
                    cm.sendNext("恭喜你转职为 #b主教#k 我送你一些神秘小礼物^^");
                }
            } else {
                cm.sendOk("你没有多的栏位请清空再来尝试一次!");
                cm.dispose();
                return;
            }
        }
    } else if (status == 2) {
        if (cm.getJob() == 212) {
            cm.sendNext("不要忘记了这一切都取决于你练了多少.");
        } else if (cm.getJob() == 222) {
            cm.sendNextPrev("不要忘记了这一切都取决于你练了多少.");
        } else {
            cm.sendNextPrev("不要忘记了这一切都取决于你练了多少.");
        }
    } else if (status == 3) {
        cm.dispose();
    }
}