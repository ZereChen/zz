package gui;

import static a.�������ݿ�.�����������Ŀ¼;
import static a.�������ݿ�.������µ���Ŀ¼;
import static a.�������ݿ�.������½�ѹĿ¼;
import static a.�÷���ȫ.��������ʱ��;
import static a.�÷���ȫ.��ϵ��֤;
import static a.�÷���ȫ.�ж���������;
import client.MapleCharacter;
import client.inventory.Equip;
import client.inventory.ItemFlag;
import client.inventory.MapleInventoryType;
import constants.GameConstants;
import database.DatabaseConnection;
import static gui.ZEVMS.jButton1111;
import static gui.ZEVMS.����;
import static gui.ZEVMS.jButton11112;
import static gui.ZEVMS.jButton11113;
import static gui.ZEVMS.jButton11115;
import static gui.ZEVMS.����˹��ܿ���;
import static gui.ZEVMS.�ű��༭��2;
import gui.����̨.����̨3��;
import gui.����̨.�����̨;
import gui.����̨.����̨2��;
import gui.����̨.��ֵ����̨;
import gui.����̨.����̨1��;
import gui.����̨.�ű��༭��2;
import handling.channel.ChannelServer;
import handling.login.handler.AutoRegister;
import handling.world.World;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import provider.MapleData;
import provider.MapleDataProvider;
import provider.MapleDataProviderFactory;
import provider.MapleDataTool;
import scripting.PortalScriptManager;
import scripting.ReactorScriptManager;
import server.CashItemFactory;
import server.CashItemInfo;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.MapleShopFactory;
import server.life.MapleMonsterInformationProvider;
import tools.MaplePacketCreator;
import client.LoginCrypto;
import java.util.concurrent.ScheduledFuture;
import javax.swing.JDialog;
import javax.swing.UIManager;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import server.ShutdownServer;
import server.Timer;
import static handling.cashshop.handler.CashShopOperation.Gaincharacter7;
import static handling.cashshop.handler.CashShopOperation.Getcharacter7;
import handling.world.MapleParty;
import scripting.NPCConversationManager;
import static gui.QQMsgServer.sendMsgToQQGroup;
import static a.�÷���ȫ.��ȡ����ʱ��;
import static gui.ZEVMS2.���ÿ���̨;
import static abc.Game.���ڱ���;
import static abc.sancu.FileDemo_05.ɾ���ļ�;
import client.inventory.IItem;
import client.inventory.ItemLoader;
import database1.DatabaseConnection1;
import gui.Jieya.��ѹ�ļ�;
import static gui.Start.GetCloudBacklist;
import static gui.Start.��ȡ����PVP�˺�;
import static gui.ZEVMS.�����ļ�;
import static gui.ZEVMS.������;
import static server.MapleCarnivalChallenge.getJobNameById;
import gui.����.������Ⱥ�������;
import static gui.ZEVMS.����Ӧ��;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import server.MerchItemPackage;
import tools.Pair;

public final class ZEVMS2 extends javax.swing.JFrame {

    private static final MapleDataProvider Mobdata = MapleDataProviderFactory.getDataProvider(new File(System.getProperty("net.sf.odinms.wzpath") + "/Mob.wz"));
    private static final MapleDataProvider npcData = MapleDataProviderFactory.getDataProvider(new File(System.getProperty("net.sf.odinms.wzpath") + "/Npc.wz"));
    private static final MapleDataProvider stringDataWZ = MapleDataProviderFactory.getDataProvider(new File(System.getProperty("net.sf.odinms.wzpath") + "/String.wz"));
    private static final MapleDataProvider etcDataWZ = MapleDataProviderFactory.getDataProvider(new File(System.getProperty("net.sf.odinms.wzpath") + "/Etc.wz"));
    private static final MapleData mobStringData = stringDataWZ.getData("Mob.img");
    private static final MapleData npcStringData = stringDataWZ.getData("Npc.img");
    private static final MapleData npclocData = etcDataWZ.getData("NpcLocation.img");
    private static Object jButton11117;

    MapleDataProvider stringProvider = MapleDataProviderFactory.getDataProvider(new File(System.getProperty("net.sf.odinms.wzpath") + "/String.wz"));
    MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
    MapleData cash = stringProvider.getData("Cash.img");
    MapleData consume = stringProvider.getData("Consume.img");
    MapleData eqp = stringProvider.getData("Eqp.img").getChildByPath("Eqp");
    MapleData etc = stringProvider.getData("Etc.img").getChildByPath("Etc");
    MapleData ins = stringProvider.getData("Ins.img");
    MapleData pet = stringProvider.getData("Pet.img");
    MapleData map = stringProvider.getData("Map.img");
    MapleData mob = stringProvider.getData("Mob.img");
    MapleData skill = stringProvider.getData("Skill.img");
    MapleData npc = stringProvider.getData("Npc.img");

    private MapleDataProvider data = MapleDataProviderFactory.getDataProvider(new File(System.getProperty("net.sf.odinms.wzpath") + "/Etc.wz"));
    private MapleData commodities = this.data.getData("Commodity.img");

    public ZEVMS2() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        setIconImage(icon.getImage());
        Properties �O���n = System.getProperties();
        String �û����� = "";
        if (MapleParty.�������� == 999) {
            �û����� = "��Ȩ�û�";
        } else {
            �û����� = "����û�";
        }
        setTitle("" + ���ڱ��� + "  [������̨�����ɹر�]  [" + �û����� + "]");
        initComponents();
        initCharacterPannel();
        ˢ���̳�����۸�();
        ˢ���ݵ��ҿ���();
        ˢ���ݵ��ȯ����();
        ˢ���ݵ㾭�鿪��();
        ˢ���ݵ���ÿ���();
        ˢ���ݵ�����();
        ˢ���˺���Ϣ();
        ˢ�½�ɫ��Ϣ();
        ˢ����Ϣ();
        ˢ�¹�Ӷ�ݵ�����();
        MapleParty.����� = 0;

        MapleParty.�����������++;
        System.out.println("�� ������̨�������\r\n\r\n");
        //new ZevmsLauncherServer(60000).start();
        //sendMsgToQQGroup("[�������Ϣ]:����������ɹ�����ҿ���������½��Ϸ��");
    }

    private void ˢ����Ϣ() {
        ˢ���˺�();
        ˢ����������();
        ˢ���˻�����();
        ˢ����Ϸ����();
        ˢ�°󶨵�ַ();
    }

    private void ˢ�°󶨵�ַ() {
     //   �󶨵�ַ.setText(��ϵ��֤(MapleParty.�����˺�));
    }

    private void ˢ���˻�����() {
        if (MapleParty.�������� == 999) {
            ʣ��ʱ��.setText("[��Ȩ�û�] ������");
        } else {
            ʣ��ʱ��.setText("[����û�] " + ��ȡ����ʱ��(MapleParty.�����˺�));
        }

    }

    private void ˢ����Ϸ����() {
        ��Ϸ����.setText("" + MapleParty.�������� + "");
    }

    private void ˢ���˺�() {
        �����˺�.setText(MapleParty.�����˺�);
    }

    private void ˢ����������() {
        if (MapleParty.�������� == 999) {
            ��������.setText("[��Ȩ�û�] ������");
        } else {
            ��������.setText("[����û�] ������: " + �ж���������(MapleParty.�����˺�) + "");
        }
    }

    public void ɾ��SN(int a) {
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        try {
            ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM sn" + a + " WHERE SN = ?");
            ps2.setInt(1, Integer.parseInt(this.��Ʒ����.getText()));
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                String sqlstr = " delete from sn" + a + " where SN =" + Integer.parseInt(this.��Ʒ����.getText()) + ";";
                ps2.executeUpdate(sqlstr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ɾ��SN���() {
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        try {
            ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM character7 WHERE Name = ?");
            ps2.setInt(1, Integer.parseInt(this.��Ʒ����.getText()));
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                String sqlstr = " delete from character7 where Name =" + Integer.parseInt(this.��Ʒ����.getText()) + ";";
                ps2.executeUpdate(sqlstr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ɾ��SN���2() {
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        try {
            ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM character7 WHERE Name = ?  &&  channel = 2");
            ps2.setInt(1, Integer.parseInt(this.��Ʒ����.getText()));
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                String sqlstr = " delete from character7 where Name =" + Integer.parseInt(this.��Ʒ����.getText()) + ";";
                ps2.executeUpdate(sqlstr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ɾ��SN���3() {
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        try {
            ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM character7 WHERE Name = ?  &&  channel = 3");
            ps2.setInt(1, Integer.parseInt(this.��Ʒ����.getText()));
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                String sqlstr = " delete from character7 where Name =" + Integer.parseInt(this.��Ʒ����.getText()) + ";";
                ps2.executeUpdate(sqlstr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ɾ��SN���4() {
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        try {
            ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM character7 WHERE Name = ?  &&  channel = 4");
            ps2.setInt(1, Integer.parseInt(this.��Ʒ����.getText()));
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                String sqlstr = " delete from character7 where Name =" + Integer.parseInt(this.��Ʒ����.getText()) + ";";
                ps2.executeUpdate(sqlstr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        ��ҳ = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        ��ҳ1 = new javax.swing.JPanel();
        ��ҳLOGO = new javax.swing.JLabel();
        �ϴ����� = new javax.swing.JPanel();
        �����˺� = new javax.swing.JLabel();
        �����˺�1 = new javax.swing.JLabel();
        �������� = new javax.swing.JLabel();
        �ϴ�����ʱ��2 = new javax.swing.JLabel();
        �ϴ�����ʱ��3 = new javax.swing.JLabel();
        ʣ��ʱ�� = new javax.swing.JLabel();
        ��Ϸ���� = new javax.swing.JLabel();
        �����˺�3 = new javax.swing.JLabel();
        ������3 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        ��Ʒ���� = new javax.swing.JPanel();
        �̳���ʾ���� = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        charTable = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        �޳���״̬ = new javax.swing.JButton();
        NEW = new javax.swing.JButton();
        Sale = new javax.swing.JButton();
        HOT = new javax.swing.JButton();
        event = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        ��Ʒ���� = new javax.swing.JTextField();
        ��Ʒ���� = new javax.swing.JTextField();
        ��Ʒ���� = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        ��Ʒ�۸� = new javax.swing.JTextField();
        ��Ʒʱ�� = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        ��Ʒ��� = new javax.swing.JTextField();
        ��Ʒ�ۿ� = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        ÿ���޹� = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        �������� = new javax.swing.JTextField();
        jScrollPane132 = new javax.swing.JScrollPane();
        �̳�����۸� = new javax.swing.JTable();
        �̳�����۸��޸� = new javax.swing.JTextField();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        ����� = new javax.swing.JButton();
        ��ȡ������Ʒ = new javax.swing.JButton();
        � = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        ñ�� = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        ȹ�� = new javax.swing.JButton();
        Ь�� = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        ��ָ = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        ��� = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        ϲ����Ʒ = new javax.swing.JButton();
        ͨѶ��Ʒ = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        ��Ա�� = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        �����̵� = new javax.swing.JButton();
        ������ = new javax.swing.JButton();
        ��Ϸ = new javax.swing.JButton();
        Ч�� = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        ���� = new javax.swing.JButton();
        ������� = new javax.swing.JButton();
        ���� = new javax.swing.JButton();
        �޸ı�������۸� = new javax.swing.JButton();
        ��Ʒ����״̬ = new javax.swing.JTextField();
        ��ʾ���� = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        �޸� = new javax.swing.JButton();
        ��� = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        ˢ��ģʽ = new javax.swing.JButton();
        �˺��б� = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        �˺���Ϣ = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        ���� = new javax.swing.JTextField();
        �˺� = new javax.swing.JTextField();
        ��ȯ = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        �޸��˺ŵ�ȯ���� = new javax.swing.JButton();
        �˺�ID = new javax.swing.JTextField();
        jLabel206 = new javax.swing.JLabel();
        jLabel312 = new javax.swing.JLabel();
        ����1 = new javax.swing.JTextField();
        jLabel353 = new javax.swing.JLabel();
        QQ = new javax.swing.JTextField();
        jLabel357 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        ע����˺� = new javax.swing.JTextField();
        ע������� = new javax.swing.JTextField();
        jButton35 = new javax.swing.JButton();
        jLabel111 = new javax.swing.JLabel();
        jLabel201 = new javax.swing.JLabel();
        jButton30 = new javax.swing.JButton();
        �˺���ʾ���� = new javax.swing.JLabel();
        ˢ���˺���Ϣ = new javax.swing.JButton();
        �����˺� = new javax.swing.JButton();
        ��� = new javax.swing.JButton();
        �ѷ��˺� = new javax.swing.JButton();
        �����˺� = new javax.swing.JButton();
        ɾ���˺� = new javax.swing.JButton();
        �����˺� = new javax.swing.JButton();
        �⿨ = new javax.swing.JButton();
        �˺Ų��� = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        ��ʾ�����˺� = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        ���QQ��� = new javax.swing.JButton();
        ��ɫ�б� = new javax.swing.JPanel();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        ��ɫ��Ϣ1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ��ɫ��Ϣ = new javax.swing.JTable();
        ˢ�½�ɫ��Ϣ = new javax.swing.JButton();
        ��ʾ�����ɫ = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        ɾ����ɫ = new javax.swing.JButton();
        ��ɫ�ǳ� = new javax.swing.JTextField();
        �ȼ� = new javax.swing.JTextField();
        ���� = new javax.swing.JTextField();
        ���� = new javax.swing.JTextField();
        ���� = new javax.swing.JTextField();
        ���� = new javax.swing.JTextField();
        HP = new javax.swing.JTextField();
        MP = new javax.swing.JTextField();
        ��� = new javax.swing.JTextField();
        ��ͼ = new javax.swing.JTextField();
        GM = new javax.swing.JTextField();
        jLabel182 = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        jLabel184 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        jLabel186 = new javax.swing.JLabel();
        jLabel187 = new javax.swing.JLabel();
        jLabel189 = new javax.swing.JLabel();
        jLabel190 = new javax.swing.JLabel();
        jLabel191 = new javax.swing.JLabel();
        jLabel192 = new javax.swing.JLabel();
        jLabel193 = new javax.swing.JLabel();
        ��ɫID = new javax.swing.JTextField();
        �����Ծ�1 = new javax.swing.JButton();
        �����Ծ�2 = new javax.swing.JButton();
        jLabel203 = new javax.swing.JLabel();
        �鿴���� = new javax.swing.JButton();
        �鿴���� = new javax.swing.JButton();
        �������� = new javax.swing.JButton();
        ���� = new javax.swing.JTextField();
        ���� = new javax.swing.JTextField();
        jLabel214 = new javax.swing.JLabel();
        ���߽�ɫ = new javax.swing.JButton();
        ���߽�ɫ = new javax.swing.JButton();
        ��ʾ������� = new javax.swing.JLabel();
        ��ɫ���� = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel36 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        ��ɫ�������� = new javax.swing.JTable();
        ������Ʒ����1 = new javax.swing.JTextField();
        ���ϴ������1 = new javax.swing.JTextField();
        ������Ʒ����1 = new javax.swing.JTextField();
        jLabel276 = new javax.swing.JLabel();
        jLabel283 = new javax.swing.JLabel();
        jLabel287 = new javax.swing.JLabel();
        ɾ������װ�� = new javax.swing.JButton();
        jPanel37 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        ��ɫװ������ = new javax.swing.JTable();
        װ��������Ʒ���� = new javax.swing.JTextField();
        װ��������Ʒ��� = new javax.swing.JTextField();
        װ��������Ʒ���� = new javax.swing.JTextField();
        jLabel288 = new javax.swing.JLabel();
        jLabel289 = new javax.swing.JLabel();
        jLabel290 = new javax.swing.JLabel();
        ɾ��װ������ = new javax.swing.JButton();
        jPanel38 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        ��ɫ���ı��� = new javax.swing.JTable();
        ���ı�����Ʒ���� = new javax.swing.JTextField();
        ���ı�����Ʒ��� = new javax.swing.JTextField();
        ���ı�����Ʒ���� = new javax.swing.JTextField();
        jLabel291 = new javax.swing.JLabel();
        jLabel292 = new javax.swing.JLabel();
        jLabel293 = new javax.swing.JLabel();
        ɾ�����ı��� = new javax.swing.JButton();
        jPanel39 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        ��ɫ���ñ��� = new javax.swing.JTable();
        ���ñ�����Ʒ���� = new javax.swing.JTextField();
        ���ñ�����Ʒ��� = new javax.swing.JTextField();
        ���ñ�����Ʒ���� = new javax.swing.JTextField();
        jLabel294 = new javax.swing.JLabel();
        jLabel295 = new javax.swing.JLabel();
        jLabel296 = new javax.swing.JLabel();
        ɾ�����ñ��� = new javax.swing.JButton();
        jPanel40 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        ��ɫ�������� = new javax.swing.JTable();
        ����������Ʒ���� = new javax.swing.JTextField();
        ����������Ʒ��� = new javax.swing.JTextField();
        ����������Ʒ���� = new javax.swing.JTextField();
        jLabel297 = new javax.swing.JLabel();
        jLabel298 = new javax.swing.JLabel();
        jLabel299 = new javax.swing.JLabel();
        ɾ���������� = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        ��ɫ���ⱳ�� = new javax.swing.JTable();
        ���ⱳ����Ʒ���� = new javax.swing.JTextField();
        ���ⱳ����Ʒ��� = new javax.swing.JTextField();
        ���ⱳ����Ʒ���� = new javax.swing.JTextField();
        jLabel300 = new javax.swing.JLabel();
        jLabel301 = new javax.swing.JLabel();
        jLabel302 = new javax.swing.JLabel();
        ɾ�����ⱳ�� = new javax.swing.JButton();
        jPanel42 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        ��ɫ��Ϸ�ֿ� = new javax.swing.JTable();
        ��Ϸ�ֿ���Ʒ���� = new javax.swing.JTextField();
        ��Ϸ�ֿ���Ʒ��� = new javax.swing.JTextField();
        ��Ϸ�ֿ���Ʒ���� = new javax.swing.JTextField();
        jLabel303 = new javax.swing.JLabel();
        jLabel304 = new javax.swing.JLabel();
        jLabel305 = new javax.swing.JLabel();
        ɾ����Ϸ�ֿ� = new javax.swing.JButton();
        jPanel43 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        ��ɫ�̳ǲֿ� = new javax.swing.JTable();
        �̳ǲֿ���Ʒ���� = new javax.swing.JTextField();
        �̳ǲֿ���Ʒ��� = new javax.swing.JTextField();
        �̳ǲֿ���Ʒ���� = new javax.swing.JTextField();
        jLabel306 = new javax.swing.JLabel();
        jLabel307 = new javax.swing.JLabel();
        jLabel308 = new javax.swing.JLabel();
        ɾ���̳ǲֿ� = new javax.swing.JButton();
        jPanel48 = new javax.swing.JPanel();
        jScrollPane30 = new javax.swing.JScrollPane();
        ��ɫ��ȯ������ = new javax.swing.JTable();
        ��������Ʒ����1 = new javax.swing.JTextField();
        ��ɫ��ȯ��������� = new javax.swing.JTextField();
        ��������Ʒ����1 = new javax.swing.JTextField();
        jLabel354 = new javax.swing.JLabel();
        jLabel355 = new javax.swing.JLabel();
        jLabel356 = new javax.swing.JLabel();
        ɾ��������1 = new javax.swing.JButton();
        jPanel44 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        ��ɫ��������� = new javax.swing.JTable();
        ��������Ʒ���� = new javax.swing.JTextField();
        ��ɫ������������ = new javax.swing.JTextField();
        ��������Ʒ���� = new javax.swing.JTextField();
        jLabel309 = new javax.swing.JLabel();
        jLabel310 = new javax.swing.JLabel();
        jLabel311 = new javax.swing.JLabel();
        ɾ�������� = new javax.swing.JButton();
        ���� = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        ������Ϣ = new javax.swing.JTable();
        ���ܴ��� = new javax.swing.JTextField();
        ����Ŀǰ�ȼ� = new javax.swing.JTextField();
        ������ߵȼ� = new javax.swing.JTextField();
        �������� = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        �޸ļ��� = new javax.swing.JButton();
        ������� = new javax.swing.JTextField();
        jLabel188 = new javax.swing.JLabel();
        jLabel204 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        ɾ������ = new javax.swing.JButton();
        �޸ļ���1 = new javax.swing.JButton();
        ��ɫ��ʾ���� = new javax.swing.JLabel();
        ������ = new javax.swing.JPanel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane27 = new javax.swing.JScrollPane();
        ��������� = new javax.swing.JTable();
        ˢ�½�������� = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane28 = new javax.swing.JScrollPane();
        ��ȯ������ = new javax.swing.JTable();
        ˢ�½��������1 = new javax.swing.JButton();
        ���͸��� = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        ������¼���� = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        ȫ��������Ʒ���� = new javax.swing.JTextField();
        ȫ��������Ʒ���� = new javax.swing.JTextField();
        ������Ʒ1 = new javax.swing.JButton();
        jLabel217 = new javax.swing.JLabel();
        jLabel234 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        ȫ������װ��װ���Ӿ� = new javax.swing.JTextField();
        ȫ������װ��װ�������� = new javax.swing.JTextField();
        ȫ������װ��װ������ = new javax.swing.JTextField();
        ȫ������װ��װ��MP = new javax.swing.JTextField();
        ȫ������װ��װ������ = new javax.swing.JTextField();
        ȫ������װ��װ������ = new javax.swing.JTextField();
        ȫ������װ��װ��HP = new javax.swing.JTextField();
        ȫ������װ��װ�������� = new javax.swing.JTextField();
        ȫ������װ��װ������ʱ�� = new javax.swing.JTextField();
        ȫ������װ��װ���ɷ��� = new javax.swing.JTextField();
        ȫ������װ��װ������ = new javax.swing.JTextField();
        ȫ������װ����ƷID = new javax.swing.JTextField();
        ȫ������װ��װ��ħ���� = new javax.swing.JTextField();
        ȫ������װ��װ��ħ������ = new javax.swing.JTextField();
        ȫ������װ��װ��������� = new javax.swing.JTextField();
        ����װ��1 = new javax.swing.JButton();
        jLabel219 = new javax.swing.JLabel();
        jLabel220 = new javax.swing.JLabel();
        jLabel221 = new javax.swing.JLabel();
        jLabel222 = new javax.swing.JLabel();
        jLabel223 = new javax.swing.JLabel();
        jLabel224 = new javax.swing.JLabel();
        jLabel225 = new javax.swing.JLabel();
        jLabel226 = new javax.swing.JLabel();
        jLabel227 = new javax.swing.JLabel();
        jLabel228 = new javax.swing.JLabel();
        jLabel229 = new javax.swing.JLabel();
        jLabel230 = new javax.swing.JLabel();
        jLabel231 = new javax.swing.JLabel();
        jLabel232 = new javax.swing.JLabel();
        jLabel233 = new javax.swing.JLabel();
        ����װ��������� = new javax.swing.JTextField();
        ����װ��2 = new javax.swing.JButton();
        jLabel246 = new javax.swing.JLabel();
        jLabel244 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        z2 = new javax.swing.JButton();
        z3 = new javax.swing.JButton();
        z1 = new javax.swing.JButton();
        z4 = new javax.swing.JButton();
        z5 = new javax.swing.JButton();
        z6 = new javax.swing.JButton();
        a1 = new javax.swing.JTextField();
        jLabel235 = new javax.swing.JLabel();
        ������ʾ���� = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        ���˷�����Ʒ���� = new javax.swing.JTextField();
        ���˷�����Ʒ������� = new javax.swing.JTextField();
        ���˷�����Ʒ���� = new javax.swing.JTextField();
        ������Ʒ = new javax.swing.JButton();
        jLabel240 = new javax.swing.JLabel();
        jLabel241 = new javax.swing.JLabel();
        jLabel242 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        �洢������Ʒ = new javax.swing.JTable();
        ˢ�¸��� = new javax.swing.JButton();
        ˢ��ͨ�� = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        ������� = new javax.swing.JTextField();
        �������� = new javax.swing.JButton();
        ����ͨ�� = new javax.swing.JButton();
        ɾ��ͨ�� = new javax.swing.JButton();
        ɾ������ = new javax.swing.JButton();
        �޸ļ�¼ = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane134 = new javax.swing.JScrollPane();
        �����ݵ����� = new javax.swing.JTable();
        �ݵ���� = new javax.swing.JTextField();
        �ݵ����� = new javax.swing.JTextField();
        �ݵ�ֵ = new javax.swing.JTextField();
        �ݵ�ֵ�޸� = new javax.swing.JButton();
        jLabel322 = new javax.swing.JLabel();
        jLabel323 = new javax.swing.JLabel();
        jLabel324 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        �ݵ��ҿ��� = new javax.swing.JButton();
        �ݵ㾭�鿪�� = new javax.swing.JButton();
        �ݵ��ȯ���� = new javax.swing.JButton();
        �ݵ���ÿ��� = new javax.swing.JButton();
        jLabel325 = new javax.swing.JLabel();
        ������ʾ����2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane135 = new javax.swing.JScrollPane();
        ��Ӷ�����ݵ����� = new javax.swing.JTable();
        �ݵ����1 = new javax.swing.JTextField();
        jLabel326 = new javax.swing.JLabel();
        �ݵ�����1 = new javax.swing.JTextField();
        jLabel327 = new javax.swing.JLabel();
        �ݵ�ֵ1 = new javax.swing.JTextField();
        jLabel328 = new javax.swing.JLabel();
        �ݵ�ֵ�޸�1 = new javax.swing.JButton();
        jLabel329 = new javax.swing.JLabel();
        �������� = new javax.swing.JPanel();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        ������д = new javax.swing.JTextField();
        ���淢�����ȴ��� = new javax.swing.JTextField();
        jButton34 = new javax.swing.JButton();
        jLabel106 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel259 = new javax.swing.JLabel();
        ������ʾ���� = new javax.swing.JLabel();
        �������� = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        ������Ϣ = new javax.swing.JTable();
        ˢ�¼�����Ϣ = new javax.swing.JButton();
        jLabel194 = new javax.swing.JLabel();
        ����ID = new javax.swing.JTextField();
        �������� = new javax.swing.JTextField();
        jLabel195 = new javax.swing.JLabel();
        �����峤 = new javax.swing.JTextField();
        jLabel196 = new javax.swing.JLabel();
        jLabel197 = new javax.swing.JLabel();
        �����Ա1 = new javax.swing.JTextField();
        jLabel198 = new javax.swing.JLabel();
        �����Ա2 = new javax.swing.JTextField();
        jLabel199 = new javax.swing.JLabel();
        �����Ա3 = new javax.swing.JTextField();
        jLabel200 = new javax.swing.JLabel();
        �����Ա4 = new javax.swing.JTextField();
        jLabel313 = new javax.swing.JLabel();
        �����Ա5 = new javax.swing.JTextField();
        jLabel314 = new javax.swing.JLabel();
        ����GP = new javax.swing.JTextField();
        PVP���� = new javax.swing.JPanel();
        jScrollPane26 = new javax.swing.JScrollPane();
        PVP�������� = new javax.swing.JTable();
        ����PVP��� = new javax.swing.JTextField();
        ����PVPID = new javax.swing.JTextField();
        ����PVP�˺� = new javax.swing.JTextField();
        jLabel218 = new javax.swing.JLabel();
        jLabel236 = new javax.swing.JLabel();
        ˢ��PVP�б� = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        ����1 = new javax.swing.JButton();
        �޸�1 = new javax.swing.JButton();
        ����1 = new javax.swing.JButton();
        jLabel268 = new javax.swing.JLabel();
        jLabel269 = new javax.swing.JLabel();
        jLabel271 = new javax.swing.JLabel();
        jLabel272 = new javax.swing.JLabel();
        ���� = new javax.swing.JPanel();
        ���� = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        ���¼�¼ = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        ���¼�¼��ϸ = new javax.swing.JTextPane();
        jButton10 = new javax.swing.JButton();
        ��Ӷ��ɫID = new javax.swing.JPanel();
        ��ѯ = new javax.swing.JButton();
        jLabel270 = new javax.swing.JLabel();
        ��Ӷ��ɫID2 = new javax.swing.JTextField();
        ָ�� = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        ��Ǵ1���� = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        �������� = new javax.swing.JButton();
        �����Ӷ = new javax.swing.JButton();
        Ӧ������ = new javax.swing.JButton();
        �����̨ = new javax.swing.JButton();
        ��ֵ����̨ = new javax.swing.JButton();
        ��������̨ = new javax.swing.JButton();
        ����̨2�� = new javax.swing.JButton();
        ����̨1�� = new javax.swing.JButton();
        ����Ӧ�� = new javax.swing.JButton();
        �رշ���� = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        �ű��༭�� = new javax.swing.JButton();
        ������ = new javax.swing.JButton();
        ��������1 = new javax.swing.JButton();
        �����Ӷ1 = new javax.swing.JButton();
        Ӧ������1 = new javax.swing.JButton();
        �����̨1 = new javax.swing.JButton();
        ��ֵ����̨1 = new javax.swing.JButton();
        ��������̨1 = new javax.swing.JButton();
        ����̨2��1 = new javax.swing.JButton();
        ����̨1��1 = new javax.swing.JButton();
        ����Ӧ��1 = new javax.swing.JButton();
        �رշ����1 = new javax.swing.JButton();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jTabbedPane1.setFont(new java.awt.Font("������", 0, 16)); // NOI18N
        jTabbedPane1.setNextFocusableComponent(this);

        ��ҳ.setBackground(new java.awt.Color(255, 255, 255));
        ��ҳ.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ��ҳ.setFont(new java.awt.Font("������", 0, 15)); // NOI18N
        ��ҳ.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane6.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N

        ��ҳ1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ҳLOGO.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("http://123.207.53.97:8082/����˹��λ/ad1.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        ��ҳ1.add(��ҳLOGO, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -5, 1260, 660));

        jTabbedPane6.addTab("         ��ҳ         ", null, ��ҳ1, "");

        �ϴ�����.setBackground(new java.awt.Color(255, 255, 255));
        �ϴ�����.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�����˻���Ϣ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 1, 36), new java.awt.Color(255, 255, 255))); // NOI18N
        �ϴ�����.setForeground(new java.awt.Color(255, 255, 255));
        �ϴ�����.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �����˺�.setBackground(new java.awt.Color(255, 255, 255));
        �����˺�.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        �����˺�.setForeground(new java.awt.Color(255, 255, 255));
        �����˺�.setText("71447500");
        �ϴ�����.add(�����˺�, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 450, 30));

        �����˺�1.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        �����˺�1.setForeground(new java.awt.Color(255, 255, 255));
        �����˺�1.setText("|�û��˺�:");
        �ϴ�����.add(�����˺�1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 130, 30));

        ��������.setBackground(new java.awt.Color(255, 255, 255));
        ��������.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        ��������.setForeground(new java.awt.Color(255, 255, 255));
        ��������.setText("6");
        �ϴ�����.add(��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 730, 30));

        �ϴ�����ʱ��2.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        �ϴ�����ʱ��2.setForeground(new java.awt.Color(255, 255, 255));
        �ϴ�����ʱ��2.setText("|����ʱ�䣺");
        �ϴ�����.add(�ϴ�����ʱ��2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, 30));

        �ϴ�����ʱ��3.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        �ϴ�����ʱ��3.setForeground(new java.awt.Color(255, 255, 255));
        �ϴ�����ʱ��3.setText("|��������:");
        �ϴ�����.add(�ϴ�����ʱ��3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 130, 30));

        ʣ��ʱ��.setBackground(new java.awt.Color(255, 255, 255));
        ʣ��ʱ��.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        ʣ��ʱ��.setForeground(new java.awt.Color(255, 255, 255));
        ʣ��ʱ��.setText("1111");
        �ϴ�����.add(ʣ��ʱ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 730, 30));

        ��Ϸ����.setBackground(new java.awt.Color(255, 255, 255));
        ��Ϸ����.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        ��Ϸ����.setForeground(new java.awt.Color(255, 255, 255));
        ��Ϸ����.setText("����ð�յ�");
        �ϴ�����.add(��Ϸ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 440, 30));

        �����˺�3.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        �����˺�3.setForeground(new java.awt.Color(255, 255, 255));
        �����˺�3.setText("|��Ϸ����:");
        �ϴ�����.add(�����˺�3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 130, 30));

        ������3.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        ������3.setForeground(new java.awt.Color(255, 255, 255));
        ������3.setText("|�󶨵�ַ:");
        �ϴ�����.add(������3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 130, 30));

        jButton11.setText("ˢ��һ��");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        �ϴ�����.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 600, 100, -1));

        jTabbedPane6.addTab("         �˺���Ϣ         ", �ϴ�����);

        ��ҳ.add(jTabbedPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1255, 680));
        jTabbedPane6.getAccessibleContext().setAccessibleName("");

        jTabbedPane1.addTab("ZEVMS��ҳ", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ��ҳ); // NOI18N

        ��Ʒ����.setBackground(new java.awt.Color(255, 255, 255));
        ��Ʒ����.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ��Ʒ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ʒ����.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �̳���ʾ����.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        �̳���ʾ����.setText("[��Ϣ]:");
        ��Ʒ����.add(�̳���ʾ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 660, 25));

        charTable.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        charTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "��Ʒ����", "��Ʒ����", "��������", "����", "�۸�", "��ʱ/��", "����״̬", "��/�¼�", "���۳�", "���", "����/%", "ÿ���޹�"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        charTable.setToolTipText("");
        charTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(charTable);
        if (charTable.getColumnModel().getColumnCount() > 0) {
            charTable.getColumnModel().getColumn(0).setResizable(false);
            charTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            charTable.getColumnModel().getColumn(1).setResizable(false);
            charTable.getColumnModel().getColumn(1).setPreferredWidth(30);
            charTable.getColumnModel().getColumn(2).setResizable(false);
            charTable.getColumnModel().getColumn(2).setPreferredWidth(180);
            charTable.getColumnModel().getColumn(3).setResizable(false);
            charTable.getColumnModel().getColumn(3).setPreferredWidth(5);
            charTable.getColumnModel().getColumn(4).setResizable(false);
            charTable.getColumnModel().getColumn(4).setPreferredWidth(30);
            charTable.getColumnModel().getColumn(5).setResizable(false);
            charTable.getColumnModel().getColumn(5).setPreferredWidth(10);
            charTable.getColumnModel().getColumn(6).setResizable(false);
            charTable.getColumnModel().getColumn(6).setPreferredWidth(50);
            charTable.getColumnModel().getColumn(7).setResizable(false);
            charTable.getColumnModel().getColumn(7).setPreferredWidth(40);
            charTable.getColumnModel().getColumn(8).setResizable(false);
            charTable.getColumnModel().getColumn(8).setPreferredWidth(30);
            charTable.getColumnModel().getColumn(9).setResizable(false);
            charTable.getColumnModel().getColumn(9).setPreferredWidth(30);
            charTable.getColumnModel().getColumn(10).setResizable(false);
            charTable.getColumnModel().getColumn(10).setPreferredWidth(30);
            charTable.getColumnModel().getColumn(11).setResizable(false);
            charTable.getColumnModel().getColumn(11).setPreferredWidth(30);
        }

        ��Ʒ����.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 60, 1254, 500));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "����״̬", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �޳���״̬.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޳���״̬.setText("��");
        �޳���״̬.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޳���״̬ActionPerformed(evt);
            }
        });
        jPanel15.add(�޳���״̬, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 70, 25));

        NEW.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        NEW.setText("NEW");
        NEW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NEWActionPerformed(evt);
            }
        });
        jPanel15.add(NEW, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 55, 70, 25));

        Sale.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        Sale.setText("Sale");
        Sale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaleActionPerformed(evt);
            }
        });
        jPanel15.add(Sale, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 70, 25));

        HOT.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        HOT.setText("HOT");
        HOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HOTActionPerformed(evt);
            }
        });
        jPanel15.add(HOT, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 70, 25));

        event.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        event.setText("event");
        event.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventActionPerformed(evt);
            }
        });
        jPanel15.add(event, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 55, 140, 25));

        ��Ʒ����.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 230, 90));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "���ֵ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��Ʒ����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        jPanel16.add(��Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 55, 65, 20));

        ��Ʒ����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ��Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ʒ����ActionPerformed(evt);
            }
        });
        jPanel16.add(��Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 25, 65, 20));

        ��Ʒ����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        jPanel16.add(��Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 55, 65, -1));

        jLabel30.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel30.setText("��Ʒ������");
        jPanel16.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, 30));

        jLabel29.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel29.setText("��Ʒ���룻");
        jPanel16.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 30));

        ��Ʒ�۸�.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        jPanel16.add(��Ʒ�۸�, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 25, 65, -1));

        ��Ʒʱ��.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        jPanel16.add(��Ʒʱ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 55, 65, 20));

        jLabel32.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel32.setText("��Ʒ��棻");
        jPanel16.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 90, 30));

        jLabel33.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel33.setText("��ʱʱ�䣻");
        jPanel16.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, 30));

        jLabel34.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel34.setText("��Ʒ���룻");
        jPanel16.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 30));

        jLabel35.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel35.setText("��Ʒ�۸�");
        jPanel16.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 90, 30));

        ��Ʒ���.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        jPanel16.add(��Ʒ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 25, 65, -1));

        ��Ʒ�ۿ�.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        jPanel16.add(��Ʒ�ۿ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 25, 65, -1));

        jLabel37.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel37.setText("��Ʒ������");
        jPanel16.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 90, 30));

        jLabel36.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel36.setText("ÿ���޹���");
        jPanel16.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 90, 30));

        ÿ���޹�.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        jPanel16.add(ÿ���޹�, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 55, 65, -1));

        jLabel38.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel38.setText("�������ͣ�");
        jPanel16.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 90, 30));

        ��������.setEditable(false);
        ��������.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        jPanel16.add(��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 25, 65, -1));

        ��Ʒ����.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 560, 730, 90));

        �̳�����۸�.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �̳�����۸�.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "��������۸�"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        �̳�����۸�.getTableHeader().setReorderingAllowed(false);
        jScrollPane132.setViewportView(�̳�����۸�);
        if (�̳�����۸�.getColumnModel().getColumnCount() > 0) {
            �̳�����۸�.getColumnModel().getColumn(0).setResizable(false);
            �̳�����۸�.getColumnModel().getColumn(0).setPreferredWidth(50);
        }

        ��Ʒ����.add(jScrollPane132, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 570, 130, 50));

        �̳�����۸��޸�.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ��Ʒ����.add(�̳�����۸��޸�, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 620, 60, 27));

        jTabbedPane2.setBackground(new java.awt.Color(204, 255, 204));
        jTabbedPane2.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        �����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        �����.setText("�����");
        �����.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">�����</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">�����</font></strong>�����µ�������Ʒ<br> ");
        �����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����ActionPerformed(evt);
            }
        });

        ��ȡ������Ʒ.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ��ȡ������Ʒ.setText("������Ʒ");
        ��ȡ������Ʒ.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">������Ʒ</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">������Ʒ</font></strong>�����µ�������Ʒ<br> ");
        ��ȡ������Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ȡ������ƷActionPerformed(evt);
            }
        });

        �.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        �.setText("�");
        �.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">�</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">�</font></strong>�����µ�������Ʒ<br> ");
        �.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        jButton4.setText("ÿ������");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(��ȡ������Ʒ, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(�����, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(�, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(��ȡ������Ʒ)
            .addComponent(�����)
            .addComponent(�)
            .addComponent(jButton4)
        );

        jTabbedPane2.addTab("������Ʒ", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ñ��.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ñ��.setText("ñ��");
        ñ��.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">ñ��</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">ñ��</font></strong>�����µ�������Ʒ<br> ");
        ñ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ñ��ActionPerformed(evt);
            }
        });
        jPanel5.add(ñ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ����.setText("����");
        ����.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">����</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">����</font></strong>�����µ�������Ʒ<br> ");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel5.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 90, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ����.setText("����");
        ����.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">����</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">����</font></strong>�����µ�������Ʒ<br> ");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel5.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 90, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ����.setText("����");
        ����.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">����</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">����</font></strong>�����µ�������Ʒ<br> ");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel5.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 90, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ����.setText("����");
        ����.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">����</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">����</font></strong>�����µ�������Ʒ<br> ");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel5.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 0, 90, -1));

        ȹ��.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ȹ��.setText("ȹ��");
        ȹ��.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">ȹ��</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">ȹ��</font></strong>�����µ�������Ʒ<br> ");
        ȹ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȹ��ActionPerformed(evt);
            }
        });
        jPanel5.add(ȹ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 90, -1));

        Ь��.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        Ь��.setText("Ь��");
        Ь��.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">Ь��</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">Ь��</font></strong>�����µ�������Ʒ<br> ");
        Ь��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ь��ActionPerformed(evt);
            }
        });
        jPanel5.add(Ь��, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 90, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ����.setText("����");
        ����.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">����</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">����</font></strong>�����µ�������Ʒ<br> ");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel5.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, 90, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ����.setText("����");
        ����.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">����</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">����</font></strong>�����µ�������Ʒ<br> ");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel5.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 90, -1));

        ��ָ.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ��ָ.setText("��ָ");
        ��ָ.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">��ָ</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">��ָ</font></strong>�����µ�������Ʒ<br> ");
        ��ָ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ָActionPerformed(evt);
            }
        });
        jPanel5.add(��ָ, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 90, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ����.setText("����");
        ����.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">����</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">����</font></strong>�����µ�������Ʒ<br> ");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel5.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 90, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ����.setText("����");
        ����.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">����</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">����</font></strong>�����µ�������Ʒ<br> ");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel5.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 90, -1));

        ���.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ���.setText("���");
        ���.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">���</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">���</font></strong>�����µ�������Ʒ<br> ");
        ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ActionPerformed(evt);
            }
        });
        jPanel5.add(���, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, 90, -1));

        jTabbedPane2.addTab("װ��", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        ϲ����Ʒ.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ϲ����Ʒ.setText("ϲ����Ʒ");
        ϲ����Ʒ.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">ϲ����Ʒ</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">ϲ����Ʒ</font></strong>�����µ�������Ʒ<br> ");
        ϲ����Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ϲ����ƷActionPerformed(evt);
            }
        });

        ͨѶ��Ʒ.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ͨѶ��Ʒ.setText("ͨѶ��Ʒ");
        ͨѶ��Ʒ.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">ͨѶ��Ʒ</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">ͨѶ��Ʒ</font></strong>�����µ�������Ʒ<br> ");
        ͨѶ��Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ͨѶ��ƷActionPerformed(evt);
            }
        });

        ����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ����.setText("����");
        ����.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">����</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">����</font></strong>�����µ�������Ʒ<br> ");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(ϲ����Ʒ, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ͨѶ��Ʒ, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(����, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ϲ����Ʒ)
            .addComponent(ͨѶ��Ʒ)
            .addComponent(����)
        );

        jTabbedPane2.addTab("����", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��Ա��.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ��Ա��.setText("��Ա��");
        ��Ա��.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">��Ա��</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">��Ա��</font></strong>�����µ�������Ʒ<br> ");
        ��Ա��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ա��ActionPerformed(evt);
            }
        });
        jPanel7.add(��Ա��, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, -1));

        ����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ����.setText("����");
        ����.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">����</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">����</font></strong>�����µ�������Ʒ<br> ");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        jPanel7.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 100, -1));

        �����̵�.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        �����̵�.setText("�����̵�");
        �����̵�.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">�����̵�</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">�����̵�</font></strong>�����µ�������Ʒ<br> ");
        �����̵�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����̵�ActionPerformed(evt);
            }
        });
        jPanel7.add(�����̵�, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 100, -1));

        ������.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ������.setText("������");
        ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������ActionPerformed(evt);
            }
        });
        jPanel7.add(������, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 100, -1));

        ��Ϸ.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ��Ϸ.setText("��Ϸ");
        ��Ϸ.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">��Ϸ</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">��Ϸ</font></strong>�����µ�������Ʒ<br> ");
        ��Ϸ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ϷActionPerformed(evt);
            }
        });
        jPanel7.add(��Ϸ, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 100, -1));

        Ч��.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        Ч��.setText("Ч��");
        Ч��.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">Ч��</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">Ч��</font></strong>�����µ�������Ʒ<br> ");
        Ч��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ч��ActionPerformed(evt);
            }
        });
        jPanel7.add(Ч��, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 100, -1));

        jTabbedPane2.addTab("����", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        ����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ����.setText("����");
        ����.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">����</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">����</font></strong>�����µ�������Ʒ<br> ");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });

        �������.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        �������.setText("�������");
        �������.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����</font></strong><br> \n����<strong><font color=\"#0000E3\">�������</font></strong>�����������Ʒ<br> \n��ʾ<strong><font color=\"#0000E3\">�������</font></strong>�����µ�������Ʒ<br> ");
        �������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �������ActionPerformed(evt);
            }
        });

        ����.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ����.setText("����");
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(����, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(�������, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(����, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(����)
            .addComponent(�������)
            .addComponent(����)
        );

        jTabbedPane2.addTab("����", jPanel8);

        ��Ʒ����.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 57));

        �޸ı�������۸�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸ı�������۸�.setText("�޸�");
        �޸ı�������۸�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸ı�������۸�ActionPerformed(evt);
            }
        });
        ��Ʒ����.add(�޸ı�������۸�, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 620, 70, 27));

        ��Ʒ����״̬.setEditable(false);
        ��Ʒ����״̬.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N
        ��Ʒ����״̬.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ʒ����״̬ActionPerformed(evt);
            }
        });
        ��Ʒ����.add(��Ʒ����״̬, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 650, 70, 27));

        ��ʾ����.setEditable(false);
        ��ʾ����.setFont(new java.awt.Font("��Բ", 1, 14)); // NOI18N
        ��ʾ����.setForeground(new java.awt.Color(255, 0, 51));
        ��ʾ����.setText("��������");
        ��ʾ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ʾ����ActionPerformed(evt);
            }
        });
        ��Ʒ����.add(��ʾ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 650, 120, 27));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 255));
        jButton1.setText("ˢ��");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 70, 25));

        jButton24.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jButton24.setText("ɾ��");
        jButton24.setToolTipText("<html>\n<strong><font color=\"#FF0000\">ɾ����</font></strong><br>\n1.ѡ����Ʒ<br>\n2.ɾ��<br>");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 70, 25));

        jButton27.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jButton27.setText("�¼�");
        jButton27.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�¼ܣ�</font></strong><br>\n1.ѡ����Ʒ<br>\n2.�ϼ�/�¼�<br>");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 70, 25));

        jButton13.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jButton13.setText("�ϼ�");
        jButton13.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�ϼܣ�</font></strong><br>\n1.ѡ����Ʒ<br>\n2.�ϼ�/�¼�<br>");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 25));

        �޸�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸�.setText("�޸�");
        �޸�.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�޸ģ�</font></strong><br> \n1.���б���ѡ����Ҫ�޸ĵ���Ʒ<br>\n2.���ı����������޸�ֵ<br>\n");
        �޸�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸�ActionPerformed(evt);
            }
        });
        jPanel19.add(�޸�, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 45, 70, 25));

        ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���.setText("���");
        ���.setToolTipText("<html>\n<strong><font color=\"#FF0000\">��ӣ�</font></strong><br> \n1.ѡ����Ʒ����<br>\n2.������Ʒ����<br>\n3.������Ʒ����<br>\n4.������Ʒ�۸�<br>\n5.������ʱʱ��(0��������)<br>\n6.ѡ�����״̬<br>");
        ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ActionPerformed(evt);
            }
        });
        jPanel19.add(���, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, 70, 25));

        ��Ʒ����.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 570, 140, 70));

        jButton3.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jButton3.setText("�����̳�");
        jButton3.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����̳ǣ�</font></strong><br>\n���̳ǿ���̨�е��޸���Ҫ���ز�������Ϸ����Ч");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        ��Ʒ����.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 650, 140, 25));

        ˢ��ģʽ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ��ģʽ.setText("�����̳�");
        ˢ��ģʽ.setToolTipText("<html>\n<strong><font color=\"#FF0000\">�����̳ǣ�</font></strong><br>\n���̳ǿ���̨�е��޸���Ҫ���ز�������Ϸ����Ч");
        ˢ��ģʽ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ��ģʽActionPerformed(evt);
            }
        });
        ��Ʒ����.add(ˢ��ģʽ, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 650, 130, 25));

        jTabbedPane1.addTab("��Ϸ�̳�", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ��Ʒ����, "<html>\n<strong><font color=\"#FF0000\">С��ʾ</font></strong><br> \n���Ʒ�Χ��������Ϸ�ڶ����̳���Ʒ<br> \n���ƹ��ܣ��ϼܣ��¼ܣ���ӣ�ɾ�����޸�<br><br>\n\n\n<strong><font color=\"#FF0000\">��ӽ̳�</font></strong><br>\n1.ѡ����Ʒ����<br>\n2.������Ʒ���룬��Ʒ��������Ʒ�۸���ʱʱ��<br>\n3.ѡ�����״̬<br>\n4.������<br>\n5.ÿ������ظ��˲���<br><br>\n\n<strong><font color=\"#FF0000\">������������</font></strong><br>\n���⣻SN�����ظ�<br>\n�����Ҫ�����Ʒ�����ȡ�÷��������µ�SN����<br>\n���⣻�����Ʒ����Ϸ�̳ǲ���ʾ<br>\n��������ɺ���Ҫ���<strong><font color=\"#0000E3\">�����̳�</font></strong>������Ч\n\n\n\n\n\n\n\n"); // NOI18N

        �˺��б�.setBackground(new java.awt.Color(255, 255, 255));
        �˺��б�.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �˺���Ϣ.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �˺���Ϣ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "�˺�ID", "�˺�", "IP��ַ", "MAC��ַ", "��QQ", "��ȯ", "����", "�������", "����", "���", "GM"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        �˺���Ϣ.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(�˺���Ϣ);
        if (�˺���Ϣ.getColumnModel().getColumnCount() > 0) {
            �˺���Ϣ.getColumnModel().getColumn(0).setResizable(false);
            �˺���Ϣ.getColumnModel().getColumn(0).setPreferredWidth(10);
            �˺���Ϣ.getColumnModel().getColumn(1).setResizable(false);
            �˺���Ϣ.getColumnModel().getColumn(1).setPreferredWidth(80);
            �˺���Ϣ.getColumnModel().getColumn(2).setResizable(false);
            �˺���Ϣ.getColumnModel().getColumn(3).setResizable(false);
            �˺���Ϣ.getColumnModel().getColumn(3).setPreferredWidth(130);
            �˺���Ϣ.getColumnModel().getColumn(4).setResizable(false);
            �˺���Ϣ.getColumnModel().getColumn(4).setPreferredWidth(130);
            �˺���Ϣ.getColumnModel().getColumn(5).setResizable(false);
            �˺���Ϣ.getColumnModel().getColumn(5).setPreferredWidth(60);
            �˺���Ϣ.getColumnModel().getColumn(6).setResizable(false);
            �˺���Ϣ.getColumnModel().getColumn(6).setPreferredWidth(60);
            �˺���Ϣ.getColumnModel().getColumn(7).setResizable(false);
            �˺���Ϣ.getColumnModel().getColumn(7).setPreferredWidth(140);
            �˺���Ϣ.getColumnModel().getColumn(8).setResizable(false);
            �˺���Ϣ.getColumnModel().getColumn(8).setPreferredWidth(10);
            �˺���Ϣ.getColumnModel().getColumn(9).setResizable(false);
            �˺���Ϣ.getColumnModel().getColumn(9).setPreferredWidth(20);
            �˺���Ϣ.getColumnModel().getColumn(10).setResizable(false);
            �˺���Ϣ.getColumnModel().getColumn(10).setPreferredWidth(20);
        }

        �˺��б�.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1255, 510));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�˺��޸�", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel13.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 120, 30));

        �˺�.setEditable(false);
        �˺�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel13.add(�˺�, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 100, 30));

        ��ȯ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel13.add(��ȯ, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 120, 30));

        jLabel55.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel55.setText("���ã�");
        jPanel13.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 60, -1));

        jLabel131.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel131.setText("��ȯ��");
        jPanel13.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        �޸��˺ŵ�ȯ����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �޸��˺ŵ�ȯ����.setText("�޸�");
        �޸��˺ŵ�ȯ����.setToolTipText("<html>\n����˺ź���޸��˺ŵ�<strong><font color=\"#FF0000\">����ȯ</font></strong><strong>��<font color=\"#FF0000\">��ȯ</font></strong>");
        �޸��˺ŵ�ȯ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸��˺ŵ�ȯ����ActionPerformed(evt);
            }
        });
        jPanel13.add(�޸��˺ŵ�ȯ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 70, 30));

        �˺�ID.setEditable(false);
        �˺�ID.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel13.add(�˺�ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 70, 30));

        jLabel206.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel206.setText("ID��");
        jPanel13.add(jLabel206, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel312.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel312.setText("����");
        jPanel13.add(jLabel312, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, -1, -1));

        ����1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel13.add(����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 70, 30));

        jLabel353.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel353.setText("�˺ţ�");
        jPanel13.add(jLabel353, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        QQ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jPanel13.add(QQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 120, 30));

        jLabel357.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel357.setText("��QQ��");
        jPanel13.add(jLabel357, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 80, -1));

        �˺��б�.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 710, 90));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ע��/�޸�", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ע����˺�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ע����˺�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ע����˺�ActionPerformed(evt);
            }
        });
        jPanel10.add(ע����˺�, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 100, 30));

        ע�������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ע�������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ע�������ActionPerformed(evt);
            }
        });
        jPanel10.add(ע�������, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 100, 30));

        jButton35.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jButton35.setText("ע��");
        jButton35.setToolTipText("<html>\n����<strong><font color=\"#FF0000\">�˺�</font></strong><strong>��<strong><font color=\"#FF0000\">����</font></strong><strong>����ע���˺�");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 70, 30));

        jLabel111.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel111.setText("�˺ţ�");
        jPanel10.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 30));

        jLabel201.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel201.setText("���룻");
        jPanel10.add(jLabel201, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, 30));

        jButton30.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jButton30.setText("����");
        jButton30.setToolTipText("<html>\n�����˺��޸�<strong><font color=\"#FF0000\">����</font></strong><strong>");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 70, 30));

        �˺��б�.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 560, 520, 90));

        �˺���ʾ����.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        �˺���ʾ����.setText("[��Ϣ]��");
        �˺��б�.add(�˺���ʾ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 653, 700, 25));

        ˢ���˺���Ϣ.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ˢ���˺���Ϣ.setText("ȫ���˺�");
        ˢ���˺���Ϣ.setToolTipText("��ʾ��������˺�");
        ˢ���˺���Ϣ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ���˺���ϢActionPerformed(evt);
            }
        });
        �˺��б�.add(ˢ���˺���Ϣ, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 100, 30));

        �����˺�.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �����˺�.setText("�����˺�");
        �����˺�.setToolTipText("��ʾ�����˺�");
        �����˺�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����˺�ActionPerformed(evt);
            }
        });
        �˺��б�.add(�����˺�, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 5, 100, 30));

        ���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ���.setText("����˺�");
        ���.setToolTipText("<html>\n���ı���<strong><font color=\"#FF0000\">�������˺�</font></strong>�������˺ż��ɽ���Ѿ���������˺�<br>\n");
        ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ActionPerformed(evt);
            }
        });
        �˺��б�.add(���, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 5, 100, 30));

        �ѷ��˺�.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �ѷ��˺�.setText("�ѷ��˺�");
        �ѷ��˺�.setToolTipText("��ʾ�Ѿ���������˺�");
        �ѷ��˺�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �ѷ��˺�ActionPerformed(evt);
            }
        });
        �˺��б�.add(�ѷ��˺�, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 5, 100, 30));

        �����˺�.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �����˺�.setText("�����˺�");
        �����˺�.setToolTipText("��ʾ�����˺�");
        �����˺�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����˺�ActionPerformed(evt);
            }
        });
        �˺��б�.add(�����˺�, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 5, 100, 30));

        ɾ���˺�.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ɾ���˺�.setText("ɾ���˺�");
        ɾ���˺�.setToolTipText("<html>\n���ı���<strong><font color=\"#FF0000\">�������˺�</font></strong>�������˺ż���ɾ���˺�<br>");
        ɾ���˺�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ���˺�ActionPerformed(evt);
            }
        });
        �˺��б�.add(ɾ���˺�, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 5, 100, 30));

        �����˺�.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �����˺�.setText("�����˺�");
        �����˺�.setToolTipText("<html>\n���ı���<strong><font color=\"#FF0000\">�������˺�</font></strong>�������˺ż��ɷ���˺�<br>");
        �����˺�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����˺�ActionPerformed(evt);
            }
        });
        �˺��б�.add(�����˺�, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 5, 100, 30));

        �⿨.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �⿨.setText("�⿨�˺�");
        �⿨.setToolTipText("<html>\n���ı���<strong><font color=\"#FF0000\">�������˺�</font></strong>�������˺ż��ɽ⿨�˺�<br>");
        �⿨.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �⿨ActionPerformed(evt);
            }
        });
        �˺��б�.add(�⿨, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 5, 100, 30));

        �˺Ų���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �˺Ų���.setText("71447500");
        �˺��б�.add(�˺Ų���, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 5, 110, 30));

        jButton7.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jButton7.setText("��QQ");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        �˺��б�.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 5, 80, 30));

        ��ʾ�����˺�.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        �˺��б�.add(��ʾ�����˺�, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 650, 130, 30));

        jButton12.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jButton12.setText("���˺�");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        �˺��б�.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 5, 80, 30));

        ���QQ���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ���QQ���.setForeground(new java.awt.Color(255, 51, 51));
        ���QQ���.setText("���QQ���");
        ���QQ���.setToolTipText("<html>\n���ı���<strong><font color=\"#FF0000\">�������˺�</font></strong>�������˺ż���ɾ���˺�<br>");
        ���QQ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���QQ���ActionPerformed(evt);
            }
        });
        �˺��б�.add(���QQ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 5, 130, 30));

        jTabbedPane1.addTab("��Ϸ�˺�", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), �˺��б�, "<html>\n<strong><font color=\"#FF0000\">С��ʾ</font></strong><br> \n���Ʒ�Χ��������Ϸ�˺�<br> \n���ƹ��ܣ��鿴�����˺ţ������˺ţ������˺ţ��ѷ��˺š�����˺ţ�<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�⿨�˺ţ�ɾ���˺ţ������˺ţ��޸ĵ�ȯ���޸ĵ��á�<br> \n\n\n<strong><font color=\"#FF0000\">����</font></strong><br> \n1.�ڱ��е���˺ź󣬿����ڽ�ɫ���в鿴���˺������н�ɫ<br> \n2.�ڱ��е���˺ź󣬻������·���ʾ�˺ŵĵ�ȯ������<br> \n3.�˺ŵĵ�ȯ���ÿ����޸ģ������˺ű���<strong><font color=\"#0000E3\">����</font></strong>����Ч<br> "); // NOI18N

        ��ɫ�б�.setBackground(new java.awt.Color(255, 255, 255));
        ��ɫ�б�.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane8.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N

        ��ɫ��Ϣ1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ɫ��Ϣ.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ��ɫ��Ϣ.setFont(new java.awt.Font("��Բ", 0, 13)); // NOI18N
        ��ɫ��Ϣ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "��ɫID", "�˺�ID", "��ɫ�ǳ�", "ְҵ", "�ȼ�", "����", "����", "����", "����", "MaxHP", "MaxMP", "���", "���ڵ�ͼ", "����ʱ��", "״̬", "GM", "����", "����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��ɫ��Ϣ.setName(""); // NOI18N
        ��ɫ��Ϣ.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(��ɫ��Ϣ);
        if (��ɫ��Ϣ.getColumnModel().getColumnCount() > 0) {
            ��ɫ��Ϣ.getColumnModel().getColumn(0).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(0).setPreferredWidth(35);
            ��ɫ��Ϣ.getColumnModel().getColumn(1).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(1).setPreferredWidth(35);
            ��ɫ��Ϣ.getColumnModel().getColumn(2).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(2).setPreferredWidth(100);
            ��ɫ��Ϣ.getColumnModel().getColumn(3).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(3).setPreferredWidth(50);
            ��ɫ��Ϣ.getColumnModel().getColumn(4).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(4).setPreferredWidth(50);
            ��ɫ��Ϣ.getColumnModel().getColumn(5).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(5).setPreferredWidth(40);
            ��ɫ��Ϣ.getColumnModel().getColumn(6).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(6).setPreferredWidth(40);
            ��ɫ��Ϣ.getColumnModel().getColumn(7).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(7).setPreferredWidth(40);
            ��ɫ��Ϣ.getColumnModel().getColumn(8).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(8).setPreferredWidth(40);
            ��ɫ��Ϣ.getColumnModel().getColumn(9).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(9).setPreferredWidth(50);
            ��ɫ��Ϣ.getColumnModel().getColumn(10).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(10).setPreferredWidth(50);
            ��ɫ��Ϣ.getColumnModel().getColumn(11).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(11).setPreferredWidth(50);
            ��ɫ��Ϣ.getColumnModel().getColumn(12).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(13).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(13).setPreferredWidth(80);
            ��ɫ��Ϣ.getColumnModel().getColumn(14).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(14).setPreferredWidth(20);
            ��ɫ��Ϣ.getColumnModel().getColumn(15).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(15).setPreferredWidth(20);
            ��ɫ��Ϣ.getColumnModel().getColumn(16).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(16).setPreferredWidth(30);
            ��ɫ��Ϣ.getColumnModel().getColumn(17).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(17).setPreferredWidth(30);
        }

        ��ɫ��Ϣ1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1247, 490));

        ˢ�½�ɫ��Ϣ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ�½�ɫ��Ϣ.setText("ˢ��");
        ˢ�½�ɫ��Ϣ.setToolTipText("��ʾ���н�ɫ");
        ˢ�½�ɫ��Ϣ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�½�ɫ��ϢActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(ˢ�½�ɫ��Ϣ, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 110, 30));

        ��ʾ�����ɫ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ʾ�����ɫ.setText("�����ɫ");
        ��ʾ�����ɫ.setToolTipText("��ʾ����GM����Ա");
        ��ʾ�����ɫ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ʾ�����ɫActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(��ʾ�����ɫ, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 110, 30));

        jButton38.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jButton38.setText("�޸�");
        jButton38.setToolTipText("<html>\n�޸Ľ�ɫ��Ϣ<strong><font color=\"#FF0000\">�ı��򲻿�����</font></strong><strong>");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(jButton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 570, 100, 50));

        ɾ����ɫ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ����ɫ.setText("ɾ����ɫ");
        ɾ����ɫ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ����ɫActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(ɾ����ɫ, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 530, 100, 30));

        ��ɫ�ǳ�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ɫ�ǳ�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ɫ�ǳ�ActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(��ɫ�ǳ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 590, 120, 30));

        �ȼ�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �ȼ�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �ȼ�ActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(�ȼ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 590, 70, 30));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 590, 70, 30));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 590, 70, 30));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 590, 70, 30));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 590, 70, 30));

        HP.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        HP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HPActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(HP, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 590, 70, 30));

        MP.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        MP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(MP, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 590, 70, 30));

        ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(���, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 590, 130, 30));

        ��ͼ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ͼ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ͼActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(��ͼ, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 590, 130, 30));

        GM.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        GM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GMActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(GM, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 590, 70, 30));

        jLabel182.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel182.setText("GM�ȼ���");
        ��ɫ��Ϣ1.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 570, -1, -1));

        jLabel183.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel183.setText("��ɫID��");
        ��ɫ��Ϣ1.add(jLabel183, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, -1, -1));

        jLabel184.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel184.setText("�ȼ���");
        ��ɫ��Ϣ1.add(jLabel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 570, -1, -1));

        jLabel185.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel185.setText("������");
        ��ɫ��Ϣ1.add(jLabel185, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 570, -1, -1));

        jLabel186.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel186.setText("���ݣ�");
        ��ɫ��Ϣ1.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 570, -1, -1));

        jLabel187.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel187.setText("������");
        ��ɫ��Ϣ1.add(jLabel187, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 570, -1, -1));

        jLabel189.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel189.setText("MaxHP��");
        ��ɫ��Ϣ1.add(jLabel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 570, -1, -1));

        jLabel190.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel190.setText("MaxMP��");
        ��ɫ��Ϣ1.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 570, -1, -1));

        jLabel191.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel191.setText("��ң�");
        ��ɫ��Ϣ1.add(jLabel191, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 570, -1, -1));

        jLabel192.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel192.setText("����/����");
        ��ɫ��Ϣ1.add(jLabel192, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 530, -1, 30));

        jLabel193.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel193.setText("��ɫ�ǳƣ�");
        ��ɫ��Ϣ1.add(jLabel193, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 570, -1, -1));

        ��ɫID.setEditable(false);
        ��ɫID.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ɫID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ɫIDActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(��ɫID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 70, 30));

        �����Ծ�1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �����Ծ�1.setText("����/���ͽ��");
        �����Ծ�1.setToolTipText("<html>\n��ɫ��<strong><font color=\"#FF0000\">����</font></strong><strong>����<strong><font color=\"#FF0000\">����</font></strong><strong>ʱ����ô˹���\n");
        �����Ծ�1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����Ծ�1ActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(�����Ծ�1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 500, 130, 30));

        �����Ծ�2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �����Ծ�2.setText("����Ʒ���");
        �����Ծ�2.setToolTipText("<html>\n�ο��Ž�Ȼ�Խ�ɫ����<strong><font color=\"#FF0000\">�����Ʒ</font></strong><strong>����");
        �����Ծ�2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����Ծ�2ActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(�����Ծ�2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 530, 130, 30));

        jLabel203.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel203.setText("������");
        ��ɫ��Ϣ1.add(jLabel203, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 570, -1, -1));

        �鿴����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �鿴����.setText("�鿴��ɫ����");
        �鿴����.setToolTipText("<html>\nѡ���ɫ�󣬵���˹��ܣ��ɲ鿴��ɫ����<strong><font color=\"#FF0000\">������Ϣ</font></strong><strong>");
        �鿴����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �鿴����ActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(�鿴����, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 530, 130, 30));

        �鿴����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �鿴����.setText("�鿴��ɫ����");
        �鿴����.setToolTipText("<html>\nѡ���ɫ�󣬵���˹��ܣ��ɲ鿴��ɫ����<strong><font color=\"#FF0000\">��Ʒ��Ϣ</font></strong><strong>");
        �鿴����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �鿴����ActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(�鿴����, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 530, 130, 30));

        ��������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��������.setText("��������");
        ��������.setToolTipText("<html>\n��ɫ��<strong><font color=\"#FF0000\">����</font></strong><strong>����<strong><font color=\"#FF0000\">����</font></strong><strong>ʱ����ô˹���\n");
        ��������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��������ActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 500, 130, 30));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 530, 60, 30));

        ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 530, 60, 30));

        jLabel214.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel214.setText("���ڵ�ͼ��");
        ��ɫ��Ϣ1.add(jLabel214, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 570, -1, -1));

        ���߽�ɫ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���߽�ɫ.setText("���߽�ɫ");
        ���߽�ɫ.setToolTipText("��ʾ����GM����Ա");
        ���߽�ɫ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���߽�ɫActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(���߽�ɫ, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 530, 110, 30));

        ���߽�ɫ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���߽�ɫ.setText("���߽�ɫ");
        ���߽�ɫ.setToolTipText("��ʾ����GM����Ա");
        ���߽�ɫ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���߽�ɫActionPerformed(evt);
            }
        });
        ��ɫ��Ϣ1.add(���߽�ɫ, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 500, 110, 30));

        ��ʾ�������.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ��ɫ��Ϣ1.add(��ʾ�������, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 495, 130, 30));

        jTabbedPane8.addTab("��ɫ��Ϣ", ��ɫ��Ϣ1);

        ��ɫ����.setBackground(new java.awt.Color(255, 255, 255));
        ��ɫ����.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane5.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��ɫ����װ����Ϣ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ɫ��������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ɫ��������.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "��Ʒ����", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��ɫ��������.getTableHeader().setReorderingAllowed(false);
        jScrollPane15.setViewportView(��ɫ��������);
        if (��ɫ��������.getColumnModel().getColumnCount() > 0) {
            ��ɫ��������.getColumnModel().getColumn(0).setResizable(false);
            ��ɫ��������.getColumnModel().getColumn(0).setPreferredWidth(30);
            ��ɫ��������.getColumnModel().getColumn(1).setResizable(false);
            ��ɫ��������.getColumnModel().getColumn(1).setPreferredWidth(30);
            ��ɫ��������.getColumnModel().getColumn(2).setResizable(false);
            ��ɫ��������.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel36.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 380, 530));

        ������Ʒ����1.setEditable(false);
        ������Ʒ����1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������Ʒ����1ActionPerformed(evt);
            }
        });
        jPanel36.add(������Ʒ����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 150, 30));

        ���ϴ������1.setEditable(false);
        ���ϴ������1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ϴ������1ActionPerformed(evt);
            }
        });
        jPanel36.add(���ϴ������1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 110, 30));

        ������Ʒ����1.setEditable(false);
        ������Ʒ����1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������Ʒ����1ActionPerformed(evt);
            }
        });
        jPanel36.add(������Ʒ����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 110, 30));

        jLabel276.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel276.setText("��ţ�");
        jPanel36.add(jLabel276, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, -1, 20));

        jLabel283.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel283.setText("��Ʒ���֣�");
        jPanel36.add(jLabel283, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 250, -1, 20));

        jLabel287.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel287.setText("��Ʒ���룻");
        jPanel36.add(jLabel287, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, 20));

        ɾ������װ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ������װ��.setText("ɾ��");
        ɾ������װ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ������װ��ActionPerformed(evt);
            }
        });
        jPanel36.add(ɾ������װ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, -1, 30));

        jTabbedPane5.addTab("���ϴ���", jPanel36);

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "װ������", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ɫװ������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ɫװ������.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "��Ʒ����", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��ɫװ������.getTableHeader().setReorderingAllowed(false);
        jScrollPane16.setViewportView(��ɫװ������);
        if (��ɫװ������.getColumnModel().getColumnCount() > 0) {
            ��ɫװ������.getColumnModel().getColumn(0).setResizable(false);
            ��ɫװ������.getColumnModel().getColumn(0).setPreferredWidth(30);
            ��ɫװ������.getColumnModel().getColumn(1).setResizable(false);
            ��ɫװ������.getColumnModel().getColumn(1).setPreferredWidth(30);
            ��ɫװ������.getColumnModel().getColumn(2).setResizable(false);
            ��ɫװ������.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel37.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 380, 530));

        װ��������Ʒ����.setEditable(false);
        װ��������Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                װ��������Ʒ����ActionPerformed(evt);
            }
        });
        jPanel37.add(װ��������Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 150, 30));

        װ��������Ʒ���.setEditable(false);
        װ��������Ʒ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                װ��������Ʒ���ActionPerformed(evt);
            }
        });
        jPanel37.add(װ��������Ʒ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 110, 30));

        װ��������Ʒ����.setEditable(false);
        װ��������Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                װ��������Ʒ����ActionPerformed(evt);
            }
        });
        jPanel37.add(װ��������Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 110, 30));

        jLabel288.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel288.setText("��ţ�");
        jPanel37.add(jLabel288, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, -1, 20));

        jLabel289.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel289.setText("��Ʒ���֣�");
        jPanel37.add(jLabel289, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 250, -1, 20));

        jLabel290.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel290.setText("��Ʒ���룻");
        jPanel37.add(jLabel290, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, 20));

        ɾ��װ������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ��װ������.setText("ɾ��");
        ɾ��װ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ��װ������ActionPerformed(evt);
            }
        });
        jPanel37.add(ɾ��װ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, -1, 30));

        jTabbedPane5.addTab("װ������", jPanel37);

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));
        jPanel38.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "���ı���", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel38.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ɫ���ı���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ɫ���ı���.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "��Ʒ����", "��Ʒ����", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��ɫ���ı���.getTableHeader().setReorderingAllowed(false);
        jScrollPane17.setViewportView(��ɫ���ı���);
        if (��ɫ���ı���.getColumnModel().getColumnCount() > 0) {
            ��ɫ���ı���.getColumnModel().getColumn(0).setResizable(false);
            ��ɫ���ı���.getColumnModel().getColumn(0).setPreferredWidth(30);
            ��ɫ���ı���.getColumnModel().getColumn(1).setResizable(false);
            ��ɫ���ı���.getColumnModel().getColumn(1).setPreferredWidth(30);
            ��ɫ���ı���.getColumnModel().getColumn(2).setResizable(false);
            ��ɫ���ı���.getColumnModel().getColumn(2).setPreferredWidth(100);
            ��ɫ���ı���.getColumnModel().getColumn(3).setResizable(false);
            ��ɫ���ı���.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        jPanel38.add(jScrollPane17, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 430, 530));

        ���ı�����Ʒ����.setEditable(false);
        ���ı�����Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ı�����Ʒ����ActionPerformed(evt);
            }
        });
        jPanel38.add(���ı�����Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 150, 30));

        ���ı�����Ʒ���.setEditable(false);
        ���ı�����Ʒ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ı�����Ʒ���ActionPerformed(evt);
            }
        });
        jPanel38.add(���ı�����Ʒ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 110, 30));

        ���ı�����Ʒ����.setEditable(false);
        ���ı�����Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ı�����Ʒ����ActionPerformed(evt);
            }
        });
        jPanel38.add(���ı�����Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 110, 30));

        jLabel291.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel291.setText("��ţ�");
        jPanel38.add(jLabel291, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, -1, 20));

        jLabel292.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel292.setText("��Ʒ���֣�");
        jPanel38.add(jLabel292, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 250, -1, 20));

        jLabel293.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel293.setText("��Ʒ���룻");
        jPanel38.add(jLabel293, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, 20));

        ɾ�����ı���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ�����ı���.setText("ɾ��");
        ɾ�����ı���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ�����ı���ActionPerformed(evt);
            }
        });
        jPanel38.add(ɾ�����ı���, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, -1, 30));

        jTabbedPane5.addTab("���ı���", jPanel38);

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "���ñ���", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ɫ���ñ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ɫ���ñ���.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "��Ʒ����", "��Ʒ����", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��ɫ���ñ���.getTableHeader().setReorderingAllowed(false);
        jScrollPane18.setViewportView(��ɫ���ñ���);
        if (��ɫ���ñ���.getColumnModel().getColumnCount() > 0) {
            ��ɫ���ñ���.getColumnModel().getColumn(0).setResizable(false);
            ��ɫ���ñ���.getColumnModel().getColumn(0).setPreferredWidth(30);
            ��ɫ���ñ���.getColumnModel().getColumn(1).setResizable(false);
            ��ɫ���ñ���.getColumnModel().getColumn(1).setPreferredWidth(30);
            ��ɫ���ñ���.getColumnModel().getColumn(2).setResizable(false);
            ��ɫ���ñ���.getColumnModel().getColumn(2).setPreferredWidth(100);
            ��ɫ���ñ���.getColumnModel().getColumn(3).setResizable(false);
            ��ɫ���ñ���.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        jPanel39.add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 430, 530));

        ���ñ�����Ʒ����.setEditable(false);
        ���ñ�����Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ñ�����Ʒ����ActionPerformed(evt);
            }
        });
        jPanel39.add(���ñ�����Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 150, 30));

        ���ñ�����Ʒ���.setEditable(false);
        ���ñ�����Ʒ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ñ�����Ʒ���ActionPerformed(evt);
            }
        });
        jPanel39.add(���ñ�����Ʒ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 110, 30));

        ���ñ�����Ʒ����.setEditable(false);
        ���ñ�����Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ñ�����Ʒ����ActionPerformed(evt);
            }
        });
        jPanel39.add(���ñ�����Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 110, 30));

        jLabel294.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel294.setText("��ţ�");
        jPanel39.add(jLabel294, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, -1, 20));

        jLabel295.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel295.setText("��Ʒ���֣�");
        jPanel39.add(jLabel295, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 250, -1, 20));

        jLabel296.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel296.setText("��Ʒ���룻");
        jPanel39.add(jLabel296, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, 20));

        ɾ�����ñ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ�����ñ���.setText("ɾ��");
        ɾ�����ñ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ�����ñ���ActionPerformed(evt);
            }
        });
        jPanel39.add(ɾ�����ñ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, -1, 30));

        jTabbedPane5.addTab("���ñ���", jPanel39);

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��������", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel40.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ɫ��������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ɫ��������.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "��Ʒ����", "��Ʒ����", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��ɫ��������.getTableHeader().setReorderingAllowed(false);
        jScrollPane19.setViewportView(��ɫ��������);
        if (��ɫ��������.getColumnModel().getColumnCount() > 0) {
            ��ɫ��������.getColumnModel().getColumn(0).setResizable(false);
            ��ɫ��������.getColumnModel().getColumn(0).setPreferredWidth(30);
            ��ɫ��������.getColumnModel().getColumn(1).setResizable(false);
            ��ɫ��������.getColumnModel().getColumn(1).setPreferredWidth(30);
            ��ɫ��������.getColumnModel().getColumn(2).setResizable(false);
            ��ɫ��������.getColumnModel().getColumn(2).setPreferredWidth(100);
            ��ɫ��������.getColumnModel().getColumn(3).setResizable(false);
            ��ɫ��������.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        jPanel40.add(jScrollPane19, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 430, 530));

        ����������Ʒ����.setEditable(false);
        ����������Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����������Ʒ����ActionPerformed(evt);
            }
        });
        jPanel40.add(����������Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 150, 30));

        ����������Ʒ���.setEditable(false);
        ����������Ʒ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����������Ʒ���ActionPerformed(evt);
            }
        });
        jPanel40.add(����������Ʒ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 110, 30));

        ����������Ʒ����.setEditable(false);
        ����������Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����������Ʒ����ActionPerformed(evt);
            }
        });
        jPanel40.add(����������Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 110, 30));

        jLabel297.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel297.setText("��ţ�");
        jPanel40.add(jLabel297, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, -1, 20));

        jLabel298.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel298.setText("��Ʒ���֣�");
        jPanel40.add(jLabel298, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 250, -1, 20));

        jLabel299.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel299.setText("��Ʒ���룻");
        jPanel40.add(jLabel299, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, 20));

        ɾ����������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ����������.setText("ɾ��");
        ɾ����������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ����������ActionPerformed(evt);
            }
        });
        jPanel40.add(ɾ����������, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, -1, 30));

        jTabbedPane5.addTab("��������", jPanel40);

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));
        jPanel41.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "���ⱳ��", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel41.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ɫ���ⱳ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ɫ���ⱳ��.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "��Ʒ����", "��Ʒ����", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��ɫ���ⱳ��.getTableHeader().setReorderingAllowed(false);
        jScrollPane20.setViewportView(��ɫ���ⱳ��);
        if (��ɫ���ⱳ��.getColumnModel().getColumnCount() > 0) {
            ��ɫ���ⱳ��.getColumnModel().getColumn(0).setResizable(false);
            ��ɫ���ⱳ��.getColumnModel().getColumn(0).setPreferredWidth(30);
            ��ɫ���ⱳ��.getColumnModel().getColumn(1).setResizable(false);
            ��ɫ���ⱳ��.getColumnModel().getColumn(1).setPreferredWidth(30);
            ��ɫ���ⱳ��.getColumnModel().getColumn(2).setResizable(false);
            ��ɫ���ⱳ��.getColumnModel().getColumn(2).setPreferredWidth(100);
            ��ɫ���ⱳ��.getColumnModel().getColumn(3).setResizable(false);
            ��ɫ���ⱳ��.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        jPanel41.add(jScrollPane20, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 430, 530));

        ���ⱳ����Ʒ����.setEditable(false);
        ���ⱳ����Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ⱳ����Ʒ����ActionPerformed(evt);
            }
        });
        jPanel41.add(���ⱳ����Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 150, 30));

        ���ⱳ����Ʒ���.setEditable(false);
        ���ⱳ����Ʒ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ⱳ����Ʒ���ActionPerformed(evt);
            }
        });
        jPanel41.add(���ⱳ����Ʒ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 110, 30));

        ���ⱳ����Ʒ����.setEditable(false);
        ���ⱳ����Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ⱳ����Ʒ����ActionPerformed(evt);
            }
        });
        jPanel41.add(���ⱳ����Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 110, 30));

        jLabel300.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel300.setText("��ţ�");
        jPanel41.add(jLabel300, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, -1, 20));

        jLabel301.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel301.setText("��Ʒ���֣�");
        jPanel41.add(jLabel301, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 250, -1, 20));

        jLabel302.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel302.setText("��Ʒ���룻");
        jPanel41.add(jLabel302, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, 20));

        ɾ�����ⱳ��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ�����ⱳ��.setText("ɾ��");
        ɾ�����ⱳ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ�����ⱳ��ActionPerformed(evt);
            }
        });
        jPanel41.add(ɾ�����ⱳ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, -1, 30));

        jTabbedPane5.addTab("���ⱳ��", jPanel41);

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));
        jPanel42.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��Ϸ�ֿ�", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel42.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ɫ��Ϸ�ֿ�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ɫ��Ϸ�ֿ�.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "��Ʒ����", "��Ʒ����", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��ɫ��Ϸ�ֿ�.getTableHeader().setReorderingAllowed(false);
        jScrollPane21.setViewportView(��ɫ��Ϸ�ֿ�);
        if (��ɫ��Ϸ�ֿ�.getColumnModel().getColumnCount() > 0) {
            ��ɫ��Ϸ�ֿ�.getColumnModel().getColumn(0).setResizable(false);
            ��ɫ��Ϸ�ֿ�.getColumnModel().getColumn(0).setPreferredWidth(30);
            ��ɫ��Ϸ�ֿ�.getColumnModel().getColumn(1).setResizable(false);
            ��ɫ��Ϸ�ֿ�.getColumnModel().getColumn(1).setPreferredWidth(30);
            ��ɫ��Ϸ�ֿ�.getColumnModel().getColumn(2).setResizable(false);
            ��ɫ��Ϸ�ֿ�.getColumnModel().getColumn(2).setPreferredWidth(100);
            ��ɫ��Ϸ�ֿ�.getColumnModel().getColumn(3).setResizable(false);
            ��ɫ��Ϸ�ֿ�.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        jPanel42.add(jScrollPane21, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 430, 530));

        ��Ϸ�ֿ���Ʒ����.setEditable(false);
        ��Ϸ�ֿ���Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ϸ�ֿ���Ʒ����ActionPerformed(evt);
            }
        });
        jPanel42.add(��Ϸ�ֿ���Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 150, 30));

        ��Ϸ�ֿ���Ʒ���.setEditable(false);
        ��Ϸ�ֿ���Ʒ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ϸ�ֿ���Ʒ���ActionPerformed(evt);
            }
        });
        jPanel42.add(��Ϸ�ֿ���Ʒ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 110, 30));

        ��Ϸ�ֿ���Ʒ����.setEditable(false);
        ��Ϸ�ֿ���Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ϸ�ֿ���Ʒ����ActionPerformed(evt);
            }
        });
        jPanel42.add(��Ϸ�ֿ���Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 110, 30));

        jLabel303.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel303.setText("��ţ�");
        jPanel42.add(jLabel303, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, -1, 20));

        jLabel304.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel304.setText("��Ʒ���֣�");
        jPanel42.add(jLabel304, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 250, -1, 20));

        jLabel305.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel305.setText("��Ʒ���룻");
        jPanel42.add(jLabel305, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, 20));

        ɾ����Ϸ�ֿ�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ����Ϸ�ֿ�.setText("ɾ��");
        ɾ����Ϸ�ֿ�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ����Ϸ�ֿ�ActionPerformed(evt);
            }
        });
        jPanel42.add(ɾ����Ϸ�ֿ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, -1, 30));

        jTabbedPane5.addTab("��Ϸ�ֿ�", jPanel42);

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�̳ǲֿ�", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel43.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ɫ�̳ǲֿ�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ɫ�̳ǲֿ�.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "��Ʒ����", "��Ʒ����", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��ɫ�̳ǲֿ�.getTableHeader().setReorderingAllowed(false);
        jScrollPane22.setViewportView(��ɫ�̳ǲֿ�);
        if (��ɫ�̳ǲֿ�.getColumnModel().getColumnCount() > 0) {
            ��ɫ�̳ǲֿ�.getColumnModel().getColumn(0).setResizable(false);
            ��ɫ�̳ǲֿ�.getColumnModel().getColumn(0).setPreferredWidth(30);
            ��ɫ�̳ǲֿ�.getColumnModel().getColumn(1).setResizable(false);
            ��ɫ�̳ǲֿ�.getColumnModel().getColumn(1).setPreferredWidth(30);
            ��ɫ�̳ǲֿ�.getColumnModel().getColumn(2).setResizable(false);
            ��ɫ�̳ǲֿ�.getColumnModel().getColumn(2).setPreferredWidth(100);
            ��ɫ�̳ǲֿ�.getColumnModel().getColumn(3).setResizable(false);
            ��ɫ�̳ǲֿ�.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        jPanel43.add(jScrollPane22, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 430, 530));

        �̳ǲֿ���Ʒ����.setEditable(false);
        �̳ǲֿ���Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �̳ǲֿ���Ʒ����ActionPerformed(evt);
            }
        });
        jPanel43.add(�̳ǲֿ���Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 150, 30));

        �̳ǲֿ���Ʒ���.setEditable(false);
        �̳ǲֿ���Ʒ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �̳ǲֿ���Ʒ���ActionPerformed(evt);
            }
        });
        jPanel43.add(�̳ǲֿ���Ʒ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 110, 30));

        �̳ǲֿ���Ʒ����.setEditable(false);
        �̳ǲֿ���Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �̳ǲֿ���Ʒ����ActionPerformed(evt);
            }
        });
        jPanel43.add(�̳ǲֿ���Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 110, 30));

        jLabel306.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel306.setText("��ţ�");
        jPanel43.add(jLabel306, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, -1, 20));

        jLabel307.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel307.setText("��Ʒ���֣�");
        jPanel43.add(jLabel307, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 250, -1, 20));

        jLabel308.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel308.setText("��Ʒ���룻");
        jPanel43.add(jLabel308, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, 20));

        ɾ���̳ǲֿ�.setText("ɾ��");
        ɾ���̳ǲֿ�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ���̳ǲֿ�ActionPerformed(evt);
            }
        });
        jPanel43.add(ɾ���̳ǲֿ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, -1, 30));

        jTabbedPane5.addTab("�̳ǲֿ�", jPanel43);

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));
        jPanel48.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��ȯ������", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel48.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ɫ��ȯ������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ɫ��ȯ������.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "��Ʒ����", "��Ʒ����", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��ɫ��ȯ������.getTableHeader().setReorderingAllowed(false);
        jScrollPane30.setViewportView(��ɫ��ȯ������);
        if (��ɫ��ȯ������.getColumnModel().getColumnCount() > 0) {
            ��ɫ��ȯ������.getColumnModel().getColumn(0).setResizable(false);
            ��ɫ��ȯ������.getColumnModel().getColumn(0).setPreferredWidth(30);
            ��ɫ��ȯ������.getColumnModel().getColumn(1).setResizable(false);
            ��ɫ��ȯ������.getColumnModel().getColumn(1).setPreferredWidth(30);
            ��ɫ��ȯ������.getColumnModel().getColumn(2).setResizable(false);
            ��ɫ��ȯ������.getColumnModel().getColumn(2).setPreferredWidth(100);
            ��ɫ��ȯ������.getColumnModel().getColumn(3).setResizable(false);
            ��ɫ��ȯ������.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        jPanel48.add(jScrollPane30, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 430, 530));

        ��������Ʒ����1.setEditable(false);
        ��������Ʒ����1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��������Ʒ����1ActionPerformed(evt);
            }
        });
        jPanel48.add(��������Ʒ����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 150, 30));

        ��ɫ��ȯ���������.setEditable(false);
        ��ɫ��ȯ���������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ɫ��ȯ���������ActionPerformed(evt);
            }
        });
        jPanel48.add(��ɫ��ȯ���������, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 110, 30));

        ��������Ʒ����1.setEditable(false);
        ��������Ʒ����1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��������Ʒ����1ActionPerformed(evt);
            }
        });
        jPanel48.add(��������Ʒ����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 110, 30));

        jLabel354.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel354.setText("��ţ�");
        jPanel48.add(jLabel354, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, -1, 20));

        jLabel355.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel355.setText("��Ʒ���֣�");
        jPanel48.add(jLabel355, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 250, -1, 20));

        jLabel356.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel356.setText("��Ʒ���룻");
        jPanel48.add(jLabel356, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, 20));

        ɾ��������1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ��������1.setText("ɾ��");
        ɾ��������1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ��������1ActionPerformed(evt);
            }
        });
        jPanel48.add(ɾ��������1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, -1, 30));

        jTabbedPane5.addTab("��ȯ������", jPanel48);

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));
        jPanel44.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "���������", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel44.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ɫ���������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ɫ���������.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "��Ʒ����", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��ɫ���������.getTableHeader().setReorderingAllowed(false);
        jScrollPane23.setViewportView(��ɫ���������);
        if (��ɫ���������.getColumnModel().getColumnCount() > 0) {
            ��ɫ���������.getColumnModel().getColumn(0).setResizable(false);
            ��ɫ���������.getColumnModel().getColumn(0).setPreferredWidth(30);
            ��ɫ���������.getColumnModel().getColumn(1).setResizable(false);
            ��ɫ���������.getColumnModel().getColumn(1).setPreferredWidth(30);
            ��ɫ���������.getColumnModel().getColumn(2).setResizable(false);
            ��ɫ���������.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel44.add(jScrollPane23, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 430, 530));

        ��������Ʒ����.setEditable(false);
        ��������Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��������Ʒ����ActionPerformed(evt);
            }
        });
        jPanel44.add(��������Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 150, 30));

        ��ɫ������������.setEditable(false);
        ��ɫ������������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ɫ������������ActionPerformed(evt);
            }
        });
        jPanel44.add(��ɫ������������, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 110, 30));

        ��������Ʒ����.setEditable(false);
        ��������Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��������Ʒ����ActionPerformed(evt);
            }
        });
        jPanel44.add(��������Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 110, 30));

        jLabel309.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel309.setText("��ţ�");
        jPanel44.add(jLabel309, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, -1, 20));

        jLabel310.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel310.setText("��Ʒ���֣�");
        jPanel44.add(jLabel310, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 250, -1, 20));

        jLabel311.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel311.setText("��Ʒ���룻");
        jPanel44.add(jLabel311, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, 20));

        ɾ��������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ��������.setText("ɾ��");
        ɾ��������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ��������ActionPerformed(evt);
            }
        });
        jPanel44.add(ɾ��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, -1, 30));

        jTabbedPane5.addTab("���������", jPanel44);

        ��ɫ����.add(jTabbedPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 620));

        jTabbedPane8.addTab("��ɫ������Ϣ", ��ɫ����);

        ����.setBackground(new java.awt.Color(255, 255, 255));
        ����.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��ɫ����", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        ����.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ������Ϣ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������Ϣ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "��������", "���ܴ���", "Ŀǰ�ȼ�", "��ߵȼ�"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ������Ϣ.getTableHeader().setReorderingAllowed(false);
        jScrollPane13.setViewportView(������Ϣ);
        if (������Ϣ.getColumnModel().getColumnCount() > 0) {
            ������Ϣ.getColumnModel().getColumn(0).setResizable(false);
            ������Ϣ.getColumnModel().getColumn(0).setPreferredWidth(30);
            ������Ϣ.getColumnModel().getColumn(1).setResizable(false);
            ������Ϣ.getColumnModel().getColumn(1).setPreferredWidth(50);
            ������Ϣ.getColumnModel().getColumn(2).setResizable(false);
            ������Ϣ.getColumnModel().getColumn(3).setResizable(false);
            ������Ϣ.getColumnModel().getColumn(3).setPreferredWidth(50);
            ������Ϣ.getColumnModel().getColumn(4).setResizable(false);
        }

        ����.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 520, 560));

        ���ܴ���.setEditable(false);
        ���ܴ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.add(���ܴ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, 120, 30));

        ����Ŀǰ�ȼ�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.add(����Ŀǰ�ȼ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 180, 120, 30));

        ������ߵȼ�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.add(������ߵȼ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 180, 120, 30));

        ��������.setEditable(false);
        ��������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��������ActionPerformed(evt);
            }
        });
        ����.add(��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 180, 120, 30));

        jLabel86.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel86.setText("���ܴ��룻");
        ����.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 160, -1, -1));

        jLabel89.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel89.setText("Ŀǰ�ȼ���");
        ����.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 160, -1, -1));

        jLabel107.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel107.setText("��ߵȼ���");
        ����.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 160, -1, -1));

        jLabel108.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel108.setText("�����޷�����������Χֵ��");
        ����.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 560, 490, 30));

        �޸ļ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸ļ���.setText("�޸�");
        �޸ļ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸ļ���ActionPerformed(evt);
            }
        });
        ����.add(�޸ļ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 280, 120, 40));

        �������.setEditable(false);
        �������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����.add(�������, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, 80, 30));

        jLabel188.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel188.setText("�������֣�");
        ����.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 160, -1, -1));

        jLabel204.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel204.setText("��ţ�");
        ����.add(jLabel204, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 160, -1, -1));

        jLabel205.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel205.setText("��ʾ��");
        ����.add(jLabel205, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 530, 120, 30));

        ɾ������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ������.setText("ɾ��");
        ɾ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ������ActionPerformed(evt);
            }
        });
        ����.add(ɾ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 280, 120, 40));

        �޸ļ���1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸ļ���1.setText("ˢ��");
        �޸ļ���1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸ļ���1ActionPerformed(evt);
            }
        });
        ����.add(�޸ļ���1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 280, 120, 40));

        jTabbedPane8.addTab("��ɫ������Ϣ", ����);

        ��ɫ�б�.add(jTabbedPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1257, 655));

        ��ɫ��ʾ����.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ��ɫ��ʾ����.setText("[��Ϣ]��");
        ��ɫ�б�.add(��ɫ��ʾ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 655, 1240, 25));

        jTabbedPane1.addTab("�����Ϣ", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ��ɫ�б�); // NOI18N

        ������.setBackground(new java.awt.Color(255, 255, 255));
        ������.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane7.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��ȯ������", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ���������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���������.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "��Ʒ���", "����", "��Ʒ״̬", "���", "��Ʒ�۸�", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ���������.getTableHeader().setReorderingAllowed(false);
        jScrollPane27.setViewportView(���������);
        if (���������.getColumnModel().getColumnCount() > 0) {
            ���������.getColumnModel().getColumn(0).setResizable(false);
            ���������.getColumnModel().getColumn(0).setPreferredWidth(10);
            ���������.getColumnModel().getColumn(1).setResizable(false);
            ���������.getColumnModel().getColumn(2).setResizable(false);
            ���������.getColumnModel().getColumn(3).setResizable(false);
            ���������.getColumnModel().getColumn(4).setResizable(false);
            ���������.getColumnModel().getColumn(5).setResizable(false);
            ���������.getColumnModel().getColumn(5).setPreferredWidth(150);
        }

        jPanel2.add(jScrollPane27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 880, 580));

        ˢ�½��������.setText("ˢ��");
        ˢ�½��������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�½��������ActionPerformed(evt);
            }
        });
        jPanel2.add(ˢ�½��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 570, 100, 40));

        jTabbedPane7.addTab("��ȯ������", jPanel2);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "���������", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ȯ������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ȯ������.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "��Ʒ���", "����", "��Ʒ״̬", "���", "��Ʒ�۸�", "��Ʒ����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��ȯ������.getTableHeader().setReorderingAllowed(false);
        jScrollPane28.setViewportView(��ȯ������);
        if (��ȯ������.getColumnModel().getColumnCount() > 0) {
            ��ȯ������.getColumnModel().getColumn(0).setResizable(false);
            ��ȯ������.getColumnModel().getColumn(0).setPreferredWidth(10);
            ��ȯ������.getColumnModel().getColumn(1).setResizable(false);
            ��ȯ������.getColumnModel().getColumn(2).setResizable(false);
            ��ȯ������.getColumnModel().getColumn(3).setResizable(false);
            ��ȯ������.getColumnModel().getColumn(4).setResizable(false);
            ��ȯ������.getColumnModel().getColumn(5).setResizable(false);
            ��ȯ������.getColumnModel().getColumn(5).setPreferredWidth(150);
        }

        jPanel11.add(jScrollPane28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 880, 580));

        ˢ�½��������1.setText("ˢ��");
        ˢ�½��������1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�½��������1ActionPerformed(evt);
            }
        });
        jPanel11.add(ˢ�½��������1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 570, 100, 40));

        jTabbedPane7.addTab("���������", jPanel11);

        ������.add(jTabbedPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 680));

        jTabbedPane1.addTab("������", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ������); // NOI18N

        ���͸���.setBackground(new java.awt.Color(255, 255, 255));
        ���͸���.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane3.setFont(new java.awt.Font("��Բ", 0, 12)); // NOI18N

        ������¼����.setBackground(new java.awt.Color(255, 255, 255));
        ������¼����.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ȫ�����͸���", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        ������¼����.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ȫ�����͸���", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ȫ��������Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ��������Ʒ����ActionPerformed(evt);
            }
        });
        jPanel24.add(ȫ��������Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 100, 30));

        ȫ��������Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ��������Ʒ����ActionPerformed(evt);
            }
        });
        jPanel24.add(ȫ��������Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 110, 30));

        ������Ʒ1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������Ʒ1.setText("������Ʒ");
        ������Ʒ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������Ʒ1ActionPerformed(evt);
            }
        });
        jPanel24.add(������Ʒ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 100, 30));

        jLabel217.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel217.setText("��Ʒ������");
        jPanel24.add(jLabel217, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));

        jLabel234.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel234.setText("��Ʒ���룻");
        jPanel24.add(jLabel234, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        ������¼����.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 510, 110));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ȫ�����͸���", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ȫ������װ��װ���Ӿ�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������װ��װ���Ӿ�ActionPerformed(evt);
            }
        });
        jPanel25.add(ȫ������װ��װ���Ӿ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 100, 30));

        ȫ������װ��װ��������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������װ��װ��������ActionPerformed(evt);
            }
        });
        jPanel25.add(ȫ������װ��װ��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 100, 30));

        ȫ������װ��װ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������װ��װ������ActionPerformed(evt);
            }
        });
        jPanel25.add(ȫ������װ��װ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 100, 30));

        ȫ������װ��װ��MP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������װ��װ��MPActionPerformed(evt);
            }
        });
        jPanel25.add(ȫ������װ��װ��MP, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 100, 30));

        ȫ������װ��װ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������װ��װ������ActionPerformed(evt);
            }
        });
        jPanel25.add(ȫ������װ��װ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 100, 30));

        ȫ������װ��װ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������װ��װ������ActionPerformed(evt);
            }
        });
        jPanel25.add(ȫ������װ��װ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 100, 30));

        ȫ������װ��װ��HP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������װ��װ��HPActionPerformed(evt);
            }
        });
        jPanel25.add(ȫ������װ��װ��HP, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 100, 30));

        ȫ������װ��װ��������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������װ��װ��������ActionPerformed(evt);
            }
        });
        jPanel25.add(ȫ������װ��װ��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 100, 30));

        ȫ������װ��װ������ʱ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������װ��װ������ʱ��ActionPerformed(evt);
            }
        });
        jPanel25.add(ȫ������װ��װ������ʱ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 100, 30));

        ȫ������װ��װ���ɷ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������װ��װ���ɷ���ActionPerformed(evt);
            }
        });
        jPanel25.add(ȫ������װ��װ���ɷ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 100, 30));

        ȫ������װ��װ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������װ��װ������ActionPerformed(evt);
            }
        });
        jPanel25.add(ȫ������װ��װ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 100, 30));

        ȫ������װ����ƷID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������װ����ƷIDActionPerformed(evt);
            }
        });
        jPanel25.add(ȫ������װ����ƷID, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 100, 30));

        ȫ������װ��װ��ħ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������װ��װ��ħ����ActionPerformed(evt);
            }
        });
        jPanel25.add(ȫ������װ��װ��ħ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 100, 30));

        ȫ������װ��װ��ħ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������װ��װ��ħ������ActionPerformed(evt);
            }
        });
        jPanel25.add(ȫ������װ��װ��ħ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 100, 30));

        ȫ������װ��װ���������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������װ��װ���������ActionPerformed(evt);
            }
        });
        jPanel25.add(ȫ������װ��װ���������, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 100, 30));

        ����װ��1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����װ��1.setText("���˷���");
        ����װ��1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����װ��1ActionPerformed(evt);
            }
        });
        jPanel25.add(����װ��1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 100, 30));

        jLabel219.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel219.setText("�ܷ��ף���0");
        jPanel25.add(jLabel219, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, -1, -1));

        jLabel220.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel220.setText("HP�ӳɣ�");
        jPanel25.add(jLabel220, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, -1, -1));

        jLabel221.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel221.setText("ħ����������");
        jPanel25.add(jLabel221, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        jLabel222.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel222.setText("װ�����룻");
        jPanel25.add(jLabel222, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jLabel223.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel223.setText("MP�ӳɣ�");
        jPanel25.add(jLabel223, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        jLabel224.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel224.setText("����������");
        jPanel25.add(jLabel224, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, -1, -1));

        jLabel225.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel225.setText("���Ҿ������");
        jPanel25.add(jLabel225, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, -1));

        jLabel226.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel226.setText("װ��������");
        jPanel25.add(jLabel226, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, -1, -1));

        jLabel227.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel227.setText("װ��������");
        jPanel25.add(jLabel227, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        jLabel228.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel228.setText("װ�����ݣ�");
        jPanel25.add(jLabel228, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, -1, -1));

        jLabel229.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel229.setText("װ��������");
        jPanel25.add(jLabel229, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, -1, -1));

        jLabel230.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel230.setText("װ��������");
        jPanel25.add(jLabel230, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, -1, -1));

        jLabel231.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel231.setText("ħ��������");
        jPanel25.add(jLabel231, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        jLabel232.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel232.setText("���������");
        jPanel25.add(jLabel232, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, -1, -1));

        jLabel233.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel233.setText("��ʱʱ�䣻");
        jPanel25.add(jLabel233, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, -1, -1));

        ����װ���������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����װ���������ActionPerformed(evt);
            }
        });
        jPanel25.add(����װ���������, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 100, 30));

        ����װ��2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����װ��2.setText("ȫ������");
        ����װ��2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����װ��2ActionPerformed(evt);
            }
        });
        jPanel25.add(����װ��2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 120, 100, 30));

        jLabel246.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel246.setText("������֣�");
        jPanel25.add(jLabel246, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, -1, -1));

        jLabel244.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel244.setText("���˷�����Ҫ��д����");
        jPanel25.add(jLabel244, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, -1, -1));

        ������¼����.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 760, 290));

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ȫ�����͸���", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        z2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        z2.setText("���͵���");
        z2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                z2ActionPerformed(evt);
            }
        });
        jPanel26.add(z2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 100, 30));

        z3.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        z3.setText("���ͽ��");
        z3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                z3ActionPerformed(evt);
            }
        });
        jPanel26.add(z3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 100, 30));

        z1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        z1.setText("���͵�ȯ");
        z1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                z1ActionPerformed(evt);
            }
        });
        jPanel26.add(z1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 100, 30));

        z4.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        z4.setText("���;���");
        z4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                z4ActionPerformed(evt);
            }
        });
        jPanel26.add(z4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 100, 30));

        z5.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        z5.setText("��������");
        z5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                z5ActionPerformed(evt);
            }
        });
        jPanel26.add(z5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 100, 30));

        z6.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        z6.setText("���Ͷ���");
        z6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                z6ActionPerformed(evt);
            }
        });
        jPanel26.add(z6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 100, 30));

        a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a1ActionPerformed(evt);
            }
        });
        jPanel26.add(a1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 100, 30));

        jLabel235.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel235.setText("������");
        jPanel26.add(jLabel235, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        ������¼����.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 240, 270));

        ������ʾ����.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ������ʾ����.setText("[��Ϣ]��");
        ������¼����.add(������ʾ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 620, 810, 25));

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "���˷�����Ʒ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ���˷�����Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���˷�����Ʒ����ActionPerformed(evt);
            }
        });
        jPanel27.add(���˷�����Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 80, 30));

        ���˷�����Ʒ�������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���˷�����Ʒ�������ActionPerformed(evt);
            }
        });
        jPanel27.add(���˷�����Ʒ�������, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 130, 30));

        ���˷�����Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���˷�����Ʒ����ActionPerformed(evt);
            }
        });
        jPanel27.add(���˷�����Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 130, 30));

        ������Ʒ.setBackground(new java.awt.Color(255, 255, 255));
        ������Ʒ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������Ʒ.setText("������Ʒ");
        ������Ʒ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������ƷActionPerformed(evt);
            }
        });
        jPanel27.add(������Ʒ, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 100, 30));

        jLabel240.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel240.setText("��Ʒ������");
        jPanel27.add(jLabel240, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        jLabel241.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel241.setText("������֣�");
        jPanel27.add(jLabel241, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel242.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel242.setText("��Ʒ���룻");
        jPanel27.add(jLabel242, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, -1));

        ������¼����.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 510, 150));

        �洢������Ʒ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "���", "����", "����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        �洢������Ʒ.getTableHeader().setReorderingAllowed(false);
        jScrollPane12.setViewportView(�洢������Ʒ);
        if (�洢������Ʒ.getColumnModel().getColumnCount() > 0) {
            �洢������Ʒ.getColumnModel().getColumn(0).setResizable(false);
            �洢������Ʒ.getColumnModel().getColumn(0).setPreferredWidth(30);
            �洢������Ʒ.getColumnModel().getColumn(1).setResizable(false);
            �洢������Ʒ.getColumnModel().getColumn(1).setPreferredWidth(200);
            �洢������Ʒ.getColumnModel().getColumn(2).setResizable(false);
            �洢������Ʒ.getColumnModel().getColumn(2).setPreferredWidth(70);
        }

        ������¼����.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 40, 360, 480));

        ˢ�¸���.setText("ˢ�¼�¼[����]");
        ˢ�¸���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�¸���ActionPerformed(evt);
            }
        });
        ������¼����.add(ˢ�¸���, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 610, 190, 30));

        ˢ��ͨ��.setText("ˢ�¼�¼[ͨ��]");
        ˢ��ͨ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ��ͨ��ActionPerformed(evt);
            }
        });
        ������¼����.add(ˢ��ͨ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(1069, 610, 170, 30));
        ������¼����.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 520, 170, 30));

        �������.setEditable(false);
        ������¼����.add(�������, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 520, 80, 30));

        ��������.setText("������¼[����]");
        ��������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��������ActionPerformed(evt);
            }
        });
        ������¼����.add(��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 550, 190, 30));

        ����ͨ��.setText("������¼[ͨ��]");
        ����ͨ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ͨ��ActionPerformed(evt);
            }
        });
        ������¼����.add(����ͨ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 550, 170, 30));

        ɾ��ͨ��.setText("ɾ����¼[ͨ��]");
        ɾ��ͨ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ��ͨ��ActionPerformed(evt);
            }
        });
        ������¼����.add(ɾ��ͨ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 580, 170, 30));

        ɾ������.setText("ɾ����¼[����]");
        ɾ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ������ActionPerformed(evt);
            }
        });
        ������¼����.add(ɾ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 580, 190, 30));

        �޸ļ�¼.setText("�޸�");
        �޸ļ�¼.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸ļ�¼ActionPerformed(evt);
            }
        });
        ������¼����.add(�޸ļ�¼, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 520, 110, 30));

        jTabbedPane3.addTab("��������", ������¼����);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��������ݵ�", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �����ݵ�����.setFont(new java.awt.Font("��Բ", 0, 20)); // NOI18N
        �����ݵ�����.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "����", "��ֵ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        �����ݵ�����.getTableHeader().setReorderingAllowed(false);
        jScrollPane134.setViewportView(�����ݵ�����);
        if (�����ݵ�����.getColumnModel().getColumnCount() > 0) {
            �����ݵ�����.getColumnModel().getColumn(0).setResizable(false);
            �����ݵ�����.getColumnModel().getColumn(0).setPreferredWidth(50);
            �����ݵ�����.getColumnModel().getColumn(1).setResizable(false);
            �����ݵ�����.getColumnModel().getColumn(1).setPreferredWidth(200);
            �����ݵ�����.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel1.add(jScrollPane134, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 480, 550));

        �ݵ����.setEditable(false);
        jPanel1.add(�ݵ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, 100, 30));

        �ݵ�����.setEditable(false);
        jPanel1.add(�ݵ�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 220, 210, 30));
        jPanel1.add(�ݵ�ֵ, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 220, 120, 30));

        �ݵ�ֵ�޸�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �ݵ�ֵ�޸�.setText("�޸�");
        �ݵ�ֵ�޸�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �ݵ�ֵ�޸�ActionPerformed(evt);
            }
        });
        jPanel1.add(�ݵ�ֵ�޸�, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 220, 120, 30));

        jLabel322.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel322.setText("������ֵ��");
        jPanel1.add(jLabel322, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 200, -1, -1));

        jLabel323.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel323.setText("��ʾ���ݵ�ʱ��Ϊ30����,�������ü�ʱ��Ч��");
        jPanel1.add(jLabel323, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, -1, -1));

        jLabel324.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel324.setText("�ݵ㽱�����ͣ�");
        jPanel1.add(jLabel324, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, -1, -1));

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));
        jPanel45.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�����ݵ�����", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel45.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �ݵ��ҿ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �ݵ��ҿ���.setText("�ݵ���");
        �ݵ��ҿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �ݵ��ҿ���ActionPerformed(evt);
            }
        });
        jPanel45.add(�ݵ��ҿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 260, 40));

        �ݵ㾭�鿪��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �ݵ㾭�鿪��.setText("�ݵ㾭��");
        �ݵ㾭�鿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �ݵ㾭�鿪��ActionPerformed(evt);
            }
        });
        jPanel45.add(�ݵ㾭�鿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 260, 40));

        �ݵ��ȯ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �ݵ��ȯ����.setText("�ݵ��ȯ");
        �ݵ��ȯ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �ݵ��ȯ����ActionPerformed(evt);
            }
        });
        jPanel45.add(�ݵ��ȯ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 260, 40));

        �ݵ���ÿ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �ݵ���ÿ���.setText("�ݵ����");
        �ݵ���ÿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �ݵ���ÿ���ActionPerformed(evt);
            }
        });
        jPanel45.add(�ݵ���ÿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 260, 40));

        jPanel1.add(jPanel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 290, 340, 300));

        jLabel325.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel325.setText("��ţ�");
        jPanel1.add(jLabel325, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, -1));

        ������ʾ����2.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ������ʾ����2.setText("[��Ϣ]��");
        jPanel1.add(������ʾ����2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 620, 1240, 25));

        jTabbedPane3.addTab("��������ݵ�", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��Ӷ�����ݵ�", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��Ӷ�����ݵ�����.setFont(new java.awt.Font("��Բ", 0, 20)); // NOI18N
        ��Ӷ�����ݵ�����.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "����", "��ֵ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��Ӷ�����ݵ�����.getTableHeader().setReorderingAllowed(false);
        jScrollPane135.setViewportView(��Ӷ�����ݵ�����);
        if (��Ӷ�����ݵ�����.getColumnModel().getColumnCount() > 0) {
            ��Ӷ�����ݵ�����.getColumnModel().getColumn(0).setResizable(false);
            ��Ӷ�����ݵ�����.getColumnModel().getColumn(0).setPreferredWidth(50);
            ��Ӷ�����ݵ�����.getColumnModel().getColumn(1).setResizable(false);
            ��Ӷ�����ݵ�����.getColumnModel().getColumn(1).setPreferredWidth(200);
            ��Ӷ�����ݵ�����.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel3.add(jScrollPane135, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 480, 550));

        �ݵ����1.setEditable(false);
        jPanel3.add(�ݵ����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, 100, 30));

        jLabel326.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel326.setText("��ţ�");
        jPanel3.add(jLabel326, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, -1));

        �ݵ�����1.setEditable(false);
        jPanel3.add(�ݵ�����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 220, 210, 30));

        jLabel327.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel327.setText("�ݵ㽱�����ͣ�");
        jPanel3.add(jLabel327, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, -1, -1));
        jPanel3.add(�ݵ�ֵ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 220, 120, 30));

        jLabel328.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel328.setText("������ֵ��");
        jPanel3.add(jLabel328, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 200, -1, -1));

        �ݵ�ֵ�޸�1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �ݵ�ֵ�޸�1.setText("�޸�");
        �ݵ�ֵ�޸�1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �ݵ�ֵ�޸�1ActionPerformed(evt);
            }
        });
        jPanel3.add(�ݵ�ֵ�޸�1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 220, 120, 30));

        jLabel329.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel329.setText("��ʾ���ݵ�ʱ��Ϊ30����,�������ü�ʱ��Ч��");
        jPanel3.add(jLabel329, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, -1, -1));

        jTabbedPane3.addTab("��Ӷ�����ݵ�", jPanel3);

        ���͸���.add(jTabbedPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 680));

        jTabbedPane1.addTab("��Ϸ����", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ���͸���); // NOI18N

        ��������.setBackground(new java.awt.Color(255, 255, 255));
        ��������.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��Ϸ���淢��", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        ��������.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton31.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jButton31.setText("��ɫ��������");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        ��������.add(jButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 350, 130, 50));

        jButton32.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jButton32.setText("������ɫ����");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        ��������.add(jButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 350, 130, 50));

        jButton33.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jButton33.setText("���˹�������");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        ��������.add(jButton33, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 130, 50));

        ������д.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������д.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������дActionPerformed(evt);
            }
        });
        ��������.add(������д, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 1060, 170));

        ���淢�����ȴ���.setText("5120027");
        ���淢�����ȴ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���淢�����ȴ���ActionPerformed(evt);
            }
        });
        ��������.add(���淢�����ȴ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 370, 90, 30));

        jButton34.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jButton34.setText("��Ļ���й���");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        ��������.add(jButton34, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 350, 130, 50));

        jLabel106.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel106.setText("4�����ú��з��ɡ����������ֹ����������");
        ��������.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 580, 680, 40));

        jLabel117.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel117.setText("1������ɢ��ҥ�ԣ�������������ƻ�����ȶ�����Ϣ ");
        ��������.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, 680, 40));

        jLabel118.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel118.setText("2������ɢ���Ĳ�����������ɱ���ֲ����߽����������Ϣ");
        ��������.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, 680, 40));

        jLabel119.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel119.setText("3������������߷̰����ˣ��ֺ����˺Ϸ�Ȩ��");
        ��������.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 540, 680, 40));

        jLabel259.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel259.setText("���ȴ��룻");
        ��������.add(jLabel259, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 350, -1, -1));

        ������ʾ����.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ������ʾ����.setText("[��Ϣ]��");
        ��������.add(������ʾ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 1240, 25));

        jTabbedPane1.addTab("��Ϸ/Ⱥ����", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ��������); // NOI18N

        ��������.setBackground(new java.awt.Color(255, 255, 255));
        ��������.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ������Ϣ.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������Ϣ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "����ID", "��������", "�峤/��ɫID", "��Ա1", "��Ա2", "��Ա3", "��Ա4", "��Ա5", "����GP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ������Ϣ.getTableHeader().setReorderingAllowed(false);
        jScrollPane24.setViewportView(������Ϣ);
        if (������Ϣ.getColumnModel().getColumnCount() > 0) {
            ������Ϣ.getColumnModel().getColumn(0).setResizable(false);
            ������Ϣ.getColumnModel().getColumn(0).setPreferredWidth(10);
            ������Ϣ.getColumnModel().getColumn(1).setResizable(false);
            ������Ϣ.getColumnModel().getColumn(1).setPreferredWidth(80);
            ������Ϣ.getColumnModel().getColumn(1).setHeaderValue("��������");
            ������Ϣ.getColumnModel().getColumn(2).setResizable(false);
            ������Ϣ.getColumnModel().getColumn(2).setHeaderValue("�峤/��ɫID");
            ������Ϣ.getColumnModel().getColumn(3).setHeaderValue("��Ա1");
            ������Ϣ.getColumnModel().getColumn(4).setResizable(false);
            ������Ϣ.getColumnModel().getColumn(4).setPreferredWidth(130);
            ������Ϣ.getColumnModel().getColumn(4).setHeaderValue("��Ա2");
            ������Ϣ.getColumnModel().getColumn(5).setHeaderValue("��Ա3");
            ������Ϣ.getColumnModel().getColumn(6).setResizable(false);
            ������Ϣ.getColumnModel().getColumn(7).setResizable(false);
            ������Ϣ.getColumnModel().getColumn(7).setHeaderValue("��Ա5");
            ������Ϣ.getColumnModel().getColumn(8).setResizable(false);
            ������Ϣ.getColumnModel().getColumn(8).setHeaderValue("����GP");
        }

        ��������.add(jScrollPane24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 480));

        ˢ�¼�����Ϣ.setText("ˢ�¼�����Ϣ");
        ˢ�¼�����Ϣ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�¼�����ϢActionPerformed(evt);
            }
        });
        ��������.add(ˢ�¼�����Ϣ, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 577, -1, 40));

        jLabel194.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel194.setText("����ID��");
        ��������.add(jLabel194, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, -1));

        ����ID.setEditable(false);
        ����ID.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����IDActionPerformed(evt);
            }
        });
        ��������.add(����ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 90, 30));

        ��������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��������ActionPerformed(evt);
            }
        });
        ��������.add(��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, 140, 30));

        jLabel195.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel195.setText("�������ƣ�");
        ��������.add(jLabel195, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, -1, -1));

        �����峤.setEditable(false);
        �����峤.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �����峤.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����峤ActionPerformed(evt);
            }
        });
        ��������.add(�����峤, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 510, 140, 30));

        jLabel196.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel196.setText("�����峤��");
        ��������.add(jLabel196, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 490, -1, -1));

        jLabel197.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel197.setText("�����Ա1��");
        ��������.add(jLabel197, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 490, -1, -1));

        �����Ա1.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �����Ա1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����Ա1ActionPerformed(evt);
            }
        });
        ��������.add(�����Ա1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 510, 140, 30));

        jLabel198.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel198.setText("�����Ա2��");
        ��������.add(jLabel198, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 490, -1, -1));

        �����Ա2.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �����Ա2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����Ա2ActionPerformed(evt);
            }
        });
        ��������.add(�����Ա2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 510, 140, 30));

        jLabel199.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel199.setText("�����Ա3��");
        ��������.add(jLabel199, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 490, -1, -1));

        �����Ա3.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �����Ա3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����Ա3ActionPerformed(evt);
            }
        });
        ��������.add(�����Ա3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 510, 140, 30));

        jLabel200.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel200.setText("�����Ա4��");
        ��������.add(jLabel200, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 490, -1, -1));

        �����Ա4.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �����Ա4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����Ա4ActionPerformed(evt);
            }
        });
        ��������.add(�����Ա4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 510, 140, 30));

        jLabel313.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel313.setText("�����Ա5��");
        ��������.add(jLabel313, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 490, -1, -1));

        �����Ա5.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �����Ա5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����Ա5ActionPerformed(evt);
            }
        });
        ��������.add(�����Ա5, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 510, 140, 30));

        jLabel314.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel314.setText("����GP��");
        ��������.add(jLabel314, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 490, -1, -1));

        ����GP.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����GP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����GPActionPerformed(evt);
            }
        });
        ��������.add(����GP, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 510, 140, 30));

        jTabbedPane1.addTab("��Ϸ����", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ��������); // NOI18N

        PVP����.setBackground(new java.awt.Color(255, 255, 255));
        PVP����.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PVP��������.setFont(new java.awt.Font("��Բ", 0, 20)); // NOI18N
        PVP��������.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "���", "����ID", "�����˺�", "������"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane26.setViewportView(PVP��������);
        if (PVP��������.getColumnModel().getColumnCount() > 0) {
            PVP��������.getColumnModel().getColumn(0).setResizable(false);
            PVP��������.getColumnModel().getColumn(1).setResizable(false);
            PVP��������.getColumnModel().getColumn(1).setPreferredWidth(100);
            PVP��������.getColumnModel().getColumn(2).setResizable(false);
            PVP��������.getColumnModel().getColumn(2).setPreferredWidth(100);
            PVP��������.getColumnModel().getColumn(3).setResizable(false);
            PVP��������.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        PVP����.add(jScrollPane26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 470, 640));

        ����PVP���.setEditable(false);
        PVP����.add(����PVP���, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 240, 60, -1));
        PVP����.add(����PVPID, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 120, -1));
        PVP����.add(����PVP�˺�, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 240, 100, -1));

        jLabel218.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel218.setText("�����˺���");
        PVP����.add(jLabel218, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 220, -1, -1));

        jLabel236.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel236.setText("���ܴ��룻");
        PVP����.add(jLabel236, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 220, -1, -1));

        ˢ��PVP�б�.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ˢ��PVP�б�.setText("ˢ���б�");
        ˢ��PVP�б�.setToolTipText("");
        ˢ��PVP�б�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ��PVP�б�ActionPerformed(evt);
            }
        });
        PVP����.add(ˢ��PVP�б�, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 130, 30));

        jButton2.setText("ɾ��");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        PVP����.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 280, 80, 30));

        ����1.setText("����");
        ����1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����1ActionPerformed(evt);
            }
        });
        PVP����.add(����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, 80, 30));

        �޸�1.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �޸�1.setText("�޸�");
        �޸�1.setToolTipText("");
        �޸�1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸�1ActionPerformed(evt);
            }
        });
        PVP����.add(�޸�1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 280, 80, 30));

        ����1.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ����1.setText("����");
        ����1.setToolTipText("");
        ����1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����1ActionPerformed(evt);
            }
        });
        PVP����.add(����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 280, 80, 30));

        jLabel268.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel268.setText("���û�����þ���Ĭ�ϵ��˺���");
        PVP����.add(jLabel268, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 430, 370, 30));

        jLabel269.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel269.setText("���ü���ÿ���ȼ����˺���");
        PVP����.add(jLabel269, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 400, 370, 30));

        jLabel271.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel271.setText("���������޸ļ�ʱ��Ч������������");
        PVP����.add(jLabel271, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 680, 370, 30));

        jLabel272.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel272.setText("���������޸ļ�ʱ��Ч������������");
        PVP����.add(jLabel272, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 460, 370, 30));

        jTabbedPane1.addTab("PVP����", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), PVP����); // NOI18N

        ����.setBackground(new java.awt.Color(255, 255, 255));
        ����.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ����.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        ����.add(����, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 540, 40));

        ���¼�¼.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ���¼�¼.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "����", "�汾", "���", "null*"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(���¼�¼);
        if (���¼�¼.getColumnModel().getColumnCount() > 0) {
            ���¼�¼.getColumnModel().getColumn(0).setResizable(false);
            ���¼�¼.getColumnModel().getColumn(0).setPreferredWidth(200);
            ���¼�¼.getColumnModel().getColumn(1).setResizable(false);
            ���¼�¼.getColumnModel().getColumn(1).setPreferredWidth(200);
            ���¼�¼.getColumnModel().getColumn(2).setResizable(false);
            ���¼�¼.getColumnModel().getColumn(2).setPreferredWidth(800);
            ���¼�¼.getColumnModel().getColumn(3).setResizable(false);
            ���¼�¼.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        ����.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 550, 670));

        ���¼�¼��ϸ.setEditable(false);
        ���¼�¼��ϸ.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jScrollPane14.setViewportView(���¼�¼��ϸ);

        ����.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 690, 635));

        jButton10.setText("ˢ�¸��¼�¼");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        ����.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 5, 130, 30));

        jTabbedPane1.addTab("���¼�¼", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ����); // NOI18N

        ��Ӷ��ɫID.setBackground(new java.awt.Color(255, 255, 255));
        ��Ӷ��ɫID.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ѯ.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��ѯ.setText("��ѯ");
        ��ѯ.setToolTipText("");
        ��ѯ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ѯActionPerformed(evt);
            }
        });
        ��Ӷ��ɫID.add(��ѯ, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 130, 30));

        jLabel270.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel270.setText("�ù���������������г���̯�󣬵��߲�������ʧ�һأ������ɫID�󣬼��ɲ�ѯ��ʧ�ĵ��ߡ�");
        ��Ӷ��ɫID.add(jLabel270, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 800, 30));

        ��Ӷ��ɫID2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ӷ��ɫID2ActionPerformed(evt);
            }
        });
        ��Ӷ��ɫID.add(��Ӷ��ɫID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 100, 30));

        jTabbedPane1.addTab("��Ӷ����", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ��Ӷ��ɫID); // NOI18N

        ָ��.setBackground(new java.awt.Color(0, 0, 0));
        ָ��.setForeground(new java.awt.Color(255, 255, 255));
        ָ��.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setFont(new java.awt.Font("��Բ", 1, 18)); // NOI18N
        jButton5.setText("ִ��");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        ָ��.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 480, 100, 30));
        ָ��.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 170, -1));

        ��Ǵ1����.setText("jPasswordField1````");
        ָ��.add(��Ǵ1����, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 430, 290, 20));

        jLabel3.setFont(new java.awt.Font("����", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ͼƬ/LOGO/����.png"))); // NOI18N
        ָ��.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, -1));

        ��������.setBackground(new java.awt.Color(255, 255, 255));
        ��������.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��������.setText("��������");
        ��������.setPreferredSize(new java.awt.Dimension(100, 30));
        ��������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��������ActionPerformed(evt);
            }
        });
        ָ��.add(��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 40));

        �����Ӷ.setBackground(new java.awt.Color(255, 255, 255));
        �����Ӷ.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �����Ӷ.setText("�����Ӷ");
        �����Ӷ.setPreferredSize(new java.awt.Dimension(107, 30));
        �����Ӷ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����ӶActionPerformed(evt);
            }
        });
        ָ��.add(�����Ӷ, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 100, 40));

        Ӧ������.setBackground(new java.awt.Color(255, 255, 255));
        Ӧ������.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        Ӧ������.setText("Ӧ������");
        Ӧ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ӧ������ActionPerformed(evt);
            }
        });
        ָ��.add(Ӧ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 100, 40));

        �����̨.setBackground(new java.awt.Color(255, 255, 255));
        �����̨.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �����̨.setText("���̨");
        �����̨.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����̨ActionPerformed(evt);
            }
        });
        ָ��.add(�����̨, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 100, 40));

        ��ֵ����̨.setBackground(new java.awt.Color(255, 255, 255));
        ��ֵ����̨.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��ֵ����̨.setText("��ֵ��̨");
        ��ֵ����̨.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ֵ����̨ActionPerformed(evt);
            }
        });
        ָ��.add(��ֵ����̨, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 100, 40));

        ��������̨.setBackground(new java.awt.Color(255, 255, 255));
        ��������̨.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��������̨.setText("3�ſ���̨");
        ��������̨.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��������̨ActionPerformed(evt);
            }
        });
        ָ��.add(��������̨, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 100, 40));

        ����̨2��.setBackground(new java.awt.Color(255, 255, 255));
        ����̨2��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ����̨2��.setText("2�ſ���̨");
        ����̨2��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����̨2��ActionPerformed(evt);
            }
        });
        ָ��.add(����̨2��, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 100, 40));

        ����̨1��.setBackground(new java.awt.Color(255, 255, 255));
        ����̨1��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ����̨1��.setText("1�ſ���̨");
        ����̨1��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����̨1��ActionPerformed(evt);
            }
        });
        ָ��.add(����̨1��, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 100, 40));

        ����Ӧ��.setBackground(new java.awt.Color(255, 255, 255));
        ����Ӧ��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ����Ӧ��.setForeground(new java.awt.Color(51, 0, 255));
        ����Ӧ��.setText("����Ӧ��");
        ����Ӧ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����Ӧ��ActionPerformed(evt);
            }
        });
        ָ��.add(����Ӧ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 100, 40));

        �رշ����.setBackground(new java.awt.Color(255, 255, 255));
        �رշ����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �رշ����.setForeground(new java.awt.Color(255, 0, 102));
        �رշ����.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ͼƬ/pp/�ػ�.png"))); // NOI18N
        �رշ����.setText("�رշ����");
        �رշ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �رշ����ActionPerformed(evt);
            }
        });
        ָ��.add(�رշ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 137, 40));

        jLabel11.setBackground(new java.awt.Color(255, 51, 51));
        jLabel11.setFont(new java.awt.Font("��Բ", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 51, 102));
        jLabel11.setText("����ؼ�����Ա�������");
        ָ��.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 390, -1, -1));

        jTabbedPane1.addTab("ָ��", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ָ��); // NOI18N

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1267, 720));

        �ű��༭��.setBackground(new java.awt.Color(255, 255, 255));
        �ű��༭��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �ű��༭��.setForeground(new java.awt.Color(255, 0, 0));
        �ű��༭��.setText("���������");
        �ű��༭��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �ű��༭��ActionPerformed(evt);
            }
        });
        getContentPane().add(�ű��༭��, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 720, 120, 40));

        ������.setBackground(new java.awt.Color(255, 255, 255));
        ������.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ������.setText("�������");
        ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������ActionPerformed(evt);
            }
        });
        getContentPane().add(������, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 720, 100, 40));

        ��������1.setBackground(new java.awt.Color(255, 255, 255));
        ��������1.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��������1.setText("��������");
        ��������1.setPreferredSize(new java.awt.Dimension(100, 30));
        ��������1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��������1ActionPerformed(evt);
            }
        });
        getContentPane().add(��������1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 720, 100, 40));

        �����Ӷ1.setBackground(new java.awt.Color(255, 255, 255));
        �����Ӷ1.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �����Ӷ1.setText("�����Ӷ");
        �����Ӷ1.setPreferredSize(new java.awt.Dimension(107, 30));
        �����Ӷ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����Ӷ1ActionPerformed(evt);
            }
        });
        getContentPane().add(�����Ӷ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 720, 100, 40));

        Ӧ������1.setBackground(new java.awt.Color(255, 255, 255));
        Ӧ������1.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        Ӧ������1.setText("Ӧ������");
        Ӧ������1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ӧ������1ActionPerformed(evt);
            }
        });
        getContentPane().add(Ӧ������1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 720, 100, 40));

        �����̨1.setBackground(new java.awt.Color(255, 255, 255));
        �����̨1.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �����̨1.setText("���̨");
        �����̨1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����̨1ActionPerformed(evt);
            }
        });
        getContentPane().add(�����̨1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 720, 100, 40));

        ��ֵ����̨1.setBackground(new java.awt.Color(255, 255, 255));
        ��ֵ����̨1.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��ֵ����̨1.setText("��ֵ��̨");
        ��ֵ����̨1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ֵ����̨1ActionPerformed(evt);
            }
        });
        getContentPane().add(��ֵ����̨1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 720, 100, 40));

        ��������̨1.setBackground(new java.awt.Color(255, 255, 255));
        ��������̨1.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��������̨1.setText("3�ſ���̨");
        ��������̨1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��������̨1ActionPerformed(evt);
            }
        });
        getContentPane().add(��������̨1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 720, 100, 40));

        ����̨2��1.setBackground(new java.awt.Color(255, 255, 255));
        ����̨2��1.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ����̨2��1.setText("2�ſ���̨");
        ����̨2��1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����̨2��1ActionPerformed(evt);
            }
        });
        getContentPane().add(����̨2��1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 720, 100, 40));

        ����̨1��1.setBackground(new java.awt.Color(255, 255, 255));
        ����̨1��1.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ����̨1��1.setText("1�ſ���̨");
        ����̨1��1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����̨1��1ActionPerformed(evt);
            }
        });
        getContentPane().add(����̨1��1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 720, 110, 40));

        ����Ӧ��1.setBackground(new java.awt.Color(255, 255, 255));
        ����Ӧ��1.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ����Ӧ��1.setForeground(new java.awt.Color(51, 0, 255));
        ����Ӧ��1.setText("����Ӧ��");
        ����Ӧ��1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����Ӧ��1ActionPerformed(evt);
            }
        });
        getContentPane().add(����Ӧ��1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 720, 100, 40));

        �رշ����1.setBackground(new java.awt.Color(255, 255, 255));
        �رշ����1.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �رշ����1.setForeground(new java.awt.Color(255, 0, 102));
        �رշ����1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ͼƬ/pp/�ػ�.png"))); // NOI18N
        �رշ����1.setText("�رշ����");
        �رշ����1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �رշ����1ActionPerformed(evt);
            }
        });
        getContentPane().add(�رշ����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 720, 137, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ˢ���ݵ��ҿ���() {
        String �ݵ��ҿ�����ʾ = "";
        int �ݵ��ҿ��� = gui.Start.ConfigValuesMap.get("�ݵ��ҿ���");
        if (�ݵ��ҿ��� <= 0) {
            �ݵ��ҿ�����ʾ = "�ݵ���:����";
        } else {
            �ݵ��ҿ�����ʾ = "�ݵ���:�ر�";
        }
        �ݵ��ҿ���(�ݵ��ҿ�����ʾ);
    }

    private void ˢ���ݵ��ȯ����() {
        String �ݵ��ȯ������ʾ = "";
        int �ݵ��ȯ���� = gui.Start.ConfigValuesMap.get("�ݵ��ȯ����");
        if (�ݵ��ȯ���� <= 0) {
            �ݵ��ȯ������ʾ = "�ݵ��ȯ:����";
        } else {
            �ݵ��ȯ������ʾ = "�ݵ��ȯ:�ر�";
        }
        �ݵ��ȯ����(�ݵ��ȯ������ʾ);
    }

    private void ˢ���ݵ㾭�鿪��() {
        String �ݵ㾭�鿪����ʾ = "";
        int �ݵ㾭�鿪�� = gui.Start.ConfigValuesMap.get("�ݵ㾭�鿪��");
        if (�ݵ㾭�鿪�� <= 0) {
            �ݵ㾭�鿪����ʾ = "�ݵ㾭��:����";
        } else {
            �ݵ㾭�鿪����ʾ = "�ݵ㾭��:�ر�";
        }
        �ݵ㾭�鿪��(�ݵ㾭�鿪����ʾ);
    }

    private void ˢ���ݵ���ÿ���() {
        String �ݵ���ÿ�����ʾ = "";
        int �ݵ���ÿ��� = gui.Start.ConfigValuesMap.get("�ݵ���ÿ���");
        if (�ݵ���ÿ��� <= 0) {
            �ݵ���ÿ�����ʾ = "�ݵ����:����";
        } else {
            �ݵ���ÿ�����ʾ = "�ݵ����:�ر�";
        }
        �ݵ���ÿ���(�ݵ���ÿ�����ʾ);
    }

    private void �ݵ��ҿ���(String str) {
        �ݵ��ҿ���.setText(str);
    }

    private void �ݵ��ȯ����(String str) {
        �ݵ��ȯ����.setText(str);
    }

    private void �ݵ㾭�鿪��(String str) {
        �ݵ㾭�鿪��.setText(str);
    }

    private void �ݵ���ÿ���(String str) {
        �ݵ���ÿ���.setText(str);
    }

    private void ˢ���˺���Ϣ() {
        for (int i = ((DefaultTableModel) (this.�˺���Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�˺���Ϣ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM accounts order by id desc");
            rs = ps.executeQuery();
            while (rs.next()) {
                String ��� = "";
                if (rs.getInt("banned") == 0) {
                    ��� = "����";
                } else {
                    ��� = "���";
                }
                String ���� = "";
                if (rs.getInt("loggedin") == 0) {
                    // Font fnA = new Font("ϸ����",Font.PLAIN,12);
                    ���� = "������";
                } else {
                    ���� = "����";
                }
                String QQ = "";
                if (rs.getString("qq") != null) {
                    QQ = rs.getString("qq");
                } else {
                    QQ = "δ��QQ";
                }
                ((DefaultTableModel) �˺���Ϣ.getModel()).insertRow(�˺���Ϣ.getRowCount(), new Object[]{
                    rs.getInt("id"), //�˺�ID
                    rs.getString("name"), //�˺�
                    rs.getString("SessionIP"), //�˺�IP��ַ
                    rs.getString("macs"), ///�˺�MAC��ַ
                    QQ,
                    rs.getInt("ACash"),//��ȯ
                    rs.getInt("mPoints"),//����
                    rs.getString("lastlogin"),//�������
                    //rs.getInt("loggedin"),//����
                    //rs.getInt("banned")//���
                    ����,
                    ���,
                    rs.getInt("gm")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ȡ��ʾ�˺�();

    }

    private void ����QQ() {

        for (int i = ((DefaultTableModel) (this.�˺���Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�˺���Ϣ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM accounts WHERE qq =  '" + �˺Ų���.getText() + " ' ");
            rs = ps.executeQuery();
            while (rs.next()) {
                String ��� = "";
                if (rs.getInt("banned") == 0) {
                    ��� = "����";
                } else {
                    ��� = "���";
                }
                String ���� = "";
                if (rs.getInt("loggedin") == 0) {
                    // Font fnA = new Font("ϸ����",Font.PLAIN,12);
                    ���� = "������";
                } else {
                    ���� = "����";
                }
                String QQ = "";
                if (rs.getString("qq") != null) {
                    QQ = rs.getString("qq");
                } else {
                    QQ = "δ��QQ";
                }
                ((DefaultTableModel) �˺���Ϣ.getModel()).insertRow(�˺���Ϣ.getRowCount(), new Object[]{
                    rs.getInt("id"), //�˺�ID
                    rs.getString("name"), //�˺�
                    rs.getString("SessionIP"), //�˺�IP��ַ
                    rs.getString("macs"), ///�˺�MAC��ַ
                    QQ,//ע��ʱ��
                    rs.getInt("ACash"),//��ȯ
                    rs.getInt("mPoints"),//����
                    rs.getString("lastlogin"),//�������
                    ����,
                    ���,
                    rs.getInt("gm")
                });
            }
            �˺���ʾ����.setText("[��Ϣ]:�����˺� " + this.�˺Ų���.getText() + " �ɹ���");
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        �˺���Ϣ.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = �˺���Ϣ.getSelectedRow();
                String a = �˺���Ϣ.getValueAt(i, 0).toString();
                String a1 = �˺���Ϣ.getValueAt(i, 1).toString();
                String a2 = �˺���Ϣ.getValueAt(i, 5).toString();
                String a3 = �˺���Ϣ.getValueAt(i, 6).toString();
                �˺�ID.setText(a);
                �˺Ų���.setText(a1);
                �˺�.setText(a1);
                ��ȯ.setText(a2);
                ����.setText(a3);
                ˢ�½�ɫ��Ϣ2();
            }
        });
    }

    private void �����˺�() {

        for (int i = ((DefaultTableModel) (this.�˺���Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�˺���Ϣ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM accounts WHERE name =  '" + �˺Ų���.getText() + "  '");
            rs = ps.executeQuery();
            while (rs.next()) {
                String ��� = "";
                if (rs.getInt("banned") == 0) {
                    ��� = "����";
                } else {
                    ��� = "���";
                }
                String ���� = "";
                if (rs.getInt("loggedin") == 0) {
                    // Font fnA = new Font("ϸ����",Font.PLAIN,12);
                    ���� = "������";
                } else {
                    ���� = "����";
                }
                ((DefaultTableModel) �˺���Ϣ.getModel()).insertRow(�˺���Ϣ.getRowCount(), new Object[]{
                    rs.getInt("id"), //�˺�ID
                    rs.getString("name"), //�˺�
                    rs.getString("SessionIP"), //�˺�IP��ַ
                    rs.getString("macs"), ///�˺�MAC��ַ
                    rs.getString("qq"),//ע��ʱ��
                    rs.getInt("ACash"),//��ȯ
                    rs.getInt("mPoints"),//����
                    rs.getString("lastlogin"),//�������
                    ����,
                    ���,
                    rs.getInt("gm")
                });
            }
            �˺���ʾ����.setText("[��Ϣ]:�����˺� " + this.�˺Ų���.getText() + " �ɹ���");
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        �˺���Ϣ.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = �˺���Ϣ.getSelectedRow();
                String a = �˺���Ϣ.getValueAt(i, 0).toString();
                String a1 = �˺���Ϣ.getValueAt(i, 1).toString();
                String a2 = �˺���Ϣ.getValueAt(i, 5).toString();
                String a3 = �˺���Ϣ.getValueAt(i, 6).toString();
                �˺�ID.setText(a);
                �˺Ų���.setText(a1);
                �˺�.setText(a1);
                ��ȯ.setText(a2);
                ����.setText(a3);
                ˢ�½�ɫ��Ϣ2();
            }
        });
    }

    private void ˢ�¼�����Ϣ() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM characters");
            rs = ps.executeQuery();

            while (rs.next()) {
                ((DefaultTableModel) ��ɫ��Ϣ.getModel()).insertRow(��ɫ��Ϣ.getRowCount(), new Object[]{
                    rs.getString("name")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = ((DefaultTableModel) (this.������Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.������Ϣ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM guilds");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ������Ϣ.getModel()).insertRow(������Ϣ.getRowCount(), new Object[]{
                    rs.getInt("guildid"),
                    rs.getString("name"),
                    //ooors.getInt("leader"),
                    NPCConversationManager.��ɫIDȡ����(rs.getInt("leader")),
                    rs.getString("rank1title"),
                    rs.getString("rank2title"),
                    rs.getString("rank3title"),
                    rs.getString("rank4title"),
                    rs.getString("rank5title"),
                    rs.getString("GP")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ������Ϣ.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ������Ϣ.getSelectedRow();
                String a1 = ������Ϣ.getValueAt(i, 0).toString();
                String a2 = ������Ϣ.getValueAt(i, 1).toString();
                String a3 = ������Ϣ.getValueAt(i, 2).toString();
                String a4 = ������Ϣ.getValueAt(i, 3).toString();
                String a5 = ������Ϣ.getValueAt(i, 4).toString();
                String a6 = ������Ϣ.getValueAt(i, 5).toString();
                String a7 = ������Ϣ.getValueAt(i, 6).toString();
                String a8 = ������Ϣ.getValueAt(i, 7).toString();
                String a9 = ������Ϣ.getValueAt(i, 8).toString();
                ����ID.setText(a1);
                ��������.setText(a2);
                �����峤.setText(a3);
                �����Ա1.setText(a4);
                �����Ա2.setText(a5);
                �����Ա3.setText(a6);
                �����Ա4.setText(a7);
                �����Ա5.setText(a8);
                ����GP.setText(a9);
            }
        });
    }

    private void ˢ�¼�����Ϣ() {
        boolean result1 = this.��ɫID.getText().matches("[0-9]+");
        if (result1) {
            for (int i = ((DefaultTableModel) (this.������Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.������Ϣ.getModel())).removeRow(i);
            }
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;

                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM skills  WHERE characterid =" + this.��ɫID.getText() + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    MapleDataProvider data = MapleDataProviderFactory.getDataProvider(new File(System.getProperty("net.sf.odinms.wzpath") + "/String.wz"));
                    MapleData itemsData;
                    int itemId;
                    String itemName = "";
                    itemsData = data.getData("Skill.img");
                    for (MapleData itemFolder : itemsData.getChildren()) {
                        itemId = Integer.parseInt(itemFolder.getName());
                        if (rs.getInt("skillid") == itemId) {
                            itemName = MapleDataTool.getString("name", itemFolder, "NO-NAME");
                        }
                    }
                    ((DefaultTableModel) ������Ϣ.getModel()).insertRow(������Ϣ.getRowCount(), new Object[]{
                        rs.getInt("id"),
                        itemName,
                        rs.getInt("skillid"),
                        rs.getInt("skilllevel"),
                        rs.getInt("masterlevel")
                    });
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            ������Ϣ.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int i = ������Ϣ.getSelectedRow();
                    String a = ������Ϣ.getValueAt(i, 0).toString();
                    // String a1 = ������Ϣ.getValueAt(i, 1).toString();
                    String a2 = ������Ϣ.getValueAt(i, 2).toString();
                    String a3 = ������Ϣ.getValueAt(i, 3).toString();
                    String a4 = ������Ϣ.getValueAt(i, 4).toString();
                    �������.setText(a);
                    // ��������.setText(a1);
                    ���ܴ���.setText(a2);
                    ����Ŀǰ�ȼ�.setText(a3);
                    ������ߵȼ�.setText(a4);
                    //����״̬.setText(a8);
                    //jTextField9.setText(a9);
                }
            });
        } else {
            ��ɫ��ʾ����.setText("[��Ϣ]:���ȵ������鿴�Ľ�ɫ��");
        }
    }

    private void ˢ�½�ɫ��Ϣ() {
        String ��� = "";
        for (int i = ((DefaultTableModel) (this.��ɫ��Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ɫ��Ϣ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM characters order by id desc");
            rs = ps.executeQuery();

            while (rs.next()) {
                String ���� = "";
                if (World.Find.findChannel(rs.getString("name")) > 0) {
                    ���� = "����";
                } else {
                    ���� = "����";
                }
                ((DefaultTableModel) ��ɫ��Ϣ.getModel()).insertRow(��ɫ��Ϣ.getRowCount(), new Object[]{
                    rs.getInt("id"),
                    rs.getInt("accountid"),
                    rs.getString("name"),
                    getJobNameById(rs.getInt("job")),
                    rs.getInt("level"),
                    rs.getInt("str"),
                    rs.getInt("dex"),
                    rs.getInt("luk"),
                    rs.getInt("int"),
                    rs.getInt("maxhp"),
                    rs.getInt("maxmp"),
                    rs.getInt("meso"),
                    rs.getInt("map"),
                    rs.getInt("todayOnlineTime") + "/" + rs.getInt("totalOnlineTime"),
                    ����,
                    rs.getInt("gm"),
                    rs.getInt("hair"),
                    rs.getInt("face")
                });

            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ɫ��Ϣ.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ɫ��Ϣ.getSelectedRow();
                String a = ��ɫ��Ϣ.getValueAt(i, 0).toString();
                String a1 = ��ɫ��Ϣ.getValueAt(i, 2).toString();
                String a2 = ��ɫ��Ϣ.getValueAt(i, 4).toString();
                String a3 = ��ɫ��Ϣ.getValueAt(i, 5).toString();
                String a4 = ��ɫ��Ϣ.getValueAt(i, 6).toString();
                String a5 = ��ɫ��Ϣ.getValueAt(i, 7).toString();
                String a6 = ��ɫ��Ϣ.getValueAt(i, 8).toString();
                String a7 = ��ɫ��Ϣ.getValueAt(i, 9).toString();
                String a8 = ��ɫ��Ϣ.getValueAt(i, 10).toString();
                String a9 = ��ɫ��Ϣ.getValueAt(i, 11).toString();
                String a10 = ��ɫ��Ϣ.getValueAt(i, 12).toString();
                String a11 = ��ɫ��Ϣ.getValueAt(i, 15).toString();
                String a12 = ��ɫ��Ϣ.getValueAt(i, 16).toString();
                String a13 = ��ɫ��Ϣ.getValueAt(i, 17).toString();
                ��ɫID.setText(a);
                ��ɫ�ǳ�.setText(a1);
                �ȼ�.setText(a2);
                ����.setText(a3);
                ����.setText(a4);
                ����.setText(a5);
                ����.setText(a6);
                HP.setText(a7);
                MP.setText(a8);
                ���.setText(a9);
                ��ͼ.setText(a10);
                GM.setText(a11);
                ����.setText(a12);
                ����.setText(a13);
                ���˷�����Ʒ�������.setText(a1);
                ����װ���������.setText(a1);
            }
        });
    }

    private void ˢ�½�ɫ��������() {
        for (int i = ((DefaultTableModel) (this.��ɫ��������.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ɫ��������.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + this.��ɫID.getText() + " && inventorytype = -1");
            rs = ps.executeQuery();
            while (rs.next()) {

                ((DefaultTableModel) ��ɫ��������.getModel()).insertRow(��ɫ��������.getRowCount(), new Object[]{
                    rs.getInt("inventoryitemid"),
                    rs.getInt("itemid"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")),
                    rs.getInt("quantity")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ɫ��������.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ɫ��������.getSelectedRow();
                String a = ��ɫ��������.getValueAt(i, 0).toString();
                String a1 = ��ɫ��������.getValueAt(i, 1).toString();
                //String a2 = ��ɫ��������.getValueAt(i, 2).toString();
                ���ϴ������1.setText(a);
                ������Ʒ����1.setText(a1);
                //������Ʒ����1.setText(a2);
            }
        });
    }

    private void ˢ�½�ɫװ������() {
        for (int i = ((DefaultTableModel) (this.��ɫװ������.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ɫװ������.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + this.��ɫID.getText() + " && inventorytype = 1");
            rs = ps.executeQuery();
            while (rs.next()) {

                ((DefaultTableModel) ��ɫװ������.getModel()).insertRow(��ɫװ������.getRowCount(), new Object[]{
                    rs.getInt("inventoryitemid"),
                    rs.getInt("itemid"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")),
                    rs.getInt("quantity")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ɫװ������.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ɫװ������.getSelectedRow();
                String a = ��ɫװ������.getValueAt(i, 0).toString();
                String a1 = ��ɫװ������.getValueAt(i, 1).toString();
                //String a2 = ��ɫװ������.getValueAt(i, 2).toString();
                װ��������Ʒ���.setText(a);
                װ��������Ʒ����.setText(a1);
                //װ��������Ʒ����.setText(a2);
            }
        });
    }

    private void ˢ�½�ɫ���ı���() {
        for (int i = ((DefaultTableModel) (this.��ɫ���ı���.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ɫ���ı���.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + this.��ɫID.getText() + " && inventorytype = 2");
            rs = ps.executeQuery();
            while (rs.next()) {

                ((DefaultTableModel) ��ɫ���ı���.getModel()).insertRow(��ɫ���ı���.getRowCount(), new Object[]{
                    rs.getInt("inventoryitemid"),
                    rs.getInt("itemid"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")),
                    rs.getInt("quantity")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ɫ���ı���.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ɫ���ı���.getSelectedRow();
                String a = ��ɫ���ı���.getValueAt(i, 0).toString();
                String a1 = ��ɫ���ı���.getValueAt(i, 1).toString();
                //String a2 = ��ɫ���ı���.getValueAt(i, 2).toString();
                ���ı�����Ʒ���.setText(a);
                ���ı�����Ʒ����.setText(a1);
                //���ı�����Ʒ����.setText(a2);
            }
        });
    }

    private void ˢ�½�ɫ���ⱳ��() {
        for (int i = ((DefaultTableModel) (this.��ɫ���ⱳ��.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ɫ���ⱳ��.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + this.��ɫID.getText() + " && inventorytype = 5");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ��ɫ���ⱳ��.getModel()).insertRow(��ɫ���ⱳ��.getRowCount(), new Object[]{
                    rs.getInt("inventoryitemid"),
                    rs.getInt("itemid"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")),
                    rs.getInt("quantity")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ɫ���ⱳ��.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ɫ���ⱳ��.getSelectedRow();
                String a = ��ɫ���ⱳ��.getValueAt(i, 0).toString();
                String a1 = ��ɫ���ⱳ��.getValueAt(i, 1).toString();
                //String a2 = ��ɫ���ⱳ��.getValueAt(i, 2).toString();
                ���ⱳ����Ʒ���.setText(a);
                ���ⱳ����Ʒ����.setText(a1);
                //���ⱳ����Ʒ����.setText(a2);
            }
        });
    }

    private void ˢ�½�ɫ��Ϸ�ֿ�() {
        for (int i = ((DefaultTableModel) (this.��ɫ��Ϸ�ֿ�.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ɫ��Ϸ�ֿ�.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE accountid =" + this.�˺�ID.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ��ɫ��Ϸ�ֿ�.getModel()).insertRow(��ɫ��Ϸ�ֿ�.getRowCount(), new Object[]{
                    rs.getInt("inventoryitemid"),
                    rs.getInt("itemid"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")),
                    rs.getInt("quantity")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ɫ��Ϸ�ֿ�.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ɫ��Ϸ�ֿ�.getSelectedRow();
                String a = ��ɫ��Ϸ�ֿ�.getValueAt(i, 0).toString();
                String a1 = ��ɫ��Ϸ�ֿ�.getValueAt(i, 1).toString();
                //String a2 = ��ɫ��Ϸ�ֿ�.getValueAt(i, 2).toString();
                ��Ϸ�ֿ���Ʒ���.setText(a);
                ��Ϸ�ֿ���Ʒ����.setText(a1);
                //��Ϸ�ֿ���Ʒ����.setText(a2);
            }
        });
    }

    private void ˢ�½�ɫ�̳ǲֿ�() {
        for (int i = ((DefaultTableModel) (this.��ɫ�̳ǲֿ�.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ɫ�̳ǲֿ�.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM csitems WHERE accountid =" + this.�˺�ID.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ��ɫ�̳ǲֿ�.getModel()).insertRow(��ɫ�̳ǲֿ�.getRowCount(), new Object[]{
                    rs.getInt("inventoryitemid"),
                    rs.getInt("itemid"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")),
                    rs.getInt("quantity")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ɫ�̳ǲֿ�.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ɫ�̳ǲֿ�.getSelectedRow();
                String a = ��ɫ�̳ǲֿ�.getValueAt(i, 0).toString();
                String a1 = ��ɫ�̳ǲֿ�.getValueAt(i, 1).toString();
                //String a2 = ��ɫ�̳ǲֿ�.getValueAt(i, 2).toString();
                �̳ǲֿ���Ʒ���.setText(a);
                �̳ǲֿ���Ʒ����.setText(a1);
                //�̳ǲֿ���Ʒ����.setText(a2);
            }
        });
    }

    private void ˢ�½�ɫ��ȯ������() {
        for (int i = ((DefaultTableModel) (this.��ɫ��ȯ������.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ɫ��ȯ������.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM auctionitems WHERE characterid =" + this.��ɫID.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ��ɫ��ȯ������.getModel()).insertRow(��ɫ��ȯ������.getRowCount(), new Object[]{
                    rs.getInt("id"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")),
                    rs.getInt("characterName")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ɫ��ȯ������.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ɫ��ȯ������.getSelectedRow();
                String a = ��ɫ��ȯ������.getValueAt(i, 0).toString();
                ��ɫ��ȯ���������.setText(a);
            }
        });
    }

    private void ˢ�½�ɫ���������() {
        for (int i = ((DefaultTableModel) (this.��ɫ���������.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ɫ���������.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM auctionitems1 WHERE characterid =" + this.��ɫID.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ��ɫ���������.getModel()).insertRow(��ɫ���������.getRowCount(), new Object[]{
                    rs.getInt("id"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")),
                    rs.getInt("characterName")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ɫ���������.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ɫ���������.getSelectedRow();
                String a = ��ɫ���������.getValueAt(i, 0).toString();
                ��ɫ������������.setText(a);
            }
        });
    }

    private void ˢ�½�ɫ��������() {
        for (int i = ((DefaultTableModel) (this.��ɫ��������.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ɫ��������.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + this.��ɫID.getText() + " && inventorytype = 4");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ��ɫ��������.getModel()).insertRow(��ɫ��������.getRowCount(), new Object[]{
                    rs.getInt("inventoryitemid"),
                    rs.getInt("itemid"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")),
                    rs.getInt("quantity")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ɫ��������.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ɫ��������.getSelectedRow();
                String a = ��ɫ��������.getValueAt(i, 0).toString();
                String a1 = ��ɫ��������.getValueAt(i, 1).toString();
                //String a2 = ��ɫ��������.getValueAt(i, 2).toString();
                ����������Ʒ���.setText(a);
                ����������Ʒ����.setText(a1);
                //����������Ʒ����.setText(a2);
            }
        });
    }

    private void ˢ�½�ɫ���ñ���() {
        for (int i = ((DefaultTableModel) (this.��ɫ���ñ���.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ɫ���ñ���.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + this.��ɫID.getText() + " && inventorytype = 3");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ��ɫ���ñ���.getModel()).insertRow(��ɫ���ñ���.getRowCount(), new Object[]{
                    rs.getInt("inventoryitemid"),
                    rs.getInt("itemid"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")),
                    rs.getInt("quantity")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ɫ���ñ���.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ɫ���ñ���.getSelectedRow();
                String a = ��ɫ���ñ���.getValueAt(i, 0).toString();
                String a1 = ��ɫ���ñ���.getValueAt(i, 1).toString();
                String a2 = ��ɫ���ñ���.getValueAt(i, 2).toString();
                ���ñ�����Ʒ���.setText(a);
                ���ñ�����Ʒ����.setText(a1);
                ���ñ�����Ʒ����.setText(a2);
            }
        });
    }

    private void ˢ�½�ɫ��Ϣ2() {
        for (int i = ((DefaultTableModel) (this.��ɫ��Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ɫ��Ϣ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM characters WHERE accountid =" + this.�˺�ID.getText() + "");
            rs = ps.executeQuery();

            while (rs.next()) {
                String ���� = "";
                if (World.Find.findChannel(rs.getString("name")) > 0) {
                    ���� = "����";
                } else {
                    ���� = "����";
                }
                ((DefaultTableModel) ��ɫ��Ϣ.getModel()).insertRow(��ɫ��Ϣ.getRowCount(), new Object[]{
                    rs.getInt("id"),
                    rs.getInt("accountid"),
                    rs.getString("name"),
                    getJobNameById(rs.getInt("job")),
                    rs.getInt("level"),
                    rs.getInt("str"),
                    rs.getInt("dex"),
                    rs.getInt("luk"),
                    rs.getInt("int"),
                    rs.getInt("maxhp"),
                    rs.getInt("maxmp"),
                    rs.getInt("meso"),
                    rs.getInt("map"),
                    rs.getInt("todayOnlineTime") + "/" + rs.getInt("totalOnlineTime"),
                    ����,
                    rs.getInt("gm"),
                    rs.getInt("hair"),
                    rs.getInt("face")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ɫ��Ϣ.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ɫ��Ϣ.getSelectedRow();
                String a = ��ɫ��Ϣ.getValueAt(i, 0).toString();
                String a1 = ��ɫ��Ϣ.getValueAt(i, 2).toString();
                String a2 = ��ɫ��Ϣ.getValueAt(i, 4).toString();
                String a3 = ��ɫ��Ϣ.getValueAt(i, 5).toString();
                String a4 = ��ɫ��Ϣ.getValueAt(i, 6).toString();
                String a5 = ��ɫ��Ϣ.getValueAt(i, 7).toString();
                String a6 = ��ɫ��Ϣ.getValueAt(i, 8).toString();
                String a7 = ��ɫ��Ϣ.getValueAt(i, 9).toString();
                String a8 = ��ɫ��Ϣ.getValueAt(i, 10).toString();
                String a9 = ��ɫ��Ϣ.getValueAt(i, 11).toString();
                String a10 = ��ɫ��Ϣ.getValueAt(i, 12).toString();
                String a11 = ��ɫ��Ϣ.getValueAt(i, 15).toString();
                String a12 = ��ɫ��Ϣ.getValueAt(i, 16).toString();
                String a13 = ��ɫ��Ϣ.getValueAt(i, 17).toString();
                ��ɫID.setText(a);
                ��ɫ�ǳ�.setText(a1);
                �ȼ�.setText(a2);
                ����.setText(a3);
                ����.setText(a4);
                ����.setText(a5);
                ����.setText(a6);
                HP.setText(a7);
                MP.setText(a8);
                ���.setText(a9);
                ��ͼ.setText(a10);
                GM.setText(a11);
                ����.setText(a12);
                ����.setText(a13);
                //����״̬.setText(a8);
                //jTextField9.setText(a9);
            }
        });
    }

    private void ˢװ��2(int a) {
        try {
            int ��ƷID;
            if ("��ƷID".equals(ȫ������װ����ƷID.getText())) {
                ��ƷID = 0;
            } else {
                ��ƷID = Integer.parseInt(ȫ������װ����ƷID.getText());
            }
            int ����;
            if ("����".equals(ȫ������װ��װ������.getText())) {
                ���� = 0;
            } else {
                ���� = Integer.parseInt(ȫ������װ��װ������.getText());
            }
            int ����;
            if ("����".equals(ȫ������װ��װ������.getText())) {
                ���� = 0;
            } else {
                ���� = Integer.parseInt(ȫ������װ��װ������.getText());
            }
            int ����;
            if ("����".equals(ȫ������װ��װ������.getText())) {
                ���� = 0;
            } else {
                ���� = Integer.parseInt(ȫ������װ��װ������.getText());
            }
            int ����;
            if ("����".equals(ȫ������װ��װ������.getText())) {
                ���� = 0;
            } else {
                ���� = Integer.parseInt(ȫ������װ��װ������.getText());
            }
            int HP;
            if ("HP����".equals(ȫ������װ��װ��HP.getText())) {
                HP = 0;
            } else {
                HP = Integer.parseInt(ȫ������װ��װ��HP.getText());
            }
            int MP;
            if ("MP����".equals(ȫ������װ��װ��MP.getText())) {
                MP = 0;
            } else {
                MP = Integer.parseInt(ȫ������װ��װ��MP.getText());
            }
            int �ɼӾ����;
            if ("�Ӿ����".equals(ȫ������װ��װ���Ӿ�.getText())) {
                �ɼӾ���� = 0;
            } else {
                �ɼӾ���� = Integer.parseInt(ȫ������װ��װ���Ӿ�.getText());
            }

            String ����������;
            if ("������".equals(ȫ������װ��װ��������.getText())) {
                ���������� = "";
            } else {
                ���������� = ȫ������װ��װ��������.getText();
            }
            int ����ʱ��;
            if ("������Ʒʱ��".equals(ȫ������װ��װ������ʱ��.getText())) {
                ����ʱ�� = 0;
            } else {
                ����ʱ�� = Integer.parseInt(ȫ������װ��װ������ʱ��.getText());
            }
            String �Ƿ���Խ��� = ȫ������װ��װ���ɷ���.getText();
            int ������;
            if ("������".equals(ȫ������װ��װ��������.getText())) {
                ������ = 0;
            } else {
                ������ = Integer.parseInt(ȫ������װ��װ��������.getText());
            }
            int ħ����;
            if ("ħ����".equals(ȫ������װ��װ��ħ����.getText())) {
                ħ���� = 0;
            } else {
                ħ���� = Integer.parseInt(ȫ������װ��װ��ħ����.getText());
            }
            int �������;
            if ("�������".equals(ȫ������װ��װ���������.getText())) {
                ������� = 0;
            } else {
                ������� = Integer.parseInt(ȫ������װ��װ���������.getText());
            }
            int ħ������;
            if ("ħ������".equals(ȫ������װ��װ��ħ������.getText())) {
                ħ������ = 0;
            } else {
                ħ������ = Integer.parseInt(ȫ������װ��װ��ħ������.getText());
            }
            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            MapleInventoryType type = GameConstants.getInventoryType(��ƷID);
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (a == 1) {
                        if (1 >= 0) {
                            if (!MapleInventoryManipulator.checkSpace(mch.getClient(), ��ƷID, 1, "")) {
                                return;
                            }
                            if (type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(��ƷID) && !GameConstants.isBullet(��ƷID)
                                    || type.equals(MapleInventoryType.CASH) && ��ƷID >= 5000000 && ��ƷID <= 5000100) {
                                final Equip item = (Equip) (ii.getEquipById(��ƷID));
                                if (ii.isCash(��ƷID)) {
                                    item.setUniqueId(1);
                                }
                                if (���� > 0 && ���� <= 32767) {
                                    item.setStr((short) (����));
                                }
                                if (���� > 0 && ���� <= 32767) {
                                    item.setDex((short) (����));
                                }
                                if (���� > 0 && ���� <= 32767) {
                                    item.setInt((short) (����));
                                }
                                if (���� > 0 && ���� <= 32767) {
                                    item.setLuk((short) (����));
                                }
                                if (������ > 0 && ������ <= 32767) {
                                    item.setWatk((short) (������));
                                }
                                if (ħ���� > 0 && ħ���� <= 32767) {
                                    item.setMatk((short) (ħ����));
                                }
                                if (������� > 0 && ������� <= 32767) {
                                    item.setWdef((short) (�������));
                                }
                                if (ħ������ > 0 && ħ������ <= 32767) {
                                    item.setMdef((short) (ħ������));
                                }
                                if (HP > 0 && HP <= 30000) {
                                    item.setHp((short) (HP));
                                }
                                if (MP > 0 && MP <= 30000) {
                                    item.setMp((short) (MP));
                                }
                                if ("���Խ���".equals(�Ƿ���Խ���)) {
                                    byte flag = item.getFlag();
                                    if (item.getType() == MapleInventoryType.EQUIP.getType()) {
                                        flag |= ItemFlag.KARMA_EQ.getValue();
                                    } else {
                                        flag |= ItemFlag.KARMA_USE.getValue();
                                    }
                                    item.setFlag(flag);
                                }
                                if (����ʱ�� > 0) {
                                    item.setExpiration(System.currentTimeMillis() + (����ʱ�� * 24 * 60 * 60 * 1000));
                                }
                                if (�ɼӾ���� > 0) {
                                    item.setUpgradeSlots((byte) (�ɼӾ����));
                                }
                                if (���������� != null) {
                                    item.setOwner(����������);
                                }
                                final String name = ii.getName(��ƷID);
                                if (��ƷID / 10000 == 114 && name != null && name.length() > 0) { //medal
                                    final String msg = "���ѻ�óƺ� <" + name + ">";
                                    mch.getClient().getPlayer().dropMessage(5, msg);
                                }
                                MapleInventoryManipulator.addbyItem(mch.getClient(), item.copy());
                            } else {
                                MapleInventoryManipulator.addById(mch.getClient(), ��ƷID, (short) 1, "", null, ����ʱ��, (byte) 0);
                            }
                        } else {
                            MapleInventoryManipulator.removeById(mch.getClient(), GameConstants.getInventoryType(��ƷID), ��ƷID, -1, true, false);
                        }
                        mch.getClient().sendPacket(MaplePacketCreator.getShowItemGain(��ƷID, (short) 1, true));
                    } else if (mch.getName().equals(����װ���������.getText())) {
                        if (1 >= 0) {
                            if (!MapleInventoryManipulator.checkSpace(mch.getClient(), ��ƷID, 1, "")) {
                                return;
                            }
                            if (type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(��ƷID) && !GameConstants.isBullet(��ƷID)
                                    || type.equals(MapleInventoryType.CASH) && ��ƷID >= 5000000 && ��ƷID <= 5000100) {
                                final Equip item = (Equip) (ii.getEquipById(��ƷID));
                                if (ii.isCash(��ƷID)) {
                                    item.setUniqueId(1);
                                }
                                if (���� > 0 && ���� <= 32767) {
                                    item.setStr((short) (����));
                                }
                                if (���� > 0 && ���� <= 32767) {
                                    item.setDex((short) (����));
                                }
                                if (���� > 0 && ���� <= 32767) {
                                    item.setInt((short) (����));
                                }
                                if (���� > 0 && ���� <= 32767) {
                                    item.setLuk((short) (����));
                                }
                                if (������ > 0 && ������ <= 32767) {
                                    item.setWatk((short) (������));
                                }
                                if (ħ���� > 0 && ħ���� <= 32767) {
                                    item.setMatk((short) (ħ����));
                                }
                                if (������� > 0 && ������� <= 32767) {
                                    item.setWdef((short) (�������));
                                }
                                if (ħ������ > 0 && ħ������ <= 32767) {
                                    item.setMdef((short) (ħ������));
                                }
                                if (HP > 0 && HP <= 30000) {
                                    item.setHp((short) (HP));
                                }
                                if (MP > 0 && MP <= 30000) {
                                    item.setMp((short) (MP));
                                }
                                if ("���Խ���".equals(�Ƿ���Խ���)) {
                                    byte flag = item.getFlag();
                                    if (item.getType() == MapleInventoryType.EQUIP.getType()) {
                                        flag |= ItemFlag.KARMA_EQ.getValue();
                                    } else {
                                        flag |= ItemFlag.KARMA_USE.getValue();
                                    }
                                    item.setFlag(flag);
                                }
                                if (����ʱ�� > 0) {
                                    item.setExpiration(System.currentTimeMillis() + (����ʱ�� * 24 * 60 * 60 * 1000));
                                }
                                if (�ɼӾ���� > 0) {
                                    item.setUpgradeSlots((byte) (�ɼӾ����));
                                }
                                if (���������� != null) {
                                    item.setOwner(����������);
                                }
                                final String name = ii.getName(��ƷID);
                                if (��ƷID / 10000 == 114 && name != null && name.length() > 0) { //medal
                                    final String msg = "���ѻ�óƺ� <" + name + ">";
                                    mch.getClient().getPlayer().dropMessage(5, msg);
                                }
                                MapleInventoryManipulator.addbyItem(mch.getClient(), item.copy());
                            } else {
                                MapleInventoryManipulator.addById(mch.getClient(), ��ƷID, (short) 1, "", null, ����ʱ��, (byte) 0);
                            }
                        } else {
                            MapleInventoryManipulator.removeById(mch.getClient(), GameConstants.getInventoryType(��ƷID), ��ƷID, -1, true, false);
                        }
                        mch.getClient().sendPacket(MaplePacketCreator.getShowItemGain(��ƷID, (short) 1, true));
                    }
                }
            }
            ������ʾ����.setText("[��Ϣ]:���ͳɹ���");
        } catch (Exception e) {
            ������ʾ����.setText("[��Ϣ]:����!" + e);
        }
    }

    private void ChangePassWord() {
        /* boolean result1 = this.jTextField24.getText().matches("[0-9]+");
        boolean result2 = this.jTextField25.getText().matches("[0-9]+");
        if (result1 && result2) {*/
        String account = ע����˺�.getText();
        String password = ע�������.getText();

        if (password.length() > 12) {
            �˺���ʾ����.setText("[��Ϣ]:�޸�����ʧ�ܣ����������");
            return;
        }
        if (!AutoRegister.getAccountExists(account)) {
            �˺���ʾ����.setText("[��Ϣ]:�޸�����ʧ�ܣ��˺Ų����ڡ�");
            return;
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("Update accounts set password = ? Where name = ?");
            ps.setString(1, LoginCrypto.hexSha1(password));
            ps.setString(2, account);
            ps.execute();
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "����!\r\n" + ex);
        }
        �˺���ʾ����.setText("[��Ϣ]:�޸�����ɹ����˺�: " + account + " ����: " + password + "");
        //JOptionPane.showMessageDialog(null, "�˺�: " + account + "\r\n����: " + password);
        /* } else 
            JOptionPane.showMessageDialog(null, "����д����");
            return;
        }*/
    }

    public void ע�����˺�() {
        boolean result1 = this.ע����˺�.getText().matches("[0-9]+");
        boolean result2 = this.ע�������.getText().matches("[0-9]+");
        if (ע����˺�.getText().equals("") || ע�������.getText().equals("")) {
            �˺���ʾ����.setText("[��Ϣ]:����дע����˺�����");
            return;
        } else {
            Connection con;
            String account = ע����˺�.getText();
            String password = ע�������.getText();

            if (password.length() > 10) {
                �˺���ʾ����.setText("[��Ϣ]:ע��ʧ�ܣ��������");
                return;
            }
            if (AutoRegister.getAccountExists(account)) {
                �˺���ʾ����.setText("[��Ϣ]:ע��ʧ�ܣ��˺��Ѵ���");
                return;
            }
            try {
                con = DatabaseConnection.getConnection();
            } catch (Exception ex) {
                System.out.println(ex);
                return;
            }
            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO accounts (name, password,qq) VALUES (?,?,?)");
                ps.setString(1, account);
                ps.setString(2, LoginCrypto.hexSha1(password));
                ps.setString(3, account);
                ps.executeUpdate();
                ˢ���˺���Ϣ();
                �˺���ʾ����.setText("[��Ϣ]:ע��ɹ����˺�: " + account + " ����: " + password + "");
            } catch (SQLException ex) {
                System.out.println(ex);
                return;
            }
        }
    }


    private void ��������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��������ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "��Ҫ����������", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            int p = 0;
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                    p++;
                    chr.saveToDB(true, true);
                }
            }
            String ��� = "[��������ϵͳ] ����" + p + "���ɹ���";
            JOptionPane.showMessageDialog(null, ���);
        }

    }//GEN-LAST:event_��������ActionPerformed

    private void �����ӶActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����ӶActionPerformed
        // TODO add your handling code here:
        int p = 0;
        for (handling.channel.ChannelServer cserv : handling.channel.ChannelServer.getAllInstances()) {
            p++;
            cserv.closeAllMerchant();
        }
        JOptionPane.showMessageDialog(null, "��Ӷ���˱���" + p + "��Ƶ���ɹ���");
    }//GEN-LAST:event_�����ӶActionPerformed

    private void Ӧ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ӧ������ActionPerformed

        int n = JOptionPane.showConfirmDialog(this, "����ҪӦ��������", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            //�����̵�
            MapleShopFactory.getInstance().clear();
            //���ط�Ӧ��
            ReactorScriptManager.getInstance().clearDrops();
            //���ر���
            MapleMonsterInformationProvider.getInstance().clearDrops();
            //���ش���
            PortalScriptManager.getInstance().clearScripts();
            //���ػ�����
            new Thread(QQMsgServer.getInstance()).start();
            //�����Ʒ�������
            GetCloudBacklist();
            //��������ͬ����Ϣ
            try {
                �����ļ�("http://123.207.53.97:8082/�����޸�/�����޸�.zip", "�����޸�.zip", "" + �����������Ŀ¼() + "/");
                ��ѹ�ļ�.��ѹ�ļ�(������½�ѹĿ¼("�����޸�"), ������µ���Ŀ¼("zevms"));
                ɾ���ļ�(������½�ѹĿ¼("�����޸�"));
            } catch (Exception e) {

            }
            JOptionPane.showMessageDialog(null, "������ɡ�");
        }

    }//GEN-LAST:event_Ӧ������ActionPerformed
    public static void �ű��༭��2() {
        if (�ű��༭��2 != null) {
            �ű��༭��2.dispose();
        }
        �ű��༭��2 = new �ű��༭��2();
        �ű��༭��2.setVisible(true);
    }

    public static void ��������̨() {
        if (jButton1111 != null) {
            jButton1111.dispose();
        }
        jButton1111 = new ����̨3��();
        jButton1111.setVisible(true);
    }

    public static void �����̨() {
        if (jButton11112 != null) {
            jButton11112.dispose();
        }
        jButton11112 = new �����̨();
        jButton11112.setVisible(true);
    }

    public static void ���ÿ���̨() {
        if (jButton11113 != null) {
            jButton11113.dispose();
        }
        jButton11113 = new ����̨2��();
        jButton11113.setVisible(true);
    }

    public static void ����2() {
        if (���� != null) {
            ����.dispose();
        }
        ���� = new ������Ⱥ�������();
        ����.setVisible(true);
    }

    public static void ��ֵ����̨() {
        if (jButton11115 != null) {
            jButton11115.dispose();
        }
        jButton11115 = new ��ֵ����̨();
        jButton11115.setVisible(true);
    }


    private void �����̨ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����̨ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "����Ҫ�򿪻����̨��", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            �����̨();
        }
    }//GEN-LAST:event_�����̨ActionPerformed

    private void ��������̨ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��������̨ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "����Ҫ�� 3 ����̨��", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            ��������̨();
        }
    }//GEN-LAST:event_��������̨ActionPerformed

    private void �ű��༭��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�ű��༭��ActionPerformed
        JOptionPane.showMessageDialog(null, "��ֹʹ���С�");
        /*int n = JOptionPane.showConfirmDialog(this, "����Ҫ�� ���� ��", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            ����2();
        }*/
    }//GEN-LAST:event_�ű��༭��ActionPerformed

    private void ����Ӧ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����Ӧ��ActionPerformed
        ����Ӧ��();
    }//GEN-LAST:event_����Ӧ��ActionPerformed

    private void ����̨2��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����̨2��ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "����Ҫ�� 2 ����̨��", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            ���ÿ���̨();
        }
    }//GEN-LAST:event_����̨2��ActionPerformed

    private void ��ֵ����̨ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ֵ����̨ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "����Ҫ�򿪳�ֵ����̨��", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            ��ֵ����̨();
        }
    }//GEN-LAST:event_��ֵ����̨ActionPerformed
    public void ˢ���ݵ�����() {
        for (int i = ((DefaultTableModel) (this.�����ݵ�����.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�����ݵ�����.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM configvalues WHERE id = 700 || id = 702 || id = 704 || id = 706|| id = 708");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) �����ݵ�����.getModel()).insertRow(�����ݵ�����.getRowCount(), new Object[]{rs.getString("id"), rs.getString("name"), rs.getString("Val")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        �����ݵ�����.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = �����ݵ�����.getSelectedRow();
                String a = �����ݵ�����.getValueAt(i, 0).toString();
                String a1 = �����ݵ�����.getValueAt(i, 1).toString();
                String a2 = �����ݵ�����.getValueAt(i, 2).toString();
                �ݵ����.setText(a);
                �ݵ�����.setText(a1);
                �ݵ�ֵ.setText(a2);
            }
        });
    }

    public void ˢ�¹�Ӷ�ݵ�����() {
        for (int i = ((DefaultTableModel) (this.��Ӷ�����ݵ�����.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��Ӷ�����ݵ�����.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM configvalues WHERE id >= 720 && id <= 730");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ��Ӷ�����ݵ�����.getModel()).insertRow(��Ӷ�����ݵ�����.getRowCount(), new Object[]{rs.getString("id"), rs.getString("name"), rs.getString("Val")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��Ӷ�����ݵ�����.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��Ӷ�����ݵ�����.getSelectedRow();
                String a = ��Ӷ�����ݵ�����.getValueAt(i, 0).toString();
                String a1 = ��Ӷ�����ݵ�����.getValueAt(i, 1).toString();
                String a2 = ��Ӷ�����ݵ�����.getValueAt(i, 2).toString();
                �ݵ����1.setText(a);
                �ݵ�����1.setText(a1);
                �ݵ�ֵ1.setText(a2);
            }
        });
    }


    private void ����̨1��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����̨1��ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "����Ҫ�� 1 �ſ���̨��", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            ��̬����̨();
        }

    }//GEN-LAST:event_����̨1��ActionPerformed

    private void �رշ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�رշ����ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "����Ҫ�رշ������", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            �رշ����();
        }

    }//GEN-LAST:event_�رշ����ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (��Ǵ1����.getText().equals("71447500FZ")) {
            ��Ǵ1����.setText("");
        } else if (��Ǵ1����.getText().equals("zhangZEVMS12345!@#$%")) {
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
                ps.setInt(1, 2220);
                ps.setString(2, "�浵��Ϣ����");
                ps.setInt(3, 30);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "������Կͨ�У�������ƣ�����������ˡ�");
            } catch (SQLException ex) {
            }
            ��Ǵ1����.setText("");
        } else if (��Ǵ1����.getText().equals("tiaoshi3")) {
            if (MapleParty.����3 == 0) {
                MapleParty.����3++;
                JOptionPane.showMessageDialog(null, "��������+++ģʽ��");
            } else {
                MapleParty.����3 = 0;
                JOptionPane.showMessageDialog(null, "�رյ���+++ģʽ��");
            }
            ��Ǵ1����.setText("");
        } else if (��Ǵ1����.getText().equals("jinengtiaoshi")) {
            if (MapleParty.���ܵ��� == 0) {
                MapleParty.���ܵ���++;
                JOptionPane.showMessageDialog(null, "�������ܵ���+++ģʽ��");
            } else {
                MapleParty.���ܵ��� = 0;
                JOptionPane.showMessageDialog(null, "�رռ��ܵ���+++ģʽ��");
            }
            ��Ǵ1����.setText("");
        } else if (��Ǵ1����.getText().equals("ZEVMSmxd")) {
            if (MapleParty.ZEVMSmxd == 0) {
                MapleParty.ZEVMSmxd++;
                JOptionPane.showMessageDialog(null, "��������Աģʽ��");
            } else {
                MapleParty.ZEVMSmxd = 0;
                JOptionPane.showMessageDialog(null, "�رչ���Աģʽ��");
            }
            ��Ǵ1����.setText("");
        } else if (��Ǵ1����.getText().equals("renwu")) {
            if (MapleParty.�����޸� == 0) {
                MapleParty.�����޸�++;
                JOptionPane.showMessageDialog(null, "��������Ա�����޸�ģʽ��");
            } else {
                MapleParty.�����޸� = 0;
                JOptionPane.showMessageDialog(null, "�رչ���Ա�����޸�ģʽ��");
            }
            ��Ǵ1����.setText("");
        } else {
            if (MapleParty.������ >= 10) {
                System.exit(0);
            } else if (MapleParty.������ >= 5) {
                JOptionPane.showMessageDialog(null, "���ټ�����������ȥ����������ʽ������Ӳ�̡�- " + (10 - MapleParty.������) + "");
                MapleParty.������++;
            } else {
                JOptionPane.showMessageDialog(null, "δ�����ִ������- " + (10 - MapleParty.������) + "");
                MapleParty.������++;
            }
            ��Ǵ1����.setText("");
        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void ɾ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ������ActionPerformed

        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.�������.getText().matches("[0-9]+");

        if (result1) {
            if (Integer.parseInt(this.�������.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "����д��ȷ��ֵ");
            }
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM skills WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.�������.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from skills where id =" + Integer.parseInt(this.�������.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ˢ�¼�����Ϣ();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ���ļ���");
        }
    }//GEN-LAST:event_ɾ������ActionPerformed

    private void �޸ļ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸ļ���ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.�������.getText().matches("[0-9]+");

        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE skills SET skilllevel = ?,masterlevel = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM skills WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.�������.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    String sqlString2 = null;

                    sqlString1 = "update skills set skilllevel='" + this.����Ŀǰ�ȼ�.getText() + "' where id=" + this.�������.getText() + ";";
                    PreparedStatement skilllevel = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    skilllevel.executeUpdate(sqlString1);

                    sqlString2 = "update skills set masterlevel='" + this.������ߵȼ�.getText() + "' where id=" + this.�������.getText() + ";";
                    PreparedStatement masterlevel = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    masterlevel.executeUpdate(sqlString2);

                    ˢ�¼�����Ϣ();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�޸ĵļ���");
        }// TODO add your handling code here:
    }//GEN-LAST:event_�޸ļ���ActionPerformed

    private void ��������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��������ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        ���淢��(4);
    }//GEN-LAST:event_jButton34ActionPerformed

    private void ���淢�����ȴ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���淢�����ȴ���ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_���淢�����ȴ���ActionPerformed

    private void ������дActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������дActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_������дActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        ���淢��(1);
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        ���淢��(3);
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        ���淢��(2);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void �ݵ���ÿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�ݵ���ÿ���ActionPerformed
        int �ݵ���ÿ��� = gui.Start.ConfigValuesMap.get("�ݵ���ÿ���");
        if (�ݵ���ÿ��� <= 0) {
            ��������("�ݵ���ÿ���", 707);
            ˢ���ݵ���ÿ���();
        } else {
            ��������("�ݵ���ÿ���", 707);
            ˢ���ݵ���ÿ���();
        }

    }//GEN-LAST:event_�ݵ���ÿ���ActionPerformed

    private void �ݵ��ȯ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�ݵ��ȯ����ActionPerformed
        int �ݵ��ȯ���� = gui.Start.ConfigValuesMap.get("�ݵ��ȯ����");
        if (�ݵ��ȯ���� <= 0) {
            ��������("�ݵ��ȯ����", 703);
            ˢ���ݵ��ȯ����();
        } else {
            ��������("�ݵ��ȯ����", 703);
            ˢ���ݵ��ȯ����();
        }
    }//GEN-LAST:event_�ݵ��ȯ����ActionPerformed

    private void �ݵ㾭�鿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�ݵ㾭�鿪��ActionPerformed

        int �ݵ㾭�鿪�� = gui.Start.ConfigValuesMap.get("�ݵ㾭�鿪��");
        if (�ݵ㾭�鿪�� <= 0) {
            ��������("�ݵ㾭�鿪��", 705);
            ˢ���ݵ㾭�鿪��();
        } else {
            ��������("�ݵ㾭�鿪��", 705);
            ˢ���ݵ㾭�鿪��();
        }

    }//GEN-LAST:event_�ݵ㾭�鿪��ActionPerformed

    private void �ݵ��ҿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�ݵ��ҿ���ActionPerformed
        int �ݵ��ҿ��� = gui.Start.ConfigValuesMap.get("�ݵ��ҿ���");
        if (�ݵ��ҿ��� <= 0) {
            ��������("�ݵ��ҿ���", 701);
            ˢ���ݵ��ҿ���();
        } else {
            ��������("�ݵ��ҿ���", 701);
            ˢ���ݵ��ҿ���();
        }
    }//GEN-LAST:event_�ݵ��ҿ���ActionPerformed
    public void ��������(String a, int b) {
        int ��⿪�� = gui.Start.ConfigValuesMap.get(a);
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (��⿪�� > 0) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, b);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val= '0' where id= '" + b + "';";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                }
            } catch (SQLException ex) {

            }
        } else {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, b);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val= '1' where id='" + b + "';";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                }
            } catch (SQLException ex) {

            }
        }
        gui.Start.GetConfigValues();
    }
    private void �ݵ�ֵ�޸�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�ݵ�ֵ�޸�ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.�ݵ�ֵ.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");

                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");

                ps1.setInt(1, Integer.parseInt(this.�ݵ����.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update configvalues set Val = '" + this.�ݵ�ֵ.getText() + "' where id= " + this.�ݵ����.getText() + ";";
                    PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    Val.executeUpdate(sqlString1);
                    ˢ���ݵ�����();
                    gui.Start.GetConfigValues();
                    ������ʾ����2.setText("[��Ϣ]:�޸ĳɹ��Ѿ���Ч��");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ������ʾ����2.setText("[��Ϣ]:��ѡ����Ҫ�޸ĵ�ֵ��");
        }
    }//GEN-LAST:event_�ݵ�ֵ�޸�ActionPerformed

    private void ����װ���������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����װ���������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_����װ���������ActionPerformed

    private void ������ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������ƷActionPerformed
        ˢ��Ʒ();       // TODO add your handling code here:
    }//GEN-LAST:event_������ƷActionPerformed

    private void ���˷�����Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���˷�����Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_���˷�����Ʒ����ActionPerformed

    private void ���˷�����Ʒ�������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���˷�����Ʒ�������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_���˷�����Ʒ�������ActionPerformed

    private void ���˷�����Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���˷�����Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_���˷�����Ʒ����ActionPerformed

    private void a1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a1ActionPerformed

    private void z6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_z6ActionPerformed
        ���͸���(6);
    }//GEN-LAST:event_z6ActionPerformed

    private void z5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_z5ActionPerformed
        ���͸���(5);
    }//GEN-LAST:event_z5ActionPerformed

    private void z4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_z4ActionPerformed
        ���͸���(4);
    }//GEN-LAST:event_z4ActionPerformed

    private void z1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_z1ActionPerformed
        ���͸���(1);        // TODO add your handling code here:
    }//GEN-LAST:event_z1ActionPerformed

    private void z3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_z3ActionPerformed
        ���͸���(3);
    }//GEN-LAST:event_z3ActionPerformed

    private void z2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_z2ActionPerformed
        ���͸���(2);
    }//GEN-LAST:event_z2ActionPerformed

    private void ����װ��1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����װ��1ActionPerformed
        ˢװ��2(2);        // TODO add your handling code here:
    }//GEN-LAST:event_����װ��1ActionPerformed

    private void ȫ������װ��װ���������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������װ��װ���������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ȫ������װ��װ���������ActionPerformed

    private void ȫ������װ��װ��ħ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������װ��װ��ħ������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ȫ������װ��װ��ħ������ActionPerformed

    private void ȫ������װ��װ��ħ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������װ��װ��ħ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ȫ������װ��װ��ħ����ActionPerformed

    private void ȫ������װ����ƷIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������װ����ƷIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ȫ������װ����ƷIDActionPerformed

    private void ȫ������װ��װ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������װ��װ������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ȫ������װ��װ������ActionPerformed

    private void ȫ������װ��װ���ɷ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������װ��װ���ɷ���ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ȫ������װ��װ���ɷ���ActionPerformed

    private void ȫ������װ��װ������ʱ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������װ��װ������ʱ��ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ȫ������װ��װ������ʱ��ActionPerformed

    private void ȫ������װ��װ��������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������װ��װ��������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ȫ������װ��װ��������ActionPerformed

    private void ȫ������װ��װ��HPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������װ��װ��HPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ȫ������װ��װ��HPActionPerformed

    private void ȫ������װ��װ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������װ��װ������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ȫ������װ��װ������ActionPerformed

    private void ȫ������װ��װ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������װ��װ������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ȫ������װ��װ������ActionPerformed

    private void ȫ������װ��װ��MPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������װ��װ��MPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ȫ������װ��װ��MPActionPerformed

    private void ȫ������װ��װ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������װ��װ������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ȫ������װ��װ������ActionPerformed

    private void ȫ������װ��װ��������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������װ��װ��������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ȫ������װ��װ��������ActionPerformed

    private void ȫ������װ��װ���Ӿ�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������װ��װ���Ӿ�ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ȫ������װ��װ���Ӿ�ActionPerformed

    private void ������Ʒ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������Ʒ1ActionPerformed
        ˢ��Ʒ2();    // TODO add your handling code here:
    }//GEN-LAST:event_������Ʒ1ActionPerformed

    private void ȫ��������Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ��������Ʒ����ActionPerformed

    }//GEN-LAST:event_ȫ��������Ʒ����ActionPerformed

    private void ȫ��������Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ��������Ʒ����ActionPerformed

    }//GEN-LAST:event_ȫ��������Ʒ����ActionPerformed

    private void ɾ��������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ��������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ɾ��������ActionPerformed

    private void ��������Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��������Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��������Ʒ����ActionPerformed

    private void ��ɫ������������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ɫ������������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��ɫ������������ActionPerformed

    private void ��������Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��������Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��������Ʒ����ActionPerformed

    private void ɾ���̳ǲֿ�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ���̳ǲֿ�ActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result = this.�̳ǲֿ���Ʒ���.getText().matches("[0-9]+");
        if (result == true) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM csitems WHERE inventoryitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.�̳ǲֿ���Ʒ���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from csitems where inventoryitemid =" + Integer.parseInt(this.�̳ǲֿ���Ʒ���.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ˢ�½�ɫ�̳ǲֿ�();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ������Ʒ");
        }
    }//GEN-LAST:event_ɾ���̳ǲֿ�ActionPerformed

    private void �̳ǲֿ���Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�̳ǲֿ���Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_�̳ǲֿ���Ʒ����ActionPerformed

    private void �̳ǲֿ���Ʒ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�̳ǲֿ���Ʒ���ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_�̳ǲֿ���Ʒ���ActionPerformed

    private void �̳ǲֿ���Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�̳ǲֿ���Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_�̳ǲֿ���Ʒ����ActionPerformed

    private void ɾ����Ϸ�ֿ�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ����Ϸ�ֿ�ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ɾ����Ϸ�ֿ�ActionPerformed

    private void ��Ϸ�ֿ���Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ϸ�ֿ���Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��Ϸ�ֿ���Ʒ����ActionPerformed

    private void ��Ϸ�ֿ���Ʒ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ϸ�ֿ���Ʒ���ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��Ϸ�ֿ���Ʒ���ActionPerformed

    private void ��Ϸ�ֿ���Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ϸ�ֿ���Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��Ϸ�ֿ���Ʒ����ActionPerformed

    private void ɾ�����ⱳ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ�����ⱳ��ActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result = this.���ⱳ����Ʒ���.getText().matches("[0-9]+");
        if (result == true) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.���ⱳ����Ʒ���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from inventoryitems where inventoryitemid =" + Integer.parseInt(this.���ⱳ����Ʒ���.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ˢ�½�ɫ���ⱳ��();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ������Ʒ");
        }
    }//GEN-LAST:event_ɾ�����ⱳ��ActionPerformed

    private void ���ⱳ����Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ⱳ����Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_���ⱳ����Ʒ����ActionPerformed

    private void ���ⱳ����Ʒ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ⱳ����Ʒ���ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_���ⱳ����Ʒ���ActionPerformed

    private void ���ⱳ����Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ⱳ����Ʒ����ActionPerformed

    }//GEN-LAST:event_���ⱳ����Ʒ����ActionPerformed

    private void ɾ����������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ����������ActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result = this.����������Ʒ���.getText().matches("[0-9]+");
        if (result == true) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.����������Ʒ���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from inventoryitems where inventoryitemid =" + Integer.parseInt(this.����������Ʒ���.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ˢ�½�ɫ��������();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ������Ʒ");
        }
    }//GEN-LAST:event_ɾ����������ActionPerformed

    private void ����������Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����������Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_����������Ʒ����ActionPerformed

    private void ����������Ʒ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����������Ʒ���ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_����������Ʒ���ActionPerformed

    private void ����������Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����������Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_����������Ʒ����ActionPerformed

    private void ɾ�����ñ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ�����ñ���ActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result = this.���ñ�����Ʒ���.getText().matches("[0-9]+");
        if (result == true) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.���ñ�����Ʒ���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from inventoryitems where inventoryitemid =" + Integer.parseInt(this.���ñ�����Ʒ���.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ˢ�½�ɫ���ñ���();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ������Ʒ");
        }
    }//GEN-LAST:event_ɾ�����ñ���ActionPerformed

    private void ���ñ�����Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ñ�����Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_���ñ�����Ʒ����ActionPerformed

    private void ���ñ�����Ʒ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ñ�����Ʒ���ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_���ñ�����Ʒ���ActionPerformed

    private void ���ñ�����Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ñ�����Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_���ñ�����Ʒ����ActionPerformed

    private void ɾ�����ı���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ�����ı���ActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result = this.���ı�����Ʒ���.getText().matches("[0-9]+");
        if (result == true) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.���ı�����Ʒ���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from inventoryitems where inventoryitemid =" + Integer.parseInt(this.���ı�����Ʒ���.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ˢ�½�ɫ���ı���();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ������Ʒ");
        }
    }//GEN-LAST:event_ɾ�����ı���ActionPerformed

    private void ���ı�����Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ı�����Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_���ı�����Ʒ����ActionPerformed

    private void ���ı�����Ʒ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ı�����Ʒ���ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_���ı�����Ʒ���ActionPerformed

    private void ���ı�����Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ı�����Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_���ı�����Ʒ����ActionPerformed

    private void ɾ��װ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ��װ������ActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result = this.װ��������Ʒ���.getText().matches("[0-9]+");
        if (result == true) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.װ��������Ʒ���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from inventoryitems where inventoryitemid =" + Integer.parseInt(this.װ��������Ʒ���.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ˢ�½�ɫװ������();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ������Ʒ");
        }
    }//GEN-LAST:event_ɾ��װ������ActionPerformed

    private void װ��������Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_װ��������Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_װ��������Ʒ����ActionPerformed

    private void װ��������Ʒ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_װ��������Ʒ���ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_װ��������Ʒ���ActionPerformed

    private void װ��������Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_װ��������Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_װ��������Ʒ����ActionPerformed

    private void ɾ������װ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ������װ��ActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result = this.���ϴ������1.getText().matches("[0-9]+");
        if (result == true) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.���ϴ������1.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from inventoryitems where inventoryitemid =" + Integer.parseInt(this.���ϴ������1.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ˢ�½�ɫ��������();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ������Ʒ");
        }
    }//GEN-LAST:event_ɾ������װ��ActionPerformed

    private void ������Ʒ����1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������Ʒ����1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_������Ʒ����1ActionPerformed

    private void ���ϴ������1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ϴ������1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_���ϴ������1ActionPerformed

    private void ������Ʒ����1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������Ʒ����1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_������Ʒ����1ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_����ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_����ActionPerformed

    private void ��������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��������ActionPerformed
        ��ʾ�������.setText("�������; " + �������() + "");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.��ɫID.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characters SET (guildid = ?,guildrank = ?,allianceRank = ?)WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.��ɫID.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    String sqlString2 = null;
                    String sqlString3 = null;
                    sqlString1 = "update characters set guildid='0' where id=" + this.��ɫID.getText() + ";";
                    PreparedStatement hair = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    hair.executeUpdate(sqlString1);
                    sqlString2 = "update characters set guildrank='5' where id=" + this.��ɫID.getText() + ";";
                    PreparedStatement face = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    face.executeUpdate(sqlString2);
                    sqlString3 = "update characters set allianceRank='5' where id=" + this.��ɫID.getText() + ";";
                    PreparedStatement allianceRank = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    allianceRank.executeUpdate(sqlString3);
                    ��ɫ��ʾ����.setText("[��Ϣ]:�⿨�����ɫ�ɹ���");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ��ɫ��ʾ����.setText("[��Ϣ]:��ѡ�񿨼���Ľ�ɫ��");
        }
    }//GEN-LAST:event_��������ActionPerformed

    private void �鿴����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�鿴����ActionPerformed
        ��ʾ�������.setText("�������; " + �������() + "");
        boolean result1 = this.��ɫID.getText().matches("[0-9]+");
        if (!result1) {
            ��ɫ��ʾ����.setText("[��Ϣ]:��ѡ���ɫ��");
            return;
        }
        if (�˺�ID.getText().equals("")) {
            ��ɫ��ʾ����.setText("[��Ϣ]:����ѡ���˺ţ���ѡ���˺��µĽ�ɫ���������ſ��Բ鿴��Ϸ�ֿ⡣");
            return;
        }
        ��ɫ��ʾ����.setText("[��Ϣ]:��ѯ�ٶȸ���ɫ��Ϣ���йأ������ĵȺ�");
        ˢ�½�ɫ��������();
        ˢ�½�ɫװ������();
        ˢ�½�ɫ���ı���();
        ˢ�½�ɫ���ñ���();
        ˢ�½�ɫ��������();
        ˢ�½�ɫ���ⱳ��();
        ˢ�½�ɫ��Ϸ�ֿ�();
        ˢ�½�ɫ�̳ǲֿ�();
//        ˢ�½�ɫ���������();
//        ˢ�½�ɫ��ȯ������();
        ��ɫ��ʾ����.setText("[��Ϣ]:��ת����ɫ������Ϣ���鿴��");
    }//GEN-LAST:event_�鿴����ActionPerformed

    private void �鿴����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�鿴����ActionPerformed

        ��ɫ��ʾ����.setText("[��Ϣ]:�鿴��Ҽ�����Ϣ��");
        ˢ�¼�����Ϣ();
    }//GEN-LAST:event_�鿴����ActionPerformed

    private void �����Ծ�2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����Ծ�2ActionPerformed
        ��ʾ�������.setText("�������; " + �������() + "");
        String ��� = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.��ɫID.getText().matches("[0-9]+");
        if (result1) {
            int n = JOptionPane.showConfirmDialog(this, "��ȷ��Ҫ�⿨��Ʒ�Ծ������ɫ��", "��Ϣ", JOptionPane.YES_NO_OPTION);
            if (n != JOptionPane.YES_OPTION) {
                return;
            }
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE characterid = ?");
                ps1.setInt(1, Integer.parseInt(this.��ɫID.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr2 = " delete from inventoryitems where characterid =" + Integer.parseInt(this.��ɫID.getText()) + "";
                    ps1.executeUpdate(sqlstr2);
                    ��ɫ��ʾ����.setText("[��Ϣ]:��ɫ�Ѿ�����38����");
                    ˢ�½�ɫ��Ϣ();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            ��ɫ��ʾ����.setText("[��Ϣ]:��ѡ��Ҫ38����Ľ�ɫ��");
        }
    }//GEN-LAST:event_�����Ծ�2ActionPerformed

    private void �����Ծ�1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����Ծ�1ActionPerformed
        ��ʾ�������.setText("�������; " + �������() + "");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.��ɫID.getText().matches("[0-9]+");
        if (result1) {
            int n = JOptionPane.showConfirmDialog(this, "��ȷ��Ҫ�⿨���������Ծ������ɫ��", "��Ϣ", JOptionPane.YES_NO_OPTION);
            if (n != JOptionPane.YES_OPTION) {
                return;
            }
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characters SET (hair = ?,face = ?)WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.��ɫID.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    String sqlString2 = null;
                    sqlString1 = "update characters set hair='30000' where id=" + this.��ɫID.getText() + ";";
                    PreparedStatement hair = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    hair.executeUpdate(sqlString1);
                    sqlString2 = "update characters set face='20000' where id=" + this.��ɫID.getText() + ";";
                    PreparedStatement face = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    face.executeUpdate(sqlString2);
                    ��ɫ��ʾ����.setText("[��Ϣ]:��ȳɹ����������ͳ�ʼ����");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ��ɫ��ʾ����.setText("[��Ϣ]:��ѡ�񿨷������͵Ľ�ɫ��");
        }
    }//GEN-LAST:event_�����Ծ�1ActionPerformed

    private void ��ɫIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ɫIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��ɫIDActionPerformed

    private void GMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GMActionPerformed

    private void ��ͼActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ͼActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��ͼActionPerformed

    private void ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_���ActionPerformed

    private void MPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MPActionPerformed

    private void HPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HPActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_����ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_����ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_����ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_����ActionPerformed

    private void �ȼ�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�ȼ�ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_�ȼ�ActionPerformed

    private void ��ɫ�ǳ�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ɫ�ǳ�ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��ɫ�ǳ�ActionPerformed

    private void ɾ����ɫActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ����ɫActionPerformed
        String ��� = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.��ɫID.getText().matches("[0-9]+");

        if (result1) {
            int n = JOptionPane.showConfirmDialog(this, "��ȷ��Ҫɾ�������ɫ��", "��Ϣ", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                try {
                    ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
                    ps1.setInt(1, Integer.parseInt(this.��ɫID.getText()));
                    rs = ps1.executeQuery();
                    if (rs.next()) {
                        String sqlstr = " delete from characters where id =" + Integer.parseInt(this.��ɫID.getText()) + "";
                        ps1.executeUpdate(sqlstr);
                        String sqlstr2 = " delete from inventoryitems where characterid =" + Integer.parseInt(this.��ɫID.getText()) + "";
                        ps1.executeUpdate(sqlstr2);
                        String sqlstr3 = " delete from auctionitems where characterid =" + Integer.parseInt(this.��ɫID.getText()) + "";
                        ps1.executeUpdate(sqlstr3);
                        String sqlstr31 = " delete from auctionitems1 where characterid =" + Integer.parseInt(this.��ɫID.getText()) + "";
                        ps1.executeUpdate(sqlstr31);
                        String sqlstr4 = " delete from csitems where accountid =" + Integer.parseInt(this.��ɫID.getText()) + "";
                        ps1.executeUpdate(sqlstr4);
                        String sqlstr5 = " delete from bank_item where cid =" + Integer.parseInt(this.��ɫID.getText()) + "";
                        ps1.executeUpdate(sqlstr5);
                        String sqlstr6 = " delete from bossrank where cid =" + Integer.parseInt(this.��ɫID.getText()) + "";
                        ps1.executeUpdate(sqlstr6);
                        String sqlstr7 = " delete from skills where characterid =" + Integer.parseInt(this.��ɫID.getText()) + "";
                        ps1.executeUpdate(sqlstr7);
                        ��ɫ��ʾ����.setText("[��Ϣ]:�ɹ�ɾ����ɫ " + Integer.parseInt(this.��ɫID.getText()) + " ���Լ����������Ϣ��");
                        ˢ�½�ɫ��Ϣ();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            ��ɫ��ʾ����.setText("[��Ϣ]:��ѡ��ɾ���Ľ�ɫ��");
        }
    }//GEN-LAST:event_ɾ����ɫActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean A = this.�ȼ�.getText().matches("[0-9]+");
        boolean B = this.GM.getText().matches("[0-9]+");
        boolean C = this.��ͼ.getText().matches("[0-9]+");
        boolean D = this.���.getText().matches("[0-9]+");
        boolean E = this.MP.getText().matches("[0-9]+");
        boolean F = this.HP.getText().matches("[0-9]+");
        boolean G = this.����.getText().matches("[0-9]+");
        boolean H = this.����.getText().matches("[0-9]+");
        boolean Y = this.����.getText().matches("[0-9]+");
        boolean J = this.����.getText().matches("[0-9]+");
        if (��ɫ�ǳ�.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "��ɫ�ǳƲ�������");
            return;
        }
        if (World.Find.findChannel(��ɫ�ǳ�.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "���Ƚ���ɫ���ߺ����޸ġ�");
            return;
        }
        int n = JOptionPane.showConfirmDialog(this, "��ȷ��Ҫ�޸������ɫ��", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characters SET (name = ?,level = ?, str = ?, dex = ?, luk = ?,int = ?,  maxhp = ?, maxmp = ?, meso = ?, map = ?, gm = ?, hair = ?, face = ? )WHERE id = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
            ps1.setInt(1, Integer.parseInt(this.��ɫID.getText()));
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                String sqlString2 = null;
                String sqlString3 = null;
                String sqlString4 = null;
                String sqlString5 = null;
                String sqlString6 = null;
                String sqlString7 = null;
                String sqlString8 = null;
                String sqlString9 = null;
                String sqlString10 = null;
                String sqlString11 = null;
                String sqlString12 = null;
                String sqlString13 = null;
                sqlString1 = "update characters set name='" + this.��ɫ�ǳ�.getText() + "' where id=" + this.��ɫID.getText() + ";";
                PreparedStatement name = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                name.executeUpdate(sqlString1);
                sqlString2 = "update characters set level='" + this.�ȼ�.getText() + "' where id=" + this.��ɫID.getText() + ";";
                PreparedStatement level = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                level.executeUpdate(sqlString2);

                sqlString3 = "update characters set str='" + this.����.getText() + "' where id=" + this.��ɫID.getText() + ";";
                PreparedStatement str = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                str.executeUpdate(sqlString3);

                sqlString4 = "update characters set dex='" + this.����.getText() + "' where id=" + this.��ɫID.getText() + ";";
                PreparedStatement dex = DatabaseConnection.getConnection().prepareStatement(sqlString4);
                dex.executeUpdate(sqlString4);

                sqlString5 = "update characters set luk='" + this.����.getText() + "' where id=" + this.��ɫID.getText() + ";";
                PreparedStatement luk = DatabaseConnection.getConnection().prepareStatement(sqlString5);
                luk.executeUpdate(sqlString5);

                sqlString6 = "update characters set `int`='" + this.����.getText() + "' where id=" + this.��ɫID.getText() + ";";
                PreparedStatement executeUpdate = DatabaseConnection.getConnection().prepareStatement(sqlString6);
                executeUpdate.executeUpdate(sqlString6);

                sqlString7 = "update characters set maxhp='" + this.HP.getText() + "' where id=" + this.��ɫID.getText() + ";";
                PreparedStatement maxhp = DatabaseConnection.getConnection().prepareStatement(sqlString7);
                maxhp.executeUpdate(sqlString7);

                sqlString8 = "update characters set maxmp='" + this.MP.getText() + "' where id=" + this.��ɫID.getText() + ";";
                PreparedStatement maxmp = DatabaseConnection.getConnection().prepareStatement(sqlString8);
                maxmp.executeUpdate(sqlString8);

                sqlString9 = "update characters set meso='" + this.���.getText() + "' where id=" + this.��ɫID.getText() + ";";
                PreparedStatement meso = DatabaseConnection.getConnection().prepareStatement(sqlString9);
                meso.executeUpdate(sqlString9);

                sqlString10 = "update characters set map='" + this.��ͼ.getText() + "' where id=" + this.��ɫID.getText() + ";";
                PreparedStatement map = DatabaseConnection.getConnection().prepareStatement(sqlString10);
                map.executeUpdate(sqlString10);

                sqlString11 = "update characters set gm='" + this.GM.getText() + "' where id=" + this.��ɫID.getText() + ";";
                PreparedStatement gm = DatabaseConnection.getConnection().prepareStatement(sqlString11);
                gm.executeUpdate(sqlString11);

                sqlString12 = "update characters set hair='" + this.����.getText() + "' where id=" + this.����.getText() + ";";
                PreparedStatement hair = DatabaseConnection.getConnection().prepareStatement(sqlString12);
                hair.executeUpdate(sqlString12);

                sqlString13 = "update characters set face='" + this.����.getText() + "' where id=" + this.����.getText() + ";";
                PreparedStatement face = DatabaseConnection.getConnection().prepareStatement(sqlString13);
                face.executeUpdate(sqlString13);
                ��ɫ��ʾ����.setText("[��Ϣ]:��ɫ��Ϣ�޸ĳɹ���");
                ˢ�½�ɫ��Ϣ();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton38ActionPerformed

    private void ��ʾ�����ɫActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ʾ�����ɫActionPerformed
        ��ʾ�������.setText("�������; " + �������() + "");
        for (int i = ((DefaultTableModel) (this.��ɫ��Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ɫ��Ϣ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM characters  WHERE gm >0 ");
            rs = ps.executeQuery();
            while (rs.next()) {
                String ���� = "";
                if (World.Find.findChannel(rs.getString("name")) > 0) {
                    ���� = "����";
                } else {
                    ���� = "����";
                }
                ((DefaultTableModel) ��ɫ��Ϣ.getModel()).insertRow(��ɫ��Ϣ.getRowCount(), new Object[]{
                    rs.getInt("id"),
                    rs.getInt("accountid"),
                    rs.getString("name"),
                    getJobNameById(rs.getInt("job")),
                    rs.getInt("level"),
                    rs.getInt("str"),
                    rs.getInt("dex"),
                    rs.getInt("int"),
                    rs.getInt("luk"),
                    rs.getInt("maxhp"),
                    rs.getInt("maxmp"),
                    rs.getInt("meso"),
                    rs.getInt("map"),
                    rs.getInt("todayOnlineTime") + "/" + rs.getInt("totalOnlineTime"),
                    ����,
                    rs.getInt("gm"),
                    rs.getInt("hair"),
                    rs.getInt("face"
                    )});
            }
            ��ɫ��ʾ����.setText("[��Ϣ]:��ʾ��Ϸ���й���Ա��ɫ��Ϣ��");
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_��ʾ�����ɫActionPerformed
    public static int �������() {
        int p = 0;
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr != null) {
                    p++;
                }
            }
        }
        return p;
    }
    private void ˢ�½�ɫ��ϢActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�½�ɫ��ϢActionPerformed
        ��ɫ��ʾ����.setText("[��Ϣ]:��ʾ��Ϸ������ҽ�ɫ��Ϣ��");
        ˢ�½�ɫ��Ϣ();
        ��ʾ�������.setText("�������; " + �������() + "");
    }//GEN-LAST:event_ˢ�½�ɫ��ϢActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        ChangePassWord();
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        ע�����˺�();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton35ActionPerformed

    private void ע�������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ע�������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ע�������ActionPerformed

    private void ע����˺�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ע����˺�ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ע����˺�ActionPerformed

    private void ɾ���˺�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ���˺�ActionPerformed
        ��ʾ�����˺�.setText("�˺�����; " + �����˺�() + "");
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            int n = JOptionPane.showConfirmDialog(this, "��ȷ��Ҫɾ������˺���", "��Ϣ", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM accounts ");
                //ps1.setInt(1, Integer.parseInt(this.�˺���Ϣ.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " Delete from accounts where name ='" + this.�˺Ų���.getText() + "'";
                    �˺���ʾ����.setText("[��Ϣ]:ɾ���˺� " + this.�˺Ų���.getText() + " �ɹ���");
                    ps1.executeUpdate(sqlstr);
                    ˢ���˺���Ϣ();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ɾ���˺�ActionPerformed

    private void �����˺�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����˺�ActionPerformed
        ��ʾ�����˺�.setText("�˺�����; " + �����˺�() + "");
        if (�˺Ų���.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "��������Ҫ�������˺� ");
            return;
        }
        String account = �˺Ų���.getText();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps;

            ps = con.prepareStatement("Update accounts set banned = ? Where name = ?");
            ps.setInt(1, 1);
            ps.setString(2, account);
            ps.execute();
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "����!\r\n" + ex);
        }
        �˺���ʾ����.setText("[��Ϣ]:�����˺� " + this.�˺Ų���.getText() + " �ɹ���");
        ˢ���˺���Ϣ();
    }//GEN-LAST:event_�����˺�ActionPerformed

    private void �⿨ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�⿨ActionPerformed
        ��ʾ�����˺�.setText("�˺�����; " + �����˺�() + "");
        if (�˺Ų���.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "��������Ҫ�⿨���˺� ");
            return;
        }
        String account = �˺Ų���.getText();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps;

            ps = con.prepareStatement("Update accounts set loggedin = ? Where name = ?");
            ps.setInt(1, 0);
            ps.setString(2, account);
            ps.execute();
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "����!\r\n" + ex);
        }
        �˺���ʾ����.setText("[��Ϣ]:�⿨�˺� " + this.�˺Ų���.getText() + " �ɹ���");
        ˢ���˺���Ϣ();
    }//GEN-LAST:event_�⿨ActionPerformed

    private void �ѷ��˺�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�ѷ��˺�ActionPerformed
        ��ʾ�����˺�.setText("�˺�����; " + �����˺�() + "");
        �˺���ʾ����.setText("[��Ϣ]:��ʾ��Ϸ�����ѱ����������˺���Ϣ��");
        for (int i = ((DefaultTableModel) (this.�˺���Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�˺���Ϣ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM accounts WHERE banned > 0 ");
            rs = ps.executeQuery();
            while (rs.next()) {
                String ��� = "";
                if (rs.getInt("banned") == 0) {
                    ��� = "����";
                } else {
                    ��� = "���";
                }
                String ���� = "";
                if (rs.getInt("loggedin") == 0) {
                    ���� = "������";
                } else {
                    ���� = "����";
                }
                String QQ = "";
                if (rs.getString("qq") != null) {
                    QQ = rs.getString("qq");
                } else {
                    QQ = "δ��QQ";
                }
                ((DefaultTableModel) �˺���Ϣ.getModel()).insertRow(�˺���Ϣ.getRowCount(), new Object[]{
                    rs.getInt("id"), //�˺�ID
                    rs.getString("name"), //�˺�
                    rs.getString("SessionIP"), //�˺�IP��ַ
                    rs.getString("macs"), ///�˺�MAC��ַ
                    QQ,//ע��ʱ��
                    rs.getInt("ACash"),//��ȯ
                    rs.getInt("mPoints"),//����
                    rs.getString("lastlogin"),//�������
                    ����,
                    ���,
                    rs.getInt("gm")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ȡ��ʾ�˺�();
    }//GEN-LAST:event_�ѷ��˺�ActionPerformed

    private void ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ActionPerformed
        ��ʾ�����˺�.setText("�˺�����; " + �����˺�() + "");
        if (�˺Ų���.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "��������Ҫ�����˺� ");
            return;
        }
        String account = �˺Ų���.getText();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps;

            ps = con.prepareStatement("Update accounts set banned = ? Where name = ?");
            ps.setInt(1, 0);
            ps.setString(2, account);
            ps.execute();
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "����!\r\n" + ex);
        }
        �˺���ʾ����.setText("[��Ϣ]:����˺� " + account + " �ɹ���");
        //JOptionPane.showMessageDialog(null, "�˺Ž��ɹ�");
        ˢ���˺���Ϣ();
    }//GEN-LAST:event_���ActionPerformed

    private void �����˺�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����˺�ActionPerformed
        ��ʾ�����˺�.setText("�˺�����; " + �����˺�() + "");
        �˺���ʾ����.setText("[��Ϣ]:��ʾ��Ϸ������������˺���Ϣ��");
        for (int i = ((DefaultTableModel) (this.�˺���Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�˺���Ϣ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM accounts  WHERE loggedin = 0 ");
            rs = ps.executeQuery();
            while (rs.next()) {
                String ��� = "";
                if (rs.getInt("banned") == 0) {
                    ��� = "����";
                } else {
                    ��� = "���";
                }
                String ���� = "";
                if (rs.getInt("loggedin") == 0) {
                    ���� = "������";
                } else {
                    ���� = "����";
                }
                String QQ = "";
                if (rs.getString("qq") != null) {
                    QQ = rs.getString("qq");
                } else {
                    QQ = "δ��QQ";
                }
                ((DefaultTableModel) �˺���Ϣ.getModel()).insertRow(�˺���Ϣ.getRowCount(), new Object[]{
                    rs.getInt("id"), //�˺�ID
                    rs.getString("name"), //�˺�
                    rs.getString("SessionIP"), //�˺�IP��ַ
                    rs.getString("macs"), ///�˺�MAC��ַ
                    QQ,//ע��ʱ��
                    rs.getInt("ACash"),//��ȯ
                    rs.getInt("mPoints"),//����
                    rs.getString("lastlogin"),//�������
                    ����,
                    ���,
                    rs.getInt("gm")
                });

            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ȡ��ʾ�˺�();
    }//GEN-LAST:event_�����˺�ActionPerformed

    private void �����˺�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����˺�ActionPerformed
        ��ʾ�����˺�.setText("�˺�����; " + �����˺�() + "");
        �˺���ʾ����.setText("[��Ϣ]:��ʾ��Ϸ������������˺���Ϣ��");
        for (int i = ((DefaultTableModel) (this.�˺���Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�˺���Ϣ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM accounts  WHERE loggedin > 0 ");
            rs = ps.executeQuery();
            while (rs.next()) {
                String ��� = "";
                if (rs.getInt("banned") == 0) {
                    ��� = "����";
                } else {
                    ��� = "���";
                }
                String ���� = "";
                if (rs.getInt("loggedin") == 0) {
                    ���� = "������";
                } else {
                    ���� = "����";
                }
                String QQ = "";
                if (rs.getString("qq") != null) {
                    QQ = rs.getString("qq");
                } else {
                    QQ = "δ��QQ";
                }
                ((DefaultTableModel) �˺���Ϣ.getModel()).insertRow(�˺���Ϣ.getRowCount(), new Object[]{
                    rs.getInt("id"), //�˺�ID
                    rs.getString("name"), //�˺�
                    rs.getString("SessionIP"), //�˺�IP��ַ
                    rs.getString("macs"), ///�˺�MAC��ַ
                    QQ,//ע��ʱ��
                    rs.getInt("ACash"),//��ȯ
                    rs.getInt("mPoints"),//����
                    rs.getString("lastlogin"),//�������
                    ����,
                    ���,
                    rs.getInt("gm")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ȡ��ʾ�˺�();
    }//GEN-LAST:event_�����˺�ActionPerformed
    public void ��ȡ��ʾ�˺�() {
        �˺���Ϣ.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = �˺���Ϣ.getSelectedRow();
                String a = �˺���Ϣ.getValueAt(i, 0).toString();
                String a1 = �˺���Ϣ.getValueAt(i, 1).toString();
                String a2 = �˺���Ϣ.getValueAt(i, 5).toString();
                String a3 = �˺���Ϣ.getValueAt(i, 6).toString();
                //if (�˺���Ϣ.getValueAt(i, 4).toString() != null) {
                String a4 = �˺���Ϣ.getValueAt(i, 4).toString();
                QQ.setText(a4);
                //}
                String a10 = �˺���Ϣ.getValueAt(i, 10).toString();
                �˺�ID.setText(a);
                �˺Ų���.setText(a1);
                �˺�.setText(a1);

                ��ȯ.setText(a2);
                ����.setText(a3);
                ����1.setText(a10);
                �˺���ʾ����.setText("[��Ϣ]:��ʾ�˺� " + �˺�.getText() + " �½�ɫ��Ϣ��");
                ˢ�½�ɫ��Ϣ2();
            }
        });
    }

    public static int �����˺�() {
        int data = 0;
        int p = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT loggedin as DATA FROM accounts WHERE loggedin > 0");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    data = rs.getInt("DATA");
                    p += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("�����˺š�����");
        }
        return p;
    }
    private void ˢ���˺���ϢActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ���˺���ϢActionPerformed
        �˺���ʾ����.setText("[��Ϣ]:��ʾ��Ϸ��������˺���Ϣ��");
        ˢ���˺���Ϣ();
        ��ʾ�����˺�.setText("�˺�����; " + �����˺�() + "");
    }//GEN-LAST:event_ˢ���˺���ϢActionPerformed

    private void �޸��˺ŵ�ȯ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸��˺ŵ�ȯ����ActionPerformed
        boolean result1 = this.��ȯ.getText().matches("[0-9]+");
        boolean result2 = this.����.getText().matches("[0-9]+");
        boolean result3 = this.����1.getText().matches("[0-9]+");
        boolean result4 = this.QQ.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result1 && result2 && result3 && result4) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE accounts SET ACash = ?, mPoints = ?, gm = ?, qq = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM accounts  WHERE id = ? ");
                ps1.setInt(1, Integer.parseInt(this.�˺�ID.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    String sqlString3 = null;
                    String sqlString4 = null;
                    String sqlString5 = null;
                    sqlString2 = "update accounts set ACash=" + Integer.parseInt(this.��ȯ.getText()) + " where id ='" + Integer.parseInt(this.�˺�ID.getText()) + "';";
                    PreparedStatement priority = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    priority.executeUpdate(sqlString2);
                    sqlString3 = "update accounts set mPoints=" + Integer.parseInt(this.����.getText()) + " where id='" + Integer.parseInt(this.�˺�ID.getText()) + "';";
                    PreparedStatement period = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    period.executeUpdate(sqlString3);
                    sqlString4 = "update accounts set gm=" + Integer.parseInt(this.����1.getText()) + " where id='" + Integer.parseInt(this.�˺�ID.getText()) + "';";
                    PreparedStatement gm = DatabaseConnection.getConnection().prepareStatement(sqlString4);
                    gm.executeUpdate(sqlString4);
                    sqlString5 = "update accounts set qq=" + Integer.parseInt(this.QQ.getText()) + " where id='" + Integer.parseInt(this.�˺�ID.getText()) + "';";
                    PreparedStatement qq = DatabaseConnection.getConnection().prepareStatement(sqlString5);
                    qq.executeUpdate(sqlString5);
                    ˢ���˺���Ϣ();
                    �˺���ʾ����.setText("[��Ϣ]:�޸��˺� " + this.�˺Ų���.getText() + " / ��ȯ��" + Integer.parseInt(this.��ȯ.getText()) + " / ����ȯ��" + Integer.parseInt(this.����.getText()) + " �ɹ���");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            �˺���ʾ����.setText("[��Ϣ]:��ѡ��Ҫ�޸ĵ��˺�,���ݲ���Ϊ�գ�������ֵ��д���ԡ�");
        }
    }//GEN-LAST:event_�޸��˺ŵ�ȯ����ActionPerformed

    private void �޸ı�������۸�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸ı�������۸�ActionPerformed
        boolean result1 = this.�̳�����۸��޸�.getText().matches("[0-9]+");
        if (result1) {
            if (Integer.parseInt(this.�̳�����۸��޸�.getText()) < 0) {
                �̳���ʾ����.setText("[��Ϣ]:��������ȷ���޸�ֵ��");
                return;
            }
            PreparedStatement ps1 = null;
            ResultSet rs = null;
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 1);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from configvalues where id =999";
                    ps1.executeUpdate(sqlstr);

                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
                ps.setInt(1, 999);
                ps.setString(2, "�̳�����۸�");
                ps.setInt(3, Integer.parseInt(this.�̳�����۸��޸�.getText()));
                ps.executeUpdate();
                ˢ���̳�����۸�();
                gui.Start.GetConfigValues();
                �̳���ʾ����.setText("[��Ϣ]:�̳����䱳���۸��޸ĳɹ����Ѿ���Ч��");

            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_�޸ı�������۸�ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        ��ȡ��Ʒ(60200000, 60300000, 5, 3);
    }//GEN-LAST:event_����ActionPerformed

    private void �������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�������ActionPerformed
        ��ȡ��Ʒ(60100000, 60200000, 5, 2);
    }//GEN-LAST:event_�������ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        ��ȡ��Ʒ(60000000, 60100000, 5, 1);
    }//GEN-LAST:event_����ActionPerformed

    private void Ч��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ч��ActionPerformed
        ��ȡ��Ʒ(50500000, 50600000, 4, 4);
    }//GEN-LAST:event_Ч��ActionPerformed

    private void ��ϷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ϷActionPerformed
        ��ȡ��Ʒ(50400000, 50500000, 4, 5);
    }//GEN-LAST:event_��ϷActionPerformed

    private void ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������ActionPerformed
        ��ȡ��Ʒ(50300000, 50400000, 4, 6);
    }//GEN-LAST:event_������ActionPerformed

    private void �����̵�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����̵�ActionPerformed
        ��ȡ��Ʒ(50200000, 50300000, 4, 3);
    }//GEN-LAST:event_�����̵�ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        ��ȡ��Ʒ(50100000, 50200000, 4, 2);
    }//GEN-LAST:event_����ActionPerformed

    private void ��Ա��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ա��ActionPerformed
        ��ȡ��Ʒ(50000000, 50100000, 4, 1);
    }//GEN-LAST:event_��Ա��ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        ��ȡ��Ʒ(30200000, 30300000, 3, 3);
    }//GEN-LAST:event_����ActionPerformed

    private void ͨѶ��ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ͨѶ��ƷActionPerformed
        ��ȡ��Ʒ(30100000, 30200000, 3, 2);
    }//GEN-LAST:event_ͨѶ��ƷActionPerformed

    private void ϲ����ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ϲ����ƷActionPerformed
        ��ȡ��Ʒ(30000000, 30100000, 3, 1);
    }//GEN-LAST:event_ϲ����ƷActionPerformed

    private void ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ActionPerformed
        ��ȡ��Ʒ(21200000, 21300000, 2, 8);
    }//GEN-LAST:event_���ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        ��ȡ��Ʒ(21100000, 21200000, 2, 3);
    }//GEN-LAST:event_����ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        ��ȡ��Ʒ(21000000, 21100000, 2, 4);
    }//GEN-LAST:event_����ActionPerformed

    private void ��ָActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ָActionPerformed
        ��ȡ��Ʒ(20900000, 21000000, 2, 9);
    }//GEN-LAST:event_��ָActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        ��ȡ��Ʒ(20800000, 20900000, 2, 12);
    }//GEN-LAST:event_����ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        ��ȡ��Ʒ(20700000, 20800000, 2, 11);
    }//GEN-LAST:event_����ActionPerformed

    private void Ь��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ь��ActionPerformed
        ��ȡ��Ʒ(20600000, 20700000, 2, 7);
    }//GEN-LAST:event_Ь��ActionPerformed

    private void ȹ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȹ��ActionPerformed
        ��ȡ��Ʒ(20500000, 20600000, 2, 2);
    }//GEN-LAST:event_ȹ��ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        ��ȡ��Ʒ(20400000, 20500000, 2, 13);
    }//GEN-LAST:event_����ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        ��ȡ��Ʒ(20300000, 20400000, 2, 5);
    }//GEN-LAST:event_����ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        ��ȡ��Ʒ(20200000, 20300000, 2, 10);
    }//GEN-LAST:event_����ActionPerformed

    private void ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ActionPerformed
        ��ȡ��Ʒ(20100000, 20200000, 2, 6);
    }//GEN-LAST:event_����ActionPerformed

    private void ñ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ñ��ActionPerformed
        ��ȡ��Ʒ(20000000, 20100000, 2, 1);
    }//GEN-LAST:event_ñ��ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        �̳���ʾ����.setText("[��Ϣ]:δ���á�");
        //JOptionPane.showMessageDialog(this, "δ����");  // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void �ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�ActionPerformed
        ��ȡ��Ʒ(10200000, 10300000, 1, 3);

    }//GEN-LAST:event_�ActionPerformed

    private void ��ȡ������ƷActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ȡ������ƷActionPerformed
        ��ȡ��Ʒ(10000000, 10100000, 1, 1);
    }//GEN-LAST:event_��ȡ������ƷActionPerformed

    private void �����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����ActionPerformed
        ��ȡ��Ʒ(10100000, 10200000, 1, 2);
    }//GEN-LAST:event_�����ActionPerformed

    private void ��ʾ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ʾ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��ʾ����ActionPerformed

    private void ��Ʒ����״̬ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ʒ����״̬ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��Ʒ����״̬ActionPerformed

    private void ��Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ʒ����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��Ʒ����ActionPerformed

    private void eventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventActionPerformed
        �̳���ʾ����.setText("[��Ϣ]:��ʾ��Ʒ����״̬Ϊ event");
        ��Ʒ����״̬.setText("3");        // TODO add your handling code here:
    }//GEN-LAST:event_eventActionPerformed

    private void HOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HOTActionPerformed
        �̳���ʾ����.setText("[��Ϣ]:��ʾ��Ʒ����״̬Ϊ HOT");
        ��Ʒ����״̬.setText("2");        // TODO add your handling code here:
    }//GEN-LAST:event_HOTActionPerformed

    private void SaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaleActionPerformed
        �̳���ʾ����.setText("[��Ϣ]:��ʾ��Ʒ����״̬Ϊ Sale");
        ��Ʒ����״̬.setText("1");        // TODO add your handling code here:
    }//GEN-LAST:event_SaleActionPerformed

    private void NEWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NEWActionPerformed
        �̳���ʾ����.setText("[��Ϣ]:��ʾ��Ʒ����״̬Ϊ NEW");
        ��Ʒ����״̬.setText("0");        // TODO add your handling code here:
    }//GEN-LAST:event_NEWActionPerformed

    private void �޳���״̬ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޳���״̬ActionPerformed
        �̳���ʾ����.setText("[��Ϣ]:��ʾ��Ʒ����״̬Ϊ ��");
        ��Ʒ����״̬.setText("-1");        // TODO add your handling code here:
    }//GEN-LAST:event_�޳���״̬ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "�Ƿ�ˢ�£�\r\nˢ������ʱ��������Ʒ���������������þ�����", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            initCharacterPannel();
        }
        �̳���ʾ����.setText("[��Ϣ]:ˢ���̳���Ʒ�б�");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        int n = JOptionPane.showConfirmDialog(this, "ȷ��Ϊ[ " + ��Ʒ����.getText() + " ��Ʒ]    �¼�?", "�ϼ���Ʒ��ʾ��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            �¼�();
            //ˢ��();
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        int n = JOptionPane.showConfirmDialog(this, "ȷ��Ϊ[ " + ��Ʒ����.getText() + " ��Ʒ]    �ϼ�?", "�ϼ���Ʒ��ʾ��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            �ϼ�();
            //ˢ��();
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ActionPerformed
        boolean result1 = this.��Ʒ����.getText().matches("[0-9]+");
        boolean result2 = this.��Ʒ����.getText().matches("[0-9]+");
        boolean result3 = this.��Ʒ�۸�.getText().matches("[0-9]+");
        boolean result4 = this.��Ʒʱ��.getText().matches("[0-9]+");
        boolean result5 = this.��Ʒ���.getText().matches("[0-9]+");
        boolean result6 = this.ÿ���޹�.getText().matches("[0-9]+");
        boolean result7 = this.��Ʒ�ۿ�.getText().matches("[0-9]+");
        boolean result8 = this.��Ʒ����.getText().matches("[0-9]+");

        if (!result1 && !result2 && !result3 && !result4 && !result5 && !result6 && !result7 && !result8) {
            �̳���ʾ����.setText("[��Ϣ]:��������ȷ�����ݡ�");
            return;
        }
        if (��Ʒ����.getText().equals("")) {
            �̳���ʾ����.setText("[��Ϣ]:������Ʒ����ѡ��������͡�");
            return;
        }
        if (��Ʒ����.getText().equals("")) {
            �̳���ʾ����.setText("[��Ϣ]:��������ӵ���Ʒ���롣");
            return;
        }
        if (��Ʒ�۸�.getText().equals("")) {
            �̳���ʾ����.setText("[��Ϣ]:��������Ʒ�۸�");
            return;
        }
        if (Integer.parseInt(this.��Ʒ�۸�.getText()) > 999999999) {
            �̳���ʾ����.setText("[��Ϣ]:��Ʒ�������ܴ���999999999��");
            return;
        }
        if (��Ʒʱ��.getText().equals("")) {
            �̳���ʾ����.setText("[��Ϣ]:��������Ʒ�ĸ���ʱ�䣬0 ���������ơ�");
            return;
        }
        if (��Ʒ����.getText().equals("")) {
            �̳���ʾ����.setText("[��Ϣ]:��������Ʒ����Ʒ������");
            return;
        }
        if (Integer.parseInt(this.��Ʒ����.getText()) > 100) {
            �̳���ʾ����.setText("[��Ϣ]:��Ʒ�������ܴ���100��");
            return;
        }
        int ��Ʒ����״̬2;
        if (��Ʒ����״̬.getText().equals("")) {
            ��Ʒ����״̬2 = -1;
        } else {
            ��Ʒ����״̬2 = Integer.parseInt(this.��Ʒ����״̬.getText());
        }
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM cashshop_modified_items WHERE serial = ?");
            ps1.setInt(1, Integer.parseInt(this.��Ʒ����.getText()));
            rs = ps1.executeQuery();
            if (!rs.next()) {
                try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO cashshop_modified_items (serial, showup,itemid,priority,period,gender,count,meso,discount_price,mark, unk_1, unk_2, unk_3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    ps.setInt(1, Integer.parseInt(this.��Ʒ����.getText()));
                    ps.setInt(2, 1);
                    ps.setInt(3, Integer.parseInt(this.��Ʒ����.getText()));
                    ps.setInt(4, 1);
                    ps.setInt(5, Integer.parseInt(this.��Ʒʱ��.getText()));
                    ps.setInt(6, 2);
                    ps.setInt(7, Integer.parseInt(this.��Ʒ����.getText()));
                    ps.setInt(8, 0);
                    ps.setInt(9, Integer.parseInt(this.��Ʒ�۸�.getText()));
                    ps.setInt(10, ��Ʒ����״̬2);
                    ps.setInt(11, 0);
                    ps.setInt(12, 0);
                    ps.setInt(13, 0);
                    ps.executeUpdate();

                } catch (SQLException ex) {
                    Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!��Ʒ���.getText().equals("")) {
                    int SN��� = Getcharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 2);
                    if (SN��� == -1) {
                        Gaincharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 2, -2);
                    }
                    Gaincharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 2, -SN���);
                    Gaincharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 2, Integer.parseInt(this.��Ʒ���.getText()));
                }
                if (!��Ʒ�ۿ�.getText().equals("")) {
                    int SN��� = Getcharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 3);
                    if (SN��� == -1) {
                        Gaincharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 3, -2);
                    }
                    Gaincharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 3, -SN���);
                    Gaincharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 3, Integer.parseInt(this.��Ʒ�ۿ�.getText()));
                }

                �̳���ʾ����.setText("[��Ϣ]:����Ʒ����ɹ���");
                int n = JOptionPane.showConfirmDialog(this, "�Ƿ�ˢ�£�\r\nˢ������ʱ��������Ʒ���������������þ�����", "��Ϣ", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    ˢ��();
                }
            } else {
                �̳���ʾ����.setText("[��Ϣ]:�Ѵ��ڵ�SN�����޷��ɹ����롣");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*int ���� = Integer.parseInt(this.��Ʒ����.getText()) - 1;
        ��Ʒ����.setText("" + ���� + "");
        /*int ���� = Integer.parseInt(this.��Ʒ����.getText()) - 1;
        ��Ʒ����.setText("" + ���� + "");
        ��ȡ��Ʒ(60100000, 60200000, 5, 2);*/
    }//GEN-LAST:event_���ActionPerformed

    private void �޸�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸�ActionPerformed
        boolean result1 = this.��Ʒ����.getText().matches("[0-9]+");
        boolean result2 = this.��Ʒ����.getText().matches("[0-9]+");
        boolean result3 = this.��Ʒ�۸�.getText().matches("[0-9]+");
        boolean result4 = this.��Ʒʱ��.getText().matches("[0-9]+");
        boolean result5 = this.��Ʒ���.getText().matches("[0-9]+");
        boolean result6 = this.ÿ���޹�.getText().matches("[0-9]+");
        boolean result7 = this.��Ʒ�ۿ�.getText().matches("[0-9]+");
        boolean result8 = this.��Ʒ����.getText().matches("[0-9]+");
        if (!result1 && !result2 && !result3 && !result4 && !result5 && !result6 && !result7 && !result8) {
            �̳���ʾ����.setText("[��Ϣ]:��������ȷ�����ݡ�");
            return;
        }
        if (��Ʒ����.getText().equals("")) {
            �̳���ʾ����.setText("[��Ϣ]:������Ʒ����ѡ��������͡�");
            return;
        }
        if (��Ʒ����.getText().equals("")) {
            �̳���ʾ����.setText("[��Ϣ]:��������ӵ���Ʒ���롣");
            return;
        }
        if (��Ʒ�۸�.getText().equals("")) {
            �̳���ʾ����.setText("[��Ϣ]:��������Ʒ�۸�");
            return;
        }
        if (Integer.parseInt(this.��Ʒ�۸�.getText()) > 999999999) {
            �̳���ʾ����.setText("[��Ϣ]:��Ʒ�������ܴ���999999999��");
            return;
        }
        if (��Ʒʱ��.getText().equals("")) {
            �̳���ʾ����.setText("[��Ϣ]:��������Ʒ�ĸ���ʱ�䣬0 ���������ơ�");
            return;
        }
        if (��Ʒ����.getText().equals("")) {
            �̳���ʾ����.setText("[��Ϣ]:��������Ʒ����Ʒ������");
            return;
        }
        if (Integer.parseInt(this.��Ʒ����.getText()) > 100) {
            �̳���ʾ����.setText("[��Ϣ]:��Ʒ�������ܴ���100��");
            return;
        }
        int ��Ʒ����״̬2;
        if (��Ʒ����״̬.getText().equals("")) {
            ��Ʒ����״̬2 = -1;
        } else {
            ��Ʒ����״̬2 = Integer.parseInt(this.��Ʒ����״̬.getText());
        }
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            //���table����
            for (int i = ((DefaultTableModel) (this.charTable.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.charTable.getModel())).removeRow(i);
            }
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE cashshop_modified_items SET showup = ?, itemid = ?, priority = ?, period = ?, gender = ?, count = ?, meso = ?, discount_price = ?, mark = ?, unk_1 = ?, unk_2 = ?, unk_3 = ? WHERE serial = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM cashshop_modified_items WHERE serial = ?");
            ps1.setInt(1, Integer.parseInt(this.��Ʒ����.getText()));
            rs = ps1.executeQuery();
            if (rs.next()) {

                String sqlString1 = null;
                String sqlString3 = null;
                String sqlString5 = null;
                String sqlString6 = null;
                String sqlString7 = null;

                sqlString1 = "update cashshop_modified_items set itemid='" + Integer.parseInt(this.��Ʒ����.getText()) + "' where serial=" + Integer.parseInt(this.��Ʒ����.getText()) + ";";
                PreparedStatement itemid = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                itemid.executeUpdate(sqlString1);

                sqlString3 = "update cashshop_modified_items set period='" + Integer.parseInt(this.��Ʒʱ��.getText()) + "' where serial=" + Integer.parseInt(this.��Ʒ����.getText()) + ";";
                PreparedStatement period = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                period.executeUpdate(sqlString3);

                sqlString5 = "update cashshop_modified_items set count='" + Integer.parseInt(this.��Ʒ����.getText()) + "' where serial=" + Integer.parseInt(this.��Ʒ����.getText()) + ";";
                PreparedStatement count = DatabaseConnection.getConnection().prepareStatement(sqlString5);
                count.executeUpdate(sqlString5);

                sqlString6 = "update cashshop_modified_items set discount_price='" + Integer.parseInt(this.��Ʒ�۸�.getText()) + "' where serial=" + Integer.parseInt(this.��Ʒ����.getText()) + ";";
                PreparedStatement discount_price = DatabaseConnection.getConnection().prepareStatement(sqlString6);
                discount_price.executeUpdate(sqlString6);

                sqlString7 = "update cashshop_modified_items set mark='" + Integer.parseInt(this.��Ʒ����״̬.getText()) + "' where serial=" + Integer.parseInt(this.��Ʒ����.getText()) + ";";
                PreparedStatement mark = DatabaseConnection.getConnection().prepareStatement(sqlString7);
                mark.executeUpdate(sqlString7);
                if (!��Ʒ���.getText().equals("")) {
                    int SN��� = Getcharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 2);
                    if (SN��� == -1) {
                        Gaincharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 2, -2);
                    }
                    Gaincharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 2, -SN���);
                    Gaincharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 2, Integer.parseInt(this.��Ʒ���.getText()));
                } else {
                    ɾ��SN���2();
                }
                if (!��Ʒ�ۿ�.getText().equals("")) {
                    int SN��� = Getcharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 3);
                    if (SN��� == -1) {
                        Gaincharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 3, -2);
                    }
                    Gaincharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 3, -SN���);
                    Gaincharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 3, Integer.parseInt(this.��Ʒ�ۿ�.getText()));
                } else {
                    ɾ��SN���3();
                }

                if (!ÿ���޹�.getText().equals("")) {
                    int SN��� = Getcharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 4);
                    if (SN��� == -1) {
                        Gaincharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 4, -2);
                    }
                    Gaincharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 4, -SN���);
                    Gaincharacter7("" + Integer.parseInt(this.��Ʒ����.getText()) + "", 4, Integer.parseInt(this.ÿ���޹�.getText()));
                } else {
                    ɾ��SN���4();
                }
                �̳���ʾ����.setText("[��Ϣ]:�޸���Ʒ����ɹ���");
                int n = JOptionPane.showConfirmDialog(this, "�Ƿ�ˢ�£�\r\nˢ������ʱ��������Ʒ���������������þ�����", "��Ϣ", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    ˢ��();
                }
            } else {
                �̳���ʾ����.setText("[��Ϣ]:ֻ���޸ģ������Ҫ����µ�SN���룡������ӡ�");

            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_�޸�ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        String ��� = "";
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;
        int �̳�SN���� = Integer.parseInt(this.��Ʒ����.getText());
        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM cashshop_modified_items WHERE serial = ?");
            ps1.setInt(1, �̳�SN����);
            rs1 = ps1.executeQuery();
            if (rs1.next()) {
                String sqlstr = " delete from cashshop_modified_items where serial =" + �̳�SN���� + ";";
                ps1.executeUpdate(sqlstr);
                �̳���ʾ����.setText("[��Ϣ]:�ɹ�ɾ����Ʒ��");
            } else {
                �̳���ʾ����.setText("[��Ϣ]:ɾ����Ʒʧ�ܾߡ�");

            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ɾ��SN���();
        int n = JOptionPane.showConfirmDialog(this, "�Ƿ�ˢ�£�\r\nˢ������ʱ��������Ʒ���������������þ�����", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            ˢ��();
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        �̳���ʾ����.setText("[��Ϣ]:�̳����ؿ�ʼ��");
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, ";�̳ǿ�ʼ����������Ʒ������ά����������롣"));        // TODO add your handling code here:
        CashItemFactory.getInstance().clearCashShop();
        /*Properties �O���n = System.getProperties();
        FileDemo_05.deleteFiles("" + �O���n.getProperty("user.dir") + "\\�����ļ�\\�̳Ǽ��.ini");
        FileoutputUtil.logToFile("�����ļ�/�̳Ǽ��.ini/", "id=,");
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT itemid as DATA FROM cashshop_modified_items WHERE serial > 0");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    data = rs.getString("DATA");
                    FileoutputUtil.logToFile("�����ļ�/�̳Ǽ��.ini/", "" + data + ",");
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("" + Ex);
        }*/
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, ";�̳�����������Ʒ�ɹ���ά����ϣ����Ž��롣"));
        �̳���ʾ����.setText("[��Ϣ]:�̳����سɹ���");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        ��ʾ�����˺�.setText("�˺�����; " + �����˺�() + "");
        ����QQ();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void ˢ�½��������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�½��������ActionPerformed
        ˢ�½��������();
    }//GEN-LAST:event_ˢ�½��������ActionPerformed

    private void ˢ�½��������1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�½��������1ActionPerformed
        ˢ�µ�ȯ������();
    }//GEN-LAST:event_ˢ�½��������1ActionPerformed

    private void ��������Ʒ����1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��������Ʒ����1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��������Ʒ����1ActionPerformed

    private void ��ɫ��ȯ���������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ɫ��ȯ���������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��ɫ��ȯ���������ActionPerformed

    private void ��������Ʒ����1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��������Ʒ����1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��������Ʒ����1ActionPerformed

    private void ɾ��������1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ��������1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ɾ��������1ActionPerformed

    private void ����װ��2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����װ��2ActionPerformed
        ˢװ��2(1);
    }//GEN-LAST:event_����װ��2ActionPerformed

    private void ����GPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����GPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_����GPActionPerformed

    private void �����Ա5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����Ա5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_�����Ա5ActionPerformed

    private void �����Ա4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����Ա4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_�����Ա4ActionPerformed

    private void �����Ա3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����Ա3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_�����Ա3ActionPerformed

    private void �����Ա2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����Ա2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_�����Ա2ActionPerformed

    private void �����Ա1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����Ա1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_�����Ա1ActionPerformed

    private void �����峤ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����峤ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_�����峤ActionPerformed

    private void ��������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��������ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��������ActionPerformed

    private void ����IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_����IDActionPerformed

    private void ˢ�¼�����ϢActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�¼�����ϢActionPerformed
        ˢ�¼�����Ϣ();
    }//GEN-LAST:event_ˢ�¼�����ϢActionPerformed

    private void �޸ļ���1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸ļ���1ActionPerformed
        ��ɫ��ʾ����.setText("[��Ϣ]:�鿴��Ҽ�����Ϣ��");
        ˢ�¼�����Ϣ();
    }//GEN-LAST:event_�޸ļ���1ActionPerformed

    private void ���߽�ɫActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���߽�ɫActionPerformed
        ��ʾ�������.setText("�������; " + �������() + "");
        for (int i = ((DefaultTableModel) (this.��ɫ��Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ɫ��Ϣ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM characters order by id desc");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (World.Find.findChannel(rs.getString("name")) <= 0) {
                    ((DefaultTableModel) ��ɫ��Ϣ.getModel()).insertRow(��ɫ��Ϣ.getRowCount(), new Object[]{
                        rs.getInt("id"),
                        rs.getInt("accountid"),
                        rs.getString("name"),
                        getJobNameById(rs.getInt("job")),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"),
                        rs.getInt("maxhp"),
                        rs.getInt("maxmp"),
                        rs.getInt("meso"),
                        rs.getInt("map"),
                        rs.getInt("todayOnlineTime") + "/" + rs.getInt("totalOnlineTime"),
                        "����",
                        rs.getInt("gm"),
                        rs.getInt("hair"),
                        rs.getInt("face"
                        )});
                }
            }
            ��ɫ��ʾ����.setText("[��Ϣ]:��ʾ��Ϸ�������߽�ɫ��Ϣ��");

        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_���߽�ɫActionPerformed

    private void ���߽�ɫActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���߽�ɫActionPerformed
        ��ʾ�������.setText("�������; " + �������() + "");
        for (int i = ((DefaultTableModel) (this.��ɫ��Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ɫ��Ϣ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM characters order by id desc");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (World.Find.findChannel(rs.getString("name")) > 0) {
                    ((DefaultTableModel) ��ɫ��Ϣ.getModel()).insertRow(��ɫ��Ϣ.getRowCount(), new Object[]{
                        rs.getInt("id"),
                        rs.getInt("accountid"),
                        rs.getString("name"),
                        getJobNameById(rs.getInt("job")),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"),
                        rs.getInt("maxhp"),
                        rs.getInt("maxmp"),
                        rs.getInt("meso"),
                        rs.getInt("map"),
                        rs.getInt("todayOnlineTime") + "/" + rs.getInt("totalOnlineTime"),
                        "����",
                        rs.getInt("gm"),
                        rs.getInt("hair"),
                        rs.getInt("face"
                        )});
                }
            }
            ��ɫ��ʾ����.setText("[��Ϣ]:��ʾ��Ϸ�������߽�ɫ��Ϣ��");

        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_���߽�ɫActionPerformed

    private static int ������ȴ = 0;
    private void ��������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��������ActionPerformed
        boolean result1 = this.jTextField1.getText().matches("[0-9]+");
        if (result1) {
            try (Connection con = DatabaseConnection1.getConnection1(); PreparedStatement ps = con.prepareStatement("INSERT INTO ���õķ�������Ʒ��¼ ( ��Ʒ����,�˺� ) VALUES ( ?,? )")) {
                ps.setInt(1, Integer.parseInt(jTextField1.getText()));
                ps.setString(2, MapleParty.�����˺�);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
            ˢ�¸���();
            JOptionPane.showMessageDialog(null, "�����ɹ���");
        } else {
            JOptionPane.showMessageDialog(null, "��д��ȷ����Ʒ���롣");
        }
    }//GEN-LAST:event_��������ActionPerformed

    private void ˢ�¸���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�¸���ActionPerformed
        ˢ�¸���();
    }//GEN-LAST:event_ˢ�¸���ActionPerformed

    private void ˢ��ͨ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ��ͨ��ActionPerformed
        JOptionPane.showMessageDialog(null, "��δ���š�");//ˢ��ͨ��();
    }//GEN-LAST:event_ˢ��ͨ��ActionPerformed

    private void ����ͨ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ͨ��ActionPerformed
        JOptionPane.showMessageDialog(null, "��δ���š�");

        /*boolean result1 = this.jTextField1.getText().matches("[0-9]+");
        if (result1) {
            try (Connection con = DatabaseConnection1.getConnection1(); PreparedStatement ps = con.prepareStatement("INSERT INTO ���õķ�������Ʒ��¼ ( ��Ʒ���� ) VALUES ( ? )")) {
                ps.setInt(1, Integer.parseInt(jTextField1.getText()));
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
            ˢ��ͨ��();
            JOptionPane.showMessageDialog(null, "�����ɹ���");
        } else {
            JOptionPane.showMessageDialog(null, "��д��ȷ����Ʒ���롣");*/
        //}
    }//GEN-LAST:event_����ͨ��ActionPerformed

    private void ɾ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ������ActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (!"".equals(�������.getText())) {
            try {
                ps1 = DatabaseConnection1.getConnection1().prepareStatement("SELECT * FROM ���õķ�������Ʒ��¼ WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.�������.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from ���õķ�������Ʒ��¼ where id =" + Integer.parseInt(this.�������.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                }
                ˢ�¸���();
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "ѡ����Ҫɾ���ļ�¼��");
        }
    }//GEN-LAST:event_ɾ������ActionPerformed

    private void ɾ��ͨ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ��ͨ��ActionPerformed
        JOptionPane.showMessageDialog(null, "��δ���š�");
    }//GEN-LAST:event_ɾ��ͨ��ActionPerformed

    private void �޸ļ�¼ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸ļ�¼ActionPerformed
        boolean result1 = this.jTextField1.getText().matches("[0-9]+");
        if (result1) {
            PreparedStatement ps = null;
            PreparedStatement ps1 = null;
            ResultSet rs = null;
            try {
                ps = DatabaseConnection1.getConnection1().prepareStatement("UPDATE ���õķ�������Ʒ��¼ SET ��Ʒ���� = ? WHERE id = ?");
                ps1 = DatabaseConnection1.getConnection1().prepareStatement("SELECT * FROM ���õķ�������Ʒ��¼ WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.�������.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update ���õķ�������Ʒ��¼ set ��Ʒ����='" + this.jTextField1.getText() + "' where id = " + this.�������.getText() + ";";
                    PreparedStatement name = DatabaseConnection1.getConnection1().prepareStatement(sqlString1);
                    name.executeUpdate(sqlString1);
                    ˢ�¸���();
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_�޸ļ�¼ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        ��ʾ�����˺�.setText("�˺�����; " + �����˺�() + "");
        �����˺�();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        ˢ�¸��¼�¼();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void ˢ��ģʽActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ��ģʽActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ˢ��ģʽActionPerformed

    private void ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������ActionPerformed
        //JOptionPane.showMessageDialog(null, "δ���á�");
        int n = JOptionPane.showConfirmDialog(this, "����Ҫ�����������", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            ������();
        }
    }//GEN-LAST:event_������ActionPerformed

    private void ��������1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��������1ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "��Ҫ����������", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            int p = 0;
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                    p++;
                    chr.saveToDB(true, true);
                }
            }
            String ��� = "[��������ϵͳ] ����" + p + "���ɹ���";
            JOptionPane.showMessageDialog(null, ���);
        }
    }//GEN-LAST:event_��������1ActionPerformed

    private void �����Ӷ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����Ӷ1ActionPerformed
        // TODO add your handling code here:
        int p = 0;
        for (handling.channel.ChannelServer cserv : handling.channel.ChannelServer.getAllInstances()) {
            p++;
            cserv.closeAllMerchant();
        }
        JOptionPane.showMessageDialog(null, "��Ӷ���˱���" + p + "��Ƶ���ɹ���");
    }//GEN-LAST:event_�����Ӷ1ActionPerformed

    private void Ӧ������1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ӧ������1ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "����ҪӦ��������", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            //�����̵�
            MapleShopFactory.getInstance().clear();
            //���ط�Ӧ��
            ReactorScriptManager.getInstance().clearDrops();
            //���ر���
            MapleMonsterInformationProvider.getInstance().clearDrops();
            //���ش���
            PortalScriptManager.getInstance().clearScripts();
            //���ػ�����
            new Thread(QQMsgServer.getInstance()).start();
            //�����Ʒ�������
            GetCloudBacklist();
            //��������ͬ����Ϣ
            gui.Start.GetConfigValues();
            gui.Start.��ȡ��ͼ���ּ��();
            gui.Start.��ȡ���ܷ�Χ���();

            gui.Start.��ȡ����PVP�˺�();
            MapleParty.����3010025 = ȡ���ӱ�ע(3010025);
            MapleParty.����3010100 = ȡ���ӱ�ע(3010100);
            MapleParty.����3012002 = ȡ���ӱ�ע(3012002);
            try {
                �����ļ�("http://123.207.53.97:8082/�����޸�/�����޸�.zip", "�����޸�.zip", "" + �����������Ŀ¼() + "/");
                ��ѹ�ļ�.��ѹ�ļ�(������½�ѹĿ¼("�����޸�"), ������µ���Ŀ¼("zevms"));
                ɾ���ļ�(������½�ѹĿ¼("�����޸�"));
            } catch (Exception e) {

            }
            Start.GetFuMoInfo();
            //������������();
            JOptionPane.showMessageDialog(null, "������ɡ�");
        }
    }//GEN-LAST:event_Ӧ������1ActionPerformed
    public static String ȡ���ӱ�ע(int a) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM qqstem");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("channel") == a) {
                    data = rs.getString("Name");
                }
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }
    private void �����̨1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����̨1ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "����Ҫ�򿪻����̨��", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            �����̨();
        }
    }//GEN-LAST:event_�����̨1ActionPerformed

    private void ��ֵ����̨1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ֵ����̨1ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "����Ҫ�򿪳�ֵ����̨��", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            ��ֵ����̨();
        }
    }//GEN-LAST:event_��ֵ����̨1ActionPerformed

    private void ��������̨1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��������̨1ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "����Ҫ�� 3 ����̨��", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            ��������̨();
        }
    }//GEN-LAST:event_��������̨1ActionPerformed

    private void ����̨2��1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����̨2��1ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "����Ҫ�� 2 ����̨��", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            ���ÿ���̨();
        }
    }//GEN-LAST:event_����̨2��1ActionPerformed

    private void ����̨1��1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����̨1��1ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "����Ҫ�� 1 �ſ���̨��", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            ��̬����̨();
        }
    }//GEN-LAST:event_����̨1��1ActionPerformed

    private void ����Ӧ��1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����Ӧ��1ActionPerformed
        ����Ӧ��();
    }//GEN-LAST:event_����Ӧ��1ActionPerformed

    private void �رշ����1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�رշ����1ActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "����Ҫ�رշ������", "��Ϣ", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            �رշ����();
        }
    }//GEN-LAST:event_�رշ����1ActionPerformed

    private void ˢ��PVP�б�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ��PVP�б�ActionPerformed
        ˢ��PVP��������();
    }//GEN-LAST:event_ˢ��PVP�б�ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.����PVP���.getText().matches("[0-9]+");

        if (result1) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM pvpskills WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.����PVP���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from pvpskills where id =" + Integer.parseInt(this.����PVP���.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ˢ��PVP��������();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ����1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����1ActionPerformed
        if (����PVP�˺�.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "������ϢŶ��");
            return;
        }
        int ID = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT `id` FROM pvpskills  ORDER BY `id` DESC LIMIT 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String SN = rs.getString("id");
                    int sns = Integer.parseInt(SN);
                    sns++;
                    ID = sns;
                    ps.close();
                }
            }
            ps.close();
        } catch (SQLException ex) {

        }
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO pvpskills ( id,name,Val ) VALUES ( ? ,?,?)")) {
            ps.setInt(1, ID);
            ps.setString(2, ����PVPID.getText());
            ps.setInt(3, Integer.parseInt(����PVP�˺�.getText()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
        }
        ˢ��PVP��������();
    }//GEN-LAST:event_����1ActionPerformed

    private void �޸�1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸�1ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.����PVP���.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE pvpskills SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM pvpskills WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.����PVP���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update pvpskills set Val = '" + this.����PVP�˺�.getText() + "' where id= " + this.����PVP���.getText() + ";";
                    PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    Val.executeUpdate(sqlString1);
                    ˢ��PVP��������();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ��Ѿ���Ч");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�޸ĵ�ֵ");
        }
    }//GEN-LAST:event_�޸�1ActionPerformed

    private void ����1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����1ActionPerformed
        boolean result1 = this.����PVPID.getText().matches("[0-9]+");

        if (!result1) {
            return;
        }
        for (int i = ((DefaultTableModel) (this.PVP��������.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.PVP��������.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM pvpskills WHERE name = " + ����PVPID.getText() + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                MapleDataProvider data = MapleDataProviderFactory.getDataProvider(new File(System.getProperty("net.sf.odinms.wzpath") + "/String.wz"));
                MapleData itemsData;
                int itemId;
                String itemName = "";
                itemsData = data.getData("Skill.img");
                for (MapleData itemFolder : itemsData.getChildren()) {
                    itemId = Integer.parseInt(itemFolder.getName());
                    if (rs.getInt("name") == itemId) {
                        itemName = MapleDataTool.getString("name", itemFolder, "NO-NAME");
                    }
                }
                ((DefaultTableModel) PVP��������.getModel()).insertRow(PVP��������.getRowCount(), new Object[]{
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("Val"),
                    itemName
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        PVP��������.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = PVP��������.getSelectedRow();
                String a = PVP��������.getValueAt(i, 0).toString();
                String a1 = PVP��������.getValueAt(i, 1).toString();
                String a2 = PVP��������.getValueAt(i, 2).toString();
                ����PVP���.setText(a);
                ����PVPID.setText(a1);
                ����PVP�˺�.setText(a2);
            }
        });
        ��ȡ����PVP�˺�();
    }//GEN-LAST:event_����1ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (MapleParty.��ȴ1 == 0) {
            MapleParty.��ȴ1 = 1;
            ˢ����Ϣ();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 60 * 1);
                        MapleParty.��ȴ1 = 0;
                    } catch (InterruptedException e) {
                        System.err.println(e);
                    }
                }
            }.start();
        } else {
            JOptionPane.showMessageDialog(null, "ˢ����ȴ�С�");
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void ��ѯActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ѯActionPerformed
        final MerchItemPackage pack = loadItemFrom_Database(Integer.parseInt(��Ӷ��ɫID2.getText()), ��ɫIDȡ�˺�ID(Integer.parseInt(��Ӷ��ɫID2.getText())));
        if (pack == null && ��ɫIDȡ��Ӷ����(Integer.parseInt(��Ӷ��ɫID2.getText())) > 0) {
            JOptionPane.showMessageDialog(null, "ȷ��Ϊ�쳣�����Բ�����");
        } else if (pack != null) {
            JOptionPane.showMessageDialog(null, "δ�����쳣��");
        }
    }//GEN-LAST:event_��ѯActionPerformed
    private static final MerchItemPackage loadItemFrom_Database(final int charid, final int accountid) {
        final Connection con = DatabaseConnection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * from hiredmerch where characterid = ? OR accountid = ?");
            ps.setInt(1, charid);
            ps.setInt(2, accountid);
            //System.out.println("5");
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                ps.close();
                rs.close();
                return null;
            }
            final int packageid = rs.getInt("PackageId");

            final MerchItemPackage pack = new MerchItemPackage();
            pack.setPackageid(packageid);
            pack.setMesos(rs.getInt("Mesos"));
            pack.setSentTime(rs.getLong("time"));
            ps.close();
            rs.close();
            Map<Integer, Pair<IItem, MapleInventoryType>> items = ItemLoader.HIRED_MERCHANT.loadItems_hm(packageid, accountid);
            if (items != null) {
                List<IItem> iters = new ArrayList<IItem>();
                for (Pair<IItem, MapleInventoryType> z : items.values()) {
                    iters.add(z.left);
                }
                pack.setItems(iters);
            }
            return pack;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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

    public static int ��ɫIDȡ��Ӷ����(int id) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT cid as DATA FROM hire WHERE cid = ?");
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
    private void ��Ӷ��ɫID2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ӷ��ɫID2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_��Ӷ��ɫID2ActionPerformed

    private void �ݵ�ֵ�޸�1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�ݵ�ֵ�޸�1ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.�ݵ�ֵ1.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");

                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");

                ps1.setInt(1, Integer.parseInt(this.�ݵ����1.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update configvalues set Val = '" + this.�ݵ�ֵ1.getText() + "' where id= " + this.�ݵ����1.getText() + ";";
                    PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    Val.executeUpdate(sqlString1);
                    ˢ�¹�Ӷ�ݵ�����();
                    gui.Start.GetConfigValues();
                    ������ʾ����2.setText("[��Ϣ]:�޸ĳɹ��Ѿ���Ч��");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ������ʾ����2.setText("[��Ϣ]:��ѡ����Ҫ�޸ĵ�ֵ��");
        }
    }//GEN-LAST:event_�ݵ�ֵ�޸�1ActionPerformed

    private void ���QQ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���QQ���ActionPerformed
        // ���QQ���();

    }//GEN-LAST:event_���QQ���ActionPerformed
    public static int �˺�IDȡ��ɫID(int a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE accountid = " + a + "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data = rs.getInt("id");
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int ��QQȡ�˺�ID(int a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE qq = " + a + "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data = rs.getInt("id");
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public static int ��ɫIDȡ�˺�ID���ж��˺��Ƿ����(int a) {
        int data = 0;
        int data2 = 2;
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE accountid = " + a + "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data = rs.getInt("id");
            }
            ps.close();
        } catch (SQLException ex) {
        }
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE id = " + data + "");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data2 += 1;
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data2;
    }

    public void ���QQ���() {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            int n = JOptionPane.showConfirmDialog(this, "��ȷ��Ҫɾ�����QQ�������˺ţ���ɫ��Ϣ������", "��Ϣ", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                /**
                 * <�����QQ�������˺ŵĽ�ɫ>
                 */
                try {
                    ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters");
                    rs = ps1.executeQuery();
                    if (rs.next()) {
                        String sqlstr = " delete from characters where id =" + �˺�IDȡ��ɫID(��QQȡ�˺�ID(Integer.parseInt(�˺Ų���.getText()))) + "";
                        ps1.executeUpdate(sqlstr);
                        String sqlstr2 = " delete from inventoryitems where characterid =" + �˺�IDȡ��ɫID(��QQȡ�˺�ID(Integer.parseInt(�˺Ų���.getText()))) + "";
                        ps1.executeUpdate(sqlstr2);
                        String sqlstr3 = " delete from auctionitems where characterid =" + �˺�IDȡ��ɫID(��QQȡ�˺�ID(Integer.parseInt(�˺Ų���.getText()))) + "";
                        ps1.executeUpdate(sqlstr3);
                        String sqlstr31 = " delete from auctionitems1 where characterid =" + �˺�IDȡ��ɫID(��QQȡ�˺�ID(Integer.parseInt(�˺Ų���.getText()))) + "";
                        ps1.executeUpdate(sqlstr31);
                        String sqlstr4 = " delete from csitems where accountid =" + �˺�IDȡ��ɫID(��QQȡ�˺�ID(Integer.parseInt(�˺Ų���.getText()))) + "";
                        ps1.executeUpdate(sqlstr4);
                        String sqlstr5 = " delete from bank_item where cid =" + �˺�IDȡ��ɫID(��QQȡ�˺�ID(Integer.parseInt(�˺Ų���.getText()))) + "";
                        ps1.executeUpdate(sqlstr5);
                        String sqlstr6 = " delete from bossrank where cid =" + �˺�IDȡ��ɫID(��QQȡ�˺�ID(Integer.parseInt(�˺Ų���.getText()))) + "";
                        ps1.executeUpdate(sqlstr6);
                        String sqlstr7 = " delete from skills where characterid =" + �˺�IDȡ��ɫID(��QQȡ�˺�ID(Integer.parseInt(�˺Ų���.getText()))) + "";
                        ps1.executeUpdate(sqlstr7);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
                }

                /**
                 * <�����QQ�������˺�>
                 */
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM accounts ");
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " Delete from accounts where qq ='" + this.�˺Ų���.getText() + "'";
                    ps1.executeUpdate(sqlstr);
                    ˢ���˺���Ϣ();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ˢ��PVP��������() {
        for (int i = ((DefaultTableModel) (this.PVP��������.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.PVP��������.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM pvpskills ");
            rs = ps.executeQuery();
            while (rs.next()) {
                MapleDataProvider data = MapleDataProviderFactory.getDataProvider(new File(System.getProperty("net.sf.odinms.wzpath") + "/String.wz"));
                MapleData itemsData;
                int itemId;
                String itemName = "";
                itemsData = data.getData("Skill.img");
                for (MapleData itemFolder : itemsData.getChildren()) {
                    itemId = Integer.parseInt(itemFolder.getName());
                    if (rs.getInt("name") == itemId) {
                        itemName = MapleDataTool.getString("name", itemFolder, "NO-NAME");
                    }
                }
                ((DefaultTableModel) PVP��������.getModel()).insertRow(PVP��������.getRowCount(), new Object[]{
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("Val"),
                    itemName
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        PVP��������.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = PVP��������.getSelectedRow();
                String a = PVP��������.getValueAt(i, 0).toString();
                String a1 = PVP��������.getValueAt(i, 1).toString();
                String a2 = PVP��������.getValueAt(i, 2).toString();
                ����PVP���.setText(a);
                ����PVPID.setText(a1);
                ����PVP�˺�.setText(a2);
            }
        });
        ��ȡ����PVP�˺�();
        // ��ȡ��ͼ���ּ��();
    }

    public void ˢ�¸���() {
        for (int i = ((DefaultTableModel) (this.�洢������Ʒ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�洢������Ʒ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM ���õķ�������Ʒ��¼ WHERE �˺� = " + MapleParty.�����˺� + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) �洢������Ʒ.getModel()).insertRow(�洢������Ʒ.getRowCount(), new Object[]{
                    rs.getString("id"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("��Ʒ����")),
                    rs.getString("��Ʒ����")
                });

            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        �洢������Ʒ.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = �洢������Ʒ.getSelectedRow();
                String a = �洢������Ʒ.getValueAt(i, 0).toString();
                String a1 = �洢������Ʒ.getValueAt(i, 2).toString();
                �������.setText(a);
                jTextField1.setText(a1);
                ȫ��������Ʒ����.setText(a1);
                ���˷�����Ʒ����.setText(a1);
                ȫ������װ����ƷID.setText(a1);
            }
        });
    }

    public void ˢ��ͨ��() {
        for (int i = ((DefaultTableModel) (this.�洢������Ʒ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�洢������Ʒ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM ���õķ�������Ʒ��¼");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) �洢������Ʒ.getModel()).insertRow(�洢������Ʒ.getRowCount(), new Object[]{
                    rs.getString("id"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("��Ʒ����")),
                    rs.getString("��Ʒ����")
                });

            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        �洢������Ʒ.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = �洢������Ʒ.getSelectedRow();
                String a = �洢������Ʒ.getValueAt(i, 0).toString();
                String a1 = �洢������Ʒ.getValueAt(i, 2).toString();
                �������.setText(a);
                jTextField1.setText(a1);
                ȫ��������Ʒ����.setText(a1);
                ���˷�����Ʒ����.setText(a1);
                ȫ������װ����ƷID.setText(a1);
            }
        });
    }

    public static void �������ش���(String a) {
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Ӧ������");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("�ļ���").equals("" + a + "")) {
                    String aa = null;
                    int b = rs.getInt("���ش���");
                    aa = "update Ӧ������ set ���ش���=" + (b + 1) + " where �ļ���=" + a + ";";
                    PreparedStatement �汾 = DatabaseConnection1.getConnection1().prepareStatement(aa);
                    �汾.executeUpdate(aa);
                }
            }
        } catch (SQLException ex) {
        }
    }

    private void ˢ�¸��¼�¼() {

        for (int i = ((DefaultTableModel) (this.���¼�¼.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.���¼�¼.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection1.getConnection1();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM ���¼�¼ order by id desc");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ���¼�¼.getModel()).insertRow(���¼�¼.getRowCount(), new Object[]{
                    rs.getString("id"),
                    rs.getString("�汾"),
                    rs.getString("�ı�"),
                    rs.getString("��ϸ")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ���¼�¼.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ���¼�¼.getSelectedRow();
                String a0 = ���¼�¼.getValueAt(i, 0).toString();
                String a1 = ���¼�¼.getValueAt(i, 1).toString();
                String a2 = ���¼�¼.getValueAt(i, 2).toString();
                String a3 = ���¼�¼.getValueAt(i, 3).toString();
                ���¼�¼��ϸ.setText(a3);
                ����.setText(a0 + ":" + a1);
            }
        });

    }

    private void ˢ�½��������() {
        for (int i = ((DefaultTableModel) (this.���������.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.���������.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM auctionitems");
            rs = ps.executeQuery();
            while (rs.next()) {
                String ״̬ = "";
                switch (rs.getInt("auctionState")) {
                    case 1:
                        ״̬ = "�ϼ�";
                        break;
                    case 2:
                        ״̬ = "�۳�";
                        break;
                    case 0:
                        ״̬ = "�¼�";
                        break;
                    default:
                        ״̬ = "XX";
                        break;
                }
                String ��� = "";
                if (!"null".equals(rs.getString("buyerName"))) {
                    ��� = rs.getString("buyerName");
                } else {
                    ��� = "δ�۳�";
                }

                ((DefaultTableModel) ���������.getModel()).insertRow(���������.getRowCount(), new Object[]{
                    rs.getInt("id"), //�˺�ID
                    rs.getString("characterName"),
                    ״̬,
                    ���,
                    rs.getString("price"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))
                });

            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ˢ�µ�ȯ������() {

        for (int i = ((DefaultTableModel) (this.��ȯ������.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��ȯ������.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM auctionitems1");
            rs = ps.executeQuery();
            while (rs.next()) {
                String ״̬ = "";
                switch (rs.getInt("auctionState")) {
                    case 1:
                        ״̬ = "�ϼ�";
                        break;
                    case 2:
                        ״̬ = "�۳�";
                        break;
                    case 0:
                        ״̬ = "�¼�";
                        break;
                    default:
                        ״̬ = "XX";
                        break;
                }
                String ��� = "";
                if (!"null".equals(rs.getString("buyerName"))) {
                    ��� = rs.getString("buyerName");
                } else {
                    ��� = "δ�۳�";
                }
                ((DefaultTableModel) ��ȯ������.getModel()).insertRow(��ȯ������.getRowCount(), new Object[]{
                    rs.getInt("id"), //�˺�ID
                    rs.getString("characterName"),
                    ״̬,
                    ���,
                    rs.getString("price"),
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))
                });

            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void �رշ����() {
        //��ʼ�����һ�ι�Ӷ
        int p = 0;
        for (handling.channel.ChannelServer cserv : handling.channel.ChannelServer.getAllInstances()) {
            p++;
            cserv.closeAllMerchant();
        }
        //��ʼ�����������
        int pp = 0;
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                pp++;
                chr.saveToDB(true, true);
            }
        }
        //�ж���������
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            cserv.getPlayerStorage().��������(true);
        }
        //�ж���������
        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
            cserv.getPlayerStorage().��������(true);
        }
        //��ʼ�رշ����
        try {
            minutesLeft = 0;
            if (ts == null && (t == null || !t.isAlive())) {
                t = new Thread(ShutdownServer.getInstance());
                ts = Timer.EventTimer.getInstance().register(new Runnable() {

                    @Override
                    public void run() {
                        if (minutesLeft == 0) {
                            ShutdownServer.getInstance();
                            t.start();
                            ts.cancel(false);
                            return;
                        }
                        minutesLeft--;
                    }
                }, 60000);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "����!\r\n" + e);
        }
    }

    private static ScheduledFuture<?> ts = null;
    private int minutesLeft = 0;
    private static Thread t = null;

    public static void ��̬����̨() {
        if (����˹��ܿ��� != null) {
            ����˹��ܿ���.dispose();
        }
        ����˹��ܿ��� = new ����̨1��();
        ����˹��ܿ���.setVisible(true);
    }

    public void ˢ���̳�����۸�() {
        for (int i = ((DefaultTableModel) (this.�̳�����۸�.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�̳�����۸�.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM configvalues WHERE id = 999 ");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) �̳�����۸�.getModel()).insertRow(�̳�����۸�.getRowCount(), new Object[]{rs.getString("Val")});

            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ���淢��(int a) {
        try {
            String str = ������д.getText();
            String ��� = "";
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    switch (a) {
                        case 1:
                            //���˹���
                            World.Broadcast.broadcastMessage(MaplePacketCreator.serverMessage(str.toString()));
                            break;
                        case 2:
                            //��������
                            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(1, str));
                            break;
                        case 3:
                            //������ɫ����
                            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, str));
                            break;
                        case 4:
                            mch.startMapEffect(str, Integer.parseInt(���淢�����ȴ���.getText()));
                            break;
                        default:
                            break;
                    }
                }
                ���淢�����ȴ���.setText("5120027");
            }
            sendMsgToQQGroup("<" + MapleParty.�������� + ">����:" + str + "��");
            ������ʾ����.setText("[��Ϣ]:" + str + "");
        } catch (Exception e) {
            ������ʾ����.setText("[��Ϣ]:" + e);
        }
    }

    private void ˢ��Ʒ2() {
        try {
            int ����;
            int ��ƷID;
            ��ƷID = Integer.parseInt(ȫ��������Ʒ����.getText());
            ���� = Integer.parseInt(ȫ��������Ʒ����.getText());
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
                }
            }
            ȫ��������Ʒ����.setText("");
            ȫ��������Ʒ����.setText("");
            ������ʾ����.setText("[��Ϣ]:���ͳɹ���");
        } catch (Exception e) {
            ������ʾ����.setText("[��Ϣ]:����!" + e);
        }
    }

    private void ˢ��Ʒ() {
        try {
            String ����;
            if ("�������".equals(���˷�����Ʒ�������.getText())) {
                ���� = "";
            } else {
                ���� = ���˷�����Ʒ�������.getText();
            }
            int ��ƷID;
            if ("��ƷID".equals(���˷�����Ʒ����.getText())) {
                ��ƷID = 0;
            } else {
                ��ƷID = Integer.parseInt(���˷�����Ʒ����.getText());
            }
            int ����;
            if ("����".equals(���˷�����Ʒ����.getText())) {
                ���� = 0;
            } else {
                ���� = Integer.parseInt(���˷�����Ʒ����.getText());
            }
            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            MapleInventoryType type = GameConstants.getInventoryType(��ƷID);
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (mch.getName().equals(����)) {
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
                    }
                }
            }
            ���˷�����Ʒ�������.setText("");
            ���˷�����Ʒ����.setText("");
            ���˷�����Ʒ����.setText("");
            ������ʾ����.setText("[��Ϣ]:���ͳɹ���");
        } catch (Exception e) {
            ������ʾ����.setText("[��Ϣ]:����!" + e);
        }
    }

    private void ���͸���(int a) {
        boolean result1 = this.a1.getText().matches("[0-9]+");
        if (result1) {
            int ����;
            if ("100000000".equals(a1.getText())) {
                ���� = 100;
            } else {
                ���� = Integer.parseInt(a1.getText());
            }
            if (���� <= 0 || ���� > 999999999) {
                return;
            }
            String ���� = "";
            for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {

                    switch (a) {
                        case 1:
                            ���� = "��ȯ";
                            mch.modifyCSPoints(1, ����, true);
                            break;
                        case 2:
                            ���� = "����ȯ";
                            mch.modifyCSPoints(2, ����, true);
                            break;
                        case 3:
                            ���� = "���";
                            mch.gainMeso(����, true);
                            break;
                        case 4:
                            ���� = "����";
                            mch.gainExp(����, false, false, false);
                            break;
                        case 5:
                            ���� = "����";
                            mch.addFame(����);
                            break;
                        case 6:
                            ���� = "����";
                            mch.gainBeans(����);
                            break;
                        default:
                            break;
                    }
                    mch.startMapEffect("����Ա���� " + ���� + " " + ���� + "�����ߵ�������ң�", 5120027);
                }
            }
            ������ʾ����.setText("[��Ϣ]:���� " + ���� + " " + ���� + "�����ߵ�������ҡ�");
            a1.setText("");
            JOptionPane.showMessageDialog(null, "���ͳɹ�");
        } else {
            ������ʾ����.setText("[��Ϣ]:������Ҫ����������");
        }
    }

    /* private void ���͸���(int a) {
        boolean result1 = this.a1.getText().matches("[0-9]+");
        if (result1) {
            int ����;
            if ("100000000".equals(a1.getText())) {
                ���� = 100;
            } else {
                ���� = Integer.parseInt(a1.getText());
            }
            if (���� <= 0 || ���� > 999999999) {
                return;
            }
            String ���� = "";
            switch (a) {
                case 1:
                    for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                        for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                            ���� = "��ȯ";
                            mch.modifyCSPoints(1, ����, true);
                            mch.startMapEffect("����Ա���� " + ���� + " " + ���� + "�����ߵ�������ң�", 5120027);
                        }
                    }
                    break;
                case 2:
                    for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                        for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                            ���� = "����ȯ";
                            mch.modifyCSPoints(2, ����, true);
                            mch.startMapEffect("����Ա���� " + ���� + " " + ���� + "�����ߵ�������ң�", 5120027);
                            
                        }
                    }
                    break;
                case 3:
                    for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                        for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                            ���� = "���";
                            mch.gainMeso(����, true);
                            mch.startMapEffect("����Ա���� " + ���� + " " + ���� + "�����ߵ�������ң�", 5120027);
                           
                        }
                    }
                    break;
                case 4:
                    for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                        for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                            ���� = "����";
                            mch.gainExp(����, false, false, false);
                            mch.startMapEffect("����Ա���� " + ���� + " " + ���� + "�����ߵ�������ң�", 5120027);
                            
                        }
                    }
                    break;
                case 5:
                    for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                        for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                            ���� = "����";
                            mch.addFame(����);
                            mch.startMapEffect("����Ա���� " + ���� + " " + ���� + "�����ߵ�������ң�", 5120027);
                            ������ʾ����.setText("[��Ϣ]:���� " + ���� + " " + ���� + "�����ߵ�������ҡ�");
                        }
                    }
                    break;
                case 6:
                    for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                        for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                            ���� = "����";
                            mch.gainBeans(����);
                            mch.startMapEffect("����Ա���� " + ���� + " " + ���� + "�����ߵ�������ң�", 5120027);
                            
                        }
                    }
                    break;
                default:
                    break;
            }
            a1.setText("");
            ������ʾ����.setText("[��Ϣ]:���ͳɹ���");
        } else {
            ������ʾ����.setText("[��Ϣ]:������Ҫ����������");
        }
    }
     */
    public void �ϼ�() {

        try {
            int SN_ = Integer.parseInt(String.valueOf(this.charTable.getValueAt(this.charTable.getSelectedRow(), 0)));
            //���table����
            for (int i = ((DefaultTableModel) (this.charTable.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.charTable.getModel())).removeRow(i);
            }
            int OnSale_ = 1;
            CashItemInfo merchandise = new CashItemInfo(SN_, OnSale_);
            int success = update���¼�(merchandise);
            if (success == 0) {
                �̳���ʾ����.setText("[��Ϣ]:�ϼ�ʧ�ܡ�");
            } else {
                initCharacterPannel();
                �̳���ʾ����.setText("[��Ϣ]:�ϼܳɹ���");
            }
        } catch (NumberFormatException e) {
            System.err.println(e);
            �̳���ʾ����.setText("[��Ϣ]:�ϼ�ʧ�ܣ���ѡ����Ҫ�ϼܵĵ��ߡ�");
        }
    }

    public void �¼�() {
        try {
            int SN_ = Integer.parseInt(String.valueOf(this.charTable.getValueAt(this.charTable.getSelectedRow(), 0)));
            //���table����
            for (int i = ((DefaultTableModel) (this.charTable.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.charTable.getModel())).removeRow(i);
            }
            int OnSale_ = 0;
            CashItemInfo merchandise = new CashItemInfo(SN_, OnSale_);
            int success = update���¼�(merchandise);
            if (success == 0) {
                �̳���ʾ����.setText("[��Ϣ]:�¼�ʧ�ܡ�");
            } else {
                initCharacterPannel();
                �̳���ʾ����.setText("[��Ϣ]:�¼ܳɹ���");
            }
        } catch (NumberFormatException e) {
            System.err.println(e);
            �̳���ʾ����.setText("[��Ϣ]:�¼�ʧ�ܣ���ѡ����Ҫ�ϼܵĵ��ߡ�");
        }
    }

    public static int update���¼�(CashItemInfo merchandise) {//�޸�
        PreparedStatement ps = null;
        int resulet = 0;
        Connection conn = DatabaseConnection.getConnection();
        int i = 0;
        try {
            ps = conn.prepareStatement("update cashshop_modified_items set showup = ? where serial = ?");//itemid
            ps.setInt(++i, merchandise.getOnSale());
            ps.setInt(++i, merchandise.getSN());
            resulet = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return resulet;
    }

    public void ��ȡ��Ʒ(final int a, int b, int c, int d) {
        for (int i = ((DefaultTableModel) (this.charTable.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.charTable.getModel())).removeRow(i);
        }
        ��Ʒ����.setText("" + a + "");
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            PreparedStatement pse;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM cashshop_modified_items WHERE serial >= " + a + " && serial < " + b + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                String �ϼ�״̬ = "";
                if (rs.getInt("showup") == 0) {
                    �ϼ�״̬ = "�Ѿ��¼ܡ�";
                } else {
                    �ϼ�״̬ = "�Ѿ��ϼܡ�";
                }
                String ����״̬2 = "";
                switch (rs.getInt("mark")) {
                    case -1:
                        ����״̬2 = "��";
                        break;
                    case 0:
                        ����״̬2 = "NEW";
                        break;
                    case 1:
                        ����״̬2 = "Sale";
                        break;
                    case 2:
                        ����״̬2 = "HOT";
                        break;
                    case 3:
                        ����״̬2 = "Event";
                        break;
                    default:
                        break;
                }
                String ���� = "";
                if ("".equals(NPCConversationManager.SNȡ����(rs.getInt("serial")))) {
                    ���� = "��ȯ";
                } else {
                    ���� = "��/����ȯ";
                }
                ((DefaultTableModel) charTable.getModel()).insertRow(charTable.getRowCount(), new Object[]{
                    rs.getInt("serial"),
                    rs.getInt("itemid"),
                    //itemName,
                    MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")),
                    rs.getInt("count"),
                    rs.getInt("discount_price"),
                    rs.getInt("period"),
                    ����״̬2,
                    �ϼ�״̬,
                    NPCConversationManager.SNȡ����(rs.getInt("serial")),
                    NPCConversationManager.SNȡ���(rs.getInt("serial")),
                    NPCConversationManager.SNȡ�ۿ�(rs.getInt("serial")),
                    NPCConversationManager.SNȡ�޹�(rs.getInt("serial")),
                    ����
                });

            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT `serial` FROM cashshop_modified_items WHERE serial >= " + a + " && serial <" + b + " ORDER BY `serial` DESC LIMIT 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String SN = rs.getString("serial");
                    int sns = Integer.parseInt(SN);
                    sns++;
                    ��Ʒ����.setText("" + sns);
                    ps.close();
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("�����ȡ��Ʒ��" + ex.getMessage());
        }
        if (c == 1 && d == 1) {
            ��ʾ����.setText("������Ʒ");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ������Ʒ��˫�������������Ʒ�������Ʒ��");
        } else if (c == 1 && d == 2) {
            ��ʾ����.setText("�����");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ����ݣ�˫�������������������Ʒ��");
        } else if (c == 1 && d == 3) {
            ��ʾ����.setText("�");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ���˫������ڻ�������Ʒ��");
        } else if (c == 2 && d == 1) {
            ��ʾ����.setText("ñ��");
            �̳���ʾ����.setText("[��Ϣ]:��ʾñ�ӣ�˫�������ñ���������Ʒ��");
        } else if (c == 2 && d == 2) {
            ��ʾ����.setText("ȹ��");
            �̳���ʾ����.setText("[��Ϣ]:��ʾȹ�㣬˫�������ȹ���������Ʒ��");
        } else if (c == 2 && d == 3) {
            ��ʾ����.setText("����");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ���磬˫������������������Ʒ��");
        } else if (c == 2 && d == 4) {
            ��ʾ����.setText("����");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ���ڣ�˫������ڷ����������Ʒ��");
        } else if (c == 2 && d == 5) {
            ��ʾ����.setText("����");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ���ۣ�˫������ڳ����������Ʒ��");
        } else if (c == 2 && d == 6) {
            ��ʾ����.setText("����");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ���Σ�˫������������������Ʒ��");
        } else if (c == 2 && d == 7) {
            ��ʾ����.setText("Ь��");
            �̳���ʾ����.setText("[��Ϣ]:��ʾЬ�ӣ�˫�������Ь���������Ʒ��");
        } else if (c == 2 && d == 8) {
            ��ʾ����.setText("���");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ��裬˫�����������������Ʒ��");
        } else if (c == 2 && d == 9) {
            ��ʾ����.setText("��ָ");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ��ָ��˫������ڽ�ָ�������Ʒ��");
        } else if (c == 2 && d == 10) {
            ��ʾ����.setText("����");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ���Σ�˫������������������Ʒ��");
        } else if (c == 2 && d == 11) {
            ��ʾ����.setText("����");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ���ף�˫������������������Ʒ��");
        } else if (c == 2 && d == 12) {
            ��ʾ����.setText("����");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ������˫������������������Ʒ��");
        } else if (c == 2 && d == 13) {
            ��ʾ����.setText("����");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ���£�˫������������������Ʒ��");
        } else if (c == 3 && d == 1) {
            ��ʾ����.setText("ϲ����Ʒ");
            �̳���ʾ����.setText("[��Ϣ]:��ʾϲ����Ʒ��˫�������ϲ����Ʒ�������Ʒ��");
        } else if (c == 3 && d == 2) {
            ��ʾ����.setText("ͨѶ��Ʒ");
            �̳���ʾ����.setText("[��Ϣ]:��ʾͨѶ��Ʒ��˫�������ͨѶ��Ʒ�������Ʒ��");
        } else if (c == 3 && d == 3) {
            ��ʾ����.setText("����");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ���ᣬ˫������ھ����������Ʒ��");
        } else if (c == 4 && d == 1) {
            ��ʾ����.setText("��Ա��");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ��Ա����˫������ڻ�Ա���������Ʒ��");
        } else if (c == 4 && d == 2) {
            ��ʾ����.setText("����");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ���飬˫������ڱ����������Ʒ��");
        } else if (c == 4 && d == 3) {
            ��ʾ����.setText("�����̵�");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ�����̵꣬˫������ڸ����̵��������Ʒ��");
        } else if (c == 4 && d == 4) {
            ��ʾ����.setText("Ч��");
            �̳���ʾ����.setText("[��Ϣ]:��ʾЧ����˫�������Ч���������Ʒ��");
        } else if (c == 4 && d == 5) {
            ��ʾ����.setText("��Ϸ");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ��Ϸ��˫���������Ϸ�������Ʒ��");
        } else if (c == 4 && d == 6) {
            ��ʾ����.setText("������");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ�����գ�˫������ڼ������������Ʒ��");
        } else if (c == 5 && d == 1) {
            ��ʾ����.setText("����");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ���˫������ڳ����������Ʒ��");
        } else if (c == 5 && d == 2) {
            ��ʾ����.setText("�������");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ������Σ�˫������ڳ�������������Ʒ��");
        } else if (c == 5 && d == 3) {
            ��ʾ����.setText("����");
            �̳���ʾ����.setText("[��Ϣ]:��ʾ������˫������������������Ʒ��");
        } else {
            ��ʾ����.setText("XXXX");
            �̳���ʾ����.setText("[��Ϣ]:XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX��");
        }
    }

    public void ˢ��() {
        if ("������Ʒ".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(10000000, 10100000, 1, 1);
        } else if ("�����".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(10100000, 10200000, 1, 2);
        } else if ("�".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(10200000, 10300000, 1, 3);
        } else if ("ñ��".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(20000000, 20100000, 2, 1);
        } else if ("ȹ��".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(20500000, 20600000, 2, 2);
        } else if ("����".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(21100000, 21200000, 2, 3);
        } else if ("����".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(21000000, 21100000, 2, 4);
        } else if ("����".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(20300000, 20400000, 2, 5);
        } else if ("����".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(20100000, 20200000, 2, 6);
        } else if ("Ь��".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(20600000, 20700000, 2, 7);
        } else if ("���".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(21200000, 21300000, 2, 8);
        } else if ("��ָ".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(20900000, 21000000, 2, 9);
        } else if ("����".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(20200000, 20300000, 2, 10);
        } else if ("����".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(20700000, 20800000, 2, 11);
        } else if ("����".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(20800000, 20900000, 2, 12);
        } else if ("����".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(20400000, 20500000, 2, 13);
        } else if ("ϲ����Ʒ".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(30000000, 30100000, 3, 1);
        } else if ("ͨѶ��Ʒ".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(30100000, 30200000, 3, 2);
        } else if ("����".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(30200000, 30300000, 3, 3);
        } else if ("��Ա��".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(50000000, 50100000, 4, 1);
        } else if ("����".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(50100000, 50200000, 4, 2);
        } else if ("�����̵�".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(50200000, 50300000, 4, 3);
        } else if ("Ч��".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(50500000, 50600000, 4, 4);
        } else if ("������".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(50300000, 50400000, 4, 6);
        } else if ("��Ϸ".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(50400000, 50500000, 4, 5);
        } else if ("����".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(60000000, 60100000, 5, 1);
        } else if ("�������".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(60100000, 60200000, 5, 2);
        } else if ("����".equals(��ʾ����.getText())) {
            ��ȡ��Ʒ(60200000, 60300000, 5, 3);
        } else if ("".equals(��ʾ����.getText())) {
            initCharacterPannel();
        }
    }

    public void initCharacterPannel() {
        long start = System.currentTimeMillis();
        for (int i = ((DefaultTableModel) (this.charTable.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.charTable.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            PreparedStatement pse;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM cashshop_modified_items ");//WHERE serial > 10000000 && serial < 10100000

            rs = ps.executeQuery();
            while (rs.next()) {
                String itemName = "";
                itemName = MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"));
                String �ϼ�״̬ = "";
                if (rs.getInt("showup") == 0) {
                    �ϼ�״̬ = "�Ѿ��¼ܡ�";
                } else {
                    �ϼ�״̬ = "�Ѿ��ϼܡ�";
                }
                String ����״̬2 = "";
                switch (rs.getInt("mark")) {
                    case -1:
                        ����״̬2 = "��";
                        break;
                    case 0:
                        ����״̬2 = "NEW";
                        break;
                    case 1:
                        ����״̬2 = "Sale";
                        break;
                    case 2:
                        ����״̬2 = "HOT";
                        break;
                    case 3:
                        ����״̬2 = "Event";
                        break;
                    default:
                        break;
                }
                ((DefaultTableModel) charTable.getModel()).insertRow(charTable.getRowCount(), new Object[]{
                    rs.getInt("serial"),
                    rs.getInt("itemid"),
                    "����ϸ���಻��ʾ����",
                    //itemName,
                    rs.getInt("count"),
                    rs.getInt("discount_price"),
                    rs.getInt("period"),
                    ����״̬2,
                    �ϼ�״̬,
                    NPCConversationManager.SNȡ����(rs.getInt("serial")),
                    NPCConversationManager.SNȡ���(rs.getInt("serial")),
                    NPCConversationManager.SNȡ�ۿ�(rs.getInt("serial")),
                    NPCConversationManager.SNȡ�޹�(rs.getInt("serial"))
                });
            }
            long now = System.currentTimeMillis() - start;

        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        charTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = charTable.getSelectedRow();
                String a1 = charTable.getValueAt(i, 0).toString();
                String a2 = charTable.getValueAt(i, 1).toString();
                String a3 = charTable.getValueAt(i, 3).toString();
                String a4 = charTable.getValueAt(i, 4).toString();
                String a5 = charTable.getValueAt(i, 5).toString();
                String a6 = charTable.getValueAt(i, 6).toString();
                String a7 = charTable.getValueAt(i, 7).toString();
                String a8 = charTable.getValueAt(i, 8).toString();
                String a9 = charTable.getValueAt(i, 9).toString();
                String a10 = charTable.getValueAt(i, 10).toString();
                String a11 = charTable.getValueAt(i, 11).toString();

                ��Ʒ����.setText(a1);
                ��Ʒ����.setText(a2);
                ��Ʒ����.setText(a3);
                ��Ʒ�۸�.setText(a4);
                ��Ʒʱ��.setText(a5);
                ��Ʒ���.setText(a9);
                ��Ʒ�ۿ�.setText(a10);
                ÿ���޹�.setText(a11);

                if (null != charTable.getValueAt(i, 6).toString()) {
                    switch (charTable.getValueAt(i, 6).toString()) {
                        case "��":
                            ��Ʒ����״̬.setText("-1");
                            break;
                        case "NEW":
                            ��Ʒ����״̬.setText("0");
                            break;
                        case "Sale":
                            ��Ʒ����״̬.setText("1");
                            break;
                        case "HOT":
                            ��Ʒ����״̬.setText("2");
                            break;
                        case "Event":
                            ��Ʒ����״̬.setText("3");
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    public static void main(String args[]) {
        ZEVMS2.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ZEVMS2().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField GM;
    private javax.swing.JButton HOT;
    private javax.swing.JTextField HP;
    private javax.swing.JTextField MP;
    private javax.swing.JButton NEW;
    private javax.swing.JPanel PVP����;
    private javax.swing.JTable PVP��������;
    private javax.swing.JTextField QQ;
    private javax.swing.JButton Sale;
    private javax.swing.JTextField a1;
    private javax.swing.JTable charTable;
    private javax.swing.JButton event;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel227;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel231;
    private javax.swing.JLabel jLabel232;
    private javax.swing.JLabel jLabel233;
    private javax.swing.JLabel jLabel234;
    private javax.swing.JLabel jLabel235;
    private javax.swing.JLabel jLabel236;
    private javax.swing.JLabel jLabel240;
    private javax.swing.JLabel jLabel241;
    private javax.swing.JLabel jLabel242;
    private javax.swing.JLabel jLabel244;
    private javax.swing.JLabel jLabel246;
    private javax.swing.JLabel jLabel259;
    private javax.swing.JLabel jLabel268;
    private javax.swing.JLabel jLabel269;
    private javax.swing.JLabel jLabel270;
    private javax.swing.JLabel jLabel271;
    private javax.swing.JLabel jLabel272;
    private javax.swing.JLabel jLabel276;
    private javax.swing.JLabel jLabel283;
    private javax.swing.JLabel jLabel287;
    private javax.swing.JLabel jLabel288;
    private javax.swing.JLabel jLabel289;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel290;
    private javax.swing.JLabel jLabel291;
    private javax.swing.JLabel jLabel292;
    private javax.swing.JLabel jLabel293;
    private javax.swing.JLabel jLabel294;
    private javax.swing.JLabel jLabel295;
    private javax.swing.JLabel jLabel296;
    private javax.swing.JLabel jLabel297;
    private javax.swing.JLabel jLabel298;
    private javax.swing.JLabel jLabel299;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel300;
    private javax.swing.JLabel jLabel301;
    private javax.swing.JLabel jLabel302;
    private javax.swing.JLabel jLabel303;
    private javax.swing.JLabel jLabel304;
    private javax.swing.JLabel jLabel305;
    private javax.swing.JLabel jLabel306;
    private javax.swing.JLabel jLabel307;
    private javax.swing.JLabel jLabel308;
    private javax.swing.JLabel jLabel309;
    private javax.swing.JLabel jLabel310;
    private javax.swing.JLabel jLabel311;
    private javax.swing.JLabel jLabel312;
    private javax.swing.JLabel jLabel313;
    private javax.swing.JLabel jLabel314;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel322;
    private javax.swing.JLabel jLabel323;
    private javax.swing.JLabel jLabel324;
    private javax.swing.JLabel jLabel325;
    private javax.swing.JLabel jLabel326;
    private javax.swing.JLabel jLabel327;
    private javax.swing.JLabel jLabel328;
    private javax.swing.JLabel jLabel329;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel353;
    private javax.swing.JLabel jLabel354;
    private javax.swing.JLabel jLabel355;
    private javax.swing.JLabel jLabel356;
    private javax.swing.JLabel jLabel357;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane132;
    private javax.swing.JScrollPane jScrollPane134;
    private javax.swing.JScrollPane jScrollPane135;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton z1;
    private javax.swing.JButton z2;
    private javax.swing.JButton z3;
    private javax.swing.JButton z4;
    private javax.swing.JButton z5;
    private javax.swing.JButton z6;
    private javax.swing.JPanel �ϴ�����;
    private javax.swing.JLabel �ϴ�����ʱ��2;
    private javax.swing.JLabel �ϴ�����ʱ��3;
    private javax.swing.JButton ����;
    private javax.swing.JTextField ���˷�����Ʒ����;
    private javax.swing.JTextField ���˷�����Ʒ����;
    private javax.swing.JTextField ���˷�����Ʒ�������;
    private javax.swing.JButton �����̵�;
    private javax.swing.JButton �����;
    private javax.swing.JLabel ��������;
    private javax.swing.JButton ��Ա��;
    private javax.swing.JButton ��������;
    private javax.swing.JButton ��������1;
    private javax.swing.JButton �����Ӷ;
    private javax.swing.JButton �����Ӷ1;
    private javax.swing.JButton �޸�;
    private javax.swing.JButton �޸�1;
    private javax.swing.JButton �޸ļ���;
    private javax.swing.JButton �޸ļ���1;
    private javax.swing.JButton �޸ı�������۸�;
    private javax.swing.JButton �޸ļ�¼;
    private javax.swing.JButton �޸��˺ŵ�ȯ����;
    private javax.swing.JButton ��ֵ����̨;
    private javax.swing.JButton ��ֵ����̨1;
    private javax.swing.JTextField ȫ��������Ʒ����;
    private javax.swing.JTextField ȫ��������Ʒ����;
    private javax.swing.JTextField ȫ������װ����ƷID;
    private javax.swing.JTextField ȫ������װ��װ��HP;
    private javax.swing.JTextField ȫ������װ��װ��MP;
    private javax.swing.JTextField ȫ������װ��װ��������;
    private javax.swing.JTextField ȫ������װ��װ������;
    private javax.swing.JTextField ȫ������װ��װ���Ӿ�;
    private javax.swing.JTextField ȫ������װ��װ���ɷ���;
    private javax.swing.JTextField ȫ������װ��װ��������;
    private javax.swing.JTextField ȫ������װ��װ������;
    private javax.swing.JTextField ȫ������װ��װ������;
    private javax.swing.JTextField ȫ������װ��װ���������;
    private javax.swing.JTextField ȫ������װ��װ������ʱ��;
    private javax.swing.JTextField ȫ������װ��װ������;
    private javax.swing.JTextField ȫ������װ��װ��ħ����;
    private javax.swing.JTextField ȫ������װ��װ��ħ������;
    private javax.swing.JTextField ���淢�����ȴ���;
    private javax.swing.JTextField ������д;
    private javax.swing.JLabel ������ʾ����;
    private javax.swing.JButton �رշ����;
    private javax.swing.JButton �رշ����1;
    private javax.swing.JButton ����;
    private javax.swing.JTextField ����������Ʒ����;
    private javax.swing.JTextField ����������Ʒ����;
    private javax.swing.JTextField ����������Ʒ���;
    private javax.swing.JPanel ��������;
    private javax.swing.JButton ɾ������;
    private javax.swing.JButton ɾ����������;
    private javax.swing.JButton ɾ���̳ǲֿ�;
    private javax.swing.JButton ɾ������;
    private javax.swing.JButton ɾ��������;
    private javax.swing.JButton ɾ��������1;
    private javax.swing.JButton ɾ�����ı���;
    private javax.swing.JButton ɾ����Ϸ�ֿ�;
    private javax.swing.JButton ɾ�����ⱳ��;
    private javax.swing.JButton ɾ������װ��;
    private javax.swing.JButton ɾ��װ������;
    private javax.swing.JButton ɾ����ɫ;
    private javax.swing.JButton ɾ�����ñ���;
    private javax.swing.JButton ɾ���˺�;
    private javax.swing.JButton ɾ��ͨ��;
    private javax.swing.JButton ˢ��PVP�б�;
    private javax.swing.JButton ˢ�¸���;
    private javax.swing.JButton ˢ�¼�����Ϣ;
    private javax.swing.JButton ˢ��ģʽ;
    private javax.swing.JButton ˢ�½�ɫ��Ϣ;
    private javax.swing.JButton ˢ���˺���Ϣ;
    private javax.swing.JButton ˢ��ͨ��;
    private javax.swing.JButton ˢ�½��������;
    private javax.swing.JButton ˢ�½��������1;
    private javax.swing.JLabel ʣ��ʱ��;
    private javax.swing.JButton ��������̨;
    private javax.swing.JButton ��������̨1;
    private javax.swing.JTextField ����;
    private javax.swing.JButton �����Ծ�1;
    private javax.swing.JButton �����Ծ�2;
    private javax.swing.JButton ��������;
    private javax.swing.JButton ����;
    private javax.swing.JTextField ����;
    private javax.swing.JPanel ��������;
    private javax.swing.JPanel ���͸���;
    private javax.swing.JTextField ����װ���������;
    private javax.swing.JLabel �����˺�;
    private javax.swing.JLabel �����˺�1;
    private javax.swing.JLabel �����˺�3;
    private javax.swing.JTextField ��Ʒ����;
    private javax.swing.JTextField ��Ʒ�۸�;
    private javax.swing.JTextField ��Ʒ����״̬;
    private javax.swing.JTextField ��Ʒ���;
    private javax.swing.JTextField ��Ʒ�ۿ�;
    private javax.swing.JTextField ��Ʒ����;
    private javax.swing.JTextField ��Ʒʱ��;
    private javax.swing.JTextField ��Ʒ����;
    private javax.swing.JPanel ��Ʒ����;
    private javax.swing.JTextField �̳ǲֿ���Ʒ����;
    private javax.swing.JTextField �̳ǲֿ���Ʒ����;
    private javax.swing.JTextField �̳ǲֿ���Ʒ���;
    private javax.swing.JTable �̳�����۸�;
    private javax.swing.JTextField �̳�����۸��޸�;
    private javax.swing.JLabel �̳���ʾ����;
    private javax.swing.JLabel ������3;
    private javax.swing.JButton ϲ����Ʒ;
    private javax.swing.JTable �����ݵ�����;
    private javax.swing.JButton ���߽�ɫ;
    private javax.swing.JButton �����˺�;
    private javax.swing.JTextField ��ͼ;
    private javax.swing.JPasswordField ��Ǵ1����;
    private javax.swing.JTable �洢������Ʒ;
    private javax.swing.JButton ����;
    private javax.swing.JButton �������;
    private javax.swing.JTextField ����GP;
    private javax.swing.JTextField ����ID;
    private javax.swing.JTable ������Ϣ;
    private javax.swing.JTextField ��������;
    private javax.swing.JTextField �����Ա1;
    private javax.swing.JTextField �����Ա2;
    private javax.swing.JTextField �����Ա3;
    private javax.swing.JTextField �����Ա4;
    private javax.swing.JTextField �����Ա5;
    private javax.swing.JTextField �����峤;
    private javax.swing.JButton �����˺�;
    private javax.swing.JButton �ѷ��˺�;
    private javax.swing.JButton ñ��;
    private javax.swing.JButton Ӧ������;
    private javax.swing.JButton Ӧ������1;
    private javax.swing.JButton ������;
    private javax.swing.JButton ��ָ;
    private javax.swing.JButton ����;
    private javax.swing.JPanel ����;
    private javax.swing.JTextField ����PVPID;
    private javax.swing.JTextField ����PVP�˺�;
    private javax.swing.JTextField ����PVP���;
    private javax.swing.JTextField ���ܴ���;
    private javax.swing.JTable ������Ϣ;
    private javax.swing.JTextField ��������;
    private javax.swing.JTextField �������;
    private javax.swing.JTextField ������ߵȼ�;
    private javax.swing.JTextField ����Ŀǰ�ȼ�;
    private javax.swing.JButton ����;
    private javax.swing.JTextField ����;
    private javax.swing.JPanel ������;
    private javax.swing.JTextField ��������Ʒ����;
    private javax.swing.JTextField ��������Ʒ����1;
    private javax.swing.JTextField ��������Ʒ����;
    private javax.swing.JTextField ��������Ʒ����1;
    private javax.swing.JPanel ָ��;
    private javax.swing.JButton ����̨1��;
    private javax.swing.JButton ����̨1��1;
    private javax.swing.JButton ����̨2��;
    private javax.swing.JButton ����̨2��1;
    private javax.swing.JButton ����1;
    private javax.swing.JButton Ч��;
    private javax.swing.JTextField ����;
    private javax.swing.JButton ����1;
    private javax.swing.JButton ��������;
    private javax.swing.JButton ����ͨ��;
    private javax.swing.JButton �޳���״̬;
    private javax.swing.JLabel ��ʾ�������;
    private javax.swing.JLabel ��ʾ�����˺�;
    private javax.swing.JButton ��ʾ�����ɫ;
    private javax.swing.JTextField ��ʾ����;
    private javax.swing.JTextField ����;
    private javax.swing.JButton ����Ӧ��;
    private javax.swing.JButton ����Ӧ��1;
    private javax.swing.JPanel ����;
    private javax.swing.JTable ���¼�¼;
    private javax.swing.JTextPane ���¼�¼��ϸ;
    private javax.swing.JButton �鿴����;
    private javax.swing.JButton �鿴����;
    private javax.swing.JButton ��ѯ;
    private javax.swing.JLabel ����;
    private javax.swing.JButton ����;
    private javax.swing.JTextField ÿ���޹�;
    private javax.swing.JTextField �ݵ�ֵ;
    private javax.swing.JTextField �ݵ�ֵ1;
    private javax.swing.JButton �ݵ�ֵ�޸�;
    private javax.swing.JButton �ݵ�ֵ�޸�1;
    private javax.swing.JTextField �ݵ����;
    private javax.swing.JTextField �ݵ����1;
    private javax.swing.JButton �ݵ���ÿ���;
    private javax.swing.JButton �ݵ��ȯ����;
    private javax.swing.JTextField �ݵ�����;
    private javax.swing.JTextField �ݵ�����1;
    private javax.swing.JButton �ݵ㾭�鿪��;
    private javax.swing.JButton �ݵ��ҿ���;
    private javax.swing.JTextField ע�������;
    private javax.swing.JTextField ע����˺�;
    private javax.swing.JButton �;
    private javax.swing.JButton �����̨;
    private javax.swing.JButton �����̨1;
    private javax.swing.JTextField ���ı�����Ʒ����;
    private javax.swing.JTextField ���ı�����Ʒ����;
    private javax.swing.JTextField ���ı�����Ʒ���;
    private javax.swing.JButton ���;
    private javax.swing.JButton ���QQ���;
    private javax.swing.JButton ��Ϸ;
    private javax.swing.JTextField ��Ϸ�ֿ���Ʒ����;
    private javax.swing.JTextField ��Ϸ�ֿ���Ʒ����;
    private javax.swing.JTextField ��Ϸ�ֿ���Ʒ���;
    private javax.swing.JLabel ��Ϸ����;
    private javax.swing.JTextField ��ȯ;
    private javax.swing.JTable ��ȯ������;
    private javax.swing.JTextField ���ⱳ����Ʒ����;
    private javax.swing.JTextField ���ⱳ����Ʒ����;
    private javax.swing.JTextField ���ⱳ����Ʒ���;
    private javax.swing.JButton ����;
    private javax.swing.JTextField �������;
    private javax.swing.JLabel ������ʾ����;
    private javax.swing.JLabel ������ʾ����2;
    private javax.swing.JPanel ������¼����;
    private javax.swing.JButton ���߽�ɫ;
    private javax.swing.JButton �����˺�;
    private javax.swing.JTextField �ȼ�;
    private javax.swing.JTextField ����1;
    private javax.swing.JButton ������;
    private javax.swing.JButton ������Ʒ;
    private javax.swing.JButton ������Ʒ1;
    private javax.swing.JButton ����װ��1;
    private javax.swing.JButton ����װ��2;
    private javax.swing.JTextField ������Ʒ����1;
    private javax.swing.JTextField ������Ʒ����1;
    private javax.swing.JButton �ű��༭��;
    private javax.swing.JTextField ����;
    private javax.swing.JButton ����;
    private javax.swing.JButton ����;
    private javax.swing.JTextField װ��������Ʒ����;
    private javax.swing.JTextField װ��������Ʒ����;
    private javax.swing.JTextField װ��������Ʒ���;
    private javax.swing.JButton ȹ��;
    private javax.swing.JTextField ��ɫID;
    private javax.swing.JTable ��ɫ��Ϣ;
    private javax.swing.JPanel ��ɫ��Ϣ1;
    private javax.swing.JTable ��ɫ��������;
    private javax.swing.JPanel ��ɫ�б�;
    private javax.swing.JTable ��ɫ�̳ǲֿ�;
    private javax.swing.JLabel ��ɫ��ʾ����;
    private javax.swing.JTextField ��ɫ�ǳ�;
    private javax.swing.JTable ��ɫ���ı���;
    private javax.swing.JTable ��ɫ��Ϸ�ֿ�;
    private javax.swing.JTable ��ɫ��ȯ������;
    private javax.swing.JTextField ��ɫ��ȯ���������;
    private javax.swing.JTable ��ɫ���ⱳ��;
    private javax.swing.JPanel ��ɫ����;
    private javax.swing.JTable ��ɫ��������;
    private javax.swing.JTable ��ɫװ������;
    private javax.swing.JTable ��ɫ���ñ���;
    private javax.swing.JTable ��ɫ���������;
    private javax.swing.JTextField ��ɫ������������;
    private javax.swing.JButton �⿨;
    private javax.swing.JButton ���;
    private javax.swing.JTextField ���ñ�����Ʒ����;
    private javax.swing.JTextField ���ñ�����Ʒ����;
    private javax.swing.JTextField ���ñ�����Ʒ���;
    private javax.swing.JButton ��ȡ������Ʒ;
    private javax.swing.JTextField �˺�;
    private javax.swing.JTextField �˺�ID;
    private javax.swing.JTable �˺���Ϣ;
    private javax.swing.JPanel �˺��б�;
    private javax.swing.JLabel �˺���ʾ����;
    private javax.swing.JTextField �˺Ų���;
    private javax.swing.JTextField ��������;
    private javax.swing.JTextField ���ϴ������1;
    private javax.swing.JTextField ����;
    private javax.swing.JButton ͨѶ��Ʒ;
    private javax.swing.JTextField ���;
    private javax.swing.JTable ���������;
    private javax.swing.JButton ����;
    private javax.swing.JTable ��Ӷ�����ݵ�����;
    private javax.swing.JPanel ��Ӷ��ɫID;
    private javax.swing.JTextField ��Ӷ��ɫID2;
    private javax.swing.JButton Ь��;
    private javax.swing.JButton ����;
    private javax.swing.JPanel ��ҳ;
    private javax.swing.JPanel ��ҳ1;
    private javax.swing.JLabel ��ҳLOGO;
    private javax.swing.JButton ���;
    // End of variables declaration//GEN-END:variables

}
