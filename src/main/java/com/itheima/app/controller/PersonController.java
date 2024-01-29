package com.itheima.app.controller;

import com.itheima.app.entity.Person;
import com.itheima.app.service.OptionalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @ClassName PersonController
 * @Description TODO
 * @Author 20609
 * @Date 2023/12/9 19:12
 * @Version 1.0
 */
@Controller
public class PersonController {
    @Autowired
    private OptionalPersonService optionalPersonService;
    @RequestMapping("/person")
    @ResponseBody
    public Person getPersonById(@RequestParam("no") Integer no){
        Person person = optionalPersonService.getPersonById(no);
        return person;
    }

    //通过前端传入一个person对象
    //http://localhost:8080/insert?name=jack2&age=23&height=1.75&addressId=2
    @RequestMapping("/insert")
    @ResponseBody
    public Person insertPerson(@RequestParam Map<String,String> allParameter){
        Person person = new Person();
        //这里不需要传入no的值，no的值由mysql维护
        person.setName(allParameter.get("name"));
        person.setAge(Integer.valueOf(allParameter.get("age")));
        person.setHeight(Float.parseFloat(allParameter.get("height")));
        person.setAddressId(Integer.valueOf(allParameter.get("addressId")));
        System.out.println(person);
        boolean b = optionalPersonService.insertPerson(person);
        System.out.println("person数据插入结果："+b);
        return person;
    }

}
