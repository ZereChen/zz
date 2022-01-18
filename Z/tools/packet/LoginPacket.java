package tools.packet;

import static a.�÷���ȫ.����ȡ�㲥;
import static a.�÷���ȫ.����ȡ�ض��㲥;
import java.util.List;
import java.util.Map;
import java.util.Set;

import client.MapleClient;
import client.MapleCharacter;
import constants.GameConstants;
import constants.ServerConstants;
import handling.SendPacketOpcode;
import handling.login.Balloon;
import handling.login.LoginServer;
import handling.world.MapleParty;
import tools.data.MaplePacketLittleEndianWriter;
import tools.HexTool;
import tools.MaplePacketCreator;

public class LoginPacket {

    public static final byte[] getHello(final short mapleVersion, final byte[] sendIv, final byte[] recvIv) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(16);

        if (ServerConstants.����������) {
            System.out.println("getHello--------------------");
        }
        mplew.writeShort(13); // 13 = MSEA, 14 = GlobalMS, 15 = EMS
        mplew.writeShort(mapleVersion);
        mplew.write(new byte[]{0, 0});
        // mplew.writeMapleAsciiString(ServerConstants.���ͻ���С�汾);
        mplew.write(recvIv);
        mplew.write(sendIv);
        mplew.write(4); // 7 = MSEA, 8 = GlobalMS, 5 = Test Server

        return mplew.getPacket();
    }

    public static final byte[] getPing() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(16);

        if (ServerConstants.����������) {
            System.out.println("getPing--------------------");
        }
        mplew.writeShort(SendPacketOpcode.PING.getValue());

        return mplew.getPacket();
    }

    public static final byte[] StrangeDATA() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(16);

        if (ServerConstants.����������) {
            System.out.println("StrangeDATA--------------------");
        }
        mplew.writeShort(0x12);
        // ���ַ���=���ɵľ�̬��Կ
        mplew.writeMapleAsciiString("30819F300D06092A864886F70D010101050003818D0030818902818100994F4E66B003A7843C944E67BE4375203DAA203C676908E59839C9BADE95F53E848AAFE61DB9C09E80F48675CA2696F4E897B7F18CCB6398D221C4EC5823D11CA1FB9764A78F84711B8B6FCA9F01B171A51EC66C02CDA9308887CEE8E59C4FF0B146BF71F697EB11EDCEBFCE02FB0101A7076A3FEB64F6F6022C8417EB6B87270203010001");
        //mplew.writeMapleAsciiString("30819D300D06092A864886F70D010101050003818B00308187028181009E68DD55B554E5924BA42CCB2760C30236B66234AFAA420E8E300E74F1FDF27CD22B7FF323C324E714E143D71780C1982E6453AD87749F33E540DB44E9F8C627E6898F915587CD2A7D268471E002D30DF2E214E2774B4D3C58609155A7C79E517CEA332AF96C0161BFF6EDCF1CB44BA21392BED48CBF4BD1622517C6EA788D8D020111");

        return mplew.getPacket();
    }

    public static byte[] genderNeeded(MapleClient c) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(3);

        if (ServerConstants.����������) {
            System.out.println("genderNeeded--------------------");
        }
        mplew.writeShort(SendPacketOpcode.CHOOSE_GENDER.getValue());
        mplew.writeMapleAsciiString(c.getAccountName());

        return mplew.getPacket();
    }

    public static final byte[] getLoginFailed(final int reason) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(16);

        if (ServerConstants.����������) {
            System.out.println("getLoginFailed--------------------");
        }
        /**
         * * * * *
         * 3�����֤��ɾ�����赲 4������ȷ������ 5������һ��ע������֤ 6��ϵͳ���� 7���ѵ�¼ 8��ϵͳ���� 9��ϵͳ����
         * 10�����ܴ�����ô������ 11��ֻ��20�����ϵ��û�����ʹ�ø�Ƶ�� 13���޷���¼��֪ʶ��Ȩ 14����������ػ������Ϣ����ֵĺ�����ť
         * 15�����������뺫����ť�� 16����ͨ�������ʼ���֤�����ʻ��� 17����������ػ������Ϣ 21����ͨ�������ʼ���֤�����ʻ���
         * 23�����Э�� 25��ŷ�޷�Ҷŷ�޹��� 27��һЩ��ֵ������Ŀͻ���֪ͨ������Ϊ���ð汾 32��֪ʶ��Ȩ���� 84��������ͨ����վ-->
         * 0x07 recv��Ӧ00 / 01 /
         */
        mplew.writeShort(SendPacketOpcode.LOGIN_STATUS.getValue());
        mplew.writeInt(reason);
        mplew.writeShort(0);

        return mplew.getPacket();
    }

    public static final byte[] getPermBan(final byte reason) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(16);

        if (ServerConstants.����������) {
            System.out.println("getPermBan--------------------");
        }
        mplew.writeShort(SendPacketOpcode.LOGIN_STATUS.getValue());
        mplew.writeShort(2); // �ʻ��Ǳ���ֹ��
        mplew.write(0);
        mplew.write(reason);
        mplew.write(HexTool.getByteArrayFromHexString("01 01 01 01 00"));

        return mplew.getPacket();
    }

    public static final byte[] getTempBan(final long timestampTill, final byte reason) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(17);

        if (ServerConstants.����������) {
            System.out.println("getTempBan--------------------");
        }
        mplew.writeShort(SendPacketOpcode.LOGIN_STATUS.getValue());
        mplew.write(2);
        mplew.write(HexTool.getByteArrayFromHexString("00 00 00 00 00"));
        mplew.write(reason);
        mplew.writeLong(timestampTill); // Tempban date is handled as a 64-bit long, number of 100NS intervals since 1/1/1601. Lulz.

        return mplew.getPacket();
    }

    public static final byte[] getGenderChanged(final MapleClient client) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();

        if (ServerConstants.����������) {
            System.out.println("getGenderChanged--------------------");
        }
        mplew.writeShort(SendPacketOpcode.GENDER_SET.getValue());
        mplew.write(0);
        mplew.writeMapleAsciiString(client.getAccountName());
        mplew.writeMapleAsciiString(String.valueOf(client.getAccID()));

        return mplew.getPacket();
    }

    public static final byte[] getGenderNeeded(final MapleClient client) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();

        if (ServerConstants.����������) {
            System.out.println("getGenderNeeded--------------------");
        }
        mplew.writeShort(SendPacketOpcode.CHOOSE_GENDER.getValue());
        mplew.writeMapleAsciiString(client.getAccountName());

        return mplew.getPacket();
    }

    //��½��Ϸ����ʾ�ĵ���
    public static final byte[] getAuthSuccessRequest(final MapleClient client) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.LOGIN_STATUS.getValue());
        mplew.write(0);
        mplew.writeInt(client.getAccID());
        mplew.write(client.getGender());
        mplew.writeShort(client.isGm() ? 1 : 0);
        mplew.writeMapleAsciiString(client.getAccountName());
        mplew.write(HexTool.getByteArrayFromHexString("00 00 00 03 01 00 00 00 E2 ED A3 7A FA C9 01"));
        mplew.writeInt(0);
        mplew.writeLong(0L);
        mplew.writeMapleAsciiString(String.valueOf(client.getAccID()));
        mplew.writeMapleAsciiString(client.getAccountName());
        mplew.write(1);
        /*if (gui.Start.ConfigValuesMap.get("��½���濪��") == 0) {
            client.sendPacket(MaplePacketCreator.serverNotice(1, ����ȡ�ض��㲥()));
        }*/
        return mplew.getPacket();
    }

    public static final byte[] deleteCharResponse(final int cid, final int state) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.DELETE_CHAR_RESPONSE.getValue());
        mplew.writeInt(cid);
        mplew.write(state);

        return mplew.getPacket();
    }

    public static final byte[] secondPwError(final byte mode) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(3);

        /*
         * 14 - Invalid password
         * 15 - Second password is incorrect
         */
        if (ServerConstants.����������) {
            System.out.println("secondPwError--------------------");
        }
        mplew.writeShort(SendPacketOpcode.SECONDPW_ERROR.getValue());
        mplew.write(mode);

        return mplew.getPacket();
    }

    //Ƶ��
    public static final byte[] getServerList(final int serverId, final String serverName, final Map<Integer, Integer> channelLoad, final int a) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();

        if (ServerConstants.����������) {
            System.out.println("getServerList--------------------");
        }
        mplew.writeShort(SendPacketOpcode.SERVERLIST.getValue());
        mplew.write(serverId);//serverId // 0 = Aquilla, 1 = bootes, 2 = cass, 3 = delphinus
        mplew.writeMapleAsciiString(serverName);
        mplew.write(a);//mplew.write(LoginServer.getFlag());
        mplew.writeMapleAsciiString(LoginServer.getEventMessage());
        mplew.writeShort(100);
        mplew.writeShort(100);

        int lastChannel = 1;
        Set<Integer> channels = channelLoad.keySet();
        for (int i = 30; i > 0; i--) {
            if (channels.contains(i)) {
                lastChannel = i;
                break;
            }
        }
        mplew.write(lastChannel);
        mplew.writeInt(500);

        int load;
        for (int i = 1; i <= lastChannel; i++) {
            if (channels.contains(i)) {
                load = channelLoad.get(i);
            } else {
                load = 1200;
            }
            mplew.writeMapleAsciiString(serverName + "-" + i);
            mplew.writeInt(load);
            mplew.write(serverId);
            mplew.writeShort(i - 1);
        }
        //mplew.writeShort(0);
        mplew.writeShort(GameConstants.getBalloons().size());
        for (Balloon balloon : GameConstants.getBalloons()) {
            mplew.writeShort(balloon.nX);
            mplew.writeShort(balloon.nY);
            mplew.writeMapleAsciiString(balloon.sMessage);
        }
        //System.err.println(HexTool.toString(mplew.getPacket().getBytes()));

        return mplew.getPacket();
    }

    public static final byte[] getEndOfServerList() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();

        if (ServerConstants.����������) {
            System.out.println("getEndOfServerList--------------------");
        }
        mplew.writeShort(SendPacketOpcode.SERVERLIST.getValue());
        mplew.write(0xFF);

        return mplew.getPacket();
    }

    public static final byte[] getServerStatus(final int status) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();

        if (ServerConstants.����������) {
            System.out.println("getServerStatus--------------------");
        }
        /*	 * 0 - Normal
         * 1 - Highly populated
         * 2 - Full*/
        mplew.writeShort(SendPacketOpcode.SERVERSTATUS.getValue());
        mplew.writeShort(status);

        return mplew.getPacket();
    }

    public static final byte[] getCharList(final boolean secondpw, final List<MapleCharacter> chars, int charslots) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();

        if (ServerConstants.����������) {
            System.out.println("getCharList--------------------");
        }
        mplew.writeShort(SendPacketOpcode.CHARLIST.getValue());
        mplew.write(0);
        mplew.writeInt(0); // 40 42 0F 00
        mplew.write(chars.size()); // 1

        for (final MapleCharacter chr : chars) {
            addCharEntry(mplew, chr, !chr.isGM() && chr.getLevel() >= 10, false);
        }
        mplew.writeShort(3); // second pw request
        mplew.writeInt(charslots);

        return mplew.getPacket();
    }

    public static final byte[] addNewCharEntry(final MapleCharacter chr, final boolean worked) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();

        if (ServerConstants.����������) {
            System.out.println("addNewCharEntry--------------------");
        }
        mplew.writeShort(SendPacketOpcode.ADD_NEW_CHAR_ENTRY.getValue());
        mplew.write(worked ? 0 : 1);
        addCharEntry(mplew, chr, false, false);

        return mplew.getPacket();
    }

    public static final byte[] charNameResponse(final String charname, final boolean nameUsed) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();

        if (ServerConstants.����������) {
            System.out.println("charNameResponse--------------------");
        }
        mplew.writeShort(SendPacketOpcode.CHAR_NAME_RESPONSE.getValue());
        mplew.writeMapleAsciiString(charname);
        mplew.write(nameUsed ? 1 : 0);

        return mplew.getPacket();
    }

    private static final void addCharEntry(final MaplePacketLittleEndianWriter mplew, final MapleCharacter chr, boolean ranking, boolean viewAll) {
        if (ServerConstants.����������) {
            System.out.println("addCharEntry--------------------");
        }
        PacketHelper.addCharStats(mplew, chr);
        PacketHelper.addCharLook(mplew, chr, true, viewAll);
        mplew.write(0); //<-- who knows
        if (chr.getJob() == 900) {
            mplew.write(2);
            return;
        }
        /* mplew.write(ranking ? 1 : 0);
        if (ranking) {
            mplew.writeInt(chr.getRank());
            mplew.writeInt(chr.getRankMove());
            mplew.writeInt(chr.getJobRank());
            mplew.writeInt(chr.getJobRankMove());
        }*/
    }
}
