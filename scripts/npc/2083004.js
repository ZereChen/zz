/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű���������ս
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
    if (cm.getPlayer().getClient().getChannel() != 3) {
        cm.˵������("������ #b3#k Ƶ����ս��");
        cm.�Ի�����();
    } else if (status == 0) {
        var selStr = "\r\n";
        if (cm.�ж�ָ����ͼ�������(240060200) == 0 && cm.�ж�ָ����ͼ�������(240060100) == 0 && cm.�ж�ָ����ͼ�������(240060000) == 0) {
            if (cm.Getcharacterz("����Զ���Ӷӳ�", 202) > 0) {
                if (cm.Getcharacterz("����Զ���Ӷӳ�", 202) <= 1) {
                    if (cm.Getcharacterz("" + cm.getPlayer().id + "", 201) <= 0) {
                        selStr += " #L1##b����Զ����#k#l\r\n";
                    } else {
                        if (cm.Getcharacterz("" + cm.getPlayer().id + "", 201) < 2) {
                            selStr += " #L2##b�˳�Զ����#k#l\r\n";
                        } else {
                            selStr += " #L13##r��ʼԶ������#k#l\r\n";
                            selStr += " #L12##b��ɢԶ����#k#l\r\n";
                        }
                    }
                }
            } else {
                if (cm.Getcharacterz("" + cm.getPlayer().id + "", 201) <= 0) {
                    selStr += " #L11##b����Զ����#k#l\r\n";
                }
            }
            if (cm.Getcharacterz("����Զ���Ӷӳ�", 202) > 1 && cm.Getcharacterz("" + cm.getPlayer().id + "", 201) > 0) {
                selStr += " #L4##b���¼���ս��#k#l\r\n";
            }
            selStr += " #L3##b�鿴Զ�̶�#k#l\r\n";
        }
        cm.˵������(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.Gaincharacterz("" + cm.getPlayer().id + "", 201, 1);
                cm.˵������("����#b����Զ����#k�ɹ�������");
                cm.�Ի�����();
                break;
            case 2:
                cm.Gaincharacterz("" + cm.getPlayer().id + "", 201, -cm.Getcharacterz("" + cm.getPlayer().id + "", 201));
                cm.˵������("�˳�#b����Զ����#k�ɹ�������");
                cm.�Ի�����();
                break;
            case 11:
                if (cm.Getcharacterz("����Զ���Ӷӳ�", 202) > 0) {
                    cm.˵������("�����Ѿ������˺���Զ���ӣ�����");
                    cm.�Ի�����();
                } else {
                    cm.Gaincharacterz("" + cm.getPlayer().id + "", 201, 2);
                    cm.Gaincharacterz("����Զ���Ӷӳ�", 202, 1);
                    cm.˵������("����#b����Զ����#k�ɹ����Ͽ��ټ���Ķ���ɡ�");
                    cm.�Ի�����();
                }
                break;
            case 12:
                cm.Gaincharacterz("" + cm.getPlayer().id + "", 201, -cm.Getcharacterz("" + cm.getPlayer().id + "", 201));
                cm.Gaincharacterz("����Զ���Ӷӳ�", 202, -cm.Getcharacterz("����Զ���Ӷӳ�", 202));
                cm.��ɢ����Զ����();
                cm.˵������("��ɢ#b����Զ����#k�ɹ���");
                cm.�Ի�����();
                break;
            case 13:
                cm.getMap(240060000).resetFully();
                cm.getMap(240060100).resetFully();
                cm.getMap(240060200).resetFully();
                cm.Gaincharacterz("����Զ���Ӷӳ�", 202, 1);
                cm.��ʼ����Զ��(3, 240060000);
                cm.ָ����ͼ�ٻ�����(8810024, 240060000, 890, 230);
                cm.ָ����ͼ�ٻ�����(8810025, 240060100, -360, 230);
                break;
            case 3:
                cm.�Ի�����();
                cm.��NPC(2083004, 1);
                break;
            case 4:
                cm.warp(240060000, 0);
                cm.�Ի�����();
                break;
        }
    }
} 