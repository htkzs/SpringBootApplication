package com.itheima.app;

import com.itheima.app.entity.Pet;
import com.itheima.app.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Map;
import java.util.Set;

/**
 * @SpringBootApplication：标识该应用是一个Springboot应用
 * 注意这里不能使用application明明xml文件否则会报错
 */
@ImportResource(value="classpath:bean.xml")
@SpringBootApplication
@MapperScan(basePackages = "com.itheima.app.mapper")
public class HelloSpringBootApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(HelloSpringBootApplication.class, args);
//        String[] beanDefinitionNames = run.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
//        System.out.println(run.getBeanDefinitionCount());


//        User getUser =(User)run.getBean("getUser");
//        System.out.println(getUser);
//        Pet pet = (Pet)run.getBean("getPet");
//        System.out.println(pet);
//        boolean getUser = run.containsBean("getUser");
//        System.out.println(getUser);
//        User user = (User)run.getBean("getUser");
//        System.out.println(user);

        ConfigurableEnvironment environment = run.getEnvironment();
        //获取环境变量的值
        Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
        //获取系统属性
        Map<String, Object> systemProperties = environment.getSystemProperties();

        Set<String> keySet = systemEnvironment.keySet();
        for (String key:keySet){
            System.out.println("环境变量的属性为："+key+":对应的值为"+systemEnvironment.get(key));
        }
        Set<String> set = systemProperties.keySet();
        for (String key:set){
            System.out.println("系统属性名称为："+key+":对应的值为"+systemProperties.get(key));
        }
    }
}
