package com.iljungitjung.domain.category.entity;

//import com.iljungitjung.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private User user;

    @Column(nullable = false, name = "category_name")
    private String categoryName;

    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String time;

//    public Category(User user, String category_name, String color, String time) {
//        this.user = user;
//        this.category_name = category_name;
//        this.color = color;
//        this.time = time;
//    }

    @Builder
    public Category(String categoryName, String color, String time) {
        this.categoryName = categoryName;
        this.color = color;
        this.time = time;
    }

    public void change(Category category) {
        this.categoryName = category.getCategoryName();
        this.color = category.getColor();
        this.time = category.getTime();
    }
}
