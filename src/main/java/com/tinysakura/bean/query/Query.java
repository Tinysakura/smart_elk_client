package com.tinysakura.bean.query;

import com.tinysakura.bean.query.extra.WordEntry;
import lombok.Data;

import java.util.Map;

/**
 * elk query节点bean
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class Query {
    QueryString query_string;

    /**
     * 词条查询
     */
    Map<String, WordEntry> term;

    /**
     * 多词条查询
     */
    Map<String, Object> terms;
}