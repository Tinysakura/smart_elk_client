package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.CommonEntry;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 常用词查询
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class CommonQuery {
    private Query query;

    public static final class Builder{
        private Query query;
        private String fieldName;
        private CommonEntry commonEntry;

        public Builder(){
            this.query = new Query();
            this.commonEntry = new CommonEntry();
        }

        /**
         * 指定查询字段
         * @param fieldName
         * @return
         */
        public Builder field(String fieldName) {
            this.fieldName = fieldName;

            return this;
        }

        /**
         * 指定查询文本
         * @param query
         * @return
         */
        public Builder queryContent(String query) {
            this.commonEntry.setQuery(query);

            return this;
        }

        /**
         * 指定高低频词组
         * @param frequency
         * @return
         */
        public Builder cutoffFrequency(double frequency) {
            this.commonEntry.setCutoff_frequency(frequency);

            return this;
        }

        /**
         * 低频词布尔运算符
         * @param bool
         * @return
         */
        public Builder lowFreqOperator(String bool) {
            this.commonEntry.setLow_freq_operator(bool);

            return this;
        }

        /**
         * 高频词布尔运算符
         * @param bool
         * @return
         */
        public Builder highFreqOperator(String bool) {
            this.commonEntry.setHigh_freq_operator(bool);

            return this;
        }

        /**
         * 最小匹配数量
         * @param num
         * @return
         */
        public Builder minimumMatch(Integer num) {
            this.commonEntry.setMinimumMatch(num);

            return this;
        }

        /**
         * 文档得分加权
         * @param boost
         * @return
         */
        public Builder boost(Double boost) {
            this.commonEntry.setBoost(boost);

            return this;
        }

        /**
         * 指定查询分析器
         * @param analyzer
         * @return
         */
        public Builder analyzer(String analyzer) {
            this.commonEntry.setAnalyzer(analyzer);

            return this;
        }

        public CommonQuery build() {
            CommonQuery commonQuery = new CommonQuery();
            Map<String, CommonEntry> map = new HashMap<>();
            map.put(fieldName, commonEntry);
            query.setCommon(map);
            commonQuery.setQuery(query);

            return commonQuery;
        }
    }
}