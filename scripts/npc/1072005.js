/*
 ZEVMSð�յ�(079)��Ϸ�����
 ħ��ʦ��תתְ�̹�
 */
 
var ����ͼ = 101020000;
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
        var selStr = "���Ƿ��Ѿ��ռ�����#b30�� #v4031013# #t4031013##k ��\r\n";
        selStr += "  #L1##b���Ѿ��ռ�����#k#l\r\n";
        selStr += "  #L2##b��Ҫ�뿪����#k#l\r\n";
        cm.sendSimple(selStr)
    } else if (status == 1) {
        switch (selection) {
            case 1:
                if (cm.haveItem(4031013, 30)) {
                    cm.warp(����ͼ, 0);
                    cm.removeAll(4031013);
                    cm.gainItem(4031009, -1);
                    cm.gainItem(4031012, 1);
                    cm.�Ի�����();
                } else {
                    cm.sendOk("���ռ�����#b30�� #v4031013# #t4031013##k ��");
                    cm.�Ի�����();
                }
                break;
            case 2:
                cm.warp(����ͼ, 0);
                cm.�Ի�����();
                break;
        }
    }
}