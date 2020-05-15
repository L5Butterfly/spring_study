package com.oxygen.mybatis.v1;

import com.oxygen.mybatis.v1.mapper.BlogMapper;

/**
 * @author:oxygen
 * @date 20200515
 */
public class MyBatisBoot {
    public static void main(String[] args) {
        GPSqlSession sqlSession = new GPSqlSession(new GPConfiguration(), new GPExecutor());
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.selectBlogById(1);
    }
}
