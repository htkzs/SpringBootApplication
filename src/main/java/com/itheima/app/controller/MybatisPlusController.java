package com.itheima.app.controller;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.app.entity.User;
import com.itheima.app.mapper.UserMapper;
import com.itheima.app.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName MybatisPlusController
 * @Description TODO
 * @Author 20609
 * @Date 2023/12/10 19:12
 * @Version 1.0
 */
@Slf4j
@Controller
public class MybatisPlusController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService UserServiceImpl;
    /**
     * @description: 测试地址：http://localhost:8080/user/1
     * @author 20609
     * @date 2023/12/10 19:40
     * @version 1.0
     */
    @RequestMapping("/user/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") Integer id){
        User user = userMapper.selectById(id);
        log.info(user.toString());
        return user;
    }
    /**
     * @description: 测试地址：http://localhost:8080/user/mp/1
     * @author 20609
     * @date 2023/12/10 19:40
     * @version 1.0
     */
    @RequestMapping("/user/mp/{id}")
    @ResponseBody
    public User getUserByIdWithMP(@PathVariable("id") Integer id){
        User user = (User) UserServiceImpl.getById(id);
        System.out.println(user);
        return user;
    }
}
