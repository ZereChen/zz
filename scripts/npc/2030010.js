/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű��������˳�
 */

function start() {
    cm.sendYesNo("����������뿪���㽫���ò����¿�ʼ��");
}

function action(mode, type, selection) {
    if (mode == 1) {
		cm.Gaincharacterz("" + cm.getPlayer().id + "", 200, -cm.Getcharacterz("" + cm.getPlayer().id + "", 200));
		cm.warp(211042300);
    }
    cm.dispose();
}