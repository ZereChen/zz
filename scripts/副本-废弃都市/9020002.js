/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű����������н���
 */
importPackage(java.awt);
importPackage(Packages.tools);
importPackage(Packages.server);
importPackage(Packages.handling.world);
var status;

function start() {
    status = -1;
    action(1, 0, 0);
}
var time = 0;
function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.dispose();
        return;
    } else {
        if (mode == 1)
            status++;
        else
            status--;
        var mapId = cm.getMapId();
        if (mapId == 103000890) {
            cm.warp(103000000, "mid00");
            cm.removeAll(4001007);
            cm.removeAll(4001008);
            cm.dispose();
        } else {
            var outText;
            if (mapId == 103000805) {
                outText = "��ȷ��Ҫ�뿪��ͼ����";
                time = 1;
            } else {
                outText = "һ�����뿪��ͼ���㽫���ò�������������������������ٴγ��ԡ��㻹��Ҫ�뿪�����ͼ��";
                time = 0;
            }
            if (status == 0) {
                cm.sendYesNo(outText);
            } else if (mode == 1) {
                if (time == 1) {
                    getPrize();
                }
				cm.warp(103000890, "st00");
                cm.dispose();
            }
        }
    }
}
//����ͨ�ؽ�������
/*
cm.���ʸ���Ʒ(��Ʒ���룬�̶�����������,��ע)��
cm.���ʸ���Ʒ2(��Ʒ���룬�������������,��ע)��
*/
function getPrize() {
	//����ʢ��ĸ�󣬶���
	cm.���ʸ���Ʒ2(4004000,2,30,"����ĸ��");
	cm.���ʸ���Ʒ2(4004001,2,30,"�ǻ�ĸ��");
	cm.���ʸ���Ʒ2(4004002,2,30,"����ĸ��");
	cm.���ʸ���Ʒ2(4004003,2,30,"����ĸ��");
	cm.���ʸ���Ʒ2(4004004,2,1,"�ڰ�ˮ��ĸ��");
	cm.���ʸ���Ʒ2(4020000,2,10,"ʯ��ʯĸ��");
	cm.���ʸ���Ʒ2(4020001,2,10,"��ˮ��ĸ��");
	cm.���ʸ���Ʒ2(4020002,2,10,"����ʯĸ��");
	cm.���ʸ���Ʒ2(4020003,2,10,"��ĸ��ĸ��");
	cm.���ʸ���Ʒ2(4020004,2,10,"����ʯĸ��");
	cm.���ʸ���Ʒ2(4020005,2,10,"����ʯĸ��");
	cm.���ʸ���Ʒ2(4020006,2,10,"�ƾ�ĸ��");
	cm.���ʸ���Ʒ2(4020007,2,10,"��ʯĸ��");
	cm.���ʸ���Ʒ2(4020008,2,10,"��ˮ��ĸ��");
	//��¼ͨ����Ϣ
	cm.getPlayer().endPartyQuest(1201);
	cm.setBossRankCount("��������",1);
	cm.setBossLog("��������");
    cm.worldMessage(2, "[����-��������] : ��ϲ " + cm.getPlayer().getName() + " ������飬��ɷ������и�����");
}
























