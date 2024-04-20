package com.samsung_hackaton.backend.dao;

import com.samsung_hackaton.backend.entity.Task;
import com.samsung_hackaton.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
