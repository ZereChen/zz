/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�����ɱ�߼�����
 */
var ��ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var ������ = "#fUI/UIWindow.img/Quest/summary#";
var ���� = "#fUI/UIWindow.img/Quest/reward#";
var ������ = "#fUI/UIWindow.img/QuestIcon/6/0#";
var ���ʻ�� = "#fUI/UIWindow.img/Quest/prob#";
var ���ص��� = "#fUI/UIWindow.img/QuestIcon/5/0#";
var ��̾�� = "#fUI/UIWindow.img/Quest/icon0#";
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
    var Ҫ������ = 5;
    var ��ɽ��� = cm.getBossLog("����ɭ��");
    if (status == 0) {
        var
                selStr = "\r\n";
        selStr += " " + ������ + " �������:[#r " + Ҫ������ + " #k/ #b" + ��ɽ��� + "#k ]\r\n\r\n";
        selStr += "   ȥ����ɭ����� #b" + Ҫ������ + "#k �ζ���ɭ�ָ����󣬼����������Ŷ��\r\n\r\n";
        selStr += " " + ���� + " " + ���ص��� + "\r\n";
        selStr += " \t\t\t\t    #L0#" + ��ͷ + "#b#e����#l\r\n";
        if (cm.getBossLog("����ɭ��2") <= 0 && ��ɽ��� >= Ҫ������) {
            selStr += " \t\t\t     #L1#" + ��ͷ + "#b#e�������#l\r\n";
        }
        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                if (��ɽ��� >= Ҫ������) {
                    //���ý���������


                    ////
                    cm.setBossLog("����ɭ��2");
                    cm.sendNext("��ϲ�㣬������ɡ�");
                    cm.dispose();

                } else {
                    cm.sendNext("δ�ﵽ����Ҫ��");
                    cm.dispose();
                }
                break;
            case 0:
                cm.dispose();
                cm.openNpc(9330042, 0);
                break;
        }
    }
}