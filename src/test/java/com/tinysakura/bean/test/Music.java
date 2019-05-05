package com.tinysakura.bean.test;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */
public class Music {
    private String author;

    private String title;

    public Music() {
    }

    public Music(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}