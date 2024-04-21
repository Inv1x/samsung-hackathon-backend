package com.samsung_hackathon.backend.controller.dto;

import com.samsung_hackathon.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachedTaskDto {
    private long id;
    private String description;
    private UserShortDto assignedTo;
    private boolean isDone;
}
