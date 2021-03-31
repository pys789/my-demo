package cn.pys.multithread;

/**
 * @Description 虚假等待示例
 * 当一个条件满足时，很多线程都被唤醒了，但是只有其中部分是有用的唤醒，其它的唤醒都是无用功
 * 1.比如说买货，如果商品本来没有货物，突然进了一件商品，这是所有的线程都被唤醒了
 * 	，但是只能一个人买，所以其他人都是假唤醒，获取不到对象的锁
 * @Date 2021/3/31 15:19
 * @Created by pengys
 */
public class FalseAwakenDemo {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producter producter = new Producter(clerk);
        Customer customer = new Customer(clerk);

        new Thread(producter, "生产者A").start();
        new Thread(customer, "消费者A").start();
        new Thread(producter, "生产者B").start();
        new Thread(customer, "消费者B").start();
    }
}

// 售货员
class Clerk {
    private int product = 0;

    // 进货
    public synchronized void add() {
        /**
         * 把  while (product >=1) {} 和 while (product <= 0) {}
         * 换成   if (product >=1) {} 和 if (product <= 0) {}
         * 就会出现虚假唤醒
         */

        /**
         * 因为if只会执行一次，执行完会接着向下执行if（）外边的
         * 而while不会，直到条件满足才会向下执行while（）外边的
         */

        // 产品已满
        while (product >= 1) {
            System.out.println(Thread.currentThread().getName() + ": " + "已满！");
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        ++product;
        // 该线程从while中出来的时候，是满足条件的
        System.out.println(Thread.currentThread().getName() + ": " + "....................进货成功，剩下" + product);
        this.notifyAll();
    }

    // 卖货
    public synchronized void sale() {
        while (product <= 0) {
            System.out.println(Thread.currentThread().getName() + ": " + "没有买到货");
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        --product;
        System.out.println(Thread.currentThread().getName() + ":买到了货物，剩下 " + product);
        this.notifyAll();
    }
}

// 生产者
class Producter implements Runnable {
    private Clerk clerk;

    public Producter(Clerk clerk) {
        this.clerk = clerk;
    }

    // 进货
    @Override
    public void run() {
        for (int i = 0; i < 20; ++i) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            clerk.add();
        }
    }


}

// 消费者
class Customer implements Runnable {
    private Clerk clerk;

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }

    // 买货
    @Override
    public void run() {
        for (int i = 0; i < 20; ++i) {
            clerk.sale();
        }
    }
}
