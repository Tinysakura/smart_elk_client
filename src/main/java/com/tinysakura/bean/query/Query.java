package com.tinysakura.bean.query;

import com.tinysakura.bean.query.entry.*;
import com.tinysakura.bean.query.entry.composite.BoolEntry;
import lombok.Data;

import java.util.Map;

/**
 * elk query节点bean
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class Query {
    /**
     * queryString查询
     */
    QueryStringEntry query_string;

    /**
     * simpleQueryString查询
     */
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

    /**
     * 标识符查询
     */
    DocumentIdsEntry ids;

    /**
     * 前缀查询
     */
    Map<String, PrefixEntry> prefix;

    /**
     * fuzzy_like_this查询
     */
    FuzzyLikeEntry fuzzy_like_this;

    /**
     * fuzzy查询
     */
    Map<String, FuzzyEntry> fuzzy;

    /**
     * 通配符查询
     */
    Map<String, WildCardEntry> wildcard;

    /**
     * more_like_this查询
     */
    MoreKLikeEntry more_like_this;

    /**
     * 范围查询
     */
    Map<String, RangeEntry> range;

    /**
     * 正则表达式查询
     */
    Map<String, RegexpEntry> regexp;

    /**
     * 布尔查询
     */
    Map<String, BoolEntry> bool;
}