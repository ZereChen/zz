/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű������
 */
var ��͵ȼ� = 20;
var ��ߵȼ� = 255;
var ÿ�մ��� = 10;

var status = 0;

function start() {
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.�Ի�����();
        return;
    }
    if (status == 1) {
        cm.sendYesNo("\r\n��Ҫ��ȥ��ս��򼣿\r\n�ȼ�Ҫ��:#b" + ��͵ȼ� + " - " + ��ߵȼ� + "#k\r\nÿ�մ���:#b" + ÿ�մ��� + "#k\r\n�ܼ����: #r" + cm.getBossRank("���", 2) + "\r\n#k�������: #r" + cm.getBossLog("���") + "\r\n\r\n");
    } else if (status == 2) {
        if (cm.getParty() == null) {
            cm.sendOk("������������ҡ�");
        } else if (!cm.isLeader()) {
            cm.sendOk("�����Ķӳ������ҡ�");
        } else if (cm.�жϵȼ�() > ��ߵȼ� || cm.�жϵȼ�() < ��͵ȼ�) {
        } else if (cm.getBossLog("���") <= ÿ�մ���) {
            var em = cm.getEventManager("WuGongPQ");
			cm.setBossLog("���")
            if (em == null) {
                cm.˵������("��ǰ���������⣬���������Ա....");
            } else {
                var prop = em.getProperty("state");
                if (prop.equals("0") || prop == null) {
                    em.startInstance(cm.getParty(), cm.getMap());
                    cm.�Ի�����();
                    return;
                } else {
                    cm.˵������("�����Ѿ���������ս�ˡ�����Ҫ�ȴ��ȴ�");
                }
            }
        } else {
            cm.˵������("������Ѿ�������ս�ˣ�");
        }
        cm.�Ի�����();
    }
}