package cn.pys.jvm;

public class OldJvm {
    public static void main(String[] args) {
        /**
         * -Xmx20m -Xms20m -Xmn10m
         * -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=10m
         *  -XX:MaxTenuringThreshold=15
         * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
         * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:OldJvm.log
         */
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[128 * 1024];
        array2=null;

        byte[] array3 = new byte[2 * 1024 * 1024];

    }
}
