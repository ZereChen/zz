/*
 ZEVMSð�յ�(079)��Ϸ�����
 */
var status = 0;
var �һ��ɹ��� = 10;
var itemList =
        Array(	//�س�
				Array(2040006, 500, 1, 1),
				Array(2040007, 500, 1, 1),
				Array(2040303, 500, 1, 1),
				Array(2040403, 500, 1, 1),
				Array(2040506, 500, 1, 1),
				Array(2040507, 500, 1, 1),
				Array(2040603, 500, 1, 1),
				Array(2040709, 500, 1, 1),
				Array(2040710, 500, 1, 1),
				Array(2040711, 500, 1, 1),
				Array(2040806, 500, 1, 1),
				Array(2040903, 500, 1, 1),
				Array(2041024, 500, 1, 1),
				Array(2041025, 500, 1, 1),
				Array(2043003, 500, 1, 1),
				Array(2043103, 500, 1, 1),
				Array(2043203, 500, 1, 1),
				Array(2043303, 500, 1, 1),
				Array(2043703, 500, 1, 1),
				Array(2043803, 500, 1, 1),
				Array(2044003, 500, 1, 1),
				Array(2044019, 500, 1, 1),
				Array(2044103, 500, 1, 1),
				Array(2044203, 500, 1, 1),
				Array(2044303, 500, 1, 1),
				Array(2044403, 500, 1, 1),
				Array(2044503, 500, 1, 1),
				Array(2044603, 500, 1, 1),
				Array(2044703, 500, 1, 1),
				Array(2044815, 500, 1, 1),
				Array(2044908, 500, 1, 1)

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
            cm.sendOk("��û�� " + cm.��ʾ��Ʒ(4000464) + " ��");
            cm.dispose();
        }
        status--;
    }
    if (status == 0) {
        if (cm.haveItem(4000464, 1)) {
            var str1 = "";
            for (var i = 0; i < itemList.length; i++) {
                str1 += "" + cm.��ʾ��Ʒ(itemList[i][0]) + "\r\n";
            }
            cm.sendYesNo("��� #b#h ##k ���� " + cm.��ʾ��Ʒ(4000464) + " �һ�#rһ������#k���������������Щ������\r\n\r\n"+str1+"");
        } else {
            var str1 = "\r\n";
            for (var i = 0; i < itemList.length; i++) {
                str1 += "" + cm.��ʾ��Ʒ(itemList[i][0]) + "\r\n";
            }
            cm.sendOk("��û�� " + cm.��ʾ��Ʒ(4000464) + " ! ���������������Ұѡ�\r\n\r\n"+str1+"\r\n");
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
            item = cm.gainGachaponItem(itemId, quantity, "ð�յ���ӪԱ", notice);
			
            if (item != -1) {
                cm.gainItem(4000464, -1);
				if(cm.�ٷ���(�һ��ɹ���)){
					cm.sendOk("������ " + cm.��ʾ��Ʒ(item) + " x #b"+quantity+"#k");
				}else{
					cm.sendOk("��������ʲô��û���õ���");
				}
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