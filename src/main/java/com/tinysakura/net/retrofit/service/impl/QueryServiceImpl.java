package com.tinysakura.net.retrofit.service.impl;

import com.tinysakura.bean.query.QueryBody;
import com.tinysakura.bean.query.result.QueryResponse;
import com.tinysakura.net.retrofit.service.QueryService;
import io.reactivex.Observable;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */

public class QueryServiceImpl implements QueryService {
    @Override
    public Observable<QueryResponse> search(String index, String documentType, QueryBody queryBody, String searchType) {
        return null;
    }

    @Override
    public Observable<QueryResponse> search(String index, QueryBody queryBody, String searchType) {
        return null;
    }

    @Override
    public Observable<QueryResponse> search(String index, String documentType, QueryBody queryBody, String searchType, Class clazz) {
        return null;
    }

    @Override
    public Observable<QueryResponse> search(String index, QueryBody queryBody, String searchType, Class clazz) {
        return null;
    }

    @Override
    public Observable<QueryResponse> search(String index, String documentType, QueryBody queryBody) {
        return null;
    }

    @Override
    public Observable<QueryResponse> search(String index, QueryBody queryBody) {
        return null;
    }

    @Override
    public Observable<QueryResponse> search(String index, String documentType, QueryBody queryBody, Class clazz) {
        return null;
    }

    @Override
    public Observable<QueryResponse> search(String index, QueryBody queryBody, Class clazz) {
        return null;
    }
}