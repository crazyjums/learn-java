package com.multidatasource.aop;

import com.multidatasource.annotaion.TargetDataSource;
import com.multidatasource.config.DynamicDataSource;
import com.multidatasource.enums.DBType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DynamicDataSourceAspect {


    /**
     * 在方法执行之前，切换数据源，数据源根据@DataSourceTarget注解指定
     *
     * @param joinPoint        joinPoint
     * @param targetDataSource dataSourceTarget
     */
    @Before("within(com.multidatasource.service.impl.* || " +
            "com.multidatasource.modules.*.service.impl.*) && " +
            "@annotation(targetDataSource)")
    public void setMysqlDataSourceBeforeExecuteSqlQuery(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        //通过@DataSourceTarget注解显示在service层控制数据查询走主库还是从库
        DBType dbType = targetDataSource.targetDB();
        DBType currDBType = (dbType == null ? DBType.MASTER : dbType);
        DynamicDataSource.targetDataSourceContext.set(currDBType);
    }


}
