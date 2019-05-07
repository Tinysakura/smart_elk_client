package com.tinysakura.core.query.sort;

import com.tinysakura.bean.query.entry.sort.ScriptEntry;
import com.tinysakura.bean.query.entry.sort.SortEntry;
import com.tinysakura.constant.QueryConstant;
import com.tinysakura.exception.IllegalNodeException;
import com.tinysakura.exception.NotAppointFieldsException;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/7
 */
@Data
public class SortQuery {
    private Map<String, SortEntry>[] fieldSort;

    private SortEntry scriptSort;

    public static final class Builder{
        private List<Map<String, SortEntry>> sortEntryMapList;
        private String fieldName;
        private ScriptEntry scriptEntry;

        public Builder() {
            this.sortEntryMapList = new ArrayList<>();
        }

        public Builder field(String fieldName) {
            if (scriptEntry != null) {
                throw new IllegalNodeException();
            }

            this.fieldName = fieldName;

            return this;
        }

        private Builder hidAsc(Object missing) {
            if (fieldName == null) {
                throw new NotAppointFieldsException();
            }

            SortEntry sortEntry = new SortEntry();

            sortEntry.setOrder(QueryConstant.Order.ASC);
            sortEntry.setMissing(missing);

            Map<String, SortEntry> sortEntryMap = new HashMap<>(1);
            sortEntryMap.put(fieldName, sortEntry);
            sortEntryMapList.add(sortEntryMap);

            return this;
        }

        /**
         * 升序排序
         * @param missing 指定字段为空时的行为{@link com.tinysakura.constant.QueryConstant.Order}
         * @return
         */
        public Builder asc(String missing) {
            hidAsc(missing);

            return this;
        }

        /**
         * 升序排序
         * @param missing 指定字段为空时的文档的得分
         * @return
         */
        public Builder asc(Integer missing) {
            hidAsc(missing);

            return this;
        }


        private Builder hidDesc(Object missing) {
            if (fieldName == null) {
                throw new NotAppointFieldsException();
            }

            SortEntry sortEntry = new SortEntry();

            sortEntry.setOrder(QueryConstant.Order.DESC);
            sortEntry.setMissing(missing);

            Map<String, SortEntry> sortEntryMap = new HashMap<>(1);
            sortEntryMap.put(fieldName, sortEntry);
            sortEntryMapList.add(sortEntryMap);

            return this;
        }

        /**
         * 升序排序
         * @param missing 指定字段为空时的行为{@link com.tinysakura.constant.QueryConstant.Order}
         * @return
         */
        public Builder desc(String missing) {
            hidDesc(missing);

            return this;
        }

        /**
         * 升序排序
         * @param missing 指定字段为空时的文档的得分
         * @return
         */
        public Builder desc(Integer missing) {
            hidDesc(missing);

            return this;
        }

        /**
         * 脚本排序
         * @param script 脚本内容
         * @param order 排序规则 {@link com.tinysakura.constant.QueryConstant.Order}
         * @param type 用于比较的参数类型 {@link com.tinysakura.constant.DocumentPropertiesConstant.Type}
         * @return
         */
        public Builder script(String script, String order, String type) {
            if (fieldName != null) {
                throw new IllegalNodeException();
            }

            this.scriptEntry = new ScriptEntry();
            scriptEntry.setScript(script);
            scriptEntry.setOrder(order);
            scriptEntry.setType(type);

            return this;
        }

        public SortQuery build() {
            SortQuery sortQuery = new SortQuery();

            if (scriptEntry == null) {
                sortQuery.setFieldSort(sortEntryMapList.toArray(new HashMap[]{}));
            } else {
                SortEntry sortEntry = new SortEntry();
                sortEntry.set_script(scriptEntry);
                sortQuery.setScriptSort(sortEntry);
            }

            return sortQuery;
        }
    }
}