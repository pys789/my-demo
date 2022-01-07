package cn.pys.thread;

public class ThreadLocalTest {

    // 使用 ThreadLocal，子线程拿不到父线程设置的值
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    //public static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<Integer>();

    public static void main(String args[]) throws InterruptedException {
        threadLocal.set(123);

        Thread thread = new MyThread();
        thread.start();
        thread.join();

        System.out.println("main = " + threadLocal.get());
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread1 = " + threadLocal.get());
            threadLocal.set(456);
            System.out.println("MyThread2 = " + threadLocal.get());
        }
    }
}