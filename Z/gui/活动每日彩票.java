package gui;

import handling.world.MapleParty;
import handling.world.World;
import java.util.Calendar;
import java.util.concurrent.ScheduledFuture;
import server.Timer;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;

/**
 *
 * @author Administrator
 */
public class �ÿ�ղ�Ʊ {

    public static ScheduledFuture<?> ��Ʊ�߳� = null;
    public static Boolean �� = false;
    public static Boolean �� = false;
    public static Boolean һ = false;
    public static Boolean �� = false;

    public static void main(String args[]) {
        ��Ʊ�߳�();
    }

    /**
     * <23��>
     */
    public static void ��Ʊ�߳�() {
        if (��Ʊ�߳� == null) {
            System.err.println("[�����]" + CurrentReadable_Time() + " : [ÿ�ղ�Ʊ] : ÿ�ղ�Ʊ�Ѿ���ʼҡ����");
            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[ÿ�ղ�Ʊ] : ÿ�ղ�Ʊ�Ѿ���ʼҡ��������Ĵ󽱽��Ứ��˭���ء�"));
            ��Ʊ�߳� = Timer.BuffTimer.getInstance().register(new Runnable() {
                @Override
                public void run() {
                    int ʱ = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                    int �� = Calendar.getInstance().get(Calendar.MINUTE);
                    if (ʱ == 23 && �� == 10 && �� == false) {
                        ���Ƚ�();
                    } else if (ʱ == 23 && �� == 11 && �� == false) {
                        ���Ƚ�();
                    } else if (ʱ == 23 && �� == 12 && һ == false) {
                        һ�Ƚ�();
                    } else if (ʱ == 23 && �� == 13 && �� == false) {
                        �صȽ�();
                    }
                    System.err.println("1");
                }
            }, 1000 * 1);
        }
    }

    public static void ���Ƚ�() {
        double ��� = Math.ceil(Math.random() * 200);
        ��� += 100;
        MapleParty.���Ƚ� = (int) ���;
        �� = true;
    }

    public static void ���Ƚ�() {
        double ��� = Math.ceil(Math.random() * 200);
        ��� += 100;
        MapleParty.���Ƚ� = (int) ���;
        �� = true;
    }

    public static void һ�Ƚ�() {
        double ��� = Math.ceil(Math.random() * 200);
        ��� += 100;
        MapleParty.һ�Ƚ� = (int) ���;
        һ = true;
    }

    public static void �صȽ�() {
        double ��� = Math.ceil(Math.random() * 200);
        ��� += 100;
        MapleParty.�صȽ� = (int) ���;
        �� = true;
        �رղ�Ʊ�߳�();
    }

    public static void �رղ�Ʊ�߳�() {
        if (��Ʊ�߳� != null) {
            ��Ʊ�߳�.cancel(true);
            ��Ʊ�߳� = null;

        }
    }

}
