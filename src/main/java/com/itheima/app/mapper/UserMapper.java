package com.itheima.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.app.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 使用mybatis操作数据库 extends BaseMapper<User>使用mybatisPlus查询
 */
//@Mapper
public interface UserMapper extends BaseMapper<User> {
    public List<User> getAllUser();

    void selectAndUpdateById(User user);
}
