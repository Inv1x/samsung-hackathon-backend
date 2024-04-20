package com.samsung_hackathon.backend.service.impl;

import com.samsung_hackathon.backend.exception.UserAlreadyExistsException;
import com.samsung_hackathon.backend.controller.dto.UserProfileDto;
import com.samsung_hackathon.backend.controller.dto.UserRegisterDto;
import com.samsung_hackathon.backend.dao.AuthorityRepository;
import com.samsung_hackathon.backend.dao.BoardRepository;
import com.samsung_hackathon.backend.dao.TaskRepository;
import com.samsung_hackathon.backend.dao.UserRepository;
import com.samsung_hackathon.backend.entity.Authority;
import com.samsung_hackathon.backend.entity.Board;
import com.samsung_hackathon.backend.entity.ColumnTask;
import com.samsung_hackathon.backend.entity.User;
import com.samsung_hackathon.backend.exception.UserNotFoundException;
import com.samsung_hackathon.backend.mapper.UserMapper;
import com.samsung_hackathon.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    private final BoardRepository boardRepository;

    @Autowired
    private final TaskRepository taskRepository;

    @Autowired
    private final AuthorityRepository authorityRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserProfileDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserProfileDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserProfileDto createUser(UserRegisterDto userRegisterDto) {
        if (userRepository.findByUsername(userRegisterDto.getUsername()).isPresent())
            throw new UserAlreadyExistsException("User already exists");

//        Optional<Authority> authorityOptional = authorityRepository.findByAuthority("ROLE_USER");
//        if (authorityOptional.isEmpty()) throw new RuntimeException("Authority not found!");

        User user = UserMapper.toUserEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        Set<Authority> authority = new HashSet<>();
//        authority.add(authorityOptional.get());
        user.setAuthorities(authority);

        return UserMapper.toUserProfileDto(userRepository.saveAndFlush((user)));
    }

    @Override
    public UserProfileDto getUser(long id) throws RuntimeException{
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) throw new UserNotFoundException("User with ID " + id + " not found");

        return UserMapper.toUserProfileDto(userOptional.get());
    }

    @Override
    public UserProfileDto getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) throw new UserNotFoundException("User with username " + username + " not found");

        return UserMapper.toUserProfileDto(userOptional.get());
    }

    @Override
    public UserProfileDto updateUser(long id, UserProfileDto userProfileDto) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) throw new UserNotFoundException("User with ID " + id + " not found");

        User user = userOptional.get();
        if (userProfileDto.getName() != null) user.setName(userProfileDto.getName());
        if (userProfileDto.getUsername() != null) user.setUsername(userProfileDto.getUsername());
        if (userProfileDto.getEmail() != null) user.setEmail(userProfileDto.getEmail());
        if (userProfileDto.getBoards() != null) user.setBoards(userProfileDto.getBoards());
        if (userProfileDto.getAssignedColumnTasks() != null) user.setAssignedColumnTasks(userProfileDto.getAssignedColumnTasks());

        return UserMapper.toUserProfileDto(userRepository.save(user));
    }

    @Override
    public void updateAuthority(long id, Authority authority) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) throw new UserNotFoundException("User with ID " + id + " not found");

        Optional<Authority> authorityOptional = authorityRepository.findByAuthority(authority.getAuthority());
        if (authorityOptional.isEmpty()) throw new RuntimeException("Authority not found!");

        User user = userOptional.get();
        authority = authorityOptional.get();

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        user.setAuthorities(authorities);

        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        userRepository.delete(user);
    }

    @Override
    public UserProfileDto linkBoard(long userId, long boardId) {
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
        return UserMapper.toUserProfileDto(userRepository.saveAndFlush(user));
    }

    @Override
    public UserProfileDto assignTask(long userId, long taskId) {
        User user;
        ColumnTask task;

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

        user.getAssignedColumnTasks().add(task);
        task.setAssignedTo(user);
        return UserMapper.toUserProfileDto(userRepository.saveAndFlush(user));
    }
}
