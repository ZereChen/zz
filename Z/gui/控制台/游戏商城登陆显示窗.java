package gui.����̨;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.UIManager;
import gui.Start;
import static gui.Start.instance;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import static tools.FileoutputUtil.CurrentReadable_Time;

public class ��Ϸ�̳ǵ�½��ʾ�� extends javax.swing.JFrame {

    public ��Ϸ�̳ǵ�½��ʾ��() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        setIconImage(icon.getImage());
        setTitle("��Ϸ�̳ǵ�½��ʾ��");
        initComponents();
    }

    public static final Start getInstance() {
        return instance;
    }

    public void ��Ϸ�̳ǵ�½��ʾ��(String str) {
        ������ʾ��.setText("[" + CurrentReadable_Time() + "]:" + str + "\r\n" + ������ʾ��.getText() + "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ������ʾ�� = new javax.swing.JTextPane();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ������ʾ��.setBackground(new java.awt.Color(0, 0, 0));
        ������ʾ��.setFont(new java.awt.Font("��Բ", 1, 16)); // NOI18N
        ������ʾ��.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(������ʾ��);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 800));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 800));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        ��Ϸ�̳ǵ�½��ʾ��.setDefaultLookAndFeelDecorated(true);
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
                new ��Ϸ�̳ǵ�½��ʾ��().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane ������ʾ��;
    // End of variables declaration//GEN-END:variables
}
