/*
 ZEVMSð�յ�(079)��Ϸ�����
 */
var status = -1;
var sel;

function start() {
    cm.sendNext("�������Ƿ��мǵù���Ʊ?");
}

function action(mode, type, selection) {
    if (mode < 1) {
        cm.dispose();
        return;
    }
    status++;
    if (status == 0)
        cm.sendSimple("������Ҫȥ�ĸ�վ̨��#b\r\n\r\n#L0#ȥħ��ɭ��#l\r\n#L1#ȥ��߳�#l\r\n#L2#ȥ��ľ��#l\r\n#L3#ȥ�һ��ɾ�#l\r\n#L4#ȥ��ϣɳĮ#l\r\n#L5#ȥҮ�׸�#l");
    else if (status == 1) {
        sel = selection;
        cm.sendNext("�ҽ����㵽 #m" + (200000110 + (sel * 10)) + "#");
    } else if(status == 2){
        cm.warp(200000110 + (sel * 10));
        cm.dispose();
    }
}