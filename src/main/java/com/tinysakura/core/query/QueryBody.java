package com.tinysakura.core.query;

import com.tinysakura.bean.query.Partial;
import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.filter.FilterEntry;
import com.tinysakura.bean.query.entry.sort.SortEntry;
import com.tinysakura.constant.QueryConstant;
import com.tinysakura.util.StringUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * QueryBody构造类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class QueryBody {

    private com.tinysakura.bean.query.QueryBody queryBody;

    private String index;

    private String documentType;

    /**
     * 搜索执行偏好{@link com.tinysakura.constant.QueryConstant.SearchPreference}
     */
    private String searchPreference;

    /**
     * 搜索类型{@link com.tinysakura.constant.QueryConstant.SearchType}
     */
    private String searchType;

    public static final class Builder{
        private com.tinysakura.bean.query.QueryBody queryBody;

        private String index;

        private String searchPreference;

        private String documentType;

        private String searchType;

        private List<String> fieldsList;

        private Map<String, Partial> partialMap;

        public Builder() {
            this.queryBody = new com.tinysakura.bean.query.QueryBody();
        }

        /**
         * 指定在哪个索引上查询
         * @param index
         * @return
         */
        public Builder index(String index) {
            this.index = index;

            return this;
        }

        /**
         * 指定查询文档类型
         * @param documentType
         * @return
         */
        public Builder documentType(String documentType) {
            this.documentType = documentType;

            return this;
        }

        public Builder searchPreference(String searchPreference) {
            if (this.searchPreference == null || !this.searchPreference.startsWith("_shards")) {
                this.searchPreference = searchPreference;
            } else {
                this.searchPreference = this.searchType.concat(";").concat(searchPreference);
            }

            return this;
        }

        /**
         * 只在指定的分片上执行
         * @param shards
         * @return
         */
        public Builder onlyShards(String[] shards) {
            this.searchPreference = QueryConstant.SearchPreference.SHARDS.concat(StringUtil.jointStringArrayWithRegex(shards, ","));

            return this;
        }

        @Deprecated
        public Builder onlyNode(String nodeId) {
            this.searchPreference = QueryConstant.SearchPreference.ONLY_NODE.concat(nodeId);

            return this;
        }

        @Deprecated
        public Builder preferNode(String nodeId) {
            this.searchPreference = QueryConstant.SearchPreference.PREFER_NODE.concat(nodeId);

            return this;
        }

        public Builder searchType(String searchType) {
            this.searchType = searchType;

            return this;
        }

        /**
         * 指定起始文档
         * @param from
         * @return
         */
        public Builder from(Integer from) {
            queryBody.setFrom(from);

            return this;
        }

        /**
         * 指定返回文档数
         * @param size
         * @return
         */
        public Builder size(Integer size) {
            queryBody.setSize(size);

            return this;
        }

        public Builder version(boolean enabled) {
            queryBody.setVersion(enabled);

            return this;
        }

        /**
         * 指定文档的最小得分
         * @param minScore
         * @return
         */
        public Builder minScore(float minScore) {
            queryBody.setMin_score(minScore);

            return this;
        }

        /**
         * 指定返回文档应该包含的字段
         * @param fields
         * @return
         */
        public Builder fields(String fields) {
            if (fieldsList == null) {
                fieldsList = new ArrayList<String>();
            }

            fieldsList.add(fields);

            return this;
        }

        /**
         * 指定返回文档应该包含的字段的条件
         * @param partialName
         * @param include
         * @return
         */
        public Builder include(String partialName, String[] include) {
            if (partialMap == null) {
                partialMap = new HashMap<String, Partial>(16);
            }

            if (partialMap.get(partialName) == null) {
                partialMap.put(partialName, new Partial());
            }

            partialMap.get(partialName).setInclude(include);

            return this;
        }

        /**
         * 指定返回文档不应该包含的字段的条件
         * @param partialName
         * @param exclude
         * @return
         */
        public Builder exclude(String partialName, String[] exclude) {
            if (partialMap == null) {
                partialMap = new HashMap<String, Partial>(16);
            }

            if (partialMap.get(partialName) == null) {
                partialMap.put(partialName, new Partial());
            }

            partialMap.get(partialName).setExclude(exclude);

            return this;
        }

        /**
         * 指定查询体进行查询(包含基本查询与复合查询)
         * @param query
         * @return
         */
        public Builder query(Query query) {
            this.queryBody.setQuery(query);

            return this;
        }

        /**
         * 指定查询过滤器
         * @param filterEntry
         * @return
         */
        public Builder filter(FilterEntry filterEntry) {
            this.queryBody.setPost_filter(filterEntry);

            return this;
        }

        /**
         * 指定排序规则（按字段）
         * @param sort
         * @return
         */
        public Builder sort(Object sort) {
            this.queryBody.setSort(sort);

            return this;
        }

        public QueryBody build() {
            if (partialMap != null) {
                this.queryBody.setPartial_fields(partialMap);
            }

            if (fieldsList != null) {
                this.queryBody.setStored_fields(fieldsList.toArray(new String[]{}));
            }

            QueryBody queryBody = new QueryBody();
            queryBody.setQueryBody(this.queryBody);
            queryBody.setIndex(this.index);
            queryBody.setDocumentType(this.documentType);
            queryBody.setSearchType(this.searchType);

            return queryBody;
        }
    }
}