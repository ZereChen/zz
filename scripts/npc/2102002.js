/*
 ZEVMSð�յ�(079)��Ϸ�����
 */
var cost = 6000;
var status = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if(mode == -1) {
        cm.dispose();
    } else {
        if(mode == 1) {
            status++;
        }
        if(mode == 0) {
            cm.sendNext("����һЩ���õĸ������޷���԰�?");
            cm.dispose();
            return;
        }
        if(status == 0) {
    cm.sendYesNo("		���,��������˹�������뿪��ϣɳĮ�����֮����? ��Ҫ����#b "+cost+" #k��� ����#b#t4031045##k �ſ���������");
        } else if(status == 1) {
            if(cm.getMeso() >= cost && cm.canHold(4031045)) {
                cm.gainItem(4031045,1);
                cm.gainMeso(-cost);
            } else {
                cm.sendOk("�������� #b"+cost+" ���#k? ����еĻ�,��Ȱ�����������������λ���Ƿ���û������.");
            }
            cm.dispose();
        }
    }
}
