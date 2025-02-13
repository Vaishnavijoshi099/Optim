package com.estuate.optim.OptimBackend.service;

import com.estuate.optim.OptimBackend.model.Users;
import com.estuate.optim.OptimBackend.Repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;

    public UserService(UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getAllSchemas() {
        String sql = "SHOW DATABASES;";
        return jdbcTemplate.queryForList(sql, String.class);
    }



    public Optional<Users> findUserById(String userId) {
        return userRepository.findById(userId);
    }

    public void saveUser(Users user) {
        userRepository.save(user);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Users users) {
        userRepository.delete(users);
    }

    public List<Map<String, Object>> getTableData(String tableName) {
        String query = "SELECT * FROM " + tableName;
        return jdbcTemplate.queryForList(query);
    }
    public String archiveTableDataToJson(String tableName, List<Map<String, Object>> tableData) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = "C:/Users/Vjoshi/Downloads/" + tableName + "_archive.json";
        objectMapper.writeValue(new File(filePath), tableData);
        return filePath;
    }

    public List<String> getColumnNames(String tableName) throws SQLException {
        List<String> columnNames = new ArrayList<>();
        Connection connection = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        try (ResultSet resultSet = metaData.getColumns(null, null, tableName, null)) {
            while (resultSet.next()) {
                columnNames.add(resultSet.getString("COLUMN_NAME"));
            }
        }

        connection.close();
        return columnNames;
    }


    public List<String> getAllTables() {
        String sql = "SHOW TABLES";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public List<String> getTablesByDatabase(String databaseName) {
        String query = "SELECT table_name FROM information_schema.tables WHERE table_schema = ?";
        return jdbcTemplate.queryForList(query, String.class, databaseName);
    }



}



