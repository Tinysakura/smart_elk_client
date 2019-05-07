package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.WordEntry;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 词条查询
 * 用法 ：new TermQuery.Builder.field(xxx).value(xxx).boost(xxx).build(); 注意三个方法的调用顺序
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class TermQuery {

    private Query query;

    public static class Builder{
        private Query query;

        private Map<String, WordEntry> entryMap;

        private String entryName;

        public Builder(){
            this.query = new Query();

            entryMap = new HashMap<>();
        }

        public Builder field(String entry) {
            this.entryName = entry;

            entryMap.put(entry, null);

            return this;
        }

        public Builder value(Object value) {
            if (entryMap.containsKey(entryName)) {
                WordEntry wordEntry = new WordEntry();
                wordEntry.setValue(value);

                entryMap.replace(entryName, wordEntry);
            }

            return this;
        }

        public Builder boost(Double boosts) {
            if (entryMap.containsKey(entryName)) {
                entryMap.get(entryName).setBoost(boosts);
            }

            return this;
        }

        public TermQuery build() {
            query.setTerm(entryMap);

            TermQuery termQuery = new TermQuery();
            termQuery.setQuery(this.query);

            return termQuery;
        }
    }
}