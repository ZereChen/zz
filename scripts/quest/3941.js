/*
 ZEVMS冒险岛(079)游戏服务端
 */
var status = -1;

function start(mode, type, selection) {
	if (qm.判断背包其他栏().isFull()) {
        qm.sendNext("其他栏必须有一个空位。");
        qm.对话结束();
        return;
    }
	qm.给物品(4031571,1);
	qm.任务完成(3941);
	qm.dispose();
}
function end(mode, type, selection) {
	if (qm.判断背包其他栏().isFull()) {
        qm.sendNext("其他栏必须有一个空位。");
        qm.对话结束();
        return;
    }
	qm.给物品(4031571,1);
	qm.任务完成(3941);
	qm.dispose();
}