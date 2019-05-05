package com.tinysakura.net.retrofit.service;

import com.tinysakura.bean.query.QueryBody;
import com.tinysakura.bean.query.result.QueryResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */

public interface QueryService {
    /**
     * 查询索引中的文档，返回详细结果<QueryResponse>
     * @param index
     * @param documentType
     * @param queryBody
     * @return
     */
    @POST("/{index}/{documentType}/_search/")
    Observable<QueryResponse> search(@Path("index") String index, @Path("documentType") String documentType, @Body QueryBody queryBody);

    /**
     * 查询索引中的文档，返回详细结果<QueryResponse>
     * @param index
     * @param queryBody
     * @return
     */
    @POST("/{index}/_search/")
    Observable<QueryResponse> search(@Path("index") String index, @Body QueryBody queryBody);

    /**
     * 查询索引中的文档，返回详细结果<QueryResponse>
     * @param index
     * @param documentType
     * @param queryBody
     * @param clazz 文档对应的bean类型，反序列化结果会存储在QueryResponse的results字段中
     * @return
     */
    @POST("/{index}/{documentType}/_search/")
    Observable<QueryResponse> search(@Path("index") String index, @Path("documentType") String documentType, @Body QueryBody queryBody, Class clazz);

    /**
     * 查询索引中的文档，返回详细结果<QueryResponse>
     * @param index
     * @param queryBody
     * @param clazz 文档对应的bean类型，反序列化结果会存储在QueryResponse的results字段中
     * @return
     */
    @POST("/{index}/_search/")
    Observable<QueryResponse> search(@Path("index") String index, @Body QueryBody queryBody, Class clazz);
}