package server;

import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import database.DatabaseConnection;
import static gui.ZEVMS.����ļ�3;
import java.io.*;

/**
 * ����̨
 */
public class ServerProperties {

    private static final Properties props = new Properties();
    private static final String[] toLoad = {
        "Load\\��������ü�����.ini",
        "Load\\����˼����¼�.ini",
         "Load\\��������������������.ini",
        /*"PVP\\սʿ.ini",
        "PVP\\����.ini",
        "PVP\\��ʿ.ini",
        "PVP\\Ӣ��.ini",
        "PVP\\����ʿ.ini",
        "PVP\\׼��ʿ.ini",
        "PVP\\ʥ��ʿ.ini",
        "PVP\\��ʿ.ini",
        "PVP\\ħ��ʦ.ini",
        "PVP\\�𶾷�ʦ.ini",
        "PVP\\����ʦ.ini",
        "PVP\\��ħ��ʦ.ini",
        "PVP\\���׷�ʦ.ini",
        "PVP\\������ʦ.ini",
        "PVP\\����ħ��ʦ.ini",
        "PVP\\��ʦ.ini",
        "PVP\\��ʦ.ini",
        "PVP\\����.ini",
        "PVP\\������.ini",
        "PVP\\����.ini",
        "PVP\\����.ini",
        "PVP\\������.ini",
        "PVP\\����.ini",
        "PVP\\�̿�.ini",
        "PVP\\��Ӱ��.ini",
        "PVP\\��ʿ.ini",
        "PVP\\����.ini",*/
        "Load\\��ϷƵ��״̬��ʾ.txt"
    };

    private ServerProperties() {

    }

    static {
        ����ļ�3();
        for (String s : toLoad) {
            InputStreamReader fr;
            try {
                fr = new InputStreamReader(new FileInputStream(s), "UTF-8");//UTF-8
                props.load(fr);
                fr.close();
            } catch (IOException ex) {
                //System.out.println("���������ļ�����" + ex);
                System.out.println("�����ļ�ȱʧ������������������޸���������");
            }
        }
        try {
            //PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM auth_server_channel_ip");���ݿ�
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM auth_ip");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                props.put(rs.getString("name") + rs.getInt("channelid"), rs.getString("value"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(0); //Big ass error.
        }

    }

    public static String getProperty(String s) {
        return props.getProperty(s);
    }

    public static void setProperty(String prop, String newInf) {
        props.setProperty(prop, newInf);
    }

    public static String getProperty(String s, String def) {
        return props.getProperty(s, def);
    }
}
