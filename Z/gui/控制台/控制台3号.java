package gui.����̨;

import static abc.Game.�汾;
import database.DatabaseConnection;
import gui.ZEVMS;
import handling.world.MapleParty;
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

/**
 *
 * @author Administrator
 */
public class ����̨3�� extends javax.swing.JFrame {

    /**
     * Creates new form ������������̨
     */
    public ����̨3��() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        setIconImage(icon.getImage());
        Properties �O���n = System.getProperties();
        setTitle("ZEVMS����˺�̨ v" + �汾 + "<����̨3��>");
        initComponents();
        ��䲻���ڵ�������Ϣ();
        ˢ�²�������();
        ˢ������ԡͰ();
        ˢ��ħ��ͻϮ����();
        ˢ��OX���⿪��();
        ˢ��ÿ���ͻ�����();
        ˢ������ְҵ����();
        ˢ��ħ�幥�ǿ���();
        ˢ����ĩ���ʿ���();
        ˢ���������˿���();
        ˢ��Ұ��ͨ������();
        ˢ��ϲ���콵����();
        ˢ�����();
        ˢ�·�������();
        ˢ�·����ҿ���();
        ˢ�·����ҿ���();
        ˢ�·����ҿ���();
        ˢ��������������();
        ˢ����������ʱ��();
        ˢ������ְҵ();
        ˢ����Ӧ����();
        ˢ�����ӱ�ע();
        ˢ�·�Ҷ����();
        ˢ�´����Żݿ���();
    }

    private void ˢ�´����Żݿ���() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("�����Żݿ���");
        if (S <= 0) {
            ��ʾ = "����";
        } else {
            ��ʾ = "�ر�";
        }
        �����Żݿ���.setText(��ʾ);
    }

    private void ˢ�·�Ҷ����() {
        ��Ҷ����ʽ��.setText("" + gui.Start.ConfigValuesMap.get("��Ҷ����ʽ��") + "");
        ��Ҷ����ʾ���.setText("" + gui.Start.ConfigValuesMap.get("��Ҷ����ʾ���") + "");
        ��Ҷ���������.setText("" + gui.Start.ConfigValuesMap.get("��Ҷ���������") + "");
        ��Ҷ����ʼ��.setText("" + gui.Start.ConfigValuesMap.get("��Ҷ����ʼ��") + "");
    }

    private void ��䲻���ڵ�������Ϣ() {
        if ("".equals(ȡ���ӱ�ע(3010025))) {
            Gainqqstem(" ", 3010025, 1);
        }
        if ("".equals(ȡ���ӱ�ע(3010100))) {
            Gainqqstem(" ", 3010100, 1);
        }
        if ("".equals(ȡ���ӱ�ע(3012002))) {
            Gainqqstem(" ", 3012002, 1);
        }
    }

    private void ˢ�����ӱ�ע() {
        ��Ҷ����ʱ�ע.setText(ȡ���ӱ�ע(3010025));
        ����ԡͰ��ע.setText(ȡ���ӱ�ע(3010100));
        �������ӱ�ע.setText(ȡ���ӱ�ע(3012002));
    }

    private void ˢ����Ӧ����() {
        ��Ӧ������ʾ.setText("" + gui.Start.ConfigValuesMap.get("��Ӧ��������") + "");
    }

    private void ˢ������ְҵ() {

        ����ְҵ����.setText("" + MapleParty.����ְҵ + "");
    }

    private void ˢ����������ʱ��() {

        �������˳���ʱ��.setText("" + MapleParty.��������ʱ�� + "");
    }

    private void ˢ��������������() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("������������");
        if (S <= 0) {
            ��ʾ = "����";
        } else {
            ��ʾ = "�ر�";
        }
        ������������.setText(��ʾ);
    }

    private void ˢ�·�������() {
        ��������.setText("" + gui.Start.ConfigValuesMap.get("��������") + "");
        ���鱶��.setText("" + gui.Start.ConfigValuesMap.get("���㾭�鱶��") + "");
        ��ұ���.setText("" + gui.Start.ConfigValuesMap.get("�����ұ���") + "");
        ��ȯ����.setText("" + gui.Start.ConfigValuesMap.get("�����ȯ����") + "");
    }

    private void ˢ�·��㾭�鿪��() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("���㾭�鿪��");
        if (S <= 0) {
            ��ʾ = "��";
        } else {
            ��ʾ = "��";
        }
        ���鿪��.setText(��ʾ);
    }

    private void ˢ�·����ҿ���() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("�����ҿ���");
        if (S <= 0) {
            ��ʾ = "��";
        } else {
            ��ʾ = "��";
        }
        ��ҿ���.setText(��ʾ);
    }

    private void ˢ�·����ȯ����() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("�����ȯ����");
        if (S <= 0) {
            ��ʾ = "��";
        } else {
            ��ʾ = "��";
        }
        ��ȯ����.setText(��ʾ);
    }

    private void ˢ��ϲ���콵����() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("ϲ���콵����");
        if (S <= 0) {
            ��ʾ = "����";
        } else {
            ��ʾ = "�ر�";
        }
        ϲ���콵����.setText(��ʾ);
    }

    private void ˢ��Ұ��ͨ������() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("Ұ��ͨ������");
        if (S <= 0) {
            ��ʾ = "����";
        } else {
            ��ʾ = "�ر�";
        }
        Ұ��ͨ������.setText(��ʾ);
    }

    private void ˢ���������˿���() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("�������˿���");
        if (S <= 0) {
            ��ʾ = "����";
        } else {
            ��ʾ = "�ر�";
        }
        �������˿���.setText(��ʾ);
    }

    private void ˢ����ĩ���ʿ���() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("��ĩ���ʿ���");
        if (S <= 0) {
            ��ʾ = "����";
        } else {
            ��ʾ = "�ر�";
        }
        ��ĩ���ʿ���.setText(��ʾ);
    }

    private void ˢ��ħ�幥�ǿ���() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("ħ�幥�ǿ���");
        if (S <= 0) {
            ��ʾ = "����";
        } else {
            ��ʾ = "�ر�";
        }
        ħ�幥�ǿ���.setText(��ʾ);
    }

    private void ˢ������ְҵ����() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("����ְҵ����");
        if (S <= 0) {
            ��ʾ = "����";
        } else {
            ��ʾ = "�ر�";
        }
        ����ְҵ����.setText(��ʾ);
    }

    private void ˢ��ÿ���ͻ�����() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("ÿ���ͻ�����");
        if (S <= 0) {
            ��ʾ = "����";
        } else {
            ��ʾ = "�ر�";
        }
        ÿ���ͻ�����.setText(��ʾ);
    }

    private void ˢ��OX���⿪��() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("OX���⿪��");
        if (S <= 0) {
            ��ʾ = "����";
        } else {
            ��ʾ = "�ر�";
        }
        OX���⿪��.setText(��ʾ);
    }

    private void ˢ��ħ��ͻϮ����() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("ħ��ͻϮ����");
        if (S <= 0) {
            ��ʾ = "����";
        } else {
            ��ʾ = "�ر�";
        }
        ħ��ͻϮ����.setText(��ʾ);
    }

    private void ˢ������ԡͰ() {
        ԡͰ���.setText("" + gui.Start.ConfigValuesMap.get("ԡͰ����0") + "");
        ԡͰ����1.setText("" + gui.Start.ConfigValuesMap.get("ԡͰ����1") + "");
        ԡͰ����2.setText("" + gui.Start.ConfigValuesMap.get("ԡͰ����2") + "");
        ԡͰ����3.setText("" + gui.Start.ConfigValuesMap.get("ԡͰ����3") + "");
        ԡͰ����4.setText("" + gui.Start.ConfigValuesMap.get("ԡͰ����4") + "");
        ԡͰ����5.setText("" + gui.Start.ConfigValuesMap.get("ԡͰ����5") + "");
        ԡͰ����6.setText("" + gui.Start.ConfigValuesMap.get("ԡͰ����6") + "");
        ԡͰ����7.setText("" + gui.Start.ConfigValuesMap.get("ԡͰ����7") + "");
        ԡͰ����8.setText("" + gui.Start.ConfigValuesMap.get("ԡͰ����8") + "");
        ԡͰ����9.setText("" + gui.Start.ConfigValuesMap.get("ԡͰ����9") + "");
    }

    private void ˢ�²�������() {
        �������ӽ������.setText("" + gui.Start.ConfigValuesMap.get("��������1") + "");
        �������Ӽ��.setText("" + gui.Start.ConfigValuesMap.get("��������2") + "");
        �������ӵ���.setText("" + gui.Start.ConfigValuesMap.get("��������3") + "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane3 = new javax.swing.JTabbedPane();
        ��¼� = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        �������˳���ʱ�� = new javax.swing.JTextField();
        jLabel343 = new javax.swing.JLabel();
        �޸��������� = new javax.swing.JButton();
        ����ְҵ���� = new javax.swing.JTextField();
        jLabel344 = new javax.swing.JLabel();
        ����ְҵ�޸� = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        ħ�幥�ǿ��� = new javax.swing.JButton();
        jLabel264 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        OX���⿪�� = new javax.swing.JButton();
        jLabel265 = new javax.swing.JLabel();
        jLabel266 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        ÿ���ͻ����� = new javax.swing.JButton();
        jLabel268 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        ÿ���ͻ�����1 = new javax.swing.JButton();
        jLabel271 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        ����ְҵ���� = new javax.swing.JButton();
        jLabel267 = new javax.swing.JLabel();
        jLabel269 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        ħ��ͻϮ���� = new javax.swing.JButton();
        jLabel270 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        ��ĩ���ʿ��� = new javax.swing.JButton();
        jLabel272 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        ÿ���ͻ�����3 = new javax.swing.JButton();
        jLabel273 = new javax.swing.JLabel();
        jLabel263 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        �������˿��� = new javax.swing.JButton();
        jLabel274 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        Ұ��ͨ������ = new javax.swing.JButton();
        jLabel275 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        ϲ���콵���� = new javax.swing.JButton();
        jLabel276 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        ������������ = new javax.swing.JButton();
        jLabel277 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        �����Żݿ��� = new javax.swing.JButton();
        jLabel302 = new javax.swing.JLabel();
        ������� = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        �������ӽ������ = new javax.swing.JTextField();
        �������Ӽ�� = new javax.swing.JTextField();
        jLabel249 = new javax.swing.JLabel();
        �������ӵ��� = new javax.swing.JTextField();
        jLabel251 = new javax.swing.JLabel();
        ���������޸� = new javax.swing.JButton();
        jLabel262 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ԡͰ��� = new javax.swing.JTextField();
        jLabel252 = new javax.swing.JLabel();
        jLabel254 = new javax.swing.JLabel();
        ���������޸�1 = new javax.swing.JButton();
        ԡͰ����1 = new javax.swing.JTextField();
        ԡͰ����2 = new javax.swing.JTextField();
        ԡͰ����3 = new javax.swing.JTextField();
        ԡͰ����4 = new javax.swing.JTextField();
        ԡͰ����5 = new javax.swing.JTextField();
        ԡͰ����6 = new javax.swing.JTextField();
        ԡͰ����7 = new javax.swing.JTextField();
        ԡͰ����8 = new javax.swing.JTextField();
        ԡͰ����9 = new javax.swing.JTextField();
        jLabel253 = new javax.swing.JLabel();
        jLabel255 = new javax.swing.JLabel();
        jLabel256 = new javax.swing.JLabel();
        jLabel257 = new javax.swing.JLabel();
        jLabel258 = new javax.swing.JLabel();
        jLabel259 = new javax.swing.JLabel();
        jLabel260 = new javax.swing.JLabel();
        jLabel261 = new javax.swing.JLabel();
        jLabel250 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        ��Ҷ����ʽ�� = new javax.swing.JTextField();
        ��Ҷ����ʼ�� = new javax.swing.JTextField();
        jLabel295 = new javax.swing.JLabel();
        ��Ҷ����ʾ��� = new javax.swing.JTextField();
        jLabel296 = new javax.swing.JLabel();
        ���������޸�2 = new javax.swing.JButton();
        jLabel297 = new javax.swing.JLabel();
        ��Ҷ��������� = new javax.swing.JTextField();
        jLabel298 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel299 = new javax.swing.JLabel();
        ��Ҷ����ʱ�ע = new javax.swing.JTextField();
        jLabel300 = new javax.swing.JLabel();
        ����ԡͰ��ע = new javax.swing.JTextField();
        jLabel301 = new javax.swing.JLabel();
        �������ӱ�ע = new javax.swing.JTextField();
        ��ע�޸� = new javax.swing.JButton();
        ��� = new javax.swing.JPanel();
        jScrollPane110 = new javax.swing.JScrollPane();
        OX������� = new javax.swing.JTable();
        ¼������ = new javax.swing.JTextField();
        ¼��� = new javax.swing.JTextField();
        ¼�����ⰴť = new javax.swing.JButton();
        �޸����ⰴť = new javax.swing.JButton();
        ɾ�����ⰴť = new javax.swing.JButton();
        ¼����� = new javax.swing.JTextField();
        jLabel336 = new javax.swing.JLabel();
        jLabel340 = new javax.swing.JLabel();
        jLabel342 = new javax.swing.JLabel();
        jLabel350 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel327 = new javax.swing.JLabel();
        ��ȯ���� = new javax.swing.JTextField();
        jLabel328 = new javax.swing.JLabel();
        ��ұ��� = new javax.swing.JTextField();
        ���鱶��2 = new javax.swing.JLabel();
        ���鱶�� = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        �������� = new javax.swing.JTextField();
        ��ȯ���� = new javax.swing.JButton();
        ��ҿ��� = new javax.swing.JButton();
        ���鿪�� = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel278 = new javax.swing.JLabel();
        jLabel279 = new javax.swing.JLabel();
        jLabel280 = new javax.swing.JLabel();
        jLabel281 = new javax.swing.JLabel();
        jLabel282 = new javax.swing.JLabel();
        jLabel283 = new javax.swing.JLabel();
        jLabel284 = new javax.swing.JLabel();
        jLabel285 = new javax.swing.JLabel();
        jLabel286 = new javax.swing.JLabel();
        jLabel287 = new javax.swing.JLabel();
        jLabel288 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        ��Ӧ������ʾ = new javax.swing.JTextField();
        jLabel345 = new javax.swing.JLabel();
        �޸���������1 = new javax.swing.JButton();
        jLabel289 = new javax.swing.JLabel();
        jLabel290 = new javax.swing.JLabel();
        jLabel291 = new javax.swing.JLabel();
        jLabel292 = new javax.swing.JLabel();
        jLabel293 = new javax.swing.JLabel();
        jLabel294 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        �޸���������2 = new javax.swing.JButton();
        ��Ӧ������ʾ1 = new javax.swing.JTextField();
        ����ģʽ1 = new javax.swing.JTextField();
        BOSS��� = new javax.swing.JPanel();
        jScrollPane104 = new javax.swing.JScrollPane();
        Ұ��BOSSˢ��ʱ�� = new javax.swing.JTable();
        ˢ��Ұ��BOSSˢ��ʱ�� = new javax.swing.JButton();
        Ұ��BOSS��� = new javax.swing.JTextField();
        Ұ��BOSSˢ��ʱ��ֵ = new javax.swing.JTextField();
        Ұ��BOSS = new javax.swing.JTextField();
        ˢ��Ұ��BOSSˢ��ʱ���޸� = new javax.swing.JButton();
        jLabel323 = new javax.swing.JLabel();
        jLabel324 = new javax.swing.JLabel();
        jLabel325 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane105 = new javax.swing.JScrollPane();
        BOSS�������� = new javax.swing.JTable();
        Ұ��BOSS���1 = new javax.swing.JTextField();
        BOSS�޴� = new javax.swing.JTextField();
        BOSS = new javax.swing.JTextField();
        �޸�BOSS�޴� = new javax.swing.JButton();
        ˢ��BOSS�޴� = new javax.swing.JButton();
        jLabel326 = new javax.swing.JLabel();
        jLabel329 = new javax.swing.JLabel();
        jLabel330 = new javax.swing.JLabel();

        setResizable(false);

        jTabbedPane3.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N

        ��¼�.setBackground(new java.awt.Color(255, 255, 255));
        ��¼�.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextPane1.setEditable(false);
        jTextPane1.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jTextPane1.setForeground(new java.awt.Color(51, 0, 255));
        jTextPane1.setText("[ÿ���ͻ�] ÿ�� 12:00 - 23:59\n[ħ��͵Ϯ] ÿ�� 22:00 - 22:10\n[ħ�幥��] ���� 21:00 - 21:30\n[ÿ�մ���] ��һ������ 20:10 - 20:20 ��ĩ 20:10 - 20:59\n[��������] ��ȫ������֣��޹���\n[Ұ��ͨ��] ϵͳ����һ���������ɺ� 1 Сʱˢ��\n[����ְҵ] 11:00 23:00 �����ȡְҵȺ������ 50% ���Ծ���\n[��ĩ��] �����������賿�������2�����飬2�����ʣ�2������ͱ���\n[ϲ���콵] ���գ�22:30 ���� 2 Ƶ���г��񻶷�����Ʒ\n[��������] ��һ������ 21:30 - 21:40 ��ĩ 21:30 - 21:59 ��ˮ���������\n");
        jTextPane1.setToolTipText("");
        jScrollPane1.setViewportView(jTextPane1);

        jPanel18.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 960, 540));
        jPanel18.add(�������˳���ʱ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 50, 110, 30));

        jLabel343.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel343.setText("�������ˣ�");
        jPanel18.add(jLabel343, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, -1, 20));

        �޸���������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸���������.setText("�޸�");
        �޸���������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸���������ActionPerformed(evt);
            }
        });
        jPanel18.add(�޸���������, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 50, 70, 30));
        jPanel18.add(����ְҵ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 130, 110, 30));

        jLabel344.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel344.setText("����ְҵ��");
        jPanel18.add(jLabel344, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 100, -1, 20));

        ����ְҵ�޸�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ְҵ�޸�.setText("�޸�");
        ����ְҵ�޸�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ְҵ�޸�ActionPerformed(evt);
            }
        });
        jPanel18.add(����ְҵ�޸�, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 130, 70, 30));

        jTabbedPane2.addTab("Ԥ��", jPanel18);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ħ�幥��", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ħ�幥�ǿ���.setText("����");
        ħ�幥�ǿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ħ�幥�ǿ���ActionPerformed(evt);
            }
        });
        jPanel4.add(ħ�幥�ǿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 80, 30));

        jLabel264.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel264.setForeground(new java.awt.Color(51, 102, 255));
        jLabel264.setText("��������ĩ���� 21:10 ֮��ħ�����й��ǣ�������֮�ǿ�ʼ��������ۣ����ִ壬�������У�ħ�����֡�");
        jPanel4.add(jLabel264, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 960, 30));

        jPanel6.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 1190, 80));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OX����", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        OX���⿪��.setText("����");
        OX���⿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OX���⿪��ActionPerformed(evt);
            }
        });
        jPanel5.add(OX���⿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 80, 30));

        jLabel265.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel265.setForeground(new java.awt.Color(255, 0, 51));
        jPanel5.add(jLabel265, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 610, 30));

        jLabel266.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel266.setForeground(new java.awt.Color(51, 102, 255));
        jLabel266.setText("������ÿ��20:10�󣬻����OX��������һ������20����10�⣬����������50����25�⡣");
        jPanel5.add(jLabel266, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 880, 30));

        jPanel6.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 1190, 80));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ÿ���ͻ�", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ÿ���ͻ�����.setText("����");
        ÿ���ͻ�����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ÿ���ͻ�����ActionPerformed(evt);
            }
        });
        jPanel7.add(ÿ���ͻ�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 80, 30));

        jLabel268.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel268.setForeground(new java.awt.Color(51, 102, 255));
        jLabel268.setText("������ÿ��12:00֮��ʼ�ͻ���������ۿ�ʼ�����������н�����(npc\\9010009.js)");
        jPanel7.add(jLabel268, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 820, 30));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ÿ���ͻ�", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ÿ���ͻ�����1.setText("����");
        ÿ���ͻ�����1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ÿ���ͻ�����1ActionPerformed(evt);
            }
        });
        jPanel10.add(ÿ���ͻ�����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 30, 130, -1));

        jLabel271.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel271.setForeground(new java.awt.Color(255, 0, 51));
        jLabel271.setText("������ÿ��12:00֮��ʼ�ͻ���������ۿ�ʼ�����������н�����");
        jPanel10.add(jLabel271, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 820, 30));

        jPanel7.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 1190, 80));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 1190, 80));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "����ְҵ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ����ְҵ����.setText("����");
        ����ְҵ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ְҵ����ActionPerformed(evt);
            }
        });
        jPanel8.add(����ְҵ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 80, 30));

        jLabel267.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel267.setForeground(new java.awt.Color(255, 0, 51));
        jPanel8.add(jLabel267, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 610, 30));

        jLabel269.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel269.setForeground(new java.awt.Color(51, 102, 255));
        jLabel269.setText("�����󣬸���ָ����ְҵ����50%�Ķ������Ծ��飬ÿ�� 11:00 23:00 ���������ָ����ְҵ����ת����Ч��");
        jPanel8.add(jLabel269, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 880, 30));

        jPanel6.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 1190, 80));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ħ��ͻϮ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ħ��ͻϮ����.setText("����");
        ħ��ͻϮ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ħ��ͻϮ����ActionPerformed(evt);
            }
        });
        jPanel9.add(ħ��ͻϮ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 80, 30));

        jLabel270.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel270.setForeground(new java.awt.Color(51, 102, 255));
        jLabel270.setText("������ÿ��22:00 - 22:10������ħ��͵Ϯ��Ұ���ð�ռң�����30�����䵥����С����� ��");
        jPanel9.add(jLabel270, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 960, 30));

        jPanel6.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1190, 80));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��ĩ���˫���", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ĩ���ʿ���.setText("����");
        ��ĩ���ʿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ĩ���ʿ���ActionPerformed(evt);
            }
        });
        jPanel11.add(��ĩ���ʿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 80, 30));

        jLabel272.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel272.setForeground(new java.awt.Color(51, 102, 255));
        jLabel272.setText("�����������������賿���������24Сʱ2�����飬���ʣ����߾��鱬�ʻ��");
        jPanel11.add(jLabel272, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 820, 30));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ÿ���ͻ�", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ÿ���ͻ�����3.setText("����");
        ÿ���ͻ�����3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ÿ���ͻ�����3ActionPerformed(evt);
            }
        });
        jPanel12.add(ÿ���ͻ�����3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 30, 130, -1));

        jLabel273.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel273.setForeground(new java.awt.Color(255, 0, 51));
        jLabel273.setText("������ÿ��12:00֮��ʼ�ͻ���������ۿ�ʼ�����������н�����");
        jPanel12.add(jLabel273, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 820, 30));

        jPanel11.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 1190, 80));

        jPanel6.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 1190, 80));

        jLabel263.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel263.setForeground(new java.awt.Color(255, 0, 51));
        jLabel263.setText("�޸ĺ�ʵʱ��Ч�������ڻʱ�俪ʼǰ�޸Ĳ��ܴ�����");
        jPanel6.add(jLabel263, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 500, 30));

        jTabbedPane2.addTab("һ", jPanel6);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��������", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �������˿���.setText("����");
        �������˿���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �������˿���ActionPerformed(evt);
            }
        });
        jPanel14.add(�������˿���, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 80, 30));

        jLabel274.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel274.setForeground(new java.awt.Color(51, 102, 255));
        jLabel274.setText("�����󣬷���˻Ὺʼ�ٻ��������ˣ�����ÿ��ֻ��� 5 ���ӣ���ʧ����֪��һ�γ��ֵ���Ϣ����npc\\9900001.js��");
        jPanel14.add(jLabel274, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 960, 30));

        jPanel13.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 1190, 80));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ұ��ͨ��", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Ұ��ͨ������.setText("����");
        Ұ��ͨ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ұ��ͨ������ActionPerformed(evt);
            }
        });
        jPanel15.add(Ұ��ͨ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 80, 30));

        jLabel275.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel275.setForeground(new java.awt.Color(51, 102, 255));
        jLabel275.setText("�����󣬷���˻��������� 1 Сʱ�򷢲�ͨ������ͨ��Ŀ�걻��ɱ��� 1 Сʱ�ٴη���ͨ���(�����\\9000011_5.js)");
        jPanel15.add(jLabel275, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 1020, 30));

        jPanel13.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1190, 80));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ϲ���콵", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ϲ���콵����.setText("����");
        ϲ���콵����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ϲ���콵����ActionPerformed(evt);
            }
        });
        jPanel16.add(ϲ���콵����, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 80, 30));

        jLabel276.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel276.setForeground(new java.awt.Color(51, 102, 255));
        jLabel276.setText("�������������������� 22:30 �ֻ����г� 2 Ƶ��������ϲ���콵���");
        jPanel16.add(jLabel276, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 960, 30));

        jPanel13.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 1190, 80));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��������", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ������������.setText("����");
        ������������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������������ActionPerformed(evt);
            }
        });
        jPanel17.add(������������, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 80, 30));

        jLabel277.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel277.setForeground(new java.awt.Color(51, 102, 255));
        jLabel277.setText("������ÿ������ 21:30 ��ʼ���� 2 Ƶ��ˮ������������������������������á�");
        jPanel17.add(jLabel277, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 960, 30));

        jPanel13.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 1190, 80));

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�����Ż�", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �����Żݿ���.setText("����");
        �����Żݿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����Żݿ���ActionPerformed(evt);
            }
        });
        jPanel27.add(�����Żݿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 80, 30));

        jLabel302.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel302.setForeground(new java.awt.Color(51, 102, 255));
        jLabel302.setText("������ÿ�� 11:11  23:11 ��Ϸ�̳ǣ���Ϸ�̵깺����Ʒ��5�ۡ�");
        jPanel27.add(jLabel302, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 960, 30));

        jPanel13.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 1190, 80));

        jTabbedPane2.addTab("��", jPanel13);

        ��¼�.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 640));

        jTabbedPane3.addTab("�¼����", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ��¼�); // NOI18N

        �������.setBackground(new java.awt.Color(255, 255, 255));
        �������.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��������", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(�������ӽ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 120, 25));
        jPanel2.add(�������Ӽ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 120, 25));

        jLabel249.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel249.setText("ÿ�ε��ӣ�");
        jPanel2.add(jLabel249, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, 30));
        jPanel2.add(�������ӵ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 120, 25));

        jLabel251.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel251.setText("��ȡ���/�֣�");
        jPanel2.add(jLabel251, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 30));

        ���������޸�.setText("�޸�");
        ���������޸�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���������޸�ActionPerformed(evt);
            }
        });
        jPanel2.add(���������޸�, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 120, 25));
        ���������޸�.getAccessibleContext().setAccessibleDescription("");

        jLabel262.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel262.setText("������ޣ�");
        jPanel2.add(jLabel262, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 220, 470));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "����ԡͰ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(ԡͰ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 120, 25));

        jLabel252.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel252.setText("61-80����");
        jPanel3.add(jLabel252, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, 30));

        jLabel254.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel254.setText("��ȡ���/�֣�");
        jPanel3.add(jLabel254, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 30));

        ���������޸�1.setText("�޸�");
        ���������޸�1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���������޸�1ActionPerformed(evt);
            }
        });
        jPanel3.add(���������޸�1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 120, 25));
        jPanel3.add(ԡͰ����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 120, 25));
        jPanel3.add(ԡͰ����2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 120, 25));
        jPanel3.add(ԡͰ����3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 120, 25));
        jPanel3.add(ԡͰ����4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 120, 25));
        jPanel3.add(ԡͰ����5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 120, 25));
        jPanel3.add(ԡͰ����6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 120, 25));
        jPanel3.add(ԡͰ����7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 120, 25));
        jPanel3.add(ԡͰ����8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 120, 25));
        jPanel3.add(ԡͰ����9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 120, 25));

        jLabel253.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel253.setText("161+��");
        jPanel3.add(jLabel253, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, -1, 30));

        jLabel255.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel255.setText("21-40����");
        jPanel3.add(jLabel255, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 30));

        jLabel256.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel256.setText("41-60����");
        jPanel3.add(jLabel256, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, 30));

        jLabel257.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel257.setText("0-20����");
        jPanel3.add(jLabel257, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 30));

        jLabel258.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel258.setText("81-100����");
        jPanel3.add(jLabel258, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, 30));

        jLabel259.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel259.setText("101-120����");
        jPanel3.add(jLabel259, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, 30));

        jLabel260.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel260.setText("121-140����");
        jPanel3.add(jLabel260, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, 30));

        jLabel261.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel261.setText("141-160����");
        jPanel3.add(jLabel261, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, -1, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 340, 470));

        jLabel250.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel250.setForeground(new java.awt.Color(255, 0, 51));
        jLabel250.setText("�޸ĺ������ſ�����Ч��");
        jPanel1.add(jLabel250, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 310, 30));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��Ҷ�����", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel23.add(��Ҷ����ʽ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 120, 25));
        jPanel23.add(��Ҷ����ʼ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 120, 25));

        jLabel295.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel295.setText("���飻");
        jPanel23.add(jLabel295, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 30));
        jPanel23.add(��Ҷ����ʾ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 120, 25));

        jLabel296.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel296.setText("��ȡ���/�֣�");
        jPanel23.add(jLabel296, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, 30));

        ���������޸�2.setText("�޸�");
        ���������޸�2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���������޸�2ActionPerformed(evt);
            }
        });
        jPanel23.add(���������޸�2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 120, 25));

        jLabel297.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel297.setText("��ң�");
        jPanel23.add(jLabel297, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, 30));
        jPanel23.add(��Ҷ���������, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 120, 25));

        jLabel298.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel298.setText("�������ޣ�");
        jPanel23.add(jLabel298, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 30));

        jPanel1.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 220, 470));

        jTabbedPane1.addTab("��һҳ", jPanel1);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel299.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel299.setText("��Ҷ����ʱ�ע��");
        jPanel24.add(jLabel299, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 30));
        jPanel24.add(��Ҷ����ʱ�ע, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 310, 25));

        jLabel300.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel300.setText("����ԡͰ��ע��");
        jPanel24.add(jLabel300, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 30));
        jPanel24.add(����ԡͰ��ע, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 310, 25));

        jLabel301.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel301.setText("�������ӱ�ע��");
        jPanel24.add(jLabel301, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, 30));
        jPanel24.add(�������ӱ�ע, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 310, 25));

        ��ע�޸�.setText("�޸�");
        ��ע�޸�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ע�޸�ActionPerformed(evt);
            }
        });
        jPanel24.add(��ע�޸�, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 120, 25));

        jTabbedPane1.addTab("��ע", jPanel24);

        �������.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 650));

        jTabbedPane3.addTab("�������", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), �������); // NOI18N

        ���.setBackground(new java.awt.Color(255, 255, 255));
        ���.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        OX�������.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        OX�������.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "����", "��"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        OX�������.getTableHeader().setReorderingAllowed(false);
        jScrollPane110.setViewportView(OX�������);
        if (OX�������.getColumnModel().getColumnCount() > 0) {
            OX�������.getColumnModel().getColumn(0).setResizable(false);
            OX�������.getColumnModel().getColumn(1).setResizable(false);
            OX�������.getColumnModel().getColumn(1).setPreferredWidth(700);
            OX�������.getColumnModel().getColumn(2).setResizable(false);
            OX�������.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        ���.add(jScrollPane110, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1220, 520));
        ���.add(¼������, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 590, 810, 30));
        ���.add(¼���, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 590, 60, 30));

        ¼�����ⰴť.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ¼�����ⰴť.setText("¼��");
        ¼�����ⰴť.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ¼�����ⰴťActionPerformed(evt);
            }
        });
        ���.add(¼�����ⰴť, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 590, 70, 30));

        �޸����ⰴť.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸����ⰴť.setText("�޸�");
        �޸����ⰴť.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸����ⰴťActionPerformed(evt);
            }
        });
        ���.add(�޸����ⰴť, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 590, 70, 30));

        ɾ�����ⰴť.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ�����ⰴť.setText("ɾ��");
        ɾ�����ⰴť.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ�����ⰴťActionPerformed(evt);
            }
        });
        ���.add(ɾ�����ⰴť, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 590, 70, 30));

        ¼�����.setEditable(false);
        ���.add(¼�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, 60, 30));

        jLabel336.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel336.setText("��ţ�");
        ���.add(jLabel336, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, -1, 20));

        jLabel340.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel340.setText("�𰸣�O����X");
        ���.add(jLabel340, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 570, -1, 20));

        jLabel342.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel342.setText("���⣻");
        ���.add(jLabel342, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 570, -1, 20));

        jLabel350.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel350.setForeground(new java.awt.Color(255, 51, 0));
        jLabel350.setText("��ĿĿ¼��   ������¼���ʽ��*¼��<�ո�>[����]<�ո�>[��]     ������*¼�� ð�յ��ǲ����������ֵ���Ϸ O");
        ���.add(jLabel350, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1220, 20));

        jTabbedPane3.addTab("���", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ���); // NOI18N

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel327.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel327.setText("��ȯ���ʣ�");
        jPanel19.add(jLabel327, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, -1, 20));
        jPanel19.add(��ȯ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 120, 30));

        jLabel328.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel328.setText("��ұ��ʣ�");
        jPanel19.add(jLabel328, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, 20));
        jPanel19.add(��ұ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 120, 30));

        ���鱶��2.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ���鱶��2.setText("���鱶�ʣ�");
        jPanel19.add(���鱶��2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, 20));
        jPanel19.add(���鱶��, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 120, 30));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��������", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), java.awt.Color.black)); // NOI18N
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel20.add(��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 120, 30));

        jPanel19.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 210, 170));

        ��ȯ����.setText("��");
        ��ȯ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ȯ����ActionPerformed(evt);
            }
        });
        jPanel19.add(��ȯ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 80, 30));

        ��ҿ���.setText("��");
        ��ҿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ҿ���ActionPerformed(evt);
            }
        });
        jPanel19.add(��ҿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 80, 30));

        ���鿪��.setText("��");
        ���鿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���鿪��ActionPerformed(evt);
            }
        });
        jPanel19.add(���鿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 80, 30));

        jButton1.setText("�޸�");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 490, 100, 30));

        jLabel278.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel278.setForeground(new java.awt.Color(51, 51, 255));
        jLabel278.setText("���ֵ * ��ȯ��");
        jPanel19.add(jLabel278, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, 190, 30));

        jLabel279.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel279.setText("���÷�������������������Ѷȡ�ÿ����һ�ȡ��������ͷ��㡣");
        jPanel19.add(jLabel279, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 760, 30));

        jLabel280.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel280.setText("���ʼ����ҽ���2Ƶ��ˮ�����磬��Ϊ�μӻ��");
        jPanel19.add(jLabel280, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 690, 30));

        jLabel281.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel281.setText("�ÿ 30 ��Ϊ�����Ϊ�μӻ��������ӽ�����");
        jPanel19.add(jLabel281, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 690, 30));

        jLabel282.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel282.setForeground(new java.awt.Color(255, 51, 51));
        jLabel282.setText("�޸���ߵ�������ԣ���������õ����档");
        jPanel19.add(jLabel282, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 530, 690, 30));

        jLabel283.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel283.setText("������ȡ��ʽ��");
        jPanel19.add(jLabel283, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, 190, 30));

        jLabel284.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel284.setForeground(new java.awt.Color(51, 51, 255));
        jLabel284.setText("���ֵ * �����");
        jPanel19.add(jLabel284, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 190, 30));

        jLabel285.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel285.setForeground(new java.awt.Color(51, 51, 255));
        jLabel285.setText("���ֵ * ������");
        jPanel19.add(jLabel285, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 190, 30));

        jLabel286.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel286.setText("���ÿ 30 ������ 1 ���ֵ�������������ͼ��� 1 ����ֵ��");
        jPanel19.add(jLabel286, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 690, 30));

        jLabel287.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel287.setText("�뿪ˮ�������ͼ���뿪2Ƶ����Ϊ���������ö��ֵ��");
        jPanel19.add(jLabel287, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 390, 690, 30));

        jLabel288.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel288.setText("��ڼ��ٴν�����ٴμ��롣");
        jPanel19.add(jLabel288, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 430, 690, 30));

        jTabbedPane3.addTab("��������", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), jPanel19); // NOI18N

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�������", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel22.add(��Ӧ������ʾ, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 110, 30));

        jLabel345.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel345.setText("��Ӧ������");
        jPanel22.add(jLabel345, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, 20));

        �޸���������1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸���������1.setText("�޸�");
        �޸���������1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸���������1ActionPerformed(evt);
            }
        });
        jPanel22.add(�޸���������1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 110, 30));

        jPanel21.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 220, 190));

        jLabel289.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel289.setForeground(new java.awt.Color(255, 0, 0));
        jLabel289.setText("��Ҫ��ѩ����");
        jPanel21.add(jLabel289, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 150, 30));

        jLabel290.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel290.setText("���������� ����10��֮�󿪷š�");
        jPanel21.add(jLabel290, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 610, 30));

        jLabel291.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel291.setText("��Ӧָ��Ϊ��");
        jPanel21.add(jLabel291, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 150, 30));

        jLabel292.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel292.setText("������Ӧ����������Ӧ���������ͻῪ�����");
        jPanel21.add(jLabel292, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 610, 30));

        jLabel293.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel293.setText("���������ȴʱ��Ϊ 1 Сʱ��");
        jPanel21.add(jLabel293, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 610, 30));

        jLabel294.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel294.setText("��һ������ ����17��֮�󿪷š�");
        jPanel21.add(jLabel294, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, 610, 30));

        jTabbedPane3.addTab("�����", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), jPanel21); // NOI18N

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �޸���������2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸���������2.setText("�޸�");
        �޸���������2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸���������2ActionPerformed(evt);
            }
        });
        jPanel25.add(�޸���������2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 110, 30));
        jPanel25.add(��Ӧ������ʾ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 110, 30));

        ����ģʽ1.setEditable(false);
        jPanel25.add(����ģʽ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 110, 30));

        jTabbedPane3.addTab("����ģʽ", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), jPanel25); // NOI18N

        BOSS���.setBackground(new java.awt.Color(255, 255, 255));
        BOSS���.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BOSSˢ��ʱ��", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        BOSS���.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Ұ��BOSSˢ��ʱ��.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "Ұ��BOSS", "ˢ��ʱ��/��"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Ұ��BOSSˢ��ʱ��.getTableHeader().setReorderingAllowed(false);
        jScrollPane104.setViewportView(Ұ��BOSSˢ��ʱ��);
        if (Ұ��BOSSˢ��ʱ��.getColumnModel().getColumnCount() > 0) {
            Ұ��BOSSˢ��ʱ��.getColumnModel().getColumn(0).setResizable(false);
            Ұ��BOSSˢ��ʱ��.getColumnModel().getColumn(0).setPreferredWidth(50);
            Ұ��BOSSˢ��ʱ��.getColumnModel().getColumn(1).setResizable(false);
            Ұ��BOSSˢ��ʱ��.getColumnModel().getColumn(1).setPreferredWidth(170);
            Ұ��BOSSˢ��ʱ��.getColumnModel().getColumn(2).setResizable(false);
            Ұ��BOSSˢ��ʱ��.getColumnModel().getColumn(2).setPreferredWidth(170);
        }

        BOSS���.add(jScrollPane104, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 430, 600));

        ˢ��Ұ��BOSSˢ��ʱ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ��Ұ��BOSSˢ��ʱ��.setText("ˢ��");
        ˢ��Ұ��BOSSˢ��ʱ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ��Ұ��BOSSˢ��ʱ��ActionPerformed(evt);
            }
        });
        BOSS���.add(ˢ��Ұ��BOSSˢ��ʱ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 390, 90, 30));

        Ұ��BOSS���.setEditable(false);
        BOSS���.add(Ұ��BOSS���, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, 100, 30));
        BOSS���.add(Ұ��BOSSˢ��ʱ��ֵ, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 310, 120, 30));

        Ұ��BOSS.setEditable(false);
        BOSS���.add(Ұ��BOSS, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 310, 210, 30));

        ˢ��Ұ��BOSSˢ��ʱ���޸�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ��Ұ��BOSSˢ��ʱ���޸�.setText("�޸�");
        ˢ��Ұ��BOSSˢ��ʱ���޸�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ��Ұ��BOSSˢ��ʱ���޸�ActionPerformed(evt);
            }
        });
        BOSS���.add(ˢ��Ұ��BOSSˢ��ʱ���޸�, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 390, 90, 30));

        jLabel323.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel323.setText("ˢ��ʱ�䣻");
        BOSS���.add(jLabel323, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 290, -1, -1));

        jLabel324.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel324.setText("��ţ�");
        BOSS���.add(jLabel324, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 290, -1, -1));

        jLabel325.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel325.setText("BOSS��");
        BOSS���.add(jLabel325, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, -1, -1));

        jTabbedPane3.addTab("BOSSˢ�µ���", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), BOSS���); // NOI18N

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BOSS��������.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "BOSS", "ÿ�ջ�ȡ�������"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        BOSS��������.getTableHeader().setReorderingAllowed(false);
        jScrollPane105.setViewportView(BOSS��������);
        if (BOSS��������.getColumnModel().getColumnCount() > 0) {
            BOSS��������.getColumnModel().getColumn(0).setResizable(false);
            BOSS��������.getColumnModel().getColumn(0).setPreferredWidth(50);
            BOSS��������.getColumnModel().getColumn(1).setResizable(false);
            BOSS��������.getColumnModel().getColumn(1).setPreferredWidth(170);
            BOSS��������.getColumnModel().getColumn(2).setResizable(false);
            BOSS��������.getColumnModel().getColumn(2).setPreferredWidth(170);
        }

        jPanel26.add(jScrollPane105, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 430, 600));

        Ұ��BOSS���1.setEditable(false);
        jPanel26.add(Ұ��BOSS���1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, 100, 30));
        jPanel26.add(BOSS�޴�, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 310, 120, 30));

        BOSS.setEditable(false);
        jPanel26.add(BOSS, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 310, 210, 30));

        �޸�BOSS�޴�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸�BOSS�޴�.setText("�޸�");
        �޸�BOSS�޴�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸�BOSS�޴�ActionPerformed(evt);
            }
        });
        jPanel26.add(�޸�BOSS�޴�, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 390, 90, 30));

        ˢ��BOSS�޴�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ��BOSS�޴�.setText("ˢ��");
        ˢ��BOSS�޴�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ��BOSS�޴�ActionPerformed(evt);
            }
        });
        jPanel26.add(ˢ��BOSS�޴�, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 390, 90, 30));

        jLabel326.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel326.setText("BOSS��");
        jPanel26.add(jLabel326, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, -1, -1));

        jLabel329.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel329.setText("��ţ�");
        jPanel26.add(jLabel329, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 290, -1, -1));

        jLabel330.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel330.setText("�޴Σ�");
        jPanel26.add(jLabel330, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 290, -1, -1));

        jTabbedPane3.addTab("BOSS��������", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), jPanel26); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1260, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ˢ��Ұ��BOSSˢ��ʱ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ��Ұ��BOSSˢ��ʱ��ActionPerformed
        ˢ��Ұ��BOSSˢ��ʱ��();
    }//GEN-LAST:event_ˢ��Ұ��BOSSˢ��ʱ��ActionPerformed
    public void ˢ��Ұ��BOSSˢ��ʱ��() {
        for (int i = ((DefaultTableModel) (this.Ұ��BOSSˢ��ʱ��.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.Ұ��BOSSˢ��ʱ��.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM configvalues WHERE id >= 10000 && id < 20000");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) Ұ��BOSSˢ��ʱ��.getModel()).insertRow(Ұ��BOSSˢ��ʱ��.getRowCount(), new Object[]{rs.getString("id"), rs.getString("name"), rs.getString("Val")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        Ұ��BOSSˢ��ʱ��.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = Ұ��BOSSˢ��ʱ��.getSelectedRow();
                String a = Ұ��BOSSˢ��ʱ��.getValueAt(i, 0).toString();
                String a1 = Ұ��BOSSˢ��ʱ��.getValueAt(i, 1).toString();
                String a2 = Ұ��BOSSˢ��ʱ��.getValueAt(i, 2).toString();
                Ұ��BOSS���.setText(a);
                Ұ��BOSS.setText(a1);
                Ұ��BOSSˢ��ʱ��ֵ.setText(a2);
            }
        });
    }
    private void ˢ��Ұ��BOSSˢ��ʱ���޸�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ��Ұ��BOSSˢ��ʱ���޸�ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.Ұ��BOSSˢ��ʱ��ֵ.getText().matches("[0-9]+");

        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");

                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");

                ps1.setInt(1, Integer.parseInt(this.Ұ��BOSS���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update configvalues set Val = '" + this.Ұ��BOSSˢ��ʱ��ֵ.getText() + "' where id= " + this.Ұ��BOSS���.getText() + ";";
                    PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    Val.executeUpdate(sqlString1);
                    ˢ��Ұ��BOSSˢ��ʱ��();
                    gui.Start.GetConfigValues();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ����Ѿ���Ч");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨3��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�޸ĵ�ˢ��ʱ��");
        }
    }//GEN-LAST:event_ˢ��Ұ��BOSSˢ��ʱ���޸�ActionPerformed

    private void ���������޸�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���������޸�ActionPerformed
        boolean result1 = this.�������ӽ������.getText().matches("[0-9]+");
        boolean result2 = this.�������Ӽ��.getText().matches("[0-9]+");
        boolean result3 = this.�������ӵ���.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result1 && result2 && result3) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 2302);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.�������ӽ������.getText() + "' where id = 210000;";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);

                    String sqlString3 = null;
                    sqlString3 = "update configvalues set Val='" + this.�������Ӽ��.getText() + "' where id = 210001;";
                    PreparedStatement dropperid3 = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    dropperid3.executeUpdate(sqlString3);

                    String sqlString4 = null;
                    sqlString4 = "update configvalues set Val='" + this.�������ӵ���.getText() + "' where id = 210002;";
                    PreparedStatement dropperid4 = DatabaseConnection.getConnection().prepareStatement(sqlString4);
                    dropperid4.executeUpdate(sqlString4);
                    gui.Start.GetConfigValues();
                    ˢ�²�������();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_���������޸�ActionPerformed

    private void ���������޸�1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���������޸�1ActionPerformed
        boolean result0 = this.ԡͰ���.getText().matches("[0-9]+");
        boolean result1 = this.ԡͰ����1.getText().matches("[0-9]+");
        boolean result2 = this.ԡͰ����2.getText().matches("[0-9]+");
        boolean result3 = this.ԡͰ����3.getText().matches("[0-9]+");
        boolean result4 = this.ԡͰ����4.getText().matches("[0-9]+");
        boolean result5 = this.ԡͰ����5.getText().matches("[0-9]+");
        boolean result6 = this.ԡͰ����6.getText().matches("[0-9]+");
        boolean result7 = this.ԡͰ����7.getText().matches("[0-9]+");
        boolean result8 = this.ԡͰ����8.getText().matches("[0-9]+");
        boolean result9 = this.ԡͰ����9.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result0 && result1 && result2 && result3 && result4 && result5 && result6 && result7 && result8 && result9) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 2302);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString0 = null;
                    sqlString0 = "update configvalues set Val='" + this.ԡͰ���.getText() + "' where id = 210010;";
                    PreparedStatement dropperid0 = DatabaseConnection.getConnection().prepareStatement(sqlString0);
                    dropperid0.executeUpdate(sqlString0);

                    String sqlString1 = null;
                    sqlString1 = "update configvalues set Val='" + this.ԡͰ����1.getText() + "' where id = 210011;";
                    PreparedStatement dropperid1 = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    dropperid1.executeUpdate(sqlString1);

                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.ԡͰ����2.getText() + "' where id = 210012;";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);

                    String sqlString3 = null;
                    sqlString3 = "update configvalues set Val='" + this.ԡͰ����3.getText() + "' where id = 210013;";
                    PreparedStatement dropperid3 = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    dropperid3.executeUpdate(sqlString3);

                    String sqlString4 = null;
                    sqlString4 = "update configvalues set Val='" + this.ԡͰ����4.getText() + "' where id = 210014;";
                    PreparedStatement dropperid4 = DatabaseConnection.getConnection().prepareStatement(sqlString4);
                    dropperid4.executeUpdate(sqlString4);

                    String sqlString5 = null;
                    sqlString5 = "update configvalues set Val='" + this.ԡͰ����5.getText() + "' where id = 210015;";
                    PreparedStatement dropperid5 = DatabaseConnection.getConnection().prepareStatement(sqlString5);
                    dropperid5.executeUpdate(sqlString5);

                    String sqlString6 = null;
                    sqlString6 = "update configvalues set Val='" + this.ԡͰ����6.getText() + "' where id = 210016;";
                    PreparedStatement dropperid6 = DatabaseConnection.getConnection().prepareStatement(sqlString6);
                    dropperid6.executeUpdate(sqlString6);

                    String sqlString7 = null;
                    sqlString7 = "update configvalues set Val='" + this.ԡͰ����7.getText() + "' where id = 210017;";
                    PreparedStatement dropperid7 = DatabaseConnection.getConnection().prepareStatement(sqlString7);
                    dropperid7.executeUpdate(sqlString7);

                    String sqlString8 = null;
                    sqlString8 = "update configvalues set Val='" + this.ԡͰ����8.getText() + "' where id = 210018;";
                    PreparedStatement dropperid8 = DatabaseConnection.getConnection().prepareStatement(sqlString8);
                    dropperid8.executeUpdate(sqlString8);

                    String sqlString9 = null;
                    sqlString9 = "update configvalues set Val='" + this.ԡͰ����9.getText() + "' where id = 210019;";
                    PreparedStatement dropperid9 = DatabaseConnection.getConnection().prepareStatement(sqlString9);
                    dropperid9.executeUpdate(sqlString9);

                    gui.Start.GetConfigValues();
                    ˢ������ԡͰ();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_���������޸�1ActionPerformed

    private void ħ�幥�ǿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ħ�幥�ǿ���ActionPerformed
        ��������("ħ�幥�ǿ���", 2404);
        ˢ��ħ�幥�ǿ���();
    }//GEN-LAST:event_ħ�幥�ǿ���ActionPerformed

    private void OX���⿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OX���⿪��ActionPerformed
        ��������("OX���⿪��", 2401);
        ˢ��OX���⿪��();
    }//GEN-LAST:event_OX���⿪��ActionPerformed

    private void ÿ���ͻ�����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ÿ���ͻ�����ActionPerformed
        ��������("ÿ���ͻ�����", 2402);
        ˢ��ÿ���ͻ�����();
    }//GEN-LAST:event_ÿ���ͻ�����ActionPerformed

    private void ����ְҵ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ְҵ����ActionPerformed
        ��������("����ְҵ����", 749);
        ˢ������ְҵ����();
    }//GEN-LAST:event_����ְҵ����ActionPerformed

    private void ħ��ͻϮ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ħ��ͻϮ����ActionPerformed
        ��������("ħ��ͻϮ����", 2400);
        ˢ��ħ��ͻϮ����();
    }//GEN-LAST:event_ħ��ͻϮ����ActionPerformed

    private void ÿ���ͻ�����1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ÿ���ͻ�����1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ÿ���ͻ�����1ActionPerformed

    private void ��ĩ���ʿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ĩ���ʿ���ActionPerformed
        ��������("��ĩ���ʿ���", 2405);
        ˢ����ĩ���ʿ���();
    }//GEN-LAST:event_��ĩ���ʿ���ActionPerformed

    private void ÿ���ͻ�����3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ÿ���ͻ�����3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ÿ���ͻ�����3ActionPerformed

    private void �������˿���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�������˿���ActionPerformed
        ��������("�������˿���", 2406);
        ˢ���������˿���();
    }//GEN-LAST:event_�������˿���ActionPerformed

    private void Ұ��ͨ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ұ��ͨ������ActionPerformed
        ��������("Ұ��ͨ������", 2407);
        ˢ��Ұ��ͨ������();
    }//GEN-LAST:event_Ұ��ͨ������ActionPerformed

    private void ϲ���콵����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ϲ���콵����ActionPerformed
        ��������("ϲ���콵����", 2408);
        ˢ��ϲ���콵����();
    }//GEN-LAST:event_ϲ���콵����ActionPerformed

    private void ¼�����ⰴťActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_¼�����ⰴťActionPerformed
        if (¼������.getText().equals("") || ¼���.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "����ʹ𰸲���Ϊ��");
            return;
        }
        if (!¼���.getText().equals("O") && !¼���.getText().equals("X")) {
            JOptionPane.showMessageDialog(null, "��ֻ�ܴ�д�� O ���� X");
            return;
        }
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO oxdt (b, c) VALUES (?, ?)")) {
            ps.setString(1, ¼������.getText());
            ps.setString(2, ¼���.getText());
            ps.executeUpdate();
            ˢ�����();

        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_¼�����ⰴťActionPerformed

    private void �޸����ⰴťActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸����ⰴťActionPerformed
        if (!¼���.getText().equals("O") && !¼���.getText().equals("X")) {
            JOptionPane.showMessageDialog(null, "��ֻ�ܴ�д�� O ���� X");
            return;
        }
        boolean result1 = this.¼�����.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE oxdt SET b = ?, c = ? WHERE a = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM oxdt WHERE a = ?");
                ps1.setInt(1, Integer.parseInt(this.¼�����.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    String sqlString3 = null;
                    sqlString2 = "update oxdt set b='" + this.¼������.getText() + "' where a=" + this.¼�����.getText() + ";";
                    PreparedStatement priority = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    priority.executeUpdate(sqlString2);
                    sqlString3 = "update oxdt set c='" + this.¼���.getText() + "' where a=" + this.¼�����.getText() + ";";
                    PreparedStatement period = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    period.executeUpdate(sqlString3);
                    ˢ�����();

                }
            } catch (SQLException ex) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "��������Ҫ�޸ĵ�����");
        }
    }//GEN-LAST:event_�޸����ⰴťActionPerformed

    private void ɾ�����ⰴťActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ�����ⰴťActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.¼�����.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM oxdt WHERE a = ?");
                ps1.setInt(1, Integer.parseInt(this.¼�����.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from oxdt where a =" + Integer.parseInt(this.¼�����.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ˢ�����();

                }
            } catch (SQLException ex) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ�������� ");
        }
    }//GEN-LAST:event_ɾ�����ⰴťActionPerformed

    private void ������������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������������ActionPerformed
        ��������("������������", 2409);
        ˢ��������������();
    }//GEN-LAST:event_������������ActionPerformed

    private void ��ҿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ҿ���ActionPerformed
        ��������("�����ҿ���", 4002);
        ˢ�·����ҿ���();
    }//GEN-LAST:event_��ҿ���ActionPerformed

    private void ���鿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���鿪��ActionPerformed
        ��������("���㾭�鿪��", 4001);
        ˢ�·��㾭�鿪��();
    }//GEN-LAST:event_���鿪��ActionPerformed

    private void ��ȯ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ȯ����ActionPerformed
        ��������("�����ȯ����", 4003);
        ˢ�·����ȯ����();
    }//GEN-LAST:event_��ȯ����ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = ��������.getText().matches("[0-9]+");
        boolean result2 = ��ұ���.getText().matches("[0-9]+");
        boolean result3 = ���鱶��.getText().matches("[0-9]+");
        boolean result4 = ��ȯ����.getText().matches("[0-9]+");
        if (result1 && result2 && result3 && result4) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues ");
                rs = ps.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update configvalues set Val = '" + ��������.getText() + "' where id = 4010;";
                    PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    Val.executeUpdate(sqlString1);

                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val = '" + ���鱶��.getText() + "' where id = 4011;";
                    PreparedStatement Val2 = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    Val2.executeUpdate(sqlString2);

                    String sqlString3 = null;
                    sqlString3 = "update configvalues set Val = '" + ��ұ���.getText() + "' where id = 4012;";
                    PreparedStatement Val3 = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    Val3.executeUpdate(sqlString3);

                    String sqlString4 = null;
                    sqlString4 = "update configvalues set Val = '" + ��ȯ����.getText() + "' where id = 4013;";
                    PreparedStatement Val4 = DatabaseConnection.getConnection().prepareStatement(sqlString4);
                    Val4.executeUpdate(sqlString4);
                    gui.Start.GetConfigValues();
                    ˢ�·�������();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ����Ѿ���Ч");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨3��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��������������");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void �޸���������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸���������ActionPerformed
        boolean result1 = �������˳���ʱ��.getText().matches("[0-9]+");
        if (result1) {
            MapleParty.��������ʱ�� = Integer.parseInt(�������˳���ʱ��.getText());
            ˢ����������ʱ��();
            JOptionPane.showMessageDialog(null, "�޸ĳɹ����������˳���ʱ����Ϊ " + �������˳���ʱ��.getText());
        }
    }//GEN-LAST:event_�޸���������ActionPerformed

    private void ����ְҵ�޸�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ְҵ�޸�ActionPerformed
        boolean result1 = ����ְҵ����.getText().matches("[0-9]+");
        if (result1) {
            MapleParty.����ְҵ = Integer.parseInt(����ְҵ����.getText());
            ˢ������ְҵ();
            JOptionPane.showMessageDialog(null, "�޸ĳɹ�������ְҵ���Ϊ " + ����ְҵ����.getText());
        }
    }//GEN-LAST:event_����ְҵ�޸�ActionPerformed

    private void �޸���������1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸���������1ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = ��Ӧ������ʾ.getText().matches("[0-9]+");

        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues ");
                rs = ps.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update configvalues set Val = '" + ��Ӧ������ʾ.getText() + "' where id = 4100;";
                    PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    Val.executeUpdate(sqlString1);
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ����Ѿ���Ч");
                    gui.Start.GetConfigValues();
                    ˢ����Ӧ����();
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨3��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��������������");
        }
    }//GEN-LAST:event_�޸���������1ActionPerformed

    private void ���������޸�2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���������޸�2ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = ��Ҷ����ʽ��.getText().matches("[0-9]+");
        boolean result2 = ��Ҷ����ʾ���.getText().matches("[0-9]+");
        boolean result3 = ��Ҷ���������.getText().matches("[0-9]+");
        boolean result4 = ��Ҷ����ʼ��.getText().matches("[0-9]+");
        if (result1 && result2 && result3 && result4) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues ");
                rs = ps.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update configvalues set Val = '" + ��Ҷ����ʽ��.getText() + "' where id = 210020;";
                    PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    Val.executeUpdate(sqlString1);

                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val = '" + ��Ҷ����ʾ���.getText() + "' where id = 210021;";
                    PreparedStatement Val2 = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    Val2.executeUpdate(sqlString2);

                    String sqlString3 = null;
                    sqlString3 = "update configvalues set Val = '" + ��Ҷ���������.getText() + "' where id = 210022;";
                    PreparedStatement Val3 = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    Val3.executeUpdate(sqlString3);

                    String sqlString4 = null;
                    sqlString4 = "update configvalues set Val = '" + ��Ҷ����ʼ��.getText() + "' where id = 210023;";
                    PreparedStatement Val4 = DatabaseConnection.getConnection().prepareStatement(sqlString4);
                    Val4.executeUpdate(sqlString4);
                    gui.Start.GetConfigValues();
                    ˢ�·�������();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ����Ѿ���Ч");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨3��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��������������");
        }
    }//GEN-LAST:event_���������޸�2ActionPerformed

    private void ��ע�޸�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ע�޸�ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;

        try {
            ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM qqstem ");
            rs = ps.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                sqlString1 = "update qqstem set Name = '" + ��Ҷ����ʱ�ע.getText() + "' where channel = 3010025;";
                PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                Val.executeUpdate(sqlString1);

                String sqlString2 = null;
                sqlString2 = "update qqstem set Name = '" + ����ԡͰ��ע.getText() + "' where channel = 3012002;";
                PreparedStatement Val2 = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                Val2.executeUpdate(sqlString2);

                String sqlString3 = null;
                sqlString3 = "update qqstem set Name = '" + �������ӱ�ע.getText() + "' where channel = 3010100;";
                PreparedStatement Val3 = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                Val3.executeUpdate(sqlString3);
                ˢ�����ӱ�ע();
                JOptionPane.showMessageDialog(null, "�޸ĳɹ����Ѿ���Ч");
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨3��.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_��ע�޸�ActionPerformed

    private void �޸���������2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸���������2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_�޸���������2ActionPerformed

    private void �޸�BOSS�޴�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸�BOSS�޴�ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_�޸�BOSS�޴�ActionPerformed

    private void ˢ��BOSS�޴�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ��BOSS�޴�ActionPerformed
        ˢ��BOSS�޴�();
    }//GEN-LAST:event_ˢ��BOSS�޴�ActionPerformed

    private void �����Żݿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����Żݿ���ActionPerformed
        ��������("�����Żݿ���", 2410);
        ˢ�´����Żݿ���();
    }//GEN-LAST:event_�����Żݿ���ActionPerformed
    public void ˢ��BOSS�޴�() {
        for (int i = ((DefaultTableModel) (this.BOSS��������.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.BOSS��������.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM configvalues WHERE id >= 15000 && id < 16000");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) BOSS��������.getModel()).insertRow(BOSS��������.getRowCount(), new Object[]{rs.getString("id"), rs.getString("name"), rs.getString("Val")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        BOSS��������.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = BOSS��������.getSelectedRow();
                String a = BOSS��������.getValueAt(i, 0).toString();
                String a1 = BOSS��������.getValueAt(i, 1).toString();
                String a2 = BOSS��������.getValueAt(i, 2).toString();
                Ұ��BOSS���1.setText(a);
                BOSS.setText(a1);
                BOSS�޴�.setText(a2);
            }
        });

    }

    public void ��������(String a, int b) {
        int ��⿪�� = gui.Start.ConfigValuesMap.get(a);
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (��⿪�� > 0) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, b);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val= '0' where id= '" + b + "';";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, b);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val= '1' where id='" + b + "';";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        gui.Start.GetConfigValues();
    }

    public void Gainqqstem(String Name, int Channale, int Piot) {
        try {
            int ret = Getqqstem(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO qqstem (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);
                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e);
                    }
                }
            }
            ret += Piot;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE qqstem SET `Point` = ? WHERE Name = ? and channel = ?");
            ps.setInt(1, ret);
            ps.setString(2, Name);
            ps.setInt(3, Channale);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("Getqqstem!!55" + sql);
        }
    }

    public int Getqqstem(String Name, int Channale) {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM qqstem WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public static String ȡ���ӱ�ע(int a) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM qqstem");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("channel") == a) {
                    data = rs.getString("Name");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public void ˢ�����() {
        for (int i = ((DefaultTableModel) (this.OX�������.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.OX�������.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM oxdt order by a desc");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) OX�������.getModel()).insertRow(OX�������.getRowCount(), new Object[]{rs.getInt("a"), rs.getString("b"), rs.getString("c")});

            }
        } catch (SQLException ex) {
            Logger.getLogger(�����̨.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        OX�������.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = OX�������.getSelectedRow();
                String a = OX�������.getValueAt(i, 0).toString();
                String a1 = OX�������.getValueAt(i, 1).toString();
                String a2 = OX�������.getValueAt(i, 2).toString();
                ¼�����.setText(a);
                ¼������.setText(a1);
                ¼���.setText(a2);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(����̨3��.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(����̨3��.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(����̨3��.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(����̨3��.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        ����̨3��.setDefaultLookAndFeelDecorated(true);
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
                new ����̨3��().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BOSS;
    private javax.swing.JPanel BOSS���;
    private javax.swing.JTable BOSS��������;
    private javax.swing.JTextField BOSS�޴�;
    private javax.swing.JButton OX���⿪��;
    private javax.swing.JTable OX�������;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel249;
    private javax.swing.JLabel jLabel250;
    private javax.swing.JLabel jLabel251;
    private javax.swing.JLabel jLabel252;
    private javax.swing.JLabel jLabel253;
    private javax.swing.JLabel jLabel254;
    private javax.swing.JLabel jLabel255;
    private javax.swing.JLabel jLabel256;
    private javax.swing.JLabel jLabel257;
    private javax.swing.JLabel jLabel258;
    private javax.swing.JLabel jLabel259;
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
    private javax.swing.JLabel jLabel276;
    private javax.swing.JLabel jLabel277;
    private javax.swing.JLabel jLabel278;
    private javax.swing.JLabel jLabel279;
    private javax.swing.JLabel jLabel280;
    private javax.swing.JLabel jLabel281;
    private javax.swing.JLabel jLabel282;
    private javax.swing.JLabel jLabel283;
    private javax.swing.JLabel jLabel284;
    private javax.swing.JLabel jLabel285;
    private javax.swing.JLabel jLabel286;
    private javax.swing.JLabel jLabel287;
    private javax.swing.JLabel jLabel288;
    private javax.swing.JLabel jLabel289;
    private javax.swing.JLabel jLabel290;
    private javax.swing.JLabel jLabel291;
    private javax.swing.JLabel jLabel292;
    private javax.swing.JLabel jLabel293;
    private javax.swing.JLabel jLabel294;
    private javax.swing.JLabel jLabel295;
    private javax.swing.JLabel jLabel296;
    private javax.swing.JLabel jLabel297;
    private javax.swing.JLabel jLabel298;
    private javax.swing.JLabel jLabel299;
    private javax.swing.JLabel jLabel300;
    private javax.swing.JLabel jLabel301;
    private javax.swing.JLabel jLabel302;
    private javax.swing.JLabel jLabel323;
    private javax.swing.JLabel jLabel324;
    private javax.swing.JLabel jLabel325;
    private javax.swing.JLabel jLabel326;
    private javax.swing.JLabel jLabel327;
    private javax.swing.JLabel jLabel328;
    private javax.swing.JLabel jLabel329;
    private javax.swing.JLabel jLabel330;
    private javax.swing.JLabel jLabel336;
    private javax.swing.JLabel jLabel340;
    private javax.swing.JLabel jLabel342;
    private javax.swing.JLabel jLabel343;
    private javax.swing.JLabel jLabel344;
    private javax.swing.JLabel jLabel345;
    private javax.swing.JLabel jLabel350;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane104;
    private javax.swing.JScrollPane jScrollPane105;
    private javax.swing.JScrollPane jScrollPane110;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextField ����ģʽ1;
    private javax.swing.JButton �޸�BOSS�޴�;
    private javax.swing.JButton �޸���������;
    private javax.swing.JButton �޸���������1;
    private javax.swing.JButton �޸���������2;
    private javax.swing.JButton �޸����ⰴť;
    private javax.swing.JButton ɾ�����ⰴť;
    private javax.swing.JButton ˢ��BOSS�޴�;
    private javax.swing.JButton ˢ��Ұ��BOSSˢ��ʱ��;
    private javax.swing.JButton ˢ��Ұ��BOSSˢ��ʱ���޸�;
    private javax.swing.JButton ��ĩ���ʿ���;
    private javax.swing.JTextField ��Ӧ������ʾ;
    private javax.swing.JTextField ��Ӧ������ʾ1;
    private javax.swing.JButton ϲ���콵����;
    private javax.swing.JButton ��ע�޸�;
    private javax.swing.JTextField ����ְҵ����;
    private javax.swing.JButton ����ְҵ�޸�;
    private javax.swing.JButton ����ְҵ����;
    private javax.swing.JTextField ¼�����;
    private javax.swing.JTextField ¼���;
    private javax.swing.JTextField ¼������;
    private javax.swing.JButton ¼�����ⰴť;
    private javax.swing.JButton �����Żݿ���;
    private javax.swing.JTextField ��Ҷ���������;
    private javax.swing.JTextField ��Ҷ����ʱ�ע;
    private javax.swing.JTextField ��Ҷ����ʾ���;
    private javax.swing.JTextField ��Ҷ����ʽ��;
    private javax.swing.JTextField ��Ҷ����ʼ��;
    private javax.swing.JPanel �������;
    private javax.swing.JButton ÿ���ͻ�����;
    private javax.swing.JButton ÿ���ͻ�����1;
    private javax.swing.JButton ÿ���ͻ�����3;
    private javax.swing.JTextField ����ԡͰ��ע;
    private javax.swing.JPanel ��¼�;
    private javax.swing.JTextField ԡͰ����1;
    private javax.swing.JTextField ԡͰ����2;
    private javax.swing.JTextField ԡͰ����3;
    private javax.swing.JTextField ԡͰ����4;
    private javax.swing.JTextField ԡͰ����5;
    private javax.swing.JTextField ԡͰ����6;
    private javax.swing.JTextField ԡͰ����7;
    private javax.swing.JTextField ԡͰ����8;
    private javax.swing.JTextField ԡͰ����9;
    private javax.swing.JTextField ԡͰ���;
    private javax.swing.JTextField ��ȯ����;
    private javax.swing.JButton ��ȯ����;
    private javax.swing.JTextField �������˳���ʱ��;
    private javax.swing.JButton �������˿���;
    private javax.swing.JTextField ���鱶��;
    private javax.swing.JLabel ���鱶��2;
    private javax.swing.JButton ���鿪��;
    private javax.swing.JButton ���������޸�;
    private javax.swing.JButton ���������޸�1;
    private javax.swing.JButton ���������޸�2;
    private javax.swing.JTextField �������ӵ���;
    private javax.swing.JTextField �������ӱ�ע;
    private javax.swing.JTextField �������ӽ������;
    private javax.swing.JTextField �������Ӽ��;
    private javax.swing.JTextField Ұ��BOSS;
    private javax.swing.JTable Ұ��BOSSˢ��ʱ��;
    private javax.swing.JTextField Ұ��BOSSˢ��ʱ��ֵ;
    private javax.swing.JTextField Ұ��BOSS���;
    private javax.swing.JTextField Ұ��BOSS���1;
    private javax.swing.JButton Ұ��ͨ������;
    private javax.swing.JTextField ��ұ���;
    private javax.swing.JButton ��ҿ���;
    private javax.swing.JPanel ���;
    private javax.swing.JTextField ��������;
    private javax.swing.JButton ħ�幥�ǿ���;
    private javax.swing.JButton ħ��ͻϮ����;
    private javax.swing.JButton ������������;
    // End of variables declaration//GEN-END:variables

}
