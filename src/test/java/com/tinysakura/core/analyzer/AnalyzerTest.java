package com.tinysakura.core.analyzer;

import com.alibaba.fastjson.JSON;
import com.tinysakura.core.index.Index;
import org.junit.Test;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */

public class AnalyzerTest {
    /**
     * 测试自定义分析器构造器
     */
    @Test
    public void AnalyzerTest() {
        Analyzer.Builder builder = new Analyzer.Builder();

        Analyzer analyzer = builder.name("en").tokenizer("standard").filter("lowercase").filter("asciifolding").build();

        Index media = new Index.Builder().indexName("arts").shardsNumber(4).analysis(analyzer).build();

        System.out.println(JSON.toJSONString(media));
    }
}