/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű��������б�
 */
var status = -1;
var ��� = 250000;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status == 0) {
	    cm.sendNext("���к�����");
	    cm.�Ի�����();
	    return;
	} else if (status >= 1) {
	    cm.sendNext("�Ҳ���Ϊ��û�����ѣ���ֻ�ǲ��뻨 #v5200000# #b"+���+"#k ��� �������Լ��ĺ�����!");
	    cm.�Ի�����();
	    return;
	}
	status--;
    }
    if (status == 0) {
	cm.sendYesNo("    ��ĺ��������ˣ��ҿ���Ϊ������λ��Ŷ��������û��� #v5200000# #b"+���+"#k ��ҡ� ");
    } else if (status == 1) {
	var capacity = cm.getBuddyCapacity();
	if (capacity >= 100 || cm.getMeso() < ���) {
	    cm.sendNext("�� ��ȷ������ #v5200000# #b"+���+"#k ��� ����㹻ȷ���ǲ�����ĺ������Ѿ� #b100#k ����..");
	} else {
	    var newcapacity = capacity + 5;
	    cm.gainMeso(-���);
	    cm.updateBuddyCapacity(newcapacity);
	    cm.sendOk("�����Ѿ�������5���������ˣ�����㻹��Ҫ�����������ң���Ȼ����������ѵ�!");
	}
	cm.�Ի�����();
    }
}