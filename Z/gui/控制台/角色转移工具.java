/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.����̨;

import static abc.Game.���ڱ���;
import database.DatabaseConnection;
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

/**
 *
 * @author Administrator
 */
public class ��ɫת�ƹ��� extends javax.swing.JFrame {

    /**
     * Creates new form �������̨
     */
    public ��ɫת�ƹ���() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        setIconImage(icon.getImage());
        setTitle("" + ���ڱ��� + "����ɫת�ƹ��ߣ��ɹرա�");
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ��ʾ��Ϸ�˺� = new javax.swing.JTable();
        ��ʾ���ɫ�˺� = new javax.swing.JButton();
        �˺�ID = new javax.swing.JTextField();
        jLabel315 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ת����ɫ���˺� = new javax.swing.JTextField();
        jLabel314 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ��ʾ��ɫ1 = new javax.swing.JTable();
        jLabel316 = new javax.swing.JLabel();
        ��ɫID = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        ת���ɫ���˺� = new javax.swing.JTextField();
        ��ʼת�� = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        ��ʾ��ɫ2 = new javax.swing.JTable();
        jLabel317 = new javax.swing.JLabel();
        jLabel318 = new javax.swing.JLabel();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ʾ��Ϸ�˺�.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ��ʾ��Ϸ�˺�.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "�˺�ID", "��Ϸ�˺�", "��QQ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(��ʾ��Ϸ�˺�);
        if (��ʾ��Ϸ�˺�.getColumnModel().getColumnCount() > 0) {
            ��ʾ��Ϸ�˺�.getColumnModel().getColumn(0).setResizable(false);
            ��ʾ��Ϸ�˺�.getColumnModel().getColumn(0).setPreferredWidth(100);
            ��ʾ��Ϸ�˺�.getColumnModel().getColumn(1).setResizable(false);
            ��ʾ��Ϸ�˺�.getColumnModel().getColumn(1).setPreferredWidth(200);
            ��ʾ��Ϸ�˺�.getColumnModel().getColumn(2).setResizable(false);
            ��ʾ��Ϸ�˺�.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 430, 540));

        ��ʾ���ɫ�˺�.setText("��ʾ���ɫ�˺�");
        ��ʾ���ɫ�˺�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ʾ���ɫ�˺�ActionPerformed(evt);
            }
        });
        jPanel1.add(��ʾ���ɫ�˺�, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, 360, 30));

        �˺�ID.setEditable(false);
        jPanel1.add(�˺�ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 60, 30));

        jLabel315.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel315.setText("ѡ����Ҫת�ƽ�ɫ���˺ţ�");
        jPanel1.add(jLabel315, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jTabbedPane1.addTab("��һ��", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ת����ɫ���˺�.setEditable(false);
        jPanel2.add(ת����ɫ���˺�, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 230, 30));

        jLabel314.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel314.setText("˫��ת�ƵĽ�ɫ��");
        jPanel2.add(jLabel314, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        ��ʾ��ɫ1.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        ��ʾ��ɫ1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "*", "��ɫ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(��ʾ��ɫ1);
        if (��ʾ��ɫ1.getColumnModel().getColumnCount() > 0) {
            ��ʾ��ɫ1.getColumnModel().getColumn(0).setResizable(false);
            ��ʾ��ɫ1.getColumnModel().getColumn(0).setPreferredWidth(20);
            ��ʾ��ɫ1.getColumnModel().getColumn(1).setResizable(false);
            ��ʾ��ɫ1.getColumnModel().getColumn(1).setPreferredWidth(150);
        }

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 410, 500));

        jLabel316.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel316.setText("ת����ɫ���˺ţ�");
        jPanel2.add(jLabel316, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        ��ɫID.setEditable(false);
        jPanel2.add(��ɫID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 60, 30));

        jTabbedPane1.addTab("�ڶ���", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(ת���ɫ���˺�, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 150, 30));

        ��ʼת��.setText("��ʼת��");
        ��ʼת��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ʼת��ActionPerformed(evt);
            }
        });
        jPanel3.add(��ʼת��, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 100, 30));

        ��ʾ��ɫ2.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ��ʾ��ɫ2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "*", "��ɫ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(��ʾ��ɫ2);
        if (��ʾ��ɫ2.getColumnModel().getColumnCount() > 0) {
            ��ʾ��ɫ2.getColumnModel().getColumn(0).setResizable(false);
            ��ʾ��ɫ2.getColumnModel().getColumn(0).setPreferredWidth(20);
            ��ʾ��ɫ2.getColumnModel().getColumn(1).setResizable(false);
            ��ʾ��ɫ2.getColumnModel().getColumn(1).setPreferredWidth(150);
        }

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 420, 500));

        jLabel317.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel317.setText("��ʼת�ƺ�ˢ�¸��˺��µĽ�ɫ�б�");
        jPanel3.add(jLabel317, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel318.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel318.setText("��дת���ɫ���˺ţ�");
        jPanel3.add(jLabel318, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jTabbedPane1.addTab("������", jPanel3);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 710));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ��ʾ���ɫ�˺�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ʾ���ɫ�˺�ActionPerformed
        ˢ���˺���Ϣ();
    }//GEN-LAST:event_��ʾ���ɫ�˺�ActionPerformed

    private void ��ʼת��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ʼת��ActionPerformed
        if (�˺�ȡ�˺�ID(ת���ɫ���˺�.getText()) != 0) {
            PreparedStatement ps = null;
            PreparedStatement ps1 = null;
            ResultSet rs = null;
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update characters set accountid = " + �˺�ȡ�˺�ID(ת���ɫ���˺�.getText()) + " where id ='" + Integer.parseInt(this.��ɫID.getText()) + "';";
                    PreparedStatement priority = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    priority.executeUpdate(sqlString2);
                }
                JOptionPane.showMessageDialog(null, "ת�Ƴɹ���");
            } catch (SQLException ex) {
            }
            ˢ���˺Ž�ɫ1();
            ˢ���˺Ž�ɫ2();
        } else {
            JOptionPane.showMessageDialog(null, "û�в�ѯ�����˺ŵ�ID��");
        }

    }//GEN-LAST:event_��ʼת��ActionPerformed
    public static int �˺�ȡ�˺�ID(String id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id as DATA FROM accounts WHERE name = ?");
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�˺�ȡ�˺�ID������");
        }
        return data;
    }

    private void ˢ���˺���Ϣ() {
        for (int i = ((DefaultTableModel) (this.��ʾ��Ϸ�˺�.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ʾ��Ϸ�˺�.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM accounts ");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (���ɫ(rs.getInt("id")) > 1) {
                    ((DefaultTableModel) ��ʾ��Ϸ�˺�.getModel()).insertRow(��ʾ��Ϸ�˺�.getRowCount(), new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("qq")
                    });
                }
            }
        } catch (SQLException ex) {
        }
        ��ʾ��Ϸ�˺�.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ʾ��Ϸ�˺�.getSelectedRow();
                String a = ��ʾ��Ϸ�˺�.getValueAt(i, 0).toString();
                String a1 = ��ʾ��Ϸ�˺�.getValueAt(i, 1).toString();
                �˺�ID.setText(a);
                ת����ɫ���˺�.setText(a1);
                ˢ���˺Ž�ɫ1();
                JOptionPane.showMessageDialog(null, "ѡ�����˺ź������ڶ����ɡ�");
            }
        });
    }

    private void ˢ���˺Ž�ɫ1() {
        for (int i = ((DefaultTableModel) (this.��ʾ��ɫ1.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ʾ��ɫ1.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM characters WHERE accountid = " + �˺�ID.getText() + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ��ʾ��ɫ1.getModel()).insertRow(��ʾ��ɫ1.getRowCount(), new Object[]{
                    rs.getString("id"),
                    rs.getString("name")
                });
            }
        } catch (SQLException ex) {
        }
        ��ʾ��ɫ1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ʾ��ɫ1.getSelectedRow();
                String a = ��ʾ��ɫ1.getValueAt(i, 0).toString();
                ��ɫID.setText(a);
                JOptionPane.showMessageDialog(null, "ѡ���˽�ɫ�������������ɡ�");
            }
        });
    }

    private void ˢ���˺Ž�ɫ2() {
        for (int i = ((DefaultTableModel) (this.��ʾ��ɫ2.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ʾ��ɫ2.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM characters WHERE accountid = " + �˺�ȡ�˺�ID(ת���ɫ���˺�.getText()) + " ");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ��ʾ��ɫ2.getModel()).insertRow(��ʾ��ɫ2.getRowCount(), new Object[]{
                    rs.getString("id"),
                    rs.getString("name")
                });
            }
        } catch (SQLException ex) {
        }

    }

    public static int ���ɫ(int a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE accountid = " + a + "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data += 1;
            }
        } catch (SQLException ex) {
        }
        return data;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(����̨2��.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        ��ɫת�ƹ���.setDefaultLookAndFeelDecorated(true);
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
                new ��ɫת�ƹ���().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel314;
    private javax.swing.JLabel jLabel315;
    private javax.swing.JLabel jLabel316;
    private javax.swing.JLabel jLabel317;
    private javax.swing.JLabel jLabel318;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton ��ʼת��;
    private javax.swing.JButton ��ʾ���ɫ�˺�;
    private javax.swing.JTable ��ʾ��Ϸ�˺�;
    private javax.swing.JTable ��ʾ��ɫ1;
    private javax.swing.JTable ��ʾ��ɫ2;
    private javax.swing.JTextField ��ɫID;
    private javax.swing.JTextField �˺�ID;
    private javax.swing.JTextField ת���ɫ���˺�;
    private javax.swing.JTextField ת����ɫ���˺�;
    // End of variables declaration//GEN-END:variables
}
