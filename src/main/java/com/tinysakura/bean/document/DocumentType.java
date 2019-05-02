package com.tinysakura.bean.document;

import lombok.Data;

import java.util.Map;

/**
 * 单个文档类型的映射配置
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/2
 */
@Data
public class DocumentType {
    private Map<String, Map<String, Object>> properties;

    /**
     * 是否打开自动类型猜测
     */
    private boolean dynamic;
}