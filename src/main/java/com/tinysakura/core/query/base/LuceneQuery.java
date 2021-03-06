package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.QueryStringEntry;
import lombok.Data;

/**
 * 对应query节点下的query_string节点。支持全部的Apache Lucene语法
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class LuceneQuery {
    private Query query;

    public static final class Builder{
        private QueryStringEntry queryStringEntry;

        /**
         * 是否启用simple_query_string
         * 同样接受lucene语法但会丢弃无效的部分，因此不会抛出异常
         */
        private boolean simpleEnabled;

        public Builder() {
            this.queryStringEntry = new QueryStringEntry();
        }

        public Builder query(String query) {
            this.queryStringEntry.setQuery(query);

            return this;
        }

        public Builder simple(Boolean simpleEnabled) {
            this.simpleEnabled = simpleEnabled;

            return this;
        }

        public Builder defaultField(String field) {
            this.queryStringEntry.setDefault_field(field);

            return this;
        }

        public Builder defaultOperator(String operator) {
            this.queryStringEntry.setDefault_operator(operator);

            return this;
        }

        public Builder allowLeadingWildcard(Boolean bool) {
            this.queryStringEntry.setAllow_leading_wildcard(bool);

            return this;
        }

        public Builder lowercase(Boolean bool) {
            this.queryStringEntry.setAllow_leading_wildcard(bool);

            return this;
        }

        public Builder fuzzyMaxExpansions(Integer expansions) {
            this.queryStringEntry.setFuzzy_max_expansions(expansions);

            return this;
        }

        public Builder fuzzyPrefixLength(Integer length) {
            this.queryStringEntry.setFuzzy_prefix_length(length);

            return this;
        }

        public Builder fuzzyMinSim(Double minSim) {
            this.queryStringEntry.setFuzzy_min_sim(minSim);

            return this;
        }

        public Builder boost(Double boost) {
            this.queryStringEntry.setBoost(boost);

            return this;
        }

        public Builder analyzerWildcard(Boolean bool) {
            this.queryStringEntry.setAnalyzer_wildcard(bool);

            return this;
        }

        public Builder minimumShouldMatch(Double minimum) {
            this.queryStringEntry.setMinimum_should_match(minimum);

            return this;
        }

        public Builder useDisMac(Boolean bool) {
            this.queryStringEntry.setUse_dis_max(bool);

            return this;
        }

        public Builder fields(String[] fields) {
            this.queryStringEntry.setFields(fields);

            return this;
        }

        public LuceneQuery build() {
            LuceneQuery luceneQuery = new LuceneQuery();
            Query query = new Query();
            if (simpleEnabled) {
                query.setSimple_query_string(queryStringEntry);
            } else {
                query.setQuery_string(queryStringEntry);
            }
            luceneQuery.setQuery(query);

            return luceneQuery;
        }
    }
}