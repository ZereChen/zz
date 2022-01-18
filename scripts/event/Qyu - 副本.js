var setupTask;
var map;
function init() {
	scheduleNew();
}

function scheduleNew() {
	
	var nextTime = 60*1000;//cal.getTimeInMillis();
	nextTime += java.lang.System.currentTimeMillis() ;
	setupTask =  em.scheduleAtTimestamp("start", nextTime);
	
}

function cancelSchedule() { 
	if (setupTask != null)
		setupTask.cancel(true);
}

function start() {
	
	scheduleNew()
	var hour=new Date(java.lang.System.currentTimeMillis()).getHours();
	var minute=new Date(java.lang.System.currentTimeMillis()).getMinutes();
	if (hour == 20&& minute == 30) 
	{
	var eim = em.newInstance("Laba");
	var redm1 = getredm(6, 999);
        for (var i = 1; i < 7; i++) {
        if (i == redm1) {
         map = eim.setInstanceMap(910000000+i);
         map.spawnNpc(9001106, new java.awt.Point(56, 34));
		em.broadcastServerMsg(5120027, "神秘人物出现在了市场的 1 - 6 洞某个洞穴，找到他会有奖励，你只有 3 分钟时间", true);
                       }
                    }
    }
  if (hour == 20&& minute == 33 ) 
	{
         map.removeNpc(9001106);
		em.broadcastServerMsg(5120027, "神秘人物消失了", true);
                       }
	
	
	if (hour == 21&& minute == 30) 
	{
	var eim = em.newInstance("Laba");
	var redm1 = getredm(6, 999);
        for (var i = 1; i < 7; i++) {
        if (i == redm1) {
         map = eim.setInstanceMap(910000000+i);
         map.spawnNpc(9001106, new java.awt.Point(56, 34));
		em.broadcastServerMsg(5120027, "神秘人物出现在了市场的 1 - 6 洞某个洞穴，找到他会有奖励，你只有 3 分钟时间", true);
                       }
                    }
    }
  if (hour == 21&& minute == 33 ) 
	{
         map.removeNpc(9001106);
		em.broadcastServerMsg(5120027, "神秘人物消失了", true);
                       }
					   
	if (hour == 22&& minute == 30) 
	{
	var eim = em.newInstance("Laba");
	var redm1 = getredm(6, 999);
        for (var i = 1; i < 7; i++) {
        if (i == redm1) {
         map = eim.setInstanceMap(910000000+i);
         map.spawnNpc(9001106, new java.awt.Point(56, 34));
		em.broadcastServerMsg(5120027, "神秘人物出现在了市场的 1 - 6 洞某个洞穴，找到他会有奖励，你只有 3 分钟时间", true);
                       }
                    }
    }
  if (hour == 22&& minute == 33 ) 
	{
         map.removeNpc(9001106);
		em.broadcastServerMsg(5120027, "神秘人物消失了", true);
                       }
					   
	if (hour == 23&& minute == 30) 
	{
	var eim = em.newInstance("Laba");
	var redm1 = getredm(6, 999);
        for (var i = 1; i < 7; i++) {
        if (i == redm1) {
         map = eim.setInstanceMap(910000000+i);
         map.spawnNpc(9001106, new java.awt.Point(56, 34));
		em.broadcastServerMsg(5120027, "神秘人物出现在了市场的 1 - 6 洞某个洞穴，找到他会有奖励，你只有 3 分钟时间", true);
                       }
                    }
    }
  if (hour == 23&& minute == 33 ) 
	{
         map.removeNpc(9001106);
		em.broadcastServerMsg(5120027, "神秘人物消失了", true);
                       }
	
}
function monsterDrop(eim, player, mob) {}
function getredm(max, count) {
    var redm = Math.floor(Math.random() * max);
    if (redm == count) {
        redm = getredm(max, count)
    }
    return redm;
}

rand = (function () {
    var today = new Date();
    var seed = today.getTime();
    function rnd() {
        seed = (seed * 9301 + 49297) % 233280;
        return seed / (233280.0);
    };
    return function rand(number) {
        return Math.ceil(rnd() * number);
    };
})();