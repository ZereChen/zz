package gui.Jieya;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ��ѹ�ļ� {

    static final int BUFFER = 2048;

    /**
     * ��ȡZIP�ļ���ֻ�ʺ���ZIP�ļ�����RAR�ļ���Ч����ΪZIP�ļ���ѹ���㷨�ǹ����ģ���RAR����
     *
     * @author ����
     * @version 1.0
     * @param zipfilepath��ZIP�ļ���·����unzippath��Ҫ��ѹ�����ļ�·��
     */
    /*public  void main(String[] args) {
        
        ReadZip("C:\\Users\\Administrator\\Desktop\\divine.zip", "C:\\Users\\Administrator\\Desktop\\");
        
    }*/
    public static void ��ѹ�ļ�(String zipfilepath, String unzippath) {
        try {
            BufferedOutputStream bos = null;
            //���������ֽ���
            FileInputStream fis = new FileInputStream(zipfilepath);
            //���������ֽ������������ַ���
            BufferedInputStream bis = new BufferedInputStream(fis);
            //�����ַ���������ZIP�ļ�������
            ZipInputStream zis = new ZipInputStream(bis);
            //zip�ļ���Ŀ����ʾzip�ļ�
            ZipEntry entry;
            //ѭ����ȡ�ļ���Ŀ��ֻҪ��Ϊ�գ��ͽ��д���
            while ((entry = zis.getNextEntry()) != null) {
                //System.out.println("====" + entry.getName());
                int count;
                byte date[] = new byte[BUFFER];
                //�����Ŀ���ļ�Ŀ¼�������ִ��
                if (entry.isDirectory()) {
                    continue;
                } else {
                    int begin = zipfilepath.lastIndexOf("\\") + 1;
                    int end = zipfilepath.lastIndexOf(".") + 1;
                    String zipRealName = zipfilepath.substring(begin, end);
                    bos = new BufferedOutputStream(new FileOutputStream(getRealFileName(unzippath, entry.getName())));//zipRealName
//                    System.out.println(unzippath);
//                    System.out.println(zipRealName);
//                    System.out.println(entry.getName());
                    while ((count = zis.read(date)) != -1) {
                        bos.write(date, 0, count);
                    }
                    bos.flush();
                    bos.close();
                }
            }
            zis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static File getRealFileName(String zippath, String absFileName) {
        String[] dirs = absFileName.split("/", absFileName.length());
        //�����ļ����� 
        File file = new File(zippath);
        if (dirs.length > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                //����file����·����dir·���ַ�������һ���µ�file����·��Ϊ�ļ�����һ��Ŀ¼
                file = new File(file, dirs[i]);
            }
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(file, dirs[dirs.length - 1]);
        return file;
    }
}
