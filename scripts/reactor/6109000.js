function act() {
	var 绯红图腾值 = rm.GetPiot("绯红图腾值",1);
	rm.mapMessage(6, "成功启动战士图腾");
	rm.GainPiot("绯红图腾值","1",1);
	if(绯红图腾值>=4){
		rm.getMap().changeEnvironment("2pt", 2);
		rm.mapMessage(6, "通往下一关的通道打开了");
		rm.openNpc(9120054,2);
	}
	/*var em = rm.getEventManager("CWKPQ");
	if (em != null) {
		if (rm.getMap().getId() == 610030200) {
			rm.mapMessage(6, "The Warrior Sigil has been activated!");
			em.setProperty("glpq2", parseInt(em.getProperty("glpq2")) + 1);
			if (em.getProperty("glpq2").equals("5")) { //all 5 done
				rm.mapMessage(6, "The Antellion grants you access to the next portal! Proceed!");
				rm.getMap().changeEnvironment("2pt", 2);
			}
		} else if (rm.getMap().getId() == 610030300) {
			rm.mapMessage(6, "The Warrior Sigil has been activated! You hear gears turning! The Menhir Defense System is active! Run!");
	    		em.setProperty("glpq3", parseInt(em.getProperty("glpq3")) + 1);
			rm.getMap().moveEnvironment("menhir0", 1);
	    		if (em.getProperty("glpq3").equals("10")) {
				rm.mapMessage(6, "The Antellion grants you access to the next portal! Proceed!");
				rm.getMap().changeEnvironment("3pt", 2);
	    		}
		}
	}*/
}