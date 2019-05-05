package com.tinysakura.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组相关工具类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/5
 */

public class ArrayUtil {
    /**
     * 移除数组中指定类型的成员
     * @param clazz
     * @param array
     */
    public static Object[] removeAppointType(Class clazz, Object[] array) {
        List<Object> list = new ArrayList<Object>();

        for (Object o : array) {
            if (!o.getClass().equals(clazz)) {
                list.add(o);
            }
        }

        return list.toArray(new Object[]{});
    }

    /**
     * 提取数组中所有成员的Class类型
     * @param array
     * @return
     */
    public static Class[] convert2ClassArray(Object[] array) {
        Class[] classes = new Class[array.length];

        for (int i = 0; i < array.length; i++) {
            classes[i] = array[i].getClass();
        }

        return classes;
    }


}