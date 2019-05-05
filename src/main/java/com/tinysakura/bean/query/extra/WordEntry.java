package com.tinysakura.bean.query.extra;

import lombok.Data;

/**
 * 词条bean
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class WordEntry {
    /**
     * 词条内容
     */
    private Object value;

    /**
     * 权值
     */
    private double boost;
}