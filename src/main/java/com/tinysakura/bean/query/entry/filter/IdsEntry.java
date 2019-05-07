package com.tinysakura.bean.query.entry.filter;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/7
 */
@Data
public class IdsEntry {
    /**
     * 限定文档类型
     */
    private String[] type;

    /**
     * 限定文档唯一标识符
     */
    private String[] values;
}