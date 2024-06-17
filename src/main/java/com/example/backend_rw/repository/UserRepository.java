package com.example.backend_rw.repository;

import com.example.backend_rw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
