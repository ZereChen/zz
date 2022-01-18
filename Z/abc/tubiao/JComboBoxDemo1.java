
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class JComboBoxDemo1 {

    public static void main(String[] args) {
        JFrame f = new JFrame("JComboBoxDemo1");// ����һ��JFrame����
        Container contentPane = f.getContentPane();	// ����һ������
        contentPane.setLayout(new GridLayout(1, 2));
        String[] s = {"�й�", "�ձ�", "����", "Ӣ��", "����", "̨��", "����", "����"};// ����һ���ַ������飬�������ʼ��
        Vector v = new Vector();
        v.addElement("Nokia N80");
        v.addElement("Nokia 8250");
        v.addElement("Motorola v8088");
        v.addElement("Motorola v3");
        v.addElement("Panasonic 8850");
        v.addElement("����");
        JComboBox combo1 = new JComboBox(s);// ����һ��JComboBox����
        combo1.addItem("�й�");// ����JComboBox�����ṩ��addItem()����������һ����Ŀ����JComboBox�С�
        combo1.setBorder(BorderFactory.createTitledBorder("����ϲ�����ĸ���������?"));
        JComboBox combo2 = new JComboBox(v);
        combo2.setBorder(BorderFactory.createTitledBorder("����ϲ����һ���ֻ��أ�"));
        contentPane.add(combo1);
        contentPane.add(combo2);
        f.pack();
        f.show();
        f.addWindowListener(new WindowAdapter() {// ��Ӵ��ڼ�����
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
