package gui.ͼƬ.gui1;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
public class ProgressBar extends Thread implements ActionListener{
        JProgressBar jpb=new JProgressBar();
        JLabel jl=new JLabel();
        public void actionPerformed(ActionEvent e) {
                start();
        }
        public void run(){
                for(int i=0;i<=100;i++){
                jpb.setValue(i);
                jpb.setStringPainted(true);
                String per=(int)(jpb.getPercentComplete()*100)+"%";
                jl.setText(per);
                        try{
                                Thread.sleep(50);
                        }catch(Exception ee){
                                ee.printStackTrace();
                        }
                }
        
        }
        public ProgressBar(){
                JFrame jf=new JFrame();
                jf.setLayout(new FlowLayout());
                JButton jb=new JButton("��ʼ");
                JPanel jp=new JPanel();
                jp.setLayout(new GridLayout(2,1));
                jp.add(jpb);
                jp.add(jl);
                jf.add(jp);
                jf.add(jb);
                jb.addActionListener(this);
                jf.pack();
                jf.setLocation(400,300);
                jf.setSize(200,100);
                jf.setVisible(true);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        public static void main(String[] args) {
                new ProgressBar();
        }
}