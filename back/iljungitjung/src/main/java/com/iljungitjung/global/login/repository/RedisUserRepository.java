package com.iljungitjung.global.login.repository;

import com.iljungitjung.global.login.entity.RedisUser;
import org.springframework.data.repository.CrudRepository;

public interface RedisUserRepository extends CrudRepository<RedisUser, String> {
}
