package com.iljungitjung.domain.schedule.repository;

import com.iljungitjung.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findScheduleById(Long id);
    List<Schedule> findByUserTo_IdIs(Long id);

    List<Schedule> findByUserFrom_IdIs(Long id);

}
