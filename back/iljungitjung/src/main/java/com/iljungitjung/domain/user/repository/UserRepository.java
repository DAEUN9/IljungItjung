package com.iljungitjung.domain.user.repository;

import com.iljungitjung.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByNickname(String nickname);

    Optional<User> findUserByEmail(String email);

    boolean existsUserByEmail(String email);
}
