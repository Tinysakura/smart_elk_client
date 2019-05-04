package com.tinysakura.core.document.bulk;

import com.tinysakura.bean.document.bulk.BulkOperation;
import lombok.Data;

/**
 * BulkItem 构造类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */
@Data
public class BulkItem {
    /**
     * 操作类型{@link com.tinysakura.constant.BulkOperationConstants.Type}
     */
    private String operation;

    private com.tinysakura.bean.document.bulk.BulkItem bulkItem;

    public static final class Builder{
        private String operation;

        private com.tinysakura.bean.document.bulk.BulkItem bulkItem;

        public Builder() {
            this.bulkItem = new com.tinysakura.bean.document.bulk.BulkItem();
            this.bulkItem.setBulkOperation(new BulkOperation());
        }

        /**
         * 指定操作名
         * @param operation
         * @return
         */
        public Builder operation(String operation) {
            this.operation = operation;

            return this;
        }

        /**
         * 被索引的文档
         * @param document
         * @return
         */
        public Builder document(Object document) {
            this.bulkItem.setDocument(document);

            return this;
        }

        /**
         * 指定建立文档的索引
         * @param index
         * @return
         */
        public Builder index(String index) {
            this.bulkItem.getBulkOperation().set_index(index);

            return this;
        }

        /**
         * 指定文档唯一标识符
         * @param id
         * @return
         */
        public Builder documentId(String id) {
            this.bulkItem.getBulkOperation().set_id(id);

            return this;
        }

        /**
         * 指定操作类型
         * @param type {@link com.tinysakura.constant.BulkOperationConstants.Type}
         * @return
         */
        public Builder type(String type) {
            this.bulkItem.getBulkOperation().set_type(type);

            return this;
        }

        public BulkItem build() {
            BulkItem bulkItem = new BulkItem();
            bulkItem.setOperation(this.operation);
            bulkItem.setBulkItem(this.bulkItem);

            return bulkItem;
        }
    }
}