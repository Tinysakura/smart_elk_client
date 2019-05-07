package com.tinysakura.bean.query.entry.composite;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class RangeEntry {
    /**
     * 范围开始
     */
    private Object from;

    /**
     * 范围结束
     */
    private Object to;
}