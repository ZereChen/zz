/*
 ZEVMSð�յ�(079)��Ϸ�����
 �������֮��
 4280000
 5490000
 */
var status = 0;
//��ƷID���齱���ʣ���Ʒ������(0/���㲥��1�㲥)
var itemList =
        Array(	
				/*************
				����
				*************/
				//���㶨������
                Array(1082234, 100, 1, 1),
				//������ң����
                Array(1082235, 100, 1, 1),
				//�����������
                Array(1082236, 100, 1, 1),
				//����̽������
                Array(1082237, 100, 1, 1),
				//���㸧������
                Array(1082238, 100, 1, 1),
				/*************
				Ь��
				*************/
				//������ѥ
                Array(1072355, 100, 1, 1),
				//�������Ь
                Array(1072356, 100, 1, 1),
				//����ʺ�Ь
                Array(1072357, 100, 1, 1),
				//�������ѥ
                Array(1072358, 100, 1, 1),
				//���㶨��ѥ
                Array(1072359, 100, 1, 1),
				/*************
				�׷�
				*************/
				//����������
                Array(1052155, 100, 1, 1),
				//���������
                Array(1052156, 100, 1, 1),
				//����Ѳ����
                Array(1052157, 100, 1, 1),
				//���㷭�Ʒ�
                Array(1052158, 100, 1, 1),
				//������ߺ�
                Array(1052159, 100, 1, 1),
				/*************
				ñ��
				*************/
				//����ھ���
                Array(1002776, 100, 1, 1),
				//��������ñ
                Array(1002777, 100, 1, 1),
				//��������ñ
                Array(1002778, 100, 1, 1),
				//��������ñ
                Array(1002779, 100, 1, 1),
				//���㺣����
                Array(1002780, 100, 1, 1),
				/*************
				����
				*************/
				//�����Ƽ׽�
                Array(1302081, 100, 1, 1),
				//������Ÿ�
                Array(1312037, 100, 1, 1),
				//���㾪����
                Array(1322060, 100, 1, 1),
				//��������
                Array(1332073, 100, 1, 1),
				//���������
				Array(1332074, 300, 1, 1),
				//���������
                Array(1372044, 100, 1, 1),
				//���������
                Array(1382057, 100, 1, 1),
				//������ڤ��
                Array(1402046, 100, 1, 1),
				//����������
                Array(1412033, 100, 1, 1),
				//����������
                Array(1422037, 100, 1, 1),
				//������ʥǹ
                Array(1432047, 100, 1, 1),
				//��������
                Array(1442063, 100, 1, 1),
				//���㾪�繭
                Array(1452057, 100, 1, 1),
				//����ڤ����
                Array(1462050, 100, 1, 1),
				//����󱯸�
                Array(1472068, 100, 1, 1),
				//�����ȸ��
                Array(1482023, 100, 1, 1),
				//�������
                Array(1492023, 100, 1, 1)
                );

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
	if (status == 0) {
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
            item = cm.gainGachaponItem2(itemId, quantity, "�������֮��", notice);
            if (item != -1) {
            } else {
                cm.playerMessage(1,"����ȷ���ڱ�����װ�������ģ������������Ƿ���һ�����ϵĿռ䡣");
            }
            cm.safeDispose();
        } else {
            cm.playerMessage(1,"�������������ʲô��û���õ���");
            cm.safeDispose();
        }
        cm.�Ի�����();
    }
}