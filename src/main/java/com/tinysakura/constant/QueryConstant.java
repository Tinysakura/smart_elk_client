package com.tinysakura.constant;

/**
 * 查询相关常量类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */

public class QueryConstant {

    /**
     * 搜索类型常量类
     */
    public class SearchType{
        /**
         * 在每个分片上分别执行再汇总结果
         */
        public static final String QUERY_THEN_FETCH = "query_then_fetch";

        /**
         * 所有分片返回等于size值的结果数
         */
        public static final String QUERY_AND_FETCH = "query_and_fetch";

        /**
         * 在初始查询中执行分布式词频计算的QUERY_AND_FETCH
         */
        public static final String DFS_QUERY_AND_FETCH = "dfs_query_and_fetch";

        /**
         * 在初始查询中执行分布式词频计算的QUERY_THEN_FETCH
         */
        public static final String DFS_QUERY_THEN_FETCH = "dfs_query_then_fetch";
    }

    /**
     * 搜索执行偏好常量类
     */
    public class SearchPreference {
        /**
         * 只在主分片上搜索
         */
        public static final String PRIMARY = "_primary";

        /**
         * 如果主分片可用则优先使用主分片
         */
        public static final String PRIMARY_FIRST = "_primary_first";

        /**
         * 如果可能的话只在发送请求的节点的可能分片上执行搜索
         */
        public static final String LOCAL = "_local";

        /**
         * 只在提供标识符的节点上搜索_only_node:{node_id}
         */
        public static final String ONLY_NODE = "_only_node:";

        /**
         * 将尝试在提供标识符的节点上搜素，如果节点不可用再尝试其他节点
         */
        public static final String PREFER_NODE = "_prefer_node:";

        /**
         * 将尝试在提供标识符的节点上搜索
         */
        public static final String SHARDS = "_shards:";
    }

}