package com.itheima.app.mapper;

import com.itheima.app.entity.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

//@Mapper
public interface PersonMapper {

    public Person queryPersonById(Integer no);


    //向person表中插入一条数据，自动生成数据的主键 将主键返回
    @Insert("insert into person(no,name,age,height,address_id) values (null,#{name},#{age},#{height},#{addressId})")
    @Options(useGeneratedKeys = true,keyProperty = "no")
    public Boolean InsertPerson(Person person);
}
