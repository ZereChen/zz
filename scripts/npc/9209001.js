/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�������ĩ�г�
 */

status = -1;
var sel, sel2;

function start() {
    cm.˵������("	  Hi~#b#h ##k ���켯�п�ҵ�ˣ����벻���ȥ����һ��ö����أ�����Ŀǰ������Ŷ�����ǲ�Ҫȥ�ĺá�");//\r\n\r\n#L0#����ȥ����#
	cm.dispose();
    return;
}

function action(mode, type, selection) {
    status++;
    if (status == 6 && mode == 1) {
        sel2 = undefined;
        status = 0;
    }
    if (mode != 1) {
        if (mode == 0 && type == 0)
            status -= 2;
        else {
            cm.dispose();
            return;
        }
    }
    if (status == 0) {
        if (sel == undefined)
            sel = selection;
        if (selection == 0) {
            cm.sendNext("���ˣ��ҽ����㵽���е�ͼ.");
        } else
            cm.sendSimple("���������˽⼯���Ĳ���??#b\r\n#L0#����ʲô�ط�??\r\n#L1#���ڼ��и�ʲô����??\r\n#L2#��û���κ�����");
    } else if (status == 1) {
        if (sel == 0) {
            cm.saveLocation("EVENT");
            cm.warp(680100000 + parseInt(Math.random() * 3));
            cm.dispose();
        } else if (selection == 0) {
            cm.sendNext("����ֻ���ڼ��տ��š��������������������Ҳ���������ҵ��ҽ���, �Ҽ�����������!!!");
            status -= 2;
        } else if (selection == 1)
            cm.sendSimple("������ڼ����ҵ������ط������ҵ��ĺ�����Ʒ.#b\r\n#L0#����������Ʒ\r\n#L1#��������ũ��ҵ��");
        else {
            cm.sendNext("�Ҳ���û���κ����⣬��������������Ҳ�����뷨��ѯ���㷳��ʲô~");
            cm.dispose();
        }
    } else if (status == 2) {
        if (sel2 == undefined)
            sel2 = selection;
        if (sel2 == 0)
            cm.sendNext("������ҵ��������ڼ���,�۸�������б䶯,��������������ı�۸�ǰ������,��Ϊ���Ǳ䶯��ʱ��ܱ��˵�!!");
        else
            cm.sendNext("���������㻹�����ҵ������������ڼ�������,����������������ĵ�,ֱ����������һֻ�óԵļ�!");
    } else if (status == 3) {
        if (sel2 == 0)
            cm.sendNextPrev("��������еĹ���������ظ��̼ҵ��н飬������������������κγ���һ�����ڵ�ʱ������������ȷ����������");
        else
            cm.sendNextPrev("����������ֻ�����κ��˵ļ����������ʱ�֤��֧�����Ĵ�����ȡ�����չ˺�.");
    } else if (status == 4) {
        if (sel2 == 0)
            cm.sendNextPrev("�������������ĵ�����һ���������⽫�����ǵģ�����ʱ��Ϳ���ʹ�������󡣼۸���������Сʱ�����Լǵ�Ҫ�������.");
        else
            cm.sendNextPrev("����������ɹ��ɳ�Ϊ���û����䣬����ᱨ���㡣������������ģ�����������.");
    } else if (status == 5) {
        if (sel2 == 0)
            cm.sendNextPrev("ͨ�����������ڼ��м۸���۸��̼ҵ��н�ʱ�����ֵ�����������ҵ��Ļ��ǣ�");
        else
            cm.sendNextPrev("�������ڼ�������鿴����������������������ڷܣ���Ϊ���úͼ���һ��ɳ���EXP������");
    }
}