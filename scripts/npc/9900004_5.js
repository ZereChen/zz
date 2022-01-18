/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：清理背包
 */
var 图标1 = "#fUI/UIWindow.img/FadeYesNo/icon7#";
var 图标2 = "#fUI/StatusBar.img/BtClaim/mouseOver/0#";
var 关闭 = "#fUI/UIWindow.img/CashGachapon/BtOpen/mouseOver/0#";
var 打开 = "#fUI/UIWindow.img/CashGachapon/BtOpen/disabled/0#";
var JD = "#fUI/Basic/BtHide3/mouseOver/0#";
var 心 = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
var 装备2 = "#fUI/CashShop.img/Base/Tab2/Enable/0#";
var 消耗2 = "#fUI/CashShop.img/Base/Tab2/Enable/1#";  
var 设置2 = "#fUI/CashShop.img/Base/Tab2/Enable/2#"; 
var 其他2 = "#fUI/CashShop.img/Base/Tab2/Enable/3#";   
var 特殊2 = "#fUI/CashShop.img/Base/Tab2/Enable/4#"; 
var a = "#fEffect/CharacterEff.img/1112926/0/1#";
function start() {
    status = -1;
    action(1, 0, 0)
}

function action(mode, type, selection) {
    if (status <= 0 && mode <= 0) {
        cm.dispose();
        return
    }
    if (mode == 1) {
        status++
    } else {
        status--
    }	 
	if (status <= 0) {
        var selStr = "\t\t\t" + 心 + "  " + 心 + " #r#e < 清理背包 > #k#n " + 心 + "  " + 心 + "\r\n\r\n";
		
		if (cm.GetPiot("清理背包开关","1") <= 0) {
				selStr += "  #b选择你要清理的背包类型:\r\n\r\n";
				selStr += "#L1#"+装备2+"#l";
				
				selStr += "\t#L2#"+消耗2+"#l";
				
				selStr += "\t#L3#"+设置2+"#l";
				
				selStr += "\t#L4#"+其他2+"#l";
				
				selStr += "\t#L5#"+特殊2+"#l";
		}else{
		selStr += "\r\n  "+维护图+"";
		
		 
		}
		selStr += "\r\n\r\n\t\t\t\t#L0##b返回界面#l";
		if (cm.getPlayer().getGMLevel() == 6) {	
			if (cm.GetPiot("清理背包开关","1") <= 0  ) {
			selStr += "\r\n\t\t\t\t#L100##b清理背包#g[开启中]#r[GM]#k#l";} 
			if (cm.GetPiot("清理背包开关","1") >= 1  ) {
			selStr += "\r\n\t\t\t\t#L101##b清理背包#r[关闭中]#r[GM]#k#l";}
			}	

        cm.sendSimple(selStr)
    } else if (status == 1) {
        switch (selection) {
			
			case 0:
                cm.dispose();
                cm.openNpc(9900004,0);
                break;
            case 1:
                cm.dispose();
                cm.openNpc(9900004,51);
                break;
			case 2:
                cm.dispose();
                cm.openNpc(9900004,52);
                break;
			case 3:
                cm.dispose();
                cm.openNpc(9900004,53);
                break;	
			case 4:
                cm.dispose();
                cm.openNpc(9900004,54);
                break;		
			case 5:
                cm.dispose();
                cm.openNpc(9900004,55);
                break;	


			case 100:
			cm.GainPiot("清理背包开关","1",-清理背包开关);
			cm.GainPiot("清理背包开关","1",1);
            cm.dispose();
			cm.openNpc(9900004,5);
            break;
			case 101:
			cm.GainPiot("清理背包开关","1",-清理背包开关);
            cm.dispose();
            cm.openNpc(9900004,5);
            break
        }
    }
}