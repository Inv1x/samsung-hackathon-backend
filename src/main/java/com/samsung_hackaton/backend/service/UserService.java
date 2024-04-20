package com.samsung_hackaton.backend.service;


import com.samsung_hackaton.backend.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User createUser(User user);

    User getUser(long id);

    User updateUser(long id, User user);

    void deleteUser(long id);

    User linkBoard(long userId, long boardId);

    User assignTask(long userId, long taskId);
}