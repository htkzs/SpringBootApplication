package com.itheima.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @Author GhostGalaxy
 * @Description //测试thymeleaf  测试地址http://localhost:8080/view/page
 * @Date 17:53:54 2022/12/23
 * @Param
 * @return
 **/
@Controller
public class ViewController {
    @RequestMapping("/view/page")
    public String goToPage(Model model){
        model.addAttribute("msg","hello world");
        model.addAttribute("link","http://www.baidu.com");
        return "success";
    }
}
