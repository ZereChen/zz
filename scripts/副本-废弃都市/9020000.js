/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�����������
 */

var ��С���� = 1;
var ������� = 6;
var ��͵ȼ� = 21;
var ��ߵȼ� = 256;
//��������Ԥ��
/*
 ��Ʒ������
 */
var ����Ԥ�� = [
    [4004000, 30],
	[4004001, 30],
	[4004002, 30],
	[4004003, 30],
	[4004004, 1],
	[4020000, 10],
	[4020001, 10],
	[4020002, 10],
	[4020003, 10],
	[4020004, 10],
	[4020005, 10],
	[4020006, 10],
	[4020007, 10],
	[4020008, 10]
];
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
        cm.dispose();
        return;
    }

    if (status == 1) {
        var �ı���Ϣ = "";
        for (var i = 0; i < ����Ԥ��.length; i++) {
            �ı���Ϣ += "   " + cm.��ʾ��Ʒ(����Ԥ��[i][0]) + "  " + ����Ԥ��[i][1] + " % #k\r\n";
        }
        cm.sendYesNo("\r\n     λ�ڷ�������ˮ���ĸ������񣬿���ͨ����ɸø�����ȡ�������档������Ҫ���������#b" + ��С���� + " - " + ������� + "#k���ȼ�Ҫ��#b" + ��͵ȼ� + " - " + ��ߵȼ� + "#k����Ҫ�μӸ���#b��������#k��������ʢ������#r��ʯĸ��#kŶ��\r\n\r\n   �ܼ����: #r" + cm.getBossRank("��������", 2) + "\r\n#k   �������: #r" + cm.getBossLog("��������") + "\r\n\r\n #k#e��������Ԥ��:#n\r\n\r\n" + �ı���Ϣ + "");
    } else if (status == 2) {
        if (cm.getParty() == null) {
            cm.sendOk("������������ң������öӳ����ҡ�");
        } else if (!cm.isLeader()) {
            cm.sendOk("�����Ķӳ�������!");
        } else {
            var party = cm.getParty().getMembers();
            var mapId = cm.getMapId();
            var next = true;
            var levelValid = 0;
            var inMap = 0;

            var it = party.iterator();
            while (it.hasNext()) {
                var cPlayer = it.next();
                if (cPlayer.getLevel() >= ��͵ȼ� && cPlayer.getLevel() <= ��ߵȼ�) {
                    levelValid += 1;
                } else {
                    next = false;
                }
                if (cPlayer.getMapid() == mapId) {
                    inMap += (cPlayer.getJobId() == 900 ? 4 : 1);
                }
            }
            if (party.size() > ������� || inMap < ��С����) {
                next = false;
            }
            if (next) {
                var em = cm.getEventManager("KerningPQ");
                if (em == null) {
                    cm.sendOk("�Ҳ����ű�������ϵ����Ա��");
                    cm.dispose();
                    return;
                } else {
                    var prop = em.getProperty("state");
                    if (prop == null || prop.equals("0")) {
                        em.startInstance(cm.getParty(), cm.getMap());
                    } else {
                        cm.sendOk("�Ѿ��ж�����������ս�ˡ�");
                        cm.dispose();
                        return;
                    }
                    cm.removeAll(4001008);
                    cm.removeAll(4001007);
                }
            } else {
                cm.sendOk("����: #b��������#k\r\n����: #b" + ��С���� + " - " + ������� + "#k\r\n�ȼ�: #b" + ��͵ȼ� + " - " + ��ߵȼ� + "#k");
                cm.dispose();
                return;
            }
        }
        cm.dispose();
    }
}