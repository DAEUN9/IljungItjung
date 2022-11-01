package com.iljungitjung.domain.user.service;

import com.iljungitjung.domain.user.dto.SignUpDto;
import com.iljungitjung.domain.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmail(String email);

    boolean isExistUserByEmail(String email);

    void signUpUser(SignUpDto signUpDto, HttpServletRequest request);
}
