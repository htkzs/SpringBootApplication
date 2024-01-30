package com.itheima.app;

import com.itheima.app.Transactional.OrderService;
import com.itheima.app.Transactional.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName TransactionTest  测试事务的失效
 * @Description TODO
 * @Author 20609
 * @Date 2023/12/27 20:41
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Test
    public void createOrder(){
        orderService.createOrder();

    }
    @Test
    public  void  userOrder(){
        userService.createOrder();
    }
}
