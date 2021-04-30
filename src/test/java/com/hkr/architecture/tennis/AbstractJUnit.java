package com.hkr.architecture.tennis;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-09-22 11:50
 * This is my work in rental code.
 * mail:chenxiangning@rental.com
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationConfig.class, SpringTestCommonConfig.class})
@TestPropertySource("classpath:test-spring.properties")
@ComponentScan(basePackages = {"com.hkr.architecture.tennis.springscan"})
public abstract class AbstractJUnit {


}
