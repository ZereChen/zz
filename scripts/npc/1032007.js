/*
 ZEVMSð�յ�(079)��Ϸ�����
 ħ��������ͷȥ���֮��
 101000300
 */
var status = 0;
var ��� = 5000;

function start() {
    cm.sendYesNo("      #b#h ##k��ã�������ͷ����Ա�����������뿪������ȥ���֮����? ��Ҫ���� #b" + ��� + " ���#k ����#b#z4031045##k �ſ����ϴ�Ŷ��");
}

function action(mode, type, selection) {
    if (mode == -1)
        cm.dispose();
    else {
        if (mode == 0) {
            cm.sendNext("����һЩ���õĸ������޷���԰�?");
            cm.dispose();
            return;
        }
        status++;
        if (status == 1) {
            if (cm.getMeso() >= ��� && cm.canHold(4031045)) {
                cm.gainItem(4031045, 1);
                cm.gainMeso(-���);
                cm.dispose();
            } else {
                cm.sendOk("�������� #b" + ��� + " ���#k? ����еĻ�,��Ȱ�����������������λ���Ƿ���û������.");
                cm.dispose();
            }
        }
    }
}