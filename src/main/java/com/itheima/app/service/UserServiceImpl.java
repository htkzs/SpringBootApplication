package com.itheima.app.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.app.mapper.UserMapper;
import com.itheima.app.pojo.User;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public void selectOrUpdateById(User user) {
        userMapper.selectAndUpdateById(user);
    }
    private static final IUserService userService = (IUserService)AopContext.currentProxy();
    @DS("master")
    @Override
    public void insertUserForDs1(User user) {
        userService.save(user);
    }
    @DS("slave")
    @Override
    public void insertUserForDs2(User user) {
        userService.save(user);
        throw new NullPointerException();
    }
}
