package com.iljungitjung.domain.user.repository;

import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUsersByNickname(String nickname);

}
