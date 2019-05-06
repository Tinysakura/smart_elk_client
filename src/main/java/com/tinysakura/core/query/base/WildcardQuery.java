package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.WildCardEntry;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通配符查询(不注重性能)
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class WildcardQuery {
    Query query;

    public static final class Builder{
        private WildCardEntry wildCardEntry;
        private String fieldName;
        private Map<String, WildCardEntry> wildcardQueryMap;

        public Builder() {
            this.wildCardEntry = new WildCardEntry();
            this.wildcardQueryMap = new HashMap<>(1);
        }

        public Builder field(String fieldName) {
            this.fieldName = fieldName;

            return this;
        }

        public Builder wildcard(String text) {
            this.wildCardEntry.setValue(text);

            return this;
        }

        public Builder boost(Double boost) {
            this.wildCardEntry.setBoost(boost);

            return this;
        }

        public WildcardQuery build() {
            WildcardQuery wildcardQuery = new WildcardQuery();
            Query query = new Query();
            wildcardQueryMap.put(fieldName, wildCardEntry);
            query.setWildcard(wildcardQueryMap);
            wildcardQuery.setQuery(query);

            return wildcardQuery;
        }
    }
}