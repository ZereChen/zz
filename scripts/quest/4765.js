/*
 ZEVMSð�յ�(079)��Ϸ�����
 30������
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
            if (qm.getQuestStatus(4765) == 2) {
                qm.sendOk("���Ѿ���ȡ������������Ŭ����40�����Ի�ø��ཱ���");
                qm.completeQuest();
                qm.dispose();
            } else {
                qm.sendNext("��ϲ�㵱ǰ�ȼ��Ѿ����� #b30#k ����\r\n\r\n" + huoqu + " #v2000005# x 20");
            }
        } else if (status == 1) {
            qm.sendOk("��һ�ν���Ϊ #b40#k ����");
            qm.gainItem(2000005, 20);
            qm.completeQuest();
            qm.dispose();
        }
    }
}