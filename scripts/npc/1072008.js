/*
 ZEVMSð�յ�(079)��Ϸ�����
 ������תתְ�̹�
 */
 
var ����ͼ = 120000101;
function start() {
    status = -1;
    action(1, 0, 0)
}
function action(mode, type, selection) {
    if (status <= 0 && mode <= 0) {
        cm.dispose();
        return
    }
    if (mode == 1) {
        status++
    } else {
        status--

    }
    if (status <= 0) {
        var selStr = " \r\n";
		if (cm.getMapId() == 108000500) {
			selStr += "���Ƿ��Ѿ��ռ�����#b30�� #v4031857# #t4031857##k ��\r\n";
			selStr += "  #L1##b���Ѿ��ռ�����#k#l\r\n";
		} else if (cm.getMapId() == 108000502) {
			selStr += "���Ƿ��Ѿ��ռ�����#b30�� #v4031856# #t4031856##k ��\r\n";
			selStr += "  #L2##b���Ѿ��ռ�����#k#l\r\n";
		}
        selStr += "  #L3##b��Ҫ�뿪����#k#l\r\n";
        cm.sendSimple(selStr)
    } else if (status == 1) {
        switch (selection) {
            case 1:
                if (cm.haveItem(4031857, 30)) {
                    cm.warp(����ͼ, 0);
                    cm.removeAll(4031857);
                    cm.gainItem(4031012, 1);
                    cm.�Ի�����();
                } else {
                    cm.sendOk("���ռ�����#b30�� #v4031857# #t4031857##k ��");
                    cm.�Ի�����();
                }
                break;
			case 2:
                if (cm.haveItem(4031856, 30)) {
                    cm.warp(����ͼ, 0);
                    cm.removeAll(4031856);
                    cm.gainItem(4031012, 1);
                    cm.�Ի�����();
                } else {
                    cm.sendOk("���ռ�����#b30�� #v4031856# #t4031856##k ��");
                    cm.�Ի�����();
                }
                break;	
				
            case 3:
                cm.warp(����ͼ, 0);
                cm.�Ի�����();
                break;
        }
    }
}