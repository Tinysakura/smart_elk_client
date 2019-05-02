package com.tinysakura.core.index;

import com.tinysakura.bean.document.Properties;
import com.tinysakura.bean.index.Setting;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 索引建造类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/2
 */
@Data
public class Index {
    /**
     * 索引名
     */
    private String indexName;

    /**
     * 索引映射文件
     */
    private com.tinysakura.bean.index.Index index;

    public static final class Builder {
        private com.tinysakura.bean.index.Index index;

        private String indexName;

        public Builder() {
            index = new com.tinysakura.bean.index.Index();
        }

        /**
         * 设置索引名
         * @param indexName
         * @return
         */
        public Builder indexName(String indexName) {
            this.indexName = indexName;

            return this;
        }

        /**
         * 设置索引所占分片数量
         * @param numberOfShards
         * @return
         */
        public Builder shardsNumber(Integer numberOfShards) {
            if (index.getSettings() == null) {
                index.setSettings(new Setting());
            }

            index.getSettings().setNumber_of_shards(numberOfShards);

            return this;
        }

        /**
         * 设置索引副本数量
         *
         * @param numberOfReplicas
         * @return
         */
        public Builder replicasNumber(Integer numberOfReplicas) {
            if (index.getSettings() == null) {
                index.setSettings(new Setting());
            }

            index.getSettings().setNumber_of_replicas(numberOfReplicas);

            return this;
        }

        /**
         * 添加文档类型映射
         * @param documentType
         * @return
         */
        public Builder mapping(com.tinysakura.core.document.DocumentType documentType) {
            if (index.getMappings() == null) {
                HashMap<String, Map<String, Map<String, Object>>> mappings = new HashMap<String, Map<String, Map<String, Object>>>(16);
                index.setMappings(mappings);
            }

            index.getMappings().put(documentType.getDocumentMappingName(), documentType.getDocumentType().getPropertiesMap());

            return this;
        }

        public Index build() {
            Index index = new Index();
            index.setIndexName(this.indexName);
            index.setIndex(this.index);

            return index;
        }
    }
}