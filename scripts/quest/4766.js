/*
 ZEVMSð�յ�(079)��Ϸ�����
 40������
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
            if (qm.getQuestStatus(4766) == 2) {
                qm.sendOk("���Ѿ���ȡ������������Ŭ����50�����Ի�ø��ཱ���");
                qm.completeQuest();
                qm.dispose();
            } else {
                qm.sendNext("��ϲ�㵱ǰ�ȼ��Ѿ����� #b40#k ����\r\n\r\n" + huoqu + " ��� x #b200000#k");
            }
        } else if (status == 1) {
            qm.sendOk("��һ�ν���Ϊ #b50#k ����");
            qm.gainMeso(20 * 10000);
            qm.completeQuest();
            qm.dispose();
        }
    }
}