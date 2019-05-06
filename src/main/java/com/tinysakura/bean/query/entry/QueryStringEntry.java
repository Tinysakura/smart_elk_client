package com.tinysakura.bean.query.entry;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class QueryStringEntry {
    /**
     * 查询条件
     */
    String query;

    /**
     * 默认查询字段（由index.query.default.field指定）
     */
    String default_field;

    /**
     * 默认逻辑运算符{@link com.tinysakura.constant.QueryConstant.Bool}
     */
    String default_operator;

    /**
     * 是否允许通配符作为词条的第一个字符
     */
    Boolean allow_leading_wildcard;

    /**
     * 指定查询重写是否把词条转换为小写
     */
    Boolean lowercase_expand_terms;

    /**
     * 使用模糊查询时可被扩展到的最大词条数
     */
    Integer fuzzy_max_expansions;

    /**
     * 指定模糊查询中的前缀长度
     */
    Integer fuzzy_prefix_length;

    /**
     * 指定模糊查询的最小相似度[0 - 1]
     */
    Double fuzzy_min_sim;

    Double boost;

    /**
     * 是否分析通配符查询产生的词条
     */
    Boolean analyzer_wildcard;

    /**
     * 此参数控制有多少生成的Boolean should子句与文档匹配才返回结果，可以是[0 - 1]的百分比也可以是具体数量
     */
    Double minimum_should_match;
}