/*
 ZEVMSð�յ�(079)��Ϸ�����
 50������
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
            if (qm.getQuestStatus(4767) == 2) {
                qm.sendOk("���Ѿ���ȡ������������Ŭ����60�����Ի�ø��ཱ���");
                qm.dispose();
            } else {
                qm.sendNext("��ϲ�㵱ǰ�ȼ��Ѿ����� #b50#k ����\r\n\r\n" + huoqu + " ��ȯ x #b1000#k");
            }
        } else if (status == 1) {
            qm.sendOk("��һ�ν���Ϊ #b60#k ����");
            qm.����ȯ(1000);
            qm.completeQuest();
            qm.dispose();
        }
    }
}