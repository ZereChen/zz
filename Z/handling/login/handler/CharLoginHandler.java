/*
��½�˺�
 */
package handling.login.handler;

import static a.�������ݿ�.��ѯQQ���Ƿ��з���˺�;
import abc.ע�������;
import abc.ע�������;
import java.util.List;
import java.util.Calendar;
import client.inventory.IItem;
import client.inventory.Item;
import client.MapleClient;
import client.MapleCharacter;
import client.MapleCharacterUtil;
import client.inventory.MapleInventory;
import client.inventory.MapleInventoryType;
import database.DatabaseConnection;
import handling.channel.ChannelServer;
import handling.login.LoginInformationProvider;
import handling.login.LoginServer;
import handling.login.LoginWorker;
import handling.world.MapleParty;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import server.MapleItemInformationProvider;
import server.quest.MapleQuest;
import server.ServerProperties;
import gui.Start;
import static a.�������ݿ�.�˺�ȡ��QQ;
import tools.FileoutputUtil;
import static tools.FileoutputUtil.CurrentReadable_Date;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;
import tools.packet.LoginPacket;
import tools.KoreanDateUtil;
import tools.StringUtil;
import tools.data.LittleEndianAccessor;

public class CharLoginHandler {

    public static final void login(final LittleEndianAccessor slea, final MapleClient c) {
        /**
         * ***
         * �����˺ŵ����½��Ϸ
         */
        //�жϷ�����Ƿ��������
        /*if (MapleParty.����������� == 0) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "�����δ������ɣ����Ժ�"));
            c.sendPacket(LoginPacket.getLoginFailed(1));
            return;
        }*/
        final String login = slea.readMapleAsciiString();
        final String pwd = slea.readMapleAsciiString();
        //��¼�˺�
        c.setAccountName(login);
        int[] bytes = new int[6];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = slea.readByteAsInt();
        }
        StringBuilder sps = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sps.append(StringUtil.getLeftPaddedStr(Integer.toHexString(bytes[i]).toUpperCase(), '0', 2));
            sps.append("-");
        }
        //�������ַ
        String macData = sps.toString();
        macData = macData.substring(0, macData.length() - 1);
        c.setMac(macData);
        //�ж�IP�Ƿ񱻽�ֹ
        final boolean ipBan = c.hasBannedIP();
        //�жϻ������Ƿ񱻽�
        final boolean macBan = c.isBannedMac(macData);
        //ĳһ�������򱻽�ֹ
        final boolean banned = ipBan || macBan;
        int loginok = 0;
        if (AutoRegister.autoRegister && !AutoRegister.�ж��˺��Ƿ����(login) && (!banned)) {
            if (pwd.equalsIgnoreCase("disconnect") || pwd.equalsIgnoreCase("fixme")) {
                c.sendPacket(MaplePacketCreator.serverNotice(1, "��������Ч��"));
                c.sendPacket(LoginPacket.getLoginFailed(1));
                return;
            }
            if (gui.Start.ConfigValuesMap.get("�˺�ע�Ὺ��") <= 0) {
                AutoRegister.createAccount(login, pwd, c.getSession().remoteAddress().toString(), macData);
                AutoRegister.success = true;
                AutoRegister.mac = true;
                c.sendPacket(MaplePacketCreator.serverNotice(1, "<<" + MapleParty.�������� + ">>\r\n\r\n�˺�ע��ɹ��������µ�¼�����ɽ�����Ϸ��"));
            } else {
                c.sendPacket(MaplePacketCreator.serverNotice(1, "<<" + MapleParty.�������� + ">>\r\n\r\n����Աδ����ע�Ṧ�ܡ�"));
            }
            c.sendPacket(LoginPacket.getLoginFailed(1));
            return;
        }
        loginok = c.login(login, pwd, ipBan || macBan);
        final Calendar tempbannedTill = c.getTempBanCalendar();
        if (loginok == 0 && (ipBan || macBan) && !c.isGm()) {
            loginok = 3;
            if (macBan) {
            }
        }

        /**
         * <��¼ǰ�����½��Ϣ>
         */
        //ȡ�˺Ű�QQ
        String ��QQ = �˺�ȡ��QQ(login);
        //�ж��˺��Ƿ��QQ
        if (��QQ == null) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "<<" + MapleParty.�������� + ">>\r\n\r\n����˺���δ��QQ�����QQ���ٳ��Ե�½��"));
            c.sendPacket(LoginPacket.getLoginFailed(1));
            return;
        }
        if (gui.Start.ConfigValuesMap.get("�������ؿ���") == 0) {
            ע������� itemjc = new ע�������();
            String items1 = itemjc.getQQ();
            if (!items1.contains("," + ��QQ + ",")) {
                //�ƺ�����
                try {
                    String qq = ��QQ;
                    String ip = c.getSession().remoteAddress().toString().split(":")[0];
                    if (Start.CloudBlacklist.containsKey(qq) || Start.CloudBlacklist.containsValue(ip)) {
                        c.sendPacket(MaplePacketCreator.serverNotice(1, ""
                                + "<<ð�յ��Ʒ�ϵͳ>>\r\n"
                                + "" + MapleParty.�������� + "\r\n"
                                + "\r\n\r\n��⵽����ʹ�÷Ƿ�����������ƻ���Ϸƽ��Ĳ�����¼����������˺��Ǹ������Ⱥ�ĳ�Ա����Ĵ˴ε�½�Ѿ������أ��ѱ���ֹ��½����һ�з��������������ʣ�����ϵ����Ա��"));
                        c.sendPacket(LoginPacket.getLoginFailed(1));
                        return;
                    }
                } catch (Exception ex) {
                }
            }
            //���غ�����
            ע������� itemjc2 = new ע�������();
            String items2 = itemjc2.getQQ();
            if (items2.contains("," + ��QQ + ",")) {
                c.sendPacket(MaplePacketCreator.serverNotice(1, ""
                        + "<<ð�յ�������ϵͳ>>\r\n"
                        + "" + MapleParty.�������� + "\r\n"
                        + "\r\n\r\n����˺��ѱ������������ʣ�����ϵ����Ա��"));
                c.sendPacket(LoginPacket.getLoginFailed(1));
                return;
            }
        }
        //��½����
        if (gui.Start.ConfigValuesMap.get("��½��������") == 0) {
            if (Getcharacterz("" + login + "", 101) > 0) {
                c.sendPacket(MaplePacketCreator.serverNotice(1, "<<" + MapleParty.�������� + ">>\r\n\r\n���Ժ��ڵ�½��Ŀǰ����˺Ż��ڵ�½����ʱ�䡣"));
                c.sendPacket(LoginPacket.getLoginFailed(1));
                return;
            }
        }
        //��½����
        if (gui.Start.ConfigValuesMap.get("��½���п���") == 0) {
            if (��������() > 5) {
                c.sendPacket(MaplePacketCreator.serverNotice(1, "<<" + MapleParty.�������� + ">>\r\n\r\n�����ǰ�滹�� " + ��������() + " ����ҡ�"));
                c.sendPacket(LoginPacket.getLoginFailed(1));
                return;
            }
        }
        //IP���ƶ࿪
        if (gui.Start.ConfigValuesMap.get("IP�࿪����") == 0) {
            if (IP��½��("" + c.getSessionIPAddress() + "") == false) {
                c.sendPacket(MaplePacketCreator.serverNotice(1, "<<" + MapleParty.�������� + ">>\r\n\r\n��IP���Ѿ��е�½���˺š�"));
                c.sendPacket(LoginPacket.getLoginFailed(1));
                return;
            }
        } else if (IP��½��2("" + c.getSessionIPAddress() + "") >= gui.Start.ConfigValuesMap.get("IP�࿪��")) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "<<" + MapleParty.�������� + ">>\r\n\r\n��½�����࿪���� A01��"));
            c.sendPacket(LoginPacket.getLoginFailed(1));
            return;
        }
        //���������ƶ࿪
        if (gui.Start.ConfigValuesMap.get("�����࿪����") == 0) {
            if (�������½��("" + macData + "") == false) {
                c.sendPacket(MaplePacketCreator.serverNotice(1, "<<" + MapleParty.�������� + ">>\r\n\r\n�û������Ѿ��е�½���˺š�"));
                c.sendPacket(LoginPacket.getLoginFailed(1));
                return;
            }
        } else if (�������½��2("" + macData + "") >= gui.Start.ConfigValuesMap.get("������࿪��")) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "<<" + MapleParty.�������� + ">>\r\n\r\n��½�����࿪���� A02��"));
            c.sendPacket(LoginPacket.getLoginFailed(1));
            return;
        }

        //�жϸ�QQ�Ƿ񱻷��
        if (��ѯQQ���Ƿ��з���˺�(��QQ) > 0) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "<<" + MapleParty.�������� + ">>\r\n\r\n��� QQ " + ��QQ + " �Ѿ��������"));
            c.sendPacket(LoginPacket.getLoginFailed(1));
            return;
        }
        //�ж���������
//        if (MapleParty.�������� != 999) {
//            if (�����˺�() >= MapleParty.��������) {
//                c.sendPacket(MaplePacketCreator.serverNotice(1, "<<" + MapleParty.�������� + ">>\r\n\r\n�ǳ���Ǹ�������������Ѵﵽ���ޣ���Ĵ˴ε�½���ܾ���"));
//                c.sendPacket(LoginPacket.getLoginFailed(1));
//                return;
//            }
//        }
        if (loginok != 0) {
            if (!loginFailCount(c)) {
                c.sendPacket(LoginPacket.getLoginFailed(loginok));
            }
        } else if (tempbannedTill.getTimeInMillis() != 0) {
            if (!loginFailCount(c)) {
                c.sendPacket(LoginPacket.getTempBan(KoreanDateUtil.getTempBanTimestamp(tempbannedTill.getTimeInMillis()), c.getBanReason()));
            }
        } else {
            //��½�˺ż�¼IP�ͻ�����
            if (gui.Start.ConfigValuesMap.get("��½��¼����") <= 0) {
                FileoutputUtil.logToFile("�˺ŵ�½Դ��¼/" + CurrentReadable_Date() + "/IP��ַ��½��Ϣ/" + c.getSession().remoteAddress().toString() + ".txt", "[ʱ�䣺" + CurrentReadable_Time() + "]�˺ţ�[ " + login + " ]�����룺[ " + macData + " ]\r\n");
                FileoutputUtil.logToFile("�˺ŵ�½Դ��¼/" + CurrentReadable_Date() + "/�������½��Ϣ/" + macData + ".txt", "[ʱ�䣺" + CurrentReadable_Time() + "]�˺ţ�[ " + login + " ]IP��[ " + c.getSession().remoteAddress().toString() + " ]\r\n");
                FileoutputUtil.logToFile("�˺ŵ�½Դ��¼/" + CurrentReadable_Date() + "/�˺ŵ�½��Ϣ/" + login + ".txt", "[ʱ�䣺" + CurrentReadable_Time() + "]�����룺[ " + macData + " ]IP��[ " + c.getSession().remoteAddress().toString() + " ]\r\n");
            }
            FileoutputUtil.logToZev("�������Ϣ�ļ�/����˵�½�˺��ռ�/" + MapleParty.�������� + ".txt", "" + login + "\r\n");
            //��¼�˴ε�½��mac
            c.updateMacs();
            c.loginAttempt = 0;
            //��½�˺�
            LoginWorker.registerClient(c);

            if (gui.Start.ConfigValuesMap.get("��½���п���") == 0) {
                //�������
                Gaincharacterz("" + login + "", 102, 1);
                //30�볬ʱʱ��
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000 * 30);
                            if (Getcharacterz("" + login + "", 102) > 0) {
                                Gaincharacterz("" + login + "", 102, -Getcharacterz("" + login + "", 102));
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
            }
            System.out.println("[�����]" + CurrentReadable_Time() + " : ��½�˺ţ� " + login + " ����QQ�� " + ��QQ + " ���� ");
        }
    }

    public static int Getcharacterz(String Name, int Channale) {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characterz WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public static void Gaincharacterz(String Name, int Channale, int Piot) {
        try {
            int ret = Getcharacterz(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO characterz (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);

                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e);
                    }
                }
            }
            ret += Piot;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE characterz SET `Point` = ? WHERE Name = ? and channel = ?");
            ps.setInt(1, ret);
            ps.setString(2, Name);
            ps.setInt(3, Channale);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("Getcharacterz!!55" + sql);
        }
    }

    public static final void SetGenderRequest(final LittleEndianAccessor slea, final MapleClient c) {
        byte gender = slea.readByte();
        String username = slea.readMapleAsciiString();
        // String password = slea.readMapleAsciiString();
        if (c.getAccountName().equals(username)) {
            c.setGender(gender);
            // c.setSecondPassword(password);
            c.updateSecondPassword();
            c.updateGender();
            c.sendPacket(LoginPacket.getGenderChanged(c));
            c.sendPacket(MaplePacketCreator.licenseRequest());
            c.updateLoginState(MapleClient.LOGIN_NOTLOGGEDIN, c.getSessionIPAddress());

        } else {
            c.getSession().close();
        }

    }

    public static final void ServerListRequest(final MapleClient c) {//��ʾƵ��
        if (gui.Start.ConfigValuesMap.get("����ţ����") == 0) {
            c.sendPacket(LoginPacket.getServerList(0, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.����ţ״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("Ģ���п���") == 0) {
            c.sendPacket(LoginPacket.getServerList(1, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.Ģ����״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("��ˮ�鿪��") == 0) {
            c.sendPacket(LoginPacket.getServerList(2, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��ˮ��״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("ƯƯ����") == 0) {
            c.sendPacket(LoginPacket.getServerList(3, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.ƯƯ��״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("С���߿���") == 0) {
            c.sendPacket(LoginPacket.getServerList(4, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.С����״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("���з����") == 0) {
            c.sendPacket(LoginPacket.getServerList(5, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.���з״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("�󺣹꿪��") == 0) {
            c.sendPacket(LoginPacket.getServerList(6, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�󺣹�״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("����ֿ���") == 0) {
            c.sendPacket(LoginPacket.getServerList(7, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�����״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("��Ƥ�￪��") == 0) {
            c.sendPacket(LoginPacket.getServerList(8, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��Ƥ��״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("�Ǿ��鿪��") == 0) {
            c.sendPacket(LoginPacket.getServerList(9, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�Ǿ���״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("����쿪��") == 0) {
            c.sendPacket(LoginPacket.getServerList(10, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�����״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("��ѩ�˿���") == 0) {
            c.sendPacket(LoginPacket.getServerList(11, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��ѩ��״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("ʯͷ�˿���") == 0) {
            c.sendPacket(LoginPacket.getServerList(12, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.ʯͷ��״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("��ɫè����") == 0) {
            c.sendPacket(LoginPacket.getServerList(13, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��ɫè״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("����ǿ���") == 0) {
            c.sendPacket(LoginPacket.getServerList(14, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�����״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("С���ÿ���") == 0) {
            c.sendPacket(LoginPacket.getServerList(15, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.С����״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("���������") == 0) {
            c.sendPacket(LoginPacket.getServerList(16, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.�����״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("��Ұ����") == 0) {
            c.sendPacket(LoginPacket.getServerList(17, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��Ұ��״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("�����㿪��") == 0) {
            c.sendPacket(LoginPacket.getServerList(18, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.������״̬"))));
        }
        if (gui.Start.ConfigValuesMap.get("��Ģ������") == 0) {
            c.sendPacket(LoginPacket.getServerList(19, LoginServer.getServerName(), LoginServer.getLoad(), Integer.parseInt(ServerProperties.getProperty("ZEV.��Ģ��״̬"))));
        }
        c.sendPacket(LoginPacket.getEndOfServerList());
    }

    public static final void ServerStatusRequest(final MapleClient c) {//����̫����ʾ
        // 0 = ��������
        // 1 = ����ӵ��
        // 2 = ��������
        final int numPlayer = LoginServer.getUsersOn();
        final int userLimit = LoginServer.getUserLimit();
        if (numPlayer >= userLimit) {
            c.sendPacket(LoginPacket.getServerStatus(2));
        } else if (numPlayer * 2 >= userLimit) {
            c.sendPacket(LoginPacket.getServerStatus(1));
        } else {
            c.sendPacket(LoginPacket.getServerStatus(0));
        }
    }

    public static final void LicenseRequest(final LittleEndianAccessor slea, final MapleClient c) {
        if (slea.readByte() == 1) {
            c.sendPacket(MaplePacketCreator.licenseResult());
            c.updateLoginState(0);
        } else {
            c.getSession().close();

        }

    }

    //ѡ��Ƶ��
    public static final void CharlistRequest(final LittleEndianAccessor slea, final MapleClient c) throws UnknownHostException, SocketException {
        // slea.readByte();
        final int server = slea.readByte();
        final int channel = slea.readByte() + 1;
        slea.readInt();

        //c.setWorld(server);
        c.setWorld(0);
        // System.out.println("Client " + c.getSession().getRemoteAddress().toString().split(":")[0] + " is connecting to server " + server + " channel " + channel + "");
        c.setChannel(channel);
        //final List<MapleCharacter> chars = c.loadCharacters(server);
        final List<MapleCharacter> chars = c.loadCharacters(0);
        if (chars != null) {
            c.sendPacket(LoginPacket.getCharList(c.getSecondPassword() != null, chars, c.getCharacterSlots()));
        } else {
            c.getSession().close();
        }
    }

    //�ж��������
    public static final void CheckCharName(final String name, final MapleClient c) {
        if (!MapleCharacterUtil.canCreateChar(name) || LoginInformationProvider.getInstance().isForbiddenName(name)) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "��ʾ����ɫ����ʽ����ȷ��ֻ�����Ļ������֡�"));
            c.sendPacket(LoginPacket.getLoginFailed(1));
        }
        c.sendPacket(LoginPacket.charNameResponse(name, !MapleCharacterUtil.canCreateChar(name) || LoginInformationProvider.getInstance().isForbiddenName(name)));
    }

    public static final void CreateChar(final LittleEndianAccessor slea, final MapleClient c) {
        int ð�ռ� = gui.Start.ConfigValuesMap.get("ð�ռ�ְҵ����");
        int ս�� = gui.Start.ConfigValuesMap.get("ս��ְҵ����");
        int ��ʿ�� = gui.Start.ConfigValuesMap.get("��ʿ��ְҵ����");
        final String name = slea.readMapleAsciiString();
        // 1 = ð�յ�, 0 = ��ʿ��, 2 = ս��
        final int JobType = slea.readInt();

        if (��ʿ�� == 1 && JobType == 0) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "��ʿ��ְҵȺ�ѱ��رգ��޷�������"));
            c.sendPacket(LoginPacket.getLoginFailed(1));
            return;
        } else if (ð�ռ� == 1 && JobType == 1) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "ð�ռ�ְҵȺ�ѱ��رգ��޷�������"));
            c.sendPacket(LoginPacket.getLoginFailed(1));
            return;
        } else if (ս�� == 1 && JobType == 2) {
            c.sendPacket(MaplePacketCreator.serverNotice(1, "ս��ְҵȺ�ѱ��رգ��޷�������"));
            c.sendPacket(LoginPacket.getLoginFailed(1));
            return;
        }//server.Start.ConfigValuesMap.get("������ɫ����")
        if (c.loadCharacterIds(c.getWorld()).size() >= gui.Start.ConfigValuesMap.get("������ɫ����")) {//LoginServer.getMaxCharacters()//LoginServer.getMaxCharacters()
            c.sendPacket(MaplePacketCreator.serverNotice(1, "�޷����������½�ɫ��"));
            c.sendPacket(LoginPacket.getLoginFailed(1));
            return;
        }
        final short db = 0; //whether dual blade = 1 or adventurer = 0
        final int face = slea.readInt();
        final int hair = slea.readInt();
        final int hairColor = 0;
        final byte skinColor = 0;
        final int top = slea.readInt();
        final int bottom = slea.readInt();
        final int shoes = slea.readInt();
        final int weapon = slea.readInt();

        final byte gender = c.getGender();

        if (gender == 0) {
            /*if (face != 20100 && face != 20401 && face != 20402) {
                return;
            }
            if (hair != 30030 && hair != 30027 && hair != 30000) {
                return;
            }
            if (top != 1040002 && top != 1040006 && top != 1040010 && top != 1042167) {
                return;
            }
            if (bottom != 1060002 && bottom != 1060006 && bottom != 1062115) {
                return;
            }*/
        } else if (gender == 1) {
            /*if (face != 21002 && face != 21700 && face != 21201) {
                return;
            }
            if (hair != 31002 && hair != 31047 && hair != 31057) {
                return;
            }
            if (top != 1041002 && top != 1041006 && top != 1041010 && top != 1041011) {
                return;
            }
            if (bottom != 1061002 && bottom != 1061008 && bottom != 1062115) {
                return;
            }*/
        } else {
            return;
        }
        if (shoes != 1072001 && shoes != 1072005 && shoes != 1072037 && shoes != 1072038 && shoes != 1072383) {
            return;
        }
        if (weapon != 1302000 && weapon != 1322005 && weapon != 1312004 && weapon != 1442079) {
            return;
        }

        MapleCharacter newchar = MapleCharacter.getDefault(c, JobType);
        //newchar.setWorld((byte) c.getWorld());
        newchar.setWorld((byte) 0);
        newchar.setFace(face);
        newchar.setHair(hair + hairColor);
        newchar.setGender(gender);
        newchar.setName(name);
        newchar.setSkinColor(skinColor);
        MapleInventory equip = newchar.getInventory(MapleInventoryType.EQUIPPED);
        final MapleItemInformationProvider li = MapleItemInformationProvider.getInstance();

        IItem item = li.getEquipById(top);
        item.setPosition((byte) -5);
        equip.addFromDB(item);

        item = li.getEquipById(bottom);
        item.setPosition((byte) -6);
        equip.addFromDB(item);

        item = li.getEquipById(shoes);
        item.setPosition((byte) -7);
        equip.addFromDB(item);

        item = li.getEquipById(weapon);
        item.setPosition((byte) -11);
        equip.addFromDB(item);

        //blue/red pots
        switch (JobType) {
            case 0: // Cygnus
                newchar.setQuestAdd(MapleQuest.getInstance(20022), (byte) 1, "1");
                newchar.setQuestAdd(MapleQuest.getInstance(20010), (byte) 1, null); //>_>_>_> ugh

                newchar.setQuestAdd(MapleQuest.getInstance(20000), (byte) 1, null); //>_>_>_> ugh
                newchar.setQuestAdd(MapleQuest.getInstance(20015), (byte) 1, null); //>_>_>_> ugh
                newchar.setQuestAdd(MapleQuest.getInstance(20020), (byte) 1, null); //>_>_>_> ugh

                newchar.getInventory(MapleInventoryType.ETC).addItem(new Item(4161047, (byte) 0, (short) 1, (byte) 0));
                break;
            case 1: // Adventurer
                newchar.getInventory(MapleInventoryType.ETC).addItem(new Item(4161001, (byte) 0, (short) 1, (byte) 0));
                break;
            case 2: // Aran
                newchar.getInventory(MapleInventoryType.ETC).addItem(new Item(4161048, (byte) 0, (short) 1, (byte) 0));
                break;
        }

        if (MapleCharacterUtil.canCreateChar(name) && !LoginInformationProvider.getInstance().isForbiddenName(name)) {
            MapleCharacter.saveNewCharToDB(newchar, JobType, JobType == 1 && db == 0);
            c.sendPacket(LoginPacket.addNewCharEntry(newchar, true));
            c.createdChar(newchar.getId());
            if (MapleParty.����3 > 0) {
                System.out.printf("�������ͨ��");
            }
        } else {
            if (MapleParty.����3 > 0) {
                System.out.printf("������ֲ�ͨ��");
            }
            c.sendPacket(LoginPacket.addNewCharEntry(newchar, false));
        }
    }

    public static final void DeleteChar(final LittleEndianAccessor slea, final MapleClient c) {

        slea.readByte();
        String Secondpw_Client = null;
//        if (slea.readByte() > 0) { // Specific if user have second password or not
        Secondpw_Client = slea.readMapleAsciiString();
//        }
//        slea.readMapleAsciiString();
        final int Character_ID = slea.readInt();

        if (!c.login_Auth(Character_ID)) {
            c.sendPacket(LoginPacket.secondPwError((byte) 0x14));
            return; // Attempting to delete other character
        }
        byte state = 0;

        if (c.getSecondPassword() != null) { // On the server, there's a second password
            if (Secondpw_Client == null) { // Client's hacking
                c.getSession().close();
                return;
            } else if (!c.CheckSecondPassword(Secondpw_Client)) { // Wrong Password
                //state = 12;
                state = 16;
            }
        }
        // TODO, implement 13 digit Asiasoft passport too.

        if (state == 0) {
            state = (byte) c.deleteCharacter(Character_ID);
        }
        c.sendPacket(LoginPacket.deleteCharResponse(Character_ID, state));
    }

    public static int ȡ�˺�����״̬(int a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("id") == a) {
                    data = rs.getInt("loggedin");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static void Character_WithoutSecondPassword(final LittleEndianAccessor slea, final MapleClient c) {//������Ϸѡ��Ƶ��
        // slea.skip(1);
        //�ж��ǲ�������
        if (ȡ�˺�����״̬(c.getAccID()) != 2) {
            c.sendPacket(LoginPacket.getLoginFailed(7));
            return;
        }

        if (c.getLoginState() != 2) {
            c.sendPacket(LoginPacket.getLoginFailed(7));
            return;
        }
        final int charId = slea.readInt();
        if ((!c.isLoggedIn()) || (loginFailCount(c)) || (!c.login_Auth(charId))) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if ((ChannelServer.getInstance(c.getChannel()) == null) || (c.getWorld() != 0)) {
            c.getSession().close();
            return;
        }
        if (c.getIdleTask() != null) {
            c.getIdleTask().cancel(true);
        }

        String ip = c.getSessionIPAddress();
        LoginServer.putLoginAuth(charId, ip.substring(ip.indexOf('/') + 1, ip.length()), c.getTempIP(), c.getChannel());
        c.sendPacket(MaplePacketCreator.getServerIP(Integer.parseInt(ChannelServer.getInstance(c.getChannel()).getIP().split(":")[1]), charId));
    }

    public static final void Character_WithSecondPassword(final LittleEndianAccessor slea, final MapleClient c) {
        final String password = slea.readMapleAsciiString();
        final int charId = slea.readInt();
        // ����������֣����򲻻ᷢ�����������
        if (loginFailCount(c) || c.getSecondPassword() == null || !c.login_Auth(charId)) {
            c.getSession().close();
            return;
        }
        if (c.CheckSecondPassword(password)) {
            c.updateMacs(slea.readMapleAsciiString());
            if (c.getIdleTask() != null) {
                c.getIdleTask().cancel(true);
            }
            c.updateLoginState(MapleClient.LOGIN_SERVER_TRANSITION, c.getSessionIPAddress());
            c.sendPacket(MaplePacketCreator.getServerIP(Integer.parseInt(ChannelServer.getInstance(c.getChannel()).getIP().split(":")[1]), charId));
        } else {
            c.sendPacket(LoginPacket.secondPwError((byte) 0x14));
        }
    }

    public static int �����˺�() {
        int ret = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM  accounts WHERE loggedin > 0");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ret += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�����˺š�����");
        }
        return ret;
    }

    public static int ��������() {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Point as DATA FROM characterz WHERE channel = 102");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    data += rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��������������");
        }
        return data;
    }

    public static boolean IP��½��(String a) {
        boolean ret = true;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM  accounts");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (rs.getString("SessionIP") != null) {
                        if (rs.getString("SessionIP").equals("" + a + "")) {
                            if (rs.getInt("loggedin") > 0) {
                                ret = false;
                            }
                        }
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("IP��½��������");
        }
        return ret;
    }

    public static int IP��½��2(String a) {
        int ret = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM  accounts");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (rs.getString("SessionIP") != null) {
                        if (rs.getString("SessionIP").equals("" + a + "")) {
                            if (rs.getInt("loggedin") > 0) {
                                ret += 1;
                            }
                        }
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("IP��½��2������");
        }
        return ret;
    }

    public static boolean �������½��(String a) {
        boolean ret = true;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM  accounts");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (rs.getString("macs") != null) {
                        if (rs.getString("macs").equals("" + a + "")) {
                            if (rs.getInt("loggedin") > 0) {
                                ret = false;
                            }
                        }
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�������½��������");
        }
        return ret;
    }

    public static int �������½��2(String a) {
        int ret = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM  accounts");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (rs.getString("macs") != null) {
                        if (rs.getString("macs").equals("" + a + "")) {
                            if (rs.getInt("loggedin") > 0) {
                                ret += 1;
                            }
                        }
                    }
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�������½��2������");
        }
        return ret;
    }

    private static final boolean loginFailCount(final MapleClient c) {
        c.loginAttempt++;
        if (c.loginAttempt > 5) {
            return true;
        }
        return false;
    }

    public static final void Welcome(final MapleClient c) {
        System.out.println("HELLO_CHANNEL������Welcome");
    }
}
