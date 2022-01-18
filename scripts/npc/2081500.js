/*
 ZEVMS冒险岛(079)游戏服务端
 海盗四转教官
 */

var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        status--;
    }

    if (status == 0) {
        if (cm.getQuestStatus(6941) != 2 || cm.getQuestStatus(6942) != 2 || cm.getQuestStatus(6943) != 2 || cm.getQuestStatus(6944) != 2) {
            cm.sendOk("先听我给你讲一下故事吧？");
            cm.dispose();
            return;
        } else if (!(cm.getJob() == 511 || cm.getJob() == 521)) {
            cm.sendOk("为什么你要见我??还有你想要问我关于什么事情??");
            cm.dispose();
            return;
        } else if (cm.getPlayer().getLevel() < 120) {
            cm.sendOk("你等级尚未到达120级。");
            cm.dispose();
            return;
        } else {
            if (cm.getJob() == 511) {
                cm.sendSimple("你想继续变强吗？\r\n#b#L1#我想成为冲锋队长#l#l");
            } else if (cm.getJob() == 521) {
                cm.sendSimple("你想继续变强吗？\r\n#b#L1#我想成为船长#l#l");
            } else {
                cm.sendOk("好吧假如你想要4转麻烦再来找我。");
                cm.dispose();
                return;
            }
        }
    } else if (status == 1) {
        if (selection == 1) {
                if (cm.getJob() == 511) {
                    cm.changeJob(512);
                    cm.teachSkill(5121007, 0, 10);
                    cm.teachSkill(5121001, 0, 10);
                    cm.teachSkill(5121002, 0, 10);
                    cm.teachSkill(5121009, 0, 10);
                    cm.sendNext("恭喜你转职为 #b冲锋队长#k.我送你一些神秘小礼物^^");
                } else if (cm.getJob() == 521) {
                    cm.changeJob(522);
                    cm.teachSkill(5221004, 0, 10);
                    cm.teachSkill(5220001, 0, 10);
                    cm.teachSkill(5220002, 0, 10);
                    cm.teachSkill(5220011, 0, 10);
                    cm.sendNext("恭喜你转职为 #b船长#k.我送你一些神秘小礼物^^");
                }
            cm.dispose();
        }
    } else if (status == 2) {
        if (cm.getJob() == 512) {
            cm.sendNext("不要忘记了这一切都取决于你练了多少.");
        } else {
            cm.sendNext("不要忘记了这一切都取决于你练了多少.");
        }
    } else if (status == 3) {
        cm.dispose();
    }
}