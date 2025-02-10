package com.estuate.optim.OptimBackend.service;

import com.estuate.optim.OptimBackend.model.Users;
import com.estuate.optim.OptimBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;



    public List<String> getAllSchemas() {
        String sql = "SHOW SCHEMAS;";
        List<String> schemas = jdbcTemplate.queryForList(sql, String.class);
        return schemas;

    }


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<Users> findUserById(String userId) {
        return userRepository.findById(userId);
    }

    public void saveUser(Users user) {
        userRepository.save(user);
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Users users){
        userRepository.delete(users);
    }
}
