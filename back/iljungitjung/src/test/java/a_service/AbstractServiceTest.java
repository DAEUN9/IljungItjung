package a_service;

import com.iljungitjung.domain.category.service.CategoryService;
import com.iljungitjung.domain.notification.service.NotificationService;
import com.iljungitjung.domain.schedule.service.ReservationService;
import com.iljungitjung.domain.schedule.service.ScheduleService;
import main.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class AbstractServiceTest extends AbstractTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    NotificationService notificationService;
    public static Long categoryId=0L;
    public static Long scheduleId=0L;


}