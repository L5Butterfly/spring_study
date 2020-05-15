package com.oxygen.v1.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author oxygen
 * @description 数据实体类
 * @date 2020/5/14
 **/

@Data
public class Blog implements Serializable{

    /**
     * 文章ID
     */
    Integer bid;

    /**
     * 文章标题
     */
    String name;

    /**
     * 文章作者ID
     */
    Integer authorId;
}
