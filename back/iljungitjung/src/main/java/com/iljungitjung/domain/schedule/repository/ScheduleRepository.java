package com.iljungitjung.domain.schedule.repository;

import com.iljungitjung.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findScheduleByUserFromId(String userFromId);
    Optional<Schedule> findScheduleById(Long id);

}
