package com.itheima.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter @Getter
@TableName("user")
public class User {
    //标识该字段不是mybatis plus映射的数据库表中的字段
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private Integer userAge;
    @TableField(exist = false)
    private Pet pet;

    private Integer id;
    private String name;
    private Integer age;

    public User(String userName, Integer userAge) {
        this.userName = userName;
        this.userAge = userAge;
    }

}
