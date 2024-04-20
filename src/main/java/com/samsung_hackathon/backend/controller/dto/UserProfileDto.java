package com.samsung_hackathon.backend.controller.dto;

import com.samsung_hackathon.backend.entity.Board;
import com.samsung_hackathon.backend.entity.ColumnTask;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.h2.table.Column;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private long id;

    private String name;

    private String username;

    private String email;

    private Set<Board> boards;

    private Set<ColumnTask> assignedColumnTasks;
}
