package quest;

public class Quest {

    public static boolean Quest(int a) {
        switch (a) {
            case 3936://���ﰲ������
            case 2000://�����ĵ���
            case 2103://��������
            case 2104://��ϲ�ĺ�ɫë��
            case 2254://ɳĮ�Ŀ��ֿ���
            case 2257://���ֿ����ĸ��
            case 2258://����˵������������
            case 2259://����˵��Ы�ӻ�����
            case 2260://ǰ��Ģ����
            case 2261://�����ظ�1
            case 2262://�����ظ�2
            case 2263://�����ظ�3
            case 2232://��½ͬѧ
            case 3953://ɳĮ
            case 6029://���켼��
            case 21729://ս��ĵ��Ĵ��ռ�
                return true;
            default:
                return false;
        }
    }

    public static boolean canForfeit(int questid) {
        switch (questid) {
            case 6029://���켼��
            case 2001://�����ĵ���
            case 2261://�����ظ�1
            case 2262://�����ظ�2
            case 2263://�����ظ�3
            case 20010:
            case 20015:
            case 20020:
                return false;
            default:
                return true;
        }
    }
}
