/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�����Եǩ���������޸�
 */
var JT = "#fUI/Basic/BtHide3/mouseOver/0#";
var �� = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.�Ի�����();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    var ǩ�� = cm.getBossRank("ÿ��ǩ��", 2);
    if (ǩ�� < 0) {
        ǩ�� = 0;
    }
    if (status == 0) {
        var selStr = "	  Hi~#b#h ##k������Ե�������ǩ���������Ѿ�ǩ���� #r" + ǩ�� + "#k �죬�������ǩ������������������ȡ����Ŷ��\r\n";

        if (cm.getBossLog("ÿ��ǩ��") < 1) {
            selStr += " #L0##b��Ҫǩ��#k#l\r\n";
        }
        if (cm.getBossRank("3��ǩ������", 2) <= 0) {
            selStr += " #L1##b3��ǩ��������"+cm.��ʾ��Ʒ(2000004)+" x 50 #k#l\r\n";
        }
        if (cm.getBossRank("5��ǩ������", 2) <= 0) {
            selStr += " #L2##b5��ǩ��������"+cm.��ʾ��Ʒ(2049000)+" x 5 #k#l\r\n";
        }
        if (cm.getBossRank("7��ǩ������", 2) <= 0) {
            selStr += " #L3##b7��ǩ��������#i4031138# x 500000 #k#l\r\n";
        }
        if (cm.getBossRank("10��ǩ������", 2) <= 0) {
            selStr += " #L4##b10��ǩ��������"+cm.��ʾ��Ʒ(5531000)+" x 1#k#l\r\n";
        }
        if (cm.getBossRank("15��ǩ������", 2) <= 0) {
            selStr += " #L5##b15��ǩ��������"+cm.��ʾ��Ʒ(5531000)+" x 2#k#l\r\n";
        }
        if (cm.getBossRank("30��ǩ������", 2) <= 0) {
            selStr += " #L6##b30��ǩ��������"+cm.��ʾ��Ʒ(5531000)+" x 8#k#l\r\n";
        }
        cm.˵������(selStr);
    } else if (status == 1) {
        switch (selection) {
            //30��ǩ������
            case 6:
                if (ǩ�� >= 30) {
                    cm.������(60000000);
                    cm.setBossRankCount("30��ǩ������", 1);
                    cm.˵������("��ϲ����ȡ 30 ��ǩ�������ɹ���");
                } else {
                    cm.˵������("ǩ������������");
                }
                cm.�Ի�����();
                break;
                //15��ǩ������
            case 5:
                if (ǩ�� >= 30) {
                    cm.������(6000000);
                    cm.setBossRankCount("15��ǩ������", 1);
                    cm.˵������("��ϲ����ȡ 15 ��ǩ�������ɹ���");
                } else {
                    cm.˵������("ǩ������������");
                }
                cm.�Ի�����();
                break;
                //10��ǩ������
            case 4:
                if (ǩ�� >= 30) {
                    cm.������(1500000);
                    cm.setBossRankCount("10��ǩ������", 1);
                    cm.˵������("��ϲ����ȡ 10 ��ǩ�������ɹ���");
                } else {
                    cm.˵������("ǩ������������");
                }
                cm.�Ի�����();
                break;
                //7��ǩ������
            case 3:
                if (ǩ�� >= 30) {
                    cm.������(500000);
					cm.�����(50*10000);
                    cm.setBossRankCount("7��ǩ������", 1);
                    cm.˵������("��ϲ����ȡ 7 ��ǩ�������ɹ���");
                } else {
                    cm.˵������("ǩ������������");
                }
                cm.�Ի�����();
                break;
                //5��ǩ������
            case 2:
                if (ǩ�� >= 30) {
                    cm.������(300000);
					cm.����Ʒ(2049000,5);
                    cm.setBossRankCount("5��ǩ������", 1);
                    cm.˵������("��ϲ����ȡ 5 ��ǩ�������ɹ���");
                } else {
                    cm.˵������("ǩ������������");
                }
                cm.�Ի�����();
                break;
                //3��ǩ������
            case 1:
                if (ǩ�� >= 30) {
                    cm.������(100000);
					cm.����Ʒ(2000004,50);
                    cm.setBossRankCount("3��ǩ������", 1);
                    cm.˵������("��ϲ����ȡ 3 ��ǩ�������ɹ���");
                } else {
                    cm.˵������("ǩ������������");
                }
                cm.�Ի�����();
                break;
                //ÿ��ǩ��
            case 0:
                cm.setBossRankCount("ÿ��ǩ��", 1);
                cm.setBossLog("ÿ��ǩ��");
                cm.˵������("��ϲ��ǩ���ɹ���");
                cm.�Ի�����();
                break;
        }
    }
}