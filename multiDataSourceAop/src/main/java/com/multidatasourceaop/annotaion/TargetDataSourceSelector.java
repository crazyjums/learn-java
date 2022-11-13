package com.multidatasourceaop.annotaion;

import com.multidatasourceaop.enums.DBType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解可作用于方法或者类
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSourceSelector {

    /**
     * 显示控制数据查询走主库还是从库，默认走主库
     *
     * @return DBType
     */
    DBType targetDB() default DBType.MASTER;
}
