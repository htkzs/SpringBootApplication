package com.itheima.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *   @RestController = @ResponseBody+@Controller
 */
@RestController
@Slf4j
public class HelloSpringBoot {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello springboot 2";
    }

    @RequestMapping("/sql")
    public String testDruid(){
        String sql="select count(*) from user";
        Long aLong = jdbcTemplate.queryForObject(sql, Long.class);
        return "查询到的总的数据行数为:"+aLong;
    }
}
