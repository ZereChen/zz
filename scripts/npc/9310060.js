/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�����Ե
 */
var JT = "#fUI/Basic/BtHide3/mouseOver/0#";
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
        var selStr = "	  Hi~#b#h ##k������Ե����������ʲô����������ð����������ж��ٲ�Ϊ��֪�����ܣ����в�Ϊ�����ĺڰ���\r\n";
		
		selStr += " #L2##bÿ��ǩ��#k#l\r\n";
		selStr += " #L3##b���߽���(����)#k#l\r\n";
		selStr += " #L4##b���߽���(ȫ��)#k#l\r\n";
		selStr += " #L5##b���߽���(����)#k#l\r\n";
        selStr += " #L1##r[����]#bȥ�ֹ��լۡ#k#l\r\n";
		selStr += " #L6##r[ÿ��]#bð���ռ�#k#l\r\n";
	

        cm.˵������(selStr);
    } else if (status == 1) {
        switch (selection) {
			case 6:
                cm.dispose();
				cm.��NPC(9310060,5);
                break;
			case 5:
                cm.dispose();
				cm.��NPC(9310060,4);
                break;
			case 4:
                cm.dispose();
				cm.��NPC(9310060,3);
                break;
			case 3:
                cm.dispose();
				cm.��NPC(9310060,2);
                break;
			case 2:
                cm.dispose();
				cm.��NPC(9310060,1);
                break;
            case 1:
                cm.dispose();
				cm.��NPC(2007,6);
                break;
        }
    }
}