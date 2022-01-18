package server.life;

import java.lang.ref.WeakReference;

public class SummonAttackEntry {

    private WeakReference<MapleMonster> mob;
    private int damage;

    public SummonAttackEntry(MapleMonster mob, int damage) {
        super();
        this.mob = new WeakReference<MapleMonster>(mob);
        this.damage = damage;
    }

    public MapleMonster getMonster() {
        return mob.get();
    }

    public int getDamage() {
        return damage;
    }
}
