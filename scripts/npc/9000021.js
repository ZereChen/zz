/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű���������
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
        var selStr = "  Hi~#b#h ##k �����ռ���#rδ֪������#k��������еĻ����ǵð��������ҡ�\r\n\r\n";
		
		//selStr += ""+cm.��ʾ��Ʒ(1142124)+"\r\n";
		selStr += " #L1##b�һ� "+cm.��ʾ��Ʒ(1142125)+"#l\r\n";
		selStr += " #L2##b�һ� "+cm.��ʾ��Ʒ(1002980)+"#l\r\n";
		selStr += " #L3##b�һ� "+cm.��ʾ��Ʒ(1022088)+"#l\r\n";
        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.dispose();
				cm.��NPC(9000021,1);
                break;
			case 2:
                cm.dispose();
				cm.��NPC(9000021,2);
                break;
			case 3:
                cm.dispose();
				cm.��NPC(9000021,3);
                break;
        }
    }
}