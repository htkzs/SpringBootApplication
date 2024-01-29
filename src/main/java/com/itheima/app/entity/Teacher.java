package com.itheima.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @ClassName Teacher 注意在使用@ConfigurationProperties(prefix = "people")注入值时，无参构造方法必须存在，spring在创建bean的时候会根据构造方法，如果只有一个有参构造，spring就会使用这个有参构造，
 * 而@ConfigurationProperties是通过set赋值的。
 * @Description TODO
 * @Author 20609
 * @Date 2023/12/10 20:56
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@Profile("test")
@ConfigurationProperties(prefix = "people")
public class Teacher extends People{
    private Integer perNo;
    private String name;
    private String professional;
}
