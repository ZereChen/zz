/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�������ʹ��
 */
//���� r ��ֵ
var r = "����ʹ��";
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
    //��ʼ
    if (status == 0) {

        var �ı� = "  Hi~#b#h ##k������Ҫ��һ������ǩ��һ�����������ܻ����������#r����ʹ��#kѫ�£��������ѫ�²������õ�Ŷ��\r\n";
        if (cm.�жϵ�ͼ() == 100000000) {
            �ı� += "#L100000000##b#m100000000#ǩ��#k#l";
        } else if (cm.�жϵ�ͼ() == 101000000) {
            �ı� += "#L101000000##b#m101000000#ǩ��#k#l";
        } else if (cm.�жϵ�ͼ() == 102000000) {
            �ı� += "#L102000000##b#m102000000#ǩ��#k#l";
        } else if (cm.�жϵ�ͼ() == 103000000) {
            �ı� += "#L103000000##b#m103000000#ǩ��#k#l";
        } else if (cm.�жϵ�ͼ() == 105040300) {
            �ı� += "#L105040300##b#m105040300#ǩ��#k#l";
        } else if (cm.�жϵ�ͼ() == 120000000) {
            �ı� += "#L120000000##b#m120000000#ǩ��#k#l";
        } else if (cm.�жϵ�ͼ() == 211000000) {
            �ı� += "#L211000000##b#m211000000#ǩ��#k#l";
        } else if (cm.�жϵ�ͼ() == 230000000) {
            �ı� += "#L230000000##b#m230000000#ǩ��#k#l";
        } else if (cm.�жϵ�ͼ() == 220000000) {
            �ı� += "#L220000000##b#m220000000#ǩ��#k#l";
		} else if (cm.�жϵ�ͼ() == 221000000) {
            �ı� += "#L221000000##b#m221000000#ǩ��#k#l";
		} else if (cm.�жϵ�ͼ() == 222000000) {
            �ı� += "#L222000000##b#m222000000#ǩ��#k#l";
		} else if (cm.�жϵ�ͼ() == 240000000) {
            �ı� += "#L240000000##b#m240000000#ǩ��#k#l";
		} else if (cm.�жϵ�ͼ() == 250000000) {
            �ı� += "#L250000000##b#m250000000#ǩ��#k#l";
		} else if (cm.�жϵ�ͼ() == 251000000) {
            �ı� += "#L251000000##b#m251000000#ǩ��#k#l";
		} else if (cm.�жϵ�ͼ() == 260000000) {
            �ı� += "#L260000000##b#m260000000#ǩ��#k#l";
		} else if (cm.�жϵ�ͼ() == 261000000) {
            �ı� += "#L261000000##b#m261000000#ǩ��#k#l";
		} else if (cm.�жϵ�ͼ() == 104000000) {
            �ı� += "#L104000000##b#m104000000#ǩ��#k#l";
		} else if (cm.�жϵ�ͼ() == 200000000) {
            �ı� += "#L200000000##b#m200000000#ǩ��#k#l";
        }

        cm.˵������(�ı�);
    } else if (status == 1) {
        switch (selection) {
            case 100000000:
                //�趨 b ���� ���ִ尮��ʹ��
                var b = "���ִ尮��ʹ��";
                //�趨 a ���� cm.getBossRank("���ִ尮��ʹ��", 2)��
                var a = cm.getBossRank(b, 2);
                //�жϽ����Ƿ��Ѿ�ǩ���ˣ�r �ڽű���ͷ�Ѿ�������һ��ֵ
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    //���ݳ�ʼ��
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    //������ֵ���ڻ��ߵ���7��ǩ��7��󣬵ڰ���ǩ���ͱ����ȡ
                    if (a >= 7) {
                        //ѫ�´���
                        var ѫ�� = 1142014;
                        //��ѫ��
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        //���ǩ��
                        cm.setBossRankCount(b, -a);
                        //��ʾ˵������
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        //��ֵΪ b ��ǩ��
                        cm.setBossRankCount(b, 1);
                        //��ʾǩ��˵��
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    //��¼����ǩ��
                    cm.����ÿ��ֵ(r);
                } else {
                    //�Ѿ�ǩ���Ͳ���ǩ
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                //�����������Ի�����
                cm.�Ի�����();
                break;
            case 101000000:
                var b = "ħ�����ְ���ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142015;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
            case 102000000:
                var b = "��ʿ���䰮��ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142016;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
            case 103000000:
                var b = "�������а���ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142017;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
            case 105040300:
                var b = "����֮�ǰ���ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142018;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
            case 120000000:
                var b = "ŵ����˹����ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142019;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
            case 211000000:
                var b = "����ѩ����ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142020;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
            case 230000000:
                var b = "ˮ�����簮��ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142021;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
			case 220000000:
                var b = "��߳ǰ���ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142022;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
			case 221000000:
                var b = "���������������ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142023;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
			case 222000000:
                var b = "ͯ���尮��ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142024;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
			case 240000000:
                var b = "��ľ�尮��ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142025;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
			case 250000000:
                var b = "���갮��ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142026;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
			case 251000000:
                var b = "�ٲ��ð���ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142027;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
			case 260000000:
                var b = "���ﰲ�ذ���ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142028;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
			case 261000000:
                var b = "������ǰ���ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142029;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
			case 104000000:
                var b = "����۰���ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142030;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
			case 200000000:
                var b = "���֮�ǰ���ʹ��";
                var a = cm.getBossRank(b, 2);
                if (cm.�ж�ÿ��ֵ(r) <= 0) {
                    if (a == -1) {
                        cm.setBossRankCount(b, 0);
                    }
                    if (a >= 7) {
                        var ѫ�� = 1142031;
                        cm.gainItemPeriod(ѫ��, 1, 7);
                        cm.setBossRankCount(b, -a);
                        cm.˵������("��ϲ���� " + cm.��ʾ��Ʒ(ѫ��) + " ��");
                    } else {
                        cm.setBossRankCount(b, 1);
                        cm.˵������("��ϲ��#r(" + b + ")#kǩ���ɹ����Ѿ�ǩ�� #r" + (a + 1) + "#k �Ρ�");
                    }
                    cm.����ÿ��ֵ(r);
                } else {
                    cm.˵������("�����Ѿ�ǩ���ˡ����Ѿ�ǩ�� #r" + a + "#k �Ρ�");
                }
                cm.�Ի�����();
                break;
            default:
                cm.˵������("���ﲻ�����ǣ�û����Ϣ��");
                cm.�Ի�����();
                break;

        }
    }
}



























