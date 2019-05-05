package com.tinysakura.net.retrofit.service;

import com.google.gson.Gson;
import com.tinysakura.bean.query.result.QueryResponse;
import com.tinysakura.bean.test.Music;
import com.tinysakura.constant.QueryConstant;
import com.tinysakura.core.query.QueryBody;
import com.tinysakura.net.client.RetrofitProxyServiceHolder;
import io.reactivex.functions.Consumer;
import org.junit.Test;

/**
 * QueryService 测试类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */

public class QueryServiceTest {

    @Test
    public void queryServiceTest() {
        QueryService queryService = RetrofitProxyServiceHolder.getInstance().getQueryServiceProxy();

        QueryBody.Builder builder = new QueryBody.Builder();

        final QueryBody queryBody = builder.fields("author").fields("title").from(0).size(10)
                .version(true).luceneQuery("title:Love Story").searchType(QueryConstant.SearchType.QUERY_AND_FETCH)
                .build();

        Gson gson = new Gson();
        System.out.println(gson.toJson(queryBody.getQueryBody()));

        queryService.search("media", queryBody.getQueryBody(), null, Music.class).subscribe(new Consumer<QueryResponse>() {
            public void accept(QueryResponse queryResponse) throws Exception {
                System.out.println(queryResponse);
            }
        });
    }
}