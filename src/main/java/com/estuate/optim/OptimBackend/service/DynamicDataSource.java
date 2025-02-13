package com.estuate.optim.OptimBackend.service;

import org.springframework.jdbc.datasource.AbstractDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DynamicDataSource extends AbstractDataSource {

    private DataSource currentDataSource;

    public DynamicDataSource(DataSource dataSource) {
        this.currentDataSource = dataSource;
    }

    public synchronized void setDataSource(DataSource dataSource) {
        this.currentDataSource = dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return currentDataSource.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return currentDataSource.getConnection(username, password);
    }
}
