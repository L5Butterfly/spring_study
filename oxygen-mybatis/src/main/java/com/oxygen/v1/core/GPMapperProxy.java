package com.oxygen.v1.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * GPMapperProxy,Mapper接口的代理对象
 * @author oxygen
 * @date 2020/5/14
 **/
public class GPMapperProxy implements InvocationHandler {

    /**
     * SqlSession
     */
    private GPSqlSession sqlSession;


    /**
     * Mapper代理构造传入 {@link  GPSqlSession }
     * @param sqlSession
     */
    public GPMapperProxy(GPSqlSession sqlSession){
        this.sqlSession = sqlSession;
    }


    /**
     * JDK的动态代理：
     * 每一个动态代理类的调用处理程序都必须实现InvocationHandler接口，并且每个代理类的实例都关联到了实现该接口的动态代理类调用处理程序中，、
     * 当我们通过动态代理对象调用一个方法时候，这个方法的调用就会被转发到实现InvocationHandler接口类的invoke方法来调用。
     *
     * @param proxy  proxy:代理类代理的真实代理对象com.sun.proxy.$Proxy0
     * @param method method:我们所要调用某个对象真实的方法的Method对象
     * @param args  args:指代代理对象方法传递的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mapperInterface = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String statementId = mapperInterface + "." + methodName;
        if(methodName.contains("update")){
            return sqlSession.update(statementId, args);
        }else if(methodName.contains("delete")){
            return sqlSession.delete(statementId, args[0]);
        }else if(methodName.contains("insert")){
            return sqlSession.insert(statementId, args);
        }else{
            return sqlSession.selectOne(statementId, args[0]);
        }
    }
}
