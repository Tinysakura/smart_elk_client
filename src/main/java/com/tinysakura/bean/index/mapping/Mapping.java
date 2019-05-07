package com.tinysakura.bean.index.mapping;

import com.tinysakura.bean.document.DocumentType;
import lombok.Data;

import java.util.Map;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */
@Data
public class Mapping {
    private Map<String, DocumentType> documentTypeMap;
}