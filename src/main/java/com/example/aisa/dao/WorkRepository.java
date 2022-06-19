package com.example.aisa.dao;

import com.example.aisa.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Log, Long> {
}
