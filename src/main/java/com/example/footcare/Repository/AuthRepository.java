package com.example.footcare.Repository;

import com.example.footcare.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Users, Long> {
}
