/*
 ZEVMSð�յ�(079)��Ϸ�����
 */
var status = 0;
var cost;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status >= 1 && mode == 0){
	cm.sendNext("�����Ҳ�кܶ��ṩ���ҵ����������������б�Ҫȥ���������");
	cm.dispose();
	return;	
    }
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
	cm.sendNext("��ã����ǳ��⳵ֻ��VIP�ͻ���������ֻ���㵽��ͬ�ĳ�����������⳵�������ṩһ�����õķ���ֵ�ù�����ġ�����һ���е�󣬵��Ƕ���......ֻ��10,000 ��ң����ǻ���㰲ȫ���͵�\n #b���Ͽ��#k.");
    } else if (status == 1) {
	var job = cm.getJob();
	if (job == 0 || job == 2000 || job == 1000) {
	    cm.sendYesNo("�����ж�����90%�Ĵ��� ������ֻ��Ҫ�� #b1,000 ���#k �Ƿ�Ҫȥ����??");
	    cost = 1000;
	} else {
	    cm.sendYesNo("�����Ǳ��и�24Сʱ���ŵ����Թ���Ʒ #b10,000 ���#k?");
	    cost = 10000;
	}
    } else if (status == 2) {
	if (cm.getMeso() < cost) {
	    cm.sendNext("��ȷ�����Ƿ����㹻�Ľ��!")
	} else {
	    cm.gainMeso(-cost);
	    cm.warp(105070001, 0);
	}
	cm.dispose();
    }
}