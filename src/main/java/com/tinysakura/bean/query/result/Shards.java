package com.tinysakura.bean.query.result;

import lombok.Data;

/**
 * 返回结果的分片信息bean
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */
@Data
public class Shards {
    /**
     * 使用的分片数目
     */
    Integer total;

    /**
     * 检索成功的数目
     */
    Integer successful;

    /**
     * 检索失败的数目
     */
    Integer failed;
}