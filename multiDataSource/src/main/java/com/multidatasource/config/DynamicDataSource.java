package com.multidatasource.config;

import com.multidatasource.enums.DBType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

@Component
@Primary
public class DynamicDataSource implements DataSource, InitializingBean {

    public static ThreadLocal<DBType> currDataSource = new ThreadLocal<>();

    @Resource
    DataSource masterDataSource;

    @Resource
    DataSource slaveDataSource;

    @Override
    public Connection getConnection() throws SQLException {
        if (DBType.MASTER.equals(currDataSource.get())) {
            return masterDataSource.getConnection();
        } else if (DBType.SLAVE.equals(currDataSource.get())) {
            return slaveDataSource.getConnection();
        }
        return masterDataSource.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //默认用主库
        currDataSource.set(DBType.MASTER);
    }
}
