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

    public static String jointStringArrayWithRegex(String[] array, String regex) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < array.length - 1; i++) {
            sb.append(array[i]).append(regex);
        }

        sb.append(array[array.length - 1]);

        return sb.toString();
    }
}