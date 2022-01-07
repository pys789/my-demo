package cn.pys.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProductConsumTest2 {
    private List<Integer> queue = new ArrayList<>();
    private int count;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void product(Integer i) throws InterruptedException {
        try {
            lock.lockInterruptibly();
            if (count >= 5) {
                System.out.println("容器满了，等待消费");
                condition.await();
            }
            System.out.println("当前元素个数：" + count);
            System.out.println("生产元素：" + i);
            queue.add(i);
            count++;
            condition.signal();
        } finally {
            lock.unlock();
        }

    }

    private void consume() throws InterruptedException {
        try {
            lock.lockInterruptibly();
            if (count == 0) {
                System.out.println("容器空了，等待生产");
                condition.await();
            }
            Integer item = queue.get(0);
            System.out.println("消费元素:" + item);
            queue.remove(item);
            count--;
            condition.signal();
        } finally {
            lock.unlock();
        }

    }


    public static void main(String[] args) {
        ProductConsumTest2 test = new ProductConsumTest2();
        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                try {
                    test.product(i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                try {
                    test.consume();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
