/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�������
 */
 
 
var ħ��ʯ = 4006000;
var JT = "#fUI/Basic/BtHide3/mouseOver/0#";
var �� = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
	if(cm.�жϵ�ͼ()==500000000||
	cm.�жϵ�ͼ()==702000000||
	cm.�жϵ�ͼ()==600000000||
	cm.�жϵ�ͼ()==540000000||
	cm.�жϵ�ͼ()==800000000||
	cm.�жϵ�ͼ()==702100000||
	cm.�жϵ�ͼ()==550000000
	){
		cm.�Ի�����();
		cm.��NPC(2007,12);
		return;
	}
	if(cm.getBossRank("��¼���͵�12", 2)==-1){
		cm.setBossRankCount("��¼���͵�12", 1);
	}
	if (status == 0) {
        var selStr = " ѡ��һ����Ҫȥ������Ŀ�ĵذɣ�\r\n";
        selStr += "#L1##b#m500000000##k#l\r\n";
		selStr += "#L2##b#m702000000##k#l\r\n";
		selStr += "#L3##b#m600000000##k#l\r\n";
		selStr += "#L4##b#m540000000##k#l\r\n";
		selStr += "#L5##b#m800000000##k#l\r\n";
		selStr += "#L6##b#m702100000##k#l\r\n";
		selStr += "#L7##b#m550000000##k#l\r\n";

        cm.˵������(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
				cm.setBossRankCount("��¼���͵�12", -cm.getBossRank("��¼���͵�12", 2));
				cm.setBossRankCount("��¼���͵�12", cm.getMapId());
				cm.warp(500000000,0);
                cm.dispose();
                break;
            case 2:
				cm.setBossRankCount("��¼���͵�12", -cm.getBossRank("��¼���͵�12", 2));
				cm.setBossRankCount("��¼���͵�12", cm.getMapId());
				cm.warp(702000000,0);
                cm.dispose();
                break;
            case 3:
				cm.setBossRankCount("��¼���͵�12", -cm.getBossRank("��¼���͵�12", 2));
				cm.setBossRankCount("��¼���͵�12", cm.getMapId());
				cm.warp(600000000,0);
                cm.dispose();
                break;
			case 4:
				cm.setBossRankCount("��¼���͵�12", -cm.getBossRank("��¼���͵�12", 2));
				cm.setBossRankCount("��¼���͵�12", cm.getMapId());
				cm.warp(540000000,0);
                cm.dispose();
                break;
			case 5:
				cm.setBossRankCount("��¼���͵�12", -cm.getBossRank("��¼���͵�12", 2));
				cm.setBossRankCount("��¼���͵�12", cm.getMapId());
				cm.warp(800000000,0);
                cm.dispose();
                break;
			case 6:
				cm.setBossRankCount("��¼���͵�12", -cm.getBossRank("��¼���͵�12", 2));
				cm.setBossRankCount("��¼���͵�12", cm.getMapId());
				cm.warp(702100000,0);
                cm.dispose();
                break;
			case 7:
				cm.setBossRankCount("��¼���͵�12", -cm.getBossRank("��¼���͵�12", 2));
				cm.setBossRankCount("��¼���͵�12", cm.getMapId());
				cm.warp(550000000,0);
                cm.dispose();
                break;
        }
    }
}



















