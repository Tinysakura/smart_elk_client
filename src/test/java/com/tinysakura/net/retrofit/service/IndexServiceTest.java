package com.tinysakura.net.retrofit.service;

import com.tinysakura.bean.index.Index;
import com.tinysakura.net.client.RetrofitProxyServiceHolder;
import io.reactivex.functions.Consumer;
import org.junit.Test;

import java.util.Map;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */

public class IndexServiceTest {

    @Test
    public void queryIndexTest() {
        IndexService indexService = RetrofitProxyServiceHolder.getInstance().getIndexServiceProxy();

        indexService.queryIndex("mediasss").subscribe(new Consumer<Map<String, Index>>() {
            public void accept(Map<String, Index> index) throws Exception {
                System.out.println(index);
            }
        });
    }
}