package com.tinysakura.bean.routing;

import lombok.Data;

/**
 * 建立映射路由节点bean
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */
@Data
public class Routing {
    /**
     * 是否启用路由
     */
    private boolean required;

    /**
     * 路由计算字段
     */
    private String path;
}