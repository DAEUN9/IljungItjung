package com.iljungitjung.domain.schedule.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

    @Column(nullable = false, name="start_date")
    private Date startDate;

    @Column(nullable = false, name="end_date")
    private Date endDate;

    @Column(nullable = false, name="category_name")
    private String categoryName;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String phonenum;

    @Column(nullable = false)
    private Type type;

    @Builder
    public Schedule(String userFromId, String userToId, Date startDate, Date endDate, String categoryName, String color, String contents, String phonenum, Type type) {
        this.userFromId = userFromId;
        this.userToId=userToId;
        this.startDate = startDate;
        this.endDate=endDate;
        this.categoryName=categoryName;
        this.color = color;
        this.contents=contents;
        this.phonenum = phonenum;
        this.type=type;
    }


}
