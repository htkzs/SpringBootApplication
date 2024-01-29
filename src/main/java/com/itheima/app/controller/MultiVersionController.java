package com.itheima.app.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.itheima.app.pojo.User;
import com.itheima.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试多数据源的动态切换访问和多数据源的统一事务处理
 */
@Controller
@ResponseBody
public class MultiVersionController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/v1/query")
    @DS("salve")
    public User queryUserV1(){
        User user = userService.getBaseMapper().selectById(43l);
        return user;
    }

    @RequestMapping("/v2/query")
    @DS("master")
    public User queryUserV2(){
        User user = userService.getBaseMapper().selectById(43l);
        return user;
    }

    @DSTransactional
    public void insertAll(){
        User user = new User("tomcat", 23, "2398298479@qq.com");
        userService.insertUserForDs1(user);
        User user1 = new User("tony", 28, "sdfcdfvcaz9@qq.com");
        userService.insertUserForDs2(user1);
    }


}
