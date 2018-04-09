package com.yexuejc.springboot.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationRun.class)
public class ApplicationTest {

    @Test
    public void contextLoads() {
        System.out.printf("springboot test is runing");
    }

}
