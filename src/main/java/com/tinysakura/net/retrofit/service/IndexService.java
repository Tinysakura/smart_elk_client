package com.tinysakura.net.retrofit.service;

import com.tinysakura.bean.base.Acknowledged;
import com.tinysakura.bean.index.Index;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;


/**
 * 索引相关操作的Rest Api
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */
public interface IndexService {
    @PUT("/{index}/")
    Observable<Acknowledged> createIndex(@Path("index") String indexName, @Body Index index);

    @PUT("/{index}/")
    Observable<Acknowledged> createIndex(@Path("index") String indexName);
}