/*
 ZEVMSð�յ�(079)��Ϸ�����
 */
var status = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status >= 0 && mode == 0) {
	cm.sendOk("û�������ѵ����? �����Ҫ�뿪����, �벻Ҫ���ĵĸ����ҡ�.");
	cm.dispose();
	return;
    }
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
	cm.sendYesNo("��������ѵ������? �������Ҫ�뿪�Ļ����ҿ��Դ����뿪��");
    } else if (status == 1) {
	cm.sendNext("����Ҫ�����뿪��� ���ͣ�");
    } else if (status == 2) {
	cm.warp(3, 0);
	cm.dispose();
    }
}