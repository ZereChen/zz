/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�����������߹�
 */
var �ռ���Ƭ���� = 3;
var ���߹ؾ��� = 16240;


var status;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    var eim = cm.getEventInstance();
    var stage7status = eim.getProperty("stage7status");

    if (stage7status == null) {
        if (cm.isLeader()) {
            var stage7leader = eim.getProperty("stage7leader");
            if (stage7leader == "done") {

                if (cm.haveItem(4001022, �ռ���Ƭ����)) {
                    cm.sendNext("��ϲ�����Ѿ�ͨ���˵��߽׶Ρ�������ڣ�����8�׶Ρ�");
                    cm.removeAll(4001022);
                    clear(7, eim, cm);
                    cm.givePartyExp(���߹ؾ���, eim.getPlayers());
                    cm.dispose();
                } else {
                    cm.sendNext("��ȷ�������ռ��� #r" + �ռ���Ƭ���� + "#k �� #v4001022# ����");
                }
                cm.dispose();
            } else {
                cm.sendOk("��ӭ�������߽׶Ρ�#r" + �ռ���Ƭ���� + "#k ��#v4001022#  �����Ҽ����������");
                eim.setProperty("stage7leader", "done");
                cm.dispose();
            }
        } else {
            cm.sendNext("��ӭ�������߽׶Ρ����ռ�#v4001022# �����ӳ���Ȼ��жӳ������Ҽ����������");
            cm.dispose();
        }
    } else {
        cm.sendNext("��ϲ�����Ѿ�ͨ���˵��߽׶Ρ�������ڣ�����8�׶Ρ�");
        cm.dispose();
    }
}

function clear(stage, eim, cm) {
    eim.setProperty("stage" + stage.toString() + "status", "clear");
    cm.showEffect(true, "quest/party/clear");
    cm.playSound(true, "Party1/Clear");
    cm.environmentChange(true, "gate");
}