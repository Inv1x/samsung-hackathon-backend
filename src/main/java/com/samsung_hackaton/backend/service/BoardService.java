package com.samsung_hackaton.backend.service;

import com.samsung_hackaton.backend.entity.Board;

import java.util.List;

public interface BoardService {

    List<Board> getAllBoards();

    Board createBoard(Board board);

    Board getBoard(long id);

    Board updateBoard(long id, Board board);  // бесполезный метод, можно удалить впринципе

    void deleteBoard(long id);

    Board addColumn(long boardId, long columnId);

    Board removeColumn(long boardId, long columnId);

    Board addCollaborator(long boardId, long collaboratorId);

    Board removeCollaborator(long boardId, long collaboratorId);
}
