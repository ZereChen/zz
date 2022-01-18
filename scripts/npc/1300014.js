/*
 ZEVMS冒险岛(079)游戏服务端
 */
 
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 1)
            status++;
        else
            status--;
    }
    if (status == 0) {
        if (cm.isQuestActive(2314)) {
            cm.PlayerToNpc("这是一个#b巨大的魔法屏障#k ...");
        } else if (cm.isQuestActive(2322)) {
            cm.PlayerToNpc("这个地方还是先报告给 #b#p1300003##k 知道吧！");
        } else {
            cm.PlayerToNpc("我是否该使用 #b#v2430014# #t2430014#？？");
        }
    }else  if (status == 1) {
        if (cm.isQuestActive(2314)) {
            cm.forceCompleteQuest(2314);
            cm.dispose();
        } else {
            cm.playerMessage("你可以进去了。");
        }
    }
}