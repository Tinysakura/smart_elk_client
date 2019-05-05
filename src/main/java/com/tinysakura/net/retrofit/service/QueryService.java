package com.tinysakura.net.retrofit.service;

import com.tinysakura.bean.query.QueryBody;
import com.tinysakura.bean.query.result.QueryResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;


/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */

public interface QueryService {
    /**
     * 查询索引中的文档，返回详细结果<QueryResponse>
     *
     * @param index
     * @param documentType
     * @param queryBody
     * @return
     */
    // @HTTP(method = "GET", path = "/{index}/{documentType}/_search/", hasBody = true)
    @POST("/{index}/{documentType}/_search/")
    Observable<QueryResponse> search(@Path("index") String index, @Path("documentType") String documentType, @Body QueryBody queryBody);


    /**
     * 查询索引中的文档，只返回文档结果
     * @param index
     * @param documentType
     * @param queryBody
     * @return
     */
    @HTTP(method = "GET", path = "/{index}/{documentType}/_search/", hasBody = true)
    Observable<List<Object>> simpleSearch(@Path("index") String index, @Path("documentType") String documentType, @Body QueryBody queryBody);
}