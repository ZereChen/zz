package abc.yunxing;


public class Multithread {
    public static void main(String[] args) throws InterruptedException {
        ThreadState state = new ThreadState();// ����State����
        Thread thread = new Thread(state);// ����State���󴴽�Thread����
        System.out.println("�½��̣߳�" + thread.getState());// ����߳�״̬
        thread.start(); // ����thread�����start()�������������߳�
        System.out.println("�����̣߳�" + thread.getState());// ����߳�״̬
        Thread.sleep(100); // ��ǰ�߳�����0.1�룬ʹ���߳�����waitForASecond()����
        System.out.println("��ʱ�ȴ���" + thread.getState());// ����߳�״̬
        Thread.sleep(1000); // ��ǰ�߳�����1�룬ʹ���߳�����waitForYears()����
        System.out.println("�ȴ��̣߳�" + thread.getState());// ����߳�״̬
        state.notifyNow(); // ����state��notifyNow()����
        System.out.println("�����̣߳�" + thread.getState());// ����߳�״̬
        Thread.sleep(1000); // ��ǰ�߳�����1�룬ʹ���߳̽���
        System.out.println("��ֹ�̣߳�" + thread.getState());// ����߳�״̬
    }
}
