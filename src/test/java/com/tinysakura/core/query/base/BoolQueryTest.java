package com.tinysakura.core.query.base;

import com.google.gson.Gson;
import org.junit.Test;

/**
 * BoolQuery单元测试类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */

public class BoolQueryTest {

    @Test
    public void boolQueryTest() {
        BoolQuery.Builder builder = new BoolQuery.Builder();

        BoolQuery boolQuery = builder.must().field("author").value("taylor swift").should().field("year").range(1998, 2019).build();

        System.out.println(new Gson().toJson(boolQuery.getQuery()));
    }
}