package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.FuzzyEntry;
import com.tinysakura.exception.NotAppointFieldsException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * fuzzy查询
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class FuzzyQuery {
    Query query;

    public static final class Builder{
        private FuzzyEntry fuzzyEntry;
        private String fieldName;
        private Map<String, FuzzyEntry> fuzzyEntryMap;

        public Builder() {
            this.fuzzyEntry = new FuzzyEntry();
            this.fuzzyEntryMap = new HashMap<>(1);
        }

        public Builder field(String fieldName) {
            this.fieldName = fieldName;

            return this;
        }

        public Builder likeText(String value) {
            this.fuzzyEntry.setValue(value);

            return this;
        }

        public Builder prefixLength(Integer length) {
            this.fuzzyEntry.setPrefix_length(length);

            return this;
        }

        public Builder minSimilarity(Double min) {
            this.fuzzyEntry.setMin_similarity(min);

            return this;
        }

        public Builder boost(Double boost) {
            this.fuzzyEntry.setBoost(boost);

            return this;
        }

        public FuzzyQuery build() {
            if (fieldName == null) {
                throw new NotAppointFieldsException();
            }

            FuzzyQuery fuzzyQuery = new FuzzyQuery();
            Query query = new Query();
            fuzzyEntryMap.put(fieldName, fuzzyEntry);
            query.setFuzzy(fuzzyEntryMap);
            fuzzyQuery.setQuery(query);

            return fuzzyQuery;
        }
    }
}