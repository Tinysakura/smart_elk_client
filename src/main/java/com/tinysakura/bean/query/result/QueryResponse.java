package com.tinysakura.bean.query.result;

import lombok.Data;

import java.util.List;

/**
 * 查询结果bean
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class QueryResponse {
    /**
     * 花费的时间
     */
    Long took;

    /**
     * 是否超时
     */
    boolean time_out;

    /**
     * 分片信息
     */
    Shards _shards;

    /**
     * 文档信息
     */
    Hits hits;

    List results;
}