package com.tinysakura.core.query;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * QueryBody构造类单元测试
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */

public class QueryBodyTest {

    @Test
    public void QueryBodyTest() {
        QueryBody.Builder builder = new QueryBody.Builder();

        QueryBody queryBody = builder.fields("author").fields("title").from(1).size(10).minScore(0.75f)
                .version(true).include("partial1", new String[]{"titl*"}).exclude("partial1", new String[]{"char*"}).luceneQuery("title:Love Story")
                .build();

        System.out.println(JSON.toJSONString(queryBody.getQueryBody()));
    }
}