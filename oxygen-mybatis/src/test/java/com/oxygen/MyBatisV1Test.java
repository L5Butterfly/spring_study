package com.oxygen;

import com.oxygen.v1.config.DataSourceProperties;
import com.oxygen.v1.core.GPConfiguration;
import com.oxygen.v1.core.GPExecutor;
import com.oxygen.v1.core.GPSqlSession;
import com.oxygen.v1.mapper.BlogMapper;
import org.junit.Test;

public class MyBatisV1Test {


    @Test
    public void myBatisV1(){
        GPConfiguration gpConfiguration = new GPConfiguration();
        DataSourceProperties dataSource = gpConfiguration.getDataSourceProperties();
        GPSqlSession sqlSession = new GPSqlSession(gpConfiguration, new GPExecutor(dataSource));
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.selectBlogById(1);
    }
}
