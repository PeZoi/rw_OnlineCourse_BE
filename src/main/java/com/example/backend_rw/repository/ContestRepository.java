package com.example.backend_rw.repository;

import com.example.backend_rw.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRepository extends JpaRepository<Contest, Integer> {
}
