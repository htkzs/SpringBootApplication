package com.itheima.app.Transactional;

import jdk.nashorn.internal.ir.Block;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName OrderService 演示事务的传播行为
 * @Description TODO
 * @Author 20609
 * @Date 2023/12/27 20:19
 * @Version 1.0
 */
@Service
public class OrderService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void createOrder(){
        //模拟创建订单完成后修改数据库操作
        jdbcTemplate.execute("insert into user values (1004,'hanmeimei',23)");
        OrderService orderService= (OrderService) AopContext.currentProxy();
        orderService.lockStock();
        orderService.integration();
    }
    //如果调用他的方法本来就是一个事务，则该方法会沿用，两个方法处于同一个事务控制下
    @Transactional(propagation = Propagation.REQUIRED)
    public void integration() {
        jdbcTemplate.execute("insert into user values (1005,'lilei',24)");
        //此处抛出异常不会导致lockStock() 方法的回滚操作，REQUIRES_NEW:声明该事务是一个新的事务会使用新的数据库连接，
        throw new NullPointerException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void lockStock() {
        jdbcTemplate.execute("insert into user values (1006,'xiaotu',24)");
        //这里抛出异常，虽然使用的是新的事务，但异常最终抛给了createOrder()，导致createOrder()也发生了回滚
        throw new NullPointerException();
    }

}
