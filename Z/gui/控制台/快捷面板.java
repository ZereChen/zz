package gui.����̨;

import database.DatabaseConnection;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.UIManager;
import gui.Start;
import static gui.Start.instance;
import gui.ZEVMS2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import server.MapleItemInformationProvider;

public class ������ extends javax.swing.JFrame {

    public ������() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        setIconImage(icon.getImage());
        setTitle("��Ϸ��ԴԤ��");
        initComponents();
        ˢ����Ʒ����();
    }

    private void ˢ����Ʒ����() {
        ��Ʒ����.setText("" + MapleItemInformationProvider.getInstance().getName(Integer.parseInt(��Ʒ����.getText())) + "");
        ���������.setText("" + ��ȡ��������("auctionitems1", "quantity", "itemid", Integer.parseInt(��Ʒ����.getText())) + "");
        ��ȯ������.setText("" + ��ȡ��������("auctionitems", "quantity", "itemid", Integer.parseInt(��Ʒ����.getText())) + "");
        ����.setText("" + ��ȡ��������("inventoryitems", "quantity", "itemid", Integer.parseInt(��Ʒ����.getText())) + "");
        ��������ֿ�.setText("" + ��ȡ��������("bank_item", "count", "itemid", Integer.parseInt(��Ʒ����.getText())) + "");
        ��������ֿ�.setText("" + ��ȡ��������("bank_item1", "count", "itemid", Integer.parseInt(��Ʒ����.getText())) + "");
        ��Ϸ�̳�.setText("" + ��ȡ��������("csitems", "quantity", "itemid", Integer.parseInt(��Ʒ����.getText())) + "");
    }

    public static final Start getInstance() {
        return instance;
    }

    public static int ��ȡ��������(String a, String b, String c, int d) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + a + " WHERE " + c + " = " + d + "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data += rs.getInt(b);
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        ��������� = new javax.swing.JTextField();
        jLabel212 = new javax.swing.JLabel();
        ���� = new javax.swing.JTextField();
        ��Ʒ���� = new javax.swing.JTextField();
        jLabel213 = new javax.swing.JLabel();
        ��Ʒ���� = new javax.swing.JTextField();
        jLabel214 = new javax.swing.JLabel();
        ��ȯ������ = new javax.swing.JTextField();
        jLabel215 = new javax.swing.JLabel();
        ��������ֿ� = new javax.swing.JTextField();
        jLabel216 = new javax.swing.JLabel();
        ��������ֿ� = new javax.swing.JTextField();
        jLabel217 = new javax.swing.JLabel();
        ��Ϸ�̳� = new javax.swing.JTextField();
        jLabel218 = new javax.swing.JLabel();
        ��ѯ = new javax.swing.JButton();
        jLabel219 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ���ӵ������ = new javax.swing.JTable();
        jLabel220 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ���ӵ������1 = new javax.swing.JTable();
        jLabel221 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel222 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        �����н�� = new javax.swing.JTable();
        jLabel223 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        �����н��1 = new javax.swing.JTable();
        jLabel224 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ����ֿ� = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel225 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        ����ֿ� = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ���������.setEditable(false);
        ���������.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ���������.setForeground(new java.awt.Color(51, 0, 255));
        jPanel3.add(���������, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 160, 30));

        jLabel212.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel212.setText("����/�ֿ⣻");
        jPanel3.add(jLabel212, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, 30));

        ����.setEditable(false);
        ����.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ����.setForeground(new java.awt.Color(51, 0, 255));
        jPanel3.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 160, 30));

        ��Ʒ����.setFont(new java.awt.Font("����", 0, 18)); // NOI18N
        ��Ʒ����.setText("4006000");
        jPanel3.add(��Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 160, 30));

        jLabel213.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel213.setText("��Ʒ���ƣ�");
        jPanel3.add(jLabel213, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 80, 30));

        ��Ʒ����.setEditable(false);
        ��Ʒ����.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ��Ʒ����.setForeground(new java.awt.Color(204, 0, 0));
        jPanel3.add(��Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 160, 30));

        jLabel214.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel214.setText("������(���)��");
        jPanel3.add(jLabel214, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 120, 30));

        ��ȯ������.setEditable(false);
        ��ȯ������.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ��ȯ������.setForeground(new java.awt.Color(51, 0, 255));
        jPanel3.add(��ȯ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 160, 30));

        jLabel215.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel215.setText("������(��ȯ)��");
        jPanel3.add(jLabel215, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 120, 30));

        ��������ֿ�.setEditable(false);
        ��������ֿ�.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ��������ֿ�.setForeground(new java.awt.Color(51, 0, 255));
        jPanel3.add(��������ֿ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 160, 30));

        jLabel216.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel216.setText("����ֿ�(����)��");
        jPanel3.add(jLabel216, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, 30));

        ��������ֿ�.setEditable(false);
        ��������ֿ�.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ��������ֿ�.setForeground(new java.awt.Color(51, 0, 255));
        jPanel3.add(��������ֿ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 160, 30));

        jLabel217.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel217.setText("����ֿ�(����)��");
        jPanel3.add(jLabel217, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, 30));

        ��Ϸ�̳�.setEditable(false);
        ��Ϸ�̳�.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ��Ϸ�̳�.setForeground(new java.awt.Color(51, 0, 255));
        jPanel3.add(��Ϸ�̳�, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 160, 30));

        jLabel218.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel218.setText("��Ϸ�̳ǣ�");
        jPanel3.add(jLabel218, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, 30));

        ��ѯ.setText("��ѯ");
        ��ѯ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ѯActionPerformed(evt);
            }
        });
        jPanel3.add(��ѯ, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, 90, 30));

        jLabel219.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel219.setText("��ɫ������");
        jPanel3.add(jLabel219, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 140, 30));

        ���ӵ������.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "���", "����", "QQ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(���ӵ������);
        if (���ӵ������.getColumnModel().getColumnCount() > 0) {
            ���ӵ������.getColumnModel().getColumn(0).setResizable(false);
            ���ӵ������.getColumnModel().getColumn(0).setPreferredWidth(100);
            ���ӵ������.getColumnModel().getColumn(1).setResizable(false);
            ���ӵ������.getColumnModel().getColumn(2).setResizable(false);
            ���ӵ������.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 370, 560));

        jLabel220.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel220.setText("��Ʒ���룻");
        jPanel3.add(jLabel220, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 80, 30));

        ���ӵ������1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "���", "����", "QQ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(���ӵ������1);
        if (���ӵ������1.getColumnModel().getColumnCount() > 0) {
            ���ӵ������1.getColumnModel().getColumn(0).setResizable(false);
            ���ӵ������1.getColumnModel().getColumn(0).setPreferredWidth(100);
            ���ӵ������1.getColumnModel().getColumn(1).setResizable(false);
            ���ӵ������1.getColumnModel().getColumn(2).setResizable(false);
            ���ӵ������1.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 50, 370, 560));

        jLabel221.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel221.setText("��ɫ�ֿ⣻");
        jPanel3.add(jLabel221, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 140, 30));

        jTabbedPane1.addTab("��ҳ", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel222.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel222.setText("��������У�");
        jPanel4.add(jLabel222, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, 30));

        �����н��.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "���", "����", "QQ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(�����н��);
        if (�����н��.getColumnModel().getColumnCount() > 0) {
            �����н��.getColumnModel().getColumn(0).setResizable(false);
            �����н��.getColumnModel().getColumn(0).setPreferredWidth(100);
            �����н��.getColumnModel().getColumn(1).setResizable(false);
            �����н��.getColumnModel().getColumn(2).setResizable(false);
            �����н��.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 370, 560));

        jLabel223.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel223.setText("��ȯ�����У�");
        jPanel4.add(jLabel223, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 140, 30));

        �����н��1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "���", "����", "QQ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(�����н��1);
        if (�����н��1.getColumnModel().getColumnCount() > 0) {
            �����н��1.getColumnModel().getColumn(0).setResizable(false);
            �����н��1.getColumnModel().getColumn(0).setPreferredWidth(100);
            �����н��1.getColumnModel().getColumn(1).setResizable(false);
            �����н��1.getColumnModel().getColumn(2).setResizable(false);
            �����н��1.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 370, 560));

        jLabel224.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel224.setText("����ֿ⣻");
        jPanel4.add(jLabel224, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 140, 30));

        ����ֿ�.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "���", "����", "QQ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(����ֿ�);
        if (����ֿ�.getColumnModel().getColumnCount() > 0) {
            ����ֿ�.getColumnModel().getColumn(0).setResizable(false);
            ����ֿ�.getColumnModel().getColumn(0).setPreferredWidth(100);
            ����ֿ�.getColumnModel().getColumn(1).setResizable(false);
            ����ֿ�.getColumnModel().getColumn(2).setResizable(false);
            ����ֿ�.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel4.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, 370, 560));

        jTabbedPane1.addTab("**2", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel225.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel225.setText("����ֿ⣻");
        jPanel5.add(jLabel225, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, 30));

        ����ֿ�.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "���", "����", "QQ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(����ֿ�);
        if (����ֿ�.getColumnModel().getColumnCount() > 0) {
            ����ֿ�.getColumnModel().getColumn(0).setResizable(false);
            ����ֿ�.getColumnModel().getColumn(0).setPreferredWidth(100);
            ����ֿ�.getColumnModel().getColumn(1).setResizable(false);
            ����ֿ�.getColumnModel().getColumn(2).setResizable(false);
            ����ֿ�.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel5.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 370, 560));

        jTabbedPane1.addTab("**3", jPanel5);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 650));

        jTabbedPane2.addTab("���ϲ�ѯ", jPanel1);
        jTabbedPane2.addTab("�Ʋ���ѯ", jPanel2);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 680));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ��ѯActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ѯActionPerformed
        boolean result1 = this.��Ʒ����.getText().matches("[0-9]+");
        if (result1) {
            ˢ����Ʒ����();
            ˢ����Ʒ��������();
            ˢ����Ʒ��������2();
            ˢ�½��������();
            ˢ�µ�ȯ������();
            ˢ������ֿ�();
            ˢ�¼���ֿ�();
        }

    }//GEN-LAST:event_��ѯActionPerformed

    private void ˢ�¼���ֿ�() {
        for (int i = ((DefaultTableModel) (this.����ֿ�.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.����ֿ�.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM bank_item1  WHERE itemid = " + Integer.parseInt(��Ʒ����.getText()) + "  order by count desc");
            rs = ps.executeQuery();
            while (rs.next()) {

                ((DefaultTableModel) ����ֿ�.getModel()).insertRow(����ֿ�.getRowCount(), new Object[]{
                    ��ȡ��������(rs.getInt("cid")), //�˺�ID
                    rs.getInt("count"), //�˺�
                    �˺�IDȡ��QQ(��ɫIDȡ�˺�ID(rs.getInt("cid")))
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String ��ȡ��������(int guildId) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ȡ�������Ƴ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        return data;
    }

    private void ˢ������ֿ�() {
        for (int i = ((DefaultTableModel) (this.����ֿ�.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.����ֿ�.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM bank_item  WHERE itemid = " + Integer.parseInt(��Ʒ����.getText()) + "  order by count desc");
            rs = ps.executeQuery();
            while (rs.next()) {

                ((DefaultTableModel) ����ֿ�.getModel()).insertRow(����ֿ�.getRowCount(), new Object[]{
                    ��ɫIDȡ��ɫ��(rs.getInt("cid")), //�˺�ID
                    rs.getInt("count"), //�˺�
                    �˺�IDȡ��QQ(��ɫIDȡ�˺�ID(rs.getInt("cid")))
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ˢ�µ�ȯ������() {
        for (int i = ((DefaultTableModel) (this.�����н��1.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�����н��1.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM auctionitems  WHERE itemid = " + Integer.parseInt(��Ʒ����.getText()) + "  order by quantity desc");
            rs = ps.executeQuery();
            while (rs.next()) {

                ((DefaultTableModel) �����н��1.getModel()).insertRow(�����н��1.getRowCount(), new Object[]{
                    rs.getString("characterName"), //�˺�ID
                    rs.getInt("quantity"), //�˺�
                    �˺�IDȡ��QQ(��ɫIDȡ�˺�ID(rs.getInt("characterid")))
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ˢ�½��������() {
        for (int i = ((DefaultTableModel) (this.�����н��.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�����н��.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM auctionitems1  WHERE itemid = " + Integer.parseInt(��Ʒ����.getText()) + "  order by quantity desc");
            rs = ps.executeQuery();
            while (rs.next()) {

                ((DefaultTableModel) �����н��.getModel()).insertRow(�����н��.getRowCount(), new Object[]{
                    rs.getString("characterName"), //�˺�ID
                    rs.getInt("quantity"), //�˺�
                    �˺�IDȡ��QQ(��ɫIDȡ�˺�ID(rs.getInt("characterid")))
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    private void ˢ����Ʒ��������() {
        for (int i = ((DefaultTableModel) (this.���ӵ������.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.���ӵ������.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems  WHERE itemid = " + Integer.parseInt(��Ʒ����.getText()) + " && characterid > 0 order by quantity desc");
            rs = ps.executeQuery();
            while (rs.next()) {

                ((DefaultTableModel) ���ӵ������.getModel()).insertRow(���ӵ������.getRowCount(), new Object[]{
                    ��ɫIDȡ��ɫ��(rs.getInt("characterid")) + " [" + rs.getInt("characterid") + "]", //�˺�ID
                    rs.getInt("quantity"), //�˺�
                    �˺�IDȡ��QQ(��ɫIDȡ�˺�ID(rs.getInt("characterid")))
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    private void ˢ����Ʒ��������2() {
        for (int i = ((DefaultTableModel) (this.���ӵ������1.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.���ӵ������1.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems  WHERE itemid = " + Integer.parseInt(��Ʒ����.getText()) + " && accountid > 0 order by quantity desc");
            rs = ps.executeQuery();
            while (rs.next()) {

                ((DefaultTableModel) ���ӵ������1.getModel()).insertRow(���ӵ������1.getRowCount(), new Object[]{
                    rs.getInt("accountid"), //�˺�ID
                    rs.getInt("quantity"), //�˺�
                    �˺�IDȡ��QQ(rs.getInt("accountid"))
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public static int ��ɫIDȡ�˺�ID(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT accountid as DATA FROM characters WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ɫ����ȡ�˺�ID������");
        }
        return data;
    }

    public static String �˺�IDȡ��QQ(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT qq as DATA FROM accounts WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�˺�IDȡ�˺š�����");
        }
        return data;
    }

    public static String ��ɫIDȡ��ɫ��(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM characters WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ɫIDȡ��ɫ��������");
        }
        return data;
    }

    public static void main(String args[]) {
        ������.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            //UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ������().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField ��������ֿ�;
    private javax.swing.JTable ����ֿ�;
    private javax.swing.JTextField ��������ֿ�;
    private javax.swing.JTable �����н��;
    private javax.swing.JTable �����н��1;
    private javax.swing.JButton ��ѯ;
    private javax.swing.JTextField ��Ϸ�̳�;
    private javax.swing.JTextField ��ȯ������;
    private javax.swing.JTextField ��Ʒ����;
    private javax.swing.JTextField ��Ʒ����;
    private javax.swing.JTable ���ӵ������;
    private javax.swing.JTable ���ӵ������1;
    private javax.swing.JTextField ����;
    private javax.swing.JTextField ���������;
    private javax.swing.JTable ����ֿ�;
    // End of variables declaration//GEN-END:variables
}
