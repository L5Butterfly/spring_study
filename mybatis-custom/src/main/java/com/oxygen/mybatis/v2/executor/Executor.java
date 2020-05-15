package com.oxygen.mybatis.v2.executor;

/**
 * 基础的执行器执行的方法，自己根据需求扩展
 * @author:oxygen
 * @date 20209514
 */
public interface Executor {


    /**
     * 查询方法
     * @param statement
     * @param parameter
     * @param pojo
     * @param <T>
     * @return
     */
    <T>T query(String statement, Object[] parameter, Class pojo);
}
