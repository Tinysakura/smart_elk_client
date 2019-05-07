package com.tinysakura.bean.query.entry.sort;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/7
 */
@Data
public class ScriptEntry {
    private String script;

    /**
     * 排序规则{@link com.tinysakura.constant.QueryConstant.Order}
     */
    private String order;

    /**
     * 用于比较的类型
     */
    private String type;
}