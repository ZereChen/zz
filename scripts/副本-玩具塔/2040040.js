/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�������������
 */
var �ռ���Ƭ���� = 24;
var ����ؾ��� = 15000;

function action(mode, type, selection) {
    var eim = cm.getEventInstance();

    var stage5status = eim != null ? eim.getProperty("stage5status") : null;

    if (stage5status == null) {
        if (cm.isLeader()) {
            var stage5leader = eim.getProperty("stage5leader");
            if (stage5leader == "done") {

                if (cm.haveItem(4001022, �ռ���Ƭ����)) {
                    cm.sendNext("��ϲ�����Ѿ�ͨ���˵���׶Ρ�������ڣ�����6�׶Ρ�");
                    cm.removeAll(4001022);
                    clear(5, eim, cm);
                    cm.givePartyExp(����ؾ���, eim.getPlayers());
                } else {
                    cm.sendNext("��ȷ�������ռ��� #r" + �ռ���Ƭ���� + "#k �� #v4001022# ����");
                }
                cm.safeDispose();
            } else {
                cm.sendOk("��ӭ��������׶Ρ�#r" + �ռ���Ƭ���� + "#k ��#v4001022#  �����Ҽ����������");
                eim.setProperty("stage5leader", "done");
                cm.safeDispose();
            }
        } else {
            cm.sendNext("��ӭ��������׶Ρ����ռ�#v4001022# �����ӳ���Ȼ��жӳ������Ҽ����������");
            cm.safeDispose();
        }
    } else {
        cm.sendNext("��ϲ�����Ѿ�ͨ���˵���׶Ρ�������ڣ�����6�׶Ρ�");
        cm.safeDispose();
    }
}

function clear(stage, eim, cm) {
    eim.setProperty("stage" + stage.toString() + "status", "clear");
    cm.showEffect(true, "quest/party/clear");
    cm.playSound(true, "Party1/Clear");
    cm.environmentChange(true, "gate");
}
