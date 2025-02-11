package com.estuate.optim.OptimBackend.Repository;

import com.estuate.optim.OptimBackend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
    Users findByUsername(String username);
}
