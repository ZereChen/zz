/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű���������
 */
var status = -1;
var sel;

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        cm.sendNext("�㻹��û�е�����");
        cm.dispose();
        return;
    }

    if (status == 0) {
        cm.sendSimple("\r\n   Hi~ #b#h ##k ������������ս��������ߣ���ӭ��ǰ��������������Ҽ�֤һ�����ǿ��\r\n\r\n#b#L0#�����������#l\r\n#b#L1#�������а�#l")
    } else if (status == 1) {
        sel = selection;
        if (sel == 1) {
            var text = "   ������������������< #e#r�����#k#n >�������������������� #n\r\n\r\n";
				text += "    ����        \t���         \t\t\t������\r\n";
                var rankinfo_list = cm.getBossRankCountTop("�������ۼƵ�������");
                if (rankinfo_list != null) {
                    for (var i = 0; i < rankinfo_list.size(); i++) {
                        if (i == 20) {
                            break;
                        }
                        var info = rankinfo_list.get(i);

                        text += i == 0 ? "#r" : i == 1 ? "#b" : i == 2 ? "#b" : "";
                        text += "\t" + (i + 1) + "\t\t\t\t";
                        text += info.getCname();
                        for (var j = 16 - info.getCname().getBytes().length; j > 0; j--) {
                            text += " ";
                        }
                        text += "\t\t#k#n#r" + info.getCount();
                        text += "#k#n \t\t#k";
                        text += "";
                    }
                }
				text += "\r\n\r\n";
                cm.sendOkS(text, 3);
                cm.dispose();
        } else {
            cm.sendYesNo("  �ܺã���ȷ���㹻������");
        }
    } else if (status == 2) {
        if (sel == 1) {
            cm.sendNextPrev("��ӭ������ս�����û�������Ļ������������һ��Ҳ�޷���");
        } else {
            //cm.saveLocation("MULUNG_TC");
            //cm.warp(925020000, 0);
			cm.��NPC(2007,5);
            cm.dispose();
        }
    } else if (status == 3) {
        cm.dispose();
    }
}