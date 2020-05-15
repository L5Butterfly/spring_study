package com.oxygen.v1.config;


import lombok.Data;

import java.util.ResourceBundle;

/**
 * 数据库的数据源配置属性注入映射
 * @author oxygen
 * @date 2020/5/14
 **/

@Data
public class DataSourceProperties {

    private final String name;
    private final String driverClassName;
    private final String url;
    private final String username;
    private final String password;

    //全局配置属性
    private String cacheEnable;
    private String plugsPath;
    private String mapperPath;


    /**
     * 数据源加载
     * @param name
     * @param driverClassName
     * @param url
     * @param username
     * @param password
     */
    public DataSourceProperties(String name, String driverClassName, String url, String username, String password) {
        this.name = name;
        this.driverClassName = driverClassName;
        this.url = url;
        this.username = username;
        this.password = password;
    }


    /**
     * ResourceBundle的方式加载配置文件
     * @param datasource
     */
    public DataSourceProperties(ResourceBundle datasource){
        //数据源相关
        this.name = datasource.getString("jdbc.name");
        this.driverClassName = datasource.getString("jdbc.driver");
        this.url = datasource.getString("jdbc.url");
        this.username = datasource.getString("jdbc.username");
        this.password = datasource.getString("jdbc.password");

        //缓存相关配置
        this.mapperPath=datasource.getString("cache.enabled");
        this.plugsPath=datasource.getString("plugin.path");
        this.mapperPath=datasource.getString("mapper.path");
    }

}
