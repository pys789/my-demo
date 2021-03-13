package cn.pys.jvm;

public class JstatOldDemo {
    public static void main(String[] args) throws Exception {
        /**
         *
         * -Xmx200m -Xms200m -Xmn100m
         * -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=20m
         * -XX:MaxTenuringThreshold=15
         * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
         * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:JstatOldDemo.log
         *
         */

        // 将堆大小调整成300m，s区比值调成2，这样ygc之后，s区可以放入存活对象，
        // 从而不会进入老年代，减少fgc的频率
        /**
         *
         * -Xmx300m -Xms300m -Xmn200m
         * -XX:SurvivorRatio=2 -XX:PretenureSizeThreshold=20m
         * -XX:MaxTenuringThreshold=15
         * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
         * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:JstatOldDemo.log
         *
         */
        Thread.sleep(30000);
        while (true) {
            loadData();
        }

    }

    private static void loadData() throws Exception {
        byte[] data = null;
        for (int i = 0; i < 4; i++) {
            data = new byte[10 * 1024 * 1024];
        }
        data = null;

        /**
         * 模拟每次ygc后有30m的内存存活
         */
        byte[] data1 = new byte[10 * 1024 * 1024];
        byte[] data2 = new byte[10 * 1024 * 1024];
        byte[] data3 = new byte[10 * 1024 * 1024];

        // 此时触发ygc
        data3 = new byte[10 * 1024 * 1024];
        Thread.sleep(1000);
    }
}
