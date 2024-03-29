package com.example.news.web.controller;


import com.example.news.exception.*;
import com.example.news.mapper.UserMapper;
import com.example.news.model.User;
import com.example.news.service.UserService;
import com.example.news.web.model.response.*;
import com.example.news.web.model.request.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<ModelListResponse<UserResponse>> getAllUsers(@Valid PaginationRequest request) {
        Page<User> userPage = userService.findAll(request.pageRequest());

        return ResponseEntity.ok(
                ModelListResponse.<UserResponse>builder()
                        .totalCount(userPage.getTotalElements())
                        .data(userPage.stream().map(userMapper::userToResponse).toList())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.userToResponse(userService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UpsertUserRequest request) {
        if (userService.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException(MessageFormat.format("User with email {0} already exists!", request.getEmail()));
        }
        if (userService.existsByUsername(request.getUsername())) {
            throw new AlreadyExistsException(MessageFormat.format("User with email {0} already exists!", request.getEmail()));
        }


        User newUser = userMapper.upsertRequestToUser(request);
        log.debug(newUser.toString());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(userService.save(newUser)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpsertUserRequest request, @PathVariable Long id) {
        User updatedUser = userService.update(id, userMapper.upsertRequestToUser(request));

        return ResponseEntity.ok(userMapper.userToResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
