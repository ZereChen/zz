package a.http;

import abc.Game;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ���ݿ�����ļ� {

    public static void main(String args[]) {
        ���ݿ��Ⲣ����();
    }

    public static void ���ݿ��Ⲣ����() {
        ����ļ�("accountsz.frm");
    }

    /*
    1.�����ļ�����
    2.Ŀ¼
     */
    public static void ����ļ�(String a) {
        try {
            ByteArrayOutputStream baos = null;
            InputStream os = null;
            String s = "";
            Process p = Runtime.getRuntime().exec("cmd /c tasklist");
            baos = new ByteArrayOutputStream();
            final Runtime runtime = Runtime.getRuntime();
            Properties ZEV = System.getProperties();
            Process process = null;
            File file = new File(ZEV.getProperty("user.dir") + "/MYSQL/MySQL/data/zevms/" + a + "");
            //����ļ��в������򴴽�
            if (!file.exists() && !file.isDirectory()) {
                System.err.println("�� ���ݿ��ļ�: " + a + " ȱʧ��");
                DownLoad downLoad = new DownLoad();
                String url = "http://123.207.53.97:8082/���ݿ��ļ�/" + a + "";
                downLoad.downLoad(url, ZEV.getProperty("user.dir") + "/" + a + "");
                System.err.println("�� ���ݿ��ļ�: " + a + " ���³ɹ���");
            }
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
