package cn.pys.thread.WaitNotifyTest;

public class ThreadNotify {

    private Object lock;

    public ThreadNotify(Object lock) {
        this.lock = lock;
    }

    public void testNotify() {
        try {
            synchronized (lock) {
                System.out.println("start notify........");
                lock.notify();
                // 调用notify只是唤醒对象锁的wait方法，调用wait()方法处的代码还不能获取锁
                // 直到当前代码执行完毕，例如睡眠3秒后执行完毕，
                // 调用wait()方法处的代码才能去获取对象锁继续执行
                Thread.sleep(3000);
                System.out.println("end notify........");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}