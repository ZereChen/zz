/*
 ZEVMSð�յ�(079)��Ϸ�����
 */

var status;
var choice;
var guildName;
var partymembers;

function start() {
    partymembers = cm.getPartyMembers();
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        cm.�Ի�����();
        return;
    }
    if (status == 0) {
        cm.sendSimple("   ����������ŷ�� �ܸ���Ϊ������#k\r\n\r\n#b#L0#����Ҫ֪������������ʲô#l\r\n#L1#��Ҫ��ô��������������#l\r\n#L2#����Ҫ������������#l\r\n#L3#����Ҫ��������Ĺ��ᵽ����#l\r\n#L4#����Ҫ��ɢ��������#l");
    } else if (status == 1) {
        choice = selection;
        if (selection == 0) {
            cm.˵������("�������˾����������Ĺ����Ա����������һЩ��Ȥ�����顣");
            cm.�Ի�����();
        } else if (selection == 1) {
            cm.˵������("Ϊ�˳����������ˣ���������Ļ᳤��Ҫ��ӣ�Ȼ����������Ķӳ��ͻ�ѡΪ�������˵Ļ᳤��");
            cm.�Ի�����();
        } else if (selection == 2) {
            if (cm.getPlayer().getParty() == null || partymembers == null || partymembers.size() != 2 || !cm.isLeader()) {
                cm.˵������("�㲻�ܴ���һ���������ˣ�ֱ�����ҵ���һ�����ᡣ");
                cm.�Ի�����();
            } else if (partymembers.get(0).getGuildId() <= 0 || partymembers.get(0).getGuildRank() > 1) {
                cm.˵������("�㲻�ܴ���һ���������ˣ�ֱ�������Լ��Ĺ��ᡣ");
                cm.�Ի�����();
            } else if (partymembers.get(1).getGuildId() <= 0 || partymembers.get(1).getGuildRank() > 1) {
                cm.˵������("��ĳ�Ա�ƺ�û���Լ��Ĺ��ᡣ");
                cm.�Ի�����();
            } else {
                var gs = cm.getGuild(cm.getPlayer().getGuildId());
                var gs2 = cm.getGuild(partymembers.get(1).getGuildId());
                if (gs.getAllianceId() > 5) {
                    cm.˵������("�㲻���ٴ�����Ϊ���Ѿ���������Ϊͬ���ˡ�");
                    cm.�Ի�����();
                } else if (gs2.getAllianceId() > 1) {
                    cm.˵������("��ĳ�Ա�Ѿ������������Ϊͬ���ˡ�");
                    cm.�Ի�����();
                } else if (cm.partyMembersInMap() < 1) {
                    cm.˵������("��ȷ��������Ա��ͬ�ŵ�ͼ�ϡ�");
                    cm.�Ի�����();
                } else
                    cm.sendYesNo("Ŷ��������Ȥ����һ���������ˣ�");
            }
        } else if (selection == 3) {
            if (cm.getPlayer().getGuildRank() == 1 && cm.getPlayer().getAllianceRank() == 1) {
                cm.sendYesNo("Ϊ�����ӿ����Ҫ֧�� #b10000000#k ���. ��ȷ��Ҫ������");
            } else {
                cm.˵������("ֻ�й������˳������������ˡ�");
                cm.�Ի�����();
            }
        } else if (selection == 4) {
            if (cm.getPlayer().getGuildRank() == 1 && cm.getPlayer().getAllianceRank() == 1) {
                cm.sendYesNo("�������Ҫ��ɢ�������ˣ���");
            } else {
                cm.˵������("ֻ�й������˳��ſ��Խ�ɢ��");
                cm.�Ի�����();
            }
        }
    } else if (status == 2) {
        if (choice == 2) {
            cm.sendGetText("��������������Ҫ�Ĺ����������ơ�");
        } else if (choice == 3) {
            if (cm.getPlayer().getGuildId() <= 0) {
                cm.˵������("�㲻�����Ӳ��湫�����ˡ�");
                cm.�Ի�����();
            } else {
                if (cm.addCapacityToAlliance()) {
                    cm.˵������("��ɹ������˹�������������");
                } else {
                    cm.˵������("�ܱ�Ǹ��������Ĺ������������Ѿ����ˣ����Բ��������䡣");
                }
                cm.�Ի�����();
            }
        } else if (choice == 4) {
            if (cm.getPlayer().getGuildId() <= 0) {
                cm.˵������("�㲻�ܽ�ɢ�����ڵĹ������ˡ�");
                cm.�Ի�����();
            } else {
                if (cm.disbandAlliance()) {
                    cm.˵������("�ɹ���ɢ�������ˡ�");
                } else {
                    cm.˵������("��ɢ��������ʱ��������");
                }
                cm.�Ի�����();
            }
        }
    } else if (status == 3) {
        guildName = cm.getText();
        cm.sendYesNo("��� #b" + guildName + "#k ������Ҫ�Ĺ������������𣿣�");
    } else if (status == 4) {
        if (!cm.createAlliance(guildName)) {
            cm.sendNext("������ֲ���ʹ�ã��볢�������ġ�");
            status = 1;
            choice = 2;
        } else
            cm.˵������("�ɹ��Ĵ����˹������ˣ���");
        cm.�Ի�����();
    }
}