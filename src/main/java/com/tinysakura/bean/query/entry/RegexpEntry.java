package com.tinysakura.bean.query.entry;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class RegexpEntry {
    /**
     * 正则表达式
     */
    private String value;

    private Double boost;
}