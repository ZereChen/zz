/*
���ϵͳ
 */
package handling.channel.handler;

import client.MapleCharacter;
import client.MapleClient;
import handling.world.MapleParty;
import handling.world.MaplePartyCharacter;
import handling.world.PartyOperation;
import handling.world.World;
import tools.MaplePacketCreator;
import tools.data.LittleEndianAccessor;

public class PartyHandler {

    public static void DenyPartyRequest(LittleEndianAccessor slea, MapleClient c) {
        /*���������*/
        int action = slea.readByte();
        /*������*/
        int partyid = slea.readInt();
        /*�����ҵĶ���Ϊ��*/
        if (c.getPlayer().getParty() == null) {
            /*������������ֵ*/
            MapleParty party = World.Party.getParty(partyid);
            /*������鲻Ϊ��*/
            if (party != null) {
                /*ѭ����ȡ���������*/
                switch (action) {
                    /*�����0x1B ��������*/
                    case 0x1B:
                        /*������������С��6��*/
                        if (party.getMembers().size() < 6) {
                            /*֪ͨ�����Ա���¶�Ա�������*/
                            World.Party.updateParty(partyid, PartyOperation.JOIN, new MaplePartyCharacter(c.getPlayer()));
                            /*�յ���ҵ�Ѫ��*/
                            c.getPlayer().receivePartyMemberHP();
                            /*������ҵ�Ѫ��*/
                            c.getPlayer().updatePartyMemberHP();
                        } else {
                            /*��ӳ�Ա����*/
                            c.sendPacket(MaplePacketCreator.partyStatusMessage(17));
                        }
                        break;
                    /*�����0x16 �ܾ�����*/
                    case 0x16:
                        /*������������ֵΪ��ȡ������״̬ ��ȡ�ӳ��Ķ�����*/
                        MapleCharacter cfrom = c.getChannelServer().getPlayerStorage().getCharacterById(party.getLeader().getId());
                        /*����Ϊ��*/
                        if (cfrom != null) {
                            /*���ͷ����Ϣ��֪ͨ�����ߣ��������ߡ��ܾ�������д����롱*/
                            cfrom.getClient().sendPacket(MaplePacketCreator.partyStatusMessage(23, c.getPlayer().getName()));
                        }
                        break;
                    default:
                        //����ѭ��
                        break;
                }

            } else {
                c.getPlayer().dropMessage(5, "Ҫ�μӵĶ��鲻���ڡ�");
            }
        } else {
            c.getPlayer().dropMessage(5, "���Ѿ���һ����ӣ��޷������������!");
        }

    }

    public static void PartyOperatopn(LittleEndianAccessor slea, MapleClient c) {
        /*���������*/
        int operation = slea.readByte();
        /*��������party ��ȡ��Ҷ���*/
        MapleParty party = c.getPlayer().getParty();
        /*�������� partyplayer ��ȡ������������*/
        MaplePartyCharacter partyplayer = new MaplePartyCharacter(c.getPlayer());

        switch (operation) {
            case 1: //��������
                /*�����ȡ��Ҷ���Ϊ��*/
                if (party == null) {
                    /*��ֵparty �߳��д���һ������*/
                    party = World.Party.createParty(partyplayer);
                    /*�������*/
                    c.getPlayer().setParty(party);
                    /*������ӵķ��*/
                    c.sendPacket(MaplePacketCreator.partyCreated(party.getId()));
                    /*�����������Ƕӳ������Ҷ������� = 1��*/
                } else if (partyplayer.equals(party.getLeader()) && party.getMembers().size() == 1) { //only one, reupdate
                    c.sendPacket(MaplePacketCreator.partyCreated(party.getId()));
                } else {
                    c.getPlayer().dropMessage(5, "�㲻�ܴ���һ�����,��Ϊ���Ѿ�����һ��������");
                }
                break;
            case 2: // ��ɢand�뿪����
                /*������鲻Ϊ��*/
                if (party != null) { //
                    /*����뿪�Ķ��������Ƕӳ�*/
                    if (partyplayer.equals(party.getLeader())) { // ��ɢ
                        /*֪ͨ��Ա���ӳ���ɢ����*/
                        World.Party.updateParty(party.getId(), PartyOperation.DISBAND, partyplayer);
                        /*�ڸ�����*/
                        if (c.getPlayer().getEventInstance() != null) {
                            c.getPlayer().getEventInstance().disbandParty();
                        }
                        /*�ڸ�����*/
                        if (c.getPlayer().getPyramidSubway() != null) {
                            c.getPlayer().getPyramidSubway().fail(c.getPlayer());
                        }
                    } else {
                        /*֪ͨ���������ˣ���Ա�˳�����*/
                        World.Party.updateParty(party.getId(), PartyOperation.LEAVE, partyplayer);
                        /*�ڸ�����*/
                        if (c.getPlayer().getEventInstance() != null) {
                            c.getPlayer().getEventInstance().leftParty(c.getPlayer());
                        }
                        /*�ڸ�����*/
                        if (c.getPlayer().getPyramidSubway() != null) {
                            c.getPlayer().getPyramidSubway().fail(c.getPlayer());
                        }
                    }
                    /*���ÿ����״̬*/
                    c.getPlayer().setParty(null);
                }
                break;
            case 3: //�������
                /*����ID���*/
                int partyid = slea.readInt();
                /*��ɫ�Ķ���Ϊ��*/
                if (party == null) {
                    /*party���θ�ֵ*/
                    party = World.Party.getParty(partyid);
                    /*������鲻Ϊ��*/
                    if (party != null) {
                        /*�����������С��6��*/
                        if (party.getMembers().size() < 6) {
                            int a = 1;
                            for (final MaplePartyCharacter chr : party.getMembers()) {
                                if (chr.getMapid() != c.getPlayer().getMapId()) {
                                    a = 0;
                                }
                            }
                            if (a > 0) {
                                /*֪ͨ��Ա�����˼������*/
                                World.Party.updateParty(party.getId(), PartyOperation.JOIN, partyplayer);
                                /*�յ���ҵ�Ѫ��*/
                                c.getPlayer().receivePartyMemberHP();
                                /*������ҵ�Ѫ��*/
                                c.getPlayer().updatePartyMemberHP();
                            } else {
                                c.getPlayer().dropMessage(5, "�޷�������ӡ�");
                            }
                        } else {
                            /*���ͷ������ӳ�Ա������*/
                            c.sendPacket(MaplePacketCreator.partyStatusMessage(17));
                        }
                    } else {
                        c.getPlayer().dropMessage(5, "Ҫ����Ķ��鲻����");
                    }
                } else {
                    c.getPlayer().dropMessage(5, "���Ѿ��ж����ˣ��޷��ټ����������顣���������ԣ�");
                }
                break;
            case 4: // ����
                /*������������ֵ ��ȡƵ�������������*/
                MapleCharacter invited = c.getChannelServer().getPlayerStorage().getCharacterByName(slea.readMapleAsciiString());
                /*���invited��Ϊ��*/
                if (invited != null) {
                    /*������������Ҷ���Ϊ�գ����������߶��鲻Ϊ��*/
                    if (invited.getParty() == null && party != null) {
                        /*�����������С��6��*/
                        if (party.getMembers().size() < 6) {
                            //c.sendPacket(MaplePacketCreator.partyStatusMessage(23, invited.getName()));
                            /*������Ϣ������Է��������*/
                            if (invited.getMapId() == c.getPlayer().getMapId() && c.getPlayer().getClient().getChannel() == invited.getClient().getChannel()) {
                                invited.getClient().sendPacket(MaplePacketCreator.partyInvite(c.getPlayer()));
                            } else {
                                c.getPlayer().dropMessage(5, "�޷�����Ŀ�������ӡ�");
                            }
                        } else {
                            /*�����Ա����*/
                            c.sendPacket(MaplePacketCreator.partyStatusMessage(17));
                        }
                    } else {
                        /*������Ϣ��ʾ���������ҡ��Ѿ����������顱*/
                        c.sendPacket(MaplePacketCreator.partyStatusMessage(16));
                    }
                } else {
                    c.sendPacket(MaplePacketCreator.partyStatusMessage(18));
                }
                break;
            case 5:  // �����Ա
                if ((party == null) || (partyplayer == null) || (!partyplayer.equals(party.getLeader()))) {
                    break;
                }
                /*��������е�����Ƕӳ�*/
                if (partyplayer.equals(party.getLeader())) {
                    /*����expelled��������ֵ ��ȡ����ı��*/
                    MaplePartyCharacter expelled = party.getMemberById(slea.readInt());
                    /*�����Ų�Ϊ��*/
                    if (expelled != null) {
                        /*����֪ͨ������Ա����Ա�����������*/
                        World.Party.updateParty(party.getId(), PartyOperation.EXPEL, expelled);
                        /*������ڸ�����*/
                        if (c.getPlayer().getEventInstance() != null) {
                            /*������鱻�������������ߵ�*/
                            if (expelled.isOnline()) {
                                /*����������ȫ�Ӵ��ͳ�ȥ*/
                                c.getPlayer().getEventInstance().disbandParty();
                            }
                        }
                        /*������*/
                        if (c.getPlayer().getPyramidSubway() != null && expelled.isOnline()) {
                            c.getPlayer().getPyramidSubway().fail(c.getPlayer());
                        }
                    }
                }
                break;
            case 6: // �ı�ӳ�
                /*���鲻Ϊ��*/
                if (party != null) {
                    /*�������� newleader �µĶӳ� =��ȡ������*/
                    MaplePartyCharacter newleader = party.getMemberById(slea.readInt());
                    /*����������¶ӳ��Ķ���ID����Ǵ��ڵġ����Ҹ����¶ӳ��Ķ�Ա�Ƕӳ�*/
                    if (newleader != null && partyplayer.equals(party.getLeader())) {
                        /*���ö���Ķӳ��������¶ӳ�*/
                        party.setLeader(newleader);//������ӳ�
                        /*���Ͷ�����¶ӳ�״̬��R�����棬�����еĶӳ�����ǣ���ת�Ƶ��¶ӳ���*/
                        World.Party.updateParty(party.getId(), PartyOperation.SILENT_UPDATE, newleader);
                    }
                    /*����*/
                    c.sendPacket(MaplePacketCreator.enableActions());
                }
                break;
            default:
                System.out.println("δ֪�Ķ������. 0x0" + operation);
                break;
        }
    }
}

//    public static final void DenyPartyRequest(final LittleEndianAccessor slea, final MapleClient c) {
//        final int action = slea.readByte();
//        final int partyid = slea.readInt();
//        //    MapleCharacter cfrom = c.getChannelServer().getPlayerStorage().getCharacterByName(slea.readMapleAsciiString());
//        //      if (cfrom != null) {
//        //        cfrom.getClient().sendPacket(MaplePacketCreator.partyStatusMessage(23, c.getPlayer().getName()));
//        //    }
//        if (c.getPlayer().getParty() == null) {
//            MapleParty party = World.Party.getParty(partyid);
//            if (party != null) {
//                if (action == 0x1B) { //accept
//                    if (party.getMembers().size() < 12 ) {//�������
//                        World.Party.updateParty(partyid, PartyOperation.JOIN, new MaplePartyCharacter(c.getPlayer()));
//                        c.getPlayer().receivePartyMemberHP();
//                        c.getPlayer().updatePartyMemberHP();
//                    } else {
//                        c.sendPacket(MaplePacketCreator.partyStatusMessage(17));
//                    }
//                } else if (action != 0x16) {
//                    final MapleCharacter cfrom = c.getChannelServer().getPlayerStorage().getCharacterById(party.getLeader().getId());
//                    if (cfrom != null) {
//                        cfrom.getClient().sendPacket(MaplePacketCreator.partyStatusMessage(23, c.getPlayer().getName()));
//                    }
//                }
//            } else {
//                c.getPlayer().dropMessage(5, "Ҫ�μӵĶ��鲻���ڡ�");
//            }
//        } else {
//            c.getPlayer().dropMessage(5, "���Ѿ���һ����ӣ��޷������������!");
//        }
//
//    }
//
//    public static final void PartyOperatopn(final LittleEndianAccessor slea, final MapleClient c) {
//        final int operation = slea.readByte();
//        MapleParty party = c.getPlayer().getParty();
//        MaplePartyCharacter partyplayer = new MaplePartyCharacter(c.getPlayer());
//
//        switch (operation) {
//            case 1: // create
//                if (c.getPlayer().getParty() == null) {
//                    party = World.Party.createParty(partyplayer);
//                    c.getPlayer().setParty(party);
//                    c.sendPacket(MaplePacketCreator.partyCreated(party.getId()));
//
//                } else {
//                    if (partyplayer.equals(party.getLeader()) && party.getMembers().size() == 1) { //only one, reupdate
//                        c.sendPacket(MaplePacketCreator.partyCreated(party.getId()));
//                    } else {
//                        c.getPlayer().dropMessage(5, "�㲻�ܴ���һ�����,��Ϊ���Ѿ�����һ��������");
//                    }
//                }
//                break;
//            case 2: // leave
//                if (party != null) { //are we in a party? o.O"
//                    if (partyplayer.equals(party.getLeader())) { // disband
//                        World.Party.updateParty(party.getId(), PartyOperation.DISBAND, partyplayer);
//                        if (c.getPlayer().getEventInstance() != null) {
//                            c.getPlayer().getEventInstance().disbandParty();
//                        }
//                        if (c.getPlayer().getPyramidSubway() != null) {
//                            c.getPlayer().getPyramidSubway().fail(c.getPlayer());
//                        }
//                    } else {
//                        World.Party.updateParty(party.getId(), PartyOperation.LEAVE, partyplayer);
//                        if (c.getPlayer().getEventInstance() != null) {
//                            c.getPlayer().getEventInstance().leftParty(c.getPlayer());
//                        }
//                        if (c.getPlayer().getPyramidSubway() != null) {
//                            c.getPlayer().getPyramidSubway().fail(c.getPlayer());
//                        }
//                    }
//                    c.getPlayer().setParty(null);
//                }
//                break;
//            case 3: // accept invitation
//                final int partyid = slea.readInt();
//                if (c.getPlayer().getParty() == null) {
//                    party = World.Party.getParty(partyid);
//                    if (party != null) {
//                        if (party.getMembers().size() < 12 ) {//�������
//                            World.Party.updateParty(party.getId(), PartyOperation.JOIN, partyplayer);
//                            c.getPlayer().receivePartyMemberHP();
//                            c.getPlayer().updatePartyMemberHP();
//                        } else {
//                            c.sendPacket(MaplePacketCreator.partyStatusMessage(17));
//                        }
//                    } else {
//                        c.getPlayer().dropMessage(5, "Ҫ����Ķ��鲻����");
//                    }
//                } else {
//                    c.getPlayer().dropMessage(5, "�㲻�ܴ���һ�����,��Ϊ���Ѿ�����һ��������");
//                }
//                break;
//            case 4: // invite
//                // TODO store pending invitations and check against them
//                final MapleCharacter invited = c.getChannelServer().getPlayerStorage().getCharacterByName(slea.readMapleAsciiString());
//                if (invited != null) {
//                    if (invited.getParty() == null && party != null) {
//                        if (party.getMembers().size() < 6 ) {//�������
//                            //  c.sendPacket(MaplePacketCreator.partyStatusMessage(23, invited.getName()));
//                            invited.getClient().sendPacket(MaplePacketCreator.partyInvite(c.getPlayer()));
//                        } else {
//                            break;
//                            // c.sendPacket(MaplePacketCreator.partyStatusMessage(16));
//                        }
//                    } else {
//                        c.sendPacket(MaplePacketCreator.partyStatusMessage(16));
//                    }
//                } else {
//                    c.sendPacket(MaplePacketCreator.partyStatusMessage(18));
//                }
//                break;
//            case 5: // expel
//                if (partyplayer != null && party != null) {
//                    if (partyplayer.equals(party.getLeader())) {
//                        final MaplePartyCharacter expelled = party.getMemberById(slea.readInt());
//                        if (expelled != null) {
//                            World.Party.updateParty(party.getId(), PartyOperation.EXPEL, expelled);
//                            if (c.getPlayer().getEventInstance() != null) {
//                                /*
//                                 * if leader wants to boot someone, then the
//                                 * whole party gets expelled TODO: Find an
//                                 * easier way to get the character behind a
//                                 * MaplePartyCharacter possibly remove just the
//                                 * expellee.
//                                 */
//                                if (expelled.isOnline()) {
//                                    c.getPlayer().getEventInstance().disbandParty();
//                                }
//                            }
//                            if (c.getPlayer().getPyramidSubway() != null && expelled.isOnline()) {
//                                c.getPlayer().getPyramidSubway().fail(c.getPlayer());
//                            }
//                        }
//                    }
//                }
//                break;
//            case 6: // change leader
//
//                if (party != null) {
//                    final MaplePartyCharacter newleader = party.getMemberById(slea.readInt());
//                    if (newleader != null && partyplayer.equals(party.getLeader())) {
//                        party.setLeader(newleader);
//                        World.Party.updateParty(party.getId(), PartyOperation.SILENT_UPDATE, newleader);
//                    }
//                    c.sendPacket(MaplePacketCreator.enableActions());
//                }
//                break;
//            default:
//                System.out.println("Unhandled Party function." + operation);
//                break;
//        }
//    }

