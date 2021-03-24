package cn.pys.design.observer;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description 用户订阅新闻报纸，观察者：用户，被观察者：报社
 * @Date 2021/3/24 9:48
 * @Created by pengys
 */
public class NewsProvider extends Observable {
    private static final long DELAY = 2 * 1000;

    public NewsProvider() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            private int contentCount = 1;

            @Override
            public void run() {
                int titleCount = 1;
                int contentCount = 1;
                //调用setChagned方法，将changed域设置为true，这样才能通知到观察者们
                setChanged();
                notifyObservers(new NewsModel("title:" + titleCount++, "content:" + contentCount++));
            }
        }, DELAY, 1000);
    }

    public static void main(String[] args) {
        NewsProvider newsProvider = new NewsProvider();
        for (int i = 0; i < 10; i++) {
            User user = new User("user:" + i);
            newsProvider.addObserver(user);
        }
    }
}
