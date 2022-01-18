function act(){
	if(rm.getPlayer().getInventory(rm.getInvType(-1)).findById(1322071)){
	rm.setBossRankCount2("挖矿经验",1);		
	rm.setBossRankCount("挖矿",1);
	rm.setBossLog("挖矿");
	rm.dropItems();
	}else{
	rm.playerMessage(5,"[挖矿信息]:你未装备挖矿铁锹，所以你挖矿失败了。"); 	
	}
}