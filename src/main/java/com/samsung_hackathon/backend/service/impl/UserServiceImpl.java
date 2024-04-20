package com.samsung_hackathon.backend.service.impl;

import com.samsung_hackathon.backend.dao.BoardRepository;
import com.samsung_hackathon.backend.dao.TaskRepository;
import com.samsung_hackathon.backend.dao.UserRepository;
import com.samsung_hackathon.backend.entity.Board;
import com.samsung_hackathon.backend.entity.Task;
import com.samsung_hackathon.backend.entity.User;
import com.samsung_hackathon.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    private final BoardRepository boardRepository;

    @Autowired
    private final TaskRepository taskRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getUser(long id) throws RuntimeException{
        User user;

        try {
            user = userRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("User with ID " + id + " not found!", e);
        }

        return user;
    }

    @Override
    public User updateUser(long id, User user) {
        User newUser;

        try {
            newUser = userRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("User with ID " + id + " not found!", e);
        }

        newUser.setName(user.getName());
        newUser.setBoards(user.getBoards());
        newUser.setAssignedTasks(user.getAssignedTasks());

        return userRepository.saveAndFlush(newUser);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User linkBoard(long userId, long boardId) {
        User user;
        Board board;

        try {
            user = userRepository.findById(userId).get();
            board = boardRepository.findById(boardId).get();
        } catch (NoSuchElementException e) {
            if (userRepository.findById(userId).isEmpty()) {
                throw new RuntimeException("User with ID " + userId + " not found!", e);
            }
            else {
                throw new RuntimeException("Board with ID " + boardId + " not found!", e);
            }
        }

        user.getBoards().add(board);
        board.getCollaborators().add(user);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User assignTask(long userId, long taskId) {
        User user;
        Task task;

        try {
            user = userRepository.findById(userId).get();
            task = taskRepository.findById(taskId).get();
        } catch (NoSuchElementException e) {
            if (userRepository.findById(userId).isEmpty()) {
                throw new RuntimeException("User with ID " + userId + " not found!", e);
            }
            else {
                throw new RuntimeException("Board with ID " + taskId + " not found!", e);
            }
        }

        user.getAssignedTasks().add(task);
        task.setAssignedTo(user);
        return userRepository.saveAndFlush(user);
    }
}
