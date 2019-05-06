package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.composite.BoostingEntry;
import com.tinysakura.bean.query.entry.composite.RangeEntry;
import com.tinysakura.constant.QueryConstant;
import com.tinysakura.exception.NotAppointFieldsException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 加权查询，包含两个查询和一个权值，positive节点查询返回的文档得分不变，negative节点查询得到的文档的得分会被乘以权值
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class BoostingQuery {
    private Query query;

    public static final class Builder{
        private Map<String, Object> boostingEntryMap;
        /**
         * 倾向{@link com.tinysakura.constant.QueryConstant.Boosting}
         */
        private String tendency;
        private String fieldName;

        public Builder() {
            this.boostingEntryMap = new HashMap<>(3);
        }

        public Builder positive() {
            this.tendency = QueryConstant.Boosting.POSITIVE;

            return this;
        }

        public Builder negative() {
            this.tendency = QueryConstant.Boosting.NEGATIVE;

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

            BoostingEntry boostingEntry = new BoostingEntry();
            Map<String, Object> term = new HashMap<>(1);
            term.put(fieldName, value);
            boostingEntry.setTerm(term);

            boostingEntryMap.put(tendency, boostingEntry);

            return this;
        }

        public Builder range(Object from, Object to) {
            if (fieldName == null) {
                throw new NotAppointFieldsException();
            }

            BoostingEntry boostingEntry = new BoostingEntry();
            Map<String, RangeEntry> range = new HashMap<>(1);
            RangeEntry rangeEntry = new RangeEntry();
            rangeEntry.setFrom(from);
            rangeEntry.setTo(to);
            range.put(fieldName, rangeEntry);
            boostingEntry.setRange(range);

            boostingEntryMap.put(tendency, boostingEntry);

            return this;
        }

        public Builder negativeBoost(Double boost) {
            boostingEntryMap.put("negative_boost",boost);

            return this;
        }

        public BoostingQuery build() {
            BoostingQuery boostingQuery = new BoostingQuery();
            Query query = new Query();
            query.setBoosting(boostingEntryMap);
            boostingQuery.setQuery(query);

            return boostingQuery;
        }
    }
}