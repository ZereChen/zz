/*
 ZEVMSð�յ�(079)��Ϸ�����
 */
var status = 0;
var skin = Array(0, 1, 2, 3, 4);
var price;

function start() {
    cm.sendSimple("��ӭ���������ִ廤������! �Ƿ�����ҪȾ��Ƥ����?? �����ҵĽ���Ƥ��??  ������� #b#t5153000##k, �Ϳ�������Ⱦ���뵽��Ƥ��~~~\r\n\#L2#���Ѿ���һ���Ż�ȯ!#l");
}

function action(mode, type, selection) {
    if (mode < 1)
        cm.dispose();
    else {
        status++;
        if (status == 1)
            cm.sendStyle("ѡһ����Ҫ�ķ��.",5153000, skin);
        else {
            if (cm.haveItem(5153000)){
                cm.gainItem(5153000, -1);
                cm.setSkin(selection);
                cm.sendOk("����!");
            } else
                cm.sendOk("��ò��û��#b#t5153000##k..");
            cm.dispose();
        }
    }
}
