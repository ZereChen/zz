package gui.����̨;

import database1.DatabaseConnection1;
import static download.Toupdate.downLoadFromUrl;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.UIManager;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;

public class �ű������� extends javax.swing.JFrame {

    public �ű�������() {

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/pp/2.png"));
        Image background = new ImageIcon("gui/1.png").getImage();
        setIconImage(icon.getImage());
        setTitle("���߸��³���");
        initComponents();
        ��ʾ.setText("����׼�����³���...");
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 5);
                    ��ʼ����();
                } catch (InterruptedException e) {
                }
            }
        }.start();
    }

    public void Z(int i) {
        ����������.setValue(i);
    }
    public static int ���� = 0;
    public static int �� = 0;

    public void ��ʼ����() {
        int ����ֵ = 100 / �����������();
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �ű����� ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Properties �O���n = System.getProperties();
                try {
                    downLoadFromUrl("http://123.207.53.97:8082/ħ���ļ����/�ļ�����/" + rs.getString("����·��") + "/" + rs.getString("�ļ���") + "", "" + rs.getString("�ļ���") + "", "" + �O���n.getProperty("user.dir") + "/" + rs.getString("����·��") + "");
                    �� += 1;
                    Z(���� += ����ֵ);
                    if (�� == �����������()) {
                        ��ʾ.setText("������ɣ�3����Զ��˳���");
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000 * 3);
                                    dispose();
                                } catch (InterruptedException e) {
                                }
                            }
                        }.start();
                    } else {
                        ��ʾ.setText("����:" + rs.getString("�ļ���"));
                    }
                } catch (Exception e) {
                    System.out.println("" + rs.getString("�ļ���") + "���³���");
                }
            }
        } catch (SQLException ex) {
        }
    }

    public static int �����������() {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM �ű�����");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data += 1;
            }
        } catch (SQLException ex) {
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ���������� = new javax.swing.JProgressBar();
        ��ʾ = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(����������, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 520, 20));

        ��ʾ.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        ��ʾ.setForeground(new java.awt.Color(255, 255, 255));
        ��ʾ.setText("ZEVMS���߸��³���");
        jPanel1.add(��ʾ, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 370, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/LOGO/A1_����.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 170));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(�ű�������.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(�ű�������.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(�ű�������.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(�ű�������.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

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
                new �ű�������().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar ����������;
    private javax.swing.JLabel ��ʾ;
    // End of variables declaration//GEN-END:variables

}
