/*
 ZEVMSð�յ�(079)��Ϸ�����
 ��ʥʯͷ
 */
var status = 0;
var qChars = new Array(
		"��һ��: ��ħ�����ּ�������NPC��˭��   #³��#�׵�#����#�а�#1",
        "��һ��: �����ص�(�����)û�г��ֵĹ�������һ����   #������#�Ǿ���#�¾���#ʳ�˻�#1",
        "��һ��: ��Ģ����ľ������ˮ�飬��ľ�������������м�����ߵĹ�������һ����   #��Ģ��#ľ��#��ˮ��#��ľ��#4",
        "��һ��: ��ð�յ��еǳ���ҩ�͹�Ч��ȷ���ߵ�����һ����#����ҩˮ + HP2000#����ҩˮ + HP3000#��ɫҩˮ + HP50#��ɫҩˮ + HP200#3",
		"��һ��: �������й�ϵNPC��˭��   #³��#�׵�#����#�ƶ�#4"
		);
var qItems = new Array(
		"�ڶ���: ���й����У����������������ܵõ���ս��Ʒ����ȷ��Ӧ��ϵ�ģ�#������-����ͷ��#���� - ������#ú�� - ճ����������#�� - ˿��#2",
        "�ڶ���: ���й����У����������������ܵõ���ս��Ʒ�ǲ���ȷ��Ӧ��ϵ�ģ�#�д��ʷ�- ������#ú�� - ú����ĭ#��ɫ��ţ - ��ɫ��ţ��#ʳ�˻� - ʳ�˻���Ҷ��#4",
        "�ڶ���: ð�յ�������ҩƷ�У�����ҩƷ�빦Ч����ȷ��Ӧ��ϵ�ģ�#��ɫҩˮ - �ظ� 250 HP#����ҩˮ �� HP400�ָ�#��ɫҩˮ - �ظ� 100 HP#���� �� HP400�ָ�#4",
        "�ڶ���: ð�յ�������ҩƷ�У�����ҩˮ���Իظ�HP50%MP50%��#����ҩˮ#����ҩˮ#������#��Ȫˮ#1",
        "�ڶ���: ð�յ�������ҩƷ�У�����ҩƷ�빦Ч�ǲ���ȷ��Ӧ��ϵ�ģ�#��ɫҩˮ - �ظ� 100 MP#����ҩˮ - �ظ� 300 MP#�峿֮¶����3000MP�ָ�#��ɫҩˮ - �ظ� 50 HP#3");
var qMobs = new Array(
        "������: �����û���ĸ����#Сʯ��#��ţ#����ţ#Ģ����#1",
        "������: ȥ���֮�ǵĴ��ϻ�����ĸ����#����#����ħ#Сʯ��#������#2",
        "������: �ڱ���ѩ��û���ĸ����#Ұ��#ѩ��#Сѩ��#������#4",
        "������: ��ɵĹ�����ʲô��#����#���߱���#Сѩ��#С����#1",
        "������: �����������#���֮��#����ѩ��#ˮ������#���ִ�#2");
var qQuests = new Array(
		"������: �����������ﱻ�ٻ�#������̳#��߳�#���֮��#2ˮ������#1",
		"������: ����ţ��ս��ƷΪ?#����#����#����ţ��#ú̿#3"
		);
var qTowns = new Array(
		"������: ���ټ����Խ���3ת?#30#40#60#70#4",
		"������: ħ��ʦһת���ټ�?#8#10#30#70#1",
        "������: ���ټ����Խ���2ת?#30#40#60#70#1");
var correctAnswer = 0;

function start() {
	if (cm.�жϱ���������().isFull()) {
        cm.sendNext("������������һ����λ��");
        cm.�Ի�����();
        return;
    }
    if (cm.haveItem(4031058, 1)) {
        cm.sendOk("#h #,���Ѿ����� #b#t4031058##k��");
        cm.dispose();
    }
    if (!(cm.haveItem(4031058, 1))) {
        cm.sendNext("�������Ѿ����˺�Զ��·��");
    }
}

function action(mode, type, selection) {
    if (mode == -1)
        cm.dispose();
    else {
        if (mode == 0) {
            cm.sendOk("�´��ټ���");
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 1)
            cm.sendNextPrev("#h #, �������� #b�ڰ�ˮ��#k �ҽ����������Żش�5������,����5�����ⶼ��������õ� #v4031058# #b#t4031058##k.");
        else if (status == 2) {
            if (!cm.haveItem(4005004)) {
                cm.sendOk("#h #, ��û�� #b�ڰ�ˮ��#k");
                cm.dispose();
            } else {
                cm.gainItem(4005004, -1);
                cm.sendSimple("" + getQuestion(qChars[Math.floor(Math.random() * qChars.length)]));
                status = 2;
            }
        } else if (status == 3) {
            if (selection == correctAnswer)
                cm.sendOk("#h # ������.\n׼������һ�⡣");
            else {
                cm.sendOk("�����˵Ĵ�!.\r\n�ܱ�Ǹ������ڸ���һ�� #b�ڰ�ˮ��#k �ſ�������ս!");
                cm.dispose();
            }
        } else if (status == 4)
            cm.sendSimple("" + getQuestion(qItems[Math.floor(Math.random() * qItems.length)]));
        else if (status == 5) {
            if (selection == correctAnswer)
                cm.sendOk("#h # ������.\n׼������һ�⡣");
            else {
                cm.sendOk("�����˵Ĵ�!.\r\n�ܱ�Ǹ������ڸ���һ�� #b�ڰ�ˮ��#k �ſ�������ս!");
                cm.dispose();
            }
        } else if (status == 6) {
            cm.sendSimple("" + getQuestion(qMobs[Math.floor(Math.random() * qMobs.length)]));
            status = 6;
        } else if (status == 7) {
            if (selection == correctAnswer)
                cm.sendOk("#h # ������.\n׼������һ�⡣");
            else {
                cm.sendOk("�����˵Ĵ�!.\r\n�ܱ�Ǹ������ڸ���һ�� #b�ڰ�ˮ��#k �ſ�������ս!");
                cm.dispose();
            }
        } else if (status == 8)
            cm.sendSimple("" + getQuestion(qQuests[Math.floor(Math.random() * qQuests.length)]));
        else if (status == 9) {
            if (selection == correctAnswer) {
                cm.sendOk("#h # ������.\n׼������һ�⡣");
                status = 9;
            } else {
                cm.sendOk("�����˵Ĵ�!.\r\n�ܱ�Ǹ������ڸ���һ�� #b�ڰ�ˮ��#k �ſ�������ս!");
                cm.dispose();
            }
        } else if (status == 10) {
            cm.sendSimple("��\r\n\r\n" + getQuestion(qTowns[Math.floor(Math.random() * qTowns.length)]));
            status = 10;
        } else if (status == 11) {
            if (selection == correctAnswer) {
                cm.gainItem(4031058, 1);
				//������
                //cm.warp(211000001, 0);
                cm.sendOk("��ϲ #h #, \r\n������� #v4031058# ȥ�����תְ�ٰ̹�!.");
                cm.dispose();
            } else {
                cm.sendOk("̫��ϧ�ˣ��ܱ�Ǹ������ڸ���һ�� #b�ڰ�ˮ��#k �ſ�������ս!");
                cm.dispose();
            }
        }
    }
}
function getQuestion(qSet) {
    var q = qSet.split("#");
    var qLine = q[0] + "#b\r\n\r\n#L0#" + q[1] + "#l\r\n#L1#" + q[2] + "#l\r\n#L2#" + q[3] + "#l\r\n#L3#" + q[4] + "#l";
    correctAnswer = parseInt(q[5], 10);
    correctAnswer--;
    return qLine;
}