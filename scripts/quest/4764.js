/*
 ZEVMSð�յ�(079)��Ϸ�����
 20������
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
            if (qm.getQuestStatus(4764) == 2) {
                qm.sendOk("���Ѿ���ȡ������������Ŭ����30�����Ի���½����");
                qm.completeQuest();
                qm.dispose();
            } else {
                qm.sendNext("��ϲ�㵱ǰ�ȼ��Ѿ����� #b20#k ����\r\n\r\n" + huoqu + " #v5220007# x 1");
            }
        } else if (status == 1) {
            qm.sendOk("��һ�ν���Ϊ #b30#k ����");
            qm.gainItem(5220007, 1);
            qm.completeQuest();
            qm.dispose();
        }
    }
}