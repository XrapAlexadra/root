package com.github.xrapalexandra.kr.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSource {

    private final ComboPooledDataSource pool;

    public DataSource() {
        pool = new ComboPooledDataSource();
        ResourceBundle resource = ResourceBundle.getBundle("db");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String pass = resource.getString("password");
        String driver = resource.getString("driver");
        try {
            pool.setDriverClass(driver);
        } catch (PropertyVetoException e) {
            throw new RuntimeException("driver not loaded!");
        }
        pool.setJdbcUrl(url);
        pool.setUser(user);
        pool.setPassword(pass);
        pool.setMinPoolSize(3);
        pool.setAcquireIncrement(5);
        pool.setMaxPoolSize(20);
        pool.setMaxStatements(180);

    }

    private static volatile DataSource instance;

    public static DataSource getInstance() {
        DataSource localInstance = instance;
        if (localInstance == null) {
            synchronized (DataSource.class) {
                localInstance = instance;
                if (localInstance == null) localInstance = instance = new DataSource();
            }
        }
        return localInstance;
    }

    public Connection getConnection() {
        try {
            return this.pool.getConnection();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
