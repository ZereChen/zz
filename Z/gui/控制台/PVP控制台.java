package gui.����̨;

import static abc.Game.���ڱ���;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.UIManager;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;

public class PVP����̨ extends javax.swing.JFrame {

    public PVP����̨() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        setIconImage(icon.getImage());
        setTitle("" + ���ڱ��� + "��PVP����̨���ɹرա�");
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        ���� = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        սʿ = new javax.swing.JPanel();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ����.setBackground(new java.awt.Color(255, 255, 255));
        ����.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pvp/����ͼ��/����/1000.png"))); // NOI18N
        jLabel1.setText("��ţͶ����");
        ����.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 130, 40));

        jTabbedPane1.addTab("����", ����);

        սʿ.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.addTab("սʿ", սʿ);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                new PVP����̨().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel սʿ;
    private javax.swing.JPanel ����;
    // End of variables declaration//GEN-END:variables
}
