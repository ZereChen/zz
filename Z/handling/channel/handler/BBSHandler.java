package handling.channel.handler;

import client.MapleClient;
import handling.world.World;
import handling.world.guild.MapleBBSThread;
import java.rmi.RemoteException;
import java.util.List;
import tools.MaplePacketCreator;
import tools.data.LittleEndianAccessor;

public class BBSHandler {

    private static final String correctLength(final String in, final int maxSize) {
        if (in.length() > maxSize) {
            return in.substring(0, maxSize);
        }
        return in;
    }

    public static final void BBSOperatopn(final LittleEndianAccessor slea, final MapleClient c) {
        if (c.getPlayer().getGuildId() <= 0) {
            return; // �����BBS��HAXʱ������
        }
        int localthreadid = 0;
        final byte action = slea.readByte();
        switch (action) {
            case 0: //����һ����ְλ
                final boolean bEdit = slea.readByte() > 0;
                if (bEdit) {
                    localthreadid = slea.readInt();
                }
                final boolean bNotice = slea.readByte() > 0;
                final String title = correctLength(slea.readMapleAsciiString(), 25);
                String text = correctLength(slea.readMapleAsciiString(), 600);
                final int icon = slea.readInt();
                if (icon >= 0x64 && icon <= 0x6a) {
                    if (!c.getPlayer().haveItem(5290000 + icon - 0x64, 1, false, true)) {
                        return; // HAX��ʹ��һ��NXͼ�꣬��/��û��
                    }
                } else if (icon < 0 || icon > 2) {
                    //HAX��ʹ����Чͼ��
                    return; 
                }
                if (!bEdit) {
                    newBBSThread(c, title, text, icon, bNotice);
                } else {
                    editBBSThread(c, title, text, icon, localthreadid);
                }
                break;
            //ɾ���߳�
            case 1:
                localthreadid = slea.readInt();
                deleteBBSThread(c, localthreadid);
                break;
            //�б��߳�
            case 2:
                int start = slea.readInt();
                listBBSThreads(c, start * 10);
                break;
            //�г��߳�+Ӧ�𣬽�����ID��int��
            case 3:
                localthreadid = slea.readInt();
                displayThread(c, localthreadid);
                break;
            //�ظ�
            case 4:
                localthreadid = slea.readInt();
                text = correctLength(slea.readMapleAsciiString(), 25);
                newBBSReply(c, localthreadid, text);
                break;
            //ɾ���ظ�
            case 5:
                localthreadid = slea.readInt();
                int replyid = slea.readInt();
                deleteBBSReply(c, localthreadid, replyid);
                break;
        }
    }

    private static void listBBSThreads(MapleClient c, int start) {
        if (c.getPlayer().getGuildId() <= 0) {
            return;
        }
        c.sendPacket(MaplePacketCreator.BBSThreadList(World.Guild.getBBS(c.getPlayer().getGuildId()), start));
    }

    private static final void newBBSReply(final MapleClient c, final int localthreadid, final String text) {
        if (c.getPlayer().getGuildId() <= 0) {
            return;
        }
        World.Guild.addBBSReply(c.getPlayer().getGuildId(), localthreadid, text, c.getPlayer().getId());
        displayThread(c, localthreadid);
    }

    private static final void editBBSThread(final MapleClient c, final String title, final String text, final int icon, final int localthreadid) {
        if (c.getPlayer().getGuildId() <= 0) {
            return; // expelled while viewing?
        }
        World.Guild.editBBSThread(c.getPlayer().getGuildId(), localthreadid, title, text, icon, c.getPlayer().getId(), c.getPlayer().getGuildRank());
        displayThread(c, localthreadid);
    }

    private static final void newBBSThread(final MapleClient c, final String title, final String text, final int icon, final boolean bNotice) {
        if (c.getPlayer().getGuildId() <= 0) {
            return; // expelled while viewing?
        }
        displayThread(c, World.Guild.addBBSThread(c.getPlayer().getGuildId(), title, text, icon, bNotice, c.getPlayer().getId()));
    }

    private static final void deleteBBSThread(final MapleClient c, final int localthreadid) {
        if (c.getPlayer().getGuildId() <= 0) {
            return;
        }
        World.Guild.deleteBBSThread(c.getPlayer().getGuildId(), localthreadid, c.getPlayer().getId(), (int) c.getPlayer().getGuildRank());
    }

    private static final void deleteBBSReply(final MapleClient c, final int localthreadid, final int replyid) {
        if (c.getPlayer().getGuildId() <= 0) {
            return;
        }

        World.Guild.deleteBBSReply(c.getPlayer().getGuildId(), localthreadid, replyid, c.getPlayer().getId(), (int) c.getPlayer().getGuildRank());
        displayThread(c, localthreadid);
    }

    private static final void displayThread(final MapleClient c, final int localthreadid) {
        if (c.getPlayer().getGuildId() <= 0) {
            return;
        }
        final List<MapleBBSThread> bbsList = World.Guild.getBBS(c.getPlayer().getGuildId());
        if (bbsList != null) {
            for (MapleBBSThread t : bbsList) {
                if (t != null && t.localthreadID == localthreadid) {
                    c.sendPacket(MaplePacketCreator.showThread(t));
                }
            }
        }
    }
}
