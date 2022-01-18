package gui.ͼƬ.xiazai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class DownloadFrame implements ActionListener {

    ArrayList<JProgressBar> jps = new ArrayList<JProgressBar>();
    ArrayList<JLabel> jls = new ArrayList<JLabel>();
    JButton jbutton;
    public JTextField textField;
    public JButton button;
    public JTextField textField_1;
    public JTextField textField_2;
    public JLabel label_3;
    String sourceFile = null;
    String path = null;
    String name = null;

    /**
     * ***************************************************����¼�*****************************************************************
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbutton) {
            if (!ThreadController.start) {
                sourceFile = textField.getText();//����·��
                path = textField_1.getText();//�ļ�·��
                name = textField_2.getText();//�ļ���						
                System.out.println(textField.getText().substring(0, 3));

                if (textField.getText().substring(0, 3).equals("htt")) {
                    System.out.println(textField.getText().substring(0, 3));
                    StartDownload sd = new StartDownload(jps, jls, sourceFile, name, path, label_3);
                    sd.start();
                    jbutton.setText("��ͣ	");
                    ThreadController.start = true;
                }
            } else {
                jbutton.setText("����");
                ThreadController.start = false;
            }
        }
        if (e.getSource() == button) {
            try {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.showDialog(new JLabel(), "ѡ��");
                File file = jfc.getSelectedFile();
                if (file.isDirectory()) {
                    System.out.println("�ļ���:" + file.getAbsolutePath());
                    String m = file.getAbsolutePath();
                    textField_1.setText(m);
                } else if (file.isFile()) {
                    textField_1.setText("���ļ�����������ȷ��·��");
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
    }

    /**
     * *********************************************************����************************************************************
     */
    private void createAndShowGUI() {
        JFrame frame = new JFrame("���ؽ���");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel pn = new Panel(null);
        pn.setSize(800, 600);
        jbutton = new JButton("��ʼ����");
        jbutton.setMnemonic(KeyEvent.VK_I);//alt+i���Խ�������
        jbutton.addActionListener(this);
        jbutton.setBounds(356, 346, 88, 44);
        pn.add(jbutton);
        int intWidth = 0;

        JLabel label = new JLabel("��������������");
        label.setBounds(100, 10, 100, 30);
        pn.add(label);

        textField = new JTextField();
        textField.setBounds(200, 11, 500, 30);
        pn.add(textField);
        textField.setColumns(10);

        JLabel label_1 = new JLabel("ѡ������·����");
        label_1.setBounds(100, 50, 100, 30);
        pn.add(label_1);

        textField_1 = new JTextField();
        textField_1.setBounds(200, 50, 400, 30);
        pn.add(textField_1);
        textField_1.setColumns(10);

        button = new JButton("ѡ��·��");
        button.setBounds(610, 51, 100, 30);
        pn.add(button);
        button.addActionListener(this);

        JLabel label_2 = new JLabel("�ļ�����");
        label_2.setBounds(100, 90, 100, 30);
        pn.add(label_2);

        textField_2 = new JTextField();
        textField_2.setBounds(200, 90, 400, 30);
        pn.add(textField_2);
        textField_2.setColumns(10);

        label_3 = new JLabel("");
        label_3.setBounds(470, 361, 54, 15);
        pn.add(label_3);

        for (int i = 1; i <= 3; i++) {
            if ((i + 2) % 3 == 0) {
                JLabel lbMain = new JLabel();
                lbMain.setBounds(100, 100 * (i + 2) / 3 + 20, 700, 30);
                intWidth = 100 * (i + 2) / 3 + 20;
                pn.add(lbMain);
                jls.add(lbMain);
            }
            JLabel labelDown = new JLabel("Thread" + ((i + 2) % 3 + 1) + ":");
            labelDown.setBounds(100, intWidth + 30, 100, 30);
            pn.add(labelDown);

            JProgressBar progress = new JProgressBar(1, 100); // ʵ����������
            progress.setStringPainted(true);    // �������
            progress.setName("progress" + i);
            progress.setBounds(200, intWidth + 30, 500, 30);
            pn.add(progress);
            jps.add(progress);
            intWidth = intWidth + 40;
        }

        frame.getContentPane().add(pn);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(816, 496);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DownloadFrame downloadframe = new DownloadFrame();
                downloadframe.createAndShowGUI();
            }
        });
    }
}
