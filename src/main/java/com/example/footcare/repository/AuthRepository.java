package com.example.footcare.repository;

import com.example.footcare.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Users, Long> {
}
