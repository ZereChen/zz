/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű����ͻ�
 */
var a = "#fUI/Basic/BtHide3/mouseOver/0#";
var �� = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
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
    if (status == 0) {
        var selStr = "  Hi~#b#h ##k ����Ҫ�ͻ��𣿱�Ǹ����ʱ���ṩ����\r\n\r\n";
        cm.�Ƿ�˵������(selStr);
    } else if (status == 1) {
        var selStr = "�ͻ�ע�����#b\r\n";
        selStr += "1.�ͻ��ڼ䲻�ɴ��͡�\r\n";
        selStr += "2.�ͻ��������ߣ����ߺ���Ϊ������\r\n";
        selStr += "3.�ͻ����ɸ���Ƶ����\r\n";
        cm.�Ƿ�˵������(selStr);
    } else if (status == 2) {
        var selStr = "���ڿ���������\r\n";
		selStr += "\r\n";


        cm.˵������(selStr);
    } else if (status == 3) {
        switch (selection) {
            case 1:
                cm.dispose();
                break;
        }
    }
}