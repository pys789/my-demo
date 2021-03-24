package cn.pys.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @Description
 * @Date 2021/3/24 9:54
 * @Created by pengys
 */
public class NewsModel {

    private String title;
    private String content;

    public NewsModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
