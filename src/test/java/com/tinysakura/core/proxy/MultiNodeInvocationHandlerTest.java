package com.tinysakura.core.proxy;

import com.tinysakura.bean.base.Acknowledged;
import com.tinysakura.net.retrofit.service.IndexService;
import com.tinysakura.net.retrofit.service.impl.IndexServiceImpl;
import io.reactivex.functions.Consumer;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * MultiNodeInvocationHandler单元测试类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */
public class MultiNodeInvocationHandlerTest {

    @Test
    public void multiNodeInvocationHandlerTest() {
        IndexServiceImpl proxy = new IndexServiceImpl();

        InvocationHandler multiNodeInvocationHandler = new MultiNodeInvocationHandler<IndexService>(proxy);

        IndexService indexServiceProxy = (IndexService) Proxy.newProxyInstance(IndexService.class.getClassLoader(), new Class[]{IndexService.class}, multiNodeInvocationHandler);

        indexServiceProxy.createIndex("java").subscribe(new Consumer<Acknowledged>() {
            public void accept(Acknowledged acknowledged) throws Exception {
                System.out.println(acknowledged.isAcknowledged());
            }
        });
    }
}