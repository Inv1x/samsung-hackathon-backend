package com.samsung_hackaton.backend.dao;

import com.samsung_hackaton.backend.entity.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends JpaRepository<BoardColumn, Long> {
}
