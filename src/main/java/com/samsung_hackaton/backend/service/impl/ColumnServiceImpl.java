package com.samsung_hackaton.backend.service.impl;

import com.samsung_hackaton.backend.dao.BoardRepository;
import com.samsung_hackaton.backend.dao.ColumnRepository;
import com.samsung_hackaton.backend.dao.TaskRepository;
import com.samsung_hackaton.backend.entity.Board;
import com.samsung_hackaton.backend.entity.BoardColumn;
import com.samsung_hackaton.backend.entity.Task;
import com.samsung_hackaton.backend.service.ColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ColumnServiceImpl implements ColumnService {
    private final ColumnRepository columnRepository;

    @Autowired
    private final BoardRepository boardRepository;

    @Autowired
    private final TaskRepository taskRepository;

    @Override
    public List<BoardColumn> getAllColumns() {
        return columnRepository.findAll();
    }

    @Override
    public BoardColumn createColumn(BoardColumn boardColumn) {
        return columnRepository.saveAndFlush(boardColumn);
    }

    @Override
    public BoardColumn getColumn(long id) {
        BoardColumn newColumn;

        try {
            newColumn = columnRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Column with ID " + id + " not found!", e);
        }

        return newColumn;
    }

    @Override
    public BoardColumn updateColumn(long id, BoardColumn boardColumn) {
        BoardColumn column;

        try {
            column = columnRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Column with ID " + id + " not found!", e);
        }

        column.setHeading(boardColumn.getHeading());

        return columnRepository.saveAndFlush(column);
    }

    @Override
    public void deleteColumn(long id) {
        columnRepository.deleteById(id);
    }

    @Override
    public BoardColumn addTask(long listId, long taskId) {
        BoardColumn column;
        Task task;

        try {
            column = columnRepository.findById(listId).get();
            task = taskRepository.findById(taskId).get();
        } catch (NoSuchElementException e) {
            if (columnRepository.findById(listId).isEmpty()) {
                throw new RuntimeException("Column with ID " + listId + " not found!", e);
            }
            else {
                throw new RuntimeException("Task with ID " + taskId + " not found!", e);
            }
        }

        column.getTasks().add(task);
        // привязать задачу к колонке (вызвать createTask)

        return columnRepository.saveAndFlush(column);
    }

    @Override
    public BoardColumn removeTask(long listId, long taskId) {
        BoardColumn column;
        Task task;

        try {
            column = columnRepository.findById(listId).get();
            task = taskRepository.findById(taskId).get();
        } catch (NoSuchElementException e) {
            if (columnRepository.findById(listId).isEmpty()) {
                throw new RuntimeException("Column with ID " + listId + " not found!", e);
            }
            else {
                throw new RuntimeException("Task with ID " + taskId + " not found!", e);
            }
        }

        column.getTasks().remove(task);

        return columnRepository.saveAndFlush(column);
    }

    @Override
    public BoardColumn linkBoard(long listId, long boardId) {
        return null;  // ненужный метод
    }

    @Override
    public BoardColumn unlinkBoard(long listId, long boardId) {
        BoardColumn column;
        Board board;  // не нужно?

        try {
            column = columnRepository.findById(listId).get();
            board = boardRepository.findById(boardId).get();
        } catch (NoSuchElementException e) {
            if (columnRepository.findById(listId).isEmpty()) {
                throw new RuntimeException("Column with ID " + listId + " not found!", e);
            }
            else {
                throw new RuntimeException("Board with ID " + boardId + " not found!", e);
            }
        }

        column.setBoard(null);
        board.getColumns().remove(column);

        return columnRepository.saveAndFlush(column);
    }
}
