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
        var selStr = "	Hi~#b#h ##k����Ҫ��ѩ�����ҿ�����ô��С����һ����ù����ɣ�ȥ�����С�����һ������ս�ɡ�\r\n\r\n";
			selStr += " #L2##b��ʼ��ѩ��#k#l\r\n";

        cm.˵������(selStr);
    } else if (status == 1) {
        switch (selection) {
			case 2:
				cm.warp(209080000,4);
                cm.dispose();
            break;
        }
    }
}