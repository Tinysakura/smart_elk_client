package com.tinysakura.core.query.filter;

import com.tinysakura.bean.query.entry.composite.RangeEntry;
import com.tinysakura.bean.query.entry.filter.FieldEntry;
import com.tinysakura.bean.query.entry.filter.FilterEntry;
import com.tinysakura.bean.query.entry.filter.IdsEntry;
import com.tinysakura.bean.query.entry.filter.ScriptEntry;
import com.tinysakura.exception.NotAppointFieldsException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/7
 */
@Data
public class FilterQuery {
    FilterEntry filterEntry;

    public FilterEntry getFilter() {
        return filterEntry;
    }

    public static final class Builder {
        private FilterEntry filterEntry;
        private ScriptEntry scriptEntry;
        private IdsEntry idsEntry;
        private String fieldName;

        public Builder() {
            this.filterEntry = new FilterEntry();
        }

        public Builder field(String fieldName) {
            this.fieldName = fieldName;

            return this;
        }

        public Builder value(Object value) {
            if (fieldName == null) {
                throw new NotAppointFieldsException();
            }

            Map<String, Object> term = new HashMap<>(1);
            term.put(fieldName, value);
            filterEntry.setTerm(term);

            return this;
        }

        public Builder range(Object from, Object to) {
            if (fieldName == null) {
                throw new NotAppointFieldsException();
            }

            Map<String, RangeEntry> range = new HashMap<>(1);
            RangeEntry rangeEntry = new RangeEntry();
            rangeEntry.setFrom(from);
            rangeEntry.setTo(to);
            range.put(fieldName, rangeEntry);
            filterEntry.setRange(range);

            return this;
        }

        /**
         * 过滤指定字段为空的文档
         * @param fieldName
         * @return
         */
        public Builder fieldExists(String fieldName) {
            FieldEntry fieldEntry = new FieldEntry();
            fieldEntry.setField(fieldName);
            filterEntry.setExists(fieldEntry);

            return this;
        }

        /**
         * 过滤指定字段有值的文档
         * @param fieldName 字段名
         * @param nullValue 应当被视为空的值
         * @return
         */
        public Builder fieldMissing(String fieldName, Object nullValue) {
            FieldEntry fieldEntry = new FieldEntry();
            fieldEntry.setField(fieldName);
            fieldEntry.setNull_value(nullValue);
            filterEntry.setMissing(fieldEntry);

            return this;
        }

        /**
         * 设置过滤脚本
         * @param script
         * @return
         */
        public Builder script(String script) {
            if (scriptEntry == null) {
                scriptEntry = new ScriptEntry();
            }

            scriptEntry.setScript(script);
            filterEntry.setScript(scriptEntry);

            return this;
        }

        /**
         * 设置过滤脚本使用的变量
         * Map<String, Object> String:变量名 Object:变量值
         * @param params
         * @return
         */
        public Builder scriptParams(Map<String, Object> params) {
            if (scriptEntry == null) {
                scriptEntry = new ScriptEntry();
            }

            scriptEntry.setParams(params);

            return this;
        }

        /**
         * 限制文档类型
         * @param type
         * @return
         */
        public Builder documentType(String type) {
            Map<String, String> map = new HashMap<>(1);
            map.put("value", type);
            filterEntry.setType(map);

            return this;
        }

        /**
         * 限制单个分片返回的文档数量
         * @param limitNumber
         * @return
         */
        public Builder shardsLimit(Integer limitNumber) {
            Map<String, Integer> map = new HashMap<>(1);
            map.put("value", limitNumber);
            filterEntry.setLimit(map);

            return this;
        }

        /**
         * 过滤标识符时指定文档类型
         * @param types
         * @return
         */
        public Builder types(String[] types) {
            if (idsEntry == null) {
                idsEntry = new IdsEntry();
            }

            idsEntry.setType(types);

            return this;
        }

        /**
         * 根据具体文档唯一标识符过滤文档
         * @param ids
         * @return
         */
        public Builder ids(String[] ids) {
            if (idsEntry == null) {
                idsEntry = new IdsEntry();
            }

            idsEntry.setValues(ids);
            filterEntry.setIds(idsEntry);

            return this;
        }

        public FilterQuery build() {
            FilterQuery filterQuery = new FilterQuery();
            filterQuery.setFilterEntry(this.filterEntry);

            return filterQuery;
        }
    }
}