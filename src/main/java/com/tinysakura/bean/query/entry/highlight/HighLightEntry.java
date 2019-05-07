package com.tinysakura.bean.query.entry.highlight;

import lombok.Data;

import java.util.Map;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/7
 */
@Data
public class HighLightEntry {
    private String[] pre_tags;

    private String[] post_tags;

    private Map<String, FieldEntry> fields;
}