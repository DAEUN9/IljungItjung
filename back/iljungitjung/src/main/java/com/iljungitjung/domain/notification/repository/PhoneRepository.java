package com.iljungitjung.domain.notification.repository;

import com.iljungitjung.domain.notification.entity.Phone;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PhoneRepository extends CrudRepository<Phone, String> {

    Optional<Phone> findById(String id);
}
