/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�����������Ĺ�
 */

var �ռ���Ƭ���� = 6;
var ���Ĺؾ��� = 14000;


function action(mode, type, selection) {
    var eim = cm.getEventInstance();
    var stage4status = eim.getProperty("stage4status");

    if (stage4status == null) {
	if (cm.isLeader()) {
	    var stage4leader = eim.getProperty("stage4leader");
	    if (stage4leader == "done") {

		if (cm.haveItem(4001022,�ռ���Ƭ����)) { 
		    cm.sendNext("��ϲ�����Ѿ�ͨ���˵��Ľ׶Ρ�������ڣ�����5�׶Ρ�");
		    cm.removeAll(4001022);
		    clear(4,eim,cm);
		    cm.givePartyExp(���Ĺؾ���);
		} else { 
		    cm.sendNext("��ȷ�������ռ��� #r" + �ռ���Ƭ���� + "#k �� #v4001022# ����");
		}
		cm.safeDispose();
	    } else {
		cm.sendOk("��ӭ�������Ľ׶Ρ�#r" + �ռ���Ƭ���� + "#k ��#v4001022#  �����Ҽ����������");
		eim.setProperty("stage4leader","done");
		cm.safeDispose();
	    }
	} else {
	     cm.sendNext("��ӭ�������Ľ׶Ρ����ռ�#v4001022# �����ӳ���Ȼ��жӳ������Ҽ����������");
	    cm.safeDispose();
	}
    } else {
	cm.sendNext("��ϲ�����Ѿ�ͨ���˵��Ľ׶Ρ�������ڣ�����5�׶Ρ�");
	cm.safeDispose();
    }
}

function clear(stage, eim, cm) {
    eim.setProperty("stage" + stage.toString() + "status","clear");
    cm.showEffect(true, "quest/party/clear");
    cm.playSound(true, "Party1/Clear");
    cm.environmentChange(true, "gate");
}