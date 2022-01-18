/*
 ZEVMS冒险岛(079)游戏服务端
 */
function enter(pi) {
	if(pi.getQuestStatus(2314)== 2 ){
		pi.warp(106020400,2);
		return true;
	}
	return false;
}