package com.iljungitjung.domain.user.service;

import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeleteUserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init(){
        String userFromEmail = "userFrom@naver.com";
        String userFromNickname = "userFrom@naver.com";
        String userFromPhonenum = "01044444444";
        String userFromDes = "userFromDes";
        String userFromImagePath = "userFromImagePath";
        String userFromCloseTime = "userFromCloseTime";
        String userFromOpenTime = "userFromOpenTime";
        User userFrom = User.builder()
                .email(userFromEmail)
                .nickname(userFromNickname)
                .phonenum(userFromPhonenum)
                .description(userFromDes)
                .imagePath(userFromImagePath)
                .closeTime(userFromCloseTime)
                .openTime(userFromOpenTime)
                .build();

        String userToEmail = "userTo@naver.com";
        String userToNickname = "userTo@naver.com";
        String userToPhonenum = "01033333333";
        String userToDes = "userToDes";
        String userToImagePath = "userToImagePath";
        String userToCloseTime = "userToCloseTime";
        String userToOpenTime = "userToOpenTime";
        User userTo = User.builder()
                .email(userToEmail)
                .nickname(userToNickname)
                .phonenum(userToPhonenum)
                .description(userToDes)
                .imagePath(userToImagePath)
                .closeTime(userToCloseTime)
                .openTime(userToOpenTime)
                .build();
        Category category = createCategory();
        Schedule schedule = createSchedule();

        userRepository.save(userFrom);
        userRepository.save(userTo);

        category.updateUser(userTo);
        schedule.setScheduleResponseList(userFrom);
        schedule.setScheduleRequestList(userTo);

        categoryRepository.save(category);
        scheduleRepository.save(schedule);
    }

    //@Test
    @DisplayName("사용자 삭제 후 카테고리와 스케쥴 삭제")
    void deleteUserAndCategoriesAndSchedule(){
        String userToEmail = "userTo@naver.com";

        assertEquals(1, scheduleRepository.findAll().size());
        assertEquals(1, categoryRepository.findAll().size());
        assertEquals(2, userRepository.findAll().size());

        userService.deleteUserByEmail(userToEmail);

        assertEquals(0, scheduleRepository.findAll().size());
        assertEquals(0, categoryRepository.findAll().size());
        assertEquals(1, userRepository.findAll().size());
    }

    private Schedule createSchedule() {
        String categoryName = "categoryName";
        String categoryColor = "#000000";
        Date endTime = new Date();
        Date startTime = new Date();
        String contents = "contents";
        Type type = Type.REQUEST;
        String phoneNum = "01044444444";
        return Schedule.builder()
                .categoryName(categoryName)
                .color(categoryColor)
                .contents(contents)
                .type(type)
                .phonenum(phoneNum)
                .endDate(endTime)
                .startDate(startTime)
                .build();
    }

    private Category createCategory(){
        String categoryName = "categoryName";
        String categoryColor = "#000000";
        String time = "0130";

        return Category.builder()
                .categoryName(categoryName)
                .color(categoryColor)
                .time(time)
                .build();
    }
}