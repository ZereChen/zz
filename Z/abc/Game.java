package abc;

import client.Class1;
import client.Class2;
import client.�ű��༭��;
import database.DatabaseConnection;
import static download.Toupdate.�����ļ�;
import handling.world.MapleParty;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import server.ServerProperties;
import tools.FileoutputUtil;
import static tools.FileoutputUtil.CurrentReadable_Date;
import static tools.FileoutputUtil.CurrentReadable_Time;

/*

 * ������Դ�� ����Ŀ���Դ���ڲ�һЩ���ݣ����ܵȵȡ� 
 */
public class Game {

    //���������
    public static String ��������� = "ZEVMSð�յ�(079)��Ϸ�����";
    //���������
    public static String ��ȫϵͳ = "<��ȫ����>\r\n\r\nVer.1.0\r\n\r\n";
    //���������
    public static String �ٷ���վ = "www.zevmxd.com";
    //���������
    public static String ���³��� = "���³���.exe";
    //����˰汾��
    public static int �汾 = 26;
    //��ֹδ��֤�ű�ʹ��*
    public static String ���ڱ��� = "ZEV.Maplestory(079)Ver." + �汾 + " ����:" + �ٷ���վ + " ";
    //�ͻ��˰汾��ZEVMS���³���
    public static final short ���ͻ��˰汾1 = 79;
    //�ͻ��˰汾��2
    public static final String ���ͻ��˰汾2 = "1";
    //Ƶ���˿�
    public static int �̳Ƕ˿� = 7585;
    //����QQ
    public static String ����QQ = "755543306";
    //����
    public static String �����ǳ� = "����ؼ";
    //�ٷ�Ⱥ
    public static String �ٷ�Ⱥ�� = "66141952";
    //��������
    public static int �������� = 3;
    //�ٷ�Ⱥ����
    public static String �ٷ�Ⱥ���� = "https://jq.qq.com/?_wv=1027&k=51yai7u";
    //��������ټ��ָ��IP
    public static String IP��ַ = "123.207.53.97";
    //�����������ʱ��
    public static String ����ʱ�� = "2020-05-01 00:00";
    //�������ȡʱ��
    public static String ��ȡ����ʱ�� = "http://baidu.com";
    //��ǰ����ʱ��
    public static String ��ǰʱ�� = "" + ��ȡ����ʱ��(��ȡ����ʱ��) + "";
    //ÿ����������ʱ�䣨׼����
    public static int ����ÿ������ʱ�� = 1;
    //ÿ����������ʱ�䣨��ʼ����
    public static int ׼������ÿ������ʱ�� = 23;
    //������Զ���ʱ������׼����
    public static int ׼��������Զ����� = 15;
    //������Զ���ʱ��������ʼ������
    public static int ������Զ����� = 16;
    //��������ټ��ָ����ַ
    public static String �������ٿ��� = "��";
    public static String �������� = "www.baidu.com";
    //���������Ҫ��*���ڸ������޷�����
    public static String �����������Ҫ���� = "��";

    public static int �����������Ҫ = 16 * 1024 * 1024;//KB
    //�����IP��ַ*���������ļ�
    //public static String �����IP��ַ = MapleParty.IP��ַ;
    //����˿�������*���������ļ�
    public static String �������� = MapleParty.��������;

    //����˵���
    public static String ���� = "��";
    public static String ����2 = "��";
    public static String ����3 = "��";
    public static String OX���� = "��";
    public static String ������� = "��";
    public static String ������� = "��";
    public static String ��ҵ��� = "��";
    public static String ��ͼ�ű����� = "��";
    //���ܶ���ѡ��
    public static String ԭʼ���� = "��";
    public static String ����1 = "��";
    public static String ����2 = "��";
    public static String ����3 = "��";
    public static String ����4 = "��";
    //ָ��ؼ�
    public static String �ȼ� = "��";
    public static String �������� = "��";
    public static String �޵� = "��";
    public static String ˢ���ܵ� = "��";
    public static String ˢ������ = "��";
    public static String ˢ = "��";
    public static String ���� = "��";
    public static String ˢ�� = "��";
    public static String ���� = "��";
    public static String ��� = "��";
    public static String ���2 = "��";
    public static String �ҵ�λ�� = "��";
    public static String �ٻ����� = "��";
    public static String ���� = "��";
    //�ʱ������
    public static int ��ߵػʱ�� = 20 * 60 * 1000;
    public static int ��¥�ʱ�� = 20 * 60 * 1000;
    //��ֹδ��֤�ű�ʹ��*
    public static String ��ֹδ��֤�ű�ʹ�� = "��";
    //����˵������
    public static int �������� = 1;
    public static String ����˵������ = "�����Ŀǰģʽ:�����棬���� " + �������� + " ��";
    public static String ������������ = "Ŀǰ�����Ϊ�����棬���� " + �������� + " �˵�½��Ϸ�����������������飬������������������ƾ������Ȼ���л�Ϊ����ģʽ";
    //����˵������
    public static int �������� = 3;
    public static String ����˵������ = "�����Ŀǰģʽ:�����棬���� " + �������� + " ��";
    public static String ������������ = "Ŀǰ�����Ϊ�����棬���� " + �������� + " �˵�½��Ϸ��������������������빺��˽���汾";
    //˽��˵������
    public static String ˽��˵������ = "�����Ŀǰģʽ:˽���棬����������";
    public static String ˽���������� = ServerProperties.getProperty("ZEV.ServerMessage");
    //NPC�����ı���ʾ
    public static String NPC�����ı���ʾ = "���ǲ�����Ϊ����Щʲô�أ�";
    //�ű�NPC����ǰ׺
    public static String NPCǰ׺ = "cm";
    public static String NPCǰ׺2 = "MMP";

    public static boolean ��������(String a) {
        switch (a) {
            case "��":
                return true;
            default:
                return false;
        }
    }

    public static boolean ��Сʱ��ʱ����(int a) {
        switch (a) {
            case 5211060:
                return true;
            default:
                return false;
        }
    }

    public static boolean ��Сʱ��ʱ����(int a) {
        switch (a) {
            //˫������ֵ������Сʱ��
            case 5211047:
            //˫������ֵ������Сʱ��
            case 5360014:
            //��������ֵ������Сʱ��
            case 5211060:
                return true;
            default:
                return false;
        }
    }

    public static boolean һ��ʱ��ʱ����(int a) {
        switch (a) {
            //�ڰ�1��
            case 5370001:
            //��Ӷ�̵�
            case 5030001:
            case 5030009:
            case 5030011:
            case 5030012:
            //˫������ֵ����һ��Ȩ��
            case 5210000:
            //˫������ֵ��������һ��Ȩ��
            case 5210002:
            //˫������ֵ��������һ��Ȩ��
            case 5210004:
            //˫�����ʿ���һ��Ȩ��
            case 5360000:
            //˫�����ʿ���һ��Ȩ��
            case 5360015:
                return true;
            default:
                return false;
        }
    }

    public static boolean ����ʱ��ʱ����(int a) {
        switch (a) {
            //�ڰ�7��
            case 5370000:
            //װ�����֤
            case 5590000:
            case 5590001:
            //��Ӷ�̵�
            case 5030000:
            case 5030008:
            case 5030010:
            case 5030018:
            //˫������ֵ��������Ȩ��
            case 5210001:
            //˫������ֵ������������Ȩ��
            case 5210003:
            //˫������ֵ������������Ȩ��
            case 5210005:
            //˫�����ʿ���һ��Ȩ��
            case 5360016:
                return true;
            default:
                return false;
        }
    }

    public static boolean ��ʮ��ʱ��ʱ����(int a) {
        switch (a) {
            //��Ӷ
            case 5030016:
                return true;
            default:
                return false;
        }
    }

    public static boolean ����(int a) {
        switch (a) {
            //�ʺ��
            case 1000000:
            //�ʺ��������
            case 1000001:
            //�ʺ������
            case 1000002:
            //�ʺ���ӻ���
            case 1000003:
            //�ϸ�
            case 2000000:
            //���ִ�
            case 100000000:
            //���ִ���լ
            case 100000001:
            //���ִ弯��
            case 100000100:
            //���ִ�������
            case 100000101:
            //���ִ��ӻ���
            case 100000102:
            //���ִ�����Ժ
            case 100000103:
            //���ִ�������
            case 100000104:
            //���ִ廤������
            case 100000105:
            //���ִ幫԰
            case 100000200:
            //���﹫԰ 
            case 100000202:
            //���ִ���Ϸ����
            case 100000203:
            //�����ֵĵ���
            case 100000204:
            //ħ������
            case 101000000:
            //ħ������������
            case 101000001:
            //ħ�������ӻ���
            case 101000002:
            //ħ������ͼ���
            case 101000003:
            //ħ��ʦ�ĵ���
            case 101000004:
            //����֮��
            case 101000200:
            //ħ��������ͷ
            case 101000300:
            //����
            case 101000301:
            //��ʿ����
            case 102000000:
            case 102000001:
            case 102000002:
            case 102000003:
            case 102000004:
            case 103000000:
            case 103000001:
            case 103000002:
            case 103000003:
            case 103000004:
            case 103000005:
            case 103000006:
            case 103000008:
            case 103000100:
            case 104000000:
            case 104000001:
            case 104000002:
            case 104000003:
            case 104000004:
            case 105040400:
            case 105040401:
            case 105040402:
            case 105040300:
            case 106020000:
            case 140000000:
            case 140000001:
            case 140000010:
            case 140000011:
            case 140000012:
            case 140010110:
            case 200000000:
            case 200000001:
            case 200000002:
            case 200000100:
            case 200000110:
            case 200000111:
            case 200000112:
            case 200000120:
            case 200000121:
            case 200000122:
            case 200000130:
            case 200000131:
            case 200000132:
            case 200000140:
            case 200000141:
            case 200000150:
            case 200000151:
            case 200000152:
            case 200000160:
            case 200000161:
            case 200000200:
            case 200000201:
            case 200000202:
            case 200000203:
            case 200000300:
            case 200000301:
            case 209000000:
            case 211000001:
            case 209080000:
            case 209080100:
            case 211000000:
            case 211000100:
            case 211000101:
            case 211000102:
            case 220000000:
            case 220000001:
            case 220000002:
            case 220000003:
            case 220000004:
            case 220000005:
            case 220000006:
            case 220000100:
            case 220000110:
            case 220000111:
            case 220000300:
            case 220000301:
            case 220000302:
            case 220000303:
            case 220000304:
            case 220000305:
            case 220000306:
            case 220000307:
            case 220000400:
            case 220000500:
            case 221000000:
            case 221000001:
            case 221000100:
            case 221000200:
            case 221000300:
            case 222000000:
            case 222020000:
            case 230000000:
            case 230000001:
            case 230000002:
            case 230000003:
            case 240000000:
            case 240000001:
            case 240000002:
            case 240000003:
            case 240000004:
            case 240000005:
            case 240000006:
            case 240000100:
            case 240000110:
            case 240000111:
            case 250000000:
            case 250000001:
            case 250000002:
            case 250000003:
            case 250000100:
            case 251000000:
            case 260000000:
            case 260000100:
            case 260000110:
            case 260000200:
            case 260000201:
            case 260000202:
            case 260000203:
            case 260000204:
            case 260000205:
            case 260000206:
            case 260000207:
            case 260000300:
            case 260000301:
            case 260000302:
            case 260000303:
            case 261000000:
            case 261000001:
            case 261000002:
            case 261000010:
            case 261000011:
            case 261000020:
            case 261000021:
            case 270010000:
            case 270000000:
            case 300000000:
            case 300000001:
            case 300000002:
            case 300000010:
            case 300000011:
            case 300000012:
            case 500000000:
            case 540000000:
            case 541000000:
            case 550000000:
            case 551000000:
            case 600000000:
            case 600000001:
            case 701000000:
            case 700000000:
            case 700000100:
            case 700000101:
            case 700000200:
            case 701000100:
            case 701000200:
            case 701000201:
            case 701000202:
            case 701000203:
            case 701000210:
            case 702000000:
            case 702050000:
            case 702090102:
            case 741000200:
            case 741000201:
            case 741000202:
            case 741000203:
            case 741000204:
            case 741000205:
            case 741000206:
            case 741000207:
            case 741000208:
            case 800000000:
            case 801000000:
            case 801000001:
            case 801000002:
            case 801000100:
            case 801000110:
            case 801000200:
            case 801000210:
            case 801000300:
            case 810000000:
            case 910000000:
            case 910110000:
            case 930000700:
                return true;
            default:
                return false;
        }
    }

    public static boolean ����2(int a) {
        switch (a) {
            case 104000000:
            case 101000000:
            case 120000000:
            case 102000000:
            case 100000000:
            case 103000000:
            case 222000000:
            case 105040300:
            case 220000000:
            case 230000000:
            case 240000000:
            case 251000000:
            case 221000000:
            case 200000000:
            case 211000000:
            case 260000000:
            case 261000000:
            case 250000000:
            case 551000000:
            case 801000000:
            case 130000200:
                return true;
            default:
                return false;
        }
    }

    public static void ˵��() {
 //       System.out.println("<ZEVMSð�յ�(079)��Ϸ�����>");
 //       System.out.println("�ٷ���վ��www.zevmxd.com");
 //       System.out.println("�ƹ���̳��www.fengyewuyu.com");
 //       System.out.println("�� �� Ⱥ��66141952");
 //       System.out.println("���浺����624290083");
 //       System.out.println("�����Ŷӣ�ZEV�����ͷſ�Eric");
//        System.out.println("������Ա��Woif����ͤ���ģ�����");
//        System.out.println("������л���ϴ�磬�հ�߹����������������ҽ");
//        System.out.println("�Ƽ���¼����Coly(895059359)");

        System.out.println("");
    }

    //��������
    public static String �η���̳���� = "https://www.xn--qpr917b0zhss0a.com";
    public static String ZEVMS���� = "https://www.zevmsmxd.cn";

    //������������򣬿ɽ�����
    public static String chars = "1234567890aAbBcCdDeEfFgGhHiIjJkKlLmMNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
    public static char ����1 = chars.charAt((int) (Math.random() * 62));
    public static char ����2 = chars.charAt((int) (Math.random() * 62));
    public static char ����3 = chars.charAt((int) (Math.random() * 62));
    public static char ����4 = chars.charAt((int) (Math.random() * 62));
    public static char ����5 = chars.charAt((int) (Math.random() * 62));
    public static char ����6 = chars.charAt((int) (Math.random() * 62));
    public static char ����7 = chars.charAt((int) (Math.random() * 62));
    public static char ����8 = chars.charAt((int) (Math.random() * 62));
    public static char ����9 = chars.charAt((int) (Math.random() * 62));
    public static char ����10 = chars.charAt((int) (Math.random() * 62));
    public static char ����11 = chars.charAt((int) (Math.random() * 62));
    public static char ����12 = chars.charAt((int) (Math.random() * 62));
    public static char ����13 = chars.charAt((int) (Math.random() * 62));
    public static char ����14 = chars.charAt((int) (Math.random() * 62));
    public static char ����15 = chars.charAt((int) (Math.random() * 62));
    public static char ����16 = chars.charAt((int) (Math.random() * 62));
    public static char ����17 = chars.charAt((int) (Math.random() * 62));
    public static char ����18 = chars.charAt((int) (Math.random() * 62));
    public static char ����19 = chars.charAt((int) (Math.random() * 62));
    public static char ����20 = chars.charAt((int) (Math.random() * 62));
    public static char ����21 = chars.charAt((int) (Math.random() * 62));
    public static char ����22 = chars.charAt((int) (Math.random() * 62));
    public static char ����23 = chars.charAt((int) (Math.random() * 62));
    public static char ����24 = chars.charAt((int) (Math.random() * 62));
    public static char ����25 = chars.charAt((int) (Math.random() * 62));
    public static char ����26 = chars.charAt((int) (Math.random() * 62));
    public static char ����27 = chars.charAt((int) (Math.random() * 62));
    public static char ����28 = chars.charAt((int) (Math.random() * 62));
    public static char ����29 = chars.charAt((int) (Math.random() * 62));
    public static char ����30 = chars.charAt((int) (Math.random() * 62));
    //��Ȩ��֤ʧ����Ϣ˵��
    public static String ��֤ʧ����Ϣ = ""
            + "\r\n��֤��Ȩʧ�� >_< "
            + "\r\n"
            + "\r\n��׼Ψһ��Ȩ�������������ų���֮��"
            + "\r\n�����κ���Ȩ��Ϣ��С�Ľ����ϵ���ƭ"
            + "\r\nʱ�� : " + CurrentReadable_Time() + ""
            + "\r\n��ַ : " + MapleParty.IP��ַ + ""
            + "\r\n���� : " + ����1 + "" + ����2 + "" + ����3 + "" + ����4 + "" + ����5 + "" + ����6 + "" + ����7 + "" + ����8 + "" + ����9 + "" + ����10 + "" + ����11 + "" + ����12 + "" + ����13 + "" + ����14 + "" + ����15 + "" + ����16 + "" + ����17 + "" + ����18 + "" + ����19 + "" + ����20 + ""
            + "\r\n"
            + "\r\n";

    //��ȡ����ʱ��
    private static String ��ȡ����ʱ��(String webUrl) {
        try {
            URL url = new URL(webUrl);// ȡ����Դ����
            URLConnection uc = url.openConnection();// �������Ӷ���
            uc.connect();// ��������
            long ld = uc.getDate();// ��ȡ��վ����ʱ��
            Date date = new Date(ld);// ת��Ϊ��׼ʱ�����
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// �������ʱ��
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //��������
    public static boolean ����(int xiangzi) {
        switch (xiangzi) {
            /*case 2022465:
            case 2022466:*/
            //return true;
        }
        return false;
    }
    private static final int ������[] = {159502199

    };
    //, "gonggao"
    public static String �¼�[] = {"Gailou", "Laba", "MonsterPark", "GoldTempleBoss", "szsl", "SkyPark", "WitchTower_Hard", "WitchTower_Med", "WitchTower_EASY",
        "CWKPQ", "Relic", "HontalePQ", "HorntailBattle", "cpq2", "elevator", "Christmas", "FireDemon", "Amoria", "cpq", "AutomatedEvent", "Flight",
        "English", "English0", "English1", "English2", "WuGongPQ", "ElementThanatos", "4jberserk", "4jrush", "Trains", "Geenie", "AirPlane", "Boats",
        "OrbisPQ", "HenesysPQ", "Romeo", "Juliet", "Pirate", "Ellin", "DollHouse", "BossBalrog_NORMAL", "Nibergen", "PinkBeanBattle", "ZakumBattle",
        "ZakumPQ", "LudiPQ", "KerningPQ", "ProtectTylus", "CoreBlaze", "GuildQuest", "Aufhaven", "Subway", "KyrinTrainingGroundC", "KyrinTrainingGroundV",
        "ProtectPig", "ScarTarBattle", "Relic", "QiajiPQ", "BossBalrog", "s4resurrection", "s4resurrection2", "s4nest", "s4aWorld", "DLPracticeField",
        "BossQuestEASY", "shaoling"};

    public static boolean �������Ȩ��֤() throws UnknownHostException, SocketException, UnsupportedEncodingException, IOException {
        String i = MapleParty.IP��ַ;
        String a = Class1.Aa();
        //System.out.println(a);
        String c = Class1.C();
        //System.out.println(c);
        String v = Class1.M(a + c);
        v = Class1.M(v + i);
        String r = Class2.P(v);
        String d = new SimpleDateFormat("M" + "dH").format(new Date());
        String f = Class1.M(v + d);

        if (r.equals(f)) {
            /*System.out.println("�� ��֤�������Ȩͨ��");
            FileoutputUtil.logToZev("�������Ϣ�ļ�/��Ȩ���¼"
                    + "/" + �������� + ".txt",
                    "ʱ��:" + CurrentReadable_Date() + "\r\n"
                    + "��Ȩ��:" + f + "\r\n"
                    + "����IP:" + �����IP��ַ + "\r\n\r\n");*/
            return true;
        } else {
            //JOptionPane.showMessageDialog(null, "" + ��֤ʧ����Ϣ + ""
            // );
            return false;
        }
    }

    public static boolean �༭����Ȩ() throws UnknownHostException, SocketException, UnsupportedEncodingException, IOException {
        String i = MapleParty.IP��ַ;
        String a = Class1.Aa();
        String c = Class1.C();
        String v = Class1.M(a + c);
        v = Class1.M(v + i);
        String r = �ű��༭��.P(v);
        String d = new SimpleDateFormat("M" + "dH").format(new Date());
        String f = Class1.M(v + d);
        if (r.equals(f)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "��û��ʹ�õ�Ȩ�ޡ�");
            return false;
        }
    }

    public static boolean ��¼ͨ����Ȩ��() throws UnknownHostException, SocketException, UnsupportedEncodingException, IOException {
        String i = MapleParty.IP��ַ;
        String a = Class1.Aa();
        //System.out.println(a);
        String c = Class1.C();
        //System.out.println(c);
        String v = Class1.M(a + c);
        v = Class1.M(v + i);
        String r = Class2.P(v);
        String d = new SimpleDateFormat("M" + "dH").format(new Date());
        String f = Class1.M(v + d);

        FileoutputUtil.logToZev("�������Ϣ�ļ�/�������Ȩ���¼"
                + "/" + �������� + ".txt",
                "ʱ��:" + CurrentReadable_Date() + "\r\n"
                + "��Ȩ��1:" + f + "\r\n"
                + "��Ȩ��2:" + r + "\r\n"
                + "����IP:" + MapleParty.IP��ַ + "\r\n\r\n");
        return true;
    }

    public static boolean �������Ȩ��֤2() throws UnknownHostException, SocketException, UnsupportedEncodingException, IOException {
        String i = MapleParty.IP��ַ;
        String a = Class1.Aa();
        //System.out.println(a);
        String c = Class1.C();
        //System.out.println(c);
        String v = Class1.M(a + c);
        v = Class1.M(v + i);
        String r = Class2.P(v);
        String d = new SimpleDateFormat("M" + "dH").format(new Date());
        String f = Class1.M(v + d);

        if (r.equals(f)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "" + ��֤ʧ����Ϣ + ""
            );
            return false;
        }
    }

    public static boolean �����������¼() throws UnknownHostException, SocketException, UnsupportedEncodingException, IOException {
        String i = MapleParty.IP��ַ;
        String a = Class1.Aa();
        //System.out.println(a);
        String c = Class1.C();
        //System.out.println(c);
        String v = Class1.M(a + c);
        v = Class1.M(v + i);
        String r = Class2.P(v);
        String d = new SimpleDateFormat("M" + "dH").format(new Date());
        String f = Class1.M(v + d);
        return true;
    }
    //���ų���*��������
    ///����ĺ��󶪳� 250 ��һ��� 1 ��ȯ
    public static String ����ҿ��� = "��";

    public static void ��⶯̬���ݿ�(String a, int b, int c) {
        if (gui.Start.ConfigValuesMap.get(a) == null) {
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
                ps.setInt(1, b);
                ps.setString(2, a);
                ps.setInt(3, c);
                ps.executeUpdate();
                System.out.println("�� ��ʼ��<" + a + ">���ݿ�");
            } catch (SQLException ex) {
            }
        }
    }

    public static void ���ݿ����ݵ���() {
        long start = System.currentTimeMillis();
        //������ݿ����Ƿ�����������
        System.out.println("�� ��ʼУ�Զ�̬���ݿ�");
        ��⶯̬���ݿ�("��Ӷ�ݵ���", 720, 1000);
        ��⶯̬���ݿ�("��Ӷ�ݵ��ȯ", 721, 1000);
        ��⶯̬���ݿ�("������������", 15000, 3);
        ��⶯̬���ݿ�("������������", 15001, 3);
        ��⶯̬���ݿ�("������������", 15002, 1);
        ��⶯̬���ݿ�("��ɮ��������", 15003, 1);
        ��⶯̬���ݿ�("���Ӿ�������", 15004, 3);
        ��⶯̬���ݿ�("ħ��ͻϮ����", 2400, 1);
        ��⶯̬���ݿ�("OX���⿪��", 2401, 1);
        ��⶯̬���ݿ�("ÿ���ͻ�����", 2402, 1);
        ��⶯̬���ݿ�("����ְҵ����", 749, 1);
        ��⶯̬���ݿ�("ħ�幥�ǿ���", 2404, 1);
        ��⶯̬���ݿ�("��ĩ���ʿ���", 2405, 1);
        ��⶯̬���ݿ�("�������˿���", 2406, 1);
        ��⶯̬���ݿ�("Ұ��ͨ������", 2407, 1);
        ��⶯̬���ݿ�("ϲ���콵����", 2408, 1);
        ��⶯̬���ݿ�("������������", 2409, 1);
        ��⶯̬���ݿ�("�����Żݿ���", 2410, 1);
        ��⶯̬���ݿ�("��Ӷ����ʱ��", 995, 24);
        ��⶯̬���ݿ�("��ͼˢ��Ƶ��", 996, 3000);
        ��⶯̬���ݿ�("��ͼ��Ʒ����", 997, 300);
        ��⶯̬���ݿ�("��Ϸ���˿���", 2127, 0);
        ��⶯̬���ݿ�("����״̬����", 2061, 0);
        ��⶯̬���ݿ�("����������", 2062, 0);
        ��⶯̬���ݿ�("IP�࿪��", 2063, 2);
        ��⶯̬���ݿ�("������࿪��", 2064, 2);
        //��⶯̬���ݿ�("�����޸�����", 2061, 0);
        ��⶯̬���ݿ�("������������", 2128, 1);
        ��⶯̬���ݿ�("������������", 2129, 1);
        ��⶯̬���ݿ�("���ּ�⿪��", 2130, 1);
        ��⶯̬���ݿ�("ȫ����⿪��", 2131, 1);
        ��⶯̬���ݿ�("���ܵ��Կ���", 2132, 1);
        ��⶯̬���ݿ�("���ܳͷ�����", 2133, 1);
        ��⶯̬���ݿ�("���ֵ��Կ���", 2134, 1);
        ��⶯̬���ݿ�("���ֳͷ�����", 2135, 1);
        ��⶯̬���ݿ�("��ͼ���ƿ���", 2136, 1);
        ��⶯̬���ݿ�("����޵п���", 2137, 1);
        ��⶯̬���ݿ�("������⿪��", 2138, 1);
        ��⶯̬���ݿ�("Ⱥ����⿪��", 2139, 1);
        ��⶯̬���ݿ�("��ͼ�浵����", 2140, 1);
        ��⶯̬���ݿ�("�һ���⿪��", 2141, 1);
        ��⶯̬���ݿ�("�����޴ο���", 2142, 1);
        ��⶯̬���ݿ�("ÿ��ƣ��ֵ", 2143, 24);
        ��⶯̬���ݿ�("��ħ���ѿ���", 2144, 0);
        ��⶯̬���ݿ�("����ģʽ����", 2145, 1);
        ��⶯̬���ݿ�("���ټ�⿪��", 2146, 1);
        ��⶯̬���ݿ�("����˺�", 2147, 500);
        ��⶯̬���ݿ�("�����⿪��", 2148, 500);
        //
        ��⶯̬���ݿ�("��������", 4010, 5);
        ��⶯̬���ݿ�("���㾭�鿪��", 4001, 0);
        ��⶯̬���ݿ�("�����ҿ���", 4002, 0);
        ��⶯̬���ݿ�("�����ȯ����", 4003, 0);
        ��⶯̬���ݿ�("���㾭�鱶��", 4011, 1);
        ��⶯̬���ݿ�("�����ұ���", 4012, 1);
        ��⶯̬���ݿ�("�����ȯ����", 4013, 1);

        ��⶯̬���ݿ�("��Ӧ��������", 4100, 10);
        //
        ��⶯̬���ݿ�("װ�������ȡ��", 200000, 100);
        ��⶯̬���ݿ�("װ����ߵȼ�", 200001, 1);
        ��⶯̬���ݿ�("װ��һ����ྭ���ȡ", 200002, 10000);
        //
        ��⶯̬���ݿ�("װ���ȼ�1", 100001, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�2", 100002, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�3", 100003, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�4", 100004, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�5", 100005, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�6", 100006, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�7", 100007, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�8", 100008, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�9", 100009, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�10", 100010, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�11", 100011, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�12", 100012, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�13", 100013, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�14", 100014, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�15", 100015, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�16", 100016, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�17", 100017, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�18", 100018, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�19", 100019, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�20", 100020, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�21", 100021, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�22", 100022, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�23", 100023, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�24", 100024, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�25", 100025, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�26", 100026, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�27", 100027, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�28", 100028, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�29", 100029, 11000000);
        ��⶯̬���ݿ�("װ���ȼ�30", 100030, 11000000);

        ��⶯̬���ݿ�("��������1", 210000, 10000);
        ��⶯̬���ݿ�("��������2", 210001, 60);
        ��⶯̬���ݿ�("��������3", 210002, 10);

        ��⶯̬���ݿ�("ԡͰ����0", 210010, 60);
        ��⶯̬���ݿ�("ԡͰ����1", 210011, 100);
        ��⶯̬���ݿ�("ԡͰ����2", 210012, 200);
        ��⶯̬���ݿ�("ԡͰ����3", 210013, 300);
        ��⶯̬���ݿ�("ԡͰ����4", 210014, 400);
        ��⶯̬���ݿ�("ԡͰ����5", 210015, 500);
        ��⶯̬���ݿ�("ԡͰ����6", 210016, 600);
        ��⶯̬���ݿ�("ԡͰ����7", 210017, 700);
        ��⶯̬���ݿ�("ԡͰ����8", 210018, 800);
        ��⶯̬���ݿ�("ԡͰ����9", 210019, 900);

        ��⶯̬���ݿ�("��Ҷ����ʽ��", 210020, 1000);
        ��⶯̬���ݿ�("��Ҷ����ʾ���", 210021, 1000);
        ��⶯̬���ݿ�("��Ҷ���������", 210022, 10);
        ��⶯̬���ݿ�("��Ҷ����ʼ��", 210023, 60);
        long now = System.currentTimeMillis() - start;
        System.out.println("�� У�Զ�̬���ݿ���ϣ�" + now + "");
    }

    public static void ����ļ�(String Name) {
        Properties �O���n = System.getProperties();
        File file = new File(�O���n.getProperty("user.dir") + Name);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void ����ļ�ȱʧ(String Name) {
        Properties �O���n = System.getProperties();
        File file = new File(�O���n.getProperty("user.dir") + Name);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void ����ļ���(String Name) {
        Properties �O���n = System.getProperties();
        File file = new File(�O���n.getProperty("user.dir") + Name);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
    }

    public static void ����ļ����Ƿ����1() {
        try {
            ByteArrayOutputStream baos = null;
            InputStream os = null;
            String s = "";
            Process p = Runtime.getRuntime().exec("cmd /c tasklist");
            baos = new ByteArrayOutputStream();
            final Runtime runtime = Runtime.getRuntime();
            Properties �O���n = System.getProperties();
            Process process = null;
            File file = new File(�O���n.getProperty("user.dir") + ":\\Users\\QPING\\Desktop\\JavaScript");
            //����ļ��в������򴴽�
            if (!file.exists() && !file.isDirectory()) {
                System.out.println("//������");
                file.mkdir();
            } else {
                System.out.println("//Ŀ¼����");
            }
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void �����³���1() {
        Properties �O���n = System.getProperties();
        File file = new File(�O���n.getProperty("user.dir") + "\\" + ���³��� + "");
        if (!file.exists()) {
            try {
                �����ļ�("http://123.207.53.97:8082/����������ļ����/" + ���³��� + "", "" + ���³��� + "", "" + �O���n.getProperty("user.dir") + "");
                System.out.println("�� ȱʧ�ļ�:" + ���³��� + "���Ѿ����ء�");
            } catch (IOException e) {
                System.out.println("�� ȱʧ�ļ�:" + ���³��� + "������ʧ�ܣ����������ԣ�");
                System.out.println("�� ������Ϣ:" + e);
            }
        }
    }

    public static void ��ʼ��������ļ���() {
        long start = System.currentTimeMillis();

        long now = System.currentTimeMillis() - start;
        System.out.println("�� ������������ļ���ɣ�" + now + "");
    }

    public static void ��ʼ������������ļ�() {
        long start = System.currentTimeMillis();
        System.out.println("�� ��ʼ������������ļ�");
        �����³���1();
        long now = System.currentTimeMillis() - start;
        System.out.println("�� ������������ļ���ɣ�" + now + "");
    }

    public static boolean ��Ȩ��() throws UnknownHostException, SocketException, UnsupportedEncodingException, IOException {
        String i = ServerProperties.getProperty("ZEV.QQ1");
        String a = Class1.Aa();
        String c = Class1.C();
        String v = Class1.M(a + c);
        v = Class1.M(v + i);
        String r = Class2.P(v);
        String d = new SimpleDateFormat("M" + "dH").format(new Date());
        String f = Class1.M(v + d);

        if (r.equals(f)) {
            FileoutputUtil.logToFile("Togan/�ű��༭����Ȩ��.txt", "" + f + "\r\n");
            return true;
        } else {
            FileoutputUtil.logToFile("Togan/�ű��༭����Ȩ��.txt", "" + f + "\r\n");
            return false;
        }
    }
}
