package cn.pys.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNum2 {

    public static void main(String[] args) {
        PrintTask2 myThread=new PrintTask2();
        new Thread(()->{
            for (int i = 0; i <=100 ; i++) {
                myThread.increase();
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i <=100 ; i++) {
                myThread.increase();
            }
        }).start();

    }
}

class PrintTask2{
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int num = 1;

    void increase() {
        if (num <= 100) {
            try {
                lock.tryLock();
                condition.signal();
                System.out.println(Thread.currentThread().getName() + ":" + num++);
                condition.await();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
