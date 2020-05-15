package com.oxygen.mybatis.v1.mapper;

import com.oxygen.mybatis.v2.annotation.Entity;
import com.oxygen.mybatis.v2.annotation.Select;

/**
 * @author:oxygen
 */
@Entity(Blog.class)
public interface BlogMapper {
    /**
     * 根据主键查询文章
     * @param bid
     * @return
     */
    @Select("select * from blog where bid = ? ")
    public Blog selectBlogById(Integer bid);

}
