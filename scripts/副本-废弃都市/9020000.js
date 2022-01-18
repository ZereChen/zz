/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：废弃都市
 */

var 最小人数 = 1;
var 最大人数 = 6;
var 最低等级 = 21;
var 最高等级 = 256;
//废弃奖励预览
/*
 物品，概率
 */
var 奖励预览 = [
    [4004000, 30],
	[4004001, 30],
	[4004002, 30],
	[4004003, 30],
	[4004004, 1],
	[4020000, 10],
	[4020001, 10],
	[4020002, 10],
	[4020003, 10],
	[4020004, 10],
	[4020005, 10],
	[4020006, 10],
	[4020007, 10],
	[4020008, 10]
];
var status = 0;

function start() {
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }

    if (status == 1) {
        var 文本信息 = "";
        for (var i = 0; i < 奖励预览.length; i++) {
            文本信息 += "   " + cm.显示物品(奖励预览[i][0]) + "  " + 奖励预览[i][1] + " % #k\r\n";
        }
        cm.sendYesNo("\r\n     位于废弃底下水道的副本任务，可以通过完成该副本获取超多收益。副本的要求的人数是#b" + 最小人数 + " - " + 最大人数 + "#k，等级要求#b" + 最低等级 + " - " + 最高等级 + "#k，你要参加副本#b废弃都市#k吗？在这里盛产各种#r晶石母矿#k哦。\r\n\r\n   总计完成: #r" + cm.getBossRank("废弃都市", 2) + "\r\n#k   今日完成: #r" + cm.getBossLog("废弃都市") + "\r\n\r\n #k#e副本奖励预览:#n\r\n\r\n" + 文本信息 + "");
    } else if (status == 2) {
        if (cm.getParty() == null) {
            cm.sendOk("请组队再来找我，或者让队长找我。");
        } else if (!cm.isLeader()) {
            cm.sendOk("请叫你的队长来找我!");
        } else {
            var party = cm.getParty().getMembers();
            var mapId = cm.getMapId();
            var next = true;
            var levelValid = 0;
            var inMap = 0;

            var it = party.iterator();
            while (it.hasNext()) {
                var cPlayer = it.next();
                if (cPlayer.getLevel() >= 最低等级 && cPlayer.getLevel() <= 最高等级) {
                    levelValid += 1;
                } else {
                    next = false;
                }
                if (cPlayer.getMapid() == mapId) {
                    inMap += (cPlayer.getJobId() == 900 ? 4 : 1);
                }
            }
            if (party.size() > 最大人数 || inMap < 最小人数) {
                next = false;
            }
            if (next) {
                var em = cm.getEventManager("KerningPQ");
                if (em == null) {
                    cm.sendOk("找不到脚本，请联系管理员！");
                    cm.dispose();
                    return;
                } else {
                    var prop = em.getProperty("state");
                    if (prop == null || prop.equals("0")) {
                        em.startInstance(cm.getParty(), cm.getMap());
                    } else {
                        cm.sendOk("已经有队伍在里面挑战了。");
                        cm.dispose();
                        return;
                    }
                    cm.removeAll(4001008);
                    cm.removeAll(4001007);
                }
            } else {
                cm.sendOk("副本: #b废弃都市#k\r\n人数: #b" + 最小人数 + " - " + 最大人数 + "#k\r\n等级: #b" + 最低等级 + " - " + 最高等级 + "#k");
                cm.dispose();
                return;
            }
        }
        cm.dispose();
    }
}