/*
????ϵͳ
 */
package handling.channel.handler;

import java.rmi.RemoteException;
import java.util.Iterator;

import client.MapleCharacter;
import client.MapleClient;
import client.inventory.MaplePet;
import handling.world.World;
import handling.world.guild.*;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;
import tools.data.LittleEndianAccessor;
import tools.packet.PetPacket;

public class GuildHandler {

    public static final void DenyGuildRequest(final String from, final MapleClient c) {
        final MapleCharacter cfrom = c.getChannelServer().getPlayerStorage().getCharacterByName(from);
        if (cfrom != null) {
            cfrom.getClient().sendPacket(MaplePacketCreator.denyGuildInvitation(c.getPlayer().getName()));
        }
    }

    private static final boolean isGuildNameAcceptable(final String name) {
        if (name.length() > 15) {
            return false;
        }
        if (name.length() < 3) {
            return false;
        }
        return true;
    }

    private static final void respawnPlayer(final MapleCharacter mc) {
        mc.getMap().broadcastMessage(mc, MaplePacketCreator.removePlayerFromMap(mc.getId()), false);
        mc.getMap().broadcastMessage(mc, MaplePacketCreator.spawnPlayerMapobject(mc), false);
 
    }

    private static final class Invited {

        public String name;
        public int gid;
        public long expiration;

        public Invited(final String n, final int id) {
            name = n.toLowerCase();
            gid = id;
            expiration = System.currentTimeMillis() + 60 * 60 * 1000; // 1 hr expiration
        }

        @Override
        public final boolean equals(Object other) {
            if (!(other instanceof Invited)) {
                return false;
            }
            Invited oth = (Invited) other;
            return (gid == oth.gid && name.equals(oth.name));
        }
    }
    private static final java.util.List<Invited> invited = new java.util.LinkedList<Invited>();
    private static long nextPruneTime = System.currentTimeMillis() + 20 * 60 * 1000;

    public static final void Guild(final LittleEndianAccessor slea, final MapleClient c) {
        if (System.currentTimeMillis() >= nextPruneTime) {
            Iterator<Invited> itr = invited.iterator();
            Invited inv;
            while (itr.hasNext()) {
                inv = itr.next();
                if (System.currentTimeMillis() >= inv.expiration) {
                    itr.remove();
                }
            }
            nextPruneTime = System.currentTimeMillis() + 20 * 60 * 1000;
        }
        
        switch (slea.readByte()) {
            case 0x02: // Create guild
                if (c.getPlayer().getGuildId() > 0 || c.getPlayer().getMapId() != 200000301) {
                    c.getPlayer().dropMessage(1, "?㲻???ڴ???һ???µļ???.");
                    return;
                } else if (c.getPlayer().getMeso() < 15000000) {
                    c.getPlayer().dropMessage(1, "???Ľ??Ҳ??????޷?????????");
                    return;
                }
                final String guildName = slea.readMapleAsciiString();

                if (!isGuildNameAcceptable(guildName)) {
                    c.getPlayer().dropMessage(1, "?????????????Ʋ?????ʹ??.");
                    return;
                }
                int guildId = World.Guild.createGuild(c.getPlayer().getId(), guildName);
                if (guildId == 0) {
                    c.sendPacket(MaplePacketCreator.genericGuildMessage((byte) 0x1c));
                    return;
                }
                c.getPlayer().gainMeso(-15000000, true, false, true);
                c.getPlayer().setGuildId(guildId);
                c.getPlayer().setGuildRank((byte) 1);
                c.getPlayer().saveGuildStatus();
                c.sendPacket(MaplePacketCreator.showGuildInfo(c.getPlayer()));
                World.Guild.setGuildMemberOnline(c.getPlayer().getMGC(), true, c.getChannel());
                c.getPlayer().dropMessage(1, "??ϲ???ɹ?????һ??????.");
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "??ϲ???? < "+guildName+" > ?? " + CurrentReadable_Time() + " ??????,???????? " + c.getPlayer().getName().toString()));
                
                respawnPlayer(c.getPlayer());
                break;
            case 0x05: // invitation
                if (c.getPlayer().getGuildId() <= 0 || c.getPlayer().getGuildRank() > 2) { // 1 == guild master, 2 == jr
                    return;
                }
                String name = slea.readMapleAsciiString();
                final MapleGuildResponse mgr = MapleGuild.sendInvite(c, name);

                if (mgr != null) {
                    c.sendPacket(mgr.getPacket());
                } else {
                    Invited inv = new Invited(name, c.getPlayer().getGuildId());
                    if (!invited.contains(inv)) {
                        invited.add(inv);
                    }
                }
                break;
            case 0x06: // accepted guild invitation
                if (c.getPlayer().getGuildId() > 0) {
                    return;
                }
                guildId = slea.readInt();
                int cid = slea.readInt();

                if (cid != c.getPlayer().getId()) {
                    return;
                }
                name = c.getPlayer().getName().toLowerCase();
                Iterator<Invited> itr = invited.iterator();

                while (itr.hasNext()) {
                    Invited inv = itr.next();
                    if (guildId == inv.gid && name.equals(inv.name)) {
                        c.getPlayer().setGuildId(guildId);
                        c.getPlayer().setGuildRank((byte) 5);
                        itr.remove();
                        int s = World.Guild.addGuildMember(c.getPlayer().getMGC());
                        if (s == 0) {
                            c.getPlayer().dropMessage(1, "????Ҫ?????ļ????Ѿ???Ա??.");
                            c.getPlayer().setGuildId(0);
                            return;
                        }
                        c.sendPacket(MaplePacketCreator.showGuildInfo(c.getPlayer()));
                        final MapleGuild gs = World.Guild.getGuild(guildId);
                        for (byte[] pack : World.Alliance.getAllianceInfo(gs.getAllianceId(), true)) {
                            if (pack != null) {
                                c.sendPacket(pack);
                            }
                        }
                        c.getPlayer().saveGuildStatus();
                        respawnPlayer(c.getPlayer());
                        break;
                    }
                }
                break;
            case 0x07: // leaving

                cid = slea.readInt();
                name = slea.readMapleAsciiString();

                if (cid != c.getPlayer().getId() || !name.equals(c.getPlayer().getName()) || c.getPlayer().getGuildId() <= 0) {
                    return;
                }
                World.Guild.leaveGuild(c.getPlayer().getMGC());
                c.sendPacket(MaplePacketCreator.showGuildInfo(null));//??ʾ????????
                c.sendPacket(MaplePacketCreator.fuckGuildInfo(c.getPlayer()));//?Զ???????ʾ????
                break;
            case 0x08: // Expel
                cid = slea.readInt();
                name = slea.readMapleAsciiString();

                if (c.getPlayer().getGuildRank() > 2 || c.getPlayer().getGuildId() <= 0) {
                    return;
                }
                World.Guild.expelMember(c.getPlayer().getMGC(), name, cid);
                break;
            case 0x0D: // Guild rank titles change
                if (c.getPlayer().getGuildId() <= 0 || c.getPlayer().getGuildRank() != 1) {
                    return;
                }
                String ranks[] = new String[5];
                for (int i = 0; i < 5; i++) {
                    ranks[i] = slea.readMapleAsciiString();
                }

                World.Guild.changeRankTitle(c.getPlayer().getGuildId(), ranks);
                break;
            case 0x0E: // Rank change
                cid = slea.readInt();
                byte newRank = slea.readByte();

                if ((newRank <= 1 || newRank > 5) || c.getPlayer().getGuildRank() > 2 || (newRank <= 2 && c.getPlayer().getGuildRank() != 1) || c.getPlayer().getGuildId() <= 0) {
                    return;
                }

                World.Guild.changeRank(c.getPlayer().getGuildId(), cid, newRank);
                break;
            case 0x0F: // guild emblem change
                if (c.getPlayer().getGuildId() <= 0 || c.getPlayer().getGuildRank() != 1 || c.getPlayer().getMapId() != 200000301) {
                    return;
                }

                if (c.getPlayer().getMeso() < 5000000) {
                    c.getPlayer().dropMessage(1, "???Ľ??Ҳ??????޷?????????ѫ??");
                    return;
                }
                final short bg = slea.readShort();
                final byte bgcolor = slea.readByte();
                final short logo = slea.readShort();
                final byte logocolor = slea.readByte();

                World.Guild.setGuildEmblem(c.getPlayer().getGuildId(), bg, bgcolor, logo, logocolor);

                c.getPlayer().gainMeso(-5000000, true, false, true);
                respawnPlayer(c.getPlayer());
                break;
            case 0x10: // guild notice change
                final String notice = slea.readMapleAsciiString();
                if (notice.length() > 100 || c.getPlayer().getGuildId() <= 0 || c.getPlayer().getGuildRank() > 2) {
                    return;
                }
                World.Guild.setGuildNotice(c.getPlayer().getGuildId(), notice);
                break;
        }
    }
}
