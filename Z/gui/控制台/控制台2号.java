package gui.����̨;

import static abc.Game.���ڱ���;
import database.DatabaseConnection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import scripting.NPCConversationManager;
import scripting.ReactorScriptManager;
import server.MapleItemInformationProvider;

public class ����̨2�� extends javax.swing.JFrame {

    public ����̨2��() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        setIconImage(icon.getImage());
        Properties �O���n = System.getProperties();
        setTitle("" + ���ڱ��� + "�� 2 �ſ���̨���ɹرա�");
        initComponents();
        //������
        ˢ�¶���();
        ˢ����Ʒ�������ʱ��();
        ˢ�µ�ͼ��Ʒ����();
        ˢ�µ�ͼˢ��Ƶ��();
    }

    private void ˢ�µ�ͼˢ��Ƶ��() {
        int ��ʾ = gui.Start.ConfigValuesMap.get("��ͼˢ��Ƶ��");
        ��ͼˢ��Ƶ��.setText("" + ��ʾ + "");
    }

    private void ˢ�µ�ͼ��Ʒ����() {
        int ��ʾ = gui.Start.ConfigValuesMap.get("��ͼ��Ʒ����");
        ��ͼ��Ʒ����.setText("" + ��ʾ + "");
    }

    private void ˢ����Ʒ�������ʱ��() {
        int ��ʾ = gui.Start.ConfigValuesMap.get("��Ʒ�������ʱ��");
        ��Ʒ�������ʱ��.setText("" + ��ʾ + "");
    }
//    public void ˢ����Ʒ�������ʱ��() {
//        for (int i = ((DefaultTableModel) (this.��Ʒ�������ʱ����ʾ.getModel())).getRowCount() - 1; i >= 0; i--) {
//            ((DefaultTableModel) (this.��Ʒ�������ʱ����ʾ.getModel())).removeRow(i);
//        }
//        try {
//            Connection con = DatabaseConnection.getConnection();
//            PreparedStatement ps = null;
//            ResultSet rs = null;
//            ps = con.prepareStatement("SELECT * FROM configvalues WHERE id = 998 ");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                ((DefaultTableModel) ��Ʒ�������ʱ����ʾ.getModel()).insertRow(��Ʒ�������ʱ����ʾ.getRowCount(), new Object[]{rs.getString("Val")});
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    private void ˢ�¶���() {
        �򶹶�����.setText("" + gui.Start.ConfigValuesMap.get("����������") + "");
        ����������.setText("" + gui.Start.ConfigValuesMap.get("��������������") + "");
        ����������.setText("" + gui.Start.ConfigValuesMap.get("������������������") + "");
        ����ϡ����.setText("" + gui.Start.ConfigValuesMap.get("����������ϡ����") + "");
        �춹���ӿ�����.setText("" + gui.Start.ConfigValuesMap.get("�춹���ӿ�����") + "");
        �������ӿ�����.setText("" + gui.Start.ConfigValuesMap.get("�������ӿ�����") + "");
        �̶����ӿ�����.setText("" + gui.Start.ConfigValuesMap.get("�̶����ӿ�����") + "");
        ��������.setText("" + gui.Start.ConfigValuesMap.get("������������������") + "");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        ��Ϸ���� = new javax.swing.JPanel();
        ��ѯ��Ʒ���� = new javax.swing.JButton();
        ��ѯ��Ʒ������� = new javax.swing.JTextField();
        ��ѯ������� = new javax.swing.JButton();
        ��ѯ���������� = new javax.swing.JTextField();
        ɾ��ָ���ĵ��䰴�� = new javax.swing.JButton();
        ɾ��ָ���ĵ��� = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        ���ﱬ�� = new javax.swing.JTable();
        ���ﱬ�����к� = new javax.swing.JTextField();
        ���ﱬ�������� = new javax.swing.JTextField();
        ���ﱬ����Ʒ���� = new javax.swing.JTextField();
        ���ﱬ�ﱬ�� = new javax.swing.JTextField();
        ���ﱬ����Ʒ���� = new javax.swing.JTextField();
        ɾ�����ﱬ�� = new javax.swing.JButton();
        ��ӹ��ﱬ�� = new javax.swing.JButton();
        jLabel120 = new javax.swing.JLabel();
        jLabel211 = new javax.swing.JLabel();
        jLabel212 = new javax.swing.JLabel();
        jLabel213 = new javax.swing.JLabel();
        �޸Ĺ��ﱬ�� = new javax.swing.JButton();
        ˢ�¹��ﱬ�� = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ���籬�� = new javax.swing.JTable();
        ���籬�����к� = new javax.swing.JTextField();
        ���籬����Ʒ���� = new javax.swing.JTextField();
        ���籬�ﱬ�� = new javax.swing.JTextField();
        ������籬�� = new javax.swing.JButton();
        ɾ�����籬�� = new javax.swing.JButton();
        jLabel210 = new javax.swing.JLabel();
        jLabel202 = new javax.swing.JLabel();
        jLabel209 = new javax.swing.JLabel();
        ���籬������ = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        �޸����籬�� = new javax.swing.JButton();
        ˢ�����籬�� = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jLabel216 = new javax.swing.JLabel();
        jLabel318 = new javax.swing.JLabel();
        jLabel321 = new javax.swing.JLabel();
        jLabel323 = new javax.swing.JLabel();
        jLabel315 = new javax.swing.JLabel();
        jLabel316 = new javax.swing.JLabel();
        jLabel317 = new javax.swing.JLabel();
        �޸���Ʒ�������ʱ�� = new javax.swing.JButton();
        ��Ʒ�������ʱ�� = new javax.swing.JTextField();
        jLabel320 = new javax.swing.JLabel();
        ˢ�¹��￨Ƭ = new javax.swing.JButton();
        ��ͼ��Ʒ���� = new javax.swing.JTextField();
        �޸���Ʒ�������ʱ��1 = new javax.swing.JButton();
        jLabel322 = new javax.swing.JLabel();
        jLabel319 = new javax.swing.JLabel();
        jLabel324 = new javax.swing.JLabel();
        jLabel325 = new javax.swing.JLabel();
        ��ͼˢ��Ƶ�� = new javax.swing.JTextField();
        �޸���Ʒ�������ʱ��2 = new javax.swing.JButton();
        jLabel330 = new javax.swing.JLabel();
        ɾ����Ϸ��Ʒ = new javax.swing.JPanel();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        ��Ϸ���� = new javax.swing.JTable();
        ��Ϸ���ߴ��� = new javax.swing.JTextField();
        jLabel338 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        ���ҵ��� = new javax.swing.JButton();
        ɾ������ = new javax.swing.JButton();
        jLabel337 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        ���ҵ���1 = new javax.swing.JButton();
        ɾ������1 = new javax.swing.JButton();
        jLabel339 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        ���ҵ���2 = new javax.swing.JButton();
        ɾ������2 = new javax.swing.JButton();
        jLabel340 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        ���ҵ���3 = new javax.swing.JButton();
        ɾ������3 = new javax.swing.JButton();
        jLabel341 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        ���ҵ���4 = new javax.swing.JButton();
        ɾ������4 = new javax.swing.JButton();
        jLabel342 = new javax.swing.JLabel();
        jLabel343 = new javax.swing.JLabel();
        jLabel344 = new javax.swing.JLabel();
        jLabel345 = new javax.swing.JLabel();
        ������� = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        ������Ʒ = new javax.swing.JTable();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jPanel32 = new javax.swing.JPanel();
        �޸ĵ�����Ʒ = new javax.swing.JButton();
        ˢ�µ�����Ʒ = new javax.swing.JButton();
        ������Ʒ���� = new javax.swing.JTextField();
        ����������Ʒ = new javax.swing.JButton();
        ������Ʒ���� = new javax.swing.JTextField();
        ������Ʒ���� = new javax.swing.JTextField();
        ɾ��������Ʒ = new javax.swing.JButton();
        ������Ʒ��� = new javax.swing.JTextField();
        jLabel264 = new javax.swing.JLabel();
        jLabel265 = new javax.swing.JLabel();
        jLabel266 = new javax.swing.JLabel();
        jLabel267 = new javax.swing.JLabel();
        �ڿ���� = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        �ڿ�Ӧ�� = new javax.swing.JTable();
        jPanel29 = new javax.swing.JPanel();
        �����ڿ���Ʒ = new javax.swing.JButton();
        �����ڿ���Ʒ = new javax.swing.JTextField();
        �ڿ���Ʒ���� = new javax.swing.JTextField();
        �ڿ����к� = new javax.swing.JTextField();
        �޸��ڿ���Ʒ = new javax.swing.JButton();
        ��Ʒ���� = new javax.swing.JTextField();
        ɾ���ڿ���Ʒ1 = new javax.swing.JButton();
        jLabel260 = new javax.swing.JLabel();
        jLabel261 = new javax.swing.JLabel();
        jLabel262 = new javax.swing.JLabel();
        jLabel263 = new javax.swing.JLabel();
        jPanel54 = new javax.swing.JPanel();
        �����ڿ���Ʒ1 = new javax.swing.JButton();
        �����ڿ���Ʒ1 = new javax.swing.JTextField();
        �ڿ���Ʒ����1 = new javax.swing.JTextField();
        �ڿ����к�1 = new javax.swing.JTextField();
        �޸��ڿ���Ʒ1 = new javax.swing.JButton();
        ��Ʒ����1 = new javax.swing.JTextField();
        ɾ���ڿ���Ʒ2 = new javax.swing.JButton();
        jLabel326 = new javax.swing.JLabel();
        jLabel327 = new javax.swing.JLabel();
        jLabel328 = new javax.swing.JLabel();
        jLabel329 = new javax.swing.JLabel();
        �����ڿ���Ʒ2 = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        ˢ���ڿ��� = new javax.swing.JButton();
        jPanel53 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        ˢ���ڿ���1 = new javax.swing.JButton();
        ��ҵ��ʾ���� = new javax.swing.JLabel();
        ��Ϸ�̵� = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        ��Ϸ�̵�2 = new javax.swing.JTable();
        jPanel33 = new javax.swing.JPanel();
        ��ѯ�̵�2 = new javax.swing.JButton();
        ��ѯ�̵� = new javax.swing.JTextField();
        jLabel270 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        ɾ����Ʒ = new javax.swing.JButton();
        ������Ʒ = new javax.swing.JButton();
        ��Ʒ��� = new javax.swing.JTextField();
        �̵���� = new javax.swing.JTextField();
        ��Ʒ��Ʒ���� = new javax.swing.JTextField();
        ��Ʒ�ۼ۽�� = new javax.swing.JTextField();
        jLabel268 = new javax.swing.JLabel();
        jLabel269 = new javax.swing.JLabel();
        jLabel271 = new javax.swing.JLabel();
        jLabel272 = new javax.swing.JLabel();
        �޸���Ʒ = new javax.swing.JButton();
        ��Ʒ���� = new javax.swing.JTextField();
        jLabel273 = new javax.swing.JLabel();
        �̵���ʾ���� = new javax.swing.JLabel();
        ��Ӧ������ = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        ��Ӧ�� = new javax.swing.JTable();
        ɾ����Ӧ����Ʒ���� = new javax.swing.JTextField();
        ɾ����Ӧ����Ʒ = new javax.swing.JButton();
        jLabel114 = new javax.swing.JLabel();
        jLabel281 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jButton20 = new javax.swing.JButton();
        ��Ӧ�����к� = new javax.swing.JTextField();
        ��Ӧ�Ѵ��� = new javax.swing.JTextField();
        ��Ӧ����Ʒ = new javax.swing.JTextField();
        ��Ӧ�Ѹ��� = new javax.swing.JTextField();
        ������Ӧ����Ʒ = new javax.swing.JButton();
        ɾ����Ӧ����Ʒ1 = new javax.swing.JButton();
        ���ҷ�Ӧ�ѵ��� = new javax.swing.JTextField();
        jButton36 = new javax.swing.JButton();
        ������Ʒ = new javax.swing.JTextField();
        jButton37 = new javax.swing.JButton();
        jLabel274 = new javax.swing.JLabel();
        jLabel275 = new javax.swing.JLabel();
        jLabel277 = new javax.swing.JLabel();
        jLabel278 = new javax.swing.JLabel();
        jLabel279 = new javax.swing.JLabel();
        jLabel280 = new javax.swing.JLabel();
        �޸ķ�Ӧ����Ʒ = new javax.swing.JButton();
        jLabel282 = new javax.swing.JLabel();
        ���������� = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        �춹���ӿ����� = new javax.swing.JTextField();
        jLabel350 = new javax.swing.JLabel();
        jLabel351 = new javax.swing.JLabel();
        �������ӿ����� = new javax.swing.JTextField();
        jLabel352 = new javax.swing.JLabel();
        �̶����ӿ����� = new javax.swing.JTextField();
        jLabel354 = new javax.swing.JLabel();
        jLabel355 = new javax.swing.JLabel();
        jLabel356 = new javax.swing.JLabel();
        �������޸�����1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        �������� = new javax.swing.JTextField();
        �������޸�����2 = new javax.swing.JButton();
        jLabel357 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel346 = new javax.swing.JLabel();
        �򶹶����� = new javax.swing.JTextField();
        ���������� = new javax.swing.JTextField();
        jLabel347 = new javax.swing.JLabel();
        jLabel348 = new javax.swing.JLabel();
        ���������� = new javax.swing.JTextField();
        jLabel349 = new javax.swing.JLabel();
        ����ϡ���� = new javax.swing.JTextField();
        �������޸����� = new javax.swing.JButton();
        jLabel353 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ҩˮ��ȴ = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        ҩˮ��ȴʱ�� = new javax.swing.JTextField();
        ҩˮ��� = new javax.swing.JTextField();
        ҩˮ���� = new javax.swing.JTextField();
        jLabel285 = new javax.swing.JLabel();
        jLabel286 = new javax.swing.JLabel();
        jLabel287 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        ZEVMS2��ʾ�� = new javax.swing.JLabel();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��Ϸ����.setBackground(new java.awt.Color(255, 255, 255));
        ��Ϸ����.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ѯ��Ʒ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ѯ��Ʒ����.setText("��ѯ��Ʒ����");
        ��ѯ��Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ѯ��Ʒ����ActionPerformed(evt);
            }
        });
        ��Ϸ����.add(��ѯ��Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 100, 140, -1));

        ��ѯ��Ʒ�������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ѯ��Ʒ�������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ѯ��Ʒ�������ActionPerformed(evt);
            }
        });
        ��Ϸ����.add(��ѯ��Ʒ�������, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 70, 140, -1));

        ��ѯ�������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ѯ�������.setText("��ѯ�������");
        ��ѯ�������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ѯ�������ActionPerformed(evt);
            }
        });
        ��Ϸ����.add(��ѯ�������, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 190, 140, -1));

        ��ѯ����������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ϸ����.add(��ѯ����������, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 160, 140, -1));

        ɾ��ָ���ĵ��䰴��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ��ָ���ĵ��䰴��.setText("ɾ��ָ���ĵ���");
        ɾ��ָ���ĵ��䰴��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ��ָ���ĵ��䰴��ActionPerformed(evt);
            }
        });
        ��Ϸ����.add(ɾ��ָ���ĵ��䰴��, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 280, 140, -1));

        ɾ��ָ���ĵ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ϸ����.add(ɾ��ָ���ĵ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 250, 140, -1));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "���ﱬ��/(10000=1%)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ���ﱬ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���ﱬ��.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���к�", "�������", "��Ʒ����", "����", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ���ﱬ��.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(���ﱬ��);
        if (���ﱬ��.getColumnModel().getColumnCount() > 0) {
            ���ﱬ��.getColumnModel().getColumn(0).setResizable(false);
            ���ﱬ��.getColumnModel().getColumn(1).setResizable(false);
            ���ﱬ��.getColumnModel().getColumn(2).setResizable(false);
            ���ﱬ��.getColumnModel().getColumn(3).setResizable(false);
            ���ﱬ��.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel17.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 590, 510));

        ���ﱬ�����к�.setEditable(false);
        ���ﱬ�����к�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel17.add(���ﱬ�����к�, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 80, 30));

        ���ﱬ��������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel17.add(���ﱬ��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 560, 110, 30));

        ���ﱬ����Ʒ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel17.add(���ﱬ����Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 560, 110, 30));

        ���ﱬ�ﱬ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel17.add(���ﱬ�ﱬ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 560, 120, 30));

        ���ﱬ����Ʒ����.setEditable(false);
        ���ﱬ����Ʒ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel17.add(���ﱬ����Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 560, 170, 30));

        ɾ�����ﱬ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ�����ﱬ��.setText("ɾ��");
        ɾ�����ﱬ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ�����ﱬ��ActionPerformed(evt);
            }
        });
        jPanel17.add(ɾ�����ﱬ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 600, 70, 30));

        ��ӹ��ﱬ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ӹ��ﱬ��.setText("���");
        ��ӹ��ﱬ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ӹ��ﱬ��ActionPerformed(evt);
            }
        });
        jPanel17.add(��ӹ��ﱬ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 600, 70, 30));

        jLabel120.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel120.setText("������룻");
        jPanel17.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, -1, -1));

        jLabel211.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel211.setText("��Ʒ���룻");
        jPanel17.add(jLabel211, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, -1, 20));

        jLabel212.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel212.setText("���ʣ�");
        jPanel17.add(jLabel212, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 540, -1, -1));

        jLabel213.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel213.setText("���кţ�");
        jPanel17.add(jLabel213, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, -1, -1));

        �޸Ĺ��ﱬ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸Ĺ��ﱬ��.setText("�޸�");
        �޸Ĺ��ﱬ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸Ĺ��ﱬ��ActionPerformed(evt);
            }
        });
        jPanel17.add(�޸Ĺ��ﱬ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 600, 70, 30));

        ˢ�¹��ﱬ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ�¹��ﱬ��.setText("ˢ�¹��ﱬ��");
        ˢ�¹��ﱬ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�¹��ﱬ��ActionPerformed(evt);
            }
        });
        jPanel17.add(ˢ�¹��ﱬ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 600, 140, 30));

        jLabel39.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel39.setText("��Ʒ����");
        jPanel17.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 540, -1, -1));

        ��Ϸ����.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 610, 640));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ȫ�ֱ���/(10000=1%)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ���籬��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���籬��.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���к�", "��Ʒ����", "����", "��Ʒ��"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ���籬��.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(���籬��);
        if (���籬��.getColumnModel().getColumnCount() > 0) {
            ���籬��.getColumnModel().getColumn(0).setResizable(false);
            ���籬��.getColumnModel().getColumn(0).setPreferredWidth(20);
            ���籬��.getColumnModel().getColumn(1).setResizable(false);
            ���籬��.getColumnModel().getColumn(2).setResizable(false);
            ���籬��.getColumnModel().getColumn(3).setResizable(false);
            ���籬��.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        jPanel18.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 440, 510));

        ���籬�����к�.setEditable(false);
        ���籬�����к�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���籬�����к�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���籬�����к�ActionPerformed(evt);
            }
        });
        jPanel18.add(���籬�����к�, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 90, 30));

        ���籬����Ʒ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���籬����Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���籬����Ʒ����ActionPerformed(evt);
            }
        });
        jPanel18.add(���籬����Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 560, 120, 30));

        ���籬�ﱬ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���籬�ﱬ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���籬�ﱬ��ActionPerformed(evt);
            }
        });
        jPanel18.add(���籬�ﱬ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 560, 90, 30));

        ������籬��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������籬��.setText("���");
        ������籬��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������籬��ActionPerformed(evt);
            }
        });
        jPanel18.add(������籬��, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 600, 70, 30));

        ɾ�����籬��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ�����籬��.setText("ɾ��");
        ɾ�����籬��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ�����籬��ActionPerformed(evt);
            }
        });
        jPanel18.add(ɾ�����籬��, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 600, 70, 30));

        jLabel210.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel210.setText("���кţ�");
        jPanel18.add(jLabel210, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, -1, -1));

        jLabel202.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel202.setText("��Ʒ���룻");
        jPanel18.add(jLabel202, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 540, -1, 20));

        jLabel209.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel209.setText("���ʣ�");
        jPanel18.add(jLabel209, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 540, -1, -1));

        ���籬������.setEditable(false);
        ���籬������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���籬������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���籬������ActionPerformed(evt);
            }
        });
        jPanel18.add(���籬������, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 560, 140, 30));

        jLabel36.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel36.setText("��Ʒ����");
        jPanel18.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 540, -1, -1));

        �޸����籬��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸����籬��.setText("�޸�");
        �޸����籬��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸����籬��ActionPerformed(evt);
            }
        });
        jPanel18.add(�޸����籬��, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 600, 70, 30));

        ˢ�����籬��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ�����籬��.setText("ˢ�����籬��");
        ˢ�����籬��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�����籬��ActionPerformed(evt);
            }
        });
        jPanel18.add(ˢ�����籬��, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 600, 140, 30));

        ��Ϸ����.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 460, 640));

        jLabel62.setFont(new java.awt.Font("����", 1, 15)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(204, 0, 51));
        ��Ϸ����.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 40, -1, -1));

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��ѯ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel216.setFont(new java.awt.Font("����", 1, 15)); // NOI18N
        jLabel216.setForeground(new java.awt.Color(204, 0, 51));
        jLabel216.setText("ָ���������Ʒ����");
        jPanel31.add(jLabel216, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 140, -1, -1));

        jLabel318.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel318.setText("ָ�������ѯ����");
        jPanel31.add(jLabel318, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel321.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel321.setText("ɾ��ָ����Ʒ����");
        jPanel31.add(jLabel321, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel323.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel323.setText("ָ����Ʒ��ѯ����");
        jPanel31.add(jLabel323, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        ��Ϸ����.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 10, 160, 320));

        jLabel315.setFont(new java.awt.Font("����", 1, 15)); // NOI18N
        jLabel315.setForeground(new java.awt.Color(204, 0, 51));
        jLabel315.setText("ɾ�����ﱬ����Ʒ");
        ��Ϸ����.add(jLabel315, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 230, -1, -1));

        jLabel316.setFont(new java.awt.Font("����", 1, 15)); // NOI18N
        jLabel316.setForeground(new java.awt.Color(204, 0, 51));
        jLabel316.setText("ָ���������Ʒ����");
        ��Ϸ����.add(jLabel316, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 140, -1, -1));

        jLabel317.setFont(new java.awt.Font("����", 1, 15)); // NOI18N
        jLabel317.setForeground(new java.awt.Color(204, 0, 51));
        jLabel317.setText("ָ����Ʒ��������");
        ��Ϸ����.add(jLabel317, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 50, -1, -1));

        �޸���Ʒ�������ʱ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸���Ʒ�������ʱ��.setText("�޸�ȷ��");
        �޸���Ʒ�������ʱ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸���Ʒ�������ʱ��ActionPerformed(evt);
            }
        });
        ��Ϸ����.add(�޸���Ʒ�������ʱ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 430, 140, 30));

        ��Ʒ�������ʱ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ϸ����.add(��Ʒ�������ʱ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 400, 90, -1));

        jLabel320.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel320.setForeground(new java.awt.Color(255, 0, 0));
        jLabel320.setText("��Ҫ������Ч������ 10000 ");
        ��Ϸ����.add(jLabel320, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 640, 180, 40));

        ˢ�¹��￨Ƭ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ�¹��￨Ƭ.setText("ˢ�¹��￨Ƭ");
        ˢ�¹��￨Ƭ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�¹��￨ƬActionPerformed(evt);
            }
        });
        ��Ϸ����.add(ˢ�¹��￨Ƭ, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 340, 140, 30));

        ��ͼ��Ʒ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ϸ����.add(��ͼ��Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 490, 90, -1));

        �޸���Ʒ�������ʱ��1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸���Ʒ�������ʱ��1.setText("�޸�ȷ��");
        �޸���Ʒ�������ʱ��1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸���Ʒ�������ʱ��1ActionPerformed(evt);
            }
        });
        ��Ϸ����.add(�޸���Ʒ�������ʱ��1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 520, 140, 30));

        jLabel322.setFont(new java.awt.Font("��Բ", 0, 20)); // NOI18N
        jLabel322.setText("��");
        ��Ϸ����.add(jLabel322, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 390, 30, 40));

        jLabel319.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel319.setText("��ͼ��Ʒ������");
        ��Ϸ����.add(jLabel319, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 470, -1, -1));

        jLabel324.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel324.setText("��Ʒ�������ʱ��");
        ��Ϸ����.add(jLabel324, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 380, -1, -1));

        jLabel325.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel325.setText("��ͼˢ��Ƶ��");
        ��Ϸ����.add(jLabel325, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 560, -1, -1));

        ��ͼˢ��Ƶ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ϸ����.add(��ͼˢ��Ƶ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 580, 90, -1));

        �޸���Ʒ�������ʱ��2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸���Ʒ�������ʱ��2.setText("�޸�ȷ��");
        �޸���Ʒ�������ʱ��2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸���Ʒ�������ʱ��2ActionPerformed(evt);
            }
        });
        ��Ϸ����.add(�޸���Ʒ�������ʱ��2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 610, 140, 30));

        jLabel330.setFont(new java.awt.Font("��Բ", 0, 20)); // NOI18N
        jLabel330.setText("��");
        ��Ϸ����.add(jLabel330, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 480, 30, 40));

        jTabbedPane2.addTab("��Ϸ���ﱬ��", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ��Ϸ����, ""); // NOI18N

        ɾ����Ϸ��Ʒ.setBackground(new java.awt.Color(255, 255, 255));
        ɾ����Ϸ��Ʒ.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        ɾ����Ϸ��Ʒ.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));
        ɾ����Ϸ��Ʒ.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));
        ɾ����Ϸ��Ʒ.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        ɾ����Ϸ��Ʒ.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��Ϸ��Ʒ����", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��Ϸ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ϸ����.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "��ɫID", "��ɫ����", "����ID", "��������"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��Ϸ����.setColumnSelectionAllowed(true);
        jScrollPane10.setViewportView(��Ϸ����);

        jPanel20.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 600, 640));
        jPanel20.add(��Ϸ���ߴ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 160, 30));

        jLabel338.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel338.setText("�����߲�����");
        jPanel20.add(jLabel338, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 290, 350, 20));

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ���ҵ���.setText("���ҵ���");
        ���ҵ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ҵ���ActionPerformed(evt);
            }
        });
        jPanel9.add(���ҵ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 140, 30));

        ɾ������.setText("ɾ������");
        ɾ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ������ActionPerformed(evt);
            }
        });
        jPanel9.add(ɾ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 140, 30));

        jLabel337.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel337.setText("��ɫ��������ɫ����");
        jPanel9.add(jLabel337, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jPanel20.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 160, 100));

        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ���ҵ���1.setText("���ҵ���");
        ���ҵ���1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ҵ���1ActionPerformed(evt);
            }
        });
        jPanel22.add(���ҵ���1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 140, 30));

        ɾ������1.setText("ɾ������");
        ɾ������1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ������1ActionPerformed(evt);
            }
        });
        jPanel22.add(ɾ������1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 140, 30));

        jLabel339.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel339.setText("��ȯ������");
        jPanel22.add(jLabel339, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jPanel20.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 320, 160, 100));

        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ���ҵ���2.setText("���ҵ���");
        ���ҵ���2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ҵ���2ActionPerformed(evt);
            }
        });
        jPanel23.add(���ҵ���2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 140, 30));

        ɾ������2.setText("ɾ������");
        ɾ������2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ������2ActionPerformed(evt);
            }
        });
        jPanel23.add(ɾ������2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 140, 30));

        jLabel340.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel340.setText("���������");
        jPanel23.add(jLabel340, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jPanel20.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 210, 160, 100));

        jPanel46.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel46.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ���ҵ���3.setText("���ҵ���");
        ���ҵ���3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ҵ���3ActionPerformed(evt);
            }
        });
        jPanel46.add(���ҵ���3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 140, 30));

        ɾ������3.setText("ɾ������");
        ɾ������3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ������3ActionPerformed(evt);
            }
        });
        jPanel46.add(ɾ������3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 140, 30));

        jLabel341.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel341.setText("������Ϸ�ֿ�");
        jPanel46.add(jLabel341, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jPanel20.add(jPanel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 540, 160, 100));

        jPanel47.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel47.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ���ҵ���4.setText("���ҵ���");
        ���ҵ���4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ҵ���4ActionPerformed(evt);
            }
        });
        jPanel47.add(���ҵ���4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 140, 30));

        ɾ������4.setText("ɾ������");
        ɾ������4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ������4ActionPerformed(evt);
            }
        });
        jPanel47.add(ɾ������4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 140, 30));

        jLabel342.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel342.setText("������Ϸ�ֿ�");
        jPanel47.add(jLabel342, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jPanel20.add(jPanel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 430, 160, 100));

        jLabel343.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel343.setText("��Ϸ���ߴ��룻");
        jPanel20.add(jLabel343, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, -1, 20));

        jLabel344.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel344.setText("�ù��ܿɲ�ѯ��Ϸ��ָ���������Ϸ���ߣ����Բ鿴");
        jPanel20.add(jLabel344, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 230, 350, 20));

        jLabel345.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel345.setText("�õ����ж������ӵ�С�����һ��ɾ�����иõ��ߡ�");
        jPanel20.add(jLabel345, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 260, 350, 20));

        ɾ����Ϸ��Ʒ.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 690));

        jTabbedPane2.addTab("��Ϸ��Ʒɾ��", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ɾ����Ϸ��Ʒ); // NOI18N

        �������.setBackground(new java.awt.Color(255, 255, 255));
        �������.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�������", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        �������.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ������Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������Ʒ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "����", "����", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ������Ʒ.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(������Ʒ);

        �������.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 510, 590));
        �������.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));
        �������.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "����༭", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �޸ĵ�����Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸ĵ�����Ʒ.setText("�޸�");
        �޸ĵ�����Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸ĵ�����ƷActionPerformed(evt);
            }
        });
        jPanel32.add(�޸ĵ�����Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, 30));

        ˢ�µ�����Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ�µ�����Ʒ.setText("ˢ�µ�����Ʒ");
        ˢ�µ�����Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�µ�����ƷActionPerformed(evt);
            }
        });
        jPanel32.add(ˢ�µ�����Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 30));

        ������Ʒ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������Ʒ����ActionPerformed(evt);
            }
        });
        jPanel32.add(������Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 110, 30));

        ����������Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����������Ʒ.setText("����");
        ����������Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����������ƷActionPerformed(evt);
            }
        });
        jPanel32.add(����������Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, 30));

        ������Ʒ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel32.add(������Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 100, 30));

        ������Ʒ����.setEditable(false);
        ������Ʒ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel32.add(������Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 150, 30));

        ɾ��������Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ��������Ʒ.setText("ɾ��");
        ɾ��������Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ��������ƷActionPerformed(evt);
            }
        });
        jPanel32.add(ɾ��������Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, -1, 30));

        ������Ʒ���.setEditable(false);
        ������Ʒ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������Ʒ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������Ʒ���ActionPerformed(evt);
            }
        });
        jPanel32.add(������Ʒ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 30));

        jLabel264.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel264.setText("��Ʒ���֣�");
        jPanel32.add(jLabel264, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, -1, -1));

        jLabel265.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel265.setText("���кţ�");
        jPanel32.add(jLabel265, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel266.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel266.setText("��Ʒ���룻");
        jPanel32.add(jLabel266, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        jLabel267.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel267.setText("�������ʣ�");
        jPanel32.add(jLabel267, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        �������.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 480, 150));

        jTabbedPane2.addTab("�泡�������", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), �������); // NOI18N

        �ڿ����.setBackground(new java.awt.Color(255, 255, 255));
        �ڿ����.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�ڿ�/��ҩ����", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 51, 51))); // NOI18N
        �ڿ����.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �ڿ�Ӧ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �ڿ�Ӧ��.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���к�", "��Ʒ����", "����", "����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        �ڿ�Ӧ��.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(�ڿ�Ӧ��);

        �ڿ����.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 420, 610));

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�ڿ���", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �����ڿ���Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �����ڿ���Ʒ.setText("����[�ڿ�]");
        �����ڿ���Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����ڿ���ƷActionPerformed(evt);
            }
        });
        jPanel29.add(�����ڿ���Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 110, 30));

        �����ڿ���Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel29.add(�����ڿ���Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 110, 30));

        �ڿ���Ʒ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel29.add(�ڿ���Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 110, 30));

        �ڿ����к�.setEditable(false);
        �ڿ����к�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel29.add(�ڿ����к�, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 90, 30));

        �޸��ڿ���Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸��ڿ���Ʒ.setText("�޸�");
        �޸��ڿ���Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸��ڿ���ƷActionPerformed(evt);
            }
        });
        jPanel29.add(�޸��ڿ���Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 80, 30));

        ��Ʒ����.setEditable(false);
        ��Ʒ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel29.add(��Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 120, 30));

        ɾ���ڿ���Ʒ1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ���ڿ���Ʒ1.setText("ɾ��");
        ɾ���ڿ���Ʒ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ���ڿ���Ʒ1ActionPerformed(evt);
            }
        });
        jPanel29.add(ɾ���ڿ���Ʒ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 80, 30));

        jLabel260.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel260.setText("��Ʒ���ƣ�");
        jPanel29.add(jLabel260, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, -1, -1));

        jLabel261.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel261.setText("���кţ�");
        jPanel29.add(jLabel261, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel262.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel262.setText("��Ʒ���룻");
        jPanel29.add(jLabel262, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        jLabel263.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel263.setText("������ʣ�");
        jPanel29.add(jLabel263, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

        jPanel54.setBackground(new java.awt.Color(255, 255, 255));
        jPanel54.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�ڿ�༭", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("����", 1, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel54.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �����ڿ���Ʒ1.setText("����");
        �����ڿ���Ʒ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����ڿ���Ʒ1ActionPerformed(evt);
            }
        });
        jPanel54.add(�����ڿ���Ʒ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 80, 30));
        jPanel54.add(�����ڿ���Ʒ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 110, 30));
        jPanel54.add(�ڿ���Ʒ����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 110, 30));

        �ڿ����к�1.setEditable(false);
        jPanel54.add(�ڿ����к�1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 90, 30));

        �޸��ڿ���Ʒ1.setText("�޸�");
        �޸��ڿ���Ʒ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸��ڿ���Ʒ1ActionPerformed(evt);
            }
        });
        jPanel54.add(�޸��ڿ���Ʒ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 80, 30));

        ��Ʒ����1.setEditable(false);
        jPanel54.add(��Ʒ����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 120, 30));

        ɾ���ڿ���Ʒ2.setText("ɾ��");
        ɾ���ڿ���Ʒ2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ���ڿ���Ʒ2ActionPerformed(evt);
            }
        });
        jPanel54.add(ɾ���ڿ���Ʒ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 80, 30));

        jLabel326.setFont(new java.awt.Font("����", 1, 15)); // NOI18N
        jLabel326.setForeground(new java.awt.Color(204, 0, 51));
        jLabel326.setText("��Ʒ���ƣ�");
        jPanel54.add(jLabel326, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, -1, -1));

        jLabel327.setFont(new java.awt.Font("����", 1, 15)); // NOI18N
        jLabel327.setForeground(new java.awt.Color(204, 0, 51));
        jLabel327.setText("���кţ�");
        jPanel54.add(jLabel327, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel328.setFont(new java.awt.Font("����", 1, 15)); // NOI18N
        jLabel328.setForeground(new java.awt.Color(204, 0, 51));
        jLabel328.setText("��Ʒ���룻");
        jPanel54.add(jLabel328, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        jLabel329.setFont(new java.awt.Font("����", 1, 15)); // NOI18N
        jLabel329.setForeground(new java.awt.Color(204, 0, 51));
        jLabel329.setText("������ʣ�");
        jPanel54.add(jLabel329, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

        jPanel29.add(jPanel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, 500, 180));

        �����ڿ���Ʒ2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �����ڿ���Ʒ2.setText("����[��ҩ]");
        �����ڿ���Ʒ2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����ڿ���Ʒ2ActionPerformed(evt);
            }
        });
        jPanel29.add(�����ڿ���Ʒ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 110, 30));

        �ڿ����.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 360, 500, 250));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�ڿ���", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton11.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jButton11.setText("����");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel30.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 130, -1));

        ˢ���ڿ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ���ڿ���.setText("ˢ���ڿ���");
        ˢ���ڿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ���ڿ���ActionPerformed(evt);
            }
        });
        jPanel30.add(ˢ���ڿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 130, 30));

        �ڿ����.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 210, 140));

        jPanel53.setBackground(new java.awt.Color(255, 255, 255));
        jPanel53.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�ڿ���", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel53.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton16.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jButton16.setText("����");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel53.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 130, -1));

        ˢ���ڿ���1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ���ڿ���1.setText("ˢ�²�ҩ����");
        ˢ���ڿ���1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ���ڿ���1ActionPerformed(evt);
            }
        });
        jPanel53.add(ˢ���ڿ���1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 130, 30));

        �ڿ����.add(jPanel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, 210, 140));

        ��ҵ��ʾ����.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ��ҵ��ʾ����.setText("[��Ϣ]��");
        �ڿ����.add(��ҵ��ʾ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 1240, 25));

        jTabbedPane2.addTab("��Ϸ�ڿ��ҩ", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), �ڿ����); // NOI18N

        ��Ϸ�̵�.setBackground(new java.awt.Color(255, 255, 255));
        ��Ϸ�̵�.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��Ϸ�̵����", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        ��Ϸ�̵�.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��Ϸ�̵�2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ϸ�̵�2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "�̵�ID", "��Ʒ����", "���۽��", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��Ϸ�̵�2.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(��Ϸ�̵�2);

        ��Ϸ�̵�.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 490, 620));

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��ѯ��Ʒ������Ʒ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ѯ�̵�2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ѯ�̵�2.setText("��ѯ�̵�");
        ��ѯ�̵�2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ѯ�̵�2ActionPerformed(evt);
            }
        });
        jPanel33.add(��ѯ�̵�2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, 30));

        ��ѯ�̵�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel33.add(��ѯ�̵�, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 110, 30));

        jLabel270.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel270.setText("�̵�ID��");
        jPanel33.add(jLabel270, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        ��Ϸ�̵�.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, 380, 130));

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��ѯ��Ʒ������Ʒ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ɾ����Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ����Ʒ.setText("ɾ��");
        ɾ����Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ����ƷActionPerformed(evt);
            }
        });
        jPanel34.add(ɾ����Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, -1, 30));

        ������Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������Ʒ.setText("����");
        ������Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������ƷActionPerformed(evt);
            }
        });
        jPanel34.add(������Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, -1, 30));

        ��Ʒ���.setEditable(false);
        ��Ʒ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel34.add(��Ʒ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 80, 30));

        �̵����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �̵����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �̵����ActionPerformed(evt);
            }
        });
        jPanel34.add(�̵����, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 80, 30));

        ��Ʒ��Ʒ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel34.add(��Ʒ��Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 90, 30));

        ��Ʒ�ۼ۽��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel34.add(��Ʒ�ۼ۽��, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 100, 30));

        jLabel268.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel268.setText("���۽�ң�");
        jPanel34.add(jLabel268, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, -1, -1));

        jLabel269.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel269.setText("��ţ�");
        jPanel34.add(jLabel269, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabel271.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel271.setText("��Ʒ���ƣ�");
        jPanel34.add(jLabel271, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, -1));

        jLabel272.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel272.setText("�̵�ID��");
        jPanel34.add(jLabel272, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, -1));

        �޸���Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸���Ʒ.setText("�޸�");
        �޸���Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸���ƷActionPerformed(evt);
            }
        });
        jPanel34.add(�޸���Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, -1, 30));

        ��Ʒ����.setEditable(false);
        ��Ʒ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ʒ����ActionPerformed(evt);
            }
        });
        jPanel34.add(��Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 140, 30));

        jLabel273.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel273.setText("��Ʒ���룻");
        jPanel34.add(jLabel273, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        ��Ϸ�̵�.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 420, 580, 220));

        �̵���ʾ����.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        �̵���ʾ����.setText("[��Ϣ]��");
        ��Ϸ�̵�.add(�̵���ʾ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 653, 1240, 25));

        jTabbedPane2.addTab("��Ϸ�̵����", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ��Ϸ�̵�, ""); // NOI18N

        ��Ӧ������.setBackground(new java.awt.Color(255, 255, 255));
        ��Ӧ������.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��Ӧ��/����", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        ��Ӧ������.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��Ӧ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ӧ��.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���к�", "��Ӧ��", "��Ʒ����", "����", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��Ӧ��.getTableHeader().setReorderingAllowed(false);
        jScrollPane14.setViewportView(��Ӧ��);

        ��Ӧ������.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 510, 630));
        ��Ӧ������.add(ɾ����Ӧ����Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 600, 90, 30));

        ɾ����Ӧ����Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ����Ӧ����Ʒ.setText("ɾ��");
        ɾ����Ӧ����Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ����Ӧ����ƷActionPerformed(evt);
            }
        });
        ��Ӧ������.add(ɾ����Ӧ����Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 600, 100, 30));

        jLabel114.setFont(new java.awt.Font("����", 1, 18)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(255, 0, 102));
        ��Ӧ������.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 503, -1, -1));

        jLabel281.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel281.setText("��ʾ���޸���ɺ����Ҳ�Ӧ�����غ󼴿���Ч��");
        ��Ӧ������.add(jLabel281, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 370, -1, 20));

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��Ӧ�ѱ༭", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton20.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jButton20.setText("ˢ��");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel35.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, 70, 30));

        ��Ӧ�����к�.setEditable(false);
        ��Ӧ�����к�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel35.add(��Ӧ�����к�, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 70, 30));

        ��Ӧ�Ѵ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel35.add(��Ӧ�Ѵ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 90, 30));

        ��Ӧ����Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel35.add(��Ӧ����Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 90, 30));

        ��Ӧ�Ѹ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ӧ�Ѹ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ӧ�Ѹ���ActionPerformed(evt);
            }
        });
        jPanel35.add(��Ӧ�Ѹ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 90, 30));

        ������Ӧ����Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������Ӧ����Ʒ.setText("����");
        ������Ӧ����Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������Ӧ����ƷActionPerformed(evt);
            }
        });
        jPanel35.add(������Ӧ����Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 70, 30));

        ɾ����Ӧ����Ʒ1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ����Ӧ����Ʒ1.setText("ɾ��");
        ɾ����Ӧ����Ʒ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ����Ӧ����Ʒ1ActionPerformed(evt);
            }
        });
        jPanel35.add(ɾ����Ӧ����Ʒ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 70, 30));

        ���ҷ�Ӧ�ѵ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel35.add(���ҷ�Ӧ�ѵ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 90, 30));

        jButton36.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jButton36.setText("����");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jPanel35.add(jButton36, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, -1, 30));

        ������Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel35.add(������Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 90, 30));

        jButton37.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jButton37.setText("����");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        jPanel35.add(jButton37, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, -1, 30));

        jLabel274.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel274.setText("������ʣ�");
        jPanel35.add(jLabel274, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, -1, 20));

        jLabel275.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel275.setText("��ţ�");
        jPanel35.add(jLabel275, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, 20));

        jLabel277.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel277.setText("��Ʒ���룻");
        jPanel35.add(jLabel277, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, 20));

        jLabel278.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel278.setText("��Ӧ�ѣ�");
        jPanel35.add(jLabel278, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, 20));

        jLabel279.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel279.setText("��Ӧ�ѣ�");
        jPanel35.add(jLabel279, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, 20));

        jLabel280.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel280.setText("��Ʒ���룻");
        jPanel35.add(jLabel280, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 20));

        �޸ķ�Ӧ����Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸ķ�Ӧ����Ʒ.setText("�޸�");
        �޸ķ�Ӧ����Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸ķ�Ӧ����ƷActionPerformed(evt);
            }
        });
        jPanel35.add(�޸ķ�Ӧ����Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 70, 30));

        ��Ӧ������.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, 660, 220));

        jLabel282.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel282.setText("ɾ��ָ����Ӧ�ѵ�������Ʒ��");
        ��Ӧ������.add(jLabel282, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 580, -1, 20));

        jTabbedPane2.addTab("��Ӧ��/����", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ��Ӧ������); // NOI18N

        ����������.setBackground(new java.awt.Color(255, 255, 255));
        ����������.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�������������", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(1210, 550));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�����������ӿ�����", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(�춹���ӿ�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 80, 30));

        jLabel350.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel350.setText("%");
        jPanel3.add(jLabel350, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 20, 30));

        jLabel351.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel351.setText("�̶�����");
        jPanel3.add(jLabel351, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, 30));
        jPanel3.add(�������ӿ�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 80, 30));

        jLabel352.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel352.setText("��������");
        jPanel3.add(jLabel352, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, 30));
        jPanel3.add(�̶����ӿ�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 80, 30));

        jLabel354.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel354.setText("�춹����");
        jPanel3.add(jLabel354, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, 30));

        jLabel355.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel355.setText("%");
        jPanel3.add(jLabel355, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 20, 30));

        jLabel356.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel356.setText("%");
        jPanel3.add(jLabel356, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 20, 30));

        �������޸�����1.setText("�޸�");
        �������޸�����1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �������޸�����1ActionPerformed(evt);
            }
        });
        jPanel3.add(�������޸�����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 260, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 260, 280));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��������������", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 100, 30));

        �������޸�����2.setText("�޸�");
        �������޸�����2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �������޸�����2ActionPerformed(evt);
            }
        });
        jPanel4.add(�������޸�����2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 260, -1));

        jLabel357.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel357.setText("����������");
        jPanel4.add(jLabel357, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, 30));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 260, 280));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "����������", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel346.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel346.setText("�򶹶����ȣ�");
        jPanel5.add(jLabel346, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 30));
        jPanel5.add(�򶹶�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 100, 30));
        jPanel5.add(����������, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 100, 30));

        jLabel347.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel347.setText("���������ʣ�");
        jPanel5.add(jLabel347, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 30));

        jLabel348.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel348.setText("���������ʣ�");
        jPanel5.add(jLabel348, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 30));
        jPanel5.add(����������, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 100, 30));

        jLabel349.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel349.setText("����ϡ���ʣ�");
        jPanel5.add(jLabel349, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, 30));
        jPanel5.add(����ϡ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 100, 30));

        �������޸�����.setText("�޸�");
        �������޸�����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �������޸�����ActionPerformed(evt);
            }
        });
        jPanel5.add(�������޸�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 260, -1));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 260, 280));

        jLabel353.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel353.setText("�����������ڽű��ڲ����� 9100205_1");
        jPanel2.add(jLabel353, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 450, 30));

        jTabbedPane1.addTab("����н�����", jPanel2);

        ����������.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 690));

        jTabbedPane2.addTab("����������", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ����������); // NOI18N

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ҩˮ��ȴ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ҩˮ��ȴ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "���", "ҩˮ", "��ȴ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(ҩˮ��ȴ);
        if (ҩˮ��ȴ.getColumnModel().getColumnCount() > 0) {
            ҩˮ��ȴ.getColumnModel().getColumn(0).setResizable(false);
            ҩˮ��ȴ.getColumnModel().getColumn(0).setPreferredWidth(20);
            ҩˮ��ȴ.getColumnModel().getColumn(1).setResizable(false);
            ҩˮ��ȴ.getColumnModel().getColumn(1).setPreferredWidth(200);
            ҩˮ��ȴ.getColumnModel().getColumn(2).setResizable(false);
            ҩˮ��ȴ.getColumnModel().getColumn(2).setPreferredWidth(200);
        }

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 500, 620));

        jButton5.setText("ˢ��");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 270, 100, 30));
        jPanel6.add(ҩˮ��ȴʱ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 170, 110, -1));

        ҩˮ���.setEditable(false);
        jPanel6.add(ҩˮ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 70, -1));

        ҩˮ����.setEditable(false);
        jPanel6.add(ҩˮ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 170, 150, -1));

        jLabel285.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel285.setText("��ȴ��");
        jPanel6.add(jLabel285, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 150, -1, 20));

        jLabel286.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel286.setText("��ţ�");
        jPanel6.add(jLabel286, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, -1, 20));

        jLabel287.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel287.setText("ҩˮ��");
        jPanel6.add(jLabel287, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, -1, 20));

        jButton6.setText("�޸�");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 270, 100, 30));

        jTabbedPane2.addTab("ҩˮ��ȴ", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), jPanel6); // NOI18N

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 720));

        ZEVMS2��ʾ��.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ZEVMS2��ʾ��.setText("[��Ϣ]��");
        getContentPane().add(ZEVMS2��ʾ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 725, 1260, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ��ѯ��Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ѯ��Ʒ����ActionPerformed
        boolean result = this.��ѯ��Ʒ�������.getText().matches("[0-9]+");
        if (result) {
            if (Integer.parseInt(this.��ѯ��Ʒ�������.getText()) < 0) {
                ZEVMS2��ʾ��.setText("[��Ϣ]:����д��ȷ��ֵ��");
                return;
            }
            for (int i = ((DefaultTableModel) (this.���ﱬ��.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.���ﱬ��.getModel())).removeRow(i);
            }
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;

                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM drop_data WHERE itemid =  " + Integer.parseInt(this.��ѯ��Ʒ�������.getText()) + "");
                rs = ps.executeQuery();
                while (rs.next()) {

                    ((DefaultTableModel) ���ﱬ��.getModel()).insertRow(���ﱬ��.getRowCount(), new Object[]{
                        rs.getInt("id"),
                        rs.getInt("dropperid"),
                        rs.getInt("itemid"),
                        rs.getInt("chance"),
                        MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))
                    });
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
            ���ﱬ��.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int i = ���ﱬ��.getSelectedRow();
                    String a = ���ﱬ��.getValueAt(i, 0).toString();
                    String a1 = ���ﱬ��.getValueAt(i, 1).toString();
                    String a2 = ���ﱬ��.getValueAt(i, 2).toString();
                    String a3 = ���ﱬ��.getValueAt(i, 3).toString();
                    //String a4 = ���ﱬ��.getValueAt(i, 4).toString();
                    ���ﱬ�����к�.setText(a);
                    ���ﱬ��������.setText(a1);
                    ���ﱬ����Ʒ����.setText(a2);
                    ���ﱬ�ﱬ��.setText(a3);
                }
            });
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:��������Ҫ���ҵ���Ʒ���롣");
        }
    }//GEN-LAST:event_��ѯ��Ʒ����ActionPerformed

    private void ��ѯ��Ʒ�������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ѯ��Ʒ�������ActionPerformed

    }//GEN-LAST:event_��ѯ��Ʒ�������ActionPerformed

    private void ��ѯ�������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ѯ�������ActionPerformed
        boolean result = this.��ѯ����������.getText().matches("[0-9]+");
        if (result) {
            if (Integer.parseInt(this.��ѯ����������.getText()) < 0) {
                ZEVMS2��ʾ��.setText("[��Ϣ]:����д��ȷ��ֵ��");
                return;
            }
            for (int i = ((DefaultTableModel) (this.���ﱬ��.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.���ﱬ��.getModel())).removeRow(i);
            }
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;

                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM drop_data WHERE dropperid =  " + Integer.parseInt(this.��ѯ����������.getText()) + " && itemid !=0");//&& itemid !=0
                rs = ps.executeQuery();
                while (rs.next()) {

                    ((DefaultTableModel) ���ﱬ��.getModel()).insertRow(���ﱬ��.getRowCount(), new Object[]{
                        rs.getInt("id"),
                        rs.getInt("dropperid"),
                        rs.getInt("itemid"),
                        rs.getInt("chance"),
                        MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))
                    });
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
            ���ﱬ��.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int i = ���ﱬ��.getSelectedRow();
                    String a = ���ﱬ��.getValueAt(i, 0).toString();
                    String a1 = ���ﱬ��.getValueAt(i, 1).toString();
                    String a2 = ���ﱬ��.getValueAt(i, 2).toString();
                    String a3 = ���ﱬ��.getValueAt(i, 3).toString();
                    //String a4 = ���ﱬ��.getValueAt(i, 4).toString();
                    ���ﱬ�����к�.setText(a);
                    ���ﱬ��������.setText(a1);
                    ���ﱬ����Ʒ����.setText(a2);
                    ���ﱬ�ﱬ��.setText(a3);
                    //���ﱬ����Ʒ����.setText(a4);

                }
            });
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:��������Ҫ���ҵĹ�����롣");
        }
    }//GEN-LAST:event_��ѯ�������ActionPerformed

    private void ɾ��ָ���ĵ��䰴��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ��ָ���ĵ��䰴��ActionPerformed
        String ��� = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;

        boolean result = this.ɾ��ָ���ĵ���.getText().matches("[0-9]+");
        if (result == true) {
            int �̳�SN���� = Integer.parseInt(this.ɾ��ָ���ĵ���.getText());
            try {
                // for (int i = ((DefaultTableModel) (this.���ﱬ��.getModel())).getRowCount() - 1; i >= 0; i--) {
                //   ((DefaultTableModel) (this.���ﱬ��.getModel())).removeRow(i);
                //}
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM drop_data WHERE itemid = ?");
                ps1.setInt(1, �̳�SN����);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from drop_data where itemid =" + �̳�SN���� + "";
                    ps1.executeUpdate(sqlstr);
                    ZEVMS2��ʾ��.setText("[��Ϣ]:�ɹ�ɾ�� " + �̳�SN���� + " ��Ʒ��");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
            ˢ�¹��ﱬ��();
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:��������Ҫ���ҵ���Ʒ���롣");
        }

    }//GEN-LAST:event_ɾ��ָ���ĵ��䰴��ActionPerformed

    private void ɾ�����ﱬ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ�����ﱬ��ActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;

        boolean result = this.���ﱬ�����к�.getText().matches("[0-9]+");
        if (result == true) {
            int �̳�SN���� = Integer.parseInt(this.���ﱬ�����к�.getText());

            try {
                //���table����
                for (int i = ((DefaultTableModel) (this.���ﱬ��.getModel())).getRowCount() - 1; i >= 0; i--) {
                    ((DefaultTableModel) (this.���ﱬ��.getModel())).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM drop_data WHERE id = ?");
                ps1.setInt(1, �̳�SN����);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from drop_data where id =" + �̳�SN���� + "";
                    ps1.executeUpdate(sqlstr);
                    ZEVMS2��ʾ��.setText("[��Ϣ]:ɾ������ɹ���");
                    ˢ��ָ�����ﱬ��();
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ɾ�����ﱬ��ActionPerformed

    private void ��ӹ��ﱬ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ӹ��ﱬ��ActionPerformed
        boolean result1 = this.���ﱬ��������.getText().matches("[0-9]+");
        boolean result2 = this.���ﱬ����Ʒ����.getText().matches("[0-9]+");
        boolean result3 = this.���ﱬ�ﱬ��.getText().matches("[0-9]+");
        if (result1 && result2 && result3) {
            if (Integer.parseInt(this.���ﱬ��������.getText()) < 0 && Integer.parseInt(this.���ﱬ����Ʒ����.getText()) < 0 && Integer.parseInt(this.���ﱬ�ﱬ��.getText()) < 0) {
                ZEVMS2��ʾ��.setText("[��Ϣ]:����д��ȷ��ֵ��");
                return;
            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO drop_data ( dropperid,itemid,minimum_quantity,maximum_quantity,chance) VALUES ( ?, ?, ?, ?, ?)")) {
                ps.setInt(1, Integer.parseInt(this.���ﱬ��������.getText()));
                ps.setInt(2, Integer.parseInt(this.���ﱬ����Ʒ����.getText()));
                ps.setInt(3, 1);
                ps.setInt(4, 1);
                ps.setInt(5, Integer.parseInt(this.���ﱬ�ﱬ��.getText()));
                ps.executeUpdate();
                ZEVMS2��ʾ��.setText("[��Ϣ]:��ӳɹ���");
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:������<�������><��Ʒ����><��Ʒ����>�ĸ�ʽ����ӡ�");
        }
    }//GEN-LAST:event_��ӹ��ﱬ��ActionPerformed

    private void �޸Ĺ��ﱬ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸Ĺ��ﱬ��ActionPerformed
        boolean result1 = this.���ﱬ��������.getText().matches("[0-9]+");
        boolean result2 = this.���ﱬ����Ʒ����.getText().matches("[0-9]+");
        boolean result3 = this.���ﱬ�ﱬ��.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result1 && result2 && result3) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE drop_data SET dropperid = ?, itemid = ?, chance = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM drop_data WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.���ﱬ�����к�.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    String sqlString3 = null;
                    String sqlString4 = null;
                    sqlString2 = "update drop_data set dropperid='" + this.���ﱬ��������.getText() + "' where id=" + this.���ﱬ�����к�.getText() + ";";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    sqlString3 = "update drop_data set itemid='" + this.���ﱬ����Ʒ����.getText() + "' where id=" + this.���ﱬ�����к�.getText() + ";";
                    PreparedStatement itemid = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    itemid.executeUpdate(sqlString3);
                    sqlString4 = "update drop_data set chance='" + this.���ﱬ�ﱬ��.getText() + "' where id=" + this.���ﱬ�����к�.getText() + ";";
                    PreparedStatement chance = DatabaseConnection.getConnection().prepareStatement(sqlString4);
                    chance.executeUpdate(sqlString4);
                    ZEVMS2��ʾ��.setText("[��Ϣ]:�޸ĳɹ���");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:��ѡ����Ҫ�޸ĵ����ݡ�");
        }
    }//GEN-LAST:event_�޸Ĺ��ﱬ��ActionPerformed

    private void ˢ�¹��ﱬ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�¹��ﱬ��ActionPerformed
        ZEVMS2��ʾ��.setText("[��Ϣ]:ˢ�¹�����Ʒ�������ݡ�");
        ˢ�¹��ﱬ��();
    }//GEN-LAST:event_ˢ�¹��ﱬ��ActionPerformed

    private void ���籬�����к�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���籬�����к�ActionPerformed

    }//GEN-LAST:event_���籬�����к�ActionPerformed

    private void ���籬����Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���籬����Ʒ����ActionPerformed

    }//GEN-LAST:event_���籬����Ʒ����ActionPerformed

    private void ���籬�ﱬ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���籬�ﱬ��ActionPerformed

    }//GEN-LAST:event_���籬�ﱬ��ActionPerformed

    private void ������籬��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������籬��ActionPerformed
        boolean result1 = this.���籬����Ʒ����.getText().matches("[0-9]+");
        boolean result2 = this.���籬�ﱬ��.getText().matches("[0-9]+");
        if (result1 && result2) {
            if (Integer.parseInt(this.���籬����Ʒ����.getText()) < 0 && Integer.parseInt(this.���籬�ﱬ��.getText()) < 0) {
                ZEVMS2��ʾ��.setText("[��Ϣ]:����д��ȷ��ֵ��");
                return;
            }
            PreparedStatement ps1 = null;
            ResultSet rs = null;
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO drop_data_global (continent,dropType,itemid,minimum_quantity,maximum_quantity,questid,chance) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
                ps.setInt(1, 1);
                ps.setInt(2, 1);
                ps.setInt(3, Integer.parseInt(this.���籬����Ʒ����.getText()));
                ps.setInt(4, 1);
                ps.setInt(5, 1);
                ps.setInt(6, 0);
                ps.setInt(7, Integer.parseInt(this.���籬�ﱬ��.getText()));
                ps.executeUpdate();
                ZEVMS2��ʾ��.setText("[��Ϣ]:���籬����ӳɹ���");
                ˢ�����籬��();
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:������<��Ʒ����>��<��Ʒ����> ��");
        }


    }//GEN-LAST:event_������籬��ActionPerformed

    private void ɾ�����籬��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ�����籬��ActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result = this.���籬�����к�.getText().matches("[0-9]+");
        if (result == true) {
            int �̳�SN���� = Integer.parseInt(this.���籬�����к�.getText());
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM drop_data_global WHERE id = ?");
                ps1.setInt(1, �̳�SN����);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from drop_data_global where id =" + �̳�SN���� + "";
                    ps1.executeUpdate(sqlstr);
                    ZEVMS2��ʾ��.setText("[��Ϣ]:ɾ���ɹ���");
                    ˢ�����籬��();
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:��ѡ����Ҫɾ������Ʒ��");
        }
    }//GEN-LAST:event_ɾ�����籬��ActionPerformed

    private void ���籬������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���籬������ActionPerformed

    }//GEN-LAST:event_���籬������ActionPerformed

    private void �޸����籬��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸����籬��ActionPerformed

        boolean result2 = this.���籬����Ʒ����.getText().matches("[0-9]+");
        boolean result3 = this.���籬�ﱬ��.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result2 && result3) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE drop_data_global SET dropperid = ?, itemid = ?, chance = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM drop_data_global WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.���籬�����к�.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    String sqlString3 = null;
                    sqlString2 = "update drop_data_global set itemid='" + this.���籬����Ʒ����.getText() + "' where id=" + this.���籬�����к�.getText() + ";";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    sqlString3 = "update drop_data_global set chance='" + this.���籬�ﱬ��.getText() + "' where id=" + this.���籬�����к�.getText() + ";";
                    PreparedStatement itemid = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    itemid.executeUpdate(sqlString3);
                    ZEVMS2��ʾ��.setText("[��Ϣ]:�޸ĳɹ���");
                    ˢ�����籬��();
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:��ѡ����Ҫ�޸ĵ����ݡ�");
        }
    }//GEN-LAST:event_�޸����籬��ActionPerformed

    private void ˢ�����籬��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�����籬��ActionPerformed
        ZEVMS2��ʾ��.setText("[��Ϣ]:ˢ��������Ʒ�������ݡ�");
        ˢ�����籬��();
    }//GEN-LAST:event_ˢ�����籬��ActionPerformed

    private void �޸���Ʒ�������ʱ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸���Ʒ�������ʱ��ActionPerformed
        boolean result2 = this.��Ʒ�������ʱ��.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result2) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 998);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.��Ʒ�������ʱ��.getText() + "' where id = 998;";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    gui.Start.GetConfigValues();
                    ˢ����Ʒ�������ʱ��();
                    ZEVMS2��ʾ��.setText("[��Ϣ]:�޸ĳɹ���");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:��������Ҫ�޸ĵ����ݡ�");
        }
    }//GEN-LAST:event_�޸���Ʒ�������ʱ��ActionPerformed

    private void ���ҵ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ҵ���ActionPerformed
        boolean result1 = this.��Ϸ���ߴ���.getText().matches("[0-9]+");
        if (result1) {
            for (int i = ((DefaultTableModel) (this.��Ϸ����.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.��Ϸ����.getModel())).removeRow(i);
            }
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;

                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE itemid =" + Integer.parseInt(this.��Ϸ���ߴ���.getText()) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) ��Ϸ����.getModel()).insertRow(��Ϸ����.getRowCount(), new Object[]{
                        rs.getInt("characterid"),
                        NPCConversationManager.��ɫIDȡ����(rs.getInt("characterid")),
                        rs.getInt("itemid"),
                        MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))
                    });
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��������Ҫ���ҵ�<��Ʒ����>");
        }
    }//GEN-LAST:event_���ҵ���ActionPerformed

    private void ɾ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ������ActionPerformed
        String ��� = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.��Ϸ���ߴ���.getText().matches("[0-9]+");

        if (result1) {
            try {
                for (int i = ((DefaultTableModel) (this.��Ϸ����.getModel())).getRowCount() - 1; i >= 0; i--) {
                    ((DefaultTableModel) (this.��Ϸ����.getModel())).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE itemid = ?");
                ps1.setInt(1, Integer.parseInt(this.��Ϸ���ߴ���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from inventoryitems where itemid =" + Integer.parseInt(this.��Ϸ���ߴ���.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "�ɹ�ɾ�����д���Ϊ " + Integer.parseInt(this.��Ϸ���ߴ���.getText()) + " ��Ʒ��");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "����������Ҫɾ����<��Ʒ����>");
        }
    }//GEN-LAST:event_ɾ������ActionPerformed

    private void ���ҵ���1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ҵ���1ActionPerformed
        boolean result1 = this.��Ϸ���ߴ���.getText().matches("[0-9]+");
        if (result1) {
            for (int i = ((DefaultTableModel) (this.��Ϸ����.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.��Ϸ����.getModel())).removeRow(i);
            }
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;

                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM auctionitems WHERE itemid =" + Integer.parseInt(this.��Ϸ���ߴ���.getText()) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) ��Ϸ����.getModel()).insertRow(��Ϸ����.getRowCount(), new Object[]{
                        rs.getInt("characterid"),
                        NPCConversationManager.��ɫIDȡ����(rs.getInt("characterid")),
                        rs.getInt("itemid"),
                        MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))
                    });
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��������Ҫ���ҵ�<��Ʒ����>");
        }
    }//GEN-LAST:event_���ҵ���1ActionPerformed

    private void ɾ������1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ������1ActionPerformed
        String ��� = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.��Ϸ���ߴ���.getText().matches("[0-9]+");
        if (result1) {
            try {
                for (int i = ((DefaultTableModel) (this.��Ϸ����.getModel())).getRowCount() - 1; i >= 0; i--) {
                    ((DefaultTableModel) (this.��Ϸ����.getModel())).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM auctionitems WHERE itemid = ?");
                ps1.setInt(1, Integer.parseInt(this.��Ϸ���ߴ���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from auctionitems where itemid =" + Integer.parseInt(this.��Ϸ���ߴ���.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "�ɹ�ɾ�����д���Ϊ " + Integer.parseInt(this.��Ϸ���ߴ���.getText()) + " ��Ʒ��");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "����������Ҫɾ����<��Ʒ����>");
        }
    }//GEN-LAST:event_ɾ������1ActionPerformed

    private void ���ҵ���2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ҵ���2ActionPerformed
        boolean result1 = this.��Ϸ���ߴ���.getText().matches("[0-9]+");
        if (result1) {
            for (int i = ((DefaultTableModel) (this.��Ϸ����.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.��Ϸ����.getModel())).removeRow(i);
            }
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM auctionitems1 WHERE itemid =" + Integer.parseInt(this.��Ϸ���ߴ���.getText()) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) ��Ϸ����.getModel()).insertRow(��Ϸ����.getRowCount(), new Object[]{
                        rs.getInt("characterid"),
                        NPCConversationManager.��ɫIDȡ����(rs.getInt("characterid")),
                        rs.getInt("itemid"),
                        MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))
                    });
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��������Ҫ���ҵ�<��Ʒ����>");
        }
    }//GEN-LAST:event_���ҵ���2ActionPerformed

    private void ɾ������2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ������2ActionPerformed
        String ��� = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.��Ϸ���ߴ���.getText().matches("[0-9]+");

        if (result1) {
            try {
                for (int i = ((DefaultTableModel) (this.��Ϸ����.getModel())).getRowCount() - 1; i >= 0; i--) {
                    ((DefaultTableModel) (this.��Ϸ����.getModel())).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM auctionitems1 WHERE itemid = ?");
                ps1.setInt(1, Integer.parseInt(this.��Ϸ���ߴ���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from auctionitems1 where itemid =" + Integer.parseInt(this.��Ϸ���ߴ���.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "�ɹ�ɾ�����д���Ϊ " + Integer.parseInt(this.��Ϸ���ߴ���.getText()) + " ��Ʒ��");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "����������Ҫɾ����<��Ʒ����>");
        }
    }//GEN-LAST:event_ɾ������2ActionPerformed

    private void ���ҵ���3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ҵ���3ActionPerformed
        boolean result1 = this.��Ϸ���ߴ���.getText().matches("[0-9]+");
        if (result1) {
            for (int i = ((DefaultTableModel) (this.��Ϸ����.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.��Ϸ����.getModel())).removeRow(i);
            }
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM bank_item1 WHERE itemid =" + Integer.parseInt(this.��Ϸ���ߴ���.getText()) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) ��Ϸ����.getModel()).insertRow(��Ϸ����.getRowCount(), new Object[]{
                        rs.getInt("cid"),
                        NPCConversationManager.��ɫIDȡ����(rs.getInt("cid")),
                        rs.getInt("itemid"),
                        MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))
                    });
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��������Ҫ���ҵ�<��Ʒ����>");
        }
    }//GEN-LAST:event_���ҵ���3ActionPerformed

    private void ɾ������3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ������3ActionPerformed
        String ��� = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.��Ϸ���ߴ���.getText().matches("[0-9]+");
        if (result1) {
            try {
                for (int i = ((DefaultTableModel) (this.��Ϸ����.getModel())).getRowCount() - 1; i >= 0; i--) {
                    ((DefaultTableModel) (this.��Ϸ����.getModel())).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM bank_item1 WHERE itemid = ?");
                ps1.setInt(1, Integer.parseInt(this.��Ϸ���ߴ���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from bank_item1 where itemid =" + Integer.parseInt(this.��Ϸ���ߴ���.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "�ɹ�ɾ�����д���Ϊ " + Integer.parseInt(this.��Ϸ���ߴ���.getText()) + " ��Ʒ��");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "����������Ҫɾ����<��Ʒ����>");
        }
    }//GEN-LAST:event_ɾ������3ActionPerformed

    private void ���ҵ���4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ҵ���4ActionPerformed
        boolean result1 = this.��Ϸ���ߴ���.getText().matches("[0-9]+");
        if (result1) {
            for (int i = ((DefaultTableModel) (this.��Ϸ����.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.��Ϸ����.getModel())).removeRow(i);
            }
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;

                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM bank_item WHERE itemid =" + Integer.parseInt(this.��Ϸ���ߴ���.getText()) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) ��Ϸ����.getModel()).insertRow(��Ϸ����.getRowCount(), new Object[]{
                        rs.getInt("cid"),
                        NPCConversationManager.��ɫIDȡ����(rs.getInt("cid")),
                        rs.getInt("itemid"),
                        MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))
                    });
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��������Ҫ���ҵ�<��Ʒ����>");
        }
    }//GEN-LAST:event_���ҵ���4ActionPerformed

    private void ɾ������4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ������4ActionPerformed
        String ��� = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.��Ϸ���ߴ���.getText().matches("[0-9]+");

        if (result1) {
            try {
                for (int i = ((DefaultTableModel) (this.��Ϸ����.getModel())).getRowCount() - 1; i >= 0; i--) {
                    ((DefaultTableModel) (this.��Ϸ����.getModel())).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM bank_item WHERE itemid = ?");
                ps1.setInt(1, Integer.parseInt(this.��Ϸ���ߴ���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from bank_item where itemid =" + Integer.parseInt(this.��Ϸ���ߴ���.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "�ɹ�ɾ�����д���Ϊ " + Integer.parseInt(this.��Ϸ���ߴ���.getText()) + " ��Ʒ��");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "����������Ҫɾ����<��Ʒ����>");
        }
    }//GEN-LAST:event_ɾ������4ActionPerformed

    private void �޸ĵ�����ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸ĵ�����ƷActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.������Ʒ���.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE ������Ʒ SET itemid = ?,chance = ?WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM ������Ʒ WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.������Ʒ���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    String sqlString2 = null;
                    sqlString1 = "update ������Ʒ set itemid='" + this.������Ʒ����.getText() + "' where id=" + this.������Ʒ���.getText() + ";";
                    PreparedStatement name = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    name.executeUpdate(sqlString1);
                    sqlString2 = "update ������Ʒ set chance='" + this.������Ʒ����.getText() + "' where id=" + this.������Ʒ���.getText() + ";";
                    PreparedStatement level = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    level.executeUpdate(sqlString2);
                    ZEVMS2��ʾ��.setText("[��Ϣ]:�޸ĵ�����Ʒ�ɹ���");
                    ˢ�µ���();
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:����<��Ʒ����><����>��");
        }
    }//GEN-LAST:event_�޸ĵ�����ƷActionPerformed

    private void ˢ�µ�����ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�µ�����ƷActionPerformed
        ZEVMS2��ʾ��.setText("[��Ϣ]:ˢ�µ��㽱���ɹ���");
        ˢ�µ���();
    }//GEN-LAST:event_ˢ�µ�����ƷActionPerformed

    private void ������Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������Ʒ����ActionPerformed

    }//GEN-LAST:event_������Ʒ����ActionPerformed

    private void ����������ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����������ƷActionPerformed
        boolean result1 = this.������Ʒ����.getText().matches("[0-9]+");
        boolean result2 = this.������Ʒ����.getText().matches("[0-9]+");

        if (result1 && result2) {
            if (Integer.parseInt(this.������Ʒ����.getText()) < 0 && Integer.parseInt(this.������Ʒ����.getText()) < 0) {
                ZEVMS2��ʾ��.setText("[��Ϣ]:����д��ȷ��ֵ��");
                return;
            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO ������Ʒ (itemid, chance ,expiration) VALUES (?, ?, ?)")) {
                ps.setInt(1, Integer.parseInt(this.������Ʒ����.getText()));
                ps.setInt(2, Integer.parseInt(this.������Ʒ����.getText()));
                ps.setInt(3, 1);
                ps.executeUpdate();
                ZEVMS2��ʾ��.setText("[��Ϣ]:�������㽱���ɹ���");
                ˢ�µ���();
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:������<��Ʒ����><����>��");
        }
    }//GEN-LAST:event_����������ƷActionPerformed
    private void ˢ�µ���() {
        for (int i = ((DefaultTableModel) (this.������Ʒ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.������Ʒ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM ������Ʒ");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ������Ʒ.getModel()).insertRow(������Ʒ.getRowCount(), new Object[]{
                    rs.getInt("id"),
                    rs.getInt("itemid"),
                    rs.getInt("chance"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
        }
        ������Ʒ.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ������Ʒ.getSelectedRow();
                String a = ������Ʒ.getValueAt(i, 0).toString();
                String a1 = ������Ʒ.getValueAt(i, 1).toString();
                String a2 = ������Ʒ.getValueAt(i, 2).toString();
                String a3 = ������Ʒ.getValueAt(i, 3).toString();
                ������Ʒ���.setText(a);
                ������Ʒ����.setText(a1);
                ������Ʒ����.setText(a2);
                ������Ʒ����.setText(a3);
            }
        });
    }
    private void ɾ��������ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ��������ƷActionPerformed
        String ��� = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.������Ʒ���.getText().matches("[0-9]+");
        if (result1) {
            try {
                //���table����
                for (int i = ((DefaultTableModel) (this.������Ʒ.getModel())).getRowCount() - 1; i >= 0; i--) {
                    ((DefaultTableModel) (this.������Ʒ.getModel())).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM ������Ʒ WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.������Ʒ���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from ������Ʒ where id =" + Integer.parseInt(this.������Ʒ���.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ZEVMS2��ʾ��.setText("[��Ϣ]:ɾ�����㽱����Ʒ�ɹ���");
                    ˢ�µ���();
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:��ѡ����Ҫɾ���ĵ�����Ʒ��");
        }
    }//GEN-LAST:event_ɾ��������ƷActionPerformed

    private void ������Ʒ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������Ʒ���ActionPerformed

    }//GEN-LAST:event_������Ʒ���ActionPerformed

    private void �����ڿ���ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����ڿ���ƷActionPerformed
        boolean result1 = this.�����ڿ���Ʒ.getText().matches("[0-9]+");
        boolean result2 = this.�ڿ���Ʒ����.getText().matches("[0-9]+");
        if (result1 && result2) {
            if (Integer.parseInt(this.�����ڿ���Ʒ.getText()) < 0 && Integer.parseInt(this.�ڿ���Ʒ����.getText()) < 0) {
                ZEVMS2��ʾ��.setText("[��Ϣ]:����д��ȷ��ֵ��");
                return;
            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO reactordrops (reactorid ,itemid ,chance ,questid ) VALUES (?, ?, ?, ?)")) {
                ps.setInt(1, 2112012);
                ps.setInt(2, Integer.parseInt(this.�����ڿ���Ʒ.getText()));
                ps.setInt(3, Integer.parseInt(this.�ڿ���Ʒ����.getText()));
                ps.setInt(4, -1);
                ps.executeUpdate();
                ZEVMS2��ʾ��.setText("[��Ϣ]:�ڿ��������ɹ���");
                ˢ���ڿ�();
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:��������Ʒ���룬�����ʡ�");
        }
    }//GEN-LAST:event_�����ڿ���ƷActionPerformed
    private void ˢ���ڿ�() {
        for (int i = ((DefaultTableModel) (this.�ڿ�Ӧ��.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�ڿ�Ӧ��.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM reactordrops WHERE reactorid = 2112012");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) �ڿ�Ӧ��.getModel()).insertRow(�ڿ�Ӧ��.getRowCount(), new Object[]{rs.getInt("reactordropid"), rs.getInt("itemid"), rs.getInt("chance"), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
        }
        �ڿ�Ӧ��.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = �ڿ�Ӧ��.getSelectedRow();
                String a = �ڿ�Ӧ��.getValueAt(i, 0).toString();
                String a1 = �ڿ�Ӧ��.getValueAt(i, 1).toString();
                String a2 = �ڿ�Ӧ��.getValueAt(i, 2).toString();
                String a3 = �ڿ�Ӧ��.getValueAt(i, 3).toString();
                �ڿ����к�.setText(a);
                �����ڿ���Ʒ.setText(a1);
                �ڿ���Ʒ����.setText(a2);
                ��Ʒ����.setText(a3);
            }
        });

    }

    public void ��ѯ�̵�() {

        boolean result = this.��ѯ�̵�.getText().matches("[0-9]+");
        if (result) {
            if (Integer.parseInt(this.��ѯ�̵�.getText()) < 0) {
                ZEVMS2��ʾ��.setText("[��Ϣ]:����д��ȷ��ֵ��");
                return;
            }
            for (int i = ((DefaultTableModel) (this.��Ϸ�̵�2.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.��Ϸ�̵�2.getModel())).removeRow(i);
            }
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM shopitems WHERE shopid = " + Integer.parseInt(this.��ѯ�̵�.getText()) + " ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) ��Ϸ�̵�2.getModel()).insertRow(��Ϸ�̵�2.getRowCount(), new Object[]{
                        rs.getInt("shopitemid"),
                        rs.getInt("shopid"),
                        rs.getInt("itemid"),
                        rs.getInt("price"),
                        MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))
                    });
                }
                ZEVMS2��ʾ��.setText("[��Ϣ]:�̳���Ʒ��ѯ�ɹ���");
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
            ��Ϸ�̵�2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int i = ��Ϸ�̵�2.getSelectedRow();
                    String a = ��Ϸ�̵�2.getValueAt(i, 0).toString();
                    String a1 = ��Ϸ�̵�2.getValueAt(i, 1).toString();
                    String a2 = ��Ϸ�̵�2.getValueAt(i, 2).toString();
                    String a3 = ��Ϸ�̵�2.getValueAt(i, 3).toString();
                    //String a4 = ��Ϸ�̵�2.getValueAt(i, 4).toString();
                    ��Ʒ���.setText(a);
                    �̵����.setText(a1);
                    ��Ʒ��Ʒ����.setText(a2);
                    ��Ʒ�ۼ۽��.setText(a3);
                    // ��Ʒ����.setText(a4);
                }
            });
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:����������Ҫ��ѯ���̵�ID��");
        }
    }

    private void ˢ�²�ҩ() {
        for (int i = ((DefaultTableModel) (this.�ڿ�Ӧ��.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�ڿ�Ӧ��.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM reactordrops WHERE reactorid = 1072000");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) �ڿ�Ӧ��.getModel()).insertRow(�ڿ�Ӧ��.getRowCount(), new Object[]{rs.getInt("reactordropid"), rs.getInt("itemid"), rs.getInt("chance"), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
        }
        �ڿ�Ӧ��.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = �ڿ�Ӧ��.getSelectedRow();
                String a = �ڿ�Ӧ��.getValueAt(i, 0).toString();
                String a1 = �ڿ�Ӧ��.getValueAt(i, 1).toString();
                String a2 = �ڿ�Ӧ��.getValueAt(i, 2).toString();
                String a3 = �ڿ�Ӧ��.getValueAt(i, 3).toString();
                �ڿ����к�.setText(a);
                �����ڿ���Ʒ.setText(a1);
                �ڿ���Ʒ����.setText(a2);
                ��Ʒ����.setText(a3);
            }
        });

    }
    private void �޸��ڿ���ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸��ڿ���ƷActionPerformed
        boolean result1 = this.�����ڿ���Ʒ.getText().matches("[0-9]+");
        boolean result2 = this.�ڿ���Ʒ����.getText().matches("[0-9]+");
        if (result1 && result2) {
            PreparedStatement ps = null;
            PreparedStatement ps1 = null;
            ResultSet rs = null;
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE reactordrops SET itemid = ?,chance = ?WHERE reactordropid = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM reactordrops WHERE reactordropid = ?");
                ps1.setInt(1, Integer.parseInt(this.�ڿ����к�.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    String sqlString2 = null;
                    sqlString1 = "update reactordrops set itemid='" + this.�����ڿ���Ʒ.getText() + "' where reactordropid=" + this.�ڿ����к�.getText() + ";";
                    PreparedStatement name = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    name.executeUpdate(sqlString1);
                    sqlString2 = "update reactordrops set chance='" + this.�ڿ���Ʒ����.getText() + "' where reactordropid=" + this.�ڿ����к�.getText() + ";";
                    PreparedStatement level = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    level.executeUpdate(sqlString2);
                    ZEVMS2��ʾ��.setText("[��Ϣ]:�޸ĳɹ���");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:��ѡ����Ҫ�޸ĵ���Ʒ��");
        }
    }//GEN-LAST:event_�޸��ڿ���ƷActionPerformed

    private void ɾ���ڿ���Ʒ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ���ڿ���Ʒ1ActionPerformed

        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.�ڿ����к�.getText().matches("[0-9]+");
        if (result1) {
            try {
                for (int i = ((DefaultTableModel) (this.�ڿ�Ӧ��.getModel())).getRowCount() - 1; i >= 0; i--) {
                    ((DefaultTableModel) (this.�ڿ�Ӧ��.getModel())).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM reactordrops WHERE reactordropid = ?");
                ps1.setInt(1, Integer.parseInt(this.�ڿ����к�.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from reactordrops where reactordropid =" + Integer.parseInt(this.�ڿ����к�.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ZEVMS2��ʾ��.setText("[��Ϣ]:ɾ���ɹ���");
                    for (int i = ((DefaultTableModel) (this.�ڿ�Ӧ��.getModel())).getRowCount() - 1; i >= 0; i--) {
                        ((DefaultTableModel) (this.�ڿ�Ӧ��.getModel())).removeRow(i);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:��ѡ����Ҫɾ������Ʒ��");
        }
    }//GEN-LAST:event_ɾ���ڿ���Ʒ1ActionPerformed

    private void �����ڿ���Ʒ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����ڿ���Ʒ1ActionPerformed

    }//GEN-LAST:event_�����ڿ���Ʒ1ActionPerformed

    private void �޸��ڿ���Ʒ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸��ڿ���Ʒ1ActionPerformed

    }//GEN-LAST:event_�޸��ڿ���Ʒ1ActionPerformed

    private void ɾ���ڿ���Ʒ2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ���ڿ���Ʒ2ActionPerformed

    }//GEN-LAST:event_ɾ���ڿ���Ʒ2ActionPerformed

    private void �����ڿ���Ʒ2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����ڿ���Ʒ2ActionPerformed
        boolean result1 = this.�����ڿ���Ʒ.getText().matches("[0-9]+");
        boolean result2 = this.�ڿ���Ʒ����.getText().matches("[0-9]+");
        if (result1 && result2) {
            if (Integer.parseInt(this.�����ڿ���Ʒ.getText()) < 0 && Integer.parseInt(this.�ڿ���Ʒ����.getText()) < 0) {
                ZEVMS2��ʾ��.setText("[��Ϣ]:����д��ȷ��ֵ��");
                return;
            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO reactordrops (reactorid ,itemid ,chance ,questid ) VALUES (?, ?, ?, ?)")) {
                ps.setInt(1, 1072000);
                ps.setInt(2, Integer.parseInt(this.�����ڿ���Ʒ.getText()));
                ps.setInt(3, Integer.parseInt(this.�ڿ���Ʒ����.getText()));
                ps.setInt(4, -1);
                ps.executeUpdate();
                ZEVMS2��ʾ��.setText("[��Ϣ]:�ڿ��������ɹ���");
                ˢ�²�ҩ();
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:��������Ʒ���룬�����ʡ�");
        }
    }//GEN-LAST:event_�����ڿ���Ʒ2ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        ZEVMS2��ʾ��.setText("[��Ϣ]:�ڿ����ؿ�ʼ��");
        ReactorScriptManager.getInstance().clearDrops();
        ZEVMS2��ʾ��.setText("[��Ϣ]:�ڿ����سɹ���");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void ˢ���ڿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ���ڿ���ActionPerformed
        ZEVMS2��ʾ��.setText("[��Ϣ]:ˢ���ڿ�����");
        ˢ���ڿ�();
    }//GEN-LAST:event_ˢ���ڿ���ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        ZEVMS2��ʾ��.setText("[��Ϣ]:��ҩ���ؿ�ʼ��");
        ReactorScriptManager.getInstance().clearDrops();
        ZEVMS2��ʾ��.setText("[��Ϣ]:��ҩ���سɹ���");
    }//GEN-LAST:event_jButton16ActionPerformed

    private void ˢ���ڿ���1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ���ڿ���1ActionPerformed
        ZEVMS2��ʾ��.setText("[��Ϣ]:ˢ�²�ҩ������");
        ˢ�²�ҩ();
    }//GEN-LAST:event_ˢ���ڿ���1ActionPerformed

    private void ��ѯ�̵�2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ѯ�̵�2ActionPerformed
        ��ѯ�̵�();
    }//GEN-LAST:event_��ѯ�̵�2ActionPerformed

    private void ɾ����ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ����ƷActionPerformed

        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result = this.��Ʒ���.getText().matches("[0-9]+");
        if (result == true) {
            int �̳�SN���� = Integer.parseInt(this.��Ʒ���.getText());
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM shopitems WHERE shopitemid = ?");
                ps1.setInt(1, �̳�SN����);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from shopitems where shopitemid =" + �̳�SN���� + "";
                    ps1.executeUpdate(sqlstr);
                    ��ѯ�̵�();
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
            ZEVMS2��ʾ��.setText("[��Ϣ]:ɾ���̵���Ʒ�ɹ���");
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:��ѡ����Ҫɾ������Ʒ��");
        }
    }//GEN-LAST:event_ɾ����ƷActionPerformed

    private void ������ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������ƷActionPerformed

        boolean result = this.��Ʒ��Ʒ����.getText().matches("[0-9]+");
        boolean result1 = this.�̵����.getText().matches("[0-9]+");
        boolean result2 = this.��Ʒ�ۼ۽��.getText().matches("[0-9]+");

        if (result && result1 && result2) {
            if (Integer.parseInt(this.�̵����.getText()) < 0 && Integer.parseInt(this.��Ʒ��Ʒ����.getText()) < 0 && Integer.parseInt(this.��Ʒ�ۼ۽��.getText()) < 0) {
                ZEVMS2��ʾ��.setText("[��Ϣ]:����д��ȷ��ֵ��");
                return;
            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO shopitems (shopid ,itemid ,price ,pitch ,position ,reqitem ,reqitemq) VALUES ( ?, ?, ?, ?, ?, ?, ?)")) {
                ps.setInt(1, Integer.parseInt(this.�̵����.getText()));
                ps.setInt(2, Integer.parseInt(this.��Ʒ��Ʒ����.getText()));
                ps.setInt(3, Integer.parseInt(this.��Ʒ�ۼ۽��.getText()));
                ps.setInt(4, 0);
                ps.setInt(5, 0);
                ps.setInt(6, 0);
                ps.setInt(7, 0);
                ps.executeUpdate();
                ��ѯ�̵�();
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
            ZEVMS2��ʾ��.setText("[��Ϣ]:�����̵���Ʒ�ɹ���");
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:����<�̵�ID><��Ʒ����><�ۼ�>��");
        }
    }//GEN-LAST:event_������ƷActionPerformed

    private void �̵����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�̵����ActionPerformed

    }//GEN-LAST:event_�̵����ActionPerformed

    private void �޸���ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸���ƷActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result = this.��Ʒ��Ʒ����.getText().matches("[0-9]+");
        boolean result1 = this.�̵����.getText().matches("[0-9]+");
        boolean result2 = this.��Ʒ�ۼ۽��.getText().matches("[0-9]+");

        if (result && result1 && result2) {
            if (Integer.parseInt(this.�̵����.getText()) < 0 && Integer.parseInt(this.��Ʒ��Ʒ����.getText()) < 0 && Integer.parseInt(this.��Ʒ�ۼ۽��.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "����д��ȷ��ֵ");
            }
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE shopitems SET itemid = ?,price = ?,shopid = ?WHERE shopitemid = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM shopitems WHERE shopitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.��Ʒ���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    String sqlString2 = null;
                    String sqlString3 = null;
                    sqlString1 = "update shopitems set itemid='" + this.��Ʒ��Ʒ����.getText() + "' where shopitemid=" + this.��Ʒ���.getText() + ";";
                    PreparedStatement itemid = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    itemid.executeUpdate(sqlString1);

                    sqlString2 = "update shopitems set price='" + this.��Ʒ�ۼ۽��.getText() + "' where shopitemid=" + this.��Ʒ���.getText() + ";";
                    PreparedStatement price = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    price.executeUpdate(sqlString2);

                    sqlString3 = "update shopitems set shopid='" + this.�̵����.getText() + "' where shopitemid=" + this.��Ʒ���.getText() + ";";
                    PreparedStatement shopid = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    shopid.executeUpdate(sqlString3);

                    ��ѯ�̵�();
                }
                ZEVMS2��ʾ��.setText("[��Ϣ]:�̵���Ʒ�޸ĳɹ���");
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:ѡ����Ҫ�޸ĵ���Ʒ,����д<�̵�ID><��Ʒ����><�ۼ۽��>��");
        }
    }//GEN-LAST:event_�޸���ƷActionPerformed

    private void ��Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ʒ����ActionPerformed

    }//GEN-LAST:event_��Ʒ����ActionPerformed
    public void ˢ�·�Ӧ��() {
        for (int i = ((DefaultTableModel) (this.��Ӧ��.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��Ӧ��.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM reactordrops ");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ��Ӧ��.getModel()).insertRow(��Ӧ��.getRowCount(), new Object[]{rs.getInt("reactordropid"), rs.getInt("reactorid"), rs.getInt("itemid"), rs.getInt("chance"), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��Ӧ��.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��Ӧ��.getSelectedRow();
                String a = ��Ӧ��.getValueAt(i, 0).toString();
                String a1 = ��Ӧ��.getValueAt(i, 1).toString();
                String a2 = ��Ӧ��.getValueAt(i, 2).toString();
                String a3 = ��Ӧ��.getValueAt(i, 3).toString();
                ��Ӧ�����к�.setText(a);
                ��Ӧ�Ѵ���.setText(a1);
                ��Ӧ����Ʒ.setText(a2);
                ��Ӧ�Ѹ���.setText(a3);
            }
        });
    }
    private void ɾ����Ӧ����ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ����Ӧ����ƷActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.ɾ����Ӧ����Ʒ����.getText().matches("[0-9]+");

        if (result1) {
            if (Integer.parseInt(this.ɾ����Ӧ����Ʒ����.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "����д��ȷ��ֵ");
            }
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM reactordrops WHERE itemid = ?");
                ps1.setInt(1, Integer.parseInt(this.ɾ����Ӧ����Ʒ����.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from reactordrops where itemid =" + Integer.parseInt(this.ɾ����Ӧ����Ʒ����.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "�ɹ�ɾ�� " + Integer.parseInt(this.ɾ����Ӧ����Ʒ����.getText()) + " ��Ʒ�����غ���Ч��");
                    ˢ�·�Ӧ��();

                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��������Ҫɾ���ķ�Ӧ�Ѵ��� ");
        }
    }//GEN-LAST:event_ɾ����Ӧ����ƷActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        ˢ�·�Ӧ��();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void ��Ӧ�Ѹ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ӧ�Ѹ���ActionPerformed

    }//GEN-LAST:event_��Ӧ�Ѹ���ActionPerformed

    private void ������Ӧ����ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������Ӧ����ƷActionPerformed

        boolean result2 = this.��Ӧ�Ѵ���.getText().matches("[0-9]+");

        if (result2) {
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO reactordrops ( reactorid ,itemid ,chance ,questid ) VALUES ( ?, ?, ?, ?)")) {
                ps.setInt(1, Integer.parseInt(this.��Ӧ�Ѵ���.getText()));
                ps.setInt(2, Integer.parseInt(this.��Ӧ����Ʒ.getText()));
                ps.setInt(3, Integer.parseInt(this.��Ӧ�Ѹ���.getText()));
                ps.setInt(4, -1);
                ps.executeUpdate();
                ˢ�·�Ӧ��();
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "�����뷴Ӧ�Ѵ��룬��Ʒ���룬������� ");
        }
    }//GEN-LAST:event_������Ӧ����ƷActionPerformed

    private void ɾ����Ӧ����Ʒ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ����Ӧ����Ʒ1ActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.��Ӧ�����к�.getText().matches("[0-9]+");

        if (result1) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM reactordrops WHERE reactordropid = ?");
                ps1.setInt(1, Integer.parseInt(this.��Ӧ�����к�.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from reactordrops where reactordropid =" + Integer.parseInt(this.��Ӧ�����к�.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ˢ�·�Ӧ��();
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ������Ʒ ");
        }
    }//GEN-LAST:event_ɾ����Ӧ����Ʒ1ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        boolean result2 = this.���ҷ�Ӧ�ѵ���.getText().matches("[0-9]+");
        if (result2) {
            for (int i = ((DefaultTableModel) (this.��Ӧ��.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.��Ӧ��.getModel())).removeRow(i);
            }
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM reactordrops WHERE reactorid = " + Integer.parseInt(���ҷ�Ӧ�ѵ���.getText()));
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) ��Ӧ��.getModel()).insertRow(��Ӧ��.getRowCount(), new Object[]{rs.getInt("reactordropid"), rs.getInt("reactorid"), rs.getInt("itemid"), rs.getInt("chance"), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��������Ҫ���ҵķ�Ӧ�� ");
        }
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        boolean result2 = this.���ҷ�Ӧ�ѵ���.getText().matches("[0-9]+");
        if (result2) {
            for (int i = ((DefaultTableModel) (this.��Ӧ��.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.��Ӧ��.getModel())).removeRow(i);
            }
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;

                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM reactordrops WHERE itemid = " + Integer.parseInt(������Ʒ.getText()));
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) ��Ӧ��.getModel()).insertRow(��Ӧ��.getRowCount(), new Object[]{
                        rs.getInt("reactordropid"),
                        rs.getInt("reactorid"),
                        rs.getInt("itemid"),
                        rs.getInt("chance"),
                        MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))
                    });

                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��������Ҫ���ҵ���Ʒ���� ");
        }
    }//GEN-LAST:event_jButton37ActionPerformed

    private void �޸ķ�Ӧ����ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸ķ�Ӧ����ƷActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result = this.��Ӧ�Ѵ���.getText().matches("[0-9]+");
        boolean result1 = this.��Ӧ����Ʒ.getText().matches("[0-9]+");
        boolean result2 = this.��Ӧ�Ѹ���.getText().matches("[0-9]+");

        if (result && result1 && result2) {
            if (Integer.parseInt(this.��Ӧ�Ѵ���.getText()) < 0 && Integer.parseInt(this.��Ӧ����Ʒ.getText()) < 0 && Integer.parseInt(this.��Ӧ�Ѹ���.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "����д��ȷ��ֵ");
            }
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE reactordrops SET reactorid = ?,itemid = ?,chance = ?WHERE reactordropid = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM reactordrops WHERE reactordropid = ?");
                ps1.setInt(1, Integer.parseInt(this.��Ӧ�����к�.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    String sqlString2 = null;
                    String sqlString3 = null;
                    sqlString1 = "update reactordrops set reactorid='" + this.��Ӧ�Ѵ���.getText() + "' where reactordropid=" + this.��Ӧ�����к�.getText() + ";";
                    PreparedStatement itemid = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    itemid.executeUpdate(sqlString1);

                    sqlString2 = "update reactordrops set itemid='" + this.��Ӧ����Ʒ.getText() + "' where reactordropid=" + this.��Ӧ�����к�.getText() + ";";
                    PreparedStatement price = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    price.executeUpdate(sqlString2);

                    sqlString3 = "update reactordrops set chance='" + this.��Ӧ�Ѹ���.getText() + "' where reactordropid=" + this.��Ӧ�����к�.getText() + ";";
                    PreparedStatement shopid = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    shopid.executeUpdate(sqlString3);

                    ˢ�·�Ӧ��();
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�޸ĵ�����");
        }
    }//GEN-LAST:event_�޸ķ�Ӧ����ƷActionPerformed

    private void �������޸�����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�������޸�����ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET (name = ? )WHERE id = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
            ps1.setInt(1, 1);
            rs = ps1.executeQuery();
            if (rs.next()) {
                String a1 = null;
                String a2 = null;
                String a3 = null;
                String a4 = null;
                a1 = "update configvalues set Val='" + this.�򶹶�����.getText() + "' where id=20000;";
                PreparedStatement aa1 = DatabaseConnection.getConnection().prepareStatement(a1);
                aa1.executeUpdate(a1);

                a2 = "update configvalues set Val='" + this.����������.getText() + "' where id=20001;";
                PreparedStatement aa2 = DatabaseConnection.getConnection().prepareStatement(a2);
                aa2.executeUpdate(a2);

                a3 = "update configvalues set Val='" + this.����������.getText() + "' where id=20002;";
                PreparedStatement aa3 = DatabaseConnection.getConnection().prepareStatement(a3);
                aa3.executeUpdate(a3);

                a4 = "update configvalues set Val='" + this.����ϡ����.getText() + "' where id=20003;";
                PreparedStatement aa4 = DatabaseConnection.getConnection().prepareStatement(a4);
                aa4.executeUpdate(a4);
                gui.Start.GetConfigValues();
                ZEVMS2��ʾ��.setText("[��Ϣ]:�����������޸ĳɹ���");
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_�������޸�����ActionPerformed

    private void �������޸�����1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�������޸�����1ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET (name = ? )WHERE id = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
            ps1.setInt(1, 1);
            rs = ps1.executeQuery();
            if (rs.next()) {
                String a1 = null;
                String a2 = null;
                String a3 = null;
                a1 = "update configvalues set Val='" + this.�춹���ӿ�����.getText() + "' where id=20010;";
                PreparedStatement aa1 = DatabaseConnection.getConnection().prepareStatement(a1);
                aa1.executeUpdate(a1);

                a2 = "update configvalues set Val='" + this.�������ӿ�����.getText() + "' where id=20011;";
                PreparedStatement aa2 = DatabaseConnection.getConnection().prepareStatement(a2);
                aa2.executeUpdate(a2);

                a3 = "update configvalues set Val='" + this.�̶����ӿ�����.getText() + "' where id=20012;";
                PreparedStatement aa3 = DatabaseConnection.getConnection().prepareStatement(a3);
                aa3.executeUpdate(a3);

                gui.Start.GetConfigValues();
                ZEVMS2��ʾ��.setText("[��Ϣ]:���������������н��������޸ĳɹ���");
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_�������޸�����1ActionPerformed

    private void �������޸�����2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�������޸�����2ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET (name = ? )WHERE id = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
            ps1.setInt(1, 1);
            rs = ps1.executeQuery();
            if (rs.next()) {
                String a1 = null;
                a1 = "update configvalues set Val='" + this.��������.getText() + "' where id=20005;";
                PreparedStatement aa1 = DatabaseConnection.getConnection().prepareStatement(a1);
                aa1.executeUpdate(a1);

                ZEVMS2��ʾ��.setText("[��Ϣ]:�����������޸ĳɹ���");
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_�������޸�����2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ˢ��ҩˮ��ȴ();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.ҩˮ��ȴʱ��.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ?WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.ҩˮ���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update configvalues set Val='" + this.ҩˮ��ȴʱ��.getText() + "' where id=" + this.ҩˮ���.getText() + ";";
                    PreparedStatement name = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    name.executeUpdate(sqlString1);
                    ZEVMS2��ʾ��.setText("[��Ϣ]:�޸���ȴ�ɹ���");
                    ˢ��ҩˮ��ȴ();
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void ˢ�¹��￨ƬActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�¹��￨ƬActionPerformed
        ˢ�¹��￨Ƭ();
    }//GEN-LAST:event_ˢ�¹��￨ƬActionPerformed

    private void �޸���Ʒ�������ʱ��1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸���Ʒ�������ʱ��1ActionPerformed
        boolean result2 = this.��ͼ��Ʒ����.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result2) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 997);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.��ͼ��Ʒ����.getText() + "' where id = 997;";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    gui.Start.GetConfigValues();
                    ˢ�µ�ͼ��Ʒ����();
                    ZEVMS2��ʾ��.setText("[��Ϣ]:�޸ĳɹ���");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:��������Ҫ�޸ĵ����ݡ�");
        }
    }//GEN-LAST:event_�޸���Ʒ�������ʱ��1ActionPerformed

    private void �޸���Ʒ�������ʱ��2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸���Ʒ�������ʱ��2ActionPerformed
        boolean result2 = this.��ͼˢ��Ƶ��.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result2) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 996);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.��ͼˢ��Ƶ��.getText() + "' where id = 996;";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    gui.Start.GetConfigValues();
                    ˢ�µ�ͼˢ��Ƶ��();
                    ZEVMS2��ʾ��.setText("[��Ϣ]:�޸ĳɹ���");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ZEVMS2��ʾ��.setText("[��Ϣ]:��������Ҫ�޸ĵ����ݡ�");
        }
    }//GEN-LAST:event_�޸���Ʒ�������ʱ��2ActionPerformed
    private void ˢ��ҩˮ��ȴ() {
        for (int i = ((DefaultTableModel) (this.ҩˮ��ȴ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.ҩˮ��ȴ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM configvalues WHERE id >= 30000 && id<= 31000");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ҩˮ��ȴ.getModel()).insertRow(ҩˮ��ȴ.getRowCount(), new Object[]{
                    rs.getInt("id"),
                    rs.getString("Name"),
                    rs.getInt("Val")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
        }
        ҩˮ��ȴ.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ҩˮ��ȴ.getSelectedRow();
                String a = ҩˮ��ȴ.getValueAt(i, 0).toString();
                String a1 = ҩˮ��ȴ.getValueAt(i, 1).toString();
                String a2 = ҩˮ��ȴ.getValueAt(i, 2).toString();
                ҩˮ���.setText(a);
                ҩˮ����.setText(a1);
                ҩˮ��ȴʱ��.setText(a2);
            }
        });

    }

    public void ˢ�����籬��() {

        for (int i = ((DefaultTableModel) (this.���籬��.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.���籬��.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM drop_data_global WHERE itemid !=0");
            rs = ps.executeQuery();
            while (rs.next()) {

                ((DefaultTableModel) ���籬��.getModel()).insertRow(���籬��.getRowCount(), new Object[]{
                    rs.getInt("id"),
                    rs.getInt("itemid"),
                    rs.getString("chance"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))
                });
            }
            ���籬��.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int i = ���籬��.getSelectedRow();
                    String a = ���籬��.getValueAt(i, 0).toString();
                    String a1 = ���籬��.getValueAt(i, 1).toString();
                    String a2 = ���籬��.getValueAt(i, 2).toString();
                    ���籬�����к�.setText(a);
                    ���籬����Ʒ����.setText(a1);
                    ���籬�ﱬ��.setText(a2);
                }
            });

        } catch (SQLException ex) {
            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ˢ��ָ�����ﱬ��() {
        boolean result = this.��ѯ����������.getText().matches("[0-9]+");
        if (result) {
            if (Integer.parseInt(this.��ѯ����������.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "����д��ȷ��ֵ");
            }
            for (int i = ((DefaultTableModel) (this.���ﱬ��.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.���ﱬ��.getModel())).removeRow(i);
            }
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;

                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM drop_data WHERE dropperid =  " + Integer.parseInt(this.���ﱬ��������.getText()) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) ���ﱬ��.getModel()).insertRow(���ﱬ��.getRowCount(), new Object[]{rs.getInt("id"), rs.getInt("dropperid"), rs.getInt("itemid"), rs.getInt("chance"), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
            ���ﱬ��.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int i = ���ﱬ��.getSelectedRow();
                    String a = ���ﱬ��.getValueAt(i, 0).toString();
                    String a1 = ���ﱬ��.getValueAt(i, 1).toString();
                    String a2 = ���ﱬ��.getValueAt(i, 2).toString();
                    String a3 = ���ﱬ��.getValueAt(i, 3).toString();
                    String a4 = ���ﱬ��.getValueAt(i, 4).toString();
                    ���ﱬ�����к�.setText(a);
                    ���ﱬ��������.setText(a1);
                    ���ﱬ����Ʒ����.setText(a2);
                    ���ﱬ�ﱬ��.setText(a3);
                    ���ﱬ����Ʒ����.setText(a4);
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "������Ҫ��ѯ�Ĺ������ ");
        }
    }

    public void ˢ�¹��ﱬ��() {
        for (int i = ((DefaultTableModel) (this.���ﱬ��.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.���ﱬ��.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM drop_data WHERE itemid !=0");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ���ﱬ��.getModel()).insertRow(���ﱬ��.getRowCount(), new Object[]{
                    rs.getInt("id"),
                    rs.getInt("dropperid"),
                    //MapleLifeFactory.getMonster(rs.getInt("dropperid")),
                    rs.getInt("itemid"),
                    rs.getInt("chance"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
        }
        ���ﱬ��.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ���ﱬ��.getSelectedRow();
                String a = ���ﱬ��.getValueAt(i, 0).toString();
                String a1 = ���ﱬ��.getValueAt(i, 1).toString();
                String a2 = ���ﱬ��.getValueAt(i, 2).toString();
                String a3 = ���ﱬ��.getValueAt(i, 3).toString();
                //String a4 = ���ﱬ��.getValueAt(i, 4).toString();
                ���ﱬ�����к�.setText(a);
                ���ﱬ��������.setText(a1);
                ���ﱬ����Ʒ����.setText(a2);
                ���ﱬ�ﱬ��.setText(a3);
                //���ﱬ����Ʒ����.setText(a4);

            }
        });
    }

    public void ˢ�¹��￨Ƭ() {
        for (int i = ((DefaultTableModel) (this.���ﱬ��.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.���ﱬ��.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM drop_data WHERE itemid >=2380000&& itemid <2390000");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ���ﱬ��.getModel()).insertRow(���ﱬ��.getRowCount(), new Object[]{
                    rs.getInt("id"),
                    rs.getInt("dropperid"),
                    //MapleLifeFactory.getMonster(rs.getInt("dropperid")),
                    rs.getInt("itemid"),
                    rs.getInt("chance"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
        }
        ���ﱬ��.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ���ﱬ��.getSelectedRow();
                String a = ���ﱬ��.getValueAt(i, 0).toString();
                String a1 = ���ﱬ��.getValueAt(i, 1).toString();
                String a2 = ���ﱬ��.getValueAt(i, 2).toString();
                String a3 = ���ﱬ��.getValueAt(i, 3).toString();
                //String a4 = ���ﱬ��.getValueAt(i, 4).toString();
                ���ﱬ�����к�.setText(a);
                ���ﱬ��������.setText(a1);
                ���ﱬ����Ʒ����.setText(a2);
                ���ﱬ�ﱬ��.setText(a3);
                //���ﱬ����Ʒ����.setText(a4);

            }
        });
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
        ����̨2��.setDefaultLookAndFeelDecorated(true);
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
                new ����̨2��().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ZEVMS2��ʾ��;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel260;
    private javax.swing.JLabel jLabel261;
    private javax.swing.JLabel jLabel262;
    private javax.swing.JLabel jLabel263;
    private javax.swing.JLabel jLabel264;
    private javax.swing.JLabel jLabel265;
    private javax.swing.JLabel jLabel266;
    private javax.swing.JLabel jLabel267;
    private javax.swing.JLabel jLabel268;
    private javax.swing.JLabel jLabel269;
    private javax.swing.JLabel jLabel270;
    private javax.swing.JLabel jLabel271;
    private javax.swing.JLabel jLabel272;
    private javax.swing.JLabel jLabel273;
    private javax.swing.JLabel jLabel274;
    private javax.swing.JLabel jLabel275;
    private javax.swing.JLabel jLabel277;
    private javax.swing.JLabel jLabel278;
    private javax.swing.JLabel jLabel279;
    private javax.swing.JLabel jLabel280;
    private javax.swing.JLabel jLabel281;
    private javax.swing.JLabel jLabel282;
    private javax.swing.JLabel jLabel285;
    private javax.swing.JLabel jLabel286;
    private javax.swing.JLabel jLabel287;
    private javax.swing.JLabel jLabel315;
    private javax.swing.JLabel jLabel316;
    private javax.swing.JLabel jLabel317;
    private javax.swing.JLabel jLabel318;
    private javax.swing.JLabel jLabel319;
    private javax.swing.JLabel jLabel320;
    private javax.swing.JLabel jLabel321;
    private javax.swing.JLabel jLabel322;
    private javax.swing.JLabel jLabel323;
    private javax.swing.JLabel jLabel324;
    private javax.swing.JLabel jLabel325;
    private javax.swing.JLabel jLabel326;
    private javax.swing.JLabel jLabel327;
    private javax.swing.JLabel jLabel328;
    private javax.swing.JLabel jLabel329;
    private javax.swing.JLabel jLabel330;
    private javax.swing.JLabel jLabel337;
    private javax.swing.JLabel jLabel338;
    private javax.swing.JLabel jLabel339;
    private javax.swing.JLabel jLabel340;
    private javax.swing.JLabel jLabel341;
    private javax.swing.JLabel jLabel342;
    private javax.swing.JLabel jLabel343;
    private javax.swing.JLabel jLabel344;
    private javax.swing.JLabel jLabel345;
    private javax.swing.JLabel jLabel346;
    private javax.swing.JLabel jLabel347;
    private javax.swing.JLabel jLabel348;
    private javax.swing.JLabel jLabel349;
    private javax.swing.JLabel jLabel350;
    private javax.swing.JLabel jLabel351;
    private javax.swing.JLabel jLabel352;
    private javax.swing.JLabel jLabel353;
    private javax.swing.JLabel jLabel354;
    private javax.swing.JLabel jLabel355;
    private javax.swing.JLabel jLabel356;
    private javax.swing.JLabel jLabel357;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable ���籬��;
    private javax.swing.JTextField ���籬������;
    private javax.swing.JTextField ���籬�����к�;
    private javax.swing.JTextField ���籬�ﱬ��;
    private javax.swing.JTextField ���籬����Ʒ����;
    private javax.swing.JButton �޸����籬��;
    private javax.swing.JButton �޸ķ�Ӧ����Ʒ;
    private javax.swing.JButton �޸���Ʒ;
    private javax.swing.JButton �޸Ĺ��ﱬ��;
    private javax.swing.JButton �޸��ڿ���Ʒ;
    private javax.swing.JButton �޸��ڿ���Ʒ1;
    private javax.swing.JButton �޸���Ʒ�������ʱ��;
    private javax.swing.JButton �޸���Ʒ�������ʱ��1;
    private javax.swing.JButton �޸���Ʒ�������ʱ��2;
    private javax.swing.JButton �޸ĵ�����Ʒ;
    private javax.swing.JButton ɾ�����籬��;
    private javax.swing.JButton ɾ����Ӧ����Ʒ;
    private javax.swing.JButton ɾ����Ӧ����Ʒ1;
    private javax.swing.JTextField ɾ����Ӧ����Ʒ����;
    private javax.swing.JButton ɾ����Ʒ;
    private javax.swing.JButton ɾ�����ﱬ��;
    private javax.swing.JTextField ɾ��ָ���ĵ���;
    private javax.swing.JButton ɾ��ָ���ĵ��䰴��;
    private javax.swing.JButton ɾ���ڿ���Ʒ1;
    private javax.swing.JButton ɾ���ڿ���Ʒ2;
    private javax.swing.JPanel ɾ����Ϸ��Ʒ;
    private javax.swing.JButton ɾ������;
    private javax.swing.JButton ɾ������1;
    private javax.swing.JButton ɾ������2;
    private javax.swing.JButton ɾ������3;
    private javax.swing.JButton ɾ������4;
    private javax.swing.JButton ɾ��������Ʒ;
    private javax.swing.JButton ˢ�����籬��;
    private javax.swing.JButton ˢ�¹��￨Ƭ;
    private javax.swing.JButton ˢ�¹��ﱬ��;
    private javax.swing.JButton ˢ���ڿ���;
    private javax.swing.JButton ˢ���ڿ���1;
    private javax.swing.JButton ˢ�µ�����Ʒ;
    private javax.swing.JLabel ��ҵ��ʾ����;
    private javax.swing.JTable ��Ӧ��;
    private javax.swing.JTextField ��Ӧ�Ѵ���;
    private javax.swing.JTextField ��Ӧ�����к�;
    private javax.swing.JTextField ��Ӧ�Ѹ���;
    private javax.swing.JTextField ��Ӧ����Ʒ;
    private javax.swing.JPanel ��Ӧ������;
    private javax.swing.JTextField ��Ʒ����;
    private javax.swing.JTextField ��Ʒ�ۼ۽��;
    private javax.swing.JTextField ��Ʒ���;
    private javax.swing.JTextField ��Ʒ��Ʒ����;
    private javax.swing.JTextField �̵����;
    private javax.swing.JLabel �̵���ʾ����;
    private javax.swing.JTextField ��ͼˢ��Ƶ��;
    private javax.swing.JTextField ��ͼ��Ʒ����;
    private javax.swing.JTextField �����ڿ���Ʒ;
    private javax.swing.JTextField �����ڿ���Ʒ1;
    private javax.swing.JTable ���ﱬ��;
    private javax.swing.JTextField ���ﱬ�����к�;
    private javax.swing.JTextField ���ﱬ��������;
    private javax.swing.JTextField ���ﱬ�ﱬ��;
    private javax.swing.JTextField ���ﱬ����Ʒ����;
    private javax.swing.JTextField ���ﱬ����Ʒ����;
    private javax.swing.JTextField �򶹶�����;
    private javax.swing.JTable �ڿ�Ӧ��;
    private javax.swing.JTextField �ڿ����к�;
    private javax.swing.JTextField �ڿ����к�1;
    private javax.swing.JTextField �ڿ���Ʒ����;
    private javax.swing.JTextField �ڿ���Ʒ����1;
    private javax.swing.JPanel �ڿ����;
    private javax.swing.JButton ������Ӧ����Ʒ;
    private javax.swing.JButton ������Ʒ;
    private javax.swing.JButton �����ڿ���Ʒ;
    private javax.swing.JButton �����ڿ���Ʒ1;
    private javax.swing.JButton �����ڿ���Ʒ2;
    private javax.swing.JButton ����������Ʒ;
    private javax.swing.JTextField ���ҷ�Ӧ�ѵ���;
    private javax.swing.JTextField ������Ʒ;
    private javax.swing.JButton ���ҵ���;
    private javax.swing.JButton ���ҵ���1;
    private javax.swing.JButton ���ҵ���2;
    private javax.swing.JButton ���ҵ���3;
    private javax.swing.JButton ���ҵ���4;
    private javax.swing.JTextField ��ѯ�̵�;
    private javax.swing.JButton ��ѯ�̵�2;
    private javax.swing.JButton ��ѯ�������;
    private javax.swing.JTextField ��ѯ����������;
    private javax.swing.JButton ��ѯ��Ʒ����;
    private javax.swing.JTextField ��ѯ��Ʒ�������;
    private javax.swing.JButton ������籬��;
    private javax.swing.JButton ��ӹ��ﱬ��;
    private javax.swing.JPanel ��Ϸ�̵�;
    private javax.swing.JTable ��Ϸ�̵�2;
    private javax.swing.JPanel ��Ϸ����;
    private javax.swing.JTable ��Ϸ����;
    private javax.swing.JTextField ��Ϸ���ߴ���;
    private javax.swing.JTextField ��Ʒ����;
    private javax.swing.JTextField ��Ʒ����1;
    private javax.swing.JTextField ��Ʒ�������ʱ��;
    private javax.swing.JTextField �춹���ӿ�����;
    private javax.swing.JTextField �̶����ӿ�����;
    private javax.swing.JTable ҩˮ��ȴ;
    private javax.swing.JTextField ҩˮ��ȴʱ��;
    private javax.swing.JTextField ҩˮ����;
    private javax.swing.JTextField ҩˮ���;
    private javax.swing.JTextField �������ӿ�����;
    private javax.swing.JTextField ��������;
    private javax.swing.JButton �������޸�����;
    private javax.swing.JButton �������޸�����1;
    private javax.swing.JButton �������޸�����2;
    private javax.swing.JPanel ����������;
    private javax.swing.JTextField ����ϡ����;
    private javax.swing.JTextField ����������;
    private javax.swing.JTextField ����������;
    private javax.swing.JTable ������Ʒ;
    private javax.swing.JTextField ������Ʒ����;
    private javax.swing.JTextField ������Ʒ����;
    private javax.swing.JTextField ������Ʒ���;
    private javax.swing.JTextField ������Ʒ����;
    private javax.swing.JPanel �������;
    // End of variables declaration//GEN-END:variables
}
