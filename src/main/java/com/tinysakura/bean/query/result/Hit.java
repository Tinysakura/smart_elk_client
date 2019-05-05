package com.tinysakura.bean.query.result;

import lombok.Data;

/**
 * 返回的单个文档的结果
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class Hit<T> {
    /**
     * 索引
     */
    String _index;

    /**
     * 文档类型
     */
    String _type;

    /**
     * 文档唯一标识符
     */
    String _id;

    /**
     * 文档得分
     */
    Float _score;

    /**
     * 结果文档
     */
    T _source;
}