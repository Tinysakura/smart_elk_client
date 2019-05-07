package com.tinysakura.bean.query.entry.filter;

import lombok.Data;

import java.util.Map;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/7
 */
@Data
public class ScriptEntry {
    /**
     * 脚本
     */
    private String script;

    /**
     * 脚本变量
     */
    private Map<String, Object> params;
}