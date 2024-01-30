package com.itheima.app.Transactional;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserService 演示事务的失效
 * @Description TODO
 * @Author 20609
 * @Date 2023/12/27 20:54
 * @Version 1.0
 */
@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional
    public void createOrder(){
        //模拟创建订单完成后修改数据库操作
        jdbcTemplate.execute("insert into user values (1004,'hanmeimei',23)");
        lockStock();
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void integration() {
        jdbcTemplate.execute("insert into user values (1005,'lilei',24)");
    }
    //通过this调用lockStock()，导致@Transactional失效，即使这里设置REQUIRES_NEW还会导致事务的回滚
    @Transactional(propagation = Propagation.NEVER)
    public void lockStock() {
        jdbcTemplate.execute("insert into user values (1006,'xiaotu',24)");
    }



}
