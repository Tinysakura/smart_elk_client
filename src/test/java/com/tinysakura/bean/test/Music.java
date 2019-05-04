package com.tinysakura.bean.test;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */
@Data
public class Music {
    private String author;

    private String title;

    public Music() {
    }

    public Music(String author, String title) {
        this.author = author;
        this.title = title;
    }
}