package com.itheima.app.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
public class User {
    //采用雪花算法生成主键
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField(value = "name")
    private String name;
    private Integer age;
    private String email;
    @TableLogic(value = "0",delval = "1")  //逻辑删除 0代表未删除，1标识记录被删除
    private Integer isDeleted;
    @Version
    private Integer version;

    public User(Long id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public User(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public User(String name, Integer age, String email, Integer isDeleted) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.isDeleted = isDeleted;
    }

    public User(Long id, String name, Integer age, String email, Integer isDeleted) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.isDeleted = isDeleted;
    }
}
