package com.iljungitjung.domain.user.controller;

import com.iljungitjung.domain.user.dto.SignUpDto;
import com.iljungitjung.domain.user.service.UserService;
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

    @PostMapping
    public void signUpUser(@Valid @RequestBody SignUpDto signUpDto, HttpServletRequest request){
        userService.signUpUser(signUpDto, request);
    }

    @GetMapping
    public ResponseEntity<CommonResponse> getUserInfo (@Pattern(regexp = "^[a-z|A-Z|0-9|ㄱ-ㅎ|가-힣]{2,10}$", message = "닉네임은 최소 2자, 최대 10자 영어, 한글, 숫자만 입력가능합니다.") @RequestParam(name = "nickname", required = false) String nickname, HttpSession session){
        if(Objects.isNull(nickname)) return ResponseEntity.ok(CommonResponse.getSuccessResponse(userService.getUserInfo(session)));
        return ResponseEntity.ok(CommonResponse.getSuccessResponse(userService.getUserInfo(nickname)));
    }
}
