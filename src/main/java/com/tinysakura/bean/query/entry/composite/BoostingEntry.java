package com.tinysakura.bean.query.entry.composite;

import lombok.Data;

import java.util.Map;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class BoostingEntry {
    /**
     * 词条查询条件
     */
    Map<String, Object> term;

    /**
     * 范围查询条件
     */
    Map<String, RangeEntry> range;
}