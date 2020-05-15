package com.oxygen.mybatis.v2.binding;

import com.oxygen.mybatis.v2.session.DefaultSqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * 维护接口和工厂类的关系，用于获取MapperProxy代理对象
 * 工厂类指定了POJO类型，用于处理结果集返回
 * @author:oxygen
 * @date 20200515
 */
public class MapperRegistry {

    /**
     * 接口和工厂类映射关系
     * key: Mapper接口
     * value: MapperProxyFactory
     */
    private final Map<Class<?>, MapperProxyFactory> knownMappers = new HashMap<>();


    /**
     * 在Configuration中解析接口上的注解时，存入接口和工厂类的映射关系
     * 此处传入pojo类型，是为了最终处理结果集的时候将结果转换为POJO类型
     *
     * @param clazz   泛型的Mapper接口
     * @param pojo  接口方法的返回结果对象
     * @param <T>
     */
    public <T> void addMapper(Class<T> clazz, Class pojo){
        knownMappers.put(clazz, new MapperProxyFactory(clazz, pojo));
    }


    /**
     * 创建一个Mapper的代理对象
     * @param clazz
     * @param sqlSession
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz, DefaultSqlSession sqlSession) {

        //创建MapperProxy代理类的工厂
        MapperProxyFactory proxyFactory = knownMappers.get(clazz);
        if (proxyFactory == null) {
            throw new RuntimeException("Type: " + clazz + " can not find");
        }
        //创建返回xxxMapper的代理类对象
        return (T)proxyFactory.newInstance(sqlSession);
    }

}
