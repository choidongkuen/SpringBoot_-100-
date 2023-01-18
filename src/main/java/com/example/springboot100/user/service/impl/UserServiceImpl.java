package com.example.springboot100.user.service.impl;


import com.example.springboot100.user.domain.dto.UserCreateDto.UserCreateRequest;
import com.example.springboot100.user.domain.dto.UserCreateDto.UserCreateResponse;
import com.example.springboot100.user.domain.dto.UserDto;
import com.example.springboot100.user.domain.dto.UserUpdateDto;
import com.example.springboot100.user.domain.dto.UserUpdateDto.UserUpdateRequest;
import com.example.springboot100.user.domain.dto.UserUpdateDto.UserUpdateResponse;
import com.example.springboot100.user.domain.entity.User;
import com.example.springboot100.user.domain.repository.UserRepository;
import com.example.springboot100.user.exception.UserException;
import com.example.springboot100.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.springboot100.exception.ErrorCode.NO_FOUND_USER;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserCreateResponse addUser(UserCreateRequest request) {

        return UserCreateResponse.from(userRepository.save(User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .phone(request.getPhone())
                .build()
        ));
    }

    @Transactional
    @Override
    public UserUpdateResponse updateUser(Long id, UserUpdateRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(NO_FOUND_USER));

        return UserUpdateResponse.from(user.updateUser(request));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(NO_FOUND_USER));

        return UserDto.from(user);

    }
}