/*
����ҽ�ɫ�й�



 */
package handling.channel.handler;

import static abc.Game.����ҿ���;
import static abc.Game.����;
import java.awt.Point;
import java.util.List;
import client.inventory.IItem;
import client.ISkill;
import client.SkillFactory;
import client.SkillMacro;
import constants.GameConstants;
import client.inventory.MapleInventoryType;
import client.MapleBuffStat;
import client.MapleClient;
import client.MapleCharacter;
import client.MapleCoolDownValueHolder;
import client.MapleStat;
import client.PlayerStats;
import client.anticheat.CheatingOffense;
import client.inventory.ItemFlag;
import constants.MapConstants;
import static fumo.FumoSkill.FM;
import static gui.QQMsgServer.sendMsgToQQGroup;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import handling.world.World;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Calendar;
import scripting.NPCConversationManager;
import scripting.NPCScriptManager;
import server.AutobanManager;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.MapleStatEffect;
import server.MaplePortal;
import java.util.concurrent.ScheduledFuture;
import server.Randomizer;
import server.Timer;
import server.Timer.EtcTimer;
import server.custom.bossrank.BossRankManager;
import server.events.MapleSnowball.MapleSnowballs;
import server.life.MapleMonster;
import server.life.MobAttackInfo;
import server.life.MobAttackInfoFactory;
import server.life.MobSkill;
import server.life.MobSkillFactory;
import server.maps.MapleMap;
import server.maps.FieldLimitType;
import server.movement.LifeMovementFragment;
import server.quest.MapleQuest;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;
import tools.packet.MobPacket;
import tools.packet.MTSCSPacket;
import tools.data.LittleEndianAccessor;

public class PlayerHandler {

    private static int channel;

    private static boolean isFinisher(final int skillid) {
        switch (skillid) {
            case 1111003:
            case 1111004:
            case 1111005:
            case 1111006:
            case 11111002:
            case 11111003:
                return true;
        }
        return false;
    }

    public static void ChangeMonsterBookCover(final int bookid, final MapleClient c, final MapleCharacter chr) {
        if (bookid == 0 || GameConstants.isMonsterCard(bookid)) {
            chr.setMonsterBookCover(bookid);
            chr.getMonsterBook().updateCard(c, bookid);
        }
    }

    public static void ChangeSkillMacro(final LittleEndianAccessor slea, final MapleCharacter chr) {
        final int num = slea.readByte();
        String name;
        int shout, skill1, skill2, skill3;
        SkillMacro macro;

        for (int i = 0; i < num; i++) {
            name = slea.readMapleAsciiString();
            shout = slea.readByte();
            skill1 = slea.readInt();
            skill2 = slea.readInt();
            skill3 = slea.readInt();

            macro = new SkillMacro(skill1, skill2, skill3, name, shout, i);
            chr.updateMacros(i, macro);
        }
    }

    public static final void ChangeKeymap(final LittleEndianAccessor slea, final MapleCharacter chr) {

        if (slea.available() > 8 && chr != null) { // else = pet auto pot
            chr.updateTick(slea.readInt());
            final int numChanges = slea.readInt();

            for (int i = 0; i < numChanges; i++) {
                chr.changeKeybinding(slea.readInt(), slea.readByte(), slea.readInt());
            }
        } else if (chr != null) {
            int type = slea.readInt();
            int data = slea.readInt();
            switch (type) {
                case 1:
                    if (data <= 0) {
                        chr.getQuestRemove(MapleQuest.getInstance(122221));
                    } else {
                        chr.getQuestNAdd(MapleQuest.getInstance(122221)).setCustomData(String.valueOf(data));
                    }
                    break;
                case 2:
                    if (data <= 0) {
                        chr.getQuestRemove(MapleQuest.getInstance(122222));
                    } else {
                        chr.getQuestNAdd(MapleQuest.getInstance(122222)).setCustomData(String.valueOf(data));
                    }
                    break;
                case 3:
                    if (data <= 0) {
                        chr.getQuestRemove(MapleQuest.getInstance(122224));
                    } else {
                        chr.getQuestNAdd(MapleQuest.getInstance(122224)).setCustomData(String.valueOf(data));
                    }
            }
        }
    }

    //ʹ������
    public static final void UseChair(final int itemId, final MapleClient c, final MapleCharacter chr) {
        if (chr == null) {
            return;
        }
        final IItem toUse = chr.getInventory(MapleInventoryType.SETUP).findById(itemId);

        if (toUse == null) {
            chr.getCheatTracker().registerOffense(CheatingOffense.ʹ�ò����ڵ���, Integer.toString(itemId));
            return;
        }
        //��������
        if (itemId == 3010025) {
            chr.��Ҷ����();
            c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.startMapEffect("", 5120008, false));
            chr.dropMessage(5, "----------------------------------------------------------------------------------------");
            chr.dropMessage(5, "����: " + MapleItemInformationProvider.getInstance().getName(3010025));
            chr.dropMessage(5, "Ʒ��: ������");
            chr.dropMessage(5, "Ч��: [���] ȫ����Ҷ");
            //chr.dropMessage(5, "Ч��: [����] �ظ���ɫ���һ������");
            chr.dropMessage(5, "Ч��: [����] ��ȡ���飬��ң������ڳ�����������");
            if (!"".equals(MapleParty.����3010025)) {
                chr.dropMessage(5, "��ע: " + MapleParty.����3010025);
            }
            chr.dropMessage(5, "----------------------------------------------------------------------------------------");
            chr.�رձ����߳�();
        }
        //��������
        if (itemId == 3010100) {
            chr.����();
            chr.dropMessage(5, "----------------------------------------------------------------------------------------");
            chr.dropMessage(5, "����: " + MapleItemInformationProvider.getInstance().getName(3010100));
            chr.dropMessage(5, "Ʒ��: ������");
            chr.dropMessage(5, "Ч��: [����] ���������ϣ����Ի�ȡ��ҡ���ȡ����ʱ�����ӣ������ޡ�");
            chr.dropMessage(5, "Ч��: [����] ���������ϣ����Ի�ȡ��ҡ���ȡ����ʱ�����ӣ������ޡ�");
            if (!"".equals(MapleParty.����3010100)) {
                chr.dropMessage(5, "��ע: " + MapleParty.����3010100);
            }
            chr.dropMessage(5, "----------------------------------------------------------------------------------------");
            chr.�رձ����߳�();
        }
        //ԡͰ
        if (itemId == 3012002) {
            if (chr.getMap().getCharactersSize() > 1) {
                chr.dropMessage(1, "��Ҫ�ͱ���һ�����裬��ᱻ�����ġ�");
            }
            chr.dropMessage(5, "----------------------------------------------------------------------------------------");
            chr.dropMessage(5, "����: " + MapleItemInformationProvider.getInstance().getName(3012002));
            chr.dropMessage(5, "Ʒ��: ������");
            chr.dropMessage(5, "Ч��: [����] ����ԡͰ����Ի�ȡ���顣��ȡ���͵ȼ���ء�");
            if (!"".equals(MapleParty.����3012002)) {
                chr.dropMessage(5, "��ע: " + MapleParty.����3012002);
            }
            chr.dropMessage(5, "----------------------------------------------------------------------------------------");
            chr.ԡͰ();
            chr.dropMessage(5, "");
            chr.�رձ����߳�();
        }

        //����������
        if (itemId == 3011000) {
            if (!chr.haveItem(5340000) && !chr.haveItem(5340001)) {
                chr.dropMessage(1, "�㱳����û����ͨ��ͻ��߸߼���͡�");
            }
            if (!chr.haveItem(2300000) && !chr.haveItem(2300001)) {
                chr.dropMessage(1, "�㱳����û�������");
            }
            boolean haz = false;
            for (IItem item : c.getPlayer().getInventory(MapleInventoryType.CASH).list()) {
                if (item.getItemId() == 5340000) {
                    haz = true;
                } else if (item.getItemId() == 5340001) {
                    haz = false;//false
                    if (chr.Getcharactera("" + chr.getId() + "", 1) <= 1) {
                        chr.startFishingTask1(true);
                    } else if (chr.Getcharactera("" + chr.getId() + "", 1) == 2) {
                        chr.startFishingTask2(true);
                    } else if (chr.Getcharactera("" + chr.getId() + "", 1) == 3) {
                        chr.startFishingTask3(true);
                    } else if (chr.Getcharactera("" + chr.getId() + "", 1) == 4) {
                        chr.startFishingTask4(true);
                    } else if (chr.Getcharactera("" + chr.getId() + "", 1) == 5) {
                        chr.startFishingTask5(true);
                    } else if (chr.Getcharactera("" + chr.getId() + "", 1) >= 6) {
                        chr.startFishingTask6(true);
                    }
                    break;
                }
            }
            if (haz) {
                if (chr.Getcharactera("" + chr.getId() + "", 1) <= 1) {
                    chr.startFishingTask1(false);
                } else if (chr.Getcharactera("" + chr.getId() + "", 1) == 2) {
                    chr.startFishingTask2(false);
                } else if (chr.Getcharactera("" + chr.getId() + "", 1) == 3) {
                    chr.startFishingTask3(false);
                } else if (chr.Getcharactera("" + chr.getId() + "", 1) == 4) {
                    chr.startFishingTask4(false);
                } else if (chr.Getcharactera("" + chr.getId() + "", 1) == 5) {
                    chr.startFishingTask5(false);
                } else if (chr.Getcharactera("" + chr.getId() + "", 1) >= 6) {
                    chr.startFishingTask6(false);
                }
            }
        }
        chr.setChair(itemId);
        chr.getMap().broadcastMessage(chr, MaplePacketCreator.showChair(chr.getId(), itemId), false);
        c.sendPacket(MaplePacketCreator.enableActions());
    }

    public static final void CancelChair(final short id, final MapleClient c, final MapleCharacter chr) {
        // ȡ������
        if (id == -1) {
            //�򿪱����߳�
            chr.�����߳�();
            //����������
            if (chr.getChair() == 3011000) {
                chr.cancelFishingTask();
            }
            if (chr.getChair() == 3010025) {
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.removeMapEffect());
                chr.�رշ�Ҷ�߳�();
            }
            if (chr.getChair() == 3010100) {
                chr.�رղ����߳�();
            }
            if (chr.getChair() == 3012002) {
                chr.�ر�ԡͰ�߳�();
            }
            chr.setChair(0);
            c.sendPacket(MaplePacketCreator.cancelChair(-1));
            chr.getMap().broadcastMessage(chr, MaplePacketCreator.showChair(chr.getId(), 0), false);
        } else {
            //�ڵ�ͼ���е�Ӧ��
            chr.setChair(id);
            c.sendPacket(MaplePacketCreator.cancelChair(id));
        }
    }

    public static final void TrockAddMap(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        final byte addrem = slea.readByte();
        final byte vip = slea.readByte();

        if (vip == 1) {
            if (addrem == 0) {
                chr.deleteFromRocks(slea.readInt());
            } else if (addrem == 1) {
                if (!FieldLimitType.VipRock.check(chr.getMap().getFieldLimit())) {
                    chr.addRockMap();
                } else {
                    chr.dropMessage(1, "����ܲ�����Ӵ˵�ͼ.");
                }
            }
        } else if (addrem == 0) {
            chr.deleteFromRegRocks(slea.readInt());
        } else if (addrem == 1) {
            if (!FieldLimitType.VipRock.check(chr.getMap().getFieldLimit())) {
                chr.addRegRockMap();
            } else {
                chr.dropMessage(1, "����ܲ�����Ӵ˵�ͼ.");
            }
        }
        c.sendPacket(MTSCSPacket.getTrockRefresh(chr, vip == 1, addrem == 3));
    }

    public static final void CharInfoRequest(final int objectid, final MapleClient c, final MapleCharacter chr) {
        if (c.getPlayer() == null || c.getPlayer().getMap() == null) {
            return;
        }

        //�ʵ�4
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.HOUR_OF_DAY) == 0) {
            if (chr.getBossLog("�ʵ�4") <= 6) {
                if (chr.getMapId() == 1000000000) {
                    if (chr.getBossLog("�ʵ�4") <= 5) {
                        chr.setBossLog("�ʵ�4");
                        if (chr.getBossLog("�ʵ�4") == 4) {
                            chr.dropMessage(1, "��������");
                        }
                    } else {
                        chr.setBossLog("�ʵ�4");
                        c.getPlayer().addFame(1);
                        chr.dropMessage(1, "[С�ʵ�] : \r\n��ϲ�㴥���ʵ������ 1 ������");
                    }
                    return;
                }
            }
        }
        if (c.getPlayer().isGM()) {
            //ά��ñ�ӵ�����
            if (c.getPlayer().hasEquipped(1002140)) {
                String guildName = NPCConversationManager.��ȡ��������(c.getPlayer().getMap().getCharacterById(objectid).guildid);
                c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
                c.getPlayer().dropMessage(5, "   [������Աά��֮�ӡ�<ά����ñ> ]");
                c.getPlayer().dropMessage(5, "   [ ��ɫID ] : " + c.getPlayer().getMap().getCharacterById(objectid).getId());
                c.getPlayer().dropMessage(5, "   [ IP��ַ ] : " + c.getPlayer().getMap().getCharacterById(objectid).getClient().getSessionIPAddress());
                c.getPlayer().dropMessage(5, "   [ ��  �� ] : " + c.getPlayer().getMap().getCharacterById(objectid).getClient().getAccountName());
                c.getPlayer().dropMessage(5, "   [ ��  �� ] : " + c.getPlayer().getMap().getCharacterById(objectid).getName());
                c.getPlayer().dropMessage(5, "   [ ��  �� ] : " + c.getPlayer().getMap().getCharacterById(objectid).getLevel());
                c.getPlayer().dropMessage(5, "   [ ��  �� ] : " + c.getPlayer().getMap().getCharacterById(objectid).getFame());
                if (c.getPlayer().getMap().getCharacterById(objectid).guildid > 0) {
                    c.getPlayer().dropMessage(5, "   [ ��  �� ] : " + guildName);
                }
                c.getPlayer().dropMessage(5, "   [ ��  ȯ ] : " + c.getPlayer().getMap().getCharacterById(objectid).getCSPoints(1));
                c.getPlayer().dropMessage(5, "   [ ��  �� ] : " + c.getPlayer().getMap().getCharacterById(objectid).getCSPoints(2));
                c.getPlayer().dropMessage(5, "   [ ��  �� ] : " + c.getPlayer().getMap().getCharacterById(objectid).getMeso());
                c.getPlayer().dropMessage(5, "   [ ����ֵ ] : �� " + c.getPlayer().getMap().getCharacterById(objectid).getStat().getHp() + " / " + c.getPlayer().getMap().getCharacterById(objectid).getHp() + " ��");
                c.getPlayer().dropMessage(5, "   [ ����ֵ ] : �� " + c.getPlayer().getMap().getCharacterById(objectid).getStat().getMp() + " / " + c.getPlayer().getMap().getCharacterById(objectid).getMp() + " ��");
                c.getPlayer().dropMessage(5, "   [ ��  �� ] : " + c.getPlayer().getMap().getCharacterById(objectid).getStat().getStr());
                c.getPlayer().dropMessage(5, "   [ ��  �� ] : " + c.getPlayer().getMap().getCharacterById(objectid).getStat().getDex());
                c.getPlayer().dropMessage(5, "   [ ��  �� ] : " + c.getPlayer().getMap().getCharacterById(objectid).getStat().getInt());
                c.getPlayer().dropMessage(5, "   [ ��  �� ] : " + c.getPlayer().getMap().getCharacterById(objectid).getStat().getLuk());
                c.getPlayer().dropMessage(5, " * ���� : ά����ñ��ά������������������ɫ���ɹ���Ŀ���ɫ��");
                c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            }
            //ά�����������������
            if (c.getPlayer().hasEquipped(1002140) && c.getPlayer().hasEquipped(1322013)) {
                c.getPlayer().getMap().getCharacterById(objectid).addHP(-c.getPlayer().getMap().getCharacterById(objectid).getMaxHp() * 99 / 100);
            }
        }
        final MapleCharacter player = c.getPlayer().getMap().getCharacterById(objectid);
        c.sendPacket(MaplePacketCreator.enableActions());
        //����ɱ�
        if (MapleParty.����ɱ� > 0) {
            //����ɱ��ͼ
            if (player.getMapId() == 100000203) {
                //2Ƶ������
                if (c.getPlayer().getClient().getChannel() != 2) {
                    c.getPlayer().dropMessage(5, "��� 2 Ƶ�����С�");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                //�Լ�����Լ�
                if (c.getPlayer().getName() == null ? c.getPlayer().getMap().getCharacterById(objectid).getName() == null : c.getPlayer().getName().equals(c.getPlayer().getMap().getCharacterById(objectid).getName())) {
                    //���ֻʣ���Լ����ж�Ϊʤ��
                    if (c.getPlayer().getMap().getCharactersSize() == 1) {
                        //�����ű�
                        NPCScriptManager.getInstance().start(c, 9900007, 88888888);
                        //��������ɱ
                        MapleParty.����ɱ� = 0;
                        c.getPlayer().dropMessage(1, "��ϲ����ʤ����");
                        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[����ɱ]:  " + c.getPlayer().getName() + " ����˱��λ��ʤ������ϲ��ϲ��"));
                        sendMsgToQQGroup("[����ɱ]:  " + c.getPlayer().getName() + " ����˱��λ��ʤ������ϲ��ϲ��");
                        BossRankManager.getInstance().setLog(player.getId(), player.getName(), "����ɱ��ھ�", (byte) 2, (byte) 1);
                        c.getPlayer().getMap().getCharacterById(objectid).changeMap(100000000);
                        c.sendPacket(MaplePacketCreator.enableActions());
                        return;
                    }
                }
                //�ж�Ŀ���Ƿ�����
                if (c.getPlayer().getMap().getCharacterById(objectid).isAlive()) {
                    int �ӵ� = 4000140;
                    if (c.getPlayer().haveItem(�ӵ�, 0, false, false)) {
                        c.getPlayer().dropMessage(5, "��û���ӵ�");
                        return;
                    }
                    double r = Math.ceil(Math.random() * 100);
                    if (r > 50) {
                        //������Ч
                        c.sendPacket(MaplePacketCreator.playSound("Party1/jj"));
                        //����Ѫ��
                        c.getPlayer().getMap().getCharacterById(objectid).addHP(-c.getPlayer().getMap().getCharacterById(objectid).getMaxHp() * 30 / 100);
                        if (!c.getPlayer().getMap().getCharacterById(objectid).isAlive()) {
                            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[����ɱ]:  " + c.getPlayer().getName() + "  �{��ߩרT ��ɱ��  " + c.getPlayer().getMap().getCharacterById(objectid).getName() + ""));
                            sendMsgToQQGroup("[����ɱ]:  " + c.getPlayer().getName() + "  �{��ߩרT ��ɱ��  " + c.getPlayer().getMap().getCharacterById(objectid).getName() + "");
                            BossRankManager.getInstance().setLog(player.getId(), player.getName(), "����ɱ���ɱ", (byte) 2, (byte) 1);
                            BossRankManager.getInstance().setLog(c.getPlayer().getMap().getCharacterById(objectid).getId(), c.getPlayer().getMap().getCharacterById(objectid).getName(), "����ɱ���ɱ", (byte) 2, (byte) 1);
                        }
                    } else {
                        //������Ч
                        c.sendPacket(MaplePacketCreator.playSound("Party1/jj"));
                        c.getPlayer().dropMessage(5, "��ƫ��");
                    }
                    //�����ӵ�
                    MapleInventoryManipulator.removeById(chr.getClient(), GameConstants.getInventoryType(�ӵ�), �ӵ�, 1, true, false);
                } else {
                    c.getPlayer().dropMessage(5, "Ŀ���Ѿ�����");
                    c.getPlayer().getMap().getCharacterById(objectid).changeMap(100000000);
                }
            }
        }

        //����ԱȨ��
        if (!c.getPlayer().isGM()) {
            if (c.getPlayer().getMap().getCharacterById(objectid).Getcharacterz("" + objectid + "", 1) > 0) {
                c.getPlayer().dropMessage(1, "����Ҳ�������˲鿴��Ϣ��");
                return;
            }
        }
        if (player != null && !player.isClone() && !c.getPlayer().hasEquipped(1002140)) {
            if (!player.isGM() || c.getPlayer().isGM()) {
                c.sendPacket(MaplePacketCreator.charInfo(player, c.getPlayer().getId() == objectid));
            }
        }
        //����Ϣ2

    }

    // public long Ӣ�۵Ľ�ָ��ȴ = 0;
    public static final void TakeDamage(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {//�ܵ��˺�
        //System.out.println(slea.toString());
        chr.updateTick(slea.readInt());
        final byte type = slea.readByte(); //-4 is mist, -3 and -2 are map damage.
        slea.skip(1); // Element - 0x00 = elementless, 0x01 = ice, 0x02 = fire, 0x03 = lightning
        int damage = slea.readInt();

        int oid = 0;
        int monsteridfrom = 0;
        int reflect = 0;
        byte direction = 0;
        int pos_x = 0;
        int pos_y = 0;
        int fake = 0;
        int mpattack = 0;
        boolean is_pg = false;
        boolean isDeadlyAttack = false;
        MapleMonster attacker = null;
        if (chr == null || chr.isHidden() || chr.getMap() == null) {
            return;
        }
        chr.����������++;
        if (chr.getMapId() == 230000000) {
            if (chr.�������� > 0) {
                if (chr.�������� > 10) {
                    chr.�������� -= chr.�������� * 0.1;
                } else {
                    chr.�������� -= 1;
                }
                chr.getClient().sendPacket(MaplePacketCreator.sendHint("#b<���ֵ>\r\n\r\n#e#r " + chr.�������� + "\r\n", 200, 3));
            }
        }
        if (chr.isGM() && chr.isInvincible()) {
            return;
        }
        if (chr.F().get(FM("ʥ��")) != null) {
            if (chr.ʥ���޵� > 0) {
                chr.ʥ���޵� -= 1;
                double r = Math.ceil(Math.random() * 100);
                if (chr.F().get(FM("ʥ��")) >= r) {
                    return;
                }
            }
        }
        if (gui.Start.������Ϣ����.get("����ģʽ" + chr.getId() + "") != null) {
            if (gui.Start.������Ϣ����.get("����ģʽ" + chr.getId() + "") == 0) {
                damage += damage * 0.3;
                damage -= gui.Start.������Ϣ����.get("Ӳ��Ƥ��" + chr.getId() + "") * gui.Start.������Ϣ����.get("BUFF����" + chr.getId() + "") * 0.01;
            }
        }
        //����̩ɽ
        if (chr.F().get(FM("����̩ɽ")) != null) {
            if (!chr.isActiveBuffedValue(1221002)) {
                double a = Math.ceil(Math.random() * 1000);
                if (a <= chr.F().get(FM("����̩ɽ"))) {
                    SkillFactory.getSkill(1221002).getEffect(10).applyTo(chr);
                }
            }
        }
        //��ŭ֮��
        if (chr.F().get(FM("��ŭ֮��")) != null) {
            if (!chr.isActiveBuffedValue(1101006)) {
                double a = Math.ceil(Math.random() * 1000);
                if (a <= chr.F().get(FM("��ŭ֮��"))) {
                    SkillFactory.getSkill(1101006).getEffect(10).applyTo(chr);
                }
            }
        }

        /*if (chr.isActiveBuffedValue(1201007)) {
            if (chr.getJob() == 120 || chr.getJob() == 121 || chr.getJob() == 122) {
                if (chr.getEquippedFuMoMap().get(41) != null) {
                    double r = Math.ceil(Math.random() * 100);
                    long ��ħ���� = (long) (damage / 100 * chr.getEquippedFuMoMap().get(41));
                    if (r >= ��ħ����) {
                        return;
                    } else {
                        damage -= ��ħ����;
                    }
                }
            }
        }*/
        if (chr.F().get(FM("δ����֪")) != null) {
            if (chr.haveItem(2000005, 1, false, false)) {
                MapleInventoryManipulator.addById(chr.getClient(), 2000005, (short) 1, (byte) 0);
                return;
            }
        }
        if (chr.F().get(FM("���")) != null) {
            if (damage > chr.getMaxHp() * 0.6) {
                damage -= (damage / 100 * chr.F().get(FM("���")));
            }
        }
        if (chr.F().get(FM("�᲻�ɴ�")) != null) {
            damage -= chr.F().get(FM("�᲻�ɴ�"));
        }
        if (chr.getEquippedFuMoMap().get(21) != null) {
            long ��ħ���� = (long) (damage / 100 * chr.getEquippedFuMoMap().get(21));
            damage -= ��ħ����;
        }
        final PlayerStats stats = chr.getStat();
        if (type != -2 && type != -3 && type != -4) {
            monsteridfrom = slea.readInt();
            oid = slea.readInt();
            attacker = chr.getMap().getMonsterByOid(oid);
            direction = slea.readByte();
            if (attacker == null) {
                return;
            }
            if (type != -1) { // Bump damage
                final MobAttackInfo attackInfo = MobAttackInfoFactory.getInstance().getMobAttackInfo(attacker, type);
                if (attackInfo != null) {
                    if (attackInfo.isDeadlyAttack()) {
                        isDeadlyAttack = true;
                        mpattack = stats.getMp() - 1;
                        if ("��".equals(����)) {
                            chr.dropMessage(5, "mpattack: " + mpattack);
                        }
                    } else {
                        mpattack += attackInfo.getMpBurn();
                        if ("��".equals(����)) {
                            chr.dropMessage(5, "mpattack: " + mpattack);
                        }
                    }
                    final MobSkill skill = MobSkillFactory.getMobSkill(attackInfo.getDiseaseSkill(), attackInfo.getDiseaseLevel());
                    if (skill != null && (damage == -1 || damage > 0)) {
                        skill.applyEffect(chr, attacker, false);
                        if ("��".equals(����)) {
                            chr.dropMessage(5, "attacker: " + attacker);
                        }
                    }
                    attacker.setMp(attacker.getMp() - attackInfo.getMpCon());
                }
            }
        }

        if (damage == -1) {
            fake = 4020002 + ((chr.getJob() / 10 - 40) * 100000);
            if ("��".equals(����)) {
                chr.dropMessage(5, "damage: " + damage);
                chr.dropMessage(5, "fake: " + fake);
            }
        }
        /*} else if (damage < -1 || damage > 60000) {
            AutobanManager.getInstance().addPoints(c, 1000, 60000, "�����쳣��" + monsteridfrom + ": " + damage);
            return;
        }*/
        //chr.getCheatTracker().checkTakeDamage(damage);//��ײ
        // chr.dropMessage(5, "1");
        if (damage > 0) {
            chr.getCheatTracker().setAttacksWithoutHit(false);

            if (chr.getBuffedValue(MapleBuffStat.MORPH) != null) {
                chr.cancelMorphs();
                //chr.dropMessage(5, "2");
            }
            if (slea.available() == 3) {
                byte level = slea.readByte();
                if (level > 0) {
                    final MobSkill skill = MobSkillFactory.getMobSkill(slea.readShort(), level);
                    if (skill != null) {
                        skill.applyEffect(chr, attacker, false);
                    }
                }
            }
            if (type != -2 && type != -3 && type != -4) {
                final int bouncedam_ = (Randomizer.nextInt(100) < chr.getStat().DAMreflect_rate ? chr.getStat().DAMreflect : 0) + (type == -1 && chr.getBuffedValue(MapleBuffStat.POWERGUARD) != null ? chr.getBuffedValue(MapleBuffStat.POWERGUARD) : 0) + (type == -1 && chr.getBuffedValue(MapleBuffStat.PERFECT_ARMOR) != null ? chr.getBuffedValue(MapleBuffStat.PERFECT_ARMOR) : 0);
                // final boolean bouncedam_A = chr.getBuffedValue(MapleBuffStat.BODY_PRESSURE) != null;
                if (bouncedam_ > 0 && attacker != null) {
                    long bouncedamage = (long) (damage * bouncedam_ / 100);
                    bouncedamage = Math.min(bouncedamage, attacker.getMobMaxHp() / 10);
                    attacker.damage(chr, bouncedamage, true);
                    damage -= bouncedamage;
                    chr.getMap().broadcastMessage(chr, MobPacket.damageMonster(oid, bouncedamage), chr.getPosition());
                    //chr.dropMessage(5, "3");
                    is_pg = true;
                }
            }
            if (type != -1 && type != -2 && type != -3 && type != -4) {
                switch (chr.getJob()) {
                    case 112: {
                        final ISkill skill = SkillFactory.getSkill(1120004);
                        if (chr.getSkillLevel(skill) > 0) {
                            damage = (int) ((skill.getEffect(chr.getSkillLevel(skill)).getX() / 1000.0) * damage);
                        }
                        break;
                    }
                    case 122: {
                        final ISkill skill = SkillFactory.getSkill(1220005);
                        if (chr.getSkillLevel(skill) > 0) {
                            damage = (int) ((skill.getEffect(chr.getSkillLevel(skill)).getX() / 1000.0) * damage);
                        }
                        break;
                    }
                    case 132: {
                        final ISkill skill = SkillFactory.getSkill(1320005);
                        if (chr.getSkillLevel(skill) > 0) {
                            damage = (int) ((skill.getEffect(chr.getSkillLevel(skill)).getX() / 1000.0) * damage);
                        }
                        break;
                    }
                }
            }
            /*if (c.getPlayer().hasEquipped(1112793) && c.getPlayer().getParty() == null) {//����ָ��
                chr.gainExp(damage * 3, true, false, false);
            }*/

            final MapleStatEffect bouncedam_A = chr.getStatForBuff(MapleBuffStat.BODY_PRESSURE);
            if (attacker != null && bouncedam_A != null && damage > 0) {
                ISkill ��ѹ = SkillFactory.getSkill(21101003); //��ѹ
                int ��ѹ�˺� = (int) ((��ѹ.getEffect(chr.getSkillLevel(21101003)).getDamage() / 100.0) * damage);
                //long bouncedamage = (long) (damage * bouncedam_ / 100);
                //bouncedamage = Math.min(bouncedamage, attacker.getMobMaxHp() / 10);c.getPlayer().getParty() != null
                attacker.damage(chr, ��ѹ�˺�, true);
                damage -= ��ѹ�˺�;
                chr.getMap().broadcastMessage(chr, MobPacket.damageMonster(oid, ��ѹ�˺�), chr.getPosition());
                chr.checkMonsterAggro(attacker);
                chr.setHp(chr.getHp() - damage);
            }
            final MapleStatEffect magicShield = chr.getStatForBuff(MapleBuffStat.MAGIC_SHIELD);
            if (magicShield != null) {
                damage -= (int) ((magicShield.getX() / 100.0) * damage);
            }
            final MapleStatEffect blueAura = chr.getStatForBuff(MapleBuffStat.BLUE_AURA);
            if (blueAura != null) {
                damage -= (int) ((blueAura.getY() / 100.0) * damage);
            }
            if (chr.getBuffedValue(MapleBuffStat.SATELLITESAFE_PROC) != null && chr.getBuffedValue(MapleBuffStat.SATELLITESAFE_ABSORB) != null) {
                double buff = chr.getBuffedValue(MapleBuffStat.SATELLITESAFE_PROC).doubleValue();
                double buffz = chr.getBuffedValue(MapleBuffStat.SATELLITESAFE_ABSORB).doubleValue();
                if ((int) ((buff / 100.0) * chr.getStat().getMaxHp()) <= damage) {
                    damage -= (int) ((buffz / 100.0) * damage);
                    chr.cancelEffectFromBuffStat(MapleBuffStat.SUMMON);
                    chr.cancelEffectFromBuffStat(MapleBuffStat.REAPER);
                }
            }
            if (chr.getBuffedValue(MapleBuffStat.MAGIC_GUARD) != null) {
                int hploss = 0, mploss = 0;
                if (isDeadlyAttack) {
                    if (stats.getHp() > 1) {
                        hploss = stats.getHp() - 1;
                    }
                    if (stats.getMp() > 1) {
                        mploss = stats.getMp() - 1;
                    }
                    if (chr.getBuffedValue(MapleBuffStat.INFINITY) != null) {
                        mploss = 0;
                    }
                    chr.addMPHP(-hploss, -mploss);
                    //} else if (mpattack > 0) {
                    //    chr.addMPHP(-damage, -mpattack);
                } else {
                    mploss = (int) (damage * (chr.getBuffedValue(MapleBuffStat.MAGIC_GUARD).doubleValue() / 100.0)) + mpattack;
                    hploss = damage - mploss;
                    if (chr.getBuffedValue(MapleBuffStat.INFINITY) != null) {
                        mploss = 0;
                    } else if (mploss > stats.getMp()) {
                        mploss = stats.getMp();
                        hploss = damage - mploss + mpattack;
                    }
                    chr.addMPHP(-hploss, -mploss);
                    //chr.dropMessage(5, "4");
                }

            } else if (chr.getBuffedValue(MapleBuffStat.MESOGUARD) != null) {
                damage = (damage % 2 == 0) ? damage / 2 : (damage / 2 + 1);

                final int mesoloss = (int) (damage * (chr.getBuffedValue(MapleBuffStat.MESOGUARD).doubleValue() / 100.0));
                if (chr.getMeso() < mesoloss) {
                    chr.gainMeso(-chr.getMeso(), false);
                    chr.cancelBuffStats(MapleBuffStat.MESOGUARD);
                } else {
                    chr.gainMeso(-mesoloss, false);
                }
                if (isDeadlyAttack && stats.getMp() > 1) {
                    mpattack = stats.getMp() - 1;
                }
                chr.addMPHP(-damage, -mpattack);
            } else if (isDeadlyAttack) {
                chr.addMPHP(stats.getHp() > 1 ? -(stats.getHp() - 1) : 0, stats.getMp() > 1 ? -(stats.getMp() - 1) : 0);

            } else {
                chr.addMPHP(-damage, -mpattack);
            }
            //�жϼ���BUFF����
            if (chr.isActiveBuffedValue(21120007)) {
                damage -= damage % chr.getSkillLevel(21120007);
            }
            chr.handleBattleshipHP(-damage);
            chr.�ۼƵ�Ѫ += damage;
        }
//        if (c.getPlayer().hasEquipped(1113011)) {//�ǻ��ָ
//            chr.�ǻ��ָ();
//        }
        if (!chr.isHidden()) {
            chr.getMap().broadcastMessage(chr, MaplePacketCreator.damagePlayer(type, monsteridfrom, chr.getId(), damage, fake, direction, reflect, is_pg, oid, pos_x, pos_y), false);
        }
    }

    public static final void AranCombo(final MapleClient c, final MapleCharacter chr) {//ս������

        if (chr != null && (chr.getJob() >= 2000 && chr.getJob() <= 2112) || (chr.getJob() >= 100 && chr.getJob() <= 199)) {
            //if (chr != null ) {
            short combo = chr.getCombo();
            final long curr = System.currentTimeMillis();

            if (combo > 0 && (curr - chr.getLastCombo()) > 7000) {
                // Official MS timing is 3.5 seconds, so 7 seconds should be safe.
                //chr.getCheatTracker().registerOffense(CheatingOffense.ARAN_COMBO_HACK);
                combo = 0;
                final ISkill skill = SkillFactory.getSkill(21000000);
                final ISkill skillA = SkillFactory.getSkill(21110000);
                if (combo <= 1 && skill != null) {
                    //   chr.cancelEffect(skill.getEffect(1), false, -1);
                    SkillFactory.getSkill(21000000).getEffect((short) 0).applyComboBuff(chr, (short) 0);
                }
                if (combo <= 1 && skillA != null) {
                    //  chr.cancelEffect(skillA.getEffect(1), false, -1);
                    SkillFactory.getSkill(21110000).getEffect((short) 0).applyComboBuff(chr, (short) 0);
                }
            }
            if (combo < 30000) {
                combo++;
            }
            chr.setLastCombo(curr);
            chr.setCombo(combo);
            switch (combo) { // Hackish method xD
                case 10:
                case 20:
                case 30:
                case 40:
                case 50:
                case 60:
                case 70:
                case 80:
                case 90:
                case 100:
                    if (chr.getSkillLevel(21000000) >= (combo / 10)) {
                        SkillFactory.getSkill(21000000).getEffect(combo / 10).applyComboBuff(chr, combo);
                    }
                    if (chr.getSkillLevel(21110000) >= (combo / 10)) {
                        SkillFactory.getSkill(21110000).getEffect(combo / 10).applyComboBuff(chr, combo);
                    }
                    break;
            }
            c.sendPacket(MaplePacketCreator.testCombo(combo));
            chr.setLastCombo(curr);
        }
    }

    public static final void UseItemEffect(final int itemId, final MapleClient c, final MapleCharacter chr) {//����
        final IItem toUse = chr.getInventory(MapleInventoryType.CASH).findById(itemId);
        if (toUse == null || toUse.getItemId() != itemId || toUse.getQuantity() < 1) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (itemId != 5510000) {
            chr.setItemEffect(itemId);
        }
        if (chr.haveItem(5511000, 1, true, true)) {
            c.getPlayer().dropMessage(5, "����һ������ʯ����");
            MapleInventoryManipulator.removeById(c, GameConstants.getInventoryType(5530212), 5530212, 1, true, false);
            c.getPlayer().addHP(30000);
            c.getPlayer().addMP(30000);
        } else {
            byte flag = toUse.getFlag();
            chr.getMap().broadcastMessage(chr, MaplePacketCreator.itemEffects(chr.getId(), itemId), false);
            if (ItemFlag.KARMA_EQ.check(flag)) {
                toUse.setFlag((byte) (flag - ItemFlag.KARMA_EQ.getValue()));
                c.sendPacket(MaplePacketCreator.getCharInfo(chr));
                chr.getMap().removePlayer(chr);
                chr.getMap().addPlayer(chr);
                // c.sendPacket(MaplePacketCreator.updateSpecialItemUse_(toUse, GameConstants.getInventoryType(toUse.getItemId()).getType()));
            } else if (ItemFlag.KARMA_USE.check(flag)) {
                toUse.setFlag((byte) (flag - ItemFlag.KARMA_USE.getValue()));
                c.sendPacket(MaplePacketCreator.getCharInfo(chr));
                chr.getMap().removePlayer(chr);
                chr.getMap().addPlayer(chr);
                // c.sendPacket(MaplePacketCreator.updateSpecialItemUse_(toUse, GameConstants.getInventoryType(toUse.getItemId()).getType()));
            }
        }
    }

    public static final void CancelItemEffect(final int id, final MapleCharacter chr) {
        chr.cancelEffect(MapleItemInformationProvider.getInstance().getItemEffect(-id), false, -1);
    }

    public static final void CancelBuffHandler(final int sourceid, final MapleCharacter chr) {
        if (chr == null) {
            return;
        }
        final ISkill skill = SkillFactory.getSkill1(sourceid);
        if (sourceid == 1013) {// && chr.getMountId() != 0
            chr.setKeyDownSkill_Time(0);
            chr.getMap().broadcastMessage(chr, MaplePacketCreator.skillCancel(chr, 1013), false);
        }

        if (skill.isChargeSkill()) {
            chr.setKeyDownSkill_Time(0);
            chr.getMap().broadcastMessage(chr, MaplePacketCreator.skillCancel(chr, sourceid), false);
        } else {
            chr.cancelEffect(skill.getEffect(1), false, -1);
        }
    }

    public static final void SkillEffect(final LittleEndianAccessor slea, final MapleCharacter chr) {
        final int skillId = slea.readInt();
        final byte level = slea.readByte();
        final byte flags = slea.readByte();
        final byte speed = slea.readByte();
        final byte unk = slea.readByte(); // Added on v.82

        final ISkill skill = SkillFactory.getSkill(skillId);
        if (chr == null) {
            return;
        }
        final int skilllevel_serv = chr.getSkillLevel(skill);

        if (skilllevel_serv > 0 && skilllevel_serv == level && skill.isChargeSkill()) {
            chr.setKeyDownSkill_Time(System.currentTimeMillis());
            chr.getMap().broadcastMessage(chr, MaplePacketCreator.skillEffect(chr, skillId, level, flags, speed, unk), false);
        }
    }

    public static final void SpecialMove(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null || !chr.isAlive() || chr.getMap() == null) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        slea.skip(4); // Old X and Y
        final int skillid = slea.readInt();
        final int skillLevel = slea.readByte();
        final ISkill skill = SkillFactory.getSkill(skillid);
        if (chr.getSkillLevel(skill) <= 0 || chr.getSkillLevel(skill) != skillLevel) {

            if (!GameConstants.isMulungSkill(skillid) && !GameConstants.isPyramidSkill(skillid)) {
                //  c.getSession().close();
                return;
            }
            if (GameConstants.isMulungSkill(skillid)) {
                if (chr.getMapId() / 10000 != 92502) {
                    //AutobanManager.getInstance().autoban(c, "Using Mu Lung dojo skill out of dojo maps.");
                    return;
                } else {
                    chr.mulung_EnergyModify(false);
                }
            } else if (GameConstants.isPyramidSkill(skillid)) {
                if (chr.getMapId() / 10000 != 92602) {
                    //AutobanManager.getInstance().autoban(c, "Using Pyramid skill out of pyramid maps.");
                    return;
                }
            }
        }
        final MapleStatEffect effect = skill.getEffect(chr.getSkillLevel(GameConstants.getLinkedAranSkill(skillid)));

        if (effect.getCooldown() > 0 && !chr.isGM()) {
            if (chr.skillisCooling(skillid)) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            if (skillid != 5221006) { // Battleship
                c.sendPacket(MaplePacketCreator.skillCooldown(skillid, effect.getCooldown()));
                chr.addCooldown(skillid, System.currentTimeMillis(), effect.getCooldown() * 1000);
            }
        }

        if (chr.F().get(FM("�Ż�����")) != null) {
            double a = Math.ceil(Math.random() * 100);
            if (a <= chr.F().get(FM("�Ż�����"))) {
                for (MapleCoolDownValueHolder i : chr.getCooldowns()) {
                    chr.removeCooldown(i.skillId);
                    chr.getClient().sendPacket(MaplePacketCreator.skillCooldown(i.skillId, 0));
                    chr.dropMessage(5, "[�Ż�����]:������м�����ȴʱ�䡣");
                }
            }
        }
        //chr.checkFollow(); //not msea-like but ALEX'S WISHES
        switch (skillid) {

            /* case 1002:
                //c.getPlayer().getClient().sendPacket(MaplePacketCreator.sendHint("#e#b���粽#k\r\n#rͻ���˺�����\r\n", 120, 1));
                //c.sendPacket(MaplePacketCreator.showEffect("CrossBrigade/CherylAction"));
                chr.getClient().sendPacket(MaplePacketCreator.showEffect("ajob/archer0/0/0"));
                c.sendPacket(MaplePacketCreator.enableActions());
                break;*/
            case 1121001:
            case 1221001:
            case 1321001:
            case 9001020: // GM magnet
                final byte number_of_mobs = slea.readByte();
                slea.skip(3);
                for (int i = 0; i < number_of_mobs; i++) {
                    int mobId = slea.readInt();

                    final MapleMonster mob = chr.getMap().getMonsterByOid(mobId);
                    if (mob != null) {
//			chr.getMap().broadcastMessage(chr, MaplePacketCreator.showMagnet(mobId, slea.readByte()), chr.getPosition());
                        mob.switchController(chr, mob.isControllerHasAggro());
                    }
                }
                chr.getMap().broadcastMessage(chr, MaplePacketCreator.showBuffeffect(chr.getId(), skillid, 1, slea.readByte()), chr.getPosition());
                c.sendPacket(MaplePacketCreator.enableActions());
                break;
            default:
                Point pos = null;
                if (slea.available() == 7 || skill.getId() == 3111002 || skill.getId() == 3211002) {
                    pos = slea.readPos();
                }

                if (effect.isMagicDoor()) { // Mystic Door
                    if (!FieldLimitType.MysticDoor.check(chr.getMap().getFieldLimit())) {
                        effect.applyTo(c.getPlayer(), pos);
                    } else {
                        c.sendPacket(MaplePacketCreator.enableActions());
                    }

                } else {
                    final int mountid = MapleStatEffect.parseMountInfo(c.getPlayer(), skill.getId());
                    if (mountid != 0 && mountid != GameConstants.getMountItem(skill.getId()) && !c.getPlayer().isGM() && c.getPlayer().getBuffedValue(MapleBuffStat.MONSTER_RIDING) == null && c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem((byte) -118) == null) {
                        if (!GameConstants.isMountItemAvailable(mountid, c.getPlayer().getJob())) {
                            c.sendPacket(MaplePacketCreator.enableActions());
                            return;
                        }
                    }
                    effect.applyTo(c.getPlayer(), pos);
                }
                break;
        }
    }

    //������
    public static final void closeRangeAttack(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr, final boolean energy) {
        if (chr == null || (energy && chr.getBuffedValue(MapleBuffStat.ENERGY_CHARGE) == null && chr.getBuffedValue(MapleBuffStat.BODY_PRESSURE) == null && !GameConstants.isKOC(chr.getJob()))) {
            return;
        }
        final AttackInfo attack = DamageParse.Modify_AttackCrit(DamageParse.parseDmgM(slea, chr), chr, 1);
        final boolean mirror = chr.getBuffedValue(MapleBuffStat.MIRROR_IMAGE) != null;
        double maxdamage = chr.getStat().getCurrentMaxBaseDamage();
        int attackCount = (chr.getJob() >= 430 && chr.getJob() <= 434 ? 2 : 1), skillLevel = 0;
        MapleStatEffect effect = null;
        ISkill skill = null;
        if (attack.skill == 21100004 || attack.skill == 21100005 || attack.skill == 21110003 || attack.skill == 21110004 || attack.skill == 21120006 || attack.skill == 21120007) {
            chr.setCombo((byte) 1);
        }
        if (attack.skill != 0) {
            skill = SkillFactory.getSkill(GameConstants.getLinkedAranSkill(attack.skill));
            skillLevel = chr.getSkillLevel(skill);
            effect = attack.getAttackEffect(chr, skillLevel, skill);
            if (effect == null) {
                return;
            }
            maxdamage *= effect.getDamage() / 100.0;
            attackCount = effect.getAttackCount();

            if (effect.getCooldown() > 0 && !chr.isGM()) {
                if (chr.skillisCooling(attack.skill)) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                c.sendPacket(MaplePacketCreator.skillCooldown(attack.skill, effect.getCooldown()));
                chr.addCooldown(attack.skill, System.currentTimeMillis(), effect.getCooldown() * 1000);
            }
        }

        attackCount *= (mirror ? 2 : 1);//�չ���
        if (!energy) {
            if ((chr.getMapId() == 109060000 || chr.getMapId() == 109060002 || chr.getMapId() == 109060004) && attack.skill == 0) {
                MapleSnowballs.hitSnowball(chr);
            }
            int numFinisherOrbs = 0;
            final Integer comboBuff = chr.getBuffedValue(MapleBuffStat.COMBO);
            //��������
            if (isFinisher(attack.skill)) {
                if (comboBuff != null) {
                    numFinisherOrbs = comboBuff.intValue() - 1;
                }
                chr.handleOrbconsume();
            } else if (attack.targets > 0 && comboBuff != null) {
                // handle combo orbgain
                switch (chr.getJob()) {
                    case 111:
                    case 112:
                    case 1110:
                    case 1111:
                        if (attack.skill != 1111008) { // shout should not give orbs
                            chr.handleOrbgain();
                        }
                        break;
                }
            }
            switch (chr.getJob()) {
                case 511:
                case 512: {
                    chr.handleEnergyCharge(5110001, attack.targets * attack.hits);
                    break;
                }
                case 1510:
                case 1511:
                case 1512: {
                    chr.handleEnergyCharge(15100004, attack.targets * attack.hits);
                    break;
                }
            }

            if (attack.targets > 0 && attack.skill == 1211002) {
                final int advcharge_level = chr.getSkillLevel(SkillFactory.getSkill(1220010));
                if (advcharge_level > 0) {
                    if (!SkillFactory.getSkill(1220010).getEffect(advcharge_level).makeChanceResult()) {
                        chr.cancelEffectFromBuffStat(MapleBuffStat.WK_CHARGE);
                        chr.cancelEffectFromBuffStat(MapleBuffStat.LIGHTNING_CHARGE);
                    }
                } else {
                    chr.cancelEffectFromBuffStat(MapleBuffStat.WK_CHARGE);
                    chr.cancelEffectFromBuffStat(MapleBuffStat.LIGHTNING_CHARGE);
                }
            }

            if (numFinisherOrbs > 0) {
                maxdamage *= numFinisherOrbs;
            } else if (comboBuff != null) {
                ISkill combo;
                if (c.getPlayer().getJob() == 1110 || c.getPlayer().getJob() == 1111) {
                    combo = SkillFactory.getSkill(11111001);
                } else {
                    combo = SkillFactory.getSkill(1111002);
                }
                if (c.getPlayer().getSkillLevel(combo) > 0) {
                    maxdamage *= 1.0 + (combo.getEffect(c.getPlayer().getSkillLevel(combo)).getDamage() / 100.0 - 1.0) * (comboBuff.intValue() - 1);
                }
            }

            if (isFinisher(attack.skill)) {
                if (numFinisherOrbs == 0) {
                    return;
                }
                //maxdamage = 199999; // FIXME reenable damage calculation for finishers
                maxdamage = 199999;//�˺���ʾ��
            }
        }
        chr.checkFollow();
        //��������ʾ�˺�
        chr.getMap().broadcastMessage(chr, MaplePacketCreator.closeRangeAttack(chr.getId(), attack.tbyte, attack.skill, skillLevel, attack.display, attack.animation, attack.speed, attack.allDamage, energy, chr.getLevel(), chr.getStat().passive_mastery(), attack.unk, attack.charge), chr.getPosition());
        DamageParse.applyAttack(attack, skill, c.getPlayer(), attackCount, maxdamage, effect, mirror ? AttackType.NON_RANGED_WITH_MIRROR : AttackType.NON_RANGED);
    }

    //����Զ�̹���
    public static final void rangedAttack(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {//Զ�̹���
        if (chr == null) {
            return;
        }
        /* if (!chr.isAlive() || chr.getMap() == null) {
            chr.getCheatTracker().registerOffense(CheatingOffense.������������);
            return;
        }*///���
        final AttackInfo attack = DamageParse.Modify_AttackCrit(DamageParse.parseDmgR(slea, chr), chr, 2);

        int bulletCount = 1, skillLevel = 0;
        MapleStatEffect effect = null;
        ISkill skill = null;

        if (attack.skill != 0) {
            skill = SkillFactory.getSkill(GameConstants.getLinkedAranSkill(attack.skill));
            skillLevel = chr.getSkillLevel(skill);
            effect = attack.getAttackEffect(chr, skillLevel, skill);
            if ("��".equals(����)) {
                chr.dropMessage(5, "skill: " + skill);
                chr.dropMessage(5, "skillLevel: " + skillLevel);
            }
            if (effect == null) {
                return;
            }

            switch (attack.skill) {
                case 13111007:
                case 21120006:
                case 21110004: // Ranged but uses attackcount instead
                case 14101006: // Vampure
                    bulletCount = effect.getAttackCount();
                    break;
                default:
                    bulletCount = effect.getBulletCount();
                    break;
            }
            if (effect.getCooldown() > 0 && !chr.isGM()) {
                if (chr.skillisCooling(attack.skill)) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                c.sendPacket(MaplePacketCreator.skillCooldown(attack.skill, effect.getCooldown()));
                chr.addCooldown(attack.skill, System.currentTimeMillis(), effect.getCooldown() * 1000);
                if ("��".equals(����)) {
                    chr.dropMessage(5, "" + attack.skill + " / " + effect.getCooldown() + "");
                    chr.dropMessage(5, "" + attack.skill + " / " + System.currentTimeMillis() + " / " + effect.getCooldown());
                }
            }
        }
        final Integer ShadowPartner = chr.getBuffedValue(MapleBuffStat.SHADOWPARTNER);
        if (ShadowPartner != null) {
            bulletCount *= 2;
            if ("��".equals(����)) {
                chr.dropMessage(5, "ShadowPartner: " + ShadowPartner);
            }
        }
        int projectile = 0, visProjectile = 0;
        if (attack.AOE != 0 && chr.getBuffedValue(MapleBuffStat.SOULARROW) == null && attack.skill != 4111004) {
            if (chr.getInventory(MapleInventoryType.USE).getItem(attack.slot) == null) {
                return;
            }
            projectile = chr.getInventory(MapleInventoryType.USE).getItem(attack.slot).getItemId();
            if ("��".equals(����)) {
                chr.dropMessage(5, "projectile: " + projectile);
            }
            if (attack.csstar > 0) {
                if (chr.getInventory(MapleInventoryType.CASH).getItem(attack.csstar) == null) {
                    if ("��".equals(����)) {
                        chr.dropMessage(5, "csstar: " + attack.csstar + "");
                    }
                    return;
                }
                visProjectile = chr.getInventory(MapleInventoryType.CASH).getItem(attack.csstar).getItemId();
                if ("��".equals(����)) {
                    chr.dropMessage(5, "visProjectile: " + visProjectile + "");
                }
            } else {
                visProjectile = projectile;
                if ("��".equals(����)) {
                    chr.dropMessage(5, "visProjectile: " + visProjectile + "");
                }
            }
            // Handle bulletcount
            if (chr.getBuffedValue(MapleBuffStat.SPIRIT_CLAW) == null) {//��/�ӵ�/����
                int bulletConsume = bulletCount;
                if (effect != null && effect.getBulletConsume() != 0) {
                    bulletConsume = effect.getBulletConsume() * (ShadowPartner != null ? 2 : 1);
                    if ("��".equals(����)) {
                        chr.dropMessage(5, "bulletConsume: " + bulletConsume + "");
                    }
                }
                if (!MapleInventoryManipulator.removeById(c, MapleInventoryType.USE, projectile, bulletConsume, false, true)) {
                    chr.dropMessage(5, "��û���㹻�ļ�/�ӵ�/����.");
                    return;
                }
            }
        }

        double basedamage;
        int projectileWatk = 0;
        if (projectile != 0) {
            projectileWatk = MapleItemInformationProvider.getInstance().getWatkForProjectile(projectile);
            if ("��".equals(����)) {
                chr.dropMessage(5, "projectile: " + projectile + "");
                chr.dropMessage(5, "projectileWatk: " + projectileWatk + "");
            }
        }
        final PlayerStats statst = chr.getStat();
        if ("��".equals(����)) {
            chr.dropMessage(5, "PlayerStats statst: " + statst);
        }
        switch (attack.skill) {
            case 4001344: // Lucky Seven
            case 4121007: // Triple Throw
            case 14001004: // Lucky seven
            case 14111005: // Triple Throw
                basedamage = (float) ((float) ((statst.getTotalLuk() * 5.0f) * (statst.getTotalWatk() + projectileWatk)) / 100);
                break;
            case 4111004: // Shadow Meso
//		basedamage = ((effect.getMoneyCon() * 10) / 100) * effect.getProb(); // Not sure
                basedamage = 13000;
                break;
            default:
                if (projectileWatk != 0) {
                    basedamage = statst.calculateMaxBaseDamage(statst.getTotalWatk() + projectileWatk);
                    if ("��".equals(����)) {
                        chr.dropMessage(5, "basedamage: " + basedamage);
                    }
                } else {
                    basedamage = statst.getCurrentMaxBaseDamage();
                    if ("��".equals(����)) {
                        chr.dropMessage(5, "basedamage: " + basedamage);
                    }
                }
                switch (attack.skill) {
                    case 3101005: // arrowbomb is hardcore like that
                    case 3111006://��ɨ��
                        basedamage *= effect.getX() / 100.0;
                        //chr.dropMessage(5, "��ɨ��: " + basedamage);
                        break;
                }
                break;
        }
        if (effect != null) {
            basedamage *= effect.getDamage() / 100.0;
            if ("��".equals(����)) {
                chr.dropMessage(5, "basedamage: " + basedamage + "");
            }
            int money = effect.getMoneyCon();
            if (money != 0) {
                if (money > chr.getMeso()) {
                    money = chr.getMeso();
                    if ("��".equals(����)) {
                        chr.dropMessage(5, "money: " + money + "");
                    }
                }
                chr.gainMeso(-money, false);
                if ("��".equals(����)) {
                    chr.dropMessage(5, "gainMeso: " + money + "");
                }
            }
        }
        chr.checkFollow();
        chr.getMap().broadcastMessage(chr, MaplePacketCreator.rangedAttack(chr.getId(), attack.tbyte, attack.skill, skillLevel, attack.display, attack.animation, attack.speed, visProjectile, attack.allDamage, attack.position, chr.getLevel(), chr.getStat().passive_mastery(), attack.unk), chr.getPosition());
        DamageParse.applyAttack(attack, skill, chr, bulletCount, basedamage, effect, ShadowPartner != null ? AttackType.RANGED_WITH_SHADOWPARTNER : AttackType.RANGED);
    }

    public static final void MagicDamage(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {//�չ�
        if (chr == null) {
            return;
        }

        final AttackInfo attack = DamageParse.Modify_AttackCrit(DamageParse.parseDmgMa(slea, chr), chr, 3);
        final ISkill skill = SkillFactory.getSkill(GameConstants.getLinkedAranSkill(attack.skill));
        final int skillLevel = chr.getSkillLevel(skill);
        final MapleStatEffect effect = attack.getAttackEffect(chr, skillLevel, skill);
        if (effect == null) {
            return;
        }
        if (effect.getCooldown() > 0 && !chr.isGM()) {
            if (chr.skillisCooling(attack.skill)) {
                c.sendPacket(MaplePacketCreator.enableActions());
                return;
            }
            c.sendPacket(MaplePacketCreator.skillCooldown(attack.skill, effect.getCooldown()));
            chr.addCooldown(attack.skill, System.currentTimeMillis(), effect.getCooldown() * 1000);
        }
        if (chr.getMapId() == 100000203 && MapleParty.����ɱ� > 0) {
            if (attack.skill == 2301002) {
                c.getPlayer().changeMap(100000000, 0);
                chr.dropMessage(1, "���ͼ��ֹʹ�����Ƽ���");
            }
        }

        chr.checkFollow();//ħ������
        chr.getMap().broadcastMessage(chr, MaplePacketCreator.magicAttack(chr.getId(), attack.tbyte, attack.skill, skillLevel, attack.display, attack.animation, attack.speed, attack.allDamage, attack.charge, chr.getLevel(), attack.unk), chr.getPosition());
        DamageParse.applyAttackMagic(attack, skill, c.getPlayer(), effect);
    }

    public static final void DropMeso(final int meso, final MapleCharacter chr) {//�����
        int ������ҿ��� = gui.Start.ConfigValuesMap.get("������ҿ���");
        if (������ҿ��� > 0) {
            chr.dropMessage(1, "����Ա�Ѿ��Ӻ�̨��ֹ�������");
            chr.getClient().sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (!chr.isAlive() || (meso < 10 || meso > 50000) || (meso > chr.getMeso())) {
            chr.getClient().sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        Calendar calendar = Calendar.getInstance();
        //�ʵ�
        if (meso == 250 && chr.getMapId() == 104010001 && chr.getBossLog("�ʵ�1") == 0) {//&& calendar.get(Calendar.HOUR_OF_DAY) == 7
            chr.modifyCSPoints(1, 100, true);
            chr.setBossLog("�ʵ�1");
            chr.dropMessage(1, "[С�ʵ�] : \r\n��ϲ�㴥���ʵ������ 100 ��ȯ��");
        }

        chr.gainMeso(-meso, false, true);
        chr.getMap().spawnMesoDrop(meso, chr.getPosition(), chr, chr, true, (byte) 0);
        chr.getCheatTracker().checkDrop(true);

    }

    public static final void ChangeEmotion(final int emote, final MapleCharacter chr) {//������
        if (emote > 7) {
            final int emoteid = 5159992 + emote;
            final MapleInventoryType type = GameConstants.getInventoryType(emoteid);
            if (chr.getInventory(type).findById(emoteid) == null) {
                chr.getCheatTracker().registerOffense(CheatingOffense.ʹ�ò����ڵ���, Integer.toString(emoteid));
                return;
            }
        }
        if (emote > 0 && chr != null && chr.getMap() != null) { //O_o
            chr.getMap().broadcastMessage(chr, MaplePacketCreator.facialExpression(chr, emote), false);
        }
        /*String ���� = "";
        switch (emote) {
            case 1:
                ���� = "��ü";
                break;
            case 2:
                ���� = "΢Ц";
                break;
            case 3:
                ���� = "����";
                break;
            case 4:
                ���� = "���";
                break;
            case 5:
                ���� = "��ŭ";
                break;
            case 6:
                ���� = "ãȻ";
                break;
            case 7:
                ���� = "����";
                break;
            default:
                ���� = "δ¼�� " + emote;
                break;
        }*/
        //System.err.println("[�����]" + CurrentReadable_Time() + " : [" + chr.getName() + "������] " + ����);
        //�ʵ�3
        if (chr.getBossLog("�ʵ�3") == 0) {
            chr.gainExp(500, true, true, false);
            chr.setBossLog("�ʵ�3");
            chr.dropMessage(1, "[С�ʵ�] : \r\n��ϲ�㴥���ʵ������ 500 ���顣");
        }
        //�ʵ�6
        if (emote == 2) {
            if (!chr.isAlive() && chr.getBossLog("�ʵ�6") == 0) {
                chr.addHP(30000);
                chr.addMP(30000);
                chr.setBossLog("�ʵ�6");
                chr.dropMessage(1, "[С�ʵ�] : \r\n��ϲ�㴥���ʵ������ 1 ���顣");
                chr.dropMessage(1, "��ϲ��������������ֵġ�");
            }
        }
    }

    public static final void Heal(final LittleEndianAccessor slea, final MapleCharacter chr) {
        /*
        ��ɫ�ָ�Ѫ��   
         */
 /*if (MapleParty.����ģʽ != 0) {
            return;
        }*/
        if (chr == null) {
            return;
        }

        chr.updateTick(slea.readInt());
        /*
         * if (slea.available() >= 8) { slea.skip(4); }
         */
        int healHP = slea.readShort();
        int healMP = slea.readShort();
        final PlayerStats stats = chr.getStat();
        int check_hp = (int) stats.getHealHP();
        int check_mp = (int) stats.getHealMP();

        if (stats.getHp() <= 0) {
            return;
        }
        if (chr.canHP()) {
            if (healHP != 0) {
                if (chr.getChair() != 0) {
                    check_hp += 150;
                }
                if (healHP > check_hp * 2 && healHP > 20) {
                    chr.getCheatTracker().registerOffense(CheatingOffense.�ظ�����HP, String.valueOf(healHP) + " ������:" + check_hp);
                    //  healHP = check_hp;
                }
                chr.addHP(healHP);
                if ("��".equals(����)) {
                    chr.dropMessage(5, "healHP:" + healHP);
                }
            }
        }
        if (chr.canMP()) {
            if (healMP != 0) {
                if (healMP > check_mp * 2 && healMP > 20) {
                    chr.getCheatTracker().registerOffense(CheatingOffense.�ظ�����MP, String.valueOf(healMP) + "������:" + check_mp);
                    //  healMP = check_mp;
                }
                chr.addMP(healMP);
                if ("��".equals(����)) {
                    chr.dropMessage(5, "healMP:" + healMP);
                }
            }
        }

    }

    /**
     * ��ɫ�ƶ�
     * @param slea ����
     * @param c netty client
     * @param chr ��ɫ��Ϣ
     */
    public static final void MovePlayer(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {//��ɫ�ƶ��ͻ��ж�
        if (chr == null) {
            return;
        }
        // chr.dropMessage(5, "" + c.getPlayer().getPosition().distanceSq(c.getPlayer().getPosition()) + " x: " + c.getPlayer().getPosition().x + " y: " + c.getPlayer().getPosition().y);

        //V.80 MSEA����4�ֽ�
        final Point Original_Pos = chr.getPosition();
        slea.skip(33);
        List<LifeMovementFragment> res;
        try {
            res = MovementParse.parseMovement(slea, 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("AIOBE Type1:\n" + slea.toString(true));
            return;
        }
        // ����֤��������
        if (res != null && c.getPlayer().getMap() != null) {
            if (slea.available() < 13 || slea.available() > 26) {
                System.out.println("���ã���13-26���˶���������\n" + slea.toString(true));
                return;
            }
            final List<LifeMovementFragment> res2 = new ArrayList<LifeMovementFragment>(res);
            final MapleMap map = c.getPlayer().getMap();

            if (chr.isHidden()) {
                chr.setLastRes(res2);
                c.getPlayer().getMap().broadcastGMMessage(chr, MaplePacketCreator.movePlayer(chr.getId(), res, Original_Pos), false);
            } else {
                c.getPlayer().getMap().broadcastMessage(c.getPlayer(), MaplePacketCreator.movePlayer(chr.getId(), res, Original_Pos), false);
            }
            MovementParse.updatePosition(res, chr, 0);
            final Point pos = chr.getPosition();
            map.movePlayer(chr, pos);
            if (chr.getFollowId() > 0 && chr.isFollowOn() && chr.isFollowInitiator()) {
                final MapleCharacter fol = map.getCharacterById(chr.getFollowId());
                if (fol != null) {
                    final Point original_pos = fol.getPosition();
                    // fol.getClient().sendPacket(MaplePacketCreator.moveFollow(Original_Pos, original_pos, pos, res));
                    MovementParse.updatePosition(res, fol, 0);
                    //map.broadcastMessage(fol, MaplePacketCreator.movePlayer(fol.getId(), res, original_pos), false);
                } else {
                    chr.checkFollow();
                }
            }
            int count = c.getPlayer().getFallCounter();
            try {
                if (map.getFootholds().findBelow(c.getPlayer().getPosition()) == null && c.getPlayer().getPosition().y > c.getPlayer().getOldPosition().y && c.getPlayer().getPosition().x == c.getPlayer().getOldPosition().x) {
                    if (count > 10) {
                        c.getPlayer().changeMap(map, map.getPortal(0));
                        c.getPlayer().setFallCounter(0);
                    } else {
                        c.getPlayer().setFallCounter(++count);
                    }
                } else if (count > 0) {
                    c.getPlayer().setFallCounter(0);
                }
            } catch (Exception e) {
            }
            c.getPlayer().setOldPosition(new Point(c.getPlayer().getPosition()));
        }
    }

    public static final void UpdateHandler(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        c.getPlayer().saveToDB(true, true);
    }

    public static final void ChangeMapSpecial(final LittleEndianAccessor slea, final String portal_name, final MapleClient c, final MapleCharacter chr) {
        slea.readShort();
        final MaplePortal portal = chr.getMap().getPortal(portal_name);
//	slea.skip(2);

        if (portal != null) {
            portal.enterPortal(c);
        } else {
            c.sendPacket(MaplePacketCreator.enableActions());
        }
    }

    public static final void ChangeMap(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null) {

            // chr.dropMessage(5, "[Сz��ʾ]��������ֽ�ͼ�޷�Ӧ��NPC�޷�Ӧ����򿪶๦�ܺ��Ӽ���");
            c.sendPacket(MaplePacketCreator.enableActions());//�⿨
            NPCScriptManager.getInstance().dispose(c);
            return;
        }
        if (slea.available() == 0L) {
            String[] socket = c.getChannelServer().getIP().split(":");
            chr.saveToDB(false, false);
            //   chr.setInCS(false);
            c.getChannelServer().removePlayer(c.getPlayer());
            c.updateLoginState(MapleClient.LOGIN_SERVER_TRANSITION);
            try {
                c.sendPacket(MaplePacketCreator.getChannelChange(InetAddress.getByName(socket[0]), Integer.parseInt(socket[1])));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return;
        }
        if (slea.available() != 0) {
//            slea.skip(6); //D3 75 00 00 00 00
            slea.readByte(); // 1 = from dying 2 = regular portals
            int targetid = slea.readInt(); // FF FF FF FF
            if (targetid == 0) {
                targetid = 1000000;
            }
            final MaplePortal portal = chr.getMap().getPortal(slea.readMapleAsciiString());
            /*
             * if (slea.available() >= 7) { chr.updateTick(slea.readInt()); }
             */
            //  slea.skip(1);
            final boolean wheel = slea.readShort() > 0 && !MapConstants.isEventMap(chr.getMapId()) && chr.haveItem(5510000, 1, false, true);

            if (targetid != -1 && !chr.isAlive()) {
                chr.setStance(0);
                if (chr.getEventInstance() != null && chr.getEventInstance().revivePlayer(chr) && chr.isAlive()) {
                    return;
                }
                if (chr.getPyramidSubway() != null) {
                    chr.getStat().setHp((short) 50);
                    chr.getPyramidSubway().fail(chr);
                    return;
                }

                if (!wheel) {
                    chr.getStat().setHp((short) 50);
                    MapleMap to = chr.getMap().getReturnMap();
                    if (to == null) {
                        chr.setHp(50);
                        chr.updateSingleStat(MapleStat.HP, 50);
                        c.sendPacket(MaplePacketCreator.enableActions());
                        return;
//                        int mapid = chr.getMapId();
//                        mapid = mapid / 1000000;
//                        mapid *= 1000000;
//                        to = chr.getClient().getChannelServer().getMapFactory().getMap(mapid);
//                        System.out.println(mapid + "");
                    }

                    chr.changeMap(to, to.getPortal(0));
                } else {
                    c.sendPacket(MTSCSPacket.useWheel((byte) (chr.getInventory(MapleInventoryType.CASH).countById(5510000) - 1)));
                    chr.getStat().setHp(((chr.getStat().getMaxHp() / 100) * 40));
                    MapleInventoryManipulator.removeById(c, MapleInventoryType.CASH, 5510000, 1, true, false);

                    final MapleMap to = chr.getMap();
                    chr.changeMap(to, to.getPortal(0));
                }
            } else if (targetid != -1 && chr.isGM()) {
                final MapleMap to = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                if (to != null && to.getPortal(0) != null) {
                    chr.changeMap(to, to.getPortal(0));
                }

            } else if (targetid != -1 && !chr.isGM()) {
                final int divi = chr.getMapId() / 100;
                if (divi == 9130401) { // Only allow warp if player is already in Intro map, or else = hack

                    if (targetid == 130000000 || targetid / 100 == 9130401) { // Cygnus introduction
                        final MapleMap to = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                        chr.changeMap(to, to.getPortal(0));
                    }
                } else if (divi == 9140900) { // Aran Introduction
                    if (targetid == 914090011 || targetid == 914090012 || targetid == 914090013 || targetid == 140090000) {
                        final MapleMap to = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                        chr.changeMap(to, to.getPortal(0));
                    }
                } else if (divi == 9140901 && targetid == 140000000) {
                    //c.sendPacket(UIPacket.IntroDisableUI(false));
                    //   c.sendPacket(UIPacket.IntroLock(false));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    final MapleMap to = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to, to.getPortal(0));
                } else if (divi == 9140902 && (targetid == 140030000 || targetid == 140000000)) { //thing is. dont really know which one!
                    //  c.sendPacket(UIPacket.IntroDisableUI(false));
                    //   c.sendPacket(UIPacket.IntroLock(false));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    final MapleMap to = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to, to.getPortal(0));
                } else if (divi == 9000900 && targetid / 100 == 9000900 && targetid > chr.getMapId()) {
                    final MapleMap to = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to, to.getPortal(0));
                } else if (divi / 1000 == 9000 && targetid / 100000 == 9000) {
                    if (targetid < 900090000 || targetid > 900090004) { //1 movie
                        //      c.sendPacket(UIPacket.IntroDisableUI(false));
                        //      c.sendPacket(UIPacket.IntroLock(false));
                        c.sendPacket(MaplePacketCreator.enableActions());
                    }
                    final MapleMap to = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to, to.getPortal(0));
                    /*} else if (divi / 10 == 1020 && targetid == 1020000) { // Adventurer movie clip Intro
                    //  c.sendPacket(UIPacket.IntroDisableUI(false));
                    //   c.sendPacket(UIPacket.IntroLock(false));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    final MapleMap to = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to, to.getPortal(0));
                     */
                } else if (chr.getMapId() == 900090101 && targetid == 100030100) {
                    //    c.sendPacket(UIPacket.IntroDisableUI(false));
                    //    c.sendPacket(UIPacket.IntroLock(false));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    final MapleMap to = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to, to.getPortal(0));
                } else if (chr.getMapId() == 2010000 && targetid == 104000000) {
                    //   c.sendPacket(UIPacket.IntroDisableUI(false));
                    //    c.sendPacket(UIPacket.IntroLock(false));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    final MapleMap to = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to, to.getPortal(0));
                } else if (chr.getMapId() == 106020001 || chr.getMapId() == 106020502) {
                    if (targetid == (chr.getMapId() - 1)) {
                        //     c.sendPacket(UIPacket.IntroDisableUI(false));
                        //     c.sendPacket(UIPacket.IntroLock(false));
                        c.sendPacket(MaplePacketCreator.enableActions());
                        final MapleMap to = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                        chr.changeMap(to, to.getPortal(0));
                    }
                } else if (chr.getMapId() == 0 && targetid == 10000) {
                    //  c.sendPacket(UIPacket.IntroDisableUI(false));
                    //  c.sendPacket(UIPacket.IntroLock(false));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    final MapleMap to = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(targetid);
                    chr.changeMap(to, to.getPortal(0));
                }
            } else if (portal != null) {
                portal.enterPortal(c);
            } else {
                c.sendPacket(MaplePacketCreator.enableActions());
            }
        }
    }

    public static final void InnerPortal(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null) {
            return;
        }
        final MaplePortal portal = chr.getMap().getPortal(slea.readMapleAsciiString());
        final Point Original_Pos = chr.getPosition();
        final int toX = slea.readShort();
        final int toY = slea.readShort();
//	slea.readShort(); // Original X pos
//	slea.readShort(); // Original Y pos

        if (portal == null) {
            return;
        } else if (portal.getPosition().distanceSq(chr.getPosition()) > 22500) {
            chr.getCheatTracker().registerOffense(CheatingOffense.ʹ�ù�Զ���͵�);
        }
        chr.getMap().movePlayer(chr, new Point(toX, toY));
        chr.checkFollow();
    }

    public static final void snowBall(LittleEndianAccessor slea, MapleClient c) {
        //B2 00
        //01 [team]
        //00 00 [unknown]
        //89 [position]
        //01 [stage]
        c.sendPacket(MaplePacketCreator.enableActions());
        //empty, we do this in closerange
    }

    public static final void leftKnockBack(LittleEndianAccessor slea, final MapleClient c) {//ѩ����
        // D1 00 86 01 47 01
        if (c.getPlayer().getMapId() / 10000 == 10906) { //must be in snowball map or else its like infinite FJ
            c.sendPacket(MaplePacketCreator.leftKnockBack());
            c.sendPacket(MaplePacketCreator.enableActions());
        }
    }

//    public static void Rabbit(LittleEndianAccessor slea, final MapleClient c) {
//        int arrackfrom = slea.readInt();
//        int damage = slea.readInt() + 100;
//        int attackto = slea.readInt();
//        MapleMonster mob = c.getPlayer().getMap().getMonsterByOid(attackto);
//        if (mob != null && mob.getHp() > 0) {
//            mob.damage(c.getPlayer(), (long) damage, true);
//        }
//    }
    public static void Rabbit(LittleEndianAccessor slea, final MapleClient c) {//�޸����µ��˲���Ѫ
        // BB 00 [CC 86 01 00] [83 05 00 00] [E2 86 01 00]
        int arrackfrom = slea.readInt();
        int damage = slea.readInt() + 100;
        int attackto = slea.readInt();
        MapleMonster mob = c.getPlayer().getMap().getMonsterByOid(attackto);
        if (mob != null && mob.getHp() > 0) {
            mob.damage(c.getPlayer(), (long) damage, true, true);
        }
    }

    /*
     * public static final void UpdateFkCharMessages(final
     * LittleEndianAccessor slea, final MapleClient c, final
     * MapleCharacter chr) { int type = slea.readByte();
     * //chr.UpdateCharMessageZone(); //c.getPlayer().setcharmessage(s); // if
     * (type == 0) { // ��ɫӍϢ /*String
     *///int charmessage = slea.readMapleAsciiString();
    //c.getPlayer().setcharmessage(charmessage);
    //MapleCharacter.UpdateCharMessageZone();
    //chr.UpdateCharMessageZone();
    //System.err.println("SetCharMessage");
    /*
     * } else if (type == 1) { // ���� int expression = slea.readByte();
     * c.getPlayer().setexpression(expression);
     * System.err.println("Expression"); } else if (type == 2) { // ���ռ����� int
     * blood = slea.readByte(); int month = slea.readByte(); int day =
     * slea.readByte(); int constellation = slea.readByte();
     * c.getPlayer().setblood(blood); c.getPlayer().setmonth(month);
     * c.getPlayer().setday(day); c.getPlayer().setconstellation(constellation);
     * System.err.println("Constellation"); }
     */
    //}
    /*
     * public String getcharmessage(final MapleClient c) {
     *
     * return c.getPlayer().getcharmessage();
     *
     * }
     * public void setcharmessage(final MapleClient c, String s) {
     * c.getPlayer().setcharmessage(s);
     * c.sendPacket(MaplePacketCreator.updateBeans(c.getPlayer().getId(),
     * s)); }
     */
}
