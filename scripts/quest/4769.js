/*
 ZEVMSð�յ�(079)��Ϸ�����
 70������
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
            if (qm.�жϱ���������().isFull(2)) {
                qm.sendNext("������������������λ��");
                qm.�Ի�����();
                return;
            }
            if (qm.getQuestStatus(4769) == 2) {
                qm.sendOk("���Ѿ���ȡ������������Ŭ����71�����Ի�ø��ཱ���");
                qm.completeQuest();
                qm.dispose();
            } else {
                qm.sendNext("��ϲ�㵱ǰ�ȼ��Ѿ����� #b70#k ����\r\n\r\n" + huoqu + " #v5390000# #v5390001# #v5390002# x 2");
            }
        } else if (status == 1) {
            qm.sendOk("��һ�ν���Ϊ #b71#k ����");
            qm.gainItem(5390000, 2);
            qm.gainItem(5390001, 2);
            qm.gainItem(5390002, 2);
            qm.completeQuest();
            qm.dispose();
        }
    }
}