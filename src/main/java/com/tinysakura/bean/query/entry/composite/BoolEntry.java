package com.tinysakura.bean.query.entry.composite;

import lombok.Data;

import java.util.Map;

/**
 * bool节点下should, must, must_not三个节点对应的bean
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class BoolEntry {
    /**
     * 词条查询条件
     */
    Map<String, Object> term;

    /**
     * 范围查询条件
     */
    Map<String, RangeEntry> range;
}