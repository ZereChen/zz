/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：黑龙挑战
 */
var JT = "#fUI/Basic/BtHide3/mouseOver/0#";
var 心 = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (cm.getPlayer().getClient().getChannel() != 3) {
        cm.说明文字("黑龙在 #b3#k 频道挑战。");
        cm.对话结束();
    } else if (status == 0) {
        var selStr = "\r\n";
        if (cm.判断指定地图玩家数量(240060200) == 0 && cm.判断指定地图玩家数量(240060100) == 0 && cm.判断指定地图玩家数量(240060000) == 0) {
            if (cm.Getcharacterz("黑龙远征队队长", 202) > 0) {
                if (cm.Getcharacterz("黑龙远征队队长", 202) <= 1) {
                    if (cm.Getcharacterz("" + cm.getPlayer().id + "", 201) <= 0) {
                        selStr += " #L1##b加入远征队#k#l\r\n";
                    } else {
                        if (cm.Getcharacterz("" + cm.getPlayer().id + "", 201) < 2) {
                            selStr += " #L2##b退出远征队#k#l\r\n";
                        } else {
                            selStr += " #L13##r开始远征黑龙#k#l\r\n";
                            selStr += " #L12##b解散远征队#k#l\r\n";
                        }
                    }
                }
            } else {
                if (cm.Getcharacterz("" + cm.getPlayer().id + "", 201) <= 0) {
                    selStr += " #L11##b开启远征队#k#l\r\n";
                }
            }
            if (cm.Getcharacterz("黑龙远征队队长", 202) > 1 && cm.Getcharacterz("" + cm.getPlayer().id + "", 201) > 0) {
                selStr += " #L4##b重新加入战斗#k#l\r\n";
            }
            selStr += " #L3##b查看远程队#k#l\r\n";
        }
        cm.说明文字(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.Gaincharacterz("" + cm.getPlayer().id + "", 201, 1);
                cm.说明文字("加入#b黑龙远征队#k成功！！！");
                cm.对话结束();
                break;
            case 2:
                cm.Gaincharacterz("" + cm.getPlayer().id + "", 201, -cm.Getcharacterz("" + cm.getPlayer().id + "", 201));
                cm.说明文字("退出#b黑龙远征队#k成功！！！");
                cm.对话结束();
                break;
            case 11:
                if (cm.Getcharacterz("黑龙远征队队长", 202) > 0) {
                    cm.说明文字("有人已经开启了黑龙远征队！！！");
                    cm.对话结束();
                } else {
                    cm.Gaincharacterz("" + cm.getPlayer().id + "", 201, 2);
                    cm.Gaincharacterz("黑龙远征队队长", 202, 1);
                    cm.说明文字("开启#b黑龙远征队#k成功，赶快召集你的队伍吧。");
                    cm.对话结束();
                }
                break;
            case 12:
                cm.Gaincharacterz("" + cm.getPlayer().id + "", 201, -cm.Getcharacterz("" + cm.getPlayer().id + "", 201));
                cm.Gaincharacterz("黑龙远征队队长", 202, -cm.Getcharacterz("黑龙远征队队长", 202));
                cm.解散黑龙远征队();
                cm.说明文字("解散#b黑龙远征队#k成功。");
                cm.对话结束();
                break;
            case 13:
                cm.getMap(240060000).resetFully();
                cm.getMap(240060100).resetFully();
                cm.getMap(240060200).resetFully();
                cm.Gaincharacterz("黑龙远征队队长", 202, 1);
                cm.开始黑龙远征(3, 240060000);
                cm.指定地图召唤怪物(8810024, 240060000, 890, 230);
                cm.指定地图召唤怪物(8810025, 240060100, -360, 230);
                break;
            case 3:
                cm.对话结束();
                cm.打开NPC(2083004, 1);
                break;
            case 4:
                cm.warp(240060000, 0);
                cm.对话结束();
                break;
        }
    }
} 