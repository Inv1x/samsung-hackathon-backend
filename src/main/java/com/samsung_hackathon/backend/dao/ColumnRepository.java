package com.samsung_hackathon.backend.dao;

import com.samsung_hackathon.backend.entity.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends JpaRepository<BoardColumn, Long> {
}
