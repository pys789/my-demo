package cn.pys.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @Description
 * @Date 2021/3/24 10:05
 * @Created by pengys
 */
public class User implements Observer {

    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable observable, Object data) {
        NewsModel model = (NewsModel) data;
        System.out.println(name + " receive news:" + model.getTitle() + "  " + model.getContent());
    }
}
