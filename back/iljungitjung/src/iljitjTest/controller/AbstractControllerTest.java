import com.fasterxml.jackson.databind.ObjectMapper;
import com.iljungitjung.IljungitjungApplication;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class AbstractControllerTest extends AbstractTest{

	@Autowired
	protected WebApplicationContext webApplicationContext;
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper= new ObjectMapper();

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
				.alwaysDo(print())
				.build();
	}

}