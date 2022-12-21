package com.itheima.app;

import com.itheima.app.entity.Pet;
import com.itheima.app.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @SpringBootApplication：标识该应用是一个Springboot应用
 * 注意这里不能使用application明明xml文件否则会报错
 */
@ImportResource(value="classpath:bean.xml")
@SpringBootApplication
public class HelloSpringBootApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(HelloSpringBootApplication.class, args);
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        System.out.println(run.getBeanDefinitionCount());
//        User getUser =(User)run.getBean("getUser");
//        System.out.println(getUser);
//        Pet pet = (Pet)run.getBean("getPet");
//        System.out.println(pet);
//        boolean getUser = run.containsBean("getUser");
//        System.out.println(getUser);
//        User user = (User)run.getBean("getUser");
//        System.out.println(user);
    }
}
