/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű���������ڶ���
 */

var �ռ���Ƭ���� = 15;
var �ڶ��ؾ��� = 10000;

var status;


function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    var eim = cm.getEventInstance();
    var stage2status = eim.getProperty("stage2status");

    if (stage2status == null) {
        if (cm.isLeader()) {
            var stage2leader = eim.getProperty("stage2leader");
            if (stage2leader == "done") {

                if (cm.haveItem(4001022, �ռ���Ƭ����)) {
                    cm.sendNext("��ϲ�����Ѿ�ͨ���˵ڶ��׶Ρ�������ڣ��������׶Ρ�");
                    cm.removeAll(4001022);
                    clear(2, eim, cm);
                    cm.givePartyExp(�ڶ��ؾ���);
                    cm.dispose();
                } else {
                    cm.sendNext("��ȷ�������ռ��� #r" + �ռ���Ƭ���� + "#k�� #v4001022#����");
                }
                cm.dispose();
            } else {
                cm.sendOk("��ӭ�����ڶ��׶Ρ����ռ� #r" + �ռ���Ƭ���� + "#k��#v4001022# �����Ҽ����������");
                eim.setProperty("stage2leader", "done");
                cm.dispose();
            }
        } else {
            cm.sendNext("��ӭ�����ڶ��׶Ρ����ռ�#v4001022# �����ӳ���Ȼ��жӳ������Ҽ����������");
            cm.dispose();
        }
    } else {
        cm.sendNext("��ϲ�����Ѿ�ͨ���˵ڶ��׶Ρ�������ڣ��������׶Ρ�");
        cm.dispose();
    }
}

function clear(stage, eim, cm) {
    eim.setProperty("stage" + stage.toString() + "status", "clear");
    cm.showEffect(true, "quest/party/clear");
    cm.playSound(true, "Party1/Clear");
    cm.environmentChange(true, "gate");
}