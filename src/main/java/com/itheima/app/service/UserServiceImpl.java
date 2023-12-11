package com.itheima.app.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.app.entity.User;
import com.itheima.app.mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author 20609
 * @Date 2023/12/10 19:21
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
