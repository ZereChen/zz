/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű����ʺ絺ȥ�����
 */

var ��� = 150;
var status = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status >= 1 && mode == 0) {
        cm.sendNext("���Ƿ�Ҫ���͵��������");
        cm.dispose();
        return;
    }
    if (mode == 1)
        status++;
    else
        status--;
    if (status == 0) {
        cm.sendNext("��ã�����Ҫȥ#b�����#k��");
    } else if (status == 1) {
        var job = cm.getJob();
            cm.sendYesNo("֧���� #v5200000# #b150#k ����Ҿ������ȥ��");
            ��� = 150;
        
    } else if (status == 2) {
        if (cm.getMeso() < ���) {
            cm.sendNext("���Ҳ�����!")
        } else {
            cm.gainMeso(-���);
            cm.warp(2010000, 0);
        }
        cm.dispose();
    }
}