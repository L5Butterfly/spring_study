package com.oxygen.mybatis.v2.executor;

import com.oxygen.mybatis.v2.cache.CacheKey;
import java.util.HashMap;
import java.util.Map;

/**
 * CachingExecutor：带缓存的执行器，用于装饰基本执行器
 * SimpleExecutor：执行器是操作数据库的执行器
 * @author:oxygen
 * @date 20209514
 */
public class CachingExecutor implements Executor {

    /**
     * 委托别的执行器执行操作
     */
    private Executor delegate;


    /**
     * 数据查询缓存的容器---（只针对查询使用）
     * key ：sql查询的唯一标识(sql+参数)-----采用hash算法加特殊质数的算法，减少hash碰撞
     * values : 数据库执行的查询的结果
     */
    private static final Map<Integer, Object> cache = new HashMap<>();


    /**
     * 构造方式传入SimpleExecutor调用数据库的执行器
     * @param delegate
     */
    public CachingExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> T query(String statement, Object[] parameter, Class pojo)  {
        // 计算CacheKey
        CacheKey cacheKey = new CacheKey();
        cacheKey.update(statement);
        cacheKey.update(joinStr(parameter));
        // 是否拿到缓存
        if (cache.containsKey(cacheKey.getCode())) {
            // 命中缓存
            System.out.println("【命中缓存】");
            return (T)cache.get(cacheKey.getCode());
        }else{
            // 没有的话调用被装饰的SimpleExecutor从数据库查询
            Object obj = delegate.query(statement, parameter, pojo);
            cache.put(cacheKey.getCode(), obj);
            return (T)obj;
        }
    }

    // 为了命中缓存，把Object[]转换成逗号拼接的字符串，因为对象的HashCode都不一样
    public String joinStr(Object[] objs){
        StringBuffer sb = new StringBuffer();
        if(objs !=null && objs.length>0){
            for (Object objStr : objs) {
                sb.append(objStr.toString() + ",");
            }
        }
        int len = sb.length();
        if(len >0){
            sb.deleteCharAt(len-1);
        }
        return  sb.toString();
    }
}
