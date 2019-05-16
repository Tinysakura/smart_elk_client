package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.MultiMatchEntry;
import lombok.Data;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class MultiMatchQuery {
    private Query query;

    public static final class Builder{
        private String[] fields;
        private MultiMatchEntry multiMatchEntry;

        public Builder() {
            this.multiMatchEntry = new MultiMatchEntry();
        }

        public Builder fields(String[] fields) {
            this.fields = fields;

            return this;
        }

        public Builder query(String query) {
            this.multiMatchEntry.setQuery(query);

            return this;
        }

        public Builder operator(String bool) {
            this.multiMatchEntry.setOperator(bool);

            return this;
        }

        public Builder analyzer(String analyzer) {
            this.multiMatchEntry.setAnalyzer(analyzer);

            return this;
        }

        public Builder fuzziness(Double fuzziness) {
            this.multiMatchEntry.setFuzziness(fuzziness);

            return this;
        }

        /**
         * 设置当所有词条都被分析器移除后的查询行为
         * @param behavior
         * @return
         */
        public Builder zeroTermQuery(String behavior) {
            this.multiMatchEntry.setZero_terms_query(behavior);

            return this;
        }

        public Builder cutoffFrequency(Double frequency) {
            this.multiMatchEntry.setCutoff_frequency(frequency);

            return this;
        }

        public  MultiMatchQuery build() {
            MultiMatchQuery multiMatchQuery = new MultiMatchQuery();
            Query query = new Query();
            multiMatchEntry.setFields(fields);
            query.setMulti_match(multiMatchEntry);
            multiMatchQuery.setQuery(query);

            return multiMatchQuery;
        }
    }
}