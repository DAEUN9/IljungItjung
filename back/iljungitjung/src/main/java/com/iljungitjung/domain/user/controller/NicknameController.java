package com.iljungitjung.domain.user.controller;

import com.iljungitjung.domain.user.service.NicknameService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nicknames")
@Validated
public class NicknameController {

    private final NicknameService nicknameService;

    @GetMapping
    public ResponseEntity<CommonResponse> getUserInfo (
            @Pattern(regexp = "^[a-z|A-Z|0-9|ㄱ-ㅎ|가-힣]{2,10}$", message = "닉네임은 최소 2자, 최대 10자 영어, 한글, 숫자만 입력가능합니다.")
            @RequestParam(name = "nickname", required = true ) String nickname){
        return ResponseEntity.ok(CommonResponse.getSuccessResponse(nicknameService.checkAvailableNickname(nickname)));
    }
}
