/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：每日任务
 */
var JT = "#fUI/Basic/BtHide3/mouseOver/0#";
var 心 = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
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
    if (status == 0) {
        var selStr = "    你好，我是薇薇安，我这里有很多#r每日任务#k，不知道勇士你能帮我吗？\r\n";
		if(cm.getBossLog("为被人点赞2")<=0){
			selStr += " #L1##b为被人点赞#k#l\r\n";
		}
		if(cm.getBossLog("被被人点赞2")<=0){
			selStr += " #L2##b被被人点赞#k#l\r\n";
		}
		if(cm.getBossLog("击杀高级怪物2")<=0){
			selStr += " #L3##b击杀高级怪物#k#l\r\n";
		}
		if(cm.getBossLog("钓鱼2")<=0){
			selStr += " #L4##b垂钓#k#l\r\n";
		}
		if(cm.getBossLog("月妙2")<=0){
			selStr += " #L100##b保护月妙兔#k#l\r\n";
		}
		if(cm.getBossLog("毒雾森林2")<=0){
			selStr += " #L101##b探索毒雾森林#k#l\r\n";
		}
		if(cm.getBossLog("废弃都市2")<=0){
			selStr += " #L102##b都市的下水道#k#l\r\n";
		}
		if(cm.getBossLog("海盗船2")<=0){
			selStr += " #L103##b停泊的海盗船#k#l\r\n";
		}
		if(cm.getBossLog("罗密欧与朱丽叶2")<=0){
			selStr += " #L104##b见证爱情#k#l\r\n";
		}
		if(cm.getBossLog("女神塔2")<=0){
			selStr += " #L105##b残缺的女神雕像#k#l\r\n";
		}
		if(cm.getBossLog("玩具塔2")<=0){
			selStr += " #L106##b神秘的玩具塔#k#l\r\n";
		}
        cm.说明文字(selStr);
    } else if (status == 1) {
        switch (selection) {
			//神秘的玩具塔
            case 106:
                cm.dispose();
                cm.openNpc(9330042, 106);
                break;
			//残缺的女神雕像
            case 105:
                cm.dispose();
                cm.openNpc(9330042, 105);
                break;
			//罗密欧与朱丽叶
            case 104:
                cm.dispose();
                cm.openNpc(9330042, 104);
                break;
			//停泊的海盗船
            case 103:
                cm.dispose();
                cm.openNpc(9330042, 103);
                break;
			//都市的下水道
            case 102:
                cm.dispose();
                cm.openNpc(9330042, 102);
                break;
			//探索毒雾森林
            case 101:
                cm.dispose();
                cm.openNpc(9330042, 101);
                break;
			//保护月妙兔
            case 100:
                cm.dispose();
                cm.openNpc(9330042, 100);
                break;
			//垂钓
            case 3:
                cm.dispose();
                cm.openNpc(9330042, 4);
                break;
			//击杀高级怪物
            case 3:
                cm.dispose();
                cm.openNpc(9330042, 3);
                break;
			//被被人点赞
            case 2:
                cm.dispose();
                cm.openNpc(9330042, 2);
                break;
			//为被人点赞
            case 1:
                cm.dispose();
                cm.openNpc(9330042, 1);
                break;
            
        }
    }
}