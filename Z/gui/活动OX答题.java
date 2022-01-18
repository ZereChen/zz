package gui;

import client.MapleCharacter;
import database.DatabaseConnection;
import static gui.QQͨ��.OX����;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.concurrent.ScheduledFuture;
import scripting.NPCScriptManager;
import server.Timer;
import static tools.FileoutputUtil.CurrentReadable_Time;

/**
 *
 * @author Administrator
 */
public class �OX���� {

    public static ScheduledFuture<?> OX�����߳� = null;
    public static String ��Ŀ = "";
    public static String �� = "";
    public static int �� = 1;
    public static Boolean ���� = false;//true//false
    public static Boolean x = false;

    /**
     * <
     *8:00 ����֪ͨOX����10�ֺ�ʼ��*����OX�������߳�*�ӳ�10��������OX�����߳�
     * >
     */
    public static void OX�����߳�() {
        if (OX�����߳� == null) {
            OX�����߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    int ʱ = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                    int �� = Calendar.getInstance().get(Calendar.MINUTE);
                    int ���� = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                    if (���� == true) {
                        System.out.println("[�����]" + CurrentReadable_Time() + " : OX�����߳������С�/ " + ʱ + " / " + �� + "/  " + x + "");
                    }
                    switch (��) {
                        case 1:
                            if (ʱ == 20 && �� == 10 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 11 && x == true) {
                                �жϴ�();
                            }
                            break;
                        case 2:
                            if (ʱ == 20 && �� == 12 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 13 && x == true) {
                                �жϴ�();
                            }
                            break;
                        case 3:
                            if (ʱ == 20 && �� == 14 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 15 && x == true) {
                                �жϴ�();
                            }
                            break;
                        case 4:
                            if (ʱ == 20 && �� == 16 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 17 && x == true) {
                                �жϴ�();
                            }
                            break;
                        case 5:
                            if (ʱ == 20 && �� == 18 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 19 && x == true) {
                                �жϴ�();
                            }
                            break;
                        case 6:
                            if (ʱ == 20 && �� == 20 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 21 && x == true) {
                                �жϴ�();
                            }
                            break;
                        case 7:
                            if (ʱ == 20 && �� == 22 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 23 && x == true) {
                                �жϴ�();
                            }
                            break;
                        case 8:
                            if (ʱ == 20 && �� == 24 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 25 && x == true) {
                                �жϴ�();
                            }
                            break;
                        case 9:
                            if (ʱ == 20 && �� == 26 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 27 && x == true) {
                                �жϴ�();
                            }
                            break;
                        case 10:
                            if (ʱ == 20 && �� == 28 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 29 && x == true) {
                                �жϴ�();
                                if (���� > 1 && ���� < 7) {
                                    �ر�OX�����߳�();
                                }
                            }
                        case 11:
                            if (ʱ == 20 && �� == 30 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 31 && x == true) {
                                �жϴ�();
                            }
                        case 12:
                            if (ʱ == 20 && �� == 32 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 33 && x == true) {
                                �жϴ�();
                            }
                        case 13:
                            if (ʱ == 20 && �� == 34 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 35 && x == true) {
                                �жϴ�();
                            }
                        case 14:
                            if (ʱ == 20 && �� == 36 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 37 && x == true) {
                                �жϴ�();
                            }
                        case 15:
                            if (ʱ == 20 && �� == 38 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 39 && x == true) {
                                �жϴ�();
                            }
                        case 16:
                            if (ʱ == 20 && �� == 40 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 41 && x == true) {
                                �жϴ�();
                            }
                        case 17:
                            if (ʱ == 20 && �� == 42 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 43 && x == true) {
                                �жϴ�();
                            }
                        case 18:
                            if (ʱ == 20 && �� == 44 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 45 && x == true) {
                                �жϴ�();
                            }
                        case 19:
                            if (ʱ == 20 && �� == 46 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 47 && x == true) {
                                �жϴ�();
                            }
                        case 20:
                            if (ʱ == 20 && �� == 48 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 49 && x == true) {
                                �жϴ�();
                            }
                            break;
                        case 21:
                            if (ʱ == 20 && �� == 50 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 51 && x == true) {
                                �жϴ�();
                            }
                            break;
                        case 22:
                            if (ʱ == 20 && �� == 52 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 53 && x == true) {
                                �жϴ�();
                            }
                            break;
                        case 23:
                            if (ʱ == 20 && �� == 54 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 55 && x == true) {
                                �жϴ�();
                            }
                            break;
                        case 24:
                            if (ʱ == 20 && �� == 56 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 57 && x == true) {
                                �жϴ�();
                            }
                            break;
                        case 25:
                            if (ʱ == 20 && �� == 58 && x == false) {
                                if ("".equals(��Ŀ)) {
                                    OX����();
                                }
                            } else if (ʱ == 20 && �� == 59 && x == true) {
                                �жϴ�();
                                �ر�OX�����߳�();
                            }
                            break;
                        default:

                            break;
                    }
                }
            }, 1000 * 5);
        }
    }

    public static void �ر�OX�����߳�() {
        if (OX�����߳� != null) {
            OX�����߳�.cancel(true);
            OX�����߳� = null;
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    mch.startMapEffect("[ÿ�ջ] : OX ����˴δ���Բ����������л��λС����֧�֡�", 5120027);
                }
            }
            �� = 1;
            OX����("[ÿ�ջ] : OX����˴δ���Բ����������л��λС����֧�֡�");
            MapleParty.OX���� = 0;
        }
    }

    public static void OX����() {
        x = true;
        ��Ŀ = ���ȡ��Ŀ();
        �� = ���ȡ��Ŀ��(��Ŀ);
        System.err.println("[�����]" + CurrentReadable_Time() + " : [OX����]��" + �� + "�� ����: " + ��Ŀ);
        System.err.println("[�����]" + CurrentReadable_Time() + " : [OX����]��" + �� + "�� ��: " + ��);
        OX����("����" + �� + "�⡿: " + ��Ŀ + "");
        for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
            for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                if (mch.getMapId() == 109020001) {
                    mch.startMapEffect("����" + �� + "�⡿: " + ��Ŀ + "", 5120027);
                }
            }
        }
    }

    public static void �жϴ�() {
        x = false;
        �� = ���ȡ��Ŀ��(��Ŀ);
        if ("O".equals(��) || "o".equals(��)) {
            O();
        } else if ("X".equals(��) || "x".equals(��)) {
            X();
        } else {
            Z();
        }
        System.err.println("[�����]" + CurrentReadable_Time() + " : �жϴ�: " + ��);
        ��Ŀ = "";
        ��++;
    }

    public static String ���ȡ��Ŀ() {
        String ��Ŀ = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM `oxdt` ORDER BY RAND() LIMIT 0,100 ;  ")) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    ��Ŀ = rs.getString("b");
                }
            }
        } catch (SQLException ex) {

        }
        return ��Ŀ;
    }

    public static String ���ȡ��Ŀ��(String a) {
        String �� = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM `oxdt` ; ")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("b").equals(a)) {
                        �� = rs.getString("c");
                    }
                }
            }
        } catch (SQLException ex) {

        }
        return ��;
    }

    public static void O() {
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() == 109020001) {
                    double x = chr.getPosition().getX();
                    double y = chr.getPosition().getY();
                    if (x <= -307 && y > -200) {
                        NPCScriptManager.getInstance().dispose(chr.getClient());
                        NPCScriptManager.getInstance().start(chr.getClient(), 9000011, 4);
                        chr.dropMessage(1, "��ϲ��ѡ������ȷ�Ĵ�");
                    } else {
                        chr.dropMessage(1, "�ܱ�Ǹ����ѡ���˲���ȷ�Ĵ�");
                    }
                }
            }
        }
    }

    public static void X() {
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() == 109020001) {
                    double x = chr.getPosition().getX();
                    double y = chr.getPosition().getY();
                    if (x >= -146 && y > -200) {
                        NPCScriptManager.getInstance().dispose(chr.getClient());
                        NPCScriptManager.getInstance().start(chr.getClient(), 9000011, 4);
                        chr.dropMessage(1, "��ϲ��ѡ������ȷ�Ĵ�");
                    } else {
                        chr.dropMessage(1, "�ܱ�Ǹ����ѡ���˲���ȷ�Ĵ�");
                    }
                }
            }
        }
    }

    public static void Z() {
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() == 109020001) {
                    chr.dropMessage(1, "�ܱ�Ǹ����¼��Ĵ𰸼Ȳ���O��Ҳ����X���������⡣");
                }
            }
        }

    }

}
