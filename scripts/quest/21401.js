var status = -1;

function start(mode, type, selection) {
	qm.任务开始(21401);
	qm.warp(914020000,0);
	qm.spawnMob_map(9001014, 914020000, -433, 86);
	qm.dispose();
}

function end(mode, type, selection) {
	qm.任务完成(21401);
	qm.changeJob(2112);
	qm.给物品(1142132,1);
	qm.dispose();
}