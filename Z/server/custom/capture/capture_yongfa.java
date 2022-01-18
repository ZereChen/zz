package server.custom.capture;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class capture_yongfa {

    /*
    capture_cs
    id 
    cid@���ID
    duiwu@���飬0����ɫ��1�Ǻ�ɫ
    zhuangtai @0��û׼����1��׼��
     */
    public static String ��ʾ������Ա() {
        StringBuilder name = new StringBuilder();
        //name.append(" \t\t\t#v4009165# #e#d< ����Կ��� >#k#n #v4009167#[������]\r\n");
        name.append("\t\t\t��������([#b����#k].").append(�ж���ɫ����()).append(" |[#r���#k].").append(�жϺ�ɫ����()).append(")\r\n");
        name.append("����������������������������������������������������\r\n");
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM capture_cs order by id desc");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ������� = Rolename(rs.getInt("cid"));
                String ״̬ = "";
                if (rs.getInt("zhuangtai") == 0) {
                    ״̬ = "[δ׼��]";
                } else {
                    ״̬ = "[��׼��]";
                }
                if (rs.getInt("duiwu") == 0) {
                    name.append("\t\t\t[#b����#k] ").append(״̬).append(" ���: #d").append(�������).append("\r\n");
                }
                if (rs.getInt("duiwu") > 0) {
                    name.append("\t\t\t[#r���#k] ").append(״̬).append(" ���: #d").append(�������).append("\r\n");
                }
            }

        } catch (SQLException ex) {
        }
        name.append("����������������������������������������������������\r\n");
        return name.toString();
    }

    public static void �μ�(int id) {
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO capture_cs ( cid,duiwu) VALUES ( ?, ?)")) {
            ps.setInt(1, id);
            if (�ж���ɫ����() > �жϺ�ɫ����()) {
                ps.setInt(2, 1);
            } else {
                ps.setInt(2, 0);
            }
            ps.executeUpdate();
        } catch (SQLException ex) {

        }
    }

    public static String Rolename(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM characters WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("capture_yongfa_Rolename_err");
        }
        return data;
    }

    public static int �ж���ɫ����() {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM capture_cs");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("duiwu") == 0) {
                    data += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("capture_yongfa_Rolename_�ж���ɫ����");
        }
        return data;
    }

    public static int �жϺ�ɫ����() {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM capture_cs");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("duiwu") == 1) {
                    data += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("capture_yongfa_Rolename_�жϺ�ɫ����");
        }
        return data;
    }

    public static int �ж��Ƿ��Ѿ��μ�(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT cid as DATA FROM capture_cs WHERE cid = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("capture_yongfa_�ж��Ƿ��Ѿ��μ�");
        }
        return data;
    }

    public static int �ж϶���(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT duiwu as DATA FROM capture_cs WHERE cid = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("capture_yongfa_�ж϶���");
        }
        return data;
    }

    /*
    capture_zk
    id
    duiwu
    zhankuang
     */
    
    public static int �ж϶�����������(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT zhankuang as DATA FROM capture_zk WHERE duiwu = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("capture_yongfa_�ж϶�����������");
        }
        return data;
    }

    public static void ��������(int id) {

    }

}
