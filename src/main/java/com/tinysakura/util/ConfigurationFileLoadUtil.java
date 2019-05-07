package com.tinysakura.util;

import org.ho.yaml.Yaml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * 配置文件读取根据类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */
public class ConfigurationFileLoadUtil {

    /**
     * 读取yml格式的application配置文件
     * @param fileName
     * @return
     */
    public static HashMap loadApplicationConfiguration4Yaml(String fileName) {
        HashMap loadMap = null;

        try {
            loadMap = Yaml.loadType(ConfigurationFileLoadUtil.class.getClassLoader().getResourceAsStream(fileName), HashMap.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return loadMap;
    }

    /**
     * 读取properties格式的application配置文件
     * @return
     */
    public static Properties loadApplicationConfiguration4Properties() {
        Properties properties = new Properties();

        try {
            properties.load(ConfigurationFileLoadUtil.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}