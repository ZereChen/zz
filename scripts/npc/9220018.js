/*
 ZEVMSð�յ�(079)��Ϸ�����
 */
function action(mode, type, selection) {

    if (cm.getPlayer().getParty() == null || !cm.isLeader()) {
        cm.sendOk("���Ҷӳ�������̸����");
	} else	if(cm.�ж��Ŷ���Ʒ(5252001)!=0){
		 cm.sendOk("�㣬������Ķ�Աû�� "+cm.��ʾ��Ʒ(5252001)+"��");
    } else {
        var next = true;
        if (cm.�ж�ָ����ͼ�������(674030000) > 0 || cm.�ж�ָ����ͼ�������(674030200) > 0 || cm.�ж�ָ����ͼ�������(674030300) > 0) {
            next = false;
        }
        if (next) {
            var em = cm.getEventManager("MV");
            if (em == null) {
                cm.sendOk("Ŀǰ��������һ��״�������Ժ��ٳ��ԡ�");
            } else {
                cm.givePartyItems(5252001, -1);
                em.startInstance(cm.getPlayer().getParty(), cm.getPlayer().getMap());
            }
        } else {
            cm.sendOk("�Ѿ���������ս��Ŷ��");
        }
    }
    cm.�Ի�����();
}