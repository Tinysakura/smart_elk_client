package com.tinysakura.bean.index;

import com.tinysakura.bean.document.Properties;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 索引操作相关请求体
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */
@Data
public class Index {
    private HashMap<String, Map<String, Map<String, Object>>> mappings;
    private Setting settings;
}