package gui;

import static abc.sancu.FileDemo_05.ɾ���ļ�;
import gui.Jieya.��ѹ�ļ�;
import static gui.ZEVMS.�����ļ�;
import static a.�������ݿ�.���ݿ�����Ŀ¼;
import static a.�������ݿ�.���ݿ⵼��Ŀ¼;
import static a.�������ݿ�.���ݿ��ѹĿ¼;
import static a.�������ݿ�.��������ݿ�Ŀ¼;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ������ݿ�� {

    public static void ������ݿ��(String a) {
        //�жϼ���Ƿ���ڣ������ھ�����
        try {
            ByteArrayOutputStream baos = null;
            InputStream os = null;
            String s = "";
            Process p = Runtime.getRuntime().exec("cmd /c tasklist");
            baos = new ByteArrayOutputStream();
            File file = new File(��������ݿ�Ŀ¼() + "/" + a + ".frm");

            //����ļ��в������򴴽�
            if (!file.exists() && !file.isDirectory()) {
                System.err.println("�� �������ݿ�:" + a);
                try {
                    �����ļ�("http://123.207.53.97:8082/ħ���ļ����/���ݿ�/" + a + ".zip", "" + a + ".zip", "" + ���ݿ�����Ŀ¼() + "/");
                     ��ѹ�ļ�.��ѹ�ļ�(���ݿ��ѹĿ¼(a), ���ݿ⵼��Ŀ¼("zevms"));
                     ɾ���ļ�(���ݿ��ѹĿ¼(a));
                } catch (Exception e) {
                    System.err.println("�� ����Ҫ�������ļ�����");
                }
            }
        } catch (IOException ex) {

        }
    }
}
