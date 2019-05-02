package com.tinysakura.bean.document;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 单个文档类型的映射配置
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/2
 */
@Data
public class DocumentType {
    private HashMap<String, Map<String, Object>> propertiesMap;
}