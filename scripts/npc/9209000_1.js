/*
 ZEVMSð�յ�(079)��Ϸ�����
 */
 
var �һ���Ʒ = 4031250;


importPackage(Packages.client);
importPackage(Packages.client.inventory);
var status = -1;
var beauty = 0;
var tosend = 0;
var sl;
var mats;
function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.�Ի�����();
    } else {
        if (mode == 0 && status == 0) {
            cm.�Ի�����();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            if (status == 0) {
                cm.sendNext("�����Ҫ����н�����������Ұɡ�");
                cm.�Ի�����();
            }
            status--;
        }
        if (status == 0) {
            var e = "";
            e += "  �һ���Ʒ��" + cm.��ʾ��Ʒ(�һ���Ʒ) + "\r\n";
            e += "  �һ�����:#r1:1#k\r\n";
            e += "  ��ǰ���:#r" + cm.getPlayer().getCSPoints(1) + "#k\r\n\r\n";
            e += "#L0##b��Ҫ�һ�#l\r\n\r\n";
            cm.sendSimple(e);
        } else if (status == 1) {
            if (selection == 0) {
                    beauty = 1;
                    cm.sendGetNumber("��������Ҫ�һ��ĵ�ȯ������\r\n", 1, 1, 10000);
            } 
        } else if (status == 2) {
            if (beauty == 1) {
                if (selection <= 0) {
                    cm.˵������("����Ķһ����ִ���");
                    cm.�Ի�����();
                } else if (cm.�ж���Ʒ����(�һ���Ʒ,selection)) {
                    cm.����ȯ(selection);
                    cm.����Ʒ(�һ���Ʒ, selection);
                    cm.˵������("���ɹ��� " + cm.��ʾ��Ʒ(�һ���Ʒ) + " x #b"+selection+"#k �һ� #r" + selection + "#k ��ȯ��")
                } else {
                    cm.sendNext("��û���㹻�һ���Ʒ��");
                    cm.�Ի�����();
                }
            } 
            status = -1;
        } else {
            cm.�Ի�����();
        }
    }
}