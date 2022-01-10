package cn.pys.thread;

/**
 * interrupt（）是给线程设置中断标志；
 * interrupted（）是检测中断并清除中断状态；
 * isInterrupted（）只检测中断。
 * 还有重要的一点就是interrupted（）作用于当前线程，
 * interrupt（）和isInterrupted（）作用于此线程，即代码中调用此方法的实例所代表的线程
 *
 * @Author: pengys
 * @Description:
 */
public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread=new MyThread();
        thread.start();

        System.out.println("第一次调用thread.isInterrupted()："+thread.isInterrupted());
        Thread.sleep(1);
        thread.interrupt();
        System.out.println("第二次调用thread.isInterrupted()："+thread.isInterrupted());
        //测试interrupted（）函数
        System.out.println("thread是否存活："+thread.isAlive());
    }
}

class MyThread extends Thread {
    @Override
    public  void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i="+(i+1));
            if(this.isInterrupted()){
                System.out.println("通过this.isInterrupted()检测到中断");
                System.out.println("第一个interrupted()"+this.interrupted());
                System.out.println("第二个interrupted()"+this.interrupted());
                break;
            }
        }
        System.out.println("因为检测到中断，所以跳出循环，线程到这里结束，因为后面没有内容了");
    }
}