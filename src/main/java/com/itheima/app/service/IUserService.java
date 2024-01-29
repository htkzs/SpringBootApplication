package com.itheima.app.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.app.pojo.User;

public interface IUserService extends IService<User> {
    //测试mybatis的乐观锁
    public void selectOrUpdateById(User user);
    void insertUserForDs1(User user);
    void insertUserForDs2(User user);
}
