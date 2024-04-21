package com.samsung_hackathon.backend.service;

import com.samsung_hackathon.backend.controller.dto.BoardDto;
import com.samsung_hackathon.backend.entity.Board;

import java.util.List;

public interface BoardService {

    List<BoardDto> getAllBoards();

    BoardDto createBoard(Board board);

    BoardDto getBoard(long id);

    BoardDto updateBoard(long id, Board board);  // бесполезный метод, можно удалить впринципе

    void deleteBoard(long id);

    BoardDto addColumn(long boardId, long columnId);

    BoardDto removeColumn(long boardId, long columnId);

    BoardDto addCollaborator(long boardId, long collaboratorId);

    BoardDto removeCollaborator(long boardId, long collaboratorId);
}
