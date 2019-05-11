package com.tinysakura;

import com.google.gson.Gson;
import com.tinysakura.bean.base.Acknowledged;
import com.tinysakura.bean.query.result.QueryResponse;
import com.tinysakura.bean.test.Book;
import com.tinysakura.constant.DocumentPropertiesConstant;
import com.tinysakura.core.analyzer.Analyzer;
import com.tinysakura.core.document.DocumentType;
import com.tinysakura.core.document.Properties;
import com.tinysakura.core.index.Index;
import com.tinysakura.core.query.QueryBody;
import com.tinysakura.core.query.base.MatchQuery;
import com.tinysakura.core.query.base.TermQuery;
import com.tinysakura.net.client.RetrofitProxyServiceHolder;
import com.tinysakura.net.retrofit.service.DocumentService;
import com.tinysakura.net.retrofit.service.IndexService;
import com.tinysakura.net.retrofit.service.QueryService;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

/**
 * 使用框架创建索引，添加文档，查询等操作的完整示例
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/7
 */

public class SampleTest {

    @Test
    public void createIndexTest() {
        Gson gson = new Gson();
        IndexService indexService = RetrofitProxyServiceHolder.getInstance().getIndexServiceProxy();

        Analyzer.Builder analyzerBuilder = new Analyzer.Builder();
        Analyzer analyzer = analyzerBuilder.name("chinese").tokenizer("ik_max_word").build();
        System.out.println(gson.toJson(analyzer));

        Properties.Builder propBuilder = new Properties.Builder();
        Properties properties = propBuilder.name("author").type(DocumentPropertiesConstant.Type.TEXT).analyzer("ik_max_word").name("title").analyzer("ik_max_word").type(DocumentPropertiesConstant.Type.TEXT).build();
        System.out.println(gson.toJson(properties));

        DocumentType.Builder docBuilder = new DocumentType.Builder();
        DocumentType documentType = docBuilder.name("book").properties(properties).build();
        System.out.println(gson.toJson(documentType));

        Index.Builder indexBuilder = new Index.Builder();
        Index index = indexBuilder.indexName("book").replicasNumber(1).shardsNumber(4).analysis(analyzer).mapping(documentType).build();
        System.out.println(gson.toJson(index));

        indexService.createIndex(index.getIndexName(), index.getIndex()).subscribe(new Consumer<Acknowledged>() {
            @Override
            public void accept(Acknowledged acknowledged) throws Exception {
                System.out.println(acknowledged);
            }
        });
    }

    @Test
    public void addDocumentTest() {
        Gson gson = new Gson();
        DocumentService documentService = RetrofitProxyServiceHolder.getInstance().getDocumentServiceProxy();

        Book book = new Book();
        book.setTitle("冰与火之歌");
        book.setAuthor("乔治·马丁");

        documentService.postDocument("book", "book", book).subscribe(new Consumer<Acknowledged>() {
            @Override
            public void accept(Acknowledged acknowledged) throws Exception {
                System.out.println(acknowledged);
            }
        });

    }

    @Test
    public void termQueryTest() {
        Gson gson = new Gson();
        QueryService queryService = RetrofitProxyServiceHolder.getInstance().getQueryServiceProxy();

        TermQuery.Builder builder = new TermQuery.Builder();
        TermQuery termQuery = builder.field("author").value("马丁").build();

        QueryBody.Builder queryBodyBuilder = new QueryBody.Builder();
        QueryBody queryBody = queryBodyBuilder.query(termQuery.getQuery()).build();

        queryService.search("book", "book", queryBody.getQueryBody(), Book.class).subscribe(new Consumer<QueryResponse>() {
            @Override
            public void accept(QueryResponse queryResponse) throws Exception {
                System.out.println(queryResponse.getResults());
            }
        });
    }

    @Test
    public void matchQueryTest() {
        Gson gson = new Gson();
        QueryService queryService = RetrofitProxyServiceHolder.getInstance().getQueryServiceProxy();

        MatchQuery.Builder matchQueryBuilder = new MatchQuery.Builder();
        MatchQuery matchQuery = matchQueryBuilder.field("title").query("冰与火之歌").analyzer("ik_max_word").build();

        QueryBody.Builder queryBodyBuilder = new QueryBody.Builder();
        QueryBody queryBody = queryBodyBuilder.query(matchQuery.getQuery()).build();

        System.out.println(gson.toJson(queryBody.getQueryBody()));

        queryService.search("book", "book", queryBody.getQueryBody(), Book.class).subscribeOn(Schedulers.io()).subscribe(new Consumer<QueryResponse>() {
            @Override
            public void accept(QueryResponse queryResponse) throws Exception {
                System.out.println(queryResponse.getResults());
            }
        });
    }
}