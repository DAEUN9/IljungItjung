package com.iljungitjung.domain.category.entity;

import com.iljungitjung.domain.user.entity.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
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
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false, unique = true, name = "category_name")
    private String categoryName;

    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String time;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCategoryList(User user){
        user.getCategoryList().add(this);
        this.user = user;
    }

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
