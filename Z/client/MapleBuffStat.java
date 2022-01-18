package client;

import java.io.Serializable;

public enum MapleBuffStat implements Serializable {
    //DICE = 0x100000, the roll is determined in the long-ass packet itself as buffedvalue
    //ENERGY CHARGE = 0x400000
    //teleport mastery = 0x800000
    //PIRATE'S REVENGE = 0x4000000
    //DASH = 0x8000000 and 0x10000000
    //SPEED INFUSION = 0x40000000
    //MONSTER RIDER = 0x20000000
    //COMBAT ORDERS = 0x1000000
    Ӱ����(0x400000000000000L),
    ENHANCED_WDEF(0x1, true),
    ENHANCED_MDEF(0x2, true),
    PERFECT_ARMOR(0x4, true),
    SATELLITESAFE_PROC(0x8, true),
    SATELLITESAFE_ABSORB(0x10, true),
    CRITICAL_RATE_BUFF(0x40, true),
    MP_BUFF(0x80, true),
    DAMAGE_TAKEN_BUFF(0x100, true),
    DODGE_CHANGE_BUFF(0x200, true),
    CONVERSION(0x400, true),
    REAPER(0x800, true),
    MECH_CHANGE(0x2000, true), //determined in packet by [skillLevel or something] [skillid] 1E E0 58 52???
    DARK_AURA(0x8000, true),
    BLUE_AURA(0x10000, true),
    YELLOW_AURA(0x20000, true),
    //ENERGY_CHARGE(0x80000000000L, true),
    ENERGY_CHARGE(0x2000000000L, true),
    DASH(1610612736L),
    DASH_SPEED(549755813888L),
    DASH_JUMP(0x4000000000L),
    //DASH_SPEED(0x100000000000L, true),
    // DASH_JUMP(0x200000000000L, true),
    �����ƶ�(549755813888L),
    ������Ծ(0x4000000000L),
    // MONSTER_RIDING(0x400000000000L, true),
    // SPEED_INFUSION(0x800000000000L, true),
    // HOMING_BEACON(0x1000000000000L, true),
    MONSTER_RIDING(0x10000000000L, true),
    SPEED_INFUSION(/*
     * 0x40000000000L
             */140737488355328L, true),
    HOMING_BEACON(0x80000000000L, true),
    ELEMENT_RESET(0x200000000000000L, true),
    // BODY_PRESSURE(0x8000000000000000L, true),
    BODY_PRESSURE(17592186044416L, true),
    SMART_KNOCKBACK(0x1, false),
    PYRAMID_PQ(0x2, false),
    LIGHTNING_CHARGE(0x4, false),
    //POST BB
    //DUMMY_STAT0     (0x8000000L, true), //appears on login
    //DUMMY_STAT1     (0x10000000L, true),
    //DUMMY_STAT2     (0x20000000L, true),
    //DUMMY_STAT3     (0x40000000L, true),
    //DUMMY_STAT4     (0x80000000L, true),

    SOUL_STONE(0x20000000000L, true), //same as pyramid_pq
    MAGIC_SHIELD(0x800000000000L, true),
    MAGIC_RESISTANCE(0x1000000000000L, true),
    SOARING(0x4000000000000L, true),
    //    LIGHTNING_CHARGE(0x10000000000000L, true),
    //db stuff
    MIRROR_IMAGE(0x20000000000000L, true),
    OWL_SPIRIT(0x40000000000000L, true),
    FINAL_CUT(0x100000000000000L, true),
    THORNS(0x200000000000000L, true),
    DAMAGE_BUFF(0x400000000000000L, true),
    RAINING_MINES(0x1000000000000000L, true),
    ENHANCED_MAXHP(0x2000000000000000L, true),
    ENHANCED_MAXMP(0x4000000000000000L, true),
    ENHANCED_WATK(0x8000000000000000L, true),
    MORPH(0x2),
    RECOVERY(0x4),
    MAPLE_WARRIOR(0x8),
    STANCE(0x10),
    SHARP_EYES(0x20),
    MANA_REFLECTION(0x40),
    DRAGON_ROAR(0x80), // Stuns the user

    SPIRIT_CLAW(0x100),
    INFINITY(0x200),
    HOLY_SHIELD(0x400),
    HAMSTRING(0x800),
    BLIND(0x1000),
    CONCENTRATE(0x2000),
    ECHO_OF_HERO(0x8000),
    ��ס̩ɽ(0x8000),//1221002
    UNKNOWN3(0x10000),
    GHOST_MORPH(0x20000),
    ARIANT_COSS_IMU(0x40000), // The white ball around you

    DROP_RATE(0x100000),
    MESO_RATE(0x200000),
    EXPRATE(0x400000),
    ACASH_RATE(0x800000),
    GM_HIDE(0x1000000),
    UNKNOWN7(0x2000000),
    ILLUSION(0x4000000),
    BERSERK_FURY(0x8000000),
    DIVINE_BODY(0x10000000),
    SPARK(0x20000000),
    ARIANT_COSS_IMU2(0x40000000), // no idea, seems the same
    FINALATTACK(0x80000000L),
    WATK(0x100000000L),
    WDEF(0x200000000L),
    MATK(0x400000000L),
    MDEF(0x800000000L),
    ACC(0x1000000000L),
    AVOID(0x2000000000L),
    HANDS(0x4000000000L),
    SPEED(0x8000000000L),
    JUMP(0x10000000000L),
    MAGIC_GUARD(0x20000000000L),
    DARKSIGHT(0x40000000000L),
    BOOSTER(0x80000000000L),
    POWERGUARD(0x100000000000L),
    MAXHP(0x200000000000L),
    MAXMP(0x400000000000L),
    INVINCIBLE(0x800000000000L),
    SOULARROW(0x1000000000000L),
    SUMMON(0x20000000000000L), //hack buffstat for summons ^.- (does/should not increase damage... hopefully <3)
    WK_CHARGE(0x40000000000000L),//����MP��

    DRAGONBLOOD(0x80000000000000L),//��������

    HOLY_SYMBOL(0x100000000000000L),//��ʥ��
    ��ʥ��(0x100000000000000L),//��ʥ��
    MESOUP(0x200000000000000L),
    SHADOWPARTNER(0x400000000000000L),
    PICKPOCKET(0x800000000000000L),
    PUPPET(0x800000000000000L), // HACK - shares buffmask with pickpocket - odin special ^.-

    ����(0x2000000000L, true),
    ������ȡ(0x2000000000L, true),
    ��輼��(1099511627776L),
    MESOGUARD(0x1000000000000000L),
    /**
     * ***<ս����mask>****
     */
    //ì����ǿ��(4294967296L, 3),//��������mask
    //ì����ǿ��(0x100000000L, 3),
    //ì����ǿ��(0x40, 3),
    ARAN_COMBO(0x1000000000000000L, true),
    //������Ѫ
    COMBO_DRAIN(0x2000000000000000L, true),
    COMBO_BARRIER(0x4000000000000000L, true),
    ì����ǿ��(8589934592L, true),
    ì����ǿ������(8589934592L, true),//������mask
    ì����ǿ��ħ������(34359738368L, true),//ħ��������mask
    ��ѹ(17592186044416L),//�˺�������mask 17592186044416
    //������Ѫ(18014398509481984L),//��Ѫ���ܵ�mask
    ���ɻ���(140737488355328L), //���ٹ�����ײ�˺��ٷֱȵ�mask
    ս��֮��(140737488355328L),//���ٹ�����ײ�˺��ٷֱȵ�mask
    /////////////
    ������(0x100000000L),
    �������(0x200000000L),
    ħ������(0x400000000L),
    ħ������(0x800000000L),
    ������(0x1000000000L),
    �ر���(0x2000000000L),
    �ּ�(0x4000000000L),
    �ƶ��ٶ�(0x8000000000L),
    ��Ծ��(0x10000000000L),
    ħ����(0x20000000000L),
    ������(0x40000000000L),
    ��������(0x80000000000L),
    �˺�����(0x100000000000L),
    ���HP(0x200000000000L),
    ���MP(0x400000000000L),
    ��֮����(0x800000000000L),
    ���μ���(0x1000000000000L),
    ��������(0x20000000000000L),
    COMBO(0x20000000000000L),
    �ٻ���(0x20000000000000L), //hack buffstat for summons ^.- (does/should not increase damage... hopefully <3)
    ���Թ���(0x40000000000000L),
    ��֮��(0x80000000000000L),
    �۲���(0x200000000000000L),
    ������(0x800000000000000L),
    ������(0x800000000000000L), // HACK - shares buffmask with ������ - odin special ^.-
    ��Ǯ����(0x1000000000000000L),
    ��������(0x2000, true), //determined in packet by [skillLevel or something] [skillid] 1E E0 58 52???  ���׸ı䣿
    �ڰ�����(0x8000, true),
    ��ɫ����(0x10000, true),
    ��ɫ����(0x20000, true),
    �������(0x800000, 4),
    �������(0x2000000000L, true),
    ���޼���(0x10000000000L, true),
    ��������(0x40000000000L, true),//�޸�����������38����
    //��������(0x100000000000L, true),
    //��������(0x20000000, 12),
    ��������(0x40000000, 8), //probably changed
    ��Ȼ������(0x200000000000000L, true),
    //���ɻ���(0x1, false),//?
    ���֮ʯ(0x20000000000L, true), //��ͬ�ڽ�����PQ
    ��߶�Ա������_BUFF(0x400000000000000L, true),
    ��ǿ_���HP(0x2000000000000000L, true),
    ��ǿ_���MP(0x4000000000000000L, true),
    ��ǿ_������(0x8000000000000000L, true),
    ��ǿ_���������(0x1, true),
    ��ǿ_ħ��������(0x2, true),
    ����(0x2),
    �Ŷ�����(0x4),
    ð�յ���ʿ(0x8),
    ����̩ɽ(0x10),
    ���۾���(0x20),
    ħ������(0x40),
    ������(0x80), // Stuns the user
    ��������(0x100),
    �ռ�����(0x200),
    ʥ��֮��(0x400),
    ���˼�(0x800),
    ���ۼ�(0x1000),
    ���о���(0x2000),
    Ӣ��֮����(0x8000),
    ����_��(0x100000),
    ���_��(0x200000),
    ����_��(0x400000),
    �ֽ�_��(0x800000),
    GM_������(0x1000000),//��֪����ʲô�������Ҳû�е���
    ��ս��(0x8000000),
    ��հ���(0x10000000),
    �����(0x20000000),
    �ռ�����(0x80000000L),
    ��ѩì(0x4000, 2),
    ������Ѫ(0x2000000000000000L, true),
    ս��ѹ(0x20, 3),
    // ����ǿ��(0x20,true),
    //ì����ǿ������(8589934592L, true),//������mask
    //ì����ǿ��ħ������(34359738368L, true),//ħ��������mask
    //��ѹ(17592186044416L),//�˺�������mask 17592186044416
    //������Ѫ(18014398509481984L),//��Ѫ���ܵ�mask
    //ս��֮��(140737488355328L),//���ٹ�����ײ�˺��ٷֱȵ�mask
    //ì����ǿ��(0x40, 3),

    WEAKEN(0x4000000000000000L),//��֪����ʲô�������Ҳû�е���
    �ӵ�����(0xFFFFF, 1);

    /**
     * ***<�޷�ʶ��ļ���>*****
     */
    //ì����ǿ��(0x40, 3),
    /**
     * *************************
     */
    private static final long serialVersionUID = 0L;
    private final long buffstat;
    private final long maskPos;
    private final boolean first;

    private MapleBuffStat(long buffstat) {
        this.buffstat = buffstat;
        this.maskPos = 4L;
        first = false;
    }

    private MapleBuffStat(long buffstat, long maskPos) {
        this.buffstat = buffstat;
        this.maskPos = maskPos;
        this.first = false;
    }

    private MapleBuffStat(long buffstat, boolean first) {
        this.buffstat = buffstat;
        this.maskPos = 4L;
        this.first = first;
    }

    public long getMaskPos() {
        return this.maskPos;
    }

    public final boolean isFirst() {
        return first;
    }

    public final long getValue() {
        return buffstat;
    }

}
