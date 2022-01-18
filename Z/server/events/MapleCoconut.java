/*
��ƿ�ǻ
��Ҭ�ӻ
 */
package server.events;

import client.MapleCharacter;
import java.util.LinkedList;
import java.util.List;
import server.Timer.EventTimer;
import tools.MaplePacketCreator;

public class MapleCoconut extends MapleEvent {

    private List<MapleCoconuts> coconuts = new LinkedList<MapleCoconuts>();
    private int[] coconutscore = new int[2];
    private int countBombing = 0;
    private int countFalling = 0;
    private int countStopped = 0;

    public MapleCoconut(final int channel, final int[] mapid) {
        super(channel, mapid);
    }

    @Override
    public void reset() {
        super.reset();
        resetCoconutScore();
    }

    @Override
    public void unreset() {
        super.unreset();
        resetCoconutScore();
        setHittable(false);
    }

    @Override
    public void onMapLoad(MapleCharacter chr) {
        chr.getClient().sendPacket(MaplePacketCreator.coconutScore(getCoconutScore()));
    }

    public MapleCoconuts getCoconut(int id) {
        return coconuts.get(id);
    }

    public List<MapleCoconuts> getAllCoconuts() {
        return coconuts;
    }

    public void setHittable(boolean hittable) {
        for (MapleCoconuts nut : coconuts) {
            nut.setHittable(hittable);
        }
    }

    public int getBombings() {
        return countBombing;
    }

    public void bombCoconut() {
        countBombing--;
    }

    public int getFalling() {
        return countFalling;
    }

    public void fallCoconut() {
        countFalling--;
    }

    public int getStopped() {
        return countStopped;
    }

    public void stopCoconut() {
        countStopped--;
    }

    public int[] getCoconutScore() { // coconut event
        return coconutscore;
    }

    public int getMapleScore() { // Team Maple, coconut event
        return coconutscore[0];
    }

    public int getStoryScore() { // Team Story, coconut event
        return coconutscore[1];
    }

    public void addMapleScore() { // Team Maple, coconut event
        coconutscore[0]++;
    }

    public void addStoryScore() { // Team Story, coconut event
        coconutscore[1]++;
    }

    public void resetCoconutScore() {
        coconutscore[0] = 0;
        coconutscore[1] = 0;
        countBombing = 80;
        countFalling = 1001;
        countStopped = 20;
        coconuts.clear();
        for (int i = 0; i < 506; i++) {
            coconuts.add(new MapleCoconuts());
        }
    }

    @Override
    public void startEvent() {
        reset();
        setHittable(true);
        getMap(0).broadcastMessage(MaplePacketCreator.serverNotice(5, "���ʼ"));
        getMap(0).broadcastMessage(MaplePacketCreator.hitCoconut(true, 0, 0));
        getMap(0).broadcastMessage(MaplePacketCreator.getClock(360));

        EventTimer.getInstance().schedule(new Runnable() {

            @Override
            public void run() {
                if (getMapleScore() == getStoryScore()) {
                    bonusTime();
                } else if (getMapleScore() > getStoryScore()) {
                    for (MapleCharacter chr : getMap(0).getCharactersThreadsafe()) {
                        if (chr.getCoconutTeam() == 0) {
                            chr.getClient().sendPacket(MaplePacketCreator.showEffect("event/coconut/victory"));
                            chr.getClient().sendPacket(MaplePacketCreator.playSound("Coconut/Victory"));
                        } else {
                            chr.getClient().sendPacket(MaplePacketCreator.showEffect("event/coconut/lose"));
                            chr.getClient().sendPacket(MaplePacketCreator.playSound("Coconut/Failed"));
                        }
                    }
                    warpOut();
                } else {
                    for (MapleCharacter chr : getMap(0).getCharactersThreadsafe()) {
                        if (chr.getCoconutTeam() == 1) {
                            chr.getClient().sendPacket(MaplePacketCreator.showEffect("event/coconut/victory"));
                            chr.getClient().sendPacket(MaplePacketCreator.playSound("Coconut/Victory"));
                        } else {
                            chr.getClient().sendPacket(MaplePacketCreator.showEffect("event/coconut/lose"));
                            chr.getClient().sendPacket(MaplePacketCreator.playSound("Coconut/Failed"));
                        }
                    }
                    warpOut();
                }
            }
        }, 360000);
    }

    public void bonusTime() {
        getMap(0).broadcastMessage(MaplePacketCreator.getClock(120));
        EventTimer.getInstance().schedule(new Runnable() {

            @Override
            public void run() {
                if (getMapleScore() == getStoryScore()) {
                    for (MapleCharacter chr : getMap(0).getCharactersThreadsafe()) {
                        chr.getClient().sendPacket(MaplePacketCreator.showEffect("event/coconut/lose"));
                        chr.getClient().sendPacket(MaplePacketCreator.playSound("Coconut/Failed"));
                    }
                    warpOut();
                } else if (getMapleScore() > getStoryScore()) {
                    for (MapleCharacter chr : getMap(0).getCharactersThreadsafe()) {
                        if (chr.getCoconutTeam() == 0) {
                            chr.getClient().sendPacket(MaplePacketCreator.showEffect("event/coconut/victory"));
                            chr.getClient().sendPacket(MaplePacketCreator.playSound("Coconut/Victory"));
                        } else {
                            chr.getClient().sendPacket(MaplePacketCreator.showEffect("event/coconut/lose"));
                            chr.getClient().sendPacket(MaplePacketCreator.playSound("Coconut/Failed"));
                        }
                    }
                    warpOut();
                } else {
                    for (MapleCharacter chr : getMap(0).getCharactersThreadsafe()) {
                        if (chr.getCoconutTeam() == 1) {
                            chr.getClient().sendPacket(MaplePacketCreator.showEffect("event/coconut/victory"));
                            chr.getClient().sendPacket(MaplePacketCreator.playSound("Coconut/Victory"));
                        } else {
                            chr.getClient().sendPacket(MaplePacketCreator.showEffect("event/coconut/lose"));
                            chr.getClient().sendPacket(MaplePacketCreator.playSound("Coconut/Failed"));
                        }
                    }
                    warpOut();
                }
            }
        }, 120000);

    }

    public void warpOut() {
        setHittable(false);
        EventTimer.getInstance().schedule(new Runnable() {

            @Override
            public void run() {
                for (MapleCharacter chr : getMap(0).getCharactersThreadsafe()) {
                    if ((getMapleScore() > getStoryScore() && chr.getCoconutTeam() == 0) || (getStoryScore() > getMapleScore() && chr.getCoconutTeam() == 1)) {
                        //���Ҭ�ӱ���
                        hddyzbs(chr);
                    }
                    warpBack(chr);
                }
                unreset();
            }
        }, 12000);
    }

    public static class MapleCoconuts {

        private int hits = 0;
        private boolean hittable = false;
        private boolean stopped = false;
        private long hittime = System.currentTimeMillis();

        public void hit() {
            this.hittime = System.currentTimeMillis() + 1000; // test
            hits++;
        }

        public int getHits() {
            return hits;
        }

        public void resetHits() {
            hits = 0;
        }

        public boolean isHittable() {
            return hittable;
        }

        public void setHittable(boolean hittable) {
            this.hittable = hittable;
        }

        public boolean isStopped() {
            return stopped;
        }

        public void setStopped(boolean stopped) {
            this.stopped = stopped;
        }

        public long getHitTime() {
            return hittime;
        }
    }
}