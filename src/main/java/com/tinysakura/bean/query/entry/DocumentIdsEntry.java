package com.tinysakura.bean.query.entry;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class DocumentIdsEntry {
    /**
     * 文档标识符集合
     */
    private String[] values;

    /**
     * 限制文档类型
     */
    private String type;
}