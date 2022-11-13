package com.multidatasourceaop.config;

import com.multidatasourceaop.enums.DBType;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
@Primary
public class DynamicDataSource extends AbstractRoutingDataSource {

    public static ThreadLocal<DBType> targetDataSourceContext = new ThreadLocal<>();

    @Resource
    DataSource masterDataSource;

    @Resource
    DataSource slaveDataSource;

    @Override
    public void afterPropertiesSet() {
        //为targetDataSources初始化所有数据源
        Map<Object, Object> targetDataSources = new HashMap<>(3);
        targetDataSources.put(DBType.MASTER, masterDataSource);
        targetDataSources.put(DBType.SLAVE, slaveDataSource);
        super.setTargetDataSources(targetDataSources);

        //为defaultTargetDataSource设置默认的数据源
        super.setDefaultTargetDataSource(masterDataSource);

        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        //设置默认的数据源
        return targetDataSourceContext.get();
    }

    /**
     * 设置当前上下文环境中的数据源
     *
     * @param dbType dbType
     */
    public static void setTargetDataSourceContext(DBType dbType) {
        targetDataSourceContext.set(dbType);
    }

    /**
     * 清空当前上下文中的数据源
     */
    public static void clearTargetDataSourceContext() {
        targetDataSourceContext.remove();
    }
}
