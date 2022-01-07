package cn.pys.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new readNum(i, countDownLatch)).start();
        }
        // countDownLatch做减法，调用countDown时减一，达到0时执行主线程await后面的代码
        countDownLatch.await();
        // 等其他线程执行完再执行主线程
        System.out.println("主线程组执行结束");
    }

    static class readNum implements Runnable {
        private int id;
        private CountDownLatch latch;

        readNum(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            synchronized (this) {
                System.out.println("id:" + id);
                latch.countDown();
                System.out.println("线程组任务" + id + "结束，其他任务继续");
            }
        }
    }
}
