package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.RangeEntry;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 范围查询
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class RangeQuery {
    private Query query;

    public static final class Builder{
        private Map<String, RangeEntry> rangeEntryMap;
        private String fieldName;
        private RangeEntry rangeEntry;

        public Builder() {
            this.rangeEntryMap = new HashMap<>(1);
            this.rangeEntry = new RangeEntry();
        }

        public Builder field(String fieldName) {
            this.fieldName = fieldName;

            return this;
        }

        public Builder gte(String value) {
            this.rangeEntry.setGte(value);

            return this;
        }

        public Builder gt(String value) {
            this.rangeEntry.setGt(value);

            return this;
        }

        public Builder lte(String value) {
            this.rangeEntry.setLte(value);

            return this;
        }

        public Builder lt(String value) {
            this.rangeEntry.setLt(value);

            return this;
        }

        public Builder gte(Float value) {
            this.rangeEntry.setGte(value);

            return this;
        }

        public Builder gt(Float value) {
            this.rangeEntry.setGt(value);

            return this;
        }

        public Builder lte(Float value) {
            this.rangeEntry.setLte(value);

            return this;
        }

        public Builder lt(Float value) {
            this.rangeEntry.setLt(value);

            return this;
        }

        public RangeQuery build() {
            RangeQuery rangeQuery = new RangeQuery();
            Query query = new Query();
            rangeEntryMap.put(fieldName, rangeEntry);
            query.setRange(rangeEntryMap);
            rangeQuery.setQuery(query);

            return rangeQuery;
        }
    }
}