/*
 ZEVMSð�յ�(079)��Ϸ�����
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
        var selStr = "	Hi~#b#h ##k���ǻ�ȥ��������������������ǲ��Ǻ�Ư����С��ׯ����ӭ������Ŷ��\r\n\r\n";
		if(cm.�жϵ�ͼ()==209000000){
        selStr += " #L1##b��Ҫ��ȥ��#k#l\r\n";
		//selStr += " #L2##bȥ����#k#l\r\n";
		selStr += " #L3##b���������#k#l\r\n";
		}

        cm.˵������(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.dispose();
				cm.��NPC(2007,8);
                break;
			case 2:
				cm.warp(889100100,1);
                cm.dispose();
                break;
			case 3:
				cm.warp(209080100,2);
                cm.dispose();
                break;	
				
        }
    }
}