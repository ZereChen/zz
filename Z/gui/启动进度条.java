/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import handling.world.MapleParty;
import server.Timer;

/**
 *
 * @author Administrator
 */
public class ���������� extends javax.swing.JFrame {

    public ����������() {
        initComponents();
        ˢ�½���(1);
    }

    public void ˢ�½���(int time) {
            
        Timer.WorldTimer.getInstance().register(new Runnable() {
            public void run() {
                Z();
            }
        }, 1000 * time);
    }

    public void Z(int i) {
        ����������.setValue(i);
    }

    public void Z() {
        ����������.setValue(MapleParty.��������);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ���������� = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(����������, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 540, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 150));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ����������().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar ����������;
    // End of variables declaration//GEN-END:variables
}
