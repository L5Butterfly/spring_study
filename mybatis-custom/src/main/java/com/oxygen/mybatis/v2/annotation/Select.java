package com.oxygen.mybatis.v2.annotation;

import java.lang.annotation.*;

/**
 * 注解方法，配置SQL语句
 * @author:oxygen
 * @date 20200515
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Select {
    String value();
}
