/*
 ZEVMSð�յ�(079)��Ϸ����˽ű�
 ���ͣ�װ����ħ������
 ʱ�䣺2018-08-09
 ���ߣ�ZEV�����ͷſ�
 ����; 1032002_1,1032002_2,1032002_3
 */
var jt = "#fUI/Basic/BtHide3/mouseOver/0#";
function start() {
    status = -1;
    action(1, 0, 0)
}
function action(mode, type, selection) {
    if (status <= 0 && mode <= 0) {
        cm.dispose();
        return
    }
    if (mode == 1) {
        status++
    } else {
        status--

    }
    if (status <= 0) {
        var selStr = "������ʲô?\r\n\r\n";
        selStr += "#b#L0#���뵽���㳡#v4220084##l\r\n";
        selStr += "#b#L1#������ͨ���#v2300000##l\r\n";
        selStr += "#b#L2#�����������#v3011000##l\r\n ";
        selStr += "#b#L3#ʹ�������ͷ#v5350000##l\r\n";
        selStr += "#b#L4#����˵������#v4161004##l\r\n";
        cm.sendSimple(selStr)
    } else if (status == 1) {
        switch (selection) {
            //�����泡
            case 0:
                if (cm.haveItem(5340000) || cm.haveItem(5340001)) {
                    if (cm.haveItem(3011000)) {
                        cm.saveLocation("FISHING");
                        cm.warp(741000200);
                        cm.dispose();
                    } else {
                        cm.sendNext("������е����β��ܵ���!");
                        cm.safeDispose();
                    }
                } else {
                    cm.sendNext("������е���Ͳ��ܵ���!");
                    cm.safeDispose();
                }
                cm.safeDispose();
                break;
                //�������
            case 1:
                if (cm.canHold(2300000, 120) && cm.getMeso() >= 3000) {
                    if (!cm.haveItem(2300000)) {
                        cm.gainMeso(-3000);
                        cm.gainItem(2300000, 120);
                        cm.sendNext("���ֵ���~");
                    } else {
                        cm.sendNext("���Ѿ����˵�����ն�.");
                    }
                } else {
                    cm.sendOk("�����Ƿ����㹻�����ռ�.");
                }
                cm.safeDispose();
                break;
                //��������
            case 2:
                if (cm.haveItem(3011000)) {
                    cm.sendNext("���Ѿ���һ�ѵ������ˡ�ÿ����ɫ��ֻ����1��������.");
                } else {
                    if (cm.canHold(3011000) && cm.getMeso() >= 50000) {
                        cm.gainMeso(-50000);
                        cm.gainItem(3011000, 1);
                        cm.sendNext("���ֵ���~");
                    } else {
                        cm.sendOk("�����Ƿ����㹻�Ľ�һ��㹻�ı�����.");
                    }
                }
                cm.safeDispose();
                break;
                //ʹ�����ͷ
            case 3:
                if (cm.canHold(2300001, 120) && cm.haveItem(5350000, 1)) {
                    if (!cm.haveItem(2300001)) {
                        cm.gainItem(2300001, 120);
                        cm.gainItem(5350000, -1);
                        cm.sendNext("���ֵ���~");
                    } else {
                        cm.sendNext("���Ѿ����˵�����ն�.");
                    }
                } else {
                    cm.sendOk("���鱳���ռ����û�и߼������ͷ����̳ǹ���.");
                }
                cm.safeDispose();
                break;
                //���㹥��
            case 4:
                cm.sendOk("����Ҫ10�����ϣ�����͡���������ν�����㳡���㽫��ÿ1����һ�Ρ����泡��¼�����������㲶׽��¼!");
                cm.safeDispose();
                break;

        }
    }
}