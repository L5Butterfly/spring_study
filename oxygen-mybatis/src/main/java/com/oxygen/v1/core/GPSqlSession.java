package com.oxygen.v1.core;

/**
 * GPSqlSession封装了操作数据库的方法API,selectOne等等
 * @author oxygen
 * @date 2020/5/14
 **/
public class GPSqlSession {

    /**
     * 配置信息
     */
    private GPConfiguration configuration;


    /**
     * 执行器
     */
    private GPExecutor executor;


    /**
     * 构造方式属性赋值
     * @param configuration
     * @param executor
     */
    public GPSqlSession(GPConfiguration configuration, GPExecutor executor){
        this.configuration = configuration;
        this.executor = executor;
    }


    /**
     * 查询数据的方法
     * @param statementId  根据statementId拿到SQL，namespace+methodName
     * @param parameter  参数
     * @param <T> 方法返回的对象
     * @return
     */
    public <T> T selectOne(String statementId, Object parameter){
        //TODO:使用map集合缓存
        String sql = configuration.getSqlMappings().getString(statementId);
        if(null != sql && !"".equals(sql)){
            //调用执行器的查询方法
            return executor.query(sql, parameter);
        }
        return null;
    }


    /**
     * 更新数据
     * @param statementId
     * @param parameter
     * @return
     */
    public int update(String statementId, Object parameter){
        String sql = configuration.getSqlMappings().getString(statementId);
        if(!"".equals(sql)){
            return executor.update(sql, parameter);
        }
        return 0;
    }


    /**
     * 删除数据
     * @param statementId
     * @param parameter
     * @return
     */
    public int delete(String statementId, Object parameter){
        String sql = configuration.getSqlMappings().getString(statementId);
        if(!"".equals(sql)){
            return executor.delete(sql, parameter);
        }
        return 0;
    }


    /**
     * 数据插入操作
     * @param statementId
     * @param parameter
     * @return
     */
    public Object insert(String statementId, Object parameter) {
        String sql = configuration.getSqlMappings().getString(statementId);
        if(!"".equals(sql)){
            return executor.insert(sql, parameter);
        }
        return 0;
    }


    /**
     * 获取Mapper-代理对象
     * @param clazz   数据操作的接口
     * @param <T> sqlSession操作数据库的API
     * @return
     */
    public <T> T getMapper(Class clazz){
        return configuration.getMapper(clazz, this);
    }
}
