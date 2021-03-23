package cn.pys.jvm;

public class NewJvm {
    public static void main(String[] args) {
        /**
         * -Xmx11m -Xms11m -Xmn6m
         * -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=10m
         * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
         * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:NewJvm.log
         */
        byte[] array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];
    }
}
