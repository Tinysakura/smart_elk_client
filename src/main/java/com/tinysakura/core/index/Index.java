package com.tinysakura.core.index;

import com.tinysakura.bean.analyzer.Analysis;
import com.tinysakura.bean.document.DocumentType;
import com.tinysakura.bean.index.Setting;
import com.tinysakura.bean.index.SettingIndex;
import com.tinysakura.core.analyzer.Analyzer;
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
         * 为索引设置自定义分析器
         * @param analyzer
         * @return
         */
        public Builder analysis(Analyzer analyzer) {
            if (index.getSettings() == null) {
                index.setSettings(new Setting());
            }

            if (index.getSettings().getSettingIndex() == null) {
                index.getSettings().setSettingIndex(new SettingIndex());
            }

            if (index.getSettings().getSettingIndex().getAnalysis() == null) {
                index.getSettings().getSettingIndex().setAnalysis(new Analysis());
                index.getSettings().getSettingIndex().getAnalysis().setAnalyzer(new HashMap<String, com.tinysakura.bean.analyzer.Analyzer>());
            }

            index.getSettings().getSettingIndex().getAnalysis().getAnalyzer().put(analyzer.getName(), analyzer.getAnalyzer());

            return this;
        }

        /**
         * 添加文档类型映射
         * @param documentType
         * @return
         */
        public Builder mapping(com.tinysakura.core.document.DocumentType documentType) {
            if (index.getMappings() == null) {
                Map<String, DocumentType> mapping = new HashMap<String, DocumentType>(16);

                index.setMappings(mapping);
            }

            index.getMappings().put(documentType.getDocumentMappingName(), documentType.getDocumentType());

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