package gui.����;

import static a.�������ݿ�.�����������Ŀ¼;
import static a.�������ݿ�.������µ���Ŀ¼;
import static a.�������ݿ�.������½�ѹĿ¼;
import static abc.sancu.FileDemo_05.ɾ���ļ�;
import gui.Jieya.��ѹ�ļ�;
import static gui.ZEVMS.�����ļ�;
import gui.����̨.����̨2��;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.UIManager;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;

public class ������Ⱥ������� extends javax.swing.JFrame {

    public ������Ⱥ�������() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        setIconImage(icon.getImage());
        setTitle("ZEVMS���غ�̨");
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            �����ļ�("http://123.207.53.97:8082/�����޸�/�����޸�.zip", "�����޸�.zip", "" + �����������Ŀ¼() + "/");
            ��ѹ�ļ�.��ѹ�ļ�(������½�ѹĿ¼("�����޸�"), ������µ���Ŀ¼("zevms"));
            ɾ���ļ�(������½�ѹĿ¼("�����޸�"));
        } catch (Exception e) {

        }

    }//GEN-LAST:event_jButton1ActionPerformed
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
