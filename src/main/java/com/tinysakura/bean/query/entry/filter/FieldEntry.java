package com.tinysakura.bean.query.entry.filter;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/7
 */
@Data
public class FieldEntry {
    /**
     * 字段名
     */
    private String field;

    /**
     * 字段应该被视为空的额外值
     */
    private Object null_value;


}