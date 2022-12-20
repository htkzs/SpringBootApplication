package com.itheima.app.configuration;

import com.itheima.app.entity.Car;
import com.itheima.app.entity.Pet;
import com.itheima.app.entity.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = true)
@EnableConfigurationProperties(Car.class)
public class ApplicationConfig {

     @ConditionalOnBean(name = "getPet")
     @Bean
     public User getUser(){
         User zhangsan = new User("zhangsan", 23);
         zhangsan.setPet(getPet());
         return zhangsan;
     }
     @Bean
     public Pet getPet(){
         return new Pet("哈士奇",2);
     }
}
