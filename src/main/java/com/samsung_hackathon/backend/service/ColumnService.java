package com.samsung_hackathon.backend.service;

import com.samsung_hackathon.backend.entity.BoardColumn;
import com.samsung_hackathon.backend.entity.ColumnTask;

import java.util.List;
import java.util.Set;

public interface ColumnService {

    List<BoardColumn> getAllColumns();

    Set<ColumnTask> getAllTasksFromColumn(long columnId);

    BoardColumn createColumn(BoardColumn boardColumn);

    BoardColumn getColumn(long id);

    BoardColumn updateColumn(long id, BoardColumn boardColumn);

    void deleteColumn(long id);

    BoardColumn addTask(long listId, long taskId);

    BoardColumn removeTask(long listId, long taskId);

    BoardColumn linkBoard(long listId, long boardId);

    BoardColumn unlinkBoard(long listId, long boardId);
}
