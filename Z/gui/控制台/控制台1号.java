package gui.����̨;

import static abc.Game.���ڱ���;
import client.MapleCharacterUtil;
import database.DatabaseConnection;
import gui.ZEVMS;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import gui.ZEVMS2;
import scripting.NPCConversationManager;
import static gui.Start.ConfigValuesMap;
import static gui.Start.��ȡ��ͼ���ּ��;
import static gui.Start.��ȡ���ܷ�Χ���;
import handling.world.World;
import java.io.File;
import provider.MapleData;
import provider.MapleDataProvider;
import provider.MapleDataProviderFactory;
import provider.MapleDataTool;
import static tools.FileoutputUtil.CurrentReadable_Time;

/**
 *
 * @author Administrator
 */
public class ����̨1�� extends javax.swing.JFrame {

    /**
     * Creates new form ����
     */
    public ����̨1��() {
        setTitle("" + ���ڱ��� + "�� 1 �ſ���̨���ɹرա�");
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("gui/ͼƬ/pp/2.png"));
        setIconImage(icon.getImage());
        GetConfigValues();
        initComponents();
        ˢ�¼���ֵ�б�();
        ˢ���˺�ע��();
        ˢ��������Ѷ();
        ˢ����Ϸ���˿���();
        ˢ��ð�ռ�ְҵ����();
        ˢ��ս��ְҵ����();
        ˢ����ʿ��ְҵ����();
        ˢ�¹���������();
        ˢ�¹�����ٿ���();
        ˢ����Ϸָ���();
        ˢ����Ϸ���ȿ���();
        ˢ�¶�����ҿ���();
        ˢ�¶�����Ʒ����();
        ˢ����ҽ��׿���();
        ˢ�½�ֹ��½����();
        ˢ��ȫ����������();
        ˢ�»�ӭ��������();
        ˢ������㲥����();
        ˢ����Ϸ�ֿ⿪��();
        ˢ�µ�½��֤����();
        ˢ������ֿ⿪��();
        ˢ�������¼����();
        ˢ�������п���();
        ˢ����ѩ�쿪��();
        ˢ����ѩ������();
        ˢ�������ݿ���();
        ˢ���·�Ҷ����();
        ˢ��ע���˺�����();
        ˢ��ð�ռҵȼ�����();
        ˢ����ʿ�ŵȼ�����();
        ˢ�´�����ɫ����();
        ˢ���º컨����();
        ˢ�½���();
        ˢ�½�������();
        ˢ�½ű����뿪��();
        ˢ�¹�Ӷ���˿���();
        ˢ���������ѿ���();
        ˢ���۷�㲥����();
        ˢ��������쿪��();
        ˢ�¹������濪��();
        ˢ��ָ��֪ͨ����();
        ˢ�»��յ�ͼ����();
        ˢ�µ�½��¼����();
        ˢ�¾���ӳɱ�();
        ˢ������ţ����();
        ˢ��Ģ���п���();
        ˢ����ˮ�鿪��();
        ˢ��ƯƯ����();
        ˢ��С���߿���();
        ˢ�º��з����();
        ˢ�´󺣹꿪��();
        ˢ������ֿ���();
        ˢ����Ƥ�￪��();
        ˢ���Ǿ��鿪��();
        ˢ������쿪��();
        ˢ�°�ѩ�˿���();
        ˢ��ʯͷ�˿���();
        ˢ����ɫè����();
        ˢ�´���ǿ���();
        ˢ��С���ÿ���();
        ˢ�����������();
        ˢ�»�Ұ����();
        ˢ�������㿪��();
        ˢ�»�Ģ������();
        ˢ�·�����������();
        ˢ���̳Ǽ�⿪��();
        ˢ�»����࿪();
        ˢ��IP�࿪();
        ˢ�µ�½����();
        ˢ�µ�½����();
        ˢ����������();
        ˢ�µ�½����();
        ˢ�»�����();
        ˢ��˫��Ƶ������();
        ˢ�»�����ע�Ὺ��();
        ˢ�¹���״̬����();
        ˢ��Խ����ֿ���();
        ˢ��������������();
        ˢ��������������();
        ˢ�����ּ�⿪��();
        ˢ�¼��ټ�⿪��();
        ˢ��ȫ����⿪��();
        ˢ�¼��ܵ��Կ���();
        ˢ�¼��ܳͷ�����();
        ˢ�µ�ͼ���ƿ���();
        ˢ�¶�����⿪��();
        ˢ��Ⱥ����⿪��();
        ˢ�¹�Ӷ����ʱ��();
        ˢ�¹�ͼ�浵ʱ��();
        ˢ�¶࿪����();
        ˢ�¹һ���⿪��();
        ˢ��ÿ��ƣ��ֵ();
        ˢ�¸�ħ���ѿ���();
        ˢ������ģʽ����();
        ˢ������˺�();
        ˢ�¼����⿪��();
    }

    private void ˢ�¼����⿪��() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("�����⿪��");
        if (S <= 0) {
            ��ʾ = "������:����";
        } else {
            ��ʾ = "������:�ر�";
        }
        �����⿪��.setText(��ʾ);
    }

    private void ˢ������˺�() {
        ����˺�.setText("" + gui.Start.ConfigValuesMap.get("����˺�") + "");
    }

    private void ˢ������ģʽ����() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("����ģʽ����");
        if (S <= 0) {
            ��ʾ = "����ģʽ:����";
        } else {
            ��ʾ = "����ģʽ:�ر�";
        }
        ����ģʽ����.setText(��ʾ);
    }

    private void ˢ�¸�ħ���ѿ���() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("��ħ���ѿ���");
        if (S <= 0) {
            ��ʾ = "��ħ����:����";
        } else {
            ��ʾ = "��ħ����:�ر�";
        }
        ��ħ���ѿ���.setText(��ʾ);
    }

    private void ˢ��ÿ��ƣ��ֵ() {
        ƣ��ֵ.setText("" + gui.Start.ConfigValuesMap.get("ÿ��ƣ��ֵ") + "");
    }

    private void ˢ�¶࿪����() {

        IP�࿪����.setText("" + gui.Start.ConfigValuesMap.get("IP�࿪��") + "");
        ������࿪����.setText("" + gui.Start.ConfigValuesMap.get("������࿪��") + "");
    }

    private void ˢ�¹�ͼ�浵ʱ��() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("��ͼ�浵����");
        if (S <= 0) {
            ��ʾ = "��ͼ�浵:����";
        } else {
            ��ʾ = "��ͼ�浵:�ر�";
        }
        ��ͼ�浵����.setText(��ʾ);
    }

    private void ˢ�¹�Ӷ����ʱ��() {
        ��Ӷ����ʱ��.setText("" + gui.Start.ConfigValuesMap.get("��Ӷ����ʱ��") + "");
    }

    private void ˢ�¹һ���⿪��() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("�һ���⿪��");
        if (S <= 0) {
            ��ʾ = "�һ����:����";
        } else {
            ��ʾ = "�һ����:�ر�";
        }
        �һ���⿪��.setText(��ʾ);
    }

    private void ˢ��Ⱥ����⿪��() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("Ⱥ����⿪��");
        if (S <= 0) {
            ��ʾ = "Ⱥ�����:����";
        } else {
            ��ʾ = "Ⱥ�����:�ر�";
        }
        Ⱥ����⿪��.setText(��ʾ);
    }

    private void ˢ�¶�����⿪��() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("������⿪��");
        if (S <= 0) {
            ��ʾ = "�������:����";
        } else {
            ��ʾ = "�������:�ر�";
        }
        ������⿪��.setText(��ʾ);
    }

    private void ˢ�¼��ټ�⿪��() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("���ټ�⿪��");
        if (S <= 0) {
            ��ʾ = "���ټ��:����";
        } else {
            ��ʾ = "���ټ��:�ر�";
        }
        ���ټ�⿪��.setText(��ʾ);
    }

    private void ˢ�µ�ͼ���ƿ���() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("��ͼ���ƿ���");
        if (S <= 0) {
            ��ʾ = "��ͼ����:��ʾ";
        } else {
            ��ʾ = "��ͼ����:�ر�";
        }
        ��ͼ���ƿ���.setText(��ʾ);
    }

    private void ˢ�¼��ܳͷ�����() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("���ܳͷ�����");
        if (S <= 0) {
            ��ʾ = "���ܳͷ�:����";
        } else {
            ��ʾ = "���ܳͷ�:�ر�";
        }
        ���ܳͷ�����.setText(��ʾ);
    }

    private void ˢ�¼��ܵ��Կ���() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("���ܵ��Կ���");
        if (S <= 0) {
            ��ʾ = "���ܵ���:����";
        } else {
            ��ʾ = "���ܵ���:�ر�";
        }
        ���ܵ��Կ���.setText(��ʾ);
    }

    private void ˢ��ȫ����⿪��() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("ȫ����⿪��");
        if (S <= 0) {
            ��ʾ = "ȫ�����:����";
        } else {
            ��ʾ = "ȫ�����:�ر�";
        }
        ȫ����⿪��.setText(��ʾ);
    }

    private void ˢ�����ּ�⿪��() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("���ּ�⿪��");
        if (S <= 0) {
            ��ʾ = "���ּ��:����";
        } else {
            ��ʾ = "���ּ��:�ر�";
        }
        ���ּ�⿪��.setText(��ʾ);
    }

    private void ˢ��������������() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("������������");
        if (S <= 0) {
            ��ʾ = "��������:����";
        } else {
            ��ʾ = "��������:�ر�";
        }
        ������������.setText(��ʾ);
    }

    private void ˢ��������������() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("������������");
        if (S <= 0) {
            ��ʾ = "��������:����";
        } else {
            ��ʾ = "��������:�ر�";
        }
        ������������.setText(��ʾ);
    }

    private void ˢ��Խ����ֿ���() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("Խ����ֿ���");
        if (S <= 0) {
            ��ʾ = "Խ�����:����";
        } else {
            ��ʾ = "Խ�����:�ر�";
        }
        Խ����ֿ���.setText(��ʾ);
    }

    private void ˢ�¹���״̬����() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("����״̬����");
        if (S <= 0) {
            ��ʾ = "����״̬:����";
        } else {
            ��ʾ = "����״̬:�ر�";
        }
        ����״̬����.setText(��ʾ);
    }

    private void ˢ�»�����ע�Ὺ��() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("������ע�Ὺ��");
        if (S <= 0) {
            ��ʾ = "������ע��:����";
        } else {
            ��ʾ = "������ע��:�ر�";
        }
        ������ע�Ὺ��.setText(��ʾ);
    }

    private void ˢ�»�����() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("QQ�����˿���");
        if (S <= 0) {
            ��ʾ = "������:����";
        } else {
            ��ʾ = "������:�ر�";
        }
        �����˿���.setText(��ʾ);
    }

    private void ˢ��˫��Ƶ������() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("˫��Ƶ������");
        if (S <= 0) {
            ��ʾ = "˫��Ƶ��:����";
        } else {
            ��ʾ = "˫��Ƶ��:�ر�";
        }
        ˫��Ƶ������.setText(��ʾ);
    }

    private void ˢ����Ϸ���˿���() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("��Ϸ���˿���");
        if (S <= 0) {
            ��ʾ = "��Ϸ����:����";
        } else {
            ��ʾ = "��Ϸ����:�ر�";
        }
        ��Ϸ���˿���.setText(��ʾ);
    }

    private void ˢ�µ�½����() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("��½��������");
        if (S <= 0) {
            ��ʾ = "��½����:����";
        } else {
            ��ʾ = "��½����:�ر�";
        }
        ��½��������.setText(��ʾ);
    }

    private void ˢ����������() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("�������ؿ���");
        if (S <= 0) {
            ��ʾ = "��������:����";
        } else {
            ��ʾ = "��������:�ر�";
        }
        �������ؿ���.setText(��ʾ);
    }

    private void ˢ�µ�½����() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("��½���п���");
        if (S <= 0) {
            ��ʾ = "��½����:����";
        } else {
            ��ʾ = "��½����:�ر�";
        }
        ��½���п���.setText(��ʾ);
    }

    private void ˢ�µ�½����() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("��½��������");
        if (S <= 0) {
            ��ʾ = "��½����:����";
        } else {
            ��ʾ = "��½����:�ر�";
        }
        ��½��������.setText(��ʾ);
    }

    private void ˢ��IP�࿪() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("IP�࿪����");
        if (S <= 0) {
            ��ʾ = "IP�࿪:��ֹ";
        } else {
            ��ʾ = "IP�࿪:����";
        }
        IP�࿪����.setText(��ʾ);
    }

    private void ˢ�»����࿪() {
        String ��ʾ = "";
        int S = gui.Start.ConfigValuesMap.get("�����࿪����");
        if (S <= 0) {
            ��ʾ = "�����࿪:��ֹ";
        } else {
            ��ʾ = "�����࿪:����";
        }
        �����࿪����.setText(��ʾ);
    }

    private void ������������(String str) {
        ������������.setText(str);
    }

    public void ˢ�½���() {
        for (int i = ((DefaultTableModel) (this.���ӱ�.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.���ӱ�.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM configvalues WHERE id = 600  ");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ���ӱ�.getModel()).insertRow(���ӱ�.getRowCount(), new Object[]{rs.getString("id"), rs.getString("name"), rs.getString("Val")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
        }
        ���ӱ�.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ���ӱ�.getSelectedRow();
                String a = ���ӱ�.getValueAt(i, 0).toString();
                String a1 = ���ӱ�.getValueAt(i, 1).toString();
                String a2 = ���ӱ�.getValueAt(i, 2).toString();
                �������.setText(a);
                ��������.setText(a1);
                ������ֵ.setText(a2);
            }
        });
    }

    private void ˢ�½�������() {
        String ��������������ʾ = "";
        int ������������ = gui.Start.ConfigValuesMap.get("������������");
        if (������������ <= 0) {
            ��������������ʾ = "��������:����";
        } else {
            ��������������ʾ = "��������:�ر�";
        }
        ������������(��������������ʾ);
    }

    public void ˢ�¾���ӳɱ�() {
        for (int i = ((DefaultTableModel) (this.����ӳɱ�.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.����ӳɱ�.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM configvalues WHERE id = 150 ||  id = 151  ||  id=152  ||  id=153  ");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ����ӳɱ�.getModel()).insertRow(����ӳɱ�.getRowCount(), new Object[]{rs.getString("id"), rs.getString("name"), rs.getString("Val")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        ����ӳɱ�.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ����ӳɱ�.getSelectedRow();
                String a = ����ӳɱ�.getValueAt(i, 0).toString();
                String a1 = ����ӳɱ�.getValueAt(i, 1).toString();
                String a2 = ����ӳɱ�.getValueAt(i, 2).toString();
                ����ӳɱ����.setText(a);
                ����ӳɱ�����.setText(a1);
                ����ӳɱ���ֵ.setText(a2);
            }
        });
    }

    public static void GetConfigValues() {
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT name, val FROM ConfigValues");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    int val = rs.getInt("val");
                    ConfigValuesMap.put(name, val);
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("GetConfigValues����" + ex.getMessage());
        }
    }

    private void ��Ϸ�˺�ע��(String str) {
        ��Ϸ�˺�ע��.setText(str);
    }

    private void ����ע���˺�����(String str) {
        ����ע���˺�����.setText(str);
    }

    private void ������������(String str) {
        ������������.setText(str);
    }

    private void ð�ռҵȼ�����(String str) {
        ð�ռҵȼ�����.setText(str);
    }

    private void ��ʿ�ŵȼ�����(String str) {
        ��ʿ�ŵȼ�����.setText(str);
    }

    private void ���ƴ�����ɫ����(String str) {
        ���ƴ�����ɫ����.setText(str);
    }

    private void ��Ϸ������Ѷ(String str) {
        ��Ϸ������Ѷ.setText(str);
    }

    private void ��½��¼����(String str) {
        ��½��¼����.setText(str);
    }

    private void ˢ���̳Ǽ�⿪��() {
        String ˢ�µ�¼��¼������ʾ = "";
        int ��¼��¼���� = gui.Start.ConfigValuesMap.get("�̳Ǽ�⿪��");
        if (��¼��¼���� <= 0) {
            ˢ�µ�¼��¼������ʾ = "�̳Ǽ��:����";
        } else {
            ˢ�µ�¼��¼������ʾ = "�̳Ǽ��:�ر�";
        }
        �̳Ǽ�⿪��(ˢ�µ�¼��¼������ʾ);
    }

    private void �̳Ǽ�⿪��(String str) {
        �̳Ǽ�⿪��.setText(str);
    }

    private void �����¼����(String str) {
        �����¼����.setText(str);
    }

    private void ��½��֤����(String str) {
        ��½��֤����.setText(str);
    }

    private void ��Ϸð�ռ�ְҵ����(String str) {
        ð�ռ�ְҵ����.setText(str);
    }

    private void ��Ϸ��ʿ��ְҵ����(String str) {
        ��ʿ��ְҵ����.setText(str);
    }

    private void ��Ϸս��ְҵ����(String str) {
        ս��ְҵ����.setText(str);
    }

    private void ����������(String str) {
        ����������.setText(str);
    }

    private void ������ٿ���(String str) {
        ������ٿ���.setText(str);
    }

    private void ��Ϸָ���(String str) {
        ��Ϸָ���.setText(str);
    }

    private void ��Ϸ���ȿ���(String str) {
        ��Ϸ���ȿ���.setText(str);
    }

    private void ������ҿ���(String str) {
        ������ҿ���.setText(str);
    }

    private void ��ҽ��׿���(String str) {
        ��ҽ��׿���.setText(str);
    }

    private void ������Ʒ����(String str) {
        ������Ʒ����.setText(str);
    }

    private void ��ֹ��½����(String str) {
        ��ֹ��½����.setText(str);
    }

    private void ��ѩ�쿪��(String str) {
        ��ѩ�쿪��.setText(str);
    }

    private void �����ݿ���(String str) {
        �����ݿ���.setText(str);
    }

    private void �º컨����(String str) {
        �º컨����.setText(str);
    }

    private void ��ѩ������(String str) {
        ��ѩ������.setText(str);
    }

    private void �·�Ҷ����(String str) {
        �·�Ҷ����.setText(str);
    }

    private void ȫ����������(String str) {
        ȫ����������.setText(str);
    }

    private void ��ӭ��������(String str) {
        ��ӭ��������.setText(str);
    }

    private void ����㲥����(String str) {
        ����㲥����.setText(str);
    }

    private void ��Ϸ�ֿ⿪��(String str) {
        ��Ϸ�ֿ⿪��.setText(str);
    }

    private void ����ֿ⿪��(String str) {
        ����ֿ⿪��.setText(str);
    }

    private void �����п���(String str) {
        �����п���.setText(str);
    }

    private void �������ѿ���(String str) {
        �������ѿ���.setText(str);
    }

    private void ָ��֪ͨ����(String str) {
        ָ��֪ͨ����.setText(str);
    }

    private void ��Ӷ���˿���(String str) {
        ��Ӷ���˿���.setText(str);
    }

    private void �۷�㲥����(String str) {
        �۷�㲥����.setText(str);
    }

    private void ������쿪��(String str) {
        ������쿪��.setText(str);
    }

    private void �ű����뿪��(String str) {
        �ű����뿪��.setText(str);
    }

    private void �������濪��(String str) {
        �������濪��.setText(str);
    }

    private void ���յ�ͼ����(String str) {
        ���յ�ͼ����.setText(str);
    }

    private void ����ţ����(String str) {
        ����ţ����.setText(str);
    }

    private void Ģ���п���(String str) {
        Ģ���п���.setText(str);
    }

    private void ��ˮ�鿪��(String str) {
        ��ˮ�鿪��.setText(str);
    }

    private void ƯƯ����(String str) {
        ƯƯ����.setText(str);
    }

    private void С���߿���(String str) {
        С���߿���.setText(str);
    }

    private void ���з����(String str) {
        ���з����.setText(str);
    }

    private void �󺣹꿪��(String str) {
        �󺣹꿪��.setText(str);
    }

    private void ����ֿ���(String str) {
        ����ֿ���.setText(str);
    }

    private void ��Ƥ�￪��(String str) {
        ��Ƥ�￪��.setText(str);
    }

    private void �Ǿ��鿪��(String str) {
        �Ǿ��鿪��.setText(str);
    }

    private void ����쿪��(String str) {
        ����쿪��.setText(str);
    }

    private void ��ѩ�˿���(String str) {
        ��ѩ�˿���.setText(str);
    }

    private void ��ɫè����(String str) {
        ��ɫè����.setText(str);
    }

    private void ����ǿ���(String str) {
        ����ǿ���.setText(str);
    }

    private void С���ÿ���(String str) {
        С���ÿ���.setText(str);
    }

    private void ���������(String str) {
        ���������.setText(str);
    }

    private void ��Ұ����(String str) {
        ��Ұ����.setText(str);
    }

    private void �����㿪��(String str) {
        �����㿪��.setText(str);
    }

    private void ��Ģ������(String str) {
        ��Ģ������.setText(str);
    }

    private void ˢ�»�Ģ������() {
        String ��Ģ����ʾ = "";
        int ��Ģ�� = gui.Start.ConfigValuesMap.get("��Ģ������");
        if (��Ģ�� <= 0) {
            ��Ģ����ʾ = "��Ģ��:����";
        } else {
            ��Ģ����ʾ = "��Ģ��:�ر�";
        }
        ��Ģ������(��Ģ����ʾ);
    }

    private void ˢ�»�Ұ����() {
        String ��Ұ����ʾ = "";
        int ��Ұ�� = gui.Start.ConfigValuesMap.get("��Ұ����");
        if (��Ұ�� <= 0) {
            ��Ұ����ʾ = "��Ұ��:����";
        } else {
            ��Ұ����ʾ = "��Ұ��:�ر�";
        }
        ��Ұ����(��Ұ����ʾ);
    }

    private void ˢ�������㿪��() {
        String ��������ʾ = "";
        int ������ = gui.Start.ConfigValuesMap.get("�����㿪��");
        if (������ <= 0) {
            ��������ʾ = "������:����";
        } else {
            ��������ʾ = "������:�ر�";
        }
        �����㿪��(��������ʾ);
    }

    private void ˢ�����������() {
        String �������ʾ = "";
        int ����� = gui.Start.ConfigValuesMap.get("���������");
        if (����� <= 0) {
            �������ʾ = "�����:����";
        } else {
            �������ʾ = "�����:�ر�";
        }
        ���������(�������ʾ);
    }

    private void ˢ��С���ÿ���() {
        String С������ʾ = "";
        int С���� = gui.Start.ConfigValuesMap.get("С���ÿ���");
        if (С���� <= 0) {
            С������ʾ = "С����:����";
        } else {
            С������ʾ = "С����:�ر�";
        }
        С���ÿ���(С������ʾ);
    }

    private void ˢ�´���ǿ���() {
        String �������ʾ = "";
        int ����� = gui.Start.ConfigValuesMap.get("����ǿ���");
        if (����� <= 0) {
            �������ʾ = "�����:����";
        } else {
            �������ʾ = "�����:�ر�";
        }
        ����ǿ���(�������ʾ);
    }

    private void ˢ����ɫè����() {
        String ��ɫè��ʾ = "";
        int ��ɫè = gui.Start.ConfigValuesMap.get("��ɫè����");
        if (��ɫè <= 0) {
            ��ɫè��ʾ = "��ɫè:����";
        } else {
            ��ɫè��ʾ = "��ɫè:�ر�";
        }
        ��ɫè����(��ɫè��ʾ);
    }

    private void ʯͷ�˿���(String str) {
        ʯͷ�˿���.setText(str);
    }

    private void ˢ��ʯͷ�˿���() {
        String ʯͷ����ʾ = "";
        int ʯͷ�� = gui.Start.ConfigValuesMap.get("ʯͷ�˿���");
        if (ʯͷ�� <= 0) {
            ʯͷ����ʾ = "ʯͷ��:����";
        } else {
            ʯͷ����ʾ = "ʯͷ��:�ر�";
        }
        ʯͷ�˿���(ʯͷ����ʾ);
    }

    private void ˢ�°�ѩ�˿���() {
        String ��ѩ����ʾ = "";
        int ��ѩ�� = gui.Start.ConfigValuesMap.get("��ѩ�˿���");
        if (��ѩ�� <= 0) {
            ��ѩ����ʾ = "��ѩ��:����";
        } else {
            ��ѩ����ʾ = "��ѩ��:�ر�";
        }
        ��ѩ�˿���(��ѩ����ʾ);
    }

    private void ˢ������쿪��() {
        String �������ʾ = "";
        int ����� = gui.Start.ConfigValuesMap.get("����쿪��");
        if (����� <= 0) {
            �������ʾ = "�����:����";
        } else {
            �������ʾ = "�����:�ر�";
        }
        ����쿪��(�������ʾ);
    }

    private void ˢ���Ǿ��鿪��() {
        String �Ǿ�����ʾ = "";
        int �Ǿ��� = gui.Start.ConfigValuesMap.get("�Ǿ��鿪��");
        if (�Ǿ��� <= 0) {
            �Ǿ�����ʾ = "�Ǿ���:����";
        } else {
            �Ǿ�����ʾ = "�Ǿ���:�ر�";
        }
        �Ǿ��鿪��(�Ǿ�����ʾ);
    }

    private void ˢ����Ƥ�￪��() {
        String ��Ƥ����ʾ = "";
        int ��Ƥ�� = gui.Start.ConfigValuesMap.get("��Ƥ�￪��");
        if (��Ƥ�� <= 0) {
            ��Ƥ����ʾ = "��Ƥ��:����";
        } else {
            ��Ƥ����ʾ = "��Ƥ��:�ر�";
        }
        ��Ƥ�￪��(��Ƥ����ʾ);
    }

    private void ˢ������ֿ���() {
        String �������ʾ = "";
        int ����� = gui.Start.ConfigValuesMap.get("����ֿ���");
        if (����� <= 0) {
            �������ʾ = "�����:����";
        } else {
            �������ʾ = "�����:�ر�";
        }
        ����ֿ���(�������ʾ);
    }

    private void ˢ�´󺣹꿪��() {
        String �󺣹���ʾ = "";
        int �󺣹� = gui.Start.ConfigValuesMap.get("�󺣹꿪��");
        if (�󺣹� <= 0) {
            �󺣹���ʾ = "�󺣹�:����";
        } else {
            �󺣹���ʾ = "�󺣹�:�ر�";
        }
        �󺣹꿪��(�󺣹���ʾ);
    }

    private void ˢ�º��з����() {
        String ���з��ʾ = "";
        int ���з = gui.Start.ConfigValuesMap.get("���з����");
        if (���з <= 0) {
            ���з��ʾ = "���з:����";
        } else {
            ���з��ʾ = "���з:�ر�";
        }
        ���з����(���з��ʾ);
    }

    private void ˢ��С���߿���() {
        String С������ʾ = "";
        int С���� = gui.Start.ConfigValuesMap.get("С���߿���");
        if (С���� <= 0) {
            С������ʾ = "С����:����";
        } else {
            С������ʾ = "С����:�ر�";
        }
        С���߿���(С������ʾ);
    }

    private void ˢ������ţ����() {
        String ����ţ��ʾ = "";
        int ����ţ = gui.Start.ConfigValuesMap.get("����ţ����");
        if (����ţ <= 0) {
            ����ţ��ʾ = "����ţ:����";
        } else {
            ����ţ��ʾ = "����ţ:�ر�";
        }
        ����ţ����(����ţ��ʾ);
    }

    private void ˢ��ƯƯ����() {
        String ƯƯ����ʾ = "";
        int ƯƯ�� = gui.Start.ConfigValuesMap.get("ƯƯ����");
        if (ƯƯ�� <= 0) {
            ƯƯ����ʾ = "ƯƯ��:����";
        } else {
            ƯƯ����ʾ = "ƯƯ��:�ر�";
        }
        ƯƯ����(ƯƯ����ʾ);
    }

    private void ˢ����ˮ�鿪��() {
        String ��ˮ����ʾ = "";
        int ��ˮ�� = gui.Start.ConfigValuesMap.get("��ˮ�鿪��");
        if (��ˮ�� <= 0) {
            ��ˮ����ʾ = "��ˮ��:����";
        } else {
            ��ˮ����ʾ = "��ˮ��:�ر�";
        }
        ��ˮ�鿪��(��ˮ����ʾ);
    }

    private void ˢ��Ģ���п���() {
        String Ģ������ʾ = "";
        int Ģ���� = gui.Start.ConfigValuesMap.get("Ģ���п���");
        if (Ģ���� <= 0) {
            Ģ������ʾ = "Ģ����:����";
        } else {
            Ģ������ʾ = "Ģ����:�ر�";
        }
        Ģ���п���(Ģ������ʾ);
    }

    private void ˢ��ע���˺�����() {
        String ע���˺�������ʾ = "";
        int ע���˺����� = gui.Start.ConfigValuesMap.get("ע���˺�����");

        ע���˺�������ʾ = "" + ע���˺�����;

        ����ע���˺�����(ע���˺�������ʾ);
    }

    private void ˢ��ע��ɫ����() {
        String ע���˺�������ʾ = "";
        int ע���˺����� = gui.Start.ConfigValuesMap.get("������ɫ����");

        ע���˺�������ʾ = "" + ע���˺�����;

        ���ƴ�����ɫ����(ע���˺�������ʾ);
    }

    private void ˢ�·�����������() {
        String ��������������ʾ = "";
        int ������������ = gui.Start.ConfigValuesMap.get("������������");

        ��������������ʾ = "" + ������������;

        ������������(��������������ʾ);
    }

    private void ˢ��ð�ռҵȼ�����() {
        String ð�ռҵȼ�������ʾ = "";
        int ð�ռҵȼ����� = gui.Start.ConfigValuesMap.get("ð�ռҵȼ�����");

        ð�ռҵȼ�������ʾ = "" + ð�ռҵȼ�����;

        ð�ռҵȼ�����(ð�ռҵȼ�������ʾ);
    }

    private void ˢ����ʿ�ŵȼ�����() {
        String ��ʿ�ŵȼ�������ʾ = "";
        int ��ʿ�ŵȼ����� = gui.Start.ConfigValuesMap.get("��ʿ�ŵȼ�����");

        ��ʿ�ŵȼ�������ʾ = "" + ��ʿ�ŵȼ�����;

        ��ʿ�ŵȼ�����(��ʿ�ŵȼ�������ʾ);
    }

    private void ˢ�´�����ɫ����() {
        String ������ɫ������ʾ = "";
        int ������ɫ���� = gui.Start.ConfigValuesMap.get("������ɫ����");

        ������ɫ������ʾ = "" + ������ɫ����;

        ���ƴ�����ɫ����(������ɫ������ʾ);
    }

    public void ˢ�·�MAC() {
        for (int i = ((DefaultTableModel) (this.��MAC.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��MAC.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM macbans");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ��MAC.getModel()).insertRow(��MAC.getRowCount(), new Object[]{
                    rs.getInt("macbanid"),
                    rs.getString("mac"),
                    NPCConversationManager.MACȡ�˺�(rs.getString("mac"))
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ˢ�·�IP() {
        for (int i = ((DefaultTableModel) (this.��IP.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.��IP.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM ipbans");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ��IP.getModel()).insertRow(��IP.getRowCount(), new Object[]{
                    rs.getInt("ipbanid"),
                    rs.getString("ip"),
                    NPCConversationManager.IPȡ�˺�(rs.getString("ip"))
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        ð�ռ�ְҵ���� = new javax.swing.JButton();
        ս��ְҵ���� = new javax.swing.JButton();
        ��ʿ��ְҵ���� = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        ��Ϸ������Ѷ = new javax.swing.JButton();
        ���������� = new javax.swing.JButton();
        ������ٿ��� = new javax.swing.JButton();
        ��Ϸָ��� = new javax.swing.JButton();
        ��Ϸ���ȿ��� = new javax.swing.JButton();
        ������ҿ��� = new javax.swing.JButton();
        ��ҽ��׿��� = new javax.swing.JButton();
        ������Ʒ���� = new javax.swing.JButton();
        ��ֹ��½���� = new javax.swing.JButton();
        ȫ���������� = new javax.swing.JButton();
        ��ӭ�������� = new javax.swing.JButton();
        ����㲥���� = new javax.swing.JButton();
        ����ֿ⿪�� = new javax.swing.JButton();
        ��Ϸ�ֿ⿪�� = new javax.swing.JButton();
        �����п��� = new javax.swing.JButton();
        ��Ӷ���˿��� = new javax.swing.JButton();
        �������ѿ��� = new javax.swing.JButton();
        �۷�㲥���� = new javax.swing.JButton();
        ������쿪�� = new javax.swing.JButton();
        �ű����뿪�� = new javax.swing.JButton();
        �������濪�� = new javax.swing.JButton();
        ָ��֪ͨ���� = new javax.swing.JButton();
        ���յ�ͼ���� = new javax.swing.JButton();
        ��½��¼���� = new javax.swing.JButton();
        �����¼���� = new javax.swing.JButton();
        ��½��֤���� = new javax.swing.JButton();
        �̳Ǽ�⿪�� = new javax.swing.JButton();
        �����࿪���� = new javax.swing.JButton();
        IP�࿪���� = new javax.swing.JButton();
        ��½�������� = new javax.swing.JButton();
        ��½���п��� = new javax.swing.JButton();
        �������ؿ��� = new javax.swing.JButton();
        �����˿��� = new javax.swing.JButton();
        ��½�������� = new javax.swing.JButton();
        ˫��Ƶ������ = new javax.swing.JButton();
        ����״̬���� = new javax.swing.JButton();
        Խ����ֿ��� = new javax.swing.JButton();
        ��Ϸ���˿��� = new javax.swing.JButton();
        ��ͼ���ƿ��� = new javax.swing.JButton();
        ��Ӷ����ʱ�� = new javax.swing.JTextField();
        jLabel249 = new javax.swing.JLabel();
        �޸���ʿ�ŵȼ�����1 = new javax.swing.JButton();
        ��ͼ�浵���� = new javax.swing.JButton();
        �һ���⿪�� = new javax.swing.JButton();
        ƣ��ֵ = new javax.swing.JTextField();
        jLabel254 = new javax.swing.JLabel();
        �޸���ʿ�ŵȼ�����3 = new javax.swing.JButton();
        ��ħ���ѿ��� = new javax.swing.JButton();
        ����ģʽ���� = new javax.swing.JButton();
        ������⿪�� = new javax.swing.JButton();
        Ⱥ����⿪�� = new javax.swing.JButton();
        ȫ����⿪�� = new javax.swing.JButton();
        ���ּ�⿪�� = new javax.swing.JButton();
        ���ټ�⿪�� = new javax.swing.JButton();
        jLabel264 = new javax.swing.JLabel();
        ����˺� = new javax.swing.JTextField();
        �޸�����˺� = new javax.swing.JButton();
        �����⿪�� = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        ����ţ���� = new javax.swing.JButton();
        Ģ���п��� = new javax.swing.JButton();
        ��ˮ�鿪�� = new javax.swing.JButton();
        ƯƯ���� = new javax.swing.JButton();
        С���߿��� = new javax.swing.JButton();
        ���з���� = new javax.swing.JButton();
        �󺣹꿪�� = new javax.swing.JButton();
        ����ֿ��� = new javax.swing.JButton();
        ��Ƥ�￪�� = new javax.swing.JButton();
        �Ǿ��鿪�� = new javax.swing.JButton();
        ����쿪�� = new javax.swing.JButton();
        ��ѩ�˿��� = new javax.swing.JButton();
        ʯͷ�˿��� = new javax.swing.JButton();
        ��ɫè���� = new javax.swing.JButton();
        ����ǿ��� = new javax.swing.JButton();
        ��������� = new javax.swing.JButton();
        ��Ұ���� = new javax.swing.JButton();
        С���ÿ��� = new javax.swing.JButton();
        �����㿪�� = new javax.swing.JButton();
        ��Ģ������ = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        ð�ռҵȼ����� = new javax.swing.JTextField();
        �޸�ð�ռҵȼ����� = new javax.swing.JButton();
        jLabel253 = new javax.swing.JLabel();
        ��ʿ�ŵȼ����� = new javax.swing.JTextField();
        jLabel252 = new javax.swing.JLabel();
        �޸���ʿ�ŵȼ����� = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        ����ע���˺����� = new javax.swing.JTextField();
        jLabel250 = new javax.swing.JLabel();
        ���ƴ�����ɫ���� = new javax.swing.JTextField();
        jLabel251 = new javax.swing.JLabel();
        �޸��˺����� = new javax.swing.JButton();
        �޸Ľ�ɫ���� = new javax.swing.JButton();
        ������������ = new javax.swing.JTextField();
        �޸ķ����������� = new javax.swing.JButton();
        ��Ϸ�˺�ע�� = new javax.swing.JButton();
        ������ע�Ὺ�� = new javax.swing.JButton();
        jLabel263 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        ������࿪���� = new javax.swing.JTextField();
        �޸�ð�ռҵȼ�����1 = new javax.swing.JButton();
        jLabel262 = new javax.swing.JLabel();
        IP�࿪���� = new javax.swing.JTextField();
        jLabel267 = new javax.swing.JLabel();
        �޸���ʿ�ŵȼ�����2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane136 = new javax.swing.JScrollPane();
        ����ӳɱ� = new javax.swing.JTable();
        ����ӳɱ���� = new javax.swing.JTextField();
        ����ӳɱ����� = new javax.swing.JTextField();
        ����ӳɱ���ֵ = new javax.swing.JTextField();
        ����ӳɱ��޸� = new javax.swing.JButton();
        jLabel330 = new javax.swing.JLabel();
        jLabel331 = new javax.swing.JLabel();
        jLabel333 = new javax.swing.JLabel();
        ��Ϸ����ӳ�˵�� = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        ��ѩ�쿪�� = new javax.swing.JButton();
        �����ݿ��� = new javax.swing.JButton();
        �º컨���� = new javax.swing.JButton();
        ��ѩ������ = new javax.swing.JButton();
        �·�Ҷ���� = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel281 = new javax.swing.JLabel();
        һ������ = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        ���� = new javax.swing.JPanel();
        jScrollPane137 = new javax.swing.JScrollPane();
        ���ӱ� = new javax.swing.JTable();
        ������� = new javax.swing.JTextField();
        �������� = new javax.swing.JTextField();
        ������ֵ = new javax.swing.JTextField();
        �����޸� = new javax.swing.JButton();
        jLabel332 = new javax.swing.JLabel();
        jLabel334 = new javax.swing.JLabel();
        jLabel335 = new javax.swing.JLabel();
        jLabel337 = new javax.swing.JLabel();
        ������������ = new javax.swing.JButton();
        ���IPMAC = new javax.swing.JPanel();
        jScrollPane107 = new javax.swing.JScrollPane();
        ��IP = new javax.swing.JTable();
        jScrollPane108 = new javax.swing.JScrollPane();
        ��MAC = new javax.swing.JTable();
        ˢ�·�IP = new javax.swing.JButton();
        ˢ�·�MAC = new javax.swing.JButton();
        ɾ��IP���� = new javax.swing.JTextField();
        ɾ��MAC = new javax.swing.JButton();
        ɾ��IP = new javax.swing.JButton();
        ɾMAC���� = new javax.swing.JTextField();
        jLabel338 = new javax.swing.JLabel();
        jLabel339 = new javax.swing.JLabel();
        ɾ��NPC = new javax.swing.JPanel();
        jScrollPane106 = new javax.swing.JScrollPane();
        �����NPC = new javax.swing.JTable();
        ˢ�������NPC = new javax.swing.JButton();
        ɾ�������npc���� = new javax.swing.JTextField();
        ɾ�������npc = new javax.swing.JButton();
        jLabel336 = new javax.swing.JLabel();
        jLabel285 = new javax.swing.JLabel();
        ��Ϸ�㲥 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        �㲥��Ϣ = new javax.swing.JTable();
        �㲥�ı� = new javax.swing.JTextField();
        ˢ�¹�� = new javax.swing.JButton();
        ������� = new javax.swing.JButton();
        �㲥��� = new javax.swing.JTextField();
        ɾ���㲥 = new javax.swing.JButton();
        �޸Ĺ㲥 = new javax.swing.JButton();
        �ʼ�ϵͳ = new javax.swing.JPanel();
        ����1���� = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        �ʼ��ı� = new javax.swing.JTextPane();
        �ռ��� = new javax.swing.JTextField();
        ����3���� = new javax.swing.JTextField();
        ����1���� = new javax.swing.JTextField();
        ����2���� = new javax.swing.JTextField();
        ����2���� = new javax.swing.JTextField();
        ����3���� = new javax.swing.JTextField();
        jLabel340 = new javax.swing.JLabel();
        jLabel341 = new javax.swing.JLabel();
        jLabel342 = new javax.swing.JLabel();
        jLabel343 = new javax.swing.JLabel();
        jLabel344 = new javax.swing.JLabel();
        jLabel345 = new javax.swing.JLabel();
        jLabel346 = new javax.swing.JLabel();
        jLabel347 = new javax.swing.JLabel();
        jLabel348 = new javax.swing.JLabel();
        jLabel349 = new javax.swing.JLabel();
        jLabel350 = new javax.swing.JLabel();
        jLabel351 = new javax.swing.JLabel();
        jLabel352 = new javax.swing.JLabel();
        jLabel353 = new javax.swing.JLabel();
        jLabel354 = new javax.swing.JLabel();
        ȫ������ = new javax.swing.JButton();
        ���˷��� = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        ��ɫ��Ϣ = new javax.swing.JTable();
        ȷ��ȫ�� = new javax.swing.JButton();
        ˢ�½�ɫ = new javax.swing.JButton();
        ���߽�ɫ = new javax.swing.JButton();
        ���߽�ɫ = new javax.swing.JButton();
        �ռ������� = new javax.swing.JTextField();
        �ʼ����� = new javax.swing.JTextField();
        jLabel355 = new javax.swing.JLabel();
        ������� = new javax.swing.JButton();
        ���߷��� = new javax.swing.JButton();
        ���������ʼ� = new javax.swing.JButton();
        jLabel356 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ������������ = new javax.swing.JButton();
        ������������ = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        ����������Ϣ = new javax.swing.JTable();
        jLabel255 = new javax.swing.JLabel();
        ˢ�¾���� = new javax.swing.JButton();
        װ��������� = new javax.swing.JTextField();
        װ���������� = new javax.swing.JTextField();
        װ�������ȼ� = new javax.swing.JTextField();
        jLabel256 = new javax.swing.JLabel();
        jLabel257 = new javax.swing.JLabel();
        ������������1 = new javax.swing.JButton();
        jLabel258 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        װ���������� = new javax.swing.JTable();
        ˢ�����ñ� = new javax.swing.JButton();
        װ���������1 = new javax.swing.JTextField();
        װ�������ȼ�1 = new javax.swing.JTextField();
        װ����������1 = new javax.swing.JTextField();
        ������������2 = new javax.swing.JButton();
        jLabel259 = new javax.swing.JLabel();
        jLabel260 = new javax.swing.JLabel();
        jLabel261 = new javax.swing.JLabel();
        ���ܷ�Χ��� = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        ȫ�����ñ� = new javax.swing.JTable();
        ˢ��ȫ���б� = new javax.swing.JButton();
        ���ܵ��Կ��� = new javax.swing.JButton();
        ���ܼ���� = new javax.swing.JTextField();
        ���ܼ���ͼ = new javax.swing.JTextField();
        ���ܼ����ֵ = new javax.swing.JTextField();
        ����1 = new javax.swing.JButton();
        �޸�1 = new javax.swing.JButton();
        ����1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel268 = new javax.swing.JLabel();
        jLabel269 = new javax.swing.JLabel();
        jLabel270 = new javax.swing.JLabel();
        jLabel271 = new javax.swing.JLabel();
        ȫ�ּ�⿪�� = new javax.swing.JButton();
        ���ܳͷ����� = new javax.swing.JButton();

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ְҵ����", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ð�ռ�ְҵ����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ð�ռ�ְҵ����.setText("ð�ռ�");
        ð�ռ�ְҵ����.setToolTipText("<html>\n<strong><font color=\"#FF0000\">����:</font></strong><br> \n��������ҿ��Դ���ð�ռ�ְҵ��<br> \n<strong><font color=\"#FF0000\">�ر�:</font></strong><br> \n�رպ���Ҳ��ܴ���ð�ռ�ְҵ��<br> <br>  \n");
        ð�ռ�ְҵ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ð�ռ�ְҵ����ActionPerformed(evt);
            }
        });
        jPanel5.add(ð�ռ�ְҵ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 130, 30));

        ս��ְҵ����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ս��ְҵ����.setText("ս��");
        ս��ְҵ����.setToolTipText("<html>\n<strong><font color=\"#FF0000\">����:</font></strong><br> \n��������ҿ��Դ���ս��ְҵ��<br> \n<strong><font color=\"#FF0000\">�ر�:</font></strong><br> \n�رպ���Ҳ��ܴ���ս��ְҵ��<br> <br>  ");
        ս��ְҵ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ս��ְҵ����ActionPerformed(evt);
            }
        });
        jPanel5.add(ս��ְҵ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 130, 30));

        ��ʿ��ְҵ����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��ʿ��ְҵ����.setText("��ʿ��");
        ��ʿ��ְҵ����.setToolTipText("<html>\n<strong><font color=\"#FF0000\">����:</font></strong><br> \n��������ҿ��Դ�����ʿ��ְҵ��<br> \n<strong><font color=\"#FF0000\">�ر�:</font></strong><br> \n�رպ���Ҳ��ܴ�����ʿ��ְҵ��<br> <br>  ");
        ��ʿ��ְҵ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ʿ��ְҵ����ActionPerformed(evt);
            }
        });
        jPanel5.add(��ʿ��ְҵ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 130, 30));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 190, 140));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "���ÿ���", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��Ϸ������Ѷ.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��Ϸ������Ѷ.setText("������Ѷ");
        ��Ϸ������Ѷ.setToolTipText("");
        ��Ϸ������Ѷ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ϸ������ѶActionPerformed(evt);
            }
        });
        jPanel20.add(��Ϸ������Ѷ, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 40, 130, 30));

        ����������.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ����������.setText("��������");
        ����������.setToolTipText("");
        ����������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����������ActionPerformed(evt);
            }
        });
        jPanel20.add(����������, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, 130, 30));

        ������ٿ���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ������ٿ���.setText("�������");
        ������ٿ���.setToolTipText("");
        ������ٿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������ٿ���ActionPerformed(evt);
            }
        });
        jPanel20.add(������ٿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 70, 130, 30));

        ��Ϸָ���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��Ϸָ���.setText("��Ϸָ��");
        ��Ϸָ���.setToolTipText("");
        ��Ϸָ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ϸָ���ActionPerformed(evt);
            }
        });
        jPanel20.add(��Ϸָ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 130, 30));

        ��Ϸ���ȿ���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��Ϸ���ȿ���.setText("��Ϸ����");
        ��Ϸ���ȿ���.setToolTipText("");
        ��Ϸ���ȿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ϸ���ȿ���ActionPerformed(evt);
            }
        });
        jPanel20.add(��Ϸ���ȿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 130, 30));

        ������ҿ���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ������ҿ���.setText("�������");
        ������ҿ���.setToolTipText("");
        ������ҿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������ҿ���ActionPerformed(evt);
            }
        });
        jPanel20.add(������ҿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 40, 130, 30));

        ��ҽ��׿���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��ҽ��׿���.setText("��ҽ���");
        ��ҽ��׿���.setToolTipText("");
        ��ҽ��׿���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ҽ��׿���ActionPerformed(evt);
            }
        });
        jPanel20.add(��ҽ��׿���, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 130, 30));

        ������Ʒ����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ������Ʒ����.setText("������Ʒ");
        ������Ʒ����.setToolTipText("");
        ������Ʒ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������Ʒ����ActionPerformed(evt);
            }
        });
        jPanel20.add(������Ʒ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 40, 130, 30));

        ��ֹ��½����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��ֹ��½����.setText("��Ϸ��½");
        ��ֹ��½����.setToolTipText("");
        ��ֹ��½����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ֹ��½����ActionPerformed(evt);
            }
        });
        jPanel20.add(��ֹ��½����, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 130, 30));

        ȫ����������.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ȫ����������.setText("ȫ������");
        ȫ����������.setToolTipText("");
        ȫ����������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ����������ActionPerformed(evt);
            }
        });
        jPanel20.add(ȫ����������, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 130, 30));

        ��ӭ��������.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��ӭ��������.setText("��ӭ����");
        ��ӭ��������.setToolTipText("");
        ��ӭ��������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ӭ��������ActionPerformed(evt);
            }
        });
        jPanel20.add(��ӭ��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 130, 30));

        ����㲥����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ����㲥����.setText("����㲥");
        ����㲥����.setToolTipText("");
        ����㲥����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����㲥����ActionPerformed(evt);
            }
        });
        jPanel20.add(����㲥����, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 130, 30));

        ����ֿ⿪��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ����ֿ⿪��.setText("����ֿ�");
        ����ֿ⿪��.setToolTipText("");
        ����ֿ⿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ֿ⿪��ActionPerformed(evt);
            }
        });
        jPanel20.add(����ֿ⿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, 130, 30));

        ��Ϸ�ֿ⿪��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��Ϸ�ֿ⿪��.setText("��Ϸ�ֿ�");
        ��Ϸ�ֿ⿪��.setToolTipText("");
        ��Ϸ�ֿ⿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ϸ�ֿ⿪��ActionPerformed(evt);
            }
        });
        jPanel20.add(��Ϸ�ֿ⿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, 130, 30));

        �����п���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �����п���.setText("������");
        �����п���.setToolTipText("");
        �����п���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����п���ActionPerformed(evt);
            }
        });
        jPanel20.add(�����п���, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 100, 130, 30));

        ��Ӷ���˿���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��Ӷ���˿���.setText("��Ӷ����");
        ��Ӷ���˿���.setToolTipText("");
        ��Ӷ���˿���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ӷ���˿���ActionPerformed(evt);
            }
        });
        jPanel20.add(��Ӷ���˿���, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 100, 130, 30));

        �������ѿ���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �������ѿ���.setText("��������");
        �������ѿ���.setToolTipText("");
        �������ѿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �������ѿ���ActionPerformed(evt);
            }
        });
        jPanel20.add(�������ѿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 130, 30));

        �۷�㲥����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �۷�㲥����.setText("�۷�㲥");
        �۷�㲥����.setToolTipText("");
        �۷�㲥����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �۷�㲥����ActionPerformed(evt);
            }
        });
        jPanel20.add(�۷�㲥����, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 130, 30));

        ������쿪��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ������쿪��.setText("�������");
        ������쿪��.setToolTipText("");
        ������쿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������쿪��ActionPerformed(evt);
            }
        });
        jPanel20.add(������쿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 130, 30));

        �ű����뿪��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �ű����뿪��.setText("�ű�����");
        �ű����뿪��.setToolTipText("");
        �ű����뿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �ű����뿪��ActionPerformed(evt);
            }
        });
        jPanel20.add(�ű����뿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 130, 30));

        �������濪��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �������濪��.setText("��������");
        �������濪��.setToolTipText("");
        �������濪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �������濪��ActionPerformed(evt);
            }
        });
        jPanel20.add(�������濪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 130, 30));

        ָ��֪ͨ����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ָ��֪ͨ����.setText("ָ��֪ͨ");
        ָ��֪ͨ����.setToolTipText("\n");
        ָ��֪ͨ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ָ��֪ͨ����ActionPerformed(evt);
            }
        });
        jPanel20.add(ָ��֪ͨ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 130, 30));

        ���յ�ͼ����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ���յ�ͼ����.setText("���յ�ͼ");
        ���յ�ͼ����.setToolTipText("");
        ���յ�ͼ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���յ�ͼ����ActionPerformed(evt);
            }
        });
        jPanel20.add(���յ�ͼ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 130, 30));

        ��½��¼����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��½��¼����.setText("��½��¼");
        ��½��¼����.setToolTipText("\n");
        ��½��¼����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��½��¼����ActionPerformed(evt);
            }
        });
        jPanel20.add(��½��¼����, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 130, 30));

        �����¼����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �����¼����.setText("������¼");
        �����¼����.setToolTipText("");
        �����¼����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����¼����ActionPerformed(evt);
            }
        });
        jPanel20.add(�����¼����, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 70, 130, 30));

        ��½��֤����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��½��֤����.setText("��½��֤");
        ��½��֤����.setToolTipText("");
        ��½��֤����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��½��֤����ActionPerformed(evt);
            }
        });
        jPanel20.add(��½��֤����, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 130, 30));

        �̳Ǽ�⿪��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �̳Ǽ�⿪��.setText("�̳Ǽ��");
        �̳Ǽ�⿪��.setToolTipText("");
        �̳Ǽ�⿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �̳Ǽ�⿪��ActionPerformed(evt);
            }
        });
        jPanel20.add(�̳Ǽ�⿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 130, 30));

        �����࿪����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �����࿪����.setText("�����࿪");
        �����࿪����.setToolTipText("");
        �����࿪����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����࿪����ActionPerformed(evt);
            }
        });
        jPanel20.add(�����࿪����, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 130, 30));

        IP�࿪����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        IP�࿪����.setText("IP�࿪");
        IP�࿪����.setToolTipText("");
        IP�࿪����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IP�࿪����ActionPerformed(evt);
            }
        });
        jPanel20.add(IP�࿪����, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 130, 30));

        ��½��������.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��½��������.setText("��½����");
        ��½��������.setToolTipText("");
        ��½��������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��½��������ActionPerformed(evt);
            }
        });
        jPanel20.add(��½��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 130, 30));

        ��½���п���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��½���п���.setText("��½����");
        ��½���п���.setToolTipText("");
        ��½���п���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��½���п���ActionPerformed(evt);
            }
        });
        jPanel20.add(��½���п���, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 130, 130, 30));

        �������ؿ���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �������ؿ���.setText("��������");
        �������ؿ���.setToolTipText("");
        �������ؿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �������ؿ���ActionPerformed(evt);
            }
        });
        jPanel20.add(�������ؿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, 130, 30));

        �����˿���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �����˿���.setText("������");
        �����˿���.setToolTipText("");
        �����˿���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����˿���ActionPerformed(evt);
            }
        });
        jPanel20.add(�����˿���, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, 130, 30));

        ��½��������.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��½��������.setText("��½����");
        ��½��������.setToolTipText("");
        ��½��������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��½��������ActionPerformed(evt);
            }
        });
        jPanel20.add(��½��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 130, 130, 30));

        ˫��Ƶ������.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ˫��Ƶ������.setText("˫��Ƶ��");
        ˫��Ƶ������.setToolTipText("");
        ˫��Ƶ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˫��Ƶ������ActionPerformed(evt);
            }
        });
        jPanel20.add(˫��Ƶ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 130, 30));

        ����״̬����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ����״̬����.setText("����״̬");
        ����״̬����.setToolTipText("");
        ����״̬����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����״̬����ActionPerformed(evt);
            }
        });
        jPanel20.add(����״̬����, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 130, 30));

        Խ����ֿ���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        Խ����ֿ���.setText("Խ�����");
        Խ����ֿ���.setToolTipText("");
        Խ����ֿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Խ����ֿ���ActionPerformed(evt);
            }
        });
        jPanel20.add(Խ����ֿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 130, 130, 30));

        ��Ϸ���˿���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��Ϸ���˿���.setText("��Ϸ����");
        ��Ϸ���˿���.setToolTipText("");
        ��Ϸ���˿���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ϸ���˿���ActionPerformed(evt);
            }
        });
        jPanel20.add(��Ϸ���˿���, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 130, 30));

        ��ͼ���ƿ���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��ͼ���ƿ���.setText("��ͼ����");
        ��ͼ���ƿ���.setToolTipText("");
        ��ͼ���ƿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ͼ���ƿ���ActionPerformed(evt);
            }
        });
        jPanel20.add(��ͼ���ƿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 130, 30));
        jPanel20.add(��Ӷ����ʱ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 260, 100, 25));

        jLabel249.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel249.setText("��Ӷ����ʱ��;");
        jPanel20.add(jLabel249, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 230, -1, 30));

        �޸���ʿ�ŵȼ�����1.setText("�޸�");
        �޸���ʿ�ŵȼ�����1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸���ʿ�ŵȼ�����1ActionPerformed(evt);
            }
        });
        jPanel20.add(�޸���ʿ�ŵȼ�����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 260, 70, 25));

        ��ͼ�浵����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��ͼ�浵����.setText("��ͼ�浵");
        ��ͼ�浵����.setToolTipText("");
        ��ͼ�浵����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ͼ�浵����ActionPerformed(evt);
            }
        });
        jPanel20.add(��ͼ�浵����, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 130, 30));

        �һ���⿪��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �һ���⿪��.setForeground(new java.awt.Color(255, 51, 51));
        �һ���⿪��.setText("�һ����");
        �һ���⿪��.setToolTipText("");
        �һ���⿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �һ���⿪��ActionPerformed(evt);
            }
        });
        jPanel20.add(�һ���⿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 130, 30));
        jPanel20.add(ƣ��ֵ, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 260, 100, 25));

        jLabel254.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel254.setText("ÿ������ƣ��ֵ;");
        jPanel20.add(jLabel254, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 230, -1, 30));

        �޸���ʿ�ŵȼ�����3.setText("�޸�");
        �޸���ʿ�ŵȼ�����3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸���ʿ�ŵȼ�����3ActionPerformed(evt);
            }
        });
        jPanel20.add(�޸���ʿ�ŵȼ�����3, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 260, 70, 25));

        ��ħ���ѿ���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��ħ���ѿ���.setText("��ħ����");
        ��ħ���ѿ���.setToolTipText("");
        ��ħ���ѿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ħ���ѿ���ActionPerformed(evt);
            }
        });
        jPanel20.add(��ħ���ѿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 130, 30));

        ����ģʽ����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ����ģʽ����.setText("����ģʽ");
        ����ģʽ����.setToolTipText("");
        ����ģʽ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ģʽ����ActionPerformed(evt);
            }
        });
        jPanel20.add(����ģʽ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 160, 130, 30));

        ������⿪��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ������⿪��.setForeground(new java.awt.Color(255, 0, 51));
        ������⿪��.setText("�������");
        ������⿪��.setToolTipText("");
        ������⿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������⿪��ActionPerformed(evt);
            }
        });
        jPanel20.add(������⿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 130, 30));

        Ⱥ����⿪��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        Ⱥ����⿪��.setForeground(new java.awt.Color(255, 0, 51));
        Ⱥ����⿪��.setText("Ⱥ�����");
        Ⱥ����⿪��.setToolTipText("");
        Ⱥ����⿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ⱥ����⿪��ActionPerformed(evt);
            }
        });
        jPanel20.add(Ⱥ����⿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 130, 30));

        ȫ����⿪��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ȫ����⿪��.setForeground(new java.awt.Color(255, 0, 51));
        ȫ����⿪��.setText("ȫ�����");
        ȫ����⿪��.setToolTipText("");
        ȫ����⿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ����⿪��ActionPerformed(evt);
            }
        });
        jPanel20.add(ȫ����⿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 130, 30));

        ���ּ�⿪��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ���ּ�⿪��.setForeground(new java.awt.Color(255, 0, 51));
        ���ּ�⿪��.setText("���ּ��");
        ���ּ�⿪��.setToolTipText("");
        ���ּ�⿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ּ�⿪��ActionPerformed(evt);
            }
        });
        jPanel20.add(���ּ�⿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 130, 30));

        ���ټ�⿪��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ���ټ�⿪��.setForeground(new java.awt.Color(255, 0, 51));
        ���ټ�⿪��.setText("���ټ��");
        ���ټ�⿪��.setToolTipText("");
        ���ټ�⿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ټ�⿪��ActionPerformed(evt);
            }
        });
        jPanel20.add(���ټ�⿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 130, 30));

        jLabel264.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel264.setText("������ɱֵ������˺��ж�/��");
        jPanel20.add(jLabel264, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, -1, 30));

        ����˺�.setText("500");
        jPanel20.add(����˺�, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 100, 25));

        �޸�����˺�.setText("�޸�");
        �޸�����˺�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸�����˺�ActionPerformed(evt);
            }
        });
        jPanel20.add(�޸�����˺�, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, 70, 25));

        �����⿪��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �����⿪��.setForeground(new java.awt.Color(255, 0, 51));
        �����⿪��.setText("������");
        �����⿪��.setToolTipText("");
        �����⿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����⿪��ActionPerformed(evt);
            }
        });
        jPanel20.add(�����⿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 130, 30));

        jPanel4.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 1250, 310));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "���򿪹�", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ����ţ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ţ����.setText("����ţ");
        ����ţ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ţ����ActionPerformed(evt);
            }
        });
        jPanel21.add(����ţ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 130, -1));

        Ģ���п���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        Ģ���п���.setText("Ģ����");
        Ģ���п���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ģ���п���ActionPerformed(evt);
            }
        });
        jPanel21.add(Ģ���п���, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 130, -1));

        ��ˮ�鿪��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ˮ�鿪��.setText("��ˮ��");
        ��ˮ�鿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ˮ�鿪��ActionPerformed(evt);
            }
        });
        jPanel21.add(��ˮ�鿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 130, -1));

        ƯƯ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ƯƯ����.setText("ƯƯ��");
        ƯƯ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ƯƯ����ActionPerformed(evt);
            }
        });
        jPanel21.add(ƯƯ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 130, -1));

        С���߿���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        С���߿���.setText("С����");
        С���߿���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                С���߿���ActionPerformed(evt);
            }
        });
        jPanel21.add(С���߿���, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 130, -1));

        ���з����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���з����.setText("���з");
        ���з����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���з����ActionPerformed(evt);
            }
        });
        jPanel21.add(���з����, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 130, -1));

        �󺣹꿪��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �󺣹꿪��.setText("�󺣹�");
        �󺣹꿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �󺣹꿪��ActionPerformed(evt);
            }
        });
        jPanel21.add(�󺣹꿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 40, 130, -1));

        ����ֿ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ֿ���.setText("�����");
        ����ֿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ֿ���ActionPerformed(evt);
            }
        });
        jPanel21.add(����ֿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 40, 130, -1));

        ��Ƥ�￪��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ƥ�￪��.setText("��Ƥ��");
        ��Ƥ�￪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ƥ�￪��ActionPerformed(evt);
            }
        });
        jPanel21.add(��Ƥ�￪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 40, 130, -1));

        �Ǿ��鿪��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �Ǿ��鿪��.setText("�Ǿ���");
        �Ǿ��鿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �Ǿ��鿪��ActionPerformed(evt);
            }
        });
        jPanel21.add(�Ǿ��鿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 130, -1));

        ����쿪��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����쿪��.setText("�����");
        ����쿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����쿪��ActionPerformed(evt);
            }
        });
        jPanel21.add(����쿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 130, -1));

        ��ѩ�˿���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ѩ�˿���.setText("��ѩ��");
        ��ѩ�˿���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ѩ�˿���ActionPerformed(evt);
            }
        });
        jPanel21.add(��ѩ�˿���, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 130, -1));

        ʯͷ�˿���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ʯͷ�˿���.setText("ʯͷ��");
        ʯͷ�˿���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ʯͷ�˿���ActionPerformed(evt);
            }
        });
        jPanel21.add(ʯͷ�˿���, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, 130, -1));

        ��ɫè����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��ɫè����.setText("��ɫè");
        ��ɫè����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ɫè����ActionPerformed(evt);
            }
        });
        jPanel21.add(��ɫè����, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 130, -1));

        ����ǿ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ǿ���.setText("�����");
        ����ǿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ǿ���ActionPerformed(evt);
            }
        });
        jPanel21.add(����ǿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 130, -1));

        ���������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ���������.setText("�����");
        ���������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���������ActionPerformed(evt);
            }
        });
        jPanel21.add(���������, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 130, -1));

        ��Ұ����.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ұ����.setText("��Ұ��");
        ��Ұ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ұ����ActionPerformed(evt);
            }
        });
        jPanel21.add(��Ұ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 70, 130, -1));

        С���ÿ���.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        С���ÿ���.setText("С����");
        С���ÿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                С���ÿ���ActionPerformed(evt);
            }
        });
        jPanel21.add(С���ÿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 130, -1));

        �����㿪��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �����㿪��.setText("������");
        �����㿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����㿪��ActionPerformed(evt);
            }
        });
        jPanel21.add(�����㿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, 130, -1));

        ��Ģ������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ģ������.setText("��Ģ��");
        ��Ģ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ģ������ActionPerformed(evt);
            }
        });
        jPanel21.add(��Ģ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 70, 130, -1));

        jPanel4.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 350, 1250, 160));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�ȼ�����", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel17.add(ð�ռҵȼ�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 60, -1));

        �޸�ð�ռҵȼ�����.setText("�޸�");
        �޸�ð�ռҵȼ�����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸�ð�ռҵȼ�����ActionPerformed(evt);
            }
        });
        jPanel17.add(�޸�ð�ռҵȼ�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 70, -1));

        jLabel253.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel253.setText("ð�ռҵȼ����ޣ�");
        jPanel17.add(jLabel253, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 30));
        jPanel17.add(��ʿ�ŵȼ�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 60, -1));

        jLabel252.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel252.setText("��ʿ�ŵȼ����ޣ�");
        jPanel17.add(jLabel252, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 30));

        �޸���ʿ�ŵȼ�����.setText("�޸�");
        �޸���ʿ�ŵȼ�����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸���ʿ�ŵȼ�����ActionPerformed(evt);
            }
        });
        jPanel17.add(�޸���ʿ�ŵȼ�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 70, -1));

        jPanel4.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 520, 310, 140));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�˺�����", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(����ע���˺�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 80, -1));

        jLabel250.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel250.setText("���ƴ�����ɫ������");
        jPanel6.add(jLabel250, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 30));

        ���ƴ�����ɫ����.setText("1");
        jPanel6.add(���ƴ�����ɫ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 80, -1));

        jLabel251.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel251.setText("����ע���˺�������");
        jPanel6.add(jLabel251, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 30));

        �޸��˺�����.setText("�޸�");
        �޸��˺�����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸��˺�����ActionPerformed(evt);
            }
        });
        jPanel6.add(�޸��˺�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 65, 25));

        �޸Ľ�ɫ����.setText("�޸�");
        �޸Ľ�ɫ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸Ľ�ɫ����ActionPerformed(evt);
            }
        });
        jPanel6.add(�޸Ľ�ɫ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 65, 25));
        jPanel6.add(������������, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 80, -1));

        �޸ķ�����������.setText("�޸�");
        �޸ķ�����������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸ķ�����������ActionPerformed(evt);
            }
        });
        jPanel6.add(�޸ķ�����������, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 65, 25));

        ��Ϸ�˺�ע��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ��Ϸ�˺�ע��.setText("�˺�ע��");
        ��Ϸ�˺�ע��.setToolTipText("");
        ��Ϸ�˺�ע��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ϸ�˺�ע��ActionPerformed(evt);
            }
        });
        jPanel6.add(��Ϸ�˺�ע��, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 130, 25));

        ������ע�Ὺ��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ������ע�Ὺ��.setText("������ע��");
        ������ע�Ὺ��.setToolTipText("");
        ������ע�Ὺ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������ע�Ὺ��ActionPerformed(evt);
            }
        });
        jPanel6.add(������ע�Ὺ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 130, 25));

        jLabel263.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel263.setText("��������������");
        jPanel6.add(jLabel263, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 30));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 520, 440, 140));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "�࿪����", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel18.add(������࿪����, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 70, -1));

        �޸�ð�ռҵȼ�����1.setText("�޸�");
        �޸�ð�ռҵȼ�����1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸�ð�ռҵȼ�����1ActionPerformed(evt);
            }
        });
        jPanel18.add(�޸�ð�ռҵȼ�����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 70, -1));

        jLabel262.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel262.setText("������࿪��");
        jPanel18.add(jLabel262, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 30));
        jPanel18.add(IP�࿪����, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 70, -1));

        jLabel267.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel267.setText("IP��ַ�࿪��");
        jPanel18.add(jLabel267, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 30));

        �޸���ʿ�ŵȼ�����2.setText("�޸�");
        �޸���ʿ�ŵȼ�����2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸���ʿ�ŵȼ�����2ActionPerformed(evt);
            }
        });
        jPanel18.add(�޸���ʿ�ŵȼ�����2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 70, -1));

        jPanel4.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 520, 290, 140));

        jTabbedPane4.addTab("����˹���", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), jPanel4); // NOI18N

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��Ϸ����ӳ�", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ����ӳɱ�.setFont(new java.awt.Font("��Բ", 0, 20)); // NOI18N
        ����ӳɱ�.setForeground(new java.awt.Color(102, 102, 255));
        ����ӳɱ�.setModel(new javax.swing.table.DefaultTableModel(
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
        ����ӳɱ�.getTableHeader().setReorderingAllowed(false);
        jScrollPane136.setViewportView(����ӳɱ�);

        jPanel9.add(jScrollPane136, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 400, 360));

        ����ӳɱ����.setEditable(false);
        jPanel9.add(����ӳɱ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 500, 70, -1));

        ����ӳɱ�����.setEditable(false);
        jPanel9.add(����ӳɱ�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, 230, -1));
        jPanel9.add(����ӳɱ���ֵ, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 500, 100, -1));

        ����ӳɱ��޸�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ����ӳɱ��޸�.setText("�޸�");
        ����ӳɱ��޸�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����ӳɱ��޸�ActionPerformed(evt);
            }
        });
        jPanel9.add(����ӳɱ��޸�, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 540, 100, -1));

        jLabel330.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel330.setText("��ֵ��");
        jPanel9.add(jLabel330, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 480, -1, -1));

        jLabel331.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel331.setText("���ͣ�");
        jPanel9.add(jLabel331, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 480, -1, -1));

        jLabel333.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel333.setText("��ţ�");
        jPanel9.add(jLabel333, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 480, -1, -1));

        ��Ϸ����ӳ�˵��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��Ϸ����ӳ�˵��.setText("˵��");
        ��Ϸ����ӳ�˵��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��Ϸ����ӳ�˵��ActionPerformed(evt);
            }
        });
        jPanel9.add(��Ϸ����ӳ�˵��, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 540, 100, -1));

        jTabbedPane1.addTab("����ӳ�", jPanel9);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 730));

        jTabbedPane4.addTab("��������", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), jPanel1); // NOI18N

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "��Ϸ������������\n", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��ѩ�쿪��.setText("��ѩ��");
        ��ѩ�쿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ѩ�쿪��ActionPerformed(evt);
            }
        });
        jPanel7.add(��ѩ�쿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 130, 30));

        �����ݿ���.setText("������");
        �����ݿ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����ݿ���ActionPerformed(evt);
            }
        });
        jPanel7.add(�����ݿ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 440, 130, 30));

        �º컨����.setText("�º컨");
        �º컨����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �º컨����ActionPerformed(evt);
            }
        });
        jPanel7.add(�º컨����, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, 130, 30));

        ��ѩ������.setText("��ѩ��");
        ��ѩ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ��ѩ������ActionPerformed(evt);
            }
        });
        jPanel7.add(��ѩ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 320, 130, 30));

        �·�Ҷ����.setText("�·�Ҷ");
        �·�Ҷ����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �·�Ҷ����ActionPerformed(evt);
            }
        });
        jPanel7.add(�·�Ҷ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 200, 130, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ͼƬ/����/1_����.png"))); // NOI18N
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, -1, 100));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ͼƬ/����/2_����.png"))); // NOI18N
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, -1, 100));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ͼƬ/����/3_����.png"))); // NOI18N
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, -1, 100));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ͼƬ/����/4_����.png"))); // NOI18N
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, -1, 100));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ͼƬ/����/5_����.png"))); // NOI18N
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, -1, 100));

        jLabel281.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel281.setText("��������������ð�յ����е�ͼ������Ч�������������һῨ�٣���ر�ʹ�á�");
        jPanel7.add(jLabel281, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 550, 610, 30));

        jTabbedPane4.addTab("����Ч��", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), jPanel7); // NOI18N

        һ������.setBackground(new java.awt.Color(255, 255, 255));
        һ������.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane5.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N

        ����.setBackground(new java.awt.Color(255, 255, 255));
        ����.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "���ӳɹ�������", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        ����.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ���ӱ�.setFont(new java.awt.Font("��Բ", 0, 20)); // NOI18N
        ���ӱ�.setForeground(new java.awt.Color(255, 255, 255));
        ���ӱ�.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���", "����", "�ɹ���/%"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ���ӱ�.getTableHeader().setReorderingAllowed(false);
        jScrollPane137.setViewportView(���ӱ�);

        ����.add(jScrollPane137, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, 480, 60));

        �������.setEditable(false);
        ����.add(�������, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 100, -1));

        ��������.setEditable(false);
        ����.add(��������, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, 170, -1));
        ����.add(������ֵ, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 350, 100, -1));

        �����޸�.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �����޸�.setText("�޸�");
        �����޸�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �����޸�ActionPerformed(evt);
            }
        });
        ����.add(�����޸�, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, 80, -1));

        jLabel332.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel332.setText("�ɹ���%;");
        ����.add(jLabel332, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, -1, -1));

        jLabel334.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel334.setText("���ͣ�");
        ����.add(jLabel334, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 330, -1, -1));

        jLabel335.setFont(new java.awt.Font("��Բ", 0, 24)); // NOI18N
        jLabel335.setForeground(new java.awt.Color(204, 0, 0));
        jLabel335.setText("��ʾ:���ӿ�����������������������������Գɹ���");
        ����.add(jLabel335, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, -1, -1));

        jLabel337.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel337.setText("��ţ�");
        ����.add(jLabel337, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, -1, -1));

        ������������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ������������.setText("��������");
        ������������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������������ActionPerformed(evt);
            }
        });
        ����.add(������������, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 450, 150, 60));

        jTabbedPane5.addTab("����", ����);

        ���IPMAC.setBackground(new java.awt.Color(255, 255, 255));
        ���IPMAC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "IP/MAC���", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), new java.awt.Color(0, 0, 0))); // NOI18N
        ���IPMAC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ��IP.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��IP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���к�", "IP��ַ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane107.setViewportView(��IP);

        ���IPMAC.add(jScrollPane107, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 330, 450));

        ��MAC.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ��MAC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "���к�", "MAC��ַ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane108.setViewportView(��MAC);

        ���IPMAC.add(jScrollPane108, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 330, 450));

        ˢ�·�IP.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ�·�IP.setText("ˢ��");
        ˢ�·�IP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�·�IPActionPerformed(evt);
            }
        });
        ���IPMAC.add(ˢ�·�IP, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 520, 70, 30));

        ˢ�·�MAC.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ�·�MAC.setText("ˢ��");
        ˢ�·�MAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�·�MACActionPerformed(evt);
            }
        });
        ���IPMAC.add(ˢ�·�MAC, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 520, 70, 30));
        ���IPMAC.add(ɾ��IP����, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 520, 100, 30));

        ɾ��MAC.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ��MAC.setText("ɾ��");
        ɾ��MAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ��MACActionPerformed(evt);
            }
        });
        ���IPMAC.add(ɾ��MAC, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 520, 70, 30));

        ɾ��IP.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ��IP.setText("ɾ��");
        ɾ��IP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ��IPActionPerformed(evt);
            }
        });
        ���IPMAC.add(ɾ��IP, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 520, 70, 30));
        ���IPMAC.add(ɾMAC����, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 520, 100, 30));

        jLabel338.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel338.setText("��ţ�");
        ���IPMAC.add(jLabel338, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 500, -1, 20));

        jLabel339.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel339.setText("��ţ�");
        ���IPMAC.add(jLabel339, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 500, -1, 20));

        jTabbedPane5.addTab("��IP/MAC", ���IPMAC);

        ɾ��NPC.setBackground(new java.awt.Color(255, 255, 255));
        ɾ��NPC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ɾ�������NPC", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("��Բ", 0, 24), java.awt.Color.black)); // NOI18N
        ɾ��NPC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �����NPC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "��ͼ����", "NPC����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane106.setViewportView(�����NPC);

        ɾ��NPC.add(jScrollPane106, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 390, 460));

        ˢ�������NPC.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ�������NPC.setText("ˢ��");
        ˢ�������NPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�������NPCActionPerformed(evt);
            }
        });
        ɾ��NPC.add(ˢ�������NPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 340, -1, -1));
        ɾ��NPC.add(ɾ�������npc����, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 300, 80, -1));

        ɾ�������npc.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ�������npc.setText("ɾ��");
        ɾ�������npc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ�������npcActionPerformed(evt);
            }
        });
        ɾ��NPC.add(ɾ�������npc, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, -1));

        jLabel336.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel336.setText("NPC���룻");
        ɾ��NPC.add(jLabel336, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 280, -1, 20));

        jLabel285.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel285.setText("��ʾ��ɾ����ָ����ӵ�NPC");
        ɾ��NPC.add(jLabel285, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, 20));

        jTabbedPane5.addTab("ɾ�������NPC", ɾ��NPC);

        һ������.add(jTabbedPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 730));

        jTabbedPane4.addTab("��������", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), һ������); // NOI18N

        ��Ϸ�㲥.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        �㲥��Ϣ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "���", "5����һ������㲥����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(�㲥��Ϣ);
        if (�㲥��Ϣ.getColumnModel().getColumnCount() > 0) {
            �㲥��Ϣ.getColumnModel().getColumn(0).setResizable(false);
            �㲥��Ϣ.getColumnModel().getColumn(0).setPreferredWidth(100);
            �㲥��Ϣ.getColumnModel().getColumn(1).setResizable(false);
            �㲥��Ϣ.getColumnModel().getColumn(1).setPreferredWidth(2000);
        }

        ��Ϸ�㲥.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 670));

        �㲥�ı�.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ��Ϸ�㲥.add(�㲥�ı�, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 680, 760, 40));

        ˢ�¹��.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ˢ�¹��.setText("ˢ�¹㲥");
        ˢ�¹��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�¹��ActionPerformed(evt);
            }
        });
        ��Ϸ�㲥.add(ˢ�¹��, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 680, 100, 40));

        �������.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �������.setText("�����㲥");
        �������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �������ActionPerformed(evt);
            }
        });
        ��Ϸ�㲥.add(�������, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 680, 100, 40));

        �㲥���.setEditable(false);
        �㲥���.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ��Ϸ�㲥.add(�㲥���, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 680, 60, 40));

        ɾ���㲥.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        ɾ���㲥.setText("ɾ���㲥");
        ɾ���㲥.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ɾ���㲥ActionPerformed(evt);
            }
        });
        ��Ϸ�㲥.add(ɾ���㲥, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 680, 100, 40));

        �޸Ĺ㲥.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        �޸Ĺ㲥.setText("�޸Ĺ㲥");
        �޸Ĺ㲥.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸Ĺ㲥ActionPerformed(evt);
            }
        });
        ��Ϸ�㲥.add(�޸Ĺ㲥, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 680, 100, 40));

        jTabbedPane4.addTab("��Ϸ�㲥", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ��Ϸ�㲥); // NOI18N

        �ʼ�ϵͳ.setBackground(new java.awt.Color(255, 255, 255));
        �ʼ�ϵͳ.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ����1����.setText("0");
        �ʼ�ϵͳ.add(����1����, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 130, 20));

        �ʼ��ı�.setText("������д�ʼ�������");
        jScrollPane1.setViewportView(�ʼ��ı�);

        �ʼ�ϵͳ.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 410, 220));

        �ռ���.setEditable(false);
        �ʼ�ϵͳ.add(�ռ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 130, 20));

        ����3����.setText("0");
        �ʼ�ϵͳ.add(����3����, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 470, 130, 20));

        ����1����.setText("0");
        �ʼ�ϵͳ.add(����1����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 130, 20));

        ����2����.setText("0");
        �ʼ�ϵͳ.add(����2����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 130, 20));

        ����2����.setText("0");
        �ʼ�ϵͳ.add(����2����, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 130, 20));

        ����3����.setText("0");
        ����3����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����3����ActionPerformed(evt);
            }
        });
        �ʼ�ϵͳ.add(����3����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 130, 20));

        jLabel340.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel340.setText("����3����;");
        �ʼ�ϵͳ.add(jLabel340, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, -1, 20));

        jLabel341.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel341.setText("�ռ��ˣ�");
        �ʼ�ϵͳ.add(jLabel341, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 20));

        jLabel342.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel342.setText("�ʼ��ı���");
        �ʼ�ϵͳ.add(jLabel342, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 20));

        jLabel343.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel343.setText("2�������ȯ");
        �ʼ�ϵͳ.add(jLabel343, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, 230, 20));

        jLabel344.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel344.setText("����1����;");
        �ʼ�ϵͳ.add(jLabel344, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, 20));

        jLabel345.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel345.setText("����2����;");
        �ʼ�ϵͳ.add(jLabel345, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, 20));

        jLabel346.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel346.setText("����1����;");
        �ʼ�ϵͳ.add(jLabel346, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, -1, 20));

        jLabel347.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel347.setText("����2����;");
        �ʼ�ϵͳ.add(jLabel347, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, -1, 20));

        jLabel348.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel348.setText("����3����;");
        �ʼ�ϵͳ.add(jLabel348, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, 20));

        jLabel349.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel349.setText("������ѡ���ռ��ˣ�");
        �ʼ�ϵͳ.add(jLabel349, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 60, -1, 20));

        jLabel350.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel350.setText("0����û�и���");
        �ʼ�ϵͳ.add(jLabel350, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 230, 20));

        jLabel351.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel351.setText("1�����ȯ");
        �ʼ�ϵͳ.add(jLabel351, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, 230, 20));

        jLabel352.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel352.setText("����������");
        �ʼ�ϵͳ.add(jLabel352, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 670, 230, 20));

        jLabel353.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel353.setText("3������");
        �ʼ�ϵͳ.add(jLabel353, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 630, 230, 20));

        jLabel354.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel354.setText("4������");
        �ʼ�ϵͳ.add(jLabel354, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 650, 230, 20));

        ȫ������.setText("ȫ������");
        ȫ������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ������ActionPerformed(evt);
            }
        });
        �ʼ�ϵͳ.add(ȫ������, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 130, 30));

        ���˷���.setText("���˷���");
        ���˷���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���˷���ActionPerformed(evt);
            }
        });
        �ʼ�ϵͳ.add(���˷���, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 130, 30));

        ��ɫ��Ϣ.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ��ɫ��Ϣ.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        ��ɫ��Ϣ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "��ɫID", "��ɫ����", "��ɫ�ȼ�"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ��ɫ��Ϣ.setName(""); // NOI18N
        ��ɫ��Ϣ.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(��ɫ��Ϣ);
        if (��ɫ��Ϣ.getColumnModel().getColumnCount() > 0) {
            ��ɫ��Ϣ.getColumnModel().getColumn(0).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(0).setPreferredWidth(100);
            ��ɫ��Ϣ.getColumnModel().getColumn(1).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(1).setPreferredWidth(200);
            ��ɫ��Ϣ.getColumnModel().getColumn(2).setResizable(false);
            ��ɫ��Ϣ.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        �ʼ�ϵͳ.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 90, 330, 600));

        ȷ��ȫ��.setBackground(new java.awt.Color(255, 255, 255));
        ȷ��ȫ��.setForeground(new java.awt.Color(255, 0, 0));
        ȷ��ȫ��.setText("ȷ��ȫ��");
        ȷ��ȫ��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȷ��ȫ��ActionPerformed(evt);
            }
        });
        �ʼ�ϵͳ.add(ȷ��ȫ��, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 130, 30));

        ˢ�½�ɫ.setText("ˢ�½�ɫ");
        ˢ�½�ɫ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�½�ɫActionPerformed(evt);
            }
        });
        �ʼ�ϵͳ.add(ˢ�½�ɫ, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, 130, 30));

        ���߽�ɫ.setText("���߽�ɫ");
        ���߽�ɫ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���߽�ɫActionPerformed(evt);
            }
        });
        �ʼ�ϵͳ.add(���߽�ɫ, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 120, 130, 30));

        ���߽�ɫ.setText("���߽�ɫ");
        ���߽�ɫ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���߽�ɫActionPerformed(evt);
            }
        });
        �ʼ�ϵͳ.add(���߽�ɫ, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, 130, 30));

        �ռ�������.setEditable(false);
        �ʼ�ϵͳ.add(�ռ�������, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 130, 20));

        �ʼ�����.setText("������д�ʼ��ı���");
        �ʼ�ϵͳ.add(�ʼ�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 410, 30));

        jLabel355.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel355.setText("�ʼ����⣻");
        �ʼ�ϵͳ.add(jLabel355, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 20));

        �������.setText("�������");
        �������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �������ActionPerformed(evt);
            }
        });
        �ʼ�ϵͳ.add(�������, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 130, 30));

        ���߷���.setText("ȫ�����߷���");
        ���߷���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���߷���ActionPerformed(evt);
            }
        });
        �ʼ�ϵͳ.add(���߷���, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 130, 30));

        ���������ʼ�.setText("���������ʼ�");
        ���������ʼ�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���������ʼ�ActionPerformed(evt);
            }
        });
        �ʼ�ϵͳ.add(���������ʼ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, 130, 30));

        jLabel356.setFont(new java.awt.Font("��Բ", 0, 15)); // NOI18N
        jLabel356.setText("�������룻");
        �ʼ�ϵͳ.add(jLabel356, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, -1, 20));

        jTabbedPane4.addTab("�ʼ�ϵͳ", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), �ʼ�ϵͳ); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ������������.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ������������.setText("��������");
        ������������.setToolTipText("");
        ������������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������������ActionPerformed(evt);
            }
        });
        jPanel2.add(������������, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 130, 30));

        ������������.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ������������.setText("��������");
        ������������.setToolTipText("");
        ������������.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������������ActionPerformed(evt);
            }
        });
        jPanel2.add(������������, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 130, 30));

        ����������Ϣ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "*", "�ȼ�", "����"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(����������Ϣ);
        if (����������Ϣ.getColumnModel().getColumnCount() > 0) {
            ����������Ϣ.getColumnModel().getColumn(0).setResizable(false);
            ����������Ϣ.getColumnModel().getColumn(0).setPreferredWidth(1);
            ����������Ϣ.getColumnModel().getColumn(1).setResizable(false);
            ����������Ϣ.getColumnModel().getColumn(1).setPreferredWidth(120);
            ����������Ϣ.getColumnModel().getColumn(2).setResizable(false);
            ����������Ϣ.getColumnModel().getColumn(2).setPreferredWidth(120);
        }

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 320, 570));

        jLabel255.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel255.setText("Ŀǰ���30��");
        jPanel2.add(jLabel255, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 120, 370, 20));

        ˢ�¾����.setText("ˢ�¾����");
        ˢ�¾����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�¾����ActionPerformed(evt);
            }
        });
        jPanel2.add(ˢ�¾����, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 130, 30));

        װ���������.setEditable(false);
        jPanel2.add(װ���������, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 620, 60, -1));
        jPanel2.add(װ����������, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 620, 170, -1));

        װ�������ȼ�.setEditable(false);
        jPanel2.add(װ�������ȼ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 620, 90, -1));

        jLabel256.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel256.setText("װ���������أ�");
        jPanel2.add(jLabel256, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 30));

        jLabel257.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel257.setText("װ�����������������ڷ�����е�����");
        jPanel2.add(jLabel257, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 370, 30));

        ������������1.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ������������1.setText("�޸�");
        ������������1.setToolTipText("");
        ������������1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������������1ActionPerformed(evt);
            }
        });
        jPanel2.add(������������1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 650, 130, 30));

        jLabel258.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel258.setText("װ������һ�������");
        jPanel2.add(jLabel258, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, -1, 30));

        װ����������.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "1", "��Ϣ", "ֵ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(װ����������);
        if (װ����������.getColumnModel().getColumnCount() > 0) {
            װ����������.getColumnModel().getColumn(0).setResizable(false);
            װ����������.getColumnModel().getColumn(0).setPreferredWidth(1);
            װ����������.getColumnModel().getColumn(1).setResizable(false);
            װ����������.getColumnModel().getColumn(1).setPreferredWidth(200);
            װ����������.getColumnModel().getColumn(2).setResizable(false);
            װ����������.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 180, 350, 440));

        ˢ�����ñ�.setText("ˢ�����ñ�");
        ˢ�����ñ�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ�����ñ�ActionPerformed(evt);
            }
        });
        jPanel2.add(ˢ�����ñ�, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 130, 30));

        װ���������1.setEditable(false);
        jPanel2.add(װ���������1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 620, 60, -1));

        װ�������ȼ�1.setEditable(false);
        jPanel2.add(װ�������ȼ�1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 620, 160, -1));
        jPanel2.add(װ����������1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 620, 130, -1));

        ������������2.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ������������2.setText("�޸�");
        ������������2.setToolTipText("");
        ������������2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ������������2ActionPerformed(evt);
            }
        });
        jPanel2.add(������������2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 650, 130, 30));

        jLabel259.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        jLabel259.setText("װ���������辭�飻");
        jPanel2.add(jLabel259, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, 30));

        jLabel260.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel260.setText("·�� *\\Load\\��������������������");
        jPanel2.add(jLabel260, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 70, 370, 30));

        jLabel261.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel261.setText("������������˫�����������������õ�һ��");
        jPanel2.add(jLabel261, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 100, 370, 20));

        jTabbedPane4.addTab("��������װ��", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), jPanel2); // NOI18N

        ���ܷ�Χ���.setBackground(new java.awt.Color(255, 255, 255));
        ���ܷ�Χ���.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ȫ�����ñ�.setFont(new java.awt.Font("��Բ", 0, 20)); // NOI18N
        ȫ�����ñ�.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "���", "����ID", "���ֵ", "������"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(ȫ�����ñ�);
        if (ȫ�����ñ�.getColumnModel().getColumnCount() > 0) {
            ȫ�����ñ�.getColumnModel().getColumn(0).setResizable(false);
            ȫ�����ñ�.getColumnModel().getColumn(1).setResizable(false);
            ȫ�����ñ�.getColumnModel().getColumn(1).setPreferredWidth(100);
            ȫ�����ñ�.getColumnModel().getColumn(2).setResizable(false);
            ȫ�����ñ�.getColumnModel().getColumn(2).setPreferredWidth(100);
            ȫ�����ñ�.getColumnModel().getColumn(3).setResizable(false);
            ȫ�����ñ�.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        ���ܷ�Χ���.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 470, 690));

        ˢ��ȫ���б�.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ˢ��ȫ���б�.setText("ˢ���б�");
        ˢ��ȫ���б�.setToolTipText("");
        ˢ��ȫ���б�.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ˢ��ȫ���б�ActionPerformed(evt);
            }
        });
        ���ܷ�Χ���.add(ˢ��ȫ���б�, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 130, 30));

        ���ܵ��Կ���.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ���ܵ��Կ���.setForeground(new java.awt.Color(255, 0, 0));
        ���ܵ��Կ���.setText("���ܵ���");
        ���ܵ��Կ���.setToolTipText("");
        ���ܵ��Կ���.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ܵ��Կ���ActionPerformed(evt);
            }
        });
        ���ܷ�Χ���.add(���ܵ��Կ���, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 130, 30));

        ���ܼ����.setEditable(false);
        ���ܷ�Χ���.add(���ܼ����, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 510, 60, -1));
        ���ܷ�Χ���.add(���ܼ���ͼ, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 510, 170, -1));
        ���ܷ�Χ���.add(���ܼ����ֵ, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 510, 90, -1));

        ����1.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ����1.setText("����");
        ����1.setToolTipText("");
        ����1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����1ActionPerformed(evt);
            }
        });
        ���ܷ�Χ���.add(����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 540, 80, 30));

        �޸�1.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        �޸�1.setText("�޸�");
        �޸�1.setToolTipText("");
        �޸�1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                �޸�1ActionPerformed(evt);
            }
        });
        ���ܷ�Χ���.add(�޸�1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 540, 80, 30));

        ����1.setText("����");
        ����1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ����1ActionPerformed(evt);
            }
        });
        ���ܷ�Χ���.add(����1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 540, 80, 30));

        jButton2.setText("ɾ��");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        ���ܷ�Χ���.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 540, 80, 30));

        jLabel268.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel268.setText("��ͼ 0 �Ǵ������м���Ĭ�ϵļ��ֵ��");
        ���ܷ�Χ���.add(jLabel268, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 590, 370, 30));

        jLabel269.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel269.setText("ÿ�����ܿ������õ����ļ����ֵ��");
        ���ܷ�Χ���.add(jLabel269, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 620, 680, 30));

        jLabel270.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel270.setText("�����ֵ����������ֵ�ͻᱻϵͳ���С�");
        ���ܷ�Χ���.add(jLabel270, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 650, 630, 30));

        jLabel271.setFont(new java.awt.Font("��Բ", 0, 18)); // NOI18N
        jLabel271.setText("���������޸ļ�ʱ��Ч������������");
        ���ܷ�Χ���.add(jLabel271, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 680, 370, 30));

        ȫ�ּ�⿪��.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ȫ�ּ�⿪��.setForeground(new java.awt.Color(255, 0, 51));
        ȫ�ּ�⿪��.setText("���ܼ��");
        ȫ�ּ�⿪��.setToolTipText("");
        ȫ�ּ�⿪��.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ȫ�ּ�⿪��ActionPerformed(evt);
            }
        });
        ���ܷ�Χ���.add(ȫ�ּ�⿪��, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 130, 30));

        ���ܳͷ�����.setFont(new java.awt.Font("��Բ", 0, 14)); // NOI18N
        ���ܳͷ�����.setForeground(new java.awt.Color(255, 0, 0));
        ���ܳͷ�����.setText("�ͷ�");
        ���ܳͷ�����.setToolTipText("");
        ���ܳͷ�����.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ���ܳͷ�����ActionPerformed(evt);
            }
        });
        ���ܷ�Χ���.add(���ܳͷ�����, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 130, 30));

        jTabbedPane4.addTab("��ɫ���ܼ��", new javax.swing.ImageIcon(getClass().getResource("/provider/WzXML/1.png")), ���ܷ�Χ���); // NOI18N

        getContentPane().add(jTabbedPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1268, 760));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ð�ռ�ְҵ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ð�ռ�ְҵ����ActionPerformed
        ��������("ð�ռ�ְҵ����", 2000);
        ˢ��ð�ռ�ְҵ����();
    }//GEN-LAST:event_ð�ռ�ְҵ����ActionPerformed

    private void ս��ְҵ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ս��ְҵ����ActionPerformed
        ��������("ս��ְҵ����", 2002);
        ˢ��ս��ְҵ����();
    }//GEN-LAST:event_ս��ְҵ����ActionPerformed

    private void ��ʿ��ְҵ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ʿ��ְҵ����ActionPerformed
        ��������("��ʿ��ְҵ����", 2001);
        ˢ����ʿ��ְҵ����();
    }//GEN-LAST:event_��ʿ��ְҵ����ActionPerformed

    private void ��Ϸ�˺�ע��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ϸ�˺�ע��ActionPerformed
        ��������("�˺�ע�Ὺ��", 2004);
        ˢ���˺�ע��();
    }//GEN-LAST:event_��Ϸ�˺�ע��ActionPerformed

    private void ��Ϸ������ѶActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ϸ������ѶActionPerformed
        ��������("������Ѷ����", 2003);
        ˢ��������Ѷ();
    }//GEN-LAST:event_��Ϸ������ѶActionPerformed

    private void ����������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����������ActionPerformed
        ��������("����������", 2006);
        ˢ�¹���������();
    }//GEN-LAST:event_����������ActionPerformed

    private void ������ٿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������ٿ���ActionPerformed
        ��������("������ٿ���", 2007);
        ˢ�¹�����ٿ���();
    }//GEN-LAST:event_������ٿ���ActionPerformed

    private void ��Ϸָ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ϸָ���ActionPerformed
        ��������("��Ϸָ���", 2008);
        ˢ����Ϸָ���();
    }//GEN-LAST:event_��Ϸָ���ActionPerformed

    private void ��Ϸ���ȿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ϸ���ȿ���ActionPerformed
        ��������("��Ϸ���ȿ���", 2009);
        ˢ����Ϸ���ȿ���();
    }//GEN-LAST:event_��Ϸ���ȿ���ActionPerformed

    private void ������ҿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������ҿ���ActionPerformed
        ��������("������ҿ���", 2010);
        ˢ�¶�����ҿ���();
    }//GEN-LAST:event_������ҿ���ActionPerformed

    private void ��ҽ��׿���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ҽ��׿���ActionPerformed
        ��������("��ҽ��׿���", 2011);
        ˢ����ҽ��׿���();
    }//GEN-LAST:event_��ҽ��׿���ActionPerformed

    private void ������Ʒ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������Ʒ����ActionPerformed
        ��������("������Ʒ����", 2012);
        ˢ�¶�����Ʒ����();
    }//GEN-LAST:event_������Ʒ����ActionPerformed

    private void ��ֹ��½����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ֹ��½����ActionPerformed
        ��������("��ֹ��½����", 2013);
        ˢ�½�ֹ��½����();
    }//GEN-LAST:event_��ֹ��½����ActionPerformed

    private void ȫ����������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ����������ActionPerformed
        ��������("ȫ����������", 2014);
        ˢ��ȫ����������();
    }//GEN-LAST:event_ȫ����������ActionPerformed

    private void ��ӭ��������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ӭ��������ActionPerformed
        ��������("��ӭ��������", 2015);
        ˢ�»�ӭ��������();
    }//GEN-LAST:event_��ӭ��������ActionPerformed

    private void ����㲥����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����㲥����ActionPerformed
        ��������("����㲥����", 2016);
        ˢ������㲥����();
    }//GEN-LAST:event_����㲥����ActionPerformed

    private void ����ֿ⿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ֿ⿪��ActionPerformed
        ��������("����ֿ⿪��", 2018);
        ˢ������ֿ⿪��();
    }//GEN-LAST:event_����ֿ⿪��ActionPerformed

    private void ��Ϸ�ֿ⿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ϸ�ֿ⿪��ActionPerformed
        ��������("��Ϸ�ֿ⿪��", 2017);
        ˢ����Ϸ�ֿ⿪��();
    }//GEN-LAST:event_��Ϸ�ֿ⿪��ActionPerformed

    private void �����п���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����п���ActionPerformed
        ��������("�����п���", 2019);
        ˢ�������п���();
    }//GEN-LAST:event_�����п���ActionPerformed

    private void ��Ӷ���˿���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ӷ���˿���ActionPerformed
        ��������("��Ӷ���˿���", 2020);
        ˢ�¹�Ӷ���˿���();
    }//GEN-LAST:event_��Ӷ���˿���ActionPerformed

    private void �������ѿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�������ѿ���ActionPerformed
        ��������("�������ѿ���", 2021);
        ˢ���������ѿ���();
    }//GEN-LAST:event_�������ѿ���ActionPerformed

    private void �۷�㲥����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�۷�㲥����ActionPerformed
        ��������("�۷�㲥����", 2023);
        ˢ���۷�㲥����();
    }//GEN-LAST:event_�۷�㲥����ActionPerformed

    private void ������쿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������쿪��ActionPerformed
        ��������("������쿪��", 2024);
        ˢ��������쿪��();
    }//GEN-LAST:event_������쿪��ActionPerformed

    private void �ű����뿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�ű����뿪��ActionPerformed
        ��������("�ű����뿪��", 2025);
        ˢ�½ű����뿪��();
    }//GEN-LAST:event_�ű����뿪��ActionPerformed

    private void �������濪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�������濪��ActionPerformed
        ��������("�������濪��", 2026);
        ˢ�¹������濪��();
    }//GEN-LAST:event_�������濪��ActionPerformed

    private void ָ��֪ͨ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ָ��֪ͨ����ActionPerformed
        ��������("ָ��֪ͨ����", 2028);
        ˢ��ָ��֪ͨ����();
    }//GEN-LAST:event_ָ��֪ͨ����ActionPerformed

    private void ���յ�ͼ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���յ�ͼ����ActionPerformed
        ��������("���յ�ͼ����", 2029);
        ˢ�»��յ�ͼ����();
    }//GEN-LAST:event_���յ�ͼ����ActionPerformed

    private void ��½��¼����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��½��¼����ActionPerformed
        ��������("��½��¼����", 2030);
        ˢ�µ�½��¼����();
    }//GEN-LAST:event_��½��¼����ActionPerformed

    private void �����¼����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����¼����ActionPerformed
        ��������("�����¼����", 2031);
        ˢ�������¼����();
    }//GEN-LAST:event_�����¼����ActionPerformed

    private void ��½��֤����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��½��֤����ActionPerformed
        ��������("��½��֤����", 2040);
        ˢ�µ�½��֤����();
    }//GEN-LAST:event_��½��֤����ActionPerformed

    private void �̳Ǽ�⿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�̳Ǽ�⿪��ActionPerformed
        ��������("�̳Ǽ�⿪��", 2042);
        ˢ���̳Ǽ�⿪��();
    }//GEN-LAST:event_�̳Ǽ�⿪��ActionPerformed

    private void ����ţ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ţ����ActionPerformed
        ��������("����ţ����", 2200);
        ˢ������ţ����();
    }//GEN-LAST:event_����ţ����ActionPerformed

    private void Ģ���п���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ģ���п���ActionPerformed
        ��������("Ģ���п���", 2201);
        ˢ��Ģ���п���();
    }//GEN-LAST:event_Ģ���п���ActionPerformed

    private void ��ˮ�鿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ˮ�鿪��ActionPerformed
        ��������("��ˮ�鿪��", 2202);
        ˢ����ˮ�鿪��();
    }//GEN-LAST:event_��ˮ�鿪��ActionPerformed

    private void ƯƯ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ƯƯ����ActionPerformed
        ��������("ƯƯ����", 2203);
        ˢ��ƯƯ����();
    }//GEN-LAST:event_ƯƯ����ActionPerformed

    private void С���߿���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_С���߿���ActionPerformed
        ��������("С���߿���", 2204);
        ˢ��С���߿���();
    }//GEN-LAST:event_С���߿���ActionPerformed

    private void ���з����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���з����ActionPerformed
        ��������("���з����", 2205);
        ˢ�º��з����();
    }//GEN-LAST:event_���з����ActionPerformed

    private void �󺣹꿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�󺣹꿪��ActionPerformed
        ��������("�󺣹꿪��", 2206);
        ˢ�´󺣹꿪��();
    }//GEN-LAST:event_�󺣹꿪��ActionPerformed

    private void ����ֿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ֿ���ActionPerformed
        ��������("����ֿ���", 2207);
        ˢ������ֿ���();
    }//GEN-LAST:event_����ֿ���ActionPerformed

    private void ��Ƥ�￪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ƥ�￪��ActionPerformed
        ��������("��Ƥ�￪��", 2208);
        ˢ����Ƥ�￪��();
    }//GEN-LAST:event_��Ƥ�￪��ActionPerformed

    private void �Ǿ��鿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�Ǿ��鿪��ActionPerformed
        ��������("�Ǿ��鿪��", 2209);
        ˢ���Ǿ��鿪��();
    }//GEN-LAST:event_�Ǿ��鿪��ActionPerformed

    private void ����쿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����쿪��ActionPerformed
        ��������("����쿪��", 2210);
        ˢ������쿪��();
    }//GEN-LAST:event_����쿪��ActionPerformed

    private void ��ѩ�˿���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ѩ�˿���ActionPerformed
        ��������("��ѩ�˿���", 2211);
        ˢ�°�ѩ�˿���();
    }//GEN-LAST:event_��ѩ�˿���ActionPerformed

    private void ʯͷ�˿���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ʯͷ�˿���ActionPerformed
        ��������("ʯͷ�˿���", 2212);
        ˢ��ʯͷ�˿���();
    }//GEN-LAST:event_ʯͷ�˿���ActionPerformed

    private void ��ɫè����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ɫè����ActionPerformed
        ��������("��ɫè����", 2213);
        ˢ����ɫè����();
    }//GEN-LAST:event_��ɫè����ActionPerformed

    private void ����ǿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ǿ���ActionPerformed
        ��������("����ǿ���", 2214);
        ˢ�´���ǿ���();
    }//GEN-LAST:event_����ǿ���ActionPerformed

    private void ���������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���������ActionPerformed
        ��������("���������", 2216);
        ˢ�����������();
    }//GEN-LAST:event_���������ActionPerformed

    private void ��Ұ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ұ����ActionPerformed
        ��������("��Ұ����", 2217);
        ˢ�»�Ұ����();
    }//GEN-LAST:event_��Ұ����ActionPerformed

    private void С���ÿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_С���ÿ���ActionPerformed
        ��������("С���ÿ���", 2215);
        ˢ��С���ÿ���();
    }//GEN-LAST:event_С���ÿ���ActionPerformed

    private void �����㿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����㿪��ActionPerformed
        ��������("�����㿪��", 2218);
        ˢ�������㿪��();
    }//GEN-LAST:event_�����㿪��ActionPerformed

    private void ��Ģ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ģ������ActionPerformed
        ��������("��Ģ������", 2219);
        ˢ�»�Ģ������();
    }//GEN-LAST:event_��Ģ������ActionPerformed

    private void �޸���ʿ�ŵȼ�����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸���ʿ�ŵȼ�����ActionPerformed
        if (��ʿ�ŵȼ�����.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "����Ϊ��");
            return;
        }
        boolean result2 = this.��ʿ�ŵȼ�����.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result2) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 2301);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.��ʿ�ŵȼ�����.getText() + "' where id = 2301;";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    gui.Start.GetConfigValues();
                    ˢ����ʿ�ŵȼ�����();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_�޸���ʿ�ŵȼ�����ActionPerformed

    private void �޸�ð�ռҵȼ�����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸�ð�ռҵȼ�����ActionPerformed

        if (ð�ռҵȼ�����.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "����Ϊ��");
            return;
        }
        boolean result2 = this.ð�ռҵȼ�����.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result2) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 2300);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.ð�ռҵȼ�����.getText() + "' where id = 2300;";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    gui.Start.GetConfigValues();
                    ˢ��ð�ռҵȼ�����();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_�޸�ð�ռҵȼ�����ActionPerformed

    private void �޸��˺�����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸��˺�����ActionPerformed
        if (����ע���˺�����.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "����Ϊ��");
            return;
        }
        boolean result2 = this.����ע���˺�����.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result2) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 1050);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.����ע���˺�����.getText() + "' where id = 1050;";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    gui.Start.GetConfigValues();
                    ˢ��ע���˺�����();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_�޸��˺�����ActionPerformed

    private void �޸Ľ�ɫ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸Ľ�ɫ����ActionPerformed
        if (���ƴ�����ɫ����.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "����Ϊ��");
            return;
        }
        boolean result2 = this.���ƴ�����ɫ����.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result2) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 1051);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.���ƴ�����ɫ����.getText() + "' where id = 1051;";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    gui.Start.GetConfigValues();
                    ˢ��ע��ɫ����();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_�޸Ľ�ɫ����ActionPerformed

    private void �޸ķ�����������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸ķ�����������ActionPerformed
        boolean result1 = this.������������.getText().matches("[0-9]+");
        if (������������.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "����Ϊ��");
            return;
        }
        boolean result2 = this.������������.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result2) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 2302);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.������������.getText() + "' where id = 2302;";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    gui.Start.GetConfigValues();
                    ˢ�·�����������();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_�޸ķ�����������ActionPerformed

    private void ����ӳɱ��޸�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ӳɱ��޸�ActionPerformed

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.����ӳɱ����.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.����ӳɱ����.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update configvalues set Val = '" + this.����ӳɱ���ֵ.getText() + "' where id= " + this.����ӳɱ����.getText() + ";";
                    PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    Val.executeUpdate(sqlString1);
                    ˢ�¾���ӳɱ�();
                    gui.Start.GetConfigValues();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ��Ѿ���Ч");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�޸ĵ�ֵ");
        }
    }//GEN-LAST:event_����ӳɱ��޸�ActionPerformed

    private void ��Ϸ����ӳ�˵��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ϸ����ӳ�˵��ActionPerformed
        JOptionPane.showMessageDialog(null, "<���˵����>\r\n\r\n"
                + "1:���Ӧ��ֵΪ0��Ϊ�رվ���ӳɡ�\r\n"
                + "2:�������� = ���� * ��������ӳ���ֵ��\r\n"
                + "\r\n");
    }//GEN-LAST:event_��Ϸ����ӳ�˵��ActionPerformed

    private void ��ѩ�쿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ѩ�쿪��ActionPerformed
        int ��ѩ�� = gui.Start.ConfigValuesMap.get("��ѩ�쿪��");
        int �º컨 = gui.Start.ConfigValuesMap.get("�º컨����");
        int �·�Ҷ = gui.Start.ConfigValuesMap.get("�·�Ҷ����");
        int ������ = gui.Start.ConfigValuesMap.get("�����ݿ���");
        int ��ѩ�� = gui.Start.ConfigValuesMap.get("��ѩ������");

        int ��ѩ�쿪�� = gui.Start.ConfigValuesMap.get("��ѩ�쿪��");
        if (��ѩ�쿪�� <= 0) {
            ��������("��ѩ�쿪��", 1001);
            ˢ����ѩ�쿪��();
            /*PreparedStatement ps1 = null;
            ResultSet rs = null;
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 1);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from configvalues where id =1001";
                    ps1.executeUpdate(sqlstr);
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
                ps.setInt(1, 1001);
                ps.setString(2, "��ѩ�쿪��");
                ps.setInt(3, 1);
                ps.executeUpdate();
                gui.Start.GetConfigValues();
                ˢ����ѩ�쿪��();
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        } else {
            if (�º컨 == 0 || �·�Ҷ == 0 || ������ == 0 || ��ѩ�� == 0) {
                JOptionPane.showMessageDialog(null, "��������������Ҫ��֤�����������ڹر�״̬��");
                return;
            }
            ��������("��ѩ�쿪��", 1001);
            ˢ����ѩ�쿪��();
            /*PreparedStatement ps1 = null;
            ResultSet rs = null;
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 1);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from configvalues where id =1001";
                    ps1.executeUpdate(sqlstr);
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
                ps.setInt(1, 1001);
                ps.setString(2, "��ѩ�쿪��");
                ps.setInt(3, 0);
                ps.executeUpdate();
                gui.Start.GetConfigValues();
                ˢ����ѩ�쿪��();
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
    }//GEN-LAST:event_��ѩ�쿪��ActionPerformed

    private void �����ݿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����ݿ���ActionPerformed
        int ��ѩ�� = gui.Start.ConfigValuesMap.get("��ѩ�쿪��");
        int �º컨 = gui.Start.ConfigValuesMap.get("�º컨����");
        int �·�Ҷ = gui.Start.ConfigValuesMap.get("�·�Ҷ����");
        int ������ = gui.Start.ConfigValuesMap.get("�����ݿ���");
        int ��ѩ�� = gui.Start.ConfigValuesMap.get("��ѩ������");
        int �����ݿ��� = gui.Start.ConfigValuesMap.get("�����ݿ���");
        if (�����ݿ��� <= 0) {
            ��������("�����ݿ���", 1003);
            ˢ�������ݿ���();
            /* PreparedStatement ps1 = null;
            ResultSet rs = null;
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 1);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from configvalues where id =1003";
                    ps1.executeUpdate(sqlstr);
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
                ps.setInt(1, 1003);
                ps.setString(2, "�����ݿ���");
                ps.setInt(3, 1);
                ps.executeUpdate();
                gui.Start.GetConfigValues();
                ˢ�������ݿ���();
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        } else {
            if (�º컨 == 0 || �·�Ҷ == 0 || ��ѩ�� == 0 || ��ѩ�� == 0) {
                JOptionPane.showMessageDialog(null, "��������������Ҫ��֤�����������ڹر�״̬��");
                return;
            }
            ��������("�����ݿ���", 1003);
            ˢ�������ݿ���();
            /*PreparedStatement ps1 = null;
            ResultSet rs = null;
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 1);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from configvalues where id =1003";
                    ps1.executeUpdate(sqlstr);
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
                ps.setInt(1, 1003);
                ps.setString(2, "�����ݿ���");
                ps.setInt(3, 0);
                ps.executeUpdate();
                gui.Start.GetConfigValues();
                ˢ�������ݿ���();
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
    }//GEN-LAST:event_�����ݿ���ActionPerformed

    private void �º컨����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�º컨����ActionPerformed
        int ��ѩ�� = gui.Start.ConfigValuesMap.get("��ѩ�쿪��");
        int �º컨 = gui.Start.ConfigValuesMap.get("�º컨����");
        int �·�Ҷ = gui.Start.ConfigValuesMap.get("�·�Ҷ����");
        int ������ = gui.Start.ConfigValuesMap.get("�����ݿ���");
        int ��ѩ�� = gui.Start.ConfigValuesMap.get("��ѩ������");
        int �º컨���� = gui.Start.ConfigValuesMap.get("�º컨����");
        if (�º컨���� <= 0) {
            ��������("�º컨����", 1002);
            ˢ���º컨����();
//            PreparedStatement ps1 = null;
//            ResultSet rs = null;
//            try {
//                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
//                ps1.setInt(1, 1);
//                rs = ps1.executeQuery();
//                if (rs.next()) {
//                    String sqlstr = " delete from configvalues where id =1002";
//                    ps1.executeUpdate(sqlstr);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
//                ps.setInt(1, 1002);
//                ps.setString(2, "�º컨����");
//                ps.setInt(3, 1);
//                ps.executeUpdate();
//                gui.Start.GetConfigValues();
//                ˢ���º컨����();
//            } catch (SQLException ex) {
//                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } else {
            if (��ѩ�� == 0 || �·�Ҷ == 0 || ������ == 0 || ��ѩ�� == 0) {
                JOptionPane.showMessageDialog(null, "��������������Ҫ��֤�����������ڹر�״̬��");
                return;
            }
            ��������("�º컨����", 1002);
            ˢ���º컨����();
//            PreparedStatement ps1 = null;
//            ResultSet rs = null;
//            try {
//                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
//                ps1.setInt(1, 1);
//                rs = ps1.executeQuery();
//                if (rs.next()) {
//                    String sqlstr = " delete from configvalues where id =1002";
//                    ps1.executeUpdate(sqlstr);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
//                ps.setInt(1, 1002);
//                ps.setString(2, "�º컨����");
//                ps.setInt(3, 0);
//                ps.executeUpdate();
//                gui.Start.GetConfigValues();
//                ˢ���º컨����();
//            } catch (SQLException ex) {
//                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }//GEN-LAST:event_�º컨����ActionPerformed

    private void ��ѩ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ѩ������ActionPerformed
        int ��ѩ�� = gui.Start.ConfigValuesMap.get("��ѩ�쿪��");
        int �º컨 = gui.Start.ConfigValuesMap.get("�º컨����");
        int �·�Ҷ = gui.Start.ConfigValuesMap.get("�·�Ҷ����");
        int ������ = gui.Start.ConfigValuesMap.get("�����ݿ���");
        int ��ѩ�� = gui.Start.ConfigValuesMap.get("��ѩ������");
        int ��ѩ������ = gui.Start.ConfigValuesMap.get("��ѩ������");
        if (��ѩ������ <= 0) {
            ��������("��ѩ������", 1004);
            ˢ����ѩ������();
//            PreparedStatement ps1 = null;
//            ResultSet rs = null;
//            try {
//                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
//                ps1.setInt(1, 1);
//                rs = ps1.executeQuery();
//                if (rs.next()) {
//                    String sqlstr = " delete from configvalues where id =1004";
//                    ps1.executeUpdate(sqlstr);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
//                ps.setInt(1, 1004);
//                ps.setString(2, "��ѩ������");
//                ps.setInt(3, 1);
//                ps.executeUpdate();
//                gui.Start.GetConfigValues();
//                ˢ����ѩ������();
//            } catch (SQLException ex) {
//                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } else {
            if (�º컨 == 0 || �·�Ҷ == 0 || ������ == 0 || ��ѩ�� == 0) {
                JOptionPane.showMessageDialog(null, "��������������Ҫ��֤�����������ڹر�״̬��");
                return;
            }
            ��������("��ѩ������", 1004);
            ˢ����ѩ������();
//            PreparedStatement ps1 = null;
//            ResultSet rs = null;
//            try {
//                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
//                ps1.setInt(1, 1);
//                rs = ps1.executeQuery();
//                if (rs.next()) {
//                    String sqlstr = " delete from configvalues where id =1004";
//                    ps1.executeUpdate(sqlstr);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
//                ps.setInt(1, 1004);
//                ps.setString(2, "��ѩ������");
//                ps.setInt(3, 0);
//                ps.executeUpdate();
//                gui.Start.GetConfigValues();
//                ˢ����ѩ������();
//            } catch (SQLException ex) {
//                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }//GEN-LAST:event_��ѩ������ActionPerformed

    private void �·�Ҷ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�·�Ҷ����ActionPerformed
        int ��ѩ�� = gui.Start.ConfigValuesMap.get("��ѩ�쿪��");
        int �º컨 = gui.Start.ConfigValuesMap.get("�º컨����");
        int �·�Ҷ = gui.Start.ConfigValuesMap.get("�·�Ҷ����");
        int ������ = gui.Start.ConfigValuesMap.get("�����ݿ���");
        int ��ѩ�� = gui.Start.ConfigValuesMap.get("��ѩ������");
        int �·�Ҷ���� = gui.Start.ConfigValuesMap.get("�·�Ҷ����");
        if (�·�Ҷ���� <= 0) {
            ��������("�·�Ҷ����", 1005);
            ˢ���·�Ҷ����();
            /* PreparedStatement ps1 = null;
            ResultSet rs = null;
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 1);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from configvalues where id =1005";
                    ps1.executeUpdate(sqlstr);
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
                ps.setInt(1, 1005);
                ps.setString(2, "�·�Ҷ����");
                ps.setInt(3, 1);
                ps.executeUpdate();
                gui.Start.GetConfigValues();
                ˢ���·�Ҷ����();
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        } else {
            if (�º컨 == 0 || ��ѩ�� == 0 || ������ == 0 || ��ѩ�� == 0) {
                JOptionPane.showMessageDialog(null, "��������������Ҫ��֤�����������ڹر�״̬��");
                return;
            }
            ��������("�·�Ҷ����", 1005);
            ˢ���·�Ҷ����();
//            PreparedStatement ps1 = null;
//            ResultSet rs = null;
//            try {
//                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
//                ps1.setInt(1, 1);
//                rs = ps1.executeQuery();
//                if (rs.next()) {
//                    String sqlstr = " delete from configvalues where id =1005";
//                    ps1.executeUpdate(sqlstr);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
//                ps.setInt(1, 1005);
//                ps.setString(2, "�·�Ҷ����");
//                ps.setInt(3, 0);
//                ps.executeUpdate();
//                gui.Start.GetConfigValues();
//                ˢ���·�Ҷ����();
//            } catch (SQLException ex) {
//                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }//GEN-LAST:event_�·�Ҷ����ActionPerformed

    private void ˢ�������NPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�������NPCActionPerformed
        ˢ�������NPC();        // TODO add your handling code here:
    }//GEN-LAST:event_ˢ�������NPCActionPerformed

    private void ɾ�������npcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ�������npcActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.ɾ�������npc����.getText().matches("[0-9]+");

        if (result1) {
            if (Integer.parseInt(this.ɾ�������npc����.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "����д��ȷ��ֵ");
            }
            try {

                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM ��Ϸnpc���� WHERE dataid = ?");
                ps1.setInt(1, Integer.parseInt(this.ɾ�������npc����.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from ��Ϸnpc���� where dataid =" + Integer.parseInt(this.ɾ�������npc����.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "�ɹ�ɾ�� " + Integer.parseInt(this.ɾ�������npc����.getText()) + " npc.������Ч��");
                    ˢ�������NPC();

                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "���������� ");
        }
    }//GEN-LAST:event_ɾ�������npcActionPerformed

    private void �����޸�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����޸�ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.�������.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.�������.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update configvalues set Val = '" + this.������ֵ.getText() + "' where id= " + this.�������.getText() + ";";
                    PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    Val.executeUpdate(sqlString1);
                    ˢ�½���();
                    gui.Start.GetConfigValues();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ��Ѿ���Ч");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�޸ĵ�ֵ");
        }
    }//GEN-LAST:event_�����޸�ActionPerformed

    private void ������������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������������ActionPerformed
        ��������("������������", 601);
        ˢ�½�������();
    }//GEN-LAST:event_������������ActionPerformed

    private void ˢ�·�IPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�·�IPActionPerformed
        ˢ�·�IP();
    }//GEN-LAST:event_ˢ�·�IPActionPerformed

    private void ˢ�·�MACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�·�MACActionPerformed
        ˢ�·�MAC();
    }//GEN-LAST:event_ˢ�·�MACActionPerformed

    private void ɾ��MACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ��MACActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.ɾMAC����.getText().matches("[0-9]+");

        if (result1) {
            if (Integer.parseInt(this.ɾMAC����.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "����д��ȷ��ֵ");
            }
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM macbans WHERE macbanid = ?");
                ps1.setInt(1, Integer.parseInt(this.ɾMAC����.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from macbans where macbanid =" + Integer.parseInt(this.ɾMAC����.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ˢ�·�MAC();
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "���������� ");
        }
    }//GEN-LAST:event_ɾ��MACActionPerformed

    private void ɾ��IPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ��IPActionPerformed
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.ɾ��IP����.getText().matches("[0-9]+");

        if (result1) {
            if (Integer.parseInt(this.ɾ��IP����.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "����д��ȷ��ֵ");
            }
            try {

                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM ipbans WHERE ipbanid = ?");
                ps1.setInt(1, Integer.parseInt(this.ɾ��IP����.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from ipbans where ipbanid =" + Integer.parseInt(this.ɾ��IP����.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ˢ�·�IP();
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "���������� ");
        }
    }//GEN-LAST:event_ɾ��IPActionPerformed

    private void �����࿪����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����࿪����ActionPerformed
        ��������("�����࿪����", 2053);
        ˢ�»����࿪();
    }//GEN-LAST:event_�����࿪����ActionPerformed

    private void IP�࿪����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IP�࿪����ActionPerformed
        ��������("IP�࿪����", 2054);
        ˢ��IP�࿪();

    }//GEN-LAST:event_IP�࿪����ActionPerformed

    private void ��½��������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��½��������ActionPerformed
        ��������("��½��������", 2055);
        ˢ�µ�½����();
    }//GEN-LAST:event_��½��������ActionPerformed

    private void ��½���п���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��½���п���ActionPerformed
        ��������("��½���п���", 2056);
        ˢ�µ�½����();
    }//GEN-LAST:event_��½���п���ActionPerformed

    private void �������ؿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�������ؿ���ActionPerformed
        ��������("�������ؿ���", 2057);
        ˢ����������();
    }//GEN-LAST:event_�������ؿ���ActionPerformed

    private void �����˿���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����˿���ActionPerformed
        ��������("QQ�����˿���", 2223);
        ˢ�»�����();
    }//GEN-LAST:event_�����˿���ActionPerformed

    private void ��½��������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��½��������ActionPerformed
        ��������("��½��������", 2058);
        ˢ�µ�½����();
    }//GEN-LAST:event_��½��������ActionPerformed

    private void ˢ�¹��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�¹��ActionPerformed
        ˢ�¹���㲥();
    }//GEN-LAST:event_ˢ�¹��ActionPerformed

    private void �������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�������ActionPerformed
        if (�㲥�ı�.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "����д�����ϢŶ��");
            return;
        }
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO �㲥��Ϣ ( �㲥 ) VALUES ( ? )")) {
            ps.setString(1, �㲥�ı�.getText());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
        }
        ˢ�¹���㲥();
        JOptionPane.showMessageDialog(null, "������ɡ�");
    }//GEN-LAST:event_�������ActionPerformed

    private void ɾ���㲥ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ɾ���㲥ActionPerformed

        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.�㲥���.getText().matches("[0-9]+");

        if (result1) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM �㲥��Ϣ WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.�㲥���.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from �㲥��Ϣ where id =" + Integer.parseInt(this.�㲥���.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    ˢ�¹���㲥();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ɾ���㲥ActionPerformed

    private void �޸Ĺ㲥ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸Ĺ㲥ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE �㲥��Ϣ SET �㲥 = ? WHERE id = ?");
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM �㲥��Ϣ  WHERE id = ? ");
            ps1.setInt(1, Integer.parseInt(�㲥���.getText()));
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                sqlString1 = "update �㲥��Ϣ set �㲥 = '" + �㲥�ı�.getText() + "' where id = " + Integer.parseInt(�㲥���.getText()) + ";";
                PreparedStatement a1 = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                a1.executeUpdate(sqlString1);
                ˢ�¹���㲥();
                JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
            }
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_�޸Ĺ㲥ActionPerformed

    private void ˫��Ƶ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_˫��Ƶ������ActionPerformed
        ��������("˫��Ƶ������", 2059);
        ˢ��˫��Ƶ������();
        JOptionPane.showMessageDialog(null, "������Ч��");
    }//GEN-LAST:event_˫��Ƶ������ActionPerformed

    private void ������ע�Ὺ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������ע�Ὺ��ActionPerformed
        ��������("������ע�Ὺ��", 2060);
        ˢ�»�����ע�Ὺ��();
    }//GEN-LAST:event_������ע�Ὺ��ActionPerformed

    private void ����״̬����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����״̬����ActionPerformed
        ��������("����״̬����", 2061);
        ˢ�¹���״̬����();
    }//GEN-LAST:event_����״̬����ActionPerformed

    private void Խ����ֿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Խ����ֿ���ActionPerformed
        ��������("Խ����ֿ���", 2125);
        ˢ��Խ����ֿ���();
    }//GEN-LAST:event_Խ����ֿ���ActionPerformed

    private void ��Ϸ���˿���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��Ϸ���˿���ActionPerformed
        ��������("��Ϸ���˿���", 2127);
        ˢ����Ϸ���˿���();
    }//GEN-LAST:event_��Ϸ���˿���ActionPerformed

    private void ˢ�½�ɫActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�½�ɫActionPerformed
        ˢ�½�ɫ��Ϣ();
    }//GEN-LAST:event_ˢ�½�ɫActionPerformed

    private void ���߽�ɫActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���߽�ɫActionPerformed
        ˢ�����߽�ɫ��Ϣ();
    }//GEN-LAST:event_���߽�ɫActionPerformed

    private void ���߽�ɫActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���߽�ɫActionPerformed
        ˢ�����߽�ɫ��Ϣ();
    }//GEN-LAST:event_���߽�ɫActionPerformed

    private void ���˷���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���˷���ActionPerformed
        ���˷����ʼ�();
    }//GEN-LAST:event_���˷���ActionPerformed

    private void ȫ������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ������ActionPerformed
        ȫ�������ʼ�();
    }//GEN-LAST:event_ȫ������ActionPerformed

    private void ����3����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����3����ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_����3����ActionPerformed

    private void ȷ��ȫ��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȷ��ȫ��ActionPerformed
        ȫ�������ʼ�1();
    }//GEN-LAST:event_ȷ��ȫ��ActionPerformed

    private void ���߷���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���߷���ActionPerformed
        ȫ�����߷����ʼ�();
    }//GEN-LAST:event_���߷���ActionPerformed

    private void �������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�������ActionPerformed
        ȫ����������ʼ�();
    }//GEN-LAST:event_�������ActionPerformed

    private void ���������ʼ�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���������ʼ�ActionPerformed
        JOptionPane.showMessageDialog(null, "δ���á�");
    }//GEN-LAST:event_���������ʼ�ActionPerformed

    private void ������������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������������ActionPerformed
        ��������("������������", 2129);
        ˢ��������������();
    }//GEN-LAST:event_������������ActionPerformed

    private void ������������ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������������ActionPerformed
        ��������("������������", 2128);
        ˢ��������������();
    }//GEN-LAST:event_������������ActionPerformed
    public void ˢ��װ�����������() {
        for (int i = ((DefaultTableModel) (this.����������Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.����������Ϣ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM configvalues WHERE id > 100000 && id < 200000  ");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ����������Ϣ.getModel()).insertRow(����������Ϣ.getRowCount(), new Object[]{
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("Val")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        ����������Ϣ.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ����������Ϣ.getSelectedRow();
                String a = ����������Ϣ.getValueAt(i, 0).toString();
                String a1 = ����������Ϣ.getValueAt(i, 1).toString();
                String a2 = ����������Ϣ.getValueAt(i, 2).toString();
                װ���������.setText(a);
                װ�������ȼ�.setText(a1);
                װ����������.setText(a2);
            }
        });
    }

    public void ˢ��װ���������ñ�() {
        for (int i = ((DefaultTableModel) (this.װ����������.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.װ����������.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM configvalues WHERE id >= 200000 && id <300000  ");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) װ����������.getModel()).insertRow(װ����������.getRowCount(), new Object[]{
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("Val")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        װ����������.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = װ����������.getSelectedRow();
                String a = װ����������.getValueAt(i, 0).toString();
                String a1 = װ����������.getValueAt(i, 1).toString();
                String a2 = װ����������.getValueAt(i, 2).toString();
                װ���������1.setText(a);
                װ�������ȼ�1.setText(a1);
                װ����������1.setText(a2);
            }
        });
    }
    private void ˢ�¾����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�¾����ActionPerformed
        ˢ��װ�����������();
    }//GEN-LAST:event_ˢ�¾����ActionPerformed

    private void ������������1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������������1ActionPerformed

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.װ���������1.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.װ���������1.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update configvalues set Val = '" + this.װ����������1.getText() + "' where id= " + this.װ���������1.getText() + ";";
                    PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    Val.executeUpdate(sqlString1);
                    ˢ��װ���������ñ�();
                    gui.Start.GetConfigValues();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ��Ѿ���Ч");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�޸ĵ�ֵ");
        }
    }//GEN-LAST:event_������������1ActionPerformed

    private void ˢ�����ñ�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ�����ñ�ActionPerformed
        ˢ��װ���������ñ�();
    }//GEN-LAST:event_ˢ�����ñ�ActionPerformed

    private void ������������2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������������2ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.װ���������.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.װ���������.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update configvalues set Val = '" + this.װ����������.getText() + "' where id= " + this.װ���������.getText() + ";";
                    PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    Val.executeUpdate(sqlString1);
                    ˢ��װ�����������();
                    gui.Start.GetConfigValues();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ��Ѿ���Ч");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�޸ĵ�ֵ");
        }
    }//GEN-LAST:event_������������2ActionPerformed
    public void ˢ�¼���ֵ�б�() {
        for (int i = ((DefaultTableModel) (this.ȫ�����ñ�.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.ȫ�����ñ�.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM ���ܷ�Χ��� ");
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
                ((DefaultTableModel) ȫ�����ñ�.getModel()).insertRow(ȫ�����ñ�.getRowCount(), new Object[]{
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("Val"),
                    itemName
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        ȫ�����ñ�.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ȫ�����ñ�.getSelectedRow();
                String a = ȫ�����ñ�.getValueAt(i, 0).toString();
                String a1 = ȫ�����ñ�.getValueAt(i, 1).toString();
                String a2 = ȫ�����ñ�.getValueAt(i, 2).toString();
                ���ܼ����.setText(a);
                ���ܼ���ͼ.setText(a1);
                ���ܼ����ֵ.setText(a2);
            }
        });
        ��ȡ���ܷ�Χ���();
        // ��ȡ��ͼ���ּ��();
    }
    private void ��ͼ���ƿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ͼ���ƿ���ActionPerformed
        ��������("��ͼ���ƿ���", 2136);
        ˢ�µ�ͼ���ƿ���();
    }//GEN-LAST:event_��ͼ���ƿ���ActionPerformed

    private void ������⿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_������⿪��ActionPerformed
        ��������("������⿪��", 2138);
        ˢ�¶�����⿪��();

    }//GEN-LAST:event_������⿪��ActionPerformed

    private void Ⱥ����⿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ⱥ����⿪��ActionPerformed
        ��������("Ⱥ����⿪��", 2139);
        ˢ��Ⱥ����⿪��();
    }//GEN-LAST:event_Ⱥ����⿪��ActionPerformed

    private void �޸���ʿ�ŵȼ�����1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸���ʿ�ŵȼ�����1ActionPerformed
        if (��Ӷ����ʱ��.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "����Ϊ��");
            return;
        }
        boolean result2 = this.��Ӷ����ʱ��.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result2) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 995);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.��Ӷ����ʱ��.getText() + "' where id = 995;";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    gui.Start.GetConfigValues();
                    ˢ�¹�Ӷ����ʱ��();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_�޸���ʿ�ŵȼ�����1ActionPerformed

    private void ��ͼ�浵����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ͼ�浵����ActionPerformed
        ��������("��ͼ�浵����", 2140);
        ˢ�¹�ͼ�浵ʱ��();
    }//GEN-LAST:event_��ͼ�浵����ActionPerformed

    private void �޸�ð�ռҵȼ�����1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸�ð�ռҵȼ�����1ActionPerformed
        if (������࿪����.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "����Ϊ��");
            return;
        }
        boolean result2 = this.������࿪����.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result2) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 2301);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.������࿪����.getText() + "' where id = 2064;";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    gui.Start.GetConfigValues();
                    ˢ�¶࿪����();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_�޸�ð�ռҵȼ�����1ActionPerformed

    private void �޸���ʿ�ŵȼ�����2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸���ʿ�ŵȼ�����2ActionPerformed
        if (IP�࿪����.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "����Ϊ��");
            return;
        }
        boolean result2 = this.IP�࿪����.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result2) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 2301);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.IP�࿪����.getText() + "' where id = 2063;";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    gui.Start.GetConfigValues();
                    ˢ�¶࿪����();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_�޸���ʿ�ŵȼ�����2ActionPerformed

    private void �һ���⿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�һ���⿪��ActionPerformed
        ��������("�һ���⿪��", 2141);
        ˢ�¹һ���⿪��();
    }//GEN-LAST:event_�һ���⿪��ActionPerformed

    private void �޸���ʿ�ŵȼ�����3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸���ʿ�ŵȼ�����3ActionPerformed
        if (ƣ��ֵ.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "����Ϊ��");
            return;
        }
        boolean result2 = this.ƣ��ֵ.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result2) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 2143);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.ƣ��ֵ.getText() + "' where id = 2143;";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    gui.Start.GetConfigValues();
                    ˢ��ÿ��ƣ��ֵ();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_�޸���ʿ�ŵȼ�����3ActionPerformed

    private void ��ħ���ѿ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_��ħ���ѿ���ActionPerformed
        ��������("��ħ���ѿ���", 2144);
        ˢ�¸�ħ���ѿ���();
    }//GEN-LAST:event_��ħ���ѿ���ActionPerformed

    private void ����ģʽ����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����ģʽ����ActionPerformed
        ��������("����ģʽ����", 2145);
        ˢ������ģʽ����();
    }//GEN-LAST:event_����ģʽ����ActionPerformed

    private void ȫ����⿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ����⿪��ActionPerformed
        ��������("ȫ����⿪��", 2131);
        ˢ��ȫ����⿪��();
    }//GEN-LAST:event_ȫ����⿪��ActionPerformed

    private void ���ּ�⿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ּ�⿪��ActionPerformed
        ��������("���ּ�⿪��", 2130);
        ˢ�����ּ�⿪��();
    }//GEN-LAST:event_���ּ�⿪��ActionPerformed

    private void ���ټ�⿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ټ�⿪��ActionPerformed
        ��������("���ټ�⿪��", 2146);
        ˢ�¼��ټ�⿪��();
    }//GEN-LAST:event_���ټ�⿪��ActionPerformed

    private void ���ܳͷ�����ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ܳͷ�����ActionPerformed
        ��������("���ܳͷ�����", 2133);
        ˢ�¼��ܳͷ�����();
        ��ȡ���ܷ�Χ���();
    }//GEN-LAST:event_���ܳͷ�����ActionPerformed

    private void ȫ�ּ�⿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ȫ�ּ�⿪��ActionPerformed

    }//GEN-LAST:event_ȫ�ּ�⿪��ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    private void ����1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_����1ActionPerformed
        if (���ܼ���ͼ.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "������ϢŶ��");
            return;
        }
        int ID = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT `id` FROM ���ܷ�Χ���  ORDER BY `id` DESC LIMIT 1");
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
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO ���ܷ�Χ��� ( id,name,Val ) VALUES ( ? ,?,?)")) {
            ps.setInt(1, ID);
            ps.setString(2, ���ܼ���ͼ.getText());
            ps.setInt(3, Integer.parseInt(���ܼ����ֵ.getText()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
        }
        ˢ�¼���ֵ�б�();
    }//GEN-LAST:event_����1ActionPerformed

    private void �޸�1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸�1ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result1 = this.���ܼ����.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE ���ܷ�Χ��� SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM ���ܷ�Χ��� WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.���ܼ����.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update ���ܷ�Χ��� set Val = '" + this.���ܼ����ֵ.getText() + "' where id= " + this.���ܼ����.getText() + ";";
                    PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    Val.executeUpdate(sqlString1);
                    ˢ�¼���ֵ�б�();
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
        boolean result1 = this.���ܼ���ͼ.getText().matches("[0-9]+");

        if (!result1) {
            return;
        }
        for (int i = ((DefaultTableModel) (this.ȫ�����ñ�.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.ȫ�����ñ�.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM ���ܷ�Χ��� WHERE name = " + ���ܼ���ͼ.getText() + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) ȫ�����ñ�.getModel()).insertRow(ȫ�����ñ�.getRowCount(), new Object[]{
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("Val")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        ȫ�����ñ�.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ȫ�����ñ�.getSelectedRow();
                String a = ȫ�����ñ�.getValueAt(i, 0).toString();
                String a1 = ȫ�����ñ�.getValueAt(i, 1).toString();
                String a2 = ȫ�����ñ�.getValueAt(i, 2).toString();
                ���ܼ����.setText(a);
                ���ܼ���ͼ.setText(a1);
                ���ܼ����ֵ.setText(a2);
            }
        });
        ��ȡ���ܷ�Χ���();
    }//GEN-LAST:event_����1ActionPerformed

    private void ���ܵ��Կ���ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_���ܵ��Կ���ActionPerformed
        ��������("���ܵ��Կ���", 2132);
        ˢ�¼��ܵ��Կ���();
        ��ȡ���ܷ�Χ���();
    }//GEN-LAST:event_���ܵ��Կ���ActionPerformed

    private void ˢ��ȫ���б�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ˢ��ȫ���б�ActionPerformed
        ˢ�¼���ֵ�б�();
    }//GEN-LAST:event_ˢ��ȫ���б�ActionPerformed

    private void �޸�����˺�ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�޸�����˺�ActionPerformed
        if (����˺�.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "����Ϊ��");
            return;
        }
        boolean result2 = this.����˺�.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        if (result2) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 2147);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.����˺�.getText() + "' where id = 2147;";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    gui.Start.GetConfigValues();
                    ˢ������˺�();
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_�޸�����˺�ActionPerformed

    private void �����⿪��ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_�����⿪��ActionPerformed
        ��������("�����⿪��", 2148);
        ˢ�¼����⿪��();
    }//GEN-LAST:event_�����⿪��ActionPerformed

    private void ���˷����ʼ�() {
        if (�ռ���.getText().equals("") || �ռ�������.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "���ұ�ѡ���ռ��ˡ�");
            return;
        }
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO mail (biaoti,juese,type1,shuliang1,type2,shuliang2,type3,shuliang3,wenben,shijian ) VALUES ( ?,?,?,?,?,?,?,?,?,? )")) {
            // ps.setInt(1, Integer.parseInt(�ռ���.getText()));
            ps.setString(1, �ʼ�����.getText());
            ps.setInt(2, Integer.parseInt(�ռ���.getText()));
            ps.setInt(3, Integer.parseInt(����1����.getText()));
            ps.setInt(4, Integer.parseInt(����1����.getText()));
            ps.setInt(5, Integer.parseInt(����2����.getText()));
            ps.setInt(6, Integer.parseInt(����2����.getText()));
            ps.setInt(7, Integer.parseInt(����3����.getText()));
            ps.setInt(8, Integer.parseInt(����3����.getText()));
            ps.setString(9, �ʼ��ı�.getText());
            ps.setString(10, CurrentReadable_Time());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "�ʼ����ͳɹ���");
            MapleCharacterUtil.sendNote(�ռ�������.getText(), �ռ�������.getText(), "[���ʼ�]" + �ʼ�����.getText(), 0);
        } catch (SQLException ex) {
            Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private static int ȫ������ȷ�� = 0;

    private void ȫ�������ʼ�() {
        if (ȫ������ȷ�� == 0) {
            JOptionPane.showMessageDialog(null, "���ȵ��ȫ��ȷ�ϡ�");
            return;
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                try (Connection con1 = DatabaseConnection.getConnection();
                        PreparedStatement ps1 = con1.prepareStatement("INSERT INTO mail (biaoti,juese,type1,shuliang1,type2,shuliang2,type3,shuliang3,wenben,shijian ) VALUES ( ?,?,?,?,?,?,?,?,?,? )")) {
                    ps1.setString(1, �ʼ�����.getText());
                    ps1.setInt(2, rs.getInt("id"));
                    ps1.setInt(3, Integer.parseInt(����1����.getText()));
                    ps1.setInt(4, Integer.parseInt(����1����.getText()));
                    ps1.setInt(5, Integer.parseInt(����2����.getText()));
                    ps1.setInt(6, Integer.parseInt(����2����.getText()));
                    ps1.setInt(7, Integer.parseInt(����3����.getText()));
                    ps1.setInt(8, Integer.parseInt(����3����.getText()));
                    ps1.setString(9, �ʼ��ı�.getText());
                    ps1.setString(10, CurrentReadable_Time());
                    ps1.executeUpdate();
                    MapleCharacterUtil.sendNote(rs.getString("name"), rs.getString("name"), "[���ʼ�]" + �ʼ�����.getText(), 0);
                } catch (SQLException ex) {
                }

            }
            JOptionPane.showMessageDialog(null, "�ʼ�ȫ�����ͳɹ���");

        } catch (SQLException ex) {
        }
    }

    private void ȫ����������ʼ�() {
        if (ȫ������ȷ�� == 0) {
            JOptionPane.showMessageDialog(null, "���ȵ��ȫ��ȷ�ϡ�");
            return;
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `characters` ORDER BY RAND() LIMIT 0,100;  ");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                try (Connection con1 = DatabaseConnection.getConnection();
                        PreparedStatement ps1 = con1.prepareStatement("INSERT INTO mail (biaoti,juese,type1,shuliang1,type2,shuliang2,type3,shuliang3,wenben,shijian ) VALUES ( ?,?,?,?,?,?,?,?,?,? )")) {
                    ps1.setString(1, �ʼ�����.getText());
                    ps1.setInt(2, rs.getInt("accountid"));
                    ps1.setInt(3, Integer.parseInt(����1����.getText()));
                    ps1.setInt(4, Integer.parseInt(����1����.getText()));
                    ps1.setInt(5, Integer.parseInt(����2����.getText()));
                    ps1.setInt(6, Integer.parseInt(����2����.getText()));
                    ps1.setInt(7, Integer.parseInt(����3����.getText()));
                    ps1.setInt(8, Integer.parseInt(����3����.getText()));
                    ps1.setString(9, �ʼ��ı�.getText());
                    ps1.setString(10, CurrentReadable_Time());
                    ps1.executeUpdate();
                    MapleCharacterUtil.sendNote(rs.getString("name"), rs.getString("name"), "[���ʼ�]" + �ʼ�����.getText(), 0);
                } catch (SQLException ex) {
                }
            }
            JOptionPane.showMessageDialog(null, "������ͳɹ���������͸���ң�" + rs.getString("name"));
        } catch (SQLException ex) {
        }

    }

    private void ȫ�����߷����ʼ�() {
        if (ȫ������ȷ�� == 0) {
            JOptionPane.showMessageDialog(null, "���ȵ��ȫ��ȷ�ϡ�");
            return;
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (World.Find.findChannel(rs.getString("name")) > 0) {
                    try (Connection con1 = DatabaseConnection.getConnection();
                            PreparedStatement ps1 = con1.prepareStatement("INSERT INTO mail (biaoti,juese,type1,shuliang1,type2,shuliang2,type3,shuliang3,wenben,shijian ) VALUES ( ?,?,?,?,?,?,?,?,?,? )")) {
                        ps1.setString(1, �ʼ�����.getText());
                        ps1.setInt(2, rs.getInt("accountid"));
                        ps1.setInt(3, Integer.parseInt(����1����.getText()));
                        ps1.setInt(4, Integer.parseInt(����1����.getText()));
                        ps1.setInt(5, Integer.parseInt(����2����.getText()));
                        ps1.setInt(6, Integer.parseInt(����2����.getText()));
                        ps1.setInt(7, Integer.parseInt(����3����.getText()));
                        ps1.setInt(8, Integer.parseInt(����3����.getText()));
                        ps1.setString(9, �ʼ��ı�.getText());
                        ps1.setString(10, CurrentReadable_Time());
                        ps1.executeUpdate();
                        MapleCharacterUtil.sendNote(rs.getString("name"), rs.getString("name"), "[���ʼ�]" + �ʼ�����.getText(), 0);
                    } catch (SQLException ex) {
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "�ʼ�ȫ�����߷��ͳɹ���");
        } catch (SQLException ex) {
        }
    }

    private void ȫ�������ʼ�1() {
        if (ȫ������ȷ�� > 0) {
            JOptionPane.showMessageDialog(null, "���Ѿ�ȷ�Ϲ��ˡ�");
            return;
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                try (Connection con1 = DatabaseConnection.getConnection();
                        PreparedStatement ps1 = con1.prepareStatement("INSERT INTO mail (biaoti,juese,type1,shuliang1,type2,shuliang2,type3,shuliang3,wenben,shijian ) VALUES ( ?,?,?,?,?,?,?,?,?,? )")) {
                    ps1.setString(1, �ʼ�����.getText());
                    ps1.setInt(2, 0);
                    ps1.setInt(3, 0);
                    ps1.setInt(4, 0);
                    ps1.setInt(5, 0);
                    ps1.setInt(6, 0);
                    ps1.setInt(7, 0);
                    ps1.setInt(8, 0);
                    ps1.setString(9, "����ȷ��");
                    ps1.setString(10, CurrentReadable_Time());
                    ps1.executeUpdate();
                } catch (SQLException ex) {
                }

            }
            ȫ������ȷ��++;
            JOptionPane.showMessageDialog(null, "�ʼ�ȫ������ȷ�ϳɹ���");
            PreparedStatement ps1 = null;
            ResultSet rs1 = null;
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM mail WHERE juese = ?");
                ps1.setInt(1, 0);
                rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    String sqlstr = " delete from mail where juese = 0";
                    ps1.executeUpdate(sqlstr);
                }
            } catch (SQLException ex) {

            }
        } catch (SQLException ex) {
        }
    }

    private void ˢ�½�ɫ��Ϣ() {
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
                ((DefaultTableModel) ��ɫ��Ϣ.getModel()).insertRow(��ɫ��Ϣ.getRowCount(), new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("level"),});

            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ɫ��Ϣ.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ɫ��Ϣ.getSelectedRow();
                String a = ��ɫ��Ϣ.getValueAt(i, 0).toString();
                String a1 = ��ɫ��Ϣ.getValueAt(i, 1).toString();
                �ռ���.setText(a);
                �ռ�������.setText(a1);
            }
        });
    }

    private void ˢ�����߽�ɫ��Ϣ() {
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
                        rs.getString("name"),
                        rs.getString("level"),});
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ɫ��Ϣ.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ɫ��Ϣ.getSelectedRow();
                String a = ��ɫ��Ϣ.getValueAt(i, 0).toString();
                String a1 = ��ɫ��Ϣ.getValueAt(i, 1).toString();
                �ռ���.setText(a);
                �ռ�������.setText(a1);
            }
        });
    }

    private void ˢ�����߽�ɫ��Ϣ() {
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
                        rs.getString("name"),
                        rs.getString("level"),});
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ��ɫ��Ϣ.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = ��ɫ��Ϣ.getSelectedRow();
                String a = ��ɫ��Ϣ.getValueAt(i, 0).toString();
                String a1 = ��ɫ��Ϣ.getValueAt(i, 1).toString();
                �ռ���.setText(a);
                �ռ�������.setText(a1);
            }
        });
    }

    private void ˢ�¹���㲥() {
        for (int i = ((DefaultTableModel) (this.�㲥��Ϣ.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�㲥��Ϣ.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM �㲥��Ϣ");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) �㲥��Ϣ.getModel()).insertRow(�㲥��Ϣ.getRowCount(), new Object[]{
                    rs.getString("id"),
                    rs.getString("�㲥")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZEVMS2.class.getName()).log(Level.SEVERE, null, ex);
        }
        �㲥��Ϣ.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = �㲥��Ϣ.getSelectedRow();
                String a = �㲥��Ϣ.getValueAt(i, 0).toString();
                String a1 = �㲥��Ϣ.getValueAt(i, 1).toString();
                �㲥���.setText(a);
                �㲥�ı�.setText(a1);
            }
        });
    }

    private void ˢ������㲥����() {
        String ����㲥��ʾ = "";
        int ����㲥 = gui.Start.ConfigValuesMap.get("����㲥����");
        if (����㲥 <= 0) {
            ����㲥��ʾ = "����㲥:����";
        } else {
            ����㲥��ʾ = "����㲥:�ر�";
        }
        ����㲥����(����㲥��ʾ);
    }

    private void ˢ���˺�ע��() {
        String �˺�ע����ʾ = "";
        int �˺�ע�� = gui.Start.ConfigValuesMap.get("�˺�ע�Ὺ��");
        if (�˺�ע�� <= 0) {
            �˺�ע����ʾ = "�˺�ע��:����";
        } else {
            �˺�ע����ʾ = "�˺�ע��:�ر�";
        }
        ��Ϸ�˺�ע��(�˺�ע����ʾ);
    }

    private void ˢ��������Ѷ() {
        String ������Ѷ��ʾ = "";
        int ������Ѷ = gui.Start.ConfigValuesMap.get("������Ѷ����");
        if (������Ѷ <= 0) {
            ������Ѷ��ʾ = "������Ѷ:����";
        } else {
            ������Ѷ��ʾ = "������Ѷ:�ر�";
        }
        ��Ϸ������Ѷ(������Ѷ��ʾ);
    }

    private void ˢ��ð�ռ�ְҵ����() {
        String ð�ռ�ְҵ������ʾ = "";
        int ð�ռ�ְҵ���� = gui.Start.ConfigValuesMap.get("ð�ռ�ְҵ����");
        if (ð�ռ�ְҵ���� <= 0) {
            ð�ռ�ְҵ������ʾ = "ð�ռ�:����";
        } else {
            ð�ռ�ְҵ������ʾ = "ð�ռ�:�ر�";
        }
        ��Ϸð�ռ�ְҵ����(ð�ռ�ְҵ������ʾ);
    }

    private void ˢ����ʿ��ְҵ����() {
        String ��ʿ��ְҵ������ʾ = "";
        int ��ʿ��ְҵ���� = gui.Start.ConfigValuesMap.get("��ʿ��ְҵ����");
        if (��ʿ��ְҵ���� <= 0) {
            ��ʿ��ְҵ������ʾ = "��ʿ��:����";
        } else {
            ��ʿ��ְҵ������ʾ = "��ʿ��:�ر�";
        }
        ��Ϸ��ʿ��ְҵ����(��ʿ��ְҵ������ʾ);
    }

    private void ˢ��ս��ְҵ����() {
        String ս��ְҵ������ʾ = "";
        int ս��ְҵ���� = gui.Start.ConfigValuesMap.get("ս��ְҵ����");
        if (ս��ְҵ���� <= 0) {
            ս��ְҵ������ʾ = "ս   ��:����";
        } else {
            ս��ְҵ������ʾ = "ս   ��:�ر�";
        }
        ��Ϸս��ְҵ����(ս��ְҵ������ʾ);
    }

    private void ˢ�¹���������() {
        String ˢ�¹�����������ʾ = "";
        int ���������� = gui.Start.ConfigValuesMap.get("����������");
        if (���������� <= 0) {
            ˢ�¹�����������ʾ = "��������:����";
        } else {
            ˢ�¹�����������ʾ = "��������:�ر�";
        }
        ����������(ˢ�¹�����������ʾ);
    }

    private void ˢ�¹�����ٿ���() {
        String ˢ�¹�����ٿ�����ʾ = "";
        int ������ٿ��� = gui.Start.ConfigValuesMap.get("������ٿ���");
        if (������ٿ��� <= 0) {
            ˢ�¹�����ٿ�����ʾ = "�������:����";
        } else {
            ˢ�¹�����ٿ�����ʾ = "�������:�ر�";
        }
        ������ٿ���(ˢ�¹�����ٿ�����ʾ);
    }

    private void ˢ����Ϸָ���() {
        String ˢ����Ϸָ�����ʾ = "";
        int ��Ϸָ��� = gui.Start.ConfigValuesMap.get("��Ϸָ���");
        if (��Ϸָ��� <= 0) {
            ˢ����Ϸָ�����ʾ = "��Ϸָ��:����";
        } else {
            ˢ����Ϸָ�����ʾ = "��Ϸָ��:�ر�";
        }
        ��Ϸָ���(ˢ����Ϸָ�����ʾ);
    }

    private void ˢ�¹������濪��() {
        String ˢ�¹������濪����ʾ = "";
        int �������濪�� = gui.Start.ConfigValuesMap.get("�������濪��");
        if (�������濪�� <= 0) {
            ˢ�¹������濪����ʾ = "��������:����";
        } else {
            ˢ�¹������濪����ʾ = "��������:�ر�";
        }
        �������濪��(ˢ�¹������濪����ʾ);
    }

    private void ˢ����Ϸ���ȿ���() {
        String ˢ����Ϸ���ȿ�����ʾ = "";
        int ��Ϸ���ȿ��� = gui.Start.ConfigValuesMap.get("��Ϸ���ȿ���");
        if (��Ϸ���ȿ��� <= 0) {
            ˢ����Ϸ���ȿ�����ʾ = "��Ϸ����:����";
        } else {
            ˢ����Ϸ���ȿ�����ʾ = "��Ϸ����:�ر�";
        }
        ��Ϸ���ȿ���(ˢ����Ϸ���ȿ�����ʾ);
    }

    private void ˢ�½ű����뿪��() {
        String ˢ�½ű����뿪����ʾ = "";
        int �ű����뿪�� = gui.Start.ConfigValuesMap.get("�ű����뿪��");
        if (�ű����뿪�� <= 0) {
            ˢ�½ű����뿪����ʾ = "�ű�����:����";
        } else {
            ˢ�½ű����뿪����ʾ = "�ű�����:�ر�";
        }
        �ű����뿪��(ˢ�½ű����뿪����ʾ);
    }

    private void ˢ�¶�����ҿ���() {
        String ˢ�¶�����ҿ�����ʾ = "";
        int ������ҿ��� = gui.Start.ConfigValuesMap.get("������ҿ���");
        if (������ҿ��� <= 0) {
            ˢ�¶�����ҿ�����ʾ = "�������:����";
        } else {
            ˢ�¶�����ҿ�����ʾ = "�������:�ر�";
        }
        ������ҿ���(ˢ�¶�����ҿ�����ʾ);
    }

    private void ˢ����ҽ��׿���() {
        String ˢ����ҽ��׿�����ʾ = "";
        int ��ҽ��׿��� = gui.Start.ConfigValuesMap.get("��ҽ��׿���");
        if (��ҽ��׿��� <= 0) {
            ˢ����ҽ��׿�����ʾ = "��ҽ���:����";
        } else {
            ˢ����ҽ��׿�����ʾ = "��ҽ���:�ر�";
        }
        ��ҽ��׿���(ˢ����ҽ��׿�����ʾ);
    }

    private void ˢ�¶�����Ʒ����() {
        String ˢ�¶�����Ʒ������ʾ = "";
        int ������Ʒ���� = gui.Start.ConfigValuesMap.get("������Ʒ����");
        if (������Ʒ���� <= 0) {
            ˢ�¶�����Ʒ������ʾ = "������Ʒ:����";
        } else {
            ˢ�¶�����Ʒ������ʾ = "������Ʒ:�ر�";
        }
        ������Ʒ����(ˢ�¶�����Ʒ������ʾ);
    }

    private void ˢ�½�ֹ��½����() {
        String ˢ�½�ֹ��½������ʾ = "";
        int ��ֹ��½���� = gui.Start.ConfigValuesMap.get("��ֹ��½����");
        if (��ֹ��½���� <= 0) {
            ˢ�½�ֹ��½������ʾ = "��Ϸ��½:��ֹ";
        } else {
            ˢ�½�ֹ��½������ʾ = "��Ϸ��½:ͨ��";
        }
        ��ֹ��½����(ˢ�½�ֹ��½������ʾ);
    }

    private void ˢ��ȫ����������() {
        String ˢ��ȫ������������ʾ = "";
        int ȫ���������� = gui.Start.ConfigValuesMap.get("ȫ����������");
        if (ȫ���������� <= 0) {
            ˢ��ȫ������������ʾ = "ȫ������:����";
        } else {
            ˢ��ȫ������������ʾ = "ȫ������:�ر�";
        }
        ȫ����������(ˢ��ȫ������������ʾ);
    }

    private void ˢ�»�ӭ��������() {
        String ˢ�»�ӭ����������ʾ = "";
        int ��ӭ�������� = gui.Start.ConfigValuesMap.get("��ӭ��������");
        if (��ӭ�������� <= 0) {
            ˢ�»�ӭ����������ʾ = "��ӭ����:����";
        } else {
            ˢ�»�ӭ����������ʾ = "��ӭ����:�ر�";
        }
        ��ӭ��������(ˢ�»�ӭ����������ʾ);
    }

    private void ˢ����Ϸ�ֿ⿪��() {
        String ˢ����Ϸ�ֿ⿪����ʾ = "";
        int ��Ϸ�ֿ⿪�� = gui.Start.ConfigValuesMap.get("��Ϸ�ֿ⿪��");
        if (��Ϸ�ֿ⿪�� <= 0) {
            ˢ����Ϸ�ֿ⿪����ʾ = "��Ϸ�ֿ�:����";
        } else {
            ˢ����Ϸ�ֿ⿪����ʾ = "��Ϸ�ֿ�:�ر�";
        }
        ��Ϸ�ֿ⿪��(ˢ����Ϸ�ֿ⿪����ʾ);
    }

    private void ˢ�µ�½��¼����() {
        String ˢ�µ�¼��¼������ʾ = "";
        int ��¼��¼���� = gui.Start.ConfigValuesMap.get("��½��¼����");
        if (��¼��¼���� <= 0) {
            ˢ�µ�¼��¼������ʾ = "��½��¼:����";
        } else {
            ˢ�µ�¼��¼������ʾ = "��½��¼:�ر�";
        }
        ��½��¼����(ˢ�µ�¼��¼������ʾ);
    }

    private void ˢ������ֿ⿪��() {
        String ˢ������ֿ⿪����ʾ = "";
        int ����ֿ⿪�� = gui.Start.ConfigValuesMap.get("����ֿ⿪��");
        if (����ֿ⿪�� <= 0) {
            ˢ������ֿ⿪����ʾ = "����ֿ�:����";
        } else {
            ˢ������ֿ⿪����ʾ = "����ֿ�:�ر�";
        }
        ����ֿ⿪��(ˢ������ֿ⿪����ʾ);
    }

    private void ˢ�������¼����() {
        String ˢ�������¼������ʾ = "";
        int �����¼���� = gui.Start.ConfigValuesMap.get("�����¼����");
        if (�����¼���� <= 0) {
            ˢ�������¼������ʾ = "�����¼:����";
        } else {
            ˢ�������¼������ʾ = "�����¼:�ر�";
        }
        �����¼����(ˢ�������¼������ʾ);
    }

    private void ˢ�µ�½��֤����() {
        String ˢ�µ�½��֤������ʾ = "";
        int ��½��֤���� = gui.Start.ConfigValuesMap.get("��½��֤����");
        if (��½��֤���� <= 0) {
            ˢ�µ�½��֤������ʾ = "��½��֤:����";
        } else {
            ˢ�µ�½��֤������ʾ = "��½��֤:�ر�";
        }
        ��½��֤����(ˢ�µ�½��֤������ʾ);
    }

    private void ˢ�������п���() {
        String ˢ�������п�����ʾ = "";
        int �����п��� = gui.Start.ConfigValuesMap.get("�����п���");
        if (�����п��� <= 0) {
            ˢ�������п�����ʾ = "������:����";
        } else {
            ˢ�������п�����ʾ = "������:�ر�";
        }
        �����п���(ˢ�������п�����ʾ);
    }

    private void ˢ�¹�Ӷ���˿���() {
        String ˢ�¹�Ӷ���˿�����ʾ = "";
        int ��Ӷ���˿��� = gui.Start.ConfigValuesMap.get("��Ӷ���˿���");
        if (��Ӷ���˿��� <= 0) {
            ˢ�¹�Ӷ���˿�����ʾ = "��Ӷ����:����";
        } else {
            ˢ�¹�Ӷ���˿�����ʾ = "��Ӷ����:�ر�";
        }
        ��Ӷ���˿���(ˢ�¹�Ӷ���˿�����ʾ);
    }

    private void ˢ���������ѿ���() {
        String ˢ���������ѿ�����ʾ = "";
        int �������ѿ��� = gui.Start.ConfigValuesMap.get("�������ѿ���");
        if (�������ѿ��� <= 0) {
            ˢ���������ѿ�����ʾ = "��������:����";
        } else {
            ˢ���������ѿ�����ʾ = "��������:�ر�";
        }
        �������ѿ���(ˢ���������ѿ�����ʾ);
    }

    private void ˢ���۷�㲥����() {
        String ˢ���۷�㲥������ʾ = "";
        int �۷�㲥���� = gui.Start.ConfigValuesMap.get("�۷�㲥����");
        if (�۷�㲥���� <= 0) {
            ˢ���۷�㲥������ʾ = "�۷�㲥:����";
        } else {
            ˢ���۷�㲥������ʾ = "�۷�㲥:�ر�";
        }
        �۷�㲥����(ˢ���۷�㲥������ʾ);
    }

    private void ˢ��������쿪��() {
        String ˢ��������쿪����ʾ = "";
        int ������쿪�� = gui.Start.ConfigValuesMap.get("������쿪��");
        if (������쿪�� <= 0) {
            ˢ��������쿪����ʾ = "�������:����";
        } else {
            ˢ��������쿪����ʾ = "�������:�ر�";
        }
        ������쿪��(ˢ��������쿪����ʾ);
    }

    private void ˢ�»��յ�ͼ����() {
        String ˢ�»��յ�ͼ������ʾ = "";
        int ���յ�ͼ���� = gui.Start.ConfigValuesMap.get("���յ�ͼ����");
        if (���յ�ͼ���� <= 0) {
            ˢ�»��յ�ͼ������ʾ = "���յ�ͼ:����";
        } else {
            ˢ�»��յ�ͼ������ʾ = "���յ�ͼ:�ر�";
        }
        ���յ�ͼ����(ˢ�»��յ�ͼ������ʾ);
    }

    private void ˢ��ָ��֪ͨ����() {
        String ˢ��ָ��֪ͨ������ʾ = "";
        int ָ��֪ͨ���� = gui.Start.ConfigValuesMap.get("ָ��֪ͨ����");
        if (ָ��֪ͨ���� <= 0) {
            ˢ��ָ��֪ͨ������ʾ = "ָ��֪ͨ:����";
        } else {
            ˢ��ָ��֪ͨ������ʾ = "ָ��֪ͨ:�ر�";
        }
        ָ��֪ͨ����(ˢ��ָ��֪ͨ������ʾ);
    }

    private void ˢ����ѩ�쿪��() {
        String ˢ����ѩ�쿪����ʾ = "";
        int ��ѩ�쿪�� = gui.Start.ConfigValuesMap.get("��ѩ�쿪��");
        if (��ѩ�쿪�� <= 0) {
            ˢ����ѩ�쿪����ʾ = "��ѩ��:����";
        } else {
            ˢ����ѩ�쿪����ʾ = "��ѩ��:�ر�";
        }
        ��ѩ�쿪��(ˢ����ѩ�쿪����ʾ);
    }

    private void ˢ���º컨����() {
        String ˢ���º컨������ʾ = "";
        int �º컨���� = gui.Start.ConfigValuesMap.get("�º컨����");
        if (�º컨���� <= 0) {
            ˢ���º컨������ʾ = "�º컨:����";
        } else {
            ˢ���º컨������ʾ = "�º컨:�ر�";
        }
        �º컨����(ˢ���º컨������ʾ);
    }

    private void ˢ�������ݿ���() {
        String ˢ�������ݿ�����ʾ = "";
        int �����ݿ��� = gui.Start.ConfigValuesMap.get("�����ݿ���");
        if (�����ݿ��� <= 0) {
            ˢ�������ݿ�����ʾ = "������:����";
        } else {
            ˢ�������ݿ�����ʾ = "������:�ر�";
        }
        �����ݿ���(ˢ�������ݿ�����ʾ);
    }

    private void ˢ����ѩ������() {
        String ˢ����ѩ��������ʾ = "";
        int ��ѩ������ = gui.Start.ConfigValuesMap.get("��ѩ������");
        if (��ѩ������ <= 0) {
            ˢ����ѩ��������ʾ = "��ѩ��:����";
        } else {
            ˢ����ѩ��������ʾ = "��ѩ��:�ر�";
        }
        ��ѩ������(ˢ����ѩ��������ʾ);
    }

    private void ˢ���·�Ҷ����() {
        String ˢ���·�Ҷ������ʾ = "";
        int �·�Ҷ���� = gui.Start.ConfigValuesMap.get("�·�Ҷ����");
        if (�·�Ҷ���� <= 0) {
            ˢ���·�Ҷ������ʾ = "�·�Ҷ:����";
        } else {
            ˢ���·�Ҷ������ʾ = "�·�Ҷ:�ر�";
        }
        �·�Ҷ����(ˢ���·�Ҷ������ʾ);
    }

    public void ˢ�������NPC() {
        for (int i = ((DefaultTableModel) (this.�����NPC.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.�����NPC.getModel())).removeRow(i);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM ��Ϸnpc����");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) �����NPC.getModel()).insertRow(�����NPC.getRowCount(), new Object[]{rs.getInt("mid"), rs.getString("dataid")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
                    String sqlString3 = null;
                    String sqlString4 = null;
                    sqlString2 = "update configvalues set Val= '0' where id= '" + b + "';";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, b);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    String sqlString3 = null;
                    String sqlString4 = null;
                    sqlString2 = "update configvalues set Val= '1' where id='" + b + "';";
                    PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨2��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*if (��⿪�� <= 0) {
            PreparedStatement ps1 = null;
            ResultSet rs = null;
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 1);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from configvalues where id =" + b + "";
                    ps1.executeUpdate(sqlstr);
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
                ps.setInt(1, b);
                ps.setString(2, a);
                ps.setInt(3, 1);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            PreparedStatement ps1 = null;
            ResultSet rs = null;
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 1);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from configvalues where id =" + b + "";
                    ps1.executeUpdate(sqlstr);
                }
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
                ps.setInt(1, b);
                ps.setString(2, a);
                ps.setInt(3, 0);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(����̨1��.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        gui.Start.GetConfigValues();
    }

    public static void main(String args[]) {
        ZEVMS.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ����̨1��().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton IP�࿪����;
    private javax.swing.JTextField IP�࿪����;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel249;
    private javax.swing.JLabel jLabel250;
    private javax.swing.JLabel jLabel251;
    private javax.swing.JLabel jLabel252;
    private javax.swing.JLabel jLabel253;
    private javax.swing.JLabel jLabel254;
    private javax.swing.JLabel jLabel255;
    private javax.swing.JLabel jLabel256;
    private javax.swing.JLabel jLabel257;
    private javax.swing.JLabel jLabel258;
    private javax.swing.JLabel jLabel259;
    private javax.swing.JLabel jLabel260;
    private javax.swing.JLabel jLabel261;
    private javax.swing.JLabel jLabel262;
    private javax.swing.JLabel jLabel263;
    private javax.swing.JLabel jLabel264;
    private javax.swing.JLabel jLabel267;
    private javax.swing.JLabel jLabel268;
    private javax.swing.JLabel jLabel269;
    private javax.swing.JLabel jLabel270;
    private javax.swing.JLabel jLabel271;
    private javax.swing.JLabel jLabel281;
    private javax.swing.JLabel jLabel285;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel330;
    private javax.swing.JLabel jLabel331;
    private javax.swing.JLabel jLabel332;
    private javax.swing.JLabel jLabel333;
    private javax.swing.JLabel jLabel334;
    private javax.swing.JLabel jLabel335;
    private javax.swing.JLabel jLabel336;
    private javax.swing.JLabel jLabel337;
    private javax.swing.JLabel jLabel338;
    private javax.swing.JLabel jLabel339;
    private javax.swing.JLabel jLabel340;
    private javax.swing.JLabel jLabel341;
    private javax.swing.JLabel jLabel342;
    private javax.swing.JLabel jLabel343;
    private javax.swing.JLabel jLabel344;
    private javax.swing.JLabel jLabel345;
    private javax.swing.JLabel jLabel346;
    private javax.swing.JLabel jLabel347;
    private javax.swing.JLabel jLabel348;
    private javax.swing.JLabel jLabel349;
    private javax.swing.JLabel jLabel350;
    private javax.swing.JLabel jLabel351;
    private javax.swing.JLabel jLabel352;
    private javax.swing.JLabel jLabel353;
    private javax.swing.JLabel jLabel354;
    private javax.swing.JLabel jLabel355;
    private javax.swing.JLabel jLabel356;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane106;
    private javax.swing.JScrollPane jScrollPane107;
    private javax.swing.JScrollPane jScrollPane108;
    private javax.swing.JScrollPane jScrollPane136;
    private javax.swing.JScrollPane jScrollPane137;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JPanel һ������;
    private javax.swing.JButton �������ѿ���;
    private javax.swing.JButton �·�Ҷ����;
    private javax.swing.JButton �����ݿ���;
    private javax.swing.JButton �º컨����;
    private javax.swing.JButton ��ѩ�쿪��;
    private javax.swing.JButton ��ѩ������;
    private javax.swing.JButton ������Ʒ����;
    private javax.swing.JButton ������ҿ���;
    private javax.swing.JButton ���˷���;
    private javax.swing.JButton ����ģʽ����;
    private javax.swing.JButton �޸�1;
    private javax.swing.JButton �޸�ð�ռҵȼ�����;
    private javax.swing.JButton �޸�ð�ռҵȼ�����1;
    private javax.swing.JButton �޸Ĺ㲥;
    private javax.swing.JButton �޸�����˺�;
    private javax.swing.JButton �޸ķ�����������;
    private javax.swing.JButton �޸Ľ�ɫ����;
    private javax.swing.JButton �޸��˺�����;
    private javax.swing.JButton �޸���ʿ�ŵȼ�����;
    private javax.swing.JButton �޸���ʿ�ŵȼ�����1;
    private javax.swing.JButton �޸���ʿ�ŵȼ�����2;
    private javax.swing.JButton �޸���ʿ�ŵȼ�����3;
    private javax.swing.JButton ȫ�ּ�⿪��;
    private javax.swing.JButton ȫ����⿪��;
    private javax.swing.JTable ȫ�����ñ�;
    private javax.swing.JButton ȫ����������;
    private javax.swing.JButton ȫ������;
    private javax.swing.JTextField ð�ռҵȼ�����;
    private javax.swing.JButton ð�ռ�ְҵ����;
    private javax.swing.JTextField ɾMAC����;
    private javax.swing.JButton ɾ��IP;
    private javax.swing.JTextField ɾ��IP����;
    private javax.swing.JButton ɾ��MAC;
    private javax.swing.JPanel ɾ��NPC;
    private javax.swing.JButton ɾ���㲥;
    private javax.swing.JButton ɾ�������npc;
    private javax.swing.JTextField ɾ�������npc����;
    private javax.swing.JButton ˢ��ȫ���б�;
    private javax.swing.JButton ˢ�·�IP;
    private javax.swing.JButton ˢ�·�MAC;
    private javax.swing.JButton ˢ�¹��;
    private javax.swing.JButton ˢ�¾����;
    private javax.swing.JButton ˢ�������NPC;
    private javax.swing.JButton ˢ�½�ɫ;
    private javax.swing.JButton ˢ�����ñ�;
    private javax.swing.JButton ���ټ�⿪��;
    private javax.swing.JTable ����������Ϣ;
    private javax.swing.JButton ˫��Ƶ������;
    private javax.swing.JButton �������;
    private javax.swing.JButton �������ؿ���;
    private javax.swing.JButton ���ּ�⿪��;
    private javax.swing.JButton �̳Ǽ�⿪��;
    private javax.swing.JButton ���������;
    private javax.swing.JButton ���յ�ͼ����;
    private javax.swing.JButton ���߷���;
    private javax.swing.JButton ���߽�ɫ;
    private javax.swing.JButton ��ͼ���ƿ���;
    private javax.swing.JButton �󺣹꿪��;
    private javax.swing.JButton ����ǿ���;
    private javax.swing.JTable ��IP;
    private javax.swing.JTable ��MAC;
    private javax.swing.JPanel ���IPMAC;
    private javax.swing.JButton С���ÿ���;
    private javax.swing.JButton С���߿���;
    private javax.swing.JButton ����㲥����;
    private javax.swing.JButton �۷�㲥����;
    private javax.swing.JTable �㲥��Ϣ;
    private javax.swing.JTextField �㲥���;
    private javax.swing.JTextField �㲥�ı�;
    private javax.swing.JButton ����״̬����;
    private javax.swing.JButton ս��ְҵ����;
    private javax.swing.JButton ���ܳͷ�����;
    private javax.swing.JTextField ���ܼ���ͼ;
    private javax.swing.JTextField ���ܼ����ֵ;
    private javax.swing.JTextField ���ܼ����;
    private javax.swing.JPanel ���ܷ�Χ���;
    private javax.swing.JButton ���ܵ��Կ���;
    private javax.swing.JButton �����п���;
    private javax.swing.JButton �һ���⿪��;
    private javax.swing.JButton ָ��֪ͨ����;
    private javax.swing.JButton �����⿪��;
    private javax.swing.JButton ����1;
    private javax.swing.JTextField �ռ���;
    private javax.swing.JTextField �ռ�������;
    private javax.swing.JButton ����1;
    private javax.swing.JButton �Ǿ��鿪��;
    private javax.swing.JTextField ����˺�;
    private javax.swing.JTextField ������������;
    private javax.swing.JButton �����˿���;
    private javax.swing.JButton ������ע�Ὺ��;
    private javax.swing.JButton �����࿪����;
    private javax.swing.JTextField ������࿪����;
    private javax.swing.JButton ��ӭ��������;
    private javax.swing.JButton ������⿪��;
    private javax.swing.JButton ������������;
    private javax.swing.JButton ������������1;
    private javax.swing.JButton ������������2;
    private javax.swing.JButton ���������ʼ�;
    private javax.swing.JButton ��Ϸ�ֿ⿪��;
    private javax.swing.JButton ��Ϸ������Ѷ;
    private javax.swing.JButton ��Ϸ���ȿ���;
    private javax.swing.JPanel ��Ϸ�㲥;
    private javax.swing.JButton ��Ϸ���˿���;
    private javax.swing.JButton ��Ϸָ���;
    private javax.swing.JButton ��Ϸ����ӳ�˵��;
    private javax.swing.JButton ��Ϸ�˺�ע��;
    private javax.swing.JButton �������濪��;
    private javax.swing.JButton ƯƯ����;
    private javax.swing.JButton ��Ұ����;
    private javax.swing.JButton ��ҽ��׿���;
    private javax.swing.JButton ������쿪��;
    private javax.swing.JTextField ƣ��ֵ;
    private javax.swing.JButton ��½��������;
    private javax.swing.JButton ��½��������;
    private javax.swing.JButton ��½��¼����;
    private javax.swing.JButton ��½���п���;
    private javax.swing.JButton ��½��֤����;
    private javax.swing.JButton ��ѩ�˿���;
    private javax.swing.JButton ʯͷ�˿���;
    private javax.swing.JButton ȷ��ȫ��;
    private javax.swing.JButton ��ֹ��½����;
    private javax.swing.JButton ���߽�ɫ;
    private javax.swing.JButton ����ֿ���;
    private javax.swing.JButton ������ٿ���;
    private javax.swing.JButton ����������;
    private javax.swing.JButton ��ɫè����;
    private javax.swing.JButton ���з����;
    private javax.swing.JTable ����ӳɱ�;
    private javax.swing.JButton ����ӳɱ��޸�;
    private javax.swing.JTextField ����ӳɱ����;
    private javax.swing.JTextField ����ӳɱ���ֵ;
    private javax.swing.JTextField ����ӳɱ�����;
    private javax.swing.JButton ��ˮ�鿪��;
    private javax.swing.JButton Ⱥ����⿪��;
    private javax.swing.JButton �����¼����;
    private javax.swing.JButton ����쿪��;
    private javax.swing.JButton �ű����뿪��;
    private javax.swing.JTable �����NPC;
    private javax.swing.JButton ��Ģ������;
    private javax.swing.JButton ����ţ����;
    private javax.swing.JButton Ģ���п���;
    private javax.swing.JTextField װ���������;
    private javax.swing.JTextField װ���������1;
    private javax.swing.JTextField װ�������ȼ�;
    private javax.swing.JTextField װ�������ȼ�1;
    private javax.swing.JTextField װ����������;
    private javax.swing.JTextField װ����������1;
    private javax.swing.JTable װ����������;
    private javax.swing.JTable ��ɫ��Ϣ;
    private javax.swing.JButton Խ����ֿ���;
    private javax.swing.JButton ��ͼ�浵����;
    private javax.swing.JTextPane �ʼ��ı�;
    private javax.swing.JTextField �ʼ�����;
    private javax.swing.JPanel �ʼ�ϵͳ;
    private javax.swing.JButton ������������;
    private javax.swing.JPanel ����;
    private javax.swing.JButton �����޸�;
    private javax.swing.JTextField �������;
    private javax.swing.JButton ������������;
    private javax.swing.JTextField ������ֵ;
    private javax.swing.JTextField ��������;
    private javax.swing.JTable ���ӱ�;
    private javax.swing.JTextField ����1����;
    private javax.swing.JTextField ����1����;
    private javax.swing.JTextField ����2����;
    private javax.swing.JTextField ����2����;
    private javax.swing.JTextField ����3����;
    private javax.swing.JTextField ����3����;
    private javax.swing.JButton ��ħ���ѿ���;
    private javax.swing.JTextField ���ƴ�����ɫ����;
    private javax.swing.JTextField ����ע���˺�����;
    private javax.swing.JButton �������;
    private javax.swing.JButton ����ֿ⿪��;
    private javax.swing.JButton ��Ӷ���˿���;
    private javax.swing.JTextField ��Ӷ����ʱ��;
    private javax.swing.JButton �����㿪��;
    private javax.swing.JButton ��Ƥ�￪��;
    private javax.swing.JTextField ��ʿ�ŵȼ�����;
    private javax.swing.JButton ��ʿ��ְҵ����;
    // End of variables declaration//GEN-END:variables

    private class jTextField {

        public jTextField() {
        }
    }
}
