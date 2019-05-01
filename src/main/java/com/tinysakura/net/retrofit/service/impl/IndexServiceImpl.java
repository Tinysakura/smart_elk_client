package com.tinysakura.net.retrofit.service.impl;

import com.tinysakura.bean.base.Acknowledged;
import com.tinysakura.bean.index.Index;
import com.tinysakura.net.retrofit.service.IndexService;
import io.reactivex.Observable;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */

public class IndexServiceImpl implements IndexService {

    public Observable<Acknowledged> createIndex(String indexName, Index index) {
        return null;
    }

    public Observable<Acknowledged> createIndex(String indexName) {
        return null;
    }
}