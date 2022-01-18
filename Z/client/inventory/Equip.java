package client.inventory;

import static abc.Game2.����װ��;
import static abc.Game2.����װ��;
import client.MapleClient;
import constants.GameConstants;
import handling.world.World;
import java.io.Serializable;
import java.util.List;
import server.MapleItemInformationProvider;
import server.Randomizer;
import server.ServerProperties;
import tools.MaplePacketCreator;
import tools.Pair;
//װ�����������飿��

public class Equip extends Item implements IEquip, Serializable {

    private byte upgradeSlots = 0, level = 0, vicioushammer = 0, enhance = 0;
    private short str = 0, dex = 0, _int = 0, luk = 0, hp = 0, mp = 0, watk = 0, matk = 0, wdef = 0, mdef = 0, acc = 0, avoid = 0, hands = 0, speed = 0, jump = 0, potential1 = 0, potential2 = 0, potential3 = 0, hpR = 0, mpR = 0;
    private int itemEXP = 0, durability = -1;
    private byte itemLevel;
    private String DaKongFuMo;

    public Equip(int id, short position) {
        super(id, position, (short) 1, (byte) 0);
    }

    public Equip(int id, short position, byte flag) {
        super(id, position, (short) 1, flag);
    }

    public Equip(int id, short position, int uniqueid, byte flag) {
        super(id, position, (short) 1, flag, uniqueid);
    }

    @Override
    public IItem copy() {
        Equip ret = new Equip(getItemId(), getPosition(), getUniqueId(), getFlag());
        ret.str = str;
        ret.dex = dex;
        ret._int = _int;
        ret.luk = luk;
        ret.hp = hp;
        ret.mp = mp;
        ret.matk = matk;
        ret.mdef = mdef;
        ret.watk = watk;
        ret.wdef = wdef;
        ret.acc = acc;
        ret.avoid = avoid;
        ret.hands = hands;
        ret.speed = speed;
        ret.jump = jump;
        ret.enhance = enhance;
        ret.upgradeSlots = upgradeSlots;
        ret.level = level;
        ret.itemEXP = itemEXP;
        ret.durability = durability;
        ret.vicioushammer = vicioushammer;
        ret.potential1 = potential1;
        ret.potential2 = potential2;
        ret.potential3 = potential3;
        ret.hpR = hpR;
        ret.mpR = mpR;
        ret.itemLevel = this.itemLevel;
        ret.setGiftFrom(getGiftFrom());
        ret.setOwner(getOwner());
        ret.setQuantity(getQuantity());
        ret.setExpiration(getExpiration());
        ret.setDaKongFuMo(getDaKongFuMo());
        ret.setPrice(getPrice());
        return ret;
    }

    @Override
    public byte getType() {
        return 1;
    }

    @Override
    public byte getUpgradeSlots() {
        return upgradeSlots;
    }

    @Override
    public short getStr() {
        return str;
    }

    @Override
    public short getDex() {
        return dex;
    }

    @Override
    public short getInt() {
        return _int;
    }

    @Override
    public short getLuk() {
        return luk;
    }

    @Override
    public short getHp() {
        return hp;
    }

    @Override
    public short getMp() {
        return mp;
    }

    @Override
    public short getWatk() {
        return watk;
    }

    @Override
    public short getMatk() {
        return matk;
    }

    @Override
    public short getWdef() {
        return wdef;
    }

    @Override
    public short getMdef() {
        return mdef;
    }

    @Override
    public short getAcc() {
        return acc;
    }

    @Override
    public short getAvoid() {
        return avoid;
    }

    @Override
    public short getHands() {
        return hands;
    }

    @Override
    public short getSpeed() {
        return speed;
    }

    @Override
    public short getJump() {
        return jump;
    }

    public void setStr(short str) {
        if (str < 0) {
            str = 0;
        }
        this.str = str;
    }

    public void setDex(short dex) {
        if (dex < 0) {
            dex = 0;
        }
        this.dex = dex;
    }

    public void setInt(short _int) {
        if (_int < 0) {
            _int = 0;
        }
        this._int = _int;
    }

    public void setLuk(short luk) {
        if (luk < 0) {
            luk = 0;
        }
        this.luk = luk;
    }

    public void setHp(short hp) {
        if (hp < 0) {
            hp = 0;
        }
        this.hp = hp;
    }

    public void setMp(short mp) {
        if (mp < 0) {
            mp = 0;
        }
        this.mp = mp;
    }

    public void setWatk(short watk) {
        if (watk < 0) {
            watk = 0;
        }
        this.watk = watk;
    }

    public void setMatk(short matk) {
        if (matk < 0) {
            matk = 0;
        }
        this.matk = matk;
    }

    public void setWdef(short wdef) {
        if (wdef < 0) {
            wdef = 0;
        }
        this.wdef = wdef;
    }

    public void setMdef(short mdef) {
        if (mdef < 0) {
            mdef = 0;
        }
        this.mdef = mdef;
    }

    public void setAcc(short acc) {
        if (acc < 0) {
            acc = 0;
        }
        this.acc = acc;
    }

    public void setAvoid(short avoid) {
        if (avoid < 0) {
            avoid = 0;
        }
        this.avoid = avoid;
    }

    public void setHands(short hands) {
        if (hands < 0) {
            hands = 0;
        }
        this.hands = hands;
    }

    public void setSpeed(short speed) {
        if (speed < 0) {
            speed = 0;
        }
        this.speed = speed;
    }

    public void setJump(short jump) {
        if (jump < 0) {
            jump = 0;
        }
        this.jump = jump;
    }

    public void setUpgradeSlots(byte upgradeSlots) {
        this.upgradeSlots = upgradeSlots;
    }

    @Override
    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    @Override
    public byte getViciousHammer() {
        return vicioushammer;
    }

    public void setViciousHammer(byte ham) {
        vicioushammer = ham;
    }

    @Override
    public int getItemEXP() {
        return itemEXP;
    }

    public void setItemEXP(int itemEXP) {
        if (itemEXP < 0) {
            itemEXP = 0;
        }
        this.itemEXP = itemEXP;
    }

    @Override
    public int getEquipExp() {
        if (itemEXP <= 0) {
            return 0;
        }
        //aproximate value
        if (GameConstants.isWeapon(getItemId())) {
            return itemEXP / IEquip.WEAPON_RATIO;
        } else {
            return itemEXP / IEquip.ARMOR_RATIO;
        }
    }

    @Override
    public int getEquipExpForLevel() {
        if (getEquipExp() <= 0) {
            return 0;
        }
        int expz = getEquipExp();
        for (int i = getBaseLevel(); i <= GameConstants.getMaxLevel(getItemId()); i++) {
            if (expz >= GameConstants.getExpForLevel(i, getItemId())) {
                expz -= GameConstants.getExpForLevel(i, getItemId());
            } else { //for 0, dont continue;
                break;
            }
        }
        return expz;
    }

    /**
     *
     * @return
     */
    @Override
    public int getExpPercentage() {
        return this.itemEXP;
    }

    /*@Override
    public int getExpPercentage() {
        if (getEquipLevels() < getBaseLevel() || getEquipLevels() > GameConstants.getMaxLevel(getItemId()) || GameConstants.getExpForLevel(getEquipLevels(), getItemId()) <= 0) {
            return 0;
        }
        return getEquipExpForLevel() * 100 / GameConstants.getExpForLevel(getEquipLevels(), getItemId());
    }*/
    public int getEquipLevels() {
        if (GameConstants.getMaxLevel(getItemId()) <= 0) {
            return 0;
        } else if (getEquipExp() <= 0) {
            return getBaseLevel();
        }
        int levelz = getBaseLevel();
        int expz = getEquipExp();
        for (int i = levelz; (GameConstants.getStatFromWeapon(getItemId()) == null ? (i <= GameConstants.getMaxLevel(getItemId())) : (i < GameConstants.getMaxLevel(getItemId()))); i++) {
            if (expz >= GameConstants.getExpForLevel(i, getItemId())) {
                levelz++;
                expz -= GameConstants.getExpForLevel(i, getItemId());
            } else { //for 0, dont continue;
                break;
            }
        }
        return levelz;
    }

    @Override
    public int getBaseLevel() {
        return (GameConstants.getStatFromWeapon(getItemId()) == null ? 1 : 0);
    }

    @Override
    public void setQuantity(short quantity) {
        if (quantity < 0 || quantity > 1) {
            throw new RuntimeException("Setting the quantity to " + quantity + " on an equip (itemid: " + getItemId() + ")");
        }
        super.setQuantity(quantity);
    }

    @Override
    public int getDurability() {
        return durability;
    }

    public void setDurability(final int dur) {
        this.durability = dur;
    }

    @Override
    public byte getEnhance() {
        return enhance;
    }

    public void setEnhance(final byte en) {
        this.enhance = en;
    }

    @Override
    public short getPotential1() {
        return potential1;
    }

    public void setPotential1(final short en) {
        this.potential1 = en;
    }

    @Override
    public short getPotential2() {
        return potential2;
    }

    public void setPotential2(final short en) {
        this.potential2 = en;
    }

    @Override
    public short getPotential3() {
        return potential3;
    }

    public void setPotential3(final short en) {
        this.potential3 = en;
    }

    @Override
    public byte getState() {
        final int pots = potential1 + potential2 + potential3;
        if (potential1 >= 30000 || potential2 >= 30000 || potential3 >= 30000) {
            return 7;
        } else if (potential1 >= 20000 || potential2 >= 20000 || potential3 >= 20000) {
            return 6;
        } else if (pots >= 1) {
            return 5;
        } else if (pots < 0) {
            return 1;
        }
        return 0;
    }
//
//    public void resetPotential() { //equip first receive
//        //0.04% chance unique, 4% chance epic, else rare
//        final int rank = Randomizer.nextInt(100) < 4 ? (Randomizer.nextInt(100) < 4 ? -7 : -6) : -5;
//        setPotential1((short) rank);
//        setPotential2((short) (Randomizer.nextInt(10) == 1 ? rank : 0)); //1/10 chance of 3 line
//        setPotential3((short) 0); //just set it theoretically
//    }
//
//    public void renewPotential() {
//        //4% chance upgrade
//        final int rank = Randomizer.nextInt(100) < 4 && getState() != 7 ? -(getState() + 1) : -(getState());
//        setPotential1((short) rank);
//        setPotential2((short) (getPotential3() > 0 ? rank : 0)); //1/10 chance of 3 line
//        setPotential3((short) 0); //just set it theoretically
//    }

    @Override
    public short getHpR() {
        return hpR;
    }

    public void setHpR(final short hp) {
        this.hpR = hp;
    }

    @Override
    public short getMpR() {
        return mpR;
    }

    public void setMpR(final short mp) {
        this.mpR = mp;
    }

    public void gainItemLevel() {
        this.itemLevel = (byte) (this.itemLevel + 1);
    }

    public void gainItemExp(MapleClient c, int gain, boolean timeless) {
        this.itemEXP += gain;
        int expNeeded = 0;
        if (timeless) {
            expNeeded = ExpTable.getTimelessItemExpNeededForLevel(this.itemLevel + 1);
        } else {
            expNeeded = ExpTable.getTimelessItemExpNeededForLevel(this.itemLevel + 1);//getReverseItemExpNeededForLevel
        }
        if (this.itemEXP >= expNeeded) {
            // gainItemLevel();
            gainItemLevel(c, timeless);
            //gainLevel();
            //װ������Ч��s
            c.sendPacket(MaplePacketCreator.showItemLevelup());
        }
    }

    public void gainItemLevel(MapleClient c, boolean timeless) {
        List<Pair<String, Integer>> stats = MapleItemInformationProvider.getInstance().getItemLevelupStats(getItemId(), itemLevel, timeless);
        int �ȼ� = getEquipLevel();

        String ���� = "";
        String ����1 = "";
        String ����2 = "";
        String ����3 = "";
        switch (�ȼ�) {
            case 1:
                ���� = "ZEV.��ά����1";
                ����1 = "ZEV.����ħ��1";
                ����2 = "ZEV.˫��1";
                ����3 = "ZEV.˫��1";
                break;
            case 2:
                ���� = "ZEV.��ά����2";
                ����1 = "ZEV.����ħ��2";
                ����2 = "ZEV.˫��2";
                ����3 = "ZEV.˫��2";
                break;
            case 3:
                ���� = "ZEV.��ά����3";
                ����1 = "ZEV.����ħ��3";
                ����2 = "ZEV.˫��3";
                ����3 = "ZEV.˫��3";
                break;
            case 4:
                ���� = "ZEV.��ά����4";
                ����1 = "ZEV.����ħ��4";
                ����2 = "ZEV.˫��4";
                ����3 = "ZEV.˫��4";
                break;
            case 5:
                ���� = "ZEV.��ά����5";
                ����1 = "ZEV.����ħ��5";
                ����2 = "ZEV.˫��5";
                ����3 = "ZEV.˫��5";
                break;
            case 6:
                ���� = "ZEV.��ά����6";
                ����1 = "ZEV.����ħ��6";
                ����2 = "ZEV.˫��6";
                ����3 = "ZEV.˫��6";
                break;
            case 7:
                ���� = "ZEV.��ά����7";
                ����1 = "ZEV.����ħ��7";
                ����2 = "ZEV.˫��7";
                ����3 = "ZEV.˫��7";
                break;
            case 8:
                ���� = "ZEV.��ά����8";
                ����1 = "ZEV.����ħ��8";
                ����2 = "ZEV.˫��8";
                ����3 = "ZEV.˫��8";
                break;
            case 9:
                ���� = "ZEV.��ά����9";
                ����1 = "ZEV.����ħ��9";
                ����2 = "ZEV.˫��9";
                ����3 = "ZEV.˫��9";
                break;
            case 10:
                ���� = "ZEV.��ά����10";
                ����1 = "ZEV.����ħ��10";
                ����2 = "ZEV.˫��10";
                ����3 = "ZEV.˫��10";
                break;
            case 11:
                ���� = "ZEV.��ά����11";
                ����1 = "ZEV.����ħ��11";
                ����2 = "ZEV.˫��11";
                ����3 = "ZEV.˫��11";
                break;
            case 12:
                ���� = "ZEV.��ά����12";
                ����1 = "ZEV.����ħ��12";
                ����2 = "ZEV.˫��12";
                ����3 = "ZEV.˫��12";
                break;
            case 13:
                ���� = "ZEV.��ά����13";
                ����1 = "ZEV.����ħ��13";
                ����2 = "ZEV.˫��13";
                ����3 = "ZEV.˫��13";
                break;
            case 14:
                ���� = "ZEV.��ά����14";
                ����1 = "ZEV.����ħ��14";
                ����2 = "ZEV.˫��14";
                ����3 = "ZEV.˫��14";
                break;
            case 15:
                ���� = "ZEV.��ά����15";
                ����1 = "ZEV.����ħ��15";
                ����2 = "ZEV.˫��15";
                ����3 = "ZEV.˫��15";
                break;
            case 16:
                ���� = "ZEV.��ά����16";
                ����1 = "ZEV.����ħ��16";
                ����2 = "ZEV.˫��16";
                ����3 = "ZEV.˫��16";
                break;
            case 17:
                ���� = "ZEV.��ά����17";
                ����1 = "ZEV.����ħ��17";
                ����2 = "ZEV.˫��17";
                ����3 = "ZEV.˫��17";
                break;
            case 18:
                ���� = "ZEV.��ά����18";
                ����1 = "ZEV.����ħ��18";
                ����2 = "ZEV.˫��18";
                ����3 = "ZEV.˫��18";
                break;
            case 19:
                ���� = "ZEV.��ά����19";
                ����1 = "ZEV.����ħ��19";
                ����2 = "ZEV.˫��19";
                ����3 = "ZEV.˫��19";
                break;
            case 20:
                ���� = "ZEV.��ά����20";
                ����1 = "ZEV.����ħ��20";
                ����2 = "ZEV.˫��20";
                ����3 = "ZEV.˫��20";
                break;
            case 21:
                ���� = "ZEV.��ά����21";
                ����1 = "ZEV.����ħ��21";
                ����2 = "ZEV.˫��21";
                ����3 = "ZEV.˫��21";
                break;
            case 22:
                ���� = "ZEV.��ά����22";
                ����1 = "ZEV.����ħ��22";
                ����2 = "ZEV.˫��22";
                ����3 = "ZEV.˫��22";
                break;
            case 23:
                ���� = "ZEV.��ά����23";
                ����1 = "ZEV.����ħ��23";
                ����2 = "ZEV.˫��23";
                ����3 = "ZEV.˫��23";
                break;
            case 24:
                ���� = "ZEV.��ά����24";
                ����1 = "ZEV.����ħ��24";
                ����2 = "ZEV.˫��24";
                ����3 = "ZEV.˫��24";
                break;
            case 25:
                ���� = "ZEV.��ά����25";
                ����1 = "ZEV.����ħ��25";
                ����2 = "ZEV.˫��25";
                ����3 = "ZEV.˫��25";
                break;
            case 26:
                ���� = "ZEV.��ά����26";
                ����1 = "ZEV.����ħ��26";
                ����2 = "ZEV.˫��26";
                ����3 = "ZEV.˫��26";
                break;
            case 27:
                ���� = "ZEV.��ά����27";
                ����1 = "ZEV.����ħ��27";
                ����2 = "ZEV.˫��27";
                ����3 = "ZEV.˫��27";
                break;
            case 28:
                ���� = "ZEV.��ά����28";
                ����1 = "ZEV.����ħ��28";
                ����2 = "ZEV.˫��28";
                ����3 = "ZEV.˫��28";
                break;
            case 29:
                ���� = "ZEV.��ά����29";
                ����1 = "ZEV.����ħ��29";
                ����2 = "ZEV.˫��29";
                ����3 = "ZEV.˫��29";
                break;
            case 30:
                ���� = "ZEV.��ά����30";
                ����1 = "ZEV.����ħ��30";
                ����2 = "ZEV.˫��30";
                ����3 = "ZEV.˫��30";
                break;
            default:
                ���� = "ZEV.��ά����30";
                ����1 = "ZEV.����ħ��30";
                ����2 = "ZEV.˫��30";
                ����3 = "ZEV.˫��30";
                break;
        }
        int ��ά���� = Integer.parseInt(ServerProperties.getProperty(����));
        int ����ħ�� = Integer.parseInt(ServerProperties.getProperty(����1));
        int ˫�� = Integer.parseInt(ServerProperties.getProperty(����2));
        int ˫�� = Integer.parseInt(ServerProperties.getProperty(����3));
        if (����װ��(getItemId()) || ����װ��(getItemId())) {
            watk += ˫��;
            matk += ˫��;
            mdef += ˫��;
            wdef += ˫��;
            hp += ����ħ��;
            mp += ����ħ��;
            dex += ��ά����;
            str += ��ά����;
            _int += ��ά����;
            luk += ��ά����;
        } else {
            mdef += ˫�� / 2;
            wdef += ˫�� / 2;
            hp += ����ħ�� / 2;
            mp += ����ħ�� / 2;
            dex += ��ά���� / 2;
            str += ��ά���� / 2;
            _int += ��ά���� / 2;
            luk += ��ά���� / 2;
        }

        //��ʾ��
        c.getPlayer().dropMessage(5, "[װ������]: " + MapleItemInformationProvider.getInstance().getName(getItemId()) + " �ﵽ " + (getEquipLevel() + 1) + " �����ﵽ��һ�ȼ���Ҫ " + ExpTable.getTimelessItemExpNeededForLevel((getEquipLevel() + 1)) + "");
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[װ������]: " + c.getPlayer().getName() + " �ɹ��� " + MapleItemInformationProvider.getInstance().getName(getItemId()) + " ������ " + (getEquipLevel() + 1) + " ��"));
        //����һ��
        this.itemLevel++;
        //ˢ��
        c.getPlayer().getClient().sendPacket(MaplePacketCreator.showEquipmentLevelUp());
        c.getPlayer().getClient().sendPacket(MaplePacketCreator.updateSpecialItemUse(this, getType()));

    }

    @Override
    public void setEquipLevel(byte gf) {
        this.itemLevel = gf;
    }

    @Override
    public byte getEquipLevel() {
        return itemLevel;
    }
}
/*for (Pair<String, Integer> stat : stats) {
             matk += 1;
            /*if (stat.getLeft().equals("incDEX")) {
                dex += stat.getRight();
            } else if (stat.getLeft().equals("incSTR")) {
                str += stat.getRight();
            } else if (stat.getLeft().equals("incINT")) {
                _int += stat.getRight();
            } else if (stat.getLeft().equals("incLUK")) {
                luk += stat.getRight();
            } else if (stat.getLeft().equals("incMHP")) {
                hp += stat.getRight();
            } else if (stat.getLeft().equals("incMMP")) {
                mp += stat.getRight();
            } else if (stat.getLeft().equals("incPAD")) {
                watk += stat.getRight();
            } else if (stat.getLeft().equals("incMAD")) {
                matk += stat.getRight();
            } else if (stat.getLeft().equals("incPDD")) {
                wdef += stat.getRight();
            } else if (stat.getLeft().equals("incMDD")) {
                mdef += stat.getRight();
            } else if (stat.getLeft().equals("incEVA")) {
                avoid += stat.getRight();
            } else if (stat.getLeft().equals("incACC")) {
                acc += stat.getRight();
            } else if (stat.getLeft().equals("incSpeed")) {
                speed += stat.getRight();
            } else if (stat.getLeft().equals("incJump")) {
                jump += stat.getRight();
            }
        }*/
//c.getPlayer().getClient().sendPacket(MaplePacketCreator.getCharInfo(c.getPlayer()));
/*  c.getPlayer().getMap().removePlayer(c.getPlayer());
            c.getPlayer().getMap().addPlayer(c.getPlayer());*/
//c.getPlayer().getMap().broadcastMessage(c.getPlayer(), MaplePacketCreator.showForeignEffect(c.getPlayer().getId(), 17));
// c.getPlayer().forceUpdateItem(MapleInventoryType.EQUIPPED, this);
/*if (getEquipLevel() <= 10) {
            ��ֵ = (int) Math.ceil(Math.random() * 10);
            ��ֵ1 = (int) Math.ceil(Math.random() * 5);
        } else if (getEquipLevel() > 10 && getEquipLevel() <= 20) {
            ��ֵ = (int) Math.ceil(Math.random() * 15);
            ��ֵ1 = (int) Math.ceil(Math.random() * 10);
        } else if (getEquipLevel() > 20 && getEquipLevel() <= 30) {
            ��ֵ = (int) Math.ceil(Math.random() * 15);
            ��ֵ1 = (int) Math.ceil(Math.random() * 15);
        } else if (getEquipLevel() > 30 && getEquipLevel() <= 40) {
            ��ֵ = (int) Math.ceil(Math.random() * 20);
            ��ֵ1 = (int) Math.ceil(Math.random() * 20);
        } else if (getEquipLevel() > 40 && getEquipLevel() <= 50) {
            ��ֵ = (int) Math.ceil(Math.random() * 25);
            ��ֵ1 = (int) Math.ceil(Math.random() * 25);
        } else if (getEquipLevel() > 50 && getEquipLevel() <= 60) {
            ��ֵ = (int) Math.ceil(Math.random() * 30);
            ��ֵ1 = (int) Math.ceil(Math.random() * 30);
        } else if (getEquipLevel() > 60) {
            ��ֵ = (int) Math.ceil(Math.random() * 40);
            ��ֵ1 = (int) Math.ceil(Math.random() * 40);
        }*/
        //"��ά����" + getEquipLevel() + ""
