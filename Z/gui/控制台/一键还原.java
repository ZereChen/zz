package gui.����̨;

import database.DatabaseConnection;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;

public class һ����ԭ extends javax.swing.JFrame {

    public һ����ԭ() {

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        Image background = new ImageIcon("gui/1.png").getImage();
        setIconImage(icon.getImage());
        initComponents();
        ������֤��();

    }

    private void ������֤��() {
        String ������֤�� = "1234567890aAbBcCdDeEfFgGhHiIjJkKlLmMNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
        char ����1 = ������֤��.charAt((int) (Math.random() * 62));
        char ����2 = ������֤��.charAt((int) (Math.random() * 62));
        char ����3 = ������֤��.charAt((int) (Math.random() * 62));
        char ����4 = ������֤��.charAt((int) (Math.random() * 62));
        String �����֤�� = "" + ����1 + "" + ����2 + "" + ����3 + "" + ����4 + "";
        ��֤��(�����֤��);
    }

    public void Z(int i) {
        ������1.setValue(i);
    }

    private void ��֤��(String str) {
        ��֤��.setText(str);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ������1 = new javax.swing.JProgressBar();
        ������������ = new javax.swing.JButton();
        ��ԭ = new javax.swing.JButton();
        ��֤�� = new javax.swing.JLabel();
        ������֤�� = new javax.swing.JTextField();
        ��֤��1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        �����ȯ������ = new javax.swing.JButton();
        �����������ֿ� = new javax.swing.JButton();
        �����������ֿ� = new javax.swing.JButton();
        ����̳�������Ʒ = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(������1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 40));

        ������������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������������.setText("������������");
        ������������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������������ActionPerformed(evt);
            }
        });
        jPanel1.add(������������, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 160, 30));

        ��ԭ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ԭ.setForeground(new java.awt.Color(255, 51, 102));
        ��ԭ.setText("һ����ԭ��Ϸ����");
        ��ԭ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ԭActionPerformed(evt);
            }
        });
        jPanel1.add(��ԭ, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 160, 30));

        ��֤��.setText("XXXX");
        jPanel1.add(��֤��, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 60, 40));
        jPanel1.add(������֤��, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 90, -1));

        ��֤��1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��֤��1.setText("��֤��;");
        jPanel1.add(��֤��1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 60, 40));

        jButton1.setText("ˢ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 60, 25));

        �����ȯ������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �����ȯ������.setText("�����ȯ������");
        �����ȯ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����ȯ������ActionPerformed(evt);
            }
        });
        jPanel1.add(�����ȯ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 160, 30));

        �����������ֿ�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �����������ֿ�.setText("�����������ֿ�");
        �����������ֿ�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����������ֿ�ActionPerformed(evt);
            }
        });
        jPanel1.add(�����������ֿ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 160, 30));

        �����������ֿ�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �����������ֿ�.setText("�����������ֿ�");
        �����������ֿ�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����������ֿ�ActionPerformed(evt);
            }
        });
        jPanel1.add(�����������ֿ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 160, 30));

        ����̳�������Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����̳�������Ʒ.setText("����̳�������Ʒ");
        ����̳�������Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����̳�������ƷActionPerformed(evt);
            }
        });
        jPanel1.add(����̳�������Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 160, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 540));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ��ԭActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ԭActionPerformed

        if (������֤��.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "��������֤�룡");
            return;
        }

        if (!������֤��.getText().equals(��֤��.getText())) {
            JOptionPane.showMessageDialog(null, "��֤�����");
            ������֤��();
            return;
        }
        Delete("accounts", 1);
        Delete("auctionitems", 2);
        Delete("auctionitems1", 3);
        Delete("auctionpoint", 4);
        Delete("auctionpoint1", 5);
        Delete("bank_item", 6);
        Delete("bank_item1", 7);
        Delete("bank_item2", 8);
        Delete("blocklogin", 9);
        Delete("bosslog", 10);
        Delete("bossrank", 11);
        Delete("bossrank1", 12);
        Delete("bossrank2", 13);
        Delete("bossrank3", 14);
        Delete("bossrank4", 15);
        Delete("bossrank5", 16);
        Delete("bossrank6", 17);
        Delete("bossrank7", 18);
        Delete("bossrank8", 19);
        Delete("bossrank9", 20);
        Delete("buddies", 21);
        Delete("capture_cs", 22);
        Delete("cashshop_limit_sell", 23);
        Delete("character7", 24);
        Delete("charactera", 25);
        Delete("characters", 26);
        Delete("characterz", 27);
        Delete("cheatlog", 28);
        Delete("csequipment", 29);
        Delete("csitems", 30);
        Delete("divine", 31);
        Delete("dueyequipment", 32);
        Delete("dueyitems", 33);
        Delete("dueypackages", 34);
        Delete("eventstats", 35);
        Delete("famelog", 36);
        Delete("families", 37);
        Delete("fengye", 38);
        Delete("forum_reply", 39);
        Delete("forum_section", 40);
        Delete("forum_thread", 41);
        Delete("gmlog", 42);
        Delete("guilds", 43);
        Delete("hiredmerchequipment", 44);
        Delete("hiredmerchitems", 45);
        Delete("hiredmerch", 46);
        Delete("inventoryequipment", 47);
        Delete("inventoryitems", 48);
        Delete("inventorylog", 49);
        Delete("inventoryslot", 50);
        Delete("ipbans", 51);
        Delete("macbans", 52);
        Delete("mapidban", 53);
        Delete("monsterbook", 54);
        Delete("mountdata", 55);
        Delete("mts_cart", 56);
        Delete("mts_items", 57);
        Delete("mtsequipment", 58);
        Delete("mtsitems", 59);
        Delete("mtstransfer", 60);
        Delete("mtstransferequipment", 61);
        Delete("mulungdojo", 62);
        Delete("notes", 63);
        Delete("nxcode", 64);
        Delete("pets", 65);
        Delete("pnpc", 66);
        Delete("qqlog", 67);
        Delete("qqstem", 68);
        Delete("questactions", 69);
        Delete("questinfo", 70);
        Delete("queststatusmobs", 71);
        Delete("regrocklocations", 72);
        Delete("saiji", 73);
        Delete("skillmacros", 74);
        Delete("skills", 75);
        Delete("skills_cooldowns", 76);
        Delete("speedruns", 77);
        Delete("storages", 78);
        Delete("bossrank8", 79);
        Delete("bossrank8", 80);
        Delete("bossrank8", 81);
        Delete("awarp", 82);
        Delete("bank", 83);
        Delete("mail", 84);
        Delete("jiezoudashi", 85);
        Delete("shouce", 100);

        /*Z(-100);
        �����Ӷ���();
        ����˺�();
        ��ռ����();
        ��ս�ɫ��();
        ��պ������ݿ�();
        ���ÿ���б�();
        �������ֿ�();
        �������1();
        �������2();
        ������籬��();
        �����̳1();
        �����̳2();
        �����̳3();
        �������1();
        �������2();
        ���A();
        ���B();
        ���C();
        ���D();
        �������1();
        �������2();
        ��ռ���1();
        ��ռ���2();
        ����̳�();
        ��ռ�¼��ɫ����();
        ��չ�Ӷ1();
        ��չ�Ӷ2();
        ��չ�Ӷ3();
        ���B1();
        ���B2();
        ���B3();
        ���B4();
        ���B5();
        ���B6();
        ���B7();
        ���B8();
        //�����ֳ();
        ��ո�������();
        ���qqgame();
        ���qqlog();*/
        JOptionPane.showMessageDialog(null, "�������");
        System.exit(0);
    }//GEN-LAST:event_��ԭActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ������֤��();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Delete(String a, int b) {
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from " + a + "");
            ps.executeUpdate();
            ps.close();
            Z(b);
        } catch (SQLException e) {
            System.out.println("Error/" + a + ":" + e);
        }
    }

    private void ��ո�������() {
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from characterz");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void ���qqgame() {
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from qqstem");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void ���qqlog() {
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from qqlog");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void �����ֳ() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from character7");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ���B1() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from bossrank1");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ���B2() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from bossrank2");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ���B3() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from bossrank3");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ���B4() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from bossrank4");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ���B5() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from bossrank5");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ���B6() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from bossrank6");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ���B7() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from bossrank7");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ���B8() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from bossrank8");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }
    private void ������������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������������ActionPerformed
        �������b1();
        �������b2();
        JOptionPane.showMessageDialog(null, "����������������");
    }//GEN-LAST:event_������������ActionPerformed
    private void �������b1() {
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from auctionitems1");
            ps.executeUpdate();
            ps.close();
            Z(50);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void �������b2() {
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from auctionpoint1");
            ps.executeUpdate();
            ps.close();
            Z(100);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }
    private void �����ȯ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����ȯ������ActionPerformed
        �������a1();
        �������a2();
        JOptionPane.showMessageDialog(null, "��ȯ�������������");
    }//GEN-LAST:event_�����ȯ������ActionPerformed

    private void �����������ֿ�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����������ֿ�ActionPerformed
        ��ո�������ֿ�();
        JOptionPane.showMessageDialog(null, "��������ֿ��������");
    }//GEN-LAST:event_�����������ֿ�ActionPerformed

    private void �����������ֿ�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����������ֿ�ActionPerformed
        ��ռ�������ֿ�();
        JOptionPane.showMessageDialog(null, "��������ֿ��������");
    }//GEN-LAST:event_�����������ֿ�ActionPerformed

    private void ����̳�������ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����̳�������ƷActionPerformed
        ����̳�������Ʒ();
        ���SN("sn1000", 10000000);
        Z(10);
        ���SN("sn1009", 10089999);
        Z(20);
        ���SN("sn1010", 10100000);
        Z(30);
        ���SN("sn1019", 10189999);
        Z(40);
        ���SN("sn1020", 10200000);
        Z(50);
        ���SN("sn1029", 10289999);
        Z(60);
        ���SN("sn2000", 20000000);
        Z(100);
        JOptionPane.showMessageDialog(null, "�̳�������Ʒ�������");
    }//GEN-LAST:event_����̳�������ƷActionPerformed

    private void ���SN(String a, int b) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM " + a + " WHERE SN > " + b + "");
            ps1.setInt(1, b);
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlstr = " delete from " + a + " where SN >" + b + "";
                ps1.executeUpdate(sqlstr);
            }
        } catch (SQLException ex) {
        }
    }

    private void ����̳�������Ʒ() {
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from cashshop_modified_items");
            ps.executeUpdate();
            ps.close();
            Z(100);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void ��ո�������ֿ�() {
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from bank_item");
            ps.executeUpdate();
            ps.close();
            Z(100);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void ��ռ�������ֿ�() {
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from bank_item1");
            ps.executeUpdate();
            ps.close();
            Z(100);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void �������a1() {
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from auctionitems");
            ps.executeUpdate();
            ps.close();
            Z(50);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void �������a2() {
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from auctionpoint");
            ps.executeUpdate();
            ps.close();
            Z(100);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void �����Ӷ���() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from hiredmerch");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ��ռ�¼��ɫ����() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from z��ɫͳ��");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ����̳�() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from csitems");
            ps.executeUpdate();
            ps.close();
            Z(56);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ��ռ���2() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from skills");
            ps.executeUpdate();
            ps.close();
            Z(54);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ��ռ���1() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from skillmacros");
            ps.executeUpdate();
            ps.close();
            Z(52);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void �������2() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from queststatusmobs");
            ps.executeUpdate();
            ps.close();
            Z(50);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void �������1() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from queststatus");
            ps.executeUpdate();
            ps.close();
            Z(48);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ���D() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from mountdata");
            ps.executeUpdate();
            ps.close();
            Z(46);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ���C() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from keymap");
            ps.executeUpdate();
            ps.close();
            Z(44);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ���B() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from inventoryslot");
            ps.executeUpdate();
            ps.close();
            Z(42);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void �����̳1() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from forum_thread");
            ps.executeUpdate();
            ps.close();
            Z(30);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ���A() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from hypay");
            ps.executeUpdate();
            ps.close();
            Z(40);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void �������2() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from inventoryitems");
            ps.executeUpdate();
            ps.close();
            Z(38);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void �������1() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from inventoryequipment");
            ps.executeUpdate();
            ps.close();
            Z(36);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void �����̳2() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from forum_section");
            ps.executeUpdate();
            ps.close();
            Z(32);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void �����̳3() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from forum_reply");
            ps.executeUpdate();
            ps.close();
            Z(34);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ������籬��() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from drop_data_global");
            ps.executeUpdate();
            ps.close();
            Z(28);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ��ռ����() {
        Z(6);
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from guilds");
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
        Z(8);
    }

    private void ��ս�ɫ��() {
        Z(10);
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from characters");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
        Z(12);
    }

    private void ��պ������ݿ�() {
        Z(14);
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from bossrank");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
        Z(16);
    }

    private void ���ÿ���б�() {
        Z(18);
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from bosslog");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
        Z(20);
    }

    private void �������ֿ�() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from bank_item");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
        Z(22);
    }

    private void �������1() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from auctionitems");
            ps.executeUpdate();
            ps.close();
            Z(24);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void �������2() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from auctionpoint");
            ps.executeUpdate();
            ps.close();
            Z(26);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void ����˺�() {
        Z(2);
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from accounts");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
        Z(4);
    }

    private void ��չ�Ӷ1() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from hiredmerch");
            ps.executeUpdate();
            ps.close();
            Z(58);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void ��չ�Ӷ2() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from hiredmerchequipment");
            ps.executeUpdate();
            ps.close();
            Z(60);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void ��չ�Ӷ3() {

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from hiredmerchitems");
            ps.executeUpdate();
            ps.close();
            Z(62);
        } catch (SQLException e) {
            System.out.println("Error " + e);
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
            java.util.logging.Logger.getLogger(һ����ԭ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(һ����ԭ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(һ����ԭ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(һ����ԭ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        һ����ԭ.setDefaultLookAndFeelDecorated(true);
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
                new һ����ԭ().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton �����������ֿ�;
    private javax.swing.JButton ����̳�������Ʒ;
    private javax.swing.JButton �����������ֿ�;
    private javax.swing.JButton �����ȯ������;
    private javax.swing.JButton ������������;
    private javax.swing.JTextField ������֤��;
    private javax.swing.JButton ��ԭ;
    private javax.swing.JProgressBar ������1;
    private javax.swing.JLabel ��֤��;
    private javax.swing.JLabel ��֤��1;
    // End of variables declaration//GEN-END:variables

}
