/*
 ZEVMSð�յ�(079)��Ϸ�����
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
	var ѩ������ = cm.GetPiot("ѩ������", "1");
	var ѩ�������� = ѩ������/3000*100;
    if (status == 0) {
        var selStr = "	Hi~#b#h ##k�����������ӣ����� "+cm.��ʾ��Ʒ(4031311)+" ��������ṩ���ң��ҿ�����ɴ�����ѩ��Ŷ��\r\n\r\n";
		 selStr += "       ��ѩ�˽���: #B"+ѩ��������+"# ["+ѩ������+"/3000]\r\n\r\n";
		
        selStr += " #L1##b��ȡ����#k#l  #L2##b��ȡѩ��#k#l  #L3##b�ύѩ��#k#l  #L4##bѩ������#k#l";
        cm.˵������(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.gainItem(1472063, 1, 1);
                cm.�Ի�����();
                break;
            case 2:
                cm.gainItem(2060006, 800);
                cm.�Ի�����();
                break;
			case 3:
				cm.�Ի�����();
                cm.��NPC(2007,9220004);
                break;	
			case 4:
			var text = "   ������������������< #e#rѩ����#k#n >�������������������� #n\r\n\r\n";
				text += "    ����        \t���         \t\t\tѩ��\r\n";
                var rankinfo_list = cm.getBossRankCountTop("ѩ��");
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
				 break;	
				
        }
    }
}