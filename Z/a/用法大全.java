/*
���ܽű�
10200
10201
10202
10203
10204
 */package a;

import database.DatabaseConnection;
import database1.DatabaseConnection1;
import handling.world.MapleParty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static tools.FileoutputUtil.CurrentReadable_Time;

/**
 *
 * @author Administrator
 */
public class �÷���ȫ {

    /*
    Integer.parseInt()//ת����
    c.getPlayer().getClient().getSession().close();//����
     */
    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                //System.out.println("��������ʹ��");
                return 0;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("�Ѿ����ڣ��޷�����ʹ��");
                System.out.println("���ڹٷ�Ⱥǩ���������Ӻ���ʱ��");
                System.exit(0);
                return 1;
            } else {
                return 2;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static String ��ɫIDȡ��������(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id2 as DATA FROM shangxianlaba WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ɫIDȡ�������ȡ�����");
        }
        return data;
    }

    public static int ��֤�̳���Ʒ(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemid as DATA FROM cashshop_modified_items WHERE itemid = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��֤�̳���Ʒ - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    public static int ��������(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Point as DATA FROM characterz WHERE name = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��������������");
        }
        return data;
    }

    public static String ��ɫ����ȡ�˺�ID(String id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT accountid as DATA FROM characters WHERE name = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ɫ����ȡ�˺�ID������");
        }
        return data;
    }

    public static String ��ɫIDȡ��ɫ��(String id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM characters WHERE id = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ɫIDȡ��ɫ��������");
        }
        return data;
    }

    public static String ��ɫIDȡ�˺�ID(String id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT accountid as DATA FROM characters WHERE id = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ɫ����ȡ�˺�ID������");
        }
        return data;
    }

    public static int ��ɫIDȡ�˺�ID(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT accountid as DATA FROM characters WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ɫ����ȡ�˺�ID������");
        }
        return data;
    }

    public static String �˺�IDȡ�˺�(String id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM accounts WHERE id = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�˺�IDȡ�˺š�����");
        }
        return data;
    }

    public static String �˺�IDȡ�˺�(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM accounts WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�˺�IDȡ�˺š�����");
        }
        return data;
    }

    public static String �˺�IDȡ��QQ(String id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT qq as DATA FROM accounts WHERE id = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�˺�IDȡ�˺š�����");
        }
        return data;
    }

    public static String �˺�IDȡ��QQ(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT qq as DATA FROM accounts WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�˺�IDȡ�˺š�����");
        }
        return data;
    }

    public static String �˺�ȡ�˺�ID(String id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id as DATA FROM accounts WHERE name = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�˺�ȡ�˺�ID������");
        }
        return data;
    }

    public static int �˺�ȡ�˺ŵ�ȯ���(String id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT ACash as DATA FROM accounts WHERE name = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�˺�ȡ�˺ŵ�ȯ������");
        }
        return data;
    }

    public static int �˺Ų�����(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT loggedin as DATA FROM accounts WHERE name = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�˺�IDȡ�˺š�����");
        }
        return data;
    }

    public static String ȡ�˺Ű󶨵�QQ(String id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT qq as DATA FROM accounts WHERE name = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ȡ�˺Ű󶨵�QQ������");
        }
        return data;
    }

    public static String ��ȡ����ʱ��(String a) {
        String data = "";
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ����ʱ�� WHERE Name = " + a + "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data = rs.getString("Point");
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int �ж��˺Ŵ���(String a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �˺�");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    data += 1;
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int �ж����ִ���(String a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ��������Ȩ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    data += 1;
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int �ж���Ʒ��¼(String a, String b) {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ���õķ�������Ʒ��¼");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("��Ʒ����").equals("" + a + "")) {
                    if (rs.getString("�˺�").equals("" + b + "")) {
                        data += 1;
                    }
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int �ж���Ʒ��¼(String a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ���õķ�������Ʒ��¼");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("��Ʒ����").equals("" + a + "")) {
                    data += 1;
                }
            }
        } catch (SQLException ex) {
        }
        return data;
    }

    public static String �ж��˺������Ƿ���ȷ(String a) {
        String data = "";
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �˺�");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    data = rs.getString("mima");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int �ж�����ģʽ(String a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �˺�");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    data += 1;
                }
            }
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int �ж���������(String a) {
        int data = 9999;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �˺�");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    data = rs.getInt("��������");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int ˢ��ʣ��ʱ��(String a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ʣ��ʱ��");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    data = rs.getInt("Point");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static String ��ȡ�ϴ�����ʱ��(String a) {
        int sns = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT `id` FROM ��½��¼ WHERE name = " + a + " ORDER BY `id` DESC LIMIT 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String SN = rs.getString("id");
                    sns = Integer.parseInt(SN);
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("����" + ex.getMessage());
        }
        String data = "";
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ��½��¼ ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    data = rs.getString("time");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int �ж��˻�״̬(String a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �˺�");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    data = rs.getInt("�˻�״̬");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static String ��ϵ��֤(String a) {
        String data = "";
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ��ϵ��֤");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name") == null ? a == null : rs.getString("name").equals(a)) {
                    data = rs.getString("IP");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static String ȡ�㲥() {
        String data = "";
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `�㲥��Ϣ` ORDER BY RAND() LIMIT 0,100;  ");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data = rs.getString("�㲥");
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }
    private static int ����ȡ�㲥 = 0;

    public static String ����ȡ�㲥() {

        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `�㲥��Ϣ` ORDER BY RAND() LIMIT 0,100;  ");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data = rs.getString("�㲥");
            }
            if (����ȡ�㲥 > 0) {
                System.err.println("[�����]" + CurrentReadable_Time() + " : ��������ѭ���㲥 ��");
            } else {
                ����ȡ�㲥++;
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("[�����]" + CurrentReadable_Time() + " : ��������ѭ���㲥 ��");
        }
        return data;
    }

    public static String ����ȡ�ض��㲥() {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �㲥��Ϣ  ");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("id") == 1) {
                    data = rs.getString("�㲥");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static String ȡ������Ϣ(String a) {
        String data = "";
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ����˹��� ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("Ӧ��") == null ? a == null : rs.getString("Ӧ��").equals(a)) {
                    data = rs.getString("��Ϣ");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }
//    public  String �ű�����() {
//        try {
//            Connection con = DatabaseConnection1.getConnection1();
//            PreparedStatement ps = con.prepareStatement("SELECT * FROM �ű����� ");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Properties �O���n = System.getProperties();
//                try {
//                    downLoadFromUrl("http://123.207.53.97:8082/ħ���ļ����/�ļ�����/" + rs.getString("����·��") + "/" + rs.getString("�ļ���") + "", "" + rs.getString("�ļ���") + "", "" + �O���n.getProperty("user.dir") + "" + rs.getString("����·��") + "");
//                } catch (Exception e) {
//                }
//            }
//        } catch (SQLException ex) {
//        }
//    }

    public static int ����ʱ������(String name) {
        int DATA = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT rank FROM (SELECT @rownum := @rownum + 1 AS rank, `name`, `����`, `id` FROM ����ʱ��, (SELECT @rownum := 0) r  ORDER BY `����` DESC) AS T1 WHERE `id` = ?");
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DATA = rs.getInt("rank");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ����");
        }
        return DATA;
    }

    public static int ������֤(String a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ��������Ȩ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    data = rs.getInt("qq");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static String ��������(String a) {
        String data = "";
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ��������Ȩ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("qq").equals("" + a + "")) {
                    data = rs.getString("name");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int ʣ��ʱ��(String a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ʣ��ʱ��");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("Name").equals("" + a + "")) {
                    data = rs.getInt("Point");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static void ����IP��ַ(int a, String b) {
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ��ϵ��֤");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    String aa = null;
                    aa = "update ��ϵ��֤ set IP=" + b + " where name=" + a + ";";
                    PreparedStatement �汾 = DatabaseConnection1.getConnection1().prepareStatement(aa);
                    �汾.executeUpdate(aa);
                    System.out.println("�� ������������:1111111111111");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
    }

    public static void ����汾��(String a, int b) {
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �˺�");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    String aa = null;
                    aa = "update �˺� set �汾=" + b + " where name=" + a + ";";
                    PreparedStatement �汾 = DatabaseConnection1.getConnection1().prepareStatement(aa);
                    �汾.executeUpdate(aa);
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
    }

    public static void �����˻�����(String a, int b) {
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �˺�");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    String aa = null;
                    aa = "update �˺� set �˻�����=" + b + " where name=" + a + ";";
                    PreparedStatement �汾 = DatabaseConnection1.getConnection1().prepareStatement(aa);
                    �汾.executeUpdate(aa);

                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
    }

    public static int ȡ����ʱ��(String a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ����ʱ��");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("id").equals("" + a + "")) {
                    data = rs.getInt("����");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static void ��������ʱ��(int a, int b) {
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ����ʱ��");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("id").equals("" + a + "")) {
                    String aa = null;
                    int ���� = rs.getInt("����");
                    aa = "update ����ʱ�� set ����=" + (���� + b) + " where id=" + a + ";";
                    PreparedStatement �汾 = DatabaseConnection1.getConnection1().prepareStatement(aa);
                    �汾.executeUpdate(aa);
                }
            }
            System.err.println("[�����]" + CurrentReadable_Time() + " : �ϴ�����ʱ�� �� �Ѿ����� " + MapleParty.���������ʱ�� + " ����");
            ps.close();
        } catch (SQLException ex) {
            System.err.println("[�����]" + CurrentReadable_Time() + " : �ϴ�����ʱ�� ��");
        }
    }

    public static void �޸�������������(int a, int b) {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM shangxianlaba");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("id") == a) {
                    String aa = null;
                    aa = "update shangxianlaba set id2 =" + b + " where id = " + a + ";";
                    PreparedStatement ab = DatabaseConnection.getConnection().prepareStatement(aa);
                    ab.executeUpdate(aa);
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
    }

    public static void ע������(int a, String b) {
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ��������Ȩ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    String aa = null;
                    aa = "update �˺� set �汾=" + b + " where name=" + a + ";";
                    PreparedStatement �汾 = DatabaseConnection1.getConnection1().prepareStatement(aa);
                    �汾.executeUpdate(aa);
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
    }

    public static void ����������Ϣ(String name, String b, String c) {
        try (Connection con = DatabaseConnection1.getConnection1();
                PreparedStatement ps = con.prepareStatement("INSERT INTO ���ӳ� (name,IP,time) VALUES ( ?, ?, ?)")) {
            ps.setString(1, name);
            ps.setString(2, b);
            ps.setString(3, c);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
        }
    }

    public static void ����������¼(String a, String b) {
        Connection con;
        try {
            con = DatabaseConnection1.getConnection1();
        } catch (Exception ex) {
            System.out.println(ex);
            return;
        }
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO ��½��¼ (name, time) VALUES (?, ?)");
            ps.setString(1, a);
            ps.setString(2, b);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            return;
        }

    }

    public static void ������Ȩ��֤(String a, int b) {
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �˺�");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    String aa = null;
                    aa = "update �˺� set �汾=" + b + " where name=" + a + ";";
                    PreparedStatement �汾 = DatabaseConnection1.getConnection1().prepareStatement(aa);
                    �汾.executeUpdate(aa);
                }
            }
        } catch (SQLException ex) {
        }
    }

    public static void ���������˺�����(String a, int b) {
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �˺�");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    String aa = null;
                    aa = "update �˺� set �˺�����=" + b + " where name=" + a + ";";
                    PreparedStatement �汾 = DatabaseConnection1.getConnection1().prepareStatement(aa);
                    �汾.executeUpdate(aa);
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
    }

    public static void �������˽�ɫ����(String a, int b) {
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �˺�");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    String aa = null;
                    aa = "update �˺� set ��ɫ����=" + b + " where name=" + a + ";";
                    PreparedStatement �汾 = DatabaseConnection1.getConnection1().prepareStatement(aa);
                    �汾.executeUpdate(aa);
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
    }

    public static void ������������(String a, int b) {
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �˺�");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    String aa = null;
                    aa = "update �˺� set ��������=" + b + " where name=" + a + ";";
                    PreparedStatement �汾 = DatabaseConnection1.getConnection1().prepareStatement(aa);
                    �汾.executeUpdate(aa);
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
    }

    public static void �û�����(String a, int b) {
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �˺�");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    String aa = null;
                    aa = "update �˺� set �˻�״̬=" + b + " where name=" + a + ";";
                    PreparedStatement �汾 = DatabaseConnection1.getConnection1().prepareStatement(aa);
                    �汾.executeUpdate(aa);
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
    }

    public static int ǿ�Ƹ���() {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ����");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("����").equals("ǿ�Ƹ���")) {
                    data = rs.getInt("״̬");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int �ű�Ӧ��() {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ����");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("����").equals("�ű�Ӧ��")) {
                    data = rs.getInt("״̬");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int �ж����ӳ�����(String a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �˺�");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals("" + a + "")) {
                    data += rs.getInt("name");
                }
            }
        } catch (SQLException ex) {
        }
        return data;
    }
}
