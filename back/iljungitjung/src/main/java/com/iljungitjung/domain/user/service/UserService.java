package com.iljungitjung.domain.user.service;

import com.iljungitjung.domain.user.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmail(String email);

    boolean isExistUserByEmail(String email);
}
