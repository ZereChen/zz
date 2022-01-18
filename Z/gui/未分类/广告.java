package gui.δ����;

import gui.ZEVMS;
import java.awt.*;
import java.net.*;
import javax.swing.*;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import static gui.Start.CashShopServer;
//������ȡ��

public class ��� extends JFrame {

    JTextField jtfUrl;  //����ͼ���ַurl
    JButton jbGetImage;  //ȡͼ��ť
    Image image; //��ȡ��ͼ��
    Toolkit toolKit;  //Toolkit����,���ڻ�ȡͼ��

    public ���() {
        super("ZEVMS����λ - < 7 ����Զ��رմ��� > www.zevmsmxd.cn");  //���ø��๹�캯��
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/pp/2.png"));//����ͼ��
        setIconImage(icon.getImage());
        Container container = getContentPane();   //�õ�����
        toolKit = getToolkit(); //�õ����߰�
        try {
            String urlStr = "http://123.207.53.97:8082/ad1.png";    //�õ�ͼ���URL��ַ
            URL url = new URL(urlStr);
            image = toolKit.getImage(url); //��ȡͼ��
            repaint(); //�ػ���Ļ
        } catch (MalformedURLException ex) {
            ex.printStackTrace(); //���������Ϣ
        }
        container.setLayout(new FlowLayout()); //���ò��ֹ�����
        setSize(1212, 700);  //���ô��ڳߴ�
        // setSize(1200, 650);  
        setVisible(true);  //���ô��ڿ���
        /*new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(7 * 1000);//�������Զ��ر�
                    dispose();
                    CashShopServer();
                } catch (InterruptedException e) {
                }
            }
        }.start();*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (image != null) {
            g.drawImage(image, 5, 33, this); //������ϻ���ͼ��
        }
    }

    public static void main(String[] args) {
        ZEVMS.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new ���();
    }
}
