package gui.����̨;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.UIManager;
import gui.Start;
import static gui.Start.instance;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;

public class �����¼��ʾ extends javax.swing.JFrame {

    public �����¼��ʾ() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        setIconImage(icon.getImage());
        setTitle("��������¼��Ϣ");
        initComponents();
    }

    public static final Start getInstance() {
        return instance;
    }

    public void ��Ϸ�˵�½��Ϣ���(String str) {
        ��Ϸ�˵�½��Ϣ.setText(��Ϸ�˵�½��Ϣ.getText() + str);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        ��Ϸ�˵�½��Ϣ = new javax.swing.JTextPane();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��Ϸ�˵�½��Ϣ.setFont(new java.awt.Font("����", 0, 16)); // NOI18N
        jScrollPane2.setViewportView(��Ϸ�˵�½��Ϣ);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        �����¼��ʾ.setDefaultLookAndFeelDecorated(true);
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
                new �����¼��ʾ().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane ��Ϸ�˵�½��Ϣ;
    // End of variables declaration//GEN-END:variables
}
