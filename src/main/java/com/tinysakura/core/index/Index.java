package com.tinysakura.core.index;

import com.tinysakura.bean.document.DocumentType;
import com.tinysakura.bean.index.Mapping;
import com.tinysakura.bean.index.Setting;
import lombok.Data;

import java.util.HashMap;

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
            if (index.getSetting() == null) {
                index.setSetting(new Setting());
            }

            index.getSetting().setNumber_of_shards(numberOfShards);

            return this;
        }

        /**
         * 设置索引副本数量
         *
         * @param numberOfReplicas
         * @return
         */
        public Builder replicasNumber(Integer numberOfReplicas) {
            if (index.getSetting() == null) {
                index.setSetting(new Setting());
            }

            index.getSetting().setNumber_of_replicas(numberOfReplicas);

            return this;
        }

        /**
         * 添加文档类型映射
         * @param documentType
         * @return
         */
        public Builder mapping(String documentMappingName, DocumentType documentType) {
            if (index.getMapping() == null) {
                Mapping mapping = new Mapping();
                mapping.setDocumentTypeMap(new HashMap<String, DocumentType>(16));

                index.setMapping(mapping);
            }

            index.getMapping().getDocumentTypeMap().put(documentMappingName, documentType);

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