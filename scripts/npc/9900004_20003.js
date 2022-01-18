/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：副本
 */
var 魔法石 = 4006000;
//传送地点，消耗金币，文字
var maps = Array(
		[701010320, 5],
		[106021400, 5],
        [240050400, 5],
		[211042300, 5]











        );
var status = 0;
var show;
var sCost;
var selectedMap = -1;
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status >= 2) {
            cm.说明文字(" 等你想去哪里了，记得找我哦！");
            cm.对话结束();
            return;
        }
        status--;
    }
    if (status == 0) {
    } else if (status == 1) {
        var selStr = "  #d#e请选择你要去的副本：#k#n#b";
        for (var i = 0; i < maps.length; i++) {
			 selStr += "\r\n#L" + i + "# #b#m" + maps[i] + " ##l";
        }
        cm.sendSimple(selStr);
    } else if (status == 2) {
        show = maps[selection][1];
        cm.sendYesNo("\t传送目标: #b#m" + maps[selection] + "##k\r\n\t需要 "+cm.显示物品(魔法石)+" x #b" + show + "#k");
        selectedMap = selection;
    } else if (status == 3) {
        if (!cm.判断物品数量(魔法石,1)) {
            cm.sendNext("你没有 "+cm.显示物品(魔法石)+" 无法启动空间传送阵。");
            cm.safeDispose();
        } else {
            cm.收物品(魔法石,1);
            cm.warp(maps[selectedMap][0]);
            cm.dispose();
        }
    }
}