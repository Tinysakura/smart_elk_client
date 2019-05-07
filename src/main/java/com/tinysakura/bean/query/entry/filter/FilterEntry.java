package com.tinysakura.bean.query.entry.filter;

import com.tinysakura.bean.query.entry.composite.RangeEntry;
import lombok.Data;

import java.util.Map;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/7
 */
@Data
public class FilterEntry {
    /**
     * 词条查询条件
     */
    Map<String, Object> term;

    /**
     * 范围查询条件
     */
    Map<String, RangeEntry> range;

    /**
     * 过滤指定字段为空的文档
     */
    FieldEntry exists;

    /**
     * 过滤指定字段有值的文档
     */
    FieldEntry missing;

    /**
     * 脚本过滤器节点
     */
    ScriptEntry script;

    /**
     * 文档类型过滤器节点
     */
    Map<String, String> type;

    /**
     * 限定单个分片返回文档数目
     */
    Map<String, Integer> limit;

    /**
     * 文档唯一标识符过滤器节点
     */
    IdsEntry ids;
}