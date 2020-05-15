package com.oxygen.mybatis.v2.session;

import com.oxygen.mybatis.v2.executor.Executor;

/**
 * MeBatis的API，提供给应用层使用
 * @author:oxygen
 * @date 20200515
 *
 */
public class DefaultSqlSession {

    /**
     * 配置文件的加载解析类
     */
    private Configuration configuration;

    /**
     * 执行DB的执行器，单一职责原则
     */
    private Executor executor;


    /**
     * 构造方式赋值属性
     * @param configuration
     */
    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        // 根据全局配置决定是否使用缓存装饰
        this.executor = configuration.newExecutor();
    }


    /**
     * 获取配置文件解析类
     * @return
     */
    public Configuration getConfiguration() {
        return configuration;
    }


    /**
     * 获取Mapper类的代理对象
     * @param clazz  被代理的原始Mapper接口类
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz){
        System.out.println("需要代理的原始对象："+clazz.getName());
        T mapper = configuration.getMapper(clazz, this);
        System.out.println("JDK动态代理生成的代理类："+ mapper.getClass());
        return mapper;
    }


    /**
     * 操作数据库的最外层接口方法
     * @param statement
     * @param parameter
     * @param pojo
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statement, Object[] parameter, Class pojo)  {
        //获取解析生成的SQL语句
        String sql = getConfiguration().getMappedStatement(statement);
        // 打印代理对象时会自动调用toString()方法，触发invoke()
        return executor.query(sql, parameter, pojo);
    }
}
