/*
 ZEVMSð�յ�(079)��Ϸ�����
 */
var status = 0;
var beauty = 0;
var price = 1000000;
var fface = Array(21000,21001,21002,21003,21004,21005,21006,21007,21008,21009,21010,21011,21012,21013,21014,21015,21016,21017,21018,21019,21020,21021,21022,21023,21024,21025,21026,21027,21028,21029);
var mface = Array(20000,20001,20002,20003,20004,20005,20006,20007,20008,20009,20010,20011,20012,20013,20014,20015,20016,20017,20018,20019,20020,20021,20022,20023,20024,20025,20026,20027);
var facenew = Array();

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1)
        cm.dispose();
    else {
        if (mode == 0 && status == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            cm.sendSimple("  Hi~ #b#h ##k ��������Ժ��. ������� #b#z5152001##k �ҾͿ���Ϊ������Ŷ�� \r\n\r\n#L2##b��Ҫ��ʼ����#l#k");
        } else if (status == 1) {
            if (selection == 1) {
                if(cm.getMeso() >= price) {
                    cm.gainMeso(-price);
                    cm.gainItem(5152001, 1);
                    cm.sendOk("��!");
                } else
                    cm.sendOk("��û����ô��Ǯ!");
                cm.dispose();
            } else if (selection == 2) {
                facenew = Array();
                if (cm.getPlayer().getGender() ==0) {
                    for(var i = 0; i < mface.length; i++)
                        facenew.push(mface[i]);
                }
                if (cm.getPlayer().getGender() ==1) {
                    for(var i = 0; i < fface.length; i++)
                        facenew.push(fface[i]);
                }
                cm.sendStyle("���ҿ���ѡ��һ����Ҫ��..", 5152001, facenew);
            }
        }
        else if (status == 2){
            cm.dispose();
            if (cm.haveItem(5152001) == true){
                cm.gainItem(5152001, -1);
                cm.setFace(facenew[selection]);
                cm.sendOk("�㿴����������");
            } else
                cm.sendOk("��ò��û��#b#z5152001##k..");
        }
    }
}
