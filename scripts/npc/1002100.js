/*
 ZEVMSð�յ�(079)��Ϸ�����
 */
var status = -1;
var amount = -1;
var items = [[2000002,310],[2022003,1060],[2022000,1600],[2001000,3120]];
var item;

function start() {
    if (cm.getQuestStatus(2013))
        cm.sendNext("������...лл�㣬���ܵõ��ܶ���ɡ��������Ѿ�����һ����Ʒ���������Ҫʲô������֪��.");
    else {
        if (cm.getQuestStatus(2010))
            cm.sendNext("���ƺ�û��ǿ�������ܹ������ҵ�ҩˮ......");
        else
            cm.sendOk("��Ҫ�������ſ��Ը�����ҩˮ�!");
        cm.dispose();
    }
}

function action(mode, type, selection) {
    status++;
    if (mode != 1){
        if(mode == 0 && type == 1)
            cm.sendNext("����Ȼ�в�������ǰ���ҵĲ��ϡ���Щ��Ŀ���������������ʱ��ѡ��...");
        cm.dispose();
        return;
    }
	cm.dispose();
}