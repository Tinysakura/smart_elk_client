package com.tinysakura.net.client;

import com.tinysakura.bean.base.Acknowledged;
import com.tinysakura.net.retrofit.service.IndexService;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.List;

/**
 * RetrofitServiceHolder 单元测试类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */
public class RetrofitServiceHolderTest {

    @Test
    public void retrofitServiceHolderTest() {
        List<Object> indexServices = RetrofitServiceHolder.getInstance().getIndexService();

        System.out.println(createIndex(indexServices, "monkey", 0, new Acknowledged[1]).isAcknowledged());
    }


    private Acknowledged createIndex(final List<Object> indexServices, final String indexName, final int node, final Acknowledged[] result) {
        ((IndexService) indexServices.get(node)).createIndex(indexName).subscribeOn(Schedulers.io()).subscribe(new Observer<Acknowledged>() {
            public void onSubscribe(Disposable disposable) {

            }

            public void onNext(Acknowledged acknowledged) {
                result[0] = acknowledged;
            }

            public void onError(Throwable throwable) {
                if (node == indexServices.size() - 1) {
                    return;
                }

                int nextNode = node + 1;
                createIndex(indexServices, indexName, nextNode, result);
            }

            public void onComplete() {

            }
        });

        return result[0];
    }
}