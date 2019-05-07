package com.tinysakura.exception;

/**
 * 非法的节点异常
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/7
 */

public class IllegalNodeException extends RuntimeException {
    public IllegalNodeException() {
        super("节点结构异常");
    }
}