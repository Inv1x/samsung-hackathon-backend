package com.samsung_hackaton.backend.service.impl;

import com.samsung_hackaton.backend.dao.ColumnRepository;
import com.samsung_hackaton.backend.dao.TaskRepository;
import com.samsung_hackaton.backend.dao.UserRepository;
import com.samsung_hackaton.backend.entity.BoardColumn;
import com.samsung_hackaton.backend.entity.Task;
import com.samsung_hackaton.backend.entity.User;
import com.samsung_hackaton.backend.service.TaskService;
import jakarta.persistence.Column;
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
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.saveAndFlush(task);
    }

    @Override
    public Task getTask(long id) {
        Task task;

        try {
            task = taskRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Task with ID " + id + " not found!", e);
        }

        return task;
    }

    @Override
    public Task updateTask(long id, Task task) {
        Task newTask;

        try {
            newTask = taskRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Task with ID " + id + " not found!", e);
        }

        newTask.setDescription(task.getDescription());
        newTask.setAssignedTo(task.getAssignedTo());

        return taskRepository.saveAndFlush(newTask);
    }

    @Override
    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task linkColumn(long taskId, long columnId) {
        Task task;
        BoardColumn column;

        try {
            task = taskRepository.findById(taskId).get();
            column = columnRepository.findById(columnId).get();
        } catch (NoSuchElementException e) {
            if (taskRepository.findById(taskId).isEmpty()) {
                throw new RuntimeException("Task with ID " + taskId + " not found!", e);
            }
            else {
                throw new RuntimeException("Column with ID " + columnId + " not found!", e);
            }
        }

        task.setColumnId(columnId);

        return taskRepository.saveAndFlush(task);
    }

    @Override
    public Task unlinkColumn(long taskId, long columnId) {
        Task task;
        BoardColumn column;

        try {
            task = taskRepository.findById(taskId).get();
            column = columnRepository.findById(columnId).get();
        } catch (NoSuchElementException e) {
            if (taskRepository.findById(taskId).isEmpty()) {
                throw new RuntimeException("Task with ID " + taskId + " not found!", e);
            }
            else {
                throw new RuntimeException("Column with ID " + columnId + " not found!", e);
            }
        }

        task.setColumnId(0);

        return taskRepository.saveAndFlush(task);
    }

    @Override
    public Task linkUser(long taskId, long userId) {
        Task task;
        User user;

        try {
            task = taskRepository.findById(taskId).get();
            user = userRepository.findById(userId).get();
        } catch (NoSuchElementException e) {
            if (taskRepository.findById(taskId).isEmpty()) {
                throw new RuntimeException("Task with ID " + taskId + " not found!", e);
            }
            else {
                throw new RuntimeException("User with ID " + userId + " not found!", e);
            }
        }

        task.setAssignedTo(user);

        return taskRepository.saveAndFlush(task);
    }

    @Override
    public Task unlinkUser(long taskId, long userId) {
        Task task;
        User user;

        try {
            task = taskRepository.findById(taskId).get();
            user = userRepository.findById(userId).get();
        } catch (NoSuchElementException e) {
            if (taskRepository.findById(taskId).isEmpty()) {
                throw new RuntimeException("Task with ID " + taskId + " not found!", e);
            }
            else {
                throw new RuntimeException("User with ID " + userId + " not found!", e);
            }
        }

        task.setAssignedTo(null);

        return taskRepository.saveAndFlush(task);
    }
}
