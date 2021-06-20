package cn.pys.Test.thread;

public class PrintNum {

    public static void main(String[] args) {
        PrintTask t = new PrintTask();
        new Thread(t,"线程-1").start();
        new Thread(t,"线程-2").start();
    }
}

class PrintTask implements Runnable {
    int i = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notifyAll();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    i++;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        }


    }
}
