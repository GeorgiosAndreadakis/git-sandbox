package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bootexample.SpringBootGradleMultiprojectApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootGradleMultiprojectApplication.class)
@WebAppConfiguration
public class SpringBootGradleMultiprojectApplicationTests {

	@Test
	public void contextLoads() {
	}

}
