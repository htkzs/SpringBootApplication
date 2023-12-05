package com.itheima.app;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * @ClassName : JdbcTemplateTest
 * @Description : 测试JdbcTemplate
 * @Author : 20609
 * @Date: 2023/1/1  11:52
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    @Test
    public void contextLoads() {
        Long aLong = jdbcTemplate.queryForObject("select count(*) from user", Long.class);
        System.out.println(aLong);

        log.info("数据源的类型为：{}",dataSource.getClass());  //数据源的类型为：class com.alibaba.druid.pool.DruidDataSource
    }
}
