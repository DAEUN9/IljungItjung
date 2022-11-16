package com.iljungitjung.domain.user.repository;

import com.iljungitjung.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.categoryList WHERE u.nickname=:nickname")
    Optional<User> findUserByNickname(String nickname);

    List<User> findByNicknameContaining(String nickname);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.categoryList WHERE u.email=:email")
    Optional<User> findUserByEmail(String email);

    boolean existsUserByEmail(String email);

    void deleteUserByEmail(String email);

    boolean existsUserByNickname(String nickname);
}
