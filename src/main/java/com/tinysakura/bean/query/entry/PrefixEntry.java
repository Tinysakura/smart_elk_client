package com.tinysakura.bean.query.entry;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class PrefixEntry {
    /**
     * 前缀值
     */
    String value;

    /**
     * 前缀权值
     */
    Double boost;
}