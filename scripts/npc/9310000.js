/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű���ȥ�Ϻ�
 */

var status = 0;
function start() {
    cm.sendYesNo("�����Ƿ���ȥ�Ϻ�̲??");
}

function action(mode, type, selection) {
    if (mode != 1) {
        if (mode == 0)
        cm.sendOk("��Ȼ�㲻Ҫ�Ǿ����ˡ�");
        cm.dispose();
        return;
    }
    status++;
    if (status == 1) {
		cm.warp(701000100, 0);
        cm.dispose();
    
	}
}