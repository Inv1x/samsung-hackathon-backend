package com.samsung_hackathon.backend.controller;

import com.samsung_hackathon.backend.entity.ColumnTask;
import com.samsung_hackathon.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController("task")
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ColumnTask createTask(@RequestBody ColumnTask columnTask) {
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
