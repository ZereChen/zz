/*
ZEVMSð�յ�(079)��Ϸ�����
 */

function action(mode, type, selection) {
    if (cm.�ж�ÿ��ֵ("������") <= 10) {
        if (cm.�ж���Ʒ����(4110010, 1)) {
            var ���� = cm.�����(100);
            cm.������(����);
			cm.����ÿ��ֵ("������");
            cm.getPlayer().dropMessage(1, "��ϲ������ " + ���� + " �Ŷ�����");
        } else {
            cm.getPlayer().dropMessage(1, "��û�ж���Ʊ���Ҳ��ܸ��㶹����");
        }
    } else {
        cm.getPlayer().dropMessage(1, "һ�����ֻ�ܶһ� 10 �ζ�����");
    }
}