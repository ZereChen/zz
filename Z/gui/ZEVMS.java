/*
ZEVMS
��������������Ĵ���
 */
package gui;

import gui.����̨.��ɫת�ƹ���;
import gui.����̨.�ű��༭��2;
import gui.����̨.�������̨;
import gui.����̨.һ����ԭ;
import gui.����̨.�����¼��ʾ;
import gui.����̨.��ֵ����̨;
import gui.����̨.������;
import gui.����̨.�����̨;
import gui.����̨.����̨2��;
import gui.����̨.����̨1��;
import gui.����̨.����̨3��;
import abc.Game;
import static abc.Game.IP��ַ;
import static abc.Game.���³���;
import static abc.Game.���������;
import static abc.Game.�������Ȩ��֤;
import static abc.Game.��������;
import static abc.Game.�汾;
import static abc.Game.��¼ͨ����Ȩ��;
import static abc.sancu.FileDemo_05.ɾ���ļ�;

import com.cyb.MonitorInfoBean;
import com.cyb.dao.IMonitorService;
import com.cyb.dao.MonitorServiceImpl;
import handling.world.MapleParty;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.SocketException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import database.DatabaseConnection;
import database1.DatabaseConnection1;
import gui.Jieya.��ѹ�ļ�;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.UIManager;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;

import java.sql.ResultSet;
import static a.�������ݿ�.���ݿ�����Ŀ¼;
import static a.�������ݿ�.���ݿ⵼��Ŀ¼;
import static a.�������ݿ�.���ݿ��ѹĿ¼;
import static abc.Game.˵��;
import static gui.Start.GetConfigValues;
import gui.����.������Ⱥ�������;
import static gui.ZEVMS.������;
import java.text.DateFormat;
import static tools.FileoutputUtil.CurrentReadable_Time;

public class ZEVMS extends javax.swing.JFrame {

    private static ZEVMS instance = new ZEVMS();
    public static һ����ԭ һ����ԭ;
    public static ����Ӧ�� ����Ӧ��;
    public static �������̨ �������̨;
    public static �ű��༭��2 �ű��༭��2;
    public static ������ ������;
    public static ����̨3�� jButton1111;
    public static �����̨ jButton11112;
    public static ����̨2�� jButton11113;
    public static ��ֵ����̨ jButton11115;
    public static ����̨1�� ����˹��ܿ���;
    public static �����¼��ʾ ��Ϣ���;
    public static ���������� ����������;
    public static ������Ⱥ������� ����;
    public static ��ɫת�ƹ��� ��ɫת�ƹ���;

    public static final ZEVMS getInstance() {
        return instance;
    }
    private static ScheduledFuture<?> ts = null;
    private int minutesLeft = 0;
    private static Thread t = null;

    //�������
    public static void main(String args[]) throws Exception {
        ˵��();
        System.out.println("�� ����˿�ʼ����");
        //���ý�����
        ZEVMS.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //����Ƿ�������();
        //��������ļ�();
        //������ݿ�(); todo:
        //���������ļ�
        Properties �O���n = System.getProperties();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("�� ��������˵�½����");
                new ZEVMS().setVisible(true);
            }
        });
    }

    /**
     * E:\BaiduNetdiskDownload\�����\ZEVMS\MYSQL\MySQL\data\zevms\mxmxd_fumo_info.zip
     */
    public static void ������ݿ�() {
        System.out.println("�� ��ʼ������ݿ�");
        //��ħ��ʾ�����
        try {
            �����ļ�("http://123.207.53.97:8082/ħ���ļ����/���ݿ�/mxmxd_fumo_info.zip", "mxmxd_fumo_info.zip", "" + ���ݿ�����Ŀ¼() + "/");
            ��ѹ�ļ�.��ѹ�ļ�(���ݿ��ѹĿ¼("mxmxd_fumo_info"), ���ݿ⵼��Ŀ¼("zevms"));
            ɾ���ļ�(���ݿ��ѹĿ¼("mxmxd_fumo_info"));
        } catch (Exception e) {

        }

        System.out.println("�� ���ݿ������");
    }

    public static void ��������ļ�() {
       
        //����ļ�1();
        //����ļ�3();        ������ݿ��("divine");
    }

    public static String ȡ������Ϣ(String a) {
        String data = "";
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ����˹��� ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("Ӧ��") == null ? a == null : rs.getString("Ӧ��").equals(a)) {
                    data = rs.getString("��Ϣ");
                }
            }
        } catch (SQLException ex) {
        }
        return data;
    }

    //�������������
    public ZEVMS() {
        initProperties();
        Properties �O���n = System.getProperties();
        //���ô���
        setTitle("" + ��������� + " Ver." + �汾 + "");
        //����
        initComponents();
        //�ı�����
        String ���1 = ȡ������Ϣ("����˹���");
        //����汾�ź͹�����Ϣ
        /*try {
            String versionInfo = Class2.Pv(�汾);
            String unicode = new String(versionInfo.getBytes(), "UTF-8");
            unicode = unicode.replace("\\n", "\n");
            ���1 = "" + unicode + "";

        } catch (Exception e) {
            System.out.println("��ȡ�汾��Ϣ����");
        }*/

        //��ȡ������������ʱ����ʾ
        if ("".equals(���1)) {
            ���1 = "\r\r\r\r\r\r\r                                                    ( >_< )����������Ӵ���  ��  ��  �� \r\n"
                    + "\r\n                                                    1.�������������в�����"
                    + "\r\n                                                    2.���ܷ���������ά����"
                    + "\r\n                                                    3.���ܷ����������в�����";
        }
        //�������
        print����(���1);
        //���ð汾�����ֵΪ0
        MapleParty.�汾��� = 0;
        //���ش���ͼ��
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        setIconImage(icon.getImage());
        GetConfigValues();
    //    �˺�.setText("" + gui.Start.ConfigValuesMap.get("��¼�˺�") + "");
        //ˢ�����ؿ���();
    }

    /**
     * todo�����ڸĳ��ļ���ȡ��������
     */
    private void initProperties() {
        System.setProperty("net.sf.odinms.wzpath", "wz");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ��������� = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ���� = new javax.swing.JTextPane();
        ��½LOGO = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        ���������1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ���������.setBackground(new java.awt.Color(255, 255, 255));
        ���������.setFont(new java.awt.Font("��Բ", 0, 16)); // NOI18N
        ���������.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ͼƬ/pp/����.png"))); // NOI18N
        ���������.setText("���������");
        ���������.setToolTipText("");
        ���������.setActionCommand("");
        ���������.setIconTextGap(3);
        ���������.setPreferredSize(new java.awt.Dimension(100, 30));
        ���������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���������ActionPerformed(evt);
            }
        });
        getContentPane().add(���������, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 450, 210, 25));

        jLabel3.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ͼƬ/��ͼ/��ͼ6.gif"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, -1, 60));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        ����.setEditable(false);
        ����.setBorder(null);
        ����.setText("��������");
        ����.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ����.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        ����.setSelectionColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(����);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 570, 320));

        ��½LOGO.setFont(new java.awt.Font("����", 1, 18)); // NOI18N
        ��½LOGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ͼƬ/WorldSelect.backgrnd_����.png"))); // NOI18N
        getContentPane().add(��½LOGO, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 450, -1, -1));

        ���������1.setBackground(new java.awt.Color(255, 255, 255));
        ���������1.setFont(new java.awt.Font("��Բ", 0, 16)); // NOI18N
        ���������1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ͼƬ/pp/����.png"))); // NOI18N
        ���������1.setText("���������");
        ���������1.setToolTipText("");
        ���������1.setActionCommand("");
        ���������1.setIconTextGap(3);
        ���������1.setPreferredSize(new java.awt.Dimension(100, 30));
        ���������1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���������1ActionPerformed(evt);
            }
        });
        getContentPane().add(���������1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 140, 25));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private static String ��ȡ����ʱ��(String webUrl) {
        try {
            URL url = new URL(webUrl);// ȡ����Դ����
            URLConnection uc = url.openConnection();// �������Ӷ���
            uc.connect();// ��������
            long ld = uc.getDate();// ��ȡ��վ����ʱ��
            Date date = new Date(ld);// ת��Ϊ��׼ʱ�����
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// �������ʱ��
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void ���������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���������ActionPerformed
        try {
            Start.instance.startServer();
        } catch (InterruptedException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_���������ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Start.instance.startServer();
        } catch (InterruptedException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ���������1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���������1ActionPerformed

      /*  boolean A = this.�˺�.getText().matches("[0-9]+");
        if (!A) {
            JOptionPane.showMessageDialog(null, "��������ȷ���˺š�");
            return;
        }
        if (�˺�.getText().equals("") || ����.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "�������˺����롣");
            return;
        }
        if (�ж��˺Ŵ���(�˺�.getText()) == 0) {
            JOptionPane.showMessageDialog(null, "�˺Ų����ڡ�");
            return;
        }
        if (!�ж��˺������Ƿ���ȷ(�˺�.getText()).equals(����.getText())) {
            JOptionPane.showMessageDialog(null, "�������");
            return;
        }
        if (�ж��˻�״̬(�˺�.getText()) != 0) {
            JOptionPane.showMessageDialog(null, "�ǳ���Ǹ������˺��Ѿ��������");
            return;
        }*/

     //   if ("".equals(��������(�˺�.getText()))) {
            MapleParty.�������� = "ð�յ�";
     //   } else {
       //     MapleParty.�������� = ��������(�˺�.getText());
       // }
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET dropperid = ? WHERE id = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
            ps1.setInt(1, 5000);
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString2 = null;
                sqlString2 = "update configvalues set Val='71447500' where id= 5000 ;";
                PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                dropperid.executeUpdate(sqlString2);
            }
        } catch (SQLException ex) {
        }
//        MapleParty.�������� = ��������(�˺�.getText());
       // MapleParty.IP��ַ = ��ϵ��֤(�˺�.getText());
                MapleParty.IP��ַ = "127.0.0.1";
        
    //    MapleParty.�����˺� = �˺�.getText();
        try {
            //����û����Ȩ��
            if (!�������Ȩ��֤()) {
            /*    MapleParty.�������� = 6;//�ж���������(�˺�.getText()
                System.out.println("�� ������������:" + MapleParty.��������);
                System.out.println("�� ����ʱ�� : " + ��ȡ����ʱ��(�˺�.getText()));
                System.out.println("�� ����ʱ�� : " + CurrentReadable_Time());
                �����˻�����(�˺�.getText(), 0);
                int i = compare_date(
                        "" + ��ȡ����ʱ��(�˺�.getText()) + "",//��������
                        ��ȡ����ʱ��("http://baidu.com")//��ȡ��ǰ����
                );
                if (i == 1) {
                    JOptionPane.showMessageDialog(null, "����˺��� " + ��ȡ����ʱ��(�˺�.getText()) + " �ѵ��ڡ�\r\n����Ⱥ�﷢�� *ǩ�� ָ�����ǩ�����ڡ�");
                    return;
                }
                //������Ȩ�˵�
            } else {*/
                MapleParty.�������� = 999;
                System.out.println("�� ������������:999");
                System.out.println("�� ����ʱ�� : " + CurrentReadable_Time());
                //���߷�����������Ȩ�ġ�
                //�����˻�����(�˺�.getText(), 1);
            }
        } catch (SocketException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        //��ʼ������˰汾
      /*  String v2 = Class2.Pgv();
        System.out.println("�� ������˰汾��Ϣ");
        //����Ƿ�ǿ�Ƹ���
        ����汾��(�˺�.getText(), �汾);
        if (ǿ�Ƹ���() == 0) {
            if (Integer.parseInt(v2) != �汾) {
                //�汾������������
                ����();
            }
        } else if (Integer.parseInt(v2) != �汾) {
            //�汾������������
            int n = JOptionPane.showConfirmDialog(this, "��⵽������и��£��Ƿ���и��£�", "���� v" + Integer.parseInt(v2) + "", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                ����();
            }
        }*/
        //�ո�
        System.out.println("");
        //������Ч
        try {
            if (Start.Check) {
                //���� startServer
                Start.instance.startServer();
                //��¼��Ȩ��
                ��¼ͨ����Ȩ��();
                Properties �O���n = System.getProperties();
                String ��Ч���� = �O���n.getProperty("user.dir") + "\\dist\\lib\\Start the success.mp3";
                try {
                    BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(��Ч����));
                    Player player = new Player(buffer);
                    player.play();
                } catch (Exception e) {
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "�޷��ظ����С�");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_���������1ActionPerformed
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

            }
        }
        gui.Start.GetConfigValues();
    }

//    private void ˢ�����ؿ���() {
//        String ��ʾ = "";
//        int S = gui.Start.ConfigValuesMap.get("���ؿ���");
//        if (S <= 0) {
//            ��ʾ = "����:����";
//        } else {
//            ��ʾ = "����:�ر�";
//        }
//        ���ؿ���.setText(��ʾ);
//    }
    public boolean ���ټ��() {
        //���������ټ��
        boolean connect = false;
        Runtime runtime = Runtime.getRuntime();
        Process process;
        try {
            process = runtime.exec("ping " + "" + �������� + "");
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            isr.close();
            br.close();

            if (null != sb && !sb.toString().equals("")) {
                if (sb.toString().indexOf("TTL") > 0) {
                } else {
                    JOptionPane.showMessageDialog(null, "�ӳ� " + sb.toString().indexOf("TTL") + "\r\n���������ٸ����ӳ� 10 ���޷�������");
                    System.exit(0);
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connect;
    }

    public boolean ���ټ��2() {
        //���ټ��2
        boolean connect = false;
        Runtime runtime = Runtime.getRuntime();
        Process process;
        String ��� = "";
        String ���1 = "";
        try {
            process = runtime.exec("ping " + "" + �������� + "");
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            isr.close();
            br.close();
            if (null != sb && !sb.toString().equals("")) {
                String logString = "";
                if (sb.toString().indexOf("TTL") > 0) {
                } else {
                    JOptionPane.showMessageDialog(null, "�ӳ� " + sb.toString().indexOf("TTL") + "\r\n���������ٸ����ӳ� 10 ���޷�������");
                    System.exit(0);
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connect;
    }

    public boolean isConnect() {
        boolean connect = false;
        Runtime runtime = Runtime.getRuntime();
        Process process;
        try {
            process = runtime.exec("ping " + "" + IP��ַ + "");
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            //System.out.println("����ֵΪ:" + sb);
            is.close();
            isr.close();
            br.close();

            if (null != sb && !sb.toString().equals("")) {
                String logString = "";
                if (sb.toString().indexOf("TTL") > 0) {
                    //���ټ��();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "�������粻�ȶ����޷����ӵ���������");

                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connect;
    }

    //��ȡʱ��
    private static String getWebsiteDatetime(String webUrl) {
        try {
            URL url = new URL(webUrl);// ȡ����Դ����
            URLConnection uc = url.openConnection();// �������Ӷ���
            uc.connect();// ��������
            long ld = uc.getDate();// ��ȡ��վ����ʱ��
            Date date = new Date(ld);// ת��Ϊ��׼ʱ�����
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// �������ʱ��
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //��� zevms079.exe �Ƿ����
    public static void ����ļ�1() {
        try {
            ByteArrayOutputStream baos = null;
            InputStream os = null;
            String s = "";
            Process p = Runtime.getRuntime().exec("cmd /c tasklist");
            baos = new ByteArrayOutputStream();
            final Runtime runtime = Runtime.getRuntime();
            Properties ZEV = System.getProperties();
            Process process = null;
            File file = new File(ZEV.getProperty("user.dir") + "/���³���.exe");
            //����ļ��в������򴴽�
            if (!file.exists() && !file.isDirectory()) {
                try {
                    �����ļ�("http://123.207.53.97:8082/ħ���ļ����/�ļ�����/���³���.exe", "���³���.exe", "" + ZEV.getProperty("user.dir") + "/");
                } catch (Exception e) {
                    System.err.println("�� ����Ҫ�������ļ�����");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ����ļ�3() {
        try {
            ByteArrayOutputStream baos = null;
            InputStream os = null;
            String s = "";
            Process p = Runtime.getRuntime().exec("cmd /c tasklist");
            baos = new ByteArrayOutputStream();
            final Runtime runtime = Runtime.getRuntime();
            Properties ZEV = System.getProperties();
            Process process = null;
            File file = new File(ZEV.getProperty("user.dir") + "/Load/��ϷƵ��״̬��ʾ.txt");
            //����ļ��в������򴴽�
            if (!file.exists() && !file.isDirectory()) {
                try {
                    �����ļ�("http://123.207.53.97:8082/ħ���ļ����/�ļ�����/��ϷƵ��״̬��ʾ.txt", "��ϷƵ��״̬��ʾ.txt", "" + ZEV.getProperty("user.dir") + "/Load/");
                } catch (Exception e) {
                    System.err.println("�� ����Ҫ�������ļ�����3��" + e);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void �����ļ�(String urlStr, String fileName, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //���ó�ʱ��Ϊ3��
        conn.setConnectTimeout(3 * 1000);
        //��ֹ���γ���ץȡ������403����
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //�õ�������
        InputStream inputStream = conn.getInputStream();
        //��ȡ�Լ�����
        byte[] getData = readInputStream(inputStream);

        //�ļ�����λ��
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }

    }

    public static boolean ����Ƿ�������() {
        System.out.println("�� ��ʼ������������ͨ��");
        long start = System.currentTimeMillis();
        boolean connect = false;
        Runtime runtime = Runtime.getRuntime();
        Process process;
        String ��� = "";
        String ���1 = "";
        try {
            process = runtime.exec("ping " + "" + �������� + "");
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            isr.close();
            br.close();

            if (null != sb && !sb.toString().equals("")) {
                String logString = "";
                if (sb.toString().indexOf("TTL") > 0) {
                    long now = System.currentTimeMillis() - start;
                    System.out.println("�� ������ͨ�ż����ɣ�" + now + "");
                } else {
                    JOptionPane.showMessageDialog(null, "û������������粻�ȶ����޷���������ˡ�");
                    System.exit(0);
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("�� ������ͨ�ż��ʧ�ܣ�" + e + "");

        }
        return connect;
    }

    public static boolean ����Ƿ�������2() {
        System.out.println("�� ��ʼ��������������");
        long start = System.currentTimeMillis();
        boolean connect = false;
        Runtime runtime = Runtime.getRuntime();
        Process process;
        String ��� = "";
        String ���1 = "";
        try {
            process = runtime.exec("ping " + "" + IP��ַ + "");
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            isr.close();
            br.close();

            if (null != sb && !sb.toString().equals("")) {
                String logString = "";
                if (sb.toString().indexOf("TTL") > 0) {
                    long now = System.currentTimeMillis() - start;
                    System.out.println("�� ����������������ɣ�" + now + "");
                } else {
                    JOptionPane.showMessageDialog(null, "δ���ӵ����أ�������ϻ�������ά����");
                    System.exit(0);
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("�� ���ӵ�����ʧ�ܣ�" + e + "");
        }
        return connect;
    }

    //���³���
    public static void ����() {
        //�������³���
        try {
            InetAddress addr = InetAddress.getLocalHost();
            final Runtime runtime = Runtime.getRuntime();
            Process process = null;
            Properties �O���n = System.getProperties();
            try {
                //��ָ������
                process = runtime.exec("" + �O���n.getProperty("user.dir") + "/" + ���³��� + "");
                //�ر�ָ������
                Runtime.getRuntime().exec("Taskkill /IM ���������.exe");
                Runtime.getRuntime().exec("Taskkill /IM " + �O���n.getProperty("user.dir") + "/���������.exe");
                System.exit(0);
            } catch (final Exception e) {
                System.out.println("�Զ����³�������ʧ�ܣ�");
                System.out.println("ʧ��ԭ��" + process + "");
                System.out.println("ʧ��ԭ��δ��װNET4.5֧�ֿ�");
            }
        } catch (Exception e) {
        }
    }

    //����Զ��
    public static void ����Զ��() {
        //�������³���
        try {
            InetAddress addr = InetAddress.getLocalHost();
            final Runtime runtime = Runtime.getRuntime();
            Process process = null;
            Properties �O���n = System.getProperties();
            try {
                //��ָ������
                process = runtime.exec("" + �O���n.getProperty("user.dir") + "/jdk/bin/zevms079.exe");
            } catch (final Exception e) {
                System.out.println("ȱ�������ļ����鿴�Ƿ񱻲�ɱ������������ˣ�������ϵ����:71447500");
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //�������������Ƿ��������
    public static void ������������() throws Exception {
        //���������ڴ�
        long start = System.currentTimeMillis();
        System.out.println("�� ��ʼ������������");
        IMonitorService service = new MonitorServiceImpl();
        MonitorInfoBean monitorInfo = service.getMonitorInfoBean();
        int �������ڴ� = (int) (monitorInfo.getTotalMemorySize() / 1024 / 1024);
        if (�������ڴ� < 4) {
            System.out.println("�� ��������Ҫ�� 4G �����ڴ棬��ķ������ڴ�Ϊ  " + (�������ڴ� + 1) + " ���������������Ҫ��");
            System.exit(0);
        }
        long now = System.currentTimeMillis() - start;
        System.out.println("�� ���������ü�����(4/" + (�������ڴ� + 1) + ")��" + now + "");
    }

    //���������Ƿ�����
    public static boolean ��֤�������Ƿ�����() {
        //����Ƿ�����ָ������
        ByteArrayOutputStream baos = null;
        InputStream os = null;
        String s = "";
        try {
            Process p = Runtime.getRuntime().exec("cmd /c tasklist");
            baos = new ByteArrayOutputStream();
            os = p.getInputStream();
            byte b[] = new byte[256];
            while (os.read(b) > 0) {
                baos.write(b);
            }
            s = baos.toString();
            if (s.indexOf("CQA.exe") >= 0) {

            } else {
                System.out.println("��ѡ��򿪻����ˣ���������δ����������������������������ˡ�");
                System.exit(0);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //���ó�ʱ��Ϊ3��
        conn.setConnectTimeout(3 * 1000);
        //��ֹ���γ���ץȡ������403����
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //�õ�������
        InputStream inputStream = conn.getInputStream();
        //��ȡ�Լ�����
        byte[] getData = readInputStream(inputStream);

        //�ļ�����λ��
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }

        System.out.println("info:" + url + " download success");

    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static void Զ����������() throws IOException {
        long start = System.currentTimeMillis();
        //System.out.println("�� ��ʼ��ȡ������Ϣyans");
        try {
            URL url = new URL("http://123.207.53.97:8082/ħ���ļ����/����˿���/Զ����������.txt");
            Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
            int c;
            while ((c = reader.read()) != -1) {
                switch (c) {
                    case 48:
                        //0 = 48
                        MapleParty.Զ���������� = 0;
                        //System.out.print("��");
                        break;
                    case 49:
                        //1 = 49
                        MapleParty.Զ���������� = 1;
                        //System.out.print("��");
                        break;
                    default:
                        System.out.print("yans���󣡣���");
                        break;
                }
            }
            long now = System.currentTimeMillis() - start;
            System.out.println("�� ȡ������Ϣyans��ɣ�" + now + "");
            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "���ݶ�ȡʧ��:-yans\r\n����ϵСz");
            System.exit(0);
        }
    }

    public static void �ű��༭����Ȩ����() throws IOException {
        long start = System.currentTimeMillis();
        //System.out.println("�� ��ʼ��ȡ������Ϣqans");
        try {
            URL url = new URL("http://123.207.53.97:8082/ħ���ļ����/����˿���/�ű��༭������.txt");
            Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
            int c;
            while ((c = reader.read()) != -1) {
                switch (c) {
                    case 48:
                        //0 = 48
                        MapleParty.�ű��༭����Ȩ���� = 0;
                        //System.out.print("��");
                        break;
                    case 49:
                        //1 = 49
                        MapleParty.�ű��༭����Ȩ���� = 1;
                        //System.out.print("��");
                        break;
                    default:
                        System.out.print("qans���󣡣���");
                        break;
                }
            }
            long now = System.currentTimeMillis() - start;
            System.out.println("�� ȡ������Ϣbans��ɣ�" + now + "");
            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "���ݶ�ȡʧ��:-bans\r\n����ϵСz");
            System.exit(0);
        }
    }

    public static void ǿ�Ƹ��¿���() throws IOException {
        long start = System.currentTimeMillis();
        //System.out.println("�� ��ʼ��ȡ������Ϣqans");
        try {
            URL url = new URL("http://123.207.53.97:8082/ħ���ļ����/����˿���/ǿ�Ƹ��¿���.txt");
            Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
            int c;
            while ((c = reader.read()) != -1) {
                switch (c) {
                    case 48:
                        //0 = 48
                        MapleParty.ǿ�Ƹ��¿��� = 0;
                        //System.out.print("��");
                        break;
                    case 49:
                        //1 = 49
                        MapleParty.ǿ�Ƹ��¿��� = 1;
                        //System.out.print("��");
                        break;
                    default:
                        System.out.print("qans���󣡣���");
                        break;
                }
            }
            long now = System.currentTimeMillis() - start;
            System.out.println("�� ȡ������Ϣqans��ɣ�" + now + "");
            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "���ݶ�ȡʧ��:-qans\r\n����ϵСz");
            System.exit(0);
        }
    }

    public static void ����Ķ�����() throws IOException {
        long start = System.currentTimeMillis();
        //System.out.println("�� ��ʼ��ȡ������Ϣqans");
        try {
            URL url = new URL("http://123.207.53.97:8082/ħ���ļ����/����˿���/����Ķ�����.txt");
            Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
            int c;
            while ((c = reader.read()) != -1) {
                switch (c) {
                    case 48:
                        //0 = 48
                        MapleParty.����Ķ����� = 0;
                        //System.out.print("��");
                        break;
                    case 49:
                        //1 = 49
                        MapleParty.����Ķ����� = 1;
                        //System.out.print("��");
                        break;
                    default:
                        System.out.print("gans���󣡣���");
                        break;
                }
            }
            long now = System.currentTimeMillis() - start;
            System.out.println("�� ȡ������Ϣgans��ɣ�" + now + "");
            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "���ݶ�ȡʧ��:-gans\r\n����ϵСz");
            System.exit(0);
        }
    }

    public static void ���ü�⿪��() throws IOException {
        long start = System.currentTimeMillis();
        //System.out.println("�� ��ʼ��ȡ������Ϣpans");
        try {
            URL url = new URL("http://123.207.53.97:8082/ħ���ļ����/����˿���/���ü�⿪��.txt");
            Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
            int c;
            while ((c = reader.read()) != -1) {
                switch (c) {
                    case 48:
                        //0 = 48
                        MapleParty.���ü�⿪�� = 0;
                        //System.out.print("��");
                        break;
                    case 49:
                        //1 = 49
                        MapleParty.���ü�⿪�� = 1;
                        //System.out.print("��");
                        break;
                    default:
                        System.out.print("pans���󣡣���");
                        break;
                }
            }
            long now = System.currentTimeMillis() - start;
            System.out.println("�� ȡ������Ϣpans��ɣ�" + now + "");
            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "���ݶ�ȡʧ��:-pans\r\n����ϵСz");
            System.exit(0);
        }
    }

    public static void ά�����¿���() throws IOException {
        long start = System.currentTimeMillis();
        //System.out.println("�� ��ʼ��ȡ������Ϣpans");
        try {
            URL url = new URL("http://123.207.53.97:8082/ħ���ļ����/����˿���/ά�����¿���.txt");
            Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
            int c;
            while ((c = reader.read()) != -1) {
                switch (c) {
                    case 48:
                        JOptionPane.showMessageDialog(null, "���������ά���У��޷�������\r\n������QQȺ����8091����֪ͨ��");
                        System.exit(0);
                        break;
                    case 49:

                        break;
                    default:
                        System.out.print("pans���󣡣���");
                        break;
                }
            }
            long now = System.currentTimeMillis() - start;
            //System.out.println("�� ȡ������Ϣpans��ɣ�" + now + "");
            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "���ݶ�ȡʧ��:-wans\r\n����ϵСz");
            System.exit(0);
        }
    }

    public static void �Զ����¿���() throws IOException {

        long start = System.currentTimeMillis();
        //System.out.println("�� ��ʼ��ȡ������Ϣzans");
        try {
            URL url = new URL("http://123.207.53.97:8082/ħ���ļ����/����˿���/�Զ����¿���.txt");
            Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
            int c;
            while ((c = reader.read()) != -1) {
                switch (c) {
                    case 48:
                        //0 = 48
                        MapleParty.�Զ����¿��� = 0;
                        //System.out.print("��");
                        break;
                    case 49:
                        //1 = 49
                        MapleParty.�Զ����¿��� = 1;
                        //System.out.print("��");
                        break;
                    default:
                        System.out.print("pans���󣡣���");
                        break;
                }
            }
            long now = System.currentTimeMillis() - start;
            System.out.println("�� ȡ������Ϣzans��ɣ�" + now + "");
            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "���ݶ�ȡʧ��:-zans\r\n����ϵСz");
            System.exit(0);
        }
    }

    class D extends JDialog {

        public D() {
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }

    private void print����(String str) {
        ����.setText(str);
    }

    public static void һ����ԭ() {

        if (һ����ԭ != null) {
            һ����ԭ.dispose();
        }
        һ����ԭ = new һ����ԭ();
        һ����ԭ.setVisible(true);

    }

    public static void ����Ӧ��() {
        if (����Ӧ�� != null) {
            ����Ӧ��.dispose();
        }
        ����Ӧ�� = new ����Ӧ��();
        ����Ӧ��.setVisible(true);

    }

    public static void �������̨() {
        if (�������̨ != null) {
            �������̨.dispose();
        }
        �������̨ = new �������̨();
        �������̨.setVisible(true);

    }

    public static void ��ɫת�ƹ���() {
        if (��ɫת�ƹ��� != null) {
            ��ɫת�ƹ���.dispose();
        }
        ��ɫת�ƹ��� = new ��ɫת�ƹ���();
        ��ɫת�ƹ���.setVisible(true);

    }

    public static void ������() {

        if (������ != null) {
            ������.dispose();
        }
        ������ = new ������();
        ������.setVisible(true);

    }

    public static void �ڲ�������() {

        if (һ����ԭ != null) {
            һ����ԭ.dispose();
        }
        һ����ԭ = new һ����ԭ();
        һ����ԭ.setVisible(true);

    }

    public static void ��������̨() {
        if (jButton1111 != null) {
            jButton1111.dispose();
        }
        jButton1111 = new ����̨3��();
        jButton1111.setVisible(true);
    }

    public static void ���ÿ���̨() {
        if (jButton11113 != null) {
            jButton11113.dispose();
        }
        jButton11113 = new ����̨2��();
        jButton11113.setVisible(true);
    }

    public static void �����̨() {
        if (jButton11112 != null) {
            jButton11112.dispose();
        }
        jButton11112 = new �����̨();
        jButton11112.setVisible(true);
    }

    public static void ����������() {
        if (���������� != null) {
            ����������.dispose();
        }
        ���������� = new ����������();
        ����������.setVisible(true);
    }

    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                //System.out.println("��������ʹ��");
                return 0;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("�Ѿ����ڣ��޷�����ʹ��");
                System.exit(0);
                return 1;
            } else {
                return 2;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane ����;
    private javax.swing.JButton ���������;
    private javax.swing.JButton ���������1;
    private javax.swing.JLabel ��½LOGO;
    // End of variables declaration//GEN-END:variables

}
/*
@echo off
@title ZEVMSð�յ�(079)��Ϸ�����[��ش���]
color 1A
set PATH=jdk\jre\bin
set JRE_HOME=jdk\jre
set JAVA_HOME=jdk\jre\bin
set CLASSPATH=.;dist\*
java -server -Dnet.sf.odinms.wzpath=wz gui.ZEVMS
pause


����ʾ����
if "%1"=="h" goto begin 
start mshta vbscript:createobject("wscript.shell").run("""%~nx0"" h",0)
(window.close)&&exit 
:begin

@echo off
if "%1"=="h" goto begin 
start mshta vbscript:createobject("wscript.shell").run("""%~nx0"" h",0)(window.close)&&exit 
:begin
set PATH=jdk\jre\bin
set JRE_HOME=jdk\jre
set JAVA_HOME=jdk\jre\bin
set CLASSPATH=.;jdk\*
java -server -Dnet.sf.odinms.wzpath=wz gui.ZEVMSdlQ
 */
