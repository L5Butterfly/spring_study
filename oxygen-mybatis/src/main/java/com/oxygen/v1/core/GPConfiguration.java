package com.oxygen.v1.core;

import com.oxygen.v1.config.DataSourceProperties;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 * 配置文件，加载配置信息
 * @author oxygen
 * @date 2020/5/14
 **/
public class GPConfiguration {

    /**
     * mapper接口映射的sql语句配置加载
     * 为了方便使用properties配置，不使用xml配置，xml还需要些单独的解析工具
     */
    private static final ResourceBundle sqlMappings;

    /**
     * 数据源的配置信息加载
     */
    private static final ResourceBundle dataSource;

    /**
     * 初始化配置文件数据,通过ResourceBundle实现，自定加载xxx.properties
     */
    static{
        sqlMappings = ResourceBundle.getBundle("v1sql");
        dataSource=ResourceBundle.getBundle("mybatis");
    }


    /**
     * 获取sql映射配置文件
     * @return
     */
    public  ResourceBundle getSqlMappings() {
        return sqlMappings;
    }


    /**
     * 获取数据源
     * @return
     */
    public  ResourceBundle getDataSource() {
        return dataSource;
    }


    /**
     * 获取数据源,通过配置类进行映射加载
     * @return
     */
    public DataSourceProperties getDataSourceProperties() {
        return new DataSourceProperties(dataSource);
    }


    /**
     * 生成接口方法的代理对象，JDK的动态代理方式，代理的对象实现 {@link InvocationHandler}接口
     *
     * @param clazz  目标对象，数据库操作接口
     * @param sqlSession  数据库操作JDBC的方法封装
     * @param <T>  实现InvocationHandler类的代理对象，执行invoke方法调用sqlSession里的数据库操作底层方法
     * @return
     */
    public <T> T getMapper(Class clazz, GPSqlSession sqlSession) {
        return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{clazz},
                new GPMapperProxy(sqlSession));
    }




    /**
     * Java动态代理InvocationHandler和Proxy学习笔记
     * https://blog.csdn.net/yaomingyang/article/details/80981004
     *
     *
     * 这个方法的作用就是创建一个代理类对象，它接收三个参数，我们来看下几个参数的含义
     * Proxy.newProxyInstance(参数1，参数2，参数3)
     * 参数1：(loader)：一个classloader对象，定义了由哪个classloader对象对生成的代理类进行加载
     *
     * 参数2：(interfaces)：一个interface对象数组，表示我们将要给我们的代理对象提供一组什么样的接口，如果我们提供了这样一个接口对象数组，
     * 那么也就是声明了代理类实现了这些接口，代理类就可以调用接口中声明的所有方法。
     *
     * 参数2：(h)：一个InvocationHandler对象，表示的是当动态代理对象调用方法的时候会关联到哪一个InvocationHandler对象上，并最终由其调用。
     */
    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h){
        return null;
    }


}
