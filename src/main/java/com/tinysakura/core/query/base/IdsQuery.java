package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.DocumentIdsEntry;
import lombok.Data;

/**
 * 根据文档唯一标识符查询
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class IdsQuery {
    private Query query;

    public static final class Builder{
        private DocumentIdsEntry documentIdsEntry;

        public Builder() {
            this.documentIdsEntry = new DocumentIdsEntry();
        }

        public Builder documentIds(String[] ids) {
            this.documentIdsEntry.setValues(ids);

            return this;
        }

        public Builder documentType(String type) {
            this.documentIdsEntry.setType(type);

            return this;
        }

        public IdsQuery build() {
            IdsQuery idsQuery = new IdsQuery();
            Query query = new Query();
            query.setIds(documentIdsEntry);
            idsQuery.setQuery(query);

            return idsQuery;
        }
    }
}