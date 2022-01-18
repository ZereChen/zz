/*
��Ϸ���
 */
package constants;

import server.ServerProperties;

public class ServerConstants {

    // ��ʼͶƱ
    public static final boolean PollEnabled = false;//falseture
    public static final String Poll_Question = "Are you mudkiz?";
    public static final String[] Poll_Answers = {"test1", "test2", "test3"};
    // ����ͶƱ
    /*public static final short ���ͻ��˰汾 = 79;//MAPLE_VERSION
    public static final String ���ͻ���С�汾 = "1";//MAPLE_PATCH*/
    public static final boolean Use_Fixed_IV = false;//false
    public static final int MIN_MTS = 110;
    public static final int MTS_BASE = 100; //+1000 to everything in MSEA but cash is costly here
    public static final int MTS_TAX = 10; //+% to everything
    public static final int MTS_MESO = 5000; //mesos needed
    public static final int CHANNEL_COUNT = 200;
    //������������
    public static boolean �����ʾ = Boolean.parseBoolean(ServerProperties.getProperty("ZEV.�����ʾ", "false"));//
    public static boolean ���������� = Boolean.parseBoolean(ServerProperties.getProperty("ZEV.����������", "ture"));
    public static boolean �Զ�ע�� = true;
    public static boolean Super_password = false;
    public static final boolean PACKET_ERROR_OFF = Boolean.parseBoolean(ServerProperties.getProperty("ZEV.��¼38����", "false"));
    public static String Gateway_IP_String;
    public static boolean ����ģʽ;
    public static boolean loadop;
    public static String superpw = "";
    public static String PACKET_ERROR = "";
    //Ϊ�˽������
    public static boolean clientAutoDisconnect = true;

    public static String getPACKET_ERROR() {
        return PACKET_ERROR;
    }

    public static void setPACKET_ERROR(String PACKET_ERROR) {
        ServerConstants.PACKET_ERROR = PACKET_ERROR;
    }

    public static boolean getAutoReg() {
        return �Զ�ע��;
    }

    public static String ChangeAutoReg() {
        �Զ�ע�� = !getAutoReg();
        return �Զ�ע�� ? "����" : "�ر�";
    }

    public static final byte Class_Bonus_EXP(final int job) {
        switch (job) {
            case 3000: //whenever these arrive, they'll give bonus
            case 3200:
            case 3210:
            case 3211:
            case 3212:
            case 3300:
            case 3310:
            case 3311:
            case 3312:
            case 3500:
            case 3510:
            case 3511:
            case 3512:
                return 10;
        }
        return 0;
    }

    public static enum PlayerGMRank {//�������
        ���ָ��('@', 0),
        Ѳ�����('*', 1),
        �����('*', 2),
        ��Ϸ����('*', 3);
        /*NORMAL('@', 0),
        INTERN('!', 1),
        GM('!', 2),
        ADMIN('!', 3);
        SUPERADMIN('!', 3);*/
        private char commandPrefix;
        private int level;

        private PlayerGMRank(char ch, int level) {
            commandPrefix = ch;
            this.level = level;
        }

        public char getCommandPrefix() {
            return commandPrefix;
        }

        public int getLevel() {
            return level;
        }

    }

    public static enum CommandType {

        ���ָ��(0),
        TRADE(1);
        private int level;

        private CommandType(int level) {
            this.level = level;
        }

        public int getType() {
            return level;
        }
    }
}
