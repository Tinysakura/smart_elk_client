package com.tinysakura.core.document;

import com.tinysakura.bean.index.mapping.extra.*;
import com.tinysakura.constant.DocumentPropertiesConstant;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 索引映射构造类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/2
 */
@Data
public class DocumentType {
    private String documentMappingName;

    private com.tinysakura.bean.document.DocumentType documentType;

    public static final class Builder {
        private com.tinysakura.bean.document.DocumentType documentType;

        private String mappingName;

        public Builder() {
            this.documentType = new com.tinysakura.bean.document.DocumentType();
            documentType.setProperties(new HashMap<String, Map<String, Object>>(16));
            /**
             * 默认开启自动类型猜测
             */
            documentType.setDynamic(true);
        }

        /**
         * 索引结构映射名
         * @param mappingName
         * @return
         */
        public Builder name(String mappingName) {
            this.mappingName = mappingName;

            return this;
        }

        public Builder dynamic(boolean dynamic) {
            this.documentType.setDynamic(dynamic);

            return this;
        }

        /**
         * 文档字段映射
         * @param properties
         * @return
         */
        public Builder properties(com.tinysakura.core.document.Properties properties) {
            documentType.getProperties().put(properties.getPropertiesName(), properties.getProperties());

            return this;
        }

        /**
         * 是否启用_all字段
         * @param enabled
         * @return
         */
        public Builder extraAll(boolean enabled) {
            All all = new All();
            all.setEnabled(enabled);
            documentType.set_all(all);

            return this;
        }

        /**
         * 使用文档中的哪个字段的值作为唯一标识符
         * @param path
         * @return
         */
        public Builder extraId(String path) {
            Id id = new Id();
            id.setPath(path);
            documentType.set_id(id);

            return this;
        }


        /**
         * 是否启用_index字段
         * @param enabled
         * @return
         */
        public Builder extraIndex(boolean enabled) {
            Index index = new Index();
            index.setEnabled(enabled);
            documentType.set_index(index);

            return this;
        }

        /**
         * 是否启用size字段
         * @param enabled
         * @return
         */
        public Builder extraSize(boolean enabled) {
            if (enabled) {
                Size size = new Size();
                size.setEnabled(enabled);
                size.setStore(DocumentPropertiesConstant.Store.YES);
                documentType.set_size(size);
            }

            return this;
        }

        /**
         * 是否启用_timestamp字段
         * @param enabled
         * @return
         */
        public Builder extraTimestamp(boolean enabled) {
            Timestamp timestamp = new Timestamp();
            timestamp.setEnabled(enabled);
            documentType.set_timestamp(timestamp);

            return this;
        }

        /**
         * 是否启用source字段
         * @param enabled
         * @return
         */
        public Builder extraSoure(boolean enabled) {
            Source source = new Source();
            source.setEnabled(enabled);
            documentType.set_source(source);

            return this;
        }

        /**
         * 设置文档过期时间
         * @param expire
         * @return
         */
        public Builder extraTTL(String expire) {
            TTL ttl = new TTL();
            ttl.setEnabled(true);
            ttl.setDefaults(expire);
            documentType.set_ttl(ttl);

            return this;
        }

        /**
         * 是否存储文档类型字段
         * @param store
         * @return
         */
        public Builder extraType(boolean store) {
            Type type = new Type();

            if (store) {
                type.setStore(DocumentPropertiesConstant.Store.YES);
            } else {
                type.setStore(DocumentPropertiesConstant.Store.NO);
            }
            documentType.set_type(type);

            return this;
        }

        public DocumentType build() {
            DocumentType documentType = new DocumentType();
            documentType.setDocumentMappingName(this.mappingName);
            documentType.setDocumentType(this.documentType);

            return documentType;
        }
    }
}