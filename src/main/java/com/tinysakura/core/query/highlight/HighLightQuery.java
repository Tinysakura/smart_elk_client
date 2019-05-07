package com.tinysakura.core.query.highlight;

import com.tinysakura.bean.query.entry.highlight.FieldEntry;
import com.tinysakura.bean.query.entry.highlight.HighLightEntry;
import com.tinysakura.exception.NotAppointFieldsException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 高亮查询结果
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/7
 */
@Data
public class HighLightQuery {
    private HighLightEntry highLightEntry;

    public static final class Builder{
        private HighLightEntry highLightEntry;
        private Map<String, FieldEntry> fieldTagsMap;
        private String fieldName;

        public Builder() {
            this.highLightEntry = new HighLightEntry();
        }

        public Builder globalPreTags(String[] globalPreTags) {
            this.highLightEntry.setPre_tags(globalPreTags);

            return this;
        }

        public Builder gloabalPostTags(String[] globalPostTags) {
            this.highLightEntry.setPost_tags(globalPostTags);

            return this;
        }

        public Builder field(String fieldName) {
            this.fieldName = fieldName;

            return this;
        }

        public Builder preTags(String[] preTags) {
            if (fieldName == null) {
                throw new NotAppointFieldsException();
            }

            if (fieldTagsMap == null) {
                fieldTagsMap = new HashMap<>(16);
            }

            if (fieldTagsMap.containsKey(fieldName)) {
                fieldTagsMap.get(fieldName).setPre_tags(preTags);
            } else {
                FieldEntry fieldEntry = new FieldEntry();
                fieldEntry.setPre_tags(preTags);
                fieldTagsMap.put(fieldName, fieldEntry);
            }

            return this;
        }

        public Builder postTags(String[] postTags) {
            if (fieldName == null) {
                throw new NotAppointFieldsException();
            }

            if (fieldTagsMap == null) {
                fieldTagsMap = new HashMap<>(16);
            }

            if (fieldTagsMap.containsKey(fieldName)) {
                fieldTagsMap.get(fieldName).setPost_tags(postTags);
            } else {
                FieldEntry fieldEntry = new FieldEntry();
                fieldEntry.setPost_tags(postTags);
                fieldTagsMap.put(fieldName, fieldEntry);
            }

            return this;
        }

        public HighLightQuery build() {
            HighLightQuery highLightQuery = new HighLightQuery();
            if (fieldTagsMap != null) {
                highLightEntry.setFields(fieldTagsMap);
            }
            highLightQuery.setHighLightEntry(highLightEntry);

            return highLightQuery;
        }
    }
}