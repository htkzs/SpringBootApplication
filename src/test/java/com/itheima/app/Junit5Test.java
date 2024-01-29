package com.itheima.app;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName : junit5Test
 * @Description : junit5测试类
 * @Author : 20609
 * @Date: 2023/2/6  15:21
 */
@SpringBootTest
// @SpringBootTest Junit类具有Spring的功能，@Autowired、比如 @Transactional 标注测试方法，测试完成后自动回滚
@Slf4j
public class Junit5Test {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test //注意@Test注解在org.junit.jupiter.api.Test下
    public void testJunit5(){
        log.info(String.valueOf(jdbcTemplate.getClass()));
    }
    //测试结果为  class org.springframework.jdbc.core.JdbcTemplate
    @Test
    @DisplayName("展示测试类或者测试方法的名称")
    void testDisplayName(){
        System.out.println("我的第一个测试方法");
    }
    @Test
    @BeforeEach
    void testBeforeEach(){
        System.out.println("在每个单元测试之前执行一次");
    }
    @Test
    @AfterEach
    void testAfterEach(){
        System.out.println("在每个单元测试之后执行一次");
    }

    @Test
    @BeforeAll
    static void testBeforeAll(){   //注意此处必须为一个静态方法
        System.out.println("在所有的单元测试之前执行一次");
    }

    @Test
    @AfterAll
    static void testAfterAll(){   //注意此处必须为一个静态方法
        System.out.println("在所有的单元测试之后执行一次");
    }
    /**
    * @Param :
    * @Description :  如果方法执行超时将报如下异常 java.util.concurrent.TimeoutException: testTimeOut() timed out after 5 seconds
    * @Author : 20609
    * @Date : 2023/2/7 11:25
    */

    @Test
    @Timeout(value = 5,unit = TimeUnit.SECONDS)
    void testTimeOut() throws InterruptedException {
        Thread.sleep(6000);
    }
    /**
    * @Param :
    * @Description :  重复子项该测试方法指定测试次数
    * @Author : 20609
    * @Date : 2023/2/7 11:30
    */

    @RepeatedTest(value = 5)
    void testRepeatedTest(){
        System.out.println("重复执行测试方法5次");
    }
}
