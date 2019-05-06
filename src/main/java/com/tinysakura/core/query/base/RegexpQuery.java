package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.RegexpEntry;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 正则表达式查询
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class RegexpQuery {
    Query query;

    public static final class Builder{
        private RegexpEntry regexpEntry;
        private String fieldName;
        private Map<String, RegexpEntry> regexpEntryMap;

        public Builder() {
            this.regexpEntry = new RegexpEntry();
            this.regexpEntryMap = new HashMap<>(1);
        }

        public Builder field(String fieldName) {
            this.fieldName = fieldName;

            return this;
        }

        public Builder regexp(String regexp) {
            this.regexpEntry.setValue(regexp);

            return this;
        }

        public Builder boost(Double boost) {
            this.regexpEntry.setBoost(boost);

            return this;
        }

        public RegexpQuery build() {
            RegexpQuery regexpQuery = new RegexpQuery();
            Query query = new Query();
            regexpEntryMap.put(fieldName, regexpEntry);
            query.setRegexp(regexpEntryMap);
            regexpQuery.setQuery(query);

            return regexpQuery;
        }
    }
}