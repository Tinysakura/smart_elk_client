package com.tinysakura.bean.analyzer;

import com.tinysakura.bean.filter.Filter;
import lombok.Data;

import java.util.Map;

/**
 * 分词器节点bean
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */
@Data
public class Analysis {
    Map<String, Analyzer> analyzer;

    Map<String, Filter> filter;
}