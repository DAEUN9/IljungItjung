package com.iljungitjung.domain.schedule.repository;

import com.iljungitjung.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findScheduleById(Long id);
    List<Schedule> findByUserTo_NicknameIs(String nickname);

    List<Schedule> findByUserFrom_IdIs(Long id);

    List<Schedule> findByStartDateBetween(Date startToday, Date endToday);

}
