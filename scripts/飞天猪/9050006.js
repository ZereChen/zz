/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű���������-ˮ������
 ��Ӧ�ĵ���4170007
 */
var ���� = "ˮ������";
var status = 0;
//��ƷID���齱���ʣ���Ʒ������(0/���㲥��1�㲥)
var itemList =
        Array(
                Array(2000000, 1000, 1, 1),
                Array(2000001, 1000, 1, 1),
                Array(2000002, 1000, 1, 1),
                Array(2000003, 1000, 1, 1),
				Array(2000004, 300, 1, 1),
                Array(2000005, 100, 1, 1),
				Array(4170007, 100, 1, 1)//����
				
                );

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.sendOk("��û�� " + cm.��ʾ��Ʒ(5220040) + " ��");
            cm.dispose();
        }
        status--;
    }
    if (status == 0) {
        if (cm.haveItem(5220040, 1)) {
            var str1 = "";
            for (var i = 0; i < itemList.length; i++) {
                str1 += "#v" + itemList[i][0] + "#";
            }
            cm.sendYesNo("����#b"+����+"#k�ķ�������� #b#h ##k ���� " + cm.��ʾ��Ʒ(5220040) + " �Ϳ��Գ齱�ˡ�");
        } else {
            var str1 = "\r\n";
            for (var i = 0; i < itemList.length; i++) {
                str1 += "#v" + itemList[i][0] + "#";
            }
            cm.sendOk("����#b"+����+"#k�ķ�������û�� " + cm.��ʾ��Ʒ(5220040) + " ! ���������������Ұѡ�");
            cm.safeDispose();
        }
    } else if (status == 1) {
        var chance = Math.floor(Math.random() * +100);
        var finalitem = Array();
        for (var i = 0; i < itemList.length; i++) {
            if (itemList[i][1] >= chance) {
                finalitem.push(itemList[i]);
            }
        }
        if (finalitem.length != 0) {
            if (finalitem.length == 0) {
                return 1;
            }
            var item;
            var random = new java.util.Random();
            var finalchance = random.nextInt(finalitem.length);
            var itemId = finalitem[finalchance][0];
            var quantity = finalitem[finalchance][2];
            var notice = finalitem[finalchance][3];
            item = cm.gainGachaponItem(itemId, quantity, "������", notice);
            if (item != -1) {
                cm.gainItem(5220040, -1);
                cm.sendOk("������ " + cm.��ʾ��Ʒ(item) + " x #b"+quantity+"#k");
            } else {
                cm.sendOk("����ȷ���ڱ�����װ�������ģ������������Ƿ���һ�����ϵĿռ䡣");
            }
            cm.safeDispose();
        } else {
            cm.sendOk("�������������ʲô��û���õ���");
            cm.safeDispose();
        }
        cm.safeDispose();
    }
}