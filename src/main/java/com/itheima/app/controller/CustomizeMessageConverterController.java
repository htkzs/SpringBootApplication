package com.itheima.app.controller;

import com.itheima.app.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName CustomizeMessageConverterController 自定义的MessageConverter测试
 * 场景描述：
 *      1、浏览器发请求直接返回xmL[ application/xmL ]    jacksonXMlConverter
 *      2、如果是ajax请求返回json[ application/json]    jacksonJsonConverter
 *      3、如果硅谷app发请求，返回自定义协议数据 [application/x-guigu]          CustomizeMessageConverter
 *
 * @Description TODO
 * @Author 20609
 * @Date 2023/12/8 15:04
 * @Version 1.0
 */
@Controller
public class CustomizeMessageConverterController {
    @RequestMapping("/app/get")
    @ResponseBody
    public Person getPerson(){
        Person person = new Person();
        person.setName("Alibaba");
        person.setAge(21);
        return person;
    }
}
