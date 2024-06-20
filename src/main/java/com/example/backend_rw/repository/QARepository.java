package com.example.backend_rw.repository;

import com.example.backend_rw.entity.QA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QARepository extends JpaRepository<QA, Integer> {
}
