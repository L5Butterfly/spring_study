package com.oxygen.mybatis.v2.binding;

import com.oxygen.mybatis.v2.session.DefaultSqlSession;
import java.lang.reflect.Proxy;

/**
 * 用于产生MapperProxy代理类
 * 使用了设计模式的工厂模式
 *
 * @param <T>  泛型的Mapper接口对象
 * @author:oxygen
 * @date 20200515
 */
public class MapperProxyFactory<T>{

    //Mybatis的Mapper的接口类
    private Class<T> mapperInterface;

    //Mapper接口的方法执行返回POJO对象
    private Class object;


    /**
     * MapperProxyFactory 代理的工厂类
     * @param mapperInterface
     * @param object
     */
    public MapperProxyFactory(Class<T> mapperInterface, Class object) {
        this.mapperInterface = mapperInterface;
        this.object = object;
    }


    /**
     * MapperProxy代理对象实例创建
     * @param sqlSession
     * @return
     */
    public T newInstance(DefaultSqlSession sqlSession) {
        //1.类加载器；2：Mapper接口；3：触发代理类
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, new MapperProxy(sqlSession, object));
    }
}
