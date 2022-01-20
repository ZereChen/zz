package handling.netty;

import client.MapleClient;
import com.mysql.jdbc.log.LogUtils;
import constants.GameConstants;
import constants.LogConstants;
import constants.ServerConstants;
import handling.RecvPacketOpcode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.AttributeKey;
import java.nio.ByteBuffer;
import java.util.List;

import server.Randomizer;
import tools.MapleAESOFB;
import tools.MapleCustomEncryption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.FileoutputUtil;
import tools.HexTool;
import tools.data.ByteArrayByteStream;
import tools.data.LittleEndianAccessor;

import static abc.Game.检测客户端版本1;

public class MaplePacketDecoder extends ByteToMessageDecoder {

    public static final AttributeKey<DecoderState> DECODER_STATE_KEY = AttributeKey.valueOf(MaplePacketDecoder.class.getName() + ".STATE");

    private static Logger log = LoggerFactory.getLogger(MaplePacketDecoder.class);

    public static class DecoderState {

        public int packetlength = -1;
    }

    /**
     * 将int数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。  和bytesToInt2（）配套使用
     */
    public static byte[] intToBytes(int value)
    {
        byte[] src = new byte[4];
        src[0] = (byte) ((value>>24) & 0xFF);
        src[1] = (byte) ((value>>16)& 0xFF);
        src[2] = (byte) ((value>>8)&0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    public static int bytesToInt(byte[] src, int offset) {
        int value;
        value = (int) ( ((src[offset] & 0xFF)<<24)
                |((src[offset+1] & 0xFF)<<16)
                |((src[offset+2] & 0xFF)<<8)
                |(src[offset+3] & 0xFF));
        return value;
    }
    private static final String HexStr = "0123456789abcdef";

    public static byte[] hexToByteArr(String hexStr) {
        char[] charArr = hexStr.replace(" ","").toCharArray();
        byte btArr[] = new byte[charArr.length / 2];
        int index = 0;
        for (int i = 0; i < charArr.length; i++) {
            int highBit = HexStr.indexOf(charArr[i]);
            int lowBit = HexStr.indexOf(charArr[++i]);
            btArr[index] = (byte) (highBit << 4 | lowBit);
            index++;
        }
        return btArr;
    }


    public static void main(String[] args) {
//        final byte serverRecv[] = new byte[]{70, 114, 12, (byte) Randomizer.nextInt(255)};
        final byte serverSend[] = new byte[]{82, 48, 120, (byte) Randomizer.nextInt(255)};
//        final byte ivRecv[] = ServerConstants.Use_Fixed_IV ? new byte[]{9, 0, 0x5, 0x5F} : serverRecv;
        final byte ivSend[] = ServerConstants.Use_Fixed_IV ? new byte[]{1, 0x5F, 4, 0x3F} : serverSend;
        MapleAESOFB revice = new MapleAESOFB(ivSend, (short) (0xFFFF - 检测客户端版本1));

        String str = "10 A3 18 A3 9D 65 58 04 D7 27 C9 EB";
//        byte[] originData = hexToByteArr(str);
        byte[] originData = new byte[13];
        originData[0] = (byte) 0xBA;
        originData[1] = (byte) 0xC8;
        originData[2] = (byte) 0xBE;
        originData[3] = (byte) 0xC8;
        originData[4] = (byte) 0x9D;
        int packetHeader = bytesToInt(originData,0);
        if (!revice.checkPacket(packetHeader)) {
            return;
        }

        int packetlength = MapleAESOFB.getPacketLength(packetHeader);
        System.out.println("packetlength=" + packetlength);
        byte[] decryptedPacket = new byte[packetlength];

        System.arraycopy(originData, 4, decryptedPacket, 0, packetlength);
        revice.crypt(decryptedPacket);
        MapleCustomEncryption.decryptData(decryptedPacket);
        System.out.println();
    }
    @Override
    protected void decode(ChannelHandlerContext chc, ByteBuf in, List<Object> message) throws Exception {
        final MapleClient client = (MapleClient) chc.channel().attr(MapleClient.CLIENT_KEY).get();
        final DecoderState decoderState = (DecoderState) chc.channel().attr(DECODER_STATE_KEY).get();

        final MapleAESOFB receive_crypto = client.getReceiveCrypto();
        if (in.readableBytes() >= 4 && decoderState.packetlength == -1) {
            int packetHeader = in.readInt();
            if (!receive_crypto.checkPacket(packetHeader)) {
//                chc.channel().close();
//                return;
            }
            decoderState.packetlength = MapleAESOFB.getPacketLength(packetHeader);
        } else if (in.readableBytes() < 4 && decoderState.packetlength == -1) {
            log.trace("解码…没有足够的数据/就是所谓的包不完整");
            return;
        }

        if (in.readableBytes() >= decoderState.packetlength) {//079
            byte decryptedPacket[] = new byte[decoderState.packetlength];
            in.readBytes(decryptedPacket);
            decoderState.packetlength = -1;

            receive_crypto.crypt(decryptedPacket);
            MapleCustomEncryption.decryptData(decryptedPacket);
            message.add(decryptedPacket);
            if (ServerConstants.封包显示) {
                int packetLen = decryptedPacket.length;
                int pHeader = readFirstShort(decryptedPacket);
                String op = lookupSend(pHeader);
                boolean show = true;
                switch (op) {
                    case "PONG":
                    case "NPC_ACTION":
                    case "MOVE_LIFE":
                    case "MOVE_PLAYER":
                    case "MOVE_ANDROID":
                    case "MOVE_SUMMON":
                    case "AUTO_AGGRO":
                    case "HEAL_OVER_TIME":
                    case "BUTTON_PRESSED":
                    case "STRANGE_DATA":
                        show = false;
                }
                String Send = LogConstants.CLIENT + "发送: op=" + op + ", packetlength=" + packetLen
                        + ", 最新发送iv=[" +  HexTool.toString(receive_crypto.getIv()) + "]\r\n";

                if (packetLen <= 3000) {
                    String SendTo = Send + " 返回后的bytes：[" + HexTool.toString(decryptedPacket) +"]\r\n";
                    //log.info(HexTool.toString(decryptedPacket) + "客户端发送");
                    if (show) {
                        //FileoutputUtil.packetLog("Load\\log\\客户端封包.log", SendTo);
                        System.out.println("++" + SendTo);
                    }
                    String SendTos = "\r\n时间：" + FileoutputUtil.CurrentReadable_Time() + "  ";
                    if (op.equals("UNKNOWN")) {
                        //FileoutputUtil.packetLog("Load\\log\\未知客服端封包.log", SendTos + SendTo);
                    }
                } else {
                    log.info(HexTool.toString(new byte[]{decryptedPacket[0], decryptedPacket[1]}) + "...");
                }
            }
            return;
        }
        /*
         * if (in.remaining() >= decoderState.packetlength) { final byte
         * decryptedPacket[] = new byte[decoderState.packetlength];
         * in.get(decryptedPacket, 0, decoderState.packetlength);
         * decoderState.packetlength = -1;
         *
         * client.getReceiveCrypto().crypt(decryptedPacket); //
         * MapleCustomEncryption.decryptData(decryptedPacket);
         * out.write(decryptedPacket); return true; }
         */

        return;
    }

    private String lookupSend(int val) {
        for (RecvPacketOpcode op : RecvPacketOpcode.values()) {
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
