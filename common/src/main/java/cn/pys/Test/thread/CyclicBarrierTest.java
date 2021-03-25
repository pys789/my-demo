package cn.pys.Test.thread;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
        // Barrier 做加法，await达到5次时执行下面的方法，其他线程继续执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> System.out.println("线程组执行结束"));
        for (int i = 0; i < 5; i++) {
            new Thread(new readNum(i, cyclicBarrier)).start();
        }
        // 不影响主线程的执行
        System.out.println("主线程组执行结束");

        //CyclicBarrier 可以重复利用，这个是CountDownLatch做不到的
        /*for (int i = 10; i < 15; i++) {
            new Thread(new readNum(i, cyclicBarrier)).start();
        }*/
    }

    static class readNum implements Runnable {
        Random random = new Random();
        private int id;
        private CyclicBarrier cyc;

        readNum(int id, CyclicBarrier cyc) {
            this.id = id;
            this.cyc = cyc;
        }

        @Override
        public void run() {
            synchronized (this) {
                System.out.println("id:" + id);
                int randomNum = random.nextInt(2000) + 1000;
                try {
                    cyc.await();
                    Thread.sleep(randomNum);
                    System.out.println("线程组任务1:" + id + "结束，其他任务继续");
                    cyc.await();
                    Thread.sleep(randomNum);
                    System.out.println("线程组任务2:" + id + "结束，其他任务继续");
                    cyc.await();
                    Thread.sleep(randomNum);
                    System.out.println("线程组任务3:" + id + "结束，其他任务继续");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}