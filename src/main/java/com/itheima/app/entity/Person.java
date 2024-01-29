package com.itheima.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Person
 * @Description TODO
 * @Author 20609
 * @Date 2023/12/8 14:51
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    private Integer no;
    private String name;
    private Integer age;
    private float height;
    private Integer addressId;
}
