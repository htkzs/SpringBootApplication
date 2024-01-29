package com.itheima.app.controller;

import com.itheima.app.pojo.User;
import com.itheima.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 使用mp查询数据库
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;
    @RequestMapping("/query/user")
    @ResponseBody
    public List<User> queryAllUser(){
        List<User> users = userService.list();
        users.forEach(System.out::println);
        return users;
    }
}

