package tools.packet;

import static abc.Game.����2;
import handling.SendPacketOpcode;
import tools.MaplePacketCreator;
import tools.data.MaplePacketLittleEndianWriter;

public class UIPacket {

    public static final byte[] MapNameDisplay(final int mapid) {
        return MaplePacketCreator.environmentChange("maplemap/enter/" + mapid, 3);
    }

    public static final byte[] EarnTitleMsg(final String msg) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();

        if (����2 == "��") {
            System.out.println("UIPacket��EarnTitleMsg");
        }
// "You have acquired the Pig's Weakness skill."
        mplew.writeShort(SendPacketOpcode.EARN_TITLE_MSG.getValue());
        mplew.writeMapleAsciiString(msg);

        return mplew.getPacket();
    }

    public static byte[] getSPMsg(byte sp, short job) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();

        if (����2 == "��") {
            System.out.println("UIPacket��getSPMsg");
        }
        mplew.writeShort(SendPacketOpcode.SHOW_STATUS_INFO.getValue());
        mplew.write(3);
        mplew.writeShort(job);
        mplew.write(sp);

        return mplew.getPacket();
    }

    public static byte[] getGPMsg(int itemid) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();

        if (����2 == "��") {
            System.out.println("UIPacket��getGPMsg");
        }
        // Temporary transformed as a dragon, even with the skill ......
        mplew.writeShort(SendPacketOpcode.SHOW_STATUS_INFO.getValue());
        mplew.write(6);
        mplew.writeInt(itemid);

        return mplew.getPacket();
    }

    /**
     * <��Ļ�м��ɫ��>
     */
    public static byte[] getTopMsg(String msg) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.TOP_MSG.getValue());
        mplew.writeMapleAsciiString(msg);

        return mplew.getPacket();
    }

    public static byte[] getStatusMsg(int itemid) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();

        if (����2 == "��") {
            System.out.println("UIPacket��getStatusMsg");
        }
        // Temporary transformed as a dragon, even with the skill ......
        mplew.writeShort(SendPacketOpcode.SHOW_STATUS_INFO.getValue());
        mplew.write(7);
        mplew.writeInt(itemid);

        return mplew.getPacket();
    }

    public static final byte[] MapEff(final String path) {
        if (����2 == "��") {
            System.out.println("UIPacket��MapEff");
        }
        return MaplePacketCreator.environmentChange(path, 3);
    }

    public static final byte [] Aran_Start() {
        if (����2 == "��") {
            System.out.println("UIPacket��Aran_Start");
        }
        return MaplePacketCreator.environmentChange("Aran/balloon", 4);
    }

    public static final byte[] AranTutInstructionalBalloon(final String data) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.SHOW_ITEM_GAIN_INCHAT.getValue());
        mplew.write(0x15);
        mplew.writeMapleAsciiString(data);
        mplew.writeInt(1);
        return mplew.getPacket();
    }

    public static final byte[] AranTutInstructionalBalloon22(final String data) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.SHOW_ITEM_GAIN_INCHAT.getValue());
        mplew.write(0x21);
        mplew.writeMapleAsciiString(data);
        mplew.writeInt(1);
        return mplew.getPacket();
    }

    public static byte[] AranTutInstructionalBalloon2(String data) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.SHOW_ITEM_GAIN_INCHAT.getValue());
        mplew.write(0x23);
        mplew.writeMapleAsciiString(data);
        mplew.writeInt(1);
        return mplew.getPacket();
    }

    /**
     * <���Ŷ���>
     */
    public static byte[] ShowWZEffectS(String data, int info) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.SHOW_ITEM_GAIN_INCHAT.getValue());
        mplew.write(0x14);
        mplew.writeMapleAsciiString(data);
        if (info > -1) {
            mplew.writeInt(info);
        }
        return mplew.getPacket();
    }

    /**
     * <���Ŷ���>
     */
    public static final byte[] ShowWZEffect(final String data, int info) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.SHOW_ITEM_GAIN_INCHAT.getValue());
        if (info == -1) {
            mplew.write(18);
        } else {
            mplew.write(23);
        }
        mplew.writeMapleAsciiString(data);
        if (info > -1) {
            mplew.writeInt(info);
        }

        return mplew.getPacket();
    }

    /**
     * <�ٻ�ս��С����?>
     */
    public static byte[] summonHelper(boolean summon) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.SUMMON_HINT.getValue());
        mplew.write(summon ? 1 : 0);

        return mplew.getPacket();
    }

    /**
     * <�ٻ���Ϣ???>
     */
    public static byte[] summonMessage(int type) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.SUMMON_HINT_MSG.getValue());
        mplew.write(1);
        mplew.writeInt(type);
        mplew.writeInt(7000); // probably the delay

        return mplew.getPacket();
    }

    /**
     * <�ٻ���Ϣ???>
     */
    public static byte[] summonMessage(String message) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.SUMMON_HINT_MSG.getValue());
        mplew.write(0);
        mplew.writeMapleAsciiString(message);
        mplew.writeInt(200); // IDK
        mplew.writeShort(0);
        mplew.writeInt(10000); // Probably delay

        return mplew.getPacket();
    }

    /**
     * <δ���ԣ�һ�ֿ���>
     */
    public static byte[] IntroLock(boolean enable) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.CYGNUS_INTRO_LOCK.getValue());
        mplew.write(enable ? 1 : 0);

        return mplew.getPacket();
    }

    /**
     * <UI���濪��>
     */
    public static byte[] IntroDisableUI(boolean enable) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.CYGNUS_INTRO_DISABLE_UI.getValue());
        mplew.write(enable ? 1 : 0);

        return mplew.getPacket();
    }

    public static byte[] IntroDisableUI2(boolean enable) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.CYGNUS_INTRO_DISABLE_UI.getValue());
        mplew.write(enable ? 1 : 0);
        return mplew.getPacket();
    }

    /**
     * <�����㶫��>
     */
    public static byte[] fishingUpdate(byte type, int id) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.FISHING_BOARD_UPDATE.getValue());
        mplew.write(type);
        mplew.writeInt(id);

        return mplew.getPacket();
    }

    /**
     * <����Ч�����ɹ���> @
     */
    public static byte[] fishingCaught(int chrid) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.FISHING_CAUGHT.getValue());
        mplew.writeInt(chrid);

        return mplew.getPacket();
    }

}
