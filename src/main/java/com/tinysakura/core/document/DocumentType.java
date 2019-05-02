package com.tinysakura.core.document;

import com.tinysakura.bean.document.Properties;
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
            documentType.setPropertiesMap(new HashMap<String, Map<String, Object>>());
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

        /**
         * 文档字段映射
         * @param properties
         * @return
         */
        public Builder properties(com.tinysakura.core.document.Properties properties) {
            documentType.getPropertiesMap().put(properties.getPropertiesName(), properties.getProperties());

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