package com.tinysakura.core.query.composite;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.composite.BoolEntry;
import com.tinysakura.bean.query.entry.composite.RangeEntry;
import com.tinysakura.constant.QueryConstant;
import com.tinysakura.exception.NotAppointFieldsException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 布尔查询
 * 用法 ： new BoolQuery.Builder().must().field(xxx).value(xxx).
 *          .should().field(xxx).range(xxx, xxx).build;
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class BoolQuery {
    private Query query;

    public static final class Builder{
        private Map<String, BoolEntry> boolQueryMap;
        private String fieldName;
        private String bool;

        public Builder() {
            this.boolQueryMap = new HashMap<>(16);
        }

        public Builder must() {
            this.bool = QueryConstant.Bool.MUST;

            return this;
        }

        public Builder mustNot() {
            this.bool = QueryConstant.Bool.MUST_NOT;

            return this;
        }

        public Builder should() {
            this.bool = QueryConstant.Bool.SHOULD;

            return this;
        }

        public Builder field(String fieldName) {
            this.fieldName = fieldName;

            return this;
        }

        public Builder value(Object value) {
            if (fieldName == null) {
                throw new NotAppointFieldsException();
            }

            BoolEntry boolEntry = new BoolEntry();
            Map<String, Object> term = new HashMap<>(1);
            term.put(fieldName, value);
            boolEntry.setTerm(term);

            boolQueryMap.put(bool, boolEntry);

            return this;
        }

        public Builder range(Object from, Object to) {
            if (fieldName == null) {
                throw new NotAppointFieldsException();
            }

            BoolEntry boolEntry = new BoolEntry();
            Map<String, RangeEntry> range = new HashMap<>(1);
            RangeEntry rangeEntry = new RangeEntry();
            rangeEntry.setFrom(from);
            rangeEntry.setTo(to);
            range.put(fieldName, rangeEntry);
            boolEntry.setRange(range);

            boolQueryMap.put(bool, boolEntry);

            return this;
        }

        public BoolQuery build() {
            BoolQuery boolQuery = new BoolQuery();
            Query query = new Query();
            query.setBool(boolQueryMap);
            boolQuery.setQuery(query);

            return boolQuery;
        }
    }
}