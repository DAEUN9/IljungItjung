package com.iljungitjung.domain.category.entity;

import com.iljungitjung.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false, name = "category_name")
    private String categoryName;

    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String time;

    public void updateUser(User user) {
        this.user = user;
    }

    public void updateCategoryList(User user){
        user.getCategoryList().add(this);
        this.user = user;
    }

    @Builder
    public Category(String categoryName, String color, String time) {
        this.categoryName = categoryName;
        this.color = color;
        this.time = time;
    }
}
