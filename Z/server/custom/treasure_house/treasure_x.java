/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.custom.treasure_house;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class treasure_x {

    /*
    treasure_house
    �ر���¼��
     */
 /*
    0û�У�1��
     */
    public static int �жϱ���ͼ��û�б���(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM treasure_house WHERE map = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�жϱ���ͼ��û�б���_����");
        }
        return data;
    }

    public static int ȡ�ر�����x(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM treasure_house WHERE map = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("x");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ȡ�ر�����x_����");
        }
        return data;
    }

    public static int ȡ�ر�����y(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM treasure_house WHERE map = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("y");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ȡ�ر�����y_����");
        }
        return data;
    }

}
