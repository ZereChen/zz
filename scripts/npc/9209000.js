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
		
        var selStr = "	Hi~#b#h ##k�����н����ˣ�������ʲô������Ҫ���Ҷһ����������ҿɲ������á�\r\n\r\n";
		
		selStr += "#L1##b�һ���ȯ#k#l\r\n";

        cm.˵������(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.dispose();
                cm.openNpc(9209000, 1);
                break;
        }
    }
}






















