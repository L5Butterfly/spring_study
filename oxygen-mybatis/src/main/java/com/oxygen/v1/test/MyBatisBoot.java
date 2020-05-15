package com.oxygen.v1.test;


import com.oxygen.v1.config.DataSourceProperties;
import com.oxygen.v1.core.GPConfiguration;
import com.oxygen.v1.core.GPExecutor;
import com.oxygen.v1.core.GPSqlSession;
import com.oxygen.v1.mapper.BlogMapper;

/**
 * MyBatisBoot
 * @author oxygen
 * @date 2020/5/14
 **/
public class MyBatisBoot {
    public static void main(String[] args) {
        GPConfiguration gpConfiguration = new GPConfiguration();
        DataSourceProperties dataSource = gpConfiguration.getDataSourceProperties();
        GPSqlSession sqlSession = new GPSqlSession(gpConfiguration, new GPExecutor(dataSource));
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.selectBlogById(1);
        //blogMapper.deleteBlogById(1);
        //blogMapper.insertBlog("哈哈哈",18);
        //blogMapper.updateBlogById("XXX",2);
    }
}
