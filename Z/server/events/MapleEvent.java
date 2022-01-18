package server.events;

import client.MapleCharacter;
import database.DatabaseConnection;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import handling.world.World;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import server.MapleInventoryManipulator;
import static gui.QQMsgServer.sendMsgToQQGroup;
import scripting.NPCScriptManager;
import server.RandomRewards;
import server.Randomizer;
import server.Timer.EventTimer;
import server.maps.MapleMap;
import server.maps.SavedLocationType;
import tools.FileoutputUtil;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;

public abstract class MapleEvent {

    protected int[] mapid;
    protected int channel;
    protected boolean isRunning = false;

    public MapleEvent(final int channel, final int[] mapid) {
        this.channel = channel;
        this.mapid = mapid;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public MapleMap getMap(final int i) {
        return getChannelServer().getMapFactory().getMap(mapid[i]);
    }

    public ChannelServer getChannelServer() {
        return ChannelServer.getInstance(channel);
    }

    public void broadcast(final byte[] packet) {
        for (int i = 0; i < mapid.length; i++) {
            getMap(i).broadcastMessage(packet);
        }
    }

    public void givePrize(final MapleCharacter chr) {

        final int reward = RandomRewards.getInstance().getEventReward();
        if (reward == 0) {
            chr.gainMeso(200000, true, false, false);
            chr.dropMessage(5, "���� 2000000 ð�ձ�");
        } else if (reward == 1) {
            chr.gainMeso(500000, true, false, false);
            chr.dropMessage(5, "���� 500000 ð�ձ�");
        } else if (reward == 2) {
            chr.modifyCSPoints(0, 1000, false);
            // chr.gainMeso(666666, true, false, false);
            chr.dropMessage(5, "���� 1000 ���");
        } else if (reward == 3) {
            chr.addFame(10);
            chr.dropMessage(5, "���� 10 ����");
        }

        // final int quantity = (max_quantity > 1 ? Randomizer.nextInt(max_quantity) : 0) + 1;
        if (MapleInventoryManipulator.checkSpace(chr.getClient(), 4032226, 20, "")) {
            MapleInventoryManipulator.addById(chr.getClient(), 4032226, (short) 20, (byte) 0);
            chr.dropMessage(5, "���� 20 ���ƽ�����");
        } else {
            // givePrize(chr); //do again until they get
            chr.gainMeso(100000, true, false, false);
            chr.dropMessage(5, "�����㱳�����ˡ�����ֻ�ܸ�����ð�ձң�");
        }

        /*else {
            int max_quantity = 1;
            switch (reward) {
                case 5062000:
                    max_quantity = 3;
                    break;
                case 5220000:
                    max_quantity = 25;
                    break;
                case 4031307:
                case 5050000:
                    max_quantity = 5;
                    break;
                case 2022121:
                    max_quantity = 10;
                    break;
            }
            final int quantity = (max_quantity > 1 ? Randomizer.nextInt(max_quantity) : 0) + 1;
            if (MapleInventoryManipulator.checkSpace(chr.getClient(), reward, quantity, "")) {
                MapleInventoryManipulator.addById(chr.getClient(), reward, (short) quantity, (byte) 0);
            } else {
                // givePrize(chr); //do again until they get
                chr.gainMeso(100000, true, false, false);
                chr.dropMessage(5, "���Ӫ� 100000 ����");
            }
            //5062000 = 1-3
            //5220000 = 1-25
            //5050000 = 1-5
            //2022121 = 1-10
            //4031307 = 1-5
        }*/
    }

    public void hdtxqbs(final MapleCharacter chr) {
        NPCScriptManager.getInstance().dispose(chr.getClient());
        NPCScriptManager.getInstance().start(chr.getClient(), 9000011, 1);
    }

    public void hdxgdbs(final MapleCharacter chr) {
        NPCScriptManager.getInstance().dispose(chr.getClient());
        NPCScriptManager.getInstance().start(chr.getClient(), 9000011, 2);
    }

    public void hddyzbs(final MapleCharacter chr) {
        NPCScriptManager.getInstance().dispose(chr.getClient());
        NPCScriptManager.getInstance().start(chr.getClient(), 9000011, 3);
    }

    public void finished(MapleCharacter chr) { //most dont do shit here
    }

    public void onMapLoad(MapleCharacter chr) { //most dont do shit here
    }

    public void startEvent() {
    }

    public void warpBack(MapleCharacter chr) {
        int map = chr.getSavedLocation(SavedLocationType.EVENT);
        if (map <= -1) {
            map = 104000000;
        }
        final MapleMap mapp = chr.getClient().getChannelServer().getMapFactory().getMap(map);
        chr.changeMap(mapp, mapp.getPortal(0));
    }

    public void reset() {
        isRunning = true;
    }

    public void unreset() {
        isRunning = false;
    }

    public static final void setEvent(final ChannelServer cserv, final boolean auto) {
        if (auto) {
            for (MapleEventType t : MapleEventType.values()) {
                final MapleEvent e = cserv.getEvent(t);
                if (e.isRunning) {
                    for (int i : e.mapid) {
                        if (cserv.getEvent() == i) {
                            e.broadcast(MaplePacketCreator.serverNotice(0, "�������ʼ����Ⱥ�"));
                            e.broadcast(MaplePacketCreator.getClock(60));
                            EventTimer.getInstance().schedule(new Runnable() {
                                public void run() {
                                    e.startEvent();
                                }
                            }, 60000);
                            break;
                        }
                    }
                }
            }
        }
        cserv.setEvent(-1);
    }

    public static final void mapLoad(final MapleCharacter chr, final int channel) {
        if (chr == null) {
            return;
        } //o_o
        for (MapleEventType t : MapleEventType.values()) {
            final MapleEvent e = ChannelServer.getInstance(channel).getEvent(t);
            if (e.isRunning) {
                if (chr.getMapId() == 109050000) { //finished map
                    e.finished(chr);
                }
                for (int i : e.mapid) {
                    if (chr.getMapId() == i) {
                        e.onMapLoad(chr);
                    }
                }
            }
        }
    }

    public static final void onStartEvent(final MapleCharacter chr) {
        for (MapleEventType t : MapleEventType.values()) {
            final MapleEvent e = chr.getClient().getChannelServer().getEvent(t);
            if (e.isRunning) {
                for (int i : e.mapid) {
                    if (chr.getMapId() == i) {
                        e.startEvent();
                        chr.dropMessage(5, String.valueOf(t) + " ���ʼ");
                    }
                }
            }
        }
    }

    public static final String scheduleEvent(final MapleEventType event, final ChannelServer cserv) {
        if (cserv.getEvent() != -1 || cserv.getEvent(event) == null) {
            return "�Ļ�Ѿ�����ֹ������.";
        }
        for (int i : cserv.getEvent(event).mapid) {
            if (cserv.getMapFactory().getMap(i).getCharactersSize() > 0) {
                return "�û�Ѿ���ִ����.";
            }
        }
        cserv.setEvent(cserv.getEvent(event).mapid[0]);
        cserv.getEvent(event).reset();
        World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, cserv.getChannel(), "[�] : " + cserv.getChannel() + " Ƶ�� 3 ���Ӻ�׼������ [" + String.valueOf(event) + "] �,��Ҫ�μӵ������ӻ�򵼴��μӡ�"));
        sendMsgToQQGroup("[�����] : " + cserv.getChannel() + " Ƶ�� 3 ���Ӻ�׼������ " + String.valueOf(event) + " ,�μӵ���Ҵӻ�򵼴��μӡ�");
        MapleParty.ѩ���� = 0;
        return "";
    }
}
