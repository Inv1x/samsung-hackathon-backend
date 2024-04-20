package com.samsung_hackaton.backend.controller;

import com.samsung_hackaton.backend.entity.ColumnTask;
import com.samsung_hackaton.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("task")
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ColumnTask createTask(ColumnTask columnTask) {
        return taskService.createTask(columnTask);
    }

    @GetMapping("/{id}")
    public ColumnTask getTask(long id) {
        return taskService.getTask(id);
    }

    @PutMapping("/{id}")
    public ColumnTask updateTask(long id, ColumnTask columnTask) {
        return taskService.updateTask(id, columnTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(long id) {
        taskService.deleteTask(id);
    }

    @PutMapping("/{taskId}/link-column/{columnId}")
    public ColumnTask linkColumn(long taskId, long columnId) {
        return taskService.linkColumn(taskId, columnId);
    }

    @PutMapping("/{taskId}/unlink-user/{columnId}")
    public ColumnTask unlinkUser(long taskId, long userId) {
        return taskService.unlinkUser(taskId, userId);
    }
}
