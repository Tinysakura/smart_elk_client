package com.tinysakura.bean.query.result;

import lombok.Data;

/**
 * 返回的文档集合的聚合结果bean
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class Hits {
    /**
     * 返回的文档数
     */
    Integer total;

    /**
     * 返回的文档中最高的得分
     */
    Float max_score;

    /**
     * 文档集合
     */
    Hit[] hits;
}