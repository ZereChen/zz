/*
 ZEVMSð�յ�(079)��Ϸ�����
 10������
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
            if (qm.getQuestStatus(4762) == 2) {
                qm.sendOk("���Ѿ���ȡ������������Ŭ����15�����Ի���½����");
                qm.completeQuest();
                qm.dispose();
            } else {
                qm.sendNext("��ϲ�㵱ǰ�ȼ��Ѿ����� #b10#k ����\r\n\r\n" + huoqu + " ��� x #b100000#k");
            }
        } else if (status == 1) {
            qm.sendOk("��һ�ν���Ϊ #b15#k ����");
            qm.gainMeso(10 * 10000);
            qm.completeQuest();
            qm.dispose();
        }
    }
}