/*
 ZEVMS冒险岛(079)游戏服务端
 */
var status = 0;
var beauty = 0;
var hairprice = 1000000;
var haircolorprice = 1000000;
//男发型
var mhair = Array(30030, 30020, 30000, 30310, 30330, 30060, 30150, 30410, 30210, 30140, 30120, 30200);
//女发型
var fhair = Array(31050, 31040, 31000, 31150, 31310, 31300, 31160, 31100, 31410, 31030, 31080, 31070);
var hairnew = Array();

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode < 1) {
        cm.dispose();
    } else {
        status++;
        if (status == 0)
            cm.sendSimple(" Hi~ #b#h ##k 你想换个帅气的发型吗？\r\n\r\n\r\n#b#L1#我要换个发型#l\r\n#L2#我要染个颜色#l");
        else if (status == 1) {
            if (selection == 0) {
                beauty = 0;
            } else if (selection == 1) {
                beauty = 1;
                hairnew = Array();
                if (cm.getPlayer().getGender() == 0){
                    for (var i = 0; i < mhair.length; i++){
                        hairnew.push(mhair[i]);
					}
				}
                if (cm.getPlayer().getGender() == 1){
                    for (var i = 0; i < fhair.length; i++){
                        hairnew.push(fhair[i]);
					}
				}
                cm.sendStyle("请选择你想要发发型吧。", 5150001, hairnew);
            } else if (selection == 2) {
                beauty = 2;
                haircolor = Array();
                var current = parseInt(cm.getPlayer().getHair() / 10) * 10;
                for (var i = 0; i < 8; i++)
                    haircolor.push(current + i);
                cm.sendStyle("请选择你想染的发型颜色吧。", 5150001, haircolor);
            }
        } else if (status == 2) {
            cm.dispose();
            if (beauty == 1) {
                if (cm.haveItem(5150001)) {
                    cm.gainItem(5150001, -1);
                    cm.setHair(hairnew[selection]);
                    cm.sendOk("恩，发型已经剪好了，你看看好看吧!");
                } else
                    cm.sendOk("你没有 #v5150001# #b#t5150001##k。");
            }
            if (beauty == 2) {
                if (cm.haveItem(5151001)) {
                    cm.gainItem(5151001, -1);
                    cm.setHair(haircolor[selection]);
                    cm.sendOk("哇，颜色已经帮你染好的，你看看好看吧!");
                } else{
                   cm.sendOk("你没有 #v5151001# #b#t5151001##k。");
				}
            }
        }
    }
}