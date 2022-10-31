package com.iljungitjung.domain.user.service;

import com.iljungitjung.domain.user.dto.SignUpDto;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.global.login.entity.TemporaryUser;
import com.iljungitjung.global.login.exception.ExpireTemporaryUserException;
import com.iljungitjung.global.login.repository.TemporaryUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final TemporaryUserRepository temporaryUserRepository;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public boolean isExistUserByEmail(String email){
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public void signUpUser(SignUpDto signUpDto, HttpServletRequest request) {
        log.debug("session Id : {}", request.getSession().getId());
        TemporaryUser temporaryUser = temporaryUserRepository.findById(request.getSession().getId()).orElseThrow(() -> {
            throw new ExpireTemporaryUserException();
        });
        User user = signUpDto.toEntity();
        user.signUp(temporaryUser);
        log.debug("user : {}", user);
        temporaryUserRepository.deleteById(request.getSession().getId());
        userRepository.save(user);
    }
}
