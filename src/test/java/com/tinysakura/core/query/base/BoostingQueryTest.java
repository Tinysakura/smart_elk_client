package com.tinysakura.core.query.base;

import com.google.gson.Gson;
import com.tinysakura.core.query.composite.BoostingQuery;
import org.junit.Test;

/**
 * BoostingQuery单元测试类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */

public class BoostingQueryTest {

    @Test
    public void boostingQueryTest() {
        BoostingQuery.Builder builder = new BoostingQuery.Builder();

        BoostingQuery boostingQuery = builder.positive().field("author").value("taylor swift").negative().field("year").range(1998, 2019)
                .negativeBoost(0.5).build();

        System.out.println(new Gson().toJson(boostingQuery.getQuery()));
    }
}