package gui.����;

import database.DatabaseConnection;
import database1.DatabaseConnection1;
import gui.����̨.����̨2��;
import handling.world.MapleParty;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;

public class ����_1 extends javax.swing.JFrame {

    public ����_1() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        setIconImage(icon.getImage());
        setTitle("ZEVMS���غ�̨");
        initComponents();
        //MapleParty.IP��ַ = "193.112.13.208";
        ��¼��ַ.setText(MapleParty.IP��ַ);
        ���ض˿�.setText(ȡֵ("���ض˿�"));
        �ٷ���վ.setText(ȡֵ("�ٷ���վ"));
        ��������.setText(ȡֵ("��������"));
        ���ع���.setText(ȡֵ("���ع���"));
        ˢ�¼��ֵ();
        //new ZevmsLauncherServer(60000).start();
        //ˢ�����ؿ���();
    }

    public static String ȡֵ(String b) {
        String data = "";
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM wz����");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("a").equals("" + MapleParty.IP��ַ + "") && rs.getString("b").equals(b)) {
                    data = rs.getString("c");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ȡֵ������");
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ���ض˿� = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        �ٷ���վ = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        �������� = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ��Ϣ��ʾ = new javax.swing.JScrollPane();
        ���ع��� = new javax.swing.JTextPane();
        �޸Ķ˿� = new javax.swing.JButton();
        ��¼��ַ = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        �޸Ĺ��� = new javax.swing.JButton();
        �޸����� = new javax.swing.JButton();
        �޸���վ = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        �ͻ��˼�� = new javax.swing.JTable();
        ���ֵ = new javax.swing.JTextField();
        ����ļ� = new javax.swing.JTextField();
        �޸Ŀͻ��˼�� = new javax.swing.JButton();
        ˢ�¿ͻ��˼�� = new javax.swing.JButton();
        �����ͻ��˼�� = new javax.swing.JButton();
        ɾ���ͻ��˼�� = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel3.setText("��¼�����棻");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));
        jPanel1.add(���ض˿�, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 150, 30));

        jLabel2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel2.setText("��¼�˿ڣ�");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, -1, 20));
        jPanel1.add(�ٷ���վ, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, 320, 30));

        jLabel4.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel4.setText("�ٷ���վ��");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, -1, 20));
        jPanel1.add(��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, 320, 30));

        jLabel5.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel5.setText("�������ӣ�");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 240, 20));

        ���ع���.setFont(new java.awt.Font("��Բ", 0, 17)); // NOI18N
        ��Ϣ��ʾ.setViewportView(���ع���);

        jPanel1.add(��Ϣ��ʾ, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 440, 380));

        �޸Ķ˿�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸Ķ˿�.setText("�޸Ķ˿�");
        �޸Ķ˿�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸Ķ˿�ActionPerformed(evt);
            }
        });
        jPanel1.add(�޸Ķ˿�, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 110, 30));

        ��¼��ַ.setEditable(false);
        jPanel1.add(��¼��ַ, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 150, 30));

        jLabel6.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel6.setText("��¼��ַ��");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, 20));

        �޸Ĺ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸Ĺ���.setText("�޸Ĺ���");
        �޸Ĺ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸Ĺ���ActionPerformed(evt);
            }
        });
        jPanel1.add(�޸Ĺ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 390, 110, 30));

        �޸�����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸�����.setText("�޸�����");
        �޸�����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸�����ActionPerformed(evt);
            }
        });
        jPanel1.add(�޸�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, 110, 30));

        �޸���վ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸���վ.setText("�޸���վ");
        �޸���վ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸���վActionPerformed(evt);
            }
        });
        jPanel1.add(�޸���վ, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, 110, 30));

        jTabbedPane1.addTab("��������", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �ͻ��˼��.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        �ͻ��˼��.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "����ļ�", "���ֵ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(�ͻ��˼��);
        if (�ͻ��˼��.getColumnModel().getColumnCount() > 0) {
            �ͻ��˼��.getColumnModel().getColumn(0).setResizable(false);
            �ͻ��˼��.getColumnModel().getColumn(0).setPreferredWidth(100);
            �ͻ��˼��.getColumnModel().getColumn(1).setResizable(false);
            �ͻ��˼��.getColumnModel().getColumn(1).setPreferredWidth(500);
        }

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 790, 390));
        jPanel2.add(���ֵ, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 370, -1));
        jPanel2.add(����ļ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 130, -1));

        �޸Ŀͻ��˼��.setText("�޸�");
        �޸Ŀͻ��˼��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸Ŀͻ��˼��ActionPerformed(evt);
            }
        });
        jPanel2.add(�޸Ŀͻ��˼��, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 410, 70, 25));

        ˢ�¿ͻ��˼��.setText("ˢ��");
        ˢ�¿ͻ��˼��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�¿ͻ��˼��ActionPerformed(evt);
            }
        });
        jPanel2.add(ˢ�¿ͻ��˼��, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 410, 70, 25));

        �����ͻ��˼��.setText("����");
        �����ͻ��˼��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����ͻ��˼��ActionPerformed(evt);
            }
        });
        jPanel2.add(�����ͻ��˼��, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 410, 70, 25));

        ɾ���ͻ��˼��.setText("ɾ��");
        ɾ���ͻ��˼��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ���ͻ��˼��ActionPerformed(evt);
            }
        });
        jPanel2.add(ɾ���ͻ��˼��, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 410, 70, 25));

        jTabbedPane1.addTab("�ͻ��˼��", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jTabbedPane1.addTab("    *    ", jPanel3);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 480));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void �޸Ŀͻ��˼��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸Ŀͻ��˼��ActionPerformed
        if (����ļ�.getText().equals("")) {
            return;
        }
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM WZ���ֵ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String aa = null;
                aa = "update WZ���ֵ set c ='" + ���ֵ.getText() + "' where b ='" + ����ļ�.getText() + "'&& a = '" + MapleParty.IP��ַ + "';";
                PreparedStatement cc = DatabaseConnection1.getConnection1().prepareStatement(aa);
                cc.executeUpdate(aa);
            }
            ˢ�¼��ֵ();
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_�޸Ŀͻ��˼��ActionPerformed

    private void �޸Ķ˿�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸Ķ˿�ActionPerformed
        if ("".equals(ȡֵ("���ض˿�"))) {
            try (Connection con = DatabaseConnection1.getConnection1(); PreparedStatement ps = con.prepareStatement("INSERT INTO WZ���� ( a,b,c) VALUES ( ?, ?, ?)")) {
                ps.setString(1, MapleParty.IP��ַ);
                ps.setString(2, "���ض˿�");
                ps.setString(3, "" + ���ض˿�.getText() + "");
                ps.executeUpdate();
            } catch (SQLException ex) {
            }
        } else {
            try {
                Connection con = DatabaseConnection1.getConnection1();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM WZ����");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String aa = null;
                    aa = "update WZ���� set c ='" + ���ض˿�.getText() + "' where b ='���ض˿�'&& a = '" + MapleParty.IP��ַ + "';";
                    PreparedStatement cc = DatabaseConnection1.getConnection1().prepareStatement(aa);
                    cc.executeUpdate(aa);
                }
                ˢ�¼��ֵ();
            } catch (SQLException ex) {
            }
        }
    }//GEN-LAST:event_�޸Ķ˿�ActionPerformed

    private void �޸Ĺ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸Ĺ���ActionPerformed
        if ("".equals(ȡֵ("���ع���"))) {
            try (Connection con = DatabaseConnection1.getConnection1(); PreparedStatement ps = con.prepareStatement("INSERT INTO WZ���� ( a,b,c) VALUES ( ?, ?, ?)")) {
                ps.setString(1, MapleParty.IP��ַ);
                ps.setString(2, "���ع���");
                ps.setString(3, "" + ���ع���.getText() + "");
                ps.executeUpdate();
            } catch (SQLException ex) {
            }
        } else {
            try {
                Connection con = DatabaseConnection1.getConnection1();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM WZ����");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String aa = null;
                    aa = "update WZ���� set c ='" + ���ع���.getText() + "' where b ='���ع���'&& a = '" + MapleParty.IP��ַ + "';";
                    PreparedStatement cc = DatabaseConnection1.getConnection1().prepareStatement(aa);
                    cc.executeUpdate(aa);
                }
                ˢ�¼��ֵ();
            } catch (SQLException ex) {
            }
        }
    }//GEN-LAST:event_�޸Ĺ���ActionPerformed

    private void �޸�����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸�����ActionPerformed
        if ("".equals(ȡֵ("��������"))) {
            try (Connection con = DatabaseConnection1.getConnection1(); PreparedStatement ps = con.prepareStatement("INSERT INTO WZ���� ( a,b,c) VALUES ( ?, ?, ?)")) {
                ps.setString(1, MapleParty.IP��ַ);
                ps.setString(2, "��������");
                ps.setString(3, "" + ��������.getText() + "");
                ps.executeUpdate();
            } catch (SQLException ex) {
            }
        } else {
            try {
                Connection con = DatabaseConnection1.getConnection1();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM WZ����");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String aa = null;
                    aa = "update WZ���� set c ='" + ��������.getText() + "' where b ='��������'&& a = '" + MapleParty.IP��ַ + "';";
                    PreparedStatement cc = DatabaseConnection1.getConnection1().prepareStatement(aa);
                    cc.executeUpdate(aa);
                }
                ˢ�¼��ֵ();
            } catch (SQLException ex) {
            }
        }
    }//GEN-LAST:event_�޸�����ActionPerformed

    private void �޸���վActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸���վActionPerformed
        if ("".equals(ȡֵ("�ٷ���վ"))) {
            try (Connection con = DatabaseConnection1.getConnection1(); PreparedStatement ps = con.prepareStatement("INSERT INTO WZ���� ( a,b,c) VALUES ( ?, ?, ?)")) {
                ps.setString(1, MapleParty.IP��ַ);
                ps.setString(2, "�ٷ���վ");
                ps.setString(3, "" + �ٷ���վ.getText() + "");
                ps.executeUpdate();
            } catch (SQLException ex) {
            }
        } else {
            try {
                Connection con = DatabaseConnection1.getConnection1();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM WZ����");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String aa = null;
                    aa = "update WZ���� set c ='" + �ٷ���վ.getText() + "' where b ='�ٷ���վ'&& a = '" + MapleParty.IP��ַ + "';";
                    PreparedStatement cc = DatabaseConnection1.getConnection1().prepareStatement(aa);
                    cc.executeUpdate(aa);
                }
                ˢ�¼��ֵ();
            } catch (SQLException ex) {
            }
        }
    }//GEN-LAST:event_�޸���վActionPerformed

    private void ˢ�¿ͻ��˼��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�¿ͻ��˼��ActionPerformed
        ˢ�¼��ֵ();
    }//GEN-LAST:event_ˢ�¿ͻ��˼��ActionPerformed

    private void �����ͻ��˼��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����ͻ��˼��ActionPerformed
        try (Connection con = DatabaseConnection1.getConnection1();
                PreparedStatement ps = con.prepareStatement("INSERT INTO WZ���ֵ (a,b,c ) VALUES ( ?,?,? )")) {
            ps.setString(1, MapleParty.IP��ַ);
            ps.setString(2, ����ļ�.getText());
            ps.setString(3, ���ֵ.getText());
            ps.executeUpdate();
            ˢ�¼��ֵ();
            JOptionPane.showMessageDialog(null, "�������ɹ���");
        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_�����ͻ��˼��ActionPerformed

    private void ɾ���ͻ��˼��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ���ͻ��˼��ActionPerformed

        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (!"".equals(����ļ�.getText())) {
            try {
                ps1 = DatabaseConnection1.getConnection1().prepareStatement("SELECT * FROM WZ���ֵ WHERE a = ?");
                ps1.setString(1, MapleParty.IP��ַ);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from WZ���ֵ where b = '" + ����ļ�.getText() + "';";
                    ps1.executeUpdate(sqlstr);
                    ˢ�¼��ֵ();
                }
            } catch (SQLException ex) {

            }
        } else {
            JOptionPane.showMessageDialog(null, "ѡ����Ҫɾ���ļ�¼��");
        }
    }//GEN-LAST:event_ɾ���ͻ��˼��ActionPerformed
    public static int ���ؿ���1 = 0;

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(����̨2��.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        ������Ⱥ�������.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
            // UIManager.setLookAndFeel(new SubstanceBusinessLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ������Ⱥ�������().setVisible(true);
            }
        });
    }

    private void ˢ�¼��ֵ() {
        for (int i = ((DefaultTableModel) (this.�ͻ��˼��.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�ͻ��˼��.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM WZ���ֵ WHERE a = '" + MapleParty.IP��ַ + "'");//
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) �ͻ��˼��.getModel()).insertRow(�ͻ��˼��.getRowCount(), new Object[]{
                    rs.getString("b"),
                    rs.getString("c")

                });
            }
        } catch (SQLException ex) {

        }
        �ͻ��˼��.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = �ͻ��˼��.getSelectedRow();
                String a = �ͻ��˼��.getValueAt(i, 0).toString();
                String a1 = �ͻ��˼��.getValueAt(i, 1).toString();
                ����ļ�.setText(a);
                ���ֵ.setText(a1);

            }
        });

    }

    public void ��������(String a, int b) {
        int ��⿪�� = gui.Start.ConfigValuesMap.get(a);
        if (��⿪�� <= 0) {
            PreparedStatement ps1 = null;
            ResultSet rs = null;
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 1);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from configvalues where id =" + b + "";
                    ps1.executeUpdate(sqlstr);
                }
            } catch (SQLException ex) {

            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
                ps.setInt(1, b);
                ps.setString(2, a);
                ps.setInt(3, 1);
                ps.executeUpdate();
            } catch (SQLException ex) {

            }
        } else {
            PreparedStatement ps1 = null;
            ResultSet rs = null;
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 1);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from configvalues where id =" + b + "";
                    ps1.executeUpdate(sqlstr);
                }
            } catch (SQLException ex) {

            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
                ps.setInt(1, b);
                ps.setString(2, a);
                ps.setInt(3, 0);
                ps.executeUpdate();
            } catch (SQLException ex) {

            }
        }
        gui.Start.GetConfigValues();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JScrollPane ��Ϣ��ʾ;
    private javax.swing.JButton �޸Ĺ���;
    private javax.swing.JButton �޸Ŀͻ��˼��;
    private javax.swing.JButton �޸Ķ˿�;
    private javax.swing.JButton �޸���վ;
    private javax.swing.JButton �޸�����;
    private javax.swing.JButton ɾ���ͻ��˼��;
    private javax.swing.JButton ˢ�¿ͻ��˼��;
    private javax.swing.JTextField �ٷ���վ;
    private javax.swing.JTable �ͻ��˼��;
    private javax.swing.JButton �����ͻ��˼��;
    private javax.swing.JTextField ���ֵ;
    private javax.swing.JTextField ����ļ�;
    private javax.swing.JTextField ��¼��ַ;
    private javax.swing.JTextPane ���ع���;
    private javax.swing.JTextField ���ض˿�;
    private javax.swing.JTextField ��������;
    // End of variables declaration//GEN-END:variables
}
