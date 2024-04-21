package com.samsung_hackathon.backend.mapper;

import com.samsung_hackathon.backend.controller.dto.BoardDto;
import com.samsung_hackathon.backend.entity.Board;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class BoardMapper {
    public BoardDto toBoardDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .columns(board.getColumns().stream().map(BoardColumnMapper::toAttachedColumnDto).collect(Collectors.toSet()))
                .owner(board.getOwner() == null ? null : UserMapper.toUserShortDto(board.getOwner()))
                .collaborators(board.getCollaborators().stream().map(UserMapper::toUserShortDto).collect(Collectors.toSet()))
                .build();
    }
}
