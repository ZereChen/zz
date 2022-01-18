/*
��ͼ���ļ�
 */
package server.maps;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.Calendar;
import client.inventory.Equip;
import client.inventory.IItem;
import client.inventory.Item;
import constants.GameConstants;
import client.MapleBuffStat;
import client.MapleCharacter;
import client.MapleClient;
import client.inventory.MapleInventoryType;
import client.inventory.MaplePet;
import client.status.MonsterStatus;
import client.status.MonsterStatusEffect;
import database.DatabaseConnection;
import static fumo.FumoSkill.FM;
import static gui.QQͨ��.Ⱥ֪ͨ;
import gui.Start;
import static gui.�Ұ��ͨ��.���ͨ��;
import static gui.����BOSS.����BOSS�߳�.�رս���BOSS�߳�;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import handling.world.MaplePartyCharacter;
import handling.world.World;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import server.MapleItemInformationProvider;
import server.MaplePortal;
import server.MapleStatEffect;
import server.Randomizer;
import server.MapleInventoryManipulator;
import server.life.MapleMonster;
import server.life.MapleNPC;
import server.life.MapleLifeFactory;
import server.life.Spawns;
import server.life.SpawnPoint;
import server.life.SpawnPointAreaBoss;
import server.life.MonsterDropEntry;
import server.life.MonsterGlobalDropEntry;
import server.life.MapleMonsterInformationProvider;
import tools.FileoutputUtil;
import tools.MaplePacketCreator;
import tools.packet.PetPacket;
import tools.packet.MobPacket;
import scripting.EventManager;
import server.MapleCarnivalFactory;
import server.MapleCarnivalFactory.MCSkill;
import server.MapleSquad;
import server.MapleSquad.MapleSquadType;
import server.SpeedRunner;
import server.Timer;
import server.Timer.MapTimer;
import server.custom.bossrank.BossRankManager;
import server.events.MapleEvent;
import server.life.*;
import server.maps.MapleNodes.MapleNodeInfo;
import server.maps.MapleNodes.MaplePlatform;
import server.maps.MapleNodes.MonsterPoint;
import pvp.MaplePvp;
import scripting.NPCScriptManager;
import server.custom.bossrank.BossRankInfo;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.Pair;
import tools.packet.MTSCSPacket;

public final class MapleMap {

    private final Map<MapleMapObjectType, LinkedHashMap<Integer, MapleMapObject>> mapobjects;
    private final Map<MapleMapObjectType, ReentrantReadWriteLock> mapobjectlocks;
    private final List<MapleCharacter> characters = new ArrayList<MapleCharacter>();
    private final ReentrantReadWriteLock charactersLock = new ReentrantReadWriteLock();
    private int runningOid = 100000;
    private final Lock runningOidLock = new ReentrantLock();
    private final List<Spawns> monsterSpawn = new ArrayList<Spawns>();
    private final AtomicInteger spawnedMonstersOnMap = new AtomicInteger(0);
    private final Map<Integer, MaplePortal> portals = new HashMap<Integer, MaplePortal>();
    private MapleFootholdTree footholds = null;
    private float monsterRate, recoveryRate;
    private MapleMapEffect mapEffect;
    private byte channel;
    private short decHP = 0, createMobInterval = 9000;
    private int consumeItemCoolTime = 0, protectItem = 0, decHPInterval = 10000, mapid, returnMapId, timeLimit,
            fieldLimit, maxRegularSpawn = 0, fixedMob, forcedReturnMap = 999999999,
            lvForceMove = 0, lvLimit = 0, permanentWeather = 0;
    private boolean town, clock, personalShop, everlast = false, dropsDisabled = false, gDropsDisabled = false,
            soaring = false, squadTimer = false, isSpawns = true;
    private String mapName, streetName, onUserEnter, onFirstUserEnter, bgm, speedRunLeader = "";
    private List<Integer> dced = new ArrayList<Integer>();
    private ScheduledFuture<?> squadSchedule;
    private long speedRunStart = 0, lastSpawnTime = 0, lastHurtTime = 0;
    private MapleNodes nodes;
    private boolean boat;
    private boolean docked;
    private MapleSquadType squad;
    private int fieldType;
    private Map<String, Integer> environment = new LinkedHashMap<String, Integer>();

    public MapleMap(final int mapid, final int channel, final int returnMapId, final float monsterRate) {
        //��ͼ����
        this.mapid = mapid;
        //Ƶ��
        this.channel = (byte) channel;
        //����ӳ��
        this.returnMapId = returnMapId;
        if (this.returnMapId == 910000000) {
            this.returnMapId = mapid;
        }
        this.monsterRate = monsterRate;
        EnumMap<MapleMapObjectType, LinkedHashMap<Integer, MapleMapObject>> objsMap = new EnumMap<MapleMapObjectType, LinkedHashMap<Integer, MapleMapObject>>(MapleMapObjectType.class);
        EnumMap<MapleMapObjectType, ReentrantReadWriteLock> objlockmap = new EnumMap<MapleMapObjectType, ReentrantReadWriteLock>(MapleMapObjectType.class);
        for (MapleMapObjectType type : MapleMapObjectType.values()) {
            objsMap.put(type, new LinkedHashMap<Integer, MapleMapObject>());
            objlockmap.put(type, new ReentrantReadWriteLock());
        }
        mapobjects = Collections.unmodifiableMap(objsMap);
        mapobjectlocks = Collections.unmodifiableMap(objlockmap);
        //��ͼ����();
        //�ͻ�Ա(10);
        //AutoNx();
    }

    public void ��ͼ����() {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (MapleParty.��������ʱ�� == 1) {
                    if (getAllMonstersThreadsafe().size() > 0 && getCharactersSize() == 0) {
                        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                            System.err.println("[�����]" + CurrentReadable_Time() + " : ϵͳ���ڻ��յ�ͼ �� " + getId());
                            cserv.getMapFactory().destroyMap(getId(), true);
                            cserv.getMapFactory().HealMap(getId());
                        }
                    }
                    System.err.println("[�����]" + CurrentReadable_Time() + " : ϵͳ���ڻ��յ�ͼ ��");
                }
            }
        }, 1000 * 30);
    }

    public MapleCharacter getCharacterByName(String id) {
        charactersLock.readLock().lock();
        try {
            for (MapleCharacter mc : characters) {
                if (mc.getName().equalsIgnoreCase(id)) {
                    return mc;
                }
            }
        } finally {
            charactersLock.readLock().unlock();
        }
        return null;
    }

    public void ��ɮ��ͼ����() {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (getId() == 702060000) {
                    if (getAllMonstersThreadsafe().size() > 0 && getCharactersSize() == 0) {
                        ���();
                        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, ";��ɮ�����ѱ����á�"));
                    }
                }
            }
        }, 10 * 6000);
    }

    public void ��ʱ�ٻ���ţ��(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (getMonsterById(2220000) == null) {
                    try {
                        MapleMonster mob1 = MapleLifeFactory.getMonster(2220000);
                        spawnMonsterOnGroundBelow(mob1, new Point(439, 185));
                    } catch (Exception e) {
                    }
                }
            }
        }, 1000 * 60 * time);
    }

    public void ��ʱ�ٻ���ͨ����(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (getAllMonstersThreadsafe().isEmpty()) {
                    try {
                        if (getChannel() != 1) {//8800100
                            spawnZakum(-10, -215);
                            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, ";�����ڼ�̨�����ˡ�"));
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }, 1000 * 60 * time);
    }

    public void ��ʱ�ٻ���������(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (getAllMonstersThreadsafe().isEmpty()) {
                    //if (getMonsterById(8810026) == null) {
                    try {
                        if (getChannel() != 1) {
                            MapleMonster mob1 = MapleLifeFactory.getMonster(8810026);
                            spawnMonsterOnGroundBelow(mob1, new Point(71, 260));
                            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, ";�������������ˡ�"));
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }, 1000 * 60 * time);
    }

    public void ��ʱ�ٻ�ʱ����(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (getId() == 270050100) {
                    if (getAllMonstersThreadsafe().isEmpty()) {
                        if (getMonsterById(8820008) == null) {
                            try {
                                if (getChannel() != 1) {
                                    MapleMonster mob1 = MapleLifeFactory.getMonster(8820008);
                                    spawnMonsterOnGroundBelow(mob1, new Point(2, -42));
                                }
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
        }, 1000 * 60 * time);
    }

    public void ��ʱ�ٻ������(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (getId() == 105100300) {
                    //if (getAllMonstersThreadsafe().isEmpty()) {
                    if (getMonsterById(8830000) == null && getMonsterById(8830001) == null && getMonsterById(8830002) == null) {
                        try {
                            if (getChannel() != 1) {
                                MapleMonster mob1 = MapleLifeFactory.getMonster(8830000);
                                MapleMonster mob2 = MapleLifeFactory.getMonster(8830001);
                                MapleMonster mob3 = MapleLifeFactory.getMonster(8830002);
                                spawnMonsterOnGroundBelow(mob1, new Point(483, 258));
                                spawnMonsterOnGroundBelow(mob2, new Point(483, 258));
                                spawnMonsterOnGroundBelow(mob3, new Point(483, 258));
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }, 1000 * 60 * time);
    }

    /* public void ��ʱ�ٻ�֩��Ů��(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                //if (getAllMonstersThreadsafe().size() == 0) {
                if (getMonsterById(8800400) == null) {
                    try {
                        if (getChannel() != 1) {
                            MapleMonster mob1 = MapleLifeFactory.getMonster(8800400);
                            spawnMonsterOnGroundBelow(mob1, new Point(61, 363));

                        }
                    } catch (Exception e) {
                    }
                }
            }
        }, 1000 * 60 * time);
    }*/
    public void ��ʱ�ٻ�����Ů��(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                //if (getAllMonstersThreadsafe().size() == 0) {
                if (getMonsterById(8920102) == null) {
                    try {
                        if (getChannel() != 1) {
                            MapleMonster mob1 = MapleLifeFactory.getMonster(8920000);
                            spawnMonsterOnGroundBelow(mob1, new Point(-2118, 86));

                        }
                    } catch (Exception e) {
                    }
                }
            }
        }, 1000 * 60 * time);
    }

    public void ��ʱ�ٻ��������(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                //if (getAllMonstersThreadsafe().size() == 0) {
                try {
                    if (getChannel() != 1) {
                        MapleMonster mob1 = MapleLifeFactory.getMonster(9500006);
                        spawnMonsterOnGroundBelow(mob1, new Point(475, 35));
                    }
                } catch (Exception e) {
                }
                // }
            }
        }, 1000 * 60 * time);
    }

    public void ��ʱ�ٻ��װ�(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (getAllMonstersThreadsafe().size() == 0) {
                    try {
                        if (getChannel() != 1) {
                            MapleMonster mob1 = MapleLifeFactory.getMonster(8840000);
                            spawnMonsterOnGroundBelow(mob1, new Point(-570, 102));
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }, 1000 * 60 * time);
    }

    public void ��ʱ�ٻ�ʯ����(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                //if (getAllMonstersThreadsafe().size() == 0) {
                if (getMonsterById(2500360) == null) {
                    try {
                        if (getChannel() != 1) {
                            MapleMonster mob1 = MapleLifeFactory.getMonster(2500360);
                            spawnMonsterOnGroundBelow(mob1, new Point(1927, 2205));
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }, 1000 * 60 * time);
    }

    public void ��ʱ�ٻ��ػ���(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                //if (getMonsterById(2500402) == null) {
                if (getMonsterById(2500402) == null) {
                    try {
                        if (getChannel() != 1) {
                            MapleMonster mob1 = MapleLifeFactory.getMonster(2500400);
                            spawnMonsterOnGroundBelow(mob1, new Point(821, 195));
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }, 1000 * 60 * time);
    }

    public void ��ʱ�ٻ�ϣ��˹(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                //if (getAllMonstersThreadsafe().size() == 0) {
                if (getMonsterById(9300742) == null) {
                    try {
                        if (getChannel() != 1) {
                            MapleMonster mob1 = MapleLifeFactory.getMonster(9300742);
                            spawnMonsterOnGroundBelow(mob1, new Point(1285, 143));
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }, 1000 * 60 * Start.ConfigValuesMap.get("ϣ��˹ˢ��ʱ��"));
    }

    public void ���յ�ͼ() {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (getId() != 103000804 && getId() != 922010900 && getId() != 240060200 && getId() != 280030000) {
                    if (getAllMonstersThreadsafe().size() > 0 && getCharactersSize() == 0) {
                        ���();
                        MaplePacketCreator.removeMapEffect();
                    }
                }
            }
        }, gui.Start.ConfigValuesMap.get("���յ�ͼʱ��") * 60 * 1000);
    }

//                for (MapleCharacter chr : characters) {
//                    int ��ȯ = 0;
//                    int ���� = 0;
//                    int ��� = 0;
//                    int ���� = 0;
//                    int �ݵ��ҿ��� = gui.Start.ConfigValuesMap.get("�ݵ��ҿ���");
//                    if (�ݵ��ҿ��� <= 0) {
//                        int �ݵ��� = gui.Start.ConfigValuesMap.get("�ݵ���");
//                        ��� += �ݵ���;
//                        if (chr.getEquippedFuMoMap().get(34) != null) {
//                            ��� += �ݵ��� / 100 * chr.getEquippedFuMoMap().get(34);
//                        }
//                        chr.gainMeso(�ݵ���, true);//���ӽ��
//                    }
//                    int �ݵ��ȯ���� = gui.Start.ConfigValuesMap.get("�ݵ��ȯ����");
//                    if (�ݵ��ȯ���� <= 0) {
//                        int �ݵ��ȯ = gui.Start.ConfigValuesMap.get("�ݵ��ȯ");
//                        chr.modifyCSPoints(1, �ݵ��ȯ, true);
//                        ��ȯ += �ݵ��ȯ;
//                    }
//                    int �ݵ���ÿ��� = gui.Start.ConfigValuesMap.get("�ݵ���ÿ���");
//                    if (�ݵ���ÿ��� <= 0) {
//                        int �ݵ���� = gui.Start.ConfigValuesMap.get("�ݵ����");
//                        chr.modifyCSPoints(2, �ݵ����, true);
//                        ���� += �ݵ����;
//                    }
//                    int �ݵ㾭�鿪�� = gui.Start.ConfigValuesMap.get("�ݵ㾭�鿪��");
//                    if (�ݵ㾭�鿪�� <= 0) {
//                        int �ݵ㾭�� = gui.Start.ConfigValuesMap.get("�ݵ㾭��");
//                        ���� += �ݵ㾭��;
//                        if (chr.getEquippedFuMoMap().get(33) != null) {
//                            ���� += �ݵ㾭�� / 100 * chr.getEquippedFuMoMap().get(33);
//                        }
//                        chr.gainExp(�ݵ㾭��, true, true, false);
//                    }
//                    BossRankManager3.getInstance().setLog(chr.getId(), chr.getName(), "�ݵ㾭��", (byte) 2, (byte) 1);
//                    chr.getClient().sendPacket(UIPacket.getTopMsg("[" + MapleParty.�������� + "�ݵ�]:" + ��ȯ + " ��ȯ / " + ���� + " ���� / " + ���� + " ���� / " + ��� + " ��ң��ݵ㾭�� + 1"));
//                }
    public final void setSpawns(final boolean fm) {
        this.isSpawns = fm;
    }

    public final boolean getSpawns() {
        return isSpawns;
    }

    public final void setFixedMob(int fm) {
        this.fixedMob = fm;
    }

    public final void setForceMove(int fm) {
        this.lvForceMove = fm;
    }

    public final int getForceMove() {
        return lvForceMove;
    }

    public final void setLevelLimit(int fm) {
        this.lvLimit = fm;
    }

    public final int getLevelLimit() {
        return lvLimit;
    }

    public final void setReturnMapId(int rmi) {
        this.returnMapId = rmi;
    }

    public final void setSoaring(boolean b) {
        this.soaring = b;
    }

    public final boolean canSoar() {
        return soaring;
    }

    public final void toggleDrops() {
        this.dropsDisabled = !dropsDisabled;
    }

    public final void setDrops(final boolean b) {
        this.dropsDisabled = b;
    }

    public final void toggleGDrops() {
        this.gDropsDisabled = !gDropsDisabled;
    }

    public final int getId() {
        return mapid;
    }

    public final MapleMap getReturnMap() {
        return ChannelServer.getInstance(channel).getMapFactory().getMap(returnMapId);
    }

    public final int getReturnMapId() {
        return returnMapId;
    }

    public final int getForcedReturnId() {
        return forcedReturnMap;
    }

    public final MapleMap getForcedReturnMap() {
        return ChannelServer.getInstance(channel).getMapFactory().getMap(forcedReturnMap);
    }

    public final void setForcedReturnMap(final int map) {
        this.forcedReturnMap = map;
    }

    /**
     * <�жϵ�ͼ������>
     */
    public final float getRecoveryRate() {
        return recoveryRate;
    }

    /**
     * <���õ�ͼ������>
     */
    public final void setRecoveryRate(final float recoveryRate) {
        this.recoveryRate = recoveryRate;
    }

    public final int getFieldLimit() {
        return fieldLimit;
    }

    public final void setFieldLimit(final int fieldLimit) {
        this.fieldLimit = fieldLimit;
    }

    public final void setCreateMobInterval(final short createMobInterval) {
        this.createMobInterval = createMobInterval;
    }

    public final void setTimeLimit(final int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public final void setMapName(final String mapName) {
        this.mapName = mapName;
    }

    public final String getMapName() {
        return mapName;
    }

    public final String getStreetName() {
        return streetName;
    }

    public final void setFirstUserEnter(final String onFirstUserEnter) {
        this.onFirstUserEnter = onFirstUserEnter;
    }

    public final void setUserEnter(final String onUserEnter) {
        this.onUserEnter = onUserEnter;
    }

    public void setOnUserEnter(String onUserEnter) {
        this.onUserEnter = onUserEnter;
    }

    public final boolean hasClock() {
        return clock;
    }

    public final void setClock(final boolean hasClock) {
        this.clock = hasClock;
    }

    public final boolean isTown() {
        return town;
    }

    public final void setTown(final boolean town) {
        this.town = town;
    }

    public final boolean allowPersonalShop() {
        return personalShop;
    }

    public final void setPersonalShop(final boolean personalShop) {
        this.personalShop = personalShop;
    }

    public final void setStreetName(final String streetName) {
        this.streetName = streetName;
    }

    public final void setEverlast(final boolean everlast) {
        this.everlast = everlast;
    }

    public final boolean getEverlast() {
        return everlast;
    }

    public final int getHPDec() {
        return decHP;
    }

    public final void setHPDec(final int delta) {
        if (delta > 0 || mapid == 749040100) { //pmd
            lastHurtTime = System.currentTimeMillis(); //start it up
        }
        decHP = (short) delta;
    }

    public final int getHPDecInterval() {
        return decHPInterval;
    }

    public final void setHPDecInterval(final int delta) {
        decHPInterval = delta;
    }

    public final int getHPDecProtect() {
        return protectItem;
    }

    public final void setHPDecProtect(final int delta) {
        this.protectItem = delta;
    }

    public final int getCurrentPartyId() {
        charactersLock.readLock().lock();
        try {
            final Iterator<MapleCharacter> ltr = characters.iterator();
            MapleCharacter chr;
            while (ltr.hasNext()) {
                chr = ltr.next();
                if (chr.getPartyId() != -1) {
                    return chr.getPartyId();
                }
            }
        } finally {
            charactersLock.readLock().unlock();
        }
        return -1;
    }

    public final void addMapObject(final MapleMapObject mapobject) {
        runningOidLock.lock();
        int newOid;
        try {
            newOid = ++runningOid;
        } finally {
            runningOidLock.unlock();
        }

        mapobject.setObjectId(newOid);

        mapobjectlocks.get(mapobject.getType()).writeLock().lock();
        try {
            mapobjects.get(mapobject.getType()).put(newOid, mapobject);
        } finally {
            mapobjectlocks.get(mapobject.getType()).writeLock().unlock();
        }
    }

    /*
    new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 10);
                        
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
     */
    //������Ʒ
    private void spawnAndAddRangedMapObject(final MapleMapObject mapobject, final DelayedPacketCreation packetbakery, final SpawnCondition condition) {
        addMapObject(mapobject);
        charactersLock.readLock().lock();
        try {
            final Iterator<MapleCharacter> itr = characters.iterator();
            MapleCharacter chr;
            while (itr.hasNext()) {
                chr = itr.next();
                if (condition == null || condition.canSpawn(chr)) {
                    if (!chr.isClone() && chr.getPosition().distanceSq(mapobject.getPosition()) <= GameConstants.maxViewRangeSq()) {
                        packetbakery.sendPackets(chr.getClient());
                        chr.addVisibleMapObject(mapobject);
                    }
                }
            }
        } finally {
            charactersLock.readLock().unlock();
        }
    }

    public final void removeMapObject(final MapleMapObject obj) {
        mapobjectlocks.get(obj.getType()).writeLock().lock();
        try {
            mapobjects.get(obj.getType()).remove(Integer.valueOf(obj.getObjectId()));
        } finally {
            mapobjectlocks.get(obj.getType()).writeLock().unlock();
        }
    }

    public final Point calcPointBelow(final Point initial) {
        final MapleFoothold fh = footholds.findBelow(initial);
        if (fh == null) {
            return null;
        }
        int dropY = fh.getY1();
        if (!fh.isWall() && fh.getY1() != fh.getY2()) {
            final double s1 = Math.abs(fh.getY2() - fh.getY1());
            final double s2 = Math.abs(fh.getX2() - fh.getX1());
            if (fh.getY2() < fh.getY1()) {
                dropY = fh.getY1() - (int) (Math.cos(Math.atan(s2 / s1)) * (Math.abs(initial.x - fh.getX1()) / Math.cos(Math.atan(s1 / s2))));
            } else {
                dropY = fh.getY1() + (int) (Math.cos(Math.atan(s2 / s1)) * (Math.abs(initial.x - fh.getX1()) / Math.cos(Math.atan(s1 / s2))));
            }
        }
        return new Point(initial.x, dropY);
    }

    public final Point calcDropPos(final Point initial, final Point fallback) {
        final Point ret = calcPointBelow(new Point(initial.x, initial.y - 50));
        if (ret == null) {
            return fallback;
        }
        return ret;
    }

    /**
     * <���ﱬ��>
     */
    private void dropFromMonster(final MapleCharacter chr, final MapleMonster mob) {//���й������
        if ((mob.getId() >= 9400714 && mob.getId() <= 9400724) || mob == null || chr == null || ChannelServer.getInstance(channel) == null || dropsDisabled || mob.dropsDisabled() || chr.getPyramidSubway() != null) { //no drops in pyramid ok? no cash either
            return;
        }

        if (chr.�ж�ƣ��ֵ() > 60 * gui.Start.ConfigValuesMap.get("ÿ��ƣ��ֵ")) {
            return;
        }
        //�жϸõ�ͼ��������
        if (mapobjects.get(MapleMapObjectType.ITEM).size() >= gui.Start.ConfigValuesMap.get("��ͼ��Ʒ����")) {
            removeDrops();
            chr.dropMessage(6, "[ϵͳ��ʾ] : ��ǰ��ͼ��Ʒ�����Ѿ��ﵽ���ƣ������ѱ������");
        }
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final byte droptype = (byte) (mob.getStats().isExplosiveReward() ? 3 : mob.getStats().isFfaLoot() ? 2 : chr.getParty() != null ? 1 : 0);
        //���ﱬ���
        int mobpos = mob.getPosition().x, cmServerrate = ChannelServer.getInstance(channel).getMesoRate(), chServerrate = ChannelServer.getInstance(channel).getDropRate(), caServerrate = ChannelServer.getInstance(channel).getCashRate();
        IItem idrop;

        byte d = 1;
        Point pos = new Point(0, mob.getPosition().y);
        double showdown = 100.0;
        final MonsterStatusEffect mse = mob.getBuff(MonsterStatus.SHOWDOWN);
        if (mse != null) {
            showdown += mse.getX();
        }
        if (mob.getStats().isBoss()) {
            chServerrate = ChannelServer.getInstance(channel).getBossDropRate();
        }
        final MapleMonsterInformationProvider mi = MapleMonsterInformationProvider.getInstance();//���й������
        final List<MonsterDropEntry> dropEntry = mi.retrieveDrop(mob.getId());
        Collections.shuffle(dropEntry);
        /**
         * <���ﱬ��>
         */
        for (final MonsterDropEntry de : dropEntry) {
            if (de.itemId == mob.getStolen()) {
                continue;
            }
            int Rand = Randomizer.nextInt(999999);
            int part1 = de.chance;
            int part2 = chServerrate;
            int part3 = chr.getDropMod();
            int part4 = (int) (chr.getStat().dropBuff / 100.0);
            int part5 = (int) (showdown / 100.0);
            int last = part1 * part2 * part3 * part4 * part5;
            if (Rand < last) {
                if (droptype == 3) {
                    pos.x = (mobpos + (d % 2 == 0 ? (40 * (d + 1) / 2) : -(40 * (d / 2))));
                } else {
                    pos.x = (mobpos + ((d % 2 == 0) ? (25 * (d + 1) / 2) : -(25 * (d / 2))));
                }
                if (de.itemId != 0) {
                    if (GameConstants.getInventoryType(de.itemId) == MapleInventoryType.EQUIP) {
                        idrop = ii.randomizeStats((Equip) ii.getEquipById(de.itemId));
                    } else {
                        final int range = Math.abs(de.Maximum - de.Minimum);
                        idrop = new Item(de.itemId, (byte) 0, (short) (de.Maximum != 1 ? Randomizer.nextInt(range <= 0 ? 1 : range) + de.Minimum : 1), (byte) 0);
                    }
                    spawnMobDrop(idrop, calcDropPos(pos, mob.getPosition()), mob, chr, droptype, de.questid);
                }
                d++;
            }
        }

        double mesoDecrease = Math.pow(0.93, mob.getStats().getExp() / 350.0);
        if (mesoDecrease > 1.0) {
            mesoDecrease = 1.0;
        }
        int tempmeso = Math.min(30000, (int) (mesoDecrease * (mob.getStats().getExp()) * (1.0 + Math.random() * 5) / 10.0));
        if (tempmeso > 0) {
            pos.x = Math.min(Math.max(mobpos - 25 * (d / 2), footholds.getMinDropX() + 25), footholds.getMaxDropX() - d * 25);
            spawnMobMesoDrop((int) (tempmeso * (chr.getStat().mesoBuff / 100.0) * chr.getDropMod() * cmServerrate), calcDropPos(pos, mob.getPosition()), mob, chr, false, droptype);
        }
        /**
         * <���籬��>
         */
        final List<MonsterGlobalDropEntry> globalEntry = new ArrayList<MonsterGlobalDropEntry>(mi.getGlobalDrop());
        Collections.shuffle(globalEntry);
        for (final MonsterGlobalDropEntry de : globalEntry) {
            if (Randomizer.nextInt(999999) < de.chance && (de.continent < 0 || (de.continent < 10) || (de.continent < 100) || (de.continent < 1000))) {
                if (droptype == 3) {
                    pos.x = (mobpos + (d % 2 == 0 ? (40 * (d + 1) / 2) : -(40 * (d / 2))));
                } else {
                    pos.x = (mobpos + ((d % 2 == 0) ? (25 * (d + 1) / 2) : -(25 * (d / 2))));
                }
                if (de.itemId > 0) {
                    if (!gDropsDisabled) {
                        if (GameConstants.getInventoryType(de.itemId) == MapleInventoryType.EQUIP) {
                            idrop = ii.randomizeStats((Equip) ii.getEquipById(de.itemId));
                        } else {
                            idrop = new Item(de.itemId, (byte) 0, (short) (de.Maximum != 1 ? Randomizer.nextInt(de.Maximum - de.Minimum) + de.Minimum : 1), (byte) 0);
                        }
                        spawnMobDrop(idrop, calcDropPos(pos, mob.getPosition()), mob, chr, de.onlySelf ? 0 : droptype, de.questid);
                        d++;
                    }
                }
            }
        }

    }

    public void removeMonster(final MapleMonster monster) {
        spawnedMonstersOnMap.decrementAndGet();
        broadcastMessage(MobPacket.killMonster(monster.getObjectId(), 0));
        removeMapObject(monster);
    }

    private void killMonster(final MapleMonster monster) { // For mobs with removeAfter
        spawnedMonstersOnMap.decrementAndGet();
        monster.setHp(0);
        monster.spawnRevives(this);
        broadcastMessage(MobPacket.killMonster(monster.getObjectId(), 1));
        removeMapObject(monster);
    }

    public final void killMonster(final MapleMonster monster, final MapleCharacter chr, final boolean withDrops, final boolean second, byte animation) {
        killMonster(monster, chr, withDrops, second, animation, 0);
    }

    public final void ��ͼɱ��(final MapleMonster monster, final MapleCharacter chr) {
        final int mobid = monster.getId();

        if (mobid == MapleParty.ͨ��BOSS && mapid == MapleParty.ͨ����ͼ) {
            MapleParty.ͨ��BOSS = 0;
            MapleParty.ͨ����ͼ = 0;
            String ��Ϣ = "[Ұ��ͨ��] : " + chr.getName() + " ����˴˴�ͨ�����һ��ͨ����� 1 Сʱ�󷢲���";
            Ⱥ֪ͨ(��Ϣ);
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, ��Ϣ));
            System.err.println("[�����]" + CurrentReadable_Time() + " : " + ��Ϣ);
            chr.��ɱҰ��BOSS��Ч2();
            chr.�򿪽���();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 60 * 60 * 1);
                        ���ͨ��();
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        } else if (mobid == 9500337 && mapid == 104000400) {
            �رս���BOSS�߳�();
        } else if (mobid == 2220000 && mapid == 104000400) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[����ţ����ɱ��]: " + chr.getName() + " �ں����ݴ�III��ɱ�˺���ţ��"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("ÿ�ջ�ɱ����ţ��");
            chr.setBossLog("��ɱ�߼�����");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ����ţ��", (byte) 2, (byte) 1);
        } else if (mobid == 3220000 && mapid == 101030404) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[��������ɱ��]: " + chr.getName() + " �ڶ�����ɽ����ɱ��������"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ������");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ������", (byte) 2, (byte) 1);
        } else if (mobid == 5220001 && mapid == 110040000) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[�޾�з��ɱ��]: " + chr.getName() + " ������ɳ̲��ɱ�˾޾�з"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ�޾�з");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ�޾�з", (byte) 2, (byte) 1);
        } else if (mobid == 7220000 && mapid == 250010304) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[�ϵ�����ɱ��]: " + chr.getName() + " �������ܵĵ��̻�ɱ�˿ϵ���"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ�ϵ���");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ�ϵ���", (byte) 2, (byte) 1);
        } else if (mobid == 8220000 && mapid == 200010300) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[��������ɱ��]: " + chr.getName() + " �����¥�ݢ��ɱ�˰�����"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ������");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ������", (byte) 2, (byte) 1);
        } else if (mobid == 7220002 && mapid == 250010503) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[������ʦ��ɱ��]: " + chr.getName() + " ������ɭ�ֻ�ɱ��������ʦ"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ������ʦ");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ������ʦ", (byte) 2, (byte) 1);

        } else if (mobid == 7220001 && mapid == 222010310) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[��β����ɱ��]: " + chr.getName() + " �������ɱ�˾�β��"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ��β��");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ��β��", (byte) 2, (byte) 1);
        } else if (mobid == 6220000 && mapid == 107000300) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[�����ɱ��]: " + chr.getName() + " ������̶���ɱ�˶��"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ���");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ���", (byte) 2, (byte) 1);
        } else if (mobid == 5220002 && mapid == 100040105) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[��ʿ����ɱ��]: " + chr.getName() + " ������ɭ�֢��ɱ�˸�ʿ��"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ��ʿ��");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ��ʿ��", (byte) 2, (byte) 1);
        } else if (mobid == 5220003 && mapid == 220050100) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[��Ī��ɱ��]: " + chr.getName() + " ��ʱ�����л�ɱ����Ī"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ��Ī");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ��Ī", (byte) 2, (byte) 1);
        } else if (mobid == 6220001 && mapid == 221040301) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[��ŵ��ɱ��]: " + chr.getName() + " �ڸ��ײ�ԭ��ɱ����ŵ"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ��ŵ");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ��ŵ", (byte) 2, (byte) 1);
        } else if (mobid == 8220003 && mapid == 240040401) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[������ɱ��]: " + chr.getName() + " �ڴ��� Ͽ�Ȼ�ɱ�˴���"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ����");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ����", (byte) 2, (byte) 1);
        } else if (mobid == 3220001 && mapid == 260010201) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[������ɱ��]: " + chr.getName() + " �������ưְ�ɳĮ��ɱ�˴���"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ����");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ����", (byte) 2, (byte) 1);
        } else if (mobid == 8220002 && mapid == 261030000) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[��������ɱ��]: " + chr.getName() + " ���о�����������ͨ����ɱ�˼�����"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ������");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ������", (byte) 2, (byte) 1);
        } else if (mobid == 4220000 && mapid == 230020100) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[Ъ������ɱ��]: " + chr.getName() + " �ں���֮����ɱ��Ъ����"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱЪ����");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱЪ����", (byte) 2, (byte) 1);
        } else if (mobid == 6130101 && mapid == 100000005) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[Ģ������ɱ��]: " + chr.getName() + " ��������԰3��ɱ��Ģ����"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱĢ����");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱĢ����", (byte) 2, (byte) 1);
        } else if (mobid == 6300005 && mapid == 105070002) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[��ʬĢ������ɱ��]: " + chr.getName() + " ��Ģ����֮Ĺ��ɱ�˽�ʬĢ����"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ��ʬĢ����");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ��ʬĢ����", (byte) 2, (byte) 1);
        } else if (mobid == 8130100 && mapid == 105090900) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[�������ɱ��]: " + chr.getName() + " �ڱ��������Ժ��ɱ�������"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ�����");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ�����", (byte) 2, (byte) 1);
        } else if (mobid == 9400205 && mapid == 800010100) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[��Ģ������ɱ��]: " + chr.getName() + " ����ʵ��û�ɱ����Ģ����"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ��Ģ����");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ��Ģ����", (byte) 2, (byte) 1);
        } else if (mobid == 9400120 && mapid == 801030000) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[�ϰ���ɱ��]: " + chr.getName() + " ���Ѻ��ڲ��ֵ�3��ɱ���ϰ�"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ�ϰ�");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ�ϰ�", (byte) 2, (byte) 1);
        } else if (mobid == 8220001 && mapid == 211040101) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[����ѩ����ɱ��]: " + chr.getName() + " ��ѩ�˹Ȼ�ɱ������ѩ��"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ����ѩ��");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ����ѩ��", (byte) 2, (byte) 1);
        } else if (mobid == 8180000 && mapid == 240020401) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[��������ɱ��]: " + chr.getName() + " ���������Ϣ�ػ�ɱ�˻�����"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ������");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ������", (byte) 2, (byte) 1);
        } else if (mobid == 8180001 && mapid == 240020101) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[��ӥ��ɱ��]: " + chr.getName() + " �ڸ���Ҷ�ɭ�ֻ�ɱ����ӥ"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ��ӥ");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ��ӥ", (byte) 2, (byte) 1);
        } else if (mobid == 8220006 && mapid == 270030500) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[�׿���ɱ��]: " + chr.getName() + " ����ȴ֮·5��ɱ���׿�"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ�׿�");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ�׿�", (byte) 2, (byte) 1);
        } else if (mobid == 8220005 && mapid == 270020500) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[������������ɱ��]: " + chr.getName() + " �ں��֮·5��ɱ������������"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ����������");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ����������", (byte) 2, (byte) 1);
        } else if (mobid == 8220004 && mapid == 270010500) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[�����ɱ��]: " + chr.getName() + " ��׷��֮·5��ɱ�˶��"));
            }
            chr.��ɱҰ��BOSS��Ч();
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ���");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ���", (byte) 2, (byte) 1);
        } else if (mobid == 8220004) {
            chr.setBossLog("���");
            chr.��ɱҰ��BOSS��Ч();
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���", (byte) 2, (byte) 1);
        } else if (mobid == 8500002 && mapid == 220080001) {
            if (gui.Start.ConfigValuesMap.get("����㲥����") <= 0) {
                World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[������ͼ˹��ɱ��]: " + chr.getName() + " ��ʱ�����ı�Դ��ɱ��������ͼ˹"));
            }
            chr.setBossLog("��ɱ�߼�����");
            chr.setBossLog("ÿ�ջ�ɱ������ͼ˹");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "ÿ����ս������ͼ˹", (byte) 2, (byte) 1);
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "���˻�ɱ������ͼ˹", (byte) 2, (byte) 1);
        } else if (mobid == 9300003 && mapid == 103000804) {
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "��������BOOS��ɱ����", (byte) 2, (byte) 1);
        } else if (mobid == 8830000 && mapid == 105100300) {//����֣�����
            MapleMonster mob;
            MapleMap map = chr.getMap();
            boolean drop = false;
            double range = Double.POSITIVE_INFINITY;
            List<MapleMapObject> monsters = map.getMapObjectsInRange(chr.getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER));
            for (MapleMapObject monstermo : map.getMapObjectsInRange(chr.getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
                mob = (MapleMonster) monstermo;
                map.killMonster(mob, chr, drop, false, (byte) 1);
            }
            for (MapleMapObject monstermo : map.getMapObjectsInRange(chr.getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
                mob = (MapleMonster) monstermo;
                map.killMonster(mob, chr, drop, false, (byte) 1);
            }
            /**
             * <����>
             */
        } else if (mobid == 8800002 && mapid == 280030000) {//��������
            MapleMonster mob;
            MapleMap map = chr.getMap();
            boolean drop = false;
            double range = Double.POSITIVE_INFINITY;
            List<MapleMapObject> monsters = map.getMapObjectsInRange(chr.getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER));
            for (MapleMapObject monstermo : map.getMapObjectsInRange(chr.getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
                mob = (MapleMonster) monstermo;
                map.killMonster(mob, chr, drop, false, (byte) 1);
            }
            for (MapleMapObject monstermo : map.getMapObjectsInRange(chr.getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
                mob = (MapleMonster) monstermo;
                map.killMonster(mob, chr, drop, false, (byte) 1);
            }

        }

    }

    public final void killMonster(final MapleMonster monster, final MapleCharacter chr, final boolean withDrops, final boolean second, byte animation, final int lastSkill) {
        if ((monster.getId() == 8810122 || monster.getId() == 8810018) && !second) {
            MapTimer.getInstance().schedule(new Runnable() {

                @Override
                public void run() {
                    killMonster(monster, chr, true, true, (byte) 1);
                    killAllMonsters(true);
                }
            }, 3000);
            return;
        }
        if (monster.getId() == 8150000) {
            if (MapleParty.����ħA���� > 0) {
                if (mapid == 106010100 || mapid == 106010000 || mapid == 100000000) {
                    MapleParty.����ħA���� -= 1;
                }
            } else if (MapleParty.����ħB���� > 0) {
                if (mapid == 107000400 || mapid == 107000300 || mapid == 107000200 || mapid == 107000100 || mapid == 107000000 || mapid == 103000000) {
                    MapleParty.����ħB���� -= 1;
                }
            } else if (MapleParty.����ħC���� > 0) {
                if (mapid == 101010103 || mapid == 101010102 || mapid == 101010101 || mapid == 101010100 || mapid == 101010000 || mapid == 101000000) {
                    MapleParty.����ħC���� -= 1;
                }
            } else if (MapleParty.����ħD���� > 0) {
                if (mapid == 106000300 || mapid == 106000200 || mapid == 106000100 || mapid == 106000000 || mapid == 102000000) {
                    MapleParty.����ħD���� -= 1;
                }
            }
        }
        //��ɱ��XX֮ǰҪɱ��
        if (monster.getId() == 8820014) { //ʱ��ĳ����Ʒ����
            killMonster(8820000);//ʱ��ĳ����Ʒ����
        } else if (monster.getId() == 9300166) { //PQ������
            animation = 2; //������3��
        } else if (this.getId() == 910320100) {
        }
        spawnedMonstersOnMap.decrementAndGet();
        removeMapObject(monster);
        int dropOwner = monster.killBy(chr, lastSkill);
        broadcastMessage(MobPacket.killMonster(monster.getObjectId(), animation));

        if (monster.getBuffToGive() > -1) {
            final int buffid = monster.getBuffToGive();
            final MapleStatEffect buff = MapleItemInformationProvider.getInstance().getItemEffect(buffid);
            charactersLock.readLock().lock();
            try {
                for (final MapleCharacter mc : characters) {
                    if (mc.isAlive()) {
                        buff.applyTo(mc);

                        switch (monster.getId()) {
                            case 8810018:
                            case 8810122:
                            case 8820001:
                                mc.getClient().sendPacket(MaplePacketCreator.showOwnBuffEffect(buffid, 11)); // HT nine spirit
                                broadcastMessage(mc, MaplePacketCreator.showBuffeffect(mc.getId(), buffid, 11), false); // HT nine spirit
                                break;
                        }
                    }
                }
            } finally {
                charactersLock.readLock().unlock();//reactor
            }
        }
        final int mobid = monster.getId();
        SpeedRunType type = SpeedRunType.NULL;
        final MapleSquad sqd = getSquadByMap();
        ��ͼɱ��(monster, chr);
        if (mapid == 702060000 && monster.getId() == 9600025) {
            String ��ս�� = "";
            for (MapleCharacter player : this.getCharacters()) {
                ��ս�� += player.getName() + " ";
                player.setBossLog("��ɮ��������");
            }
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[��ս]:������ɮ�� " + ��ս�� + "�����ˡ�"));
            //Ⱥ֪ͨ("[��ս]:������ɮ�� " + ��ս�� + "�����ˡ�");
        } else if (mapid == 541020800 && monster.getId() == 9420521) {
            String ��ս�� = "";
            for (MapleCharacter player : this.getCharacters()) {
                ��ս�� += player.getName() + " ";
                player.setBossLog("������������");
            }
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[��ս]:���������� " + ��ս�� + "�����ˡ�"));
            //Ⱥ֪ͨ("[��ս]:���������� " + ��ս�� + "�����ˡ�");
        } else if (mobid == 8810018 && mapid == 240060200) {
            String ��ս�� = "";
            for (MapleCharacter player : this.getCharacters()) {
                ��ս�� += player.getName() + " ";
                player.setBossLog("������������");
            }
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[��ս]:�ڰ�����˹�� " + ��ս�� + "�����ˡ�"));
            //Ⱥ֪ͨ("[��ս]:�ڰ�����˹�� " + ��ս�� + "�����ˡ�");
            if (speedRunStart > 0) {
                type = SpeedRunType.Horntail;
            }
            if (sqd != null) {
                doShrine(true);
            }
            /**
             * <��ɱ���������������ط���Ϣ>
             */
            try {
                try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characterz SET Point = 0 WHERE channel = 501")) {
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
            }
            /**
             * < ������ͼ˹ >
             */
        } else if (mobid == 8500002 && mapid == 220080001) {
            String ��ս�� = "";
            for (MapleCharacter player : this.getCharacters()) {
                ��ս�� += player.getName() + " ";
                player.setBossLog("���Ӿ�������");
            }
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[��ս]:������ͼ˹�� " + ��ս�� + "�����ˡ�"));
            //Ⱥ֪ͨ("[��ս]:������ͼ˹�� " + ��ս�� + "�����ˡ�");
            if (speedRunStart > 0) {
                type = SpeedRunType.Papulatus;
            }

            /**
             * <��ŭ���İ�ʨ�� >
             */
        } else if ((mobid == 9420549 || mobid == 9420544) && mapid == 551030200) {
            String ��ս�� = "";
            for (MapleCharacter player : this.getCharacters()) {
                ��ս�� += player.getName() + " ";
            }
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[��ս]:�İ�ʨ���� " + ��ս�� + "�����ˡ�"));
            //Ⱥ֪ͨ("[��ս]:�İ�ʨ���� " + ��ս�� + "�����ˡ�");
            if (speedRunStart > 0) {
                if (mobid == 9420549) {
                    type = SpeedRunType.Scarlion;
                } else {
                    type = SpeedRunType.Targa;
                }
            }
            /**
             * <ʱ��ĳ����Ʒ���� >
             */
        } else if (mobid == 8820001 && mapid == 270050100) {
            String ��ս�� = "";
            for (MapleCharacter player : this.getCharacters()) {
                ��ս�� += player.getName();

            }
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[��ս]:Ʒ���ͱ�" + ��ս�� + "�����ˡ�"));
            //Ⱥ֪ͨ("[��ս]:Ʒ���ͱ�" + ��ս�� + "�����ˡ�");
            if (speedRunStart > 0) {
                type = SpeedRunType.Pink_Bean;
            }
            if (sqd != null) {
                doShrine(true);
            }
            /**
             * <�����ֱ�,��ɱ�����ֱۺ󣬲��ܴ�����>
             */
        } else if (mobid >= 8800003 && mobid <= 8800010) {
            boolean makeZakReal = true;
            final Collection<MapleMonster> monsters = getAllMonstersThreadsafe();

            for (final MapleMonster mons : monsters) {
                if (mons.getId() >= 8800003 && mons.getId() <= 8800010) {
                    makeZakReal = false;
                    break;
                }
            }
            if (makeZakReal) {
                for (final MapleMapObject object : monsters) {
                    final MapleMonster mons = ((MapleMonster) object);
                    if (mons.getId() == 8800000) {
                        final Point pos = mons.getPosition();
                        this.killAllMonsters(true);
                        spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(8800000), pos);
                        break;
                    }
                }
            }
            /**
             * <��������>
             */
        } else if (mobid == 8800002 && mapid == 280030000) {
            String ��ս�� = "";
            for (MapleCharacter player : this.getCharacters()) {
                ��ս�� += player.getName() + " ";
                player.setBossLog("������������");
            }
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[��ս]:������ " + ��ս�� + "�����ˡ�"));
            //Ⱥ֪ͨ("[��ս]:������ " + ��ս�� + "�����ˡ�");
            if (speedRunStart > 0) {
                type = SpeedRunType.Zakum;
            }
            if (sqd != null) {
                doShrine(true);
            }
            /**
             * <��ɱ������������������ط���Ϣ>
             */
            try {
                try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characterz SET Point = 0 WHERE channel = 500")) {
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
            }
        }
        /**
         * <����������ٻ���͸������>
         */
        if (mobid == 8820008) {
            for (final MapleMapObject mmo : getAllMonstersThreadsafe()) {
                MapleMonster mons = (MapleMonster) mmo;
                if (mons.getLinkOid() != monster.getObjectId()) {
                    killMonster(mons, chr, false, false, animation);
                }
            }
            /**
             * <ʱ��ĳ����Ʒ���� >
             */
        } else if (mobid >= 8820010 && mobid <= 8820014) {
            for (final MapleMapObject mmo : getAllMonstersThreadsafe()) {
                MapleMonster mons = (MapleMonster) mmo;
                if (mons.getId() != 8820000 && mons.getObjectId() != monster.getObjectId() && mons.getLinkOid() != monster.getObjectId()) {
                    killMonster(mons, chr, false, false, animation);
                }
            }
        }
        /**
         * <��ɱ�����ı��� >
         */
        if (withDrops) {
            MapleCharacter drop = null;
            if (dropOwner <= 0) {
                drop = chr;
            } else {
                drop = getCharacterById(dropOwner);
                if (drop == null) {
                    drop = chr;
                }
            }
            dropFromMonster(drop, monster);
        }
    }

    public List<MapleReactor> getAllReactor() {
        return getAllReactorsThreadsafe();
    }

    public List<MapleReactor> getAllReactorsThreadsafe() {
        ArrayList<MapleReactor> ret = new ArrayList<MapleReactor>();
        mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().lock();
        try {
            for (MapleMapObject mmo : mapobjects.get(MapleMapObjectType.REACTOR).values()) {
                ret.add((MapleReactor) mmo);
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().unlock();
        }
        return ret;
    }

    public List<MapleMapObject> getAllDoor() {
        return getAllDoorsThreadsafe();
    }

    public List<MapleMapObject> getAllDoorsThreadsafe() {
        ArrayList<MapleMapObject> ret = new ArrayList<MapleMapObject>();
        mapobjectlocks.get(MapleMapObjectType.DOOR).readLock().lock();
        try {
            for (MapleMapObject mmo : mapobjects.get(MapleMapObjectType.DOOR).values()) {
                ret.add(mmo);
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.DOOR).readLock().unlock();
        }
        return ret;
    }

    public List<MapleMapObject> getAllMerchant() {
        return getAllHiredMerchantsThreadsafe();
    }

    public List<MapleMapObject> getAllHiredMerchantsThreadsafe() {
        ArrayList<MapleMapObject> ret = new ArrayList<MapleMapObject>();
        mapobjectlocks.get(MapleMapObjectType.HIRED_MERCHANT).readLock().lock();
        try {
            for (MapleMapObject mmo : mapobjects.get(MapleMapObjectType.HIRED_MERCHANT).values()) {
                ret.add(mmo);
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.HIRED_MERCHANT).readLock().unlock();
        }
        return ret;
    }

    public List<MapleMonster> getAllMonster() {
        return getAllMonstersThreadsafe();
    }

    public List<MapleMonster> getAllMonstersThreadsafe() {
        ArrayList<MapleMonster> ret = new ArrayList<MapleMonster>();
        mapobjectlocks.get(MapleMapObjectType.MONSTER).readLock().lock();
        try {
            for (MapleMapObject mmo : mapobjects.get(MapleMapObjectType.MONSTER).values()) {
                ret.add((MapleMonster) mmo);
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.MONSTER).readLock().unlock();
        }
        return ret;
    }

    public List<MapleMonster> getAllMonstersThreadsafe(int a) {
        ArrayList<MapleMonster> ret = new ArrayList<MapleMonster>();
        mapobjectlocks.get(MapleMapObjectType.MONSTER).readLock().lock();
        try {
            for (MapleMapObject mmo : mapobjects.get(MapleMapObjectType.MONSTER).values()) {
                ret.add((MapleMonster) mmo);
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.MONSTER).readLock().unlock();
        }
        return ret;
    }

    public final void killAllMonsters(final boolean animate) {
        for (final MapleMapObject monstermo : getAllMonstersThreadsafe()) {
            final MapleMonster monster = (MapleMonster) monstermo;
            spawnedMonstersOnMap.decrementAndGet();
            monster.setHp(0);
            broadcastMessage(MobPacket.killMonster(monster.getObjectId(), animate ? 1 : 0));
            removeMapObject(monster);
            monster.killed();

        }
    }

    public final void killMonster(final int monsId) {
        for (final MapleMapObject mmo : getAllMonstersThreadsafe()) {
            if (((MapleMonster) mmo).getId() == monsId) {
                spawnedMonstersOnMap.decrementAndGet();
                removeMapObject(mmo);
                broadcastMessage(MobPacket.killMonster(mmo.getObjectId(), 1));
                break;
            }
        }
    }

    private String MapDebug_Log() {
        final StringBuilder sb = new StringBuilder("Defeat time : ");
        sb.append(FileoutputUtil.CurrentReadable_Time());

        sb.append(" | Mapid : ").append(this.mapid);

        charactersLock.readLock().lock();
        try {
            sb.append(" Users [").append(characters.size()).append("] | ");
            for (MapleCharacter mc : characters) {
                sb.append(mc.getName()).append(", ");
            }
        } finally {
            charactersLock.readLock().unlock();
        }
        return sb.toString();
    }

    public final void limitReactor(final int rid, final int num) {
        List<MapleReactor> toDestroy = new ArrayList<MapleReactor>();
        Map<Integer, Integer> contained = new LinkedHashMap<Integer, Integer>();
        mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().lock();
        try {
            for (MapleMapObject obj : mapobjects.get(MapleMapObjectType.REACTOR).values()) {
                MapleReactor mr = (MapleReactor) obj;
                if (contained.containsKey(mr.getReactorId())) {
                    if (contained.get(mr.getReactorId()) >= num) {
                        toDestroy.add(mr);
                    } else {
                        contained.put(mr.getReactorId(), contained.get(mr.getReactorId()) + 1);
                    }
                } else {
                    contained.put(mr.getReactorId(), 1);
                }
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().unlock();
        }
        for (MapleReactor mr : toDestroy) {
            destroyReactor(mr.getObjectId());
        }
    }

    public final void destroyReactors(final int first, final int last) {
        List<MapleReactor> toDestroy = new ArrayList<MapleReactor>();
        mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().lock();
        try {
            for (MapleMapObject obj : mapobjects.get(MapleMapObjectType.REACTOR).values()) {
                MapleReactor mr = (MapleReactor) obj;
                if (mr.getReactorId() >= first && mr.getReactorId() <= last) {
                    toDestroy.add(mr);
                }
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().unlock();
        }
        for (MapleReactor mr : toDestroy) {
            destroyReactor(mr.getObjectId());
        }
    }

    public final void destroyReactor(final int oid) {
        final MapleReactor reactor = getReactorByOid(oid);
        broadcastMessage(MaplePacketCreator.destroyReactor(reactor));
        reactor.setAlive(false);
        removeMapObject(reactor);
        reactor.setTimerActive(false);

        if (reactor.getDelay() > 0) {
            MapTimer.getInstance().schedule(new Runnable() {

                @Override
                public final void run() {
                    respawnReactor(reactor);
                }
            }, reactor.getDelay());
        }
    }

    public final void reloadReactors() {
        List<MapleReactor> toSpawn = new ArrayList<MapleReactor>();
        mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().lock();
        try {
            for (MapleMapObject obj : mapobjects.get(MapleMapObjectType.REACTOR).values()) {
                final MapleReactor reactor = (MapleReactor) obj;
                broadcastMessage(MaplePacketCreator.destroyReactor(reactor));
                reactor.setAlive(false);
                reactor.setTimerActive(false);
                toSpawn.add(reactor);
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().unlock();
        }
        for (MapleReactor r : toSpawn) {
            removeMapObject(r);
            if (r.getReactorId() != 9980000 && r.getReactorId() != 9980001) { //guardians cpq
                respawnReactor(r);
            }
        }
    }

    /*
     * command to reset all item-reactors in a map to state 0 for GM/NPC use -
     * not tested (broken reactors get removed from mapobjects when destroyed)
     * Should create instances for multiple copies of non-respawning reactors...
     */
    public final void resetReactors() {
        setReactorState((byte) 0);
    }

    public final void setReactorState() {
        setReactorState((byte) 1);
    }

    public final void setReactorState(final byte state) {
        mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().lock();
        try {
            for (MapleMapObject obj : mapobjects.get(MapleMapObjectType.REACTOR).values()) {
                ((MapleReactor) obj).forceHitReactor((byte) state);
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().unlock();
        }
    }

    /*
     * command to shuffle the positions of all reactors in a map for PQ purposes
     * (such as ZPQ/LMPQ)
     */
    public final void shuffleReactors() {
        shuffleReactors(0, 9999999); //all
    }

    public final void shuffleReactors(int first, int last) {
        List<Point> points = new ArrayList<Point>();
        mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().lock();
        try {
            for (MapleMapObject obj : mapobjects.get(MapleMapObjectType.REACTOR).values()) {
                MapleReactor mr = (MapleReactor) obj;
                if (mr.getReactorId() >= first && mr.getReactorId() <= last) {
                    points.add(mr.getPosition());
                }
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().unlock();
        }
        Collections.shuffle(points);
        mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().lock();
        try {
            for (MapleMapObject obj : mapobjects.get(MapleMapObjectType.REACTOR).values()) {
                MapleReactor mr = (MapleReactor) obj;
                if (mr.getReactorId() >= first && mr.getReactorId() <= last) {
                    mr.setPosition(points.remove(points.size() - 1));
                }
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().unlock();
        }
    }

    /**
     * �Զ��ش��ַ����ҵ�����������¿������� on the map...
     *
     * @param monster
     */
    public final void updateMonsterController(final MapleMonster monster) {
        if (!monster.isAlive()) {
            return;
        }
        if (monster.getController() != null) {
            if (monster.getController().getMap() != this) {
                monster.getController().stopControllingMonster(monster);
            } else { // Everything is fine :)
                return;
            }
        }
        int mincontrolled = -1;
        MapleCharacter newController = null;

        charactersLock.readLock().lock();
        try {
            final Iterator<MapleCharacter> ltr = characters.iterator();
            MapleCharacter chr;
            while (ltr.hasNext()) {
                chr = ltr.next();
                if (!chr.isHidden() && !chr.isClone() && (chr.getControlledSize() < mincontrolled || mincontrolled == -1)) {
                    mincontrolled = chr.getControlledSize();
                    newController = chr;
                }
            }
        } finally {
            charactersLock.readLock().unlock();
        }
        if (newController != null) {
            if (monster.isFirstAttack()) {
                newController.controlMonster(monster, true);
                monster.setControllerHasAggro(true);
                monster.setControllerKnowsAboutAggro(true);
            } else {
                newController.controlMonster(monster, false);
            }
        }
    }

    public final MapleMapObject getMapObject(int oid, MapleMapObjectType type) {
        mapobjectlocks.get(type).readLock().lock();
        try {
            return mapobjects.get(type).get(oid);
        } finally {
            mapobjectlocks.get(type).readLock().unlock();
        }
    }

    public final boolean containsNPC(int npcid) {
        mapobjectlocks.get(MapleMapObjectType.NPC).readLock().lock();
        try {
            Iterator<MapleMapObject> itr = mapobjects.get(MapleMapObjectType.NPC).values().iterator();
            while (itr.hasNext()) {
                MapleNPC n = (MapleNPC) itr.next();
                if (n.getId() == npcid) {
                    return true;
                }
            }
            return false;
        } finally {
            mapobjectlocks.get(MapleMapObjectType.NPC).readLock().unlock();
        }
    }

    public MapleNPC getNPCById(int id) {
        mapobjectlocks.get(MapleMapObjectType.NPC).readLock().lock();
        try {
            Iterator<MapleMapObject> itr = mapobjects.get(MapleMapObjectType.NPC).values().iterator();
            while (itr.hasNext()) {
                MapleNPC n = (MapleNPC) itr.next();
                if (n.getId() == id) {
                    return n;
                }
            }
            return null;
        } finally {
            mapobjectlocks.get(MapleMapObjectType.NPC).readLock().unlock();
        }
    }

    public MapleMonster getMonsterById(int id) {
        mapobjectlocks.get(MapleMapObjectType.MONSTER).readLock().lock();
        try {
            MapleMonster ret = null;
            Iterator<MapleMapObject> itr = mapobjects.get(MapleMapObjectType.MONSTER).values().iterator();
            while (itr.hasNext()) {
                MapleMonster n = (MapleMonster) itr.next();
                if (n.getId() == id) {
                    ret = n;
                    break;
                }
            }
            return ret;
        } finally {
            mapobjectlocks.get(MapleMapObjectType.MONSTER).readLock().unlock();
        }
    }

    public int countMonsterById(int id) {
        mapobjectlocks.get(MapleMapObjectType.MONSTER).readLock().lock();
        try {
            int ret = 0;
            Iterator<MapleMapObject> itr = mapobjects.get(MapleMapObjectType.MONSTER).values().iterator();
            while (itr.hasNext()) {
                MapleMonster n = (MapleMonster) itr.next();
                if (n.getId() == id) {
                    ret++;
                }
            }
            return ret;
        } finally {
            mapobjectlocks.get(MapleMapObjectType.MONSTER).readLock().unlock();
        }
    }

    public MapleReactor getReactorById(int id) {
        mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().lock();
        try {
            MapleReactor ret = null;
            Iterator<MapleMapObject> itr = mapobjects.get(MapleMapObjectType.REACTOR).values().iterator();
            while (itr.hasNext()) {
                MapleReactor n = (MapleReactor) itr.next();
                if (n.getReactorId() == id) {
                    ret = n;
                    break;
                }
            }
            return ret;
        } finally {
            mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().unlock();
        }
    }

    /**
     * returns a monster with the given oid, if no such monster exists returns
     * null
     *
     * @param oid
     * @return
     */
    public final MapleMonster getMonsterByOid(final int oid) {
        MapleMapObject mmo = getMapObject(oid, MapleMapObjectType.MONSTER);
        if (mmo == null) {
            return null;
        }
        return (MapleMonster) mmo;
    }

    public final MapleNPC getNPCByOid(final int oid) {
        MapleMapObject mmo = getMapObject(oid, MapleMapObjectType.NPC);
        if (mmo == null) {
            return null;
        }
        return (MapleNPC) mmo;
    }

    public final MapleReactor getReactorByOid(final int oid) {
        MapleMapObject mmo = getMapObject(oid, MapleMapObjectType.REACTOR);
        if (mmo == null) {
            return null;
        }
        return (MapleReactor) mmo;
    }

    public final MapleReactor getReactorByName(final String name) {
        mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().lock();
        try {
            for (MapleMapObject obj : mapobjects.get(MapleMapObjectType.REACTOR).values()) {
                MapleReactor mr = ((MapleReactor) obj);
                if (mr.getName().equalsIgnoreCase(name)) {
                    return mr;
                }
            }
            return null;
        } finally {
            mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().unlock();
        }
    }

    public final void spawnNpc(final int id, final Point pos) {
        final MapleNPC npc = MapleLifeFactory.getNPC(id);
        npc.setPosition(pos);
        npc.setCy(pos.y);
        npc.setRx0(pos.x + 50);
        npc.setRx1(pos.x - 50);
        npc.setFh(getFootholds().findBelow(pos).getId());
        npc.setCustom(true);
        addMapObject(npc);
        broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
    }

    public final void removeNpc(final int npcid) {
        mapobjectlocks.get(MapleMapObjectType.NPC).writeLock().lock();
        try {
            Iterator<MapleMapObject> itr = mapobjects.get(MapleMapObjectType.NPC).values().iterator();
            while (itr.hasNext()) {
                MapleNPC npc = (MapleNPC) itr.next();
                if (npc.isCustom() && npc.getId() == npcid) {
                    broadcastMessage(MaplePacketCreator.removeNPC(npc.getObjectId()));
                    itr.remove();
                }
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.NPC).writeLock().unlock();
        }
    }

    public final void spawnMonster_sSack(final MapleMonster mob, final Point pos, final int spawnType) {
        final Point spos = calcPointBelow(new Point(pos.x, pos.y - 1));
        mob.setPosition(spos);
        spawnMonster(mob, spawnType);
    }

    public final void spawnMonsterOnGroundBelow(final MapleMonster mob, final Point pos) {
        spawnMonster_sSack(mob, pos, -2);
    }

    public final void spawnMonster_sSack(final MapleMonster mob, final Point pos, final int spawnType, int hp) {
        final Point spos = calcPointBelow(new Point(pos.x, pos.y - 1));
        mob.setPosition(spos);
        mob.setHp(hp);
        spawnMonster(mob, spawnType);
    }

    public final void spawnMonsterOnGroundBelow2(final MapleMonster mob, final Point pos, long hp, int Exp) {

        spawnMonster2(mob, pos, -2, hp, Exp);
    }

    public final void spawnMonster2(final MapleMonster mob, final Point pos, final int spawnType, long hp, int Exp) {
        final Point spos = calcPointBelow(new Point(pos.x, pos.y - 1));
        mob.setPosition(spos);
        mob.setHp(hp);
        mob.setExp(Exp);
        spawnMonster(mob, spawnType);
    }

    public final void spawnMonsterOnGroundBelow(final MapleMonster mob, final Point pos, int hp) {
        spawnMonster_sSack(mob, pos, -2, hp);
    }

    public final int spawnMonsterWithEffectBelow(final MapleMonster mob, final Point pos, final int effect) {
        final Point spos = calcPointBelow(new Point(pos.x, pos.y - 1));
        return spawnMonsterWithEffect(mob, effect, spos);
    }

    public final void spawnZakum(final int x, final int y) {
        final Point pos = new Point(x, y);
        final MapleMonster mainb = MapleLifeFactory.getMonster(8800000);
        final Point spos = calcPointBelow(new Point(pos.x, pos.y - 1));
        mainb.setPosition(spos);
        mainb.setFake(true);
        spawnFakeMonster(mainb);

        final int[] zakpart = {8800003, 8800004, 8800005, 8800006, 8800007,
            8800008, 8800009, 8800010};

        for (final int i : zakpart) {
            final MapleMonster part = MapleLifeFactory.getMonster(i);
            part.setPosition(spos);
            spawnMonster(part, -2);
        }
        if (squadSchedule != null) {
            cancelSquadSchedule();
            //broadcastMessage(MaplePacketCreator.stopClock());
        }
//        for (MapleCharacter player : this.getCharacters()) {
//            player.Gaincharacterz("" + player.getId() + "", 500, 1);
//        }
    }

    public final void spawnChaosZakum(final int x, final int y) {//�ٻ�����
        final Point pos = new Point(x, y);
        final MapleMonster mainb = MapleLifeFactory.getMonster(8800100);
        final Point spos = calcPointBelow(new Point(pos.x, pos.y - 1));
        mainb.setPosition(spos);
        mainb.setFake(true);

        // Might be possible to use the map object for reference in future.
        spawnFakeMonster(mainb);

        final int[] zakpart = {8800103, 8800104, 8800105, 8800106, 8800107,
            8800108, 8800109, 8800110};

        for (final int i : zakpart) {
            final MapleMonster part = MapleLifeFactory.getMonster(i);
            part.setPosition(spos);

            spawnMonster(part, -2);
        }
        if (squadSchedule != null) {
            cancelSquadSchedule();
            // broadcastMessage(MaplePacketCreator.stopClock());
        }
    }

    public List<MapleMist> getAllMistsThreadsafe() {
        ArrayList<MapleMist> ret = new ArrayList<MapleMist>();
        mapobjectlocks.get(MapleMapObjectType.MIST).readLock().lock();
        try {
            for (MapleMapObject mmo : mapobjects.get(MapleMapObjectType.MIST).values()) {
                ret.add((MapleMist) mmo);
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.MIST).readLock().unlock();
        }
        return ret;
    }

    public final void spawnFakeMonsterOnGroundBelow(final MapleMonster mob, final Point pos) {
        Point spos = calcPointBelow(new Point(pos.x, pos.y - 1));
        spos.y -= 1;
        mob.setPosition(spos);
        spawnFakeMonster(mob);
    }

    public int getMobsSize() {
        return ((LinkedHashMap) this.mapobjects.get(MapleMapObjectType.MONSTER)).size();
    }

    private void checkRemoveAfter(final MapleMonster monster) {
        final int ra = monster.getStats().getRemoveAfter();

        if (ra > 0) {
            MapTimer.getInstance().schedule(new Runnable() {

                @Override
                public final void run() {
                    if (monster != null && monster == getMapObject(monster.getObjectId(), monster.getType())) {
                        killMonster(monster);
                    }
                }
            }, ra * 1000);
        }
    }

    public final void spawnRevives(final MapleMonster monster, final int oid) {
        monster.setMap(this);
        checkRemoveAfter(monster);
        monster.setLinkOid(oid);
        spawnAndAddRangedMapObject(monster, new DelayedPacketCreation() {

            @Override
            public final void sendPackets(MapleClient c) {
                c.sendPacket(MobPacket.spawnMonster(monster, -2, 0, oid)); // TODO effect
            }
        }, null);
        updateMonsterController(monster);

        spawnedMonstersOnMap.incrementAndGet();
    }

    public final void spawnMonster(final MapleMonster monster, final int spawnType) {
        if (monster.getId() == 6090000) {
            return;
        }

        monster.setMap(this);
        checkRemoveAfter(monster);

        spawnAndAddRangedMapObject(monster, new DelayedPacketCreation() {

            public final void sendPackets(MapleClient c) {
                c.sendPacket(MobPacket.spawnMonster(monster, spawnType, 0, 0));
            }
        }, null);
        updateMonsterController(monster);

        spawnedMonstersOnMap.incrementAndGet();
    }

    public final int spawnMonsterWithEffect(final MapleMonster monster, final int effect, Point pos) {
        try {
            monster.setMap(this);
            monster.setPosition(pos);

            spawnAndAddRangedMapObject(monster, new DelayedPacketCreation() {

                @Override
                public final void sendPackets(MapleClient c) {
                    c.sendPacket(MobPacket.spawnMonster(monster, -2, effect, 0));
                }
            }, null);
            updateMonsterController(monster);

            spawnedMonstersOnMap.incrementAndGet();
            return monster.getObjectId();
        } catch (Exception e) {
            return -1;
        }
    }

    public final void spawnFakeMonster(final MapleMonster monster) {
        monster.setMap(this);
        monster.setFake(true);

        spawnAndAddRangedMapObject(monster, new DelayedPacketCreation() {

            @Override
            public final void sendPackets(MapleClient c) {
                c.sendPacket(MobPacket.spawnMonster(monster, -2, 0xfc, 0));
//		c.sendPacket(MobPacket.spawnFakeMonster(monster, 0));
            }
        }, null);
        updateMonsterController(monster);

        spawnedMonstersOnMap.incrementAndGet();
    }

    public final void spawnReactor(final MapleReactor reactor) {
        reactor.setMap(this);

        spawnAndAddRangedMapObject(reactor, new DelayedPacketCreation() {

            @Override
            public final void sendPackets(MapleClient c) {
                c.sendPacket(MaplePacketCreator.spawnReactor(reactor));
            }
        }, null);
    }

    private void respawnReactor(final MapleReactor reactor) {
        reactor.setState((byte) 0);
        reactor.setAlive(true);
        spawnReactor(reactor);
    }

    public final void spawnDoor(final MapleDoor door) {
        spawnAndAddRangedMapObject(door, new DelayedPacketCreation() {

            public final void sendPackets(MapleClient c) {
                c.sendPacket(MaplePacketCreator.spawnDoor(door.getOwner().getId(), door.getTargetPosition(), false));
                if (door.getOwner().getParty() != null && (door.getOwner() == c.getPlayer() || door.getOwner().getParty().containsMembers(new MaplePartyCharacter(c.getPlayer())))) {
                    c.sendPacket(MaplePacketCreator.partyPortal(door.getTown().getId(), door.getTarget().getId(), door.getSkill(), door.getTargetPosition()));
                }
                c.sendPacket(MaplePacketCreator.spawnPortal(door.getTown().getId(), door.getTarget().getId(), door.getSkill(), door.getTargetPosition()));
                c.sendPacket(MaplePacketCreator.enableActions());
            }
        }, new SpawnCondition() {

            public final boolean canSpawn(final MapleCharacter chr) {
                return door.getTarget().getId() == chr.getMapId() || door.getOwnerId() == chr.getId() || (door.getOwner() != null && door.getOwner().getParty() != null && door.getOwner().getParty().getMemberById(chr.getId()) != null);
            }
        });
    }

    public final void spawnSummon(final MapleSummon summon) {
        summon.updateMap(this);
        spawnAndAddRangedMapObject(summon, new DelayedPacketCreation() {

            @Override
            public void sendPackets(MapleClient c) {
                if (!summon.isChangedMap() || summon.getOwnerId() == c.getPlayer().getId()) {
                    c.sendPacket(MaplePacketCreator.spawnSummon(summon, true));
                }
            }
        }, null);
    }

    public final void spawnDragon(final MapleDragon summon) {
        spawnAndAddRangedMapObject(summon, new DelayedPacketCreation() {

            @Override
            public void sendPackets(MapleClient c) {
                //  c.sendPacket(MaplePacketCreator.spawnDragon(summon));
            }
        }, null);
    }

    public final void spawnMist(final MapleMist mist, final int duration, boolean fake) {
        spawnAndAddRangedMapObject(mist, new DelayedPacketCreation() {

            @Override
            public void sendPackets(MapleClient c) {
                mist.sendSpawnData(c);
            }
        }, null);

        final MapTimer tMan = MapTimer.getInstance();
        final ScheduledFuture<?> poisonSchedule;
        switch (mist.isPoisonMist()) {
            case 1:
                //poison: 0 = none, 1 = poisonous, 2 = recovery aura
                final MapleCharacter owner = getCharacterById(mist.getOwnerId());
                poisonSchedule = tMan.register(new Runnable() {

                    @Override
                    public void run() {
                        /*
                         * for (final MapleMapObject mo :
                         * getMapObjectsInRect(mist.getBox(),
                         * Collections.singletonList(MapleMapObjectType.MONSTER)))
                         * { if (mist.makeChanceResult()) { ((MapleMonster)
                         * mo).applyStatus(owner, new
                         * MonsterStatusEffect(MonsterStatus.POISON, 1,
                         * mist.getSourceSkill().getId(), null, false), true,
                         * duration, false); } }
                         */
                        for (final MapleMapObject mo : getMapObjectsInRect(mist.getBox(), Collections.singletonList(MapleMapObjectType.MONSTER))) {
                            /*
                             * if (mist.makeChanceResult() && ((MapleCharacter)
                             * mo).getId() != mist.getOwnerId()) {
                             * ((MapleCharacter)
                             * mo).setDOT(mist.getSource().getDOT(),
                             * mist.getSourceSkill().getId(),
                             * mist.getSkillLevel()); } else
                             */
                            if (mist.makeChanceResult() && !((MapleMonster) mo).isBuffed(MonsterStatus.POISON)) {
                                ((MapleMonster) mo).applyStatus(owner, new MonsterStatusEffect(MonsterStatus.POISON, 1, mist.getSourceSkill().getId(), null, false), true, duration, true/*
                                         * , mist.getSource()
                                 */);
                            }
                        }
                    }
                }, 2000, 2500);
                break;
            case 2:
                poisonSchedule = tMan.register(new Runnable() {

                    @Override
                    public void run() {
                        for (final MapleMapObject mo : getMapObjectsInRect(mist.getBox(), Collections.singletonList(MapleMapObjectType.PLAYER))) {
                            if (mist.makeChanceResult()) {
                                final MapleCharacter chr = ((MapleCharacter) mo);
                                chr.addMP((int) (mist.getSource().getX() * (chr.getStat().getMaxMp() / 100.0)));
                            }
                        }
                    }
                }, 2000, 2500);
                break;
            default:
                poisonSchedule = null;
                break;
        }
        tMan.schedule(new Runnable() {

            @Override
            public void run() {
                //broadcastMessage(MaplePacketCreator.removeMist(mist.getObjectId()));
                broadcastMessage(MaplePacketCreator.removeMist(mist.getObjectId(), false));
                removeMapObject(mist);
                if (poisonSchedule != null) {
                    poisonSchedule.cancel(false);
                }
            }
        }, duration);
    }

    public final void disappearingItemDrop(final MapleMapObject dropper, final MapleCharacter owner, final IItem item, final Point pos) {
        if (owner.isCheating) {
            return;
        }

        final Point droppos = calcDropPos(pos, pos);
        final MapleMapItem drop = new MapleMapItem(item, droppos, dropper, owner, (byte) 1, false);
        broadcastMessage(MaplePacketCreator.dropItemFromMapObject(drop, dropper.getPosition(), droppos, (byte) 3), drop.getPosition());
    }

    public final void spawnMesoDrop(final int meso, final Point position, final MapleMapObject dropper, final MapleCharacter owner, final boolean playerDrop, final byte droptype) {
        final Point droppos = calcDropPos(position, position);
        final MapleMapItem mdrop = new MapleMapItem(meso, droppos, dropper, owner, droptype, playerDrop);
        spawnAndAddRangedMapObject(mdrop, new DelayedPacketCreation() {
            @Override
            public void sendPackets(MapleClient c) {
                if (owner.isCheating) {
                    return;
                }
                c.sendPacket(MaplePacketCreator.dropItemFromMapObject(mdrop, dropper.getPosition(), droppos, (byte) 1));
            }
        }, null);
        if (!everlast) {
            int ��Ʒ�������ʱ�� = gui.Start.ConfigValuesMap.get("��Ʒ�������ʱ��");
            mdrop.registerExpire(��Ʒ�������ʱ�� * 1000);//��ɫ������ҳ���ʱ��

            if (droptype == 0 || droptype == 1) {
                mdrop.registerFFA(30000);
            }
        }
    }

    public final void spawnMobMesoDrop(final int meso, final Point position, final MapleMapObject dropper, final MapleCharacter owner, final boolean playerDrop, final byte droptype) {

        final MapleMapItem mdrop = new MapleMapItem(meso, position, dropper, owner, droptype, playerDrop);
        spawnAndAddRangedMapObject(mdrop, new DelayedPacketCreation() {
            @Override
            public void sendPackets(MapleClient c) {
                if (owner.isCheating) {
                    return;
                }
                c.sendPacket(MaplePacketCreator.dropItemFromMapObject(mdrop, dropper.getPosition(), position, (byte) 1));
            }
        }, null);
        int ��Ʒ�������ʱ�� = gui.Start.ConfigValuesMap.get("��Ʒ�������ʱ��");
        mdrop.registerExpire(��Ʒ�������ʱ�� * 1000);//��ҳ���ʱ��

        if (droptype == 0 || droptype == 1) {
            mdrop.registerFFA(30000);
        }

    }

    public final void spawnMobDrop(final IItem idrop, final Point dropPos, final MapleMonster mob, final MapleCharacter chr, final byte droptype, final short questid) {
        ;
        final MapleMapItem mdrop = new MapleMapItem(idrop, dropPos, mob, chr, droptype, false, questid);
        spawnAndAddRangedMapObject(mdrop, new DelayedPacketCreation() {
            @Override
            public void sendPackets(MapleClient c) {
                if (chr.isCheating) {
                    return;
                }
                if (questid <= 0 || c.getPlayer().getQuestStatus(questid) == 1) {
                    c.sendPacket(MaplePacketCreator.dropItemFromMapObject(mdrop, mob.getPosition(), dropPos, (byte) 1));
                }
            }
        }, null);
        //broadcastMessage(MaplePacketCreator.dropItemFromMapObject(mdrop, mob.getPosition(), dropPos, (byte) 0));
        int ��Ʒ�������ʱ�� = gui.Start.ConfigValuesMap.get("��Ʒ�������ʱ��");
        mdrop.registerExpire(��Ʒ�������ʱ�� * 1000);//�������Ʒ����ʱ�䣿
        if (droptype == 0 || droptype == 1) {
            mdrop.registerFFA(30000);
        }
        activateItemReactors(mdrop, chr.getClient());

    }

    public final void spawnRandDrop() {
        if (mapid != 749030000) {
            return;
        }
        mapobjectlocks.get(MapleMapObjectType.ITEM).readLock().lock();
        try {
            for (MapleMapObject o : mapobjects.get(MapleMapObjectType.ITEM).values()) {
                if (((MapleMapItem) o).isRandDrop()) {
                    return;
                }
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.ITEM).readLock().unlock();
        }
        MapTimer.getInstance().schedule(new Runnable() {
            public void run() {
                final Point pos = new Point(27, -9);//Randomizer.nextInt(800) + 531, 34 - Randomizer.nextInt(800)
                int itemid = 0;
                itemid = 2000005;//4000463
                spawnAutoDrop(itemid, pos);
                System.out.println("�� XXXXXXXXXXX");
            }
        }, 1 * 1000);
    }

    public final void spawnAutoDrop(final int itemid, final Point pos) {
        IItem idrop = null;
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (GameConstants.getInventoryType(itemid) == MapleInventoryType.EQUIP) {
            idrop = ii.randomizeStats((Equip) ii.getEquipById(itemid));
        } else {
            idrop = new Item(itemid, (byte) 0, (short) 1, (byte) 0);
        }
        final MapleMapItem mdrop = new MapleMapItem(pos, idrop);
        spawnAndAddRangedMapObject(mdrop, new DelayedPacketCreation() {

            @Override
            public void sendPackets(MapleClient c) {
                if (c.getPlayer().isCheating) {
                    return;
                }
                c.sendPacket(MaplePacketCreator.dropItemFromMapObject(mdrop, pos, pos, (byte) 1));
            }
        }, null);

        broadcastMessage(MaplePacketCreator.dropItemFromMapObject(mdrop, pos, pos, (byte) 0));
        mdrop.registerExpire(120000);//��֪����ʲô����ʱ��
    }

    public final void spawnAutoDrop2(final int itemid, final Point pos) {
        IItem idrop = null;
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (GameConstants.getInventoryType(itemid) == MapleInventoryType.EQUIP) {
            idrop = ii.randomizeStats((Equip) ii.getEquipById(itemid));
        } else {
            idrop = new Item(itemid, (byte) 0, (short) 1, (byte) 0);
        }
        final MapleMapItem mdrop = new MapleMapItem(pos, idrop);
        spawnAndAddRangedMapObject(mdrop, new DelayedPacketCreation() {
            @Override
            public void sendPackets(MapleClient c) {
                if (c.getPlayer().isCheating) {
                    return;
                }
                c.sendPacket(MaplePacketCreator.dropItemFromMapObject(mdrop, pos, pos, (byte) 1));
            }
        }, null);
        broadcastMessage(MaplePacketCreator.dropItemFromMapObject(mdrop, pos, pos, (byte) 0));
        mdrop.registerExpire(1000 * 10);
    }

    public final void ��Ʒ����(final MapleMapObject dropper, final MapleCharacter owner, final IItem item, Point pos, final boolean ffaDrop, final boolean playerDrop) {
        final Point droppos = calcDropPos(pos, pos);
        final MapleMapItem drop = new MapleMapItem(item, droppos, dropper, owner, (byte) 2, playerDrop);

        spawnAndAddRangedMapObject(drop, new DelayedPacketCreation() {

            @Override
            public void sendPackets(MapleClient c) {
                if (owner.isCheating) {
                    return;
                }
                c.sendPacket(MaplePacketCreator.dropItemFromMapObject(drop, dropper.getPosition(), droppos, (byte) 1));
            }
        }, null);

        if (owner.isCheating) {
            return;
        }
        broadcastMessage(MaplePacketCreator.dropItemFromMapObject(drop, dropper.getPosition(), droppos, (byte) 0));

        if (!everlast) {
            drop.registerExpire(10 * 1000);//��Ʒ����ʱ��
            activateItemReactors(drop, owner.getClient());
        }
    }

    public final void spawnItemDrop(final MapleMapObject dropper, final MapleCharacter owner, final IItem item, Point pos, final boolean ffaDrop, final boolean playerDrop) {
        final Point droppos = calcDropPos(pos, pos);
        final MapleMapItem drop = new MapleMapItem(item, droppos, dropper, owner, (byte) 2, playerDrop);

        spawnAndAddRangedMapObject(drop, new DelayedPacketCreation() {

            @Override
            public void sendPackets(MapleClient c) {
                if (owner.isCheating) {
                    return;
                }
                c.sendPacket(MaplePacketCreator.dropItemFromMapObject(drop, dropper.getPosition(), droppos, (byte) 1));
            }
        }, null);

        if (owner.isCheating) {
            return;
        }
        broadcastMessage(MaplePacketCreator.dropItemFromMapObject(drop, dropper.getPosition(), droppos, (byte) 0));

        if (!everlast) {
            if (getId() >= 209000001 && getId() <= 209000015) {
                drop.registerExpire(1000 * 60 * 60 * 1);
            } else {
                int ��Ʒ�������ʱ�� = gui.Start.ConfigValuesMap.get("��Ʒ�������ʱ��");
                drop.registerExpire(��Ʒ�������ʱ�� * 1000);//��ɫ������ҳ���ʱ��
            }
            activateItemReactors(drop, owner.getClient());
        }
    }

    private void activateItemReactors(final MapleMapItem drop, final MapleClient c) {
        final IItem item = drop.getItem();

        mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().lock();
        try {
            for (final MapleMapObject o : mapobjects.get(MapleMapObjectType.REACTOR).values()) {
                final MapleReactor react = (MapleReactor) o;

                if (react.getReactorType() == 100) {
                    if (GameConstants.isCustomReactItem(react.getReactorId(), item.getItemId(), react.getReactItem().getLeft()) && react.getReactItem().getRight() == item.getQuantity()) {
                        if (react.getArea().contains(drop.getPosition())) {
                            if (!react.isTimerActive()) {
                                MapTimer.getInstance().schedule(new ActivateItemReactor(drop, react, c), 5000);
                                react.setTimerActive(true);
                                break;
                            }
                        }
                    }
                }
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.REACTOR).readLock().unlock();
        }
    }

    public int getItemsSize() {
        return mapobjects.get(MapleMapObjectType.ITEM).size();
    }

    public List<MapleMapItem> getAllItems() {
        return getAllItemsThreadsafe(); //Which genius wrote this?
    }

    public List<MapleMapItem> getAllItemsThreadsafe() {
        ArrayList<MapleMapItem> ret = new ArrayList<MapleMapItem>();
        mapobjectlocks.get(MapleMapObjectType.ITEM).readLock().lock();
        try {
            for (MapleMapObject mmo : mapobjects.get(MapleMapObjectType.ITEM).values()) {
                ret.add((MapleMapItem) mmo);
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.ITEM).readLock().unlock();
        }
        return ret;
    }

    public final void returnEverLastItem(final MapleCharacter chr) {
        for (final MapleMapObject o : getAllItemsThreadsafe()) {
            final MapleMapItem item = ((MapleMapItem) o);
            if (item.getOwner() == chr.getId()) {
                item.setPickedUp(true);
                broadcastMessage(MaplePacketCreator.removeItemFromMap(item.getObjectId(), 2, chr.getId()), item.getPosition());
                if (item.getMeso() > 0) {
                    chr.gainMeso(item.getMeso(), false);
                } else {
                    MapleInventoryManipulator.addFromDrop(chr.getClient(), item.getItem(), false);
                }
                removeMapObject(item);
            }
        }
        spawnRandDrop();
    }

    public final void talkMonster(final String msg, final int itemId, final int objectid) {
        if (itemId > 0) {
            startMapEffect(msg, itemId, false);
        }
        broadcastMessage(MobPacket.talkMonster(objectid, itemId, msg)); //5120035
        broadcastMessage(MobPacket.removeTalkMonster(objectid));
    }

    public final void startMapEffect(final String msg, final int itemId) {
        startMapEffect(msg, itemId, false);
    }

    public final void startMapEffect(final String msg, final int itemId, final boolean jukebox) {
        if (mapEffect != null) {
            return;
        }
        mapEffect = new MapleMapEffect(msg, itemId);
        mapEffect.setJukebox(jukebox);
        broadcastMessage(mapEffect.makeStartData());
        MapTimer.getInstance().schedule(new Runnable() {

            @Override
            public void run() {
                broadcastMessage(mapEffect.makeDestroyData());
                mapEffect = null;
            }
        }, jukebox ? 300000 : 30000);
    }

    public final void startExtendedMapEffect(final String msg, final int itemId) {
        broadcastMessage(MaplePacketCreator.startMapEffect(msg, itemId, true));
        MapTimer.getInstance().schedule(new Runnable() {

            @Override
            public void run() {
                broadcastMessage(MaplePacketCreator.removeMapEffect());
                broadcastMessage(MaplePacketCreator.startMapEffect(msg, itemId, false));
                //dont remove mapeffect.
            }
        }, 60000);
    }

    public final void startJukebox(final String msg, final int itemId) {
        startMapEffect(msg, itemId, true);
    }

    public int getBossRank(int cid, String bossname, byte type) {
        int ret = -1;
        BossRankInfo info = BossRankManager.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1://����
                ret = info.getPoints();
                break;
            case 2://����
                ret = info.getCount();
                break;
        }
        return ret;
    }

    public final void �ͻ�(final MapleCharacter chr) {
        int ��ͼ = chr.getMapId();
        /**
         * <��һ�׶�9>
         */
        if (chr.getBossLog("�ͻ�") == 1) {
            if (��ͼ == 104000100 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ����۽��� �� �����ݴ�I");
            } else if (��ͼ == 104000200 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : �����ݴ�I �� �����ݴ�II");
            } else if (��ͼ == 104000300 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : �����ݴ�II �� �����ݴ�III");
            } else if (��ͼ == 104000400 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : �����ݴ�III �� ����·");
            } else if (��ͼ == 104010000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ����· �� ���ִ�����ɭ��");
            } else if (��ͼ == 104020000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ���ִ�����ɭ�� �� ���ִ�����Сɽ");
            } else if (��ͼ == 104030000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ���ִ�����Сɽ �� ���ִ�ѵ����I");
            } else if (��ͼ == 104040000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ���ִ�ѵ����I �� ���ִ�");
            } else if (��ͼ == 100000000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ���ִ� �� ���ͻ�Ա");
            }
        }
        /**
         * <�ڶ��׶�7>
         */
        if (chr.getBossLog("�ͻ�") == 3) {
            if (��ͼ == 100010000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ���ִ嶫��Сɽ �� ���ִ嶫���ݴ�");
            } else if (��ͼ == 100020000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ���ִ嶫���ݴ� �� ���ִ嶫������");
            } else if (��ͼ == 100030000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ���ִ嶫������ �� ħ��ɭ���ϲ�");
            } else if (��ͼ == 100040000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ħ��ɭ���ϲ� �� �ǻ�ɭ��");
            } else if (��ͼ == 100040100 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : �ǻ�ɭ�� �� ħ��ɭ���Ͻ�");
            } else if (��ͼ == 100050000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ħ��ɭ���Ͻ� �� ħ��ɭ��");
            } else if (��ͼ == 101000000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ħ��ɭ�� �� ���ͻ�Ա");
            }
        }

        /**
         * <�����׶�10>
         */
        if (chr.getBossLog("�ͻ�") == 5) {
            if (��ͼ == 101010000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ħ��ɭ�ֱ��� �� ��ľ��I");
            } else if (��ͼ == 101010100 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ��ľ��I �� ħ��ɭ�ֱ���");
            } else if (��ͼ == 101020000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ħ��ɭ�ֱ��� �� ��ʿ���䶫��");
            } else if (��ͼ == 101030000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ��ʿ���䶫�� �� ��ʯ·III");
            } else if (��ͼ == 101030100 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ��ʯ·III �� ��ʯ·II");
            } else if (��ͼ == 101030200 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ��ʯ·II �� ��ʯ·I");
            } else if (��ͼ == 101030300 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ��ʯ·I �� ������ɽI");
            } else if (��ͼ == 101030400 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ������ɽI �� ��ʿ���䶫�����");
            } else if (��ͼ == 101040000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ��ʿ���䶫����� �� ��ʿ����");
            } else if (��ͼ == 102000000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ��ʿ���� �� ���ͻ�Ա");
            }
        }
        /**
         * <�����׶�6>
         */
        if (chr.getBossLog("�ͻ�") == 7) {
            if (��ͼ == 102010000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ��ʿ������� �� ������ɽI");
            } else if (��ͼ == 102020000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ������ɽI �� ��ʿ��������");
            } else if (��ͼ == 102030000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : ��ʿ�������� �� �϶���������");
            } else if (��ͼ == 102040000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : �϶��������� �� �϶������");
            } else if (��ͼ == 102050000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : �϶������ �� ��������");
            } else if (��ͼ == 103000000 && chr.getBossLog("�ͻ�" + ��ͼ + "") == 0) {
                chr.setBossLog("�ͻ�" + ��ͼ + "");
                chr.dropMessage(5, "[�ͻ�����] : �������� �� ���ͻ�Ա");
            }
        }
    }

    //��ͼִ���ļ�
    public final void addPlayer(final MapleCharacter chr) {
        //ˢ���߳�
        charactersLock.writeLock().lock();
        try {
            characters.add(chr);
        } finally {
            charactersLock.writeLock().unlock();
        }
        mapobjectlocks.get(MapleMapObjectType.PLAYER).writeLock().lock();
        try {
            mapobjects.get(MapleMapObjectType.PLAYER).put(chr.getObjectId(), chr);
        } finally {
            mapobjectlocks.get(MapleMapObjectType.PLAYER).writeLock().unlock();
        }
//        if (gui.Start.ConfigValuesMap.get("���ּ�⿪��") == 0) {
//            if (chr.isAdmin()) {
//                int ��ͼ = chr.getMapId();
//                if (gui.Start.��ͼ���ּ��.get("" + ��ͼ + "") != null) {
//                    int ���ּ��ֵ = gui.Start.��ͼ���ּ��.get("" + ��ͼ + "");
//                    chr.dropMessage(5, "<��ͼ��ȫ���ϵͳ>");
//                    chr.dropMessage(5, " ��ͼ����: " + ��ͼ);
//                    chr.dropMessage(5, " ��ͼ�������: ����");
//                    chr.dropMessage(5, " ���ּ����ֵ: " + ���ּ��ֵ);
//                } else {
//                    int ���ּ��ֵ = gui.Start.��ͼ���ּ��.get("0");
//                    chr.dropMessage(5, "<��ͼ��ȫ���ϵͳ>");
//                    chr.dropMessage(5, " ��ͼ����: " + ��ͼ);
//                    chr.dropMessage(5, " ��ͼ�������: Ĭ��");
//                    chr.dropMessage(5, " ���ּ����ֵ: " + ���ּ��ֵ);
//                }
//            }
//        }
        //����ͼ����
        if (chr.getMapId() == 999999999) {
            chr.dropMessage(1, "����ͼ���");
            chr.changeMap(100000000, 0);
        }
        chr.�رղ����߳�();
        chr.�ر�ԡͰ�߳�();
        if (gui.Start.ConfigValuesMap.get("ÿ���ͻ�����") == 0) {
            if (chr.getBossLog("�ͻ�") > 0 && chr.getBossLog("�ͻ�") < 8) {
                �ͻ�(chr);
            }
        }
        //�ط���Ϣ
        switch (chr.getMapId()) {
            //����
            case 280030000:
                chr.Gaincharacterz("" + chr.getId() + "", 500, 1);
                break;
            //����
            case 240060200:
                chr.Gaincharacterz("" + chr.getId() + "", 501, 1);
                break;
            //pb
            case 270050100:
                chr.Gaincharacterz("" + chr.getId() + "", 502, 1);
                break;
            default:
                break;
        }
        if (chr.F().get(FM("��Ҷն")) != null) {
            chr.��Ҷ���� = chr.�ж���Ʒ����(4001126);
        }

        if (chr.F().get(FM("��������")) != null) {
            chr.�������� = chr.�ж���Ʒ����(2070006);
        }

        //��תְ�������˵Ļ���ǿ�ƴ��͹���
        if (chr.getMapId() >= 1020100 && chr.getMapId() <= 1020500) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 5);
                        if (chr.getMapId() >= 1020100 && chr.getMapId() <= 1020500) {
                            chr.changeMap(1020000, 0);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
        //����ͼ��ʾ��ͼ����
        if (gui.Start.ConfigValuesMap.get("��ͼ���ƿ���") == 0) {
            chr.startMapEffect(chr.getMap().getMapName(), 5120023, 5000);
        }
        //����ħ�����׺�ѩ��
        chr.removeAll(1472063);
        chr.removeAll(2060006);
        //���
        chr.removeAll(4001101);

        //ƵĻ����ʾ����
        switch (mapid) {
            case 209080000:
                if (getMonsterById(9400714) == null) {
                    MapleMonster mob1 = MapleLifeFactory.getMonster(9400714);
                    spawnMonsterOnGroundBelow(mob1, new Point(1450, 140));
                }
                break;
            case 180000001:
                chr.startMapEffect("�� ɣ �� �� ��", 5120018);
                break;
            /* case 100000203:
                chr.removeAll(4000140);
                chr.dropMessage(1, "���ͼ˵��\r\n\r\n"
                        + "1.���ʼ���ֹʹ��[Ⱥ������]������ᱻ���ͳ��֡�\r\n"
                        + "2.���ʼ���ͼ����ʹ�����Ƶ��ߡ�");
                break;*/
            case 910010000:
                chr.startMapEffect("���������ӣ��������������������ɣ�", 5120016);
                break;
            //��������
            case 103000800:
                chr.removeAll(4001008);
                chr.removeAll(4001007);
                chr.startMapEffect("������Ⲣ�ռ�ͨ��֤�����������ɽ�����һ�أ�", 5120017);
                chr.dropMessage(6, "������ֶ��ѵ��ߣ���ʹ�� @���ض��� �������ء�");
                chr.Gaincharacterz("" + chr.getId() + "", 499, 1);
                break;
            case 103000801:
                chr.startMapEffect("�ŶӺ��������������ҿ���ȷ����ϰ�!", 5120017);
                break;
            case 103000802:
                chr.startMapEffect("�ŶӺ�������ƽ̨�Ͻҿ���ȷ����ϰ�!", 5120017);
                break;
            case 103000803:
                chr.startMapEffect("�ŶӺ�������ľͰ�Ͻҿ���ȷ����ϰ�!", 5120017);
                break;
            case 103000804:
                chr.startMapEffect("�����ˮ����������Сˮ��ɣ���!", 5120017);
                break;
            case 103000805:
                chr.startMapEffect("��ϲ��ͨ���ˣ���ȡ��Ľ����ɣ�", 5120017);
                break;
            //�����
            case 922010100:
                chr.removeAll(4001022);
                chr.removeAll(4001023);
                chr.startMapEffect("��ɱ�����ռ�ͨ��֤�Ϳ��Խ�����һ�أ�", 5120018);
                break;
            case 922010200:
                chr.startMapEffect("�������ӣ��ռ�ͨ��֤�Ϳ��Խ�����һ��!", 5120018);
                break;
            case 922010300:
                chr.startMapEffect("��ɱ����ռ�ͨ��֤�Ϳ��Խ�����һ��!", 5120018);
                break;
            case 922010400:
                chr.startMapEffect("��ɱ�ڰ��еĹ���ռ�ͨ��֤!", 5120018);
                break;
            case 922010500:
                chr.startMapEffect("�Ͽ춯�������ռ�ͨ��֤!", 5120018);
                break;
            case 922010600:
                chr.startMapEffect("����ֻ��һ����ȷ��·��", 5120018);
                break;
            case 922010700:
                chr.startMapEffect("��ɱ����ռ�ͨ��֤�Ϳ��Խ�����һ�أ�", 5120018);
                break;
            case 922010800:
                chr.startMapEffect("�������ӣ��Ͷ�����������ȷ����ϰɣ�", 5120018);
                break;
            case 922010900:
                chr.startMapEffect("��ɱBOSS���õ�Կ�ף�", 5120018);
                break;
            case 920010000:
                chr.startMapEffect("�����ɫ���ƶ䣬�ռ���Ƭ��", 5120019);
                break;
            case 920010200:
                chr.startMapEffect("�ռ���һ��С��Ƭ�ɣ�", 5120019);
                break;
            case 920010300:
                chr.startMapEffect("�ռ��ڶ���С��Ƭ�ɣ�", 5120019);
                break;
            case 920010400:
                chr.startMapEffect("����������֣����������Ŀ�������", 5120019);
                break;
            case 920010500:
                chr.startMapEffect("��һ�룬�����ͨ���أ�", 5120019);
                break;
            case 920010600:
                chr.startMapEffect("��һ�룬�����ͨ���أ�", 5120019);
                break;
            case 920010700:
                chr.startMapEffect("��һ�룬�����ͨ���أ�", 5120019);
                break;
            case 925100000:
                //��������
                chr.removeAll(4001117);
                chr.removeAll(4001120);
                chr.removeAll(4001121);
                chr.removeAll(4001122);
                chr.startMapEffect("���鱦�䣬�ռ�Կ��", 5120020);
                break;
            case 925100100:
                chr.startMapEffect("��ɱ�������ռ�����֤����", 5120020);
                break;
            case 925100300:
                chr.startMapEffect("������������������������ǣ�", 5120020);
                break;
            case 925100500:
                chr.startMapEffect("�����Ϻ�����", 5120020);
                break;
            case 926100000:
                chr.startMapEffect("���ҵ����ص��ţ�ͨ������ʵ���ң�", 5120021);
                break;
            case 926100001:
                chr.startMapEffect("�ҵ���ķ�ʽͨ����ڰ���", 5120021);
                break;
            case 926100100:
                chr.startMapEffect("�����������ձ���", 5120021);
                break;
            case 926100200:
                chr.startMapEffect("��ȡʵ����ļ�ͨ��ÿ����!", 5120021);
                break;
            case 926100203:
                chr.startMapEffect("�������еĹ��!", 5120021);
                break;
            case 926100300:
                chr.startMapEffect("�ҵ���ķ���ͨ��ʵ���ң�", 5120021);
                break;
            case 926100401:
                chr.startMapEffect("�뱣���ҵİ��ˣ�", 5120021);
                break;
            case 930000000:
                chr.startMapEffect("���봫�͵㣬��Ҫ������ʩ�ű���ħ���ˣ�", 5120023);
                break;
            case 930000100:
                chr.startMapEffect("��������Ĺ����������������������20ֻ�Ϳ���ͨ���ˣ�", 5120023);
                break;
            case 930000200:
                chr.startMapEffect("��Ҫϡ�Ͷ�Һ��Ȼ���ͷ��ھ������棬�Ϳ��Դ���������", 5120023);
                break;
            case 930000300:
                chr.startMapEffect("�ҵ���ȥ��·������ʧ������!", 5120023);
                break;
            case 930000500:
                chr.startMapEffect("�Ͽ�ȥ�Ϸ�Ѱ����ɫħ��ʯ��!", 5120023);
                break;
            case 930000600:
                chr.startMapEffect("��ħ��ʯ���ڼ�̳�ϣ�", 5120023);
                break;
            default:
                break;
        }
//        if (mapid == 100000203) {
//            if (MapleParty.����ɱ� > 0) {
//                chr.changeMap(100000200, 0);
//                chr.dropMessage(5, "��Ѿ���ʼ���޷����롣");
//                return;
//            }
//        }

        if (!chr.isHidden()) {
            broadcastMessage(chr, MaplePacketCreator.spawnPlayerMapobject(chr), false);
            if (chr.isGM() && speedRunStart > 0) {
                endSpeedRun();
                broadcastMessage(MaplePacketCreator.serverNotice(5, "�ٶ������Ѿ�������"));
            }
        }
        //ð�յ��
        if (mapid == 109080000 || mapid == 109080001 || mapid == 109080002 || mapid == 109080003 || mapid == 109080010 || mapid == 109080011 || mapid
                == 109080012) {
            chr.setCoconutTeam(getAndSwitchTeam() ? 0 : 1);
        }
        //ð�յ��

        if (!chr.isClone()) {
            //���ε�ͼ����
            /*if (!onFirstUserEnter.equals("")) {
                if (getCharactersSize() == 1) {
                    MapScriptMethods.startScript_FirstUser(chr.getClient(), onFirstUserEnter);
                }
            }*/
            sendObjectPlacement(chr);
            chr.getClient().sendPacket(MaplePacketCreator.spawnPlayerMapobject(chr));
            //���ε�ͼ����
            /*if (!onUserEnter.equals("")) {
                MapScriptMethods.startScript_User(chr.getClient(), onUserEnter);
            }*/
            switch (mapid) {
                //��¥
                case 109030001:
                //��ߵ�
                case 109040000:
                //����
                case 109060001:
                //��Ҭ��
                case 109080000:
                //��ƿ��
                case 109080010:
                    chr.getClient().sendPacket(MaplePacketCreator.showEventInstructions());
                    break;
                /*case 109080002:
                case 109080003:
                case 109080011:
                case 109080012:
                    chr.getClient().sendPacket(MaplePacketCreator.showEquipEffect(chr.getCoconutTeam()));
                    break;*/
                //������
                case 809000101:
                //����Ů
                case 809000201:
                    chr.getClient().sendPacket(MaplePacketCreator.showEquipEffect());
                    break;
            }
        }
        for (final MaplePet pet : chr.getPets()) {
            if (pet.getSummoned()) {
                chr.getClient().sendPacket(PetPacket.updatePet(pet, chr.getInventory(MapleInventoryType.CASH).getItem((short) (byte) pet.getInventoryPosition()), true));
                broadcastMessage(chr, PetPacket.showPet(chr, pet, false, false), false);
            }
        }

        if (hasForcedEquip()) {
            chr.getClient().sendPacket(MaplePacketCreator.showForcedEquip());
        }

        chr.getClient().sendPacket(MaplePacketCreator.removeTutorialStats());
        //ս�����
        if (chr.getMapId() >= 914000200 && chr.getMapId() <= 914000220) {
            chr.getClient().sendPacket(MaplePacketCreator.addTutorialStats());
        }
        //ս���ٻ�Ů����NPC
        if (chr.getMapId() >= 140090100 && chr.getMapId() <= 140090500 || chr.getJob() == 1000 && chr.getMapId() != 130030000) {
            chr.getClient().sendPacket(MaplePacketCreator.spawnTutorialSummon(1));
        }
        //��ͼ�¼�1
        if (!onUserEnter.equals("")) {
            MapScriptMethods.startScript_User(chr.getClient(), onUserEnter);
            //MapScriptManager.getInstance().getMapScript(chr.getClient(), onUserEnter, false);
        }
        //��ͼ�¼�2
        if (!onFirstUserEnter.equals("")) {
            if (getCharacters().size() == 1) {
                MapScriptMethods.startScript_FirstUser(chr.getClient(), onFirstUserEnter);
                // MapScriptManager.getInstance().getMapScript(chr.getClient(), onFirstUserEnter, true);
            }
        }
        final MapleStatEffect stat = chr.getStatForBuff(MapleBuffStat.SUMMON);
        if (stat != null && !chr.isClone()) {
            final MapleSummon summon = chr.getSummons().get(stat.getSourceId());
            summon.setPosition(chr.getPosition());
            try {
                summon.setFh(getFootholds().findBelow(chr.getPosition()).getId());
            } catch (NullPointerException e) {
                summon.setFh(0);
            }
            this.spawnSummon(summon);
            chr.addVisibleMapObject(summon);
        }

        if (chr.getChalkboard() != null) {
            chr.getClient().sendPacket(MTSCSPacket.useChalkboard(chr.getId(), chr.getChalkboard()));
        }
        //���ģ�
        broadcastMessage(MaplePacketCreator.loveEffect());
        //ˢ�֣�
        if (timeLimit > 0 && getForcedReturnMap() != null && !chr.isClone()) {
            chr.startMapTimeLimitTask(timeLimit, getForcedReturnMap());
        }

        if (getSquadBegin() != null && getSquadBegin().getTimeLeft() > 0 && getSquadBegin().getStatus() == 1) {
            chr.getClient().sendPacket(MaplePacketCreator.getClock((int) (getSquadBegin().getTimeLeft() / 1000)));
        }

        if (chr.getCarnivalParty() != null && chr.getEventInstance() != null) {
            chr.getEventInstance().onMapLoad(chr);
        }

        MapleEvent.mapLoad(chr, channel);

        if (chr.getEventInstance() != null && chr.getEventInstance().isTimerStarted() && !chr.isClone()) {
            chr.getClient().sendPacket(MaplePacketCreator.getClock((int) (chr.getEventInstance().getTimeLeft() / 1000)));
        }

        if (hasClock()) {
            final Calendar cal = Calendar.getInstance();
            chr.getClient().sendPacket((MaplePacketCreator.getClockTime(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND))));
        }

        if (isTown()) {
            chr.cancelEffectFromBuffStat(MapleBuffStat.RAINING_MINES);
        }

        if (chr.getParty() != null && !chr.isClone()) {
            //chr.silentPartyUpdate();
            //chr.getClient().sendPacket(MaplePacketCreator.updateParty(chr.getClient().getChannel(), chr.getParty(), PartyOperation.SILENT_UPDATE, null));
            chr.updatePartyMemberHP();
            chr.receivePartyMemberHP();
        }
        if (permanentWeather > 0) {
            chr.getClient().sendPacket(MaplePacketCreator.startMapEffect("", permanentWeather, false)); //snow, no msg
        }

        if (getPlatforms().size() > 0) {
            chr.getClient().sendPacket(MaplePacketCreator.getMovingPlatforms(this));
        }

        if (environment.size() > 0) {
            chr.getClient().sendPacket(MaplePacketCreator.getUpdateEnvironment(this));
        }

        if (isTown()) {
            chr.cancelEffectFromBuffStat(MapleBuffStat.RAINING_MINES);
        }
        if (gui.Start.ConfigValuesMap.get("��ͼ�浵����") == 0) {
            chr.saveToDB(false, false);
        }
        if (gui.Start.ConfigValuesMap.get("��ѩ�쿪��") <= 0 || gui.Start.ConfigValuesMap.get("�º컨����") <= 0 || gui.Start.ConfigValuesMap.get("�����ݿ���") <= 0 || gui.Start.ConfigValuesMap.get("��ѩ������") <= 0 || gui.Start.ConfigValuesMap.get("�·�Ҷ����") <= 0) {
            if (gui.Start.ConfigValuesMap.get("��ѩ�쿪��") <= 0) {
                chr.getMap().broadcastMessage(MaplePacketCreator.startMapEffect("", 5120000, false));
            } else if (gui.Start.ConfigValuesMap.get("�º컨����") <= 0) {
                chr.getMap().broadcastMessage(MaplePacketCreator.startMapEffect("", 5120001, false));
            } else if (gui.Start.ConfigValuesMap.get("�����ݿ���") <= 0) {
                chr.getMap().broadcastMessage(MaplePacketCreator.startMapEffect("", 5120002, false));
            } else if (gui.Start.ConfigValuesMap.get("��ѩ������") <= 0) {
                chr.getMap().broadcastMessage(MaplePacketCreator.startMapEffect("", 5120003, false));
            } else if (gui.Start.ConfigValuesMap.get("�·�Ҷ����") <= 0) {
                chr.getMap().broadcastMessage(MaplePacketCreator.startMapEffect("", 5120008, false));
            }
        }
        /**
         * <������>
         */
        int ��ͼ = mapid;
        if (chr.��ͼ >= 10) {
            chr.��ͼ = 0;
            chr.��ͼ����1 = 0;
            chr.��ͼ����2 = 0;
            chr.��ͼ����3 = 0;
            chr.��ͼ����4 = 0;
            chr.��ͼ����5 = 0;
            chr.��ͼ����6 = 0;
            chr.��ͼ����7 = 0;
            chr.��ͼ����8 = 0;
            chr.��ͼ����9 = 0;
            chr.��ͼ����10 = 0;
            chr.��ͼ����ʱ��1 = "";
            chr.��ͼ����ʱ��2 = "";
            chr.��ͼ����ʱ��3 = "";
            chr.��ͼ����ʱ��4 = "";
            chr.��ͼ����ʱ��5 = "";
            chr.��ͼ����ʱ��6 = "";
            chr.��ͼ����ʱ��7 = "";
            chr.��ͼ����ʱ��8 = "";
            chr.��ͼ����ʱ��9 = "";
            chr.��ͼ����ʱ��10 = "";
        }
        chr.��ͼ++;
        switch (chr.��ͼ) {
            case 1:
                chr.��ͼ����1 = ��ͼ;
                chr.��ͼ����ʱ��1 = CurrentReadable_Time();
                break;
            case 2:
                chr.��ͼ����2 = ��ͼ;
                chr.��ͼ����ʱ��2 = CurrentReadable_Time();
                break;
            case 3:
                chr.��ͼ����3 = ��ͼ;
                chr.��ͼ����ʱ��3 = CurrentReadable_Time();
                break;
            case 4:
                chr.��ͼ����4 = ��ͼ;
                chr.��ͼ����ʱ��4 = CurrentReadable_Time();
                break;
            case 5:
                chr.��ͼ����5 = ��ͼ;
                chr.��ͼ����ʱ��5 = CurrentReadable_Time();
                break;
            case 6:
                chr.��ͼ����6 = ��ͼ;
                chr.��ͼ����ʱ��6 = CurrentReadable_Time();
                break;
            case 7:
                chr.��ͼ����7 = ��ͼ;
                chr.��ͼ����ʱ��7 = CurrentReadable_Time();
                break;
            case 8:
                chr.��ͼ����8 = ��ͼ;
                chr.��ͼ����ʱ��8 = CurrentReadable_Time();
                break;
            case 9:
                chr.��ͼ����9 = ��ͼ;
                chr.��ͼ����ʱ��9 = CurrentReadable_Time();
                break;
            case 10:
                chr.��ͼ����10 = ��ͼ;
                chr.��ͼ����ʱ��10 = CurrentReadable_Time();
                break;
            default:
                break;
        }
        if (mapid == 280020000) {
            chr.�����⿪��();
        }
    }

    public void ��¼��ͼ(int a) {
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO map (id) VALUES ( ?)")) {
            ps.setInt(1, a);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {

        }
    }

    public int �жϵ�ͼ(int a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM map where id =" + a + "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data += 1;
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public int getNumItems() {

        mapobjectlocks.get(MapleMapObjectType.ITEM).readLock().lock();
        try {
            return mapobjects.get(MapleMapObjectType.ITEM).size();
        } finally {
            mapobjectlocks.get(MapleMapObjectType.ITEM).readLock().unlock();
        }
    }

    private boolean hasForcedEquip() {
        return fieldType == 81 || fieldType == 82;
    }

    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
    }

    public int getNumMonsters() {
        mapobjectlocks.get(MapleMapObjectType.MONSTER).readLock().lock();
        try {
            return mapobjects.get(MapleMapObjectType.MONSTER).size();
        } finally {
            mapobjectlocks.get(MapleMapObjectType.MONSTER).readLock().unlock();
        }
    }

    public void doShrine(final boolean spawned) { //false = entering map, true = defeated
        if (squadSchedule != null) {
            cancelSquadSchedule();
        }
        final int mode = (mapid == 280030000 ? 1 : (mapid == 280030001 ? 2 : (mapid == 240060200 || mapid == 240060201 ? 3 : 0)));
        //chaos_horntail message for horntail too because it looks nicer
        final MapleSquad sqd = getSquadByMap();
        final EventManager em = getEMByMap();
        if (sqd != null && em != null && getCharactersSize() > 0) {
            final String leaderName = sqd.getLeaderName();
            final String state = em.getProperty("state");
            final Runnable run;
            MapleMap returnMapa = getForcedReturnMap();
            if (returnMapa == null || returnMapa.getId() == mapid) {
                returnMapa = getReturnMap();
            }
            switch (mode) {
                case 1:
                    //zakum
                    broadcastMessage(MaplePacketCreator.showZakumShrine(spawned, 5));
                    break;
                case 2:
                    //chaoszakum
                    broadcastMessage(MaplePacketCreator.showChaosZakumShrine(spawned, 5));
                    break;
                case 3:
                    //ht/chaosht
                    broadcastMessage(MaplePacketCreator.showChaosHorntailShrine(spawned, 5));
                    break;
                default:
                    broadcastMessage(MaplePacketCreator.showHorntailShrine(spawned, 5));
                    break;
            }
            if (mode == 1 || spawned) { //both of these together dont go well
                broadcastMessage(MaplePacketCreator.getClock(300)); //5 min
            }
            final MapleMap returnMapz = returnMapa;
            if (!spawned) { //no monsters yet; inforce timer to spawn it quickly
                final List<MapleMonster> monsterz = getAllMonstersThreadsafe();
                final List<Integer> monsteridz = new ArrayList<Integer>();
                for (MapleMapObject m : monsterz) {
                    monsteridz.add(m.getObjectId());
                }
                run = new Runnable() {

                    public void run() {
                        final MapleSquad sqnow = MapleMap.this.getSquadByMap();
                        if (MapleMap.this.getCharactersSize() > 0 && MapleMap.this.getNumMonsters() == monsterz.size() && sqnow != null && sqnow.getStatus() == 2 && sqnow.getLeaderName().equals(leaderName) && MapleMap.this.getEMByMap().getProperty("state").equals(state)) {
                            boolean passed = monsterz.isEmpty();
                            for (MapleMapObject m : MapleMap.this.getAllMonstersThreadsafe()) {
                                for (int i : monsteridz) {
                                    if (m.getObjectId() == i) {
                                        passed = true;
                                        break;
                                    }
                                }
                                if (passed) {
                                    break;
                                } //even one of the monsters is the same
                            }
                            if (passed) {
                                //are we still the same squad? are monsters still == 0?
                                byte[] packet;
                                if (mode == 1) { //zakum
                                    packet = MaplePacketCreator.showZakumShrine(spawned, 0);
                                } else if (mode == 2) { //chaoszakum
                                    packet = MaplePacketCreator.showChaosZakumShrine(spawned, 0);
                                } else {
                                    packet = MaplePacketCreator.showHorntailShrine(spawned, 0); //chaoshorntail message is weird
                                }
                                for (MapleCharacter chr : MapleMap.this.getCharactersThreadsafe()) { //warp all in map
                                    chr.getClient().sendPacket(packet);
                                    chr.changeMap(returnMapz, returnMapz.getPortal(0)); //hopefully event will still take care of everything once warp out
                                }
                                checkStates("");
                                resetFully();
                            }
                        }

                    }
                };
            } else { //inforce timer to gtfo
                run = new Runnable() {

                    public void run() {
                        MapleSquad sqnow = MapleMap.this.getSquadByMap();
                        //we dont need to stop clock here because they're getting warped out anyway
                        if (MapleMap.this.getCharactersSize() > 0 && sqnow != null && sqnow.getStatus() == 2 && sqnow.getLeaderName().equals(leaderName) && MapleMap.this.getEMByMap().getProperty("state").equals(state)) {
                            //are we still the same squad? monsters however don't count
                            byte[] packet;
                            if (mode == 1) { //zakum
                                packet = MaplePacketCreator.showZakumShrine(spawned, 0);
                            } else if (mode == 2) { //chaoszakum
                                packet = MaplePacketCreator.showChaosZakumShrine(spawned, 0);
                            } else {
                                packet = MaplePacketCreator.showHorntailShrine(spawned, 0); //chaoshorntail message is weird
                            }
                            for (MapleCharacter chr : MapleMap.this.getCharactersThreadsafe()) { //warp all in map
                                chr.getClient().sendPacket(packet);
                                chr.changeMap(returnMapz, returnMapz.getPortal(0)); //hopefully event will still take care of everything once warp out
                            }
                            checkStates("");
                            resetFully();
                        }
                    }
                };
            }
            squadSchedule = MapTimer.getInstance().schedule(run, 300000); //5 mins
        }
    }

    public final MapleSquad getSquadByMap() {
        MapleSquadType zz = null;
        switch (mapid) {
            case 105100400:
            case 105100300:
                zz = MapleSquadType.bossbalrog;
                break;
            case 280030000:
                zz = MapleSquadType.zak;
                break;
            case 280030001:
                zz = MapleSquadType.chaoszak;
                break;
            case 240060200:
                zz = MapleSquadType.horntail;
                break;
            case 240060201:
                zz = MapleSquadType.chaosht;
                break;
            case 270050100:
                zz = MapleSquadType.pinkbean;
                break;
            case 802000111:
                zz = MapleSquadType.nmm_squad;
                break;
            case 802000211:
                zz = MapleSquadType.vergamot;
                break;
            case 802000311:
                zz = MapleSquadType.tokyo_2095;
                break;
            case 802000411:
                zz = MapleSquadType.dunas;
                break;
            case 802000611:
                zz = MapleSquadType.nibergen_squad;
                break;
            case 802000711:
                zz = MapleSquadType.dunas2;
                break;
            case 802000801:
            case 802000802:
            case 802000803:
                zz = MapleSquadType.core_blaze;
                break;
            case 802000821:
            case 802000823:
                zz = MapleSquadType.aufheben;
                break;
            case 211070100:
            case 211070101:
            case 211070110:
                zz = MapleSquadType.vonleon;
                break;
            case 551030200:
                zz = MapleSquadType.scartar;
                break;
            case 271040100:
                zz = MapleSquadType.cygnus;
                break;
            default:
                return null;
        }
        return ChannelServer.getInstance(channel).getMapleSquad(zz);
    }

    public final MapleSquad getSquadBegin() {
        if (squad != null) {
            return ChannelServer.getInstance(channel).getMapleSquad(squad);
        }
        return null;
    }

    public final EventManager getEMByMap() {
        String em = null;
        switch (mapid) {
            case 105100400:
                em = "BossBalrog_EASY";
                break;
            case 105100300:
                em = "BossBalrog_NORMAL";
                break;
            case 280030000:
                em = "ZakumBattle";
                break;
            case 240060200:
                em = "HorntailBattle";
                break;
            case 280030001:
                em = "ChaosZakum";
                break;
            case 240060201:
                em = "ChaosHorntail";
                break;
            case 270050100:
                em = "PinkBeanBattle";
                break;
            case 802000111:
                em = "NamelessMagicMonster";
                break;
            case 802000211:
                em = "Vergamot";
                break;
            case 802000311:
                em = "2095_tokyo";
                break;
            case 802000411:
                em = "Dunas";
                break;
            case 802000611:
                em = "Nibergen";
                break;
            case 802000711:
                em = "Dunas2";
                break;
            case 802000801:
            case 802000802:
            case 802000803:
                em = "CoreBlaze";
                break;
            case 802000821:
            case 802000823:
                em = "Aufhaven";
                break;
            case 211070100:
            case 211070101:
            case 211070110:
                em = "VonLeonBattle";
                break;
            case 551030200:
                em = "ScarTarBattle";
                break;
            case 271040100:
                em = "CygnusBattle";
                break;
            case 262030300:
                em = "HillaBattle";
                break;
            case 262031300:
                em = "DarkHillaBattle";
                break;
            case 272020110:
            case 272030400:
                em = "ArkariumBattle";
                break;
            case 955000100:
            case 955000200:
            case 955000300:
                em = "AswanOffSeason";
                break;
            /*
             *
             */ case 280030100:
                /*
                 * 2829
                 */ em = "ZakumBattle";
                /*
                 * 2830
                 */ break;
            /*
             *
             */ case 272020200:
                /*
                 * 2862
                 */ em = "Akayile";
                /*
                 * 2863
                 */ break;
            /*
             *
             */ case 689013000:
                /*
                 * 2888
                 */ em = "PinkZakum";
                /*
                 * 2889
                 */ break;
            /*
             *
             */ case 703200400:
                /*
                 * 2891
                 */ em = "0AllBoss";
                /*
                 * 2892
                 */ break;
            //case 689010000:
            //    em = "PinkZakumEntrance";
            //    break;
            //case 689013000:
            //    em = "PinkZakumFight";
            //    break;
            default:
                return null;
        }
        return ChannelServer.getInstance(channel).getEventSM().getEventManager(em);
    }

    public final void removePlayer(final MapleCharacter chr) {
        //log.warn("[dc] [level2] Player {} leaves map {}", new Object[] { chr.getName(), mapid });

        if (everlast) {
            returnEverLastItem(chr);
        }

        charactersLock.writeLock().lock();
        try {
            characters.remove(chr);
        } finally {
            charactersLock.writeLock().unlock();
        }
        removeMapObject(chr);
        chr.checkFollow();
        broadcastMessage(MaplePacketCreator.removePlayerFromMap(chr.getId()));
        if (!chr.isClone()) {
            final List<MapleMonster> update = new ArrayList<MapleMonster>();
            final Iterator<MapleMonster> controlled = chr.getControlled().iterator();

            while (controlled.hasNext()) {
                MapleMonster monster = controlled.next();
                if (monster != null) {
                    monster.setController(null);
                    monster.setControllerHasAggro(false);
                    monster.setControllerKnowsAboutAggro(false);
                    controlled.remove();
                    update.add(monster);
                }
            }
            for (MapleMonster mons : update) {
                updateMonsterController(mons);
            }
            chr.leaveMap();
            checkStates(chr.getName());
            if (mapid == 109020001) {
                chr.canTalk(true);
            }
            for (final WeakReference<MapleCharacter> chrz : chr.getClones()) {
                if (chrz.get() != null) {
                    removePlayer(chrz.get());
                }
            }
        }
        chr.cancelEffectFromBuffStat(MapleBuffStat.PUPPET);
        chr.cancelEffectFromBuffStat(MapleBuffStat.REAPER);
        boolean cancelSummons = false;
        for (final MapleSummon summon : chr.getSummons().values()) {
            if (summon.getMovementType() == SummonMovementType.STATIONARY || summon.getMovementType() == SummonMovementType.CIRCLE_STATIONARY || summon.getMovementType() == SummonMovementType.WALK_STATIONARY) {
                cancelSummons = true;
            } else {
                summon.setChangedMap(true);
                removeMapObject(summon);
            }
        }
        if (cancelSummons) {
            chr.cancelEffectFromBuffStat(MapleBuffStat.SUMMON);

        }
        if (chr.getDragon() != null) {
            removeMapObject(chr.getDragon());
        }
    }

    public List<MapleMapObject> getAllPlayers() {
        return getMapObjectsInRange(new Point(0, 0), Double.POSITIVE_INFINITY, Arrays.asList(MapleMapObjectType.PLAYER));
    }

    public final void broadcastMessage(final byte[] packet) {
        broadcastMessage(null, packet, Double.POSITIVE_INFINITY, null);
    }

    public final void broadcastMessage(final MapleCharacter source, final byte[] packet, final boolean repeatToSource) {
        broadcastMessage(repeatToSource ? null : source, packet, Double.POSITIVE_INFINITY, source.getPosition());
    }

    /*
     * public void broadcastMessage(MapleCharacter source, MaplePacket packet,
     * boolean repeatToSource, boolean ranged) { broadcastMessage(repeatToSource
     * ? null : source, packet, ranged ? MapleCharacter.MAX_VIEW_RANGE_SQ :
     * Double.POSITIVE_INFINITY, source.getPosition()); }
     */
    public final void broadcastMessage(final byte[] packet, final Point rangedFrom) {
        broadcastMessage(null, packet, GameConstants.maxViewRangeSq(), rangedFrom);
    }

    public final void broadcastMessage(final MapleCharacter source, final byte[] packet, final Point rangedFrom) {
        broadcastMessage(source, packet, GameConstants.maxViewRangeSq(), rangedFrom);
    }

    //������������
    private void broadcastMessage(final MapleCharacter source, final byte[] packet, final double rangeSq, final Point rangedFrom) {
        charactersLock.readLock().lock();
        try {
            for (MapleCharacter chr : characters) {
                if (chr != source) {
                    if (rangeSq < Double.POSITIVE_INFINITY) {
                        if (rangedFrom.distanceSq(chr.getPosition()) <= rangeSq) {
                            chr.getClient().sendPacket(packet);
                        }
                    } else {
                        chr.getClient().sendPacket(packet);
                    }
                }
            }
        } finally {
            charactersLock.readLock().unlock();
        }
    }

    /*
   
            new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 3);
                    charactersLock.readLock().lock();

                } catch (InterruptedException e) {
                }
            }
        }.start();
     */
    private void sendObjectPlacement(final MapleCharacter c) {
        if (c == null || c.isClone()) {
            return;
        }
        for (final MapleMapObject o : this.getAllMonstersThreadsafe()) {
            updateMonsterController((MapleMonster) o);
        }
        for (final MapleMapObject o : getMapObjectsInRange(c.getPosition(), GameConstants.maxViewRangeSq(), GameConstants.rangedMapobjectTypes)) {
            if (o.getType() == MapleMapObjectType.REACTOR) {
                if (!((MapleReactor) o).isAlive()) {
                    continue;
                }
            }
            o.sendSpawnData(c.getClient());
            c.addVisibleMapObject(o);
        }
    }

    public final List<MapleMapObject> getMapObjectsInRange(final Point from, final double rangeSq) {
        final List<MapleMapObject> ret = new ArrayList<>();
        for (MapleMapObjectType type : MapleMapObjectType.values()) {
            mapobjectlocks.get(type).readLock().lock();
            try {
                Iterator<MapleMapObject> itr = mapobjects.get(type).values().iterator();
                while (itr.hasNext()) {
                    MapleMapObject mmo = itr.next();
                    if (from.distanceSq(mmo.getPosition()) <= rangeSq) {
                        ret.add(mmo);
                    }
                }
            } finally {
                mapobjectlocks.get(type).readLock().unlock();
            }
        }
        return ret;
    }

    public List<MapleMapObject> getItemsInRange(Point from, double rangeSq) {
        return getMapObjectsInRange(from, rangeSq, Arrays.asList(MapleMapObjectType.ITEM));
    }

    public final List<MapleMapObject> getMapObjectsInRange(final Point from, final double rangeSq, final List<MapleMapObjectType> MapObject_types) {
        final List<MapleMapObject> ret = new ArrayList<MapleMapObject>();
        for (MapleMapObjectType type : MapObject_types) {
            mapobjectlocks.get(type).readLock().lock();
            try {
                Iterator<MapleMapObject> itr = mapobjects.get(type).values().iterator();
                while (itr.hasNext()) {
                    MapleMapObject mmo = itr.next();
                    if (from.distanceSq(mmo.getPosition()) <= rangeSq) {
                        ret.add(mmo);
                    }
                }
            } finally {
                mapobjectlocks.get(type).readLock().unlock();
            }
        }
        return ret;
    }

    public final List<MapleMapObject> getMapObjectsInRect(final Rectangle box, final List<MapleMapObjectType> MapObject_types) {
        final List<MapleMapObject> ret = new ArrayList<MapleMapObject>();
        for (MapleMapObjectType type : MapObject_types) {
            mapobjectlocks.get(type).readLock().lock();
            try {
                Iterator<MapleMapObject> itr = mapobjects.get(type).values().iterator();
                while (itr.hasNext()) {
                    MapleMapObject mmo = itr.next();
                    if (box.contains(mmo.getPosition())) {
                        ret.add(mmo);
                    }
                }
            } finally {
                mapobjectlocks.get(type).readLock().unlock();
            }
        }
        return ret;
    }

    public final List<MapleCharacter> getPlayersInRectAndInList(final Rectangle box, final List<MapleCharacter> chrList) {
        final List<MapleCharacter> character = new LinkedList<MapleCharacter>();

        charactersLock.readLock().lock();
        try {
            final Iterator<MapleCharacter> ltr = characters.iterator();
            MapleCharacter a;
            while (ltr.hasNext()) {
                a = ltr.next();
                if (chrList.contains(a) && box.contains(a.getPosition())) {
                    character.add(a);
                }
            }
        } finally {
            charactersLock.readLock().unlock();
        }
        return character;
    }

    public final void addPortal(final MaplePortal myPortal) {
        portals.put(myPortal.getId(), myPortal);
    }

    public final MaplePortal getPortal(final String portalname) {
        for (final MaplePortal port : portals.values()) {
            if (port.getName().equals(portalname)) {
                return port;
            }
        }
        return null;
    }

    public final MaplePortal getPortal(final int portalid) {
        return portals.get(portalid);
    }

    public final void resetPortals() {
        for (final MaplePortal port : portals.values()) {
            port.setPortalState(true);
        }
    }

    public final void setFootholds(final MapleFootholdTree footholds) {
        this.footholds = footholds;
    }

    public final MapleFootholdTree getFootholds() {
        return footholds;
    }

    public final void loadMonsterRate(final boolean first) {
        final int spawnSize = monsterSpawn.size();
        maxRegularSpawn = Math.round(spawnSize * monsterRate);
        if (maxRegularSpawn < 2) {
            maxRegularSpawn = 2;
        } else if (maxRegularSpawn > spawnSize) {
            maxRegularSpawn = spawnSize - (spawnSize / 15);
        }
        if (fixedMob > 0) {
            maxRegularSpawn = fixedMob;
        }
        Collection<Spawns> newSpawn = new LinkedList<Spawns>();
        Collection<Spawns> newBossSpawn = new LinkedList<Spawns>();
        for (final Spawns s : monsterSpawn) {
            if (s.getCarnivalTeam() >= 2) {
                continue; // Remove carnival spawned mobs
            }
            if (s.getMonster().getStats().isBoss()) {
                newBossSpawn.add(s);
            } else {
                newSpawn.add(s);
            }
        }
        monsterSpawn.clear();
        monsterSpawn.addAll(newBossSpawn);
        monsterSpawn.addAll(newSpawn);

        if (first && spawnSize > 0) {
            lastSpawnTime = System.currentTimeMillis();
            if (GameConstants.isForceRespawn(mapid)) {
                createMobInterval = 15000;
            }
        }
    }

    public final SpawnPoint addMonsterSpawn(final MapleMonster monster, final int mobTime, final byte carnivalTeam, final String msg) {
        final Point newpos = calcPointBelow(monster.getPosition());
        newpos.y -= 1;
        final SpawnPoint sp = new SpawnPoint(monster, newpos, mobTime, carnivalTeam, msg);
        if (carnivalTeam > -1) {
            monsterSpawn.add(0, sp); //at the beginning
        } else {
            monsterSpawn.add(sp);
        }
        return sp;
    }

    public final void addAreaMonsterSpawn(final MapleMonster monster, Point pos1, Point pos2, Point pos3, final int mobTime, final String msg) {
        pos1 = calcPointBelow(pos1);
        pos2 = calcPointBelow(pos2);
        pos3 = calcPointBelow(pos3);
        if (pos1 != null) {
            pos1.y -= 1;
        }
        if (pos2 != null) {
            pos2.y -= 1;
        }
        if (pos3 != null) {
            pos3.y -= 1;
        }
        if (pos1 == null && pos2 == null && pos3 == null) {
            System.out.println("WARNING: mapid " + mapid + ", monster " + monster.getId() + " could not be spawned.");

            return;
        } else if (pos1 != null) {
            if (pos2 == null) {
                pos2 = new Point(pos1);
            }
            if (pos3 == null) {
                pos3 = new Point(pos1);
            }
        } else if (pos2 != null) {
            if (pos1 == null) {
                pos1 = new Point(pos2);
            }
            if (pos3 == null) {
                pos3 = new Point(pos2);
            }
        } else if (pos3 != null) {
            if (pos1 == null) {
                pos1 = new Point(pos3);
            }
            if (pos2 == null) {
                pos2 = new Point(pos3);
            }
        }
        monsterSpawn.add(new SpawnPointAreaBoss(monster, pos1, pos2, pos3, mobTime, msg));
    }

    public final List<MapleCharacter> getCharacters() {
        return getCharactersThreadsafe();
    }

    public final List<MapleCharacter> getCharactersThreadsafe() {
        final List<MapleCharacter> chars = new ArrayList<MapleCharacter>();

        charactersLock.readLock().lock();
        try {
            for (MapleCharacter mc : characters) {
                chars.add(mc);
            }
        } finally {
            charactersLock.readLock().unlock();
        }
        return chars;
    }

    public final MapleCharacter getCharacterById_InMap(final int id) {
        return getCharacterById(id);
    }

    public final MapleCharacter getCharacterById(final int id) {
        charactersLock.readLock().lock();
        try {
            for (MapleCharacter mc : characters) {
                if (mc.getId() == id) {
                    return mc;
                }
            }
        } finally {
            charactersLock.readLock().unlock();
        }
        return null;
    }

    public final void updateMapObjectVisibility(final MapleCharacter chr, final MapleMapObject mo) {
        if (chr == null || chr.isClone()) {
            return;
        }
        if (!chr.isMapObjectVisible(mo)) { // monster entered view range
            if (mo.getType() == MapleMapObjectType.SUMMON || mo.getPosition().distanceSq(chr.getPosition()) <= GameConstants.maxViewRangeSq()) {
                chr.addVisibleMapObject(mo);
                mo.sendSpawnData(chr.getClient());
            }
        } else if (mo.getType() != MapleMapObjectType.SUMMON && mo.getPosition().distanceSq(chr.getPosition()) > GameConstants.maxViewRangeSq()) {
            chr.removeVisibleMapObject(mo);
            mo.sendDestroyData(chr.getClient());
        }
    }

    public void moveMonster(MapleMonster monster, Point reportedPos) {
        monster.setPosition(reportedPos);

        charactersLock.readLock().lock();
        try {
            for (MapleCharacter mc : characters) {
                updateMapObjectVisibility(mc, monster);
            }
        } finally {
            charactersLock.readLock().unlock();
        }
    }

    public void movePlayer(final MapleCharacter player, final Point newPosition) {
        player.setPosition(newPosition);
        if (!player.isClone()) {
            try {
                Collection<MapleMapObject> visibleObjects = player.getAndWriteLockVisibleMapObjects();
                ArrayList<MapleMapObject> copy = new ArrayList<MapleMapObject>(visibleObjects);
                Iterator<MapleMapObject> itr = copy.iterator();
                while (itr.hasNext()) {
                    MapleMapObject mo = itr.next();
                    if (mo != null && getMapObject(mo.getObjectId(), mo.getType()) == mo) {
                        updateMapObjectVisibility(player, mo);
                    } else if (mo != null) {
                        visibleObjects.remove(mo);
                    }
                }
                for (MapleMapObject mo : getMapObjectsInRange(player.getPosition(), GameConstants.maxViewRangeSq())) {
                    if (mo != null && !player.isMapObjectVisible(mo)) {
                        mo.sendSpawnData(player.getClient());
                        visibleObjects.add(mo);
                    }
                }
            } finally {
                player.unlockWriteVisibleMapObjects();
            }
        }
    }

    public MaplePortal findClosestSpawnpoint(Point from) {
        MaplePortal closest = null;
        double distance, shortestDistance = Double.POSITIVE_INFINITY;
        for (MaplePortal portal : portals.values()) {
            distance = portal.getPosition().distanceSq(from);
            if (portal.getType() >= 0 && portal.getType() <= 2 && distance < shortestDistance && portal.getTargetMapId() == 999999999) {
                closest = portal;
                shortestDistance = distance;
            }
        }
        return closest;
    }

    public String spawnDebug() {
        StringBuilder sb = new StringBuilder("Mapobjects in map : ");
        sb.append(this.getMapObjectSize());
        sb.append(" spawnedMonstersOnMap: ");
        sb.append(spawnedMonstersOnMap);
        sb.append(" spawnpoints: ");
        sb.append(monsterSpawn.size());
        sb.append(" maxRegularSpawn: ");
        sb.append(maxRegularSpawn);
        sb.append(" actual monsters: ");
        sb.append(getNumMonsters());

        return sb.toString();
    }

    public int characterSize() {
        return characters.size();
    }

    public final int getMapObjectSize() {
        return mapobjects.size() + getCharactersSize() - characters.size();
    }

    public final int getCharactersSize() {
        int ret = 0;
        charactersLock.readLock().lock();
        try {
            final Iterator<MapleCharacter> ltr = characters.iterator();
            MapleCharacter chr;
            while (ltr.hasNext()) {
                chr = ltr.next();
                if (!chr.isClone()) {
                    ret++;
                }
            }
        } finally {
            charactersLock.readLock().unlock();
        }
        return ret;
    }

    public Collection<MaplePortal> getPortals() {
        return Collections.unmodifiableCollection(portals.values());
    }

    public int getSpawnedMonstersOnMap() {
        return spawnedMonstersOnMap.get();
    }

    //��ͼ������Ʒ����ʱ��
    public void spawnLove(final MapleLove love) {
        addMapObject(love);
        broadcastMessage(love.makeSpawnData());
        final MapTimer tMan = MapTimer.getInstance();
        tMan.schedule(new Runnable() {

            @Override
            public void run() {
                removeMapObject(love);
                broadcastMessage(love.makeDestroyData());
            }
        }, 100 * 60 * 60 * 1000);
    }

    public void broadcastMessage(String songName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    private static class slea {

        private static int readInt() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public slea() {
        }
    }

    private class ActivateItemReactor implements Runnable {

        private MapleMapItem mapitem;
        private MapleReactor reactor;
        private MapleClient c;

        public ActivateItemReactor(MapleMapItem mapitem, MapleReactor reactor, MapleClient c) {
            this.mapitem = mapitem;
            this.reactor = reactor;
            this.c = c;
        }

        @Override
        public void run() {
            if (mapitem != null && mapitem == getMapObject(mapitem.getObjectId(), mapitem.getType())) {
                if (mapitem.isPickedUp()) {
                    reactor.setTimerActive(false);
                    return;
                }
                mapitem.expire(MapleMap.this);
                reactor.hitReactor(c);
                reactor.setTimerActive(false);

                if (reactor.getDelay() > 0) {
                    MapTimer.getInstance().schedule(new Runnable() {

                        @Override
                        public void run() {
                            reactor.forceHitReactor((byte) 0);
                        }
                    }, reactor.getDelay());
                }
            } else {
                reactor.setTimerActive(false);
            }
        }
    }

    public void respawn(final boolean force) {
        lastSpawnTime = System.currentTimeMillis();
        if (force) { //cpq quick hack
            final int numShouldSpawn = monsterSpawn.size() - spawnedMonstersOnMap.get();

            if (numShouldSpawn > 0) {
                int spawned = 0;

                for (Spawns spawnPoint : monsterSpawn) {
                    spawnPoint.spawnMonster(this);
                    spawned++;
                    if (spawned >= numShouldSpawn) {
                        break;
                    }
                }
            }
        } else {
            final int numShouldSpawn = maxRegularSpawn - spawnedMonstersOnMap.get();
            if (numShouldSpawn > 0) {
                int spawned = 0;

                final List<Spawns> randomSpawn = new ArrayList<Spawns>(monsterSpawn);
                Collections.shuffle(randomSpawn);

                for (Spawns spawnPoint : randomSpawn) {
                    if ((!isSpawns) && (spawnPoint.getMobTime() > 0)) {
                        continue;
                    }
                    if (spawnPoint.shouldSpawn() || GameConstants.isForceRespawn(mapid)) {
                        spawnPoint.spawnMonster(this);
                        spawned++;
                    }
                    if (spawned >= numShouldSpawn) {
                        break;

                    }
                }
            }
        }
    }

    private static interface DelayedPacketCreation {

        void sendPackets(MapleClient c);
    }

    private static interface SpawnCondition {

        boolean canSpawn(MapleCharacter chr);
    }

    public String getSnowballPortal() {
        int[] teamss = new int[2];
        for (MapleCharacter chr : getCharactersThreadsafe()) {
            if (chr.getPosition().y > -80) {
                teamss[0]++;
            } else {
                teamss[1]++;
            }
        }
        if (teamss[0] > teamss[1]) {
            return "st01";
        } else {
            return "st00";
        }
    }

    public boolean isDisconnected(int id) {
        return dced.contains(Integer.valueOf(id));
    }

    public void addDisconnected(int id) {
        dced.add(Integer.valueOf(id));
    }

    public void resetDisconnected() {
        dced.clear();
    }

    public void startSpeedRun() {
        final MapleSquad squad = getSquadByMap();
        if (squad != null) {
            for (MapleCharacter chr : getCharactersThreadsafe()) {
                if (chr.getName().equals(squad.getLeaderName())) {
                    startSpeedRun(chr.getName());
                    return;
                }
            }
        }
    }

    public void startSpeedRun(String leader) {
        speedRunStart = System.currentTimeMillis();
        speedRunLeader = leader;
    }

    public void endSpeedRun() {
        speedRunStart = 0;
        speedRunLeader = "";
    }

    public void getRankAndAdd(String leader, String time, SpeedRunType type, long timz, Collection<String> squad) {
        try {
            //Pair<String, Map<Integer, String>>
            StringBuilder rett = new StringBuilder();
            if (squad != null) {
                for (String chr : squad) {
                    rett.append(chr);
                    rett.append(",");
                }
            }
            String z = rett.toString();
            if (squad != null) {
                z = z.substring(0, z.length() - 1);
            }
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO speedruns(`type`, `leader`, `timestring`, `time`, `members`) VALUES (?,?,?,?,?)");
            ps.setString(1, type.name());
            ps.setString(2, leader);
            ps.setString(3, time);
            ps.setLong(4, timz);
            ps.setString(5, z);
            ps.executeUpdate();
            ps.close();

            if (SpeedRunner.getInstance().getSpeedRunData(type) == null) { //great, we just add it
                SpeedRunner.getInstance().addSpeedRunData(type, SpeedRunner.getInstance().addSpeedRunData(new StringBuilder("#rThese are the speedrun times for " + type + ".#k\r\n\r\n"), new HashMap<Integer, String>(), z, leader, 1, time));
            } else {
                //i wish we had a way to get the rank
                //TODO revamp
                SpeedRunner.getInstance().removeSpeedRunData(type);
                SpeedRunner.getInstance().loadSpeedRunData(type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getSpeedRunStart() {
        return speedRunStart;
    }

    public final void disconnectAll() {
        for (MapleCharacter chr : getCharactersThreadsafe()) {
            if (!chr.isGM()) {
                chr.getClient().disconnect(true, false);
                chr.getClient().getSession().close();
            }
        }
    }

    public final void �����(int c) {
        for (MapleCharacter chr : getCharactersThreadsafe()) {
            if (!chr.isGM()) {
                chr.getClient().disconnect(true, false);
                chr.getClient().getSession().close();
            }
        }
    }

    public List<MapleNPC> getAllNPCs() {
        return getAllNPCsThreadsafe();
    }

    public List<MapleNPC> getAllNPCsThreadsafe() {
        ArrayList<MapleNPC> ret = new ArrayList<MapleNPC>();
        mapobjectlocks.get(MapleMapObjectType.NPC).readLock().lock();
        try {
            for (MapleMapObject mmo : mapobjects.get(MapleMapObjectType.NPC).values()) {
                ret.add((MapleNPC) mmo);
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.NPC).readLock().unlock();
        }
        return ret;
    }

    public final void resetNPCs() {
        List<MapleNPC> npcs = getAllNPCsThreadsafe();
        for (MapleNPC npc : npcs) {
            if (npc.isCustom()) {
                broadcastMessage(MaplePacketCreator.spawnNPC(npc, false));
                removeMapObject(npc);
            }
        }
    }

    public final void resetFully() {
        resetFully(true);
    }

    public final void ���() {
        killAllMonsters(true);
    }

    public final void resetFully(final boolean respawn) {
        killAllMonsters(false);
        reloadReactors();
        removeDrops();
        resetNPCs();
        resetSpawns();
        resetDisconnected();
        endSpeedRun();
        cancelSquadSchedule();
        resetPortals();
        environment.clear();
        if (respawn) {
            respawn(true);
        }
    }

    public final void cancelSquadSchedule() {
        squadTimer = false;
        if (squadSchedule != null) {
            squadSchedule.cancel(false);
            squadSchedule = null;
        }
    }

    public final void removeDrops() {
        List<MapleMapItem> items = this.getAllItemsThreadsafe();
        for (MapleMapItem i : items) {
            i.expire(this);
        }
    }

    public final void resetAllSpawnPoint(int mobid, int mobTime) {
        Collection<Spawns> sss = new LinkedList<Spawns>(monsterSpawn);
        resetFully();
        monsterSpawn.clear();
        for (Spawns s : sss) {
            MapleMonster newMons = MapleLifeFactory.getMonster(mobid);
            MapleMonster oldMons = s.getMonster();
            newMons.setCy(oldMons.getCy());
            newMons.setF(oldMons.getF());
            newMons.setFh(oldMons.getFh());
            newMons.setRx0(oldMons.getRx0());
            newMons.setRx1(oldMons.getRx1());
            newMons.setPosition(new Point(oldMons.getPosition()));
            newMons.setHide(oldMons.isHidden());
            addMonsterSpawn(newMons, mobTime, (byte) -1, null);
        }
        loadMonsterRate(true);
    }

    public final void resetSpawns() {
        boolean changed = false;
        Iterator<Spawns> sss = monsterSpawn.iterator();
        while (sss.hasNext()) {
            if (sss.next().getCarnivalId() > -1) {
                sss.remove();
                changed = true;
            }
        }
        setSpawns(true);
        if (changed) {
            loadMonsterRate(true);
        }
    }

    public final boolean makeCarnivalSpawn(final int team, final MapleMonster newMons, final int num) {
        MonsterPoint ret = null;
        for (MonsterPoint mp : nodes.getMonsterPoints()) {
            if (mp.team == team || mp.team == -1) {
                final Point newpos = calcPointBelow(new Point(mp.x, mp.y));
                newpos.y -= 1;
                boolean found = false;
                for (Spawns s : monsterSpawn) {
                    if (s.getCarnivalId() > -1 && (mp.team == -1 || s.getCarnivalTeam() == mp.team) && s.getPosition().x == newpos.x && s.getPosition().y == newpos.y) {
                        found = true;
                        break; //this point has already been used.
                    }
                }
                if (!found) {
                    ret = mp; //this point is safe for use.
                    break;
                }
                if (!found) {
                    ret = mp;
                    break;
                }
            }
        }
        if (ret != null) {
            newMons.setCy(ret.cy);
            newMons.setF(0); //always.
            newMons.setFh(ret.fh);
            newMons.setRx0(ret.x + 50);
            newMons.setRx1(ret.x - 50); //does this matter
            newMons.setPosition(new Point(ret.x, ret.y));
            newMons.setHide(false);
            final SpawnPoint sp = addMonsterSpawn(newMons, 1, (byte) team, null);
            sp.setCarnival(num);
        }
        return ret != null;
    }

    public final boolean makeCarnivalReactor(final int team, final int num) {
        final MapleReactor old = getReactorByName(team + "" + num);
        if (old != null && old.getState() < 5) { //already exists
            return false;
        }
        Point guardz = null;
        final List<MapleReactor> react = getAllReactorsThreadsafe();
        for (Pair<Point, Integer> guard : nodes.getGuardians()) {
            if (guard.right == team || guard.right == -1) {
                boolean found = false;
                for (MapleReactor r : react) {
                    if (r.getPosition().x == guard.left.x && r.getPosition().y == guard.left.y && r.getState() < 5) {
                        found = true;
                        break; //already used
                    }
                }
                if (!found) {
                    guardz = guard.left; //this point is safe for use.
                    break;
                }
            }
        }
        if (guardz != null) {
            final MapleReactorStats stats = MapleReactorFactory.getReactor(9980000 + team);
            final MapleReactor my = new MapleReactor(stats, 9980000 + team);
            stats.setFacingDirection((byte) 0); //always
            my.setPosition(guardz);
            my.setState((byte) 1);
            my.setDelay(0);
            my.setName(team + "" + num); //lol
            //with num. -> guardians in factory
            spawnReactor(my);
            final MCSkill skil = MapleCarnivalFactory.getInstance().getGuardian(num);
            for (MapleMonster mons : getAllMonstersThreadsafe()) {
                if (mons.getCarnivalTeam() == team) {
                    skil.getSkill().applyEffect(null, mons, false);
                }
            }
        }
        return guardz != null;
    }

    public final void blockAllPortal() {
        for (MaplePortal p : portals.values()) {
            p.setPortalState(false);
        }
    }

    public boolean getAndSwitchTeam() {
        return getCharactersSize() % 2 != 0;
    }

    public void setSquad(MapleSquadType s) {
        this.squad = s;

    }

    /**
     * ><�ж�Ƶ��>
     */
    public int getChannel() {
        return channel;
    }

    /**
     * <�жϵ�ͼ����������ȴ����>
     */
    public int getConsumeItemCoolTime() {
        return consumeItemCoolTime;
    }

    /**
     * <����ͼ����������ȴ����>
     */
    public void setConsumeItemCoolTime(int ciit) {
        this.consumeItemCoolTime = ciit;
    }

    /**
     * <����ͼ���ø���ͼ��������>
     */
    public void setPermanentWeather(int pw) {
        this.permanentWeather = pw;
    }

    /**
     * <�жϵ�ͼ���ø���ͼ��������>
     */
    public int getPermanentWeather() {
        return permanentWeather;
    }

    public void checkStates(final String chr) {
        final MapleSquad sqd = getSquadByMap();
        final EventManager em = getEMByMap();
        final int size = getCharactersSize();
        if (sqd != null) {
            sqd.removeMember(chr);
            if (em != null) {
                if (sqd.getLeaderName().equals(chr)) {
                    em.setProperty("leader", "false");
                }
                if (chr.equals("") || size == 0) {
                    sqd.clear();
                    em.setProperty("state", "0");
                    em.setProperty("leader", "true");
                    cancelSquadSchedule();
                }
            }
        }
        if (em != null && em.getProperty("state") != null) {
            if (size == 0) {
                em.setProperty("state", "0");
                if (em.getProperty("leader") != null) {
                    em.setProperty("leader", "true");
                }
            }
        }
        if (speedRunStart > 0 && speedRunLeader.equalsIgnoreCase(chr)) {
            if (size > 0) {
                broadcastMessage(MaplePacketCreator.serverNotice(5, "�ӳ����ڵ�ͼ�ϣ������սʧ��"));
            }
            endSpeedRun();
        }
    }

    public void setNodes(final MapleNodes mn) {
        this.nodes = mn;
    }

    public final List<MaplePlatform> getPlatforms() {
        return nodes.getPlatforms();
    }

    public Collection<MapleNodeInfo> getNodes() {
        return nodes.getNodes();
    }

    public MapleNodeInfo getNode(final int index) {
        return nodes.getNode(index);
    }

    public final List<Rectangle> getAreas() {
        return nodes.getAreas();
    }

    public final Rectangle getArea(final int index) {
        return nodes.getArea(index);
    }

    public final void changeEnvironment(final String ms, final int type) {
        broadcastMessage(MaplePacketCreator.environmentChange(ms, type));
    }

    public final void toggleEnvironment(final String ms) {
        if (environment.containsKey(ms)) {
            moveEnvironment(ms, environment.get(ms) == 1 ? 2 : 1);
        } else {
            moveEnvironment(ms, 1);
        }
    }

    public final void moveEnvironment(final String ms, final int type) {
        //  broadcastMessage(MaplePacketCreator.environmentMove(ms, type));
        environment.put(ms, type);
    }

    public final Map<String, Integer> getEnvironment() {
        return environment;
    }

    public final int getNumPlayersInArea(final int index) {
        int ret = 0;

        charactersLock.readLock().lock();
        try {
            final Iterator<MapleCharacter> ltr = characters.iterator();
            MapleCharacter a;
            while (ltr.hasNext()) {
                if (getArea(index).contains(ltr.next().getPosition())) {
                    ret++;
                }
            }
        } finally {
            charactersLock.readLock().unlock();
        }
        return ret;
    }

    public void broadcastGMMessage(MapleCharacter source, byte[] packet, boolean repeatToSource) {
        broadcastGMMessage(repeatToSource ? null : source, packet, Double.POSITIVE_INFINITY, source.getPosition());
    }

    private void broadcastGMMessage(MapleCharacter source, byte[] packet, double rangeSq, Point rangedFrom) {
        charactersLock.readLock().lock();
        try {
            if (source == null) {
                for (MapleCharacter chr : characters) {
                    if (chr.isStaff()) {
                        chr.getClient().sendPacket(packet);
                    }
                }
            } else {
                for (MapleCharacter chr : characters) {
                    if (chr != source && (chr.getGMLevel() >= source.getGMLevel())) {
                        chr.getClient().sendPacket(packet);
                    }
                }
            }
        } finally {
            charactersLock.readLock().unlock();
        }
    }

    public final List<Pair<Integer, Integer>> getMobsToSpawn() {
        return nodes.getMobsToSpawn();
    }

    public final List<Integer> getSkillIds() {
        return nodes.getSkillIds();
    }

    public final boolean canSpawn() {
        return lastSpawnTime > 0 && isSpawns && lastSpawnTime + createMobInterval < System.currentTimeMillis();
    }

    public final boolean canHurt() {
        if (lastHurtTime > 0 && lastHurtTime + decHPInterval < System.currentTimeMillis()) {
            lastHurtTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    /**
     * <�жϵ�ͼ����>
     */
    public List<Integer> getAllUniqueMonsters() {
        ArrayList ret = new ArrayList();
        ((ReentrantReadWriteLock) this.mapobjectlocks.get(MapleMapObjectType.MONSTER)).readLock().lock();
        try {
            for (MapleMapObject mmo : mapobjects.get(MapleMapObjectType.MONSTER).values()) {
                int theId = ((MapleMonster) mmo).getId();
                if (!ret.contains(Integer.valueOf(theId))) {
                    ret.add(Integer.valueOf(theId));
                }
            }
        } finally {
            ((ReentrantReadWriteLock) this.mapobjectlocks.get(MapleMapObjectType.MONSTER)).readLock().unlock();
        }
        return ret;
    }

    public int getNumPlayersItemsInArea(int index) {
        return getNumPlayersItemsInRect(getArea(index));
    }

    public final int getNumPlayersItemsInRect(final Rectangle rect) {
        int ret = getNumPlayersInRect(rect);

        mapobjectlocks.get(MapleMapObjectType.ITEM).readLock().lock();
        try {
            for (MapleMapObject mmo : mapobjects.get(MapleMapObjectType.ITEM).values()) {
                if (rect.contains(mmo.getPosition())) {
                    ret++;
                }
            }
        } finally {
            mapobjectlocks.get(MapleMapObjectType.ITEM).readLock().unlock();
        }
        return ret;
    }

    public int getNumPlayersInRect(Rectangle rect) {
        int ret = 0;
        this.charactersLock.readLock().lock();
        try {
            Iterator ltr = this.characters.iterator();

            while (ltr.hasNext()) {
                if (rect.contains(((MapleCharacter) ltr.next()).getTruePosition())) {
                    ret++;
                }
            }
        } finally {
            this.charactersLock.readLock().unlock();
        }
        return ret;
    }

    public final void resetAriantPQ(int level) {
        killAllMonsters(true);
        reloadReactors();
        removeDrops();
        resetNPCs();
        resetSpawns();
        resetDisconnected();
        endSpeedRun();
        resetPortals();
        environment.clear();
        respawn(true);
        for (MapleMonster mons : getAllMonstersThreadsafe()) {
            mons.changeLevel(level, true);
        }
        resetSpawnLevel(level);
    }

    public final void resetSpawnLevel(int level) {
        for (Spawns spawn : monsterSpawn) {
            if (spawn instanceof SpawnPoint) {
                ((SpawnPoint) spawn).setLevel(level);
            }
        }
    }

    public int hasBoat() {
        if (boat && docked) {
            return 2;
        } else if (boat) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setBoat(boolean hasBoat) {
        this.boat = hasBoat;
    }

    public void setDocked(boolean isDocked) {
        this.docked = isDocked;
    }

    /**
     * <�ٻ�����>
     */
    public void spawnRabbit(int hp) {
        hp = 10000;
        int mid = 9300061;
        MapleMonster onemob = MapleLifeFactory.getMonster(mid);
        final OverrideMonsterStats overrideStats = new OverrideMonsterStats(hp, onemob.getMobMaxMp(), 0, false);
        MapleMonster mob = MapleLifeFactory.getMonster(mid);
        mob.setHp(hp);
        mob.setOverrideStats(overrideStats);
        spawnMonsterOnGroundBelow(mob, new java.awt.Point(-183, -433));
    }

    /*public void spawnRabbit(int hp) {
        int mid = 9300061;
        MapleMonster onemob = MapleLifeFactory.getMonster(mid);
        final OverrideMonsterStats overrideStats = new OverrideMonsterStats(hp, onemob.getMobMaxMp(), 0, false);
        MapleMonster mob = MapleLifeFactory.getMonster(mid);
        mob.setHp(4000);
        mob.setOverrideStats(overrideStats);
        spawnMonsterOnGroundBelow(mob, new java.awt.Point(-183, -433));
    }*/
    public void spawnRabbit2(int hp) {
        int mid = 9300061;
        MapleMonster onemob = MapleLifeFactory.getMonster(mid);
        final OverrideMonsterStats overrideStats = new OverrideMonsterStats(hp, onemob.getMobMaxMp(), 0, false);
        MapleMonster mob = MapleLifeFactory.getMonster(mid);
        mob.setHp(hp);
        mob.setOverrideStats(overrideStats);
        spawnMonsterOnGroundBelow(mob, new java.awt.Point(-183, -433));
    }

    public final void KillFk(final boolean animate) {
        List<MapleMapObject> monsters = getMapObjectsInRange(new Point(0, 0), Double.POSITIVE_INFINITY, Arrays.asList(MapleMapObjectType.MONSTER));
        for (MapleMapObject monstermo : monsters) {
            MapleMonster monster = (MapleMonster) monstermo;
            if (monster.getId() == 3230300 || monster.getId() == 3230301) {
                spawnedMonstersOnMap.decrementAndGet();
                monster.setHp(0);
                broadcastMessage(MobPacket.killMonster(monster.getObjectId(), animate ? 1 : 0));
                removeMapObject(monster);
                monster.killed();
            }
        }
    }

    /**
     * <�жϵ�ͼ��������>
     */
    public final int mobCount() {
        List<MapleMapObject> mobsCount = getMapObjectsInRange(new Point(0, 0), Double.POSITIVE_INFINITY, Arrays.asList(MapleMapObjectType.MONSTER));
        return mobsCount.size();
    }

    /**
     * <�жϵ�ͼ�������>
     */
    public final int playerCount() {
        List<MapleMapObject> players = getMapObjectsInRange(new Point(0, 0), Double.POSITIVE_INFINITY, Arrays.asList(MapleMapObjectType.PLAYER));
        return players.size();
    }

    public void killMonster_2(final MapleMonster monster) { // For mobs with removeAfter
        if (monster == null) {
            return;
        }
        spawnedMonstersOnMap.decrementAndGet();
        monster.setHp(0);

        broadcastMessage(MobPacket.killMonster(monster.getObjectId(), monster.getStats().getSelfD() < 0 ? 1 : monster.getStats().getSelfD()));
        removeMapObject(monster);
        monster.killed();
    }

    public void reloadCPQ() {//���껪��ͼ
        int[] maps = {980000101, 980000201, 980000301, 980000401, 980000501, 980000601};
        for (int i = 0; i < maps.length; i++) {
            int mapid = maps[i];
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                cserv.getMapFactory().destroyMap(mapid, true);
                cserv.getMapFactory().HealMap(mapid);
            }
        }
    }

    public Collection<MapleCharacter> getNearestPvpChar(Point attacker, double maxRange, double maxHeight, Collection<MapleCharacter> chr) {
        Collection<MapleCharacter> character = new LinkedList<MapleCharacter>();
        for (MapleCharacter a : characters) {
            if (chr.contains(a.getClient().getPlayer())) {
                Point attackedPlayer = a.getPosition();
                MaplePortal Port = a.getMap().findClosestSpawnpoint(a.getPosition());
                Point nearestPort = Port.getPosition();
                double safeDis = attackedPlayer.distance(nearestPort);
                double distanceX = attacker.distance(attackedPlayer.getX(), attackedPlayer.getY());
                if (MaplePvp.isLeft) {
                    if (attacker.x > attackedPlayer.x && distanceX < maxRange && distanceX > 2
                            && attackedPlayer.y >= attacker.y - maxHeight && attackedPlayer.y <= attacker.y + maxHeight && safeDis > 2) {
                        character.add(a);
                    }
                }
                if (MaplePvp.isRight) {
                    if (attacker.x < attackedPlayer.x && distanceX < maxRange && distanceX > 2
                            && attackedPlayer.y >= attacker.y - maxHeight && attackedPlayer.y <= attacker.y + maxHeight && safeDis > 2) {
                        character.add(a);
                    }
                }
            }
        }
        return character;
    }

}/*
 final int[] zakpart = {8800103, 8800104, 8800105, 8800106, 8800107,
            8800108, 8800109, 8800110};
 */
 /*                        mob1.setHp(2100000000 * 2); //guaiwu xie liang
         * if (mapEffect != null) { mapEffect.sendStartData(chr.getClient()); }
 */
 /*
         * if (timeLimit > 0 && getForcedReturnMap() != null && !chr.isClone())
         * { chr.startMapTimeLimitTask(timeLimit, getForcedReturnMap()); if
         * (ServerConstants.�����ʾ || �����ͼ������ʾ����) {
         * System.out.println("�����ͼ��������I"); } } if
         * (chr.getBuffedValue(MapleBuffStat.MONSTER_RIDING) != null) { if
         * (FieldLimitType.Mount.check(fieldLimit)) {
         * chr.cancelBuffStats(MapleBuffStat.MONSTER_RIDING); if
         * (ServerConstants.�����ʾ || �����ͼ������ʾ����) {
         * System.out.println("�����ͼ��������J"); } } }
 */
 /*
         * if (!chr.isClone()) { if (chr.getEventInstance() != null &&
         * chr.getEventInstance().isTimerStarted() && !chr.isClone()) {
         * chr.getClient().sendPacket(MaplePacketCreator.getClock((int)
         * (chr.getEventInstance().getTimeLeft() / 1000))); if
         * (ServerConstants.�����ʾ || �����ͼ������ʾ����) {
         * System.out.println("�����ͼ��������K"); } } if (hasClock()) { final Calendar
         * cal = Calendar.getInstance();
         * chr.getClient().sendPacket((MaplePacketCreator.getClockTime(cal.get(Calendar.HOUR_OF_DAY),
         * cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND)))); if
         * (ServerConstants.�����ʾ || �����ͼ������ʾ����) {
         * System.out.println("�����ͼ��������L"); } } if (chr.getCarnivalParty() !=
         * null && chr.getEventInstance() != null) {
         * chr.getEventInstance().onMapLoad(chr); if (ServerConstants.�����ʾ ||
         * �����ͼ������ʾ����) { System.out.println("�����ͼ��������M"); } }
         * MapleEvent.mapLoad(chr, channel); if (ServerConstants.�����ʾ ||
         * �����ͼ������ʾ����) { System.out.println("�����ͼ��������N"); } if (getSquadBegin()
         * != null && getSquadBegin().getTimeLeft() > 0 &&
         * getSquadBegin().getStatus() == 1) {
         * chr.getClient().sendPacket(MaplePacketCreator.getClock((int)
         * (getSquadBegin().getTimeLeft() / 1000))); if (ServerConstants.�����ʾ ||
         * �����ͼ������ʾ����) { System.out.println("�����ͼ��������O"); } }
 */
 /*
         * if (mapid / 1000 != 105100 && mapid / 100 != 8020003 && mapid / 100
         * != 8020008) { //no boss_balrog/2095/coreblaze/auf. but coreblaze/auf
         * does AFTER final MapleSquad sqd = getSquadByMap(); //for all squads
         * if (!squadTimer && sqd != null &&
         * chr.getName().equals(sqd.getLeaderName()) && !chr.isClone()) {
         * //leader? display doShrine(false); squadTimer = true; } if
         * (ServerConstants.�����ʾ || �����ͼ������ʾ����) {
         * System.out.println("�����ͼ��������P"); } }
 */
 /*
         * if (getNumMonsters() > 0 && (mapid == 280030001 || mapid == 240060201
         * || mapid == 280030000 || mapid == 240060200 || mapid == 220080001 ||
         * mapid == 541020800 || mapid == 541010100)) { String music =
         * "Bgm09/TimeAttack"; switch (mapid) { case 240060200: case 240060201:
         * music = "Bgm14/HonTale"; break; case 280030000: case 280030001: music
         * = "Bgm06/FinalFight"; break; case 200090000: case 200090010: music =
         * "Bgm04/ArabPirate"; break; }
         * chr.getClient().sendPacket(MaplePacketCreator.musicChange(music));
         * if (ServerConstants.�����ʾ || �����ͼ������ʾ����) {
         * System.out.println("�����ͼ��������Q"); } //maybe timer too for zak/ht }
 */
 /*
         * for (final WeakReference<MapleCharacter> chrz : chr.getClones()) { if
         * (chrz.get() != null) { chrz.get().setPosition(new
         * Point(chr.getPosition())); chrz.get().setMap(this);
         * addPlayer(chrz.get()); } if (ServerConstants.�����ʾ || �����ͼ������ʾ����) {
         * System.out.println("�����ͼ��������R"); } }
 */
//  if (mapid == 914000000) {
///     chr.getClient().sendPacket(MaplePacketCreator.addTutorialStats());
//   if (ServerConstants.�����ʾ || �����ͼ������ʾ����) {
//       System.out.println("�����ͼ��������S");
//  }
// }
/*
         * else if (mapid == 105100300 && chr.getLevel() >= 91) {
         * chr.getClient().sendPacket(MaplePacketCreator.temporaryStats_Balrog(chr));
         * if (ServerConstants.�����ʾ || �����ͼ������ʾ����) {
         * System.out.println("�����ͼ��������T"); } }
 */
//  else if (mapid == 140090000 || mapid == 105100301 || mapid == 105100401 || mapid == 105100100) {
//  chr.getClient().sendPacket(MaplePacketCreator.temporaryStats_Reset());
//   if (ServerConstants.�����ʾ || �����ͼ������ʾ����) {
//      System.out.println("�����ͼ��������U");
//  }
// }
//  }
/*
                    //���˵���
//                    if (!chr.haveItem(5220008, 0, false, false)) {
//                        MapleInventoryManipulator.removeById(chr.getClient(), GameConstants.getInventoryType(5220008), 5220008, 1, true, false);
//                        final double ���� = Math.ceil(Math.random() * 3);
//                        if (���� <= 1) {
//                            idrop = new Item(2022450, (byte) 0, (short) 1, (byte) 0);
//                        } else if (���� == 2) {
//                            idrop = new Item(2022451, (byte) 0, (short) 1, (byte) 0);
//                        } else if (���� == 3) {
//                            idrop = new Item(2022452, (byte) 0, (short) 1, (byte) 0);
//                        } else {
//                            idrop = new Item(2022450, (byte) 0, (short) 1, (byte) 0);
//                        }
//                    }
                    /*OtherSettings item = new OtherSettings();
                    String itempb[] = item.getItempb_id();
                    for (int i = 0; i < itempb.length; i++) {
                        if (de.itemId == Integer.parseInt(itempb[i])) {
                            if (server.Start.ConfigValuesMap.get("QQ�����˿���") == 0) {
                                if (MapleParty.�����˴��� > 0) {
                                    return;
                                }
                                sendMsgToQQGroup("[��������] : ��ϲ��� [ " + chr.getName() + " ] ��ɱ���������Ʒ [ " + ii.getName(de.itemId) + " ]");
                            }
                            String text = "[��������] : ��ϲ��� [ " + chr.getName() + " ] ��ɱ���������Ʒ [ " + ii.getName(de.itemId) + " ]";
                            World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, channel, text));
                        }
                    }*/
//if (GameConstants.isEvan(chr.getJob()) && chr.getJob() >= 2200 &&
//         * chr.getBuffedValue(MapleBuffStat.MONSTER_RIDING) == null) { if
//         * (chr.getDragon() == null) { chr.makeDragon(); }
//         * spawnDragon(chr.getDragon()); if (!chr.isClone()) {
//         * updateMapObjectVisibility(chr, chr.getDragon()); } }

// if ((mapid == 10000 && chr.getJob() == 0) || (mapid == 130030000 && chr.getJob() == 1000) || (mapid == 914000000 && chr.getJob() == 2000) || (mapid == 900010000 && chr.getJob() == 2001)) {
//            chr.getClient().sendPacket(MaplePacketCreator.startMapEffect("Welcome to " + chr.getClient().getChannelServer().getServerName() + "!", 5122000, true));
//            chr.dropMessage(1, "Welcome to " + chr.getClient().getChannelServer().getServerName() + ", " + chr.getName() + " ! \r\nUse @joyce to collect your Item Of Appreciation once you're level 10! \r\nUse @help for commands. \r\nGood luck and have fun!");
//   chr.dropMessage(1, "���ּ���ӛ����һ�D֮ǰ�c�� ʮ��֮�����ȥ�����Ј��ҶY����I�|��");
//            chr.dropMessage(5, "Use @joyce to collect your Item Of Appreciation once you're level 10! Use @help for commands. Good luck and have fun!");
        //    }
