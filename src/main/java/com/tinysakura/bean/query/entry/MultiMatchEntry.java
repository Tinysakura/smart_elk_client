package com.tinysakura.bean.query.entry;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class MultiMatchEntry {
    private String query;

    /**
     * 所有查询字段
     */
    String[] fields;

    /**
     * 布尔运算符{@link com.tinysakura.constant.QueryConstant.Bool}
     */
    String operator;

    String analyzer;

    /**
     * 使用该字段开启模糊查询，区间为0-1
     */
    Double fuzziness;

    /**
     * 指定当所有词条都被移除后的查询行为{@link com.tinysakura.constant.QueryConstant.SearchBehavior}
     * none : 不返回任何文档
     * all : 返回所有文档
     */
    String zero_terms_query;

    /**
     * 构建高低频词组，例如当该值为0.001时意味着频率<=0.1%的词会被划为低频次
     */
    Double cutoff_frequency;
}