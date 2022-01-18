package abc.yunxing;

public class ThreadState implements Runnable {
    public synchronized void waitForASecond() throws InterruptedException {
        wait(500); // ʹ��ǰ�̵߳ȴ�0.5��������̵߳���notify()��notifyAll()����
    }
    
    public synchronized void waitForYears() throws InterruptedException {
        wait(); // ʹ��ǰ�߳����õȴ���ֱ�������̵߳���notify()��notifyAll()����
    }
    
    public synchronized void notifyNow() throws InterruptedException {
        notify(); // �����ɵ���wait()��������ȴ�״̬���߳�
    }
    
    public void run() {
        try {
            waitForASecond(); // �����߳�������waitForASecond()����
            waitForYears(); // �����߳�������waitForYears()����
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
