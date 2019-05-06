package com.tinysakura.bean.query.entry;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class CommonEntry {
    /**
     * 查询内容，会被分析器分析
     */
    String query;

    /**
     * 构建高低频词组，例如当该值为0.001时意味着频率<=0.1%的词会被划为低频次
     */
    Double cutoff_frequency;

    /**
     * 指定为低频词构建查询时用到的布尔运算符{@link com.tinysakura.constant.QueryConstant.Bool}
     */
    String low_freq_operator;

    /**
     * 指定为高频词构建查询时用到的布尔运算符{@link com.tinysakura.constant.QueryConstant.Bool}
     */
    String high_freq_operator;

    /**
     * 最小匹配数量
     */
    Integer minimumMatch;

    /**
     * 文档得分加权
     */
    Double boost;

    /**
     * 指定分析查询文本的分析器名称
     */
    String analyzer;
}