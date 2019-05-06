package com.tinysakura.bean.query.entry;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class MoreKLikeEntry {

    private String[] fields;

    private String like_text;

    /**
     * 指定文档至少需要多少百分比的词条与查询匹配才能人为是类似
     */
    private Double percent_terms_to_match;

    /**
     * 指定文档中词条出现的最低词频，低于该词频的词条会被忽略
     */
    private Integer min_term_freq;

    /**
     * 暂停词，文档中的这些词会被忽略
     */
    private String[] stop_words;

    /**
     * 词条至少在n个文档中出现
     */
    private Integer min_doc_freq;

    /**
     * 词条至多在n个文档中出现
     */
    private Integer max_doc_freq;

    /**
     * 词条最小长度
     */
    private Integer min_word_len;

    /**
     * 词条最大长度
     */
    private Integer max_word_len;

    private Double boost;

    private String analyzer;
}