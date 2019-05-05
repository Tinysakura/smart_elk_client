package com.tinysakura.util;

/**
 * 字符串相关工具类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */

public class StringUtil {
    /**
     * 字符串首字母大写
     * @param str
     * @return
     */
    public static String upperCaseFirst(String str) {
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        return  str;
    }
}