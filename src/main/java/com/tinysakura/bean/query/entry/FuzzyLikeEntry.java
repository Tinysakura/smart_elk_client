package com.tinysakura.bean.query.entry;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class FuzzyLikeEntry {
    /**
     * 匹配内容
     */
    private String like_text;

    /**
     * 模糊匹配字段
     */
    private String[] fields;

    /**
     * 是否忽略词频
     */
    private Boolean ignore_if;

    /**
     * 生成的差分词条的最小相似度[0 - 1]
     */
    private Double min_similarity;

    /**
     * 生成的差分词条的公共前缀长度
     */
    private Integer prefix_length;

    private Double boost;

    private String analyzer;
}