package com.tinysakura.net.retrofit.service.impl;

import com.google.gson.JsonObject;
import com.tinysakura.bean.base.Acknowledged;
import com.tinysakura.net.retrofit.service.DocumentService;
import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */

public class DocumentServiceImpl implements DocumentService {
    @Override
    public Observable<Acknowledged> postDocument(String index, String documentType, String documentId, Object document) {
        return null;
    }

    @Override
    public Observable<Acknowledged> postDocument(String index, String documentType, Object document) {
        return null;
    }

    @Override
    public Observable<JsonObject> batchPostDocument(RequestBody requestBody) {
        return null;
    }

    @Override
    public Observable<Acknowledged> postDocument(String index, String documentType, String documentId, Object document, String routing) {
        return null;
    }

    @Override
    public Observable<Acknowledged> postDocument(String index, String documentType, Object document, String routing) {
        return null;
    }
}