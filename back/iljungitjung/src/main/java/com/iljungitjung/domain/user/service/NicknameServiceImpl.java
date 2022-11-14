package com.iljungitjung.domain.user.service;

import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NicknameServiceImpl implements NicknameService{

    private final UserRepository userRepository;

    @Override
    public boolean checkAvailableNickname(String nickname) {
        User user = userRepository.findUserByNickname(nickname).orElse(null);
        if (user == null) return true;
        return false;
    }
}
