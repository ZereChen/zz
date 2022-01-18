function act() {
	var 绯红石像值 = rm.GetPiot("绯红石像值",1);

	rm.GainPiot("绯红石像值","1",1);
	rm.mapMessage(6, "绝杀暗爪·激活");
	if(绯红石像值>=4){
		rm.getMap().changeEnvironment("5pt", 2);
		rm.mapMessage(6, "通往下一关的通道打开了");
		rm.openNpc(9120054,2);
	}
	/*var em = rm.getEventManager("CWKPQ");
	if (em != null) {
		rm.mapMessage(6, "A weapon has been restored to the Relic of Mastery!");
		em.setProperty("glpq5", parseInt(em.getProperty("glpq5")) + 1);
		if (em.getProperty("glpq5").equals("5")) { //all 5 done
			rm.mapMessage(6, "The Antellion grants you access to the next portal! Proceed!");
			rm.getMap().changeEnvironment("5pt", 2);
		}
	}*/
}