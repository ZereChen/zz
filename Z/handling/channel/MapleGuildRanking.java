package handling.channel;

import client.MapleClient;
import java.util.List;
import java.util.LinkedList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnection;
import server.Timer;
import tools.MaplePacketCreator;

public class MapleGuildRanking {

    int ְҵ������ʾ = 10;
    private static final MapleGuildRanking instance = new MapleGuildRanking();
    private final List<GuildRankingInfo> ranks = new LinkedList<>();
    private final List<levelRankingInfo> ranks1 = new LinkedList<>();
    private final List<mesoRankingInfo> ranks2 = new LinkedList<>();
    //սʿ��
    private final List<levelRankingInfo> ranks���� = new LinkedList<>();//110

    public List<levelRankingInfo> ����() {
        if (ranks����.isEmpty()) {
            ����ְҵ����();
        }
        return ranks����;
    }

    private void ����ְҵ����() {
        ranks����.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 110  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks����.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("�������д���");
        }
    }

    private final List<levelRankingInfo> ranks��ʿ = new LinkedList<>();//111

    public List<levelRankingInfo> ��ʿ() {
        if (ranks��ʿ.isEmpty()) {
            ��ʿְҵ����();
        }
        return ranks��ʿ;
    }

    private void ��ʿְҵ����() {
        ranks��ʿ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 111  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks��ʿ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("��ʿ���д���");
        }
    }

    private final List<levelRankingInfo> ranksӢ�� = new LinkedList<>();//112

    public List<levelRankingInfo> Ӣ��() {
        if (ranksӢ��.isEmpty()) {
            Ӣ��ְҵ����();
        }
        return ranksӢ��;
    } 

    private void Ӣ��ְҵ����() {
        ranksӢ��.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 112  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranksӢ��.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("Ӣ�����д���");
        }
    }

    private final List<levelRankingInfo> ranksǹսʿ = new LinkedList<>();//130

    public List<levelRankingInfo> ǹսʿ() {
        if (ranksǹսʿ.isEmpty()) {
            ǹսʿְҵ����();
        }
        return ranksǹսʿ;
    }

    private void ǹսʿְҵ����() {
        ranksǹսʿ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 130  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranksǹսʿ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("ǹսʿ���д���");
        }
    }

    private final List<levelRankingInfo> ranks����ʿ = new LinkedList<>();//131

    public List<levelRankingInfo> ����ʿ() {
        if (ranks����ʿ.isEmpty()) {
            ����ʿְҵ����();
        }
        return ranks����ʿ;
    }

    private void ����ʿְҵ����() {
        ranks����ʿ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 131  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks����ʿ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("����ʿ���д���");
        }
    }

    private final List<levelRankingInfo> ranks����ʿ = new LinkedList<>();//132

    public List<levelRankingInfo> ����ʿ() {
        if (ranks����ʿ.isEmpty()) {
            ����ʿְҵ����();
        }
        return ranks����ʿ;
    }

    private void ����ʿְҵ����() {
        ranks����ʿ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 132  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks����ʿ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("����ʿ���д���");
        }
    }

    private final List<levelRankingInfo> ranks׼��ʿ = new LinkedList<>();//120

    public List<levelRankingInfo> ׼��ʿ() {
        if (ranks׼��ʿ.isEmpty()) {
            ׼��ʿְҵ����();
        }
        return ranks׼��ʿ;
    }

    private void ׼��ʿְҵ����() {
        ranks׼��ʿ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 120  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks׼��ʿ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("׼��ʿ���д���");
        }
    }

    private final List<levelRankingInfo> ranks��ʿ = new LinkedList<>();//121

    public List<levelRankingInfo> ��ʿ() {
        if (ranks��ʿ.isEmpty()) {
            ��ʿְҵ����();
        }
        return ranks��ʿ;
    }

    private void ��ʿְҵ����() {
        ranks��ʿ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 121  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks��ʿ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("��ʿ���д���");
        }
    }

    private final List<levelRankingInfo> ranksʥ��ʿ = new LinkedList<>();//122

    public List<levelRankingInfo> ʥ��ʿ() {
        if (ranksʥ��ʿ.isEmpty()) {
            ʥ��ʿְҵ����();
        }
        return ranksʥ��ʿ;
    }

    private void ʥ��ʿְҵ����() {
        ranksʥ��ʿ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 122  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranksʥ��ʿ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("ʥ��ʿ���д���");
        }
    }

    //ħ��ʦ��
    private final List<levelRankingInfo> ranks�𶾷�ʦ = new LinkedList<>();//210

    public List<levelRankingInfo> �𶾷�ʦ() {
        if (ranks�𶾷�ʦ.isEmpty()) {
            �𶾷�ʦְҵ����();
        }
        return ranks�𶾷�ʦ;
    }

    private void �𶾷�ʦְҵ����() {
        ranks�𶾷�ʦ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 210  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks�𶾷�ʦ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("�𶾷�ʦ���д���");
        }
    }

    private final List<levelRankingInfo> ranks����ʦ = new LinkedList<>();//211

    public List<levelRankingInfo> ����ʦ() {
        if (ranks����ʦ.isEmpty()) {
            ����ʦְҵ����();
        }
        return ranks����ʦ;
    }

    private void ����ʦְҵ����() {
        ranks����ʦ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 211  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks����ʦ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("����ʦ���д���");
        }
    }

    private final List<levelRankingInfo> ranks��ħ��ʦ = new LinkedList<>();//212

    public List<levelRankingInfo> ��ħ��ʦ() {
        if (ranks��ħ��ʦ.isEmpty()) {
            ��ħ��ʦְҵ����();
        }
        return ranks��ħ��ʦ;
    }

    private void ��ħ��ʦְҵ����() {
        ranks��ħ��ʦ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 212  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks��ħ��ʦ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("��ħ��ʦ���д���");
        }
    }

    private final List<levelRankingInfo> ranks���׷�ʦ = new LinkedList<>();//220

    public List<levelRankingInfo> ���׷�ʦ() {
        if (ranks���׷�ʦ.isEmpty()) {
            ���׷�ʦְҵ����();
        }
        return ranks���׷�ʦ;
    }

    private void ���׷�ʦְҵ����() {
        ranks���׷�ʦ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 220  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks���׷�ʦ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("���׷�ʦ���д���");
        }
    }

    private final List<levelRankingInfo> ranks������ʦ = new LinkedList<>();//221

    public List<levelRankingInfo> ������ʦ() {
        if (ranks������ʦ.isEmpty()) {
            ������ʦְҵ����();
        }
        return ranks������ʦ;
    }

    private void ������ʦְҵ����() {
        ranks������ʦ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 221  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks������ʦ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("������ʦ���д���");
        }
    }

    private final List<levelRankingInfo> ranks����ħ��ʦ = new LinkedList<>();//222

    public List<levelRankingInfo> ����ħ��ʦ() {
        if (ranks����ħ��ʦ.isEmpty()) {
            ����ħ��ʦְҵ����();
        }
        return ranks����ħ��ʦ;
    }

    private void ����ħ��ʦְҵ����() {
        ranks����ħ��ʦ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 222  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks����ħ��ʦ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("����ħ��ʦ���д���");
        }
    }
    private final List<levelRankingInfo> ranks��ʦ = new LinkedList<>();//230

    public List<levelRankingInfo> ��ʦ() {
        if (ranks��ʦ.isEmpty()) {
            ��ʦְҵ����();
        }
        return ranks��ʦ;
    }

    private void ��ʦְҵ����() {
        ranks��ʦ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 230  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks��ʦ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("��ʦ���д���");
        }
    }

    private final List<levelRankingInfo> ranks��ʦ = new LinkedList<>();//231

    public List<levelRankingInfo> ��ʦ() {
        if (ranks��ʦ.isEmpty()) {
            ��ʦְҵ����();
        }
        return ranks��ʦ;
    }

    private void ��ʦְҵ����() {
        ranks��ʦ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 231  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks��ʦ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("��ʦ���д���");
        }
    }

    private final List<levelRankingInfo> ranks���� = new LinkedList<>();//232

    public List<levelRankingInfo> ����() {
        if (ranks����.isEmpty()) {
            ����ְҵ����();
        }
        return ranks����;
    }

    private void ����ְҵ����() {
        ranks����.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 232  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks����.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("�������д���");
        }
    }

    //������
    private final List<levelRankingInfo> ranks���� = new LinkedList<>();//310

    public List<levelRankingInfo> ����() {
        if (ranks����.isEmpty()) {
            ����ְҵ����();
        }
        return ranks����;
    }

    private void ����ְҵ����() {
        ranks����.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 310  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks����.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("�������д���");
        }
    }

    private final List<levelRankingInfo> ranks���� = new LinkedList<>();//311

    public List<levelRankingInfo> ����() {
        if (ranks����.isEmpty()) {
            ����ְҵ����();
        }
        return ranks����;
    }

    private void ����ְҵ����() {
        ranks����.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 311  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks����.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("�������д���");
        }
    }

    private final List<levelRankingInfo> ranks������ = new LinkedList<>();//312

    public List<levelRankingInfo> ������() {
        if (ranks������.isEmpty()) {
            ������ְҵ����();
        }
        return ranks������;
    }

    private void ������ְҵ����() {
        ranks������.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 312  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks������.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("���������д���");
        }
    }

    private final List<levelRankingInfo> ranks���� = new LinkedList<>();//320

    public List<levelRankingInfo> ����() {
        if (ranks����.isEmpty()) {
            ����ְҵ����();
        }
        return ranks����;
    }

    private void ����ְҵ����() {
        ranks����.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 320  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks����.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("�������д���");
        }
    }

    private final List<levelRankingInfo> ranks���� = new LinkedList<>();//321

    public List<levelRankingInfo> ����() {
        if (ranks����.isEmpty()) {
            ����ְҵ����();
        }
        return ranks����;
    }

    private void ����ְҵ����() {
        ranks����.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 321  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks����.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("�������д���");
        }
    }

    private final List<levelRankingInfo> ranks���� = new LinkedList<>();//322

    public List<levelRankingInfo> ����() {
        if (ranks����.isEmpty()) {
            ����ְҵ����();
        }
        return ranks����;
    }

    private void ����ְҵ����() {
        ranks����.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 322  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks����.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("�������д���");
        }
    }
    //������
    private final List<levelRankingInfo> ranks�̿� = new LinkedList<>();//410

    public List<levelRankingInfo> �̿�() {
        if (ranks�̿�.isEmpty()) {
            �̿�ְҵ����();
        }
        return ranks�̿�;
    }

    private void �̿�ְҵ����() {
        ranks�̿�.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 410  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks�̿�.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("�̿����д���");
        }
    }

    private final List<levelRankingInfo> ranks��Ӱ�� = new LinkedList<>();//411

    public List<levelRankingInfo> ��Ӱ��() {
        if (ranks��Ӱ��.isEmpty()) {
            ��Ӱ��ְҵ����();
        }
        return ranks��Ӱ��;
    }

    private void ��Ӱ��ְҵ����() {
        ranks��Ӱ��.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 411  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks��Ӱ��.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("��Ӱ�����д���");
        }
    }

    private final List<levelRankingInfo> ranks��ʿ = new LinkedList<>();//412

    public List<levelRankingInfo> ��ʿ() {
        if (ranks��ʿ.isEmpty()) {
            ��ʿְҵ����();
        }
        return ranks��ʿ;
    }

    private void ��ʿְҵ����() {
        ranks��ʿ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 412  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks��ʿ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("��ʿ���д���");
        }
    }

    private final List<levelRankingInfo> ranks���� = new LinkedList<>();//420

    public List<levelRankingInfo> ����() {
        if (ranks����.isEmpty()) {
            ����ְҵ����();
        }
        return ranks����;
    }

    private void ����ְҵ����() {
        ranks����.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 420  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks����.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("�������д���");
        }
    }
    private final List<levelRankingInfo> ranks���п� = new LinkedList<>();//421

    public List<levelRankingInfo> ���п�() {
        if (ranks���п�.isEmpty()) {
            ���п�ְҵ����();
        }
        return ranks���п�;
    }

    private void ���п�ְҵ����() {
        ranks���п�.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 421  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks���п�.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("���п����д���");
        }
    }
    private final List<levelRankingInfo> ranks���� = new LinkedList<>();//422

    public List<levelRankingInfo> ����() {
        if (ranks����.isEmpty()) {
            ����ְҵ����();
        }
        return ranks����;
    }

    private void ����ְҵ����() {
        ranks����.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 422  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks����.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("�������д���");
        }
    }
    //������
    private final List<levelRankingInfo> ranksȭ�� = new LinkedList<>();//510

    public List<levelRankingInfo> ȭ��() {
        if (ranksȭ��.isEmpty()) {
            ȭ��ְҵ����();
        }
        return ranksȭ��;
    }

    private void ȭ��ְҵ����() {
        ranksȭ��.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 510  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranksȭ��.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("ȭ�����д���");
        }
    }

    private final List<levelRankingInfo> ranks��ʿ = new LinkedList<>();//511

    public List<levelRankingInfo> ��ʿ() {
        if (ranks��ʿ.isEmpty()) {
            ��ʿְҵ����();
        }
        return ranks��ʿ;
    }

    private void ��ʿְҵ����() {
        ranks��ʿ.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 511  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks��ʿ.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("��ʿ���д���");
        }
    }
    private final List<levelRankingInfo> ranks���ӳ� = new LinkedList<>();//512

    public List<levelRankingInfo> ���ӳ�() {
        if (ranks���ӳ�.isEmpty()) {
            ���ӳ�ְҵ����();
        }
        return ranks���ӳ�;
    }

    private void ���ӳ�ְҵ����() {
        ranks���ӳ�.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 512  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks���ӳ�.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("���ӳ����д���");
        }
    }
    private final List<levelRankingInfo> ranks��ǹ�� = new LinkedList<>();//520

    public List<levelRankingInfo> ��ǹ��() {
        if (ranks��ǹ��.isEmpty()) {
            ��ǹ��ְҵ����();
        }
        return ranks��ǹ��;
    }

    private void ��ǹ��ְҵ����() {
        ranks��ǹ��.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 520  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks��ǹ��.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("��ǹ�����д���");
        }
    }
    private final List<levelRankingInfo> ranks�� = new LinkedList<>();//521

    public List<levelRankingInfo> ��() {
        if (ranks��.isEmpty()) {
            ��ְҵ����();
        }
        return ranks��;
    }

    private void ��ְҵ����() {
        ranks��.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 521  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks��.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("�����д���");
        }
    }
    private final List<levelRankingInfo> ranks���� = new LinkedList<>();//522

    public List<levelRankingInfo> ����() {
        if (ranks����.isEmpty()) {
            ����ְҵ����();
        }
        return ranks����;
    }

    private void ����ְҵ����() {
        ranks����.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 522  ORDER BY `level` DESC LIMIT " + ְҵ������ʾ + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks����.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("�������д���");
        }
    }

    public void RankingUpdate() {
        //  System.out.println("");//�� �� �� �� �� �� �� : : :
        Timer.WorldTimer.getInstance().register(new Runnable() {

            public void run() {
                try {
                    reload();
                    showLevelRank();
                    showMesoRank();
                    //   System.out.println("Ranking update");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.err.println("Could not update rankings");
                }
            }
        }, 1 * 60 * 60 * 1000, 1 * 60 * 60 * 1000);
    }

    public static MapleGuildRanking getInstance() {
        return instance;
    }

    public List<GuildRankingInfo> getGuildRank() {
        if (ranks.isEmpty()) {
            reload();
        }
        return ranks;
    }

    public List<levelRankingInfo> getLevelRank() {
        if (ranks1.isEmpty()) {
            showLevelRank();
        }
        return ranks1;
    }

    public List<mesoRankingInfo> getMesoRank() {
        if (ranks2.isEmpty()) {
            showMesoRank();
        }
        return ranks2;
    }

    private void reload() {
        ranks.clear();

        Connection con = DatabaseConnection.getConnection();
        ResultSet rs;
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM guilds ORDER BY `GP` DESC LIMIT 10")) {
            rs = ps.executeQuery();
            while (rs.next()) {
                final GuildRankingInfo rank = new GuildRankingInfo(
                        rs.getString("name"),
                        rs.getInt("GP"),
                        rs.getInt("logo"),
                        rs.getInt("logoColor"),
                        rs.getInt("logoBG"),
                        rs.getInt("logoBGColor"));

                ranks.add(rank);
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println("�������д���" + e);
        }
    }

    private void showLevelRank() {
        ranks1.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE gm < 1 ORDER BY `level` DESC LIMIT 10");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks1.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("�������д���");
        }
    }

    public static void MapleMSpvpdeaths(MapleClient c, int npcid) {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT `name`, `pvpdeaths`, `str`, `dex`, "
                    + "`int`, `luk` FROM characters ORDER BY `pvpdeaths` DESC LIMIT 10");

            ResultSet rs = ps.executeQuery();
            c.sendPacket(MaplePacketCreator.MapleMSpvpdeaths(npcid, rs));

            ps.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("failed to display guild ranks." + e);
        }
    }

    public static void MapleMSpvpkills(MapleClient c, int npcid) {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT `name`, `pvpkills`, `str`, `dex`, "
                    + "`int`, `luk` FROM characters ORDER BY `pvpkills` WHERE gm < 1  DESC LIMIT 10");

            ResultSet rs = ps.executeQuery();
            c.sendPacket(MaplePacketCreator.MapleMSpvpkills(npcid, rs));

            ps.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("failed to display guild ranks." + e);
        }
    }

    private void showMesoRank() {
        ranks2.clear();

        Connection con = DatabaseConnection.getConnection();
        ResultSet rs;
        try (PreparedStatement ps = con.prepareStatement("SELECT *, ( chr.meso + s.meso ) as money FROM `characters` as chr , `storages` as s WHERE chr.gm < 1  AND s.accountid = chr.accountid ORDER BY money DESC LIMIT 10")) {
            rs = ps.executeQuery();
            while (rs.next()) {
                final mesoRankingInfo rank2 = new mesoRankingInfo(
                        rs.getString("name"),
                        rs.getLong("money"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks2.add(rank2);
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println("������д���");
        }
    }

    public static class mesoRankingInfo {

        private final String name;
        private final long meso;
        private final int str, dex, _int, luk;

        public mesoRankingInfo(String name, long meso, int str, int dex, int intt, int luk) {
            this.name = name;
            this.meso = meso;
            this.str = str;
            this.dex = dex;
            this._int = intt;
            this.luk = luk;
        }

        public String getName() {
            return name;
        }

        public long getMeso() {
            return meso;
        }

        public int getStr() {
            return str;
        }

        public int getDex() {
            return dex;
        }

        public int getInt() {
            return _int;
        }

        public int getLuk() {
            return luk;
        }
    }

    public static class levelRankingInfo {

        private final String name;
        private final int level, str, dex, _int, luk;

        public levelRankingInfo(String name, int level, int str, int dex, int intt, int luk) {
            this.name = name;
            this.level = level;
            this.str = str;
            this.dex = dex;
            this._int = intt;
            this.luk = luk;
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        public int getStr() {
            return str;
        }

        public int getDex() {
            return dex;
        }

        public int getInt() {
            return _int;
        }

        public int getLuk() {
            return luk;
        }
    }

    public static class GuildRankingInfo {

        private final String name;
        private final int gp, logo, logocolor, logobg, logobgcolor;

        public GuildRankingInfo(String name, int gp, int logo, int logocolor, int logobg, int logobgcolor) {
            this.name = name;
            this.gp = gp;
            this.logo = logo;
            this.logocolor = logocolor;
            this.logobg = logobg;
            this.logobgcolor = logobgcolor;
        }

        public String getName() {
            return name;
        }

        public int getGP() {
            return gp;
        }

        public int getLogo() {
            return logo;
        }

        public int getLogoColor() {
            return logocolor;
        }

        public int getLogoBg() {
            return logobg;
        }

        public int getLogoBgColor() {
            return logobgcolor;
        }
    }
}
