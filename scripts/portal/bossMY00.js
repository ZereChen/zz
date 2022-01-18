function enter(pi) {
	/*if (pi.getPlayer().getClient().getChannel() != 1 && pi.getPlayer().getClient().getChannel() != 2) {
		pi.playerMessage(5, "1 频道或者 2 频道 挑战试试？");
		return false;
	}*/
	if(pi.getMap(551030200).isDisconnected(pi.getPlayer().getId())){
			pi.playPortalSE();
			pi.warp(551030200, "sp");
	}
	if (pi.getBossLog("暴力熊") >= pi.GetZ毒雾森林("暴力熊", "1")) {
		pi.playerMessage(5, "一天只能打 "+pi.GetZ毒雾森林("暴力熊", "1")+" 次暴力熊。");
		return false;
	}
    if (!pi.haveItem(4032246)) {
	pi.playerMessage(5, "你没有梦幻主题娃娃，所以无法挑战.");
    } else {
	if (pi.getPlayerCount(551030200) <= 0) {
			var FantMap = pi.getMap(551030200);
			pi.setBossLog("暴力熊");
			FantMap.resetFully();
			pi.playPortalSE();
			pi.warp(551030200, "sp");	
	} else {
	    if (pi.getMap(551030200).getSpeedRunStart() == 0 && (pi.getMonsterCount(551030200) <= 0)) {
		pi.playPortalSE();
		pi.setBossLog("暴力熊");
		pi.warp(551030200, "sp");
		}else{
		pi.playerMessage(5, "战斗已经开始，所以你可能不会进入这个地方.");
	    }
	}
    }
}