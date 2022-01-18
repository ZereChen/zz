package client.messages.commands.a;

import client.MapleCharacter;
import client.MapleClient;
import client.inventory.MapleInventoryType;
import client.inventory.MaplePet;
import client.inventory.PetDataFactory;
import client.messages.commands.CommandExecute;
import constants.ServerConstants.PlayerGMRank;
import handling.channel.ChannelServer;
import handling.world.CheaterData;
import handling.world.World;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import scripting.EventManager;
import scripting.NPCScriptManager;
import server.Timer;
import server.events.MapleEvent;
import tools.FileoutputUtil;
import tools.MaplePacketCreator;
import tools.StringUtil;
import tools.packet.PetPacket;

/**
 *
 * @author ���
 */
public class PlayerCommand {

   /* public static PlayerGMRank getPlayerLevelRequired() {
        return PlayerGMRank.NORMAL;
    }*/

    public static class cd extends save {
    }
    public static class �浵 extends save {
    }
    public static class bz extends helpll {
    }
    public static class ���� extends helpll {
    }
 
 //   public static class ���� extends bw {
  //  }

    public static class �⿨ extends ck {
    }
    public static class jc extends ��� {
    }
   // public static class yy extends ���� {
    //}
    /*public static class admin extends zev {
    }*/
    public static class jk extends ck {
    }

    public static class ck extends CommandExecute {

        @Override
    public int execute(MapleClient c, String[] splitted) {
            //   PredictCardFactory.getInstance().initialize();
            NPCScriptManager.getInstance().dispose(c);
            c.sendPacket(MaplePacketCreator.enableActions());
            c.getPlayer().dropMessage(5, "��������ɹ�������ʱ��ô�������뷴����ZEV��71447500");
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:ָ����Ϣ - [���:" + c.getPlayer().getName() + "]ʹ�ý⿨����"));
            
            //c.getPlayer().dropMessage(6, "��ǰʱ����" + FileoutputUtil.CurrentReadable_Time() + " GMT+8 | ����ֵ���� " + (Math.round(c.getPlayer().getEXPMod()) * 100) * Math.round(c.getPlayer().getStat().expBuff / 100.0) + "%, ���ﱶ�� " + (Math.round(c.getPlayer().getDropMod()) * 100) * Math.round(c.getPlayer().getStat().dropBuff / 100.0) + "%, ��ұ��� " + Math.round(c.getPlayer().getStat().mesoBuff / 100.0) * 100 + "%");
            //c.getPlayer().dropMessage(6, "��ǰ�ӳ� " + c.getPlayer().getClient().getLatency() + " ����");
            //  NPCScriptManager.getInstance().start(c, 9102001);
            if (c.getPlayer().isAdmin()) {
                c.sendPacket(MaplePacketCreator.sendPyramidEnergy("massacre_hit", String.valueOf(50)));

//                  c.sendPacket(MaplePacketCreator.sendPyramidResult((byte) 1, 23));
//                MapleCharacter chr = c.getPlayer();
//                for (MaplePet pet : chr.getPets()) {
//                    if (pet.getSummoned()) {
//                        int newFullness = pet.getFullness() - PetDataFactory.getHunger(pet.getPetItemId());
//                        newFullness = 100;
//                        if (newFullness <= 5) {
//                            pet.setFullness(15);
//                            chr.unequipPet(pet, true);
//                        } else {
//                            pet.setFullness(newFullness);
//                            chr.getClient().sendPacket(PetPacket.updatePet(pet, chr.getInventory(MapleInventoryType.CASH).getItem(pet.getInventoryPosition()), true));
//                        }
//                    }
//                }
//                c.getPlayer().getStat().setDex((short) 4);
//                c.getPlayer().updateSingleStat(MapleStat.DEX, 4);
                //   System.out.println(new Date(System.currentTimeMillis() + (long) (3 * 60 * 60 * 1000)));
                //     c.getPlayer().getGuild().gainGP(50);  
                //     c.getPlayer().saveToDB(false, false);
            }//      
            return 1;
        }
    }

    public static class save extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            c.getPlayer().saveToDB(false, false);
            c.getPlayer().dropMessage("��ǰʱ���� " + FileoutputUtil.CurrentReadable_Time()+ " ����ɫ��Ϣ�浵�ɹ���");
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:ָ����Ϣ - [���:" + c.getPlayer().getName() + "]ʹ�ø��˴浵����"));
                      
            return 1;
        }
    }

    /*public static class help extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            NPCScriptManager.getInstance().dispose(c);
            c.sendPacket(MaplePacketCreator.enableActions());
            NPCScriptManager npc = NPCScriptManager.getInstance();
            npc.start(c, 9101001);
            return 1;
        }
    }*/
    
     /*public static class zev extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            NPCScriptManager.getInstance().dispose(c);
            c.sendPacket(MaplePacketCreator.enableActions());
            NPCScriptManager npc = NPCScriptManager.getInstance();
            World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:ָ����Ϣ - [���:" + c.getPlayer().getName() + "]���������ߺ�̨����"));
           
            npc.start(c, 2000,9);
            return 1;
        }
    }*/
    // public static class bw extends CommandExecute {

       // @Override
        //public int execute(MapleClient c, String[] splitted) {
           // NPCScriptManager.getInstance().dispose(c);
          //  c.sendPacket(MaplePacketCreator.enableActions());
            //NPCScriptManager npc = NPCScriptManager.getInstance();
           // npc.start(c, 2000,1);
            //return 1;
       // }
  //  }

    /*public static class Mobdrop extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            NPCScriptManager.getInstance().dispose(c);
            c.sendPacket(MaplePacketCreator.enableActions());
            NPCScriptManager npc = NPCScriptManager.getInstance();
            npc.start(c, 9900004,225);
            return 1;
        }
    }*/

    public static class GM extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
            if (splitted[1] == null) {
                c.getPlayer().dropMessage(6, "@GM����Ͽո�����Ҫ˵�Ļ���лл.");
                return 1;
            }
            if (c.getPlayer().isGM()) {
                c.getPlayer().dropMessage(6, "��Ϊ���Լ��ǹ����޷�ʹ�ô�����,���Գ���!cngm <ѶϢ> ����GM�����l��~");
                return 1;
            }
            if (!c.getPlayer().getCheatTracker().GMSpam(100000, 1)) { // 5 minutes.
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "�l�� " + c.getPlayer().getClient().getChannel() + " ��� [" + c.getPlayer().getName() + "] : " + StringUtil.joinStringFrom(splitted, 1)));
                c.getPlayer().dropMessage(6, "ѶϢ�Ѿ�����GM��!");
            } else {
                c.getPlayer().dropMessage(6, "Ϊ�˷�ֹ��GMˢ������ÿ1���ֻ�ܷ�һ��.");
            }
            return 1;
        }
    }

    public static class helpll extends CommandExecute {

        @Override
        public int execute(MapleClient c, String[] splitted) {
           // c.getPlayer().dropMessage(1, "��ZEVMS��\r\n\r\n���ָ���б�\r\n\r\n\r\n@cd  �� @�浵   <��������>\r\n@GM + ��˵�Ļ�  <�Ի�����>\r\n\r\n- ���ߣ�ZEV��71447500 -\r\n");
           // c.getPlayer().dropMessage(5, "@bw  �� @����   <���ﱬ��>");
            c.getPlayer().dropMessage(5, "@cd  �� @�浵   <��������>");
            c.getPlayer().dropMessage(5, "@jk  �� @�⿨   <�������>");
           // c.getPlayer().dropMessage(5, "@yy  �� @����   <��������>");
          //  c.getPlayer().dropMessage(5, "@jc + ��ɫ      <�������>");
            c.getPlayer().dropMessage(5, "@GM + ��˵�Ļ�  <�Ի�����>");
         //  c.getPlayer().dropMessage(5, "@gg  �� @����   <��Ϸ��Ѷ>");
           c.getPlayer().dropMessage(5, "- ���ߣ�ZEV��71447500 -");
            return 1;
        }
    }
        public static class ��� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {

            if (splitted.length < 2) {
                return 0;
            }
            final StringBuilder builder = new StringBuilder();
            MapleCharacter other;
            String name = splitted[1];
            int ch = World.Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage(6, "��ұ�������");
                return 0;
            }
            other = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);

            if (other == null) {
                builder.append("��ɫ������");
                c.getPlayer().dropMessage(6, builder.toString());
            } else {
                if (other.getClient().getLastPing() <= 0) {
                    other.getClient().sendPing();
                }
                builder.append(MapleClient.getLogMessage(other, ""));
                builder.append(" �� ").append(other.getPosition().x);
                builder.append(" /").append(other.getPosition().y);

                builder.append(" || HP : ");
                builder.append(other.getStat().getHp());
                builder.append(" /");
                builder.append(other.getStat().getCurrentMaxHp());

                builder.append(" || MP : ");
                builder.append(other.getStat().getMp());
                builder.append(" /");
                builder.append(other.getStat().getCurrentMaxMp());

                builder.append(" || ������ : ");
                builder.append(other.getStat().getTotalWatk());
                builder.append(" || ħ������ : ");
                builder.append(other.getStat().getTotalMagic());
               // builder.append(" || ��߹��� : ");
               // builder.append(other.getStat().getCurrentMaxBaseDamage());
                //builder.append(" || ����%�� : ");
                //builder.append(other.getStat().dam_r);
                //builder.append(" || BOSS����%�� : ");
               // builder.append(other.getStat().bossdam_r);

                builder.append(" || ���� : ");
                builder.append(other.getStat().getStr());
                builder.append(" || ���� : ");
                builder.append(other.getStat().getDex());
                builder.append(" || ���� : ");
                builder.append(other.getStat().getInt());
                builder.append(" || ���\ : ");
                builder.append(other.getStat().getLuk());

               // builder.append(" || ���� : ");
               // builder.append(other.getStat().getTotalStr());
               // builder.append(" || ���� : ");
                //builder.append(other.getStat().getTotalDex());
               // builder.append(" || ���� : ");
                //builder.append(other.getStat().getTotalInt());
               // builder.append(" || ���� : ");
               // builder.append(other.getStat().getTotalLuk());

                builder.append(" || ����ֵ : ");
                builder.append(other.getExp());

                builder.append(" || ���״̬ : ");
                builder.append(other.getParty() != null);

                //builder.append(" || ���נ�B: ");
               // builder.append(other.getTrade() != null);
               // builder.append(" || Latency: ");
               // builder.append(other.getClient().getLatency());
              //  builder.append(" || ����PING: ");
               // builder.append(other.getClient().getLastPing());
               // builder.append(" || ����PONG: ");
              //  builder.append(other.getClient().getLastPong());
                builder.append(" || IP: ");
                builder.append(other.getClient().getSessionIPAddress());
                other.getClient().DebugMessage(builder);

                c.getPlayer().dropMessage(6, builder.toString());
                World.Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[����Ա��Ϣ]:ָ����Ϣ - [���:" + c.getPlayer().getName() + "]ʹ�ü�������Ϣ����"));
            
            }
            return 1;
        }
}
        public String getMessage() {
            return new StringBuilder().append("@��� <��ɫ����> - �鿴��ɫ��ǰ״̬").toString();

        }
         /*   public static class ���� extends CommandExecute {

        public int execute(MapleClient c, String splitted[]) {
            if (splitted.length < 2) {
                return 0;
            }
            c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.musicChange(splitted[1]));
            return 1;
        }

        public String getMessage() {
            return new StringBuilder().append("@���� - ��������").toString();
        }
    }*/
    }

    
