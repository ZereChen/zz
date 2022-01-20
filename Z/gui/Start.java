package gui;

import static a.�������ݿ�.�����������Ŀ¼;

import static a.�������ݿ�.������µ���Ŀ¼;
import static a.�������ݿ�.������½�ѹĿ¼;
import static a.�÷���ȫ.��ϵ��֤;
import static abc.Game.���ݿ����ݵ���;
import static abc.Game.�汾;
import static abc.sancu.FileDemo_05.ɾ���ļ�;
import client.Class2;
import client.DebugWindow;
import client.MapleCharacter;
import client.SkillFactory;
import constants.LogConstants;
import handling.MapleServerHandler;
import handling.channel.ChannelServer;
import handling.channel.MapleGuildRanking;
import handling.login.LoginServer;
import handling.cashshop.CashShopServer;
import handling.login.LoginInformationProvider;
import handling.world.World;
import java.sql.SQLException;
import database.DatabaseConnection;
import gui.Jieya.��ѹ�ļ�;
import static gui.ZEVMS.�����ļ�;
import handling.world.MapleParty;
import handling.world.family.MapleFamilyBuff;
import java.io.*;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import gui.����̨.����̨1��;
import gui.δ����.���;
import gui.����̨.�ű�������;
import gui.����̨.�����¼��ʾ;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import server.Timer.*;
import server.custom.forum.Forum_Section;
import server.events.MapleOxQuizFactory;
import server.life.MapleLifeFactory;
import server.maps.MapleMapFactory;
import server.quest.MapleQuest;
import tools.FileoutputUtil;
import tools.MaplePacketCreator;
import server.AutobanManager;
import server.CashItemFactory;
import server.ItemMakerFactory;
import server.MTSStorage;
import server.MapleCarnivalFactory;
import server.MapleItemInformationProvider;
import server.PredictCardFactory;
import server.RandomRewards;
import server.ServerProperties;
import server.ShutdownServer;
import server.SpeedRunner;
import server.Timer;
import server.custom.bossrank3.BossRankManager3;
import tools.packet.UIPacket;
import static gui.QQͨ��.ͨ��;
import static a.�÷���ȫ.����ȡ�㲥;
import static abc.Game.����;
import static gui.QQͨ��.OX����;
import static gui.QQͨ��.Ⱥ֪ͨ;
import static gui.�Ұ��ͨ��.���ͨ��;
import static gui.����BOSS.����BOSS�߳�.��������BOSS�߳�;
import java.util.Scanner;
import static server.MapleCarnivalChallenge.getJobNameById;
import static tools.FileoutputUtil.CurrentReadable_Time;

public class Start {

    public static boolean Check = true;
    public static final Start instance = new Start();
    private static int maxUsers = 0;
    public static ZEVMS2 CashGui;
    public static ����̨1�� ����˹��ܿ���;
    public static ��� ���;
    public static �����¼��ʾ ��Ϣ���;
    public static �ű������� ����֪ͨ;
    public static DebugWindow DebugWindow;
    public static Map<String, Integer> ConfigValuesMap = new HashMap<>();
    public static Map<String, Integer> ��ͼ���ּ�� = new HashMap<>();
    public static Map<String, Integer> ���ܷ�Χ��� = new HashMap<>();
    public static Map<String, Integer> PVP�����˺� = new HashMap<>();
    public static Map<String, Integer> ������Ϣ���� = new HashMap<>();

    public void startServer() throws InterruptedException, SocketException, UnsupportedEncodingException, IOException {
        MapleParty.ÿ������ = 0;
        long start = System.currentTimeMillis();
        ���ݿ����ݵ���();
        GetConfigValues();
        Check = false;
        System.out.println("\r\n�� ��ʼ����ZEVMS�����");
        System.out.println("�� ��Ϸ���� : " + MapleParty.��������);
        System.out.println("�� ��Ϸ��ַ : " + MapleParty.IP��ַ);
        try {
            try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE accounts SET loggedin = 0")) {
                ps.executeUpdate();
            }
            try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE accounts SET lastGainHM = 0")) {
                ps.executeUpdate();
            }
            try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characterz SET Point = 0 WHERE channel = 202")) {
                ps.executeUpdate();
            }
            try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characterz SET Point = 0 WHERE channel = 201")) {
                ps.executeUpdate();
            }
            try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characterz SET Point = 0 WHERE channel = 200")) {
                ps.executeUpdate();
            }
            try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characterz SET Point = 0 WHERE channel = 500")) {
                ps.executeUpdate();
            }
            try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characterz SET Point = 0 WHERE channel = 501")) {
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("[��������ֵ����] Please check if the SQL server is active.");
        }
        System.out.println("�� ��ʼ���ظ�������");
        World.init();
        System.out.println("�� ��ʼ����ʱ���߳�");
        ʱ���߳�();
        System.out.println("�� ��ʼ��Ϸ�̳�");
        LoginInformationProvider.getInstance();
        System.out.println("�� ��ʼ����������Ϣ1");
        MapleQuest.initQuests();
        System.out.println("�� ��ʼ����������Ϣ2");
        MapleLifeFactory.loadQuestCounts();
        System.out.println("�� ��ʼ���ر�����Ʒ1");
        ItemMakerFactory.getInstance();
        System.out.println("�� ��ʼ���ر�����Ʒ2");
        MapleItemInformationProvider.getInstance().load();
        System.out.println("�� ��ʼ����ˢ���߳�");
        World.registerRespawn();
        System.out.println("�� ��ʼ���ر���ˢ���߳�");
        World.registerRespawn2();
        System.out.println("�� ��ʼ���ط�Ӧ��1");
        System.out.println("�� ��ʼ���ط�Ӧ��2");
        RandomRewards.getInstance();
        System.out.println("�� ��ʼ���ؼ�����Ϣ");
        SkillFactory.getSkill(99999999);
        System.out.println("�� ��ʼ���ػ��Ϣ");
        MapleOxQuizFactory.getInstance().initialize();
        MapleCarnivalFactory.getInstance();
        System.out.println("�� ��ʼ���ؼ�����Ϣ");
        MapleGuildRanking.getInstance().RankingUpdate();
        System.out.println("�� ��ʼ����ѧԺ��Ϣ");
        MapleFamilyBuff.getBuffEntry();
        new Thread(QQMsgServer.getInstance()).start();
        System.out.println("�� ��ʼ����װ����ħ��Ϣ");
        GetFuMoInfo();
        System.out.println("�� ��ʼ�����Ʒ���������");
        GetCloudBacklist();
        System.out.println("�� ��ʼ���ؼ��ܷ�Χ���");
        ��ȡ���ܷ�Χ���();
        System.out.println("�� ��ʼ���ص�ͼ���ּ��");
//        ��ȡ��ͼ���ּ��();
        System.out.println("�� ��ʼ����PVP��������");
        ��ȡ����PVP�˺�();
        System.out.println("�� ��ʼ���ظ���������Ϣ");
        ��ȡ��������Ϣ����();
        ������������();
        System.out.println("�� ��ʼ������Ϸ��̳");
        System.out.println("�� ��ʼ������Ϸ����");
        System.out.println("�� ��ʼ���ش��͵�����");
        Forum_Section.loadAllSection();
        System.out.println("�� ��ʼ���ص�½������");
        //MapleServerHandler.registerMBean();
        MTSStorage.load();
        PredictCardFactory.getInstance().initialize();
        CashItemFactory.getInstance().initialize();
        System.out.println("�� ��ʼ������Ϸ������Ϣ");
        LoginServer.run_startup_configurations();
        //���﹥��
        //RespawnManager.getInstance().run();
        System.out.println("�� ��ʼ������ϷƵ����Ϣ");
        ChannelServer.startChannel_Main();
        LoginServer.setOn();
        System.out.println("�� ��ʼ�����½�NPCλ����Ϣ");
        MapleMapFactory.loadCustomLife();
        System.out.println("�� ��ʼ������Ϸ�̳���Ʒ");
        CashShopServer.run_startup_configurations();
        CheatTimer.getInstance().register(AutobanManager.getInstance(), 60000);
        //new ZevmsLauncherServer(60000).start();
        //�����ǵ�����Ʒ��
        //ChannelServer.getInstance(1).getMapFactory().getMap(749030000).spawnRandDrop();
        //WorldTimer.getInstance().register(new World.Respawn(), 10000);
        Runtime.getRuntime().addShutdownHook(new Thread(new Shutdown()));
        try {
            SpeedRunner.getInstance().loadSpeedRuns();
        } catch (SQLException e) {
            System.out.println("SpeedRunner����:" + e);
        }
        WorldTimer.getInstance().register(DatabaseConnection.CloseSQLConnections, 10 * 60 * 1000);//18
        System.out.println("�� �̳�: " + CurrentReadable_Time() + " ��ȡ�̳��ϼ�ʱװ���");
        //��Ϸ������ʾ
        System.out.println("�� ��Ϸ������Ϣ");
        //��ʾ���鱶�ʣ����ܳ���100��
        if (Integer.parseInt(ServerProperties.getProperty("ZEV.Exp")) > 100) {
            System.out.println("�� ��Ϸ���鱶��: 100 �� (����)");
        } else {
            System.out.println("�� ��Ϸ���鱶��: " + Integer.parseInt(ServerProperties.getProperty("ZEV.Exp")) + " �� ");
        }
        //��ʾ��Ʒ���ʣ����ܳ���100��
        if (Integer.parseInt(ServerProperties.getProperty("ZEV.Drop")) > 100) {
            System.out.println("�� ��Ϸ��Ʒ���ʣ�100 �� (����)");
        } else {
            System.out.println("�� ��Ϸ��Ʒ���ʣ�" + Integer.parseInt(ServerProperties.getProperty("ZEV.Drop")) + " �� ");
        }
        //��ʾ��ұ��ʣ����ܳ���100��
        if (Integer.parseInt(ServerProperties.getProperty("ZEV.Meso")) > 100) {
            System.out.println("�� ��Ϸ��ұ��ʣ�100 �� (����)");
        } else {
            System.out.println("�� ��Ϸ��ұ��ʣ�" + Integer.parseInt(ServerProperties.getProperty("ZEV.Meso")) + " �� ");
        }
        System.out.println("�� �����Զ��浵�߳�");
        �Զ��浵(5);
        System.out.println("�� ������ɫ�����ݵ��߳�");
        �����ݵ�(30);
        System.out.println("�� ������Ӷ�����ݵ��߳�");
        �����ݵ�2(30);
        System.out.println("�� �������ݿ�ͨ���߳�");
        ��ʱ��ѯ(3);
        System.out.println("�� ����������ڴ�����߳�");
        //�����ڴ�(30);
        System.out.println("�� ������¼����ʱ���߳�");
        System.out.println("�� ������¼����ʱ�������߳�");
        ��¼����ʱ��(1);
        //���յ�ͼ(60 * 3);
        System.out.println("�� ������Ϸ���沥���߳�");
        ����(20);
        MapleParty.���������ʱ�� = 0;
        System.out.println("�� �����ƶ���֤IP��ַ�߳�");
        ͳ����������(22);
        ������˰汾(60);
        System.out.println("�� ͬ���ƶ������޸��ļ���");
        ����ͬ���޸�();
        System.out.println("\r\n");
        System.out.println("�� [�����˺�����]: " + �������˺�() + " ��");
        System.out.println("�� [���ؽ�ɫ����]: " + ��������ɫ() + " ��");
        System.out.println("�� [���ص�������]: " + ����������() + " ��");
        System.out.println("�� [���ؼ�������]: " + ����������() + " ��");
        System.out.println("�� [������Ϸ��Ʒ����]: " + ��������Ϸ��Ʒ() + " ��");
        System.out.println("�� [�����̳���Ʒ����]: " + �������̳���Ʒ() + " ��");
        //��ʾ������ɵ�ʱ��
        long now = System.currentTimeMillis() - start;
        long seconds = now / 1000;
        System.out.println("�� �����������ʱ: " + seconds + " ��");
        System.out.println("\r\n");
        System.out.println("�� ��������������̨");
        CashShopServer();
        //��������BOSS�߳�();
        qingkongguyong();

    }

    public static void qingkongguyong() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM hiredmerch");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ��Ӷд��(rs.getInt("characterid"), 1, rs.getInt("Mesos"));
            }
            ps.close();
        } catch (SQLException ex) {
        }

        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM hiredmerchantshop");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("hiredmerchantshop " + e);
        }
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM hiredmerchequipment");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("hiredmerchequipment " + e);
        }
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM hiredmerchitems");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("hiredmerchitems " + e);
        }
    }
    /**
     * * <1����ִ��һ��>
     */
    private static int ��¼����ʱ�� = 0;
    private static int �������ݿ� = 0;
    private static Boolean ÿ���ͻ� = false;
    private static Boolean ϲ���콵 = false;
    private static Boolean �������� = false;
    private static int ��ʼͨ���� = 0;
    private static Boolean ���ʻ = false;
    private static Boolean ����ְҵ = false;
    private static Boolean ����OX�����߳� = false;
    private static Boolean ħ������ = false;
    private static Boolean isClearBossLog = false;
    private static Boolean ħ�幥�� = false;
    private static int Z = 0;

    public static void ��¼����ʱ��(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (��¼����ʱ�� > 0) {
                    MapleParty.���������ʱ�� += 1;
                    Calendar calendar = Calendar.getInstance();
                    int ʱ = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                    int �� = Calendar.getInstance().get(Calendar.MINUTE);
                    int ���� = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                    /**
                     * <�賿����ÿ����Ϣ>
                     */
                    if (ʱ == 0 && isClearBossLog == false) {
                        System.err.println("[�����]" + CurrentReadable_Time() + " : ------------------------------");
                        System.err.println("[�����]" + CurrentReadable_Time() + " : ����˿�ʼ����ÿ����Ϣ ��");
                        ͨ��("����˿�ʼ����ÿ����Ϣ");
                        try {
                            //���ý�������ʱ��
                            try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characters SET todayOnlineTime = 0")) {
                                ps.executeUpdate();
                                System.err.println("[�����]" + CurrentReadable_Time() + " : �����������ʱ����� ��");
                            }
                            //����ÿ��bosslog��Ϣ
                            try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE bosslog SET characterid = 0")) {
                                ps.executeUpdate();
                                System.err.println("[�����]" + CurrentReadable_Time() + " : �������log��Ϣ��� ��");
                            }
                            //����ÿ��bosslog��Ϣ
                            try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE accounts SET df_tired_point = 0")) {
                                ps.executeUpdate();
                                System.err.println("[�����]" + CurrentReadable_Time() + " : �������ƣ����� ��");
                            }
                            System.err.println("[�����]" + CurrentReadable_Time() + " : ���������ÿ����Ϣ��� ��");
                            ͨ��("���������ÿ����Ϣ���");
                            System.err.println("[�����]" + CurrentReadable_Time() + " : ------------------------------");
                        } catch (SQLException ex) {
                            System.err.println("[�����]" + CurrentReadable_Time() + " : ����˴���ÿ�����ݳ��� �� " + ex.getMessage());
                            ͨ��("����˴���ÿ�����ݳ��������Ҫ�ֶ�����һ�¡�");
                            System.err.println("[�����]" + CurrentReadable_Time() + " : ------------------------------");
                        }
                        isClearBossLog = true;
                        ÿ���ͻ� = false;
                        ����OX�����߳� = false;
                        ħ������ = false;
                        ħ�幥�� = false;
                        ϲ���콵 = false;
                        �������� = false;
                        MapleParty.OX���� = 0;
                        /*if (�������ݿ� == 1) {
                            �������ݿ�();
                        } else {
                            �������ݿ�++;
                        }*/
                    } else if (ʱ == 23) {
                        isClearBossLog = false;
                    }
                    /**
                     * <��������>
                     */
                    if (gui.Start.ConfigValuesMap.get("������������") == 0) {
                        if (ʱ == 21 && �� >= 30 && �������� == false) {
                            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                                    mch.startMapEffect("[��������]: ˮ������ 2 Ƶ�� 1 ���Ӻ�ʼϲ�������������", 5120027);
                                }
                            }
                            QQͨ��.Ⱥ֪ͨ("[��������]: ˮ������ 2 Ƶ�� 1 ���Ӻ�ʼϲ�������������");
                            new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1000 * 60);
                                        ���������.��������();
                                    } catch (InterruptedException e) {
                                    }
                                }
                            }.start();
                            �������� = true;
                        }
                    }
                    /**
                     * <ϲ���콵>
                     */
                    if (gui.Start.ConfigValuesMap.get("ϲ���콵����") == 0) {
                        if (���� == 1 || ���� == 7) {
                            if (ʱ == 22 && �� == 30 && ϲ���콵 == false) {
                                for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                                    for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                                        mch.startMapEffect("[ϲ���콵]: �г� 2 Ƶ�� 1 ���Ӻ�ʼϲ���콵���", 5120027);
                                    }
                                }
                                QQͨ��.Ⱥ֪ͨ("[ϲ���콵]: �г� 2 Ƶ�� 1 ���Ӻ�ʼϲ���콵���");
                                new Thread() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1000 * 60);
                                            �ϲ���콵.ϲ���콵();
                                        } catch (InterruptedException e) {
                                        }
                                    }
                                }.start();

                                ϲ���콵 = true;
                            }
                        }
                    }
                    /**
                     * <ÿ���ͻ�>
                     */
                    if (gui.Start.ConfigValuesMap.get("ÿ���ͻ�����") == 0) {
                        if (ʱ == 12 && ÿ���ͻ� == false) {
                            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                                    mch.startMapEffect("[ÿ���ͻ�]: �ͻ��������������ͻ�Ա�����Կ�ʼ�ͻ�Ŷ�����Ի�÷��Ľ�����", 5120027);
                                }
                            }
                            QQͨ��.Ⱥ֪ͨ("[ÿ���ͻ�]: �ͻ��������������ͻ�Ա�����Կ�ʼ�ͻ�Ŷ�����Ի�÷��Ľ�����");
                            ÿ���ͻ� = true;
                        }
                    }
                    /**
                     * <OX����>
                     */
                    if (gui.Start.ConfigValuesMap.get("OX���⿪��") == 0) {
                        if (ʱ == 20 && ����OX�����߳� == false) {
                            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                                    mch.startMapEffect("[ÿ�մ���]: 10 ���Ӻ󽫾��� OX �����������Ҫ�μӵ�С��飬���һ�򵼲μӰɡ�", 5120027);
                                }
                            }
                            System.err.println("[�����]" + CurrentReadable_Time() + " : ÿ�ջ OX���� �߳��Ѿ�����");
                            OX����("[ÿ�ջ]: 10 ���Ӻ󽫾��� OX �����������Ҫ�μӵ�С��飬���һ�򵼲μӰɡ�");
                            �OX����.OX�����߳�();
                            MapleParty.OX����++;
                            ����OX�����߳� = true;
                        }
                    }
                    /**
                     * <ħ������>
                     */
                    if (gui.Start.ConfigValuesMap.get("ħ��ͻϮ����") == 0) {
                        if (calendar.get(Calendar.HOUR_OF_DAY) == 22 && ħ������ == false) {
                            �ħ������.ħ�������߳�();
                            ħ������ = true;
                        }
                    }
                    /**
                     * <ħ�幥��>
                     */
                    if (gui.Start.ConfigValuesMap.get("ħ�幥�ǿ���") == 0) {
                        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 1) {
                            if (ʱ == 21 && �� <= 10 && ħ�幥�� == false) {
                                �ħ�幥��.ħ�幥���߳�();
                                ħ�幥�� = true;
                            }
                        }
                    }
                    /**
                     * <����ְҵ����ѡ֮��-����>
                     */
                    if (gui.Start.ConfigValuesMap.get("����ְҵ����") == 0) {
                        if (ʱ == 11 && ����ְҵ == false) {
                            ����ְҵ();
                            ����ְҵ = true;
                        } else if (ʱ == 23 && ����ְҵ == true) {
                            ����ְҵ();
                            ����ְҵ = false;
                        } else if (MapleParty.����ְҵ == 0) {
                            ����ְҵ();
                        }
                    }
                    /**
                     * <��ĩ������ʻ>
                     */
                    if (gui.Start.ConfigValuesMap.get("��ĩ���ʿ���") == 0) {
                        switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
                            case 7:
                                if (ʱ == 0 && ���ʻ == false) {
                                    ����ʻ.���ʻ�߳�();
                                    ���ʻ = true;
                                } else if (ʱ == 23) {
                                    ���ʻ = false;
                                }
                                break;
                            case 1:
                                if (ʱ == 0 && ���ʻ == false) {
                                    ����ʻ.���ʻ�߳�();
                                    ���ʻ = true;
                                } else if (ʱ == 23) {
                                    ���ʻ = false;
                                }
                                break;
                            case 6:
                                if (���ʻ == true) {
                                    ���ʻ = false;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    /**
                     * <������������>
                     */
                    if (gui.Start.ConfigValuesMap.get("�������˿���") == 0) {
                        //��һ��������������
                        if (MapleParty.���������߳� == 0) {
                            ���������.������������();
                            MapleParty.���������߳�++;
                        }
                        //�ٻ���������
                        if (MapleParty.���������߳� > 0) {
                            if (ʱ == MapleParty.��������ʱ�� && MapleParty.�������� == 0) {
                                ���������.�ٻ���������();
                            }
                        }

                    }
                    /**
                     * <��ʼ��ͨ����>
                     */
                    if (gui.Start.ConfigValuesMap.get("Ұ��ͨ������") == 0) {
                        if (��ʼͨ���� == 30) {
                            ���ͨ��();
                            ��ʼͨ����++;
                        } else {
                            ��ʼͨ����++;
                        }
                    }

                    /**
                     * <��¼����ʱ��>
                     */
                    Z = 0;
                    for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                        for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                            if (chr == null) {
                                continue;
                            }
                            chr.���ӽ�ɫƣ��ֵ(1);
                            Connection con = DatabaseConnection.getConnection();
                            try {
                                /**
                                 * <������>
                                 */
                                try (PreparedStatement psu = con.prepareStatement("UPDATE characters SET todayOnlineTime = todayOnlineTime + ?, totalOnlineTime = totalOnlineTime + ? WHERE id = ?")) {
                                    psu.setInt(1, time);
                                    psu.setInt(2, time);
                                    psu.setInt(3, chr.getId());
                                    psu.executeUpdate();
                                    psu.close();
                                }
                                chr.getClient().sendPacket(MaplePacketCreator.enableActions());
                                if (Z == 0) {
                                    Z++;
                                    System.err.println("[�����]" + CurrentReadable_Time() + " : ��¼���� ��");
                                }
                            } catch (SQLException ex) {
                                System.err.println("[�����]" + CurrentReadable_Time() + " : ��¼���� �� (" + chr.getId() + ")");//+ ex.getMessage()
                                //ͨ��("��� [" + ��ɫIDȡ����(chr.getId()) + "] ��¼���ߴ������ڿ�ʼ���ȡ�");
                                ��¼����ʱ�䲹��(chr.getId());
                            }

                        }
                    }
                } else {
                    ��¼����ʱ��++;
                }
            }
        }, 60 * 1000 * time
        );
    }

    public static void �������ݿ�() {
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ϵͳ֪ͨ] : Ϊ��֤�����������ϵͳ�������������棬Ԥ�� 1 ����������ϣ����̻ᵼ����Ϸ���٣��������ߡ�"));
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ϵͳ֪ͨ] : Ϊ��֤�����������ϵͳ�������������棬Ԥ�� 1 ����������ϣ����̻ᵼ����Ϸ���٣��������ߡ�"));
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ϵͳ֪ͨ] : Ϊ��֤�����������ϵͳ�������������棬Ԥ�� 1 ����������ϣ����̻ᵼ����Ϸ���٣��������ߡ�"));
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ϵͳ֪ͨ] : Ϊ��֤�����������ϵͳ�������������棬Ԥ�� 1 ����������ϣ����̻ᵼ����Ϸ���٣��������ߡ�"));
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                chr.saveToDB(false, false);
            }
        }
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 5);
                    �رշ���("MySQL5");
                } catch (InterruptedException e) {
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 10);
                    ��������("MySQL5");
                } catch (InterruptedException e) {
                }
            }
        }.start();
    }

    public static void ����ְҵ() {
        int ��� = (int) Math.ceil(Math.random() * 18);
        if (��� == 0) {
            ��� += 1;
        }
        switch (���) {
            case 1:
                MapleParty.����ְҵ = 111;
                break;
            case 2:
                MapleParty.����ְҵ = 121;
                break;
            case 3:
                MapleParty.����ְҵ = 131;
                break;
            case 4:
                MapleParty.����ְҵ = 211;
                break;
            case 5:
                MapleParty.����ְҵ = 221;
                break;
            case 6:
                MapleParty.����ְҵ = 231;
                break;
            case 7:
                MapleParty.����ְҵ = 311;
                break;
            case 8:
                MapleParty.����ְҵ = 321;
                break;
            case 9:
                MapleParty.����ְҵ = 411;
                break;
            case 10:
                MapleParty.����ְҵ = 421;
                break;
            case 11:
                MapleParty.����ְҵ = 511;
                break;
            case 12:
                MapleParty.����ְҵ = 521;
                break;
            case 13:
                MapleParty.����ְҵ = 1111;
                break;

            case 14:
                MapleParty.����ְҵ = 1211;
                break;
            case 15:
                MapleParty.����ְҵ = 1311;
                break;
            case 16:
                MapleParty.����ְҵ = 1411;
                break;
            case 17:
                MapleParty.����ְҵ = 1511;
                break;
            case 18:
                MapleParty.����ְҵ = 2111;
                break;
            default:

                break;
        }
        String ��Ϣ = "��ϲ " + getJobNameById((MapleParty.����ְҵ - 1)) + " " + getJobNameById(MapleParty.����ְҵ) + " " + getJobNameById((MapleParty.����ְҵ + 1)) + " ���˳�Ϊ����ְҵ������50%�������Ծ���";
        Ⱥ֪ͨ("[����ְҵ] : " + ��Ϣ);
        System.err.println("[�����]" + CurrentReadable_Time() + " : [����ְҵ] : " + ��Ϣ);
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[����ְҵ] : " + ��Ϣ));

    }

    private static int ��ʱ��ѯ = 0;
    /**
     * <3���Ӽ��һ�����ݿ�>
     * <ÿ3���Ӽ��һ�����ݿ�ͨ����Ϣ����������쳣��֪ͨ���ˣ�3���Ӻ���������쳣�����˿���ѡ���ֶ�������ͨ��ָ���������ݿ�>
     * <������ָ� *�������ݿ�>
     */
    public static int �쳣���� = 0;

    public static void ��ʱ��ѯ(final int time) {
        Timer.WorldTimer.getInstance().register(() -> {
            if (��ʱ��ѯ > 0) {
                try {
                    try (PreparedStatement psu = DatabaseConnection.getConnection().prepareStatement("SELECT COUNT(id) FROM accounts WHERE loggedin > 0")) {
                        psu.execute();
                        psu.close();
                        System.err.println(LogConstants.SERVER + CurrentReadable_Time() + " : ������ݿ�ͨ�� ��");
                        if (�쳣���� > 0) {
                            ͨ��("��������ݿ�ͨ���ѻָ�");
                            �쳣���� = 0;
                        }
                    }
                } catch (SQLException ex) {
                    System.err.println(LogConstants.SERVER + CurrentReadable_Time() + " : ������ݿ�ͨ�� �� " + ex.getMessage());
                    if (�쳣���� >= 5) {
                        ͨ��("��������ݿ�ͨ�������쳣���뼰ʱ�������ݿ�");
                    } else {
                        ͨ��("��������ݿ�ͨ���쳣�����������ʾ����Ϣ�����������ݿ�");
                        �쳣����++;
                    }
                }
            } else {
                ��ʱ��ѯ++;
            }
        }, 60 * 1000 * time);
    }

    /**
     * * <30�����ݵ�һ��>
     */
    public static int �����ݵ� = 0;

    public static void �����ݵ�(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (�����ݵ� > 0) {
                    try {
                        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                                if (chr == null) {
                                    continue;
                                }
                                if (MapleParty.OX���� == 0) {
                                    if (chr.level > 10) {
                                        int ��ȯ = 0;
                                        int ���� = 0;
                                        int ��� = 0;
                                        int ���� = 0;
                                        int �ݵ��ҿ��� = gui.Start.ConfigValuesMap.get("�ݵ��ҿ���");
                                        if (�ݵ��ҿ��� <= 0) {
                                            int �ݵ��� = gui.Start.ConfigValuesMap.get("�ݵ���");
                                            ��� += �ݵ���;
                                            if (chr.getEquippedFuMoMap().get(34) != null) {
                                                ��� += �ݵ��� / 100 * chr.getEquippedFuMoMap().get(34);
                                            }
                                            chr.gainMeso(�ݵ���, true);
                                        }
                                        int �ݵ��ȯ���� = gui.Start.ConfigValuesMap.get("�ݵ��ȯ����");
                                        if (�ݵ��ȯ���� <= 0) {
                                            int �ݵ��ȯ = gui.Start.ConfigValuesMap.get("�ݵ��ȯ");
                                            chr.modifyCSPoints(1, �ݵ��ȯ, true);
                                            ��ȯ += �ݵ��ȯ;
                                        }
                                        int �ݵ���ÿ��� = gui.Start.ConfigValuesMap.get("�ݵ���ÿ���");
                                        if (�ݵ���ÿ��� <= 0) {
                                            int �ݵ���� = gui.Start.ConfigValuesMap.get("�ݵ����");
                                            chr.modifyCSPoints(2, �ݵ����, true);
                                            ���� += �ݵ����;
                                        }
                                        int �ݵ㾭�鿪�� = gui.Start.ConfigValuesMap.get("�ݵ㾭�鿪��");
                                        if (�ݵ㾭�鿪�� <= 0) {
                                            int �ݵ㾭�� = gui.Start.ConfigValuesMap.get("�ݵ㾭��");
                                            ���� += �ݵ㾭��;
                                            if (chr.getEquippedFuMoMap().get(33) != null) {
                                                ���� += �ݵ㾭�� / 100 * chr.getEquippedFuMoMap().get(33);
                                            }
                                            if (chr.Getcharactera("���ִ己�ٶ�", 1) > 0) {
                                                ���� += �ݵ㾭�� * chr.Getcharactera("���ִ己�ٶ�", 1) * 0.000001;
                                            }
                                            chr.gainExp(����, false, false, false);
                                        }
                                        BossRankManager3.getInstance().setLog(chr.getId(), chr.getName(), "�ݵ㾭��", (byte) 2, (byte) 1);
                                        chr.getClient().sendPacket(UIPacket.getTopMsg("[" + MapleParty.�������� + "�ݵ�]:" + ��ȯ + " ��ȯ / " + ���� + " ���� / " + ���� + " ���� / " + ��� + " ��ң��ݵ㾭�� + 1"));
                                        chr.getClient().sendPacket(MaplePacketCreator.enableActions());
                                    }
                                }
                            }
                        }
                        System.err.println("[�����]" + CurrentReadable_Time() + " : ϵͳ���ڷ����ݵ� �� ");
                    } catch (Exception e) {
                        System.err.println("[�����]" + CurrentReadable_Time() + " : ϵͳ�������ñ��÷����ݵ� �� ");
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000 * 10);
                                    �����ݵ�();
                                } catch (InterruptedException e) {
                                }
                            }
                        }.start();
                    }
                } else {
                    �����ݵ�++;
                }
            }
        }, 60 * 1000 * time);
    }

    /**
     * * <���÷��͸����ݵ�>
     */
    public static void �����ݵ�() {
        try {
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                    if (chr == null) {
                        continue;
                    }
                    if (chr.level > 10) {
                        int ��ȯ = 0;
                        int ���� = 0;
                        int ��� = 0;
                        int ���� = 0;
                        int �ݵ��ҿ��� = gui.Start.ConfigValuesMap.get("�ݵ��ҿ���");
                        if (�ݵ��ҿ��� <= 0) {
                            int �ݵ��� = gui.Start.ConfigValuesMap.get("�ݵ���");
                            ��� += �ݵ���;
                            if (chr.getEquippedFuMoMap().get(34) != null) {
                                ��� += �ݵ��� / 100 * chr.getEquippedFuMoMap().get(34);
                            }
                            chr.gainMeso(�ݵ���, true);
                        }
                        int �ݵ��ȯ���� = gui.Start.ConfigValuesMap.get("�ݵ��ȯ����");
                        if (�ݵ��ȯ���� <= 0) {
                            int �ݵ��ȯ = gui.Start.ConfigValuesMap.get("�ݵ��ȯ");
                            chr.modifyCSPoints(1, �ݵ��ȯ, true);
                            ��ȯ += �ݵ��ȯ;
                        }
                        int �ݵ���ÿ��� = gui.Start.ConfigValuesMap.get("�ݵ���ÿ���");
                        if (�ݵ���ÿ��� <= 0) {
                            int �ݵ���� = gui.Start.ConfigValuesMap.get("�ݵ����");
                            chr.modifyCSPoints(2, �ݵ����, true);
                            ���� += �ݵ����;
                        }
                        int �ݵ㾭�鿪�� = gui.Start.ConfigValuesMap.get("�ݵ㾭�鿪��");
                        if (�ݵ㾭�鿪�� <= 0) {
                            int �ݵ㾭�� = gui.Start.ConfigValuesMap.get("�ݵ㾭��");
                            ���� += �ݵ㾭��;
                            if (chr.getEquippedFuMoMap().get(33) != null) {
                                ���� += �ݵ㾭�� / 100 * chr.getEquippedFuMoMap().get(33);
                            }
                            chr.gainExp(�ݵ㾭��, true, true, false);
                        }
                        BossRankManager3.getInstance().setLog(chr.getId(), chr.getName(), "�ݵ㾭��", (byte) 2, (byte) 1);
                        chr.getClient().sendPacket(UIPacket.getTopMsg("[" + MapleParty.�������� + "�ݵ�]:" + ��ȯ + " ��ȯ / " + ���� + " ���� / " + ���� + " ���� / " + ��� + " ��ң��ݵ㾭�� + 1"));
                        chr.getClient().sendPacket(MaplePacketCreator.enableActions());
                    }
                }
            }
            System.err.println("[�����]" + CurrentReadable_Time() + " : ϵͳ���ڷ����ݵ� ���� ");
        } catch (Exception e) {
            System.err.println("[�����]" + CurrentReadable_Time() + " : ϵͳ���ڷ����ݵ� ���� ");
        }
    }

    /**
     * * <30���ӹ�Ӷ�ݵ�һ��>
     */
    public static int �����ݵ�2 = 0;

    public static void �����ݵ�2(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (�����ݵ�2 > 0) {

                    int ID = 0;
                    try {
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
                                //д����
                                ��Ӷд��(i, 1, gui.Start.ConfigValuesMap.get("��Ӷ�ݵ���"));
                                //д���ȯ
                                ��Ӷд��(i, 2, gui.Start.ConfigValuesMap.get("��Ӷ�ݵ��ȯ"));
                            }
                        }
                        System.err.println("[�����]" + CurrentReadable_Time() + " : ϵͳ���ڷ��Ź�Ӷ�ݵ� �� ");
                    } catch (SQLException | NumberFormatException e) {
                        System.err.println("[�����]" + CurrentReadable_Time() + " : ϵͳ�������ñ��÷����ݵ� x ");
                    }
                } else {
                    �����ݵ�2++;
                }
            }
        }, 1000 * 60 * time);
    }
    /**
     * * <5���Ӵ浵һ��>
     */
    private static int �Զ��浵 = 0;

    public static void �Զ��浵(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (�Զ��浵 > 0) {
                    try {
                        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                                if (chr == null) {
                                    continue;
                                }
                                chr.saveToDB(false, false);
                                chr.getClient().sendPacket(MaplePacketCreator.enableActions());
                                if (chr.getSession() != null && !chr.getClient().getSession().isActive()) {
                                    System.err.println("[�����]" + CurrentReadable_Time() + " : ������ʱ���Ͽ�����(" + chr.getClient().getSession().isActive()+ " �� " + chr.getId() + ") ��");
                                    chr.getClient().getSession().close();
                                }
                            }
                        }
                        System.err.println("[�����]" + CurrentReadable_Time() + " : ϵͳ�����Զ��浵�����Ҽ������ ��");
                    } catch (Exception e) {
                        System.err.println("[�����]" + CurrentReadable_Time() + " : ϵͳ�������ñ����Զ��浵 ��");
                    }
                } else {
                    �Զ��浵++;
                }
            }
        }, 60 * 1000 * time);
    }
    private static int ���յ�ͼ = 0;

    public static void ���յ�ͼ(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (���յ�ͼ > 0) {
                    try {
                        try {
                            Connection con = DatabaseConnection.getConnection();
                            PreparedStatement ps = con.prepareStatement("SELECT * FROM map  ");
                            ResultSet rs = ps.executeQuery();
                            while (rs.next()) {
                                if (rs.getInt("id") < 910000000 || rs.getInt("id") > 910000024) {
                                    for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                                        if (cserv == null) {
                                            continue;
                                        }
                                        cserv.getMapFactory().destroyMap(rs.getInt("id"), true);
                                        cserv.getMapFactory().HealMap(rs.getInt("id"));
                                    }
                                }
                            }
                            ps.close();
                        } catch (SQLException ex) {
                        }
                        System.err.println("[�����]" + CurrentReadable_Time() + " : ϵͳ���ڻ��յ�ͼ ��");
                    } catch (Exception e) {
                        System.err.println("[�����]" + CurrentReadable_Time() + " : ϵͳ���ڻ��յ�ͼ ��");
                    }
                } else {
                    ���յ�ͼ++;
                }
            }
        }, 60 * 1000 * time);
    }

    /**
     * * <�����Զ��浵>
     */
    public static void �Զ��浵() {
        try {
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                    if (chr == null) {
                        continue;
                    }
                    chr.saveToDB(false, false);
                    chr.getClient().sendPacket(MaplePacketCreator.enableActions());
                }
            }
        } catch (Exception e) {
        }
    }

    public static void ��¼����ʱ�䲹��(int a) {
        Connection con = DatabaseConnection.getConnection();
        try {
            try (PreparedStatement psu = con.prepareStatement("UPDATE characters SET todayOnlineTime = todayOnlineTime + ?, totalOnlineTime = totalOnlineTime + ? WHERE id = ?")) {
                psu.setInt(1, 1);
                psu.setInt(2, 1);
                psu.setInt(3, a);
                psu.executeUpdate();
                psu.close();
            }
            //ͨ��("��� [" + ��ɫIDȡ����(a) + "] ���ȳɹ���");
            System.err.println("[�����]" + CurrentReadable_Time() + " : ��¼���ߵ�һ�β��ȳɹ� �� (" + a + ")");
        } catch (SQLException ex) {
            System.err.println("[�����]" + CurrentReadable_Time() + " : ��¼���ߵ�һ�β���ʧ�� �� (" + a + ")" + ex.getMessage());
            ��¼����ʱ�䲹��2(a);
        }
    }

    public static void ��¼����ʱ�䲹��2(int a) {
        Connection con = DatabaseConnection.getConnection();
        try {
            try (PreparedStatement psu = con.prepareStatement("UPDATE characters SET todayOnlineTime = todayOnlineTime + ?, totalOnlineTime = totalOnlineTime + ? WHERE id = ?")) {
                psu.setInt(1, 1);
                psu.setInt(2, 1);
                psu.setInt(3, a);
                psu.executeUpdate();
                psu.close();
            }
            //ͨ��("��� [" + ��ɫIDȡ����(a) + "] ���ȳɹ���");
            System.err.println("[�����]" + CurrentReadable_Time() + " : ��¼���ߵڶ��β��ȳɹ� �� (" + a + ")");
        } catch (SQLException ex) {
            System.err.println("[�����]" + CurrentReadable_Time() + " : ��¼���ߵڶ��β���ʧ�� �� (" + a + ")" + ex.getMessage());
            ��¼����ʱ�䲹��3(a);
        }
    }

    public static void ��¼����ʱ�䲹��3(int a) {
        Connection con = DatabaseConnection.getConnection();
        try {
            try (PreparedStatement psu = con.prepareStatement("UPDATE characters SET todayOnlineTime = todayOnlineTime + ?, totalOnlineTime = totalOnlineTime + ? WHERE id = ?")) {
                psu.setInt(1, 1);
                psu.setInt(2, 1);
                psu.setInt(3, a);
                psu.executeUpdate();
                psu.close();
            }
            //ͨ��("��� [" + ��ɫIDȡ����(a) + "] ���ȳɹ���");
            System.err.println("[�����]" + CurrentReadable_Time() + " : ��¼���ߵ����β��ȳɹ� �� (" + a + ")");
        } catch (SQLException ex) {
            System.err.println("[�����]" + CurrentReadable_Time() + " : ��¼���ߵ����β���ʧ�� �� (" + a + ")" + ex.getMessage());

        }
    }
    /**
     * * <30����ǿ�ƻ���һ���ڴ�>
     */
    private static int �����ڴ� = 0;

    public static void �����ڴ�(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (�����ڴ� > 0) {
                    System.gc();
                    System.err.println("[�����]" + CurrentReadable_Time() + " : ���շ�����ڴ� ��");
                } else {
                    �����ڴ�++;
                }
            }
        }, 60 * 1000 * time);
    }

    /**
     * * <30���Ӽ���һ��IP�����>
     */
    private static int ��ϵ��֤ = 0;

    public static void ��ϵ��֤2(int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (��ϵ��֤ > 0) {
                    if (��ϵ��֤(MapleParty.�����˺�) == null ? "" + MapleParty.IP��ַ + "" != null : !��ϵ��֤(MapleParty.�����˺�).equals("" + MapleParty.IP��ַ + "")) {
                        System.out.println("[�����]" + CurrentReadable_Time() + " : �󶨵�ַ�����仯������� 1 ���Ӻ󽫻�ر� ��");
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000 * 60);
                                    System.exit(0);
                                } catch (InterruptedException e) {
                                }
                            }
                        }.start();
                    } else {
                        System.err.println("[�����]" + CurrentReadable_Time() + " : �������֤�ƶ˰�IP��ַ ��");
                    }
                } else {
                    ��ϵ��֤ += 1;
                }
            }
        }, 1000 * 60 * time);
    }

    /**
     * * <60���Ӽ���һ�η���˰汾>
     */
    private static int ������˰汾 = 0;
    private static int ������˰汾1 = 0;

    public static void ������˰汾(int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (������˰汾 > 0 && ������˰汾1 == 0) {
                    String v2 = Class2.Pgv();
                    if (Integer.parseInt(v2) != �汾) {
                        if (������˰汾1 == 0) {
                            ͨ��("��������°汾�����㼰ʱ���¡�");
                            ������˰汾1++;
                        }
                        System.err.println("[�����]" + CurrentReadable_Time() + " : ����������°汾�����㼰ʱ���� ��");
                    } else {
                        System.err.println("[�����]" + CurrentReadable_Time() + " : ������˰汾������˵�ǰ�����°汾 ��");
                    }
                } else {
                    ������˰汾++;
                }
            }
        }, 1000 * 60 * time);
    }
    /**
     * * <60����ͳ��һ�������������>
     */
    private static int ͳ������������� = 0;
    private static int ����������� = 0;
    private static int �����������2 = 0;

    public static void ͳ����������(int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (ͳ������������� > 0) {
                    int p = 0;
                    try {
                        Connection con = DatabaseConnection.getConnection();
                        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE loggedin > 0"); ResultSet rs = ps.executeQuery()) {
                            while (rs.next()) {
                                p += 1;
                            }
                            ps.close();
                        }
                    } catch (SQLException Ex) {
                        System.err.println("[�����]" + CurrentReadable_Time() + " : ͳ��������߳��� ��");
                    }
                    ����������� = p;
                    if (����������� > �����������2) {
                        ����������� = 0;
                        �����������2 = p;
                    }
                    if (�����������2 > ����������� && �����������2 > 0) {
                        System.err.println("[�����]" + CurrentReadable_Time() + " : ͳ�ƴ˴�������� �� ����: " + �����������2);
                    }
                } else {
                    ͳ�������������++;
                }
            }
        }, 1000 * 60 * time);
    }

    /**
     * * <ѭ�����Ź���> @param time
     *
     * @param time
     */
    public static void ����(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                String ���� = ����ȡ�㲥();
                if (!"".equals(����)) {
                    World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, " : " + ����));
                }
            }
        }, time * 1000 * 60);
    }

    /**
     * * <����>
     */
    public static void GetConfigValues() {
        //��̬���ݿ�����
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement("SELECT name, val FROM ConfigValues")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    int val = rs.getInt("val");
                    ConfigValuesMap.put(name, val);
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ȡ��̬���ݿ����" + ex.getMessage());
        }
    }

    public static void ��ȡ��ͼ���ּ��() {
        //��̬���ݿ�����
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement("SELECT name, val FROM ��ͼ���ּ��")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    int val = rs.getInt("val");
                    ��ͼ���ּ��.put(name, val);
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ȡ���ּ�����" + ex.getMessage());
        }
    }

    public static void GetFuMoInfo() {
        FuMoInfoMap.clear();
        System.out.println("�� ��ʼ���ظ�ħװ��Ч��");
        //����//
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement("SELECT fumoType, fumoName, fumoInfo FROM mxmxd_fumo_info")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int fumoType = rs.getInt("fumoType");
                    String fumoName = rs.getString("fumoName");
                    String fumoInfo = rs.getString("fumoInfo");
                    FuMoInfoMap.put(fumoType, new String[]{fumoName, fumoInfo});
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("�� ���ظ�ħװ��Ч��Ԥ��ʧ�ܡ�");
        }
    }

    public static void ��ȡ���ܷ�Χ���() {
        //��̬���ݿ�����
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement("SELECT name, val FROM ���ܷ�Χ���")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    int val = rs.getInt("val");
                    ���ܷ�Χ���.put(name, val);
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ȡ���ּ�����" + ex.getMessage());
        }
    }

    public static void ��ȡ����PVP�˺�() {
        //��̬���ݿ�����
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement("SELECT name, val FROM pvpskills")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    int val = rs.getInt("val");
                    PVP�����˺�.put(name, val);
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ȡ����PVP�˺�����" + ex.getMessage());
        }
    }

    public static void ��ȡ��������Ϣ����() {
        //��̬���ݿ�����
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement("SELECT name, val FROM jiezoudashi")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    int val = rs.getInt("val");
                    ������Ϣ����.put(name, val);
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("��ȡ������Ϣ���ô���" + ex.getMessage());
        }
    }

    public static void ��������Ϣ() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress().toString(); //�@ȡ���Cip
            String hostName = addr.getHostName().toString(); //�@ȡ���CӋ��C���Q
            //System.out.println("���CIP��"+ip+"\n���C���Q:"+hostName);
            System.out.println("�� ����������л���");
            System.out.println("�� ��������: " + hostName);
            Properties �O���n = System.getProperties();
            System.out.println("�� ����ϵͳ��" + �O���n.getProperty("os.name"));
            System.out.println("�� ϵͳ��ܣ�" + �O���n.getProperty("os.arch"));
            System.out.println("�� ϵͳ�汾��" + �O���n.getProperty("os.version"));
            System.out.println("�� �����Ŀ¼��" + �O���n.getProperty("user.dir"));
            System.out.println("�� ����˻���������");
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public static class Shutdown implements Runnable {

        @Override
        public void run() {
            new Thread(ShutdownServer.getInstance()).start();
        }
    }

    public static void ����֪ͨ() {
        if (����֪ͨ != null) {
            ����֪ͨ.dispose();
        }
        ����֪ͨ = new �ű�������();
        ����֪ͨ.setVisible(true);
    }

    public static void CashShopServer() {
        if (Start.CashGui != null) {
            Start.CashGui.dispose();
        }
        Start.CashGui = new ZEVMS2();
        Start.CashGui.setVisible(true);
    }

    public static void ������̬����̨() {
        if (Start.����˹��ܿ��� != null) {
            Start.����˹��ܿ���.dispose();
        }
        Start.����˹��ܿ��� = new ����̨1��();
        Start.����˹��ܿ���.setVisible(true);
    }

    public static void ���() {
        if (Start.��� != null) {
            Start.���.dispose();
        }
        Start.��� = new ���();
        Start.���.setVisible(true);
    }

    public static void ��Ϣ���() {
        if (Start.��Ϣ��� != null) {
            Start.��Ϣ���.dispose();
        }
        Start.��Ϣ��� = new �����¼��ʾ();
        Start.��Ϣ���.setVisible(true);
    }

    public static void CashShopServer2() {
        if (Start.DebugWindow != null) {
            Start.DebugWindow.dispose();
        }
        Start.DebugWindow = new DebugWindow();
        Start.DebugWindow.setVisible(true);
    }

    public static Map<Integer, String[]> FuMoInfoMap = new HashMap<>();

    public static Map<String, String> CloudBlacklist = new HashMap<>();

    public static void GetCloudBacklist() {
        CloudBlacklist.clear();
        try {
            String backlistStr = Class2.Pgb();
            if (backlistStr.contains("/")) {
                String blacklistArr[] = backlistStr.split(",");
                for (int i = 0; i < blacklistArr.length; i++) {
                    String pairString = blacklistArr[i];
                    String pair[] = pairString.split("/");
                    String qq = pair[0];
                    String ip = "/" + pair[1];
                    CloudBlacklist.put(qq, ip);
                }
            }
        } catch (Exception ex) {
            System.err.println("��ȡ�ƺ���������" + ex.getMessage());
        }
    }

    public static boolean ���Ϸ�() {
        //����Ƿ�����ָ������
        ByteArrayOutputStream baos = null;
        InputStream os = null;
        String s = "";
        try {
            Process p = Runtime.getRuntime().exec("cmd /c tasklist");
            baos = new ByteArrayOutputStream();
            os = p.getInputStream();
            byte b[] = new byte[256];
            while (os.read(b) > 0) {
                baos.write(b);
            }
            s = baos.toString();
            if (s.indexOf("zevms079.exe") >= 0) {
            } else {
                System.out.println("����˼�⵽ȱ���ļ������Զ��رա�");
                System.exit(0);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int ȡװ������(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemid as DATA FROM inventoryitems WHERE inventoryitemid = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ȡװ�����롢����");
        }
        return data;
    }

    public static int ȡװ��ӵ����(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT characterid as DATA FROM inventoryitems WHERE inventoryitemid = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ȡװ�����롢����");
        }
        return data;
    }

    public static int ���������() {
        int p = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT meso as DATA FROM characters ");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    p += rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�ϴ�����,���������,����");
        }
        return p;
    }

    public static String ��ɫIDȡ����(int id) {
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
            System.err.println("��ȡ��ɫIDȡ���ֳ��� - ���ݿ��ѯʧ�ܣ�" + Ex);
        }
        if (data == null) {
            data = "������ʿ";
        }
        return data;
    }

    public static int ��������ɫ() {
        int p = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id as DATA FROM characters WHERE id >=0");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    p += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��������ɫ��");
        }
        return p;
    }

    public static int �������˺�() {
        int p = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id as DATA FROM accounts WHERE id >=0");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    p += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�������˺ţ�");
        }
        return p;
    }

    public static int ����������() {
        int p = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id as DATA FROM skills ");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    p += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("���������ܣ�");
        }
        return p;
    }

    public static int ����������() {
        int p = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT inventoryitemid as DATA FROM inventoryitems WHERE inventoryitemid >=0");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    p += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("���������ߣ�");
        }
        return p;
    }

    public static int �������̳���Ʒ() {
        int p = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT serial as DATA FROM cashshop_modified_items WHERE serial >=0");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    p += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�������̳���Ʒ��");
        }
        return p;
    }

    public static int ��������Ϸ��Ʒ() {
        int p = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT shopitemid as DATA FROM shopitems WHERE shopitemid >=0");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    p += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("������������Ϸ��Ʒ��");
        }
        return p;
    }

    private static void ����ͬ���޸�() {
        try {
            �����ļ�("http://123.207.53.97:8082/�����޸�/�����޸�.zip", "�����޸�.zip", "" + �����������Ŀ¼() + "/");
            ��ѹ�ļ�.��ѹ�ļ�(������½�ѹĿ¼("�����޸�"), ������µ���Ŀ¼("zevms"));
            ɾ���ļ�(������½�ѹĿ¼("�����޸�"));
        } catch (Exception e) {

        }
    }

    protected static void checkCopyItemFromSql() {
        System.out.println("��������� ������ϵͳ�����ָ���װ��.����ɾ��������");
        List<Integer> equipOnlyIds = new ArrayList<>(); //[���ߵ�ΨһID��Ϣ]
        Map<Integer, Integer> checkItems = new HashMap<>(); //[����ΨһID ����ID]
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps;
            //��ȡ��⸴��װ��
            ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE equipOnlyId > 0");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int itemId = rs.getInt("itemId");
                int equipOnlyId = rs.getInt("equipOnlyId");
                if (equipOnlyId > 0) {
                    if (checkItems.containsKey(equipOnlyId)) { //�����ظ���ΨһIDװ��
                        if (checkItems.get(equipOnlyId) == itemId) {
                            equipOnlyIds.add(equipOnlyId);
                        }
                    } else {
                        checkItems.put(equipOnlyId, itemId);
                    }
                }
            }
            rs.close();
            ps.close();
            //ɾ�����и���װ����ΨһID��Ϣ
            Collections.sort(equipOnlyIds);
            for (int i : equipOnlyIds) {
                ps = con.prepareStatement("DELETE FROM inventoryitems WHERE equipOnlyId = ?");
                ps.setInt(1, i);
                ps.executeUpdate();
                ps.close();
                System.out.println("���ָ���װ�� ��װ����ΨһID: " + i + " �ѽ���ɾ������..");
                FileoutputUtil.log("װ������.txt", "���ָ���װ�� ��װ����ΨһID: " + i + " �ѽ���ɾ������..");
            }
        } catch (SQLException ex) {
            System.out.println("[EXCEPTION] ������װ�����ִ���." + ex);
        }
    }

    private void ʱ���߳�() {
        WorldTimer.getInstance().start();
        EtcTimer.getInstance().start();
        MapTimer.getInstance().start();
        MobTimer.getInstance().start();
        RespawnTimer.getInstance().start();
        CloneTimer.getInstance().start();
        EventTimer.getInstance().start();
        BuffTimer.getInstance().start();
    }

    public static String ��������(String string) {
        StringBuilder builder = new StringBuilder();
        try {
            // ���� cmd���ִ�� net start mysql�� /c ����Ҫ��
            Process p = Runtime.getRuntime().exec("cmd.exe /c net start " + string);
            InputStream inputStream = p.getInputStream();
            // ��ȡ����ִ����Ľ��
            Scanner scanner = new Scanner(inputStream, "GBK");
            scanner.useDelimiter("\\A");

            while (scanner.hasNext()) {
                builder.append(scanner.next());
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static String �رշ���(String string) {
        StringBuilder builder = new StringBuilder();
        try {
            // ���� cmd���ִ�� net start mysql�� /c ����Ҫ��
            Process p = Runtime.getRuntime().exec("cmd.exe /c net stop " + string);
            InputStream inputStream = p.getInputStream();

            // ��ȡ����ִ����Ľ��
            Scanner scanner = new Scanner(inputStream, "GBK");
            scanner.useDelimiter("\\A");

            while (scanner.hasNext()) {
                builder.append(scanner.next());
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void ������������() {
        int ID = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT `id` FROM characters  ORDER BY `id` DESC LIMIT 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String SN = rs.getString("id");
                    int sns = Integer.parseInt(SN);
                    sns++;
                    ID = sns;
                }
            }
        } catch (SQLException ex) {

        }
        for (int i = 0; i <= ID; i++) {
            if (gui.Start.������Ϣ����.get("����ģʽ" + i + "") == null) {
                ѧϰ����ģʽ("����ģʽ" + i, 1);
            }
            if (gui.Start.������Ϣ����.get("BUFF����" + i + "") == null) {
                ѧϰ����ģʽ("BUFF����" + i, 1);
            }
            if (gui.Start.������Ϣ����.get("Ӳ��Ƥ��" + i + "") == null) {
                ѧϰ����ģʽ("Ӳ��Ƥ��" + i, 1);
            }
            if (gui.Start.������Ϣ����.get("�������" + i + "") == null) {
                ѧϰ����ģʽ("�������" + i, 1);
            }
            if (gui.Start.������Ϣ����.get("��������" + i + "") == null) {
                ѧϰ����ģʽ("��������" + i, 1);
            }
            if (gui.Start.������Ϣ����.get("ħ��������" + i + "") == null) {
                ѧϰ����ģʽ("ħ��������" + i, 1);
            }
            if (gui.Start.������Ϣ����.get("�������" + i + "") == null) {
                ѧϰ����ģʽ("�������" + i, 1);
            }
            if (gui.Start.������Ϣ����.get("ħ������" + i + "") == null) {
                ѧϰ����ģʽ("ħ������" + i, 1);
            }
            if (gui.Start.������Ϣ����.get("����������" + i + "") == null) {
                ѧϰ����ģʽ("����������" + i, 1);
            }
            if (gui.Start.������Ϣ����.get("ħ��������" + i + "") == null) {
                ѧϰ����ģʽ("ħ��������" + i, 1);
            }
            /*if (gui.Start.������Ϣ����.get("��������" + i + "") == null) {
                ѧϰ����ģʽ("��������" + i, 1);
            }*/
        }
        ��ȡ��������Ϣ����();
    }

    public static void ѧϰ����ģʽ(String a, int b) {
        int ID = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT `id` FROM jiezoudashi  ORDER BY `id` DESC LIMIT 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String SN = rs.getString("id");
                    int sns = Integer.parseInt(SN);
                    sns++;
                    ID = sns;
                }
            }
        } catch (SQLException ex) {

        }
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO jiezoudashi ( id,name,Val ) VALUES ( ? ,?,?)")) {
            ps.setInt(1, ID);
            ps.setString(2, a);
            ps.setInt(3, b);
            ps.executeUpdate();
            ps.close();
            System.err.println("[�����]" + CurrentReadable_Time() + " : ���ݲ��� " + a);
        } catch (SQLException ex) {

        }
    }

    public static int ��ɫIDȡ�˺�ID(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT accountid as DATA FROM characters WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("��ɫ����ȡ�˺�ID������");
        }
        return data;
    }

    public static String �˺�IDȡ�˺�(int id) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM accounts WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�˺�IDȡ�˺š�����");
        }
        return data;
    }

    public static void ��Ӷд��(int Name, int Channale, int Piot) {
        try {
            int ret = Get��Ӷд��(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO hirex (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setInt(2, Name);
                    ps.setInt(3, ret);

                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("��Ӷд��1:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("��Ӷд��2:" + e);
                    }
                }
            }
            ret += Piot;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE hirex SET `Point` = ? WHERE Name = ? and channel = ?");
            ps.setInt(1, ret);
            ps.setInt(2, Name);
            ps.setInt(3, Channale);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("��Ӷд��3" + sql);
        }
    }

    public static int Get��Ӷд��(int Name, int Channale) {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM hirex WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setInt(2, Name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

}
