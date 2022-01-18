package client;

import client.messages.CommandProcessorUtil;
import constants.GameConstants;
import gui.ZEVMS2;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.UIManager;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import server.life.MapleMonster;
import server.maps.MapleMap;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import server.quest.MapleQuest;
import tools.HexTool;
import tools.MaplePacketCreator;
import tools.data.MaplePacketLittleEndianWriter;

/**
 *
 * @author Administrator
 */
public class DebugWindow extends javax.swing.JFrame {

    private MapleClient c;

    public DebugWindow() {
        initComponents();
    }

    public MapleClient getC() {
        return this.c;
    }

    public void setC(MapleClient c) {
        this.c = c;
        if (c.getPlayer() != null) {
            setTitle("��Ҳ������: " + c.getPlayer().getName() + " ");

        }
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        setIconImage(icon.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ��� = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        �޵� = new javax.swing.JButton();
        SP = new javax.swing.JButton();
        AP = new javax.swing.JButton();
        ˢ�� = new javax.swing.JButton();
        ��� = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        ������ = new javax.swing.JButton();
        ������ = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        սʿ = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        ��ʿ = new javax.swing.JButton();
        Ӣ�� = new javax.swing.JButton();
        ׼��ʿ = new javax.swing.JButton();
        ��ʿ = new javax.swing.JButton();
        ʥ��ʿ = new javax.swing.JButton();
        ǹսʿ = new javax.swing.JButton();
        ����ʿ = new javax.swing.JButton();
        ����ʿ = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        ħ��ʦ = new javax.swing.JButton();
        �𶾷�ʦ = new javax.swing.JButton();
        ����ʦ = new javax.swing.JButton();
        ��ħ��ʦ = new javax.swing.JButton();
        ���׷�ʦ = new javax.swing.JButton();
        ������ʦ = new javax.swing.JButton();
        ����ħ��ʦ = new javax.swing.JButton();
        ��ʦ = new javax.swing.JButton();
        ��˾ = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        ������ = new javax.swing.JButton();
        �𶾷�ʦ1 = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        ������ = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        ���� = new javax.swing.JButton();
        �̿� = new javax.swing.JButton();
        ��Ӱ�� = new javax.swing.JButton();
        ��ʿ = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        ���п� = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        ���� = new javax.swing.JButton();
        ȭ�� = new javax.swing.JButton();
        ��ʿ = new javax.swing.JButton();
        ���ӳ� = new javax.swing.JButton();
        ��ǹ�� = new javax.swing.JButton();
        �� = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        ����ʿ1 = new javax.swing.JButton();
        ����ʿ2 = new javax.swing.JButton();
        ����ʿ3 = new javax.swing.JButton();
        ����ʿ1 = new javax.swing.JButton();
        ����ʿ2 = new javax.swing.JButton();
        ����ʿ3 = new javax.swing.JButton();
        ����ʹ��1 = new javax.swing.JButton();
        ����ʹ��3 = new javax.swing.JButton();
        ����ʹ��2 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        ҹ����1 = new javax.swing.JButton();
        ҹ����2 = new javax.swing.JButton();
        ҹ����3 = new javax.swing.JButton();
        ��Ϯ��3 = new javax.swing.JButton();
        ��Ϯ��2 = new javax.swing.JButton();
        ��Ϯ��1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        ս��1 = new javax.swing.JButton();
        ս��2 = new javax.swing.JButton();
        ս��3 = new javax.swing.JButton();
        ս��4 = new javax.swing.JButton();
        ����ʼ = new javax.swing.JButton();
        ��� = new javax.swing.JButton();
        ��ȯ = new javax.swing.JButton();
        ����ȯ = new javax.swing.JButton();
        ˢ��Ʒ = new javax.swing.JButton();
        ������� = new javax.swing.JTextField();
        ������� = new javax.swing.JButton();
        ˢ��11 = new javax.swing.JButton();
        ��Ʒ���� = new javax.swing.JTextField();
        ��Ʒ���� = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 163, -1, -1));

        ���.setColumns(20);
        ���.setRows(5);
        jScrollPane2.setViewportView(���);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 420, 90));

        jButton1.setText("���ͷ��");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 420, 30));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.setText("����");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel1.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 140, -1));

        �޵�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޵�.setText("�޵�");
        �޵�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޵�ActionPerformed(evt);
            }
        });
        jPanel1.add(�޵�, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 140, -1));

        SP.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        SP.setText("SP+10");
        SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SPActionPerformed(evt);
            }
        });
        jPanel1.add(SP, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 140, -1));

        AP.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        AP.setText("AP+10");
        AP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                APActionPerformed(evt);
            }
        });
        jPanel1.add(AP, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 140, -1));

        ˢ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ��.setText("ˢ��");
        ˢ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ��ActionPerformed(evt);
            }
        });
        jPanel1.add(ˢ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 140, -1));

        ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���.setText("���");
        ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ActionPerformed(evt);
            }
        });
        jPanel1.add(���, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 140, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.setText("����");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel1.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 140, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.setText("����");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel1.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 140, -1));

        ������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������.setText("������");
        ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������ActionPerformed(evt);
            }
        });
        jPanel1.add(������, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 140, -1));

        ������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������.setText("������");
        ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������ActionPerformed(evt);
            }
        });
        jPanel1.add(������, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        սʿ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        սʿ.setText("սʿ");
        սʿ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                սʿActionPerformed(evt);
            }
        });
        jPanel3.add(սʿ, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.setText("����");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel3.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        ��ʿ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ʿ.setText("��ʿ");
        ��ʿ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ʿActionPerformed(evt);
            }
        });
        jPanel3.add(��ʿ, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        Ӣ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        Ӣ��.setText("Ӣ��");
        Ӣ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ӣ��ActionPerformed(evt);
            }
        });
        jPanel3.add(Ӣ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        ׼��ʿ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ׼��ʿ.setText("׼��ʿ");
        ׼��ʿ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ׼��ʿActionPerformed(evt);
            }
        });
        jPanel3.add(׼��ʿ, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        ��ʿ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ʿ.setText("��ʿ");
        ��ʿ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ʿActionPerformed(evt);
            }
        });
        jPanel3.add(��ʿ, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 120, -1));

        ʥ��ʿ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ʥ��ʿ.setText("ʥ��ʿ");
        ʥ��ʿ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ʥ��ʿActionPerformed(evt);
            }
        });
        jPanel3.add(ʥ��ʿ, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 120, -1));

        ǹսʿ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ǹսʿ.setText("ǹսʿ");
        ǹսʿ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ǹսʿActionPerformed(evt);
            }
        });
        jPanel3.add(ǹսʿ, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 120, -1));

        ����ʿ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ʿ.setText("����ʿ");
        ����ʿ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ʿActionPerformed(evt);
            }
        });
        jPanel3.add(����ʿ, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 120, -1));

        ����ʿ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ʿ.setText("����ʿ");
        ����ʿ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ʿActionPerformed(evt);
            }
        });
        jPanel3.add(����ʿ, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 120, -1));

        jTabbedPane1.addTab("սʿ", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ħ��ʦ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ħ��ʦ.setText("ħ��ʦ");
        ħ��ʦ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ħ��ʦActionPerformed(evt);
            }
        });
        jPanel4.add(ħ��ʦ, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        �𶾷�ʦ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �𶾷�ʦ.setText("�𶾷�ʦ");
        �𶾷�ʦ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �𶾷�ʦActionPerformed(evt);
            }
        });
        jPanel4.add(�𶾷�ʦ, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        ����ʦ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ʦ.setText("����ʦ");
        ����ʦ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ʦActionPerformed(evt);
            }
        });
        jPanel4.add(����ʦ, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        ��ħ��ʦ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ħ��ʦ.setText("��ħ��ʦ");
        ��ħ��ʦ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ħ��ʦActionPerformed(evt);
            }
        });
        jPanel4.add(��ħ��ʦ, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        ���׷�ʦ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���׷�ʦ.setText("���׷�ʦ");
        ���׷�ʦ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���׷�ʦActionPerformed(evt);
            }
        });
        jPanel4.add(���׷�ʦ, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        ������ʦ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������ʦ.setText("������ʦ");
        ������ʦ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������ʦActionPerformed(evt);
            }
        });
        jPanel4.add(������ʦ, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 120, -1));

        ����ħ��ʦ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ħ��ʦ.setText("����ħ��ʦ");
        ����ħ��ʦ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ħ��ʦActionPerformed(evt);
            }
        });
        jPanel4.add(����ħ��ʦ, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 120, -1));

        ��ʦ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ʦ.setText("��ʦ");
        ��ʦ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ʦActionPerformed(evt);
            }
        });
        jPanel4.add(��ʦ, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 120, -1));

        ��˾.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��˾.setText("��˾");
        ��˾.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��˾ActionPerformed(evt);
            }
        });
        jPanel4.add(��˾, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 120, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.setText("����");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel4.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 120, -1));

        jTabbedPane1.addTab("ħ��ʦ", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������.setText("������");
        ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������ActionPerformed(evt);
            }
        });
        jPanel5.add(������, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        �𶾷�ʦ1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �𶾷�ʦ1.setText("����");
        �𶾷�ʦ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �𶾷�ʦ1ActionPerformed(evt);
            }
        });
        jPanel5.add(�𶾷�ʦ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.setText("����");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel5.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        ������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������.setText("������");
        ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������ActionPerformed(evt);
            }
        });
        jPanel5.add(������, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.setText("����");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel5.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.setText("����");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel5.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 120, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.setText("����");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel5.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 120, -1));

        jTabbedPane1.addTab("������", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.setText("����");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel6.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        �̿�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �̿�.setText("�̿�");
        �̿�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �̿�ActionPerformed(evt);
            }
        });
        jPanel6.add(�̿�, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        ��Ӱ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ӱ��.setText("��Ӱ��");
        ��Ӱ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ӱ��ActionPerformed(evt);
            }
        });
        jPanel6.add(��Ӱ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        ��ʿ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ʿ.setText("��ʿ");
        ��ʿ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ʿActionPerformed(evt);
            }
        });
        jPanel6.add(��ʿ, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.setText("����");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel6.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 120, -1));

        ���п�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���п�.setText("���п�");
        ���п�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���п�ActionPerformed(evt);
            }
        });
        jPanel6.add(���п�, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 120, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.setText("����");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel6.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        jTabbedPane1.addTab("����", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.setText("����");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel7.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        ȭ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ȭ��.setText("ȭ��");
        ȭ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȭ��ActionPerformed(evt);
            }
        });
        jPanel7.add(ȭ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        ��ʿ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ʿ.setText("��ʿ");
        ��ʿ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ʿActionPerformed(evt);
            }
        });
        jPanel7.add(��ʿ, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        ���ӳ�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���ӳ�.setText("���ӳ�");
        ���ӳ�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ӳ�ActionPerformed(evt);
            }
        });
        jPanel7.add(���ӳ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        ��ǹ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ǹ��.setText("��ǹ��");
        ��ǹ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ǹ��ActionPerformed(evt);
            }
        });
        jPanel7.add(��ǹ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��.setText("��");
        ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ActionPerformed(evt);
            }
        });
        jPanel7.add(��, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 120, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.setText("����");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel7.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 120, -1));

        jTabbedPane1.addTab("����", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ����ʿ1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ʿ1.setText("����ʿ1");
        ����ʿ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ʿ1ActionPerformed(evt);
            }
        });
        jPanel8.add(����ʿ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        ����ʿ2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ʿ2.setText("����ʿ2");
        ����ʿ2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ʿ2ActionPerformed(evt);
            }
        });
        jPanel8.add(����ʿ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        ����ʿ3.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ʿ3.setText("����ʿ3");
        ����ʿ3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ʿ3ActionPerformed(evt);
            }
        });
        jPanel8.add(����ʿ3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        ����ʿ1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ʿ1.setText("����ʿ1");
        ����ʿ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ʿ1ActionPerformed(evt);
            }
        });
        jPanel8.add(����ʿ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 120, -1));

        ����ʿ2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ʿ2.setText("����ʿ2");
        ����ʿ2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ʿ2ActionPerformed(evt);
            }
        });
        jPanel8.add(����ʿ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        ����ʿ3.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ʿ3.setText("����ʿ3");
        ����ʿ3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ʿ3ActionPerformed(evt);
            }
        });
        jPanel8.add(����ʿ3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 120, -1));

        ����ʹ��1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ʹ��1.setText("����ʹ��1");
        ����ʹ��1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ʹ��1ActionPerformed(evt);
            }
        });
        jPanel8.add(����ʹ��1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 120, -1));

        ����ʹ��3.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ʹ��3.setText("����ʹ��3");
        ����ʹ��3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ʹ��3ActionPerformed(evt);
            }
        });
        jPanel8.add(����ʹ��3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 120, -1));

        ����ʹ��2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ʹ��2.setText("����ʹ��2");
        ����ʹ��2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ʹ��2ActionPerformed(evt);
            }
        });
        jPanel8.add(����ʹ��2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 120, -1));

        jTabbedPane1.addTab("��ʿ��", jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ҹ����1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ҹ����1.setText("ҹ����1");
        ҹ����1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ҹ����1ActionPerformed(evt);
            }
        });
        jPanel9.add(ҹ����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        ҹ����2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ҹ����2.setText("ҹ����2");
        ҹ����2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ҹ����2ActionPerformed(evt);
            }
        });
        jPanel9.add(ҹ����2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        ҹ����3.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ҹ����3.setText("ҹ����3");
        ҹ����3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ҹ����3ActionPerformed(evt);
            }
        });
        jPanel9.add(ҹ����3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        ��Ϯ��3.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ϯ��3.setText("��Ϯ��3");
        ��Ϯ��3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ϯ��3ActionPerformed(evt);
            }
        });
        jPanel9.add(��Ϯ��3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 120, -1));

        ��Ϯ��2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ϯ��2.setText("��Ϯ��2");
        ��Ϯ��2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ϯ��2ActionPerformed(evt);
            }
        });
        jPanel9.add(��Ϯ��2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, -1));

        ��Ϯ��1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ϯ��1.setText("��Ϯ��1");
        ��Ϯ��1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ϯ��1ActionPerformed(evt);
            }
        });
        jPanel9.add(��Ϯ��1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 120, -1));

        jTabbedPane1.addTab("*", jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ս��1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ս��1.setText("ս��1");
        ս��1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ս��1ActionPerformed(evt);
            }
        });
        jPanel10.add(ս��1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, -1));

        ս��2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ս��2.setText("ս��2");
        ս��2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ս��2ActionPerformed(evt);
            }
        });
        jPanel10.add(ս��2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        ս��3.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ս��3.setText("ս��3");
        ս��3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ս��3ActionPerformed(evt);
            }
        });
        jPanel10.add(ս��3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        ս��4.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ս��4.setText("ս��4");
        ս��4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ս��4ActionPerformed(evt);
            }
        });
        jPanel10.add(ս��4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        jTabbedPane1.addTab("ս��", jPanel10);

        jPanel2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 170));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 420, 170));

        ����ʼ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ʼ.setText("����ʼ");
        ����ʼ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ʼActionPerformed(evt);
            }
        });
        jPanel1.add(����ʼ, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 140, -1));

        ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���.setText("���");
        ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ActionPerformed(evt);
            }
        });
        jPanel1.add(���, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 140, -1));

        ��ȯ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ȯ.setText("��ȯ");
        ��ȯ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ȯActionPerformed(evt);
            }
        });
        jPanel1.add(��ȯ, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 140, -1));

        ����ȯ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ȯ.setText("����ȯ");
        ����ȯ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ȯActionPerformed(evt);
            }
        });
        jPanel1.add(����ȯ, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 140, -1));

        ˢ��Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ��Ʒ.setText("ˢ��Ʒ");
        ˢ��Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ��ƷActionPerformed(evt);
            }
        });
        jPanel1.add(ˢ��Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 140, -1));

        �������.setText("�������");
        jPanel1.add(�������, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 130, -1));

        �������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �������.setText("�������");
        �������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �������ActionPerformed(evt);
            }
        });
        jPanel1.add(�������, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 140, -1));

        ˢ��11.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ��11.setText("û�õ�");
        ˢ��11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ��11ActionPerformed(evt);
            }
        });
        jPanel1.add(ˢ��11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 140, -1));

        ��Ʒ����.setText("10");
        jPanel1.add(��Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 130, -1));

        ��Ʒ����.setText("2000000");
        jPanel1.add(��Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 130, -1));

        jLabel2.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel2.setText("׼�����ͣ�");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        byte[] data = HexTool.getByteArrayFromHexString(this.���.getText());
        //this.jTextArea2.setText(null);
        this.jLabel1.setText(null);
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        int packetheader = Integer.parseInt(this.���.getText());
        String packet_in = " 00 00 00 00 00 00 00 00 00 ";
        mplew.writeShort(packetheader);
        mplew.write(HexTool.getByteArrayFromHexString(packet_in));
        mplew.writeZeroBytes(20);
        c.sendPacket(mplew.getPacket());
        //c.getPlayer().dropMessage(6,"�Ѵ��ͷ��[" +packetheader + "] ");
        c.getPlayer().dropMessage(6, "�Ѵ��ͷ��[" + packetheader + "][" + mplew.getPacket().length + "] : " + mplew.toString());
        this.���.setText("" + (Integer.parseInt(this.���.getText()) + 1) + "");
        System.err.println("" + c.getPlayer().getName() + " �ѷ��ͣ�" + (Integer.parseInt(this.���.getText()) - 1) + "");
        this.jButton1.setText("�ѷ��ͣ�" + (Integer.parseInt(this.���.getText()) - 1) + "");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        c.getPlayer().dropMessage(6, "[�ȼ�����]:�ɹ����ӵȼ�");
        c.getPlayer().setExp(0);
        c.getPlayer().updateSingleStat(MapleStat.EXP, 0);
        if (c.getPlayer().getLevel() < 200) {
            c.getPlayer().gainExp(GameConstants.getExpNeededForLevel(c.getPlayer().getLevel()) + 1, true, false, true);
        }
    }//GEN-LAST:event_����ActionPerformed

    private void �޵�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޵�ActionPerformed
        MapleCharacter player = c.getPlayer();
        if (player.isInvincible()) {
            player.setInvincible(false);
            player.dropMessage(6, "[�޵�״̬]:�Ѿ��ر�");
        } else {
            player.setInvincible(true);
            player.dropMessage(6, "[�޵�״̬]:�Ѿ�����.");
        }
    }//GEN-LAST:event_�޵�ActionPerformed

    private void SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SPActionPerformed
        c.getPlayer().gainSP((short) 10);
    }//GEN-LAST:event_SPActionPerformed

    private void APActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_APActionPerformed
        c.getPlayer().gainAp((short) 10);
    }//GEN-LAST:event_APActionPerformed

    private void ˢ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ��ActionPerformed
        MapleCharacter player = c.getPlayer();
        c.sendPacket(MaplePacketCreator.getCharInfo(player));
        player.getMap().removePlayer(player);
        player.getMap().addPlayer(player);
    }//GEN-LAST:event_ˢ��ActionPerformed

    private void ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ActionPerformed
        MapleMap map = c.getPlayer().getMap();
        double range = Double.POSITIVE_INFINITY;
        boolean drop = false;
        MapleMonster mob;
        List<MapleMapObject> monsters = map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER));
        for (MapleMapObject monstermo : map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
            mob = (MapleMonster) monstermo;
            map.killMonster(mob, c.getPlayer(), true, false, (byte) 1);
        }
    }//GEN-LAST:event_���ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        c.getPlayer().getMap().removeDrops();
    }//GEN-LAST:event_����ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        Point pos = c.getPlayer().getPosition();
        c.getPlayer().dropMessage(6, "X: " + pos.x + " | Y: " + pos.y + " | RX0: " + (pos.x + 50) + " | RX1: " + (pos.x - 50) + " | FH: " + c.getPlayer().getFH() + "| CY:" + pos.y);

    }//GEN-LAST:event_����ActionPerformed

    private void ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������ActionPerformed
        MapleCharacter player = c.getPlayer();
        player.maxSkills();
    }//GEN-LAST:event_������ActionPerformed

    private void ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������ActionPerformed
        MapleCharacter player = c.getPlayer();
        player.getStat().setMaxHp((short) 30000);
        player.getStat().setMaxMp((short) 30000);
        player.getStat().setStr(Short.MAX_VALUE);
        player.getStat().setDex(Short.MAX_VALUE);
        player.getStat().setInt(Short.MAX_VALUE);
        player.getStat().setLuk(Short.MAX_VALUE);
        player.updateSingleStat(MapleStat.MAXHP, 30000);
        player.updateSingleStat(MapleStat.MAXMP, 30000);
        player.updateSingleStat(MapleStat.STR, Short.MAX_VALUE);
        player.updateSingleStat(MapleStat.DEX, Short.MAX_VALUE);
        player.updateSingleStat(MapleStat.INT, Short.MAX_VALUE);
        player.updateSingleStat(MapleStat.LUK, Short.MAX_VALUE);
    }//GEN-LAST:event_������ActionPerformed

    private void սʿActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_սʿActionPerformed
        c.getPlayer().changeJob(100);
    }//GEN-LAST:event_սʿActionPerformed

    private void ����ʿ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ʿ1ActionPerformed
        c.getPlayer().changeJob(1100);
    }//GEN-LAST:event_����ʿ1ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        c.getPlayer().changeJob(110);
    }//GEN-LAST:event_����ActionPerformed

    private void ��ʿActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ʿActionPerformed
        c.getPlayer().changeJob(111);
    }//GEN-LAST:event_��ʿActionPerformed

    private void Ӣ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ӣ��ActionPerformed
        c.getPlayer().changeJob(112);
    }//GEN-LAST:event_Ӣ��ActionPerformed

    private void ׼��ʿActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_׼��ʿActionPerformed
        c.getPlayer().changeJob(120);
    }//GEN-LAST:event_׼��ʿActionPerformed

    private void ��ʿActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ʿActionPerformed
        c.getPlayer().changeJob(121);
    }//GEN-LAST:event_��ʿActionPerformed

    private void ʥ��ʿActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ʥ��ʿActionPerformed
        c.getPlayer().changeJob(122);
    }//GEN-LAST:event_ʥ��ʿActionPerformed

    private void ǹսʿActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ǹսʿActionPerformed
        c.getPlayer().changeJob(130);
    }//GEN-LAST:event_ǹսʿActionPerformed

    private void ����ʿActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ʿActionPerformed
        c.getPlayer().changeJob(131);
    }//GEN-LAST:event_����ʿActionPerformed

    private void ����ʿActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ʿActionPerformed
        c.getPlayer().changeJob(132);
    }//GEN-LAST:event_����ʿActionPerformed

    private void ħ��ʦActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ħ��ʦActionPerformed
        c.getPlayer().changeJob(200);
    }//GEN-LAST:event_ħ��ʦActionPerformed

    private void �𶾷�ʦActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�𶾷�ʦActionPerformed
        c.getPlayer().changeJob(210);
    }//GEN-LAST:event_�𶾷�ʦActionPerformed

    private void ����ʦActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ʦActionPerformed
        c.getPlayer().changeJob(211);
    }//GEN-LAST:event_����ʦActionPerformed

    private void ��ħ��ʦActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ħ��ʦActionPerformed
        c.getPlayer().changeJob(212);
    }//GEN-LAST:event_��ħ��ʦActionPerformed

    private void ���׷�ʦActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���׷�ʦActionPerformed
        c.getPlayer().changeJob(220);
    }//GEN-LAST:event_���׷�ʦActionPerformed

    private void ������ʦActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������ʦActionPerformed
        c.getPlayer().changeJob(221);
    }//GEN-LAST:event_������ʦActionPerformed

    private void ����ħ��ʦActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ħ��ʦActionPerformed
        c.getPlayer().changeJob(222);
    }//GEN-LAST:event_����ħ��ʦActionPerformed

    private void ��ʦActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ʦActionPerformed
        c.getPlayer().changeJob(230);
    }//GEN-LAST:event_��ʦActionPerformed

    private void ��˾ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��˾ActionPerformed
        c.getPlayer().changeJob(231);
    }//GEN-LAST:event_��˾ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        c.getPlayer().changeJob(232);
    }//GEN-LAST:event_����ActionPerformed

    private void ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������ActionPerformed
        c.getPlayer().changeJob(300);
    }//GEN-LAST:event_������ActionPerformed

    private void �𶾷�ʦ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�𶾷�ʦ1ActionPerformed
        c.getPlayer().changeJob(310);
    }//GEN-LAST:event_�𶾷�ʦ1ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        c.getPlayer().changeJob(311);
    }//GEN-LAST:event_����ActionPerformed

    private void ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������ActionPerformed
        c.getPlayer().changeJob(312);
    }//GEN-LAST:event_������ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        c.getPlayer().changeJob(320);
    }//GEN-LAST:event_����ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        c.getPlayer().changeJob(321);
    }//GEN-LAST:event_����ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        c.getPlayer().changeJob(322);
    }//GEN-LAST:event_����ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        c.getPlayer().changeJob(400);
    }//GEN-LAST:event_����ActionPerformed

    private void �̿�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�̿�ActionPerformed
        c.getPlayer().changeJob(410);
    }//GEN-LAST:event_�̿�ActionPerformed

    private void ��Ӱ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ӱ��ActionPerformed
        c.getPlayer().changeJob(411);
    }//GEN-LAST:event_��Ӱ��ActionPerformed

    private void ��ʿActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ʿActionPerformed
        c.getPlayer().changeJob(412);
    }//GEN-LAST:event_��ʿActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        c.getPlayer().changeJob(422);
    }//GEN-LAST:event_����ActionPerformed

    private void ���п�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���п�ActionPerformed
        c.getPlayer().changeJob(421);
    }//GEN-LAST:event_���п�ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        c.getPlayer().changeJob(420);
    }//GEN-LAST:event_����ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        c.getPlayer().changeJob(500);
    }//GEN-LAST:event_����ActionPerformed

    private void ȭ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȭ��ActionPerformed
        c.getPlayer().changeJob(510);
    }//GEN-LAST:event_ȭ��ActionPerformed

    private void ��ʿActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ʿActionPerformed
        c.getPlayer().changeJob(511);
    }//GEN-LAST:event_��ʿActionPerformed

    private void ���ӳ�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ӳ�ActionPerformed
        c.getPlayer().changeJob(512);
    }//GEN-LAST:event_���ӳ�ActionPerformed

    private void ��ǹ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ǹ��ActionPerformed
        c.getPlayer().changeJob(520);
    }//GEN-LAST:event_��ǹ��ActionPerformed

    private void ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ActionPerformed
        c.getPlayer().changeJob(521);
    }//GEN-LAST:event_��ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        c.getPlayer().changeJob(522);
    }//GEN-LAST:event_����ActionPerformed

    private void ����ʼActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ʼActionPerformed
        boolean a = this.�������.getText().matches("[0-9]+");
        if (a) {
            MapleQuest.getInstance(Integer.parseInt(�������.getText())).forceStart(c.getPlayer(), 0, null);
        }
    }//GEN-LAST:event_����ʼActionPerformed

    private void ����ʿ2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ʿ2ActionPerformed
        c.getPlayer().changeJob(1110);
    }//GEN-LAST:event_����ʿ2ActionPerformed

    private void ����ʿ3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ʿ3ActionPerformed
        c.getPlayer().changeJob(1111);
    }//GEN-LAST:event_����ʿ3ActionPerformed

    private void ����ʿ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ʿ1ActionPerformed
        c.getPlayer().changeJob(1200);
    }//GEN-LAST:event_����ʿ1ActionPerformed

    private void ����ʿ2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ʿ2ActionPerformed
        c.getPlayer().changeJob(1210);
    }//GEN-LAST:event_����ʿ2ActionPerformed

    private void ����ʿ3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ʿ3ActionPerformed
        c.getPlayer().changeJob(1211);
    }//GEN-LAST:event_����ʿ3ActionPerformed

    private void ����ʹ��1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ʹ��1ActionPerformed
        c.getPlayer().changeJob(1300);
    }//GEN-LAST:event_����ʹ��1ActionPerformed

    private void ����ʹ��3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ʹ��3ActionPerformed
        c.getPlayer().changeJob(1311);
    }//GEN-LAST:event_����ʹ��3ActionPerformed

    private void ����ʹ��2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ʹ��2ActionPerformed
        c.getPlayer().changeJob(1310);
    }//GEN-LAST:event_����ʹ��2ActionPerformed

    private void ҹ����1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ҹ����1ActionPerformed
        c.getPlayer().changeJob(1400);
    }//GEN-LAST:event_ҹ����1ActionPerformed

    private void ҹ����2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ҹ����2ActionPerformed
        c.getPlayer().changeJob(1410);
    }//GEN-LAST:event_ҹ����2ActionPerformed

    private void ҹ����3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ҹ����3ActionPerformed
        c.getPlayer().changeJob(1411);
    }//GEN-LAST:event_ҹ����3ActionPerformed

    private void ��Ϯ��3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ϯ��3ActionPerformed
        c.getPlayer().changeJob(1511);
    }//GEN-LAST:event_��Ϯ��3ActionPerformed

    private void ��Ϯ��2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ϯ��2ActionPerformed
        c.getPlayer().changeJob(1510);
    }//GEN-LAST:event_��Ϯ��2ActionPerformed

    private void ��Ϯ��1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ϯ��1ActionPerformed
        c.getPlayer().changeJob(1500);
    }//GEN-LAST:event_��Ϯ��1ActionPerformed

    private void ս��1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ս��1ActionPerformed
        c.getPlayer().changeJob(2100);
    }//GEN-LAST:event_ս��1ActionPerformed

    private void ս��2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ս��2ActionPerformed
        c.getPlayer().changeJob(2110);
    }//GEN-LAST:event_ս��2ActionPerformed

    private void ս��3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ս��3ActionPerformed
        c.getPlayer().changeJob(2111);
    }//GEN-LAST:event_ս��3ActionPerformed

    private void ս��4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ս��4ActionPerformed
        c.getPlayer().changeJob(2112);
    }//GEN-LAST:event_ս��4ActionPerformed

    private void ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ActionPerformed
        c.getPlayer().gainMeso(1000000, true);
    }//GEN-LAST:event_���ActionPerformed

    private void ��ȯActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ȯActionPerformed
        c.getPlayer().modifyCSPoints(1, 100000);
    }//GEN-LAST:event_��ȯActionPerformed

    private void ����ȯActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ȯActionPerformed
        c.getPlayer().modifyCSPoints(2, 100000);
    }//GEN-LAST:event_����ȯActionPerformed

    private void ˢ��ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ��ƷActionPerformed
        boolean a = this.��Ʒ����.getText().matches("[0-9]+");
        boolean b = this.��Ʒ����.getText().matches("[0-9]+");
        if (a && b) {
            MaplePacketCreator.getShowItemGain(Integer.parseInt(��Ʒ����.getText()), (short) Integer.parseInt(��Ʒ����.getText()), true);
        }
    }//GEN-LAST:event_ˢ��ƷActionPerformed

    private void �������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_�������ActionPerformed

    private void ˢ��11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ��11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ˢ��11ActionPerformed

    public static void main(String args[]) {
        DebugWindow.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
        } catch (Exception e) {
        }
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DebugWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DebugWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DebugWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DebugWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DebugWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AP;
    private javax.swing.JButton SP;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton ����;
    private javax.swing.JTextField �������;
    private javax.swing.JButton �������;
    private javax.swing.JButton ����ʼ;
    private javax.swing.JButton ����;
    private javax.swing.JButton ����;
    private javax.swing.JButton ������ʦ;
    private javax.swing.JButton ���׷�ʦ;
    private javax.swing.JButton ����ħ��ʦ;
    private javax.swing.JButton ���ӳ�;
    private javax.swing.JButton ׼��ʿ;
    private javax.swing.JButton ˢ��;
    private javax.swing.JButton ˢ��11;
    private javax.swing.JButton ˢ��Ʒ;
    private javax.swing.JButton �̿�;
    private javax.swing.JButton ����;
    private javax.swing.JButton ��ʿ;
    private javax.swing.JButton ����;
    private javax.swing.JButton ʥ��ʿ;
    private javax.swing.JButton ����;
    private javax.swing.JButton ҹ����1;
    private javax.swing.JButton ҹ����2;
    private javax.swing.JButton ҹ����3;
    private javax.swing.JButton ��;
    private javax.swing.JButton ��Ϯ��1;
    private javax.swing.JButton ��Ϯ��2;
    private javax.swing.JButton ��Ϯ��3;
    private javax.swing.JTextArea ���;
    private javax.swing.JButton ����;
    private javax.swing.JButton ������;
    private javax.swing.JButton ����;
    private javax.swing.JButton սʿ;
    private javax.swing.JButton ս��1;
    private javax.swing.JButton ս��2;
    private javax.swing.JButton ս��3;
    private javax.swing.JButton ս��4;
    private javax.swing.JButton ����ȯ;
    private javax.swing.JButton ȭ��;
    private javax.swing.JButton ��ʿ;
    private javax.swing.JButton ��Ӱ��;
    private javax.swing.JButton �޵�;
    private javax.swing.JButton ǹսʿ;
    private javax.swing.JButton ����;
    private javax.swing.JButton ���;
    private javax.swing.JButton ����;
    private javax.swing.JButton ����;
    private javax.swing.JButton ������;
    private javax.swing.JButton ������;
    private javax.swing.JButton ��ǹ��;
    private javax.swing.JButton ����ʦ;
    private javax.swing.JButton �𶾷�ʦ;
    private javax.swing.JButton �𶾷�ʦ1;
    private javax.swing.JButton ��ħ��ʦ;
    private javax.swing.JButton ����ʿ1;
    private javax.swing.JButton ����ʿ2;
    private javax.swing.JButton ����ʿ3;
    private javax.swing.JButton ��ȯ;
    private javax.swing.JButton ��ʦ;
    private javax.swing.JTextField ��Ʒ����;
    private javax.swing.JTextField ��Ʒ����;
    private javax.swing.JButton ���п�;
    private javax.swing.JButton ������;
    private javax.swing.JButton ��˾;
    private javax.swing.JButton ����;
    private javax.swing.JButton ����;
    private javax.swing.JButton Ӣ��;
    private javax.swing.JButton ���;
    private javax.swing.JButton ��ʿ;
    private javax.swing.JButton ����ʹ��1;
    private javax.swing.JButton ����ʹ��2;
    private javax.swing.JButton ����ʹ��3;
    private javax.swing.JButton ����;
    private javax.swing.JButton ��ʿ;
    private javax.swing.JButton ����ʿ1;
    private javax.swing.JButton ����ʿ2;
    private javax.swing.JButton ����ʿ3;
    private javax.swing.JButton ħ��ʦ;
    private javax.swing.JButton ����ʿ;
    private javax.swing.JButton ����ʿ;
    // End of variables declaration//GEN-END:variables
}
