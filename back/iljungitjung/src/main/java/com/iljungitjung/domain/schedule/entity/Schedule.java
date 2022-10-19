package com.iljungitjung.domain.schedule.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

//    @JoinColumn(name="user_id")
//    private User userFrom;

//    @JoinColumn(name="user_id")
//    private User userTo;

    @Column(nullable = false, name="user_from_id")
    private String userFromId;

    @Column(nullable = false, name="user_to_id")
    private String userToId;

    @Column(nullable = false, name="start_time")
    private String startTime;

    @Column(nullable = false, name="end_time")
    private String endTime;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false, name="category_name")
    private String categoryName;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String phonenum;




}
