package pvp;

import handling.channel.handler.AttackInfo;
import server.ServerProperties;

public class Pvpskill {

    public static int SK(int lbound, int ubound) {
        return (int) ((Math.random() * (ubound - lbound)) + lbound);
    }

    public static int skilname(String a, String b) {
        return (int) ((Math.random() * (Integer.parseInt(ServerProperties.getProperty(b)) - Integer.parseInt(ServerProperties.getProperty(a)))) + Integer.parseInt(ServerProperties.getProperty(b)));
    }

    public static int skilname(String a) {
        return Integer.parseInt(ServerProperties.getProperty(a));
    }

    static boolean ��ս����(AttackInfo attack) {
        switch (attack.skill) {
            case 1001004:    //ǿ������
            case 1001005:    //Ⱥ�幥��
            case 4001334:    //������
            case 4201005:    //����ն
            case 1111003:    //�ֻ�
            case 1311004:    //��˫ì
            case 1311003:    //��˫ǹ
            case 1311002:    //ì����
            case 1311005:    //��֮�׼�
            case 1311001:    //ǹ����
            case 1121008:    //�������
            case 1221009:    //��������
            case 1121006:    //ͻ��
            case 1221007:    //ͻ��
            case 1321003:    //ͻ��
            case 4221001:    //��ɱ
            case 5001001:    //����ȭ
            case 5001002:    //������
            case 3101003:    //ǿ��
            case 3201003:    //ǿ��

                return true;
        }
        return false;
    }

    static boolean Զ�̹���(AttackInfo attack) {
        switch (attack.skill) {
            case 2001004:    //ħ����
            case 2001005:    //ħ��˫��
            case 3001004:    //�ϻ��
            case 3001005:    //������
            case 4001344:    //˫��ն
            case 2101004:    //�����
            case 2101005:    //������
            case 2201004:    //������
            case 2301005:    //ʥ����
            case 4101005:    //��������
            case 2211002:    //������
            case 2211003:    //����ǹ
            case 3111006:    //��ɨ��
            case 3211006:    //��ɨ��
            case 4111005:    //���ط���
            case 4211002:    //��Ҷն
            case 2121003:    //�����
            case 2221006:    //��������
            case 2221003:    //������
            case 2111006:    //�𶾺ϻ�
            case 2211006:    //���׺ϻ�
            case 2321007:    //��â�ɼ�
            case 3121003:    //���������
            case 3121004:    //�������
            case 3221003:    //���������
            case 3221001:    //��͸��
            case 3221007:    //һ��Ҫ����
            case 4121003:    //����
            case 4121007:    //�����������
            case 4221007:    //һ��˫��
            case 4221003:    //����
            case 4111004:    //��Ǯ����
            case 5001003:    //˫�����
                return true;
        }
        return false;
    }

    static boolean Ⱥ�幥��(AttackInfo attack) {
        switch (attack.skill) {
            case 1111008:    //������
            case 2201005:    //�׵���
            case 3101005:    //��ը��
            case 3201005:    //��͸��
            case 1111006:    //���ͻ��
            case 1111005:    //��ѩì
            case 1211002:    //���Թ���
            case 1311006:    //������
            case 2111002:    //ĩ������
            case 2111003:    //��������
            case 2311004:    //ʥ��
            case 3111004:    //����
            case 3111003:    //�һ��
            case 3211004:    //������
            case 3211003:    //������
            case 4211004:    //������
            case 1221011:    //ʥ��
            case 2121001:    //����֮��
            case 2121007:    //�콵����
            case 2121006:    //��������
            case 2221001:    //����֮��
            case 2221007:    //��˪����
            case 2321008:    //ʥ������
            case 2321001:    //����֮��
            case 4121004:    //���߷���
            case 4121008:    //���߳��
            case 4221004:    //���߷���
            case 4111003:    //Ӱ����
                return true;
        }
        return false;
    }
}
