package client.anticheat;

import java.awt.Point;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import constants.GameConstants;
import client.MapleCharacter;
import handling.world.World;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import server.AutobanManager;
import server.Timer.CheatTimer;
import tools.FileoutputUtil;
import tools.MaplePacketCreator;
import tools.StringUtil;

public class CheatTracker {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock rL = lock.readLock(), wL = lock.writeLock();
    private final Map<CheatingOffense, CheatingOffenseEntry> offenses = new LinkedHashMap<CheatingOffense, CheatingOffenseEntry>();
    private final WeakReference<MapleCharacter> chr;
    // For keeping track of speed attack hack.
    private int lastAttackTickCount = 0;
    private byte Attack_tickResetCount = 0;
    private long Server_ClientAtkTickDiff = 0;
    private long lastDamage = 0;
    private long takingDamageSince;
    private int numSequentialDamage = 0;
    private long lastDamageTakenTime = 0;
    private byte numZeroDamageTaken = 0;
    private int numSequentialSummonAttack = 0;
    private long summonSummonTime = 0;
    private int numSameDamage = 0;
    private Point lastMonsterMove;
    private int monsterMoveCount;
    private int attacksWithoutHit = 0;
    private byte dropsPerSecond = 0;
    private long lastDropTime = 0;
    private byte msgsPerSecond = 0;
    private long lastMsgTime = 0;
    private ScheduledFuture<?> invalidationTask;
    private int gm_message = 50;
    private int lastTickCount = 0, tickSame = 0;
    private long lastASmegaTime = 0;
    private long[] lastTime = new long[6];

    public CheatTracker(final MapleCharacter chr) {
        this.chr = new WeakReference<MapleCharacter>(chr);
        invalidationTask = CheatTimer.getInstance().register(new InvalidationTask(), 60000);
        takingDamageSince = System.currentTimeMillis();
    }
//    public final void ���ټ��(final MapleCharacter player ) {
//        //սʿְҵȺ
//        if (player.getJob() >= 100 && player.getJob() < 200) {
//            //Ӣ��ְҵȺ
//            if (player.getJob() == 112) {
//                long nowTimestamp = System.currentTimeMillis();
//                int Ӣ�۹��ټ�� = gui.Start.ConfigValuesMap.get("Ӣ�۹��ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < Ӣ�۹��ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX112");
//                    return;
//                }
//            } else if (player.getJob() == 122) {
//                long nowTimestamp = System.currentTimeMillis();
//                int ʥ��ʿ���ټ�� = gui.Start.ConfigValuesMap.get("ʥ��ʿ���ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < ʥ��ʿ���ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX122");
//                    return;
//                }
//            } else if (player.getJob() == 132) {
//                long nowTimestamp = System.currentTimeMillis();
//                int ����ʿ���ټ�� = gui.Start.ConfigValuesMap.get("����ʿ���ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < ����ʿ���ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX132");
//                    return;
//                }
//            } else if (player.getJob() == 111) {
//                long nowTimestamp = System.currentTimeMillis();
//                int ��ʿ���ټ�� = gui.Start.ConfigValuesMap.get("��ʿ���ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < ��ʿ���ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX111");
//                    return;
//                }
//            } else if (player.getJob() == 121) {
//                long nowTimestamp = System.currentTimeMillis();
//                int ��ʿ���ټ�� = gui.Start.ConfigValuesMap.get("��ʿ���ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < ��ʿ���ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX121");
//                    return;
//                }
//            } else if (player.getJob() == 131) {
//                long nowTimestamp = System.currentTimeMillis();
//                int ����ʿ���ټ�� = gui.Start.ConfigValuesMap.get("����ʿ���ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < ����ʿ���ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX131");
//                    return;
//                }
//            } else if (player.getJob() == 110) {
//                long nowTimestamp = System.currentTimeMillis();
//                int ���͹��ټ�� = gui.Start.ConfigValuesMap.get("���͹��ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < ���͹��ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX110");
//                    return;
//                }
//            } else if (player.getJob() == 120) {
//                long nowTimestamp = System.currentTimeMillis();
//                int ׼��ʿ���ټ�� = gui.Start.ConfigValuesMap.get("׼��ʿ���ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < ׼��ʿ���ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX120");
//                    return;
//                }
//            } else if (player.getJob() == 130) {
//                long nowTimestamp = System.currentTimeMillis();
//                int ǹսʿ���ټ�� = gui.Start.ConfigValuesMap.get("ǹսʿ���ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < ǹսʿ���ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX130");
//                    return;
//                }
//            } else if (player.getJob() == 100) {
//                
//                long nowTimestamp = System.currentTimeMillis();
//                int սʿ���ټ�� = gui.Start.ConfigValuesMap.get("սʿ���ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < սʿ���ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX100");
//                    return;
//                }
//            }
//        }
//        //ħ��ʦְҵ��
//        if (player.getJob() >= 200 && player.getJob() < 300) {
//            if (player.getJob() == 200) {
//                long nowTimestamp = System.currentTimeMillis();
//                int ħ��ʦ���ټ�� = gui.Start.ConfigValuesMap.get("ħ��ʦ���ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < ħ��ʦ���ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX200");
//                    return;
//                }
//            } else if (player.getJob() == 212) {
//                long nowTimestamp = System.currentTimeMillis();
//                int ��ħ��ʦ���ټ�� = gui.Start.ConfigValuesMap.get("��ħ��ʦ���ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < ��ħ��ʦ���ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX212");
//                    return;
//                }
//            } else if (player.getJob() == 222) {
//                long nowTimestamp = System.currentTimeMillis();
//                int ����ħ��ʦ���ټ�� = gui.Start.ConfigValuesMap.get("����ħ��ʦ���ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < ����ħ��ʦ���ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX222");
//                    return;
//                }
//            } else if (player.getJob() == 232) {
//                long nowTimestamp = System.currentTimeMillis();
//                int ���̹��ټ�� = gui.Start.ConfigValuesMap.get("���̹��ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < ���̹��ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX232");
//                    return;
//                }
//            } else if (player.getJob() == 211) {
//                long nowTimestamp = System.currentTimeMillis();
//                int ����ʦ���ټ�� = gui.Start.ConfigValuesMap.get("����ʦ���ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < ����ʦ���ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX211");
//                    return;
//                }
//             } else if (player.getJob() == 211) {
//                long nowTimestamp = System.currentTimeMillis();
//                int ����ʦ���ټ�� = gui.Start.ConfigValuesMap.get("����ʦ���ټ��");
//                if (nowTimestamp - player.��ɫ���ټ�� < ����ʦ���ټ��) {
//                    player.highSpeedGamageCount++;
//                } else {
//                    player.��ɫ���ټ�� = nowTimestamp;
//                    player.highSpeedGamageCount = 0;
//                }
//                if (player.highSpeedGamageCount > 15) {
//                    player.dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽��ʹ�ü�������\r\n���룺YX211");
//                    return;
//                }
//            }
//        }
//    }

    public final void �����˺�(final int eachd) {
        if (lastDamage == eachd) {
            numSameDamage++;

            if (numSameDamage > 5) {
                numSameDamage = 0;
                //dropMessage(1, "<��Ǵ֮��>\r\n\r\n���棺ϵͳ��⵽�������˺�");
            }
        } else {
            lastDamage = eachd;
            numSameDamage = 0;
        }
    }

    /*public final void checkSameDamage(final int dmg) {
        if (dmg > 2000 && lastDamage == dmg) {
            numSameDamage++;

            if (numSameDamage > 5) {
                numSameDamage = 0;
                registerOffense(CheatingOffense.�˺���ͬ, numSameDamage + " times: " + dmg);

            }
        } else {
            lastDamage = dmg;
            numSameDamage = 0;
        }
    }*/
    public final void checkAttack(final int skillId, final int tickcount) {
        final short AtkDelay = GameConstants.getAttackDelay(skillId);
        /*if ((tickcount - lastAttackTickCount) < AtkDelay) {
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "����! "));
               
            registerOffense(CheatingOffense.���ٹ���);
        }*/

        final long STime_TC = System.currentTimeMillis() - tickcount; // hack = - more
        if (Server_ClientAtkTickDiff - STime_TC > 250) { // 250 is the ping, TODO
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "����! "));
            registerOffense(CheatingOffense.���ٹ���2);
        }
        // if speed hack, client tickcount values will be running at a faster pace
        // For lagging, it isn't an issue since TIME is running simotaniously, client
        // will be sending values of older time

//	System.out.println("Delay [" + skillId + "] = " + (tickcount - lastAttackTickCount) + ", " + (Server_ClientAtkTickDiff - STime_TC));
        Attack_tickResetCount++; // Without this, the difference will always be at 100
        if (Attack_tickResetCount >= (AtkDelay <= 200 ? 2 : 4)) {
            Attack_tickResetCount = 0;
            Server_ClientAtkTickDiff = STime_TC;
        }
        chr.get().updateTick(tickcount);
        lastAttackTickCount = tickcount;
    }

    public final void checkTakeDamage(final int damage) {//�����޵�MISS���
        numSequentialDamage++;
        lastDamageTakenTime = System.currentTimeMillis();

        // System.out.println("tb" + timeBetweenDamage);
        // System.out.println("ns" + numSequentialDamage);
        // System.out.println(timeBetweenDamage / 1500 + "(" + timeBetweenDamage / numSequentialDamage + ")");
        /* if (lastDamageTakenTime - takingDamageSince / 500 < numSequentialDamage) {
            registerOffense(CheatingOffense.������ײ����);//���
        }*/
        if (lastDamageTakenTime - takingDamageSince > 4500) {
            takingDamageSince = lastDamageTakenTime;
            numSequentialDamage = 0;
        }
        /*
         * (non-thieves) Min Miss Rate: 2% Max Miss Rate: 80% (thieves) Min Miss
         * Rate: 5% Max Miss Rate: 95%
         */
        if (damage == 0) {
            numZeroDamageTaken++;
            if (numZeroDamageTaken >= 35) { // Num count MSEA a/b players
                numZeroDamageTaken = 0;

                registerOffense(CheatingOffense.�ر��ʹ���);//���
            }

        } else if (damage != -1) {
            numZeroDamageTaken = 0;
        }
    }

    public final void checkMoveMonster(final Point pos, MapleCharacter chr) {

        //double dis = Math.abs(pos.distance(lastMonsterMove));
        if (pos.equals(this.lastMonsterMove)) {
            monsterMoveCount++;
            /*if (monsterMoveCount > 50) {
                //   registerOffense(CheatingOffense.MOVE_MONSTERS);
                monsterMoveCount = 0;
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[����Ա��Ϣ] �������[" + MapleCharacterUtil.makeMapleReadable(chr.getName()) + "] ��ͼID[" + chr.getMapId() + "] ����ʹ������! "));
                String note = "ʱ�䣺" + FileoutputUtil.CurrentReadable_Time() + " "
                        + "|| ������֣�" + chr.getName() + ""
                        + "|| ��ҵ�ͼ��" + chr.getMapId() + "\r\n";
                FileoutputUtil.packetLog("log\\���ּ��\\" + chr.getName() + ".log", note);
            }*///���
            /*
             * } else if ( dis > 1500 ) { monsterMoveCount++; if
             * (monsterMoveCount > 15) {
             * registerOffense(CheatingOffense.MOVE_MONSTERS); }
             */
        } else {
            lastMonsterMove = pos;
            monsterMoveCount = 1;
        }
    }

    public final void resetSummonAttack() {
        summonSummonTime = System.currentTimeMillis();
        numSequentialSummonAttack = 0;
    }

    public final boolean checkSummonAttack() {
        numSequentialSummonAttack++;
        //estimated
        // System.out.println(numMPRegens + "/" + allowedRegens);
        /*if ((System.currentTimeMillis() - summonSummonTime) / (2000 + 1) < numSequentialSummonAttack) {
            registerOffense(CheatingOffense.�ٻ��޿��ٹ���);
            return false;
        }*///���
        return true;
    }

    public final void checkDrop() {
        checkDrop(false);
    }

    public final void checkDrop(final boolean dc) {
        if ((System.currentTimeMillis() - lastDropTime) < 1000) {
            dropsPerSecond++;
            if (dropsPerSecond >= (dc ? 32 : 16) && chr.get() != null) {
//                if (dc) {
//                    chr.get().getClient().getSession().close();
//                } else {
                chr.get().getClient().setMonitored(true);
//                }
            }
        } else {
            dropsPerSecond = 0;
        }
        lastDropTime = System.currentTimeMillis();
    }

    public boolean canAvatarSmega2() {
        if (lastASmegaTime + 10000 > System.currentTimeMillis() && chr.get() != null && !chr.get().isGM()) {
            return false;
        }
        lastASmegaTime = System.currentTimeMillis();
        return true;
    }

    public synchronized boolean GMSpam(int limit, int type) {
        if (type < 0 || lastTime.length < type) {
            type = 1; // default xD
        }
        if (System.currentTimeMillis() < limit + lastTime[type]) {
            return true;
        }
        lastTime[type] = System.currentTimeMillis();
        return false;
    }

    public final void checkMsg() { //ALL types of msg. caution with number of  msgsPerSecond
        if ((System.currentTimeMillis() - lastMsgTime) < 1000) { //luckily maplestory has auto-check for too much msging
            msgsPerSecond++;
            /*
             * if (msgsPerSecond > 10 && chr.get() != null) {
             * chr.get().getClient().getSession().close(); }
             */
        } else {
            msgsPerSecond = 0;
        }
        lastMsgTime = System.currentTimeMillis();
    }

    public final int getAttacksWithoutHit() {
        return attacksWithoutHit;
    }

    public final void setAttacksWithoutHit(final boolean increase) {
        if (increase) {
            this.attacksWithoutHit++;
        } else {
            this.attacksWithoutHit = 0;
        }
    }

    public final void registerOffense(final CheatingOffense offense) {
        registerOffense(offense, null);
    }

    public final void registerOffense(final CheatingOffense offense, final String param) {
        final MapleCharacter chrhardref = chr.get();
        if (chrhardref == null || !offense.isEnabled() || chrhardref.isClone() || chrhardref.isGM()) {
            return;
        }
        CheatingOffenseEntry entry = null;
        rL.lock();
        try {
            entry = offenses.get(offense);
        } finally {
            rL.unlock();
        }
        if (entry != null && entry.isExpired()) {
            expireEntry(entry);
            entry = null;
        }
        if (entry == null) {
            entry = new CheatingOffenseEntry(offense, chrhardref.getId());
        }
        if (param != null) {
            entry.setParam(param);
        }
        entry.incrementCount();
        if (offense.shouldAutoban(entry.getCount())) {
            final byte type = offense.getBanType();
            if (type == 1) {
                AutobanManager.getInstance().autoban(chrhardref.getClient(), StringUtil.makeEnumHumanReadable(offense.name()));
            } else if (type == 2) {
                //������ײ���� �ر��ʹ��� ���ٹ��� ���ٹ���2 �����ƶ� �˺���ͬ
                String outputFileName = "����";
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM��Ϣ] " + chrhardref.getName() + " �Զ����� ���: " + offense.toString() + " ԭ��: " + (param == null ? "" : (" - " + param))));
                FileoutputUtil.logToFile_chr(chrhardref, "logs/Hack/" + outputFileName + ".txt", "\r\n " + FileoutputUtil.NowTime() + " ���" + offense.toString() + " ԭ�� " + (param == null ? "" : (" - " + param)));
                chrhardref.getClient().getSession().close();
                //chrhardref.getClient().getSession().close();
            } else if (type == 3) {
            }
            gm_message = 50;
            return;
        }
        wL.lock();
        try {
            offenses.put(offense, entry);
        } finally {
            wL.unlock();
        }
        switch (offense) {

            case ħ���˺�����:
            case ħ���˺�����2:
            case ��������2:
            case ����������:
            case ���ٹ���:
            case ���ٹ���2:
            case ������Χ����:
            case �ٻ��޹�����Χ����:
            case �˺���ͬ:

            case ����:
            case �����ƶ�:

            case �ر��ʹ���:
                String show = offense.name();
                gm_message--;
                if (gm_message % 5 == 0) {
                    String msg = "[����Ա��Ϣ] " + chrhardref.getName() + " ���� " + show
                            + "��ͼID [" + chrhardref.getMapId() + "]" + ""
                            + (param == null ? "" : (" - " + param));
                    World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
                    FileoutputUtil.logToFile_chr(chrhardref, FileoutputUtil.hack_log, show);
                }
                if (gm_message == 0) {

                    // System.out.println(MapleCharacterUtil.makeMapleReadable(chrhardref.getName()) + "����ʹ�����");
                    // World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[����TӍϢ] �������[" + MapleCharacterUtil.makeMapleReadable(chrhardref.getName()) + "] ��ͼID[" + chrhardref.getMapId() + "] suspected of hacking! " + StringUtil.makeEnumHumanReadable(offense.name()) + (param == null ? "" : (" - " + param))));
                    /*
                     * String note = "ʱ�䣺" +
                     * FileoutputUtil.CurrentReadable_Time() + " " + "|| ������֣�"
                     * + chrhardref.getName() + "" + "|| ��ҵ�ͼ��" +
                     * chrhardref.getMapId() + "" + "|| ������ͣ�" + offense.name()
                     * + "\r\n"; FileoutputUtil.packetLog("log\\��Ҽ��\\" +
                     * chrhardref.getName() + ".log", note);
                     */ //                    AutobanManager.getInstance().autoban(chrhardref.getClient(), StringUtil.makeEnumHumanReadable(offense.name()));
                    gm_message = 50;
                }
                break;
        }
        CheatingOffensePersister.getInstance().persistEntry(entry);
    }

    public void updateTick(int newTick) {
        if (newTick == lastTickCount) { //definitely packet spamming
/*      
             * if (tickSame >= 5) { chr.get().getClient().getSession().close();
             * //i could also add a check for less than, but i'm not too worried
             * at the moment :) } else {
             */
            tickSame++;
//	    }
        } else {
            tickSame = 0;
        }
        lastTickCount = newTick;
    }

    public final void expireEntry(final CheatingOffenseEntry coe) {
        wL.lock();
        try {
            offenses.remove(coe.getOffense());
        } finally {
            wL.unlock();
        }
    }

    public final int getPoints() {
        int ret = 0;
        CheatingOffenseEntry[] offenses_copy;
        rL.lock();
        try {
            offenses_copy = offenses.values().toArray(new CheatingOffenseEntry[offenses.size()]);
        } finally {
            rL.unlock();
        }
        for (final CheatingOffenseEntry entry : offenses_copy) {
            if (entry.isExpired()) {
                expireEntry(entry);
            } else {
                ret += entry.getPoints();
            }
        }
        return ret;
    }

    public final Map<CheatingOffense, CheatingOffenseEntry> getOffenses() {
        return Collections.unmodifiableMap(offenses);
    }

    public final String getSummary() {
        final StringBuilder ret = new StringBuilder();
        final List<CheatingOffenseEntry> offenseList = new ArrayList<CheatingOffenseEntry>();
        rL.lock();
        try {
            for (final CheatingOffenseEntry entry : offenses.values()) {
                if (!entry.isExpired()) {
                    offenseList.add(entry);
                }
            }
        } finally {
            rL.unlock();
        }
        Collections.sort(offenseList, new Comparator<CheatingOffenseEntry>() {

            @Override
            public final int compare(final CheatingOffenseEntry o1, final CheatingOffenseEntry o2) {
                final int thisVal = o1.getPoints();
                final int anotherVal = o2.getPoints();
                return (thisVal < anotherVal ? 1 : (thisVal == anotherVal ? 0 : -1));
            }
        });
        final int to = Math.min(offenseList.size(), 4);
        for (int x = 0; x < to; x++) {
            ret.append(StringUtil.makeEnumHumanReadable(offenseList.get(x).getOffense().name()));
            ret.append(": ");
            ret.append(offenseList.get(x).getCount());
            if (x != to - 1) {
                ret.append(" ");
            }
        }
        return ret.toString();
    }

    public final void dispose() {
        if (invalidationTask != null) {
            invalidationTask.cancel(false);
        }
        invalidationTask = null;
    }

    private final class InvalidationTask implements Runnable {

        @Override
        public final void run() {
            CheatingOffenseEntry[] offenses_copy;
            rL.lock();
            try {
                offenses_copy = offenses.values().toArray(new CheatingOffenseEntry[offenses.size()]);
            } finally {
                rL.unlock();
            }
            for (CheatingOffenseEntry offense : offenses_copy) {
                if (offense.isExpired()) {
                    expireEntry(offense);
                }
            }
            if (chr.get() == null) {
                dispose();
            }
        }
    }
}
