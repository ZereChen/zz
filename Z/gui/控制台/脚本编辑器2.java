package gui.����̨;

import static abc.Game.�汾;
import abc.sancu.FileDemo_05;
import client.�ű��༭��;
import handling.world.MapleParty;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import server.MapleItemInformationProvider;
import tools.FileoutputUtil;

public class �ű��༭��2 extends javax.swing.JFrame {

    public �ű��༭��2() {
        setTitle("ZEVMS�ű�������");
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        setIconImage(icon.getImage());
        initComponents();
        String ���1 = "";
        //����汾�ź͹�����Ϣ
        try {
            String versionInfo = �ű��༭��.Pv(�汾);
            String unicode = new String(versionInfo.getBytes(), "UTF-8");
            unicode = unicode.replace("\\n", "\n");
            �ű���ʾ��.setText("" + unicode + "");

        } catch (Exception e) {
            System.out.println("��ȡ�汾��Ϣ����");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        �ű���� = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        �ű���ʾ�� = new javax.swing.JTextPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        �ж���Ʒ���� = new javax.swing.JTextField();
        �ж����� = new javax.swing.JButton();
        �ж���Ʒ���� = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        �жϽ�Ǯ���� = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        �жϽ�� = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        ��ȡ��Ʒ���� = new javax.swing.JTextField();
        ��ȡ���� = new javax.swing.JButton();
        ��ȡ��Ʒ���� = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        ��ȡ��Ǯ���� = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        ������Ʒ���� = new javax.swing.JTextField();
        �������� = new javax.swing.JButton();
        ������Ʒ���� = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        �ű����� = new javax.swing.JButton();
        ��ʼд�ű� = new javax.swing.JButton();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel1.setText("���ȶ���ű���ţ�");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, -1));
        jPanel1.add(�ű����, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 30, 100, 30));

        �ű���ʾ��.setBackground(new java.awt.Color(0, 0, 0));
        �ű���ʾ��.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        �ű���ʾ��.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(�ű���ʾ��);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 700));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�ж�����", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(�ж���Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 150, 30));

        �ж�����.setText("�ж���Ʒ������");
        �ж�����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �ж�����ActionPerformed(evt);
            }
        });
        jPanel2.add(�ж�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 150, 30));
        jPanel2.add(�ж���Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 150, 30));

        jLabel2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel2.setText("��Ʒ������");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel3.setText("��Ʒ���룻");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 210));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�жϽ�Ǯ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(�жϽ�Ǯ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 110, 30));

        jLabel4.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel4.setText("������");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 30));

        jButton1.setText("��ȯ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 70, 30));

        �жϽ��.setText("���");
        �жϽ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �жϽ��ActionPerformed(evt);
            }
        });
        jPanel3.add(�жϽ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 70, 30));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 210, 140));

        jTabbedPane1.addTab("�ж���", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��ȡ����", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(��ȡ��Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 150, 30));

        ��ȡ����.setText("��ȡ����");
        ��ȡ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ȡ����ActionPerformed(evt);
            }
        });
        jPanel6.add(��ȡ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 150, 30));
        jPanel6.add(��ȡ��Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 150, 30));

        jLabel5.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel5.setText("��Ʒ������");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel6.setText("��Ʒ���룻");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 210));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��ȡ��Ǯ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel7.add(��ȡ��Ǯ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 110, 30));

        jLabel7.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel7.setText("������");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 30));

        jButton3.setText("��ȯ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 70, 30));

        jButton4.setText("���");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 70, 30));

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 210, 140));

        jTabbedPane1.addTab("��ȡ��", jPanel5);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��������", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel9.add(������Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 150, 30));

        ��������.setText("������Ʒ");
        ��������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��������ActionPerformed(evt);
            }
        });
        jPanel9.add(��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 150, 30));
        jPanel9.add(������Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 150, 30));

        jLabel8.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel8.setText("��Ʒ������");
        jPanel9.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel9.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel9.setText("��Ʒ���룻");
        jPanel9.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 210));

        jTabbedPane1.addTab("������", jPanel8);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 70, 240, 580));

        �ű�����.setText("�ű�����");
        �ű�����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �ű�����ActionPerformed(evt);
            }
        });
        jPanel1.add(�ű�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 660, 130, 30));

        ��ʼд�ű�.setText("��ʼд�ű�");
        ��ʼд�ű�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ʼд�ű�ActionPerformed(evt);
            }
        });
        jPanel1.add(��ʼд�ű�, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 30, 130, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void ��ʾ(String str) {
        �ű���ʾ��.setText(�ű���ʾ��.getText() + "" + str + "\r\n");
    }
    private void ��ʼд�ű�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ʼд�ű�ActionPerformed

        boolean ��� = this.�ű����.getText().matches("[0-9]+");
        if (!��� || �ű����.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "�ű���Ŵ���");
            return;
        }
        �ű���ʾ��.setText("");
        //ɾ���Ѿ�ͬ�����ļ�
        Properties �O���n = System.getProperties();
        FileDemo_05.deleteFiles("" + �O���n.getProperty("user.dir") + "/�ű������ĵ�/" + �ű����.getText() + ".js");
        //���
        FileoutputUtil.logToFile("�ű������ĵ�/" + �ű����.getText() + ".js",
                "/*\r\n"
                + "ZEVMS�ű�������2\r\n"
                + "*/\r\n"
                + "importPackage(java.lang);\r\n"
                + "importPackage(Packages.tools);\r\n"
                + "importPackage(Packages.client);\r\n"
                + "importPackage(Packages.server);\r\n"
                + "function action(mode, type, selection) {\r\n"
        );
        MapleParty.�ű�����1 = 0;
        MapleParty.�ű��ж�1 = 0;
        MapleParty.�ű���ȡ1 = 0;
        MapleParty.�ű��ж����� = 0;
        MapleParty.�ű���ȡ���� = 0;
        ��ʾ("�ű���ʼ�����Ϊ��" + �ű����.getText() + ".js\r\n");
    }//GEN-LAST:event_��ʼд�ű�ActionPerformed

    private void �ж�����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�ж�����ActionPerformed
        boolean a1 = this.�ж���Ʒ����.getText().matches("[0-9]+");
        boolean a2 = this.�ж���Ʒ����.getText().matches("[0-9]+");
        if (!a1 || !a2 || �ж���Ʒ����.getText().equals("") || �ж���Ʒ����.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "��Ч��������д��");
            return;
        }
        FileoutputUtil.logToFile("�ű������ĵ�/" + �ű����.getText() + ".js",
                "    if(!cm.�ж���Ʒ����(" + �ж���Ʒ����.getText() + ", " + �ж���Ʒ����.getText() + ")){\r\n"
                + "        cm.˵������(\"��Ҫ#v" + �ж���Ʒ����.getText() + "##b#t" + �ж���Ʒ����.getText() + "# #kx " + �ж���Ʒ����.getText() + "#k\");\r\n"
                + "        cm.�Ի�����();\r\n"
                + "        return;\r\n"
                + "    }\r\n"
        );
        if (MapleParty.�ű��ж�1 <= 0) {
            ��ʾ("��Ҫ������Ʒ��\r\n��(�ж�)");
            MapleParty.�ű��ж�1 = 1;
        }
        MapleParty.�ű��ж����� += 1;
        ��ʾ("���ж���Ҫ " + MapleItemInformationProvider.getInstance().getName(Integer.parseInt(�ж���Ʒ����.getText())) + "(" + Integer.parseInt(�ж���Ʒ����.getText()) + ") x " + �ж���Ʒ����.getText() + "");
    }//GEN-LAST:event_�ж�����ActionPerformed

    private void �ű�����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�ű�����ActionPerformed
        Properties �O���n = System.getProperties();
        MapleParty.�ű�����1 = 0;
        MapleParty.�ű��ж�1 = 0;
        MapleParty.�ű���ȡ1 = 0;
        MapleParty.�ű��ж����� = 0;
        MapleParty.�ű���ȡ���� = 0;
        FileoutputUtil.logToFile("�ű������ĵ�/" + �ű����.getText() + ".js",
                "    cm.�Ի�����()\r\n"
                + "}"
        );

        ��ʾ("��(�ű�����)\r\n\r\n�ű���������ȥĿ¼·��:" + �O���n.getProperty("user.dir") + "\\�ű������ĵ�\\" + �ű����.getText() + ".js���Ľű���š�");
    }//GEN-LAST:event_�ű�����ActionPerformed

    private void ��ȡ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ȡ����ActionPerformed
        boolean a1 = this.��ȡ��Ʒ����.getText().matches("[0-9]+");
        boolean a2 = this.��ȡ��Ʒ����.getText().matches("[0-9]+");
        if (!a1 || !a2 || ��ȡ��Ʒ����.getText().equals("") || ��ȡ��Ʒ����.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "��Ч��������д��");
            return;
        }
        FileoutputUtil.logToFile("�ű������ĵ�/" + �ű����.getText() + ".js",
                "    cm.����Ʒ(" + ��ȡ��Ʒ����.getText() + ", " + ��ȡ��Ʒ����.getText() + ");\r\n"
        );
        if (MapleParty.�ű���ȡ1 <= 0) {
            ��ʾ("��(�жϽ���)\r\n��ȡ������Ʒ��\r\n��(��ȡ)");
            MapleParty.�ű���ȡ1 = 1;
        }
        MapleParty.�ű���ȡ���� += 1;
        ��ʾ("����ȡ��Ʒ " + MapleItemInformationProvider.getInstance().getName(Integer.parseInt(��ȡ��Ʒ����.getText())) + "(" + Integer.parseInt(��ȡ��Ʒ����.getText()) + ") x " + ��ȡ��Ʒ����.getText() + "");
    }//GEN-LAST:event_��ȡ����ActionPerformed

    private void ��������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��������ActionPerformed
        boolean a1 = this.������Ʒ����.getText().matches("[0-9]+");
        boolean a2 = this.������Ʒ����.getText().matches("[0-9]+");
        if (!a1 || !a2 || ������Ʒ����.getText().equals("") || ������Ʒ����.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "��Ч��������д��");
            return;
        }
        if (MapleParty.�ű��ж����� != MapleParty.�ű���ȡ����) {
            int n = JOptionPane.showConfirmDialog(this, "�ж�������������ȡ�������Ƿ�Ҫ����д����", "��ʾ", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.NO_OPTION) {
                return;
            }
        }
        if (MapleParty.�ű�����1 <= 0) {
            ��ʾ("��(��ȡ����)\r\n�������Ʒ��\r\n��(����)");
            MapleParty.�ű�����1 = 1;
        }
        FileoutputUtil.logToFile("�ű������ĵ�/" + �ű����.getText() + ".js",
                "    cm.����Ʒ(" + ������Ʒ����.getText() + ", " + ������Ʒ����.getText() + ")\r\n"
        );

        ��ʾ("�Ǹ�����Ʒ " + MapleItemInformationProvider.getInstance().getName(Integer.parseInt(������Ʒ����.getText())) + "(" + Integer.parseInt(������Ʒ����.getText()) + ") x " + ������Ʒ����.getText() + "");
    }//GEN-LAST:event_��������ActionPerformed

    private void �жϽ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�жϽ��ActionPerformed
        boolean a1 = this.�жϽ�Ǯ����.getText().matches("[0-9]+");
        boolean a2 = this.�жϽ�Ǯ����.getText().matches("[0-9]+");
        if (!a1 || !a2 || �жϽ�Ǯ����.getText().equals("") || �жϽ�Ǯ����.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "��Ч��������д��");
            return;
        }
        FileoutputUtil.logToFile("�ű������ĵ�/" + �ű����.getText() + ".js",
                "    if(cm.�жϽ��()<=" + �жϽ�Ǯ����.getText() + "){\r\n"
                + "        cm.˵������(\"��Ҫ #b" + �жϽ�Ǯ����.getText() + " #k ��ҡ�\");\r\n"
                + "        cm.�Ի�����();\r\n"
                + "        return;\r\n"
                + "    }\r\n"
        );
        if (MapleParty.�ű��ж�1 <= 0) {
            ��ʾ("��Ҫ������Ʒ��\r\n��(�ж�)");
            MapleParty.�ű��ж�1 = 1;
        }
        MapleParty.�ű��ж����� += 1;
        ��ʾ("���ж���Ҫ " + �жϽ�Ǯ����.getText() + " ���");
    }//GEN-LAST:event_�жϽ��ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean a1 = this.�жϽ�Ǯ����.getText().matches("[0-9]+");
        boolean a2 = this.�жϽ�Ǯ����.getText().matches("[0-9]+");
        if (!a1 || !a2 || �жϽ�Ǯ����.getText().equals("") || �жϽ�Ǯ����.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "��Ч��������д��");
            return;
        }
        FileoutputUtil.logToFile("�ű������ĵ�/" + �ű����.getText() + ".js",
                "    if(cm.�жϵ�ȯ()<=" + �жϽ�Ǯ����.getText() + "){\r\n"
                + "        cm.˵������(\"��Ҫ  #b" + �жϽ�Ǯ����.getText() + " #k ��ȯ��\");\r\n"
                + "        cm.�Ի�����();\r\n"
                + "        return;\r\n"
                + "    }\r\n"
        );
        if (MapleParty.�ű��ж�1 <= 0) {
            ��ʾ("��Ҫ������Ʒ��\r\n��(�ж�)");
            MapleParty.�ű��ж�1 = 1;
        }
        MapleParty.�ű��ж����� += 1;
        ��ʾ("���ж���Ҫ " + �жϽ�Ǯ����.getText() + " ��ȯ");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        boolean a1 = this.��ȡ��Ǯ����.getText().matches("[0-9]+");
        boolean a2 = this.��ȡ��Ǯ����.getText().matches("[0-9]+");
        if (!a1 || !a2 || ��ȡ��Ǯ����.getText().equals("") || ��ȡ��Ǯ����.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "��Ч��������д��");
            return;
        }
        FileoutputUtil.logToFile("�ű������ĵ�/" + �ű����.getText() + ".js",
                "    cm.�ս��(" + ��ȡ��Ǯ����.getText() + ");\r\n"
        );
        if (MapleParty.�ű���ȡ1 <= 0) {
            ��ʾ("��(�жϽ���)\r\n��ȡ������Ʒ��\r\n��(��ȡ)");
            MapleParty.�ű���ȡ1 = 1;
        }
        MapleParty.�ű���ȡ���� += 1;
        ��ʾ("����ȡ��� " + ��ȡ��Ǯ����.getText() + "");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        boolean a1 = this.��ȡ��Ǯ����.getText().matches("[0-9]+");
        boolean a2 = this.��ȡ��Ǯ����.getText().matches("[0-9]+");
        if (!a1 || !a2 || ��ȡ��Ǯ����.getText().equals("") || ��ȡ��Ǯ����.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "��Ч��������д��");
            return;
        }
        FileoutputUtil.logToFile("�ű������ĵ�/" + �ű����.getText() + ".js",
                "    cm.�յ�ȯ(" + ��ȡ��Ǯ����.getText() + ");\r\n"
        );
        if (MapleParty.�ű���ȡ1 <= 0) {
            ��ʾ("��(�жϽ���)\r\n��ȡ������Ʒ��\r\n��(��ȡ)");
            MapleParty.�ű���ȡ1 = 1;
        }
        MapleParty.�ű���ȡ���� += 1;
        ��ʾ("����ȡ��ȯ " + ��ȡ��Ǯ����.getText() + "");
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {
        �ű��༭��2.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new �ű��༭��2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton �ж�����;
    private javax.swing.JTextField �ж���Ʒ����;
    private javax.swing.JTextField �ж���Ʒ����;
    private javax.swing.JButton �жϽ��;
    private javax.swing.JTextField �жϽ�Ǯ����;
    private javax.swing.JButton ��ʼд�ű�;
    private javax.swing.JButton ��ȡ����;
    private javax.swing.JTextField ��ȡ��Ʒ����;
    private javax.swing.JTextField ��ȡ��Ʒ����;
    private javax.swing.JTextField ��ȡ��Ǯ����;
    private javax.swing.JButton ��������;
    private javax.swing.JTextField ������Ʒ����;
    private javax.swing.JTextField ������Ʒ����;
    private javax.swing.JTextPane �ű���ʾ��;
    private javax.swing.JButton �ű�����;
    private javax.swing.JTextField �ű����;
    // End of variables declaration//GEN-END:variables
}
