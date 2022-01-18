var setupTask;

function init() {
    scheduleNew();
}

function scheduleNew() {
    em.setProperty("state", "false");
    em.setProperty("endEvent", "true");
    em.setProperty("check", "0");
    em.setProperty("maxCheck", "9999999");
    var cal = java.util.Calendar.getInstance();
    cal.set(java.util.Calendar.HOUR, 0);
    cal.set(java.util.Calendar.MINUTE, 10);
    cal.set(java.util.Calendar.SECOND, 0);
    var nextTime = cal.getTimeInMillis();
    while (nextTime <= java.lang.System.currentTimeMillis()) {
        nextTime += 1000 * 60 * 60 * 2; //���ö�ÿ���
    }
    setupTask = em.scheduleAtTimestamp("startEvent", nextTime);
}

function startEvent() {
    em.setProperty("state", "true");
    em.setProperty("endEvent", "false");
    em.setProperty("check", 0);
    em.setProperty("maxCheck", "" + getMaxCheck(Math.floor(Math.random() * 2)));
    var cal = java.util.Calendar.getInstance();
    cal.set(java.util.Calendar.HOUR, 0);
    cal.set(java.util.Calendar.MINUTE, 10);
    cal.set(java.util.Calendar.SECOND, 0);
    var nextTime = cal.getTimeInMillis();
    while (nextTime <= java.lang.System.currentTimeMillis()) {
        nextTime += 1000 * 60 * 10; //���ö�ý���
    }
    setupTask = em.scheduleAtTimestamp("finishEvent", nextTime);
	em.broadcastServerMsg(5121010, " ��¥��Ѿ���ʼ����Ҫ�μӵ�����뵽�г� 7 ���һרԱ�μӰɡ�",true);
        em.broadcastServerMsg("[��¥�]  ��Ѿ�������10���Ӻ��������1���ﵽ " + em.getProperty("maxCheck") + " ¥����ҽ���÷��Ľ�����");
		em.broadcastServerMsg("[��¥�]  ��Ѿ�������10���Ӻ��������1���ﵽ " + em.getProperty("maxCheck") + " ¥����ҽ���÷��Ľ�����");
		em.broadcastServerMsg("[��¥�]  ��Ѿ�������10���Ӻ��������1���ﵽ " + em.getProperty("maxCheck") + " ¥����ҽ���÷��Ľ�����");
}

function finishEvent() {
    if (em.getProperty("endEvent").equals("false")) {
        em.broadcastServerMsg("[��¥�] ��¥��Ѿ�������120���Ӻ�����һ�֡�");
    } else {
        em.broadcastServerMsg("[��¥�] ���ֻ��������һ����¥����120���Ӻ�����");
    }
    scheduleNew();
}

function cancelSchedule() {
    if (setupTask != null) {
        setupTask.cancel(true);
    }
}

function getMaxCheck(type) {
    switch (type) {
    case 0:
        return 321;//¥��
    case 1:
        return 350;//¥��
    case 2:
        return 400;//¥��
	case 3:
        return 450;//¥��
    case 4:
        return 500;//¥��
	case 5:
        return 550;//¥��
    case 6:
        return 600;//¥��
    }
    return 700;//¥��
}

function rand(lbound, ubound) {
    return Math.floor(Math.random() * (ubound - lbound)) + lbound;
}