package com.samsung_hackathon.backend.service;


import com.samsung_hackathon.backend.controller.dto.UserProfileDto;
import com.samsung_hackathon.backend.controller.dto.UserRegisterDto;
import com.samsung_hackathon.backend.entity.Authority;
import com.samsung_hackathon.backend.entity.User;

import java.util.List;

public interface UserService {

    List<UserProfileDto> getAllUsers();

    UserProfileDto createUser(UserRegisterDto user);

    UserProfileDto getUser(long id);

    UserProfileDto getUserByUsername(String username);

    UserProfileDto updateUser(long id, UserProfileDto user);

    void updateAuthority(long id, Authority authority);

    void deleteUser(long id);

    UserProfileDto linkBoard(long userId, long boardId);

    UserProfileDto assignTask(long userId, long taskId);
}