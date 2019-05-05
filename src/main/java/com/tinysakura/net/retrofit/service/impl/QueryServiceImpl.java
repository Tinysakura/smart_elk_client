package com.tinysakura.net.retrofit.service.impl;

import com.tinysakura.bean.query.QueryBody;
import com.tinysakura.bean.query.result.QueryResponse;
import com.tinysakura.net.retrofit.service.QueryService;
import io.reactivex.Observable;

import java.util.List;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */

public class QueryServiceImpl implements QueryService {

    public Observable<QueryResponse> search(String index, String documentType, QueryBody queryBody) {
        return null;
    }

    public Observable<List<Object>> simpleSearch(String index, String documentType, QueryBody queryBody) {
        return null;
    }
}