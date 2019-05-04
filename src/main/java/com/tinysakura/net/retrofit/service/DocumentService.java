package com.tinysakura.net.retrofit.service;

import com.google.gson.JsonObject;
import com.tinysakura.bean.base.Acknowledged;
import com.tinysakura.bean.document.bulk.BulkResult;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.*;

/**
 * 文档相关操作 Rest Api
 *
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */

public interface DocumentService {
    /**
     * 为文档建立索引
     * @param index        索引名
     * @param documentType 文档类型
     * @param documentId   文档唯一表示符
     * @param document     文档对象
     * @return
     */
    @POST("/{index}/{documentType}/{documentId}/")
    Observable<Acknowledged> postDocument(@Path("index") String index, @Path("documentType") String documentType, @Path("documentId") String documentId, @Body Object document);

    /**
     * 为文档建立索引（不指定唯一表示符<>使用文档内字段的值或自动生成</>）
     * @param index        索引名
     * @param documentType 文档类型
     * @param document     文档对象
     * @return
     */
    @POST("/{index}/{documentType}/{documentId}/")
    Observable<Acknowledged> postDocument(@Path("index") String index, @Path("documentType") String documentType, @Body Object document);

    /**
     * 为文档批量建立索引
     * @return
     */
    @POST("/_bulk")
    Observable<JsonObject> batchPostDocument(@Body RequestBody requestBody);

    /**
     * 在指定分片上建立索引
     * @param index
     * @param documentType
     * @param documentId
     * @param document
     * @param routing
     * @return
     */
    @POST("/{index}/{documentType}/{documentId}/")
    Observable<Acknowledged> postDocument(@Path("index") String index, @Path("documentType") String documentType, @Path("documentId") String documentId, @Body Object document, @Query("routing") String routing);

    /**
     * 在指定分片上建立索引
     * @param index
     * @param documentType
     * @param document
     * @param routing
     * @return
     */
    @POST("/{index}/{documentType}/{documentId}/")
    Observable<Acknowledged> postDocument(@Path("index") String index, @Path("documentType") String documentType, @Body Object document, @Query("routing") String routing);
}