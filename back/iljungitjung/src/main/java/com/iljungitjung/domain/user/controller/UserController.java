package com.iljungitjung.domain.user.controller;

import com.iljungitjung.domain.user.dto.SignUpDto;
import com.iljungitjung.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public void signUpUser(@Valid @RequestBody SignUpDto signUpDto, HttpServletRequest request){
        userService.signUpUser(signUpDto, request);
    }
}
