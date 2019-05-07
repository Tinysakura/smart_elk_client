package com.tinysakura.net.retrofit.service.impl;

import com.tinysakura.bean.base.Acknowledged;
import com.tinysakura.bean.index.Index;
import com.tinysakura.net.retrofit.service.IndexService;
import io.reactivex.Observable;

import java.util.Map;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */

public class IndexServiceImpl implements IndexService {
    @Override
    public Observable<Acknowledged> createIndex(String indexName, Index index) {
        return null;
    }

    @Override
    public Observable<Acknowledged> createIndex(String indexName) {
        return null;
    }

    @Override
    public Observable<Map<String, Index>> queryIndex(String indexName) {
        return null;
    }
}