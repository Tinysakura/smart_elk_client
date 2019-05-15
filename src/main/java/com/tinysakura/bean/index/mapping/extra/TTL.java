package com.tinysakura.bean.index.mapping.extra;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * _ttl指定了文档的生命周期，周期结束后文档会被自动删除
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */
@Data
public class TTL {
    /**
     * 是否启用_ttl字段
     */
    private String enabled;

    /**
     * 默认过期时间，如果需要30天后过期则 "default" : "30d"
     */
    @SerializedName(value = "default")
    private String defaults;
}