package com.iljungitjung.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UserInfoList {
    List<UserInfo> users;
}
