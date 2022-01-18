/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：召唤扎昆
 */


function act() {
    rm.changeMusic("Bgm06/FinalFight");
	rm.getMap().spawnZakum(-10, -215);
    rm.mapMessage("扎昆在祭坛上现身了。");
	if (!rm.getPlayer().isGM()) {
		rm.getMap().startSpeedRun();
	}
}