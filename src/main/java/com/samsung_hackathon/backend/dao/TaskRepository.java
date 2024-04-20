package com.samsung_hackathon.backend.dao;

import com.samsung_hackathon.backend.entity.ColumnTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<ColumnTask, Long> {
}
