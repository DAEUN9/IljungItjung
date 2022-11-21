package com.iljungitjung.global.login.repository;

import com.iljungitjung.global.login.entity.TemporaryUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TemporaryUserRepository extends CrudRepository<TemporaryUser, String> {
    Optional<TemporaryUser> findById(String id);
}
