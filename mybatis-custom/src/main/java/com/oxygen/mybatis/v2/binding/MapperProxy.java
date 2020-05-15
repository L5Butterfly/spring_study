package com.oxygen.mybatis.v2.binding;

import com.oxygen.mybatis.v2.session.DefaultSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * MapperProxy代理类，用于代理Mapper接口
 * MapperProxy-JDK动态代理触发类
 * 实现InvocationHandler接口调用invoke方法触发被代理对象的执行方法
 * @author:oxygen
 * @date 20200515
 */
public class MapperProxy implements InvocationHandler{

    /**
     * 默认的sqlSession实现
     */
    private DefaultSqlSession sqlSession;

    /**
     * 接口查询结果的POJO对象，用于结果集的映射处理
     */
    private Class object;


    /**
     * 构造方式MapperProxy属性赋值
     * @param sqlSession
     * @param object
     */
    public MapperProxy(DefaultSqlSession sqlSession, Class object) {
        this.sqlSession = sqlSession;
        this.object = object;
    }

    /**
     * 所有Mapper接口的方法调用都会走到这里
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mapperInterface = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String statementId = mapperInterface + "." + methodName;
        // 如果根据接口类型+方法名能找到映射的SQL，则执行SQL
        if (sqlSession.getConfiguration().hasStatement(statementId)) {
            return sqlSession.selectOne(statementId, args, object);
        }
        // 否则直接执行被代理对象的原方法
        return method.invoke(proxy, args);
    }
}
