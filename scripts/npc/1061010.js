/*
 ZEVMSð�յ�(079)��Ϸ�����
 */
function start() {
    cm.sendYesNo("�������Ƿ�Ҫ�뿪�أ�");
}

function action(mode, type, selection) {
    if (mode == 1) {
        var map = cm.getMapId();
        var kill = cm.getMap().killAllMonsters(true);
        var tomap;
		//������ ɭ���Թ�
        if (map == 108010101) {
            kill;
            tomap = 105040305;
		//ħ��ʦ ����ɭ��II
        } else if (map == 108010201) {
            kill;
            tomap = 100040106;
		//սʿ ���Ϲ㳡
        } else if (map == 108010301) {
            kill;
            tomap = 105070001;
		//���� ��������
        } else if (map == 108010401) {
            kill;
            tomap = 107000402;
		//���� ������޶�Ѩ
        } else if (map == 108010501) {
            kill;
            tomap = 105070200;
        }
        cm.warp(tomap);
    }
    cm.dispose();
}