package a;

import database.DatabaseConnection;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Administrator
 */
public class �������ݿ� {

    public static String �˺�ȡ��QQ(String a) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name") == null ? a == null : rs.getString("name").equals(a)) {
                    data = rs.getString("qq");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static String ȡ���ӱ�ע(int a) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM qqstem");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("channel") == a) {
                    data = rs.getString("Name");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int ��ѯQQ���Ƿ��з���˺�(String a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE banned > 0");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("qq") == null ? a == null : rs.getString("qq").equals(a)) {
                    data += 1;
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static String �����Ŀ¼() {
        String data = "";
        Properties �O���n = System.getProperties();
        data = �O���n.getProperty("user.dir");
        return data;
    }

    public static String ��������ݿ�Ŀ¼() {
        String data = "";
        Properties �O���n = System.getProperties();
        data = �O���n.getProperty("user.dir") + "\\MYSQL\\MySQL\\data\\zevms\\";
        return data;
    }

    public static String ���ݿ��ѹĿ¼(String a) {
        String data = "";
        Properties �O���n = System.getProperties();
        data = �O���n.getProperty("user.dir") + "\\MYSQL\\MySQL\\data\\" + a + ".zip";
        return data;
    }

    public static String ���ݿ⵼��Ŀ¼(String a) {
        String data = "";
        Properties �O���n = System.getProperties();
        data = �O���n.getProperty("user.dir") + "\\MYSQL\\MySQL\\data\\" + a + "";
        return data;
    }

    public static String ���ݿ�����Ŀ¼() {
        String data = "";
        Properties �O���n = System.getProperties();
        data = �O���n.getProperty("user.dir") + "\\MYSQL\\MySQL\\data";
        return data;
    }

    public static String �����������Ŀ¼() {
        String data = "";
        Properties �O���n = System.getProperties();
        data = �O���n.getProperty("user.dir") + "\\scripts";
        return data;
    }

    public static String ������½�ѹĿ¼(String a) {
        String data = "";
        Properties �O���n = System.getProperties();
        data = �O���n.getProperty("user.dir") + "\\scripts\\" + a + ".zip";
        return data;
    }

    public static String ������µ���Ŀ¼(String a) {
        String data = "";
        Properties �O���n = System.getProperties();
        data = �O���n.getProperty("user.dir") + "\\scripts\\" + a + "";
        return data;
    }

    public static void load() {
        try {
            String fPath = "I:\\Users\\divine.sql";
            Runtime rt = Runtime.getRuntime();

            Process child = rt.exec("mysql -uroot -pzevms zevms");
            OutputStream out = child.getOutputStream();
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fPath), "utf8"));
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();

            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
            writer.write(outStr);
            writer.flush();

            out.close();
            br.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
