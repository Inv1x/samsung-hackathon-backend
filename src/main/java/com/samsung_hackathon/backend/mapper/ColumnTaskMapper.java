package com.samsung_hackathon.backend.mapper;

import com.samsung_hackathon.backend.controller.dto.AttachedTaskDto;
import com.samsung_hackathon.backend.entity.ColumnTask;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ColumnTaskMapper {
    public AttachedTaskDto toAttachedColumnDto(ColumnTask columnTask) {
        return AttachedTaskDto.builder()
                .id(columnTask.getId())
                .description(columnTask.getDescription())
                .assignedTo(UserMapper.toUserShortDto(columnTask.getAssignedTo()))
                .isDone(columnTask.isDone())
                .build();
    }
}
