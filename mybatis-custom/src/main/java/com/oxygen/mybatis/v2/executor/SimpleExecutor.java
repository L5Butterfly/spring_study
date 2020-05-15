package com.oxygen.mybatis.v2.executor;

/**
 * SimpleExecutor 操作JDBC底层方法的执行器
 * @author:oxygen
 * @date 20200515
 */
public class SimpleExecutor implements Executor {

    @Override
    public <T> T query(String statement, Object[] parameter, Class pojo) {

        //封装JDBC Statement，用于操作数据库
        StatementHandler statementHandler = new StatementHandler();
        return statementHandler.query(statement, parameter, pojo);
    }
}
