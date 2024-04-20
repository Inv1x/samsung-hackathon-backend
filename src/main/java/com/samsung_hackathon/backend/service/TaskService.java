package com.samsung_hackathon.backend.service;

import com.samsung_hackathon.backend.entity.ColumnTask;

import java.util.List;

public interface TaskService {

    ColumnTask createTask(ColumnTask columnTask);

    ColumnTask getTask(long id);

    ColumnTask updateTask(long id, ColumnTask columnTask);

    void deleteTask(long id);

    ColumnTask linkColumn(long taskId, long columnId);

    ColumnTask unlinkColumn(long taskId, long columnId);

    ColumnTask linkUser(long taskId, long userId);

    ColumnTask unlinkUser(long taskId, long userId);
}
