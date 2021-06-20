package cn.pys.Test.thread;

import java.util.ArrayList;
import java.util.List;

public class ProductConsumTest {
    private final List<Integer> queue = new ArrayList<>();
    private int count;

    private void product(Integer i) throws InterruptedException {
        synchronized (queue){
            if (count >= 5) {
                System.out.println("容器满了，等待消费");
                queue.wait();
            }
            System.out.println("当前元素个数：" + count);
            System.out.println("生产元素：" + i);
            queue.add(i);
            count++;
            queue.notify();
        }


    }

    private void consume() throws InterruptedException {
        synchronized (queue){
            if (count == 0) {
                System.out.println("容器空了，等待生产");
                queue.wait();
            }
            Integer item = queue.get(0);
            System.out.println("消费元素:" + item);
            queue.remove(item);
            count--;
            queue.notify();
        }

    }


    public static void main(String[] args) {
        ProductConsumTest test = new ProductConsumTest();
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
