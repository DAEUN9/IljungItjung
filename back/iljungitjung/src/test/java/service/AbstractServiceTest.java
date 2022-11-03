package service;

import main.AbstractTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class AbstractServiceTest extends AbstractTest {

    @MockBean
    protected HttpSession httpSession;

}