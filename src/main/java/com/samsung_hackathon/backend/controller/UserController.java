package com.samsung_hackathon.backend.controller;

import com.samsung_hackathon.backend.controller.dto.UserProfileDto;
import com.samsung_hackathon.backend.controller.dto.UserRegisterDto;
import com.samsung_hackathon.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("user")
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserProfileDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public UserProfileDto createUser(@RequestBody UserRegisterDto user) {
        return userService.createUser(user);
    }

    @GetMapping("/login")
    public UserProfileDto login(Authentication authentication) {
        return userService.getUserByUsername(authentication.getName());
    }

    @GetMapping("by-id/{id}")
    public UserProfileDto getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PutMapping("by-id/{id}")
    public UserProfileDto updateUser(@PathVariable long id, @RequestBody UserProfileDto user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("by-id/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @PostMapping("{userId}/link-board/{boardId}")
    public UserProfileDto linkBoard(@PathVariable long userId, @PathVariable long boardId) {
        return userService.linkBoard(userId, boardId);
    }

    @PostMapping("{userId}/assign-task/{taskId}")
    public UserProfileDto assignTask(@PathVariable long userId, @PathVariable long taskId) {
        return userService.assignTask(userId, taskId);
    }
}
