package gui.ͼƬ.gui1;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import javazoom.jl.player.Player;

public class TestURL {

    public static void main(String[] args) throws IOException {
        test4();
        //test3();
        //test2();
        //test();
        //��ȡ��Ϸ����1();
    }

    public static void test4() throws IOException {
        URL url = new URL("http://123.207.53.97:8082/����˿���/���ü�⿪��.txt");
        //�򿪵��� URL �����Ӳ�����һ�����ڴӸ����Ӷ���� InputStream�� 
        Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
        int c;
        while ((c = reader.read()) != -1) {
            System.out.println((char) c);
        }
        reader.close();
    }

    /**
     * ��ȡURLָ������Դ��
     *
     * @throws IOException
     */
    public static void test42() throws IOException {
        URL url = new URL("http://123.207.53.97:8082/����˿���/���ü�⿪��.txt");
//��ô� URL �����ݡ� 
        Object obj = url.getContent();
        try {
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(obj.getClass().getName()));
            Player player = new Player(buffer);
            player.play();
        } catch (Exception e) {
        }
        System.out.println(obj.getClass().getName());
    }

    /**
     * ��ȡURLָ������Դ
     *
     * @throws IOException
     */
    public static void test3() throws IOException {
        URL url = new URL("http://www.hrtsea.com/down/soft/45.htm");
//����һ�� URLConnection ��������ʾ�� URL �����õ�Զ�̶�������ӡ� 
        URLConnection uc = url.openConnection();
//�򿪵����Ӷ�ȡ���������� 
        InputStream in = uc.getInputStream();
        int c;
        while ((c = in.read()) != -1) {
            System.out.print(c);
        }
        in.close();
    }

    /**
     * ��ȡURLָ������ҳ����
     *
     * @throws IOException
     */
    public static void test2() throws IOException {
        URL url = new URL("http://www.zevmsmxd.cn/chanpin");
//�򿪵��� URL �����Ӳ�����һ�����ڴӸ����Ӷ���� InputStream�� 
        Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
        int c;
        while ((c = reader.read()) != -1) {
            System.out.print((char) c);
        }
        reader.close();
    }

    /**
     * ��ȡURL���������������
     *
     * @throws IOException
     */
    public static void test() throws IOException {
        URL url = new URL("http://www.zevmsmxd.cn/chanpin");
//�򿪵��� URL �����Ӳ�����һ�����ڴӸ����Ӷ���� InputStream�� 
        InputStream in = url.openStream();
        int c;
        while ((c = in.read()) != -1) {
            System.out.print(c);
        }
        in.close();
    }

    public static void ��ȡ��Ϸ����1() throws IOException {
        URL url = new URL("http://123.207.53.97:8082/����.txt");
        //�򿪵��� URL �����Ӳ�����һ�����ڴӸ����Ӷ���� InputStream�� 
        Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
        int c;
        while ((c = reader.read()) != -1) {
            if (c == 48) { //0 = 48
                System.out.print("��");
            } else if (c == 49) {
                System.out.print("��");
            } else {
                System.out.print("���󣡣���");
            }
        }
        reader.close();
    }
}
