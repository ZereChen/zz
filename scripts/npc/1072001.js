/*
 ZEVMSð�յ�(079)��Ϸ�����
 ħ��ʦ��תתְ�̹�
 */
var ������� = 4031009;
var ������ = "��˹";
var �ռ���Ʒ = 4031013;
var �ռ����� = 30;
var ������ͼ = 108000200;
var status;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1)
        cm.�Ի�����();
    else {
        if (mode == 1)
            status++;
        else
            status--;
        if (cm.haveItem(�������)) {
            if (status == 0)
                cm.�Ƿ�˵������("Hi #b#h ##k������#b"+������+"#k����������")
            else if (status == 1)
                cm.�Ƿ�˵������("������Ҫ֤�����ʵ����? ");
            else if (status == 2)
                cm.�Ƿ�˵������("�ҿ��Ը���һ�λ���,������ա�");
            else if (status == 3)
                cm.�Ƿ�˵������("�����#b"+�ռ�����+"�� #v"+�ռ���Ʒ+"# #t"+�ռ���Ʒ+"##k��ף����ˡ�");
            else if (status == 4) {
                cm.warp(������ͼ, 0);
                cm.�Ի�����();
            }
        } else {
            cm.˵������("�ܱ�Ǹ,����Ҫ#b#v"+�������+"# #t"+�������+"##k��ȥ��"+������+"��ȡлл��");
            cm.�Ի�����();
        }
    }
}	