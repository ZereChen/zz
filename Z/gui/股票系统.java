package gui;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ScheduledFuture;
import server.Timer;
import server.Timer.EtcTimer;
import server.Timer.MapTimer;
import server.Timer.WorldTimer;

public class ��Ʊϵͳ {

    public static void main(String args[]) {
        ��ȡ��Ʊ����();
        WorldTimer.getInstance().start();
        ������Ʊ�߳�();
    }
    public static ScheduledFuture<?> ��Ʊ�߳� = null;
    public static int ���� = 0;//0
    public static long ����ţֵ = 0;//0
    public static long ����ţֵ = 0;//1
    public static long ����ţֵ = 0;//2
    public static long ��Ģ��ֵ = 0;//3
    public static long ��Ģ��ֵ = 0;//4
    public static long ��Ģ��ֵ = 0;//5

    public static void ������Ʊ�߳�() {
        if (��Ʊ�߳� == null) {
            ��Ʊ�߳� = Timer.WorldTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    ����ţ����();
                    ����++;
                }
            }, 1000 * 1);
        }
    }

    public static void ����ţ����() {
        int ����ţ���� = (int) Math.ceil(Math.random() * 200) - 100;
        ����ţֵ += ����ţ����;
        int ����ţֵ���� = (int) Math.ceil(Math.random() * 2000) - 1000;
        ����ţֵ += ����ţֵ����;
        int ����ţֵ���� = (int) Math.ceil(Math.random() * 20000) - 10000;
        ����ţֵ += ����ţֵ����;
        int ��Ģ��ֵ���� = (int) Math.ceil(Math.random() * 400) - 200;
        ��Ģ��ֵ += ��Ģ��ֵ����;
        int ��Ģ��ֵ���� = (int) Math.ceil(Math.random() * 4000) - 2000;
        ��Ģ��ֵ += ��Ģ��ֵ����;
        int ��Ģ��ֵ���� = (int) Math.ceil(Math.random() * 40000) - 20000;
        ��Ģ��ֵ += ��Ģ��ֵ����;

        System.err.println("\r\nZEVMS��Ʊ�� " + ���� + " ����");
        System.err.println("����ţֵ " + ����ţֵ);
        System.err.println("����ţֵ " + ����ţֵ);
        System.err.println("����ţֵ " + ����ţֵ);
        System.err.println("��Ģ��ֵ " + ��Ģ��ֵ);
        System.err.println("��Ģ��ֵ " + ��Ģ��ֵ);
        System.err.println("��Ģ��ֵ " + ��Ģ��ֵ);
    }

    public static void ��ȡ��Ʊ����() {
        ����ţֵ = ȡֵ(0);
        ����ţֵ = ȡֵ(1);
        ����ţֵ = ȡֵ(2);
        ��Ģ��ֵ = ȡֵ(3);
        ��Ģ��ֵ = ȡֵ(4);
        ��Ģ��ֵ = ȡֵ(5);
        System.err.println("\r\n");
        System.err.println("����ţ ÿ�� " + ����ţֵ + " ��� / ����100");
        System.err.println("����ţ ÿ�� " + ����ţֵ + " ��� / ����1000");
        System.err.println("����ţ ÿ�� " + ����ţֵ + " ��� / ����10000");
        System.err.println("��Ģ�� ÿ�� " + ��Ģ��ֵ + " ��� / ����200");
        System.err.println("��Ģ�� ÿ�� " + ��Ģ��ֵ + " ��� / ����2000");
        System.err.println("��Ģ�� ÿ�� " + ��Ģ��ֵ + " ��� / ����20000"); 
    }

    public static long ȡֵ(int a) {
        long data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM shares  WHERE a = " + a + "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data = rs.getLong("b");
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }
}
