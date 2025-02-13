package com.estuate.optim.OptimBackend.config;

import com.estuate.optim.OptimBackend.service.DynamicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DynamicDataSource dynamicDataSource() {
        HikariDataSource defaultDataSource = new HikariDataSource();
        defaultDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/optimfrontend");
        defaultDataSource.setUsername("root");
        defaultDataSource.setPassword("Vaish@099");
        defaultDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return new DynamicDataSource(defaultDataSource);
    }
}
