package com.samsung_hackaton.backend.controller;

import com.samsung_hackaton.backend.entity.Task;
import com.samsung_hackaton.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("task")
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task createTask(Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public Task getTask(long id) {
        return taskService.getTask(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(long id, Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(long id) {
        taskService.deleteTask(id);
    }

    @PutMapping("/{taskId}/link-column/{columnId}")
    public Task linkColumn(long taskId, long columnId) {
        return taskService.linkColumn(taskId, columnId);
    }

    @PutMapping("/{taskId}/unlink-user/{columnId}")
    public Task unlinkUser(long taskId, long userId) {
        return taskService.unlinkUser(taskId, userId);
    }
}
