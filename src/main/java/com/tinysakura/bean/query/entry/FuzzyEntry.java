package com.tinysakura.bean.query.entry;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class FuzzyEntry {

    private String value;

    private Double boost;

    /**
     * 最小相似度
     */
    private Double min_similarity;

    /**
     * 公共前缀长度
     */
    private Integer prefix_length;
}