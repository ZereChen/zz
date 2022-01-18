package server.custom.respawn;

import handling.channel.ChannelServer;
import handling.world.World;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import server.Randomizer;
import server.ServerProperties;
import server.Timer;
import server.Timer.RespawnTimer;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import server.life.OverrideMonsterStats;
import server.maps.MapleMap;
import tools.MaplePacketCreator;

/**
 *
 * @author Administrator
 */
public class RespawnManager {

    private static class InstanceHolder {

        public static final RespawnManager instance = new RespawnManager();
    }

    public static RespawnManager getInstance() {
        return InstanceHolder.instance;
    }

    private List<RespawnInfo> respawnInfolist = new ArrayList<>();
    private ScheduledFuture<?> spawnTime;

    public void run() {
        reloadSpawn();
        startTime();
    }

    private void reloadSpawn() {
        respawnInfolist.clear();
        //respawnInfolist.add(new RespawnInfo(��ͼ, ����, ����, new Point(����X, ����Y), Ƶ��, ��������, ���ﾭ��, ����, ʱ, ��));
        // ˢ��ţ
        respawnInfolist.add(new RespawnInfo(910000000, 100100, 10, new Point(912, -806), 1, 10000));

    }

    public void spawnInTime() {
        for (RespawnInfo respawnInfo : respawnInfolist) {
            ChannelServer channelServer = ChannelServer.getInstance(respawnInfo.getChannelId());
            if (channelServer == null) {

                continue;
            }
            MapleMap mapleMap = channelServer.getMapFactory().getMap(respawnInfo.getMapId());
            if (mapleMap == null) {

                continue;
            }
            MapleMonster mobName = MapleLifeFactory.getMonster(respawnInfo.getMobId());
            if (mobName == null) {

                continue;
            }
            for (int i = 0; i < respawnInfo.getMobCount(); i++) {
                MapleMonster mapleMonster = MapleLifeFactory.getMonster(respawnInfo.getMobId());
                if (respawnInfo.getHp() > 0) {
                    mapleMonster.setHp(respawnInfo.getHp());
                }
                if (respawnInfo.getExp() > 0) {
                    mapleMonster.setOverrideStats(new OverrideMonsterStats(mapleMonster.getHp(), mapleMonster.getMp(), respawnInfo.getExp()));
                }
                mapleMonster.setPosition(respawnInfo.getSpawnPoint());
                mapleMap.spawnMonster(mapleMonster, -2);
            }
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[���﹥��]���l��:" + channelServer.getChannel() + "��ͼ:" + mapleMap.getMapName() + " ����:" + mobName.getStats().getName() + " ����:" + respawnInfo.getMobCount()));
        }
    }

    public void startTime() {
        if (spawnTime != null) {
            spawnTime.cancel(true);
        }
        spawnTime = Timer.RespawnTimer.getInstance().register(new Runnable() {
            public void run() {
                spawnInTime();
            }
        }, 1000 * 10);
    }

    public void �ر�startTime() {
        if (spawnTime != null) {
            spawnTime.cancel(true);
        }
        spawnTime = null;
    }

    public static void main(String args[]) {
        RespawnTimer.getInstance().start();
        RespawnManager.getInstance().run();
    }
}
