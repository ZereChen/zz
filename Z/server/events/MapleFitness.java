/*
��ߵ�
 */
package server.events;

import static abc.Game.��ߵػʱ��;
import java.util.concurrent.ScheduledFuture;
import client.MapleCharacter;
import server.Timer.EventTimer;
import server.maps.MapleMap;
import server.maps.SavedLocationType;
import tools.MaplePacketCreator;

public class MapleFitness extends MapleEvent {

    private static final long serialVersionUID = 845748950824L;
    private long time = ��ߵػʱ��;
    private long timeStarted = 0;
    private ScheduledFuture<?> fitnessSchedule, msgSchedule;

    public MapleFitness(final int channel, final int[] mapid) {
        super(channel, mapid);
    }

    @Override
    public void finished(final MapleCharacter chr) {
        hdxgdbs(chr);
        //chr.finishAchievement(20);
    }

    @Override
    public void onMapLoad(MapleCharacter chr) {
        if (isTimerStarted()) {
            chr.getClient().sendPacket(MaplePacketCreator.getClock((int) (getTimeLeft() / 1000)));
        }
    }

    @Override
    public void startEvent() {
        unreset();
        super.reset(); 
        broadcast(MaplePacketCreator.getClock((int) (time / 1000)));
        this.timeStarted = System.currentTimeMillis();
        checkAndMessage();
        fitnessSchedule = EventTimer.getInstance().schedule(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mapid.length; i++) {
                    for (MapleCharacter chr : getMap(i).getCharactersThreadsafe()) {
                        warpBack(chr);
                    }
                }
                unreset();
            }
        }, this.time);
        broadcast(MaplePacketCreator.serverNotice(0, "[��ߵ�] ��Ѿ���ʼ�����Ѿ��򿪡�"));
    }

    public boolean isTimerStarted() {
        return timeStarted > 0;
    }

    public long getTime() {
        return time;
    }

    public void resetSchedule() {
        this.timeStarted = 0;
        if (fitnessSchedule != null) {
            fitnessSchedule.cancel(false);
        }
        fitnessSchedule = null;
        if (msgSchedule != null) {
            msgSchedule.cancel(false);
        }
        msgSchedule = null;
    }

    @Override
    public void reset() {
        super.reset();
        resetSchedule();
        getMap(0).getPortal("join00").setPortalState(false);
    }

    @Override
    public void unreset() {
        super.unreset();
        resetSchedule();
        getMap(0).getPortal("join00").setPortalState(true);
    }

    public long getTimeLeft() {
        return time - (System.currentTimeMillis() - timeStarted);
    }

    public void checkAndMessage() {
        msgSchedule = EventTimer.getInstance().register(new Runnable() {

            @Override
            public void run() {
                final long timeLeft = getTimeLeft();
                if (timeLeft > 9000 && timeLeft < 11000) {
                    broadcast(MaplePacketCreator.serverNotice(0, "���������޷�սʤ�ı���������ϣ������һ��սʤ�����´��ټ�~"));
                } else if (timeLeft > 11000 && timeLeft < 101000) {
                    broadcast(MaplePacketCreator.serverNotice(0, "�ðɣ���ʣ�µ�ʱ��û�ж����ˡ���ץ��ʱ��!"));
                } else if (timeLeft > 101000 && timeLeft < 241000) {
                    broadcast(MaplePacketCreator.serverNotice(0, "��4�׶Σ������һ�� [��ߵ�]. �벻Ҫ�����һ�̷�����ƴ��ȫ��. ���Ľ�������߲������Ŷ!"));
                } else if (timeLeft > 241000 && timeLeft < 301000) {
                    broadcast(MaplePacketCreator.serverNotice(0, "�����׶Σ��кܶ����壬����ܻῴ�����ǣ����㲻�ܲ�����.������ķ�ʽ������ȥ��."));
                } else if (timeLeft > 301000 && timeLeft < 361000) {
                    broadcast(MaplePacketCreator.serverNotice(0, "������������ƶ���С�ĵ�������ȥ��"));
                } else if (timeLeft > 361000 && timeLeft < 501000) {
                    broadcast(MaplePacketCreator.serverNotice(0, "���ס��������ڻ�ڼ������ˣ�������Ϸ����̭. ������벹��HP��ʹ��ҩˮ���ƶ�֮ǰ�Ȼָ�HP��"));
                } else if (timeLeft > 501000 && timeLeft < 601000) {
                    broadcast(MaplePacketCreator.serverNotice(0, "����Ҫ��������Ҫ֪������Ҫ�������ӵ��㽶���У����ڹ涨��ʱ�����һ�У�"));
                } else if (timeLeft > 601000 && timeLeft < 661000) {
                    broadcast(MaplePacketCreator.serverNotice(0, "�ڶ��׶��ϰ��Ǻ������㽶. ��ȷ��������ȷ��·���ƶ���������ǵĹ�����"));
                } else if (timeLeft > 661000 && timeLeft < 701000) {
                    broadcast(MaplePacketCreator.serverNotice(0, "���ס��������ڻ�ڼ������ˣ�������Ϸ����̭. ������벹��HP��ʹ��ҩˮ���ƶ�֮ǰ�Ȼָ�HP��"));
                } else if (timeLeft > 701000 && timeLeft < 781000) {
                    broadcast(MaplePacketCreator.serverNotice(0, "ÿ���˶��òμ� [��ߵ�] �μ������Ŀ��������ɵ�˳�򣬶����ý���������ֻ�����֣��������������4���׶Ρ�"));
                } else if (timeLeft > 781000 && timeLeft < 841000) {
                    broadcast(MaplePacketCreator.serverNotice(0, "��Ҫη����ѣ�������ȥ��������Ϸ������������."));
                } else if (timeLeft > 841000) {
                    broadcast(MaplePacketCreator.serverNotice(0, "[��ߵ�]��4���׶Σ��������������Ϸ���������������ӱ�������̭��������ע����һ�㡣"));
                }
            }
        }, 90000);
    }
}
