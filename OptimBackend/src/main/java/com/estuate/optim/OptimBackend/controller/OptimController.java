package com.estuate.optim.OptimBackend.controller;

import com.estuate.optim.OptimBackend.model.Users;
import com.estuate.optim.OptimBackend.service.UserService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/optim")
public class OptimController {

    private final UserService userService;

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
    public ResponseEntity<?> getALlUsers(){
        List<Users> users =userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody Users users){
        userService.deleteUser(users);
        return ResponseEntity.ok("User deleted successfully!!");
    }

    @GetMapping("/showDatabases")
    public ResponseEntity<?> showDB(){
        userService.getAllSchemas();
        return ResponseEntity.ok("Databases");
    }
}
