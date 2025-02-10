package com.estuate.optim.OptimBackend.repository;

import com.estuate.optim.OptimBackend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
}
