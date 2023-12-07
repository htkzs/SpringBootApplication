package com.itheima.app.entity;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter @Getter
public class User {
    private String userName;
    private Integer userAge;
    private Pet pet;

    public User(String userName, Integer userAge) {
        this.userName = userName;
        this.userAge = userAge;
    }

}
