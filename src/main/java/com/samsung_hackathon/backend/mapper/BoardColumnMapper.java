package com.samsung_hackathon.backend.mapper;

import com.samsung_hackathon.backend.controller.dto.AttachedColumnDto;
import com.samsung_hackathon.backend.entity.BoardColumn;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class BoardColumnMapper {
    public AttachedColumnDto toAttachedColumnDto(BoardColumn boardColumn) {
        return AttachedColumnDto.builder()
                .id(boardColumn.getId())
                .heading(boardColumn.getHeading())
                .columnTasks(boardColumn.getColumnTasks().stream().map(ColumnTaskMapper::toAttachedColumnDto).collect(Collectors.toSet()))
                .build();
    }
}
