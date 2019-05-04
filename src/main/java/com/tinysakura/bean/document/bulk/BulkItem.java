package com.tinysakura.bean.document.bulk;

import lombok.Data;

/**
 * 批量文档索引建立中一条数据的bean
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */
@Data
public class BulkItem {
    /**
     * 操作类型描述
     */
    private BulkOperation bulkOperation;

    /**
     * 被索引文档
     */
    private Object document;
}