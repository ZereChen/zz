package handling.world;

import static a.�������ݿ�.ȡ���ӱ�ע;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class MapleParty implements Serializable {

    private static final long serialVersionUID = 9179541993413738569L;
    private MaplePartyCharacter leader;
    private List<MaplePartyCharacter> members = new LinkedList<MaplePartyCharacter>();
     public static int �����˺� = 0;
    public static int ͨ��BOSS = 0;
    public static int ��ͼ���� = 0;
    public static int ͨ����ͼ = 0;
    public static int ���������߳� = 0;
    public static int �������� = 0;
    public static int ��������Ƶ�� = 0;
    public static int �������˵�ͼ = 0;
    public static int ������������X = 0;
    public static int ������������Y = 0;
    public static int ��������ʱ�� = 0;
    public static int ����ħA���� = 0;
    public static int ����ħB���� = 0;
    public static int ����ħC���� = 0;
    public static int ����ħD���� = 0;
    public static int ����ְҵ = 0;
    public static int �صȽ� = 0;
    public static int һ�Ƚ� = 0;
    public static int ���Ƚ� = 0;
    public static int ���Ƚ� = 0;
    private int id;
    public static int ����� = 1;
    public static int ����������� = 0;
    public static int OX���� = 0;
    public static int �����޸� = 0;
    public static String �������� = "";
    public static String IP��ַ = "";
    public static int ���������ģʽ = 0;
    public static int �������� = 0;
    public static int ��ȴ1 = 0;
    public static String �����˺� = "";
    public static int ������ȴ = 0;
    public static int �������� = 0;
    public static int ������ȴ1 = 0;
    public static int ������ȴ2 = 0;
    public static int ������ȴ3 = 0;
    public static int yzcs = 0;
    public static int �汾��� = 0;
    public static int �汾���2 = 0;
    public static int �����˴��� = 0;
    public static int ÿ������ = 0;
    public static int �˺���ʾ = 0;
    public static int ������ = 0;
    public static int ��Ϣͬ�� = 0;
    public static int ����˵��� = 0;
    public static int ���������ʱ�� = 0;
    public static int ��ҵ�½���� = 0;
    public static int ��ɫ��½���� = 0;
    public static int ��Ϸ����Ⱥ��� = 0;
    public static int ����ͳ�� = 0;
    public static int ����ģʽ = 0;
    public static int ������ = 0;
    public static int ����3 = 0;
    public static int �г����� = 0;
    public static int ���ܵ��� = 0;
    public static int �ƺ����� = 0;
    public static int ����ɱ� = 0;
    public static int ˫����ģʽ = 0;
    public static int �������� = 0;
    public static int �̳ǳ���֪ͨ = 1;
    ///////////////////////////
    public static int ZEVMSmxd = 0;
    ///////////////////////////
    public static int ����ʹ��ģʽ = 0;
    public static int ����ʹ��ģʽ = 0;
    public static int Զ���������� = 0;
    public static int ǿ�Ƹ��¿��� = 0;
    public static int ����Ķ����� = 0;
    public static int ���ü�⿪�� = 0;
    public static int �Զ����¿��� = 0;
    public static int �ű��༭����Ȩ���� = 0;
    //
    public static int �ű��ж�1 = 0;
    public static int �ű���ȡ1 = 0;
    public static int �ű�����1 = 0;
    public static int �ű��ж����� = 0;
    public static int �ű���ȡ���� = 0;
    public static int ���˷��� = 0;
    public static String �������ʵ� = "";
    //
    public static int ����� = 0;
    public static int ѩ���� = 0;
    //
    public static String ����3010025 = ȡ���ӱ�ע(3010025);
    public static String ����3010100 = ȡ���ӱ�ע(3010100);
    public static String ����3012002 = ȡ���ӱ�ע(3012002);

    public MapleParty(int id, MaplePartyCharacter chrfor) {
        this.leader = chrfor;
        this.members.add(this.leader);
        this.id = id;
    }

    public boolean containsMembers(MaplePartyCharacter member) {
        return members.contains(member);
    }

    public void addMember(MaplePartyCharacter member) {
        members.add(member);
    }

    public void removeMember(MaplePartyCharacter member) {
        members.remove(member);
    }

    public void updateMember(MaplePartyCharacter member) {
        for (int i = 0; i < members.size(); i++) {
            MaplePartyCharacter chr = members.get(i);
            if (chr.equals(member)) {
                members.set(i, member);
            }
        }
    }

    public MaplePartyCharacter getMemberById(int id) {
        for (MaplePartyCharacter chr : members) {
            if (chr.getId() == id) {
                return chr;
            }
        }
        return null;
    }

    public MaplePartyCharacter getMemberByIndex(int index) {
        return members.get(index);
    }

    public Collection<MaplePartyCharacter> getMembers() {
        return new LinkedList<MaplePartyCharacter>(members);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MaplePartyCharacter getLeader() {
        return leader;
    }

    public void setLeader(MaplePartyCharacter nLeader) {
        leader = nLeader;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MapleParty other = (MapleParty) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }
}
