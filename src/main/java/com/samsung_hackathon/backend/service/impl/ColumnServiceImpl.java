package com.samsung_hackathon.backend.service.impl;

import com.samsung_hackathon.backend.controller.dto.AttachedColumnDto;
import com.samsung_hackathon.backend.dao.BoardRepository;
import com.samsung_hackathon.backend.dao.ColumnRepository;
import com.samsung_hackathon.backend.dao.TaskRepository;
import com.samsung_hackathon.backend.entity.Board;
import com.samsung_hackathon.backend.entity.BoardColumn;
import com.samsung_hackathon.backend.entity.ColumnTask;
import com.samsung_hackathon.backend.mapper.BoardColumnMapper;
import com.samsung_hackathon.backend.service.ColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

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
    public Set<ColumnTask> getAllTasksFromColumn(long columnId){
        BoardColumn column = getColumn(columnId);

        return column.getColumnTasks();
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
    public AttachedColumnDto getAttachedColumnDto(long id) {
        BoardColumn column = getColumn(id);
        return BoardColumnMapper.toAttachedColumnDto(column);
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
    public AttachedColumnDto addTask(long listId, long taskId) {
        BoardColumn column;
        ColumnTask columnTask;

        try {
            column = columnRepository.findById(listId).get();
            columnTask = taskRepository.findById(taskId).get();
        } catch (NoSuchElementException e) {
            if (columnRepository.findById(listId).isEmpty()) {
                throw new RuntimeException("Column with ID " + listId + " not found!", e);
            }
            else {
                throw new RuntimeException("Task with ID " + taskId + " not found!", e);
            }
        }

        column.getColumnTasks().add(columnTask);
        // привязать задачу к колонке (вызвать createTask)

        return BoardColumnMapper.toAttachedColumnDto(columnRepository.saveAndFlush(column));
    }

    @Override
    public BoardColumn removeTask(long listId, long taskId) {
        BoardColumn column;
        ColumnTask columnTask;

        try {
            column = columnRepository.findById(listId).get();
            columnTask = taskRepository.findById(taskId).get();
        } catch (NoSuchElementException e) {
            if (columnRepository.findById(listId).isEmpty()) {
                throw new RuntimeException("Column with ID " + listId + " not found!", e);
            }
            else {
                throw new RuntimeException("Task with ID " + taskId + " not found!", e);
            }
        }

        column.getColumnTasks().remove(columnTask);

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
