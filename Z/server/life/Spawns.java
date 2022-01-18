package server.life;

import java.awt.Point;
import server.maps.MapleMap;

public abstract class Spawns {

    public abstract MapleMonster getMonster();

    public abstract byte getCarnivalTeam();

    public abstract boolean shouldSpawn();

    public abstract int getCarnivalId();

    public abstract MapleMonster spawnMonster(MapleMap map);

    public abstract int getMobTime();

    public abstract Point getPosition();
}
