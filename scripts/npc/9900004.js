/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű����������˵�
 */
//ʱ������
var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR);
var month = ca.get(java.util.Calendar.MONTH) + 1;
var day = ca.get(java.util.Calendar.DATE);
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY);
var minute = ca.get(java.util.Calendar.MINUTE);
var second = ca.get(java.util.Calendar.SECOND);
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
//�ز�����
var JT = "#fUI/Basic/BtHide3/mouseOver/0#";
var �� = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
var r = "#fUI/UIWindow.img/Quest/TimeQuest/AlarmClock/default/0/0#";

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
	if(cm.�жϵ�ͼ()>=970000000 && cm.�жϵ�ͼ()<=970000005){
		cm.���˹���("�������޷�ʹ�á�");
		cm.�Ի�����();
		return;
	}
	//��ʼ
    if (status == 0) {
		
        var selStr = "\r\n\r\n\t\t" + �� + "  " + �� + " #r#e < " + cm.��������() + " > #k#n " + �� + "  " + �� + "\r\n\r\n";
		
		selStr += "\t\t\t#k" + r + " ʱ��: #r" + hour + "#k #b: #r" + minute + "#k #b: #r" + second + "#k\r\n";
		 
        selStr += " #L1##b������Ϣ#k#l #L2##b�ռ䴫��#k#l #L3##b�� �� ��#k#l#L4##b����ֿ�#k#l\r\n";

        selStr += " #L5##b������#k#l #L6##b��Ϸ��̳#k#l #L7##b����ר��#k#l#L8##b�ƹ�ϵͳ#k#l\r\n";
		
		selStr += " #L10##bð�հٿ�#k#l #L11##b����ϵͳ#k#l #L12##b��������#k#l#L13##b�� Ӱ ��#k#l\r\n";
		
		selStr += " #L14##b����ʹ��#k#l #L15##b���ֵ㲥#k#l #L16##b��������#k#l\r\n";
		
		selStr += "\r\n\r\n";
		
		selStr += "\t\t\t\t   www.zevmxd.com\r\n";

        cm.�Ƿ�˵������(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.�Ի�����();
                cm.openNpc(9900004, 1);
                break;
            case 2:
                cm.�Ի�����();
                cm.openNpc(9900004, 2);
                break;
            case 3:
                cm.�Ի�����();
                cm.openNpc(9900004, 3);
                break;
            case 4:
                cm.�Ի�����();
                cm.openNpc(9900004, 4);
                break;
            case 5:
                cm.�Ի�����();
                cm.openNpc(9900004, 5);
                break;
            case 6:
                cm.�Ի�����();
                cm.openNpc(9900004, 6);
                break;
            case 7:
                cm.�Ի�����();
                cm.openNpc(9900004, 7);
                break;
            case 8:
                cm.�Ի�����();
                cm.openNpc(9900004, 8);
                break;
			case 9:
                cm.�Ի�����();
                cm.openNpc(9900004, 9);
                break;
			case 10:
                cm.�Ի�����();
                cm.openNpc(9900007, 0);
                break;
			case 11:
                cm.�Ի�����();
                cm.openNpc(9900004, 11);
                break;
			case 12:
                cm.�Ի�����();
                cm.openNpc(9900004, 12);
                break;
			case 13:
                cm.�Ի�����();
                cm.openNpc(2007, 11);
                break;
			case 14:
                cm.�Ի�����();
                cm.openNpc(9900004, 14);
                break;
			case 15:
                cm.�Ի�����();
                cm.openNpc(9900004, 15);
                break;
			case 16:
                cm.�Ի�����();
                cm.openNpc(2007, 2007);
                break;
			default:
                cm.�Ի�����();
                break;
        }
    }
}