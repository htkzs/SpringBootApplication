package com.itheima.app.controller;

import com.itheima.app.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ProfileController 定义一个测试读取配置文件变量的profile类
 * @Value注解的使用取配置文件中的值，通过${} 如果配置文件中没该值，可以给一个默认值如 @Value(value = "${user.username:lisi}")
 * @Description TODO
 * @Author 20609
 * @Date 2023/12/10 20:49
 * @Version 1.0
 */
@Controller
public class ProfileController {

    @Value(value = "${user.username:lisi}")
    private String userName;

    @Autowired
    private People people;

    @RequestMapping("/profile/attribute")
    @ResponseBody
    public String getUserName(){
        return userName;
    }

    /**
     * @description: 测试springboot会根据环境配置文件的生效情况决定向IOC中注入Teacher(测试环境)还是boss(生产环境)
     * @author 20609
     * @date 2023/12/10 21:09
     * @version 1.0
     */
    @RequestMapping("/profile/people")
    @ResponseBody
    public People getPeople(){
        return people;
    }

}
