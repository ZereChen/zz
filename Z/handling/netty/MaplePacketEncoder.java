package handling.netty;

import client.MapleClient;
import constants.LogConstants;
import constants.ServerConstants;
import handling.SendPacketOpcode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import java.nio.ByteBuffer;

import server.Randomizer;
import tools.MapleAESOFB;
import tools.MapleCustomEncryption;

import java.util.concurrent.locks.Lock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.FileoutputUtil;
import tools.HexTool;
import tools.data.ByteArrayByteStream;
import tools.data.LittleEndianAccessor;

import static abc.Game.���ͻ��˰汾1;

public class MaplePacketEncoder extends MessageToByteEncoder<Object> {

    private static Logger log = LoggerFactory.getLogger(MaplePacketEncoder.class);


    public static void main(String[] args) {
        System.out.println(HexTool.toString(MapleAESOFB.getPacketHeader(4,(short)20224, new byte[]{0x00, 0x00, (byte) 0x45, (byte) 0x78})));
//        final byte serverRecv[] = new byte[]{70, 114, 12, (byte) Randomizer.nextInt(255)};
//        final byte serverSend[] = new byte[]{82, 48, 120, (byte) Randomizer.nextInt(255)};
//        final byte ivRecv[] = ServerConstants.Use_Fixed_IV ? new byte[]{9, 0, 0x5, 0x5F} : serverRecv;
//        final byte ivSend[] = ServerConstants.Use_Fixed_IV ? new byte[]{1, 0x5F, 4, 0x3F} : serverSend;
//        final MapleAESOFB send_crypto = new MapleAESOFB(ivSend, (short) (0xFFFF - ���ͻ��˰汾1));
//        final MapleAESOFB receive_crypto = new MapleAESOFB(ivRecv, ���ͻ��˰汾1);
//
//        String str = "10 A3 18 A3 9D 65 58 04 D7 27 C9 EB";
//        byte[] originData = new byte[13];
//        originData[0] = (byte) 0x10;
//        originData[1] = (byte) 0xA3;
//        originData[2] = (byte) 0x18;
//        originData[3] = (byte) 0xA3;
//        originData[4] = (byte) 0x9D;
//        originData[5] = (byte) 0x65;
//        originData[6] = (byte) 0x58;
//        originData[7] = (byte) 0x04;
//        originData[8] = (byte) 0xD7;
//        originData[9] = (byte) 0x27;
//        originData[10] = (byte) 0xC9;
//        originData[11] = (byte) 0xEB;
//
//        byte[] header = send_crypto.getPacketHeader(4);
//        System.arraycopy(header, 0, originData, 0, 4);
//        int packetlength = MapleAESOFB.getPacketLength(MaplePacketDecoder.bytesToInt(originData,0));
//        System.out.println("packetlength=" + packetlength);
//
//        if (!receive_crypto.checkPacket(packetlength)) {
//            System.out.println("FAILED");
//        }
    }

    @Override
    protected void encode(ChannelHandlerContext chc, Object message, ByteBuf buffer) throws Exception {
        final MapleClient client = (MapleClient) chc.channel().attr(MapleClient.CLIENT_KEY).get();

        if (client != null) {
            final MapleAESOFB send_crypto = client.getSendCrypto();

            final byte[] inputInitialPacket = ((byte[]) message);
            final byte[] unencrypted = new byte[inputInitialPacket.length];
            System.arraycopy(inputInitialPacket, 0, unencrypted, 0, inputInitialPacket.length); // Copy the input > "unencrypted"
            final byte[] ret = new byte[unencrypted.length + 4]; // Create new bytes with length = "unencrypted" + 4

            final Lock mutex = client.getLock();
            mutex.lock();
            final byte[] header = send_crypto.getPacketHeader(unencrypted.length);
            try {
                MapleCustomEncryption.encryptData(unencrypted); // Encrypting Data
                send_crypto.crypt(unencrypted);
                System.arraycopy(header, 0, ret, 0, 4);
                System.arraycopy(unencrypted, 0, ret, 4, unencrypted.length);
                buffer.writeBytes(ret);
            } finally {
                mutex.unlock();
            }

            if (ServerConstants.�����ʾ) {
                int packetLen = inputInitialPacket.length;
                int pHeader = readFirstShort(inputInitialPacket);
                String op = lookupRecv(pHeader);
                boolean show = true;
                switch (op) {
                    case "WARP_TO_MAP":
                    case "PING":
                    case "NPC_ACTION":
                    case "UPDATE_STATS":
                    case "MOVE_PLAYER":
                    case "SPAWN_NPC":
                    case "SPAWN_NPC_REQUEST_CONTROLLER":
                    case "REMOVE_NPC":
                    case "MOVE_LIFE":
                    case "MOVE_MONSTER":
                    case "MOVE_MONSTER_RESPONSE":
                    case "SPAWN_MONSTER":
                    case "SPAWN_MONSTER_CONTROL":
                    case "ANDROID_MOVE":
                        show = false;
                }
                String Recv = LogConstants.SERVER + "����: op=" + op + ", packetlength=" + packetLen
                        + ", ���·���iv=[" +  HexTool.toString(send_crypto.getIv()) + "]\r\n";

                if (packetLen <= 50000) {
                    String RecvTo = Recv + " ��ʼbytes��[" + HexTool.toString(inputInitialPacket) + "]\r\n"
                            + " ���ܵ�header:[" + HexTool.toString(header) + "]\r\n"
                            + " ���ܵ�bytes��[" + HexTool.toString(unencrypted) + "]\r\n";
                    if (show) {
                        // FileoutputUtil.packetLog("log\\����˷��.log", RecvTo);
                        System.out.println("++" + RecvTo);
                    } //log.info("����˷���" + "\r\n" + HexTool.toString(inputInitialPacket));
                } else {
                    log.info(HexTool.toString(new byte[]{inputInitialPacket[0], inputInitialPacket[1]}) + " ...");
                }

            }

//            System.arraycopy(unencrypted, 0, ret, 4, unencrypted.length); // Copy the unencrypted > "ret"
//            out.write(IoBuffer.wrap(ret));
        } else { // no client object created yet, send unencrypted (hello)
            byte[] input = (byte[]) message;
            buffer.writeBytes(input);
        }
    }

    private String lookupRecv(int val) {
        for (SendPacketOpcode op : SendPacketOpcode.values()) {
            if (op.getValue() == val) {
                return op.name();
            }
        }
        return "UNKNOWN";
    }

    private int readFirstShort(byte[] arr) {
        return new LittleEndianAccessor(new ByteArrayByteStream(arr)).readShort();
    }
}
