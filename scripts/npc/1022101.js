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
        var selStr = "  Hi~#b#h ##k��������ʲô�������Ҹ����Ǳߵ����ǹ����Ҹ�����������ѩ�����ģ�����Ҫ�Լ�ȥ�����\r\n\r\n";
        selStr += " #L1##b��Ҫȥ�Ҹ���#k#l\r\n";
		

        cm.˵������(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.dispose();
				cm.��NPC(2007,8);
                break;
        }
    }
}