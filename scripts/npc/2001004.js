/*
 ZEVMSð�յ�(079)��Ϸ�����
 */


function start() {
    cm.sendYesNo("����ķ羰��ô�� �����Ҫ��ȥ��");
}

function action(mode, type, selection) {
    if (mode == 1) {
        cm.warp(209000000, 9);
    } else {
        cm.sendNext("����Ҫ�����ʱ��װ����, ��? �������Ҫ�뿪����ط�����ʱ������������˵��?");
    }
    cm.dispose();
}