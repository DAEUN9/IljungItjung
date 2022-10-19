package com.iljungitjung.domain.category.entity;

//import com.iljungitjung.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private User user;

    private String category_name;

    private String color;

    private String time;

//    public Category(User user, String category_name, String color, String time) {
//        this.user = user;
//        this.category_name = category_name;
//        this.color = color;
//        this.time = time;
//    }

    public Category(String category_name, String color, String time) {
        this.category_name = category_name;
        this.color = color;
        this.time = time;
    }

    public void change(String category_name, String color, String time) {
        this.category_name = category_name;
        this.color = color;
        this.time = time;
    }
}
