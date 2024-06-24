package com.example.backend_rw.repository;

import com.example.backend_rw.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerQuizRepository extends JpaRepository<Answer, Integer> {
}