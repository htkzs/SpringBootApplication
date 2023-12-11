package com.itheima.app.service;

import com.itheima.app.entity.Person;
import com.itheima.app.mapper.PersonMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName OptionalPersonService
 * @Description TODO
 * @Author 20609
 * @Date 2023/12/9 19:10
 * @Version 1.0
 */
@Service
public class OptionalPersonService {
    @Resource
    private PersonMapper personMapper;

    public Person getPersonById(Integer no){
        Person person = personMapper.queryPersonById(no);
        return person;
    }

    public boolean insertPerson(Person person){
        Boolean inserted = personMapper.InsertPerson(person);
        return inserted;
    }
}
