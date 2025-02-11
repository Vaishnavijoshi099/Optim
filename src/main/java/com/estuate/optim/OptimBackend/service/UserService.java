package com.estuate.optim.OptimBackend.service;

import com.estuate.optim.OptimBackend.model.Users;
import com.estuate.optim.OptimBackend.Repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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


        // Fetch all tables dynamically
        public List<Map<String, Object>> getTables() {
            String query = """
            SELECT table_name 
            FROM information_schema.tables 
            WHERE table_schema = 'public'
        """;
            return jdbcTemplate.queryForList(query);
        }

        // Fetch columns of a selected table dynamically
        public List<Map<String, Object>> getTableColumns(String tableName) {
            String query = """
            SELECT column_name, data_type 
            FROM information_schema.columns 
            WHERE table_name = ?
        """;
            return jdbcTemplate.queryForList(query, tableName);
        }
    }



