package com.estuate.optim.OptimBackend.controller;

import com.estuate.optim.OptimBackend.model.Users;
import com.estuate.optim.OptimBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/optim")
public class OptimController {

    private final UserService userService;
    @Autowired
    public JdbcTemplate jdbcTemplate;

    public OptimController(UserService userService) {
        this.userService = userService;
    }

    // **User Registration**
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        Optional<Users> existingUser = userService.findUserById(user.getUserId());

        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("{\"error\": \"User already exists\"}");
        } else {
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("{\"message\": \"Registration successful\"}");
        }
    }

    // **User Login**
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Users user) {
        Optional<Users> existingUser = userService.findUserById(user.getUserId());

        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok().body("{\"message\": \"Login successful\"}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\": \"Invalid credentials\"}");
        }
    }

    @GetMapping("/allUsers")
    public ResponseEntity<?> getALlUsers() {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody Users users) {
        userService.deleteUser(users);
        return ResponseEntity.ok("User deleted successfully!!");
    }

    @GetMapping("/tables")
    public ResponseEntity<List<String>> getAllTables() {
        List<String> tables = userService.getAllTables();
        return ResponseEntity.ok(tables);
    }


    @GetMapping("/tables/{tableName}/data")
    public ResponseEntity<List<Map<String, Object>>> getTableData(@PathVariable String tableName) {
        List<Map<String, Object>> tableData = userService.getTableData(tableName);
        return ResponseEntity.ok(tableData);
    }

    @PostMapping("/tables/{tableName}/archive")
    public ResponseEntity<String> archiveTableData(@PathVariable String tableName) {
        try {
            List<Map<String, Object>> tableData = userService.getTableData(tableName);
            String filePath = userService.archiveTableDataToJson(tableName, tableData);
            return ResponseEntity.ok("Data archived successfully at: " + filePath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/tables/{tableName}/columns")
    public ResponseEntity<List<String>> getColumns(@PathVariable String tableName) throws SQLException {
        List<String> columns = userService.getColumnNames(tableName);
        return ResponseEntity.ok(columns);
    }

    @GetMapping("/databases")
    public List<String> getDatabases() {
        String sql = "SHOW DATABASES";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    @GetMapping("/databases/{database}/tables")
    public List<String> getTables(@PathVariable String database) {
        try {
            String sql = "SELECT TABLE_NAME FROM information_schema.tables WHERE table_schema = ?";
            return jdbcTemplate.queryForList(sql, new Object[]{database}, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }


    }
}





