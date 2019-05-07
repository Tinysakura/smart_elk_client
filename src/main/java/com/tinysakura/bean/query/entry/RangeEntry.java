package com.tinysakura.bean.query.entry;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class RangeEntry {
    /**
     * 大于等于
     */
    private Object gte;

    /**
     * 大于
     */
    private Object gt;

    /**
     * 小于等于
     */
    private Object lte;

    /**
     * 小于
     */
    private Object lt;
}