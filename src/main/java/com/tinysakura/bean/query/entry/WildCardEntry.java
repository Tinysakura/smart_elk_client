package com.tinysakura.bean.query.entry;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class WildCardEntry {
    /**
     * 包含通配符的查询语句
     */
    String value;

    Double boost;
}