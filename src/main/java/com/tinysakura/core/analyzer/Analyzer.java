package com.tinysakura.core.analyzer;

import lombok.Data;

import java.util.ArrayList;

/**
 * 自定义分析器建造类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */
@Data
public class Analyzer {
    private String name;

    private com.tinysakura.bean.analyzer.Analyzer analyzer;

    public static final class Builder {
        private String name;

        private com.tinysakura.bean.analyzer.Analyzer analyzer;

        private ArrayList<String> filter;

        public Builder() {
            analyzer = new com.tinysakura.bean.analyzer.Analyzer();
            filter = new ArrayList<String>();
        }

        public Builder name(String name) {
            this.name = name;

            return this;
        }

        /**
         * 指定分词器
         * @param tokenizer
         * @return
         */
        public Builder tokenizer(String tokenizer) {
            this.analyzer.setTokenizer(tokenizer);

            return this;
        }

        public Builder filter(String filterName) {
            filter.add(filterName);

            return this;
        }

        public Analyzer build() {
            Analyzer analyzer = new Analyzer();

            analyzer.setName(this.name);
            this.analyzer.setFilter(filter.toArray(new String[]{}));
            analyzer.setAnalyzer(this.analyzer);

            return analyzer;
        }
    }

}