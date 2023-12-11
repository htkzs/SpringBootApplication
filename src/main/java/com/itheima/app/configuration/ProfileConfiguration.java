package com.itheima.app.configuration;

import com.itheima.app.entity.Boss;
import com.itheima.app.entity.People;
import com.itheima.app.entity.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @ClassName ProfileConfiguration 测试profile注解 在生产环境和测试环境时分别注入不同的bean 可以理解为一种条件注解来使用 并且@Profile还可以标注在类上
 * @Description TODO
 * @Author 20609
 * @Date 2023/12/10 21:21
 * @Version 1.0
 */
//@Configuration
public class ProfileConfiguration {

    @Bean
    @Profile(value = "test")
    public People getTeacher(){
        return new Teacher(1001,"mr zhang","teacher");
    }

    @Bean
    @Profile(value = "prod")
    public People getBoss(){
        return new Boss(1002,"mr wang","boss");
    }

}
