package com.tinysakura.bean.index.mapping.extra;

import lombok.Data;

/**
 * _index字段存储文档所在的索引
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */
@Data
public class Index {
    /**
     * 是否启用_index字段
     */
    private String enabled;
}