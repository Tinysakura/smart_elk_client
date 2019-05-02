package com.tinysakura.core.document;

import com.tinysakura.constant.DocumentPropertiesConstant;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 映射字段配置构造类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/2
 */
@Data
public class Properties {
    private String propertiesName;

    // private com.tinysakura.bean.document.Properties properties;

    private Map<String, Object> properties;

    public static final class Builder {
        private String propertiesName;

        private Map<String, Object> properties;

        public Builder() {
            properties = new HashMap<String, Object>();
        }

        public Builder name(String propertiesName) {
            this.propertiesName = propertiesName;

            return this;
        }

        public Builder type(String type) {
            properties.put("type", type);
            return this;
        }

        /**
         * 是否将原始值写入索引
         * @param store
         * @return
         */
        public Builder storeOriginal2Index(boolean store) {
            if (store) {
                properties.put("store", DocumentPropertiesConstant.Store.YES);
            } else {
                properties.put("store", DocumentPropertiesConstant.Store.NO);
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
                properties.put("index", DocumentPropertiesConstant.Index.ANALYZED);
            } else {
                properties.put("index", DocumentPropertiesConstant.Index.NOT_ANALYZED);
            }

            return this;
        }

        /**
         * 指定字段在索引中的名称
         * @param indexName
         * @return
         */
        public Builder indexName(String indexName) {
            properties.put("index_name", indexName);

            return this;
        }

        public Builder boost(Double boost) {
            properties.put("boost", boost);

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
            if ((type = (String) this.properties.get("type")) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.TEXT)) {
                    this.properties.put("term_vector", termVector);
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
            if ((type = (String) this.properties.get("type")) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.TEXT)) {
                    this.properties.put("omit_norms", omitNorms);
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
            if ((type = (String) this.properties.get("type")) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.TEXT)) {
                    this.properties.put("analyzer", analyzer);
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
            if ((type = (String) this.properties.get("type")) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.TEXT)) {
                    this.properties.put("index_analyzer", analyzer);
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
            if ((type = (String) this.properties.get("type")) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.TEXT)) {
                    this.properties.put("search_analyzer", analyzer);
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
            if ((type = (String) this.properties.get("type")) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.TEXT)) {
                    this.properties.put("ignore_above", limit);
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
            if ((type = (String) this.properties.get("type")) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.NUMBER) || type.equals(DocumentPropertiesConstant.Type.DATE)) {
                    this.properties.put("precision_step", precisionStep);
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
            if ((type = (String) this.properties.get("type")) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.NUMBER) || type.equals(DocumentPropertiesConstant.Type.DATE)) {
                    this.properties.put("ignore_malformed", ignoreMalformed);
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
            if ((type = (String) this.properties.get("type")) != null) {
                if (type.equals(DocumentPropertiesConstant.Type.DATE)) {
                    this.properties.put("format", format);
                }
            }

            return this;
        }

        /**
         * 多字段
         * @param fieldsName
         * @param fields
         * @return
         */
        public Builder fields(String fieldsName, Map<String, Object> fields) {
            if (this.properties.get("fields") == null) {
                this.properties.put("fields", new HashMap<String, Object>(16));
            }

            ((Map) this.properties.get("fields")).put(fieldsName, fields);

            return this;
        }

        public Properties build() {
            Properties properties = new Properties();
            properties.setPropertiesName(this.propertiesName);
            properties.setProperties(this.properties);

            return properties;
        }
    }
}