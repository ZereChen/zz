/*
 ZEVMSð�յ�(079)��Ϸ����˽ű�
 */
//��������
var ���� = [
    [4030002, 10],
    [4030003, 10],
    [4030004, 10],
    [4030005, 10],
    [4030006, 10],
    [4030007, 10],
    [4030008, 10]
];

var ��� = 0;
var ��ȯ = 0;


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
    if (cm.getInventory(1).getItem(1) == null) {
        cm.˵������("���װ������һ��û��װ����");
        cm.dispose();
        return
    }
    var װ�� = cm.��ʾ��Ʒ(cm.getInventory(1).getItem(1).getItemId());
	if(cm.isCash(cm.getInventory(1).getItem(1).getItemId())){
		cm.˵������("���װ������һ�� "+װ��+" ��ʱװ���޷�ϴ����");
        cm.dispose();
        return
	}
    var ϴ������ = 0;
    if (status <= 0) {

        var selStr = "    Hi~#b#h ##k ��ȷ��Ҫϴ����" + װ�� + " �� װ��ϴ��֮�����Ի���ݵ�ǰ����ֵ��������������������������\r\nϴ����ʽ #r������� = ��ǰ���� + ��ǰ��ֵ * 0.5#k\r\n\r\n";
        selStr += "#b#e��ǰ���ԣ�#k#n\r\n";

        selStr += "" + cm.��ʾװ������() + "\r\n";
        selStr += "#d������ϣ�����������������������������������������#k\r\n";
        for (var ii = 0; ii < ����.length; ii++) {
            selStr += "    #i" + ����[ii][0] + "#  #b#t" + ����[ii][0] + "##k [" + ����[ii][1] + " / #r#c " + ����[ii][0] + "##k]\r\n";
            if (ii % 3 == 0) {
                selStr += "";
            }
        }
        selStr += "\r\n";
        if (��� > 0 || ��ȯ > 0) {
            selStr += "#d������ã�����������������������������������������#k\r\n";
            if (��� > 0) {
                selStr += "    #v5200000##b  ��� #k[" + ��� + " / #r" + cm.�жϽ��() + "#k]\r\n";
            }
            if (��ȯ > 0) {
                selStr += "    #v5440000##b  ��ȯ #k[" + ��ȯ + " / #r" + cm.�жϵ�ȯ() + "#k]\r\n";
            }
        }
        selStr += "#d������������������������������������������������������#k\r\n";
        selStr += "\t\t\t\t\t#L1##b��ʼϴ��#k#l\r\n";


        cm.sendSimple(selStr)
    } else if (status == 1) {
        switch (selection) {
            case 1:
                if (cm.�жϽ��() < ��� || cm.�жϵ�ȯ() < ��ȯ) {
                    cm.˵������("�������ò�����");
                    cm.�Ի�����();
                    return;
                }
                for (var i = 0; i < ����.length; i++) {
                    if (!cm.haveItem(����[i][0], ����[i][1])) {
                        cm.˵������("#i" + ����[i][0] + "#  #b#t" + ����[i][0] + "##k ��Ҫ #r" + ����[i][1] + "#k ��");
                        cm.�Ի�����();
                        return;
                    }
                }
				for (var i = 0; i < ����.length; i++) {
					cm.����Ʒ(����[i][0], ����[i][1]);
				}
                cm.װ��ϴ��();
                cm.�Ի�����();
                cm.��NPC(1032002, 6);
            break;
        }
    }
}