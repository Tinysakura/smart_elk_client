package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.MatchEntry;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * match查询
 * 传给match查询的词条将使用与建立索引时相同的分析器处理且不支持lucene语法
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class MatchQuery {
    private Query query;

    public static final class Builder{
        private Map<String, MatchEntry> matchQueryMap;
        private String fieldName;
        private MatchEntry matchEntry;

        public Builder() {
            this.matchQueryMap = new HashMap<>(1);
            this.matchEntry = new MatchEntry();
        }

        public Builder field(String fieldName) {
            this.fieldName = fieldName;

            return this;
        }

        public Builder query(String query) {
            this.matchEntry.setQuery(query);

            return this;
        }

        public Builder operator(String bool) {
            this.matchEntry.setOperator(bool);

            return this;
        }

        public Builder analyzer(String analyzer) {
            this.matchEntry.setAnalyzer(analyzer);

            return this;
        }

        public Builder fuzziness(Double fuzziness) {
            this.matchEntry.setFuzziness(fuzziness);

            return this;
        }

        /**
         * 设置当所有词条都被分析器移除后的查询行为
         * @param behavior
         * @return
         */
        public Builder zeroTermQuery(String behavior) {
            this.matchEntry.setZero_terms_query(behavior);

            return this;
        }

        public Builder cutoffFrequency(Double frequency) {
            this.matchEntry.setCutoff_frequency(frequency);

            return this;
        }

        public MatchQuery build() {
            Query query = new Query();
            matchQueryMap.put(fieldName, matchEntry);
            query.setMatch(matchQueryMap);
            MatchQuery matchQuery = new MatchQuery();
            matchQuery.setQuery(query);

            return matchQuery;
        }
    }

}