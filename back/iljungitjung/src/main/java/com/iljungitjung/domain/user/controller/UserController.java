package com.iljungitjung.domain.user.controller;

import com.iljungitjung.domain.user.dto.SignUpDto;
import com.iljungitjung.domain.user.dto.UpdateUser;
import com.iljungitjung.domain.user.service.UserService;
import com.iljungitjung.global.common.CommonResponse;
import com.iljungitjung.global.login.entity.RedisUser;
import com.iljungitjung.global.login.exception.ExpireRedisUserException;
import com.iljungitjung.global.login.repository.RedisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;

    private final RedisUserRepository redisUserRepository;

    @PostMapping
    public void signUpUser(@Valid @RequestBody SignUpDto signUpDto, HttpServletRequest request){
        userService.signUpUser(signUpDto, request);
    }

    @GetMapping
    public ResponseEntity<CommonResponse> getUserInfo (
                                                        @Pattern(regexp = "^[a-z|A-Z|0-9|ㄱ-ㅎ|가-힣]{2,10}$", message = "닉네임은 최소 2자, 최대 10자 영어, 한글, 숫자만 입력가능합니다.") @RequestParam(name = "nickname", required = false) String nickname,
                                                        @RequestParam(name = "isSearch", required = false) boolean isSearch,
                                                        HttpSession session){
        if(isSearch) return ResponseEntity.ok(CommonResponse.getSuccessResponse(userService.getUserInfoList(nickname)));
        if(Objects.isNull(nickname)) return ResponseEntity.ok(CommonResponse.getSuccessResponse(userService.getUserInfo(session)));
        return ResponseEntity.ok(CommonResponse.getSuccessResponse(userService.getUserInfo(nickname)));
    }

    @DeleteMapping
    public void deleteUser(HttpSession session){
        RedisUser redisUser = redisUserRepository.findById(session.getId()).orElseThrow(() -> {
            throw new ExpireRedisUserException();
        });
        userService.deleteUserByEmail(redisUser.getEmail());
    }

    @PutMapping
    public void updateUser(@Valid @RequestBody UpdateUser updateUser, HttpSession session){
        userService.updateUser(updateUser, session);
    }

    @GetMapping("/{nickname}")
    public void isExistNickname(@Pattern(regexp = "^[a-z|A-Z|0-9|ㄱ-ㅎ|가-힣]{2,10}$", message = "닉네임은 최소 2자, 최대 10자 영어, 한글, 숫자만 입력가능합니다.") @PathVariable(name = "nickname") String nickname){
        userService.isExistUserByNickname(nickname);
    }
}
