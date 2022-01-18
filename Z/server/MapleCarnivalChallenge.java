package server;

import java.lang.ref.WeakReference;
import client.MapleCharacter;
import handling.world.MapleParty;
import handling.world.MaplePartyCharacter;

public class MapleCarnivalChallenge {

    WeakReference<MapleCharacter> challenger;
    String challengeinfo = "";

    public MapleCarnivalChallenge(MapleCharacter challenger) {
        this.challenger = new WeakReference<MapleCharacter>(challenger);
        challengeinfo += "#b";
        for (MaplePartyCharacter pc : challenger.getParty().getMembers()) {
            MapleCharacter c = challenger.getMap().getCharacterById(pc.getId());
            if (c != null) {
                challengeinfo += ("�������: " + c.getName() + " / �ȼ�: " + c.getLevel() + " / ְҵ:  " + getJobNameById(c.getJob()) + "\r\n");
            }
        }
        challengeinfo += "#k";
    }

    public MapleCharacter getChallenger() {
        return challenger.get();
    }

    public String getChallengeInfo() {
        return challengeinfo;
    }

    public static final String getJobNameById(int job) {
        switch (job) {
            case 0:
                return "����";
            case 1000:
                return "������";
            case 2000:
                return "սͯ";
            case 100:
                return "սʿ";
            case 110:
                return "����";
            case 111:
                return "��ʿ";
            case 112:
                return "Ӣ��";
            case 120:
                return "׼��ʿ";
            case 121:
                return "��ʿ";
            case 122:
                return "ʥ��ʿ";
            case 130:
                return "ǹսʿ";
            case 131:
                return "����ʿ";
            case 132:
                return "����ʿ";
            case 200:
                return "ħ��ʦ";
            case 210:
                return "��ʦ(��,��)";
            case 211:
                return "��ʦ(��,��)";
            case 212:
                return "ħ��ʦ(��,��)";
            case 220:
                return "��ʦ(��,��)";
            case 221:
                return "��ʦ(��,��)";
            case 222:
                return "ħ��ʦ(��,��)";
            case 230:
                return "��ʦ";
            case 231:
                return "��˾";
            case 232:
                return "����";
            case 300:
                return "������";
            case 310:
                return "����";
            case 311:
                return "����";
            case 312:
                return "������";
            case 320:
                return "����";
            case 321:
                return "����";
            case 322:
                return "����";
            case 400:
                return "����";
            case 410:
                return "�̿�";
            case 411:
                return "��Ӱ��";
            case 412:
                return "��ʿ";
            case 420:
                return "����";
            case 421:
                return "���п�";
            case 422:
                return "����";
            case 500:
                return "���I";
            case 510:
                return "ȭ��";
            case 511:
                return "��ʿ";
            case 512:
                return "���ӳ�";
            case 520:
                return "��ǹ��";
            case 521:
                return "��";
            case 522:
                return "����";
            case 1100:
                return "����ʿ(һת)";
            case 1110:
                return "����ʿ(��ת)";
            case 1111:
                return "����ʿ(��ת)";
            case 1112:
                return "����ʿ(��ת)";
            case 1200:
                return "����ʿ(һת)";
            case 1210:
                return "����ʿ(��ת)";
            case 1211:
                return "����ʿ(��ת)";
            case 1212:
                return "����ʿ(��ת)";
            case 1300:
                return "����ʹ��(һת)";
            case 1310:
                return "����ʹ��(��ת)";
            case 1311:
                return "����ʹ��(��ת)";
            case 1312:
                return "����ʹ��(��ת)";
            case 1400:
                return "ҹ����(һת)";
            case 1410:
                return "ҹ����(��ת)";
            case 1411:
                return "ҹ����(��ת)";
            case 1412:
                return "ҹ����(��ת)";
            case 1500:
                return "��Ϯ��(һת)";
            case 1510:
                return "��Ϯ��(��ת)";
            case 1511:
                return "��Ϯ��(��ת)";
            case 1512:
                return "��Ϯ��(��ת)";
            case 2100:
                return "ս��(һת)";
            case 2110:
                return "ս��(��ת)";
            case 2111:
                return "ս��(��ת)";
            case 2112:
                return "ս��(��ת)";
            default:
                return "δְ֪ҵ";
        }
    }

    public static final String getJobBasicNameById(int job) {
        switch (job) {
            case 0:
                return "����";
            case 1000:
                return "������";
            case 2000:
                return "սͯ";
            case 100:
                return "սʿ";
            case 110:
                return "����";
            case 111:
                return "��ʿ";
            case 112:
                return "Ӣ��";
            case 120:
                return "׼��ʿ";
            case 121:
                return "��ʿ";
            case 122:
                return "ʥ��ʿ";
            case 130:
                return "ǹսʿ";
            case 131:
                return "����ʿ";
            case 132:
                return "����ʿ";

            case 200:
                return "ħ��ʦ";
            case 210:
                return "��ʦ(��,��)";
            case 211:
                return "��ʦ(��,��)";
            case 212:
                return "ħ��ʦ(��,��)";
            case 220:
                return "��ʦ(��,��)";
            case 221:
                return "��ʦ(��,��)";
            case 222:
                return "ħ��ʦ(��,��)";
            case 230:
                return "��ʦ";
            case 231:
                return "��˾";
            case 232:
                return "����";

            case 300:
                return "������";
            case 310:
                return "����";
            case 311:
                return "����";
            case 312:
                return "������";
            case 320:
                return "����";
            case 321:
                return "����";
            case 322:
                return "����";

            case 400:
                return "����";
            case 410:
                return "�̿�";
            case 411:
                return "��Ӱ��";
            case 412:
                return "��ʿ";
            case 420:
                return "����";
            case 421:
                return "���п�";
            case 422:
                return "����";
            case 500:
                return "���I";
            case 510:
                return "ȭ��";
            case 511:
                return "��ʿ";
            case 512:
                return "���ӳ�";
            case 520:
                return "��ǹ��";
            case 521:
                return "��";
            case 522:
                return "����";

            case 1100:
                return "����ʿ(һת)";
            case 1110:
                return "����ʿ(��ת)";
            case 1111:
                return "����ʿ(��ת)";
            case 1112:
                return "����ʿ(��ת)";

            case 1200:
                return "����ʿ(һת)";
            case 1210:
                return "����ʿ(��ת)";
            case 1211:
                return "����ʿ(��ת)";
            case 1212:
                return "����ʿ(��ת)";

            case 1300:
                return "����ʹ��(һת)";
            case 1310:
                return "����ʹ��(��ת)";
            case 1311:
                return "����ʹ��(��ת)";
            case 1312:
                return "����ʹ��(��ת)";

            case 1400:
                return "ҹ����(һת)";
            case 1410:
                return "ҹ����(��ת)";
            case 1411:
                return "ҹ����(��ת)";
            case 1412:
                return "ҹ����(��ת)";

            case 1500:
                return "��Ϯ��(һת)";
            case 1510:
                return "��Ϯ��(��ת)";
            case 1511:
                return "��Ϯ��(��ת)";
            case 1512:
                return "��Ϯ��(��ת)";

            case 2100:
                return "ս��(һת)";
            case 2110:
                return "ս��(��ת)";
            case 2111:
                return "ս��(��ת)";
            case 2112:
                return "ս��(��ת)";

            default:
                return "δְ֪ҵ";
        }
    }
}
