package com.tinysakura.net.retrofit.service.impl;

import com.tinysakura.bean.base.Acknowledged;
import com.tinysakura.bean.document.bulk.BulkResult;
import com.tinysakura.net.retrofit.service.DocumentService;
import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */

public class DocumentServiceImpl implements DocumentService {
    public Observable<Acknowledged> postDocument(String index, String documentType, String documentId, Object document) {
        return null;
    }

    public Observable<Acknowledged> postDocument(String index, String documentType, Object document) {
        return null;
    }

    public Observable<BulkResult> batchPostDocument(MultipartBody.Part batchJsonFile) {
        return null;
    }
}