/*
QQ������
 */
package gui;

import static a.�������ݿ�.��ѯQQ���Ƿ��з���˺�;
import static a.�÷���ȫ.ȡ�˺Ű󶨵�QQ;
import static a.�÷���ȫ.��ɫIDȡ��ɫ��;
import static a.�÷���ȫ.��ɫIDȡ�˺�ID;
import static a.�÷���ȫ.�˺�IDȡ�˺�;
import abc.ע�������;
import abc.ע�������;
import client.LoginCrypto;
import client.MapleCharacter;
import client.inventory.Equip;
import client.inventory.MapleInventoryType;
import constants.GameConstants;
import database.DatabaseConnection;
import database1.DatabaseConnection1;
import static gui.QQͨ��.ͨ��;
import static gui.Start.CashShopServer;
import static gui.����̨.������.�˺�IDȡ��QQ;
import gui.����̨.����̨1��;
import handling.channel.ChannelServer;
import handling.login.handler.AutoRegister;
import handling.world.MapleParty;
import handling.world.World;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static scripting.NPCConversationManager.������е�ȯ;
import static scripting.NPCConversationManager.������н��;
import static scripting.NPCConversationManager.��ɫ����ȡID;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.ServerProperties;
import static scripting.NPCConversationManager.�˺�ȡ��QQ;
import static scripting.NPCConversationManager.���п�����;
import static scripting.NPCConversationManager.���е�ȯ���;
import static scripting.NPCConversationManager.�����˺�;
import static scripting.NPCConversationManager.�����˺�����;
import static scripting.NPCConversationManager.���н�����;
import server.maps.MapleMap;
import tools.FileoutputUtil;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;

/**
 *
 * @author ʱ���ȷ�
 */
public class QQMsgServer implements Runnable {

    public static void main(String args[]) {
        new Thread(QQMsgServer.getInstance()).start();
         System.err.println("QQ");
    }
    private static final int INPORT = 9001;
    private static final int PeerPort = 9000;

    private byte[] buf = new byte[1024];
    private DatagramPacket dp = new DatagramPacket(buf, buf.length);
    private static DatagramSocket socket;
    private static final QQMsgServer instance = new QQMsgServer();

    public static QQMsgServer getInstance() {
        return instance;

    }
    public static int ��ȴ = 0;
    public static int ��ȴʱ�� = 5;

    private QQMsgServer() {
        try {
            socket = new DatagramSocket(INPORT);
        } catch (SocketException e) {
            System.err.println("�޷��������˿ڱ�ռ��");
            System.exit(1);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                socket.receive(dp);
                // ���յ�����QQ�����˵���Ϣ
                String rcvd = new String(dp.getData(), 0, dp.getLength());
                //System.out.println("QQ: " + rcvd);
                String msgArr[] = rcvd.split("_");
                String msgType = msgArr[0];
                if (msgType.equals("P")) { // ˽��
                    int index = msgType.length() + 1;
                    String fromQQ = msgArr[1];
                    index += fromQQ.length() + 1;
                    String token = msgArr[2];
                    index += token.length() + 1;
                    String msg[] = rcvd.substring(index).trim().split("\\s+");
                    switch (msg[0]) {
                        case "*��ѯ�������":
                            if (msg.length > 2) {
                                �������(fromQQ, msg[1], msg[2], token);
                            } else {
                                sendMsg("ָ���ʽ����ȷ\r\n*��ѯ�������<�ո�>[����]<�ո�>[����]��", token);
                            }
                            break;
                        case "*ת�˽��":
                        case "*ת�ʽ��":
                            if (msg.length > 2) {
                                ת�˽��(fromQQ, msg[1], msg[2], msg[3], msg[4], token);
                            } else {
                                sendMsg("ָ���ʽ����ȷ\r\n*ת�˽��<�ո�>[����]<�ո�>[����]<�ո�>[ת�˿���]<�ո�>[���]��", token);
                            }
                            break;
                        case "*ת�˵�ȯ":
                        case "*ת�ʵ�ȯ":
                            if (msg.length > 2) {
                                ת�˵�ȯ(fromQQ, msg[1], msg[2], msg[3], msg[4], token);
                            } else {
                                sendMsg("ָ���ʽ����ȷ\r\n*ת�˵�ȯ<�ո�>[����]<�ո�>[����]<�ո�>[ת�˿���]<�ո�>[���]��", token);
                            }
                            break;
                        case "*ָ���ȫ":
                        case "*����":
                        case "*�˵�":
                            ָ���ȫ(fromQQ, token);
                            break;
                        case "*ע���˺�":
                        case "*ע���ʺ�":
                            if (msg.length > 1) {
                                ע���˺�(fromQQ, msg[1], token);
                            } else {
                                sendMsg("ָ���ʽ����ȷ\r\n*ע���˺�<�ո�>[�˺�]��", token);
                            }
                            break;
                        case "*�ҵ��˺�":
                        case "*�ҵ��ʺ�":
                            ��ѯ�˺�(fromQQ, token);
                            break;
                        case "*��ѯ�˺�":
                        case "*��ѯ�ʺ�":
                            if (msg.length > 1) {
                                ��ѯ�˺�2(fromQQ, msg[1], token);
                            } else {
                                sendMsg("ָ���ʽ����ȷ\r\n*��ѯ�˺�<�ո�>[�˺�]��", token);
                            }
                            break;
                        case "*�޸�����":
                        case "*�޸�����":
                            if (msg.length > 2) {
                                �޸�����(fromQQ, msg[1], msg[2], token);
                            } else {
                                sendMsg("ָ���ʽ����ȷ\r\n*�޸�����<�ո�>[�˺�]<�ո�>[����]��", token);
                            }
                            break;

                        case "*�̳ǳ���֪ͨ":
                            if (msg.length > 1) {
                                �̳ǳ���֪ͨ(fromQQ, msg[1], token);
                            } else {
                                sendMsg("ָ���ʽ����ȷ��", token);
                            }
                            break;
                        case "*ȫ������":
                            if (msg.length > 1) {
                                ȫ������(fromQQ, msg[1], token);
                            } else {
                                sendMsg("ָ���ʽ����ȷ��", token);
                            }
                            break;
                        case "*������״̬":
                            if (msg.length > 1) {
                                ������״̬(fromQQ, msg[1], token);
                            } else {
                                sendMsg("ָ���ʽ����ȷ��", token);
                            }
                            break;
                        case "*�⿨����":
                            if (msg.length > 1) {
                                �⿨����(fromQQ, msg[1], token);
                            } else {
                                sendMsg("ָ���ʽ����ȷ��", token);
                            }
                            break;
                        case "*ɾ����ɫ":
                            if (msg.length > 1) {
                                ɾ����ɫ(fromQQ, msg[1], token);
                            } else {
                                sendMsg("ָ���ʽ����ȷ��", token);
                            }
                            break;
                        case "*�������ݿ�":
                            �������ݿ�(fromQQ, token);
                            break;
                        case "*����":
                        case "*���߽�ɫ":
                        case "*�������":
                            �������(fromQQ, token);
                            break;
                        case "*���߹�Ӷ":
                        case "*��������":
                        case "*��Ӷ����":
                            ���߹�Ӷ(fromQQ, token);
                            break;
                        case "*���":
                            ���(fromQQ, msg[1], token);
                            break;

                        case "*�⿨����":
                            if (msg.length > 1) {
                                �⿨����(fromQQ, msg[1], token);
                            } else {
                                sendMsg("ָ���ʽ����ȷ��", token);
                            }
                            break;
                        case "*ɾ���쳣��Ʒ":
                            if (msg.length > 1) {
                                ɾ���쳣��Ʒ(fromQQ, msg[1], token);
                            } else {
                                sendMsg("ָ���ʽ����ȷ��", token);
                            }
                            break;
                        case "*�⿨��ͼ1":
                            if (msg.length > 1) {
                                �⿨��ͼ1(fromQQ, msg[1], token);
                            } else {
                                sendMsg("ָ���ʽ����ȷ��", token);
                            }
                            break;
                        case "*�⿨��ͼ2":
                            if (msg.length > 1) {
                                �⿨��ͼ2(fromQQ, msg[1], token);
                            } else {
                                sendMsg("ָ���ʽ����ȷ��", token);
                            }
                            break;
                        case "*����":
                            if (msg.length > 2) {
                                ����(fromQQ, msg[1], msg[2], token);
                            } else {
                                sendMsg("ָ���ʽ��[ָ��]<�ո�>[����]<�ո�>[����ʱ��]��", token);
                            }
                            break;
                        case "*���ʻ":
                            if (msg.length > 2) {
                                ���ʻ(fromQQ, msg[1], msg[2], token);
                            } else {
                                sendMsg("ָ���ʽ��[ָ��]<�ո�>[����]<�ո�>[����ʱ��]��", token);
                            }
                            break;
                        case "*��һ":
                            if (msg.length > 2) {
                                ��һ(fromQQ, msg[1], msg[2], token);
                            } else {
                                sendMsg("ָ���ʽ��[ָ��]<�ո�>[����]<�ո�>[����ʱ��]��", token);
                            }
                            break;
                        case "*������̨":
                            if (msg.length > 2) {
                                ������̨(fromQQ, token);
                            }
                            break;
                        case "*��ȫ������ȯ":
                            if (msg.length > 1) {
                                ��ȫ������ȯ(fromQQ, msg[1], token);
                            } else {
                                sendMsg("ָ���ʽ��[ָ��]<�ո�>[����]��", token);
                            }
                            break;
                        case "*��ȫ��������":
                            if (msg.length > 1) {
                                ��ȫ��������(fromQQ, msg[1], token);
                            } else {
                                sendMsg("ָ���ʽ��[ָ��]<�ո�>[����]��", token);
                            }
                            break;
                        case "*��ȫ�������":
                            if (msg.length > 1) {
                                ��ȫ�������(fromQQ, msg[1], token);
                            } else {
                                sendMsg("ָ���ʽ��[ָ��]<�ո�>[����]��", token);
                            }
                            break;
                        case "*��ȫ������Ʒ":
                            if (msg.length > 2) {
                                ��ȫ������Ʒ(fromQQ, msg[1], msg[2], token);
                            } else {
                                sendMsg("ָ���ʽ��[ָ��]<�ո�>[��Ʒ����]<�ո�>[����]��", token);
                            }
                            break;
                        case "*¼��":
                            if (msg.length > 2) {
                                ¼��(fromQQ, msg[1], msg[2], token);
                            } else {
                                sendMsg("ָ���ʽ��[ָ��]<�ո�>[��Ŀ]<�ո�>[O/X]��", token);
                            }
                            break;
                        default: // ��������
                            break;
                    }
                }
//                else if (msgType.equals("G")) { // Ⱥ��
//                    int index = msgType.length() + 1;
//                    String fromGroup = msgArr[1];
//                    index += fromGroup.length() + 1;
//                    String fromQQ = msgArr[2];
//                    index += fromQQ.length() + 1;
//                    String nickName = msgArr[3];
//                    index += nickName.length() + 1;
//                    String msg = rcvd.substring(index).trim();
//                    switch (msg) {
//                        default:
//                            break;
//                    }
//                }
            }
        } catch (SocketException e) {
            System.err.println("Can't open socket");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("UdpHost init error");
            e.printStackTrace();
        }
    }

    public static void sendMsg(final String msg, final String token) {
        try {
            String data = String.format("P_%s_%s", token, msg);
            byte[] buf = data.getBytes();
            //System.out.println("[->qq] : " + new String(buf));
            DatagramPacket echo = new DatagramPacket(buf, buf.length, InetAddress.getLoopbackAddress(), PeerPort);
            socket.send(echo);
        } catch (IOException e) {
            System.err.println("sendMsgToQQ error");
        }
    }

    public static void sendMsgToAdminQQ(final String msg) {
        try {
            String data = "A_" + msg;
            byte[] buf = data.getBytes();
            //System.out.println("[->admin qq] : " + new String(buf));
            DatagramPacket echo = new DatagramPacket(buf, buf.length, InetAddress.getLoopbackAddress(), PeerPort);
            socket.send(echo);
        } catch (IOException e) {
            System.err.println("sendMsgToAdminQQ error");
        }
    }

    public static void sendMsgToQQGroup(final String msg) {
        try {
            String data = "G_" + msg;
            byte[] buf = data.getBytes();
            DatagramPacket echo = new DatagramPacket(buf, buf.length, InetAddress.getLoopbackAddress(), PeerPort);
            socket.send(echo);
        } catch (IOException e) {
            System.err.println("sendMsgToQQGroup error");
        }

    }

    private static int sendToOnlinePlayer(final String fromQQ, final String msg) {

        int count = 0;
        if (MapleParty.�����˴��� > 0) {
            return count;
        }
        if (MapleParty.��Ϣͬ�� > 0) {
            return count;
        }
        for (ChannelServer chl : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : chl.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (gui.Start.������Ϣ����.get("" + chr.getName() + "10") != null) {
                    if (gui.Start.������Ϣ����.get("" + chr.getName() + "10") == 0) {
                        chr.dropMessage(String.format("[Ⱥ][%s] : %s", fromQQ, msg));
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static void �⿨����(final String qq, final String account, final String token) {
        System.out.println("[������]" + CurrentReadable_Time() + " : QQ��" + qq + "���⿨���� �� " + account);
        if (��ȴ > 0) {
            sendMsg("�����˷�æ�����Ժ����ԡ�", token);
            return;
        } else {
            ��ȴ += 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * ��ȴʱ��);
                        ��ȴ = 0;
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
        boolean result = account.matches("[0-9]+");
        if (!result) {
            sendMsg("����������ID��", token);
            return;
        }
        Boolean isExists = AutoRegister.�жϽ�ɫID�Ƿ����(account);
        if (!isExists) {
            sendMsg("������Ľ�ɫID�����ڡ�", token);
            return;
        }
        if (!ServerProperties.getProperty("ZEV.QQ1").equals(qq) || !ServerProperties.getProperty("ZEV.QQ2").equals(qq) || !"71447500".equals(qq)) {
            if (!�˺�ȡ��QQ(�˺�IDȡ�˺�(��ɫIDȡ�˺�ID(account))).equals(token)) {
                sendMsg("��Ҫ�⿨���͵Ľ�ɫ����������ġ�", token);
                return;
            }
        }
        if (World.Find.findChannel(��ɫIDȡ��ɫ��(account)) > 0) {
            sendMsg("��Ľ�ɫĿǰ���ߣ����ý�ɫ���ߺ���ʹ�á�", token);
            return;
        }
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characters SET hair = ? WHERE id = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
            ps1.setInt(1, Integer.parseInt(account));
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                sqlString1 = "update characters set hair='30000' where id='" + Integer.parseInt(account) + "';";
                PreparedStatement hair = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                hair.executeUpdate(sqlString1);
                ps.close();
                ps1.close();
                sendMsg("��ɫ���ͽ⿨�ɹ�����", token);
            }
        } catch (SQLException ex) {

        }
    }

    private static void ɾ����ɫ(final String qq, final String account, final String token) {
        System.out.println("[������]" + CurrentReadable_Time() + " : QQ��" + qq + "��ɾ����ɫ �� " + account);
        if (��ȴ > 0) {
            sendMsg("�����˷�æ�����Ժ����ԡ�", token);
            return;
        } else {
            ��ȴ += 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * ��ȴʱ��);
                        ��ȴ = 0;
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
        boolean result = account.matches("[0-9]+");
        if (!result) {
            sendMsg("����������ID��", token);
            return;
        }
        Boolean isExists = AutoRegister.�жϽ�ɫID�Ƿ����(account);
        if (!isExists) {
            sendMsg("������Ľ�ɫID�����ڡ�", token);
            return;
        }
        if (!ServerProperties.getProperty("ZEV.QQ1").equals(qq) || !ServerProperties.getProperty("ZEV.QQ2").equals(qq) || !"71447500".equals(qq)) {
            if (!�˺�ȡ��QQ(�˺�IDȡ�˺�(��ɫIDȡ�˺�ID(account))).equals(token)) {
                sendMsg("��Ҫɾ���Ľ�ɫ�������㡣", token);
                return;
            }
        }
        if (World.Find.findChannel(��ɫIDȡ��ɫ��(account)) > 0) {
            sendMsg("��Ľ�ɫĿǰ���ߣ����ý�ɫ���ߺ���ʹ�á�", token);
            return;
        }
        sendMsg("����ɾ����ɫ " + ��ɫIDȡ��ɫ��(account) + " �ɹ�����ɫ���� 1 Сʱ��ɾ����", token);
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 60 * 60 * 1);
                    sendMsg("������ɾ���Ľ�ɫ " + ��ɫIDȡ��ɫ��(account) + " �Ѿ���ɾ����", token);
                    ɾ����ɫ(Integer.parseInt(account));
                } catch (InterruptedException e) {
                }
            }
        }.start();
    }

    private static void �⿨����(final String qq, final String account, final String token) {
        System.out.println("[������]" + CurrentReadable_Time() + " : QQ��" + qq + "���⿨���� �� " + account);
        if (��ȴ > 0) {
            sendMsg("�����˷�æ�����Ժ����ԡ�", token);
            return;
        } else {
            ��ȴ += 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * ��ȴʱ��);
                        ��ȴ = 0;
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
        boolean result = account.matches("[0-9]+");
        if (!result) {
            sendMsg("����������ID��", token);
            return;
        }
        Boolean isExists = AutoRegister.�жϽ�ɫID�Ƿ����(account);
        if (!isExists) {
            sendMsg("������Ľ�ɫID�����ڡ�", token);
            return;
        }
        if (!ServerProperties.getProperty("ZEV.QQ1").equals(qq) || !ServerProperties.getProperty("ZEV.QQ2").equals(qq) || !"71447500".equals(qq)) {
            if (!�˺�ȡ��QQ(�˺�IDȡ�˺�(��ɫIDȡ�˺�ID(account))).equals(token)) {
                sendMsg("��Ҫ�⿨���͵Ľ�ɫ����������ġ�", token);
                return;
            }
        }
        if (World.Find.findChannel(��ɫIDȡ��ɫ��(account)) > 0) {
            sendMsg("��Ľ�ɫĿǰ���ߣ����ý�ɫ���ߺ���ʹ�á�", token);
            return;
        }
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characters SET face = ?WHERE id = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
            ps1.setInt(1, Integer.parseInt(account));
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                sqlString1 = "update characters set face='20000' where id='" + Integer.parseInt(account) + "';";
                PreparedStatement hair = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                hair.executeUpdate(sqlString1);
                ps.close();
                ps1.close();
                sendMsg("��ɫ���ͽ⿨�ɹ���", token);
            }
        } catch (SQLException ex) {

        }

    }

    private static void ɾ���쳣��Ʒ(final String qq, final String account, final String token) {
        if (!ServerProperties.getProperty("ZEV.QQ1").equals(qq) || !ServerProperties.getProperty("ZEV.QQ2").equals(qq) || !"71447500".equals(qq)) {
            System.out.println("[������]" + CurrentReadable_Time() + " : QQ��" + qq + "��ɾ���쳣��Ʒ �� " + account);
            boolean result = account.matches("[0-9]+");
            if (!result) {
                sendMsg("����������ID��", token);
                return;
            }
            Boolean isExists = AutoRegister.�жϽ�ɫID�Ƿ����(account);
            if (!isExists) {
                sendMsg("������Ľ�ɫID�����ڡ�", token);
                return;
            }
            if (World.Find.findChannel(��ɫIDȡ��ɫ��(account)) > 0) {
                sendMsg("��Ľ�ɫĿǰ���ߣ����ý�ɫ���ߺ���ʹ�á�", token);
                return;
            }
            try {
                Connection con = DatabaseConnection.getConnection();
                try (PreparedStatement ps = con.prepareStatement("SELECT *  FROM inventoryitems WHERE characterid =" + account + ""); ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        if (MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")) == null) {
                            String sqlstr = " delete from inventoryitems where itemid = " + rs.getInt("itemid") + "";
                            sendMsg("ɾ����������Ʒ����ɾ��: " + rs.getInt("itemid"), token);
                            ps.executeUpdate(sqlstr);
                        }
                    }
                    ps.close();
                }

            } catch (SQLException Ex) {
            }

        }

    }

    private static void �⿨��ͼ1(final String qq, final String account, final String token) {
        System.out.println("[������]" + CurrentReadable_Time() + " : QQ��" + qq + "���⿨��ͼ �� " + account);
        if (��ȴ > 0) {
            sendMsg("�����˷�æ�����Ժ����ԡ�", token);
            return;
        } else {
            ��ȴ += 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * ��ȴʱ��);
                        ��ȴ = 0;
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
        boolean result = account.matches("[0-9]+");
        if (!result) {
            sendMsg("����������ID��", token);
            return;
        }
        Boolean isExists = AutoRegister.�жϽ�ɫID�Ƿ����(account);
        if (!isExists) {
            sendMsg("������Ľ�ɫID�����ڡ�", token);
            return;
        }
        if (!ServerProperties.getProperty("ZEV.QQ1").equals(qq) || !ServerProperties.getProperty("ZEV.QQ2").equals(qq) || !"71447500".equals(qq)) {
            if (!�˺�ȡ��QQ(�˺�IDȡ�˺�(��ɫIDȡ�˺�ID(account))).equals(token)) {
                sendMsg("��Ҫ�⿨���͵Ľ�ɫ����������ġ�", token);
                return;
            }
        }
        if (World.Find.findChannel(��ɫIDȡ��ɫ��(account)) > 0) {
            sendMsg("��Ľ�ɫĿǰ���ߣ����ý�ɫ���ߺ���ʹ�á�", token);
            return;
        }
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characters SET map = ?WHERE id = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
            ps1.setInt(1, Integer.parseInt(account));
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                sqlString1 = "update characters set map='100000000' where id='" + Integer.parseInt(account) + "';";
                PreparedStatement hair = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                hair.executeUpdate(sqlString1);
                ps.close();
                ps1.close();
                sendMsg("��ɫ��ͼ�⿨�ɹ���", token);
            }
        } catch (SQLException ex) {

        }
    }

    private static void �⿨��ͼ2(final String qq, final String account, final String token) {
        System.out.println("[������]" + CurrentReadable_Time() + " : QQ��" + qq + "���⿨��ͼ �� " + account);

        if (��ȴ > 0) {
            sendMsg("�����˷�æ�����Ժ����ԡ�", token);
            return;
        } else {
            ��ȴ += 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * ��ȴʱ��);
                        ��ȴ = 0;
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
        boolean result = account.matches("[0-9]+");
        if (!result) {
            sendMsg("����������ID��", token);
            return;
        }
        Boolean isExists = AutoRegister.�жϽ�ɫID�Ƿ����(account);
        if (!isExists) {
            sendMsg("������Ľ�ɫID�����ڡ�", token);
            return;
        }
        if (!ServerProperties.getProperty("ZEV.QQ1").equals(qq) || !ServerProperties.getProperty("ZEV.QQ2").equals(qq) || !"71447500".equals(qq)) {
            if (!�˺�ȡ��QQ(�˺�IDȡ�˺�(��ɫIDȡ�˺�ID(account))).equals(token)) {
                sendMsg("��Ҫ�⿨���͵Ľ�ɫ����������ġ�", token);
                return;
            }
        }
        if (World.Find.findChannel(��ɫIDȡ��ɫ��(account)) <= 0) {
            sendMsg("��Ľ�ɫĿǰ�����ߣ����ý�ɫ���ߺ���ʹ�á�", token);
            return;
        }
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter c : cserv.getPlayerStorage().getAllCharacters()) {
                if (c.getId() == Integer.parseInt(account)) {
                    c.changeMap(100000000, 1);
                    sendMsg("��ɫ��ͼ�⿨�ɹ���", token);
                }
            }
        }
    }

    private static void ���(final String qq, final String account, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq)) {
            String �˺� = �˺�IDȡ�˺�(��ɫIDȡ�˺�ID(��ɫ����ȡID(account)));
            int id = ��ɫ����ȡID(account);
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps;
                ps = con.prepareStatement("Update accounts set banned = ? Where name = ?");
                ps.setInt(1, 1);
                ps.setString(2, �˺�);
                ps.execute();
                ps.close();
            } catch (Exception ex) {
            }

            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                    if (chr == null) {
                        continue;
                    }
                    if (chr.getId() == id) {
                        chr.getClient().getSession().close();
                    }
                }
            }
        }
    }

    private static void ������״̬(final String qq, final String account, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq)) {
            if (null != account) {
                switch (account) {
                    case "��":
                        MapleParty.�����˴��� = 0;
                        sendMsg("<" + MapleParty.�������� + ">�������˿�ʼ������", token);
                        break;
                    case "��":
                        MapleParty.�����˴��� = 1;
                        sendMsg("<" + MapleParty.�������� + ">�������˿�ʼ������", token);
                        break;
                    default:
                        sendMsg("<" + MapleParty.�������� + ">��������[��],[��]��", token);
                        break;
                }
            }
        }
    }

    private static void �̳ǳ���֪ͨ(final String qq, final String account, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq)) {
            if (null != account) {
                switch (account) {
                    case "��":
                        MapleParty.�̳ǳ���֪ͨ = 0;
                        sendMsg("<" + MapleParty.�������� + ">�������̳���Ʒ����֪ͨ��", token);
                        break;
                    case "��":
                        MapleParty.�̳ǳ���֪ͨ = 1;
                        sendMsg("<" + MapleParty.�������� + ">���ر��̳���Ʒ����֪ͨ��", token);
                        break;
                    default:
                        sendMsg("<" + MapleParty.�������� + ">��������[��],[��]��", token);
                        break;
                }
            }
        }
    }

    private static void ȫ������(final String qq, final String account, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq)) {
            if (null != account) {
                switch (account) {
                    case "��":
                        ������("ȫ����������", 2014);
                        sendMsg("<" + MapleParty.�������� + ">������ȫ��������", token);
                        break;
                    case "��":
                        ������("ȫ����������", 2014);
                        sendMsg("<" + MapleParty.�������� + ">���ر�ȫ��������", token);
                        break;
                    default:
                        sendMsg("<" + MapleParty.�������� + ">��������[��],[��]��", token);
                        break;
                }
            }
        }
    }

    private static void �������ݿ�(final String qq, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
            if (MapleParty.�����˴��� > 0) {
                return;
            }
            sendMsg("<" + MapleParty.�������� + ">����ʼ�������ݿ⡣", token);
            �ر����ݿ�(qq, token);
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 5);
                        �����ݿ�(qq, token);
                        sendMsg("<" + MapleParty.�������� + ">�����ݿ�������ϡ�", token);
                    } catch (InterruptedException e) {
                    }
                }
            }.start();

        }
    }

    private static void �����ݿ�(final String qq, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
            if (MapleParty.�����˴��� > 0) {
                return;
            }
            StringBuilder builder = new StringBuilder();
            try {
                // ���� cmd���ִ�� net start mysql�� /c ����Ҫ��
                Process p = Runtime.getRuntime().exec("cmd.exe /c net start MySQL5");
                InputStream inputStream = p.getInputStream();
                try ( // ��ȡ����ִ����Ľ��
                        Scanner scanner = new Scanner(inputStream, "GBK")) {
                    scanner.useDelimiter("\\A");
                    while (scanner.hasNext()) {
                        builder.append(scanner.next());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void �ر����ݿ�(final String qq, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
            if (MapleParty.�����˴��� > 0) {
                return;
            }
            StringBuilder builder = new StringBuilder();
            try {
                // ���� cmd���ִ�� net start mysql�� /c ����Ҫ��
                Process p = Runtime.getRuntime().exec("cmd.exe /c net stop MySQL5");
                InputStream inputStream = p.getInputStream();

                try ( // ��ȡ����ִ����Ľ��
                        Scanner scanner = new Scanner(inputStream, "GBK")) {
                    scanner.useDelimiter("\\A");

                    while (scanner.hasNext()) {
                        builder.append(scanner.next());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void ����(final String qq, final String b, final String a, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
            if (MapleParty.�����˴��� > 0) {
                return;
            }
            int ԭʼ���� = Integer.parseInt(ServerProperties.getProperty("ZEV.Exp"));
            int ���� = ԭʼ���� * Integer.parseInt(b);
            int seconds = 0;
            int mins = 0;
            int hours = Integer.parseInt(a);
            int time = seconds + (mins * 60) + (hours * 60 * 60);
            final String rate = "����";
            World.scheduleRateDelay(rate, time);
            for (ChannelServer cservs : ChannelServer.getAllInstances()) {
                cservs.setExpRate(����);
            }
            World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[���ʻ] : ��Ϸ��ʼ " + b + " ����־����������� " + a + " Сʱ�����λ��ҿ񻶰ɣ�"));
            sendMsgToQQGroup(String.format("<" + MapleParty.�������� + ">����Ϸ��ʼ " + b + " ����־����������� " + a + " Сʱ�����λ��ҿ񻶰ɣ�", qq));
            sendMsg("<" + MapleParty.�������� + ">������������ϡ�", token);
        }
    }

    private static void ���ʻ(final String qq, final String b, final String a, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
            if (MapleParty.�����˴��� > 0) {
                return;
            }
            int ԭʼ���� = Integer.parseInt(ServerProperties.getProperty("ZEV.Drop"));
            int ���ʻ = ԭʼ���� * Integer.parseInt(b);
            int seconds = 0;
            int mins = 0;
            int hours = Integer.parseInt(a);
            int time = seconds + (mins * 60) + (hours * 60 * 60);
            final String rate = "����";
            World.scheduleRateDelay(rate, time);
            for (ChannelServer cservs : ChannelServer.getAllInstances()) {
                cservs.setDropRate(���ʻ);
            }
            World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[���ʻ] : ��Ϸ��ʼ " + b + " ����ֱ��ʻ�������� " + a + " Сʱ�����λ��ҿ񻶰ɣ�"));
            sendMsgToQQGroup(String.format("<" + MapleParty.�������� + ">����Ϸ��ʼ " + b + " ����ֱ��ʻ�������� " + a + " Сʱ�����λ��ҿ񻶰ɣ�", qq));
            sendMsg("<" + MapleParty.�������� + ">�����ʻ������ϡ�", token);
        }
    }

    private static void ��һ(final String qq, final String b, final String a, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
            if (MapleParty.�����˴��� > 0) {
                return;
            }
            int ԭʼ��� = Integer.parseInt(ServerProperties.getProperty("ZEV.Drop"));
            int ��һ = ԭʼ��� * Integer.parseInt(b);
            int seconds = 0;
            int mins = 0;
            int hours = Integer.parseInt(a);
            int time = seconds + (mins * 60) + (hours * 60 * 60);
            final String rate = "���";
            World.scheduleRateDelay(rate, time);
            for (ChannelServer cservs : ChannelServer.getAllInstances()) {
                cservs.setMesoRate(��һ);
            }
            World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[���ʻ] : ��Ϸ��ʼ " + b + " ����ֽ�һ�������� " + a + " Сʱ�����λ��ҿ񻶰ɣ�"));
            sendMsgToQQGroup(String.format("<" + MapleParty.�������� + ">����Ϸ��ʼ " + b + " ����ֽ�һ�������� " + a + " Сʱ�����λ��ҿ񻶰ɣ�", qq));
            sendMsg("<" + MapleParty.�������� + ">����һ������ϡ�", token);
        }
    }

    private static void ��ȫ������ȯ(final String qq, final String a, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
            if (MapleParty.�����˴��� > 0) {
                return;
            }
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    mch.modifyCSPoints(1, Integer.parseInt(a));
                    mch.startMapEffect("" + MapleParty.�������� + "����Ա���� " + a + " ��ȯ�����ߵ�������ң�ף����Ŀ�����Ŀ��֣�", 5120027);
                }
            }
            sendMsgToQQGroup(String.format("<" + MapleParty.�������� + ">������Ա���� " + a + " ��ȯ�����ߵ�������ң�ף����Ŀ�����Ŀ��֣�", qq));
            sendMsg("<" + MapleParty.�������� + ">����ȫ�����͵�ȯ��ɡ�", token);
        }
    }

    private static void ��ȫ�������(final String qq, final String a, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
            if (MapleParty.�����˴��� > 0) {
                return;
            }
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    mch.gainMeso(Integer.parseInt(a), true);
                    mch.startMapEffect("" + MapleParty.�������� + "����Ա���� " + a + " ��Ҹ����ߵ�������ң�ף����Ŀ�����Ŀ��֣�", 5120027);
                }
            }
            sendMsgToQQGroup(String.format("<" + MapleParty.�������� + ">������Ա���� " + a + " ��Ҹ����ߵ�������ң�ף����Ŀ�����Ŀ��֣�", qq));
            sendMsg("<" + MapleParty.�������� + ">����ȫ�����ͽ����ɡ�", token);
        }
    }

    private static void ��ȫ��������(final String qq, final String a, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
            if (MapleParty.�����˴��� > 0) {
                return;
            }
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    mch.gainExp(Integer.parseInt(a), true, true, true);
                    mch.startMapEffect("" + MapleParty.�������� + "����Ա���� " + a + " ��������ߵ�������ң�ף����Ŀ�����Ŀ��֣�", 5120027);
                }
            }
            sendMsgToQQGroup(String.format("<" + MapleParty.�������� + ">������Ա���� " + a + " ��������ߵ�������ң�ף����Ŀ�����Ŀ��֣�", qq));
            sendMsg("<" + MapleParty.�������� + ">����ȫ�����;�����ɡ�", token);
        }
    }

    private static void ��ȫ������Ʒ(final String qq, final String a, final String b, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
            if (MapleParty.�����˴��� > 0) {
                return;
            }
            sendMsgToQQGroup(String.format("<" + MapleParty.�������� + ">������Ա���� " + MapleItemInformationProvider.getInstance().getName(Integer.parseInt(a)) + " x " + b + " �����ߵ�������ң�ף����Ŀ�����Ŀ��֣�", qq));
            sendMsg("<" + MapleParty.�������� + ">����ȫ��������Ʒ��ɡ�", token);
            try {
                int ����;
                int ��ƷID;
                ��ƷID = Integer.parseInt(a);
                ���� = Integer.parseInt(b);
                MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                MapleInventoryType type = GameConstants.getInventoryType(��ƷID);
                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        if (���� >= 0) {
                            if (!MapleInventoryManipulator.checkSpace(mch.getClient(), ��ƷID, ����, "")) {
                                return;
                            }
                            if (type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(��ƷID) && !GameConstants.isBullet(��ƷID)
                                    || type.equals(MapleInventoryType.CASH) && ��ƷID >= 5000000 && ��ƷID <= 5000100) {
                                final Equip item = (Equip) (ii.getEquipById(��ƷID));
                                if (ii.isCash(��ƷID)) {
                                    item.setUniqueId(1);
                                }
                                final String name = ii.getName(��ƷID);
                                if (��ƷID / 10000 == 114 && name != null && name.length() > 0) { //medal
                                    final String msg = "���ѻ�óƺ� <" + name + ">";
                                    mch.getClient().getPlayer().dropMessage(5, msg);
                                }
                                MapleInventoryManipulator.addbyItem(mch.getClient(), item.copy());
                            } else {
                                MapleInventoryManipulator.addById(mch.getClient(), ��ƷID, (short) ����, "", null, (byte) 0);
                            }
                        } else {
                            MapleInventoryManipulator.removeById(mch.getClient(), GameConstants.getInventoryType(��ƷID), ��ƷID, -����, true, false);
                        }
                        mch.getClient().sendPacket(MaplePacketCreator.getShowItemGain(��ƷID, (short) ����, true));
                        mch.startMapEffect("" + MapleParty.�������� + "����Ա���� " + MapleItemInformationProvider.getInstance().getName(Integer.parseInt(a)) + " x " + b + " �����ߵ�������ң�ף����Ŀ�����Ŀ��֣�", 5120027);
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    private static void ¼��(final String qq, final String a, final String b, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
            if (MapleParty.�����˴��� > 0) {
                return;
            }
            if (!"O".equals(b) && !"X".equals(b) && !"o".equals(b) && !"x".equals(b)) {
                sendMsg("<" + MapleParty.�������� + ">����ֻ��Ϊ[O��o��X��x]", token);
                return;
            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO oxdt (b, c) VALUES (?, ?)")) {
                ps.setString(1, a);
                ps.setString(2, b);
                ps.executeUpdate();
                sendMsg("<" + MapleParty.�������� + ">����Ŀ¼��ɹ���(" + a + "/" + b + ")", token);
            } catch (SQLException ex) {

            }
        }
    }

    private static void ������(final String qq, final String a, final String b, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
            if (MapleParty.�����˴��� > 0) {
                return;
            }

        }
    }

    private static void ��ѯ�˺�(final String qq, final String token) {
        System.out.println("[������]" + CurrentReadable_Time() + " : QQ��" + qq + "���ҵ��˺� �� " + token);
        if (��ѯQQ���Ƿ��з���˺�(qq) > 0) {
            sendMsg("���QQ��������Ϸ�˺��Ѿ�Υ����ð�յ���ɫ��Ϸ�����Ѿ������÷����", token);
            return;
        }
        if (��ȴ > 0) {
            sendMsg("�����˷�æ�����Ժ����ԡ�", token);
            return;
        } else {
            ��ȴ += 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * ��ȴʱ��);
                        ��ȴ = 0;
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
        final StringBuilder sb = new StringBuilder();
        int a = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM accounts WHERE qq = " + qq + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                String txt = "";
                txt += "\r\n";
                txt += "��Ϸ�˺ţ�" + rs.getString("name") + "\r\n";
                if (rs.getString("lastlogin") != "") {
                    txt += "����¼��" + rs.getString("lastlogin") + "\r\n";
                }
                /*if (�˺Ž�ɫ2(rs.getInt("id"), "id") > 0) {
                    String ״̬ = "����";
                    if (World.Find.findChannel(�˺Ž�ɫ(rs.getInt("id"), "name")) > 0) {
                        ״̬ = "����";
                    }
                    txt += "Ŀǰ״̬��" + ״̬ + "\r\n";
                    txt += "����¼��" + rs.getString("lastlogin") + "\r\n";
                    txt += "��ɫ���֣�[" + �˺Ž�ɫ(rs.getInt("id"), "id") + "] " + �˺Ž�ɫ(rs.getInt("id"), "name") + "\r\n";
                } else {
                    txt += "û�����ݣ����˺�û�н�ɫ\r\n";
                }*/
                a += 1;
                sb.append(String.format(txt));
            }
        } catch (SQLException ex) {

        }
        sendMsg("�����˺����£�\r\n"
                + "��QQ��" + token + "\r\n"
                // + "�������֣�100\r\n"
                + "�˺�������" + a + "\r\n"
                + "" + sb.toString(), token);
    }

    private static void ��ѯ�˺�2(final String qq, final String zhanghao, final String token) {
        System.out.println("[������]" + CurrentReadable_Time() + " : QQ��" + qq + "����ѯ�˺� �� " + token);
        if (��ѯQQ���Ƿ��з���˺�(qq) > 0) {
            sendMsg("���QQ��������Ϸ�˺��Ѿ�Υ����ð�յ���ɫ��Ϸ�����Ѿ������÷����", token);
            return;
        }
        if (!�˺�ȡ��QQ(zhanghao).equals(token)) {
            sendMsg("��Ҫ��ѯ�����˺Ų������㡣", token);
            return;
        }
        if (��ȴ > 0) {
            sendMsg("�����˷�æ�����Ժ����ԡ�", token);
            return;
        } else {
            ��ȴ += 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * ��ȴʱ��);
                        ��ȴ = 0;
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }

        final StringBuilder sb = new StringBuilder();
        int a = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM accounts WHERE name = " + zhanghao + "");
            rs = ps.executeQuery();
            if (rs.next()) {
                String txt = "";
                txt += "\r\n";
                txt += "��Ϸ�˺ţ�" + rs.getString("name") + "\r\n";
                if (�˺Ž�ɫ2(rs.getInt("id"), "id") > 0) {
                    String ״̬ = "����";
                    if (World.Find.findChannel(�˺Ž�ɫ(rs.getInt("id"), "name")) > 0) {
                        ״̬ = "����";
                    }
                    txt += "��ɫ���֣�[" + �˺Ž�ɫ(rs.getInt("id"), "id") + "] " + �˺Ž�ɫ(rs.getInt("id"), "name") + "\r\n";
                    txt += "��ɫ�ȼ���" + �˺Ž�ɫ(rs.getInt("id"), "level") + "\r\n";
                    txt += "��ɫְҵ��" + getJobNameById(Integer.parseInt(�˺Ž�ɫ(rs.getInt("id"), "job"))) + "\r\n";
                    txt += "Ŀǰ״̬��" + ״̬ + "\r\n";
                    txt += "����¼��" + rs.getString("lastlogin") + "\r\n";
                } else {
                    txt += "û�����ݣ����˺�û�н�ɫ\r\n";
                }
                a += 1;
                sb.append(String.format(txt));
            }
        } catch (SQLException ex) {

        }
        sendMsg(sb.toString(), token);
    }

    private static void �������(final String qq, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {

            System.out.println("[������]" + CurrentReadable_Time() + " : QQ��" + qq + "��������� �� " + token);
            StringBuilder sb = new StringBuilder();
            int a = 0;
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;

                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM characters ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String txt = "";
                    if (World.Find.findChannel(rs.getString("name")) > 0) {
                        txt += "��� : " + rs.getString("name") + " [" + rs.getString("id") + "]\r\n";
                        a += 1;
                    }
                    sb.append(String.format(txt));
                }
            } catch (SQLException ex) {

            }
            sendMsg("�������߽�ɫ��\r\n"
                    + "������" + a + "\r\n\r\n"
                    + "" + sb.toString(), token);
        }
    }

    private static void ���߹�Ӷ(final String qq, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {

            System.out.println("[������]" + CurrentReadable_Time() + " : QQ��" + qq + "��������� �� " + token);
            try {
                int ID = 0;
                int ��Ӷ���� = 0;
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT `id` FROM accounts  ORDER BY `id` DESC LIMIT 1");
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String SN = rs.getString("id");
                        int sns = Integer.parseInt(SN);
                        sns++;
                        ID = sns;
                    }
                }
                for (int i = 0; i <= ID; i++) {
                    boolean ��Ӷ = World.hasMerchant(i);
                    if (��Ӷ) {
                        ��Ӷ����++;
                    }
                }
                sendMsg("�������߹�Ӷ��\r\n"
                        + "������" + ��Ӷ���� + " ��", token);
                System.err.println("[�����]" + CurrentReadable_Time() + " : ϵͳ���ڷ��Ź�Ӷ�ݵ� �� ");
            } catch (SQLException | NumberFormatException e) {
                System.err.println("[�����]" + CurrentReadable_Time() + " : ϵͳ�������ñ��÷����ݵ� x ");
            }

        }
    }

    private static void ������̨(final String qq, final String token) {
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq) || "34443647".equals(qq)) {
            System.out.println("[������]" + CurrentReadable_Time() + " : QQ��" + qq + "��������̨ �� " + token);
            CashShopServer();
        }
    }

    public static int �˺�����(String a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("qq") == null ? a == null : rs.getString("qq").equals(a)) {
                    data += 1;
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static String �˺Ž�ɫ(int a, String b) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE accountid = " + a + "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data = rs.getString(b);
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int �˺Ž�ɫ2(int a, String b) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE accountid = " + a + "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data = rs.getInt(b);
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    private static void ע���˺�(final String qq, final String newPassword, final String token) {
        System.out.println("[������]" + CurrentReadable_Time() + " : QQ��" + qq + "��ע�����˺� �� " + newPassword);
        if (��ѯQQ���Ƿ��з���˺�(qq) > 0) {
            sendMsg("���QQ��������Ϸ�˺��Ѿ�Υ����ð�յ���ɫ��Ϸ�����Ѿ������÷����", token);
            return;
        }
        if (��ȴ > 0) {
            sendMsg("�����˷�æ�����Ժ����ԡ�", token);
            return;
        } else {
            ��ȴ += 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * ��ȴʱ��);
                        ��ȴ = 0;
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
        if (MapleParty.�����˴��� > 0) {
            return;
        }
        if (!newPassword.matches("^[0-9A-Za-z]{6,11}$")) {
            sendMsg("�˺Ÿ�ʽ���ԣ�������6-11λ���ֻ���ĸ��ɡ�", token);
            return;
        }

        if (gui.Start.ConfigValuesMap.get("������ע�Ὺ��") != 0) {
            sendMsg("����Ա�ر����˺�ע�Ṧ�ܡ�", token);
            return;
        }
        ע������� itemjc = new ע�������();
        String items1 = itemjc.getQQ();
        if (!items1.contains("," + token + ",")) {
            try {
                if (Start.CloudBlacklist.containsKey(token)) {
                    sendMsg("<<" + MapleParty.�������� + "�Ʒ�ϵͳ>>"
                            + "����⵽����ʹ�÷Ƿ�����������ƻ���Ϸƽ��Ĳ�����¼����������˺��Ǹ������Ⱥ�ĳ�Ա����Ĵ˴�ע���Ѿ������أ��ѱ���ֹ��½������ע�᱾��һ�з��������������ʣ�����ϵ����Ա��", token);
                    return;
                }
            } catch (Exception ex) {
                System.err.println("���غ�������ҳ���" + ex.getMessage());
            }
        }
        ע������� itemjc2 = new ע�������();
        String items2 = itemjc2.getQQ();
        if (items2.contains("," + token + ",")) {
            sendMsg("<<" + MapleParty.�������� + "������ϵͳ>>"
                    + "����˺��ѱ������������ʣ�����ϵ����Ա��", token);
            return;
        }
        //�ж��Ѿ�ע���˺�����
        if (�˺�����(token) >= gui.Start.ConfigValuesMap.get("ע���˺�����")) {
            sendMsg("���Ѿ��޷�����ע���˺��ˡ�", token);
            return;
        }
        // ���ж�QQ�˺��Ƿ��Ѿ�ע��
        Boolean isExists = AutoRegister.getAccountExists(newPassword);
        if (isExists) {
            sendMsg("���˺��Ѿ���ע�ᣬ�뻻һ���ɡ�", token);
            return;
        }

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO accounts (name, password, email, birthday, qq) VALUES (?, ?, ?, ?, ?)");
            try {
                ps.setString(1, newPassword);
                ps.setString(2, LoginCrypto.hexSha1(qq));
                ps.setString(3, String.format("%s@qq.com", qq));
                ps.setString(4, "2018-11-11");
                ps.setString(5, qq);
                ps.executeUpdate();
                ps.close();
            } finally {
                ps.close();
            }
            sendMsg("��ã��뽫��Ҫ���õ�������[*�޸�����]<�ո�>[�˺�]<�ո�>[����]��ʽ�����ң��ҽ�Ϊ���޸����롣", token);
            Boolean isExists2 = AutoRegister.getAccountExists2(qq);
            if (!isExists2) {
                try {
                    Connection con1 = DatabaseConnection1.getConnection1();
                    PreparedStatement ps1 = con1.prepareStatement("INSERT INTO ��ϷQQ�� ( qq ) VALUES (?)");
                    ps1.setString(1, token);
                    ps1.executeUpdate();
                    ps1.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                    return;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            sendMsg("�Բ���QQ %s ע��ʧ�ܣ�", token);
        }
    }

    private static void �������(final String qq, final String ����, final String ����, final String token) {
        if (��ѯQQ���Ƿ��з���˺�(qq) > 0) {
            sendMsg("���QQ��������Ϸ�˺��Ѿ�Υ����ð�յ���ɫ��Ϸ�����Ѿ������÷����", token);
            return;
        }
        if (!����.matches("^[0-9]{7,7}$")) {
            sendMsg("����ȷ�����뿨�š�", token);
            return;
        }
        if (!����.matches("^[0-9]{7,7}$")) {
            sendMsg("����ȷ���������롣", token);
            return;
        }
        if (��ȴ > 0) {
            sendMsg("�����˷�æ�����Ժ����ԡ�", token);
            return;
        } else {
            ��ȴ += 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * ��ȴʱ��);
                        ��ȴ = 0;
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
        if (�����˺�����(Integer.parseInt(����)) == Integer.parseInt(����)) {
            sendMsg("�����п��ŵ����"
                    + "\r\n����:" + MapleParty.��������
                    + "\r\n����:" + ����
                    + "\r\n��ȯ:" + ���е�ȯ���(Integer.parseInt(����))
                    + "\r\n���:" + ���н�����(Integer.parseInt(����)), token);
        } else {
            sendMsg("�����˺��������", token);
        }

    }

    private static void ת�˽��(final String qq, final String ����, final String ����, final String ת���˺�, final String ���, final String token) {
        if (��ѯQQ���Ƿ��з���˺�(qq) > 0) {
            sendMsg("���QQ��������Ϸ�˺��Ѿ�Υ����ð�յ���ɫ��Ϸ�����Ѿ������÷����", token);
            return;
        }
        if (!����.matches("^[0-9]{7,7}$")) {
            sendMsg("����ȷ�����뿨�š�", token);
            return;
        }
        if (!����.matches("^[0-9]{7,7}$")) {
            sendMsg("����ȷ���������롣", token);
            return;
        }
        if (!ת���˺�.matches("^[0-9]{7,7}$")) {
            sendMsg("����ȷ������ת���˺š�", token);
            return;
        }
        if (!���.matches("^[0-9]{1,100000000000}$")) {
            sendMsg("����ȷ������ת�˽�", token);
            return;
        }
        if (��ȴ > 0) {
            sendMsg("�����˷�æ�����Ժ����ԡ�", token);
            return;
        } else {
            ��ȴ += 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * ��ȴʱ��);
                        ��ȴ = 0;
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
        if (�����˺�(Integer.parseInt(����)) <= 0) {
            sendMsg("���п��Ų����ڡ�", token);
            return;
        }
        if (�����˺�����(Integer.parseInt(����)) == Integer.parseInt(����)) {
            long ��� = ���н�����(Integer.parseInt(����));
            long ת�˽�� = Long.parseLong(���);
            long Ŀ����� = ���н�����(Integer.parseInt(ת���˺�));
            if (��� >= ת�˽��) {
                /**
                 * <ת����>
                 */
                ������н��(Integer.parseInt(����), (��� - ת�˽��));
                ͨ��("���������˺� " + ���� + " �� " + CurrentReadable_Time() + " ת�� " + ��� + " ��ң�Ŀǰ�������˺����Ϊ " + (��� - ת�˽��) + " ��ҡ�������" + MapleParty.�������� + "���������ֻ�ת�ˣ�", �˺�IDȡ��QQ(��ɫIDȡ�˺�ID(���п�����(Integer.parseInt(����)))));
                /**
                 * <������>
                 */
                ������н��(Integer.parseInt(ת���˺�), (long) (ת�˽�� * 0.99 + Ŀ�����));
                ͨ��("���������˺� " + ת���˺� + " �� " + CurrentReadable_Time() + " ת�� " + (long) (ת�˽�� * 0.99) + " ��ң�Ŀǰ�������˺����Ϊ " + (long) (ת�˽�� * 0.99 + Ŀ�����) + " ��ҡ�������" + MapleParty.�������� + "���������ֻ�ת�ˣ�", �˺�IDȡ��QQ(��ɫIDȡ�˺�ID(���п�����(Integer.parseInt(ת���˺�)))));
            } else {
                sendMsg("ת�����㣬�����Խ���ת�ˡ�", token);
            }
        } else {
            sendMsg("�����˺��������", token);
        }
    }

    private static void ת�˵�ȯ(final String qq, final String ����, final String ����, final String ת���˺�, final String ���, final String token) {
        if (��ѯQQ���Ƿ��з���˺�(qq) > 0) {
            sendMsg("���QQ��������Ϸ�˺��Ѿ�Υ����ð�յ���ɫ��Ϸ�����Ѿ������÷����", token);
            return;
        }
        if (!����.matches("^[0-9]{7,7}$")) {
            sendMsg("����ȷ�����뿨�š�", token);
            return;
        }
        if (!����.matches("^[0-9]{7,7}$")) {
            sendMsg("����ȷ���������롣", token);
            return;
        }
        if (!ת���˺�.matches("^[0-9]{7,7}$")) {
            sendMsg("����ȷ������ת���˺š�", token);
            return;
        }
        if (!���.matches("^[0-9]{1,100000000000}$")) {
            sendMsg("����ȷ������ת�˽�", token);
            return;
        }
        if (��ȴ > 0) {
            sendMsg("�����˷�æ�����Ժ����ԡ�", token);
            return;
        } else {
            ��ȴ += 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * ��ȴʱ��);
                        ��ȴ = 0;
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
        if (�����˺�(Integer.parseInt(����)) <= 0) {
            sendMsg("���п��Ų����ڡ�", token);
            return;
        }
        if (�����˺�����(Integer.parseInt(����)) == Integer.parseInt(����)) {
            long ��� = ���е�ȯ���(Integer.parseInt(����));
            long ת�˽�� = Long.parseLong(���);
            long Ŀ����� = ���е�ȯ���(Integer.parseInt(ת���˺�));
            if (��� >= ת�˽��) {
                /**
                 * <ת����>
                 */
                ������е�ȯ(Integer.parseInt(����), (��� - ת�˽��));
                ͨ��("���������˺� " + ���� + " �� " + CurrentReadable_Time() + " ת�� " + ��� + " ��ȯ��Ŀǰ�������˺����Ϊ " + (��� - ת�˽��) + " ��ȯ��������" + MapleParty.�������� + "���������ֻ�ת�ˣ�", �˺�IDȡ��QQ(��ɫIDȡ�˺�ID(���п�����(Integer.parseInt(����)))));
                /**
                 * <������>
                 */

                ������е�ȯ(Integer.parseInt(ת���˺�), (long) (ת�˽�� * 0.99 + Ŀ�����));
                ͨ��("���������˺� " + ת���˺� + " �� " + CurrentReadable_Time() + " ת�� " + (long) (ת�˽�� * 0.99) + " ��ȯ��Ŀǰ�������˺����Ϊ " + (long) (ת�˽�� * 0.99 + Ŀ�����) + " ��ȯ��������" + MapleParty.�������� + "���������ֻ�ת�ˣ�", �˺�IDȡ��QQ(��ɫIDȡ�˺�ID(���п�����(Integer.parseInt(ת���˺�)))));
            } else {
                sendMsg("ת�����㣬�����Խ���ת�ˡ�", token);
            }
        } else {
            sendMsg("�����˺��������", token);
        }
    }

    private static void �޸�����(final String qq, final String zhanghao, final String newPassword, final String token) {
        System.out.println("[������]" + CurrentReadable_Time() + " : QQ��" + zhanghao + "���޸����� �� " + newPassword);
        if (��ȴ > 0) {
            sendMsg("�����˷�æ�����Ժ����ԡ�", token);
            return;
        } else {
            ��ȴ += 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * ��ȴʱ��);
                        ��ȴ = 0;
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
        if (MapleParty.�����˴��� > 0) {
            return;
        }
        if (!newPassword.matches("^[0-9A-Za-z]{6,10}$")) {
            sendMsg("�����벻�ϸ񣬱�����6-10λ���ֻ���ĸ��ɡ�", token);
            return;
        }
        Boolean isExists = AutoRegister.getAccountExists(zhanghao);
        if (!isExists) {
            sendMsg("���˺Ų����ڡ�", token);
            return;
        }
        if (ȡ�˺Ű󶨵�QQ(zhanghao) == null ? token != null : !ȡ�˺Ű󶨵�QQ(zhanghao).equals(token)) {
            sendMsg("����˺Ų������㡣", token);
            return;
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pss = con.prepareStatement("UPDATE `accounts` SET `password` = ?, `salt` = ? WHERE qq = ? && name = ?");
            try {
                final String newSalt = LoginCrypto.makeSalt();
                pss.setString(1, LoginCrypto.makeSaltedSha512Hash(newPassword, newSalt));
                pss.setString(2, newSalt);
                pss.setString(3, qq);
                pss.setString(4, zhanghao);
                int res = pss.executeUpdate();
                if (res > 0) {
                    sendMsg("��ϲ�㣬�����޸ĳɹ���", token);
                } else {
                    sendMsg("û���ҵ����QQ��Ӧ���˺ţ������޸�ʧ�ܣ�", token);
                }
            } finally {
                pss.close();
            }
        } catch (SQLException e) {
            System.err.println("�޸��������" + e);
        }
    }

    public static void ������(String a, int b) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
            ps1.setInt(1, 1);
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlstr = " delete from configvalues where id =" + b + "";
                ps1.executeUpdate(sqlstr);
                ps1.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
            ps.setInt(1, b);
            ps.setString(2, a);
            ps.setInt(3, 0);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
        }
        gui.Start.GetConfigValues();
    }

    public static void ������(String a, int b) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
            ps1.setInt(1, 1);
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlstr = " delete from configvalues where id =" + b + "";
                ps1.executeUpdate(sqlstr);
                ps1.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
            ps.setInt(1, b);
            ps.setString(2, a);
            ps.setInt(3, 1);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
        }
        gui.Start.GetConfigValues();
    }

    private static void ָ���ȫ(final String qq, final String token) {
        if (��ȴ > 0) {
            sendMsg("�����˷�æ�����Ժ����ԡ�", token);
            return;
        } else {
            ��ȴ += 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * ��ȴʱ��);
                        ��ȴ = 0;
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
        if (ServerProperties.getProperty("ZEV.QQ1").equals(qq) || ServerProperties.getProperty("ZEV.QQ2").equals(qq) || "71447500".equals(qq)) {
            sendMsg(""
                    + "<�����ʹ�õ�ָ��>\r\n"
                    + "[˽]*�ҵ��˺�\r\n"
                    + "[˽]*�������\r\n"
                    + "[˽]*���߹�Ӷ\r\n"
                    + "[˽]*�������ݿ�\r\n"
                    + "[˽]*ע���˺�<�ո�>[�˺�]\r\n"
                    + "[˽]*��ѯ�˺�<�ո�>[�˺�]\r\n"
                    + "[˽]*¼��<�ո�>[��Ŀ]<�ո�>[��]\r\n"
                    + "[˽]*ɾ����ɫ<�ո�>[��ɫID]\r\n"
                    + "[˽]*�޸�����<�ո�>[�˺�]<�ո�>[����]\r\n"
                    + "[˽]*�⿨����<�ո�>[��ɫID]\r\n"
                    + "[˽]*�⿨����<�ո�>[��ɫID]\r\n"
                    + "[˽]*�⿨��ͼ1<�ո�>[��ɫID](����)\r\n"
                    + "[˽]*�⿨��ͼ2<�ո�>[��ɫID](����)\r\n"
                    + "[˽]*����<�ո�>[����]<�ո�>[����ʱ��]\r\n"
                    + "[˽]*���ʻ<�ո�>[����]<�ո�>[����ʱ��]\r\n"
                    + "[˽]*��һ<�ո�>[����]<�ո�>[����ʱ��]\r\n"
                    + "[˽]*��ȫ������ȯ<�ո�>[����]\r\n"
                    + "[˽]*��ȫ�������<�ո�>[����]\r\n"
                    + "[˽]*��ȫ��������<�ո�>[����]\r\n"
                    + "[˽]*��ȫ������Ʒ<�ո�>[��Ʒ����]<�ո�>[����]\r\n"
                    + "[˽]*������״̬<�ո�>[��/��]\r\n"
                    + "[˽]*ȫ������<�ո�>[��/��]\r\n"
                    + "[˽]*�̳ǳ���֪ͨ<�ո�>[��/��]\r\n"
                    + "[˽]*������̨(��С�Ĺرշ���˺�̨��ʹ�ú���)\r\n"
                    + "[˽]*��ѯ�������<�ո�>[����]<�ո�>[����]\r\n"
                    + "[˽]*ת�˵�ȯ<�ո�>[����]<�ո�>[����]<�ո�>[ת�˿���]<�ո�>[���]\r\n"
                    + "[˽]*ת�˽��<�ո�>[����]<�ո�>[����]<�ո�>[ת�˿���]<�ո�>[���]\r\n"
                    + "", token);
        } else {
            sendMsg(""
                    + "<�����ʹ�õ�ָ��>\r\n"
                    + "[˽]*�ҵ��˺�(�鿴QQ�������˺�)\r\n"
                    + "[˽]*ע���˺�<�ո�>[�˺�]\r\n"
                    + "[˽]*�޸�����<�ո�>[�˺�]<�ո�>[����]\r\n"
                    + "[˽]*ɾ����ɫ<�ո�>[��ɫID]\r\n"
                    + "[˽]*�⿨����<�ո�>[��ɫID]\r\n"
                    + "[˽]*�⿨����<�ո�>[��ɫID]\r\n"
                    + "[˽]*�⿨��ͼ1<�ո�>[��ɫID](����)\r\n"
                    + "[˽]*�⿨��ͼ2<�ո�>[��ɫID](����)\r\n"
                    + "[˽]*��ѯ�˺�<�ո�>[�˺�]\r\n"
                    + "[˽]*��ѯ�������<�ո�>[����]<�ո�>[����]\r\n"
                    + "[˽]*ת�˵�ȯ<�ո�>[����]<�ո�>[����]<�ո�>[ת�˿���]<�ո�>[���]\r\n"
                    + "[˽]*ת�˽��<�ո�>[����]<�ո�>[����]<�ո�>[ת�˿���]<�ո�>[���]\r\n"
                    + "", token);
        }
    }

    public static final String getJobNameById(int job) {
        switch (job) {
            case 0:
                return "����";
            case 1000:
                return "������";
            case 2000:
                return "սͯ";
            case 100:
                return "սʿ";
            case 110:
                return "����";
            case 111:
                return "��ʿ";
            case 112:
                return "Ӣ��";
            case 120:
                return "׼��ʿ";
            case 121:
                return "��ʿ";
            case 122:
                return "ʥ��ʿ";
            case 130:
                return "ǹսʿ";
            case 131:
                return "����ʿ";
            case 132:
                return "����ʿ";
            case 200:
                return "ħ��ʦ";
            case 210:
                return "��ʦ(��,��)";
            case 211:
                return "��ʦ(��,��)";
            case 212:
                return "ħ��ʦ(��,��)";
            case 220:
                return "��ʦ(��,��)";
            case 221:
                return "��ʦ(��,��)";
            case 222:
                return "ħ��ʦ(��,��)";
            case 230:
                return "��ʦ";
            case 231:
                return "��˾";
            case 232:
                return "����";
            case 300:
                return "������";
            case 310:
                return "����";
            case 311:
                return "����";
            case 312:
                return "������";
            case 320:
                return "����";
            case 321:
                return "����";
            case 322:
                return "����";
            case 400:
                return "����";
            case 410:
                return "�̿�";
            case 411:
                return "��Ӱ��";
            case 412:
                return "��ʿ";
            case 420:
                return "����";
            case 421:
                return "���п�";
            case 422:
                return "����";
            case 500:
                return "���I";
            case 510:
                return "ȭ��";
            case 511:
                return "��ʿ";
            case 512:
                return "���ӳ�";
            case 520:
                return "��ǹ��";
            case 521:
                return "��";
            case 522:
                return "����";
            case 800:
            case 900:
                return "����Ա";
            case 1100:
                return "����ʿ(һת)";
            case 1110:
                return "����ʿ(��ת)";
            case 1111:
                return "����ʿ(��ת)";
            case 1112:
                return "����ʿ(��ת)";
            case 1200:
                return "����ʿ(һת)";
            case 1210:
                return "����ʿ(��ת)";
            case 1211:
                return "����ʿ(��ת)";
            case 1212:
                return "����ʿ(��ת)";
            case 1300:
                return "����ʹ��(һת)";
            case 1310:
                return "����ʹ��(��ת)";
            case 1311:
                return "����ʹ��(��ת)";
            case 1312:
                return "����ʹ��(��ת)";
            case 1400:
                return "ҹ����(һת)";
            case 1410:
                return "ҹ����(��ת)";
            case 1411:
                return "ҹ����(��ת)";
            case 1412:
                return "ҹ����(��ת)";
            case 1500:
                return "��Ϯ��(һת)";
            case 1510:
                return "��Ϯ��(��ת)";
            case 1511:
                return "��Ϯ��(��ת)";
            case 1512:
                return "��Ϯ��(��ת)";
            case 2100:
                return "ս��(һת)";
            case 2110:
                return "ս��(��ת)";
            case 2111:
                return "ս��(��ת)";
            case 2112:
                return "ս��(��ת)";
            default:
                return "δְ֪ҵ";
        }
    }

    public static void ɾ����ɫ(final int cid) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;

        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
            ps1.setInt(1, cid);
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlstr = " delete from characters where id =" + cid + "";
                ps1.executeUpdate(sqlstr);

                String sqlstr2 = " delete from inventoryitems where characterid =" + cid + "";
                ps1.executeUpdate(sqlstr2);

                String sqlstr21 = " delete from inventoryitems where characterid =" + cid + "";
                ps1.executeUpdate(sqlstr21);

                String sqlstr3 = " delete from auctionitems where characterid =" + cid + "";
                ps1.executeUpdate(sqlstr3);

                String sqlstr31 = " delete from auctionitems1 where characterid =" + cid + "";
                ps1.executeUpdate(sqlstr31);

                String sqlstr32 = " delete from auctionpoint where characterid =" + cid + "";
                ps1.executeUpdate(sqlstr32);

                String sqlstr33 = " delete from auctionpoint1 where characterid =" + cid + "";
                ps1.executeUpdate(sqlstr33);

                String sqlstr4 = " delete from csitems where accountid =" + cid + "";
                ps1.executeUpdate(sqlstr4);

                String sqlstr41 = " delete from awarp where id =" + cid + "";
                ps1.executeUpdate(sqlstr41);

                String sqlstr5 = " delete from bank_item where cid =" + cid + "";
                ps1.executeUpdate(sqlstr5);

                String sqlstr6 = " delete from bossrank where cid =" + cid + "";
                ps1.executeUpdate(sqlstr6);

                String sqlstr7 = " delete from skills where characterid =" + cid + "";
                ps1.executeUpdate(sqlstr7);

                String sqlstr71 = " delete from skillmacros where characterid =" + cid + "";
                ps1.executeUpdate(sqlstr71);

                String sqlstr711 = " delete from monsterbook where charid =" + cid + "";
                ps1.executeUpdate(sqlstr711);

            }
        } catch (SQLException ex) {
        }

    }

    public static void deleteCharacter(final int cid, final int accId) {
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT guildid, guildrank, familyid, name FROM characters WHERE id = ? AND accountid = ?");
            ps.setInt(1, cid);
            ps.setInt(2, accId);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                rs.close();
                ps.close();

            }
            if (rs.getInt("guildid") > 0) {
                if (rs.getInt("guildrank") == 1) {
                    rs.close();
                    ps.close();

                }
                World.Guild.deleteGuildCharacter(rs.getInt("guildid"), cid);
            }
            if (rs.getInt("familyid") > 0) {
                World.Family.getFamily(rs.getInt("familyid")).leaveFamily(cid);
            }
            rs.close();
            ps.close();
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM characters WHERE id = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM monsterbook WHERE charid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM hiredmerch WHERE characterid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM mts_cart WHERE characterid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM mts_items WHERE characterid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM mountdata WHERE characterid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM inventoryitems WHERE characterid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM famelog WHERE characterid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM famelog WHERE characterid_to = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM dueypackages WHERE RecieverId = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM wishlist WHERE characterid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM buddies WHERE characterid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM buddies WHERE buddyid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM keymap WHERE characterid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM savedlocations WHERE characterid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM skills WHERE characterid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM mountdata WHERE characterid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM skillmacros WHERE characterid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM trocklocations WHERE characterid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM queststatus WHERE characterid = ?", cid);
            MapleCharacter.deleteWhereCharacterId(con, "DELETE FROM inventoryslot WHERE characterid = ?", cid);

        } catch (Exception e) {
            FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, e);
            e.printStackTrace();
        }

    }
}
