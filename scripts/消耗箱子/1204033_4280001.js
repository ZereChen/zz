/*
 ZEVMSð�յ�(079)��Ϸ�����
 ��������֮��
 4280001
 5490001
 */
var status = 0;
//��ƷID���齱���ʣ���Ʒ������(0/���㲥��1�㲥)
var itemList =
        Array(
				/*************
				����
				*************/
				//������������
                Array(1082239, 100, 1, 1),
				//������ң����
                Array(1082240, 100, 1, 1),
				//������������
                Array(1082241, 100, 1, 1),
				//����̽������
                Array(1082242, 100, 1, 1),
				//������������
                Array(1082243, 100, 1, 1),
				/*************
				Ь��
				*************/
				//�������ѥ
                Array(1072361, 100, 1, 1),
				//�������Ь
                Array(1072362, 100, 1, 1),
				//�����ʺ�Ь
                Array(1072363, 100, 1, 1),
				//�������ѥ
                Array(1072364, 100, 1, 1),
				//��������ѥ
                Array(1072365, 100, 1, 1),
				/*************
				�׷�
				*************/
				//����������
                Array(1052160, 100, 1, 1),
				//����������
                Array(1052161, 100, 1, 1),
				//����Ѳ����
                Array(1052162, 100, 1, 1),
				//�������Ʒ�
                Array(1052163, 100, 1, 1),
				//�������ߺ�
                Array(1052164, 100, 1, 1),
				/*************
				ñ��
				*************/
				//�����ھ���
                Array(1002790, 100, 1, 1),
				//��������ñ
                Array(1002791, 100, 1, 1),
				//��������ñ
                Array(1002792, 100, 1, 1),
				//��������ñ
                Array(1002793, 100, 1, 1),
				//����������
                Array(1002794, 100, 1, 1),
				/*************
				����
				*************/
				//�����Ƽ׽�
                Array(1302086, 100, 1, 1),
				//�������Ÿ�
                Array(1312038, 100, 1, 1),
				//����������
                Array(1322061, 100, 1, 1),
				//���������
                Array(1332075, 100, 1, 1),
				//����������
				Array(1332076, 300, 1, 1),
				//����������
                Array(1372045, 100, 1, 1),
				//����������
                Array(1382059, 100, 1, 1),
				//������ڤ��
                Array(1402047, 100, 1, 1),
				//����������
                Array(1412034, 100, 1, 1),
				//����������
                Array(1422038, 100, 1, 1),
				//������ʥǹ
                Array(1432049, 100, 1, 1),
				//��������
                Array(1442067, 100, 1, 1),
				//�������繭
                Array(1452059, 100, 1, 1),
				//����ڤ����
                Array(1462051, 100, 1, 1),
				//�����󱯸�
                Array(1472071, 100, 1, 1),
				//������ȸ��
                Array(1482024, 100, 1, 1),
				//��������
                Array(1492025, 100, 1, 1)
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
            item = cm.gainGachaponItem3(itemId, quantity, "��������֮��", notice);
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