package com.tinysakura.bean.query.entry.sort;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/7
 */
@Data
public class SortEntry {
    /**
     * 排序规则{@link com.tinysakura.constant.QueryConstant.Order}
     */
    private String order;

    /**
     * 指定缺乏指定字段的文档时的排序行为{@link com.tinysakura.constant.QueryConstant.FieldMissingBehavior}
     * 也可以指定任意数字，此时缺少该字段的文档得分等于该数字
     */
    private Object missing;

    /**
     * 排序脚本
     */
    private ScriptEntry _script;


}