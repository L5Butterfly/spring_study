package com.oxygen.mybatis.v2.mapper;

import com.oxygen.mybatis.v2.annotation.Entity;
import com.oxygen.mybatis.v2.annotation.Select;

/**
 * 数据库相关操作的Mapper接口
 * @author:oxygen
 * @date 20200515
 */
@Entity(Blog.class)
public interface BlogMapper {
    /**
     * 根据主键查询文章
     * @param bid
     * @return
     */
    @Select("select * from blog where bid = ?")
    Blog selectBlogById(Integer bid);

}
