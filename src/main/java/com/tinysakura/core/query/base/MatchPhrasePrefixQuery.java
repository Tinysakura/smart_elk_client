package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.MatchPhrasePrefixEntry;
import com.tinysakura.exception.NotAppointFieldsException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 与matchPhrase查询基本相同但允许最后一个词条只进行前缀匹配
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class MatchPhrasePrefixQuery {
    Query query;

    public static final class Builder{
        private String fieldName;
        private Map<String, MatchPhrasePrefixEntry> matchPhrasePrefixEntryMap;
        private MatchPhrasePrefixEntry matchPhrasePrefixEntry;

        public Builder() {
            this.matchPhrasePrefixEntryMap = new HashMap<>(1);
            this.matchPhrasePrefixEntry = new MatchPhrasePrefixEntry();
        }

        public Builder field(String fieldName) {
            this.fieldName = fieldName;

            return this;
        }

        public Builder query(String query) {
            this.matchPhrasePrefixEntry.setQuery(query);

            return this;
        }

        public Builder analyzer(String analyzer) {
            this.matchPhrasePrefixEntry.setAnalyzer(analyzer);

            return this;
        }

        public Builder slop(Integer slop) {
            this.matchPhrasePrefixEntry.setSlop(slop);

            return this;
        }

        /**
         * 指定有多少前缀将被重写为最后的词条
         * @param expansion
         * @return
         */
        public Builder maxExpansions(Integer expansion) {
            this.matchPhrasePrefixEntry.setMax_expansions(expansion);

            return this;
        }

        public MatchPhrasePrefixQuery build() {
            if (fieldName == null) {
                throw new NotAppointFieldsException();
            }

            MatchPhrasePrefixQuery matchPhrasePrefixQuery = new MatchPhrasePrefixQuery();
            Query query = new Query();
            matchPhrasePrefixEntryMap.put(fieldName, matchPhrasePrefixEntry);
            query.setMatch_phrase_prefix(matchPhrasePrefixEntryMap);
            matchPhrasePrefixQuery.setQuery(query);

            return matchPhrasePrefixQuery;
        }
    }
}