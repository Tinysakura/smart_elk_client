package com.tinysakura.bean.document.bulk;

import lombok.Data;

/**
 * 批量索引操作响应bean
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */
@Data
public class BulkOperation {
    /**
     * 索引名
     */
    private String _index;

    /**
     * 操作类型{@link com.tinysakura.constant.BulkOperationConstants.Type}
     */
    private String _type;

    /**
     * 文档唯一标识符
     */
    private String _id;

    /**
     * 版本号
     */
    private Long _version;

    /**
     * rest api响应状态(200、404...)
     */
    private Integer status;

    /**
     * 出错原因
     */
    private String error;

    private Boolean found;
}