package repository;

import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.category.service.CategoryService;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.schedule.service.ReservationService;
import com.iljungitjung.domain.schedule.service.ScheduleService;
import com.iljungitjung.domain.user.repository.UserRepository;
import main.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;


public class AbstractRepositoryTest extends AbstractTest {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;




}