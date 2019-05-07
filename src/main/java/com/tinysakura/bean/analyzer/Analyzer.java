package com.tinysakura.bean.analyzer;

import lombok.Data;

/**
 * 自定义分析器bean，每个分析器由一个分词器和多个过滤器组成
 *
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */
@Data
public class Analyzer {
    /**
     * 分词器名称
     */
    String tokenizer;

    /**
     * 过滤器名称
     */
    String[] filter;
}