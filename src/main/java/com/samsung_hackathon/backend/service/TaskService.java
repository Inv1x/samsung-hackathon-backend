package com.samsung_hackathon.backend.service;

import com.samsung_hackathon.backend.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task createTask(Task task);

    Task getTask(long id);

    Task updateTask(long id, Task task);

    void deleteTask(long id);

    Task linkColumn(long taskId, long columnId);

    Task unlinkColumn(long taskId, long columnId);

    Task linkUser(long taskId, long userId);

    Task unlinkUser(long taskId, long userId);
}
