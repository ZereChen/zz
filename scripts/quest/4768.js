/*
 ZEVMSð�յ�(079)��Ϸ�����
 60������
 */
var huoqu = "#fUI/UIWindow.img/QuestIcon/4/0#";
var status = -1;

function start(mode, type, selection) {
    if (mode == -1) {
        qm.dispose();
    } else {
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            if (qm.�жϱ���������().isFull()) {
                qm.sendNext("������������һ����λ��");
                qm.�Ի�����();
                return;
            }
            if (qm.getQuestStatus(4768) == 2) {
                qm.sendOk("���Ѿ���ȡ������������Ŭ����70�����Ի�ø��ཱ���");
                qm.completeQuest();
                qm.dispose();
            } else {
                qm.sendNext("��ϲ�㵱ǰ�ȼ��Ѿ����� #b60#k ����\r\n\r\n" + huoqu + " #v5072000# x 10");
            }
        } else if (status == 1) {
            qm.sendOk("��һ�ν���Ϊ #b70#k ����");
            qm.gainItem(5072000, 10);
            qm.completeQuest();
            qm.dispose();
        }
    }
}