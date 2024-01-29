package com.itheima.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tuling.properties.AttributeProperties;
import org.tuling.service.HelloService;

/**
 * @ClassName HelloController 自定义starter(customize-springboot-starter)测试类 测试地址 http://localhost:8080/customize/hello?username=zhangsan
 *
 * @Description TODO
 * @Author 20609
 * @Date 2023/12/11 15:01
 * @Version 1.0
 */
@Controller
public class HelloController {
    @Autowired
    private HelloService helloService;
    @RequestMapping("/customize/hello")
    @ResponseBody
    public String sayHello(@RequestParam("username") String userName){
        String s = helloService.sayHello(userName);
        return s;
    }
}
