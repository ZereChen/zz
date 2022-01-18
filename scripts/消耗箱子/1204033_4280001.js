/*
 ZEVMS冒险岛(079)游戏服务端
 重生的谜之蛋
 4280001
 5490001
 */
var status = 0;
//物品ID，抽奖概率，物品数量，(0/不广播，1广播)
var itemList =
        Array(
				/*************
				手套
				*************/
				//重生定边手套
                Array(1082239, 100, 1, 1),
				//重生逍遥手套
                Array(1082240, 100, 1, 1),
				//重生白云手套
                Array(1082241, 100, 1, 1),
				//重生探云手套
                Array(1082242, 100, 1, 1),
				//重生抚浪手套
                Array(1082243, 100, 1, 1),
				/*************
				鞋子
				*************/
				//重生坚壁靴
                Array(1072361, 100, 1, 1),
				//重生缥缈鞋
                Array(1072362, 100, 1, 1),
				//重生彩虹鞋
                Array(1072363, 100, 1, 1),
				//重生舞空靴
                Array(1072364, 100, 1, 1),
				//重生定海靴
                Array(1072365, 100, 1, 1),
				/*************
				套服
				*************/
				//重生演武铠
                Array(1052160, 100, 1, 1),
				//重生奥神袍
                Array(1052161, 100, 1, 1),
				//重生巡礼者
                Array(1052162, 100, 1, 1),
				//重生翻云服
                Array(1052163, 100, 1, 1),
				//重生霸七海
                Array(1052164, 100, 1, 1),
				/*************
				帽子
				*************/
				//重生冠军盔
                Array(1002790, 100, 1, 1),
				//重生玄妙帽
                Array(1002791, 100, 1, 1),
				//重生霓翎帽
                Array(1002792, 100, 1, 1),
				//重生迷踪帽
                Array(1002793, 100, 1, 1),
				//重生海王星
                Array(1002794, 100, 1, 1),
				/*************
				武器
				*************/
				//重生破甲剑
                Array(1302086, 100, 1, 1),
				//重生断蚺斧
                Array(1312038, 100, 1, 1),
				//重生惊破天
                Array(1322061, 100, 1, 1),
				//重生狂鲨锯
                Array(1332075, 100, 1, 1),
				//重生断首刃
				Array(1332076, 300, 1, 1),
				//重生蝶翼杖
                Array(1372045, 100, 1, 1),
				//重生冰轮杖
                Array(1382059, 100, 1, 1),
				//重生玄冥剑
                Array(1402047, 100, 1, 1),
				//重生碎鼋斧
                Array(1412034, 100, 1, 1),
				//重生威震天
                Array(1422038, 100, 1, 1),
				//重生显圣枪
                Array(1432049, 100, 1, 1),
				//重生神光戟
                Array(1442067, 100, 1, 1),
				//重生惊电弓
                Array(1452059, 100, 1, 1),
				//重生冥雷弩
                Array(1462051, 100, 1, 1),
				//重生大悲赋
                Array(1472071, 100, 1, 1),
				//重生孔雀翎
                Array(1482024, 100, 1, 1),
				//重生凤凰铳
                Array(1492025, 100, 1, 1)
                );

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
	if (status == 0) {
        var chance = Math.floor(Math.random() * +100);
        var finalitem = Array();
        for (var i = 0; i < itemList.length; i++) {
            if (itemList[i][1] >= chance) {
                finalitem.push(itemList[i]);
            }
        }
        if (finalitem.length != 0) {
            if (finalitem.length == 0) {
                return 1;
            }
            var item;
            var random = new java.util.Random();
            var finalchance = random.nextInt(finalitem.length);
            var itemId = finalitem[finalchance][0];
            var quantity = finalitem[finalchance][2];
            var notice = finalitem[finalchance][3];
            item = cm.gainGachaponItem3(itemId, quantity, "重生的谜之蛋", notice);
            if (item != -1) {
            } else {
                cm.playerMessage(1,"请你确认在背包的装备，消耗，其他窗口中是否有一格以上的空间。");
            }
            cm.safeDispose();
        } else {
            cm.playerMessage(1,"今天的运气可真差，什么都没有拿到。");
            cm.safeDispose();
        }
        cm.对话结束();
    }
}