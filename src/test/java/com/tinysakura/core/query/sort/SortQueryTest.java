package com.tinysakura.core.query.sort;

import com.google.gson.Gson;
import com.tinysakura.constant.DocumentPropertiesConstant;
import com.tinysakura.constant.QueryConstant;
import org.junit.Test;

/**
 * SortQuery单元测试类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/7
 */

public class SortQueryTest {

    @Test
    public void fieldSortQueryTest() {
        SortQuery.Builder builder = new SortQuery.Builder();
        SortQuery sortQuery = builder.field("author").asc(QueryConstant.FieldMissingBehavior.LAST).field("title").desc(QueryConstant.FieldMissingBehavior.FIRST).build();

        System.out.println(new Gson().toJson(sortQuery.getFieldSort()));
    }

    @Test
    public void scriptSortQueryTest() {
        SortQuery.Builder builder = new SortQuery.Builder();
        SortQuery sortQuery = builder.script("xxx", QueryConstant.Order.DESC, DocumentPropertiesConstant.Type.TEXT).build();

        System.out.println(new Gson().toJson(sortQuery.getScriptSort()));
    }
}