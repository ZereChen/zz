/*
 ZEVMSð�յ�(079)��Ϸ����˽ű�
 װ������
 */
//��������
var ���� = [
    [4032391, 10],
    [4032392, 10],
    [4032393, 10],
    [4032394, 10],
    [4032395, 10],
    [4032396, 10],
    [4032397, 10]
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

    var ϴ������ = 0;
    if (status <= 0) {

        var selStr = "";
        selStr += "�һ� "+cm.��ʾ��Ʒ(1122017)+" [�Ƴ��ۼƣ�ֱ����Ч]\r\n\r\n";
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
        selStr += "\t\t\t\t\t#L1##b��Ҫ�һ�#k#l\r\n";


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
				cm.gainItem(1122017,1,1);
				cm.ȫ����ɫ����("[ð�յ���ӪԱ] : ��ϲ��� "+cm.getPlayer().getName()+" �ɹ��һ� ����������")
                cm.�Ի�����();
            break;
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
        }
    }
}