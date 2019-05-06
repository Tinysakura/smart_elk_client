package com.tinysakura.bean.query;

import com.tinysakura.bean.query.entry.*;
import lombok.Data;

import java.util.Map;

/**
 * elk query节点bean
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class Query {
    QueryStringEntry query_string;

    QueryStringEntry simple_query_string;

    /**
     * 词条查询
     */
    Map<String, WordEntry> term;

    /**
     * 多词条查询
     */
    Map<String, Object> terms;

    /**
     * 常用词查询
     */
    Map<String, CommonEntry> common;

    /**
     * match查询
     */
    Map<String, MatchEntry> match;

    /**
     * matchPhrase查询
     */
    Map<String, MatchPhraseEntry> match_phrase;

    /**
     * matchPhrasePrefix查询
     */
    Map<String, MatchPhrasePrefixEntry> match_phrase_prefix;

    /**
     * 多字段match查询
     */
    MultiMatchEntry multi_match;
}