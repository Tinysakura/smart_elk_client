package com.tinysakura.bean.query.entry;

import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class MatchPhraseEntry {
    private String query;

    /**
     * 指定文本查询中的词条和文档中的词条之间可以有多少个未知词条
     * example:slop为1时，"a b"和"a and b"可以被视为匹配
     */
    private Integer slop;

    private String analyzer;
}