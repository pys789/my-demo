package cn.pys.jvm;

public class JstatDemo {
    public static void main(String[] args) throws Exception {
        /**
         *
         * -Xmx200m -Xms200m -Xmn100m
         * -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=30m
         * -XX:MaxTenuringThreshold=15
         * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
         * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:JstatDemo.log
         *
         */
        Thread.sleep(30000);
        while (true) {
            loadData();
        }

    }

    private static void loadData() throws Exception {
        byte[] data = null;
        for (int i = 0; i < 50; i++) {
            data = new byte[100 * 1024];
        }
        data = null;
        Thread.sleep(1000);
    }
}
