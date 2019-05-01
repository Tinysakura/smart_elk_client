package com.tinysakura.bean.index;

import lombok.Data;

/**
 * 索引操作相关请求体
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */
@Data
public class Index {
    private Mapping mapping;
    private Setting setting;
}