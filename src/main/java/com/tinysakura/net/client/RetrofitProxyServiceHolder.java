package com.tinysakura.net.client;

import com.tinysakura.core.proxy.MultiNodeInvocationHandler;
import com.tinysakura.net.retrofit.service.DocumentService;
import com.tinysakura.net.retrofit.service.IndexService;
import com.tinysakura.net.retrofit.service.QueryService;
import com.tinysakura.net.retrofit.service.impl.DocumentServiceImpl;
import com.tinysakura.net.retrofit.service.impl.IndexServiceImpl;
import com.tinysakura.net.retrofit.service.impl.QuerySerciceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 被动态代理的retrofit service持有类（单例模式）
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/2
 */

public class RetrofitProxyServiceHolder {

    private static RetrofitProxyServiceHolder retrofitProxyServiceHolder = null;

    private IndexService indexServiceProxy;

    private QueryService queryServiceProxy;

    private DocumentService documentServiceProxy;

    private RetrofitProxyServiceHolder() {
        initIndexServiceProxy();
        initQueryServiceProxy();
    }

    public static RetrofitProxyServiceHolder getInstance() {
        if (retrofitProxyServiceHolder == null) {
            synchronized (RetrofitProxyServiceHolder.class) {
                if (retrofitProxyServiceHolder == null) {
                    retrofitProxyServiceHolder = new RetrofitProxyServiceHolder();
                }
            }
        }

        return retrofitProxyServiceHolder;
    }

    private void initIndexServiceProxy() {
        IndexServiceImpl target = new IndexServiceImpl();

        InvocationHandler multiNodeInvocationHandler = new MultiNodeInvocationHandler<IndexService>(target
        );

        indexServiceProxy = (IndexService) Proxy.newProxyInstance(IndexService.class.getClassLoader(), new Class[]{IndexService.class}, multiNodeInvocationHandler);
    }

    private void initQueryServiceProxy() {
        QuerySerciceImpl target = new QuerySerciceImpl();

        InvocationHandler multiNodeInvocationHandler = new MultiNodeInvocationHandler<QueryService>(target);

        queryServiceProxy = (QueryService) Proxy.newProxyInstance(QueryService.class.getClassLoader(), new Class[]{QueryService.class}, multiNodeInvocationHandler);
    }

    private void initDocumentServiceProxy() {
        DocumentServiceImpl target = new DocumentServiceImpl();

        InvocationHandler multiNodeInvocationHandler = new MultiNodeInvocationHandler<DocumentService>(target);

        documentServiceProxy = (DocumentService) Proxy.newProxyInstance(DocumentService.class.getClassLoader(), new Class[]{DocumentService.class}, multiNodeInvocationHandler);
    }

    public IndexService getIndexServiceProxy() {
        return indexServiceProxy;
    }

    public QueryService getQueryServiceProxy() {
        return queryServiceProxy;
    }

    public DocumentService getDocumentServiceProxy() {
        return documentServiceProxy;
    }
}