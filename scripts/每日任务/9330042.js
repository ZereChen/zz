/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű���ÿ������
 */
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
    if (status == 0) {
        var selStr = "    ��ã�����ޱޱ�����������кܶ�#rÿ������#k����֪����ʿ���ܰ�����\r\n";
		if(cm.getBossLog("Ϊ���˵���2")<=0){
			selStr += " #L1##bΪ���˵���#k#l\r\n";
		}
		if(cm.getBossLog("�����˵���2")<=0){
			selStr += " #L2##b�����˵���#k#l\r\n";
		}
		if(cm.getBossLog("��ɱ�߼�����2")<=0){
			selStr += " #L3##b��ɱ�߼�����#k#l\r\n";
		}
		if(cm.getBossLog("����2")<=0){
			selStr += " #L4##b����#k#l\r\n";
		}
		if(cm.getBossLog("����2")<=0){
			selStr += " #L100##b����������#k#l\r\n";
		}
		if(cm.getBossLog("����ɭ��2")<=0){
			selStr += " #L101##b̽������ɭ��#k#l\r\n";
		}
		if(cm.getBossLog("��������2")<=0){
			selStr += " #L102##b���е���ˮ��#k#l\r\n";
		}
		if(cm.getBossLog("������2")<=0){
			selStr += " #L103##bͣ���ĺ�����#k#l\r\n";
		}
		if(cm.getBossLog("����ŷ������Ҷ2")<=0){
			selStr += " #L104##b��֤����#k#l\r\n";
		}
		if(cm.getBossLog("Ů����2")<=0){
			selStr += " #L105##b��ȱ��Ů�����#k#l\r\n";
		}
		if(cm.getBossLog("�����2")<=0){
			selStr += " #L106##b���ص������#k#l\r\n";
		}
        cm.˵������(selStr);
    } else if (status == 1) {
        switch (selection) {
			//���ص������
            case 106:
                cm.dispose();
                cm.openNpc(9330042, 106);
                break;
			//��ȱ��Ů�����
            case 105:
                cm.dispose();
                cm.openNpc(9330042, 105);
                break;
			//����ŷ������Ҷ
            case 104:
                cm.dispose();
                cm.openNpc(9330042, 104);
                break;
			//ͣ���ĺ�����
            case 103:
                cm.dispose();
                cm.openNpc(9330042, 103);
                break;
			//���е���ˮ��
            case 102:
                cm.dispose();
                cm.openNpc(9330042, 102);
                break;
			//̽������ɭ��
            case 101:
                cm.dispose();
                cm.openNpc(9330042, 101);
                break;
			//����������
            case 100:
                cm.dispose();
                cm.openNpc(9330042, 100);
                break;
			//����
            case 3:
                cm.dispose();
                cm.openNpc(9330042, 4);
                break;
			//��ɱ�߼�����
            case 3:
                cm.dispose();
                cm.openNpc(9330042, 3);
                break;
			//�����˵���
            case 2:
                cm.dispose();
                cm.openNpc(9330042, 2);
                break;
			//Ϊ���˵���
            case 1:
                cm.dispose();
                cm.openNpc(9330042, 1);
                break;
            
        }
    }
}