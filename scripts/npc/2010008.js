/*
 ZEVMSð�յ�(079)��Ϸ�����
 */
var status = 0;
var sel;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1)
        status++;
    else
        status--;

    if (status == 0)
        cm.sendSimple("  Hi~#b#h ##k��һ��Ư����΢�¸�������һ��������ص㣬��Ҫ��Ҫѡһ����\r\n\r\n#b#L0#����/���Ļ���#l#k");
    else if (status == 1) {
        sel = selection;
        if (selection == 0) {
            if (cm.getPlayerStat("GRANK") == 1)
                cm.sendYesNo("���´���һ��������Ҫ #b5,000,000���#k����ȷ��Ҫ������");
            else
                cm.sendOk("���칫�������Ҫ���᳤�����Ҳ���ร������ǵĹ��᳤�����Ұ�~");
        }

    } else if (status == 2) {
        if (sel == 0) {
            cm.genericGuildMessage(17);
            cm.dispose();
        }
    }
}