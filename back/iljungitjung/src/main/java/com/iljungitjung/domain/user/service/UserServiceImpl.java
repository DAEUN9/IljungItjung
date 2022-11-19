package com.iljungitjung.domain.user.service;

import com.iljungitjung.domain.user.dto.*;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.exception.NoExistUserException;
import com.iljungitjung.domain.user.exception.AlreadyExistUserException;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.global.login.entity.RedisUser;
import com.iljungitjung.global.login.entity.TemporaryUser;
import com.iljungitjung.global.login.exception.ExpireLoginUserException;
import com.iljungitjung.global.login.exception.ExpireRedisUserException;
import com.iljungitjung.global.login.exception.ExpireTemporaryUserException;
import com.iljungitjung.global.login.repository.RedisUserRepository;
import com.iljungitjung.global.login.repository.TemporaryUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final TemporaryUserRepository temporaryUserRepository;

    private final RedisUserRepository redisUserRepository;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public boolean isExistUserByEmail(String email){
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public SignUpUserResponseDto signUpUser(SignUpDto signUpDto, HttpServletRequest request) {
        log.debug("session Id : {}", request.getSession().getId());
        TemporaryUser temporaryUser = temporaryUserRepository.findById(request.getSession().getId()).orElseThrow(() -> {
            throw new ExpireTemporaryUserException();
        });
        User user = signUpDto.toEntity();
        user.signUp(temporaryUser);
        if(userRepository.existsUserByEmail(user.getEmail())){
            throw new AlreadyExistUserException();
        }
        log.debug("user : {}", user);
        temporaryUser.matchPhonenum(signUpDto);
        temporaryUserRepository.deleteById(request.getSession().getId());
        user = userRepository.save(user);

        return new SignUpUserResponseDto(user.getId());
    }

    @Override
    public UserInfo getUserInfo(String nickname) {
        User user = userRepository.findUserByNickname(nickname).orElseThrow(() -> {
            throw new NoExistUserException();
        });
        UserInfo userInfo = new UserInfo(user);
        userInfo.convertCategories(user.getCategoryList());
        return userInfo;
    }

    @Override
    public UserInfo getUserInfo(HttpSession session) {
        RedisUser redisUser = redisUserRepository.findById(session.getId()).orElseThrow(() -> {
            throw new ExpireRedisUserException();
        });
        return getUserInfo(redisUser.getNickname());
    }

    public User findUserBySessionId(HttpSession session) {
        RedisUser redisUser = redisUserRepository.findById(session.getId()).orElseThrow(() -> {
            throw new ExpireLoginUserException();
        });
        User user = userRepository.findUserByEmail(redisUser.getEmail()).orElseThrow(() -> {
            throw new NoExistUserException();
        });

        return user;
    }

    @Override
    public UserInfoList getUserInfoList(String nickname) {
        List<User> userList = userRepository.findByNicknameContaining(nickname);
        List<UserInfo> userInfoList = userList.stream().map(user -> getUserInfo(user.getNickname())).collect(Collectors.toList());
        return new UserInfoList(userInfoList);
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        userRepository.deleteUserByEmail(email);
    }

    @Override
    public void updateUser(UpdateUser updateUser, HttpSession session) {
        RedisUser sessionUser = redisUserRepository.findById(session.getId()).orElseThrow(() -> {
            throw new ExpireTemporaryUserException();
        });
        User user = userRepository.findUserByEmail(sessionUser.getEmail()).orElseThrow(() -> {
            throw new NoExistUserException();
        });
        user.updateUser(updateUser);
        userRepository.save(user);
        sessionUser.updateRedisUser(updateUser);
        redisUserRepository.save(sessionUser);
        log.debug("user save ok");
    }

    @Override
    public void isExistUserByNickname(String nickname) {
        if(userRepository.existsUserByNickname(nickname))
            throw new AlreadyExistUserException();
    }
}
