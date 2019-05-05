package com.tinysakura.bean.query;

import lombok.Data;

/**
 * 排除条件bean
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class Partial {
    /**
     * 结果集中应该包含的字段，可以使用通配符
     */
    String[] include;

    /**
     * 结果集中不应该包含的字段，可以使用通配符
     */
    String[] exclude;
}