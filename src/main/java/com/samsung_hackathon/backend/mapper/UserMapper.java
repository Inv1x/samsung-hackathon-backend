package com.samsung_hackathon.backend.mapper;

import com.samsung_hackathon.backend.controller.dto.UserProfileDto;
import com.samsung_hackathon.backend.controller.dto.UserRegisterDto;
import com.samsung_hackathon.backend.controller.dto.UserShortDto;
import com.samsung_hackathon.backend.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public User toUserEntity(UserProfileDto userProfileDto){
        return User.builder()
                .id(userProfileDto.getId())
                .name(userProfileDto.getName())
                .username(userProfileDto.getUsername())
                .email(userProfileDto.getEmail())
                .boards(userProfileDto.getBoards())
                .assignedColumnTasks(userProfileDto.getAssignedColumnTasks())
                .build();
    }

    public User toUserEntity(UserRegisterDto userRegisterDto) {

        User user = User.builder()
                .username(userRegisterDto.getUsername())
                .password(userRegisterDto.getPassword())
                .email(userRegisterDto.getEmail())
                .build();

        if (userRegisterDto.getId() != 0) user.setId(userRegisterDto.getId());

        return user;
    }

    public UserRegisterDto toUserRegisterDto(User user) {

        return UserRegisterDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getPassword())
                .build();
    }

    public UserProfileDto toUserProfileDto(User user) {

        return UserProfileDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .boards(user.getBoards())
                .assignedColumnTasks(user.getAssignedColumnTasks())
                .build();
    }

    public UserShortDto toUserShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
