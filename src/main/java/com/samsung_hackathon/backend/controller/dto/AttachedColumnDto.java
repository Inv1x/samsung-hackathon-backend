package com.samsung_hackathon.backend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachedColumnDto {
    private long id;
    private String heading;
    private Set<AttachedTaskDto> columnTasks;
}
