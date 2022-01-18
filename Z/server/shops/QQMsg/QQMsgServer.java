/////*
////QQ������
//// */
/////*package server;
////
////import static a.Yongfa.Yongfa1.��ɫIDȡ�˺�ID;
////import static a.Yongfa.Yongfa1.�˺�IDȡ�˺�;
////import static a.Yongfa.Yongfa1.�˺Ų�����;
////import static abc.Game.��������;
////import static abc.Game.��������;
////import static abc.Game.�汾;
////import abc.ע�������;
////import abc.ע�������;
////import client.Class2;
////import client.LoginCrypto;
////import client.MapleCharacter;
////import database.DatabaseConnection;
////import gui.ZEVMS;
////import handling.RecvPacketOpcode;
////import handling.SendPacketOpcode;
////import handling.channel.ChannelServer;
////import handling.login.handler.AutoRegister;
////import handling.world.MapleParty;
////import handling.world.World;
////import handling.world.guild.MapleGuild;
////import java.awt.Point;
////import java.io.BufferedReader;
////import java.io.File;��
////import java.io.IOException;
////import java.io.InputStream;
////import java.io.InputStreamReader;
////import java.io.UnsupportedEncodingException;
////import java.net.DatagramPacket;
////import java.net.DatagramSocket;
////import java.net.InetAddress;
////import java.net.MalformedURLException;
////import java.net.SocketException;
////import java.net.URL;
////import java.net.URLConnection;
////import java.sql.Connection;
////import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////import java.sql.SQLException;
////import java.text.SimpleDateFormat;
////import java.util.ArrayList;
////import java.util.Calendar;
////import java.util.Date;
////import java.util.List;
////import java.util.Locale;
////import java.util.concurrent.ScheduledFuture;
////import java.util.logging.Level;
////import java.util.logging.Logger;
////import javax.swing.JOptionPane;
////import provider.MapleData;
////import provider.MapleDataProvider;
////import provider.MapleDataProviderFactory;
////import provider.WzXML.CashGui;
////import scripting.NPCConversationManager;
////import scripting.PortalScriptManager;
////import scripting.ReactorScriptManager;
////import static server.MapleCarnivalChallenge.getJobNameById;
////import static server.Start.GetCloudBacklist;
////import server.custom.respawn.RespawnInfo;
////import server.events.MapleEvent;
////import server.events.MapleEventType;
////import server.life.MapleLifeFactory;
////import server.life.MapleMonster;
////import server.life.MapleMonsterInformationProvider;
////import server.life.OverrideMonsterStats;
////import server.maps.MapleMap;
////import tools.FileoutputUtil;
////import static tools.FileoutputUtil.CurrentReadable_Date;
////import static tools.FileoutputUtil.CurrentReadable_Time;
////import tools.MaplePacketCreator;
////import tools.StringUtil;
////
/////**
//// *
//// * @author ʱ���ȷ�
//// */
////public class QQMsgServer implements Runnable {
////
//////    public static void main(String args[]) {
//////        new Thread(QQMsgServer.getInstance()).start();
//////        System.err.println("��������");
//////    }
////    private static final int INPORT = 9001;
////    private static final int PeerPort = 9000;
////
////    private byte[] buf = new byte[1024];
////    private DatagramPacket dp = new DatagramPacket(buf, buf.length);
////    private static DatagramSocket socket;
////    private static final MapleDataProvider stringDataWZ = MapleDataProviderFactory.getDataProvider(new File(System.getProperty("net.sf.odinms.wzpath") + "/String.wz"));
////    private static final MapleDataProvider data = MapleDataProviderFactory.getDataProvider(new File(System.getProperty("net.sf.odinms.wzpath") + "/Mob.wz"));
////    private static final MapleData mobStringData = stringDataWZ.getData("Mob.img");
////    private static final QQMsgServer instance = new QQMsgServer();
////
////    public static QQMsgServer getInstance() {
////        return instance;
////    }
////
////    private QQMsgServer() {
////        try {
////            socket = new DatagramSocket(INPORT);
////            //System.out.println("Launch qq msg server completed - Port: " + INPORT);
////        } catch (SocketException e) {
////            System.err.println("Can't open socket");
////            System.exit(1);
////        }
////    }
////
////    public static void sendMsg(final String msg, final String token) {
////        try {
////            String data = String.format("P_%s_%s", token, msg);
////            byte[] buf = data.getBytes();
////            //System.out.println("[->qq] : " + new String(buf));
////            DatagramPacket echo = new DatagramPacket(buf, buf.length, InetAddress.getLoopbackAddress(), PeerPort);
////            socket.send(echo);
////        } catch (IOException e) {
////            System.err.println("sendMsgToQQ error");
////            e.printStackTrace();
////        }
////    }
////
////    public static void sendMsgToAdminQQ(final String msg) {
////        try {
////            String data = "A_" + msg;
////            byte[] buf = data.getBytes();
////            //System.out.println("[->admin qq] : " + new String(buf));
////            DatagramPacket echo = new DatagramPacket(buf, buf.length, InetAddress.getLoopbackAddress(), PeerPort);
////            socket.send(echo);
////        } catch (IOException e) {
////            System.err.println("sendMsgToAdminQQ error");
////            e.printStackTrace();
////        }
////    }
////
////    public static void sendMsgToQQGroup(final String msg) {
////
////        try {
////            String data = "G_" + msg;
////            byte[] buf = data.getBytes();
////            //System.out.println("[->qq group] : " + new String(buf));
////            DatagramPacket echo = new DatagramPacket(buf, buf.length, InetAddress.getLoopbackAddress(), PeerPort);
////            socket.send(echo);
////        } catch (IOException e) {
////            System.err.println("sendMsgToQQGroup error");
////            e.printStackTrace();
////        }
////
////    }
////
////    private static int sendToOnlinePlayer(final String fromQQ, final String msg) {
////
////        int count = 0;
////        if (MapleParty.�����˴��� > 0) {
////            return count;
////        }
////        if (MapleParty.��Ϣͬ�� > 0) {
////            return count;
////        }
////        for (ChannelServer chl : ChannelServer.getAllInstances()) {
////            for (MapleCharacter chr : chl.getPlayerStorage().getAllCharacters()) {
////                if (chr == null) {
////                    continue;
////                }
////                if (chr.Getcharacterz("" + chr.getId() + "", 3) > 0) {
////                    continue;
////                }
////                chr.dropMessage(String.format("[Ⱥ][%s] : %s", fromQQ, msg));
////                count++;
////
////            }
////        }
////        return count;
////    }
////
////    public int ��ѯ��������() {
////        int count = 0;
////        for (ChannelServer chl : ChannelServer.getAllInstances()) {
////            count += chl.getPlayerStorage().getAllCharacters().size();
////        }
////        return count;
////    }
////
////    private static void �⿨����(final String qq, final String account, final String token) {
////        //�˺�IDȡ�˺�(��ɫIDȡ�˺�ID(account)) != qq
////        if (!�˺�IDȡ�˺�(��ɫIDȡ�˺�ID(account)).equals(qq)) {
////            sendMsg("<" + �������� + ">������:��Ҫ�⿨���͵Ľ�ɫ����������ġ�", token);
////            return;
////        }
////        if (�˺Ų�����(Integer.parseInt(qq)) != 0) {
////            sendMsg("<" + �������� + ">������:��Ľ�ɫĿǰ���ߣ�����Է��ͶϿ����ӶϿ���ɫ��", token);
////            return;
////        }
////        PreparedStatement ps = null;
////        PreparedStatement ps1 = null;
////        ResultSet rs = null;
////        try {
////            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characters SET hair = ? WHERE id = ?");
////            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
////            ps1.setInt(1, Integer.parseInt(account));
////            rs = ps1.executeQuery();
////            if (rs.next()) {
////                String sqlString1 = null;
////                sqlString1 = "update characters set hair='30000' where id='" + Integer.parseInt(account) + "';";
////                PreparedStatement hair = DatabaseConnection.getConnection().prepareStatement(sqlString1);
////                hair.executeUpdate(sqlString1);
////                sendMsg("<" + �������� + ">������:��ɫ���ͽ⿨�ɹ�����", token);
////            }
////        } catch (SQLException ex) {
////            Logger.getLogger(CashGui.class.getName()).log(Level.SEVERE, null, ex);
////        }
////    }
////
////    private static void �⿨����(final String qq, final String account, final String token) {
////        if (!�˺�IDȡ�˺�(��ɫIDȡ�˺�ID(account)).equals(qq)) {
////            sendMsg("<" + �������� + ">������:��Ҫ�⿨���͵Ľ�ɫ����������ġ�", token);
////            return;
////        }
////        if (�˺Ų�����(Integer.parseInt(qq)) != 0) {
////            sendMsg("<" + �������� + ">������:��Ľ�ɫĿǰ���ߣ�����Է��ͶϿ����ӶϿ���ɫ��", token);
////            return;
////        }
////        PreparedStatement ps = null;
////        PreparedStatement ps1 = null;
////        ResultSet rs = null;
////        try {
////            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characters SET face = ?WHERE id = ?");
////            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
////            ps1.setInt(1, Integer.parseInt(account));
////            rs = ps1.executeQuery();
////            if (rs.next()) {
////                String sqlString1 = null;
////                sqlString1 = "update characters set face='20000' where id='" + Integer.parseInt(account) + "';";
////                PreparedStatement hair = DatabaseConnection.getConnection().prepareStatement(sqlString1);
////                hair.executeUpdate(sqlString1);
////                sendMsgToQQGroup("<" + �������� + ">������:����Ա�Ѿ�����ɫidΪ " + account + " ����ҽ⿨���ͳɹ���");
////                sendMsg("<" + �������� + ">������:��ɫ���ͽ⿨�ɹ���", token);
////            }
////        } catch (SQLException ex) {
////            Logger.getLogger(CashGui.class.getName()).log(Level.SEVERE, null, ex);
////        }
////
////    }
////
////    private static void �⿨��ͼ(final String qq, final String account, final String token) {
////        if (!�˺�IDȡ�˺�(��ɫIDȡ�˺�ID(account)).equals(qq)) {
////            sendMsg("<" + �������� + ">������:��Ҫ�⿨��ͼ�Ľ�ɫ����������ġ�", token);
////            return;
////        }
////        if (�˺Ų�����(Integer.parseInt(qq)) != 0) {
////            sendMsg("<" + �������� + ">������:��Ľ�ɫĿǰ���ߣ�����Է��ͶϿ����ӶϿ���ɫ��", token);
////            return;
////        }
////        PreparedStatement ps = null;
////        PreparedStatement ps1 = null;
////        ResultSet rs = null;
////        try {
////            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characters SET map = ?WHERE id = ?");
////            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
////            ps1.setInt(1, Integer.parseInt(account));
////            rs = ps1.executeQuery();
////            if (rs.next()) {
////                String sqlString1 = null;
////                sqlString1 = "update characters set map='100000000' where id='" + Integer.parseInt(account) + "';";
////                PreparedStatement hair = DatabaseConnection.getConnection().prepareStatement(sqlString1);
////                hair.executeUpdate(sqlString1);
////                sendMsgToQQGroup("<" + �������� + ">������:����Ա�Ѿ�����ɫidΪ " + account + " ����ҽ⿨��ͼ�ɹ���");
////                sendMsg("<" + �������� + ">������:��ɫ��ͼ�⿨�ɹ���", token);
////            }
////        } catch (SQLException ex) {
////            Logger.getLogger(CashGui.class.getName()).log(Level.SEVERE, null, ex);
////        }
////    }
////
////    private static void �ж�����(final String qq, final String account, final String token) {
////        if (!�˺�IDȡ�˺�(��ɫIDȡ�˺�ID(account)).equals(qq)) {
////            sendMsg("<" + �������� + ">������:��Ҫ�ж����ӵĽ�ɫ����������ġ�", token);
////            return;
////        }
////        if (�˺Ų�����(Integer.parseInt(qq)) == 0) {
////            sendMsg("<" + �������� + ">������:��Ľ�ɫĿǰ�����ߡ�", token);
////            return;
////        }
////        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
////            for (MapleCharacter c : cserv.getPlayerStorage().getAllCharacters()) {
////                if (c.getId() == Integer.parseInt(account)) {
////                    c.getClient().getSession().close();
////                }
////            }
////        }
////    }
////
////    private static void �ϴ��ƺ�����(final String qq, final String newPassword, final String token) {
////        if ("71447500".equals(qq) || "34443647".equals(qq)) {
////            FileoutputUtil.logToZev("blacklist", ",\r\n" + newPassword + "/0");
////            FileoutputUtil.logToZev("blacklist�ϴ���¼.txt", "\r\n" + token + "/" + newPassword + "");
////            sendMsg("<" + �������� + ">������:�˺� " + newPassword + " �ϴ����ƺ������ɹ���", token);
////        }
////    }
////
////    private static void �⿨�˺�(final String qq, final String account, final String token) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            try {
////                Connection con = DatabaseConnection.getConnection();
////                PreparedStatement ps;
////                ps = con.prepareStatement("Update accounts set loggedin = ? Where name = ?");
////                ps.setInt(1, 0);
////                ps.setString(2, account);
////                ps.execute();
////                ps.close();
////            } catch (Exception ex) {
////                JOptionPane.showMessageDialog(null, "����!\r\n" + ex);
////            }
////            sendMsgToQQGroup("<" + �������� + ">������:����Ա�Ѿ����˺� " + account + " �⿨�ɹ���");
////            sendMsg("<" + �������� + ">������:�˺� " + account + " �⿨�ɹ���", token);
////        }
////    }
////
////    private static void ����(final String qq, final String account, final String token) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq)) {
////            sendMsgToQQGroup("<" + �������� + ">������:" + account + "");
////        }
////    }
////
////    private static void ����(final String qq, final String account, final String a, final String token) {
////        if (!�˺�IDȡ�˺�(��ɫIDȡ�˺�ID(account)).equals(qq)) {
////            sendMsg("<" + �������� + ">������:��Ҫ�ж����ӵĽ�ɫ����������ġ�", token);
////            return;
////        }
////        if (�˺Ų�����(Integer.parseInt(qq)) == 0) {
////            sendMsg("<" + �������� + ">������:��Ľ�ɫĿǰ�����ߡ�", token);
////            return;
////        }
////        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
////            for (MapleCharacter c : cserv.getPlayerStorage().getAllCharacters()) {
////                if (c.getId() == Integer.parseInt(account)) {
////                    if (a == "���ִ�") {
////                        c.changeMap(100000000, 1);
////                    } else if (a == "�г�") {
////                        c.changeMap(910000000, 1);
////                    }
////                }
////            }
////        }
////    }
////
////    @Override
////    public void run() {
////        try {
////            while (true) {
////                socket.receive(dp);
////                // ���յ�����QQ�����˵���Ϣ
////                String rcvd = new String(dp.getData(), 0, dp.getLength());
////                //System.out.println("QQ: " + rcvd);
////
////                String msgArr[] = rcvd.split("_");
////                String msgType = msgArr[0];
////                if (msgType.equals("P")) { // ˽��
////                    int index = msgType.length() + 1;
////                    String fromQQ = msgArr[1];
////                    index += fromQQ.length() + 1;
////                    String token = msgArr[2];
////                    index += token.length() + 1;
////
////                    String msg[] = rcvd.substring(index).trim().split("\\s+");
////
////                    switch (msg[0]) {
////                        case "*�����޸�":
////                        case "*�޸�����":
////                            if (msg.length > 1) {
////                                �޸�����(fromQQ, msg[1], token);
////                            } else {
////                                sendMsg("<" + �������� + ">������:\r\n"
////                                        + "�㷢�����Ҳ���ȷ��ָ��\r\n"
////                                        + "��ʽ:*�޸�����<�ո�>[�������]", token);
////                            }
////                            break;
////                        case "*�ϴ��ƺ�����":
////                            if (msg.length > 1) {
////                                �ϴ��ƺ�����(fromQQ, msg[1], token);
////                            } else {
////                                sendMsg("<" + �������� + ">������:\r\n"
////                                        + "�㷢�����Ҳ���ȷ��ָ��\r\n"
////                                        + "��ʽ:*�ϴ��ƺ�����<�ո�>[�˺�]", token);
////                            }
////                            break;
////                        case "*�⿨�˺�":
////                            if (msg.length > 1) {
////                                �⿨�˺�(fromQQ, msg[1], token);
////                            } else {
////                                sendMsg("<" + �������� + ">������:\r\n"
////                                        + "�㷢�����Ҳ���ȷ��ָ��\r\n"
////                                        + "��ʽ:*�⿨�˺�<�ո�>[�˺�]", token);
////                            }
////                            break;
////                        case "*�⿨����":
////                            if (msg.length > 1) {
////                                �⿨����(fromQQ, msg[1], token);
////                            } else {
////                                sendMsg("<" + �������� + ">������:\r\n"
////                                        + "�㷢�����Ҳ���ȷ��ָ��\r\n"
////                                        + "��ʽ:*�⿨����<�ո�>[��ɫid]", token);
////                            }
////                            break;
////                        case "*�⿨����":
////                            if (msg.length > 1) {
////                                �⿨����(fromQQ, msg[1], token);
////                            } else {
////                                sendMsg("<" + �������� + ">������:\r\n"
////                                        + "�㷢�����Ҳ���ȷ��ָ��\r\n"
////                                        + "��ʽ:*�⿨����<�ո�>[��ɫid]", token);
////                            }
////                            break;
////                        case "*�⿨��ͼ":
////                            if (msg.length > 1) {
////                                �⿨��ͼ(fromQQ, msg[1], token);
////                            } else {
////                                sendMsg("<" + �������� + ">������:\r\n"
////                                        + "�㷢�����Ҳ���ȷ��ָ��\r\n"
////                                        + "��ʽ:*�⿨��ͼ<�ո�>[��ɫid]", token);
////                            }
////                            break;
////                        case "*�ж�����":
////                            if (msg.length > 1) {
////                                �ж�����(fromQQ, msg[1], token);
////                            } else {
////                                sendMsg("<" + �������� + ">������:\r\n"
////                                        + "�㷢�����Ҳ���ȷ��ָ��\r\n"
////                                        + "��ʽ:*�ж�����<�ո�>[��ɫid]", token);
////                            }
////                            break;
////                        case "*����":
////                            if (msg.length > 1) {
////                                ����(fromQQ, msg[1], msg[2], token);
////                            } else {
////                                sendMsg("<" + �������� + ">������:\r\n"
////                                        + "�㷢�����Ҳ���ȷ��ָ��\r\n"
////                                        + "��ʽ:*����<�ո�>[��ɫid]<�ո�>[Ŀ���ͼ]", token);
////                            }
////                            break;
////                        case "*����":
////                            ����(fromQQ, msg[1], token);
////                            break;
////
////                        default: // ��������
////                            break;
////                    }
////                } else if (msgType.equals("G")) { // Ⱥ��
////                    int index = msgType.length() + 1;
////                    String fromGroup = msgArr[1];
////                    index += fromGroup.length() + 1;
////                    String fromQQ = msgArr[2];
////                    index += fromQQ.length() + 1;
////                    String msg = rcvd.substring(index).trim();
////                    switch (msg) {
////                        case "*���﹥��":
////                            ���﹥��(fromQQ);
////                            break;
////                        case "*����1000��ȯ����":
////                            ����1000��ȯ����(fromQQ);
////                            break;
////                        case "*����2000��ȯ����":
////                            ����2000��ȯ����(fromQQ);
////                            break;
////                        case "*����5000��ȯ����":
////                            ����5000��ȯ����(fromQQ);
////                            break;
////                        case "*����10000��ȯ����":
////                            ����10000��ȯ����(fromQQ);
////                            break;
////                        case "*��ʼ��ѩ�����":
////                            ��ʼ��ѩ�����(fromQQ);
////                            break;
////                        case "*��ѩ�������ʼ":
////                            ��ѩ�������ʼ(fromQQ);
////                            break;
////                        case "*����1Сʱ˫������":
////                            ����1Сʱ˫������(fromQQ);
////                            break;
////                        case "*����2Сʱ˫������":
////                            ����2Сʱ˫������(fromQQ);
////                            break;
////                        case "*����1Сʱ˫������":
////                            ����1Сʱ˫������(fromQQ);
////                            break;
////                        case "*����2Сʱ˫������":
////                            ����2Сʱ˫������(fromQQ);
////                            break;
////                        case "*��ֹ��ҵ�½":
////                            ��ֹ��ҵ�½(fromQQ);
////                            break;
////                        case "*�����ƺ�����":
////                            �����ƺ�����(fromQQ);
////                            break;
////                        case "*�ر��ƺ�����":
////                            �ر��ƺ�����(fromQQ);
////                            break;
////                        case "*������ҵ�½":
////                            ������ҵ�½(fromQQ);
////                            break;
////                        case "*������ҽ���":
////                            ������ҽ���(fromQQ);
////                            break;
////                        case "*��ֹ��ҽ���":
////                            ��ֹ��ҽ���(fromQQ);
////                            break;
////                        case "*������Ϸ����":
////                            ������Ϸ����(fromQQ);
////                            break;
////                        case "*��ֹ��Ϸ����":
////                            ��ֹ��Ϸ����(fromQQ);
////                            break;
////                        case "*��ֹ�������":
////                            ��ֹ�������(fromQQ);
////                            break;
////                        case "*�����������":
////                            �����������(fromQQ);
////                            break;
////                        case "*�رշ����":
////                            �رշ����(fromQQ);
////                            break;
////                        case "*�鿴�۷��":
////                            �鿴�۷��(fromQQ);
////                            break;
////                        case "*����ȫ������":
////                            ����ȫ������(fromQQ);
////                            break;
////                        case "*�ر�ȫ������":
////                            �ر�ȫ������(fromQQ);
////                            break;
////                        case "*������½��֤":
////                            ������½��֤(fromQQ);
////                            break;
////                        case "*�رյ�½��֤":
////                            �رյ�½��֤(fromQQ);
////                            break;
////                        case "*���ݴ浵":
////                            ���ݴ浵(fromQQ);
////                            break;
////                        case "*�ͷ��ڴ�":
////                            �ͷ��ڴ�(fromQQ);
////                            break;
////                        case "*����״̬":
////                            ����״̬(fromQQ);
////                            break;
////                        case "*�ر��۷�㲥":
////                            �ر��۷�㲥(fromQQ);
////                            break;
////                        case "*�����۷�㲥":
////                            �����۷�㲥(fromQQ);
////                            break;
////                        case "*�ж�ȫ������":
////                            �ж�ȫ������(fromQQ);
////                            break;
////                        case "*���ط����":
////                            ���ط����(fromQQ);
////                            break;
////                        case "*����":
////                            ����(fromQQ);
////                            break;
////                        case "*����":
////                            ����(fromQQ);
////                            break;
////                        case "*��������":
////                            ��������(fromQQ);
////                            break;
////                        case "*�����˺���ʾ":
////                            �����˺���ʾ(fromQQ);
////                            break;
////                        case "*�ر��˺���ʾ":
////                            �ر��˺���ʾ(fromQQ);
////                            break;
////                        case "*�ر���Ϣͬ��":
////                            �ر���Ϣͬ��(fromQQ);
////                            break;
////                        case "*������Ϣͬ��":
////                            ������Ϣͬ��(fromQQ);
////                            break;
////                        case "*�ر���Ϸ����Ⱥ���":
////                            �ر���Ϸ����Ⱥ���(fromQQ);
////                            break;
////                        case "*������Ϸ����Ⱥ���":
////                            ������Ϸ����Ⱥ���(fromQQ);
////                            break;
////                        case "*��������ͳ��":
////                            ��������ͳ��(fromQQ);
////                            break;
////                        case "*�ر�����ͳ��":
////                            �ر�����ͳ��(fromQQ);
////                            break;
////                        case "*��ʼ����ɱ�":
////                            ��ʼ����ɱ�(fromQQ);
////                            break;
////                        case "*��������ɱ�":
////                            ��������ɱ�(fromQQ);
////                            break;
////                        case "*�汾���":
////                            �汾���(fromQQ);
////                            break;
////                        case "*�����ƺ�����":
////                            �����ƺ�����(fromQQ);
////                            break;
////                        case "*����":
////                        case "*����":
////                        case "*������Ϣ":
////                        case "*����":
////                            ����˱���();
////                            break;
////                        case "*��ôע��":
////                        case "*��ôע����":
////                        case "*ע��":
////                        case "*ע���ʺ�":
////                        case "*ע���˺�":
////                        case "*�˺�ע��":
////                        case "*�ʺ�ע��":
////                            ע���˺�(fromQQ);
////                            break;
////                        case "*��ѯ��ɫ":
////                        case "*��ɫ��ѯ":
////                        case "*�鿴��ɫ":
////                            ��ѯ��ɫ(fromQQ);
////                            break;
////                        case "*��������ģʽ":
////                            ��������ģʽ(fromQQ);
////                            break;
////                        case "*�ر�����ģʽ":
////                            �ر�����ģʽ(fromQQ);
////                            break;
////                        case "*�ϴ��ƺ�����":
////                            �ϴ��ƺ�����(fromQQ);
////                            break;
////                        //////////////////////////////////////////////////����
////                        case "*��ѯ�ʺ�":
////                        case "*��ѯ�˺�":
////                            ��ѯ�˺�(fromQQ);
////                            break;
////                        case "*�Ľ��":
////                            �Ľ��(fromQQ);
////                            break;
////                        case "*�ľ���":
////                            �ľ���(fromQQ);
////                            break;
////                        case "*�ĵ�ȯ":
////                            �ĵ�ȯ(fromQQ);
////                            break;
////                        case "*ǩ��":
////                            ǩ��(fromQQ);
////                            break;
////                        default: // ��������
////                            sendToOnlinePlayer(fromQQ, msg);
////                            break;
////                    }
////                }
////            }
////        } catch (SocketException e) {
////            System.err.println("Can't open socket");
////            System.exit(1);
////        } catch (IOException e) {
////            System.err.println("UdpHost init error");
////            e.printStackTrace();
////        }
////    }
////
////    private static void ��ѯ�˺�(final String qq) {
////        if (MapleParty.�����˴��� > 0) {
////            return;
////        }
////        /*
////        1������״̬
////        2�����
////        3������
////        4����ȯ
////        100��ʹ�ô���
////        101��ǩ������
////
////         */
////        if (Getqqstem(qq, 1) > 0) {
////            sendMsgToQQGroup(String.format("<" + �������� + "Ⱥ����>\r\n"
////                    + "�˺ţ�" + qq + "\r\n"
////                    + "��ң�" + Getqqstem(qq, 2) + "\r\n"
////                    + "���飺" + Getqqstem(qq, 3) + "\r\n"
////                    + "��ȯ��" + Getqqstem(qq, 4), qq));
////        } else {
////            sendMsgToQQGroup(String.format("<" + �������� + "Ⱥ����>:�˺�" + qq + "δ���������Ϸ��СZ����˹��ܡ�", qq));
////        }
////    }
////
////    private static void �ľ���(final String qq) {
////        if (MapleParty.�����˴��� > 0) {
////            return;
////        }
////        if (Getqqstem(qq, 1) <= 0) {
////            sendMsgToQQGroup(String.format("<" + �������� + "Ⱥ����>:�˺�" + qq + "δ���������Ϸ��СZ����˹��ܡ�", qq));
////            return;
////        }
////        int ���� = server.Start.ConfigValuesMap.get("Ⱥ�Ĳ�����");
////        if (���� == 0) {
////            return;
////        }
////        if (Getqqstem(qq, 4) >= ����) {
////            final double ���� = Math.ceil(Math.random() * 100);
////            if (���� >= 70) {
////                //�����
////                Gainqqstem(qq, 3, ����);
////                //��¼ʹ�ô���
////                Gainqqstem(qq, 100, 1);
////                sendMsgToQQGroup(String.format("<�ľ���>: " + qq + " ��ϲ��Ӯ�� " + ���� + " ���顣", qq));
////            } else {
////                //��ȡ���
////                Gainqqstem(qq, 3, -����);
////                //��¼ʹ�ô���
////                Gainqqstem(qq, 100, 1);
////                sendMsgToQQGroup(String.format("<�ľ���>: " + qq + " ��Ǹ������ " + ���� + " ���顣", qq));
////            }
////        } else {
////            sendMsgToQQGroup(String.format("<�ľ���>: " + qq + " �㲻�� " + ���� + " ���飬������Ϸ����Сz��ֵ��", qq));
////        }
////    }
////
////    private static void �ĵ�ȯ(final String qq) {
////        if (MapleParty.�����˴��� > 0) {
////            return;
////        }
////        if (Getqqstem(qq, 1) <= 0) {
////            sendMsgToQQGroup(String.format("<" + �������� + "Ⱥ����>:�˺�" + qq + "δ���������Ϸ��СZ����˹��ܡ�", qq));
////            return;
////        }
////        int ��ȯ = server.Start.ConfigValuesMap.get("Ⱥ�Ĳ���ȯ");
////        if (��ȯ == 0) {
////            return;
////        }
////        if (Getqqstem(qq, 4) >= ��ȯ) {
////            final double ���� = Math.ceil(Math.random() * 100);
////            if (���� >= 70) {
////                //�����
////                Gainqqstem(qq, 4, ��ȯ);
////                //��¼ʹ�ô���
////                Gainqqstem(qq, 100, 1);
////                sendMsgToQQGroup(String.format("<�ĵ�ȯ>: " + qq + " ��ϲ��Ӯ�� " + ��ȯ + " ��ȯ��", qq));
////            } else {
////                //��ȡ���
////                Gainqqstem(qq, 4, -��ȯ);
////                //��¼ʹ�ô���
////                Gainqqstem(qq, 100, 1);
////                sendMsgToQQGroup(String.format("<�ĵ�ȯ>: " + qq + " ��Ǹ������ " + ��ȯ + " ��ȯ��", qq));
////            }
////        } else {
////            sendMsgToQQGroup(String.format("<�ĵ�ȯ>: " + qq + " �㲻�� " + ��ȯ + " ��ȯ��������Ϸ����Сz��ֵ��", qq));
////        }
////    }
////
////    public long �жϽ��(final String qq) {
////        return Getqqstem(qq, 2);
////    }
////
////    public static void ���(final String qq, int s) {
////        Gainqqstem(qq, 2, s);
////    }
////
////    public long �жϾ���(final String qq) {
////        return Getqqstem(qq, 3);
////    }
////
////    public static void ����(final String qq, int s) {
////        Gainqqstem(qq, 3, s);
////    }
////
////    public long �жϵ�ȯ(final String qq) {
////        return Getqqstem(qq, 4);
////    }
////
////    public static void ��ȯ(final String qq, int s) {
////        Gainqqstem(qq, 4, s);
////    }
////
////    public long ��ʹ�ô���(final String qq) {
////        return Getqqstem(qq, 100);
////    }
////
////    public static void ʹ�ô���(final String qq, int s) {
////        Gainqqstem(qq, 100, s);
////    }
////
////    public long ��ʹǩ����(final String qq) {
////        return Getqqstem(qq, 101);
////    }
////
////    public static void ǩ������(final String qq, int s) {
////        Gainqqstem(qq, 101, s);
////    }
////
////    public static long ��ʹÿ��(final String qq) {
////        return Getqqlog(qq, 1);
////    }
////
////    public static void ����ÿ��(final String qq, int s) {
////        Gainqqlog(qq, 1, s);
////    }
////
////    //ǩ��
////    private static void ǩ��(final String qq) {
////        if (MapleParty.�����˴��� > 0) {
////            return;
////        }
////        if (Getqqstem(qq, 1) <= 0) {
////            sendMsgToQQGroup(String.format("<" + �������� + "Ⱥ����>:�˺�" + qq + "δ���������Ϸ��СZ����˹��ܡ�", qq));
////            return;
////        }
////        if (��ʹÿ��(qq) > 0) {
////            return;
////        }
////        if (server.Start.ConfigValuesMap.get("Ⱥǩ����ҽ���") == 0 && server.Start.ConfigValuesMap.get("Ⱥǩ�����齱��") == 0 && server.Start.ConfigValuesMap.get("Ⱥǩ����ȯ����") == 0) {
////            return;
////        }
////        ǩ������(qq, 1);
////        ����ÿ��(qq, 1);
////        final double ��� = Math.ceil(Math.random() * server.Start.ConfigValuesMap.get("Ⱥǩ����ҽ���"));
////        final double ���� = Math.ceil(Math.random() * server.Start.ConfigValuesMap.get("Ⱥǩ�����齱��"));
////        final double ��ȯ = Math.ceil(Math.random() * server.Start.ConfigValuesMap.get("Ⱥǩ����ȯ����"));
////        sendMsgToQQGroup(String.format("<" + �������� + "Ⱥǩ��>\r\n"
////                + "��ϲ " + qq + " ǩ���ɹ�\r\n"
////                + "��ý�ң�" + (int) ��� + "\r\n"
////                + "��õ�ȯ��" + (int) ��ȯ + "\r\n"
////                + "��þ��飺" + (int) ���� + "", qq));
////        ���(qq, (int) ���);
////        ��ȯ(qq, (int) ��ȯ);
////        ����(qq, (int) ����);
////    }
////
////    private static void �Ľ��(final String qq) {
////        if (MapleParty.�����˴��� > 0) {
////            return;
////        }
////        if (Getqqstem(qq, 1) <= 0) {
////            sendMsgToQQGroup(String.format("<" + �������� + "Ⱥ����>:�˺�" + qq + "δ���������Ϸ��СZ����˹��ܡ�", qq));
////            return;
////        }
////        int ��� = server.Start.ConfigValuesMap.get("Ⱥ�Ĳ����");
////        if (��� == 0) {
////            return;
////        }
////        if (Getqqstem(qq, 2) >= ���) {
////            final double ���� = Math.ceil(Math.random() * 100);
////            if (���� >= 70) {
////                //�����
////                Gainqqstem(qq, 2, ���);
////                //��¼ʹ�ô���
////                Gainqqstem(qq, 100, 1);
////                sendMsgToQQGroup(String.format("<�Ľ��>: " + qq + " ��ϲ��Ӯ�� " + ��� + " ��ҡ�", qq));
////            } else {
////                //��ȡ���
////                Gainqqstem(qq, 2, -���);
////                //��¼ʹ�ô���
////                Gainqqstem(qq, 100, 1);
////                sendMsgToQQGroup(String.format("<�Ľ��>: " + qq + " ��Ǹ������ " + ��� + " ��ҡ�", qq));
////            }
////        } else {
////            sendMsgToQQGroup(String.format("<�Ľ��>: " + qq + " �㲻�� " + ��� + " ��ң�������Ϸ����Сz��ֵ��", qq));
////        }
////    }
////
////    private static int Getqqstem(String Name, int Channale) {
////        int ret = -1;
////        try {
////            Connection con = DatabaseConnection.getConnection();
////            PreparedStatement ps = con.prepareStatement("SELECT * FROM qqstem WHERE channel = ? and Name = ?");
////            ps.setInt(1, Channale);
////            ps.setString(2, Name);
////            ResultSet rs = ps.executeQuery();
////            rs.next();
////            ret = rs.getInt("Point");
////            rs.close();
////            ps.close();
////        } catch (SQLException ex) {
////        }
////        return ret;
////    }
////
////    public static void Gainqqstem(String qq, int Channale, int Piot) {
////        try {
////            int ret = Getqqstem(qq, Channale);
////            if (ret == -1) {
////                ret = 0;
////                PreparedStatement ps = null;
////                try {
////                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO qqstem (channel, Name,Point) VALUES (?, ?, ?)");
////                    ps.setInt(1, Channale);
////                    ps.setString(2, qq);
////                    ps.setInt(3, ret);
////                    ps.execute();
////                } catch (SQLException e) {
////                    System.out.println("xxxxxxxx:" + e);
////                } finally {
////                    try {
////                        if (ps != null) {
////                            ps.close();
////                        }
////                    } catch (SQLException e) {
////                        System.out.println("xxxxxxxxzzzzzzz:" + e);
////                    }
////                }
////            }
////            ret += Piot;
////            Connection con = DatabaseConnection.getConnection();
////            PreparedStatement ps = con.prepareStatement("UPDATE qqstem SET `Point` = ? WHERE Name = ? and channel = ?");
////            ps.setInt(1, ret);
////            ps.setString(2, qq);
////            ps.setInt(3, Channale);
////            ps.execute();
////            ps.close();
////        } catch (SQLException sql) {
////            System.err.println("Getqqstem!!55" + sql);
////        }
////    }
////
////    private static int Getqqlog(String Name, int Channale) {
////        int ret = -1;
////        try {
////            Connection con = DatabaseConnection.getConnection();
////            PreparedStatement ps = con.prepareStatement("SELECT * FROM qqlog WHERE channel = ? and Name = ?");
////            ps.setInt(1, Channale);
////            ps.setString(2, Name);
////            ResultSet rs = ps.executeQuery();
////            rs.next();
////            ret = rs.getInt("Point");
////            rs.close();
////            ps.close();
////        } catch (SQLException ex) {
////        }
////        return ret;
////    }
////
////    public static void Gainqqlog(String qq, int Channale, int Piot) {
////        try {
////            int ret = Getqqlog(qq, Channale);
////            if (ret == -1) {
////                ret = 0;
////                PreparedStatement ps = null;
////                try {
////                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO qqlog (channel, Name,Point) VALUES (?, ?, ?)");
////                    ps.setInt(1, Channale);
////                    ps.setString(2, qq);
////                    ps.setInt(3, ret);
////                    ps.execute();
////                } catch (SQLException e) {
////                    System.out.println("xxxxxxxx:" + e);
////                } finally {
////                    try {
////                        if (ps != null) {
////                            ps.close();
////                        }
////                    } catch (SQLException e) {
////                        System.out.println("xxxxxxxxzzzzzzz:" + e);
////                    }
////                }
////            }
////            ret += Piot;
////            Connection con = DatabaseConnection.getConnection();
////            PreparedStatement ps = con.prepareStatement("UPDATE qqlog SET `Point` = ? WHERE Name = ? and channel = ?");
////            ps.setInt(1, ret);
////            ps.setString(2, qq);
////            ps.setInt(3, Channale);
////            ps.execute();
////            ps.close();
////        } catch (SQLException sql) {
////            System.err.println("Getqqlog!!55" + sql);
////        }
////    }
////
////    private static void �ϴ��ƺ�����(final String qq) {
////        if ("71447500".equals(qq) || "34443647".equals(qq)) {
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:��ʼ�ϴ����������ݵ��Ʒ�������", qq));
////            String data = "";
////            try {
////                Connection con = DatabaseConnection.getConnection();
////                PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM accounts WHERE banned > 0");
////                try (ResultSet rs = ps.executeQuery()) {
////                    while (rs.next()) {
////                        data = rs.getString("DATA");
////                        FileoutputUtil.logToZev("blacklist", ",\r\n" + data + "/0");
////                    }
////                }
////                ps.close();
////            } catch (SQLException Ex) {
////                System.err.println("" + Ex);
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�ϴ�������������ɣ�ʹ������ָ����Ч��", qq));
////        }
////    }
////
////    private static void �����ƺ�����(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:��ʼ�����ƺ��������ݡ�", qq));
////            GetCloudBacklist();
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�ƺ������������¼�����ɡ�", qq));
////        }
////    }
////
////    private static void ��������ģʽ(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            MapleParty.����ģʽ++;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:���������ģʽ�����ɹ���", qq));
////        }
////    }
////
////    private static void �ر�����ģʽ(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            MapleParty.����ģʽ = 0;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:���������ģʽ�رճɹ���", qq));
////        }
////    }
////
////    private static void �����˺���ʾ(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            MapleParty.�˺���ʾ++;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�����˺���ʾ��", qq));
////        }
////    }
////
////    private static void �ر��˺���ʾ(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            MapleParty.�˺���ʾ = 0;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�ر��˺���ʾ��", qq));
////        }
////    }
////
////    private static void �汾���(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            String v2 = Class2.Pgv();
////            if (Integer.parseInt(v2) != �汾) {
////                sendMsgToQQGroup(""
////                        + "<" + �������� + ">\r\n"
////                        + "��ǰ�汾:v" + �汾 + "\r\n"
////                        + "���°汾:v" + Integer.parseInt(v2) + "\r\n"
////                        + "��Ҫ��������˽��и���"
////                );
////            } else {
////                sendMsgToQQGroup(""
////                        + "<" + �������� + ">\r\n"
////                        + "��ǰ�汾:v" + �汾 + "\r\n"
////                        + "���°汾:v" + Integer.parseInt(v2) + "\r\n"
////                        + "�����Ŀǰ�����°汾��"
////                );
////            }
////        }
////    }
////
////    private static void ��������(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            int p = 0;
////            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
////                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
////                    if (chr != null) {
////                        ++p;
////                    }
////                }
////            }
////            sendMsgToQQGroup("<" + �������� + ">������:�Ѿ����������˽�ĸ�������");
////            sendMsg("<" + �������� + ">��\r\n"
////                    + "��ǰ���ߣ�" + p + " ��\r\n"
////                    //+ "��½�û���" + MapleParty.��ɫ��½���� + " ��\r\n"
////                    // + "��½������" + MapleParty.��ҵ�½���� + " ��\r\n"
////                    + "����ʱ����" + MapleParty.���������ʱ�� + " ����"
////                    + "", "71447500");
////            if (!"71447500".equals(ServerProperties.getProperty("ZEV.QQ1"))) {
////                sendMsg("<" + �������� + ">��\r\n"
////                        + "��ǰ���ߣ�" + p + " ��\r\n"
////                        // + "��½�û���" + MapleParty.��ɫ��½���� + " ��\r\n"
////                        //+ "��½������" + MapleParty.��ҵ�½���� + " ��\r\n"
////                        + "����ʱ����" + MapleParty.���������ʱ�� + " ����"
////                        + "", "" + ServerProperties.getProperty("ZEV.QQ1") + "");
////            }
////            if (!ServerProperties.getProperty("ZEV.QQ1").equals(ServerProperties.getProperty("ZEV.QQ2"))) {
////                sendMsg("<" + �������� + ">��\r\n"
////                        + "��ǰ���ߣ�" + p + " ��\r\n"
////                        // + "��½�û���" + MapleParty.��ɫ��½���� + " ��\r\n"
////                        // + "��½������" + MapleParty.��ҵ�½���� + " ��\r\n"
////                        + "����ʱ����" + MapleParty.���������ʱ�� + " ����"
////                        + "", "" + ServerProperties.getProperty("ZEV.QQ2") + "");
////            }
////        }
////    }
////
////    private static void ��ʼ����ɱ�(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.����ɱ� > 0) {
////                sendMsgToQQGroup(String.format("<" + �������� + ">������:��Ѿ���ʼ���޷��ظ���ʼ��", qq));
////                return;
////            }
////            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "<" + �������� + ">������:�[����ɱ]���� 5 ���Ӻ� 2 Ƶ����ʼ�����λҪ�μӵ�С��飬�����ִ���Ϸ�ش��μӣ����ʼ�󽫲������ٴν��롣").getBytes());
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�[����ɱ]���� 5 ���Ӻ� 2 Ƶ����ʼ�����λҪ�μӵ�С��飬�����ִ���Ϸ�ش��μӣ����ʼ�󽫲������ٴν��롣", qq));
////            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
////                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
////                    mch.startMapEffect("�[����ɱ]���� 5 ���Ӻ� 2 Ƶ����ʼ�����λҪ�μӵ�С��飬�����ִ���Ϸ�ش��μӣ����ʼ�󽫲������ٴν��롣", 5120027);
////                }
////            }
////            new Thread() {
////                @Override
////                public void run() {
////                    try {
////                        Thread.sleep(5 * 60 * 1000);//�������Զ��ر�
////                        World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "<" + �������� + ">������:�[����ɱ]��ʼ����Ϸ�ش��رս���ڣ���������һλ��ң�����Լ����ɻ��ʤ����").getBytes());
////                        sendMsgToQQGroup(String.format("<" + �������� + ">������:�[����ɱ]��ʼ����Ϸ�ش��رս���ڣ���������һλ��ң�����Լ����ɻ��ʤ����", qq));
////                        for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
////                            for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
////                                mch.startMapEffect("�[����ɱ]��ʼ����Ϸ�ش��رս���ڣ���������һλ��ң�����Լ����ɻ��ʤ����", 5120027);
////                            }
////                        }
////                        MapleParty.����ɱ� = 1;
////                    } catch (InterruptedException e) {
////                    }
////                }
////            }.start();
////        }
////    }
////
////    private static void ��������ɱ�(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.����ɱ� == 0) {
////                sendMsgToQQGroup(String.format("<" + �������� + ">������:���δ��ʼ��", qq));
////                return;
////            }
////            MapleParty.����ɱ� = 0;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:����Ա�����˴���ɱ���", qq));
////        }
////    }
////
////    private static void ��������ͳ��(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            MapleParty.����ͳ�� = 0;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�Ѿ���������ͳ�ơ�", qq));
////        }
////    }
////
////    private static void �ر�����ͳ��(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            MapleParty.����ͳ��++;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�Ѿ��ر�����ͳ�ơ�", qq));
////        }
////    }
////
////    private static void �ر���Ϸ����Ⱥ���(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            MapleParty.��Ϸ����Ⱥ���++;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�Ѿ��ر���Ϸ���������Ⱥ�", qq));
////        }
////    }
////
////    private static void ������Ϸ����Ⱥ���(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            MapleParty.��Ϸ����Ⱥ��� = 0;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�Ѿ�������Ϸ���������Ⱥ�", qq));
////        }
////    }
////
////    private static void �ر���Ϣͬ��(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            MapleParty.��Ϣͬ��++;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�Ѿ��ر�Ⱥ����Ϸ��Ϣͬ����", qq));
////        }
////    }
////
////    private static void ������Ϣͬ��(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            MapleParty.��Ϣͬ�� = 0;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�Ѿ�����Ⱥ����Ϸ��Ϣͬ����", qq));
////        }
////    }
////
////    private static void ����(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            MapleParty.�����˴���++;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:���Ѿ��������״̬����ͣ������", qq));
////        }
////    }
////
////    private static void ����(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            MapleParty.�����˴��� = 0;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:���Ѿ����뿪��״̬����ʼ������", qq));
////        }
////    }
////
////    private static void �ж�ȫ������(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
////                cserv.getPlayerStorage().��������(true);
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�Ѿ��ɹ��ж�����������ҡ�", qq));
////        }
////    }
////
////    private static void ���ط����(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            long start = System.currentTimeMillis();
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:����˿����������룬��ʱ��ҿ��ܻῨ�٣������˳���Ϸ���Ե�Ƭ�̼ȿɻָ���", qq));
////            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[����Ա��Ϣ]:����˿����������룬��ʱ��ҿ��ܻῨ�٣������˳���Ϸ���Ե�Ƭ�̼ȿɻָ���").getBytes());
////            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
////                //�����̵�
////                MapleShopFactory.getInstance().clear();
////                //���ش���
////                PortalScriptManager.getInstance().clearScripts();
////                //������ƷWZ
////                MapleItemInformationProvider.getInstance().load();
////                //�����̳�
////                CashItemFactory.getInstance().initialize();
////                //���ر���
////                MapleMonsterInformationProvider.getInstance().clearDrops();
////                //���ؼ���
////                MapleGuild.loadAll(); //(this); ;
////                //���ر���
////                ReactorScriptManager.getInstance().clearDrops();
////                SendPacketOpcode.reloadValues();
////                RecvPacketOpcode.reloadValues();
////
////            }
////            long now = System.currentTimeMillis() - start;
////            long seconds = now / 1000;
////            long ms = now % 1000;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�����������ϣ���ʱ: " + seconds + " �롣", qq));
////            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[����Ա��Ϣ]:�����������ϣ���ʱ: " + seconds + " �롣").getBytes());
////        }
////    }
////
////    private static void ����1000��ȯ����(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            String ��� = "";
////            String chars = "1234567890aAbBcCdDeEfFgGhHiIjJkKlLmMNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
////            char ����1 = chars.charAt((int) (Math.random() * 62));
////            char ����2 = chars.charAt((int) (Math.random() * 62));
////            char ����3 = chars.charAt((int) (Math.random() * 62));
////            char ����4 = chars.charAt((int) (Math.random() * 62));
////            char ����5 = chars.charAt((int) (Math.random() * 62));
////            char ����6 = chars.charAt((int) (Math.random() * 62));
////            char ����7 = chars.charAt((int) (Math.random() * 62));
////            char ����8 = chars.charAt((int) (Math.random() * 62));
////            char ����9 = chars.charAt((int) (Math.random() * 62));
////            char ����10 = chars.charAt((int) (Math.random() * 62));
////            char ����11 = chars.charAt((int) (Math.random() * 62));
////            char ����12 = chars.charAt((int) (Math.random() * 62));
////            char ����13 = chars.charAt((int) (Math.random() * 62));
////            char ����14 = chars.charAt((int) (Math.random() * 62));
////            char ����15 = chars.charAt((int) (Math.random() * 62));
////            char ����16 = chars.charAt((int) (Math.random() * 62));
////            char ����17 = chars.charAt((int) (Math.random() * 62));
////            char ����18 = chars.charAt((int) (Math.random() * 62));
////            char ����19 = chars.charAt((int) (Math.random() * 62));
////            char ����20 = chars.charAt((int) (Math.random() * 62));
////            char ����21 = chars.charAt((int) (Math.random() * 62));
////            char ����22 = chars.charAt((int) (Math.random() * 62));
////            char ����23 = chars.charAt((int) (Math.random() * 62));
////            char ����24 = chars.charAt((int) (Math.random() * 62));
////            char ����25 = chars.charAt((int) (Math.random() * 62));
////            char ����26 = chars.charAt((int) (Math.random() * 62));
////            char ����27 = chars.charAt((int) (Math.random() * 62));
////            char ����28 = chars.charAt((int) (Math.random() * 62));
////            char ����29 = chars.charAt((int) (Math.random() * 62));
////            char ����30 = chars.charAt((int) (Math.random() * 62));
////            String ��ֵ�� = "1Q" + ����1 + "" + ����2 + "" + ����3 + "" + ����4 + "" + ����5 + "" + ����6 + "" + ����7 + "" + ����8 + "" + ����9 + "" + ����10 + "" + ����11 + "" + ����12 + "" + ����13 + "" + ����14 + "" + ����15 + "" + ����16 + "" + ����17 + "" + ����18 + "" + ����19 + "" + ����20 + "" + ����21 + "" + ����22 + "" + ����23 + "" + ����24 + "" + ����25 + "" + ����26 + "" + ����27 + "" + ����28 + "" + ����29 + "" + ����30 + "";
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO z��ȯ�һ��� ( Name,Point,channel) VALUES ( ?, ?, ?)")) {
////                ps.setString(1, ��ֵ��);
////                ps.setInt(2, 1000);
////                ps.setInt(3, 1);
////                ps.executeUpdate();
////                FileoutputUtil.logToFile("��ֵ����̨���/[" + CurrentReadable_Date() + "]1000��ȯ��ֵ��[������].txt", "" + ��ֵ�� + "\r\n");
////                sendMsgToQQGroup(String.format("<" + �������� + ">1000��ȯ������:" + ��ֵ�� + "", qq));
////            } catch (SQLException ex) {
////
////            }
////        }
////    }
////
////    private static void ����2000��ȯ����(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            String ��� = "";
////            String chars = "1234567890aAbBcCdDeEfFgGhHiIjJkKlLmMNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
////            char ����1 = chars.charAt((int) (Math.random() * 62));
////            char ����2 = chars.charAt((int) (Math.random() * 62));
////            char ����3 = chars.charAt((int) (Math.random() * 62));
////            char ����4 = chars.charAt((int) (Math.random() * 62));
////            char ����5 = chars.charAt((int) (Math.random() * 62));
////            char ����6 = chars.charAt((int) (Math.random() * 62));
////            char ����7 = chars.charAt((int) (Math.random() * 62));
////            char ����8 = chars.charAt((int) (Math.random() * 62));
////            char ����9 = chars.charAt((int) (Math.random() * 62));
////            char ����10 = chars.charAt((int) (Math.random() * 62));
////            char ����11 = chars.charAt((int) (Math.random() * 62));
////            char ����12 = chars.charAt((int) (Math.random() * 62));
////            char ����13 = chars.charAt((int) (Math.random() * 62));
////            char ����14 = chars.charAt((int) (Math.random() * 62));
////            char ����15 = chars.charAt((int) (Math.random() * 62));
////            char ����16 = chars.charAt((int) (Math.random() * 62));
////            char ����17 = chars.charAt((int) (Math.random() * 62));
////            char ����18 = chars.charAt((int) (Math.random() * 62));
////            char ����19 = chars.charAt((int) (Math.random() * 62));
////            char ����20 = chars.charAt((int) (Math.random() * 62));
////            char ����21 = chars.charAt((int) (Math.random() * 62));
////            char ����22 = chars.charAt((int) (Math.random() * 62));
////            char ����23 = chars.charAt((int) (Math.random() * 62));
////            char ����24 = chars.charAt((int) (Math.random() * 62));
////            char ����25 = chars.charAt((int) (Math.random() * 62));
////            char ����26 = chars.charAt((int) (Math.random() * 62));
////            char ����27 = chars.charAt((int) (Math.random() * 62));
////            char ����28 = chars.charAt((int) (Math.random() * 62));
////            char ����29 = chars.charAt((int) (Math.random() * 62));
////            char ����30 = chars.charAt((int) (Math.random() * 62));
////            String ��ֵ�� = "2Q" + ����1 + "" + ����2 + "" + ����3 + "" + ����4 + "" + ����5 + "" + ����6 + "" + ����7 + "" + ����8 + "" + ����9 + "" + ����10 + "" + ����11 + "" + ����12 + "" + ����13 + "" + ����14 + "" + ����15 + "" + ����16 + "" + ����17 + "" + ����18 + "" + ����19 + "" + ����20 + "" + ����21 + "" + ����22 + "" + ����23 + "" + ����24 + "" + ����25 + "" + ����26 + "" + ����27 + "" + ����28 + "" + ����29 + "" + ����30 + "";
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO z��ȯ�һ��� ( Name,Point,channel) VALUES ( ?, ?, ?)")) {
////                ps.setString(1, ��ֵ��);
////                ps.setInt(2, 2000);
////                ps.setInt(3, 1);
////                ps.executeUpdate();
////                FileoutputUtil.logToFile("��ֵ����̨���/[" + CurrentReadable_Date() + "]2000��ȯ��ֵ��[������].txt", "" + ��ֵ�� + "\r\n");
////                sendMsgToQQGroup(String.format("<" + �������� + ">2000��ȯ������:" + ��ֵ�� + "", qq));
////            } catch (SQLException ex) {
////
////            }
////        }
////    }
////
////    private static void ����5000��ȯ����(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            String ��� = "";
////            String chars = "1234567890aAbBcCdDeEfFgGhHiIjJkKlLmMNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
////            char ����1 = chars.charAt((int) (Math.random() * 62));
////            char ����2 = chars.charAt((int) (Math.random() * 62));
////            char ����3 = chars.charAt((int) (Math.random() * 62));
////            char ����4 = chars.charAt((int) (Math.random() * 62));
////            char ����5 = chars.charAt((int) (Math.random() * 62));
////            char ����6 = chars.charAt((int) (Math.random() * 62));
////            char ����7 = chars.charAt((int) (Math.random() * 62));
////            char ����8 = chars.charAt((int) (Math.random() * 62));
////            char ����9 = chars.charAt((int) (Math.random() * 62));
////            char ����10 = chars.charAt((int) (Math.random() * 62));
////            char ����11 = chars.charAt((int) (Math.random() * 62));
////            char ����12 = chars.charAt((int) (Math.random() * 62));
////            char ����13 = chars.charAt((int) (Math.random() * 62));
////            char ����14 = chars.charAt((int) (Math.random() * 62));
////            char ����15 = chars.charAt((int) (Math.random() * 62));
////            char ����16 = chars.charAt((int) (Math.random() * 62));
////            char ����17 = chars.charAt((int) (Math.random() * 62));
////            char ����18 = chars.charAt((int) (Math.random() * 62));
////            char ����19 = chars.charAt((int) (Math.random() * 62));
////            char ����20 = chars.charAt((int) (Math.random() * 62));
////            char ����21 = chars.charAt((int) (Math.random() * 62));
////            char ����22 = chars.charAt((int) (Math.random() * 62));
////            char ����23 = chars.charAt((int) (Math.random() * 62));
////            char ����24 = chars.charAt((int) (Math.random() * 62));
////            char ����25 = chars.charAt((int) (Math.random() * 62));
////            char ����26 = chars.charAt((int) (Math.random() * 62));
////            char ����27 = chars.charAt((int) (Math.random() * 62));
////            char ����28 = chars.charAt((int) (Math.random() * 62));
////            char ����29 = chars.charAt((int) (Math.random() * 62));
////            char ����30 = chars.charAt((int) (Math.random() * 62));
////            String ��ֵ�� = "5Q" + ����1 + "" + ����2 + "" + ����3 + "" + ����4 + "" + ����5 + "" + ����6 + "" + ����7 + "" + ����8 + "" + ����9 + "" + ����10 + "" + ����11 + "" + ����12 + "" + ����13 + "" + ����14 + "" + ����15 + "" + ����16 + "" + ����17 + "" + ����18 + "" + ����19 + "" + ����20 + "" + ����21 + "" + ����22 + "" + ����23 + "" + ����24 + "" + ����25 + "" + ����26 + "" + ����27 + "" + ����28 + "" + ����29 + "" + ����30 + "";
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO z��ȯ�һ��� ( Name,Point,channel) VALUES ( ?, ?, ?)")) {
////                ps.setString(1, ��ֵ��);
////                ps.setInt(2, 5000);
////                ps.setInt(3, 1);
////                ps.executeUpdate();
////                FileoutputUtil.logToFile("��ֵ����̨���/[" + CurrentReadable_Date() + "]5000��ȯ��ֵ��[������].txt", "" + ��ֵ�� + "\r\n");
////                sendMsgToQQGroup(String.format("<" + �������� + ">5000��ȯ������:" + ��ֵ�� + "", qq));
////            } catch (SQLException ex) {
////
////            }
////        }
////    }
////
////    private static void ����10000��ȯ����(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            String ��� = "";
////            String chars = "1234567890aAbBcCdDeEfFgGhHiIjJkKlLmMNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
////            char ����1 = chars.charAt((int) (Math.random() * 62));
////            char ����2 = chars.charAt((int) (Math.random() * 62));
////            char ����3 = chars.charAt((int) (Math.random() * 62));
////            char ����4 = chars.charAt((int) (Math.random() * 62));
////            char ����5 = chars.charAt((int) (Math.random() * 62));
////            char ����6 = chars.charAt((int) (Math.random() * 62));
////            char ����7 = chars.charAt((int) (Math.random() * 62));
////            char ����8 = chars.charAt((int) (Math.random() * 62));
////            char ����9 = chars.charAt((int) (Math.random() * 62));
////            char ����10 = chars.charAt((int) (Math.random() * 62));
////            char ����11 = chars.charAt((int) (Math.random() * 62));
////            char ����12 = chars.charAt((int) (Math.random() * 62));
////            char ����13 = chars.charAt((int) (Math.random() * 62));
////            char ����14 = chars.charAt((int) (Math.random() * 62));
////            char ����15 = chars.charAt((int) (Math.random() * 62));
////            char ����16 = chars.charAt((int) (Math.random() * 62));
////            char ����17 = chars.charAt((int) (Math.random() * 62));
////            char ����18 = chars.charAt((int) (Math.random() * 62));
////            char ����19 = chars.charAt((int) (Math.random() * 62));
////            char ����20 = chars.charAt((int) (Math.random() * 62));
////            char ����21 = chars.charAt((int) (Math.random() * 62));
////            char ����22 = chars.charAt((int) (Math.random() * 62));
////            char ����23 = chars.charAt((int) (Math.random() * 62));
////            char ����24 = chars.charAt((int) (Math.random() * 62));
////            char ����25 = chars.charAt((int) (Math.random() * 62));
////            char ����26 = chars.charAt((int) (Math.random() * 62));
////            char ����27 = chars.charAt((int) (Math.random() * 62));
////            char ����28 = chars.charAt((int) (Math.random() * 62));
////            char ����29 = chars.charAt((int) (Math.random() * 62));
////            char ����30 = chars.charAt((int) (Math.random() * 62));
////            String ��ֵ�� = "1W" + ����1 + "" + ����2 + "" + ����3 + "" + ����4 + "" + ����5 + "" + ����6 + "" + ����7 + "" + ����8 + "" + ����9 + "" + ����10 + "" + ����11 + "" + ����12 + "" + ����13 + "" + ����14 + "" + ����15 + "" + ����16 + "" + ����17 + "" + ����18 + "" + ����19 + "" + ����20 + "" + ����21 + "" + ����22 + "" + ����23 + "" + ����24 + "" + ����25 + "" + ����26 + "" + ����27 + "" + ����28 + "" + ����29 + "" + ����30 + "";
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO z��ȯ�һ��� ( Name,Point,channel) VALUES ( ?, ?, ?)")) {
////                ps.setString(1, ��ֵ��);
////                ps.setInt(2, 10000);
////                ps.setInt(3, 1);
////                ps.executeUpdate();
////                FileoutputUtil.logToFile("��ֵ����̨���/[" + CurrentReadable_Date() + "]10000��ȯ��ֵ��[������].txt", "" + ��ֵ�� + "\r\n");
////                sendMsgToQQGroup(String.format("<" + �������� + ">10000��ȯ������:" + ��ֵ�� + "", qq));
////            } catch (SQLException ex) {
////
////            }
////        }
////    }
////
////    private static void ��ʼ��ѩ�����(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            final MapleEventType type = MapleEventType.getByString("ѩ����");
////            if (type == null) {
////                final StringBuilder sb = new StringBuilder("Ŀǰ���ŵĻ��: ");
////                for (MapleEventType t : MapleEventType.values()) {
////                    sb.append(t.name()).append(",");
////                }
////                //dropMessage(5, sb.toString().substring(0, sb.toString().length() - 1));
////            }
////            final String msg = MapleEvent.scheduleEvent(type, ChannelServer.getInstance(2));
////            MapleParty.ѩ����++;
////            if (msg.length() > 0) {
////                //c.getPlayer().dropMessage(5, msg);
////            }
////        }
////    }
////
////    private static void ��ѩ�������ʼ(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.ѩ���� > 0) {
////                MapleEvent.setEvent(ChannelServer.getInstance(2), true);
////            } else {
////                sendMsgToQQGroup(String.format("<" + �������� + ">������:���ȡ���ʼ��ѩ�������������ҵ�������ʹ�á���ѩ�������ʼ����ʼ���", qq));
////            }
////        }
////    }
////
////    private static void ����1Сʱ˫������(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            int ԭʼ���� = Integer.parseInt(ServerProperties.getProperty("ZEV.Exp"));
////            int ˫������ = ԭʼ���� * 2;
////            int seconds = 0;
////            int mins = 0;
////            int hours = 1;
////            int time = seconds + (mins * 60) + (hours * 60 * 60);
////            final String rate = "����";
////            World.scheduleRateDelay(rate, time);
////            for (ChannelServer cservs : ChannelServer.getAllInstances()) {
////                cservs.setExpRate(˫������);
////            }
////            World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[���ʻ] : ��Ϸ��ʼ 2 ����־����������� " + hours + " Сʱ�����λ��ҿ񻶰ɣ�").getBytes());
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:��Ϸ���� " + hours + " Сʱ˫��������", qq));
////        }
////    }
////
////    private static void ����1Сʱ˫������(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            int ԭʼ���� = Integer.parseInt(ServerProperties.getProperty("ZEV.Drop"));
////            int ˫�����ʻ = ԭʼ���� * 2;
////            int seconds = 0;
////            int mins = 0;
////            int hours = 1;
////            int time = seconds + (mins * 60) + (hours * 60 * 60);
////            final String rate = "����";
////            World.scheduleRateDelay(rate, time);
////            for (ChannelServer cservs : ChannelServer.getAllInstances()) {
////                cservs.setExpRate(˫�����ʻ);
////            }
////            World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[���ʻ] : ��Ϸ��ʼ 2 ����ֱ��ʻ�������� " + hours + " Сʱ�����λ��ҿ񻶰ɣ�").getBytes());
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:��Ϸ���� " + hours + " Сʱ˫�����ʻ��", qq));
////        }
////    }
////
////    private static void ����2Сʱ˫������(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            int ԭʼ���� = Integer.parseInt(ServerProperties.getProperty("ZEV.Drop"));
////            int ˫�����ʻ = ԭʼ���� * 2;
////            int seconds = 0;
////            int mins = 0;
////            int hours = 2;
////            int time = seconds + (mins * 60) + (hours * 60 * 60);
////            final String rate = "����";
////            World.scheduleRateDelay(rate, time);
////            for (ChannelServer cservs : ChannelServer.getAllInstances()) {
////                cservs.setExpRate(˫�����ʻ);
////            }
////            World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[���ʻ] : ��Ϸ��ʼ 2 ����ֱ��ʻ�������� " + hours + " Сʱ�����λ��ҿ񻶰ɣ�").getBytes());
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:��Ϸ���� " + hours + " Сʱ˫�����ʻ��", qq));
////        }
////    }
////
////    private static void ����2Сʱ˫������(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            int ԭʼ���� = Integer.parseInt(ServerProperties.getProperty("ZEV.Exp"));
////            int ˫������ = ԭʼ���� * 2;
////            int seconds = 0;
////            int mins = 0;
////            int hours = 2;
////            int time = seconds + (mins * 60) + (hours * 60 * 60);
////            final String rate = "����";
////            World.scheduleRateDelay(rate, time);
////            for (ChannelServer cservs : ChannelServer.getAllInstances()) {
////                cservs.setExpRate(˫������);
////            }
////            World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[���ʻ] : ��Ϸ��ʼ 2 ����־����������� " + hours + " Сʱ�����λ��ҿ񻶰ɣ�").getBytes());
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:��Ϸ���� " + hours + " Сʱ˫��������", qq));
////        }
////    }
////
////    private static void ������ҵ�½(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            PreparedStatement ps1 = null;
////            ResultSet rs = null;
////            try {
////                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
////                ps1.setInt(1, 1);
////                rs = ps1.executeQuery();
////                if (rs.next()) {
////                    String sqlstr = " delete from configvalues where id =2013";
////                    ps1.executeUpdate(sqlstr);
////                }
////            } catch (SQLException ex) {
////            }
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
////                ps.setInt(1, 2013);
////                ps.setString(2, "��ֹ��½����");
////                ps.setInt(3, 1);
////                ps.executeUpdate();
////                server.Start.GetConfigValues();
////            } catch (SQLException ex) {
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:������ҵ�½��Ϸ��", qq));
////        }
////    }
////
////    //�����ƺ�����
////    private static void �����ƺ�����(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            MapleParty.�ƺ����� = 1;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�ƺ����������ɹ���", qq));
////        }
////    }
////
////    private static void �ر��ƺ�����(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            MapleParty.�ƺ����� = 0;
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�ƺ������رճɹ���", qq));
////        }
////    }
////
////    private static void ��ֹ��ҵ�½(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            PreparedStatement ps1 = null;
////            ResultSet rs = null;
////            try {
////                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
////                ps1.setInt(1, 1);
////                rs = ps1.executeQuery();
////                if (rs.next()) {
////                    String sqlstr = " delete from configvalues where id =2013";
////                    ps1.executeUpdate(sqlstr);
////                }
////            } catch (SQLException ex) {
////            }
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
////                ps.setInt(1, 2013);
////                ps.setString(2, "��ֹ��½����");
////                ps.setInt(3, 0);
////                ps.executeUpdate();
////                server.Start.GetConfigValues();
////            } catch (SQLException ex) {
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:��ֹ��ҵ�½��Ϸ��", qq));
////        }
////    }
////
////    private static void ������ҽ���(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            PreparedStatement ps1 = null;
////            ResultSet rs = null;
////            try {
////                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
////                ps1.setInt(1, 1);
////                rs = ps1.executeQuery();
////                if (rs.next()) {
////                    String sqlstr = " delete from configvalues where id =2011";
////                    ps1.executeUpdate(sqlstr);
////                }
////            } catch (SQLException ex) {
////            }
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
////                ps.setInt(1, 2011);
////                ps.setString(2, "��ҽ��׿���");
////                ps.setInt(3, 0);
////                ps.executeUpdate();
////                server.Start.GetConfigValues();
////            } catch (SQLException ex) {
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�����������Ϸ�н��ס�", qq));
////        }
////    }
////
////    private static void ��ֹ��ҽ���(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            PreparedStatement ps1 = null;
////            ResultSet rs = null;
////            try {
////                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
////                ps1.setInt(1, 1);
////                rs = ps1.executeQuery();
////                if (rs.next()) {
////                    String sqlstr = " delete from configvalues where id =2011";
////                    ps1.executeUpdate(sqlstr);
////                }
////            } catch (SQLException ex) {
////            }
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
////                ps.setInt(1, 2011);
////                ps.setString(2, "��ҽ��׿���");
////                ps.setInt(3, 1);
////                ps.executeUpdate();
////                server.Start.GetConfigValues();
////            } catch (SQLException ex) {
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:��ֹ�������Ϸ�н��ס�", qq));
////        }
////    }
////
////    private static void ����ȫ������(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            PreparedStatement ps1 = null;
////            ResultSet rs = null;
////            try {
////                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
////                ps1.setInt(1, 1);
////                rs = ps1.executeQuery();
////                if (rs.next()) {
////                    String sqlstr = " delete from configvalues where id =2014";
////                    ps1.executeUpdate(sqlstr);
////                }
////            } catch (SQLException ex) {
////            }
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
////                ps.setInt(1, 2014);
////                ps.setString(2, "ȫ����������");
////                ps.setInt(3, 0);
////                ps.executeUpdate();
////                server.Start.GetConfigValues();
////            } catch (SQLException ex) {
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:����˿���ȫ��������", qq));
////            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[����Ա��Ϣ]:����˿���ȫ��������").getBytes());
////        }
////    }
////
////    private static void ������½��֤(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            PreparedStatement ps1 = null;
////            ResultSet rs = null;
////            try {
////                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
////                ps1.setInt(1, 1);
////                rs = ps1.executeQuery();
////                if (rs.next()) {
////                    String sqlstr = " delete from configvalues where id =2040";
////                    ps1.executeUpdate(sqlstr);
////                }
////            } catch (SQLException ex) {
////            }
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
////                ps.setInt(1, 2040);
////                ps.setString(2, "��½��֤����");
////                ps.setInt(3, 0);
////                ps.executeUpdate();
////                server.Start.GetConfigValues();
////            } catch (SQLException ex) {
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:����˿�����½��֤��", qq));
////
////        }
////    }
////
////    private static void �رյ�½��֤(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            PreparedStatement ps1 = null;
////            ResultSet rs = null;
////            try {
////                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
////                ps1.setInt(1, 1);
////                rs = ps1.executeQuery();
////                if (rs.next()) {
////                    String sqlstr = " delete from configvalues where id =2040";
////                    ps1.executeUpdate(sqlstr);
////                }
////            } catch (SQLException ex) {
////            }
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
////                ps.setInt(1, 2040);
////                ps.setString(2, "��½��֤����");
////                ps.setInt(3, 1);
////                ps.executeUpdate();
////                server.Start.GetConfigValues();
////            } catch (SQLException ex) {
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:����˹رյ�½��֤��", qq));
////
////        }
////    }
////
////    private static void �ر�ȫ������(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            PreparedStatement ps1 = null;
////            ResultSet rs = null;
////            try {
////                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
////                ps1.setInt(1, 1);
////                rs = ps1.executeQuery();
////                if (rs.next()) {
////                    String sqlstr = " delete from configvalues where id =2014";
////                    ps1.executeUpdate(sqlstr);
////                }
////            } catch (SQLException ex) {
////            }
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
////                ps.setInt(1, 2014);
////                ps.setString(2, "ȫ����������");
////                ps.setInt(3, 1);
////                ps.executeUpdate();
////                server.Start.GetConfigValues();
////            } catch (SQLException ex) {
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:����˹ر�ȫ��������", qq));
////            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[����Ա��Ϣ]:����˹ر�ȫ��������").getBytes());
////        }
////    }
////
////    private static void ��ֹ��Ϸ����(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            PreparedStatement ps1 = null;
////            ResultSet rs = null;
////            try {
////                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
////                ps1.setInt(1, 1);
////                rs = ps1.executeQuery();
////                if (rs.next()) {
////                    String sqlstr = " delete from configvalues where id =2009";
////                    ps1.executeUpdate(sqlstr);
////                }
////            } catch (SQLException ex) {
////            }
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
////                ps.setInt(1, 2009);
////                ps.setString(2, "��Ϸ���ȿ���");
////                ps.setInt(3, 1);
////                ps.executeUpdate();
////                server.Start.GetConfigValues();
////            } catch (SQLException ex) {
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:��ֹ�������Ϸ�з������ȡ�", qq));
////        }
////    }
////
////    private static void ������Ϸ����(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            PreparedStatement ps1 = null;
////            ResultSet rs = null;
////            try {
////                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
////                ps1.setInt(1, 1);
////                rs = ps1.executeQuery();
////                if (rs.next()) {
////                    String sqlstr = " delete from configvalues where id =2009";
////                    ps1.executeUpdate(sqlstr);
////                }
////            } catch (SQLException ex) {
////            }
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
////                ps.setInt(1, 2009);
////                ps.setString(2, "��Ϸ���ȿ���");
////                ps.setInt(3, 0);
////                ps.executeUpdate();
////                server.Start.GetConfigValues();
////            } catch (SQLException ex) {
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�����������Ϸ�з������ȡ�", qq));
////        }
////    }
////
////    private static void ��ֹ�������(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            PreparedStatement ps1 = null;
////            ResultSet rs = null;
////            try {
////                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
////                ps1.setInt(1, 1);
////                rs = ps1.executeQuery();
////                if (rs.next()) {
////                    String sqlstr = " delete from configvalues where id =2024";
////                    ps1.executeUpdate(sqlstr);
////                }
////            } catch (SQLException ex) {
////            }
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
////                ps.setInt(1, 2024);
////                ps.setString(2, "������쿪��");
////                ps.setInt(3, 1);
////                ps.executeUpdate();
////                server.Start.GetConfigValues();
////
////            } catch (SQLException ex) {
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:��ֹ�������Ϸ�����졣", qq));
////        }
////    }
////
////    private static void �����������(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            PreparedStatement ps1 = null;
////            ResultSet rs = null;
////            try {
////                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
////                ps1.setInt(1, 1);
////                rs = ps1.executeQuery();
////                if (rs.next()) {
////                    String sqlstr = " delete from configvalues where id =2024";
////                    ps1.executeUpdate(sqlstr);
////                }
////            } catch (SQLException ex) {
////            }
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
////                ps.setInt(1, 2024);
////                ps.setString(2, "������쿪��");
////                ps.setInt(3, 0);
////                ps.executeUpdate();
////                server.Start.GetConfigValues();
////
////            } catch (SQLException ex) {
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�����������Ϸ�����졣", qq));
////        }
////    }
////
////    private static void ���������(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            try {
////
////                Start.instance.startServer();
////
////            } catch (InterruptedException ex) {
////                Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
////            } catch (UnsupportedEncodingException ex) {
////                Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
////            } catch (IOException ex) {
////                Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:����˿�ʼ�ͷŷ������ڴ档", qq));
////            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[����Ա��Ϣ]:����˿�ʼ�ͷŷ������ڴ档").getBytes());
////        }
////    }
////
////    private boolean ���ټ��2() {
////        boolean connect = false;
////        Runtime runtime = Runtime.getRuntime();
////        Process process;
////        String ��� = "";
////        String ���1 = "";
////        try {
////            process = runtime.exec("ping " + "" + �������� + "");
////            InputStream is = process.getInputStream();
////            InputStreamReader isr = new InputStreamReader(is);
////            BufferedReader br = new BufferedReader(isr);
////            String line = null;
////            StringBuffer sb = new StringBuffer();
////            while ((line = br.readLine()) != null) {
////                sb.append(line);
////            }
////            is.close();
////            isr.close();
////            br.close();
////
////            if (null != sb && !sb.toString().equals("")) {
////                String logString = "";
////                if (sb.toString().indexOf("TTL") > 0) {
////                    //JOptionPane.showMessageDialog(null, "�ӳ� " + sb.toString().indexOf("TTL") + "\r\n���������ٸ����ӳ� 10 ���޷�������");
////                    //��� = "�ӳ� " + sb.toString().indexOf("TTL") + "";
////                } else {
////                    JOptionPane.showMessageDialog(null, "�ӳ� " + sb.toString().indexOf("TTL") + "\r\n���������ٸ����ӳ� 10 ���޷�������");
////                    System.exit(0);
////                    return false;
////                }
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        return connect;
////    }
////
////    private static void ����״̬(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            /*int �ȶ� = MapleParty.��ɫ��½����;
////            String �ȶ���ʾ = "";
////            if (�ȶ� > 100) {
////                �ȶ���ʾ = "������";
////            } else if (�ȶ� > 70) {
////                �ȶ���ʾ = "������";
////            } else if (�ȶ� > 40) {
////                �ȶ���ʾ = "������";
////            } else if (�ȶ� > 20) {
////                �ȶ���ʾ = "������";
////            } else {
////                �ȶ���ʾ = "������";
////            }*/
////            long start = System.currentTimeMillis();
////            Runtime runtime = Runtime.getRuntime();
////            Process process;
////            try {
////                process = runtime.exec("ping " + "www.baidu.com");
////                InputStream is = process.getInputStream();
////                InputStreamReader isr = new InputStreamReader(is);
////                BufferedReader br = new BufferedReader(isr);
////                String line = null;
////                StringBuffer sb = new StringBuffer();
////                while ((line = br.readLine()) != null) {
////                    sb.append(line);
////                }
////                is.close();
////                isr.close();
////                br.close();
////                if (null != sb && !sb.toString().equals("")) {
////                    long now = System.currentTimeMillis() - start;
////                    long seconds = now / 1000;
////                    long ms = now % 1000;
////                    if (sb.toString().indexOf("TTL") > 0) {
////                        sendMsgToQQGroup(String.format("<" + �������� + ">״̬���\r\n"
////                                //+ "��ǰ�ȶ�: " + �ȶ���ʾ + "\r\n"
////                                + "��ǰ�汾: v" + �汾 + "\r\n"
////                                + "Ƶ������: " + ServerProperties.getProperty("ZEV.Count") + "\r\n"
////                                + "�����ӳ�: " + sb.toString().indexOf("TTL") + "\r\n"
////                                + "��Ӧ�ٶ�: " + seconds + "." + ms + "\r\n"
////                                // + "���Ӵ���: " + MapleParty.��ҵ�½���� + " ��\r\n"
////                                + "�˴�����: " + MapleParty.���������ʱ�� + " ����\r\n"
////                                + "���鱶��: " + Integer.parseInt(ServerProperties.getProperty("ZEV.Exp")) + " ��\r\n"
////                                + "��ұ���: " + Integer.parseInt(ServerProperties.getProperty("ZEV.Meso")) + " ��\r\n"
////                                + "���ﱶ��: " + Integer.parseInt(ServerProperties.getProperty("ZEV.Drop")) + " ��"
////                                + "", qq));
////                    }
////                }
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////    }
////    //+ "����ʱ��: " + CurrentReadable_Time() + "\r\n"
////    //+ "����ʱ��: " + getWebsiteDatetime(��ȡ����ʱ��) + "\r\n"MapleParty.��ɫ��½����
////
////    private static String getWebsiteDatetime(String webUrl) {
////        try {
////            URL url = new URL(webUrl);// ȡ����Դ����
////            URLConnection uc = url.openConnection();// �������Ӷ���
////            uc.connect();// ��������
////            long ld = uc.getDate();// ��ȡ��վ����ʱ��
////            Date date = new Date(ld);// ת��Ϊ��׼ʱ�����
////            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// �������ʱ��
////            return sdf.format(date);
////        } catch (MalformedURLException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        return null;
////    }
////
////    private static void �ͷ��ڴ�(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            System.gc();
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:����˿�ʼ�ͷŷ������ڴ档", qq));
////            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[����Ա��Ϣ]:����˿�ʼ�ͷŷ������ڴ档").getBytes());
////        }
////    }
////    private static ScheduledFuture<?> ts = null;
////    private int minutesLeft = 0;
////    private static Thread t = null;
////
////    private void �رշ����(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
//////��ʼ�����һ�ι�Ӷ
////            int p = 0;
////            for (handling.channel.ChannelServer cserv : handling.channel.ChannelServer.getAllInstances()) {
////                p++;
////                cserv.closeAllMerchant();
////            }
////            //��ʼ����ڶ��ι�Ӷ
////            for (handling.channel.ChannelServer cserv : handling.channel.ChannelServer.getAllInstances()) {
////                p++;
////                cserv.closeAllMerchant();
////            }
////            //��ʼ��������ι�Ӷ
////            for (handling.channel.ChannelServer cserv : handling.channel.ChannelServer.getAllInstances()) {
////                p++;
////                cserv.closeAllMerchant();
////            }
////            //��ʼ�����������
////            int pp = 0;
////            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
////                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
////                    pp++;
////                    chr.saveToDB(true, true);
////                }
////            }
////            //��ʼ�ڶ��α����������
////            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
////                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
////                    pp++;
////                    chr.saveToDB(true, true);
////                }
////            }
////            //��ʼ�����α����������
////            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
////                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
////                    pp++;
////                    chr.saveToDB(true, true);
////                }
////            }
////            //�ж���������
////            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
////                cserv.getPlayerStorage().��������(true);
////            }
////            //�ж���������
////            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
////                cserv.getPlayerStorage().��������(true);
////            }
////            //��ʼ�رշ����
////            try {
////                minutesLeft = 0;
////                if (ts == null && (t == null || !t.isAlive())) {
////                    t = new Thread(ShutdownServer.getInstance());
////                    ts = Timer.EventTimer.getInstance().register(new Runnable() {
////
////                        @Override
////                        public void run() {
////                            if (minutesLeft == 0) {
////                                ShutdownServer.getInstance();
////                                t.start();
////                                ts.cancel(false);
////                                return;
////                            }
////                            minutesLeft--;
////                        }
////                    }, 60000);
////                }
////            } catch (Exception e) {
////
////            }
////        }
////    }
////
////    private static void �鿴�۷��(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            String ��ߵȼ���� = NPCConversationManager.��ȡ��ߵȼ��������();
////            int ��ߵȼ� = NPCConversationManager.��ȡ�����ҵȼ�();
////            String ���������� = NPCConversationManager.��ȡ��������������();
////            int ������� = NPCConversationManager.��ȡ����������();
////            String ���������� = NPCConversationManager.��ȡ��������������();
////            int ������� = NPCConversationManager.��ȡ����������();
////            String ����������� = NPCConversationManager.��ȡ���������������();
////            String ��ǿ���� = NPCConversationManager.��ȡ��ǿ��������();
////            sendMsgToQQGroup("<" + �������� + "�۷��>\r\n"
////                    + "��ߵȼ���" + ��ߵȼ���� + "\r\n"
////                    + "���������" + ���������� + "\r\n"
////                    + "��ǿ���壺" + ��ǿ���� + "\r\n"
////                    + "����ʱ��(��)��" + ����������� + "\r\n"
////                    + "����ʱ��(��)��" + ����������
////            );
////        }
////    }
////
////    private static void ���ݴ浵(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:����˿�ʼ����������ݡ�", qq));
////            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[����Ա��Ϣ]:����˿�ʼ����������ݡ�").getBytes());
////            int ppl = 0;
////            try {
////                for (ChannelServer cserv : ChannelServer.getAllInstances()) {
////                    for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
////                        if (chr == null) {
////                            continue;
////                        }
////                        ppl++;
////                        chr.saveToDB(false, false);
////                    }
////                }
////            } catch (Exception e) {
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:����˱������������ɡ�", qq));
////            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[����Ա��Ϣ]:����˱������������ɡ�").getBytes());
////        }
////    }
////
////    private static void �ر��۷�㲥(final String qq) {
////
////        //String ���������� = Integer.parseInt(server.Start.ConfigValuesMap.get("����������QQ"));
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            PreparedStatement ps1 = null;
////            ResultSet rs = null;
////            try {
////                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
////                ps1.setInt(1, 1);
////                rs = ps1.executeQuery();
////                if (rs.next()) {
////                    String sqlstr = " delete from configvalues where id =2035";
////                    ps1.executeUpdate(sqlstr);
////                }
////            } catch (SQLException ex) {
////            }
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
////                ps.setInt(1, 2035);
////                ps.setString(2, "�������۷�㲥����");
////                ps.setInt(3, 1);
////                ps.executeUpdate();
////                server.Start.GetConfigValues();
////                sendMsgToQQGroup(String.format("<" + �������� + ">������:�۷�㲥�رճɹ���", qq));
////            } catch (SQLException ex) {
////            }
////        }
////    }
////
////    private static void �����۷�㲥(final String qq) {
////        // ���ж�QQ�˺��Ƿ��Ѿ�ע��
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            PreparedStatement ps1 = null;
////            ResultSet rs = null;
////            try {
////                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
////                ps1.setInt(1, 1);
////                rs = ps1.executeQuery();
////                if (rs.next()) {
////                    String sqlstr = " delete from configvalues where id =2035";
////                    ps1.executeUpdate(sqlstr);
////                }
////            } catch (SQLException ex) {
////            }
////            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
////                ps.setInt(1, 2035);
////                ps.setString(2, "�������۷�㲥����");
////                ps.setInt(3, 0);
////                ps.executeUpdate();
////                server.Start.GetConfigValues();
////                sendMsgToQQGroup(String.format("<" + �������� + ">������:�۷�㲥�����ɹ���", qq));
////            } catch (SQLException ex) {
////            }
////        }
////    }
////
////    private static void ע���˺�(final String qq) {
////
////        if (MapleParty.�����˴��� > 0) {
////            return;
////        }
////        ע������� itemjc = new ע�������();
////        String items1 = itemjc.getQQ();
////        if (!items1.contains("," + qq + ",")) {
////            try {
////                if (Start.CloudBlacklist.containsKey(qq)) {
////                    sendMsgToQQGroup(String.format("<<" + �������� + "�Ʒ�ϵͳ>>"
////                            + "����⵽����ʹ�÷Ƿ�����������ƻ���Ϸƽ��Ĳ�����¼����������˺��Ǹ������Ⱥ�ĳ�Ա����Ĵ˴�ע���Ѿ������أ��ѱ���ֹ��½������ע�᱾��һ�з��������������ʣ�����ϵ����Ա��", qq));
////                    return;
////                }
////            } catch (Exception ex) {
////                System.err.println("���غ�������ҳ���" + ex.getMessage());
////            }
////        }
////        ע������� itemjc2 = new ע�������();
////        String items2 = itemjc2.getQQ();
////        if (items2.contains("," + qq + ",")) {
////            sendMsgToQQGroup(String.format("<<" + �������� + "������ϵͳ>>"
////                    + "����˺��ѱ������������ʣ�����ϵ����Ա��", qq));
////            return;
////        }
////        // ���ж�QQ�˺��Ƿ��Ѿ�ע��
////        Boolean isExists = AutoRegister.getAccountExists(qq);
////        if (isExists) {
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:���QQ %s �Ѿ�ע�����,һ��QQֻ��ע��һ���˺ţ�����ϧ�˺š�", qq));
////            return;
////        }
////
////        try {
////            Connection con = DatabaseConnection.getConnection();
////            PreparedStatement ps = con.prepareStatement("INSERT INTO accounts (name, password, email, birthday, qq) VALUES (?, ?, ?, ?, ?)");
////
////            try {
////                ps.setString(1, qq);
////                ps.setString(2, LoginCrypto.hexSha1(qq));
////                ps.setString(3, String.format("%s@qq.com", qq));
////                ps.setString(4, "2018-11-11");
////                ps.setString(5, qq);
////                ps.executeUpdate();
////            } finally {
////                ps.close();
////            }
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:��ϲ�㣬QQ %s ע��ɹ�����˽���ң�ʹ�á�*�޸����롱����������롣", qq));
////            sendMsg("<" + �������� + ">������:��ã��뽫��Ҫ���õ�������[*�޸�����]<�ո�>[����]��ʽ�����ң��ҽ�Ϊ���޸����롣", qq);
////            //sendMsgToAdminQQ(String.format("<" + �������� + ">������:QQ %s ע��ɹ���", qq));
////        } catch (SQLException ex) {
////            System.out.println(ex);
////            sendMsgToQQGroup(String.format("<" + �������� + ">������:�Բ���QQ %s ע��ʧ�ܣ�", qq));
////            //sendMsgToAdminQQ(String.format("<" + �������� + ">������:QQ %s ע��ʧ�ܡ�\n�쳣��%s", qq, ex.getMessage()));
////        }
////    }
////
////    private static final String[] ��ʾ�� = {
////        "���ǲ���ɵ�����˺�����û�н�ɫҪ����ô�飿 ",
////        "����ɵ������ɵ���ҿ�������ɵ���Ƚ���Ϸ������ɫ����Ȼ����ô�飿 ",
////        "û��ɫ��ô�飿",
////        "���й���ƨû�н�ɫҲҪ���Ҳ�ѯ��ɫ�� ",
////        "�����������������ô��ѯ�ɣ�û��ɫ������ѡ� ",
////        "��������Ѿ�Ƿ�ѣ��޷���ѯ��ɫ�� "
////    };
////
////    private static void ��ѯ��ɫ(final String qq) {
////        if (MapleParty.�����˴��� > 0) {
////            return;
////        }
////        StringBuilder sb = new StringBuilder();
////
////        int count = 0;
////        try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT c.`name`,c.`id`, c.`level`, c.`str`, c.`dex`, c.`luk`, c.`int`, c.`job`, c.`exp`, c.`fame`, c.`maxhp`, c.`maxmp`, c.`pvpkills`, c.`pvpdeaths`, c.`todayOnlineTime`, c.`totalOnlineTime` FROM accounts as a, characters as c WHERE a.id = c.accountid AND a.qq = ?")) {
////            ps.setString(1, qq);
////            try (ResultSet rs = ps.executeQuery()) {
////                while (rs.next()) {
////                    sb.append(String.format(""
////                            + "<" + �������� + ">\r\n"
////                            + "��ɫ: %s\n"
////                            + " id : %s\n"
////                            + "ְҵ: %s\n"
////                            + "�ȼ�: %s\n"
////                            + "����: %s\n"
////                            + "����: %s\n"
////                            + "����: %s\n"
////                            + "����: %s\n"
////                            + "����: %s\n"
////                            + "����: %s\n"
////                            + "����: %s \n"
////                            + "������: %s\n"
////                            + "����ֵ: %s\n"
////                            + "ħ��ֵ: %s\n"
////                            + "��������: %s \n"
////                            + "�ܹ�����: %s \n", rs.getString("name"),
////                            rs.getString("id"),
////                            getJobNameById(rs.getInt("job")),
////                            rs.getString("level"),
////                            rs.getString("exp"),
////                            rs.getString("fame"),
////                            rs.getString("str"),
////                            rs.getString("dex"),
////                            rs.getString("luk"),
////                            rs.getString("int"),
////                            rs.getString("pvpkills"),
////                            rs.getString("pvpdeaths"),
////                            rs.getString("maxhp"),
////                            rs.getString("maxmp"),
////                            rs.getString("todayOnlineTime"),
////                            rs.getString("totalOnlineTime")));
////                    count++;
////                }
////            }
////        } catch (SQLException ex) {
////            System.out.println(ex);
////        }
////        /*if (count == 0) {
////            sendMsgToQQGroup("" + ��ʾ��[Randomizer.nextInt(��ʾ��.length)]);
////        }*/
////        //String info = String.format("�˺�: %s ����%s����ɫ\n----------------------------\n", qq, count);
////        /*String info = String.format("�˺�: %s ����%s����ɫ\n----------------------------\n", qq, count);
////        sb.insert(0, info);*/
////        sendMsgToQQGroup(sb.toString());
////    }
////
////    private static void �������Ծ�(final String qq, final String newPassword, final String token) {
////        try {
////            Connection con = DatabaseConnection.getConnection();
////            PreparedStatement pss = con.prepareStatement("UPDATE `accounts` SET `hair` = ?, `salt` = ? WHERE qq = ?");
////            try {
////                final String newSalt = LoginCrypto.makeSalt();
////                pss.setString(1, LoginCrypto.makeSaltedSha512Hash(newPassword, newSalt));
////                pss.setInt(2, 30000);
////                pss.setString(3, qq);
////                int res = pss.executeUpdate();
////                if (res > 0) {
////                    sendMsg("<" + �������� + ">������:��ϲ�㣬�����޸ĳɹ�������Ϸ��Ҳ������Сz�޸�����Ŷ��", token);
////                } else {
////                    sendMsg("<" + �������� + ">������:û���ҵ����QQ��Ӧ���˺ţ������޸�ʧ�ܣ�", token);
////                }
////            } finally {
////                pss.close();
////            }
////        } catch (SQLException e) {
////            System.err.println("�޸��������" + e);
////        }
////    }
////
////    private static void �޸�����(final String qq, final String newPassword, final String token) {
////        if (MapleParty.�����˴��� > 0) {
////            return;
////        }
////        /* if (server.Start.ConfigValuesMap.get("�������޸����뿪��") > 0) {
////            sendMsg("<" + �������� + ">������:����Ա�Ӻ�̨�ر����޸�����Ĺ��ܡ�", token);
////            return;
////        }*/
////        if (!newPassword.matches("^[0-9A-Za-z]{6,10}$")) {
////            sendMsg("<" + �������� + ">������:�����벻�ϸ񣬱�����6-10λ���ֻ���ĸ��ɡ�", token);
////            return;
////        }
////
////        try {
////            Connection con = DatabaseConnection.getConnection();
////            PreparedStatement pss = con.prepareStatement("UPDATE `accounts` SET `password` = ?, `salt` = ? WHERE qq = ?");
////            try {
////                final String newSalt = LoginCrypto.makeSalt();
////                pss.setString(1, LoginCrypto.makeSaltedSha512Hash(newPassword, newSalt));
////                pss.setString(2, newSalt);
////                pss.setString(3, qq);
////                int res = pss.executeUpdate();
////                if (res > 0) {
////                    sendMsg("<" + �������� + ">������:��ϲ�㣬�����޸ĳɹ�������Ϸ��Ҳ������Сz�޸�����Ŷ��", token);
////                    FileoutputUtil.logToFile("��������Ϣ��¼/" + CurrentReadable_Date() + "/�޸�����ָ��[�ɹ�]/" + qq + ".txt", "[ʱ�䣺" + CurrentReadable_Time() + "]�˺ţ�[" + qq + "]�޸����룺[" + newPassword + "]\r\n");
////                } else {
////                    sendMsg("<" + �������� + ">������:û���ҵ����QQ��Ӧ���˺ţ������޸�ʧ�ܣ�", token);
////                    FileoutputUtil.logToFile("��������Ϣ��¼/" + CurrentReadable_Date() + "/�޸�����ָ��[ʧ��]/" + qq + ".txt", "[ʱ�䣺" + CurrentReadable_Time() + "]�˺ţ�[" + qq + "] �޸�ʧ�ܣ�δע���˺��޸�����\r\n");
////                }
////            } finally {
////                pss.close();
////            }
////        } catch (SQLException e) {
////            System.err.println("�޸��������" + e);
////        }
////    }
////
////    private static void ����˱���() {
////        if (MapleParty.�����˴��� > 0) {
////            return;
////        }
////        if ("��".equals(ServerProperties.getProperty("ZEV.ZEV"))) {
////            sendMsgToQQGroup(""
////                    + "<" + �������� + ">"
////                    + "\r\n[��������]:"
////                    + "\r\n���鱶�� :" + Integer.parseInt(ServerProperties.getProperty("ZEV.Exp"))
////                    + "\r\n��ұ��� :" + Integer.parseInt(ServerProperties.getProperty("ZEV.Meso"))
////                    + "\r\n���ﱶ�� :" + Integer.parseInt(ServerProperties.getProperty("ZEV.Drop"))
////                    + "\r\n[��������]:"
////                    + "\r\n���鱶�� :" + Integer.parseInt(ServerProperties.getProperty("ZEV.Exp2"))
////                    + "\r\n��ұ��� :" + Integer.parseInt(ServerProperties.getProperty("ZEV.Meso2"))
////                    + "\r\n���ﱶ�� :" + Integer.parseInt(ServerProperties.getProperty("ZEV.Drop2"))
////            );
////        } else {
////            sendMsgToQQGroup(""
////                    + "<" + �������� + ">"
////                    + "\r\n���鱶�� :" + Integer.parseInt(ServerProperties.getProperty("ZEV.Exp"))
////                    + "\r\n��ұ��� :" + Integer.parseInt(ServerProperties.getProperty("ZEV.Meso"))
////                    + "\r\n���ﱶ�� :" + Integer.parseInt(ServerProperties.getProperty("ZEV.Drop"))
////            );
////        }
////    }
////    private List<RespawnInfo> respawnInfolist = new ArrayList<>();
////
////    public void reloadSpawn() {
////
////        respawnInfolist.clear();
////        //respawnInfolist.add(new RespawnInfo(Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�ǵ�ͼ7")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�ǹ���7")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥������7")), new Point(Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��x����7")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��y����7"))), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��Ƶ��7")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥����7")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�Ǿ���7")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥������7")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��Сʱ7")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�Ƿ���7"))));
////        //respawnInfolist.add(new RespawnInfo(Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�ǵ�ͼ6")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�ǹ���6")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥������6")), new Point(Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��x����6")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��y����6"))), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��Ƶ��6")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥����6")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�Ǿ���6")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥������6")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��Сʱ6")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�Ƿ���6"))));
////        respawnInfolist.add(new RespawnInfo(Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�ǵ�ͼ5")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�ǹ���5")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥������5")), new Point(Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��x����5")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��y����5"))), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��Ƶ��5")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥����5")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�Ǿ���5")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥������5")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��Сʱ5")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�Ƿ���5"))));
////        respawnInfolist.add(new RespawnInfo(Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�ǵ�ͼ4")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�ǹ���4")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥������4")), new Point(Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��x����4")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��y����4"))), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��Ƶ��4")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥����4")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�Ǿ���4")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥������4")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��Сʱ4")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�Ƿ���4"))));
////        respawnInfolist.add(new RespawnInfo(Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�ǵ�ͼ2")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�ǹ���2")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥������2")), new Point(Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��x����2")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��y����2"))), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��Ƶ��2")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥����2")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�Ǿ���2")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥������2")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��Сʱ2")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�Ƿ���2"))));
////        respawnInfolist.add(new RespawnInfo(Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�ǵ�ͼ1")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�ǹ���1")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥������1")), new Point(Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��x����1")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��y����1"))), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��Ƶ��1")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥����1")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�Ǿ���1")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥������1")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��Сʱ1")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�Ƿ���1"))));
////        respawnInfolist.add(new RespawnInfo(Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�ǵ�ͼ3")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�ǹ���3")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥������3")), new Point(Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��x����3")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��y����3"))), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��Ƶ��3")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥����3")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�Ǿ���3")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥������3")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥��Сʱ3")), Integer.parseInt(ServerProperties.getProperty("ZEV.���﹥�Ƿ���3"))));
////
////        //respawnInfolist.add(new RespawnInfo(��ͼ, ����, ����, new Point(����X, ����Y), Ƶ��, ��������, ���ﾭ��, ����, ʱ, ��));
////    }
////
////    public void ���﹥��(final String qq) {
////        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
////            if (MapleParty.�����˴��� > 0) {
////                return;
////            }
////            Calendar calendar = Calendar.getInstance();
////            for (RespawnInfo respawnInfo : respawnInfolist) {
////
////                //ChannelServer channelServer= ChannelServer.getInstance(Randomizer.nextInt(ChannelServer.getChannelCount())+1);
////                ChannelServer channelServer = ChannelServer.getInstance(respawnInfo.getChannelId());
////                if (channelServer == null) {
////                    System.out.println("spawnInTime:�����ڵ�Ƶ��,ID" + respawnInfo.getChannelId());
////                    continue;
////                }
////                MapleMap mapleMap = channelServer.getMapFactory().getMap(respawnInfo.getMapId());
////                if (mapleMap == null) {
////                    System.out.println("spawnInTime:�����ڵĵ�ͼ,ID" + respawnInfo.getMapId());
////                    continue;
////                }
////                MapleMonster mobName = MapleLifeFactory.getMonster(respawnInfo.getMobId());
////                if (mobName == null) {
////                    System.out.println("spawnInTime:�����ڵĹ���,ID" + respawnInfo.getMobId());
////                    continue;
////                }
////                for (int i = 0; i < respawnInfo.getMobCount(); i++) {
////                    MapleMonster mapleMonster = MapleLifeFactory.getMonster(respawnInfo.getMobId());
////                    if (respawnInfo.getHp() > 0) {
////                        mapleMonster.setHp(respawnInfo.getHp());
////                    }
////                    if (respawnInfo.getExp() > 0) {
////                        mapleMonster.setOverrideStats(new OverrideMonsterStats(mapleMonster.getHp(), mapleMonster.getMp(), respawnInfo.getExp()));
////                    }
////                    mapleMonster.setPosition(respawnInfo.getSpawnPoint());
////                    mapleMap.spawnMonster(mapleMonster, -2);
////                }
////            }
////        }
////    }
////}
////*/