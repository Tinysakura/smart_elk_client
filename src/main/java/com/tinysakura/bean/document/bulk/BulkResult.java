package com.tinysakura.bean.document.bulk;

import lombok.Data;

import java.util.Map;

/**
 * 批量索引结果bean
 *
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */
@Data
public class BulkResult {
    /**
     * 花费的时间
     */
    Long took;

    /**
     * 是否出现问题
     */
    boolean errors;

    /**
     * Map<String,BulkOperation>
     * string -> 操作类型 {@link com.tinysakura.constant.BulkOperationConstants.Operation}
     */
    private Map<String,BulkOperation>[] items;
}