package com.tinysakura.net.client;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tinysakura.net.retrofit.service.IndexService;
import com.tinysakura.net.retrofit.service.QueryService;
import com.tinysakura.util.ConfigurationFileLoadUtil;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.util.*;

/**
 * 一个单例（懒汉式）的Retrofit Service 持有类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */
public class RetrofitServiceHolder {
    private static RetrofitServiceHolder retrofitServiceHolder;

    /**
     * elk所有节点的retrofit客户端集合
     */
    private List<Retrofit> elkClients;

    /**
     * data
     */
    private Map<String, List<Object>> serviceMap;

    /**
     * constant
     */
    private static final String INDEX_SERVICE = "index";

    private static final String QUERY_SERVICE = "query";

    private static final String ELK_NODE_IP_CONFIG = "elk.node.ip";

    private RetrofitServiceHolder() {
        serviceMap = new HashMap<String, List<Object>>(16);
        serviceMap.put(INDEX_SERVICE, new ArrayList<Object>());
        serviceMap.put(QUERY_SERVICE, new ArrayList<Object>());

        elkClients = new ArrayList<Retrofit>();

        initRetrofitClient();
        initServices();
    }

    public static RetrofitServiceHolder getInstance() {
        if (retrofitServiceHolder == null) {
            synchronized (RetrofitServiceHolder.class) {
                if (retrofitServiceHolder == null) {
                    retrofitServiceHolder = new RetrofitServiceHolder();
                }
            }
        }

        return retrofitServiceHolder;
    }

    /**
     * retrofit client初始化
     */
    private void initRetrofitClient() {
        Properties properties = null;
        HashMap hashMap = null;
        String elkNodeIpsConfig = null;
        String[] elkNodeIps = null;

        /**
         * 从classpath下的resource目录下读取application.yml（application.properties）
         */
        String resourcePath = this.getClass().getClassLoader().getResource("").toString();

        if (new File(resourcePath.concat("/application.yaml")).exists()) {
            hashMap = ConfigurationFileLoadUtil.loadApplicationConfiguration4Yaml("application.yaml");
        } else if (new File(resourcePath.concat("/application.yml")).exists()) {
            hashMap = ConfigurationFileLoadUtil.loadApplicationConfiguration4Yaml("application.yml");
        } else {
            properties = ConfigurationFileLoadUtil.loadApplicationConfiguration4Properties();
        }

        if (hashMap != null) {
            elkNodeIpsConfig = (String) hashMap.get(ELK_NODE_IP_CONFIG);
        } else if (properties != null) {
            elkNodeIpsConfig = properties.getProperty(ELK_NODE_IP_CONFIG);
        }

        elkNodeIps = elkNodeIpsConfig.split(",");

        for (String ip : elkNodeIps) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ip)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            elkClients.add(retrofit);
        }
    }

    /**
     * retrofit service初始化
     */
    private void initServices() {
        for (Retrofit retrofit : elkClients) {
            IndexService indexService = retrofit.create(IndexService.class);
            QueryService queryService = retrofit.create(QueryService.class);

            serviceMap.get(INDEX_SERVICE).add(indexService);
            serviceMap.get(QUERY_SERVICE).add(queryService);
        }
    }

    /**
     * 获取索引操作相关的rest service
     * @return
     */
    public List<Object> getIndexService() {
        return serviceMap.get(INDEX_SERVICE);
    }

    public List<Object> getQueryService() {
        return serviceMap.get(QUERY_SERVICE);
    }

}