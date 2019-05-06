package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.FuzzyLikeEntry;
import lombok.Data;

/**
 * fuzzy_like_this查询，查询与文本相似的文档，利用模糊字符串并选择生成的最佳差分词条
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class FuzzyLikeQuery {
    Query query;

    public static final class Builder{
        private FuzzyLikeEntry fuzzyLikeEntry;

        public Builder() {
            this.fuzzyLikeEntry = new FuzzyLikeEntry();
        }

        public Builder fields(String[] fields) {
            this.fuzzyLikeEntry.setFields(fields);

            return this;
        }

        public Builder likeText(String text) {
            this.fuzzyLikeEntry.setLike_text(text);

            return this;
        }

        /**
         * 是否忽略词频
         * @param bool
         * @return
         */
        public Builder ignoreTF(Boolean bool) {
            this.fuzzyLikeEntry.setIgnore_if(bool);

            return this;
        }

        public Builder minSimilarity(Double min) {
            this.fuzzyLikeEntry.setMin_similarity(min);

            return this;
        }

        public Builder boost(Double boost) {
            this.fuzzyLikeEntry.setBoost(boost);

            return this;
        }

        public Builder analyzer(String analyzer) {
            this.fuzzyLikeEntry.setAnalyzer(analyzer);

            return this;
        }

        public FuzzyLikeEntry build() {
            FuzzyLikeQuery fuzzyLikeQuery = new FuzzyLikeQuery();
            Query query = new Query();
            query.setFuzzy_like_this(fuzzyLikeEntry);
            fuzzyLikeQuery.setQuery(query);

            return fuzzyLikeEntry;
        }
    }
}