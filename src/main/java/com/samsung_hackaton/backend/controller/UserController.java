package com.samsung_hackaton.backend.controller;

import com.samsung_hackaton.backend.entity.User;
import com.samsung_hackaton.backend.service.UserService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("user")
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping()
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("by-id/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PutMapping("by-id/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("by-id/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @PostMapping("{userId}/link-board/{boardId}")
    public User linkBoard(@PathVariable long userId, @PathVariable long boardId) {
        return userService.linkBoard(userId, boardId);
    }

    @PostMapping("{userId}/assign-task/{taskId}")
    public User assignTask(@PathVariable long userId, @PathVariable long taskId) {
        return userService.assignTask(userId, taskId);
    }
}
