package ca.airspeed.servingwebcontent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
class ServingWebContentApplicationTests {

	@Autowired
	private GreetingController greetingController;

	@Test
	void contextLoads() {
		assertThat(greetingController, notNullValue());
	}

}
