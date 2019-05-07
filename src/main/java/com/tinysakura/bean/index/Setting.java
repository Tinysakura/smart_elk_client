package com.tinysakura.bean.index;

import lombok.Data;

import java.util.Map;

/**
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */
@Data
public class Setting {
    /**
     * 分片数量
     */
    private Integer number_of_shards;

    /**
     * 副本数量
     */
    private Integer number_of_replicas;

    /**
     * 索引创建时间
     */
    private Long creation_date;

    private String uuid;

    private String providedName;

    private Map<String, String> version;

    /**
     * 自定义分析器
     */
    private SettingIndex index;
}