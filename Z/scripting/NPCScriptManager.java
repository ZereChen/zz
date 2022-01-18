/*
NPC����
 */
package scripting;

import static abc.Game2.�������;
import static abc.Game.NPCǰ׺;
import static abc.Game.NPC�����ı���ʾ;
import static abc.Game2.zevms;
import static abc.Game2.ð�հٿ�;
import static abc.Game2.Ů����;
import static abc.Game2.��������;
import static abc.Game2.����;
import static abc.Game2.ÿ������;
import static abc.Game2.����ɭ��;
import static abc.Game2.����;
import static abc.Game2.������;
import static abc.Game2.��������;
import static abc.Game2.�����;
import static abc.Game2.���NPC;
import static abc.Game2.����ŷ������Ҷ;
import static abc.Game2.Ӣ���;
import static abc.Game2.������;
import abc.PNPC;
import java.util.Map;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import client.MapleClient;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import server.quest.MapleQuest;
import tools.FileoutputUtil;
import tools.MaplePacketCreator;

public class NPCScriptManager extends AbstractScriptManager {

    private final Map<MapleClient, NPCConversationManager> cms = new WeakHashMap<MapleClient, NPCConversationManager>();
    private static final NPCScriptManager instance = new NPCScriptManager();

    public static final NPCScriptManager getInstance() {
        return instance;
    }

    public void start(MapleClient c, int npc) {
        start(c, npc, 0);
    }

    public final void start(final MapleClient c, final int npc, int wh) {
        if (c.getPlayer().getMapId() == 180000001) {
            return;
        }
        final String whStr = wh == 0 ? "" : "_" + wh;
        final String npcIdName = npc + whStr;
        if (npc > 100 && npc < 2000) {
            return;
        }
        if (npc == 9900004 && wh == 9900005) {
            if (gui.Start.ConfigValuesMap.get("�����п���") > 0) {
                c.getPlayer().dropMessage(1, "����Ա�Ѿ��Ӻ�̨�ر��������й���");
                return;
            }
            if (c.getPlayer().getMapId() == 100000203) {
                c.getPlayer().dropMessage(1, "�˵�ͼ�޷���������");
                return;
            }
        }
        // �Ż����ֹ��ܣ���Ҫ���ٷ���
        //if (npc != 2000 && npc != 9900007 && npc != 9900004 && npc != 1012114 && npc != 9020002 && npc != 2040035 && npc != 9110014) {
        if (!c.canCallNpc(npcIdName)) {
            c.getPlayer().dropMessage(5, "���������ˣ������㡣");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        } else {
            c.setLastCallNpcTime(System.currentTimeMillis(), npcIdName);
        }
        //}

        final Lock lock = c.getNPCLock();
        lock.lock();
        try {
            if (gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0 && c.getPlayer().isGM()) {
                //����ʾ��NPC����
                PNPC itemjc2 = new PNPC();
                String items2 = itemjc2.getPM();
                if (npc != 2007) {
                    if (�������(npc) && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/�������/" + npc + "]");
                    } else if (�������(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/�������/" + npc + "," + wh + "]");
                    } else if (Ӣ���(npc) && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-Ӣ���/" + npc + "]");
                    } else if (Ӣ���(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-Ӣ���/" + npc + "," + wh + "]");
                    } else if (��������(npc) && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-��������/" + npc + "]");
                    } else if (��������(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-��������/" + npc + "," + wh + "]");
                    } else if (ÿ������(npc) && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/ÿ������/" + npc + "]");
                    } else if (ÿ������(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/ÿ������/" + npc + "," + wh + "]");
                    } else if (�����(npc) && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-�����/" + npc + "]");
                    } else if (�����(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-�����/" + npc + "," + wh + "]");
                    } else if (Ů����(npc) && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-Ů����/" + npc + "]");
                    } else if (Ů����(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-Ů����/" + npc + "," + wh + "]");
                    } else if (����(npc) && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-����/" + npc + "]");
                    } else if (����(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-����/" + npc + "," + wh + "]");
                    } else if (������(npc) && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-������/" + npc + "]");
                    } else if (������(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-������/" + npc + "," + wh + "]");
                    } else if (����ɭ��(npc) && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-����ɭ��/" + npc + "]");
                    } else if (����ɭ��(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-����ɭ��/" + npc + "," + wh + "]");
                    } else if (����ŷ������Ҷ(npc) && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-����ŷ������Ҷ/" + npc + "]");
                    } else if (����ŷ������Ҷ(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/����-����ŷ������Ҷ/" + npc + "," + wh + "]");
                    } else if (ð�հٿ�(npc) && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/ð�հٿ�/" + npc + "]");
                    } else if (ð�հٿ�(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/ð�հٿ�/" + npc + "," + wh + "]");
                    } else if (���NPC(npc) && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/������/" + npc + "]");
                    } else if (���NPC(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/������/" + npc + "," + wh + "]");

                    } else if (zevms(npc) && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/zevms[����ִ���ļ�/" + npc + "]");
                    } else if (zevms(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/zevms[����ִ���ļ�/" + npc + "," + wh + "]");
                    } else if (������(npc) && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/������]/" + npc + "]");
                    } else if (������(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/������]/" + npc + "," + wh + "]");
                    } else if (��������(npc) && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/��������]/" + npc + "]");
                    } else if (��������(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/��������]/" + npc + "," + wh + "]");
                    } else if (����(npc) && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/�����]/" + npc + "," + wh + "]");
                    } else if (items2.contains("," + npc + ",") && wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/pnpc]/" + npc + "]");
                    } else if (items2.contains("," + npc + ",") && wh > 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/pnpc/" + npc + " _ " + wh + "]");
                    } else if (wh == 0) {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/npc/" + npc + "]");
                    } else {
                        c.getPlayer().dropMessage(5, "dialogue:[scripts/npc/" + npc + " _ " + wh + "]");
                    }
                }
            }

            if (!cms.containsKey(c)) {
                Invocable iv;
                PNPC itemjc2 = new PNPC();
                String items2 = itemjc2.getPM();
                if (�������(npc) && wh == 0) {
                    iv = getInvocable("�������/" + npc + ".js", c, true);
                } else if (�������(npc) && wh > 0) {
                    iv = getInvocable("�������/" + npc + "_" + wh + ".js", c, true);
                } else if (Ӣ���(npc) && wh == 0) {
                    iv = getInvocable("����-Ӣ���/" + npc + ".js", c, true);
                } else if (Ӣ���(npc) && wh > 0) {
                    iv = getInvocable("����-Ӣ���/" + npc + "_" + wh + ".js", c, true);
                } else if (��������(npc) && wh == 0) {
                    iv = getInvocable("����-��������/" + npc + ".js", c, true);
                } else if (��������(npc) && wh > 0) {
                    iv = getInvocable("����-��������/" + npc + "_" + wh + ".js", c, true);
                } else if (�����(npc) && wh == 0) {
                    iv = getInvocable("����-�����/" + npc + ".js", c, true);
                } else if (�����(npc) && wh > 0) {
                    iv = getInvocable("����-�����/" + npc + "_" + wh + ".js", c, true);
                } else if (Ů����(npc) && wh == 0) {
                    iv = getInvocable("����-Ů����/" + npc + ".js", c, true);
                } else if (Ů����(npc) && wh > 0) {
                    iv = getInvocable("����-Ů����/" + npc + "_" + wh + ".js", c, true);
                } else if (����(npc) && wh == 0) {
                    iv = getInvocable("����-����/" + npc + ".js", c, true);
                } else if (����(npc) && wh > 0) {
                    iv = getInvocable("����-����/" + npc + "_" + wh + ".js", c, true);
                } else if (������(npc) && wh == 0) {
                    iv = getInvocable("����-������/" + npc + ".js", c, true);
                } else if (������(npc) && wh > 0) {
                    iv = getInvocable("����-������/" + npc + "_" + wh + ".js", c, true);
                } else if (����ɭ��(npc) && wh == 0) {
                    iv = getInvocable("����-����ɭ��/" + npc + ".js", c, true);
                } else if (����ɭ��(npc) && wh > 0) {
                    iv = getInvocable("����-����ɭ��/" + npc + "_" + wh + ".js", c, true);
                } else if (����ŷ������Ҷ(npc) && wh == 0) {
                    iv = getInvocable("����-����ŷ������Ҷ/" + npc + ".js", c, true);
                } else if (����ŷ������Ҷ(npc) && wh > 0) {
                    iv = getInvocable("����-����ŷ������Ҷ/" + npc + "_" + wh + ".js", c, true);
                } else if (ÿ������(npc) && wh == 0) {
                    iv = getInvocable("ÿ������/" + npc + ".js", c, true);
                } else if (ÿ������(npc) && wh > 0) {
                    iv = getInvocable("ÿ������/" + npc + "_" + wh + ".js", c, true);
                } else if (ð�հٿ�(npc) && wh == 0) {
                    iv = getInvocable("ð�հٿ�/" + npc + ".js", c, true);
                } else if (ð�հٿ�(npc) && wh > 0) {
                    iv = getInvocable("ð�հٿ�/" + npc + "_" + wh + ".js", c, true);
                } else if (���NPC(npc) && wh == 0) {
                    iv = getInvocable("������/" + npc + ".js", c, true);
                } else if (���NPC(npc) && wh > 0) {
                    iv = getInvocable("������/" + npc + "_" + wh + ".js", c, true);
                } else if (zevms(npc) && wh == 0) {
                    iv = getInvocable("zevms/" + npc + ".js", c, true);
                } else if (zevms(npc) && wh > 0) {
                    iv = getInvocable("zevms/" + npc + "_" + wh + ".js", c, true);
                } else if (������(npc) && wh == 0) {
                    iv = getInvocable("������/" + npc + ".js", c, true);
                } else if (������(npc) && wh > 0) {
                    iv = getInvocable("������/" + npc + "_" + wh + ".js", c, true);
                } else if (��������(npc) && wh == 0) {
                    iv = getInvocable("��������/" + npc + ".js", c, true);
                } else if (��������(npc) && wh > 0) {
                    iv = getInvocable("��������/" + npc + "_" + wh + ".js", c, true);
                } else if (����(npc) && wh > 0) {
                    iv = getInvocable("�����/" + npc + "_" + wh + ".js", c, true);
                } else if (items2.contains("," + npc + ",") && wh == 0) {
                    iv = getInvocable("pnpc/" + npc + ".js", c, true);
                } else if (items2.contains("," + npc + ",") && wh > 0) {
                    iv = getInvocable("pnpc/" + npc + "_" + wh + ".js", c, true);
                } else if (wh == 0) {
                    iv = getInvocable("npc/" + npc + ".js", c, true);
                } else {
                    iv = getInvocable("npc/" + npc + "_" + wh + ".js", c, true);
                }

                final ScriptEngine scriptengine = (ScriptEngine) iv;
                final NPCConversationManager cm;
                if (wh == 0) {
                    cm = new NPCConversationManager(c, npc, -1, (byte) -1, iv, 0);
                } else {
                    cm = new NPCConversationManager(c, npc, -1, (byte) -1, iv, wh);
                }

                cms.put(c, cm);
                if ((iv == null) || (getInstance() == null)) {

                    if (wh == 0) {
                        cm.sendOk("" + NPC�����ı���ʾ + "");
                        c.getPlayer().dropMessage(5, "[�ű�����]: " + npc + "");
                    } else {
                        cm.sendOk("" + NPC�����ı���ʾ + "");
                        c.getPlayer().dropMessage(5, "[�ű�����]: " + npc + "_" + wh + "");
                    }
                    cm.dispose();
                    return;
                }
                scriptengine.put("" + NPCǰ׺ + "", cm);
                //scriptengine.put("" + NPCǰ׺2 + "", cm);
                scriptengine.put("npcid", npc);
                c.getPlayer().setConversation(1);

                try {
                    iv.invokeFunction("start"); // Temporary until I've removed all of start
                } catch (NoSuchMethodException nsme) {
                    iv.invokeFunction("action", (byte) 1, (byte) 0, 0);
                }
            } else {
                c.sendPacket(MaplePacketCreator.enableActions());//�⿨
                NPCScriptManager.getInstance().dispose(c);
            }
        } catch (final Exception e) {
            System.err.println("[������ʾ] NPC�� " + npc + "_" + wh + "." + e);
            if (c.getPlayer().isGM()) {
                c.getPlayer().dropMessage(6, "[������ʾ] NPC�� " + npc + "_" + wh + " �ű����� " + e + "");
            }
            FileoutputUtil.log(FileoutputUtil.ScriptEx_Log, "Error executing NPC script, NPC ID : " + npc + "_" + wh + "." + e);
            dispose(c);
        } finally {
            lock.unlock();
        }
    }

    public void action(final MapleClient c, final byte mode, final byte type, final int selection) {
        action(c, (byte) mode, (byte) type, selection, 0);
    }

    public final void action(final MapleClient c, final byte mode, final byte type, final int selection, int wh) {
        if (mode != -1) {
            final NPCConversationManager cm = cms.get(c);
            if (cm == null || cm.getLastMsg() > -1) {
                return;
            }
            final Lock lock = c.getNPCLock();
            lock.lock();
            try {
                if (cm.pendingDisposal) {
                    dispose(c);
                } else if (wh == 0) {
                    cm.getIv().invokeFunction("action", mode, type, selection);
                } else {
                    cm.getIv().invokeFunction("action", mode, type, selection, wh);
                }
            } catch (final Exception e) {
                if (c.getPlayer().isGM()) {
                    c.getPlayer().dropMessage(6, "[������ʾ] NPC��" + cm.getNpc() + "_" + wh + "�ű�����,�뽫������Ϣ��ͼ��ZEV \n" + e + "");
                }
                System.err.println("[������ʾ] NPC�� " + cm.getNpc() + "_" + wh + ":" + e);
                dispose(c);
                FileoutputUtil.log(FileoutputUtil.ScriptEx_Log, "Error executing NPC script, NPC ID : " + cm.getNpc() + "_" + wh + "." + e);
            } finally {
                lock.unlock();
            }
        }
    }

    public final void startQuest(final MapleClient c, final int npc, final int quest) {
        if (!MapleQuest.getInstance(quest).canStart(c.getPlayer(), null)) {
            return;
        }
        final Lock lock = c.getNPCLock();
        lock.lock();
        try {
            if (!cms.containsKey(c)) {
                final Invocable iv = getInvocable("quest/" + quest + ".js", c, true);
                if (iv == null) {
                    dispose(c);
                    return;
                }
                final ScriptEngine scriptengine = (ScriptEngine) iv;
                final NPCConversationManager cm = new NPCConversationManager(c, npc, quest, (byte) 0, iv, 0);
                cms.put(c, cm);
                scriptengine.put("qm", cm);
                c.getPlayer().setConversation(1);
                if (gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0 && c.getPlayer().isGM()) {
                    c.getPlayer().dropMessage(5, "�Ի�����:" + quest + "");
                }
                iv.invokeFunction("start", (byte) 1, (byte) 0, 0);
            } else {
                dispose(c);
            }
        } catch (final Exception e) {
            System.err.println("" + quest + " : " + npc + ":" + e);
            //FileoutputUtil.log(FileoutputUtil.ScriptEx_Log, "Error executing Quest script. (" + quest + ")..NPCID: " + npc + ":" + e);
            dispose(c);
        } finally {
            lock.unlock();
        }
    }

    public final void startQuest(final MapleClient c, final byte mode, final byte type, final int selection) {
        final Lock lock = c.getNPCLock();
        final NPCConversationManager cm = cms.get(c);
        if (cm == null || cm.getLastMsg() > -1) {
            return;
        }
        lock.lock();
        try {
            if (cm.pendingDisposal) {
                dispose(c);
            } else {
                cm.getIv().invokeFunction("start", mode, type, selection);
            }
        } catch (Exception e) {
            if (gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0 && c.getPlayer().isGM()) {
                c.getPlayer().dropMessage("����ű�����:" + cm.getQuest() + "����NPC: " + cm.getNpc() + ":" + e);
            }
            System.err.println("Error executing Quest script. (" + cm.getQuest() + ")...NPC: " + cm.getNpc() + ":" + e);
            FileoutputUtil.log(FileoutputUtil.ScriptEx_Log, "Error executing Quest script. (" + cm.getQuest() + ")..NPCID: " + cm.getNpc() + ":" + e);
            dispose(c);
        } finally {
            lock.unlock();
        }
    }

    public final void endQuest(final MapleClient c, final int npc, final int quest, final boolean customEnd) {
        if (!customEnd && !MapleQuest.getInstance(quest).canComplete(c.getPlayer(), null)) {
            return;
        }
        final Lock lock = c.getNPCLock();
        lock.lock();
        try {
            if (!cms.containsKey(c)) {
                final Invocable iv = getInvocable("quest/" + quest + ".js", c, true);
                if (iv == null) {
                    dispose(c);
                    return;
                }
                final ScriptEngine scriptengine = (ScriptEngine) iv;
                final NPCConversationManager cm = new NPCConversationManager(c, npc, quest, (byte) 1, iv, 0);
                cms.put(c, cm);
                scriptengine.put("qm", cm);
                c.getPlayer().setConversation(1);
                iv.invokeFunction("end", (byte) 1, (byte) 0, 0); // start it off as something
            } else {
            }
        } catch (Exception e) {
            if (gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0 && c.getPlayer().isGM()) {
                c.getPlayer().dropMessage("����ű�����:" + quest + " ����NPC: " + quest + ":" + e);
            }
            System.err.println("Error executing Quest script. (" + quest + ")..NPCID: " + npc + ":" + e);
            FileoutputUtil.log(FileoutputUtil.ScriptEx_Log, "Error executing Quest script. (" + quest + ")..NPCID: " + npc + ":" + e);
            dispose(c);
        } finally {
            lock.unlock();
        }
    }

    public final void endQuest(final MapleClient c, final byte mode, final byte type, final int selection) {
        final Lock lock = c.getNPCLock();
        final NPCConversationManager cm = cms.get(c);
        if (cm == null || cm.getLastMsg() > -1) {
            return;
        }
        lock.lock();
        try {
            if (cm.pendingDisposal) {
                dispose(c);
            } else {
                cm.getIv().invokeFunction("end", mode, type, selection);
            }
        } catch (Exception e) {
            if (gui.Start.ConfigValuesMap.get("�ű����뿪��") <= 0 && c.getPlayer().isGM()) {
                c.getPlayer().dropMessage("����ű�����:" + cm.getQuest() + "����NPC: " + cm.getNpc() + ":" + e);
            }
            System.err.println("Error executing Quest script. (" + cm.getQuest() + ")...NPC: " + cm.getNpc() + ":" + e);
            FileoutputUtil.log(FileoutputUtil.ScriptEx_Log, "Error executing Quest script. (" + cm.getQuest() + ")..NPCID: " + cm.getNpc() + ":" + e);
            dispose(c);
        } finally {
            lock.unlock();
        }
    }

    public final void dispose(final MapleClient c) {
        final NPCConversationManager npccm = cms.get(c);
        PNPC itemjc2 = new PNPC();
        String items2 = itemjc2.getPM();
        if (npccm != null) {
            cms.remove(c);
            if (npccm.getType() == -1) {
                if (�������(npccm.getNpc()) && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/�������/" + npccm.getNpc() + ".js");
                } else if (�������(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/�������/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (Ӣ���(npccm.getNpc()) && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/����-Ӣ���/" + npccm.getNpc() + ".js");
                } else if (Ӣ���(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/����-Ӣ���/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (��������(npccm.getNpc()) && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/����-��������/" + npccm.getNpc() + ".js");
                } else if (��������(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/����-��������/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (�����(npccm.getNpc()) && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/����-�����/" + npccm.getNpc() + ".js");
                } else if (�����(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/����-�����/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (Ů����(npccm.getNpc()) && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/����-Ů����/" + npccm.getNpc() + ".js");
                } else if (Ů����(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/����-Ů����/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (����(npccm.getNpc()) && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/����-����/" + npccm.getNpc() + ".js");
                } else if (����(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/����-����/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (������(npccm.getNpc()) && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/����-������/" + npccm.getNpc() + ".js");
                } else if (������(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/����-������/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (����ɭ��(npccm.getNpc()) && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/����-����ɭ��/" + npccm.getNpc() + ".js");
                } else if (����ɭ��(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/����-����ɭ��/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (����ŷ������Ҷ(npccm.getNpc()) && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/����-����ŷ������Ҷ/" + npccm.getNpc() + ".js");
                } else if (����ŷ������Ҷ(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/����-����ŷ������Ҷ/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (ÿ������(npccm.getNpc()) && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/ÿ������/" + npccm.getNpc() + ".js");
                } else if (ÿ������(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/ÿ������/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (ð�հٿ�(npccm.getNpc()) && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/ð�հٿ�/" + npccm.getNpc() + ".js");
                } else if (ð�հٿ�(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/ð�հٿ�/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (���NPC(npccm.getNpc()) && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/������/" + npccm.getNpc() + ".js");
                } else if (���NPC(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/������/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (zevms(npccm.getNpc()) && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/zevms/" + npccm.getNpc() + ".js");
                } else if (zevms(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/zevms/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (������(npccm.getNpc()) && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/������/" + npccm.getNpc() + ".js");
                } else if (������(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/������/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (��������(npccm.getNpc()) && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/��������/" + npccm.getNpc() + ".js");
                } else if (��������(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/��������/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (����(npccm.getNpc()) && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/�����/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (items2.contains("," + npccm.getNpc() + ",") && npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/pnpc/" + npccm.getNpc() + ".js");
                } else if (items2.contains("," + npccm.getNpc() + ",") && npccm.getwh() > 0) {
                    c.removeScriptEngine("scripts/pnpc/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                } else if (npccm.getwh() == 0) {
                    c.removeScriptEngine("scripts/npc/" + npccm.getNpc() + ".js");
                } else {
                    c.removeScriptEngine("scripts/npc/" + npccm.getNpc() + "_" + npccm.getwh() + ".js");
                }
                //c.removeScriptEngine("scripts/npc/notcoded.js");
            } else {
                c.removeScriptEngine("scripts/quest/" + npccm.getQuest() + ".js");
            }
        }
        if (c.getPlayer() != null && c.getPlayer().getConversation() == 1) {
            c.getPlayer().setConversation(0);
        }
    }

    public final NPCConversationManager getCM(final MapleClient c) {
        return cms.get(c);
    }

}
