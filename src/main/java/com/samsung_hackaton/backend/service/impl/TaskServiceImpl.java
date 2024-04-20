package com.samsung_hackaton.backend.service.impl;

import com.samsung_hackaton.backend.dao.ColumnRepository;
import com.samsung_hackaton.backend.dao.TaskRepository;
import com.samsung_hackaton.backend.dao.UserRepository;
import com.samsung_hackaton.backend.entity.BoardColumn;
import com.samsung_hackaton.backend.entity.ColumnTask;
import com.samsung_hackaton.backend.entity.User;
import com.samsung_hackaton.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ColumnRepository columnRepository;

    @Override
    public ColumnTask createTask(ColumnTask columnTask) {
        return taskRepository.saveAndFlush(columnTask);
    }

    @Override
    public ColumnTask getTask(long id) {
        ColumnTask columnTask;

        try {
            columnTask = taskRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Task with ID " + id + " not found!", e);
        }

        return columnTask;
    }

    @Override
    public ColumnTask updateTask(long id, ColumnTask columnTask) {
        ColumnTask newColumnTask;

        try {
            newColumnTask = taskRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Task with ID " + id + " not found!", e);
        }

        newColumnTask.setDescription(columnTask.getDescription());
        newColumnTask.setAssignedTo(columnTask.getAssignedTo());

        return taskRepository.saveAndFlush(newColumnTask);
    }

    @Override
    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public ColumnTask linkColumn(long taskId, long columnId) {
        ColumnTask columnTask;
        BoardColumn column;

        try {
            columnTask = taskRepository.findById(taskId).get();
            column = columnRepository.findById(columnId).get();
        } catch (NoSuchElementException e) {
            if (taskRepository.findById(taskId).isEmpty()) {
                throw new RuntimeException("Task with ID " + taskId + " not found!", e);
            }
            else {
                throw new RuntimeException("Column with ID " + columnId + " not found!", e);
            }
        }

        columnTask.setColumnId(columnId);

        return taskRepository.saveAndFlush(columnTask);
    }

    @Override
    public ColumnTask unlinkColumn(long taskId, long columnId) {
        ColumnTask columnTask;
        BoardColumn column;

        try {
            columnTask = taskRepository.findById(taskId).get();
            column = columnRepository.findById(columnId).get();
        } catch (NoSuchElementException e) {
            if (taskRepository.findById(taskId).isEmpty()) {
                throw new RuntimeException("Task with ID " + taskId + " not found!", e);
            }
            else {
                throw new RuntimeException("Column with ID " + columnId + " not found!", e);
            }
        }

        columnTask.setColumnId(0);

        return taskRepository.saveAndFlush(columnTask);
    }

    @Override
    public ColumnTask linkUser(long taskId, long userId) {
        ColumnTask columnTask;
        User user;

        try {
            columnTask = taskRepository.findById(taskId).get();
            user = userRepository.findById(userId).get();
        } catch (NoSuchElementException e) {
            if (taskRepository.findById(taskId).isEmpty()) {
                throw new RuntimeException("Task with ID " + taskId + " not found!", e);
            }
            else {
                throw new RuntimeException("User with ID " + userId + " not found!", e);
            }
        }

        columnTask.setAssignedTo(user);

        return taskRepository.saveAndFlush(columnTask);
    }

    @Override
    public ColumnTask unlinkUser(long taskId, long userId) {
        ColumnTask columnTask;
        User user;

        try {
            columnTask = taskRepository.findById(taskId).get();
            user = userRepository.findById(userId).get();
        } catch (NoSuchElementException e) {
            if (taskRepository.findById(taskId).isEmpty()) {
                throw new RuntimeException("Task with ID " + taskId + " not found!", e);
            }
            else {
                throw new RuntimeException("User with ID " + userId + " not found!", e);
            }
        }

        columnTask.setAssignedTo(null);

        return taskRepository.saveAndFlush(columnTask);
    }
}
