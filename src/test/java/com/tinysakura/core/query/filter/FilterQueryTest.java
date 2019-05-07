package com.tinysakura.core.query.filter;

import com.google.gson.Gson;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * FilterQuery单元测试类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/7
 */

public class FilterQueryTest {

    @Test
    public void filterQueryTest() {
        FilterQuery.Builder builder = new FilterQuery.Builder();
        Map<String, Object> params = new HashMap<>();
        params.put("now", 2019);

        FilterQuery filterQuery = builder.script("now - doc['year'].value > 100").scriptParams(params).build();

        System.out.println(new Gson().toJson(filterQuery.getFilter()));
    }
}