package cn.pys.jvm;

import java.util.ArrayList;
import java.util.List;

public class DumpDemo {
    public static void main(String[] args) throws InterruptedException {
        List<Data> dataList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            dataList.add(new Data());
        }
        Thread.sleep(1 * 60 * 60 * 1000);
    }

    static class Data {
    }
}
