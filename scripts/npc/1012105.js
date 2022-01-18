/*
 ZEVMS冒险岛(079)游戏服务端
 */
var status = 0;
var skin = Array(0, 1, 2, 3, 4);
var price;

function start() {
    cm.sendSimple("欢迎来到弓箭手村护发中心! 是否有想要染的皮肤呢?? 就像我的健康皮肤??  如果你有 #b#t5153000##k, 就可以随意染的想到的皮肤~~~\r\n\#L2#我已经有一个优惠券!#l");
}

function action(mode, type, selection) {
    if (mode < 1)
        cm.dispose();
    else {
        status++;
        if (status == 1)
            cm.sendStyle("选一个想要的风格.",5153000, skin);
        else {
            if (cm.haveItem(5153000)){
                cm.gainItem(5153000, -1);
                cm.setSkin(selection);
                cm.sendOk("享受!");
            } else
                cm.sendOk("您貌似没有#b#t5153000##k..");
            cm.dispose();
        }
    }
}
