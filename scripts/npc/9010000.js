/*
 ZEVMSð�յ�(079)��Ϸ�����
 */
//�ز�����
var JT = "#fUI/Basic/BtHide3/mouseOver/0#";
var �� = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
var r = "#fUI/UIWindow.img/Quest/TimeQuest/AlarmClock/default/0/0#";

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
	//��ʼ
    if (status == 0) {
		
        var selStr = "	Hi~#b#h ##k����"+cm.��������()+"����ӪԱ����������ʲô�أ�����Ҫ�ٱ��𣿹�������\r\n\r\n";
		
		selStr += "#L1##b�һ���������#k#l\r\n";
		selStr += "#L2##b�ռ���ϯͼ��#k#l\r\n";
		selStr += "#L4##b�����й���#k#l\r\n";
		selStr += "#L3##b���ϻ�Ӵ#k#l\r\n";
        cm.˵������(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.dispose();
                cm.openNpc(9010000, 1);
                break;
			case 2:
                cm.dispose();
                cm.openNpc(9010000, 2);
                break;
			case 3:
                cm.dispose();
                cm.openNpc(9010000, 3);
                break;
			case 4:
                cm.dispose();
                cm.openNpc(9010000, 4);
                break;
        }
    }
}