package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.PrefixEntry;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀查询，指定字段前缀匹配的将作为结果返回
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class PrefixQuery {
    private Query query;

    public static final class Builder{
        private Map<String, PrefixEntry> prefixEntryMap;
        private PrefixEntry prefixEntry;
        private String fieldsName;

        public Builder() {
            this.prefixEntryMap = new HashMap<>(1);
            this.prefixEntry = new PrefixEntry();
        }

        public Builder fields(String fieldsName) {
            this.fieldsName = fieldsName;

            return this;
        }

        public Builder prefix(String value) {
            this.prefixEntry.setValue(value);

            return this;
        }

        public Builder boost(Double boost) {
            this.prefixEntry.setBoost(boost);

            return this;
        }

        public PrefixQuery build() {
            PrefixQuery prefixQuery = new PrefixQuery();
            Query query = new Query();
            prefixEntryMap.put(fieldsName, prefixEntry);
            query.setPrefix(prefixEntryMap);
            prefixQuery.setQuery(query);

            return prefixQuery;
        }
    }
}