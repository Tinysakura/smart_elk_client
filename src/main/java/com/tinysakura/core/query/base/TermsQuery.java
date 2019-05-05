package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 多词条查询
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class TermsQuery {
    private Query query;

    public static final class Builder{
        private Query query;
        private Map<String, Object> entryMap;
        private String entryName;
        private Object[] entryValues;
        private Integer minimumMatch;

        public Builder() {
            this.query = new Query();
            this.entryMap = new HashMap<>();
        }

        public Builder field(String fieldName) {
            this.entryName = fieldName;

            return this;
        }

        public Builder values(Object[] values) {
            this.entryValues = values;

            return this;
        }

        public Builder minimumMatch(Integer minimumMatch) {
            this.minimumMatch = minimumMatch;

            return this;
        }

        public TermsQuery build() {
            TermsQuery termsQuery = new TermsQuery();
            entryMap.put(entryName, entryValues);
            entryMap.put("minimumMatch", minimumMatch);
            query.setTerms(entryMap);
            termsQuery.setQuery(query);

            return termsQuery;
        }
    }
}