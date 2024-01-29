package com.itheima.app;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName : Junit4Test
 * @Description : junit4测试类
 * @Author : 20609
 * @Date: 2023/2/6  15:49
 */
@SpringBootTest
//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class Junit4Test {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test  //注意注解为import org.junit.Test
    public void testJunit4(){
        log.info(String.valueOf(jdbcTemplate.getClass()));
    }
}
