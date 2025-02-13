package com.estuate.optim.OptimBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Service
public class DynamicDataSourceService {

    @Autowired
    private DynamicDataSource dynamicDataSource;

    private static final Map<String, HikariDataSource> dataSourceCache = new HashMap<>();

    public void changeDatabase(String dbName) {
        DataSource newDataSource = createNewDataSource(dbName);
        dynamicDataSource.setDataSource(newDataSource);
    }

    public DataSource createNewDataSource(String dbName) {
        if (dataSourceCache.containsKey(dbName)) {
            return dataSourceCache.get(dbName);
        }

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/" + dbName);
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("Vaish@099");
        hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSourceCache.put(dbName, hikariDataSource);
        return hikariDataSource;
    }
}
