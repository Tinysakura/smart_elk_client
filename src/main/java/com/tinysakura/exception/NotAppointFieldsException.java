package com.tinysakura.exception;

/**
 * 未指定查询字段异常
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/6
 */
public class NotAppointFieldsException extends RuntimeException {
    public NotAppointFieldsException(String message) {
        super(message);
    }

    public NotAppointFieldsException() {
        super("未指定文档字段");
    }
}