/*
 ZEVMSð�յ�(079)��Ϸ�����
 */
 //BGM·��
var BMG = [
	"BgmUI/Title",
	"BgmUI/WCSelect",
	"BgmTW/CakeMap",
	"Bgm00/FloralLife",
	"Bgm00/GoPicnic",
	"Bgm00/Nightmare",
	"Bgm00/RestNPeace",
	"Bgm00/SleepyWood",
	"Bgm01/BadGuys",
	"Bgm01/CavaBien",
	"Bgm02/AboveTheTreetops",
	"Bgm02/MissingYou",
	"Bgm02/WhenTheMorningComes",
	"Bgm03/Beachway",
	"Bgm03/BlueSky",
	"Bgm03/Elfwood",
	"Bgm03/SnowyVillage",
	"Bgm03/Subway",
	"Bgm04/Shinin'Harbor",
	"Bgm04/WhiteChristmas",
	"Bgm06/FantasticThinking",
	"Bgm07/Fantasia",
	"Bgm07/FunnyTimeMaker",
	"Bgm07/HighEnough",
	"Bgm07/PlotOfPixie",
	"BgmUI/ShopBgm",
	
];

var ˵������ = " 	Hi~ #b#h ##k ����������Ե㲥ð�յ����е�BGMŶ��";
var ���ֺ� = 5100000;

var sels;
var �ű�ִ�� = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        �ű�ִ��++;
    } else if (mode == 0) {
        �ű�ִ��--;
    } else {
        cm.�Ի�����();
        return;
    }
    if (�ű�ִ�� == 0) {
        var �ı���Ϣ = "";
        for (var i = 0; i < BMG.length; i++) {
            �ı���Ϣ += "#b#L" + i + "#";
            �ı���Ϣ += "" + BMG[i] + "#l#k\r\n";
        }
        cm.sendSimple("" + ˵������ + "������Ҫ "+cm.��ʾ��Ʒ(���ֺ�)+" �ſ��Խ��е㲥��\r\n\r\n" + �ı���Ϣ + "");
    } else if (�ű�ִ�� == 1) {
        sels = selection;
        cm.�Ƿ�˵������("ȷ��Ҫ�㲥 #b" + BMG[sels] + "#k �� �� \r\n");
    } else if (�ű�ִ�� == 2) {
		if(!cm.�ж���Ʒ����(���ֺ�,1)){
			cm.˵������("��Ҫ "+cm.��ʾ��Ʒ(���ֺ�)+" x 1 ");
			cm.�Ի�����();
			return;
		}
        cm.���˵��(BMG[sels]);
		cm.����Ʒ(���ֺ�,1)
        cm.˵������("�㲥 #b" + BMG[sels] + "#k �ɹ���");
		cm.��ͼ����("[���ֵ㲥]:��� "+cm.getChar().getName()+" �ڱ���ͼ�㲥 " + BMG[sels] + " ")
        cm.�Ի�����();
    } else {
        cm.˵������("#r��������: mode : " + mode + " �ű�ִ�� : " + �ű�ִ��);
        cm.�Ի�����();
    }
}















