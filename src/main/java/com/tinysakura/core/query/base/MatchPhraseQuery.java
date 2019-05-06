package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.MatchPhraseEntry;
import com.tinysakura.exception.NotAppointFieldsException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * matchPhrase查询，从分析后的文本中构建短语查询而不是布尔查询
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class MatchPhraseQuery {
    Query query;

    public static final class Builder{
        private String fieldName;
        private Map<String, MatchPhraseEntry> matchPhraseEntryMap;
        private MatchPhraseEntry matchPhraseEntry;

        public Builder() {
            this.matchPhraseEntryMap = new HashMap<>(1);
            this.matchPhraseEntry = new MatchPhraseEntry();
        }

        public Builder field(String fieldName) {
            this.fieldName = fieldName;

            return this;
        }

        public Builder query(String query) {
            this.matchPhraseEntry.setQuery(query);

            return this;
        }

        public Builder analyzer(String analyzer) {
            this.matchPhraseEntry.setAnalyzer(analyzer);

            return this;
        }

        public Builder slop(Integer slop) {
            this.matchPhraseEntry.setSlop(slop);

            return this;
        }

        public MatchPhraseQuery build() {
            if (fieldName == null) {
                throw new NotAppointFieldsException();
            }

            MatchPhraseQuery matchPhraseQuery = new MatchPhraseQuery();
            Query query = new Query();
            matchPhraseEntryMap.put(fieldName, matchPhraseEntry);
            query.setMatch_phrase(matchPhraseEntryMap);
            matchPhraseQuery.setQuery(query);

            return matchPhraseQuery;
        }
    }
}