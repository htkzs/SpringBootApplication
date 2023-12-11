package com.itheima.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @ClassName Boss
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
@ConfigurationProperties(prefix = "people")
@Profile("prod")
public class Boss extends People{
    private Integer perNo;
    private String name;
    private String professional;
}
