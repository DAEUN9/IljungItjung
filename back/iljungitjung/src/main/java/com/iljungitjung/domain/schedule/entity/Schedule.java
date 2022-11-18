package com.iljungitjung.domain.schedule.entity;


import com.iljungitjung.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
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
    @JoinColumn(name = "user_to_id")
    private User userTo;

    @ManyToOne
    @JoinColumn(name = "user_from_id")
    private User userFrom;

    @Column(nullable = false, name="start_date")
    private Date startDate;

    @Column(nullable = false, name="end_date")
    private Date endDate;

    @Column(nullable = false, name="category_name")
    private String categoryName;

    private String color;

    private String contents;

    private String phonenum;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Type type;

    private String cancelFrom;
    private String reason;

    public String getDateFormat(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return simpleDateFormat.format(date);
    }
    public void setScheduleRequestList(User user){
        user.getScheduleRequestList().add(this);
        this.userFrom = user;
    }

    public void setScheduleResponseList(User user){
        user.getScheduleResponseList().add(this);
        this.userTo = user;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Builder
    public Schedule(User userFrom, User userTo, Date startDate, Date endDate, String categoryName, String color, String contents, String phonenum, Type type) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.startDate = startDate;
        this.endDate=endDate;
        this.categoryName=categoryName;
        this.color = color;
        this.contents=contents;
        this.phonenum = phonenum;
        this.type=type;
    }
    public void accepted() {
        this.type= Type.ACCEPT;
    }

    public void deleted() {
        this.type= Type.DELETE;
    }

    public void canceled(String cancelFrom, String reason){
        this.cancelFrom=cancelFrom;
        this.reason=reason;
        this.type=Type.CANCEL;
    }

}
