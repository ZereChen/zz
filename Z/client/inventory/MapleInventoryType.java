package client.inventory;

/**
 *
 * @author Matze
 */
public enum MapleInventoryType {
    //背包类型
    UNDEFINED(0),
    EQUIP(1),
    装备栏(1),
    USE(2),
    消耗栏(2),
    SETUP(3),
    设置栏(3),
    ETC(4),
    其他栏(4),
    CASH(5),
    特殊栏(5),
    EQUIPPED(-1);
    final byte type;

    private MapleInventoryType(int type) {
        this.type = (byte) type;
    }

    public byte getType() {
        return type;
    }

    public short getBitfieldEncoding() {
        return (short) (2 << type);
    }

    public static MapleInventoryType getByType(byte type) {
        for (MapleInventoryType l : MapleInventoryType.values()) {
            if (l.getType() == type) {
                return l;
            }
        }
        return null;
    }

    public static MapleInventoryType getByWZName(String name) {
        if (name.equals("Install")) {
            return SETUP;
        } else if (name.equals("Consume")) {
            return USE;
        } else if (name.equals("Etc")) {
            return ETC;
        } else if (name.equals("Cash")) {
            return CASH;
        } else if (name.equals("Pet")) {
            return CASH;
        }
        return UNDEFINED;
    }
}
