/*
��ɫ�����ˣ��Լ�
 */
package client;

import static abc.Game.����;
import client.anticheat.CheatTracker;
import client.inventory.Equip;
import client.inventory.IItem;
import client.inventory.Item;
import client.inventory.ItemFlag;
import client.inventory.ItemLoader;
import client.inventory.MapleInventory;
import client.inventory.MapleInventoryIdentifier;
import client.inventory.MapleInventoryType;
import client.inventory.MapleMount;
import client.inventory.MaplePet;
import client.inventory.MapleRing;
import constants.GameConstants;
import constants.ServerConstants;
import database.DatabaseConnection;
import database.DatabaseException;
import static fumo.FumoSkill.FM;
import static gui.QQͨ��.Ⱥ֪ͨ;
import static gui.Start.��ȡ��������Ϣ����;
import handling.channel.ChannelServer;
import handling.login.LoginServer;
import handling.world.CharacterTransfer;
import handling.world.MapleMessenger;
import handling.world.MapleMessengerCharacter;
import handling.world.MapleParty;
import handling.world.MaplePartyCharacter;
import handling.world.PartyOperation;
import handling.world.PlayerBuffStorage;
import handling.world.PlayerBuffValueHolder;
import handling.world.World;
import handling.world.family.MapleFamily;
import handling.world.family.MapleFamilyBuff;
import handling.world.family.MapleFamilyBuff.MapleFamilyBuffEntry;
import handling.world.family.MapleFamilyCharacter;
import handling.world.guild.MapleGuild;
import handling.world.guild.MapleGuildCharacter;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import provider.MapleData;
import provider.MapleDataProvider;
import provider.MapleDataProviderFactory;
import scripting.EventInstanceManager;
import scripting.NPCConversationManager;
import scripting.NPCScriptManager;
import server.*;
import server.Timer.BuffTimer;
import server.Timer.EtcTimer;
import server.Timer.EventTimer;
import server.Timer.MapTimer;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import server.life.MobSkill;
import server.life.PlayerNPC;
import server.maps.*;
import server.maps.AbstractAnimatedMapleMapObject;
import server.maps.FieldLimitType;
import server.maps.MapleDoor;
import server.maps.MapleMap;
import server.maps.MapleMapFactory;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import server.maps.MapleSummon;
import server.maps.SavedLocationType;
import server.movement.LifeMovementFragment;
import server.quest.MapleQuest;
import server.shops.IMaplePlayerShop;
import tools.ConcurrentEnumMap;
import tools.FileoutputUtil;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;
import tools.MockIOSession;
import tools.Pair;
import tools.data.MaplePacketLittleEndianWriter;
import tools.packet.MTSCSPacket;
import tools.packet.MobPacket;
import tools.packet.MonsterCarnivalPacket;
import tools.packet.PetPacket;
import tools.packet.PlayerShopPacket;
import tools.packet.UIPacket;

public class MapleCharacter extends AbstractAnimatedMapleMapObject implements Serializable {

    private static final long serialVersionUID = 845748950829L;
    private String name, chalktext, BlessOfFairy_Origin, charmessage;
    private long lastCombo, lastfametime, keydown_skill;
    private byte dojoRecord, gmLevel, gender, initialSpawnPoint, skinColor, guildrank = 5, allianceRank = 5, world, fairyExp = 10, fairyExp2 = 10, numClones, subcategory; // Make this a quest record, TODO : Transfer it somehow with the current data
    public short level, mulung_energy, combo, availableCP, totalCP, fame, hpApUsed, job, remainingAp;
    public int accountid, id, meso, exp, hair, face, mapid, bookCover, dojo, prop,
            guildid = 0, fallcounter = 0, maplepoints, acash, chair, itemEffect, points, vpoints,
            rank = 1, rankMove = 0, jobRank = 1, jobRankMove = 0, marriageId, marriageItemId = 0,
            currentrep, totalrep, linkMid = 0, coconutteam = 0, followid = 0, battleshipHP = 0,
            expression, constellation, blood, month, day, beans, beansNum, beansRange, prefix, Sign, Present;
    public int changeChannel;
    public static int ��¼��ǰѪ�� = 0;
    public static int ��ɫ�޵�ָ�� = 0;
    public static int ��ͼ��¼ = 0;
    public static int ���ֹ�ֵ = 0;
    public static long ��¼��Χ = 0;
    public static int ��¼���� = 0;
    public static int ���ܼ��ͷ� = 0;
    public static int ��½��֤ = 0;
    public int �������� = 0;
    public long ����һ�� = 0;
    public long ԽսԽ�� = 0;
    public long prevTimestamp1 = 0;
    public long �����Ի�NPC = 0;
    public long ���������ȴ = 0;
    public long �����ƶ���ȴ = 0;
    public long ��Ҽ�����ȴ = 0;
    public long ����������ȴ = 0;
    public long ��������ҩ = 0;
    public long ����ҩˮ = 0;
    public long ����ҩˮ = 0;
    public long ����������ȴ = 0;
    public long �Ի���ȴ = 0;
    public long ���ּ�� = 0;
    public long ��ŭ֮���Ѫ = 0;
    public long ��ʥ֮���Ѫ = 0;
    private static int _tiredMinutes = 0;
    public int �����˺� = 0;
    public int �������� = 0;
    public long ����˺� = 0;
    public long ��Ҷ���� = 0;
    public long �������� = 0;
    public int ���Թ����ۼ��˺����� = 0;
    public long ���Թ����ۼ��˺� = 0;
    public int ��֮�׼��������� = 0;
    public long ĩ�������˺���¼ = 0;
    public int ʥ���޵� = 0;
    /**
     * <��ֹ����>
     */
    public double ��¼����X = 0;
    public double ��¼����Y = 0;
    public int ����ָ�� = 0;
    public int ��ֵ�ͼ = 0;
    public int ������� = 0;
    /**
     * <�޵�>
     */
    public int ���������� = 0;
    public long �ۼƵ�Ѫ = 0;
    /**
     * <��ֹȫ��>
     */
    public double X������� = 0;
    public double Y������� = 0;
    public int ������ = 0;
    /**
     * <��ֹ�����ͼ>
     */
    public int ��ͼ = 0;
    public int ��ͼ����1 = 0, ��ͼ����2 = 0, ��ͼ����3 = 0, ��ͼ����4 = 0, ��ͼ����5 = 0, ��ͼ����6 = 0, ��ͼ����7 = 0, ��ͼ����8 = 0, ��ͼ����9 = 0, ��ͼ����10 = 0;
    public String ��ͼ����ʱ��1 = "", ��ͼ����ʱ��2 = "", ��ͼ����ʱ��3 = "", ��ͼ����ʱ��4 = "", ��ͼ����ʱ��5 = "", ��ͼ����ʱ��6 = "", ��ͼ����ʱ��7 = "", ��ͼ����ʱ��8 = "", ��ͼ����ʱ��9 = "", ��ͼ����ʱ��10 = "";
    /**
     * <�������ټ��>
     */
    public long �������� = 0;
    public int ���������ж� = 0;
    /**
     * <�������͵�ͼ�������>
     */
    public double ��¼�ƶ�����X1 = 0;
    public double ��¼�ƶ�����Y1 = 0;
    public double ��¼�ƶ�����X2 = 0;
    public double ��¼�ƶ�����Y2 = 0;

    private ScheduledFuture<?> ������ = null;

    public void �����⿪��() {
        if (������ == null) {
            ������ = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    if (��¼�ƶ�����X1 != 0) {
                        ��¼�ƶ�����X2 = getPosition().x;
                    } else {
                        ��¼�ƶ�����X1 = getPosition().x;
                    }
                    if (��¼�ƶ�����Y1 != 0) {
                        ��¼�ƶ�����Y2 = getPosition().y;
                    } else {
                        ��¼�ƶ�����Y1 = getPosition().y;
                    }
                    if (��¼�ƶ�����X1 != 0 && ��¼�ƶ�����X2 != 0) {
                        if (��¼�ƶ�����X1 - ��¼�ƶ�����X2 > 500) {
                            dropMessage(1, "��ʹ�÷Ƿ������ͼ���ѱ����");
                        }
                    }
                    if (��¼�ƶ�����Y1 != 0 && ��¼�ƶ�����Y2 != 0) {
                        if (��¼�ƶ�����Y1 - ��¼�ƶ�����Y2 > 500) {
                            dropMessage(1, "��ʹ�÷Ƿ������ͼ���ѱ����");
                        }
                    }
                    if (��¼�ƶ�����X1 != 0 && ��¼�ƶ�����X2 != 0 && ��¼�ƶ�����Y1 != 0 && ��¼�ƶ�����Y2 != 0) {
                        ��¼�ƶ�����X2 = 0;
                        ��¼�ƶ�����X1 = 0;
                        ��¼�ƶ�����Y1 = 0;
                        ��¼�ƶ�����Y2 = 0;
                    }
                    if (getMapId() != 280020000) {
                        if (������ != null) {
                            ������.cancel(false);
                            ������ = null;
                        }
                    }
                }
            }, 1500);
        }
    }

    public int �����˺�() {
        return �����˺�;
    }

    public int ��������() {
        return ��������;
    }

    public void �����˺�(int a) {
        �����˺� = a;
    }

    public void ��������(int a) {
        �������� = a;
    }
    /**
     * <����ģʽ>
     */
    private ScheduledFuture<?> ����ģʽ�߳� = null;
    private ScheduledFuture<?> ����ģʽBUFF�߳� = null;

    public void �ر�����ģʽ() {
        dropMessage(5, "����ģʽ - �ر�");
        �ر�����ģʽ��Ϣ();
        if (����ģʽ�߳� != null) {
            ����ģʽ�߳�.cancel(false);
            ����ģʽ�߳� = null;
        }
        if (����ģʽBUFF�߳� != null) {
            ����ģʽBUFF�߳�.cancel(false);
            ����ģʽBUFF�߳� = null;
        }
        �����߳�();
    }

    public void ��������ģʽ��Ϣ() {
        PreparedStatement ps1 = null;
        ResultSet rs = null;

        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM jiezoudashi ");
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString2 = null;
                sqlString2 = "update jiezoudashi set Val= 0 where Name = '����ģʽ" + id + "'";
                PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                dropperid.executeUpdate(sqlString2);
                ��ȡ��������Ϣ����();
            }

        } catch (SQLException ex) {
        }
    }

    public void �ر�����ģʽ��Ϣ() {
        PreparedStatement ps1 = null;
        ResultSet rs = null;

        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM jiezoudashi ");
            rs = ps1.executeQuery();
            if (rs.next()) {
                String sqlString2 = null;
                sqlString2 = "update jiezoudashi set Val= 1 where Name = '����ģʽ" + id + "'";
                PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                dropperid.executeUpdate(sqlString2);
                ��ȡ��������Ϣ����();
            }

        } catch (SQLException ex) {
        }
    }

    public void ����ģʽ() {
        �����߳�();
        if (����ģʽ�߳� == null) {
            ��������ģʽ��Ϣ();
            ����ģʽBUFF();
            dropMessage(5, "����ģʽ - ����");
            int ���� = (gui.Start.������Ϣ����.get("BUFF����" + id + ""));
            int a = 0;
            if (���� > 50 && ���� <= 100) {
                /**
                 * <����>
                 */
                ����ģʽ�߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                    @Override
                    public void run() {
                        if (getMp() > 0) {
                            addMP((int) (-stats.getMaxMp() * 0.15));
                        } else {
                            �ر�����ģʽ();
                        }
                        addHP((int) (-stats.getMaxHp() * 0.15));
                    }
                }, 1000 * 5);
            } else if (���� > 100 && ���� <= 150) {
                /**
                 * <�м�>
                 */
                ����ģʽ�߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                    @Override
                    public void run() {
                        if (getMp() > 0) {
                            addMP((int) (-stats.getMaxMp() * 0.15));
                        } else {
                            �ر�����ģʽ();
                        }
                        addHP((int) (-stats.getMaxHp() * 0.15));
                    }
                }, 1000 * 4);
            } else if (���� > 150) {
                /**
                 * <����>
                 */
                ����ģʽ�߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                    @Override
                    public void run() {
                        if (getMp() > 0) {
                            addMP((int) (-stats.getMaxMp() * 0.20));
                        } else {
                            �ر�����ģʽ();
                        }
                        addHP((int) (-stats.getMaxHp() * 0.20));
                    }
                }, 1000 * 4);
            } else {
                /**
                 * <����>
                 */
                ����ģʽ�߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                    @Override
                    public void run() {
                        if (getMp() > 0) {
                            addMP((int) (-stats.getMaxMp() * 0.1));
                        } else {
                            �ر�����ģʽ();
                        }
                        addHP((int) (-stats.getMaxHp() * 0.1));
                    }
                }, 1000 * 5);
            }

        } else {
            ����ģʽ�߳�.cancel(false);
            ����ģʽ�߳� = null;
        }
    }

    public void ����ģʽBUFF() {
        if (����ģʽBUFF�߳� == null) {
            int ��� = (int) (gui.Start.������Ϣ����.get("�������" + id + "") * gui.Start.������Ϣ����.get("BUFF����" + id + "") * 0.0001);
            if (��� > 10000) {
                ��� = 10000;
            }
            ����ģʽBUFF�߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    int ֵ = gui.Start.������Ϣ����.get("BUFF����" + id + "");
                    int buff = 2022359;
                    if (ֵ < 90) {
                        double a = Math.ceil(Math.random() * 8);
                        buff += a;
                    } else if (ֵ >= 90 && ֵ < 180) {
                        double a = Math.ceil(Math.random() * 17);
                        buff += a;
                    } else if (ֵ >= 180 && ֵ < 270) {
                        double a = Math.ceil(Math.random() * 26);
                        buff += a;
                    } else if (ֵ >= 270 && ֵ < 360) {
                        double a = Math.ceil(Math.random() * 35);
                        buff += a;
                    } else if (ֵ >= 360 && ֵ < 450) {
                        double a = Math.ceil(Math.random() * 44);
                        buff += a;
                    } else if (ֵ >= 450 && ֵ < 540) {
                        double a = Math.ceil(Math.random() * 53);
                        buff += a;
                    } else if (ֵ >= 540) {
                        double a = Math.ceil(Math.random() * 62);
                        buff += a;
                    }
                    MapleItemInformationProvider.getInstance().getItemEffect(buff).applyTo(client.getPlayer());
                }
            }, 20000 - ���);
        } else {
            ����ģʽBUFF�߳�.cancel(false);
            ����ģʽBUFF�߳� = null;
        }
    }
    private ScheduledFuture<?> �����������߳� = null;
    public int ���� = 0;

    public void ��������������() {
        �����߳�();
        if (getExp() < level * 10000) {
            dropMessage(1, "���鲻�㹻����");
            return;
        }
        if (ħ���������߳� != null) {
            dropMessage(1, "�޷�ͬʱ����");
            return;
        }
        if (����(getMapId())) {
            if (�����������߳� == null) {
                dropMessage(1, "������������������");
                �����������߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                    @Override
                    public void run() {
                        if (���� > 0) {
                            if (����(getMapId())) {
                                if (getExp() > level * 10000) {
                                    gainExp(-level * 10000, true, true, false);
                                    ��������("����������" + id);
                                    dropMessage(5, "���������� + 1");
                                } else {
                                    �����������߳�.cancel(false);
                                    �����������߳� = null;
                                    ���� = 0;
                                    dropMessage(1, "���������������ر�,���鲻��");
                                }
                            } else {
                                �����������߳�.cancel(false);
                                �����������߳� = null;
                                ���� = 0;
                                dropMessage(1, "��ȫ���ڲſ���������");
                            }
                        } else {
                            ����++;
                        }
                    }
                }, 1000 * 30);
            } else {
                �����������߳�.cancel(false);
                �����������߳� = null;
                ���� = 0;
                dropMessage(1, "���������������ر�");
            }
        } else {
            �����������߳�.cancel(false);
            �����������߳� = null;
            ���� = 0;
            dropMessage(1, "��ȫ���ڲſ���������");
        }
    }

    private ScheduledFuture<?> ħ���������߳� = null;

    public void ����ħ��������() {
        �����߳�();
        if (getExp() < level * 10000) {
            dropMessage(1, "���鲻�㹻����");
            return;
        }
        if (�����������߳� != null) {
            dropMessage(1, "�޷�ͬʱ����");
            return;
        }
        if (����(getMapId())) {
            if (ħ���������߳� == null) {
                dropMessage(1, "����ħ������������");
                ħ���������߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                    @Override
                    public void run() {
                        if (���� > 0) {
                            if (����(getMapId())) {
                                if (getExp() > level * 10000) {
                                    gainExp(-level * 10000, true, true, false);
                                    ��������("ħ��������" + id);
                                    dropMessage(5, "ħ�������� + 1");
                                } else {
                                    �����������߳�.cancel(false);
                                    �����������߳� = null;
                                    ���� = 0;
                                    dropMessage(1, "����ħ���������ر�,���鲻��");
                                }
                            } else {
                                �����������߳�.cancel(false);
                                �����������߳� = null;
                                ���� = 0;
                                dropMessage(1, "��ȫ���ڲſ���������");
                            }
                        } else {
                            ����++;
                        }
                    }
                }, 1000 * 30);
            } else {
                ħ���������߳�.cancel(false);
                ħ���������߳� = null;
                ���� = 0;
                dropMessage(1, "����ħ���������ر�");
            }
        } else {
            �����������߳�.cancel(false);
            �����������߳� = null;
            ���� = 0;
            dropMessage(1, "��ȫ���ڲſ���������");
        }
    }

    private ScheduledFuture<?> Ӳ��Ƥ���߳� = null;

    public void ����Ӳ��Ƥ��() {
        �����߳�();
        if (getExp() < level * 10000) {
            dropMessage(1, "���鲻�㹻����");
            return;
        }
        if (�����������߳� != null) {
            dropMessage(1, "�޷�ͬʱ����");
            return;
        }
        if (ħ���������߳� != null) {
            dropMessage(1, "�޷�ͬʱ����");
            return;
        }
        if (����(getMapId())) {
            if (Ӳ��Ƥ���߳� == null) {
                dropMessage(1, "����Ӳ��Ƥ������");
                Ӳ��Ƥ���߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                    @Override
                    public void run() {
                        if (���� > 0) {
                            if (����(getMapId())) {
                                if (getHp() > stats.getMaxHp() * 0.3) {
                                    if (getExp() > level * 30000) {
                                        gainExp(-level * 30000, true, true, false);
                                        ��������("Ӳ��Ƥ��" + id);
                                        dropMessage(5, "Ӳ��Ƥ�� + 1");
                                        addHP((int) (-stats.getMaxHp() * 0.3));
                                    } else {
                                        Ӳ��Ƥ���߳�.cancel(false);
                                        Ӳ��Ƥ���߳� = null;
                                        ���� = 0;
                                        dropMessage(1, "����Ӳ��Ƥ���ر�,���鲻��");
                                    }
                                } else {
                                    Ӳ��Ƥ���߳�.cancel(false);
                                    Ӳ��Ƥ���߳� = null;
                                    ���� = 0;
                                    dropMessage(1, "״̬��������");
                                }
                            } else {
                                Ӳ��Ƥ���߳�.cancel(false);
                                Ӳ��Ƥ���߳� = null;
                                ���� = 0;
                                dropMessage(1, "��ȫ���ڲſ���������");
                            }
                        } else {
                            ����++;
                        }
                    }
                }, 1000 * 60);
            } else {
                Ӳ��Ƥ���߳�.cancel(false);
                Ӳ��Ƥ���߳� = null;
                ���� = 0;
                dropMessage(1, "����Ӳ��Ƥ���ر�");
            }
        } else {
            Ӳ��Ƥ���߳�.cancel(false);
            Ӳ��Ƥ���߳� = null;
            ���� = 0;
            dropMessage(1, "��ȫ���ڲſ���������");
        }
    }

    private ScheduledFuture<?> ����BUFF�����߳� = null;

    public void ����BUFF����() {
        �����߳�();
        if (getExp() < level * 15000) {
            dropMessage(1, "���鲻�㹻����");
            return;
        }
        if (ħ���������߳� != null) {
            dropMessage(1, "�޷�ͬʱ����");
            return;
        }
        if (�����������߳� != null) {
            dropMessage(1, "�޷�ͬʱ����");
            return;
        }
        if (����(getMapId())) {
            if (����BUFF�����߳� == null) {
                dropMessage(1, "��������������");
                ����BUFF�����߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                    @Override
                    public void run() {
                        if (���� > 0) {
                            if (����(getMapId())) {
                                if (getMp() > stats.getMaxMp() * 0.05 && getHp() > stats.getMaxHp() * 0.05) {
                                    int �̶� = �ж�����("BUFF����" + id);
                                    /**
                                     * <50,��Ҫ �ȼ� * 15000 �ɹ��� 50>
                                     */
                                    if (�̶� <= 50) {
                                        int ���� = 15000;
                                        int �ɹ� = 50;
                                        if (getExp() > level * ����) {
                                            double ���� = Math.ceil(Math.random() * 100);
                                            addMP((int) (-stats.getMaxMp() * 0.05));
                                            addHP((int) (-stats.getMaxHp() * 0.05));
                                            gainExp(-level * ����, true, true, false);
                                            if (���� <= �ɹ�) {
                                                ��������("BUFF����" + id);
                                                dropMessage(5, "������ + 1");
                                            } else {
                                                dropMessage(5, "����������ʧ��");
                                            }
                                        } else {
                                            ����BUFF�����߳�.cancel(false);
                                            ����BUFF�����߳� = null;
                                            ���� = 0;
                                            dropMessage(1, "�����������ر�,���鲻��");
                                        }
                                        /**
                                         * <51-100,��Ҫ �ȼ� * 30000 �ɹ��� 40 ��Ҫ����ֵ����ʯͷ 200>
                                         */
                                    } else if (�̶� > 50 && �̶� <= 100) {
                                        int ���� = 30000;
                                        int �ɹ� = 40;
                                        if (haveItem(4031216, 200)) {
                                            if (getExp() > level * ����) {
                                                double ���� = Math.ceil(Math.random() * 100);
                                                addMP((int) (-stats.getMaxMp() * 0.05));
                                                addHP((int) (-stats.getMaxHp() * 0.05));
                                                gainExp(-level * ����, true, true, false);
                                                MapleInventoryManipulator.removeById(client, GameConstants.getInventoryType(4031216), 4031216, 200, true, false);
                                                if (���� <= �ɹ�) {
                                                    ��������("BUFF����" + id);
                                                    dropMessage(5, "������ + 1");
                                                } else {
                                                    dropMessage(5, "����������ʧ��");
                                                }
                                            } else {
                                                ����BUFF�����߳�.cancel(false);
                                                ����BUFF�����߳� = null;
                                                ���� = 0;
                                                dropMessage(1, "�����������ر�,���鲻��");
                                            }
                                        } else {
                                            ����BUFF�����߳�.cancel(false);
                                            ����BUFF�����߳� = null;
                                            ���� = 0;
                                            dropMessage(1, "�����������ر�\r\n��Ҫ��\r\n����ֵ����ʯ*200");
                                        }
                                        /**
                                         * <101-150,��Ҫ �ȼ� * 40000 �ɹ��� 30 ��Ҫ����ֵ����ʯͷ 200 �ڰ�ˮ�� 1>
                                         */
                                    } else if (�̶� > 101 && �̶� <= 150) {
                                        int ���� = 60000;
                                        int �ɹ� = 30;
                                        if (haveItem(4031216, 200) && haveItem(4005004, 1)) {
                                            if (getExp() > level * ����) {
                                                double ���� = Math.ceil(Math.random() * 100);
                                                addMP((int) (-stats.getMaxMp() * 0.1));
                                                addHP((int) (-stats.getMaxHp() * 0.1));
                                                gainExp(-level * ����, true, true, false);
                                                MapleInventoryManipulator.removeById(client, GameConstants.getInventoryType(4031216), 4031216, 200, true, false);
                                                MapleInventoryManipulator.removeById(client, GameConstants.getInventoryType(4005004), 4005004, 1, true, false);
                                                if (���� <= �ɹ�) {
                                                    ��������("BUFF����" + id);
                                                    dropMessage(5, "������ + 1");
                                                } else {
                                                    dropMessage(5, "����������ʧ��");
                                                }
                                            } else {
                                                ����BUFF�����߳�.cancel(false);
                                                ����BUFF�����߳� = null;
                                                ���� = 0;
                                                dropMessage(1, "�����������ر�,���鲻��");
                                            }
                                        } else {
                                            ����BUFF�����߳�.cancel(false);
                                            ����BUFF�����߳� = null;
                                            ���� = 0;
                                            dropMessage(1, "�����������ر�\r\n��Ҫ��\r\n����ֵ����ʯ*200\r\n�ڰ�ˮ��*1");
                                        }
                                        /**
                                         * <101-150,��Ҫ �ȼ� * 40000 �ɹ��� 30  �ڰ�ˮ�� 1 ��άĸ�� 1>
                                         */
                                    } else if (�̶� > 151 && �̶� <= 200) {
                                        int ���� = 100000;
                                        int �ɹ� = 30;
                                        if (haveItem(4005004, 1) && haveItem(4005000, 5) && haveItem(4005001, 5) && haveItem(4005002, 5) && haveItem(4005003, 5)) {
                                            if (getExp() > level * ����) {
                                                double ���� = Math.ceil(Math.random() * 100);
                                                addMP((int) (-stats.getMaxMp() * 0.1));
                                                addHP((int) (-stats.getMaxHp() * 0.1));
                                                gainExp(-level * ����, true, true, false);
                                                MapleInventoryManipulator.removeById(client, GameConstants.getInventoryType(4005000), 4005000, 5, true, false);
                                                MapleInventoryManipulator.removeById(client, GameConstants.getInventoryType(4005001), 4005001, 5, true, false);
                                                MapleInventoryManipulator.removeById(client, GameConstants.getInventoryType(4005002), 4005002, 5, true, false);
                                                MapleInventoryManipulator.removeById(client, GameConstants.getInventoryType(4005003), 4005003, 5, true, false);
                                                MapleInventoryManipulator.removeById(client, GameConstants.getInventoryType(4005004), 4005004, 1, true, false);
                                                if (���� <= �ɹ�) {
                                                    ��������("BUFF����" + id);
                                                    dropMessage(5, "������ + 1");
                                                } else {
                                                    dropMessage(5, "����������ʧ��");
                                                }
                                            } else {
                                                ����BUFF�����߳�.cancel(false);
                                                ����BUFF�����߳� = null;
                                                ���� = 0;
                                                dropMessage(1, "�����������ر�,���鲻��");
                                            }
                                        } else {
                                            ����BUFF�����߳�.cancel(false);
                                            ����BUFF�����߳� = null;
                                            ���� = 0;
                                            dropMessage(1, "�����������ر�\r\n��Ҫ��\r\n�ڰ�ˮ��*1\r\n����ˮ��*5\r\n����ˮ��*5\r\n�ǻ�ˮ��*5\r\n����ˮ��*5");
                                        }
                                    } else {
                                        ����BUFF�����߳�.cancel(false);
                                        ����BUFF�����߳� = null;
                                        ���� = 0;
                                        dropMessage(1, "��δ��ͨ����������Ϊ");
                                    }
                                } else {
                                    ����BUFF�����߳�.cancel(false);
                                    ����BUFF�����߳� = null;
                                    ���� = 0;
                                    dropMessage(1, "�����������ر�,״̬������");
                                }
                            } else {
                                ����BUFF�����߳�.cancel(false);
                                ����BUFF�����߳� = null;
                                ���� = 0;
                                dropMessage(1, "��ȫ���ڲſ���������");
                            }
                        } else {
                            ����++;
                        }
                    }
                }, 1000 * 120);
            } else {
                ����BUFF�����߳�.cancel(false);
                ����BUFF�����߳� = null;
                ���� = 0;
                dropMessage(1, "�����������ر�");
            }
        } else {
            �����������߳�.cancel(false);
            �����������߳� = null;
            ���� = 0;
            dropMessage(1, "��ȫ���ڲſ���������");
        }
    }

    public void ��ŭ֮���Ѫ() {
        long nowTimestamp = System.currentTimeMillis();
        if (nowTimestamp - ��ŭ֮���Ѫ > 1000 * 3) {
            addHP(-stats.getMaxHp() * 20 / 100);
            ��ŭ֮���Ѫ = nowTimestamp;
        }
    }

    public void ��ʥ֮���Ѫ() {
        long nowTimestamp = System.currentTimeMillis();
        if (nowTimestamp - ��ʥ֮���Ѫ > 1000 * (60 - getEquippedFuMoMap().get(42))) {
            addHP(stats.getMaxHp());
            ��ʥ֮���Ѫ = nowTimestamp;
        }
    }

    public void ����() {
        getClient().disconnect(false, false, true);
        getClient().getSession().close();
    }

    public void �Ի���ȴ() {
        long nowTimestamp = System.currentTimeMillis();
        if (nowTimestamp - �Ի���ȴ > 1000 * 5) {

            dropMessage(5, "�Ի��ٶȹ���");
            �Ի���ȴ = nowTimestamp;
        }
        return;
    }

    public void aaa() {
        long nowTimestamp = System.currentTimeMillis();
        if (nowTimestamp - prevTimestamp1 > 1000 * 5) {
            if (hasEquipped(1002419) && hasEquipped(1032035) && hasEquipped(1102071)) {//��Ҷ��Ǯ��װ
                gainMeso(20, true, false, false);
                prevTimestamp1 = nowTimestamp;
            }
        }
    }

    public void ��������ӳ�() {
        int ���� = getFame() * gui.Start.ConfigValuesMap.get("��������ӳ�");
        gainExp(����, true, false, false);
    }

    public void ��ħ����ӳ�() {
        int ���� = getEquippedFuMoMap().get(31);
        gainExp(exp * ����, true, false, false);
    }

    public int getSign() {
        return Sign;
    }

    public void setSign(int Sign) {
        this.Sign = Sign;
    }

    public void gainSign(int Sign) {
        this.Sign += Sign;
    }
    private boolean canSetBeansNum;
    private Point old = new Point(0, 0);
    private boolean smega, hidden, hasSummon = false;
    private int[] wishlist, rocks, savedLocations, regrocks, remainingSp = new int[10];
    private transient AtomicInteger inst;
    private transient List<LifeMovementFragment> lastres;
    private List<Integer> lastmonthfameids;
    private List<MapleDoor> doors;
    private List<MaplePet> pets;
    private transient WeakReference<MapleCharacter>[] clones;
    private transient Set<MapleMonster> controlled;
    private transient Set<MapleMapObject> visibleMapObjects;
    private transient ReentrantReadWriteLock visibleMapObjectsLock;
    private Map<MapleQuest, MapleQuestStatus> quests;
    private Map<Integer, String> questinfo;
    private Map<ISkill, SkillEntry> skills = new LinkedHashMap<ISkill, SkillEntry>();
    private transient Map<MapleBuffStat, MapleBuffStatValueHolder> effects = new ConcurrentEnumMap<MapleBuffStat, MapleBuffStatValueHolder>(MapleBuffStat.class);
    private transient Map<Integer, MapleSummon> summons;
    private transient Map<Integer, MapleCoolDownValueHolder> coolDowns = new LinkedHashMap<Integer, MapleCoolDownValueHolder>();
    private transient Map<MapleDisease, MapleDiseaseValueHolder> diseases = new ConcurrentEnumMap<MapleDisease, MapleDiseaseValueHolder>(MapleDisease.class);
    private CashShop cs;
    private transient Deque<MapleCarnivalChallenge> pendingCarnivalRequests;
    private transient MapleCarnivalParty carnivalParty;
    private BuddyList buddylist;
    private MonsterBook monsterbook;
    private transient CheatTracker anticheat;
    private MapleClient client;
    private PlayerStats stats;
    private transient PlayerRandomStream CRand;
    private transient MapleMap map;
    private transient MapleShop shop;
    private transient MapleDragon dragon;
    private transient RockPaperScissors rps;
    private MapleStorage storage;
    private transient MapleTrade trade;
    private MapleMount mount;
    private List<Integer> finishedAchievements = new ArrayList<Integer>();
    private MapleMessenger messenger;
    private byte[] petStore;
    private transient IMaplePlayerShop playerShop;
    private transient IMaplePlayerShop playerShop2;
    private MapleParty party;
    private boolean invincible = false, canTalk = true, clone = false, followinitiator = false, followon = false;
    private MapleGuildCharacter mgc;
    private MapleFamilyCharacter mfc;
    private transient EventInstanceManager eventInstance;
    private MapleInventory[] inventory;
    private SkillMacro[] skillMacros = new SkillMacro[5];
    private MapleKeyLayout keylayout;
    private transient ScheduledFuture<?> beholderHealingSchedule, beholderBuffSchedule, BerserkSchedule,
            dragonBloodSchedule, dragonBlood1Schedule, fairySchedule, mapTimeLimitTask, fishing;
    private long nextConsume = 0, pqStartTime = 0;
    private transient Event_PyramidSubway pyramidSubway = null;
    private transient List<Integer> pendingExpiration = null, pendingSkills = null;
    private transient Map<Integer, Integer> movedMobs = new HashMap<Integer, Integer>();
    private String teleportname = "";
    private int APQScore;
    //public String ��һ�仰 = "�Ұ�ð�յ�";
    private long lasttime = 0L;
    private long currenttime = 0L;
    private long deadtime = 1000L;
    private MapleCharacter chars;
    private long nengl = 0;
    private long nengls = 0;
    private static String[] ariantroomleader = new String[3]; // AriantPQ
    private static int[] ariantroomslot = new int[3]; // AriantPQ
    public int apprentice = 0, master = 0; // apprentice ID for master
    public boolean DebugMessage = false;
    public int ariantScore = 0;
    public long lastGainHM;
    public Boolean isCheating = false;
    private int _isCheatingPlayer = 0;
    public int _tiredPoints = 0;

    private MapleCharacter(final boolean ChannelServer) {
        setStance(0);
        setPosition(new Point(0, 0));

        inventory = new MapleInventory[MapleInventoryType.values().length];
        for (MapleInventoryType type : MapleInventoryType.values()) {
            inventory[type.ordinal()] = new MapleInventory(type, (byte) 100);
        }
        quests = new LinkedHashMap<MapleQuest, MapleQuestStatus>(); // Stupid erev quest.
        stats = new PlayerStats(this);
        for (int i = 0; i < remainingSp.length; i++) {
            remainingSp[i] = 0;
        }
        if (ChannelServer) {
            lastMoveItemTime = 0;
            lastCheckPeriodTime = 0;
            lastQuestTime = 0;
            lastHPTime = 0;
            lastMPTime = 0;
            lastCombo = 0;
            mulung_energy = 0;
            combo = 0;
            keydown_skill = 0;
            smega = true;
            petStore = new byte[3];
            for (int i = 0; i < petStore.length; i++) {
                petStore[i] = (byte) -1;
            }
            wishlist = new int[10];
            rocks = new int[10];
            regrocks = new int[5];
            clones = new WeakReference[25];
            for (int i = 0; i < clones.length; i++) {
                clones[i] = new WeakReference<MapleCharacter>(null);
            }
            inst = new AtomicInteger();
            inst.set(0); // 1 = NPC/ Quest, 2 = Duey, 3 = Hired Merch store, 4 = Storage
            keylayout = new MapleKeyLayout();
            doors = new ArrayList<MapleDoor>();
            controlled = new LinkedHashSet<MapleMonster>();
            summons = new LinkedHashMap<Integer, MapleSummon>();
            visibleMapObjects = new LinkedHashSet<MapleMapObject>();
            visibleMapObjectsLock = new ReentrantReadWriteLock();
            pendingCarnivalRequests = new LinkedList<MapleCarnivalChallenge>();

            savedLocations = new int[SavedLocationType.values().length];
            for (int i = 0; i < SavedLocationType.values().length; i++) {
                savedLocations[i] = -1;
            }
            questinfo = new LinkedHashMap<Integer, String>();
            anticheat = new CheatTracker(this);
            pets = new ArrayList<MaplePet>();
        }
    }

    public static MapleCharacter getDefault(final MapleClient client, final int type) {
        MapleCharacter ret = new MapleCharacter(false);
        ret.client = client;
        ret.map = null;
        ret.exp = 0;
        ret.gmLevel = 0;
        ret.job = (short) (type == 1 ? 0 : (type == 0 ? 1000 : (type == 3 ? 2001 : (type == 4 ? 3000 : 2000))));
        ret.beans = 0;
        ret.meso = 0;
        ret.level = 1;
        ret.remainingAp = 0;
        ret.fame = 0;
        ret.accountid = client.getAccID();
        ret.buddylist = new BuddyList((byte) 20);

        ret.stats.str = 12;
        ret.stats.dex = 5;
        ret.stats.int_ = 4;
        ret.stats.luk = 4;
        ret.stats.maxhp = 50;
        ret.stats.hp = 50;
        ret.stats.maxmp = 50;
        ret.stats.mp = 50;
        ret.prefix = 0;

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * FROM accounts WHERE id = ?");
            ps.setInt(1, ret.accountid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ret.client.setAccountName(rs.getString("name"));
                //ret.client.setAccountqq(rs.getString("qq"));
                ret.acash = rs.getInt("ACash");
                ret.maplepoints = rs.getInt("mPoints");
                ret.points = rs.getInt("points");
                ret.vpoints = rs.getInt("vpoints");
                ret.lastGainHM = rs.getLong("lastGainHM");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error getting character default" + e);
        }
        return ret;
    }

    public final static MapleCharacter ReconstructChr(final CharacterTransfer ct, final MapleClient client, final boolean isChannel) {
        final MapleCharacter ret = new MapleCharacter(true); // Always true, it's change channel
        ret.client = client;
        if (!isChannel) {
            ret.client.setChannel(ct.channel);
        }

        ret.mount_id = ct.mount_id;
        ret.DebugMessage = ct.DebugMessage;
        ret.id = ct.characterid;
        ret.name = ct.name;
        ret.level = ct.level;
        ret.fame = ct.fame;

        ret.CRand = new PlayerRandomStream();

        ret.stats.str = ct.str;
        ret.stats.dex = ct.dex;
        ret.stats.int_ = ct.int_;
        ret.stats.luk = ct.luk;
        ret.stats.maxhp = ct.maxhp;
        ret.stats.maxmp = ct.maxmp;
        ret.stats.hp = ct.hp;
        ret.stats.mp = ct.mp;

        ret.chalktext = ct.chalkboard;
        ret.exp = ct.exp;
        ret.hpApUsed = ct.hpApUsed;
        ret.remainingSp = ct.remainingSp;
        ret.remainingAp = ct.remainingAp;
        ret.beans = ct.beans;
        ret.meso = ct.meso;
        ret.gmLevel = ct.gmLevel;
        ret.skinColor = ct.skinColor;
        ret.gender = ct.gender;
        ret.job = ct.job;
        ret.hair = ct.hair;
        ret.face = ct.face;
        ret.accountid = ct.accountid;
        ret.mapid = ct.mapid;
        ret.initialSpawnPoint = ct.initialSpawnPoint;
        ret.world = ct.world;
        ret.bookCover = ct.mBookCover;
        ret.dojo = ct.dojo;
        ret.dojoRecord = ct.dojoRecord;
        ret.guildid = ct.guildid;
        ret.guildrank = ct.guildrank;
        ret.allianceRank = ct.alliancerank;
        ret.points = ct.points;
        ret.vpoints = ct.vpoints;
        ret.fairyExp = ct.fairyExp;
        ret.marriageId = ct.marriageId;
        ret.currentrep = ct.currentrep;
        ret.totalrep = ct.totalrep;
        ret.charmessage = ct.charmessage;
        ret.expression = ct.expression;
        ret.constellation = ct.constellation;
        ret.blood = ct.blood;
        ret.month = ct.month;
        ret.day = ct.day;
        ret.makeMFC(ct.familyid, ct.seniorid, ct.junior1, ct.junior2);
        if (ret.guildid > 0) {
            ret.mgc = new MapleGuildCharacter(ret);
        }
        ret.buddylist = new BuddyList(ct.buddysize);
        ret.subcategory = ct.subcategory;
        ret.prefix = ct.prefix;

        if (isChannel) {
            final MapleMapFactory mapFactory = ChannelServer.getInstance(client.getChannel()).getMapFactory();
            ret.map = mapFactory.getMap(ret.mapid);
            if (ret.map == null) { //char is on a map that doesn't exist warp it to henesys
                ret.map = mapFactory.getMap(100000000);
            } else if (ret.map.getForcedReturnId() != 999999999) {
                ret.map = ret.map.getForcedReturnMap();
            }
            MaplePortal portal = ret.map.getPortal(ret.initialSpawnPoint);
            if (portal == null) {
                portal = ret.map.getPortal(0); // char is on a spawnpoint that doesn't exist - select the first spawnpoint instead
                ret.initialSpawnPoint = 0;
            }
            ret.setPosition(portal.getPosition());

            final int messengerid = ct.messengerid;
            if (messengerid > 0) {
                ret.messenger = World.Messenger.getMessenger(messengerid);
            }
        } else {

            ret.messenger = null;
        }
        int partyid = ct.partyid;
        if (partyid >= 0) {
            MapleParty party = World.Party.getParty(partyid);
            if (party != null && party.getMemberById(ret.id) != null) {
                ret.party = party;
            }
        }

        MapleQuestStatus queststatus;
        MapleQuestStatus queststatus_from;
        MapleQuest quest;
        for (final Map.Entry<Integer, Object> qs : ct.Quest.entrySet()) {
            quest = MapleQuest.getInstance(qs.getKey());
            queststatus_from = (MapleQuestStatus) qs.getValue();

            queststatus = new MapleQuestStatus(quest, queststatus_from.getStatus());
            queststatus.setForfeited(queststatus_from.getForfeited());
            queststatus.setCustomData(queststatus_from.getCustomData());
            queststatus.setCompletionTime(queststatus_from.getCompletionTime());

            if (queststatus_from.getMobKills() != null) {
                for (final Map.Entry<Integer, Integer> mobkills : queststatus_from.getMobKills().entrySet()) {
                    queststatus.setMobKills(mobkills.getKey(), mobkills.getValue());
                }
            }
            ret.quests.put(quest, queststatus);
        }
        for (final Map.Entry<Integer, SkillEntry> qs : ct.Skills.entrySet()) {
            ret.skills.put(SkillFactory.getSkill(qs.getKey()), qs.getValue());
        }
        for (final Integer zz : ct.finishedAchievements) {
            ret.finishedAchievements.add(zz);
        }
        ret.monsterbook = new MonsterBook(ct.mbook);
        ret.inventory = (MapleInventory[]) ct.inventorys;
        ret.BlessOfFairy_Origin = ct.BlessOfFairy;
        ret.skillMacros = (SkillMacro[]) ct.skillmacro;
        ret.petStore = ct.petStore;
        ret.keylayout = new MapleKeyLayout(ct.keymap);
        ret.questinfo = ct.InfoQuest;
        ret.savedLocations = ct.savedlocation;
        ret.wishlist = ct.wishlist;
        ret.rocks = ct.rocks;
        ret.regrocks = ct.regrocks;
        ret.buddylist.loadFromTransfer(ct.buddies);
        // ret.lastfametime
        // ret.lastmonthfameids
        ret.keydown_skill = 0; // Keydown skill can't be brought over
        ret.lastfametime = ct.lastfametime;
        ret.lastmonthfameids = ct.famedcharacters;
        ret.storage = (MapleStorage) ct.storage;
        ret.cs = (CashShop) ct.cs;
        client.setAccountName(ct.accountname);
        ret.acash = ct.ACash;
        ret.lastGainHM = ct.lastGainHM;
        ret.maplepoints = ct.MaplePoints;
        ret.numClones = ct.clonez;
        ret.mount = new MapleMount(ret, ct.mount_itemid, GameConstants.isKOC(ret.job) ? 10001004 : (GameConstants.isAran(ret.job) ? 20001004 : (GameConstants.isEvan(ret.job) ? 20011004 : 1004)), ct.mount_Fatigue, ct.mount_level, ct.mount_exp);
        ret.stats.recalcLocalStats(true);
        return ret;
    }

    public boolean isTired() {
        if (_tiredMinutes > 24 * 60) {
            return false;
        }
        return _tiredPoints > _tiredMinutes;
    }

    public int �ж�ƣ��ֵ() {
        int ƣ�� = 0;
        if (_tiredPoints >= 60 * gui.Start.ConfigValuesMap.get("ÿ��ƣ��ֵ")) {
            ƣ�� = 60 * gui.Start.ConfigValuesMap.get("ÿ��ƣ��ֵ");
        } else {
            ƣ�� = _tiredPoints;
        }
        return (int) ƣ��;
    }

    public void ���ӽ�ɫƣ��ֵ(int period) {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = null;
        if (period > 0) {
            /**
             * <�������ǣ�����ƣ��>
             */
            if (period > 0 && !����(getMap().getId())) {
                try {
                    ps = con.prepareStatement("UPDATE accounts SET df_tired_point = df_tired_point + ? WHERE id = ?");
                    ps.setInt(1, period);
                    ps.setInt(2, getClient().getAccID());
                    ps.executeUpdate();
                    ps.close();
                    _tiredPoints += period;
                } catch (SQLException Ex) {
                    System.err.println("���½�ɫƣ��ֵ����: " + Ex);
                }
            }
        }
    }
//
//    public void ���ӽ�ɫƣ��ֵ2(int period) {
//        Connection con = DatabaseConnection.getConnection();
//        PreparedStatement ps = null;
//        if (period > 0) {
//            if (period > 0 && ����(getMap().getId())) { // ��ɫ��������ʱ���Ż�����ƣ��ֵ��
//                try {
//                    ps = con.prepareStatement("UPDATE accounts SET df_tired_point = df_tired_point + ? WHERE id = ?");
//                    ps.setInt(1, period);
//                    ps.setInt(2, getClient().getAccID());
//                    ps.executeUpdate();
//                    ps.close();
//                    _tiredPoints += period;
//                } catch (SQLException Ex) {
//                    System.err.println("���½�ɫƣ��ֵ����: " + Ex);
//                }
//            }
//        }
//    }

    public static MapleCharacter loadCharFromDB(int charid, MapleClient client, boolean channelserver) {
        final MapleCharacter ret = new MapleCharacter(channelserver);
        ret.client = client;
        ret.id = charid;

        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = null;
        PreparedStatement pse = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("SELECT * FROM characters WHERE id = ?");
            ps.setInt(1, charid);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new RuntimeException("Loading the Char Failed (char not found)");
            }

            ret.mount_id = rs.getInt("mountid");
            ret.name = rs.getString("name");
            ret.level = rs.getShort("level");
            ret.fame = rs.getShort("fame");

            ret.stats.str = rs.getShort("str");
            ret.stats.dex = rs.getShort("dex");
            ret.stats.int_ = rs.getShort("int");
            ret.stats.luk = rs.getShort("luk");
            ret.stats.maxhp = rs.getShort("maxhp");
            ret.stats.maxmp = rs.getShort("maxmp");
            ret.stats.hp = rs.getShort("hp");
            ret.stats.mp = rs.getShort("mp");

            ret.exp = rs.getInt("exp");
            ret.hpApUsed = rs.getShort("hpApUsed");
            final String[] sp = rs.getString("sp").split(",");
            for (int i = 0; i < ret.remainingSp.length; i++) {
                ret.remainingSp[i] = Integer.parseInt(sp[i]);
            }
            ret.remainingAp = rs.getShort("ap");
            ret.beans = rs.getInt("beans");

            if (rs.getInt("meso") > 2147483647) {
                ret.meso = 2147483647;
            } else {
                ret.meso = rs.getInt("meso");
            }
            ret.gmLevel = rs.getByte("gm");
            ret.skinColor = rs.getByte("skincolor");
            ret.gender = rs.getByte("gender");
            ret.job = rs.getShort("job");
            ret.hair = rs.getInt("hair");
            ret.face = rs.getInt("face");
            ret.accountid = rs.getInt("accountid");
            ret.mapid = rs.getInt("map");
            ret.initialSpawnPoint = rs.getByte("spawnpoint");
            ret.world = rs.getByte("world");
            ret.guildid = rs.getInt("guildid");
            ret.guildrank = rs.getByte("guildrank");
            ret.allianceRank = rs.getByte("allianceRank");
            ret.currentrep = rs.getInt("currentrep");
            ret.totalrep = rs.getInt("totalrep");
            ret.makeMFC(rs.getInt("familyid"), rs.getInt("seniorid"), rs.getInt("junior1"), rs.getInt("junior2"));
            if (ret.guildid > 0) {
                ret.mgc = new MapleGuildCharacter(ret);
            }
            ret.buddylist = new BuddyList(rs.getByte("buddyCapacity"));
            ret.subcategory = rs.getByte("subcategory");
            ret.mount = new MapleMount(ret, 0, ret.job > 1000 && ret.job < 2000 ? 10001004 : (ret.job >= 2000 ? (ret.job == 2001 || (ret.job >= 2200 && ret.job <= 2218) ? 20011004 : (ret.job >= 3000 ? 30001004 : 20001004)) : 1004), (byte) 0, (byte) 1, 0);
            ret.rank = rs.getInt("rank");
            ret.rankMove = rs.getInt("rankMove");
            ret.jobRank = rs.getInt("jobRank");
            ret.jobRankMove = rs.getInt("jobRankMove");
            ret.marriageId = rs.getInt("marriageId");
            ret.charmessage = rs.getString("charmessage");
            ret.expression = rs.getInt("expression");
            ret.constellation = rs.getInt("constellation");
            ret.blood = rs.getInt("blood");
            ret.month = rs.getInt("month");
            ret.day = rs.getInt("day");
            ret.prefix = rs.getInt("prefix");
            /**
             * <�����׹>
             */
            if (rs.getInt("todayOnlineTime") > 180) {
                ret.fairyExp = 30;
            } else {
                ret.fairyExp = (byte) (rs.getInt("todayOnlineTime") / 6);
            }

            if (channelserver) {
                ret.antiMacro = new MapleLieDetector(ret);
                MapleMapFactory mapFactory = ChannelServer.getInstance(client.getChannel()).getMapFactory();//�ֱ�������
                ret.map = mapFactory.getMap(ret.mapid);
                if (ret.map == null) { //char is on a map that doesn't exist warp it to henesys
                    ret.map = mapFactory.getMap(100000000);
                }
                MaplePortal portal = ret.map.getPortal(ret.initialSpawnPoint);
                if (portal == null) {
                    portal = ret.map.getPortal(0); // char is on a spawnpoint that doesn't exist - select the first spawnpoint instead
                    ret.initialSpawnPoint = 0;
                }
                ret.setPosition(portal.getPosition());

                int partyid = rs.getInt("party");
                if (partyid >= 0) {
                    MapleParty party = World.Party.getParty(partyid);
                    if (party != null && party.getMemberById(ret.id) != null) {
                        ret.party = party;
                    }
                }
                ret.bookCover = rs.getInt("monsterbookcover");
                ret.dojo = rs.getInt("dojo_pts");
                ret.dojoRecord = rs.getByte("dojoRecord");
                final String[] pets = rs.getString("pets").split(",");
                for (int i = 0; i < ret.petStore.length; i++) {
                    ret.petStore[i] = Byte.parseByte(pets[i]);
                }
                rs.close();
                ps.close();
                ps = con.prepareStatement("SELECT * FROM achievements WHERE accountid = ?");
                ps.setInt(1, ret.accountid);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ret.finishedAchievements.add(rs.getInt("achievementid"));
                }

            }
            rs.close();
            ps.close();

            boolean compensate_previousEvans = false;
            ps = con.prepareStatement("SELECT * FROM queststatus WHERE characterid = ?");
            ps.setInt(1, charid);
            rs = ps.executeQuery();
            pse = con.prepareStatement("SELECT * FROM queststatusmobs WHERE queststatusid = ?");

            while (rs.next()) {
                final int id = rs.getInt("quest");
                if (id == 170000) {
                    compensate_previousEvans = true;
                }
                final MapleQuest q = MapleQuest.getInstance(id);
                final MapleQuestStatus status = new MapleQuestStatus(q, rs.getByte("status"));
                final long cTime = rs.getLong("time");
                if (cTime > -1) {
                    status.setCompletionTime(cTime * 1000);
                }
                status.setForfeited(rs.getInt("forfeited"));
                status.setCustomData(rs.getString("customData"));
                ret.quests.put(q, status);
                pse.setInt(1, rs.getInt("queststatusid"));
                final ResultSet rsMobs = pse.executeQuery();

                while (rsMobs.next()) {
                    status.setMobKills(rsMobs.getInt("mob"), rsMobs.getInt("count"));
                }
                rsMobs.close();
            }
            rs.close();
            ps.close();
            pse.close();

            if (channelserver) {
                ret.CRand = new PlayerRandomStream();
                ret.monsterbook = MonsterBook.loadCards(charid);

                ps = con.prepareStatement("SELECT * FROM inventoryslot where characterid = ?");
                ps.setInt(1, charid);
                rs = ps.executeQuery();
                if (!rs.next()) {
                    rs.close();
                    ps.close();
                    throw new RuntimeException("No Inventory slot column found in SQL. [inventoryslot]*********************");
                }
                ret.getInventory(MapleInventoryType.EQUIP).setSlotLimit(rs.getByte("equip"));
                ret.getInventory(MapleInventoryType.USE).setSlotLimit(rs.getByte("use"));
                ret.getInventory(MapleInventoryType.SETUP).setSlotLimit(rs.getByte("setup"));
                ret.getInventory(MapleInventoryType.ETC).setSlotLimit(rs.getByte("etc"));
                ret.getInventory(MapleInventoryType.CASH).setSlotLimit(rs.getByte("cash"));
                ps.close();
                rs.close();

                for (Pair<IItem, MapleInventoryType> mit : ItemLoader.INVENTORY.loadItems(false, charid).values()) {
                    ret.getInventory(mit.getRight()).addFromDB(mit.getLeft());
                    if (mit.getLeft().getPet() != null) {
                        ret.pets.add(mit.getLeft().getPet());
                    }
                }

                ps = con.prepareStatement("SELECT * FROM accounts WHERE id = ?");
                ps.setInt(1, ret.accountid);
                rs = ps.executeQuery();
                if (rs.next()) {
                    ret.getClient().setAccountName(rs.getString("name"));
                    ret.lastGainHM = rs.getLong("lastGainHM");
                    ret.acash = rs.getInt("ACash");
                    ret.maplepoints = rs.getInt("mPoints");
                    ret.points = rs.getInt("points");
                    ret.vpoints = rs.getInt("vpoints");
                    ret._tiredPoints = rs.getInt("df_tired_point");
                    ret._isCheatingPlayer = rs.getInt("df_cheating_player");
                    if (rs.getTimestamp("lastlogon") != null) {
                        final Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(rs.getTimestamp("lastlogon").getTime());
                    }
                    rs.close();
                    ps.close();

                    ps = con.prepareStatement("UPDATE accounts SET lastlogon = CURRENT_TIMESTAMP() WHERE id = ?");
                    ps.setInt(1, ret.accountid);
                    ps.executeUpdate();
                } else {
                    rs.close();
                }
                ps.close();

                ps = con.prepareStatement("SELECT * FROM questinfo WHERE characterid = ?");
                ps.setInt(1, charid);
                rs = ps.executeQuery();

                while (rs.next()) {
                    ret.questinfo.put(rs.getInt("quest"), rs.getString("customData"));
                }
                rs.close();
                ps.close();

                ps = con.prepareStatement("SELECT skillid, skilllevel, masterlevel, expiration FROM skills WHERE characterid = ?");
                ps.setInt(1, charid);
                rs = ps.executeQuery();
                ISkill skil;
                while (rs.next()) {
                    skil = SkillFactory.getSkill(rs.getInt("skillid"));
                    if (skil != null && GameConstants.isApplicableSkill(rs.getInt("skillid"))) {
                        ret.skills.put(skil, new SkillEntry(rs.getByte("skilllevel"), rs.getByte("masterlevel"), rs.getLong("expiration")));
                    } else if (skil == null) { //doesnt. exist. e.g. bb
                        ret.remainingSp[GameConstants.getSkillBookForSkill(rs.getInt("skillid"))] += rs.getByte("skilllevel");
                    }
                }
                rs.close();
                ps.close();

                ret.expirationTask(false); //do it now

                // Bless of Fairy handling
                ps = con.prepareStatement("SELECT * FROM characters WHERE accountid = ? ORDER BY level DESC");
                ps.setInt(1, ret.accountid);
                rs = ps.executeQuery();
                byte maxlevel_ = 0;
                while (rs.next()) {
                    if (rs.getInt("id") != charid) { // Not this character
                        byte maxlevel = (byte) (rs.getShort("level") / 10);

                        if (maxlevel > 20) {
                            maxlevel = 20;
                        }
                        if (maxlevel > maxlevel_) {
                            maxlevel_ = maxlevel;
                            ret.BlessOfFairy_Origin = rs.getString("name");
                        }

                    } else if (charid < 17000 && !compensate_previousEvans && ret.job >= 2200 && ret.job <= 2218) { //compensate, watch max charid
                        for (int i = 0; i <= GameConstants.getSkillBook(ret.job); i++) {
                            ret.remainingSp[i] += 2; //2 that they missed. gg
                        }
                        ret.setQuestAdd(MapleQuest.getInstance(170000), (byte) 0, null); //set it so never again
                    }
                }
                ret.skills.put(SkillFactory.getSkill(GameConstants.getBOF_ForJob(ret.job)), new SkillEntry(maxlevel_, (byte) 0, -1));
                ps.close();
                rs.close();
                /**
                 * <���ܺ�>
                 */
                ps = con.prepareStatement("SELECT * FROM skillmacros WHERE characterid = ?");
                ps.setInt(1, charid);
                rs = ps.executeQuery();
                int position;
                while (rs.next()) {
                    position = rs.getInt("position");
                    SkillMacro macro = new SkillMacro(rs.getInt("skill1"), rs.getInt("skill2"), rs.getInt("skill3"), rs.getString("name"), rs.getInt("shout"), position);
                    ret.skillMacros[position] = macro;
                }
                rs.close();
                ps.close();
                /**
                 * <���̿�ݼ�>
                 */
                ps = con.prepareStatement("SELECT `key`,`type`,`action` FROM keymap WHERE characterid = ?");
                ps.setInt(1, charid);
                rs = ps.executeQuery();
                final Map<Integer, Pair<Byte, Integer>> keyb = ret.keylayout.Layout();
                while (rs.next()) {
                    Pair<Byte, Integer> put = keyb.put(Integer.valueOf(rs.getInt("key")), new Pair<Byte, Integer>(rs.getByte("type"), rs.getInt("action")));
                }
                rs.close();
                ps.close();

                ps = con.prepareStatement("SELECT `locationtype`,`map` FROM savedlocations WHERE characterid = ?");
                ps.setInt(1, charid);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ret.savedLocations[rs.getInt("locationtype")] = rs.getInt("map");
                }
                rs.close();
                ps.close();

                ps = con.prepareStatement("SELECT `characterid_to`,`when` FROM famelog WHERE characterid = ? AND DATEDIFF(NOW(),`when`) < 30");
                ps.setInt(1, charid);
                rs = ps.executeQuery();
                ret.lastfametime = 0;
                ret.lastmonthfameids = new ArrayList<Integer>(31);
                while (rs.next()) {
                    ret.lastfametime = Math.max(ret.lastfametime, rs.getTimestamp("when").getTime());
                    ret.lastmonthfameids.add(Integer.valueOf(rs.getInt("characterid_to")));
                }
                rs.close();
                ps.close();

                ret.buddylist.loadFromDb(charid);
                ret.storage = MapleStorage.loadStorage(ret.accountid);
                ret.cs = new CashShop(ret.accountid, charid, ret.getJob());

                ps = con.prepareStatement("SELECT sn FROM wishlist WHERE characterid = ?");
                ps.setInt(1, charid);
                rs = ps.executeQuery();
                int i = 0;
                while (rs.next()) {
                    ret.wishlist[i] = rs.getInt("sn");
                    i++;
                }
                while (i < 10) {
                    ret.wishlist[i] = 0;
                    i++;
                }
                rs.close();
                ps.close();

                ps = con.prepareStatement("SELECT mapid FROM trocklocations WHERE characterid = ?");
                ps.setInt(1, charid);
                rs = ps.executeQuery();
                int r = 0;
                while (rs.next()) {
                    ret.rocks[r] = rs.getInt("mapid");
                    r++;
                }
                while (r < 10) {
                    ret.rocks[r] = 999999999;
                    r++;
                }
                rs.close();
                ps.close();

                ps = con.prepareStatement("SELECT mapid FROM regrocklocations WHERE characterid = ?");
                ps.setInt(1, charid);
                rs = ps.executeQuery();
                r = 0;
                while (rs.next()) {
                    ret.regrocks[r] = rs.getInt("mapid");
                    r++;
                }
                while (r < 5) {
                    ret.regrocks[r] = 999999999;
                    r++;
                }
                rs.close();
                ps.close();

                ps = con.prepareStatement("SELECT * FROM mountdata WHERE characterid = ?");
                ps.setInt(1, charid);
                rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new RuntimeException("No mount data found on SQL column");
                }
                final IItem mount = ret.getInventory(MapleInventoryType.EQUIPPED).getItem((byte) -18);
                ret.mount = new MapleMount(ret, mount != null ? mount.getItemId() : 0, ret.job > 1000 && ret.job < 2000 ? 10001004 : (ret.job >= 2000 ? (ret.job == 2001 || ret.job >= 2200 ? 20011004 : (ret.job >= 3000 ? 30001004 : 20001004)) : 1004), rs.getByte("Fatigue"), rs.getByte("Level"), rs.getInt("Exp"));
                ps.close();
                rs.close();
                ret.stats.recalcLocalStats(true);
            } else {
                for (Pair<IItem, MapleInventoryType> mit : ItemLoader.INVENTORY.loadItems(true, charid).values()) {
                    ret.getInventory(mit.getRight()).addFromDB(mit.getLeft());
                }
            }
        } catch (SQLException ess) {
            System.out.println("���ؽ�ɫ������Ϣ����...");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ignore) {
            }
        }
        return ret;
    }

    public static void saveNewCharToDB(final MapleCharacter chr, final int type, final boolean db) {
        Connection con = DatabaseConnection.getConnection();

        PreparedStatement ps = null;
        PreparedStatement pse = null;
        ResultSet rs = null;
        try {
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            con.setAutoCommit(false);

            ps = con.prepareStatement("INSERT INTO characters (level, fame, str, dex, luk, `int`, exp, hp, mp, maxhp, maxmp, sp, ap, gm, skincolor, gender, job, hair, face, map, meso, hpApUsed, spawnpoint, party, buddyCapacity, monsterbookcover, dojo_pts, dojoRecord, pets, subcategory, marriageId, currentrep, totalrep, prefix, accountid, name, world, mountid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, 1); // Level
            ps.setShort(2, (short) 0); // Fame
            final PlayerStats stat = chr.stats;
            ps.setShort(3, stat.getStr()); // Str
            ps.setShort(4, stat.getDex()); // Dex
            ps.setShort(5, stat.getInt()); // Int
            ps.setShort(6, stat.getLuk()); // Luk
            ps.setInt(7, 0); // EXP
            ps.setShort(8, stat.getHp()); // HP
            ps.setShort(9, stat.getMp());
            ps.setShort(10, stat.getMaxHp()); // MP
            ps.setShort(11, stat.getMaxMp());
            ps.setString(12, "0,0,0,0,0,0,0,0,0,0"); // Remaining SP
            ps.setShort(13, (short) 0); // Remaining AP
            ps.setInt(14, chr.getClient().gm ? 5 : 0); // GM Level
            ps.setByte(15, chr.skinColor);
            ps.setByte(16, chr.gender);
            ps.setShort(17, chr.job);
            ps.setInt(18, chr.hair);
            ps.setInt(19, chr.face);
            //1=ð�ռ�0
            //2=ս��
            //3=��ʿ��130030000
            ps.setInt(20, type == 1 ? 0 : (type == 0 ? 130030000 : (type == 2 ? 914000000 : 910000000)));//�����أ�130030000//140000000

            // ps.setInt(20, type == 1 ? Integer.parseInt(ServerProperties.getProperty("ZEV.mxjcsd")) : (type == 0 ? Integer.parseInt(ServerProperties.getProperty("ZEV.qstcsd")) : (type == 2 ? Integer.parseInt(ServerProperties.getProperty("ZEV.zscsd")) : 910000000)));//�����أ�130030000//140000000
            ps.setInt(21, chr.meso); // Meso
            ps.setShort(22, (short) 0); // HP ap used
            ps.setByte(23, (byte) 0); // Spawnpoint
            ps.setInt(24, -1); // Party
            ps.setByte(25, chr.buddylist.getCapacity()); // Buddylist
            ps.setInt(26, 0); // Monster book cover
            ps.setInt(27, 0); // Dojo
            ps.setInt(28, 0); // Dojo record
            ps.setString(29, "-1,-1,-1");
            ps.setInt(30, /*
                     * db ? 1 :
                     */ 0); //for now
            ps.setInt(31, 0); //marriage ID
            ps.setInt(32, 0); //current reps
            ps.setInt(33, 0); //total reps
            ps.setInt(34, chr.prefix);
            ps.setInt(35, chr.getAccountID());
            ps.setString(36, chr.name);
            ps.setByte(37, chr.world);
            ps.setInt(38, chr.mount_id);
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                chr.id = rs.getInt(1);
            } else {
                throw new DatabaseException("Inserting char failed.");
            }
            ps.close();
            rs.close();
            ps = con.prepareStatement("INSERT INTO queststatus (`queststatusid`, `characterid`, `quest`, `status`, `time`, `forfeited`, `customData`) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pse = con.prepareStatement("INSERT INTO queststatusmobs VALUES (DEFAULT, ?, ?, ?)");
            ps.setInt(1, chr.id);
            for (final MapleQuestStatus q : chr.quests.values()) {
                ps.setInt(2, q.getQuest().getId());
                ps.setInt(3, q.getStatus());
                ps.setInt(4, (int) (q.getCompletionTime() / 1000));
                ps.setInt(5, q.getForfeited());
                ps.setString(6, q.getCustomData());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                rs.next();

                if (q.hasMobKills()) {
                    for (int mob : q.getMobKills().keySet()) {
                        pse.setInt(1, rs.getInt(1));
                        pse.setInt(2, mob);
                        pse.setInt(3, q.getMobKills(mob));
                        pse.executeUpdate();
                    }
                }
                rs.close();
            }
            ps.close();
            pse.close();

            ps = con.prepareStatement("INSERT INTO inventoryslot (characterid, `equip`, `use`, `setup`, `etc`, `cash`) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, chr.id);
            ps.setByte(2, (byte) 32); // Eq
            ps.setByte(3, (byte) 32); // Use
            ps.setByte(4, (byte) 32); // Setup
            ps.setByte(5, (byte) 32); // ETC
            ps.setByte(6, (byte) 60); // Cash
            ps.execute();
            ps.close();

            ps = con.prepareStatement("INSERT INTO mountdata (characterid, `Level`, `Exp`, `Fatigue`) VALUES (?, ?, ?, ?)");
            ps.setInt(1, chr.id);
            ps.setByte(2, (byte) 1);
            ps.setInt(3, 0);
            ps.setByte(4, (byte) 0);
            ps.execute();
            ps.close();

            List<Pair<IItem, MapleInventoryType>> listing = new ArrayList<Pair<IItem, MapleInventoryType>>();
            for (final MapleInventory iv : chr.inventory) {
                for (final IItem item : iv.list()) {
                    listing.add(new Pair<IItem, MapleInventoryType>(item, iv.getType()));
                }
            }
            ItemLoader.INVENTORY.saveItems(listing, con, chr.id);

            /*
             * // SEA 102 final int[] array1 = {2, 3, 4, 5, 6, 7, 16, 17, 18,
             * 19, 23, 25, 26, 27, 31, 34, 37, 38, 41, 44, 45, 46, 50, 57, 59,
             * 60, 61, 62, 63, 64, 65, 8, 9, 24, 30}; final int[] array2 = {4,
             * 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 4,
             * 5, 6, 6, 6, 6, 6, 6, 6, 4, 4, 4, 4}; final int[] array3 = {10,
             * 12, 13, 18, 6, 11, 8, 5, 0, 4, 1, 19, 14, 15, 3, 17, 9, 20, 22,
             * 50, 51, 52, 7, 53, 100, 101, 102, 103, 104, 105, 106, 16, 23, 24,
             * 2};
             */
            int[] array1 = {2, 3, 4, 5, 6, 7, 16, 17, 18, 19, 23, 25, 26, 27, 29, 31, 34, 35, 37, 38, 40, 41, 43, 44, 45, 46, 48, 50, 56, 57, 59, 60, 61, 62, 63, 64, 65};
            int[] array2 = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 4, 4, 4, 5, 5, 6, 6, 6, 6, 6, 6, 6};
            int[] array3 = {10, 12, 13, 18, 24, 21, 8, 5, 0, 4, 1, 19, 14, 15, 52, 2, 17, 11, 3, 20, 16, 23, 9, 50, 51, 6, 22, 7, 53, 54, 100, 101, 102, 103, 104, 105, 106};

            ps = con.prepareStatement("INSERT INTO keymap (characterid, `key`, `type`, `action`) VALUES (?, ?, ?, ?)");
            ps.setInt(1, chr.id);
            for (int i = 0; i < array1.length; i++) {
                ps.setInt(2, array1[i]);
                ps.setInt(3, array2[i]);
                ps.setInt(4, array3[i]);
                ps.execute();
            }
            ps.close();

            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            FileoutputUtil.outputFileError("Load\\log\\Packet_Except.log", e);
            System.err.println("[charsave] Error saving character data");
            try {
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
                FileoutputUtil.outputFileError("Load\\log\\Packet_Except.log", ex);
                System.err.println("[charsave] Error Rolling Back");
            }
        } finally {
            try {
                if (pse != null) {
                    pse.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                con.setAutoCommit(true);
                con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            } catch (SQLException e) {
                e.printStackTrace();
                FileoutputUtil.outputFileError("Load\\log\\Packet_Except.log", e);
                System.err.println("[charsave] Error going back to autocommit mode");
            }
        }
        try {
            Connection con1 = DatabaseConnection.getConnection();
            PreparedStatement ps1 = con1.prepareStatement("INSERT INTO shangxianlaba (id) VALUES (?)");
            ps1.setInt(1, chr.id);
            ps1.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public void saveToDB3(boolean dc, boolean fromcs) {
        if (isClone()) {
            return;
        }
        Connection con = DatabaseConnection.getConnection();

        PreparedStatement ps = null;
        PreparedStatement pse = null;
        ResultSet rs = null;

        try {
            /**
             * <��ɫ��Ϣ�浵>
             */
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE characters SET level = ?, fame = ?, str = ?, dex = ?, luk = ?, `int` = ?, exp = ?, hp = ?, mp = ?, maxhp = ?, maxmp = ?, sp = ?, ap = ?, gm = ?, skincolor = ?, gender = ?, job = ?, hair = ?, face = ?, map = ?, meso = ?, hpApUsed = ?, spawnpoint = ?, party = ?, buddyCapacity = ?, monsterbookcover = ?, dojo_pts = ?, dojoRecord = ?, pets = ?, subcategory = ?, marriageId = ?, currentrep = ?, totalrep = ?, charmessage = ?, expression = ?, constellation = ?, blood = ?, month = ?, day = ?, beans = ?, prefix = ?, name = ?, mountid = ? WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, level);
            ps.setShort(2, fame);
            ps.setShort(3, stats.getStr());
            ps.setShort(4, stats.getDex());
            ps.setShort(5, stats.getLuk());
            ps.setShort(6, stats.getInt());
            ps.setInt(7, exp);
            ps.setShort(8, stats.getHp() < 1 ? 50 : stats.getHp());
            ps.setShort(9, stats.getMp());
            ps.setShort(10, stats.getMaxHp());
            ps.setShort(11, stats.getMaxMp());
            final StringBuilder sps = new StringBuilder();
            for (int i = 0; i < remainingSp.length; i++) {
                sps.append(remainingSp[i]);
                sps.append(",");
            }
            final String sp = sps.toString();
            ps.setString(12, sp.substring(0, sp.length() - 1));
            ps.setShort(13, remainingAp);
            ps.setByte(14, gmLevel);
            ps.setByte(15, skinColor);
            ps.setByte(16, gender);
            ps.setShort(17, job);
            ps.setInt(18, hair);
            ps.setInt(19, face);
            if (!fromcs && map != null) {
                if (map.getForcedReturnId() != 999999999) {
                    ps.setInt(20, map.getForcedReturnId());
                } else {
                    ps.setInt(20, stats.getHp() < 1 ? map.getReturnMapId() : map.getId());
                }
            } else {
                ps.setInt(20, mapid);
            }
            ps.setInt(21, meso);
            ps.setShort(22, hpApUsed);
            if (map == null) {
                ps.setByte(23, (byte) 0);
            } else {
                final MaplePortal closest = map.findClosestSpawnpoint(getPosition());
                ps.setByte(23, (byte) (closest != null ? closest.getId() : 0));
            }
            ps.setInt(24, party != null ? party.getId() : -1);
            ps.setShort(25, buddylist.getCapacity());
            ps.setInt(26, bookCover);
            ps.setInt(27, dojo);
            ps.setInt(28, dojoRecord);
            final StringBuilder petz = new StringBuilder();
            int petLength = 0;
            /**
             * <����浵>
             */
            for (final MaplePet pet : pets) {
                pet.saveToDb();
                if (pet.getSummoned()) {
                    petz.append(pet.getInventoryPosition());
                    petz.append(",");
                    petLength++;
                }
            }
            while (petLength < 3) {
                petz.append("-1,");
                petLength++;
            }
            final String petstring = petz.toString();
            ps.setString(29, petstring.substring(0, petstring.length() - 1));
            ps.setByte(30, subcategory);
            ps.setInt(31, marriageId);
            ps.setInt(32, currentrep);
            ps.setInt(33, totalrep);
            ps.setString(34, charmessage);
            ps.setInt(35, expression);
            ps.setInt(36, constellation);
            ps.setInt(37, blood);
            ps.setInt(38, month);
            ps.setInt(39, day);
            ps.setInt(40, beans);
            ps.setInt(41, prefix);
            ps.setString(42, name);
            ps.setInt(43, mount_id);
            ps.setInt(44, id);
            if (ps.executeUpdate() < 1) {
                ps.close();
                throw new DatabaseException("Character not in database (" + id + ")");
            }
            ps.close();
            /**
             * <������Ϣ�浵>
             */
            deleteWhereCharacterId(con, "DELETE FROM skillmacros WHERE characterid = ?");
            for (int i = 0; i < 5; i++) {
                final SkillMacro macro = skillMacros[i];
                if (macro != null) {
                    ps = con.prepareStatement("INSERT INTO skillmacros (characterid, skill1, skill2, skill3, name, shout, position) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    ps.setInt(1, id);
                    ps.setInt(2, macro.getSkill1());
                    ps.setInt(3, macro.getSkill2());
                    ps.setInt(4, macro.getSkill3());
                    ps.setString(5, macro.getName());
                    ps.setInt(6, macro.getShout());
                    ps.setInt(7, i);
                    ps.execute();
                    ps.close();
                }
            }
            /**
             * <������Ϣ�浵>
             */
            deleteWhereCharacterId(con, "DELETE FROM inventoryslot WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO inventoryslot (characterid, `equip`, `use`, `setup`, `etc`, `cash`) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setByte(2, getInventory(MapleInventoryType.EQUIP).getSlotLimit());
            ps.setByte(3, getInventory(MapleInventoryType.USE).getSlotLimit());
            ps.setByte(4, getInventory(MapleInventoryType.SETUP).getSlotLimit());
            ps.setByte(5, getInventory(MapleInventoryType.ETC).getSlotLimit());
            ps.setByte(6, getInventory(MapleInventoryType.CASH).getSlotLimit());
            ps.execute();
            ps.close();
            saveInventory(con);
            /**
             * <������Ϣ�浵1>
             */
            deleteWhereCharacterId(con, "DELETE FROM questinfo WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO questinfo (`characterid`, `quest`, `customData`) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            for (final Entry<Integer, String> q : questinfo.entrySet()) {
                ps.setInt(2, q.getKey());
                ps.setString(3, q.getValue());
                ps.execute();
            }
            ps.close();
            /**
             * <������Ϣ�浵2>
             */
            deleteWhereCharacterId(con, "DELETE FROM queststatus WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO queststatus (`queststatusid`, `characterid`, `quest`, `status`, `time`, `forfeited`, `customData`) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pse = con.prepareStatement("INSERT INTO queststatusmobs VALUES (DEFAULT, ?, ?, ?)");
            ps.setInt(1, id);
            for (final MapleQuestStatus q : quests.values()) {
                ps.setInt(2, q.getQuest().getId());
                ps.setInt(3, q.getStatus());
                ps.setInt(4, (int) (q.getCompletionTime() / 1000));
                ps.setInt(5, q.getForfeited());
                ps.setString(6, q.getCustomData());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                rs.next();

                if (q.hasMobKills()) {
                    for (int mob : q.getMobKills().keySet()) {
                        pse.setInt(1, rs.getInt(1));
                        pse.setInt(2, mob);
                        pse.setInt(3, q.getMobKills(mob));

                        pse.executeUpdate();
                    }
                }
                rs.close();
            }
            ps.close();
            pse.close();
            /**
             * <������Ϣ�浵>
             */
            deleteWhereCharacterId(con, "DELETE FROM skills WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO skills (characterid, skillid, skilllevel, masterlevel, expiration) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, id);

            for (final Entry<ISkill, SkillEntry> skill : skills.entrySet()) {
                if (GameConstants.isApplicableSkill(skill.getKey().getId())) { //do not save additional skills
                    ps.setInt(2, skill.getKey().getId());
                    ps.setByte(3, skill.getValue().skillevel);
                    ps.setByte(4, skill.getValue().masterlevel);
                    ps.setLong(5, skill.getValue().expiration);
                    ps.execute();
                }
            }
            ps.close();
            /**
             * <������Ϣ�浵>
             */
            List<MapleCoolDownValueHolder> cd = getCooldowns();
            if (dc && cd.size() > 0) {
                ps = con.prepareStatement("INSERT INTO skills_cooldowns (charid, SkillID, StartTime, length) VALUES (?, ?, ?, ?)");
                ps.setInt(1, getId());
                for (final MapleCoolDownValueHolder cooling : cd) {
                    ps.setInt(2, cooling.skillId);
                    ps.setLong(3, cooling.startTime);
                    ps.setLong(4, cooling.length);
                    ps.execute();
                }
                ps.close();
            }

            deleteWhereCharacterId(con, "DELETE FROM savedlocations WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO savedlocations (characterid, `locationtype`, `map`) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            for (final SavedLocationType savedLocationType : SavedLocationType.values()) {
                if (savedLocations[savedLocationType.getValue()] != -1) {
                    ps.setInt(2, savedLocationType.getValue());
                    ps.setInt(3, savedLocations[savedLocationType.getValue()]);
                    ps.execute();
                }
            }
            ps.close();
            //���˳ɾ�
            ps = con.prepareStatement("DELETE FROM achievements WHERE accountid = ?");
            ps.setInt(1, accountid);
            ps.executeUpdate();
            ps.close();
            ps = con.prepareStatement("INSERT INTO achievements(charid, achievementid, accountid) VALUES(?, ?, ?)");
            for (Integer achid : finishedAchievements) {
                ps.setInt(1, id);
                ps.setInt(2, achid);
                ps.setInt(3, accountid);
                ps.executeUpdate();
            }
            ps.close();
            //������Ϣ
            deleteWhereCharacterId(con, "DELETE FROM buddies WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO buddies (characterid, `buddyid`, `pending`) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            for (BuddyEntry entry : buddylist.getBuddies()) {
                if (entry != null) {
                    ps.setInt(2, entry.getCharacterId());
                    ps.setInt(3, entry.isVisible() ? 0 : 1);
                    ps.execute();
                }
            }
            ps.close();
            //�˺���Ϣ
            ps = con.prepareStatement("UPDATE accounts SET `ACash` = ?, `mPoints` = ?, `points` = ?,`vpoints` = ? WHERE id = ?");
            ps.setInt(1, acash);
            ps.setInt(2, maplepoints);
            ps.setInt(3, points);
            ps.setInt(4, vpoints);
            ps.setInt(5, client.getAccID());
            ps.execute();
            ps.close();
            if (storage != null) {
                storage.saveToDB();
            }

            ps = con.prepareStatement("UPDATE accounts SET `lastGainHM` = ? WHERE id = ?");
            ps.setLong(1, lastGainHM);
            ps.setInt(2, client.getAccID());
            ps.execute();
            ps.close();

            if (cs != null) {
                cs.save();
            }
            PlayerNPC.updateByCharId(this);
            keylayout.saveKeys(id);
            mount.saveMount(id);
            monsterbook.saveCards(id);

            deleteWhereCharacterId(con, "DELETE FROM wishlist WHERE characterid = ?");
            for (int i = 0; i < getWishlistSize(); i++) {
                ps = con.prepareStatement("INSERT INTO wishlist(characterid, sn) VALUES(?, ?) ");
                ps.setInt(1, getId());
                ps.setInt(2, wishlist[i]);
                ps.execute();
                ps.close();
            }

            deleteWhereCharacterId(con, "DELETE FROM trocklocations WHERE characterid = ?");
            for (int i = 0; i < rocks.length; i++) {
                if (rocks[i] != 999999999) {
                    ps = con.prepareStatement("INSERT INTO trocklocations(characterid, mapid) VALUES(?, ?) ");
                    ps.setInt(1, getId());
                    ps.setInt(2, rocks[i]);
                    ps.execute();
                    ps.close();
                }
            }

            deleteWhereCharacterId(con, "DELETE FROM regrocklocations WHERE characterid = ?");
            for (int i = 0; i < regrocks.length; i++) {
                if (regrocks[i] != 999999999) {
                    ps = con.prepareStatement("INSERT INTO regrocklocations(characterid, mapid) VALUES(?, ?) ");
                    ps.setInt(1, getId());
                    ps.setInt(2, regrocks[i]);
                    ps.execute();
                    ps.close();
                }
            }

            con.commit();
        } catch (SQLException | DatabaseException | UnsupportedOperationException e) {
            FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, e);
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, ex);
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (pse != null) {
                    pse.close();
                }
                if (rs != null) {
                    rs.close();
                }
                con.setAutoCommit(true);//���ݿⱻ�ص�
                con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);//+1
            } catch (SQLException e) {
                FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, e);
                //System.err.println(MapleClient.getLogMessage(this, "[charsave] Error going back to autocommit mode") + e);
            }
        }
    }

    public void ���ߴ浵() {
        if (isClone()) {
            return;
        }
        Connection con = DatabaseConnection.getConnection();

        PreparedStatement ps = null;
        PreparedStatement pse = null;
        ResultSet rs = null;
        try {
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            con.setAutoCommit(false);
            deleteWhereCharacterId(con, "DELETE FROM inventoryslot WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO inventoryslot (characterid, `equip`, `use`, `setup`, `etc`, `cash`) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setByte(2, getInventory(MapleInventoryType.EQUIP).getSlotLimit());
            ps.setByte(3, getInventory(MapleInventoryType.USE).getSlotLimit());
            ps.setByte(4, getInventory(MapleInventoryType.SETUP).getSlotLimit());
            ps.setByte(5, getInventory(MapleInventoryType.ETC).getSlotLimit());
            ps.setByte(6, getInventory(MapleInventoryType.CASH).getSlotLimit());
            ps.execute();
            ps.close();
            saveInventory(con);
        } catch (SQLException | DatabaseException | UnsupportedOperationException e) {
            //e.printStackTrace();
            FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, e);
            // System.err.println(MapleClient.getLogMessage(this, "[charsave] Error saving character data") + e);
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, ex);
                //     System.err.println(MapleClient.getLogMessage(this, "[charsave] Error Rolling Back") + e);
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (pse != null) {
                    pse.close();
                }
                if (rs != null) {
                    rs.close();
                }
                con.setAutoCommit(true);//���ݿⱻ�ص�
                con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);//+1
            } catch (SQLException e) {
                FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, e);
            }
        }
    }

    public void ����浵() {
        if (isClone()) {
            return;
        }
        Connection con = DatabaseConnection.getConnection();

        PreparedStatement ps = null;
        PreparedStatement pse = null;
        ResultSet rs = null;
        try {
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            con.setAutoCommit(false);
            deleteWhereCharacterId(con, "DELETE FROM queststatus WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO queststatus (`queststatusid`, `characterid`, `quest`, `status`, `time`, `forfeited`, `customData`) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pse = con.prepareStatement("INSERT INTO queststatusmobs VALUES (DEFAULT, ?, ?, ?)");
            ps.setInt(1, id);
            for (final MapleQuestStatus q : quests.values()) {
                ps.setInt(2, q.getQuest().getId());
                ps.setInt(3, q.getStatus());
                ps.setInt(4, (int) (q.getCompletionTime() / 1000));
                ps.setInt(5, q.getForfeited());
                ps.setString(6, q.getCustomData());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                rs.next();

                if (q.hasMobKills()) {
                    for (int mob : q.getMobKills().keySet()) {
                        pse.setInt(1, rs.getInt(1));
                        pse.setInt(2, mob);
                        pse.setInt(3, q.getMobKills(mob));

                        pse.executeUpdate();
                    }
                }
                rs.close();
            }
            ps.close();
            pse.close();
        } catch (SQLException | DatabaseException | UnsupportedOperationException e) {
            //e.printStackTrace();
            FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, e);
            // System.err.println(MapleClient.getLogMessage(this, "[charsave] Error saving character data") + e);
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, ex);
                //     System.err.println(MapleClient.getLogMessage(this, "[charsave] Error Rolling Back") + e);
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (pse != null) {
                    pse.close();
                }
                if (rs != null) {
                    rs.close();
                }
                con.setAutoCommit(true);//���ݿⱻ�ص�
                con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);//+1
            } catch (SQLException e) {
                FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, e);
            }
        }
    }

    public void saveToDB2(boolean dc, boolean fromcs) {
        if (isClone()) {
            return;
        }
        Connection con = DatabaseConnection.getConnection();

        PreparedStatement ps = null;
        PreparedStatement pse = null;
        ResultSet rs = null;

        try {
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            con.setAutoCommit(false);
            //ɱ������
//            if (mxmxdMapKilledCountMap.size() > 0) {
//                String date = "ɱ�ּ�¼";
//                ps = con.prepareStatement("DELETE FROM mxmxd_mapkilledcount WHERE chrId = ? AND `date` = ?");
//                ps.setInt(1, id);
//                ps.setString(2, date);
//                ps.execute();
//                ps.close();
//                for (Entry<Integer, Integer> pair : mxmxdMapKilledCountMap.entrySet()) {
//                    ps = con.prepareStatement("INSERT INTO mxmxd_mapkilledcount (chrId, mapId, `count`, `date`) VALUES (?,?,?,?)");
//                    ps.setInt(1, id);
//                    ps.setInt(2, id); // mapId
//                    ps.setInt(3, pair.getValue()); // count
//                    ps.setString(4, date);
//                    ps.execute();
//                    ps.close();
//                }
//            }
            //��ɫ��Ϣ
            ps = con.prepareStatement("UPDATE characters SET level = ?, fame = ?, str = ?, dex = ?, luk = ?, `int` = ?, exp = ?, hp = ?, mp = ?, maxhp = ?, maxmp = ?, sp = ?, ap = ?, gm = ?, skincolor = ?, gender = ?, job = ?, hair = ?, face = ?, map = ?, meso = ?, hpApUsed = ?, spawnpoint = ?, party = ?, buddyCapacity = ?, monsterbookcover = ?, dojo_pts = ?, dojoRecord = ?, pets = ?, subcategory = ?, marriageId = ?, currentrep = ?, totalrep = ?, charmessage = ?, expression = ?, constellation = ?, blood = ?, month = ?, day = ?, beans = ?, prefix = ?, name = ?, mountid = ? WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, level);
            ps.setShort(2, fame);
            ps.setShort(3, stats.getStr());
            ps.setShort(4, stats.getDex());
            ps.setShort(5, stats.getLuk());
            ps.setShort(6, stats.getInt());
            ps.setInt(7, exp);
            ps.setShort(8, stats.getHp() < 1 ? 50 : stats.getHp());
            ps.setShort(9, stats.getMp());
            ps.setShort(10, stats.getMaxHp());
            ps.setShort(11, stats.getMaxMp());
            final StringBuilder sps = new StringBuilder();
            for (int i = 0; i < remainingSp.length; i++) {
                sps.append(remainingSp[i]);
                sps.append(",");
            }
            final String sp = sps.toString();
            ps.setString(12, sp.substring(0, sp.length() - 1));
            ps.setShort(13, remainingAp);
            ps.setByte(14, gmLevel);
            ps.setByte(15, skinColor);
            ps.setByte(16, gender);
            ps.setShort(17, job);
            ps.setInt(18, hair);
            ps.setInt(19, face);
            if (!fromcs && map != null) {
                if (map.getForcedReturnId() != 999999999) {
                    ps.setInt(20, map.getForcedReturnId());
                } else {
                    ps.setInt(20, stats.getHp() < 1 ? map.getReturnMapId() : map.getId());
                }
            } else {
                ps.setInt(20, mapid);
            }
            ps.setInt(21, meso);
            ps.setShort(22, hpApUsed);
            if (map == null) {
                ps.setByte(23, (byte) 0);
            } else {
                final MaplePortal closest = map.findClosestSpawnpoint(getPosition());
                ps.setByte(23, (byte) (closest != null ? closest.getId() : 0));
            }
            ps.setInt(24, party != null ? party.getId() : -1);
            ps.setShort(25, buddylist.getCapacity());
            ps.setInt(26, bookCover);
            ps.setInt(27, dojo);
            ps.setInt(28, dojoRecord);
            final StringBuilder petz = new StringBuilder();
            int petLength = 0;
            //�浵������Ϣ
            /*if (����浵 > 10) {
                for (final MaplePet pet : pets) {
                    //����浵
                    pet.saveToDb();
                    if (pet.getSummoned()) {
                        petz.append(pet.getInventoryPosition());
                        petz.append(",");
                        petLength++;
                    }
                }
                while (petLength < 3) {
                    petz.append("-1,");
                    petLength++;
                }
                final String petstring = petz.toString();
                ps.setString(29, petstring.substring(0, petstring.length() - 1));
                ����浵 = 0;
            } else {
                ����浵++;
            }*/
            for (final MaplePet pet : pets) {
                //����浵
                pet.saveToDb();
                if (pet.getSummoned()) {
                    petz.append(pet.getInventoryPosition());
                    petz.append(",");
                    petLength++;
                }
            }
            while (petLength < 3) {
                petz.append("-1,");
                petLength++;
            }
            final String petstring = petz.toString();
            ps.setString(29, petstring.substring(0, petstring.length() - 1));
            ps.setByte(30, subcategory);
            ps.setInt(31, marriageId);
            ps.setInt(32, currentrep);
            ps.setInt(33, totalrep);
            ps.setString(34, charmessage);
            ps.setInt(35, expression);
            ps.setInt(36, constellation);
            ps.setInt(37, blood);
            ps.setInt(38, month);
            ps.setInt(39, day);
            ps.setInt(40, beans);
            ps.setInt(41, prefix);
            ps.setString(42, name);
            ps.setInt(43, mount_id);
            ps.setInt(44, id);
            if (ps.executeUpdate() < 1) {
                ps.close();
                throw new DatabaseException("Character not in database (" + id + ")");
            }
            ps.close();
            //������Ϣ
            deleteWhereCharacterId(con, "DELETE FROM skillmacros WHERE characterid = ?");
            for (int i = 0; i < 5; i++) {
                final SkillMacro macro = skillMacros[i];
                if (macro != null) {
                    ps = con.prepareStatement("INSERT INTO skillmacros (characterid, skill1, skill2, skill3, name, shout, position) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    ps.setInt(1, id);
                    ps.setInt(2, macro.getSkill1());
                    ps.setInt(3, macro.getSkill2());
                    ps.setInt(4, macro.getSkill3());
                    ps.setString(5, macro.getName());
                    ps.setInt(6, macro.getShout());
                    ps.setInt(7, i);
                    ps.execute();
                    ps.close();
                }
            }
            //����
            deleteWhereCharacterId(con, "DELETE FROM inventoryslot WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO inventoryslot (characterid, `equip`, `use`, `setup`, `etc`, `cash`) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setByte(2, getInventory(MapleInventoryType.EQUIP).getSlotLimit());
            ps.setByte(3, getInventory(MapleInventoryType.USE).getSlotLimit());
            ps.setByte(4, getInventory(MapleInventoryType.SETUP).getSlotLimit());
            ps.setByte(5, getInventory(MapleInventoryType.ETC).getSlotLimit());
            ps.setByte(6, getInventory(MapleInventoryType.CASH).getSlotLimit());
            ps.execute();
            ps.close();

            saveInventory(con);
            //������Ϣ1
            deleteWhereCharacterId(con, "DELETE FROM questinfo WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO questinfo (`characterid`, `quest`, `customData`) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            for (final Entry<Integer, String> q : questinfo.entrySet()) {
                ps.setInt(2, q.getKey());
                ps.setString(3, q.getValue());
                ps.execute();
            }
            ps.close();
            //������Ϣ2
            deleteWhereCharacterId(con, "DELETE FROM queststatus WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO queststatus (`queststatusid`, `characterid`, `quest`, `status`, `time`, `forfeited`, `customData`) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pse = con.prepareStatement("INSERT INTO queststatusmobs VALUES (DEFAULT, ?, ?, ?)");
            ps.setInt(1, id);
            for (final MapleQuestStatus q : quests.values()) {
                ps.setInt(2, q.getQuest().getId());
                ps.setInt(3, q.getStatus());
                ps.setInt(4, (int) (q.getCompletionTime() / 1000));
                ps.setInt(5, q.getForfeited());
                ps.setString(6, q.getCustomData());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                rs.next();

                if (q.hasMobKills()) {
                    for (int mob : q.getMobKills().keySet()) {
                        pse.setInt(1, rs.getInt(1));
                        pse.setInt(2, mob);
                        pse.setInt(3, q.getMobKills(mob));

                        pse.executeUpdate();
                    }
                }
                rs.close();
            }
            ps.close();
            pse.close();
            //������Ϣ1
            deleteWhereCharacterId(con, "DELETE FROM skills WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO skills (characterid, skillid, skilllevel, masterlevel, expiration) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, id);

            for (final Entry<ISkill, SkillEntry> skill : skills.entrySet()) {
                if (GameConstants.isApplicableSkill(skill.getKey().getId())) { //do not save additional skills
                    ps.setInt(2, skill.getKey().getId());
                    ps.setByte(3, skill.getValue().skillevel);
                    ps.setByte(4, skill.getValue().masterlevel);
                    ps.setLong(5, skill.getValue().expiration);
                    ps.execute();
                }
            }
            ps.close();
            //������Ϣ2
            List<MapleCoolDownValueHolder> cd = getCooldowns();
            if (dc && cd.size() > 0) {
                ps = con.prepareStatement("INSERT INTO skills_cooldowns (charid, SkillID, StartTime, length) VALUES (?, ?, ?, ?)");
                ps.setInt(1, getId());
                for (final MapleCoolDownValueHolder cooling : cd) {
                    ps.setInt(2, cooling.skillId);
                    ps.setLong(3, cooling.startTime);
                    ps.setLong(4, cooling.length);
                    ps.execute();
                }
                ps.close();
            }

            deleteWhereCharacterId(con, "DELETE FROM savedlocations WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO savedlocations (characterid, `locationtype`, `map`) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            for (final SavedLocationType savedLocationType : SavedLocationType.values()) {
                if (savedLocations[savedLocationType.getValue()] != -1) {
                    ps.setInt(2, savedLocationType.getValue());
                    ps.setInt(3, savedLocations[savedLocationType.getValue()]);
                    ps.execute();
                }
            }
            ps.close();
            //���˳ɾ�
            ps = con.prepareStatement("DELETE FROM achievements WHERE accountid = ?");
            ps.setInt(1, accountid);
            ps.executeUpdate();
            ps.close();
            ps = con.prepareStatement("INSERT INTO achievements(charid, achievementid, accountid) VALUES(?, ?, ?)");
            for (Integer achid : finishedAchievements) {
                ps.setInt(1, id);
                ps.setInt(2, achid);
                ps.setInt(3, accountid);
                ps.executeUpdate();
            }
            ps.close();
            //������Ϣ
            deleteWhereCharacterId(con, "DELETE FROM buddies WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO buddies (characterid, `buddyid`, `pending`) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            for (BuddyEntry entry : buddylist.getBuddies()) {
                if (entry != null) {
                    ps.setInt(2, entry.getCharacterId());
                    ps.setInt(3, entry.isVisible() ? 0 : 1);
                    ps.execute();
                }
            }
            ps.close();
            //�˺���Ϣ
            ps = con.prepareStatement("UPDATE accounts SET `ACash` = ?, `mPoints` = ?, `points` = ?,`vpoints` = ? WHERE id = ?");
            ps.setInt(1, acash);
            ps.setInt(2, maplepoints);
            ps.setInt(3, points);
            ps.setInt(4, vpoints);
            ps.setInt(5, client.getAccID());
            ps.execute();
            ps.close();
            if (storage != null) {
                storage.saveToDB();
            }

            ps = con.prepareStatement("UPDATE accounts SET `lastGainHM` = ? WHERE id = ?");
            ps.setLong(1, lastGainHM);
            ps.setInt(2, client.getAccID());
            ps.execute();
            ps.close();

            if (cs != null) {
                cs.save();
            }
            PlayerNPC.updateByCharId(this);
            keylayout.saveKeys(id);
            mount.saveMount(id);
            monsterbook.saveCards(id);

            deleteWhereCharacterId(con, "DELETE FROM wishlist WHERE characterid = ?");
            for (int i = 0; i < getWishlistSize(); i++) {
                ps = con.prepareStatement("INSERT INTO wishlist(characterid, sn) VALUES(?, ?) ");
                ps.setInt(1, getId());
                ps.setInt(2, wishlist[i]);
                ps.execute();
                ps.close();
            }

            deleteWhereCharacterId(con, "DELETE FROM trocklocations WHERE characterid = ?");
            for (int i = 0; i < rocks.length; i++) {
                if (rocks[i] != 999999999) {
                    ps = con.prepareStatement("INSERT INTO trocklocations(characterid, mapid) VALUES(?, ?) ");
                    ps.setInt(1, getId());
                    ps.setInt(2, rocks[i]);
                    ps.execute();
                    ps.close();
                }
            }

            deleteWhereCharacterId(con, "DELETE FROM regrocklocations WHERE characterid = ?");
            for (int i = 0; i < regrocks.length; i++) {
                if (regrocks[i] != 999999999) {
                    ps = con.prepareStatement("INSERT INTO regrocklocations(characterid, mapid) VALUES(?, ?) ");
                    ps.setInt(1, getId());
                    ps.setInt(2, regrocks[i]);
                    ps.execute();
                    ps.close();
                }
            }

            con.commit();
        } catch (SQLException | DatabaseException | UnsupportedOperationException e) {
            //e.printStackTrace();
            FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, e);
            // System.err.println(MapleClient.getLogMessage(this, "[charsave] Error saving character data") + e);
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, ex);
                //     System.err.println(MapleClient.getLogMessage(this, "[charsave] Error Rolling Back") + e);
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (pse != null) {
                    pse.close();
                }
                if (rs != null) {
                    rs.close();
                }
                con.setAutoCommit(true);//���ݿⱻ�ص�
                con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);//+1
            } catch (SQLException e) {
                FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, e);
                //System.err.println(MapleClient.getLogMessage(this, "[charsave] Error going back to autocommit mode") + e);
            }
        }
        NPCScriptManager.getInstance().dispose(getClient());
        //System.err.println("[�����]" + CurrentReadable_Time() + " : ��");
    }

    private void deleteWhereCharacterId(Connection con, String sql) throws SQLException {
        deleteWhereCharacterId(con, sql, id);
    }

    public static void deleteWhereCharacterId(Connection con, String sql, int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }

    public void saveInventory(final Connection con) throws SQLException {
        List<Pair<IItem, MapleInventoryType>> listing = new ArrayList<Pair<IItem, MapleInventoryType>>();
        for (final MapleInventory iv : inventory) {
            for (final IItem item : iv.list()) {
                listing.add(new Pair<IItem, MapleInventoryType>(item, iv.getType()));
            }
        }
        if (con != null) {
            ItemLoader.INVENTORY.saveItems(listing, con, id);
        } else {
            ItemLoader.INVENTORY.saveItems(listing, id);
        }
    }

    public final PlayerStats getStat() {
        return stats;
    }

    public final PlayerRandomStream CRand() {
        return CRand;
    }

    public final void QuestInfoPacket(final MaplePacketLittleEndianWriter mplew) {
        mplew.writeShort(questinfo.size());

        for (final Entry<Integer, String> q : questinfo.entrySet()) {
            mplew.writeShort(q.getKey());
            mplew.writeMapleAsciiString(q.getValue() == null ? "" : q.getValue());
        }
    }

    public final void updateInfoQuest(final int questid, final String data) {
        questinfo.put(questid, data);
        client.sendPacket(MaplePacketCreator.updateInfoQuest(questid, data));
    }

    public final String getInfoQuest(final int questid) {
        if (questinfo.containsKey(questid)) {
            return questinfo.get(questid);
        }
        return "";
    }

    public final int getNumQuest() {
        int i = 0;
        for (final MapleQuestStatus q : quests.values()) {
            if (q.getStatus() == 2 && !(q.isCustom())) {
                i++;
            }
        }
        return i;
    }

    public final byte getQuestStatus(final int quest) {
        return getQuest(MapleQuest.getInstance(quest)).getStatus();
    }

    public final MapleQuestStatus getQuest(final MapleQuest quest) {
        if (!quests.containsKey(quest)) {
            return new MapleQuestStatus(quest, (byte) 0);
        }
        return quests.get(quest);
    }

    public void setQuestAdd(int quest) {
        setQuestAddZ(MapleQuest.getInstance(quest), (byte) 2, null);
    }

    public final void setQuestAddZ(final MapleQuest quest, final byte status, final String customData) {

        final MapleQuestStatus stat = new MapleQuestStatus(quest, status);
        stat.setCustomData(customData);
        quests.put(quest, stat);

    }

    public final void setQuestAdd(final MapleQuest quest, final byte status, final String customData) {
        if (!quests.containsKey(quest)) {
            final MapleQuestStatus stat = new MapleQuestStatus(quest, status);
            stat.setCustomData(customData);
            quests.put(quest, stat);
        }
    }

    public final MapleQuestStatus getQuestNAdd(final MapleQuest quest) {
        if (!quests.containsKey(quest)) {
            final MapleQuestStatus status = new MapleQuestStatus(quest, (byte) 0);
            quests.put(quest, status);
            return status;
        }
        return quests.get(quest);
    }

    public MapleQuestStatus getQuestRemove(MapleQuest quest) {
        return (MapleQuestStatus) this.quests.remove(quest);
    }

    public final MapleQuestStatus getQuestNoAdd(final MapleQuest quest) {
        return quests.get(quest);
    }

    public final void updateQuest(final MapleQuestStatus quest) {
        updateQuest(quest, false);
    }

    public final void updateQuest(final MapleQuestStatus quest, final boolean update) {
        quests.put(quest.getQuest(), quest);
        if (!(quest.isCustom())) {
            client.sendPacket(MaplePacketCreator.updateQuest(quest));
            if (quest.getStatus() == 1 && !update) {
                client.sendPacket(MaplePacketCreator.updateQuestInfo(this, quest.getQuest().getId(), quest.getNpc(), (byte) 8));
            }
        }
    }

    public void dropTopMsg(String message) {//��Ļ�м��ɫ����
        client.sendPacket(UIPacket.getTopMsg(message));
    }

    public final Map<Integer, String> getInfoQuest_Map() {
        return questinfo;
    }

    public final Map<MapleQuest, MapleQuestStatus> getQuest_Map() {
        return quests;
    }

    //���ݼ���ID�ж�
    public boolean isActiveBuffedValue(int skillid) {
        LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>(effects.values());
        for (MapleBuffStatValueHolder mbsvh : allBuffs) {
            if (mbsvh.effect.isSkill() && mbsvh.effect.getSourceId() == skillid) {
                return true;
            }
        }
        return false;
    }

    public Integer getBuffedValue(MapleBuffStat effect) {
        final MapleBuffStatValueHolder mbsvh = effects.get(effect);
        return mbsvh == null ? null : Integer.valueOf(mbsvh.value);
    }

    public final Integer getBuffedSkill_X(final MapleBuffStat effect) {
        final MapleBuffStatValueHolder mbsvh = effects.get(effect);
        if (mbsvh == null) {
            return null;
        }
        return mbsvh.effect.getX();
    }

    public final Integer getBuffedSkill_Y(final MapleBuffStat effect) {
        final MapleBuffStatValueHolder mbsvh = effects.get(effect);
        if (mbsvh == null) {
            return null;
        }
        return mbsvh.effect.getY();
    }

    public boolean isBuffFrom(MapleBuffStat stat, ISkill skill) {
        final MapleBuffStatValueHolder mbsvh = effects.get(stat);
        if (mbsvh == null) {
            return false;
        }
        return mbsvh.effect.isSkill() && mbsvh.effect.getSourceId() == skill.getId();
    }

    public int getBuffSource(MapleBuffStat stat) {
        final MapleBuffStatValueHolder mbsvh = effects.get(stat);
        return mbsvh == null ? -1 : mbsvh.effect.getSourceId();
    }

    public int getItemQuantity(int itemid, boolean checkEquipped) {
        int possesed = inventory[GameConstants.getInventoryType(itemid).ordinal()].countById(itemid);
        if (checkEquipped) {
            possesed += inventory[MapleInventoryType.EQUIPPED.ordinal()].countById(itemid);
        }
        return possesed;
    }

    public void setBuffedValue(MapleBuffStat effect, int value) {
        final MapleBuffStatValueHolder mbsvh = effects.get(effect);
        if (mbsvh == null) {
            return;
        }
        mbsvh.value = value;
    }

    public Long getBuffedStarttime(MapleBuffStat effect) {
        final MapleBuffStatValueHolder mbsvh = effects.get(effect);
        return mbsvh == null ? null : Long.valueOf(mbsvh.startTime);
    }

    public MapleStatEffect getStatForBuff(MapleBuffStat effect) {
        final MapleBuffStatValueHolder mbsvh = effects.get(effect);
        return mbsvh == null ? null : mbsvh.effect;
    }

    private void prepareDragonBlood(final MapleStatEffect bloodEffect) {//��֮���������
        if (dragonBloodSchedule != null) {
            dragonBloodSchedule.cancel(false);
        }
        dragonBloodSchedule = BuffTimer.getInstance().register(new Runnable() {

            @Override
            public void run() {
                //if (stats.getHp() - bloodEffect.getX() < 1) {
                if (stats.getHp() < 500) {
                    cancelBuffStats(MapleBuffStat.DRAGONBLOOD);
                } else {
                    addHP(-bloodEffect.getX());
                    client.sendPacket(MaplePacketCreator.showOwnBuffEffect(bloodEffect.getSourceId(), 5));
                    map.broadcastMessage(MapleCharacter.this, MaplePacketCreator.showBuffeffect(getId(), bloodEffect.getSourceId(), 5), false);
                }
            }
        }, 3000, 3000);//ʱ��
    }

    public void startMapTimeLimitTask(int time, final MapleMap to) {
        client.sendPacket(MaplePacketCreator.getClock(time));

        time *= 1000;
        mapTimeLimitTask = MapTimer.getInstance().register(new Runnable() {

            @Override
            public void run() {
                changeMap(to, to.getPortal(0));
            }
        }, time, time);
    }

    public void startFishingTask1(final boolean VIP) {
        final int time = 300 * 1000;
        final int �ɹ��� = 30;
        dropMessage(5, "����ȼ���1");
        dropMessage(5, "����ʱ�䣺" + (time / 1000) + "��");
        dropMessage(5, "������ʣ�" + �ɹ��� + " %");
        cancelFishingTask();
        fishing = EtcTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                final boolean ʹ�ø߼���� = haveItem(2300001, 1, false, true);
                if (!ʹ�ø߼���� && !haveItem(2300000, 1, false, true)) {
                    cancelFishingTask();
                    return;
                }
                setBossLog("����");
                MapleInventoryManipulator.removeById(client, MapleInventoryType.USE, ʹ�ø߼���� ? 2300001 : 2300000, 1, false, false);

                final int randval = RandomRewards.getInstance().getFishingReward();

                switch (randval) {
                    case 0: // Meso
                        final int money = Randomizer.rand(ʹ�ø߼���� ? 20 : 10, ʹ�ø߼���� ? 20000 : 10000);
                        gainMeso(money, true);
                        client.sendPacket(UIPacket.fishingUpdate((byte) 1, money));
                        break;
                    case 1: // EXP
                        final int experi = Randomizer.nextInt(Math.abs(GameConstants.getExpNeededForLevel(level) / 600) + 1);
                        gainExp(ʹ�ø߼���� ? (experi * 2) : experi, true, false, true);
                        client.sendPacket(UIPacket.fishingUpdate((byte) 2, experi));
                        break;
                    default:
                        final double ����ɹ��� = Math.ceil(Math.random() * 100);
                        if (�ɹ��� > ����ɹ���) {
                            MapleInventoryManipulator.addById(client, randval, (short) 1, (byte) 0);
                            client.sendPacket(UIPacket.fishingUpdate((byte) 0, randval));
                            if (RandomRewards.getInstance().FishingRewardItemNameMap.containsKey(randval)) {
                                String itemName = RandomRewards.getInstance().FishingRewardItemNameMap.get(randval);
                                MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                                NPCScriptManager.getInstance().start(client, 2000, 5);
                                String msg = "[�����¼] : �������" + ii.getName(randval);
                                if (randval > 0) {
                                    dropMessage(5, msg);
                                } else {
                                    World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(6, client.getChannel(), msg));
                                }
                            }
                        } else {
                            dropMessage(5, "����ʧ�ܡ�");
                        }

                        break;
                }
                map.broadcastMessage(UIPacket.fishingCaught(id));
            }
        }, time, time);
    }

    public void startFishingTask2(final boolean VIP) {
        final int time = 270 * 1000;
        final int �ɹ��� = 35;
        dropMessage(5, "����ȼ���2");
        dropMessage(5, "����ʱ�䣺" + (time / 1000) + "��");
        dropMessage(5, "������ʣ�" + �ɹ��� + " %");
        cancelFishingTask();
        fishing = EtcTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                final boolean ʹ�ø߼���� = haveItem(2300001, 1, false, true);
                if (!ʹ�ø߼���� && !haveItem(2300000, 1, false, true)) {
                    cancelFishingTask();
                    return;
                }
                setBossLog("����");
                MapleInventoryManipulator.removeById(client, MapleInventoryType.USE, ʹ�ø߼���� ? 2300001 : 2300000, 1, false, false);

                final int randval = RandomRewards.getInstance().getFishingReward();

                switch (randval) {
                    case 0: // Meso
                        final int money = Randomizer.rand(ʹ�ø߼���� ? 20 : 10, ʹ�ø߼���� ? 20000 : 10000);
                        gainMeso(money, true);
                        client.sendPacket(UIPacket.fishingUpdate((byte) 1, money));
                        break;
                    case 1: // EXP
                        final int experi = Randomizer.nextInt(Math.abs(GameConstants.getExpNeededForLevel(level) / 600) + 1);
                        gainExp(ʹ�ø߼���� ? (experi * 2) : experi, true, false, true);
                        client.sendPacket(UIPacket.fishingUpdate((byte) 2, experi));
                        break;
                    default:
                        final double ����ɹ��� = Math.ceil(Math.random() * 100);
                        if (�ɹ��� > ����ɹ���) {
                            MapleInventoryManipulator.addById(client, randval, (short) 1, (byte) 0);
                            client.sendPacket(UIPacket.fishingUpdate((byte) 0, randval));
                            if (RandomRewards.getInstance().FishingRewardItemNameMap.containsKey(randval)) {
                                String itemName = RandomRewards.getInstance().FishingRewardItemNameMap.get(randval);
                                MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                                NPCScriptManager.getInstance().start(client, 2000, 5);
                                String msg = "[�����¼] : �������" + ii.getName(randval);
                                if (randval > 0) {
                                    dropMessage(5, msg);
                                } else {
                                    World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(6, client.getChannel(), msg));
                                }
                            }
                        } else {
                            dropMessage(5, "����ʧ�ܡ�");
                        }

                        break;
                }
                map.broadcastMessage(UIPacket.fishingCaught(id));
            }
        }, time, time);
    }

    public void startFishingTask3(final boolean VIP) {
        final int time = 240 * 1000;
        final int �ɹ��� = 40;
        dropMessage(5, "����ȼ���3");
        dropMessage(5, "����ʱ�䣺" + (time / 1000) + "��");
        dropMessage(5, "������ʣ�" + �ɹ��� + " %");
        cancelFishingTask();
        fishing = EtcTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                final boolean ʹ�ø߼���� = haveItem(2300001, 1, false, true);
                if (!ʹ�ø߼���� && !haveItem(2300000, 1, false, true)) {
                    cancelFishingTask();
                    return;
                }
                setBossLog("����");
                MapleInventoryManipulator.removeById(client, MapleInventoryType.USE, ʹ�ø߼���� ? 2300001 : 2300000, 1, false, false);

                final int randval = RandomRewards.getInstance().getFishingReward();

                switch (randval) {
                    case 0: // Meso
                        final int money = Randomizer.rand(ʹ�ø߼���� ? 20 : 10, ʹ�ø߼���� ? 20000 : 10000);
                        gainMeso(money, true);
                        client.sendPacket(UIPacket.fishingUpdate((byte) 1, money));
                        break;
                    case 1: // EXP
                        final int experi = Randomizer.nextInt(Math.abs(GameConstants.getExpNeededForLevel(level) / 600) + 1);
                        gainExp(ʹ�ø߼���� ? (experi * 2) : experi, true, false, true);
                        client.sendPacket(UIPacket.fishingUpdate((byte) 2, experi));
                        break;
                    default:
                        final double ����ɹ��� = Math.ceil(Math.random() * 100);
                        if (�ɹ��� > ����ɹ���) {
                            MapleInventoryManipulator.addById(client, randval, (short) 1, (byte) 0);
                            client.sendPacket(UIPacket.fishingUpdate((byte) 0, randval));
                            if (RandomRewards.getInstance().FishingRewardItemNameMap.containsKey(randval)) {
                                String itemName = RandomRewards.getInstance().FishingRewardItemNameMap.get(randval);
                                MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                                NPCScriptManager.getInstance().start(client, 2000, 5);
                                String msg = "[�����¼] : �������" + ii.getName(randval);
                                if (randval > 0) {
                                    dropMessage(5, msg);
                                } else {
                                    World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(6, client.getChannel(), msg));
                                }
                            }
                        } else {
                            dropMessage(5, "����ʧ�ܡ�");
                        }

                        break;
                }
                map.broadcastMessage(UIPacket.fishingCaught(id));
            }
        }, time, time);
    }

    public void startFishingTask4(final boolean VIP) {
        final int time = 200 * 1000;
        final int �ɹ��� = 50;
        dropMessage(5, "����ȼ���4");
        dropMessage(5, "����ʱ�䣺" + (time / 1000) + "��");
        dropMessage(5, "������ʣ�" + �ɹ��� + " %");
        cancelFishingTask();
        fishing = EtcTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                final boolean ʹ�ø߼���� = haveItem(2300001, 1, false, true);
                if (!ʹ�ø߼���� && !haveItem(2300000, 1, false, true)) {
                    cancelFishingTask();
                    return;
                }
                setBossLog("����");
                MapleInventoryManipulator.removeById(client, MapleInventoryType.USE, ʹ�ø߼���� ? 2300001 : 2300000, 1, false, false);

                final int randval = RandomRewards.getInstance().getFishingReward();

                switch (randval) {
                    case 0: // Meso
                        final int money = Randomizer.rand(ʹ�ø߼���� ? 20 : 10, ʹ�ø߼���� ? 20000 : 10000);
                        gainMeso(money, true);
                        client.sendPacket(UIPacket.fishingUpdate((byte) 1, money));
                        break;
                    case 1: // EXP
                        final int experi = Randomizer.nextInt(Math.abs(GameConstants.getExpNeededForLevel(level) / 600) + 1);
                        gainExp(ʹ�ø߼���� ? (experi * 2) : experi, true, false, true);
                        client.sendPacket(UIPacket.fishingUpdate((byte) 2, experi));
                        break;
                    default:
                        final double ����ɹ��� = Math.ceil(Math.random() * 100);
                        if (�ɹ��� > ����ɹ���) {
                            MapleInventoryManipulator.addById(client, randval, (short) 1, (byte) 0);
                            client.sendPacket(UIPacket.fishingUpdate((byte) 0, randval));
                            if (RandomRewards.getInstance().FishingRewardItemNameMap.containsKey(randval)) {
                                String itemName = RandomRewards.getInstance().FishingRewardItemNameMap.get(randval);
                                MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                                NPCScriptManager.getInstance().start(client, 2000, 5);
                                String msg = "[�����¼] : �������" + ii.getName(randval);
                                if (randval > 0) {
                                    dropMessage(5, msg);
                                } else {
                                    World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(6, client.getChannel(), msg));
                                }
                            }
                        } else {
                            dropMessage(5, "����ʧ�ܡ�");
                        }

                        break;
                }
                map.broadcastMessage(UIPacket.fishingCaught(id));
            }
        }, time, time);
    }

    public void startFishingTask5(final boolean VIP) {
        final int time = 150 * 1000;
        final int �ɹ��� = 60;
        dropMessage(5, "����ȼ���5");
        dropMessage(5, "����ʱ�䣺" + (time / 1000) + "��");
        dropMessage(5, "������ʣ�" + �ɹ��� + " %");
        cancelFishingTask();
        fishing = EtcTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                final boolean ʹ�ø߼���� = haveItem(2300001, 1, false, true);
                if (!ʹ�ø߼���� && !haveItem(2300000, 1, false, true)) {
                    cancelFishingTask();
                    return;
                }
                setBossLog("����");
                MapleInventoryManipulator.removeById(client, MapleInventoryType.USE, ʹ�ø߼���� ? 2300001 : 2300000, 1, false, false);

                final int randval = RandomRewards.getInstance().getFishingReward();

                switch (randval) {
                    case 0: // Meso
                        final int money = Randomizer.rand(ʹ�ø߼���� ? 20 : 10, ʹ�ø߼���� ? 20000 : 10000);
                        gainMeso(money, true);
                        client.sendPacket(UIPacket.fishingUpdate((byte) 1, money));
                        break;
                    case 1: // EXP
                        final int experi = Randomizer.nextInt(Math.abs(GameConstants.getExpNeededForLevel(level) / 600) + 1);
                        gainExp(ʹ�ø߼���� ? (experi * 2) : experi, true, false, true);
                        client.sendPacket(UIPacket.fishingUpdate((byte) 2, experi));
                        break;
                    default:
                        final double ����ɹ��� = Math.ceil(Math.random() * 100);
                        if (�ɹ��� > ����ɹ���) {
                            MapleInventoryManipulator.addById(client, randval, (short) 1, (byte) 0);
                            client.sendPacket(UIPacket.fishingUpdate((byte) 0, randval));
                            if (RandomRewards.getInstance().FishingRewardItemNameMap.containsKey(randval)) {
                                String itemName = RandomRewards.getInstance().FishingRewardItemNameMap.get(randval);
                                MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                                NPCScriptManager.getInstance().start(client, 2000, 5);
                                String msg = "[�����¼] : �������" + ii.getName(randval);
                                if (randval > 0) {
                                    dropMessage(5, msg);
                                } else {
                                    World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(6, client.getChannel(), msg));
                                }
                            }
                        } else {
                            dropMessage(5, "����ʧ�ܡ�");
                        }

                        break;
                }
                map.broadcastMessage(UIPacket.fishingCaught(id));
            }
        }, time, time);
    }

    public void startFishingTask6(final boolean VIP) {
        final int time = 120 * 1000;
        final int �ɹ��� = 70;
        dropMessage(5, "����ȼ���6");
        dropMessage(5, "����ʱ�䣺" + (time / 1000) + "��");
        dropMessage(5, "������ʣ�" + �ɹ��� + " %");
        cancelFishingTask();
        fishing = EtcTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                final boolean ʹ�ø߼���� = haveItem(2300001, 1, false, true);
                if (!ʹ�ø߼���� && !haveItem(2300000, 1, false, true)) {
                    cancelFishingTask();
                    return;
                }
                setBossLog("����");
                MapleInventoryManipulator.removeById(client, MapleInventoryType.USE, ʹ�ø߼���� ? 2300001 : 2300000, 1, false, false);

                final int randval = RandomRewards.getInstance().getFishingReward();

                switch (randval) {
                    case 0: // Meso
                        final int money = Randomizer.rand(ʹ�ø߼���� ? 20 : 10, ʹ�ø߼���� ? 20000 : 10000);
                        gainMeso(money, true);
                        client.sendPacket(UIPacket.fishingUpdate((byte) 1, money));
                        break;
                    case 1: // EXP
                        final int experi = Randomizer.nextInt(Math.abs(GameConstants.getExpNeededForLevel(level) / 600) + 1);
                        gainExp(ʹ�ø߼���� ? (experi * 2) : experi, true, false, true);
                        client.sendPacket(UIPacket.fishingUpdate((byte) 2, experi));
                        break;
                    default:
                        final double ����ɹ��� = Math.ceil(Math.random() * 100);
                        if (�ɹ��� > ����ɹ���) {
                            MapleInventoryManipulator.addById(client, randval, (short) 1, (byte) 0);
                            client.sendPacket(UIPacket.fishingUpdate((byte) 0, randval));
                            if (RandomRewards.getInstance().FishingRewardItemNameMap.containsKey(randval)) {
                                String itemName = RandomRewards.getInstance().FishingRewardItemNameMap.get(randval);
                                MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                                NPCScriptManager.getInstance().start(client, 2000, 5);
                                String msg = "[�����¼] : �������" + ii.getName(randval);
                                if (randval > 0) {
                                    dropMessage(5, msg);
                                } else {
                                    World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(6, client.getChannel(), msg));
                                }
                            }
                        } else {
                            dropMessage(5, "����ʧ�ܡ�");
                        }

                        break;
                }
                map.broadcastMessage(UIPacket.fishingCaught(id));
            }
        }, time, time);
    }

    public void cancelMapTimeLimitTask() {
        if (mapTimeLimitTask != null) {
            mapTimeLimitTask.cancel(false);
        }
    }

    public void cancelFishingTask() {
        if (fishing != null) {
            fishing.cancel(false);
        }
    }

    public void registerEffect(MapleStatEffect effect, long starttime, ScheduledFuture<?> schedule) {
        registerEffect(effect, starttime, schedule, effect.getStatups());
    }

    public void registerEffect(MapleStatEffect effect, long starttime, ScheduledFuture<?> schedule, List<Pair<MapleBuffStat, Integer>> statups) {
        if (effect.isHide()) {
            this.hidden = true;
            map.broadcastMessage(this, MaplePacketCreator.removePlayerFromMap(getId()), false);
        } else if (effect.isDragonBlood()) {
            prepareDragonBlood(effect);
        } else if (effect.isBerserk()) {
            checkBerserk();
        } else if (effect.isMonsterRiding_()) {
            getMount().startSchedule();
        } else if (effect.isBeholder()) {
            prepareBeholderEffect();
        } else if (effect.getSourceId() == 1001 || effect.getSourceId() == 10001001 || effect.getSourceId() == 1001) {
            prepareRecovery();
        }
        int clonez = 0;
        for (Pair<MapleBuffStat, Integer> statup : statups) {
            if (statup.getLeft() == MapleBuffStat.ILLUSION) {
                clonez = statup.getRight();
            }
            int value = statup.getRight().intValue();
            if (statup.getLeft() == MapleBuffStat.MONSTER_RIDING && effect.getSourceId() == 5221006) {
                if (battleshipHP <= 0) {//quick hack
                    battleshipHP = value; //copy this as well
                }
            }
            effects.put(statup.getLeft(), new MapleBuffStatValueHolder(effect, starttime, schedule, value));
        }
        if (clonez > 0) {
            int cloneSize = Math.max(getNumClones(), getCloneSize());
            if (clonez > cloneSize) { //how many clones to summon
                for (int i = 0; i < clonez - cloneSize; i++) { //1-1=0
                    cloneLook();
                }
            }
        }
        stats.recalcLocalStats();
        //System.out.println("Effect registered. Effect: " + effect.getSourceId());
    }

    public List<MapleBuffStat> getBuffStats(final MapleStatEffect effect, final long startTime) {
        final List<MapleBuffStat> bstats = new ArrayList<MapleBuffStat>();
        final Map<MapleBuffStat, MapleBuffStatValueHolder> allBuffs = new EnumMap<MapleBuffStat, MapleBuffStatValueHolder>(effects);
        for (Entry<MapleBuffStat, MapleBuffStatValueHolder> stateffect : allBuffs.entrySet()) {
            final MapleBuffStatValueHolder mbsvh = stateffect.getValue();
            if (mbsvh.effect.sameSource(effect) && (startTime == -1 || startTime == mbsvh.startTime)) {
                bstats.add(stateffect.getKey());
            }
        }
        return bstats;
    }
//�ٻ���

    private boolean deregisterBuffStats(List<MapleBuffStat> stats) {
        boolean clonez = false;
        List<MapleBuffStatValueHolder> effectsToCancel = new ArrayList<MapleBuffStatValueHolder>(stats.size());
        for (MapleBuffStat stat : stats) {
            final MapleBuffStatValueHolder mbsvh = effects.remove(stat);
            if (mbsvh != null) {
                boolean addMbsvh = true;
                for (MapleBuffStatValueHolder contained : effectsToCancel) {
                    if (mbsvh.startTime == contained.startTime && contained.effect == mbsvh.effect) {
                        addMbsvh = false;
                    }
                }
                if (addMbsvh) {
                    effectsToCancel.add(mbsvh);
                }
                if (stat == MapleBuffStat.SUMMON || stat == MapleBuffStat.PUPPET || stat == MapleBuffStat.REAPER) {
                    final int summonId = mbsvh.effect.getSourceId();
                    final MapleSummon summon = summons.get(summonId);
                    if (summon != null) {
                        map.broadcastMessage(MaplePacketCreator.removeSummon(summon, true));
                        map.removeMapObject(summon);
                        removeVisibleMapObject(summon);
                        summons.remove(summonId);
                        if (summon.getSkill() == 1321007) {
                            if (beholderHealingSchedule != null) {
                                beholderHealingSchedule.cancel(false);
                                beholderHealingSchedule = null;
                            }
                            if (beholderBuffSchedule != null) {
                                beholderBuffSchedule.cancel(false);
                                beholderBuffSchedule = null;
                            }
                        }
                    }
                } else if (stat == MapleBuffStat.DRAGONBLOOD) {
                    if (dragonBloodSchedule != null) {
                        dragonBloodSchedule.cancel(false);
                        dragonBloodSchedule = null;
                    }
                } else if (stat == MapleBuffStat.ILLUSION) {
                    disposeClones();
                    clonez = true;
                }
            }
        }
        for (MapleBuffStatValueHolder cancelEffectCancelTasks : effectsToCancel) {
            if (getBuffStats(cancelEffectCancelTasks.effect, cancelEffectCancelTasks.startTime).size() == 0) {
                if (cancelEffectCancelTasks.schedule != null) {
                    cancelEffectCancelTasks.schedule.cancel(false);
                }
            }
        }
        return clonez;
    }

    /**
     * @param effect
     * @param overwrite when overwrite is set no data is sent and all the
     * Buffstats in the StatEffect are deregistered
     * @param startTime
     */
    public void cancelEffect(final MapleStatEffect effect, final boolean overwrite, final long startTime) {
        cancelEffect(effect, overwrite, startTime, effect.getStatups());
    }

    public void cancelEffect(final MapleStatEffect effect, final boolean overwrite, final long startTime, List<Pair<MapleBuffStat, Integer>> statups) {
        List<MapleBuffStat> buffstats;
        if (!overwrite) {
            buffstats = getBuffStats(effect, startTime);
        } else {
            buffstats = new ArrayList<MapleBuffStat>(statups.size());
            for (Pair<MapleBuffStat, Integer> statup : statups) {
                //�����ٻ�������
                buffstats.add(statup.getLeft());
            }
            for (Pair<MapleBuffStat, Integer> statup : statups) {
                //�����ٻ�������
                buffstats.add(statup.getLeft());
            }
        }
        if (buffstats.size() <= 0) {
            return;
        }
        final boolean clonez = deregisterBuffStats(buffstats);
        if (effect.isMagicDoor()) {
            // �ڵ�ͼ��ɾ������
            if (!getDoors().isEmpty()) {
                MapleDoor door = getDoors().iterator().next();
                for (MapleCharacter chr : door.getTarget().getCharacters()) {
                    door.sendDestroyData(chr.client);
                }
                for (MapleCharacter chr : door.getTown().getCharacters()) {
                    door.sendDestroyData(chr.client);
                }
                for (MapleDoor destroyDoor : getDoors()) {
                    door.getTarget().removeMapObject(destroyDoor);
                    door.getTown().removeMapObject(destroyDoor);
                }
                removeDoor();
                silentPartyUpdate();
            }
        } else if (effect.isMonsterRiding_() || getMountId() == effect.getSourceId()) {
            getMount().cancelSchedule();
        } else if (effect.isMonsterRiding()) {
            cancelEffectFromBuffStat(MapleBuffStat.MECH_CHANGE);
        } else if (effect.isAranCombo()) {
            combo = 0;
        }
        //��������Ƿ���OO�е�¼
        if (!overwrite) {
            cancelPlayerBuffs(buffstats);
            if (effect.isHide() && client.getChannelServer().getPlayerStorage().getCharacterById(this.getId()) != null) { //�ۣ�������̫����ˡ�
                this.hidden = false;
                map.broadcastMessage(this, MaplePacketCreator.spawnPlayerMapobject(this), false);

                for (final MaplePet pet : pets) {
                    if (pet.getSummoned()) {
                        map.broadcastMessage(this, PetPacket.showPet(this, pet, false, false), false);
                    }
                }
                for (final WeakReference<MapleCharacter> chr : clones) {
                    if (chr.get() != null) {
                        map.broadcastMessage(chr.get(), MaplePacketCreator.spawnPlayerMapobject(chr.get()), false);
                    }
                }
            }
        }
        /*if (!clonez) {
            for (WeakReference<MapleCharacter> chr : clones) {
                if (chr.get() != null) {
                    chr.get().cancelEffect(effect, overwrite, startTime);
                }
            }
        }*/
        //System.out.println("Effect deregistered. Effect: " + effect.getSourceId());
    }

    public void cancelBuffStats(MapleBuffStat... stat) {
        List<MapleBuffStat> buffStatList = Arrays.asList(stat);
        deregisterBuffStats(buffStatList);
        cancelPlayerBuffs(buffStatList);
    }

    public void cancelEffectFromBuffStat(MapleBuffStat stat) {
        if (effects.get(stat) != null) {
            cancelEffect(effects.get(stat).effect, false, -1);
        }
    }

    //BUFF��ʧ
    private void cancelPlayerBuffs(List<MapleBuffStat> buffstats) {
        boolean write = client.getChannelServer().getPlayerStorage().getCharacterById(getId()) != null;
        if (buffstats.contains(MapleBuffStat.HOMING_BEACON)) {
            if (write) {
                client.sendPacket(MaplePacketCreator.cancelHoming());
            }
        } else if (buffstats.contains(MapleBuffStat.MONSTER_RIDING)) {
            if (write) {
                stats.recalcLocalStats();
            }
            client.sendPacket(MaplePacketCreator.cancelBuffMONSTER(buffstats));
            map.broadcastMessage(this, MaplePacketCreator.cancelForeignBuffMONSTER(getId(), buffstats), false);
        } else {
            if (write) {
                stats.recalcLocalStats();
            }
            client.sendPacket(MaplePacketCreator.cancelBuff(buffstats));
            map.broadcastMessage(this, MaplePacketCreator.cancelForeignBuff(getId(), buffstats), false);
        }
    }

    public void dispel() {
        if (!isHidden()) {
            final LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>(effects.values());
            for (MapleBuffStatValueHolder mbsvh : allBuffs) {
                if (mbsvh.effect.isSkill() && mbsvh.schedule != null && !mbsvh.effect.isMorph()) {
                    cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                }
            }
        }
    }

    public void dispelSkill(int skillid) {
        final LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>(effects.values());

        for (MapleBuffStatValueHolder mbsvh : allBuffs) {
            if (skillid == 0) {
                if (mbsvh.effect.isSkill() && (mbsvh.effect.getSourceId() == 4331003 || mbsvh.effect.getSourceId() == 4331002 || mbsvh.effect.getSourceId() == 4341002 || mbsvh.effect.getSourceId() == 22131001 || mbsvh.effect.getSourceId() == 1321007 || mbsvh.effect.getSourceId() == 2121005 || mbsvh.effect.getSourceId() == 2221005 || mbsvh.effect.getSourceId() == 2311006 || mbsvh.effect.getSourceId() == 2321003 || mbsvh.effect.getSourceId() == 3111002 || mbsvh.effect.getSourceId() == 3111005 || mbsvh.effect.getSourceId() == 3211002 || mbsvh.effect.getSourceId() == 3211005 || mbsvh.effect.getSourceId() == 4111002)) {
                    cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                    break;
                }
            } else if (mbsvh.effect.isSkill() && mbsvh.effect.getSourceId() == skillid) {
                cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                break;
            }
        }
    }

    public void dispelBuff(int skillid) {
        final LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>(effects.values());

        for (MapleBuffStatValueHolder mbsvh : allBuffs) {
            if (mbsvh.effect.getSourceId() == skillid) {
                cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                break;
            }
        }
    }

    public void cancelAllBuffs_() {
        effects.clear();
    }

    public void cancelAllBuffs() {
        final LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>(effects.values());

        for (MapleBuffStatValueHolder mbsvh : allBuffs) {
            cancelEffect(mbsvh.effect, false, mbsvh.startTime);
        }
    }

    public void cancelMorphs() {
        final LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>(effects.values());

        for (MapleBuffStatValueHolder mbsvh : allBuffs) {
            switch (mbsvh.effect.getSourceId()) {
                case 5111005:
                case 5121003:
                case 15111002:
                case 13111005:
                    return; // Since we can't have more than 1, save up on loops
                default:
                    if (mbsvh.effect.isMorph()) {
                        cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                        continue;
                    }
            }
        }
    }

    public int getMorphState() {
        LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>(effects.values());
        for (MapleBuffStatValueHolder mbsvh : allBuffs) {
            if (mbsvh.effect.isMorph()) {
                return mbsvh.effect.getSourceId();
            }
        }
        return -1;
    }

    public void silentGiveBuffs(List<PlayerBuffValueHolder> buffs) {
        if (buffs == null) {
            return;
        }
        for (PlayerBuffValueHolder mbsvh : buffs) {
            mbsvh.effect.silentApplyBuff(this, mbsvh.startTime);
        }
    }

    public List<PlayerBuffValueHolder> getAllBuffs() {
        List<PlayerBuffValueHolder> ret = new ArrayList<PlayerBuffValueHolder>();
        LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>(effects.values());
        for (MapleBuffStatValueHolder mbsvh : allBuffs) {
            ret.add(new PlayerBuffValueHolder(mbsvh.startTime, mbsvh.effect));
        }
        return ret;
    }

    public void cancelMagicDoor() {
        final LinkedList<MapleBuffStatValueHolder> allBuffs = new LinkedList<MapleBuffStatValueHolder>(effects.values());

        for (MapleBuffStatValueHolder mbsvh : allBuffs) {
            if (mbsvh.effect.isMagicDoor()) {
                cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                break;
            }
        }
    }

    public int getSkillLevel(int skillid) {
        return getSkillLevel(SkillFactory.getSkill(skillid));
    }

    public final void handleEnergyCharge(final int skillid, final int targets) {//ȭ�����������޸�
        final ISkill echskill = SkillFactory.getSkill(skillid);//����
        final byte skilllevel = getSkillLevel(echskill);//���ܵȼ�
        if (skilllevel > 0) {//������ܵȼ�����0
            final MapleStatEffect echeff = echskill.getEffect(skilllevel);
            if (targets > 0) {
                //dropMessage(5, "ENERGY_CHARGEA = null "+getBuffedValue(MapleBuffStat.ENERGY_CHARGE));
                if (getBuffedValue(MapleBuffStat.ENERGY_CHARGE) == null) {
                    echeff.applyEnergyBuff(this, true); // Infinity time
                    //dropMessage(5, "ENERGY_CHARGEB = null "+getBuffedValue(MapleBuffStat.ENERGY_CHARGE));
                } else {
                    Integer energyLevel = getBuffedValue(MapleBuffStat.ENERGY_CHARGE);
                    //TODO: bar going down
                    if (energyLevel <= 10000) {//����С��10000��ִ������
                        energyLevel += (echeff.getX() * targets);//��������

                        //dropMessage(5, "energyLevel"+energyLevel);
                        client.sendPacket(MaplePacketCreator.showOwnBuffEffect(skillid, 2));
                        map.broadcastMessage(this, MaplePacketCreator.showBuffeffect(id, skillid, 2), false);

                        if (energyLevel >= 10000) {//�����������10000
                            energyLevel = 10000;//��Ҫ����һ��
                            //dropMessage(5, "energyLevelA"+energyLevel);
                            Timer.BuffTimer.getInstance().schedule(new Runnable() {
                                @Override
                                public void run() {
                                    Integer energyLevel = 0;//��ʼ������ ֵΪ0
                                    setBuffedValue(MapleBuffStat.ENERGY_CHARGE, energyLevel);//��������Ϊ0
                                    List<Pair<MapleBuffStat, Integer>> stat = Collections.singletonList(new Pair<>(MapleBuffStat.ENERGY_CHARGE, energyLevel));
                                    client.sendPacket(MaplePacketCreator.������(stat, 0));//��������������Ϊ0
                                }
                            }, 50000L);//50���������0
                        }
                        List<Pair<MapleBuffStat, Integer>> stat = Collections.singletonList(new Pair<MapleBuffStat, Integer>(MapleBuffStat.ENERGY_CHARGE, energyLevel));
                        client.sendPacket(MaplePacketCreator.������(stat, energyLevel / 1000));
                        setBuffedValue(MapleBuffStat.ENERGY_CHARGE, energyLevel);
                        //dropMessage(5, "������"+energyLevel / 1000);
                    } else if (energyLevel == 10000) {//��������ﵽ10000
                        echeff.applyEnergyBuff(this, false);//ֹͣ������
                        setBuffedValue(MapleBuffStat.ENERGY_CHARGE, 10001);//��������ֵΪ10001 ���ö�1��
                    }
                }
            }
        }
    }

    //��װ����
    //���ݼ���ID���ж�
    public final void handleBattleshipHP(int damage) {
        if (isActiveBuffedValue(5221006)) {
            battleshipHP -= damage;
            if (battleshipHP <= 0) {
                battleshipHP = 0;
                final MapleStatEffect effect = getStatForBuff(MapleBuffStat.MONSTER_RIDING);
                client.sendPacket(MaplePacketCreator.skillCooldown(5221006, effect.getCooldown()));
                addCooldown(5221006, System.currentTimeMillis(), effect.getCooldown() * 1000);
                dispelSkill(5221006);
            }
        }
    }

    //ս������
    public final void handleOrbgain() {
        ISkill combo;
        int orbcount = getBuffedValue(MapleBuffStat.COMBO);
        ISkill advcombo = null;

        switch (getJob()) {
            case 1110:
            case 1111:
            case 1112:
                combo = SkillFactory.getSkill(11111001);
                advcombo = SkillFactory.getSkill(11110005);
                break;
            case 121:
            case 122:
                combo = SkillFactory.getSkill(1211009);
                break;
            default:
                combo = SkillFactory.getSkill(1111002);

                advcombo = SkillFactory.getSkill(1120003);
                break;
        }

        MapleStatEffect ceffect = null;
        int advComboSkillLevel = getSkillLevel(advcombo);
        if (advComboSkillLevel > 0) {
            ceffect = advcombo.getEffect(advComboSkillLevel);
        } else if (getSkillLevel(combo) > 0) {
            ceffect = combo.getEffect(getSkillLevel(combo));
        } else {
            return;
        }

        if (orbcount < ceffect.getX() + 1) {
            int neworbcount = orbcount + 1;
            if (advComboSkillLevel > 0 && ceffect.makeChanceResult()) {
                if (neworbcount < ceffect.getX() + 1) {
                    neworbcount++;
                }
            }
            List<Pair<MapleBuffStat, Integer>> stat = Collections.singletonList(new Pair<MapleBuffStat, Integer>(MapleBuffStat.COMBO, neworbcount));
            setBuffedValue(MapleBuffStat.COMBO, neworbcount);
            int duration = ceffect.getDuration();
            duration += (int) ((getBuffedStarttime(MapleBuffStat.COMBO) - System.currentTimeMillis()));

            client.sendPacket(MaplePacketCreator.giveBuff(combo.getId(), duration, stat, ceffect));
            map.broadcastMessage(this, MaplePacketCreator.giveForeignBuff(this, getId(), stat, ceffect), false);
        }
    }

    //ս������
    public void handleOrbconsume() {
        ISkill combo;

        switch (getJob()) {
            case 1110:
            case 1111:
                combo = SkillFactory.getSkill(11111001);
                break;
            default:
//                if (F().get(FM("������ͨ")) != null) {
//                    double ��� = Math.ceil(Math.random() * 100);
//                    if (F().get(FM("������ͨ")) <= ���) {
//                        combo = SkillFactory.getSkill(9999999);
//                    } else {
//                        combo = SkillFactory.getSkill(1111002);
//                    }
//                } else {
                combo = SkillFactory.getSkill(1111002);
//                }
                break;
        }

        if (getSkillLevel(combo) <= 0) {
            return;
        }
        MapleStatEffect ceffect = getStatForBuff(MapleBuffStat.COMBO);
        if (ceffect == null) {
            return;
        }
        List<Pair<MapleBuffStat, Integer>> stat = Collections.singletonList(new Pair<MapleBuffStat, Integer>(MapleBuffStat.COMBO, 1));
        setBuffedValue(MapleBuffStat.COMBO, 1);
        int duration = ceffect.getDuration();
        duration += (int) ((getBuffedStarttime(MapleBuffStat.COMBO) - System.currentTimeMillis()));
        client.sendPacket(MaplePacketCreator.giveBuff(combo.getId(), duration, stat, ceffect));
        map.broadcastMessage(this, MaplePacketCreator.giveForeignBuff(this, getId(), stat, ceffect), false);
    }

    public void silentEnforceMaxHpMp() {
        stats.setMp(stats.getMp());
        stats.setHp(stats.getHp(), true);
    }

    public void enforceMaxHpMp() {
        List<Pair<MapleStat, Integer>> statups = new ArrayList<Pair<MapleStat, Integer>>(2);
        if (stats.getMp() > stats.getCurrentMaxMp()) {
            stats.setMp(stats.getMp());
            statups.add(new Pair<MapleStat, Integer>(MapleStat.MP, Integer.valueOf(stats.getMp())));
        }
        if (stats.getHp() > stats.getCurrentMaxHp()) {
            stats.setHp(stats.getHp());
            statups.add(new Pair<MapleStat, Integer>(MapleStat.HP, Integer.valueOf(stats.getHp())));
        }
        if (statups.size() > 0) {
            client.sendPacket(MaplePacketCreator.updatePlayerStats(statups, getJob()));
        }
    }

    public MapleMap getMap() {
        return map;
    }
    private transient MapleLieDetector antiMacro;

    public MapleLieDetector getAntiMacro() {
        return this.antiMacro;
    }

    public MonsterBook getMonsterBook() {
        return monsterbook;
    }

    public void setMap(MapleMap newmap) {
        this.map = newmap;
    }

    public void setMap(int PmapId) {
        this.mapid = PmapId;
    }

    public int getMapId() {
        if (map != null) {
            return map.getId();
        }
        return mapid;
    }

    public byte getInitialSpawnpoint() {
        return initialSpawnPoint;
    }

    public int getId() {
        return id;
    }//guildid

    public int getguildid() {
        return guildid;
    }//guildid

    public String getName() {
        return name;
    }

    /* public final void disconnectAll() {
       for (MapleCharacter chr : getCharactersThreadsafe()) {
            if (!chr.isGM()) {
                chr.getClient().disconnect(true, false);
                chr.getClient().getSession().close();
            }
        }
    }*/
    public final String getBlessOfFairyOrigin() {
        return this.BlessOfFairy_Origin;
    }

    public final short getLevel() {
        return level;
    }

    public final short getFame() {
        return fame;
    }

    public final int getDojo() {
        return dojo;
    }

    public final int getDojoRecord() {
        return dojoRecord;
    }

    public final int getFallCounter() {
        return fallcounter;
    }

    public final MapleClient getClient() {
        return client;
    }//guildid

    public final void setClient(final MapleClient client) {
        this.client = client;
    }

    public int getExp() {
        return exp;
    }

    public short getRemainingAp() {
        return remainingAp;
    }

    public int getRemainingSp() {
        return remainingSp[GameConstants.getSkillBook(job)]; //default
    }

    public int getRemainingSp(final int skillbook) {
        return remainingSp[skillbook];
    }

    public int[] getRemainingSps() {
        return remainingSp;
    }

    public int getRemainingSpSize() {
        int ret = 0;
        for (int i = 0; i < remainingSp.length; i++) {
            if (remainingSp[i] > 0) {
                ret++;
            }
        }
        return ret;
    }

    public short getHpApUsed() {
        return hpApUsed;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHpApUsed(short hpApUsed) {
        this.hpApUsed = hpApUsed;
    }

    public byte getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(byte skinColor) {
        this.skinColor = skinColor;
    }

    public short getJob() {
        return job;
    }

    public byte getGender() {
        return gender;
    }

    public int getHair() {
        return hair;
    }

    public int getFace() {
        return face;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setHair(int hair) {
        this.hair = hair;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public void setFame(short fame) {
        this.fame = fame;
    }

    public void setDojo(final int dojo) {
        this.dojo = dojo;
    }

    public void setDojoRecord(final boolean reset) {
        if (reset) {
            dojo = 0;
            dojoRecord = 0;
        } else {
            dojoRecord++;
        }
    }

    public void setFallCounter(int fallcounter) {
        this.fallcounter = fallcounter;
    }

    public Point getOldPosition() {
        return old;
    }

    public void setOldPosition(Point x) {
        this.old = x;
    }

    public void setRemainingAp(short remainingAp) {
        this.remainingAp = remainingAp;
    }

    public void setRemainingSp(int remainingSp) {
        this.remainingSp[GameConstants.getSkillBook(job)] = remainingSp; //default
    }

    public void setRemainingSp(int remainingSp, final int skillbook) {
        this.remainingSp[skillbook] = remainingSp;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public void setInvincible(boolean invinc) {
        invincible = invinc;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public CheatTracker getCheatTracker() {
        return anticheat;
    }

    public BuddyList getBuddylist() {
        return buddylist;
    }

    public void addFame(int famechange) {
        this.fame += famechange;
        /*
         * if (this.fame >= 50) { finishAchievement(7); }
         */
    }

    public void changeMapBanish(final int mapid, final String portal, final String msg) {
        dropMessage(5, msg);
        final MapleMap map = client.getChannelServer().getMapFactory().getMap(mapid);
        if (map != null) {
            changeMap(map, map.getPortal(portal));
        }
    }

    public void changeMap(final int to) {
        MapleMap map = ChannelServer.getInstance(getClient().getChannel()).getMapFactory().getMap(to);
        changeMapInternal(map, map.getPortal(0).getPosition(), MaplePacketCreator.getWarpToMap(map, 0, this), map.getPortal(0));
    }

    public void changeMap(int map, int portal) {
        MapleMap warpMap = client.getChannelServer().getMapFactory().getMap(map);
        changeMap(warpMap, warpMap.getPortal(portal));
    }

    public void changeMap(final MapleMap to, final Point pos) {
        changeMapInternal(to, pos, MaplePacketCreator.getWarpToMap(to, 128, this), null);
    }

    public void changeMap(final MapleMap to, final MaplePortal pto) {
        changeMapInternal(to, pto.getPosition(), MaplePacketCreator.getWarpToMap(to, pto.getId(), this), null);
    }

    public void changeMapPortal(final MapleMap to, final MaplePortal pto) {
        changeMapInternal(to, pto.getPosition(), MaplePacketCreator.getWarpToMap(to, pto.getId(), this), pto);
    }

    private void changeMapInternal(final MapleMap to, final Point pos, byte[] warpPacket, final MaplePortal pto) {
        if (to == null) {
            return;
        }
        saveToDB(false, false);
        final int nowmapid = map.getId();
        if (eventInstance != null) {
            eventInstance.changedMap(this, to.getId());
        }
        final boolean pyramid = pyramidSubway != null;
        if (map.getId() == nowmapid) {
            client.sendPacket(warpPacket);

            map.removePlayer(this);
            if (!isClone() && client.getChannelServer().getPlayerStorage().getCharacterById(getId()) != null) {
                map = to;
                setPosition(pos);
                to.addPlayer(this);
                stats.relocHeal();
            }
        }
        if (party != null) {
            silentPartyUpdate();
            getClient().sendPacket(MaplePacketCreator.updateParty(getClient().getChannel(), party, PartyOperation.SILENT_UPDATE, null));
            updatePartyMemberHP();
        }
        if (pyramid && pyramidSubway != null) { //checks if they had pyramid before AND after changing
            pyramidSubway.onChangeMap(this, to.getId());
        }

    }

    public void leaveMap() {
        //controlled.clear();
        visibleMapObjectsLock.writeLock().lock();
        try {
            visibleMapObjects.clear();
        } finally {
            visibleMapObjectsLock.writeLock().unlock();
        }
        if (chair != 0) {
            cancelFishingTask();
            chair = 0;
        }
        cancelMapTimeLimitTask();
    }

    //���񣿣���
    public void changeJob(int newJob) {
        try {
            final boolean isEv = GameConstants.isEvan(job) || GameConstants.isResist(job);
            this.job = (short) newJob;
            if (newJob != 0 && newJob != 1000 && newJob != 2000 && newJob != 2001 && newJob != 3000) {
                if (isEv) {
                    remainingSp[GameConstants.getSkillBook(newJob)] += 5;
                    client.sendPacket(UIPacket.getSPMsg((byte) 5, (short) newJob));
                } else {
                    remainingSp[GameConstants.getSkillBook(newJob)]++;
                    if (newJob % 10 >= 2) {
                        remainingSp[GameConstants.getSkillBook(newJob)] += 2;
                    }
                }
            }
            if (newJob > 0 && !isGM()) {
                resetStatsByJob(true);
                if (!GameConstants.isEvan(newJob)) {
                    if (getLevel() > (newJob == 200 ? 8 : 10) && newJob % 100 == 0 && (newJob % 1000) / 100 > 0) { //first job
                        remainingSp[GameConstants.getSkillBook(newJob)] += 3 * (getLevel() - (newJob == 200 ? 8 : 10));
                    }
                } else if (newJob == 2200) {
                    MapleQuest.getInstance(22100).forceStart(this, 0, null);
                    MapleQuest.getInstance(22100).forceComplete(this, 0);
                    expandInventory((byte) 1, 4);
                    expandInventory((byte) 2, 4);
                    expandInventory((byte) 3, 4);
                    expandInventory((byte) 4, 4);
                    client.sendPacket(MaplePacketCreator.getEvanTutorial("UI/tutorial/evan/14/0"));
                    dropMessage(5, "С�������������ƺ��л�Ҫ�����㡣��������������ʼ̸��.");
                }
            }
            client.sendPacket(MaplePacketCreator.updateSp(this, false, isEv));
            updateSingleStat(MapleStat.JOB, newJob);

            int maxhp = stats.getMaxHp(), maxmp = stats.getMaxMp();

            switch (job) {
                case 100: // Warrior
                case 1100: // Soul Master
                case 2100: // Aran
                case 3200:
                    maxhp += Randomizer.rand(200, 250);
                    break;
                case 200: // Magician
                case 2200: //evan
                case 2210: //evan
                    maxmp += Randomizer.rand(100, 150);
                    break;
                case 300: // Bowman
                case 400: // Thief
                case 500: // Pirate
                case 3300:
                case 3500:
                    maxhp += Randomizer.rand(100, 150);
                    maxmp += Randomizer.rand(25, 50);
                    break;
                case 110: // Fighter
                    maxhp += Randomizer.rand(300, 350);
                    break;
                case 120: // Page
                case 130: // Spearman
                case 510: // ����
                case 512: // ȭ��
                case 1110: // Soul Master
                case 2110: // Aran
                case 3210:
                    maxhp += Randomizer.rand(300, 350);
                    break;
                case 210: // FP
                case 220: // IL
                case 230: // Cleric
                    maxmp += Randomizer.rand(400, 450);
                    break;
                case 310: // Bowman
                case 312: // Bowman
                case 320: // Crossbowman
                case 322: // Bowman
                case 410: // Assasin
                case 412: // Assasin
                case 420: // Bandit
                case 422: // Assasin
                case 430: // Semi Dualer
                case 520: // ����
                case 522: // ����
                case 1310: // Wind Breaker
                case 1410: // Night Walker
                case 3310:
                case 3510:
                    maxhp += Randomizer.rand(300, 350);
                    maxhp += Randomizer.rand(150, 200);
                    break;
                case 900: // GM
                case 800: // Manager
                    maxhp += 30000;
                    maxhp += 30000;
                    break;
            }
            if (maxhp >= 30000) {
                maxhp = 30000;
            }
            if (maxmp >= 30000) {
                maxmp = 30000;
            }

            stats.setMaxHp((short) maxhp);
            stats.setMaxMp((short) maxmp);
            stats.setHp((short) maxhp);
            stats.setMp((short) maxmp);
            List<Pair<MapleStat, Integer>> statup = new ArrayList<Pair<MapleStat, Integer>>(4);
            statup.add(new Pair<MapleStat, Integer>(MapleStat.MAXHP, Integer.valueOf(maxhp)));
            statup.add(new Pair<MapleStat, Integer>(MapleStat.MAXMP, Integer.valueOf(maxmp)));
            statup.add(new Pair<MapleStat, Integer>(MapleStat.HP, Integer.valueOf(maxhp)));
            statup.add(new Pair<MapleStat, Integer>(MapleStat.MP, Integer.valueOf(maxmp)));
            stats.recalcLocalStats();
            client.sendPacket(MaplePacketCreator.updatePlayerStats(statup, getJob()));
            map.broadcastMessage(this, MaplePacketCreator.showForeignEffect(getId(), 8), false);
            silentPartyUpdate();
            guildUpdate();
            familyUpdate();
            if (dragon != null) {
                map.broadcastMessage(MaplePacketCreator.removeDragon(this.id));
                map.removeMapObject(dragon);
                dragon = null;
            }
            baseSkills();
            if (newJob >= 2200 && newJob <= 2218) { //make new
                if (getBuffedValue(MapleBuffStat.MONSTER_RIDING) != null) {
                    cancelBuffStats(MapleBuffStat.MONSTER_RIDING);
                }
                makeDragon();
                map.spawnDragon(dragon);
                map.updateMapObjectVisibility(this, dragon);
            }
        } catch (Exception e) {
            FileoutputUtil.outputFileError(FileoutputUtil.ScriptEx_Log, e); //all jobs throw errors :(
        }
    }

    public void baseSkills() {
        if (GameConstants.getJobNumber(job) >= 3) { //third job.
            List<Integer> skills = SkillFactory.getSkillsByJob(job);
            if (skills != null) {
                for (int i : skills) {
                    final ISkill skil = SkillFactory.getSkill(i);
                    if (skil != null && !skil.isInvisible() && skil.isFourthJob() && getSkillLevel(skil) <= 0 && getMasterLevel(skil) <= 0 && skil.getMasterLevel() > 0) {
                        changeSkillLevel(skil, (byte) 0, (byte) skil.getMasterLevel());
                    }
                }
            }
        }
    }

    public void makeDragon() {
        dragon = new MapleDragon(this);
    }

    public MapleDragon getDragon() {
        return dragon;
    }

    public void gainAp(short ap) {
        this.remainingAp += ap;
        updateSingleStat(MapleStat.AVAILABLEAP, this.remainingAp);
    }

    public void gainSP(int sp) {
        this.remainingSp[GameConstants.getSkillBook(job)] += sp; //default
        client.sendPacket(MaplePacketCreator.updateSp(this, false));
        client.sendPacket(UIPacket.getSPMsg((byte) sp, (short) job));
    }

    public void gainSP2(int sp) {
        this.remainingSp[GameConstants.getSkillBook(job)] -= sp; //default
        client.sendPacket(MaplePacketCreator.updateSp(this, false));
        client.sendPacket(UIPacket.getSPMsg((byte) sp, (short) job));
    }

    public void gainSP(int sp, final int skillbook) {
        this.remainingSp[skillbook] += sp; //default
        client.sendPacket(MaplePacketCreator.updateSp(this, false));
        client.sendPacket(UIPacket.getSPMsg((byte) sp, (short) job));
    }

    public void resetSP(int sp) {
        for (int i = 0; i < this.remainingSp.length; i++) {
            this.remainingSp[i] = sp;
        }
        updateSingleStat(MapleStat.AVAILABLESP, getRemainingSp());
        //   this.client.sendPacket(MaplePacketCreator.updateSp(this, false));
    }

    public void resetAPSP() {
        for (int i = 0; i < remainingSp.length; i++) {
            this.remainingSp[i] = 0;
        }
        client.sendPacket(MaplePacketCreator.updateSp(this, false));
        gainAp((short) -this.remainingAp);
    }

    public int getAllSkillLevels() {
        int rett = 0;
        for (Map.Entry ret : this.skills.entrySet()) {
            if ((!((Skill) ret.getKey()).isBeginnerSkill()) && (((SkillEntry) ret.getValue()).skillevel > 0)) {
                rett += ((SkillEntry) ret.getValue()).skillevel;
            }
        }
        return rett;
    }

    public void changeSkillLevel(final ISkill skill, byte newLevel, byte newMasterlevel) { //1 month
        if (skill == null) {
            return;
        }
        changeSkillLevel(skill, newLevel, newMasterlevel, skill.isTimeLimited() ? (System.currentTimeMillis() + (long) (30L * 24L * 60L * 60L * 1000L)) : -1);
    }

    public void changeSkillLevel(final ISkill skill, byte newLevel, byte newMasterlevel, long expiration) {
        if (skill == null || (!GameConstants.isApplicableSkill(skill.getId()) && !GameConstants.isApplicableSkill_(skill.getId()))) {
            return;
        }
        client.sendPacket(MaplePacketCreator.updateSkill(skill.getId(), newLevel, newMasterlevel, expiration));
        if (newLevel == 0 && newMasterlevel == 0) {
            if (skills.containsKey(skill)) {
                skills.remove(skill);
            } else {
                return; //nothing happen
            }
        } else {
            skills.put(skill, new SkillEntry(newLevel, newMasterlevel, expiration));
        }
        if (GameConstants.isRecoveryIncSkill(skill.getId())) {
            stats.relocHeal();
        } else if (GameConstants.isElementAmp_Skill(skill.getId())) {
            stats.recalcLocalStats();
        }

    }

    public void changeSkillLevel_Skip(final ISkill skill, byte newLevel, byte newMasterlevel) {
        if (skill == null) {
            return;
        }
        client.sendPacket(MaplePacketCreator.updateSkill(skill.getId(), newLevel, newMasterlevel, -1L));
        if (newLevel == 0 && newMasterlevel == 0) {
            if (skills.containsKey(skill)) {
                skills.remove(skill);
            } else {
                return; //nothing happen
            }
        } else {
            skills.put(skill, new SkillEntry(newLevel, newMasterlevel, -1L));
        }

    }

    //��ɫ����
    public void playerDead() {
        final MapleStatEffect statss = getStatForBuff(MapleBuffStat.SOUL_STONE);
        if (statss != null) {
            dropMessage(5, "���Ѿ������ʯ������");
            getStat().setHp(((getStat().getMaxHp() / 100) * statss.getX()));
            setStance(0);
            changeMap(getMap(), getMap().getPortal(0));
            return;
        }
        int[] charmID = {5130000, 5130002, 5131000, 4031283, 4140903};

        int possesed = 0;
        int i;

        //Check for charms
        for (i = 0; i < charmID.length; i++) {
            int quantity = getItemQuantity(charmID[i], false);
            if (possesed == 0 && quantity > 0) {
                possesed = quantity;
                break;
            }
        }
        if (possesed > 0) {
            possesed -= 1;
            getClient().sendPacket(MaplePacketCreator.serverNotice(5, "��ʹ���� [������] ���������ľ��鲻����٣�ʣ�� (" + possesed + " ��)"));
            MapleInventoryManipulator.removeById(getClient(), MapleItemInformationProvider.getInstance().getInventoryType(charmID[i]), charmID[i], 1, true, false);
        } else {
            if (getEventInstance() != null) {
                getEventInstance().playerKilled(this);
            }
            dispelSkill(0);
            cancelEffectFromBuffStat(MapleBuffStat.MORPH);
            cancelEffectFromBuffStat(MapleBuffStat.MONSTER_RIDING);
            cancelEffectFromBuffStat(MapleBuffStat.SUMMON);
            cancelEffectFromBuffStat(MapleBuffStat.REAPER);
            cancelEffectFromBuffStat(MapleBuffStat.PUPPET);
            checkFollow();
            if (job != 0 && job != 1000 && job != 2000) {
                float diepercentage = 0.0f;
                int expforlevel = GameConstants.getExpNeededForLevel(level);
                if (map.isTown() || FieldLimitType.RegularExpLoss.check(map.getFieldLimit())) {
                    diepercentage = 0.01f;
                } else {
                    float v8 = 0.0f;
                    if (this.job / 100 == 3) {
                        v8 = 0.08f;
                    } else {
                        v8 = 0.2f;
                    }
                    diepercentage = (float) (v8 / this.stats.getLuk() + 0.05);
                }
                int v10 = (int) (exp - (long) ((double) expforlevel * diepercentage));
                if (v10 < 0) {
                    v10 = 0;
                }
                this.exp = v10;
            }
            this.updateSingleStat(MapleStat.EXP, this.exp);
            if (!stats.checkEquipDurabilitys(this, -100)) { //i guess this is how it works ?
                dropMessage(5, "�;ö��Ѿ�����.");
            } //lol
            if (pyramidSubway != null) {
                stats.setHp((short) 50);
                pyramidSubway.fail(this);
            }
        }
    }

    public void updatePartyMemberHP() {
        if (party != null) {
            final int channel = client.getChannel();
            for (MaplePartyCharacter partychar : party.getMembers()) {
                if (partychar.getMapid() == getMapId() && partychar.getChannel() == channel) {
                    MapleCharacter other = ChannelServer.getInstance(channel).getPlayerStorage().getCharacterByName(partychar.getName());
                    if (other != null) {
                        other.getClient().sendPacket(MaplePacketCreator.updatePartyMemberHP(getId(), stats.getHp(), stats.getCurrentMaxHp()));
                    }
                }
            }
        }
    }

    public void receivePartyMemberHP() {
        if (party == null) {
            return;
        }
        int channel = client.getChannel();
        for (MaplePartyCharacter partychar : party.getMembers()) {
            if (partychar.getMapid() == getMapId() && partychar.getChannel() == channel) {
                MapleCharacter other = ChannelServer.getInstance(channel).getPlayerStorage().getCharacterByName(partychar.getName());
                if (other != null) {
                    client.sendPacket(MaplePacketCreator.updatePartyMemberHP(other.getId(), other.getStat().getHp(), other.getStat().getCurrentMaxHp()));
                }
            }
        }
    }

    public void healHP(int delta) {
        addHP(delta);
//        client.sendPacket(MaplePacketCreator.showOwnHpHealed(delta));
//        getMap().broadcastMessage(this, MaplePacketCreator.showHpHealed(getId(), delta), false);
    }

    public void healMP(int delta) {
        addMP(delta);
//        client.sendPacket(MaplePacketCreator.showOwnHpHealed(delta));
//        getMap().broadcastMessage(this, MaplePacketCreator.showHpHealed(getId(), delta), false);
    }

    /**
     * Convenience function which adds the supplied parameter to the current hp
     * then directly does a updateSingleStat.
     *
     * @see MapleCharacter#setHp(int)
     * @param delta
     */
    public void addHP(int delta) {
        if (stats.setHp(stats.getHp() + delta)) {
            updateSingleStat(MapleStat.HP, stats.getHp());
        }
    }

    /**
     * Convenience function which adds the supplied parameter to the current mp
     * then directly does a updateSingleStat.
     *
     * @see MapleCharacter#setMp(int)
     * @param delta
     */
    public void addMP(int delta) {
        if (stats.setMp(stats.getMp() + delta)) {
            updateSingleStat(MapleStat.MP, stats.getMp());
        }
    }

    public void addMPHP(int hpDiff, int mpDiff) {
        List<Pair<MapleStat, Integer>> statups = new ArrayList<Pair<MapleStat, Integer>>();

        if (stats.setHp(stats.getHp() + hpDiff)) {
            statups.add(new Pair<MapleStat, Integer>(MapleStat.HP, Integer.valueOf(stats.getHp())));
        }
        if (stats.setMp(stats.getMp() + mpDiff)) {
            statups.add(new Pair<MapleStat, Integer>(MapleStat.MP, Integer.valueOf(stats.getMp())));
        }
        if (statups.size() > 0) {
            client.sendPacket(MaplePacketCreator.updatePlayerStats(statups, getJob()));
        }
    }
    private long lastHPTime, lastMPTime, lastCheckPeriodTime, lastMoveItemTime, lastQuestTime;
    public long lastRecoveryTime = 0;

    public final boolean canQuestAction() {
        if (lastQuestTime + 1000 > System.currentTimeMillis()) {
            return false;
        }
        lastQuestTime = System.currentTimeMillis();
        return true;
    }

    private void prepareRecovery() {
        lastRecoveryTime = System.currentTimeMillis();
    }

    public boolean canRecovery() {
        return lastRecoveryTime > 0 && lastRecoveryTime + 5000 < System.currentTimeMillis() + 5000;
    }

    public void doRecovery() {
        MapleStatEffect eff = getStatForBuff(MapleBuffStat.RECOVERY);
        if (eff != null) {
            prepareRecovery();
            if (stats.getHp() > stats.getCurrentMaxHp()) {
                this.cancelEffectFromBuffStat(MapleBuffStat.RECOVERY);
            } else {
                healHP(eff.getX());
            }
        }
    }

    public final boolean canHP() {
        if (lastHPTime + 5000 > System.currentTimeMillis()) {
            return false;
        }
        lastHPTime = System.currentTimeMillis();
        return true;
    }

    public final boolean canMP() {
        if (lastMPTime + 5000 > System.currentTimeMillis()) {
            return false;
        }
        lastMPTime = System.currentTimeMillis();
        return true;
    }

    public final boolean canCheckPeriod() {
        if (lastCheckPeriodTime + 30000 > System.currentTimeMillis()) {
            return false;
        }
        lastCheckPeriodTime = System.currentTimeMillis();
        return true;
    }

    public final boolean canMoveItem() {
        if (lastMoveItemTime + 250 > System.currentTimeMillis()) {
            return false;
        }
        lastMoveItemTime = System.currentTimeMillis();
        return true;
    }

    public void updateSingleStat(MapleStat stat, int newval) {
        updateSingleStat(stat, newval, false);
    }

    /**
     * Updates a single stat of this MapleCharacter for the client. This method
     * only creates and sends an update packet, it does not update the stat
     * stored in this MapleCharacter instance.
     *
     * @param stat
     * @param newval
     * @param itemReaction
     */
    public void updateSingleStat(MapleStat stat, int newval, boolean itemReaction) {
        /*
         * if (stat == MapleStat.AVAILABLESP) {
         * client.sendPacket(MaplePacketCreator.updateSp(this,
         * itemReaction, false)); return; }
         */
        Pair<MapleStat, Integer> statpair = new Pair<MapleStat, Integer>(stat, Integer.valueOf(newval));
        client.sendPacket(MaplePacketCreator.updatePlayerStats(Collections.singletonList(statpair), itemReaction, getJob()));
    }

    public void gainExp(final int total, final boolean show, final boolean inChat, final boolean white) {
        try {
            if (�ж�ƣ��ֵ() < 60 * gui.Start.ConfigValuesMap.get("ÿ��ƣ��ֵ")) {
                int prevexp = getExp();
                int needed = GameConstants.getExpNeededForLevel(level);
                int ��ʿ�ŵȼ����� = gui.Start.ConfigValuesMap.get("��ʿ�ŵȼ�����");
                int ð�ռҵȼ����� = gui.Start.ConfigValuesMap.get("ð�ռҵȼ�����");
                if (level >= ð�ռҵȼ����� || (GameConstants.isKOC(job) && level >= ��ʿ�ŵȼ�����)) {//��ʿ�ŵȼ�����
                    if (exp + total > needed) {

                        setExp(needed);
                    } else {
                        exp += total;
                    }
                } else {
                    boolean leveled = false;
                    if (exp + total >= needed) {
                        exp += total;
                        levelUp();
                        leveled = true;
                        needed = GameConstants.getExpNeededForLevel(level);
                        if (exp > needed) {
                            setExp(needed);
                        }
                    } else {
                        exp += total;
                    }
                    if (total > 0) {
                        // familyRep(prevexp, needed, leveled);
                    }
                }
                if (total != 0) {
                    if (exp < 0) { // After adding, and negative
                        if (total > 0) {
                            setExp(needed);
                        } else if (total < 0) {
                            setExp(0);
                        }
                    }
                    if (show) {
                        client.sendPacket(MaplePacketCreator.GainEXP_Others(total, inChat, white));
                    }
                    updateSingleStat(MapleStat.EXP, getExp());
                }
            }
        } catch (Exception e) {
            FileoutputUtil.outputFileError(FileoutputUtil.ScriptEx_Log, e);
        }
    }

    public void familyRep(int prevexp, int needed, boolean leveled) {
        if (mfc != null) {
            int onepercent = needed / 100;
            int percentrep = (prevexp / onepercent + getExp() / onepercent);
            if (leveled) {
                percentrep = 100 - percentrep + (level / 2);
            }
            if (percentrep > 0) {
                int sensen = World.Family.setRep(mfc.getFamilyId(), mfc.getSeniorId(), percentrep, level);
                if (sensen > 0) {
                    World.Family.setRep(mfc.getFamilyId(), sensen, percentrep / 2, level); //and we stop here
                }
            }
        }
    }

    public void ����������() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement psu = con.prepareStatement("UPDATE accounts SET df_cheating_player = 1 WHERE id = ?");
            psu.setInt(1, accountid);
            psu.executeUpdate();
            psu.close();
            _isCheatingPlayer = 1;
        } catch (SQLException ex) {
            System.err.printf("���������ҳ�����" + ex.getMessage());
        }
    }

    public void ����ֵ() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement psu = con.prepareStatement("update accounts set df_cheating_player = df_cheating_player + 1 where id = ?");
            psu.setInt(1, accountid);
            psu.executeUpdate();
            psu.close();
            _isCheatingPlayer = 1;
        } catch (SQLException ex) {
            System.err.printf("���������ҳ�����" + ex.getMessage());
        }
    }

    public void ����ֵ() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement psu = con.prepareStatement("update accounts set df_cheating_player = df_cheating_player - 1 where id = ?");
            psu.setInt(1, accountid);
            psu.executeUpdate();
            psu.close();
            _isCheatingPlayer = 1;
        } catch (SQLException ex) {
            System.err.printf("���������ҳ�����" + ex.getMessage());
        }
    }

//    public void ��Ǵ����() {
//        // ����������
//        if (gui.Start.ConfigValuesMap.get("��Ǵ֮�׿���") <= 0) {
//            //����������();
//            �޵�ָ�� = 0;
//            ����ָ�� = 0;
//            spend = -1;
//            startMapEffect("�t _ �s  ��Ǵ֮�ף����Ȳ������������ݣ����� �t _ �s", 5120027);
//            client.sendPacket(MaplePacketCreator.showEffect("tdAnbur/idea_hyperMagic"));
//            NPCScriptManager.getInstance().dispose(client);
//            NPCScriptManager.getInstance().start(client, 2000, 4);
//            setHp(0);
//            updateSingleStat(MapleStat.HP, 0);
//        }
//    }
//
//    public void �ж�����() {
//        // ����������
//        if (gui.Start.ConfigValuesMap.get("��Ǵ֮�׿���") <= 0) {
//            //����������();
//            �޵�ָ�� = 0;
//            ����ָ�� = 0;
//            spend = -1;
//            startMapEffect("�t _ �s  ��Ǵ֮�ף����Ȳ������������ݣ����� �t _ �s", 5120027);
//            client.sendPacket(MaplePacketCreator.showEffect("tdAnbur/idea_hyperMagic"));
//            NPCScriptManager.getInstance().dispose(client);
//            NPCScriptManager.getInstance().start(client, 2000, 4);
//            setHp(0);
//            updateSingleStat(MapleStat.HP, 0);
//        }
//    }
    public void ����() {
//        startMapEffect("�t _ �s  ��Ǵ֮�ף����Ȳ������������ݣ����� �t _ �s", 5120027);
//        client.sendPacket(MaplePacketCreator.showEffect("tdAnbur/idea_hyperMagic"));
        /*NPCScriptManager.getInstance().dispose(client);
        NPCScriptManager.getInstance().start(client, 2000, 4);*/
        startMapEffect("�t _ �s  ��⵽��ʹ�÷Ƿ���������Ѿ�����¼�ڰ��� �t _ �s", 5120027);
        setHp(0);
        updateSingleStat(MapleStat.HP, 0);
    }

    public void ��������() {
//        startMapEffect("�t _ �s  ��Ǵ֮�ף����Ȳ������������ݣ����� �t _ �s", 5120027);
//        client.sendPacket(MaplePacketCreator.showEffect("tdAnbur/idea_hyperMagic"));
        /*NPCScriptManager.getInstance().dispose(client);
        NPCScriptManager.getInstance().start(client, 2000, 4);*/
        startMapEffect("�t _ �s  ��⵽��ʹ�÷Ƿ���������Ѿ�����¼�ڰ��� �t _ �s", 5120027);
        setHp(0);
        getMap().removeDrops();
        updateSingleStat(MapleStat.HP, 0);
    }

    public void ����ȫ��() {
//        startMapEffect("�t _ �s  ��Ǵ֮�ף����Ȳ������������ݣ����� �t _ �s", 5120027);
//        client.sendPacket(MaplePacketCreator.showEffect("tdAnbur/idea_hyperMagic"));
        /*NPCScriptManager.getInstance().dispose(client);
        NPCScriptManager.getInstance().start(client, 2000, 4);*/
        startMapEffect("�t _ �s  ��⵽��ʹ�÷Ƿ���������Ѿ�����¼�ڰ��� �t _ �s", 5120027);
        setHp(0);
        getMap().removeDrops();
        updateSingleStat(MapleStat.HP, 0);
    }

    public void �����޵�() {
//        startMapEffect("�t _ �s  ��Ǵ֮�ף����Ȳ������������ݣ����� �t _ �s", 5120027);
//        client.sendPacket(MaplePacketCreator.showEffect("tdAnbur/idea_hyperMagic"));
        /*NPCScriptManager.getInstance().dispose(client);
        NPCScriptManager.getInstance().start(client, 2000, 4);*/
        startMapEffect("�t _ �s  ��⵽��ʹ�÷Ƿ���������Ѿ�����¼�ڰ��� �t _ �s", 5120027);
        setHp(0);
        getMap().removeDrops();
        updateSingleStat(MapleStat.HP, 0);
    }

    public int �����޵�ָ������(int mobLevel) {
        int lvBalance = level - mobLevel;
        if (lvBalance <= 0) {
            return 100;
        } else if (lvBalance < 5) {
            return 150;
        } else if (lvBalance < 10) {
            return 200;
        } else {
            return lvBalance * 20;
        }
    }

    //private static final int killedLimit = Integer.parseInt(ServerProperties.getProperty("ZEV.tianqian2", "10000"));
    // �˼�����ÿ�ν�ɫ�浵ʱ�ᱻ���
    /*List<MxmxdGainExpMonsterLog> mxmxdGainExpMonsterLogs = new ArrayList<>();
    Map<Integer, Integer> mxmxdMapKilledCountMap = new HashMap<>();*/
 /*int m = 0;
    long t = 0;
    int c = 0; // ��ǰ��ͼɱ���ۻ�����
    int e = 0; // ��ǰ��ͼɱ���ۻ�����
    long spend = -1; // ���ݴ�ֵ����ɱ�ֵľ���ֵ��ֵԽС��˵�����˹ң�������ɱ��Ч�ʣ����õ�����Խ��
    int hpNow = 0;
    int �޵�ָ�� = 0; // ��¼ÿ50��ɱ�ֵ�Ѫ���仯���������һֱû�䣬���ж�Ϊ�޵У���Ǵ����
    int posX = 0; // ɱ��ʱ��λ��
    int ����ָ�� = 0;*/
    int a = 0;//��¼��ͼ
    public int b = 0;//��¼�������
    int c = 0;//��¼�������
    public int d = 0;//������֤��
    public int �һ������֤�� = 0;

    public void gainExpMonster(int gain, final boolean show, final boolean white, final byte pty, int wedding_EXP, int Class_Bonus_EXP, int Equipment_Bonus_EXP, int Premium_Bonus_EXP, int skillId, int mobId, int mobLv, long mobHp) {
        if ((getMapId() >= 190000000 && getMapId() <= 190000002) || getMapId() == 925100100) {
            return;
        }

        if (�ж�ƣ��ֵ() < 60 * gui.Start.ConfigValuesMap.get("ÿ��ƣ��ֵ")) {
            /**
             * <������ɮÿ�մ���>
             */
            if (getMapId() == 702060000 || getMapId() == 541020800 || getMapId() == 280030000 || getMapId() == 240060200 || getMapId() == 220080001) {
                switch (getMapId()) {
                    case 702060000:
                        if (getBossLog("��ɮ��������") >= gui.Start.ConfigValuesMap.get("��ɮ��������")) {
                            return;
                        }
                        break;
                    case 541020800:
                        if (getBossLog("������������") >= gui.Start.ConfigValuesMap.get("������������")) {
                            return;
                        }
                        break;
                    case 280030000:
                        if (getBossLog("������������") >= gui.Start.ConfigValuesMap.get("������������")) {
                            return;
                        }
                        break;
                    case 240060200:
                        if (getBossLog("������������") >= gui.Start.ConfigValuesMap.get("������������")) {
                            return;
                        }
                        break;
                    case 220080001:
                        if (getBossLog("���Ӿ�������") >= gui.Start.ConfigValuesMap.get("���Ӿ�������")) {
                            return;
                        }
                        break;
                    default:
                        break;
                }
            }

            Point pos = getPosition();
            int total = gain + Class_Bonus_EXP + Equipment_Bonus_EXP + Premium_Bonus_EXP + wedding_EXP;
            int partyinc = 0;
            /**
             * <Ұ���ֹһ����>
             */
            if (gui.Start.ConfigValuesMap.get("�һ���⿪��") == 0) {
                int mapId = map.getId();
                if (a == 0) {
                    a = mapId;
                } else if (a == mapId) {
                    b++;
                } else if (a != mapId) {
                    a = 0;
                }
                if (b >= 1000 && d == 0) {
                    d++;
                    int ��� = (int) Math.ceil(Math.random() * 1000);
                    �һ������֤�� = ��� + 1000;
                    dropMessage(5, "��⵽���нű��һ����ɡ�");
                    NPCScriptManager.getInstance().dispose(client);
                    NPCScriptManager.getInstance().start(client, 9900003, 1);
                }
                if (�һ������֤�� > 0) {
                    if (c >= 50) {
                        b = 0;
                        c = 0;
                        d = 0;
                        �һ������֤�� = 0;
                        MapleItemInformationProvider.getInstance().getItemEffect(2030000).applyReturnScroll(client.getPlayer());
                        dropMessage(1, "ϵͳ�������ܴ��ڹһ����ɣ����Ѿ���ǿ�ƻسǡ�");
                        Ⱥ֪ͨ("[ϵͳ����] : ��� " + name + " ��ϵͳ�����ű��һ���û��ͨ����֤���ѱ���¼�ڰ���");
                    } else {
                        c++;
                    }
                    return;
                }
            }
            if (canCheckPeriod()) {
                expirationTask(true, false);
            }
            if (pty > 1) {
                // (Exp / 20) * (member + 1)
                partyinc = (int) (((float) (gain / 20.0)) * (pty + 1));
                total += partyinc;
            }
            if (total > 2100000000) {
                total = 2100000000;
            }
            if (gain > 0 && total < gain) { //just in case
                total = Integer.MAX_VALUE;
            }
            int needed = GameConstants.getExpNeededForLevel(level);
            int ��ʿ�ŵȼ����� = gui.Start.ConfigValuesMap.get("��ʿ�ŵȼ�����");
            int ð�ռҵȼ����� = gui.Start.ConfigValuesMap.get("ð�ռҵȼ�����");
            if (level >= ð�ռҵȼ����� || (GameConstants.isKOC(job) && level >= ��ʿ�ŵȼ�����)) {//�ȼ�����
                if (exp + total > needed) {
                    setExp(needed);
                } else {
                    exp += total;
                }
            } else {
                boolean leveled = false;
                if (exp + total >= needed) {
                    exp += total;
                    levelUp();
                    leveled = true;
                    needed = GameConstants.getExpNeededForLevel(level);
                    if (exp > needed) {
                        setExp(needed);
                    }
                } else {
                    exp += total;
                }
                if (total > 0) {
                    //   familyRep(prevexp, needed, leveled);
                }
            }
            if (gain != 0) {
                if (exp < 0) { // After adding, and negative
                    if (gain > 0) {
                        setExp(GameConstants.getExpNeededForLevel(level));
                    } else if (gain < 0) {
                        setExp(0);
                    }
                }
                updateSingleStat(MapleStat.EXP, getExp());
                if (show) { // still show the expgain even if it's not there
                    client.sendPacket(MaplePacketCreator.GainEXP_Monster(gain, white, wedding_EXP, partyinc, Class_Bonus_EXP, Equipment_Bonus_EXP, Premium_Bonus_EXP));
                }
                //����һ�� = System.currentTimeMillis();
                // dropMessage(5, "��ʼ������" + t);
                //stats.checkEquipLevels(this, total);
            }
        }
    }

    public void forceReAddItem_NoUpdate(IItem item, MapleInventoryType type) {
        getInventory(type).removeSlot(item.getPosition());
        getInventory(type).addFromDB(item);
    }

    public void forceReAddItem(IItem item, MapleInventoryType type) { //used for stuff like durability, item exp/level, probably owner?
        forceReAddItem_NoUpdate(item, type);
        if (type != MapleInventoryType.UNDEFINED) {
            client.sendPacket(MaplePacketCreator.updateSpecialItemUse(item, type == MapleInventoryType.EQUIPPED ? (byte) 1 : type.getType()));
        }
    }

    public void forceReAddItem_Flag(IItem item, MapleInventoryType type) { //used for flags
        forceReAddItem_NoUpdate(item, type);
        if (type != MapleInventoryType.UNDEFINED) {
            client.sendPacket(MaplePacketCreator.updateSpecialItemUse_(item, type == MapleInventoryType.EQUIPPED ? (byte) 1 : type.getType()));
        }
    }

    public void silentPartyUpdate() {
        if (party != null) {
            World.Party.updateParty(party.getId(), PartyOperation.SILENT_UPDATE, new MaplePartyCharacter(this));
        }
    }

    public boolean isGM() {
        return gmLevel > 0;
    }

    public boolean ����Ա() {
        return gmLevel > 0;
    }

    public boolean isAdmin() {
        return gmLevel >= 2;
    }

    public int getGMLevel() {
        return gmLevel;
    }

    public int ����Ա�ȼ�() {
        return gmLevel;
    }

    public boolean isPlayer() {
        return gmLevel == 0;
    }

    public boolean hasGmLevel(int level) {
        return gmLevel >= level;
    }

    public final MapleInventory getInventory(MapleInventoryType type) {
        return inventory[type.ordinal()];
    }

    public final MapleInventory[] getInventorys() {
        return inventory;
    }

    public final void expirationTask() {
        expirationTask(false);
    }

    public final void expirationTask(boolean pending) {
        expirationTask(false, pending);
    }

    public final void expirationTask(boolean packet, boolean pending) {
        if (pending) {
            if (pendingExpiration != null) {
                for (Integer z : pendingExpiration) {
                    client.sendPacket(MTSCSPacket.itemExpired(z));
                }
            }
            pendingExpiration = null;
            if (pendingSkills != null) {
                for (Integer z : pendingSkills) {
                    client.sendPacket(MaplePacketCreator.updateSkill(z, 0, 0, -1));
                    client.sendPacket(MaplePacketCreator.serverNotice(5, "[" + SkillFactory.getSkillName(z) + "] �����Ѿ�����"));
                }
            } //not real msg
            pendingSkills = null;
            return;
        }
        long expiration;
        final List<Integer> ret = new ArrayList<>();
        final long currenttime = System.currentTimeMillis();
        final List<Pair<MapleInventoryType, IItem>> toberemove = new ArrayList<>(); // This is here to prevent deadlock.
        final List<IItem> tobeunlock = new ArrayList<>(); // This is here to prevent deadlock.

        for (final MapleInventoryType inv : MapleInventoryType.values()) {
            for (final IItem item : getInventory(inv)) {
                expiration = item.getExpiration();

                if (expiration != -1 && !GameConstants.isPet(item.getItemId()) && currenttime > expiration) {
                    if (ItemFlag.LOCK.check(item.getFlag())) {
                        tobeunlock.add(item);
                    } else if (currenttime > expiration) {
                        toberemove.add(new Pair<>(inv, item));
                    }
                } else if (item.getItemId() == 5000054 && item.getPet() != null && item.getPet().getSecondsLeft() <= 0) {
                    toberemove.add(new Pair<>(inv, item));
                }
            }
        }
        IItem item;
        for (final Pair<MapleInventoryType, IItem> itemz : toberemove) {
            item = itemz.getRight();
            ret.add(item.getItemId());
            if (packet) {
                getInventory(itemz.getLeft()).removeItem(item.getPosition(), item.getQuantity(), false, this);
            } else {
                getInventory(itemz.getLeft()).removeItem(item.getPosition(), item.getQuantity(), false);
            }
        }
        for (final IItem itemz : tobeunlock) {
            itemz.setExpiration(-1);
            itemz.setFlag((byte) (itemz.getFlag() - ItemFlag.LOCK.getValue()));
        }

        this.pendingExpiration = ret;

        final List<Integer> skilz = new ArrayList<>();
        final List<ISkill> toberem = new ArrayList<>();
        for (Entry<ISkill, SkillEntry> skil : skills.entrySet()) {
            if (skil.getValue().expiration != -1 && currenttime > skil.getValue().expiration) {
                toberem.add(skil.getKey());
            }
        }
        for (ISkill skil : toberem) {
            skilz.add(skil.getId());
            this.skills.remove(skil);
        }
        this.pendingSkills = skilz;
    }

    public MapleShop getShop() {
        return shop;
    }

    public void setShop(MapleShop shop) {
        this.shop = shop;
    }

    public int getMeso() {
        return meso;
    }

    public int �жϽ��() {
        return meso;
    }

    public final int[] getSavedLocations() {
        return savedLocations;
    }

    public int getSavedLocation(SavedLocationType type) {
        return savedLocations[type.getValue()];
    }

    public void saveLocation(SavedLocationType type) {
        savedLocations[type.getValue()] = getMapId();
    }

    public void saveLocation(SavedLocationType type, int mapz) {
        savedLocations[type.getValue()] = mapz;
    }

    public void clearSavedLocation(SavedLocationType type) {
        savedLocations[type.getValue()] = -1;
    }

    public int getDY() {
        return maplepoints;
    }

    public void setDY(int set) {
        this.maplepoints = set;
    }

    public void gainDY(int gain) {
        this.maplepoints += gain;
        // setDY(getDY() + gain);
    }

    public void gainMeso(int gain, boolean show) {
        gainMeso(gain, show, false, false);
    }

    public void gainMeso(int gain, boolean show, boolean enableActions) {
        gainMeso(gain, show, enableActions, false);
    }

    public void gainMeso(int gain, boolean show, boolean enableActions, boolean inChat) {
        if (meso + gain < 0) {
            client.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        meso += gain;
        updateSingleStat(MapleStat.MESO, meso, enableActions);
        if (show) {
            client.sendPacket(MaplePacketCreator.showMesoGain(gain, inChat));
        }
    }

    public void controlMonster(MapleMonster monster, boolean aggro) {
        if (clone) {
            return;
        }
        monster.setController(this);
        controlled.add(monster);
        client.sendPacket(MobPacket.controlMonster(monster, false, aggro));
    }

    public void stopControllingMonster(MapleMonster monster) {
        if (clone) {
            return;
        }
        if (monster != null && controlled.contains(monster)) {
            controlled.remove(monster);
        }
    }

    //��������
    public void checkMonsterAggro(MapleMonster monster) {
        if (clone || monster == null) {
            return;
        }
        if (monster.getController() == this) {
            monster.setControllerHasAggro(true);
        } else {
            monster.switchController(this, true);
        }
    }

    public Set<MapleMonster> getControlled() {
        return controlled;
    }

    public int getControlledSize() {
        return controlled.size();
    }

    public int getAccountID() {
        return accountid;
    }

    public void mobKilled(final int id, final int skillID) {
        for (MapleQuestStatus q : quests.values()) {
            if (q.getStatus() != 1 || !q.hasMobKills()) {
                continue;
            }
            if (q.mobKilled(id, skillID)) {
                client.sendPacket(MaplePacketCreator.updateQuestMobKills(q));
                if (q.getQuest().canComplete(this, null)) {
                    client.sendPacket(MaplePacketCreator.getShowQuestCompletion(q.getQuest().getId()));
                }
            }
        }
    }

    public final List<MapleQuestStatus> getStartedQuests() {
        List<MapleQuestStatus> ret = new LinkedList<MapleQuestStatus>();
        for (MapleQuestStatus q : quests.values()) {
            if (q.getStatus() == 1 && !(q.isCustom())) {
                ret.add(q);
            }
        }
        return ret;
    }

    public final List<MapleQuestStatus> getCompletedQuests() {
        List<MapleQuestStatus> ret = new LinkedList<MapleQuestStatus>();
        for (MapleQuestStatus q : quests.values()) {
            if (q.getStatus() == 2 && !(q.isCustom())) {
                ret.add(q);
            }
        }
        return ret;
    }

    public Map<ISkill, SkillEntry> getSkills() {
        return Collections.unmodifiableMap(skills);
    }

    public byte getSkillLevel(final ISkill skill) {
        final SkillEntry ret = skills.get(skill);
        if (ret == null || ret.skillevel <= 0) {
            return 0;
        }
        return (byte) Math.min(skill.getMaxLevel(), ret.skillevel + (skill.isBeginnerSkill() ? 0 : stats.incAllskill));
    }

    public byte getMasterLevel(final int skill) {
        return getMasterLevel(SkillFactory.getSkill(skill));
    }

    public byte getMasterLevel(final ISkill skill) {
        final SkillEntry ret = skills.get(skill);
        if (ret == null) {
            return 0;
        }
        return ret.masterlevel;
    }

    public void levelUp() {
        //  getClient().StartWindow();
        if (GameConstants.isKOC(job)) {
            if (level <= 70) {
                remainingAp += 6;
            } else {
                remainingAp += 5;
            }
        } else {
            remainingAp += 5;
        }
        int maxhp = stats.getMaxHp();
        int maxmp = stats.getMaxMp();

        if (job == 0 || job == 1000 || job == 2000 || job == 2001 || job == 3000) { // Beginner
            maxhp += Randomizer.rand(12, 16);
            maxmp += Randomizer.rand(10, 12);
        } else if (job >= 100 && job <= 132) { // Warrior
            final ISkill improvingMaxHP = SkillFactory.getSkill(1000001);
            final int slevel = getSkillLevel(improvingMaxHP);
            if (slevel > 0) {
                maxhp += improvingMaxHP.getEffect(slevel).getX();
            }
            maxhp += Randomizer.rand(24, 28);
            maxmp += Randomizer.rand(4, 6);
        } else if (job >= 200 && job <= 232) { // Magician
            final ISkill improvingMaxMP = SkillFactory.getSkill(2000001);
            final int slevel = getSkillLevel(improvingMaxMP);
            if (slevel > 0) {
                maxmp += improvingMaxMP.getEffect(slevel).getX() * 2;
            }
            maxhp += Randomizer.rand(10, 14);
            maxmp += Randomizer.rand(22, 24);
        } else if (job >= 3200 && job <= 3212) { //battle mages get their own little neat thing
            maxhp += Randomizer.rand(20, 24);
            maxmp += Randomizer.rand(42, 44);
        } else if ((job >= 300 && job <= 322) || (job >= 400 && job <= 434) || (job >= 1300 && job <= 1311) || (job >= 1400 && job <= 1411) || (job >= 3300 && job <= 3312)) { // Bowman, Thief, Wind Breaker and Night Walker
            maxhp += Randomizer.rand(20, 24);
            maxmp += Randomizer.rand(14, 16);
        } else if ((job >= 500 && job <= 522) || (job >= 3500 && job <= 3512)) { // Pirate
            final ISkill improvingMaxHP = SkillFactory.getSkill(5100000);
            final int slevel = getSkillLevel(improvingMaxHP);
            if (slevel > 0) {
                maxhp += improvingMaxHP.getEffect(slevel).getX();
            }
            maxhp += Randomizer.rand(22, 26);
            maxmp += Randomizer.rand(18, 22);
        } else if (job >= 1100 && job <= 1111) { // Soul Master
            final ISkill improvingMaxHP = SkillFactory.getSkill(11000000);
            final int slevel = getSkillLevel(improvingMaxHP);
            if (slevel > 0) {
                maxhp += improvingMaxHP.getEffect(slevel).getX();
            }
            maxhp += Randomizer.rand(24, 28);
            maxmp += Randomizer.rand(4, 6);
        } else if (job >= 1200 && job <= 1211) { // Flame Wizard
            final ISkill improvingMaxMP = SkillFactory.getSkill(12000000);
            final int slevel = getSkillLevel(improvingMaxMP);
            if (slevel > 0) {
                maxmp += improvingMaxMP.getEffect(slevel).getX() * 2;
            }
            maxhp += Randomizer.rand(10, 14);
            maxmp += Randomizer.rand(22, 24);
        } else if (job >= 1500 && job <= 1512) { // Pirate
            final ISkill improvingMaxHP = SkillFactory.getSkill(15100000);
            final int slevel = getSkillLevel(improvingMaxHP);
            if (slevel > 0) {
                maxhp += improvingMaxHP.getEffect(slevel).getX();
            }
            maxhp += Randomizer.rand(22, 26);
            maxmp += Randomizer.rand(18, 22);
        } else if (job >= 2100 && job <= 2112) { // Aran
            maxhp += Randomizer.rand(50, 52);
            maxmp += Randomizer.rand(4, 6);
        } else if (job >= 2200 && job <= 2218) { // Evan
            maxhp += Randomizer.rand(12, 16);
            maxmp += Randomizer.rand(50, 52);
        } else { // GameMaster
            maxhp += Randomizer.rand(50, 100);
            maxmp += Randomizer.rand(50, 100);
        }
        if (F().get(FM("��׳����")) != null) {
            if (F().get(FM("��׳����")) > 0) {
                int x = F().get(FM("��׳����"));
                maxhp += x;
                dropMessage(5, "[��׳����]: ������� " + F().get(FM("��׳����")) + " ����� MaxHP��");
            }
        }
        if (F().get(FM("��׳ħ��")) != null) {
            if (F().get(FM("��׳ħ��")) > 0) {
                int x = F().get(FM("��׳ħ��"));
                maxmp += x;
                dropMessage(5, "[��׳ħ��]: ������� " + F().get(FM("��׳ħ��")) + " ����� MaxMP��");
            }
        }
        if (F().get(FM("��׳����")) != null) {
            if (F().get(FM("��׳����")) > 0) {
                int x = F().get(FM("��׳����"));

                maxhp += x;
                maxmp += x;
                dropMessage(5, "[��׳����]: ������� " + F().get(FM("��׳����")) + " ����� MaxMP��MaxHP��");
            }
        }

        maxmp += stats.getTotalInt() / 10;
        exp -= GameConstants.getExpNeededForLevel(level);
        level += 1;
        if (F().get(FM("��������")) != null) {
            double a = Math.ceil(Math.random() * 100);
            if (a <= F().get(FM("��������"))) {
                level += 1;
            }
        }
        int level = getLevel();

        int ������Ѷ = gui.Start.ConfigValuesMap.get("������Ѷ����");
        if (������Ѷ <= 0) {
            if (!isGM()) {
                if (level >= 1) {
                    String ��ߵȼ���� = NPCConversationManager.��ȡ��ߵȼ��������();
                    final StringBuilder sb = new StringBuilder("[������Ѷ]: ��ϲ ");
                    final IItem medal = getInventory(MapleInventoryType.EQUIPPED).getItem((byte) -26);
                    if (guildid > 0) {
                        String guildName = NPCConversationManager.��ȡ��������(guildid);
                        sb.append(" <");
                        sb.append(guildName);
                        sb.append("> ����� ");
                    }

                    sb.append(getName());
                    sb.append(" �ﵽ ").append(level).append(" �� ");

                    if (medal != null) { // Medal
                        sb.append("<");
                        sb.append(MapleItemInformationProvider.getInstance().getName(medal.getItemId()));
                        sb.append(">");
                    }
                    World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, sb.toString()));
                }
            }
        }
        maxhp = (short) Math.min(30000, Math.abs(maxhp));
        maxmp = (short) Math.min(30000, Math.abs(maxmp));
        maxhp = Math.min(30000, maxhp);
        maxmp = Math.min(30000, maxmp);
        final List<Pair<MapleStat, Integer>> statup = new ArrayList<>(8);

        statup.add(new Pair(MapleStat.AVAILABLEAP, Integer.valueOf(this.remainingAp)));
        statup.add(new Pair<>(MapleStat.MAXHP, maxhp));
        statup.add(new Pair<>(MapleStat.MAXMP, maxmp));
        statup.add(new Pair<>(MapleStat.HP, maxhp));
        statup.add(new Pair<>(MapleStat.MP, maxmp));
        statup.add(new Pair<>(MapleStat.EXP, exp));
        statup.add(new Pair<>(MapleStat.LEVEL, (int) level));

        if (isGM() || (job != 0 && job != 1000 && job != 2000 && job != 2001 && job != 3000)) {
            remainingSp[GameConstants.getSkillBook(this.job)] += 3;//���ܵ�����
            client.sendPacket(MaplePacketCreator.updateSp(this, false));
        } else if (level <= 10) {
            stats.setStr((short) (stats.getStr() + remainingAp));
            remainingAp = 0;
            statup.add(new Pair<>(MapleStat.STR, (int) stats.getStr()));
        }

        statup.add(new Pair<MapleStat, Integer>(MapleStat.AVAILABLEAP, (int) remainingAp));
        stats.setMaxHp((short) maxhp);
        stats.setMaxMp((short) maxmp);
        stats.setHp((short) maxhp);
        stats.setMp((short) maxmp);
        client.sendPacket(MaplePacketCreator.updatePlayerStats(statup, getJob()));
        //������Ч
        map.broadcastMessage(this, MaplePacketCreator.showForeignEffect(getId(), 0), false);
        stats.recalcLocalStats();
        silentPartyUpdate();
        guildUpdate();
        //familyUpdate();
        //�ȼ��ɾ�();
        //����ѧԺ����();

        /*if (getFamilyId() > 0) {
            //���Լ���
            setCurrentRep2(getCurrentRep() + (300));
        }*/
        if (level == 160) {
            dropMessage(5, "��ϲ���������֮�����ɲ鿴�й�����֮������塣");
        }
        saveToDB(false, false);
    }

    public static void ѧϰ����ģʽ(String a, int b) {
        int ID = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT `id` FROM jiezoudashi  ORDER BY `id` DESC LIMIT 1");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String SN = rs.getString("id");
                    int sns = Integer.parseInt(SN);
                    sns++;
                    ID = sns;
                }
            }
        } catch (SQLException ex) {

        }
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO jiezoudashi ( id,name,Val ) VALUES ( ? ,?,?)")) {
            ps.setInt(1, ID);
            ps.setString(2, a);
            ps.setInt(3, b);
            //ps.executeUpdate();
        } catch (SQLException ex) {

        }
    }

    public void ����ѧԺ����() {
        for (ChannelServer cserv1 : ChannelServer.getAllInstances()) {
            for (MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                if (mch == null) {
                    continue;
                }
                //��ѧԺԺ����
                if (mch.getId() == getFamilyId()) {
                    mch.setCurrentRep2(mch.getCurrentRep() + (500));
                }
                //�������Ǽ�
                if (mch.getId() == getSeniorId()) {
                    mch.setCurrentRep2(mch.getCurrentRep() + (1000));
                }
                mch.saveToDB(false, false);
            }
        }
    }

    public void �ȼ��ɾ�() {
        dropMessage(5, "[�ɾʹ��]: ��ϲ�� " + CurrentReadable_Time() + " �ﵽ " + level + " ����");
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Upgrade_career ( id2,shijian,name) VALUES ( ?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, CurrentReadable_Time());
            ps.setString(3, "�ȼ��ﵽ - Lv." + level);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("�@�e�`!!55" + sql);
        }
    }

    public void changeKeybinding(int key, byte type, int action) {
        if (type != 0) {
            keylayout.Layout().put(Integer.valueOf(key), new Pair<Byte, Integer>(type, action));
        } else {
            keylayout.Layout().remove(Integer.valueOf(key));
        }
    }

    public void sendMacros() {
        for (int i = 0; i < 5; i++) {
            if (skillMacros[i] != null) {
                client.sendPacket(MaplePacketCreator.getMacros(skillMacros));
                break;
            }
        }
    }

    public void updateMacros(int position, SkillMacro updateMacro) {
        skillMacros[position] = updateMacro;
    }

    public final SkillMacro[] getMacros() {
        return skillMacros;
    }

    public void tempban(String reason, Calendar duration, int greason, boolean IPMac) {
        if (IPMac) {
            client.banMacs();
        }

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO ipbans VALUES (DEFAULT, ?)");
            ps.setString(1, client.getSession().remoteAddress().toString().split(":")[0]);
            ps.execute();
            ps.close();

            client.getSession().close();

            ps = con.prepareStatement("UPDATE accounts SET tempban = ?, banreason = ?, greason = ? WHERE id = ?");
            Timestamp TS = new Timestamp(duration.getTimeInMillis());
            ps.setTimestamp(1, TS);
            ps.setString(2, reason);
            ps.setInt(3, greason);
            ps.setInt(4, accountid);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            System.err.println("Error while tempbanning" + ex);
        }

    }

    public final boolean ban(String reason, boolean IPMac, boolean autoban, boolean hellban) {
        hellban = false;
        if (lastmonthfameids == null) {
            throw new RuntimeException("Trying to ban a non-loaded character (testhack)");
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE accounts SET banned = ?, banreason = ? WHERE id = ?");
            ps.setInt(1, autoban ? 2 : 1);
            ps.setString(2, reason);
            ps.setInt(3, accountid);
            ps.execute();
            ps.close();

            client.banMacs();
//            String ip = client.getSessionIPAddress();
//            if (!"/127.0.0.1".equals(ip)) {
//                ps = con.prepareStatement("INSERT INTO ipbans VALUES (DEFAULT, ?)");
//                ps.setString(1, ip);
//                ps.execute();
//                ps.close();
//            }

            if (hellban) {
                PreparedStatement psa = con.prepareStatement("SELECT * FROM accounts WHERE id = ?");
                psa.setInt(1, accountid);
                ResultSet rsa = psa.executeQuery();
                if (rsa.next()) {
                    PreparedStatement pss = con.prepareStatement("UPDATE accounts SET banned = ?, banreason = ? WHERE email = ? ");
                    pss.setInt(1, autoban ? 2 : 1);
                    pss.setString(2, reason);
                    pss.setString(3, rsa.getString("email"));
                    pss.execute();
                    pss.close();
                }
                rsa.close();
                psa.close();
            }

        } catch (SQLException ex) {
            System.err.println("Error while banning" + ex);
            return false;
        }
        client.getSession().close();
        return true;
    }

    public static boolean ban(String id, String reason, boolean accountId, int gmlevel, boolean hellban) {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps;

            if (id.matches("/[0-9]{1,3}\\..*")) {
                if (id != "/127.0.0.1") {
//                    ps = con.prepareStatement("INSERT INTO ipbans VALUES (DEFAULT, ?)");
//                    ps.setString(1, id);
//                    ps.execute();
//                    ps.close();
                }
                return true;
            }
            if (accountId) {
                ps = con.prepareStatement("SELECT id FROM accounts WHERE name = ?");
            } else {
                ps = con.prepareStatement("SELECT accountid FROM characters WHERE name = ?");
            }
            boolean ret = false;
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int z = rs.getInt(1);
                PreparedStatement psb = con.prepareStatement("UPDATE accounts SET banned = 1, banreason = ? WHERE id = ? AND gm < ?");
                psb.setString(1, reason);
                psb.setInt(2, z);
                psb.setInt(3, gmlevel);
                psb.execute();
                psb.close();

                if (gmlevel > 100) { //admin ban
                    PreparedStatement psa = con.prepareStatement("SELECT * FROM accounts WHERE id = ?");
                    psa.setInt(1, z);
                    ResultSet rsa = psa.executeQuery();
                    if (rsa.next()) {
                        String sessionIP = rsa.getString("sessionIP");
                        if (sessionIP != null && sessionIP.matches("/[0-9]{1,3}\\..*")) {
                            PreparedStatement psz = con.prepareStatement("INSERT INTO ipbans VALUES (DEFAULT, ?)");
                            psz.setString(1, sessionIP);
                            psz.execute();
                            psz.close();
                        }
                        if (rsa.getString("macs") != null) {
                            String[] macData = rsa.getString("macs").split(", ");
                            if (macData.length > 0) {
                                MapleClient.banMacs(macData);
                            }
                        }
                        if (hellban) {
                            PreparedStatement pss = con.prepareStatement("UPDATE accounts SET banned = 1, banreason = ? WHERE email = ?" + (sessionIP == null ? "" : " OR SessionIP = ?"));
                            pss.setString(1, reason);
                            pss.setString(2, rsa.getString("email"));
                            if (sessionIP != null) {
                                pss.setString(3, sessionIP);
                            }
                            pss.execute();
                            pss.close();
                        }
                    }
                    rsa.close();
                    psa.close();
                }
                ret = true;
            }
            rs.close();
            ps.close();
            return ret;
        } catch (SQLException ex) {
            System.err.println("Error while banning" + ex);
        }
        return false;
    }

    /**
     * Oid of players is always = the cid
     */
    @Override
    public int getObjectId() {
        return getId();
    }

    /**
     * Throws unsupported operation exception, oid of players is read only
     */
    @Override
    public void setObjectId(int id) {
        throw new UnsupportedOperationException();
    }

    public MapleStorage getStorage() {
        return storage;
    }

    public void addVisibleMapObject(MapleMapObject mo) {
        if (clone) {
            return;
        }
        visibleMapObjectsLock.writeLock().lock();
        try {
            visibleMapObjects.add(mo);
        } finally {
            visibleMapObjectsLock.writeLock().unlock();
        }
    }

    public void removeVisibleMapObject(MapleMapObject mo) {
        if (clone) {
            return;
        }
        visibleMapObjectsLock.writeLock().lock();
        try {
            visibleMapObjects.remove(mo);
        } finally {
            visibleMapObjectsLock.writeLock().unlock();
        }
    }

    public boolean isMapObjectVisible(MapleMapObject mo) {
        visibleMapObjectsLock.readLock().lock();
        try {
            return !clone && visibleMapObjects.contains(mo);
        } finally {
            visibleMapObjectsLock.readLock().unlock();
        }
    }

    public Collection<MapleMapObject> getAndWriteLockVisibleMapObjects() {
        visibleMapObjectsLock.writeLock().lock();
        return visibleMapObjects;
    }

    public void unlockWriteVisibleMapObjects() {
        visibleMapObjectsLock.writeLock().unlock();
    }

    public boolean isAlive() {
        return stats.getHp() > 0;
    }

    @Override
    public void sendDestroyData(MapleClient client) {
        client.sendPacket(MaplePacketCreator.removePlayerFromMap(this.getObjectId()));
        for (final WeakReference<MapleCharacter> chr : clones) {
            if (chr.get() != null) {
                chr.get().sendDestroyData(client);
            }
        }
    }

    @Override
    public void sendSpawnData(MapleClient client) {
        if (client.getPlayer().allowedToTarget(this)) {
            client.sendPacket(MaplePacketCreator.spawnPlayerMapobject(this));

            for (final MaplePet pet : pets) {
                if (pet.getSummoned()) {
                    client.sendPacket(PetPacket.showPet(this, pet, false, false));
                    client.sendPacket(PetPacket.petStatUpdate(this));

                }
            }
            for (final WeakReference<MapleCharacter> chr : clones) {
                if (chr.get() != null) {
                    chr.get().sendSpawnData(client);
                }
            }
            if (summons != null) {
                for (final MapleSummon summon : summons.values()) {
                    client.sendPacket(MaplePacketCreator.spawnSummon(summon, false));
                }
            }
            if (followid > 0) {
                //   client.sendPacket(MaplePacketCreator.followEffect(followinitiator ? id : followid, followinitiator ? followid : id, null));
            }
        }
    }

    public final void equipChanged() {
        map.broadcastMessage(this, MaplePacketCreator.updateCharLook(this), false);
        map.broadcastMessage(MaplePacketCreator.loveEffect());
        stats.recalcLocalStats();
        if (getMessenger() != null) {
            World.Messenger.updateMessenger(getMessenger().getId(), getName(), client.getChannel());
        }
        saveToDB(false, false);
    }

    public final MaplePet getPet(final int index) {
        byte count = 0;
        for (final MaplePet pet : pets) {
            if (pet.getSummoned()) {
                if (count == index) {
                    return pet;
                }
                count++;
            }
        }
        return null;
    }

    public void addPet(final MaplePet pet) {
        if (pets.contains(pet)) {
            pets.remove(pet);
        }
        pets.add(pet);
        // So that the pet will be at the last
        // Pet index logic :(
    }

    public void removePet(MaplePet pet) {
        pet.setSummoned(0);
        pets.remove(pet);
    }

    public final List<MaplePet> getSummonedPets() {
        List<MaplePet> ret = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ret.add(null);
        }
        for (final MaplePet pet : pets) {
            if (pet != null && pet.getSummoned()) {
                int index = pet.getSummonedValue() - 1;
                ret.remove(index);
                ret.add(index, pet);
            }
        }
        List<Integer> nullArr = new ArrayList();
        nullArr.add(null);
        ret.removeAll(nullArr);
        return ret;
    }

    public final MaplePet getSummonedPet(final int index) {
        for (final MaplePet pet : getSummonedPets()) {
            if (pet.getSummonedValue() - 1 == index) {
                return pet;
            }
        }
        return null;
    }

    public final void shiftPetsRight() {
        List<MaplePet> petsz = getSummonedPets();
        if (petsz.size() >= 3 || petsz.size() < 1) {
            return;
        } else {
            boolean[] indexBool = new boolean[]{false, false, false};
            for (int i = 0; i < 3; i++) {
                for (MaplePet p : petsz) {
                    if (p.getSummonedValue() == i + 1) {
                        indexBool[i] = true;
                    }
                }
            }
            if (petsz.size() > 1) {
                if (!indexBool[2]) {
                    petsz.get(0).setSummoned(2);
                    petsz.get(1).setSummoned(3);
                } else if (!indexBool[1]) {
                    petsz.get(0).setSummoned(2);
                }
            } else if (indexBool[0]) {
                petsz.get(0).setSummoned(2);
            }
        }
    }

    public final int getPetSlotNext() {
        List<MaplePet> petsz = getSummonedPets();
        int index = 0;
        if (petsz.size() >= 3) {
            unequipPet(getSummonedPet(0), false);
        } else {
            boolean[] indexBool = new boolean[]{false, false, false};
            for (int i = 0; i < 3; i++) {
                for (MaplePet p : petsz) {
                    if (p.getSummonedValue() == i + 1) {
                        indexBool[i] = true;
                    }
                }
            }
            for (boolean b : indexBool) {
                if (!b) {
                    break;
                }
                index++;
            }
            index = Math.min(index, 2);
            for (MaplePet p : petsz) {
                if (p.getSummonedValue() == index + 1) {
                    unequipPet(p, false);
                }
            }
        }
        return index;
    }

    public final byte getPetIndex(final MaplePet petz) {
        return (byte) Math.max(-1, petz.getSummonedValue() - 1);
    }

    public final byte getPetIndex(final int petId) {
        for (final MaplePet pet : getSummonedPets()) {
            if (pet.getUniqueId() == petId) {
                return (byte) Math.max(-1, pet.getSummonedValue() - 1);
            }
        }
        return -1;
    }

    public final byte getPetIndexById(final int petId) {
        for (final MaplePet pet : getSummonedPets()) {
            if (pet.getPetItemId() == petId) {
                return (byte) Math.max(-1, pet.getSummonedValue() - 1);
            }
        }
        return -1;
    }

    public final List<MaplePet> getPets() {
        return pets;
    }

    public final void unequipAllPets() {
        for (final MaplePet pet : getSummonedPets()) {
            unequipPet(pet, false);
        }
    }

    public void unequipPet(MaplePet pet, boolean hunger) {
        if (pet.getSummoned()) {
            pet.saveToDb();

            List<MaplePet> summonedPets = getSummonedPets();
            if (summonedPets.contains(pet)) {
                summonedPets.remove(pet);
                int i = 1;
                for (MaplePet p : summonedPets) {
                    if (p == null) {
                        continue;
                    }
                    p.setSummoned(i);
                    i++;
                }
            }
            if (map != null) {
                map.broadcastMessage(this, PetPacket.showPet(this, pet, true, hunger), true);
            }
            //List<Pair<MapleStat, Integer>> stats = new ArrayList<Pair<MapleStat, Integer>>();
            //stats.add(new Pair<MapleStat, Integer>(MapleStat.PET, Integer.valueOf(0)));
            pet.setSummoned(0);
            client.sendPacket(PetPacket.petStatUpdate(this));
            client.sendPacket(MaplePacketCreator.enableActions());
        }
    }

    public final long getLastFameTime() {
        return lastfametime;
    }

    public final List<Integer> getFamedCharacters() {
        return lastmonthfameids;
    }

    public FameStatus canGiveFame(MapleCharacter from) {
        if (lastfametime >= System.currentTimeMillis() - 60 * 60 * 24 * 1000) {
            return FameStatus.NOT_TODAY;
        } else if (from == null || lastmonthfameids == null || lastmonthfameids.contains(Integer.valueOf(from.getId()))) {
            return FameStatus.NOT_THIS_MONTH;
        }
        return FameStatus.OK;
    }

    public void hasGivenFame(MapleCharacter to) {
        lastfametime = System.currentTimeMillis();
        lastmonthfameids.add(Integer.valueOf(to.getId()));
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO famelog (characterid, characterid_to) VALUES (?, ?)");
            ps.setInt(1, getId());
            ps.setInt(2, to.getId());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.err.println("ERROR writing famelog for char " + getName() + " to " + to.getName() + e);
        }
    }

    public final MapleKeyLayout getKeyLayout() {
        return this.keylayout;
    }

    public MapleParty getParty() {
        return party;
    }

    public int getPartyId() {
        return (party != null ? party.getId() : -1);
    }

    public byte getWorld() {
        return world;
    }

    public void setWorld(byte world) {
        this.world = world;
    }

    public void setParty(MapleParty party) {
        this.party = party;
    }

    public MapleTrade getTrade() {
        return trade;
    }

    public void setTrade(MapleTrade trade) {
        this.trade = trade;
    }

    public EventInstanceManager getEventInstance() {
        return eventInstance;
    }

    public void setEventInstance(EventInstanceManager eventInstance) {
        this.eventInstance = eventInstance;
    }

    public void addDoor(MapleDoor door) {
        doors.add(door);
    }

    public void clearDoors() {
        doors.clear();
    }

    public List<MapleDoor> getDoors() {
        return new ArrayList<MapleDoor>(doors);
    }

    public void setSmega() {
        if (smega) {
            smega = false;
            dropMessage(5, "You have set megaphone to disabled mode");
        } else {
            smega = true;
            dropMessage(5, "You have set megaphone to enabled mode");
        }
    }

    public boolean getSmega() {
        return smega;
    }

    public Map<Integer, MapleSummon> getSummons() {
        return summons;
    }

    public void unlockSummonsReadLock() {
    }

    public int getChair() {
        return chair;
    }

    public int getItemEffect() {
        return itemEffect;
    }

    public void setChair(int chair) {
        this.chair = chair;
        stats.relocHeal();
    }

    public void setItemEffect(int itemEffect) {
        this.itemEffect = itemEffect;
    }

    @Override
    public MapleMapObjectType getType() {
        return MapleMapObjectType.PLAYER;
    }

    public int getFamilyId() {
        if (mfc == null) {
            return 0;
        }
        return mfc.getFamilyId();
    }

    public int getSeniorId() {
        if (mfc == null) {
            return 0;
        }
        return mfc.getSeniorId();
    }

    public int getJunior1() {
        if (mfc == null) {
            return 0;
        }
        return mfc.getJunior1();
    }

    public int getJunior2() {
        if (mfc == null) {
            return 0;
        }
        return mfc.getJunior2();
    }

    public int getCurrentRep() {
        return currentrep;
    }

    public int getTotalRep() {
        return totalrep;
    }

    public void setCurrentRep(int _rank) {
        currentrep = _rank;
        if (mfc != null) {
            mfc.setCurrentRep(_rank);
        }
    }

    public void setCurrentRep2(int cr) {
        this.currentrep = cr;
    }

    public void setTotalRep(int _rank) {
        totalrep = _rank;
        if (mfc != null) {
            mfc.setTotalRep(_rank);
        }
    }

    public int getGuildId() {
        return guildid;
    }

    public byte getGuildRank() {
        return guildrank;
    }

    public void setGuildId(int _id) {
        guildid = _id;
        if (guildid > 0) {
            if (mgc == null) {
                mgc = new MapleGuildCharacter(this);

            } else {
                mgc.setGuildId(guildid);
            }
        } else {
            mgc = null;
        }
    }

    public void setGuildRank(byte _rank) {
        guildrank = _rank;
        if (mgc != null) {
            mgc.setGuildRank(_rank);
        }
    }

    public MapleGuildCharacter getMGC() {
        return mgc;
    }

    public void setAllianceRank(byte rank) {
        allianceRank = rank;
        if (mgc != null) {
            mgc.setAllianceRank(rank);
        }
    }

    public byte getAllianceRank() {
        return allianceRank;
    }

    public MapleGuild getGuild() {
        if (getGuildId() <= 0) {
            return null;
        }
        return World.Guild.getGuild(getGuildId());
    }

    public void guildUpdate() {
        if (guildid <= 0) {
            return;
        }
        mgc.setLevel((short) level);
        mgc.setJobId(job);
        World.Guild.memberLevelJobUpdate(mgc);
    }

    public void saveGuildStatus() {
        MapleGuild.setOfflineGuildStatus(guildid, guildrank, allianceRank, id);
    }

    public void familyUpdate() {
        if (mfc == null) {
            return;
        }
        World.Family.memberFamilyUpdate(mfc, this);
    }

    public void saveFamilyStatus() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE characters SET familyid = ?, seniorid = ?, junior1 = ?, junior2 = ? WHERE id = ?");
            if (mfc == null) {
                ps.setInt(1, 0);
                ps.setInt(2, 0);
                ps.setInt(3, 0);
                ps.setInt(4, 0);
            } else {
                ps.setInt(1, mfc.getFamilyId());
                ps.setInt(2, mfc.getSeniorId());
                ps.setInt(3, mfc.getJunior1());
                ps.setInt(4, mfc.getJunior2());
            }
            ps.setInt(5, id);
            ps.execute();
            ps.close();
        } catch (SQLException se) {
            System.out.println("SQLException: " + se.getLocalizedMessage());
            se.printStackTrace();
        }
        //MapleFamily.setOfflineFamilyStatus(familyid, seniorid, junior1, junior2, currentrep, totalrep, id);
    }

    public void modifyCSPoints(int type, int quantity) {
        modifyCSPoints(type, quantity, false);
    }

    public void dropMessage(String message) {
        dropMessage(2, message);
    }

    public void worldMessage(String text) {//��������
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(2, text));
    }

    public void modifyCSPoints(int type, int quantity, boolean show) {

        switch (type) {
            case 1:
                if (acash + quantity < 0) {
                    if (show) {
                        dropMessage(5, "��ĵ���Ѿ�����");
                    }
                    return;
                }
                acash += quantity;
                break;
            case 2:
                if (maplepoints + quantity < 0) {
                    if (show) {
                        dropMessage(5, "��ĵ��þ��Ѿ�����.");
                    }
                    return;
                }
                maplepoints += quantity;
                break;
            default:
                break;
        }
        if (show && quantity != 0) {
            dropMessage(5, "���Ѿ� " + (quantity > 0 ? "��� " : "ʹ�� ") + quantity + (type == 1 ? " ���." : " ���þ�."));
            //client.sendPacket(MaplePacketCreator.showSpecialEffect(19));
        }
    }

    public int getCSPoints(int type) {
        switch (type) {
            case 1:
                return acash;
            case 2:
                return maplepoints;
            case 3:
                return beans;
            default:
                return 0;
        }
    }

    //�Ƿ�װ��
    public final boolean hasEquipped(int itemid) {
        return inventory[MapleInventoryType.EQUIPPED.ordinal()].countById(itemid) >= 1;
    }

    public final boolean haveItem(int itemid, int quantity, boolean checkEquipped, boolean greaterOrEquals) {
        final MapleInventoryType type = GameConstants.getInventoryType(itemid);
        int possesed = inventory[type.ordinal()].countById(itemid);
        if (checkEquipped && type == MapleInventoryType.EQUIP) {
            possesed += inventory[MapleInventoryType.EQUIPPED.ordinal()].countById(itemid);
        }
        if (greaterOrEquals) {
            return possesed >= quantity;
        } else {
            return possesed == quantity;
        }
    }

    public final int �ж���Ʒ����(int itemid) {
        final MapleInventoryType type = GameConstants.getInventoryType(itemid);
        int quantity = inventory[type.ordinal()].countById(itemid);
        return quantity;
    }

    public final boolean haveItem(int itemid, int quantity) {
        return haveItem(itemid, quantity, true, true);
    }

    public final boolean haveItem(int itemid) {
        return haveItem(itemid, 1, true, true);
    }

    public void maxAllSkills() {
        MapleDataProvider dataProvider = MapleDataProviderFactory.getDataProvider(new File(System.getProperty("wz") + "/" + "String.wz"));
        MapleData skilldData = dataProvider.getData("Skill.img");
        for (MapleData skill_ : skilldData.getChildren()) {
            try {
                Skill skill = (Skill) SkillFactory.getSkill1(Integer.parseInt(skill_.getName()));
                if (level >= 0) {
                    changeSkillLevel(skill, skill.getMaxLevel(), (byte) skill.getMaxLevel());
                }
            } catch (NumberFormatException nfe) {
                break;
            } catch (NullPointerException npe) {
                continue;
            }
        }
    }

    public void setAPQScore(int score) {
        this.APQScore = score;
    }

    public int getAPQScore() {
        return APQScore;
    }

    public long getLasttime() {
        return this.lasttime;
    }

    public void setLasttime(long lasttime) {
        this.lasttime = lasttime;
    }

    public long getCurrenttime() {
        return this.currenttime;
    }

    public void setCurrenttime(long currenttime) {
        this.currenttime = currenttime;
    }

    public Object getSession() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Gaincharacterz(String Name, int Channale, int Piot) {
        try {
            int ret = Getcharacterz(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO characterz (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);

                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e);
                    }
                }
            }
            ret += Piot;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE characterz SET `Point` = ? WHERE Name = ? and channel = ?");
            ps.setInt(1, ret);
            ps.setString(2, Name);
            ps.setInt(3, Channale);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("Getcharacterz!!55" + sql);
        }
    }

    public void Gainaccoun(String Name, int Channale, int Piot) {
        try {
            int ret = Getaccoun(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO accoun (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);

                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e);
                    }
                }
            }
            ret += Piot;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE accoun SET `Point` = ? WHERE Name = ? and channel = ?");
            ps.setInt(1, ret);
            ps.setString(2, Name);
            ps.setInt(3, Channale);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("Getaccoun!!55" + sql);
        }
    }

    public int Getaccoun(String Name, int Channale) {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM accoun WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public int Getcharacterz(String Name, int Channale) {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characterz WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public void Gainrobot(String Name, int Channale, int Piot) {
        try {
            int ret = Getrobot(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO robot (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);

                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e);
                    }
                }
            }
            ret += Piot;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE robot SET `Point` = ? WHERE Name = ? and channel = ?");
            ps.setInt(1, ret);
            ps.setString(2, Name);
            ps.setInt(3, Channale);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("Getrobot!!55" + sql);
        }
    }

    public int Getrobot(String Name, int Channale) {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM robot WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public void Gaincharactera(String Name, int Channale, int Piot) {
        try {
            int ret = Getcharactera(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO charactera (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);

                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e);
                    }
                }
            }
            ret += Piot;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE charactera SET `Point` = ? WHERE Name = ? and channel = ?");
            ps.setInt(1, ret);
            ps.setString(2, Name);
            ps.setInt(3, Channale);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("Getcharactera!!55" + sql);
        }
    }

    public int Getcharactera(String Name, int Channale) {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM charactera WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public void Gainpersonal(String Name, int Channale, int Piot) {
        try {
            int ret = Getpersonal(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO personal (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);
                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e);
                    }
                }
            }
            ret += Piot;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE personal SET `Point` = ? WHERE Name = ? and channel = ?");
            ps.setInt(1, ret);
            ps.setString(2, Name);
            ps.setInt(3, Channale);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("personal!!55" + sql);
        }
    }

    public int Getpersonal(String Name, int Channale) {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM personal WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;

    }

    public int questItemId = 0, questItemIdd = 0;

    public void setItemQuestItemId(int itemId) {
        questItemId = itemId;
    }

    public int getItemQuestItemId() {
        return questItemId;
    }

    public void setItemQuestId(int itemId) {
        questItemIdd = itemId;
    }

    public int getItemQuestId() {
        return questItemIdd;

    }

    public static enum FameStatus {

        OK, NOT_TODAY, NOT_THIS_MONTH
    }

    public byte getBuddyCapacity() {
        return buddylist.getCapacity();
    }

    public void setBuddyCapacity(byte capacity) {
        buddylist.setCapacity(capacity);
        client.sendPacket(MaplePacketCreator.updateBuddyCapacity(capacity));
    }

    public MapleMessenger getMessenger() {
        return messenger;
    }

    public void setMessenger(MapleMessenger messenger) {
        this.messenger = messenger;
    }

    public void addCooldown(int skillId, long startTime, long length) {
        coolDowns.put(Integer.valueOf(skillId), new MapleCoolDownValueHolder(skillId, startTime, length));
    }

    public void removeCooldown(int skillId) {
        if (coolDowns.containsKey(Integer.valueOf(skillId))) {
            coolDowns.remove(Integer.valueOf(skillId));
        }
    }

    public boolean skillisCooling(int skillId) {
        return coolDowns.containsKey(Integer.valueOf(skillId));
    }

    public void giveCoolDowns(final int skillid, long starttime, long length) {
        addCooldown(skillid, starttime, length);
    }

    public void giveCoolDowns(final List<MapleCoolDownValueHolder> cooldowns) {
        int time;
        if (cooldowns != null) {
            for (MapleCoolDownValueHolder cooldown : cooldowns) {
                coolDowns.put(cooldown.skillId, cooldown);
            }
        } else {
            try {
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT SkillID,StartTime,length FROM skills_cooldowns WHERE charid = ?");
                ps.setInt(1, getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getLong("length") + rs.getLong("StartTime") - System.currentTimeMillis() <= 0) {
                        continue;
                    }
                    giveCoolDowns(rs.getInt("SkillID"), rs.getLong("StartTime"), rs.getLong("length"));
                }
                ps.close();
                rs.close();
                deleteWhereCharacterId(con, "DELETE FROM skills_cooldowns WHERE charid = ?");

            } catch (SQLException e) {
                System.err.println("Error while retriving cooldown from SQL storage");
            }
        }
    }

    public List<MapleCoolDownValueHolder> getCooldowns() {
        return new ArrayList<MapleCoolDownValueHolder>(coolDowns.values());
    }

    public final List<MapleDiseaseValueHolder> getAllDiseases() {
        return new ArrayList<MapleDiseaseValueHolder>(diseases.values());
    }

    public final boolean hasDisease(final MapleDisease dis) {
        return diseases.keySet().contains(dis);
    }

    public void giveDebuff(final MapleDisease disease, MobSkill skill) {
        giveDebuff(disease, skill.getX(), skill.getDuration(), skill.getSkillId(), skill.getSkillLevel());
    }

    //����������BUFF
    public void giveDebuff(final MapleDisease disease, int x, long duration, int skillid, int level) {
        final List<Pair<MapleDisease, Integer>> debuff = Collections.singletonList(new Pair<MapleDisease, Integer>(disease, Integer.valueOf(x)));
        if (!hasDisease(disease) && diseases.size() < 2) {
            if (!(disease == MapleDisease.SEDUCE || disease == MapleDisease.STUN)) {
                if (isActiveBuffedValue(2321005)) {
                    return;
                }
            }
            client.sendPacket(MaplePacketCreator.giveDebuff(debuff, skillid, level, (int) duration));
            map.broadcastMessage(this, MaplePacketCreator.giveForeignDebuff(id, debuff, skillid, level), false);
            if (F().get(FM("�쳣����")) != null) {
                double r1 = Math.ceil(Math.random() * 500);
                int ���� = F().get(FM("�쳣����"));
                if (r1 <= ����) {
                    diseases.put(disease, new MapleDiseaseValueHolder(disease, System.currentTimeMillis(), duration / 100));
                    dropMessage(5, "�쳣����");
                    return;
                }
            }
            if (F().get(FM("�쳣����")) != null) {
                int ���� = F().get(FM("�쳣����"));
                if (���� > 65) {
                    ���� = 65;
                }
                diseases.put(disease, new MapleDiseaseValueHolder(disease, System.currentTimeMillis(), duration / 100 * ����));
            } else {
                diseases.put(disease, new MapleDiseaseValueHolder(disease, System.currentTimeMillis(), duration));
            }
        }
    }

    public final void giveSilentDebuff(final List<MapleDiseaseValueHolder> ld) {
        if (ld != null) {
            for (final MapleDiseaseValueHolder disease : ld) {
                diseases.put(disease.disease, disease);
            }
        }
    }

    public void dispelDebuff(MapleDisease debuff) {
        if (hasDisease(debuff)) {
            long mask = debuff.getValue();
            boolean first = debuff.isFirst();
            client.sendPacket(MaplePacketCreator.cancelDebuff(mask, first));
            map.broadcastMessage(this, MaplePacketCreator.cancelForeignDebuff(id, mask, first), false);

            diseases.remove(debuff);
        }
    }

    public void dispelDebuffs() {
        dispelDebuff(MapleDisease.CURSE);
        dispelDebuff(MapleDisease.DARKNESS);
        dispelDebuff(MapleDisease.POISON);
        dispelDebuff(MapleDisease.SEAL);
        dispelDebuff(MapleDisease.WEAKEN);
    }

    public void cancelAllDebuffs() {
        diseases.clear();
    }

    public void setLevel(final short level) {
        this.level = (short) (level - 1);
    }

    public void sendNote(String to, String msg) {
        sendNote(to, msg, 0);
    }

    public void sendNote(String to, String msg, int fame) {
        MapleCharacterUtil.sendNote(to, getName(), msg, fame);
    }

    public void showNote() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM notes WHERE `to`=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, getName());
            ResultSet rs = ps.executeQuery();
            rs.last();
            int count = rs.getRow();
            rs.first();
            client.sendPacket(MTSCSPacket.showNotes(rs, count));
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Unable to show note" + e);
        }
    }

    public void deleteNote(int id, int fame) {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT gift FROM notes WHERE `id`=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("gift") == fame && fame > 0) { //not exploited! hurray
                    addFame(fame);
                    updateSingleStat(MapleStat.FAME, getFame());
                    client.sendPacket(MaplePacketCreator.getShowFameGain(fame));
                }
            }
            rs.close();
            ps.close();
            ps = con.prepareStatement("DELETE FROM notes WHERE `id`=?");
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Unable to delete note" + e);
        }
    }

    public void mulung_EnergyModify(boolean inc) {
        if (inc) {
            if (mulung_energy + 100 > 10000) {
                mulung_energy = 10000;
            } else {
                mulung_energy += 100;
            }
        } else {
            mulung_energy = 0;
        }
        //    client.sendPacket(MaplePacketCreator.MulungEnergy(mulung_energy));
    }

    public void writeMulungEnergy() {
        //  client.sendPacket(MaplePacketCreator.MulungEnergy(mulung_energy));
    }

    public void writeEnergy(String type, String inc) {
        // client.sendPacket(MaplePacketCreator.sendPyramidEnergy(type, inc));
    }

    public void writeStatus(String type, String inc) {
        //  client.sendPacket(MaplePacketCreator.sendGhostStatus(type, inc));
    }

    public void writePoint(String type, String inc) {
        //  client.sendPacket(MaplePacketCreator.sendGhostPoint(type, inc));
    }

    public final short getCombo() {
        return combo;
    }

    public void setCombo(final short combo) {
        this.combo = combo;
    }

    public final long getLastCombo() {
        return lastCombo;
    }

    public void setLastCombo(final long combo) {
        this.lastCombo = combo;
    }

    public final long getKeyDownSkill_Time() {
        return keydown_skill;
    }

    public void setKeyDownSkill_Time(final long keydown_skill) {
        this.keydown_skill = keydown_skill;
    }

    public void checkBerserk() {
        if (BerserkSchedule != null) {
            BerserkSchedule.cancel(false);
            BerserkSchedule = null;
        }

        final ISkill BerserkX = SkillFactory.getSkill(1320006);
        final int skilllevel = getSkillLevel(BerserkX);
        if (skilllevel >= 1) {
            final MapleStatEffect ampStat = BerserkX.getEffect(skilllevel);
            stats.Berserk = stats.getHp() * 100 / stats.getMaxHp() <= ampStat.getX();
            client.sendPacket(MaplePacketCreator.showOwnBuffEffect(1320006, 1, (byte) (stats.Berserk ? 1 : 0)));
            map.broadcastMessage(this, MaplePacketCreator.showBuffeffect(getId(), 1320006, 1, (byte) (stats.Berserk ? 1 : 0)), false);

            BerserkSchedule = BuffTimer.getInstance().schedule(new Runnable() {

                @Override
                public void run() {
                    checkBerserk();
                }
            }, 10000);
        }
    }

    private void prepareBeholderEffect() {
        if (beholderHealingSchedule != null) {
            beholderHealingSchedule.cancel(false);
        }
        if (beholderBuffSchedule != null) {
            beholderBuffSchedule.cancel(false);
        }
        ISkill bHealing = SkillFactory.getSkill(1320008);
        final int bHealingLvl = getSkillLevel(bHealing);
        final int berserkLvl = getSkillLevel(SkillFactory.getSkill(1320006));

        if (bHealingLvl > 0) {
            final MapleStatEffect healEffect = bHealing.getEffect(bHealingLvl);
            int healInterval = healEffect.getX() * 1000;
            beholderHealingSchedule = BuffTimer.getInstance().register(new Runnable() {

                @Override
                public void run() {
                    int remhppercentage = (int) Math.ceil((getStat().getHp() * 100.0) / getStat().getMaxHp());
                    if (berserkLvl == 0 || remhppercentage >= berserkLvl + 10) {
                        addHP(healEffect.getHp());
                    }
                    client.sendPacket(MaplePacketCreator.showOwnBuffEffect(1321007, 2));
                    map.broadcastMessage(MaplePacketCreator.summonSkill(getId(), 1321007, 5));
                    map.broadcastMessage(MapleCharacter.this, MaplePacketCreator.showBuffeffect(getId(), 1321007, 2), false);
                }
                //}, healInterval, healInterval);
            }, 1000 * 20, 1000 * 20);
        }
        ISkill bBuff = SkillFactory.getSkill(1320009);
        final int bBuffLvl = getSkillLevel(bBuff);
        if (bBuffLvl > 0) {
            final MapleStatEffect buffEffect = bBuff.getEffect(bBuffLvl);
            int buffInterval = buffEffect.getX() * 1000;
            beholderBuffSchedule = BuffTimer.getInstance().register(new Runnable() {

                @Override
                public void run() {
                    buffEffect.applyTo(MapleCharacter.this);
                    client.sendPacket(MaplePacketCreator.showOwnBuffEffect(1321007, 2));
                    map.broadcastMessage(MaplePacketCreator.summonSkill(getId(), 1321007, Randomizer.nextInt(3) + 6));
                    map.broadcastMessage(MapleCharacter.this, MaplePacketCreator.showBuffeffect(getId(), 1321007, 2), false);
                }
                //}, buffInterval, buffInterval);
            }, 1000 * 20, 1000 * 20);
        }
    }

    public void setChalkboard(String text) {
        this.chalktext = text;
        map.broadcastMessage(MTSCSPacket.useChalkboard(getId(), text));
    }

    public String getChalkboard() {
        return chalktext;
    }

    public MapleMount getMount() {
        return mount;
    }

    public int[] getWishlist() {
        return wishlist;
    }

    public void clearWishlist() {
        for (int i = 0; i < 10; i++) {
            wishlist[i] = 0;
        }
    }

    public int getWishlistSize() {
        int ret = 0;
        for (int i = 0; i < 10; i++) {
            if (wishlist[i] > 0) {
                ret++;
            }
        }
        return ret;
    }

    public void setWishlist(int[] wl) {
        this.wishlist = wl;
    }

    public int[] getRocks() {
        return rocks;
    }

    public int getRockSize() {
        int ret = 0;
        for (int i = 0; i < 10; i++) {
            if (rocks[i] != 999999999) {
                ret++;
            }
        }
        return ret;
    }

    public void deleteFromRocks(int map) {
        for (int i = 0; i < 10; i++) {
            if (rocks[i] == map) {
                rocks[i] = 999999999;
                break;
            }
        }
    }

    public void addRockMap() {
        if (getRockSize() >= 10) {
            return;
        }
        rocks[getRockSize()] = getMapId();
    }

    public boolean isRockMap(int id) {
        for (int i = 0; i < 10; i++) {
            if (rocks[i] == id) {
                return true;
            }
        }
        return false;
    }

    public int[] getRegRocks() {
        return regrocks;
    }

    public int getRegRockSize() {
        int ret = 0;
        for (int i = 0; i < 5; i++) {
            if (regrocks[i] != 999999999) {
                ret++;
            }
        }
        return ret;
    }

    public void deleteFromRegRocks(int map) {
        for (int i = 0; i < 5; i++) {
            if (regrocks[i] == map) {
                regrocks[i] = 999999999;
                break;
            }
        }
    }

    public void addRegRockMap() {
        if (getRegRockSize() >= 5) {
            return;
        }
        regrocks[getRegRockSize()] = getMapId();
    }

    public boolean isRegRockMap(int id) {
        for (int i = 0; i < 5; i++) {
            if (regrocks[i] == id) {
                return true;
            }
        }
        return false;
    }

    public List<LifeMovementFragment> getLastRes() {
        return lastres;
    }

    public void setLastRes(List<LifeMovementFragment> lastres) {
        this.lastres = lastres;
    }

    public void setMonsterBookCover(int bookCover) {
        this.bookCover = bookCover;
    }

    public int getMonsterBookCover() {
        return bookCover;
    }

    public int getOneTimeLog(String bossid) {
        Connection con1 = DatabaseConnection.getConnection();
        try {
            int ret_count = 0;
            PreparedStatement ps;
            ps = con1.prepareStatement("select count(*) from onetimelog where characterid = ? and log = ?");
            ps.setInt(1, id);
            ps.setString(2, bossid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ret_count = rs.getInt(1);
            } else {
                ret_count = -1;
            }
            rs.close();
            ps.close();
            return ret_count;
        } catch (Exception Ex) {
            //log.error("Error while read bosslog.", Ex);
            return -1;
        }
    }

    public void setOneTimeLog(String bossid) {
        Connection con1 = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps;
            ps = con1.prepareStatement("insert into onetimelog (characterid, log) values (?,?)");
            ps.setInt(1, id);
            ps.setString(2, bossid);
            ps.executeUpdate();
            ps.close();
        } catch (Exception Ex) {
            //   log.error("Error while insert bosslog.", Ex);
        }
    }

    public int getBossLog(String bossid) {
        Connection con1 = DatabaseConnection.getConnection();
        try {
            int ret_count = 0;
            PreparedStatement ps;
            ps = con1.prepareStatement("select count(*) from bosslog where characterid = ? and bossid = ? and lastattempt >= subtime(current_timestamp, '1 0:0:0.0')");
            ps.setInt(1, id);
            ps.setString(2, bossid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ret_count = rs.getInt(1);
            } else {
                ret_count = -1;
            }
            rs.close();
            ps.close();
            return ret_count;
        } catch (Exception Ex) {
            //log.error("Error while read bosslog.", Ex);
            return -1;
        }
    }

    public int getBossLogg(String bossid) {
        Connection con1 = DatabaseConnection.getConnection();
        try {
            int ret_count = 0;
            PreparedStatement ps;
            ps = con1.prepareStatement("select count(*) from bosslogg where characterid = ? and bossid = ? and lastattempt >= subtime(current_timestamp, '1 0:0:0.0')");//and lastattempt >= subtime(current_timestamp, '1 0:0:0.0')
            ps.setInt(1, id);
            ps.setString(2, bossid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ret_count = rs.getInt(1);
            } else {
                ret_count = -1;
            }
            rs.close();
            ps.close();
            return ret_count;
        } catch (Exception Ex) {
            //log.error("Error while read bosslog.", Ex);
            return -1;
        }
    }

    public void setBossLog(String bossid) {
        Connection con1 = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps;
            ps = con1.prepareStatement("insert into bosslog (characterid, bossid) values (?,?)");
            ps.setInt(1, id);
            ps.setString(2, bossid);
            ps.executeUpdate();
            ps.close();
        } catch (Exception Ex) {
            //   log.error("Error while insert bosslog.", Ex);
        }
    }//zev

    public void setBossLogg(String bossid) {
        Connection con1 = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps;
            ps = con1.prepareStatement("insert into bosslogg (characterid, bossid) values (?,?)");
            ps.setInt(1, id);
            ps.setString(2, bossid);
            ps.executeUpdate();
            ps.close();
        } catch (Exception Ex) {
            //   log.error("Error while insert bosslog.", Ex);
        }
    }//zev

    public int getbpLog(String bpid) {
        Connection con1 = DatabaseConnection.getConnection();
        try {
            int ret_count = 0;
            PreparedStatement ps;
            ps = con1.prepareStatement("select count(*) from bplog where characterid = ? and bpid = ? and lastattempt >= subtime(current_timestamp, '1 0:0:0.0')");
            ps.setInt(1, id);
            ps.setString(2, bpid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ret_count = rs.getInt(1);
            } else {
                ret_count = -1;
            }
            rs.close();
            ps.close();
            return ret_count;
        } catch (Exception Ex) {
            //log.error("Error while read bosslog.", Ex);
            return -1;
        }
    }

    public void setbpLog(String bpid) {
        Connection con1 = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps;
            ps = con1.prepareStatement("insert into bplog (characterid, bpid) values (?,?)");
            ps.setInt(1, id);
            ps.setString(2, bpid);
            ps.executeUpdate();
            ps.close();
        } catch (Exception Ex) {
            //   log.error("Error while insert bosslog.", Ex);
        }
    }//zev

    public int getqlLog(String qlid) {
        Connection con1 = DatabaseConnection.getConnection();
        try {
            int ret_count = 0;
            PreparedStatement ps;
            ps = con1.prepareStatement("select count(*) from qllog where qlid = ? and lastattempt >= subtime(current_timestamp, '1 0:0:0.0')");
            //  ps.setInt(1, id);
            ps.setString(2, qlid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ret_count = rs.getInt(1);
            } else {
                ret_count = -1;
            }
            rs.close();
            ps.close();
            return ret_count;
        } catch (Exception Ex) {
            //log.error("Error while read bosslog.", Ex);
            return -1;
        }
    }

    public void setqlLog(String qlid) {
        Connection con1 = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps;
            ps = con1.prepareStatement("insert into qllog (qlid) values (?,?)");
            //   ps.setInt(1, id);
            ps.setString(2, qlid);
            ps.executeUpdate();
            ps.close();
        } catch (Exception Ex) {
            //   log.error("Error while insert bosslog.", Ex);
        }
    }//zev

    public void setPrizeLog(String bossid) {
        Connection con1 = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps;
            ps = con1.prepareStatement("insert into Prizelog (accid, bossid) values (?,?)");
            ps.setInt(1, getClient().getAccID());
            ps.setString(2, bossid);
            ps.executeUpdate();
            ps.close();
        } catch (Exception Wx) {
        }
    }

    public int getPrizeLog(String bossid) {
        Connection con1 = DatabaseConnection.getConnection();
        try {
            int ret_count = 0;
            PreparedStatement ps;
            ps = con1.prepareStatement("select count(*) from Prizelog where accid = ? and bossid = ?");
            ps.setInt(1, getClient().getAccID());
            ps.setString(2, bossid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ret_count = rs.getInt(1);
            } else {
                ret_count = -1;
            }
            rs.close();
            ps.close();
            return ret_count;
        } catch (Exception Wx) {
            return -1;
        }
    }

    public void dropMessage(int type, String message) {
        /*
         * if (type == -1) {
         * client.sendPacket(UIPacket.getTopMsg(message)); } else
         */ if (type == -2) {
            client.sendPacket(PlayerShopPacket.shopChat(message, 0)); //0 or what
        } else {
            client.sendPacket(MaplePacketCreator.serverNotice(type, message));
        }
    }

    public IMaplePlayerShop getPlayerShop() {
        return playerShop;
    }

    public void setPlayerShop(IMaplePlayerShop playerShop) {
        this.playerShop = playerShop;
    }

    public IMaplePlayerShop getPlayerShop2() {
        return playerShop2;
    }

    public void setPlayerShop2(IMaplePlayerShop playerShop) {
        this.playerShop2 = playerShop2;
    }

    public int getConversation() {
        return inst.get();
    }

    public void setConversation(int inst) {
        this.inst.set(inst);
    }

    public MapleCarnivalParty getCarnivalParty() {
        return carnivalParty;
    }

    public void setCarnivalParty(MapleCarnivalParty party) {
        carnivalParty = party;
    }

    public void addCP(int ammount) {
        totalCP += ammount;
        availableCP += ammount;
    }

    public void useCP(int ammount) {
        availableCP -= ammount;
    }

    public int getAvailableCP() {
        return availableCP;
    }

    public int getTotalCP() {
        return totalCP;
    }

    public void resetCP() {
        totalCP = 0;
        availableCP = 0;
    }

    public void addCarnivalRequest(MapleCarnivalChallenge request) {
        pendingCarnivalRequests.add(request);
    }

    public final MapleCarnivalChallenge getNextCarnivalRequest() {
        return pendingCarnivalRequests.pollLast();
    }

    public void clearCarnivalRequests() {
        pendingCarnivalRequests = new LinkedList<MapleCarnivalChallenge>();
    }

    public void startMonsterCarnival(final int enemyavailable, final int enemytotal) {
        client.sendPacket(MonsterCarnivalPacket.startMonsterCarnival(this, enemyavailable, enemytotal));
    }

    public void CPUpdate(final boolean party, final int available, final int total, final int team) {
        client.sendPacket(MonsterCarnivalPacket.CPUpdate(party, available, total, team));
    }

    public void playerDiedCPQ(final String name, final int lostCP, final int team) {
        client.sendPacket(MonsterCarnivalPacket.playerDiedMessage(name, lostCP, team));
    }

    /*
     * public void setAchievementFinished(int id) { if
     * (!finishedAchievements.contains(id)) { finishedAchievements.add(id); } }
     *
     * public boolean achievementFinished(int achievementid) { return
     * finishedAchievements.contains(achievementid); }
     *
     * public void finishAchievement(int id) { if (!achievementFinished(id)) {
     * if (isAlive() && !isClone()) {
     * MapleAchievements.getInstance().getById(id).finishAchievement(this); } }
     * }
     *
     * public List<Integer> getFinishedAchievements() { return
     * finishedAchievements; }
     *
     * public void modifyAchievementCSPoints(int type, int quantity) { switch
     * (type) { case 1: acash += quantity; break; case 2: maplepoints +=
     * quantity; break; } }
     */
    public boolean getCanTalk() {
        return this.canTalk;
    }

    public void canTalk(boolean talk) {
        this.canTalk = talk;
    }

    public int getHp() {
        return stats.hp;
    }

    public void setHp(int hp) {
        stats.setHp(hp);
    }

    public int getMp() {
        return stats.mp;
    }

    public void setMp(int mp) {
        stats.setMp(mp);
    }

    public int getMaxHp() {
        return stats.maxhp;
    }

    public void setMaxHp(int hp) {
        stats.setMaxHp((short) hp);
    }

    public int getMaxMp() {
        return stats.maxmp;
    }

    public void setMaxmp(int mp) {
        stats.setMaxMp((short) mp);
    }

    public int getStr() {
        return stats.str;
    }

    public int getDex() {
        return stats.dex;
    }

    public int getLuk() {
        return stats.luk;
    }

    public int getInt() {
        return stats.int_;
    }

    public int getEXPMod() {
        return stats.���鱶��;
    }

    public int getDropMod() {
        return stats.���ﱶ��;
    }

    public int getCashMod() {
        return stats.cashMod;
    }

    public void setPoints(int p) {
        this.points = p;
        /*
         * if (this.points >= 1) { finishAchievement(1); }
         */
    }

    public int getPoints() {
        return points;
    }

    public void setVPoints(int p) {
        this.vpoints = p;
    }

    public int getVPoints() {
        return vpoints;
    }

    public CashShop getCashInventory() {
        return cs;
    }

    public void removeAll(int id) {
        removeAll(id, true, false);
    }

    public void removeAll(int id, boolean show, boolean checkEquipped) {
        MapleInventoryType type = GameConstants.getInventoryType(id);
        int possessed = getInventory(type).countById(id);

        if (possessed > 0) {
            MapleInventoryManipulator.removeById(getClient(), type, id, possessed, true, false);
            if (show) {
                getClient().sendPacket(MaplePacketCreator.getShowItemGain(id, (short) -possessed, true));
            }
        }
        if ((checkEquipped) && (type == MapleInventoryType.EQUIP)) {
            type = MapleInventoryType.EQUIPPED;
            possessed = getInventory(type).countById(id);
            if (possessed > 0) {
                MapleInventoryManipulator.removeById(getClient(), type, id, possessed, true, false);
                if (show) {
                    getClient().sendPacket(MaplePacketCreator.getShowItemGain(id, (short) (-possessed), true));
                }
                equipChanged();
            }
        }
    }

    //TODO: more than one crush/friendship ring at a time
    public Pair<List<MapleRing>, List<MapleRing>> getRings(boolean equip) {
        MapleInventory iv = getInventory(MapleInventoryType.EQUIPPED);
        Collection<IItem> equippedC = iv.list();
        List<Item> equipped = new ArrayList<>(equippedC.size());
        for (IItem item : equippedC) {
            equipped.add((Item) item);
        }
        Collections.sort(equipped);
        List<MapleRing> crings = new ArrayList<>();
        List<MapleRing> frings = new ArrayList<>();
        MapleRing ring;
        for (Item item : equipped) {
            if (item.getRing() != null) {
                ring = item.getRing();
                ring.setEquipped(true);
                if (GameConstants.isFriendshipRing(item.getItemId()) || GameConstants.isCrushRing(item.getItemId())) {
                    if (equip) {
                        if (GameConstants.isCrushRing(item.getItemId())) {
                            crings.add(ring);
                        } else if (GameConstants.isFriendshipRing(item.getItemId())) {
                            frings.add(ring);
                        }
                    } else if (crings.isEmpty() && GameConstants.isCrushRing(item.getItemId())) {
                        crings.add(ring);
                    } else if (frings.isEmpty() && GameConstants.isFriendshipRing(item.getItemId())) {
                        frings.add(ring);
                    } //for 3rd person the actual slot doesnt matter, so we'll use this to have both shirt/ring same?
                    //however there seems to be something else behind this, will have to sniff someone with shirt and ring, or more conveniently 3-4 of those
                }
            }
        }
        if (equip) {
            iv = getInventory(MapleInventoryType.EQUIP);
            for (IItem item : iv.list()) {
                if (item.getRing() != null && GameConstants.isEffectRing(item.getItemId())) {
                    ring = item.getRing();
                    ring.setEquipped(false);
                    if (GameConstants.isFriendshipRing(item.getItemId())) {
                        frings.add(ring);
                    } else if (GameConstants.isCrushRing(item.getItemId())) {
                        crings.add(ring);
                    }
                }
            }
        }
        Collections.sort(frings, new MapleRing.RingComparator());
        Collections.sort(crings, new MapleRing.RingComparator());
        return new Pair<>(crings, frings);
    }

    public int getFH() {
        MapleFoothold fh = getMap().getFootholds().findBelow(getPosition());
        if (fh != null) {
            return fh.getId();
        }
        return 0;
    }

    public void startFairySchedule(boolean exp) {
        startFairySchedule(exp, false);
    }

    public void startFairySchedule(boolean exp, boolean equipped) {
//        cancelFairySchedule(exp);
//        if (fairyExp < 10 && stats.equippedFairy) {
//            if (equipped) {
//                dropMessage(5, "������߾��齫����10���Ӻ����� " + (fairyExp) + "% ");
//            }
//
//            fairySchedule = EtcTimer.getInstance().schedule(new Runnable() {
//
//                @Override
//                public void run() {
//                    if (fairyExp < 10 && stats.equippedFairy) {
//                        fairyExp = 10;
//                        dropMessage(5, "������߾������� " + fairyExp + "%.");
//                        startFairySchedule(false, true);
//                    } else {
//                        cancelFairySchedule(!stats.equippedFairy);
//                    }
//                }
//            }, 60 * 60 * 1000);
//
//        }

    }

    public void ���ùȷ�˿(boolean exp, boolean equipped) {
        ���ùȷ�˿(exp);
    }

    public void ���ù���֤(boolean exp, boolean equipped) {
        ���ù���֤(exp);
    }

    public void ����VIP����ӳ�(boolean exp, boolean equipped) {
        ����VIP����ӳ�(exp);
    }

    public void ����VIP����ӳ�(boolean exp) {
        if (exp) {
            this.fairyExp += 30;
        }
    }

    public void cancelFairySchedule(boolean exp) {
        if (fairySchedule != null) {
            fairySchedule.cancel(false);
            fairySchedule = null;
        }
        if (exp) {
            int �����׹ = gui.Start.ConfigValuesMap.get("�����׹����ӳ�");
            this.fairyExp = (byte) �����׹;
        }
    }

    //
    public void ���ùȷ�˿(boolean exp) {
        //  if (HOUR_OF_DAY >= 0 && HOUR_OF_DAY <= 6) {
        if (exp) {
            this.fairyExp = 50;
        }
        // }
    }

    public void ���ù���֤(boolean exp) {
        //if (HOUR_OF_DAY >= 0 && HOUR_OF_DAY <= 6) {
        // if (party.getMembers().size() < 12) {
        if (exp) {
            this.fairyExp = 100;
        }
        //}
        // }
    }

    public byte getFairyExp() {
        return fairyExp;
    }

    public int getCoconutTeam() {
        return coconutteam;
    }

    public void setCoconutTeam(int team) {
        coconutteam = team;
    }

    public void spawnPet(byte slot) {
        spawnPet(slot, false, true);
    }

    public void spawnPet(byte slot, boolean lead) {
        spawnPet(slot, lead, true);
    }

    public void spawnPet(byte slot, boolean lead, boolean broadcast) {
        final IItem item = getInventory(MapleInventoryType.CASH).getItem(slot);
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (item == null || item.getItemId() > 5010000 || item.getItemId() < 5000000) {
            return;
        }
        switch (item.getItemId()) {
            case 5000047:
            case 5000028: {
                final MaplePet pet = MaplePet.createPet(item.getItemId() + 1, MapleInventoryIdentifier.getInstance());
                if (pet != null) {
                    MapleInventoryManipulator.addById(client, item.getItemId() + 1, (short) 1, item.getOwner(), pet, 45, (byte) 0);
                    MapleInventoryManipulator.removeFromSlot(client, MapleInventoryType.CASH, slot, (short) 1, false);
                }
                break;
            }
            default: {
                final MaplePet pet = item.getPet();
                if (pet != null && (item.getItemId() != 5000054 || pet.getSecondsLeft() > 0) && (item.getExpiration() == -1 || item.getExpiration() > System.currentTimeMillis())) {
                    if (pet.getSummoned()) { // Already summoned, let's keep it
                        unequipPet(pet, false);
                    } else {
                        int leadid = 8;
                        if (GameConstants.isKOC(getJob())) {
                            leadid = 10000018;
                        } else if (GameConstants.isAran(getJob())) {
                            leadid = 20000024;
                        }
                        if (getSkillLevel(SkillFactory.getSkill(leadid)) == 0 && getPet(0) != null) {
                            unequipPet(getPet(0), false);
                        } else if (lead) { // Follow the Lead
                            shiftPetsRight();
                        }
                        final Point pos = getPosition();
                        pet.setPos(pos);
                        try {
                            pet.setFh(getMap().getFootholds().findBelow(pos).getId());
                        } catch (NullPointerException e) {
                            pet.setFh(0); //lol, it can be fixed by movement
                        }
                        pet.setStance(0);
                        pet.setSummoned(getPetSlotNext() + 1);

                        addPet(pet);
                        if (broadcast) {
                            getMap().broadcastMessage(this, PetPacket.showPet(this, pet, false, false), true);

                            //final List<Pair<MapleStat, Integer>> stats = new ArrayList<Pair<MapleStat, Integer>>(1);
                            //stats.add(new Pair<MapleStat, Integer>(MapleStat.PET, Integer.valueOf(pet.getUniqueId())));
                            client.sendPacket(PetPacket.petStatUpdate(this));
                        }
                    }
                }
                break;
            }
        }
        client.sendPacket(PetPacket.emptyStatUpdate());
    }

    public void addMoveMob(int mobid) {
        if (movedMobs.containsKey(mobid)) {
            movedMobs.put(mobid, movedMobs.get(mobid) + 1);
            if (movedMobs.get(mobid) > 30) { //trying to move not null monster = broadcast dead
                for (MapleCharacter chr : getMap().getCharactersThreadsafe()) { //also broadcast to others
                    if (chr.getMoveMobs().containsKey(mobid)) { //they also tried to move this mob
                        chr.getClient().sendPacket(MobPacket.killMonster(mobid, 1));
                        chr.getMoveMobs().remove(mobid);
                    }
                }
            }
        } else {
            movedMobs.put(mobid, 1);
        }
    }

    public Map<Integer, Integer> getMoveMobs() {
        return movedMobs;
    }

    public int getLinkMid() {
        return linkMid;
    }

    public void setLinkMid(int lm) {
        this.linkMid = lm;
    }

    public boolean isClone() {
        return clone;
    }

    public void setClone(boolean c) {
        this.clone = c;
    }

    public WeakReference<MapleCharacter>[] getClones() {
        return clones;
    }

    public final void �ٻ�����(int a) {
        if (clone) {
            return;
        }
        for (int i = 0; i < clones.length; i++) {
            if (clones[i].get() == null) {
                final MapleCharacter newp1 = ����ϵͳ(a);
                map.addPlayer(newp1);
                map.broadcastMessage(MaplePacketCreator.updateCharLook(newp1));
                map.movePlayer(newp1, getPosition());
                clones[i] = new WeakReference<MapleCharacter>(newp1);
                return;
            }
        }
    }

    public MapleCharacter ����ϵͳ(int a) {
        MapleClient cs = new MapleClient(null, null, new MockIOSession());

        final int minus = (getId() + Randomizer.nextInt(getId()));

        MapleCharacter ret = new MapleCharacter(true);
        ret.id = minus;
        ret.client = cs;
        ret.exp = 0;
        ret.meso = 0;
        ret.beans = beans;
        ret.blood = blood;
        ret.month = month;
        ret.day = day;
        ret.charmessage = charmessage;
        ret.expression = expression;
        ret.constellation = constellation;
        ret.remainingAp = 0;
        ret.fame = 0;
        ret.accountid = client.getAccID();
        ret.name = ȡ��������(a, "name");
        ret.level = (short) Integer.parseInt(ȡ��������(a, "level"));
        ret.fame = (short) Integer.parseInt(ȡ��������(a, "fame"));
        ret.job = (short) Integer.parseInt(ȡ��������(a, "job"));
        ret.hair = (short) Integer.parseInt(ȡ��������(a, "hair"));
        ret.face = (short) Integer.parseInt(ȡ��������(a, "face"));
        ret.skinColor = skinColor;
        ret.bookCover = bookCover;
        ret.monsterbook = monsterbook;
        ret.mount = mount;
        ret.CRand = new PlayerRandomStream();
        ret.gmLevel = gmLevel;
        ret.gender = gender;
        ret.mapid = map.getId();
        ret.map = map;
        ret.setStance(getStance());
        ret.chair = chair;
        ret.itemEffect = itemEffect;
        ret.guildid = (short) Integer.parseInt(ȡ��������(a, "guildid"));
        ret.currentrep = currentrep;
        ret.totalrep = totalrep;
        ret.stats = stats;
        ret.effects.putAll(effects);
        ret.guildrank = guildrank;
        ret.allianceRank = allianceRank;
        ret.hidden = hidden;
        ret.setPosition(new Point(getPosition()));
        for (IItem equip : getInventory(MapleInventoryType.EQUIPPED)) {
            ret.getInventory(MapleInventoryType.EQUIPPED).addFromDB(equip);
        }
        ret.skillMacros = skillMacros;
        ret.keylayout = keylayout;
        ret.questinfo = questinfo;
        ret.savedLocations = savedLocations;
        ret.wishlist = wishlist;
        ret.rocks = rocks;
        ret.regrocks = regrocks;
        ret.buddylist = buddylist;
        ret.keydown_skill = 0;
        ret.lastmonthfameids = lastmonthfameids;
        ret.lastfametime = lastfametime;
        ret.storage = storage;
        ret.cs = this.cs;
        ret.client.setAccountName(client.getAccountName());
        ret.acash = acash;
        ret.lastGainHM = lastGainHM;
        ret.maplepoints = maplepoints;
        ret.clone = true;
        ret.client.setChannel(this.client.getChannel());
        while (map.getCharacterById(ret.id) != null || client.getChannelServer().getPlayerStorage().getCharacterById(ret.id) != null) {
            ret.id++;
        }
        ret.client.setPlayer(ret);
        return ret;
    }

    public static String ȡ��������(int a, String b) {
        String data = "";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT " + b + " as DATA FROM characters WHERE id = ?");
            ps.setInt(1, a);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString(b);
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("ȡ�������Գ���");
        }
        return data;
    }

    public MapleCharacter cloneLooks() {
        MapleClient cs = new MapleClient(null, null, new MockIOSession());

        final int minus = (getId() + Randomizer.nextInt(getId())); // really randomize it, dont want it to fail

        MapleCharacter ret = new MapleCharacter(true);
        ret.id = minus;
        ret.client = cs;
        ret.exp = 0;
        ret.meso = 0;
        ret.beans = beans;
        ret.blood = blood;
        ret.month = month;
        ret.day = day;
        ret.charmessage = charmessage;
        ret.expression = expression;
        ret.constellation = constellation;
        ret.remainingAp = 0;
        ret.fame = 0;
        ret.accountid = client.getAccID();
        ret.name = name;
        ret.level = level;
        ret.fame = fame;
        ret.job = job;
        ret.hair = hair;
        ret.face = face;
        ret.skinColor = skinColor;
        ret.bookCover = bookCover;
        ret.monsterbook = monsterbook;
        ret.mount = mount;
        ret.CRand = new PlayerRandomStream();
        ret.gmLevel = gmLevel;
        ret.gender = gender;
        ret.mapid = map.getId();
        ret.map = map;
        ret.setStance(getStance());
        ret.chair = chair;
        ret.itemEffect = itemEffect;
        ret.guildid = guildid;
        ret.currentrep = currentrep;
        ret.totalrep = totalrep;
        ret.stats = stats;
        ret.effects.putAll(effects);
        if (ret.effects.get(MapleBuffStat.ILLUSION) != null) {
            ret.effects.remove(MapleBuffStat.ILLUSION);
        }
        if (ret.effects.get(MapleBuffStat.SUMMON) != null) {
            ret.effects.remove(MapleBuffStat.SUMMON);
        }
        if (ret.effects.get(MapleBuffStat.REAPER) != null) {
            ret.effects.remove(MapleBuffStat.REAPER);
        }
        if (ret.effects.get(MapleBuffStat.PUPPET) != null) {
            ret.effects.remove(MapleBuffStat.PUPPET);
        }
        ret.guildrank = guildrank;
        ret.allianceRank = allianceRank;
        ret.hidden = hidden;
        ret.setPosition(new Point(getPosition()));
        for (IItem equip : getInventory(MapleInventoryType.EQUIPPED)) {
            ret.getInventory(MapleInventoryType.EQUIPPED).addFromDB(equip);
        }
        ret.skillMacros = skillMacros;
        ret.keylayout = keylayout;
        ret.questinfo = questinfo;
        ret.savedLocations = savedLocations;
        ret.wishlist = wishlist;
        ret.rocks = rocks;
        ret.regrocks = regrocks;
        ret.buddylist = buddylist;
        ret.keydown_skill = 0;
        ret.lastmonthfameids = lastmonthfameids;
        ret.lastfametime = lastfametime;
        ret.storage = storage;
        ret.cs = this.cs;
        ret.client.setAccountName(client.getAccountName());
        ret.acash = acash;
        ret.lastGainHM = lastGainHM;
        ret.maplepoints = maplepoints;
        ret.clone = true;
        ret.client.setChannel(this.client.getChannel());

        while (map.getCharacterById(ret.id) != null || client.getChannelServer().getPlayerStorage().getCharacterById(ret.id) != null) {
            ret.id++;
        }
        ret.client.setPlayer(ret);
        return ret;
    }

    //---------------------------------------����1
    public final void cloneLook1() {
        if (clone) {
            return;
        }
        for (int i = 0; i < clones.length; i++) {
            if (clones[i].get() == null) {
                final MapleCharacter newp1 = cloneLooks1();
                map.addPlayer(newp1);
                map.broadcastMessage(MaplePacketCreator.updateCharLook(newp1));
                map.movePlayer(newp1, getPosition());
                clones[i] = new WeakReference<MapleCharacter>(newp1);
                return;
            }
        }
    }

    public MapleCharacter cloneLooks1() {
        MapleClient cs = new MapleClient(null, null, new MockIOSession());
        final int minus = (getId() + Randomizer.nextInt(getId())); // really randomize it, dont want it to fail
        MapleCharacter ret = new MapleCharacter(true);
        //MapleCharacter ret = new MapleCharacter(false);
        ret.id = minus;
        ret.client = cs;
        ret.exp = 0;
        ret.meso = 0;
        ret.beans = 0;
        ret.blood = blood;
        ret.month = month;
        ret.day = day;
        ret.charmessage = charmessage;
        ret.expression = expression;
        ret.constellation = constellation;
        ret.remainingAp = 0;
        ret.fame = 0;
        ret.accountid = client.getAccID();
        ret.name = name + "��";
        //��������
        if (Getrobot("" + id + "", 11) <= 0) {
            ret.hair = hair;
        } else {
            ret.hair = (short) Getrobot("" + id + "", 11);
        }
        //��������
        if (Getrobot("" + id + "", 12) <= 0) {
            ret.face = face;
        } else {
            ret.face = (short) Getrobot("" + id + "", 12);
        }
        //������ɫ
        if (Getrobot("" + id + "", 13) <= 0) {
            ret.skinColor = skinColor;
        } else {
            ret.skinColor = (byte) (short) Getrobot("" + id + "", 13);
        }
        //�����ȼ�
        if (Getrobot("" + id + "", 14) <= 0) {
            ret.level = 1;
        } else {
            ret.level = (short) Getrobot("" + id + "", 14);
        }
        //����
        ret.fame = 0;
        //ְҵ
        ret.job = job;
        ret.bookCover = bookCover;
        ret.monsterbook = monsterbook;
        ret.mount = mount;
        ret.CRand = new PlayerRandomStream();
        //GM�ȼ�
        ret.gmLevel = 0;
        //����
        ret.gender = 0;
        //��ͼ
        ret.mapid = map.getId();
        ret.map = map;
        ret.setStance(getStance());
        ret.chair = chair;
        ret.itemEffect = itemEffect;
        //����ID
        ret.guildid = guildid;
        ret.currentrep = currentrep;
        ret.totalrep = totalrep;
        ret.stats = stats;
        //BUFF
        ret.effects.putAll(effects);
        if (ret.effects.get(MapleBuffStat.ILLUSION) != null) {
            ret.effects.remove(MapleBuffStat.ILLUSION);
        }
        if (ret.effects.get(MapleBuffStat.SUMMON) != null) {
            ret.effects.remove(MapleBuffStat.SUMMON);
        }
        if (ret.effects.get(MapleBuffStat.REAPER) != null) {
            ret.effects.remove(MapleBuffStat.REAPER);
        }
        if (ret.effects.get(MapleBuffStat.PUPPET) != null) {
            ret.effects.remove(MapleBuffStat.PUPPET);
        }
        ret.guildrank = guildrank;
        ret.allianceRank = allianceRank;
        //���ص�
        ret.hidden = hidden;
        ret.setPosition(new Point(getPosition()));
        for (IItem equip : getInventory(MapleInventoryType.EQUIPPED)) {
            ret.getInventory(MapleInventoryType.EQUIPPED).addFromDB(equip);
        }
        //���ܺ�
        ret.skillMacros = skillMacros;
        //���̿��
        ret.keylayout = keylayout;
        //????
        ret.questinfo = questinfo;
        //����λ��
        ret.savedLocations = savedLocations;
        //Ը���嵥
        ret.wishlist = wishlist;
        ret.rocks = rocks;
        ret.regrocks = regrocks;
        ret.buddylist = buddylist;
        ret.keydown_skill = 0;
        ret.lastmonthfameids = lastmonthfameids;
        ret.lastfametime = lastfametime;
        //�洢
        ret.storage = storage;
        ret.cs = this.cs;
        //�����ʺ�����
        ret.client.setAccountName(client.getAccountName());
        ret.acash = acash;
        ret.lastGainHM = lastGainHM;
        ret.maplepoints = maplepoints;
        //��¡
        ret.clone = true;
        //ret.clone = false;
        ret.client.setChannel(this.client.getChannel());
        while (map.getCharacterById(ret.id) != null || client.getChannelServer().getPlayerStorage().getCharacterById(ret.id) != null) {
            ret.id++;
        }
        ret.client.setPlayer(ret);
        return ret;
    }

    //---------------------------------------����2
    public final void cloneLook2() {
        if (clone) {
            return;
        }
        for (int i = 0; i < clones.length; i++) {
            if (clones[i].get() == null) {
                final MapleCharacter newp = cloneLooks2();
                //��ͼ
                map.addPlayer(newp);
                //�㲥��Ϣ
                map.broadcastMessage(MaplePacketCreator.updateCharLook(newp));
                //�ƶ�������
                map.movePlayer(newp, getPosition());
                clones[i] = new WeakReference<MapleCharacter>(newp);
                return;
            }
        }
    }

    public MapleCharacter cloneLooks2() {
        MapleClient cs = new MapleClient(null, null, new MockIOSession());
        final int minus = (getId() + Randomizer.nextInt(getId())); // really randomize it, dont want it to fail
        MapleCharacter ret = new MapleCharacter(true);
        //MapleCharacter ret = new MapleCharacter(false);
        ret.id = minus;
        ret.client = cs;
        ret.exp = 0;
        ret.meso = 0;
        ret.beans = 0;
        ret.blood = blood;
        ret.month = month;
        ret.day = day;
        ret.charmessage = charmessage;
        ret.expression = expression;
        ret.constellation = constellation;
        ret.remainingAp = 0;
        ret.fame = 0;
        ret.accountid = client.getAccID();
        ret.name = "[" + name + "][������]";

        //��������
        if (Getrobot("" + id + "", 21) <= 0) {
            ret.hair = hair;
        } else {
            ret.hair = (short) Getrobot("" + id + "", 21);
        }
        //��������
        if (Getrobot("" + id + "", 22) <= 0) {
            ret.face = face;
        } else {
            ret.face = (short) Getrobot("" + id + "", 22);
        }
        //������ɫ
        if (Getrobot("" + id + "", 23) <= 0) {
            ret.skinColor = skinColor;
        } else {
            ret.skinColor = (byte) (short) Getrobot("" + id + "", 23);
        }
        //�����ȼ�
        if (Getrobot("" + id + "", 24) <= 0) {
            ret.level = 1;
        } else {
            ret.level = (short) Getrobot("" + id + "", 24);
        }

        ret.fame = fame;
        ret.job = job;
        ret.bookCover = bookCover;
        ret.monsterbook = monsterbook;
        ret.mount = mount;
        ret.CRand = new PlayerRandomStream();
        ret.gmLevel = 0;
        ret.gender = 0;
        ret.mapid = map.getId();
        ret.map = map;
        ret.setStance(getStance());
        ret.chair = chair;
        ret.itemEffect = itemEffect;
        ret.guildid = guildid;
        ret.currentrep = currentrep;
        ret.totalrep = totalrep;
        ret.stats = stats;
        ret.effects.putAll(effects);
        if (ret.effects.get(MapleBuffStat.ILLUSION) != null) {
            ret.effects.remove(MapleBuffStat.ILLUSION);
        }
        if (ret.effects.get(MapleBuffStat.SUMMON) != null) {
            ret.effects.remove(MapleBuffStat.SUMMON);
        }
        if (ret.effects.get(MapleBuffStat.REAPER) != null) {
            ret.effects.remove(MapleBuffStat.REAPER);
        }
        if (ret.effects.get(MapleBuffStat.PUPPET) != null) {
            ret.effects.remove(MapleBuffStat.PUPPET);
        }
        ret.guildrank = guildrank;
        ret.allianceRank = allianceRank;
        //���ص�
        ret.hidden = hidden;
        ret.setPosition(new Point(getPosition()));
        for (IItem equip : getInventory(MapleInventoryType.EQUIPPED)) {
            ret.getInventory(MapleInventoryType.EQUIPPED).addFromDB(equip);
        }
        //���ܺ�
        ret.skillMacros = skillMacros;
        //���̿��
        ret.keylayout = keylayout;
        //????
        ret.questinfo = questinfo;
        //����λ��
        ret.savedLocations = savedLocations;
        //Ը���嵥
        ret.wishlist = wishlist;
        ret.rocks = rocks;
        ret.regrocks = regrocks;
        ret.buddylist = buddylist;
        ret.keydown_skill = 0;
        ret.lastmonthfameids = lastmonthfameids;
        ret.lastfametime = lastfametime;
        //�洢
        ret.storage = storage;
        ret.cs = this.cs;
        //�����ʺ�����
        ret.client.setAccountName(client.getAccountName());
        ret.acash = acash;
        ret.lastGainHM = lastGainHM;
        ret.maplepoints = maplepoints;
        //��¡
        ret.clone = true;
        //ret.clone = false;
        ret.client.setChannel(this.client.getChannel());
        while (map.getCharacterById(ret.id) != null || client.getChannelServer().getPlayerStorage().getCharacterById(ret.id) != null) {
            ret.id++;
        }
        ret.client.setPlayer(ret);
        return ret;
    }

    public final void cloneLook() {
        if (clone) {
            return;
        }
        for (int i = 0; i < clones.length; i++) {
            if (clones[i].get() == null) {
                final MapleCharacter newp = cloneLooks();
                map.addPlayer(newp);
                map.broadcastMessage(MaplePacketCreator.updateCharLook(newp));
                map.movePlayer(newp, getPosition());
                clones[i] = new WeakReference<MapleCharacter>(newp);
                return;
            }
        }
    }

    public final void disposeClones() {
        numClones = 0;
        for (int i = 0; i < clones.length; i++) {
            if (clones[i].get() != null) {
                map.removePlayer(clones[i].get());
                clones[i].get().getClient().disconnect(false, false);
                clones[i] = new WeakReference<MapleCharacter>(null);
                numClones++;
            }
        }
    }

    public final int getCloneSize() {
        int z = 0;
        for (int i = 0; i < clones.length; i++) {
            if (clones[i].get() != null) {
                z++;
            }
        }
        return z;
    }

    public void spawnClones() {
        if (numClones == 0 && stats.hasClone) {
            cloneLook(); //once and never again
        }
        for (int i = 0; i < numClones; i++) {
            cloneLook();
        }
        numClones = 0;
    }

    public byte getNumClones() {
        return numClones;
    }

    public void setDragon(MapleDragon d) {
        this.dragon = d;
    }

    public void spawnSavedPets() {
        for (int i = 0; i < petStore.length; i++) {
            if (petStore[i] > -1) {
                spawnPet(petStore[i], false, false);
            }
        }
        client.sendPacket(PetPacket.petStatUpdate(this));
        petStore = new byte[]{-1, -1, -1};
    }

    public final byte[] getPetStores() {
        return petStore;
    }

    public void resetStats(final int str, final int dex, final int int_, final int luk) {
        List<Pair<MapleStat, Integer>> stat = new ArrayList<Pair<MapleStat, Integer>>(2);
        int total = stats.getStr() + stats.getDex() + stats.getLuk() + stats.getInt() + getRemainingAp();

        total -= str;
        stats.setStr((short) str);

        total -= dex;
        stats.setDex((short) dex);

        total -= int_;
        stats.setInt((short) int_);

        total -= luk;
        stats.setLuk((short) luk);

        setRemainingAp((short) total);

        stat.add(new Pair<MapleStat, Integer>(MapleStat.STR, str));
        stat.add(new Pair<MapleStat, Integer>(MapleStat.DEX, dex));
        stat.add(new Pair<MapleStat, Integer>(MapleStat.INT, int_));
        stat.add(new Pair<MapleStat, Integer>(MapleStat.LUK, luk));
        stat.add(new Pair<MapleStat, Integer>(MapleStat.AVAILABLEAP, total));
        client.sendPacket(MaplePacketCreator.updatePlayerStats(stat, false, getJob()));
    }

    public Event_PyramidSubway getPyramidSubway() {
        return pyramidSubway;
    }

    public void setPyramidSubway(Event_PyramidSubway ps) {
        this.pyramidSubway = ps;
    }

    public byte getSubcategory() {
        if (job >= 430 && job <= 434) {
            return 1; //dont set it
        }
        return subcategory;
    }

    public int itemQuantity(final int itemid) {
        return getInventory(GameConstants.getInventoryType(itemid)).countById(itemid);
    }

    public void setRPS(RockPaperScissors rps) {
        this.rps = rps;
    }

    public RockPaperScissors getRPS() {
        return rps;
    }

    public long getNextConsume() {
        return nextConsume;
    }

    public void setNextConsume(long nc) {
        this.nextConsume = nc;
    }

    public int getRank() {
        return rank;
    }

    public int getRankMove() {
        return rankMove;
    }

    public int getJobRank() {
        return jobRank;
    }

    public int getJobRankMove() {
        return jobRankMove;

    }
    
    public void changeChannel(final int channel) {//��Ϸ���л�Ƶ��
        saveToDB(false, false);
        int ˫��Ƶ�� = gui.Start.ConfigValuesMap.get("˫��Ƶ������");
        if (˫��Ƶ�� == 0) {
            if (channel == Integer.parseInt(ServerProperties.getProperty("ZEV.Count"))) {
                if (!haveItem(5220002, 1, false, false)) {
                    dropMessage(5, "�Բ�����ȱ��������Ʒ���޷����� " + Integer.parseInt(ServerProperties.getProperty("ZEV.Count")) + " Ƶ����");
                    client.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
            }
        }
        //����Ǻ�����
        Integer energyLevel = getBuffedValue(MapleBuffStat.ENERGY_CHARGE);
        if (energyLevel != null && energyLevel > 0) {
            setBuffedValue(MapleBuffStat.ENERGY_CHARGE, Integer.valueOf(energyLevel));
            List<Pair<MapleBuffStat, Integer>> stat = Collections.singletonList(new Pair<MapleBuffStat, Integer>(MapleBuffStat.ENERGY_CHARGE, energyLevel));
            client.sendPacket(MaplePacketCreator.������(stat, 0));
        }
        String[] socket = client.getChannelServer().getIP().split(":");
        final ChannelServer toch = ChannelServer.getInstance(channel);

        if (channel == client.getChannel() || toch == null || toch.isShutdown()) {
            // client.sendPacket(MaplePacketCreator.serverBlocked(1));
            return;
        }
        changeRemoval();

        final ChannelServer ch = ChannelServer.getInstance(client.getChannel());
        if (getMessenger() != null) {
            World.Messenger.silentLeaveMessenger(getMessenger().getId(), new MapleMessengerCharacter(this));
        }
        PlayerBuffStorage.addBuffsToStorage(getId(), getAllBuffs());
        PlayerBuffStorage.addCooldownsToStorage(getId(), getCooldowns());
        PlayerBuffStorage.addDiseaseToStorage(getId(), getAllDiseases());
        World.ChannelChange_Data(new CharacterTransfer(this), getId(), channel);
        ch.removePlayer(this);
        client.updateLoginState(MapleClient.CHANGE_CHANNEL, client.getSessionIPAddress());
        String s = this.client.getSessionIPAddress();
        LoginServer.addIPAuth(s.substring(s.indexOf(47) + 1, s.length()));
        try {
            client.sendPacket(MaplePacketCreator.getChannelChange(InetAddress.getByName(socket[0]), Integer.parseInt(toch.getIP().split(":")[1])));

        } catch (UnknownHostException ex) {
            Logger.getLogger(MapleCharacter.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        getMap().removePlayer(this);
        client.setPlayer(null);
        client.setReceiving(false);
        expirationTask(true, false);
    }

    public void expandInventory(byte type, int amount) {
        final MapleInventory inv = getInventory(MapleInventoryType.getByType(type));
        inv.addSlot((byte) amount);
        // client.sendPacket(MaplePacketCreator.getSlotUpdate(type, (byte) inv.getSlotLimit()));
    }

    public boolean allowedToTarget(MapleCharacter other) {
        return other != null && (!other.isHidden() || getGMLevel() >= other.getGMLevel());
    }

    public int getFollowId() {
        return followid;
    }

    public void setFollowId(int fi) {
        this.followid = fi;
        if (fi == 0) {
            this.followinitiator = false;
            this.followon = false;
        }
    }

    public void setFollowInitiator(boolean fi) {
        this.followinitiator = fi;
    }

    public void setFollowOn(boolean fi) {
        this.followon = fi;
    }

    public boolean isFollowOn() {
        return followon;
    }

    public boolean isFollowInitiator() {
        return followinitiator;
    }

    public void checkFollow() {
        if (followon) {
            //  map.broadcastMessage(MaplePacketCreator.followEffect(id, 0, null));
            //  map.broadcastMessage(MaplePacketCreator.followEffect(followid, 0, null));
            MapleCharacter tt = map.getCharacterById(followid);
            //client.sendPacket(MaplePacketCreator.getFollowMessage("Follow canceled."));
            if (tt != null) {
                tt.setFollowId(0);
                // tt.getClient().sendPacket(MaplePacketCreator.getFollowMessage("Follow canceled."));
            }
            setFollowId(0);
        }
    }

    public int getMarriageId() {
        return marriageId;
    }

    public int �ж��Ƿ���() {
        return marriageId;
    }

    public void setMarriageId(final int mi) {
        this.marriageId = mi;
    }

    public int getMarriageItemId() {
        return marriageItemId;
    }

    public void setMarriageItemId(final int mi) {
        this.marriageItemId = mi;
    }

    public boolean isStaff() {
        return this.gmLevel > ServerConstants.PlayerGMRank.���ָ��.getLevel();
    }

    // TODO: gvup, vic, lose, draw, VR
    public boolean startPartyQuest(final int questid) {
        boolean ret = false;
        if (!quests.containsKey(MapleQuest.getInstance(questid)) || !questinfo.containsKey(questid)) {
            final MapleQuestStatus status = getQuestNAdd(MapleQuest.getInstance(questid));
            status.setStatus((byte) 1);
            updateQuest(status);
            switch (questid) {
                case 1300:
                case 1301:
                case 1302: //carnival, ariants.
                    updateInfoQuest(questid, "min=0;sec=0;date=0000-00-00;have=0;rank=F;try=0;cmp=0;CR=0;VR=0;gvup=0;vic=0;lose=0;draw=0");
                    break;
                case 1204: //herb town pq
                    updateInfoQuest(questid, "min=0;sec=0;date=0000-00-00;have0=0;have1=0;have2=0;have3=0;rank=F;try=0;cmp=0;CR=0;VR=0");
                    break;
                case 1206: //ellin pq
                    updateInfoQuest(questid, "min=0;sec=0;date=0000-00-00;have0=0;have1=0;rank=F;try=0;cmp=0;CR=0;VR=0");
                    break;
                default:
                    updateInfoQuest(questid, "min=0;sec=0;date=0000-00-00;have=0;rank=F;try=0;cmp=0;CR=0;VR=0");
                    break;
            }
            ret = true;
        } //started the quest.
        return ret;
    }

    public String getOneInfo(final int questid, final String key) {
        if (!questinfo.containsKey(questid) || key == null) {
            return null;
        }
        final String[] split = questinfo.get(questid).split(";");
        for (String x : split) {
            final String[] split2 = x.split("="); //should be only 2
            if (split2.length == 2 && split2[0].equals(key)) {
                return split2[1];
            }
        }
        return null;
    }

    public void updateOneInfo(final int questid, final String key, final String value) {
        if (!questinfo.containsKey(questid) || key == null || value == null) {
            return;
        }
        final String[] split = questinfo.get(questid).split(";");
        boolean changed = false;
        final StringBuilder newQuest = new StringBuilder();
        for (String x : split) {
            final String[] split2 = x.split("="); //should be only 2
            if (split2.length != 2) {
                continue;
            }
            if (split2[0].equals(key)) {
                newQuest.append(key).append("=").append(value);
            } else {
                newQuest.append(x);
            }
            newQuest.append(";");
            changed = true;
        }

        updateInfoQuest(questid, changed ? newQuest.toString().substring(0, newQuest.toString().length() - 1) : newQuest.toString());
    }

    public void recalcPartyQuestRank(final int questid) {
        if (!startPartyQuest(questid)) {
            final String oldRank = getOneInfo(questid, "rank");
            if (oldRank == null || oldRank.equals("S")) {
                return;
            }
            final String[] split = questinfo.get(questid).split(";");
            String newRank = null;
            if (oldRank.equals("A")) {
                newRank = "S";
            } else if (oldRank.equals("B")) {
                newRank = "A";
            } else if (oldRank.equals("C")) {
                newRank = "B";
            } else if (oldRank.equals("D")) {
                newRank = "C";
            } else if (oldRank.equals("F")) {
                newRank = "D";
            } else {
                return;
            }
            final List<Pair<String, Pair<String, Integer>>> questInfo = MapleQuest.getInstance(questid).getInfoByRank(newRank);
            for (Pair<String, Pair<String, Integer>> q : questInfo) {
                boolean found = false;
                final String val = getOneInfo(questid, q.right.left);
                if (val == null) {
                    return;
                }
                int vall = 0;
                try {
                    vall = Integer.parseInt(val);
                } catch (NumberFormatException e) {
                    return;
                }
                if (q.left.equals("less")) {
                    found = vall < q.right.right;
                } else if (q.left.equals("more")) {
                    found = vall > q.right.right;
                } else if (q.left.equals("equal")) {
                    found = vall == q.right.right;
                }
                if (!found) {
                    return;
                }
            }
            //perfectly safe
            updateOneInfo(questid, "rank", newRank);
        }
    }

    public void tryPartyQuest(final int questid) {
        try {
            startPartyQuest(questid);
            pqStartTime = System.currentTimeMillis();
            updateOneInfo(questid, "try", String.valueOf(Integer.parseInt(getOneInfo(questid, "try")) + 1));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("tryPartyQuest error");
        }
    }

    public void endPartyQuest(final int questid) {
        try {
            startPartyQuest(questid);
            if (pqStartTime > 0) {
                final long changeTime = System.currentTimeMillis() - pqStartTime;
                final int mins = (int) (changeTime / 1000 / 60), secs = (int) (changeTime / 1000 % 60);
                final int mins2 = Integer.parseInt(getOneInfo(questid, "min")), secs2 = Integer.parseInt(getOneInfo(questid, "sec"));
                if (mins2 <= 0 || mins < mins2) {
                    updateOneInfo(questid, "min", String.valueOf(mins));
                    updateOneInfo(questid, "sec", String.valueOf(secs));
                    updateOneInfo(questid, "date", FileoutputUtil.CurrentReadable_Date());
                }
                final int newCmp = Integer.parseInt(getOneInfo(questid, "cmp")) + 1;
                updateOneInfo(questid, "cmp", String.valueOf(newCmp));
                updateOneInfo(questid, "CR", String.valueOf((int) Math.ceil((newCmp * 100.0) / Integer.parseInt(getOneInfo(questid, "try")))));
                recalcPartyQuestRank(questid);
                pqStartTime = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("endPartyQuest error");
        }

    }

    public void havePartyQuest(final int itemId) {
        int questid = 0, index = -1;
        switch (itemId) {
            case 1002798:
                questid = 1200; //henesys
                break;
            case 1072369:
                questid = 1201; //kerning
                break;
            case 1022073:
                questid = 1202; //ludi
                break;
            case 1082232:
                questid = 1203; //orbis
                break;
            case 1002571:
            case 1002572:
            case 1002573:
            case 1002574:
                questid = 1204; //herbtown
                index = itemId - 1002571;
                break;
            case 1122010:
                questid = 1205; //magatia
                break;
            case 1032061:
            case 1032060:
                questid = 1206; //ellin
                index = itemId - 1032060;
                break;
            case 3010018:
                questid = 1300; //ariant
                break;
            case 1122007:
                questid = 1301; //carnival
                break;
            case 1122058:
                questid = 1302; //carnival2
                break;
            default:
                return;
        }
        startPartyQuest(questid);
        updateOneInfo(questid, "have" + (index == -1 ? "" : index), "1");
    }

    public void resetStatsByJob(boolean beginnerJob) {
        int baseJob = (beginnerJob ? (job % 1000) : (job % 1000 / 100 * 100)); //1112 -> 112 -> 1 -> 100
        if (baseJob == 100) { //first job = warrior
            resetStats(25, 4, 4, 4);
        } else if (baseJob == 200) {
            resetStats(4, 4, 20, 4);
        } else if (baseJob == 300 || baseJob == 400) {
            resetStats(4, 25, 4, 4);
        } else if (baseJob == 500) {
            resetStats(4, 20, 4, 4);
        }
    }

    public boolean hasSummon() {
        return hasSummon;
    }

    public void setHasSummon(boolean summ) {
        this.hasSummon = summ;
    }

    public void removeDoor() {
        final MapleDoor door = getDoors().iterator().next();
        for (final MapleCharacter chr : door.getTarget().getCharactersThreadsafe()) {
            door.sendDestroyData(chr.getClient());
        }
        for (final MapleCharacter chr : door.getTown().getCharactersThreadsafe()) {
            door.sendDestroyData(chr.getClient());
        }
        for (final MapleDoor destroyDoor : getDoors()) {
            door.getTarget().removeMapObject(destroyDoor);
            door.getTown().removeMapObject(destroyDoor);
        }
        clearDoors();
    }

    public void changeRemoval() {
        changeRemoval(false);
    }

    public void changeRemoval(boolean dc) {
        if (getTrade() != null) {
            MapleTrade.cancelTrade(getTrade(), client);
        }
        if (getCheatTracker() != null) {
            getCheatTracker().dispose();
        }
        if (!dc) {
            cancelEffectFromBuffStat(MapleBuffStat.MONSTER_RIDING);
            cancelEffectFromBuffStat(MapleBuffStat.SUMMON);
            cancelEffectFromBuffStat(MapleBuffStat.REAPER);
            cancelEffectFromBuffStat(MapleBuffStat.PUPPET);
        }
        if (getPyramidSubway() != null) {
            getPyramidSubway().dispose(this);
        }
        if (playerShop != null && !dc) {
            playerShop.removeVisitor(this);
            if (playerShop.isOwner(this)) {
                playerShop.setOpen(true);
            }
        }
        if (!getDoors().isEmpty()) {
            removeDoor();
        }
        disposeClones();
        NPCScriptManager.getInstance().dispose(client);
    }

    public void updateTick(int newTick) {
        anticheat.updateTick(newTick);
    }

    public boolean canUseFamilyBuff(MapleFamilyBuffEntry buff) {
        final MapleQuestStatus stat = getQuestNAdd(MapleQuest.getInstance(buff.questID));
        if (stat.getCustomData() == null) {
            stat.setCustomData("0");
        }
        return Long.parseLong(stat.getCustomData()) + (24 * 3600000) < System.currentTimeMillis();
    }

    public void useFamilyBuff(MapleFamilyBuffEntry buff) {
        final MapleQuestStatus stat = getQuestNAdd(MapleQuest.getInstance(buff.questID));
        stat.setCustomData(String.valueOf(System.currentTimeMillis()));
    }

    public List<Pair<Integer, Integer>> usedBuffs() {
        //assume count = 1
        List<Pair<Integer, Integer>> used = new ArrayList<Pair<Integer, Integer>>();
        for (MapleFamilyBuffEntry buff : MapleFamilyBuff.getBuffEntry()) {
            if (!canUseFamilyBuff(buff)) {
                used.add(new Pair<Integer, Integer>(buff.index, buff.count));
            }
        }
        return used;
    }

    public String getTeleportName() {
        return teleportname;
    }

    public void setTeleportName(final String tname) {
        teleportname = tname;
    }

    public int getNoJuniors() {
        if (mfc == null) {
            return 0;
        }
        return mfc.getNoJuniors();
    }

    public MapleFamilyCharacter getMFC() {
        return mfc;
    }

    public void makeMFC(final int familyid, final int seniorid, final int junior1, final int junior2) {
        if (familyid > 0) {
            MapleFamily f = World.Family.getFamily(familyid);
            if (f == null) {
                mfc = null;
            } else {
                mfc = f.getMFC(id);
                if (mfc == null) {
                    mfc = f.addFamilyMemberInfo(this, seniorid, junior1, junior2);
                }
                if (mfc.getSeniorId() != seniorid) {
                    mfc.setSeniorId(seniorid);
                }
                if (mfc.getJunior1() != junior1) {
                    mfc.setJunior1(junior1);
                }
                if (mfc.getJunior2() != junior2) {
                    mfc.setJunior2(junior2);
                }
            }
        } else {
            mfc = null;
        }
    }

    public void setFamily(final int newf, final int news, final int newj1, final int newj2) {
        if (mfc == null || newf != mfc.getFamilyId() || news != mfc.getSeniorId() || newj1 != mfc.getJunior1() || newj2 != mfc.getJunior2()) {
            makeMFC(newf, news, newj1, newj2);
        }
    }

    public int maxBattleshipHP(int skillid) {
        return (getSkillLevel(skillid) * 5000) + ((getLevel() - 120) * 3000);
    }

    public int currentBattleshipHP() {
        return battleshipHP;
    }

    public void sendEnglishQuiz(String msg) {
        client.sendPacket(MaplePacketCreator.englishQuizMsg(msg));
    }

    public void fakeRelog() {
        client.sendPacket(MaplePacketCreator.getCharInfo(this));
        final MapleMap mapp = getMap();
//        mapp.setCheckStates(false);
        mapp.removePlayer(this);
        mapp.addPlayer(this);
//        mapp.setCheckStates(true);
    }

    /*
     * public String getcharmessage(){ System.err.println("CharMessage(get)");
     * return charmessage; }
     *
     * public void setcharmessage(int s){
     * System.err.println("CharMessage(set)"); charmessage += s; }
     */
    public String getcharmessage() {
        //System.err.println("CharMessage(get)");
        return charmessage;
    }

    public void setcharmessage(String s) {
        //System.err.println("CharMessage(set)");
        charmessage /*
                 * +
                 */ = s;
    }

    public int getexpression() {
        return expression;
    }

    public void setexpression(int s) {
        expression /*
                 * +
                 */ = s;
    }

    public int getconstellation() {
        return constellation;
    }

    public void setconstellation(int s) {
        constellation /*
                 * +
                 */ = s;
    }

    public int getblood() {
        return blood;
    }

    public void setblood(int s) {
        blood /*
                 * +
                 */ = s;
    }

    public int getmonth() {
        return month;
    }

    public void setmonth(int s) {
        month /*
                 * +
                 */ = s;
    }

    public int getday() {
        return day;
    }

    public void setday(int s) {
        day /*
                 * +
                 */ = s;
    }

    public int getTeam() {
        return coconutteam;
    }

    public int getBeans() {
        return beans;
    }

    public void gainBeans(int s) {
        beans += s;
    }

    public void setBeans(int s) {
        beans = s;
    }

    public int getBeansNum() {
        return beansNum;
    }

    /**
     * @param beansNum the beansNum to set
     */
    public void setBeansNum(int beansNum) {
        this.beansNum = beansNum;
    }

    /**
     * @return the beansRange
     */
    public int getBeansRange() {
        return beansRange;
    }

    /**
     * @param beansRange the beansRange to set
     */
    public void setBeansRange(int beansRange) {
        beansRange = beansRange;
    }

    /**
     * @return the canSetBeansNum
     */
    public boolean isCanSetBeansNum() {
        return canSetBeansNum;
    }

    /**
     * @param canSetBeansNum the canSetBeansNum to set
     */
    public void setCanSetBeansNum(boolean canSetBeansNum) {
        this.canSetBeansNum = canSetBeansNum;
    }

    public boolean haveGM() {
        return gmLevel >= 2 && gmLevel <= 3;
    }

    public void setprefix(int prefix) {
        this.prefix = prefix;
    }

    public int getPrefix() {
        return prefix;
    }

    public int getPresent() {
        return this.Present;
    }

    public void setPresent(int state) {
        this.Present = state;
    }

    public void startMapEffect(String msg, int itemId) {
        startMapEffect(msg, itemId, 10000);
    }

    public void startMapEffect1(String msg, int itemId) {
        startMapEffect(msg, itemId, 20000);
    }

    public void startMapEffect(String msg, int itemId, int duration) {
        final MapleMapEffect mapEffect = new MapleMapEffect(msg, itemId);
        getClient().sendPacket(mapEffect.makeStartData());
        EventTimer.getInstance().schedule(new Runnable() {

            @Override
            public void run() {
                getClient().sendPacket(mapEffect.makeDestroyData());
            }
        }, duration);
    }

    public int getHyPay(int type) {
        int pay = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from hypay where accname = ?");
            ps.setString(1, getClient().getAccountName());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (type == 1) {
                    pay = rs.getInt("pay");
                } else if (type == 2) {
                    pay = rs.getInt("payUsed");
                } else if (type == 3) {
                    pay = rs.getInt("pay") + rs.getInt("payUsed");
                } else if (type == 4) {
                    pay = rs.getInt("payReward");
                } else {
                    pay = 0;
                }
            } else {
                PreparedStatement psu = con.prepareStatement("insert into hypay (accname, pay, payUsed, payReward) VALUES (?, ?, ?, ?)");
                psu.setString(1, getClient().getAccountName());
                psu.setInt(2, 0);
                psu.setInt(3, 0);
                psu.setInt(4, 0);
                psu.executeUpdate();
                psu.close();
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println("���ֵ��Ϣ��������: " + ex);
        }
        return pay;
    }

    public int gainHyPay(int hypay) {
        int pay = getHyPay(1);
        int payUsed = getHyPay(2);
        int payReward = getHyPay(4);
        if (hypay <= 0) {
            return 0;
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE hypay SET pay = ? ,payUsed = ? ,payReward = ? where accname = ?");
            ps.setInt(1, pay + hypay);
            ps.setInt(2, payUsed);
            ps.setInt(3, payReward);
            ps.setString(4, getClient().getAccountName());
            ps.executeUpdate();
            ps.close();
            return 1;
        } catch (SQLException ex) {
            System.err.println("�Ӽ���ֵ��Ϣ��������: " + ex);
        }
        return 0;
    }

    public int addHyPay(int hypay) {
        int pay = getHyPay(1);
        int payUsed = getHyPay(2);
        int payReward = getHyPay(4);
        if (hypay > pay) {
            return -1;
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE hypay SET pay = ? ,payUsed = ? ,payReward = ? where accname = ?");
            ps.setInt(1, pay - hypay);
            ps.setInt(2, payUsed + hypay);
            ps.setInt(3, payReward + hypay);
            ps.setString(4, getClient().getAccountName());
            ps.executeUpdate();
            ps.close();
            return 1;
        } catch (SQLException ex) {
            System.err.println("�Ӽ���ֵ��Ϣ��������: " + ex);
        }
        return -1;
    }

    public int delPayReward(int pay) {
        int payReward = getHyPay(4);
        if (pay <= 0) {
            return -1;
        }
        if (pay > payReward) {
            return -1;
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE hypay SET payReward = ? where accname = ?");
            ps.setInt(1, payReward - pay);
            ps.setString(2, getClient().getAccountName());
            ps.executeUpdate();
            ps.close();
            return 1;
        } catch (SQLException ex) {
            System.err.println("�Ӽ����ѽ�����Ϣ��������: " + ex);
        }
        return -1;
    }

    public int getGamePoints() {
        try {
            int gamePoints = 0;
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts_info WHERE accId = ? AND worldId = ?")) {
                ps.setInt(1, getClient().getAccID());
                ps.setInt(2, getWorld());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    gamePoints = rs.getInt("gamePoints");
                    Timestamp updateTime = rs.getTimestamp("updateTime");
                    Calendar sqlcal = Calendar.getInstance();
                    if (updateTime != null) {
                        sqlcal.setTimeInMillis(updateTime.getTime());
                    }
                    if ((sqlcal.get(5) + 1 <= Calendar.getInstance().get(5)) || (sqlcal.get(2) + 1 <= Calendar.getInstance().get(2)) || (sqlcal.get(1) + 1 <= Calendar.getInstance().get(1))) {
                        gamePoints = 0;
                        PreparedStatement psu = con.prepareStatement("UPDATE accounts_info SET gamePoints = 0, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?");

                        psu.setInt(1, getClient().getAccID());
                        psu.setInt(2, getWorld());
                        psu.executeUpdate();
                        psu.close();
                    }
                } else {
                    PreparedStatement psu = con.prepareStatement("INSERT INTO accounts_info (accId, worldId, gamePoints) VALUES (?, ?, ?)");

                    psu.setInt(1, getClient().getAccID());
                    psu.setInt(2, getWorld());
                    psu.setInt(3, 0);
                    psu.executeUpdate();
                    psu.close();
                }
                rs.close();
            }
            return gamePoints;
        } catch (SQLException Ex) {
            System.err.println("���ɫ�ʺŵ�����ʱ�����ִ��� - ���ݿ��ѯʧ��1" + Ex);
        }
        return -1;
    }

    public int getGamePointsPD() {
        try {
            int gamePointsPD = 0;
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts_info WHERE accId = ? AND worldId = ?")) {
                ps.setInt(1, getClient().getAccID());
                ps.setInt(2, getWorld());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    gamePointsPD = rs.getInt("gamePointspd");
                    Timestamp updateTime = rs.getTimestamp("updateTime");
                    Calendar sqlcal = Calendar.getInstance();
                    if (updateTime != null) {
                        sqlcal.setTimeInMillis(updateTime.getTime());
                    }
                    if ((sqlcal.get(5) + 1 <= Calendar.getInstance().get(5)) || (sqlcal.get(2) + 1 <= Calendar.getInstance().get(2)) || (sqlcal.get(1) + 1 <= Calendar.getInstance().get(1))) {
                        gamePointsPD = 0;
                        PreparedStatement psu = con.prepareStatement("UPDATE accounts_info SET gamePointspd = 0, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?");

                        psu.setInt(1, getClient().getAccID());
                        psu.setInt(2, getWorld());
                        psu.executeUpdate();
                        psu.close();
                    }
                } else {
                    try (PreparedStatement psu = con.prepareStatement("INSERT INTO accounts_info (accId, worldId, gamePointspd) VALUES (?, ?, ?)")) {
                        psu.setInt(1, getClient().getAccID());
                        psu.setInt(2, getWorld());
                        psu.setInt(3, 0);
                        psu.executeUpdate();
                    }
                }
                rs.close();
            }
            return gamePointsPD;
        } catch (SQLException Ex) {
            System.err.println("���ɫ�ʺŵ�����ʱ�����ִ��� - ���ݿ��ѯʧ��2" + Ex);
        }
        return -1;
    }

    public void gainGamePoints(int amount) {
        int gamePoints = getGamePoints() + amount;
        updateGamePoints(gamePoints);
    }

    public void gainGamePointsPD(int amount) {
        int gamePointsPD = getGamePointsPD() + amount;
        updateGamePointsPD(gamePointsPD);
    }

    public void resetGamePointsPD() {
        updateGamePointsPD(0);
    }

    public void updateGamePointsPD(int amount) {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE accounts_info SET gamePointspd = ?, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?");

            ps.setInt(1, amount);
            ps.setInt(2, getClient().getAccID());
            ps.setInt(3, getWorld());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("���½�ɫ�ʺŵ�����ʱ����ִ��� - ���ݿ����ʧ��." + Ex);
        }
    }

    public void resetGamePoints() {
        updateGamePoints(0);
    }

    public void updateGamePoints(int amount) {
        try {
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("UPDATE accounts_info SET gamePoints = ?, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?")) {
                ps.setInt(1, amount);
                ps.setInt(2, getClient().getAccID());
                ps.setInt(3, getWorld());
                ps.executeUpdate();
            }
        } catch (SQLException Ex) {
            System.err.println("���½�ɫ�ʺŵ�����ʱ����ִ��� - ���ݿ����ʧ��." + Ex);
        }
    }

    public int getGamePointsRQ() {
        try {
            int gamePointsRQ = 0;
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts_info WHERE accId = ? AND worldId = ?")) {
                ps.setInt(1, getClient().getAccID());
                ps.setInt(2, getWorld());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        gamePointsRQ = rs.getInt("gamePointsrq");
                        Timestamp updateTime = rs.getTimestamp("updateTime");
                        Calendar sqlcal = Calendar.getInstance();
                        if (updateTime != null) {
                            sqlcal.setTimeInMillis(updateTime.getTime());
                        }
                        if ((sqlcal.get(5) + 1 <= Calendar.getInstance().get(5)) || (sqlcal.get(2) + 1 <= Calendar.getInstance().get(2)) || (sqlcal.get(1) + 1 <= Calendar.getInstance().get(1))) {
                            gamePointsRQ = 0;
                            try (PreparedStatement psu = con.prepareStatement("UPDATE accounts_info SET gamePointsrq = 0, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?")) {
                                psu.setInt(1, getClient().getAccID());
                                psu.setInt(2, getWorld());
                                psu.executeUpdate();
                            }
                        }
                    } else {
                        try (PreparedStatement psu = con.prepareStatement("INSERT INTO accounts_info (accId, worldId, gamePointsrq) VALUES (?, ?, ?)")) {
                            psu.setInt(1, getClient().getAccID());
                            psu.setInt(2, getWorld());
                            psu.setInt(3, 0);
                            psu.executeUpdate();
                        }
                    }
                }
            }
            return gamePointsRQ;
        } catch (SQLException Ex) {
            System.err.println("���ɫ�ʺŵ�����ʱ�����ִ��� - ���ݿ��ѯʧ��3" + Ex);
        }
        return -1;
    }

    public void gainGamePointsRQ(int amount) {
        int gamePointsRQ = getGamePointsRQ() + amount;
        updateGamePointsRQ(gamePointsRQ);
    }

    public void resetGamePointsRQ() {
        updateGamePointsRQ(0);
    }

    public void updateGamePointsRQ(int amount) {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE accounts_info SET gamePointsrq = ?, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?");

            ps.setInt(1, amount);
            ps.setInt(2, getClient().getAccID());
            ps.setInt(3, getWorld());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("���½�ɫ�ʺŵ�����ʱ����ִ��� - ���ݿ����ʧ��." + Ex);
        }
    }

    public long getDeadtime() {
        return this.deadtime;
    }

    public void setDeadtime(long deadtime) {
        this.deadtime = deadtime;
    }

    //���߾���
    public void increaseEquipExp(int mobexp) {
        MapleItemInformationProvider mii = MapleItemInformationProvider.getInstance();
        try {
            for (IItem item : getInventory(MapleInventoryType.EQUIPPED).list()) {
                Equip nEquip = (Equip) item;
                String itemName = mii.getName(nEquip.getItemId());
                if (itemName == null) {
                    continue;
                }
                int ��ߵȼ� = gui.Start.ConfigValuesMap.get("װ����ߵȼ�");
                if (gui.Start.ConfigValuesMap.get("������������") == 0) {
                    if ((itemName.contains("����") && nEquip.getEquipLevel() < ��ߵȼ�)) {
                        nEquip.gainItemExp(client, mobexp, itemName.contains("����"));
                    }
                }
                if (gui.Start.ConfigValuesMap.get("������������") == 0) {
                    if ((itemName.contains("����") && nEquip.getEquipLevel() < ��ߵȼ�)) {
                        nEquip.gainItemExp(client, mobexp, itemName.contains("����"));
                    }
                }
                /*if (itemName.contains("����") && nEquip.getEquipLevel() < 100) {
                    nEquip.gainItemExp(client, mobexp, itemName.contains("����"));
                }*/

            }
        } catch (Exception ex) {
        }
    }

    public void reloadC() {
        client.sendPacket(MaplePacketCreator.getCharInfo(client.getPlayer()));
        client.getPlayer().getMap().removePlayer(client.getPlayer());
        client.getPlayer().getMap().addPlayer(client.getPlayer());
    }

    public void maxSkills() {
        for (ISkill sk : SkillFactory.getAllSkills()) {
            changeSkillLevel(sk, sk.getMaxLevel(), sk.getMaxLevel());
        }
    }

    public void UpdateCash() {
        getClient().sendPacket(MaplePacketCreator.showCharCash(this));
    }

    public static String getAriantRoomLeaderName(int room) {
        return ariantroomleader[room];
    }

    public static int getAriantSlotsRoom(int room) {
        return ariantroomslot[room];
    }

    public static void removeAriantRoom(int room) {
        ariantroomleader[room] = "";
        ariantroomslot[room] = 0;
    }

    public static void setAriantRoomLeader(int room, String charname) {
        ariantroomleader[room] = charname;
    }

    public static void setAriantSlotRoom(int room, int slot) {
        ariantroomslot[room] = slot;
    }

    public void addAriantScore() {
        ariantScore++;
    }

    public void resetAriantScore() {
        ariantScore = 0;
    }

    public int getAriantScore() { // TODO: code entire score system
        return ariantScore;
    }

    public void updateAriantScore() {
        getMap().broadcastMessage(MaplePacketCreator.updateAriantScore(this.getName(), getAriantScore(), false));
    }

    public int getAveragePartyLevel() {
        int averageLevel = 0, size = 0;
        for (MaplePartyCharacter pl : getParty().getMembers()) {
            averageLevel += pl.getLevel();
            size++;
        }
        if (size <= 0) {
            return level;
        }
        averageLevel /= size;
        return averageLevel;
    }

    public int getAverageMapLevel() {
        int averageLevel = 0, size = 0;
        for (MapleCharacter pl : getMap().getCharacters()) {
            averageLevel += pl.getLevel();
            size++;
        }
        if (size <= 0) {
            return level;
        }
        averageLevel /= size;
        return averageLevel;
    }

    public void setApprentice(int app) {
        this.apprentice = app;
    }

    public boolean hasApprentice() {
        if (apprentice > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getMaster() {
        return this.master;
    }

    public int getApprentice() {
        return this.apprentice;
    }

    public MapleCharacter getApp() {
        return client.getChannelServer().getPlayerStorage().getCharacterById(this.apprentice);
    }

    public MapleCharacter getMster() {
        return client.getChannelServer().getPlayerStorage().getCharacterById(this.master);
    }

    public void setMaster(int mstr) {
        this.master = mstr;
    }

    public MapleRing getMarriageRing(boolean incluedEquip) {
        MapleInventory iv = getInventory(MapleInventoryType.EQUIPPED);
        Collection<IItem> equippedC = iv.list();
        List<Item> equipped = new ArrayList<>(equippedC.size());
        MapleRing ring;
        for (IItem item : equippedC) {
            equipped.add((Item) item);
        }
        for (Item item : equipped) {
            if (item.getRing() != null) {
                ring = item.getRing();
                ring.setEquipped(true);
                if (GameConstants.isMarriageRing(item.getItemId())) {
                    return ring;
                }
            }
        }
        if (incluedEquip) {
            iv = getInventory(MapleInventoryType.EQUIP);
            for (IItem item : iv.list()) {
                if (item.getRing() != null && GameConstants.isMarriageRing(item.getItemId())) {
                    ring = item.getRing();
                    ring.setEquipped(false);
                    return ring;
                }
            }
        }
        return null;
    }

    public void setDebugMessage(boolean control) {
        DebugMessage = control;
    }

    public boolean getDebugMessage() {
        return DebugMessage;
    }

    public int getNX() {
        return getCSPoints(1);
    }

    public final boolean canHold(final int itemid) {
        return getInventory(GameConstants.getInventoryType(itemid)).getNextFreeSlot() > -1;
    }

    public final boolean �ж��ܷ���(final int itemid) {
        return getInventory(GameConstants.getInventoryType(itemid)).getNextFreeSlot() > -1;
    }

    public int getIntRecord(int questID) {
        final MapleQuestStatus stat = getQuestNAdd(MapleQuest.getInstance(questID));
        if (stat.getCustomData() == null) {
            stat.setCustomData("0");
        }
        return Integer.parseInt(stat.getCustomData());
    }

    public int getIntNoRecord(int questID) {
        final MapleQuestStatus stat = getQuestNoAdd(MapleQuest.getInstance(questID));
        if (stat == null || stat.getCustomData() == null) {
            return 0;
        }
        return Integer.parseInt(stat.getCustomData());
    }

    public void updatePetEquip() {
        if (getIntNoRecord(122221) > 0) {
            client.sendPacket(MaplePacketCreator.petAutoHP(getIntRecord(122221)));
        }
        if (getIntNoRecord(122222) > 0) {
            client.sendPacket(MaplePacketCreator.petAutoMP(getIntRecord(122222)));
        }
    }

    public void spawnBomb() {
        final MapleMonster bomb = MapleLifeFactory.getMonster(9300166);
        bomb.changeLevel(250, true);
        getMap().spawnMonster_sSack(bomb, getPosition(), -2);
        EventTimer.getInstance().schedule(new Runnable() {

            @Override
            public void run() {
                map.killMonster(bomb, client.getPlayer(), false, false, (byte) 1);
            }
        }, 10 * 1000);
    }

    public boolean isAriantPQMap() {
        switch (getMapId()) {
            case 980010101:
            case 980010201:
            case 980010301:
                return true;
        }
        return false;
    }
    private int MobVac = 0, MobVac2 = 0;

    public void addMobVac(int type) {
        if (type == 1) {
            MobVac++;
        } else if (type == 2) {
            MobVac2++;
        }
    }

    public int getMobVac(int type) {
        if (type == 1) {
            return MobVac;
        } else if (type == 2) {
            return MobVac2;
        } else {
            return 0;
        }
    }

    public int highSpeedGamageCount = 0;//������

    private int mount_id = 0;

    public int getMountId() {
        return mount_id;
    }

    public void setMountId(int id) {
        mount_id = id;
    }

    public void gainIten(int id, int amount) {
        MapleInventoryManipulator.addById(getClient(), id, (short) amount, (byte) 0);
    }

    public long getLastHM() {
        return lastGainHM;
    }

    public void setLastHM(long newTime) {
        lastGainHM = newTime;
    }

    public String getServerName() {
        return MapleParty.��������;//var MC = cm.etServerName();
    }

    //public String getip() {
    //   return ��IP;//var MC = cm.etServerName();
    //}
    /**
     * ��pvpkills
     *
     * @return
     */
    public int GetPvpkills() {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Pvpkills");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public int Get�̳���Ʒ() {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cashshop_modified_items WHERE serial = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("meso");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public void Gain�̳���Ʒ(int Piot) {
        try {
            int ret = GetPvpkills();
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO cashshop_modified_items (serial,meso) VALUES (?, ?)");
                    ps.setInt(1, id);
                    ps.setInt(2, ret);

                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e);
                    }
                }
            }
            ret += Piot;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE cashshop_modified_items SET `meso` = ? WHERE serial = ?");
            ps.setInt(1, ret);
            ps.setInt(2, id);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("�@�e�`!!55" + sql);
        }
    }

    /**
     * *
     * ���ӵ���
     *
     * @param Name
     * @param Channale
     * @param Piot
     */
    public void Gainguilds(int Piot) {
        try {
            int ret = GetPvpkills();
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO characters (id,guilds) VALUES (?, ?)");
                    ps.setInt(1, id);
                    ps.setInt(2, ret);

                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e);
                    }
                }
            }
            ret += Piot;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE characters SET `guilds` = ? WHERE id = ?");
            ps.setInt(1, ret);
            ps.setInt(2, id);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("�@�e�`!!55" + sql);
        }
    }

    public void GainPvpkills(int Piot) {
        try {
            int ret = GetPvpkills();
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO characters (id,Pvpkills) VALUES (?, ?)");
                    ps.setInt(1, id);
                    ps.setInt(2, ret);

                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e);
                    }
                }
            }
            ret += Piot;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE characters SET `Pvpkills` = ? WHERE id = ?");
            ps.setInt(1, ret);
            ps.setInt(2, id);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("�@�e�`!!55" + sql);
        }
    }

    /**
     * ��pvpkills
     *
     * @return
     */
    public int GetPvpdeaths() {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Pvpdeaths");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    /**
     * *
     * ���ӵ���
     *
     * @param Name
     * @param Channale
     * @param Piot
     */
    public void GainPvpdeaths(int Piot) {
        try {
            int ret = GetPvpdeaths();
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO characters (id,Pvpdeaths) VALUES (?, ?)");
                    ps.setInt(1, id);
                    ps.setInt(2, ret);

                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e);
                    }
                }
            }
            ret += Piot;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE characters SET `Pvpdeaths` = ? WHERE id = ?");
            ps.setInt(1, ret);
            ps.setInt(2, id);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("�@�e�`!!55" + sql);
        }
    }

    public static String c() {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"wmic", "cpu", "get", "ProcessorId"});
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream());
            sc.next();
            return sc.next();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "aaaaaa";
    }

    public static String Cc() {
        String result = "";
        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_Processor\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.ProcessorId \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";

            // + "    exit for  \r\n" + "Next";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
            file.delete();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        if (result.trim().length() < 1 || result == null) {
            result = "��CPU_ID����";
        }
        return result.trim();
    }

    // ����װ����ħ��������
    private Map<Integer, Integer> _equippedFuMoMap = new HashMap<>();

    public Map<Integer, Integer> getEquippedFuMoMap() {
        return _equippedFuMoMap;
    }

    public Map<Integer, Integer> F() {
        return _equippedFuMoMap;
    }

    public void ˢ������װ����ħ��������() {
        _equippedFuMoMap.clear();
        Connection con = DatabaseConnection.getConnection();

        String sqlQuery1 = "select b.mxmxd_dakong_fumo from inventoryitems a, inventoryequipment b where a.inventoryitemid = b.inventoryitemid and a.characterid = ? and a.inventorytype = -1 and b.mxmxd_dakong_fumo != '' and b.mxmxd_dakong_fumo is not NULL";
        try {
            PreparedStatement ps = con.prepareStatement(sqlQuery1);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String mxmxdDaKongFuMo = rs.getString("mxmxd_dakong_fumo");
                    if (mxmxdDaKongFuMo != null && mxmxdDaKongFuMo.length() > 0) {
                        String arr[] = mxmxdDaKongFuMo.split(",");
                        for (String pair : arr) {
                            if (pair.length() == 0) {
                                continue;
                            }
                            String arr2[] = pair.split(":");
                            int fumoType = Integer.parseInt(arr2[0]);
                            int fumoVal = Integer.parseInt(arr2[1]);
                            if (_equippedFuMoMap.containsKey(fumoType)) {
                                _equippedFuMoMap.put(fumoType, _equippedFuMoMap.get(fumoType) + fumoVal);
                            } else {
                                _equippedFuMoMap.put(fumoType, fumoVal);
                            }
                        }
                    }
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("ˢ������װ����ħ�������ݳ�����" + ex.getMessage());
        }
    }

    //cm.getPlayer().��ħ����ֵ(6)
    public int ��ȡ��ħ����ֵ(int fumoType) {
        int val = 0;
        if (_equippedFuMoMap.containsKey(fumoType)) {
            return _equippedFuMoMap.get(fumoType);
        }

        return 0;
    }

    public static int rand(int lbound, int ubound) {
        return (int) ((Math.random() * (ubound - lbound + 1)) + lbound);
    }

    private ScheduledFuture<?> �������߳� = null;
    public int ���������� = 0;
    public int �������� = 0;
    public int �н����� = 0;
    public int ���������� = 0;
    public boolean �Ƿ��н� = false;
    public int �н����� = gui.Start.ConfigValuesMap.get("�������н�����");
    public int �н������� = gui.Start.ConfigValuesMap.get("������������������");

    public void ����������() {
        �������߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                int ���� = gui.Start.ConfigValuesMap.get("����������");
                int �������������� = gui.Start.ConfigValuesMap.get("��������������");
                int ������������������ = gui.Start.ConfigValuesMap.get("������������������");
                int ����������ϡ���� = gui.Start.ConfigValuesMap.get("����������ϡ����");
                boolean �Ƿ��н� = false;
                boolean �򶹶� = false;
                //int ���� = 0;
                int �������� = 0;
                int �������� = 0;
                //
                //final double ���� = Math.ceil(Math.random() * ����2);
                final double �ٷ��� = Math.ceil(Math.random() * 100);
                �������� = rand(0, ����������ϡ����);//�������Լ���..�������..��
                if (���������� >= ����) {
                    //���� = (int) rand;
                    �򶹶� = true;
                }
                if (������������������ >= �ٷ��� && �������� == 0) {
                    �Ƿ��н� = true;
                    �������� = 1;
                }
                switch (��������) {
                    case 3:
                        �������� = 3;
                        �������������� += gui.Start.ConfigValuesMap.get("�춹���ӿ�����");
                        dropMessage(5, "[�н���]:+ " + gui.Start.ConfigValuesMap.get("�춹���ӿ�����") + " %");
                        break;
                    case 2:
                        �������� = 2;
                        �������������� += gui.Start.ConfigValuesMap.get("�������ӿ�����");
                        dropMessage(5, "[�н���]:+ " + gui.Start.ConfigValuesMap.get("�������ӿ�����") + " %");
                        break;
                    case 1:
                        �������� = 1;
                        �������������� += gui.Start.ConfigValuesMap.get("�̶����ӿ�����");
                        dropMessage(5, "[�н���]:+ " + gui.Start.ConfigValuesMap.get("�̶����ӿ�����") + " %");
                        break;
                    default:
                        �������� = 0;
                        break;
                }
                if (�򶹶�) {
                    if (�������������� >= �ٷ���) {
                        if (�Ƿ��н�) {
                            MapleCharacter.this.getClient().sendPacket(MaplePacketCreator.��ʼ����(1, (int) 4343, (byte) 0, (short) 0, ��������));
                        } else {
                            MapleCharacter.this.getClient().sendPacket(MaplePacketCreator.��ʼ����(1, (int) ����������, (byte) 0, (short) 0, ��������));
                        }
                    } else {
                        MapleCharacter.this.getClient().sendPacket(MaplePacketCreator.��ʼ����(1, (int) ����������, (byte) 0, (short) 0, ��������));
                    }
                } else {
                    MapleCharacter.this.getClient().sendPacket(MaplePacketCreator.��ʼ����(1, (int) ����������, (byte) 0, (short) 0, ��������));
                }
            }
        }, 1000);
    }

    public void �رն�����() {
        if (�������߳� != null) {
            �������߳�.cancel(true);
            �������߳� = null;
        }
    }
    private String customData;

    public final void setCustomData(final String customData) {
        this.customData = customData;
    }

    public final String getCustomData() {
        return customData;
    }
    /**
     * <�����߳�>
     */
    private ScheduledFuture<?> �����߳� = null;

    public void �����߳�() {
        if (�����߳� == null) {
            System.err.println("[�����]" + CurrentReadable_Time() + " : �����ȶ���```���߳� �� " + name);
            �����߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    final MapleQuest quest = MapleQuest.getInstance(8593);
                    final MapleQuestStatus stats = getQuestNAdd(quest);
                    int oldValue = stats.getCustomData() == null ? 0 : Integer.valueOf(stats.getCustomData());
                    stats.setCustomData(String.valueOf(oldValue + 2500));

                }
            }, 1000 * 10);
        }
    }

    public void �ر������߳�() {
        if (�����߳� != null) {
            �����߳�.cancel(false);
            //System.err.println("[�����]" + CurrentReadable_Time() + " : �ر��ȶ������߳� �� " + name);
            �����߳� = null;
        }
    }
    /**
     * <�����߳�>
     */
    private ScheduledFuture<?> �����߳� = null;

    public void �����߳�() {
        if (��Ҷ�߳� != null) {
            ��Ҷ�߳�.cancel(false);
            ��Ҷ�߳� = null;
        }
        if (�����߳� != null) {
            �����߳�.cancel(false);
            �����߳� = null;
            ������ = 0;
            �����һ�γ�ʼ�� = 0;
        }
        if (ԡͰ�߳� != null) {
            ԡͰ�߳�.cancel(false);
            ԡͰ�߳� = null;
            ԡͰ��һ�γ�ʼ�� = 0;
        }
        if (�����߳� == null) {
            �����߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    if (World.Find.findChannel(name) == 2) {
                        gainExp(1, false, false, false);
                    } else {
                        �رձ����߳�();
                    }
                }
            }, 1000 * 60 * 1);
        } else if (�����߳� != null) {
            �����߳�.cancel(false);
            �����߳� = null;
        }
    }

    public void �رձ����߳�() {
        if (�����߳� != null) {
            �����߳�.cancel(false);
            �����߳� = null;
        }
    }
    /**
     * <��������>
     */
    private ScheduledFuture<?> ���������߳�2 = null;

    public void ���������߳�() {
        if (���������߳�2 == null) {
            ���������߳�2 = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    if (client.getChannel() == 2) {
                        if (getMapId() == 230000000) {
                            ��������++;
                            getClient().sendPacket(MaplePacketCreator.sendHint("#b<���ֵ>\r\n\r\n#e#r " + �������� + "\r\n", 200, 6));
                        } else {
                            �ر����������߳�();
                            �������� = 0;
                            dropMessage(5, "�뿪���ͼ����ֵ����");
                        }
                    } else {
                        �ر����������߳�();
                        �������� = 0;
                        dropMessage(5, "�뿪�Ƶ������ֵ����");
                    }
                }
            }, 1000 * 5);
        }
    }

    public void �ر����������߳�() {
        if (���������߳�2 != null) {
            ���������߳�2.cancel(false);
            ���������߳�2 = null;
        }
    }

    /**
     * <��Ҷ����>
     */
    private ScheduledFuture<?> ��Ҷ�߳� = null;
    public int ��Ҷʱ�� = gui.Start.ConfigValuesMap.get("��Ҷ����ʼ��");
    public int ��Ҷ��� = gui.Start.ConfigValuesMap.get("��Ҷ����ʽ��");
    public int ��Ҷ���� = gui.Start.ConfigValuesMap.get("��Ҷ����ʾ���");
    public int ��Ҷ���� = gui.Start.ConfigValuesMap.get("��Ҷ���������");
    public int x = 0;

    public void ��Ҷ����() {
        ��Ҷ�߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (x > 0) {
                    int ��ͼ���� = getMap().getCharactersSize();
                    if (��ͼ���� >= ��Ҷ����) {
                        ��ͼ���� = ��Ҷ����;
                    }
                    /*if (��Ҷ�߳� != null) {
                        getMap().broadcastMessage(MaplePacketCreator.getChatText(id, ��һ�仰, isGM(), 1));
                    }*/
                    gainMeso(��Ҷ��� * ��ͼ����, true);
                    gainExp(��Ҷ���� * ��ͼ����, true, true, false);
                } else {
                    x++;
                }
            }
        }, 1000 * ��Ҷʱ��);
    }

    public void �رշ�Ҷ�߳�() {
        if (��Ҷ�߳� != null) {
            ��Ҷ�߳�.cancel(false);
            ��Ҷ�߳� = null;
            x = 0;
            getMap().broadcastMessage(MaplePacketCreator.getChatText(id, "Hi~�Ҵ����������ˡ�", isGM(), 1));
        }
    }

    /**
     * <��������>
     */
    private ScheduledFuture<?> �����߳� = null;
    public int ������ = 1;
    public int �����һ�γ�ʼ�� = 0;
    public int �������� = gui.Start.ConfigValuesMap.get("��������1");
    public int ����ʱ�� = gui.Start.ConfigValuesMap.get("��������2");
    public int ������� = gui.Start.ConfigValuesMap.get("��������3");

    public void ����() {
        �����߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (�����һ�γ�ʼ�� > 0) {
                    if (������ > ��������) {
                        gainMeso(��������, true);
                    } else {
                        gainMeso(������, true);
                        ������ += �������;
                    }
                } else {
                    �����һ�γ�ʼ��++;
                }
            }
        }, 1000 * ����ʱ��);
    }

    public void �رղ����߳�() {
        if (�����߳� != null) {
            �����߳�.cancel(false);
            �����߳� = null;
            ������ = 0;
            �����һ�γ�ʼ�� = 0;
        }
    }
    /**
     * <����ԡͰ>
     */
    private ScheduledFuture<?> ԡͰ�߳� = null;
    public int ԡͰ��һ�γ�ʼ�� = 0;
    public int ԡͰʱ�� = gui.Start.ConfigValuesMap.get("ԡͰ����0");
    public int ԡͰһ�׶� = gui.Start.ConfigValuesMap.get("ԡͰ����1");
    public int ԡͰ���׶� = gui.Start.ConfigValuesMap.get("ԡͰ����2");
    public int ԡͰ���׶� = gui.Start.ConfigValuesMap.get("ԡͰ����3");
    public int ԡͰ�Ľ׶� = gui.Start.ConfigValuesMap.get("ԡͰ����4");
    public int ԡͰ��׶� = gui.Start.ConfigValuesMap.get("ԡͰ����5");
    public int ԡͰ���׶� = gui.Start.ConfigValuesMap.get("ԡͰ����6");
    public int ԡͰ�߽׶� = gui.Start.ConfigValuesMap.get("ԡͰ����7");
    public int ԡͰ�˽׶� = gui.Start.ConfigValuesMap.get("ԡͰ����8");
    public int ԡͰ�Ž׶� = gui.Start.ConfigValuesMap.get("ԡͰ����9");

    public void ԡͰ() {
        ԡͰ�߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (ԡͰ��һ�γ�ʼ�� > 0) {
                    int ���� = 1;
                    if (mapid == 105040402) {
                        ���� += 1;
                        dropMessage(1, "ԡͰ��ɣ�÷��ӳ֣�Ч��������");
                    }
                    if (level > 0 && level <= 20) {
                        gainExp(ԡͰһ�׶� * ����, true, true, false);
                    } else if (level > 20 && level <= 40) {
                        gainExp(ԡͰ���׶� * ����, true, true, false);
                    } else if (level > 40 && level <= 60) {
                        gainExp(ԡͰ���׶� * ����, true, true, false);
                    } else if (level > 60 && level <= 80) {
                        gainExp(ԡͰ�Ľ׶� * ����, true, true, false);
                    } else if (level > 80 && level <= 100) {
                        gainExp(ԡͰ��׶� * ����, true, true, false);
                    } else if (level > 100 && level <= 120) {
                        gainExp(ԡͰ���׶� * ����, true, true, false);
                    } else if (level > 120 && level <= 140) {
                        gainExp(ԡͰ�߽׶� * ����, true, true, false);
                    } else if (level > 140 && level <= 160) {
                        gainExp(ԡͰ�˽׶� * ����, true, true, false);
                    } else if (level > 160) {
                        gainExp(ԡͰ�Ž׶� * ����, true, true, false);
                    }

                } else {
                    ԡͰ��һ�γ�ʼ��++;
                }
            }
        }, 1000 * ԡͰʱ��);
    }

    public void �ر�ԡͰ�߳�() {
        if (ԡͰ�߳� != null) {
            ԡͰ�߳�.cancel(false);
            ԡͰ�߳� = null;
            ԡͰ��һ�γ�ʼ�� = 0;
        }
    }

    public void �򿪽���() {
        NPCScriptManager.getInstance().dispose(client);
        NPCScriptManager.getInstance().start(client, 9000011, 5);
    }

    public void ��ɱҰ��BOSS��Ч2() {
        map.broadcastMessage(MaplePacketCreator.environmentChange("dojang/end/clear", 3));
    }

    public void ��ɱҰ��BOSS��Ч() {
        // map.broadcastMessage(MaplePacketCreator.environmentChange("dojang/end/clear", 3));
    }

    public void ʱ�䵽() {
        map.broadcastMessage(MaplePacketCreator.environmentChange("summerboating/timeout", 3));
    }

    public void ��������(String a) {
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        int ret = �ж�����(a);
        ret += 1;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM jiezoudashi ");
            rs = ps.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                sqlString1 = "update jiezoudashi set Val = '" + ret + "' where Name = '" + a + "';";
                PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                Val.executeUpdate(sqlString1);
            }
        } catch (SQLException ex) {

        }

    }

    public int �ж�����(String a) {
        int data = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM jiezoudashi WHERE Name = '" + a + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data = rs.getInt("Val");
            }
            ps.close();
        } catch (SQLException ex) {
        }
        return data;
    }

    public int �ж�����2(String a) {
        int ret = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM jiezoudashi WHERE Name = `" + a + "`")) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    ret = rs.getInt("Val");
                }
            }
        } catch (SQLException ex) {
        }
        return ret;
    }

    //������Ч map.broadcastMessage(UIPacket.fishingCaught(id));
    //������Чmap.broadcastMessage(this, MaplePacketCreator.showForeignEffect(getId(), 0), false);
//    public void ���ݼ�() {
//                    keylayout.saveKeys(id);
//    }
    public void saveToDB(boolean dc, boolean fromcs) {
        Connection con = DatabaseConnection.getConnection();

        PreparedStatement ps = null;
        PreparedStatement pse = null;
        ResultSet rs = null;

        try {
            keylayout.saveKeys(id);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE characters SET level = ?, fame = ?, str = ?, dex = ?, luk = ?, `int` = ?, exp = ?, hp = ?, mp = ?, maxhp = ?, maxmp = ?, sp = ?, ap = ?, gm = ?, skincolor = ?, gender = ?, job = ?, hair = ?, face = ?, map = ?, meso = ?, hpApUsed = ?, spawnpoint = ?, party = ?, buddyCapacity = ?, monsterbookcover = ?, dojo_pts = ?, dojoRecord = ?, pets = ?, subcategory = ?, marriageId = ?, currentrep = ?, totalrep = ?, charmessage = ?, expression = ?, constellation = ?, blood = ?, month = ?, day = ?, beans = ?, prefix = ?, name = ?, mountid = ? WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, level);
            ps.setShort(2, fame);
            ps.setShort(3, stats.getStr());
            ps.setShort(4, stats.getDex());
            ps.setShort(5, stats.getLuk());
            ps.setShort(6, stats.getInt());
            ps.setInt(7, exp);
            ps.setShort(8, stats.getHp() < 1 ? 50 : stats.getHp());
            ps.setShort(9, stats.getMp());
            ps.setShort(10, stats.getMaxHp());
            ps.setShort(11, stats.getMaxMp());
            final StringBuilder sps = new StringBuilder();
            for (int i = 0; i < remainingSp.length; i++) {
                sps.append(remainingSp[i]);
                sps.append(",");
            }
            final String sp = sps.toString();
            ps.setString(12, sp.substring(0, sp.length() - 1));
            ps.setShort(13, remainingAp);
            ps.setByte(14, gmLevel);
            ps.setByte(15, skinColor);
            ps.setByte(16, gender);
            ps.setShort(17, job);
            ps.setInt(18, hair);
            ps.setInt(19, face);
            if (!fromcs && map != null) {
                if (map.getForcedReturnId() != 999999999) {
                    ps.setInt(20, map.getForcedReturnId());
                } else {
                    ps.setInt(20, stats.getHp() < 1 ? map.getReturnMapId() : map.getId());
                }
            } else {
                ps.setInt(20, mapid);
            }
            ps.setInt(21, meso);
            ps.setShort(22, hpApUsed);
            if (map == null) {
                ps.setByte(23, (byte) 0);
            } else {
                final MaplePortal closest = map.findClosestSpawnpoint(getPosition());
                ps.setByte(23, (byte) (closest != null ? closest.getId() : 0));
            }
            ps.setInt(24, party != null ? party.getId() : -1);
            ps.setShort(25, buddylist.getCapacity());
            ps.setInt(26, bookCover);
            ps.setInt(27, dojo);
            ps.setInt(28, dojoRecord);
            final StringBuilder petz = new StringBuilder();
            int petLength = 0;
            for (final MaplePet pet : pets) {
                pet.saveToDb();
                if (pet.getSummoned()) {

                    petz.append(pet.getInventoryPosition());
                    petz.append(",");
                    petLength++;
                }
            }
            while (petLength < 3) {
                petz.append("-1,");
                petLength++;
            }
            final String petstring = petz.toString();
            ps.setString(29, petstring.substring(0, petstring.length() - 1));
            ps.setByte(30, subcategory);
            ps.setInt(31, marriageId);
            ps.setInt(32, currentrep);
            ps.setInt(33, totalrep);
            ps.setString(34, charmessage);
            ps.setInt(35, expression);
            ps.setInt(36, constellation);
            ps.setInt(37, blood);
            ps.setInt(38, month);
            ps.setInt(39, day);
            ps.setInt(40, beans);
            ps.setInt(41, prefix);
            ps.setString(42, name);
            ps.setInt(43, mount_id);
            ps.setInt(44, id);

            if (ps.executeUpdate() < 1) {
                ps.close();
                throw new DatabaseException("Character not in database (" + id + ")");
            }
            ps.close();

            deleteWhereCharacterId(con, "DELETE FROM skillmacros WHERE characterid = ?");
            for (int i = 0; i < 5; i++) {
                final SkillMacro macro = skillMacros[i];
                if (macro != null) {
                    ps = con.prepareStatement("INSERT INTO skillmacros (characterid, skill1, skill2, skill3, name, shout, position) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    ps.setInt(1, id);
                    ps.setInt(2, macro.getSkill1());
                    ps.setInt(3, macro.getSkill2());
                    ps.setInt(4, macro.getSkill3());
                    ps.setString(5, macro.getName());
                    ps.setInt(6, macro.getShout());
                    ps.setInt(7, i);
                    ps.execute();
                    ps.close();
                }
            }

            deleteWhereCharacterId(con, "DELETE FROM inventoryslot WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO inventoryslot (characterid, `equip`, `use`, `setup`, `etc`, `cash`) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setByte(2, getInventory(MapleInventoryType.EQUIP).getSlotLimit());
            ps.setByte(3, getInventory(MapleInventoryType.USE).getSlotLimit());
            ps.setByte(4, getInventory(MapleInventoryType.SETUP).getSlotLimit());
            ps.setByte(5, getInventory(MapleInventoryType.ETC).getSlotLimit());
            ps.setByte(6, getInventory(MapleInventoryType.CASH).getSlotLimit());
            ps.execute();
            ps.close();

            saveInventory(con);

            deleteWhereCharacterId(con, "DELETE FROM questinfo WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO questinfo (`characterid`, `quest`, `customData`) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            for (final Entry<Integer, String> q : questinfo.entrySet()) {
                ps.setInt(2, q.getKey());
                ps.setString(3, q.getValue());
                ps.execute();
            }
            ps.close();

            deleteWhereCharacterId(con, "DELETE FROM queststatus WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO queststatus (`queststatusid`, `characterid`, `quest`, `status`, `time`, `forfeited`, `customData`) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pse = con.prepareStatement("INSERT INTO queststatusmobs VALUES (DEFAULT, ?, ?, ?)");
            ps.setInt(1, id);
            for (final MapleQuestStatus q : quests.values()) {
                ps.setInt(2, q.getQuest().getId());
                ps.setInt(3, q.getStatus());
                ps.setInt(4, (int) (q.getCompletionTime() / 1000));
                ps.setInt(5, q.getForfeited());
                ps.setString(6, q.getCustomData());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                rs.next();

                if (q.hasMobKills()) {
                    for (int mob : q.getMobKills().keySet()) {
                        pse.setInt(1, rs.getInt(1));
                        pse.setInt(2, mob);
                        pse.setInt(3, q.getMobKills(mob));
                        pse.executeUpdate();
                    }
                }
                rs.close();
            }
            ps.close();
            pse.close();

            deleteWhereCharacterId(con, "DELETE FROM skills WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO skills (characterid, skillid, skilllevel, masterlevel, expiration) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, id);

            for (final Entry<ISkill, SkillEntry> skill : skills.entrySet()) {
                if (GameConstants.isApplicableSkill(skill.getKey().getId())) { //do not save additional skills
                    ps.setInt(2, skill.getKey().getId());
                    ps.setByte(3, skill.getValue().skillevel);
                    ps.setByte(4, skill.getValue().masterlevel);
                    ps.setLong(5, skill.getValue().expiration);
                    ps.execute();
                }
            }
            ps.close();

            List<MapleCoolDownValueHolder> cd = getCooldowns();
            if (dc && cd.size() > 0) {
                ps = con.prepareStatement("INSERT INTO skills_cooldowns (charid, SkillID, StartTime, length) VALUES (?, ?, ?, ?)");
                ps.setInt(1, getId());
                for (final MapleCoolDownValueHolder cooling : cd) {
                    ps.setInt(2, cooling.skillId);
                    ps.setLong(3, cooling.startTime);
                    ps.setLong(4, cooling.length);
                    ps.execute();
                }
                ps.close();
            }

            deleteWhereCharacterId(con, "DELETE FROM savedlocations WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO savedlocations (characterid, `locationtype`, `map`) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            for (final SavedLocationType savedLocationType : SavedLocationType.values()) {
                if (savedLocations[savedLocationType.getValue()] != -1) {
                    ps.setInt(2, savedLocationType.getValue());
                    ps.setInt(3, savedLocations[savedLocationType.getValue()]);
                    ps.execute();
                }
            }
            ps.close();

            ps = con.prepareStatement("DELETE FROM achievements WHERE accountid = ?");
            ps.setInt(1, accountid);
            ps.executeUpdate();
            ps.close();
            ps = con.prepareStatement("INSERT INTO achievements(charid, achievementid, accountid) VALUES(?, ?, ?)");
            for (Integer achid : finishedAchievements) {
                ps.setInt(1, id);
                ps.setInt(2, achid);
                ps.setInt(3, accountid);
                ps.executeUpdate();
            }
            ps.close();

            deleteWhereCharacterId(con, "DELETE FROM buddies WHERE characterid = ?");
            ps = con.prepareStatement("INSERT INTO buddies (characterid, `buddyid`, `pending`) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            for (BuddyEntry entry : buddylist.getBuddies()) {
                if (entry != null) {
                    ps.setInt(2, entry.getCharacterId());
                    ps.setInt(3, entry.isVisible() ? 0 : 1);
                    ps.execute();
                }
            }
            ps.close();

            ps = con.prepareStatement("UPDATE accounts SET `ACash` = ?, `mPoints` = ?, `points` = ?, `vpoints` = ? WHERE id = ?");
            ps.setInt(1, acash);
            ps.setInt(2, maplepoints);
            ps.setInt(3, points);
            ps.setInt(4, vpoints);
            ps.setInt(5, client.getAccID());
            ps.execute();
            ps.close();

            if (storage != null) {
                storage.saveToDB();
            }

            ps = con.prepareStatement("UPDATE accounts SET `lastGainHM` = ? WHERE id = ?");
            ps.setLong(1, lastGainHM);
            ps.setInt(2, client.getAccID());
            ps.execute();
            ps.close();

            if (cs != null) {
                cs.save();
            }
            PlayerNPC.updateByCharId(this);
            //keylayout.saveKeys(id);
            mount.saveMount(id);
            monsterbook.saveCards(id);

            deleteWhereCharacterId(con, "DELETE FROM wishlist WHERE characterid = ?");
            for (int i = 0; i < getWishlistSize(); i++) {
                ps = con.prepareStatement("INSERT INTO wishlist(characterid, sn) VALUES(?, ?) ");
                ps.setInt(1, getId());
                ps.setInt(2, wishlist[i]);
                ps.execute();
                ps.close();
            }

            deleteWhereCharacterId(con, "DELETE FROM trocklocations WHERE characterid = ?");
            for (int i = 0; i < rocks.length; i++) {
                if (rocks[i] != 999999999) {
                    ps = con.prepareStatement("INSERT INTO trocklocations(characterid, mapid) VALUES(?, ?) ");
                    ps.setInt(1, getId());
                    ps.setInt(2, rocks[i]);
                    ps.execute();
                    ps.close();
                }
            }

            deleteWhereCharacterId(con, "DELETE FROM regrocklocations WHERE characterid = ?");
            for (int i = 0; i < regrocks.length; i++) {
                if (regrocks[i] != 999999999) {
                    ps = con.prepareStatement("INSERT INTO regrocklocations(characterid, mapid) VALUES(?, ?) ");
                    ps.setInt(1, getId());
                    ps.setInt(2, regrocks[i]);
                    ps.execute();
                    ps.close();
                }
            }

            con.commit();
        } catch (SQLException | DatabaseException | UnsupportedOperationException e) {
            FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, e);
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, ex);

            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (pse != null) {
                    pse.close();
                }
                if (rs != null) {
                    rs.close();
                }
                con.setAutoCommit(true);
                con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            } catch (SQLException e) {
                FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, e);

            }
        }
    }
}