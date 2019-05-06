package com.tinysakura.core.query.base;

import com.tinysakura.bean.query.Query;
import com.tinysakura.bean.query.entry.MoreKLikeEntry;
import lombok.Data;

/**
 * more_like_this查询
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
@Data
public class MoreLikeQuery {
    Query query;

    public static final class Builder{
        private MoreKLikeEntry moreKLikeEntry;

        public Builder() {
            this.moreKLikeEntry = new MoreKLikeEntry();
        }

        public Builder fields(String[] fields) {
            this.moreKLikeEntry.setFields(fields);

            return this;
        }

        public Builder likeText(String text) {
            this.moreKLikeEntry.setLike_text(text);

            return this;
        }

        /**
         * 指定文档至少需要多少百分比的词条与查询匹配才能人为是类似
         */
        public Builder percent(Double percent) {
            this.moreKLikeEntry.setPercent_terms_to_match(percent);

            return this;
        }

        /**
         * 指定文档中词条出现的最低词频，低于该词频的词条会被忽略
         */
        public Builder minTermFreq(Integer min) {
            this.moreKLikeEntry.setMin_term_freq(min);

            return this;
        }

        public Builder stopWords(String[] stopWords) {
            this.moreKLikeEntry.setStop_words(stopWords);

            return this;
        }

        /**
         * 词条至少在n个文档中出现
         */
        public Builder minDocFreq(Integer freq) {
            this.moreKLikeEntry.setMin_doc_freq(freq);

            return this;
        }

        /**
         * 词条至多在n个文档中出现
         */
        public Builder maxDocFreq(Integer freq) {
            this.moreKLikeEntry.setMax_doc_freq(freq);

            return this;
        }

        /**
         * 词条最小长度
         * @param length
         * @return
         */
        public Builder minWordLen(Integer length) {
            this.moreKLikeEntry.setMin_word_len(length);

            return this;
        }

        /**
         * 词条最小长度
         * @param length
         * @return
         */
        public Builder maxWordLen(Integer length) {
            this.moreKLikeEntry.setMax_word_len(length);

            return this;
        }

        public Builder boost(Double boost) {
            this.moreKLikeEntry.setBoost(boost);

            return this;
        }

        public Builder analyzer(String analyzer) {
            this.moreKLikeEntry.setAnalyzer(analyzer);

            return this;
        }

        public MoreLikeQuery build() {
            MoreLikeQuery moreLikeQuery = new MoreLikeQuery();
            Query query = new Query();
            query.setMore_like_this(moreKLikeEntry);
            moreLikeQuery.setQuery(query);

            return moreLikeQuery;
        }
    }
}