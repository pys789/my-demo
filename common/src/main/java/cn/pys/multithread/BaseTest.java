package cn.pys.multithread;

import com.sun.deploy.util.SyncAccess;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description
 * @Date 2021/3/30 14:53
 * @Created by pengys
 */
public class BaseTest {
    public static void main(String[] args) {
        System.out.println("begin park");

        LockSupport.unpark(Thread.currentThread());

        LockSupport.park();

        System.out.println("end park");
    }

}
