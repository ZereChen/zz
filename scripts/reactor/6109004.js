function act() {
	var 绯红图腾值 = rm.GetPiot("绯红图腾值",1);
	rm.mapMessage(6, "成功启动海盗图腾");
	rm.GainPiot("绯红图腾值","1",1);
	if(绯红图腾值>=4){
		rm.getMap().changeEnvironment("2pt", 2);
		rm.mapMessage(6, "通往下一关的通道打开了");
		rm.openNpc(9120054,2);
	}
}