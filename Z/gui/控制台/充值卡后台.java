package gui.����̨;

import static abc.Game.�汾;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import database.DatabaseConnection;
import gui.ZEVMS;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import tools.FileoutputUtil;
import static tools.FileoutputUtil.CurrentReadable_Date;

/**
 *
 * @author Administrator
 */
public class ��ֵ����̨ extends javax.swing.JFrame {

    /**
     * Creates new form ��ֵ����̨
     */
    public ��ֵ����̨() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        setIconImage(icon.getImage());
        Properties �O���n = System.getProperties();
        setTitle("ZEV.Maplestory(079)v." + �汾 + " www.zevnxd.com   ����ֵ/�һ�������̨���ɹرա�");
        initComponents();
        ˢ�³�ֵ����Ϣ();
        JOptionPane.showMessageDialog(null, ""
                + "�ر�������ʾ��\r\n"
                + "1.ÿ�ξ�����Ҫ����̫���ֵ��\r\n"
                + "2.���ɳ�ֵ������ϼܵ�����ƽ̨\r\n"
                + "3.�뼰ʱɾ����������֮ǰ�Ŀ���Ū��\r\n");
        String chars = "1234567890aAbBcCdDeEfFgGhHiIjJkKlLmMNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        ��ֵ�� = new javax.swing.JPanel();
        jScrollPane81 = new javax.swing.JScrollPane();
        ��ֵ����Ϣ = new javax.swing.JTable();
        ˢ�³�ֵ����Ϣ = new javax.swing.JButton();
        ˢ�³�ֵ����Ϣ1 = new javax.swing.JButton();
        ���ɵ�ȯ��ֵ��1 = new javax.swing.JButton();
        ��ȯ��ֵ����� = new javax.swing.JTextField();
        ���ɵ�ȯ��ֵ��2 = new javax.swing.JButton();
        jLabel221 = new javax.swing.JLabel();
        ���ɵ���ȯ��ֵ��1 = new javax.swing.JButton();
        ����ȯ��ֵ����� = new javax.swing.JTextField();
        jLabel222 = new javax.swing.JLabel();
        ���ɵ���ȯ��ֵ��2 = new javax.swing.JButton();
        jLabel223 = new javax.swing.JLabel();
        ������ = new javax.swing.JTextField();
        �������1 = new javax.swing.JButton();
        �������10 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ��ֵ����Ϣ��� = new javax.swing.JTextArea();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N

        ��ֵ��.setBackground(new java.awt.Color(255, 255, 255));
        ��ֵ��.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ��ֵ��.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ֵ����Ϣ.setBorder(new javax.swing.border.MatteBorder(null));
        ��ֵ����Ϣ.setFont(new java.awt.Font("����", 0, 18)); // NOI18N
        ��ֵ����Ϣ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "����", "����", "����", "���"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��ֵ����Ϣ.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane81.setViewportView(��ֵ����Ϣ);
        if (��ֵ����Ϣ.getColumnModel().getColumnCount() > 0) {
            ��ֵ����Ϣ.getColumnModel().getColumn(0).setResizable(false);
            ��ֵ����Ϣ.getColumnModel().getColumn(0).setPreferredWidth(250);
            ��ֵ����Ϣ.getColumnModel().getColumn(1).setResizable(false);
            ��ֵ����Ϣ.getColumnModel().getColumn(1).setPreferredWidth(50);
            ��ֵ����Ϣ.getColumnModel().getColumn(2).setResizable(false);
            ��ֵ����Ϣ.getColumnModel().getColumn(2).setPreferredWidth(50);
            ��ֵ����Ϣ.getColumnModel().getColumn(3).setResizable(false);
        }

        ��ֵ��.add(jScrollPane81, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 630, 510));

        ˢ�³�ֵ����Ϣ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ�³�ֵ����Ϣ.setText("ˢ�³�ֵ����Ϣ");
        ˢ�³�ֵ����Ϣ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�³�ֵ����ϢActionPerformed(evt);
            }
        });
        ��ֵ��.add(ˢ�³�ֵ����Ϣ, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 490, 200, -1));

        ˢ�³�ֵ����Ϣ1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ�³�ֵ����Ϣ1.setText("�򿪳�ֵ������ļ���");
        ˢ�³�ֵ����Ϣ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�³�ֵ����Ϣ1ActionPerformed(evt);
            }
        });
        ��ֵ��.add(ˢ�³�ֵ����Ϣ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 490, 210, -1));

        ���ɵ�ȯ��ֵ��1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���ɵ�ȯ��ֵ��1.setText("����1��");
        ���ɵ�ȯ��ֵ��1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ɵ�ȯ��ֵ��1ActionPerformed(evt);
            }
        });
        ��ֵ��.add(���ɵ�ȯ��ֵ��1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 130, 30));

        ��ȯ��ֵ�����.setMaximumSize(new java.awt.Dimension(137, 27));
        ��ȯ��ֵ�����.setMinimumSize(new java.awt.Dimension(137, 27));
        ��ֵ��.add(��ȯ��ֵ�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, 130, 30));

        ���ɵ�ȯ��ֵ��2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���ɵ�ȯ��ֵ��2.setText("����10��");
        ���ɵ�ȯ��ֵ��2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ɵ�ȯ��ֵ��2ActionPerformed(evt);
            }
        });
        ��ֵ��.add(���ɵ�ȯ��ֵ��2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 40, 130, 30));

        jLabel221.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel221.setText("��ȯ��ֵ����");
        ��ֵ��.add(jLabel221, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, -1, 20));

        ���ɵ���ȯ��ֵ��1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���ɵ���ȯ��ֵ��1.setText("����1��");
        ���ɵ���ȯ��ֵ��1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ɵ���ȯ��ֵ��1ActionPerformed(evt);
            }
        });
        ��ֵ��.add(���ɵ���ȯ��ֵ��1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 100, 130, 30));

        ����ȯ��ֵ�����.setMaximumSize(new java.awt.Dimension(137, 27));
        ����ȯ��ֵ�����.setMinimumSize(new java.awt.Dimension(137, 27));
        ��ֵ��.add(����ȯ��ֵ�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, 130, 30));

        jLabel222.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel222.setText("���ó�ֵ����");
        ��ֵ��.add(jLabel222, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, -1, 20));

        ���ɵ���ȯ��ֵ��2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���ɵ���ȯ��ֵ��2.setText("����10��");
        ���ɵ���ȯ��ֵ��2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ɵ���ȯ��ֵ��2ActionPerformed(evt);
            }
        });
        ��ֵ��.add(���ɵ���ȯ��ֵ��2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 100, 130, 30));

        jLabel223.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel223.setText("���������");
        ��ֵ��.add(jLabel223, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 140, -1, 20));

        ������.setMaximumSize(new java.awt.Dimension(137, 27));
        ������.setMinimumSize(new java.awt.Dimension(137, 27));
        ��ֵ��.add(������, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 160, 130, 30));

        �������1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �������1.setText("����1��");
        �������1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �������1ActionPerformed(evt);
            }
        });
        ��ֵ��.add(�������1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 160, 130, 30));

        �������10.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �������10.setText("����10��");
        �������10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �������10ActionPerformed(evt);
            }
        });
        ��ֵ��.add(�������10, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 160, 130, 30));

        jTabbedPane1.addTab("��ֵCDK����", ��ֵ��);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1179, 560));

        ��ֵ����Ϣ���.setColumns(20);
        ��ֵ����Ϣ���.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        ��ֵ����Ϣ���.setRows(5);
        jScrollPane1.setViewportView(��ֵ����Ϣ���);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 1180, 200));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ���ɵ�ȯ��ֵ��2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ɵ�ȯ��ֵ��2ActionPerformed
        boolean result1 = this.��ȯ��ֵ�����.getText().matches("[0-9]+");
        if (��ȯ��ֵ�����.getText().equals("") && !result1) {
            return;
        }
        �����Զ����ֵ��();
        �����Զ����ֵ��();
        �����Զ����ֵ��();
        �����Զ����ֵ��();
        �����Զ����ֵ��();
        �����Զ����ֵ��();
        �����Զ����ֵ��();
        �����Զ����ֵ��();
        �����Զ����ֵ��();
        �����Զ����ֵ��();// TODO add your handling code here:
    }//GEN-LAST:event_���ɵ�ȯ��ֵ��2ActionPerformed

    private void ���ɵ�ȯ��ֵ��1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ɵ�ȯ��ֵ��1ActionPerformed

        boolean result1 = this.��ȯ��ֵ�����.getText().matches("[0-9]+");
        if (��ȯ��ֵ�����.getText().equals("") && !result1) {
            return;
        }
        �����Զ����ֵ��();
    }//GEN-LAST:event_���ɵ�ȯ��ֵ��1ActionPerformed

    private void ˢ�³�ֵ����Ϣ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�³�ֵ����Ϣ1ActionPerformed
        �򿪳�ֵ������ļ���();// TODO add your handling code here:
    }//GEN-LAST:event_ˢ�³�ֵ����Ϣ1ActionPerformed

    private void ˢ�³�ֵ����ϢActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�³�ֵ����ϢActionPerformed
        ˢ�³�ֵ����Ϣ();
    }//GEN-LAST:event_ˢ�³�ֵ����ϢActionPerformed

    private void ���ɵ���ȯ��ֵ��1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ɵ���ȯ��ֵ��1ActionPerformed
        boolean result1 = this.����ȯ��ֵ�����.getText().matches("[0-9]+");
        if (����ȯ��ֵ�����.getText().equals("") && !result1) {
            return;
        }
        �����Զ����ֵ��2();
    }//GEN-LAST:event_���ɵ���ȯ��ֵ��1ActionPerformed
    public void �����Զ����ֵ��2() {
        int ��� = Integer.parseInt(����ȯ��ֵ�����.getText());
        String ��� = "";
        String chars = "1234567890aAbBcCdDeEfFgGhHiIjJkKlLmMNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
        char ����1 = chars.charAt((int) (Math.random() * 62));
        char ����2 = chars.charAt((int) (Math.random() * 62));
        char ����3 = chars.charAt((int) (Math.random() * 62));
        char ����4 = chars.charAt((int) (Math.random() * 62));
        char ����5 = chars.charAt((int) (Math.random() * 62));
        char ����6 = chars.charAt((int) (Math.random() * 62));
        char ����7 = chars.charAt((int) (Math.random() * 62));
        char ����8 = chars.charAt((int) (Math.random() * 62));
        char ����9 = chars.charAt((int) (Math.random() * 62));
        char ����10 = chars.charAt((int) (Math.random() * 62));
        char ����11 = chars.charAt((int) (Math.random() * 62));
        char ����12 = chars.charAt((int) (Math.random() * 62));
        char ����13 = chars.charAt((int) (Math.random() * 62));
        char ����14 = chars.charAt((int) (Math.random() * 62));
        char ����15 = chars.charAt((int) (Math.random() * 62));
        char ����16 = chars.charAt((int) (Math.random() * 62));
        char ����17 = chars.charAt((int) (Math.random() * 62));
        char ����18 = chars.charAt((int) (Math.random() * 62));
        char ����19 = chars.charAt((int) (Math.random() * 62));
        char ����20 = chars.charAt((int) (Math.random() * 62));
        char ����21 = chars.charAt((int) (Math.random() * 62));
        char ����22 = chars.charAt((int) (Math.random() * 62));
        char ����23 = chars.charAt((int) (Math.random() * 62));
        char ����24 = chars.charAt((int) (Math.random() * 62));
        char ����25 = chars.charAt((int) (Math.random() * 62));
        char ����26 = chars.charAt((int) (Math.random() * 62));
        char ����27 = chars.charAt((int) (Math.random() * 62));
        char ����28 = chars.charAt((int) (Math.random() * 62));
        char ����29 = chars.charAt((int) (Math.random() * 62));
        char ����30 = chars.charAt((int) (Math.random() * 62));

        String ��ֵ�� = "DY" + ����1 + "" + ����2 + "" + ����3 + "" + ����4 + "" + ����5 + "" + ����6 + "" + ����7 + "" + ����8 + "" + ����9 + "" + ����10 + "" + ����11 + "" + ����12 + "" + ����13 + "" + ����14 + "" + ����15 + "" + ����16 + "" + ����17 + "" + ����18 + "" + ����19 + "" + ����20 + "" + ����21 + "" + ����22 + "" + ����23 + "" + ����24 + "" + ����25 + "" + ����26 + "" + ����27 + "" + ����28 + "" + ����29 + "" + ����30 + "";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO nxcodez ( code,leixing,valid) VALUES ( ?, ?, ?)")) {
            ps.setString(1, ��ֵ��);
            ps.setInt(2, 2);
            ps.setInt(3, ���);
            ps.executeUpdate();
            FileoutputUtil.logToFile("��ֵ����̨���/[" + CurrentReadable_Date() + "]" + ��� + "�����ȯֵ��.txt", "" + ��ֵ�� + "\r\n");
            ˢ�³�ֵ����Ϣ();
            ��� = "" + CurrentReadable_Date() + "/���ɶһ����ɹ�������Ϊ " + ��� + " ����ȯ���Ѿ���ŷ���˸�Ŀ¼��";
        } catch (SQLException ex) {
            Logger.getLogger(��ֵ����̨.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ֵ����Ϣ���(���);
    }
    private void ���ɵ���ȯ��ֵ��2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ɵ���ȯ��ֵ��2ActionPerformed
        boolean result1 = this.����ȯ��ֵ�����.getText().matches("[0-9]+");
        if (����ȯ��ֵ�����.getText().equals("") && !result1) {
            return;
        }
        �����Զ����ֵ��2();
        �����Զ����ֵ��2();
        �����Զ����ֵ��2();
        �����Զ����ֵ��2();
        �����Զ����ֵ��2();
        �����Զ����ֵ��2();
        �����Զ����ֵ��2();
        �����Զ����ֵ��2();
        �����Զ����ֵ��2();
        �����Զ����ֵ��2();
    }//GEN-LAST:event_���ɵ���ȯ��ֵ��2ActionPerformed

    private void �������1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�������1ActionPerformed
        boolean result1 = this.������.getText().matches("[0-9]+");
        if (������.getText().equals("") && !result1) {
            return;
        }
        �������();
    }//GEN-LAST:event_�������1ActionPerformed

    private void �������10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�������10ActionPerformed
        boolean result1 = this.������.getText().matches("[0-9]+");
        if (������.getText().equals("") && !result1) {
            return;
        }
        �������();
        �������();
        �������();
        �������();
        �������();
        �������();
        �������();
        �������();
        �������();
        �������();
    }//GEN-LAST:event_�������10ActionPerformed
    public void �������() {
        int ��� = Integer.parseInt(������.getText());
        String ��� = "";
        String chars = "1234567890aAbBcCdDeEfFgGhHiIjJkKlLmMNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
        char ����1 = chars.charAt((int) (Math.random() * 62));
        char ����2 = chars.charAt((int) (Math.random() * 62));
        char ����3 = chars.charAt((int) (Math.random() * 62));
        char ����4 = chars.charAt((int) (Math.random() * 62));
        char ����5 = chars.charAt((int) (Math.random() * 62));
        char ����6 = chars.charAt((int) (Math.random() * 62));
        char ����7 = chars.charAt((int) (Math.random() * 62));
        char ����8 = chars.charAt((int) (Math.random() * 62));
        char ����9 = chars.charAt((int) (Math.random() * 62));
        char ����10 = chars.charAt((int) (Math.random() * 62));
        char ����11 = chars.charAt((int) (Math.random() * 62));
        char ����12 = chars.charAt((int) (Math.random() * 62));
        char ����13 = chars.charAt((int) (Math.random() * 62));
        char ����14 = chars.charAt((int) (Math.random() * 62));
        char ����15 = chars.charAt((int) (Math.random() * 62));
        char ����16 = chars.charAt((int) (Math.random() * 62));
        char ����17 = chars.charAt((int) (Math.random() * 62));
        char ����18 = chars.charAt((int) (Math.random() * 62));
        char ����19 = chars.charAt((int) (Math.random() * 62));
        char ����20 = chars.charAt((int) (Math.random() * 62));
        char ����21 = chars.charAt((int) (Math.random() * 62));
        char ����22 = chars.charAt((int) (Math.random() * 62));
        char ����23 = chars.charAt((int) (Math.random() * 62));
        char ����24 = chars.charAt((int) (Math.random() * 62));
        char ����25 = chars.charAt((int) (Math.random() * 62));
        char ����26 = chars.charAt((int) (Math.random() * 62));
        char ����27 = chars.charAt((int) (Math.random() * 62));
        char ����28 = chars.charAt((int) (Math.random() * 62));
        char ����29 = chars.charAt((int) (Math.random() * 62));
        char ����30 = chars.charAt((int) (Math.random() * 62));

        String ��ֵ�� = "LB" + ����1 + "" + ����2 + "" + ����3 + "" + ����4 + "" + ����5 + "" + ����6 + "" + ����7 + "" + ����8 + "" + ����9 + "" + ����10 + "" + ����11 + "" + ����12 + "" + ����13 + "" + ����14 + "" + ����15 + "" + ����16 + "" + ����17 + "" + ����18 + "" + ����19 + "" + ����20 + "" + ����21 + "" + ����22 + "" + ����23 + "" + ����24 + "" + ����25 + "" + ����26 + "" + ����27 + "" + ����28 + "" + ����29 + "" + ����30 + "";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO nxcodez ( code,leixing,valid,itme) VALUES ( ?, ?, ?,?)")) {
            ps.setString(1, ��ֵ��);
            ps.setInt(2, 5);
            ps.setInt(3, 0);
            ps.setInt(4, ���);
            ps.executeUpdate();
            FileoutputUtil.logToFile("��ֵ����̨���/[" + CurrentReadable_Date() + "]" + ��� + "����һ���.txt", "" + ��ֵ�� + "\r\n");
            ˢ�³�ֵ����Ϣ();
            ��� = "" + CurrentReadable_Date() + "/���ɶһ����ɹ������Ϊ " + ��� + " �ţ��Ѿ���ŷ���˸�Ŀ¼��";
        } catch (SQLException ex) {
            Logger.getLogger(��ֵ����̨.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ֵ����Ϣ���(���);
    }

    public void �����Զ����ֵ��() {
        int ��� = Integer.parseInt(��ȯ��ֵ�����.getText());
        String ��� = "";
        String chars = "1234567890aAbBcCdDeEfFgGhHiIjJkKlLmMNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
        char ����1 = chars.charAt((int) (Math.random() * 62));
        char ����2 = chars.charAt((int) (Math.random() * 62));
        char ����3 = chars.charAt((int) (Math.random() * 62));
        char ����4 = chars.charAt((int) (Math.random() * 62));
        char ����5 = chars.charAt((int) (Math.random() * 62));
        char ����6 = chars.charAt((int) (Math.random() * 62));
        char ����7 = chars.charAt((int) (Math.random() * 62));
        char ����8 = chars.charAt((int) (Math.random() * 62));
        char ����9 = chars.charAt((int) (Math.random() * 62));
        char ����10 = chars.charAt((int) (Math.random() * 62));
        char ����11 = chars.charAt((int) (Math.random() * 62));
        char ����12 = chars.charAt((int) (Math.random() * 62));
        char ����13 = chars.charAt((int) (Math.random() * 62));
        char ����14 = chars.charAt((int) (Math.random() * 62));
        char ����15 = chars.charAt((int) (Math.random() * 62));
        char ����16 = chars.charAt((int) (Math.random() * 62));
        char ����17 = chars.charAt((int) (Math.random() * 62));
        char ����18 = chars.charAt((int) (Math.random() * 62));
        char ����19 = chars.charAt((int) (Math.random() * 62));
        char ����20 = chars.charAt((int) (Math.random() * 62));
        char ����21 = chars.charAt((int) (Math.random() * 62));
        char ����22 = chars.charAt((int) (Math.random() * 62));
        char ����23 = chars.charAt((int) (Math.random() * 62));
        char ����24 = chars.charAt((int) (Math.random() * 62));
        char ����25 = chars.charAt((int) (Math.random() * 62));
        char ����26 = chars.charAt((int) (Math.random() * 62));
        char ����27 = chars.charAt((int) (Math.random() * 62));
        char ����28 = chars.charAt((int) (Math.random() * 62));
        char ����29 = chars.charAt((int) (Math.random() * 62));
        char ����30 = chars.charAt((int) (Math.random() * 62));

        String ��ֵ�� = "DQ" + ����1 + "" + ����2 + "" + ����3 + "" + ����4 + "" + ����5 + "" + ����6 + "" + ����7 + "" + ����8 + "" + ����9 + "" + ����10 + "" + ����11 + "" + ����12 + "" + ����13 + "" + ����14 + "" + ����15 + "" + ����16 + "" + ����17 + "" + ����18 + "" + ����19 + "" + ����20 + "" + ����21 + "" + ����22 + "" + ����23 + "" + ����24 + "" + ����25 + "" + ����26 + "" + ����27 + "" + ����28 + "" + ����29 + "" + ����30 + "";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO nxcodez ( code,leixing,valid) VALUES ( ?, ?, ?)")) {
            ps.setString(1, ��ֵ��);
            ps.setInt(2, 1);
            ps.setInt(3, ���);
            ps.executeUpdate();
            FileoutputUtil.logToFile("��ֵ����̨���/[" + CurrentReadable_Date() + "]" + ��� + "��ȯ��ֵ��.txt", "" + ��ֵ�� + "\r\n");
            ˢ�³�ֵ����Ϣ();
            ��� = "" + CurrentReadable_Date() + "/���ɶһ����ɹ�������Ϊ " + ��� + " ��ȯ���Ѿ���ŷ���˸�Ŀ¼��";
        } catch (SQLException ex) {
            Logger.getLogger(��ֵ����̨.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ֵ����Ϣ���(���);
    }

    private void ��ֵ����Ϣ���(String str) {
        ��ֵ����Ϣ���.setText(��ֵ����Ϣ���.getText() + str + "\r\n");
    }

    private static void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            System.out.println("Successfully deleted empty directory: " + dir);
        } else {
            System.out.println("Failed to delete empty directory: " + dir);
        }

    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//��ɾ���ļ���������ļ�  
                flag = true;
            }
        }
        return flag;
    }

    public static void �򿪳�ֵ������ļ���() {
        final Runtime runtime = Runtime.getRuntime();
        Process process = null;//  
        Properties �O���n = System.getProperties();
        final String cmd = "rundll32 url.dll FileProtocolHandler file:" + �O���n.getProperty("user.dir") + "\\��ֵ����̨���";
        try {
            process = runtime.exec(cmd);
        } catch (final Exception e) {
            System.out.println("Error exec!");
        }
    }

    public void ˢ�³�ֵ����Ϣ() {
        for (int i = ((DefaultTableModel) (this.��ֵ����Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ֵ����Ϣ.getModel())).removeRow(i);
        }
        PreparedStatement ps1 = null;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM nxcodez");
            rs = ps.executeQuery();
            while (rs.next()) {
                String ���� = "";
                switch (rs.getInt("leixing")) {
                    case 1:
                        ���� = "��ȯ";
                        break;
                    case 2:
                        ���� = "����ȯ";
                        break;
                    case 3:
                        ���� = "���";
                        break;
                    case 4:
                        ���� = "����";
                        break;
                    case 5:
                        ���� = "���";
                        break;
                    default:
                        break;
                }
                ((DefaultTableModel) ��ֵ����Ϣ.getModel()).insertRow(��ֵ����Ϣ.getRowCount(), new Object[]{
                    rs.getString("code"),
                    ����,
                    rs.getInt("valid"),
                    rs.getInt("itme")
                });
            }

        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(��ֵ����̨.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(��ֵ����̨.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(��ֵ����̨.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(��ֵ����̨.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        ��ֵ����̨.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
            // UIManager.setLookAndFeel(new SubstanceBusinessLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ��ֵ����̨().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane81;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel ��ֵ��;
    private javax.swing.JTable ��ֵ����Ϣ;
    private javax.swing.JTextArea ��ֵ����Ϣ���;
    private javax.swing.JButton ˢ�³�ֵ����Ϣ;
    private javax.swing.JButton ˢ�³�ֵ����Ϣ1;
    private javax.swing.JTextField ����ȯ��ֵ�����;
    private javax.swing.JTextField ��ȯ��ֵ�����;
    private javax.swing.JButton ���ɵ���ȯ��ֵ��1;
    private javax.swing.JButton ���ɵ���ȯ��ֵ��2;
    private javax.swing.JButton ���ɵ�ȯ��ֵ��1;
    private javax.swing.JButton ���ɵ�ȯ��ֵ��2;
    private javax.swing.JButton �������1;
    private javax.swing.JButton �������10;
    private javax.swing.JTextField ������;
    // End of variables declaration//GEN-END:variables

}
