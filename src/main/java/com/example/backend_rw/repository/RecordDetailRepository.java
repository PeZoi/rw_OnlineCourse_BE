package com.example.backend_rw.repository;

import com.example.backend_rw.entity.RecordDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordDetailRepository extends JpaRepository<RecordDetail, Integer> {
}
