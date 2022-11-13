package com.multidatasourceaop.aop;

import com.multidatasourceaop.annotaion.TargetDataSourceSelector;
import com.multidatasourceaop.config.DynamicDataSource;
import com.multidatasourceaop.enums.DBType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DynamicDataSourceAspect {


    /**
     * 在方法执行之前，切换数据源，数据源根据@TargetDataSourceSelector注解指定
     *
     * @param joinPoint                joinPoint
     * @param targetDataSourceSelector dataSourceTarget
     */
    @Before("within(com.multidatasourceaop.service.impl.* || " +
            "com.multidatasourceaop.modules.*.service.impl.*) && " +
            "@annotation(targetDataSourceSelector)")
    public void setDataSourceBeforeQuery(JoinPoint joinPoint, TargetDataSourceSelector targetDataSourceSelector) {
        //通过@TargetDataSourceSelector注解显示在service层控制数据查询走主库还是从库
        DBType dbType = targetDataSourceSelector.targetDB();
        DBType currDBType = (dbType == null ? DBType.MASTER : dbType);
        DynamicDataSource.setTargetDataSourceContext(currDBType);
    }

    /**
     * 在方法执行之后，清除上下文中的数据源，防止写请求走读库
     *
     * @param joinPoint                joinPoint
     * @param targetDataSourceSelector dataSourceTarget
     */
    @After("within(com.multidatasourceaop.service.impl.* || " +
            "com.multidatasourceaop.modules.*.service.impl.*) && " +
            "@annotation(targetDataSourceSelector)")
    public void clearDataSourceAfterQuery(JoinPoint joinPoint, TargetDataSourceSelector targetDataSourceSelector) {
        DynamicDataSource.clearTargetDataSourceContext();
    }
}
