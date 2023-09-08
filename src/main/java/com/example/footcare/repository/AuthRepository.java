package com.example.footcare.repository;

import com.example.footcare.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
