package com.tinysakura.core.document;

import com.tinysakura.constant.DocumentPropertiesConstant;
import lombok.Data;

import java.util.HashMap;

/**
 * 映射字段配置构造类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/2
 */
@Data
public class Properties {
    private String propertiesName;

    private com.tinysakura.bean.document.Properties properties;

    public static final class Builder {
        private String propertiesName;

        private com.tinysakura.bean.document.Properties properties;

        public Builder() {
            this.properties = new com.tinysakura.bean.document.Properties();
        }

        public Builder name(String propertiesName) {
            this.propertiesName = propertiesName;

            return this;
        }

        public Builder type(String type) {
            this.properties.setType(type);

            return this;
        }

        /**
         * 是否将原始值写入索引
         * @param store
         * @return
         */
        public Builder storeOriginal2Index(boolean store) {
            if (store) {
                this.properties.setStore(DocumentPropertiesConstant.Store.YES);
            } else {
                this.properties.setStore(DocumentPropertiesConstant.Store.NO);
            }

            return this;
        }

        /**
         * 是否将该字段写入索引
         * @param index
         * @return
         */
        public Builder write2Index(boolean index) {
            if (index) {
                this.properties.setIndex(DocumentPropertiesConstant.Index.ANALYZED);
            } else {
                this.properties.setIndex(DocumentPropertiesConstant.Index.NOT_ANALYZED);
            }

            return this;
        }

        /**
         * 指定字段在索引中的名称
         * @param indexName
         * @return
         */
        public Builder indexName(String indexName) {
            this.properties.setIndex_name(indexName);

            return this;
        }

        public Builder boost(Double boost) {
            this.properties.setBoost(boost);

            return this;
        }

        /**
         * 是否计算Lucene词向量
         * 字符串类型字段限定
         * @param termVector
         * @return
         */
        public Builder termVector(String termVector) {
            String type;
            if ((type = this.properties.getType()) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.TEXT)) {
                    this.properties.setTerm_vector(termVector);
                }
            }

            return this;
        }

        /**
         * 是否禁用Lucene对该字段的加权基准计算
         * 字符串类型字段限定
         * @param omitNorms
         * @return
         */
        public Builder openOmitNorms(boolean omitNorms) {
            String type;
            if ((type = this.properties.getType()) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.TEXT)) {
                    this.properties.setOmit_norms(omitNorms);
                }
            }

            return this;
        }

        /**
         * 指定分词器
         * 字符串类型字段限定
         * @param analyzer
         * @return
         */
        public Builder analyzer(String analyzer) {
            String type;
            if ((type = this.properties.getType()) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.TEXT)) {
                    this.properties.setAnalyzer(analyzer);
                }
            }

            return this;
        }

        /**
         * 指定写入索引使用的分词器
         * 字符串类型字段限定
         * @param analyzer
         * @return
         */
        public Builder indexAnalyzer(String analyzer) {
            String type;
            if ((type = this.properties.getType()) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.TEXT)) {
                    this.properties.setIndex_analyzer(analyzer);
                }
            }

            return this;
        }

        /**
         * 指定查询时使用的分词器
         * 字符串类型字段限定
         * @param analyzer
         * @return
         */
        public Builder searchAnalyzer(String analyzer) {
            String type;
            if ((type = this.properties.getType()) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.TEXT)) {
                    this.properties.setSerach_analyzer(analyzer);
                }
            }

            return this;
        }

        /**
         * 指定该字段中内容字符的最大值，当超过最大值时分析器会将其忽略
         * 字符串类型字段限定
         * @param limit
         * @return
         */
        public Builder propertiesLengthLimit(Long limit) {
            String type;
            if ((type = this.properties.getType()) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.TEXT)) {
                    this.properties.setIgnore_above(limit);
                }
            }

            return this;
        }

        /**
         * 指定字段中每个值生成的词条数，值越低产生的词条数越高（范围查询更快但索引稍大）
         * 数值和日期类型限定
         * @param precisionStep
         * @return
         */
        public Builder precisionStep(Integer precisionStep) {
            String type;
            if ((type = this.properties.getType()) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.NUMBER) || type.equals(DocumentPropertiesConstant.Type.DATE)) {
                    this.properties.setPrecision_step(precisionStep);
                }
            }

            return this;
        }

        /**
         * 是否忽略格式错误的字段
         * 数值和日期类型限定
         * @param ignoreMalformed
         * @return
         */
        public Builder ignoreMalformed(boolean ignoreMalformed) {
            String type;
            if ((type = this.properties.getType()) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.NUMBER) || type.equals(DocumentPropertiesConstant.Type.DATE)) {
                    this.properties.setIgnore_malformed(ignoreMalformed);
                }
            }

            return this;
        }

        /**
         * 指定日期格式
         * 日期类型限定
         * @param format
         * @return
         */
        public Builder format(String format) {
            String type;
            if ((type = this.properties.getType()) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.DATE)) {
                    this.properties.setFormat(format);
                }
            }

            return this;
        }

        /**
         * 多字段
         * @param fieldsName
         * @param properties
         * @return
         */
        public Builder fields(String fieldsName, com.tinysakura.bean.document.Properties properties) {
            if (this.properties.getFields() == null) {
                this.properties.setFields(new HashMap<String, com.tinysakura.bean.document.Properties>(16));
            }

            this.properties.getFields().put(fieldsName, properties);

            return this;
        }
    }
}