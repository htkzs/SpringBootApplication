package com.itheima.app.controller;

import com.itheima.app.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class ConfigurationController {
    @Autowired
    private Car car;
    @RequestMapping("/car")
    public Car getCar(){
        return car;
    }
}
