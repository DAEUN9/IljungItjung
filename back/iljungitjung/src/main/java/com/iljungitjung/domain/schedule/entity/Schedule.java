package com.iljungitjung.domain.schedule.entity;


import com.iljungitjung.domain.user.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne
    @JoinColumn
    private Users userTo;

    @ManyToOne
    @JoinColumn
    private Users userFrom;


    @Column(nullable = false, name="start_date")
    private Date startDate;

    @Column(nullable = false, name="end_date")
    private Date endDate;

    @Column(nullable = false, name="category_name")
    private String categoryName;

    private String color;

    @Column(nullable = false)
    private String contents;

    private String phonenum;

    @Column(nullable = false)
    private Type type;

    private String reason;

    public void setScheduleRequestList(Users user){
        user.getScheduleRequestList().add(this);
        this.userTo=user;
    }

    public void setScheduleResponseList(Users user){
        user.getScheduleResponseList().add(this);
        this.userFrom=user;
    }


    @Builder
    public Schedule(Date startDate, Date endDate, String categoryName, String color, String contents, String phonenum, Type type) {
        this.startDate = startDate;
        this.endDate=endDate;
        this.categoryName=categoryName;
        this.color = color;
        this.contents=contents;
        this.phonenum = phonenum;
        this.type=type;
    }
    public void accpeted() {
        this.type= Type.ACCEPT;
    }
    public void canceled(String reason){
        this.reason=reason;
        this.type=Type.CANCEL;
    }

}
