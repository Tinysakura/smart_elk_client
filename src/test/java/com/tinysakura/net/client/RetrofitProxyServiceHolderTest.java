package com.tinysakura.net.client;

import com.google.gson.Gson;
import com.tinysakura.constant.DocumentPropertiesConstant;
import com.tinysakura.core.document.DocumentType;
import com.tinysakura.core.document.Properties;
import com.tinysakura.core.index.Index;
import com.tinysakura.net.retrofit.service.IndexService;
import org.junit.Test;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/2
 */

public class RetrofitProxyServiceHolderTest {

    @Test
    public void RetrofitProxyServiceHolderTest() {
        IndexService indexService = RetrofitProxyServiceHolder.getInstance().getIndexServiceProxy();

        /**
         * 使用自定义的构造器构造请求体
         */
        Properties properties1 = new Properties.Builder().name("author").type(DocumentPropertiesConstant.Type.TEXT).storeOriginal2Index(true).build();
        Properties properties2 = new Properties.Builder().name("title").type(DocumentPropertiesConstant.Type.TEXT).storeOriginal2Index(true).build();

        DocumentType music = new DocumentType.Builder().name("music").dynamic(false).properties(properties1).properties(properties2).build();

        Index media = new Index.Builder().indexName("art").shardsNumber(4).replicasNumber(1)
                .mapping(music).build();

        Gson gson = new Gson();
        System.out.println(gson.toJson(media.getIndex()));

        indexService.createIndex(media.getIndexName(), media.getIndex());
    }
}