/*
 ZEVMS冒险岛(079)游戏服务端
 永恒的谜之蛋
 4280000
 5490000
 */
var status = 0;
//物品ID，抽奖概率，物品数量，(0/不广播，1广播)
var itemList =
        Array(	
				/*************
				手套
				*************/
				//永恒定边手套
                Array(1082234, 100, 1, 1),
				//永恒逍遥手套
                Array(1082235, 100, 1, 1),
				//永恒白云手套
                Array(1082236, 100, 1, 1),
				//永恒探云手套
                Array(1082237, 100, 1, 1),
				//永恒抚浪手套
                Array(1082238, 100, 1, 1),
				/*************
				鞋子
				*************/
				//永恒坚壁靴
                Array(1072355, 100, 1, 1),
				//永恒缥缈鞋
                Array(1072356, 100, 1, 1),
				//永恒彩虹鞋
                Array(1072357, 100, 1, 1),
				//永恒舞空靴
                Array(1072358, 100, 1, 1),
				//永恒定海靴
                Array(1072359, 100, 1, 1),
				/*************
				套服
				*************/
				//永恒演武铠
                Array(1052155, 100, 1, 1),
				//永恒奥神袍
                Array(1052156, 100, 1, 1),
				//永恒巡礼者
                Array(1052157, 100, 1, 1),
				//永恒翻云服
                Array(1052158, 100, 1, 1),
				//永恒霸七海
                Array(1052159, 100, 1, 1),
				/*************
				帽子
				*************/
				//永恒冠军盔
                Array(1002776, 100, 1, 1),
				//永恒玄妙帽
                Array(1002777, 100, 1, 1),
				//永恒霓翎帽
                Array(1002778, 100, 1, 1),
				//永恒迷踪帽
                Array(1002779, 100, 1, 1),
				//永恒海王星
                Array(1002780, 100, 1, 1),
				/*************
				武器
				*************/
				//永恒破甲剑
                Array(1302081, 100, 1, 1),
				//永恒断蚺斧
                Array(1312037, 100, 1, 1),
				//永恒惊破天
                Array(1322060, 100, 1, 1),
				//永恒狂鲨锯
                Array(1332073, 100, 1, 1),
				//永恒断首刃
				Array(1332074, 300, 1, 1),
				//永恒蝶翼杖
                Array(1372044, 100, 1, 1),
				//永恒冰轮杖
                Array(1382057, 100, 1, 1),
				//永恒玄冥剑
                Array(1402046, 100, 1, 1),
				//永恒碎鼋斧
                Array(1412033, 100, 1, 1),
				//永恒威震天
                Array(1422037, 100, 1, 1),
				//永恒显圣枪
                Array(1432047, 100, 1, 1),
				//永恒神光戟
                Array(1442063, 100, 1, 1),
				//永恒惊电弓
                Array(1452057, 100, 1, 1),
				//永恒冥雷弩
                Array(1462050, 100, 1, 1),
				//永恒大悲赋
                Array(1472068, 100, 1, 1),
				//永恒孔雀翎
                Array(1482023, 100, 1, 1),
				//永恒凤凰铳
                Array(1492023, 100, 1, 1)
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
            item = cm.gainGachaponItem2(itemId, quantity, "永恒的谜之蛋", notice);
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