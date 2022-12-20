package com.itheima.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *   @RestController = @ResponseBody+@Controller
 */
@RestController
public class HelloSpringBoot {
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello springboot 2";
    }
}
