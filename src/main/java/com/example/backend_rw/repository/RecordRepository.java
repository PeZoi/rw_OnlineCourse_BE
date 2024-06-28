package com.example.backend_rw.repository;

import com.example.backend_rw.entity.Contest;
import com.example.backend_rw.entity.Record;
import com.example.backend_rw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    List<Record> findAllByUser(User user);
    List<Record> findAllByContest(Contest contest);
    int countAllByContest(Contest contest);
    List<Record> findAllByUserAndContest(User user, Contest contest);
}
