/*
 ZEVMS冒险岛(079)游戏服务端脚本
 类型：装备附魔类类型
 时间：2018-08-09
 作者：ZEV，坐和放宽
 引用; 1032002_1,1032002_2,1032002_3
 */
var jt = "#fUI/Basic/BtHide3/mouseOver/0#";
function start() {
    status = -1;
    action(1, 0, 0)
}
function action(mode, type, selection) {
    if (status <= 0 && mode <= 0) {
        cm.dispose();
        return
    }
    if (mode == 1) {
        status++
    } else {
        status--

    }
    if (status <= 0) {
        var selStr = "   我是来自#r异世界#k的道具制作者，我可以打造非凡的道具效果，你想给你的道具增加特殊效果吗？附魔的道具通过移动，可以在信息栏查看详细信息。\r\n   #r附魔的装备不能进行强化，放置拍卖等#k。\r\n\r\n";
		selStr += "	 #d#e附魔类；#n#k\r\n";
		selStr += "  #L1##b装备打孔#k#l  #L2##b装备附魔#k#l  #L3##b装备清洗#k#l\r\n\r\n";
		selStr += "	 #d#e宝石类；#n#k\r\n";
		selStr += "  #L4##b宝石洗练#k#l  #L5##b宝石镶嵌#k#l\r\n\r\n";
		selStr += "	 #d#e增幅类；#n#k\r\n";
		selStr += "  #L6##b装备洗练#k#l  #L7##b装备增幅#k#l";
        cm.sendSimple(selStr)
    } else if (status == 1) {
        switch (selection) {
        case 1:
			cm.对话结束();
            cm.打开NPC(1032002,1);
            break;
		case 2:
			cm.对话结束();
            cm.打开NPC(1032002,2);
            break;
		case 3:
			cm.对话结束();
            cm.打开NPC(1032002,3);
            break;
		case 4:
			cm.对话结束();
            cm.打开NPC(1032002,4);
            break;
		case 5:
			cm.对话结束();
            cm.打开NPC(1032002,5);
            break;
		case 6:
			cm.对话结束();
            cm.打开NPC(1032002,6);
            break;
		case 7:
			cm.对话结束();
            cm.打开NPC(1032002,7);
            break;
        }
    }
}