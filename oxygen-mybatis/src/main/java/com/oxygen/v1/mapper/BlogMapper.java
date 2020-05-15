package com.oxygen.v1.mapper;

import com.oxygen.v1.domain.Blog;


/**
 * Mapper映射接口
 * @author oxygen
 * @date 2020/5/14
 **/
public interface BlogMapper {

    /**
     * 根据主键查询文章
     * @param bid
     * @return
     */
    Blog selectBlogById(Integer bid);


    /**
     * 根据ID更新数据
     * @param name
     * @param bid
     * @return
     */
    int updateBlogById(String name,Integer bid);


    /**
     * 根据ID删除数据
     * @param bid
     * @return
     */
    int deleteBlogById(Integer bid);


    /**
     * 数据插入
     * @param name
     * @param authorId
     * @return
     */
    int insertBlog(String name,Integer authorId);

}
