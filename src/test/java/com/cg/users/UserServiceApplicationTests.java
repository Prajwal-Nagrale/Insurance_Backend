package com.cg.users;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


// ApplicationContext will be loaded from "classpath:/com/example/OrderServiceTest-context.xml"

@SpringBootTest(classes = UserServiceApplication.class)
@ExtendWith(SpringExtension.class)
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
