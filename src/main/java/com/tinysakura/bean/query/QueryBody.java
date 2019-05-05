package com.tinysakura.bean.query;

import lombok.Data;

import java.util.Map;

/**
 * elk查询请求体bean
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class QueryBody {
    /**
     * 查询条件
     */
    Query query;

    /**
     * 指定在结果返回的起始文档
     */
    Integer from;

    /**
     * 指定一次查询中返回的最大文档数
     */
    Integer size;

    /**
     * 是否返回版本值
     */
    Boolean version;

    /**
     * 限制返回文档结果的最小得分
     */
    Double min_score;

    /**
     * 指定在响应中包含的字段（只能返回存储在索引中的字段）
     */
    String[] fields;

    /**
     * 字段排除条件
     */
    Map<String, Partial> partial_fields;
}