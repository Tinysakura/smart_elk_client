package com.tinysakura.bean.document;

import com.tinysakura.constant.DocumentPropertiesConstant;

import java.util.Map;

/**
 * 对文档单个字段的描述
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/2
 */

public class Properties {
    /**
     * 共有属性
     */

    /**
     * 字段类型{@link DocumentPropertiesConstant.Type}
     * 数值类型特有的Type值{@link DocumentPropertiesConstant.Number.Type}
     */
    private String type;

    /**
     * 指定该字段的原始值是否会被写入索引{@link DocumentPropertiesConstant.Store}
     */
    private String store;

    /**
     * 指定该字段是否被写入索引以供搜索{@link DocumentPropertiesConstant.Index}
     */
    private String index;

    /**
     * 指定字段写入索引后的名称，若不指定则使用对象的原始名称
     */
    private String index_name;

    /**
     * 定义字段在文档的重要性，数值越高重要性越高
     */
    private double boost;

    /**
     * 此属性指定一个字段，字段的所有值都将复制到该指定字段
     */
    private String copy_to;

    /**
     * =============================================
     */

    /**
     * 字符串类型特有属性
     */

    /**
     * 是否需要计算该字段的Lucene词向量{@link DocumentPropertiesConstant.Strings.TermVector}
     */
    private String term_vector;

    /**
     * 是否禁用Lucene对该字段的加权基准计算
     */
    private boolean omit_norms;

    /**
     * 定义用于该字段的索引和搜索的分析器，若不指定则默认使用全局定义的分析器
     */
    private String analyzer;

    private String index_analyzer;

    private String serach_analyzer;

    /**
     * 指定该字段中内容字符的最大值，当超过最大值时分析器会将其忽略
     */
    private Long ignore_above;

    /**
     * =============================================
     */

    /**
     * 数值类型特有属性
     */

    /**
     * 指定字段中每个值生成的词条数，值越低产生的词条数越高（范围查询更快但索引稍大）
     * 日期类型的properties中也有该字段
     */
    private Integer precision_step;

    /**
     * 是否忽略格式错误的数值，默认为false即不忽略
     * 日期类型的properties中也有该字段
     */
    private boolean ignore_malformed;

    /**
     * =============================================
     */

    /**
     * 日期类型特有属性
     */

    /**
     * 指定日期格式，默认值为dateOptionalTime
     */
    private String format;

    /**
     * 多字段
     */
    private Map<String, Properties> fields;

}